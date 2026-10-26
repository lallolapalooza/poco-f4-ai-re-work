#!/usr/bin/env python3
"""JAX reimplementation of assets/tflite_langid.tflite.jpg (ML Kit bundled language ID).

The TFLite graph (ir/tflite_langid.json) is

    STRING[1]
      -> NGramHash (CUSTOM, options: ngram_lengths=[1,2,3,4], seed=10911,
                    vocab_sizes=[5500,5500,13000,13000], max_splits=128)
      -> [1,4,N] int32          (hash % vocab + 1 per n-gram, 0 padded)
      -> UNPACK(axis=1)         -> 4 x [1,N]
      -> KmeansEmbeddingLookup  -> 4 x [1,24]   (mean over n-grams of
                                   concat_{j<6} b_matrix[c_matrix[h][j]])
      -> CONCAT(axis=1)         -> [1,96]
      -> FC(96->160)+ReLU, FC(160->200)+ReLU, FC(200->111)
      -> SOFTMAX                -> [1,111]

`apply()` implements everything from the 96-d embedding onward in JAX.  The
integer featurizer (`featurize`, in langid_pipeline.py) reproduces the
NGramHash + KmeansEmbeddingLookup indexing exactly (MurmurHash64A, recovered
from liblanguage_id_l2c_jni.so @0x407c8/0x401f8).

Both halves are numerically cross-checked against an independent NumPy
implementation in reference_numpy.py; see verify.txt.
"""

from __future__ import annotations

import os

import jax
import jax.numpy as jnp
import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))


def init_params(path: str | None = None) -> dict:
    """Load params.npz (produced by extract_params.py)."""
    p = np.load(path or os.path.join(HERE, "params.npz"), allow_pickle=True)
    params = {
        "ngram_b": [jnp.asarray(p[f"t{i}"], dtype=jnp.float32) for i in (2, 4, 6, 8)],
        "ngram_c": [jnp.asarray(p[f"t{i}"], dtype=jnp.int32) for i in (3, 5, 7, 9)],
        "fc_w": [jnp.asarray(p[f"t{i}"], dtype=jnp.int8) for i in (13, 14, 15)],
        "fc_b": [jnp.asarray(p[f"t{i}"], dtype=jnp.float32) for i in (10, 11, 12)],
        "fc_scale": jnp.asarray(p["fc_scales"], dtype=jnp.float32),
        "class_ids": [str(s) for s in p["class_ids"]],
        "threshold": float(p["threshold"]),
        "vocab_sizes": [int(v) for v in p["vocab_sizes"]],
    }
    return params


def embed(params: dict, hashes: jnp.ndarray) -> jnp.ndarray:
    """KmeansEmbeddingLookup for the 4 n-gram orders.

    hashes: int32 [4, N]  (0 = padding)
    returns float32 [96]  = concat over orders of the mean 24-d embedding
    """
    outs = []
    for o in range(4):
        h = hashes[o]                                  # [N]
        valid = h > 0
        code = jnp.where(valid, h, 0)                   # index into c_matrix rows
        c = jnp.take(params["ngram_c"][o], code, axis=0)        # [N,6] uint8 codes
        b = jnp.take(params["ngram_b"][o], c.reshape(-1), axis=0)  # [N*6,4]
        b = b.reshape(h.shape[0], 24)                   # 6 sub-vectors x 4 dims
        m = jnp.where(valid[:, None], b, 0.0)
        n = jnp.maximum(jnp.sum(valid.astype(jnp.float32)), 1.0)
        outs.append(jnp.sum(m, axis=0) / n)             # [24]
    return jnp.concatenate(outs)                        # [96]


def fc(params: dict, i: int, x: jnp.ndarray, relu: bool) -> jnp.ndarray:
    """Hybrid FULLY_CONNECTED: int8 weights (zp=0, per-tensor scale) x float input."""
    w = params["fc_w"][i].astype(jnp.float32) * params["fc_scale"][i]
    y = x @ w.T + params["fc_b"][i]
    return jax.nn.relu(y) if relu else y


def apply(params: dict, hashes: jnp.ndarray) -> jnp.ndarray:
    """hashes: int32 [4, N] -> softmax over 111 language classes."""
    x = embed(params, jnp.asarray(hashes, dtype=jnp.int32))
    x = fc(params, 0, x, relu=True)
    x = fc(params, 1, x, relu=True)
    x = fc(params, 2, x, relu=False)
    return jax.nn.softmax(x)


def identify(params: dict, text: str) -> list[tuple[str, float]]:
    """End-to-end: text -> [(language_code, probability)] sorted, >= threshold."""
    from langid_pipeline import featurize

    probs = np.asarray(apply(params, featurize(text)))
    order = np.argsort(-probs)
    return [(params["class_ids"][i], float(probs[i])) for i in order if probs[i] >= params["threshold"]]


if __name__ == "__main__":
    import sys

    from langid_pipeline import featurize

    prm = init_params()
    text = sys.argv[1] if len(sys.argv) > 1 else "Hello, how are you doing today?"
    h = featurize(text)
    y = apply(prm, h)
    print("text      :", text)
    print("hashes    :", np.asarray(h).shape, "dtype", np.asarray(h).dtype)
    print("logits    :", "softmax", y.shape, "sum", float(y.sum()))
    print("top-3     :", [(prm["class_ids"][i], round(float(y[i]), 4))
                          for i in np.argsort(-np.asarray(y))[:3]])
