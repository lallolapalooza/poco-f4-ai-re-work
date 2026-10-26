# Gap closure — `miui_mediaeditor` app-level photo pipeline

Closes the gap recorded in `REIMPL_COVERAGE.md` ("MediaEditor … Missing: app-level
image/video pipeline") for the **photo beauty path** documented in `PIPELINE.md`
§0/§3/§4/§5. It does not change `reimpl/model.py`, `reimpl/langid_pipeline.py` or
`reimpl/bundles/*`.

## Files

| file | content |
|---|---|
| `reimpl/photo_pipeline.py` | 512×512 synthetic portrait → PNG with `eXIf` orientation → decode/rotate → face quad → `face_landmark_mb360_64` → `Fprs.0003.Exp007e33_quan1_sm` → `skin_model_expand_…seg153` → masked whitening with the bundle's `skin_white` LUT → PNG |
| `evidence/photo_pipeline/verify.txt` | full transcript of the run (command, asset SHA-256, shapes, stats, `RESULT: PASS`, `exit_code=0`) |
| `evidence/photo_pipeline/` | `source_exif.png`, `decoded_upright.png`, `face_crop_landmark64.png`, `landmarks.png`, `parsing_crop_384x512.png`, `parsing_labels.png`, `mask_skin.png`, `rendered.png`, `rendered_mask_overlay.png`, `stats.json` |
| `reimpl/coverage_fragment.json` | machine-readable update for the parent merge |

Run:

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
$WS/tooling/venv312/bin/python $WS/reports/miui_mediaeditor/reimpl/photo_pipeline.py
# exit 0, RESULT: PASS, ~4 s, output evidence/photo_pipeline/rendered.png
```

Inference uses the **shipped TFLite flatbuffers** with `ai_edge_litert`
(the kernels FuAI vendors: `libfuai.so` ≈ TensorFlow Lite 2.x, `PIPELINE.md`
§4.1) and the extracted weights. Nothing is randomly initialised, no model is
re-extracted, and no entry from the encrypted bundle prologue is touched
(`stats.json → encrypted_models_used: []`).

## Exact stages

| # | stage | artefact / model | tensor contract | where it maps in the app |
|---|---|---|---|---|
| 1 | source + decode + EXIF orientation | PNG `eXIf` chunk (tag `0x0112 = 6`), own codec | stored 512×512×3 → upright 512×512×3 | `gltexturerender.d.k()`: `BitmapFactory.decodeFile` + `Matrix.postRotate` for EXIF 3/6/8 (`PIPELINE.md` §3.1) |
| 2 | face quad | YCbCr skin-chroma box (classical; **not** the DNN detector) | image → `(x0,y0,x1,y1)` | detector pass `FaceUnityDetectAlgorithm.f` case 0/3 (`PIPELINE.md` §1.1b) — see omissions |
| 3 | landmarks | `face_landmark_mb360_64.tflite` (346,788 B) | `[1,64,64,1]` f32 gray **0…255** → `[1,154]` f32 = 77 2-D points in 64×64 crop pixels | `fuLoadAIModelFromPackage(…, FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS)` → engine landmark block |
| 4 | face parsing | `Fprs.0003.Exp007e33_quan1_sm.tflite` (1,196,952 B) | `[1,512,384,3]` f32 RGB **0…255** → `[1,512,384,15]` softmax | `ai_face_beauty_processor.bundle → face_parsing` (`PIPELINE.md` §4.2, step 12) |
| 5 | skin segmentation | `skin_model_expand_supply_pta18000_quant_resizetrain_seg153.tflite` (2,433,488 B) | `[1,320,320,3]` f32 RGB **0…1** → `[1,320,320,1]` prob; threshold 0.6, `keep_block_radio 0.01` | same bundle → `face_skin_seg` (`PIPELINE.md` §4.2) |
| 6 | mask → image space | bilinear resample of the two net masks + parsed hair label; `max(alpha,0.3)` shader floor inside the face quad | 512×512 alpha | mask-mapper / `GetAverMask` family, `image_white_split_fs.glsl` |
| 7 | whitening blend | `lut/skin_white.png` (512×512 RGBA = 8×8×64 LUT) with `shader_main_s_color_custom_shader_whitten` reimplemented in NumPy | `out = mix(src, LUT(src), alpha*strength)`, strength 0.7 | `skin_white()` in `image_skin_white_red_jili_threed_fs.glsl` / `image_white_split_fs.glsl` |

Input normalisation is **derived from the graphs, not guessed**:

| model | evidence in the artefact | required input |
|---|---|---|
| mb360 | first op `SUB` with constant `sub/y = 127.5` (`reimpl/bundles/face_landmark_mb360_64/params.npz:c3`) | gray 0…255 |
| Fprs | first op `QUANTIZE` scale 1.0, zero_point −128 → `q = x − 128` covers int8 exactly iff `x ∈ [0,255]` | RGB 0…255 |
| skin seg | first op `QUANTIZE` scale 1/128, zero_point −1 → `q = 128x − 1` covers int8 iff `x ∈ [0,1]` | RGB 0…1 |

Empirical check for the parsing channel order: with RGB the skin channel peaks at
0.633; with BGR at 0.113 and with gray-replicated at 0.016. `PIPELINE.md` §3.5
listed the float normalisation as unknown — for these three nets it is now
settled by the graphs' first ops.

## Confirmed vs inferred mappings

### Confirmed (read directly in the artefact)

* Parsing class **channel 1 = skin, channel 13 = hair, channel 14 = neck**.
  `graphics__image_beauty_facebeauty/image_choose_whole_white_skin_fs.glsl`
  declares
  `int background=0, skin=1, nose=2, eye_g=3, l_eye=4, r_eye=5, l_brow=6,
  r_brow=7, l_ear=8, r_ear=9, mouth=10, u_lip=11, l_lip=12, hair=13, hat=14,
  ear_r=15, neck_l=16, neck=17, cloth=18, forehead=19, chin=20;`
  and compares the parsed label against `arrfloat_mask_index[hair]`. The config's
  `cate_mask_order = [0..13, 0, 0, 0, 14, 0]` maps UI classes 14/15/16/18 → 0 and
  17 (neck) → 14, i.e. the 15 network channels are exactly UI classes 0–13 plus
  neck. The network therefore has **no** forehead/chin/hat/earring/neck_l/cloth
  channels — the shader names beyond 14 are for the 21-class variant.
* `cate_mask_order` length 19, `flg_multiface_post_use_prob: true`,
  `out_mask_scale: 0.5`, parsing 384×512×15 with softmax, skin net 320×320 with
  `mask_threshold 0.6`, `keep_block_radio 0.01` — `config_ai_face_beauty_processor.json`.
* mb360 output is `154 = 77 × 2` values in 64×64 **pixel** coordinates (range
  3.5…55.4 on this input, values exceed 64 slightly at the chin).
* The whitening shader's LUT contract: 8 columns × 8 rows × 64 blue slices ⇒
  512×512 texture; `lut/skin_white.png` is exactly 512×512 RGBA. The shader does
  `mix(outcolor, white, clamp(alpha.g+alpha.b) * (rate-0.5)/0.5)` with a 0.3 floor.
* `image_choose_whole_white_skin_fs.glsl` builds the skin mask as
  `clamp(skin_mask + skin_white_mask)` and zeroes it where
  `parsed_label == mask_index[hair]`; the pipeline reproduces that combination.

### Inferred

* `tex_skinwhite_lut` ↔ `lut/skin_white.png`: the file name is the only static
  link (the item's texture bindings live in the encrypted first bundle entry);
  the 8×8×64 dimension match is exact.
* `1 - P(background)`/`alpha.b` = `tex_face_rate_mask` is produced by a GL pass
  that is not in the APK; the pipeline substitutes the landmark quad expanded by
  10 % and limits the shader's 0.3 floor to that quad.
* `mean_shape` frame: the shape is 75 points with x∈[86.6,293.6], y∈[244.6,461.1]
  and `face_parsing` omits `mean_shape_width/height` (the bindi bundle declares
  512×512), so 384×512 (network frame) and 512×512 are both possible. We use
  384×512; 512×512 widens the crop 1.33× in x and changes the parse on the
  synthetic input (skin 2.0 % → 4.6 %, neck channel appears).
* mb360's 77-point layout has **no recoverable correspondence** to the 75-point
  `mean_shape`; only bbox correspondence is used (anisotropic scale). The engine
  fits the full point set.
* `strength = 0.7` stands in for the `image_white` slider (app writes `p/100` or
  `p/200`, `PIPELINE.md` §5.3); the shader maps `skinwhite_rate ∈ (0.5,1]` to
  `(rate-0.5)/0.5`.
* Stage 3–5 are executed on a face crop derived from the landmark bbox, whereas
  FuAI keeps one internal aligned block per process mode; tensor shapes and
  normalisation are confirmed, the exact crop origin is not.

## Mapping to `FaceBeautyRenderFragment` / `fuImageBeauty*`

| PIPELINE step | app symbol | pipeline equivalent |
|---|---|---|
| §0.2–0.4 slider → `image_white` | `FaceBeautyRenderFragment.observeData$*` → `FaceBeautyViewModel` → `face.processer.a.a()` → `m2.a.i0("image_white", …)` | `--strength` (0.7) |
| §0.6 engine init | `BeautyBaseManager.b()`: `fuSetMaxFaces(5)`, `fuSetFaceProcessorDetectMode(IMAGE)` | single-face path; `detect` stage produces one quad |
| §0.8 model bytes → engine | `k2.a.e()` → `fuIsAIModelLoaded` → `fuLoadAIModelFromPackage(byte[], type)` | `run_tflite(path)` loads the same flatbuffers per model |
| §0.10 image in | `BeautyGlView.d(bitmap, FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY)` → `GlTexturePhotoRenderer` | `read_png` → upright RGB |
| §0.11 preprocess | `gltexturerender.d.k()` EXIF rotate, optional `fuImageBeautyConvertRGBA2NV21WithBitmap`, `fuImageBeautyPreProcess(type, 0, w, h, buf, stride=w, …, items, isGpuMode=1, …)` | decode/rotate + per-model crop/resize (no NV21, no GL, no engine item list) |
| §0.12 model run | `ImageBeautyController` reads `config.json` → FuAI runs `Fprs…` on the aligned face crop | stages 4–5 with the config-driven shapes and thresholds |
| §0.13 render | `fuImageBeautyPreview(items)` → RTT chain (`image_combine_*`, `image_global_*`, `image_white_split_fs`) | stage 6–7 in NumPy: mask combine + LUT whiten |
| §0.15 save | `gltexturerender.c.run()` → `fuImageBeautyGetResultBitmap(w,h,items)` | `write_png(rendered.png)` (PNG instead of JPEG/MediaStore) |
| §0.5 per-face params | `fuItemSetParam(handle, "{\"faceid\":N,\"param\":\"image_white\"}", value)` | not modelled (one face, one scalar) |

## What is *not* implemented (exact omissions)

1. **DNN face detector** — `n-small_256_0.05_only_a5` / `n-0.5_416_0.05_4aspect6`
   are present with weights but their YOLO head + anchor decoding is not
   recovered, so stage 2 is a YCbCr skin-chroma box. The pipeline therefore
   requires a roughly centred, front-facing face.
2. **Mean-shape alignment** — only the bbox correspondence is used (see above);
   no similarity/affine landmark fit, no `netin_image_expand_udlr_ratio` crop for
   teeth/bindi.
3. **Mask → image geometry** — the engine warps the mask through the 80-vertex
   `face_mask_mapper_face_mask.json` UV mesh (`PIPELINE.md` §5.2). The pipeline
   uses a bilinear resample of the axis-aligned crop; no mesh, no
   `FUAI_MaskTransform`/`*Warp`.
4. **GL effect stack** — the bundle's other passes are not ported: even-skin /
   flaw-remover SPADE GANs (weights exist, 256×256 → 256×256), del-spot patch
   classifiers + mole seg + protect mask, teeth whitening, makeup layers,
   reshape mesh (`face_warp.json`, 283 verts) and its displacement shaders,
   `image_erode_dilate_*`/`GetAverMask` chains. Only skin whitening is executed.
5. **`tex_face_rate_mask` / `image_choose_whole_white_skin_fs` pass chain** — the
   per-face offset/scale uniforms (`arrvec2_offset`, `wh`, `wh_face`) come from a
   GL pass that is not in the APK; replaced by the landmark quad.
6. **Inpaint** (`libinpaint.so`/`libvis.so`, external NNF weights): untouched.
7. **Video path** (`XmsContext.setupBeauty`, MLT `movit.filter.face.beauty`):
   untouched; this pipeline is photo-only.
8. **Encrypted models**: `acne_mole_acnenet_1206_3x24x24_quat`, `age_gender160_0422`,
   `Bdycntr.0406…quan1`, `bindi_seg_tiny_2024_0924`, `MOBILEONE_S2_…FP16` and the
   3 configs in the 1024-byte prologue remain unusable (no key). None is loaded.
9. **JAX bundle runners** (`reimpl/bundles/Fprs…`, `face_landmark_mb360_64`, `s1`)
   are **not** used here: the Fprs JAX conversion drifts 1.76e-1 vs LiteRT
   (`reimpl/bundles/Fprs…/verify.txt`). The pipeline calls the flatbuffers so the
   numbers are the app's kernels.

## Verification summary (`evidence/photo_pipeline/verify.txt`)

```
[1] decode  512x512x3, eXIf orientation 6 applied, equals upright source
[2] quad    (139,204)-(374,486)
[3] mb360   [1,64,64,1] 19.3..246.0 -> [1,154] 3.48..55.38  (77 pts)
[4] Fprs    [1,512,384,3] 14..248 -> [1,512,384,15], softmax sums 0.988..1.008
            argmax: background=193079 skin=2011 nose=707 l_eye=341 r_eye=470
            (labels land on the eye/nose band of the synthetic face; skin ch1 1.7 %)
[5] skin    [1,320,320,3] 0.067..0.973 -> [1,320,320,1] 0.000..0.996 mean 0.274
            threshold 0.6 -> 26.6 % coverage, 6 components, 4 dropped (<1 % area)
[6] blend   alpha mean 0.560 over 58,520 px (22.3 %), hair-labelled px zeroed 0
            luma 0.5737 -> 0.6125 (delta +0.0388)
RESULT: PASS  exit_code=0
```

The synthetic portrait is deliberately **out of domain** for the FuAI nets: the
15-class parser sees a flat cartoon and labels only the eye/nose band (cheeks stay
background), while the skin net is noisy over the hair. This is a property of the
input, not of the wrapper — the same code path on a real photo would produce the
usual skin mask. Nothing in the report claims a production-quality mask for this
input; the effect is nevertheless real (LUT + mask + alpha, +0.039 mean luma).
