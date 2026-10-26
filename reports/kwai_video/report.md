# com.kwai.video (Kwai) 6.0.20.524206 — AI/vision subsystem

| | |
|---|---|
| Package | `com.kwai.video` |
| App | Kwai (Kuaishou international), preinstalled partner build |
| Version | 6.0.20.524206 (code 524206) |
| APK | `cust/app/customized/partner-com.kwai.video_33/partner-com.kwai.video_33.apk` |
| Size / SHA-256 | 80,706,484 B / `a0050c589bf6d748e2c721c86e5e0f5e254e594aa88822fe304810af9611f98e` |
| ABI | arm64-v8a only |
| Zip | 8,130 entries: 16 dex (53.9 MB), 80 native libs (114.3 MB), **0 model assets** |
| Manifest | minSdk 21, targetSdk 31, main `com.yxcorp.gifshow.homepage.HomeActivity` |

All claims below cite a binary offset, a symbol, a decompiled class, or a command
transcript under `evidence/`.

---

## 1. Headline result

Kwai's on-device vision stack is **three engines plus a shared neural-net runtime**:

1. **YCNN / `ykit`** — Kuaishou's own inference engine (`libykit.so`, 10.3 MB; 3,103 exported symbols, 1,228 of them
   `ycnn::*` / `ycnn_net::*`). Implements face detect, landmarks, matting,
   head/hair seg, human parsing/pose, scene classification, saliency, style transfer,
   inpainting — 35 model types with IDs recovered from `YCNNModelInfo`.
2. **Westeros + FaceMagic + Gorgeous + skwai** — the AR/beauty *renderer*: it consumes
   landmarks/masks produced by YCNN and applies GL/GLSL effects (a full beauty chain,
   makeup, 3D mesh, face reshape, LUT grading).
3. **KVE (`libvisionengine.so`)** — the smart-edit/auto-cut analyzer: scene-change,
   motion, quality and highlight DAGs, plus an upload-bitrate model (not a NN).
4. **TensorFlow Lite 2.4.1** (`libtensorflow-lite.so`) is shipped and is used only by
   `libykit_module.so` (generative "AI edit") and `libksaudioprocesslib-dl.so`.

**No model file of any kind is inside the APK.** Every network (YCNN `.model`,
AI-edit `.fbm`, any `.tflite`) is fetched at runtime from Kuaishou's model service
(`/rest/o/production/ycnn/models`, resource packs `model_matting_v1`, …) and handed to
native code as a filesystem path. See §5 with evidence.

---

## 2. Native inventory (vision/beauty relevant)

`evidence/01_inventory.txt`, `native/*.json` (full `elf_info.py` dumps).

| Library | Size | JNI exports | Role |
|---|---:|---:|---|
| `libykit.so` | 10,292,232 | 30 | **YCNN engine** + inference JNI (`com.kwai.kscnnrenderlib.KSJNILib`) |
| `libykit_module.so` | 15,788,912 | 11 | AI-edit generator (`com.kwai.aieditlib.AIEditJNILib`); links TFLite |
| `libykit_core_plugin.so` | 592,000 | 12 | per-frame YCNN providers (`…westeros.v2.ycnn.YcnnPlugin`) |
| `libykit_plugin.so` | 260,216 | 5 | `…westeros.v2.ykitplugin.YKitPlugin` |
| `libFaceMagic.so` | 7,722,568 | **1,802** | AR-effect/beauty renderer (`com.kwai.FaceMagic.nativePort.*`, `…AE2.*`) |
| `libskwai.so` | 6,515,760 | 0 | `SKwai::VirtualFaceProcessor` — GL/Lua AR scene graph (exact C++ exports) |
| `libgorgeous.so` | 1,616,920 | 0 | Gorgeous beauty SDK; **all beauty GLSL is plain rodata** (334 C++ exports) |
| `libwesteros.so` | 1,395,688 | 43 | camera capture framework, model-path setters |
| `libfacedetectcontext.so` | 191,728 | 26 | camera face detector (`kuaishou::face::YcnnFaceDetector` → YCNN) |
| `libvisionengine.so` | 2,108,736 | 95 | KVE analysis (`com.kwai.kve.*`) |
| `libfaceless-plugin.so` | 1,792,128 | 44 | `AIEditProvider`, `aiedit.pb`, `face_magic.pb` glue |
| `libtensorflow-lite.so` | 3,446,232 | 2,612 | TF Lite **2.4.1**, 215 builtin ops, NNAPI+XNNPACK |
| `libksvisionengineplugin.so` | 38,984 | 21 | plugin shim (`vision_engine.pb.cc` build path) |
| `libkwaivpp.so` | 5,760 | 0 | empty stub (60-byte `.text`, no exports) |

