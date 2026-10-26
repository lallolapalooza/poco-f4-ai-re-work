#!/usr/bin/env python3
"""Verify generated JAX code against the LiteRT reference interpreter.

Runs the model twice on identical inputs:

  reference : ai_edge_litert.interpreter.Interpreter (the real TFLite kernels)
  candidate : the generated ``model.py`` + ``params.npz`` from tflite2jax.py

Compares output shapes and values and writes a compact table.  Float outputs
use an absolute tolerance of 1e-4 (float16: 1e-2); quantized outputs are
compared after dequantization with a 0.02 tolerance; plain integer/bool
outputs must match exactly.

CLI::

    python verify_tflite_jax.py model.tflite
    python verify_tflite_jax.py samples/tflite/            # whole directory
    python verify_tflite_jax.py m.tflite --out converted/m --subgraph 0
    python verify_tflite_jax.py m.tflite --inputs inputs.npz --seed 7

Exit codes: 0 all comparable models pass (skips allowed), 1 value/shape
mismatch, 2 execution error or skipped model with --require-all.
"""

from __future__ import annotations

import argparse
import glob
import importlib.util
import json
import os
import sys
import tempfile
import traceback
from typing import Any, Dict, List, Optional, Sequence, Tuple

import numpy as np

import tflite_ir
import tflite2jax

os.environ.setdefault("JAX_PLATFORMS", "cpu")

TOL_FLOAT = 1e-4
TOL_FLOAT16 = 1e-2
TOL_QUANT = 0.02

_FLOAT_DTYPES = ("FLOAT16", "FLOAT32", "FLOAT64")
_QUANT_DTYPES = ("INT8", "UINT8", "INT16")
_NP_FOR_TFLITE = {
    "FLOAT16": np.float16,
    "FLOAT32": np.float32,
    "FLOAT64": np.float64,
    "INT8": np.int8,
    "INT16": np.int16,
    "INT32": np.int32,
    "INT64": np.int64,
    "UINT8": np.uint8,
    "UINT16": np.uint16,
    "UINT32": np.uint32,
    "UINT64": np.uint64,
    "BOOL": np.bool_,
}


class VerifyError(Exception):
    """Infrastructure problem (missing interpreter, unreadable module, ...)."""


# ---------------------------------------------------------------------------
# Inputs
# ---------------------------------------------------------------------------
def make_inputs(conv: "tflite2jax.Conversion", seed: int = 0) -> List[np.ndarray]:
    """Random inputs matching the declared dtype/shape of each graph input.

    Integers are kept small (0/1) except for quantized tensors, where the full
    storage range is exercised.  Use ``--inputs`` for deterministic/provided
    data.
    """
    rng = np.random.RandomState(seed)
    out: List[np.ndarray] = []
    for spec in conv.inputs:
        shape = tuple(int(d) for d in spec.shape)
        dtype = _NP_FOR_TFLITE.get(spec.dtype)
        if dtype is None:
            raise VerifyError(f"cannot generate input for dtype {spec.dtype}")
        if dtype in (np.float32, np.float16, np.float64):
            arr = rng.uniform(-1.0, 1.0, size=shape).astype(dtype)
        elif dtype == np.uint8:
            arr = rng.randint(0, 256, size=shape).astype(dtype)
        elif dtype == np.int8:
            arr = rng.randint(-128, 128, size=shape).astype(dtype)
        elif dtype == np.bool_:
            arr = rng.randint(0, 2, size=shape).astype(dtype)
        elif spec.quantization.get("scale"):
            info = np.iinfo(dtype)
            arr = rng.randint(info.min, info.max, size=shape, dtype=np.int64).astype(dtype)
        else:
            arr = rng.randint(0, 2, size=shape).astype(dtype)
        out.append(arr)
    return out


