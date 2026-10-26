"""Kaldi-style MFCC front-end matching CornVad's configuration.

Configuration recovered from the embedded vad_2.1.0_0 blob (conf/*):
    sample-frequency 16000, use-energy false, num-mel-bins 40, num-ceps 40,
    low-freq 40, high-freq -200 (=> 7800 Hz), left/right context 10.

Constants recovered from CornFeatureInitialize (libmibrainvad2.so @0x32b50):
    sample rate 16000.0 (0x467a0000), frame 25 ms (0x41c80000),
    shift 10 ms (0x41200000), pre-emphasis 0.97 (0x3f7851ec),
    Povey window (switch case 3, exponent 0.85), padded FFT size 512 (0x200),
    frame length 400 samples (0x190).
"""
from __future__ import annotations

import numpy as np

SR = 16000
FRAME_LEN = 400      # 25 ms
FRAME_SHIFT = 160    # 10 ms
N_FFT = 512
N_MELS = 40
N_CEPS = 40
LOW_FREQ = 40.0
HIGH_FREQ = 7600.0   # conf encodes -200 => nyquist + (-200)
PREEMPH = 0.97


def _hz_to_mel(f):
    return 1127.0 * np.log1p(np.asarray(f, dtype=np.float64) / 700.0)


def _mel_to_hz(m):
    return 700.0 * (np.exp(np.asarray(m, dtype=np.float64) / 1127.0) - 1.0)


def mel_filterbank(sr=SR, n_fft=N_FFT, n_mels=N_MELS, low=LOW_FREQ, high=HIGH_FREQ):
    """Kaldi-style triangular filterbank, [n_mels, n_fft//2+1]."""
    n_bins = n_fft // 2 + 1
    fft_freqs = np.arange(n_bins, dtype=np.float64) * sr / n_fft
    mel_pts = np.linspace(_hz_to_mel(low), _hz_to_mel(high), n_mels + 2)
    hz_pts = _mel_to_hz(mel_pts)
    fb = np.zeros((n_mels, n_bins), dtype=np.float64)
    for m in range(n_mels):
        left, centre, right = hz_pts[m], hz_pts[m + 1], hz_pts[m + 2]
        up = (fft_freqs - left) / max(centre - left, 1e-9)
        down = (right - fft_freqs) / max(right - centre, 1e-9)
        fb[m] = np.maximum(0.0, np.minimum(up, down))
    return fb.astype(np.float32)


def dct_matrix(n_ceps=N_CEPS, n_mels=N_MELS):
    """Orthonormal DCT-II basis, [n_ceps, n_mels] (Kaldi `dct_mat`)."""
    k = np.arange(n_ceps)[:, None]
    n = np.arange(n_mels)[None, :]
    mat = np.cos(np.pi * k * (2 * n + 1) / (2.0 * n_mels)) * np.sqrt(2.0 / n_mels)
    mat[0] /= np.sqrt(2.0)
    return mat.astype(np.float32)


def povey_window(n=FRAME_LEN, alpha=0.85):
    i = np.arange(n, dtype=np.float64)
    w = np.power(0.5 - 0.5 * np.cos(2.0 * np.pi * i / (n - 1)), alpha)
    return w.astype(np.float32)


def frames(x, frame_len=FRAME_LEN, shift=FRAME_SHIFT):
    """[T, frame_len] view of a 1-D signal (no padding; trailing samples dropped)."""
    if len(x) < frame_len:
        return np.zeros((0, frame_len), dtype=np.float32)
    n = 1 + (len(x) - frame_len) // shift
    idx = np.arange(frame_len)[None, :] + shift * np.arange(n)[:, None]
    return x[idx]


def mfcc(pcm, sr=SR, dither=0.0, rng=None):
    """pcm: int16 or float samples [N] -> [T, 40] cepstra (no energy, no deltas)."""
    x = np.asarray(pcm, dtype=np.float32)
    if np.issubdtype(np.asarray(pcm).dtype, np.integer):
        x = x.copy()
    if dither:
        rng = rng or np.random.default_rng(0)
        x = x + rng.normal(0.0, dither, size=x.shape).astype(np.float32)

    fr = frames(x)
    if fr.shape[0] == 0:
        return np.zeros((0, N_CEPS), dtype=np.float32)
    fr = fr - fr.mean(axis=1, keepdims=True)              # DC removal (SpectProcessPack)
    fr = np.concatenate([fr[:, :1], fr[:, 1:] - PREEMPH * fr[:, :-1]], axis=1)
    fr = fr * povey_window()[None, :]

    spec = np.fft.rfft(fr, n=N_FFT, axis=1)
    power = (spec.real ** 2 + spec.imag ** 2).astype(np.float32)
    mel = power @ mel_filterbank().T                        # [T, 40]
    logmel = np.log(np.maximum(mel, 1e-10))
    return logmel @ dct_matrix().T                          # [T, 40]


def cmvn(feat, cmvn_values, guard=True):
    """Apply the embedded CMVN statistics (82 values = 2 x 41).

    The last element of each 41-value half is 0.0 and there are only 40 cepstra,
    so the first N_CEPS values of each half are used.  The magnitudes stored in
    the blob (~1e10) do not match log-cepstra, so `guard` falls back to on-line
    mean/variance normalisation when the statistics are implausible for `feat`.
    Returns (normalised_features, mode_used).
    """
    mean = np.asarray(cmvn_values[:41], dtype=np.float64)[: feat.shape[1]]
    var = np.asarray(cmvn_values[41:82], dtype=np.float64)[: feat.shape[1]]
    plausible = (np.abs(mean).max() < 1e3) and (np.abs(var).max() < 1e6)
    if guard and not plausible:
        return ((feat - feat.mean(axis=0, keepdims=True))
                / (feat.std(axis=0, keepdims=True) + 1e-5), "online")
    return (feat - mean) / np.sqrt(np.maximum(var, 1e-10)), "blob"


def stack_context(feat, left=10, right=10):
    """[T, D] -> [T, D*(left+1+right)] with edge replication (CornSpectProcessPack)."""
    t, d = feat.shape
    pad = np.concatenate([np.repeat(feat[:1], left, axis=0), feat,
                          np.repeat(feat[-1:], right, axis=0)], axis=0)
    out = np.empty((t, d * (left + 1 + right)), dtype=feat.dtype)
    for k in range(left + 1 + right):
        out[:, k * d:(k + 1) * d] = pad[k:k + t]
    return out
