#!/usr/bin/env python3
"""elf_info.py - static summary of an (Android/aarch64) shared object.

Uses LIEF for ELF parsing, with a pyelftools fallback for section/notes if
LIEF is unavailable.  Emits a human summary and, optionally, JSON.

Examples:
    python3 elf_info.py libfoo.so
    python3 elf_info.py libfoo.so --json libfoo.json
    python3 elf_info.py libfoo.so --json -            # JSON only
    python3 elf_info.py libfoo.so --strings-limit 50
"""

from __future__ import annotations

import argparse
import hashlib
import json
import os
import re
import struct
import sys
from typing import Dict, Iterable, List, Optional, Tuple

try:
    import lief
except ImportError as exc:  # pragma: no cover - environment problem
    sys.stderr.write("elf_info.py: LIEF is required (pip install lief): %s\n" % exc)
    raise SystemExit(2)


# --------------------------------------------------------------------------
# Constants / helpers
# --------------------------------------------------------------------------

SHF_WRITE = 0x1
SHF_ALLOC = 0x2
SHF_EXECINSTR = 0x4
SHF_MERGE = 0x10
SHF_STRINGS = 0x20
SHF_INFO_LINK = 0x40
SHF_LINK_ORDER = 0x80
SHF_OS_NONCONFORMING = 0x100
SHF_GROUP = 0x200
SHF_TLS = 0x400

SECTION_FLAG_NAMES = [
    (SHF_WRITE, "W"),
    (SHF_ALLOC, "A"),
    (SHF_EXECINSTR, "X"),
    (SHF_MERGE, "M"),
    (SHF_STRINGS, "S"),
    (SHF_INFO_LINK, "I"),
    (SHF_LINK_ORDER, "L"),
    (SHF_OS_NONCONFORMING, "O"),
    (SHF_GROUP, "G"),
    (SHF_TLS, "T"),
]

SEGMENT_FLAG_NAMES = [(0x1, "X"), (0x2, "W"), (0x4, "R")]

# Categories of strings that matter when triaging an on-device AI library.
# Patterns are matched case-insensitively against printable ASCII runs.
AI_STRING_PATTERNS: List[Tuple[str, str]] = [
    ("tflite",
     r"tflite|tensorflow|tfl3|flatbuffer"),
    ("model",
     r"\.tflite|\.dlc\b|\.caffemodel|\.prototxt|\.onnx\b|\.mnn\b|\.param\b|"
     r"\.model\b|caffe_model|\bcaffe\b|\bweights?\b"),
    ("nn_runtime",
     r"\bsnpe\b|\bqnn\b|\bnnapi\b|neuralnetworks|\bhexagon\b|\bhtp\b|"
     r"\bopencl\b|adreno|\bdsp\b|\bgpu\b|\bnpu\b"),
    ("face",
     r"(?<![a-z0-9])face|faceid|face_?auth|liveness|anti_?spoof|landmark|"
     r"\biris\b|(?<![a-z0-9])eye(?![a-z0-9])|align"),
    ("ocr",
     r"\bocr\b|text_?detect|text_?recogn|charset|ppocr|\bcrnn\b"),
    ("asr",
     r"\basr\b|\bspeech\b|\bvad\b|wakeup|\baudio\b|\bcodec\b|\bflac\b|"
     r"\bopus\b|\bwhisper\b"),
    ("segment",
     r"segment|matting|portrait|\bsaliency\b"),
    ("model_name",
     r"^[A-Za-z0-9][A-Za-z0-9_.\-]{3,}(?:_model|\.model|\.tflite|\.dlc|"
     r"\.caffemodel|\.prototxt)$"),
]

AI_STRING_RES = [(name, re.compile(pattern, re.IGNORECASE))
                 for name, pattern in AI_STRING_PATTERNS]
ANY_AI_RE = re.compile("|".join("(?:%s)" % p for _, p in AI_STRING_PATTERNS),
                       re.IGNORECASE)
MODEL_NAME_RE = re.compile(AI_STRING_PATTERNS[-1][1])

PRINTABLE_RE = re.compile(rb"[\x20-\x7e]{4,}")
READ_CHUNK = 8 * 1024 * 1024
CHUNK_OVERLAP = 1024


def _enum_name(value) -> str:
    """Render a LIEF enum as a short string, e.g. FILE_TYPE.DYN -> DYN."""
    text = str(value)
    for prefix in ("FILE_TYPE.", "ARCH.", "OS_ABI.", "ELF_DATA.", "ELF_CLASS.",
                   "CLASS.", "TYPE.", "BINDING.", "VISIBILITY.", "TAG."):
        if text.startswith(prefix):
            return text[len(prefix):]
    return text


