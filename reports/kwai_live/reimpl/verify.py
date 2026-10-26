"""Verification harness for the Kwai Live reimplementation.

Checks the JAX stages against (a) an independent NumPy transcription of the
same GLSL programs, (b) analytic invariants (identity at zero intensity),
and (c) determinism.  Writes ``verify.txt`` next to this file.

    python verify.py
"""

from __future__ import annotations

import os
import platform
import subprocess
import sys
import traceback

import jax
import jax.numpy as jnp
import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, os.path.dirname(HERE))

from reimpl import beauty, liquify, pipeline  # noqa: E402


# --------------------------------------------------------------------------
# Independent NumPy transcription of facemagic_61b3c0.glsl (no JAX, no splits
# shared with beauty.py: edge padding + explicit slicing, written to mirror
# the GLSL statement order).
# --------------------------------------------------------------------------
def np_taps(x, taps):
    """Sum of x at the given (dy, dx) offsets, edge-clamped."""
    h, w = x.shape[:2]
    acc = np.zeros_like(x)
    for dy, dx in taps:
        ys = np.clip(np.arange(h) + dy, 0, h - 1)
        xs = np.clip(np.arange(w) + dx, 0, w - 1)
        acc += x[np.ix_(ys, xs)]
    return acc


def np_blur(rgba):
    mean_a = np_taps(rgba[..., 3:4], beauty._OFFS) * (1.0 / 9.0)
    alpha_pass = np.concatenate([rgba[..., :3], mean_a], axis=-1)
    mean_rgb = np_taps(alpha_pass[..., :3], beauty._OFFS) * 0.1111
    mean_y = np.sum(mean_rgb * np.array([0.2983549, 0.58747799, 0.11416711]), axis=-1,
                    keepdims=True)
    diff = alpha_pass[..., 3:4] - mean_y
    var_y = np.minimum(diff * diff * 50.0, 1.0)
    return np.concatenate([mean_rgb, var_y], axis=-1)


def np_blend(ori, blur, ds, skin, soften, adjust, blur_alpha, bright_alpha, sharpness):
    result = ori[..., :3].copy()
    am = adjust[..., :3]
    blur_rgb, blur_a = blur[..., :3], blur[..., 3:4]

    # brighten
    sum_color = result + np_taps(ori[..., :3], beauty.TAPS_A)
    sum_color = sum_color * 0.2
    sum_color = np.clip(sum_color + (result - sum_color) * 3.0, 0.0, 1.0)
    sum_color = np.maximum(result, sum_color)
    result_b = result + (sum_color - result) * am[..., 2:3]
    bright = np.clip(result_b * result_b * (-0.3543) + 1.354 * result_b, 0.0, 1.0)
    bright = np.clip((bright - 0.5) * (1.0 + am[..., 2:3] * 0.6) + 0.5, 0.0, 1.0)
    result_b = result_b + (bright - result_b) * am[..., 2:3]
    m = (am[..., 2:3] > 0.01) & (am[..., 0:1] < 0.01)
    result = np.where(m, result_b, result)

    # clarity
    sum_color = ds[..., :3] + np_taps(ds[..., :3], beauty.TAPS_A)
    sum_color = sum_color * 0.2
    tex_diff = np.clip((sum_color - ds[..., :3]) * 2.0 + 0.05 * blur_rgb, 0.0, 0.4)
    tex_diff = np.minimum(result + tex_diff, 1.0)
    m = (am[..., 0:1] > 0.01) & (am[..., 2:3] < 0.005)
    result = np.where(m, result + (tex_diff - result) * am[..., 0:1], result)

    # sharpen-lite
    sum1 = ds[..., :3] + np_taps(ds[..., :3], beauty.TAPS_B)
    sum1 = sum1 * 0.2
    sum2 = ds[..., :3] + np_taps(ds[..., :3], beauty.TAPS_A)
    sum2 = sum2 * 0.1111
    sum2 = sum2 + (blur_rgb - sum2) * 0.392
    tex_diff = np.clip((sum2 - sum1) * 1.3 + 0.03 * blur_rgb, 0.0, 0.2)
    tex_diff = np.minimum(result + tex_diff, 1.0)
    m = (am[..., 1:2] > 0.01) & (am[..., 0:1] < 0.005)
    result = np.where(m, result + (tex_diff - result) * am[..., 1:2], result)

    # skin smoothing
    p = np.clip((np.minimum(result[..., 0:1], blur_rgb[..., 0:1]) - 0.2) * 6.0, 0.0, 1.0)
    k_min = (1.0 - blur_a / (blur_a + 0.1)) * p * blur_alpha * skin
    smoothed = result + (blur_rgb - result) * (k_min * (1.0 - soften))
    result = np.where((blur_alpha != 0.0) & (skin > 0.005), smoothed, result)

    if sharpness != 0.0:
        cc = np_taps(ori[..., 1:2], beauty.TAPS_C) * 0.25
        h_pass = ori[..., 1:2] - cc + 0.5
        flag = np.where(h_pass >= 0.5, 1.0, 0.0)
        tmp = 2.0 * h_pass + result - 1.0
        shap = np.maximum(tmp, 0.0) + (np.minimum(tmp, 1.0) - np.maximum(tmp, 0.0)) * flag
        sharpened = result + (shap - result) * sharpness
        result = np.where(blur_alpha != 0.0, sharpened, result)

    return np.concatenate([np.clip(result, 0.0, 1.0), ori[..., 3:4]], axis=-1)


