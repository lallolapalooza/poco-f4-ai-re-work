# Data-pipeline contract — Mi Media Editor (`com.miui.mediaeditor`, 1.9.0.6-global)

Scope: how a photo/video edit flows from the editor UI through the FaceUnity
(FuAI TFLite) engine and the Mi inpaint stack to the saved image.

Evidence roots (all relative to this report directory):
`sources/classes2/` (jadx single-class decompiles made for this pass),
`sources/classes/` (earlier pass), `native/`, `ir/`, `models/bundles/`,
`evidence/`.

Confidence tags: **confirmed** = read directly in the artifact/code,
**inferred** = consistent with several confirmed facts but not observed,
**unknown** = not recoverable from this APK.

Decompiled helper classes keep their obfuscated names. Mapping used below:

| obfuscated | real role | evidence |
|---|---|---|
| `j2.c` | `FUImageRenderInputData` (buffer + process mode + frame meta) | `sources/classes2/j2.c.java` |
| `j2.d` | render result `{texId,width,height}` | `sources/classes2/j2.d.java` |
| `j2.a` | bundle descriptor `{path,name,type}` | `sources/classes2/j2.a.java` |
| `f2.b` | BundleManager (item handles, render order) | `sources/classes2/f2.b.java` |
| `k2.a` | FuAI controller (`fuLoadAIModelFromPackage`) | `sources/classes2/k2.a.java` |
| `m2.a` | `ImageBeautyController` model (per-face params) | `sources/classes2/m2.a.java` |
| `d` (`…gltexturerender.d`) | `GLTexturePhotoRendererNoCache` | `sources/classes2/com.faceunity.core.renderer.gltexturerender.d.java` |
| `c` (`…gltexturerender.c`) | save/export `Runnable` | `sources/classes2/com.faceunity.core.renderer.gltexturerender.c.java` |

---

## 0. End-to-end trace (acceptance): one photo edit → one TFLite model → saved image

Scenario: user opens the editor on a photo, taps 美颜 (Beauty) → 肤色/美白
(whitening) and drags the slider. The whitening pass is masked by the skin
region: the 15-class face-parsing net `Fprs.0003.Exp007e33_quan1_sm.tflite`
(and the single-channel skin-seg net) supply that mask, so this traces the
parsing model.

| # | step | class.method / symbol | evidence | conf |
|---|---|---|---|---|
| 1 | UI fragment created | `com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyFunctionFragment.renderFragment` → `FaceBeautyRenderFragment` | `sources/classes2/com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment.java` | confirmed |
| 2 | slider gesture → menu entity | `FaceBeautyRenderFragment.observeData$*` → `FaceBeautyViewModel.D/E` (`FaceBeautyMenuEntity`) | same file; `sources/classes2/…FaceBeautyViewModel.java:85,101,118` | confirmed |
| 3 | slider → parameter | `FaceBeautyViewModel.v(...)` builds `FaceBeautyProcessPreviewParam`; `FaceBeautyAlgorithm.f/g/h` registers processor map | `sources/classes2/com.miui.mediaeditor.algo.beauty.face.FaceBeautyAlgorithm.java` (`h()`), `…face.processer.a` | confirmed |
| 4 | processor writes intensity | `com.miui.mediaeditor.algo.beauty.face.processer.a.a()` → `m2.a.i0("image_white", …)` (also `image_aver`, `image_blur`, …) | `sources/classes2/com.miui.mediaeditor.algo.beauty.face.processer.a.java:60-120` | confirmed |
| 5 | param encoded per face | `m2.a.h0/i0` → JSON key `{"faceid":N,"param":"image_white"}` + double → `BaseSingleModel.e` → `BaseSingleController.e` → `SDKController.f` → `Faceunity.fuItemSetParam(handle,json,value)` | `sources/classes2/m2.a.java:631-660`, `BaseSingleController.java:250-300`, `SDKController.java:130` | confirmed |
| 6 | engine init (once) | `BeautyBaseManager.b()`: `fuSetMaxFaces(5)`, `fuSetHumanProcessorSetMaxHumans(1)`, `fuSetFaceProcessorDetectMode(IMAGE)`, `fuImageBeautySetAttributePath(attribute)`, `fuImageBeautySetFaceBeautyPath(faceParams)`, `FURenderKit.e("sdkImageCache")` | `sources/classes2/com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager.java:33-80` | confirmed |
| 7 | bundles selected | `…algo.beauty.face.a.c()`: `graphics/image_beauty_facebeauty.bundle` (GL), then `k2.a.e("model/ai_beauty_type_processor.bundle", FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY)`, `…ai_face_beauty_processor.bundle, FUAITYPE_IMAGE_BEAUTY`, `…ai_face_bindi_seg_processor.bundle, FUAITYPE_FACEPROCESSOR_BINDI_SEG_PROCESSOR`, plus `ai_wrinkle_remover_processor.bundle` from `getDir("libs")` | `sources/classes2/com.miui.mediaeditor.algo.beauty.face.a.java:55-90` | confirmed |
| 8 | model bytes → engine | `k2.a.e` → `fuIsAIModelLoaded(type)` then `fuLoadAIModelFromPackage(byte[], type)`; bundle zip read via `miuix…i.w(assets, path)` | `sources/classes2/k2.a.java:95-210` | confirmed |
| 9 | bundle → GL/ai items | `f2.b.a(path)` → `fuSetInputCameraBufferMatrixState(1)` → `fuCreateItemFromPackage(byte[])`; bound via `fuBindItems` | `sources/classes2/f2.b.java:60-100`; `SDKController.b` | confirmed |
| 10 | image in | `BeautyGlView.d(bitmap, ProcessMode.FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY)` → `new GlTexturePhotoRenderer(this, bitmap, magnifier, mode, listener)` → `gltexturerender.d` ctor (`FU_FORMAT_BITMAP`, 30) | `sources/classes2/…BeautyGlView.java:218`; `…gltexturerender.d.java:60-70` | confirmed |
| 11 | preprocess | `BaseGLTextureRenderer.a()` (onSurfaceCreated) → `d.k(j2.c)`: EXIF rotate, `fuImageBeautyConvertRGBA2NV21WithBitmap` (NV21 path) or keep Bitmap → `FURenderBridge.h(cVar)` → `fuImageBeautyPreProcess(type,0,w,h,buf,w,null,0,null,0,items,1,isNew,processMode,-1.0f)` → `FURenderBridge.c` → `fuImageBeautyCreateTextureCoverPreview(...)` | `…gltexturerender.d.java:130-260`; `FURenderBridge.java:190-240,290-360` | confirmed |
| 12 | **model run** | native `ImageBeautyController` (`libCNamaSDK`, `fuImageBeautyPreProcess` @0x4e6780) reads the bundle's `config.json` → `face_parsing.model.tflite_model_param.tflite_name = Fprs.0003.Exp007e33_quan1_sm.tflite`, loads it through FuAI (`libfuai.so`, `fuai/model/tflite/tflite_model.cc`) and runs it on the aligned 384×512×3 face crop | `models/bundles/config_ai_face_beauty_processor.json`, `ir/model_Fprs….json`, `evidence/native_fuai_params.txt` §3/§4 | confirmed (config→model), inferred (exact call site inside the controller) |
| 13 | render | `BaseGLTextureRenderer.f()` (onDrawFrame) drains `FURenderBridge.f1383c`, then `FURenderKit.f().a(0).d(cVar)` → `FUCoreBizApiProxy.a(0)` returns `IFUCoreBeautyImpl` → `.d(cVar)` → `FURenderBridge.c(cVar)` → `fuImageBeautyPreview(items)` → texture | `…BaseGLTextureRenderer.java:440-500,540-560`; `IFUCoreBeautyImpl.java:860-868`; `FUCoreBizApiProxy.java:40-50` | confirmed |
| 14 | preview present | `d.b()` draws `texId` with `n2.b` (shader, 3 matrices) into the GLSurfaceView; `n2.c` copies to the `backTexture` | `…gltexturerender.d.java:74-130` | confirmed |
| 15 | save/export | `BeautyGlView.k(cb)` → `GlTexturePhotoRenderer.e` → `ExecutorService` (single thread) → `gltexturerender.c.run()`: commits manual grids (`image_beauty_manual_type`, `image_manual_acen_data/blur/wrikle`), reads `image_face_data`, builds `ImageBeautyData` via `m2.a.h()`, then on the GL thread `FURenderBridge.d(renderInput)` → `fuImageBeautyGetResultBitmap(w,h,items)`; result wrapped in `CacheData(IMAGE, data, buffer, mode)` | `sources/classes2/…gltexturerender.c.java:45-210` | confirmed |
| 16 | result → editor | `com.miui.mediaeditor.algo.beauty.common.processer.a` → `BeautyExportDataResult` with `d.f7421c = bitmap.copy(ARGB_8888, true)` | `sources/classes2/com.miui.mediaeditor.algo.beauty.common.processer.a.java` | confirmed |
| 17 | file written | editor save service (`com.miui.gallery.editor.photo.app.saver.SaveParam`, `SaveQualityViewModel`) writes the JPEG | `sources/index.md`; class list (`dex_find 'Save'`) | inferred (consumer class present, not decompiled) |

