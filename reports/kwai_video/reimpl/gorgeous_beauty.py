"""Gorgeous (libgorgeous.so) beauty pipeline, transcribed to JAX.

Every constant here is copied from GLSL that ships as plain rodata in
`lib/arm64-v8a/libgorgeous.so` (Kuaishou "Gorgeous" beauty SDK, used by
libFaceMagic for the BEAUTY_FILTER / beautify stage).  The pipeline is:

    YUV (camera output)
      -> [1] 5-tap mean + high-frequency "variance" pass        (pass-1 frag)
      -> [2] 3x3 blur of (mean, variance)                        (pass-2 frag)
      -> [3] skin-smoothing composite with kMin formula          (beautify frag)
      -> [4] Hald 3D-LUT grade                                   (lookup())
      -> [5] luma unsharp-mask sharpen                           (sharpen())
      -> [6] alpha blend back over the input                     (beautify frag)

Recovered source strings (offsets in libgorgeous.so):

  pass-1  @0x12f75d  "vec3 sum = texture2D(...); ... gl_FragColor =
                      vec4(meanColor, (diffColor.r+diffColor.g+diffColor.b)*0.33);"
  pass-2  @0x12fc3d  "sum += ... (8 taps) ... gl_FragColor = sum * 0.1111;"
  beautify@0x1312c8 / 0x1319be  kMin = (1 - meanVar/(meanVar+theta)) * p *
                      blurAlpha [* skinMask];  theta = 0.1;
                      p = clamp((min(in.r, blur.a) - 0.2) * 4.0, 0, 1)
  lookup  @0x12fef8 (inlined) / shader 16
  sharpen @0x~ "vec3 sharpen(vec3 ori, vec3 base, float sharpness) {...}"

`%` tags such as $SHARPEN_FUNC / $LOOKUP_FUNC are spliced in by
gorgeous::gsCreateBeautifyEffect; the spliced bodies are the strings
quoted above (they exist as separate rodata blobs in the same library).
"""

from __future__ import annotations

import jax
import jax.numpy as jnp
import numpy as np

# --------------------------------------------------------------------------
# colour transforms - constants copied from the embedded GLSL
# --------------------------------------------------------------------------

THETA = 0.1  # `const float theta = 0.1;`
W_LUMA = (0.2125, 0.7154, 0.0721)  # `const vec3 W`

# `if (colorSpace == 0) mat2Y = vec3(0.257, 0.504, 0.098) + 16/255;` etc.
LUMA_COEFFS = {
    "bt601_limited": (0.257, 0.504, 0.098, 16.0 / 255.0),
    "bt2020_limited": (0.183, 0.614, 0.062, 16.0 / 255.0),
    "bt709_full": (0.213, 0.715, 0.072, 0.0),
    "bt601_full": (0.2983549, 0.58747799, 0.11416711, 0.0),
}

# The host supplies yuv2rgbMatrix / rgb2yuvMatrix.  For colorSpace==0 the
# shader's own Y reconstruction implies the standard BT.601 limited-range
# matrix, which is what libyuv uses for NV21 -> ARGB everywhere else in
# this APK (`NV21ToARGB` / `I420ToARGB` symbols in libykit.so).
BT601_LIMITED_YUV2RGB = np.array(
    [
        [1.164383, 0.000000, 1.596027],
        [1.164383, -0.391762, -0.812968],
        [1.164383, 2.017232, 0.000000],
    ],
    dtype=np.float32,
)
BT601_LIMITED_RGB2YUV = np.array(
    [
        [0.256788, 0.504129, 0.097906],
        [-0.148223, -0.290993, 0.439216],
        [0.439216, -0.367788, -0.071427],
    ],
    dtype=np.float32,
)


def yuv_to_rgb(yuv, matrix=BT601_LIMITED_YUV2RGB, limited=True):
    """Transcription of the GLSL `yuv2rgb()` helper (incl. the ceil/round)."""
    y, u, v = yuv[..., 0], yuv[..., 1] - 0.5, yuv[..., 2] - 0.5
    if limited:
        y = y - 16.0 / 255.0
    m = jnp.asarray(matrix)
    rgb = jnp.stack(
        [
            m[0, 0] * y + m[0, 1] * u + m[0, 2] * v,
            m[1, 0] * y + m[1, 1] * u + m[1, 2] * v,
            m[2, 0] * y + m[2, 1] * u + m[2, 2] * v,
        ],
        axis=-1,
    )
    rgb = jnp.ceil(rgb * 255.0 + 0.5) / 255.0
    return jnp.clip(rgb, 0.0, 1.0)


def rgb_to_yuv(rgb, matrix=BT601_LIMITED_RGB2YUV, limited=True):
    """Transcription of the GLSL `rgb2yuv()` helper."""
    m = jnp.asarray(matrix)
    y = m[0, 0] * rgb[..., 0] + m[0, 1] * rgb[..., 1] + m[0, 2] * rgb[..., 2]
    u = m[1, 0] * rgb[..., 0] + m[1, 1] * rgb[..., 1] + m[1, 2] * rgb[..., 2]
    v = m[2, 0] * rgb[..., 0] + m[2, 1] * rgb[..., 1] + m[2, 2] * rgb[..., 2]
    if limited:
        y = y + 16.0 / 255.0
    return jnp.clip(jnp.stack([y, u + 0.5, v + 0.5], axis=-1), 0.0, 1.0)


