"""End-to-end weak-signal forecast pipeline (synthetic measurements -> class).

AI feature: com.xiaomi.mtb.aiservices.AiWeakSignalForecastService
  every 100 ms : RSRP/RSRQ/SINR + PCI-change -> 100-entry deque
  every 1000 ms: deque -> median filter -> [1,400,1] -> TFLite -> WEAK/STRONG

Run:
    python pipeline.py                 # JAX reimplementation (real weights)
    python pipeline.py --reference     # ai_edge_litert TFLite interpreter

The model is a radio-signal classifier, not an audio classifier; there is no
waveform/MFCC stage anywhere in the app's use of this model.
"""

from __future__ import annotations

import argparse
import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
MODEL_PATH = os.path.join(HERE, "..", "models", "0.3_new_0410_less.tflite")

sys.path.insert(0, HERE)
from preprocess import classify, make_input, synthetic_series  # noqa: E402


def run_jax(x: np.ndarray) -> np.ndarray:
    import model as generated  # tflite2jax output, same directory

    params = generated.init_params()
    y = generated.apply(params, x)
    return np.asarray(y)


def run_reference(x: np.ndarray) -> np.ndarray:
    from ai_edge_litert.interpreter import Interpreter

    it = Interpreter(model_path=MODEL_PATH)
    it.allocate_tensors()
    inp = it.get_input_details()[0]
    out = it.get_output_details()[0]
    it.set_tensor(inp["index"], x)
    it.invoke()
    return it.get_tensor(out["index"])


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--reference", action="store_true",
                    help="use the TFLite interpreter instead of JAX")
    ap.add_argument("--seed", type=int, default=0)
    args = ap.parse_args()

    run = run_reference if args.reference else run_jax
    print(f"backend: {'ai_edge_litert' if args.reference else 'jax'}")
    for kind in ("strong", "degrading", "weak", "improving"):
        series = synthetic_series(kind, seed=args.seed)
        x = make_input(series)
        y = run(x)
        p = np.asarray(y).reshape(-1)
        print(f"{kind:10s} series{series.shape} -> input{x.shape} {x.dtype} "
              f"-> probs=[{p[0]:.6f}, {p[1]:.6f}] -> {classify(p)} "
              f"(p_weak={p[0]:.6f})")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
