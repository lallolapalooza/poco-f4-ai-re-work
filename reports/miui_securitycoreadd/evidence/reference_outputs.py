#!/usr/bin/env python3
"""Dump reference outputs of the two inference subgraphs from the LiteRT
interpreter for a fixed input grid (reproducible evidence).

    python evidence/reference_outputs.py > evidence/interpreter_reference.txt
"""
import os
import sys

import numpy as np
from ai_edge_litert.interpreter import Interpreter

HERE = os.path.dirname(os.path.abspath(__file__))
MODEL = os.path.join(HERE, "..", "models", "aiautobrt.tflite")

# x = [log(lux+1), log(lux_span+1), log(app_category+1)]  (f3.c.a / g3.a)
LUX = [0.0, 1.0, 8.0, 50.0, 200.0, 1000.0, 5000.0, 20000.0, 50000.0]
APP = [0.0, 1.0, 2.0, 7.0, 15.0]

xs = np.array([[np.log1p(l), np.log1p(l), np.log1p(a)] for l in LUX for a in APP],
              dtype=np.float32)

it = Interpreter(model_path=MODEL)
it.allocate_tensors()
new = it.get_signature_runner("new_model_infer")
old = it.get_signature_runner("old_model_infer")

print("model:", os.path.abspath(MODEL))
print("signatures:", sorted(it.get_signature_list()))
print("input x:", xs.shape, "= [log1p(lux), log1p(lux_span), log1p(app_category_id)]")
print()
print(f"{'lux':>8} {'app':>4} {'log1p(lux)':>11} {'log1p(app)':>10} "
      f"{'old_model_infer':>16} {'new_model_infer':>16}")
for row, l, a in zip(xs, [l for l in LUX for _ in APP], [a for _ in LUX for a in APP]):
    po = float(np.asarray(old(x=row[None, :])["pre"]).ravel()[0])
    pn = float(np.asarray(new(x=row[None, :])["pre"]).ravel()[0])
    print(f"{l:8.1f} {a:4.1f} {row[0]:11.5f} {row[2]:10.5f} {po:16.5f} {pn:16.5f}")

print()
print("batch invocation (all rows at once):")
print("  old:", np.asarray(old(x=xs)["pre"]).ravel())
print("  new:", np.asarray(new(x=xs)["pre"]).ravel())
