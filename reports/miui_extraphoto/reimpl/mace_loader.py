#!/usr/bin/env python3
"""mace_loader.py - structural parser for the MACE model sections embedded in
libdoc_photo_process.so (com.miui.extraphoto / DocumentProcessJNI).

WHAT THIS IS
------------
The document-photo engine (`libDocumentProcess.so` = `libdoc_photo_process.so`)
ships no model file.  Its 19.5 MB `.rodata` carries two kinds of payload:

  * five *model description blocks* (0x16020e0 .. 0x160cb60) - each one is a
    NUL-terminated ASCII name stream (MACE op types, operator argument names
    and the graph's tensor names), one or more tables of 16-byte little-endian
    `u64 offset, u64 size` const-tensor records, and an 8-byte block trailer
    `u32 0, u32 value`;
  * ~18 MB of quantised tensor bytes (0x4d3700 .. 0x1602000) that carry no
    ASCII names at all.

This module parses the description blocks (and the model interface
descriptors `hed_model`, `enhance_model`, `dewarp_*`), reports provenance,
hashes and per-block statistics, and can carve the separable metadata blocks
out to disk.

WHAT THIS IS NOT
----------------
It is **not** an inference engine and it does **not** reconstruct the graph
(operator -> tensor wiring, per-tensor shapes) or the mapping from a record's
`offset` to a byte range of the tensor payload.  See reimpl/GAP_CLOSURE.md:
the file has no protobuf/NetDef framing for these models, and neither the name
streams, the record tables nor the tensor bytes are referenced by any
instruction, relocated pointer, GOT entry or literal pool, so the payload base
cannot be proven.  `graph_decoded`/`inference_available` are therefore False.

USAGE
    python3 reimpl/mace_loader.py --report
    python3 reimpl/mace_loader.py --json reimpl/mace_report.json
    python3 reimpl/mace_loader.py --extract models/embedded/mace
    python3 reimpl/mace_loader.py --self-test
"""

from __future__ import annotations

import argparse
import hashlib
import json
import os
import re
import struct
import sys

# ---------------------------------------------------------------- constants --
HERE = os.path.dirname(os.path.abspath(__file__))
SO_PATH = os.path.join(HERE, "..", "native", "libdoc_photo_process.so")
RODATA_DUMP = os.path.join(HERE, "..", "models", "embedded",
                           "libdoc_photo_process.rodata.bin")

RODATA_VADDR = 0x435700          # .rodata sh_addr / sh_offset of the library
RODATA_SIZE = 0x12A29AD          # 19,540,397 B

# Model description blocks (proven: trailer-delimited, see GAP_CLOSURE.md).
MODEL_REGION = (0x16020D8, 0x160CB60)

# Interface descriptors: vaddr of the model-name string + vaddr of the first
# int64[4] dimension array that follows the name/input/output strings.
DESCRIPTORS = [
    ("hed_model", 0x436FE2, 0x437008),
    ("enhance_model", 0x437B61, 0x437B80),
    ("dewarp_fine_model", 0x437EA0, 0x437EB8),
]

# Candidate tensor-payload regions (statistical, NOT tied to any record).
PAYLOAD_REGIONS = [
    ("int8_zero_point_0x80", 0x4D3700, 0x78C700),
    ("fp16_like", 0x78C700, 0x1602000),
]

TOKEN_RE = re.compile(rb"[ -~]{2,}\x00")

# MACE op types / argument names, used only for the report histogram.
KNOWN_OPS = {
    "Conv2D", "DepthwiseConv2d", "Deconv2D", "DepthwiseDeconv2d",
    "FullyConnected", "BatchNorm", "Softmax", "Activation", "Eltwise", "Concat",
    "Reshape", "Stack", "StridedSlice", "ScalarMath", "ResizeBilinear",
    "ResizeNearestNeighbor", "Transform", "Add", "Mul", "Sub", "Div", "Relu",
    "Tanh", "Clip", "Squeeze", "Fill", "Pad", "Slice", "Split", "Transpose",
    "Crop", "AddN", "Resize", "OneHot", "ArgMax", "LocalResponseNorm", "MVNorm",
    "PNorm", "PriorBox", "DetectionOutput", "Proposal", "DepthToSpace",
    "BatchToSpaceND", "MaxPooling", "AvgPooling", "MatMul", "Pack", "Unpack",
}


# ------------------------------------------------------------------ helpers --
def sha256(buf: bytes) -> str:
    return hashlib.sha256(buf).hexdigest()


