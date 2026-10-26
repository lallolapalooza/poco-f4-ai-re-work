#!/usr/bin/env python3
"""Decoder for FaceUnity/MiMediaEditor `.bundle` containers.

Container layout (reversed from libCNamaSDK.so::CNamaSDK::BundleHelper::
DecryptObfuscatedPackage, vaddr 0x56fd68; see native/cnamasdk_bundle.c):

    0   4   magic  f3 5b 06 12
    4   8   header bytes;  XOR key k = byte[4] ^ byte[5] ^ byte[6]
    12  16  IV for the first 1024-byte block
    28  1040 ciphertext of the first 1056-byte block (16-byte prologue +
            1024 payload bytes that are inserted at output offset 0)
    1068..  remainder, copied verbatim
    tail    all of the output is then XOR-ed with the single byte k

The result is a normal ZIP archive (DEFLATE entries).  Entries whose data
starts below offset 1024 sit in the strongly encrypted prologue and cannot be
recovered without `BundleHelper::_obfuscation_key`; everything else decodes.

    python unbundle.py list   <file.bundle>
    python unbundle.py extract <file.bundle> <outdir>
"""

from __future__ import annotations

import argparse
import os
import struct
import sys
import zlib

MAGIC = bytes.fromhex("f35b0612")
HDR = 1068          # 4 magic + 8 hdr + 16 iv + 1040 first-block ciphertext
PLAIN = 1024        # decrypted prefix length


def decrypt_prefix(data: bytes) -> bytes:
    """Return the 1024-byte plaintext prefix, or raise if it cannot be done."""
    raise NotImplementedError(
        "first 1024 bytes are encrypted with CNamaSDK::BundleHelper::DecryptObfuscatedPackageTFE "
        "(table cipher keyed by BundleHelper::_obfuscation_key) - key not present in the APK"
    )


def load_container(path: str) -> tuple[bytes, int, bytes]:
    d = open(path, "rb").read()
    if d[:4] != MAGIC:
        raise ValueError(f"{path}: bad magic {d[:4].hex()} (expected {MAGIC.hex()})")
    k = d[4] ^ d[5] ^ d[6]
    body = bytes(c ^ k for c in d[HDR:])
    return d, k, body


def zip_entries(d: bytes, k: int, body: bytes) -> list[dict]:
    eocd = body.rfind(b"PK\x05\x06")
    if eocd < 0:
        raise ValueError("no EOCD")
    disk, cdisk, n1, n2, cdsz, cdoff, clen = struct.unpack_from("<HHHHIIH", body, eocd + 4)
    cd = body[cdoff - PLAIN:cdoff - PLAIN + cdsz]
    out, i = [], 0
    while i < len(cd):
        if cd[i:i + 4] != b"PK\x01\x02":
            raise ValueError(f"bad central directory at {i}")
        (_, _, flags, meth, _, _, crc, csz, usz, nlen, elen, clen2,
         _, _, _, off) = struct.unpack_from("<HHHHHHIIIHHHHHII", cd, i + 4)
        name = cd[i + 46:i + 46 + nlen].decode("utf-8", "replace")
        out.append(dict(name=name, method=meth, flags=flags, crc=crc,
                        csize=csz, usize=usz, offset=off))
        i += 46 + nlen + elen + clen2
    for e in out:
        e["encrypted"] = e["offset"] < PLAIN
    return out


def read_entry(d: bytes, k: int, e: dict) -> bytes | None:
    """Return the decompressed entry, or None when it lies in the encrypted prefix."""
    start = e["offset"]
    if start < PLAIN:
        return None
    # local file header is 30 bytes + name + extra; find it after the prefix
    lh = start - PLAIN
    sig = bytes(b ^ k for b in b"PK\x03\x04")
    if d[HDR + lh:HDR + lh + 4] != sig:
        idx = d.find(sig, HDR + lh, HDR + lh + 256)
        if idx < 0:
            raise ValueError(f"{e['name']}: local header not found at {start}")
        lh = idx - HDR
    hdr = bytes(b ^ k for b in d[HDR + lh:HDR + lh + 30])
    nlen, elen = struct.unpack_from("<HH", hdr, 26)
    ds = HDR + lh + 30 + nlen + elen
    raw = bytes(c ^ k for c in d[ds:ds + e["csize"]])
    if e["method"] == 0:
        return raw
    if e["method"] == 8:
        return zlib.decompress(raw, -15)
    raise ValueError(f"unsupported method {e['method']}")


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("cmd", choices=["list", "extract"])
    ap.add_argument("bundle")
    ap.add_argument("outdir", nargs="?")
    a = ap.parse_args()

    d, k, body = load_container(a.bundle)
    entries = zip_entries(d, k, body)
    print(f"{os.path.basename(a.bundle)}: xor_key=0x{k:02x} "
          f"file={len(d)}B entries={len(entries)}")
    for e in entries:
        flag = " ENCRYPTED-PREFIX" if e["encrypted"] else ""
        print(f"  {e['name']:58s} method={e['method']} "
              f"csize={e['csize']:9d} usize={e['usize']:9d} "
              f"off={e['offset']:9d}{flag}")
    n_crc = n_bad = 0
    for e in entries:
        if e["encrypted"]:
            continue
        try:
            data = read_entry(d, k, e)
        except Exception as exc:
            print(f"  ERROR {e['name']}: {exc}")
            n_bad += 1
            continue
        if zlib.crc32(data) & 0xFFFFFFFF != e["crc"]:
            print(f"  CRC-MISMATCH {e['name']}")
            n_bad += 1
        else:
            n_crc += 1
    print(f"  CRC32 verified entries: {n_crc} ok, {n_bad} bad "
          f"({sum(1 for e in entries if e['encrypted'])} skipped in encrypted prefix)")
    if a.cmd == "extract":
        assert a.outdir
        os.makedirs(a.outdir, exist_ok=True)
        for e in entries:
            name = e["name"]
            if not name or name.endswith("/"):
                print(f"  SKIP (directory): {name}")
                continue
            data = read_entry(d, k, e)
            if data is None:
                print(f"  SKIP (encrypted prefix): {name}")
                continue
            p = os.path.join(a.outdir, name.lstrip("/"))
            os.makedirs(os.path.dirname(p), exist_ok=True)
            open(p, "wb").write(data)
            print(f"  wrote {p} ({len(data)} bytes)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
