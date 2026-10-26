#!/usr/bin/env python3
"""carve_models.py - find embedded AI model blobs in an ELF or raw file.

Detectors:
  tflite      TFL3 flatbuffer identifier (validates the flatbuffer root table)
  zip         PK\\x03\\x04 archives (Snapdragon .dlc, TFLite-with-metadata, ...)
  caffe       Caffe NetParameter protobuf heuristic plus Caffe-related strings
  strings     model-name strings ("*_model", *.tflite, *.dlc, *.caffemodel, ...)
  flatbuffer  identifier-less flatbuffer heuristic (opt-in)

Nothing is written unless --extract DIR is given.  Reported findings include
ready-to-run dd commands (GNU coreutils byte-offset flags).

Examples:
  python3 carve_models.py lib.so
  python3 carve_models.py lib.so --types tflite,zip,caffe --json out.json
  python3 carve_models.py model.tflite --extract /tmp/carved
  python3 carve_models.py blob.bin --scan-flatbuffers
"""

from __future__ import annotations

import argparse
import hashlib
import json
import mmap
import os
import re
import struct
import sys
from typing import Dict, Iterator, List, Optional, Tuple

TFLITE_MAGIC = b"TFL3"
ZIP_LOCAL = b"PK\x03\x04"
ZIP_EOCD = b"PK\x05\x06"
ZIP64_EOCD = b"PK\x06\x06"
ZIP64_LOCATOR = b"PK\x06\x07"

CAFFE_LAYER_TYPES = {
    "Convolution", "Pooling", "InnerProduct", "ReLU", "Softmax", "Data",
    "Dropout", "BatchNorm", "Scale", "Eltwise", "Concat", "Slice", "Flatten",
    "Deconvolution", "PReLU", "Sigmoid", "TanH", "LRN", "Power", "EuclideanLoss",
    "Accuracy", "Input", "Split", "Reshape", "Permute", "PriorBox", "Detection",
}
CAFFE_STRING_RE = re.compile(rb"(?i:caffe)|\.prototxt|\.caffemodel")
MODEL_NAME_RE = re.compile(
    rb"(?i)(?<![A-Za-z0-9_.\-])(?:[A-Za-z][A-Za-z0-9_.\-]{2,})"
    rb"(?:_model|\.model|\.tflite|\.dlc|\.caffemodel|\.prototxt|\.onnx|\.mnn|\.param)\b")
PRINTABLE_RUN = re.compile(rb"[\x20-\x7e]{4,}")

EXTENSIONS = {
    "tflite": ".tflite",
    "zip_dlc": ".dlc",
    "zip": ".zip",
    "caffe_proto": ".caffemodel",
    "caffe_string": ".txt",
    "model_string": ".txt",
    "flatbuffer": ".fbs.bin",
}

MAX_CAFFE_SEEDS = 200
MAX_CAFFE_BACKTRACK = 16 * 1024
MAX_FINDINGS_DEFAULT = 500


# --------------------------------------------------------------------------
# Protobuf helpers (Caffe NetParameter heuristic)
# --------------------------------------------------------------------------

def _varint(buf, pos: int, limit: int) -> Tuple[Optional[int], int]:
    value = 0
    shift = 0
    while pos < limit and shift < 35:
        byte = buf[pos]
        pos += 1
        value |= (byte & 0x7F) << shift
        if not byte & 0x80:
            return value, pos
        shift += 7
    return None, pos


def _proto_fields(buf, start: int, limit: int,
                  max_fields: int = 4096) -> Iterator[Tuple[int, int, int, int, int]]:
    """Yield (field_number, wire_type, payload_start, payload_end, next_pos)."""
    pos = start
    for _ in range(max_fields):
        if pos >= limit:
            return
        key, pos = _varint(buf, pos, limit)
        if key is None or key == 0:
            return
        field, wire = key >> 3, key & 7
        if field == 0:
            return
        if wire == 0:
            _v, pos = _varint(buf, pos, limit)
            if _v is None:
                return
            yield field, wire, pos, pos, pos
        elif wire == 1:
            if pos + 8 > limit:
                return
            yield field, wire, pos, pos + 8, pos + 8
            pos += 8
        elif wire == 2:
            length, pos = _varint(buf, pos, limit)
            if length is None or pos + length > limit:
                return
            yield field, wire, pos, pos + length, pos + length
            pos += length
        elif wire == 5:
            if pos + 4 > limit:
                return
            yield field, wire, pos, pos + 4, pos + 4
            pos += 4
        else:
            return