The complete JNI map of all nine vision-related libraries is in `evidence/04_jni_map.txt` (2,024 `Java_*` entry points; `libskwai.so` has none — it exposes a plain C++ `SKwai::VirtualFaceProcessor` API consumed by `libFaceMagic.so`, DT_NEEDED `libskwai.so`).

---

## 3. Pipeline map

### 3.1 Camera path — face detect → landmarks → beauty → encode

```
Camera2 / KS camera (libwesteros.so, 43 JNI: com.kwai.video.westeros.*)
   │  VideoFrame (NV21), sensor orientation
   ▼
com.kwai.camerasdk.face.FaceDetectorContext      (libfacedetectcontext.so, 26 JNI)
   │   static{ load opencv_world; ykit; facedetectcontext }      [FaceDetectorContext.java:68-73]
   │   nativeInit(ctx, mode, …)  → BaseFaceDetector::Init(modelPath, …)
   │   nativeDetectFacesFromFrame(jlong, VideoFrame, int) → byte[][] (per-face protobuf)
   ▼
kuaishou::face::YcnnFaceDetector  (symbol string in libfacedetectcontext.so)
   │   YCNN model type 21 = "Detector", 5 = "Landmarks"          [YCNNModelInfo constants]
   │   YCNNModelIn{ data_0 = NV21 bytes, width, height, colorType=3, faces=[crop rects] }
   ▼
ycnn::YCNNModel::runModelBuffer → ycnn_net (NEON fp16 / OpenCL / MTK APU)
   │   outputs: KSFaceDetectOut{faces[]: rect, confidence, yaw/pitch/roll, pos[K] (x,y,z,valid)}
   ▼
libykit_core_plugin.so  YcnnPlugin  (com.kwai.video.westeros.v2.ycnn)
   │   nativeStartProvideSalientData / …GeneralRecogData / …FaceAttributeData
   │   callbacks: SalientData, YcnnFaceAttributeResult, general-recog JSON string
   ▼
FaceMagic / Gorgeous / skwai render
   │   FM.* JNI: updateLandmarks3DData(), updateSegmentation(), FMBeautifyG3Effect,
   │            FMGorgeousMakeup, FMBokehDepthEffect, FMWhiteSKinEffect …
   │   Gorgeous GSBeautifyGT: mean/variance pass → kMin skin smoothing → LUT → sharpen
   ▼
MediaCodec / ffmpeg encode (libffmpeg.so, libvideoprocessor.so) → upload
```

Decompiled evidence: `sources/classes/com.kwai.camerasdk.face.FaceDetectorContext.java`
(422 lines, incl. `ModelMissingListener.onModelMissing(int, String)` — the runtime signal that
a model is absent), `…/YcnnPlugin.java` (193 lines), `…/YCNNModelInfo.java` (307 lines,
all structs + constants), `…/KSRenderObj.java` (42 methods, output marshalling),
`…/KSJNILib.java` (32 native methods).

### 3.2 Editor path — smart edit / auto-cut (KVE)

`com.kwai.kve.SmartEditTask` (`libvisionengine.so`, 95 JNI) runs a DAG of analyzers:

