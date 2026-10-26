# Data-pipeline integration contract — `miui_biometric` (`com.miui.face`, `Biometric.apk`)

Two pipelines run through the same native SDK instance:

* **A. authenticate (unlock)** — `miui.face.FaceService` CODE_AUTHENTICATE → camera →
  `st_auth_unlock` → unlock or app-status code.
* **B. enroll** — `miui.face.FaceService` CODE_ENROLL → camera → `st_auth_enroll` →
  `template_file.data`.

Both share one process (`com.miui.face`), one `FaceAuthManager` singleton
(`com.ts.faceauth.FaceAuthManager`), one `FaceUnlock2DInterFace` C++ object
(`libstfaceunlockocl.so`, global at `0x5ed2000+0xf68`) and one 2-thread native
worker pool created at `st_auth_init`.

Legend for every row: `confirmed` = read from an artifact (file/offset/class/method),
`inferred` = derived from symbols/strings/structure, `unknown` = not recoverable.

---

# Pipeline A — authenticate (unlock)

## A.1 Data sources / triggers

| item | value | confidence | evidence |
|---|---|---|---|
| trigger | `com.android.systemui` binder call `CODE_AUTHENTICATE (3)` on service `miui.face.FaceService` | confirmed | `sources/faceunlock/classes/com.miui.face.MiuiFaceService.java` `onTransact` case 3 → `authenticate(IBinder,long,int,IBinder,int,String,int)`; service registered by `MiuiFaceService.init()` → `ServiceManager.addService("miui.face.FaceService", …)` |
| warm-up | `CODE_PRE_INIT_AUTHEN (2)` → `FaceAuthenHelper.tryPreInitAuth()` → `FaceUnlockManager.initAll()` only (no camera) | confirmed | `MiuiFaceService.preInitAuth`, `FaceAuthenHelper.tryPreInitAuth` |
| screen-on release | `BroadcastReceiver` for `android.intent.action.USER_PRESENT` releases the pre-initialised client | confirmed | `MiuiFaceService$ScreenReceiver.onReceive` |
| frame source | front camera via **Camera2** (`CameraManager.openCamera`, `TEMPLATE_PREVIEW`), id = first camera with `LENS_FACING == 0` | confirmed | `Camera2Util.getFrontFacingCameraId`, `Camera2Util.1.onOpened` (`createCaptureRequest(1)`) |
| frame sink | `ImageReader` (width×height, format **35 = `ImageFormat.YUV_420_888`**, `maxImages = 1`) → `Yuv.getNV21Bytes()` → `IPreviewCallback.onPreviewFrame(byte[], cameraId)` | confirmed | `Camera2Util.startPreview`, `Camera2Util.m26x64ce4d8c`, `com.miui.image.Yuv` |
| loop | `FaceUnlockManager.onPreviewFrame` → `FaceAuthManager.compare(byte[],w,h,angle,true)` | confirmed | `FaceUnlockManager.java:148` |
| model load timing | on `initAll()` (pre-init or first auth), **before** the camera opens | confirmed | `FaceUnlockManager.initAll` → `FaceAuthManager.initAll("model1","model2")` → `initLicense` + `createFaceAuth` |

## A.2 Transport and format