def _printable(buf, start: int, end: int) -> Optional[str]:
    if end <= start or end - start > 256:
        return None
    chunk = bytes(buf[start:end])
    if any(b < 0x20 or b > 0x7E for b in chunk):
        return None
    try:
        return chunk.decode("ascii")
    except UnicodeDecodeError:
        return None


def _try_caffe_net(buf, start: int, limit: int) -> Optional[Tuple[int, int]]:
    """Parse a NetParameter at *start*.

    Returns (number_of_known_layers, end_offset) or None.  Requires a printable
    net name (field 1) and at least three recognised layer messages (field 2
    with nested name/type).
    """
    if start < 0 or start + 2 >= limit or buf[start] != 0x0A:
        return None
    fields = _proto_fields(buf, start, limit)
    name_seen = False
    layers = 0
    last_end = start
    for field, wire, pstart, pend, nxt in fields:
        last_end = nxt
        if wire != 2:
            continue
        if field == 1 and not name_seen:
            text = _printable(buf, pstart, pend)
            if text is None or not text:
                return None
            name_seen = True
        elif field == 2 and pend > pstart:
            nested_name = None
            nested_type = None
            for nf, nw, ns, ne, _nn in _proto_fields(buf, pstart, pend, 64):
                if nw != 2:
                    continue
                if nf == 1:
                    nested_name = _printable(buf, ns, ne)
                elif nf == 2:
                    nested_type = _printable(buf, ns, ne)
                if nested_type is not None:
                    break
            if nested_type in CAFFE_LAYER_TYPES:
                layers += 1
        if layers >= 3 and last_end - start > 64:
            return layers, last_end
    return None


def find_caffe_protobuf(buf, limit: int) -> List[Tuple[int, int, int]]:
    """Return [(start, end, layer_count)] for NetParameter-looking blobs."""
    seeds = []
    for match in re.finditer(rb"Convolution", buf):
        seeds.append(match.start())
        if len(seeds) >= MAX_CAFFE_SEEDS:
            break
    results: List[Tuple[int, int, int]] = []
    seen = set()
    for seed in seeds:
        lo = max(0, seed - MAX_CAFFE_BACKTRACK)
        # Candidate net starts are field-1 tags followed by a short name length.
        for pos in range(seed - 1, lo - 1, -1):
            if buf[pos] != 0x0A:
                continue
            length, name_pos = _varint(buf, pos + 1, seed + 1)
            if length is None or not (1 <= length <= 128) or name_pos >= seed:
                continue
            if _printable(buf, name_pos, name_pos + length) is None:
                continue
            parsed = _try_caffe_net(buf, pos, limit)
            if parsed is None:
                continue
            end, layers = parsed[1], parsed[0]
            if end <= seed:
                continue
            key = (pos, end)
            if key in seen:
                break
            seen.add(key)
            results.append((pos, end, layers))
            break
        if len(results) >= 100:
            break
    return results


# --------------------------------------------------------------------------
# Flatbuffer validation
# --------------------------------------------------------------------------

def _flatbuffer_info(buf, start: int, limit: int) -> Optional[Dict[str, int]]:
    if start + 8 > limit:
        return None
    root_off = struct.unpack_from("<I", buf, start)[0]
    if root_off < 4 or root_off > limit - start:
        return None
    table = start + root_off
    if table + 4 > limit:
        return None
    soff = struct.unpack_from("<i", buf, table)[0]
    vtable = table - soff
    if not (start <= vtable < table) or vtable + 4 > limit:
        return None
    vtable_size, object_size = struct.unpack_from("<HH", buf, vtable)
    if vtable_size < 4 or vtable_size % 2 or vtable_size > 1024:
        return None
    if object_size < 4 or vtable + vtable_size > limit:
        return None
    return {
        "root_offset": root_off,
        "table_offset": root_off,
        "vtable_offset": vtable - start,
        "vtable_size": vtable_size,
        "object_size": object_size,
    }


# --------------------------------------------------------------------------
# ZIP
# --------------------------------------------------------------------------

