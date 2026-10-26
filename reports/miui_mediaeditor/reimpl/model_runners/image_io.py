#!/usr/bin/env python3
"""Minimal dependency-free image loading / resizing for the model runners.

``$PY`` has no Pillow/OpenCV, so the formats that matter for synthetic and
recorded checks are decoded directly:

* PNG  (8-bit, colour types 0/2/4/6, non-interlaced, zlib)
* PNM  (P5/P6 binary PGM/PPM)
* BMP  (uncompressed 24/32-bit)
* NPY  (``(H,W)`` or ``(H,W,3)``, uint8 or float)

Anything else (JPEG/WebP/...) is decoded with Pillow when it happens to be
importable and rejected with a clear message otherwise.

All loaders return ``uint8`` arrays shaped ``(H, W, 3)`` for colour sources
and ``(H, W)`` for grayscale sources.
"""

from __future__ import annotations

import struct
import zlib

import numpy as np


class ImageError(Exception):
    pass


# ---------------------------------------------------------------------------
# PNG
# ---------------------------------------------------------------------------
def _png_paeth(a: int, b: int, c: int) -> int:
    p = a + b - c
    pa, pb, pc = abs(p - a), abs(p - b), abs(p - c)
    if pa <= pb and pa <= pc:
        return a
    if pb <= pc:
        return b
    return c


def _decode_png(data: bytes) -> np.ndarray:
    if not data.startswith(b"\x89PNG\r\n\x1a\n"):
        raise ImageError("not a PNG file")
    pos = 8
    idat = bytearray()
    width = height = bitdepth = colour = interlace = None
    palette = None
    trns = None
    while pos + 8 <= len(data):
        length, ctype = struct.unpack(">I4s", data[pos:pos + 8])
        pos += 8
        chunk = data[pos:pos + length]
        pos += length + 4  # skip CRC
        if ctype == b"IHDR":
            width, height, bitdepth, colour, comp, filt, interlace = struct.unpack(
                ">IIBBBBB", chunk
            )
            if bitdepth != 8:
                raise ImageError(f"PNG bit depth {bitdepth} unsupported (need 8)")
            if comp != 0 or filt != 0:
                raise ImageError("PNG with non-standard compression/filter")
            if interlace != 0:
                raise ImageError("interlaced PNG unsupported")
        elif ctype == b"PLTE":
            palette = np.frombuffer(chunk, dtype=np.uint8).reshape(-1, 3)
        elif ctype == b"tRNS":
            trns = np.frombuffer(chunk, dtype=np.uint8)
        elif ctype == b"IDAT":
            idat += chunk
        elif ctype == b"IEND":
            break
    if width is None:
        raise ImageError("PNG without IHDR")
    channels = {0: 1, 2: 3, 3: 1, 4: 2, 6: 4}.get(colour)
    if channels is None:
        raise ImageError(f"PNG colour type {colour} unsupported")
    raw = zlib.decompress(bytes(idat))
    stride = width * channels
    out = np.zeros((height, stride), dtype=np.uint8)
    prev = np.zeros(stride, dtype=np.uint8)
    p = 0
    for y in range(height):
        f = raw[p]
        p += 1
        line = np.frombuffer(raw, dtype=np.uint8, count=stride, offset=p).astype(np.int32)
        p += stride
        if f == 0:
            cur = line
        elif f == 1:  # Sub
            cur = line.copy()
            for i in range(channels, stride):
                cur[i] = (cur[i] + cur[i - channels]) & 0xFF
        elif f == 2:  # Up
            cur = (line + prev) & 0xFF
        elif f == 3:  # Average
            cur = line.copy()
            for i in range(stride):
                left = cur[i - channels] if i >= channels else 0
                cur[i] = (cur[i] + ((int(left) + int(prev[i])) >> 1)) & 0xFF
        elif f == 4:  # Paeth
            cur = line.copy()
            for i in range(stride):
                a = int(cur[i - channels]) if i >= channels else 0
                b = int(prev[i])
                c = int(prev[i - channels]) if i >= channels else 0
                cur[i] = (cur[i] + _png_paeth(a, b, c)) & 0xFF
        else:
            raise ImageError(f"bad PNG filter {f}")
        out[y] = cur.astype(np.uint8)
        prev = out[y]
    img = out.reshape(height, width, channels)
    if colour == 3:
        if palette is None:
            raise ImageError("palette PNG without PLTE")
        img = palette[img[..., 0]]
    elif colour == 0:
        img = img[..., 0]
    elif colour == 4:
        img = img[..., 0]
    elif colour == 6:
        img = img[..., :3]
    return img