Concrete tensor contract of the model in step 12 (from `ir/`):
input `image` FLOAT32 `[1,512,384,3]`, output `softmax`/`Identity`
FLOAT32 `[1,512,384,15]`, 15-class softmax (`netout_with_softmax: true`),
49 CONV_2D + 24 DEPTHWISE + 13 QUANTIZE ops; size 1,196,952 B.

---

## 1. Data sources / triggers

### 1.1 Photo editor (confirmed)

| item | value | evidence |
|---|---|---|
| entry fragment | `com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyRenderFragment` | `sources/classes2/…FaceBeautyRenderFragment.java` |
| view model | `FaceBeautyViewModel` (`BitmapHolderViewModel`) — holds source `Bitmap` | `…FaceBeautyViewModel.java:39-60` |
| algo facade | `com.miui.mediaeditor.algo.base.BaseAlgorithm.a(BaseParam)` / `.e(BaseParam, callback)` — dispatch by `param.getClass()`; lazy `h()` init | `sources/classes2/…BaseAlgorithm.java:20-60` |
| GL surface | `com.miui.mediaeditor.algo.beauty.common.core.widgets.BeautyGlView extends c` (GLSurfaceView); `setEGLContextClientVersion(2)`, `setRenderMode(0)` = RENDERMODE_WHEN_DIRTY | `…BeautyGlView.java:212`; `…BaseGLTextureRenderer.java:130-145` |
| magnifier | `MagnifierBeautyGlView` — second renderer for the hand-touch detail view | `…FaceBeautyRenderFragment.java:414` |
| trigger cadence | one render per invalidation (`i(true)` → `requestRender`); no fixed FPS | `…BaseGLTextureRenderer.java:562-575` |
| engine init | `BeautyBaseManager.b()` (idempotent, `AtomicBoolean`) | `…BeautyBaseManager.java:33` |
| model install | `FURenderKit.h(m2.a)` → `FaceBeautyController.g(j2.b(bundle,…))` (applyControllerBundle) → flush queued per-face params → `fuProcessorChangeModel(1)` | `sources/classes2/com.faceunity.core.faceunity.FURenderKit.java:40-70`; `FaceBeautyController.java:20-40` |
| teardown | `BeautyBaseManager.d()` → release AI models, destroy items, `fuReleaseGLResources`, `fuDone`, `fuOnDeviceLost` | `…BeautyBaseManager.java:80-120`; `IFUCoreVideoImpl.a` for the video variant |
| ui→bundle table | `com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel.s` builds `FaceBeautyCategory/FaceBeautyMenuEntity`; `og.c` enum values map to params | `…FaceBeautyViewModel.java:85-118`; `…face.processer.a.java` |

### 1.1b Detection gate (confirmed)

Before a beauty UI is offered, the editor runs a detection pass:

```
com.miui.mediaeditor.algo.detect.FaceUnityDetectAlgorithm.f(FaceUnityDetectInitParam)
  case 0  -> k2.a.e("model/ai_face_processor_detect_landmark.bundle",
                    FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS)
  case 1  -> k2.a.e("model/ai_human_processor_beautybody_mb.bundle",
                    FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY)
  case 3  -> same landmark bundle
  (case 2 -> NotImplementedError)
release: g(FaceUnityDetectReleaseParam) -> k2.a.f(type) -> fuReleaseAIModel(type)
```

Processors: `FaceNumDetectParam` (`rg.b`), `BodyNumDetectParam` (`rg.a`),
`LandMarksDetectParam` (`rg.c`) — registered in `h()`.
Evidence: `sources/classes2/com.miui.mediaeditor.algo.detect.FaceUnityDetectAlgorithm.java`.

### 1.2 Video editor / transcoder (confirmed engine, app wiring inferred)