```
MakeBasicDag / MakeAnalyzingDag
  ├── scene_change_analysis_dag_       (cut detection)
  ├── motion_analysis_dag              (camera/object motion)
  ├── face_score_dag, face_laplacian_dag, focus_center_dag, homo_proc_dag, complexity_dag_
  ├── DoSceneClassificationAnalyzing   (scene labels from YcnnPlugin general recog)
  └── image_quality_evaluation → image_quality_score
      ↓
BatchFindHighlight / FindVideoHighlightFrame / compute_face_scores
      ↓
AssetDurationRefiner, cover selection, MusicBeat, theme assembly
```

rodata evidence in `libvisionengine.so`: `face_score_dag`, `image_quality_evaluation`,
`compute_face_scores`, `DoSceneChangeAnalyzing`, `DoSceneClassificationAnalyzing`,
`<json_obj:highlight_clip=`, `"final_score":[`, `"frame_score":[`.
`SmartUploadAnalyzer` is separate and *not* a NN: `kveOpenCape` → `getHardEncBitrate` /
`getSoftEncEstBitrate` (bitrate prediction).

### 3.3 Generative "AI edit"

`libykit_module.so` `com.kwai.aieditlib.AIEditJNILib` (11 JNI) + `libfaceless-plugin.so`
`AIEditProvider`: model keys `aiedit.pbs.AIEditParamGan.{model_path, model_dir,
gan_model_path, landmark_model_path}`, model files `AIEdit_FaceSeg_0x.fbm`,
`AIEdit_HairSeg_0x.fbm` (12 `.fbm` names in rodata), executed through TFLite 2.4.1
(DT_NEEDED `libtensorflow-lite.so`).

Recovered invocation chain (undefined TFLite symbols imported by that library,
full list in `ir/tflite_runtime.txt`):

```
ykit::IModel::createModel(DiskDataStore, name)
  -> tflite_from_filepath / tflite_from_datastore
  -> FlatBufferModel::BuildFromFile | BuildFromBuffer
  -> BuiltinOpResolver -> InterpreterBuilder(&interp, threads)
  -> Interpreter::ResizeInputTensor / AllocateTensors / Invoke
guard rail: "TFLITE model must have postfix .tflite"
```
Pre/post-processing is not in the runtime but in `AIEditJNILib.renderModel` /
`updateModelOut` / `renderModelPost` (GL texture in, mask+GAN texture out).

---

## 4. Beauty pipeline in detail (the part that is fully recoverable)

`libgorgeous.so` embeds its fragment shaders as ASCII rodata. Transcribed
(`reimpl/gorgeous_beauty.py`, `models/` for weights):

* **pass 1** (file offset `0x12f75d`, 698 B): 5-tap mean (`textureCoord`, 4 diagonal taps at ±`texOffset`)
  → `meanColor = sum * 0.2`; detail energy `diff = min(((i - mean) * 7.0)^2, 1.0)`,
  alpha = `(diff.r+diff.g+diff.b) * 0.33`.
* **pass 2** (`0x12fc3d`, file offset): 3×3 box blur (`* 0.1111`) of (mean, variance).
* **beautify** (file offsets `0x1312c8` / `0x1319be`): with `theta = 0.1`,
  `p = clamp((min(in.r, blur.a) - 0.2) * 4.0, 0, 1)`,
  `kMin = (1 - meanVar/(meanVar+theta)) * p * blurAlpha [* skinMask]`,
  `out = mix(in, blur.rgb, kMin)`.
* **LUT** (file offset `0x12fef8`, `vec3 lookup()`): Hald CLUT, 8×8 tiles, 64 blue levels over a
  512×512 texture.
* **sharpen** (rodata `vec3 sharpen(vec3 ori, vec3 base, float sharpness)`): luma-domain
  unsharp: `high = (Yori - Ylow) * (1 - base.x^2) * sharpness`, recombined through YUV.
  Luma coefficients per `colorSpace`: 0 → `(0.257,0.504,0.098)+16/255`,
  2 → `(0.183,0.614,0.062)+16/255`, 3 → `(0.213,0.715,0.072)`,
  else → `(0.2983549,0.58747799,0.11416711)`.

