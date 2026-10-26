# ExtraPhotoGlobal — data-pipeline integration contract

Scope: the three on-device AI/render pipelines of `com.miui.extraphoto`
(ExtraPhotoGlobal 1.8.7.4.0-global, arm64-v8a):

* **A. Document photo** — scan → corner quad → perspective crop → enhance /
  dewarp / de-handwrite → JPEG.
* **B. Portrait relighting** — NV21 origin + depth → `MiDualPhotoJni` →
  `libmibrain_relighting_jni` → `AILAB_PLI_*` → GLES light-map render → NV21.
* **C. Dual-camera bokeh** — NV21 origin/effect + depth → `MegviiDualPhotoJni`
  → `wa::Refocus` → NV21.

Confidence tags: **confirmed** = observed in dex bytecode, disassembly,
decompiler output or file bytes; **inferred** = follows from two or more
confirmed facts but the exact link was not observed; **unknown** = not
recoverable statically from this APK.

Every address below is a virtual address inside the named `.so`; Ghidra output
uses `imagebase 0x100000`, so Ghidra `FUN_0017dxxx` = vaddr `0x7dxxx`.

---

## A. Document photo (`DocPhotoActivity` → `DocProcessor` → `libdoc_photo_process.so`)

### A.1 Data sources / triggers

| Trigger | Exact entry | Confidence | Evidence |
|---|---|---|---|
| Edit an already-tagged document photo | `com.miui.extraphoto.docphoto.document.DocPhotoActivity.onCheckPermissionSucceed` → `Storage.fromIntent(intent)` → `new DocPhotoManager(storage, isDocPhoto, false, mDocProcessor)` → `parsePhotoAsync()` | confirmed | `sources/docphoto_flow/classes/...DocPhotoActivity.java:231-255`; manifest component `...DocPhotoActivity` |
| Camera post-processing (raw doc capture) | `DocPhotoPostProcessingActivity` → `new DocPhotoManager(storage, enhanceType, false, true, mDocProcessor, watermark)` → `parsePhotoAsync()` | confirmed | `sources/docphoto_flow/classes/...DocPhotoPostProcessingActivity.java:106,147-150` |
| ID-card / PPT modes | `DocumentProcess$DocumentType {DEFAULT, ID_CARD, PPT}` ordinal passed to native | confirmed | `sources/access/classes/com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$DocumentType.java` |
| SD-card / MediaStore source | `Storage.getInputStream()` (ContentResolver or file); export through `XStorage.insert/openOutputStreamSafely` | confirmed | `DocPhotoManager$DocPhotoParser.parseExtension`, `DocPhotoUtils.writeToFileDirectly` |
| Native library load | `DocumentProcessJNI.<clinit>`: `loadLibrary("doc_photo_c++_shared")`, `loadLibrary("doc_photo_process")`, `nativeGlobalInit()` | confirmed | `sources/docphoto2/classes/...DocumentProcessJNI.java` |

Lifecycle: `DocProcessor.SafeProcessor.init()` runs once per process on first
use; `nativeInit(cacheDir, modelDir)` returns the native singleton pointer;
`nativeRelease(ptr)` on `DocProcessor.release()` (spawned on a raw
`new Thread`). `nativeInitAndRun` is an extra first-run warm-up: Java calls it only when
`cache/mace_cl_compiled_program.bin` is absent, and the native method runs the
engine's second vtable slot with `(cacheDir, modelDir)`. Its role as the
OpenCL-cache generator is **inferred** (the cache file is the only state it can
produce, and `nativeInit` alone does not create it).

### A.2 Transport and format

| Stage | Format | Detail | Confidence |
|---|---|---|---|
| Source | JPEG/HEIF file via `InputStream` | EXIF orientation read with `ExifUtil.getExifInterface`; crop points and enhancement type stored in XMP (`http://ns.xiaomi.com/photos/1.0/camera/ XMPMeta`) | confirmed |
| Preview decode | `BitmapFactory.decodeStream` with `inSampleSize` = highest power of two so that w/h fit the screen (`ImageUtils.computeInSampleInfo`) | full-image EXIF rotation applied by `ImageUtils.generateBitmapWithOrientation` | confirmed |
| Save decode | `BitmapFactory.decodeStream(inSampleSize = BigBitmapLoadUtils.calculateInSampleSize(...))` | sample size chosen from total RAM / large memory class / 96 MB–436 MB budget | confirmed |
| Native bitmap input | **Android Bitmap, format must be `ANDROID_BITMAP_FORMAT_RGBA_8888` (1)** | `doc_get_bitmap_info.s` (`0x78a00`): `AndroidBitmap_getInfo`; `ldr w8,[info+0xc]; cmp #1; b.ne fail` | confirmed |
| Native pixel staging | RGBA8888 → **RGB, 3 bytes/pixel, interleaved, R first** | `doc_rgba_to_rgb.s` (`0x7884c`): `ld4 {v0..v3}` / `st3 {v0,v1,v2}`; stride converted to `w*h*3` | confirmed |
| Native matrix | OpenCV `cv::Mat` type `0x10` = `CV_8UC3` | `FUN_00182f54` / `FUN_00183798`: `FUN_00180f34(&mat,&wh,0x10,pixels,0)` | confirmed |
| Bitmap output | New `Bitmap` `ARGB_8888`, source colour space, alpha = 255 | `doc_bitmap_class.s`/`doc_bitmap_create.s` (`0x78be4`,`0x78ce4`): `Bitmap.getColorSpace`, `Bitmap.createBitmap(IILandroid/graphics/Bitmap$Config;ZLandroid/graphics/ColorSpace;)`, `Config.valueOf("ARGB_8888")`; `doc_rgb_to_rgba.s` (`0x78904`) writes 4th byte `0xff` | confirmed |
| YUV variant (exported, unused by this dex) | `byte[]` NV21 (Y plane + interleaved VU) with explicit w/h | `nativeScanDocumentYUV`/`nativeCropAndEnhanceYUV` call `GetByteArrayElements`; `nativeCropAndEnhanceYUV` converts result to RGBA then creates a bitmap | confirmed (code), inferred (NV21 label) |

