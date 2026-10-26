#!/usr/bin/env python3
"""Independent NumPy transcription of the training maths in subgraph 5.

This is deliberately *not* JAX: forward pass, hand-written backprop and the
TF1-Adam update are written out with numpy float32 so `verify.py` can check the
JAX `jax.grad` implementation against it.

Equations (constants from the flatbuffer, see ir/ops_sg5.txt):

    loss = 0.9 * mean_b( 100*|(y_b     - p_b) / max(|y_b|,     1e-7)| )
         + 0.1 * mean_b( 100*|(o_b     - p_b) / max(|o_b|,     1e-7)| )   # o = old(x)

    dL/dp = 0.9 * 100/B * mean_b( -sign((y-p)/dy) / dy )
          + 0.1 * 100/B * mean_b( -sign((o-p)/do) / do )

    t     = iter + 1
    lr_t  = 0.001 * sqrt(1 - 0.999**t) / (1 - 0.9**t)
    m     = 0.9*m + 0.1*g
    v     = 0.999*v + 0.001*g*g
    w     = w - lr_t * m / (sqrt(v) + 1e-7)
"""
import numpy as np

NEW = (("dnn_model_1/", "dense_4", True), ("dnn_model_1/", "dense_5", True),
       ("dnn_model_1/", "dense_6", True), ("dnn_model_1/", "dense_7", False))
OLD = (("dnn_model/", "dense", True), ("dnn_model/", "dense_1", True),
       ("dnn_model/", "dense_2", True), ("dnn_model/", "dense_3", False))

LR, BETA1, BETA2, EPS = 0.001, 0.9, 0.999, 1e-7
W_SUP, W_DIS, SCALE, EPS_MAPE = 0.9, 0.1, 100.0, 1e-7


def _f32(a):
    return np.asarray(a, dtype=np.float32)


def forward(p, net, x):
    h = _f32(x)
    cache = []
    for prefix, layer, relu in net:
        k = p[f"{prefix}{layer}/kernel"]
        b = p[f"{prefix}{layer}/bias"]
        z = _f32(h @ k + b)
        cache.append((h, k, relu, z))
        h = _f32(np.maximum(z, 0) if relu else z)
    return h, cache


def backward(dout, cache):
    grads = {}
    d = _f32(dout)
    for i in range(len(cache) - 1, -1, -1):
        h, k, relu, z = cache[i]
        if relu:
            d = _f32(d * (z > 0))
        prefix, layer = None, None
        grads[i] = (_f32(h.T @ d), _f32(d.sum(axis=0)))
        d = _f32(d @ k.T)
    return grads


def loss_and_grad(p_new, p_old, x, y):
    x = _f32(x)
    y = _f32(y)
    p, cache_new = forward(p_new, NEW, x)
    o, _ = forward(p_old, OLD, x)
    dy = np.maximum(np.abs(y), EPS_MAPE)
    do = np.maximum(np.abs(o), EPS_MAPE)
    loss = (W_SUP * np.mean(np.abs((y - p) / dy)) * SCALE
            + W_DIS * np.mean(np.abs((o - p) / do)) * SCALE)
    B = x.shape[0]
    dp = (W_SUP * SCALE / B * (-np.sign((y - p) / dy) / dy)
          + W_DIS * SCALE / B * (-np.sign((o - p) / do) / do))
    d = backward(_f32(dp), cache_new)
    out = {}
    for i, (prefix, layer, _) in enumerate(NEW):
        dk, db = d[i]
        out[f"{prefix}{layer}/kernel"] = dk
        out[f"{prefix}{layer}/bias"] = db
    return _f32(loss), out


def adam_step(p, grads, lr=LR):
    out = {k: v for k, v in p.items()}
    t = np.float32(p["Adam/iter"].astype(np.int64) + 1)
    lr_t = np.float32(lr * np.sqrt(np.float32(1.0) - BETA2 ** t) / (np.float32(1.0) - BETA1 ** t))
    for prefix, layer, _ in NEW:
        for kind in ("kernel", "bias"):
            key = f"{prefix}{layer}/{kind}"
            g = grads[key]
            m = _f32(BETA1 * p[f"Adam/{key}/m"] + (1.0 - BETA1) * g)
            v = _f32(BETA2 * p[f"Adam/{key}/v"] + (1.0 - BETA2) * g * g)
            out[f"Adam/{key}/m"] = m
            out[f"Adam/{key}/v"] = v
            out[key] = _f32(p[key] - lr_t * m / (np.sqrt(v) + EPS))
    out["Adam/iter"] = np.asarray(int(t), dtype=np.int64)
    return out


def train_steps(p_new, p_old, xs, ys):
    losses = []
    for x, y in zip(xs, ys):
        loss, g = loss_and_grad(p_new, p_old, x, y)
        p_new = adam_step(p_new, g)
        losses.append(float(loss))
    return losses, p_new
