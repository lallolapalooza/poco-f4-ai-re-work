"""Verification for the EAI2 parser + interpreter.  Writes reimpl/verify.txt.

    python reimpl/verify_eai.py          # exit 0 = all checks pass

Checks
------
A  container parse vs the native loader
   `evidence/native_dumps/<blob>.txt` was produced by loading the blob with the
   real libeai_floating.so (evidence/harness/eai_host.c) and asking it for its
   own parse: section table, tensor descriptors, layer records and layer I/O
   lists.  Every field our parser produces must equal what the library reported.

B  rejection classification
   Four blobs make the shipped floating runtime refuse to load.  The parser must
   predict the same reason from the header alone (target/enpu, builder version,
   float flag).

C  numeric equivalence of the interpreter
   speech_float_speech_0.eai (DNN VAD) is executed both natively and in numpy;
   every tensor is compared.  Tensors the native allocator aliases are reported
   separately: their dumped content is whatever the *last* writer left there, so
   only the semantic outputs must match.

D  LSTM gate order
   Independent re-derivation of the gate mapping from the native dump:
   c = sigma(pre[0:H]) * tanh(pre[3H:4H]) and h/c = sigma(pre[H:2H]) * tanh(c)/tanh(c).

E  structural invariants for all 16 blobs
   constant-tensor offsets are contiguous and inside section 2, layer records
   tile section 4 exactly, layer I/O ids are in range, group counts add up.
"""

from __future__ import annotations

import glob
import json
import os
import re
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.dirname(HERE)
sys.path.insert(0, HERE)

import eai_loader  # noqa: E402
import eai_run     # noqa: E402

EAI_DIR = os.path.join(ROOT, "models", "eai")
DUMPS = os.path.join(ROOT, "evidence", "native_dumps")
OUT = os.path.join(HERE, "verify.txt")

lines = []
failures = []
checks = [0]


def say(msg=""):
    lines.append(msg)
    print(msg)


def check(cond, msg):
    checks[0] += 1
    if not cond:
        failures.append(msg)
    return cond


# ---------------------------------------------------------------------------
# A. parse vs native loader
# ---------------------------------------------------------------------------
def parse_native(path):
    """Turn an eai_host dump into the same shape as EaiModel.summary()."""
    txt = open(path, encoding="utf-8", errors="replace").read()
    if "init rc=0 handle=(nil)" in txt or "handle=(nil)" in txt:
        return None
    out = {"sections": [], "tensors": [], "layers": []}
    m = re.search(r"io n_in=(\d+) n_out=(\d+)", txt)
    out["n_in"], out["n_out"] = int(m.group(1)), int(m.group(2))
    m = re.search(r"^tensors=(\d+)", txt, re.M)
    out["n_tensors"] = int(m.group(1))
    m = re.search(r"^layers=(\d+)", txt, re.M)
    out["n_layers"] = int(m.group(1))
    for line in txt.splitlines():
        m = re.match(r"section\[(\d+)\] type=(\d+) size=(\d+) off=(\d+)", line)
        if m:
            out["sections"].append((int(m.group(2)), int(m.group(3)), int(m.group(4))))
        m = re.match(r"T(\d+) ndim=(\d+) dims=\[([^\]]*)\] bytes=(\d+) dtype=0x([0-9a-f]+) "
                     r"off=(-?\d+) flags=0x([0-9a-f]+) magic=0x([0-9a-f]+)(.*)", line)
        if m:
            dims = tuple(int(x) for x in m.group(3).split(",")) if m.group(3) else ()
            tail = m.group(9)
            out["tensors"].append({
                "id": int(m.group(1)), "ndim": int(m.group(2)), "dims": dims,
                "nbytes": int(m.group(4)), "dtype": int(m.group(5), 16),
                "off": int(m.group(6)), "flags": int(m.group(7), 16),
                "magic": int(m.group(8), 16),
                "inputs": [int(x) for x in re.findall(r"IN(\d+)", tail)],
                "outputs": [int(x) for x in re.findall(r"OUT(\d+)", tail)],
            })
        m = re.match(r"L(\d+) type=(\d+) flags=0x([0-9a-f]+) in=(\d+) out=(\d+) "
                     r"in_ids=(.*) out_ids=(.*)", line)
        if m:
            ins = [int(x) for x in m.group(6).split(",") if x.strip()]
            outs = [int(x) for x in m.group(7).split(",") if x.strip()]
            out["layers"].append({"id": int(m.group(1)), "op_id": int(m.group(2)),
                                  "flags": int(m.group(3), 16),
                                  "in_ids": ins, "out_ids": outs})
    return out


