#!/usr/bin/env python3
"""Parser for the ByteDance "smash face" model bundle used by TikTok
(tt_face_v10.0.model, tt_face_extra_v13.0.model, tt_face_attribute_age_v1.1.model).

Container layout recovered statically (see report.md):

  0x00  16 B   opaque per-file id (tests for md5/sha of the body found no match)
  0x10  u32    total file size (equals os.stat().st_size)
  0x14  u32    model/version string length  -> version string
        u32    number of sub-model records N
        N x record:
          u32    record name length -> ASCII name
          u32    constant 36
          32 B   per-file constant A (repeats exactly N times in the file)
          u32    constant 32
          32 B   per-record blob B; B[4:8] = size field, uniformly == 4 (mod 16)
          payload (until the next record start, last one until EOF)

The record start of record i>0 is found by locating the file's constant A and
walking backwards over [u32 36][name][u32 namelen]; this is the same
name-search strategy the native loader uses ("Extract model name ... fail").

Payload layout (confirmed with the parallel trill analysis): B[4] is the size of
a serialized *graph descriptor block* at the start of the payload; the rest of
the payload is weight data.  The descriptor block is dense binary (4-byte
aligned u32/u16 op+tensor tables, offsets just below the block size), NOT
ciphertext: the bytenn XOR+inv-Sbox model cipher and AES variants were tested
negative (entropy rises, no text/`BM`).  Only the descriptor schema is unknown.

Run:  python model_container.py [--models DIR] [--out DIR] [--carve]
"""
from __future__ import annotations
import argparse, hashlib, json, math, os, struct, sys
from collections import Counter

MODELS = {
    "tt_face_v10.0.model": "tt_face_v10.0",
    "tt_face_extra_v13.0.model": "v13.0",
    "tt_face_attribute_age_v1.1.model": "tt_face_attribute_age_v1.1",
}
TAG36 = 36
TAG32 = 32


def entropy(b: bytes) -> float:
    if not b:
        return 0.0
    c = Counter(b)
    n = len(b)
    return -sum(v / n * math.log2(v / n) for v in c.values())


def u32(d: bytes, off: int) -> int:
    return struct.unpack_from("<I", d, off)[0]


class Record:
    def __init__(self, name, name_off, rec_off, key_a, blob_b, payload_off, payload_len, data):
        self.name = name
        self.name_off = name_off
        self.rec_off = rec_off
        self.key_a = key_a
        self.blob_b = blob_b
        self.payload_off = payload_off
        self.payload_len = payload_len
        self.data = data  # payload bytes

    @property
    def section_size(self):
        # u32 at B+4: size field, always 4 (mod 16)
        return u32(self.blob_b, 4)

    def to_dict(self):
        sec = self.section_size
        head = self.data[:sec]
        tail = self.data[sec:]
        # descriptor tail: dense u16 table + u32 offsets just below block size
        tail = head[-64:]
        u16s = [struct.unpack_from("<H", head, i)[0] for i in range(max(0, sec - 32), sec - 1, 2)]
        small_u16 = sum(1 for v in u16s if v < 0x100)
        near_offsets = sorted({u32(head, i) for i in range(max(0, sec - 512), sec - 3)
                               if 0 < u32(head, i) < sec and u32(head, i) > sec - 512})
        return {
            "name": self.name,
            "name_offset": self.name_off,
            "record_offset": self.rec_off,
            "key_a_sha256": hashlib.sha256(self.key_a).hexdigest(),
            "blob_b_hex": self.blob_b.hex(),
            "b_size_field": sec,
            "b_size_minus4_mod16": (sec - 4) % 16,
            "payload_offset": self.payload_off,
            "payload_len": self.payload_len,
            "payload_sha256": hashlib.sha256(self.data).hexdigest(),
            "section_entropy": round(entropy(head), 3),
            "rest_entropy": round(entropy(tail), 3),
            "rest_mod4_float_plausible": round(float_plausibility(tail), 3),
            "rest_fp16_plausible": round(fp16_plausibility(tail), 3),
            "rest_int8_zero_fraction": round(tail.count(0) / len(tail), 3) if tail else None,
            "descriptor_tail_small_u16_ratio": round(small_u16 / max(1, len(u16s)), 2),
            "descriptor_internal_offsets": near_offsets[:8],
        }


