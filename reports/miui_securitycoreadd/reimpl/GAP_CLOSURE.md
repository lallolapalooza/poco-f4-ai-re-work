# Gap closure — `miui_securitycoreadd` P1 (inference / serving)

Closes the documented gap *"no lux/category → 3-float pipeline"* (`REIMPL_COVERAGE.md`,
`reimpl_coverage.json`). `reimpl/model.py` (weights, inference, training) is
**unchanged**; the new code sits on top of it.

## Files

| file | content |
|---|---|
| `reimpl/pipeline_lux.py` | `(ambientLux, luxSpanIndex, appCategoryId)` → 3-float `x` → model → nits (+ normalized value), with the app's validation/fallback gates |
| `reimpl/verify_lux.txt` | verification transcript of two runs (default bounds, caller bounds), both exit 0 |
| `reimpl/verify.txt` | unchanged model-level transcript; §F appended as a pointer to `verify_lux.txt` |

## Mapping to the app

| app symbol | pipeline symbol | confidence |
|---|---|---|
| `BrightnessDataProcessor.getAmbientLuxSpanIndex` (`,:722-746`) | `lux_span_index()` — exact port incl. all buckets 0…44 | C |
| `f3.c.a(IndividualModelEvent)`, `g3.a.a` float[1][3] | `build_x()` — `[ln(lux+1), ln(span+1), ln(cat+1)]`, float32 `(1,3)` | C |
| `g3.a.a("old_model_infer", …)` (`IndividualModelService$a.predictBrightness:72`) | `run_pipeline()` → `model.apply_old` (serving nits) | C |
| `g3.a.a` `catch IllegalArgumentException → -1.0f` | `ERR_SENTINEL`, `predict_brightness()` | C |
| — `new_model_infer` (subgraph 0) | `run_pipeline()` → `model.apply` (reported as `student_nits`) | C |
| `CustomBrightnessModeController.onPredictFinished:463-474` (`NaN`/`<0` drop) | `check_output()`, `on_predict_finished()` | C |
| `IndividualEventNormalizer.normalizeNit` (`MathUtils.norm`+constrain) | `normalize_nit(nit, nit_min, nit_max)` | C for the formula; bounds U |
| `DisplayDeviceConfig.getBrightnessFromNit` (nits → dbv 0..1) | `dbv_from_nit_config()` — raises, device LUT not in the APK | U |
| `getBrightnessSpanByNit` (label-side bucket) | `brightness_span_by_nit()` (unused by the model input) | C |

## Serving signature — important finding

The task said *run `new_model_infer`*; it is run and its value is reported, but it
cannot be the brightness. The shipped asset holds `dnn_model/*` = trained
checkpoint and `dnn_model_1/*` = **zero biases + Glorot kernels** (untrained
student; `report.md` weights table, `verify.txt` check E: 0.0…2.67 over the app
domain vs 3.86…1734.9 nits for the teacher). The service calls only
`old_model_infer`. So:

* `nits` = `old_model_infer` → the documented serving value (units: **nits**, C);
* `student_nits` = `new_model_infer` → printed for completeness, flagged untrained.

## Units / normalized value

* Model output is nits (label `curBrt` is nits; consumer converts with
  `getBrightnessFromNit`) — C.
* Normalized value: `normalize_nit()` reproduces `MathUtils.norm` + `constrain`
  from `IndividualEventNormalizer.normalizeNit`. Its bounds are ctor arguments
  (`DisplayDeviceConfig.getNitsFromBacklight(min|normalMax)`,
  `BrightnessDataProcessor.java:278`) — device display-config values, U. The
  pipeline therefore requires them (`--nit-bounds MIN MAX`) and, when absent,
  prints a column explicitly labelled ILLUSTRATIVE `[2, 1800]`; nothing is
  presented as the device's real dbv.

## Verification (synthetic input, real weights)

`$PY reimpl/pipeline_lux.py` → exit 0, `RESULT: PASS`; 8/8 rows finite, serving
nits 3.858 → 944.371 across dark/indoor/outdoor and categories 0…9 (see
`verify_lux.txt`). Extra checks in the same run:

1. `lux → span` port vs the 15 documented bucket boundaries — all match.
2. Rejection gates: NaN, ±inf, negative → `InvalidPrediction`; bad event →
   `-1.0f` sentinel and `currentBrightness` fallback.
3. Monotonicity of the serving net over a 20-point lux sweep for categories 0/1/2
   (the framework's own `isMonotonicModel` property) — non-decreasing.
4. End-to-end cross-check: the pipeline's `x` fed to the shipped flatbuffer
   (`ai_edge_litert`, `old_model_infer` / `new_model_infer`) agrees with
   `model.py` to 1.8e-4 nits / 2.4e-7 respectively.

## Not reimplemented (documented, framework-side)

* `DisplayDeviceConfig.getBrightnessFromNit` → backlight LUT (device config).
* Spline construction after the sweep (`smoothCurve`, `isMonotonic` +
  minimum-slope, `storeIndividualSpline`) — pure Java in `system_server`.
* P2 checkpoint container (`restore`/`save_for_replace`) — the training math is
  already covered by `model.py::train_step` / `reference_train.py`.
