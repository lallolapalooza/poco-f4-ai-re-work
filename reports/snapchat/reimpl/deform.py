"""AR deformation stage: Looksery liquify + landmark-triangulated mesh warp.

Both pieces come straight from the APK assets:

* ``scenarium/facestretchLiquify.glsl`` (fragment shader) defines the radial
  displacement used by face stretching / liquify lenses.  Reproduced exactly::

      dist      = dot(screenPoint_i, screenPoint_i)          # |p_i|^2
      fromCtr   = pos - coeffs_i.xy                          # normalized coords
      vecCoef   = pow(clamp(dist / r_i^2, eps, 1), coef_i) - 1
      vecCoef  *= (dist <= r_i^2)
      posFetch += fromCtr * vecCoef

  with ``POWEREPSILON = 1/1280``, applied as an inverse map ("fetch" shift).

* ``Landmarks/triangulation.txt`` / ``triangulation_with_mouth.txt`` -- the
  triangle lists over the 68/98 landmark topology (118 / 124 triangles).
  The mesh warp below is a standard piecewise-affine inverse map over those
  triangles, i.e. the CPU-side deformation that feeds Scenarium's GLES mesh.
"""

from __future__ import annotations

import numpy as np
import jax.numpy as jnp

POWEREPSILON = 1.0 / 1280.0


def liquify_displacement(coords: jnp.ndarray, points: jnp.ndarray,
                         coeffs: jnp.ndarray, radii: jnp.ndarray) -> jnp.ndarray:
    """Displacement field of the facestretchLiquify shader.

    coords  : (N, 2) normalized screen coords in [-1, 1]
    points  : (K, 2) control point screen positions
    coeffs  : (K,)   falloff exponent
    radii   : (K,)   influence radius (squared internally)
    returns : (N, 2) displacement to ADD to the fetch coordinate
    """
    disp = jnp.zeros_like(coords)
    for i in range(points.shape[0]):
        p = points[i]
        r2 = radii[i] * radii[i]
        dist = jnp.sum(p * p)
        from_ctr = coords - p
        v = jnp.power(jnp.clip(dist / r2, POWEREPSILON, 1.0), coeffs[i]) - 1.0
        v = v * (dist <= r2)
        disp = disp + from_ctr * v
    return disp


def _bilinear(img: jnp.ndarray, x: jnp.ndarray, y: jnp.ndarray):
    h, w = img.shape
    x = jnp.clip(x, 0.0, w - 1.001)
    y = jnp.clip(y, 0.0, h - 1.001)
    x0 = jnp.floor(x).astype(jnp.int32)
    y0 = jnp.floor(y).astype(jnp.int32)
    fx = x - x0
    fy = y - y0
    top = img[y0, x0] * (1 - fx) + img[y0, x0 + 1] * fx
    bot = img[y0 + 1, x0] * (1 - fx) + img[y0 + 1, x0 + 1] * fx
    return top * (1 - fy) + bot * fy


def liquify(img: np.ndarray, points: np.ndarray, coeffs: np.ndarray,
            radii: np.ndarray) -> np.ndarray:
    """Apply the shader displacement to a 2-D frame (inverse mapping)."""
    h, w = img.shape
    ys, xs = jnp.meshgrid(jnp.arange(h, dtype=jnp.float32),
                          jnp.arange(w, dtype=jnp.float32), indexing="ij")
    coords = jnp.stack([(xs / (w - 1)) * 2 - 1, (ys / (h - 1)) * 2 - 1], -1)
    disp = liquify_displacement(coords, jnp.asarray(points, jnp.float32),
                                jnp.asarray(coeffs, jnp.float32),
                                jnp.asarray(radii, jnp.float32))
    fetch = coords + disp
    fx = (fetch[..., 0] * 0.5 + 0.5) * (w - 1)
    fy = (fetch[..., 1] * 0.5 + 0.5) * (h - 1)
    return np.asarray(_bilinear(jnp.asarray(img, jnp.float32), fx, fy))


