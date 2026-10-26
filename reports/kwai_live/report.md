# Kwai Live (`com.kwai.kuaishou.video.live`) — AI/ML subsystem

## 1. Identity

| Field | Value | Evidence |
|---|---|---|
| Package | `com.kwai.kuaishou.video.live` | `inventory/inventory.md` |
| Version | `6.0.20.524207` (code `524207`) | `inventory/inventory.md` |
| Label / main activity | Kwai / `com.yxcorp.gifshow.homepage.HomeActivity` | `inventory/inventory.md` |
| SDK | min 21 / target 31 | `inventory/inventory.md` |
| APK size / sha256 | 80 280 485 B / `642f1cbfa4d4cfa4e9424e214f6295bf456cf7d220f74f9ca0bf5ba4743acbe9` | `evidence/variant_diff.txt` |
| Build id | `assets/apk.json`: `{"commit_id":"7996c78114b","task_id":"601962ee-a40c-11ec-a6ff-0c42a139cc08"}` | `evidence/variant_diff.txt` |
| Content | 16 dex (53.9 MB), 80 native libs (114 MB), 263 assets, 4246 configs, **0 model files** | `inventory/inventory.md` |

This APK is one of three preinstalled Kwai whitelabel builds (`kwai_video`,
`kwai_bulldog`, `kwai_live`) that share one code base; see §2.

## 2. Variant diff vs `kwai_video` / `kwai_bulldog` (confirmed)

All three APKs ship **byte-identical AI/vision libraries**. The only native
difference in the whole APK is `libcore.so`:

```
lib/arm64-v8a/libcore.so   live=(30800, crc 2968102410)
                           video=(30800, crc 83064215)
                           bulldog=(30800, crc 813493443)
```

Native library *name sets* are identical (80 each, no live-only or
video-only names). Asset name sets are identical (263 entries; bulldog has
one extra); only three assets differ in content — `assets/apk.json` (build
id), `assets/video_yh_loading_icon.png`, `assets/water_animation.mp4`. The
live build is missing four branding drawables that `kwai_video` has
(`kwai_icon_foreground.webp`, `notification_icon_large.webp`,
`app_launch_logo.webp`, `login_logo.webp`). Everything else that differs is
dex/manifest/resource content (different build commit `7996c78114b` vs
`0c993560a69`).

AI-relevant hashes (identical in all three variants, verified by streaming
sha256 from each APK, `evidence/variant_diff.txt`):

| Library | sha256 |
|---|---|
| `libFaceMagic.so` | `6ef763ecb8a947a943707e2685c7025c2814868e930f3a505712964c228821bb` |
| `libfaceless-plugin.so` | `727d44695b971ae7add4bb931b480b149159316587096fc557a3c3241b9f204c` |
| `libfacedetectcontext.so` | `6238b6122fb3b7520b7f6f36a106553677f814b7162fe5fb4d0124a51675bda0` |
| `libvisionengine.so` | `d18be8987e734bb2448cb7ef9c94bb4c797bf8a293b14dff43879b97d6c6e734` |
| `libksvisionengineplugin.so` | `39644f5eead40131febc35042bba29d9cff48c56d7b6246d663a29e04a1b14ab` |
| `libskwai.so` | `468b928d18313c85cca82c4929ff2cf1c2a5182e88abfb92a6c9dda8f85fbdd9` |
| `libkwaiaudio.so` | `01419e63fbb254921a67320f4ca183a0cc50589315c465143c9cc1c2d8f65a98` |
| `libkwsgmain.so` | `48aae030c926bbabc3d2183298db7f7ffbebc339fe693b39f56e89554d6a8fce` |
| `libtensorflow-lite.so` | `6ef8898d73f366db4ccf2d94738b916a67fba685f5d3941134a79b1f7911167f` |
| `libCGE.so` (graphics/warp engine) | `f6e8ba0b659c228ae0e77c32177b2f61a4718cfa574095d7c9edd8acf6ba9bc5` |

**Consequence:** every AI claim in this report is equally valid for the
`kwai_video` and `kwai_bulldog` builds; what differs between the variants is
which live/record surfaces of the same SDK are reachable from the UI, not
the ML stack.

## 3. Feature map

