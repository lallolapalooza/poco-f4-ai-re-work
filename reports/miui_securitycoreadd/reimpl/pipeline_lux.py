#!/usr/bin/env python3
"""P1 (inference / serving) pipeline for `assets/aiautobrt.tflite`.

App path reproduced here, in order:

    BrightnessDataProcessor.createModelEvent   (system_server)
      ambientLux      <- AutomaticBrightnessController.mAmbientLux   [lux]
      luxSpanIndex    <- getAmbientLuxSpanIndex(ambientLux)          [bucket 0..44]
      appCategoryId   <- AppClassifier.getAppCategoryId(pkg)         [int 0..9]
    f3.c.a(IndividualModelEvent)                                    (-, -, -)
      x = [ln(lux+1), ln(span+1), ln(category+1)]
    g3.a.a("old_model_infer", h3.a)      float[1][3] -> float[1]     [nits]
      exception -> -1.0f sentinel -> caller falls back to currentBrightness
    IndividualModelService$a.predictBrightness -> IIndividualCallback
      .onPredictFinished(ambientLux, appCategoryId, brightness)      [nits]
    CustomBrightnessModeController.onPredictFinished
      NaN or < 0 -> drop, else dbv = getBrightnessFromNit(nits)      [0..1]

`reimpl/model.py` supplies both subgraphs of the flatbuffer:

    apply(params, x)      new_model_infer   student   (subgraph 0)
    apply_old(params, x)  old_model_infer   serving   (subgraph 1)

Weight provenance matters and is visible in the shipped asset: `dnn_model/*`
(old_model_infer) carries the trained checkpoint, `dnn_model_1/*`
(new_model_infer) has **exactly zero biases and Glorot kernels**, i.e. it is the
freshly initialised student (see report.md §weights, reimpl/verify.txt §E:
new_model_infer spans 0.0..2.67 over the app domain, old_model_infer 3.86..1734.9
nits).  The service calls only `old_model_infer` (IndividualModelService$a:
`aVar2.a("old_model_infer", aVarA)`), so that is the serving result below; the
student is run as well (the task requires `new_model_infer`) and reported, but it
is flagged as not a brightness because its weights are untrained in this asset.

No CLI dependency on JAX/TFLite beyond `model.py`; no network.
"""
from __future__ import annotations

import argparse
import math
import os
import sys
import warnings
from dataclasses import dataclass

import numpy as np

# model.py switches jax_enable_x64 off on import, so its own int64 params warn;
# that warning is expected and not part of this pipeline's contract.
warnings.filterwarnings("ignore", message="Explicitly requested dtype int64")
os.environ.setdefault("JAX_PLATFORMS", "cpu")

HERE = os.path.dirname(os.path.abspath(__file__))
if HERE not in sys.path:
    sys.path.insert(0, HERE)

import model as M  # noqa: E402  (reimpl/model.py, real weights from weights.npz)

MODEL_PATH = os.path.join(HERE, "..", "models", "aiautobrt.tflite")

# --------------------------------------------------------------------------
# app constants
# --------------------------------------------------------------------------
# AppClassifier.java:33-43
APP_CATEGORIES = {
    0: "undefined", 1: "game", 2: "video", 3: "music/read", 4: "finance/learn",
    5: "news", 6: "shopping", 7: "photo", 8: "travel", 9: "social",
}

# BrightnessDataProcessor.java:91,110-124 — constants used by
# getAmbientLuxSpanIndex() / getBrightnessSpanByNit().
MAX_SPAN_INDEX = 44
SCREEN_NIT_SPAN_ONE = 3.5
SCREEN_NIT_SPAN_TWO = 8.0
SCREEN_NIT_SPAN_FOUR = 80.0
SCREEN_NIT_SPAN_FIVE = 500.0
SCREEN_NIT_SPAN_SIX = 1000.0
SPAN_SCREEN_NIT_STEP_ONE = 7.0
SPAN_SCREEN_NIT_STEP_TWO = 10.0

# g3.a.a() error sentinel (returns -1.0f when the signature runner throws).
ERR_SENTINEL = -1.0

