#!/usr/bin/env python3
"""Output decoding for the miui_mediaeditor model families.

Every handler returns a JSON-safe summary.  The contracts come from
``PIPELINE.md`` §5 (mask decoding, landmark indexing) and the bundle
``config.json`` files in ``models/bundles/``:

* parsing: 15-channel softmax, ``cate_mask_order`` maps 19 UI classes onto the
  15 channels (``netout_with_softmax: true``);
* teeth: 4-channel softmax, ``teeth_mask_order = 3``;
* skin: 1-channel probability, ``mask_threshold 0.6``,
  ``keep_block_radio 0.01`` (drop components below 1 % of the face area);
* del-spot: 2-class patch softmax per 24x24 crop;
* landmark nets: flattened 2-D point lists in the (normalised) crop square;
* body: quantised joint heatmaps / POFs / bbfs.

Uncertainties (normalisation constant, landmark coordinate frame) are flagged
in the returned ``"notes"``; nothing here invents an app constant that the APK
does not contain.
"""

from __future__ import annotations

import numpy as np

FPRS_CATE_MASK_ORDER = [
    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0, 0, 0, 14, 0,
]
SKIN_MASK_THRESHOLD = 0.6
SKIN_KEEP_BLOCK_RATIO = 0.01
DEL_SPOT_THRESHOLD = 0.13   # face_del_spot_prepro.thresh_val
TEETH_MASK_ORDER = 3


def mask_threshold(spec) -> tuple[float, str]:
    """Probability threshold for a 1-channel mask, with its config source."""
    if "mole_seg" in spec.name:
        # del-spot segmentation; PIPELINE §5.1 thresh_val (skin's 0.6 belongs to
        # the skin segmenter only)
        return DEL_SPOT_THRESHOLD, "face_del_spot_prepro.thresh_val"
    return SKIN_MASK_THRESHOLD, "face_skin_seg.mask_threshold"


# ---------------------------------------------------------------------------
# helpers
# ---------------------------------------------------------------------------
def _stats(a: np.ndarray) -> dict:
    a = np.asarray(a)
    if a.dtype == np.bool_ or np.issubdtype(a.dtype, np.integer):
        flat = a.astype(np.float64)
    else:
        flat = a.astype(np.float64)
    return {
        "min": float(np.nanmin(flat)) if flat.size else None,
        "max": float(np.nanmax(flat)) if flat.size else None,
        "mean": float(np.nanmean(flat)) if flat.size else None,
        "std": float(np.nanstd(flat)) if flat.size else None,
    }


def _softmax(x: np.ndarray, axis: int = -1) -> np.ndarray:
    x = np.asarray(x, dtype=np.float64)
    m = np.max(x, axis=axis, keepdims=True)
    e = np.exp(x - m)
    return e / np.sum(e, axis=axis, keepdims=True)


def _sigmoid(x: np.ndarray) -> np.ndarray:
    return 1.0 / (1.0 + np.exp(-np.asarray(x, dtype=np.float64)))


def _quant(ts) -> dict:
    q = getattr(ts, "quantization", None)
    if q is None:
        return {"scale": [], "zero_point": []}
    return {
        "scale": list(getattr(q, "scale", []) or []),
        "zero_point": list(getattr(q, "zero_point", []) or []),
        "quantized_dimension": int(getattr(q, "quantized_dimension", 0) or 0),
    }


def _dequantize(arr: np.ndarray, ts) -> np.ndarray:
    a = np.asarray(arr)
    q = _quant(ts)
    if not q["scale"]:
        return a.astype(np.float64)
    scale = np.asarray(q["scale"], dtype=np.float64)
    zp = np.asarray(q["zero_point"] or [0], dtype=np.float64)
    out = a.astype(np.float64)
    if scale.size > 1:
        axis = q["quantized_dimension"] % out.ndim
        shp = [1] * out.ndim
        shp[axis] = scale.size
        scale = scale.reshape(shp)
        zp = zp.reshape(shp) if zp.size > 1 else zp.reshape([1] * out.ndim)
    elif zp.size > 1:
        zp = zp.reshape(-1)[0:1]
    return (out - zp) * scale


def _points(out: np.ndarray) -> np.ndarray:
    a = np.asarray(out, dtype=np.float32)
    return a.reshape(-1, 2)