| Feature | Entry points | Model / runtime | Confidence |
|---|---|---|---|
| Live beauty (skin smoothing, brightness, sharpness, face deform) | `LivePrePushPrettifyPresenter.onEvent(BeautyChangeEvent)`, `PrettifyFragment`, keys `RECORD_LIVE_BEAUTY_FEATURE_SETTINGS`, `live_beautify_enabled`, `last_live_beautify_config`; presets `assets/beauty_list` | `FaceMagicController` → `libfaceless-plugin.so` → `libFaceMagic.so` (`FMBeautifyG2/G3Effect`, shaders in `evidence/shaders/`) | confirmed (wiring), pipeline shaders confirmed |
| Face detection / landmarks | `FaceDetectorContext` (26 JNI), `YcnnPluginUtils.getFaceData` → `YCNNFaces` protobuf | `libfacedetectcontext.so` (`kuaishou::face::YcnnFaceDetector`), YCNN `landmark`/`face_detect` models | confirmed |
| Face / skin segmentation, matting, background blur (bokeh) | `FMBokehDepthEffect`, `nativeRequireBodySegmentation`, `nativeUpdateSegmentation`; strings `ykit_human_matting_cpu/gpu`, `human_matting_param`, `bg_model.bin`, `fg_model.bin` | YCNN `face_seg`, `skin_seg`, `human_matting` | confirmed (API), models not in APK |
| Makeup (blush, lips, brows, lenses, 3D makeup) | `MakeupResource(s)`, `MakeupColor` protobufs, `MakeupDownloadHelper`, `FMMakeupEffectWrapper`, `makeup3duv1.bin`/`makeup3duv2.bin` | `libFaceMagic.so` + downloaded makeup assets | confirmed |
| "MMU" (makeup-to-user / animoji / dynamic face props) | `MmuPlugin` (21 methods), `FMM2UEffectHandler`, `MmuFace` protobuf, `nativeSetFeatureEnabled` | `libFaceMagic.so`; model paths via `YlabModelPathConfig.mmuModelPath` | confirmed (API), model unresolved |
| Body slimming | `BodySlimmingAdjustType` (`WAIST/HIP/BREAST/HEAD/NECK/SHOULDER/LEG/ALL`), `assets/BodySlimming/{Filter28p,Filter40p}/*.params` | `FMGorgeousBodySlim` → `FMLiquifyEffect` → `CGELiquifyFilter` (GPU mesh) | confirmed, math recovered |
| AR stickers / live effects | `LiveMagicEmojiPresenter`, `LiveTextStickerContainer`, `LiveMagicEffectDownloader`, `LiveEffectLocalRender` | `libskwai.so` (`SKwai::VirtualFaceProcessor`), `libFaceMagic.so` AE2 runtime | confirmed |
| Live audio AI (VAD, voice change, KTV reverb, spatial/HRTF) | `AudioProcess$AudioVoiceDetectInterface`, `LiveAudioEffectFragment`, `LivePushSoundEffectFragment` | `libkwaiaudio.so` (Stannis, 295 JNI), `libksaudioprocesslib.so` | confirmed (API) |
| Video quality assessment / smart edit / stabilization | `com.kwai.kve.VisionEngine`, `com.kwai.video.editorsdk2.kve.EditorKve*Task`; `assets/demo_vision_feature_response_v2.txt` (`blocky`, `blur`, `colorfulness`, `defocus`, HDR levels) | `libvisionengine.so` (95 JNI) + `libksvisionengineplugin.so` (21 JNI) | confirmed |
| Voice detection for smart-cut | `com.kwai.video.editorsdk2.kve.EditorKveVoiceDetector` (native `newNativeVoiceDetector`/`detectVoiceNative`), `AICutProject.observableDownloadVoiceDetect`, model key `ks://download_voice_detect_mode` | `libvisionengine.so` (dynamically registered natives + `com/kwai/kve/VoiceDetectorCallback`) + bundled TFLite | inferred (only `.tflite` name in the APK) |

## 4. Live-streaming pipeline (traced)

