# PIPELINE — kwai_bulldog (`com.kwai.bulldog` 6.0.20.524206)

Scope: the camera/video data path of this variant, from the frame producer to the
renderer/encoder, with the JNI contracts and the model-acquisition fallbacks.
Every row is tagged **confirmed** / **inferred** / **unknown** with the artifact +
offset/class/method that supports it.

Artifacts referenced:

| short | artifact |
|---|---|
| `dae` | `lib/arm64-v8a/libdaenerys.so` (2,580,368 B) — camera/encoder core, `VideoRawFrameFromJni`, protobuf `kuaishou.model.*` |
| `fdc` | `lib/arm64-v8a/libfacedetectcontext.so` (191,728 B) — `FaceDetectorContext` JNI |
| `ykit` | `lib/arm64-v8a/libykit.so` (10,292,232 B) — ycnn runtime + `KSJNILib` JNI |
| `flp` | `lib/arm64-v8a/libfaceless-plugin.so` (1,792,128 B) — `FaceMagicController`/`FacelessPlugin` JNI + `FMGlProcessor` |
| `fm` | `lib/arm64-v8a/libFaceMagic.so` (7,722,568 B) — effect renderer |
| `sources/classes/…` | jadx `--single-class` output (this report) |

Native evidence: `native/daenerys_VideoRawFrameFromJni.s`,
`native/facedetect_detectFaces.s`, `native/facedetect_nativeSetData.s`,
`native/ykit_renderModel.s`, `native/ykit_updateModelOut.s`,
`evidence/gap_pipeline/*.txt`.

---

## 1. Data sources / triggers

| trigger | binding | confidence |
|---|---|---|
| camera capture / record / live | `android.permission.CAMERA`, `RECORD_AUDIO` in the manifest (`inventory/inventory.json`); launcher `com.yxcorp.gifshow.homepage.HomeActivity` | confirmed |
| per camera frame | `Java_com_kwai_camerasdk_videoCapture_DaenerysFrameObserver_nativeOnVideoFrameCaptured` @ `dae` 0xe18d8 (432 B) → Java `VideoFrame`; audio twin `…nativeOnAudioFrameCaptured` 0xe1a88 (560 B) | confirmed |
| camera session control | `com.kwai.camerasdk.videoCapture.CameraSession`, `CameraController{,Impl}` — defined in `classes15.dex` | confirmed |
| live-push consumption of a frame | `com.kwai.video.ksmedialivekit.LivePushClient$1.onVideoFrame(Lcom/kwai/camerasdk/video/VideoFrame;)V` (`classes8.dex`) | confirmed |
| face detection request | `com.kwai.camerasdk.face.FaceDetectorContext.detectFacesFromFrame(VideoFrame, l0)` → native @ `fdc` 0x1a19c | confirmed (declaration) / **no Java caller in this APK** (see §8) |
| effect start | `com.kwai.video.westeros.v2.faceless.FacelessPlugin.<init>` → `new FaceMagicController(nativePlugin, true)` → `nativeInitController` @ `flp` 0x3d49c | confirmed |
| per-frame effect | `FaceMagicController` extends `WesterosGlProcessor`; native `FMGlProcessor::OnDraw()` / `update()` on the render thread (`flp` rodata 0x155d…) | confirmed |
| teardown | `FaceDetectorContext.dispose()` → `nativeDestroy` @ `fdc` 0x18b10; `FaceMagicController.release()` → `nativeDestroyController` @ `flp` 0x3d840; `FMGlProcessor::OnRenderThreadDestroy()` (`flp` rodata) | confirmed |

Lifecycle in detail (`sources/classes/com.kwai.camerasdk.face.FaceDetectorContext.java`):

```
FaceDetectorContext(ctx, type=kYcnnFaceDetect, initType=Auto)   [classes15]
  nativeInitSensorManager()                       fdc 0x183b4
  nativeInit(sensorMgr, type, initType)           fdc 0x184b0   (1632 B)
  new SensorUtils(ctx, sensorMgr, ignore)
  setData(kYcnnFaceDetect, modelPath)             -> nativeSetData fdc 0x18e28   (gated, §7)
  prepareVideoDetector() / prepareImageDetector() -> nativePrepare fdc 0x1af34
  detectFacesFromFrame(frame, kVideoDetector)     -> nativeDetectFacesFromFrame fdc 0x1a19c
  dispose()                                       -> nativeDestroy fdc 0x18b10
```

