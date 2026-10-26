"""FaceMagic G3 beautify stage, reimplemented in JAX.

Source of truth: the GLSL programs embedded in
``libFaceMagic.so`` (sha256 6ef763ec...), extracted verbatim to
``../evidence/shaders/``:

  * ``facemagic_61815c.glsl`` - 9-tap alpha (mean-alpha) pass
  * ``facemagic_617dba.glsl`` - 9-tap mean colour + ``varY`` (soften weight) pass
  * ``facemagic_61b3c0.glsl`` - the G3 blend program (teeth LUT, clarity,
                                 brightness curve, skin-smooth blend, sharpen)
  * ``facemagic_61cfd4.glsl`` - the G2 blend program (subset, no adjust masks)
  * ``facemagic_5b89c0.glsl`` - 8x8x8 (512x512) 2D LUT sampling used by
                                 ``lookup(...)``

The GLSL uniforms map 1:1 onto the Java/Kotlin faces
(``FMGorgeousBeautify`` -> ``FMBeautifyG3Effect::setSoften/setBright/
setSharpness``), and the intensities come from ``assets/beauty_list``
(copied to ``data/beauty_list``) - a JSON preset list with
``smoothSkin.beauty`` / ``smoothSkin.bright`` in 0..100 units.

Everything here is deterministic, weight-free image processing; the only
learned component of the real pipeline is the *skin mask* and the
*soften/adjust masks*, which come from YCNN segmentation models that are
not shipped in the APK (see report.md).
"""

from __future__ import annotations

import json
import os

import jax
import jax.numpy as jnp
import numpy as np

LUMA = jnp.array([0.2983549, 0.58747799, 0.11416711], dtype=jnp.float32)

DATA_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "data")


# --------------------------------------------------------------------------
# 3x3 neighbourhood helpers (GL textureShift[0..3] = the 8 neighbours of a
# 3x3 box; clamp-to-edge sampling as in the GL FBO setup).
# --------------------------------------------------------------------------
def _shift_offsets():
    """The 8 neighbour offsets used by textureShift[0..3].xy/.zw."""
    return [(dy, dx) for dy in (-1, 0, 1) for dx in (-1, 0, 1) if (dy, dx) != (0, 0)]


_OFFS = _shift_offsets()

# The GLSL programs address precomputed varyings textureShift[0..5].xy/.zw,
# i.e. six vec4s = 12 scalar offsets, populated by the vertex shader.  The
# vertex shader itself is not embedded as text and the exact per-index
# assignment is not recovered; the centre + first 8 neighbours are used
# here (documented as inferred in report.md).
TAPS_A = [(-1, 0), (-1, 1), (0, 0), (0, 1)]   # 4 taps used by "brighten"
TAPS_B = [(1, 0), (1, 1), (1, -1), (-1, -1)]  # 4 taps used by "clarity"
TAPS_C = [(-1, 0), (-1, -1), (0, 0), (0, -1)] # 4 taps used by the sharpen branch


def _shift(x: jnp.ndarray, dy: int, dx: int) -> jnp.ndarray:
    """Edge-clamped integer shift of an HxWxC array."""
    h, w = x.shape[0], x.shape[1]
    ys = jnp.clip(jnp.arange(h) + dy, 0, h - 1)
    xs = jnp.clip(jnp.arange(w) + dx, 0, w - 1)
    return x[jnp.ix_(ys, xs)]


def box3x3_sum(x: jnp.ndarray) -> jnp.ndarray:
    """Sum of the 9 taps (centre + 8 neighbours), edge clamped."""
    acc = x
    for dy, dx in _OFFS:
        acc = acc + _shift(x, dy, dx)
    return acc


def alpha_pass(rgba: jnp.ndarray) -> jnp.ndarray:
    """``facemagic_61815c.glsl``: vec4(rgb, mean(alpha) * 9 * (1/9))."""
    mean_a = box3x3_sum(rgba[..., 3:4]) * (1.0 / 9.0)
    return jnp.concatenate([rgba[..., :3], mean_a], axis=-1)