```
Camera / SurfaceTexture
  └─ westeros GL pipeline (libwesteros.so, 43 JNI: ResourceManager_nativeSetFace3DResourcesDir,
     Westeros_nativeSetFaceDetectorContext)
      ├─ face detection: libfacedetectcontext.so
      │    kuaishou::face::{BaseFaceDetector, YcnnFaceDetector, FaceDetectorWrapper}
      │    JNI com.kwai.camerasdk.face.FaceDetectorContext (26): nativeInit, nativeSetData,
      │        nativeDetectFacesFromFrame, nativeSetFaceDetectConfig, nativePrepare, ...
      │    -> YCNNFaces protobuf (com.kwai.video.westeros.models.YCNNFaces)
      │
      ├─ effect / beauty: libfaceless-plugin.so (44 JNI, class
      │    com.kwai.video.westeros.v2.faceless.FaceMagicController)
      │      nativeInitController, nativeSetEffect, nativeSetEffectEnable,
      │      nativeUpdateEffectControl, nativeSendEffectCommand(Sync|Batch),
      │      nativeSetUserInfo, nativeSetPickedFaceImage, nativeGetRedPacketScore,
      │      nativeSetBeautifyExternalBuiltinPath, nativeSetDynamicRunningKeyandValue,
      │      nativeSetIsValidPortraitCallback, nativeUpdateEffectUsingFramePts
      │        └─ libFaceMagic.so  (FM::FMEffectHandler, 1802 JNI)
      │             ├─ FMBeautifyG2Effect / FMBeautifyG3Effect  (skin smoothing)
      │             ├─ FMBeautifyG3seEffect (segmentation-aware beauty)
      │             ├─ FMGorgeousDeform / FMBeautyDeformInterface (face reshape)
      │             ├─ FMGorgeousBodySlim -> FMLiquifyEffect -> CGELiquifyFilter
      │             ├─ FMMakeupEffectWrapper / FMGorgeousMakeup (makeup, 3D)
      │             ├─ FMBokehDepthEffect (portrait bokeh, depth + seg masks)
      │             ├─ FMHairDyeingEffect / FMHairSofteningEffect / FMWhiteSKinEffect
      │             ├─ FM3DEffect (AR sky / 3D)
      │             └─ FMM2UEffectHandler (MMU: animoji / face props)
      │                   ├─ libCGE.so      CGELiquifyFilter (mesh warp), image filters
      │                   ├─ libskwai.so    SKwai::VirtualFaceProcessor (AR)
      │                   └─ libgorgeous.so face/makeup rendering
      │
      └─ audio: libkwaiaudio.so (Stannis) + libksaudioprocesslib.so
           AudioProcess_nativeAudioVoiceDetectInterface/Process (VAD),
           nativeSetVoiceEffectOption/nativeSetVoiceRoleOption (voice changer),
           SetHrtfModePath / nativeSetKtpModelPath (spatial + karaoke models)

Compositor -> encoder -> LivePushClient (111 methods)
```

Beauty parameters are stored per-user and replayed into the GL pipeline:
`assets/beauty_list` is a 5-entry JSON preset list (`smoothSkin.beauty`,
`smoothSkin.bright`, `faceDeform.*`, `makeup.*`), and the live UI keeps
`last_live_beautify_config` keyed by `RECORD_LIVE_BEAUTY_FEATURE_SETTINGS`
(strings in `classes*.dex`). `LivePrePushPrettifyPresenter` subscribes to
`com.yxcorp.gifshow.product.prettify.beauty.BeautyChangeEvent` — i.e. the
same prettify panel used by recording drives the live preview
(`evidence/class_surfaces.txt`).

## 5. Model acquisition (nothing is bundled)

`inventory` reports **0 model assets**; `tools/carve_models.py` finds no
embedded TFLite/Caffe/DLC blob in any AI library (only the `TFL3` magic
literal inside `libtensorflow-lite.so` itself, and the string
`debug.tflite`). Models and inference backends are fetched at runtime:

1. **Model config**: `GET /rest/o/production/ycnn/models`
   (`YlabModelConfigResponse` → `ModelConfig` → `ModelUrl`, fields
   `useYcnnModelConfig`, `ycnnVersion`) is merged into the protobuf
   `com.kwai.video.westeros.models.YlabModelPathConfig`
   (`ycnnModelPath`, `ycnnModelPathMap`, `mmuModelPath`,
   `dirtyLensProcessorModelPath`) and handed to native code via
   `nativeSetYlabModelPathConfig` / `nativeSetDirtyLensDetectModelPath`
   (`libFaceMagic.so` AE2 resource setters).
2. **Model families** (string constants, `evidence/ycnn_strings.txt`: 37
   `magic_ycnn_model_*` names plus 3 non-model `magic_ycnn_*` resource
   names):
   `magic_ycnn_model_landmark`, `_face_attributes`, `_face_seg`, `_skin_seg`,
   `_head_seg`, `_cloth_seg`, `_hand_seg`, `_nail_seg`, `_matting`,
   `_human_keypoint`, `_human_mesh`, `_human_parsing`, `_humanpose`,
   `_handpose`, `_handpose3d`, `_general_handpose`, `_gesture`, `_hair`,
   `_hair_dir`, `_sky`, `_plane`, `_depth`, `_scene`, `_ar`, `_ar_detect_seg`,
   `_inpainting`, `_inpainting_video`, `_photo3d`, `_photo3dv2`,
   `_style_neo`, `_gan_face2drawing`, `_gan_pks1`, `_general_recog`,
   `_face_similarity`, `_animal_landmarks`, `_dog_landmarks`, `_finger`,
   plus `magic_ycnn_beautify_assets`, `magic_ycnn_face_3d_resource`,
   `magic_ycnn_common_strategy`.
   Java-side model enum (`com.kwai.kscnnrenderlib.YCNNModelInfo`):
   `YCNNModelDetector`, `YCNNModelLandmarks`, `YCNNModelLandmarks3D`,
   `YCNNModelFaceAttribute`, `YCNNModelFaceSeg`, `YCNNModelMakeupSeg`,
   `YCNNModelHumanMatting`, `YCNNModelHumanParsing`, `YCNNModelHumanpose`,
   `YCNNModelHumanKeyPose`, `YCNNModelHeadSeg`, `YCNNModelHair`,
   `YCNNModelHandSeg`, `YCNNModelNailSeg`, `YCNNModelInstanceSeg`,
   `YCNNModelSky`, `YCNNModelPlane`, `YCNNModelDepthCamera`,
   `YCNNModelSalient`, `YCNNModelStyleTransfer`, `YCNNModelHDRNet`, …
