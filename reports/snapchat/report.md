# Snapchat 11.70.0.36 (com.snapchat.android) — on-device AI/ML subsystem

## Identity

| field | value |
|---|---|
| APK | `cust/app/customized/partner-com.snapchat.android_200/partner-com.snapchat.android_200.apk` |
| package / version | `com.snapchat.android` 11.70.0.36 (versionCode 84582) |
| size / sha256 | 92,250,637 B / `70147a34fd974522a43a142ad1a5c81a0698b74663a3933e88bde30846a50238` |
| ABI | arm64-v8a only |
| SDK | min 19, target 30 |
| dex / natives | 7 dex (60.5 MB), 14 `.so` (60.5 MB) |
| model assets | **0** (`apk_inventory.py`: `ai_models=0`, `ai_libs=0`) |

## Verdict: does any DNN ship in this APK?

**No. There is no DNN model file of any kind in the APK.** Confirmed by four
independent checks:

1. `tools/apk_inventory.py` reports `model_assets: 0` and `ai_libs: 0`
   (`inventory/inventory.json`).
2. A raw scan of **all 6,869 zip entries** for the TFLite flatbuffer identifier
   `TFL3` finds **0 occurrences** (`evidence/02_model_magic_scan.txt`).  No
   `.tflite`, `.onnx`, `.dlc`, `.dnn`, `.enc`, `.mnn`, `.pt`/`.ptl`, `.caffemodel`
   or `.model` entry exists; the asset extension census is
   `glsl 275, composermodule 145, json 15, js 8, lns 11, xml 1, bin 2, txt 3,
   png/webp/jpg, mesh 2, scn 1, 3d t3d` (`evidence/02_model_magic_scan.txt`).
   The two `assets/**/*.bin` files are the 16 KB ORB vocabulary trees.
3. The only model-shaped blobs are the **classical CV** assets under
   `assets/looksery/core/`: an OpenCV Haar cascade XML (837 KB), an LZC/zstd
   container of ERT regression trees (302 KB) and two 256-bit ORB vocabulary
   trees (16 KB each) — all decoded in `models/` and `ir/`.
4. Every DNN path in the code resolves to a *downloaded* artefact:
   * AI Factory / Cameo (`app.aifactory.*`) reads manifest model paths ending in
     `.dnn.enc` from a `bloops` native component and AES-decrypts them with
     `ModelCrypto` (`sources/java/app.aifactory.ai.modelcrypto.ModelCrypto.java`,
     manifest meta-data `facesegmentation:model_path_face`,
     `landmarksextractor:model_path_aligner`, …).
   * Looksery logs `Failed to download NN face detector model.` and names
     `LENSCORE_NN_FACE_DETECTOR_MODEL` / `LENSCORE_FACETRACKING_FACE_MESH_MODEL`
     as remote assets (`libcamplat`, `native/camplat_relevant_strings.txt`).

Inference **engines** are present or referenced, but without weights:

| runtime | where | shipped? |
|---|---|---|
| MACE (Xiaomi Mobile AI Compute Engine) | compiled into `libcamplat+….so` (`/var/lib/jenkins/…/mace/…` paths, OpenCL/NNAPI/Hexagon/SNPE backends) | yes (code) |
| NNAPI (NDK `ANeuralNetworks*`) | `libcamplat+….so` imports | yes (client) |
| TFLite C API | `libcamplat` strings `TfLiteModelCreateFromFile`, `TfLiteInterpreterInvoke`, `Error loading libtensorflow-lite shared object`; no `TfLite*` dynsym, no `libtensorflow-lite.so` in APK | no (dlopen, absent) |
| TNN | manifest meta-data `tnn_shared_lib=libtnn.so`; `libcamplat` contains `/libtnn.so` | no |
| Hexagon NN | manifest `hexagon_library_name_v6{0,5,6}=libhexagon_nn_skel*.so` | no |
| `bloops` / `aifactory_native_sdk` | manifests `*:native_library=bloops`, `F2FLibraryLoader`/`ModelCrypto` via `NLOader` | no |