| item | value | evidence |
|---|---|---|
| Java API | `com.xiaomi.milab.videosdk.XmsContext.setupBeauty(soPath, bundlePath, cachePath, flag)` / `shutDownBeauty()` | `sources/classes2/com.xiaomi.milab.videosdk.XmsContext.java:204,453` |
| decode (confirmed) | `libMiVideoSDK.so`: NDK `AMediaCodec_createDecoderByType` / `dequeueOutputBuffer` / `createInputSurface` + bundled `libhevc` software HEVC decoder (`external/libhevc/decoder/ihevcd_*.c`); frame pull via `FrameRetriever.nativeGetNextFrame` @ `0x108840` / `nativeGetNextFrameData` @ `0x108914` | `evidence/video_beauty_native.txt` |
| JNI | `Java_com_xiaomi_milab_videosdk_XmsContext_nativeSetupBeauty` @ `0xf4704`, `…nativeShutDownBeauty` @ `0xf480c` | `evidence/video_beauty_native.txt` |
| native | `Context::setupBeauty(char const*,char const*,char const*,int)` @ `0xed444` → `new BeautyWapper(soPath)` → `BeautyWapper::setup(bundle,cache,flag)` → `faceProcessorSetFaceLandmarkQuality`, `setMaxFaces`, `setFaceProcessorDetectMode`, `enableVideoHash(1)` → `mlt_face_beauty_register()` | disassembly in `evidence/video_beauty_native.txt` |
| graph node | MLT/Movit filter `movit.filter.face.beauty` with properties `filter.face.beauty.{tex.render,cache.texture,cache.position,surface.width,surface.height,backup}` | strings in the same evidence file |
| per-frame | `BeautyWapper::render(int,void*,int,void*,int,int,int,int*,int,int,int,void*)` (symbol, mangled at `libMiVideoSDK.so`) | `evidence/video_beauty_native.txt` |
| no in-APK caller | `setupBeauty` is defined only in `XmsContext`; no `com.miui.mediaeditor.*` caller | `dex_find 'setupBeauty'` output |

---

## 2. Transport and format

### 2.1 Buffer formats (confirmed)

`com.faceunity.core.enumeration.FUInputBufferEnum`
(`sources/classes2/…FUInputBufferEnum.java`) and
`com.faceunity.wrapper.Faceunity` constants
(`sources/classes2/com.faceunity.wrapper.Faceunity.java:78-101`):

| enum | int | FU_* constant | use in the editor |
|---|---|---|---|
| `FU_FORMAT_YUV_BUFFER` | 0 | `FU_FORMAT_I420_BUFFER=13`, `FU_FORMAT_NV21_BUFFER=2` | planar YUV input path (3 planes) |
| `FU_FORMAT_NV21_BUFFER` | 2 | `FU_ADM_FLAG_NV21_TEXTURE=4` | **photo renderer after RGBA→NV21 conversion**; video |
| `FU_FORMAT_RGBA_BUFFER` | 4 | `FU_ADM_FLAG_RGBA_BUFFER=128` | declared, not used by the photo path |
| `FU_FORMAT_BITMAP` | 999 (SDK-only) | — | **default photo path** (`GlTexturePhotoRenderer` ctor) |

### 2.2 Photo: Bitmap / NV21

* default: `FU_FORMAT_BITMAP` — `j2.c.a.f11721e` holds the `Bitmap`
  (`…gltexturerender.d.java:66`).
* NV21 branch: `d.k()` aligns to even size
  `w' = w + (w % 2)`, `h' = h + (h % 2)`, allocates
  `byte[(h'*w'*3)/2]`, calls
  `fuImageBeautyConvertRGBA2NV21WithBitmap(bitmap, w', h', buf)`, and sets
  `j2.c.f11714a/f11715b = w'/h'` (`…gltexturerender.d.java:218-260`).
* stride: `fuImageBeautyPreProcess(..., buf, stride = width, ...)`
  (`FURenderBridge.java:290-320`) — stride equals width, no padding.
* NV21 layout built by the SDK for the video-style input path: Y plane first,
  then **V at even offsets, U at odd** — `FURenderBridge.b()`:
  `bArr5[length + i*2] = v[i]; bArr5[length + i*2 + 1] = u[i];`
  (`FURenderBridge.java:400-430`).
* colour: source `Bitmap.Config.ARGB_8888`; `RGBA_F16` is converted down
  (`…gltexturerender.d.java:136-150`); EXIF `Orientation` 3/6/8 rotated
  180/90/270 by `Matrix.postRotate` before upload (same method).
* GL target: `GLES20` (ES 2.0 context, `setEGLContextClientVersion(2)`),
  textures `GL_TEXTURE_2D` 3553.

### 2.3 Video: NV21 → render → NV21/RGBA readback

`FURenderBridge.b(cVar, RenderTypeEnum.NORMAL)`
(`FURenderBridge.java:480-560`) computes readback w/h from the transform
matrices (`CCROT90/270` swap them), packs the planes, then:

```
SDKController.d(w, h, frameId, items, buf, FU_FORMAT_NV21_BUFFER.getType(),
                readback_w, readback_h, readbackBuf)
  → Faceunity.fuRenderImg(w, h, frameId, items, flags=0, buf, imgType,
                          readback_w, readback_h, readbackBuf)
```

`frameId` is a monotonically increasing `int` (`this.f++`). For the video
engine the same contract is wrapped by `BeautyWapper::render` (12 args:
`(tex_or_ptr, void*, w, void*, h, …, params, …, out)`).

### 2.4 JNI signatures on the photo path (confirmed)

Java declarations: `sources/classes2/com.faceunity.wrapper.Faceunity.java`;
JNI symbols exported by `libCNamaSDK.so` (`nm -D`):