Threads: `PhotoManager.ParseTask` / `PreviewTask` are
`AsyncTask.executeOnExecutor(THREAD_POOL_EXECUTOR)`; `DocProcessor.SafeProcessor`
serialises every native call on `mLock` (`synchronized`). Export is a separate
`ExportTask` AsyncTask.

### A.3 Preprocessing

1. **EXIF orientation**: decode then rotate by `ImageUtils.getDegreeByOrientation`
   (3/4→180°, 5/6→90°, 7/8→270°) — **confirmed**.
2. **Coordinate spaces**: scan points are returned in the *delegate* bitmap
   space (subsampled + oriented). For the full-resolution render,
   `MatrixUtils.mapPoints(points, MatrixUtils.inverseMatrix(delegateMatrix))`
   converts them to the raw, un-rotated full-resolution pixel space before the
   native crop — **confirmed** (`DocPhotoManager.render()`; `Util.getDelegateBitmapMatrix`).
3. **Scan input resize**: `doEdgeDet` (`0x8779c`) uses `mov w8,#0x180`
   (=384) for both dimensions and calls `cv::resize` (`0x383520`) plus a
   letterbox/pad helper; the model is invoked with a `384x384x3` tensor —
   **confirmed** (`native/doc_scan_model_init.s`, `/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/xp/doedgedet.s`
   excerpt in `evidence/09_doc_pipeline.txt`).
4. **Scan normalisation**: the JNI layer converts `CV_8UC3` to the MACE input
   tensor with `convertTo(..., alpha=1.0, beta=0)`; the explicit `1/255`
   divide is *not* in the JNI staging code. The equivalent SNPE DLC export of
   the same network starts with `truediv(255)` (`ir/document_model_graph.json`,
   report §3.3), so normalisation is inside the graph — **inferred**.
5. **Crop**: native `CropAndEnhance` (`0x83798`) validates the 4-point polygon
   via vtable slot 10 (`0x8493c`, angle/cross-product threshold 30.0f =
   `0x41f00000`), computes output size from the quad + cached aspect ratio
   (`0x182750`, `param_1[0xf]` cache reused when the Java `z` save flag is
   set), then warps with `cv::getPerspectiveTransform`/`warpPerspective`
   (`0x4710d0`/`0x470b84`) into a new `CV_8UC3` buffer — **confirmed**.
6. **Enhance/dewarp/handwrite** operate in place on the RGB buffer of the
   bitmap (`nativeEnhanceBitmap`) or on the cropped buffer — **confirmed**.

Exact Java → JNI mapping (`DocProcessor.SafeProcessor`, all inside
`synchronized (mLock)`):

| Java method | JNI call | Result handling |
|---|---|---|
| `init()` | `nativeInitAndRun(cacheDir, modelDir)` then `nativeInit(cacheDir, modelDir)` | stores `long mNativeObj` |
| `doScan(Bitmap, DocumentType)` | `nativeScanDocumentBitmap(mNativeObj, bmp, type.ordinal(), new float[8], 0)` | returns the `float[8]` (zeros on failure) |
| `doCrop(Bitmap, float[], DocumentType)` | `nativeCropImageBitmap(mNativeObj, bmp, points, type.ordinal())` | null on failure → caller returns bmp |
| `doCropAndEnhance(Bitmap, float[], EnhanceType, DocumentType, boolean)` | `nativeCropAndEnhanceBitmap(mNativeObj, bmp, points, enhance.ordinal(), doc.ordinal(), z)` | null on failure |
| `doEnhance(Bitmap, EnhanceType, boolean)` | `nativeEnhanceBitmap(mNativeObj, bmp, enhance.ordinal(), z)` | non-zero status → returns `null` |
| `doUndistortion(Bitmap)` | `nativeDewarpBitmap(mNativeObj, bmp)` | in place, returns the same Bitmap |
| `doDeHandwrite(Bitmap, Bitmap)` | `nativeHandwriteRemoveBitmap(mNativeObj, bmp, bmp2)` | both mutated in place |
| `getPointsModeColor(Bitmap)` | `nativeGetDelegateColor(mNativeObj, bmp, new int[3])` | `Color.rgb(r,g,b)` |
| `release()` | `nativeRelease(mNativeObj)` on a new Thread | `mReleased = true` |

Model contract (from the embedded descriptor tables):

| Model | Input | Output | Descriptor offset | Confidence |
|---|---|---|---|---|
| `hed_model` / `ScanModel` (`hed_input`→`img_output`) | 1×384×384×3 | 1×384×384×2 | `0x436fe2`, dims at `0x437000` and `0x437028` | confirmed |
| `enhance_model` / `EnhanceModel` (`img_input`) | 1×**512**×512×3 | not captured | `0x437b61`, dims at `0x437b80` | confirmed |
| `dewarp_coarse_model` | (193×193×3 class block) | — | `0x437e5c` | inferred |
| `dewarp_fine_model` / `DewarpModelFine` | 1×385×385×3 (+ 1×193×193×3) | 1×385×385×2 | `0x437ea0`, dims at `0x437ec0-0x437f18` | inferred |
| `segment_model`, `inpaint_coarse_model`, `inpaint_fine_model`, `handwrite_model`, `demoiring_model` | names only | — | `0x438077`, `0x438085`, `0x43810f`, `0x4384c6`, `0x437fce` | confirmed names |

