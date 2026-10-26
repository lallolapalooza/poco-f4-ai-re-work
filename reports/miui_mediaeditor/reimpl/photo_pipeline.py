#!/usr/bin/env python3
"""App-level photo edit pipeline for `com.miui.mediaeditor` (FaceUnity FuAI path).

This is the executable counterpart of PIPELINE.md §0/§3/§4/§5 for the photo
beauty path: decode + EXIF orientation -> detector quad -> landmark net ->
face-parsing net -> skin-segmentation net -> masked whitening -> PNG.

Models are the *shipped* TFLite graphs executed with the real LiteRT kernels
(`ai_edge_litert`), i.e. the same interpreter FuAI vendors; weights are the
extracted ones, nothing is initialised randomly.  No encrypted bundle entry is
used.

Confirmed tensor contracts (see GAP_CLOSURE.md for the evidence chain):

  face_landmark_mb360_64.tflite   [1,64,64,1] f32 gray 0..255 -> [1,154] f32
                                  (77 2-D points, pixel coords in the 64x64 crop)
  Fprs.0003.Exp007e33_quan1_sm    [1,512,384,3] f32 RGB 0..255 -> [1,512,384,15]
                                  softmax; cate_mask_order maps 19 UI classes to
                                  these 15 channels
  skin_model_expand_supply_...    [1,320,320,3] f32 RGB 0..1 -> [1,320,320,1]
                                  sigmoid skin probability, threshold 0.6

The input range of every net is not a guess: it follows from the first op of
each graph (SUB of 127.5 for mb360; QUANTIZE scale 1.0/zp -128 for Fprs;
QUANTIZE scale 1/128/zp -1 for the skin net).

Outputs are written to `evidence/photo_pipeline/`; stdout also serves as the
verification transcript.

Usage:
    $PY reimpl/photo_pipeline.py [--out DIR] [--strength 0.7] [--size 512]
"""

from __future__ import annotations

import argparse
import hashlib
import json
import os
import struct
import sys
import zlib

import numpy as np
from scipy import ndimage

from ai_edge_litert.interpreter import Interpreter

HERE = os.path.dirname(os.path.abspath(__file__))
REPORT = os.path.dirname(HERE)
MODELS = os.path.join(REPORT, "models")
BUNDLES = os.path.join(MODELS, "bundles")
TFLITE = os.path.join(MODELS, "tflite")
BUNDLE_ENTRIES = os.path.join(MODELS, "bundle_entries")
EVIDENCE = os.path.join(REPORT, "evidence", "photo_pipeline")

MODEL_LANDMARK = os.path.join(BUNDLES, "face_landmark_mb360_64.tflite")
MODEL_PARSING = os.path.join(BUNDLES, "Fprs.0003.Exp007e33_quan1_sm.tflite")
MODEL_SKIN = os.path.join(
    TFLITE, "model__ai_face_beauty_processor__skin_model_expand_supply_pta18000_quant_resizetrain_seg153.tflite"
)
CONFIG_PARSING = os.path.join(BUNDLES, "config_ai_face_beauty_processor.json")
LUT_SKIN_WHITE = os.path.join(
    BUNDLE_ENTRIES, "graphics__image_beauty_facebeauty", "lut", "skin_white.png"
)

# FaceUnity parsing class names, confirmed from
# graphics__image_beauty_facebeauty/image_choose_whole_white_skin_fs.glsl
# (`int background = 0, skin = 1, ... hair = 13, ... neck = 17;`).  The shader
# indexes `arrfloat_mask_index[...]`, i.e. the texture stores the remapped
# network channel, so channel 13 is hair and channel 1 is skin.
PARSING_CLASSES = {
    0: "background",
    1: "skin",
    2: "nose",
    3: "eye_g",
    4: "l_eye",
    5: "r_eye",
    6: "l_brow",
    7: "r_brow",
    8: "l_ear",
    9: "r_ear",
    10: "mouth",
    11: "u_lip",
    12: "l_lip",
    13: "hair",
    14: "neck",  # UI class 17 (neck) is the only one mapped to net channel 14
}


# --------------------------------------------------------------------------- #
# PNG I/O (self-contained: the workspace venv has no Pillow/OpenCV)
# --------------------------------------------------------------------------- #
def _png_chunk(tag: bytes, body: bytes) -> bytes:
    return (
        struct.pack(">I", len(body))
        + tag
        + body
        + struct.pack(">I", zlib.crc32(tag + body) & 0xFFFFFFFF)
    )


def write_png(path: str, rgb: np.ndarray, orientation: int | None = None) -> None:
    """Write an 8-bit RGB PNG, optionally with a PNG `eXIf` chunk (TIFF/Exif)."""
    rgb = np.ascontiguousarray(rgb.astype(np.uint8))
    h, w = rgb.shape[:2]
    raw = b"".join(b"\x00" + rgb[y].tobytes() for y in range(h))
    out = b"\x89PNG\r\n\x1a\n" + _png_chunk(
        b"IHDR", struct.pack(">IIBBBBB", w, h, 8, 2, 0, 0, 0)
    )
    if orientation is not None:
        tiff = (
            b"II*\x00"
            + struct.pack("<I", 8)
            + struct.pack("<H", 1)
            + struct.pack("<HHIHH", 0x0112, 3, 1, int(orientation), 0)
            + struct.pack("<I", 0)
        )
        out += _png_chunk(b"eXIf", tiff)
    out += _png_chunk(b"IDAT", zlib.compress(raw, 6)) + _png_chunk(b"IEND", b"")
    with open(path, "wb") as fh:
        fh.write(out)