`l0` = `FaceDetectorName` {kVideoDetector 0, kImageDetector 1};
`i0` = `FaceDetectInitType` {Auto 0, Sync 1, Async 2}; `j0` = `FaceDetectMode`
{Normal 0, Tracking 1, TrackingFast 3, TrackingRobust 4, DetectTrack 5, TrackingRect 6};
`k0` = `FaceDetectType` {kYcnnFaceDetect 0} — all from `sources/classes/mo.{l0,i0,j0,k0}.java`.

Confidence: **confirmed** (all four enums + JNI table read from the artifacts).

---

## 2. Transport and format

### 2.1 The frame object

`com.kwai.camerasdk.video.VideoFrame` is **not defined in any DEX of this APK**
(`dex_find.py` reports `defined:false` in classes3/8/15/16). The full Java class was
recovered from the sibling `partner-com.kwai.video_33.apk` (same version 6.0.20.524206,
`classes15.dex`, 21 methods) and cross-checked field-by-field against the JNI lookups
inside `VideoRawFrameFromJni`; every field matches:

| Java field (video variant) | JNI name/sig read by `dae` | how used |
|---|---|---|
| `int type` | `"type"` `I` (0x1f327b) | dispatch, see below |
| `int yuv_format` | `"yuv_format"` `I` (0x1e08a8) | chroma layout |
| `Bitmap bitmap` | `"bitmap"` `Landroid/graphics/Bitmap;` (0x1e091d/0x1e1e35) | type 4 |
| `FrameBuffer data` | `"data"` `Lcom/kwai/camerasdk/videoCapture/FrameBuffer;` (0x1e0948/0x1e094d) | YUV/RGBA bytes |
| — `FrameBuffer.isDirect()Z` | `"isDirect"` `()Z` (0x1e09a7) | direct vs heap |
| — `FrameBuffer.byteBuffer` | `"byteBuffer"` `Ljava/nio/ByteBuffer;` (0x1e09b0/0x1e09d1) | direct path |
| — `FrameBuffer.getByteArray()[B` | (0x1e09bb) | heap path |
| — `FrameBuffer.release()V` | (0x1e09c8) | both paths |
| `int textureId`, `boolean isOesTexture` | (0x1e08df, 0x1e08e9) | type 2 |
| `Runnable textureNotUsedRunnable` | (0x1e08b3/0x1e08ca) | returned to Java when texture was not consumed |
| `int width`, `int height` | (0x1f3400, 0x1f340e) | geometry |
| `long timestamp` | `"timestamp"` `J` (0x1e08f6) | frame clock |
| `byte[] getSerializedAttributes()` | `"getSerializedAttributes" "()[B"` (0x1e0900/0x1e0918) | `kuaishou.model.VideoFrameAttributes` |
| `VideoFrame originalFrame` | `"originalFrame"` (0x1e0876/0x1e079a) | provenance chain |

**Frame-type dispatch** (`dae` 0xece3c, size 6048; branch code at 0xececc-0xed0a0):
`VideoFrame` constants (video variant, identical values implied by the native compares):
`None 0, YUV 1, TEXTURE 2, RGBA 3, BITMAP 4`.

| `type` | path in `VideoRawFrameFromJni` | resulting `kuaishou::MediaDataType` |
|---|---|---|
| 1 YUV | `FrameBuffer` bytes / ByteArray (0xed420) | 35 (table @0x1e0f74 entry 0) |
| 2 TEXTURE | `textureId` + `isOesTexture` + `Runnable` (0xed0a0) | texture ctor (no numeric id observed) |
| 3 RGBA | `FrameBuffer` bytes (0xed07c → 0xed420) | 38 |
| 4 BITMAP | `Bitmap` (0xecee4) | bitmap ctor |
| other | texture path | — |

Confidence: **confirmed** for the dispatch and the field list; **inferred** for the
*names* of MediaDataType 35/36/38 (only the ids are reachable statically — 36 is in the
same table but unreachable through this dispatch).

### 2.2 Geometry, rotation, cropping

`VideoFrame` (video variant, `evidence/gap_pipeline/video_variant/…VideoFrame.java`):