# --------------------------------------------------------------------------
# Triangulated mesh warp
# --------------------------------------------------------------------------
def warp_mesh(img: np.ndarray, src_pts: np.ndarray, dst_pts: np.ndarray,
              tris: list[tuple[int, int, int]]) -> np.ndarray:
    """Piecewise-affine warp driven by landmark triangles.

    ``src_pts`` / ``dst_pts``: (N,2) landmark positions in pixel coords
    (image landmarks and their deformed targets).  For every destination
    triangle the inverse affine map is solved and used to sample the source
    image; overlapping/duplicate coverage is averaged.
    """
    h, w = img.shape
    img_j = jnp.asarray(img, jnp.float32)
    acc = np.zeros((h, w), np.float32)
    cnt = np.zeros((h, w), np.float32)
    for (a, b, c) in tris:
        d = np.array([dst_pts[a], dst_pts[b], dst_pts[c]], np.float64)
        s = np.array([src_pts[a], src_pts[b], src_pts[c]], np.float64)
        # solve [x_src] = M [x_dst, y_dst, 1]
        A = np.concatenate([d, np.ones((3, 1))], 1)
        if abs(np.linalg.det(A)) < 1e-9:
            continue
        Mx = np.linalg.solve(A, s[:, 0])
        My = np.linalg.solve(A, s[:, 1])
        x0 = max(int(np.floor(d[:, 0].min())), 0)
        x1 = min(int(np.ceil(d[:, 0].max())) + 1, w)
        y0 = max(int(np.floor(d[:, 1].min())), 0)
        y1 = min(int(np.ceil(d[:, 1].max())) + 1, h)
        if x1 <= x0 or y1 <= y0:
            continue
        xs, ys = np.meshgrid(np.arange(x0, x1) + 0.5, np.arange(y0, y1) + 0.5)
        # barycentric test in destination triangle
        v0 = d[1] - d[0]
        v1 = d[2] - d[0]
        v2 = np.stack([xs - d[0, 0], ys - d[0, 1]], -1)
        den = v0[0] * v1[1] - v1[0] * v0[1]
        if abs(den) < 1e-9:
            continue
        u = (v2[..., 0] * v1[1] - v1[0] * v2[..., 1]) / den
        v = (v0[0] * v2[..., 1] - v2[..., 0] * v0[1]) / den
        inside = (u >= -1e-6) & (v >= -1e-6) & (u + v <= 1 + 1e-6)
        if not inside.any():
            continue
        sx = Mx[0] * xs + Mx[1] * ys + Mx[2]
        sy = My[0] * xs + My[1] * ys + My[2]
        # coordinates are in pixel-corner space; the sampler uses index space
        vals = np.asarray(_bilinear(img_j, jnp.asarray(sx - 0.5),
                                    jnp.asarray(sy - 0.5)))
        acc[y0:y1, x0:x1] += np.where(inside, vals, 0.0)
        cnt[y0:y1, x0:x1] += inside
    out = np.where(cnt > 0, acc / np.maximum(cnt, 1), img.astype(np.float32))
    return out.astype(np.float32)


def synthetic_landmarks(width: int, height: int, n: int = 76) -> np.ndarray:
    """Deterministic landmark layout for the 68/98-point triangulation.

    The shipped triangulation indexes points 0..75; the real tracker gets
    them from the ERT regressors.  For the synthetic verification we place
    them on an ellipse (jaw) plus an inner grid (features).
    """
    pts = []
    for i in range(n):
        if i < 40:
            ang = -np.pi * 0.9 + (i / 39.0) * np.pi * 1.8
            pts.append([width * 0.5 + width * 0.32 * np.cos(ang),
                        height * 0.55 + height * 0.40 * np.sin(ang)])
        else:
            k = i - 40
            pts.append([width * 0.30 + (k % 6) * width * 0.08,
                        height * 0.35 + (k // 6) * height * 0.08])
    return np.array(pts, np.float32)