# ---------------------------------------------------------------------------
# PNM / BMP / NPY
# ---------------------------------------------------------------------------
def _decode_pnm(data: bytes) -> np.ndarray:
    if data[:2] not in (b"P5", b"P6"):
        raise ImageError("only binary PGM/PPM (P5/P6) is supported")
    magic = data[:2]
    rest = data[2:]
    # header: width height maxval, whitespace/comments between
    tokens = []
    i = 0
    while len(tokens) < 3:
        while i < len(rest) and rest[i : i + 1].isspace():
            i += 1
        if i < len(rest) and rest[i : i + 1] == b"#":
            while i < len(rest) and rest[i : i + 1] not in (b"\n", b"\r"):
                i += 1
            continue
        j = i
        while j < len(rest) and not rest[j : j + 1].isspace():
            j += 1
        tokens.append(int(rest[i:j]))
        i = j
    i += 1  # single whitespace after maxval
    w, h, maxval = tokens
    if maxval > 255:
        raise ImageError("16-bit PNM unsupported")
    channels = 1 if magic == b"P5" else 3
    arr = np.frombuffer(rest, dtype=np.uint8, count=w * h * channels, offset=i)
    arr = arr.reshape(h, w, channels)
    return arr[..., 0] if channels == 1 else arr


def _decode_bmp(data: bytes) -> np.ndarray:
    if data[:2] != b"BM":
        raise ImageError("not a BMP file")
    pix_off = struct.unpack("<I", data[10:14])[0]
    hdr = struct.unpack("<IiiHH", data[14:34])
    width, height = hdr[1], hdr[2]
    bpp = struct.unpack("<H", data[28:30])[0]
    if bpp not in (24, 32):
        raise ImageError(f"BMP {bpp} bpp unsupported (need 24/32)")
    flip = height > 0
    h, w = abs(height), width
    ch = bpp // 8
    row = (w * ch + 3) & ~3
    buf = np.frombuffer(data, dtype=np.uint8, count=row * h, offset=pix_off)
    buf = buf.reshape(h, row)[:, : w * ch].reshape(h, w, ch)[..., :3]
    img = buf[..., ::-1]  # BGR -> RGB
    return img[::-1] if flip else img


def load_image(path: str) -> np.ndarray:
    """Load ``path`` and return uint8 ``(H,W,3)`` or ``(H,W)``."""
    with open(path, "rb") as f:
        data = f.read()
    lower = path.lower()
    if data.startswith(b"\x89PNG\r\n\x1a\n"):
        img = _decode_png(data)
    elif data[:2] in (b"P5", b"P6"):
        img = _decode_pnm(data)
    elif data[:2] == b"BM":
        img = _decode_bmp(data)
    elif lower.endswith(".npy") or data[:6] == b"\x93NUMPY":
        arr = np.load(path, allow_pickle=False)
        img = arr
    else:
        try:
            from PIL import Image  # type: ignore
        except Exception as exc:  # pragma: no cover - Pillow is absent in $PY
            raise ImageError(
                f"cannot decode {path}: unsupported format and Pillow is not "
                f"available ({exc})"
            ) from exc
        img = np.asarray(Image.open(path).convert("RGB"))
    img = np.asarray(img)
    if img.ndim == 3 and img.shape[2] == 4:
        img = img[..., :3]
    if img.ndim == 3 and img.shape[2] == 1:
        img = img[..., 0]
    if img.ndim not in (2, 3):
        raise ImageError(f"{path}: unsupported array shape {img.shape}")
    if img.dtype != np.uint8:
        img = np.clip(img, 0, 255).astype(np.uint8) if img.dtype.kind == "f" else img.astype(np.uint8)
    return img


# ---------------------------------------------------------------------------
# Resize (bilinear, half-pixel centres)
# ---------------------------------------------------------------------------
def resize_bilinear(img: np.ndarray, height: int, width: int) -> np.ndarray:
    """Bilinear resize of a ``(H,W)`` or ``(H,W,C)`` array (float or uint8)."""
    src_h, src_w = img.shape[:2]
    if (src_h, src_w) == (height, width):
        return img.copy()
    work = img.astype(np.float32)
    ys = (np.arange(height, dtype=np.float32) + 0.5) * (src_h / height) - 0.5
    xs = (np.arange(width, dtype=np.float32) + 0.5) * (src_w / width) - 0.5
    ys = np.clip(ys, 0, src_h - 1)
    xs = np.clip(xs, 0, src_w - 1)
    y0 = np.floor(ys).astype(np.int32)
    x0 = np.floor(xs).astype(np.int32)
    y1 = np.minimum(y0 + 1, src_h - 1)
    x1 = np.minimum(x0 + 1, src_w - 1)
    wy = (ys - y0)[:, None]
    wx = (xs - x0)[None, :]
    if work.ndim == 3:
        wy = wy[..., None]
        wx = wx[..., None]
    top = work[y0][:, x0] * (1 - wx) + work[y0][:, x1] * wx
    bot = work[y1][:, x0] * (1 - wx) + work[y1][:, x1] * wx
    out = top * (1 - wy) + bot * wy
    if img.dtype == np.uint8:
        return np.clip(np.rint(out), 0, 255).astype(np.uint8)
    return out.astype(np.float32)