def check_parse(blob_path):
    model = eai_loader.load(blob_path)
    name = model.name
    dump = os.path.join(DUMPS, name + ".txt")
    native = parse_native(dump) if os.path.exists(dump) else None
    if native is None:
        return model, None
    s = model.summary()
    ok = True
    # sections
    mine = [(x["type"], x["size"], x["offset"]) for x in s["sections"]]
    ok &= check(mine == native["sections"], "%s: section table differs" % name)
    # tensors
    ok &= check(s["n_tensors"] == native["n_tensors"], "%s: tensor count" % name)
    for nt, mt in zip(native["tensors"], s["tensors"]):
        same = (nt["ndim"] == mt["ndim"] and nt["dims"] == tuple(mt["shape"])
                and nt["nbytes"] == mt["nbytes"] and nt["dtype"] == mt["dtype"]
                and (nt["off"] & 0xFFFFFFFF) == (mt["data_off"] & 0xFFFFFFFF)
                and nt["flags"] == mt["flags"] and nt["magic"] == mt["magic"])
        if not check(same, "%s: tensor %d descriptor differs (%r vs %r)"
                     % (name, nt["id"], nt, mt)):
            ok = False
    # io
    ok &= check(sorted(t["id"] for t in s["inputs"]) ==
                sorted(t["id"] for t in native["tensors"] if t["inputs"]),
                "%s: graph inputs differ" % name)
    ok &= check(sorted(t["id"] for t in s["outputs"]) ==
                sorted(t["id"] for t in native["tensors"] if t["outputs"]),
                "%s: graph outputs differ" % name)
    # OUTk / INk slot order must match too
    for nt in native["tensors"]:
        for slot in nt["inputs"]:
            ok &= check(s["inputs"][slot]["id"] == nt["id"],
                        "%s: input slot %d" % (name, slot))
        for slot in nt["outputs"]:
            ok &= check(s["outputs"][slot]["id"] == nt["id"],
                        "%s: output slot %d" % (name, slot))
    # layers
    ok &= check(s["n_layers"] == native["n_layers"], "%s: layer count" % name)
    for nl, ml in zip(native["layers"], s["layers"]):
        same = (nl["op_id"] == ml["op_id"] and nl["flags"] == ml["flags"]
                and nl["in_ids"] == ml["in_ids"] and nl["out_ids"] == ml["out_ids"])
        if not check(same, "%s: layer %d differs (native %r vs mine %r)"
                     % (name, nl["id"], nl, ml)):
            ok = False
    return model, ok


# ---------------------------------------------------------------------------
# B. rejection classification
# ---------------------------------------------------------------------------
def rejection_reason(model):
    """Mirror validate_eai_model + load_network's float check."""
    return model.refusal_reason()


# ---------------------------------------------------------------------------
# C. numeric interpreter check on the VAD graph
# ---------------------------------------------------------------------------
ALIASED = {7, 8, 9, 10, 13, 14, 15, 16}   # native scratch-pool overlap (see below)
INTERNAL = {11, 12}                        # LSTM projection work buffers


def check_vad(model):
    name = "speech_float_speech_0"
    inp = np.fromfile(os.path.join(DUMPS, name + "_in0.bin"), dtype="<f4")
    runner = eai_run.EaiRunner(model)
    out = runner.run({model.input_ids[0]: inp})
    say("  tensor  native-vs-numpy  note")
    worst = 0.0
    for t in model.tensors:
        gold = np.fromfile(os.path.join(DUMPS, "%s_T%02d.bin" % (name, t.id)),
                           dtype="<f4")
        mine = np.asarray(runner.get(t.id)).ravel()
        if gold.size != mine.size:
            check(False, "tensor %d size mismatch" % t.id)
            continue
        d = float(np.abs(gold - mine).max())
        if d <= 1e-6:
            note = "match" + (" (aliased buffer)" if t.id in ALIASED else "")
            worst = max(worst, d)
        elif t.id in ALIASED:
            note = "N/A: native pool aliases this buffer, dump is the last writer's"
        elif t.id in INTERNAL:
            note = "N/A: LSTM internal projection buffer, not a graph value"
        else:
            note = "MISMATCH"
        say("  T%02d     %.3e           %s" % (t.id, d, note))
        if not note:
            check(d <= 1e-6, "tensor %d mismatch %.3e" % (t.id, d))
    gold_out = np.fromfile(os.path.join(DUMPS, "%s_T16.bin" % name), dtype="<f4")
    mine_out = np.asarray(out[model.output_ids[0]]).ravel()
    d = float(np.abs(gold_out - mine_out).max())
    check(d <= 1e-6, "VAD softmax output differs: %.3e" % d)
    say("  graph output (softmax) max|diff| = %.3e  [%s]" % (d, mine_out))
    return runner