```java
private boolean widthHeightReversed() { return this.attributes.f().m() % 180 == 90; }  // Transform.rotation
int croppedWidth()  { w = t.crop_width_frac  ? t.crop_width_frac*width  : width;  return (w+3) & -4; }
int croppedHeight() { h = t.crop_height_frac ? t.crop_height_frac*height : height; return (h+3) & -4; }
int transformedWidth()  { return widthHeightReversed() ? croppedHeight() : croppedWidth(); }
int transformedHeight() { return widthHeightReversed() ? croppedWidth()  : croppedHeight(); }
```

* rotation is carried **in the frame metadata**, not as a separate int:
  `kuaishou.model.VideoFrameAttributes.transform` (`Transform` = {mirror, rotation,
  crop_x_frac, crop_y_frac, crop_width_frac, crop_height_frac}; generated symbols at
  `dae` 0x3624e/0x36280/0x3630a/0x3633f/0x3641b/0x36454).
* width/height are the **raw** buffer dimensions; the effective (rotated, cropped)
  size is derived as above and rounded to a multiple of 4.
* the effect chain is told the size explicitly:
  `FaceMagicController.nativeSetInitialVideoFrameSize(long,int,int)` @ `flp` 0x3e17c
  (`FMGlProcessor::SetInitialVideoFrameSize() width:%d height:%d`, `flp` rodata).
* device/camera orientation are separate config keys `device_orientation` (0x1f32a7) and
  `camera_orientation` (0x1f32ba) in `dae`; `kuaishou::SensorManager::GetOrientation90`
  is exported by `dae` and imported by `fdc` (used together with the Java-side
  `SensorUtils.nativeSensorUpdateRotation/Orientation/Quaternion` @ `fdc`
  0x190e4/0x1932c/0x18f58).

Confidence: **confirmed** for the field layout and the formulas; **inferred** that the
detector consumes `Transform.rotation` directly (the call is inside a stripped
`FaceDetectorContextInterface` implementation; only the `SensorManager` import is visible).

### 2.3 Audio

`DaenerysFrameObserver.nativeOnAudioFrameCaptured` (`dae` 0xe1a88) and
`AudioRawFrame` symbols (`dae` 0x24537…) show PCM audio frames travelling the same
media-source graph. Sample rate/format constants are not in the APK strings —
**unknown** (not needed for the vision path).

### 2.4 Threads and queues

| stage | thread | evidence |
|---|---|---|
| camera → `VideoFrame` | daenerys media-source thread, then Java callback | `DaenerysFrameObserver` JNI + `AndroidVideoSource` symbols in `dae` |
| face detect | caller thread of `detectFacesFromFrame`, serialized by `FaceDetectorContext.lock` (`synchronized` in Java) | `FaceDetectorContext.java` |
| detector init | `BaseFaceDetector init %p. is_async_init = %d` + internal `Runloop` | `fdc` rodata 0x2408a, 0x23fb8 |
| effect render | GL/render thread (`FMGlProcessor::OnDraw()`, `OnRenderThreadDestroy()`, `SetEnabled()`) | `flp` rodata 0x1558ed… |
| async effect worker | `FM::FMEffectAsyncProcessor` (`flp` 0x80a0, `fm` 0x384d9) | symbol |
| encode | native encoder thread: `MediaRecorderImpl.nativeStartRecording` 0xe46dc, `nativeCaptureOneVideoFrame` 0xe4ef0, `nativeSetTargetFps` 0xe513c | `dae` |
| drop policy | `VideoFrame.canDrop() = !attributes.undroppable`; `FMGlProcessor` has a frame-skip guard (`"Politicians detected, skip the current frame rendering"`, `flp` rodata) | confirmed / inferred (guard trigger condition unknown) |

Queue depth / back-pressure: **unknown** (inside `libdaenerys` media graph; no numeric
constants in rodata).

---

## 3. Preprocessing

Order inside `libfacedetectcontext` → `libykit` (the part that is observable):

1. **Frame unwrap** — `VideoRawFrameFromJni` (see §2.1) produces `shared_ptr<VideoRawFrame>`
   holding either a texture, a bitmap or a CPU plane.
2. **CPU plane copy** — for YUV/RGBA frames `VideoRawFrame::nv12or21_copy*` /
   `i420_copy*` / `i420_copy_ignore_cache` (`dae` 0x1f459…0x1f990) normalise the layout.
   `VideoRawFrame::NeedReconvertYUVForColorSpace` (0x1f79b) handles colour-space mismatch.
