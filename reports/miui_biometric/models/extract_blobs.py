#!/usr/bin/env python3
"""Extract the SenseTime FaceAuth model resources embedded in libstfaceunlockocl.so.

The eight CNNs live in `.data` as compiler-generated symbols:

    __res_<MODEL_NAME>_model        <- blob bytes
    __res_<MODEL_NAME>_model_size   <- 4 bytes
    __res_<MODEL_NAME>_model_end    <- 8 bytes

Symbol sizes give the exact extent of every blob.  `.data` is mapped at
file_offset = vaddr - 0x10000 in this shared object, so the symbol address must
be translated through the section headers (pyelftools) before reading.

Container format (recovered, plaintext header):

    0x00  "STEF"                    magic
    0x04  u32le payload_size        always a multiple of 0x1000 (and of 16)
    0x08  "VER"
    0x0b  u8    version = 3
    0x0c  u32le flags/reserved = 0
    0x10  payload[payload_size]     high-entropy: encrypted (see report)
    0x10+payload_size   257-byte trailer (256-byte signature/MAC + 0x00)

    total blob size == 16 + payload_size + 257

Output: models/<name>.bin, models/manifest.json
"""
import hashlib
import json
import os
import struct
import subprocess
import sys

from elftools.elf.elffile import ELFFile

HERE = os.path.dirname(os.path.abspath(__file__))
DEFAULT_SO = os.path.join(
    os.path.dirname(os.path.dirname(os.path.dirname(HERE))),
    "samples", "android", "libstfaceunlockocl.so")


def read_symbols(path):
    out = subprocess.run(["nm", "-S", "-n", path], capture_output=True, text=True).stdout
    syms = {}
    for line in out.splitlines():
        parts = line.split()
        if len(parts) == 4:
            va, size, typ, name = parts
            if name.startswith("__res_"):
                syms[name] = (int(va, 16), int(size, 16), typ)
    return syms


class VaMapper:
    def __init__(self, path):
        self.elf = ELFFile(open(path, "rb"))
        self.sections = []
        for s in self.elf.iter_sections():
            if s["sh_type"] != "SHT_NOBITS" and s["sh_size"]:
                self.sections.append((s["sh_addr"], s["sh_addr"] + s["sh_size"],
                                      s["sh_offset"] - s["sh_addr"], s.name))

    def __call__(self, va):
        for lo, hi, delta, name in self.sections:
            if lo <= va < hi:
                return va + delta, name
        raise ValueError(f"vaddr {va:#x} is not backed by a section")


def main():
    so = sys.argv[1] if len(sys.argv) > 1 else DEFAULT_SO
    outdir = HERE
    syms = read_symbols(so)
    va2off = VaMapper(so)
    bases = sorted({n[:-len("_model")] for n in syms if n.endswith("_model")})

    with open(so, "rb") as f:
        blob_file = f.read()

    manifest = {"source": os.path.abspath(so), "container_format": "STEF/v3", "models": []}
    for base in bases:
        va, size, _ = syms[base + "_model"]
        off, section = va2off(va)
        name = base[len("__res_"):]
        data = blob_file[off:off + size]
        fn = name + ".bin"
        with open(os.path.join(outdir, fn), "wb") as g:
            g.write(data)

        hdr = {}
        if data[:4] == b"STEF":
            payload_size = struct.unpack("<I", data[4:8])[0]
            hdr = {
                "magic": "STEF",
                "payload_size": payload_size,
                "ver_tag": data[8:11].decode("latin1"),
                "version": data[11],
                "flags": struct.unpack("<I", data[12:16])[0],
                "payload_offset": 16,
                "trailer_size": len(data) - 16 - payload_size,
            }
        manifest["models"].append({
            "name": name,
            "file": fn,
            "symbol": base,
            "vaddr": hex(va),
            "file_offset": hex(off),
            "section": section,
            "size": size,
            "sha256": hashlib.sha256(data).hexdigest(),
            "header": hdr,
            "payload_encrypted": True,
        })

    with open(os.path.join(outdir, "manifest.json"), "w") as g:
        json.dump(manifest, g, indent=2)

    total = sum(m["size"] for m in manifest["models"])
    print(f"extracted {len(manifest['models'])} blobs, {total} bytes ({total/2**20:.2f} MiB)")
    for m in manifest["models"]:
        h = m["header"]
        print(f"  {m['name']:<72} {m['size']:>9} @ off {m['file_offset']:<9} "
              f"payload {h.get('payload_size', 0):>8} trailer {h.get('trailer_size', 0)} "
              f"{m['sha256'][:12]}")


if __name__ == "__main__":
    main()