def _png_unfilter(raw: bytes, w: int, h: int, bpp: int) -> np.ndarray:
    stride = w * bpp
    rows = np.frombuffer(raw, np.uint8).reshape(h, stride + 1)
    out = np.zeros((h, stride), np.uint8)
    prev = np.zeros((w, bpp), np.int32)
    for y in range(h):
        ft = int(rows[y, 0])
        line = rows[y, 1:].reshape(w, bpp).astype(np.int32)
        if ft == 0:
            pass
        elif ft == 1:  # Sub
            line = np.cumsum(line, axis=0) & 0xFF
        elif ft == 2:  # Up
            line = (line + prev) & 0xFF
        elif ft == 3:  # Average
            cur = np.zeros_like(line)
            for i in range(w):
                left = cur[i - 1] if i else 0
                cur[i] = (line[i] + ((left + prev[i]) >> 1)) & 0xFF
            line = cur
        elif ft == 4:  # Paeth
            cur = np.zeros_like(line)
            zero = np.zeros(bpp, np.int32)
            for i in range(w):
                a = cur[i - 1] if i else zero
                b = prev[i]
                c = prev[i - 1] if i else zero
                p = a + b - c
                pa, pb, pc = np.abs(p - a), np.abs(p - b), np.abs(p - c)
                pr = np.where((pa <= pb) & (pa <= pc), a, np.where(pb <= pc, b, c))
                cur[i] = (line[i] + pr) & 0xFF
            line = cur
        else:  # pragma: no cover
            raise ValueError(f"unsupported PNG filter {ft}")
        out[y] = line.astype(np.uint8).ravel()
        prev = line
    return out


def read_png(path: str) -> tuple[np.ndarray, dict]:
    """Decode an 8-bit RGB/RGBA non-interlaced PNG; return pixels + metadata."""
    data = open(path, "rb").read()
    if data[:8] != b"\x89PNG\r\n\x1a\n":
        raise ValueError(f"{path}: not a PNG")
    pos, idat, meta = 8, [], {}
    while pos < len(data):
        ln = struct.unpack(">I", data[pos : pos + 4])[0]
        tag = data[pos + 4 : pos + 8]
        body = data[pos + 8 : pos + 8 + ln]
        pos += 12 + ln
        if tag == b"IHDR":
            w, h, bd, ct, _comp, _filt, inter = struct.unpack(">IIBBBBB", body)
            if bd != 8 or inter != 0 or ct not in (2, 6):
                raise ValueError(f"{path}: unsupported PNG ({bd}bit ct={ct} interlace={inter})")
            meta = {"width": w, "height": h, "colortype": ct}
        elif tag == b"IDAT":
            idat.append(body)
        elif tag == b"eXIf":
            meta["exif"] = body
        elif tag == b"IEND":
            break
    bpp = 3 if meta["colortype"] == 2 else 4
    pix = _png_unfilter(zlib.decompress(b"".join(idat)), meta["width"], meta["height"], bpp)
    return pix.reshape(meta["height"], meta["width"], bpp), meta


# --------------------------------------------------------------------------- #
# EXIF orientation
# --------------------------------------------------------------------------- #
def exif_orientation(tiff: bytes | None) -> int | None:
    """Return EXIF tag 0x0112 from a TIFF/Exif block (JPEG APP1 body or PNG eXIf)."""
    if not tiff:
        return None
    if tiff[:6] == b"Exif\x00\x00":
        tiff = tiff[6:]
    if tiff[:2] == b"II":
        end = "<"
    elif tiff[:2] == b"MM":
        end = ">"
    else:
        return None
    ifd = struct.unpack(end + "I", tiff[4:8])[0]
    if ifd + 2 > len(tiff):
        return None
    n = struct.unpack(end + "H", tiff[ifd : ifd + 2])[0]
    for i in range(n):
        off = ifd + 2 + i * 12
        if off + 12 > len(tiff):
            break
        tag, typ, _cnt = struct.unpack(end + "HHI", tiff[off : off + 8])
        if tag == 0x0112 and typ == 3:
            return struct.unpack(end + "H", tiff[off + 8 : off + 10])[0]
    return None


def apply_orientation(img: np.ndarray, orientation: int) -> np.ndarray:
    """Apply the EXIF orientation transform to stored pixels (EXIF 2.32 §4.6.4)."""
    if orientation == 1:
        return img
    if orientation == 2:
        return img[:, ::-1]
    if orientation == 3:
        return img[::-1, ::-1]
    if orientation == 4:
        return img[::-1]
    if orientation == 5:
        return img.transpose(1, 0, 2)
    if orientation == 6:  # rotate 90 deg CW
        return np.rot90(img, -1)
    if orientation == 7:
        return np.rot90(img, 1)[:, ::-1]
    if orientation == 8:  # rotate 90 deg CCW
        return np.rot90(img, 1)
    raise ValueError(f"invalid EXIF orientation {orientation}")


