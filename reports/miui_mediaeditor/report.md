# Mi Media Editor (`com.miui.mediaeditor`) — AI/ML subsystem

| | |
|---|---|
| APK | `product/data-app/MIMediaEditorGlobal/MIMediaEditorGlobal.apk` |
| package / version | `com.miui.mediaeditor`, 1.9.0.6-global (code 4784320) |
| label / main activity | Gallery Editor / `com.miui.mediaeditor.MainActivity` |
| size / sha256 | 216,457,464 B / `98e2ce9e30e21d47f1b912b14721bf85fae044c045105da9561b4778b54bd976` |
| sdk / abi | minSdk 30, targetSdk 34, arm64-v8a only |
| dex | 2 (`classes.dex`, `classes2.dex`) |

All paths below are relative to this report directory
(`$WS/reports/miui_mediaeditor`). Command transcripts are in `evidence/`,
native artefacts in `native/`, model IR in `ir/`.

---

## 1. What the AI subsystem is

Two independent inference stacks ship in the APK, plus a thin Mi-side wrapper
around system/plugin libraries.

1. **FaceUnity (相芯) Nama + FuAI** — the beauty/reshape/makeup engine.
   * `libCNamaSDK.so` (14.4 MB, not stripped: 17,583 dynamic symbols;
     mbedTLS (`AES-*-CBC` cipher names, `fu_mbedtls_*`), nlohmann/json,
     duktape (`duk_hthread`), EGL/GLES, and it *re-exports* the FuAI TFLite
     API: `FUAI_NewTfliteModel`, `FUAI_TfliteModelRun`, …) — renderer,
     asset/zip loader, texture decoding, FaceUnity bundle decryption.
   * `libfuai.so` (7.65 MB, NDK r23c, 674 `FUAI_*` C exports) — the model
     runtime. It vendors its **own TensorFlow Lite** copy: exports
     `FUAI_NewTfliteModel`, `FUAI_TfliteModelSetInputData`,
     `FUAI_TfliteModelRun`, `FUAI_TfliteModelGetOutputData`,
     `FUAI_TfliteHexagonInitWithPath`, and links `libz.so`.
     Build path string: `/mnt/fu02_LVM/baodijun/fuai_8.2.0/…` → **FuAI 8.2.0**.
   * Model assets are 22 `.bundle` files (§3) holding TFLite models, GLSL
     shaders, PNG textures and JSON configs.
2. **Mi "algorithm" stack** — object removal / inpaint, scene detection,
   language ID:
   * `libinpaint.so` + `libinpaint_jni.so` + `libvis.so` (`Inpaint.inpaintBmpData`,
     `upsampleBmpData`, `releaseNNF`) — NNF-based on-device inpainting.
   * `libaiedit.so` (JNI `com.milab.aieditsdk.AiEdit.init/process/release`,
     `NEEDED libMNN.so`) — MNN-based "AI edit"; `libMNN.so` is **not in the
     APK** (loaded from `getDir("libs")`, i.e. extracted at runtime by
     MiCloud/plugin).
   * `libmotion_photo_mace.so` — Google **MACE** runtime (bazel paths
     `mace/proto/mace.pb.cc`, `mace/ops/arm/conv_winograd.cc`,
     "Creating MaceEngine, MACE version:") used by motion-photo features.
   * `com.miui.mediaeditor.detection.scene.SceneDetector` declares
     `libscene_detection.so`, `libcamera_scene.so`, `libFaceDetLmd.so`,
     `libyuv.so` — none are shipped in this APK.
   * ML Kit bundled language ID: `assets/tflite_langid.tflite.jpg` +
     `liblanguage_id_l2c_jni.so` (§5).
3. **Plugin boundary**: the editor UI/algo classes that the manifest and dex
   reference (`com.miui.mediaeditor.algo.beauty.*`, `.algo.param.*`,
   `.photo.remover_pro.InpaintAIEngine`, …) are **not defined** in either dex
   (`dex_find --classes-only` reports `defined:false`). They live in a
   downloaded plugin APK; only the FaceUnity core SDK, the JNI wrappers and the
   assets are in this APK.

### Feature map

| feature | entrypoint (Java/JNI) | models |
|---|---|---|
| face detection + 91/98/154-pt landmarks | `FUAI_NewFaceProcessorFromBundle` ← `com.faceunity.wrapper.Faceunity` | `ai_face_processor_detect_landmark.bundle` (13 entries) |
| face beauty: skin parsing, even-skin, del-spot, teeth, moles | `FUAI_NewFaceBeautyProcessorFromBundle`, `FUAI_FaceBeautyProcessorEvenSkin/ FaceDelSpot/ TeethSeg/ GetParsingMask` | `ai_face_beauty_processor.bundle` (14 entries) |
| face attribute (age/gender) | `FUAI_NewFaceAttributeProcessorFromBundle` | `ai_face_attribute_processor.bundle` |
| bindi (forehead mark) segmentation | `FUAI_NewPluginsFaceBindiSegProcessorFromBundle` | `ai_face_bindi_seg_processor.bundle` |
| face reshape / face warp | `FUAI_FaceProcessorGetFaceMeshV2*`, `FUAI_FaceProcessorGetAllLandmarksFromResult` | `graphics/image_beauty_facewarp.bundle` (mesh + brow PNGs) |
| "beauty type"/dense landmarks (eye, iris, mouth, hair) | `FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY` | `ai_beauty_type_processor.bundle` (20 entries) |
| body reshape / body slim | `FUAI_NewHumanProcessorFromBundle`, `FUAI_HumanProcessorGetPofJoint2dsFromResult` | `ai_human_processor_beautybody_mb.bundle` |
| makeup | `FaceUnity Nama MakeUpController` | `makeup/item_bundle/{jingdian,jingfu,liti,nanshi,oumei,wenrou}.bundle` (PNG layers) |
| scene recognition | `FUAI_PhotoEditingProcessorGetSceneTypeResult` | `ai_photo_editing_processor_scene_recog.bundle` |
| photo enhance / scenebeauty / autocrop / idphoto / portrait (GLSL) | `libCNamaSDK` renderer | `graphics/image_editor_*.bundle` |
| object removal (inpaint) | `Inpaint.inpaintBmpData` → `libinpaint.so` | model in `libinpaint.so`? no — see §6 |
| language identification | `ThickLanguageIdentifier.nativeIdentifyPossibleLanguages` | `assets/tflite_langid.tflite.jpg` |

---

## 2. Native entry points (evidence: `native/*.elf.txt`)

