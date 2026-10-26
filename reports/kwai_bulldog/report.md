# kwai_bulldog — AI/vision subsystem

**Package** `com.kwai.bulldog` · **version** 6.0.20.524206 (524206) ·
**label** `Snack Video` · minSdk 21 / targetSdk 31 · arm64-v8a only ·
16 dex · 80 native libs · 0 bundled model assets
**APK** `$APK_ROOT/cust/app/customized/partner-com.kwai.bulldog_112/partner-com.kwai.bulldog_112.apk`
80,257,412 bytes · sha256 `d18888e3a450569b50769a9a550358e92e4bb158608f0ba04edaafa21c1f9c7d`
(inventory: `inventory/inventory.json`)

This build is the partner/Snack-Video flavour of the same Kwai code base as
`com.kwai.video` 6.0.20.524206 (`partner-com.kwai.video_33`). All AI native
libraries are **byte-identical** to that sibling; only the resource/DEX
payloads differ (see §1). The shared Kwai SDK notes from the sibling were not
present while this variant was analysed, so every claim below was re-derived
from this APK; the hash check is the only reuse.

TL;DR: this APK ships an NN **runtime** (`libykit.so`, `ycnnnet` v3/v4 +
TFLite/NNAPI/SNPE shims) but **no model weights**. Face/beauty/segmentation
models (`KSModel*.model`, 102 names) and the voice-detection TFLite model
(`voice_detect_mode.tflite`) are downloaded per resource key and validated
with `check.json` MD5 lists. Reimplementation is therefore
`ARCHITECTURE_ONLY` with the I/O contracts recovered from native/JNI code.

> Frame → inference → render contract, JNI tables and fallbacks: **`PIPELINE.md`**.
> Machine-readable form: `findings.json → data_pipelines[]`. Trace summary: §9.

---

## 1. Variant diff vs `kwai_video`

Method: zip member CRC+size comparison of both APKs, then sha256 of every
`lib/*` member (`evidence/lib_hashes_vs_video.json`, `evidence/zip_diff_raw.json`).

| group | identical | modified | only bulldog | only video |
|---|---:|---:|---:|---:|
| native libs (`lib/`) | **79** | 1 | 0 | 0 |
| dex (16 vs 16) | 0 | 16 | 0 | 0 |
| assets | 260 | 3 | 1 | 0 |
| res | 4193 | 3200 | 26 | 12 |
| META-INF | 233 | 3 | 0 | 0 |
| root/other | 128 | 2 | 1 | 0 |
| **total** | | | **28** | **12** |

* The single differing library is `lib/arm64-v8a/libcore.so`
  (30,800 B; sha256 `6dffc5fe…` vs `80d33ae2…`); it is a utility JNI lib, not
  in the AI set.
* **All 14 AI/vision libs are identical**, verified by sha256:
  `libFaceMagic.so 6ef763ec…`, `libfacedetectcontext.so 6238b612…`,
  `libfaceless-plugin.so 727d4469…`, `libksvisionengineplugin.so 39644f5e…`,
  `libskwai.so 468b928d…`, `libtensorflow-lite.so 6ef8898d…`,
  `libvisionengine.so d18be898…`, `libopencv_world.so 67c11c7e…`,
  `libkwaivpp.so 3a1b0ad3…`, plus libykit/libCGE/libgorgeous/libklsf/libwesteros.
  (`native/libs/`, `evidence/lib_sha256.txt`.)
* Only-in-bulldog resources are local/nearby UI plus PNG app icons
  (`res/drawable*/kwai_icon.png`, `app_launch_logo.png`, `res/layout/*local*`,
  `nearby_*`, `assets/feed_follow_b.json`). Only-in-video resources are the
  same icons as `.webp` plus `res/drawable-pt-xxhdpi-v4/app_launch_logo.webp`.
* AI-relevant assets are hash-identical across both variants:
  `assets/BodySlimming/Filter{28,40}p/*.params`, `assets/beauty_list`,
  `assets/deform_config`, `assets/demo_algorithm_info.txt`,
  `assets/demo_vision_feature_response_v2.txt`, `assets/fm_builtin_face_mask.jpg`,
  `assets/eyemask.*`, `assets/default_enhance_lut.png`.
* Diff of DEX sizes is small (e.g. classes.dex 8,709,044 vs 8,657,964) and
  reflects the local/nearby feature removal, not AI code: the AI class set
  below is present in both.

Confidence: **confirmed**.

---

## 2. AI inventory

