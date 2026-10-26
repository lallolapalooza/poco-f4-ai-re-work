#!/usr/bin/env python3
"""Extract printable strings from every dex member of an APK and filter them.

Usage:
    strings_dex.py <apk> [--regex PAT] [--min-len 3] [--limit N]
                   [--unique] [--format jsonl|plain] [--out FILE]

Uses the DEX string table (falling back to raw printable runs if a dex cannot
be parsed), so assets/feature names embedded in code are included.  JSONL
records look like {"dex":"classes.dex","s":"..."}.  Default regex is the
parent-analysis string keyword set; pass --regex '' to emit everything.
"""

from __future__ import annotations

import argparse
import json
import os
import re
import sys
import time

import apk_common as C


def parse_args(argv: list[str] | None = None) -> argparse.Namespace:
    p = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    p.add_argument("apk", help="APK file")
    p.add_argument("--regex", default=C.STRING_KEYWORD_RE.pattern,
                   help="filter regex; empty string = no filter")
    p.add_argument("--min-len", type=int, default=3, help="minimum string length (default 3)")
    p.add_argument("--limit", type=int, default=0, help="stop after N strings (0 = unlimited)")
    p.add_argument("--unique", action="store_true", help="deduplicate strings across dex files")
    p.add_argument("--case-sensitive", action="store_true", help="regex is case-sensitive")
    p.add_argument("--format", choices=("jsonl", "plain"), default="jsonl")
    p.add_argument("--out", help="write output here instead of stdout")
    return p.parse_args(argv)


def main(argv: list[str] | None = None) -> int:
    args = parse_args(argv)
    flags = 0 if args.case_sensitive else re.IGNORECASE
    if args.regex:
        try:
            rx = re.compile(args.regex, flags)
        except re.error as exc:
            raise SystemExit(f"error: bad --regex: {exc}")
    else:
        rx = None

    z = C.open_zip(args.apk)
    out = open(args.out, "w", encoding="utf-8") if args.out else sys.stdout
    seen: set[str] = set()
    emitted = 0
    total = 0
    t0 = time.time()

    def emit(s: str, dex_name: str) -> bool:
        nonlocal emitted
        if args.unique:
            if s in seen:
                return True
            seen.add(s)
        if args.format == "jsonl":
            out.write(json.dumps({"dex": dex_name, "s": s}, ensure_ascii=False) + "\n")
        else:
            out.write(s + "\n")
        emitted += 1
        return not (args.limit and emitted >= args.limit)

    stop = False
    dex_names = C.iter_apk_dex_names(z)
    for dex_name in dex_names:
        if stop:
            break
        try:
            buf = C.read_dex(z, dex_name)
            strings = C.parse_dex(buf).strings
        except Exception as exc:
            print(f"warning: {dex_name}: {exc}; falling back to printable runs", file=sys.stderr)
            try:
                buf = C.read_dex(z, dex_name)
                strings = list(C.printable_runs(buf, args.min_len))
            except Exception as exc2:
                print(f"warning: skipping {dex_name}: {exc2}", file=sys.stderr)
                continue
        for s in strings:
            if len(s) < args.min_len:
                continue
            if rx is not None and not rx.search(s):
                continue
            total += 1
            if not emit(s, dex_name):
                stop = True
                break

    if args.out:
        out.close()
    z.close()
    print(
        f"strings_dex: dex={len(dex_names)} matched={total} emitted={emitted} "
        f"({time.time() - t0:.2f}s)",
        file=sys.stderr,
    )
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except BrokenPipeError:
        # Reader went away (head/grep -m1).  Avoid a noisy traceback.
        try:
            sys.stdout.close()
        finally:
            os._exit(0)
