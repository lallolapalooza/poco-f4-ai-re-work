#!/usr/bin/env python3
"""Input construction for the miui_mediaeditor model families.

The FuAI engine never binds these tensors from Java: per bundle ``config.json``
it crops/aligns the face (``mean_shape`` + ``netin_image_expand_udlr_ratio``),
resizes to ``image_width`` x ``image_height`` and copies into the network input
(``PIPELINE.md`` §3/§4.3).  The float normalisation constant is *not* in the
recovered configs (PIPELINE §3 step 5, unknown U3), so the runners use the
TFLite-typical ``x/255`` and record that choice in ``demo_report.json``.

For a single-image model the supplied photo (or the synthetic demo frame) is
resized into the network input.  Stage models (``s2``, ``s3``, the refine nets,
``dense_eye_iris``, ``dense_mouth``) take auxiliary landmark/heatmap/feature
tensors that in the app come from the preceding pipeline stage; those are
filled deterministically from the same frame so the graph still executes with
real weights, and the report marks them ``placeholder``.
"""

from __future__ import annotations

import numpy as np

from image_io import resize_bilinear

_IMAGE_NAMES = {
    "image", "inputs", "input", "input_1", "input_layer", "input_tensor",
    "Input_img", "input_image", "serving_default_image:0",
}
_LANDMARK_NAMES = {
    "eye_s1", "upper_s1", "dense_s1", "inputs_lms",
    "serving_default_inputs_landmark:0",
}


def _luma(img: np.ndarray) -> np.ndarray:
    if img.ndim == 2:
        return img.astype(np.float32)
    return (
        0.299 * img[..., 0] + 0.587 * img[..., 1] + 0.114 * img[..., 2]
    ).astype(np.float32)


def _channels(img: np.ndarray, channels: int) -> np.ndarray:
    if channels == 1:
        lum = _luma(img)
        return lum[..., None]
    if img.ndim == 2:
        return np.repeat(img[..., None], channels, axis=2).astype(np.float32)
    if img.shape[2] == channels:
        return img.astype(np.float32)
    if img.shape[2] == 1:
        return np.repeat(img, channels, axis=2).astype(np.float32)
    return img[..., :channels].astype(np.float32)


def role_for(name: str, shape: tuple[int, ...]) -> str:
    """Classify a graph input into an input role."""
    low = name.lower()
    if name == "Input_img":
        return "image_uint8"
    if name in _LANDMARK_NAMES or low.startswith("lm_") or low.startswith("inputs_landmark"):
        return "landmarks"
    if low.startswith("ht_") or low.endswith("_ht") or low.startswith("inputs_ht"):
        return "heatmap"
    if low.startswith("inputs_f") or low.startswith("serving_default_inputs_f"):
        return "features"
    if low.startswith("inputs_") and len(shape) == 4:
        return "crop"
    if len(shape) == 4 and (name in _IMAGE_NAMES or low.startswith("serving_default_inputs:")):
        return "image"
    if len(shape) == 4:
        if shape[-1] == 3:
            return "image"
        return "heatmap" if shape[-1] == 1 else "features"
    if len(shape) == 3 and shape[-1] == 2:
        return "landmarks"
    if len(shape) == 2:
        return "vector"
    return "flat"


def _landmark_placeholder(n: int) -> np.ndarray:
    """Deterministic 2-D point set in the unit crop square.

    A face-landmark-shaped ellipse is used instead of random noise so that the
    downstream consumer (and the printed ranges) is interpretable; in the app
    these values come from the previous landmark stage.
    """
    t = (np.arange(n, dtype=np.float32) + 0.5) / max(n, 1) * (2 * np.pi)
    ring = (np.arange(n) % 3) / 2.0  # three concentric rings
    x = 0.5 + (0.20 + 0.06 * ring) * np.cos(t)
    y = 0.5 + (0.26 + 0.05 * ring) * np.sin(t)
    return np.stack([x, y], axis=1).astype(np.float32)[None]