def load_inputs_npz(path: str, conv: "tflite2jax.Conversion") -> List[np.ndarray]:
    z = np.load(path)
    out: List[np.ndarray] = []
    for k, spec in enumerate(conv.inputs):
        keys = [spec.name, f"x{k}", f"input_{k}", str(k), str(spec.tensor_index)]
        found = next((key for key in keys if key in z.files), None)
        if found is None:
            raise VerifyError(
                f"{path}: no array for input {k} ({spec.name}); tried {keys}; "
                f"available: {z.files}"
            )
        arr = np.asarray(z[found])
        if tuple(arr.shape) != tuple(spec.shape):
            raise VerifyError(
                f"{path}: input {k} ({spec.name}) has shape {arr.shape}, expected {tuple(spec.shape)}"
            )
        out.append(arr.astype(_NP_FOR_TFLITE[spec.dtype], copy=False))
    return out


# ---------------------------------------------------------------------------
# Execution
# ---------------------------------------------------------------------------
def _import_model_module(outdir: str):
    path = os.path.join(outdir, "model.py")
    if not os.path.exists(path):
        raise VerifyError(f"{path} does not exist")
    name = "_tflite2jax_generated_" + str(abs(hash(os.path.abspath(outdir))))
    spec = importlib.util.spec_from_file_location(name, path)
    if spec is None or spec.loader is None:
        raise VerifyError(f"cannot import {path}")
    module = importlib.util.module_from_spec(spec)
    sys.modules[name] = module
    spec.loader.exec_module(module)
    return module


def run_reference(model_path: str, conv: "tflite2jax.Conversion", inputs: Sequence[np.ndarray]) -> List[np.ndarray]:
    try:
        from ai_edge_litert.interpreter import Interpreter, OpResolverType
    except Exception as exc:  # pragma: no cover - environment dependent
        raise VerifyError(f"ai_edge_litert is not available: {exc}") from exc
    if conv.subgraph.index != 0:
        raise VerifyError(
            "reference execution of a non-zero subgraph is not supported by the "
            "liteRT interpreter API; use --subgraph 0 or verify via a signature runner"
        )
    # Use the canonical TFLite reference kernels.  The default XNNPACK delegate
    # uses different (approximate) numerics for hybrid float/int8 ops, which
    # would make a bit-level comparison against the specification meaningless.
    resolver = getattr(OpResolverType, "BUILTIN_WITHOUT_DEFAULT_DELEGATES", None)
    if resolver is None:
        resolver = getattr(OpResolverType, "BUILTIN_REF", None)
    it = Interpreter(model_path=model_path, experimental_op_resolver_type=resolver)
    it.allocate_tensors()
    for spec, arr in zip(conv.inputs, inputs):
        it.set_tensor(spec.tensor_index, np.asarray(arr))
    it.invoke()
    return [np.asarray(it.get_tensor(spec.tensor_index)) for spec in conv.outputs]


def run_jax(conv: "tflite2jax.Conversion", outdir: str, inputs: Sequence[np.ndarray]) -> List[np.ndarray]:
    module = _import_model_module(outdir)
    params = module.init_params()
    result = module.apply(params, *inputs)
    if isinstance(result, tuple):
        outputs = list(result)
    else:
        outputs = [result]
    if len(outputs) != len(conv.outputs):
        raise VerifyError(f"apply() returned {len(outputs)} outputs, expected {len(conv.outputs)}")
    return [np.asarray(v) for v in outputs]


# ---------------------------------------------------------------------------
# Comparison
# ---------------------------------------------------------------------------
def _dequantize_np(arr: np.ndarray, spec: "tflite2jax.InputSpec") -> np.ndarray:
    q = spec.quantization or {}
    scale = np.asarray(q.get("scale", []), dtype=np.float64)
    if scale.size == 0:
        return arr.astype(np.float64)
    zp = np.asarray(q.get("zero_point", []) or [0], dtype=np.float64)
    out = arr.astype(np.float64)
    if scale.size > 1:
        axis = int(q.get("quantized_dimension", 0)) % out.ndim
        shape = [1] * out.ndim
        shape[axis] = scale.size
        scale = scale.reshape(shape)
        zp = zp.reshape(shape)
    return (out - zp) * scale