def _zip_extent(buf, start: int, limit: int) -> Optional[Tuple[int, int]]:
    """Validate a local ZIP header and return (end_offset, entry_hint)."""
    if start + 30 > limit or buf[start:start + 4] != ZIP_LOCAL:
        return None
    try:
        (version, flags, method, _t, _d, _crc, _cs, _us,
         name_len, extra_len) = struct.unpack_from("<HHHHHIIIHH", buf, start + 4)
    except struct.error:
        return None
    name_start = start + 30
    name_end = name_start + name_len
    if name_end + extra_len > limit:
        return None
    name = bytes(buf[name_start:name_end])
    if not name or any(b < 0x20 or b > 0x7E for b in name):
        return None
    if version > 100 or name_len > 1024 or extra_len > 65535:
        return None

    # Find the matching EOCD: its central-directory offset is relative to the
    # beginning of this archive and must point at a central header.
    search = start
    while True:
        eocd = buf.find(ZIP_EOCD, search, limit)
        if eocd < 0:
            return None
        if eocd + 22 <= limit:
            cd_size, cd_off, comment_len = struct.unpack_from("<IIH", buf, eocd + 12)
            central_end = eocd
            if cd_off == 0xFFFFFFFF or cd_size == 0xFFFFFFFF:
                # ZIP64: the locator sits immediately before the EOCD record.
                loc = eocd - 20
                if loc >= 0 and buf[loc:loc + 4] == ZIP64_LOCATOR:
                    z64 = struct.unpack_from("<Q", buf, loc + 8)[0]
                    # The locator stores an absolute offset for a standalone
                    # archive; for an embedded archive it is relative to the
                    # archive start.  Accept either interpretation.
                    for zcand in (z64, start + z64):
                        if zcand < start or zcand + 56 > limit:
                            continue
                        if buf[zcand:zcand + 4] != ZIP64_EOCD:
                            continue
                        cd_size = struct.unpack_from("<Q", buf, zcand + 40)[0]
                        cd_off = struct.unpack_from("<Q", buf, zcand + 48)[0]
                        central_end = zcand - start
                        break
            end = eocd + 22 + comment_len
            if end <= limit and cd_off < limit - start and \
                    buf[start + cd_off:start + cd_off + 4] == b"PK\x01\x02" and \
                    cd_off + cd_size == central_end:
                return end, 0
        search = eocd + 4


# --------------------------------------------------------------------------
# Scan
# --------------------------------------------------------------------------

def _dd(ifile: str, offset: int, size: Optional[int],
        out: Optional[str] = None) -> str:
    dst = out or "OUT"
    if size is None:
        return ("dd if='%s' of='%s' bs=1M iflag=skip_bytes skip=%d "
                "status=none" % (ifile, dst, offset))
    return ("dd if='%s' of='%s' bs=1M iflag=skip_bytes,count_bytes skip=%d "
            "count=%d status=none" % (ifile, dst, offset, size))


def _extract_slice(path: str, buf, offset: int, size: int, dest: str,
                   kind: str) -> str:
    os.makedirs(dest, exist_ok=True)
    name = "%s_%08x%s" % (kind, offset, EXTENSIONS.get(kind, ".bin"))
    target = os.path.join(dest, name)
    with open(target, "wb") as fh:
        fh.write(bytes(buf[offset:offset + size]))
    return target


