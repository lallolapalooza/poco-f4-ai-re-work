#!/usr/bin/env python3
"""Extract per-subgraph op lists and the variable initializer weights of
assets/aiautobrt.tflite (TFL3, 7 subgraphs).

Outputs (next to this script):
  ops_sg<N>.txt     one line per operator with resolved tensor names/shapes
  weights.npz       variable name -> initial value taken from subgraph 6
                    (the CALL_ONCE init subgraph), for every non-optimizer var
  weights.json      same values as JSON lists plus provenance
  tensor_table.json every tensor of every subgraph (index, name, shape, dtype,
                    is_constant, buffer_index, buffer_bytes)

Only the flatbuffer is read (tools/tflite bindings); no TF is used.
"""
import json
import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
WS = os.path.abspath(os.path.join(HERE, "..", "..", ".."))
sys.path.insert(0, os.path.join(WS, "tools"))

import tflite_ir  # noqa: E402

MODEL = os.path.join(HERE, "..", "models", "aiautobrt.tflite")


def shared_name(op):
    sn = op.options.get("SharedName") if op.options else None
    if not sn:
        return None
    try:
        return bytes.fromhex(sn).decode("utf-8")
    except ValueError:
        return sn


def tstr(sg, i):
    t = sg.tensor(i)
    flags = []
    if t.is_constant:
        flags.append("const")
    if t.is_variable:
        flags.append("var")
    shape = t.shape if t.shape is not None else []
    return f"{i}:{t.name or '?'}{list(shape)}:{t.dtype}" + ("[" + ",".join(flags) + "]" if flags else "")


def dump_ops(model):
    for sg in model.subgraphs:
        lines = [
            f"# subgraph {sg.index} name={sg.name!r} role={sg.role} "
            f"inputs={sg.inputs} outputs={sg.outputs} tensors={len(sg.tensors)} ops={len(sg.operators)}"
        ]
        for op in sg.operators:
            lines.append(
                f"[{op.index:3d}] {op.op:20s} inputs={[tstr(sg, i) for i in op.inputs]} "
                f"outputs={[tstr(sg, i) for i in op.outputs]} options={op.options}"
            )
        out = os.path.join(HERE, f"ops_sg{sg.index}.txt")
        with open(out, "w") as f:
            f.write("\n".join(lines) + "\n")
        print("wrote", out, len(sg.operators), "ops")


def tensor_table(model):
    table = {}
    for sg in model.subgraphs:
        rows = []
        for t in sg.tensors:
            rows.append(
                {
                    "index": t.index,
                    "name": t.name,
                    "shape": list(t.shape) if t.shape is not None else [],
                    "shape_signature": list(t.shape_signature) if t.shape_signature is not None else None,
                    "dtype": t.dtype,
                    "is_constant": bool(t.is_constant),
                    "is_variable": bool(t.is_variable),
                    "buffer_index": t.buffer_index,
                    "buffer_bytes": int(t.buffer_bytes or 0),
                }
            )
        table[str(sg.index)] = {"name": sg.name, "role": sg.role, "tensors": rows}
    with open(os.path.join(HERE, "tensor_table.json"), "w") as f:
        json.dump(table, f, indent=1)
    print("wrote tensor_table.json")


def extract_weights(model):
    sg = model.subgraph(6)
    consts = sg.const_map
    # variable handle tensor index -> shared name (the canonical variable name)
    var_shared = {}
    for op in sg.operators:
        if op.op == "VAR_HANDLE" and op.outputs:
            var_shared[op.outputs[0]] = shared_name(op)

    weights = {}
    provenance = {}
    for op in sg.operators:
        if op.op != "ASSIGN_VARIABLE":
            continue
        var_idx, const_idx = op.inputs
        name = var_shared.get(var_idx, sg.tensor(var_idx).name)
        arr = consts.get(const_idx)
        if arr is None:
            raise SystemExit(f"missing constant for tensor {const_idx}")
        provenance[name] = {
            "variable_tensor": var_idx,
            "variable_tensor_name": sg.tensor(var_idx).name,
            "constant_tensor": const_idx,
            "constant_tensor_name": sg.tensor(const_idx).name,
            "buffer_index": sg.tensor(const_idx).buffer_index,
            "shape": list(arr.shape),
            "dtype": str(arr.dtype),
        }
        weights[name] = np.asarray(arr)

    np.savez(os.path.join(HERE, "weights.npz"), **weights)

    js = {
        "model": os.path.abspath(MODEL),
        "source_subgraph": 6,
        "note": "Values are the constants ASSIGN_VARIABLE'd by the CALL_ONCE init subgraph 6; "
                "they are the variables' initial values in the flatbuffer (checkpoint restore "
                "through the 'restore' signature can override the dnn_model/* ones at runtime).",
        "tensors": {},
    }
    for name, arr in weights.items():
        js["tensors"][name] = {
            "shape": list(arr.shape),
            "values": np.asarray(arr).reshape(-1).tolist(),
            "provenance": provenance[name],
        }
    with open(os.path.join(HERE, "weights.json"), "w") as f:
        json.dump(js, f, indent=1)
    print(f"wrote weights.npz / weights.json ({len(weights)} variables)")

    # Human-readable summary for the inference models.
    for prefix in ("dnn_model/", "dnn_model_1/"):
        print(f"--- {prefix} ---")
        for name in sorted(weights):
            if name.startswith(prefix):
                a = weights[name]
                print(f"  {name:38s} {str(list(a.shape)):10s} min={a.min():+.6f} "
                      f"max={a.max():+.6f} mean={a.mean():+.6f} absmax={np.abs(a).max():.6f}")


def main():
    model = tflite_ir.load_model(MODEL, with_stats=True)
    dump_ops(model)
    tensor_table(model)
    extract_weights(model)


if __name__ == "__main__":
    main()