| artifact | bytes | role |
|---|---:|---|
| `libFaceMagic.so` | 7,722,568 | effect/beauty engine (FaceMagic + CGE/skwai 3D), 1802 JNI exports |
| `libskwai.so` | 6,515,760 | 3D engine (Lua-scripted Skwai runtime, no JNI) |
| `libopencv_world.so` | 7,863,816 | OpenCV for FaceMagic/visionengine |
| `libtensorflow-lite.so` | 3,446,232 | TFLite runtime (NDK r20b, 47 JNI exports) |
| `libvisionengine.so` | 2,108,736 | KVE video analysis (Halide DAGs), 95 JNI exports |
| `libfaceless-plugin.so` | 1,792,128 | `FaceMagicController`/`FacelessPlugin` JNI bridge, 44 exports |
| `libfacedetectcontext.so` | 191,728 | camera face detection/landmarks, 26 JNI exports |
| `libksvisionengineplugin.so` | 38,984 | editor KVE task JNI (enhance/analyze/stabilize), 21 exports |
| `libkwaivpp.so` | 5,760 | video post-processing stub, 0 JNI |
| `libykit.so` | 10,292,232 | **NN runtime** `ycnnnet` v3/v4 + `KSJNILib` JNI (30 exports) |
| `libykit_core_plugin.so` | 592,000 | `YcnnPlugin`/`YcnnPluginUtils` JNI (salient, face attributes, general recognition), needs libykit.so |
| `libykit_plugin.so` | 260,216 | `YKitPlugin` JNI (generic ykit model loader), needs libykit.so |
| `libykit_module_plugin.so` | 592,008 | `AIEditPlugin` / `MmuPlugin` / `YarPlugin` JNI, needs libykit_module.so |

`libykit.so` is not in the scanner's AI_LIB_RE list but is the actual
inference engine (see §4); it is required by `libfacedetectcontext.so`,
`libveplugin.so`, and the three ykit plugin libs (DT_NEEDED). The plugin
libs `libykit_core_plugin.so`, `libykit_plugin.so` and
`libykit_module_plugin.so` implement the Java `YcnnPlugin`, `YKitPlugin`,
`AIEditPlugin`, `MmuPlugin` and `YarPlugin` entry points; their codecs
(`com.kwai.video.westeros.v2.ycnn.*`, `…aiedit.*`, `…mmuplugin.*`,
`…v2.yar.*`, `…v2.ykitplugin.*`) live in classes13.dex / classes16.dex.

Model files in APK: **none**. `inventory.ai.model_assets == []`; a
whole-APK scan for the TFLite flatbuffer magic `TFL3` matches only the
schema string inside `libtensorflow-lite.so` (offset `0x2f7c25`, context
`"The model is not a valid Flatbuffer file\0TFL3\0"`). `carve_models.py`
finds no zip/dlc/caffe/TFLite container in any AI lib
(`evidence/carve_models.txt`). The other "hit", `debug.tflite` at
`0x306670`, is the substring of the ATrace category `debug.tflite.trace`.

Confidence: **confirmed**.

---

## 3. Feature map

| feature | entrypoint (Java) | native | model resources | confidence |
|---|---|---|---|---|
| camera face detection + 68/106-pt landmarks | `com.kwai.camerasdk.face.FaceDetectorContext` (`nativeDetectFacesFromFrame`, `setData(type,path)`) | `libfacedetectcontext.so` → `libykit.so` | downloaded `magic_ycnn_model_*` (face detect / landmark) | confirmed (wiring), inferred (resource↔model mapping) |
| FaceMagic live effects / beauty / makeup / 3D / bokeh / body slimming | `com.kwai.video.westeros.v2.faceless.FaceMagicController` + `FacelessPlugin` (JNI in `libfaceless-plugin.so`) | `libFaceMagic.so` + `libskwai.so` + `libCGE.so` | effect package (`MagicFace.resource`) containing `AE2…ycnnModelPath`, `dirtyLensProcessorModelPath`, `mmuModelPath`, `bg_model.bin`/`fg_model.bin` | confirmed (symbols/strings), inferred (per-effect file layout) |
| ycnn plugin: salient region, face attributes, general recognition | `com.kwai.video.westeros.v2.ycnn.YcnnPlugin` (`nativeStartProvideSalientData`, `nativeStartProvideFaceAttributeData`, `nativeSetParamInfo({"attr_local_path":…})`) | `libykit_core_plugin.so` (`YcnnPlugin` JNI 0x144bc…0x16640) → `libykit.so` | `magic_ycnn_model_salient`, `magic_ycnn_model_face_attributes`, `magic_ycnn_model_general_recog` | confirmed (Java+JNI+strings) |
| KVE smart edit / smart cover / gallery / upload analysis | `com.kwai.kve.VisionEngine` (`SmartEditTask`, `SmartCoverTask`, `SmartGalleryTask`, `SmartUploadAnalyzer`) | `libvisionengine.so` (Halide), `libksvisionengineplugin.so` | none bundled; features are computed (scene class, face score, saliency, voice) | confirmed (JNI + feature enum) |
| voice detection in editor | `com.kwai.video.editorsdk2.kve.EditorKveVoiceDetectParam.setModelPath` | editor SDK / TFLite runtime | **`voice_detect_mode.tflite`**, remote resource `voice_detect_mode` | confirmed (class + resource table) |
| Live gift/magic effects | `com.yxcorp.gifshow.live.effect.LiveMagicEffectDownloader` | westeros/CGE | effect packages | inferred (effect is GL/Lua, model optional) |
| AI edit (GAN) / MMU avatar models (memoji, animoji, face blend) | `com.kwai.video.westeros.aiedit.AIEditPlugin` (JNI `libykit_module_plugin.so` 0x14f50), `com.kwai.video.westeros.mmuplugin.MmuPlugin` (0x15e58) | `libykit_module.so` + `libykit.so` | `magic_mmu_model_{animoji1,basewhite,ear,faceblend,faceprop,memoji}`, plus effect-local `aiedit.pbs.AIEditParamGan.{model_path,model_dir,landmark_model_path}` | confirmed (JNI + protobuf strings), inferred (which effect uses which) |

