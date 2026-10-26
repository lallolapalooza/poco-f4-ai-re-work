"""JAX re-implementation of the integer Haar cascade stage used by Looksery.

The cascade weights are the real ones shipped in the APK
(``assets/looksery/core/HaarCascades/haarcascade_frontalface_alt2.xml``,
20x20 Lienhart gentle-AdaBoost frontal face detector: 20 stages, 1047
depth-2 tree classifiers, 4535 weighted rectangles).  libcamplat references
exactly ``/HaarCascades/haarcascade_frontalface_alt2.xml`` and carries
OpenCV's cascade parser strings, so OpenCV semantics are the reference.

Evaluation semantics (OpenCV ``HaarEvaluator``):

* integral image over the uint8 grayscale frame (int32),
* rectangle sum ``ii[y0,x0] - ii[y0,x1] - ii[y1,x0] + ii[y1,x1]``,
* feature value ``sum(weight_i * rectsum_i) / (window_w * window_h)`` with
  the base 20x20 rectangles scaled and rounded per window,
* tree split ``value < threshold -> left``, leaves contribute their value,
* stage rejected when the sum of tree values is below ``stage_threshold``.

Every tree in this cascade has two nodes (one split with a leaf on one side,
a second split with two leaves on the other), so the whole forest is compiled
into constant, gather-free JAX kernels -- one jit per cascade stage.
Only candidate rejection between stages happens on the host.
"""

from __future__ import annotations

import numpy as np
import jax
import jax.numpy as jnp

import looksery_assets as la

MAX_RECTS = 3


class HaarCascade:
    def __init__(self, cascade: dict | None = None):
        cascade = cascade or la.parse_haar()
        self.base_w = cascade["width"]
        self.base_h = cascade["height"]
        self.stages = []
        for stage in cascade["stages"]:
            trees = []
            for t in stage["trees"]:
                if len(t["thresholds"]) != 2:      # 1047/1047 in this cascade
                    raise NotImplementedError("expected two-node trees")
                r0 = np.zeros((MAX_RECTS, 5), np.float32)
                r1 = np.zeros((MAX_RECTS, 5), np.float32)
                m0 = np.zeros(MAX_RECTS, np.float32)
                m1 = np.zeros(MAX_RECTS, np.float32)
                for i, (off, cnt) in enumerate(zip(t["feat_off"], t["feat_cnt"])):
                    dst = r0 if i == 0 else r1
                    msk = m0 if i == 0 else m1
                    for k in range(cnt):
                        dst[k] = t["rects"][off + k]
                        msk[k] = 1.0
                trees.append(dict(
                    thr0=float(t["thresholds"][0]), thr1=float(t["thresholds"][1]),
                    lval0=float(t["lval"][0]), rval0=float(t["rval"][0]),
                    lval1=float(t["lval"][1]), rval1=float(t["rval"][1]),
                    root_left_is_leaf=bool(t["left"][0] < 0),
                    r0=jnp.asarray(r0), r1=jnp.asarray(r1),
                    m0=jnp.asarray(m0), m1=jnp.asarray(m1),
                ))
            self.stages.append(dict(threshold=float(stage["threshold"]), trees=trees))
        self.n_stages = len(self.stages)
        self.trees_per_stage = [len(s["trees"]) for s in self.stages]


def integral_image(img: np.ndarray) -> np.ndarray:
    """int32 integral image with a zero border, shape (H+1, W+1)."""
    ii = np.zeros((img.shape[0] + 1, img.shape[1] + 1), np.int32)
    ii[1:, 1:] = np.cumsum(np.cumsum(img.astype(np.int32), axis=0), axis=1)
    return ii


def make_windows(img_w: int, img_h: int, base_w: int, base_h: int,
                 scale_factor: float = 1.1, min_size: int = 24,
                 max_size: int = 0, stride: int = 1) -> np.ndarray:
    """Multi-scale sliding-window list (x, y, w, h), OpenCV scale loop."""
    out = []
    scale = 1.0
    while True:
        w = int(round(base_w * scale))
        h = int(round(base_h * scale))
        if w > img_w or h > img_h or (max_size and w > max_size):
            break
        if w >= min_size:
            xs = np.arange(0, img_w - w + 1, stride, dtype=np.int32)
            ys = np.arange(0, img_h - h + 1, stride, dtype=np.int32)
            gx, gy = np.meshgrid(xs, ys)
            out.append(np.stack([gx.ravel(), gy.ravel(),
                                 np.full(gx.size, w, np.int32),
                                 np.full(gx.size, h, np.int32)], 1))
        scale *= scale_factor
    if not out:
        return np.zeros((0, 4), np.int32)
    return np.concatenate(out, 0)


def _feature_batch(win, rects, mask, base_w, base_h, ii):
    """Feature values for M windows x T trees x R rectangles, fully batched."""
    x = win[:, 0:1]
    y = win[:, 1:2]
    w = win[:, 2:3]
    h = win[:, 3:4]
    sx = w / base_w
    sy = h / base_h
    rx = jnp.rint(rects[None, :, :, 0] * sx[:, :, None])
    ry = jnp.rint(rects[None, :, :, 1] * sy[:, :, None])
    rw = jnp.rint(rects[None, :, :, 2] * sx[:, :, None])
    rh = jnp.rint(rects[None, :, :, 3] * sy[:, :, None])
    x0 = (x[:, :, None] + rx).astype(jnp.int32)
    y0 = (y[:, :, None] + ry).astype(jnp.int32)
    x1 = (x[:, :, None] + rx + rw).astype(jnp.int32)
    y1 = (y[:, :, None] + ry + rh).astype(jnp.int32)
    s = (ii[y0, x0] - ii[y0, x1] - ii[y1, x0] + ii[y1, x1]).astype(jnp.float32)
    total = (mask[None] * rects[None, :, :, 4] * s).sum(-1)
    return total / (w * h).astype(jnp.float32)