def _tolerance(spec: "tflite2jax.InputSpec") -> Tuple[str, float]:
    if spec.dtype in _QUANT_DTYPES and spec.quantization.get("scale"):
        return "dequantized", TOL_QUANT
    if spec.dtype == "FLOAT16":
        return "float16", TOL_FLOAT16
    if spec.dtype in _FLOAT_DTYPES:
        return "absolute", TOL_FLOAT
    if spec.dtype == "BOOL":
        return "exact", 0.0
    return "exact", 0.0


def _max_abs(a: np.ndarray, b: np.ndarray) -> float:
    if a.size == 0:
        return 0.0
    a = a.astype(np.float64)
    b = b.astype(np.float64)
    both_nan = np.isnan(a) & np.isnan(b)
    if both_nan.any():
        a = np.where(both_nan, 0.0, a)
        b = np.where(both_nan, 0.0, b)
    same_inf = np.isinf(a) & np.isinf(b) & (np.sign(a) == np.sign(b))
    if same_inf.any():
        a = np.where(same_inf, 0.0, a)
        b = np.where(same_inf, 0.0, b)
    diff = np.abs(a - b)
    if np.isnan(diff).any():
        return float("inf")
    return float(diff.max())


def compare(
    conv: "tflite2jax.Conversion",
    jax_outputs: Sequence[np.ndarray],
    ref_outputs: Sequence[np.ndarray],
) -> List[Dict[str, Any]]:
    rows: List[Dict[str, Any]] = []
    for spec, got, want in zip(conv.outputs, jax_outputs, ref_outputs):
        got = np.asarray(got)
        want = np.asarray(want)
        kind, tol = _tolerance(spec)
        shape_ok = tuple(got.shape) == tuple(want.shape)
        if not shape_ok:
            rows.append(
                {
                    "name": spec.name,
                    "tensor_index": spec.tensor_index,
                    "dtype": spec.dtype,
                    "kind": kind,
                    "shape": list(want.shape),
                    "jax_shape": list(got.shape),
                    "max_abs": None,
                    "tol": tol,
                    "ok": False,
                    "error": "shape mismatch",
                }
            )
            continue
        if kind == "dequantized":
            max_abs = _max_abs(_dequantize_np(got, spec), _dequantize_np(want, spec))
        else:
            max_abs = _max_abs(got, want)
        dtype_ok = got.dtype == want.dtype
        ok = bool(max_abs <= tol) and dtype_ok
        rows.append(
            {
                "name": spec.name,
                "tensor_index": spec.tensor_index,
                "dtype": spec.dtype,
                "kind": kind,
                "shape": list(want.shape),
                "jax_dtype": str(got.dtype),
                "ref_dtype": str(want.dtype),
                "dtype_ok": bool(dtype_ok),
                "max_abs": max_abs,
                "tol": tol,
                "ok": ok,
            }
        )
    return rows


# ---------------------------------------------------------------------------
# Report
# ---------------------------------------------------------------------------
def _build_report(
    conv: "tflite2jax.Conversion",
    rows: List[Dict[str, Any]],
    status: str,
    error: Optional[str] = None,
) -> Dict[str, Any]:
    ok = status == "pass"
    return {
        "status": status,
        "ok": ok,
        "model": conv.ir.path,
        "model_file": os.path.basename(conv.ir.path),
        "subgraph_index": conv.subgraph.index,
        "subgraph_name": conv.subgraph.name,
        "signature_key": conv.signature.key if conv.signature else None,
        "selection_reason": conv.selection_reason,
        "reference": "ai_edge_litert Interpreter with reference kernels (no XNNPACK delegate)",
        "tolerances": {
            "float32": TOL_FLOAT,
            "float16": TOL_FLOAT16,
            "quantized_dequantized": TOL_QUANT,
        },
        "rows": rows,
        "max_abs": max((r["max_abs"] for r in rows if r.get("max_abs") is not None), default=0.0),
        "error": error,
    }


