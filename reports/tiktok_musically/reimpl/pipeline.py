#!/usr/bin/env python3
"""JAX reimplementation of the TikTok/ByteDance 'smash face' pipeline.

STATUS: ARCHITECTURE_ONLY for the three shipped .model bundles.
  * The container itself IS parsed for real (see model_container.py).
  * The 'mean' record IS decoded for real: 1905 float32 values.
  * The sub-model graphs and all other weights are encrypted/opaque
    (see report.md section 4) so the topologies below are documented
    placeholders, not the shipped networks. Tensor contracts are marked
    confirmed/inferred/unknown.

Run:  python pipeline.py            # prints shapes on synthetic input
      python pipeline.py --verify   # also writes verify.txt
"""
from __future__ import annotations
import argparse, json, os, sys
import numpy as np
import jax, jax.numpy as jnp
from jax import random
from functools import partial

HERE = os.path.dirname(os.path.abspath(__file__))

# ---------------------------------------------------------------- preprocessing
# Confidence: inferred. The effect SDK works on OpenCV BGR frames (8UC3) and the
# face crop asserts in libeffect.so are CV_8UC3; ByteNN tensors are float32.
# The exact mean/scale is not in the clear (the decoded 'mean' vector is 1905
# values, which is not a 3-value channel mean), so we use the common ByteDance
# (x-127.5)/128 convention and mark it inferred.
MEAN = 127.5
SCALE = 128.0


def preprocess_bgr(img_uint8: np.ndarray) -> jnp.ndarray:
    """HWC uint8 BGR -> (1,H,W,3) float32 NHWC."""
    x = np.asarray(img_uint8, dtype=np.float32)
    if x.ndim != 3 or x.shape[2] != 3:
        raise ValueError(f"expected HWC 3-channel uint8, got {x.shape}")
    return jnp.asarray((x - MEAN) / SCALE)[None, ...]


# ---------------------------------------------------------------- conv helpers
def _conv(x, w, b, stride=1):
    pad = 1 if w.shape[0] > 1 else 0
    return jax.lax.conv_general_dilated(
        x, w, window_strides=(stride, stride),
        padding=[(pad, pad), (pad, pad)], dimension_numbers=("NHWC", "HWIO", "NHWC")) + b


def _gn(x):  # cheap normalisation stand-in for BN (no running stats available)
    m = jnp.mean(x, axis=(1, 2), keepdims=True)
    v = jnp.var(x, axis=(1, 2), keepdims=True)
    return (x - m) * jax.lax.rsqrt(v + 1e-5)


def _init_conv(key, cin, cout, k=3):
    k1, k2 = random.split(key)
    w = random.normal(k1, (k, k, cin, cout)) * jnp.sqrt(2.0 / (k * k * cin))
    b = jnp.zeros((cout,))
    return {"w": w, "b": b}


def init_params(key):
    keys = random.split(key, 32)
    p = {}
    # shared face encoder (all nets) -- ARCHITECTURE_ONLY
    p["enc1"] = _init_conv(keys[0], 3, 16)
    p["enc2"] = _init_conv(keys[1], 16, 32)
    p["enc3"] = _init_conv(keys[2], 32, 64)
    # detector heads (pout / box)
    p["pout"] = _init_conv(keys[3], 64, 1, 1)
    p["box"] = _init_conv(keys[4], 64, 4, 1)
    # extra model (iris / eyecls / partface)
    p["partface"] = _init_conv(keys[5], 64, 5, 1)
    p["iris"] = _init_conv(keys[6], 64, 2, 1)
    p["eyecls_w"] = random.normal(keys[7], (64, 2)) * 0.05
    p["eyecls_b"] = jnp.zeros((2,))
    # age attribute model (agenet)
    p["age1"] = _init_conv(keys[8], 3, 16)
    p["age2"] = _init_conv(keys[9], 16, 32)
    p["age3"] = _init_conv(keys[10], 32, 64)
    p["age4"] = _init_conv(keys[11], 64, 128)
    p["age_fc_w"] = random.normal(keys[12], (128, 1)) * 0.05
    p["age_fc_b"] = jnp.zeros((1,))
    return p


# ---------------------------------------------------------------- networks
def face_detector(params, x):
    """tt_face_v10.0.model -> FaceDetectTaskTT.

    Input  (1,120,120,3) float32 NHWC        [inferred from record name 'base120']
    Outputs
      pout  (1,30,30,1) objectness logits    [inferred from record name 'pout']
      box   (1,30,30,4) ltrb regression      [inferred from 'base120det']
    Confirmed only: the record set {detect, base120, base120det, image,
    slowimage, mean} and that libeffect calls them via espresso/Thrustor.
    """
    h = jax.nn.relu(_gn(_conv(x, params["enc1"]["w"], params["enc1"]["b"], 2)))
    h = jax.nn.relu(_gn(_conv(h, params["enc2"]["w"], params["enc2"]["b"], 2)))
    h = jax.nn.relu(_gn(_conv(h, params["enc3"]["w"], params["enc3"]["b"], 1)))
    return {
        "pout": _conv(h, params["pout"]["w"], params["pout"]["b"], 1),
        "box": _conv(h, params["box"]["w"], params["box"]["b"], 1),
    }


