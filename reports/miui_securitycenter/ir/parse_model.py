#!/usr/bin/env python3
"""Parser for MIUISecurityCenter `assets/model` (custom big-endian linear SMS model).

Container (confirmed by ah/c.java + ah/d.java reading order and by an exact
end-of-file walk over the shipped 680,154-byte asset):

    u32 be  version          == 1
    u32 be  reserved         == 0
    u32 be  n_models         == 5        (sequential cascade stages)
    repeat n_models times:
        i32 be  n_feat                    (rule-feature count for this stage)
        i32 be  n_chars1; n_chars1 * u16 be   (dh.g dictionary #1 payload)
        i32 be  n_chars2                      (dh.g dictionary #2, header only)
        f64 be  w[2][n_feat]              (log-probability weights, class 0/1)
        i32 be  n_tokens
          n_tokens * { u16 be chars ... u16 be 0x000A ; f64 be w0 ; f64 be w1 }
        i32 be  n_patterns
          n_patterns * { u16 be chars ... u16 be 0x000A ; f64 be w0 ; f64 be w1 }
        f64 be  cat_bias[2][8]            (sender-category bias, dh.a index)
        f64 be  thr[3]                    (upper, lower, centre) -- see ah/d.h

Usage:
    parse_model.py <model> [--json out.json] [--dump-weights out.npz] [--quiet]
"""
from __future__ import annotations

import argparse
import hashlib
import json
import struct
import sys

CATEGORIES = ["Common", "100+5", "106Short", "106Common", "106Long", "9Pre5", "Other", "Null"]


class Reader:
    def __init__(self, buf: bytes):
        self.b = buf
        self.o = 0

    def i32(self) -> int:
        v = struct.unpack_from(">i", self.b, self.o)[0]
        self.o += 4
        return v

    def u32(self) -> int:
        v = struct.unpack_from(">I", self.b, self.o)[0]
        self.o += 4
        return v

    def f64(self) -> float:
        v = struct.unpack_from(">d", self.b, self.o)[0]
        self.o += 8
        return v

    def chars(self) -> str:
        """UTF-16BE chars up to (not including) the 0x000A terminator."""
        out = []
        while True:
            c = struct.unpack_from(">H", self.b, self.o)[0]
            self.o += 2
            if c == 0x0A:
                return "".join(out)
            out.append(chr(c))


def parse(path: str) -> dict:
    buf = open(path, "rb").read()
    r = Reader(buf)
    hdr = {"version": r.u32(), "reserved": r.u32(), "n_models": r.u32()}
    models = []
    for m in range(hdr["n_models"]):
        off0 = r.o
        n_feat = r.i32()
        n1 = r.i32()
        chars1 = [struct.unpack_from(">H", buf, r.o + 2 * i)[0] for i in range(n1)]
        r.o += 2 * n1
        n2 = r.i32()
        off_w = r.o
        # ah/d.h reads the pairs interleaved: w[0][i], w[1][i], w[0][i+1], ...
        pairs = [(r.f64(), r.f64()) for _ in range(n_feat)]
        w = [[p[0] for p in pairs], [p[1] for p in pairs]]
        n_tok = r.i32()
        toks = []
        for _ in range(n_tok):
            s = r.chars()
            toks.append((s, r.f64(), r.f64()))
        n_pat = r.i32()
        pats = []
        for _ in range(n_pat):
            s = r.chars()
            pats.append((s, r.f64(), r.f64()))
        off_bias = r.o
        # interleaved as well: bias[0][c], bias[1][c]
        bpairs = [(r.f64(), r.f64()) for _ in range(8)]
        bias = [[p[0] for p in bpairs], [p[1] for p in bpairs]]
        thr = [r.f64(), r.f64(), r.f64()]
        models.append(
            {
                "offset": off0,
                "end": r.o,
                "n_feat": n_feat,
                "chars1": "".join(chr(c) for c in chars1),
                "chars1_codepoints": chars1,
                "n_chars2": n2,
                "weight_offset": off_w,
                "weights": w,
                "tokens": toks,
                "patterns": pats,
                "bias_offset": off_bias,
                "bias": bias,
                "thresholds": thr,
            }
        )
    return {"header": hdr, "models": models, "consumed": r.o, "size": len(buf),
            "sha256": hashlib.sha256(buf).hexdigest()}