# Lux sweep for the monotonicity sanity check (own grid; the framework uses the
# device's mDefaultLuxLevels, which are a display-config resource [U]).
LUX_LEVELS = (
    0.0, 1.0, 5.0, 10.0, 30.0, 50.0, 100.0, 200.0, 500.0, 900.0, 1000.0,
    2000.0, 3500.0, 5000.0, 10000.0, 15000.0, 20000.0, 35000.0, 65000.0, 100000.0,
)

_PARAMS = None


class InvalidEvent(ValueError):
    """Event fields outside the domain the app can build (NaN / negative / range)."""


class InvalidPrediction(ValueError):
    """Model output is not a usable brightness (non-finite or negative)."""


# --------------------------------------------------------------------------
# preprocessing  (BrightnessDataProcessor + f3.c.a)
# --------------------------------------------------------------------------
def lux_span_index(lux: float) -> int:
    """Exact port of `BrightnessDataProcessor.getAmbientLuxSpanIndex` (:722-746).

    Bucket index of the *lux* value, 0..44 — not a lux delta.
    """
    lux = float(lux)
    if not math.isfinite(lux):
        raise InvalidEvent(f"ambientLux is not finite: {lux!r}")
    if lux < 0.0:
        raise InvalidEvent(f"ambientLux is negative: {lux!r}")
    if lux < 30.0:
        index = int(lux / 5.0)
    elif lux < 900.0:
        index = int((lux / 100.0) + 6.0)
    elif lux < 3500.0:
        index = int(((lux - 900.0) / 200.0) + 15.0)
    elif lux < 10000.0:
        index = int(((lux - 3500.0) / SCREEN_NIT_SPAN_FIVE) + 28.0)
    elif lux < 15000.0:
        index = 41
    elif lux < 35000.0:
        index = 42
    elif lux < 65000.0:
        index = 43
    else:
        # both remaining Java branches return MAX_SPAN_INDEX (44)
        index = MAX_SPAN_INDEX
    return index


def brightness_span_by_nit(nit: float) -> int:
    """Port of `getBrightnessSpanByNit` (:749-780) — the *label* side bucket.

    Documented for completeness; the P1 model input does not use it.
    """
    screen_nit = float(math.floor(float(nit) + 0.5))          # Java Math.round(float)
    if SCREEN_NIT_SPAN_ONE <= screen_nit < SCREEN_NIT_SPAN_TWO:
        return 1
    if SCREEN_NIT_SPAN_TWO <= screen_nit < 50.0:
        return int((screen_nit - SCREEN_NIT_SPAN_TWO) / SPAN_SCREEN_NIT_STEP_ONE) + 2
    if 50.0 <= screen_nit < SCREEN_NIT_SPAN_FOUR:
        return int((screen_nit - 50.0) / SPAN_SCREEN_NIT_STEP_TWO) + 2 + 6
    if SCREEN_NIT_SPAN_FOUR <= screen_nit < SCREEN_NIT_SPAN_FIVE:
        return int((screen_nit - SCREEN_NIT_SPAN_FOUR) / 20.0) + 2 + 9
    if SCREEN_NIT_SPAN_FIVE <= screen_nit < SCREEN_NIT_SPAN_SIX:
        return int((screen_nit - SCREEN_NIT_SPAN_FIVE) / 50.0) + 2 + 30
    if screen_nit > SCREEN_NIT_SPAN_SIX:
        return 42
    return 0


def build_x(ambient_lux: float, lux_span: int, app_category: int) -> np.ndarray:
    """`f3.c.a` + `g3.a.a`: the exact 3-float contract, float[1][3].

    Java does `(float) Math.log(v + 1.0f)` on the raw field, so no normalization,
    no clipping, no mean/scale: `math.log1p` in float64 then a float32 cast is the
    closest executable equivalent (|err| <= 1 ulp float32).
    """
    lux = float(ambient_lux)
    if not math.isfinite(lux):
        raise InvalidEvent(f"ambientLux is not finite: {lux!r}")
    if lux < 0.0:
        raise InvalidEvent(f"ambientLux is negative: {lux!r}")
    try:
        span = int(lux_span)
        cat = int(app_category)
    except (TypeError, ValueError) as exc:
        raise InvalidEvent(f"non-integer span/category: {lux_span!r}, {app_category!r}") from exc
    if float(lux_span) != span or float(app_category) != cat:
        raise InvalidEvent("luxSpan / appCategory must be integers (Java int fields)")
    if not 0 <= span <= MAX_SPAN_INDEX:
        raise InvalidEvent(f"luxSpan outside 0..{MAX_SPAN_INDEX}: {span}")
    if cat not in APP_CATEGORIES:
        raise InvalidEvent(f"appCategory outside 0..9: {cat}")

    x = np.array([[math.log(lux + 1.0), math.log(span + 1.0), math.log(cat + 1.0)]],
                 dtype=np.float32)
    assert x.shape == (1, 3) and x.dtype == np.float32
    return x


