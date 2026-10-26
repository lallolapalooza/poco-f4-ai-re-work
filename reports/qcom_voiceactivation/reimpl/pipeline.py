"""JAX reimplementation of the QualcommVoiceActivation (OneVoice) wake-word pipeline.

Two levels of fidelity are kept deliberately separate:

  REAL            front-end + decision logic
                  - 16 kHz / mono / PCM16 input  (AudioRecord(16000, CHANNEL_IN_MONO, PCM16))
                  - 480-sample analysis frame, hop 160 (30 ms / 10 ms)
                  - Hann window of length 400 (symmetric, zero padded to 480) --
                    extracted verbatim from libqvasml.so .data @ udk_hann_window
                  - 512-point FFT, magnitude sqrt(re^2+im^2) for bins 0..255
                    (udk7_q_complex_vector_mag_f)
                  - 80 triangular mel filters over bins 5..243, stored sparsely
                    (start/len/offset + 470 taps) -- extracted verbatim from
                    libqvasml.so .data @ udk_mel_filter_{start,len,offset,80}
                  - decision thresholds / tolerances from assets/qvaconfig_v4.xml

  ARCHITECTURE_ONLY
                  - first-stage keyword net (PDK5_XS / PDK6_XS.MFCN, EAI2 container)
                  - middle (1.5) stage keyword net
                  - DNN VAD net (assets/speech_float.eai)
                  - second-stage user-verification scorer
                  These helpers keep deterministic random weights and are kept
                  only to exercise the plumbing.  The real networks are in
                  eai_run.py: the EAI2 container is decoded (eai_loader.py) and
                  the DNN VAD runs on its shipped weights, bit-verified against
                  libeai_floating.so (verify_eai.py / GAP_CLOSURE.md).

Run:  python pipeline.py            (writes verify.txt next to this file)
"""

from __future__ import annotations

import json
import os
import sys

import numpy as np

import jax
import jax.numpy as jnp

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.dirname(HERE)
FE = os.path.join(ROOT, "models", "frontend")

SAMPLE_RATE = 16000
FRAME_LEN = 480          # samples analysed per frame (30 ms)
HOP = 160                # samples advanced per frame (10 ms)
NFFT = 512
N_BINS = 256             # unique bins of the real FFT
N_MEL = 80

# ---------------------------------------------------------------------------
# front-end parameters (REAL: extracted from libqvasml.so)
# ---------------------------------------------------------------------------


def load_frontend_params():
    """Hann window (480) and dense mel matrix (80 x 256)."""
    hann = np.load(os.path.join(FE, "hann_480.npy")).astype(np.float32)
    mel = np.load(os.path.join(FE, "mel_matrix_80x256.npy")).astype(np.float32)
    assert hann.shape == (FRAME_LEN,), hann.shape
    assert mel.shape == (N_MEL, N_BINS), mel.shape
    return hann, mel


def pc16_to_float(pcm: np.ndarray) -> np.ndarray:
    """short -> [-1, 1).  feature_spectrum multiplies by 1/32768 (0x38000000)."""
    return pcm.astype(np.float32) * np.float32(1.0 / 32768.0)


def frames_of(x: np.ndarray) -> np.ndarray:
    """Slice into 480-sample frames, hop 160; zero pad the tail."""
    n = 0 if len(x) < FRAME_LEN else 1 + (len(x) - FRAME_LEN) // HOP
    out = np.zeros((n, FRAME_LEN), np.float32)
    for i in range(n):
        out[i] = x[i * HOP: i * HOP + FRAME_LEN]
    return out


def logmel(pcm: np.ndarray, hann, mel, eps=1e-6):
    """PCM16 -> (frames, 80) mel magnitudes, matching udk7_feature_process().

    No logarithm is applied inside the native front-end (only log10 appears in
    EPD / noise mixing code), so this returns linear mel magnitudes.  A
    ``log`` variant is provided for the ARCHITECTURE_ONLY networks, which are
    documented as taking log-mel.
    """
    x = pc16_to_float(pcm) if pcm.dtype == np.int16 else pcm.astype(np.float32)
    fr = frames_of(x) * hann[None, :]                       # windowed frames
    spec = np.fft.rfft(fr, n=NFFT, axis=1)[:, :N_BINS]      # 512-pt real FFT
    mag = np.abs(spec).astype(np.float32)                   # sqrt(re^2+im^2)
    feats = mag @ mel.T                                     # sparse dot products
    return feats


