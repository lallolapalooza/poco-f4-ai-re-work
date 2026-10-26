# com.kwai.video — data-pipeline integration contract

Scope: the camera→face→beauty→encode path of `com.kwai.video` 6.0.20.524206
(APK `partner-com.kwai.video_33.apk`, sha256
`a0050c589bf6d748e2c721c86e5e0f5e254e594aa88822fe304810af9611f98e`).
Shared native facts (model absence, YCNN I/O contract, beauty math) are in
`../_shared_kwai_sdk.md`; this file adds the **pixel transport, JNI signatures,
threading and encoder handoff**.

Every row is `confirmed` (read from code/artifact), `inferred` (consistent
evidence, not directly observed) or `unknown` (say what was tried).

Evidence roots: `evidence/07_camera_jni_trace.md` (JNI/buffer trace),
`evidence/08_daenerys_westeros_strings.txt` (offset→string dump of
libdaenerys / libwesteros / libykit_core_plugin), `native/*.s`,
`sources/classes/*.java`.

## 0. End-to-end trace (one frame)

```
[1] Camera2 ImageReader.OnImageAvailable              camerakit.d$e:175  (CameraKit handler thread)
      Image.getPlanes() -> Y/u/v  --pixelStride-->  NV21 | NV12 | I420   videoCapture.i.f
      de-stride (row copy) -> packed 1.5*w*h ByteBuffer (direct, pooled)  FrameBuffer
[2] VideoFrame.fromCpuFrame(fb,w,h,yuv_format,tsMs)
      .withTransform(o1.p().f(rotation).e(mirror).build())                camerakit.d:207
      attributes: kBt601FullRange, kFrameSourcePreview, isFrontCamera
      FrameMonitor.e(kCameraFrameProcessThread, ts)                       camerakit.d:201
[3] CameraControllerImpl$b.onVideoFrameCaptured -> publishMediaFrame      CameraControllerImpl:155
      (stale-session frames dropped via textureNotUsedRunnable)
[4] kuaishou::android::VideoRawFrameFromJni(env, VideoFrame)             libdaenerys 0xece3c
      -> kuaishou::VideoRawFrame(MediaDataType, PixelbufferType, buf, w,h,ts, attrs)
[5] face detect: BaseFaceDetector* handed to native via
      Westeros.nativeSetFaceDetectorContext(J,J,J,I)                     libwesteros 0x3feb0
      or Java FaceDetectorContext.nativeDetectFacesFromFrame(...)[[B     libfacedetectcontext 0x1a19c
      -> ycnn::YCNNCropDataMatrix (colour convert/crop/resize)            libykit 0x1fa76c
      -> ycnn::YCNNModel::runModelBuffer -> NEON fp16 / OpenCL / MTK APU
[6] outputs: KSFaceDetectOut (rect, yaw/pitch/roll, pos[]), KSMattingOut/KSHeadSegOut masks
      attached as kuaishou::westeros::VideoFrame::face_data()            libykit_core_plugin 0x7b32
[7] Westeros processor group on the GL thread:
      YcnnPlugin providers -> beauty/AR chain (GuidedFilter, BilateralSharpGblur,
      Sharpen, Saturation, NewDenoise, Ffcc, LowLight...) + FaceMagicController
      -> WesterosFBO texture                                          libwesteros
[8] Daenerys groups: PreviewGlGroup | RecordGlGroup -> EncoderGroup
      -> VideoEncoderWorkshop -> MediaCodecVideoEncoder
      "[MediaCodecVideoEncoder] prepare with width %d, height %d"      libdaenerys 0x1f0735
[9] FrameMonitor per stage: CameraProcessor/CpuProcessor/GpuProcessor/EncodeProcessor
      .{receive,publish,process}_time, is_drop, drop_frame_position     libdaenerys 0x1f622f-0x1f63e4
```

Threads: camera (CameraKit handler) → cpu (`kCpuFrameProcessThread`) →
gpu/render (`kGpuFrameProcessThread`) → encoder (`kEncoderFrameProcessThread`),
enum `do.o0` (`sources/classes/do.o0.java`).

---

## 1. Data sources / triggers