def _int_flags(value) -> int:
    try:
        return int(value)
    except (TypeError, ValueError):
        return 0


def _flags_str(flags: int, table) -> str:
    out = "".join(name for bit, name in table if flags & bit)
    return out or "-"


def sha256_file(path: str, chunk: int = 4 * 1024 * 1024) -> str:
    h = hashlib.sha256()
    with open(path, "rb") as fh:
        while True:
            data = fh.read(chunk)
            if not data:
                break
            h.update(data)
    return h.hexdigest()


# --------------------------------------------------------------------------
# JNI name handling (shared with disasm_elf.py)
# --------------------------------------------------------------------------

_JNI_ESCAPES = {"_0": "\x00", "_1": "_", "_2": ";", "_3": "["}


def _decode_jni_component(text: str) -> str:
    out = []
    i = 0
    while i < len(text):
        two = text[i:i + 2]
        if two in _JNI_ESCAPES:
            out.append(_JNI_ESCAPES[two])
            i += 2
        else:
            out.append(text[i])
            i += 1
    return "".join(out)


def parse_jni_name(symbol: str) -> Optional[Dict[str, str]]:
    """Decode a Java_* JNI symbol into class / method / signature.

    JNI mangling: '_' escapes are _1 (underscore), _2 (semicolon), _3
    (array bracket); package separators are literal underscores.  The method
    starts at the *last literal underscore* of the encoded name, so escapes
    are decoded only after splitting.  Overload signatures follow '__'.
    """
    if not symbol.startswith("Java_"):
        return None
    body = symbol[len("Java_"):]
    signature = ""
    if "__" in body:
        body, signature = body.split("__", 1)
        signature = _decode_jni_component(signature)
    # Split at the last *unescaped* underscore: '\_1'..'\_3' are escapes and
    # therefore never the class/method separator.
    last = None
    for match in re.finditer(r"_(?![0-3])", body):
        last = match
    if last is None:
        return {"class": "", "method": _decode_jni_component(body),
                "signature": signature}
    cls_enc, meth_enc = body[:last.start()], body[last.start() + 1:]
    # Decode package-separator underscores first, then escapes, so a literal
    # '_' inside a class name (encoded _1) is not turned into a dot.
    cls = ".".join(_decode_jni_component(part)
                   for part in re.split(r"_(?![0-3])", cls_enc))
    return {
        "class": cls,
        "method": _decode_jni_component(meth_enc),
        "signature": signature,
    }


# --------------------------------------------------------------------------
# ELF parsing
# --------------------------------------------------------------------------

def _dyn_strings(binary) -> Tuple[Dict[int, str], int]:
    """Return (offset -> string) map for .dynstr plus its base address."""
    strtab = getattr(binary, "dynamic_string_table", None)
    base = None
    if strtab is not None:
        try:
            base = int(strtab.virtual_address)
            data = bytes(strtab.content)
            return _parse_strtab(data), base
        except Exception:
            pass
    for sec in binary.sections:
        if sec.name == ".dynstr":
            return _parse_strtab(bytes(sec.content)), int(sec.virtual_address)
    return {}, 0


def _parse_strtab(data: bytes) -> Dict[int, str]:
    out: Dict[int, str] = {}
    for m in re.finditer(rb"[^\x00]+", data):
        try:
            out[m.start()] = m.group().decode("utf-8", "replace")
        except Exception:
            pass
    return out


def _dynamic_values(binary, strtab: Dict[int, str]) -> Dict[str, object]:
    """Decode the dynamic table.

    LIEF 1.0 exposes the raw string-table offsets in DynamicEntry.value, so
    string-valued tags are resolved here against .dynstr.
    """
    result: Dict[str, object] = {
        "needed": [], "soname": None, "rpath": None, "runpath": None,
        "flags": {}, "entries": [],
    }
    str_tags = {"NEEDED", "SONAME", "RPATH", "RUNPATH", "AUXILIARY", "FILTER"}
    for entry in binary.dynamic_entries:
        tag = _enum_name(entry.tag)
        value = entry.value
        if tag in str_tags:
            text = None
            if isinstance(value, str):
                text = value
            elif isinstance(value, int):
                text = strtab.get(value)
            if text is not None:
                key = tag.lower()
                if tag == "NEEDED":
                    result["needed"].append(text)
                else:
                    result[key] = text
                continue
        elif tag in ("FLAGS", "FLAGS_1"):
            result["flags"][tag] = value
        result["entries"].append({"tag": tag, "value": value})
    # b.libraries is authoritative for NEEDED ordering when available.
    try:
        libs = list(binary.libraries)
        if libs:
            result["needed"] = libs
    except Exception:
        pass
    return result