# --------------------------------------------------------------------------
# model invocation + output validation
# --------------------------------------------------------------------------
def params() -> dict:
    global _PARAMS
    if _PARAMS is None:
        _PARAMS = M.load_params()
    return _PARAMS


def check_output(raw, name: str) -> float:
    """Accept only a finite, non-negative brightness.

    Both gates exist in the app: `g3.a.a` maps runner errors to -1.0f, and
    `CustomBrightnessModeController.onPredictFinished` drops `NaN` or `< 0.0f`.
    """
    arr = np.asarray(raw, dtype=np.float64).ravel()
    if arr.size != 1:
        raise InvalidPrediction(f"{name}: expected 1 output, got shape {np.shape(raw)}")
    value = float(arr[0])
    if not math.isfinite(value):
        raise InvalidPrediction(f"{name}: non-finite output {value!r}")
    if value < 0.0:
        raise InvalidPrediction(f"{name}: negative output {value!r}")
    return value


@dataclass(frozen=True)
class LuxPrediction:
    """One P1 prediction. `nits` is the serving value (old_model_infer)."""

    ambient_lux: float
    lux_span: int
    app_category: int
    x: tuple
    nits: float                 # old_model_infer — units: nits
    student_nits: float         # new_model_infer — untrained student, see module doc
    dbv: float | None           # normalized 0..1, only if bounds were supplied

    @property
    def category_name(self) -> str:
        return APP_CATEGORIES[self.app_category]


def run_pipeline(ambient_lux: float, lux_span: int, app_category: int,
                 nit_bounds: tuple[float, float] | None = None) -> LuxPrediction:
    """`(ambientLux, luxSpan, appCategory)` -> nits (+ optional normalized dbv)."""
    x = build_x(ambient_lux, lux_span, app_category)
    p = params()
    student = check_output(M.apply(p, x), "new_model_infer")
    serving = check_output(M.apply_old(p, x), "old_model_infer")
    dbv = None if nit_bounds is None else normalize_nit(serving, *nit_bounds)
    return LuxPrediction(
        ambient_lux=float(ambient_lux),
        lux_span=int(lux_span),
        app_category=int(app_category),
        x=tuple(float(v) for v in x[0]),
        nits=serving,
        student_nits=student,
        dbv=dbv,
    )


def predict_brightness(ambient_lux: float, lux_span: int, app_category: int,
                       current_brightness: float | None = None) -> float:
    """`IndividualModelService$a.predictBrightness`, including the -1.0f fallback.

    Returns nits on success.  On a rejected prediction the app substitutes
    `event.getCurrentBrightness()`; if the caller has no current brightness the
    documented sentinel (-1.0f) is returned instead.
    """
    try:
        return run_pipeline(ambient_lux, lux_span, app_category).nits
    except (InvalidEvent, InvalidPrediction):
        return ERR_SENTINEL if current_brightness is None else float(current_brightness)


def on_predict_finished(nits: float) -> float | None:
    """`CustomBrightnessModeController.onPredictFinished:463-474` gate.

    NaN or negative -> dropped (returns None); otherwise the value is converted
    to the 0..1 backlight domain by `getBrightnessFromNit` (device LUT).
    """
    value = float(nits)
    if not math.isfinite(value) or value < 0.0:
        return None
    return value


