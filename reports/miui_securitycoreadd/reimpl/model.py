#!/usr/bin/env python3
"""JAX reimplementation of `assets/aiautobrt.tflite` (package com.miui.securitycore).

The flatbuffer holds two identical-shape MLPs plus a TF1 training graph:

    subgraph 0  new_model_infer   x[1,3] -> pre[1,1]
    subgraph 1  old_model_infer   x[1,3] -> pre[1,1]
    subgraph 5  train             (x[1,3], y[1,1]) -> loss[]   (one Adam step)

Architecture of both MLPs (confirmed from ops_sg0.txt / ops_sg1.txt):

    x(3) -> Dense(3)  + ReLU
         -> Dense(128)+ ReLU
         -> Dense(64) + ReLU
         -> Dense(1)  (linear)

Keras Dense semantics: y = x @ kernel + bias, kernel stored [in, out].
TFLite stores the very same kernel and transposes/reshapes it at run time
(TRANSPOSE on the 3x3/3x128/128x64 kernels, RESHAPE [64, 1] -> [1, 64] on the
last one), so a plain `x @ kernel + bias` reproduces the graph bit-for-bit
within float32 rounding.

Training (subgraph 5), all constants read from the flatbuffer:

    loss = 0.9 * MAPE(y,     new(x)) + 0.1 * MAPE(old(x), new(x))
    MAPE(t, p) = 100 * mean(|(t - p) / max(|t|, 1e-7)|)

    optimiser  = tf.compat.v1.train.AdamOptimizer(learning_rate=0.001)
                 beta1=0.9, beta2=0.999, epsilon=1e-7
                 (unfused TF1 formulation, iter incremented *after* the step:
                  lr_t = lr * sqrt(1 - beta2**t) / (1 - beta1**t),
                  m <- beta1*m + (1-beta1)*g,
                  v <- beta2*v + (1-beta2)*g*g,
                  w <- w - lr_t * m / (sqrt(v) + eps))
    Only the `dnn_model_1/*` weights receive gradients; `dnn_model/*` is the
    frozen teacher that also supplies the 10 % distillation term.

Weight provenance: `weights.npz` is byte-exact from subgraph 6 (the CALL_ONCE
init subgraph): every variable is ASSIGN_VARIABLE'd from a float32 constant
buffer.  Verified against the LiteRT interpreter in verify.py.
"""
from __future__ import annotations

import os

import jax
import jax.numpy as jnp
import numpy as np

jax.config.update("jax_enable_x64", False)

HERE = os.path.dirname(os.path.abspath(__file__))
WEIGHTS = os.path.join(HERE, "weights.npz")

# (variable prefix, layer name, activation) in execution order
NEW_LAYERS = (
    ("dnn_model_1/", "dense_4", True),
    ("dnn_model_1/", "dense_5", True),
    ("dnn_model_1/", "dense_6", True),
    ("dnn_model_1/", "dense_7", False),
)
OLD_LAYERS = (
    ("dnn_model/", "dense", True),
    ("dnn_model/", "dense_1", True),
    ("dnn_model/", "dense_2", True),
    ("dnn_model/", "dense_3", False),
)
SHAPES = {
    "dense_4": (3, 3), "dense_5": (3, 128), "dense_6": (128, 64), "dense_7": (64, 1),
    "dense": (3, 3), "dense_1": (3, 128), "dense_2": (128, 64), "dense_3": (64, 1),
}

# Training hyper-parameters, read from subgraph 5 constants.
LR = 0.001
BETA1 = 0.9
BETA2 = 0.999
EPS = 1e-7
W_SUPERVISED = 0.9
W_DISTILL = 0.1
MAPE_SCALE = 100.0
EPS_MAPE = 1e-7


# --------------------------------------------------------------------------
# parameters
# --------------------------------------------------------------------------
def load_params(path: str = WEIGHTS) -> dict:
    """Return the flatbuffer's initial variable values as JAX float32 arrays.

    Keys are the TFLite variable names (`dnn_model_1/dense_4/kernel`, ...),
    plus the Adam slot variables (`Adam/.../m`, `Adam/.../v`) and `Adam/iter`.
    """
    with np.load(path) as z:
        params = {}
        for name in z.files:
            arr = np.asarray(z[name])
            dtype = jnp.int64 if arr.dtype == np.int64 else jnp.float32
            params[name] = jnp.asarray(arr, dtype=dtype)
    return params


def init_params(seed: int = 0) -> dict:
    """Deterministic fallback initialisation (Keras Dense defaults).

    Glorot-uniform kernels, zero biases, zero Adam slots.  Used only if
    `weights.npz` is unavailable; the shipped bytes are used otherwise.
    """
    rng = np.random.default_rng(seed)
    params = {}
    for prefix, net in (("dnn_model_1/", NEW_LAYERS), ("dnn_model/", OLD_LAYERS)):
        for _, layer, _ in net:
            i, o = SHAPES[layer]
            limit = np.sqrt(6.0 / (i + o))
            params[f"{prefix}{layer}/kernel"] = jnp.asarray(
                rng.uniform(-limit, limit, (i, o)), jnp.float32)
            params[f"{prefix}{layer}/bias"] = jnp.zeros((o,), jnp.float32)
    for prefix, net in (("dnn_model_1/", NEW_LAYERS),):
        for _, layer, _ in net:
            i, o = SHAPES[layer]
            for slot in ("m", "v"):
                params[f"Adam/{prefix}{layer}/kernel/{slot}"] = jnp.zeros((i, o), jnp.float32)
                params[f"Adam/{prefix}{layer}/bias/{slot}"] = jnp.zeros((o,), jnp.float32)
    params["Adam/iter"] = jnp.asarray(0, jnp.int64)
    return params


