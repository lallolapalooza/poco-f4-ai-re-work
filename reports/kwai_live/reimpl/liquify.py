"""Landmark-driven liquify warp (body slimming / face deform), in JAX.

Confirmed from the artifacts:
  * ``assets/BodySlimming/{Filter28p,Filter40p}/*.params`` are JSON op lists
    with ``type`` in {forward, restore, bloatWrinkle, bloatWrinkle1},
    landmark ``start``/``end``/``target`` indices, ``intensity``,
    ``radius``, ``useGradient`` and optional ``curve``.
  * ``libFaceMagic.so`` ``FM::FMLiquifyEffect::{forward, restore, bloat,
    wrinkle, updateMesh, undo, redo}`` only forwards to
    ``CGE::CGELiquifyFilter::*`` (see ``../native/liquify_*.s``).
  * The deformation transfer functions were recovered from
    ``libCGE.so`` (Ghidra, ``../native/libCGE_liquify.c``):
      - ``forwardDeformMesh(start, end, ax, ay, radius, intensity)``
            d = |v*aspect - start|
            if d <= radius:
                t = 1 - d/radius;  w = t*t*(3-2t) * intensity
                v += ((end - start) / aspect) * w
      - ``bloatMeshWithPoint(p, ax, ay, radius, intensity)``
            v += ((v*aspect - p) / aspect) * w        (radial outward)
      - ``wrinkleMeshWithPoint(p, ...)``
            v += ((p - v*aspect) / aspect) * w        (radial inward)
      - ``restoreMeshWithPoint(p, ax, ay, radius, intensity)``
            v = rest_uv*w + v*(1 - w)                 (blend back to rest)
      - ``restoreMeshWithIntensity(k)``
            v = v*(1 - k) + rest_uv*k
      - ``initWithMesh(min, max, cell)`` builds a regular grid mesh whose
        cell size is clamped to [0.001, 0.2]; ``undo``/``redo`` keep
        snapshots of the vertex buffer.

This module applies the same transfer functions to a dense per-pixel
displacement field instead of the vertex grid: ``v = grid + disp`` and
``rest_uv = grid``, so the ``restore*`` formulas become
``disp *= (1 - w)``.  The mesh is at most 0.2 units per cell, so the
difference to a dense field is the bilinear vertex interpolation inside a
cell - results are therefore equivalent up to mesh interpolation, not
bit-identical to the device GL renderer (which also applies GPU texture
interpolation).
"""


from __future__ import annotations

import json
import os

import jax.numpy as jnp
import numpy as np

DATA_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "data")


def _grid(h: int, w: int) -> jnp.ndarray:
    ys = (jnp.arange(h, dtype=jnp.float32) + 0.5) / h
    xs = (jnp.arange(w, dtype=jnp.float32) + 0.5) / w
    gy, gx = jnp.meshgrid(ys, xs, indexing="ij")
    return jnp.stack([gx, gy], axis=-1)  # (H, W, 2) in uv units


def _falloff(dist: jnp.ndarray, radius: float) -> jnp.ndarray:
    """C1 radial falloff: 1 at the centre, 0 at ``radius`` and beyond."""
    r = jnp.maximum(jnp.asarray(radius, jnp.float32), 1e-6)
    t = jnp.clip(1.0 - dist / r, 0.0, 1.0)
    return t * t * (3.0 - 2.0 * t)  # smoothstep


def forward(disp, p_start, p_end, intensity, radius, aspect):
    """``CGELiquifyFilter::forwardDeformMesh`` (0x2321... in libCGE.so).

    ``d = |(v - start) * aspect|``; for ``d <= radius``:
    ``v += (end - start) * smoothstep(1 - d/radius) * intensity``.
    """
    grid = _grid(disp.shape[0], disp.shape[1])
    delta = jnp.asarray(p_end, jnp.float32) - jnp.asarray(p_start, jnp.float32)
    d = jnp.linalg.norm((grid - jnp.asarray(p_start, jnp.float32)) * aspect, axis=-1)
    w = _falloff(d, radius)
    return disp + w[..., None] * (float(intensity) * delta)


def restore(disp, target, intensity, radius, aspect):
    """``restoreMeshWithPoint``: ``v = rest*w + v*(1-w)`` -> ``disp *= 1-w``."""
    grid = _grid(disp.shape[0], disp.shape[1])
    d = jnp.linalg.norm((grid - jnp.asarray(target, jnp.float32)) * aspect, axis=-1)
    w = _falloff(d, radius) * float(intensity)
    return disp * (1.0 - w[..., None])


def bloat_wrinkle(disp, target, intensity, radius, aspect):
    """``bloatMeshWithPoint`` / ``wrinkleMeshWithPoint`` (sign of intensity).

    bloat: ``v += (v - p)*w``; wrinkle: ``v += (p - v)*w`` -> the sign of
    ``intensity`` selects the two entry points of libCGE.
    """
    grid = _grid(disp.shape[0], disp.shape[1])
    v = grid - jnp.asarray(target, jnp.float32)
    d = jnp.linalg.norm(v * aspect, axis=-1)
    w = _falloff(d, radius)
    return disp + (w * float(intensity))[..., None] * v