def _build_id(binary) -> Optional[str]:
    for note in binary.notes:
        if "BUILD_ID" in _enum_name(note.type) or "GNU_BUILD_ID" in str(note.type):
            try:
                return bytes(note.description).hex()
            except Exception:
                continue
    return None


def _android_ident(binary) -> Dict[str, object]:
    for note in binary.notes:
        if "ANDROID" not in str(note.type).upper():
            continue
        try:
            desc = bytes(note.description)
        except Exception:
            continue
        if not desc:
            continue
        info: Dict[str, object] = {}
        if len(desc) >= 20:
            info["sdk"] = struct.unpack_from("<I", desc, 0)[0]
            info["ndk"] = desc[4:68].split(b"\x00", 1)[0].decode("ascii", "replace")
            rest = desc[68:132].split(b"\x00", 1)[0].decode("ascii", "replace")
            if rest:
                info["ndk_build"] = rest
            elif len(desc) > 68:
                alt = [p for p in desc[4:].split(b"\x00") if p]
                if len(alt) > 1:
                    info["ndk_build"] = alt[1].decode("ascii", "replace")
        return info
    return {}


def _section_dict(sec) -> Dict[str, object]:
    flags = _int_flags(sec.flags)
    return {
        "name": sec.name,
        "type": _enum_name(sec.type),
        "flags": _flags_str(flags, SECTION_FLAG_NAMES),
        "flags_raw": flags,
        "address": sec.virtual_address,
        "offset": sec.file_offset,
        "size": sec.size,
        "entry_size": sec.entry_size,
        "alignment": sec.alignment,
        "entropy": round(sec.entropy, 3) if sec.size else 0.0,
    }


def _segment_dict(seg) -> Dict[str, object]:
    flags = _int_flags(seg.flags)
    return {
        "type": _enum_name(seg.type),
        "flags": _flags_str(flags, SEGMENT_FLAG_NAMES),
        "flags_raw": flags,
        "offset": seg.file_offset,
        "address": seg.virtual_address,
        "filesz": seg.physical_size,
        "memsz": seg.virtual_size,
        "alignment": seg.alignment,
    }


def _dedup_symbols(symbols) -> List[object]:
    """LIEF 1.0 may yield the same symbol from several tables."""
    seen = set()
    out = []
    for sym in symbols:
        key = (sym.name, sym.value, sym.size, str(sym.type))
        if key in seen:
            continue
        seen.add(key)
        out.append(sym)
    return out


def _symbol_dict(sym) -> Dict[str, object]:
    try:
        demangled = sym.demangled_name
    except Exception:
        demangled = ""
    jni = parse_jni_name(sym.name)
    return {
        "name": sym.name,
        "demangled": demangled,
        "address": sym.value,
        "size": sym.size,
        "type": _enum_name(sym.type),
        "binding": _enum_name(sym.binding),
        "visibility": _enum_name(sym.visibility),
        "is_function": bool(sym.is_function),
        "is_jni": jni is not None,
    }


# --------------------------------------------------------------------------
# AI string extraction
# --------------------------------------------------------------------------

def _iter_section_strings(path: str, offset: int, size: int,
                          section_addr: int = 0) -> Iterable[Tuple[int, str]]:
    """Yield (file_offset, text) printable runs from a region of the file."""
    if size <= 0:
        return
    with open(path, "rb") as fh:
        pos = 0
        while pos < size:
            fh.seek(offset + pos)
            chunk = fh.read(min(READ_CHUNK, size - pos))
            if not chunk:
                break
            last_end = 0
            for m in PRINTABLE_RE.finditer(chunk):
                last_end = m.end()
                # Do not re-emit a run that started in the overlap.
                if pos > 0 and m.start() < CHUNK_OVERLAP:
                    continue
                yield offset + pos + m.start(), m.group().decode("ascii")
            if len(chunk) < CHUNK_OVERLAP:
                break
            pos += max(len(chunk) - CHUNK_OVERLAP, 1)
            if last_end == 0 and len(chunk) < READ_CHUNK:
                break