## ML/CV component inventory

| # | component | class | evidence |
|---|---|---|---|
| 1 | Haar face detection (Looksery) | (b) classical CV | `HaarCascades/haarcascade_frontalface_alt2.xml`; libcamplat string `/HaarCascades/haarcascade_frontalface_alt2.xml`; OpenCV cascade parser message `weight must be real number. (stage %d, tree %d, node %d, rect %d)` |
| 2 | ERT landmark tracking (68/98 + eyes + Candide) | (b) classical CV | `LiteTrackingData_ert.lns` + `/Tracking.xml`; libcamplat `LS::Tracking::NaturalFeatureTracker`, `LS::NnRegressor`, `LS::ShapeFitter`, `LS::Candide` |
| 3 | ORB natural-feature tracking | (b) classical CV | `tree-ORB-*.bin` (2 files); `/NaturalFeatureTracker/SystemData` string; `LS::Tracking::NaturalFeatureTracker` |
| 4 | Scenarium AR deformation + GLES rendering | (b) classical/GLES | `scenarium/facestretchLiquify.glsl`, `inpainting_gpu.glsl`, `std_vs/std_fs`, 275 GLSL files; Scenarium typeinfo in libcamplat |
| 5 | Camera-pipeline classical CV filters (SnapCut, PortraitFilter, SkyFilter, MomentFilter, ContextFilter, ManyTargetTracker) | (c) Snap native CV | libcamplat JNI `com.snapchat.research.previewcv.PreviewCVInterfaceJNI`, `com.snap.previewtools.tracking.ManyTargetTracker` (157 + 7 exports, `evidence/05_jni_classes.txt`) |
| 6 | AI Factory / Cameo: face neutralisation, face/eye segmentation, landmark aligner+refiner, face2face ("bloops") | (c) Snap native ML | `app.aifactory.*` (classes.dex); encrypted `.dnn.enc` model paths in the manifest; `MACE` backend string; not shipped |
| 7 | ML Kit barcode scanning + GMS face detection | (a) Google ML Kit / GmsCore | `com.google.mlkit.vision.barcode.internal.BarcodeScannerImpl`, `com.google.android.gms.vision.face.internal.client.FaceParcel/LandmarkParcel`, manifest `com.google.android.gms.vision.DEPENDENCIES=barcode,face`; no ML Kit native lib in APK |
| 8 | Memories face clustering, learned search, voice ML, on-device ranking | (d) cloud/server (+ thin client) | `com.snap.memories.lib.faceclustering.job.FaceClusteringJob`; `libclient.so` `learned_search::ModelCoordinator`, `voiceml::IntentsModel/KeywordsModel`, `downloadModel`, `getRemoteModelStatus` |
| 9 | Lens runtime itself (`LSCoreManagerWrapper`, render driver, ARCore) | (b) Looksery | 350-method Java wrapper, `LSNativeLibraryLoader`, `libarcore_sdk_*` |

## The lens/camera pipeline (traced)

Java surface (`sources/java/com.looksery.sdk.FaceTrackerWrapper.java`):

```
com.looksery.sdk.FaceTrackerWrapper
  Configuration.CORE_ASSETS_PATH = "asset:looksery/core"
  LandmarksType = {Landmarks68, LandmarksWithEyes}
  nativeInit(Mode, LandmarksType, ExternalFaceDetectorFactory) / nativeTrackImage(Bitmap) -> float[][]
com.looksery.sdk.LSCoreManagerWrapper
  nativeProcessFrame / nativeProcessFrameToTexture / nativeProcessBitmap
  nativeProcessTextureToTexture / gesture + audio natives
```

Native (`libcamplat+….so`, `native/camplat_relevant_strings.txt`):