| # | trigger | class / method | lifecycle | conf |
|---|---|---|---|---|
| 1 | Camera2 preview frames (`ImageReader.OnImageAvailableListener`) | `com.kwai.camerasdk.videoCapture.cameras.camerakit.d$e.onImageAvailable(ImageReader)` (CameraKitSession) | per frame, on the CameraKit handler thread | confirmed |
| 2 | legacy Camera1 path | same `CameraSession.CameraDataListener.onVideoFrameCaptured(CameraSession, VideoFrame)` contract; `CameraControllerImpl` switches `cameraApiVersion` to `kAndroidCamera1` on CameraKit auth failure (`CameraControllerImpl.java:882-886`, `setUseYuvOutputForCamera2TakePicture`) | per frame | confirmed (selector) / inferred (Camera1 producer class is obfuscated) |
| 3 | GL texture producers (effects, editor export) | `VideoFrame.fromTexture(int textureId, boolean isOes, int w, int h, long ts)` | per frame | confirmed |
| 4 | still/image mode | `VideoFrame.fromBitmap(...)`, `FaceDetectorContext.prepareImageDetector()` → `nativePrepare(ctx, kImageDetector)` | per image | confirmed |
| 5 | face detect request | `FaceDetectorContext.detectFacesFromFrame(VideoFrame, l0)` → `nativeDetectFacesFromFrame(J, VideoFrame, int)`; **also** driven natively via `Westeros.setFaceDetectorContext(ctx, mode)` → `nativeSetFaceDetectorContext(JJJI)V` | per frame (native), per call (Java) | confirmed |
| 6 | per-frame YCNN providers | `YcnnPlugin.nativeStartProvideSalientData / …GeneralRecogData / …FaceAttributeData` (libykit_core_plugin.so) | started/stopped from UI | confirmed |
| 7 | render | `FacelessPlugin` → `FaceMagicController` (a `WesterosGlProcessor`) registered through `Westeros.applyPlugin(plugin)` → `nativeApplyPlugin(J,J)` | per frame on the GL thread | confirmed |

Teardown: `FaceDetectorContext.dispose()` (`nativeDestroy(J,J)`),
`Westeros.dispose(Runnable)` → `nativeUnsetDaenerys` + `nativeDestroyWesteros`,
`FaceMagicController.release()` → `nativeDestroyController(J)`.

## 2. Transport and format

### 2.1 Video, Java → native

| item | value | conf |
|---|---|---|
| container | `VideoFrame` (`@Keep`, classes15.dex) | confirmed |
| CPU buffer | `FrameBuffer{ ByteBuffer byteBuffer; boolean isNativeBuffer }` — **direct** pool buffer (`ByteBuffer.allocateDirect`, native pool variant `nativeCreateByteBuffer`) | confirmed |
| `VideoFrame.type` | `1 YUV`, `2 TEXTURE`, `3 RGBA`, `4 BITMAP`, `0 None` | confirmed |
| `yuv_format` | `0 I420/YUV420P`, `1 NV12`, `2 NV21`, `3 RGBA32` | confirmed |
| size | `1.5·w·h` for YUV (`ImageFormat.getBitsPerPixel(YUV_420_888)=12`), `4·w·h` for RGBA | confirmed |
| stride | **always tight** (`stride == width`); the reader de-strides with `ByteBufferUtils.a(dst,dstRow,plane,srcRowStride,rows)` when `Image.getPlanes()[0].getRowStride() != width`; a size mismatch recreates the pool (`YUVFrameBufferReader.e`) | confirmed |
| colour space | `attributes.i(s.kBt601FullRange)` on the camera path | confirmed |
| rotation / mirror | `withTransform(o1.p().f(rotation).e(mirror).build())`; rotation from session `h0()`, mirror from `com.kwai.camerasdk.videoCapture.c` front-camera flags | confirmed |
| crop | `croppedWidth()/croppedHeight()` = `(n>0?n:w [+ ratio]) & ~3` — 4-byte aligned; `transformedWidth/Height` swap when `rotation % 180 == 90` | confirmed |
| timestamp | `long timestamp` in **ms** (`TimeUnit.NANOSECONDS.toMillis`); `attributes` also carries the raw sensor timestamp | confirmed |
| facing | `attributes.n(isFrontCamera)` | confirmed |

Selector that produces `yuv_format` (`com.kwai.camerasdk.videoCapture.i`, "YUVFrameBufferReader.java"):