3. **Inference runtime is downloaded too**: `YcnnSoLoader.loadNative()` loads
   `westeros` + `ykit_core_plugin`; `loadYcnnExtraSo()` then loads
   `hiai_ir`, `hiai_ir_build`, `hiai`, `hcl`, `hiai_session_impl` (Huawei NPU)
   and `tflite_mtk_static` (MediaTek NeuroPilot, TFLite), and — when
   `CpuInfoChecker.supportSNPE()` — `SNPE`, `ycnn_ext_snpe`,
   `PlatformValidatorShared`, `calculator`, `snpe_dsp_domains_v2(_system)`.
   **None of these `.so` files exist in the APK** (`unzip -l | grep -Ei
   'ycnn|hiai|snpe|tflite'` is empty apart from `libtensorflow-lite.so`).
   Note the `ycnn`/`ycnn2` load attempt plus `SO_LIBRARY_TYPE_YCNN`,
   `ycnn_download_failed`.
4. **Effect packages** (AE2 compositions + makeup + 3D face resources):
   `LiveMagicEffectDownloader` → `MagicDownloadPlugin.downloadMagicFace`
   (record `LiveMagicEffectDownloader.java:332,367`), `MakeupDownloadHelper`,
   and the `YlabModelConfigResponse` CDN URLs. Model directory layout is
   `/Ycnn/modelA` and `/facerecognition/ycnnmodel` with error strings
   “Ycnn model directory is empty / not a directory / not exists”.
5. **Vision-engine (KVE) models** for smart edit/cover/quality run through
   `libvisionengine.so` (95 JNI, `com.kwai.kve.*`) and are likewise not in
   the APK.

Bundled AI-relevant assets (all in `evidence/assets/`):

| Asset | Size | Role |
|---|---|---|
| `assets/beauty_list` | 6 951 B | 5 beauty presets (smoothSkin/faceDeform/makeup JSON) |
| `assets/BodySlimming/Filter28p/*.params` | 8 files, 4 820 B | 28-point body-slim warp ops (All, Breast, Head, HipNeg, HipPost, Neck, Shoulder, Waist) |
| `assets/BodySlimming/Filter40p/*.params` | 7 files, 2 481 B | 40-point body-slim warp ops |
| `assets/fm_builtin_face_mask.jpg` | 19 318 B | FaceMagic builtin face mask texture |
| `assets/faceless_bundle_readme.txt` | 26 B | placeholder |
| `assets/demo_vision_feature_response_v2.txt` | 7 646 B | KVE quality-report payload (blocky/blur/colorfulness/defocus, `version: 5`) |

## 6. TFLite

* `libtensorflow-lite.so` (3 446 232 B) exports the stock JNI surface
  (`Java_org_tensorflow_lite_NativeInterpreterWrapper_*`, `NnApiDelegate_*`,
  47 entry points), contains `XNNPACK`, `NNAPI`, `Flex` and
  `AddCustomOp` symbols, and the version string `2.4.1` in the same rodata
  blob as `FlatBuffers 1.12.0` (**inferred**: TFLite 2.4.1). No Kwai custom
  kernels.
* The Java API (`org.tensorflow.lite.*`, incl. `TensorFlowLite`,
  `NativeInterpreterWrapper`, `nnapi.NnApiDelegate.a`, `Tensor`) lives in
  `classes16.dex` (7 `Lorg/tensorflow/lite` marker hits); the library is
  loaded by name `tensorflow-lite` (string constant), not via `DT_NEEDED`
  (no APK library depends on it — `evidence/needed_map.txt`).
