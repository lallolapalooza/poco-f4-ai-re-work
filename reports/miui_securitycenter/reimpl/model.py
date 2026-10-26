#!/usr/bin/env python3
"""JAX reimplementation of the MIUI Security Center on-device SMS filter model.

The shipped asset `assets/model` (models/assets_model, sha256
b282b4ef...9f15) is decoded by ir/parse_model.py: a 5-stage cascade of
log-linear classifiers over (a) 9 hand-written rule features, (b) per-stage
character-presence features, (c) one message-length feature, (d) a token
table (8000/8000/5752/4759/2573 rows), (e) a substring-pattern table and
(f) an 8-way sender-category bias.

Scoring semantics follow ah/d.java `h(ah.b, Context)` exactly:

    for each stage:
        s[k] = sum_i ( feat_i ? w[k][i] : log1p(-exp(w[k][i])) )
             + sum_t ( tok_hit ? tw[k]  : log1p(-exp(tw[k]))  )
             + sum_p ( pat_hit ? pw[k]  : log1p(-exp(pw[k]))  )
             + cat_bias[k][phone_category]
        d = s[0] - s[1]
        if   d <  thr[1] -> class 0, stop
        elif d >  thr[0] -> class 1, stop
        else             -> vote[d - thr[2]], continue

Weights are the *real extracted* float64 values (big-endian in the container).

Run:  $PY reimpl/model.py [model_path]
"""
from __future__ import annotations

import json
import sys
from pathlib import Path

import numpy as np

ROOT = Path(__file__).resolve().parent.parent
sys.path.insert(0, str(ROOT / "ir"))
from parse_model import parse  # noqa: E402

import jax  # noqa: E402
import jax.numpy as jnp  # noqa: E402

jax.config.update("jax_enable_x64", True)

FEATURES_PER_STAGE_RULES = 9  # dh.e rule features (dh.j + dh.b + dh.c)


def load_stages(path: str | Path) -> list[dict]:
    """Return per-stage parameter dicts (real weights)."""
    ir = parse(str(path))
    stages = []
    for m in ir["models"]:
        n1 = len(m["chars1_codepoints"])
        stages.append(
            {
                "n_feat": m["n_feat"],
                "n_rule": FEATURES_PER_STAGE_RULES,
                "chars": np.array(m["chars1_codepoints"], dtype=np.uint16),
                "len_threshold": m["n_chars2"],
                "w": np.array(m["weights"], dtype=np.float64),            # [2, F]
                "token_w": np.array([t[1:] for t in m["tokens"]], dtype=np.float64),
                "pattern_w": np.array([p[1:] for p in m["patterns"]], dtype=np.float64),
                "tokens": [t[0] for t in m["tokens"]],
                "patterns": [p[0] for p in m["patterns"]],
                "bias": np.array(m["bias"], dtype=np.float64),            # [2, 8]
                "thr": np.array(m["thresholds"], dtype=np.float64),       # [3]
            }
        )
        assert stages[-1]["w"].shape[1] == m["n_feat"]
        assert stages[-1]["n_rule"] + n1 + 1 == m["n_feat"], "feature layout"
    return stages


def init_params(stages: list[dict]) -> dict:
    p = {}
    for i, s in enumerate(stages):
        p[f"w{i}"] = jnp.asarray(s["w"])
        p[f"token_w{i}"] = jnp.asarray(s["token_w"])
        p[f"pattern_w{i}"] = jnp.asarray(s["pattern_w"])
        p[f"bias{i}"] = jnp.asarray(s["bias"])
        p[f"thr{i}"] = jnp.asarray(s["thr"])
    return p


def _log_absent(p):
    """ah.i.g(x) = log(1 - exp(x)) -- weight of an *absent* feature."""
    return jnp.log1p(-jnp.exp(p))


def stage_scores(params: dict, i: int, feat_mask, tok_hit, pat_hit, category: int):
    """Two-class log-odds contributions for one stage.  Returns shape [2]."""
    w = params[f"w{i}"]                              # [2, F]
    s = jnp.sum(jnp.where(feat_mask, w, _log_absent(w)), axis=1)
    tw = params[f"token_w{i}"]                       # [T, 2]
    s = s + jnp.sum(jnp.where(tok_hit[:, None], tw, _log_absent(tw)), axis=0)
    pw = params[f"pattern_w{i}"]                     # [P, 2]
    s = s + jnp.sum(jnp.where(pat_hit[:, None], pw, _log_absent(pw)), axis=0)
    s = s + params[f"bias{i}"][:, category]
    return s


