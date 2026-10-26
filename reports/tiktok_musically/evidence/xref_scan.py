#!/usr/bin/env python3
"""Find code references to a symbol / string in an aarch64 ELF.

Usage:
  xref_scan.py <elf> --sym bef_effect_process_texture     # bl/b to symbol's PLT stub
  xref_scan.py <elf> --str 'renderEffect bef_effect_set'  # adrp+add xrefs to string
  xref_scan.py <elf> --addr 0x... --context 12
"""
import argparse
import sys

import lief
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM

md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
md.detail = True


def load(path):
    b = lief.parse(path)
    return b


def section_for(b, va):
    for s in b.sections:
        if s.virtual_address <= va < s.virtual_address + s.size:
            return s
    return None


def read_at(b, va, n):
    s = section_for(b, va)
    if s is None:
        return None
    off = va - s.virtual_address
    data = bytes(s.content)
    return data[off:off + n]


def code_iter(b):
    for s in b.sections:
        if not (s.flags & 4) or s.size == 0:  # SHF_EXECINSTR
            continue
        yield s, bytes(s.content)


def plt_map(b):
    """name -> plt stub address (via .rela.plt GOT slot)."""
    out = {}
    plt = None
    for s in b.sections:
        if s.name == ".plt":
            plt = s
    if plt is None:
        return out
    got = {}
    rela = None
    for s in b.sections:
        if s.name == ".rela.plt":
            rela = s
    if rela is None:
        return out
    entsize = 24
    raw = bytes(rela.content)
    for i in range(0, len(raw) - entsize + 1, entsize):
        off = int.from_bytes(raw[i:i + 8], "little")
        info = int.from_bytes(raw[i + 8:i + 16], "little")
        addend = int.from_bytes(raw[i + 16:i + 24], "little")
        got[off + addend] = i // entsize
    slot = plt.virtual_address + 0x20
    data = bytes(plt.content)
    for idx in range(len(got)):
        stub = plt.virtual_address + idx * 16
        if stub + 16 > plt.virtual_address + plt.size:
            break
        # adrp x16, page; ldr x17,[x16,#off]; add x16,...; br x17
        ins = list(md.disasm(data[stub - plt.virtual_address: stub - plt.virtual_address + 16], stub))
        target_slot = None
        page = None
        for j in ins:
            if j.mnemonic == "adrp" and j.operands and j.operands[0].reg == 16:
                page = j.operands[1].imm
            if j.mnemonic == "ldr" and page is not None and len(j.operands) >= 3:
                target_slot = page + j.operands[2].imm
        if target_slot in got:
            sym_idx = got[target_slot]
            # dynsym order == rela.plt order for .rela.plt entries
            names = [s for s in b.dynamic_symbols]
            if sym_idx < len(names):
                out.setdefault(names[sym_idx].name, []).append(stub)
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("elf")
    ap.add_argument("--sym")
    ap.add_argument("--str")
    ap.add_argument("--addr", type=lambda x: int(x, 0))
    ap.add_argument("--context", type=int, default=8)
    a = ap.parse_args()
    b = load(a.elf)

    if a.sym:
        p = plt_map(b)
        stubs = p.get(a.sym, [])
        print(f"# {a.sym}: plt stubs {[hex(x) for x in stubs]}")
        for s, data in code_iter(b):
            for ins in md.disasm(data, s.virtual_address):
                if ins.mnemonic in ("bl", "b") and ins.operands and ins.operands[0].type == 2:
                    if ins.operands[0].imm in stubs:
                        print(f"{ins.address:#x}: {ins.mnemonic} {ins.op_str}  [{s.name}]")
        return

    if a.str:
        needle = a.str.encode()
        offs = []
        start = 0
        for s in b.sections:
            if s.name in (".rodata", ".data.rel.ro", ".data", ".dynstr"):
                d = bytes(s.content)
                i = d.find(needle)
                while i >= 0:
                    offs.append(s.virtual_address + i)
                    i = d.find(needle, i + 1)
        print("# string vaddrs:", [hex(x) for x in offs])
        targets = set()
        for va in offs:
            targets.add(va)
            targets.add(va) # exact
        hits = []
        for s, data in code_iter(b):
            ins = list(md.disasm(data, s.virtual_address))
            for i, x in enumerate(ins):
                if x.mnemonic == "adrp" and x.operands and x.operands[0].reg in (0, 1, 2, 3):
                    page = x.operands[1].imm
                    for y in ins[i + 1:i + 4]:
                        if y.mnemonic == "add" and y.operands[0].reg == x.operands[0].reg:
                            val = page + y.operands[2].imm
                            if val in targets:
                                hits.append((x.address, s.name))
        print("# adrp/add hits:", [f"{h[0]:#x}({h[1]})" for h in hits])
        for addr, _ in hits:
            _dump(b, addr - 0x20, a.context * 4 + 0x40)
        return

    if a.addr is not None:
        _dump(b, a.addr, a.context * 12)


def _dump(b, va, n):
    for s, data in code_iter(b):
        if s.virtual_address <= va < s.virtual_address + s.size:
            start = va - s.virtual_address
            for ins in md.disasm(data[start:start + n], va):
                print(f"{ins.address:#x}: {ins.mnemonic:8s} {ins.op_str}")
            return


if __name__ == "__main__":
    sys.exit(main())
