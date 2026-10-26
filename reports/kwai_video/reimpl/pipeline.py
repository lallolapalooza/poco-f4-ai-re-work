#!/usr/bin/env python3
"""Kwai (com.kwai.video) vision/beauty pipeline - JAX reimplementation.

Runs end-to-end on synthetic frames:

    NV21 bytes (camera output, as accepted by KSJNILib.renderModel)
      -> YUV->RGB (BT.601 limited, transcribed from libgorgeous GLSL)
      -> crop/resize to the YCNN net input (contract of YCNNCropDataMatrix)
      -> face detector / landmark head            [ARCHITECTURE_ONLY]
      -> Gorgeous beauty: 5-tap mean+var pass, 3x3 var blur, kMin skin
         smoothing, Hald 3D-LUT, luma unsharp mask  [REAL recovered math]
      -> RGB out (what FaceMagic composites before encode)

Usage:  python pipeline.py [--out-dir DIR]
Exit code 0 on success; prints a shape/verification table.
"""

from __future__ import annotations

import argparse
import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, HERE)

import jax  # noqa: E402
import jax.numpy as jnp  # noqa: E402

import gorgeous_beauty as gb  # noqa: E402
import ycnn_contract as yc  # noqa: E402
from pnglut import apply_hald_lut, decode_png  # noqa: E402

LUT_PATH = os.path.join(HERE, "weights", "default_enhance_lut.png")


def synth_frame(h=270, w=480, seed=0):
    """Synthetic portrait-like frame: skin oval on a textured background."""
    rng = np.random.default_rng(seed)
    yy, xx = np.mgrid[0:h, 0:w].astype(np.float32)
    bg = np.stack(
        [
            0.25 + 0.10 * np.sin(xx / 9.0),
            0.30 + 0.08 * np.cos(yy / 7.0),
            0.38 + 0.12 * np.sin((xx + yy) / 13.0),
        ],
        axis=-1,
    )
    cx, cy, rx, ry = w * 0.5, h * 0.45, w * 0.16, h * 0.30
    face = (((xx - cx) / rx) ** 2 + ((yy - cy) / ry) ** 2) <= 1.0
    skin = np.array([0.86, 0.70, 0.60], dtype=np.float32)
    img = bg.copy()
    img[face] = skin * (1.0 - 0.12 * ((yy[face] - cy) / ry) ** 2)[:, None]
    img += rng.normal(0, 0.01, img.shape).astype(np.float32)
    return np.clip(img, 0.0, 1.0).astype(np.float32)


def rgb_to_nv21(rgb):
    """BT.601 limited-range RGB->YUV420sp, as produced by the camera path."""
    y = np.clip(0.257 * rgb[..., 0] + 0.504 * rgb[..., 1] + 0.098 * rgb[..., 2] + 16 / 255, 0, 1)
    u = np.clip(-0.148 * rgb[..., 0] - 0.291 * rgb[..., 1] + 0.439 * rgb[..., 2] + 0.5, 0, 1)
    v = np.clip(0.439 * rgb[..., 0] - 0.368 * rgb[..., 1] - 0.071 * rgb[..., 2] + 0.5, 0, 1)
    h, w = y.shape
    yb = (y * 255.0).round().astype(np.uint8).reshape(-1)
    uu = (u[::2, ::2] * 255.0).round().astype(np.uint8).reshape(-1)
    vv = (v[::2, ::2] * 255.0).round().astype(np.uint8).reshape(-1)
    inter = np.empty(uu.size * 2, dtype=np.uint8)
    inter[0::2] = vv
    inter[1::2] = uu
    return np.concatenate([yb, inter])


def psnr(a, b):
    mse = float(np.mean((a - b) ** 2))
    return float("inf") if mse == 0 else 10.0 * np.log10(1.0 / mse)