3. **Crop/convert into the net input** — `ycnn` accepts only
   `grey` / `nv21` / `nv12` for grey crops and `rgb/bgr/rgb-norm/bgr-norm/yuv0` for
   float inputs (`ykit` rodata: "only support nv21/nv12" 0x83af40,
   "only support grey/nv21/nv12 for grey crop" 0x83ad59). Conversion +
   crop is `ycnn::YCNNCropDataMatrix` (referenced by `renderModel`).
4. **Normalisation** — `inputMeanVals` per model config (`ykit` config key), applied after
   resize; net resolution from `net_w`/`net_h`.
5. **Orientation** — the Java side passes `rotate`/`flipHor`/`normRotate`/`normFlipHor`/
   `normFlipVer` in `YCNNModelIn`; the detector also has `SensorManager` orientation.

Where it lives: `fdc` 0x1a19c (`nativeDetectFacesFromFrame`) →
`ykit` `Java_…_renderModel` 0xc8360 → `ycnn::YCNNModel::runModelBuffer`.

Whether this exactly matches the model contract: **inferred** — the model files are
downloaded (`net_w`, `net_h`, `inputMeanVals`, `input_resize` come from the per-model
config, not from the APK), so the concrete numbers are **unknown**.

---

### Cross-check against the shared sibling notes
Every item in `reports/_shared_kwai_sdk.md` §3 (`YCNNModelIn` fields, `YCNNModelConfig`,
`KSLandmarksParam` defaults, model-type ids) is reproduced here from this APK's own
artifacts (`sources/classes/com.kwai.kscnnrenderlib.YCNNModelInfo.java` +
`native/ykit_renderModel.s`) and matches. Two bulldog-specific corrections/additions:
`faces` and `param_info` are **not** read by `renderModel`, and the pixel buffer is the
second JNI argument, not a field.

## 4. Model invocation

### 4.1 Runtime and session creation

* Engine: `ycnnnet` v3/v4 in `libykit.so` (CPU NEON / OpenCL; TFLite and SNPE shims for
  MTK APU). `KSJNILib.createModel(Object)` @ `ykit` 0xc81e8,
  `createModelPost` 0xc82a4, `YCNNGetConfig2Model` 0xce798.
* Config object read by `createModel`: `mKSJNIRenderObj` (J), `model_type`,
  `model_sub_type`, `model_files`, `model_path` (`ykit` rodata 0x833c1d-0x833c90).
* Model path always comes from a downloaded resource directory (§7), never from `assets/`.

### 4.2 Per-frame input binding — `KSJNILib.renderModel(Object, byte[])`

Resolved from `native/ykit_renderModel.s` (every `GetFieldID` string):

```
0xc83c0  GetField  KSRenderObj.mKSJNIRenderObj            J
0xc8428  GetField  KSRenderObj.mJNIRenderInfo             Ljava/lang/String;   (written back)
0xc8450  GetField  KSRenderObj.mModelIn                   LYCNNModelInfo$YCNNModelIn;
0xc84f4  GetField  YCNNModelIn.colorType                  I
0xc8534  GetField  YCNNModelIn.rotate                     I
0xc8574  GetField  YCNNModelIn.flipHor                    I
0xc85b4  GetField  YCNNModelIn.width                      I
0xc85f4  GetField  YCNNModelIn.height                     I
0xc8634  GetField  YCNNModelIn.frame_time                 J
0xc8674  GetField  YCNNModelIn.single_image               Z
0xc86bc  GetField  YCNNModelIn.normOut                    I
0xc86fc  GetField  YCNNModelIn.normRotate                 I
0xc873c  GetField  YCNNModelIn.normFlipHor                I
0xc877c  GetField  YCNNModelIn.normFlipVer                I
```

* The **pixels are the second JNI argument** (`byte[]`), not a Java field:
  `KSRenderObj.runModelBuffer(in)` calls `mKSJNILib.renderModel(this, in.data_0)`
  (`sources/classes/…KSRenderObj.java`).
* `YCNNModelIn.faces` and `YCNNModelIn.param_info` are **not** read by `renderModel`
  (no `GetFieldID` for them) — confirmed by absence in the resolved string table.
* Java defaults (`YCNNModelInfo.YCNNModelIn`): `colorType=3`, `rotate=0`, `flipHor=0`,
  `single_image=false`, `norm* = 0`, `data_0=null`.
