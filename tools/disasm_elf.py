#!/usr/bin/env python3
"""disasm_elf.py - capstone-based aarch64 disassembly for Android .so files.

Disassemble a named exported function, an address range, or list JNI entry
points.  Direct calls into the PLT are annotated with the imported symbol
name; adrp/ldr pairs into the GOT are annotated too.

Examples:
    python3 disasm_elf.py lib.so --list-jni
    python3 disasm_elf.py lib.so --symbol Java_com_x_Foo_bar --out foo.s
    python3 disasm_elf.py lib.so --symbol st_auth_reset
    python3 disasm_elf.py lib.so --addr 0x3530 --size 0x19c
    python3 disasm_elf.py lib.so --range 0x22f0:0x2310
"""

from __future__ import annotations

import argparse
import bisect
import datetime
import os
import re
import sys
from typing import Dict, List, Optional, Tuple

try:
    import lief
except ImportError as exc:  # pragma: no cover
    sys.stderr.write("disasm_elf.py: LIEF is required: %s\n" % exc)
    raise SystemExit(2)

try:
    from capstone import Cs, CS_ARCH_ARM64, CS_MODE_ARM
    from capstone.arm64 import ARM64_OP_IMM, ARM64_OP_MEM, ARM64_OP_REG
except ImportError as exc:  # pragma: no cover
    sys.stderr.write("disasm_elf.py: capstone is required: %s\n" % exc)
    raise SystemExit(2)

try:
    from elf_info import parse_jni_name
except ImportError:  # running from a different directory
    def parse_jni_name(name):  # type: ignore
        return None

DEFAULT_MAX_BYTES = 64 * 1024
DEFAULT_UNKNOWN_BYTES = 4096


def _enum(value) -> str:
    text = str(value)
    return text.split(".", 1)[1] if "." in text else text


