# Decompiled sources (targeted, jadx `--single-class`)

All files were produced with `tools/decompile_classes.py` (single-class jadx, no
full-project decompilation).  `classes.dex` is only 154,880 B and contains 120
defined classes; the selections below are the ones that carry the face-unlock
logic.

## `faceauth/` — SenseTime SDK Java facade (`com.ts.faceauth`)

| file | why it matters |
|---|---|
| `classes/com.ts.faceauth.FaceAuthManager.java` | singleton facade; init/licence/enrol/auth/ROI/area-code/threshold plumbing; 60+ status codes mapped to app results |
| `classes/com.ts.faceauth.FaceAuthNative.java` | all 25 `native` methods + `System.loadLibrary("jni_stfaceunlock_api")` + `onFaceDetected` callback |
| `classes/com.ts.faceauth.data.STImage.java` | the image struct passed to JNI (`mImageData/mFormat/mWidth/mHeight/mStride`) |
| `classes/com.ts.faceauth.data.CvPixelFormat.java` | pixel formats (NV21 = 3, RGB888 = 7, …) |
| `classes/com.ts.faceauth.data.FaceOrientation.java` | orientation **bitmask** (UP=1, LEFT=2, DOWN=4, RIGHT=8, UNKNOWN=15) |
| `classes/com.ts.faceauth.data.StFaceAuthStatus.java` | complete native status enum (used for the status table in the report) |
| `classes/com.ts.faceauth.data.StFaceAuthMode.java` / `StFaceFunctionConfig.java` / `StFaceGpuPriority.java` / `StFaceLogLevel.java` | configuration enums |
| `classes/com.ts.faceauth.data.FaceIDInfo.java` / `FaceDetectedInfo.java` | template entry + per-frame detect callback payload |
| `classes/com.ts.faceauth.bean.AreaInfo.java` | ISO-3166 alpha-2 → numeric area code map (threshold selector) |
| `classes/com.ts.faceauth.bean.AuthenticationResult.java` / `EnrollResult.java` | result wrappers (native status preserved via `setNativeStatus`) |
| `classes/com.ts.faceauth.FaceAuthResult.java` | app-level result codes |
| `classes/com.ts.faceauth.util.FileUtil.java` / `LogUtil.java` | `area.co` persistence, logging |

## `faceunlock/` — MIUI keyguard/system integration

| file | why it matters |
|---|---|
| `classes/com.miui.face.MiuiFaceService.java` | Binder service `miui.face.FaceService` (`ServiceManager.addService` at line 143), `CODE_*` protocol, template watchdog, lockout monitors |
| `classes/com.android.keyguard.faceunlock.FaceUnlockManager.java` | `MODEL_PATH = /data/data/com.miui.face/files/template_file.data`; `initAll()`, per-frame `compare(...)` |
| `classes/com.android.keyguard.faceunlock.BaseFaceUnlockManager.java` | worker thread, timeouts, retry logic |
| `classes/com.android.keyguard.FaceEnrollHelper.java` | enrolment state machine, `setDetectArea`, `saveFeature` |
| `classes/com.android.keyguard.FaceAuthenHelper.java` | unlock state machine |
| `classes/com.android.keyguard.faceunlock.FaceUnlockJni.java` | `nativeMallopt()` only — `libjni_faceunlock.so` |
| `classes/com.android.keyguard.faceunlock.Camera2Util.java` / `AbstractCameraUtil.java` | camera + NV21 preview delivery |
| `classes/com.miui.image.Yuv.java` | YUV plane layout helper |
| `classes/com.android.keyguard.KeyguardUtils.java` | device/keyguard feature checks |

## `faceunlock_app/`

| file | why it matters |
|---|---|
| `classes/com.miui.face.MiuiBioApp.java` | `Application`; calls `MiuiFaceService.init(getApplicationContext())` (line 57) |

## `faceunlock_extra/` — camera and client plumbing (added 2026-10-24)

| file | why it matters |
|---|---|
| `classes/com.miui.face.util.ClientMonitor.java` | the Binder receiver protocol back to SystemUI (`receiver.FaceService`, codes 201/202/**203**/204/205/206/207/261/301) |
| `classes/com.miui.face.util.DeviceUtils.java` | per-codename camera gates: `isNeedOperatingMode` (session mode 32774), `isNeedMoreStreamInAuth`, `isVirtualLightDevice`, `isNeedGetFrameNonsequence`, `isLiteMode` |
| `classes/com.miui.image.Yuv.java` | YUV_420_888 → NV21 conversion actually used for every frame |
| `classes/com.miui.face.util.SharedPrefUtil.java` | enrolled-face mirror kept next to the native template |
| `classes/com.android.keyguard.faceunlock.IPreviewCallback.java` / `FaceUnlockCallback.java` / `BaseFaceEnrollHelper.java` | the two callback interfaces that connect camera → SDK → service |
| `classes/com.ts.faceauth.FaceDetectedCallback.java`, `classes/com.ts.faceauth.data.StFaceAuthMode.java`, `StFaceFunctionConfig.java` | SDK callback/config enums |

Raw indexes: `faceauth/index.md`, `faceauth/hits.json`, `faceunlock/index.md`,
`faceunlock/hits.json`, `faceunlock_app/index.md`, `faceunlock_extra/index.md`,
`faceunlock_extra/hits.json`.