```
LS::CoreManager::processFrame(LS::Image*, SharedTexture, Timestamp, …)
LS::CoreManager::processFrameToTexture(...)
  └─ LS::FaceDetectorStage        "/HaarCascades/haarcascade_frontalface_alt2.xml"
     ├─ LS::FaceDetector          (Haar cascade, OpenCV parser)
     └─ LS::Tracking::Face::HybridAccuracyFaceDetector
           └─ optional NN face detector ("Failed to download NN face detector model.",
              LENSCORE_NN_FACE_DETECTOR_MODEL) — downloaded, not in APK
  └─ LS::Tracking::NaturalFeatureTracker
        "/NaturalFeatureTracker/SystemData/tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin"
        (ORB vocabulary tree, branching 20, depth 2)
  └─ TrackingStage graph from "/LiteTrackingData_ert.lns" (see below)
  └─ LS::Scenarium (mesh + GLES): facestretchLiquify.glsl / liquify.glsl /
     inpainting_gpu.glsl / std_vs(std_fs).glsl, triangulation.txt
  └─ render via EGL/GLESv2 (libcamplat DT_NEEDED: libEGL, libGLESv2)
```

The ERT pipeline is declared explicitly inside the container
(`/Tracking.xml`, extracted to `models/looksery_ert/Tracking.xml`):

```xml
<FaceDetectorStage Output="Faces"/>
<TrackingStage Output="FaceLandmarks68Unblurred" DependsOn="Faces"
               RegressorPath="FaceRegressor.bin" ConfidenceThreshold="0.76"/>
<TrackingStage Output="EyeLandmarks"  DependsOn="FaceLandmarks68Unblurred" RegressorPath="EyesRegressor.bin"/>
<TrackingStage Output="FaceLandmarks68" DependsOn="EyeLandmarks" RegressorPath="DenoiseRegressor.bin"/>
<TrackingStage Output="Candide"           DependsOn="FaceLandmarks68" RegressorPath="CandideRegressor.bin"/>
<TrackingStage Output="CandidePerspective" DependsOn="FaceLandmarks68" RegressorPath="CandidePespectiveRegressor.bin"/>
```

so one frame is: **camera OES texture → RGBA/gray → Haar detection → ORB tracking of the
face region → chained ERT regressors (68 landmarks → eyes → denoise → Candide-3
3-D shape → perspective) → Scenarium mesh deformation (liquify/face-stretch) →
GLES render**.  The same engine also drives `LS::Candide`/`LS::ShapeFitter` 3-D fitting
and, for high-accuracy modes, an optional downloaded NN detector.

## Data-path addendum: camera → lenses, contracts, external boundaries

Full 9-section contract: [`PIPELINE.md`](PIPELINE.md). New evidence:
`evidence/07_camera_lens_trace.txt`, `evidence/08_external_boundaries.txt`,
`native/jni_binding_table.txt`, and the decompiled sources listed in
`sources/index.md`.

### Camera frame → Haar/ERT/ORB → AR render (confirmed path)

```
Android camera (Camera2/GL; com.snap.camera.* classes present)
  └─ defpackage.rk6.a(...)                      GL thread, per camera frame
       target = EXTERNAL_OES ? 36197 : 3553     ← confirmed ternary
       └─ defpackage.Hs8.b(texId, target, tsNs, mat4, mat4, frameType)
            [LensesCameraFeatureProcessingCoreSub]
            └─ LGoa{a=texId,b=target,c=tsNs,d/e=mat4,f/g=inSize,h/i=screen,
                     j/k=fov,l=orientation,m=front,n=state,o=crop}
                 └─ defpackage.WA4.e(Object)    render lambda
                      setInputImageSize / setScreenSize
                      setCameraParams / setFrontCameraEnabled / setRecordingState
                      └─ LSCoreManagerWrapper.processFrameToTexture(...)
                           └─ nativeProcessFrameToTexture @0x767500
                                └─ LS::CoreManagerWrapper::processFrameToTexture
                                     └─ LS::CoreManager::processFrameToTexture
                                          └─ LS::TrackingManager::track(...)
                                               ├─ LS::FaceDetectorStage  (Haar)
                                               ├─ LS::TrackingStage graph (ERT)
                                               ├─ LS::Tracking::NaturalFeatureTracker (ORB)
                                               └─ LS::Scenarium (mesh + GLSL)
                                                    └─ result RGBA texture → drawTexture @0x767a00
```