```
plane[2].pixelStride == 2  → NV21 (yuv_format=2)   // Y plane + interleaved VU
plane[1].pixelStride == 2  → NV12 (yuv_format=1)   // Y plane + interleaved UV
otherwise                  → I420 (yuv_format=0)   // three separate planes
```

### 2.2 Video, native frame (`libdaenerys.so`)

`kuaishou::android::VideoRawFrameFromJni(JNIEnv*, jobject)` @`0xece3c`
(6048 B) converts `VideoFrame` → `kuaishou::VideoRawFrame` and is the **single**
bridge used by every native consumer:

* texture branch → `VideoRawFrame(MediaDataType, shared_ptr<render::gl::Texture>,
  w, h, ts, VideoFrameAttributes&&)` @`0xed384`
* CPU branch → `VideoRawFrame(MediaDataType, PixelbufferType, shared_ptr<void>,
  w, h, ts, const VideoFrameAttributes&)` (`.dynsym` #1145, `0xf6a78`)
* `VideoFrameAttributes` is the Java protobuf (`getSerializedAttributes()[B`,
  `ParseFromArray` @`0xed32c`) → `kuaishou.model.VideoFrameAttributes`
* `FrameBuffer.release()V` is called before returning (pool refcount)

Field-by-field table: `evidence/07_camera_jni_trace.md` §1.

### 2.3 Video, native → YCNN

`YCNNModelIn` (`sources/classes/com.kwai.kscnnrenderlib.YCNNModelInfo.java:278`):

```java
byte[] data_0;            // raw frame bytes (NV21 by default)
int colorType = 3;        // YCNNComm.KSY_COLOR_NV21
int rotate, flipHor; long frame_time;
boolean single_image; int width, height;
LinkedList<KSRectf> faces;           // crop rects
int normOut, normRotate, normFlipHor, normFlipVer;
String param_info;
```

YCNN colour enum (`YCNNComm`): `0 BGRA, 1 RGBA, 2 NV12, 3 NV21, 4 YUV420P,
5 Y, 6 YUVA444` — **different numbering** from `VideoFrame.yuv_format`
(0=I420, 1=NV12, 2=NV21, 3=RGBA). Conversion happens inside the engine
(`ycnn::YCNNCropDataMatrix`, libykit.so `0x1fa76c`: `(uint8* src0, src1, src2,
int w, int h, int stride, KSY_COLOR_TYPE, KSY_COLOR_RANGE_TYPE, uint8* dst,
int dw, int dh, int dc, KSCNNInputColorType, ksrect, float, bool,
KSCNNCropType)`) — confirmed (signature), the exact mapping of
`VideoFrame.yuv_format` → `KSY_COLOR_TYPE` is `inferred` (same semantic order,
but the enum constants are numeric in the binary).

### 2.4 Thread / queue

| thread | role | evidence | conf |
|---|---|---|---|
| `CameraKitSession` handler (camera) | `ImageReader.OnImageAvailableListener` → `VideoFrame` | camerakit.d `ImageReader`/`Handler` fields | confirmed |
| `kCameraFrameProcessThread` | camera frame publish; `FrameMonitor.e(o0.kCameraFrameProcessThread, ts)` | `do.o0` enum, camerakit.d:201 | confirmed |
| `kCpuFrameProcessThread` | `preprocess::CpuThread`, `YuvLoadProcessor`, CPU pre-processors | `do.o0`, libdaenerys `CpuThread`/`YuvLoadProcessor` | confirmed |
| `kGpuFrameProcessThread` (render thread) | GL processor groups, `RenderThreadWorkshop`, `AndroidRenderThread2` | `do.o0`, libdaenerys `RenderThreadWorkshop`, `fbo` strings | confirmed |
| `kEncoderFrameProcessThread` | encoder workshop | `do.o0`, `[VideoEncoderWorkshop] Prepare width = %d, height = %d` | confirmed |
| YCNN inference | runs inside the plugin's provider; `AttachCurrentThreadIfNeeded` around every JNI field read, `pthread_create` in libykit_core_plugin.so | `native/fdc_detectFaces.s:0x1a2b4`, evidence/08 | confirmed |

Queue/drop policy: `preprocess::RunloopProcessor` (`Post`, `PostAndWait`),
`utils::EasyFrameDropManager<VideoRawFrame>`, `AbstractFrameDropManager`,
`DropFrameMonitor`, `preprocess::FrameRateAdapter`, `FrameMonitor` CSV keys
`is_drop`, `drop_frame_position`, `drop_time`, `drop_avg`, `drop_max`
(evidence/08, libdaenerys). Frames are `shared_ptr<VideoRawFrame>`; `VideoFrame.canDrop()`
returns `!attributes.e()`. **Queue depth is a tunable** → `unknown` (no
constant in rodata; `DaenerysConfig` protobuf fields only).

## 3. Preprocessing

Order on the camera path (confirmed unless noted):

1. **De-interleave / de-stride** `Image` → packed I420|NV12|NV21
   (`videoCapture.i.f`, see §2.1). *No scaling here.*
2. **Dimension fix-up**: `attributes.k(cropRatio)` and
   `fo.f.g(frame, scaleFactor, size, delta)` (`camerakit.d:232`);
   4-byte alignment is enforced by `croppedWidth()/croppedHeight()` `& ~3`.
   The exact semantics of `fo.f.g` are `inferred` (it is handed the scale
   factor and `width - previewWidth`).
3. **Transform metadata only** (`rotation`, `mirror` stored in attributes, not
   applied to pixels) — the rotation is applied later on the GPU.
4. **Face crop**: `KSLandmarksParam{maxFaceNum=4, minFaceSize=50,
   detectIntervals=5, detectMode=4 SuperFast, useImageTrack=2 TRACK_128}`
   (shared notes §3; `YCNNModelInfo`).
5. **Engine-side**: `ycnn::YCNNCropDataMatrix` colour-convert + crop + resize to
   the model's `net_w`/`net_h` (`net_w`,`net_h`,`net_c`,`heatmap`,
   `valid_threshold` are JSON keys of `ycnn::KSModelParamTaskPoint`,
   libykit.so rodata `0x85154a`-`0x851574`).