def _point_summary(out: np.ndarray, net_hw: tuple[int, int] | None) -> dict:
    pts = _points(out)
    res = {
        "n_points": int(pts.shape[0]),
        "xy_mean": [float(pts[:, 0].mean()), float(pts[:, 1].mean())],
        "xy_range": [
            [float(pts[:, 0].min()), float(pts[:, 0].max())],
            [float(pts[:, 1].min()), float(pts[:, 1].max())],
        ],
    }
    lo, hi = float(pts.min()), float(pts.max())
    if lo >= -0.05 and hi <= 1.05:
        # FuAI landmark nets that emit normalised crop coordinates: the pixel
        # view (net input space) is what the face-warp stage consumes.
        res["coord_frame"] = "normalized"
        if net_hw:
            px = pts * np.array([net_hw[1], net_hw[0]], dtype=np.float32)
            res["pixel_xy_first5"] = [[float(v) for v in p] for p in px[:5]]
    elif lo >= 0 and net_hw and hi <= max(net_hw) * 1.1:
        res["coord_frame"] = f"pixels in the {net_hw[1]}x{net_hw[0]} network input"
    else:
        res["coord_frame"] = "network-specific (offsets / heatmap-argmax coords)"
    res["xy_first5"] = [[float(v) for v in p] for p in pts[:5]]
    return res


def _score_summary(logits: np.ndarray) -> dict:
    v = np.asarray(logits, dtype=np.float64).reshape(-1)
    p = _softmax(v)
    return {
        "n_classes": int(v.size),
        "logits": [float(x) for x in v],
        "probs": [float(x) for x in p],
        "argmax": int(np.argmax(p)),
        "confidence": float(p.max()),
    }


def _argmax_map(prob: np.ndarray) -> np.ndarray:
    return np.argmax(prob, axis=-1)


def _largest_components(mask: np.ndarray, keep_ratio: float) -> dict:
    """Keep connected components >= ``keep_ratio`` of the mask area."""
    total = int(mask.sum())
    if total == 0:
        return {"coverage": 0.0, "components": 0, "kept": 0, "kept_ratio": 0.0}
    try:
        from scipy import ndimage  # type: ignore

        labels, n = ndimage.label(mask)
        sizes = np.bincount(labels.ravel())
        sizes[0] = 0
        keep_ids = np.flatnonzero(sizes >= keep_ratio * mask.size)
        kept = int(sizes[keep_ids].sum()) if keep_ids.size else 0
        return {
            "coverage": float(total / mask.size),
            "components": int(n),
            "kept": int(keep_ids.size),
            "kept_pixels": kept,
            "kept_ratio": float(kept / mask.size),
        }
    except Exception:
        return {
            "coverage": float(total / mask.size),
            "components": None,
            "kept": None,
            "kept_ratio": None,
        }


# ---------------------------------------------------------------------------
# family handlers
# ---------------------------------------------------------------------------
def _handle_landmark_like(outputs, out_specs, net_hw):
    reports = {}
    for ts, arr in zip(out_specs, outputs):
        a = np.asarray(arr)
        if a.size == 2 and a.ndim <= 2:
            reports[ts.name] = {"kind": "score", **_score_summary(a)}
        elif a.shape[-1] == 2 and a.size >= 4:
            reports[ts.name] = {"kind": "landmarks", **_point_summary(a, net_hw)}
        else:
            reports[ts.name] = {
                "kind": "feature_map",
                "shape": list(a.shape),
                "range": _stats(a),
            }
    return reports


def _handle_binary_score(outputs, out_specs, net_hw):
    ts, arr = out_specs[0], np.asarray(outputs[0], dtype=np.float64)
    logit = float(arr.reshape(-1)[0])
    # facecheck40 has no fused LOGISTIC: raw regression score, sigmoid applied here
    p = float(_sigmoid(np.array([logit]))[0])
    return {
        ts.name: {
            "kind": "face_score",
            "logit": logit,
            "sigmoid": p,
            "is_face": bool(p >= 0.5),
        }
    }


def _handle_binary_class(outputs, out_specs, net_hw):
    ts, arr = out_specs[0], np.asarray(outputs[0], dtype=np.float64)
    vec = arr.reshape(-1)
    probs = vec if (vec.min() >= 0 and abs(vec.sum() - 1.0) < 1e-3) else _softmax(vec)
    return {
        ts.name: {
            "kind": "class_probs",
            "probs": [float(p) for p in probs],
            "argmax": int(np.argmax(probs)),
            "confidence": float(np.max(probs)),
            "positive": bool(np.argmax(probs) == 1),
        }
    }


def _handle_vector14(outputs, out_specs, net_hw):
    ts, arr = out_specs[0], np.asarray(outputs[0], dtype=np.float64).reshape(-1)
    return {
        ts.name: {
            "kind": "regression_vector",
            "n": int(arr.size),
            "values": [float(v) for v in arr],
            "max_abs": float(np.abs(arr).max()) if arr.size else 0.0,
        }
    }