def blur_var_pass(rgba: jnp.ndarray) -> jnp.ndarray:
    """``facemagic_617dba.glsl``: (meanColour, min((alpha-meanY)^2*50, 1)).

    ``sum * 0.1111`` (not 1/9) is used verbatim, matching the shader.
    """
    mean_rgb = box3x3_sum(rgba[..., :3]) * 0.1111
    mean_y = jnp.sum(mean_rgb * LUMA, axis=-1, keepdims=True)
    diff = rgba[..., 3:4] - mean_y
    var_y = jnp.minimum(diff * diff * 50.0, 1.0)
    return jnp.concatenate([mean_rgb, var_y], axis=-1)


def blur_image(rgba: jnp.ndarray) -> jnp.ndarray:
    """Full blur branch: alpha pass then mean/variance pass.

    Returns RGBA where rgb is the 3x3 mean colour and a is ``varY``
    (exactly what ``inputBlurTex`` holds for the blend program).
    """
    return blur_var_pass(alpha_pass(rgba))


# --------------------------------------------------------------------------
# LUT sampling (``lookup`` / ``lookup_teeth``)
# --------------------------------------------------------------------------
def _lut_sample(lut: jnp.ndarray, rgb: jnp.ndarray, dim: int) -> jnp.ndarray:
    """Tile-of-tiles LUT lookup; ``lut`` is (dim*dim, dim) for a dim^3 cube.

    Mirrors ``facemagic_5b89c0.glsl`` (blue selects the tile, bilinear in
    rg, no interpolation across the blue axis).
    """
    d = int(dim)
    span = float(d - 1)
    blue = jnp.clip(rgb[..., 2], 0.0, 1.0) * span
    b0 = jnp.floor(blue)
    b1 = jnp.minimum(b0 + 1.0, span)
    frac = blue - b0
    r = jnp.clip(rgb[..., 0], 0.0, 1.0) * span
    g = jnp.clip(rgb[..., 1], 0.0, 1.0) * span
    r0 = jnp.floor(r)
    r1 = jnp.minimum(r0 + 1.0, span)
    g0 = jnp.floor(g)
    g1 = jnp.minimum(g0 + 1.0, span)
    fr, fg = r - r0, g - g0

    def tex(tile, x, y):
        ti = jnp.clip(tile, 0, d - 1).astype(jnp.int32)
        xi = jnp.clip(x, 0, d - 1).astype(jnp.int32)
        yi = jnp.clip(y, 0, d - 1).astype(jnp.int32)
        ty = ti // d
        tx = ti - ty * d
        row = ty * d + yi   # pixel row inside the dim*dim tile image
        col = tx * d + xi   # pixel column
        return lut[row, col]

    c000 = tex(b0, r0, g0)
    c100 = tex(b1, r0, g0)
    c010 = tex(b0, r1, g0)
    c110 = tex(b1, r1, g0)
    c001 = tex(b0, r0, g1)
    c101 = tex(b1, r0, g1)
    c011 = tex(b0, r1, g1)
    c111 = tex(b1, r1, g1)
    lo = c000 * (1 - fr)[..., None] + c010 * fr[..., None]
    hi = c001 * (1 - fr)[..., None] + c011 * fr[..., None]
    lo2 = c100 * (1 - fr)[..., None] + c110 * fr[..., None]
    hi2 = c101 * (1 - fr)[..., None] + c111 * fr[..., None]
    lo = lo * (1 - fg)[..., None] + hi * fg[..., None]
    hi = lo2 * (1 - fg)[..., None] + hi2 * fg[..., None]
    return lo * (1 - frac)[..., None] + hi * frac[..., None]


