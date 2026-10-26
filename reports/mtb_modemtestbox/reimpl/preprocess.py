"""Java-exact preprocessing for com.xiaomi.mtb 0.3_new_0410_less.tflite.

Source of truth: com.xiaomi.mtb.aiservices.AiWeakSignalForecastService
(makeInputFromInfoItem / medianFilter / median / quickSort), as recovered by
jadx.  Input is NOT audio: it is 100 x 100 ms LTE/NR radio measurements.

Java layout (float input[1][400][1]):
    [0][  0:100][0] = medianFilter(RSRP, 5)
    [0][100:200][0] = medianFilter(RSRQ, 5)
    [0][200:300][0] = medianFilter(SINR, 5)
    [0][300:400][0] = PCI-change indicator (1.0 if PCI changed, else 0.0)

No scaling/normalization is applied anywhere in Java: raw dB values are fed
to the float32 input tensor.
"""

from __future__ import annotations

import numpy as np

WINDOW = 5
CHANNELS = ("rsrp", "rsrq", "sinr", "pci_changed")


def java_median(values: np.ndarray) -> float:
    """Exact port of AiWeakSignalForecastService.median().

    Java sorts the supplied array in place with its own quickSort; the value
    returned is the middle element, or the mean of the two middle elements for
    an even-length slice.
    """
    a = np.sort(np.asarray(values, dtype=np.float32))
    n = a.size
    if n == 0:
        raise ValueError("median of empty array")
    mid = n // 2
    if n % 2 == 0:
        return float((a[mid - 1] + a[mid]) * 0.5)
    return float(a[mid])


def java_median_filter(values: np.ndarray, window: int = WINDOW) -> np.ndarray:
    """Exact port of AiWeakSignalForecastService.medianFilter().

    Java clips the window asymmetrically at the array edges:
      i <= w//2            -> values[0 : i + w//2 + 1]
      i + w//2 >= length   -> values[i - w//2 : length]
      otherwise            -> values[i - w//2 : i + w//2 + 1]
    so the first/last samples use 3, 4, ... point windows, not a replicated
    edge padding.
    """
    src = np.asarray(values, dtype=np.float32)
    n = src.size
    half = window // 2
    if window > n:
        raise ValueError("window larger than array")
    out = np.empty(n, dtype=np.float32)
    for i in range(n):
        if i <= half:
            lo, hi = 0, i + half + 1
        elif i + half >= n:
            lo, hi = i - half, n
        else:
            lo, hi = i - half, i + half + 1
        out[i] = java_median(src[lo:hi])
    return out


def make_input(series: np.ndarray) -> np.ndarray:
    """Build the (1, 400, 1) float32 model input from 100 measurements.

    `series`: array-like of shape (100, 4) with columns
              [rsrp_dbm, rsrq_db, sinr_db, pci_changed].
    Returns float32 (1, 400, 1), C-contiguous, ready for the interpreter.
    """
    s = np.asarray(series, dtype=np.float32)
    if s.shape != (100, 4):
        raise ValueError(f"expected series shape (100, 4), got {s.shape}")
    x = np.zeros((1, 400, 1), dtype=np.float32)
    x[0, 0:100, 0] = java_median_filter(s[:, 0])
    x[0, 100:200, 0] = java_median_filter(s[:, 1])
    x[0, 200:300, 0] = java_median_filter(s[:, 2])
    x[0, 300:400, 0] = s[:, 3]
    return x


def classify(probabilities: np.ndarray) -> str:
    """Java threshold: output[0][1] < 0.5 -> WEAK else STRONG."""
    p = np.asarray(probabilities).reshape(-1)
    return "WEAK" if p[1] < 0.5 else "STRONG"


def synthetic_series(kind: str = "degrading", seed: int = 0) -> np.ndarray:
    """Deterministic 100-sample measurement series for end-to-end runs."""
    rng = np.random.default_rng(seed)
    t = np.linspace(0.0, 1.0, 100, dtype=np.float32)
    if kind == "degrading":
        rsrp = -85.0 - 30.0 * t
        rsrq = -8.0 - 7.0 * t
        sinr = 18.0 - 20.0 * t
    elif kind == "improving":
        rsrp = -115.0 + 35.0 * t
        rsrq = -16.0 + 8.0 * t
        sinr = -2.0 + 22.0 * t
    elif kind == "strong":
        rsrp = -70.0 + rng.normal(0, 1.5, 100)
        rsrq = -5.0 + rng.normal(0, 0.7, 100)
        sinr = 25.0 + rng.normal(0, 1.5, 100)
    elif kind == "weak":
        rsrp = -118.0 + rng.normal(0, 3.0, 100)
        rsrq = -17.0 + rng.normal(0, 1.0, 100)
        sinr = -3.0 + rng.normal(0, 1.5, 100)
    else:
        raise ValueError(f"unknown series kind {kind!r}")
    rsrp = rsrp + rng.normal(0, 0.8, 100)
    rsrq = rsrq + rng.normal(0, 0.4, 100)
    sinr = sinr + rng.normal(0, 0.8, 100)
    pci_changed = np.zeros(100, dtype=np.float32)
    if kind in ("degrading", "improving"):
        pci_changed[[33, 67]] = 1.0
    else:
        pci_changed[rng.random(100) < 0.03] = 1.0
    return np.stack([rsrp, rsrq, sinr, pci_changed], axis=1).astype(np.float32)