Buffer contract: camera frames are **GL textures**, `GL_TEXTURE_EXTERNAL_OES`
(36197) for the camera/SurfaceTexture or `GL_TEXTURE_2D` (3553) for ordinary
video; timestamps are **nanoseconds** (`TimestampConverter` divides by `1e9`
and applies a one-time clock offset); two `float[16]` matrices accompany each
frame; size is passed as width/height and the engine caps processing at
**600×800** (480 k px, `Size.optimizeForProcessing`). `nativeCreateTexture`
(0x767338) creates the OES texture (`glBindTexture(36197)`,
`GL_CLAMP_TO_EDGE`, `GL_LINEAR`). Processing is synchronous on the EGL render
thread (`EglContextChecker` + thread-id check), with optional async tracking.

Standalone still-image API: `FaceTrackerWrapper.nativeTrackImage` @0x754458
takes a `Bitmap` and returns `float[][]` flat `x,y` landmarks (68 or
`LandmarksWithEyes`), alongside `nativeInit` @0x753bd8 and
`nativeSetMinFacePixelHeight`/`nativeSetNumberOfFacesToTrack`
(@0x754424/@0x754400).

Data contracts (details/refs in `PIPELINE.md` §3–§5): Haar runs on a uint8 gray
integral image with a 20×20 window and the shipped 20-stage/1047-tree alt2
cascade; ERT consumes a per-face normalized crop and runs
`FaceRegressor` (threshold 0.76) → `Eyes` → `Denoise` → `Candide` →
`CandidePerspective`; ORB uses FAST-9, radius-10 orientation and steered
BRIEF-256 matched through the shipped 20×2 vocabulary tree; Scenarium warps a
118/124-triangle mesh (indices 0–75) and applies the exact
`facestretchLiquify.glsl` displacement
(`pow(clamp(d²/r², 1/1280, 1), coef) − 1`, inverse sampling). The ERT patch
size and packed tree layout remain unknown (payload tag `0x53`, undecoded).

**No DNN is invoked by this path.** The APK contains no DNN weights
(`model_assets=0`, TFL3 hits=0); the optional NN face detector
(`LENSCORE_NN_FACE_DETECTOR_MODEL`, downloaded from
`https://perception.sc-cdn.net`) and every AI Factory `.dnn.enc` are deferred.

### External / deferred boundaries

| Boundary | Entry | What crosses | Local vs remote |
|---|---|---|---|
| ML Kit barcode + GMS face | `BarcodeScannerImpl`, `MobileVisionBase`, `FaceParcel`/`LandmarkParcel` | camera image (ImageProxy/Frame) + options → GmsCore; `Barcode`/`FaceParcel` results back | models/executors in Google Play services; on-device, not in APK (inferred for transport) |
| AI Factory / Cameo | `FaceNeutrality`, `FSFaceSegmentation`, `LandmarksExtractor`, `DownloadBloopsAiModelsDurableJob` | encrypted `.dnn.enc` model bytes + URL/version from Snap CDN; key/IV from native `bloops` | decrypt + MACE/TNN inference local; component/models absent |
| learned search / VoiceML | `ModelCoordinator.getModel/updateLocalModel`, `ModelFileBuffer`, `IVoiceMLSDK` | model URL/version + model bytes; PCM `ByteBuffer` for ASR | model mmap’d locally (`address+size`), inference local |
| Memories face clustering | `FaceClusteringJob` (`MEMORIES_FACE_CLUSTERING_JOB`) | media/face metadata to Snap backend | no local clustering model (inferred server-side) |

## Looksery asset formats (decoded)