| property | value | confidence | evidence |
|---|---|---|---|
| camera API | Camera2 `CameraCaptureSession.setRepeatingRequest` on a `HandlerThread("auth_worker")` | confirmed | `Camera2Util.startPreview`, `BaseFaceUnlockManager.initAuthenThread` |
| resolution | **640 × 480** (hard-coded `AbstractCameraUtil.width/height`, never overridden) | confirmed | `AbstractCameraUtil.java:15-16`; `Camera2Util.startPreview` uses `this.width/this.height` |
| capture format | `YUV_420_888` (35); converted to **NV21** in Java | confirmed | `Camera2Util.startPreview` (`ImageReader.newInstance(w,h,35,1)`), `Yuv` ctor (`require(format == 35)`), `Yuv.getNV21Bytes` |
| NV21 layout | Y plane (pixelStride 1, rowStride preserved) followed by interleaved **V then U** (VU) at half resolution | confirmed | `Yuv.getNV21Bytes`: writes `bArr3` (v) then `bArr2` (u) per 2-byte pair |
| buffer size | `w*h*bpp/8` = 460,800 B for YUV_420_888 @640×480 | confirmed | `Yuv.size()` |
| stride passed to native | `w` (NV21 is not in the ×3/×4 branch of `getStride`) | confirmed | `FaceAuthManager.getStride` |
| pixel-format enum | `CvPixelFormat.NV21 = 3` → native `ST_PIX_FMT_NV21 = 3` (DWARF) | confirmed | `CvPixelFormat.java`, `native/dwarf_info.txt` enum 0x9eef |
| rotation / orientation | `angle` is a **constant 90** in `AbstractCameraUtil` (never recomputed from `SENSOR_ORIENTATION`); `getFaceOrientation(90)` → `FaceOrientation.RIGHT = 8` → `st_auth_set_face_orientation(8)` | confirmed | `AbstractCameraUtil.java:17`; `FaceAuthManager.getFaceOrientation`; `FaceAuthManager.compare` |
| mirroring | none in the Java path (byte[] forwarded unmodified; no `mirror`/`flip` in any decompiled class) | confirmed (Java) / unknown (native) | grep of `sources/`; Caffe graph has a `Reverse` layer type and `hpc::pplwrapper::ReverseParameter` kernels, but whether the shipped nets use them is inside the encrypted model |
| timestamp | **none**: `Image.getTimestamp()` is never read; the frame arrives as a bare `byte[]`. All timing is `System.currentTimeMillis()` (Java) / `std::chrono::steady_clock` (native logs only) | confirmed | `Camera2Util.m26x64ce4d8c`, `Yuv` (no timestamp member) |
| cadence | sensor default repeating request; each frame takes `acquireNextImage()` + `close()`, backlog 1 ⇒ frames are silently dropped while the SDK is busy. Auth additionally skips frames: `onPreviewFrame` returns early while `mIsDetecting` and skips every other frame when no face was seen (`mSkipIfNotFound`) | confirmed | `Camera2Util.m26x64ce4d8c`; `FaceUnlockManager.onPreviewFrame` |
| thread/queue | delivery on `HandlerThread("auth_worker")`; `compare()` runs **synchronously on that Looper thread** (native blocks until its worker pool finishes) | confirmed | `Camera2Util.startPreview(..., mCameraHandler)`; `FaceUnlockManager.onPreviewFrame` (no post) |
| extra stream | `Build.DEVICE == "munch"` (Poco F4 codename) is a member of `device_need_operating_mode` ⇒ auth sessions use session `OPERATING_MODE = 32774`; it is not in `need_more_stream_in_auth` / `virtual_light_device` / `get_frame_nonsequence` (array membership confirmed; codename→device mapping inferred) | confirmed | `evidence/resources_arrays.txt`; `DeviceUtils`, `Camera2Util.createCaptureSession` |

## A.3 Preprocessing

Order verified in `stfaceunlock::FaceDetectionClass::Detect` (0x144be8) and
`FaceUnlock2DInterFace::AuthUseId` (0x14b3a8) — see `native/big_FaceDetectionClass_Detect.s`,
`native/big_AuthUseId.s`.