# --------------------------------------------------------------------------
# nits -> normalized value
# --------------------------------------------------------------------------
def normalize_nit(nit: float, nit_min: float, nit_max: float) -> float:
    """`IndividualEventNormalizer.normalizeNit` — `MathUtils.norm` + constrain.

    `MathUtils.norm(min, max, v) = (v - min) / (max - min)`, result constrained to
    0..1 (`IndividualEventNormalizer.constrain`).  The bounds are the ctor
    arguments `mBrightnessMin` / `mBrightnessMax`, built from
    `DisplayDeviceConfig.getNitsFromBacklight(min|normalMax)`
    (`BrightnessDataProcessor.java:278`) — device display-config values, i.e. [U]
    for this static analysis.  They must be passed in; nothing here invents them.

    This is *not* `DisplayDeviceConfig.getBrightnessFromNit`, which is the actual
    nits->dbv consumer path and lives in the device's display configuration.
    """
    if not math.isfinite(nit_min) or not math.isfinite(nit_max) or nit_max <= nit_min:
        raise ValueError(f"invalid nit bounds: {nit_min!r}, {nit_max!r}")
    norm = (float(nit) - nit_min) / (nit_max - nit_min)
    return float(min(max(norm, 0.0), 1.0))


def dbv_from_nit_config(nit: float) -> float:
    """Raises: the nits -> 0..1 backlight LUT is `DisplayDeviceConfig`, not the APK."""
    raise NotImplementedError(
        "getBrightnessFromNit needs the device DisplayDeviceConfig brightness LUT; "
        "only the framework normalizer form (normalize_nit with explicit bounds) "
        "is reproducible statically")


# --------------------------------------------------------------------------
# sanity checks
# --------------------------------------------------------------------------
def is_monotonic(nits) -> bool:
    """`smoothCurve`/`isMonotonic` domain check on the nits sweep (framework is 0..1)."""
    arr = np.asarray(nits, dtype=np.float64)
    return bool(np.all(np.diff(arr) >= -1e-6))


def interpreter_check(rows, tol_abs=1e-3, tol_rel=1e-6):
    """Feed the pipeline's x to the shipped flatbuffer and compare both signatures.

    This closes the loop on the whole P1 chain: build_x() -> model.py subnet.
    Returns (ok, lines) where `ok` is False when any value disagrees.
    """
    from ai_edge_litert.interpreter import Interpreter

    it = Interpreter(model_path=os.path.abspath(MODEL_PATH))
    it.allocate_tensors()
    sigs = sorted(it.get_signature_list())
    new = it.get_signature_runner("new_model_infer")
    old = it.get_signature_runner("old_model_infer")

    lines = [f"  signatures in flatbuffer: {sigs}"]
    ok = True
    worst = {"old": 0.0, "new": 0.0}
    for pred in rows:
        x = np.asarray([pred.x], np.float32)
        ref_old = float(np.asarray(old(x=x)["pre"]).ravel()[0])
        ref_new = float(np.asarray(new(x=x)["pre"]).ravel()[0])
        for name, got, ref in (("old", pred.nits, ref_old), ("new", pred.student_nits, ref_new)):
            err = abs(got - ref)
            worst[name] = max(worst[name], err)
            if err > max(tol_abs, tol_rel * abs(ref)):
                ok = False
                lines.append(f"  MISMATCH {name} lux={pred.ambient_lux:g} cat={pred.app_category}: "
                             f"model.py={got!r} interpreter={ref!r}")
    lines.append(f"  old_model_infer max|err| = {worst['old']:.3e}  (nits)")
    lines.append(f"  new_model_infer max|err| = {worst['new']:.3e}")
    lines.append(f"  agreement over {len(rows)} rows: {'PASS' if ok else 'FAIL'}")
    return ok, lines


# --------------------------------------------------------------------------
# demo / verification entrypoint
# --------------------------------------------------------------------------
CASES = (
    # (scene, ambientLux, appCategory)
    ("dark (0 lux)", 0.0, 0),
    ("dark, 1 lux", 1.0, 1),
    ("indoor 50 lux", 50.0, 7),
    ("indoor 200 lux", 200.0, 3),
    ("indoor 1000 lux", 1000.0, 9),
    ("outdoor 5000 lux", 5000.0, 1),
    ("outdoor 20000 lux", 20000.0, 2),
    ("outdoor 100000 lux", 100000.0, 0),
)
CASES_CATEGORY = (
    ("indoor 200 lux", 200.0),
)
NIT_BOUNDS_ILLUSTRATIVE = (2.0, 1800.0)   # caller-supplied; see normalize_nit doc