### 1. `haarcascade_frontalface_alt2.xml` — confirmed
OpenCV `opencv-haar-classifier`, Lienhart tree-based 20×20 gentle AdaBoost:
20 stages, **1047 trees** (all depth-2 stumps), 2094 nodes, **4535 weighted
rectangles**, no tilted features; stage thresholds 0.3507 … 53.7556, node
thresholds −0.425 … 0.709.  `ir/haar_cascade.json`, `models/.../HaarCascades/`.

### 2. `LiteTrackingData_ert.lns` — container confirmed, payload packed
"LZC" v1 container (`ir/lzc_container.json`):

```
0x00 "LZC\0" | u32 version=1 | u32 entries=6 | u32 name_table_size=290
0x18 u32 uncompressed=383911 | u32 compressed=302059 | 32 zero bytes
0x40 u32 2 | u32 218
0x48 records: u32 namelen, name, 4 pad bytes, u32 size, u32 offset
trailer: u32 1, u32 compressed_size(302059)
payload: one zstd frame (magic 28 b5 2f fd) -> 383911 B
```

Entries: `/Tracking.xml` 1284, `/FaceRegressor.bin` 186452,
`/CandideRegressor.bin` 17172, `/DenoiseRegressor.bin` 1887,
`/CandidePespectiveRegressor.bin` 99979, `/EyesRegressor.bin` 77137; the six
sizes tile the decompressed stream exactly.

The five `*.bin` regressors are a **proprietary packed format, not decoded**:
first byte `0x53` ('S') in all five, mixed varint/int32/IEEE-754-double fields
(clean doubles 0.05, 0.95, 5.0, … near 0x70 in `DenoiseRegressor`), no zstd/gzip
header, entropy 4.8–7.9.  They are ERT (Kazemi–Sullivan) tree ensembles used by
`LS::NnRegressor`; the exact node encoding remains an unknown.

### 3. `tree-ORB-*.bin` — confirmed
Bare protobuf (no file header):

```
Tree    { Node root = 1; repeated Node nodes = 2; }
Node    { Feature feature = 1; repeated Node children = 2; }
Feature { bytes signature = 1; }        // 32 bytes = 256-bit ORB descriptor
```

`tree-ORB-sharp-b-20-d-2-r-10-numSig-195777265.bin`: root sentinel (all-zero),
20 level-1 centroids, 400 leaves (`b-20` branching, `d-2` depth, `r-10`
orientation radius).  16,019 B each; the file is exactly `12 90 7d` + 16,016 B.
`ir/orb_tree.json`.

### 4. Candide-3 shape model — obfuscated/encrypted (inferred)
`Candide/candide3reduce` (18,436 B) and `candide3reduce14AU` (20,200 B) share a
**5,612-byte identical prefix** and diverge afterwards, entropy 7.04, no
recognisable float/int structure; libcamplat logs `Candide-3 has been loaded
successfully` and references `/Candide/`.  Treated as an encrypted/obfuscated
base + 14-action-unit variant (`ir/candide_model.json`); not decoded, no DNN.

### 5. Landmark topology & triangulations — confirmed
`triangulation.txt` 118 triangles over 68 points, `triangulation_with_mouth.txt`
124 triangles, `borderTriangulation.txt` border fan; used by the Scenarium mesh
warp.  `ir/triangulation.json`.

### 6. Lens packages (`assets/lenses/**.lns`, 11 files) — confirmed
Same LZC container.  Contents are only GLSL, JS, `scene.scn`, PNG/JPG/PVR
textures (e.g. `43675310939.lns`: 15 entries, 165,604 B uncompressed).  **No
model or tensor entry in any bundled lens.**

## AI Factory / Cameo (Snap's own DNN stack) — models absent

* Java: `app.aifactory.ai.faceneutrality.FaceNeutrality` (neutraliser + classifier),
  `...facesegmentation.FSFaceSegmentation` (main + eyes, `EyesSegmentationNN`),
  `...landmarksextractor.LandmarksExtractor` (aligner + refiner),
  `...face2face.*` (F2F video reenactment), `...modelcrypto.ModelCrypto`.