def apply(params: dict, i: int, feat_mask, tok_hit, pat_hit, category: int,
          feedback: tuple[int, int] | None = None):
    """Full stage evaluation: returns (score_diff, decision) with decision in {0,1,None}.

    `feedback` mirrors ah.e.g(thr, b): (a, b) counters from ch.d; thresholds are
    shifted by ((a-b) * (thr[0]-thr[1])) / 4.
    """
    s = stage_scores(params, i, feat_mask, tok_hit, pat_hit, category)
    thr = params[f"thr{i}"]
    if feedback is not None:
        a, b = feedback
        if a != b:
            thr = thr + ((a - b) * (thr[0] - thr[1])) / 4.0
    d = s[0] - s[1]
    d = float(d)
    thr0, thr1, thr2 = (float(x) for x in thr)
    if d < thr1:
        return d, 0
    if d > thr0:
        return d, 1
    return d, None


def build_masks(stage: dict, text: str, tokens, rules_present=None,
                rules_count=None) -> tuple[np.ndarray, np.ndarray, np.ndarray]:
    """Feature/token/pattern boolean masks for one stage from raw input."""
    n_feat = stage["n_feat"]
    mask = np.zeros(n_feat, dtype=bool)
    # rule features (indices 0..8): caller supplies how many fired
    if rules_present is not None:
        mask[: len(rules_present)] = np.asarray(rules_present, dtype=bool)[: FEATURES_PER_STAGE_RULES]
    # char-presence features
    chars = stage["chars"]
    for j, cp in enumerate(chars):
        if chr(int(cp)) in text:
            mask[FEATURES_PER_STAGE_RULES + j] = True
    # message-length feature (last index)
    mask[-1] = len(text) >= stage["len_threshold"]
    tok_set = set(tokens)
    tok_hit = np.array([t in tok_set for t in stage["tokens"]], dtype=bool)
    pat_hit = np.array([p in text for p in stage["patterns"]], dtype=bool)
    return mask, tok_hit, pat_hit


def classify(params, stages, text, tokens, category, rules_present=None, feedback=None):
    """ah.a.a()/ah.d.h() cascade.  Returns dict with per-stage trace and verdict."""
    votes = []
    trace = []
    for i, st in enumerate(stages):
        mask, tok_hit, pat_hit = build_masks(st, text, tokens, rules_present)
        d, dec = apply(params, i, jnp.asarray(mask), jnp.asarray(tok_hit),
                       jnp.asarray(pat_hit), category,
                       feedback[i] if feedback else None)
        trace.append({"stage": i, "score_diff": d, "decision": dec,
                      "n_token_hits": int(tok_hit.sum()), "n_pattern_hits": int(pat_hit.sum())})
        if dec is not None:
            return {"verdict": dec, "stages": trace, "early_exit": True}
        votes.append(d - float(params[f"thr{i}"][2]))
    pos = sum(1 for v in votes if v > 0)
    neg = len(votes) - pos
    return {"verdict": 1 if pos > neg else 0, "stages": trace, "early_exit": False,
            "votes": votes, "pos": pos, "neg": neg}


def classify_numpy(stages, text, tokens, category, rules_present=None, feedback=None):
    """Independent NumPy reference of the same Java code (used to verify JAX)."""
    def absent(p):
        return np.log1p(-np.exp(p))

    votes = []
    trace = []
    tok_set = set(tokens)
    for i, st in enumerate(stages):
        mask, tok_hit, pat_hit = build_masks(st, text, tokens, rules_present)
        s = np.where(mask, st["w"], absent(st["w"])).sum(axis=1)
        s = s + np.where(tok_hit[:, None], st["token_w"], absent(st["token_w"])).sum(axis=0)
        s = s + np.where(pat_hit[:, None], st["pattern_w"], absent(st["pattern_w"])).sum(axis=0)
        s = s + st["bias"][:, category]
        thr = st["thr"].copy()
        if feedback and feedback[i] and feedback[i][0] != feedback[i][1]:
            a, b = feedback[i]
            thr = thr + ((a - b) * (thr[0] - thr[1])) / 4.0
        d = float(s[0] - s[1])
        trace.append({"stage": i, "score_diff": d})
        if d < thr[1]:
            return {"verdict": 0, "stages": trace, "early_exit": True}
        if d > thr[0]:
            return {"verdict": 1, "stages": trace, "early_exit": True}
        votes.append(d - thr[2])
    pos = sum(1 for v in votes if v > 0)
    neg = len(votes) - pos
    return {"verdict": 1 if pos > neg else 0, "stages": trace, "early_exit": False,
            "votes": votes, "pos": pos, "neg": neg}


