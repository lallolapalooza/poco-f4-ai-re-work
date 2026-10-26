#!/usr/bin/env python3
"""Regenerate the JAX modules under ``generated/`` from the real TFLite files.

    $PY convert_models.py            # every non-hard-skip model without a module
    $PY convert_models.py --only s3  # one model (substring match)
    $PY convert_models.py --force    # re-run even where model.py exists

Each model is converted with ``$WS/tools/tflite2jax.py --out generated/<name>``;
that tool also runs the LiteRT cross-check and writes ``model.py``,
``params.npz``, ``ir.json`` and ``verify.{txt,json}``.  Unconvertible graphs get
``unsupported.json`` (status, unsupported ops, per-op build errors) and are left
to the LiteRT fallback in ``runner.py``.

Models that already have a hand-maintained JAX module in ``reimpl/bundles/``
are *not* duplicated: their spec points there directly.
"""

from __future__ import annotations

import argparse
import os
import subprocess
import sys

HERE = os.path.dirname(os.path.abspath(__file__))
if HERE not in sys.path:
    sys.path.insert(0, HERE)

import specs  # noqa: E402

TFLITE2JAX = os.path.join(specs.WORKSPACE, "tools", "tflite2jax.py")


def main(argv=None) -> int:
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--only", help="substring filter on the model name")
    ap.add_argument("--force", action="store_true", help="reconvert existing outputs")
    args = ap.parse_args(argv)

    if not os.path.exists(TFLITE2JAX):
        print(f"error: {TFLITE2JAX} not found", file=sys.stderr)
        return 2

    planned = []
    for spec in specs.SPECS:
        if spec.hard_skip or spec.jax == "bundles":
            continue
        if args.only and args.only not in spec.name:
            continue
        outdir = os.path.join(specs.GENERATED_ROOT, spec.name)
        if os.path.exists(os.path.join(outdir, "model.py")) and not args.force:
            continue
        planned.append((spec, outdir))

    if not planned:
        print("nothing to do (all convertibles have a generated module)")
        return 0

    failures = 0
    for spec, outdir in planned:
        print(f"== {spec.name}", flush=True)
        cmd = [sys.executable, TFLITE2JAX, spec.tflite_path, "--out", outdir, "--quiet"]
        rc = subprocess.run(cmd).returncode
        if rc == 0:
            print("   converted + verified (see verify.txt)")
        elif rc == 2:
            print("   unsupported by tools/tflite2jax.py (see unsupported.json); "
                  "runner uses LiteRT")
        else:
            failures += 1
            print(f"   conversion error rc={rc}")
    return 1 if failures else 0


if __name__ == "__main__":
    raise SystemExit(main())