# --------------------------------------------------------------------------- #
# Resampling (bilinear, edge-clamped, half-pixel centres like GL/LiteRT resize)
# --------------------------------------------------------------------------- #
def sample_bilinear(img: np.ndarray, xs: np.ndarray, ys: np.ndarray) -> np.ndarray:
    h, w = img.shape[:2]
    x = np.clip(xs - 0.5, 0, w - 1)
    y = np.clip(ys - 0.5, 0, h - 1)
    x0 = np.floor(x).astype(np.int64)
    y0 = np.floor(y).astype(np.int64)
    x1 = np.minimum(x0 + 1, w - 1)
    y1 = np.minimum(y0 + 1, h - 1)
    if img.ndim == 3:
        wx = (x - x0)[..., None]
        wy = (y - y0)[..., None]
        flat = img.reshape(-1, img.shape[-1])
    else:
        wx, wy = x - x0, y - y0
        flat = img.reshape(-1)
    idx = lambda yy, xx: flat[yy * w + xx]  # noqa: E731
    top = idx(y0, x0) * (1 - wx) + idx(y0, x1) * wx
    bot = idx(y1, x0) * (1 - wx) + idx(y1, x1) * wx
    return top * (1 - wy) + bot * wy


def crop_resample(img: np.ndarray, rect: tuple[float, float, float, float], wh: tuple[int, int]) -> np.ndarray:
    """Sample `rect` (x0,y0,x1,y1) into a wh=(w,h) grid with a 3/4-4/5 affine resize."""
    x0, y0, x1, y1 = rect
    out_w, out_h = wh
    xs = x0 + (np.arange(out_w) + 0.5) / out_w * (x1 - x0)
    ys = y0 + (np.arange(out_h) + 0.5) / out_h * (y1 - y0)
    X, Y = np.meshgrid(xs, ys)
    return sample_bilinear(img, X, Y)


def rect_to_image(frame: np.ndarray, rect, shape: tuple[int, int], nearest: bool = False) -> tuple[np.ndarray, np.ndarray]:
    """Resample a crop-frame map back to image space; also return the quad mask."""
    x0, y0, x1, y1 = rect
    h, w = shape
    ys, xs = np.mgrid[0:h, 0:w]
    px = xs + 0.5
    py = ys + 0.5
    inside = (px >= x0) & (px <= x1) & (py >= y0) & (py <= y1)
    ch, cw = frame.shape[:2]
    u = (px - x0) / max(x1 - x0, 1e-6) * cw
    v = (py - y0) / max(y1 - y0, 1e-6) * ch
    if nearest:
        uu = np.clip(np.floor(u).astype(np.int64), 0, cw - 1)
        vv = np.clip(np.floor(v).astype(np.int64), 0, ch - 1)
        out = frame[vv, uu]
    else:
        out = sample_bilinear(frame, u, v)
    if frame.ndim == 2:
        out = np.where(inside, out, 0.0)
    else:
        out = np.where(inside[..., None], out, 0)
    return out, inside


def expand_box(box, fx: float, fy: float | None = None):
    fx = float(fx)
    fy = fx if fy is None else float(fy)
    x0, y0, x1, y1 = box
    cx, cy = (x0 + x1) / 2.0, (y0 + y1) / 2.0
    w, h = (x1 - x0) * fx, (y1 - y0) * fy
    return (cx - w / 2.0, cy - h / 2.0, cx + w / 2.0, cy + h / 2.0)


def mean_shape_rect(lm_bbox, mean_shape: np.ndarray, net_wh: tuple[int, int]):
    """Map the 75-pt `mean_shape` bbox onto the landmark bbox (config §3 step 4).

    The engine fits the full similarity/affine from the 75 aligned landmarks.
    The 77-point mb360 layout has no recoverable correspondence to the 75-pt
    mean shape, so only the bbox correspondence is used (anisotropic scale).
    """
    ms = np.asarray(mean_shape, np.float64).reshape(-1, 2)
    mx0, my0 = np.percentile(ms, 2.0, axis=0)
    mx1, my1 = np.percentile(ms, 98.0, axis=0)
    lx0, ly0, lx1, ly1 = lm_bbox
    sx = (lx1 - lx0) / (mx1 - mx0)
    sy = (ly1 - ly0) / (my1 - my0)
    w, h = net_wh
    return (lx0 - sx * mx0, ly0 - sy * my0, lx0 - sx * mx0 + sx * w, ly0 - sy * my0 + sy * h)


