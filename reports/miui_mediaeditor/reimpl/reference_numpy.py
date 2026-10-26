#!/usr/bin/env python3
"""Independent NumPy reference for the langid TFLite graph.

Deliberately written as a separate code path from model.py (explicit loops, no
jax) so that comparing the two checks the JAX port rather than re-running the
same code.  Weights are read straight from the flatbuffer via tflite_ir.
"""

from __future__ import annotations

import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
TOOLS = os.path.abspath(os.path.join(HERE, "..", "..", "..", "tools"))
if TOOLS not in sys.path:
    sys.path.insert(0, TOOLS)

import tflite_ir  # noqa: E402

MODEL = os.path.join(HERE, "..", "models", "tflite_langid.tflite")


class Ref:
    def __init__(self, model: str = MODEL):
        ir = tflite_ir.load_model(model)
        sg = ir.subgraph(0)
        self.b = [ir.constant(0, i).astype(np.float32) for i in (2, 4, 6, 8)]
        self.c = [ir.constant(0, i).astype(np.int64) for i in (3, 5, 7, 9)]
        self.w = [ir.constant(0, i).astype(np.int32) for i in (13, 14, 15)]
        self.bias = [ir.constant(0, i).astype(np.float32) for i in (10, 11, 12)]
        self.scale = [sg.tensors[i].quantization.scale[0] for i in (13, 14, 15)]
        # cross-check against params.npz produced by extract_params.py
        p = np.load(os.path.join(HERE, "params.npz"), allow_pickle=True)
        assert np.array_equal(self.c[0], p["t3"]), "c_matrix mismatch"
        assert np.array_equal(self.w[0], p["t13"]), "fc weight mismatch"
        assert np.allclose(self.bias[2], p["t12"]), "bias mismatch"

    def embeddings(self, hashes: np.ndarray) -> np.ndarray:
        out = np.empty(96, dtype=np.float32)
        for o in range(4):
            acc = np.zeros(24, dtype=np.float32)
            cnt = 0
            for h in hashes[o]:
                if h == 0:
                    break
                codes = self.c[o][h]               # 6 uint8 codes
                for j in range(6):
                    acc[4 * j:4 * j + 4] += self.b[o][codes[j]]
                cnt += 1
            out[24 * o:24 * o + 24] = acc / max(cnt, 1)
        return out

    def forward(self, hashes: np.ndarray) -> np.ndarray:
        x = self.embeddings(hashes)
        for i in range(3):
            wq = self.w[i].astype(np.float32) * np.float32(self.scale[i])
            x = x @ wq.T + self.bias[i]
            if i < 2:
                x = np.maximum(x, 0.0)
        e = np.exp(x - x.max())
        return e / e.sum()

    __call__ = forward


if __name__ == "__main__":
    import langid_pipeline as lp

    r = Ref()
    h = lp.featurize("Bonjour tout le monde")
    print("hashes", h.shape, "probs", r(h).shape, "top", int(np.argmax(r(h))))
