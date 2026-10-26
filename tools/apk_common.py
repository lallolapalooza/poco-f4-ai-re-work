"""Shared helpers for APK/DEX inventory and scanning.

The AI-artifact keyword sets below are copied verbatim from the parent
analysis (the scan that classified the 515 APKs on the mounted image), so
inventory results stay comparable with reports/*/brief.json.

Everything here is dependency-light: zipfile + struct only.  The DEX parser
reads one dex member at a time and never keeps class/method lists for a whole
APK in memory.
"""

from __future__ import annotations

import io
import os
import re
import struct
import sys
import zipfile
from typing import Iterable, Iterator, NamedTuple

# ---------------------------------------------------------------------------
# Parent-analysis keyword sets (do not "improve" without updating README)
# ---------------------------------------------------------------------------

# Model file names, exactly as used by the parent brief.json scan.
MODEL_NAME_RE = re.compile(
    r"\.(tflite|onnx|mnn|npubin|dlc|caffemodel|uim|bundle|model|pt|pth|smfb)$"
    r"|(^|/)(model|classifier|segmenter|ranker)(/|$)",
    re.IGNORECASE,
)

# Wider model-extension set from the parent's first image-wide pass.  Used for
# candidate selection and TFL3 probing (not every entry is a real model).
MODEL_EXT_RE = re.compile(
    r"\.(tflite|lite|onnx|mnn|npubin|dlc|caffemodel|pt|pth|pb|ai_model|bin|"
    r"xmodel|kmodel|rknn|mlmodel|gguf|ort|uim|bundle|model|smfb|h5|paddle|"
    r"ncnn|param|tflite\.jpg)$",
    re.IGNORECASE,
)

# Native library names flagged by the parent analysis.
AI_LIB_RE = re.compile(
    r"lib.*(tensorflow|tflite|litert|nnapi|neural|mllite|onnx|pytorch|caffe|"
    r"snpe|qnn|mace|opencv|megvii|face|arcsoft|mibrain|aivs|miocr|inpaint|"
    r"aiedit|fuai|scanner|vision|segment|matting|whisper|dnn|ocr|beaut|ai|ml)"
    r".*\.so$",
    re.IGNORECASE,
)

# Asset/config names flagged by the parent analysis (assets/, res/raw/, lib/).
ASSET_KEYWORD_RE = re.compile(
    r"(tflite|\.mnn|\.onnx|\.dlc|\.npubin|caffemodel|\.pt$|mibrain|mllite|"
    r"tensorflow|onnx|pytorch|snpe|qnn|neural|mediapipe|mlkit|vision|face|"
    r"ocr|inpaint|aiedit|fuai|scanner|megvii|arcsoft|aivs|aivoice|ByteAI|"
    r"bvtAI|mobais|hand|gesture|aiauto|model)",
    re.IGNORECASE,
)

# DEX marker byte strings, exactly as used in reports/*/brief.json.
DEX_MARKERS: tuple[bytes, ...] = (
    b"Lorg/tensorflow/lite",
    b"Lcom/google/mlkit",
    b"Lcom/google/android/gms/vision",
    b"Lcom/xiaomi/aicr",
    b"Lcom/xiaomi/mibrain",
    b"mibrain",
    b"tflite",
    b"libtensorflowlite",
    b"com/qualcomm/snpe",
    b"com/qualcomm/qti/qnn",
    b"Lcom/arcsoft",
    b"Lcom/megvii",
    b"ByteAI",
    b"visionengine",
    b"FaceMagic",
    b"mediapipe",
)
DEX_MARKER_NAMES: tuple[str, ...] = tuple(m.decode("utf-8", "replace") for m in DEX_MARKERS)

# Parent's string / feature keyword regex.
STRING_KEYWORD_RE = re.compile(
    r"(tflite|mnn|mace|snpe|qnn|onnx|tensorflow|neural|nnapi|model|ocr|detect|"
    r"recogn|segment|matting|landmark|face|voice|asr|tts|noise|subtitle|translat|"
    r"beauty|scene|inpaint|blur|person|cat|dog|car|doc|scanner|barcode|wave2|"
    r"conformer|transducer|zipformer|eres2net|ctc|vad|kws|keyword|speech|"
    r"whisper|bpe|token)",
    re.IGNORECASE,
)

# Secondary parent regex used when probing dex strings for feature code.
AI_CODE_RE = re.compile(
    r"(mlkit|tensorflow|tflite|ocr|face|beaut|translat|langid|classif|spam|"
    r"recogni|detect)",
    re.IGNORECASE,
)

