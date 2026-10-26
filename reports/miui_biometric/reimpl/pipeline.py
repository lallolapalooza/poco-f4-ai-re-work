"""JAX reimplementation of the SenseTime FaceAuth pipeline shipped in
com.miui.face (Biometric.apk) as libstfaceunlockocl.so.

STATUS: ARCHITECTURE_ONLY.

The eight CNN models are present in the .so as `__res_<model>_model` blobs
(see ../models/README.md) but every blob is stored encrypted / entropy-coded
(byte entropy ~8.0, no repeating-XOR period, no zlib/lzma/zstd header), and the
key material is only produced at run time by the SenseTime app-protect layer
(protector::ModelLoader + sdk_protector_* RSA/AES/RC4 code).  No weight tensor
could therefore be recovered statically, so this module implements the
*documented* architecture recovered from symbols, model names, Caffe layer
descriptors and Java call sites, with deterministic random initialisation.

Stages (all confirmed from libstfaceunlockocl.so symbols / model names):

  0. preprocess     NV21/YUV -> RGB, orientation, ROI crop, resize 112x112
  1. detect         M_Detect_Hunter_SmallFace_IR_AND_RGB_360.9.2.5  (Hunter, RPN)
  2. align/pose     M_Align_occlusion_106.*  (106 landmarks)
                    M_Align_CalcPose_Ann_106.*  (yaw/pitch/roll)
                    M_Align_Deepface_Eye_State_Analysis.*  (eye open/closed)
  3. quality        M_Attribute_MTNet_Quality.1.0.0
  4. liveness       IM_RGB_Liveness_General_Full_FP32.10.1.65
  5. embed          M_Verify_MobileNetV2T6_Common.3.135.0  (MobileNetV2-T6, 512-d)
  6. match          cosine similarity against enrolled template, threshold

Run:  python pipeline.py            (prints shapes + a synthetic end-to-end pass)
      python pipeline.py --json     (machine-readable shapes)
"""

from __future__ import annotations

import argparse
import json
import os
import sys

import numpy as np

os.environ.setdefault("JAX_PLATFORMS", "cpu")

import jax
import jax.numpy as jnp

# ---------------------------------------------------------------------------
# constants recovered from the artifacts
# ---------------------------------------------------------------------------

#: Verify-network input resolution.  The SenseTime FaceAuth "Verify" models are
#: 112x112 RGB (M_Verify_MobileNetV2T6_Common.3.135.0); the aligner's 106-point
#: crop is warped to this size before embedding.  INFERRED from the model family
#: and the DetectorHunter::PadBlobInput square-padding code path.
FACE_SIZE = 112

#: Embedding width of the verification network (MobileNetV2-T6 head).  INFERRED:
#: `verif_engine::Engine::InitModel`, `FeatureCmp::loadNormFile` use a compact
#: float feature vector; 512 is the MobileNetV2/ArcFace-family default.
EMBED_DIM = 512

#: Number of alignment landmarks produced by the occlusion aligner.
NUM_LANDMARKS = 106  # confirmed: "M_Align_occlusion_106..."

#: Detector input: Hunter "360" family is trained on a square padded image.
#: INFERRED from stsdk::detection::DetectorHunter::PadBlobInput + "_360" suffix.
DETECT_SIZE = 360

#: Decision threshold for the cosine matcher.  UNKNOWN (selected per area code
#: at run time via st_auth_set_threshold_by_international_code / AreaInfo).
DEFAULT_MATCH_THRESHOLD = 0.55

#: Liveness decision threshold.  UNKNOWN.
DEFAULT_LIVENESS_THRESHOLD = 0.5


# ---------------------------------------------------------------------------
# stage 0 - preprocessing
# ---------------------------------------------------------------------------

