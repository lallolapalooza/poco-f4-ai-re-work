# miui_biometric — MIUI face unlock (`com.miui.face`, `Biometric.apk`)

SenseTime **FaceAuth** SDK, packaged as a system app on a Xiaomi Poco F4
(MIUI).  Everything runs offline on-device; the eight CNN models are compiled
into the 101.8 MiB `libstfaceunlockocl.so` and executed through a fork of Caffe
(`caffe-lite`) on top of SenseTime's `hpc`/`pplwrapper` engine with an Adreno
OpenCL backend (plus ARM/x86 fallbacks).

| | |
|---|---|
| APK | `product/app/Biometric/Biometric.apk` |
| Package | `com.miui.face` |
| Version | `1.37.4-A140026` (versionCode `1370400`) |
| APK SHA-256 | `7be477e2a28139d0ccd5c9c43e0f88d8afa8627cf4c8bd829af6bcde6bcee111` |
| Size | 107,301,666 B / 17 zip entries / 1 dex (154,880 B) |
| ABI | `arm64-v8a` only |
| Components | **0 activities / 0 services / 0 receivers / 0 providers**, 0 permissions |
| min/target SDK | 33 / 34 |
| Native libs | `libstfaceunlockocl.so` (106,744,488 B), `libjni_stfaceunlock_api.so` (131,336 B), `libjni_faceunlock.so` (5,760 B), `libOpenCL.so`, `libmievent.so` |

The APK declares no components because it is a *library* app: `MiuiFaceService`
is a raw `Binder` published as `miui.face.FaceService` from
`MiuiBioApp` (see `sources/faceunlock/`), consumed by `com.android.systemui`
(`KEYGUARD_PACK`).

---

## 1. Feature map

| feature | entry point | model(s) | confidence |
|---|---|---|---|
| face detect (per preview frame) | `FaceAuthManager.compare` → `FaceAuthNative.auth` → `st_auth_unlock` | `M_Hunter_CommonFace_Gray_ppl_360_9_2_5_v2_origin_model` | confirmed |
| 106-point alignment | `stfaceunlock::FaceAlign` / `stsdk::AlignerOcclusion` | `M_Align_occlusion_106_1_19_3_model`, `M_Align_occlusion_106_360_1_7_1_model` | confirmed |
| head pose (yaw/pitch/roll) | `stfaceunlock::FaceCalcPose` / `posesdk::Pose_106` | `M_Align_CalcPose_Ann_106_2_3_0_model` | confirmed |
| eye state (open/closed, both eyes) | `stfaceunlock::FaceoOcular` / `stsdk::AlignmentEyeState` | `M_Align_Deepface_Eye_State_Analysis_3_7_8_model` | confirmed |
| face quality / attributes | `posesdk::AttributeMalamute` (MTNet) | `M_Attribute_MTNet_Quality_1_0_0_model` | confirmed |
| liveness / anti-spoof | `stfaceunlock::FaceHackness2D` / `live::GeneralFacePredictor*` | `IM_RGB_Liveness_General_Full_FP32_10_1_65_half_compression_v2_origin_model` | confirmed |
| face embedding + 1:1 match | `stfaceunlock::FaceVerify` / `verif_engine::Engine` | `M_Verify_MobileNetV2T6_Common_3_135_0_half_compression_v2_origin_model` | confirmed |
| template storage | `stfaceunlock::TemplateHepler` (`pb::TemplateFile`, magic `STFACEUNLOCK`) | – | confirmed |
| batch evaluation (QA tool) | `st_auth_evaluate` family (`st_auth_evaluate_init`, `_extract_info`, `_general_report`, `_ocular_report`) | same models | confirmed |

Model *selection* is done by name at run time (`protector::ModelLoader::LoadByName`,
`AlgoManager::GetBultinNetConfig`); the library additionally carries alternate
version keys for the same stages (e.g. `M_Detect_Hunter_SmallFace_IR_AND_RGB_360.9.2.5`,
`M_Liveness_Antispoofing_General.10.6.0/10.9.0/10.17.0`,
`M_Verify_Mobilenetv2Pruned_RGB_Common.3.97.0/3.91.0`,
`IM_RGB_Liveness_General_Face_FP32.12.1.49/50/41`) which are **not** compiled
into this APK as blobs — only the eight `__res_*` blobs below exist.

---

## 2. Java / Kotlin flow  (`sources/faceunlock`, `sources/faceauth`)

```
com.miui.face.MiuiBioApp                 (app_main, publishes the Binder)
  └─ com.miui.face.MiuiFaceService       "miui.face.FaceService", Binder CODE_* protocol
       ├─ com.android.keyguard.faceunlock.FaceUnlockManager   (singleton, MODEL_PATH)
       ├─ com.android.keyguard.FaceAuthenHelper               (unlock path)
       ├─ com.android.keyguard.FaceEnrollHelper               (enrolment path)
       └─ com.ts.faceauth.FaceAuthManager                     (SDK facade, singleton)
            └─ com.ts.faceauth.FaceAuthNative                  (25 native methods)
                 └─ libjni_stfaceunlock_api.so → libstfaceunlockocl.so
```

Key facts (each read from the decompiled source in `sources/`):

* `FaceUnlockManager.MODEL_PATH = "/data/data/com.miui.face/files/template_file.data"`
  (`com.android.keyguard.faceunlock.FaceUnlockManager`, line 11). The enrolled
  template is a single protobuf file; `FaceAuthManager.initHandler()` also
  creates `area.co` in the same directory (per-country threshold selector).
* `FaceUnlockManager.initAll()` calls `FaceAuthManager.initAll("model1", "model2")`
  — the two arguments are the *liveness-mode paths* (unused by this build;
  `mLivenessModePath` is only tested for `null`).
* Per-frame: `FaceUnlockManager.onPreviewFrame(byte[] frame, String)` →
  `FaceAuthManager.compare(frame, width, height, angle, /*liveness*/ true)`.
  Camera frames come from `Camera2Util`/`AbstractCameraUtil`; `angle` is the
  camera sensor orientation in degrees.
* Enrolment: `FaceEnrollHelper` → `FaceAuthManager.saveFeature(frame, w, h, angle)`,
  with `setDetectArea(rect)` (guide box) and `setEnrollImageNumberForFace(n)`.
* The SDK licence is a **hard-coded string** in `FaceAuthManager.LICENSE_STR`
  (`# SenseTime License … SN: 0afba29a-8324-4a70-b741-6d59f6d7da7a`, followed by
  two base64 blocks) and is passed to `FaceAuthNative.initLicense`.