Observed asset-level beauty taxonomy (confirmed, `models/assets/beauty_list`,
`deform_config`): smoothSkin (beauty/bright), makeup groups (blush, eyeBrow,
eyeShadow, lipStick, eyeLiner, eyeLash, eyeBags, PuffyEyes, doubleEyeLids,
faceShadow, cosmeticLenses, Mustache), face/eye deform modes.

KVE quality feature ids (confirmed string table, `libvisionengine.so`):
`kIntegratedScore, kSharpness, kFaceNumber, kFaceSharpness, kEyeOpenness,
kFaceSmile, kFaceArea, kFaceAreaOutOfPic, kFaceAreaAbBri, kFacePosition,
kFaceRotation, kHasValidFace, kColorfulness, kIntraEntropy, kJunk, kLowLight,
kPrincipalMotion, kMotionScore, kSceneChange, kRgbHist, kIconThumbData,
kFocusCenter, kSceneClass, kMajorSceneCluster, kSaliencyPortion, kHumanVoice,
kPureColor, kBadCover, kNoTexture`; plus Halide DAG names `face_score_dag`,
`face_laplacian_dag`, `compute_saliency_area`, `DoSceneClassificationAnalyzing`,
`VoiceDetectorFeature`.

---

## 4. Inference stack and model acquisition

### 4.1 Runtime (`libykit.so`)

* Namespace `ycnn`, `ycnn_net`, `ycnn2`; the mangled factory name
  `_ZN5ycnn28YCNNNet217createYCNNNet2ObjERKNS_14YCNNNetConfig2E` appears at
  `.dynstr` file offset 0x3ad4b.
* Log strings: `BEGIN! %s load model with ycnnnet v4: %s`,
  `%s load model with ycnnnet v3: %s`, `ycnn net binary model version : %d, %d, %d`,
  `ycnn net json model version : %d, %d, %d`, `JNI Get model_path %s` (0x833d12).
* Pixel-plane constraints: `only support nv21/nv12` (0x83af40),
  `only support grey/nv21/nv12 for grey crop` (0x83ad59),
  `currently only support grey/norm_grey` (0x83ad83), and float
  `rgb/bgr/rgb-norm/bgr-norm/yuv0` paths.
* Backends: CPU NEON + OpenCL GPU (embedded OpenCL C kernels), plus
  TFLite (`build TFLite`, `can_use_tflite`, ANeuroPilot TFLite for MTK APU)
  and SNPE (`can_use_snpe`, `KSCreateKSNetSNPE`).
* Model registry baked into `.rodata`: **102** `KSModel*.model` names and
  **19** `*_cfg.{txt,json}` family configs, e.g.
  `KSModelLandmark01..15`, `KSModelFaceAttributes01..04`, `KSModelMatting01..08`,
  `KSModelFaceSeg01`, `KSModelHair01`, `KSModelHandSeg01/02`,
  `KSModelHumanparsing01..03`, `KSModelAnimalLandmarks01..04`,
  `KSModelInstanceSeg01/02`, `KSModelInpainting(.Video)`,
  `KSModelDepth01..03`, `KSModelNailSeg01..05`, plus per-backend net names
  (`*_snpe`, `*_opencl`, `*_mtkapu`, `*_hiai`, `*_coreml`, `*_norm/_midd/_upper`).
  Full lists: `ir/ykit_model_registry.json`.
* Config keys parsed from the per-model header/JSON (same file):
  `net_w`, `net_h`, `input_info`, `output_info`, `model_type`,
  `model_sub_type`, `anchor_stride`, `nms_th`/`nms_thresh`/`nms_method`,
  `score_thresh`, `stride_width`/`stride_height`, `heatmap`, `landmark_param`,
  `inputMeanVals`, `input_type`, `input_channel`, `input_resize`,
  `input_resolution`, `model_files`, `model_load_async`, `model_post_type`.