def _fmt(x: Any) -> str:
    if x is None:
        return "-"
    if isinstance(x, float):
        if x == 0:
            return "0"
        if x < 1e-3 or x >= 1e4:
            return f"{x:.2e}"
        return f"{x:.6g}"
    return str(x)


def render_text(report: Dict[str, Any]) -> str:
    title = (
        f"{report.get('model_file')}  subgraph={report.get('subgraph_index')} "
        f"signature={report.get('signature_key')}  "
        f"{report.get('status', 'error').upper()}"
    )
    lines = [title]
    if report.get("error"):
        lines.append(f"  error: {report['error']}")
    rows = report.get("rows") or []
    if rows:
        widths = (
            max([len("output")] + [len(str(r["name"])) for r in rows]),
            max([len("shape")] + [len(str(tuple(r["shape"]))) for r in rows]),
            max([len("dtype")] + [len(str(r["dtype"])) for r in rows]),
            max([len("max_abs")] + [len(_fmt(r.get("max_abs"))) for r in rows]),
            max([len("tol")] + [len(_fmt(r.get("tol"))) for r in rows]),
        )
        lines.append(
            "  "
            + "  ".join(
                [
                    "output".ljust(widths[0]),
                    "shape".ljust(widths[1]),
                    "dtype".ljust(widths[2]),
                    "max_abs".ljust(widths[3]),
                    "tol".ljust(widths[4]),
                    "result",
                ]
            )
        )
        for r in rows:
            lines.append(
                "  "
                + "  ".join(
                    [
                        str(r["name"])[: widths[0]].ljust(widths[0]),
                        str(tuple(r["shape"])).ljust(widths[1]),
                        str(r["dtype"]).ljust(widths[2]),
                        _fmt(r.get("max_abs")).ljust(widths[3]),
                        _fmt(r.get("tol")).ljust(widths[4]),
                        ("PASS" if r.get("ok") else
                         ("FAIL(dtype)" if r.get("dtype_ok") is False else "FAIL")),
                    ]
                )
            )
    else:
        lines.append("  no outputs compared")
    lines.append(
        f"  {len(rows)} output(s), max_abs={_fmt(report.get('max_abs'))}, "
        f"status={report.get('status')}"
    )
    return "\n".join(lines)


def write_report(report: Dict[str, Any], outdir: str) -> None:
    os.makedirs(outdir, exist_ok=True)
    with open(os.path.join(outdir, "verify.txt"), "w", encoding="utf-8") as f:
        f.write(render_text(report) + "\n")
    with open(os.path.join(outdir, "verify.json"), "w", encoding="utf-8") as f:
        json.dump(report, f, indent=2, sort_keys=True, default=str)
        f.write("\n")


def verify_conversion(
    conv: "tflite2jax.Conversion",
    outdir: str,
    inputs: Optional[Sequence[np.ndarray]] = None,
    seed: int = 0,
    write: bool = True,
) -> Dict[str, Any]:
    """Execute the reference and generated model, compare, and write verify.txt."""
    report: Dict[str, Any]
    try:
        if inputs is None:
            inputs = make_inputs(conv, seed=seed)
        if len(inputs) != len(conv.inputs):
            raise VerifyError(
                f"got {len(inputs)} input arrays, model needs {len(conv.inputs)}"
            )
        jax_outputs = run_jax(conv, outdir, inputs)
        ref_outputs = run_reference(conv.ir.path, conv, inputs)
        rows = compare(conv, jax_outputs, ref_outputs)
        status = "pass" if all(r.get("ok") for r in rows) else "fail"
        report = _build_report(conv, rows, status)
    except Exception as exc:
        report = _build_report(conv, [], "error", f"{type(exc).__name__}: {exc}")
        report["traceback"] = traceback.format_exc()
    if write:
        write_report(report, outdir)
    return report