@jax.jit
def _stage_kernel(ii, win, thr0, thr1, lval0, rval0, lval1, rval1,
                  root_left_is_leaf, r0, r1, m0, m1, base_w, base_h):
    v0 = _feature_batch(win, r0, m0, base_w, base_h, ii)   # (M, T)
    v1 = _feature_batch(win, r1, m1, base_w, base_h, ii)   # (M, T)
    go_left0 = v0 < thr0[None]
    val_node1 = jnp.where(v1 < thr1[None], lval1[None], rval1[None])
    # exactly one root branch is a leaf; the other descends to node 1
    val = jnp.where(root_left_is_leaf[None],
                    jnp.where(go_left0, lval0[None], val_node1),
                    jnp.where(go_left0, val_node1, rval0[None]))
    return val.sum(1)


class _Stage:
    """Constant parameter bundle for one cascade stage (jit-friendly)."""

    def __init__(self, stage):
        self.thr0 = jnp.asarray([t["thr0"] for t in stage["trees"]], jnp.float32)
        self.thr1 = jnp.asarray([t["thr1"] for t in stage["trees"]], jnp.float32)
        self.lval0 = jnp.asarray([t["lval0"] for t in stage["trees"]], jnp.float32)
        self.rval0 = jnp.asarray([t["rval0"] for t in stage["trees"]], jnp.float32)
        self.lval1 = jnp.asarray([t["lval1"] for t in stage["trees"]], jnp.float32)
        self.rval1 = jnp.asarray([t["rval1"] for t in stage["trees"]], jnp.float32)
        self.root_left_is_leaf = jnp.asarray(
            [t["root_left_is_leaf"] for t in stage["trees"]], jnp.bool_)
        self.r0 = jnp.stack([t["r0"] for t in stage["trees"]])
        self.r1 = jnp.stack([t["r1"] for t in stage["trees"]])
        self.m0 = jnp.stack([t["m0"] for t in stage["trees"]])
        self.m1 = jnp.stack([t["m1"] for t in stage["trees"]])


def stage_scores(cascade: HaarCascade, ii, win, stage_idx: int):
    s = cascade.stages[stage_idx]
    st = s.get("_jit_params")
    if st is None:
        st = _Stage(s)
        s["_jit_params"] = st
    return _stage_kernel(ii, win, st.thr0, st.thr1, st.lval0, st.rval0,
                         st.lval1, st.rval1, st.root_left_is_leaf,
                         st.r0, st.r1, st.m0, st.m1,
                         cascade.base_w, cascade.base_h)


def detect(cascade: HaarCascade, img: np.ndarray, scale_factor: float = 1.1,
           min_size: int = 24, max_size: int = 0, stride: int = 1):
    """Run the full cascade; returns (surviving windows, scores, per-stage counts)."""
    ii = jnp.asarray(integral_image(img))
    wins = make_windows(img.shape[1], img.shape[0], cascade.base_w,
                        cascade.base_h, scale_factor, min_size,
                        max_size=max_size, stride=stride)
    if wins.shape[0] == 0:
        return wins, np.zeros(0, np.float32), []
    win = jnp.asarray(wins)
    counts = []
    scores = np.zeros(0, np.float32)
    for s in range(cascade.n_stages):
        scores = np.asarray(stage_scores(cascade, ii, win, s))
        keep = scores >= cascade.stages[s]["threshold"]
        counts.append(int(keep.sum()))
        win = jnp.asarray(np.asarray(win)[keep])
        if win.shape[0] == 0:
            return np.zeros((0, 4), np.int32), np.zeros(0, np.float32), counts
    return np.asarray(win), scores, counts


def group_rects(wins: np.ndarray, scores: np.ndarray, iou_thr: float = 0.3,
                min_neighbors: int = 3):
    """Greedy IoU grouping roughly equivalent to ``cv::groupRectangles``."""
    if len(wins) == 0:
        return []
    order = np.argsort(-scores)
    used = np.zeros(len(wins), bool)
    out = []
    for i in order:
        if used[i]:
            continue
        bx, by, bw, bh = wins[i]
        cluster = []
        for j in order:
            if used[j]:
                continue
            cx, cy, cw, ch = wins[j]
            inter = max(0, min(bx + bw, cx + cw) - max(bx, cx)) * \
                    max(0, min(by + bh, cy + ch) - max(by, cy))
            union = bw * bh + cw * ch - inter
            if union > 0 and inter / union >= iou_thr:
                used[j] = True
                cluster.append(j)
        if len(cluster) >= min_neighbors:
            c = wins[cluster].astype(np.float64)
            out.append(dict(x=float(c[:, 0].mean()), y=float(c[:, 1].mean()),
                            w=float(c[:, 2].mean()), h=float(c[:, 3].mean()),
                            votes=len(cluster), score=float(scores[cluster].mean())))
    return out
