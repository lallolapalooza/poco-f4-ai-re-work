# Snapchat 11.70.0.36 — data-pipeline integration contract

Scope: `com.snapchat.android` 11.70.0.36, arm64-v8a APK
`cust/app/customized/partner-com.snapchat.android_200/partner-com.snapchat.android_200.apk`
(sha256 `70147a34…a50238`). All paths below are relative to `reports/snapchat/`.

**Headline:** no DNN model file ships in this APK (`inventory/inventory.json`
`model_assets=0`, `ai_libs=0`; `evidence/02_model_magic_scan.txt` TFL3 hits = 0).
The only per-frame AI in the shipped app is the classical-CV Looksery lens stack
(Haar + ERT + ORB + Scenarium GLES). Every DNN path is a *downloaded/deferred*
boundary (see §7). Reimplementation: `reimpl/` (11/11 checks).

Native addresses below are virtual addresses in
`native/libs/libcamplat+.production.e6302221764648a97415467f3352d2c06a35bd64.so`
(vaddr == file offset for the executable segment). JNI bindings were recovered
from the relocated `JNINativeMethod` arrays; full table in
`native/jni_binding_table.txt`.

---

## 1. Data sources / triggers

### 1.1 Live camera → lens render (primary)

| Step | Trigger / entry | Evidence | Conf. |
|---|---|---|---|
| 1 | Android camera permission + Snap camera2/preview pipeline. Camera classes `com.snap.camera.service.hardware.camera2.*`, `android.hardware.camera2.*`, `android.media.ImageReader`, `SurfaceTexture` are present; camera permission declared in inventory. | `inventory/inventory.json` permissions/features; dex class list (`com.snap.camera.service.hardware.camera2.camera2delegate.Camera2Utils`) | confirmed (classes), inferred (which camera2 delegate on this device) |
| 2 | Snap camera frame dispatcher `rk6.a(C7363Og5, S45, float[] transform, int texId, EnumC2325Emg frameType, long ts, long, jIg, jIg, Collection, Z…, I)` on the GL thread. It selects the texture target: `EXTERNAL_OES ? 36197 : 3553`, then calls the lens callback. | `sources/java/classes/rk6.java:91-150`; literal at bytecode `umg.b(i4, enum == EXTERNAL_OES ? 36197 : 3553, j, fArr3, …)` | confirmed |
| 3 | Lens core camera callback `Hs8.b(int texId, int texTarget, long timestamp, float[] m1, float[] m2, EnumC2325Emg type)` (`LensesCameraFeatureProcessingCoreSub`). Builds a frame-state object `LGoa` via `JIi.g(...)` and submits it to the render pipeline. | `sources/java/classes/Hs8.java:64-100`; exception string `LensesCameraFeatureProcessingCoreSub must be initialized first` | confirmed |
| 4 | Render lambda `WA4.e(Object)` receives `LGoa`, applies window/viewport/camera params, calls `LSCoreManagerWrapper.processFrameToTexture(inputTex, texTarget, timestampNs, m1, m2, inW, inH, ImageProcessingConfig)`. | `sources/java/classes/` bytecode transcript in `evidence/07_camera_pipeline_trace.txt`; WA4 fields/body | confirmed |
| 5 | `LSCoreManagerWrapper` (EGL thread) → `nativeProcessFrameToTexture` @ **0x767500** → C++ `LS::CoreManagerWrapper::processFrameToTexture` / `LS::CoreManager::processFrameToTexture` (strings @0x26cd03 / 0x261b18) → `LS::TrackingManager::track(...)` @0x23f117. | `sources/java/classes/com.looksery.sdk.LSCoreManagerWrapper.java:822-834`; JNI table; `native/camplat_relevant_strings.txt` | confirmed (entry + string), inferred (internal call edges) |