# --------------------------------------------------------------------------- #
# Input generation (synthetic studio portrait) and decode
# --------------------------------------------------------------------------- #
def synth_face(size: int = 512) -> np.ndarray:
    """Procedural face-like RGB image (uint8), deterministic."""
    S = size
    yy, xx = np.mgrid[0:S, 0:S].astype(np.float32)
    img = np.zeros((S, S, 3), np.float32)
    t = yy / S
    img[..., 0] = 70 + 40 * t
    img[..., 1] = 80 + 35 * t
    img[..., 2] = 95 + 30 * t
    img += np.random.default_rng(7).normal(0, 3, img.shape)

    cx, cy = size * 0.5, size * 0.518
    ax, ay = size * 0.230, size * 0.312
    face = ((xx - cx) / ax) ** 2 + ((yy - cy) / ay) ** 2 <= 1.0
    skin = np.stack(
        [np.full_like(xx, 226), np.full_like(xx, 178), np.full_like(xx, 150)], -1
    )
    shade = 0.86 + 0.14 * (1 - ((xx - cx) / ax) ** 2)
    img = np.where(face[..., None], skin * shade[..., None], img)

    hair = (((xx - cx) / (ax * 1.12)) ** 2 + ((yy - (cy - 40)) / (ay * 0.94)) ** 2 <= 1.0) & (
        yy < cy - 45
    )
    img = np.where(hair[..., None], np.array([46, 34, 30], np.float32), img)
    neck = (np.abs(xx - cx) < 55) & (yy > cy + 120) & (yy < cy + 220)
    img = np.where(neck[..., None], np.array([205, 158, 132], np.float32), img)

    for sx in (-1, 1):
        bx = cx + sx * 52
        brow = ((xx - bx) / 34.0) ** 2 + ((yy - (cy - 52) - (sx * (xx - bx)) * 0.12) / 9.0) ** 2 <= 1.0
        img = np.where(brow[..., None], np.array([60, 42, 34], np.float32), img)
    for sx in (-1, 1):
        ex, ey = cx + sx * 52, cy - 18
        sc = ((xx - ex) / 27.0) ** 2 + ((yy - ey) / 14.0) ** 2 <= 1.0
        img = np.where(sc[..., None], np.array([248, 246, 244], np.float32), img)
        ir = ((xx - ex) / 13.0) ** 2 + ((yy - ey) / 13.0) ** 2 <= 1.0
        img = np.where(ir[..., None], np.array([74, 104, 126], np.float32), img)
        pu = ((xx - ex) / 6.0) ** 2 + ((yy - ey) / 6.0) ** 2 <= 1.0
        img = np.where(pu[..., None], np.array([18, 18, 22], np.float32), img)
        img = np.where((sc & (yy < ey - 4))[..., None], img * 0.82, img)

    nos = ((xx - cx) / 17.0) ** 2 + ((yy - (cy + 52)) / 40.0) ** 2 <= 1.0
    img = np.where(nos[..., None], img * 0.93, img)
    for sx in (-1, 1):
        nl = ((xx - (cx + sx * 11)) / 5.0) ** 2 + ((yy - (cy + 86)) / 4.0) ** 2 <= 1.0
        img = np.where(nl[..., None], np.array([120, 86, 72], np.float32), img)
    mu = ((xx - cx) / 46.0) ** 2 + ((yy - (cy + 118)) / 13.0) ** 2 <= 1.0
    ml = ((xx - cx) / 44.0) ** 2 + ((yy - (cy + 134)) / 15.0) ** 2 <= 1.0
    img = np.where(mu[..., None], np.array([168, 86, 86], np.float32), img)
    img = np.where(ml[..., None], np.array([196, 104, 102], np.float32), img)
    return np.clip(img, 0, 255).astype(np.uint8)


def detect_face_box(rgb: np.ndarray) -> tuple[int, int, int, int]:
    """Classical YCbCr skin-chroma face quad.

    Stand-in for the two YOLO detector nets (`n-small_256_0.05_only_a5`,
    `n-0.5_416_0.05_4aspect6`): their anchor/head decoding is not recovered
    from the APK, so the detector stage is deliberately a non-DNN box.
    """
    R, G, B = (rgb[..., i].astype(np.float32) for i in range(3))
    Y = 0.299 * R + 0.587 * G + 0.114 * B
    Cb = 128.0 - 0.168736 * R - 0.331264 * G + 0.5 * B
    Cr = 128.0 + 0.5 * R - 0.418688 * G - 0.081312 * B
    skin = (Cb >= 77) & (Cb <= 127) & (Cr >= 133) & (Cr <= 173) & (Y >= 40)
    skin = ndimage.binary_opening(skin, np.ones((5, 5)))
    skin = ndimage.binary_closing(skin, np.ones((5, 5)))
    lab, n = ndimage.label(skin)
    if n == 0:
        raise RuntimeError("no skin-chroma region found")
    sizes = ndimage.sum(skin, lab, index=np.arange(1, n + 1))
    k = int(np.argmax(sizes)) + 1
    ys, xs = np.where(lab == k)
    box = (int(xs.min()), int(ys.min()), int(xs.max()) + 1, int(ys.max()) + 1)
    if sizes[k - 1] < 0.02 * skin.size:
        raise RuntimeError(f"skin region too small ({sizes[k-1]:.0f} px)")
    return box


# --------------------------------------------------------------------------- #
# TFLite execution
# --------------------------------------------------------------------------- #
def run_tflite(path: str, x: np.ndarray, num_threads: int = 2):
    it = Interpreter(model_path=path, num_threads=num_threads)
    it.allocate_tensors()
    ind = it.get_input_details()[0]
    if list(x.shape) != list(ind["shape"]):
        raise ValueError(f"{os.path.basename(path)}: input {x.shape} != {tuple(ind['shape'])}")
    it.set_tensor(ind["index"], x.astype(ind["dtype"]))
    it.invoke()
    outs = [it.get_tensor(d["index"]) for d in it.get_output_details()]
    return outs, ind, it.get_output_details()


