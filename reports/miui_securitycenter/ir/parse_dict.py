#!/usr/bin/env python3
"""Parser for MIUISecurityCenter `assets/dict` (word-segmentation dictionary).

Reverse-engineered from bh/a.java `h(InputStream)` + the shipped asset; the walk
below consumes the file exactly (348,982 words, 16 length groups).

Layout (all big-endian):
    i32 version            == 1
    i32 reserved           == 0
    f64[4]                 char-class weights
    f64[4][4]              char-class transition weights
    i32[4]                 record counts of the 4 prefix-node groups
    i32 n_lengths
    i32[n_lengths]         word count per length (1..n_lengths chars)
    i32 total_freq
    i32 min_freq
    prefix section: 6-byte records (u16 char, u16 node, u16 freq) * sum(counts)
    word section:   per length L: count[L] * { L * u16 char, i32 freq }

Usage: parse_dict.py <dict> [--json out.json] [--limit N]
"""
from __future__ import annotations

import argparse
import hashlib
import json
import struct
import sys


def parse(path: str) -> dict:
    d = open(path, "rb").read()
    o = 0
    ver, res = struct.unpack_from(">ii", d, o); o += 8
    char_w = list(struct.unpack_from(">4d", d, o)); o += 32
    trans = [list(struct.unpack_from(">4d", d, o + 32 * i)) for i in range(4)]; o += 128
    groups = list(struct.unpack_from(">4i", d, o)); o += 16
    n_len = struct.unpack_from(">i", d, o)[0]; o += 4
    counts = list(struct.unpack_from(">%di" % n_len, d, o)); o += 4 * n_len
    total, minf = struct.unpack_from(">ii", d, o); o += 8
    hdr_end = o
    prefix_bytes = sum(groups) * 6
    words = []
    p = hdr_end + prefix_bytes
    for j, cnt in enumerate(counts):
        L = j + 1
        for _ in range(cnt):
            w = d[p:p + 2 * L].decode("utf-16-be", "replace")
            f = struct.unpack_from(">i", d, p + 2 * L)[0]
            words.append((w, f))
            p += 2 * L + 4
    return {
        "version": ver, "reserved": res, "char_weights": char_w,
        "transition": trans, "prefix_groups": groups, "n_lengths": n_len,
        "counts": counts, "total_freq": total, "min_freq": minf,
        "header_end": hdr_end, "prefix_bytes": prefix_bytes,
        "words": len(words), "consumed": p, "size": len(d),
        "exact": p == len(d), "sha256": hashlib.sha256(d).hexdigest(),
        "_words": words,
    }


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("dict")
    ap.add_argument("--json")
    ap.add_argument("--limit", type=int, default=12)
    a = ap.parse_args()
    r = parse(a.dict)
    print(f"file {r['size']} bytes sha256 {r['sha256']}")
    print(f"version={r['version']} n_lengths={r['n_lengths']} counts={r['counts']}")
    print(f"prefix_groups={r['prefix_groups']} prefix_bytes={r['prefix_bytes']} header_end=0x{r['header_end']:x}")
    print(f"words={r['words']} total_freq={r['total_freq']} min_freq={r['min_freq']} "
          f"consumed={r['consumed']} exact={r['exact']}")
    print("first:", r["_words"][:a.limit])
    print("longest:", sorted(r["_words"], key=lambda w: -len(w[0]))[:5])
    print("top freq:", sorted(r["_words"], key=lambda w: -w[1])[:5])
    if a.json:
        out = {k: v for k, v in r.items() if not k.startswith("_")}
        out["sample_words"] = r["_words"][:200]
        open(a.json, "w").write(json.dumps(out, ensure_ascii=True, indent=1))
    return 0 if r["exact"] else 1


if __name__ == "__main__":
    sys.exit(main())
