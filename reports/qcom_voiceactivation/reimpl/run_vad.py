"""Runnable example: DNN VAD (`assets/speech_float.eai`) with the real weights.

    python reimpl/run_vad.py            # exit 0, prints probabilities

The graph is executed by ``eai_run.EaiRunner`` (ops gemm/relu/no_op/lstm/softmax,
all verified against the native interpreter).  No native code is executed here.

Input contract
--------------
``libdnnvad_jni`` -> ``libqvavad.so`` (CAPI_v2 module) feeds the model one
``1x160`` float feature vector per 10 ms frame.  The 160 numbers are produced by
the qvavad front-end, which is *not* the same table set as the 80-bin mel
front-end in ``pipeline.py`` (that one belongs to libqvasml/udk7 in
libqvajni.so); the exact qvavad feature definition was not decoded, so this
example uses either

  * the oracle input written by ``evidence/harness/eai_host`` (default when the
    native dump is present), or
  * a deterministic synthetic frame (``--synthetic``), so the module always runs.

Decision rule (from the caller, not the model): ``nnvad.threshold`` is
891289 = 0.85 * 2**20, i.e. class-1 probability >= 0.85 means speech.
"""

from __future__ import annotations

import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.dirname(HERE)
EAI = os.path.join(ROOT, "models", "eai", "speech_float_speech_0.eai")
DUMPS = os.path.join(ROOT, "evidence", "native_dumps", "speech_float_speech_0")

THRESHOLD_Q20 = 891289          # qvaconfig_v4.xml nnvad.threshold
THRESHOLD = THRESHOLD_Q20 / float(1 << 20)


def oracle_input() -> np.ndarray:
    """The exact 160-float frame the native harness fed to the model."""
    path = DUMPS + "_in0.bin"
    if os.path.exists(path):
        return np.fromfile(path, dtype="<f4")
    # harness pattern: sin(i*0.017 + tid*1.7) * 0.9 with tid = 0
    i = np.arange(160, dtype=np.float64)
    return (np.sin(i * 0.017) * 0.9).astype(np.float32)


def synthetic_frame() -> np.ndarray:
    """Deterministic stand-in for the missing qvavad front-end."""
    i = np.arange(160, dtype=np.float64)
    return (0.5 * np.sin(2 * np.pi * 0.013 * i) + 0.25 * np.cos(2 * np.pi * 0.041 * i)
            ).astype(np.float32)


def run_vad(feature: np.ndarray) -> np.ndarray:
    import eai_run
    model = eai_run.load_model(EAI)
    runner = eai_run.EaiRunner(model)
    out = runner.run({model.input_ids[0]: np.asarray(feature, np.float32)})
    return np.asarray(out[model.output_ids[0]], np.float32).reshape(-1)


def main(argv) -> int:
    print("model:", os.path.basename(EAI))
    from eai_loader import load as load_model
    m = load_model(EAI)
    print("layers:", [(l.index, l.op) for l in m.layers])
    print("input:", [(t.id, t.shape) for t in m.inputs],
          "output:", [(t.id, t.shape) for t in m.outputs])

    ok = True
    for name, feat in (("oracle-input", oracle_input()),
                       ("synthetic", synthetic_frame())):
        p = run_vad(feat)
        speech = float(p[1])
        print("%-13s -> p(speech)=%.6f p(non-speech)=%.6f  decision=%s"
              % (name, speech, float(p[0]),
                 "SPEECH" if speech >= THRESHOLD else "silence"))
        print("               vector=%s" % np.array2string(feat[:4], precision=5))

    # numeric check against the native engine on the oracle input
    gold_path = DUMPS + "_T16.bin"
    if os.path.exists(gold_path):
        gold = np.fromfile(gold_path, dtype="<f4")
        mine = run_vad(oracle_input())
        d = float(np.abs(gold - mine).max())
        print("native comparison on T16 (softmax output): max|diff| = %.3e" % d)
        if d > 1e-5:
            print("FAIL: differs from the native interpreter")
            ok = False
        else:
            print("PASS: matches libeai_floating.so within float32 noise")
    else:
        print("native dump missing (%s) - comparison skipped" % gold_path)
    return 0 if ok else 1


if __name__ == "__main__":
    sys.path.insert(0, HERE)
    raise SystemExit(main(sys.argv[1:]))
