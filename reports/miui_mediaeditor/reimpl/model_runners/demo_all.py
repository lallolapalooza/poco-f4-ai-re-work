#!/usr/bin/env python3
"""Run every usable miui_mediaeditor TFLite model on real weights.

    $PY demo_all.py                # 33 models, writes demo_report.json
    $PY demo_all.py --only mask    # substring filter
    $PY demo_all.py --seed 7       # different synthetic frame

Each entry records the execution path (``jax`` reimplementation vs LiteRT
reference), the graph ops, input/output tensors, the family post-processing
summary, and — for JAX modules — the numeric cross-check against LiteRT on the
same input tensors.  Models that ``tools/tflite2jax.py`` cannot lower are not
crashes: they are executed through LiteRT and the conversion status is recorded
as ``skipped`` with the exact op or reason.

Always exits 0 once the report is written (a model-level failure is a recorded
status, not a process failure).
"""

from __future__ import annotations

import argparse
import json
import os
import sys
import time

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
if HERE not in sys.path:
    sys.path.insert(0, HERE)

import runner  # noqa: E402
import specs  # noqa: E402
from preprocess import synthetic_frame  # noqa: E402

DEFAULT_REPORT = os.path.join(HERE, "demo_report.json")


def _conversion(meta: dict, spec) -> dict:
    if spec.jax:
        return {
            "status": "converted",
            "runner": spec.jax,
            "jax_dir": os.path.relpath(spec.jax_dir, specs.REPORT),
        }
    ops = meta.get("unsupported_ops") or []
    return {
        "status": "skipped",
        "runner": "litert",
        "reason": spec.no_jax_reason,
        "unsupported_ops": ops,
    }


def run_all(only: str | None, seed: int, verify: bool) -> dict:
    image = synthetic_frame(256, 256, seed=seed)
    started = time.time()
    entries = []
    for spec in specs.SPECS:
        if only and only not in spec.name:
            continue
        t0 = time.time()
        try:
            entry = runner.ModelRunner(spec).execute(image, verify=verify)
        except Exception as exc:  # never let one model kill the sweep
            entry = {
                "status": "error",
                "error": f"{type(exc).__name__}: {exc}",
                "meta": spec.to_json(),
                "seconds": round(time.time() - t0, 2),
            }
        meta = entry.get("meta") or spec.to_json()
        entry["conversion"] = _conversion(meta, spec)
        if entry["status"] == "skip":
            entry["conversion"] = {
                "status": "skipped",
                "runner": "none",
                "reason": entry.get("skip_reason"),
                "unsupported_ops": meta.get("unsupported_ops") or [],
            }
        entries.append(entry)
        ver = entry.get("verification") or {}
        extra = ""
        if ver:
            extra = f" max_abs={ver['max_abs']:.3g}"
            sem = ver.get("semantic") or {}
            if sem and entry["status"] == "pass_semantic":
                extra += f" {sem['metric']}={sem['agreement']:.4f}"
        print(
            f"{entry['status']:15s} {entry.get('path', '-'):7s} "
            f"{meta.get('short', meta.get('name'))[:46]:46s}{extra}"
            f"  [{entry.get('seconds')}s]",
            flush=True,
        )

    counts: dict[str, int] = {}
    for e in entries:
        counts[e["status"]] = counts.get(e["status"], 0) + 1
    converted = sum(1 for e in entries if e["conversion"]["status"] == "converted")
    skipped = [e["meta"]["name"] for e in entries if e["conversion"]["status"] == "skipped"]
    report = {
        "target": "miui_mediaeditor",
        "models_total": len(entries),
        "seed": seed,
        "synthetic_frame": "256x256 gradient + soft face ellipse (preprocess.synthetic_frame)",
        "normalization_note": (
            "float inputs are scaled x/255; the exact FuAI mean/std is not in the "
            "recovered bundle configs (PIPELINE.md §3 step 5 / unknown U3)"
        ),
        "counts": counts,
        "converted": converted,
        "conversion_skipped": skipped,
        "by_name": {e["meta"]["name"]: e for e in entries},
        "seconds": round(time.time() - started, 1),
    }
    return report


def main(argv=None) -> int:
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--only", help="only models whose name contains this substring")
    ap.add_argument("--seed", type=int, default=0)
    ap.add_argument("--out", default=DEFAULT_REPORT)
    ap.add_argument("--no-verify", action="store_true",
                    help="skip the LiteRT cross-check (faster)")
    args = ap.parse_args(argv)

    problems = specs.check_registry()
    for p in problems:
        print("registry problem:", p, file=sys.stderr)

    report = run_all(args.only, args.seed, not args.no_verify)
    out = args.out
    if args.only and out == DEFAULT_REPORT:
        # never shrink the canonical full sweep with a filtered run
        out = os.path.join(HERE, "demo_report_only.json")
    with open(out, "w", encoding="utf-8") as f:
        json.dump(report, f, indent=2, sort_keys=True, default=str)
        f.write("\n")
    print()
    print(f"{report['models_total']} model(s): {report['counts']}")
    print(f"converted to JAX: {report['converted']}, "
          f"conversion skipped (LiteRT fallback): {len(report['conversion_skipped'])}")
    print(f"report: {out} ({report['seconds']}s)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
