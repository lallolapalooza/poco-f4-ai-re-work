#!/usr/bin/env python3
"""End-to-end example: convert a TFLite model, verify it, and run the result.

This is a thin, readable wrapper around the other modules and produces the
layout later agents can consume directly::

    outdir/
      model.py      self-contained init_params()/apply() (jax + numpy only)
      params.npz    weights, stored as numpy arrays
      ir.json       deterministic flatbuffer IR + conversion metadata
      verify.txt    comparison against ai_edge_litert (when verification runs)
      verify.json   machine-readable verification report
      unsupported.json  present instead of model.py when the model cannot run

Examples::

    python tflite_to_jax_example.py model.tflite --out out/model
    python tflite_to_jax_example.py samples/tflite --out out/corpus
    python tflite_to_jax_example.py model.tflite --subgraph 1 --no-verify
"""

from __future__ import annotations

import argparse
import glob
import importlib.util
import os
import sys
from typing import Any, List, Optional, Sequence

import numpy as np

import tflite_ir
import tflite2jax


def _load_module(outdir: str):
    path = os.path.join(outdir, "model.py")
    spec = importlib.util.spec_from_file_location("_tflite2jax_example", path)
    if spec is None or spec.loader is None:
        raise RuntimeError(f"cannot import {path}")
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    return module


def _demo_call(conv: "tflite2jax.Conversion", outdir: str, seed: int = 0) -> str:
    """Import the generated module and run it on random inputs."""
    import verify_tflite_jax as verify

    module = _load_module(outdir)
    params = module.init_params()
    inputs = verify.make_inputs(conv, seed=seed)
    result = module.apply(params, *inputs)
    outputs = list(result) if isinstance(result, tuple) else [result]
    lines = []
    for spec, value in zip(conv.outputs, outputs):
        value = np.asarray(value)
        finite = value[np.isfinite(value)] if np.issubdtype(value.dtype, np.floating) else value
        rng = f"[{float(finite.min()):.4g}, {float(finite.max()):.4g}]" if finite.size else "[]"
        lines.append(f"  {spec.name!r:35s} {str(tuple(value.shape)):16s} {str(value.dtype):8s} range={rng}")
    return "\n".join(lines)


def _one(
    path: str,
    outdir: str,
    subgraph: Optional[int],
    signature: Optional[str],
    verify: bool,
    seed: int,
    quiet: bool,
) -> str:
    """Returns 'pass' | 'fail' | 'skip' | 'error'."""
    try:
        ir = tflite_ir.load_model(path, with_stats=False)
        conv = tflite2jax.convert_ir(ir, subgraph=subgraph, signature=signature)
    except tflite2jax.UnsupportedModel as exc:
        tflite2jax.write_unsupported(exc, outdir)
        print(
            f"SKIP {os.path.basename(path)}  {exc.status}: {exc.reason}\n"
            f"     details: {os.path.join(outdir, 'unsupported.json')}"
        )
        return "skip"
    except Exception as exc:
        print(f"ERROR {os.path.basename(path)}: {type(exc).__name__}: {exc}")
        return "error"

    tflite2jax.write_conversion(conv, outdir)
    status = "pass"
    if verify:
        import verify_tflite_jax

        report = verify_tflite_jax.verify_conversion(conv, outdir, seed=seed)
        print(verify_tflite_jax.render_text(report))
        if not report["ok"]:
            status = "fail"
    if not quiet:
        try:
            demo = _demo_call(conv, outdir, seed=seed)
        except Exception as exc:
            demo = f"  <demo execution failed: {type(exc).__name__}: {exc}>"
        print(f"  subgraph={conv.subgraph.index} signature={conv.signature.key if conv.signature else None}")
        print(f"  parameters: {len(conv.params)} tensors -> {os.path.join(outdir, 'params.npz')}")
        print("  demo apply() output:")
        print(demo)
        print(
            "  API: import model; p = model.init_params(); "
            + ("y = model.apply(p, x)" if len(conv.inputs) == 1 else "ys = model.apply(p, x0, x1, ...)")
        )
    print()
    return status


def main(argv: Optional[Sequence[str]] = None) -> int:
    ap = argparse.ArgumentParser(description="Convert + verify + run a TFLite model with JAX")
    ap.add_argument("model", nargs="+", help=".tflite file(s) or directories")
    ap.add_argument("--out", default=None, help="output directory (default: converted/<stem>)")
    ap.add_argument("--subgraph", type=int, default=None, help="subgraph index")
    ap.add_argument("--signature", default=None, help="signature key")
    ap.add_argument("--no-verify", action="store_true", help="skip ai_edge_litert comparison")
    ap.add_argument("--seed", type=int, default=0)
    ap.add_argument("--quiet", action="store_true", help="do not run the demo apply()")
    args = ap.parse_args(argv)

    paths: List[str] = []
    for target in args.model:
        if os.path.isdir(target):
            found = sorted(glob.glob(os.path.join(target, "*.tflite")))
            if not found:
                print(f"no .tflite files in {target}", file=sys.stderr)
                return 2
            paths.extend(found)
        else:
            paths.append(target)

    multi = len(paths) > 1
    counts = {"pass": 0, "fail": 0, "skip": 0, "error": 0}
    for path in paths:
        stem = os.path.splitext(os.path.basename(path))[0]
        if args.out is None:
            outdir = os.path.join("converted", stem)
        elif multi:
            outdir = os.path.join(args.out, stem)
        else:
            outdir = args.out
        print(f"=== {os.path.basename(path)} -> {outdir}")
        counts[_one(path, outdir, args.subgraph, args.signature, not args.no_verify, args.seed, args.quiet)] += 1

    print(
        f"summary: {len(paths)} model(s): {counts['pass']} pass, {counts['fail']} fail, "
        f"{counts['skip']} skip, {counts['error']} error"
    )
    if counts["fail"]:
        return 1
    if counts["error"]:
        return 2
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