```
libfuai.so        674 exports, 0 JNI, NEEDED liblog,libz,libm,libdl,libc
                  FUAI_NewFaceProcessorFromBundle            0x3f86a8
                  FUAI_NewFaceBeautyProcessorFromBundle      0x439598
                  FUAI_NewFaceAttributeProcessorFromBundle   0x4467b0
                  FUAI_NewHumanProcessorFromBundle           0x41fdf4
                  FUAI_NewPhotoEditingProcessorFromBundle    0x4514cc
                  FUAI_NewTfliteModel / …Run / …SetInputData / …GetOutputData
                  FUAI_FileBufferSetFromZipBuffer            0x3f72f0
libCNamaSDK.so    17,583 exports; bundle path:
                  CNamaSDK::BundleHelper::GetBundleType     0x56f0dc
                  CNamaSDK::BundleHelper::ContainMagicCode  0x572f90
                  CNamaSDK::BundleHelper::DecryptObfuscatedPackage       0x56fd68
                  CNamaSDK::BundleHelper::DecryptObfuscatedPackageTFE    0x56f138
                  CNamaSDK::BundleHelper::VerifySignature   0x5707e4
                  CNamaSDK::CZipFile::ParseZip              0x573320
                  CNamaSDK::CZipFile::InflateDat(+_zstd)    0x5740cc/0x574068
                  CNamaSDK::CZipFile::ReadAll               0x5746dc/0x574c48
                  FuAIWrapper::DecryptObfuscatedPackageTFE  0x65ab04
libaiedit.so      3 JNI: com.milab.aieditsdk.AiEdit.{init,release,process}; NEEDED libMNN.so
libinpaint_jni.so 7 JNI: com.miui.gallery.editor.photo.app.remover.Inpaint.*
                  (initialize, inpaintBmp, inpaintBmpData, upsampleBmp(Data), releaseNNF, release)
libinpaint.so     60 exports, NEEDED libvis.so, libc++_shared.so
```

`libfuai.so` is stripped of internal names (0 symtab entries) but carries the
same bundle code — it prints `Zip read file error! file_index=` and
`config.json not exists!`, and exports `FUAI_FileBufferSetFromZipBuffer`,
i.e. it consumes the already-decoded ZIP buffer produced by the Nama
`BundleHelper` path (`FuAIWrapper::DecryptObfuscatedPackageTFE`).

---

## 3. The `.bundle` container — reversed and decoded

### 3.1 Recovery

All 22 bundles start with `f3 5b 06 12`. `BundleHelper::DecryptObfuscatedPackage`
(vaddr `0x56fd68`; Ghidra pseudocode in `native/cnamasdk_bundle.c`, capstone
disassembly in `native/bundle_decrypt.s`; source path baked into the binary:
`/root/CI/builds/eTmYD8xn/0/ruitaocai/CNamaSDK/src/common/BundleHelper.cpp`) does:

```
if (len < 0x20) return -1;
if (buf[0..3] == f3 5b 06 12) { n = 4; out_size = len - 4 - 40; }
else                        { n = 0; out_size = len - 40;          }
tmp.resize(0x420);                       // 1056 bytes
memcpy(tmp + 16, buf + 0x1c, 0x410);     // 1040 bytes, i.e. buf[28 .. 1068)
cipher_decrypt(tmp, 1056, buf + 4, _obfuscation_key);   // @0x56f2c4
out.insert(out.end(), tmp.begin() + 32, tmp.end());     // 1024 bytes
out.insert(out.end(), buf + 1068, buf + len);           // rest, verbatim
k = buf[4] ^ buf[5] ^ buf[6];
for (i < out.size()) out[i] ^= k;        // 0x570000..0x5700c0, vectorised EOR
```

So the layout is

```
0    4   magic f3 5b 06 12
4    8   header;  single-byte XOR key k = b[4]^b[5]^b[6]
12  16   IV for the 1024-byte prologue cipher
28  1040 first-block ciphertext (decrypts to 16 B prologue + 1024 B payload)
1068 ..  remainder of the ZIP, obfuscated only by the XOR
```

`zipWrapper`/`CZipFile::ParseZip` then parse the plaintext as a normal ZIP
(entries are DEFLATE; `InflateDat_zstd` shows zstd is also accepted).

**Proof**: `reimpl/unbundle.py` applies the XOR, parses the central directory
and checks every entry's CRC32 against the one stored in the ZIP. All 22
bundles parse, **726 entries total, 678 extracted with matching CRC32, 0
mismatches**; 48 entries lie inside the 1024-byte prologue and are skipped
(`evidence/bundle_decode.txt`).

Cross-check on the raw bytes: `PK\x03\x04` appears at file offset `0x0a6f`
(2671) in `ai_face_processor_detect_landmark.bundle` for the second entry,
which is exactly `1068 + 2627 - 1024` as predicted.

### 3.2 What the bundles contain

Compression/encryption is therefore **not opaque**: the payload is a ZIP of

* `*.tflite` — TFLite flatbuffers (all 33 extracted models have magic `TFL3`),
* `config.json` — per-processor pipeline description (§4),
* `*.glsl` — the beauty/enhance/idphoto/portrait GPU passes,
* `*.png` / `*.data` — makeup layers, skin/UV masks, LUTs,
* `*.json` — `auto_rules.json`, `face_mask_mapper_face_mask.json`, etc.

### 3.3 Residual: the 1024-byte prologue

The first 1024 payload bytes (i.e. the head of the first ZIP entry, and for
small bundles a few more) are processed by `BundleHelper`'s table cipher
(`0x56f2c4`, 681 instructions, `native/deobf_core.s`): a 16-byte ARX/table
network over 256-entry qword tables seeded by `_obfuscation_key` (a
`std::string` in `.bss` at `0xdc1d30`) and the 16-byte IV at file offset 12.
Consequence: 48 of the 726 entries are lost. Model-side that is the
`config.json` of the landmark, beauty-type and scene-recognition bundles plus
the five model files that sit at ZIP offset 0 (`acne_mole_acnenet…`,
`age_gender160_0422`, `bindi_seg_tiny_2024_0924`, `Bdycntr.0406…`,
`MOBILEONE_S2_SIZE384…`); the other 40 are the first GLSL/PNG entry of each
graphics and makeup bundle.

Key status: `_obfuscation_key`/`_final_key`/`_debug_key` are **never written**
in `libCNamaSDK.so` — a full `.text` scan of GOT-relative accesses yields only
7 reads (0x56f1d4, 0x56fecc, 0x570b98, 0x570cb0, 0x578d88, 0x578dec,
0x578e30) and the static initialiser at `0x44c3e4` merely default-constructs
the `std::string`s (empty). No literal key is present in the APK, so the
prologue is not decryptable statically *with this artifact alone*.
Note the model files of the landmark bundle (all 11 of them), the beauty
bundle (12 of 14), the beauty-type bundle (19 of 20) and the body bundle's
keypoint net start after the prologue and **are** fully recovered.