def collect_ai_strings(path: str, sections, limit: int = 200) -> Dict[str, object]:
    """Scan allocated, non-executable sections for AI-relevant strings."""
    per_cat: Dict[str, List[dict]] = {name: [] for name, _ in AI_STRING_PATTERNS}
    counts: Dict[str, int] = {name: 0 for name, _ in AI_STRING_PATTERNS}
    seen: set = set()
    total_scanned = 0

    for sec in sections:
        flags = _int_flags(sec.flags)
        if not flags & SHF_ALLOC or flags & SHF_EXECINSTR:
            continue
        if sec.size == 0 or _enum_name(sec.type) == "NOBITS":
            continue
        if sec.size > 256 * 1024 * 1024:      # sanity guard
            continue
        total_scanned += sec.size
        for file_off, text in _iter_section_strings(path, sec.file_offset, sec.size):
            if not ANY_AI_RE.search(text):
                continue
            matched = [name for name, rx in AI_STRING_RES if rx.search(text)]
            if not matched:
                continue
            if text in seen:
                continue
            seen.add(text)
            vaddr = int(sec.virtual_address) + (file_off - int(sec.file_offset))
            for name in matched:
                counts[name] += 1
                bucket = per_cat[name]
                if len(bucket) < limit:
                    bucket.append({
                        "value": text,
                        "offset": file_off,
                        "address": vaddr,
                        "section": sec.name,
                    })
        if sum(counts.values()) > 200000:
            break

    return {
        "scanned_bytes": total_scanned,
        "categories": per_cat,
        "counts": counts,
        "note": "strings from SHF_ALLOC non-executable sections; "
                "case-insensitive substring match, capped per category",
    }


# --------------------------------------------------------------------------
# pyelftools fallback
# --------------------------------------------------------------------------

def _pyelftools_fallback(path: str, data: Dict[str, object]) -> None:
    from elftools.elf.elffile import ELFFile
    with open(path, "rb") as fh:
        elf = ELFFile(fh)
        data["class"] = "ELF64" if elf.elfclass == 64 else "ELF32"
        data["endianness"] = elf.little_endian and "little" or "big"
        data["type"] = elf.header["e_type"]
        data["machine"] = elf.header["e_machine"]
        data["entrypoint"] = elf.header["e_entry"]
        data["sections"] = []
        for sec in elf.iter_sections():
            data["sections"].append({
                "name": sec.name,
                "type": sec["sh_type"],
                "address": sec["sh_addr"],
                "offset": sec["sh_offset"],
                "size": sec["sh_size"],
                "entry_size": sec["sh_entsize"],
                "alignment": sec["sh_addralign"],
            })
        data["segments"] = []
        for seg in elf.iter_segments():
            data["segments"].append({
                "type": seg["p_type"],
                "offset": seg["p_offset"],
                "address": seg["p_vaddr"],
                "filesz": seg["p_filesz"],
                "memsz": seg["p_memsz"],
                "alignment": seg["p_align"],
            })


# --------------------------------------------------------------------------
# Main collection
# --------------------------------------------------------------------------