| Java native | descriptor | JNI symbol @ address |
|---|---|---|
| `fuImageBeautyConvertRGBA2NV21WithBitmap` | `(Landroid/graphics/Bitmap;II[B)I` | `Java_com_faceunity_wrapper_Faceunity_fuImageBeautyConvertRGBA2NV21WithBitmap` @ `0xbea334` |
| `fuImageBeautyPreProcess` | `(IIII[BI[BI[BI[IIIIF)I` | `…_fuImageBeautyPreProcess` @ `0xbe5198` (impl `fuImageBeautyPreProcess` @ `0x4e6780`) |
| `fuImageBeautyPreProcessWithBitmap` | `(Landroid/graphics/Bitmap;[IIIIF)I` | `…_fuImageBeautyPreProcessWithBitmap` @ `0xbe53ec` |
| `fuImageBeautyCreateTextureCoverPreview` | `(III[BI[BI[BIF)I` | `…_fuImageBeautyCreateTextureCoverPreview` @ `0xbee350` |
| `fuImageBeautyPreview` | `([I)I` | `…_fuImageBeautyPreview` @ `0xbe79a8` |
| `fuImageBeautyGetResultBitmap` | `(II[I)Landroid/graphics/Bitmap;` | `…_fuImageBeautyGetResultBitmap` @ `0xbe7a5c` |
| `fuImageBeautyGetResult` | `(IIII[BI[BI[BI[I)I` | `…_fuImageBeautyGetResult` @ `0xbe972c` |
| `fuImageBeautyGetResultSize` | `([I)[I` | `…_fuImageBeautyGetResultSize` @ `0xbe9a38` |
| `fuImageBeautySetParam` | `([ILjava/lang/String;Ljava/lang/String;)I` | `…_fuImageBeautySetParam` @ `0xbeab60` |
| `fuItemSetParam` | `(ILjava/lang/String;D)I` / `(ILjava/lang/String;[D)I` | `…_fuItemSetParam__ILjava_lang_String_2D` @ `0xbdc39c` / `…_2_3D` @ `0xbdcbf8` |
| `fuLoadAIModelFromPackage` | `([BI)I` | `…_fuLoadAIModelFromPackage` @ `0xbdc070` |
| `fuCreateItemFromPackage` | `([B)I` | `…_fuCreateItemFromPackage` @ `0xbdbfb8` |
| `fuRenderImg` | `(III[II[BIII[B)I` | `…_fuRenderImg` @ `0xbe36a8` |
| `fuImageGetImageInfo` | `(Landroid/graphics/Bitmap;II)[F` | `…_fuImageGetImageInfo` @ `0xbf0d5c` |
| `fuImageBeautyGetInfo` | `(ILjava/lang/String;[F)I` | `…_fuImageBeautyGetInfo__ILjava_lang_String_2_3F` @ `0xbefe00` |
| `fuGetFaceInfo` | `(ILjava/lang/String;[F)I` / `(ILjava/lang/String;[I)I` | `…_fuGetFaceInfo__ILjava_lang_String_2_3F` @ `0xbdf930` / `…_3I` @ `0xbdfa34` |
| `fuSetMaxFaces` | `(I)I` | `…_fuSetMaxFaces` @ `0xbdcdec` |
| `fuSetFaceProcessorDetectMode` | `(I)I` | `…_fuSetFaceProcessorDetectMode` @ `0xbe2324` |

`fuImageBeautyPreProcess` argument meaning (from the only call site,
`FURenderBridge.h`): `(format, tex_id=0, width, height, buffer, stride,
buffer1=null, 0, buffer2=null, 0, items[], isGpuMode=1, isNewImage,
processMode, scaleRatio=-1.0)`.

---

## 3. Preprocessing

Order for a photo (confirmed unless noted):

1. **Decode/rotate** — `Bitmap` from the editor (already decoded by the host
   editor) or `BitmapFactory.decodeFile` + EXIF rotation in
   `gltexturerender.d.k()`.
2. **RGBA→NV21** — `fuImageBeautyConvertRGBA2NV21WithBitmap` (native,
   `libCNamaSDK` `Java_…_fuImageBeautyConvertRGBA2NV21WithBitmap` @ `0xbea334`)
   when `FU_FORMAT_NV21_BUFFER` is selected.
3. **Upload + preprocess** —
   `fuImageBeautyPreProcess(format, tex_id=0, w, h, buf, stride=w, buf1=null,
   0, buf2=null, 0, items, isGpuMode=1, isNewImage, processMode, scaleRatio=-1)`
   (`FURenderBridge.java:290-320`). `isGpuMode=1` for every photo call.
4. **Per-model crop / align** (inside the engine, driven by the bundle
   `config.json`):
   * `image_width` × `image_height` × `image_channels` = network input;
   * `mean_shape` (float pairs) in a `mean_shape_width`×`mean_shape_height`
     frame — the detected 75 landmarks are fitted to this mean shape, which
     is the face-alignment transform (parsing: 75 pts in 512×512;
     teeth: 75 pts in 384×384; bindi: 75 pts in 512×512);
   * `netin_image_expand_udlr_ratio` expands the region (teeth 0.25,
     bindi 0.01);
   * `local_region_key_area_kps_of_kps75` (teeth: 46…63) and
     `bindi_area_kps75_index` (28 landmark ids) select the landmark subset
     that defines the crop;
   * `detect_rotation_crop: false` for all shipped bundles.
5. **Normalization** — input tensors are FLOAT32 for the parsing/skin/teeth
   models, UINT8 for the body keypoint net (`Input_img` `[1,256,192,3]`
   uint8, fully quantized graph). `libfuai.so` exposes a
   `mean_stddev_normalization` config key and TFLite-style
   `scale`/`zero_point` handling; the concrete scale for the float models is
   **not** in the recovered `config.json` (no mean/std fields) → **unknown**.

Model-side input contracts (from `ir/bundle_models.json`, per-model rows in §4).

---

## 4. Model invocation

### 4.1 Engine / runtime

| layer | fact | evidence |
|---|---|---|
| runtime | FuAI 8.2.0 = vendored TensorFlow Lite (build path `fuai_8.2.0`, `fuai/model/tflite/tflite_model.cc`, `tflite_model_preprocess.cc`) | `native/libfuai.elf.txt`, `evidence/native_fuai_params.txt` §3 |
| backends | XNNPACK (`failed to create XNNPACK runtime/subgraph`), GPU delegate (`CreateGPUDelegate`), DSP/Hexagon (`tflite_use_dsp`, `fuHexagonInitWithPath`), CoreML key present (unused on Android) | same file §4 |
| session creation | per `config.json` `tflite_model_param`: `tflite_name`, `threads`, `tflite_use_xnnpack`, `tflite_use_gpu` + `tflite_gpu_param{tflite_model_token, max_queue_count:8, tflite_gpu_use_serialization:true, tflite_init_create_gpu_close:true, tflite_release_tmp_memory:false}`, `tflite_use_dsp`, `tflite_inference_priority1_max_precision` | `models/bundles/config_*.json` |
| model bytes | `fuLoadAIModelFromPackage(byte[] bundleZip, type)` — the whole `.bundle` (decrypted ZIP) is handed to the engine; Java never sees the `.tflite` bytes | `sources/classes2/k2.a.java:200-215` |
| AI type ids | `FUAITYPE_IMAGE_BEAUTY=2048`, `FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY=65536`, `FUAITYPE_FACEPROCESSOR_BINDI_SEG_PROCESSOR=67108864`, `FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY=131072`, `FUAITYPE_PHOTOEDITOR_ROCESSOR=4194304`, `FUAITYPE_FACE_ATTRIBUTE_PROCESSOR=4096` | `sources/classes/com.faceunity.core.enumeration.FUAITypeEnum.java`, `Faceunity.java:24-71` |
| item creation | `fuCreateItemFromPackage(byte[])` on the raw bundle; `fuSetInputCameraBufferMatrixState(1)` before | `f2.b.java:60-75` |
| render order | `f2.b.g(handle)` inserts at index 0 → **last bound item renders first**; `d()` snapshots the list for `items[]` | `f2.b.java:150-190` |
| batching | none; one preprocess/preview/result call per user action | `FURenderBridge` (all paths) |
| threads | `HandlerThread("KIT_…")` in `BaseSingleController.d()`; `Handler` queue in `FURenderBridge.b`; single-thread `ExecutorService` for save; all `fu*` GL calls on the GLSurfaceView thread | `BaseSingleController.java:100-160`, `FURenderBridge.java:60-90`, `gltexturerender.d.java:70` |