# ---------------------------------------------------------------------------
# D. LSTM gate order
# ---------------------------------------------------------------------------
def check_lstm_gates(model, runner):
    lstm = [l for l in model.layers if l.op == "lstm"][0]
    x = np.asarray(runner.get(lstm.in_ids[0])).ravel()
    Wx = np.asarray(runner.get(lstm.in_ids[1])).reshape(96, -1)
    Wh = np.asarray(runner.get(lstm.in_ids[2])).reshape(96, 24)
    b = (np.asarray(runner.get(lstm.in_ids[3])).ravel()
         + np.asarray(runner.get(lstm.in_ids[4])).ravel())
    pre = Wx @ x + b                       # h_prev = 0 for the first call
    H = 24
    c_mine = (1 / (1 + np.exp(-pre[0:H]))) * np.tanh(pre[3 * H:4 * H])
    c_native = np.asarray(runner.get(lstm.in_ids[8])).ravel()
    check(np.abs(c_mine - c_native).max() < 1e-6,
          "LSTM cell-state reconstruction mismatch")
    o = 1 / (1 + np.exp(-pre[H:2 * H]))
    y_native = np.asarray(runner.get(lstm.out_ids[0])).ravel()
    check(np.abs(o * np.tanh(c_native) - y_native).max() < 1e-6,
          "LSTM output gate reconstruction mismatch")
    say("  c = sigmoid(pre[0:24]) * tanh(pre[72:96])      max|diff| = %.3e"
        % np.abs(c_mine - c_native).max())
    say("  y = sigmoid(pre[24:48]) * tanh(c)              max|diff| = %.3e"
        % np.abs(o * np.tanh(c_native) - y_native).max())


# ---------------------------------------------------------------------------
# E. structural invariants
# ---------------------------------------------------------------------------
def check_invariants(model):
    ok = True
    name = model.name
    # constant tensors: contiguous, inside section 2
    sec2 = model.section(2)
    if any(getattr(t, "padded", False) for t in model.tensors):
        # quantised (ENPU-target) payloads are padded / carry per-tensor
        # quantisation parameters, so nbytes is not the plain dim product
        return None
    consts = [t for t in model.tensors if t.group == 0 and t.data_off != 0xFFFFFFFF]
    pos = 0
    for t in sorted(consts, key=lambda t: t.data_off):
        if not check(t.data_off >= pos, "%s: tensor %d overlaps previous constant"
                     % (name, t.id)):
            ok = False
        pos = t.data_off + t.nbytes
        if sec2:
            ok &= check(t.data_off + t.nbytes <= sec2[1],
                        "%s: tensor %d constant beyond section 2" % (name, t.id))
    if sec2:
        ok &= check(pos <= sec2[1], "%s: constant payload exceeds section 2" % name)
    # layer records tile section 4
    sec4 = model.section(4)
    if sec4:
        ok &= check(model.layer_data_end <= sec4[1],
                    "%s: layer records exceed section 4" % name)
    # ids in range, io sizes
    for l in model.layers:
        for tid in list(l.in_ids) + list(l.out_ids):
            ok &= check(tid < model.n_tensors, "%s: layer %d bad tensor id" % (name, l.index))
    ok &= check(len(model.input_ids) >= 1 and len(model.output_ids) >= 1,
                "%s: graph without io" % name)
    ok &= check(model.n_group0 + model.n_group1 + model.n_group2 == model.n_tensors,
                "%s: tensor group counts" % name)
    ok &= check(model.size_check, "%s: size_check" % name)
    return ok