def restore_intensity(disp, intensity):
    """``restoreMeshWithIntensity``: ``v = v*(1-k) + rest*k`` -> ``disp *= 1-k``."""
    return disp * (1.0 - float(intensity))


def sample_bilinear(image: jnp.ndarray, disp: jnp.ndarray) -> jnp.ndarray:
    """Inverse-map resample: out(p) = image(p - disp(p)), edge clamped."""
    h, w = image.shape[0], image.shape[1]
    grid = _grid(h, w) - disp
    x = jnp.clip(grid[..., 0], 0.0, 1.0) * w - 0.5
    y = jnp.clip(grid[..., 1], 0.0, 1.0) * h - 0.5
    x0 = jnp.floor(x).astype(jnp.int32)
    y0 = jnp.floor(y).astype(jnp.int32)
    fx = (x - x0)[..., None]
    fy = (y - y0)[..., None]
    x0c, y0c = jnp.clip(x0, 0, w - 1), jnp.clip(y0, 0, h - 1)
    x1c, y1c = jnp.clip(x0 + 1, 0, w - 1), jnp.clip(y0 + 1, 0, h - 1)

    def tap(yy, xx):
        return image[yy, xx]

    c00 = tap(y0c, x0c)
    c10 = tap(y0c, x1c)
    c01 = tap(y1c, x0c)
    c11 = tap(y1c, x1c)
    top = c00 * (1 - fx) + c10 * fx
    bot = c01 * (1 - fx) + c11 * fx
    return top * (1 - fy) + bot * fy


def load_body_params(profile: str = "Filter40p") -> dict[str, list[dict]]:
    """Load every ``*.params`` op list of one body-slimming profile."""
    base = os.path.join(DATA_DIR, "BodySlimming", profile)
    out: dict[str, list[dict]] = {}
    for name in sorted(os.listdir(base)):
        if not name.endswith(".params"):
            continue
        with open(os.path.join(base, name), "r", encoding="utf-8") as fh:
            out[name[: -len(".params")]] = json.load(fh)
    return out


def _pt(landmarks: jnp.ndarray, idx: str) -> jnp.ndarray:
    i = int(idx)
    if i >= landmarks.shape[0]:
        raise IndexError(f"landmark index {i} out of range ({landmarks.shape[0]})")
    return landmarks[i]


def apply_op_list(
    disp: jnp.ndarray,
    ops: list[dict],
    landmarks: jnp.ndarray,
    intensity_scale: float = 1.0,
    aspect: tuple[float, float] = (1.0, 1.0),
) -> jnp.ndarray:
    """Apply one ``*.params`` op list to the displacement field."""
    aspect_j = jnp.asarray(aspect, jnp.float32)
    for op in ops:
        scale = float(op.get("intensity", 0.0)) * float(intensity_scale)
        if scale == 0.0:
            continue
        kind = op["type"]
        radius = float(op.get("radius", 0.2))
        if kind == "forward":
            disp = forward(disp, _pt(landmarks, op["start"]), _pt(landmarks, op["end"]), scale, radius, aspect_j)
        elif kind == "restore":
            disp = restore(disp, _pt(landmarks, op["target"]), -scale, radius, aspect_j)
        elif kind in ("bloatWrinkle", "bloatWrinkle1"):
            if "target" in op:
                disp = bloat_wrinkle(disp, _pt(landmarks, op["target"]), scale, radius, aspect_j)
            else:
                mid = (_pt(landmarks, op["start"]) + _pt(landmarks, op["end"])) * 0.5
                disp = bloat_wrinkle(disp, mid, scale, radius, aspect_j)
        else:
            raise ValueError(f"unsupported liquify op {kind!r}")
    return disp


def body_slim(
    image: jnp.ndarray,
    landmarks: jnp.ndarray,
    params: dict[str, list[dict]],
    intensities: dict[str, float] | None = None,
) -> jnp.ndarray:
    """Warp ``image`` with the body-slimming op lists.

    ``params`` is the dict from :func:`load_body_params`; ``intensities``
    scales each group (``Waist``, ``HipPost``, ...) like the UI sliders do
    in ``BodySlimmingSeekBarPresenter``.
    """
    h, w = image.shape[0], image.shape[1]
    aspect = (1.0, float(h) / float(w))
    disp = jnp.zeros((h, w, 2), dtype=jnp.float32)
    intensities = intensities or {}
    for group, ops in params.items():
        s = float(intensities.get(group, 1.0))
        if s == 0.0:
            continue
        disp = apply_op_list(disp, ops, landmarks, s, aspect)
    return sample_bilinear(image, disp)