6. **Normalisation**: `ykit::NdArray::doNormalize(double)` — a **single scalar**
   (libykit.so `0x127b3c`), i.e. no per-channel mean subtraction. The exact
   scalar is not stored as a readable constant → `unknown` (tried: string scan
   for `mean_`, `pixel_mean`, `0.0078125`, `127.5`, `1.0/255` in libykit.so →
   0 hits).

For the beauty stage the preprocessing is the *same frame* already uploaded as
a GL texture; no CPU copy.

## 4. Model invocation

### 4.1 Acquisition → path (confirmed, full chain)

```
resource pack (com.yxcorp.gifshow.util.resource.model.a/c)
  → app-private dir (…/Ycnn/modelA, /.magic_model/magic_model)
  → path string only crosses JNI
      AE2WesterosWrapperCreator$Resource.ycnnModelPath / ycnnModelPathMap
      westeros.ResourceManager.nativeSetPath / nativeSetYlabModelPathConfig
      YcnnPlugin.setAttrLocalPath / nativeSetParamInfo(String,long)
      aiedit.pbs.AIEditParamGan.{model_path, model_dir}
```

### 4.2 Path → engine (confirmed)

| step | Java | JNI symbol | native impl |
|---|---|---|---|
| parse header | `KSRenderObj.YCNNGetConfig2Model(path)` → sets `model_path` | `KSJNILib.YCNNGetConfig2Model(String, YCNNModelConfig)` | `ycnn::YCNNGetConfig2Model(const string&, YCNNModelConfig2*)` |
| create CPU model | `KSRenderObj.createCPUModel()` | `KSJNILib.createModel(Object)` (`libykit.so @0xc81e8`, 188 B) | `ycnn::YCNNModel::createModel2(const YCNNModelConfig2&)` |
| create **GPU/post** model | `KSRenderObj.createGPUModel()` | `KSJNILib.createModelPost(Object)` | `ycnn::YCNNModelPost::createModelPost2(...)` |
| per-frame | `KSRenderObj.runModelBuffer(in)` | `renderModel(Object, byte[] data_0)` then `updateModelOut(Object)` | `YCNNModel::runModelBuffer(YCNNModelIn*)` |
| GL output | `KSRenderObj.runModelPostGL(in)` | `renderModelPost(Object)` | `YCNNModelPost::runModelPostGL(...)` |
| params | `YcnnPlugin.nativeSetParamInfo(String json, long h)` | libykit_core_plugin.so `@0x16640` | `YcnnPlugin::SetParamInfo` → `YlabProvider::CreateModel` → `YCNNModelPost::setParamInfo` |