# Default class/method search regex: the union of the parent's class markers
# and feature keywords.  It deliberately includes "beauty" and "aiauto" so the
# acceptance classes are found.
DEFAULT_CLASS_REGEX = (
    r"tflite|tensorflow|litert|nnapi|neural|mllite|onnx|pytorch|caffe|snpe|qnn|"
    r"mace|opencv|megvii|arcsoft|mibrain|aivs|miocr|miai|inpaint|aiedit|fuai|"
    r"scanner|visionengine|facemagic|byteai|bvtai|mobais|mediapipe|mlkit|"
    r"segment|matting|langid|textclassif|smartreply|whisper|dnnvad|qvarnn|"
    r"aiauto|beauty|beaut|ultraclear|ocr|vad|asr|tts|landmark|inpaint|objectdetect|"
    r"imagedetect|pose|barcode|speech|subtitle|translat"
)

# Config-ish extensions for inventory grouping.
CONFIG_EXTS = frozenset(
    ".xml .json .properties .prop .cfg .conf .yaml .yml .ini .txt .toml .plist "
    ".csv .tsv .pb.txt".split()
)

# ---------------------------------------------------------------------------
# Small utilities
# ---------------------------------------------------------------------------


def dex_sort_key(name: str) -> int:
    m = re.search(r"(\d+)\.dex$", name)
    return int(m.group(1)) if m else 1


def dot_name(descriptor: str) -> str:
    """Lcom/foo/Bar; -> com.foo.Bar (arrays keep their descriptor form)."""
    if descriptor.startswith("L") and descriptor.endswith(";"):
        return descriptor[1:-1].replace("/", ".")
    return descriptor.replace("/", ".")


def is_tflite_magic(head: bytes) -> bool:
    """TFLite flatbuffers carry the TFL3 identifier at byte offset 4."""
    return head[:4] == b"\x1c\x00\x00\x00" and head[4:8] == b"TFL3"


def magic_label(head: bytes) -> str:
    if is_tflite_magic(head):
        return "TFLite"
    if head[:4] == b"PK\x03\x04":
        return "zip"
    if head[:2] == b"\x1f\x8b":
        return "gzip"
    return head[:8].hex()


def sha256_file(path: str, chunk: int = 1 << 20) -> str:
    import hashlib

    h = hashlib.sha256()
    with open(path, "rb") as f:
        while True:
            b = f.read(chunk)
            if not b:
                break
            h.update(b)
    return h.hexdigest()


def open_zip(apk: str) -> zipfile.ZipFile:
    try:
        return zipfile.ZipFile(apk)
    except (OSError, zipfile.BadZipFile) as exc:
        raise SystemExit(f"error: cannot open APK {apk}: {exc}") from exc


def iter_zip_members(z: zipfile.ZipFile) -> Iterator[zipfile.ZipInfo]:
    for info in z.infolist():
        if not info.is_dir():
            yield info


def count_markers_stream(
    fh: io.BufferedIOBase, markers: Iterable[bytes], chunk: int = 1 << 20
) -> dict[str, int]:
    """Count non-overlapping occurrences while streaming, bounded memory."""
    markers = tuple(markers)
    counts = {m.decode("utf-8", "replace"): 0 for m in markers}
    if not markers:
        return counts
    overlap = max(len(m) for m in markers) - 1
    tail = b""
    while True:
        buf = fh.read(chunk)
        if not buf:
            break
        data = tail + buf
        for m in markers:
            counts[m.decode("utf-8", "replace")] += data.count(m)
        tail = data[-overlap:] if overlap else b""
    return counts


# ---------------------------------------------------------------------------
# Minimal DEX parser
# ---------------------------------------------------------------------------

def _uleb128(buf: bytes, off: int) -> tuple[int, int]:
    result = 0
    shift = 0
    while True:
        b = buf[off]
        off += 1
        result |= (b & 0x7F) << shift
        if not b & 0x80:
            return result, off
        shift += 7
        if shift > 35:
            raise ValueError("bad uleb128")


def _mutf8_decode(raw: bytes) -> str:
    # DEX stores modified UTF-8: 0xC0 0x80 is NUL and supplementary characters
    # are encoded as two 3-byte surrogates.  Regular UTF-8 covers virtually
    # all identifiers; fall back to replacement for exotic strings.
    if b"\xc0\x80" not in raw and b"\xed" not in raw:
        return raw.decode("utf-8", "replace")
    raw = raw.replace(b"\xc0\x80", b"\x00")
    try:
        return raw.decode("utf-8")
    except UnicodeDecodeError:
        return raw.decode("utf-8", "replace")


