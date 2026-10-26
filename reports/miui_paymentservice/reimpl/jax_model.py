"""JAX reimplementation of the MiPay digit-OCR classifier stage.

Recovered from libdigit_ocr.so (arm64, com.xiaomi.payment 1.12.13-global):

  DigitSegment normalises every segmented glyph to a 20x32 grayscale patch
  (CDigitalOCR::Predict calls CFEATURE_HOG::ExtractGradientOrientation(patch,
  20, 32, &feat)), then

      feat[144]  = HOG, 4x4 cells of 5x8 px, 9 orientation bins of 40 deg,
                   cell-histogram counts / 40 (cell area)
      digit      = argmax_c svm_predict_probability(model9, feat)
                   (10-class C-SVC, RBF gamma=2.0, 1076 SVs, LIBSVM text)
      is_digit   = svm_predict_probability(model2, feat)[0] > 0.5
                   (binary C-SVC, RBF gamma=0.5, 545 SVs)
      confidence = int(prob_best * 100)
      candidates = best, plus 2nd/3rd when their probability > 0.1

  All weights below are the real shipped parameters (assets/*.model parsed by
  lsvm.SvmModel); nothing is random-initialised.
"""
import numpy as np
import jax

# libsvm works in double precision; mirror that so the pairwise-coupling
# iteration is bit-comparable with the vendor build.  The HOG feature vector
# itself stays float32, exactly like the vendor's std::vector<float>.
jax.config.update("jax_enable_x64", True)
import jax.numpy as jnp

from hog import W, H, N_FEATURES, extract as np_hog
from lsvm import SvmModel

MAX_ITER_COUPLING = 100


# ---------------------------------------------------------------- parameters
def load_params(path):
    """Parse a LIBSVM text model into a dict of jnp arrays."""
    m = SvmModel(path)
    return {
        "sv": jnp.asarray(m.sv, dtype=jnp.float64),
        "sv_coef": jnp.asarray(m.sv_coef, dtype=jnp.float64),
        "rho": jnp.asarray(m.rho, dtype=jnp.float64),
        "probA": jnp.asarray(m.probA, dtype=jnp.float64),
        "probB": jnp.asarray(m.probB, dtype=jnp.float64),
        "start": jnp.asarray(m.start, dtype=jnp.int32),
        "nsv": jnp.asarray(m.nr_sv, dtype=jnp.int32),
        "label": jnp.asarray(m.label, dtype=jnp.int32),
        "gamma": jnp.float64(m.gamma),
        "nr_class": m.nr_class,
        "n_sv": m.total_sv,
        "kernel": m.kernel_type,
        "svm_type": m.svm_type,
    }