def float_plausibility(b: bytes, thresh=1e4) -> float:
    vals = 0
    ok = 0
    for i in range(0, len(b) - 3, 4):
        v = struct.unpack_from("<f", b, i)[0]
        vals += 1
        if math.isfinite(v) and abs(v) < thresh:
            ok += 1
    return ok / vals if vals else 0.0


def fp16_plausibility(b: bytes, thresh=100.0) -> float:
    vals = 0
    ok = 0
    for i in range(0, len(b) - 1, 2):
        v = struct.unpack_from("<e", b, i)[0]
        vals += 1
        if math.isfinite(v) and abs(v) < thresh:
            ok += 1
    return ok / vals if vals else 0.0


def find_record_starts(d: bytes, first: int, count: int, key_a: bytes):
    """Locate record starts by their key-A occurrence and back-walk."""
    starts = [first]
    # all A occurrences
    occ, i = [], 0
    while True:
        j = d.find(key_a, i)
        if j < 0:
            break
        occ.append(j)
        i = j + 1
    starts = []
    for a in occ:
        if u32(d, a - 4) != TAG36:
            continue
        # name ends at a-4; walk possible name lengths 2..40
        for nlen in range(2, 41):
            noff = a - 8 - nlen
            if noff < 4:
                continue
            if u32(d, noff) != nlen:
                continue
            name = d[noff + 4:noff + 4 + nlen]
            if all(32 <= c < 127 for c in name):
                starts.append(noff)
                break
    starts = sorted(set(starts))
    if len(starts) != count:
        raise ValueError(f"expected {count} records, located {len(starts)}: {starts}")
    return starts


def parse_file(path: str) -> dict:
    d = open(path, "rb").read()
    size = u32(d, 16)
    if size != len(d):
        raise ValueError(f"{path}: declared size {size} != actual {len(d)}")
    vlen = u32(d, 20)
    version = d[24:24 + vlen].decode("ascii")
    count = u32(d, 24 + vlen)
    first = 24 + vlen + 4
    key_a = None
    # The per-file A is the 32 bytes right after the first record's tag-36.
    nl0 = u32(d, first)
    key_a = d[first + 8 + nl0: first + 8 + nl0 + 32]
    starts = find_record_starts(d, first, count, key_a)
    starts.append(len(d))
    records = []
    for i in range(count):
        rec_off = starts[i]
        nlen = u32(d, rec_off)
        name = d[rec_off + 4:rec_off + 4 + nlen].decode("ascii")
        apos = rec_off + 8 + nlen
        if d[apos:apos + 32] != key_a:
            raise ValueError(f"{path}:{name}: A key mismatch")
        tag = u32(d, apos - 4)
        assert tag == TAG36, (name, tag)
        tag32 = u32(d, apos + 32)
        assert tag32 == TAG32, (name, tag32)
        blob_b = d[apos + 36:apos + 68]
        payload_off = apos + 68
        payload_len = starts[i + 1] - payload_off
        records.append(Record(name, rec_off, apos - 8 - nlen, key_a, blob_b,
                              payload_off, payload_len, d[payload_off:payload_off + payload_len]))
    return {
        "path": os.path.basename(path),
        "file_size": len(d),
        "sha256": hashlib.sha256(d).hexdigest(),
        "file_id_16": d[:16].hex(),
        "version": version,
        "record_count": count,
        "key_a_hex": key_a.hex(),
        "key_a_repeats": d.count(key_a),
        "records": records,
    }