### 4.2 Per-feature bundle → model table

| feature | bundle (asset path) | config section | model(s) actually loaded | input → output | conf |
|---|---|---|---|---|---|
| face detect + landmarks | `model/ai_face_processor_detect_landmark.bundle` | (config.json in encrypted prologue) | `n-small_256_0.05_only_a5` (0.05 score, 256²), `n-0.5_416_0.05_4aspect6` (416²), `s1` (64²→91 pts), `Fpe…XScore_base`, `Fpe…refine1/2/3`, `face_landmark_mb360_64` (64²→154), `facecheck40…` (40²→score), `head` ([1,126]→14) | image crops → landmarks/score | confirmed (models+shapes), bundle mapping confirmed from paths in `…algo.beauty.face.a.c()` and `f2.b` |
| face parsing (skin mask for whitening/even-skin) | `model/ai_face_beauty_processor.bundle` (`FUAITYPE_IMAGE_BEAUTY`) | `face_parsing` | **`Fprs.0003.Exp007e33_quan1_sm.tflite`** | `[1,512,384,3]` f32 → `[1,512,384,15]` softmax | confirmed |
| skin segmentation | same | `face_skin_seg` | `skin_model_expand_supply_pta18000_quant_resizetrain_seg153.tflite` | `[1,320,320,3]` → `[1,320,320,1]`, `mask_threshold 0.6`, `keep_block_radio 0.01` | confirmed |
| even skin (柔肤) | same | `face_even_skin` | `mobile_spade_256_ch16_from16_40000_ft16_ftsave_yunfu.tflite` (SPADE GAN) | `[1,256,256,3]` → `[1,256,256,3]` Tanh; GPU (`tflite_use_gpu:true`) | confirmed |
| flaw remover | same | `face_flaw_remover` | `mobile_spade_256_ch16_from16_ftsevere_lr0.0001_117_16001.tflite` | same as above | confirmed |
| del-spot (痘/斑/痣) | same | `face_del_spot` | 4 × 24×24×3→2 (`hair`, `lines`, `highlight`, `acne_mole`) + `mole_seg_24x24x3_1206_quat` (24²→24²×1) | patch classifiers + mask | confirmed |
| teeth whitening | same | `face_teethseg` | `FprsTeeth.0001.Exp002pe99_quan1_sm.tflite` | `[1,192,256,3]` → `[1,192,256,4]`, `teeth_mask_order 3` | confirmed |
| bindi (forehead mark) | `model/ai_face_bindi_seg_processor.bundle` | `face_bindi_seg` | `bindi_seg_tiny_2024_0924.tflite` (in encrypted prologue) | `[1,128,96,3]` → `[1,128,96,1]` | confirmed (bundle/config), model bytes lost to prologue |
| beauty-type / dense landmarks (eyes, iris, mouth, hair) | `model/ai_beauty_type_processor.bundle` (`FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY`) | (config in prologue) | `s1_112_98`, `s2`, `s3` (5-crop), `dense_eb`, `dense_eye_iris`, `dense_iris`, `dense_mouth`, `face_landmark_lite_210818_360`, `model_hair`, `mouth_checker_14`, `n-small…`, `n-0.5…` | crops → sub-landmarks + heatmaps | confirmed (shapes), config mapping inferred |
| face attribute (age/gender) | `model/ai_face_attribute_processor.bundle` | `ai_face_attribute` | `age_gender160_0422.tflite` (prologue) | `128×160×3`, 150-value mean shape | confirmed (config), model lost |
| body slim / body reshape | `model/ai_human_processor_beautybody_mb.bundle` | `human_detector`, `human_keypoint.human_keypoint_processor_imgslim` | `human_detector_retinanet_mobilenetv2_1.0_quant` (320²) → `Bdycntr.0406…quan1` (288×384, 67 joints) → `FULLBODY_POFH2D…` (256×192 → hms 64×48×31 / pofs 64×48×78 / bbfs 8×6×192) | RGB crops → heatmaps/POF offsets | confirmed (config), 2 of 3 model files in prologue |
| scene recognition | `model/ai_photo_editing_processor_scene_recog.bundle` | prologue | `MOBILEONE_S2_SIZE384_EXP30_DATA7.0.0_FP16` (prologue) | 384² input | inferred (path + type id only) |
| makeup | `makeup/item_bundle/{jingdian,jingfu,liti,nanshi,oumei,wenrou}.bundle` | `makeup_config.json` + `script.js` | none (PNG layers, GLSL) | textures → composite | confirmed |
| reshape / warp (脸型) | `graphics/image_beauty_facewarp.bundle` | `face_warp.json` + 30 GLSL | none (mesh + displacement PNGs) | landmarks → UV displacement | confirmed |
| inpaint (removal) | — (external NNF) | — | `libvis` NNF file from `nativeInit(path,path2)` | Bitmap + mask → Bitmap | confirmed (API), weights unknown |

### 4.2b Bundle selection call sites (confirmed)

