# Gap closure — `miui_mediaeditor` TFLite model runners (`model_runners/`)

Closes the documented gap *"25 TFLite have no runner"* (`REIMPL_COVERAGE.md`
§MediaEditor, `reimpl_coverage.json`). All **33 usable** models in
`models/tflite/*.tflite` now have a runnable entry on the real extracted
weights; the 5 encrypted models named in `models/INVENTORY.md` remain out of
scope. The app-level photo pipeline is a separate deliverable
(`reimpl/photo_pipeline.py`, `reimpl/GAP_CLOSURE.md`); nothing here duplicates it.

## 1. Result

| metric | count |
|---|---:|
| models enumerated / registered | **33 / 33** |
| JAX reimplementations executed and cross-checked vs LiteRT | **17** |
| — reused `reimpl/bundles/` modules (no duplication) | 7 |
| — newly converted by `tools/tflite2jax.py` into `generated/` | 10 |
| JAX numeric PASS (≤ 1e-4 float, ≤ 0.02 quantized) | **15** |
| JAX semantic PASS (mask argmax agreement 1.0000; per-pixel drift from int8 emulation) | **2** |
| executed through the LiteRT reference (conversion skipped, real weights) | **15** |
| hard skip (no stock runtime can execute the file) | **1** |
| process errors / crashes in the sweep | **0** |

`demo_all.py` finishes with `{'litert_fallback': 15, 'pass': 15,
'pass_semantic': 2, 'skip': 1}` and exits 0; `verify.txt` records
`RESULT: PASS`.

## 2. Per-model status

Runner column: `jax` = newly generated module, `bundles` = existing
`reimpl/bundles/` module reused, `litert` = real weights executed by the LiteRT
reference interpreter because conversion is unsupported, `none` = skip.
Metric column: `max_abs` is JAX vs LiteRT on identical input tensors.

| # | model | bundle | runner | status | max_abs / metric |
|---|---|---|---|---|---|
| 1 | `dense_eb` | ai_beauty_type_processor | litert | litert_fallback | reference (no numeric diff) |
| 2 | `dense_eye_iris` | ai_beauty_type_processor | litert | litert_fallback | reference (no numeric diff) |
| 3 | `dense_iris` | ai_beauty_type_processor | jax | pass | max_abs 1.53e-05 |
| 4 | `dense_mouth` | ai_beauty_type_processor | jax | pass | max_abs 1.53e-05 |
| 5 | `face_landmark_lite_210818_360` | ai_beauty_type_processor | jax | pass | max_abs 7.82e-05 |
| 6 | `model_hair` | ai_beauty_type_processor | litert | litert_fallback | reference (no numeric diff) |
| 7 | `mouth_checker_14` | ai_beauty_type_processor | jax | pass | max_abs 5.96e-08 |
| 8 | `n-0.5_416_0.05_4aspect6` | ai_beauty_type_processor | litert | litert_fallback | reference (no numeric diff) |
| 9 | `n-small_256_0.05_only_a5` | ai_beauty_type_processor | litert | litert_fallback | reference (no numeric diff) |
| 10 | `s1_112_98` | ai_beauty_type_processor | jax | pass | max_abs 5.34e-05 |
| 11 | `s2` | ai_beauty_type_processor | litert | litert_fallback | reference (no numeric diff) |
| 12 | `s3` | ai_beauty_type_processor | litert | litert_fallback | reference (no numeric diff) |
| 13 | `Fprs.0003.Exp007e33_quan1_sm` | ai_face_beauty_processor | bundles | pass_semantic | max_abs 3.09e-01; argmax agreement 1.0000 |
| 14 | `FprsTeeth.0001.Exp002pe99_quan1_sm` | ai_face_beauty_processor | jax | pass_semantic | max_abs 5.55e-01; argmax agreement 1.0000 |
| 15 | `hair_1209a_acnenet_crop0.1pos0.1_3x24x24_quat` | ai_face_beauty_processor | bundles | pass | max_abs 2.24e-08 |
| 16 | `highlight_1209a_acnenet_crop0.2pos0.1_3x24x24_quat` | ai_face_beauty_processor | jax | pass | max_abs 1.68e-07 |
| 17 | `lines_1209a_acnenet_crop0.1pos0.1_3x24x24_quat` | ai_face_beauty_processor | jax | pass | max_abs 2.44e-19 |
| 18 | `mobile_spade_256_ch16_from16_40000_ft16_ftsave_yunfu` | ai_face_beauty_processor | jax | pass | max_abs 5.96e-07 |
| 19 | `mobile_spade_256_ch16_from16_ftsevere_lr0.0001_117_16001` | ai_face_beauty_processor | jax | pass | max_abs 8.27e-07 |
| 20 | `mole_seg_24x24x3_1206_quat` | ai_face_beauty_processor | bundles | pass | max_abs 4.29e-10 |
| 21 | `skin_model_expand_supply_pta18000_quant_resizetrain_seg153` | ai_face_beauty_processor | litert | litert_fallback | reference (no numeric diff) |
| 22 | `Fpe.0007.Exp101.ERefine3e70_refine1` | ai_face_processor_detect_landmark | litert | litert_fallback | reference (no numeric diff) |
| 23 | `Fpe.0007.Exp101.ERefine3e70_refine2` | ai_face_processor_detect_landmark | litert | litert_fallback | reference (no numeric diff) |
| 24 | `Fpe.0007.Exp101.ERefine3e70_refine3` | ai_face_processor_detect_landmark | litert | litert_fallback | reference (no numeric diff) |
| 25 | `Fpe.0007.Exp101.XScore.s76e150_base` | ai_face_processor_detect_landmark | litert | litert_fallback | reference (no numeric diff) |
| 26 | `face_landmark_mb360_64` | ai_face_processor_detect_landmark | bundles | pass | max_abs 8.01e-05 |
| 27 | `facecheck40_dense_2000000_regress` | ai_face_processor_detect_landmark | bundles | pass | max_abs 0 |
| 28 | `head` | ai_face_processor_detect_landmark | bundles | pass | max_abs 2.09e-07 |
| 29 | `n-0.5_416_0.05_4aspect6` | ai_face_processor_detect_landmark | litert | litert_fallback | reference (no numeric diff) |
| 30 | `n-small_256_0.05_only_a5` | ai_face_processor_detect_landmark | litert | litert_fallback | reference (no numeric diff) |
| 31 | `s1` | ai_face_processor_detect_landmark | bundles | pass | max_abs 3.81e-05 |
| 32 | `FULLBODY_POFH2D_20210302_0001_e1604_q` | ai_human_processor_beautybody_mb | litert | litert_fallback | reference (no numeric diff) |
| 33 | `human_detector_retinanet_mobilenetv2_1.0_quant` | ai_human_processor_beautybody_mb | none | skip | — |