class DexStrings:
    """All strings of one dex, decoded once (a few MB for typical dex files)."""

    __slots__ = ("strings",)

    def __init__(self, strings: list[str]):
        self.strings = strings


def parse_dex(buf: bytes) -> DexStrings:
    if len(buf) < 112 or buf[:4] != b"dex\n":
        raise ValueError("not a dex file")
    (string_ids_size, string_ids_off) = struct.unpack_from("<II", buf, 56)
    if string_ids_size == 0:
        return DexStrings([])
    end = string_ids_off + 4 * string_ids_size
    if end > len(buf):
        raise ValueError("string_ids out of range")
    strings: list[str] = []
    for i in range(string_ids_size):
        (data_off,) = struct.unpack_from("<I", buf, string_ids_off + 4 * i)
        if data_off >= len(buf):
            strings.append("")
            continue
        try:
            _n, p = _uleb128(buf, data_off)
            nul = buf.find(b"\x00", p)
            if nul < 0:
                nul = len(buf)
            strings.append(_mutf8_decode(buf[p:nul]))
        except (IndexError, ValueError):
            strings.append("")
    return DexStrings(strings)


def _type_name(buf: bytes, type_ids_off: int, idx: int, strings: list[str]) -> str:
    (sid,) = struct.unpack_from("<I", buf, type_ids_off + 4 * idx)
    return strings[sid] if sid < len(strings) else ""


def _proto_descriptor(
    buf: bytes, proto_ids_off: int, proto_idx: int, type_ids_off: int, strings: list[str]
) -> str:
    shorty_idx, return_type_idx, params_off = struct.unpack_from("<III", buf, proto_ids_off + 12 * proto_idx)
    ret = _type_name(buf, type_ids_off, return_type_idx, strings)
    params: list[str] = []
    if params_off:
        (size,) = struct.unpack_from("<I", buf, params_off)
        for i in range(size):
            (tidx,) = struct.unpack_from("<H", buf, params_off + 4 + 2 * i)
            params.append(_type_name(buf, type_ids_off, tidx, strings))
    return "(" + "".join(params) + ")" + ret


class DexClass(NamedTuple):
    name: str          # dotted
    descriptor: str    # Lcom/foo/Bar;
    access_flags: int
    method_count: int
    dex_name: str


def iter_dex_classes(buf: bytes, dex_name: str, strings: list[str]) -> Iterator[tuple[DexClass, list[tuple[str, str, int]]]]:
    """Yield (class, methods[(name, descriptor, access_flags)]) per class_def.

    Methods are the methods *defined* by the class (class_data_item), not every
    method referenced in method_ids.
    """
    (_type_ids_size, type_ids_off) = struct.unpack_from("<II", buf, 64)
    (proto_ids_size, proto_ids_off) = struct.unpack_from("<II", buf, 72)
    (method_ids_size, method_ids_off) = struct.unpack_from("<II", buf, 88)
    (class_defs_size, class_defs_off) = struct.unpack_from("<II", buf, 96)

    if class_defs_off + 32 * class_defs_size > len(buf):
        raise ValueError("class_defs out of range")

    for ci in range(class_defs_size):
        base = class_defs_off + 32 * ci
        class_idx, access_flags, _super, _ifaces, _src, _ann, class_data_off, _static = struct.unpack_from(
            "<IIIIIIII", buf, base
        )
        descriptor = _type_name(buf, type_ids_off, class_idx, strings)
        if not descriptor:
            continue
        name = dot_name(descriptor)
        resolved: list[tuple[str, str, int]] = []
        if class_data_off:
            try:
                static_fields, p = _uleb128(buf, class_data_off)
                instance_fields, p = _uleb128(buf, p)
                direct_methods, p = _uleb128(buf, p)
                virtual_methods, p = _uleb128(buf, p)
                for _ in range(static_fields + instance_fields):
                    _idx_diff, p = _uleb128(buf, p)
                    _acc, p = _uleb128(buf, p)
                for count in (direct_methods, virtual_methods):
                    prev = 0
                    for _ in range(count):
                        idx_diff, p = _uleb128(buf, p)
                        acc, p = _uleb128(buf, p)
                        _code_off, p = _uleb128(buf, p)
                        prev += idx_diff
                        if prev >= method_ids_size:
                            continue
                        _m_class, m_proto, m_name = struct.unpack_from("<HHI", buf, method_ids_off + 8 * prev)
                        if m_name >= len(strings) or m_proto >= proto_ids_size:
                            continue
                        desc = _proto_descriptor(buf, proto_ids_off, m_proto, type_ids_off, strings)
                        resolved.append((strings[m_name], desc, acc))
            except (IndexError, ValueError):
                pass
        yield DexClass(name, descriptor, access_flags, len(resolved), dex_name), resolved