def nv21_to_rgb(data: np.ndarray, width: int, height: int) -> np.ndarray:
    """Convert an NV21 buffer (Y plane + interleaved VU) to RGB uint8 HxWx3.

    The Java layer passes CvPixelFormat.NV21 (enum value 3) and a stride of
    `width` (FaceAuthManager.getStride), see FaceAuthManager.compare().
    """
    y = np.asarray(data[: width * height], dtype=np.uint8).reshape(height, width)
    vu = np.asarray(data[width * height:], dtype=np.uint8).reshape(-1, 2)
    v = vu[:, 0].reshape(height // 2, width // 2).astype(np.float32)
    u = vu[:, 1].reshape(height // 2, width // 2).astype(np.float32)

    c = y.astype(np.float32) - 16.0
    d = u - 128.0
    e = v - 128.0

    def up(x):
        return np.repeat(np.repeat(x, 2, axis=0), 2, axis=1)

    d = up(d)
    e = up(e)

    r = 1.164 * c + 1.596 * e
    g = 1.164 * c - 0.392 * d - 0.813 * e
    b = 1.164 * c + 2.017 * d

    rgb = np.stack([r, g, b], axis=-1)
    return np.clip(rgb, 0, 255).astype(np.uint8)


def rotate_rgb(img: np.ndarray, orientation: int) -> np.ndarray:
    """Apply the camera orientation in degrees (0/90/180/270).

    The native API (`st_auth_set_face_orientation`) actually takes a bitmask
    FaceOrientation: UP=1, LEFT=2, DOWN=4, RIGHT=8, UNKNOWN=15; FaceAuthManager
    maps degrees -> enum -> getValue() before the call.
    """
    if orientation == 0:
        return img
    k = {90: 1, 180: 2, 270: 3}.get(int(orientation), 0)
    if k == 0:
        return img
    return np.rot90(img, k=-k)


def crop_roi(img: np.ndarray, roi) -> np.ndarray:
    """Crop to st_rect_t (left, top, right, bottom); roi=None keeps the frame."""
    if roi is None:
        return img
    left, top, right, bottom = [int(v) for v in roi]
    h, w = img.shape[:2]
    left, right = max(0, left), min(w, right)
    top, bottom = max(0, top), min(h, bottom)
    if right <= left or bottom <= top:
        return img
    return img[top:bottom, left:right]


def resize_bilinear(img: np.ndarray, out_h: int, out_w: int) -> np.ndarray:
    """Bilinear resize (numpy, float32) - matches the SDK's ResizeImage helper."""
    img = img.astype(np.float32)
    h, w = img.shape[:2]
    ys = (np.arange(out_h) + 0.5) * (h / out_h) - 0.5
    xs = (np.arange(out_w) + 0.5) * (w / out_w) - 0.5
    y0 = np.clip(np.floor(ys).astype(np.int32), 0, h - 1)
    y1 = np.clip(y0 + 1, 0, h - 1)
    x0 = np.clip(np.floor(xs).astype(np.int32), 0, w - 1)
    x1 = np.clip(x0 + 1, 0, w - 1)
    wy = np.clip(ys - y0, 0, 1)[:, None, None]
    wx = np.clip(xs - x0, 0, 1)[None, :, None]
    top = img[y0][:, x0] * (1 - wx) + img[y0][:, x1] * wx
    bot = img[y1][:, x0] * (1 - wx) + img[y1][:, x1] * wx
    return top * (1 - wy) + bot * wy


def normalize(x: np.ndarray, mean=(127.5, 127.5, 127.5), std=128.0) -> np.ndarray:
    """Map RGB uint8 to roughly [-1, 1].  The exact mean/scale the Caffe nets use
    is set through st_auth_set_mean_value() at run time and is UNKNOWN here."""
    return (x.astype(np.float32) - np.asarray(mean, np.float32)) / std


def preprocess(frame, width, height, orientation=0, roi=None):
    """Full stage-0: NV21 -> RGB -> rotate -> ROI -> resize -> normalize -> NHWC."""
    if isinstance(frame, np.ndarray) and frame.ndim == 3:
        rgb = frame
    else:
        rgb = nv21_to_rgb(np.asarray(frame, dtype=np.uint8), width, height)
    rgb = rotate_rgb(rgb, orientation)
    rgb = crop_roi(rgb, roi)
    rgb = resize_bilinear(rgb, FACE_SIZE, FACE_SIZE)
    return normalize(rgb)[None, ...]  # 1x112x112x3


# ---------------------------------------------------------------------------
# parameter initialisation
# ---------------------------------------------------------------------------

def _he(key, shape, fan_in=None):
    fan_in = fan_in or int(np.prod(shape[:-1])) or 1
    std = np.sqrt(2.0 / fan_in)
    return jax.random.normal(key, shape) * std


def _bn(key, ch):
    return {
        "scale": jnp.ones((ch,)),
        "bias": jnp.zeros((ch,)),
        "mean": jnp.zeros((ch,)),
        "var": jnp.ones((ch,)),
    }


def init_params(seed: int = 0):
    """Deterministic random init for every stage.

    Shapes follow the recovered architecture; values are random because the
    shipped weights are encrypted.
    """
    k = jax.random.PRNGKey(seed)
    ks = jax.random.split(k, 64)
    i = 0

    def nxt():
        nonlocal i
        i += 1
        return ks[i]

    params = {}

    # --- stage 1: detector (Hunter-style): small stride-2 conv stem + RPN head
    params["detector"] = {
        "conv1": _he(nxt(), (3, 3, 3, 16)),    "bn1": _bn(nxt(), 16),
        "conv2": _he(nxt(), (3, 3, 16, 32)),  "bn2": _bn(nxt(), 32),
        "conv3": _he(nxt(), (3, 3, 32, 64)),  "bn3": _bn(nxt(), 64),
        # RPN: objectness + box regression (4 deltas per anchor)
        "rpn_cls": _he(nxt(), (3, 3, 64, 2)),
        "rpn_reg": _he(nxt(), (3, 3, 64, 4)),
    }

    # --- stage 2: 106-landmark occlusion aligner (conv stack -> 106x2)
    params["aligner"] = {
        "conv1": _he(nxt(), (3, 3, 3, 32)),   "bn1": _bn(nxt(), 32),
        "conv2": _he(nxt(), (3, 3, 32, 64)),  "bn2": _bn(nxt(), 64),
        "conv3": _he(nxt(), (3, 3, 64, 128)), "bn3": _bn(nxt(), 128),
        "fc": _he(nxt(), (128 * 28 * 28, NUM_LANDMARKS * 2)),
        "bias": jnp.zeros((NUM_LANDMARKS * 2,)),
    }

    # --- stage 3: pose regressor (yaw, pitch, roll) from the aligner features
    params["pose"] = {
        "fc": _he(nxt(), (128 * 28 * 28, 3)),
        "bias": jnp.zeros((3,)),
    }

    # --- stage 3b: eye-state classifier (binary, per eye)
    params["eye_state"] = {
        "fc": _he(nxt(), (128 * 28 * 28, 2)),
        "bias": jnp.zeros((2,)),
    }

    # --- stage 4: MTNet quality attribute (sharpness/brightness/occlusion score)
    params["quality"] = {
        "conv1": _he(nxt(), (3, 3, 3, 16)),   "bn1": _bn(nxt(), 16),
        "fc": _he(nxt(), (16 * 56 * 56, 1)),
        "bias": jnp.zeros((1,)),
    }

    # --- stage 5: RGB liveness / anti-spoofing (IM_RGB_Liveness_General_Full)
    params["liveness"] = {
        "conv1": _he(nxt(), (3, 3, 3, 16)),   "bn1": _bn(nxt(), 16),
        "conv2": _he(nxt(), (3, 3, 16, 32)),  "bn2": _bn(nxt(), 32),
        "conv3": _he(nxt(), (3, 3, 32, 64)),  "bn3": _bn(nxt(), 64),
        "fc": _he(nxt(), (64 * 14 * 14, 1)),
        "bias": jnp.zeros((1,)),
    }

    # --- stage 6: MobileNetV2-T6 embedder (width 1.0, 17 inverted-residual blocks)
    params["embedder"] = _mobilenet_v2_t6(nxt, EMBED_DIM)

    # --- stage 7: matcher (no learned params; cosine + threshold)
    params["matcher"] = {
        "threshold": jnp.asarray(DEFAULT_MATCH_THRESHOLD),
        "liveness_threshold": jnp.asarray(DEFAULT_LIVENESS_THRESHOLD),
    }
    return params


def _conv_bn(key, cin, cout, k=3, stride=1):
    return {
        "w": _he(key, (k, k, cin, cout)),
        "bn": _bn(key, cout),
        "stride": stride,
    }


def _mobilenet_v2_t6(nxt, embed_dim):
    """MobileNetV2 (InvertedResidual) trunk as used by M_Verify_MobileNetV2T6.

    t=6 expansion, 112x112 input, 1x1 conv head -> global average pool -> FC.
    """
    layers = []
    # stem: 3x3 stride 2 conv, 32 channels
    layers.append({"kind": "conv", **_conv_bn(nxt(), 3, 32, 3, 2)})
    # (t, c, n, s) MobileNetV2 V1 spec
    spec = [
        (1, 16, 1, 1),
        (6, 24, 2, 2),
        (6, 32, 3, 2),
        (6, 64, 4, 2),
        (6, 96, 3, 1),
        (6, 160, 3, 2),
        (6, 320, 1, 1),
    ]
    cin = 32
    for t, c, n, s in spec:
        for j in range(n):
            stride = s if j == 0 else 1
            expand = max(1, cin * t)
            blk = {
                "kind": "ir",
                "expand": _conv_bn(nxt(), cin, expand, 1, 1) if t != 1 else None,
                "depthwise": {
                    "w": _he(nxt(), (3, 3, 1, expand)),
                    "bn": _bn(nxt(), expand),
                    "stride": stride,
                },
                "project": _conv_bn(nxt(), expand, c, 1, 1),
                "residual": (stride == 1 and cin == c),
            }
            layers.append(blk)
            cin = c
    layers.append({"kind": "conv", **_conv_bn(nxt(), cin, 1280, 1, 1)})
    return {"layers": layers, "fc": _he(nxt(), (1280, embed_dim)), "bias": jnp.zeros((embed_dim,))}


# ---------------------------------------------------------------------------
# layers
# ---------------------------------------------------------------------------

def _bn_apply(x, bn, eps=1e-5):
    shape = (1, 1, 1, -1)
    return (x - bn["mean"].reshape(shape)) / jnp.sqrt(bn["var"].reshape(shape) + eps) * \
        bn["scale"].reshape(shape) + bn["bias"].reshape(shape)


def conv2d(x, w, stride=1, pad="SAME"):
    return jax.lax.conv_general_dilated(
        x, w, window_strides=(stride, stride),
        padding=pad, dimension_numbers=("NHWC", "HWIO", "NHWC"))


def depthwise(x, w, stride=1):
    # w: (3,3,1,C) -> depthwise via feature_group_count
    return jax.lax.conv_general_dilated(
        x, w, window_strides=(stride, stride), padding="SAME",
        dimension_numbers=("NHWC", "HWIO", "NHWC"), feature_group_count=x.shape[-1])


def relu6(x):
    return jnp.clip(x, 0.0, 6.0)


def global_avg(x):
    return jnp.mean(x, axis=(1, 2))


# ---------------------------------------------------------------------------
# stages
# ---------------------------------------------------------------------------

def detect(params, x):
    """Hunter-style detector -> (boxes[N,4], scores[N]) in normalized xyxy.

    The real Hunter net is a Faster-RCNN RPN ("FasterRCNNRPNPostProcess",
    "RPN_Post_AllInOne"); here the backbone + RPN head are implemented and the
    anchor decode is a simplified single-anchor-per-cell version.
    """
    p = params["detector"]
    h = relu6(_bn_apply(conv2d(x, p["conv1"], 2), p["bn1"]))
    h = relu6(_bn_apply(conv2d(h, p["conv2"], 2), p["bn2"]))
    h = relu6(_bn_apply(conv2d(h, p["conv3"], 2), p["bn3"]))
    obj = conv2d(h, p["rpn_cls"], 1)[0]           # HxWx2
    reg = conv2d(h, p["rpn_reg"], 1)[0]           # HxWx4
    scores = jax.nn.softmax(obj, axis=-1)[..., 1]
    H, W = scores.shape
    gy, gx = jnp.meshgrid(jnp.arange(H), jnp.arange(W), indexing="ij")
    cx = (gx + 0.5) / W
    cy = (gy + 0.5) / H
    dx, dy, dw, dh = reg[..., 0], reg[..., 1], reg[..., 2], reg[..., 3]
    boxes = jnp.stack([cx - 0.1 * jnp.exp(dw), cy - 0.1 * jnp.exp(dh),
                       cx + 0.1 * jnp.exp(dw), cy + 0.1 * jnp.exp(dh)], axis=-1)
    return boxes.reshape(-1, 4), scores.reshape(-1)


def align(params, x):
    """106-landmark regression + pose + eye state on a 112x112 aligned crop."""
    p = params["aligner"]
    h = relu6(_bn_apply(conv2d(x, p["conv1"]), p["bn1"]))
    h = relu6(_bn_apply(conv2d(h, p["conv2"], 2), p["bn2"]))
    h = relu6(_bn_apply(conv2d(h, p["conv3"], 2), p["bn3"]))
    f = h.reshape(h.shape[0], -1)
    landmarks = (f @ p["fc"] + p["bias"]).reshape(-1, NUM_LANDMARKS, 2)
    landmarks = jax.nn.sigmoid(landmarks)  # normalized to [0,1]
    pose = f @ params["pose"]["fc"] + params["pose"]["bias"]
    eye = jax.nn.softmax(f @ params["eye_state"]["fc"] + params["eye_state"]["bias"], axis=-1)
    return landmarks, pose, eye, f


def quality(params, x):
    p = params["quality"]
    h = relu6(_bn_apply(conv2d(x, p["conv1"], 2), p["bn1"]))
    f = h.reshape(h.shape[0], -1)
    return jax.nn.sigmoid(f @ p["fc"] + p["bias"])[:, 0]


def liveness(params, x):
    p = params["liveness"]
    h = relu6(_bn_apply(conv2d(x, p["conv1"], 2), p["bn1"]))
    h = relu6(_bn_apply(conv2d(h, p["conv2"], 2), p["bn2"]))
    h = relu6(_bn_apply(conv2d(h, p["conv3"], 2), p["bn3"]))
    f = h.reshape(h.shape[0], -1)
    return jax.nn.sigmoid(f @ p["fc"] + p["bias"])[:, 0]


def embed(params, x):
    """MobileNetV2-T6 trunk -> L2-normalised embedding (the template vector)."""
    p = params["embedder"]
    h = x
    for layer in p["layers"]:
        if layer["kind"] == "conv":
            h = relu6(_bn_apply(conv2d(h, layer["w"], layer["stride"]), layer["bn"]))
        else:
            residual = h
            if layer["expand"] is not None:
                h = relu6(_bn_apply(conv2d(h, layer["expand"]["w"], 1), layer["expand"]["bn"]))
            dw = layer["depthwise"]
            h = relu6(_bn_apply(depthwise(h, dw["w"], dw["stride"]), dw["bn"]))
            pr = layer["project"]
            h = _bn_apply(conv2d(h, pr["w"], 1), pr["bn"])
            if layer["residual"]:
                h = h + residual
    h = relu6(h)
    f = global_avg(h)
    z = f @ p["fc"] + p["bias"]
    return z / (jnp.linalg.norm(z, axis=-1, keepdims=True) + 1e-8)


def match(params, emb, template):
    """Cosine similarity; the SDK compares the feature against the enrolled
    template produced by st_auth_enroll (FeatureCmp; optional PCA norm file)."""
    sim = jnp.sum(emb * template, axis=-1)
    return sim, sim >= params["matcher"]["threshold"]


def apply(params, image_nhwc):
    """Full pipeline on one 112x112x3 (NHWC) preprocessed frame."""
    x = jnp.asarray(image_nhwc, dtype=jnp.float32)
    boxes, det_scores = detect(params, x)
    landmarks, pose, eye, feat = align(params, x)
    q = quality(params, x)
    live = liveness(params, x)
    emb = embed(params, x)
    sim, ok = match(params, emb, emb)          # self-match on the synthetic input
    return {
        "boxes": boxes,
        "det_scores": det_scores,
        "landmarks": landmarks,
        "pose": pose,
        "eye_state": eye,
        "quality": q,
        "liveness": live,
        "embedding": emb,
        "match_similarity": sim,
        "match_ok": ok,
    }


# ---------------------------------------------------------------------------
# demo / verification entry point
# ---------------------------------------------------------------------------

def _synthetic_face(size=FACE_SIZE, seed=0):
    """Crude face-like image: skin ellipse, darker eye bars, mouth, gradient."""
    rng = np.random.default_rng(seed)
    img = np.zeros((size, size, 3), np.float32)
    yy, xx = np.mgrid[0:size, 0:size].astype(np.float32)
    cx, cy = size / 2, size / 2
    face = (((xx - cx) / (size * 0.32)) ** 2 + ((yy - cy) / (size * 0.40)) ** 2) < 1.0
    img[...] = np.array([40, 40, 45], np.float32)
    img[face] = np.array([200, 160, 140], np.float32)
    for ex in (cx - size * 0.15, cx + size * 0.15):
        eye = (((xx - ex) / (size * 0.06)) ** 2 + ((yy - size * 0.42) / (size * 0.03)) ** 2) < 1
        img[eye] = np.array([30, 30, 35], np.float32)
    mouth = (((xx - cx) / (size * 0.12)) ** 2 + ((yy - size * 0.70) / (size * 0.03)) ** 2) < 1
    img[mouth] = np.array([120, 60, 60], np.float32)
    img += rng.normal(0, 3.0, img.shape)
    return np.clip(img, 0, 255).astype(np.uint8)


def main(argv=None):
    ap = argparse.ArgumentParser()
    ap.add_argument("--json", action="store_true")
    args = ap.parse_args(argv)

    rng = np.random.default_rng(0)
    frame = rng.integers(0, 255, size=(480 * 640 * 3 // 2,), dtype=np.uint8)  # NV21 640x480
    x = preprocess(frame, 640, 480, orientation=90)
    face = normalize(_synthetic_face())

    params = init_params(0)

    out_full = apply(params, face[None, ...])
    out_real = apply(params, x)  # random-noise frame exercises the same path

    shapes = {
        "input_preprocessed": list(face[None, ...].shape),
        "input_nv21_frame": [1, 480 * 640 * 3 // 2],
        "boxes": list(out_full["boxes"].shape),
        "det_scores": list(out_full["det_scores"].shape),
        "landmarks": list(out_full["landmarks"].shape),
        "pose": list(out_full["pose"].shape),
        "eye_state": list(out_full["eye_state"].shape),
        "quality": list(out_full["quality"].shape),
        "liveness": list(out_full["liveness"].shape),
        "embedding": list(out_full["embedding"].shape),
        "match_similarity": list(out_full["match_similarity"].shape),
    }

    if args.json:
        print(json.dumps({"status": "ARCHITECTURE_ONLY", "shapes": shapes}, indent=2))
        return 0

    print("miui_biometric / SenseTime FaceAuth pipeline  [ARCHITECTURE_ONLY]")
    print("weights: NOT recovered (encrypted __res_* blobs) -> random init")
    print()
    for k, v in shapes.items():
        print(f"  {k:24s} {v}")
    print()
    print(f"  embedding norm          {float(jnp.linalg.norm(out_full['embedding'])):.6f}")
    print(f"  quality                 {float(out_full['quality'][0]):.6f}")
    print(f"  liveness                {float(out_full['liveness'][0]):.6f}")
    print(f"  self match similarity   {float(out_full['match_similarity'][0]):.6f}")
    print(f"  landmarks[0]            {np.asarray(out_full['landmarks'])[0, 0]}")
    print(f"  pose                    {np.asarray(out_full['pose'])[0]}")
    print(f"  noise-frame liveness    {float(out_real['liveness'][0]):.6f}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