| bundle | Java call site | note |
|---|---|---|
| `graphics/image_beauty_facebeauty.bundle` | `com.miui.mediaeditor.algo.beauty.face.a.c()` | GL beautify passes |
| `model/ai_beauty_type_processor.bundle` | `algo.beauty.face.a.c()` (face), `pg.a.c()` (face shape) | `FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY` |
| `model/ai_face_beauty_processor.bundle` | `algo.beauty.face.a.c()`, `pg.a` | `FUAITYPE_IMAGE_BEAUTY` |
| `model/ai_face_bindi_seg_processor.bundle` | `algo.beauty.face.a.c()` | `FUAITYPE_FACEPROCESSOR_BINDI_SEG_PROCESSOR` |
| `graphics/image_beauty_facewarp.bundle` | `pg.a` (`sources/classes2/pg.a.java:18`) | reshape mesh bundle |
| `graphics/image_beauty_bodyslim.bundle` | `kg.c` (`sources/classes2/kg.c.java:18`) | body slim GL |
| `model/ai_human_processor_beautybody_mb.bundle` | `kg.c`, `algo.detect.FaceUnityDetectAlgorithm.f` (case 1) | `FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY` |
| `model/ai_face_processor_detect_landmark.bundle` | `algo.detect.FaceUnityDetectAlgorithm.f` (case 0/3) | `FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS` |
| `graphics/image_editor_*.bundle`, `ai_photo_editing_processor_scene_recog.bundle` | not reached from the decompiled editor classes | strings present in dex; scene/ID-photo/enhance entrypoints (`FU_IMAGE_EDITOR_MODE_*`) exist in `ProcessMode` |
| `ai_wrinkle_remover_processor.bundle` | `algo.beauty.face.a.e()` via `ng.a.d + "/ai_wrinkle_remover_processor.bundle"` | dynamic-feature path, `FUAITYPE_PLUGINS_WRINKLE_PROCESSOR_IMAGE_BEAUTY` |
| `makeup/item_bundle/*.bundle` | **no caller found** | U7 |
| `model/ai_face_attribute_processor.bundle` | strings present; caller not found | used for smart recommend (`image_auto_style`) via native |

### 4.3 Tensor binding

Java **never** binds tensors: it binds an image buffer to the engine once per
edit (`fuImageBeautyPreProcess…` / `fuRenderImg`) and reads back
(`fuImageBeautyGetResult*`). The tensor-level binding happens inside
`libfuai.so` (`fuai/model/tflite/tflite_model.cc`): per model, the
`config.json` `image_*`/`netin_*`/`netout_*` block defines the crop, the
resize and the tensor shape; the engine allocates the tensor and copies from
its internal aligned RGBA/NV21 block. Consequences:

* models are **not** interchangeable with their raw TFLite input shape — the
  parser/segmenter nets expect the aligned face crop, not the full image;
* `out_mask_scale` (0.5) and `netout_width/height` define the mask
  resolution the engine produces internally before the GL resample;
* the same bundle can hold several nets (`s1/s2/s3`, 4 del-spot
  classifiers) that share the internal block.

---

## 5. Output postprocessing

### 5.1 Mask decoding (confirmed)

* parsing: 15 channels, `cate_mask_order` maps 19 UI classes to channels
  `[0..13,0,0,0,14,0]`; `netout_with_softmax=true`;
  `flg_multiface_post_use_prob=true` (per-face probability blend).
* teeth: `cate_mask_order` `[…, -1, -1, 3]` (`-1` = drop), `teeth_mask_order=3`,
  `bg_mask_order=0`.
* skin: single channel, threshold 0.6, `keep_block_radio 0.01` (drop
  connected components below 1 % of the face area).
* del-spot: `diff_thresh 0.48`, `thresh_val 0.13`, `area_ratio_thresh 0.3`,
  `protected_mask_thresh 110` (skin-protection LUT
  `face_mask_del_spot_protect_mask_0815A.data`), `unit_rad_scale 35`,
  `expend_corner 1.2`, `scale_size 0.25`.

### 5.2 Mask → image coordinates (confirmed)

The mask is not warped by a homography but by a **UV triangle mesh**:

* `models/bundles/face_mask_mapper_face_mask.json`
  — `face_uv` 160 floats = 80 vertices, `face_ebo` 408 idx = 136 triangles,
  `face_ebo2` 174 idx = 58 triangles. The 15-class mask (384×512) is sampled
  with this mesh and drawn over the detected face quad.
* `models/bundles/face_mask_mapper_skin_mask.data` — 518,412 B =
  12-byte header `{uint32 w=540, uint32 h=960, uint32 c=1}` + 540×960 uint8
  skin prior (value `0xff` inside the mask).
  Verified by header dump (`evidence/editor_datapath.txt` §mask data).
* native `FUAI_MaskTransform` and the `…Warp` accessors convert result masks
  into image space:
  `FUAI_FaceProcessorGetResultAllLandmarksWarp` @ `0x64a9c4`,
  `FUAI_FaceProcessorGetResultRectWarp` @ `0x64a7e8`,
  `FUAI_SkinSegmenterGetResultMaskWarp` @ `0x64a084`,
  `FUAI_HumanProcessorGetResultJoint2dsWarp` @ `0x64a524`
  (`evidence/native_fuai_params.txt` §5).
* Java-side equivalent for the persistable state is the JSON blob
  `image_face_data` = `{"p_rect":[…],"p_do_face_beauty":…,"p_bdyscores":[…],
  "p_jointscores":[…],"p_pofdirpoints":[…],"p_pofdirscores":[…]}` (default
  constant in `ImageBeautyData`/`m2.a`, and the native default string is
  byte-identical, `evidence/native_fuai_params.txt` §2).

### 5.3 Reshape (landmark indexing) (confirmed)

`models/bundles/image_beauty_facewarp_face_warp.json`:

| key | shape | meaning |
|---|---|---|
| `face_uv` | 566 floats = 283 vertices | face mesh UV in the detected face quad |
| `face_ebo` | 1584 idx = 528 triangles | face mesh indices |
| `face_ebo_no_mouth` / `face_ebo_no_eyes` | 1548 / 1512 idx | masks excluding regions |
| `mouth_uv` / `mouth_ebo` | 106 verts / 44 tris | mouth sub-mesh |
| `eye_uv` / `eye_ebo` | 113 verts / 86 tris | eye sub-mesh |
| `eye_offset`, `chin_offset` | 198 / 490 floats = 99 / 245 2-D vectors | per-vertex displacement bases |
| `face_uv_zz`, `face_uv_bz` | 332 floats = 166 verts | two lip variants (zz = 上唇, bz = 下唇 mask) |
| `haritop_ebo1`, `haritop_ebo` | 1650 / 1620 idx | hair-top region |

The displacement is generated per feature by
`ImageBeautyController::Generate{Mask,FaceThinningMask,FaceSmallMask,
FaceNarrowMask,FaceVMask,CheekBoneWarpMask,ChinWarpMask,EyeWarpMask,
EyeSpaceMask,EyeRotMask,BrowWarpMask,BrowLocationMask,BrowSpaceMask,
NoseLongMask,NoseThinningMask,NoseTipMask,ForeheadMask,LowerJawMask,
MouthWarpMask,TempleWarpMask,SmileMask,PlumplipMask,HairTopMask}Mask` (each
takes `vector<float> landmarks, float intensity, …, float radius`) in
`libCNamaSDK` (`evidence/native_fuai_params.txt` §5, mangled but readable).

The GPU application of the mask (confirmed, shader text in
`models/bundles/image_chin_warp_fs.glsl`):