def iter_dex_type_names(buf: bytes, strings: list[str]) -> Iterator[tuple[int, str]]:
    """Yield (type_idx, dotted_name) for every entry in type_ids."""
    (type_ids_size, type_ids_off) = struct.unpack_from("<II", buf, 64)
    for idx in range(type_ids_size):
        (sid,) = struct.unpack_from("<I", buf, type_ids_off + 4 * idx)
        if sid >= len(strings):
            continue
        yield idx, dot_name(strings[sid])


def iter_dex_method_refs(buf: bytes, strings: list[str]) -> Iterator[tuple[str, str, int]]:
    """Yield (class_dotted, method_name, proto_idx) for every method_ids entry."""
    (type_ids_off,) = struct.unpack_from("<I", buf, 68)
    (method_ids_size, method_ids_off) = struct.unpack_from("<II", buf, 88)
    for idx in range(method_ids_size):
        class_idx, proto_idx, name_idx = struct.unpack_from("<HHI", buf, method_ids_off + 8 * idx)
        if name_idx >= len(strings):
            continue
        (sid,) = struct.unpack_from("<I", buf, type_ids_off + 4 * class_idx)
        if sid >= len(strings):
            continue
        yield dot_name(strings[sid]), strings[name_idx], proto_idx


def dex_proto_descriptor(buf: bytes, strings: list[str], proto_idx: int) -> str:
    (type_ids_off,) = struct.unpack_from("<I", buf, 68)
    (proto_ids_off,) = struct.unpack_from("<I", buf, 76)
    return _proto_descriptor(buf, proto_ids_off, proto_idx, type_ids_off, strings)


def iter_apk_dex_names(z: zipfile.ZipFile) -> list[str]:
    names = [i.filename for i in z.infolist() if i.filename.endswith(".dex") and not i.is_dir()]
    names.sort(key=dex_sort_key)
    return names


def read_dex(z: zipfile.ZipFile, name: str) -> bytes:
    return z.read(name)


def iter_apk_classes(z: zipfile.ZipFile) -> Iterator[tuple[DexClass, list[tuple[str, str, int]]]]:
    for dex_name in iter_apk_dex_names(z):
        try:
            buf = read_dex(z, dex_name)
            strings = parse_dex(buf).strings
            yield from iter_dex_classes(buf, dex_name, strings)
        except (ValueError, KeyError, OSError) as exc:
            print(f"warning: skipping {dex_name}: {exc}", file=sys.stderr)
            continue


def printable_runs(data: bytes, min_len: int = 3) -> Iterator[str]:
    """Fallback string extraction: ASCII printable runs."""
    cur = bytearray()
    for b in data:
        if 0x20 <= b < 0x7F or b in (0x09,):
            cur.append(b)
        else:
            if len(cur) >= min_len:
                yield cur.decode("ascii", "replace")
            cur.clear()
    if len(cur) >= min_len:
        yield cur.decode("ascii", "replace")


def classify_member(name: str) -> str:
    """Return dex|native_libs|model_assets|configs|other."""
    if name.endswith(".dex"):
        return "dex"
    if name.startswith("lib/") and name.endswith(".so"):
        return "native_libs"
    # Parent brief.json used MODEL_NAME_RE (not the wider .bin/.pb pass) so
    # DebugProbesKt.bin and similar Kotlin noise stay out of model_assets.
    if MODEL_NAME_RE.search(name) or re.search(r"\.(tflite|lite|onnx|mnn|dlc|uim|smfb|gguf|mlmodel|rknn|kmodel|xmodel|ncnn|safetensors)$", name, re.IGNORECASE):
        return "model_assets"
    ext = os.path.splitext(name)[1].lower()
    if ext in CONFIG_EXTS or name.endswith(".arsc"):
        return "configs"
    return "other"


def safe_relpath(name: str) -> str:
    """Normalize a zip member path for extraction; reject traversal."""
    name = name.replace("\\", "/").lstrip("/")
    parts = [p for p in name.split("/") if p and p not in (".", "..")]
    return "/".join(parts)