def collect(path: str, strings_limit: int = 200,
            include_sections: bool = True) -> Dict[str, object]:
    st = os.stat(path)
    data: Dict[str, object] = {
        "file": os.path.abspath(path),
        "size": st.st_size,
        "sha256": sha256_file(path),
    }
    try:
        binary = lief.parse(path)
    except Exception as exc:
        binary = None
        data["lief_error"] = str(exc)
    if binary is None:
        _pyelftools_fallback(path, data)
        data["parser"] = "pyelftools"
        data.setdefault("needed", [])
        data.setdefault("symbols", {"exported": [], "imported": []})
        data.setdefault("jni_entrypoints", [])
        data.setdefault("strings", {})
        return data

    header = binary.header
    data.update({
        "parser": "lief",
        "lief_version": getattr(lief, "__version__", "unknown"),
        "class": _enum_name(header.identity_class),
        "endianness": "little" if _enum_name(header.identity_data).startswith("LSB")
                      else "big",
        "type": _enum_name(header.file_type),
        "machine": _enum_name(header.machine_type),
        "os_abi": _enum_name(header.identity_os_abi),
        "entrypoint": header.entrypoint,
        "imagebase": binary.imagebase,
        "interpreter": getattr(binary, "interpreter", "") or None,
        "build_id": _build_id(binary),
        "android": _android_ident(binary),
    })

    strtab, _ = _dyn_strings(binary)
    dyn = _dynamic_values(binary, strtab)
    data.update({
        "needed": dyn["needed"],
        "soname": dyn["soname"],
        "rpath": dyn["rpath"],
        "runpath": dyn["runpath"],
        "dynamic_flags": dyn["flags"],
        "dynamic_entries_count": len(binary.dynamic_entries),
    })

    data["sections"] = [_section_dict(s) for s in binary.sections] \
        if include_sections else []
    data["segments"] = [_segment_dict(s) for s in binary.segments]

    exported = _dedup_symbols(binary.exported_symbols)
    imported = _dedup_symbols(binary.imported_symbols)
    symtab_count = len(list(binary.symtab_symbols))
    dynsym_count = len(list(binary.dynamic_symbols))

    exported_dicts = [_symbol_dict(s) for s in exported]
    # JNI entry points: exported Java_* functions, plus symtab-defined ones.
    jni = [s for s in exported_dicts if s["is_jni"]]
    jni_names = {s["name"] for s in jni}
    for sym in binary.symtab_symbols:
        if sym.name in jni_names or not sym.name.startswith("Java_"):
            continue
        if not sym.value:
            continue
        d = _symbol_dict(sym)
        d["source"] = "symtab"
        jni.append(d)
        jni_names.add(sym.name)
    for entry in jni:
        info = parse_jni_name(entry["name"]) or {}
        entry.update({
            "jni_class": info.get("class", ""),
            "jni_method": info.get("method", ""),
            "jni_signature": info.get("signature", ""),
        })
    jni.sort(key=lambda s: s["address"])

    data["symbols"] = {
        "exported": exported_dicts,
        "imported": [_symbol_dict(s) for s in imported],
        "symtab_count": symtab_count,
        "dynsym_count": dynsym_count,
    }
    data["jni_entrypoints"] = jni
    data["strings"] = collect_ai_strings(path, binary.sections, strings_limit)
    data["statistics"] = {
        "exports": len(exported),
        "imports": len(imported),
        "jni_entrypoints": len(jni),
        "sections": len(data["sections"]),
        "segments": len(data["segments"]),
    }
    return data


# --------------------------------------------------------------------------
# Rendering
# --------------------------------------------------------------------------

def _fmt_hex(value) -> str:
    return "0x%x" % value if isinstance(value, int) else str(value)


