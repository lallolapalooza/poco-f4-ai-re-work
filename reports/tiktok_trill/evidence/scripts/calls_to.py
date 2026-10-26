#!/usr/bin/env python3
"""Find direct BL/B call sites to PLT stubs of given imported symbols.

PLT layout for aarch64 Android (lld): .plt header 32 bytes, then one 16-byte
stub per .rela.plt entry, in relocation order:
    adrp x16, page ; ldr x17, [x16, #got_off] ; add x16, x16, #got_off ; br x17

Usage: calls_to.py lib.so SetInput Inference Extract getOutput
Prints: sym=... plt=0x... call=0x... (kind)
"""
import struct
import sys

import lief


def main(argv):
    so = argv[1]
    pats = argv[2:]
    b = lief.parse(so)
    plt = b.get_section(".plt")
    if plt is None:
        print("no .plt", file=sys.stderr)
        return 1
    rel = {}
    for r in b.pltgot_relocations:
        if r.symbol is not None:
            rel[r.address] = r.symbol.name
    base = plt.virtual_address
    data = bytes(plt.content)
    stubs = {}
    for idx in range((len(data) - 32) // 16):
        off = 32 + idx * 16
        w0, w1 = struct.unpack_from("<II", data, off)
        if (w0 & 0x9F000000) != 0x90000000 or (w1 & 0xFFC00000) != 0xF9400000:
            continue
        immhi = (w0 >> 5) & 0x7FFFF
        immlo = (w0 >> 29) & 3
        imm = immhi << 2 | immlo
        if imm & (1 << 20):
            imm -= 1 << 21
        page = ((base + off) & ~0xFFF) + (imm << 12)
        got = page + (((w1 >> 10) & 0xFFF) * 8)
        name = rel.get(got)
        if name:
            stubs[base + off] = name
    want = {}
    for addr, name in stubs.items():
        for p in pats:
            if p in name:
                want[addr] = name
    tgt = {}
    for sec in b.sections:
        if not (sec.flags & 0x4):
            continue
        d = bytes(sec.content)
        v = sec.virtual_address
        n = len(d) // 4
        for i in range(n):
            w = struct.unpack_from("<I", d, i * 4)[0]
            if (w & 0xFC000000) != 0x94000000 and (w & 0xFC000000) != 0x14000000:
                continue
            imm = w & 0x3FFFFFF
            if imm & (1 << 25):
                imm -= 1 << 26
            dst = (v + i * 4 + imm * 4) & 0xFFFFFFFF
            if dst in want:
                tgt.setdefault(dst, []).append((v + i * 4, w))
    for dst, sites in sorted(tgt.items()):
        for pc, w in sites:
            print("sym=%s plt=0x%x call=0x%x %s" % (want[dst], dst, pc,
                  "bl" if (w >> 26) == 0x25 else "b"))
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