* Only one `.tflite` file name exists in the whole APK:
  `voice_detect_mode.tflite`, next to the download key
  `ks://download_voice_detect_mode` and the version tag
  `voice_detect_mode_v1` (all three string constants live in
  `classes5.dex`). The consumer surface is
  `com.kwai.video.editorsdk2.kve.EditorKveVoiceDetector`, whose native
  methods (`JniInitialize`, `newNativeVoiceDetector`, `detectVoiceNative`,
  `releaseNativeVoiceDetector`) have **no exported `Java_*` symbol** in any
  of the ten analysed libraries, so they are registered dynamically at
  `JNI_OnLoad`; `libvisionengine.so` is the matching library - it looks up
  `com/kwai/kve/VoiceDetectorCallback.getVoiceDetectorResult(VoiceDetectorParam)`
  and keeps the `voice_detector_callback_ref_` global refs.
  `AICutProject.observableDownloadVoiceDetect$1/$2` (classes16.dex) is the
  downloader. **Inferred**: the bundled TFLite runtime exists for this
  smart-cut voice detector; nothing about TFLite is wired into the live
  beauty/effect path.
* Separately, `YcnnSoLoader` pulls `tflite_mtk_static` at runtime — the
  MediaTek NeuroPilot TFLite build used as a *YCNN* backend on MTK SoCs,
  which is unrelated to the bundled 2.4.1 runtime.

## 7. Native analysis

Per-library ELF facts and JNI tables: `native/*.summary.txt`, `native/*.json`,
`evidence/jni_tables.txt` (2 373 JNI entry points across the ten
analysed libraries).

| Library | JNI | Notes |
|---|---:|---|
| `libFaceMagic.so` | 1802 | `FM::` effect engine + full AE2 editor API (SWIG) |
| `libkwaiaudio.so` | 295 | Stannis audio engine (VAD, voice effects, HRTF/KTV model paths) |
| `libvisionengine.so` | 95 | KVE smart edit / cover / stabilization; hosts the voice-detector callbacks (`com/kwai/kve/VoiceDetectorCallback`) |
| `libtensorflow-lite.so` | 47 | stock TFLite 2.4.1 JNI |
| `libfaceless-plugin.so` | 44 | `FaceMagicController` live/record controller |
| `libfacedetectcontext.so` | 26 | `FaceDetectorContext` (YCNN face detector wrapper) |
| `libksvisionengineplugin.so` | 21 | `EditorKve*Task` plugin bridge |

All 14 libraries matched by the tool's AI keyword set (`inventory/inventory.md`),
with their role and dependencies (`evidence/needed_map.txt`):

| Library | Size | Role / evidence |
|---|---:|---|
| `libFaceMagic.so` | 7 722 568 | effect/beauty engine; needs `libgorgeous`, `libCGE`, `libspine`, `libskwai`, `libopencv_world`, `libklsf`, `libprotobuf-lite` |
| `libfaceless-plugin.so` | 1 792 128 | `FaceMagicController` bridge; needs `libykit`, `libwesteros`, `libFaceMagic` |
| `libfacedetectcontext.so` | 191 728 | YCNN face-detector context; needs `libykit`, `libdaenerys`, `libksaudioprocesslib` |
| `libskwai.so` | 6 515 760 | SKwai AR runtime (`VirtualFaceProcessor`) |
| `libvisionengine.so` | 2 108 736 | KVE smart edit/cover/voice detect; needs `libopencv_world`, `libffmpeg` |
| `libksvisionengineplugin.so` | 38 984 | editor-task bridge to KVE |
| `libkwaiaudio.so` | 1 829 120 | Stannis audio engine (VAD/voice FX) |
| `libkwsgmain.so` | 757 000 | 274 exports, **0 JNI**, no AI strings — role unresolved (unknown) |
| `libkwaivoip.so` | 7 666 192 | RTC/VoIP stack (`libaegon`, `libuv`) used by live streaming |
| `libkwaiplayer.so` | 1 350 328 | media player (`libhodor`, `libkwaivpp`, `libffmpeg`, `libyuv`) |
| `libkwaivpp.so` | 5 760 | video pre-processing helper (GL only) |
| `libopencv_world.so` | 7 863 816 | OpenCV, shared by FaceMagic/skwai/visionengine |
| `libwebp_kwai.so` | 198 768 | WebP codec (matched by the `ai` keyword, not an AI component) |
| `libtensorflow-lite.so` | 3 446 232 | TFLite 2.4.1 runtime (see §6) |

