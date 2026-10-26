# Decompiled sources (targeted, jadx `--single-class` only)

All files are decompiled from `partner-com.kwai.video_33.apk` with
`tools/decompile_classes.py` / `tools/jadx --single-class`, one class per run,
`--show-bad-code --no-res`.  `logs/` holds the jadx stderr for each run.

## YCNN inference engine (`libykit.so` / `libykit_module.so`)

| file | dex | why it matters |
|---|---|---|
| `com.kwai.kscnnrenderlib.YCNNModelInfo.java` | classes14 | **the contract**: `YCNNModelIn`, `YCNNModelConfig`, `KSLandmarksParam`, `KSFaceDetectOut/KSFaceInfo`, `KSMattingOut`, `KSHeadSegOut`, `KSSceneClassOut`, `KSHumanPoseOut`, `KSInstanceSegOut`, `KSTaskDetectOut`, `KSInputInfo`, and all 35 `YCNNModel*` type ids + `KSLandmarksType`/`KSMaskOutType` enums. (jadx emits the whole outer class for each inner-class request; the `$…` copies were byte-identical and have been collapsed into this one file.) |
| `com.kwai.kscnnrenderlib.KSJNILib.java` | classes14 | 32 `native` methods: `createModel`, `renderModel(Object, byte[])`, `updateModelOut`, `YCNNGetConfig2Model`, `runTechMatting`, `runTechDepth`, `runImgRecog`, `getContour`. `static{}` loads `c++_shared` + `ykit`. |
| `com.kwai.kscnnrenderlib.KSRenderObj.java` | classes14 | Java-side marshalling: `runModelBuffer` → `renderModel(in.data_0)` + `updateModelOut`; `getLandmarks/getHeadSegOut/getHumanMattingOut/getSceneClassOut/…` field-by-field copies; `addKSImage` shows the `KSImage{width,height,channel,range}` layout. |
| `com.kwai.kscnnrenderlib.YCNNModel.java` | classes14 | thin facade (landmark-only API used by the camera SDK). |
| `com.kwai.kscnnrenderlib.YCNNComm.java` | classes14 | output image container: `KSImage{width, height, channel, ByteBuffer buffer, KSRect range}`, `KSPtInfo`, `KSRect/KSRectf/KSVec2f/KSVec3i`. |

## Camera face detection (`libfacedetectcontext.so`)

| file | dex | why |
|---|---|---|
| `com.kwai.camerasdk.face.FaceDetectorContext.java` | classes15 | 26 `native` methods, `ModelMissingListener.onModelMissing(int, String)`, static lib load order `opencv_world → ykit → facedetectcontext` (lines 68-73). |

## Per-frame YCNN providers (`libykit_core_plugin.so`)

| file | dex | why |
|---|---|---|
| `com.kwai.video.westeros.v2.ycnn.YcnnPlugin.java` | classes13 | `nativeStartProvide{Salient,GeneralRecog,FaceAttribute}Data`, callback payloads (`SalientData`, `YcnnFaceAttributeResult`, JSON string), `setAttrLocalPath` / `setSaveLogInfoPath` param JSON. |

## Model acquisition

| file | dex | why |
|---|---|---|
| `com.yxcorp.gifshow.util.resource.model.a.java` | classes5 | enum of downloadable AI resources with their business-API response names (`model_matting_v1`, `effects_like_motion`, `voice_detect_mode`, …) and `useYcnnModelConfig()`. |
| `com.yxcorp.gifshow.util.resource.model.c.java` | classes5 | "MagicModel.java": resource type 4, CDN url selection, MD5 validation, unzip dir, `addToMagicModelsResourceList()`. |

## Smart edit / auto-cut (`libvisionengine.so`)

| file | dex | why |
|---|---|---|
| `com.kwai.kve.SmartEditTask.java` | classes16 | task lifecycle + `createSmartEditTask` JNI (5,020 B native). |
| `com.kwai.kve.SmartEditTaskBuilder.java` | classes16 | 20+ `set*Native` config knobs (durations, dims, dedup threshold, themes, cache folder). |
| `com.kwai.kve.SmartCoverTask.java` | classes16 | highlight/cover analyzer JNI. |
| `com.kwai.kve.SmartGalleryTask.java` | classes16 | memory-album analysis. |
| `com.kwai.kve.SmartEditResult.java` | classes16 | result model. |
| `com.kwai.kve.SmartUploadAnalyzer.java` | classes16 | **bitrate** model (`openWithModelTypeNative`, `getHardEncBitrate`), not a NN. |