def layer_params(params: dict, prefix: str, layer: str) -> tuple:
    return params[f"{prefix}{layer}/kernel"], params[f"{prefix}{layer}/bias"]


# --------------------------------------------------------------------------
# inference  (subgraphs 0 and 1)
# --------------------------------------------------------------------------
def _mlp(params: dict, net, x):
    h = x
    for prefix, layer, relu in net:
        k, b = layer_params(params, prefix, layer)
        h = h @ k + b
        if relu:
            h = jnp.maximum(h, 0.0)
    return h


def apply(params: dict, x):
    """new_model_infer: x[..., 3] -> pre[..., 1]."""
    return _mlp(params, NEW_LAYERS, jnp.asarray(x, jnp.float32))


def apply_old(params: dict, x):
    """old_model_infer: x[..., 3] -> pre[..., 1] (frozen teacher)."""
    return _mlp(params, OLD_LAYERS, jnp.asarray(x, jnp.float32))


# --------------------------------------------------------------------------
# loss  (subgraph 5, ops 67..175)
# --------------------------------------------------------------------------
def mape(y_true, y_pred):
    """Keras-3 style MAPE, matching the graph: mean(|(t-p)/max(|t|,1e-7)|)*100."""
    diff = y_true - y_pred
    denom = jnp.maximum(jnp.abs(y_true), EPS_MAPE)
    return jnp.mean(jnp.abs(diff / denom), axis=-1) * MAPE_SCALE


def total_loss(params_new, params_old, x, y):
    pred_new = apply(params_new, x)
    pred_old = apply_old(params_old, x)
    # graph: sum(100 * per_sample_mape) / num_elements  ==  mean over the batch
    sup = jnp.mean(mape(y, pred_new))
    dis = jnp.mean(mape(pred_old, pred_new))
    return W_SUPERVISED * sup + W_DISTILL * dis


# --------------------------------------------------------------------------
# one training step  (subgraph 5, ops 176..354)
# --------------------------------------------------------------------------
def _adam_step(params, grads, lr=LR):
    it = params["Adam/iter"] + 1                      # ops 34-36: iter + 1
    t = it.astype(jnp.float32)                        # op 36 cast
    lr_t = lr * jnp.sqrt(1.0 - BETA2 ** t) / (1.0 - BETA1 ** t)  # ops 199-203
    out = dict(params)
    for prefix, layer, _ in NEW_LAYERS:
        for kind in ("kernel", "bias"):
            key = f"{prefix}{layer}/{kind}"
            g = grads[key]
            mk = f"Adam/{key}/m"
            vk = f"Adam/{key}/v"
            m = BETA1 * params[mk] + (1.0 - BETA1) * g      # ops 205-207
            v = BETA2 * params[vk] + (1.0 - BETA2) * (g * g)  # ops 209-212
            out[mk] = m
            out[vk] = v
            out[key] = params[key] - lr_t * m / (jnp.sqrt(v) + EPS)  # ops 213-218
    out["Adam/iter"] = it
    return out


TRAINABLE = tuple(
    f"{prefix}{layer}/{kind}"
    for prefix, layer, _ in NEW_LAYERS
    for kind in ("kernel", "bias")
)


def _grads(params_new, params_old, x, y):
    """d(loss)/d(new weights); `Adam/iter` is int and must stay out of grad."""

    def loss_fn(vals):
        p = dict(params_new)
        for key, v in zip(TRAINABLE, vals):
            p[key] = v
        return total_loss(p, params_old, x, y)

    g = jax.grad(loss_fn)(tuple(params_new[k] for k in TRAINABLE))
    return dict(zip(TRAINABLE, g))


def train_step(params_new, params_old, x, y):
    """One Adam step on `dnn_model_1/*`; returns (loss, new_params).

    `params_old` is not modified (the graph only reads `dnn_model/*`).
    """
    x = jnp.asarray(x, jnp.float32)
    y = jnp.asarray(y, jnp.float32)
    loss = total_loss(params_new, params_old, x, y)
    grads = _grads(params_new, params_old, x, y)
    return loss, _adam_step(params_new, grads)


if __name__ == "__main__":
    p = load_params()
    x = jnp.asarray([[1.0, 2.0, 3.0]], jnp.float32)
    print("input", x.shape)
    print("new_model_infer", apply(p, x).shape, np.asarray(apply(p, x)).ravel())
    print("old_model_infer", apply_old(p, x).shape, np.asarray(apply_old(p, x)).ravel())
    loss, p2 = train_step(p, p, x, jnp.asarray([[20.0]], jnp.float32))
    print("one train step loss", float(loss))