The shipped **SNPE DLC** asset is `1×512×512×3 → 1×512×512×1`, which matches
`enhance_model`'s 512 spatial size, **not** the 384×384 scan model. The APK's
native library is a MACE runtime and contains no SNPE/DLC parser, so the DLC is
copied to `filesDir/doc_model/dlc` and passed to `nativeInit` but is not
observably consumed — **inferred/unknown**.

### A.4 Model invocation

| Item | Value | Confidence |
|---|---|---|
| Runtime | MACE + OpenCL (`mace.pb.cc`, `Create MaceEngine from model pb`, `BuildMVNKernel`, OpenCL kernels, `mace_cl_compiled_program.bin`) | confirmed |
| Engine owner | single native `DocumentProcess`-like object returned by `nativeInit`; vtable base `0x17c3488` (derived from the `.data.rel.ro` pointer run); slot 2 = scan (`FUN_00182f54`), slot 3 = crop+enhance (`FUN_00183798`), slot 4 = enhance (`FUN_00183eb4`), slot 11 = clamp points (`FUN_00185118`), slot 13 = deleting destructor (`FUN_00185200`) | confirmed (slot functions), inferred (vtable base address) |
| Model payload | **embedded in `libdoc_photo_process.so`** (25,029,048 B; `.rodata` 19.5 MB); no model files in APK | inferred (no external file exists, names+dims embedded) |
| Engine creation | model name string + 4 `int64` dims are passed to `FUN_001892f8` at `0x8908c..0x890f0`; `hed_model`/`hed_input`/`img_output` copied into the engine object | confirmed |
| OpenCL cache | `filesDir/doc_model/cache/mace_cl_compiled_program.bin`; per-SoC asset copied when `DocPhotoAccessUtils.isNeedUseCache()`, otherwise generated by `nativeInitAndRun` | confirmed |
| Input binding | direct CPU pointer to RGB staging buffer converted to MACE tensor (no texture/ByteBuffer) | inferred |
| Cadence | on demand per user action; not per frame | confirmed |

### A.5 Output postprocessing

1. EdgeDet produces `1×384×384×2` float. JNI-side: output is converted with
   `alpha=255.0` to 8-bit (`0x87b04-0x87b24`), then combined with constants
   `0.5` / `1.0` (`0x2c0b10`, `0x2c0910`) — **confirmed** (calls/constants),
   exact channel semantics **inferred** (edge + segment).
2. Quad extraction: `FUN_00182f54` → `FUN_00182a80(handle, points, w, h)`;
   strings `calcQuad:`, `cornerCase calcCorners:`, `add boardline calcCorners:`,
   `Doc2.0 Use minAreaQuad`, `getKeyQuadPtsAndStateByIOU normalizeQuad_1/2`
   show mask→contour→quad and IOU normalisation — **inferred**.
   `ScanModel` float parameters at `0x437054`: `0.85, 0.7, 0.15, 0.1, -90.0`
   — **confirmed bytes**, role inferred as thresholds/angle prior.
3. Points are written back as `float[8]` = 4 `(x,y)` corners via
   `SetFloatArrayRegion`; length checked against `GetArrayLength`; if the
   vector is empty the JNI inserts the full-image rectangle
   `(0,0),(w-1,0),(0,h-1),(w-1,h-1)` and then clamps every point to
   `[0,w-1]×[0,h-1]` (`0x85118`) — **confirmed**.
4. Crop output size `out_w/out_h` is returned by the engine and used to
   allocate the destination RGB buffer and the final `ARGB_8888` bitmap —
   **confirmed** (`nativeCropAndEnhanceBitmap` logs `crop out_w:, out_h:`).
5. Enhance type ordinal: `RAW=0, COLOR=1, BIN=2, GRAY=3, FILTER_ID_CARD=4,
   DEMOIRE=5` — passed straight through — **confirmed**.

### A.6 Consumer / action

**Preview** (`PhotoManager.PreviewTask` → `DocPhotoManager.renderPreview()`):
decode delegate bitmap → `doCrop(points)` → `doRotate(rotateDeg)` →
optional `doDeHandwrite` → `doUndistortion` → `doEnhance(type)` →
`doDemoire` → `doHandMode` → `doWatermark` → `Bitmap.copy(ARGB_8888)` stored in
`mCurrentPreview`, delivered on the UI thread through
`PreviewCallback.onRefresh` (`DocPhotoActivity.setPreviewBitmap`).

**Save** (`ExportTask.doInBackground` → `DocPhotoManager.render()` full-res):
full decode → crop with inverse-mapped points → rotate → optional de-handwrite
→ undistortion → enhance → demoire → hand-mode composition → watermark.

* Encoding: `DocPhotoUtils.exportJpegWithExif` — **JPEG quality 97**
  (`bitmap.compress(JPEG, 97, exifWriterStream)`), ICC profile injected for
  wide-gamut bitmaps — **confirmed**.
* Metadata: `defaultExportExif` copies source EXIF, sets MIME type 4 (JPEG),
  clears thumbnail, orientation forced to `1`, clears `focusPoint`,
  `depthMapVersion`, `motionPhoto`, `mtType`, `XiaomiComment`, `UserComment`,
  `Xmp`; keeps Xiaomi maker note — **confirmed**.
* Re-editable document output (`DocPhotoExporter`): EXIF `docPhoto = 0x01`,
  XMP `docPhoto` element with `version=1`, `enhanceType`, `cropPoints` (8
  comma-separated floats), `rawLength`, optional `privacyWatermark`; the
  original JPEG is appended after the enhanced JPEG so that
  `DocPhotoParser.parseExtension` can `skip(fileLength - rawLength)` and
  re-edit — **confirmed**.
