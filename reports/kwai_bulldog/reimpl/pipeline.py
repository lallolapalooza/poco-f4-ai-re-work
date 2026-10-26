#!/usr/bin/env python3
"""Kwai Bulldog (com.kwai.bulldog 6.0.20.524206) vision pipeline.

Status: ARCHITECTURE_ONLY.

Recovered contracts (all confirmed from artifacts in this report):

  * Java entry point for every ycnn model:
        com.kwai.kscnnrenderlib.KSJNILib.createModel(path, YCNNModelConfig)
        com.kwai.kscnnrenderlib.KSJNILib.renderModel(obj, byte[])
        com.kwai.kscnnrenderlib.KSJNILib.updateModelOut(...)
        com.kwai.kscnnrenderlib.KSJNILib.YCNNGetConfig2Model(...)
    implemented in libykit.so (JNI offsets 0xc81e8 / 0xc8360 / 0xcbee4 /
    0xce798, see native/jni_entrypoints.txt).
  * Camera face detector path:
        FaceDetectorContext.nativeInit(sensorMgr, type, mode)
        FaceDetectorContext.nativeSetData(nativeContext, type, path)
        FaceDetectorContext.nativeDetectFacesFromFrame(nativeContext, frame, type)
    in libfacedetectcontext.so; nativeSetData converts the Java string and
    calls a virtual `SetData(int type, std::string path)` (vtable+0x28).
    The model bytes are supplied by the app as a filesystem path.
  * Input pixel formats accepted by the ycnn crop/converter (libykit.so
    strings): NV21 / NV12 camera frames, grey, plus rgb/bgr float paths.
  * Model config keys parsed from the ycnn header/JSON (libykit.so strings):
    net_w, net_h, input_info, model_type, model_sub_type, anchor_stride,
    nms_th/nms_thresh/nms_method, score_thresh, stride_width/stride_height,
    heatmap, landmark_param, inputMeanVals, input_type, input_channel.
  * Model files: KSModel*.model (102 names) + *_cfg.txt (19 families) are
    *not* in the APK; they are downloaded as named resources
    (magic_ycnn_model_*, voice_detect_mode.tflite) into
    <cache>/magic_effect_resource/<resourceKey>/ with a check.json MD5 list.

Because no weights or per-model graph description are present in the APK
(libykit carries only the runtime + JSON config tables), the detector below
implements the *recovered I/O contract* with deterministic random weights.
It is not Kuaishou's network and must not be used to claim numeric parity.

The KVE (libvisionengine.so) stage is a second, non-neural stage whose
feature names are taken verbatim from the recovered feature enum
(kSharpness, kColorfulness, kJunk, kLowLight, kSceneChange, ...). The
formulas are deterministic image statistics, marked INFERRED.
"""

from __future__ import annotations

import argparse
import json
import sys

import jax
import jax.numpy as jnp
import numpy as np

# --------------------------------------------------------------------------
# Recovered constants
# --------------------------------------------------------------------------

# libykit.so string scans (see evidence/cmd.txt)
SUPPORTED_CAMERA_FORMATS = ("nv21", "nv12")
DEFAULT_MEAN_VALS = (0.0, 0.0, 0.0)  # inputMeanVals, per-model in ycnn header
NUM_LANDMARKS = 68  # "68" landmark config table embedded in libykit.so
LANDMARK_COORDS = NUM_LANDMARKS * 2

# KVE feature ids recovered from libvisionengine.so
KVE_FEATURES = (
    "kSharpness",
    "kFaceNumber",
    "kFaceSharpness",
    "kColorfulness",
    "kIntraEntropy",
    "kJunk",
    "kLowLight",
    "kMotionScore",
    "kSceneChange",
    "kSaliencyPortion",
    "kPureColor",
    "kBadCover",
    "kNoTexture",
)


# --------------------------------------------------------------------------
# Stage 0: camera frame -> float RGB  (NV21 / NV12, BT.601 limited range)
# --------------------------------------------------------------------------