| # | operation | where | confidence |
|---|---|---|---|
| 1 | `st_image_t` → `cv_image_t` colour conversion (`NV21 → cv_pixel_format`, Y channel kept, chroma converted on demand) | `ImageHepler::GetCvImageFromStImage` @0x140ee8 (called 1st from `FaceDetectionClass::Detect`) | confirmed (call order) / inferred (exact colour matrix) |
| 2 | conditional downscale for the detector: `ImageHepler::ShouldResize(int,int,unsigned,int&,int&,float&)` @0x140d1c then `ImageHepler::ResizeImage` @0x140c08 (bilinear) | same function, before detection | confirmed |
| 3 | rotate to upright using the orientation bitmask: `ImageHepler::RotateImage(cv_image, in_orient, out_orient, unique_ptr&)` @0x14154c | `FaceDetectionClass::Detect` | confirmed |
| 4 | ROI: auth passes **no ROI** (`FaceAuthNative.setRoi(null, rc)` → `st_auth_set_roi(NULL)`); enroll passes the guided rect, pre-rotated in Java by `FaceAuthManager.RotateMaskRect` and again in native by `FaceUnlock2DInterFace::RotateRect`/`ImageHepler::RotateRect` | `FaceAuthManager.compare` / `saveFeature`, `CheckFaceInfo` | confirmed |
| 5 | per-image mean/scale: `CommonFun::GetMean(unsigned char*, int,int,int, cv_pixel_format, cv_landmarks_t const*, float*, float*)` @0x143398 | `FaceDetectionClass::Detect`, `AuthUseId` (crop path) | confirmed (call) / unknown (numeric constants live in encrypted `meta.conf`) |
| 6 | detector input: square pad to the Hunter 360-px input (`DetectorHunter::PadBlobInput`) | `FaceDetectionManager::Detect` → `FaceDetectionClass` | inferred (from model name `..._Gray_ppl_360_...` + `PadBlobInput`) |
| 7 | align + crop: `FaceAlignManager::Align(cv_image, cv_image*, st_rect_t*, count, orientation, scale, cv_landmarks_t**, st_rect_t**, int*, int)` @0x129528 → 106 pts, then similarity transform to the per-net crop | `FaceDetectionClass::Detect` | confirmed (call) / inferred (crop size) |
| 8 | pose: `FaceCalcPose::CalcPose(st_pointf_t const*, int, float*, float*, float*, float*)` @0x129a68 from the 106 pts | `FaceDetectionClass::Detect` | confirmed |
| 9 | occlusion: `FaceAlignManager::GetOcclusionStatus`, `FaceAlignInterFace::CopyLandmarks` | `FaceDetectionClass::Detect` | confirmed |
| 10 | quality: `FaceAttributeQuality::GetAttribute(st_image_t*, st_face_t*, float*)` @0x17c798 (MTNet) | `CheckFaceInfo` | confirmed |
| 11 | eye state: `FaceUnlock2DInterFace::CheckOcularInfo(st_image_t*, unsigned, st_face_t*, bool)` → `FaceoOcular`/`AlignmentEyeState` | `AuthUseId`, `Enroll` | confirmed |

## A.4 Model invocation

| stage | net (blob) | runtime | confidence |
|---|---|---|---|
| detect | `M_Hunter_CommonFace_Gray_ppl_360_9_2_5_v2_origin_model` | Caffe-lite (`caffe::Net`) on SenseTime `hpc`/`pplwrapper`, Adreno OpenCL (`__*_bcl_binary_data`) | confirmed (symbols + model blobs) |
| align | `M_Align_occlusion_106_1_19_3_model` (+ `_360_1_7_1`) | same | confirmed |
| pose | `M_Align_CalcPose_Ann_106_2_3_0_model` | same | confirmed |
| eye state | `M_Align_Deepface_Eye_State_Analysis_3_7_8_model` | same | confirmed |
| quality | `M_Attribute_MTNet_Quality_1_0_0_model` | same | confirmed |
| liveness | `IM_RGB_Liveness_General_Full_FP32_10_1_65_half_compression_v2_origin_model` | same | confirmed |
| embed | `M_Verify_MobileNetV2T6_Common_3_135_0_half_compression_v2_origin_model` | same | confirmed |

* Session creation: `st_auth_init` (0x10e3e8) constructs `FaceUnlock2DInterFace`, calls
  `CreateDetectHandle`, `CreatehacknessHandle`, `CreateVeriyfHandle`, `CreateOcularHandle`,
  `SetVerifyThrehold(DEFAULT_VERIFY_THRESHOLD = 0.905f)`,
  `SetLivenessThrehold(DEFAULT_LIVENESS_THRESHOLD = 0.9f)`,
  `SetTemplateFilePath(dir)`, `StartThread()` — **confirmed** by
  `native/big_st_auth_init.s` and the float check
  (`0x4f2c090` = `0x3f67ae14` = 0.905f, `0x4f2c08c` = `0x3f666666` = 0.9f).
* Models are loaded **by name** from `.data` blobs via
  `protector::ModelLoader::LoadByName` / `TarModelResource`; the payload is AES/RC4-encrypted
  (`STEF` container) so tensor names/shapes are not statically available — **confirmed** (§5.3 of `report.md`).
* Buffer binding: the Java `byte[]` is pinned with `GetByteArrayElements` and handed to C++ as
  `st_image_t.data` (direct pointer, no copy); **confirmed** `native/jni_all.c:360`
  (`java_stimage_to_st_image_t`).