`YCNNModelConfig` (`YCNNModelInfo.java:267`) = `{model_type, model_sub_type,
model_files[], model_path, param, model_type_string, model_type_param,
assetManager}`. `createModel` receives `Object` = the `KSRenderObj` itself, so
the native side reads its fields back through JNI.

Per-frame provider (`libykit_core_plugin.so`, strings at
`evidence/08_daenerys_westeros_strings.txt`, offsets `0x7647b`-`0x76c62`):

```
[YlabProvider] YCNNPluginGetModel - key: %s, model_path: %s      (0x7647b)
[YlabProvider] CreateModel type=%d, setParamInfo param_info_=%s  (0x767ca)
[YlabProvider] Ycnn model load finish %s                          (0x76b20)
[YlabProvider] Ycnn model missing %s                              (0x76b49)
```

Key format `magic_ycnn_model_{matting,gesture,hair,finger,humanpose,landmark,
face_attributes,depth,face_seg,head_seg,human_parsing,human_mesh,inpainting,
general_recog,photo3d,gan_live_photo,ar_detect_seg,…}` (~35 keys, 0x760a5-0x7645c),
resolved to `kuaishou.westeros.model.YCNNResourceConfig{ycnn_model_path,
mmu_model_path, resource_path}`.
`YlabProvider::CreateModel` disassembly: `native/ylab_createmodel.s`
(→ `setSystemInfo`, `setParamInfo`, `setUploadStatsCallBack`).

Binding: the preprocessed YUV/NV21 byte buffer is passed by **`byte[]`**
(`YCNNModelIn.data_0`), or by **GL texture id** for the post model
(`YCNNModelPostIn.texID`); no direct `ByteBuffer` address crosses JNI for the
YCNN path (KSRenderObj copies into `KSImage.buffer` only for image inputs).

## 5. Output postprocessing

| stage | output struct | fields consumed | conf |
|---|---|---|---|
| face detect | `KSFaceDetectOut{faces: KSFaceInfo{index,left,top,width,height,confidence,yaw,pitch,roll,pos: KSPtInfo{xPos,yPos,zPos,valid}[]}}` | rect + euler angles + per-point validity | confirmed |
| landmarks | `KSLandmarksParam` → `KSFaceDetectOut.pos` (model-defined count) | 2D/3D points | confirmed (struct) / unknown (count) |
| matting/seg | `KSMattingOut{mask: KSImage, contours}`, `KSHeadSegOut{seg}`, `KSFaceSeg*`, `KSHumanParsing*` | `KSImage{width,height,channel,ByteBuffer,range}` | confirmed |
| attributes | `YcnnFaceAttributeResult`, `KSSceneClassOut`, `KSGeneralRecogOut{out_json}` | JSON string / typed fields | confirmed |
| Java view | `do.g0` (`FaceInfo` protobuf) built from `byte[]` via `g0.f(byte[])` | rect, rotation, landmarks | confirmed |

Coordinate transforms back to preview space:

* detector returns coordinates in the **frame it was given** (bytes of
  `VideoFrame.data`, i.e. pre-rotation, width/height = buffer dims); the
  `rotate`/`flipHor` fields of `YCNNModelIn` (and `normRotate`/`normFlipHor`/
  `normFlipVer` for the normalised output) tell the engine which transform the
  consumer wants applied. → confirmed (fields) / inferred (semantics).
* the renderer applies the *same* `Transform` it stored on the frame
  (`o1.p().f(rotation).e(mirror)`), so detector rects and GL quad share one
  space; `VideoFrame.transformedWidth/Height` (swap at 90°/270°) is the size
  used for the landing rectangle. → inferred (consistent with the consumer
  fields and the `widthHeightReversed()==rotation%180==90` rule).
