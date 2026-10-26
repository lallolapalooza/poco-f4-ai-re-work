"""JAX re-implementation of the ORB-style tracking front-end used by Looksery.

Evidence for this stage (all from the APK):

* ``assets/looksery/core/NaturalFeatureTracker/SystemData/tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin``
  -- a 256-bit binary vocabulary tree, branching 20, depth 2 (400 leaves),
  provided twice ("sharp" and plain).
* libcamplat symbols ``N2LS8Tracking21NaturalFeatureTrackerE``,
  ``LS::Tracking::NaturalFeatureTracker`` and the literal
  ``/NaturalFeatureTracker/SystemData`` path, plus the Java
  ``FaceTrackerWrapper`` entry point.

The implementation covers the classic ORB chain: FAST-9 corners + score,
intensity-centroid orientation over radius 10 (``r-10`` in the asset name),
steered BRIEF-256 descriptors (``b-20``: 20x20 patch sampling, ``d-2``
vocabulary-tree depth), Hamming matching with a ratio test, and a
translation estimate from the matched keypoints.  Descriptor sampling uses
the shipped vocabulary-tree centroids for quantisation but a deterministic
Gaussian BRIEF pattern for description (OpenCV's exact 256-pair table is not
present in the APK; that parameterisation is ours and is stated as such).
"""

from __future__ import annotations

import numpy as np
import jax
import jax.numpy as jnp

FAST_OFFSETS = jnp.array([
    (0, -3), (1, -3), (2, -2), (3, -1), (3, 0), (3, 1), (2, 2), (1, 3),
    (0, 3), (-1, 3), (-2, 2), (-3, 1), (-3, 0), (-3, -1), (-2, -2), (-1, -3),
], jnp.int32)


# --------------------------------------------------------------------------
# FAST-9
# --------------------------------------------------------------------------
@jax.jit
def _fast_scores(img: jnp.ndarray, thr: float):
    h, w = img.shape
    p = img[3:h - 3, 3:w - 3]
    ring = jnp.stack([img[3 + dy:h - 3 + dy, 3 + dx:w - 3 + dx]
                      for dx, dy in np.asarray(FAST_OFFSETS)], 0)  # (16,H-6,W-6)
    bright = ring > (p[None] + thr)
    dark = ring < (p[None] - thr)
    # contiguous run of >= 9 over the cyclic 16 ring
    best_b = jnp.zeros(p.shape, jnp.bool_)
    best_d = jnp.zeros(p.shape, jnp.bool_)
    for s in range(16):
        run_b = jnp.ones_like(p, bool)
        run_d = jnp.ones_like(p, bool)
        for k in range(9):
            idx = (s + k) % 16
            run_b = run_b & bright[idx]
            run_d = run_d & dark[idx]
        best_b = best_b | run_b
        best_d = best_d | run_d
    corner = best_b | best_d
    # FAST score: max over the ring of |I(p_i) - I(p)| for the winning polarity
    diff = jnp.abs(ring - p[None])
    score = jnp.where(corner, diff.sum(0), 0.0)
    return corner, score


def _nms(score: jnp.ndarray, radius: int = 3):
    """3x3 (radius 1) or larger non-maximum suppression."""
    pad = jnp.pad(score, radius, mode="constant", constant_values=-1.0)
    out = jnp.zeros_like(score, bool)
    mx = score
    for dy in range(-radius, radius + 1):
        for dx in range(-radius, radius + 1):
            if dx == 0 and dy == 0:
                continue
            shifted = pad[radius + dy:radius + dy + score.shape[0],
                          radius + dx:radius + dx + score.shape[1]]
            mx = jnp.maximum(mx, shifted)
    out = score >= mx
    return out & (score > 0)


def detect_fast(img: np.ndarray, thr: float = 20.0, nms_radius: int = 3,
                max_keypoints: int = 400):
    """FAST-9 corners with NMS; returns (K,2) x,y float32 and scores."""
    corner, score = _fast_scores(jnp.asarray(img, jnp.float32), thr)
    keep = _nms(score, nms_radius) & corner
    ys, xs = jnp.nonzero(keep, size=max_keypoints, fill_value=-1)
    sc = score[ys.clip(0), xs.clip(0)]
    valid = ys >= 0
    ys = np.asarray(ys)[np.asarray(valid)]
    xs = np.asarray(xs)[np.asarray(valid)]
    sc = np.asarray(sc)[np.asarray(valid)]
    order = np.argsort(-sc)
    kp = np.stack([xs + 3, ys + 3], 1).astype(np.float32)[order]
    return kp, sc[order]


# --------------------------------------------------------------------------
# Orientation + BRIEF
# --------------------------------------------------------------------------
def _bilinear(img: np.ndarray, x: np.ndarray, y: np.ndarray):
    h, w = img.shape
    x = np.clip(x, 0, w - 1.001)
    y = np.clip(y, 0, h - 1.001)
    x0 = np.floor(x).astype(np.int32)
    y0 = np.floor(y).astype(np.int32)
    fx = x - x0
    fy = y - y0
    top = img[y0, x0] * (1 - fx) + img[y0, x0 + 1] * fx
    bot = img[y0 + 1, x0] * (1 - fx) + img[y0 + 1, x0 + 1] * fx
    return top * (1 - fy) + bot * fy