def identity_lut(dim: int = 8) -> jnp.ndarray:
    """Identity colour cube as a ``dim^2 x dim^2`` tile image (dim^3 cube).

    Pixel (py, px) of the (dim*dim) square holds the cube entry
    ``(px % dim, py % dim, tile)`` - the layout ``_lut_sample`` indexes.
    """
    n = dim * dim
    out = np.zeros((n, n, 3), dtype=np.float32)
    for py in range(n):
        for px in range(n):
            ty, tx = py // dim, px // dim
            tile = ty * dim + tx
            out[py, px] = (px % dim / (dim - 1.0), py % dim / (dim - 1.0), tile / (n - 1.0))
    return jnp.asarray(out)


# --------------------------------------------------------------------------
# Blend program
# --------------------------------------------------------------------------
def mix(a, b, t):
    return a + (b - a) * t


def beautify_g3(
    ori: jnp.ndarray,
    blur: jnp.ndarray,
    ds: jnp.ndarray,
    skin_mask: jnp.ndarray,
    soften_mask: jnp.ndarray,
    adjust_mask: jnp.ndarray,
    blur_alpha: float,
    bright_alpha: float,
    sharpness: float,
    bright_lut: jnp.ndarray | None = None,
    teeth_lut: jnp.ndarray | None = None,
    lut_dim: int = 8,
) -> jnp.ndarray:
    """``facemagic_61b3c0.glsl`` in float32.

    All inputs are HxWx{3,4} float32 in [0, 1]; ``skin_mask`` (HxWx1),
    ``soften_mask`` (HxWx1) and ``adjust_mask`` (HxWx3, r=clarity,
    g=sharpen-lite, b=bright/teeth) are what the segmentation models feed
    the effect. ``blur`` is ``blur_image(ori)``.
    """
    i_color = ori
    result = i_color[..., :3]
    skin = skin_mask[..., :1]
    soften = soften_mask[..., :1]
    am = adjust_mask[..., :3]
    blur_rgb = blur[..., :3]
    blur_a = blur[..., 3:4]

    # --- teeth / bright adjust (r>0.01 and b>0.01) -----------------------
    if teeth_lut is not None:
        teeth = _lut_sample(teeth_lut, result, lut_dim)
        result = jnp.where(
            (am[..., 0:1] > 0.01) & (am[..., 2:3] > 0.01),
            mix(result, teeth, am[..., 2:3]),
            result,
        )

    # --- brighten (b>0.01 and r<0.01) ------------------------------------
    sum_color = result
    for dy, dx in TAPS_A:
        sum_color = sum_color + _shift(ori[..., :3], dy, dx)
    sum_color = sum_color * 0.2
    sum_color = jnp.clip(sum_color + (result - sum_color) * 3.0, 0.0, 1.0)
    sum_color = jnp.maximum(result, sum_color)
    result_b = mix(result, sum_color, am[..., 2:3])
    bright_color = jnp.clip(
        result_b * result_b * (-0.3543) + 1.354 * result_b, 0.0, 1.0
    )
    bright_color = jnp.clip(
        (bright_color - 0.5) * (1.0 + am[..., 2:3] * 0.6) + 0.5, 0.0, 1.0
    )
    result_b = mix(result_b, bright_color, am[..., 2:3])
    result = jnp.where(
        (am[..., 2:3] > 0.01) & (am[..., 0:1] < 0.01), result_b, result
    )

    # --- clarity (r>0.01 and b<0.005) ------------------------------------
    ds_rgb = ds[..., :3]
    sum_color = ds_rgb
    for dy, dx in TAPS_A:
        sum_color = sum_color + _shift(ds_rgb, dy, dx)
    sum_color = sum_color * 0.2
    tex_diff = jnp.clip((sum_color - ds_rgb) * 2.0 + 0.05 * blur_rgb, 0.0, 0.4)
    tex_diff = jnp.minimum(result + tex_diff, 1.0)
    result = jnp.where(
        (am[..., 0:1] > 0.01) & (am[..., 2:3] < 0.005),
        mix(result, tex_diff, am[..., 0:1]),
        result,
    )

    # --- sharpen-lite (g>0.01 and r<0.005) -------------------------------
    scaled = ds_rgb
    sum1 = scaled
    for dy, dx in TAPS_B:
        sum1 = sum1 + _shift(ds_rgb, dy, dx)
    sum1 = sum1 * 0.2
    sum2 = scaled
    for dy, dx in TAPS_A:
        sum2 = sum2 + _shift(ds_rgb, dy, dx)
    sum2 = sum2 * 0.1111
    sum2 = mix(sum2, blur_rgb, 0.392)
    tex_diff = jnp.clip((sum2 - sum1) * 1.3 + 0.03 * blur_rgb, 0.0, 0.2)
    tex_diff = jnp.minimum(result + tex_diff, 1.0)
    result = jnp.where(
        (am[..., 1:2] > 0.01) & (am[..., 0:1] < 0.005),
        mix(result, tex_diff, am[..., 1:2]),
        result,
    )

    # --- skin smoothing + sharpen (blurAlpha != 0) -----------------------
    p = jnp.clip((jnp.minimum(result[..., 0:1], blur_rgb[..., 0:1]) - 0.2) * 6.0, 0.0, 1.0)
    k_min = (
        (1.0 - blur_a / (blur_a + 0.1))
        * p
        * float(blur_alpha)
        * skin
    )
    smoothed = mix(result, blur_rgb, k_min * (1.0 - soften))
    result = jnp.where(
        (float(blur_alpha) != 0.0) & (skin > 0.005), smoothed, result
    )

    if sharpness != 0.0:
        cc = (
            _shift(ori[..., 1:2], *TAPS_C[0])
            + _shift(ori[..., 1:2], *TAPS_C[1])
            + _shift(ori[..., 1:2], *TAPS_C[2])
            + _shift(ori[..., 1:2], *TAPS_C[3])
        ) * 0.25
        h_pass = i_color[..., 1:2] - cc + 0.5
        flag = jnp.where(h_pass >= 0.5, 1.0, 0.0)
        tmp = 2.0 * h_pass + result - 1.0
        sharp = mix(jnp.maximum(tmp, 0.0), jnp.minimum(tmp, 1.0), flag)
        sharpened = mix(result, sharp, float(sharpness))
        result = jnp.where(float(blur_alpha) != 0.0, sharpened, result)

    # --- global bright LUT ----------------------------------------------
    if bright_lut is not None:
        looked = _lut_sample(bright_lut, result, lut_dim)
        result = mix(result, looked, float(bright_alpha))

    return jnp.concatenate([jnp.clip(result, 0.0, 1.0), i_color[..., 3:4]], axis=-1)