# --------------------------------------------------------------------------- #
# Whitening LUT (the bundle's real shader maths + real LUT asset)
# --------------------------------------------------------------------------- #
def lut_whiten(rgb: np.ndarray, lut: np.ndarray) -> np.ndarray:
    """`shader_main_s_color_custom_shader_whitten` from
    image_white_split_fs.glsl (8x8x64 LUT in a 512x512 RGBA texture)."""
    tex = np.asarray(rgb, np.float32)
    tex = np.clip(tex, 0.0, 1.0)
    raw = col = 8.0
    dim = 64.0
    r_raw, r_col = 1.0 / raw, 1.0 / col
    blue = tex[..., 2] * (dim - 1.0)
    q1y = np.floor(np.floor(blue) / raw)
    q1x = np.floor(blue) - q1y * col
    q2y = np.floor(np.ceil(blue) / raw)
    q2x = np.ceil(blue) - q2y * col
    eps = 0.5 / (col * dim)
    span_c = r_col - 1.0 / (col * dim)
    span_r = r_raw - 1.0 / (raw * dim)
    p1x = q1x * r_col + eps + span_c * tex[..., 0]
    p1y = q1y * r_raw + eps + span_r * tex[..., 1]
    p2x = q2x * r_col + eps + span_c * tex[..., 0]
    p2y = q2y * r_raw + eps + span_r * tex[..., 1]

    lut_f = lut.astype(np.float32) / 255.0
    lh, lw = lut_f.shape[:2]

    def sample(u, v):
        x = np.clip(u * lw - 0.5, 0, lw - 1)
        y = np.clip(v * lh - 0.5, 0, lh - 1)
        x0 = np.floor(x).astype(np.int64)
        y0 = np.floor(y).astype(np.int64)
        x1 = np.minimum(x0 + 1, lw - 1)
        y1 = np.minimum(y0 + 1, lh - 1)
        wx = (x - x0)[..., None]
        wy = (y - y0)[..., None]
        return (lut_f[y0, x0] * (1 - wx) + lut_f[y0, x1] * wx) * (1 - wy) + (
            lut_f[y1, x0] * (1 - wx) + lut_f[y1, x1] * wx
        ) * wy

    c1 = sample(p1x, p1y)
    c2 = sample(p2x, p2y)
    f = (blue - np.floor(blue))[..., None]
    return (c1 * (1 - f) + c2 * f)[..., :3]