Effect inventory recovered from `libFaceMagic.so` export names (4 537
exports) — `FMBeautifyG2Effect`, `FMBeautifyG3Effect`, `FMBeautifyG3seEffect`,
`FMGorgeousBeautify`, `FMGorgeousDeform`, `FMGorgeousBodySlim`,
`FMLiquifyEffect`, `FMLiquifyHandler`, `FMMakeupEffectWrapper`,
`FMGorgeousMakeup`, `FMBokehDepthEffect`, `FMHairDyeingEffect`,
`FMHairSofteningEffect`, `FMWhiteSKinEffect`, `FM3DEffect`,
`FMM2UEffectHandler`, `FMLookupFilter`, `FMBlingBlingFilter`,
`FMBeautifyEffectInterface::{setBeautifyLipsIntensity, setBeautifyFaceShadow,
…}`, `FMDataExchanger`, `FMEffectAsyncProcessor`.

`libskwai.so` model strings: `instance_model`, `hair_model`,
`human_key_point_model`, `human_mesh_model`, `animal_face_model`,
`ar_hand_pose_model`, `face_input`, `face_model_params`, `instance_model`,
`Global.MMU`, `Push.MMU`, `kuaishou.westeros.model`.

### 7.1 Body-slim warp math (recovered, `native/libCGE_liquify.c`)

`FM::FMLiquifyEffect::forward/restore/bloat/wrinkle` are thin trampolines to
`CGE::CGELiquifyFilter` (`native/liquify_*.s`). Ghidra recovered the transfer
functions (32 functions, 152 s):

```
forwardDeformMesh(start, end, ax, ay, radius, intensity):   # 0x232d4c
    d = |v*aspect - start|
    if d <= radius:
        t = 1 - d/radius;  w = t*t*(3-2t)*intensity
        v += ((end - start)/aspect) * w

bloatMeshWithPoint(p, ax, ay, radius, intensity):   # 0x2332a4  v += ((v*aspect - p)/aspect)*w
wrinkleMeshWithPoint(p, ...):                      # 0x2333bc  v += ((p - v*aspect)/aspect)*w
restoreMeshWithPoint(p, ...):                      # 0x233140  v = rest*w + v*(1-w)
restoreMeshWithIntensity(k):                       # 0x232698  v = v*(1-k) + rest*k
initWithMesh(min, max, cell):  grid mesh, cell size clamped to [0.001, 0.2]
undo()/redo():                 vertex-buffer snapshots
```

The `assets/BodySlimming/*.params` op types map 1:1:
`forward → forwardDeformMesh`, `restore → restoreMeshWithPoint`,
`bloatWrinkle/bloatWrinkle1 → bloatMeshWithPoint|wrinkleMeshWithPoint`
(sign of `intensity` selects bloat vs wrinkle); `radius` is the falloff
radius, `useGradient`/`curve` select the mesh gradient variant
(`pushLeftDeformMesh` has an extra parameter and is not used by the shipped
parameter files).

## 8. Reimplementation (JAX)

`reimpl/` implements the two **weight-free** stages with the APK's own
parameters:

* `beauty.py` — the G3 beautify blend, transcribed from the embedded GLSL
  (`evidence/shaders/facemagic_{61815c,617dba,61b3c0,61cfd4}.glsl`):
  3×3 mean/alpha pass, mean+variance pass, then teeth-LUT / bright-curve /
  clarity / sharpen-lite / skin-smooth blend / sharpen / global LUT.
* `liquify.py` — the body-slim warp using the recovered CGE transfer
  functions applied to a dense displacement field, driven by
  `data/BodySlimming/{Filter28p,Filter40p}/*.params` and a 63-point
  landmark vector.
* `pipeline.py` — `init_params()` / `apply(params, frame, ctx)`; composes
  beauty → body-slim → LUT.

Run: `python verify.py` (from `reimpl/`) → **10/10 checks pass**, including a
JAX-vs-NumPy cross-check of the blend (`max_abs_err 7.45e-08`), identity at
zero intensity, strength monotonicity, warp determinism and bounded
displacement; output shapes for 32×32, 128×96, 256×144. See `reimpl/verify.txt`.

Status: **runs_unverified** — the shader and warp math are recovered and
cross-checked, but there is no device/GPU reference frame to compare
against, and the learned inputs (skin/soften masks, landmark positions,
effect packages) are not in the APK, so all model-backed stages are
`ARCHITECTURE_ONLY`.

## 9. Confidence summary

* **confirmed**: library set/hashes, ELF metadata, JNI surfaces, absence of
  bundled models, asset inventory, the stock TFLite JNI surface (47 entry
  points, NNAPI/XNNPACK/Flex, no custom ops), YCNN model taxonomy and
  download endpoints, beauty/body parameter files, CGE warp math, GLSL
  beauty programs.
* **inferred**: the TFLite runtime serves the AICut/`EditorKveVoiceDetector`
  voice detector (`voice_detect_mode.tflite`) and the binary is TFLite 2.4.1; the
  `beauty_list` presets map to `setSoften/setBright` intensities 0..100 →
  0..1; `textureShift[i]` offset ordering (vertex shader not embedded).