def nv21_to_rgb(frame: np.ndarray, width: int, height: int, fmt: str = "nv21") -> jnp.ndarray:
    """Convert a YUV420sp camera frame to float32 RGB in [0, 1].

    Matches the arithmetic of libyuv's I420ToRAW / the ycnn converter path
    (`ycnn::YCNNCropDataMatrix(..., KSY_COLOR_TYPE, KSY_COLOR_RANGE_TYPE)`)
    with limited-range BT.601 coefficients. NV12 swaps U/V.
    """
    frame = np.asarray(frame, dtype=np.uint8)
    need = width * height * 3 // 2
    if frame.size < need:
        raise ValueError(f"{fmt} frame too small: {frame.size} < {need}")
    y = frame[: width * height].reshape(height, width).astype(np.float32)
    uv = frame[width * height : need].reshape(height // 2, width // 2, 2).astype(np.float32)
    u = np.repeat(np.repeat(uv[..., 1 if fmt == "nv21" else 0], 2, axis=0), 2, axis=1)
    v = np.repeat(np.repeat(uv[..., 0 if fmt == "nv21" else 1], 2, axis=0), 2, axis=1)
    y = (y - 16.0) * (255.0 / 219.0) / 255.0
    u = (u - 128.0) * (255.0 / 224.0) / 255.0
    v = (v - 128.0) * (255.0 / 224.0) / 255.0
    r = y + 1.402 * v
    g = y - 0.344136 * u - 0.714136 * v
    b = y + 1.772 * u
    return jnp.clip(jnp.stack([r, g, b], axis=-1), 0.0, 1.0)


# --------------------------------------------------------------------------
# Stage 1: ycnn preprocessing (crop -> resize -> mean subtraction)
# --------------------------------------------------------------------------


def preprocess(
    rgb: jnp.ndarray,
    net_hw: tuple[int, int] = (128, 128),
    mean_vals: tuple[float, float, float] = DEFAULT_MEAN_VALS,
    crop: tuple[float, float, float, float] | None = None,
) -> jnp.ndarray:
    """NHWC float image -> (1, net_h, net_w, 3).

    `crop` is (x0, y0, x1, y1) in pixels like `YCNNCropDataMatrix`'s KSYRect;
    None means full frame. Bilinear resize, then per-channel mean subtraction
    (inputMeanVals from the ycnn config; typically 0 or 127.5).
    """
    if crop is not None:
        h, w = rgb.shape[:2]
        x0, y0, x1, y1 = crop
        x0 = int(max(0, min(round(x0), w - 2)))
        y0 = int(max(0, min(round(y0), h - 2)))
        x1 = int(max(x0 + 1, min(round(x1), w)))
        y1 = int(max(y0 + 1, min(round(y1), h)))
        rgb = rgb[y0:y1, x0:x1]
    x = jax.image.resize(rgb, (net_hw[0], net_hw[1], rgb.shape[-1]), method="bilinear")
    x = x[None, ...]
    return x - jnp.asarray(mean_vals, dtype=jnp.float32)


# --------------------------------------------------------------------------
# Stage 2: ycnn-style detector trunk (ARCHITECTURE_ONLY, random weights)
# --------------------------------------------------------------------------


def init_params(key, net_hw=(128, 128), width=32, num_landmarks=NUM_LANDMARKS):
    """Deterministic random init in the shape expected by `apply`."""
    k = jax.random.split(key, 5)
    w = width
    return {
        "stem": jax.random.normal(k[0], (3, 3, 3, w)) * 0.05,
        "blk1": jax.random.normal(k[1], (3, 3, w, w * 2)) * 0.03,
        "blk2": jax.random.normal(k[2], (3, 3, w * 2, w * 4)) * 0.02,
        "score": jax.random.normal(k[3], (1, 1, w * 4, 1)) * 0.01,
        "landmark": jax.random.normal(k[4], (1, 1, w * 4, num_landmarks * 2)) * 0.01,
    }


def _conv_block(x, w, stride):
    x = jax.lax.conv_general_dilated(
        x,
        w,
        window_strides=(stride, stride),
        padding="SAME",
        dimension_numbers=("NHWC", "HWIO", "NHWC"),
    )
    return jax.nn.relu(x)


def apply(params, x):
    """x: (1, H, W, 3) float32.

    Returns dict:
      score    (1, h, w, 1)      sigmoid face-ness heatmap
      landmark (1, h, w, 2K)     per-cell landmark regressions (normalised)
    The real ycnn graph is model-specific and downloaded; only the tensor
    contract (heatmap + per-landmark regression, stride_width/stride_height,
    score_thresh) is recovered.
    """
    f = _conv_block(x, params["stem"], 2)
    f = _conv_block(f, params["blk1"], 2)
    f = _conv_block(f, params["blk2"], 2)
    score = jax.nn.sigmoid(_conv_block(f, params["score"], 1))
    landmark = _conv_block(f, params["landmark"], 1)
    return {"score": score, "landmark": landmark}


def decode(score, landmark, score_thresh=0.3, num_landmarks=NUM_LANDMARKS):
    """Heatmap argmax -> (x, y) in [0,1] plus per-landmark offsets.

    Mirrors the ycnn decode knobs score_thresh / nms_th / anchor_stride: one
    best cell is selected here (the face pipeline runs NMS on CPU afterwards).
    """
    h, w = score.shape[1], score.shape[2]
    flat = score.reshape(-1)
    idx = int(jnp.argmax(flat))
    best = float(flat[idx])
    cy, cx = idx // w, idx % w
    pts = landmark.reshape(h, w, num_landmarks, 2)[cy, cx]
    coarse = jnp.array([(cx + 0.5) / w, (cy + 0.5) / h], dtype=jnp.float32)
    # offsets are in cells -> normalise
    offsets = pts * jnp.array([1.0 / w, 1.0 / h], dtype=jnp.float32)
    return {
        "score": best,
        "valid": bool(best >= score_thresh),
        "cell": (int(cx), int(cy)),
        "center": [float(v) for v in coarse],
        "landmarks": [[float(a), float(b)] for a, b in (coarse + offsets)],
    }


# --------------------------------------------------------------------------
# Stage 3: KVE video-feature stage (libvisionengine.so feature enum)
# --------------------------------------------------------------------------


def kve_features(rgb_prev: jnp.ndarray, rgb_cur: jnp.ndarray) -> dict:
    """Deterministic feature statistics named after the kve::Feature enum.

    INFERRED formulas (the Halide DAGs face_score_dag / face_laplacian_dag /
    compute_saliency_area are compiled, not readable as source):
      kSharpness      variance of the 4-neighbour Laplacian
      kColorfulness   Hasler-Suesstrunk metric
      kLowLight       fraction of pixels with luma < 0.25
      kPureColor      fraction of pixels within 1/255 of their 3x3 mean
      kIntraEntropy   Shannon entropy of the 8-bit luma histogram
      kJunk           share of saturated (>=0.99) or crushed (<=0.01) pixels
      kSceneChange    mean absolute luma difference between two frames
    """
    x = jnp.asarray(rgb_cur)
    g = 0.299 * x[..., 0] + 0.587 * x[..., 1] + 0.114 * x[..., 2]

    lap = (
        -4.0 * g[1:-1, 1:-1]
        + g[:-2, 1:-1]
        + g[2:, 1:-1]
        + g[1:-1, :-2]
        + g[1:-1, 2:]
    )

    rg = x[..., 0] - x[..., 1]
    yb = 0.5 * (x[..., 0] + x[..., 1]) - x[..., 2]
    colorful = jnp.sqrt(jnp.std(rg) ** 2 + jnp.std(yb) ** 2) + 0.3 * jnp.sqrt(
        jnp.mean(rg) ** 2 + jnp.mean(yb) ** 2
    )

    q = jnp.clip(g * 255.0, 0, 255).astype(jnp.int32)
    hist = jnp.bincount(q.reshape(-1), length=256).astype(jnp.float32)
    p = hist / jnp.maximum(hist.sum(), 1.0)
    entropy = -jnp.sum(jnp.where(p > 0, p * jnp.log2(jnp.maximum(p, 1e-12)), 0.0))

    pad = jnp.pad(x, ((1, 1), (1, 1), (0, 0)), mode="edge")
    box = (
        pad[:-2, :-2] + pad[:-2, 1:-1] + pad[:-2, 2:]
        + pad[1:-1, :-2] + pad[1:-1, 1:-1] + pad[1:-1, 2:]
        + pad[2:, :-2] + pad[2:, 1:-1] + pad[2:, 2:]
    ) / 9.0

    prev_g = 0.299 * rgb_prev[..., 0] + 0.587 * rgb_prev[..., 1] + 0.114 * rgb_prev[..., 2]
    return {
        "kSharpness": float(jnp.var(lap)),
        "kColorfulness": float(colorful),
        "kLowLight": float(jnp.mean(g < 0.25)),
        "kPureColor": float(jnp.mean(jnp.max(jnp.abs(x - box), axis=-1) <= 1.0 / 255.0)),
        "kIntraEntropy": float(entropy),
        "kJunk": float(jnp.mean((x >= 0.99) | (x <= 0.01))),
        "kSceneChange": float(jnp.mean(jnp.abs(g - prev_g))),
    }


# --------------------------------------------------------------------------
# Self-test
# --------------------------------------------------------------------------


def _synthetic_frame(width, height, seed, fmt="nv21"):
    yy, xx = np.mgrid[0:height, 0:width]
    phase = float(seed % 11)
    luma = (
        128
        + 60 * np.sin((xx + 9 * phase) / 17.0)
        + 40 * np.cos((yy - 5 * phase) / 23.0)
        + 25 * ((xx + yy + 3 * phase) % 2 == 0)
    ).clip(16, 235)
    y = luma.astype(np.uint8)
    u = np.full((height // 2, width // 2), 110, np.uint8)
    v = np.full((height // 2, width // 2), 140, np.uint8)
    uv = np.stack([v, u] if fmt == "nv21" else [u, v], axis=-1).reshape(-1)
    return jnp.asarray(np.concatenate([y.reshape(-1), uv]))


def main(argv=None):
    p = argparse.ArgumentParser(description=__doc__)
    p.add_argument("--width", type=int, default=640)
    p.add_argument("--height", type=int, default=480)
    p.add_argument("--net", type=int, default=128, help="square network input size")
    p.add_argument("--seed", type=int, default=0)
    p.add_argument("--json", action="store_true", help="emit machine-readable summary")
    args = p.parse_args(argv)

    hw = (args.net, args.net)

    frame = _synthetic_frame(args.width, args.height, args.seed)
    print(f"input frame : nv21 {args.width}x{args.height} ({frame.size} bytes)")

    rgb = nv21_to_rgb(np.asarray(frame), args.width, args.height)
    print(f"stage0 rgb  : {rgb.shape} {rgb.dtype} range=({float(rgb.min()):.3f},{float(rgb.max()):.3f})")

    x = preprocess(rgb, hw)
    print(f"stage1 input: {x.shape} {x.dtype} mean={[float(v) for v in jnp.mean(x, axis=(0, 1, 2))]}")

    params = init_params(jax.random.PRNGKey(args.seed), hw)
    out = apply(params, x)
    for k, v in out.items():
        print(f"stage2 {k:8s}: {v.shape} {v.dtype}")

    det = decode(out["score"], out["landmark"])
    print(f"stage2 decode: valid={det['valid']} score={det['score']:.4f} cell={det['cell']} landmarks={len(det['landmarks'])}")

    frame2 = _synthetic_frame(args.width, args.height, args.seed + 1)
    rgb2 = nv21_to_rgb(np.asarray(frame2), args.width, args.height)
    feats = kve_features(rgb, rgb2)
    print("stage3 kve  : " + " ".join(f"{k}={v:.4f}" for k, v in feats.items()))

    if args.json:
        print(json.dumps({
            "frame_bytes": int(frame.size),
            "rgb_shape": list(rgb.shape),
            "net_input_shape": list(x.shape),
            "score_shape": list(out["score"].shape),
            "landmark_shape": list(out["landmark"].shape),
            "detection": det,
            "kve_features": feats,
        }, indent=1))

    print("STATUS: ARCHITECTURE_ONLY (no weights present in APK; contracts recovered)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