# --------------------------------------------------------------------------- #
# Pipeline
# --------------------------------------------------------------------------- #
def main(argv=None) -> int:
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--out", default=EVIDENCE)
    ap.add_argument("--input", default=None,
                    help="decode this 8-bit RGB/RGBA PNG instead of generating the synthetic face")
    ap.add_argument("--size", type=int, default=512)
    ap.add_argument("--exif-orientation", type=int, default=6, help="orientation tag written into the synthetic PNG")
    ap.add_argument("--strength", type=float, default=0.7, help="whitening strength in [0,1] ((rate-0.5)/0.5)")
    ap.add_argument("--landmark-margin", type=float, default=1.10)
    ap.add_argument("--parsing-margin", type=float, default=1.0,
                    help="extra scale on the mean-shape-fitted parsing crop")
    ap.add_argument("--json", default=None, help="stats json path (default <out>/stats.json)")
    args = ap.parse_args(argv)

    os.makedirs(args.out, exist_ok=True)
    out_json = args.json or os.path.join(args.out, "stats.json")
    log = lambda *a: print(*a, flush=True)  # noqa: E731

    for p in (MODEL_LANDMARK, MODEL_PARSING, MODEL_SKIN, CONFIG_PARSING, LUT_SKIN_WHITE):
        if not os.path.isfile(p):
            log(f"FAIL: missing asset {p}")
            return 2
    cfg = json.load(open(CONFIG_PARSING))
    parsing_cfg = cfg["face_parsing"]
    skin_cfg = cfg["face_skin_seg"]
    pw, ph = parsing_cfg["image_width"], parsing_cfg["image_height"]
    sw, sh = skin_cfg["image_width"], skin_cfg["image_height"]
    cate_mask_order = parsing_cfg["cate_mask_order"]
    ms = np.asarray(parsing_cfg["mean_shape"], np.float64)
    hair_channel = 13
    assert cate_mask_order.index(hair_channel) == 13  # UI class 13 (hair) -> channel 13
    assert cate_mask_order.index(1) == 1  # UI class 1 (skin) -> channel 1

    log("=" * 78)
    log("miui_mediaeditor photo pipeline (FaceUnity FuAI photo path)")
    log("=" * 78)
    log(f"landmark : {MODEL_LANDMARK}  ({os.path.getsize(MODEL_LANDMARK)} B)")
    log(f"parsing  : {MODEL_PARSING}  ({os.path.getsize(MODEL_PARSING)} B)")
    log(f"skin-seg : {MODEL_SKIN}  ({os.path.getsize(MODEL_SKIN)} B)")
    log(f"lut      : {LUT_SKIN_WHITE}  ({os.path.getsize(LUT_SKIN_WHITE)} B)")
    log(f"config   : {os.path.basename(CONFIG_PARSING)} "
        f"(parsing {pw}x{ph}, skin {sw}x{sh}, mask_threshold={skin_cfg['mask_threshold']}, "
        f"keep_block_radio={skin_cfg['keep_block_radio']})")
    asset_sha = {}
    for p in (MODEL_LANDMARK, MODEL_PARSING, MODEL_SKIN, LUT_SKIN_WHITE):
        h = hashlib.sha256(open(p, "rb").read()).hexdigest()
        asset_sha[os.path.basename(p)] = h
        log(f"    sha256 {h[:16]}  {os.path.basename(p)}")

    stats: dict = {"stages": {}, "encrypted_models_used": [], "asset_sha256": asset_sha, "models": {
        "landmark": os.path.relpath(MODEL_LANDMARK, REPORT),
        "parsing": os.path.relpath(MODEL_PARSING, REPORT),
        "skin_seg": os.path.relpath(MODEL_SKIN, REPORT),
        "lut": os.path.relpath(LUT_SKIN_WHITE, REPORT),
    }}

    # ---- stage 1: source + PNG(eXIf) decode ------------------------------- #
    log("\n[1] decode / EXIF orientation")
    if args.input:
        src_path = os.path.abspath(args.input)
        pix, meta = read_png(src_path)
        orient = exif_orientation(meta.get("exif"))
        img = apply_orientation(pix[..., :3], orient if orient else 1).astype(np.uint8)
        restored = None
        meta_txt = (f"file {pix.shape[1]}x{pix.shape[0]} ct={meta['colortype']} "
                    f"exif_orientation={orient} -> decoded {img.shape[1]}x{img.shape[0]}x{img.shape[2]}")
    else:
        upright = synth_face(args.size)
        src_path = os.path.join(args.out, "source_exif.png")
        # store the raster pre-rotated against the tag so the tag is load-bearing
        stored = np.ascontiguousarray(np.rot90(upright, 1))
        write_png(src_path, stored, orientation=args.exif_orientation)
        pix, meta = read_png(src_path)
        orient = exif_orientation(meta.get("exif"))
        img = apply_orientation(pix[..., :3], orient if orient else 1).astype(np.uint8)
        restored = img.shape == upright.shape and np.array_equal(img, upright)
        meta_txt = (f"stored {pix.shape[1]}x{pix.shape[0]} ct={meta['colortype']} "
                    f"exif_orientation={orient} -> decoded {img.shape[1]}x{img.shape[0]}x{img.shape[2]}")
    log(f"    {meta_txt}")
    if restored is False:
        log("FAIL: EXIF orientation did not restore the upright raster")
        return 3
    write_png(os.path.join(args.out, "decoded_upright.png"), img)
    stats["stages"]["decode"] = {
        "input": meta_txt,
        "shape": list(img.shape),
        "exif_orientation": orient,
        "equals_source": restored,
    }

    # ---- stage 2: detector quad (classical stand-in) ---------------------- #
    log("\n[2] face quad (skin-chroma stand-in for the YOLO detector nets)")
    face_box = detect_face_box(img)
    log(f"    box={face_box}  size={face_box[2]-face_box[0]}x{face_box[3]-face_box[1]}")
    stats["stages"]["detect"] = {"box": list(face_box), "method": "ycbcr_skin_chroma"}

    # ---- stage 3: landmark net ------------------------------------------- #
    log("\n[3] face_landmark_mb360_64")
    lm_rect = expand_box(face_box, args.landmark_margin)
    lm_crop = crop_resample(img, lm_rect, (64, 64)).mean(axis=2)[None, ..., None].astype(np.float32)
    (lm_out,), _, _ = run_tflite(MODEL_LANDMARK, lm_crop)
    lm = np.asarray(lm_out, np.float32).reshape(-1, 2)
    iw, ih = lm_rect[2] - lm_rect[0], lm_rect[3] - lm_rect[1]
    lm_img = np.stack([lm_rect[0] + lm[:, 0] / 64.0 * iw, lm_rect[1] + lm[:, 1] / 64.0 * ih], 1)
    lx0, ly0 = lm_img.min(0)
    lx1, ly1 = lm_img.max(0)
    log(f"    input  {tuple(lm_crop.shape)} f32 [{lm_crop.min():.1f},{lm_crop.max():.1f}] "
        f"(graph first op subtracts 127.5 -> gray 0..255)")
    log(f"    output {tuple(np.shape(lm_out))} f32  [{lm.min():.2f},{lm.max():.2f}] "
        f"= {len(lm)} 2-D points, crop-pixel coords")
    log(f"    image-space landmark bbox = ({lx0:.1f},{ly0:.1f})-({lx1:.1f},{ly1:.1f})")
    write_png(os.path.join(args.out, "face_crop_landmark64.png"), np.repeat(lm_crop[0].astype(np.uint8), 3, -1))
    vis = img.copy()
    yy, xx = np.mgrid[-2:3, -2:3]
    disc = yy**2 + xx**2 <= 4
    for i, (x, y) in enumerate(lm_img):
        xi, yi = int(round(x)), int(round(y))
        sub = vis[max(0, yi - 2): yi + 3, max(0, xi - 2): xi + 3]
        sub[disc[: sub.shape[0], : sub.shape[1]]] = (255, 0, 0) if i < 75 else (0, 255, 0)
    write_png(os.path.join(args.out, "landmarks.png"), vis)
    stats["stages"]["landmark"] = {
        "input_shape": list(lm_crop.shape),
        "input_range": [float(lm_crop.min()), float(lm_crop.max())],
        "input_normalization": "gray 0..255 (graph op0 = SUB(127.5))",
        "output_shape": list(np.shape(lm_out)),
        "output_range": [float(lm.min()), float(lm.max())],
        "image_bbox": [float(lx0), float(ly0), float(lx1), float(ly1)],
        "points": len(lm),
        "output_std": float(lm.std()),
    }

    # ---- stage 4: face parsing ------------------------------------------- #
    log("\n[4] Fprs.0003.Exp007e33_quan1_sm (15-class face parsing)")
    parse_rect = mean_shape_rect((lx0, ly0, lx1, ly1), ms, (pw, ph))
    if args.parsing_margin != 1.0:
        parse_rect = expand_box(parse_rect, args.parsing_margin)
    log(f"    crop   mean-shape bbox fit -> x={parse_rect[0]:.1f}..{parse_rect[2]:.1f}, "
        f"y={parse_rect[1]:.1f}..{parse_rect[3]:.1f}")
    p_crop = crop_resample(img, parse_rect, (pw, ph)).astype(np.float32)
    (prob,), _, _ = run_tflite(MODEL_PARSING, p_crop[None])
    prob = np.asarray(prob, np.float32)[0]
    label = prob.argmax(-1)
    hist = np.bincount(label.ravel(), minlength=15)
    skin_ch1 = prob[..., 1]
    hair = label == hair_channel
    log(f"    input  {tuple(p_crop.shape)} f32 [{p_crop.min():.1f},{p_crop.max():.1f}] "
        f"(graph op0 QUANTIZE scale 1.0 zp -128 -> raw 0..255)")
    log(f"    output {prob.shape} f32 sum=[{prob.sum(-1).min():.3f},{prob.sum(-1).max():.3f}]")
    log("    argmax class histogram " + ", ".join(
        f"{PARSING_CLASSES[c]}={hist[c]}" for c in range(15) if hist[c] > 0))
    log(f"    channel 1 (skin) mean={skin_ch1.mean():.4f}  "
        f"channel 13 (hair) pixels={int(hair.sum())} ({hair.mean()*100:.2f}%)")
    write_png(os.path.join(args.out, "parsing_crop_384x512.png"),
              np.clip(p_crop, 0, 255).astype(np.uint8))
    palette = np.array([
        [0, 0, 0], [220, 180, 150], [40, 30, 25], [200, 60, 60], [60, 200, 60],
        [60, 60, 220], [220, 220, 60], [220, 60, 220], [60, 220, 220], [255, 140, 0],
        [140, 0, 255], [0, 140, 255], [255, 255, 255], [128, 128, 128], [160, 90, 40],
    ], np.uint8)
    write_png(os.path.join(args.out, "parsing_labels.png"), palette[label])
    stats["stages"]["parsing"] = {
        "input_shape": list(p_crop.shape),
        "input_normalization": "RGB 0..255 (graph op0 QUANTIZE scale=1.0 zp=-128)",
        "output_shape": list(prob.shape),
        "class_histogram": {PARSING_CLASSES[c]: int(hist[c]) for c in range(15)},
        "skin_ch1_mean": float(skin_ch1.mean()),
        "hair_fraction": float(hair.mean()),
        "label_map_meaning": "argmax over 15 softmax channels; u8 label texture",
    }

    # ---- stage 5: skin segmentation -------------------------------------- #
    log("\n[5] skin_model_expand_supply_pta18000_quant_resizetrain_seg153")
    s_crop = crop_resample(img, parse_rect, (sw, sh)).astype(np.float32) / 255.0
    (smask,), _, _ = run_tflite(MODEL_SKIN, s_crop[None])
    smask = np.asarray(smask, np.float32)[0, ..., 0]
    thr = float(skin_cfg.get("mask_threshold", 0.6))
    keep = float(skin_cfg.get("keep_block_radio", 0.01))
    binm = smask >= thr
    lab, n = ndimage.label(binm, structure=np.ones((3, 3), bool))
    removed = 0
    if n:
        areas = ndimage.sum(binm, lab, index=np.arange(1, n + 1))
        keep_ids = np.where(areas >= keep * binm.size)[0] + 1
        removed = n - len(keep_ids)
        binm = np.isin(lab, keep_ids)
    log(f"    input  {tuple(s_crop.shape)} f32 [{s_crop.min():.3f},{s_crop.max():.3f}] "
        f"(graph op0 QUANTIZE scale 1/128 zp -1 -> RGB 0..1)")
    log(f"    output {smask.shape} f32 [{smask.min():.3f},{smask.max():.3f}] "
        f"mean={smask.mean():.3f}")
    log(f"    threshold {thr} -> coverage {binm.mean()*100:.2f}% ; "
        f"components={n}, dropped(<{keep*100:.0f}% area)={removed}")
    mask_crop = ndimage.binary_fill_holes(binm).astype(np.float32)
    write_png(os.path.join(args.out, "mask_skin.png"),
              np.repeat((mask_crop * 255).astype(np.uint8)[..., None], 3, -1))
    stats["stages"]["skin_seg"] = {
        "input_shape": list(s_crop.shape),
        "input_normalization": "RGB 0..1 (graph op0 QUANTIZE scale=1/128 zp=-1)",
        "output_shape": list(smask.shape),
        "output_range": [float(smask.min()), float(smask.max())],
        "threshold": thr,
        "keep_block_radio": keep,
        "coverage_after_cc": float(binm.mean()),
        "components": int(n),
        "components_dropped": int(removed),
    }

    # ---- stage 6: image_space masks + whitening LUT blend ----------------- #
    log("\n[6] mask -> image space + skin_white LUT alpha blend")
    mask_img, _ = rect_to_image(mask_crop.astype(np.float32), parse_rect, img.shape[:2])
    parse_skin_img, _ = rect_to_image(skin_ch1.astype(np.float32), parse_rect, img.shape[:2])
    label_img, _ = rect_to_image(label, parse_rect, img.shape[:2], nearest=True)
    # tex_face_rate_mask is the detected-face region in image space, not the
    # padded network crop; use the landmark quad expanded by 10 %.
    face_quad = expand_box((lx0, ly0, lx1, ly1), 1.10)
    inside = np.zeros(img.shape[:2], bool)
    x0i, y0i = max(0, int(np.floor(face_quad[0]))), max(0, int(np.floor(face_quad[1])))
    x1i, y1i = min(img.shape[1], int(np.ceil(face_quad[2]))), min(img.shape[0], int(np.ceil(face_quad[3])))
    inside[y0i:y1i, x0i:x1i] = True
    # image_choose_whole_white_skin_fs.glsl: `whole_skin_color = clamp(
    # skin_color + skin_white_color, 0, 1)`; here skin_color = skin-seg net and
    # skin_white_color = parsing channel 1 (skin).
    skin_only = np.where(inside, mask_img, 0.0)
    mask_img = np.clip(skin_only + np.where(inside, parse_skin_img, 0.0), 0.0, 1.0)
    hair_img = inside & (label_img == hair_channel)
    log(f"    mask   skin-seg in quad={float(skin_only[inside].mean()):.4f} "
        f"+ parsing ch1={float(parse_skin_img[inside].mean()):.4f} "
        f"-> {float(mask_img[inside].mean()):.4f}")

    # image_white_split_fs.glsl:  alpha = clamp(mask.g + mask.b) with a 0.3
    # floor; hair (parsing channel 13) is zeroed by the caller chain.  The
    # face-rate texture (`mask.b`) is produced by a GL pass that is not in the
    # APK, so the explicit quad replaces it; the floor is quad-limited.
    alpha = np.clip(mask_img, 0.0, 1.0)
    alpha = np.maximum(alpha, 0.3 * inside.astype(np.float32))
    alpha = np.where(hair_img, 0.0, alpha) * float(args.strength)

    lut, lut_meta = read_png(LUT_SKIN_WHITE)
    if lut.shape[0] != 512 or lut.shape[1] != 512:
        log(f"FAIL: unexpected LUT size {lut.shape}")
        return 4
    src01 = img.astype(np.float32) / 255.0
    white01 = lut_whiten(src01, lut)
    out01 = src01 * (1.0 - alpha[..., None]) + white01 * alpha[..., None]
    rendered = np.clip(np.rint(out01 * 255.0), 0, 255).astype(np.uint8)
    out_path = os.path.join(args.out, "rendered.png")
    write_png(out_path, rendered)
    write_png(os.path.join(args.out, "rendered_mask_overlay.png"),
              np.clip(img.astype(np.float32) * (1 - alpha[..., None] * 0.8)
                      + np.array([255, 0, 0]) * alpha[..., None] * 0.8, 0, 255).astype(np.uint8))

    quad = inside & (alpha > 0)
    luma_src = src01.mean(-1)
    luma_out = out01.mean(-1)
    if quad.any():
        delta = float((luma_out[quad] - luma_src[quad]).mean())
        mean_mask = float(alpha[quad].mean())
    else:
        delta, mean_mask = 0.0, 0.0
    log(f"    lut    {tuple(lut.shape)} ct={lut_meta['colortype']} (8x8x64 tiles)")
    log(f"    alpha  shape={alpha.shape} mean(in quad)={mean_mask:.3f} "
        f"pixels={int(quad.sum())} ({quad.mean()*100:.1f}% of image)")
    log(f"    quad   x=[{x0i},{x1i}) y=[{y0i},{y1i})  hair pixels zeroed={int(hair_img.sum())}")
    log(f"    luma   mean before={float(luma_src[quad].mean()) if quad.any() else 0:.4f} "
        f"after={float(luma_out[quad].mean()) if quad.any() else 0:.4f} "
        f"delta={delta:+.4f}")
    stats["stages"]["blend"] = {
        "lut_shape": list(lut.shape),
        "mask_sources": {
            "skin_seg_net_mean_in_quad": float(skin_only[inside].mean()),
            "parsing_ch1_mean_in_quad": float(parse_skin_img[inside].mean()),
            "combined_mask_mean_in_quad": float(mask_img[inside].mean()),
        },
        "alpha_mean_in_quad": mean_mask,
        "quad_pixels": int(quad.sum()),
        "hair_pixels_zeroed": int(hair_img.sum()),
        "strength": float(args.strength),
        "luma_before": float(luma_src[quad].mean()) if quad.any() else 0.0,
        "luma_after": float(luma_out[quad].mean()) if quad.any() else 0.0,
        "luma_delta": delta,
        "output": os.path.relpath(out_path, REPORT),
    }
    stats["parsing_rect"] = [float(v) for v in parse_rect]
    stats["face_quad"] = [float(v) for v in face_quad]

    # ---- summary ---------------------------------------------------------- #
    ok = (
        (args.input is not None or img.shape == (args.size, args.size, 3))
        and prob.shape == (ph, pw, 15)
        and smask.shape == (sh, sw)
        and lm.std() > 1.0                      # landmark net is input-dependent
        and float(smask.max() - smask.min()) > 0.05  # skin net is input-dependent
        and abs(float(prob.sum(-1).mean()) - 1.0) < 0.05  # parsing softmax normalised
        and os.path.getsize(out_path) > 0
        and quad.any()
        and alpha.max() > 0
        and np.isfinite(delta)
    )
    stats["status"] = "PASS" if ok else "FAIL"
    with open(out_json, "w") as fh:
        json.dump(stats, fh, indent=1)
    log("\n" + "-" * 78)
    def _disp(p):
        r = os.path.relpath(p, REPORT)
        return r if not r.startswith("..") else os.path.abspath(p)

    for name, path in (
        ("source", src_path),
        ("decoded upright", os.path.join(args.out, "decoded_upright.png")),
        ("landmark crop", os.path.join(args.out, "face_crop_landmark64.png")),
        ("landmarks", os.path.join(args.out, "landmarks.png")),
        ("parsing crop", os.path.join(args.out, "parsing_crop_384x512.png")),
        ("parsing labels", os.path.join(args.out, "parsing_labels.png")),
        ("skin mask", os.path.join(args.out, "mask_skin.png")),
        ("rendered", out_path),
        ("stats", out_json),
    ):
        log(f"    {name:22s} {_disp(path)}  ({os.path.getsize(path)} B)")
    log(f"RESULT: {'PASS' if ok else 'FAIL'}")
    return 0 if ok else 1


if __name__ == "__main__":
    sys.exit(main())