Lens runtime initialisation: `LSNativeLibraryLoader.ensureAllAreLoaded()` →
`NLOader.initializeNativeComponent(LENSCORE_COMPONENT_NAME)`; component layout
maps `lenscore` → `libcamplat` + `initialize_native_component_lenscore`
(`sources/java/classes/com.looksery.sdk.CamplatPlusAwareComponentLayout.java`).
That function is exported at **0x7a547c** and calls `RegisterNatives` for the
wrapper classes. `LSCoreManagerWrapper.<init>` then calls
`nativeInit` @ **0x766838** (`(Context,String,Z,VideoCodecFactory,…)J`).
Teardown: `nativeRelease` @ **0x767bcc**.

### 1.2 Standalone face tracker API

`com.looksery.sdk.FaceTrackerWrapper` (`sources/java/com.looksery.sdk.FaceTrackerWrapper.java`)
is a separate, synchronous API used for photo/selfie tracking:

* `nativeSetResourcePath(String core, String cache)` @ **0x754a40**
* `nativeInit(Mode, LandmarksType, ExternalFaceDetectorFactory)` @ **0x753bd8**
* `nativeSetNumberOfFacesToTrack(J,I)` @ **0x754400**
* `nativeSetMinFacePixelHeight(J,I)` @ **0x754424**
* `nativeTrackImage(J, Bitmap) -> float[][]` @ **0x754458**
* `nativeRestartTracking(J)` @ **0x754a24**, `nativeRelease(J)` @ **0x75439c**

Resource root: `Configuration.CORE_ASSETS_PATH = "asset:looksery/core"`, cache
`<cacheDir>/looksery_cache`. `LandmarksType = {Landmarks68, LandmarksWithEyes}`.
The returned `float[]` is flat `x0,y0,x1,y1,…` (`FaceInfo.getLandmarkX/Y`,
`landmarksCount = len/2`). Input is an Android `Bitmap` (the JNI wrapper reads it via
`AndroidBitmap_lockPixels`; the exact bitmap config is not enforced by the Java
signature); the JNI wrapper is the only public still-image contract.

### 1.3 Video / external texture streams

`processTextureToTexture(int inputTex, long tsNs, int outputTex, float[] m1,
float[] m2, int w, int h) -> int` (`LSCoreManagerWrapper:853-856`, JNI
`nativeProcessTextureToTexture` @ **0x767610**). App renderer providers
`Xj4` (DefaultLensCoreRendererProvider) and `Zfe` (SharedLensCoreRendererProvider)
use this for video/post-capture (`sources/java/classes/Xj4.java`,
`Zfe.java`), and set `setCameraParams(59.0f, -1.0f, 0)`.
`ExternalTextureStream` supplies frames through
`nextExternalTextureFrame(float[])`, `getWidth/getHeight`,
`usesOESExternalTexture()` (`com.looksery.sdk.media.ExternalTextureStream`),
bound with `nativeSetExternalTextureStream` @ **0x770c94**.

Lifecycle: init on EGL thread (`EglContextChecker.check("init")`), per-frame on
the GL thread, teardown via `release()` (native 0x767bcc). `setTrackingSurface`
@ **0x770b2c** attaches an encoder/tracking `Surface`. `nativeSetFrontCameraEnabled`
@ **0x76b040** resets the timestamp converter on facing change.

---

## 2. Transport and format