def render_summary(data: Dict[str, object], max_symbols: int = 40,
                   max_strings: int = 20) -> str:
    out: List[str] = []
    w = out.append
    w("=" * 78)
    w("ELF: %s" % data["file"])
    w("=" * 78)
    w("size        : %d bytes (%.2f MiB)" %
      (data["size"], data["size"] / (1024.0 * 1024.0)))
    if data.get("sha256"):
        w("sha256      : %s" % data["sha256"])
    w("format      : %s %s %s, %s-endian, %s" %
      (data.get("class", "?"), data.get("type", "?"),
       data.get("machine", "?"), data.get("endianness", "?"),
       data.get("os_abi", "?")))
    w("entrypoint  : %s   imagebase: %s" %
      (_fmt_hex(data.get("entrypoint", 0)), _fmt_hex(data.get("imagebase", 0))))
    if data.get("interpreter"):
        w("interpreter : %s" % data["interpreter"])
    if data.get("build_id"):
        w("build id    : %s" % data["build_id"])
    if data.get("android"):
        w("android     : SDK %s, NDK %s%s" % (
            data["android"].get("sdk", "?"),
            data["android"].get("ndk", "?"),
            ", build " + data["android"]["ndk_build"]
            if data["android"].get("ndk_build") else ""))
    w("soname      : %s" % (data.get("soname") or "-"))
    if data.get("rpath"):
        w("rpath       : %s" % data["rpath"])
    if data.get("runpath"):
        w("runpath     : %s" % data["runpath"])
    w("needed      : %s" % (", ".join(data.get("needed") or []) or "-"))
    w("parser      : %s" % data.get("parser", "?"))

    stats = data.get("statistics") or {}
    if stats:
        w("counts      : %s exports, %s imports, %s JNI, %s sections, %s segments" % (
            stats.get("exports"), stats.get("imports"),
            stats.get("jni_entrypoints"), stats.get("sections"),
            stats.get("segments")))
    syms = data.get("symbols") or {}
    if syms:
        w("symtab/dynsym: %s / %s" %
          (syms.get("symtab_count", 0), syms.get("dynsym_count", 0)))

    secs = data.get("sections") or []
    if secs:
        w("")
        w("Sections (%d)" % len(secs))
        w("  %-22s %-16s %-6s %-12s %-10s %s" %
          ("name", "type", "flags", "address", "size", "offset"))
        for sec in secs:
            if not sec.get("name") and sec.get("size", 0) == 0:
                continue
            w("  %-22s %-16s %-6s %-12s %-10s %s" % (
                sec.get("name") or "<null>", sec.get("type"),
                sec.get("flags"), _fmt_hex(sec.get("address")),
                sec.get("size"), _fmt_hex(sec.get("offset"))))

    jni = data.get("jni_entrypoints") or []
    if jni:
        w("")
        w("JNI entry points (%d)" % len(jni))
        for entry in jni:
            w("  %-14s %-10s %s.%s" % (
                _fmt_hex(entry["address"]), entry.get("size", 0),
                entry.get("jni_class", "?"), entry.get("jni_method", "?")))
            w("      %s" % entry["name"])

    if syms.get("exports"):
        w("")
        w("Exported symbols (first %d of %d)" %
          (min(max_symbols, len(syms["exports"])), len(syms["exports"])))
        for sym in syms["exports"][:max_symbols]:
            dv = ("  # " + sym["demangled"]) if sym.get("demangled") and \
                 sym["demangled"] != sym["name"] else ""
            w("  %-12s %-8s %s%s" % (_fmt_hex(sym["address"]), sym.get("size", 0),
                                      sym["name"], dv))
        if len(syms["exports"]) > max_symbols:
            w("  ... %d more (use --json for the full list)" %
              (len(syms["exports"]) - max_symbols))

    if syms.get("imports"):
        w("")
        w("Imported symbols (%d)" % len(syms["imports"]))
        for sym in syms["imports"][:max_symbols]:
            w("  %s" % sym["name"])
        if len(syms["imports"]) > max_symbols:
            w("  ... %d more" % (len(syms["imports"]) - max_symbols))

    strings = data.get("strings") or {}
    cats = strings.get("categories") or {}
    counts = strings.get("counts") or {}
    if cats:
        w("")
        w("AI-relevant strings (%s bytes scanned in allocated data sections)" %
          strings.get("scanned_bytes", 0))
        for name, _ in AI_STRING_PATTERNS:
            bucket = cats.get(name) or []
            if not bucket:
                continue
            w("  [%s] %d unique (first %d)" %
              (name, counts.get(name, len(bucket)), min(max_strings, len(bucket))))
            for item in bucket[:max_strings]:
                w("    0x%08x %-10s %s" % (item["address"], item["section"],
                                           item["value"]))
            if len(bucket) > max_strings:
                w("    ... %d more" % (len(bucket) - max_strings))
    return "\n".join(out)


def main(argv: Optional[List[str]] = None) -> int:
    ap = argparse.ArgumentParser(
        description="Summarise an ELF shared library (LIEF): headers, "
                    "DT_NEEDED, symbols, JNI entry points, build-id, "
                    "sections and AI-relevant strings.")
    ap.add_argument("elf", help="path to ELF file")
    ap.add_argument("--json", metavar="PATH",
                    help="write JSON report (use '-' for stdout, suppresses summary)")
    ap.add_argument("--pretty", action="store_true", default=True,
                    help="pretty-print JSON (default)")
    ap.add_argument("--compact", action="store_true",
                    help="compact JSON")
    ap.add_argument("--strings-limit", type=int, default=200,
                    help="max unique strings retained per category (default 200)")
    ap.add_argument("--max-symbols", type=int, default=40,
                    help="max symbols shown in the human summary (default 40)")
    ap.add_argument("--max-strings", type=int, default=20,
                    help="max strings shown per category in the summary (default 20)")
    args = ap.parse_args(argv)

    if not os.path.isfile(args.elf):
        sys.stderr.write("elf_info.py: no such file: %s\n" % args.elf)
        return 2
    try:
        data = collect(args.elf, strings_limit=max(args.strings_limit, 0))
    except Exception as exc:
        sys.stderr.write("elf_info.py: failed to parse %s: %s\n" % (args.elf, exc))
        return 2

    if args.json:
        payload = json.dumps(data, indent=None if args.compact else 2,
                             sort_keys=False)
        if args.json == "-":
            print(payload)
        else:
            with open(args.json, "w") as fh:
                fh.write(payload)
                fh.write("\n")
            print(render_summary(data, args.max_symbols, args.max_strings))
            print("\nJSON written to %s" % args.json)
    else:
        print(render_summary(data, args.max_symbols, args.max_strings))
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except BrokenPipeError:      # e.g. `tool ... | head`
        os._exit(0)