* A large embedded rapidjson config blob is present (config only, **no
  tensors**): e.g. 3D-landmark table at file offset `0x85597e`
  (`"68": {"alpha0":…,"alpha1":…,"x0":…}` for each landmark), hand/gesture
  config at `0x8507aa..0x850ad9`, `landmarks3d_cfg` at `0x86cb15`.

JNI surface (`native/jni_entrypoints.txt`): `com.kwai.kscnnrenderlib.KSJNILib`
→ `createModel` 0xc81e8, `createModelPost` 0xc82a4, `renderModel` 0xc8360,
`updateModelOut` 0xc8a54 (13,300 B), `releaseModel`, `YCNNGetConfig2Model`
0xce798, `createTechMatting/runTechMatting`, `createTechDepth`, `createImgRecog`.

Westeros plugin JNI (separate libs, both DT_NEEDED libykit.so):
`libykit_core_plugin.so` → `YcnnPlugin.nativeCreateYcnnPlugin` 0x144bc,
`nativeStartProvideSalientData` 0x14598, `nativeStartProvideGeneralRecogData`
0x14818 (1,336 B), `nativeStartProvideFaceAttributeData` 0x151fc,
`YcnnPluginUtils.nativeGetFaceData` 0x15910 (2,016 B), `nativeSetParamInfo`
0x16640; `libykit_plugin.so` → `YKitPlugin.nativeCreateYKitPlugin` 0xab3c /
`nativeSetOnYKitModelLoadFinish` 0xac18; `libykit_module_plugin.so` →
`AIEditPlugin` 0x14f50, `MmuPlugin` 0x15e58, `YarPlugin` 0x18f8c.

`createModel` disassembly (`0xc81e8..0xc82a4`): JNIEnv → `GetStringUTFChars`,
then a ykit-internal call with the path plus the literal tags
`mKSJNIRenderObj` (0x833c1d) and `J` (0x833c2d), then a call taking the
config object. The literal `model_type`/`model_sub_type` (0x833c37/0x833c41)
shows that Java passes a typed model config, and ykit resolves the actual
model file/paths for that type.

### 4.2 Acquisition path (confirmed)

```
Camera/editor UI
   └─ ModelResourceUtil.e(resourceKey)                     magic.c:61
        root = <first cache dir>/.magic_model/magic_model   (if present)
               else <cache dir>/magic_effect_resource/      magic.c:28-59
        localDir = root + resourceKey                       magic.c:61
   └─ MagicModel(resourceKey)                              resource.model.c:29
        getResourceDir() = ModelResourceUtil.e(resourceKey)  :127
        getInitDownloadUrl(cfg) = cfg.getDownloadUrlSuffix(resourceKey, cdnIdx) :117
        needDownload(): uniqueId(url/md5) changed OR dir missing/empty  :204
        checkMd5(): <dir>/check.json {checkList:[{file,md5}]}           :68
        useYcnnModelConfig() = true                                      :261
   └─ config payload (ConfigResponse / YlabModelConfigResponse)
        ConfigResponse: modelResources: Map<String,String>,
                        kwaiPreloadModels{wifi,4G,2G}, voice_detect_mode_v1,
                        model_matting_v1, model_resources…
        YlabModelConfigResponse: modelUrls[{resourceKey, urls[{cdn,url}]}]
   └─ download + unzip into localDir, then md5 validation
   └─ path handed to native:
        FaceDetectorContext.setData(type, path)   ->  nativeSetData(ctx,type,path)
        FaceMagicController.nativeSetExternalBuiltinPath / …BeautifyExternalBuiltinPath
        YcnnPlugin.nativeSetParamInfo({"attr_local_path": "..."})
        KSJNILib.createModel(path, YCNNModelConfig)
```

Evidence:

* `sources/classes/com.yxcorp.gifshow.util.magic.c.java` (ModelResourceUtil).
* `sources/classes/com.yxcorp.gifshow.util.resource.model.c.java` (MagicModel).
* `sources/classes/resource_model_a.java` (resource table; `VOICE_DETECT`
  with `mResource="voice_detect_mode"`, `getResourceName()="voice_detect_mode.tflite"`,
  `getResponseName()=iVar.mVoiceDetect`, `getUnzipDir()=ModelResourceUtil.e(...)`).
* `sources/classes/com.yxcorp.gifshow.model.response.i.java` (ConfigResponse fields).
* `sources/classes/com.yxcorp.gifshow.model.response.r0.java` (ylab `modelUrls`).
* `sources/classes/com.yxcorp.gifshow.util.magic.b.java`
  (MagicEmojiResourceHelper: `check.json` md5 validation; deletes
  `mmu`, `ycnn_face_detect`, `ylab`, `face_3d_resource` sub-dirs on cleanup).