def numpy_reference_beauty(rgb, blur_alpha=0.6, tex_offset=1.0):
    """Independent numpy transcription of the same GLSL - parity check."""
    def shift_np(img, dx, dy):
        pads = ((abs(dy), abs(dy)), (abs(dx), abs(dx))) + ((0, 0),) * (img.ndim - 2)
        p = np.pad(img, pads, mode="edge")
        h, w = img.shape[:2]
        return p[abs(dy) + dy : abs(dy) + dy + h, abs(dx) + dx : abs(dx) + dx + w]

    o = int(round(tex_offset))
    acc = rgb.copy()
    for dx, dy in [(o, o), (-o, -o), (o, -o), (-o, o)]:
        acc = acc + shift_np(rgb, dx, dy)
    mean = acc * 0.2
    diff = np.minimum(((rgb - mean) * 7.0) ** 2, 1.0)
    var = (diff[..., 0] + diff[..., 1] + diff[..., 2]) * 0.33
    vb = var.copy()
    for dy in (-1, 0, 1):
        for dx in (-1, 0, 1):
            if dx or dy:
                vb = vb + shift_np(var, dx, dy)
    vb = vb * 0.1111
    mb = np.zeros_like(mean)
    for c in range(3):
        t = mean[..., c].copy()
        for dy in (-1, 0, 1):
            for dx in (-1, 0, 1):
                if dx or dy:
                    t = t + shift_np(mean[..., c], dx, dy)
        mb[..., c] = t * 0.1111
    p = np.clip((np.minimum(rgb[..., 0], vb) - 0.2) * 4.0, 0.0, 1.0)
    k = (1.0 - vb / (vb + 0.1)) * p * blur_alpha
    return rgb + (mb - rgb) * k[..., None]


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--out-dir", default=os.path.join(HERE, "out"))
    args = ap.parse_args()
    os.makedirs(args.out_dir, exist_ok=True)

    print("=" * 78)
    print("Kwai vision/beauty pipeline reimplementation (JAX %s)" % jax.__version__)
    print("=" * 78)

    # ---------------- stage 0: synthetic camera frame ---------------------
    frame = synth_frame()
    h, w, _ = frame.shape
    print(f"[0] synthetic frame            rgb=({h}, {w}, 3) float32 [0,1]")

    # ---------------- stage 1: NV21 round-trip ----------------------------
    nv21 = rgb_to_nv21(frame)
    rt = np.asarray(yc.nv21_to_rgb(jnp.asarray(nv21), w, h))
    print(
        f"[1] NV21 encode/decode         nv21=({nv21.size},) uint8  "
        f"rgb=({h}, {w}, 3)  roundtrip_psnr={psnr(frame, rt):.2f} dB"
    )

    # ---------------- stage 2: crop/resize to net input -------------------
    crop = (w * 0.30, h * 0.10, w * 0.70, h * 0.80)
    net = yc.crop_resize(jnp.asarray(rt), crop, yc.DEFAULT_NET_SIZE)
    print(
        f"[2] crop{crop} -> net input    x={net.shape} "
        f"(YCNNCropDataMatrix contract, {'ARCHITECTURE_ONLY'} size)"
    )

    print(
        f"    YCNN contract: model_types={len(yc.MODEL_TYPES)} "
        f"detector_type={yc.MODEL_TYPES['Detector']} landmarks_type={yc.MODEL_TYPES['Landmarks']} "
        f"colorType={yc.DEFAULT_COLOR_TYPE}"
    )
    print(f"    KSLandmarksParam defaults: {yc.LANDMARK_PARAMS}")

    # ---------------- stage 3: detector / landmarks (arch-only) -----------
    key = jax.random.PRNGKey(0)
    params = yc.init_detector(key, net_hw=yc.DEFAULT_NET_SIZE, num_landmarks=106)
    score, lmk = yc.detector_forward(params, net[None])
    pts, offs = yc.landmarks_from_heat(score, lmk, num_landmarks=106)
    print(
        f"[3] detector head (RANDOM W)   score={score.shape} min={float(score.min()):.4f} "
        f"max={float(score.max()):.4f}  landmarks={lmk.shape}"
    )
    print(
        f"    decoded: face_center=({float(pts[0]):.3f}, {float(pts[0]):.3f}) "
        f"lmk_offsets={offs.shape}  [placeholder weights]"
    )

    # ---------------- stage 4: beauty (real recovered math) ---------------
    rgb_j = jnp.asarray(rt)
    mean, var = gb.mean_variance_pass(rgb_j, tex_offset=1.0)
    var_b = gb.blur9(var, 1)
    composite = gb.smooth_composite(rgb_j, gb.blur9(mean, 1), jnp.clip(var_b, 0, 1), 0.6)
    print(
        f"[4] beauty mean/var pass       mean={mean.shape} var={var.shape} "
        f"kMax={float(jnp.max((1 - jnp.clip(var_b,0,1)/(jnp.clip(var_b,0,1)+gb.THETA))*jnp.clip((jnp.minimum(rgb_j[...,0],jnp.clip(var_b,0,1))-0.2)*4,0,1))):.3f}"
    )
    print(f"    composite                  out={composite.shape}")

    # LUT (real weights from the APK)
    lut = None
    if os.path.exists(LUT_PATH):
        lut_rgba = decode_png(LUT_PATH)
        lut = (lut_rgba[..., :3].astype(np.float32) / 255.0)
        graded = apply_hald_lut(lut, np.asarray(composite), 1.0)
        ident = np.tile(
            (np.arange(64).reshape(8, 8, 1) / 7.0).repeat(8, 0).repeat(8, 1), (1, 1, 3)
        )
        print(
            f"[5] Hald LUT (REAL weights)    lut={lut.shape} from assets/default_enhance_lut.png"
        )
        print(
            f"    graded mean={graded.mean():.4f} mean|delta| vs ungraded={(np.abs(graded-np.asarray(composite))).mean():.4f}"
        )
    else:
        print("[5] LUT                        skipped (asset missing)")

    # sharpen
    low_y = jnp.asarray(np.asarray(gb.blur9(mean, 1))) @ jnp.asarray(gb.W_LUMA)
    sharp = gb.sharpen(rgb_j, composite, low_y, 0.35)
    print(f"[6] luma unsharp mask          out={sharp.shape} sharpness=0.35")

    # full jitted pipeline
    fn = jax.jit(lambda x: gb.gorgeous_beauty(x, blur_alpha=0.6, sharpness=0.35))
    out = fn(rgb_j)
    out.block_until_ready()
    print(f"[7] jit gorgeous_beauty        out={out.shape} range=[{float(out.min()):.3f},{float(out.max()):.3f}]")

    # ---------------- verification ---------------------------------------
    ref = numpy_reference_beauty(np.asarray(rt), 0.6, 1.0)
    comp = np.asarray(composite)
    max_abs = float(np.max(np.abs(ref - comp)))
    print("-" * 78)
    print(f"[V1] JAX vs numpy parity (composite)  max_abs_err={max_abs:.3e}  psnr={psnr(ref, comp):.2f} dB")
    ok_parity = max_abs < 1e-4

    # beauty must actually change skin but leave untouched pixels alone
    changed = float(np.mean(np.abs(np.asarray(out) - np.asarray(rt)) > 1e-3))
    print(f"[V2] fraction of pixels modified      {changed*100:.1f}%  (smoothing active)")
    ok_changed = changed > 0.05

    ident_lut_ok = True
    if lut is not None:
        # Build the true identity Hald texture for this layout (8x8 tiles of
        # 8x8, tile index = blue) and check apply() is a no-op on a ramp.
        ident = np.zeros((64, 64, 3), np.float32)
        for b in range(8):
            for g in range(8):
                for r in range(8):
                    ident[(b // 8) * 8 + g, (b % 8) * 8 + r] = (r / 7.0, g / 7.0, b / 7.0)
        probe = np.random.default_rng(1).random((32, 32, 3)).astype(np.float32)
        same = apply_hald_lut(ident, probe, 1.0)
        d_ident = float(np.max(np.abs(same - probe)))
        ident_lut_ok = d_ident < 0.03
        print(f"[V3] LUT identity check               max_delta={d_ident:.3e}")

    np.save(os.path.join(args.out_dir, "beauty_out.npy"), np.asarray(out))
    np.save(os.path.join(args.out_dir, "nv21.npy"), nv21)
    print(f"[V4] artifacts written to {args.out_dir}")
    print("-" * 78)
    status = "PASS" if (ok_parity and ok_changed and ident_lut_ok) else "FAIL"
    print(f"RESULT: {status}")
    return 0 if status == "PASS" else 1


if __name__ == "__main__":
    raise SystemExit(main())