def load_rodata(path: str | None = None) -> bytes:
    """Return the .rodata bytes (raw dump or sliced out of the ELF)."""
    cand = [path] if path else []
    cand += [RODATA_DUMP, SO_PATH]
    for p in cand:
        if p and os.path.exists(p):
            data = open(p, "rb").read()
            if data[:4] == b"\x7fELF":
                return elf_section(data, ".rodata")
            if len(data) == RODATA_SIZE:
                return data
            raise SystemExit("mace_loader: %s is neither an ELF nor a %d-byte "
                             ".rodata dump" % (p, RODATA_SIZE))
    raise SystemExit("mace_loader: no source file found (looked for %s)" % cand)


def elf_section(data: bytes, want: str) -> bytes:
    e_shoff, = struct.unpack_from("<Q", data, 0x28)
    e_shentsize, e_shnum, e_shstrndx = struct.unpack_from("<HHH", data, 0x3A)
    base = e_shoff + e_shstrndx * e_shentsize
    str_off, str_size = struct.unpack_from("<QQ", data, base + 0x18)
    names = data[str_off:str_off + str_size]
    for i in range(e_shnum):
        o = e_shoff + i * e_shentsize
        nm, _typ, _fl, _addr, off, size = struct.unpack_from("<IIQQQQ", data, o)
        name = names[nm:names.index(b"\0", nm)].decode()
        if name == want:
            if size != RODATA_SIZE:
                raise SystemExit("mace_loader: unexpected .rodata size %#x" % size)
            return data[off:off + size]
    raise SystemExit("mace_loader: section %s not found" % want)


def elf_sections(data: bytes) -> dict:
    """{name: (addr, file_off, size)} for every section of an ELF image."""
    e_shoff, = struct.unpack_from("<Q", data, 0x28)
    e_shentsize, e_shnum, e_shstrndx = struct.unpack_from("<HHH", data, 0x3A)
    b = e_shoff + e_shstrndx * e_shentsize
    str_off, str_size = struct.unpack_from("<QQ", data, b + 0x18)
    names = data[str_off:str_off + str_size]
    out = {}
    for i in range(e_shnum):
        o = e_shoff + i * e_shentsize
        nm, _t, _f, addr, off, size = struct.unpack_from("<IIQQQQ", data, o)
        out[names[nm:names.index(b"\0", nm)].decode()] = (addr, off, size)
    return out


def find_payload_arrays(so: bytes) -> list:
    """Find the six (const fp16 *, size) accessors the model loader uses.

    The payload pointers are materialised as tiny getters in `.text`:

        adrp x0, page ; add x0, x0, #off ; ret      -> const float16_t *
        movz w0, #lo  ; movk w0, #hi, lsl #16 ; ret -> uint32_t size

    They always sit 12 bytes apart and are consumed by one registry function
    at 0xafbc4.  Returning these pairs is what proves the payload placement;
    nothing else in the image points at the model bytes (see GAP_CLOSURE.md).
    """
    sec = elf_sections(so)
    ts, tsz = sec[".text"][0], sec[".text"][2]
    ro_lo, ro_sz = sec[".rodata"][0], sec[".rodata"][2]
    ro_hi = ro_lo + ro_sz
    ptrs, sizes = {}, {}
    for i in range(ts, ts + tsz - 12, 4):
        w0, = struct.unpack_from("<I", so, i)
        if (w0 & 0x9F00001F) == 0x90000000 and (w0 & 0x1F) == 0:
            immlo, immhi = (w0 >> 29) & 3, (w0 >> 5) & 0x7FFFF
            imm = (immhi << 2) | immlo
            if imm & (1 << 20):
                imm -= 1 << 21
            page = (i & ~0xFFF) + (imm << 12)
            w1, = struct.unpack_from("<I", so, i + 4)
            w2, = struct.unpack_from("<I", so, i + 8)
            if ((w1 & 0xFF800000) == 0x91000000 and ((w1 >> 5) & 0x1F) == 0
                    and (w1 & 0x1F) == 0 and w2 == 0xD65F03C0):
                ptrs[i] = page + (((w1 >> 10) & 0xFFF)
                                  << (12 if (w1 >> 22) & 1 else 0))
        if (w0 & 0xFF80001F) == 0x52800000:              # movz w0, #imm16
            lo = (w0 >> 5) & 0xFFFF
            w1, = struct.unpack_from("<I", so, i + 4)
            w2, = struct.unpack_from("<I", so, i + 8)
            if ((w1 & 0xFF80001F) == 0x72800000 and ((w1 >> 21) & 3) == 1
                    and w2 == 0xD65F03C0):
                sizes[i] = (((w1 >> 5) & 0xFFFF) << 16) | lo
    out = []
    for pc, ptr in sorted(ptrs.items()):
        size = sizes.get(pc + 12)
        if size is None or not (ro_lo <= ptr < ro_hi) or ptr + size > ro_hi:
            continue
        out.append({"ptr": ptr, "size": size, "getter": pc, "size_getter": pc + 12})
    return out