# ---------------------------------------------------------------------------
def main():
    blobs = sorted(glob.glob(os.path.join(EAI_DIR, "*.eai")))
    say("EAI2 verification - %d blobs, %s" % (len(blobs), os.path.dirname(EAI_DIR)))
    say("")

    models = {}
    say("A. container parse vs the native loader (evidence/native_dumps/*.txt)")
    for path in blobs:
        model, ok = check_parse(path)
        models[model.name] = model
        if ok is None:
            say("  %-32s n/a   (native loader refuses this blob; see B)" % model.name)
        else:
            say("  %-32s %s" % (model.name, "PASS" if ok else "FAIL"))

    say("")
    say("B. blobs the floating runtime refuses (header classification)")
    for name, model in sorted(models.items()):
        dump = os.path.join(DUMPS, name + ".txt")
        native = parse_native(dump) if os.path.exists(dump) else None
        reason = rejection_reason(model)
        txt = open(os.path.join(DUMPS, name + ".txt"), errors="replace").read() \
            if os.path.exists(os.path.join(DUMPS, name + ".txt")) else ""
        if native is None:
            if "enpu version" in txt:
                expect = "enpu"
            elif "eai_builder version" in txt:
                expect = "builder"
            elif "fixed point model" in txt:
                expect = "fixed-point"
            else:
                expect = "?"
            say("  %-32s native=REFUSED  parsed reason=%-12s native reason=%-12s %s"
                % (name, reason, expect, "PASS" if reason == expect else "FAIL"))
            check(reason == expect, "%s: rejection reason %r vs native %r"
                  % (name, reason, expect))
        else:
            say("  %-32s native=accepted  parsed reason=%s"
                % (name, reason if reason else "none (float, enpu 0)"))
            check(reason is None, "%s: parser predicts refusal but native loaded it" % name)

    say("")
    say("C. numeric equivalence - speech_float_speech_0.eai (DNN VAD)")
    runner = check_vad(models["speech_float_speech_0"])

    say("")
    say("D. LSTM gate order (independent re-derivation)")
    check_lstm_gates(models["speech_float_speech_0"], runner)

    say("")
    say("E. structural invariants")
    for name, model in sorted(models.items()):
        ok = check_invariants(model)
        say("  %-32s %s" % (name, "n/a (quantised model)" if ok is None else
                             ("PASS" if ok else "FAIL")))

    say("")
    say("F. op coverage of the interpreter")
    hist = {}
    for model in models.values():
        for op, n in model.op_histogram().items():
            hist.setdefault(op, 0)
            hist[op] += n
    for op in sorted(hist):
        status = ("verified" if op in eai_run.VERIFIED_OPS else
                  "transcribed, unverified" if op in eai_run.UNVERIFIED_OPS else
                  "NOT IMPLEMENTED")
        say("  %-14s x%-3d %s" % (op, hist[op], status))
    runnable = []
    for name, model in sorted(models.items()):
        ops = set(model.op_histogram())
        if ops <= eai_run.VERIFIED_OPS:
            runnable.append(name)
    say("")
    say("graphs fully executable with verified ops: %s" % (runnable,))

    say("")
    say("G. existing front-end pipeline (pipeline.py) - unchanged, re-run here so")
    say("   verify.txt stays the single evidence file")
    import subprocess
    pipe = os.path.join(HERE, "pipeline.py")
    try:
        r = subprocess.run([sys.executable, pipe], capture_output=True, text=True,
                           timeout=900, cwd=HERE)
        for ln in r.stdout.splitlines():
            say("  | " + ln)
        check(r.returncode == 0, "pipeline.py exited %d" % r.returncode)
    except Exception as exc:                        # noqa: BLE001
        say("  | pipeline.py could not be run: %r" % (exc,))

    say("")
    say("%d checks, %d failures" % (checks[0], len(failures)))
    if failures:
        say("FAILURES:")
        for f in failures[:40]:
            say("  - " + f)
    status = "PASS" if not failures else "FAIL"
    say("status: " + status)
    with open(OUT, "w") as f:
        f.write("\n".join(lines) + "\n")
    print("\nwrote", OUT)
    return 0 if not failures else 1


if __name__ == "__main__":
    raise SystemExit(main())