def orientations(img: np.ndarray, kp: np.ndarray, radius: int = 10):
    """Intensity centroid orientation, radius 10 (asset 'r-10')."""
    if len(kp) == 0:
        return np.zeros(0, np.float32)
    off = np.arange(-radius, radius + 1)
    gx, gy = np.meshgrid(off, off)
    mask = (gx * gx + gy * gy) <= radius * radius
    gx, gy = gx[mask], gy[mask]
    xs = kp[:, 0:1] + gx[None]
    ys = kp[:, 1:2] + gy[None]
    vals = _bilinear(img.astype(np.float32), xs, ys)
    m01 = (vals * gy[None]).sum(1)
    m10 = (vals * gx[None]).sum(1)
    return np.arctan2(m01, m10).astype(np.float32)


def brief_pattern(nbits: int = 256, patch: int = 20, seed: int = 0):
    """Deterministic Gaussian BRIEF pattern inside the patch."""
    rng = np.random.default_rng(seed)
    sigma = patch / 5.0
    half = patch / 2.0
    p = np.clip(rng.normal(0, sigma, (nbits, 2, 2)), -half, half)
    return p.astype(np.float32)


def describe(img: np.ndarray, kp: np.ndarray, angles: np.ndarray,
             pattern: np.ndarray):
    """Steered BRIEF-256; returns (K, 32) uint8 bit-packed descriptors."""
    if len(kp) == 0:
        return np.zeros((0, 32), np.uint8)
    cos, sin = np.cos(angles), np.sin(angles)
    out = np.zeros((len(kp), pattern.shape[0]), np.uint8)
    imgf = img.astype(np.float32)
    for b in range(pattern.shape[0]):
        (x1, y1), (x2, y2) = pattern[b]
        rx1 = cos * x1 - sin * y1
        ry1 = sin * x1 + cos * y1
        rx2 = cos * x2 - sin * y2
        ry2 = sin * x2 + cos * y2
        v1 = _bilinear(imgf, kp[:, 0] + rx1, kp[:, 1] + ry1)
        v2 = _bilinear(imgf, kp[:, 0] + rx2, kp[:, 1] + ry2)
        out[:, b] = (v1 < v2).astype(np.uint8)
    return np.packbits(out, axis=1)


# --------------------------------------------------------------------------
# Hamming matching
# --------------------------------------------------------------------------
@jax.jit
def _hamming_matrix(d1: jnp.ndarray, d2: jnp.ndarray):
    x = jnp.bitwise_xor(d1[:, None, :], d2[None, :, :]).astype(jnp.uint8)
    pc = jnp.zeros(x.shape[:2], jnp.int32)
    for k in range(8):
        pc = pc + ((x >> k) & 1).sum(-1)
    return pc


def match(d1: np.ndarray, d2: np.ndarray, ratio: float = 0.8,
          max_dist: int = 64):
    """Brute-force Hamming matching with Lowe ratio + mutual check."""
    if len(d1) < 2 or len(d2) < 2:
        return np.zeros((0, 2), np.int32), np.zeros(0, np.int32)
    dist = np.asarray(_hamming_matrix(jnp.asarray(d1), jnp.asarray(d2)))
    order = np.argsort(dist, 1)
    best = order[:, 0]
    bd = dist[np.arange(len(d1)), best]
    sd = dist[np.arange(len(d1)), order[:, 1]]
    ok = (bd <= ratio * sd) & (bd <= max_dist)
    # mutual nearest neighbour
    back = np.argmin(dist, 0)
    ok &= back[best] == np.arange(len(d1))
    idx = np.nonzero(ok)[0]
    return np.stack([idx, best[idx]], 1), bd[idx]


def estimate_translation(kp1: np.ndarray, kp2: np.ndarray, matches: np.ndarray,
                         bin_size: float = 2.0):
    """Translation between two frames via a displacement-mode vote."""
    if len(matches) == 0:
        return np.zeros(2, np.float32), 0
    d = kp2[matches[:, 1]] - kp1[matches[:, 0]]
    keys = np.round(d / bin_size).astype(np.int32)
    uniq, counts = np.unique(keys, axis=0, return_counts=True)
    mode = uniq[np.argmax(counts)]
    inl = np.all(keys == mode, axis=1)
    return d[inl].mean(0).astype(np.float32), int(inl.sum())


# --------------------------------------------------------------------------
# Shipped vocabulary tree
# --------------------------------------------------------------------------
def tree_traverse(descriptor: np.ndarray, centroids: list[bytes],
                  parent: np.ndarray):
    """Descend the shipped ORB tree (Hamming nearest child at each level)."""
    def hamming(a, b):
        return int(np.unpackbits(np.frombuffer(a, np.uint8) ^
                                 np.frombuffer(b, np.uint8)).sum())

    node = 0
    while True:
        kids = [i for i in range(len(parent)) if parent[i] == node and i != node]
        if not kids:
            return node
        node = min(kids, key=lambda k: hamming(descriptor, centroids[k]))
