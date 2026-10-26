#!/usr/bin/env python3
"""Find callers of methods whose defining class matches a regex.

Usage: dex_invokexref.py <apk> --class-regex 'org/tensorflow/lite' [--out FILE]
JSONL: {"dex","callee_class","callee_method","caller_class","caller_method"}
"""
from __future__ import annotations
import argparse, json, re, struct, sys, zipfile
from dex_strxref import parse_strings, uleb
from dex_insn import walk

INVOKE = {0x6E, 0x6F, 0x70, 0x71, 0x72, 0x74, 0x75, 0x76, 0x77, 0x78}


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("apk"); ap.add_argument("--class-regex", required=True)
    ap.add_argument("--out")
    a = ap.parse_args()
    rx = re.compile(a.class_regex)
    z = zipfile.ZipFile(a.apk)
    out = open(a.out, "w") if a.out else sys.stdout
    seen = set()
    for name in sorted(n for n in z.namelist() if re.fullmatch(r"classes\d*\.dex", n)):
        b = z.read(name)
        strings = parse_strings(b)
        n_type, type_off = struct.unpack_from("<II", b, 0x40)
        n_meth, meth_off = struct.unpack_from("<II", b, 0x58)
        n_cls, cls_off = struct.unpack_from("<II", b, 0x60)
        types = [strings[struct.unpack_from("<I", b, type_off + 4 * i)[0]] for i in range(n_type)]
        meths = []
        targets = set()
        for i in range(n_meth):
            ci = struct.unpack_from("<H", b, meth_off + 8 * i)[0]
            ni = struct.unpack_from("<I", b, meth_off + 8 * i + 4)[0]
            meths.append((ci, ni))
            cn = types[ci] if ci < len(types) else ""
            if rx.search(cn):
                targets.add(i)
        if not targets:
            continue
        for i in range(n_cls):
            cl = struct.unpack_from("<8I", b, cls_off + 32 * i)
            cd = cl[6]
            if not cd:
                continue
            o = cd
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
                    sz = struct.unpack_from("<I", b, code + 12)[0]
                    ins = b[code + 16:code + 16 + sz * 2]
                    for k, op in walk(ins):
                        if op in INVOKE and k + 4 <= len(ins):
                            mi = ins[k + 2] | (ins[k + 3] << 8)
                            if mi in targets:
                                cci, cni = meths[mi]
                                ccls = types[cci] if cci < len(types) else "?"
                                cmeth = strings[cni]
                                oci, oni = meths[midx] if midx < len(meths) else (0, 0)
                                ocls = types[oci] if oci < len(types) else "?"
                                key = (name, ccls, cmeth, ocls, strings[oni])
                                if key in seen:
                                    continue
                                seen.add(key)
                                out.write(json.dumps({"dex": name, "callee_class": ccls, "callee_method": cmeth,
                                                      "caller_class": ocls, "caller_method": strings[oni]}) + "\n")
    return 0


if __name__ == "__main__":
    sys.exit(main())
