# Decompiled sources (targeted, jadx --single-class)

| file | why |
|---|---|
| `java/com.looksery.sdk.FaceTrackerWrapper.java` | Java entry point of the Looksery face tracker (native `nativeTrackImage(Bitmap)`, `asset:looksery/core`, `Landmarks68`/`LandmarksWithEyes`). |
| `java/com.looksery.sdk.LSNativeLibraryLoader.java` | Loads the `LENSCORE_COMPONENT_NAME` native component through `NLOader` (the lens core ships inside libcamplat). |
| `java/app.aifactory.ai.faceneutrality.FaceNeutrality.java` | Snap "AI Factory" / Cameo inference wrapper: `InterpreterCreator`, `MACE` backend string, encrypted model blobs, native `internalInitInterpreter`. |
| `java/app.aifactory.ai.facesegmentation.FSFaceSegmentation.java` | Face/eye segmentation models, `.enc` model requirement, `MACE` backend. |
| `java/app.aifactory.ai.landmarksextractor.LandmarksExtractor.java` | Landmark aligner/refiner models (`.dnn.enc` paths from manifest). |
| `java/app.aifactory.ai.modelcrypto.ModelCrypto.java` | AES `Cipher` decrypt of downloaded model blobs; key/IV/Alg come from native `internalInit()`. |
| `java/app.aifactory.ai.face2face.F2FLibraryLoader.java` | Loads the `face2face_android` / `aifactory_native_sdk` component (not shipped). |
| `java/classes/com.looksery.sdk.LSCoreManagerWrapper.java` | 350-method lens core wrapper; `nativeProcessFrameToTexture`, `nativeProcessTextureToTexture`, `nativeCreateTexture`, `setCameraParams/FrontCameraEnabled/InputImageSize/TrackingSurface`, EGL-thread checks. |
| `java/classes/Xj4.java`, `Zfe.java` | Snap's `DefaultLensCoreRendererProvider` / `SharedLensCoreRendererProvider`: create the wrapper, set 59° FOV and call `processTextureToTexture` for video/post-capture. |
| `java/classes/Hs8.java` | `LensesCameraFeatureProcessingCoreSub` camera callback `b(texId, texTarget, tsNs, mat4, mat4, frameType)` → `LGoa` → render pipeline. |
| `java/classes/rk6.java` | Camera frame dispatcher; selects `GL_TEXTURE_EXTERNAL_OES (36197)` vs `GL_TEXTURE_2D (3553)` and calls the lens callback. |
| `java/classes/com.looksery.sdk.TimestampConverter.java` | Camera ns→seconds conversion and clock offset correction. |
| `java/classes/com.looksery.sdk.domain.Size.java` | `OPTIMAL_FOR_PROCESSING = 600×800`; processing-size scaling rules. |
| `java/classes/com.looksery.sdk.media.ExternalTextureStream.java` | External OES/2D frame source contract. |
| `java/classes/com.snap.bloops.data.DownloadBloopsAiModelsDurableJob.java` | `DOWNLOAD_BLOOPS_AI_MODELS` durable job (deferred `.dnn.enc` download). |
| `java/classes/com.google.mlkit.vision.barcode.internal.BarcodeScannerImpl.java` | ML Kit barcode wrapper; the detector/model lives in GmsCore. |
| `java/classes/com.google.mlkit.vision.common.internal.MobileVisionBase.java` | ML Kit detector lifecycle base (thin, no model code). |
| `java/classes/com.google.android.gms.vision.face.internal.client.FaceParcel.java` | GMS face AIDL parcel fields (id/type/center/size/euler/landmarks/probabilities). |
| `java/classes/com.snap.memories.lib.faceclustering.job.FaceClusteringJob.java` | `MEMORIES_FACE_CLUSTERING_JOB` durable job (server-side clustering boundary). |
| `java/classes/com.snapchat.client.learned_search.*.java` | `ModelCoordinator` / `ModelFileBuffer` / `ModelMetaData` / `StatusEnums`: downloaded model bytes exposed as native address+size. |
| `java/classes/com.snapchat.client.voiceml.IVoiceMLSDK.java` | `asrTranscribe`/streaming/TTS contract over PCM `ByteBuffer`. |
| `java/classes/F.java` | Manually decompiled (`defpackage.F`, classes.dex); case-18 provider constructs the `WA4` frame lambda. |

Decompiled with:

```
python tools/decompile_classes.py "$APK" --regex '<class>' --out reports/snapchat/sources/java --max 6 --timeout 180
```

`com.looksery.sdk.LSCoreManagerWrapper` was previously truncated at 180 s; it
decompiles in ~25 s with `--timeout 420`. `defpackage.F` needs a manual
`jadx --single-class 'defpackage.F'` call because case-insensitive matching
collides with `defpackage.f`; jadx reports two residual errors but writes the
class body (`java/classes/F.java`). The JNI binding table was recovered from
relocated `JNINativeMethod` arrays and is in `native/jni_binding_table.txt`.