* Detector knobs (`YCNNModelInfo.KSLandmarksParam`): `maxFaceNum=4`, `minFaceSize=50`,
  `detectIntervals=5`, `detectMode=4` (SuperFast), `useImageTrack=2` (TRACK_128),
  `useAdjustMode=1`, `useSubEye=1`, `useSubMouth=1`, `useCorrectMode=1`,
  `useEyeAutoSwitch=1`, `useDetect360=1`, `stickFaceCropRect=true`, `stickTrackRect=true`.
* Model type ids (`YCNNModelInfo`): `Landmarks 5`, `Detector 21`, `FaceAttribute 10`,
  `FaceSeg 20`, `Matting 0`, … (full list in the decompiled class).

Cadence: the detector keeps a tracking interval (`detectIntervals=5`) and re-detects every
N frames unless forced; **confirmed** for the default value, **inferred** for enforcement
site (inside `ycnn`).

---

## 5. Output postprocessing

### 5.1 ycnn → Java (`KSJNILib.updateModelOut`, `ykit` 0xc8a54, 13,300 B)

Field names resolved from `native/ykit_updateModelOut.s`:

| Java target | members written |
|---|---|
| `KSRenderInfo.mJNIRenderInfo` | status string |
| `YCNNModelInfo$KSFaceDetectOut mFaceDetectOut` | `faces` → `KSFaceInfo{index, left, top, width, height, confidence, yaw, pitch, roll, pos → KSPtInfo{xPos, yPos, zPos, valid}}` |
| `…$KSMattingOut mMattingOut` | `mask` (`KSImage{width,height,channel,buffer,range}`), `contours` |
| `…$KSHeadSegOut mHeadSegOut` | `seg` (KSImage) |
| `…$KSHumanPoseOut mHumanposeOut` | `poses` → `KSPoseInfo{index, pose_type, pos…}` |
| `…$KSInstanceSegOut mInstanceSegOut` | `segs` |
| `…$KSSceneClassOut mSceneOut` | `infos`, `conf` |
| `…$KSTaskDetectOut mTaskDetectOut` | `detects` → `KSTaskDetectInfo{index,left,top,width,height,confidence,type}` |
| `…$KSGeneralRecogOut mGeneralRecogOut` | `out_json` |
| `…$KSInpaintingOut mInpaintingOut` | `out` (KSImage) |

`updateModelOut` is called under `synchronized(this)` from `KSRenderObj.runModelBuffer`
(Java) — i.e. one inference → one synchronised output readback.

### 5.2 Camera detector → Java (`fdc` 0x1a19c)

```
ycnn outputs
  → kuaishou::model::FaceData  (index/rect/original_rect/pose/landmark/confidence/gender)
  → FaceData::SerializeToString            fdc 0x1a6f8
  → NewObjectArray JNI #172 (env+0x560) @ 0x1a30c; per element NewByteArray JNI #176
    (env+0x580) @ 0x1a71c, SetByteArrayRegion JNI #208 (env+0x680) @ 0x1a75c,
    SetObjectArrayElement JNI #174 (env+0x570) @ 0x1a390
→ Java FaceDetectorContext.detectFacesFromFrame: mo.g0.f(byte[])  (parseFrom)
```

`kuaishou.model.FaceData` wire layout (confirmed twice: Java `mo.g0.writeTo` and native
`_ZN8kuaishou5model8FaceData24SerializeWithCachedSizes…` @ `dae` 0x179520):
`1 float, 2 float, 3 float (confidence), 4 Rect, 5 FacePose, 6 FaceLandmark, 7 Rect
(original_rect), 8 Gender enum`. Detail: `evidence/gap_pipeline/proto_shapes.txt`.

### 5.3 Coordinate transforms

| transform | where | confidence |
|---|---|---|
| image → view (landmarks, Java-facing `KSFaceInfo.pos`) | `ycnn::TransLandmarksImageToView(YCNNRenderContext*, KSFaceInfo*)` (`ykit` .dynstr symbol 0x2f0c0) | confirmed (symbol) |
| rect intersection / union | `ycnn::KSRectfInter` 0x2dec3, `KSRectfCombine` 0x2e047 (.dynstr symbols) | confirmed |
| rotated/cropped geometry | `VideoFrame.transformedWidth/Height()` (Java) from `Transform.rotation` + crop fracs | confirmed |
| mirrored landmark set | native produces `flipped_point_bytes` / `flipped_extra_points` in `FaceLandmark` before serialization | confirmed (schema), inferred (trigger) |
| camera-SDK face data → effect face data | `kuaishou::westeros::FaceData{rect_left/top/right/bottom, pose_yaw/pitch/roll, points, point_at, index}` accessors in `flp` (0x8642-0xcc7f) | confirmed (symbols), transform maths unknown |