### 3.4 Signature

`VerifySignature` (`0x5707e4`) is a self-contained routine (no mbedTLS calls)
using `_final_key`/`_debug_key`; the ZIP end-of-central-directory *comment*
of every bundle carries 64 pseudo-random bytes (e.g.
`9305fad990ccb0d242aab318508b8b9a…`) — the per-bundle signature/`config.json`
digest. `ContainMagicCode` is the `f35b0612` check.

---

## 4. Model inventory and I/O contracts

33 TFLite models were carved out of the bundles (`ir/bundle_models.json`,
full table below). `models/bundles/` keeps the seven reimplemented ones plus
five `config.json` files.

| bundle entry | bytes | inputs | outputs | top ops |
|---|---|---|---|---|
| `ai_beauty_type_processor/dense_eb.tflite` | 436824 | `image[1,40,40,1]` | `Identity[1,13,2]; Identity_1[1,2]` | CONV_2D×15, ADD×4, FULLY_CONNECTED×4 |
| `ai_beauty_type_processor/dense_eye_iris.tflite` | 504020 | `image[1,64,64,1]; eye_s1[1,8,2]; ht[1,64,64,1]` | `Identity[1,8,2]; Identity_1[1,25,2]; Identity_2[1,2]` | CONV_2D×22, ADD×8, FC×5 |
| `ai_beauty_type_processor/dense_iris.tflite` | 200152 | `image[1,64,64,1]` | `Identity[25,2]; Identity_1[25,2]` | CONV_2D×23, DEPTHWISE×6 |
| `ai_beauty_type_processor/dense_mouth.tflite` | 453380 | `image[1,64,64,1]; upper_s1[1,18,2]; dense_s1[1,56,2]; ht_upper_s1; ht_dense_s1` | `Identity[56,2]; Identity_1[18,2]` | CONV_2D×22, ADD×9 |
| `ai_beauty_type_processor/face_landmark_lite_210818_360.tflite` | 484264 | `input[1,64,64,1]` | `add_7[1,154]` | CONV_2D×28, ADD×7 |
| `ai_beauty_type_processor/model_hair.tflite` | 2791684 | `inputs[1,96,96,1]` | `Identity[7,2]` | CONV_2D×27, PRELU×25 |
| `ai_beauty_type_processor/mouth_checker_14.tflite` | 32332 | `input[1,40,40,3]` | `softmax[1,2]` | CONV_2D×6, DEPTHWISE×2 |
| `ai_beauty_type_processor/n-0.5_416_0.05_4aspect6.tflite` | 1864936 | `input_1[1,416,416,3]` | `Identity[1,7098,1]; Identity_1[1,7098,4]; Identity_2[1,7098,10]` | YOLO-style: CONV_2D×66, LOGISTIC×41, TRANSPOSE×16 |
| `ai_beauty_type_processor/n-small_256_0.05_only_a5.tflite` | 101380 | `input_1[1,256,256,3]` | `Identity[1,3072,1]; Identity_1[1,3072,4]; Identity_2[1,3072,10]` | YOLO-style |
| `ai_beauty_type_processor/s1_112_98.tflite` | 2106152 | `inputs[1,112,112,1]` | `Identity[2]; Identity_1[98,2]` | CONV_2D×38, SOFTMAX |
| `ai_beauty_type_processor/s2.tflite` | 594960 | `inputs[1,112,112,1]; inputs_ht[1,112,112,1]; inputs_lms[1,77,2]` | `Identity[1,77,2]` | CONV_2D×27, ADD×9 |
| `ai_beauty_type_processor/s3.tflite` | 987788 | 15 inputs (5 crops ×[1,64,64,1] + 5 heatmaps + 5 landmark sets) | 5 × landmark sets (8/6/8/6/18 pts) | CONV_2D×120, ADD×40 |
| `ai_face_beauty_processor/Fprs.0003.Exp007e33_quan1_sm.tflite` | 1196952 | `image[1,512,384,3]` | `[1,512,384,15]` | CONV_2D×49, DEPTHWISE×24, QUANTIZE×13 |
| `ai_face_beauty_processor/FprsTeeth.0001.Exp002pe99_quan1_sm.tflite` | 598456 | `image[1,192,256,3]` | `[1,192,256,4]` | same family |
| `ai_face_beauty_processor/hair_1209a_acnenet_crop0.1pos0.1_3x24x24_quat.tflite` | 117360 | `input_1[1,24,24,3]` | `Identity[1,2]` | CONV_2D×16, ADD×12, SOFTMAX |
| `ai_face_beauty_processor/highlight_…_3x24x24_quat.tflite` | 116640 | `input_1[1,24,24,3]` | `Identity[1,2]` | idem |
| `ai_face_beauty_processor/lines_…_3x24x24_quat.tflite` | 117360 | `input_1[1,24,24,3]` | `Identity[1,2]` | idem |
| `ai_face_beauty_processor/mobile_spade_256_ch16_from16_40000_ft16_ftsave_yunfu.tflite` | 9968196 | `input_tensor[1,256,256,3]` | `generator_1/Tanh[1,256,256,3]` | SPADE GAN (even-skin): DEQUANTIZE×152, CONV_2D×62, RESIZE_BILINEAR×20 |
| `ai_face_beauty_processor/mobile_spade_256_ch16_from16_ftsevere_lr0.0001_117_16001.tflite` | 9968400 | `input_tensor[1,256,256,3]` | `generator_1/Tanh[1,256,256,3]` | SPADE GAN (flaw remover) |
| `ai_face_beauty_processor/mole_seg_24x24x3_1206_quat.tflite` | 144408 | `input_1[1,24,24,3]` | `Identity[1,24,24,1]` | CONV_2D×15, MUL×14, LOGISTIC |
| `ai_face_beauty_processor/skin_model_expand_supply_pta18000_quant_resizetrain_seg153.tflite` | 2433488 | `input_layer[1,320,320,3]` | `Identity[1,320,320,1]` | CONV_2D×60, DEPTHWISE×30 |
| `ai_face_processor_detect_landmark/Fpe.0007.Exp101.ERefine3e70_refine1.tflite` | 1473180 | `inputs_f14[1,364,4,48]; landmark[1,91,2]` | `landmark[1,91,2]` | STRIDED_SLICE×91, CONV_2D×91 |
| …`_refine2.tflite` | 1100456 | `landmark[1,91,2]; inputs_f28[1,364,4,32]` | `landmark[1,91,2]` | idem |
| …`_refine3.tflite` | 948808 | `inputs_f56[1,364,4,24]; landmark[1,91,2]` | 91-pt + dense 13/16/56-pt subsets | STRIDED_SLICE×112, REDUCE_MAX/MIN |
| `ai_face_processor_detect_landmark/Fpe.0007.Exp101.XScore.s76e150_base.tflite` | 735956 | `inputs[1,112,112,1]` | landmark[1,91,2], score[1,1,1,91], feats 56/28/14 | CONV_2D×22, PRELU×18, PAD×15 |
| `ai_face_processor_detect_landmark/face_landmark_mb360_64.tflite` | 346788 | `input[1,64,64,1]` | `add_7[1,154]` (77×2) | CONV_2D×24, DEPTHWISE×10 |
| `ai_face_processor_detect_landmark/facecheck40_dense_2000000_regress.tflite` | 155144 | `input[1,40,40,1]` | `detect[1,1]` | CONV_2D×4 |
| `ai_face_processor_detect_landmark/head.tflite` | 7804 | `input_1[1,126]` | `Identity[1,14]` | FULLY_CONNECTED×1 |
| `ai_face_processor_detect_landmark/n-0.5_416_0.05_4aspect6.tflite` | 1864936 | `input_1[1,416,416,3]` | detector heads | YOLO-style |
| `ai_face_processor_detect_landmark/n-small_256_0.05_only_a5.tflite` | 101380 | `input_1[1,256,256,3]` | detector heads | YOLO-style |
| `ai_face_processor_detect_landmark/s1.tflite` | 672836 | `inputs[1,64,64,1]` | `Identity[91,2]; Identity_1[2]` | CONV_2D×32, DEPTHWISE×9 |
| `ai_human_processor_beautybody_mb/FULLBODY_POFH2D_20210302_0001_e1604_q.tflite` | 1268024 | `Input_img[1,256,192,3]` **uint8** | `hms[1,64,48,31]; pofs[1,64,48,78]; bbfs[1,8,6,192]` | CONV_2D×64 (fully quantized) |
| `ai_human_processor_beautybody_mb/human_detector_retinanet_mobilenetv2_1.0_quant.tflite` | 2674640 | `input_1[1,320,320,3]` | `[1,19125,1]; [1,19125,4]` | RetinaNet, CONV_2D×73, DEPTHWISE×56 |