* **unknown**: YCNN network architectures (never shipped), which exact
  model backs each `magic_ycnn_model_*` name, the 63-point body-landmark (indices 0..62)
  layout, the AE2 effect-package format details, the mesh cell size FM
  passes to `initWithMesh`, and what Java component instantiates the TFLite
  `Interpreter` at runtime.

## 10. Reproduction

```bash
APK_ROOT="/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system"
APK="$APK_ROOT/cust/app/customized/partner-com.kwai.kuaishou.video.live_155/partner-com.kwai.kuaishou.video.live_155.apk"
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python; WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
$PY $WS/tools/apk_inventory.py "$APK" --out $WS/reports/kwai_live/inventory \
    --extract-models --extract-libs --max-file-mb 50 --max-total-mb 90
$PY $WS/tools/dex_find.py "$APK" --regex 'com\.kwai\.video\.westeros\.' --classes-only
$PY $WS/tools/strings_dex.py "$APK" --regex 'ycnn|tflite' --unique --format plain
$PY $WS/tools/elf_info.py <lib>.so --json $WS/reports/kwai_live/native/<lib>.so.json
$PY $WS/tools/carve_models.py <lib>.so
flock $WS/logs/ghidra.lock -c "$WS/tools/ghidra_decompile.sh libCGE.so native/libCGE_liquify.c 'Liquify'"
cd $WS/reports/kwai_live/reimpl && $PY verify.py
```

## 11. Live push pipeline (camera + mic → effects → encoder)

**Full contract:** [`PIPELINE.md`](PIPELINE.md) (pipelines A/B/C, 9 sections
each). Line-level calls: `evidence/live_push_trace.md`. Native strings:
`evidence/kwaiaudio_strings.txt`. New jadx sources:
`sources/classes/live/` (23 classes).

### 11.1 Video: capture → effect → encoder (confirmed)

```
LivePushActivity (livePushConfig / liveBeauty / liveFilter / streamType)
  └─ prepush presenter g → CameraView → VideoSurfaceView            (preview only)
       └─ com.kwai.camerasdk.Daenerys  → libdaenerys.so (138 JNI, sha256 3b0758bf…)
            ├─ CameraControllerImpl  (Camera2 → Camera1 one-shot fallback)
            ├─ nativeSetFaceDetectorContext  ← FaceDetectorContext (libfacedetectcontext.so)
            ├─ GL pre-processor group ← Westeros (westeros plugin graph)
            │     └─ FaceMagicController GL processor (libfaceless-plugin.so)
            │           └─ libFaceMagic.so effects (beauty/makeup/AR/MMU)
            ├─ nativeDrawFrame → EGL display surface  (preview output)
            └─ nativeSetMediaCallback(fps,maxW,maxH,downSample,MediaCallback)
                  → Arya.inputRawVideo(ByteBuffer, VideoFrameAttribute)
                        → nativeInputRawVideoByteBuffer (libkwaivoip.so)
                              → live-stream encoder → RTMP / RTC
```

* Capture config comes from `DaenerysConfigBuilder.defaultCaptureConfigBuilder()`:
  30 fps target, YUV output (`kCameraOutputDataTypeYuv = 0`), size pair
  `y0()=720 / v0()=1280`, audio 44100 Hz / 1 ch / 16 bit
  (`sources/classes/live/com.kwai.camerasdk.DaenerysConfigBuilder.java:23-27`,
  builder→field map in `do.u.java`). `LivePushClient` (ksmedialivekit) then
  calls `daenerys.b0(config.s(), config.u(), config.v(), mediaCallback)`
  (`sources/classes/live/com.kwai.video.ksmedialivekit.LivePushClient.java:408`).
* Frame contract into Arya: `yuv_format, width, height, timestamp,
  rotation=attributes.f().m(), csp=attributes.b()`
  (`…LivePushClient.java:87`; `Arya$AryaConfig.java:2288`).
* Encoder params are native only: `nativeSetLiveStreamVideoEncodeParam(w,h,fps,
  maxBitrate,resumeOnRtcStop)` and `nativeSetLiveStreamRtmpUrl` (`Arya$AryaConfig.java:1443`).
  `AryaConfig` defaults: 360×640 @15 fps, min-fps-for-adapt 10, key frame every
  4 s, min bitrate 200 kbps, `videoEnableHwEnc=false`.
* Face detection and beauty run **before** the encoder tap, inside the Daenerys
  GL thread (`GlPreProcessor.onDraw(VideoFrame,int)`), so the encoded stream and
  the preview show the same effected frame; the encoder only receives the YUV
  readback.