def scan(path: str, types: set, scan_flatbuffers: bool = False,
         max_findings: int = MAX_FINDINGS_DEFAULT) -> Dict[str, object]:
    size = os.path.getsize(path)
    findings: List[Dict[str, object]] = []
    counts: Dict[str, int] = {}
    notes: List[str] = []
    hash_sha256 = hashlib.sha256()

    with open(path, "rb") as fh:
        if size == 0:
            return {"file": os.path.abspath(path), "size": 0, "findings": [],
                    "counts": {}, "sha256": hash_sha256.hexdigest()}
        buf = mmap.mmap(fh.fileno(), 0, access=mmap.ACCESS_READ)
        try:
            for chunk_start in range(0, size, 4 * 1024 * 1024):
                hash_sha256.update(buf[chunk_start:chunk_start + 4 * 1024 * 1024])

            def add(kind: str, offset: int, blob_size: Optional[int],
                    confidence: str, note: str, magic_offset: Optional[int] = None,
                    extra: Optional[Dict[str, object]] = None) -> None:
                if len(findings) >= max_findings:
                    return
                counts[kind] = counts.get(kind, 0) + 1
                item: Dict[str, object] = {
                    "kind": kind,
                    "offset": offset,
                    "size": blob_size,
                    "end": offset + blob_size if blob_size is not None else None,
                    "confidence": confidence,
                    "note": note,
                    "dd": _dd(os.path.abspath(path), offset, blob_size),
                }
                if magic_offset is not None:
                    item["magic_offset"] = magic_offset
                if extra:
                    item.update(extra)
                findings.append(item)
                return item

            def inside_model(offset: int) -> bool:
                for f in findings:
                    if f["kind"] in ("tflite", "zip_dlc") and f["size"]:
                        if f["offset"] <= offset < f["offset"] + f["size"]:
                            return True
                return False

            # ---- TFL3 (TensorFlow Lite flatbuffer) -----------------------
            if "tflite" in types:
                pos = 0
                while len(findings) < max_findings:
                    hit = buf.find(TFLITE_MAGIC, pos, size)
                    if hit < 0:
                        break
                    pos = hit + 4
                    start = hit - 4
                    if start < 0:
                        add("tflite", hit, 4, "low",
                            "TFL3 magic without a preceding root offset")
                        continue
                    info = _flatbuffer_info(buf, start, size)
                    model_size = size - start if start == 0 else None
                    note = "TFLite flatbuffer"
                    if info is None:
                        note += " (root table did not validate)"
                        conf = "low"
                        info = {}
                    else:
                        conf = "high"
                        note += " root=+0x%x vtable=+0x%x" % (
                            info["root_offset"], info["vtable_offset"])
                    if model_size is None:
                        note += "; size unknown (flatbuffer has no length prefix)"
                    add("tflite", start, model_size, conf, note,
                        magic_offset=hit, extra=info)

            # ---- ZIP / DLC ----------------------------------------------
            if "zip" in types:
                pos = 0
                last_end = -1
                while len(findings) < max_findings:
                    hit = buf.find(ZIP_LOCAL, pos, size)
                    if hit < 0:
                        break
                    pos = hit + 4
                    if hit < last_end:       # nested inside a previous archive
                        continue
                    extent = _zip_extent(buf, hit, size)
                    if extent is None:
                        continue
                    end, _hint = extent
                    last_end = end
                    add("zip_dlc", hit, end - hit, "high",
                        "ZIP archive with valid central directory "
                        "(Snapdragon DLC or bundled model)")

            # ---- Caffe protobuf + strings -------------------------------
            if "caffe" in types:
                for start, end, layers in find_caffe_protobuf(buf, size):
                    add("caffe_proto", start, end - start, "medium",
                        "Caffe NetParameter heuristic: %d recognised layers" % layers)
                seen_strings = {}
                for match in CAFFE_STRING_RE.finditer(buf):
                    off = match.start()
                    if inside_model(off):
                        continue
                    text = bytes(match.group())
                    if text in seen_strings:
                        seen_strings[text]["occurrences"] += 1
                        continue
                    if len(seen_strings) >= max_findings:
                        break
                    ctx_start = max(0, off - 24)
                    ctx = bytes(buf[ctx_start:min(size, off + 80)])
                    preview = "".join(chr(b) if 32 <= b < 127 else "."
                                      for b in ctx)
                    item = add("caffe_string", off, match.end() - off, "string",
                               "Caffe-related string %r (context: %s)" %
                               (text.decode("ascii", "replace"), preview),
                               extra={"occurrences": 1})
                    if item is not None:
                        seen_strings[text] = item

            # ---- model-name strings -------------------------------------
            if "strings" in types:
                seen = {}
                for match in MODEL_NAME_RE.finditer(buf):
                    off = match.start()
                    if inside_model(off):
                        continue
                    text = bytes(match.group())
                    if text in seen:
                        seen[text]["occurrences"] += 1
                        continue
                    if len(seen) >= max_findings:
                        break
                    item = add("model_string", off, len(text), "string",
                               "model-name string %r" %
                               text.decode("ascii", "replace"),
                               extra={"occurrences": 1})
                    if item is not None:
                        seen[text] = item

            # ---- identifier-less flatbuffers ----------------------------
            if "flatbuffer" in types or scan_flatbuffers:
                if size > 32 * 1024 * 1024:
                    notes.append("flatbuffer scan skipped: file >32 MiB "
                                 "(use --types flatbuffer on a smaller slice)")
                else:
                    for off in range(0, size - 8, 4):
                        info = _flatbuffer_info(buf, off, size)
                        if info is None:
                            continue
                        add("flatbuffer", off, None, "low",
                            "flatbuffer-like root/vtable (no identifier)",
                            extra=info)
                        if len(findings) >= max_findings:
                            break
        finally:
            buf.close()
    findings.sort(key=lambda f: (f["offset"], f["kind"]))
    return {
        "file": os.path.abspath(path),
        "size": size,
        "sha256": hash_sha256.hexdigest(),
        "findings": findings,
        "counts": counts,
        "notes": notes,
    }


