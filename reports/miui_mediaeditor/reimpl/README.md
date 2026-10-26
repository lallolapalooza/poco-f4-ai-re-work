# reimpl/ — runnable reimplementations

Python 3.12, `numpy`, `jax`, and the workspace `tools/` TFLite helpers.
`PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python`.

## 1. Language identification (`assets/tflite_langid.tflite.jpg`)

```
$PY extract_params.py          # models/tflite_langid.tflite -> params.npz
$PY langid_pipeline.py "Hello world"
$PY model.py "Hello, how are you doing today?"
$PY verify.py                  # writes verify.txt, exit 0 = PASS
```

| file | role |
|---|---|
| `langid_pipeline.py` | NGramHash custom op: tokenise -> MurmurHash64A -> `% vocab + 1`, plus padding |
| `model.py` | JAX: KmeansEmbeddingLookup (mean-pooled codebook lookup) + 3 hybrid FC + softmax |
| `reference_numpy.py` | independent NumPy implementation of the same graph |
| `verify.py` | numerical cross-check + 28-sentence multilingual accuracy |
| `extract_params.py` | flatbuffer -> `params.npz` (+ `IndexToClassID` strings, threshold) |

`params.npz` holds the real weights (int8 FC matrices with their per-tensor
scales, float32 bias, the four `b_matrix` codebooks and `c_matrix` codes), so
this is a **verified** reimplementation, not architecture-only.

## 2. `.bundle` container decoder

```
$PY unbundle.py list    ../work/assets/assets/model/ai_face_processor_detect_landmark.bundle
$PY unbundle.py extract <bundle> <outdir>
```

Decodes the FuAI container to the embedded ZIP (see `../report.md` §3).
663 of 683 entries across the 22 bundles are extracted with matching ZIP
CRC32; the remaining 20 live in the 1024-byte encrypted prefix.

## 3. Bundle models converted to JAX (`bundles/`)

Generated with `tools/tflite2jax.py` and verified against the real LiteRT
interpreter with `tools/verify_tflite_jax.py` (tolerance 1e-4):

| directory | source model | max abs error | status |
|---|---|---|---|
| `bundles/face_landmark_mb360_64` | ai_face_processor_detect_landmark.bundle | 1.53e-05 | PASS |
| `bundles/s1` | ai_face_processor_detect_landmark.bundle (`s1.tflite`, 91 landmarks + score) | 1.91e-05 | PASS |
| `bundles/head` | ai_face_processor_detect_landmark.bundle (`head.tflite`, FC 126->14) | 2.09e-07 | PASS |
| `bundles/facecheck40_dense_2000000_regress` | ai_face_processor_detect_landmark.bundle (face/non-face) | 0 | PASS |
| `bundles/hair_1209a_acnenet_crop0.1pos0.1_3x24x24_quat` | ai_face_beauty_processor.bundle (del-spot CNN) | 0 | PASS |
| `bundles/mole_seg_24x24x3_1206_quat` | ai_face_beauty_processor.bundle (mole seg) | 3.76e-06 | PASS |
| `bundles/Fprs.0003.Exp007e33_quan1_sm` | ai_face_beauty_processor.bundle (512x384x15 face parsing) | 1.76e-01 | FAIL (quantized graph, see report unknown U4) |

Each directory contains `model.py` (self-contained JAX: `init_params()` +
`apply(params, x)`), `params.npz` (real weights), `ir.json`, `verify.txt`.

Run one directly:

```
cd bundles/face_landmark_mb360_64 && $PY -c "
import model, jax.numpy as jnp, numpy as np
p = model.init_params(); y = model.apply(p, jnp.zeros((1,64,64,1)))
print(np.asarray(y).shape)"
```

## 4. Per-model runners for all 33 usable TFLite models (`model_runners/`)

```
$PY model_runners/convert_models.py     # rebuild generated/ modules via tools/tflite2jax.py
$PY model_runners/demo_all.py           # all 33 models -> model_runners/demo_report.json, exit 0
$PY model_runners/make_verify.py        # -> model_runners/verify.txt, exit 0
$PY model_runners/run_model.py --list
$PY model_runners/run_model.py --model head
$PY model_runners/run_model.py --model Fprs.0003 --image model_runners/test_frame.ppm
```

`specs.py` lists every `models/tflite/*.tflite` with its bundle, data-path family,
and either the JAX module used (7 of the existing `bundles/` modules are reused,
10 more live in `model_runners/generated/`) or the precise reason the converter
was skipped. Models the converter cannot lower are still executed on the real
weights through `ai_edge_litert`; the one file no stock runtime accepts
(`human_detector…`, per-channel `zero_point -127`) is recorded as `skip`.

Sweep result: 15 JAX numeric PASS, 2 JAX semantic PASS (mask argmax agreement
1.0000), 15 LiteRT reference runs, 1 skip, 0 errors. Pre/post contracts
(resize/normalise, landmark reshape, mask sigmoid/threshold, class argmax,
detector/anchor decode, Tanh image mapping) are implemented in
`model_runners/preprocess.py` / `postprocess.py`; the full per-model table and
the limitations are in `GAP_CLOSURE.md`.

## 5. App-level photo pipeline (`photo_pipeline.py`)

```
$PY photo_pipeline.py                      # synthetic 512x512 portrait, eXIf orientation 6
$PY photo_pipeline.py --input photo.png    # any 8-bit RGB/RGBA PNG
$PY photo_pipeline.py --strength 0.7 --out ../evidence/photo_pipeline
```

Stages (all inference on the shipped flatbuffers via `ai_edge_litert`, real
weights, no encrypted bundle entry):

| stage | model | contract (normalisation read from the graph's first op) |
|---|---|---|
| decode + EXIF | — (PNG + `eXIf`, TIFF tag 0x0112) | stored raster → upright RGB |
| face quad | YCbCr skin-chroma stand-in (the DNN detector heads are not decoded) | image → `(x0,y0,x1,y1)` |
| landmarks | `face_landmark_mb360_64` | `[1,64,64,1]` f32 gray 0…255 → `[1,154]` (77 pts, 64² crop pixels) |
| face parsing | `Fprs.0003.Exp007e33_quan1_sm` | `[1,512,384,3]` f32 RGB 0…255 → `[1,512,384,15]` softmax |
| skin seg | `skin_model_expand_supply_…seg153` | `[1,320,320,3]` f32 RGB 0…1 → `[1,320,320,1]`, thr 0.6, keep ≥1 % area |
| effect | `lut/skin_white.png` (512×512 = 8×8×64) | `image_white_split_fs.glsl` whiten LUT, alpha = skin mask + parsing skin ch1, hair (ch 13) zeroed, shader 0.3 floor inside the landmark quad |

Parsing channel semantics are confirmed from
`graphics__image_beauty_facebeauty/image_choose_whole_white_skin_fs.glsl`
(skin = 1, hair = 13, neck = 14); `mean_shape` is used as a bbox fit only, since
the 77-point mb360 layout has no recoverable correspondence to the 75-point
shape. Outputs land in `evidence/photo_pipeline/` (`verify.txt`, `stats.json`,
`rendered.png`, `source_exif.png`, `decoded_upright.png`, `landmarks.png`,
`parsing_labels.png`, `mask_skin.png`). Limitations and the exact mapping to
`FaceBeautyRenderFragment` / `fuImageBeauty*` are in `GAP_CLOSURE.md`.