* Storage: `XStorage.insert(relativePath, tag)` → `writeToFileDirectly` →
  `BaseMediaUtils.triggerScanFile` / `PhotoManager.updateImageUri`
  (`MediaStore` row with `image/jpeg`, width/height, lat/long) →
  `MediaUtils.saveToCloudAlbum` → `ExportTask.onPostExecute` →
  `DocPhotoActivity.onExport(File,Uri)` → `setResult(-1, Intent(data=uri,
  type="image/jpeg", extra_photo_edit_type="extra_photo_editor_type_doc_photo"))`.
  No share sheet is launched by this component; the caller receives the URI.

### A.7 Failure and fallback

| Failure | Behaviour | Confidence |
|---|---|---|
| Bitmap not `ARGB_8888`, null pixels, `AndroidBitmap_lockPixels` failure | `doc_get_bitmap_info` returns 0; JNI returns null/status 1 | confirmed |
| `nativeInit` engine init failure | returns `0`; every subsequent scan/crop/enhance returns null/1 (no engine) | confirmed |
| Scan returns no quad | full-image rectangle inserted; crop then succeeds with the whole frame | confirmed |
| `nativeEnhanceBitmap` non-zero | `DocProcessor.SafeProcessor.doEnhance` sets bitmap to `null` (caller does not null-check before watermark) | confirmed (code path), risk inferred |
| Parse failure (`parseBase`/`parseExtension` false) | `DocPhotoActivity` shows `doc_photo_not_support` toast and exits | confirmed |
| Export failure | `ExportTask.onPostExecute(false)` → `ExportCallback.onError()` → `DocPhotoActivity.onExit()` | confirmed |
| Process released while running | `SafeProcessor` returns the input bitmap unchanged (`mReleased`) | confirmed |
| MACE/OpenCL unsupported device | `nativeSupportFeatures`/`nativeIsAvailable` exports exist; Java gate is `DocPhotoAccessUtils` codename allow-list | confirmed (gate), native feature list unknown |

### A.8 Confidence and open questions

| Claim | Confidence | Evidence | Would settle it |
|---|---|---|---|
| Native accepts only RGBA_8888 bitmaps | confirmed | `doc_get_bitmap_info.s` format check `cmp #1` | — |
| Pixel staging is RGB 3-byte, R first | confirmed | `ld4`/`st3` in `0x7884c`, inverse in `0x78904` | runtime dump |
| Scan model input is 384×384×3, output 384×384×2 | confirmed | `0x437000`, `0x437028`, `0x89084` init | — |
| Enhance model input 512×512×3 | confirmed | `0x437b80` | — |
| `/255` normalisation inside graph | inferred | DLC first layer Div 255; JNI uses `convertTo` alpha 1.0 | decode embedded MACE pb |
| Quad algorithm = contour + minAreaQuad + IOU | inferred | strings + `FUN_00182a80` call | decompile `0x182a80` |
| `document_model.dlc` is unused by this build | inferred | no SNPE strings; MACE embedded models | runtime file-access trace |

### A.9 Reimplementation mapping

| Pipeline stage | JAX module | Contract | Status |
|---|---|---|---|
| Edge/scan network | `reimpl/document_hed.py` | `(1,512,512,3)` float → `(1,512,512,1)` sigmoid; real dequantised DLC weights | reproduces the **DLC asset**, not the shipping 384×384×2 MACE `ScanModel` — `runs_unverified` |
| Postprocess (quad) | not implemented | mask → 4 corners | ARCHITECTURE_ONLY (algorithm described, no weights/constants calibration) |
| Crop/warp | not implemented | 4 points + aspect ratio → rectified image | out of ML scope |
| Enhance/dewarp/dehandwrite | not implemented | separate MACE models, no weights extracted | unknown |

---

## B. Portrait relighting (brain relighting)

### B.1 Data sources / triggers

| Trigger | Exact entry | Confidence |
|---|---|---|
| Effect selected in relighting UI | `RelightingOperatingFragment.onSelectPosition(int)` → `ChangeEffectRunnable.run()` → `mRelightingProcessor.enableRelightingEffect(config.getEffectType(), context)` | confirmed |
| Touch/drag light source | `RelightingGestureView.OnPositionChangeListener` → `RelightingOperatingFragment.setLightSource(f,f2)` | confirmed |
| Save | `RelightingOperatingFragment.onSave()` → `SaveRunnable` on the render handler | confirmed |
| Photo init | `DualPhotoOperatingFragment` → `MiBrainRelightingProcessor.initProcessor(provider, context)` | confirmed |
| Library load | `AlgoManager$MiBrainRelightingAlgo.load()`: `relight_only_extraphoto`, then `mibrain_relighting_jni` | confirmed |

All native calls execute on the GL/render thread
(`mDisplay.getRenderHandler().postAtTime(..., "token_set_light_source")`,
`runOnRenderThread(SaveRunnable)`).

### B.2 Transport and format

The Java-side `NativeImage` is **not** a Bitmap pointer: `DualPhotoJni` keeps
an `ASVL_OFFSCREEN` descriptor allocated by `generateNativeImage`
(`librefocus.so`, symbols `allocImage`, `convertBitmapToOffscreenData`,
`getPointerByImage`, `copyNv21`, `encodeyuv420sp`, `encodeyuvToRGBA`).

```
ASVL_OFFSCREEN (64 B, little endian)      offset   source
  u32 pixel_format = 1 (ASVL_PAF_NV21)     +0x00   constant confirmed at 0xeabc; label inferred
  s32 width                                +0x04   confirmed
  s32 height                               +0x08   confirmed (also copied to +0x0c)
  u8* plane[0]  (Y, w*h)                   +0x10   confirmed
  u8* plane[1]  (interleaved VU, w*h/2)    +0x18   confirmed
  s32 pitch[0], pitch[1]                   +0x30/+0x34 confirmed
```