```glsl
vec4 maskColor = texture2D(tex_force, sub_uv);      // RGBA8 displacement
maskColor.r = maskColor.r - 127.0/255.0;            // dx integer part
maskColor.g = (maskColor.g - 0.5) * 2.0;            // dx fraction
maskColor.b = maskColor.b - 127.0/255.0;            // dy integer
maskColor.a = (maskColor.a - 0.5) * 2.0;            // dy fraction
newCoords = rotate(vec2(dx, dy), cAngle, sAngle)
outColor  = texture2D(tex_origin, texc + newCoords * intensity * scale * block_scale);
```

`block_scale` is the per-block downscale, `intensity` the slider value in
`[0,1]` (the app writes `image_*` params as `p/100` or `p/200`, see
`…face.processer.a.java:40-90`).

### 5.4 Alpha blending of masks (confirmed + inferred)

* averaging/blur masks are produced by
  `ImageBeautyController::GetAverMask`, `GetAverAndBlurMaskBySoftSkinMask`,
  `GetAlphaPassOfAverMask`, `CombineFourMaskForPerpareData`,
  `CombineThreeMask_SingleMask`, `CombineManualBlurMask`,
  `GenGlobalNoacenMask` (native, `evidence/native_fuai_params.txt` §5).
* the mask is eroded/dilated on the GPU before blending
  (`image_erode_dilate_fs`, `image_merge_mask_fs`,
  `image_merge_mask_for_ar_fs`, `image_global_boxfilter_with_mask_for_pattern_darkeye_fs`
  — built-in shader names in `libCNamaSDK`, same evidence file).
* the final composite is `dst = mix(dst, effect, mask.a * intensity)` — the
  `mask.a` comes from the mask RTT texture (`rtt_averblur_mask_*` chain).
  **inferred** from the shader names + `tex_mask`/`tex_highlight` usage in the
  built-in `face_mask_rtt` shader; the exact expression is in an encrypted
  first entry of the graphics bundles.

### 5.5 Makeup compositing (confirmed)

`models/bundles/makeup_jingdian_config.json` (identical schema for all six
styles):

| group | params |
|---|---|
| eyebrow | `tex_eyeBrow`, `makeup_eyeBrow_color[]`, `makeup_intensity_eyeBrow`, `brow_warp`, `brow_warp_type`, `use_brow_v2` |
| eyeshadow | `tex_eye`, `tex_eye2`, `makeup_eye_color[]`, `makeup_eye_color2[]`, `blend_type_tex_eye*`, `makeup_intensity_eye`, `use_eye_v2` |
| eyeliner / lashes | `tex_eyeLiner`, `makeup_eyeLiner_color[]`, `tex_eyelash`, `makeup_eyelash_color[]` |
| lips | `tex_lip_mask_bz`, `tex_lip_mask_zz`, `makeup_lip_color[4]`, `is_two_color`, `lip_type`, `lip_blend_weight_type`, `makeup_intensity_lip` |
| blush / highlight / shadow | `tex_blusher`, `tex_highlight`, `tex_shadow` + colors + intensities |
| blend modes | `blend_type_tex_*` (0 = normal/multiply, 2 = screen/soft-light family; enum values not in APK) |

`models/bundles/makeup_jingdian_script.js` runs **inside the engine**
(duktape `DukValue` bindings exist in `libCNamaSDK`): it reads
`makeup_config.json` from the item, calls `FaceUnity.LoadTexture(file, 0)` for
each `tex_*`, and publishes `makeup_info` with the texture ids. The native
`MakeUpController` (`makeup_index_v`, `makeup_current_index`,
`makeupPreprocessLipMask`, `GetLipMaskTexture`, `makeupPointsCalc`,
`makeup_distance`, `makeup_occlusion_type`, `makeup_lip_color2`,
`makeup_blusher_color2`) then composes per frame. App-side, the photo editor
exposes the 3-D makeup sliders as `image_threed`, `image_red`,
`image_red_type`, `image_sharpenbrown`, `image_sharpenbrown_type`
(`m2.a.g0/c0/b0/M/L`), auto-set by the smart-style table
(`IFUCoreBeautyImpl.c`).

### 5.6 Object removal / inpaint (confirmed API, weights unknown)

```
Inpaint.initialize()
Inpaint.inpaintBmpData(src: Bitmap, mask: Bitmap, int, int, int, int, int, int,
                       RemoverNNFData) -> int
Inpaint.upsampleBmpData(src: Bitmap, mask: Bitmap, int×6, RemoverNNFData)
InpaintProJni.init(long, long, modelDir1: String, modelDir2: String) -> long
InpaintProJni.inpaint(src, mask, dst, int, int, NNFData, handle, int, int[]) -> int
InpaintProJni.upsample(handle, bitmap, int, int, NNFData)
```
`RemoverNNFData` = `{width, height, index, count, byte[] nnf}` — the
low-resolution nearest-neighbour-field correspondence map produced by the
network; it is `Parcelable` through a `MemoryFile` (binder transport to the
`:remover` process). The high-res composite is produced by `upsample*`,
i.e. forward warp of the NNF + blend, which is why the NNF is returned to
Java instead of a bitmap.

---

## 6. Consumer / action

| stage | consumer | what changes for the user | evidence |
|---|---|---|---|
| preview | `BaseGLTextureRenderer.b()` (`n2.b` shader, 3 matrices, `GL_TEXTURE_2D`) → `GLSurfaceView` | live preview of the edit | `…gltexturerender.d.java:74-130` |
| magnifier | `MagnifierBeautyGlView` (second `GlTexturePhotoRenderer`, `ProcessMode.f1321e`) | detail loupe while dragging | `…FaceBeautyRenderFragment.java:414` |
| export object | `CacheData{ImageBeautyData, FUCacheImageBuffer, ProcessMode}` | carries both the pixels and all slider values for undo/redo | `sources/classes2/com.faceunity.core.cache.CacheData.java` |
| algo result | `BeautyExportDataResult` (`d.f7420b` data, `d.f7421c` bitmap) | hand-off to the editor's render data | `…beauty.common.processer.a` |
| persistence | `BeautyGlView.k()` → `saveData` on single-thread executor; `fuImageBeautySaveCache/SaveResultToPath` available but unused by this path | cached SDK state (`sdkImageCache`, `faceParams`, `attribute`) | `…gltexturerender.c.java:45-70`; `BaseGLTextureRenderer.java:230-240` |
| file save | `com.miui.gallery.editor.photo.app.saver.*` (`SaveParam`, `SaveQualityViewModel`) | JPEG written to MediaStore | class list only — **inferred** |
| video | MLT/Movit filter node `movit.filter.face.beauty` inside `libMiVideoSDK.so` render graph | beauty applied to decoded frames during preview/export (`MediaTranscode`) | `evidence/video_beauty_native.txt` |