* temporal smoothing: `points_stabilizer.cpp`
  (`/Users/wizard/build_cache/YKIT/ykit3/.../detector/points_stabilizer.cpp`,
  libykit.so rodata 0x8516c1) and `estimateRigidTransform` (0x8516aa) — the
  landmark stabiliser exists; parameters `unknown`.

## 6. Consumer / action

```
YcnnPlugin (Salient / GeneralRecog / FaceAttribute)   libykit_core_plugin.so
   │  YlabProvider / YlabPostProvider  →  westeros::VideoFrame::face_data()
   ▼
Westeros processor group  (WesterosProcessorGroup<shared_ptr<VideoFrame>>)
   ├─ BeautyProcessor chain (libwesteros.so, confirmed class names):
   │    GuidedFilterProcessor, BilateralSharpGblurProcessor, SharpenProcessor,
   │    SaturationProcessor, NewDenoiseProcessor, LocalRegionBlur/Filter,
   │    FfccGlProcessor, LowLightDetect/Enhance, BacklightDetect, DrawSymbol
   ├─ FaceMagicController (libFaceMagic.so) — AR + beauty, consumes
   │    landmarks via FM.* JNI (updateLandmarks3DData, updateSegmentation,
   │    FMBeautifyG3Effect, FMGorgeousMakeup, FMBokehDepthEffect, …)
   └─ GlProcessorImpl → WesterosFBO (texture_id/framebuffer_id, BindTexture)
        → published frame
   ▼
Daenerys pipeline groups (libdaenerys.so rodata 0x1f84d9-0x1f8525):
   PreviewGlGroup · EncoderGroup · RecordGlGroup · LayoutGlGroup ·
   CaptureImageGlGroup · AudioGroup
   ▼
VideoEncoderWorkshop → MediaCodecVideoEncoder
   "[MediaCodecVideoEncoder] prepare with width %d, height %d"
   "[MediaCodecVideoEncoder] Config bitrate %d, fps %d, width %d, height %d"
```

Preview vs recorded:

* **preview** = `PreviewGlGroup` → `NativeRenderThread`/`NativeRenderThread2`
  display context (`nativeBindDisplayContext`, `nativeDrawFrame`,
  `nativeSetDisplayLayout`); `RecordGlGroup` and `EncoderGroup` are separate
  downstream groups, so the beauty/effect graph is shared but the preview quad
  and the encoder surface are distinct consumers (`confirmed`: separate group
  names + separate JNI; `inferred`: they execute the same processor chain once
  per frame and branch at publish time).
* recording also feeds `VideoSourceConverter`/`I420CallbackSink` when a YUV
  consumer is registered (`[I420CallbackSink] render_thread w_h_reversed
  width = %d`), and `YuvLoadProcessor` loads CPU frames back to textures.
* `CaptureImageGlGroup` is the still-capture branch;
  `Westeros.nativeSetShouldApplyImageModeForImage`,
  `SharpenProcessor.SetEnableProcessTakePictureFrame` gate photo-only effects.

Deterministic change to user-visible data: every enabled effect/beauty module
is applied to the *recorded* frames too (same group chain), which is why
`RecordGlGroup` exists separately from `PreviewGlGroup`.

## 7. Failure and fallback

| condition | behaviour | conf |
|---|---|---|
| YCNN model absent | `YlabProvider` logs `Ycnn model missing %s` and reports through the `(YCNNErrorType, string)` callback registered in `YlabProvider::initConfig`; `{"missing_models": [...], "finish_load_models": [...]}` status JSON from libykit | confirmed |
| camera-SDK face detector without model | `FaceDetectorContext.ModelMissingListener.onModelMissing(int, String)` (Java callback from native) | confirmed |
| CameraKit auth failure | `CameraControllerImpl` re-opens with `kAndroidCamera1` (`CAMERA_UNIT_AUTHENTICATION_FAILED`, :882-886) | confirmed |
| frame buffer size mismatch | pool re-created, warning logged, frame still delivered | confirmed |
| timestamp drift > 1000 ms (CameraKit) | `f23666m=false`, `onReportCameraFunctionFailed(f0.CAMERA_KIT_ERROR, delta)` | confirmed |
| `ImageReader.acquireNextImage()` returns null / read exception | `d0()`, `stop()`, `CameraSession.a.b(ERROR, CAMERA_2_PREVIEW_EXCEPTION_FAILED)` | confirmed |
| stale session frame | `videoFrame.textureNotUsedRunnable.run()` and drop (`CameraControllerImpl$b`) | confirmed |
| effect load failure | `FaceMagicLoadEffectFailedListener.onLoadFileError(String,int)`, `FaceMagicListener.onSetEffectFailed(EffectResource, EffectSlot, EffectError)` | confirmed |
| GL/environment unsafe | `FaceMagicEnvironmentUnsafeListener.onFaceMagicEnvironmentUnsafe(int)` | confirmed |
| download during use | models are loaded per `YlabProvider` creation; `[YlabPostProvider] - checkflag changed, reload ycnn model` (0x736d5) reloads in place when the flag changes | confirmed (string) / inferred (atomicity) |