Classes that jadx could not finish inside the time budget (`YCNNModelInfo` outer
class in one shot, `KSRenderObj`, `KSCNN`) are covered either by the collapsed
`YCNNModelInfo.java` or by native analysis in `../native/`.

## Frame path, threading and render/encode (patch: `PIPELINE.md`)

Decompiled in the same way (jadx `--single-class`, one class per run).

| file | dex | why |
|---|---|---|
| `com.kwai.camerasdk.video.VideoFrame.java` | classes15 | the frame currency: `type` 1 YUV / 2 TEXTURE / 3 RGBA / 4 BITMAP, `yuv_format`, direct `FrameBuffer`, ms `timestamp`, protobuf `attributes`, `cropped/transformed*` with `& ~3` alignment. |
| `com.kwai.camerasdk.videoCapture.i.java` | classes15 | **YUVFrameBufferReader**: `Image` planes → packed buffer, sets `yuv_format` 2=NV21 / 1=NV12 / 0=I420 from plane pixel strides, de-strides row-wise. |
| `com.kwai.camerasdk.videoCapture.cameras.camerakit.d.java` | classes15 | **CameraKitSession**: `ImageReader.OnImageAvailableListener` → `VideoFrame.fromCpuFrame(...).withTransform(rotation, mirror)` → `onVideoFrameCaptured`; timestamp-drift check; `FrameMonitor`. |
| `com.kwai.camerasdk.videoCapture.CameraSession.java` | classes15 | `CameraDataListener{onVideoFrameCaptured, onReportCameraFunctionFailed}` contract. |
| `com.kwai.camerasdk.videoCapture.CameraController.java` / `…Impl.java` | classes15 | API selection, `publishMediaFrame`, stale-session drop, Camera1 fallback. |
| `com.kwai.camerasdk.videoCapture.FrameBuffer.java` | classes15 | direct `ByteBuffer` + native buffer pool. |
| `com.kwai.video.westeros.Westeros.java` | classes13 | `setFaceDetectorContext(ctx,mode)` → `nativeSetFaceDetectorContext(JJJI)`, `applyPlugin`/`removePlugin`, `dispose`. |
| `com.kwai.video.westeros.WesterosPlugin.java` | classes13 | plugin handle contract (`nativePlugin` long). |
| `com.kwai.video.westeros.v2.faceless.FacelessPlugin.java` | classes13 | owns `FaceMagicController`, loads CGE/faceless-plugin libs. |
| `com.kwai.video.westeros.v2.faceless.FaceMagicController.java` | classes13 | 124 methods, all `native*` effect/beauty entry points; extends `WesterosGlProcessor`. |
| `com.kwai.video.westeros.v2.WesterosGlProcessor.java` | classes13 | `nativeProcessor` + `toDaenerysProcessor()` → `nativeGetDaenerysGlProcessor`. |
| `com.kwai.camerasdk.preprocess.AbstractGlProcessor.java` / `GlPreProcessor.java` / `a.java` | classes15 | GL pre-processor base: `onDraw(VideoFrame,int)`, `onRenderThreadDestroy()`, `nativePostRunnableToGlThread`. |
| `do.o0.java` (jadx file name) | classes15 | `FrameProcessThread` enum: camera / cpu / gpu / encoder thread ids (1,2,3,4). |
| `com.kwai.camerasdk.face.a.java` | classes15 | `FaceDetector` interface (`a(boolean)`, `b(h0)`). |

Native evidence for this section: `../native/daenerys_videorawframe.s`,
`../native/daenerys_videorawframe.strings.txt`, `../native/ylab_createmodel.s`,
`../evidence/07_camera_jni_trace.md`, `../evidence/08_daenerys_westeros_strings.txt`.