def _handle_mask_multiclass(spec, outputs, out_specs, net_hw):
    ts = out_specs[0]
    prob = np.asarray(outputs[0], dtype=np.float64)
    if prob.ndim == 4:
        prob = prob[0]
    if prob.shape[-1] == 1:
        prob = np.concatenate([1 - prob, prob], axis=-1)
    cls = _argmax_map(prob)
    n_cls = int(prob.shape[-1])
    counts = np.bincount(cls.ravel(), minlength=n_cls).astype(np.float64)
    fractions = counts / max(counts.sum(), 1)
    res = {
        "kind": "class_mask",
        "shape": list(np.asarray(outputs[0]).shape),
        "n_classes": n_cls,
        "class_fraction": [float(f) for f in fractions],
        "argmax_class": int(np.argmax(fractions)),
    }
    order = FPRS_CATE_MASK_ORDER if n_cls == 15 else None
    if order:
        ui = []
        for ui_idx, ch in enumerate(order):
            if ch < 0:
                continue
            ui.append({"ui_class": ui_idx, "channel": int(ch),
                       "fraction": float(fractions[ch]) if ch < n_cls else 0.0})
        res["cate_mask_order"] = order
        res["ui_class_fraction"] = ui
    if n_cls == 4:
        res["teeth_channel"] = TEETH_MASK_ORDER
        res["teeth_fraction"] = float(fractions[TEETH_MASK_ORDER]) \
            if TEETH_MASK_ORDER < n_cls else None
        res["bg_fraction"] = float(fractions[0])
    return {ts.name: res}


def _handle_mask_binary(spec, outputs, out_specs, net_hw):
    ts = out_specs[0]
    prob = np.asarray(_dequantize(outputs[0], ts), dtype=np.float64)
    prob = prob.reshape(prob.shape[-3], prob.shape[-2]) if prob.ndim == 4 else prob
    threshold, source = mask_threshold(spec)
    mask = prob >= threshold
    comp = _largest_components(mask, SKIN_KEEP_BLOCK_RATIO)
    return {
        ts.name: {
            "kind": "binary_mask",
            "shape": list(np.asarray(outputs[0]).shape),
            "threshold": threshold,
            "threshold_source": source,
            "keep_block_ratio": SKIN_KEEP_BLOCK_RATIO,
            "prob": _stats(prob),
            **comp,
        }
    }


def _handle_image_tanh(spec, outputs, out_specs, net_hw):
    ts = out_specs[0]
    raw = np.asarray(outputs[0], dtype=np.float32)
    img = (raw + 1.0) / 2.0
    return {
        ts.name: {
            "kind": "image",
            "shape": list(raw.shape),
            "tanh_range": _stats(raw),
            "image_range": _stats(img),
            "note": "Tanh generator output mapped [-1,1] -> [0,1]",
        }
    }


def _handle_detector_anchor(spec, outputs, out_specs, net_hw):
    reports = {}
    boxes = None
    for ts, arr in zip(out_specs, outputs):
        a = np.asarray(arr, dtype=np.float64)
        name = ts.name
        if a.shape[-1] == 1:
            flat = a.reshape(-1)
            score = flat if (flat.min() >= 0 and flat.max() <= 1) else _sigmoid(flat)
            k = int(np.argmax(score))
            reports[name] = {
                "kind": "anchor_scores",
                "n_anchors": int(score.size),
                "sigmoid_applied": bool(not (flat.min() >= 0 and flat.max() <= 1)),
                "best_anchor": k,
                "best_score": float(score[k]),
                "score_stats": _stats(score),
            }
        elif a.shape[-1] == 4:
            boxes = a.reshape(-1, 4)
            reports[name] = {
                "kind": "anchor_ltrb",
                "n_anchors": int(boxes.shape[0]),
                "mean": [float(v) for v in boxes.mean(axis=0)],
            }
        else:
            reports[name] = {
                "kind": "anchor_classes",
                "shape": list(a.shape),
                "n_classes": int(a.shape[-1]),
            }
    if boxes is not None and "n_anchors" in next(iter(reports.values())):
        reports["decode"] = {
            "note": "FCOS-style head: box = anchor centre +/- ltrb * stride; the "
                    "per-level stride table lives in the encrypted bundle config, "
                    "so only anchor ids are reported",
        }
    return reports