* DEX strings: `ks://download_<resourceKey>` events, `magic_ycnn_model_*`,
  `magic_ycnn_common_strategy`, `magic_ycnn_beautify_assets`,
  `magic_ycnn_face_3d_resource`, `/.magic_model/magic_model`,
  `/magic_effect_resource/`, `/rest/o/production/ycnn/models`
  (classes5.dex; no direct `const-string` xref found for the last one, so the
  endpoint↔YlabModelConfigResponse link is **inferred**).

`/facerecognition/ycnnmodel` (classes3.dex, referenced by
`com.kwai.video.devicepersona.benchmarktest.f.f(DPBenchmarkResult)`) is a
device-benchmark probe path, not the production model dir.

### 4.3 Face/beauty pipeline (confirmed wiring, inferred internals)

```
camera frame (NV21/NV12)
  → FaceDetectorContext.nativeDetectFacesFromFrame(ctx, VideoFrame, type)
      libfacedetectcontext.so 0x1a19c
      Frame → VideoRawFrameFromJni → kuaishou::model::FaceData
  → ycnn face detector (type kYcnnFaceDetect) using model path from setData()
      → libykit.so YCNNNet2 (CPU/OpenCL)
  → FaceData[] (rect + landmark points + score, protobuf bytes back to Java)
  → FaceMagicController.nativeSetEffect / nativeUpdateEffectControl
      libfaceless-plugin.so 0x3d858 / 0x3db7c
  → libFaceMagic.so: FMBeautifyEffectInterface / FM3DEffect / FMMakeupEffect…
      model dirs: nativeSetExternalBuiltinPath,
                  nativeSetBeautifyExternalBuiltinPath,
                  AE2 Resource ycnnModelPath / ycnnModelPathMap,
                  setSkinColorDetectModelPath, mmuModelPath
  → GL render (CGE/skwai): warp, smooth, makeup, segmentation masks
      segments: Face/Body/Cloth/Hair/Hand/Ground/Depth (rodata strings)
```

`libFaceMagic.so` model-related exported symbols (confirmed; numbers are
`.dynstr`/`.rodata` file offsets):
`Java_…_AE2JNI_AE2WesterosWrapperCreator_1Resource_1ycnnModelPath_1{get,set}`
(0x767xx), `…_ycnnModelPathMap_…`, `…_dirtyLensProcessorModelPath_…`,
`…_mmuModelPath_…`; C++ symbols
`FMBeautifyEffectInterface::setBeautifyExternalModelPath` (0x3c754),
`FMEffectAsyncProcessor::setSkinColorDetectModelPath` (0x3bf02); strings
`bg_model.bin` (0x58c12e), `fg_model.bin` (0x58c13b),
`FMBeautifyEffectInterface::setBeautifyExternalModelPath path = (%s)`
(0x58d81d). `libFaceless…` exposes no model file itself; models live inside
each downloaded effect/resource package.

`libfacedetectcontext.so` also depends on `libykit.so`, `libdaenerys.so`,
`libyuv.so`, `libksaudioprocesslib.so` (`native/libfacedetectcontext.so.json`),
matching the ycnn detector + YUV conversion path.

### 4.4 TFLite path

* `org.tensorflow.lite` classes (`NativeInterpreterWrapper`, `Tensor`,
  `TensorFlowLite`, `NnApiDelegate`) exist **only** in classes16.dex and only
  as the packaged runtime; no `org.tensorflow.lite.Interpreter` class is
  defined or referenced (`evidence/dex_tflite_refs.jsonl`).
* The only TFLite model name referenced by app code is
  `voice_detect_mode.tflite`, and it is a *downloaded resource*
  (`resource.model.a$c`), passed via `EditorKveVoiceDetectParam.setModelPath`.
  No `.tflite` bytes exist in the APK.
* `libtensorflow-lite.so` is loaded by `TensorFlowLite.<clinit>`; its JNI table
  matches upstream TFLite (1.13-era API: `createModel`, `run`, `resizeInput`,
  `useXNNPACK`). No NNAPI/GPU delegate is instantiated from Java in this APK.

---

## 5. Models & IR

| path | format | size | weights | confidence |
|---|---|---:|---|---|
| (in APK) — | — | — | none | confirmed |
| remote resource `KSModel*.model` ×102 (registry) | ycnnnet v3/v4 binary or JSON model | unknown | encrypted/downloaded; unavailable | confirmed names, inferred layout |
| remote `voice_detect_mode.tflite` | TFLite flatbuffer | unknown | unavailable | confirmed name/route, unknown content |
| `libykit.so` embedded | rapidjson config tables (landmark alpha0/alpha1/x0, hand/gesture, net configs) | ~130 KB region | none (no tensors) | confirmed |
| `assets/BodySlimming/*.params` | JSON deform params | 123–2181 B each | n/a | confirmed |
| `assets/beauty_list`, `deform_config` | JSON presets | 6,951 / 5,177 B | n/a | confirmed |
| `assets/demo_algorithm_info.txt`, `demo_vision_feature_response_v2.txt` | JSON sample quality reports (blocky/blur/colorfulness/defocus…) | 7,291 / 7,646 B | n/a | confirmed |

