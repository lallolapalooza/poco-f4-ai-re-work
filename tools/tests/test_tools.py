#!/usr/bin/env python3
"""Self-tests for the static-analysis tools.

Usage:
    python3 tests/test_tools.py            # fast tests (elf_info/disasm/carve)
    python3 tests/test_tools.py --ghidra   # also runs the Ghidra wrapper
    WORKSPACE=/path python3 tests/test_tools.py

Corpus defaults to <workspace>/samples/android and <workspace>/samples/tflite.
Set ELF_CORPUS / TFLITE_CORPUS / DLC_SAMPLE to override.
"""

from __future__ import annotations

import argparse
import json
import os
import shutil
import struct
import subprocess
import sys
import tempfile
import time
from pathlib import Path

TOOLS = Path(__file__).resolve().parents[1]
WORKSPACE = Path(os.environ.get("WORKSPACE", TOOLS.parent))
PYTHON = os.environ.get("PYTHON", sys.executable)
ELF_CORPUS = Path(os.environ.get("ELF_CORPUS", WORKSPACE / "samples/android"))
TFLITE_CORPUS = Path(os.environ.get("TFLITE_CORPUS", WORKSPACE / "samples/tflite"))
DLC_SAMPLE = Path(os.environ.get("DLC_SAMPLE",
                                 WORKSPACE / "samples/models/document_model.dlc"))

SMALL = ELF_CORPUS / "libaivs_jni.so"
BIG = ELF_CORPUS / "libstfaceunlockocl.so"
JNI = ELF_CORPUS / "libjni_stfaceunlock_api.so"

PASS = 0
FAIL = 0
FAILURES = []


def check(name, condition, detail=""):
    global PASS, FAIL
    if condition:
        PASS += 1
        print("PASS %-52s %s" % (name, detail))
    else:
        FAIL += 1
        FAILURES.append(name)
        print("FAIL %-52s %s" % (name, detail))


def run(args, timeout=300, cwd=None):
    return subprocess.run(args, capture_output=True, text=True,
                          timeout=timeout, cwd=cwd)


def proto_str(field, text):
    data = text.encode("ascii")
    return bytes([(field << 3) | 2, len(data)]) + data


def make_fixture(path: Path):
    layer = lambda n, t: proto_str(1, n) + proto_str(2, t)
    net = proto_str(1, "testnet")
    for name, ltype in (("conv1", "Convolution"), ("pool1", "Pooling"),
                        ("fc1", "InnerProduct")):
        payload = layer(name, ltype)
        net += bytes([0x12, len(payload)]) + payload
    tflite = (TFLITE_CORPUS / "gmscore__segmenter.tflite").read_bytes()
    dlc = DLC_SAMPLE.read_bytes() if DLC_SAMPLE.is_file() else b""
    blob = b"X" * 1000 + net + b"Y" * 777 + tflite + b"Z" * 321 + dlc + b"Q" * 99
    path.write_bytes(blob)
    return {
        "caffe": (1000, len(net)),
        "tflite": (1000 + len(net) + 777, None),
        "zip": ((1000 + len(net) + 777 + len(tflite) + 321, len(dlc))
                if dlc else None),
    }


def test_elf_info(tmp: Path):
    for label, path, min_jni in (("small", SMALL, 3), ("big", BIG, 0),
                                 ("jni-api", JNI, 3)):
        out = tmp / ("info_%s.json" % label)
        proc = run([PYTHON, str(TOOLS / "elf_info.py"), str(path),
                    "--json", str(out), "--strings-limit", "20"])
        check("elf_info %s exit" % label, proc.returncode == 0,
              proc.stderr.strip()[-160:])
        if proc.returncode != 0:
            continue
        data = json.loads(out.read_text())
        check("elf_info %s header" % label,
              data["class"] == "ELF64" and data["machine"] == "AARCH64",
              "%s/%s" % (data["class"], data["machine"]))
        check("elf_info %s needed" % label, len(data["needed"]) >= 1,
              ",".join(data["needed"][:3]))
        check("elf_info %s symbols" % label,
              len(data["symbols"]["exported"]) > 0
              and len(data["symbols"]["imported"]) > 0,
              "exp=%d imp=%d" % (len(data["symbols"]["exported"]),
                                 len(data["symbols"]["imported"])))
        check("elf_info %s jni" % label,
              len(data["jni_entrypoints"]) >= min_jni,
              str(len(data["jni_entrypoints"])))
        # libstfaceunlockocl.so has no .note.gnu.build-id at all.
        check("elf_info %s build-id" % label,
              ("build_id" in data) and (bool(data.get("build_id"))
                                        or label == "big"),
              str(data.get("build_id"))[:16])
        check("elf_info %s strings" % label,
              not data["strings"] or
              sum(data["strings"]["counts"].values()) > 0,
              str((data.get("strings") or {}).get("counts", {}))[:100])
    # The 106 MB library must mention Caffe/model names in its data sections.
    data = json.loads((tmp / "info_big.json").read_text())
    counts = (data.get("strings") or {}).get("counts", {})
    check("elf_info big model/face strings",
          counts.get("model", 0) > 0 and counts.get("face", 0) > 0,
          "model=%s face=%s" % (counts.get("model"), counts.get("face")))