### Pipelines recovered from `config.json` (`models/bundles/config_*.json`)

* **Face parsing** (beauty bundle): `Fprs.0003…`, input 384×512×3, output
  512×384×15 with softmax; `cate_mask_order` = `[0..13,0,0,0,14,0]`
  (19 slots → 15 channels), `out_mask_scale=0.5`, face aligned by
  `mean_shape` (75 2-D points in a 512×512 frame).
* **Even skin / flaw remover**: two `mobile_spade_*` 256×256×3→256×256×3
  SPADE generators, `flg_align_frontal_eye=false`, driven by 10-value
  `mean_shape` (eye/mouth anchors).
* **Del-spot**: 4 classifiers (`hair`, `lines` (glasses/eyes), `highlight`,
  `acne_mole`) each 24×24×3→2 classes + `mole_seg` 24×24×3→24×24×1, with
  pre-processing constants (`expend_corner 1.2`, `scale_size 0.25`,
  `diff_thresh 0.48`, `unit_rad_scale 35`, `protected_mask_thresh 110`,
  skin-protection mask `face_mask_del_spot_protect_mask_0815A.data`).
* **Teeth seg**: `FprsTeeth…`, 256×192×3 → 4 channels, `teeth_mask_order=3`,
  region from 18 of the 75 landmarks, `netin_image_expand_udlr_ratio 0.25`.
* **Skin seg**: 320×320×3 → 320×320×1, `mask_threshold 0.6`,
  `keep_block_radio 0.01`.
* **Body (imgslim)**: RetinaNet detector 320×320 (threshold 0.7, Nt 0.5) →
  `FULLBODY_POFH2D` keypoint net 288×384×3 → heatmaps 72×96×25 + `bdycntr`
  67 joints + POF 2-D offsets 48×64×78 + BBFs 6×8×192, `estimate_mode
  MaxAveWeight`, `estimate_size 12`, `ave_threshold 0.2`, bbox expand 1.15.
* **Bindi seg**: 96×128×3 → 1 channel, region = 28 of 75 landmarks,
  `expand_udlr_ratio 0.01`.
* **Age/gender**: 128×160×3, 150-value mean shape.

### Post-processing recovered from `libfuai.so` exports

`FUAI_FaceBeautyProcessorGetParsingMask/GetSkinSegResult/GetEvenSkinOutResult/
GetDelSpotResult/GetMoleMaskResult/GetBlurMask/GetBrightEyeMask`,
`FUAI_FaceProcessorGetAllLandmarksFromResult/GetFaceMeshV2VerticesFromResult`,
`FUAI_HumanProcessorGetPofJoint2dsFromResult`, `FUAI_MaskTransform`,
`FUAI_FaceBeautyResize`, `FUAI_FaceBeautyRGBA2NV21` — i.e. mask resampling to
the image, mesh generation from landmarks, and blending are in native code,
not in Java.

---

## 5. Language identification (`assets/tflite_langid.tflite.jpg`)

315,520 B TFLite (`TFL3` at offset 4), description `MLIR Converted.`,
`min_runtime_version`, metadata `L2C_NL_CLASSIFIER_METADATA` whose last 4
bytes are the float **confidence threshold 0.3**.

Graph (`ir/tflite_langid.json`):

```
Placeholder STRING[1]
 → NGramHash (CUSTOM)            → [1,4,N] int32
 → UNPACK(axis=1)                → 4 × [1,N]
 → KmeansEmbeddingLookup ×4      → 4 × [1,24]
 → CONCAT(axis=1)                → [1,96]
 → FC 96→160 (+ReLU) → FC 160→200 (+ReLU) → FC 200→111
 → SOFTMAX                       → [1,111]
```

`IndexToClassID` (STRING[111]) = `unknown, af, am, ar, ar-Latn, az, … zh,
zh-Latn, zu` (BCP-47-ish, legacy `iw` for Hebrew).

Custom-op semantics recovered from `liblanguage_id_l2c_jni.so`:

* options of `NGramHash` are a **flexbuffer map**
  `{"max_splits":128, "ngram_lengths":[1,2,3,4], "seed":10911,
    "vocab_sizes":[5500,5500,13000,13000]}`
  (decoded with `flatbuffers.flexbuffers`).
* registration struct at `0xfce08` → init `0x3f494`, prepare `0x3fb7c`,
  invoke `0x3fc0c`; the hash is **MurmurHash64A** at `0x407c8`
  (`m=0xc6a4a7935bd1e995`, `r=47`) and the emitted value is
  `murmur64a(utf8(ngram), seed) % vocab_size + 1` (`udiv`/`msub` at
  `0x40220`, `+1` at `0x40230`) — `0` is the padding sentinel.
* the input string is split into ≤128 word tokens (`0x40294`, UTF-8 decode
  `0x48b24`, character class `0x48e48` = UnicodeSet of letters + combining
  marks; digits are in neither set, `native/ngramhash_invoke.s`).
  N-grams never cross a token boundary: `"Hello world"` yields
  `featurize().shape == (4, 10)` with 10 unigram hashes (5+5, not 11).
* `KmeansEmbeddingLookup` (prepare `0x3ee44`, invoke `0x3f2d0`): the hash
  indexes `c_matrix` (UINT8 `[V+1,6]`), whose 6 bytes each index a row of
  `b_matrix` (FLOAT32 `[256,4]`), giving 24 floats; the per-order embedding is
  the **mean over all non-zero hashes** (`scvtf`/`fdiv` at `0x3f200`–`0x3f24c`).

### Reimplementation and verification (`reimpl/`, `reimpl/verify.txt`)

* `reimpl/langid_pipeline.py` — tokenizer + MurmurHash64A + `% vocab + 1`
* `reimpl/model.py` — JAX: mean-pooled codebook embedding + 3 hybrid
  FC layers (int8 weights × per-tensor scale + float bias) + softmax
* `reimpl/reference_numpy.py` — independent NumPy implementation
* `reimpl/verify.py` → `verify.txt`: **PASS**
  * hashes in range 1..vocab, 0 only as padding
  * JAX vs NumPy: max |Δ| = 1.31e-06 (200 random hash tensors),
    5.36e-07 (28 real sentences)
  * end-to-end top-1 language accuracy **27/28** (only Hindi→Marathi misses;
    Hebrew is correctly `iw`, Indonesian `id`)

Shapes: `text → hashes[4,N] int32 → embeddings[96] float32 → logits[111] →
softmax[111]`, weights from the flatbuffer (int8 FC with scales
0.0278663/0.0114967/0.0141550, `b_matrix` 256×4 ×4, `c_matrix`
5501×6 / 5501×6 / 13001×6 / 13001×6).

---

## 6. Other AI components

* **Inpaint (`libinpaint.so`, 75 KB + `libvis.so`)** — `Inpaint.initialize()`
  then `inpaintBmpData(bitmap, mask, …, RemoverNNFData)` /
  `upsampleBmpData`. `libinpaint.so` links `libvis.so` (Mi's "vis" inference
  engine) and contains only NNF glue (`setfree_nnfindex`, `dstnnf`,
  `releaseNNF`) — **no TFLite/MACE symbols**; the network comes from a
  `vis::NNF` file, i.e. weights are external, not in this APK. `InpaintProJni.nativeInit(j,j2,path,path2)` takes
  two model directory paths — again external files.
* **`libaiedit.so` (34 KB)** — `AiEdit.init(String path)` / `AiEdit.process(handle,
  int[] shape, float[] data)`; runs **MNN** (`libMNN.so`, not shipped).
  Model file comes from the path argument.
* **`libmotion_photo_mace.so` (3.5 MB)** — Xiaomi MACE mobile inference engine
  for motion-photo features; no model assets in the APK.
* **`com.miui.gallery.editor.photo.app.beauty.animalbeauty.AnimalBeautyJni`** —
  `getLibDir`, `runSegmentModels(Bitmap, Bitmap)`, `adjustAllBeauty(...)`:
  pet beauty; backing library not in this APK.
* **`SceneDetector`** — `libscene_detection.so` / `libFaceDetLmd.so` /
  `libcamera_scene.so` referenced, not shipped.
* **`libCNamaSDK.so` graphics bundles** (`image_editor_enhance`,
  `image_editor_scenebeauty`, `image_editor_autocrop`, `image_editor_idphoto`,
  `image_editor_portrait`, `image_beauty_basic/facebeauty/facewarp/bodyslim`)
  are GLSL + PNG/LUT bundles (no TFLite), i.e. the GPU-side of beauty/enhance.

---

## 7. JAX reimplementation of bundle models

`tools/tflite2jax.py` → `reimpl/bundles/<name>/{model.py,params.npz,ir.json}`,
verified against the **real LiteRT interpreter** (`ai_edge_litert` 2.3.0)
with `tools/verify_tflite_jax.py` (tol 1e-4):

| model | I/O | max abs error | status |
|---|---|---|---|
| `face_landmark_mb360_64` | [1,64,64,1] → [1,154] | 1.53e-05 | PASS |
| `s1` | [1,64,64,1] → [91,2] + [2] | 1.91e-05 | PASS |
| `head` | [1,126] → [1,14] | 2.09e-07 | PASS |
| `facecheck40_dense_2000000_regress` | [1,40,40,1] → [1,1] | 0 | PASS |
| `hair_1209a_acnenet…` | [1,24,24,3] → [1,2] | 0 | PASS |
| `mole_seg_24x24x3_1206_quat` | [1,24,24,3] → [1,24,24,1] | 3.76e-06 | PASS |
| `Fprs.0003.Exp007e33_quan1_sm` | [1,512,384,3] → [1,512,384,15] | 1.76e-01 | FAIL |

The parsing model is heavily quantized (13 QUANTIZE + 1 DEQUANTIZE, 49 convs);
the converter's float emulation drifts on the 15-class softmax. All weights are
real (extracted), so this is a converter limitation, not missing data.
`reimpl/unbundle.py` and `reimpl/model.py`+`reference_numpy.py` are the
task-specific code; the rest is generated.

---

## 8. Unknowns

* **U1** `BundleHelper::_obfuscation_key` value: never written inside
  `libCNamaSDK.so` (7 GOT-relative reads only; the static initialiser at
  `0x44c3e4` just default-constructs the `std::string`s). Without it the
  1024-byte prologue stays encrypted, which costs 48 of 726 entries — 3
  `config.json` (landmark / beauty-type / scene) and 5 offset-0 models
  (`acne_mole…`, `age_gender160_0422`, `bindi_seg_tiny…`, `Bdycntr…`,
  `MOBILEONE_S2…`). `FuAIWrapper::DecryptObfuscatedPackageTF` (`0x65ab04`)
  only passes the key through. The cipher itself is at `0x56f2c4` (table/ARX network,
  16-byte blocks, 256-entry qword tables); a faithful reimplementation is
  blocked on the key only.