def summarise(ir: dict) -> str:
    out = [f"file {ir['size']} bytes sha256 {ir['sha256']}",
           f"header version={ir['header']['version']} reserved={ir['header']['reserved']} "
           f"n_models={ir['header']['n_models']} consumed={ir['consumed']} exact={ir['consumed']==ir['size']}"]
    for i, m in enumerate(ir["models"]):
        ws = [v for row in m["weights"] for v in row]
        out.append(
            f"stage {i}: off 0x{m['offset']:x}..0x{m['end']:x} n_feat={m['n_feat']} "
            f"dict1={len(m['chars1'])}ch dict2={m['n_chars2']} tokens={len(m['tokens'])} "
            f"patterns={len(m['patterns'])} thr=({m['thresholds'][0]:.3f},{m['thresholds'][1]:.3f},"
            f"{m['thresholds'][2]:.3f}) w[min,max]=({min(ws):.3f},{max(ws):.3f})")
    return "\n".join(out)


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("model")
    ap.add_argument("--json")
    ap.add_argument("--dump-weights")
    ap.add_argument("--quiet", action="store_true")
    a = ap.parse_args()
    ir = parse(a.model)
    if not a.quiet:
        print(summarise(ir))
    if a.json:
        slim = dict(ir)
        # keep the IR machine-readable but bounded: full token/pattern tables are
        # kept (they *are* the model); only weights are reduced to statistics.
        out = {"format": "miui_sms_linear_cascade_v1", "header": ir["header"],
               "size": ir["size"], "sha256": ir["sha256"], "consumed": ir["consumed"],
               "stages": []}
        for i, m in enumerate(ir["models"]):
            ws = [v for row in m["weights"] for v in row]
            out["stages"].append(
                {"index": i, "offset": m["offset"], "end": m["end"], "n_feat": m["n_feat"],
                 "dict1": m["chars1"], "dict1_codepoints": m["chars1_codepoints"],
                 "dict2_count": m["n_chars2"],
                 "n_tokens": len(m["tokens"]), "n_patterns": len(m["patterns"]),
                 "thresholds": m["thresholds"], "bias": m["bias"],
                 "phone_categories": CATEGORIES,
                 "weights_min": min(ws), "weights_max": max(ws),
                 "tokens_head": [[t[0], t[1], t[2]] for t in m["tokens"][:20]],
                 "patterns_head": [[p[0], p[1], p[2]] for p in m["patterns"][:20]]})
        open(a.json, "w").write(json.dumps(out, ensure_ascii=True, indent=1))
    if a.dump_weights:
        import numpy as np
        arrs = {}
        for i, m in enumerate(ir["models"]):
            arrs[f"stage{i}_w"] = np.array(m["weights"], dtype=np.float64)
            arrs[f"stage{i}_bias"] = np.array(m["bias"], dtype=np.float64)
            arrs[f"stage{i}_thr"] = np.array(m["thresholds"], dtype=np.float64)
            arrs[f"stage{i}_tok_w"] = np.array([t[1:] for t in m["tokens"]], dtype=np.float64)
            arrs[f"stage{i}_pat_w"] = np.array([p[1:] for p in m["patterns"]], dtype=np.float64)
            arrs[f"stage{i}_dict1"] = np.array(m["chars1_codepoints"], dtype=np.uint16)
        np.savez_compressed(a.dump_weights, **arrs)
    return 0


if __name__ == "__main__":
    sys.exit(main())