# --------------------------------------------------------------------------
# CLI
# --------------------------------------------------------------------------

def _render(report: Dict[str, object], max_rows: int = 80) -> str:
    out = ["=" * 78,
           "carve_models.py: %s (%d bytes)" % (report["file"], report["size"]),
           "=" * 78]
    findings = report["findings"]
    if not findings:
        out.append("no model candidates found")
        return "\n".join(out)
    out.append("%-14s %-12s %-12s %-9s %s" %
               ("kind", "offset", "size", "confidence", "note"))
    for item in findings[:max_rows]:
        size = "0x%x" % item["size"] if item["size"] is not None else "unknown"
        extra = ""
        if item.get("occurrences", 1) > 1:
            extra = " [x%d]" % item["occurrences"]
        out.append("%-14s 0x%08x  %-12s %-9s %s%s" %
                   (item["kind"], item["offset"], size, item["confidence"],
                    item["note"], extra))
    if len(findings) > max_rows:
        out.append("... %d more (see --json)" % (len(findings) - max_rows))
    out.append("")
    out.append("counts: " + ", ".join("%s=%d" % kv for kv in
                                      sorted(report["counts"].items())))
    for note in report.get("notes") or []:
        out.append("note: " + note)
    out.append("")
    out.append("dd commands for extractable candidates "
               "(GNU coreutils; substitute OUT):")
    shown = 0
    for item in findings[:max_rows]:
        if item["kind"] in ("caffe_string", "model_string"):
            continue
        out.append("  " + item["dd"])
        shown += 1
    if shown == 0:
        out.append("  (none)")
    return "\n".join(out)


def main(argv: Optional[List[str]] = None) -> int:
    ap = argparse.ArgumentParser(
        description="Scan an ELF/raw blob for embedded TFLite, ZIP/DLC, "
                    "Caffe and flatbuffer model data.")
    ap.add_argument("blob", help="file to scan")
    ap.add_argument("--types", default="tflite,zip,caffe,strings",
                    help="comma list of tflite,zip,caffe,strings,flatbuffer "
                         "(default: tflite,zip,caffe,strings)")
    ap.add_argument("--scan-flatbuffers", action="store_true",
                    help="enable the identifier-less flatbuffer heuristic "
                         "(slow; files <=32 MiB)")
    ap.add_argument("--extract", metavar="DIR",
                    help="write carved candidates to DIR (off by default)")
    ap.add_argument("--json", metavar="PATH",
                    help="write JSON report ('-' for stdout)")
    ap.add_argument("--max-findings", type=int, default=MAX_FINDINGS_DEFAULT,
                    help="cap number of findings (default %d)" % MAX_FINDINGS_DEFAULT)
    ap.add_argument("--max-rows", type=int, default=80,
                    help="rows in the human summary (default 80)")
    args = ap.parse_args(argv)

    if not os.path.isfile(args.blob):
        sys.stderr.write("carve_models.py: no such file: %s\n" % args.blob)
        return 2
    types = {t.strip() for t in args.types.split(",") if t.strip()}
    valid = {"tflite", "zip", "caffe", "strings", "flatbuffer"}
    unknown = types - valid
    if unknown:
        sys.stderr.write("carve_models.py: unknown --types value(s): %s\n" %
                         ", ".join(sorted(unknown)))
        return 2

    try:
        report = scan(args.blob, types,
                      scan_flatbuffers=args.scan_flatbuffers,
                      max_findings=args.max_findings)
    except Exception as exc:
        sys.stderr.write("carve_models.py: scan failed: %s\n" % exc)
        return 2

    if args.extract:
        # Extract with a single mmap (avoids holding the whole blob in RAM).
        with open(args.blob, "rb") as fh:
            buf = mmap.mmap(fh.fileno(), 0, access=mmap.ACCESS_READ)
            try:
                for item in report["findings"]:
                    if item["size"] is None or item["kind"] in (
                            "caffe_string", "model_string"):
                        continue
                    target = _extract_slice(args.blob, buf, item["offset"],
                                            item["size"], args.extract,
                                            item["kind"])
                    item["extracted"] = target
            finally:
                buf.close()

    if args.json == "-":
        print(json.dumps(report, indent=2))
    else:
        print(_render(report, args.max_rows))
        if args.json:
            with open(args.json, "w") as fh:
                json.dump(report, fh, indent=2)
                fh.write("\n")
            print("\nJSON written to %s" % args.json)
    return 0


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except BrokenPipeError:      # e.g. `tool ... | head`
        os._exit(0)