### 11.2 Audio: mic → Stannis → push (confirmed API, sample rate unknown)

* Arya's device layer is `AryaAudioEngineProxyImp`, which proxies to **Stannis**
  = `libkwaiaudio.so` (`stannis version r4.8.0.9.0.4d6622197`).
* Contract: 16-bit PCM, **10 ms** frames — `byteLength = sr*ch*2/100`; other
  lengths are dropped (`Stannis.java:322-331`). Channels 2 for live
  (`AryaAudioEngineProxyImp.java:1159-1161`). `enableAudioVad = true` and
  `enableAudioPreProcess = true` by default (`Arya$AryaConfig.java:392,409`).
* 3A: device or soft AEC on 10 ms packets (`ringbuf_10ms_pkgs`), then
  NS/AGC (`nativeAgcProcess`, `nativeNsProcess`), then VAD
  (`SetEnableDeepVad`, `AudioVadInfoCollector`, 500 ms hang-over).
* Voice FX enums (all in `AudioProcess`): post effect 0-5, reverb 0-18,
  voice change `kVeo*` 0-15 — pushed through `nativeDspSetParameters`,
  `nativeAudioPostEffectSetOption`, `nativeSetVoiceEffectOption`.
* Voice/background/music detection: `AudioVoiceDetectInterface(sr, ch, modelPath)`
  → `process(byte[], len)` → `{result_available, backgroud_prob, speech_prob,
  music_prob}` (JNI at `libkwaiaudio.so` 0x12a49c / 0x12a56c, disassembly in
  `native/audio_voicedetect_*.s`).
* A/V sync is native: the audio engine clock, `LiveStreamBgmOffsetCb`
  (`nativeSetLiveStreamBgmOffsetCb`) and `VocalBgmSyncWorkshop`
  (`first frame offset:%d`) keep BGM/vocal aligned with the video stream.
* Sample rate is a runtime value: 44100 appears in the camera capture config,
  48000 in the camera SDK's `AudioControllerImpl` (mono, 10 ms) and in
  `Stannis.startAudioInnerCap`, and the TX rate is only *logged*
  (`kAudioTxLiveStreamSampleRate = %d`). Marked unknown for the live TX stream.

### 11.3 Threading / lifecycle (confirmed)

* Effect + preview + media callback all run on Daenerys' native render thread;
  Java GlPreProcessors have `post(Runnable)` → `nativePostRunnableToGlThread`.
* Push lifecycle is a state machine (`INIT/READY/PUSH_SUCCESS/RETRYING/ERROR/
  PUSH_STOPPED`); `startPush` requires READY and a live long-connection,
  `stopPush` sends `Arya.stopLiveStream("User Hangup")`.
* Reconnect: `startLive(...).retryWhen(...)` — 2 s timer, bounded counter,
  stop on `1016013014`, fatal on `1016013015`; media status `RETRYING(3)`
  triggers `onRestartPush()`. Background pauses the push (`s0`), foreground
  resumes (`t0`).
* Drops: counted natively, surfaced via `QosInfo.getDroppedVideoFrames()`;
  `VideoFrame.canDrop()` marks droppable frames.
* Fallback: Camera2→Camera1 reopen once, `RetryStartPreviewHelper`, black-frame
  checker, `GPUPerformance.Level` buckets (0/50/100/200/500), and optional
  YCNN backends (HiAI / MTK TFLite / SNPE) that are **not** in the APK.

### 11.4 Effect/model binding vs the recorded path (confirmed)

Same download catalogue, same JNI resource setters
(`ResourceManager.nativeSetYlabModelPathConfig`, `nativeSetFace3DResourcesDir`,
`nativeSetDirtyLensDetectModelPath`), same `libFaceMagic.so` runtime. The
difference is the per-frame driver: live binds GL textures through the Daenerys
pipeline and hands YUV frames to Arya; the recorded/editor path drives
`MediaRecorderImpl` + `MediaCodecEncoder` (surface input) and has a
bitmap-based `YcnnPluginUtils.getFaceData(Bitmap,…)` variant. Nothing learned is
bundled in either path (0 model assets).

### 11.5 New unknowns from this patch

* Live TX sample rate (44100 vs 48000) and the exact resampler chain for the
  live stream out.
* Enumeration/units of `VideoFrameAttribute.rotation` and `.csp` (YUV matrix).
* Whether the live path uses the camera SDK audio capture (`AudioControllerImpl`)
  at all, or exclusively Stannis/Oboe (Java evidence points to Stannis only).
* Depth of the Arya video input queue and its drop policy before encoding.