* Backend selector is the literal string `"MACE"` passed to the ctor
  (`FaceNeutrality(..., "MACE")`, `FSFaceSegmentation(..., "MACE")`).
* Models must end in `.enc` and are AES-decrypted (`ModelCrypto.decrypt`,
  key/IV/algorithm from native `internalInit()`), then passed to
  `native internalInitInterpreter(byte[], int, String)`.
* Native component `bloops` via `NLOader` (`manifests`: `aiinternals:native_library`,
  `face2face:native_library`, `faceneutrality:native_library`, … all `bloops`),
  plus manifest `tnn_shared_lib=libtnn.so` and Hexagon skels — none shipped.
* Manifest model paths (all under the downloaded component):
  `/face_segmentation_dnn/FaceSegmentation_02.07.2019_smaller_8bit/…dnn.enc`,
  `/face_segmentation_dnn/EyesSegmentation_06.06.2019_ver2_8bit/…dnn.enc`,
  `/face_neutrality_model_dnn/FaceNeutralityModel_16.04.2019_small_model_8bit_new_format/…dnn.enc`,
  `/face_neutrality_model_dnn/FaceNeutralityClassifier_24.05.2019_classifier_8bit_new_format/…dnn.enc`,
  `/landmarks_model_dnn/LandmarksAlignerModel_v11/cnn_aligner_v113_best_inference_fused_8bit.dnn.enc`,
  `/landmarks_model_dnn/LandmarksRefinerModel_v11/cnn_refiner_v118_best_inference_fused_8bit.dnn.enc`.

## ML Kit, Google, cloud

* ML Kit: only `BarcodeRegistrar`/`BarcodeScannerImpl` and
  `VisionCommonRegistrar` are compiled in; the manifest advertises
  `com.google.mlkit.vision.DEPENDENCIES=barcode` and
  `com.google.android.gms.vision.DEPENDENCIES=face,barcode`, i.e. the actual
  models/executors live in GmsCore/Play services.  **External service contract**,
  not part of this APK.
* GMS vision face: only the AIDL parcels (`FaceParcel`, `LandmarkParcel`) ship —
  again a GmsCore service contract.
* Cloud/server: `com.snap.memories.lib.faceclustering.job.FaceClusteringJob`
  (Memories clustering), `libclient.so` `learned_search::ModelCoordinator`
  (`downloadModel`, `getRemoteModelStatus`, `ModelFileBuffer`) and
  `voiceml::{IntentsModel,KeywordsModel}` — model bytes are fetched at runtime
  (server-driven), nothing embedded.

## Reimplementation (`reimpl/`)

| file | stage |
|---|---|
| `looksery_assets.py` | Haar XML parser, LZC v1 reader (+ zstd via CLI), protobuf ORB-tree decoder, triangulation |
| `haar.py` | JAX integral-image Haar cascade: batched rectangle sums, area normalisation, depth-2 tree traversal, per-stage survivor pruning, multi-scale scan, IoU grouping |
| `orb.py` | JAX FAST-9 + NMS, intensity-centroid orientation (r=10), steered BRIEF-256, Hamming matching (ratio + mutual), translation vote, shipped-tree traversal |
| `deform.py` | `facestretchLiquify.glsl` displacement (exact) + landmark-triangle piecewise-affine warp |
| `pipeline.py` | camera-frame → Haar → ORB → (ERT placeholder) → deform, on synthetic frames |
| `verify.py` | 11 checks, writes `verify.txt` |
| `ref/ref_haar.cpp` | OpenCV 5 reference detector used for the cross-check |

Run: `cd reimpl && python verify.py` → **11/11 checks passed**, exit 0
(`reimpl/verify.txt`).  Notable measured results:

* Haar cascade: box `(44,34,20,20)` on the 96×96 frame, exact patch origin, 21,414
  raw windows; noise frame → 0 boxes after 7 stages.