* **Pixel format**: NV21 — Y plane then interleaved V/U. Confirmed by the
  exported conversion symbols (`encodeyuv420sp(int*, uchar*, int, int)`,
  `copyNv21`, `encodeyuvToRGBA`) and by `configYUVData`'s split loop, which
  writes the *second* byte of each UV pair to the U array and the *first* to V
  (`refocus_config_yuv.s`, loop at `0xf9d0-0xfa94`).
* **Depth**: `NativeData` = raw depth byte buffer with a length; degree and
  dimensions come from `PhotoInfoProvider`. Not an ASVL image — confirmed
  (`initBrainRelightingEffect(..., depthPtr, depthLen, ...)`).
* **Effect image**: second ASVL NV21 buffer of the same size as origin;
  relighting writes into it — confirmed.
* **Blur image**: ASVL NV21 produced by `processMiCameraRefocus` (MiCamera
  profile) or `DualPhotoJni.processRefocus` (portrait profile) — confirmed.
* **Display buffers**: `IDisplay.getYBuffer/getUBuffer/getVBuffer()` are three
  `byte[]` planes; `configYUVData` copies Y (w*h) and de-interleaves VU into
  the separate U/V arrays — confirmed.

### B.3 Preprocessing

1. **Size/orientation**: `DualPhotoNativeContext.generateNativeImage` computes
   `mScale = originBitmapWidth / processBitmapWidth`, `currentFocusPointX/Y =
   focus / mScale`, `blurLevel = round(blurLevel / mScale)`, then allocates
   `mOriginImage` and `mEffectImage` at the process bitmap size — confirmed.
2. **Degrees**: `initBrainRelightingEffect(degree, ...)` maps the photo degree
   to the AILAB orientation enum in the JNI shim: **90°→2, 180°→4, 270°→3,
   anything else→1** (`0xe824-0xe860`) — confirmed.
3. **Face pre-analysis**: `AILAB_PLI_PreProcess(handle, &image, degree,
   int out[3])`; the returned 3 ints are copied to `facePoint[1..3]`, the call
   status to `facePoint[0]`; the log string is
   `[RELIGHT] face center: %d x %d, radius %d` — confirmed.
4. **Light position** (see B.6): the brain shim packs `{w,h,x,y}` on the stack
   in that order and calls `AILAB_PLI_SetLightSource(handle, &struct)` —
   confirmed.

Exact Java → JNI mapping:

| Java method | JNI call | Native behaviour |
|---|---|---|
| `MiBrainRelightingProcessor.initProcessor(provider, ctx)` | `initBrainRelightingEffect(degree, origin.pointer, depth.pointer, depth.length, facePoint)` | creates handle, builds NV21 descriptor, `AILAB_PLI_Init` + `PreProcess`, writes `facePoint[0..3]` |
| `MiBrainRelightingProcessor.initBlurImage/getBlurImage` | `MiDualPhotoJni.processMiCameraRefocus(profile, true, w, h, blurPtr, originPtr, 0, 0, focusX, focusY, afCode, blurLevel, iso, tof, depthPtr, depthLen, false)` (or `DualPhotoJni.processRefocus` in the portrait variant) | fills the blur NV21 buffer |
| `MiBrainRelightingProcessor.enableRelightingEffect(effectType, ctx)` | `setBrainRelightingLightSource(handle, W/2, H/2, W, H)` then `enableBrainRelightingEffect(handle, effectType, blurPtr, effectPtr, originPtr, depthPtr, depthLen, pointCache)` | `AILAB_PLI_SetLightSource`, `AILAB_PLI_Process`, `AILAB_PLI_GetLightSource` |
| `MiBrainRelightingProcessor.doSave(provider, ctx, effectType)` | `releaseBrainRelightingHandle(old)`, `initBrainRelightingEffect(...)`, `setBrainRelightingLightSource(...)`, `enableBrainRelightingEffect(...)`, `releaseBrainRelightingHandle(new)` | full-resolution re-render into `effectImage` |
| `MiBrainRelightingProcessor.release()` | `releaseBrainRelightingHandle(handle)` | `AILAB_PLI_Uninit` |

### B.4 Model / renderer invocation

| Item | Value | Confidence |
|---|---|---|
| JNI library | `libmibrain_relighting_jni.so` 211,088 B, sha256 `5c914d8e…` (report §4.2), depends on `librelight_only.so` | confirmed |
| Renderer | `librelight_only_extraphoto.so` 91,925,616 B; exports only `AILAB_PLI_Init/Uninit/PreProcess/SetLightSource/GetLightSource/Process`; GLES3/EGL | confirmed |
| `AILAB_PLI_Init` | `Init(&handle, 0, width, height)` — mode 0 | confirmed (`initBrain.s 0xe8fc`) |
| `AILAB_PLI_PreProcess` | `(handle, image*, degree, int out[3])` | confirmed |
| `AILAB_PLI_Process` | `(handle, struct{int w,h; long depthPtr}*, originImg*, int* effectType, effectImg*)`; returns int | confirmed (`enableBrain.s 0xeba0-0xec00`) |
| `AILAB_PLI_GetLightSource` | `(handle, int out[4])`; `out[2],out[3]` are copied into `pointCache[0..1]` | confirmed |
| `AILAB_PLI_Uninit` | release | confirmed |
| DNN weights | none shipped as a file; the renderer is a GLES shader plus an 82.9 MB opaque `.data` payload (patterns/LUTs) | confirmed (shader/strings), unknown (payload) |

