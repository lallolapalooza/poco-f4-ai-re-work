# Reimplementation coverage — models vs data pipelines

Status: 2026-10-26 (gap-closure pass).

Two different questions:

1. **Can code load and run the extracted model?** (`reimpl/`)
2. **Can code reproduce the app's data path around it?**
   (source → preprocess → model → postprocess → consumer)

`PIPELINE.md` documents the data path for every model. Executable examples now
cover most non-encrypted models, but not every file has its own runner.

## Summary

| App | Extracted weights | Runnable model code | App-data pipeline | Scope |
|---|---|---:|---|---|
| `miui_securitycenter` | 1 legacy cascade + dict/pattern | 1 | **complete** (P1) | SMS body → dict segmentation → features → cascade → verdict |
| `miui_securitycoreadd` | 1 TFLite (7 subgraphs) | 1 | **complete** | lux/span/category → 3 floats → MLP → nits |
| `miui_mediaeditor` | 33 usable TFLite + 5 encrypted; 22 bundles | 32 | **partial** | photo beauty path; no video |
| `miui_extraphoto` | DLC + 4 MACE caches + 5 embedded MACE blocks | 1 | **partial** | document DLC; MACE weights no graph |
| `miui_biometric` | 8 encrypted blobs | 0 | none | encrypted |
| `miui_paymentservice` | 2 LIBSVM | 2 | **complete** | NV21 → HOG → SVM → digits |
| `miui_remote` | 1 VAD | 1 | **complete** | PCM → VAD → Opus → cloud stub → IR/TTS |
| `qcom_voiceactivation` | 7 `.uim` + 16 EAI2 blobs | 1 | **partial** | VAD exact; keyword stages parsed, not numeric |
| `mtb_modemtestbox` | 1 TFLite | 1 | **complete** | radio samples → median → model → broadcast |
| `snapchat` | no DNN; Haar/ERT/ORB/lenses | 4 | **complete** | OES frame → classical CV → AR render |
| `tiktok_musically` | 3 bundles / 12 records | 6 | none | model-level records only |
| `tiktok_trill` | 3 bundles / 12 records | 6 | none | byte-identical to musically |
| `kwai_video` | none | 0 | none | models downloaded |
| `kwai_bulldog` | none | 0 | none | models downloaded |
| `kwai_live` | none | 0 | none | models downloaded |

Counts: **50 unique model/asset units** have runnable code (56 including the
byte-identical TikTok duplicate); **6 apps** have a complete app-data pipeline,
**3** have a partial one. Encrypted, downloaded, or
graph-less models remain documented but not executable.

## Gap-closure results

### MediaEditor

- `reimpl/model_runners/` registers all 33 usable TFLite models; `demo_all.py`
  runs 32 with real weights:
  - 15 JAX numeric PASS vs LiteRT (worst max_abs 8.0e-05),
  - 2 JAX semantic PASS (Fprs/FprsTeeth argmax 1.0000),
  - 15 LiteRT fallbacks (PRELU/SPLIT/dynamic RESHAPE/int8),
  - 1 hard skip: `human_detector_retinanet_mobilenetv2_1.0_quant`
    (per-channel zero_point -127 rejected by stock LiteRT/XNNPACK).
- `reimpl/photo_pipeline.py` runs a real photo path: EXIF decode → landmark
  (mb360 64×64 gray, 77 points) → face parsing (Fprs 512×384, 15 classes) →
  skin segmentation (320×320, threshold 0.6) → skin-white LUT blend → PNG.
  The face quad is a skin-chroma stand-in because the YOLO detector head/anchor
  decode is not recovered; mask UV warps and the full GL stack are not executed.
- 5 TFLite and 3 configs remain encrypted (`acne_mole`, `age_gender160`,
  `Bdycntr`, `bindi_seg_tiny`, `MOBILEONE_S2`); no key.

### SecurityCenter

- `reimpl/pipeline_sms.py` implements the P1 path:
  SMS body/sender → `ah.h.c` normaliser → `dh.j/dh.b/dh.c` rule stages →
  `bh.a.c` DAG segmentation over `assets/dict` → per-stage masks → 5-stage
  cascade → verdict/block type.
- `--selftest` passes 45/45; `--demo` gives verdicts `[2,2,0,0]` (2 spam
  blocked, 2 ham allowed). `dh.c.i` digit-run/BankCard and `assets/phish` remain
  unreachable; new-engine `1.bin`/`2.bin` is downloaded.