def logmel_jax(pcm, hann, mel, eps=1e-6):
    """Same front-end expressed in JAX (differentiable, no python loop)."""
    int_input = np.issubdtype(np.asarray(pcm).dtype, np.integer)
    x = jnp.asarray(pcm, dtype=jnp.float32)
    if int_input:
        x = x / 32768.0
    n = jnp.maximum(1, 1 + (x.shape[0] - FRAME_LEN) // HOP)
    idx = jnp.arange(n)[:, None] * HOP + jnp.arange(FRAME_LEN)[None, :]
    fr = x[jnp.clip(idx, 0, x.shape[0] - 1)] * hann[None, :]
    spec = jnp.fft.rfft(fr, n=NFFT, axis=1)[:, :N_BINS]
    mag = jnp.abs(spec)
    return mag @ mel.T


# ---------------------------------------------------------------------------
# ARCHITECTURE_ONLY networks
# ---------------------------------------------------------------------------
# Shapes follow the evidence in the APK:
#   * 1st stage: 5 keywords       ("fivekeywords_us", FirstStageKW.thresholds = 5 values)
#   * 1.5 stage: 5 keywords       (MiddleStageKW.thresholds = 5 values)
#   * DNN VAD  : 1 speech logit   (nnvad.threshold, hangover/future in frames)
#   * 2nd stage: 1 UV score/keyword (SecondStageUV.threshold.KW.0..4)
# The convolution/MLP topology itself is NOT recovered from the EAI2 blob, so the
# weights below are deterministic pseudo-random values (jax.random), not trained.

VAD_CONTEXT = 5           # frames of 80-mel context feeding the VAD
KW_CONTEXT = 40           # 400 ms of 80-mel frames feeding the keyword net
N_KEYWORDS = 5


def _key(seed):
    return jax.random.key(seed)


def init_vad_params(seed: int = 0):
    k1, k2 = jax.random.split(_key(seed))
    d = VAD_CONTEXT * N_MEL
    return {
        "w1": jax.random.normal(k1, (d, 64)) * (1.0 / np.sqrt(d)),
        "b1": jnp.zeros((64,)),
        "w2": jax.random.normal(k2, (64, 1)) * (1.0 / np.sqrt(64)),
        "b2": jnp.zeros((1,)),
    }


def vad_apply(params, x):
    """x: (frames, 80) log-mel -> (frames, ) speech probability."""
    x = jnp.asarray(x)
    pad = jnp.zeros((VAD_CONTEXT // 2, x.shape[1]), x.dtype)
    xp = jnp.concatenate([pad, x, pad], axis=0)
    ctx = jnp.stack([xp[i:i + x.shape[0]] for i in range(VAD_CONTEXT)], axis=-1)
    ctx = ctx.reshape(x.shape[0], -1)
    h = jax.nn.relu(ctx @ params["w1"] + params["b1"])
    return jax.nn.sigmoid((h @ params["w2"] + params["b2"])[:, 0])


def init_keyword_params(seed: int = 1, context: int = KW_CONTEXT, n_out: int = N_KEYWORDS):
    k1, k2 = jax.random.split(_key(seed))
    d = context * N_MEL
    return {
        "w1": jax.random.normal(k1, (d, 128)) * (1.0 / np.sqrt(d)),
        "b1": jnp.zeros((128,)),
        "w2": jax.random.normal(k2, (128, n_out)) * (1.0 / np.sqrt(128)),
        "b2": jnp.zeros((n_out,)),
    }


def keyword_apply(params, x, context: int = KW_CONTEXT):
    """x: (frames, 80) log-mel -> (frames - context + 1, n_keywords) logits."""
    x = jnp.asarray(x)
    xp = jnp.concatenate([jnp.zeros((context - 1, x.shape[1]), x.dtype), x], axis=0)
    win = jnp.stack([xp[i:i + x.shape[0]] for i in range(context)], axis=-1)
    win = win.reshape(x.shape[0], -1)
    h = jax.nn.relu(win @ params["w1"] + params["b1"])
    return h @ params["w2"] + params["b2"]


# ---------------------------------------------------------------------------
# decision logic (REAL thresholds from assets/qvaconfig_v4.xml)
# ---------------------------------------------------------------------------

DEFAULTS = {
    "FirstStageKW.threshold": 26,
    "MiddleStageKW.thresholds": [50, 52, 49, 37, 35],
    "SecondStageUV.threshold": 55.0,
    "SecondStageUV.threshold.kwcmd": 45.0,
    "SecondStageUV.threshold.KW": [55.0] * 5,
    "Enrollment.threshold": 30,
    "KW.preTolerance": 400,      # ms
    "KW.postTolerance": 400,     # ms
    "UV.preTolerance": 200,      # ms
    "UV.postTolerance": 200,     # ms
    "nnvad.threshold.q20": 891289,          # 0.85 in Q20
    "nnvad.len.hangover": 70,               # frames (10 ms)
    "nnvad.len.future": 0,
    "cmd.end.detection.threshold": 30,      # frames
    "cmd.end.detection.timeout": 4000,      # ms
}


def vad_gate(prob, threshold_q20=891289, hangover=70, future=0):
    """DNN VAD state machine: threshold (Q20) + hangover + lookahead."""
    thr = np.float32(threshold_q20) / np.float32(1 << 20)
    p = np.asarray(prob)
    on = p >= thr
    if future:
        on = np.array([on[i:i + future + 1].any() for i in range(len(on))])
    out = np.zeros_like(on)
    hold = 0
    for i, v in enumerate(on):
        if v:
            hold = hangover
            out[i] = True
        elif hold > 0:
            hold -= 1
            out[i] = True
    return out


def detect(logmel_feats, vad_params, kw_params, mid_params, cfg=None):
    """Frame-wise pipeline: log-mel -> VAD gate -> 1st stage -> 1.5 stage.

    Returns dict of per-frame arrays plus the keyword index/score that would be
    reported to the framework (1st stage thresholding, middle-stage refine).
    """
    cfg = {**DEFAULTS, **(cfg or {})}
    lf = np.log(np.maximum(np.asarray(logmel_feats), 1e-6))
    # per-utterance mean/variance normalisation (INFERRED: the exact normalisation
    # used in front of the EAI networks was not recovered; qvaconfig has no key)
    lf = (lf - lf.mean()) / (lf.std() + 1e-6)
    p_vad = np.asarray(vad_apply(vad_params, lf))
    speech = vad_gate(p_vad, cfg["nnvad.threshold.q20"], cfg["nnvad.len.hangover"],
                      cfg["nnvad.len.future"])
    kw = np.asarray(keyword_apply(kw_params, lf))
    mid = np.asarray(keyword_apply(mid_params, lf))
    first_hit = kw.max(axis=1) >= np.float32(cfg["FirstStageKW.threshold"]) / 100.0
    mid_thr = np.asarray(cfg["MiddleStageKW.thresholds"], np.float32) / 100.0
    mid_hit = (mid >= mid_thr[None, :]).any(axis=1)
    return {
        "vad_prob": p_vad,
        "speech": speech,
        "kw_logits": kw,
        "kw_scores": 1.0 / (1.0 + np.exp(-kw)),
        "mid_logits": mid,
        "mid_scores": 1.0 / (1.0 + np.exp(-mid)),
        "first_stage_hit": first_hit,
        "middle_stage_hit": mid_hit,
    }


# ---------------------------------------------------------------------------
# self test
# ---------------------------------------------------------------------------


def synth_audio(seconds=2.0, seed=7):
    """Synthetic utterance: 300 Hz..3 kHz chirp bursts + white noise floor."""
    rng = np.random.default_rng(seed)
    n = int(SAMPLE_RATE * seconds)
    t = np.arange(n) / SAMPLE_RATE
    env = (np.sin(2 * np.pi * 1.5 * t) > 0).astype(np.float32)
    env = np.convolve(env, np.ones(400) / 400, mode="same")
    f0 = 300 + 900 * (t / seconds)
    sig = 0.35 * np.sin(2 * np.pi * np.cumsum(f0) / SAMPLE_RATE)
    sig += 0.1 * np.sin(2 * np.pi * 2400 * t)
    x = sig * env + 0.01 * rng.standard_normal(n)
    return np.clip(x * 32767, -32768, 32767).astype(np.int16)


def main():
    hann, mel = load_frontend_params()
    hann_j = jnp.asarray(hann)
    mel_j = jnp.asarray(mel)

    pcm = synth_audio()
    feats_np = logmel(pcm, hann, mel)
    feats_j = np.asarray(logmel_jax(pcm, hann_j, mel_j))
    fe_err = float(np.abs(feats_np - feats_j).max())

    vad_p = init_vad_params(0)
    kw_p = init_keyword_params(1)
    mid_p = init_keyword_params(2)

    out = detect(feats_np, vad_p, kw_p, mid_p)

    lines = []
    lines.append("qcom_voiceactivation / OneVoice JAX reimplementation")
    lines.append("command: python reimpl/pipeline.py")
    lines.append("jax %s  numpy %s" % (jax.__version__, np.__version__))
    lines.append("")
    lines.append("front-end (REAL parameters extracted from libqvasml.so):")
    lines.append("  sample_rate=%d frame=%d hop=%d nfft=%d bins=%d mel=%d"
                 % (SAMPLE_RATE, FRAME_LEN, HOP, NFFT, N_BINS, N_MEL))
    lines.append("  hann_len=%d (support %d) mel_nnz=%d bin_range=%d..%d"
                 % (len(hann), int((hann > 0).sum()), int((mel > 0).sum()),
                    int(np.nonzero(mel.any(0))[0][0]), int(np.nonzero(mel.any(0))[0][-1])))
    lines.append("  input pcm: int16 %d samples (%.2f s)" % (len(pcm), len(pcm) / SAMPLE_RATE))
    lines.append("  mel features: %s" % (feats_np.shape,))
    lines.append("  numpy-vs-jax front-end max abs diff: %.3e" % fe_err)
    lines.append("")
    lines.append("stage shapes (ARCHITECTURE_ONLY weights):")
    lines.append("  vad_prob      %s" % (out["vad_prob"].shape,))
    lines.append("  speech(gated) %s  frames=%d" % (out["speech"].shape, int(out["speech"].sum())))
    lines.append("  kw_logits     %s" % (out["kw_logits"].shape,))
    lines.append("  mid_logits    %s" % (out["mid_logits"].shape,))
    lines.append("")
    lines.append("decision logic (REAL config values):")
    lines.append("  nnvad.threshold=891289/2^20=%.4f hangover=%d future=%d"
                 % (891289 / (1 << 20), 70, 0))
    lines.append("  FirstStageKW.threshold=%d MiddleStageKW.thresholds=%s"
                 % (DEFAULTS["FirstStageKW.threshold"], DEFAULTS["MiddleStageKW.thresholds"]))
    lines.append("  first_stage_hit frames=%d middle_stage_hit frames=%d"
                 % (int(out["first_stage_hit"].sum()), int(out["middle_stage_hit"].sum())))
    lines.append("")
    lines.append("status: runs end-to-end; front-end verified against JAX transcription.")
    lines.append("        The DNN stages in THIS module use random weights; the real EAI2")
    lines.append("        weights are executed by eai_run.py (DNN VAD verified natively,")
    lines.append("        see verify_eai.py and GAP_CLOSURE.md).")
    lines.append("exit: 0")

    txt = "\n".join(lines) + "\n"
    with open(os.path.join(HERE, "verify.txt"), "w") as f:
        f.write(txt)
    print(txt)
    return 0


if __name__ == "__main__":
    sys.exit(main())
