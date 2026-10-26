#!/usr/bin/env python3
"""Rank candidate classes and decompile the top N with jadx single-class mode.

Usage:
    decompile_classes.py <apk> --regex PAT --out DIR [--max 5]
                         [--packages com.foo,com.bar] [--timeout 180]
                         [--jadx PATH] [--scan-limit 5000]

Never runs jadx in full-project mode.  Each class is one invocation:

    jadx --single-class <name> --single-class-output <DIR/classes/name.java>
         --show-bad-code --no-res <apk>

Writes DIR/classes/*.java, DIR/index.md and DIR/hits.json.  A per-class
timeout kills jadx without touching files outside DIR.
"""

from __future__ import annotations

import argparse
import json
import os
import re
import shutil
import subprocess
import sys
import time

import apk_common as C


def default_jadx() -> str:
    env = os.environ.get("JADX")
    if env:
        return env
    here = os.path.dirname(os.path.abspath(__file__))
    return os.path.join(here, "jadx", "bin", "jadx")


def parse_args(argv: list[str] | None = None) -> argparse.Namespace:
    p = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    p.add_argument("apk", help="APK file")
    p.add_argument("--regex", required=True, help="class-name regex (case-insensitive)")
    p.add_argument("--out", required=True, help="output directory")
    p.add_argument("--max", type=int, default=5, help="number of classes to decompile (default 5)")
    p.add_argument("--packages", default="", help="comma-separated package prefixes to keep")
    p.add_argument("--timeout", type=float, default=180.0, help="seconds per class (default 180)")
    p.add_argument("--scan-limit", type=int, default=5000,
                   help="stop collecting after N matching classes (default 5000)")
    p.add_argument("--jadx", default=default_jadx(), help="jadx executable")
    p.add_argument("--case-sensitive", action="store_true", help="regex is case-sensitive")
    return p.parse_args(argv)


def collect_candidates(apk: str, rx: re.Pattern, packages: list[str], scan_limit: int) -> tuple[list[dict], int]:
    z = C.open_zip(apk)
    candidates: list[dict] = []
    scanned = 0

    def keep(name: str) -> bool:
        if not packages:
            return True
        return any(name == p or name.startswith(p + ".") for p in packages)

    try:
        for dex_name in C.iter_apk_dex_names(z):
            try:
                buf = C.read_dex(z, dex_name)
                strings = C.parse_dex(buf).strings
            except Exception as exc:
                print(f"warning: skipping {dex_name}: {exc}", file=sys.stderr)
                continue
            for cls, _methods in C.iter_dex_classes(buf, dex_name, strings):
                scanned += 1
                if not keep(cls.name):
                    continue
                hits = sorted({m.group(0).lower() for m in rx.finditer(cls.name)})
                if not hits:
                    continue
                candidates.append(
                    {
                        "class": cls.name,
                        "dex": dex_name,
                        "method_count": cls.method_count,
                        "access": cls.access_flags,
                        "matched": hits,
                        "score": len(hits) * 1000 + min(cls.method_count, 999),
                    }
                )
                if len(candidates) >= scan_limit:
                    break
            if len(candidates) >= scan_limit:
                break
    finally:
        z.close()

    candidates.sort(key=lambda c: (-c["score"], -c["method_count"], len(c["class"]), c["class"]))
    for i, c in enumerate(candidates, 1):
        c["rank"] = i
    return candidates, scanned


def run_jadx(jadx: str, apk: str, cls: str, out_file: str, timeout: float, tmpdir: str) -> tuple[str, str, str]:
    """Return (status, error, log).  status: ok|not_found|timeout|failed."""
    os.makedirs(os.path.dirname(out_file), exist_ok=True)
    if os.path.exists(out_file):
        os.remove(out_file)
    env = dict(os.environ)
    env["TMPDIR"] = tmpdir
    cmd = [
        jadx,
        "--single-class",
        cls,
        "--single-class-output",
        out_file,
        "--show-bad-code",
        "--no-res",
        apk,
    ]
    try:
        proc = subprocess.run(
            cmd,
            stdout=subprocess.PIPE,
            stderr=subprocess.STDOUT,
            timeout=timeout,
            env=env,
            cwd=os.path.dirname(out_file),
            text=True,
            errors="replace",
        )
    except subprocess.TimeoutExpired as exc:
        partial = exc.stdout or ""
        if isinstance(partial, bytes):
            partial = partial.decode("utf-8", "replace")
        return "timeout", f"timeout after {timeout:g}s", partial
    except OSError as exc:
        return "failed", f"cannot run jadx: {exc}", ""

    log = proc.stdout or ""
    if "Input class not found" in log or "Class not found" in log:
        return "not_found", "jadx could not find the class", log
    if proc.returncode != 0:
        return "failed", f"jadx exit {proc.returncode}", log
    if not os.path.exists(out_file) or os.path.getsize(out_file) == 0:
        return "failed", "jadx produced no output", log
    try:
        with open(out_file, "r", encoding="utf-8", errors="replace") as f:
            head = f.read(64 * 1024)
    except OSError as exc:
        return "failed", f"cannot read output: {exc}", log
    if not re.search(r"\b(class|interface|enum|record)\b", head):
        return "failed", "output does not look like Java", log
    return "ok", "", log