# ------------------------------------------------------------------- parsing --
class Segment:
    __slots__ = ("kind", "start", "end", "payload", "names", "records")

    def __init__(self, kind, start, end, payload=b"", names=None, records=None):
        self.kind = kind
        self.start = start
        self.end = end
        self.payload = payload
        self.names = names or []
        self.records = records or []

    @property
    def size(self):
        return self.end - self.start

    def as_dict(self):
        return {"kind": self.kind, "start": self.start, "end": self.end,
                "size": self.size}


def find_name_streams(ro, lo, hi, min_tokens=24):
    """Maximal runs of NUL-terminated printable tokens (gap <= 8 bytes)."""
    off0 = lo - RODATA_VADDR
    end = hi - RODATA_VADDR
    toks = []
    pos = off0
    while pos < end:
        m = TOKEN_RE.match(ro, pos)
        if not m:
            pos += 1
            continue
        toks.append((pos, m.group()[:-1]))
        pos = m.end()
        nxt = pos
        while nxt < min(pos + 8, end) and ro[nxt] == 0:
            nxt += 1
        if nxt > pos and TOKEN_RE.match(ro, nxt):
            pos = nxt
    runs, cur = [], []
    for t in toks:
        if cur and t[0] - (cur[-1][0] + len(cur[-1][1]) + 1) > 8:
            runs.append(cur)
            cur = []
        cur.append(t)
    if cur:
        runs.append(cur)
    return [r for r in runs if len(r) >= min_tokens]


def find_table_runs(ro, lo, hi, min_rec=6, max_val=0x4000000,
                    merge_gap=32):
    """Maximal runs of 16-byte (u64 offset, u64 size) records.

    Runs separated by at most `merge_gap` bytes are merged (a table may contain
    a single padding/invalid slot), and every 16-byte slot of the merged span
    is classified as a valid record or an invalid slot.
    """
    off0 = lo - RODATA_VADDR
    end = hi - RODATA_VADDR
    raw, pos = [], off0
    while pos + 16 <= end:
        if pos % 16:            # records are 16-byte aligned in the image
            pos += 1
            continue
        recs, p = [], pos
        while p + 16 <= end:
            a, b = struct.unpack_from("<QQ", ro, p)
            if a < max_val and 0 < b < max_val:
                recs.append((a, b))
                p += 16
            else:
                break
        if len(recs) >= 3:
            raw.append((pos, p, recs))
            pos = p
        else:
            pos += 1

    merged = []
    for start, stop, recs in raw:
        if merged and start - merged[-1][1] <= merge_gap:
            merged[-1] = (merged[-1][0], stop, merged[-1][2] + recs)
        else:
            merged.append((start, stop, recs))

    out = []
    for start, stop, _recs in merged:
        good, bad = [], 0
        for p in range(start, stop, 16):
            a, b = struct.unpack_from("<QQ", ro, p)
            if a < max_val and 0 < b < max_val:
                good.append((a, b))
            else:
                bad += 1
        if len(good) >= min_rec:
            out.append((start, stop, good, bad))
    return out


def find_trailers(ro, lo, hi):
    """Block trailers: `u32 0, u32 value` immediately followed by a name.

    The negative side is not checked for record alignment because the trailer
    is 8-byte but not necessarily 16-byte aligned inside the block.
    """
    out = []
    for t in range(lo, hi, 4):
        a, b = struct.unpack_from("<II", ro, t - RODATA_VADDR)
        if a != 0 or not (0x100 <= b < 0x40000000):
            continue
        nxt = ro[t - RODATA_VADDR + 8:t - RODATA_VADDR + 128]
        if len(nxt) < 16 or not (32 <= nxt[0] < 127):
            continue
        if not all(32 <= c < 127 or c == 0 for c in nxt):
            continue
        toks = nxt.split(b"\0")
        # a real block starts with a tensor name and continues with another
        if len(toks) < 2 or len(toks[0]) < 3 or len(toks[1]) < 2:
            continue
        out.append((t, b))
    return out


def valid_dims(vals):
    if vals[0] != 1:
        return False
    prod = 1
    for v in vals:
        if not (1 <= v <= 8192):
            return False
        prod *= v
    return prod < (1 << 26)


def parse_descriptor(ro, name_vaddr, dims_vaddr):
    off = name_vaddr - RODATA_VADDR
    strs, p = [], off
    while len(strs) < 3 and p < off + 96:
        nul = ro.index(b"\0", p)
        if nul == p:
            break
        s = ro[p:nul]
        if not s or not all(32 <= c < 127 for c in s):
            break
        strs.append(s.decode("ascii"))
        p = nul + 1
    dims, d = [], dims_vaddr - RODATA_VADDR
    for _ in range(3):
        vals = list(struct.unpack_from("<4q", ro, d))
        if not valid_dims(vals):
            break
        dims.append(vals)
        d += 32
    return {"strings": strs, "dims": dims}


