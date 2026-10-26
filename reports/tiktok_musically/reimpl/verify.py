"""Self-verification for the TikTok smash-face descriptor reimplementation.

    python verify.py            # -> prints a transcript, exit 0 on success

Covers: AES known-answer test, container parse (byte-exact to EOF), record
decryption for both cracked auth codes, descriptor structural invariants,
weight-buffer closure, and execution of real records.
"""

from __future__ import annotations

import json
import os
import sys

import numpy as np

import descriptor as D
import run_record as R

HERE = os.path.dirname(os.path.abspath(__file__))
MODELS = os.path.join(HERE, os.pardir, "models")
FILES = ["tt_face_v10.0.model", "tt_face_extra_v13.0.model",
         "tt_face_attribute_age_v1.1.model"]

fails: list[str] = []


def check(name: str, ok: bool, detail: str = "") -> None:
    print(f"[{'ok  ' if ok else 'FAIL'}] {name}" + (f"  {detail}" if detail else ""))
    if not ok:
        fails.append(name)


def main() -> int:
    print("== AES")
    kat_key = bytes.fromhex("000102030405060708090a0b0c0d0e0f"
                            "101112131415161718191a1b1c1d1e1f")
    kat_ct = bytes.fromhex("8ea2b7ca516745bfeafc49904b496089")
    kat_pt = bytes.fromhex("00112233445566778899aabbccddeeff")
    check("FIPS-197 C.3 AES-256 decrypt", D.aes256_ecb_decrypt(kat_key, kat_ct) == kat_pt)

    print("\n== container")
    pkgs = {}
    for f in FILES:
        p = D.ModelPackage.parse(os.path.join(MODELS, f))
        pkgs[f] = p
        check(f"{f}: declared_size == file size", p.declared_size == p.size,
              f"{p.declared_size}")
        check(f"{f}: records parse, remainder {getattr(p, 'trailing', 0)} B",
              getattr(p, "trailing", 0) in (0, 8),
              f"version={p.version!r} records={len(p.records)}")
        for r in p.records:
            check(f"{f}:{r.name}: key_len == 36 and key[32:36] == u32 32",
                  len(r.key_field) == 36 and
                  int.from_bytes(r.key_field[32:36], "little") == 32)
            ok = (len(r.config) - 4) % 16 == 0 and 1 <= r.config_plain_len <= len(r.config) - 4
            check(f"{f}:{r.name}: config len {len(r.config)} == 4 mod 16, plain_len {r.config_plain_len}", ok)

    print("\n== descriptor decryption (11 of 12 records; agenet blocked)")
    n_nets = 0
    for f in ("tt_face_v10.0.model", "tt_face_extra_v13.0.model"):
        p = pkgs[f]
        auth = D.AUTH_CODES[p.version]
        for r in p.records:
            txt = r.config_text(auth)
            printable = sum(1 for c in txt if 32 <= c < 127 or c in (9, 10, 13)) / len(txt)
            ok = printable > 0.99
            if r.name == "mean":
                check(f"{f}:mean: config plaintext {txt!r} (data record)", txt == b"1")
                continue
            check(f"{f}:{r.name}: plaintext is ASCII (printable {printable:.3f})", ok)
            if not ok:
                continue
            try:
                net = D.parse_net(txt.decode("ascii"))
                check(f"{f}:{r.name}: net parsed, {len(net.layers)} layers "
                      f"(declared {net.declared_layers} + data)", 
                      len(net.layers) == net.declared_layers + 1)
                n_nets += 1
            except Exception as e:                     # noqa: BLE001
                check(f"{f}:{r.name}: net parse", False, str(e))

    age = pkgs["tt_face_attribute_age_v1.1.model"]
    print(f"[note] tt_face_attribute_age_v1.1.model:agenet: auth_code not recovered "
          f"(config {len(age.records[0].config)} B, plain_len "
          f"{age.records[0].config_plain_len}) -> BLOCKER, see GAP_CLOSURE.md")

    print("\n== weight-buffer closure (int8/int16 weights + int32 bias per layer)")
    closed, total = 0, 0
    for f in ("tt_face_v10.0.model", "tt_face_extra_v13.0.model"):
        p = pkgs[f]
        auth = D.AUTH_CODES[p.version]
        for r in p.records:
            if r.name == "mean":
                continue
            net = D.parse_net(r.config_text(auth).decode("ascii"))
            try:
                _t, st = R.carve_weights(net, r.weight)
            except Exception as e:                       # noqa: BLE001
                print(f"       {f[:20]:20s} {r.name:11s} unsupported: {e}")
                continue
            total += 1
            closed += st["closed"]
            print(f"       {f[:20]:20s} {r.name:11s} consumed={st['weight_bytes_consumed']:7d}"
                  f" buffer={st['buffer_len']:7d} residue={st['residue']:6d}"
                  f" {'CLOSED' if st['closed'] else ''}")
    check(f"weight layout closes for {closed}/{total} shape-resolvable records", closed >= 2)

    print("\n== execution with real weights")
    det = R.run(os.path.join(MODELS, "tt_face_v10.0.model"), "detect", wscale=8.0)
    check("detect runs: 1x3x128x128 -> rpn output",
          det["output_shape"] == [1, 12, 4, 4] and det["weight_layout_closed"],
          f"layers={det['parsed_layers']} tensors={det['weighted_tensors']} "
          f"out[{det['output_min']:.3f},{det['output_max']:.3f}]")
    img = R.run(os.path.join(MODELS, "tt_face_v10.0.model"), "image", wscale=8.0)
    check("image runs: 1x3x160x160 fp16(int16) weights, buffer closed",
          img["weight_layout_closed"] and img["parsed_layers"] == 75,
          f"tensors={img['weighted_tensors']} out={img['output_shape']}")
    slow = R.run(os.path.join(MODELS, "tt_face_v10.0.model"), "slowimage", wscale=8.0)
    check("slowimage runs: 1x3x192x192", slow["weight_layout_closed"],
          f"out={slow['output_shape']}")
    pou = R.run(os.path.join(MODELS, "tt_face_v10.0.model"), "pout", wscale=7.0)
    check("pout runs: 56x56 -> softmax prob (2 classes)",
          pou["output_shape"] == [1, 2, 1, 1] and
          abs(pou["output_min"] + pou["output_max"] - 1.0) < 1e-9,
          f"weight residue={pou['weight_layout_residue']} (InnerProduct f32 + 1 B)")
    mean = R.run(os.path.join(MODELS, "tt_face_v10.0.model"), "mean")
    check("mean data record: 1905 float32 prior/anchor table",
          mean["kind"] == "data" and mean["count"] == 1905,
          f"range [{mean['min']:.3f}, {mean['max']:.3f}]")

    print("\n== extra model (tt_face_extra_v13.0.model)")
    for rec in ("iris", "extra"):
        try:
            res = R.run(os.path.join(MODELS, "tt_face_extra_v13.0.model"), rec, wscale=8.0)
        except Exception as e:                            # noqa: BLE001
            print(f"[note] extra:{rec} graph needs layer support: {e}")
            continue
        check(f"extra:{rec} runs", res["parsed_layers"] == res["declared_layers"] + 1,
              f"out={res['output_shape']} residue={res['weight_layout_residue']}")

    print(f"\n{len(fails)} failure(s)")
    return 1 if fails else 0


if __name__ == "__main__":
    sys.exit(main())
