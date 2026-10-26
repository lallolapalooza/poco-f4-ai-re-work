"""End-to-end on-device VAD pipeline (16 kHz PCM -> speech segments).

Mirrors libmibrainvad2.so:
    PCM -> CornSpectProcessPack (frame/DC/window) -> CornMfccCompute
        -> CornCmvnCompute -> context stacking (CornFeatureInitialize)
        -> Soil network -> CornSmoothPosterior -> CornDetectVoice/CornDecode

Run:  python pipeline.py [model.binn]
Prints every tensor shape and the detected speech segments.
"""
from __future__ import annotations

import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, HERE)

import frontend as fe                      # noqa: E402
import vad_model as vm                     # noqa: E402

SMOOTH_WIN = 5        # conf --smooth-win-width
MIN_VOICE = 50        # conf --min-voice-length (frames)
MIN_SIL = 60          # conf --min-sil-length (frames)


def smooth(posterior, win=SMOOTH_WIN):
    if win <= 1 or posterior.size == 0:
        return posterior
    kernel = np.ones(win, dtype=np.float32) / win
    return np.convolve(posterior, kernel, mode="same")


def decode(posterior, thr=0.5, min_voice=MIN_VOICE, min_sil=MIN_SIL):
    """CornDetectVoice/CornDecode equivalent: hangover state machine (10 ms frames)."""
    segments, start, silence, in_voice = [], 0, 0, False
    for i, p in enumerate(posterior):
        if p >= thr:
            silence = 0
            if not in_voice and i - start >= 0:
                in_voice = True
        else:
            if in_voice:
                silence += 1
                if silence >= min_sil:
                    if i - silence - start + 1 >= min_voice:
                        segments.append((start, i - silence + 1))
                    in_voice, start = False, i + 1
            else:
                start = i + 1
                silence += 1
    if in_voice and len(posterior) - start >= min_voice:
        segments.append((start, len(posterior)))
    return segments


def synthetic_audio(sr=fe.SR):
    """1 s silence + 1.2 s amplitude-modulated noise 'speech' + 1 s silence."""
    rng = np.random.default_rng(7)
    sil1 = np.zeros(int(0.9 * sr), dtype=np.float32)
    n = int(1.2 * sr)
    t = np.arange(n) / sr
    env = (0.5 + 0.5 * np.sin(2 * np.pi * 3.0 * t)) ** 2
    voice = 6000.0 * env * rng.standard_normal(n) * 0.3
    sil2 = np.zeros(int(0.9 * sr), dtype=np.float32)
    return np.concatenate([sil1, voice, sil2]).astype(np.int16)


def run(model_path):
    params = vm.init_params(model_path)
    meta = params["meta"]
    print("model    :", meta["weights_source"])
    print("graph    :", " -> ".join(
        "%s(%s)" % (c["type"], c.get("out_size", c.get("out", "")))
        if c["type"] != "affine" else "affine(%d->%d)" % (c["in"], c["out"])
        for c in meta["components"]))
    print("W1/b1    :", params["W1"].shape, params["b1"].shape)
    print("W3/b3    :", params["W3"].shape, params["b3"].shape)
    print("W7/b7    :", params["W7"].shape, params["b7"].shape)

    pcm = synthetic_audio()
    print("pcm      :", pcm.shape, pcm.dtype, "%.2f s" % (len(pcm) / fe.SR))

    feat = fe.mfcc(pcm)
    print("mfcc     :", feat.shape, "mean %.3f std %.3f" % (feat.mean(), feat.std()))

    container = vm.load_container(model_path)
    feat_n, cmvn_mode = fe.cmvn(feat, container["cmvn"])
    print("cmvn     :", feat_n.shape, "mode=%s" % cmvn_mode)

    x = fe.stack_context(feat_n, left=10, right=10)
    print("stacked  :", x.shape)

    post = np.asarray(vm.posteriors(params, x))
    print("logits   :", np.asarray(vm.apply(params, x)).shape)
    print("posterior:", post.shape, "min %.4f max %.4f" % (post.min(), post.max()))

    post_s = smooth(post)
    segs = decode(post_s)
    print("segments :", segs, "(frames @10 ms)")

    # negative control: pure silence must not fire
    sil = np.zeros(fe.SR, dtype=np.int16)
    f2 = fe.stack_context(fe.cmvn(fe.mfcc(sil), container["cmvn"])[0], 10, 10)
    p2 = np.asarray(vm.posteriors(params, f2))
    print("silence  :", p2.shape, "max %.4f" % p2.max())
    return 0


if __name__ == "__main__":
    default = os.path.join(HERE, "..", "models", "vad_2.1.0_0.binn")
    sys.exit(run(sys.argv[1] if len(sys.argv) > 1 else default))