* Concurrency: liveness and embedding run as tasks on the two native threads created by
  `StartThread` (`ThreadManager<FaceVerifyManager::ExtractFeature>` and
  `ThreadManager<FaceHacknessManager::HacknessDetect>`), submitted through
  `BlockingQueue<std::function<int()>>::push` and awaited with
  `std::condition_variable::wait`; **confirmed** `native/big_StartThread.s`,
  `native/big_AuthUseId.s`.

## A.5 Output postprocessing

| step | detail | confidence | evidence |
|---|---|---|---|
| per-face geometry checks | `CheckFaceInfo` iterates faces, `RotateRect`, block/quality/eye/mouth/nose checks, returns `st_face_t` list + quality value | confirmed | `native/big_CheckFaceInfo.s` (calls `FaceAttributeQuality::GetAttribute`, `FaceDetectManager::ReleaseDetectionResult`) |
| liveness | `FaceHackness2D::HacknessDetect(st_image_t const*, st_face_t const*, st_face_orientation, float*)`, score compared to 0.9 | confirmed (call + threshold) | `native/big_AuthUseId.s`, `.data` 0x4f2c08c |
| embedding + compare | `FaceVerifyManager::ExtractFeature`, then `FaceVerifyManager::CompareFeature` @0x126064 → `FaceVerify::CompareFeature` → `cv_common_feature_comparator_get_distance` (Eigen dot product; Q16 fixed-point fast path scales by 2^-16 at literal `0x230d04`) → `cv_common_feature_comparator_normalize_score_by_model` → `stsdk::FeatureCmp::normalizeScore` → `protector::NormModel::Run(float&)` | confirmed (functions) / inferred (cosine metric — features are L2-normalised by `verif_engine::Engine::Normalize`/`GetPCAFeature`) | `native/big_FaceVerify_CompareFeature.s`, `native/cv_cmp_get_distance.s`, `native/stsdk_FeatureCmp_normalizeScore.s`, `nm` symbols `stsdk::CosineDistance`, `stsdk::FeatureCmp::set_compare_threshold` |
| decision | `FeatureManager::Unlock(feature, identifier, threshold, verifyMgr, int* faceId, int n, float& score, int& status)` compares the normalised score with the configured threshold (`fcmpe s0,s8` / `fcmpe s9,s8`, `native/big_FeatureManager_Unlock.s`) → native status 201 (ok) or 202 (fail) | confirmed | `native/big_FeatureManager_Unlock.s`; `FaceAuthManager.compare` reads 201 → `getLastUnlockID` |
| status → app | `stFaceAuthStatusToFaceAuthResult`: 201→0, 202→12, 1→5, 2→14, 4→4, 5→19, 6→36, 7→31, 117→4, 123→30, 129→27, 131..134→8..11, 113..116→16/18/15/17, -1001→34, -1008→35 | confirmed | `sources/faceauth/classes/com.ts.faceauth.FaceAuthManager.java` |
| app status → UI | every non-zero `compare()` result goes to `FaceUnlockCallback.onFaceHelp(code)` → `MiuiFaceService.MSG_ON_ACQUIRED (2)`; only `0` calls `onFaceAuthenticated()` | confirmed | `FaceUnlockManager.onPreviewFrame`, `FaceAuthenHelper$1$1` |

## A.6 Consumer / action

```
status == 0
  FaceUnlockManager.handlePassed()
    -> mCallback.onFaceAuthenticated()
       -> MiuiFaceService worker msg 3 arg1 = 123456          (MiuiFaceService.MSG_ON_AUTHENTICATE)
          -> ClientMonitor.onAuthenticationSucceeded(123456,
                 FaceCompat.newInstance("Miuiface1", 1, 123456), 0)
             -> receiver.transact(203, ...)                     (receiver.FaceService, RECEIVER_ON_AUTHENTICATION_SUCCEEDED)
  -> FaceAuthenHelper.releaseOnWorkerThread() -> FaceUnlockManager.release(true) -> MSG 1007 in 60 s
```

* The face id delivered to SystemUI is the hard-coded `DEFAULT_DEVICEID = 123456`
  (`KeyguardUtils`), **not** the native `getLastUnlockID()` — on this path the native id is only
  logged. **confirmed** (`FaceAuthenHelper$1$1.onFaceAuthenticated`, `MiuiFaceService.onAuthenticate`).