class ElfImage:
    def __init__(self, path: str):
        self.path = path
        self.size = os.path.getsize(path)
        self.binary = lief.parse(path)
        if self.binary is None:
            raise ValueError("LIEF could not parse %s" % path)
        self.md = Cs(CS_ARCH_ARM64, CS_MODE_ARM)
        self.md.detail = True
        self._load_segments = []
        for seg in self.binary.segments:
            if _enum(seg.type) == "LOAD" and seg.physical_size:
                self._load_segments.append((int(seg.virtual_address),
                                            int(seg.virtual_address) +
                                            int(seg.physical_size),
                                            int(seg.file_offset)))
        self._load_segments.sort()
        self._sec_by_name = {s.name: s for s in self.binary.sections}
        self.functions: Dict[str, Tuple[int, int, str]] = {}
        self._addr_names: List[Tuple[int, int, str]] = []
        self._collect_symbols()
        self.plt_map, self.got_map = self._build_plt_map()

    # -- address helpers ---------------------------------------------------
    def vaddr_to_offset(self, addr: int) -> Optional[int]:
        for start, end, foff in self._load_segments:
            if start <= addr < end:
                return foff + (addr - start)
        for sec in self.binary.sections:
            if sec.size and sec.virtual_address <= addr < \
                    sec.virtual_address + sec.size:
                return int(sec.file_offset) + (addr - int(sec.virtual_address))
        return None

    def read(self, addr: int, size: int) -> Optional[bytes]:
        off = self.vaddr_to_offset(addr)
        if off is None or off + size > self.size:
            return None
        with open(self.path, "rb") as fh:
            fh.seek(off)
            return fh.read(size)

    def section(self, name: str):
        return self._sec_by_name.get(name)

    # -- symbols -----------------------------------------------------------
    def _collect_symbols(self) -> None:
        entries: List[Tuple[str, int, int, str]] = []
        seen = set()
        for source in ("exported_symbols", "symtab_symbols"):
            try:
                syms = getattr(self.binary, source)
            except AttributeError:
                continue
            for sym in syms:
                if not sym.name:
                    continue
                key = (sym.name, sym.value, sym.size)
                if key in seen:
                    continue
                seen.add(key)
                entries.append((sym.name, int(sym.value), int(sym.size),
                                _enum(sym.type)))
        for name, addr, size, stype in entries:
            self.functions.setdefault(name, (addr, size, stype))
            if addr:
                self._addr_names.append((addr, size, name))
        self._addr_names.sort()
        self._func_addrs = [a for a, _, _ in self._addr_names]
        self._all_symbols = entries

    def resolve_symbol(self, name: str):
        if name in self.functions:
            return self.functions[name]
        # Allow mangled -> demangled convenience lookup.
        for sym in self.binary.exported_symbols:
            try:
                if sym.demangled_name == name:
                    return int(sym.value), int(sym.size), _enum(sym.type)
            except Exception:
                pass
        return None

    def name_at(self, addr: int) -> Optional[str]:
        """Exact function-start symbol or '<name>+0xN' for interior addresses."""
        idx = bisect.bisect_right(self._func_addrs, addr) - 1
        if idx < 0:
            return None
        start, size, name = self._addr_names[idx]
        if start == addr:
            return name
        if size and start < addr < start + size:
            return "%s+0x%x" % (name, addr - start)
        return None

    def find_next_symbol(self, addr: int) -> Optional[int]:
        idx = bisect.bisect_right(self._func_addrs, addr)
        if idx < len(self._func_addrs):
            nxt = self._addr_names[idx][0]
            return nxt if nxt > addr else None
        return None

    # -- PLT / GOT ---------------------------------------------------------
    def _decode_stub_got(self, stub_addr: int) -> Optional[int]:
        data = self.read(stub_addr, 16)
        if not data:
            return None
        page = None
        page_reg = None
        for ins in self.md.disasm(data, stub_addr):
            if ins.mnemonic == "adrp" and len(ins.operands) == 2:
                page_reg = ins.operands[0].reg
                page = ins.operands[1].imm
            elif ins.mnemonic == "ldr" and page is not None and \
                    len(ins.operands) == 2 and \
                    ins.operands[1].type == ARM64_OP_MEM:
                mem = ins.operands[1].mem
                if mem.base == page_reg:
                    return page + mem.disp
        return None

    def _build_plt_map(self) -> Tuple[Dict[int, str], Dict[int, str]]:
        plt = self.section(".plt")
        if plt is None or plt.size < 32:
            return {}, {}
        plt_addr = int(plt.virtual_address)
        relocs = list(self.binary.pltgot_relocations)
        got_names = {}
        for reloc in relocs:
            try:
                if reloc.has_symbol and reloc.symbol.name:
                    got_names[int(reloc.address)] = reloc.symbol.name
            except Exception:
                continue
        # Also include non-PLT symbol relocations (GLOB_DAT etc.).
        try:
            for reloc in self.binary.relocations:
                if reloc.has_symbol and reloc.symbol.name:
                    got_names.setdefault(int(reloc.address), reloc.symbol.name)
        except Exception:
            pass

        plt_map: Dict[int, str] = {}
        nstubs = max(0, (int(plt.size) - 32) // 16)
        for i in range(nstubs):
            stub = plt_addr + 32 + i * 16
            got = self._decode_stub_got(stub)
            name = got_names.get(got) if got is not None else None
            if name is None and i < len(relocs):
                try:
                    if relocs[i].has_symbol:
                        name = relocs[i].symbol.name
                except Exception:
                    name = None
            plt_map[stub] = name or "plt_%d" % i
        return plt_map, got_names


# --------------------------------------------------------------------------
# Disassembly
# --------------------------------------------------------------------------

def _is_terminal(ins, stop: bool) -> bool:
    if not stop:
        return False
    # A ret or an unconditional b ends an auto-sized region; a b inside a
    # symbol with a known size is just another basic block.
    return ins.mnemonic in ("ret", "b")


def disassemble(img: ElfImage, start: int, size: Optional[int],
                stop_at_ret: bool = True,
                max_bytes: int = DEFAULT_UNKNOWN_BYTES) -> Tuple[List[str], int, int]:
    """Return (lines, bytes_covered, instruction_count)."""
    sec_end = None
    for seg_start, seg_end, _ in img._load_segments:
        if seg_start <= start < seg_end:
            sec_end = seg_end
            break
    if sec_end is None:
        raise ValueError("address 0x%x is not inside a loadable segment" % start)

    auto_size = size is None or size <= 0
    if auto_size:
        next_sym = img.find_next_symbol(start)
        bound = min(sec_end, start + (max_bytes or DEFAULT_UNKNOWN_BYTES),
                    next_sym if next_sym else sec_end)
        if next_sym == start:
            bound = sec_end
        size = max(bound - start, 4)
    else:
        size = min(size, 16 * 1024 * 1024, sec_end - start)
    size -= size % 4
    if size <= 0:
        return [], 0, 0

    data = img.read(start, size)
    if not data:
        raise ValueError("could not read 0x%x bytes at 0x%x" % (size, start))

    lines = []
    adrp_regs: Dict[int, int] = {}
    used = 0
    count = 0
    for ins in img.md.disasm(data, start):
        count += 1
        used = ins.address + ins.size - start
        note = ""

        if ins.mnemonic in ("bl", "b", "blraa", "blr", "br") and ins.operands:
            target = None
            if ins.operands[0].type == ARM64_OP_IMM:
                target = ins.operands[0].imm
            if target is not None:
                if target in img.plt_map:
                    note = " -> %s@plt" % img.plt_map[target]
                else:
                    sym = img.name_at(target)
                    if sym:
                        note = " -> %s" % sym

        if ins.mnemonic == "adrp" and len(ins.operands) == 2:
            adrp_regs[ins.operands[0].reg] = ins.operands[1].imm
        elif ins.mnemonic in ("ldr", "ldrsw") and len(ins.operands) == 2 and \
                ins.operands[1].type == ARM64_OP_MEM:
            mem = ins.operands[1].mem
            page = adrp_regs.get(mem.base)
            if page is not None:
                got_addr = page + mem.disp
                name = img.got_map.get(got_addr)
                if name:
                    note = note or " -> %s@got" % name

        raw = " ".join("%02x" % b for b in ins.bytes)
        line = "0x%08x: %-24s %-8s %s" % (ins.address, raw, ins.mnemonic,
                                          ins.op_str)
        if note:
            line = "%-72s ;%s" % (line, note)
        lines.append(line)
        if _is_terminal(ins, stop_at_ret and auto_size):
            break

    return lines, used, count


# --------------------------------------------------------------------------
# Rendering / CLI
# --------------------------------------------------------------------------

def render_listing(img: ElfImage, title: str, start: int, size: Optional[int],
                   lines: List[str], used: int, count: int) -> str:
    head = [
        "; disasm_elf.py (capstone %s)" % __import__("capstone").__version__,
        "; file    : %s" % os.path.abspath(img.path),
        "; target  : %s" % title,
        "; start   : 0x%x" % start,
        "; size    : %s" % ("0x%x" % size if size else "auto (to next symbol/ret)"),
        "; bytes   : 0x%x   instructions: %d" % (used, count),
        "; generated: %s" % datetime.datetime.now().isoformat(timespec="seconds"),
        "",
    ]
    return "\n".join(head + lines) + "\n"


def list_jni(img: ElfImage) -> str:
    rows = []
    seen = set()
    for name, addr, size, _type in img._all_symbols:
        if not name.startswith("Java_") or name in seen or addr == 0:
            continue
        seen.add(name)
        info = parse_jni_name(name) or {}
        rows.append((addr, name, size, info.get("class", ""),
                     info.get("method", ""), info.get("signature", "")))
    rows.sort()
    out = ["JNI entry points (%d)" % len(rows),
           "  %-12s %-8s %-42s %s" % ("address", "size", "class.method", "symbol")]
    for addr, name, size, cls, method, sig in rows:
        target = "%s.%s" % (cls, method)
        if sig:
            target += "  [%s]" % sig
        out.append("  %-12s %-8s %-42s %s" %
                   ("0x%x" % addr, size, target, name))
    return "\n".join(out) + "\n"


def _parse_int(text: str) -> int:
    return int(text, 0)


def main(argv: Optional[List[str]] = None) -> int:
    ap = argparse.ArgumentParser(
        description="Disassemble aarch64 ELF functions/address ranges "
                    "(capstone), resolving PLT/GOT references.")
    ap.add_argument("elf", help="path to ELF file")
    grp = ap.add_mutually_exclusive_group()
    grp.add_argument("--symbol", "-s", help="function symbol name to disassemble")
    grp.add_argument("--addr", "-a", type=_parse_int,
                     help="start address (e.g. 0x3530)")
    grp.add_argument("--range", help="START:END in hex, e.g. 0x22f0:0x2310")
    grp.add_argument("--list-jni", action="store_true",
                     help="enumerate Java_* entry points and exit")
    ap.add_argument("--size", "-n", type=_parse_int,
                    help="byte size (with --addr); default: next symbol/ret")
    ap.add_argument("--out", "-o",
                    help="write listing here ('-' = stdout); default: "
                         "<elf>.<symbol|addr>.s")
    ap.add_argument("--max-bytes", type=_parse_int, default=DEFAULT_UNKNOWN_BYTES,
                    help="cap for auto-sized (size 0/unknown) functions "
                         "(default 4096; 0 = section/next-symbol bound)")
    ap.add_argument("--no-stop-at-ret", action="store_true",
                    help="do not stop auto-sized disassembly at the first ret")
    ap.add_argument("--quiet", action="store_true",
                    help="do not print a summary line to stderr")
    args = ap.parse_args(argv)

    if not os.path.isfile(args.elf):
        sys.stderr.write("disasm_elf.py: no such file: %s\n" % args.elf)
        return 2
    try:
        img = ElfImage(args.elf)
    except Exception as exc:
        sys.stderr.write("disasm_elf.py: failed to load %s: %s\n" % (args.elf, exc))
        return 2

    if args.list_jni:
        text = list_jni(img)
        if args.out and args.out != "-":
            with open(args.out, "w") as fh:
                fh.write(text)
            if not args.quiet:
                sys.stderr.write("wrote %s\n" % args.out)
        else:
            sys.stdout.write(text)
        return 0

    if not (args.symbol or args.addr is not None or args.range):
        ap.error("one of --symbol, --addr, --range or --list-jni is required")

    if args.range:
        sep = ":" if ":" in args.range else "-"
        parts = args.range.split(sep)
        if len(parts) != 2:
            ap.error("--range must be START:END")
        start, end = _parse_int(parts[0]), _parse_int(parts[1])
        if end <= start:
            ap.error("--range END must be greater than START")
        size = end - start
        title = "range 0x%x:0x%x" % (start, end)
        out_name = "range_%x_%x" % (start, end)
    elif args.symbol:
        resolved = img.resolve_symbol(args.symbol)
        if resolved is None:
            sys.stderr.write("disasm_elf.py: symbol not found: %s\n" % args.symbol)
            near = [n for n, _, _ in img._all_symbols if args.symbol in n][:10]
            if near:
                sys.stderr.write("did you mean: %s\n" % ", ".join(near))
            return 2
        start, sym_size, _type = resolved
        size = args.size if args.size is not None else (sym_size or None)
        title = "%s (0x%x)" % (args.symbol, start)
        out_name = re.sub(r"[^A-Za-z0-9_.+-]", "_", args.symbol)
    else:
        start = args.addr
        size = args.size
        title = "address 0x%x" % start
        out_name = "addr_%x" % start

    try:
        lines, used, count = disassemble(
            img, start, size, stop_at_ret=not args.no_stop_at_ret,
            max_bytes=args.max_bytes)
    except Exception as exc:
        sys.stderr.write("disasm_elf.py: %s\n" % exc)
        return 2

    text = render_listing(img, title, start, size, lines, used, count)
    out = args.out
    if not out:
        base = os.path.basename(args.elf)
        out = "%s.%s.s" % (base, out_name)
    if out == "-":
        sys.stdout.write(text)
    else:
        with open(out, "w") as fh:
            fh.write(text)
        if not args.quiet:
            sys.stderr.write("wrote %s (%d instructions, 0x%x bytes)\n" %
                             (out, count, used))
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except BrokenPipeError:      # e.g. `tool ... | head`
        os._exit(0)