def test_disasm(tmp: Path):
    cases = [
        ("small", SMALL, "Java_com_xiaomi_ai_android_vad_Vad_native_1vadInit"),
        ("big", BIG, "st_auth_reset"),
    ]
    for label, path, symbol in cases:
        out = tmp / ("disasm_%s.s" % label)
        proc = run([PYTHON, str(TOOLS / "disasm_elf.py"), str(path),
                    "--symbol", symbol, "--out", str(out)])
        text = out.read_text() if out.is_file() else ""
        check("disasm %s exit" % label, proc.returncode == 0,
              proc.stderr.strip()[-160:])
        check("disasm %s listing" % label,
              len([l for l in text.splitlines()
                   if l.startswith("0x")]) >= 3, "%d bytes" % len(text))
    small = (tmp / "disasm_small.s").read_text()
    big = (tmp / "disasm_big.s").read_text()
    check("disasm small PLT annotation", "@plt" in small)
    check("disasm big branch annotation", "->" in big)
    check("disasm headers", "; file" in big and "; target" in big)

    proc = run([PYTHON, str(TOOLS / "disasm_elf.py"), str(JNI), "--list-jni"])
    jni_lines = [l for l in proc.stdout.splitlines() if l.startswith("  0x")]
    check("disasm --list-jni", proc.returncode == 0 and len(jni_lines) >= 3,
          "%d rows" % len(jni_lines))
    proc = run([PYTHON, str(TOOLS / "disasm_elf.py"), str(BIG),
                "--range", "0x886c0:0x88700", "--out", "-"])
    check("disasm --range", proc.returncode == 0 and "br" in proc.stdout)


def test_carve(tmp: Path):
    tflite_sample = TFLITE_CORPUS / "securitycoreadd__aiautobrt.tflite"
    out = tmp / "carve_tflite.json"
    proc = run([PYTHON, str(TOOLS / "carve_models.py"), str(tflite_sample),
                "--json", str(out)])
    report = json.loads(out.read_text()) if out.is_file() else {"findings": []}
    kinds = [f["kind"] for f in report["findings"]]
    check("carve tflite", proc.returncode == 0 and "tflite" in kinds,
          str(kinds))
    tf = [f for f in report["findings"] if f["kind"] == "tflite"]
    check("carve tflite size covers file",
          bool(tf) and tf[0]["size"] == tflite_sample.stat().st_size)

    out = tmp / "carve_big.json"
    proc = run([PYTHON, str(TOOLS / "carve_models.py"), str(BIG),
                "--json", str(out)])
    report = json.loads(out.read_text()) if out.is_file() else {"findings": []}
    texts = [str(f.get("note", "")) + " " + str(f.get("kind", ""))
             for f in report["findings"]]
    has_caffe = any("caffe" in t.lower() for t in texts)
    has_model = any("_model" in t for t in texts)
    check("carve big caffe strings", proc.returncode == 0 and has_caffe,
          "caffe hits=%d" % sum("caffe" in t.lower() for t in texts))
    check("carve big model strings", has_model,
          "model hits=%d" % sum("_model" in t for t in texts))

    fixture = tmp / "fixture.bin"
    expected = make_fixture(fixture)
    out = tmp / "carve_fixture.json"
    extract = tmp / "extract"
    proc = run([PYTHON, str(TOOLS / "carve_models.py"), str(fixture),
                "--extract", str(extract), "--json", str(out),
                "--types", "tflite,zip,caffe,strings"])
    report = json.loads(out.read_text()) if out.is_file() else {"findings": []}
    by_kind = {f["kind"]: f for f in report["findings"]}
    check("carve fixture caffe_proto", "caffe_proto" in by_kind and
          by_kind["caffe_proto"]["offset"] == expected["caffe"][0],
          str(by_kind.get("caffe_proto", {}).get("offset")))
    check("carve fixture tflite", "tflite" in by_kind and
          by_kind["tflite"]["offset"] == expected["tflite"][0])
    if expected["zip"]:
        check("carve fixture zip", "zip_dlc" in by_kind and
              by_kind["zip_dlc"]["offset"] == expected["zip"][0] and
              by_kind["zip_dlc"]["size"] == expected["zip"][1],
              str(by_kind.get("zip_dlc", {})))
        carved = [p for p in extract.glob("zip_dlc_*")]
        check("carve fixture extract",
              bool(carved) and carved[0].read_bytes() == DLC_SAMPLE.read_bytes())
    flat = run([PYTHON, str(TOOLS / "carve_models.py"), str(fixture),
                "--types", "flatbuffer", "--scan-flatbuffers"])
    check("carve flatbuffer scanner", flat.returncode == 0)


def test_ghidra(tmp: Path):
    out = tmp / "ghidra.c"
    start = time.time()
    proc = run([str(TOOLS / "ghidra_decompile.sh"), str(JNI), str(out),
                "Java_.*", "--timeout", "600", "--max-functions", "10"],
               timeout=660)
    elapsed = time.time() - start
    text = out.read_text() if out.is_file() else ""
    funcs = [l for l in text.splitlines() if l.startswith(" * Function:")]
    check("ghidra exit", proc.returncode == 0, proc.stderr.strip()[-200:])
    check("ghidra >=3 JNI functions", len(funcs) >= 3,
          "%d functions in %.0fs" % (len(funcs), elapsed))
    check("ghidra C pseudocode", "JNIEnv *env" in text)
    check("ghidra summary", "SUMMARY matched=" in proc.stdout)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--ghidra", action="store_true",
                    help="also run the slow headless-Ghidra test")
    args = ap.parse_args()

    missing = [p for p in (SMALL, BIG, JNI) if not p.is_file()]
    if missing:
        print("missing corpus files: %s" % ", ".join(map(str, missing)))
        return 2

    with tempfile.TemporaryDirectory(prefix="tools_tests.") as td:
        tmp = Path(td)
        test_elf_info(tmp)
        test_disasm(tmp)
        test_carve(tmp)
        if args.ghidra:
            test_ghidra(tmp)

    print("-" * 72)
    print("%d passed, %d failed" % (PASS, FAIL))
    if FAIL:
        print("failed: " + ", ".join(FAILURES))
    return 0 if FAIL == 0 else 1


if __name__ == "__main__":
    raise SystemExit(main())