* **U2** `VerifySignature`'s exact digest scheme and the 64-byte EOCD comment
  content (it does not call mbedTLS; uses `_final_key`/`_debug_key`).
* **U3** The editor plugin (`com.miui.mediaeditor.algo.*`) is not in the APK,
  so the *application-level* orchestration of the FaceUnity controllers (which
  bundle path is applied for which UI slider, `ImageBeautyData` construction)
  is only known through the FaceUnity SDK classes that are present.
* **U4** Inpaint / aiedit / motion-photo / scene-detection weights are external
  (MNN file, NNF file, plugin `.so`s), so their I/O contracts cannot be given.
* **U5** TFLite GPU/Hexagon delegate selection (`FUAI_SetAIInferenceGPUId`,
  `FUAI_TfliteHexagonInitWithPath`) is device-gated; the actual delegate used
  on this phone is unverified.
* **U6** `Fprs` JAX conversion mismatch (1.76e-01) — converter/quantization
  emulation, root cause not isolated.

---

## 9. Reproduce

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
APK="$APK_ROOT/product/data-app/MIMediaEditorGlobal/MIMediaEditorGlobal.apk"

$PY ../../tools/apk_inventory.py "$APK" --out inventory --extract-models --extract-libs
unzip -o "$APK" 'assets/model/*' 'assets/graphics/*' 'assets/makeup/*' -d work/assets
unzip -o "$APK" 'lib/arm64-v8a/*' 'assets/tflite_langid.tflite.jpg' -d work/x

$PY reimpl/unbundle.py list    work/assets/assets/model/ai_face_processor_detect_landmark.bundle
$PY reimpl/unbundle.py extract work/assets/assets/model/ai_face_processor_detect_landmark.bundle work/bundles/x
$PY ../../tools/tflite_ir.py <model.tflite> --out ir/<model>.json
$PY ../../tools/tflite2jax.py <model.tflite> --out reimpl/bundles/<name>
$PY reimpl/verify.py            # langid, writes reimpl/verify.txt
```

Hashes of the extracted models: `evidence/model_hashes.txt`.

---

## 10. Editor data path, per-model I/O, postprocess, lifecycle (patch 2026-10-24)

Full contract: **[`PIPELINE.md`](PIPELINE.md)** (9 sections, every row tagged
with confidence + evidence). `findings.json` gained a `data_pipelines` array
with four entries: `editor_photo_face_beauty`, `editor_video_beauty`,
`editor_photo_makeup`, `editor_photo_inpaint`.

> **Correction to §8 / U3.** The claim that `com.miui.mediaeditor.algo.*` is
> absent from the APK was wrong: 2,664 `com.miui.mediaeditor` classes are
> defined (`dex_find --classes-only`), including
> `algo.beauty.face.FaceBeautyAlgorithm`,
> `algo.beauty.common.BeautyBaseManager`,
> `algo.beauty.common.core.render.GlTexturePhotoRenderer`,
> `algo.beauty.common.core.widgets.BeautyGlView` and the whole beauty UI
> (`beauty.module.facebeauty.*`). What *is* obfuscated is the FaceUnity SDK
> layer: `j2.c` = render input data, `j2.d` = render result, `k2.a` = FuAI
> controller, `f2.b` = bundle manager, `m2.a` = ImageBeauty model,
> `gltexturerender.d` = photo renderer. U3 above is superseded by this
> section; U7/U8 are the two genuinely missing pieces (makeup-style selector,
> video `setupBeauty` caller).

### 10.1 Traced photo edit (UI → TFLite → saved image)

Whitening slider ⇒ face-parsing model `Fprs.0003.Exp007e33_quan1_sm.tflite`.
Twelve hops, each with class/method evidence (detailed table in `PIPELINE.md` §0):

```
FaceBeautyRenderFragment → FaceBeautyViewModel (FaceBeautyMenuEntity)
  → FaceBeautyAlgorithm.a/e(BaseParam) → algo.beauty.face.processer.a
  → m2.a.i0("image_white", …)          [per-face JSON {"faceid":N,"param":"image_white"}]
  → BaseSingleController.e → fuItemSetParam(handle, json, value)
BeautyBaseManager.b()  → fuSetMaxFaces(5), fuSet...DetectMode(IMAGE),
                         fuImageBeautySetAttributePath/FaceBeautyPath
algo.beauty.face.a.c()  → k2.a.e("model/ai_face_beauty_processor.bundle",
                                 FUAITYPE_IMAGE_BEAUTY)
  → fuLoadAIModelFromPackage(bundleZipBytes, 2048)
  → f2.b.a("graphics/image_beauty_facebeauty.bundle") → fuCreateItemFromPackage
BeautyGlView.d(bitmap, FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY) → GlTexturePhotoRenderer
  → gltexturerender.d (FU_FORMAT_BITMAP) → fuImageBeautyConvertRGBA2NV21WithBitmap*
  → FURenderBridge.h → fuImageBeautyPreProcess(...) → fuImageBeautyCreateTextureCoverPreview
  → onDrawFrame → FUCoreBizApiProxy.a(0)=IFUCoreBeautyImpl.d → FURenderBridge.c → fuImageBeautyPreview
  [native] ImageBeautyController → config.json face_parsing → Fprs.0003… → 15-ch mask
  → skin/even-skin GLSL passes → texture
BeautyGlView.k → GlTexturePhotoRenderer.e → gltexturerender.c.run
  → FURenderBridge.d → fuImageBeautyGetResultBitmap(w,h,items) → CacheData(IMAGE)
  → BeautyExportDataResult.d.f7421c = bitmap.copy(ARGB_8888, true) → editor save