# ---------------------------------------------------------------------------
# CLI
# ---------------------------------------------------------------------------
def _expand_targets(targets: Sequence[str]) -> List[str]:
    paths: List[str] = []
    for target in targets:
        if os.path.isdir(target):
            found = sorted(glob.glob(os.path.join(target, "*.tflite")))
            if not found:
                raise VerifyError(f"no .tflite files in {target}")
            paths.extend(found)
        else:
            paths.append(target)
    return paths


def main(argv: Optional[Sequence[str]] = None) -> int:
    ap = argparse.ArgumentParser(description="Verify TFLite -> JAX conversions")
    ap.add_argument("models", nargs="+", help=".tflite files or directories")
    ap.add_argument("--out", default=None, help="output directory (single model) / root (multiple)")
    ap.add_argument("--subgraph", type=int, default=None)
    ap.add_argument("--signature", default=None)
    ap.add_argument("--inputs", default=None, help=".npz with input arrays (by name or x0/input_0/0)")
    ap.add_argument("--seed", type=int, default=0)
    ap.add_argument("--require-all", action="store_true", help="fail on skipped/unsupported models")
    ap.add_argument("--json", default=None, help="write combined JSON report here")
    ap.add_argument("--quiet", action="store_true")
    args = ap.parse_args(argv)

    try:
        paths = _expand_targets(args.models)
    except VerifyError as exc:
        print(f"error: {exc}", file=sys.stderr)
        return 2

    if args.out and len(paths) > 1:
        multi_out = True
    else:
        multi_out = False

    reports: List[Dict[str, Any]] = []
    failures = 0
    errors = 0
    skips = 0

    for path in paths:
        stem = os.path.splitext(os.path.basename(path))[0]
        outdir = args.out or os.path.join(tempfile.gettempdir(), "tflite2jax_verify", stem)
        if multi_out:
            outdir = os.path.join(args.out, stem)
        try:
            ir = tflite_ir.load_model(path, with_stats=False)
            conv = tflite2jax.convert_ir(ir, subgraph=args.subgraph, signature=args.signature)
        except tflite2jax.UnsupportedModel as exc:
            skips += 1
            payload = tflite2jax.write_unsupported(exc, outdir) if args.out else exc.to_json()
            entry = {
                "status": "skip",
                "ok": True,
                "skipped": True,
                "model": os.path.abspath(path),
                "model_file": os.path.basename(path),
                "skip_status": exc.status,
                "reason": exc.reason,
                "subgraph_index": exc.sg.index,
                "signature_key": exc.signature.key if exc.signature else None,
                "unsupported_ops": exc.unsupported_ops,
            }
            reports.append(entry)
            print(f"SKIP {stem}  {exc.status}: {exc.reason}", file=sys.stderr)
            continue
        except Exception as exc:
            errors += 1
            reports.append(
                {
                    "status": "error",
                    "ok": False,
                    "model": os.path.abspath(path),
                    "model_file": os.path.basename(path),
                    "error": f"{type(exc).__name__}: {exc}",
                }
            )
            print(f"ERROR {stem}: {type(exc).__name__}: {exc}", file=sys.stderr)
            continue

        tflite2jax.write_conversion(conv, outdir)
        provided = load_inputs_npz(args.inputs, conv) if args.inputs else None
        report = verify_conversion(conv, outdir, inputs=provided, seed=args.seed)
        report["model"] = os.path.abspath(path)
        reports.append(report)
        if report["status"] == "fail":
            failures += 1
        elif report["status"] == "error":
            errors += 1
        if not args.quiet:
            print(render_text(report))

    if args.json:
        payload = {
            "reports": reports,
            "counts": {
                "total": len(reports),
                "failures": failures,
                "errors": errors,
                "skips": skips,
            },
        }
        with open(args.json, "w", encoding="utf-8") as f:
            json.dump(payload, f, indent=2, sort_keys=True, default=str)
            f.write("\n")

    print(
        f"summary: {len(reports)} model(s), {failures} failed, {errors} error(s), {skips} skipped",
        file=sys.stderr,
    )
    if failures:
        return 1
    if errors:
        return 2
    if skips and args.require_all:
        return 2
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