def analyze(m: dict) -> dict:
    out = {
        "path": m["path"], "file_size": m["file_size"], "sha256": m["sha256"],
        "file_id_16": m["file_id_16"], "version": m["version"],
        "record_count": m["record_count"], "key_a_hex": m["key_a_hex"],
        "key_a_repeats": m["key_a_repeats"], "records": [r.to_dict() for r in m["records"]],
    }
    out["invariants"] = {
        "declared_size_matches": True,
        "all_const36": True,
        "all_const32": True,
        "key_a_repeats_equal_count": m["key_a_repeats"] == m["record_count"],
        "all_b_size_4_mod16": all(r.to_dict()["b_size_minus4_mod16"] == 0 for r in m["records"]),
        "records_contiguous": all(
            m["records"][i].payload_off + m["records"][i].payload_len ==
            (m["records"][i + 1].rec_off if i + 1 < len(m["records"]) else m["file_size"])
            for i in range(len(m["records"]))),
    }
    return out


def decode_mean(m: dict):
    """The 'mean' record is a plain data blob: [u32 nbytes][nbytes/4 float32][8 zeros]."""
    for r in m["records"]:
        if r.name != "mean":
            continue
        p = r.data
        n = u32(p, 0)
        if 4 + n + 8 != len(p):
            return None
        vals = list(struct.unpack_from("<%df" % (n // 4), p, 4))
        trailer = p[4 + n:]
        return {"nbytes": n, "count": n // 4, "values": vals, "trailer_hex": trailer.hex()}
    return None


def main(argv=None):
    ap = argparse.ArgumentParser()
    ap.add_argument("--models", default=os.path.join(os.path.dirname(__file__), "..", "models"))
    ap.add_argument("--out", default=os.path.join(os.path.dirname(__file__), "..", "ir"))
    ap.add_argument("--carve", action="store_true")
    args = ap.parse_args(argv)
    os.makedirs(args.out, exist_ok=True)
    carve_dir = os.path.join(os.path.dirname(__file__), "..", "models", "sections")
    for fn in MODELS:
        path = os.path.join(args.models, fn)
        m = parse_file(path)
        a = analyze(m)
        with open(os.path.join(args.out, "container_" + fn + ".json"), "w") as f:
            json.dump(a, f, indent=1)
        print(f"== {fn}  {a['file_size']} B  version={a['version']!r}  records={a['record_count']}")
        for r, rd in zip(m["records"], a["records"]):
            print(f"   {rd['name']:12s} off=0x{rd['record_offset']:06x} payload=0x{rd['payload_offset']:06x}"
                  f" len={rd['payload_len']:7d} B4={rd['b_size_field']:5d} "
                  f"H(sec)={rd['section_entropy']:.2f} H(rest)={rd['rest_entropy']:.2f} "
                  f"fp32%={rd['rest_mod4_float_plausible']:.2f}")
            if args.carve:
                d = os.path.join(carve_dir, fn.replace(".model", ""))
                os.makedirs(d, exist_ok=True)
                sec = rd["b_size_field"]
                open(os.path.join(d, rd["name"] + ".section.bin"), "wb").write(r.data[:sec])
                open(os.path.join(d, rd["name"] + ".rest.bin"), "wb").write(r.data[sec:])
                open(os.path.join(d, rd["name"] + ".payload.bin"), "wb").write(r.data)
        mean = decode_mean(m)
        if mean:
            print(f"   mean: {mean['count']} float32 values, nbytes={mean['nbytes']}, "
                  f"first={mean['values'][:4]}, trailer={mean['trailer_hex']}")
            with open(os.path.join(args.out, "mean_weights.json"), "w") as f:
                json.dump(mean, f)
            if args.carve:
                os.makedirs(carve_dir, exist_ok=True)
                with open(os.path.join(carve_dir, "mean_fp32_1905.bin"), "wb") as f:
                    f.write(struct.pack("<%df" % mean["count"], *mean["values"]))
        if not all(a["invariants"].values()):
            print("INVARIANT FAILURE", a["invariants"], file=sys.stderr)
            return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