The only real weight data in the package for this stage is
`assets/default_enhance_lut.png` (64×64 RGBA8 = 8×8×8 Hald CLUT, sha256
`832f73e1…`), copied to `models/default_enhance_lut.png` and used verbatim by
`reimpl/pnglut.py`.

---

## 5. Model acquisition — **proof and mechanism**

### 5.1 Proof of absence inside the APK

| check | result |
|---|---|
| `apk_inventory.py --extract-models` | `models: 0`, "model_assets: 0" (`inventory/inventory.md`) |
| zip members matching `.tflite/.onnx/.fbm/.model/.pb/.mnn/.ncnn` | 0 |
| largest assets | `avee.zip` 568 KB, LUT/filter PNGs, fonts — no networks |
| `carve_models.py` on **all 80** libs (`evidence/02_carve_all_libs.txt`) | only one false positive: the literal string `debug.tflite` at `libtensorflow-lite.so:0x306670`; no `TFL3` payload anywhere (the `TFL3` hit at `0x2f7c25` is the identifier constant inside the runtime's own code) |
| **TFLite `TFL3` magic in any lib** | none (only `libtensorflow-lite.so`, i.e. the runtime itself) |

**Conclusion: confirmed — the APK ships no model.**

### 5.2 Mechanism (confirmed)

1. **Catalog.** `libykit.so` / `libykit_module.so` hold the model-name registry:
   132 unique `*.model` filenames (683 `KSModel` strings in `libykit.so`, 700 in
   `libykit_module.so`), e.g. `KSModelDetector`, `KSModelLandmark01…15`,
   `KSModelFaceAttributes01…04`, `KSModelMatting01…08`, `KSModelHumanparsing01…03`,
   `KSModelMakeup01/02`, `KSModelHeadSeg01`, `KSModelDepth01…03`, `KSModelHdr01`,
   `KSModelInstanceSeg01/02`, `KSModelHumanMesh01`, `KSModelGesture01`, `KSModelSky01`,
   `KSModelInpainting(.Video)` (`evidence/03_model_names.txt`).
   Runtime ids: `magic_ycnn_model_{head_seg,matting,face_seg,humanmesh_half,ycnnv3,heart_estimation,…}`
   incl. `{"missing_models": ["magic_ycnn_model_…` and `{"finish_load_models": [...]}`.
2. **Delivery.** Resource packs, not assets:
   * `com.yxcorp.gifshow.util.resource.model.a` (decompiled, 1,057 lines) enumerates
     downloadable AI resources — `CUT_MATTING("model_matting_v1", "ks://download_model_matting")`,
     `VIDEO_LIKE_EFFECTS("effects_like_motion", …)`, `VOICE_DETECT("voice_detect_mode", …)`,
     `STICKER`, `THEME`, `EMOJI`, `EDITOR` … each with `useYcnnModelConfig()`.
   * `com.yxcorp.gifshow.util.resource.model.c` ("MagicModel.java", decompiled):
     `getResourceType() == 4`, `addToMagicModelsResourceList()`, url from
     `response.getDownloadUrlSuffix(resource, cdnIndex)` (multi-CDN retry),
     MD5 validation via `com.yxcorp.gifshow.util.magic.b.k(dir)`,
     unzip dir via `com.yxcorp.gifshow.util.magic.c.e(resource)`.
   * Endpoints/paths (dex strings, classes5.dex): `/rest/o/production/ycnn/models`,
     `/facerecognition/ycnnmodel`, `/Ycnn/modelA`, `/.magic_model/magic_model`,
     `%s/model_asset`.
3. **Hand-off to native.** Only paths cross the JNI boundary:
   * `AE2WesterosWrapperCreator$Resource.{ycnnModelPath, ycnnModelPathMap, mmuModelPath,
     dirtyLensProcessorModelPath, face3dResPath, deformJsonPath}` (setters are JNI, `libFaceMagic.so`).
   * `libwesteros.so`: `ResourceManager.nativeSetDirtyLensDetectModelPath`,
     `nativeSetYlabModelPathConfig` (`kuaishou.westeros.model.YlabModelPathConfig`).
   * `YcnnPlugin.setAttrLocalPath(path)`, `AIEdit` proto fields `model_path/model_dir`.
   * `ykit`: `IModel::createModel(IDataStore, name)` with `DiskDataStore`/`YFileDataStore`/
     `YCfgDataStore`; "**YCNN model only supports DiskDataStore**"; `tflite_from_filepath`,
     `tflite_from_datastore`.
4. **Failure signal on device.** `FaceDetectorContext.ModelMissingListener.onModelMissing(int, String)`
   and `{"missing_models": [...]}` — the SDK is designed to run without weights.

Confidence: **confirmed** (code + strings), with the exact CDN host not present in the
binary (only the relative API paths are) — that part is **unknown** by construction.

---

## 6. Reimplementation (`reimpl/`)

| file | content | status |
|---|---|---|
| `pipeline.py` | end-to-end driver: synthetic frame → NV21 → YUV→RGB → crop/resize → detector → beauty → LUT → sharpen | **runs, exit 0** |
| `gorgeous_beauty.py` | exact JAX transcription of the Gorgeous beauty GLSL (mean/var, kMin composite, sharpen, YUV matrices) | **real recovered math** |
| `pnglut.py` | PNG decoder + Hald-CLUT sampler transcribed from `vec3 lookup()`; uses the real shipped LUT | **real weights** |
| `ycnn_contract.py` | YCNN I/O contract (model-type ids, `KSLandmarksParam` defaults, NV21 decode, `YCNNCropDataMatrix` crop semantics) + a detector/landmark head with **random** weights | **ARCHITECTURE_ONLY** |
| `verify.txt` | run log | **PASS** |

`verify.txt` key lines:

```
[1] NV21 encode/decode  roundtrip_psnr=36.43 dB
[2] crop → net input    x=(128, 128, 3)   (size is ARCHITECTURE_ONLY)
[4] beauty mean/var     mean=(270,480,3) var=(270,480) kMax=0.182
[5] Hald LUT (REAL)     lut=(64,64,3) from assets/default_enhance_lut.png
[V1] JAX vs numpy parity max_abs_err=0.000e+00
[V3] LUT identity check  max_delta=3.209e-07
RESULT: PASS  exit_status=0
```

**Blocker for a full reimplementation:** the YCNN `.model` weights and graphs are not
obtainable from the package; the face-detect/landmark/matting stages therefore stay
`ARCHITECTURE_ONLY` (contract exact, weights absent). The beauty/LUT/sharpen stages are
complete and numerically verified.

---

## 7. Unknowns

1. YCNN network resolution per model (`net_w`/`net_h` live in the `.model` header).
2. Landmark count / output layout per landmark model (`LinkedList<KSPtInfo>`, model-defined).
3. `SKwai` Lua-scripted effect semantics (assets are downloaded per sticker).
4. Exact CDN host for `/rest/o/production/ycnn/models` (only the API path is in the binary).
5. Whether `libvisionengine`'s `image_quality_evaluation` is purely hand-crafted CV or calls
   a downloaded model — no TFLite/YCNN imports and no model strings in that library
   (evidence: 0 `TfLite*` imports, 0 `ycnn` strings).
6. Per-layer precision of the YCNN models (fp16 vs fp32) and their session/config numbers.

---

## 8. Reproduce

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
TOOLS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tools
APK="…/partner-com.kwai.video_33.apk"

$PY $TOOLS/apk_inventory.py "$APK" --out inventory --extract-models --extract-libs
$PY $TOOLS/strings_dex.py "$APK" --regex 'ycnn|model|tflite' --unique
$PY $TOOLS/carve_models.py libykit.so          # and every other lib
$PY $TOOLS/disasm_elf.py  libykit.so --list-jni
$PY $TOOLS/decompile_classes.py "$APK" --regex 'kscnnrenderlib|gifshow\.util\.resource' \
     --out sources --max 8
cd reimpl && $PY pipeline.py
```

---

## 9. Frame path: camera → detector → landmarks → beauty → encoded frame

Full contract: **`PIPELINE.md`** (9 template sections, every row tagged
`confirmed` / `inferred` / `unknown`). Raw traces:
`evidence/07_camera_jni_trace.md` (JNI + buffer formats),
`evidence/08_daenerys_westeros_strings.txt` (offset→string dump of
libdaenerys / libwesteros / libykit_core_plugin),
`native/daenerys_videorawframe.s` + `.strings.txt`, `native/ylab_createmodel.s`.

> `libdaenerys.so` (2.58 MB, sha256 `3b0758bf…b146ee`) was missing from the
> first extraction (the 90 MB cap skipped it). It is the Daenerys camera
> pipeline core — the `Java VideoFrame → C++ VideoRawFrame` bridge, the
> `Video*Workshop` classes and the 4-thread pipeline live there.

### 9.1 Producer and buffer format

* `com.kwai.camerasdk.videoCapture.cameras.camerakit.d` (**CameraKitSession**,
  Camera2 through Huawei CameraKit) `inner class e implements
  ImageReader.OnImageAvailableListener`:
  `ImageReader.acquireNextImage()` →
  `com.kwai.camerasdk.videoCapture.i` (“YUVFrameBufferReader”) →
  `VideoFrame.fromCpuFrame(FrameBuffer, w, h, yuv_format, tsMs)
  .withTransform(o1.p().f(rotation).e(mirror).build())` →
  `onVideoFrameCaptured(session, frame)`.
* `yuv_format` is decided from the `Image` plane pixel strides:
  `plane[2]==2` → **NV21 (2)**, `plane[1]==2` → **NV12 (1)**, else **I420 (0)**;
  `3` = RGBA32. Buffer is always de-strided to `1.5·w·h` bytes
  (`ByteBufferUtils.a` row copy when `rowStride != width`).
* `VideoFrame` (classes15.dex) is the only frame currency:
  `type` 1 YUV / 2 TEXTURE / 3 RGBA / 4 BITMAP, direct `FrameBuffer`,
  ms timestamp, `attributes` protobuf (rotation, mirror, crop, facing,
  `kBt601FullRange`, `kFrameSourcePreview`).
* `CameraControllerImpl$b.onVideoFrameCaptured` drops stale-session frames
  (`textureNotUsedRunnable`) and republishes; `CameraControllerImpl` falls back
  to `kAndroidCamera1` on `CAMERA_UNIT_AUTHENTICATION_FAILED` (:882-886).

### 9.2 JNI signatures

```
FaceDetectorContext.nativeInit                  (JII)J
FaceDetectorContext.nativeDetectFacesFromFrame  (JLcom/kwai/camerasdk/video/VideoFrame;I)[[B
FaceDetectorContext.nativeDestroy               (JJ)V
Westeros.nativeSetFaceDetectorContext           (JJJI)V        # nativeContext + sensorManager + mode
Westeros.nativeApplyPlugin                      (JJ)V
WesterosGlProcessor.nativeGetDaenerysGlProcessor(J)J
KSJNILib.createModel / createModelPost / YCNNGetConfig2Model / renderModel(Object,byte[])
YcnnPlugin.nativeSetParamInfo                   (Ljava/lang/String;J)V
```

`kuaishou::android::VideoRawFrameFromJni(JNIEnv*, jobject)` @`0xece3c`
(libdaenerys) reads the Java frame field-by-field — `type`, `yuv_format`,
`bitmap`, `data`, `isDirect`, `byteBuffer`, `getByteArray`, `textureId`,
`isOesTexture`, `width`, `height`, `timestamp`, `getSerializedAttributes`,
`originalFrame`, `release` — every access wrapped in
`AttachCurrentThreadIfNeeded`. Output: `VideoRawFrame(MediaDataType,
PixelbufferType, shared_ptr<void>, w, h, ts, attrs)` (CPU) or
`VideoRawFrame(MediaDataType, shared_ptr<render::gl::Texture>, …)` (texture).

### 9.3 Model binding from downloaded paths

`KSRenderObj.YCNNGetConfig2Model(path)` → `KSJNILib.YCNNGetConfig2Model` →
`ycnn::YCNNGetConfig2Model` (header parse) → `createModel` →
`ycnn::YCNNModel::createModel2`; GL path `createModelPost` →
`YCNNModelPost::createModelPost2`. Per-frame provider
(`libykit_core_plugin.so`): `[YlabProvider] YCNNPluginGetModel - key: %s,
model_path: %s` → `magic_ycnn_model_*` → `YCNNResourceConfig{ycnn_model_path,
mmu_model_path, resource_path}` → `CreateModel type=%d, setParamInfo
param_info_=%s` (`native/ylab_createmodel.s`). Network size comes from the
`.model` header (`net_w`, `net_h`, `net_c`, `heatmap`, `valid_threshold` are
`KSModelParamTaskPoint` JSON keys at libykit.so `0x85154a`-`0x851574`), not
from the APK. Normalisation is a **single scalar** (`ykit::NdArray::doNormalize(double)`),
i.e. no per-channel mean — constant `unknown`.

### 9.4 Render → encode

`FacelessPlugin` owns `FaceMagicController`; `Westeros.applyPlugin` →
`nativeApplyPlugin`; `WesterosGlProcessor.toDaenerysProcessor()` exposes the
same native GL processor to the Daenerys pipeline
(`WesterosGlProcessor.nativeGetDaenerysGlProcessor`).
`libwesteros.so` beauty processors: `GuidedFilterProcessor`,
`BilateralSharpGblurProcessor`, `SharpenProcessor`, `SaturationProcessor`,
`NewDenoiseProcessor`, `LocalRegionBlur/FilterProcessor`, `FfccGlProcessor`,
`LowLightDetect/EnhanceProcessor`, `BacklightDetectProcessor` — chained as
`WesterosProcessorGroup<shared_ptr<VideoFrame>>` with `WesterosFBO` output.
Downstream, `libdaenerys.so` splits into `PreviewGlGroup` · `RecordGlGroup` ·
`EncoderGroup` · `LayoutGlGroup` · `CaptureImageGlGroup` · `AudioGroup` →
`VideoEncoderWorkshop` / `MediaCodecVideoEncoder` (“prepare with width %d,
height %d”, “Config bitrate %d, fps %d, width %d, height %d”). Preview and
record share the processor chain and diverge at publish.

### 9.5 Threads

`do.o0` (`FrameProcessThread` proto enum): `kCameraFrameProcessThread=1`,
`kCpuFrameProcessThread=2`, `kGpuFrameProcessThread=3`,
`kEncoderFrameProcessThread=4`; `FrameMonitor` records
`CameraProcessor/CpuProcessor/GpuProcessor/EncodeProcessor.{receive,publish,
process}_time` plus `is_drop`, `drop_frame_position`, `drop_time`.
Handoff: `preprocess::RunloopProcessor::Post/PostAndWait`,
`utils::EasyFrameDropManager`, `DropFrameMonitor`, `preprocess::FrameRateAdapter`.

### 9.6 New unknowns (this patch)

1. `VideoFrame.yuv_format` → `KSY_COLOR_TYPE` mapping inside the native
   `AndroidVideoFrame` wrapper (the two enums are numbered differently).
2. Normalisation scalar / mean-subtraction in `YCNNCropDataMatrix`.
3. Queue depth and drop thresholds in `EasyFrameDropManager`.
4. Whether preview and record execute one shared GL pass or two.