* Result demux: `FaceAuthManager.stFaceAuthStatusToFaceAuthResult` maps the
  native status (`StFaceAuthStatus`, 60+ codes) onto the app-level codes
  (0 = OK/unlock, 4 = quality, 8/9/10/11 = ROI offsets, 15-18 = head pose,
  21/23/37 = eye/mouth/nose blocked, 30 = too bright, 33 = not in ROI,
  34 = template damaged, 35 = template upper limit, 36 = different person /
  sub-frame offset…). Full table in `sources/faceauth/.../StFaceAuthStatus.java`.

`FaceAuthManager` API surface (all `synchronized`, singleton):

```java
initHandler(dir)                      // sets db dir + area.co path
initAll(licenseOrMode, livenessPath)  // initLicense -> createFaceAuth -> thresholds
setDetectArea(l,t,r,b)                // ROI guide box, rotated per frame orientation
setEnrollImageNumberForFace(n)        // number of accepted frames per enrol
saveFeature(byte[],w,h,angle)         // enroll
compare(byte[],w,h,angle,boolean live) // auth (returns 0 on success)
deleteFeature()/deleteFeature(id)/reset()/release()/setStandByMode()
getFeatureCount()/getFaceIDsInTemplate()/getVersion()/getModelInfo()
```

---

## 3. JNI lifecycle — exact argument/return semantics

`com.ts.faceauth.FaceAuthNative` (25 `native` methods, all `static`, bound by
`Java_*` symbol names — no `RegisterNatives` indirection).  Signatures below
come from DWARF (`native/dwarf_types_funcs.txt`) and the disassembly
(`native/Java_com_ts_faceauth_FaceAuthNative_*.s`).

| Java method | native symbol | semantics (verified) |
|---|---|---|
| `initLicense(String)` → `int` | `st_face_init_license(const char*)` | parses the licence blob; must precede `createFaceAuth`. Returns 0 on success (`FaceAuthManager.initAll` maps non-zero to `3`). |
| `createFaceAuth(String dir, int cfg, int[] rc)` → `void` | `st_auth_init(const char* dir, int cfg)` → `rc[0]` | `GetStringUTFChars`; `ret = st_auth_init(cstr, cfg)`; writes `ret` into `rc[0]` if `rc != null`; releases the string. `cfg` is `FaceAuthManager.mFaceAuthEnrollConfig` (0 in this app). |
| `enroll(STImage, int cfg, int[] rc)` → `int` | `st_auth_enroll(st_image_t*, int cfg, int* out)` | converts `STImage`→`st_image_t` (byte[] + format + w + h + stride); optional `st_auth_set_facedetect_callback` first; **the JNI return value is the int written by `st_auth_enroll` through its 3rd argument**, and `rc[0]` receives `st_auth_enroll`'s return code. `rc[0]` is what the Java layer maps to a status; the returned int is stored in `EnrollResult.enrollStatus`. |
| `auth(STImage, int cfg, int[] faceIds, int[] rc)` → `void` | `st_auth_unlock(st_image_t*, int cfg, int* faceIds, int n)` → `rc[0]` | `faceIds` is an **in/out** array (length = capacity, matched id(s) written by native); `rc[0]` = status. 201 (`ST_FACE_AUTH_STATUS_UNLOCK_OK`) triggers `getLastUnlockID`. |
| `getLastUnlockID(int[] rc)` → `int` | `st_auth_get_last_unlock_id(int*)` | last matched face id. |
| `getFaceIDInfoInTemplate(int[] rc)` → `FaceIDInfo[]` | `st_auth_get_template_info(...)` → `st_face_id_info_t[]` → `st_faceid_info_array_to_java_faceidinfo` | array of `{mFaceID, mFeatureCountOfFace}`; `rc[0]` = status. |
| `deleteFace(int id)` → `int` | `st_auth_delete_face(int)` | |
| `deleteAllFace()` → `int` | `st_auth_delete_all_face()` | |
| `resetFaceAuth()` → `int` | `st_auth_reset()` | enroll-state reset |
| `releaseFaceAuth()` → `void` | `st_auth_deinit()` | |
| `setRoi(StRect, int[] rc)` → `void` | `st_auth_set_roi(st_rect_t*, int*)` | `null` clears the ROI |
| `setIdentifier(String, int[] rc)` | `st_auth_set_identifier(const char*, int*)` | template namespace |
| `setOrientation(int, int[] rc)` | `st_auth_set_face_orientation(int)` | **bitmask**: UP=1, LEFT=2, DOWN=4, RIGHT=8, UNKNOWN=15 |
| `setEnrollNumberForFace(int)` | `st_auth_set_faceid_image_number(int)` | frames per face id |
| `setFaceAuthMode(int)` | `st_auth_set_faceauth_mode(int)` | 0 = NORMAL, 1 = FAST |
| `setAreaCode(int)` | `st_auth_set_threshold_by_international_code(int)` | ISO-3166 numeric → per-country thresholds (`AreaInfo.mAreaMap`) |
| `setLogLevel(int)`, `setGPUPriority(int)`, `setStandByMode()`, `setTeeCommunicationFunc()`, `setTeeCommunicationFuncAddr(long)` | `st_face_open_log`, `st_auth_set_gpu_priority`, `st_auth_set_standby_mode`, `st_auth_set_tee_communication_func` | TEE variants support a trusted-execution unlock path (`st_auth_get_faceid_count_tee`, `_ree_auto`, `_ree_callback`) |
| `enableFaceDetectCallback(boolean)` | `st_auth_set_facedetect_callback(fn)` | when enabled, both `enroll` and `auth` install `st_auth_set_facedetect_callback` and Java's `FaceAuthNative.onFaceDetected(FaceDetectedInfo)` fires per frame |
| `getAlgoInfo()` / `getSDKVersion()` | `st_face_algorithm_info()` / `st_face_get_version()` | returns the `algoinfo.AlgoInfoMap` protobuf (net name + per-model name/type) as string |
| `evaluate(inPath, outPath, fmt, w, h, orient, mode)` → `int` | `st_auth_evaluate(const char* in, const char* out, int fmt, int w, int h, int orient, int mode)` | batch (dataset) evaluation; needs both paths, returns -1 if either is null |

`st_image_t` (DWARF, `native/dwarf_types_funcs.txt`):