def _fmt(value, width, prec=3):
    return f"{value:>{width}.{prec}f}"


def main(argv=None) -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("--nit-bounds", nargs=2, type=float, metavar=("MIN", "MAX"),
                    default=None,
                    help="bounds for the framework normalizer (nits); default: only "
                         "illustrative bounds are shown, real ones are display-config [U]")
    args = ap.parse_args(argv)

    failures = []
    print("=== aiautobrt P1 pipeline: (ambientLux, luxSpan, appCategory) -> nits ===")
    print(f"python {sys.version.split()[0]} | numpy {np.__version__} | jax {M.jax.__version__}")
    print(f"weights {M.WEIGHTS}")
    print(f"model   {os.path.abspath(MODEL_PATH)}")
    print("x = [ln(ambientLux+1), ln(luxSpan+1), ln(appCategory+1)]  (f3.c.a, g3.a.a, float[1][3])")
    print()

    bounds = tuple(args.nit_bounds) if args.nit_bounds else None
    shown_bounds = bounds or NIT_BOUNDS_ILLUSTRATIVE

    print("--- 1. scene / category table ---")
    header = (f"{'scene':<18}{'lux':>10}{'span':>6}{'cat':>4}  {'category':<14}"
              f"{'x0':>8}{'x1':>7}{'x2':>7}"
              f"{'old nits':>11}{'norm':>8}{'new nits':>10}  status")
    print(header)
    rows = []
    for scene, lux, cat in CASES:
        span = lux_span_index(lux)
        pred = run_pipeline(lux, span, cat, shown_bounds)
        if pred.dbv is None:
            failures.append(f"{scene}: no normalized value")
        x0, x1, x2 = pred.x
        line = (f"{scene:<18}{lux:>10.1f}{span:>6d}{cat:>4d}  {pred.category_name:<14}"
                f"{x0:>8.4f}{x1:>7.4f}{x2:>7.4f}"
                f"{pred.nits:>11.3f}{pred.dbv:>8.3f}{pred.student_nits:>10.4f}  ok")
        print(line)
        rows.append(pred)
        if not (math.isfinite(pred.nits) and pred.nits >= 0.0):
            failures.append(f"{scene}: invalid nits {pred.nits}")

    print()
    print(f"units: old_model_infer (new_model_infer) output is nits — same unit as the "
          f"training label curBrt/currentBrightness [C].")
    print(f"norm: normalize_nit(nits, {shown_bounds[0]:g}, {shown_bounds[1]:g}) = "
          f"MathUtils.norm + constrain, i.e. IndividualEventNormalizer.normalizeNit; "
          f"bounds {'caller-supplied' if bounds else 'ILLUSTRATIVE (real bounds are device display-config [U])'}.")
    print("new nits: shipped dnn_model_1/* has zero biases + Glorot kernels = untrained "
          "student (report.md weights table), so it is NOT a brightness; the app calls "
          "old_model_infer only (IndividualModelService$a.predictBrightness).")

    print()
    print("--- 2. category dependence (fixed lux=200, span=%d) ---" % lux_span_index(200.0))
    print(f"{'cat':>4}  {'category':<14}{'old nits':>11}{'new nits':>10}")
    per_cat = {}
    for cat, name in APP_CATEGORIES.items():
        pred = run_pipeline(200.0, lux_span_index(200.0), cat)
        per_cat[cat] = pred
        print(f"{cat:>4}  {name:<14}{pred.nits:>11.3f}{pred.student_nits:>10.4f}")
    if not all(math.isfinite(p.nits) for p in per_cat.values()):
        failures.append("category sweep produced non-finite nits")

    print()
    print("--- 3. lux -> span-index port (getAmbientLuxSpanIndex) ---")
    table = [(0.0, 0), (4.9, 0), (5.0, 1), (29.9, 5), (30.0, 6), (899.0, 14),
             (900.0, 15), (3499.0, 27), (3500.0, 28), (9999.0, 40), (10000.0, 41),
             (15000.0, 42), (35000.0, 43), (65000.0, 44), (100000.0, 44)]
    for lux, want in table:
        got = lux_span_index(lux)
        flag = "ok" if got == want else "MISMATCH"
        if got != want:
            failures.append(f"lux_span_index({lux}) = {got}, expected {want}")
        print(f"  lux={lux:>8.1f} -> span={got:>2d} (expected {want:>2d}) {flag}")

    print()
    print("--- 4. output rejection gates (g3.a.a sentinel / onPredictFinished) ---")
    for raw, why in ((float("nan"), "NaN"), (float("-inf"), "-inf"), (-0.5, "negative")):
        try:
            check_output(np.array([[raw]], np.float32), "new_model_infer")
        except InvalidPrediction as exc:
            print(f"  reject {why:<9} -> InvalidPrediction: {exc}")
        else:
            failures.append(f"negative/NaN output {why} was not rejected")
    print(f"  on_predict_finished(nan) -> {on_predict_finished(float('nan'))} (dropped)")
    print(f"  on_predict_finished(-1.0) -> {on_predict_finished(-1.0)} (dropped)")
    sentinel = predict_brightness(float("nan"), 0, 0)
    print(f"  predict_brightness(lux=NaN) -> {sentinel:g} (app sentinel, caller falls back)")
    if sentinel != ERR_SENTINEL:
        failures.append("bad-event path did not return the -1.0 sentinel")
    fallback = predict_brightness(float("nan"), 0, 0, current_brightness=42.0)
    print(f"  predict_brightness(lux=NaN, current=42) -> {fallback:g} (currentBrightness fallback)")
    if fallback != 42.0:
        failures.append("fallback to currentBrightness did not trigger")
    try:
        build_x(200.0, 45, 0)
    except InvalidEvent as exc:
        print(f"  build_x(span=45) -> InvalidEvent: {exc}")
    else:
        failures.append("out-of-range span accepted")
    try:
        build_x(-1.0, 0, 0)
    except InvalidEvent as exc:
        print(f"  build_x(lux=-1) -> InvalidEvent: {exc}")
    else:
        failures.append("negative lux accepted")

    print()
    print("--- 5. monotonicity of the serving net over the lux sweep ---")
    for cat in (0, 1, 2):
        nits = [run_pipeline(lux, lux_span_index(lux), cat).nits for lux in LUX_LEVELS]
        ok = is_monotonic(nits)
        print(f"  category {cat} ({APP_CATEGORIES[cat]:<9}) non-decreasing: {ok}  "
              f"[{nits[0]:.3f} .. {nits[-1]:.3f}] nits")
        if not ok:
            failures.append(f"serving net non-monotonic for category {cat}")

    print()
    print("--- 6. cross-check vs the shipped flatbuffer (ai_edge_litert) ---")
    try:
        ok, lines = interpreter_check(rows)
    except ImportError as exc:
        print(f"  SKIPPED: ai_edge_litert not importable ({exc})")
    else:
        for line in lines:
            print(line)
        if not ok:
            failures.append("pipeline disagrees with the TFLite interpreter")

    print()
    print("--- 7. contract summary ---")
    p = rows[0]
    print(f"  input  shape: {np.asarray([p.x], np.float32).shape} float32 (float[1][3])")
    print(f"  output shape: (1,) float32 (FloatBuffer(1), 1 value)")
    print(f"  finite examples: {len(rows)}/{len(rows)}; nits range "
          f"[{min(r.nits for r in rows):.3f}, {max(r.nits for r in rows):.3f}]")
    print("  confidence: x construction [C], units = nits [C], serving signature "
          "old_model_infer [C],")
    print("              normalized bounds [U], student weights untrained-in-asset [C]")

    print()
    if failures:
        print("RESULT: FAIL")
        for f in failures:
            print(f"  - {f}")
        return 1
    print("RESULT: PASS (finite nits for every input, 3-float contract, units/confidence noted)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
