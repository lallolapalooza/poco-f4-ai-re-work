#!/usr/bin/env python3
"""Record the model-runner verification transcript (``verify.txt``).

Runs the public entrypoints with the workspace interpreter and captures their
output plus a per-model table derived from ``demo_report.json``:

    $PY make_verify.py

Overwrites ``verify.txt`` next to this file.  Exit 0 when every runnable model
is accounted for (pass / pass_semantic / litert_fallback) and the deterministic
spot checks pass; the one hard skip is reported, not counted as a failure.
"""

from __future__ import annotations

import json
import os
import subprocess
import sys

HERE = os.path.dirname(os.path.abspath(__file__))
PY = sys.executable
REPORT_PATH = os.path.join(HERE, "demo_report.json")
OUT = os.path.join(HERE, "verify.txt")


def _run(args, cwd=HERE) -> tuple[int, str]:
    p = subprocess.run(args, cwd=cwd, capture_output=True, text=True)
    out = (p.stdout or "") + (p.stderr or "")
    return p.returncode, out.rstrip()


def _write_test_image() -> str:
    """Deterministic PNM frame so --image is exercised without a binary asset."""
    import numpy as np

    sys.path.insert(0, HERE)
    from preprocess import synthetic_frame

    img = synthetic_frame(180, 240, seed=5)
    path = os.path.join(HERE, "test_frame.ppm")
    with open(path, "wb") as f:
        f.write(b"P6\n%d %d\n255\n" % (img.shape[1], img.shape[0]))
        f.write(np.ascontiguousarray(img, dtype=np.uint8).tobytes())
    return path


def main() -> int:
    lines: list[str] = []
    failures: list[str] = []
    lines.append("model_runners verification transcript")
    lines.append("=" * 70)
    lines.append(f"python: {PY}")
    lines.append(f"cwd   : {HERE}")
    lines.append("")

    rc, out = _run([PY, "run_model.py", "--list"])
    lines += ["$ python run_model.py --list", out, f"exit={rc}", ""]
    if rc != 0:
        failures.append("registry check failed")

    image = _write_test_image()
    spots = [
        ["run_model.py", "--model", "head"],
        ["run_model.py", "--model", "mouth_checker_14", "--image", image],
        ["run_model.py", "--model", "face_landmark_mb360_64"],
        ["run_model.py", "--model", "model_hair"],       # LiteRT fallback (PRELU)
        ["run_model.py", "--model", "human_detector"],   # hard skip
    ]
    for args in spots:
        rc, out = _run([PY] + args)
        lines += ["$ python " + " ".join(os.path.relpath(a) if os.path.exists(a) else a for a in args),
                  out, f"exit={rc}", ""]
        if rc != 0:
            failures.append(" ".join(args))

    rc, out = _run([PY, "demo_all.py"])
    lines += ["$ python demo_all.py", out, f"exit={rc}", ""]
    if rc != 0:
        failures.append("demo_all.py")

    if not os.path.exists(REPORT_PATH):
        lines.append("ERROR: demo_report.json missing")
        failures.append("demo_report.json")
        report = None
    else:
        with open(REPORT_PATH, encoding="utf-8") as f:
            report = json.load(f)

    if report:
        lines.append("per-model status (from demo_report.json)")
        lines.append("-" * 70)
        header = f"{'model':68s} {'path':7s} {'status':15s} {'verify'}"
        lines.append(header)
        for name, e in report["by_name"].items():
            ver = e.get("verification") or {}
            v = ""
            if ver:
                v = f"max_abs={ver['max_abs']:.3g}"
                if ver.get("semantic") and e["status"] == "pass_semantic":
                    v += f" {ver['semantic']['metric']}={ver['semantic']['agreement']:.4f}"
            inputs = ";".join(
                "%s%s" % (i["name"], i["shape"]) for i in e["meta"]["inputs"]
            )
            outputs = ";".join(
                "%s%s" % (o["name"], o["shape"]) for o in e["outputs"]
            ) if e.get("outputs") else "-"
            lines.append(f"{name:68s} {e.get('path','-'):7s} {e['status']:15s} {v}")
            lines.append(f"    in : {inputs}")
            lines.append(f"    out: {outputs}")
        lines.append("")
        lines.append("counts: " + json.dumps(report["counts"], sort_keys=True))
        lines.append(f"converted to JAX: {report['converted']}; "
                     f"conversion skipped: {len(report['conversion_skipped'])}")
        lines.append("conversion skipped (LiteRT fallback): "
                     + ", ".join(report["conversion_skipped"]))
        expected = {"pass", "pass_semantic", "litert_fallback", "skip"}
        bad = {k: v for k, v in report["counts"].items() if k not in expected}
        if bad:
            failures.append(f"unexpected statuses: {bad}")
        errors = [n for n, e in report["by_name"].items() if e["status"] == "error"]
        if errors:
            failures.append("model errors: " + ", ".join(errors))

    lines.append("")
    lines.append("RESULT: " + ("PASS" if not failures else "FAIL: " + "; ".join(failures)))

    with open(OUT, "w", encoding="utf-8") as f:
        f.write("\n".join(lines) + "\n")
    print("\n".join(lines[-6:]))
    print(f"\nwrote {OUT}")
    return 0 if not failures else 1


if __name__ == "__main__":
    raise SystemExit(main())