```c
struct st_image_t { unsigned char* data; st_pixel_format pixel_format; int width; int height; int stride; };
// CvPixelFormat: GRAY8=0, YUV420P=1, NV12=2, NV21=3, BGRA8888=4, BGR888=5, RGBA8888=6, RGB888=7
```

The Java `STImage` carries `mImageData, mFormat, mWidth, mHeight, mStride`
(stride from `FaceAuthManager.getStride()`: ×3 for BGR888/RGB888, ×4 for
BGRA/RGBA, else width).

---

## 4. Native pipeline (`libstfaceunlockocl.so`, 101.8 MiB)

Unstripped: 41,090 symbols (909 exports, 1,475 dynsym), no JNI entry points
(the JNI shim is the separate `libjni_stfaceunlock_api.so` which `DT_NEEDED`s
this library).

Sections of interest: `.text` 6.78 MiB, `.rodata` 70.69 MiB — of which 70.4 MiB
is **30 precompiled Adreno BCL kernel blobs**
(`__5xx/__6xx/__7xx/__G52…__GM9446_bcl_binary_data`, with per-Android-version
variants); the rest is the model-name directory + Caffe protobuf descriptors
+ error strings — `.data` 15.65 MiB (the eight model blobs), `.debug_*` ≈5.6 MiB.

Class graph (from `nm`, mangled → demangled):

```
stfaceunlock::FaceDetection(Manager)      -> FaceHackness2D/3D, FaceAlign(InterFace/Manager)
stfaceunlock::FaceCalcPose                -> FaceoOcular(Manager)
stfaceunlock::NormalTrack / TrackManager  -> (tracking, compact occlusion)
stfaceunlock::FaceVerify(Manager)         -> verif_engine::Engine
stfaceunlock::TemplateHepler              -> pb::TemplateFile (protobuf)
stsdk::detection::DetectorHunter / DetectorCraft / detection2::DetectorSpider
stsdk::AlignerOcclusion / AlignerDeep / AlignmentEyeState / FeatureCmp
posesdk::Pose_106 / Pose_21 / AttributeMalamute
live::GeneralFacePredictorV7..V20, FramePredictorAntispoofing*, Depth/DPredictor*
protector::ModelLoader / TarModelResource / CaffeModelPPL / SVMModel / PCAModel
caffe::* (a Caffe fork, 123 protobuf message types), hpc::pplwrapper::* (OpenCL/ARM kernels)
```

Each `*::Init(protector::ModelLoader&)` loads its net by name through
`ModelLoader::LoadByName` / `LoadByCaffeName`; `AlgoManager::GetBultinNetConfig(name, key, out)`
resolves the per-model net configuration (`cv_model_net_config_t`).

### 4.1 Model → stage table (all blobs confirmed present)

| blob symbol (`__res_<name>_model`) | stage | consumer class | payload | confidence |
|---|---|---|---:|---|
| `M_Hunter_CommonFace_Gray_ppl_360_9_2_5_v2_origin` | face detection (RPN/Faster-RCNN "Hunter", 360-px input, grayscale) | `stfaceunlock::FaceDetection`, `stsdk::detection::DetectorHunter` | 983,040 | confirmed |
| `M_Align_occlusion_106_1_19_3` | 106-pt alignment + occlusion | `stsdk::AlignerOcclusion` | 1,781,760 | confirmed |
| `M_Align_occlusion_106_360_1_7_1` | 106-pt alignment (360-px variant) | `stsdk::AlignerOcclusion` (variant arg) | 2,058,240 | confirmed |
| `M_Align_CalcPose_Ann_106_2_3_0` | head pose from 106 pts (ANN) | `posesdk::Pose_106`, `stfaceunlock::FaceCalcPose` | 122,880 | confirmed |
| `M_Align_Deepface_Eye_State_Analysis_3_7_8` | eye open/closed | `stsdk::AlignmentEyeState`, `stfaceunlock::FaceoOcular` | 1,771,520 | confirmed |
| `M_Attribute_MTNet_Quality_1_0_0` | quality / attributes (MTNet) | `posesdk::AttributeMalamute` | 471,040 | confirmed |
| `IM_RGB_Liveness_General_Full_FP32_10_1_65_half_compression_v2_origin` | RGB liveness / anti-spoof (full frame) | `stfaceunlock::FaceHackness2D`, `live::GeneralFacePredictor*` | 4,106,240 | confirmed |
| `M_Verify_MobileNetV2T6_Common_3_135_0_half_compression_v2_origin` | face embedding / 1:1 verify (MobileNetV2-T6) | `stfaceunlock::FaceVerify`, `verif_engine::Engine` | 5,109,760 | confirmed |

Input resolutions/colour formats in the table are **inferred** from the model
names (`Gray`, `360`, `MobileNetV2T6`) and from `DetectorHunter::PadBlobInput`;
the authoritative per-net I/O specification lives in the encrypted `meta.conf`
(`st_model.ModelFile`/`CaffeParam`).

`FeatureCmp` additionally loads a **norm/PCA file** from the same model
container (`loadNormFile`, `verif_engine::Engine::InitPCAModel`, protobuf
`st_model.PCAParam{SrcDim,DstDim,Mean,Coeff}` + `ScoreNormalizationParameter`),
i.e. the comparison can be a PCA-projected cosine with a piecewise score
normalisation table.

---

## 5. Models: container format and why the weights are not extractable

### 5.1 Where they live

`nm -S libstfaceunlockocl.so` exposes, for every model, three symbols:

```
__res_<NAME>_model       (.data, the blob)
__res_<NAME>_model_size  (4 bytes)
__res_<NAME>_model_end   (8 bytes)
```

`.data` is mapped at `file_offset = vaddr - 0x10000` in this ELF; the eight
blobs are extracted byte-exact by `models/extract_blobs.py` into
`models/*.bin` (15.65 MiB total) with `models/manifest.json` (vaddr, file
offset, size, sha256, header fields).

### 5.2 Format (recovered, plaintext header)

```
0x00  "STEF"                 magic
0x04  u32le payload_size     always a multiple of 0x1000 (and of 16)
0x08  "VER"
0x0b  u8    version = 3
0x0c  u32le flags = 0
0x10  payload[payload_size]  high-entropy (encrypted)
0x10+payload_size  257 B     trailer (256-byte signature/MAC + 0x00)

total == 16 + payload_size + 257       (all eight blobs obey this exactly)
```