# --------------------------------------------------------------------------
def main() -> int:
    checks: list[tuple[str, bool, str]] = []

    def check(name, ok, detail=""):
        checks.append((name, bool(ok), detail))

    params = pipeline.init_params()
    check("params loaded", len(params["presets"]) > 0 and len(params["body_slim"]) == 7,
          f"{len(params['presets'])} beauty presets, {len(params['body_slim'])} body groups, "
          f"{sum(len(v) for v in params['body_slim'].values())} warp ops")

    rng = np.random.default_rng(0)
    frame = rng.random((64, 48, 3), dtype=np.float32)
    ctx = {"preset_index": 4, "landmarks": pipeline.synthetic_landmarks(64, 48),
           "body_intensities": {"Waist": 0.8, "Shoulder": 0.5}}
    out = pipeline.apply(params, jnp.asarray(frame), ctx)
    check("apply shapes", out.shape == (64, 48, 4), f"{out.shape} {out.dtype}")

    # (a) JAX vs NumPy transcription of the G3 blend
    x4 = np.concatenate([frame, np.ones((64, 48, 1), np.float32)], axis=-1)
    masks = pipeline.synthetic_masks(64, 48)
    adjust = rng.random((64, 48, 3), dtype=np.float32) * 0.02  # tiny non-zero masks
    blur_np = np_blur(x4)
    ref = np_blend(x4, blur_np, x4, masks["skin_mask"], masks["soften_mask"], adjust,
                   0.35, 0.2, 0.05)
    got = beauty.beautify_g3(
        jnp.asarray(x4), jnp.asarray(blur_np), jnp.asarray(x4),
        jnp.asarray(masks["skin_mask"]), jnp.asarray(masks["soften_mask"]),
        jnp.asarray(adjust), 0.35, 0.2, 0.05, None, None)
    err = float(np.max(np.abs(np.asarray(got) - ref)))
    check("JAX vs NumPy G3 blend", err < 1e-5, f"max_abs_err={err:.3e}")

    # (b) zero-intensity identity for the blend (no masks, no sharpen)
    zero_masks = {"skin_mask": np.ones((64, 48, 1), np.float32),
                  "soften_mask": np.zeros((64, 48, 1), np.float32),
                  "adjust_mask": np.zeros((64, 48, 3), np.float32)}
    ident = beauty.beautify_g3(
        jnp.asarray(x4), jnp.asarray(blur_np), jnp.asarray(x4),
        jnp.asarray(zero_masks["skin_mask"]), jnp.asarray(zero_masks["soften_mask"]),
        jnp.asarray(zero_masks["adjust_mask"]), 0.0, 0.0, 0.0, None, None)
    d = float(jnp.max(jnp.abs(ident - jnp.asarray(x4))))
    check("blend identity at zero intensity", d == 0.0, f"max_abs_delta={d:.3e}")

    # (c) beautify alone changes pixels monotonically with intensity
    base = beauty.beautify_g3(
        jnp.asarray(x4), jnp.asarray(blur_np), jnp.asarray(x4),
        jnp.asarray(masks["skin_mask"]), jnp.asarray(masks["soften_mask"]),
        jnp.asarray(zero_masks["adjust_mask"]), 0.2, 0.0, 0.0, None, None)
    strong = beauty.beautify_g3(
        jnp.asarray(x4), jnp.asarray(blur_np), jnp.asarray(x4),
        jnp.asarray(masks["skin_mask"]), jnp.asarray(masks["soften_mask"]),
        jnp.asarray(zero_masks["adjust_mask"]), 0.8, 0.0, 0.0, None, None)
    d1 = float(jnp.mean(jnp.abs(base - jnp.asarray(x4))))
    d2 = float(jnp.mean(jnp.abs(strong - jnp.asarray(x4))))
    check("beautify strength monotonic", 0.0 < d1 < d2, f"delta(0.2)={d1:.4f} delta(0.8)={d2:.4f}")

    # (d) body-slim identity at zero slider values
    lm = pipeline.synthetic_landmarks(64, 48)
    zero = {g: 0.0 for g in params["body_slim"]}
    warp0 = liquify.body_slim(jnp.asarray(x4), lm, params["body_slim"], zero)
    d = float(jnp.max(jnp.abs(warp0 - jnp.asarray(x4))))
    # bilinear resampling at integer f32 centres leaves ~1 ulp of rounding
    check("body-slim identity at zero intensity", d < 1e-5, f"max_abs_delta={d:.3e} (f32 resample)")

    # (e) body-slim with the APK's real op lists: bounded, deterministic, non-identity
    full = {g: 1.0 for g in params["body_slim"]}
    w1 = liquify.body_slim(jnp.asarray(x4), lm, params["body_slim"], full)
    w2 = liquify.body_slim(jnp.asarray(x4), lm, params["body_slim"], full)
    det = float(jnp.max(jnp.abs(w1 - w2)))
    changed = float(jnp.mean(jnp.abs(w1 - jnp.asarray(x4))))
    check("body-slim deterministic", det == 0.0, f"max_abs_delta={det:.3e}")
    check("body-slim non-identity", changed > 1e-3, f"mean_abs_delta={changed:.4f}")

    # displacement field bounded (should not fold the image)
    disp = jnp.zeros((64, 48, 2), jnp.float32)
    disp = liquify.apply_op_list(disp, params["body_slim"]["Waist"], lm, 1.0, (1.0, 64 / 48))
    maxdisp = float(jnp.max(jnp.linalg.norm(disp, axis=-1)))
    check("warp displacement bounded", maxdisp < 0.10, f"max_uv_displacement={maxdisp:.4f}")

    # (f) multi-resolution run of the full chain
    ok = True
    shapes = []
    for h, w in ((32, 32), (128, 96), (256, 144)):
        f = rng.random((h, w, 3), dtype=np.float32)
        o = pipeline.apply(params, jnp.asarray(f),
                           {"preset_index": 2, "landmarks": pipeline.synthetic_landmarks(h, w)})
        shapes.append(f"{h}x{w}->{tuple(o.shape)}")
        ok = ok and o.shape == (h, w, 4) and np.isfinite(np.asarray(o)).all()
    check("multi-resolution run", ok, ", ".join(shapes))

    lines = []
    lines.append("Kwai Live (com.kwai.kuaishou.video.live 6.0.20.524207) - reimpl verification")
    lines.append(f"python  : {sys.version.split()[0]} ({platform.platform()})")
    lines.append(f"jax     : {jax.__version__}  devices={jax.devices()}")
    lines.append(f"numpy   : {np.__version__}")
    lines.append(f"command : python verify.py  (cwd={HERE})")
    lines.append(f"params  : {len(params['presets'])} beauty presets from assets/beauty_list; "
                 f"{sum(len(v) for v in params['body_slim'].values())} liquify ops from "
                 f"assets/BodySlimming/{params['profile']}")
    lines.append("")
    lines.append(f"{'check':42s} {'result':6s} detail")
    lines.append("-" * 100)
    for name, ok_, detail in checks:
        lines.append(f"{name:42s} {'PASS' if ok_ else 'FAIL':6s} {detail}")
    lines.append("")
    lines.append(f"RESULT: {sum(1 for _,o,_ in checks if o)}/{len(checks)} checks passed")
    text = "\n".join(lines) + "\n"
    with open(os.path.join(HERE, "verify.txt"), "w", encoding="utf-8") as fh:
        fh.write(text)
    print(text)
    return 0 if all(o for _, o, _ in checks) else 1


if __name__ == "__main__":
    try:
        sys.exit(main())
    except Exception:  # pragma: no cover
        traceback.print_exc()
        sys.exit(2)