## 8. Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| `VideoFrame` is the only frame currency; `type`/`yuv_format` as tabled | confirmed | `sources/classes/com.kwai.camerasdk.video.VideoFrame.java`, `videoCapture.i.java` | — |
| Native reads the Java frame field-by-field with `AttachCurrentThreadIfNeeded` | confirmed | `native/daenerys_videorawframe.s`, `native/daenerys_videorawframe.strings.txt` | — |
| Detector input is packed NV21 by default (`colorType=3`) | confirmed (default) / inferred (per-frame value) | `YCNNModelInfo.java:280`, `YCNNComm` | a per-frame log or a hook on `YCNNModelIn` |
| `yuv_format → KSY_COLOR_TYPE` mapping | inferred | enum orders differ; `YCNNCropDataMatrix` takes both | decompile `kuaishou::android::AndroidVideoFrame` ctor |
| YCNN normalisation is a single scalar, no mean subtraction | inferred | `ykit::NdArray::doNormalize(double)` only | disassemble `doNormalize` + `YCNNCropDataMatrix` call site |
| `net_w/net_h` come from the `.model` header JSON (`KSModelParam…`) | inferred | `net_w/net_h/net_c/heatmap/valid_threshold` keys, `YCNNGetConfig2Model` | a real `.model` file (not in the APK) |
| 4 logical frame threads (camera/cpu/gpu/encoder) | confirmed | `do.o0` enum + `CameraProcessor.*`/`GpuProcessor.*`/`EncodeProcessor.*` stats keys | — |
| Preview and record run the same processor chain, split at publish | inferred | group names + JNI split | trace `Pipeline::InitThreadAndRunloop` |
| Encoder = MediaCodec via `VideoEncoderWorkshop` | confirmed | `[MediaCodecVideoEncoder] …`, `kuaishou::recorder::MediaRecorder` | — |
| Landmark count / layout | unknown | `KSLandmarksParam` selects behaviour | model file |
| Queue depth / drop threshold | unknown | only `FrameDropManager` class names | `EasyFrameDropManager` disassembly |

## 9. Reimplementation mapping

| pipeline stage | JAX module (`reimpl/`) | contract | status |
|---|---|---|---|
| NV21/I420 unpack, YUV→RGB | `ycnn_contract.nv21_to_rgb`, `gorgeous_beauty.yuv_to_rgb` | in `(h,w,1.5)` uint8 → `(h,w,3)` float | **real** (parity-checked) |
| de-stride / de-interleave | `pipeline.rgb_to_nv21` (inverse direction, reference only) | `Image` planes → packed buffer | reference only — arm64 Camera2 not run |
| face crop + resize | `ycnn_contract.crop_resize` | `crop=(l,t,w,h)`, `out_hw`, scale | **real math**, `net_hw` ARCHITECTURE_ONLY |
| detector / landmarks | `ycnn_contract.init_detector`, `detector_forward`, `landmarks_from_heat` | NV21 → heatmap + landmark map | **ARCHITECTURE_ONLY** (weights absent) |
| beauty (mean/var, kMin, sharpen, LUT) | `gorgeous_beauty.*`, `pnglut.py` | RGB float → RGB float | **real** (GLSL transcribed, verified) |
| render/compose | `pipeline.py` composite | preview/record split not modelled | ARCHITECTURE_ONLY |
| encode | — | MediaCodec handoff | not reimplemented (out of scope, no NN) |

`cd reimpl && python pipeline.py` → `RESULT: PASS` (see `reimpl/verify.txt`).