The decrypted payload is a **tar** archive: `protector::TarModelResource::
TarModelResource(shared_ptr<ModelResource>)`, `TarModelResource::FindFileByName`,
`protector::CaffeModelPPL::Init(TarModelResource&, string const&, cv_model_net_config_t)`,
and the literals `ustar`, `meta.conf`, `/model.bin`, `rel.prototxt`,
`detector.model`, `net_001`, `proposal_final`, `pose` in `.rodata`.

`meta.conf` parses as protobuf `st_model.ModelMeta`; the full schema was
recovered from the generated-code field-number constants in `.rodata`
(`ir/model_container.json`):

```
ModelMeta {1 Version, 2 ModelName, 3 ModelType, 4 Description, 5 repeated ModelFiles}
ModelFile {1 Name, 2 Version, 3 Filenames, 4 ModelType, 5 CustomType, 6 CaffeParam, 7 ScoreNormParam}
CaffeParam{1 InputLayer, 2 OutputLayer, 3 EnableSharedBlob, 4 MarkedOutputBlobs}
Version   {1 Major, 2 Minor, 3 Patch, 4 TrainDate}
Matrix    {1 Dim, 2 Data}
PCAParam  {1 SrcDim, 2 DstDim, 3 Mean, 4 Coeff}
ScoreNormalizationParameter {1 SrcPoints, 2 DstPoints}
```

### 5.3 Why the weights could not be parsed

* Payload entropy is 7.997–8.000 bit/byte for all eight payloads; zero-byte
  frequency is 0.39 % (= 1/256, i.e. random).
* No standard codec: `zlib`, raw `deflate`, `gzip`, `lzma`, `bz2` all reject
  the payload (tried at offset 0x10 with the declared length).
* No repeating-key XOR: per-position entropy stays ~8.00 for periods 1…16, and
  a known-plaintext test (assume a tar whose first member name is `meta.conf` /
  `model.bin` / `/model.bin`, then check the `ustar` magic at tar offset 257)
  fails for every candidate key.
* No duplicate 16-byte blocks in any payload → not ECB over repetitive weights.
* The header is plaintext but carries no key; the whole decrypt path is inside
  the SenseTime app-protect layer: `protector::ModelLoader::Load(shared_ptr<>
  ModelResource)` unwraps `TarModelResource`, parses `st_model::ModelMeta` via
  `google::protobuf::CodedInputStream`, and consults
  `sdk_protector_has_license`; `get_model_loader_bypass_license_status`,
  `sdk_protector_AES_set_key / _convert_key / _cbc_decrypt`,
  `sdk_protector_RC4_setup / _crypt`, `sdk_protector_MD5_*`,
  `sdk_protector_RSA_*`, `sdk_protector_get_offline_activation_code`,
  `sdk_protector_qr_verify` all exist as local symbols. The key material is
  derived at run time from the licence chain; it is **not** stored in plaintext
  anywhere in the APK (the licence string in `FaceAuthManager.LICENSE_STR` is an
  RSA-signed blob, not a raw key), and everything in `.data` that is not a blob
  header is equally opaque to plaintext search.
* The `__appProtect_*_create_start/fill/end` and
  `__appProtect_sdk_protector_add_license_*` stubs in `.text` are **NOP
  placeholders** (verified by disassembly of
  `__appProtect_cv_common_calcpose_106_create_fill` — 256 `nop`s), i.e. hooks
  for the post-link protection tool, not the decryptor itself.

**Conclusion (blocker):** the eight model blobs are extracted exactly, and
their container/`meta.conf` schema is recovered, but the network weights remain
encrypted. Full weight recovery requires re-implementing the SenseTime
`protector` key-derivation (licence → AES/RC4 key), which is a separate
cryptographic RE effort. `reimpl/` is therefore marked **ARCHITECTURE_ONLY**.

---

## 6. Preprocessing and thresholds

### 6.1 Preprocessing chain (stage 0)

From `FaceAuthManager` + `st_image_t` + the SDK's image helpers
(`ImageHepler::ResizeImage`, `ImageHepler::RotateImage`,
`ImageHepler::GetCvImageFromStImage`, `cv_common_color_convert`,
`cv_common_crop_face_by_points`, `cv_common_alignment_align_by_pose`):

```
camera preview bytes (NV21, fmt=3, stride=width)
  -> colour convert (cv_common_color_convert)
  -> rotate by FaceOrientation bitmask (st_auth_set_face_orientation)
  -> optional ROI (st_auth_set_roi / FaceAuthManager.setDetectArea, rotated by
     RotateMaskRect for 90/180/270)
  -> detector input: square-padded to 360 (DetectorHunter::PadBlobInput)
  -> align: 106 landmarks -> similarity transform -> aligned crop 112x112
  -> per-net mean/scale (st_auth_set_mean_value -> cv_model_net_config_t)
```

Constants visible in `.data` global pool (plaintext, before the blobs):
`0x4f2c2fc = 2.2`, `0x4f2c300 = 1.0`, `0x4f2c304 = 0.4545…` (=5/11),
`0x4f2c308 = 1.0`, `0x4f2c2a0 = 0.218137` — plausible resize/scale factors, but
the mapping to individual nets is **inferred**.

### 6.2 106-point landmark groups (inferred)

`.data` also holds small index tables that partition the 106 points:
`52,53,54,55,56,57,72,73,74,104`, `58–63,75,76,77,105`,
`84–103` (20 consecutive → mouth), `43–51,80,81,82,83` (→ nose),
plus `84–103` repeated. This matches the 106-point layout used by
`M_Align_occlusion_106` (contour / brows / eyes / nose / mouth / pupils).
Confidence: **inferred** (index tables unambiguous, semantic labels from layout).

### 6.3 Thresholds (recovered from `.data`)

| symbol | value | meaning |
|---|---:|---|
| `DEFAULT_OCULAR_THRESHOLD` | **9.0** | eye-state score threshold (different scale) |
| `FASTAUTH_HACKNESS_THRESHOLD` | **0.9** | liveness/anti-spoof reject threshold |
| `FASTAUTH_VERIFY_THRESHOLD` | **0.905** | 1:1 match accept threshold |
| `DEFAULT_LIVENESS_THRESHOLD` | **0.9** | liveness (default) |
| `DEFAULT_VERIFY_THRESHOLD` | **0.905** | verify (default) |

These are the *defaults*; `st_auth_set_threshold_by_international_code(area)`
replaces them per ISO-3166 numeric code (`AreaInfo.mAreaMap`, 200+ entries).
Detection/quality thresholds are set through
`FaceDetection::SetDetectThreshold` / `cv_common_detection_get_threshold` and
are **not** stored as plaintext constants — **unknown**.