`ir/ykit_model_registry.json` holds the extracted registry: 102 model file
names, 19 cfg paths, 342 `net_*` names, 28 config keys.

---

## 6. JAX reimplementation

`reimpl/pipeline.py` (JAX 0.12.0.1, CPU) implements the recovered contract:

1. `nv21_to_rgb` — NV21/NV12 → float RGB, limited-range BT.601 (libyuv/ycnn).
2. `preprocess` — crop + bilinear resize + `inputMeanVals` subtraction.
3. `init_params` / `apply` / `decode` — ycnn-style trunk with heatmap +
   2·68 landmark heads; **deterministic random weights**, architecture only.
4. `kve_features` — the KVE feature statistics (`kSharpness`,
   `kColorfulness`, `kLowLight`, `kPureColor`, `kIntraEntropy`, `kJunk`,
   `kSceneChange`); formulas are inferred from the feature names, no weights.

Status: `ARCHITECTURE_ONLY`; `reimpl/verify.txt` shows exit 0 and shapes
(`(1,128,128,3) → score (1,16,16,1), landmark (1,16,16,136)`).

Limitations: no numeric parity is possible — real graphs are inside the
downloaded `.model` files, and the face decoder config
(`stride_width`, `score_thresh`, landmark semantics per model type) is
model-specific. The JAX detector must not be presented as Kwai's network.

---

## 7. Unknowns / blockers

* Exact ycnnnet `.model` container layout (header + quantised layer blocks) —
  not in the APK; requires a runtime-downloaded model.
* Which `KSModel*` is selected for a given `model_type`/`model_sub_type`
  (mapping is inside `YCNNGetConfig2Model`, stripped, no strings).
* Per-model `net_w/net_h`, `inputMeanVals`, landmark count (68 table is
  embedded, but 106-point configs also exist elsewhere in Kwai).
* Whether any dynamically loaded plugin APK ships pre-baked models for this
  device (none present in the system image for this package).
* `/rest/o/production/ycnn/models` ↔ Ylab config link (no code xref found;
  endpoint name and response schema match, so marked inferred).
* Real FaceMagic effect-package layout (which files under
  `<root>/<magic_face_id>_<name>/` are ycnn models, shaders, params).

---

## 8. Files

```
inventory/            inventory.json/md (+ libs failed extraction: see note)
native/               ELF JSON + summaries, JNI tables, 2 disassemblies, libs/
sources/              index.md + 12 jadx single-class files
models/assets/        7 extracted asset files (no model weights exist)
ir/                   ykit_model_registry.json
reimpl/               pipeline.py, README.md, verify.txt
evidence/             cmd.txt, lib_sha256.txt, lib_hashes_vs_video.json,
                      zip_diff_raw.json, carve_models.txt, dex_*.jsonl/txt
```

Note: `apk_inventory.py --extract-libs` wrote all 80 libs to
`inventory/libs/lib/arm64-v8a/`; the AI subset was additionally extracted to
`native/libs/` for ELF analysis (`evidence/lib_sha256.txt`).

---

## 9. Frame → inference → render trace (2026-10-24 patch)

Full contract: `PIPELINE.md`. Evidence: `evidence/gap_pipeline/`,
`native/daenerys_VideoRawFrameFromJni.s`, `native/ykit_renderModel.s`,
`native/ykit_updateModelOut.s`, `native/facedetect_nativeSetData.s`.
New decompiled classes: `sources/classes/{mo.*,com.kwai.kscnnrenderlib.*,
com.kwai.video.westeros.v2.faceless.*}.java`.
Native bridge lib added: `libdaenerys.so` (`native/libs_extra/`) — it owns
`kuaishou::android::VideoRawFrameFromJni` and the whole camera/encoder JNI, which
the first pass had not inspected.

### 9.1 Producer and frame format (confirmed)

```
camera2 → libdaenerys media source
   Java_com_kwai_camerasdk_videoCapture_DaenerysFrameObserver_nativeOnVideoFrameCaptured
     libdaenerys.so 0xe18d8 (432 B)
   → com.kwai.camerasdk.video.VideoFrame  {type, yuv_format, width, height, timestamp,
        data:FrameBuffer|textureId+isOesTexture|bitmap, originalFrame,
        getSerializedAttributes() -> kuaishou.model.VideoFrameAttributes}
   → com.kwai.video.ksmedialivekit.LivePushClient$1.onVideoFrame(VideoFrame)   (classes8.dex)
```

`VideoFrame` type constants (from the sibling build; consistent with the native
dispatch): `None 0, YUV 1, TEXTURE 2, RGBA 3, BITMAP 4`.
`kuaishou::android::VideoRawFrameFromJni` (`libdaenerys.so` 0xece3c, 6048 B)
reads the fields at these `.rodata` offsets and dispatches:

| `type` | native branch | MediaDataType |
|---|---|---|
| 1 YUV | 0xed084 → table @0x1e0f74[0] | 35 |
| 2 TEXTURE | 0xed0a0 (`textureId`, `isOesTexture`, `textureNotUsedRunnable`) | texture ctor |
| 3 RGBA | 0xed07c | 38 |
| 4 BITMAP | 0xecee4 (`bitmap`) | bitmap ctor |

Fields: `type` 0x1f327b, `yuv_format` 0x1e08a8, `bitmap` 0x1e091d, `width` 0x1f3400,
`height` 0x1f340e, `timestamp` 0x1e08f6, `getSerializedAttributes` 0x1e0900,
`data`/FrameBuffer 0x1e0948/0x1e097b, `isDirect` 0x1e09a7, `byteBuffer` 0x1e09b0,
`getByteArray` 0x1e09bb, `release` 0x1e09c8, `originalFrame` 0x1e0876.
Rotation is carried in `VideoFrameAttributes.transform` (`kuaishou.model.Transform`:
mirror, rotation, crop_x/y/width/height_frac — symbols at `libdaenerys.so`
0x3624e-0x36454), and the detector additionally consumes the sensor state
(`kuaishou::SensorManager::GetOrientation90`, imported by `libfacedetectcontext.so`).

**Variant difference (confirmed).** `com.kwai.camerasdk.video.VideoFrame` is
**defined in `classes15.dex` of `partner-com.kwai.video_33.apk`** (21 methods) but is
**not defined in any DEX of this APK** — `dex_find.py` reports `defined:false` for
classes3/8/15/16 while `CameraSession`/`CameraController` remain defined. Its Java body
was therefore decompiled from the sibling and cross-checked field-by-field against the
JNI lookups (`evidence/gap_pipeline/video_variant/`). Likewise `FaceDetectorContext.
detectFacesFromFrame` has **no DEX caller here**; the only in-APK method whose
descriptor mentions `Lcom/kwai/camerasdk/video/VideoFrame;` is the live-push listener
(`LivePushClient$1.onVideoFrame`, classes8.dex). The native pipeline is unaffected because all
14 AI libs are byte-identical to `kwai_video`.

### 9.2 Detection: JNI contracts

```
FaceDetectorContext.detectFacesFromFrame(VideoFrame, l0)      classes15.dex
  → JNI  libfacedetectcontext.so 0x1a19c (1312 B)
      VideoRawFrameFromJni(env, frame)                       0x1a1dc
      AttachCurrentThreadIfNeeded                            0x1a2b4
      [ctx iface vtable + 0x68](iface, {frame,shared_ptr}, type, &results) 0x1a24c  <- detect
      NewObjectArray(env, n, FaceData class)                  JNI #172 (env+0x560) 0x1a30c
      for each FaceData: SerializeToString                   0x1a6f8
                         NewByteArray                         JNI #176 (env+0x580) 0x1a71c
                         SetByteArrayRegion(0, n, data)       JNI #208 (env+0x680) 0x1a75c
                         SetObjectArrayElement(arr, i, bytes) JNI #174 (env+0x570) 0x1a390
  → Java mo.g0.f(byte[]) = FaceData.parseFrom
```

`nativeSetData(ctx,type,path)` (`0x18e28`) only forwards to
`[ctx vtable + 0x28](ctx, type, std::string path)` (`0x18e8c`) — the model path
contract is a plain string; the Java `setData` is guarded by the vendor flag
`com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.f23915h`.

Detector output protobuf `kuaishou.model.FaceData` — layout confirmed from both the
Java class and the native serializer (`libdaenerys.so` 0x179520):
`1 float, 2 float, 3 float (confidence), 4 Rect (rect), 5 FacePose (yaw/pitch/roll),
6 FaceLandmark (points/point_bytes/points_other/extra/flipped), 7 Rect (original_rect),
8 Gender enum`.
Tags 1-2 remain unnamed (`unknown`); see `evidence/gap_pipeline/proto_shapes.txt`.

Enums used by the detector path (`sources/classes/mo.*.java`, classes15.dex):
`FaceDetectorName{kVideoDetector 0, kImageDetector 1}`,
`FaceDetectInitType{Auto 0, Sync 1, Async 2}`,
`FaceDetectMode{Normal 0, Tracking 1, TrackingFast 3, TrackingRobust 4, DetectTrack 5,
TrackingRect 6}`, `FaceDetectType{kYcnnFaceDetect 0}`.

### 9.3 ycnn invocation and output structs (confirmed)