* Failure/acquisition codes: `onFaceHelp(code)` → transact **202**; `onAuthenticationFailed()`
  → **204**; `onError(id, vendorCode)` → **205**; `onRemoved` → **206**; `onPreInit` → **207**;
  lockout reset → **261**; enroll result → **201**; ext command → **301**.
  **confirmed** `sources/faceunlock_extra/classes/com.miui.face.util.ClientMonitor.java`.
* Keyguard/settings side-effects: `Settings.Secure face_unlock_has_feature`,
  `face_unlock_valid_feature`, `SharedPrefUtil` face-id list. **confirmed** `MiuiFaceService.onEnrollResult/onRemoved/mCheckTemplate`.

## A.7 Failure and fallback

| situation | behaviour | confidence |
|---|---|---|
| camera open failure, codes 1/2 (in use / max cameras) | retried up to 5 times, delay `(5-n)*200 ms`; then `MSG_FACE_UNLOCK_OPEN_CAMERA_FAIL (1009)` → `onFaceAuthFailed(hasFace)` | confirmed `BaseFaceUnlockManager.UNLOCK_RETRY_TIME/RETRY_SLEEP_TIME`, `openCameraSucced` |
| auth timeout | `setRunOutTime(3000)` (FaceAuthenHelper default; values ≤2000 ignored) → `MSG_FACE_UNLOCK_WORK_TIMEOUT (1008)` after 3 s → `stopDetect()` + `onFaceAuthFailed`; the service then reports `onError(code 3 = MG_UNLOCK_FAILURE)` if the elapsed time also exceeded its own 3000 ms | confirmed `FaceAuthenHelper.mTimeout`, `BaseFaceUnlockManager.openCameraSucced` |
| no face in frame | native 1 → app **5**; `mHasFace` stays false, frames are skipped alternately (`mSkipIfNotFound`) | confirmed `FaceUnlockManager.onPreviewFrame` |
| liveness fail / quality fail | native 2 → app 14 / native 117·102 → app 4; delivered as an *acquired* code (202), unlock not granted | confirmed status map |
| template errors | native -1001 → 34 ("template damaged"), -1008 → 35 ("upper limit") | confirmed status map |
| fallback to PIN/other biometric | **none inside this APK.** FaceService only reports success/failure to SystemUI; PIN/pattern/other biometrics are SystemUI/`android.hardware.biometrics` policy, outside `Biometric.apk` | inferred (no code path) |
| memory release | `release(true)` posts MSG 1007 after 60 s idle → `setStandByMode()` + `st_auth_deinit()` | confirmed `BaseFaceUnlockManager.release`, `FaceUnlockManager.releaseFaceUnlock` |
| `virtual_light_device` | `munch` is not listed → camera is closed immediately after a failed frame; on listed devices closing is deferred until ≥30 frames or ≥1000 ms | confirmed `BaseFaceUnlockManager.stopDetect`, `evidence/resources_arrays.txt` |

## A.8 Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| frames are 640×480 NV21 from the front Camera2 device | confirmed | `AbstractCameraUtil`, `Camera2Util`, `Yuv` | — |
| orientation is always `RIGHT (8)` (angle 90 hard-coded) | confirmed | `AbstractCameraUtil.java:17` | a device with a different sensor mount would need `angle` recomputation; none exists |
| no frame timestamp reaches the SDK | confirmed | no `getTimestamp()` in `sources/` | — |
| exact cosine/PCA configuration per model | inferred | `stsdk::CosineDistance`, `verif_engine::Engine::InitPCAModel`, `PCAParam` schema | decrypt `meta.conf` |
| embedding dimension | unknown | `cv_feature_t` length is a runtime field | decrypt `meta.conf` / run the SDK |
| liveness score aggregation over multiple frames | unknown | only `FaceHackness2D` per-frame call visible | Ghidra of `FaceHacknessManager` (not reachable within budget) |
| native mirroring of the input | unknown | `Reverse`/`image_flip_funcs` symbols only | decrypt prototxt |

## A.9 Reimplementation mapping