### 6.4 OpenCL kernels (Adreno BCL)

Precompiled for each GPU family (`__5xx, __6xx, __6xx__android13/14, __7xx,
__7xx__android14, __G52, __G57, __G61, __G610, __G68, __G71, __G710, __G76,
__G77, __GM9446`, with per-Android-version variants): 30 blobs totalling
70.4 MiB — i.e. 69 % of the 101.8 MiB library is GPU kernel machine code.
Kernel names present in `.rodata` (46 `*_ocl` entries), the expensive ops being:

```
conv_filter3x3_stride1_ocl, conv_filter3x3_stride2_ocl, conv_filter3x3_stride%d_ocl,
conv_filter5x5_stride1_ocl, conv_filter5x5_stride2_ocl, conv_direct_transpose_ocl (deconv),
conv_direct_allimg_f1s1_pw{4,8}_ph1_k{4,8}_case{0,1,2} (tiled direct conv variants),
bn_batch_p1_ocl, bn_batch_p2_ocl, bn_output_ocl, bn_output_mva_ocl, bn_history_ocl,
scale_new_bias_ocl, scale_new_no_bias_ocl, eltwiseAffineTran_ocl, slicing_ocl,
concat/permute/reshape via shuffle_ocl, tile_ocl, crop_ocl, clip_ocl,
interp_ocl, interp_z%d_ocl, interp_image2d_ocl, subpixelUp_ocl, subpixelDown_ocl,
argmax_axis{1,2}_{index,val,preindex}_ocl, pointsCurve_ocl, layer_transpose_ocl
```

The Caffe fork's layer set (`ir/model_container.json → caffe_runtime_layers`,
123 message types) confirms the nets use standard conv/BN/ReLU6 +
`RpnProposal`, `ROIAlign`, `AffineTrans`, `PointsCurve`, `SubpixelUp/Down`,
`PixelShuffle`, `GridSample` — i.e. MobileNetV2-style depthwise blocks for the
embedder and an RPN detector for Hunter.

---

## 7. Template storage

`stfaceunlock::TemplateHepler` serialises `pb::TemplateFile` (protobuf;
magic string `STFACEUNLOCK`, version string `1.0.1`, file `template_file.data`):

```
TemplateFile       {1 Version, 2 Identifier, 3 repeated SinglePersonTemplate,
                    4 ModelVersion, 5 VersionString}
SinglePersonTemplate {1 Index, 2 repeated SingleTemlate}
SingleTemlate      {1 Direction, 2 repeated Points, 3 Feature, 4 ImageInfo}
Image              {1 Format, 2 Width, 3 Height, 4 Stride, 5 Data}
KeyPoint           {1 X, 2 Y}
```

Field numbers read from the generated-code constants in `.rodata`
(`ir/model_container.json`). The template also stores the enrolment *image*
(the `Image` message) plus the 106 points and the feature vector, and is
compressed on disk (`"Compression template file"` / `Uncompression template
file"` strings). Up to `-1008` = `ST_FACE_AUTH_ERROR_TEMPLDATE_FILE_UPPER_LIMIT`
face ids are allowed.

---

## 8. Reimplementation

`reimpl/pipeline.py` (JAX, no flax/TF):

* `preprocess()` — NV21→RGB, orientation, ROI crop, bilinear resize to 112×112,
  normalize (mean/scale are **inferred**; the real values come from
  `cv_model_net_config_t` inside the encrypted container).
* `detect()` — Hunter-style backbone + RPN head (objectness + 4 box deltas).
* `align()` — 106-landmark regression + 3-dof pose + 2-way eye state.
* `quality()`, `liveness()` — small conv heads (MTNet / IM_RGB_Liveness shapes).
* `embed()` — MobileNetV2 (t=6, 17 inverted-residual blocks) → 1280 → 512-d
  L2-normalised embedding.
* `match()` — cosine similarity against a template + threshold
  (defaults from the recovered `DEFAULT_VERIFY_THRESHOLD = 0.905`).

`python pipeline.py` exits 0 and prints every stage's I/O shape; see
`reimpl/verify.txt` (input `1×112×112×3`, boxes `196×4`, landmarks `1×106×2`,
pose `1×3`, eye `1×2`, quality `1`, liveness `1`, embedding `1×512`).

Status: **ARCHITECTURE_ONLY** — random init, because the shipped weights are
encrypted (§5.3).  No numerical result from this module is meaningful.

---

## 9. Unknowns / blockers

1. **Weights** — encrypted `STEF` payloads; only a re-implementation of the
   SenseTime protector key derivation (licence → AES/RC4) would unlock them.
2. **Per-net preprocessing constants** (mean/scale, exact crop/affine for each
   of the 8 nets) — inside the encrypted `meta.conf`.
3. **Detection / quality thresholds** — set at run time, not plaintext.
4. **Per-country threshold table** — only the setter and the `area.co` cache are
   visible; the table lives in the model container.
5. **Liveness ensemble composition** — `live::GeneralFacePredictorV7..V20` and
   `FramePredictorAntispoofing*` exist but only one blob
   (`IM_RGB_Liveness_General_Full_FP32.10.1.65`) is shipped for this device.
6. **Ghidra decompilation of `libstfaceunlockocl.so`** was attempted twice with
   the shared lock; the import exhausted the 1500 s budget on the 101.8 MiB
   binary (auto-analysis), so `native/` contains capstone disassembly of the
   loader/JNI functions instead of Ghidra C.

---

## 10. Reproduce

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
R=$WS/reports/miui_biometric
APK="/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/app/Biometric/Biometric.apk"