def build_inputs(spec, input_specs, image: np.ndarray) -> tuple[list[np.ndarray], dict]:
    """Build every graph input for ``spec`` from one frame.

    ``input_specs`` are ``tflite_ir.TensorIR``-like objects (``.name``,
    ``.shape``, ``.dtype``).  Returns ``(arrays, meta)`` where ``meta`` records
    the role and provenance of each input.
    """
    arrays: list[np.ndarray] = []
    meta: dict[str, dict] = {}
    for ts in input_specs:
        shape = tuple(int(d) for d in ts.shape)
        role = role_for(ts.name, shape)
        if role in ("image", "image_uint8", "heatmap", "crop"):
            if len(shape) != 4:
                raise ValueError(f"{spec.name}: unexpected {role} rank {shape}")
            _, h, w, c = shape
            if role == "heatmap":
                arr = _luma(image)[..., None]
            else:
                arr = _channels(image, c)
            resized = resize_bilinear(arr, h, w)[None]
            if ts.dtype == "UINT8":
                out = np.clip(np.rint(resized), 0, 255).astype(np.uint8)
            else:
                out = (resized / 255.0).astype(np.float32)
            provenance = "frame resized to %dx%d (%s)" % (w, h, role)
        elif role == "landmarks":
            n = shape[-2] if len(shape) >= 3 else shape[-1] // 2
            pts = _landmark_placeholder(n)
            if ts.dtype == "FLOAT32":
                out = pts.astype(np.float32)
            else:
                out = pts.astype(np.float64)
            provenance = f"{n} deterministic points in the unit crop square (placeholder)"
        elif role == "features":
            if len(shape) == 4:
                _, h, w, c = shape
                base = resize_bilinear(_luma(image)[..., None], h, w)
                out = (base / 255.0).astype(np.float32)
                out = np.repeat(out, c, axis=2)[None]
                provenance = f"frame luma resampled to {h}x{w}x{c} (placeholder feature map)"
            else:
                out = np.zeros(shape, dtype=np.float32)
                provenance = "zeros (placeholder feature vector)"
        elif role == "vector":
            n = int(np.prod(shape))
            base = resize_bilinear(_luma(image), 1, n).reshape(shape)
            out = (base / 255.0).astype(np.float32)
            provenance = f"frame luma resampled to {n}-vector (placeholder features)"
        else:
            out = np.zeros(shape, dtype=np.float32)
            provenance = "zeros (unsupported role)"
        if tuple(out.shape) != shape:
            raise ValueError(
                f"{spec.name}: input {ts.name} built with shape {out.shape}, model wants {shape}"
            )
        arrays.append(out)
        meta[ts.name] = {
            "role": role,
            "shape": list(shape),
            "dtype": ts.dtype,
            "provenance": provenance,
        }
    return arrays, meta


def synthetic_frame(height: int = 256, width: int = 256, seed: int = 0) -> np.ndarray:
    """Deterministic photo-like RGBA-free frame: gradient + soft face ellipse."""
    rng = np.random.RandomState(seed)
    yy, xx = np.mgrid[0:height, 0:width]
    r = (xx / max(width - 1, 1)).astype(np.float32)
    g = (yy / max(height - 1, 1)).astype(np.float32)
    b = 0.5 + 0.5 * np.sin(6.28318 * r) * np.cos(3.14159 * g)
    img = np.stack([r, g, b], axis=2) * 255.0
    cy, cx = height / 2.0, width / 2.0
    ell = ((yy - cy) / (height * 0.32)) ** 2 + ((xx - cx) / (width * 0.24)) ** 2
    img[ell < 1.0] = img[ell < 1.0] * 0.35 + np.array([210, 170, 150]) * 0.65
    img += rng.uniform(-6, 6, size=img.shape)
    return np.clip(img, 0, 255).astype(np.uint8)