| JAX module (`reimpl/pipeline.py`) | reproduces | input → output contract | status |
|---|---|---|---|
| `preprocess()` | A.3 steps 1-6 | `uint8[480,640,1.5]` NV21 → `float32[1,112,112,3]` (+ rotated ROI) | ARCHITECTURE_ONLY (mean/scale unknown) |
| `detect()` | Hunter detector | `float32[1,360,360,1]` → boxes `[N,4]` + objectness `[N]` | ARCHITECTURE_ONLY |
| `align()` | 106-pt aligner + ANN pose | `float32[1,112,112,3]` → `[1,106,2]`, pose `[1,3]` | ARCHITECTURE_ONLY |
| `quality()`, `liveness()` | MTNet attributes, RGB liveness | `float32[1,112,112,3]` → `[1,K]`, `[1]` | ARCHITECTURE_ONLY |
| `embed()` | MobileNetV2-T6 embedder | `float32[1,112,112,3]` → `[1,512]` L2-normalised | ARCHITECTURE_ONLY |
| `match()` | `FeatureManager::Unlock` | cosine(template, probe) ≥ 0.905 | threshold confirmed, metric inferred |

---

# Pipeline B — enroll

## B.1 Data sources / triggers

| item | value | confidence | evidence |
|---|---|---|---|
| trigger | `CODE_ENROLL (5)`: `(token, cryptoToken[], userId, receiver, groupId, packageName, Surface, detectArea:RectF, enrollArea:RectF, step, timeout)` | confirmed | `MiuiFaceService.onTransact` case 5, `enroll(...)` |
| UI precondition | caller (Settings/SystemUI) supplies a preview `Surface` and the two guide rects | confirmed | same |
| flow | `FaceEnrollHelper.enroll` → `Camera2Util.openCamera` → `onPreviewFrame` → `FaceAuthManager.saveFeature(byte[],w,h,angle)` per accepted frame | confirmed | `FaceEnrollHelper.enroll`, `onPreviewFrame:175` |
| stop/cancel | `CODE_CANCEL_ENROLL (6)` → `stopFromBp()` → close camera, `reset()`; `CODE_EXT_CMD (101)` cmd 0/1 resumes/resets the multi-step guide | confirmed | `MiuiFaceService.cancelEnrollment`, `onExtCmd` |
| timeout | `mTimeout = 30000` ms (`mDelayedHide`) | confirmed | `FaceEnrollHelper.mTimeout` |

## B.2 Transport and format

Same camera/format as A.2 (640×480 NV21, angle 90), with two differences:

| item | value | confidence |
|---|---|---|
| preview target | caller's `Surface` is added **and** an `ImageReader` is still attached (`startPreview(surface != null)` ⇒ `z=false` ⇒ session mode 0, no operating-mode flag) | confirmed `Camera2Util.startPreview` |
| ROI | `setDetectArea(left, top, right, bottom)` from `RectF`/`Rect`, rotated in Java (`RotateMaskRect(..., true)`) and applied via `st_auth_set_roi(&rect)` before **every** `enroll()`; `null` rect → `st_auth_set_roi(NULL)` | confirmed `FaceEnrollHelper.setDetectArea`, `FaceAuthManager.saveFeature` |
| frame skipping | `isNeedGetFrameNonsequence()` is **false** for `munch` ⇒ every frame is used; listed devices use every 5th frame | confirmed `FaceEnrollHelper.onPreviewFrame`, `evidence/resources_arrays.txt` |
| guide state | `mCanNextFaceInputStep` gates input; step count `mEnrollStep` (1..5) → `setEnrollImageNumberForFace` | confirmed `FaceEnrollHelper.start` |

## B.3 Preprocessing

Identical to A.3, plus the ROI crop/rotation pair (Java `RotateMaskRect` →
native `st_auth_set_roi` → `FaceUnlock2DInterFace::RotateRect`). Enrolment also derives
pose/eye/occlusion to drive the "turn your head" guide (status 19 / 36 / 15-18).

## B.4 Model invocation

Same eight nets; `st_auth_enroll` (0x10ca7c) → `FaceUnlock2DInterFace::Enroll` (0x1496ec,
4508 B), same worker-pool dispatch (`BlockingQueue<std::function<int()>>::push` + `condition_variable`)
and the same `CheckFaceInfo` → `CheckOcularInfo` → `FaceVerifyManager::ExtractFeature` →
`SerializeFeature` chain. **confirmed** `native/big_st_auth_enroll.s`, `native/big_Enroll.s`.

## B.5 Output postprocessing