Shader (`native/relight_render_shader.glsl`, GLSL ES 3.0 fragment):

```glsl
uniform sampler2D text_y, text_u, text_v, text_a, text_fliter;
uniform sampler2D text_light, noise_light, low_brightness;
uniform sampler3D filter_rgb;
uniform vec2 moveLightPos;
vec2 pos = clamp(uv + moveLightPos, 0.0, 1.0);
vec4 light_color = texture(text_light, pos, 0.0);
float screen(a,b){ return 1.0 - (1.0-a)*(1.0-b); }
photoColor = blendSource(rgbdata, lightColor(varTexcoord));
// optional 5x5 gaussian mix 0.5, noise, colour separation, 3D LUT
mat4 RGB2YUV = ...; outputColor = clamp(rgbdata * RGB2YUV, 0.0, 1.0);
```

Y/U/V are sampled as separate 2D textures; the fragment output is RGB for an
RGB target or YUV for a YUV target (`outputColor` vs `gl_FragColor`).

### B.5 Output postprocessing

* Effect buffer is NV21 at process resolution — confirmed by
  `configBitmapByNativeImage` calling `encodeyuvToRGBA(pixels, image->plane0,
  w, h)` (`refocus_config_bitmap.s`, `0xfb10-0xfb70`).
* Save path (`MiBrainRelightingProcessor.doSave`): release the preview handle,
  re-init with the full-resolution origin, centre the light source, run
  `enableBrainRelightingEffect` with a freshly rendered blur image, release the
  handle and the temporary blur image — confirmed.
* Preview path (`configDisplayData → configYUVData`) writes Y/U/V display
  planes without an RGB round trip — confirmed.
* After `doSave`, Java writes the effect NV21 into the decoded origin Bitmap
  (`DualPhotoJni.configBitmapByNativeImage(bitmapDecodeOriginBitmap,
  effectImage.pointer)`), applies orientation/mirror, fills movie black borders,
  then `DualPhotoFileUtils.writeBitmap` — confirmed.

### B.6 Light-source position and gesture mapping

**Brain path (the one in this build):**

* `RelightingOperatingFragment.setLightSource(f, f2)` multiplies the view
  coordinates by `mScale` and posts `SetLightSourceRunnable(round(x), round(y))`
  to the render handler — confirmed.
* `MiBrainRelightingProcessor.setLightSource(...)` is an **empty method**, and
  `supportAdjustLightSource()` returns **false**; `enableRelightingEffect`
  always calls
  `setBrainRelightingLightSource(handle, origin.width/2, origin.height/2,
  origin.width, origin.height)` — confirmed.
* JNI packs those 4 ints as `{w, h, x, y}` at `sp+8/sp+0xc/sp+0x10/sp+0x14`
  and calls `AILAB_PLI_SetLightSource` (`setBrainLight.s`) — confirmed.
* `AILAB_PLI_GetLightSource` writes 4 ints; JNI copies `[2],[3]` to the Java
  `pointCache[2]`, but `getLightingSourceX/Y()` return hard-coded `0` and the
  cache is never read — confirmed.

Net effect: in this global build the touch gesture is **dead** for the brain
relighting backend; the light is always at the image centre. The shader still
supports `moveLightPos`, so the capability exists but is not driven from Java.

**ArcSoft path (contrast, same UI):** `ArcSoftRelightingProcessor.setLightSource`
calls `DualPhotoJni.setRelightingLightSource(handle, x, y,
originBitmapWidth, originBitmapHeight)`, `supportAdjustLightSource()` = true,
and `doSave` re-applies the last `mLightingSource` — confirmed
(`sources/relight_core/classes/...ArcSoftRelightingProcessor.java`,
`...RelightingProcessorPortrait.java`). This is the only path where gesture
coordinates reach a native `SetLightSource` with non-centre values.

### B.7 Consumer / action

* Preview: `refreshDisplay()` → `DualPhotoNativeContext.configDisplayData(display)`
  → `configYUVData(effectImage, y[], u[], v[])` → GLES YUV renderer.
* Save: `SaveRunnable` on render thread →
  `XStorage.insert(EditorFileUtils.safeGenerateImagePath(...,"jpg"))` →
  full-res re-render → `DualPhotoFileUtils.writeBitmap`:
  **JPEG quality 100**, EXIF copied, MIME 4, thumbnail removed,
  `focusPoint/depthMapVersion/Xmp/XiaomiComment/UserComment` cleared, Xiaomi
  maker note kept, optional watermark painter (resource-based) — confirmed.
* Then `BaseMediaUtils.triggerScanFile`, `MediaStoreUtils.upsert`, and
  `SaveFinishRunnable` to the UI; no share intent.

### B.8 Failure and fallback

| Failure | Behaviour | Confidence |
|---|---|---|
| `initProcessor` native failure | base class returns `true` regardless; handle may be 0; effect simply does not change | confirmed |
| `AILAB_PLI_PreProcess` returns non-zero | status stored in `facePoint[0]`; Java ignores it (`getInitResult()` returns 0) | confirmed |
| Null blur or depth image | native logs `GPURelightFilter::Process inDepthInfo or pBlurImage is NULL`; `enableBrainRelightingEffect` accepts 0 for blur | confirmed (string/JNI) |
| UI init flagged failed | `notifyInitFailedRunnable`; save button disabled | confirmed |
| Effect unavailable on device | `RefocusAccessUtils.isBrianRelight` codename gate; effect 0x3 hidden for soft images, 0x18 outside India | confirmed |
| Renderer missing | `AlgoManager$MiBrainRelightingAlgo.load()` failure → `MiBrain` backend not selected | confirmed |

### B.9 Reimplementation mapping