```

`*` NV21 only when `FUInputBufferEnum.FU_FORMAT_NV21_BUFFER` is selected; the
default photo path keeps the `Bitmap` (`FU_FORMAT_BITMAP = 999`, SDK-only id).

### 10.2 Per-model I/O in the app

The app never names a `.tflite` file; `k2.a.e(path, FUAITYPE_*)` hands the
whole decrypted `.bundle` to `fuLoadAIModelFromPackage`. The mapping
bundle → nets is the bundle's own `config.json`:

| bundle (`assets/model/…`) | FuAI type | config section → model | net I/O |
|---|---|---|---|
| `ai_face_beauty_processor.bundle` | `FUAITYPE_IMAGE_BEAUTY` = 2048 | `face_parsing` → `Fprs.0003.Exp007e33_quan1_sm` | `[1,512,384,3]` f32 → `[1,512,384,15]` softmax |
| same | same | `face_skin_seg` → `skin_model_expand_supply…seg153` | `[1,320,320,3]` → `[1,320,320,1]`, thr 0.6 |
| same | same | `face_even_skin` / `face_flaw_remover` → two `mobile_spade_256…` SPADE GANs | `[1,256,256,3]` → `[1,256,256,3]` Tanh, GPU |
| same | same | `face_del_spot` → `hair/lines/highlight/acne_mole` 24²×3→2 + `mole_seg…` 24²→24²×1 | patch scores + mask |
| same | same | `face_teethseg` → `FprsTeeth.0001…` | `[1,192,256,3]` → `[1,192,256,4]`, `teeth_mask_order 3` |
| `ai_face_bindi_seg_processor.bundle` | `FUAITYPE_FACEPROCESSOR_BINDI_SEG_PROCESSOR` = 67108864 | `face_bindi_seg` → `bindi_seg_tiny_2024_0924` | `[1,128,96,3]` → `[1,128,96,1]` |
| `ai_beauty_type_processor.bundle` | `FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY` = 65536 | (config inside encrypted prologue) → `s1_112_98`, `s2`, `s3`, `dense_eb/eye_iris/iris/mouth`, `model_hair`, `face_landmark_lite_210818_360`, `mouth_checker_14`, two YOLO detectors | crops → sub-landmarks/heatmaps |
| `ai_face_processor_detect_landmark.bundle` | `FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS` = 8388608 | (prologue) → `n-small_256`, `n-0.5_416`, `s1`, `Fpe…XScore_base`, `Fpe…refine1/2/3`, `face_landmark_mb360_64`, `facecheck40`, `head` | 64²/112²/416² crops → 91/98/154 pts |
| `ai_human_processor_beautybody_mb.bundle` | `FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY` = 131072 | `human_detector` (320², thr 0.7, Nt 0.5) → `Bdycntr…` (288×384, 67 joints, heatmap 96×72×25) → `FULLBODY_POFH2D…` (256×192 → 64×48×31 hms / 64×48×78 pofs / 8×6×192 bbfs) | RGB crops |
| `ai_face_attribute_processor.bundle` | `FUAITYPE_FACE_ATTRIBUTE_PROCESSOR` = 4096 | `ai_face_attribute` → `age_gender160_0422` | `128×160×3`, 150-pt mean shape |
| `ai_photo_editing_processor_scene_recog.bundle` | `FUAITYPE_PHOTOEDITOR_ROCESSOR` = 4194304 | prologue → `MOBILEONE_S2_SIZE384…FP16` | 384² input |

Tensor binding: Java binds **only the image buffer**
(`fuImageBeautyPreProcess`, `fuImageBeautyPreview`, `fuImageBeautyGetResult*`,
`fuRenderImg`); per-tensor binding/crop/normalisation is inside `libfuai.so`
(`fuai/model/tflite/tflite_model.cc`, `tflite_model_preprocess.cc`), driven by
`config.json` (`image_*`, `netin_*`, `netout_*`, `mean_shape`,
`out_mask_scale`, `cate_mask_order`). Per model the session parameters
(`threads`, `tflite_use_xnnpack`, `tflite_use_gpu`, `tflite_model_token`,
`tflite_use_dsp`) come from the same block — e.g. the SPADE GANs run with
`tflite_use_gpu:true`, `max_queue_count:8`, tokens
`d2e0610b3a44e3236b5fa90532ce57a2` / `dda9a0e054fe488ee789ff610b12bcaa`.

Bundle selection call sites (confirmed by decompile):

| bundle | Java caller |
|---|---|
| `graphics/image_beauty_facebeauty.bundle` | `algo.beauty.face.a.c()` |
| `graphics/image_beauty_facewarp.bundle` | `pg.a` (`sources/classes2/pg.a.java:18`) |
| `graphics/image_beauty_bodyslim.bundle` | `kg.c` (`sources/classes2/kg.c.java:18`) |
| `model/ai_face_beauty_processor.bundle` + `ai_beauty_type_processor.bundle` | `algo.beauty.face.a.c()` and `pg.a` |
| `model/ai_face_bindi_seg_processor.bundle` | `algo.beauty.face.a.c()` |
| `model/ai_human_processor_beautybody_mb.bundle` | `kg.c`, `algo.detect.FaceUnityDetectAlgorithm.f` (case 1) |
| `model/ai_face_processor_detect_landmark.bundle` | `algo.detect.FaceUnityDetectAlgorithm.f` (case 0/3), release via `k2.a.f(type)` → `fuReleaseAIModel` |
| `ai_wrinkle_remover_processor.bundle` | `algo.beauty.face.a.e()` (dynamic-feature dir) |
| `makeup/item_bundle/*.bundle` | no caller found (U7) |

### 10.3 Postprocess and render

* **Mask decoding** — `cate_mask_order` (19 slots → 15 channels; teeth
  `[…,-1,-1,3]`), `out_mask_scale 0.5`, `mask_threshold 0.6`,
  `keep_block_radio 0.01`, del-spot `diff_thresh 0.48`,
  `protected_mask_thresh 110`, `unit_rad_scale 35`, `expend_corner 1.2`,
  `scale_size 0.25`.
* **Mask → image coordinates** — 80-vertex UV mesh
  (`models/bundles/face_mask_mapper_face_mask.json`, `face_ebo` 136 tris) plus
  a 540×960 uint8 face prior with a 12-byte `{w,h,c}` header
  (`face_mask_mapper_skin_mask.data`, 518,412 B, header `1c02 0000 c003 0000
  0100 0000`). Native warp accessors:
  `FUAI_SkinSegmenterGetResultMaskWarp` @ `0x64a084`,
  `FUAI_FaceProcessorGetResultRectWarp` @ `0x64a7e8`,
  `FUAI_FaceProcessorGetResultAllLandmarksWarp` @ `0x64a9c4`.
* **Reshape landmark indexing** — `image_beauty_facewarp.bundle`'s
  `face_warp.json`: `face_uv` 566 floats = 283 vertices, `face_ebo` 1584
  indices = 528 triangles, `mouth_uv/ebo` 106 v/44 t, `eye_uv/ebo` 113 v/86 t,
  `face_uv_zz`/`face_uv_bz` 166 v each (lip variants),
  `eye_offset`/`chin_offset` displacement bases, `haritop_ebo*` hair-top
  region. Per-feature displacement masks are generated natively by
  `ImageBeautyController::Generate*Mask` (`FaceThinning`, `FaceSmall`,
  `FaceNarrow`, `FaceV`, `CheekBone`, `Chin`, `EyeWarp/EyeSpace/EyeRot`,
  `Brow*`, `NoseLong/NoseThinning/NoseTip`, `Forehead`, `LowerJaw`,
  `MouthWarp`, `Temple`, `Smile`, `Plumplip`, `HairTop`).
* **Blending** — masks are eroded/dilated and averaged on the GPU
  (`image_erode_dilate_fs`, `image_merge_mask_fs`,
  `image_merge_mask_for_ar_fs`, `image_global_boxfilter_with_mask_*`,
  `GetAverMask`/`GetAverAndBlurMaskBySoftSkinMask`/`GetAlphaPassOfAverMask`);
  the warp shader (`models/bundles/image_chin_warp_fs.glsl`) decodes an RGBA8
  displacement map as `(r-127/255, (g-0.5)*2)` / `(b-127/255, (a-0.5)*2)`,
  rotates it by `cAngle/sAngle` and samples
  `texc + newCoords * intensity * scale * block_scale`.
* **Makeup** — item package per style (`makeup_config.json` groups for
  eyebrow/eye/eyeliner/eyelash/lip/blusher/highlight/shadow +
  `blend_type_tex_*` + `makeup_intensity_*`); `script.js` runs in the engine's
  duktape sandbox, loads the PNG layers with `FaceUnity.LoadTexture(file,0)`
  and exposes `makeup_info`; `MakeUpController`
  (`makeupPreprocessLipMask`, `GetLipMaskTexture`, `makeupPointsCalc`)
  composites. Recovered configs/scripts: `models/bundles/makeup_jingdian_*`.
* **Inpaint** — `Inpaint.inpaintBmpData(src, mask, …6 ints, RemoverNNFData)`
  returns the low-res nearest-neighbour field (`RemoverNNFData{width,height,
  index,count,nnf}` carried over a `MemoryFile`); `upsampleBmpData` warps and
  blends it back at full resolution.
* **Renderers** — `BaseGLTextureRenderer` (GL thread, `onDrawFrame` drains
  pre/post action lists), `gltexturerender.d` (photo, `n2.b` shader +
  `n2.c`/`n2.b` copy), `MagnifierBeautyGlView` (loupe), `BeautyGlView`
  (surface + save callback).
* **Save/export** — `gltexturerender.c.run()` commits manual grids
  (`image_beauty_manual_type`, `image_manual_acen_data/blur/wrikle`,
  `image_manual_order`, `image_face_data`), snapshots `ImageBeautyData`, then
  reads the result bitmap; `BeautyExportDataResult` carries
  `{ImageBeautyData, Bitmap}`; the editor writes the JPEG through
  `com.miui.gallery.editor.photo.app.saver.*` (inferred).

### 10.4 Lifecycle and threading

* init once per beauty session: `BeautyBaseManager.b()` (guarded by
  `AtomicBoolean`) → `fuSetMaxFaces(5)`,
  `fuSetHumanProcessorSetMaxHumans(1)`, detect mode `IMAGE`,
  `fuImageBeautySet{Attribute,FaceBeauty}Path`, cache dir `sdkImageCache`.
* model install/switch: `FURenderKit.h(ImageBeauty)` applies the controller
  bundle and flushes the queued per-face params, then calls
  `fuProcessorChangeModel(1)` (`FURenderKit.java:40-70`).
* per-edit: preprocess → preview render → result read; **no per-frame loop**
  (RENDERMODE_WHEN_DIRTY).
* threads: `BaseSingleController.d()` owns a
  `HandlerThread("KIT_<Controller>")` with a post/release queue;
  `FURenderBridge` keeps two `synchronizedList`s (before/after render)
  drained inside `onDrawFrame`; save runs on a single-thread
  `ExecutorService`; all `fu*` calls stay on the GL thread.
* teardown: `BeautyBaseManager.d()` → `k2.a.d.a().g()` (release every loaded
  AI model) → editor impl `a()` (`fuReleaseGLResources`, `fuDone`,
  `fuOnDeviceLost`) → `fuDestroyItem` per bundle.
* failure: `fuIsAIModelLoaded` short-circuit; `10001 file not found` /
  `10002 loadAIModelFromPackage failed` callbacks; missing dynamic-feature
  bundle → download prompt (`photo_editor_common_download_failed_msg`, library
  ids in `com.miui.gallery.editor_common.library.a`, e.g. `sFaceBeautyBundles
  = {5002005}`); SDK status enum `FU_IMAGE_BEAUTY_STATUS_*`.
* video: `XmsContext.setupBeauty(soPath, bundlePath, cachePath, flag)` →
  `Context::setupBeauty` @ `0xed444` → `BeautyWapper` dlopen → MLT filter
  `movit.filter.face.beauty`; `BeautyWapper::render(...)` per frame;
  teardown `shutDownBeauty`. No in-APK caller (see U8).

### 10.5 New artefacts

| path | content |
|---|---|
| `PIPELINE.md` | full contract, 9 sections |
| `evidence/editor_datapath.txt` | bundle inventory, decoded makeup/facewarp entries |
| `evidence/dex_datapath.txt` | dex class counts (80 `com.faceunity`, 2664 `com.miui.mediaeditor`, 91 `com.xiaomi.milab.videosdk`, 47 `com.miui.gallery.editor_common.library`) + the "no caller" checks for `setupBeauty`/makeup |
| `evidence/dex_bundle_paths.txt` | the 28 bundle path strings present in the dex |
| `models/bundles/face_mask_mapper_skin_mask.data`, `…del_spot_protect_mask_0815A.data` | 540×960 uint8 masks with 12-byte `{w,h,c}` headers |
| `evidence/native_fuai_params.txt` | `p_*` param keys, landmark/mesh keys, `config.json` schema, TFLite backends, `FUAI_*` symbols |
| `evidence/video_beauty_native.txt` | `BeautyWapper`/`setupBeauty` disassembly, MLT properties |
| `models/bundles/makeup_jingdian_config.json`, `…_script.js` | makeup layer contract |
| `models/bundles/image_beauty_facewarp_face_warp.json` | 283-vertex warp mesh + indices |
| `models/bundles/face_mask_mapper_face_mask.json` | 80-vertex mask mesh |
| `models/bundles/image_chin_warp_fs.glsl` | warp shader |
| `sources/classes2/*` (47 new classes), `sources/logs2/*` | this pass's jadx single-class output |
