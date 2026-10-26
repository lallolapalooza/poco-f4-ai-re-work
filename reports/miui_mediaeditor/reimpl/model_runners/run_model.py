#!/usr/bin/env python3
"""Run one miui_mediaeditor TFLite model on real weights.

    $PY run_model.py --list
    $PY run_model.py --model beauty_type.dense_iris        # synthetic frame
    $PY run_model.py --model face_parsing --image face.png # PPM/PNG/BMP/NPY
    $PY run_model.py --model head --json out.json

Model names are the file stems (``model__<bundle>__<model>``); the short form
without the ``model__<bundle>__`` prefix is accepted when unambiguous.
``--image`` may be omitted: a deterministic 256x256 synthetic frame is used, so
every model is runnable without any asset.

Exit codes: 0 = the model ran (JAX or LiteRT reference, including the recorded
fallbacks), 1 = execution/verification error, 2 = bad CLI/model name.
"""

from __future__ import annotations

import argparse
import json
import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
if HERE not in sys.path:
    sys.path.insert(0, HERE)

import runner  # noqa: E402
import specs  # noqa: E402
from image_io import ImageError, load_image  # noqa: E402
from preprocess import synthetic_frame  # noqa: E402


def _print_entry(entry: dict) -> None:
    meta = entry.get("meta", {})
    print(f"model      : {meta.get('name')}")
    print(f"bundle     : {meta.get('bundle')}   family: {meta.get('family')}")
    print(f"tflite     : {meta.get('tflite')}")
    print(f"ops        : {meta.get('op_count')} ({', '.join(meta.get('ops', []))})")
    for t in meta.get("inputs", []):
        print(f"input      : {t['name']} {t['shape']} {t['dtype']}")
    prov = meta.get("input_provenance", {})
    for name, p in prov.items():
        print(f"             {name}: {p['role']} <- {p['provenance']}")
    print(f"runner     : {entry.get('path')}")
    print(f"status     : {entry.get('status')}")
    if entry.get("fallback_reason"):
        print(f"conversion : skipped ({entry['fallback_reason']})")
    if entry.get("skip_reason"):
        print(f"skip       : {entry['skip_reason']}")
    if entry.get("error"):
        print(f"error      : {entry['error']}")
    for out in entry.get("outputs", []):
        print(f"output     : {out['name']} {out['shape']} {out['dtype']}")
    ver = entry.get("verification")
    if ver:
        print(f"verify     : {ver['status']}  max_abs={ver['max_abs']:.3g}")
        if ver.get("semantic"):
            s = ver["semantic"]
            print(f"             {s['metric']}={s['agreement']:.6f}")
    pp = entry.get("postprocess") or {}
    print("postprocess:")
    print(json.dumps(pp, indent=2, sort_keys=True))
    print(f"seconds    : {entry.get('seconds')}")


def main(argv=None) -> int:
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--model", help="model name (see --list)")
    ap.add_argument("--image", help="image file (PNG/PNM/BMP/NPY; JPEG needs Pillow)")
    ap.add_argument("--list", action="store_true", help="list all registered models")
    ap.add_argument("--json", help="write the full run record as JSON here")
    ap.add_argument("--force-litert", action="store_true",
                    help="run the LiteRT reference even when a JAX module exists")
    ap.add_argument("--no-verify", action="store_true",
                    help="skip the LiteRT cross-check for JAX modules")
    args = ap.parse_args(argv)

    if args.list:
        for s in specs.SPECS:
            kind = s.jax or ("skip" if s.hard_skip else "litert")
            print(f"{s.name:95s} {kind:9s} {s.family}")
        problems = specs.check_registry()
        for p in problems:
            print("registry problem:", p, file=sys.stderr)
        return 0 if not problems else 1

    if not args.model:
        ap.error("--model is required (or use --list)")

    try:
        spec = specs.resolve(args.model)
    except KeyError as exc:
        print(f"error: {exc}", file=sys.stderr)
        return 2

    if args.image:
        try:
            image = load_image(args.image)
        except (ImageError, OSError) as exc:
            print(f"error: {exc}", file=sys.stderr)
            return 2
    else:
        image = synthetic_frame(256, 256)

    entry = runner.ModelRunner(spec).execute(
        image, force_litert=args.force_litert, verify=not args.no_verify
    )
    _print_entry(entry)

    if args.json:
        with open(args.json, "w", encoding="utf-8") as f:
            json.dump(entry, f, indent=2, sort_keys=True, default=str)
            f.write("\n")

    return 0 if entry.get("status") not in ("error", "fail") else 1


if __name__ == "__main__":
    raise SystemExit(main())