| Stage | JAX module | Contract | Status |
|---|---|---|---|
| Init/face pre-analysis | not implemented | opaque `AILAB_PLI_PreProcess` | unknown |
| Renderer | `reimpl/relighting.py` | origin/blur `(256,320,3)` + depth `(256,320)` → `(256,320,3)`; screen blend with procedural light map, 5×5 gaussian, depth-selected base | ARCHITECTURE_ONLY (real light textures/LUT unavailable) |
| Output conversion | implicit | RGB/YUV matrix from shader | ARCHITECTURE_ONLY |

---

## C. Dual-camera bokeh (Megvii `wa::Refocus`)

### C.1 Data sources / triggers

| Trigger | Exact entry | Confidence |
|---|---|---|
| Blur-level slider moved | `AdjustOperatingFragment` seekbar listener → `processRefocus()` → `mDisplay.getRenderHandler().postAtTime(new RefocusRunnable(...), "refocus")` | confirmed |
| Save | `AdjustOperatingFragment.onSave()` → `SaveRunnable` via `runOnRenderThread` | confirmed |
| Init data | `AdjustProcessor.extractInitData(RefocusData)` → `MegviiAdjustProcessor.onExtractInitData` | confirmed |
| Backend load | `AlgoManager$MegviiBokehAlgo`: `wa_refocus_extraphoto` + `megvii_bokeh_jni` | confirmed |

### C.2 Transport and format

`MegviiAdjustProcessor.processRefocus(NativeImage effect, NativeImage origin,
RefocusData)` calls

```java
MegviiDualPhotoJni.processRefocusWidthHandle(handle, effect.pointer,
        origin.pointer, depthData.pointer, depthData.length,
        focusX, focusY, algFValue, depthWidth, depthHeight, portraitType);
```

JNI (`0x8a8c`, `native/megvii_process.s`):

```
wa::Image(origin.w, origin.h, origin.data, ImageType=6, DataType=0, Rotation=0)  @sp+0x28
wa::Image(effect.w, effect.h, effect.data, ImageType=6, DataType=0, Rotation=0)  @sp+0x18
wa::Image(depthW>0?depthW:origin.w, depthH>0?depthH:origin.h, depthData,
          ImageType=0, DataType=(portraitType!=1 ? 1 : 0), Rotation=0)           @sp+8
wa::Refocus::setImages(handle, &originImage, &depthImage)
wa::Refocus::run(handle, focusX, focusY, fNumber, &effectImage)
```

* Buffers are the same ASVL NV21 images as the relighting path
  (`librefocus.so` conversion symbols) — **confirmed format**, `ImageType=6`
  label **inferred** (NV21).
* Depth: one-plane raw buffer; width/height fall back to the origin size when
  the Java-passed `depthW/depthH` are ≤ 0 — **confirmed**.
* `portraitType`: `1` for `megvii_portrait_google`, `0` otherwise
  (`MegviiAdjustProcessor.getPortraitType`), selecting depth `DataType` 0/1 —
  **confirmed constant**, semantic (8-bit vs 16-bit depth) **inferred**.
* Output image is the *effect* `wa::Image`, i.e. the NV21 buffer passed by
  Java; `wa::Refocus::run` writes into it — **confirmed**.

Threading: preview refocus and save both run on the display render handler
thread; the JNI handle is created lazily (`initRefocus`) and reused for preview,
released/recreated around save (`processRefocusOnSave` → `processRefocus`
creates a fresh handle) — confirmed.

### C.3 Preprocessing

1. **Focus coordinate space**: `MegviiAdjustProcessor.onExtractInitData`:
   `ratio = originBitmapWidth / processWidth`;
   `refocusData.focusX = (int)(provider.getFocusX() / ratio)` and likewise Y.
   So preview focus is in **process-bitmap pixel coordinates**, origin top-left,
   x right, y down — confirmed.
2. **Save**: `prepareForExport` multiplies focusX/Y back by `ratio`, and
   `processRefocusOnSave` runs on the full-resolution origin — confirmed.
3. **Depth dimensions** are passed explicitly so the depth image may have a
   different resolution than the RGB image — confirmed.
4. **No colour conversion or resize** is done in Java; native consumes NV21
   directly — confirmed.

Exact Java → JNI mapping:

| Java method | JNI call | Notes |
|---|---|---|
| `MegviiAdjustProcessor.onExtractInitData(RefocusData)` | none | focus/blur rescale, `findFValueIndex`; fills `count`, `currentIndex` |
| `MegviiAdjustProcessor.processRefocus(effect, origin, data)` | `initRefocus()` if `mHandler == 0`, then `processRefocusWidthHandle(mHandler, effect.pointer, origin.pointer, depth.pointer, depth.length, focusX, focusY, algFValue, depthW, depthH, portraitType)` | preview; handle reused |
| `MegviiAdjustProcessor.processRefocusOnSave(effect, origin, data)` | `releaseRefocus(mHandler)`, then `processRefocus(...)` = `initRefocus` + `processRefocusWidthHandle` + `releaseRefocus` | full-res one-shot |
| `MegviiAdjustProcessor.release()` | `releaseRefocus(mHandler)` | `mHandler = 0` |
| `AdjustProcessor.extractInitData` / `prepareForExport` | none | coordinate-space conversions only |

### C.4 Model invocation