Temporal smoothing: `KSLandmarksParam.useSmoothMode/useSmoothCropRect/stick*` +
`KSLANDMARKS_SMOOTH_TYPE1/2` constants (`sources/classes/…YCNNModelInfo.java`). The
smoothing filter itself is compiled — **unknown**.

---

## 6. Consumer / action

```
FMGlProcessor (flp) ── owns ──► FM::FMEffectHandler / FM3DEffect / FMBeautifyEffectInterface
        │                         (fm; FMDataExchanger::setTransFaceData 0x342ff,
        │                          updateFaceData 0x3685c, setPickingImageTexture 0x34928;
        │                          all .dynstr symbol-name offsets)
        │
        ├─ GL draw of the effect (CGE / libskwai / libgorgeous; libGLESv2 + libEGL in DT_NEEDED)
        ├─ segmentation/matting masks consumed by FaceMagic (KSImage buffers)
        └─ output surface → encoder (MediaRecorderImpl native*, dae) or live-push sink
```

* Effect selection: `FaceMagicController.setEffectAtSlot(EffectResource, EffectSlot)`
  → `nativeSetEffect(long, byte[], int, int)` @ `flp` 0x3d858; per-slot enable via
  `nativeSetEffectEnable` 0x3db48; protobuf commands via
  `nativeSendEffectCommand`/`…Sync`/`…Batch` 0x3dc8c/0x3dd9c/0x3deac.
* Builtin model dirs: `nativeSetExternalBuiltinPath` 0x3e228,
  `nativeSetBeautifyExternalBuiltinPath` 0x3e564 (static), plus
  `AE2 Resource.ycnnModelPath` / `ycnnModelPathMap` / `mmuModelPath` /
  `dirtyLensProcessorModelPath` (see report §4.3).
* User-visible change: warped/smoothed/makeup/deform face, segmentation-driven
  background effects, avatar (MMU) overlays; the raw frame is never modified — the GL
  processor is inserted into the westeros render graph, so both preview and encoder see
  the processed output.
* Callbacks into Java that steer the action: `onCallCaptureTexture`, expression events,
  bokeh/body-slimming/portrait status (`FaceMagicController` inner listeners),
  `publishEventAsync(WESTEROS_EVENT_MODEL_MISSING)`.

---

## 7. Failure and fallback

| situation | behaviour | evidence | confidence |
|---|---|---|---|
| model dir missing at camera start | `ModelResourceUtil.e(key)` → `<cache>/.magic_model/magic_model/<key>` or `<cache>/magic_effect_resource/<key>`; download + unzip + `check.json` MD5 list | `sources/classes/com.yxcorp.gifshow.util.magic.{b,c}.java`, `.../resource/model/c.java` | confirmed |
| model path not delivered to the detector | `FaceDetectorContext.setData` is a **no-op** unless `com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.f23915h` is true; the same guard gates `enableLoadAssetFunc` | `FaceDetectorContext.java` | confirmed |
| model file absent at native init | `[YcnnFaceDetector] Ycnn model missing %s` → `onModelMissingCallback(int, String)` → Java `ModelMissingListener.onModelMissing(type, path)` | `fdc` rodata 0x244be / 0x249fd (`(ILjava/lang/String;)V`) | confirmed |
| model bytes source | native calls the **Java** `com.kwai.camerasdk.utils.ResourceLoader.loadData(String, boolean)[B` (also `loadImageToRGBA`, `loadText`) through `AndroidResourceLoader`; `file://asset/` is accepted | `fdc` rodata 0x239a9, 0x23ac4, 0x23cbd, 0x243bd | confirmed |
| vendor-specific fallback detector | `BaseFaceDetector` logs "we are using god feeling parameters for face detection" for a path that should be unreachable | `fdc` rodata 0x23fc0 | inferred (default-parameter detector) |
| ykit model missing / partial load | status JSON `{"missing_models":[...]}` / `{"finish_load_models":[...]}`; `JNI Get model_path %s` | report §4.1 (`ykit`) | confirmed |
| effect package invalid | `FaceMagicController.isResourceValid(dir) = new File(dir,"params.txt").exists()`; failure event `WESTEROS_EVENT_MODEL_MISSING`; Java `FaceMagicLoadEffectFailedListener`; native log `SetEffect() Effect Config Error , effect load fail` | `FaceMagicController.java`, `flp` rodata 0x155ba7 (Effect Config Error), 0x155bf5 (Effect is empty) | confirmed |
| device cannot run the model (backend) | ykit probes `can_use_tflite` / `can_use_snpe` and falls back to CPU NEON; `*_opencl/_snpe/_mtkapu` model variants | report §4.1 | confirmed |
| detection disabled | `nativeSetFaceDetectEnabled`, `nativeSetOverrideFaceDetectMode`, `nativeResetVideoDetector` (`fdc` 0x19b60/0x1a9c0/0x1b218) | JNI table | confirmed |