def ir_tokens(stages) -> list[tuple]:
    """(token, w0, w1) rows of stage 0, as read by ah/d.h."""
    st = stages[0]
    return [(t, float(w[0]), float(w[1])) for t, w in zip(st["tokens"], st["token_w"])]


def main() -> int:
    path = Path(sys.argv[1]) if len(sys.argv) > 1 else ROOT / "models" / "assets_model"
    stages = load_stages(path)
    params = init_params(stages)
    print(f"model: {path}")
    print(f"jax {jax.__version__}  devices={jax.devices()}")
    print(f"stages: {len(stages)}")
    for i, st in enumerate(stages):
        print(f"  stage {i}: n_feat={st['n_feat']} rules={st['n_rule']} chars={len(st['chars'])} "
              f"len_thr={st['len_threshold']} tokens={len(st['tokens'])} patterns={len(st['patterns'])} "
              f"w{tuple(st['w'].shape)} token_w{tuple(st['token_w'].shape)} "
              f"pattern_w{tuple(st['pattern_w'].shape)} bias{tuple(st['bias'].shape)}")

    # --- shape / semantics checks -------------------------------------------------
    syn_text = "【京东】恭喜您中奖，点击 http://t.cn/abc 领取 贷款 50000 元"
    syn_tokens = ["中奖", "领取", "点击"]
    category = 0  # "Common" sender (dh.a.f44565a[0])
    for i in range(len(stages)):
        mask, tok_hit, pat_hit = build_masks(stages[i], syn_text, syn_tokens)
        s = stage_scores(params, i, jnp.asarray(mask), jnp.asarray(tok_hit),
                         jnp.asarray(pat_hit), category)
        print(f"  stage {i} scores={np.asarray(s).round(4).tolist()} "
              f"(feat_mask {int(mask.sum())}/{mask.size}, tok_hit {int(tok_hit.sum())}, "
              f"pat_hit {int(pat_hit.sum())})")

    jv = classify(params, stages, syn_text, syn_tokens, category)
    nv = classify_numpy(stages, syn_text, syn_tokens, category)
    print(f"jax   verdict={jv['verdict']} early_exit={jv['early_exit']} "
          f"trace={[round(t['score_diff'], 4) for t in jv['stages']]}")
    print(f"numpy verdict={nv['verdict']} early_exit={nv['early_exit']} "
          f"trace={[round(t['score_diff'], 4) for t in nv['stages']]}")
    agree = all(abs(a["score_diff"] - b["score_diff"]) < 1e-9
                for a, b in zip(jv["stages"], nv["stages"]))
    print(f"jax/numpy agree={agree} verdict_equal={jv['verdict'] == nv['verdict']}")

    # negative sample (ordinary personal SMS)
    clean = "晚上七点老地方见，别迟到"
    jv2 = classify(params, stages, clean, [], 0)
    nv2 = classify_numpy(stages, clean, [], 0)
    print(f"clean sample: jax verdict={jv2['verdict']} numpy verdict={nv2['verdict']} "
          f"trace={[round(t['score_diff'], 3) for t in jv2['stages']]}")

    # --- discriminative probe: tokens ranked by (w0 - w1) of stage 0 ----------
    pairs = sorted(((t[1] - t[2], t[0]) for t in ir_tokens(stages)), reverse=True)
    spam_tokens = [t for _, t in pairs[:30]]
    ham_tokens = [t for _, t in pairs[-30:]]
    spam_text = " ".join(spam_tokens)
    ham_text = " ".join(ham_tokens)
    jsp = classify(params, stages, spam_text, spam_tokens, 0)
    nsp = classify_numpy(stages, spam_text, spam_tokens, 0)
    jha = classify(params, stages, ham_text, ham_tokens, 0)
    nha = classify_numpy(stages, ham_text, ham_tokens, 0)
    print(f"spam probe ({len(spam_tokens)} tokens, e.g. {spam_tokens[:4]}): "
          f"jax={jsp['verdict']} numpy={nsp['verdict']} "
          f"stage0 d={jsp['stages'][0]['score_diff']:.2f}")
    print(f"ham  probe ({len(ham_tokens)} tokens, e.g. {ham_tokens[:4]}): "
          f"jax={jha['verdict']} numpy={nha['verdict']} "
          f"stage0 d={jha['stages'][0]['score_diff']:.2f}")
    ok = (agree and jv["verdict"] == nv["verdict"]
          and jsp["verdict"] == 1 and jha["verdict"] == 0
          and jsp["verdict"] == nsp["verdict"] and jha["verdict"] == nha["verdict"])
    print(f"ALL CHECKS PASS={ok}")
    return 0 if ok else 1


if __name__ == "__main__":
    sys.exit(main())