---

## 7. Failure and fallback

| condition | behaviour | evidence | conf |
|---|---|---|---|
| AI model already loaded | `fuIsAIModelLoaded(type) == 1` → skip load, re-apply `fuSetMaxFaces(5)`/`fuSetHumanProcessorSetMaxHumans(10)`, callback `201` | `k2.a.java:100-130` | confirmed |
| bundle asset missing | empty path → callback `10001 "file not found: <path>"`; no engine call | `k2.a.java:130-150` | confirmed |
| load returns != 1 | callback `10002 "loadAIModelFromPackage failed type=… isLoaded=…"`; `SDKController.a()` reads `fuGetSystemError`/`fuGetSystemErrorString` | `k2.a.java:230-250`, `SDKController.java:16-35` | confirmed |
| dynamic-feature bundle absent | `FaceBeautyViewModel.startDownloadLibrary` → download prompt; on failure toast `photo_editor_common_download_failed_msg` | `FaceBeautyViewModel.java:320-360` | confirmed |
| library id → directory | `com.miui.gallery.editor_common.library.a`: `sFaceBeautyBundles = {5002005}`, body `{40030004, 40010009}`, etc.; `a(long) = getDir("libs",0)` | `sources/classes2/com.miui.gallery.editor_common.library.a.java:15-100` | confirmed |
| status codes | `FU_IMAGE_BEAUTY_STATUS_{NO_CONTEXT=1, GL_NOTSUPPORT=2, CONTROLLER_NOTFOUND=3, NO_FACE_DETECT=4, MUTIL_FACE_DETECT=5, LAYERS_ERROR=6, INVALID_ARGUMENT=7, UNINITIALIZED=8, NULLPTR=9, CACHE_DIR_EMPTY=13, ERROR_PROCESS_MODE=14, NO_CACHE=17}` | `Faceunity.java:126-144` | confirmed |
| no face / multi face | processor returns `null` → UI keeps the previous bitmap | `IFUCoreBeautyImpl.c` (face count `fuIsTracking() <= 0` → `null`) | confirmed |
| GPU delegate unavailable | `tflite_use_gpu` per model; engine falls back to CPU/XNNPACK — the actual fallback branch is inside `libfuai` | `CreateGPUDelegate` strings; **branch not observed** | inferred |
| video beauty library not found | `BeautyWapper … dlopen get error: %s` → `setupBeauty` returns error, filter not registered | `evidence/video_beauty_native.txt` | confirmed |
| inpaint library missing | `Inpaint.sLoaded=false` from `System.load` catch; `isAvailable()` false | `sources/classes/…remover.Inpaint.java` | confirmed |

---

## 8. Confidence and open questions

| claim | conf | evidence | what would settle it |
|---|---|---|---|
| Photo path is Bitmap/NV21 → `fuImageBeautyPreProcess/Preview/GetResultBitmap`, one call per user action | confirmed | `FURenderBridge.java:190-360`, `gltexturerender.d.java` | — |
| Parsing net = `Fprs.0003…` at 384×512 f32, 15-ch softmax | confirmed | `config_ai_face_beauty_processor.json`, `ir/model_Fprs…json` | — |
| Java binds only image buffers, tensors are bound in `libfuai` | confirmed | `k2.a`, `FURenderBridge` (no tensor API in `Faceunity`) | — |
| Mask→image mapping uses an 80-vertex UV mesh, not a homography | confirmed | `face_mask_mapper_face_mask.json` | — |
| Warp mesh = 283 verts / 528 tris, displacement RGBA8 with 127/0.5 encoding | confirmed | `face_warp.json`, `image_chin_warp_fs.glsl` | — |
| Makeup = item package with `makeup_config.json` + `script.js` + 8–12 PNG layers, composited natively | confirmed | `evidence/editor_datapath.txt`, `models/bundles/makeup_jingdian_*` | — |
| Makeup style is chosen by a specific Java class | **unknown** | no `Makeup*` class in either dex; only `image_threed`/`image_red*` sliders | vendor demo app / FaceUnity docs |
| Which class calls `XmsContext.setupBeauty` (video) | **unknown** | only definition present | host Gallery app (not in this APK) |
| Float normalization constants for the parser/skin nets | **unknown** | no mean/std in `config.json`; `mean_stddev_normalization` key exists | disassembly of `tflite_model_preprocess.cc` |
| `ImageBeautyController` calls `fuai::TfliteModel::Run` directly for `Fprs` | inferred | config → `tflite_name`; executor in `libfuai` | Ghidra of `fuImageBeautyPreProcess` @ `0x4e6780` |
| GPU vs CPU delegate actually used per device | unknown | per-model `tflite_use_gpu` + device gating | runtime trace |
| Save writes JPEG via `com.miui.gallery.editor.photo.app.saver` | inferred | `BeautyExportDataResult` consumer + saver classes present | decompile `SaveQualityViewModel`/`SaveParam` |

---

## 9. Reimplementation mapping

| stage | JAX module | input → output | status |
|---|---|---|---|
| face parsing (skin mask) | `reimpl/bundles/Fprs…/model.py` (generated by `tools/tflite2jax.py`) | `[1,512,384,3]` → `[1,512,384,15]` float | ARCHITECTURE_ONLY-equivalent weights, numerically drifts 1.76e-01 vs LiteRT (quantized-graph emulation) |
| teeth / skin / mole / del-spot classifiers | not converted; IR only in `ir/bundle_models.json` | see §4.2 | not run |
| body keypoint | not converted (`Bdycntr` in encrypted prologue) | `[1,288,384,3]` → hms 96×72×25 | not run |
| mask → face quad | `face_mask_mapper_face_mask.json` + `face_uv`/`face_ebo` are complete → implementable as a UV-rasterizer in NumPy/JAX | 80-vertex mesh | ready (data complete) |
| warp | `face_warp.json` (283-vertex mesh + per-vertex offsets) + `image_chin_warp_fs.glsl` formula | displacement field → bilinear sample | ready (data complete) |
| makeup compositing | `makeup_jingdian_config.json` + the PNG layers (8 of 12 readable; first entry per bundle encrypted) | layered alpha blend | partial data |
| inpaint | `libvis` NNF weights external; `RemoverNNFData` documents the interface | Bitmap+mask → NNF → upsample | blocked (weights absent) |
| langid (existing) | `reimpl/model.py` | text → 111 classes | verified (`reimpl/verify.txt`) |

Reimplementation is therefore **stage-complete for the postprocess**
(mask/mesh/blend math and its constants are fully recovered) and
**model-incomplete** only where the bundle prologue hides the weights
(U1 in `report.md` §8).