$PY $WS/tools/apk_inventory.py "$APK" --out $R/inventory
$PY $WS/tools/dex_find.py "$APK" --regex '.' --classes-only --no-refs --max-methods-per-class 0
$PY $WS/tools/decompile_classes.py "$APK" --regex 'com\.ts\.faceauth' --out $R/sources/faceauth --max 30
$PY $WS/tools/decompile_classes.py "$APK" --regex 'com\.android\.keyguard|com\.miui\.face\.MiuiFaceService' --out $R/sources/faceunlock --max 25
$PY $WS/tools/elf_info.py $WS/samples/android/libstfaceunlockocl.so --json $R/native/elf_stfaceunlockocl.json
$PY $WS/tools/carve_models.py $WS/samples/android/libstfaceunlockocl.so --json $R/evidence/carve_stface.json
$PY $R/models/extract_blobs.py
cd $R/reimpl && $PY pipeline.py
```

Artifact hashes and command transcripts: `evidence/cmd.txt`,
`evidence/hashes.json`.

---

# 11. Camera data path (patch 2026-10-24)

Full per-stage tables with confidence tags: **`PIPELINE.md`** (pipelines A = auth,
B = enroll). This section is the condensed trace.

### 11.1 Who supplies the frames

`com.miui.face` opens the camera itself with **Camera2** — there is no
CameraService/HAL binder indirection in the app, and it needs no permission
because the manifest declares `android:sharedUserId="android.uid.system"` and
`android:persistent="true"` (so the process is started by the framework even
though the APK has no components; `evidence/manifest_strings.txt`).

```
MiuiFaceService (Binder "miui.face.FaceService", CODE_AUTHENTICATE=3)
  -> MiuiFaceService.authenticate -> ClientMonitor.startAuthen -> FaceAuthenHelper.authen(timeout)
     -> FaceUnlockManager.startFaceUnlock(cb)
        -> BaseFaceUnlockManager.startFaceUnlock: mCallback.onFaceStart(); openCamera(0)
           -> Camera2Util.openCamera -> CameraManager.openCamera(frontId, stateCallback, mCameraHandler)
                                       + getCameraCharacteristics(frontId)
           -> onOpened: createCaptureRequest(TEMPLATE_PREVIEW=1), CONTROL_AF_MODE=4
           -> openCameraSucced(id,0) -> BaseFaceUnlockManager: startPreview(null);
                                       sendEmptyMessageDelayed(1008, 3000)
              -> Camera2Util.startPreview: ImageReader.newInstance(640,480,35,1)
                 setRepeatingRequest(requestBuilder.build(), null, mCameraHandler)
     <- ImageReader.OnImageAvailable (on mCameraHandler == HandlerThread "auth_worker")
        -> new Yuv(image).getNV21Bytes(); image.close()
        -> IPreviewCallback.onPreviewFrame(byte[460800], cameraId)
           -> FaceUnlockManager.onPreviewFrame (FaceUnlockManager.java:148)
              -> FaceAuthManager.compare(frame, 640, 480, 90, true)
```

Front camera selection is by `LENS_FACING == 0`
(`Camera2Util.getFrontFacingCameraId`); failure returns `"-1"` and is reported as
`ERROR_CAMERA_ID = -1`.

### 11.2 Format, resolution, stride

| property | value | evidence |
|---|---|---|
| capture format | `ImageFormat.YUV_420_888` = **35** (`ImageReader.newInstance(w,h,35,1)`); `com.miui.image.Yuv` hard-requires 35 and Y pixel-stride 1 | `Camera2Util.startPreview`, `com.miui.image.Yuv` |
| conversion | YUV_420_888 → **NV21** in Java, byte[] of `w*h*12/8` = 460,800 B; V plane written before U per 2-byte pair | `Yuv.getNV21Bytes` |
| resolution | **640×480** — `AbstractCameraUtil.width/height`, never assigned anywhere else | `AbstractCameraUtil.java:15-16`, `grep angle/width` over `sources/` |
| SDK image struct | `STImage{byte[] mImageData, int mFormat=3, mWidth, mHeight, mStride=640}` | `FaceAuthManager.compare` → `new STImage(bArr, NV21, w, h, getStride(...))` |
| stride rule | `getStride`: ×3 for BGR888/RGB888, ×4 for BGRA/RGBA, else `width` (NV21 → 640) | `FaceAuthManager.getStride` |
| pixel-format value | `CvPixelFormat.NV21 = 3` = native `ST_PIX_FMT_NV21 = 3` (DWARF enum) | `CvPixelFormat.java`, `native/dwarf_info.txt:263-276` |
| mirroring | none (byte[] forwarded unmodified; no mirror/flip in any decompiled class) | `sources/` grep |

### 11.3 Rotation / orientation bitmask

`AbstractCameraUtil.angle` is the **constant 90** and is never recomputed from
`CameraCharacteristics.SENSOR_ORIENTATION`; it is passed per frame and converted:

```java
getFaceOrientation(0)=UP(1) getFaceOrientation(90)=RIGHT(8)
getFaceOrientation(180)=DOWN(4) getFaceOrientation(270)=LEFT(2) else UNKNOWN(15)
```

(`FaceAuthManager.getFaceOrientation`; enum values are the native bitmask
`ST_FACE_UP=1, LEFT=2, DOWN=4, RIGHT=8, UNKNOWN=15`, DWARF 0x9ec8.)

On this device every frame therefore reaches the SDK as
`st_auth_set_face_orientation(8)` immediately before `st_auth_enroll`/`st_auth_unlock`.
Native then rotates the cv image (`ImageHepler::RotateImage` @0x14154c).

### 11.4 ROI (`setDetectArea` / `set_roi`)

* **Auth: no ROI at all.** `FaceAuthManager.compare(...)` always calls
  `FaceAuthNative.setRoi(null, rc)` → `st_auth_set_roi(0)`; the full frame is searched.
* **Enroll: ROI from the caller.** `FaceEnrollHelper.setDetectArea(Rect)` stores 4 ints;
  before every `enroll()` the rect is rotated in Java
  (`RotateMaskRect(rect, w, h, angle, /*mirror*/true)`) and passed to
  `st_auth_set_roi(&st_rect_t)`; `null` clears it. Native re-rotates it in
  `FaceUnlock2DInterFace::RotateRect` / `ImageHepler::RotateRect`.
* ROI semantics on violations: status 105 → app 33 (`not in ROI`), 131-134 → 8-11
  (offset left/top/right/bottom).

### 11.5 Timestamp and cadence

* **No timestamp crosses the boundary.** The frame is a bare `byte[]`; `Image.getTimestamp()`
  is never called, `STImage` has no time field. Java measures wall-clock around
  `compare()` (`System.currentTimeMillis`), native measures `std::chrono::steady_clock`
  for its own log lines only.
* Cadence: sensor-driven repeating request; `ImageReader` holds **1** image and the frame
  is closed immediately, so a slow SDK simply drops frames. Auth additionally drops frames:
  `onPreviewFrame` returns while `mIsDetecting` and, after a frame with no face
  (status 5), skips the next one (`mSkipIfNotFound`).
* Enroll uses every frame on `munch` (`isNeedGetFrameNonsequence()` false), every 5th on
  the 28 listed devices.
* Session flags on `munch`: `isNeedOperatingMode()` true ⇒ auth session created with
  `SessionConfiguration` mode **32774**; `isNeedMoreStreamInAuth()` false ⇒ no extra
  dummy `SurfaceTexture` target. (Arrays dumped in `evidence/resources_arrays.txt`.)

---

# 12. JNI lifecycle and native ABI

Exact prototypes, struct layouts, field-ID offsets and return conventions:
**`native/jni_signatures.txt`** (from DWARF + Ghidra 12.1.2 output
`native/jni_all.c`, 37/37 functions decompiled). Native API prototypes as embedded
in `.rodata`: **`native/api_prototypes.txt`** (region 0x7520c0-0x752a80).

### 12.1 Call sequence used by this app

```
FaceAuthManager.initHandler(dir)                 // dir = getFilesDir() -> /data/data/com.miui.face/files
FaceAuthManager.initAll("model1","model2")
  FaceAuthNative.initLicense(LICENSE_STR)        -> st_face_init_license(const char*)      [0xfc800]
  FaceAuthNative.createFaceAuth(dir,0,rc)        -> st_auth_init(const char*, unsigned)     [0x10e3e8]
     (rc[0]==0) configFaceAuthThresholds(area)   -> st_auth_set_threshold_by_international_code