`EnrollResult { mFaceId, mEnrollStatus, mNativeStatus }`:
`mFaceId` = the JNI **return** value (user id written by `st_auth_enroll`'s third argument),
`mEnrollStatus` = mapped `*rc[0]`, `mNativeStatus` = raw `*rc[0]`. **confirmed**
`native/jni_all.c:701`, `FaceAuthManager.saveFeature`, `EnrollResult.java`.

`FaceEnrollHelper.onPreviewFrame` reacts to: `19` = keep going (`ST_ENROLL_KEEP`) → block input
until the app sends EXT_CMD 0; `36` = not-same-person → `reset()`; `0` = success → `onEnrollResult(faceId+1)`,
camera closed, memory released (deferred). **confirmed**.

Template persistence: `FeatureManager::AddUser` → `TemplateHepler::SaveTemplateFile` writes
`<filesDir>/template_file.data` (native literal `template_file.data` at `.rodata` 0x762278;
Java `FaceUnlockManager.MODEL_PATH`). Protobuf `pb.TemplateFile` (magic `STFACEUNLOCK`,
version string `1.0.1`), features are `cv_feature_serialize`/`cv_feature_degarble`d.
**confirmed (path/magic strings), inferred (field layout from protobuf descriptors)**.

## B.6 Consumer / action

`MiuiFaceService.onEnrollResult(faceId)` → `ClientMonitor.onEnrollResult(123456, faceId, 0, 0)`
(transact **201**) → `SharedPrefUtil.saveFacesEnrollSuccess`, `Settings.Secure
face_unlock_has_feature = <count>`, `face_unlock_valid_feature = 1`.
**confirmed** `MiuiFaceService.onEnrollResult`.

## B.7 Failure and fallback

Status 4 (bad quality), 5 (no face), 8-11 (offset), 15-18 (head pose), 21/23/37 (eye/mouth/nose
blocked), 27 (multi-face), 30 (too bright), 33 (not in ROI), 34/35 (template damaged/full) are all
forwarded to the UI as *acquired* codes; `36` forces a reset; timeout (30 s) → `MSG 1008` →
`onError(3)`. **confirmed**.

## B.8 Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| enrolled feature = embedded vector + source image + 106 points, protobuf, compressed/garbled | inferred | `TemplateFile`/`SingleTemlate` messages in `.rodata` descriptors, `cv_feature_degarble`, `"Compression template file"` strings | decrypt a runtime `template_file.data` |
| storage location/name | confirmed | `FaceUnlockManager.MODEL_PATH`, `.rodata` `template_file.data` | — |
| number of frames per face id | confirmed (app sets 1..5 via `setEnrollNumberForFace`) | `FaceEnrollHelper.mEnrollStep` | — |
| template `Identifier` namespace default | unknown | `st_auth_set_identifier` never called in this app; default lives in native | runtime trace |

## B.9 Reimplementation mapping

`reimpl/pipeline.py` has no enrol path beyond `embed()` + `match()`: the shipped template is a
protobuf with garbled features, so an enrol reimplementation would need
`extract_feature()` → `cv_feature_serialize` → `pb.TemplateFile{Version, Identifier,
SinglePersonTemplate{SingleTemlate{Direction, Points, Feature, ImageInfo}}}`. Status:
**ARCHITECTURE_ONLY** (weights encrypted, template codec proprietary).

---

# Cross-cutting: native lifecycle

| phase | call chain | confidence |
|---|---|---|
| licence | `st_face_init_license(const char*)` (0xfc800) → `LicenseLoader::InitLicense`; must precede `st_auth_init`, non-zero → Java returns 3 | confirmed `native/big_st_face_init_license.s` |
| init | `st_auth_init(const char* dir, unsigned int cfg)` (0x10e3e8): `AlgoManager::InitAlgorithmInfo`, model handles, thresholds, `SetTemplateFilePath`, `StartThread` (2 detached `std::thread`) | confirmed |
| alternative init | `st_auth_pre_init` (0x10eb20) exists (same signature, no `CreateDetectHandle`) but **no JNI caller** | confirmed (symbols + JNI list) |
| per-frame | `st_auth_unlock` → `AuthUseId`: `CheckFaceInfo` (detect/align/pose/quality) → queue liveness + embed → `CheckOcularInfo` → `FeatureManager::Unlock` | confirmed |
| teardown | `st_auth_set_standby_mode` + `st_auth_deinit` (0x10c7a4) → `FaceUnlock2DInterFace::~` + `releaseNet` | confirmed |
| thread safety | `FaceAuthManager` methods are `synchronized`; native uses a mutex + condition_variable per task group | confirmed |
