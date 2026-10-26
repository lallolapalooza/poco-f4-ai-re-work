#!/usr/bin/env python3
"""Find ADRP(+ADD/LDR) code xrefs to a set of virtual addresses in an aarch64 ELF.

Linear sweep over executable sections; decodes ADRP manually, remembers the page
per destination register, then matches a following ADD immediate (64-bit) or LDR
(unsigned offset, 64-bit) that consumes that register. This is the standard
ADRP/ADD and ADRP/LDR (@got) pattern emitted by clang for `&symbol`.

Usage:
  xref_scan.py lib.so 0xd65e80 0xd66161 ...
  xref_scan.py lib.so --strings lib.so:0xd65e80:40 ...
Exit: prints one line per match to stdout.
"""
import struct
import sys

import lief

ADRP_MASK = 0x9F000000
ADRP_OP = 0x90000000
ADD_MASK = 0x7F000000
ADD_OP = 0x11000000
LDR_MASK = 0xFFC00000
LDR_OP = 0xF9400000


def sx(v, bits):
    m = 1 << (bits - 1)
    return (v ^ m) - m


def dec_adrp(w, pc):
    immlo = (w >> 29) & 3
    immhi = (w >> 5) & 0x7FFFF
    imm = sx((immhi << 2) | immlo, 21) << 12
    rd = w & 0x1F
    return rd, ((pc & ~0xFFF) + imm) & 0xFFFFFFFFFFFFFFFF


def main(argv):
    so = argv[1]
    want = {}
    for a in argv[2:]:
        v = int(a, 16) if not a.startswith("0x") else int(a, 16)
        want[v] = want.get(v, [])
    b = lief.parse(so)
    hits = []
    for sec in b.sections:
        if not (sec.flags & 0x4):  # SHF_EXECINSTR
            continue
        data = bytes(sec.content)
        base = sec.virtual_address
        n = len(data) // 4
        words = struct.unpack_from("<%dI" % n, data, 0)
        pages = {}
        for i in range(n):
            w = words[i]
            pc = base + i * 4
            if (w & ADRP_MASK) == ADRP_OP:
                rd, page = dec_adrp(w, pc)
                pages[rd] = page
                continue
            if not pages:
                continue
            if (w & ADD_MASK) == ADD_OP:
                rn = (w >> 5) & 0x1F
                rd = w & 0x1F
                if rn in pages:
                    imm = (w >> 10) & 0xFFF
                    sh = (w >> 22) & 1
                    if sh:
                        imm <<= 12
                    tgt = pages[rn] + imm
                    if tgt in want:
                        hits.append((tgt, pc, "add", w, rd, rn))
                pages.pop(rn, None)
                continue
            if (w & LDR_MASK) == LDR_OP:
                rn = (w >> 5) & 0x1F
                if rn in pages:
                    imm = ((w >> 10) & 0xFFF) * 8
                    tgt = pages[rn] + imm
                    if tgt in want:
                        hits.append((tgt, pc, "ldr", w, w & 0x1F, rn))
                pages.pop(rn, None)
                continue
            # any other instruction clobbers nothing we track except writes
            rd = w & 0x1F
            if rd in pages and (w & 0x1F) != 31:
                pages.pop(rd, None)
    for tgt, pc, kind, w, rd, rn in sorted(hits):
        print("tgt=0x%x xref=0x%x kind=%s rd=x%d rn=x%d word=0x%08x" % (tgt, pc, kind, rd, rn, w))
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
