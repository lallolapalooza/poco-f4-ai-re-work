#!/usr/bin/env python3
"""Runnable JAX reimplementation of the MiPay digit-OCR classifier stage.

  python pipeline.py            # synthetic checks, prints shapes, exits 0

Stages implemented (all with the real shipped LIBSVM parameters):
    patch (32x20 grayscale)  ->  HOG 144  ->  10-class SVM (+ bin "is digit")
    ->  digit + confidence(0..100) + top-3 candidates, exactly like
        CDigitalOCR::Predict / PredictNegPos in libdigit_ocr.so.

The segmentation stage (DigitSegment: rgbToGray -> adaptiveThreshold ->
connected components -> 1:3.5 aspect filter -> slant/RANSAC -> 20x32
normalisation) is *not* reimplemented; it is documented in report.md and its
output contract (20x32 grayscale patch) is what this module consumes.
"""
import os
import sys

import numpy as np
import jax
import jax.numpy as jnp

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

import hog
import jax_model as J
from lsvm import SvmModel

HERE = os.path.dirname(os.path.abspath(__file__))
MODELS = os.path.join(HERE, "..", "models")
M9 = os.path.join(MODELS, "Digital_OCR_svm_9.model")
M2 = os.path.join(MODELS, "Digital_OCR_svm_2.model")

# 5x7 bitmap font, only used to synthesise test patches
FONT = {
    "0": ["01110", "10001", "10011", "10101", "11001", "10001", "01110"],
    "1": ["00100", "01100", "00100", "00100", "00100", "00100", "01110"],
    "2": ["01110", "10001", "00001", "00010", "00100", "01000", "11111"],
    "3": ["11111", "00010", "00100", "00010", "00001", "10001", "01110"],
    "4": ["00010", "00110", "01010", "10010", "11111", "00010", "00010"],
    "5": ["11111", "10000", "11110", "00001", "00001", "10001", "01110"],
    "6": ["00110", "01000", "10000", "11110", "10001", "10001", "01110"],
    "7": ["11111", "00001", "00010", "00100", "01000", "01000", "01000"],
    "8": ["01110", "10001", "10001", "01110", "10001", "10001", "01110"],
    "9": ["01110", "10001", "10001", "01111", "00001", "00010", "01100"],
}


def render_digit(ch, size=(28, 28)):
    """Render one digit into a `size` grayscale image (white bg, black glyph)."""
    h, w = size
    img = np.full((h, w), 255, np.uint8)
    g = np.array([[c == "1" for c in row] for row in FONT[ch]])
    gh, gw = g.shape
    sy = h / gh
    sx = w / gw
    for y in range(h):
        for x in range(w):
            if g[min(int(y / sy), gh - 1), min(int(x / sx), gw - 1)]:
                img[y, x] = 0
    return img


def main():
    print("== MiPay digit OCR — JAX reimplementation ==")
    print("jax", jax.__version__, "backend", jax.default_backend())

    p9 = J.load_params(M9)
    p2 = J.load_params(M2)
    print(f"model9: sv={p9['sv'].shape} coef={p9['sv_coef'].shape} rho={p9['rho'].shape} "
          f"nr_class={p9['nr_class']} gamma={float(p9['gamma'])} kernel={p9['kernel']}")
    print(f"model2: sv={p2['sv'].shape} coef={p2['sv_coef'].shape} rho={p2['rho'].shape} "
          f"nr_class={p2['nr_class']} gamma={float(p2['gamma'])} kernel={p2['kernel']}")

    # ---- 1. HOG on a synthetic 28x28 / 32x32 digit, resized to the 20x32 patch
    for size in [(28, 28), (32, 32)]:
        img = render_digit("7", size=size)
        patch = J.resize_to_patch(img)
        feat = J.hog_features(patch.reshape(1, hog.H, hog.W))
        print(f"hog: input {size} -> patch {tuple(patch.shape)} -> features {tuple(feat.shape)} "
              f"sum={float(jnp.sum(feat)):.3f} (640 px / 40)")
        np.testing.assert_allclose(np.asarray(feat)[0], hog.extract(patch), atol=1e-6)

    # ---- 2. decision values / probabilities / label
    patch = J.resize_to_patch(render_digit("4", size=(32, 32)))
    feat = J.hog_features(patch.reshape(1, hog.H, hog.W))
    dec = J.decision_values(p9, feat)
    prob = J.predict_proba(p9, feat)
    print(f"decision_values: {tuple(dec.shape)}  (nr_class*(nr_class-1)/2 = 45)")
    print(f"predict_proba : {tuple(prob.shape)}  sum={float(prob.sum()):.6f}")
    print("  probs:", np.round(np.asarray(prob)[0], 6))
    print("  argmax digit:", int(np.argmax(np.asarray(prob)[0])))

    # reference cross-check with the numpy libsvm port on the same features
    m9 = SvmModel(M9)
    ref = m9.predict_proba(np.asarray(feat))
    err = np.abs(ref - np.asarray(prob)).max()
    print(f"numpy-libsvm cross-check max|diff| = {err:.3e} (float32 JAX vs float64 reference)")
    assert err < 2e-4

    # ---- 3. full per-digit classifier contract (top-3 + confidence + is_digit)
    #         run on the *vendor's own* normalised patches for the synthetic
    #         "1234" card image (dumped by the qemu harness from
    #         DigitSegmentManagement::getDigitMatBuffer, 4 x 20x32 bytes).
    print("classify (vendor 20x32 patches -> digit, conf, candidates, is_digit):")
    patch_file = os.path.join(HERE, "..", "evidence", "patches", "digits1234.patches.bin")
    if os.path.exists(patch_file):
        buf = np.frombuffer(open(patch_file, "rb").read(), np.uint8)
        patches = buf.reshape(-1, hog.H, hog.W)
        out = []
        for i, p in enumerate(patches):
            digit, conf, cands, is_digit = J.classify_digit(p9, p2, p)
            out.append(str(digit))
            print(f"  patch{i}: pred={digit} conf={conf} cands={cands} is_digit={is_digit}")
        text = "".join(out)
        print("recognised string:", text, "(vendor MainOCR gave 1234)")
        assert text == "1234", text
    else:
        print("  (evidence/patches/digits1234.patches.bin missing, skipped)")

    # ---- 4. cross-check against the vendor binary itself (captured by the
    #         qemu-aarch64 harness in native/harness, see native/README.md)
    ev = os.path.join(HERE, "..", "evidence", "hog")
    checked = 0
    if os.path.isdir(ev):
        for name in sorted(os.listdir(ev)):
            if not name.endswith(".cls.txt"):
                continue
            tag = name[:-len(".cls.txt")]
            lines = [l for l in open(os.path.join(ev, name)) if l.startswith(("feat:", "probs:"))]
            if len(lines) < 2:
                continue
            feat_native = np.array([float(x) for x in lines[0].split(":")[1].split()])
            prob_native = np.array([float(x) for x in lines[1].split(":")[1].split()])
            prob_jax = np.asarray(J.predict_proba(p9, jnp.asarray(feat_native, jnp.float32)))[0]
            d = np.abs(prob_native - prob_jax).max()
            print(f"vendor-vs-jax [{tag}]: max|dprob| = {d:.3e}")
            assert d < 1e-5, (tag, d)
            checked += 1
    print(f"vendor cross-checks passed: {checked}")

    print("OK: shapes and values verified, exit 0")
    return 0


if __name__ == "__main__":
    sys.exit(main())