def beautify_g2(
    ori: jnp.ndarray,
    blur: jnp.ndarray,
    skin_mask: jnp.ndarray,
    soften_mask: jnp.ndarray,
    blur_alpha: float,
    bright_alpha: float,
    sharpness: float,
) -> jnp.ndarray:
    """``facemagic_61cfd4.glsl`` (G2 path): no adjust/clarity/teeth masks."""
    adjust = jnp.zeros(ori.shape[:2] + (3,), dtype=jnp.float32)
    return beautify_g3(
        ori,
        blur,
        ori,
        skin_mask,
        soften_mask,
        adjust,
        blur_alpha,
        bright_alpha,
        sharpness,
        bright_lut=None,
        teeth_lut=None,
    )


def load_beauty_presets(path: str | None = None) -> list[dict]:
    """Parse ``assets/beauty_list`` (JSON preset list shipped in the APK)."""
    path = path or os.path.join(DATA_DIR, "beauty_list")
    with open(path, "r", encoding="utf-8") as fh:
        text = fh.read()
    data = json.loads(text)
    presets = []
    for entry in data:
        smooth = entry.get("smoothSkin", {})
        deform = entry.get("faceDeform", {})
        makeup = entry.get("makeup", {})
        presets.append(
            {
                "beauty": float(smooth.get("beauty", 0)) / 100.0,
                "bright": float(smooth.get("bright", 0)) / 100.0,
                "face_deform": deform,
                "makeup": makeup,
            }
        )
    return presets