def extra_model(params, x):
    """tt_face_extra_v13.0.model -> iris/eyecls/partface refinement.

    Input  (1,120,120,3) aligned face crop   [inferred]
    Outputs
      iris      (1,30,30,2) iris centre offsets  [unknown layout]
      eyecls    (1,2) eye-state logits           [inferred from 'eyecls']
      partface  (1,30,30,5) part-segmentation    [inferred from 'partface']
    libeffect hard-codes partface layer names crop_1/topdown*_proj.0/up*/
    elt*/classifier_1 plus shuffle blocks (an FPN + ShuffleNet-like decoder);
    that string evidence is recorded in report.md, the topology here is not it.
    """
    h = jax.nn.relu(_gn(_conv(x, params["enc1"]["w"], params["enc1"]["b"], 2)))
    h = jax.nn.relu(_gn(_conv(h, params["enc2"]["w"], params["enc2"]["b"], 2)))
    h = jax.nn.relu(_gn(_conv(h, params["enc3"]["w"], params["enc3"]["b"], 1)))
    pooled = jnp.mean(h, axis=(1, 2))
    return {
        "iris": _conv(h, params["iris"]["w"], params["iris"]["b"], 1),
        "eyecls": pooled @ params["eyecls_w"] + params["eyecls_b"],
        "partface": _conv(h, params["partface"]["w"], params["partface"]["b"], 1),
    }


def age_model(params, x):
    """tt_face_attribute_age_v1.1.model -> FaceAttributesTask age head.

    Input  (1,448,448,3) aligned face crop   [inferred; libeffect asserts a
             CV_8UC3 bgr448 crop] -- exact crop/size unknown.
    Output (1,1) age in years (regression)   [unknown: could be class logits]
    """
    h = jax.nn.relu(_gn(_conv(x, params["age1"]["w"], params["age1"]["b"], 2)))
    h = jax.nn.relu(_gn(_conv(h, params["age2"]["w"], params["age2"]["b"], 2)))
    h = jax.nn.relu(_gn(_conv(h, params["age3"]["w"], params["age3"]["b"], 2)))
    h = jax.nn.relu(_gn(_conv(h, params["age4"]["w"], params["age4"]["b"], 2)))
    h = jnp.mean(h, axis=(1, 2))
    return h @ params["age_fc_w"] + params["age_fc_b"]


# ---------------------------------------------------------------- decoded data
def load_mean_weights():
    """Real decoded 'mean' record: 1905 float32 values (see model_container.py)."""
    path = os.path.join(HERE, "..", "ir", "mean_weights.json")
    if not os.path.exists(path):
        return None
    with open(path) as f:
        m = json.load(f)
    return jnp.asarray(m["values"], dtype=jnp.float32), m


def mean_norm(v, mean):
    """Apply the decoded mean vector when the caller's vector matches its length."""
    v = jnp.asarray(v, dtype=jnp.float32)
    if v.shape[-1] != mean.shape[0]:
        raise ValueError(f"shape {v.shape} does not match mean vector {mean.shape}")
    return v - mean


# ---------------------------------------------------------------- entry point
def main(argv=None):
    ap = argparse.ArgumentParser()
    ap.add_argument("--verify", action="store_true")
    args = ap.parse_args(argv)
    lines = []

    def log(*a):
        s = " ".join(str(x) for x in a)
        print(s)
        lines.append(s)

    log("jax", jax.__version__)
    key = random.PRNGKey(0)
    params = init_params(key)
    rng = np.random.default_rng(0)

    img = (rng.random((120, 120, 3)) * 255).astype(np.uint8)
    x = preprocess_bgr(img)
    log("preprocess_bgr HWC uint8 ->", x.shape, x.dtype, "mean/scale", MEAN, SCALE)

    det = face_detector(params, x)
    log("face_detector(tt_face_v10.0) inputs", x.shape,
        "outputs", {k: tuple(v.shape) for k, v in det.items()},
        "finite", all(bool(jnp.isfinite(v).all()) for v in det.values()))

    ext = extra_model(params, x)
    log("extra_model(tt_face_extra_v13.0) inputs", x.shape,
        "outputs", {k: tuple(v.shape) for k, v in ext.items()},
        "finite", all(bool(jnp.isfinite(v).all()) for v in ext.values()))

    age_img = (rng.random((448, 448, 3)) * 255).astype(np.uint8)
    xa = preprocess_bgr(age_img)
    age = age_model(params, xa)
    log("age_model(tt_face_attribute_age_v1.1) inputs", xa.shape,
        "outputs", age.shape, "finite", bool(jnp.isfinite(age).all()))

    mw = load_mean_weights()
    if mw is not None:
        mean, meta = mw
        log("decoded mean weights:", tuple(mean.shape), "dtype", mean.dtype,
            "first4", [round(float(v), 4) for v in mean[:4]])
        test_vec = jnp.arange(1905, dtype=jnp.float32)
        out = mean_norm(test_vec, mean)
        log("mean_norm applied:", out.shape, "finite", bool(jnp.isfinite(out).all()))

    log("STATUS: ARCHITECTURE_ONLY (container parsed; descriptor-block schema unknown)")
    if args.verify:
        with open(os.path.join(HERE, "verify.txt"), "w") as f:
            f.write("\n".join(lines) + "\n")
    return 0


if __name__ == "__main__":
    sys.exit(main())