* JAX vs independent scalar evaluator: max stage-sum difference `3.4e-08`.
* Cross-check with OpenCV 5.0 (`ref_haar.cpp`): the same 20×20 synthetic template
  is accepted by OpenCV at base scale (`detections=1`, box `9 9 23 23` inside a
  60×60 canvas) — the core cascade semantics (features, traversal, `/ (w·h)`
  normalisation, integral image) agree.  At scale > 1 OpenCV 5's rewritten
  `xobjdetect` evaluator rounds scaled rectangle coordinates differently from
  the classic `calc_offsets` path embedded in libcamplat; on a 240×240 synthetic
  face it reports `(28,33,184,184)` (a group average) while `haar.py` reaches
  11/20 at that exact box and 17/20 at the nearest ladder window `(31,34,179)`.  Recorded as an unknown below (does not affect the DNN verdict).
* ORB: 400 FAST-9 keypoints, 344 matches, 343 inliers, recovered translation
  exactly `(7, −5)`; 400-leaf shipped tree, 19/20 level-1 centroids map to
  themselves under Hamming traversal.
* Deformation: liquify with coefficient 0 is identity (`1.9e-06`); triangle mesh
  identity warp `max|diff| = 0`; commanded landmark shift `(3,−2)` moves the
  content centroid by exactly `(−2, 3)` in (row, col).

## Unknowns / limits

1. ERT regressor binary layout (`FaceRegressor.bin` etc.) is not decoded; the
   landmark regressors are therefore `partial` (container extracted, weights
   packed) and the reimplementation uses the shipped topology with synthetic
   landmarks.
2. `candide3reduce*` are encrypted/obfuscated; their plaintext (vertex/shape/
   action-unit tables) is unavailable.
3. Whether bundled lenses ever exercise the TFLite dlopen path in libcamplat is
   unknown; no TFLite model ships.
4. Which runtime the `bloops` component uses for its `.dnn.enc` models (MACE
   string vs `libtnn.so`) is inferred from the manifest/ctor string, not from
   a shipped binary.
5. OpenCV-5 sub-pixel rect rounding differs from the classic evaluator at
   scale > 1 (see cross-check); the classic convention is used here.
6. Lens packages may carry `.crypt.glsl` resources and remote assets
   (`LSRemoteAssetsWrapper`); their decryption keys are not in the APK.
7. The exact Camera2 capture delegate/session that produces the lens OES
   texture is obfuscated. The lens-side callback (`Hs8.b`) and frame dispatcher
   (`rk6.a`) are identified, but the camera delegate and the origin of rotation
   (CameraCharacteristics vs display rotation) are inferred from the frame
   state rather than traced to a concrete capture session.
8. Haar runtime defaults (scale factor, minimum face size,
   `minFaceInImageRatio`) are enforced natively; the exact numeric defaults
   were not extracted.
9. ERT patch size and tree/node layout inside the `0x53`-tagged regressor
   payloads remain undecoded.
10. ORB robust-estimator/RANSAC parameters and `TrackingManager` queue drop
    policy are internal and were not recovered.

## Reproduction

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
$PY tools/apk_inventory.py "$APK" --out reports/snapchat/inventory --extract-models --extract-libs
$PY tools/dex_find.py "$APK" --classes-only --regex 'aifactory|looksery|mlkit|vision\.face'
$PY tools/decompile_classes.py "$APK" --regex 'app\.aifactory\.ai\.(faceneutrality\.FaceNeutrality|facesegmentation\.FSFaceSegmentation|landmarksextractor\.LandmarksExtractor|modelcrypto\.ModelCrypto)$' --out /tmp/snap_dex_aif --max 6
$PY tools/elf_info.py <libcamplat.so> --json reports/snapchat/native/camplat_elf.json
$PY reports/snapchat/reimpl/dump_ir.py
cd reports/snapchat/reimpl && $PY verify.py
```