---

## 8. Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| Bulldog camera frames are produced natively by daenerys and marshalled through the `VideoFrame` JNI contract | confirmed | `dae` 0xece3c + field table §2.1 | — |
| `VideoFrame` type 1=YUV, 2=TEXTURE, 3=RGBA, 4=BITMAP | confirmed (constants from sibling), consistent with native dispatch | video variant `VideoFrame.java` + `dae` 0xececc | runtime dump |
| Rotation lives in `VideoFrameAttributes.transform.rotation` | confirmed | proto symbols + `widthHeightReversed()` | — |
| Rotation is applied inside the detector using `SensorManager` + attributes | inferred | `GetOrientation90` import in `fdc` | disassembly of the stripped `FaceDetectIntent` impl |
| `renderModel` reads only 11 scalar fields + `byte[]`; `faces`/`param_info` unused | confirmed | full `GetFieldID` list `native/ykit_renderModel.s` | — |
| `FaceData` tags 1-2 (float) names | unknown | Java has no accessor; native kIndex/kTrackId constants are unused by the serializer | a downloaded detector model + runtime tap |
| Concurrency: detect under Java lock, effects on GL thread, detector init async | confirmed | Java `synchronized`, `fdc` `is_async_init`, `flp` `OnRenderThreadDestroy` | — |
| Model download/validation and `check.json` MD5 | confirmed | report §4.2 sources | — |
| Which `KSModel*` file backs `kYcnnFaceDetect` | unknown | mapping inside stripped `YCNNGetConfig2Model` | runtime file trace |
| Bulldog has **no Java caller** of `detectFacesFromFrame`, and `VideoFrame` is not defined in its DEX (unlike `kwai_video`) | confirmed | `dex_find.py` on both APKs | a runtime module trace |

Additional open items:
* the class that supplies `com.kwai.camerasdk.video.VideoFrame` at runtime in bulldog
  (dynamic module? `libcore.so` variant?) — **unknown**.
* MediaDataType ids 35/36/38 names — **unknown**.
* queue depth / drop counters in the daenerys media graph — **unknown**.

---

## 9. Reimplementation mapping

| stage | JAX/module artefact | contract | status |
|---|---|---|---|
| frame decode (YUV → RGB) | `reimpl/pipeline.py::nv21_to_rgb` | NV21/NV12, BT.601 limited range, `(H,W)` → `(H,W,3) float32/255` | implemented, unverified against libyuv |
| geometry (rotation/crop) | `reimpl/pipeline.py::preprocess` | crop fractions + 90° rotation + bilinear resize to `net_w×net_h` | implemented from `VideoFrame`/`Transform` semantics |
| detector | `reimpl/pipeline.py::init_params/apply/decode` | heatmap + 2·68 landmark head, `inputMeanVals` subtraction | **ARCHITECTURE_ONLY** — no weights in APK; head layout inferred |
| landmark coordinate transform | not implemented | `TransLandmarksImageToView` (image → view) | architecture unknown (stripped) |
| effect render | not implemented | GL/colour pipeline in `libFaceMagic`/`libgorgeous` | out of scope for JAX (renderer, no NN on this path) |
| KVE statistics | `reimpl/pipeline.py::kve_features` | sharpness/colourfulness/low-light/entropy/junk/scene-change | implemented from feature names |

Run: `/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reports/kwai_bulldog/reimpl/pipeline.py --net 128 --width 640 --height 480`
(see `reimpl/README.md`, `reimpl/verify.txt`). No numeric parity is claimed: the real
graphs are inside downloaded `.model` files.
