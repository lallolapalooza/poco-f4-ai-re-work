#!/usr/bin/env python3
"""Run all three ExtraPhoto JAX re-implementations and write verify.txt."""
import os
import subprocess
import sys
import time

HERE = os.path.dirname(os.path.abspath(__file__))
PY = sys.executable
STEPS = [
    ("document_hed", "document_hed.py"),
    ("relighting", "relighting.py"),
    ("bokeh", "bokeh.py"),
]


def main():
    lines = []
    lines.append("ExtraPhotoGlobal (com.miui.extraphoto) JAX re-implementation")
    lines.append(f"python: {PY}")
    try:
        import jax
        lines.append(f"jax {jax.__version__} backend {jax.default_backend()}")
    except Exception as e:  # pragma: no cover
        lines.append(f"jax import failed: {e}")
    lines.append(f"cwd: {HERE}")
    lines.append("")
    ok = True
    for name, script in STEPS:
        cmd = [PY, script]
        t0 = time.time()
        p = subprocess.run(cmd, cwd=HERE, capture_output=True, text=True)
        dt = time.time() - t0
        lines.append(f"===== {name}: {' '.join(cmd)}")
        lines.append(f"exit={p.returncode} elapsed={dt:.1f}s")
        if p.stdout:
            lines.append(p.stdout.rstrip())
        if p.stderr:
            lines.append("--- stderr")
            lines.append(p.stderr.rstrip())
        lines.append("")
        ok = ok and p.returncode == 0
    lines.append(f"OVERALL: {'PASS' if ok else 'FAIL'}")
    out = os.path.join(HERE, "verify.txt")
    with open(out, "w") as f:
        f.write("\n".join(lines) + "\n")
    print("\n".join(lines[-8:]))
    print(f"wrote {out}")
    return 0 if ok else 1


if __name__ == "__main__":
    sys.exit(main())