def _handle_detector_boxes(spec, outputs, out_specs, net_hw):
    reports = {}
    entries = []
    for ts, arr in zip(out_specs, outputs):
        a = np.asarray(arr, dtype=np.float64)
        if a.shape[-1] == 1:
            flat = a.reshape(-1)
            score = flat if (flat.min() >= 0 and flat.max() <= 1) else _sigmoid(flat)
            k = int(np.argmax(score))
            entries.append((ts.name, score, k))
            reports[ts.name] = {
                "kind": "box_scores",
                "n_boxes": int(score.size),
                "best_index": k,
                "best_score": float(score[k]),
                "n_above_0.3": int((score > 0.3).sum()),
            }
        elif a.shape[-1] == 4:
            reports[ts.name] = {
                "kind": "box_regressions",
                "n_boxes": int(a.reshape(-1, 4).shape[0]),
                "mean": [float(v) for v in a.reshape(-1, 4).mean(axis=0)],
                "best_index": int(np.argmax(np.abs(a).sum(axis=-1).reshape(-1))),
            }
        else:
            reports[ts.name] = {"kind": "other", "shape": list(a.shape)}
    if entries:
        name, score, k = entries[0]
        reports["decode"] = {
            "note": "RetinaNet head (scores + 4 box regressions); anchor decode "
                    "needs the per-level stride table that is not in the extracted "
                    "config.json",
            "best_box_index": k,
            "best_score": float(score[k]),
        }
    return reports


def _handle_pose_heatmaps(spec, outputs, out_specs, net_hw):
    reports = {}
    hms = None
    for ts, arr in zip(out_specs, outputs):
        name = ts.name
        raw = np.asarray(arr)
        val = _dequantize(raw, ts)
        if raw.ndim == 4:
            val = val[0]
        entry = {
            "kind": "quantized_output",
            "shape": list(raw.shape),
            "dtype": str(raw.dtype),
            "scale": _quant(ts)["scale"][:1],
            "zero_point": _quant(ts)["zero_point"][:1],
            "range": _stats(val),
        }
        if raw.ndim == 4 and val.shape[-1] > 1 and "hm" in name.lower():
            heat = val
            flat = heat.reshape(-1, heat.shape[-1])
            peaks = np.argmax(flat, axis=0)
            h, w = heat.shape[0], heat.shape[1]
            entry["n_joints"] = int(heat.shape[-1])
            entry["peak_xy"] = [
                [int(p % w), int(p // w)] for p in peaks[:8].tolist()
            ]
            entry["peak_prob"] = [float(np.max(flat[:, j])) for j in range(min(8, flat.shape[1]))]
            hms = entry
        if raw.ndim == 4 and val.shape[-1] == 78:
            entry["pof_layout"] = "26 parts x 3 (dx, dy, score) per pixel"
            entry["n_parts"] = 26
        reports[name] = entry
    if hms is not None:
        reports["decode"] = {
            "note": "joint = argmax over the 64x48 heatmap; POF vectors give the "
                    "sub-pixel offset consumed by the body-reshape mesh"
        }
    return reports


# ---------------------------------------------------------------------------
# dispatch
# ---------------------------------------------------------------------------
_HANDLERS = {
    "landmarks": _handle_landmark_like,
    "landmarks_score": _handle_landmark_like,
    "landmarks_multi": _handle_landmark_like,
    "landmarks_refine": _handle_landmark_like,
    "binary_score": _handle_binary_score,
    "binary_class": _handle_binary_class,
    "vector14": _handle_vector14,
    "mask_multiclass": None,  # signature with spec; bound below
    "mask_binary": None,
    "image_tanh": None,
    "detector_anchor": None,
    "detector_boxes": None,
    "pose_heatmaps": None,
}

_SPEC_HANDLERS = {
    "mask_multiclass": _handle_mask_multiclass,
    "mask_binary": _handle_mask_binary,
    "image_tanh": _handle_image_tanh,
    "detector_anchor": _handle_detector_anchor,
    "detector_boxes": _handle_detector_boxes,
    "pose_heatmaps": _handle_pose_heatmaps,
}


def postprocess(spec, input_specs, out_specs, outputs) -> dict:
    """Decode ``outputs`` of ``spec`` into a JSON-safe summary."""
    net_hw = None
    for ts in input_specs:
        if len(ts.shape) == 4 and ts.shape[-1] in (1, 3) and ts.name in (
            "image", "inputs", "input", "input_1", "input_layer", "input_tensor",
            "Input_img",
        ):
            net_hw = (int(ts.shape[1]), int(ts.shape[2]))
            break
    family = spec.family
    if family in _SPEC_HANDLERS:
        return {
            "family": family,
            "outputs": _SPEC_HANDLERS[family](spec, outputs, out_specs, net_hw),
        }
    handler = _HANDLERS.get(family)
    if handler is None:
        return {
            "family": family,
            "outputs": {
                ts.name: {"kind": "raw", "shape": list(np.asarray(a).shape),
                          "range": _stats(a)}
                for ts, a in zip(out_specs, outputs)
            },
        }
    return {"family": family, "outputs": handler(outputs, out_specs, net_hw)}