`n-0.5_416` and `n-small_256` are shipped in two bundles; the files are
byte-identical (md5 `61d91c16…` / `1524bae5…`) and are registered per bundle
path because that is how `models/INVENTORY.md` counts them.

## 3. Why each conversion was skipped

| reason | models | detail |
|---|---|---|
| `PRELU` not lowered | `model_hair`, `refine1`, `refine2`, `refine3`, `XScore` | `tools/tflite2jax.py` has no PRELU lowering; `generated/*/unsupported.json` lists the op |
| `SPLIT` not lowered | `n-0.5_416` (×2 bundles), `n-small_256` (×2) | same; op list from the converter |
| dynamic `RESHAPE` shape | `dense_eb`, `dense_eye_iris`, `s2`, `s3`, `human_detector` | the reshape target is built from `SHAPE`/`PACK` at run time; converter status `unsupported_params` |
| converted module fails numeric parity | `skin_model_expand…`, `FULLBODY_POFH2D…` | fully-int8 graphs: the converter's float32 quantized-activation emulation returns an all-zero sigmoid for the skin net (`max_abs` 0.996) and decorrelated heatmaps for the body net (`max_abs` 254.8 dequant units, `verify.json`); these two run through LiteRT |
| hard skip | `human_detector_retinanet…` | converter blocked on dynamic `RESHAPE` **and** stock LiteRT refuses the file: CONV_2D weights carry per-channel `zero_point = -127`, rejected by both XNNPACK ("unsupported zero-point value (-127) in channel 130") and the reference kernels (`conv.cc:390 affine_quantization->zero_point->data[i] != 0`). Only FuAI's vendored runtime accepts it. |

`generated/` keeps `unsupported.json` / `ir.json` / `verify.json` for every one
of these, so the reasons are machine-readable and were not inferred here.

## 4. Pre/post-processing contracts implemented

Input construction (`preprocess.py`) follows `PIPELINE.md` §3: frame → crop/align
(the app uses `mean_shape`; the runner uses a centre resize of the supplied
frame) → `image_width × image_height × image_channels` → `x/255` for FLOAT32
inputs, raw uint8 for `Input_img`, and deterministic landmark/heatmap/feature
tensors for stage models whose auxiliary inputs come from the preceding network.
Post-processing (`postprocess.py`) follows §5 and the bundle configs:

| family | net contract | post-processing |
|---|---|---|
| `landmarks`, `landmarks_score`, `landmarks_multi`, `landmarks_refine` | `[1,H,W,1]`/`[1,H,W,3]`/features → flattened point lists, `[2]` scores | point count, coordinate frame (normalized / pixels / network offsets), first 5 points, softmax scores |
| `binary_score` | `[1,1]` regression (`facecheck40`, no fused LOGISTIC) | sigmoid, `is_face` at 0.5 |
| `binary_class` | `[1,2]` softmax (del-spot 24×24 patches, mouth checker) | argmax class + confidence |
| `vector14` | `[1,126]` → `[1,14]` (`head`) | 14 regression values |
| `mask_multiclass` | `[1,512,384,15]` / `[1,192,256,4]` softmax | argmax class map, per-channel fractions, `cate_mask_order` UI mapping (15-class), `teeth_mask_order = 3` (4-class) |
| `mask_binary` | `[1,320,320,1]` / `[1,24,24,1]` | threshold from the bundle config — skin 0.6 (`face_skin_seg.mask_threshold`), mole 0.13 (`face_del_spot_prepro.thresh_val`) — connected components ≥ `keep_block_radio 0.01`, coverage |
| `image_tanh` | `[1,256,256,3]` Tanh SPADE generators | `[-1,1] → [0,1]`, range statistics |
| `detector_anchor` | `[N,1]` score + `[N,4]` ltrb + `[N,C]` classes | sigmoid (when logits), best anchor/score |
| `detector_boxes` | RetinaNet `[N,1]` score + `[N,4]` boxes | best box/score, count above 0.3 |
| `pose_heatmaps` | uint8 `hms 64×48×31`, `pofs 64×48×78`, `bbfs 8×6×192` | dequantize with the tensor scale/zero-point, per-joint heatmap argmax, POF layout |

## 5. Verification method

* Inputs are identical NumPy arrays for both paths (synthetic 256×256 frame by
  default; `--image` for a real frame: PNG/PGM/PPM/BMP/NPY, JPEG when Pillow is
  importable).
* Reference = `ai_edge_litert` `Interpreter` with
  `BUILTIN_WITHOUT_DEFAULT_DELEGATES` (reference kernels, no XNNPACK
  approximations), i.e. the same semantics FuAI vendors.
* Tolerances: 1e-4 float32, 1e-2 float16, 0.02 dequantized int8/uint8, exact
  ints/bools.
* Mask models additionally report `argmax_class_agreement`; that is the metric
  the whitening/parsing consumer uses. It is 1.0000 for both mask nets even
  though the converter's int8 emulation leaves per-pixel probability drift (the
  same limitation already recorded for `Fprs` in `reimpl/README.md` §3).

## 6. Limits (unchanged by this task)

* 5 models are encrypted (`acne_mole_acnenet…`, `age_gender160_0422`,
  `Bdycntr…`, `bindi_seg_tiny…`, `MOBILEONE_S2…`); 3 bundle `config.json`s and
  39 shader/image entries share the missing 1024-byte prefix key — out of scope
  per assignment.
* The float normalisation constant is not in the recovered configs (PIPELINE §3
  step 5, unknown U3). Runners use `x/255` and say so in `demo_report.json`.
  The app-pipeline task recovered per-model first-op scales for the three nets
  it runs; the runner default is unchanged for the other 30.
* Stage models (`s2`, `s3`, `dense_*`, refine nets) receive placeholder
  landmark/heatmap tensors because those are produced by the preceding pipeline
  stage; the pixel input is real and the graph executes with real weights.
* Detector anchor→pixel decode needs the per-level stride table that lives in
  the encrypted bundle config; the reports expose anchor ids/scores only.
* Landmark coordinate frames differ per net (observed: normalised for
  `dense_iris/Identity`, pixels for `s1`, offsets for `dense_mouth`); the runner
  reports the observed frame instead of forcing one interpretation.

## 7. Files

| file | role |
|---|---|
| `reimpl/model_runners/specs.py` | registry of all 33 models (bundle, family, JAX dir / skip reason) |
| `reimpl/model_runners/run_model.py` | `--model NAME [--image FILE] [--list] [--json OUT]` |
| `reimpl/model_runners/demo_all.py` | runs all 33, writes `demo_report.json`, exit 0 |
| `reimpl/model_runners/runner.py` | JAX + LiteRT execution, input binding, verification |
| `reimpl/model_runners/preprocess.py` | family input construction (resize/normalise, stage placeholders) |
| `reimpl/model_runners/postprocess.py` | family output decoding |
| `reimpl/model_runners/image_io.py` | dependency-free PNG/PNM/BMP/NPY loader + bilinear resize |
| `reimpl/model_runners/convert_models.py` | reproduces the `generated/` modules with `tools/tflite2jax.py` |
| `reimpl/model_runners/make_verify.py` | writes `verify.txt` |
| `reimpl/model_runners/generated/<model>/` | `model.py` + `params.npz` + `ir.json` + `verify.{txt,json}` (or `unsupported.json`) |
| `reimpl/model_runners/demo_report.json` | per-model status, ops, inputs, outputs, verification |
| `reimpl/model_runners/verify.txt` | transcript of the CLI checks and the 33-model sweep |
| `reimpl/model_runners/test_frame.ppm` | deterministic 240×180 frame used by the `--image` spot check |

## 8. Reproduce

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
PY=$WS/tooling/venv312/bin/python
cd "$WS/reports/miui_mediaeditor/reimpl/model_runners"

$PY convert_models.py          # 10 new modules (idempotent; skipped ones stay in generated/)
$PY demo_all.py                # 33 models -> demo_report.json, exit 0
$PY make_verify.py             # -> verify.txt, exit 0
$PY run_model.py --model head
$PY run_model.py --model Fprs.0003 --image test_frame.ppm
$PY run_model.py --list
```
