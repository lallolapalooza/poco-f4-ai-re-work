#!/usr/bin/env python3
"""Find classes and methods in all classes*.dex of an APK, without decompiling.

Usage:
    dex_find.py <apk> [--regex PAT] [--limit N] [--classes-only]
                [--max-methods-per-class 50] [--out FILE]

Output is JSON Lines on stdout (or --out).  One dex is parsed at a time, so
memory stays bounded (~one dex + its string table) for 250 MB multi-dex APKs.

Default PAT is the parent-analysis AI keyword union.  Matching is
case-insensitive unless --case-sensitive is given.
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
    p.add_argument("--regex", default=C.DEFAULT_CLASS_REGEX,
                   help="Python regex applied to dotted class names and method names")
    p.add_argument("--limit", type=int, default=0,
                   help="stop after N output records (0 = unlimited, default)")
    p.add_argument("--classes-only", action="store_true", help="do not emit method records")
    p.add_argument("--no-refs", action="store_true",
                   help="only report classes defined in this APK (default also reports referenced classes)")
    p.add_argument("--max-methods-per-class", type=int, default=50,
                   help="method records emitted for a class whose name matched (default 50)")
    p.add_argument("--case-sensitive", action="store_true", help="regex is case-sensitive")
    p.add_argument("--out", help="write JSONL here instead of stdout")
    return p.parse_args(argv)


def main(argv: list[str] | None = None) -> int:
    args = parse_args(argv)
    flags = 0 if args.case_sensitive else re.IGNORECASE
    try:
        rx = re.compile(args.regex, flags)
    except re.error as exc:
        raise SystemExit(f"error: bad --regex: {exc}")

    z = C.open_zip(args.apk)
    out = open(args.out, "w", encoding="utf-8") if args.out else sys.stdout
    t0 = time.time()
    emitted = 0
    class_hits = 0
    class_ref_hits = 0
    method_hits = 0
    classes_scanned = 0
    dex_names = C.iter_apk_dex_names(z)
    stop = False

    def emit(rec: dict) -> None:
        nonlocal emitted
        out.write(json.dumps(rec, separators=(",", ":"), ensure_ascii=False) + "\n")
        emitted += 1

    for dex_name in dex_names:
        if stop:
            break
        try:
            buf = C.read_dex(z, dex_name)
            strings = C.parse_dex(buf).strings
        except Exception as exc:
            print(f"warning: skipping {dex_name}: {exc}", file=sys.stderr)
            continue

        defined: set[str] = set()
        for cls, methods in C.iter_dex_classes(buf, dex_name, strings):
            classes_scanned += 1
            defined.add(cls.name)
            hits = sorted({m.group(0) for m in rx.finditer(cls.name)})
            class_matched = bool(hits)
            if class_matched:
                class_hits += 1
                emit(
                    {
                        "kind": "class",
                        "class": cls.name,
                        "dex": dex_name,
                        "defined": True,
                        "methods": cls.method_count,
                        "access": cls.access_flags,
                        "matched": hits[:10],
                    }
                )
                if emitted and args.limit and emitted >= args.limit:
                    stop = True
                    break

            if args.classes_only:
                continue
            sent = 0
            for mname, desc, acc in methods:
                if class_matched:
                    via = "class"
                elif rx.search(mname):
                    via = "method"
                else:
                    continue
                emit(
                    {
                        "kind": "method",
                        "class": cls.name,
                        "method": mname,
                        "descriptor": desc,
                        "access": acc,
                        "dex": dex_name,
                        "defined": True,
                        "via": via,
                    }
                )
                method_hits += 1
                sent += 1
                if args.limit and emitted >= args.limit:
                    stop = True
                    break
                if class_matched and sent >= args.max_methods_per_class:
                    break
            if stop:
                break

        if stop:
            break
        if args.no_refs:
            continue

        # Classes/types referenced by this dex but defined elsewhere (e.g.
        # com.xiaomi.aiautobrt.* AIDL interfaces in SecurityCoreAdd).
        matched_ref: set[str] = set()
        for _idx, tname in C.iter_dex_type_names(buf, strings):
            if tname in defined:
                continue
            hits = sorted({m.group(0) for m in rx.finditer(tname)})
            if not hits:
                continue
            class_ref_hits += 1
            matched_ref.add(tname)
            emit(
                {
                    "kind": "class",
                    "class": tname,
                    "dex": dex_name,
                    "defined": False,
                    "methods": None,
                    "access": 0,
                    "matched": hits[:10],
                }
            )
            if args.limit and emitted >= args.limit:
                stop = True
                break
        if stop:
            break
        if args.classes_only or not matched_ref:
            continue

        sent_per_class: dict[str, int] = {}
        for cname, mname, proto_idx in C.iter_dex_method_refs(buf, strings):
            if cname in defined:
                continue
            if cname in matched_ref:
                via = "class"
            elif rx.search(mname):
                via = "method"
            else:
                continue
            sent = sent_per_class.get(cname, 0)
            if sent >= args.max_methods_per_class:
                continue
            sent_per_class[cname] = sent + 1
            emit(
                {
                    "kind": "method",
                    "class": cname,
                    "method": mname,
                    "descriptor": C.dex_proto_descriptor(buf, strings, proto_idx),
                    "access": 0,
                    "dex": dex_name,
                    "defined": False,
                    "via": via,
                }
            )
            method_hits += 1
            if args.limit and emitted >= args.limit:
                stop = True
                break
        if stop:
            break

    if args.out:
        out.close()
    z.close()
    print(
        f"dex_find: dex={len(dex_names)} classes_scanned={classes_scanned} "
        f"class_hits={class_hits} class_refs={class_ref_hits} "
        f"method_records={method_hits} emitted={emitted} "
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