def main(argv: list[str] | None = None) -> int:
    args = parse_args(argv)
    t0 = time.time()
    apk = os.path.abspath(args.apk)
    out_dir = os.path.abspath(args.out)
    classes_dir = os.path.join(out_dir, "classes")
    logs_dir = os.path.join(out_dir, "logs")
    tmpdir = os.path.join(out_dir, ".tmp")
    for d in (classes_dir, logs_dir, tmpdir):
        os.makedirs(d, exist_ok=True)

    if not (os.path.isfile(args.jadx) or shutil.which(args.jadx)):
        raise SystemExit(f"error: jadx not found: {args.jadx}")

    flags = 0 if args.case_sensitive else re.IGNORECASE
    try:
        rx = re.compile(args.regex, flags)
    except re.error as exc:
        raise SystemExit(f"error: bad --regex: {exc}")

    packages = [p.strip().strip(".") for p in args.packages.split(",") if p.strip()]
    candidates, scanned = collect_candidates(apk, rx, packages, args.scan_limit)
    chosen = candidates[: max(0, args.max)]

    results: list[dict] = []
    for c in chosen:
        cls = c["class"]
        out_file = os.path.join(classes_dir, cls + ".java")
        log_file = os.path.join(logs_dir, cls + ".log")
        t = time.time()
        status, error, log = run_jadx(args.jadx, apk, cls, out_file, args.timeout, tmpdir)
        with open(log_file, "w", encoding="utf-8") as f:
            f.write(log)
        c.update(
            {
                "selected": True,
                "status": status,
                "error": error,
                "file": os.path.relpath(out_file, out_dir) if status == "ok" else None,
                "seconds": round(time.time() - t, 2),
            }
        )
        if status != "ok" and os.path.exists(out_file):
            os.remove(out_file)
        results.append(c)
        print(f"[{c['rank']}/{len(chosen)}] {cls}: {status} ({c['seconds']:.1f}s)", file=sys.stderr)

    for c in candidates[len(chosen):]:
        c["selected"] = False
        c["status"] = "not_selected"
        c["error"] = ""
        c["file"] = None

    hits = {
        "apk": apk,
        "regex": args.regex,
        "packages": packages,
        "max": args.max,
        "timeout": args.timeout,
        "jadx": args.jadx,
        "classes_scanned": scanned,
        "candidates": candidates,
        "decompiled": sum(1 for c in results if c["status"] == "ok"),
        "elapsed_sec": round(time.time() - t0, 2),
    }
    with open(os.path.join(out_dir, "hits.json"), "w", encoding="utf-8") as f:
        json.dump(hits, f, indent=2)

    with open(os.path.join(out_dir, "index.md"), "w", encoding="utf-8") as f:
        f.write(f"# Decompiled classes\n\nAPK: `{apk}`\n\n")
        f.write(f"Regex: `{args.regex}`  \nPackages: `{args.packages or '(all)'}`  \n")
        f.write(
            f"Candidates: {len(candidates)} (scanned {scanned} classes), "
            f"selected {len(chosen)}, decompiled {hits['decompiled']}.\n\n"
        )
        f.write("| Rank | Class | Methods | Score | Status | File |\n|---:|---|---:|---:|---|---|\n")
        for c in chosen:
            link = f"[{c['status']}]({c['file']})" if c["file"] else c["status"]
            f.write(
                f"| {c['rank']} | `{c['class'].replace('|', '\\|')}` | {c['method_count']} | "
                f"{c['score']} | {link} | {c['file'] or ''} |\n"
            )
        f.write("\n## Not selected\n\n")
        for c in candidates[len(chosen):][:200]:
            f.write(f"- `{c['class']}` ({c['method_count']} methods, score {c['score']})\n")
        if len(candidates) - len(chosen) > 200:
            f.write(f"- ... {len(candidates) - len(chosen) - 200} more in hits.json\n")

    print(
        f"decompile: candidates={len(candidates)} chosen={len(chosen)} "
        f"ok={hits['decompiled']} -> {out_dir} ({hits['elapsed_sec']}s)",
        file=sys.stderr,
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
