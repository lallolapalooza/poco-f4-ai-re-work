"""Kwai Live camera post-processing chain (FaceMagic), runnable in JAX.

Traced from `com.kwai.kuaishou.video.live` 6.0.20.524207
(sha256 642f1cbf...):

    Camera/GL frame
      -> westeros GL pipeline (libwesteros.so)
      -> FaceMagicController (Java, com.kwai.video.westeros.v2.faceless)
      -> libfaceless-plugin.so  (44 JNI entry points, nativeSetEffect*,
                                 nativeSetBeautifyExternalBuiltinPath, ...)
      -> libFaceMagic.so        (FM::FMGorgeousBeautify / FMGorgeousBodySlim /
                                 FMLiquifyEffect / FMMakeupEffectWrapper)
           |-- libCGE.so        (CGELiquifyFilter: mesh warp, GPU)
           |-- libskwai.so      (SKwai AR runtime: VirtualFaceProcessor)
           `-- libgorgeous.so   (Gorgeous face/makeup rendering)
      -> YCNN models (downloaded, not in APK): face landmark, face seg,
         skin seg, human keypoint/mesh, matting, ...

This module implements the two weight-free stages that are fully
recoverable from the APK:

  1. G3 beautify blend  (GLSL recovered from libFaceMagic.so -> beauty.py)
  2. Body-slim / face-deform liquify warp (op lists from
     assets/BodySlimming/*.params -> liquify.py)

The learned stages (face detect, landmarks, skin/soften masks) are not
shipped with the APK; callers must supply their outputs. ``apply``
therefore accepts masks/landmarks through ``ctx`` and documents the
required shapes.
"""

from __future__ import annotations

import os

import jax
import jax.numpy as jnp
import numpy as np

try:  # package import
    from . import beauty, liquify
except ImportError:  # direct ``python pipeline.py``
    import beauty  # type: ignore
    import liquify  # type: ignore

HERE = os.path.dirname(os.path.abspath(__file__))
DATA = os.path.join(HERE, "data")


def init_params(profile: str = "Filter40p") -> dict:
    """Load the APK's real parameters (no weights exist to load)."""
    presets = beauty.load_beauty_presets(os.path.join(DATA, "beauty_list"))
    body = liquify.load_body_params(profile)
    return {
        "presets": presets,
        "body_slim": body,
        "lut": beauty.identity_lut(8),
        "lut_dim": 8,
        "profile": profile,
    }


def synthetic_masks(h: int, w: int, seed: int = 0) -> dict:
    """Deterministic stand-ins for the YCNN mask outputs (centred ellipse)."""
    ys = (np.arange(h, dtype=np.float32) + 0.5) / h - 0.5
    xs = (np.arange(w, dtype=np.float32) + 0.5) / w - 0.5
    gy, gx = np.meshgrid(ys, xs, indexing="ij")
    r = np.sqrt((gx * 1.4) ** 2 + (gy * 1.0) ** 2)
    skin = np.clip(1.0 - r * 1.6, 0.0, 1.0).astype(np.float32)[..., None]
    soften = (skin * 0.5).astype(np.float32)
    adjust = np.zeros((h, w, 3), dtype=np.float32)
    return {"skin_mask": skin, "soften_mask": soften, "adjust_mask": adjust}


def synthetic_landmarks(h: int, w: int, n: int = 63) -> jnp.ndarray:
    """Deterministic body-landmark stand-in (uv coordinates).

    The real pipeline gets these from the YCNN ``human_keypoint`` model.
    Layout: a torso grid, so index ranges used by the param files (0..62)
    are all addressable.
    """
    pts = []
    for i in range(n):
        row, col = divmod(i, 9)
        pts.append([0.15 + 0.7 * (col / 8.0), 0.15 + 0.7 * (row / 6.0)])
    return jnp.asarray(np.asarray(pts, dtype=np.float32))


def apply(params: dict, frame, ctx: dict | None = None):
    """Run the live post-processing chain.

    Parameters
    ----------
    frame : (H, W, 3|4) float32 in [0, 1]
    ctx   : optional dict with
            ``preset_index`` (int), ``skin_mask`` (H,W,1),
            ``soften_mask`` (H,W,1), ``adjust_mask`` (H,W,3),
            ``landmarks`` (N,2), ``body_intensities`` (dict)
    """
    ctx = ctx or {}
    x = jnp.asarray(frame, dtype=jnp.float32)
    if x.ndim != 3:
        raise ValueError(f"frame must be HxWxC, got {x.shape}")
    if x.shape[-1] == 3:
        x = jnp.concatenate([x, jnp.ones(x.shape[:2] + (1,), jnp.float32)], axis=-1)
    h, w = x.shape[0], x.shape[1]

    masks = synthetic_masks(h, w)
    skin = jnp.asarray(ctx.get("skin_mask", masks["skin_mask"]), jnp.float32)
    soften = jnp.asarray(ctx.get("soften_mask", masks["soften_mask"]), jnp.float32)
    adjust = jnp.asarray(ctx.get("adjust_mask", masks["adjust_mask"]), jnp.float32)

    preset = params["presets"][int(ctx.get("preset_index", 1)) % len(params["presets"])]
    blur = beauty.blur_image(x)

    out = beauty.beautify_g3(
        x,
        blur,
        x,  # the downsampled texture falls back to the frame when ds is absent
        skin,
        soften,
        adjust,
        blur_alpha=preset["beauty"],
        bright_alpha=preset["bright"],
        sharpness=preset["beauty"] * 0.25,  # westeros setSoften/setSharpness pair
        bright_lut=params["lut"],
        teeth_lut=None,
        lut_dim=params["lut_dim"],
    )

    landmarks = ctx.get("landmarks")
    intensities = ctx.get("body_intensities")
    if landmarks is not None:
        out = liquify.body_slim(
            out,
            jnp.asarray(landmarks, jnp.float32),
            params["body_slim"],
            intensities,
        )
    return out


def main() -> None:
    params = init_params()
    h, w = 256, 144
    ys = np.linspace(0.0, 1.0, h, dtype=np.float32)[:, None]
    xs = np.linspace(0.0, 1.0, w, dtype=np.float32)[None, :]
    frame = jnp.asarray(
        np.stack([np.broadcast_to(xs, (h, w)), np.broadcast_to(ys, (h, w)),
                  np.broadcast_to(0.5 + 0.0 * xs, (h, w))], axis=-1).astype(np.float32)
    )
    ctx = {
        "preset_index": 3,
        "landmarks": synthetic_landmarks(h, w),
        "body_intensities": {"Waist": 0.8, "Shoulder": 0.5, "HipPost": 0.3},
    }
    out = apply(params, frame, ctx)
    print("params:", len(params["presets"]), "beauty presets;",
          len(params["body_slim"]), "body-slim op groups;", params["profile"])
    print("input :", frame.shape, frame.dtype)
    print("output:", out.shape, out.dtype)
    print("output range:", float(out.min()), float(out.max()))
    print("mean abs delta vs input:", float(jnp.mean(jnp.abs(out[..., :3] - frame))))


if __name__ == "__main__":
    main()