### SecurityCoreAdd

- `reimpl/pipeline_lux.py` implements
  `(ambientLux, luxSpanIndex, appCategoryId) → [ln(x+1)] → model → nits`,
  with NaN/negative rejection and 8 example cases (3.858–944.371 nits).
- Cross-check vs shipped flatbuffer: old model 1.8e-4 nits; new student
  2.4e-7. The shipped `new_model_infer` is an untrained student; serving uses
  `old_model_infer`, as the app does.

### Qualcomm Voice Activation

- `reimpl/eai_loader.py` decodes the EAI2 container and all 16 blobs
  (tensor descriptors, layer records, op ids, weights).
- `reimpl/run_vad.py` runs `speech_float_speech_0.eai` end-to-end with real
  float32 weights; matches Qualcomm's own interpreter bit-identically on the
  softmax output (max diff 0.0).
- 1st/1.5-stage keyword graphs and `general_UV_015` are parsed but their
  framer/GRU/conv semantics are not numerically reproduced; 4 of 16 blobs are
  refused by the shipped runtime by design.

### TikTok (both variants)

- The “opaque” descriptor is **AES-256-ECB-encrypted net-description text**.
  Auth codes are inline immediates in `libeffect.so`; the key is
  `auth_code[0:32]`, and the key field decrypts to a 32-byte net key.
- 11/12 records decode; 6 execute with real weights (detect, base120det, pout,
  eyecls, image, slowimage, mean); 4 have byte-exact weight buffers.
- `run_record.py` implements Convolution, DepthwiseSeparable, Pooling, Eltwise,
  InnerProduct, Softmax, ReLU, Sigmoid, Concat.
- Remaining: `agenet` auth code (1 record), int8/int16 dequant scale, and
  Slice/Split multi-top lines (base120/partface/iris/extra).

### ExtraPhoto

- `reimpl/mace_loader.py` recovers the embedded MACE model grammar: 5
  description blocks (43,656 B), 466 constant tensors, 6 fp16 weight buffers
  (18,650,700 B) with names, byte ranges, shapes for `hed_model`,
  `enhance_model`, `dewarp_fine_model`; `--check` passes (0 errors).
- No MACE NetDef/graph exists statically (MacePlatform builds it at runtime), so
  no inference is claimed; `graph_decoded=false`, `inference_available=false`.

## Remaining blockers

| Area | Blocker |
|---|---|
| Biometric | 8 STEF blobs encrypted; runtime licence key |
| MediaEditor | 5 encrypted TFLite + 3 configs; 1 stock-runtime-incompatible detector; YOLO head decode; video path |
| TikTok | `agenet` auth code; int8/int16 weight scale; Slice/Split ops; no VESDK app harness |
| Qualcomm | keyword-stage GRU/framer semantics; 4 runtime-refused blobs; VAD 160-dim front-end |
| ExtraPhoto | MACE operator graph absent; ArcSoft/Megvii weights embedded |
| SecurityCenter | new-engine TFLite downloaded; `dh.c.i`/`assets/phish` gaps |
| Kwai | YCNN/effect models downloaded, not in APK |
| Snapchat | no DNN; `.dnn.enc` downloaded, ML Kit external, Memories server-side |

## Run the examples

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
PY=$WS/tooling/venv312/bin/python

$PY "$WS/reports/miui_mediaeditor/reimpl/model_runners/demo_all.py"
$PY "$WS/reports/miui_mediaeditor/reimpl/photo_pipeline.py"
$PY "$WS/reports/miui_securitycenter/reimpl/pipeline_sms.py" --selftest
$PY "$WS/reports/miui_securitycenter/reimpl/pipeline_sms.py" --demo
$PY "$WS/reports/miui_securitycoreadd/reimpl/pipeline_lux.py"
$PY "$WS/reports/qcom_voiceactivation/reimpl/run_vad.py"
$PY "$WS/reports/qcom_voiceactivation/reimpl/verify_eai.py"
$PY "$WS/reports/tiktok_trill/reimpl/verify.py"
$PY "$WS/reports/miui_extraphoto/reimpl/mace_loader.py" --check "$WS/reports/miui_extraphoto/models/embedded/mace"
```

`PIPELINE.md` remains authoritative for models without an executable runner.