| Item | Value | Confidence |
|---|---|---|
| Runtime | `wa::Refocus` classical CV, **no DNN**; library contains `[RF] not support setModel anymore.` | confirmed |
| Entry | `wa::Refocus::setImages(const Image&, const Image&)` at `vaddr 0x1f07c0`; `wa::Refocus::run(int,int,float,Image&)` at `vaddr 0x1f082c` | confirmed (exports + `wa_refocus_ghidra.c`) |
| Return | `run` returns `bool` (true on success, 0 on failure); JNI ignores the result except for the log line | confirmed |
| Algorithm modules | `ComputeCocDepth`/`ComputeCocDepthAndDisp`, SGBM, WLS filter, `AlphaRefineKepler`, `ApertureCpu::RenderBokehColor`/`RenderYBokeh`, `BlurWorker`, `ImgMergerKepler`, shared matting | confirmed (symbols/strings) |
| f-number | `run(..., float fNumber, ...)` receives `ALG_F_VALUE[index]` | confirmed |
| Batching | single image pair per call | confirmed |

### C.5 Output postprocessing

* Native result stays in the effect NV21 buffer; Java immediately converts it to
  the destination Bitmap with `DualPhotoJni.configBitmapByNativeImage` →
  `encodeyuvToRGBA` (NV21→RGBA8888) — confirmed.
* Save applies orientation/mirror, optional Meitu post-effect, movie black
  borders, then `DualPhotoFileUtils.writeBitmap` (**JPEG q100**, EXIF copied,
  `focusPoint/depthMapVersion/Xmp` cleared) — confirmed.

### C.6 f-number mapping

```
F_VALUE  (25 entries, display) = 1.0,1.1,1.2,1.4,1.6,1.8,2.0,2.2,2.5,2.8,3.2,
                                 3.5,4.0,4.5,5.0,5.6,6.3,7.1,8.0,9.0,10,11,13,14,16
ALG_F_VALUE (25 entries)      = 1.0,1.0,1.4,1.4,1.4,2.0,2.0,2.0,2.8,2.8,2.8,
                                 4.0,4.0,4.0,5.6,5.6,5.6,8.0,8.0,8.0,11,11,11,16,16
index = findFValueIndex(blurLevel)   // compares F_VALUE[i] == blurLevel/10.0f exactly
native fNumber = ALG_F_VALUE[index]
```

`findFValueIndex` returns `-1` when there is no exact match; `onExtractInitData`
clamps it to 0 only *after* using it in a log statement, so a non-multiple-of-10
blurLevel would throw — **confirmed code**, exercised only if the provider
supplies such a value.

### C.7 Consumer / action

Preview and save share the `AdjustOperatingFragment` UI. Save path:
`XStorage.insert` → full-res re-decode (`decodeOriginBitmap`) →
`generateNativeImage` → `processRefocusOnSave` → `configBitmapByNativeImage`
→ rotate/mirror → `DualPhotoFileUtils.writeBitmap` → scan/`MediaStoreUtils.upsert`
→ `SaveFinishRunnable(..., "image", f-number string)`.

### C.8 Failure and fallback

| Failure | Behaviour | Confidence |
|---|---|---|
| Missing effect/origin image or depth data | `MegviiAdjustProcessor.processRefocus` returns immediately (preview keeps last frame) | confirmed |
| Native `wa::Refocus::run` failure | return value ignored; effect buffer remains previous content | confirmed |
| Init failure | `initRefocus` returns 0; `run` with null handle returns false; no crash path observed | confirmed (code) |
| Device not Megvii | `RefocusAccessUtils.isMegvii` codename gate; backend falls back to MiCamera/MiAlgo/ArcSoft processor | confirmed |
| Release | `releaseRefocus(handle)` in `release()` and destroy runnable | confirmed |

### C.9 Reimplementation mapping

| Stage | JAX/NumPy module | Contract | Status |
|---|---|---|---|
| CoC + aperture + blur | `reimpl/bokeh.py` | image `(256,320,3)`, depth `(256,320)`, focus point → `(256,320,3)`; thin-lens CoC, spot shapes, radius-bin blur | ARCHITECTURE_ONLY |
| SGBM/WLS/Kepler matting | not implemented | vendor calibration/`StructD/StructO` constants unavailable | unknown |
| f-number mapping | documented | 25→9 ladder above | confirmed (Java) |

---

## D. Cross-pipeline consumer summary (preview vs save, encoding, metadata)

| | Document | Relighting | Bokeh |
|---|---|---|---|
| Preview surface | Bitmap (`mCurrentPreview`) | GLES YUV textures from `configYUVData` | GLES YUV textures from `configYUVData` |
| Preview thread | AsyncTask pool | display render handler | display render handler |
| Full-res save | re-decode + re-run native | re-decode + re-run native | re-decode + re-run native |
| Save thread | `ExportTask` AsyncTask | render handler | render handler |
| Encoder | JPEG **97** + EXIF/ICC | JPEG **100** + EXIF + watermark | JPEG **100** + EXIF |
| Metadata | `docPhoto=1`, XMP `cropPoints/enhanceType/rawLength/privacyWatermark`, raw JPEG appended | orientation/mirror applied; `focusPoint/depthMapVersion/Xmp` cleared | same as relighting |
| Delivery | `setResult` URI to caller + MediaStore scan | MediaStore insert/scan + UI | MediaStore insert/scan + UI |
| Failure | toast + exit / onError→exit | init-fail UI, save disabled, silent no-op | silent no-op, previous frame retained |

## E. Global open questions

1. Does the shipping MACE `ScanModel` include the `/255` op, or is the JNI
   tensor scaled elsewhere? (would need the embedded pb decoded).
2. What exactly is `param_5 == 2` in `ScanDocument` (the "Doc2.0" path) and how
   does it scale points (`DAT_005367d0`)?
3. `wa::Image::ImageType` numeric enum names (6 for the RGB/NV21 image, 0 for
   depth) — values are confirmed constants, labels inferred.
4. `AILAB_PLI_PreProcess/Process` internals and the 82.9 MB `.data` payload of
   `librelight_only_extraphoto.so`.
5. Whether `document_model.dlc` is consumed by any loader in this build.