`KSJNILib.renderModel(Object, byte[])` (`libykit.so` 0xc8360) resolves exactly these
Java members (`native/ykit_renderModel.s`): `KSRenderObj.mKSJNIRenderObj` (J),
`mJNIRenderInfo` (String, written back), `mModelIn` (`YCNNModelInfo$YCNNModelIn`) and
the model-input fields `colorType, rotate, flipHor, width, height, frame_time,
single_image, normOut, normRotate, normFlipHor, normFlipVer`. The pixels are the second
JNI argument (`KSRenderObj.runModelBuffer` passes `yCNNModelIn.data_0`); `faces` and
`param_info` are **not** read by `renderModel`.
`KSJNILib.updateModelOut` (`0xc8a54`, 13,300 B) fills `KSFaceDetectOut.faces ->
KSFaceInfo{index,left,top,width,height,confidence,yaw,pitch,roll,pos: {xPos,yPos,zPos,
valid}}` plus `mMattingOut`, `mHeadSegOut`, `mHumanposeOut`, `mInstanceSegOut`,
`mSceneOut`, `mTaskDetectOut`, `mGeneralRecogOut.out_json`, `mInpaintingOut`.
Landmark/rect coordinate conversion back to view space is
`ycnn::TransLandmarksImageToView` (`.dynstr` symbol offset 0x2f0c0), rect algebra
`KSRectfInter`/`KSRectfCombine` (0x2dec3/0x2e047, same numbering).

`YcnnPlugin` (`sources/classes/…v2.ycnn.YcnnPlugin.java`, JNI in
`libykit_core_plugin.so`) binds **no** Java frame: it is a `WesterosPlugin` that
registers start/stop providers (`nativeStartProvideSalientData`,
`nativeStartProvideFaceAttributeData`, `nativeStartProvideGeneralRecogData`) and only
receives a model directory (`setAttrLocalPath` → `{"attr_local_path": …}`); the plugin
pulls frames from the westeros render graph itself.

### 9.4 Render / encode / threading / lifecycle (confirmed wiring)

`FacelessPlugin` (classes13.dex) → `new FaceMagicController(nativePlugin, true)` →
`nativeInitController` (`libfaceless-plugin.so` 0x3d49c) → `nativeGetGlProcessor`
(0x3d6f0) registers `FM::FMGlProcessor` as a `WesterosGlProcessor`.
`libfaceless-plugin.so` DT_NEEDED `libykit.so` + `libFaceMagic.so`, i.e. the effect
plugin runs its own ycnn detection and hands `FM::FMFaceData` to the renderer
(`FMDataExchanger::setTransFaceData` 0x342ff, `FMEffectHandler::updateFaceData`
0x3685c; `.dynstr` symbol offsets). Detector modes appear as `FaceDataWithFastMode/RobustMode/Robust3DMode/
ImageMode` in `libFaceMagic.so` (0x587b77-0x587de7), matching `FaceDetectMode`.
Per-frame: `FMGlProcessor::OnDraw()`/`update(effectTime,duration,frameTime)` on the GL
render thread; teardown `OnRenderThreadDestroy()`. Encoding is native:
`MediaRecorderImpl.nativeStartRecording` (`libdaenerys.so` 0xe46dc),
`nativeCaptureOneVideoFrame` (0xe4ef0), `nativeSetTargetFps` (0xe513c).
Detection is serialized by `FaceDetectorContext.lock`; init can be asynchronous
(`is_async_init`, `Runloop` in `libfacedetectcontext.so` rodata 0x2408a/0x23fb8).

### 9.5 Model download / fallback (additions)

* Native reads model **bytes through Java**:
  `com.kwai.camerasdk.utils.ResourceLoader.loadData(String, boolean)[B` /
  `loadImageToRGBA` / `loadText` via `kuaishou::android::AndroidResourceLoader`
  (`libfacedetectcontext.so` rodata 0x239a9, 0x23ac4, 0x23cbd); `file://asset/` accepted
  (0x243bd). A missing file surfaces as `[YcnnFaceDetector] Ycnn model missing %s`
  (0x244be) → `onModelMissingCallback(int, String)` (0x249fd) →
  `ModelMissingListener.onModelMissing`.
* Effect side: `FMGlProcessor` publishes `WESTEROS_EVENT_MODEL_MISSING` and Java raises
  `FaceMagicLoadEffectFailedListener`; `FaceMagicController.isResourceValid(dir)` is just
  `new File(dir,"params.txt").exists()`.
* Resource acquisition unchanged (report §4.2): `<cache>/.magic_model/magic_model/<key>`
  or `<cache>/magic_effect_resource/<key>`, unzip + `check.json` MD5.

### 9.6 New unknowns added by this patch

* Java class that ships `com.kwai.camerasdk.video.VideoFrame` at runtime in bulldog
  (absent from all 16 DEX).
* names of `MediaDataType` 35/36/38; `FaceData` float fields 1-2.
* rotation arithmetic inside the stripped `FaceDetectorContextInterface`
  implementation (only the `SensorManager` import and `Transform` schema are visible).