per frame:
  FaceAuthNative.setRoi(null,rc)                 -> st_auth_set_roi(NULL)                  [0x10c984]
  FaceAuthNative.setOrientation(8,rc)            -> st_auth_set_face_orientation(8)        [0x10ca00]
  FaceAuthNative.auth(img,0,null,rc)             -> st_auth_unlock(img,cfg,ids,n)          [0x10cb18]
        -> stfaceunlock::FaceUnlock2DInterFace::AuthUseId(img,cfg,ids,n)  [0x14b3a8]
  if rc[0]==201: FaceAuthNative.getLastUnlockID  -> st_auth_get_last_unlock_id(int*)        [0x10d5bc]
enroll:
  FaceAuthNative.setRoi(rotatedRect,rc); setOrientation(8,rc)
  FaceAuthNative.enroll(img, 0, rc)              -> st_auth_enroll(img,cfg,&userId)         [0x10ca7c]
        -> FaceUnlock2DInterFace::Enroll(img,cfg,&status,/*template out*/0)  [0x1496ec]
release:
  FaceAuthNative.setStandByMode()                -> st_auth_set_standby_mode()              [0x10d66c]
  FaceAuthNative.releaseFaceAuth()               -> st_auth_deinit()                       [0x10c7a4]
```

### 12.2 What crosses JNI (no copies at the model boundary)

| direction | object | mechanism |
|---|---|---|
| Java→C | `byte[] mImageData` | `GetByteArrayElements` pins the array; pointer stored in `st_image_t.data`; released with `ReleaseByteArrayElements(..., 0)` after the call (`native/jni_all.c:360`) |
| Java→C | `int[] _resultcode` | `GetIntArrayElements`; native writes `*rc = result`; `ReleaseIntArrayElements` |
| Java→C | `int[] faceids` (auth) | in/out; `GetArrayLength` is the capacity given to `st_auth_unlock`. **The FaceUnlockManager path always passes `null`**, i.e. "match all enrolled ids" |
| C→Java | face id (enroll) | **return value** of `Java_..._enroll` (the `user_id` out-param of `st_auth_enroll`), while `*rc[0]` carries the status |
| C→Java | unlock id | separate `getLastUnlockID()`; not forwarded to SystemUI (see §13) |
| C→Java | `FaceIDInfo[]` | `st_auth_get_template_info` → `st_face_id_info_t[]` → `st_faceid_info_array_to_java_faceidinfo` |
| C→Java | detect callback | `st_detect_t*` → `FaceDetectedInfo(mIsDetected, mFaceRect)` via `CallStaticVoidMethod`; requires `GetEnv(JNI_VERSION_1_6)` + `AttachCurrentThread` because it fires on a native worker. Not enabled in this app (`FaceAuthManager.setCallback` has no caller in the decompiled integration classes) |

### 12.3 Enrollment vs authentication

| aspect | enroll (`st_auth_enroll`) | auth (`st_auth_unlock`) |
|---|---|---|
| Java API | `saveFeature(byte[],w,h,angle)` → `EnrollResult(mFaceId, mEnrollStatus, mNativeStatus)` | `compare(byte[],w,h,angle,true)` → int app status (0 = unlocked) |
| ROI | set from the guide rect (rotated), every call | always `NULL` |
| config word | `mFaceAuthEnrollConfig` = **0** | `mFaceAuthCompareConfig` = **0** (the unused overload would set bit0 when liveness off and bit3 when `mLivenessModePath == null`) |
| output | face id (JNI return) + status (`rc[0]`); template written by `FeatureManager::AddUser` | status only; `rc[0] == 201` = `ST_FACE_AUTH_STATUS_UNLOCK_OK` |
| repeat | multi-step: status 19 (`KEEP`) asks the caller to move the head, EXT_CMD 0 resumes, 36 forces reset | one frame at a time until unlock or 3 s timeout |

### 12.4 Template file and matching

* Path: `/data/data/com.miui.face/files/template_file.data`
  (`FaceUnlockManager.MODEL_PATH`), native literal `template_file.data` at `.rodata` 0x762278;
  directory handed over by `st_auth_init` → `SetTemplateFilePath` (creates the dir).
* Format: protobuf `pb.TemplateFile` (magic string **`STFACEUNLOCK`**, version string
  **`1.0.1`**), messages `TemplateFile{1 Version,2 Identifier,3 SinglePersonTemplate,
  4 ModelVersion,5 VersionString}`, `SinglePersonTemplate{1 Index,2 SingleTemlate}`,
  `SingleTemlate{1 Direction,2 Points,3 Feature,4 ImageInfo}`; the feature blob goes
  through `cv_feature_serialize` / `cv_feature_degarble`, and the whole file is compressed
  (`"Compression template file"`).
* Matching: `FeatureManager::Unlock` (0x1358ec) walks every stored template, calls
  `FaceVerifyManager::CompareFeature` (0x126064, thin wrapper) →
  `FaceVerify::CompareFeature` (0x123bd0) → `cv_common_feature_comparator_get_distance`
  (Eigen dot product, plus a Q16 fixed-point path scaled by 2^-16 at literal 0x230d04) →
  `cv_common_feature_comparator_normalize_score_by_model` →
  `stsdk::FeatureCmp::normalizeScore` (0x2446b4) → `protector::NormModel::Run(float&)`
  (piecewise table from `st_model.ScoreNormalizationParameter`), then compares the
  normalised score against the configured threshold (`fcmpe`, `native/big_FeatureManager_Unlock.s`).
  Threshold default **0.905f** (`DEFAULT_VERIFY_THRESHOLD`, `.data` 0x4f2c090 = 0x3f67ae14),
  applied by `st_auth_init` through the pointer at 0x4f2a210.
* `stsdk::CosineDistance` and `stsdk::FeatureCmp::set_compare_threshold(float)` exist as
  symbols; the pipeline is L2-normalised embedding + cosine (inferred from
  `verif_engine::Engine::Normalize` / `GetPCAFeature` + dot-product-only distance code).
* Liveness threshold 0.9f from `DEFAULT_LIVENESS_THRESHOLD` (`.data` 0x4f2c08c = 0x3f666666),
  applied through pointer 0x4f29c30.
* Template upper limit: status -1008 → app 35.
* No caller of `setAreaCode` / `setFaceAuthMode` / `setFaceAuthEnrollConfig` /
  `setFaceAuthCompareConfig` / `setImageFormat` exists among the decompiled integration
  classes, so on this build the config words stay `0`, the pixel format stays `NV21` and the
  shipped thresholds (0.905 verify, 0.9 liveness) are the ones actually in force.

---

# 13. Output consumption (unlock decision)

```
native 201 -> app status 0 -> FaceUnlockManager.handlePassed()
   mCallback.onFaceAuthenticated()
     -> MiuiFaceService worker MSG_ON_AUTHENTICATE(3, arg1=123456)
        -> ClientMonitor.onAuthenticationSucceeded(123456,
              FaceCompat.newInstance("Miuiface1", 1, 123456), 0)
           -> receiver.transact(203)                       // "receiver.FaceService"
     -> FaceAuthenHelper.releaseOnWorkerThread() -> FaceUnlockManager.release(true)
        -> MSG_FACE_UNLOCK_RELEASE posted 60 000 ms later
