"""Hald-CLUT decoding and application for the Kwai/Gorgeous beauty LUT stage.

Two things are recovered from the binary, both `confirmed`:

1. The LUT sampling routine is embedded verbatim as GLSL in
   libgorgeous.so (rodata string "vec3 lookup(vec3 color) { ... }",
   referenced from the *beautify* fragment shader).  Quoting it:

       vec3 lookup(vec3 color) {
         float blueColor = color.b * 63.0;
         vec2 quad1; quad1.y = floor(floor(blueColor) / 8.0);
         quad1.x = floor(blueColor) - (quad1.y * 8.0);
         vec2 quad2; quad2.y = floor(ceil(blueColor) / 8.0);
         quad2.x = ceil(blueColor) - (quad2.y * 8.0);
         vec2 texPos1 = (quad1 * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * color.rg);
         vec2 texPos2 = (quad2 * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * color.rg);
         vec3 newColor1 = texture2D(lookUp, texPos1).rgb;
         vec3 newColor2 = texture2D(lookUp, texPos2).rgb;
         vec3 newColor = mix(newColor1, newColor2, fract(blueColor));
         return newColor;
       }

   i.e. an 8x8 tile grid (64 blue levels) over a S x S texture with
   S = 512.  The same formula with S = 64 describes the *shipped*
   asset `assets/default_enhance_lut.png` (64 x 64, RGBA8), which then
   holds 8 blue levels over an 8x8 (r,g) grid == a 8x8x8 LUT.

2. `assets/default_enhance_lut.png` is a real, non-identity LUT shipped
   inside the APK; it is the only beauty/makeup weight data present in
   the package.  It is decoded here into `weights/default_enhance_lut.npy`
   and used as the real weights of the LUT stage.
"""

from __future__ import annotations

import struct
import zlib

import numpy as np

# --------------------------------------------------------------------------
# minimal PNG decoder (8-bit, colour type 6 / 2, non-interlaced)
# --------------------------------------------------------------------------


def _paeth(a, b, c):
    p = a.astype(np.int16) + b.astype(np.int16) - c.astype(np.int16)
    pa = np.abs(p - a)
    pb = np.abs(p - b)
    pc = np.abs(p - c)
    out = np.where((pa <= pb) & (pa <= pc), a, np.where(pb <= pc, b, c))
    return out.astype(np.uint8)


def decode_png(path: str) -> np.ndarray:
    """Decode a non-interlaced 8-bit PNG to HxWxC uint8 (C = 3 or 4)."""
    blob = open(path, "rb").read()
    assert blob[:8] == b"\x89PNG\r\n\x1a\n", "not a PNG"
    pos = 8
    width = height = depth = ctype = None
    idat = bytearray()
    while pos < len(blob):
        (length,) = struct.unpack(">I", blob[pos : pos + 4])
        ctag = blob[pos + 4 : pos + 8]
        data = blob[pos + 8 : pos + 8 + length]
        pos += 12 + length
        if ctag == b"IHDR":
            width, height, depth, ctype, comp, filt, inter = struct.unpack(
                ">IIBBBBB", data
            )
            assert depth == 8 and inter == 0 and comp == 0 and filt == 0
            assert ctype in (2, 6), f"unsupported colour type {ctype}"
        elif ctag == b"IDAT":
            idat += data
        elif ctag == b"IEND":
            break
    raw = zlib.decompress(bytes(idat))
    nch = 3 if ctype == 2 else 4
    stride = width * nch
    out = np.zeros((height, stride), dtype=np.uint8)
    prev = np.zeros(stride, dtype=np.uint8)
    off = 0
    for y in range(height):
        ftype = raw[off]
        off += 1
        line = np.frombuffer(raw[off : off + stride], dtype=np.uint8).copy()
        off += stride
        if ftype == 0:
            cur = line
        elif ftype == 1:  # Sub
            cur = line
            for i in range(nch, stride):
                cur[i] = (int(cur[i]) + int(cur[i - nch])) & 0xFF
        elif ftype == 2:  # Up
            cur = (line.astype(np.int16) + prev.astype(np.int16)).astype(np.uint8)
        elif ftype == 3:  # Average
            cur = line
            for i in range(stride):
                left = int(cur[i - nch]) if i >= nch else 0
                cur[i] = (int(cur[i]) + ((left + int(prev[i])) >> 1)) & 0xFF
        elif ftype == 4:  # Paeth
            cur = line
            for i in range(stride):
                left = int(cur[i - nch]) if i >= nch else 0
                up = int(prev[i])
                ul = int(prev[i - nch]) if i >= nch else 0
                p = left + up - ul
                pa, pb, pc = abs(p - left), abs(p - up), abs(p - ul)
                pred = left if (pa <= pb and pa <= pc) else (up if pb <= pc else ul)
                cur[i] = (int(cur[i]) + pred) & 0xFF
        else:
            raise ValueError(f"bad filter {ftype}")
        out[y] = cur
        prev = out[y]
    return out.reshape(height, width, nch)


# --------------------------------------------------------------------------
# Hald CLUT application (exact transcription of the embedded GLSL)
# --------------------------------------------------------------------------


def apply_hald_lut(lut: np.ndarray, img: np.ndarray, strength: float = 1.0):
    """Apply an 8x8-tile Hald LUT exactly like Gorgeous' `lookup()` shader.

    lut : (S, S, 3or4) float32 in [0,1]; S must be a multiple of 8.
    img : (H, W, 3) float32 in [0,1].
    """
    s = lut.shape[0]
    n_blue = s // 8  # 512 -> 64, 64 -> 8
    lut3 = lut[..., :3]

    def sample(quad_x, quad_y, r, g):
        # texPos = quad*(1/8) + 0.5/S + ((1/8) - 1/S) * rg   [normalised]
        px = (quad_x * (1.0 / 8.0) + 0.5 / s + ((1.0 / 8.0) - 1.0 / s) * r) * s
        py = (quad_y * (1.0 / 8.0) + 0.5 / s + ((1.0 / 8.0) - 1.0 / s) * g) * s
        x0 = np.floor(px - 0.5).astype(np.int32)
        y0 = np.floor(py - 0.5).astype(np.int32)
        x1 = x0 + 1
        y1 = y0 + 1
        fx = (px - 0.5) - x0
        fy = (py - 0.5) - y0
        x0 = np.clip(x0, 0, s - 1)
        x1 = np.clip(x1, 0, s - 1)
        y0 = np.clip(y0, 0, s - 1)
        y1 = np.clip(y1, 0, s - 1)

        def tap(xx, yy):
            return lut3[yy, xx]

        c00 = tap(x0, y0)
        c10 = tap(x1, y0)
        c01 = tap(x0, y1)
        c11 = tap(x1, y1)
        fx = fx[..., None]
        fy = fy[..., None]
        return (c00 * (1 - fx) + c10 * fx) * (1 - fy) + (
            c01 * (1 - fx) + c11 * fx
        ) * fy

    r, g, b = img[..., 0], img[..., 1], img[..., 2]
    blue = b * (n_blue - 1)
    b0 = np.floor(blue)
    b1 = np.ceil(blue)

    q1y = np.floor(b0 / 8.0)
    q1x = b0 - q1y * 8.0
    q2y = np.floor(b1 / 8.0)
    q2x = b1 - q2y * 8.0

    c1 = sample(q1x, q1y, r, g)
    c2 = sample(q2x, q2y, r, g)
    frac = (blue - b0)[..., None]
    mapped = c1 * (1 - frac) + c2 * frac
    return img + (mapped - img) * strength