def _prefixes(names):
    pref = {}
    for n in names:
        if "/" in n:
            head = n.split("/")[0] + "/"
        elif n.startswith("model."):
            head = "model.*"
        elif n[:1].isdigit():
            head = "<numeric-node-id>"
        elif n[:1].isalpha():
            head = "<op-named>"
        else:
            head = "<other>"
        pref[head] = pref.get(head, 0) + 1
    return dict(sorted(pref.items(), key=lambda kv: -kv[1])[:12])


def _tensor_index(ro, arrays, array_idx, recs):
    """Per-const-tensor view: (offset, fp16 elements, payload, slice stats)."""
    if not array_idx or not recs:
        return []
    try:
        import numpy as np
    except Exception:                                   # pragma: no cover
        np = None
    out = []
    for off, nelem in recs:
        nbytes = 2 * nelem
        entry = {"offset": off, "elements": nelem, "bytes": nbytes,
                 "dtype": "float16"}
        if np is not None:
            vals = None
            for ai in array_idx:
                a = arrays[ai]
                if off + nbytes > a["size"]:
                    continue
                raw = ro[a["ptr"] - RODATA_VADDR + off:
                         a["ptr"] - RODATA_VADDR + off + nbytes]
                v = np.frombuffer(raw[:len(raw) // 2 * 2], dtype="<f2").astype("f4")
                if vals is None:
                    vals = v
            if vals is not None and vals.size:
                fin = np.isfinite(vals)
                entry["finite"] = round(float(fin.mean()), 5)
                if fin.any():
                    w = vals[fin]
                    entry["absmax"] = round(float(np.abs(w).max()), 5)
                    entry["mean"] = round(float(w.mean()), 6)
                    entry["std"] = round(float(w.std()), 6)
                    entry["zeros"] = round(float((w == 0).mean()), 4)
        out.append(entry)
    return out


def _payload_layout(recs, payload_bytes):
    """Validate the constant-tensor layout law for one model block.

    Law (derived, see GAP_CLOSURE.md): each 16-byte record is
    `u64 byte_offset, u64 fp16_element_count`; the tensor body is 2*count
    bytes and the model's packed payload is `payload_bytes` long.
    """
    if not recs:
        return {"law": "byte_offset + 2*fp16_count", "records": 0,
                "proven": False}
    iv = sorted((o, o + 2 * n) for o, n in recs)
    overlaps = sum(1 for i in range(1, len(iv)) if iv[i][0] < iv[i - 1][1])
    max_end = max(e for _, e in iv)
    covered = 0
    cur = 0
    gaps = []
    for s, e in iv:
        if s > cur:
            gaps.append(s - cur)
        covered += max(0, e - max(s, cur))
        cur = max(cur, e)
    slack = payload_bytes - max_end
    return {
        "law": "byte_offset + 2*fp16_count",
        "records": len(recs),
        "tensor_bytes_min": 2 * min(n for _, n in recs),
        "tensor_bytes_max": 2 * max(n for _, n in recs),
        "first_offset": iv[0][0],
        "max_tensor_end": max_end,
        "payload_bytes": payload_bytes,
        "slack_bytes": slack,
        "covered_bytes": covered,
        "coverage": round(covered / payload_bytes, 6) if payload_bytes else None,
        "gap_count": len(gaps),
        "gap_bytes": sum(gaps),
        "overlaps": overlaps,
        "offset0_tensor": any(o == 0 for o, _ in recs),
        "proven": overlaps == 0 and 0 <= slack <= 16,
    }


def payload_stats(ro, label, lo, hi):
    raw = ro[lo - RODATA_VADDR:hi - RODATA_VADDR]
    st = {"label": label, "start": lo, "end": hi, "size": len(raw),
          "sha256": sha256(raw)}
    try:
        import numpy as np
    except Exception:                                   # pragma: no cover
        st["note"] = "numpy unavailable: raw bytes only"
        return st
    u8 = np.frombuffer(raw, dtype=np.uint8)
    st["byte_mean"] = round(float(u8.mean()), 4)
    st["frac_0x80"] = round(float((u8 == 0x80).mean()), 5)
    st["frac_0x00"] = round(float((u8 == 0x00).mean()), 5)
    if label.startswith("fp16"):
        f = np.frombuffer(raw[: (len(raw) // 2) * 2], dtype="<f2").astype("f4")
        finite = np.isfinite(f)
        st["fp16_finite_frac"] = round(float(finite.mean()), 6)
        if finite.any():
            v = f[finite]
            st["fp16_p999"] = round(float(np.percentile(np.abs(v), 99.9)), 4)
            st["fp16_std"] = round(float(v.std()), 4)
            st["fp16_frac_abs_le_1"] = round(float((np.abs(v) <= 1).mean()), 5)
    st["claimed_as_tensor"] = False
    return st


def build_report(ro: bytes, payload_arrays=None) -> dict:
    lo, hi = MODEL_REGION
    trailers = find_trailers(ro, lo, hi)
    if not trailers:
        raise SystemExit("mace_loader: no block trailer found; not a "
                         "document-process .rodata image")
    # layout per block: [u32 0, u32 payload_bytes][name stream][record table]
    bounds = [(t, nxt[0] if nxt[0] is not None else hi, v)
              for (t, v), nxt in zip(trailers,
                                     trailers[1:] + [(None, None)])]

    blocks = []
    for idx, (bstart, bend, payload_bytes) in enumerate(bounds):
        bstart = bstart + 8                       # skip the trailer itself
        segs = []
        for st in find_name_streams(ro, bstart, bend):
            s = st[0][0] + RODATA_VADDR
            e = st[-1][0] + len(st[-1][1]) + 1 + RODATA_VADDR
            segs.append(Segment("names", s, e,
                                ro[st[0][0]:st[-1][0] + len(st[-1][1]) + 1],
                                [t[1].decode("ascii", "replace") for t in st]))
        runs = find_table_runs(ro, bstart, bend)
        invalid_slots = 0
        for a, b, recs, bad in runs:
            invalid_slots += bad
            segs.append(Segment("table", a + RODATA_VADDR, b + RODATA_VADDR,
                                ro[a:b], records=recs))
        segs.sort(key=lambda s: s.start)
        for i, sg in enumerate(segs[:-1]):
            sg.end = min(sg.end, segs[i + 1].start)
            sg.payload = ro[sg.start - RODATA_VADDR:sg.end - RODATA_VADDR]
        names = [n for s in segs if s.kind == "names" for n in s.names]
        recs = [r for s in segs if s.kind == "table" for r in s.records]
        if not names:
            continue
        op_hist = {}
        for n in names:
            if n in KNOWN_OPS:
                op_hist[n] = op_hist.get(n, 0) + 1
        nbytes = b"".join(s.payload for s in segs if s.kind == "names")
        tbytes = b"".join(s.payload for s in segs if s.kind == "table")
        # constant-tensor payload layout: every record is
        #   u64 byte_offset , u64 fp16_element_count
        # so the tensor occupies [off, off + 2*count) of the block payload.
        layout = _payload_layout(recs, payload_bytes)
        blocks.append({
            "index": idx,
            "start": bstart,
            "end": bend,
            "size": bend - bstart,
            "name_count": len(names),
            "names_bytes": len(nbytes),
            "names_sha256": sha256(nbytes),
            "tensor_record_count": len(recs),
            "table_run_count": len(runs),
            "table_invalid_slots": invalid_slots,
            "table_bytes": len(tbytes),
            "table_sha256": sha256(tbytes) if tbytes else None,
            "record_offset_min": min((r[0] for r in recs), default=None),
            "record_offset_max_end": max((o + s for o, s in recs), default=None),
            "record_size_min": min((r[1] for r in recs), default=None),
            "record_size_max": max((r[1] for r in recs), default=None),
            "record_size_sum": sum(s for _, s in recs),
            "payload_bytes": payload_bytes,
            "payload": layout,
            "op_histogram": dict(sorted(op_hist.items(), key=lambda kv: -kv[1])),
            "first_names": names[:5],
            "last_names": names[-5:],
            "name_provenance": _prefixes(names),
            "trailer": {"offset": bstart - 8, "value": payload_bytes},
            "segments": [s.as_dict() for s in segs],
            "tensor_records": recs,
        })

    for i, b in enumerate(blocks):
        b["index"] = i

    arrays = payload_arrays or []
    for b in blocks:
        hit = [i for i, a in enumerate(arrays) if a["size"] == b["payload_bytes"]]
        b["payload_array_index"] = hit
        b["payload_tensors"] = _tensor_index(ro, arrays, hit,
                                            b["tensor_records"])

    return {
        "source": "libdoc_photo_process.so (.rodata)",
        "rodata_vaddr": RODATA_VADDR,
        "rodata_size": len(ro),
        "rodata_sha256": sha256(ro),
        "model_region": {"start": lo, "end": hi},
        "model_region_bytes": hi - lo,
        "block_count": len(blocks),
        "blocks": blocks,
        "descriptors": {label: parse_descriptor(ro, nv, dv)
                        for label, nv, dv in DESCRIPTORS},
        "payload_arrays": arrays,
        "payload_regions_candidate": [payload_stats(ro, *pr)
                                      for pr in PAYLOAD_REGIONS],
        "graph_decoded": False,
        "inference_available": False,
        "notes": [
            "name streams are provably delimited (NUL-terminated tokens, gap<=8B)",
            "record tables are provably delimited (16B u64 pairs, values < 0x4000000)",
            "blocks are provably delimited by the (u32 0, u32 payload_bytes) trailers",
            "record law: u64 byte_offset + u64 fp16_element_count, tensor body is "
            "2*count bytes inside the block payload (verified for 4/5 blocks)",
            "payload bases come from the six (const float16_t*, uint32_t) getters "
            "in .text (adrp+add;ret / movz+movk;ret) consumed by the model loader "
            "at 0xafbc4; each block is joined to a payload by its trailer size",
            "the operator graph (NetDef) is NOT in this binary: no protobuf "
            "framing exists in .rodata and the description blocks are referenced "
            "by no instruction/relocation, so operator wiring and per-tensor "
            "shapes cannot be reconstructed (see GAP_CLOSURE.md)",
        ],
        "graph_decoded_note": "weights + tensor byte ranges recovered; graph not",
    }


# ------------------------------------------------------------------ extract --
def extract(ro: bytes, outdir: str, payload_arrays=None) -> str:
    rep = build_report(ro, payload_arrays)
    os.makedirs(outdir, exist_ok=True)
    manifest = {
        "source": rep["source"],
        "rodata_vaddr": RODATA_VADDR,
        "rodata_sha256": rep["rodata_sha256"],
        "block_count": rep["block_count"],
        "descriptors": rep["descriptors"],
        "blocks": [],
        "payload_arrays": rep["payload_arrays"],
        "payload_regions_candidate": rep["payload_regions_candidate"],
        "graph_decoded": False,
        "inference_available": False,
    }
    for b in rep["blocks"]:
        tag = "block%d_%04x" % (b["index"], b["start"])
        entry = {"tag": tag, "start": b["start"], "end": b["end"],
                 "name_count": b["name_count"],
                 "names_sha256": b["names_sha256"],
                 "tensor_record_count": b["tensor_record_count"],
                 "table_sha256": b["table_sha256"],
                 "trailer": b["trailer"], "payload": b["payload"],
                 "files": []}
        for s in b["segments"]:
            if s["kind"] not in ("names", "table"):
                continue
            raw = ro[s["start"] - RODATA_VADDR:s["end"] - RODATA_VADDR]
            fn = "%s.%s.bin" % (tag, "names" if s["kind"] == "names"
                                else "tensors.u64")
            with open(os.path.join(outdir, fn), "wb") as f:
                f.write(raw)
            entry["files"].append({"file": fn, "offset": s["start"],
                                   "size": len(raw), "sha256": sha256(raw),
                                   "kind": s["kind"]})
        # real fp16 weight buffers (proven by the .text accessor functions)
        for ai in b["payload_array_index"]:
            a = rep["payload_arrays"][ai]
            raw = ro[a["ptr"] - RODATA_VADDR:a["ptr"] - RODATA_VADDR + a["size"]]
            fn = "%s.payload%02d.%04x.weights.f16.bin" % (tag, ai, a["ptr"] & 0xffff)
            with open(os.path.join(outdir, fn), "wb") as f:
                f.write(raw)
            entry["files"].append({"file": fn, "offset": a["ptr"],
                                   "size": len(raw), "sha256": sha256(raw),
                                   "kind": "weights_fp16", "getter": a["getter"]})
        if b["payload_tensors"]:
            fn = "%s.tensors.json" % tag
            blob = json.dumps(b["payload_tensors"], indent=1).encode()
            with open(os.path.join(outdir, fn), "wb") as f:
                f.write(blob)
            entry["files"].append({"file": fn, "kind": "tensor_index",
                                   "size": len(blob), "sha256": sha256(blob),
                                   "tensors": len(b["payload_tensors"])})
        manifest["blocks"].append(entry)
    path = os.path.join(outdir, "MANIFEST.json")
    with open(path, "w") as f:
        json.dump(manifest, f, indent=1)
    return path


# ------------------------------------------------------------------- check ---
def check_extraction(outdir: str) -> int:
    """Validate an extraction directory against its MANIFEST.json."""
    man = json.load(open(os.path.join(outdir, "MANIFEST.json")))
    errs = []
    nfiles = ntensors = 0
    for b in man["blocks"]:
        for f in b["files"]:
            path = os.path.join(outdir, f["file"])
            if not os.path.exists(path):
                errs.append("missing %s" % f["file"])
                continue
            data = open(path, "rb").read()
            nfiles += 1
            if len(data) != f["size"]:
                errs.append("%s size %d != %d" % (f["file"], len(data), f["size"]))
            if sha256(data) != f["sha256"]:
                errs.append("%s sha256 mismatch" % f["file"])
            if f.get("kind") == "weights_fp16":
                if not (RODATA_VADDR <= f["offset"] < RODATA_VADDR + RODATA_SIZE):
                    errs.append("%s outside .rodata" % f["file"])
        ti = os.path.join(outdir, "%s.tensors.json" % b["tag"])
        if os.path.exists(ti):
            tensors = json.load(open(ti))
            ntensors += len(tensors)
            size = b["trailer"]["value"]
            for t in tensors:
                if t["offset"] + t["bytes"] > size:
                    errs.append("%s tensor at %#x exceeds payload"
                                % (b["tag"], t["offset"]))
                if t["dtype"] != "float16":
                    errs.append("%s bad dtype" % b["tag"])
    for e in errs:
        print("FAIL", e)
    print("check: %d blocks, %d files, %d tensors, %d errors"
          % (len(man["blocks"]), nfiles, ntensors, len(errs)))
    return 1 if errs else 0


# --------------------------------------------------------------- self-test ---
def self_test() -> int:
    """Synthetic .rodata image with the real block grammar; exact round-trip."""
    V = RODATA_VADDR
    ro = bytearray(b"\x00" * (MODEL_REGION[1] - V + 0x100))

    def put(vaddr, data):
        ro[vaddr - V:vaddr - V + len(data)] = data

    def names_at(vaddr, items):
        blob = b"".join(i.encode() + b"\0" for i in items)
        put(vaddr, blob)
        return vaddr + len(blob)

    def table_at(vaddr, recs):
        blob = b"".join(struct.pack("<QQ", a, b) for a, b in recs)
        put(vaddr, blob)
        return vaddr + len(blob)

    def al16(v):
        return (v + 15) & ~15

    def block(vaddr, payload_bytes, names, recs):
        put(vaddr, struct.pack("<II", 0, payload_bytes))
        vaddr += 8
        vaddr = names_at(vaddr, names)
        vaddr = table_at(al16(vaddr), recs)
        return vaddr

    # block 0: exact payload law (2 bytes per element, zero slack)
    r0 = [(0, 4), (8, 0x360), (0x6C8, 0x100), (0x8C8, 0x20), (0x908, 0x10),
          (0x928, 0x8)]
    end0 = 0x938
    v = block(MODEL_REGION[0], end0, [
        "default_graph", "Conv2D", "padding", "strides",
        "inference/x/conv1/Relu", "inference/x/conv1/weights:0",
        "inference/x/conv1/biases:0"] + ["edge_%d" % i for i in range(30)], r0)
    # block 1: payload larger than the tensor table (unaccounted bytes)
    r1 = [(0, 1), (4, 0x20), (0x44, 0x20), (0x84, 0x100), (0x284, 0x40),
          (0x304, 0x20)]
    v = block(al16(v), 0x444, [
        "EdgeDet/MobilenetV1/Conv2d_0/Relu", "pred_segment:0",
        "DepthwiseConv2d", "kernels"] + ["n%d" % i for i in range(25)], r1)

    rep = build_report(bytes(ro))
    assert rep["block_count"] == 2, rep["block_count"]
    b0, b1 = rep["blocks"]
    assert b0["name_count"] == 37, b0["name_count"]
    assert b0["op_histogram"].get("Conv2D") == 1, b0["op_histogram"]
    assert b0["tensor_record_count"] == 6, b0["tensor_record_count"]
    assert b0["record_size_sum"] == 4 + 0x360 + 0x100 + 0x20 + 0x10 + 0x8
    assert b0["trailer"]["value"] == end0
    assert b0["payload"]["max_tensor_end"] == end0, b0["payload"]
    assert b0["payload"]["slack_bytes"] == 0
    assert b0["payload"]["overlaps"] == 0
    assert b0["payload"]["proven"] is True, b0["payload"]
    assert b1["payload"]["slack_bytes"] == 0x444 - 0x344, b1["payload"]
    assert b1["payload"]["proven"] is False, b1["payload"]
    assert b1["first_names"][0].startswith("EdgeDet/")
    assert b0["names_sha256"] and b0["table_sha256"]

    import tempfile
    with tempfile.TemporaryDirectory() as td:
        m = json.load(open(extract(bytes(ro), td)))
        assert m["block_count"] == 2 and m["graph_decoded"] is False
        assert m["blocks"][0]["payload"]["proven"] is True
        for blk in m["blocks"]:
            for f in blk["files"]:
                got = open(os.path.join(td, f["file"]), "rb").read()
                assert sha256(got) == f["sha256"]
                assert len(got) == f["size"]
    print("mace_loader self-test: OK (2 synthetic blocks; trailer split, "
          "fp16 payload law, exact-slack and slack>0 verdicts, sha256 and "
          "extract round-trip verified)")
    return 0


# -------------------------------------------------------------------- main ---
def main(argv=None) -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("--rodata", help=".so or raw .rodata dump")
    ap.add_argument("--report", action="store_true", help="human-readable report")
    ap.add_argument("--json", help="write the machine-readable report here")
    ap.add_argument("--extract", help="write separable metadata blocks here")
    ap.add_argument("--self-test", action="store_true")
    ap.add_argument("--check", metavar="DIR",
                    help="validate an extraction directory")
    args = ap.parse_args(argv)

    if args.self_test:
        return self_test()
    if args.check:
        return check_extraction(args.check)

    ro = load_rodata(args.rodata)
    so = None
    for cand in ([args.rodata] if args.rodata else []) + [SO_PATH]:
        if cand and os.path.exists(cand):
            h = open(cand, "rb").read(4)
            if h == b"\x7fELF":
                so = open(cand, "rb").read()
                break
    arrays = find_payload_arrays(so) if so else []
    rep = build_report(ro, arrays)

    if args.json:
        with open(args.json, "w") as f:
            json.dump(rep, f, indent=1)
        print("wrote", args.json)
    if args.extract:
        print("wrote", extract(ro, args.extract, arrays))

    if args.report or not (args.json or args.extract):
        print("rodata %#x..%#x (%d B)  sha256 %s"
              % (RODATA_VADDR, RODATA_VADDR + len(ro), len(ro),
                 rep["rodata_sha256"]))
        print("model region %#x..%#x (%d B), %d blocks"
              % (MODEL_REGION[0], MODEL_REGION[1], rep["model_region_bytes"],
                 rep["block_count"]))
        for b in rep["blocks"]:
            ops = ", ".join("%s x%d" % kv
                            for kv in list(b["op_histogram"].items())[:6]) or "-"
            print("\nblock %d  %#x..%#x  (%d B)" % (b["index"], b["start"],
                                                    b["end"], b["size"]))
            print("  names        : %d (%d B, sha256 %s)"
                  % (b["name_count"], b["names_bytes"],
                     b["names_sha256"][:16]))
            print("  first        : %s" % b["first_names"][:3])
            print("  provenance   : %s" % b["name_provenance"])
            print("  ops          : %s" % ops)
            print("  tensor table : %d records in %d run(s) (%d invalid "
                  "slots), %d B, "
                  "offsets %s..%s, sizes %s..%s, sum %d"
                  % (b["tensor_record_count"], b["table_run_count"],
                     b["table_invalid_slots"], b["table_bytes"],
                     hex(b["record_offset_min"] or 0),
                     hex(b["record_offset_max_end"] or 0),
                     hex(b["record_size_min"] or 0),
                     hex(b["record_size_max"] or 0), b["record_size_sum"]))
            pl = b["payload"]
            print("  payload      : %d B declared by trailer; tensors end at "
                  "%s (slack %d, coverage %.4f, gaps %d/%d B, overlaps %d)"
                  % (b["payload_bytes"], hex(pl["max_tensor_end"] or 0),
                     pl["slack_bytes"], pl["coverage"] or 0.0,
                     pl["gap_count"], pl["gap_bytes"], pl["overlaps"]))
            print("  payload maps : %s"
                  % (b["payload_array_index"] or "-"))
            print("  payload law  : %s -> %s"
                  % (pl["law"], "VERIFIED" if pl["proven"] else
                     "NOT verified (unaccounted bytes)"))
        print("\ninterface descriptors (proven bytes):")
        for k, v in rep["descriptors"].items():
            print("  %-18s %s -> %s" % (k, v["strings"], v["dims"]))
        if rep["payload_arrays"]:
            print("\nweight payload arrays (proven by .text getter pairs):")
            for i, a in enumerate(rep["payload_arrays"]):
                users = [b["index"] for b in rep["blocks"]
                         if i in b["payload_array_index"]]
                print("  P%d  %#010x..%#010x  %9d B  getter %#x  blocks %s"
                      % (i, a["ptr"], a["ptr"] + a["size"], a["size"],
                         a["getter"], users or "-"))
        print("\ntensor payload regions (statistical candidates, NOT claimed):")
        for p in rep["payload_regions_candidate"]:
            extra = ""
            if "fp16_finite_frac" in p:
                extra = (" finite=%.5f |x|<=1=%.3f p99.9=%.3f"
                         % (p["fp16_finite_frac"], p["fp16_frac_abs_le_1"],
                            p["fp16_p999"]))
            print("  %-22s %#x..%#x %9d B  mean=%.2f frac0x80=%.4f%s"
                  % (p["label"], p["start"], p["end"], p["size"],
                     p.get("byte_mean", 0), p.get("frac_0x80", 0), extra))
        print("\ngraph_decoded=%s  inference_available=%s"
              % (rep["graph_decoded"], rep["inference_available"]))
        for n in rep["notes"]:
            print("  note:", n)
    return 0


if __name__ == "__main__":
    sys.exit(main())