# ---------------------------------------------------------------- HOG (JAX)
def hog_features(img):
    """img: (B, 32, 20) uint8/float -> (B, 144) float32 digit features."""
    x = jnp.asarray(img)
    if x.ndim == 2:
        x = x[None]
    b = jnp.where(x > 80, 255.0, 0.0)                      # 0x50 threshold
    gx = jnp.zeros_like(b).at[:, :, 1:].set(b[:, :, 1:] - b[:, :, :-1])
    gy = jnp.zeros_like(b).at[:, 1:, :].set(b[:, 1:, :] - b[:, :-1, :])
    ang = jnp.degrees(jnp.arctan2(gy, gx)) + 180.0
    ang = jnp.where(ang >= 360.0, ang - 360.0, ang)

    ys, xs = jnp.meshgrid(jnp.arange(H), jnp.arange(W), indexing="ij")
    cell = (xs // 5) + (ys // 8) * (W // 5)                # (32,20) in 0..15
    obin = jnp.clip((ang / 40.0).astype(jnp.int32), 0, 8)
    bins = (cell[None] * 9 + obin).reshape(b.shape[0], -1)
    hist = jax.vmap(lambda idx: jnp.bincount(idx, length=N_FEATURES).astype(jnp.float32))(bins)
    return hist / 40.0


# ---------------------------------------------------------------- SVM (JAX)
def _kernel(params, x):
    """RBF kernel between rows of x (B,144) and the support vectors."""
    x = x.astype(jnp.float64)
    xn = jnp.sum(x * x, axis=1)[:, None]
    yn = jnp.sum(params["sv"] * params["sv"], axis=1)[None, :]
    d = xn + yn - 2.0 * (x @ params["sv"].T)
    d = jnp.maximum(d, 0.0)
    return jnp.exp(-params["gamma"] * d)


def decision_values(params, x):
    """libsvm one-vs-one decision values, layout (B, nr_class*(nr_class-1)/2)."""
    x = jnp.atleast_2d(x)
    K = _kernel(params, x)
    k = params["nr_class"]
    coef, start, nsv = params["sv_coef"], params["start"], params["nsv"]
    outs = []
    for i in range(k):
        for j in range(i + 1, k):
            si, sj = int(start[i]), int(start[j])
            ni, nj = int(nsv[i]), int(nsv[j])
            s = K[:, si:si + ni] @ coef[si:si + ni, j - 1]
            s = s + K[:, sj:sj + nj] @ coef[sj:sj + nj, i]
            outs.append(s - params["rho"][len(outs)])
    return jnp.stack(outs, axis=1)


def _sigmoid_predict(dec, A, B):
    f = dec * A + B
    return jnp.where(f >= 0, jnp.exp(-f) / (1.0 + jnp.exp(-f)), 1.0 / (1.0 + jnp.exp(f)))


def _couple(Q, p0):
    """libsvm multiclass_probability (Wu/Lin/Weng) as a fixed-shape while_loop."""
    k = Q.shape[0]
    eps = 0.005 / k

    def cond(c):
        it, p, Qp, pQp, err = c
        return (it < MAX_ITER_COUPLING) & (err >= eps)

    def body(c):
        it, p, Qp, pQp, err = c
        Qp = Q @ p
        pQp = jnp.sum(p * Qp)
        err = jnp.max(jnp.abs(Qp - pQp))

        def update(carry):
            p, Qp, pQp = carry
            diag = jnp.diag(Q)
            diff = (-Qp + pQp) / diag
            p = p + diff
            pQp = (pQp + diff * (diff * diag + 2.0 * Qp)) / (1.0 + diff) / (1.0 + diff)
            Qp = (Qp + diff * Q) / (1.0 + diff)
            p = p / (1.0 + diff)
            return p, Qp, pQp

        # the libsvm update is sequential over t: emulate with scan
        def step(carry, t):
            p, Qp, pQp = carry
            diff = (-Qp[t] + pQp) / Q[t, t]
            p = p.at[t].add(diff)
            pQp = (pQp + diff * (diff * Q[t, t] + 2.0 * Qp[t])) / (1.0 + diff) / (1.0 + diff)
            Qp = (Qp + diff * Q[t, :]) / (1.0 + diff)
            p = p / (1.0 + diff)
            return (p, Qp, pQp), None

        (p, Qp, pQp), _ = jax.lax.scan(step, (p, Qp, pQp), jnp.arange(k))
        Qp = Q @ p
        pQp = jnp.sum(p * Qp)
        err = jnp.max(jnp.abs(Qp - pQp))
        return (it + 1, p, Qp, pQp, err)

    k = Q.shape[0]
    c = (jnp.int32(0), p0, jnp.zeros((k,), p0.dtype), jnp.zeros((), p0.dtype), jnp.full((), 1e9, p0.dtype))
    _, p, _, _, _ = jax.lax.while_loop(cond, body, c)
    return p


def _build_Q(r):
    k = r.shape[0]
    Q = jnp.zeros((k, k))
    for t in range(k):
        for j in range(t):
            Q = Q.at[t, t].add(r[j, t] * r[j, t])
            Q = Q.at[t, j].set(Q[j, t])
        for j in range(t + 1, k):
            Q = Q.at[t, t].add(r[j, t] * r[j, t])
            Q = Q.at[t, j].set(-r[j, t] * r[t, j])
    return Q


def predict_proba(params, x):
    """Pairwise-coupling class probabilities, identical to svm_predict_probability."""
    x = jnp.atleast_2d(x)
    dec = decision_values(params, x)
    k = params["nr_class"]
    A, B = params["probA"], params["probB"]
    min_prob = 1e-7

    def one(dec_row):
        p = 0
        r = jnp.zeros((k, k))
        for i in range(k):
            for j in range(i + 1, k):
                v = _sigmoid_predict(dec_row[p], A[p], B[p])
                v = jnp.clip(v, min_prob, 1.0 - min_prob)
                r = r.at[i, j].set(v).at[j, i].set(1.0 - v)
                p += 1
        return _couple(_build_Q(r), jnp.full((k,), 1.0 / k))

    return jax.vmap(one)(dec)


def predict(params, x):
    """libsvm voting prediction -> class label (B,)."""
    x = jnp.atleast_2d(x)
    dec = decision_values(params, x)
    k = params["nr_class"]
    votes = jnp.zeros((x.shape[0], k), dtype=jnp.int32)
    p = 0
    for i in range(k):
        for j in range(i + 1, k):
            win = dec[:, p] > 0
            votes = votes.at[:, i].add(win.astype(jnp.int32))
            votes = votes.at[:, j].add((~win).astype(jnp.int32))
            p += 1
    return params["label"][jnp.argmax(votes, axis=1)]


# ---------------------------------------------------------------- classifier
def classify_digit(p9, p2, patch):
    """One 20x32 patch -> (digit, confidence 0..100, candidates[(digit,conf)]).

    Mirrors CDigitalOCR::Predict + PredictNegPos: the best class plus the 2nd
    and 3rd candidates when their probability exceeds 0.1.
    """
    feat = hog_features(jnp.asarray(patch).reshape(1, H, W))
    prob = predict_proba(p9, feat)[0]
    order = jnp.argsort(-prob)
    cands = [(int(order[0]), int(prob[order[0]] * 100))]
    for r in (1, 2):
        idx = int(order[r])
        if float(prob[idx]) > 0.1:
            cands.append((idx, int(float(prob[idx]) * 100)))
    negpos_prob0 = float(predict_proba(p2, feat)[0, 0])
    return int(order[0]), int(float(prob[order[0]]) * 100), cands, (negpos_prob0 <= 0.5)


def recognize(p9, p2, patches):
    """Vector of 20x32 patches -> recognised digit string."""
    return "".join(str(classify_digit(p9, p2, p)[0]) for p in patches)


def resize_to_patch(img, dtype=np.uint8):
    """Nearest-neighbour resample of an arbitrary HxW image to 32x20."""
    img = np.asarray(img)
    if img.shape == (H, W):
        return img.astype(dtype)
    yi = (np.arange(H) * (img.shape[0] / H)).astype(int).clip(0, img.shape[0] - 1)
    xi = (np.arange(W) * (img.shape[1] / W)).astype(int).clip(0, img.shape[1] - 1)
    return img[yi][:, xi].astype(dtype)
