#!/usr/bin/env python3
"""Find class/method owners of const-string references in an APK's dex files.

Usage: dex_strxref.py <apk> --regex 'model|Ai-Model' [--out FILE]
JSONL: {"dex","string","class","method","descriptor","off"}.
Over-approximates: scans every u16 unit whose low byte is 0x1a/0x1b.
"""
from __future__ import annotations
import argparse, json, re, struct, sys, zipfile
from dex_insn import walk


def uleb(b, o):
    r = 0; s = 0
    while True:
        x = b[o]; o += 1
        r |= (x & 0x7f) << s
        if not x & 0x80:
            return r, o
        s += 7


def parse_strings(b):
    n, off = struct.unpack_from("<II", b, 0x38)
    out = []
    for i in range(n):
        so = struct.unpack_from("<I", b, off + 4 * i)[0]
        ln, p = uleb(b, so)
        e = b.index(b"\x00", p)
        out.append(b[p:e].decode("utf-8", "replace"))
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("apk")
    ap.add_argument("--regex", required=True)
    ap.add_argument("--out")
    a = ap.parse_args()
    rx = re.compile(a.regex, re.I)
    z = zipfile.ZipFile(a.apk)
    out = open(a.out, "w") if a.out else sys.stdout
    seen = set()
    for name in sorted([n for n in z.namelist() if re.fullmatch(r"classes\d*\.dex", n)]):
        b = z.read(name)
        strings = parse_strings(b)
        targets = {i: s for i, s in enumerate(strings) if rx.search(s)}
        if not targets:
            continue
        n_type, type_off = struct.unpack_from("<II", b, 0x40)
        n_proto, proto_off = struct.unpack_from("<II", b, 0x48)
        n_field, field_off = struct.unpack_from("<II", b, 0x50)
        n_meth, meth_off = struct.unpack_from("<II", b, 0x58)
        n_cls, cls_off = struct.unpack_from("<II", b, 0x60)
        types = [strings[struct.unpack_from("<I", b, type_off + 4 * i)[0]] for i in range(n_type)]
        meths = []
        for i in range(n_meth):
            ci = struct.unpack_from("<H", b, meth_off + 8 * i)[0]
            ni = struct.unpack_from("<I", b, meth_off + 8 * i + 4)[0]
            meths.append((ci, ni))
        for i in range(n_cls):
            cl = struct.unpack_from("<8I", b, cls_off + 32 * i)
            class_idx, cd_off = cl[0], cl[6]
            if not cd_off:
                continue
            o = cd_off
            sf, o = uleb(b, o); inf, o = uleb(b, o); dm, o = uleb(b, o); vm, o = uleb(b, o)
            for _ in range(sf + inf):
                _, o = uleb(b, o); _, o = uleb(b, o)
            for cnt in (dm, vm):
                midx = 0
                for _ in range(cnt):
                    d, o = uleb(b, o); midx += d
                    acc, o = uleb(b, o); code, o = uleb(b, o)
                    if not code:
                        continue
                    insns_size = struct.unpack_from("<I", b, code + 12)[0]
                    start = code + 16
                    ins = b[start:start + insns_size * 2]
                    for k, op in walk(ins):
                        if op == 0x1A:
                            if k + 4 > len(ins):
                                continue
                            idx = ins[k + 2] | (ins[k + 3] << 8)
                            if idx in targets:
                                _emit(out, seen, name, targets[idx], types, strings, meths, midx, k)
                        elif op == 0x1B:
                            if k + 6 > len(ins):
                                continue
                            idx = int.from_bytes(ins[k + 2:k + 6], "little")
                            if idx in targets:
                                _emit(out, seen, name, targets[idx], types, strings, meths, midx, k)
    return 0


def _emit(out, seen, dex, s, types, strings, meths, midx, k):
    if midx >= len(meths):
        return
    ci, ni = meths[midx]
    cls = types[ci] if ci < len(types) else "?"
    mn = strings[ni] if ni < len(strings) else "?"
    key = (dex, s, cls, mn, k)
    if key in seen:
        return
    seen.add(key)
    out.write(json.dumps({"dex": dex, "string": s, "class": cls, "method": mn, "unit_off": k}, ensure_ascii=False) + "\n")


if __name__ == "__main__":
    sys.exit(main())