# --------------------------------------------------------------------------
# sampling helpers (CLK_ADDRESS_CLAMP)
# --------------------------------------------------------------------------


def shift(img, dx, dy):
    """Integer tap with edge clamp, matching CLK_ADDRESS_CLAMP."""
    h, w = img.shape[0], img.shape[1]
    pads = ((abs(dy), abs(dy)), (abs(dx), abs(dx))) + ((0, 0),) * (img.ndim - 2)
    p = jnp.pad(img, pads, mode="edge")
    y0 = abs(dy) + dy
    x0 = abs(dx) + dx
    return jax.lax.dynamic_slice(p, (y0, x0) + (0,) * (img.ndim - 2), img.shape)


# --------------------------------------------------------------------------
# stage 1 - mean / variance pass  (libgorgeous pass-1 fragment shader)
# --------------------------------------------------------------------------


def mean_variance_pass(img, tex_offset=1.0):
    """5-tap mean + high-frequency energy, exactly as pass-1 GLSL.

    The vertex shader builds 4 diagonal taps at +/- texOffset (pixels):
        texShifts[0] = tc-t, tc+t
        texShifts[1] = tc-(tx,-ty), tc+(tx,-ty)
    and the fragment shader does:

        sum = center + 4 taps
        meanColor = sum * 0.2
        diffColor = min(((center - mean) * 7.0)^2, 1.0)
        alpha     = (diff.r + diff.g + diff.b) * 0.33

    Returns (mean_rgb, variance) where variance is the alpha channel.
    """
    ox = int(round(tex_offset))
    taps = [
        (ox, ox),
        (-ox, -ox),
        (ox, -ox),
        (-ox, ox),
    ]
    acc = img
    for dx, dy in taps:
        acc = acc + shift(img, dx, dy)
    mean = acc * 0.2
    diff = jnp.minimum(((img - mean) * 7.0) ** 2, 1.0)
    var = (diff[..., 0] + diff[..., 1] + diff[..., 2]) * 0.33
    return mean, var


def blur9(x, radius=1):
    """3x3 box blur, matching pass-2: 8 taps + center, * 0.1111."""
    acc = x
    for dy in (-radius, 0, radius):
        for dx in (-radius, 0, radius):
            if dx == 0 and dy == 0:
                continue
            acc = acc + shift(x, dx, dy)
    return acc * 0.1111


# --------------------------------------------------------------------------
# stage 3 - skin smoothing composite
# --------------------------------------------------------------------------


def smooth_composite(inp_rgb, mean_rgb, blurred_var, blur_alpha, skin_mask=None):
    """kMin skin-smoothing blend (beautify fragment shader)."""
    p = jnp.clip((jnp.minimum(inp_rgb[..., 0], blurred_var) - 0.2) * 4.0, 0.0, 1.0)
    k = (1.0 - blurred_var / (blurred_var + THETA)) * p * blur_alpha
    if skin_mask is not None:
        k = k * skin_mask
    k = k[..., None]
    return inp_rgb + (mean_rgb - inp_rgb) * k


# --------------------------------------------------------------------------
# stage 5 - sharpen (verbatim transcription of the splinched GLSL function)
# --------------------------------------------------------------------------


def sharpen(ori, base, low_y, sharpness, color_space="bt601_limited"):
    """`vec3 sharpen(vec3 ori, vec3 base, float sharpness)`.

    ori  : original RGB (the un-blurred input)
    base : smoothed RGB that will be modified
    low_y: blurred luma texture (conv of ori, GPU-side box/gauss blur)
    """
    a, b, c, offset = LUMA_COEFFS[color_space]
    yuv_base = rgb_to_yuv(base, limited=offset > 0)
    ori_y = a * ori[..., 0] + b * ori[..., 1] + c * ori[..., 2] + offset
    high = (ori_y - low_y) * (1.0 - base[..., 0] ** 2) * sharpness
    yuv_base = yuv_base.at[..., 0].set(jnp.clip(yuv_base[..., 0] + high, 0.0, 1.0))
    return yuv_to_rgb(yuv_base, limited=offset > 0)


# --------------------------------------------------------------------------
# full pipeline
# --------------------------------------------------------------------------


def gorgeous_beauty(
    rgb,
    *,
    blur_alpha: float = 0.6,
    lookup_alpha: float = 0.0,
    sharpness: float = 0.0,
    tex_offset: float = 1.0,
    skin_mask=None,
    lut=None,
):
    """Run the recovered beauty chain on an (H, W, 3) float32 RGB image.

    lookup_alpha > 0 requires `lut` = (S, S, 3) Hald texture (see pnglut.py);
    because the shader samples it per-pixel it is applied outside jit.
    """
    mean, var = mean_variance_pass(rgb, tex_offset=tex_offset)
    var_b = blur9(var, radius=1)
    mean_s = jnp.stack([blur9(mean[..., i], 1) for i in range(3)], axis=-1)
    out = smooth_composite(rgb, mean_s, jnp.clip(var_b, 0.0, 1.0), blur_alpha, skin_mask)
    if sharpness != 0.0:
        low_y = mean_s @ jnp.asarray(W_LUMA, dtype=mean_s.dtype)
        out = sharpen(rgb, out, low_y, sharpness)
    if lut is not None and lookup_alpha > 0.0:
        from pnglut import apply_hald_lut

        out = jnp.asarray(
            apply_hald_lut(np.asarray(lut), np.asarray(out), float(lookup_alpha))
        )
    return out