| Item | Contract | Evidence | Conf. |
|---|---|---|---|
| Camera frame | GL texture. Target **36197 = `GL_TEXTURE_EXTERNAL_OES`** for camera/SurfaceTexture, **3553 = `GL_TEXTURE_2D`** for ordinary video textures. Texture id + target are passed separately. | `rk6.java` ternary; `createCameraTexture` disasm: `glGenTextures`, `glBindTexture(0x8D65)`, clamp/linear params — `native/jni_binding_table.txt`, `evidence/07_camera_pipeline_trace.txt` | confirmed |
| Timestamp | `long` nanoseconds. `TimestampConverter.convert(long)` divides by `1e9` and adds a one-time offset (compares against `SystemClock.elapsedRealtime/uptimeMillis/currentTimeMillis`). Camera JNI receives a `double` in seconds (`nativeProcessFrameToTexture(JIID…)`). | `sources/java/classes/com.looksery.sdk.TimestampConverter.java`; JNI signature | confirmed |
| Matrices | Two `float[]` (16 floats = 4×4). Passed as `[F[F`; first is the surface/texture transform (`getTransformMatrix`), second is the camera/crop transform (`jIg.c` in producer). Role of the second is inferred from the producer. | `rk6.java` (`fArr3`, `c24957jIg4.c`); JNI table | confirmed (arity), inferred (semantics of #2) |
| Size | `int width, height` of the input image, plus screen size and separate processing size. `Size.OPTIMAL_FOR_PROCESSING = 600×800` (480,000 px); `optimizeForProcessing()` scales larger frames down to that area, width multiple of 32, height multiple of 4. | `sources/java/classes/com.looksery.sdk.domain.Size.java` | confirmed |
| Camera params | `setCameraParams(float hFovDeg, float vFovDeg, int orientationDeg)` → native `(JFFI)` @ **0x76bfec**. `vFov = -1` means “derive from hFov”; orientation is 0/90/180/270 (engine rejects other values: `Invalid rotationDegreeClockwise value…`). Defaults used by Snap renderers: `59.0f, -1.0f, 0`. | `LSCoreManagerWrapper:995-997`; `Xj4.java`, `Zfe.java`; `camplat_relevant_strings.txt` | confirmed (API/defaults), inferred (source of rotation = display/camera orientation) |
| Facing | `setFrontCameraEnabled(boolean)` @ 0x76b040; `LGoa.m` field; affects mirroring and timestamp reset. | `LSCoreManagerWrapper:1114`; `WA4` bytecode | confirmed |
| Output | `processFrameToTexture(...) -> int` returns a new result texture (RGBA); displayed with `drawTexture(tex, w, h, matrix[, flip])` (JNI `nativeDrawTexture` @ **0x767a00**) or `drawExternalTexture(tex, target, ?, m1, m2)` @ **0x76780c**. | `LSCoreManagerWrapper:653-660, 822-834`; JNI table | confirmed |
| Internal pixel formats | OES/RGBA input sampled with `samplerExternalOES`; engine converts to RGBA and to NV21/gray. Strings: `#extension GL_OES_EGL_image_external : require` @0x254f14, `uniform samplerExternalOES sTexture;` @0x2786ac, `common/rgbToNv21Tex` @0x26222b, `RGBToNV21Converter{SG,GL}Impl` @0x250d20/0x2746a5, `YUV2RGBx Convertor` @0x239f9c, `ERROR converting frame from YUV(A) to ARGB` @0x25d19c. | `native/camplat_relevant_strings.txt`, `models/looksery/core/scenarium/colorConverter.glsl` | confirmed |
| Colour space / camera config | `CameraConfiguration.CameraColorSpace = {GRAYSCALE, RGBY}`, `CameraIndex = {LEFT, RIGHT, STEREO}` (Spectacles path); Android path uses RGBA/OES. | `sources/java/classes/com.looksery.sdk.domain.CameraConfiguration.java`; native typeinfo `LS::CameraConfiguration::ColorSpace` @0x24f116 | confirmed |
| Thread | Processing must run on the thread that owns the current EGL context. `EglContextChecker.check(...)` guards every call; `WA4.e` re-checks the captured render-thread id and throws `Called on a thread with id […] while expecting id […]`. | `LSCoreManagerWrapper` (EglContextChecker calls); WA4 bytecode string | confirmed |
| Cadence | Camera-frame driven, one submission per rendered GL frame. No fixed engine timer. `nativeSetAsyncTrackingMode(boolean)` / `setRestartTrackersOnNewEffects` allow tracking work to move off the render thread; `TrackingStage InitializeAsync="True"` in `Tracking.xml`. `getCurrentFps()` exposes measured FPS. Camera preview is typically 30 fps (inferred from Camera2 preview, not enforced in code). | `models/looksery_ert/Tracking.xml`; `LSCoreManagerWrapper`; `RenderDriver.getCurrentFps` | confirmed (per-frame/async), inferred (30 fps) |

Queue/drop policy: not observable in Java; the engine owns its tracking queue
(`LS::TrackingManager::track(…)` takes `ImageArray&&` and a `Timestamp`).
Marked unknown (§8).

---

## 3. Preprocessing

Order for the live path (`processFrameToTexture`):

1. **OES → RGBA**: `samplerExternalOES` shader; `colorConverter.glsl`
   (`models/looksery/core/scenarium/colorConverter.glsl`). The producer's
   texture transform is applied while sampling. `GL_TEXTURE_EXTERNAL_OES` is
   created with `GL_LINEAR`, `GL_CLAMP_TO_EDGE` (`createCameraTexture` disasm).
2. **Resize / normalize orientation**: input dimensions are the frame's
   `f/g`; processing size is capped by `Size.optimizeForProcessing()`
   (≤600×800, 32/4 alignment). Rotation is applied from
   `setCameraParams(... orientation)` and the displayed matrix. Confirmed for
   parameters, inferred for the exact resize filter.
3. **Colour conversion for trackers**: RGBA → NV21 (`rgbToNv21Tex`, GPU) and/or
   gray 8-bit (`YUV2RGBx`, `RGBToNV21Converter`). Haar requires `CV_8U`
   (`scaleFactor > 1 && image.depth() == CV_8U`, 0x276a0f; `cvIntegral` @0x26bfbc,
   `cvRunHaarClassifierCascadeSum` @0x26b498).
4. **Haar input**: integral image of the uint8 gray frame, 20×20 window,
   scale ladder from `kMinFaceScale`/`minFaceInImageRatio` (strings @0x268e86,
   0x2a488d), `scaleFactor` (>1) @0x2a37c9. Cascade:
   `haarcascade_frontalface_alt2.xml` (20 stages, 1047 depth-2 trees, 4535
   rects, no tilted features) — `ir/haar_cascade.json`.
5. **ERT input**: per face, a crop around the detected box, warped to the
   regressor’s internal normalized patch, with the current mean/previous shape
   as initialisation. `FaceRegressor.bin` runs first
   (`ConfidenceThreshold="0.76"`, `TrackingSession.DstShapeId="0"`), then
   `EyesRegressor.bin`, `DenoiseRegressor.bin`, `CandideRegressor.bin`
   (shape id 1) and `CandidePespectiveRegressor.bin` (shape id 2)
   (`models/looksery_ert/Tracking.xml`; native `LS::ShapeRegressor` /
   `LS::CascadeRegressor` / `LS::ShapeFitter` typeinfo @0x354c35, 0x354bcb,
   0x3540ea; `ErtRegressor.cpp` @0x286e3b).
   **Patch size is not recoverable**: the five payloads are a proprietary
   packed format (first byte `0x53`, mixed int32/double, entropy 4.8–7.9) and
   are not decoded (`ir/ert_regressors.json`). The declared outputs are
   `FaceLandmarks68Unblurred`, `FaceLandmarks68`, `FaceLandmarks98`,
   `EyeLandmarks`, `Candide`, `CandidePerspective`.
6. **ORB input**: uint8 gray, FAST-9 corners with non-max suppression,
   intensity-centroid orientation radius `r-10`, steered BRIEF-256 descriptor.
   Face-region features are matched frame-to-frame; descriptors are quantised
   by the shipped vocabulary tree (branching 20, depth 2, 32-byte centroids).
   Model paths `/NaturalFeatureTracker/SystemData` @0x239e3d; tree names
   `tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin`,
   `tree-b-20-d-2-r-10-numSig-168673445.bin`; native typeinfo
   `LS::Tracking::NaturalFeatureTracker` @0x33b794.
7. **Scenarium**: landmark mesh + `facestretch`/`liquify` displacement; see §5.

`Size.optimizeForProcessing` (confirmed):
`scale = sqrt(600*800 / (w*h))`; if `scale < 1`, new width =
`floor(w*scale / 32)*32`, new height = `floor((newW/w)*h / 4)*4`.

---

## 4. Model invocation

### 4.1 What is actually executed locally

| Stage | Asset / loader | Invocation | Conf. |
|---|---|---|---|
| Face detection | `asset:looksery/core/HaarCascades/haarcascade_frontalface_alt2.xml` | OpenCV cascade parser strings `opencv-haar-classifier` @0x242040, `icvCreateHidHaarClassifierCascade` @0x2828bc, `cvSetImagesForHaarClassifierCascade` @0x2c66d2; `LS::FaceDetectorStage` typeinfo @0x354365; `LS::FaceDetector` (OpenCV) via `LSFaceFeatureDetectorOpenCV` @0x2c4a04. Runs per frame/scale-ladder. | confirmed |
| Optional NN detector | `LENSCORE_NN_FACE_DETECTOR_MODEL` @0x2e71e2; log `Failed to download NN face detector model.` @0x239ae6 / `NN Face detector model was downloaded.` @0x279d99; URL base `https://perception.sc-cdn.net` @0x25ace5 | `LS::Tracking::Face::HybridAccuracyFaceDetector`; only after download. Not present in APK. | confirmed (deferred), unknown (model) |
| ERT landmarks | `asset:looksery/core/LiteTrackingData_ert.lns` (LZC v1 + zstd; 6 entries) | `LS::NnRegressor`/`LS::ShapeRegressor`; `TrackingStage::execute(LS::Frame&)` @0x3569ee; chain in `Tracking.xml`. | confirmed (topology), partial (weights undecoded) |
| ORB tracking | two `tree-*.bin` protobuf vocabulary trees | `LS::Tracking::NaturalFeatureTracker`; Hamming matching; frame-to-frame translation. | confirmed |
| AR mesh / shaders | `Landmarks/triangulation*.txt`, `scenarium/*.glsl` | Scenarium GLES2/3 draw via EGL; `FaceStretchLiquifyRenderingPlugin::render` @0x269689, `FaceStretchRenderingPlugin::renderInternalHelper` @0x2a3504. | confirmed |

### 4.2 Explicit statement: no bundled DNN

**No DNN weights of any kind ship in the APK.** `inventory/inventory.json`:
`model_assets=0`, `ai_libs=0`, `counts.model_assets=0`; all 6,869 zip entries
scanned for `TFL3` → 0 hits (`evidence/02_model_magic_scan.txt`). The only
model-shaped assets are the Haar XML (837,462 B), the ERT LZC container
(302,357 B), two ORB trees (16,019 B each), GLSL/JS/`scene.scn` lens assets and
the encrypted/obfuscated Candide-3 blobs. Inference *code* exists in
`libcamplat` (MACE `MACEVER-594c0a3`, TFLite C API strings, NNAPI imports, TNN
paths), but no weights and no TFLite/TNN shared library are packaged.
`NeuralModelInference: cache directory is empty, falling back to libdnn`
@0x24c097 shows the engine's DNN path expects a downloaded model. The same is
true for the Snap AI Factory `.dnn.enc` models (manifest paths, §7.3).

### 4.3 Binding

The preprocessed texture id (camera OES or 2D) is bound directly; no CPU copy
is made in Java. `nativeProcessFrameToTexture` receives
`(handle, texId, texTarget, timestampSeconds, mat4, mat4, width, height,
ImageProcessingConfig*)`. The engine creates its own EGL surface/context and
samples the texture with `samplerExternalOES`. `ImageProcessingConfig` only
carries Spectacles stereo metadata (`mSpectaclesInfo`).

### 4.4 External / deferred model boundaries

| Boundary | App-side entry | What crosses | What stays local | Conf. |
|---|---|---|---|---|
| Google ML Kit barcode / GMS face | `com.google.mlkit.vision.barcode.internal.BarcodeScannerImpl` (`MobileVisionBase<List<Barcode>>`); `com.google.android.gms.vision.face.internal.client.FaceParcel` / `LandmarkParcel` | camera image (`ImageProxy`/GMS `Frame`, YUV/NV21) + options → **GmsCore** over binder; `Barcode`/`FaceParcel` results back | detection/decoding runs on-device inside Google Play services; no ML Kit model or native lib in the APK | confirmed (classes/parcels/manifest), inferred (IPC detail) |
| Snap AI Factory / Cameo | `FaceNeutrality`, `FSFaceSegmentation`, `LandmarksExtractor`, `F2FScenario`; download via `DownloadBloopsAiModelsDurableJob` (`DOWNLOAD_BLOOPS_AI_MODELS`) | HTTPS download of encrypted `.dnn.enc` bytes + model URL/version; AES key/IV from native `bloops` `internalInit()` | decrypt + MACE/TNN/NNAPI inference on device; `Bitmap`+`float[]` never uploaded by these APIs | confirmed (contract), inferred (runtime) |
| learned search / VoiceML (libclient) | `ModelCoordinator.getModel/updateLocalModel`, `ModelFileBuffer.getAddress/getSize`, `IVoiceMLSDK.asrTranscribe` | model URL/version then model bytes; for ASR, PCM16 `ByteBuffer` + `ASRConfig` | model is mmap'd locally (native address+size); inference/audio processing local; `StatusEnums` reports `FAILEDNETWORK/FAILEDDISK` | confirmed |
| Memories face clustering | `com.snap.memories.lib.faceclustering.job.FaceClusteringJob` (`MEMORIES_FACE_CLUSTERING_JOB`) | Memories media/face metadata → Snap backend; progress via `FaceClusteringProgress` | no local clustering model or native lib | inferred |

ML Kit manifest contracts: `com.google.mlkit.vision.DEPENDENCIES=barcode`,
`com.google.android.gms.vision.DEPENDENCIES=barcode` and `=face,barcode`.
AI Factory backend: ctor literal `"MACE"`, manifest `tnn_shared_lib=libtnn.so`,
Hexagon skels; all absent. ModelCrypto key/IV live in the native component, not
in the APK. Full excerpts: `evidence/08_external_boundaries.txt`.

---

## 5. Output postprocessing

| Stage | Output | Consumer | Conf. |
|---|---|---|---|
| Haar | face rectangles in image coordinates; multi-scale grouping; min face height / max faces set by `nativeSetMinFacePixelHeight` (0x754424) and `nativeSetNumberOfFacesToTrack` (0x754400). | ERT stage, `FaceRegionsListener` (JNI 0x76cf28) | confirmed |
| ERT | 68 landmarks (`Landmarks68`), eye landmarks (`LandmarksWithEyes`), denoised 68, Candide-3 3-D shape, perspective shape; `Tracking.xml` also declares `FaceLandmarks98` pipeline output. | Scenarium mesh, lens JS, face regions, expressions | confirmed (counts/topology), partial (weights) |
| ORB | keypoints + 256-bit descriptors; Hamming matches; translation vote / inlier filtering; used to stabilise and track face/feature regions between detections. | tracking state, Scenarium | confirmed (algorithm), inferred (exact RANSAC/robust estimator) |
| Mesh | 118 triangles (`triangulation.txt`), 124 with mouth (`triangulation_with_mouth.txt`), 21 border fan (`borderTriangulation.txt`); observed index range 0–75 (76 vertices). | `deform.py` reimplementation; Scenarium warp | confirmed |
| Liquify / face stretch | Displacement field applied to screen-space sampling. Vertex shader inputs: `uniform mat4 ptsInvMat[MAX_LIQUIFY]`, `uniform vec4 coeffs[MAX_LIQUIFY]` (`ptx,pty,coef,rad`), `uniform vec3 camDirO[MAX_LIQUIFY]`, attribute `atbCoord2d` (xy + optional zw face-stretch offset). Fragment shader computes `pow(clamp(d²/r², 1/1280, 1), coef) - 1`, inverse-maps `posToFetch`, samples `sc_ScreenTextureSampleView`. | `models/looksery/core/scenarium/facestretchLiquify.glsl`; reimpl `deform.py` | confirmed |
| Render | result RGBA texture; `drawTexture`/`drawExternalTexture` to the app surface, or returned texture id for the compositor/encoder. | preview, capture, video encoder, `saveFrame` | confirmed |

Coordinate transforms: landmark/mesh coordinates are in the input-image space;
the engine applies the camera/display matrices (`mat4`, `mat4`) to reach
screen space. `setCameraParams` supplies FOV and clockwise display rotation,
and `setFrontCameraEnabled` selects mirroring. Exact matrix order is internal
(unknown).

Temporal smoothing: ERT has a `DenoiseRegressor` stage and a confidence
threshold 0.76 for the first 68-landmark stage; tracking restart on new effects
is controlled by `setRestartTrackersOnNewEffects`. No numeric smoothing kernel
is exposed (unknown).

---

## 6. Consumer / action

* **Preview / capture / video**: result texture is composited into Snap’s camera
  preview or encoder. `setTrackingSurface(Surface)` and
  `setRecordingState(PREVIEW|VIDEO|PHOTO)` change the output path; `saveFrame`
  and `nativeDrawExternalTexture` write to an external surface.
* **Lens runtime**: Scenarium draws lenses on the result texture; lens JS/GLSL
  is supplied by the `.lns` LZC packages (`assets/lenses/**`).
* **Face regions**: `startFaceRegionsTracking(FaceRegionsListener)` streams
  face rectangles/landmarks to client code (JNI 0x76cf28). Expression listener
  and `Candide`/`BlendShape` weights feed Bitmoji/lens animation.
* **Standalone `FaceTrackerWrapper`**: `float[][]` landmark arrays to Java
  (`FaceInfo`).
* **Barcode (external)**: ML Kit returns `Barcode` results to the app UI
  (not traced further; no model shipped).
* **AI Factory (deferred)**: `FaceNeutralityResult`, segmentation masks,
  78/98-point landmarks consumed by Cameo/Bloops UI.

---

## 7. Failure and fallback

| Failure | Behaviour | Evidence | Conf. |
|---|---|---|---|
| Haar cascade missing/corrupt | `LSFaceFeatureDetectorOpenCV: No cascade file found!!` @0x257135; `Invalid classifier cascade` @0x258c18; detection fails/returns no faces (no DNN fallback unless downloaded). | strings | confirmed |
| ERT regressor missing | `Trying to call execute on tracking stage with empty regressor; regressor path:` @0x2c5155; `Too many failures while trying to load regressor. Exiting..` @0x2d0597. | strings | confirmed |
| NN face detector download fails | Logs `Failed to download NN face detector model.`, `NN face detector is not ready` @0x2b33ae; falls back to Haar. | strings | confirmed |
| Unsupported device | `throwUnsupportedEnvironmentException` if `nativeInit == 0`; MACE/TFLite paths log backend errors. | `LSCoreManagerWrapper` ctor; strings | confirmed |
| Missing `.dnn.enc` | `FaceNeutrality` throws `IOException("neutralization model not found")` / `"classifier model not found"`; `FSFaceSegmentation`/`LandmarksExtractor` cannot init. | `sources/java/app.aifactory.ai.*` | confirmed |
| ModelCrypto failure | `decrypt` returns null → interpreter handle 0 → init fails. | `ModelCrypto.java`, `FaceNeutrality.java` | confirmed |
| ML Kit / GmsCore unavailable | Task fails/`MlKitException`; app must handle. Models/executors live in Play services. | `BarcodeScannerImpl`/manifest | inferred |
| learned_search model missing | `ModelFileBuffer.stateIsValid()` false; `downloadModel`/`getRemoteModelStatus` status enums. | `ModelFileBuffer.java`, `libclient.so` exports | confirmed |

---

## 8. Confidence and open questions

| Claim | Confidence | Evidence | What would settle it |
|---|---|---|---|
| Camera frame enters the engine as a GL texture (OES 36197 for camera, 2D 3553 for video) plus ns timestamp, two 4×4 matrices, width/height. | confirmed | `rk6.java:91-150`, `WA4` bytecode, JNI signatures | — |
| Processing is synchronous on the EGL render thread; tracking can be async. | confirmed | `EglContextChecker`, WA4 thread check, `setAsyncTrackingMode`, `InitializeAsync` | — |
| Processing resolution is capped at ≤600×800 (480 k px). | confirmed | `Size.OPTIMAL_FOR_PROCESSING`, `optimizeForProcessing()` | — |
| Haar cascade is the Lienhart 20×20 alt2 tree cascade; input is 8-bit gray/integral. | confirmed | XML, `ir/haar_cascade.json`, OpenCV strings | — |
| ERT chain is 68 → eyes → denoise → Candide → perspective, first stage threshold 0.76. | confirmed (topology) | `Tracking.xml` | decode `*.bin` payloads to confirm stage/tree counts |
| ERT patch size and tree layout. | unknown | payload tag 0x53, mixed int32/double, not decoded | reverse the `ErtRegressor` payload format / obtain debug symbols |
| ORB = FAST-9 + r10 orientation + steered BRIEF-256 + 20×2 tree. | confirmed | tree names, strings, reimpl `orb.py` | — |
| Mesh is 118 (mouth 124) triangles over 76 vertices. | confirmed | `triangulation*.txt`, max index 75 | — |
| Liquify formula/uniforms. | confirmed | `facestretchLiquify.glsl` | — |
| No DNN ships in the APK. | confirmed | inventory + TFL3 scan + extension census | — |
| AI Factory models are downloaded `.dnn.enc`, AES-decrypted, run by MACE/TNN in `bloops`. | inferred | manifest meta-data, `ModelCrypto`, `"MACE"` ctor, `tnn_shared_lib`, absent native lib | a device with the bloops component installed |
| ML Kit barcode/face models execute in GmsCore, not the app process. | inferred | manifest dependencies, no native lib/model, thin registrar classes | GmsCore APK / runtime trace |
| Memories face clustering is server-side. | inferred | durable job class + no local model/lib | network trace / server contract |

Open: exact camera2 delegate used on this device; exact Haar scale factor and
min-face defaults; ERT payload format/patch size; RANSAC/robust-estimator
parameters in ORB tracking; queue drop policy in `TrackingManager`.

---

## 9. Reimplementation mapping

| Pipeline stage | JAX module | Input → output | Status |
|---|---|---|---|
| Haar integral-image cascade | `reimpl/haar.py` (`integral_image`, `rect_sum`, `cascade_stage`, `detect_multiscale`) | uint8 gray `H×W` → face boxes `(N,4)` | verified (real 20-stage cascade; exact box on synthetic face; max stage-sum diff 3.4e-08) |
| ORB FAST/BRIEF + tree | `reimpl/orb.py` (`fast9`, `orient`, `brief`, `match`, `track_translation`, `tree_traverse`) | gray frame pair → keypoints, descriptors, translation | verified (400 keypoints, 343 inliers, exact `(7,−5)`) |
| ERT landmarks | — (architecture-only placeholder) | face box + gray crop → 68 landmarks | **ARCHITECTURE_ONLY**: weights are in the undecoded 0x53 payload; topology from `Tracking.xml` is used, synthetic landmarks in `pipeline.py` |
| Mesh deformation / liquify | `reimpl/deform.py` (`liquify_field`, `warp_mesh`) | landmarks + displacement → warped image | verified (identity and +3,−2 shift exact) |
| Asset decoding | `reimpl/looksery_assets.py` | `.lns`/XML/tree/triangulation → arrays | verified |
| End-to-end | `reimpl/pipeline.py` | synthetic camera frame → Haar → ORB → deform | runs (ERT placeholder) |
| Deferred DNNs | none | `.dnn.enc` / downloaded lens models | not implemented: no weights ship; external contract only |

Run: `cd reports/snapchat/reimpl && python verify.py` → 11/11 checks, exit 0
(`reimpl/verify.txt`). `ARCHITECTURE_ONLY` applies only to ERT landmark
regression and to the deferred DNN boundaries; Haar, ORB, mesh/liquify and the
container parsers use the real shipped weights/topology.
