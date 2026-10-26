"""Numerical verification: generated JAX model vs ai_edge_litert interpreter.

Covers:
  1. random uniform inputs,
  2. random normal inputs,
  3. zeros / extreme dB constants,
  4. 50 synthetic measurement series passed through the Java-exact
     preprocessing (median filter) before inference.

Prints per-case max abs difference between output vectors and the class
decision; exits non-zero if any case exceeds 1e-4 (fp32 output tolerance).
"""

from __future__ import annotations

import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, HERE)

import model as generated  # noqa: E402
from preprocess import classify, make_input, synthetic_series  # noqa: E402
from ai_edge_litert.interpreter import Interpreter  # noqa: E402

MODEL = os.path.join(HERE, "..", "models", "0.3_new_0410_less.tflite")
TOL = 1e-4


def reference_factory():
    it = Interpreter(model_path=MODEL)
    it.allocate_tensors()
    inp = it.get_input_details()[0]
    out = it.get_output_details()[0]
    return it, inp, out


def main() -> int:
    # Java semantics self-check (medianFilter window 5, clipped edges).
    from preprocess import java_median_filter
    assert np.allclose(
        java_median_filter(np.array([1, 2, 3, 4, 5], np.float32)),
        [2.0, 2.5, 3.0, 3.5, 4.0]), "medianFilter edge semantics mismatch"
    assert np.allclose(
        java_median_filter(np.array([5, 5, 1, 9, 2, 2], np.float32)),
        [5.0, 5.0, 5.0, 2.0, 2.0, 2.0]), "medianFilter duplicate semantics mismatch"
    print("preprocess self-check: medianFilter(w=5) edge/even/duplicate semantics OK\n")

    params = generated.init_params()
    it, inp, out = reference_factory()
    cases = []
    rng = np.random.default_rng(1234)

    cases.append(("zeros", np.zeros((1, 400, 1), np.float32)))
    cases.append(("random_uniform", rng.uniform(-1, 1, (1, 400, 1)).astype(np.float32)))
    cases.append(("random_normal", rng.normal(0, 1, (1, 400, 1)).astype(np.float32)))
    const = np.zeros((1, 400, 1), np.float32)
    const[0, :100, 0] = -120.0
    const[0, 100:200, 0] = -18.0
    const[0, 200:300, 0] = -5.0
    const[0, 300:, 0] = 1.0
    cases.append(("constant_weak_dB", const))
    for i, kind in enumerate(("strong", "degrading", "weak", "improving")):
        cases.append((f"{kind}_series_seed0", make_input(synthetic_series(kind, 0))))
    for s in range(50):
        kinds = ("strong", "degrading", "weak", "improving")
        kind = kinds[s % 4]
        cases.append((f"series_seed{s}", make_input(synthetic_series(kind, s))))

    worst = 0.0
    failures = 0
    print(f"{'case':28s} {'shape':14s} {'max_abs':>10s} {'jax':>22s} {'litert':>22s} class")
    for name, x in cases:
        it.set_tensor(inp["index"], x)
        it.invoke()
        ref = np.asarray(it.get_tensor(out["index"])).reshape(-1)
        got = np.asarray(generated.apply(params, x)).reshape(-1)
        diff = float(np.max(np.abs(ref - got)))
        worst = max(worst, diff)
        ok = diff <= TOL
        failures += 0 if ok else 1
        print(f"{name:28s} {str(x.shape):14s} {diff:10.3e} "
              f"[{ref[0]:.8f},{ref[1]:.8f}] [{got[0]:.8f},{got[1]:.8f}] "
              f"{classify(got)} {'FAIL' if not ok else ''}")
    print(f"\ncases={len(cases)} worst_max_abs={worst:.3e} tol={TOL:.1e} "
          f"failures={failures}")
    return 0 if failures == 0 else 1


if __name__ == "__main__":
    raise SystemExit(main())