```

* Receiver protocol (`ClientMonitor`, constants): 201 enroll result, 202 acquired,
  **203 authentication succeeded**, 204 authentication failed, 205 error,
  206 removed, 207 pre-init, 261 lockout reset, 301 ext command; descriptor
  `"receiver.FaceService"`.
* The face id handed to SystemUI is the constant `123456`
  (`KeyguardUtils.DEFAULT_DEVICEID`), not `getLastUnlockID()`.
* Liveness/quality gating happens entirely in native: status 2 (`HACKNESS_FAILED`) → app 14,
  117/102 → app 4, 103/104/130 (eye/mouth/nose blocked) → 21/23/37, 123 → 30 etc.
  Every non-zero status is delivered as an *acquired* code (202) so the UI can coach the user;
  only status 0 unlocks.
* Fallback: this APK contains no fallback path. It reports success/failure through the
  Binder receiver; choosing PIN/pattern or another modality is SystemUI/biometrics policy
  (outside `Biometric.apk`). `FaceService` also emits `ERROR_SERVICE_IS_BUSY (2001)` and
  `ERROR_SERVICE_IS_IDLE (2002)` to non-SystemUI callers and queues SystemUI as a pending
  client (`mPendingClients`).

---

# 14. Threading, lifecycle, timeouts

| concern | behaviour | evidence |
|---|---|---|
| camera thread | `HandlerThread("auth_worker")` created in `BaseFaceUnlockManager.initAuthenThread`; every camera callback and every `compare()`/`saveFeature()` runs on it | `BaseFaceUnlockManager`, `Camera2Util` |
| service thread | `HandlerThread("serv_worker")` in `MiuiFaceService.initWorkerInfo`; all client callbacks are marshalled there | `MiuiFaceService` |
| native threads | `st_auth_init` → `FaceUnlock2DInterFace::StartThread` creates **2 detached `std::thread`s**: one `ThreadManager<FaceVerifyManager::ExtractFeature>`, one `ThreadManager<FaceHacknessManager::HacknessDetect>` | `native/big_StartThread.s` |
| per-frame dispatch | `AuthUseId`/`Enroll` push `std::function<int()>` tasks into `BlockingQueue`, then block on `mutex` + `condition_variable` until results arrive | `native/big_AuthUseId.s`, `native/big_Enroll.s` |
| init timing | licence → `st_auth_init` (model handles + thresholds + threads) happens in `FaceUnlockManager.initAll()`, i.e. on pre-init (`CODE_PRE_INIT_AUTHEN`) or on the first `authenticate` before the camera opens; measured and logged as `init all time=` | `FaceUnlockManager.initAll`, `FaceAuthenHelper.tryPreInitAuth` |
| auth timeout | 3000 ms (`FaceAuthenHelper.mTimeout`; `setRunOutTime` ignores ≤2000) → `MSG_FACE_UNLOCK_WORK_TIMEOUT (1008)` → `stopDetect()` + `onFaceAuthFailed` | `BaseFaceUnlockManager.openCameraSucced` |
| camera retry | 5 attempts, backoff `(5-retry)*200 ms`, only for error 1/2 (in use / max cameras) | `BaseFaceUnlockManager.UNLOCK_RETRY_TIME`, `openCameraSucced` |
| enroll timeout | 30 000 ms `mDelayedHide` | `FaceEnrollHelper` |
| teardown | `release(true)` defers `MSG 1007` by 60 000 ms → `setStandByMode()` + `releaseFaceAuth()`; enroll success releases right away; `MiuiFaceService.mCheckTemplate` reconciles template vs. SharedPreferences on start (max 3 restarts/min) | `BaseFaceUnlockManager.release`, `FaceUnlockManager.releaseFaceUnlock`, `MiuiFaceService.1` |
| licence/TEE | `initLicense` must run first; `setTeeCommunicationFunc` `dlopen`s `libst_client.so` and `dlsym`s `tee_communication_func`, returning -4 if absent (normal on this build) | `native/jni_all.c:1080` |

---

# 15. Machine-readable pipeline

`data_pipelines` in `findings.json` (entries `auth` and `enroll`) mirrors
`PIPELINE.md`: trigger → transport → preprocess → model → postprocess → consumer →
fallback, each step carrying `confidence` and `refs`.
