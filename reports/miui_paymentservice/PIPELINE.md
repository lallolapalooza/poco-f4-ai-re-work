# Data-pipeline integration contract — `miui_paymentservice` (Mi Coin, `com.xiaomi.payment` 1.12.13-global)

Scope: the camera digit-OCR feature (`mibi.ocr` bundle → `libdigit_ocr.so` → LIBSVM).
Companion to `report.md` (models/classifier) and `reimpl/` (JAX classifier stage).

Evidence roots used below:

| Tag | File |
|---|---|
| `SRC/` | `sources/classes/`, `sources/classes_abac/` (OCR bundle, dex `classes.dex`) |
| `SRC2/` | `sources/consumer/` (host app, dex `classes2.dex`) |
| `NAT/` | `native/ghidra_digitocr.c` (ghidra addresses contain imagebase 0x100000), `native/elf_info_arm64.json` (ELF vaddrs) |
| `EV/` | `evidence/ui_jni/{jni_disasm.txt, result_field_writers.txt, exported_jni_symbols.txt, xref.txt, resources.txt, cmds.txt}` |
| `MOD/` | `report.md` §3–§5, `ir/`, `reimpl/` |

All addresses are ELF virtual addresses in `lib/arm64-v8a/libdigit_ocr.so`
(sha256 `f374c84e71a692822fc344caa13734d9aa26ad9d8729ff478aabe97661507f2a`);
`native/ghidra_digitocr.c` prints them +0x100000.

---

## 1. Data sources / triggers

| Stage | Class / method | Detail | Conf |
|---|---|---|---|
| User-visible trigger | `SRC2/com.xiaomi.payment.channel.VoucherFragment` — camera button (`R.id.camera_btn` 2131361940) → `p0()` → `a(String[])` permission check → `j0()` | Voucher (“Mi Coin card password”) redemption screen. Button is only shown if `EntryManager.a().a(activity, f.Y1)` is true, i.e. the OCR entry exists and `DigitOcrEntry.a(Context)` accepts (camera feature present, `!v0.f(context)`) | confirmed |
| Request construction | `VoucherFragment.j0()` | `prompt = R.string.mibi_scan_prompt_voucher` (`"Scan Mi Coin password"`, zh `"请扫描米币卡密码"`); `allowedLengths = {m .. n}` from the server-provided recharge method; `new CameraIntentData(R.id.voucher_edit /*2131362538*/, prompt, allowedLengths)`; `bundle.putParcelableArrayList(IEntry.f3559b /*"arguments"*/, …)`; `EntryManager.a().a(f.Y1, this, bundle, 31)` (`m0 = 31`) | confirmed |
| Length source | `SRC2/com.xiaomi.payment.o.l` (extends `o.h`, `VoucherPayRechargeMethod`), fields `long m, n` | `VoucherFragment.l(Bundle)` reads `f.Z1` (`"payment_recharge_method"`), `c0.h` = prompt, `d0 = l.m`, `e0 = l.n`, builds `ArrayList<Integer>` of every integer in `[m..n]` | confirmed |
| Entry id | `SRC2/com.xiaomi.payment.l.f.Y1 = "mibi.ocr"` | matches `assets/manifest/ocr/plugin.xml` `<provider id="mibi.ocr" class="com.mibi.ocr.entry.DigitOcrEntry"/>` | confirmed |
| Dispatch | `SRC2/com.xiaomi.payment.entry.a.a(String, Fragment, Bundle, int)` → `private a(String, IEntry.a, Bundle, int)` | `this.f3566a.get(id).f3570a.a(aVar, bundle, requestCode)`; the `Fragment` adapter (`EntryManager$e`) calls `fragment.startActivityForResult(intent, 31)` | confirmed |
| Entry point | `SRC/com.mibi.ocr.entry.DigitOcrEntry.a(IEntry.a, Bundle, int)` | `Intent(CaptureActivity)`, extra `com.mipay.common.data.f.o0` = `CaptureFragment` class name, extra `"payment_fragment_arguments"` = the caller bundle | confirmed |
| Activity | `SRC/com.mibi.ocr.ui.CaptureActivity` (`BaseActivity.k`) | hosts the fragment; `d.b(this)` applies the window flags | confirmed |
| Lifecycle | `CaptureFragment` `j()`→`i(Bundle)`→`E()`→`D()`/`A()`; `a.b.a.c` (`DigitOCRManager`) state machine `NULL/INIT/PREVIEWING/PAUSED` | `j()` creates `a.b.a.c`; `i(Bundle)` wires the mask rect and surface callback; `E()` (resume) → `c0.h()` open camera/start; `D()` (pause) → `c0.i()` stop+release | confirmed |

Native object lifecycle: `a.b.a.d.a(Context)` news `XiaoMIDigitOCR` and calls
`XiaoMIDigitOCR.a(Context)` → `nativeCreateObject()` (one 0xe48-byte `MainOCR` per
recognizer) + `nativeBeginProcess(...)`. `XiaoMIDigitOCR.b()` (the only caller of
`nativeEndProcess`) is **never called**, so the native object is never destroyed and
`nativeEndProcess` is not even exported for this class (see §4). — confirmed
(`SRC/com.xiaomi.payment.camera.XiaoMIDigitOCR.java:162`, `grep f411a\\.` over
`SRC/classes_abac/` shows only `a`, `l`, `h`, `g` are used).

## 2. Transport and format

### 2.1 Camera → frame bytes (confirmed)

| Property | Value | Ref |
|---|---|---|
| Camera API | `android.hardware.Camera` (Camera1), `SRC/classes_abac/a.b.a.b.java` | confirmed |
| Opened on | UI thread (`Camera.open()` in `a.b.a.b.e()`, no looper switch) → `onPreviewFrame` runs on the main thread | confirmed |
| Preview surface | `SRC/com.mibi.ocr.ui.CameraPreview` (`SurfaceView`), full-screen (`match_parent` in `res/layout/mibi_capture.xml`) | confirmed (`EV/resources.txt`) |
| Callback type | `setOneShotPreviewCallback` only (one frame per request) | confirmed |
| Preview format | never set → Camera1 default **NV21** (`Y` plane `w*h` bytes, then interleaved `VU`) | confirmed (no `setPreviewFormat` call anywhere in the APK) |
| Bytes consumed | only the first `w*h` bytes (`bArr[y*w+x]`, `SRC/classes_abac/a.b.a.d.java:96-105`) — i.e. the luminance plane; chroma ignored | confirmed |
| Frame size | the camera preview size selected by `a.b.a.b.a(c.C0010c screen)` | confirmed |
| Size selection | first try: exact match of (long,short) of a `getSupportedPreviewSizes()` entry vs (screen long, screen short); otherwise the size minimising `\| (long/short integer division) − (screenLong/screenShort float) \|` (sizes sorted by area, descending; first best wins, cached in `f390c`). Note the metric truncates the candidate aspect ratio to an integer, so the first entry often wins — a vendor quirk | confirmed |
| Display orientation | `Camera.setDisplayOrientation(cameraMode)` (`a.b.a.b.a(C0010c,int)`) | confirmed |
| Focus | `continuous-picture` if supported, else `auto` + explicit `autoFocus()` cycles | confirmed |
| Frame `byte[]` ownership | allocated by the framework for the one-shot callback, passed by reference into `Message.obj` (no copy), only read; valid only for that callback | confirmed |

`cameraMode` (`a.b.a.c.b(int displayRotation)`, `SRC/classes_abac/a.b.a.c.java:160-176`):

```
rotation 0 (ROTATION_0)        -> (360-0  +90)%360 = 90
rotation 1 (ROTATION_90)       -> (360-90 +90)%360 = 0
rotation 2 (ROTATION_180)      -> (360-180+90)%360 = 270
rotation 3 (ROTATION_270)      -> (360-270+90)%360 = 180
```

i.e. `cameraMode = (450 − displayRotationDegrees) mod 360`. On a tablet
(`com.mipay.common.data.v0.d()` → `Build.IS_TABLET`) an `OrientationEventListener`
(`CaptureFragment$f`, only accepts 0/90/180/270) later overwrites it with
`(physicalOrientation + 90) mod 360` via `c0.a(int)`; that value is only consumed by the
next `h()` (camera open) — it does **not** re-map the mask or re-configure the running
camera (confirmed code path; limitation).

### 2.2 Mask rectangle and coordinate transform (confirmed)

* The mask is a pure UI overlay: `PreviewMaskView` (full-screen) draws a dimmed scrim
  with a `DST_OUT` rounded rect and an animated scan line
  (`onDraw`, 14-step alpha ramp `{0,32,…,224,192,…}`, 100 ms `postInvalidateDelayed`).
* `PreviewMaskView.a(c.C0010c size)` computes the scan window in **screen** pixels:

```
w = getDimensionPixelSize(mibi_camera_frame_rect_width)   // 350dp, 318dp @xxxhdpi
h = getDimensionPixelSize(mibi_camera_frame_rect_height)  //  65dp,  59dp @xxxhdpi
if (size.width < w) w = (int)(size.width * 0.95)
rect = Rect((W-w)/2, (H-h)/2, (W+w)/2, (H+h)/2)           // with W,H = screen size
```

  (`EV/resources.txt`; `SRC/com.mibi.ocr.ui.PreviewMaskView.java:99-122`).

* `CaptureFragment.i(Bundle)` calls `c0.a(maskView.a(c0.d()))`; `c0.d()` is the
  **display size** (not the view size); the rect is stored in `DigitOCRManager.f405f`.
* `DigitOCRManager.a(C0010c previewSize)` maps it into preview coordinates
  (`SRC/classes_abac/a.b.a.c.java:203-232`):

```
portrait  (Configuration.orientation == ORIENTATION_PORTRAIT):
    left'   = left   * previewH / screenW        // = left when previewH == screenW
    right'  = right  * previewH / screenW
    top'    = top    * previewW / screenH        // = top * (1920/2400) on a 1080x2400 phone
    bottom' = bottom * previewW / screenH

landscape:
    left'   = left   * previewW / screenW
    right'  = right  * previewW / screenW
    top'    = top    * previewH / screenH
    bottom' = bottom * previewH / screenH
```

  (integer division, no clamping, no letterbox offset). The transform is applied to a
  copy; then `RecognizeController.a(previewW, previewH, new Point(rect'.left, rect'.top),
  rect'.width(), rect'.height(), cameraMode)`.

* Why this is consistent: the frame bitmap is rotated **by `cameraMode`** in Java
  (`Matrix.setRotate(cameraMode, w/2f, h/2f)`, `a.b.a.d.a(Bitmap,int)`), so the rotated
  frame is `previewH × previewW` in portrait; the mask x maps by `previewH/screenW`
  (identity when the preview is 1080 wide on a 1080-wide screen) and y by
  `previewW/screenH` (undoes the display stretch of the 1920-tall preview into the
  2400-tall screen).
* Failure: no bounds check — `Bitmap.createBitmap(frame, x, y, w, h)` throws if the
  mapped rect leaves the rotated frame (e.g. exotic preview sizes), on the `"decode"`
  `HandlerThread`, so it would kill the process. — inferred

### 2.3 Threads, queues, cadence (confirmed)

| Item | Behaviour | Ref |
|---|---|---|
| Frame producer | main thread; `a.b.a.b$c` (`Handler` on main looper) drives one-shot captures: msg 1 = “give me a frame”; `if (hasMessages(1)) removeMessages(1)`; if focus mode `auto` (`c()` true) → send msg 2 (auto-focus) delayed 300 ms; else if camera null / not previewing → retry msg 1 in 300 ms; else `setOneShotPreviewCallback` | `a.b.a.b.java:63-88` |
| After focus | `AutoFocusCallback`: on success and previewing → `setOneShotPreviewCallback` immediately; else retry msg 2 after 300 ms | `a.b.a.b.java:31-42` |
| Cadence | one frame per request; continuous-picture devices ≈ every 300 ms loop, auto-focus devices = 300 ms + AF latency | confirmed (code) |
| Worker | `RecognizeController.c()` creates `HandlerThread("decode")`; `b extends Handler` runs the whole preprocessing + JNI call there | `SRC/classes_abac/a.b.a.f.java:100-140` |
| Queue depth | 1 (a flag + `hasMessages(1)`): `b.a()` = `!processing && !hasMessages(1)`; a new frame is posted only if idle, otherwise it is dropped and a fresh frame is requested (`f.this.j.a()`) | confirmed |
| Completion latch | `f.l` set on first non-empty result; `dispatchMessage` drops further messages while set; `b()`/`c()` reset it | confirmed |

## 3. Preprocessing

Exact order, all in `SRC/classes_abac/a.b.a.d.java` (`DigitOCRRecognizer`)
unless noted. No scaling of the full frame; the crop is 1:1 preview pixels.

| # | Operation | Formula / parameter | Conf |
|---|---|---|---|
| 1 | Argument validation | `w>0, h>0, point!=null, rectW>0, rectH>0` else return `null` | confirmed |
| 2 | NV21 Y → ARGB8888 `int[]` | `dst[y*w+x] = ((src[y*w+x] & 0xFF) * 65793) \| 0xFF000000`; `65793 = 0x10101` replicates Y into R,G,B | confirmed |
| 3 | `Bitmap` | `Bitmap.createBitmap(w,h,ARGB_8888)` + `setPixels(...)` | confirmed |
| 4 | Rotate | `Matrix.setRotate(cameraMode, w/2f, h/2f)`, `Bitmap.createBitmap(bmp,0,0,w,h,matrix,true)` (bilinear); `cameraMode==0` → no-op; `OutOfMemoryError` → keep unrotated | confirmed |
| 5 | Crop | `Bitmap.createBitmap(rotated, point.x, point.y, rectW, rectH)`; source recycled | confirmed |
| 6 | Pack | `ByteBuffer.allocateDirect(rectW*rectH*4)`, `bitmap.copyPixelsToBuffer`, `flip()` → direct RGBA byte order (`Android ARGB_8888` memory = R,G,B,A) | confirmed |
| 7 | JNI | `XiaoMIDigitOCR.a(bb, rectW, rectH, 4)` → `nativeSetImage` | confirmed |
| 8 | Native gray | `rgbToGray(img, gray, fmt=4, w, h)` — because step 2 replicated Y, any RGB weighting reproduces Y | confirmed (data flow) / inferred (coefficients) |
| 9 | Binarise + segment | `adaptiveThreshold` → `FindDigitConnectedReigon` → `FindDigitTargetRect(1.0,3.5)` → `RansacLine`/`CorrectBiasAngle` → `SetDigitMatVector` (glyphs resampled to 20×32 gray) | confirmed (structure), constants unknown |
| 10 | HOG | `CFEATURE_HOG::ExtractGradientOrientation(patch,20,32)`: binarise `p>80 ? 255:0`; `gx=p[x]-p[x-1]`, `gy=p[y]-p[y-1]`; `atan2(gy,gx)*180/π+180`; 4×4 cells (5×8 px) × 9 bins (40°); `feat/=40` → 144 float32 | confirmed pixel-exact (`MOD/§4`) |
| 11 | SVM | `svm_predict_probability(svm_9, feat)` → 10 probabilities; `OCR_STR{digit=int, conf=int(prob*100)}`, top-3 candidates with `prob>0.1`; `PredictNegPos(svm_2)` gate in `MainOCR::trimBadDigit(…,60,…)` | confirmed (`MOD/§2.2`) |

Note: the Java path already rotates the bitmap. `nativeSetCameraMode` is **not** called by
this UI (grep `f411a.` in `SRC/classes_abac/` and a bytecode xref of `XiaoMIDigitOCR`
show only `a(Context)`, `a(ByteBuffer,w,h,fmt)`, `a(int[])`, `l()`, `h()`, `g()`), so
`MainOCR+0xe40` keeps its ctor value 0. `MainOCR::process_OneImg` reads that field at
0x2c7a4 and, when it is non-zero, calls `MainOCR::estimateBestResult()` (0x2ad38, sole
caller 0x2c8dc) before assembling the result string.

## 4. Model invocation

### 4.1 Java side (`SRC/com.xiaomi.payment.camera.XiaoMIDigitOCR.java`)

| Step | Code | Effect |
|---|---|---|
| Library load | `static { System.loadLibrary("digit_ocr"); }` | `libdigit_ocr.so` |
| Model provision | `a(Context)`: `filesDir/Digital_OCR_svm_9.model`, `..._2.model`; if absent copy from assets (`v0.a(context, assetName, path)`) | both assets are plain LIBSVM text (see `report.md` §3) |
| Create | `a(String,String)`: `j = nativeCreateObject(); b(80); return nativeBeginProcess(j, svm9Path, svm2Path)` | threshold field `f3536c = 80` |
| Per frame | `a(ByteBuffer,w,h,4)` → `nativeSetImage`; `l()` → `nativeRecogDigit(j, digits[100])` | digits int buffer is the `int[100] f3537d` |

### 4.2 JNI contract (exact, confirmed from `EV/jni_disasm.txt` + `NAT`)

JNI vtable indices decoded from the call sites (`0x730` = `GetDirectBufferAddress`,
`0x548/0x550` = `Get/ReleaseStringUTFChars`, `0x5d8/0x618` = `Get/ReleaseIntArrayElements`,
`0x538` = `NewStringUTF`, `0x598` = `NewIntArray`, `0x560/0x570` =
`NewObjectArray/SetObjectArrayElement`).

| Java declaration | Export (ELF) | Addr / size | Behaviour | Conf |
|---|---|---|---|---|
| `private static native long nativeCreateObject()` | `Java_..._nativeCreateObject` | 0x14b98 / 0x5c | `operator_new(0xe48)` + `MainOCR::MainOCR`; ctor sets digit range 15..19 (`0x29a9c` → +0xe38/+0xe3c) | confirmed |
| `private static native boolean nativeBeginProcess(long, String svm9, String svm2)` | `..._nativeBeginProcess` | 0x14840 / 0xb0 | `GetStringUTFChars` ×2 → `MainOCR::beginProcess(paths)` → `ReleaseStringUTFChars`; **returns `w0=1` unconditionally** (`mov w0,#1` @0x148d8), i.e. model-load failure is not reported to Java | confirmed |
| `private static native boolean nativeSetImage(long, ByteBuffer, int w, int h, int fmt)` | `..._nativeSetImage` | 0x148f0 / 0x58 | `GetDirectBufferAddress(buffer)` → tail-call `MainOCR::setImage(ptr,w,h,fmt)`; returns its bool (false if `fmt∉{1..4}` or ptr NULL) | confirmed |
| `private static native int nativeRecogDigit(long, int[] digits)` | `..._nativeRecogDigit` | 0x1497c / 0x148 | `MainOCR::recogdigitOCR()` (0x2ca94: loads +0x7a8 ptr, +0x7b0 w, +0x7b4 h, +0x7b8 fmt → `MainOCR::process_OneImg`), `getDigitResultNum()` (+0xaf8); `GetIntArrayElements` → copy count ints from +0x7d8 → `ReleaseIntArrayElements(…,0)`; returns count | confirmed |
| `private static native String nativegetDigitResultString(long)` | `..._nativegetDigitResultString` | 0x14ac4 / 0x84 | `MainOCR::getBestFrameResultStr()` (std::string at +0xe30) → `NewStringUTF` | confirmed |
| `private static native int nativegetmeanConfidence(long)` | `..._nativegetmeanConfidence` | 0x14c08 / 0x8 | `ldr w0,[x0,#0xe20]` → mean confidence 0..100 of the selected frame | confirmed (field read); avg-vs-min naming inferred |
| `private static native int[] nativegetwordConfidences(long)` | `..._nativegetwordConfidences` | 0x14c10 / 0x34 | `NewIntArray(getDigitResultNum())` only — **never filled**, returns zeros | confirmed |
| `private static native Rect[] nativegetDigitBoundingBoxes(long)` | `..._nativegetDigitBoundingBoxes` | 0x14c44 / 0x2cc | walks the std::list at +0x768 → `Rect` objects (`<init>(IIII)`, `SetIntField`), `NewObjectArray`/`SetObjectArrayElement`; debug log `"ocrdebug len=%d"` | confirmed |
| `private static native void nativeSetCameraMode(long, int)` | `..._nativeSetCameraMode` | 0x14f6c / 0xc | `str w1,[x0,#0xe40]` (unused by this UI) | confirmed |
| `private static native void nativeclearResultQueue(long)` | `..._nativeclearResultQueue` | 0x14f78 / 0x8 | `MainOCR::clearResultQueue()` | confirmed |
| `private static native void nativesetDigitRange(long, int min, int max)` | `..._nativesetDigitRange` | 0x14f80 / 0x10 | `str w1,[+0xe38]; str w2,[+0xe3c]` | confirmed |
| `private static native boolean nativeEndProcess(long)` | **not exported for this class**; the lib only exports `Java_com_xiaomicode_digitocr_DigitOCR_nativeEndProcess` @0x14948 | — | would throw `UnsatisfiedLinkError`; dead code (never called) | confirmed |

**Buffer ownership / lifetime.** `nativeSetImage` stores the raw pointer returned by
`GetDirectBufferAddress` and does **not** copy, pin or create a global reference
(`NAT` `MainOCR::setImage` @0x129134: `*(this+0x7a8)=ptr; +0x7b0=w; +0x7b4=h; +0x7b8=fmt`).
The Java `ByteBuffer` is a per-frame `allocateDirect(rectW*rectH*4)` local; it stays
reachable through the call chain `d.a(Bitmap) → setImage → recogDigit` on the same
thread, which is the entire validity window. `nativeRecogDigit` copies results out and
owns nothing afterwards; the model files are read by `svm_load_model` when
`beginProcess` runs (native copies the text into `svm_model`).

**Threading.** All JNI calls (`nativeSetImage`, `nativeRecogDigit`,
`nativegetDigitResultString`, …) happen on the single `"decode"` `HandlerThread`; the
`MainOCR` instance is therefore confined to one thread and needs no locking.

**Cancellation.** `RecognizeController.d()` → `removeMessages(1)` + drops the callback;
`DigitOCRManager.i()` → `f.d()` + `camera.release()`; `Camera.stopPreview()` in
`a.b.a.b.f()`. There is no native abort: an in-flight `process_OneImg` runs to
completion on the decode thread.

## 5. Output postprocessing

| Stage | Detail | Conf |
|---|---|---|
| Per-frame recognition | `MainOCR::process_OneImg(ptr,w,h,fmt)` → segment, classify each glyph, `trimBadDigit(vec, 60, 1.5f, &i, &j)` (drop digits with confidence < 60 unless the binary `svm_2` gate disagrees), `setBestDigitInRange(&min,&max)` → `saveCurrentFrameResult(5)`. The exposed result lives at `digits[] @+0x7d8`, `count @+0xaf8`, scalars `+0xe1c/+0xe20/+0xe24`, best-frame string `@+0xe30`; these are initialised by `MainOCR::MainOCR` and written by `MainOCR::estimateBestResult` (0x2ad38, sole call site 0x2c8dc inside `process_OneImg`, gated on `+0xe40 != 0`). The shipped harness reproduces `digits=4`, `digit_array: 1 2 3 4` for a synthetic 472×192 card image with `+0xe40` left at 0 | confirmed (structure; `report.md` §2.2, `native/harness`) |
| Digit string | `MainOCR::getRecognitionResultString` (0x29ed8) = `sprintf("%d", digits[i])` for `i<count`, concatenated (`"%d"` @0x8ff40) | confirmed |
| Java re-assembly | `XiaoMIDigitOCR.g()` builds the same string from the `int[100]` filled by `nativeRecogDigit`, then calls `c()` and returns `i()` (`nativegetDigitResultString`) — so the returned string is the native one, not the Java `StringBuilder` | confirmed |
| Acceptance gate | `XiaoMIDigitOCR.c()`: `meanConf = j()` (`+0xe20`), `confArr = k()` (zeros); `count = f3538e`; if `count > 0`: valid ⇔ `meanConf > 80 (f3536c)` **and** `count ∈ whitelist (h[])`; sets `g` flag. `d.a(Bitmap)` returns `h() ? g() : ""` | confirmed |
| Digit-count whitelist | `XiaoMIDigitOCR.a(int[])` (from `CameraIntentData.h`) → `nativesetDigitRange(min,max)` and Java-side exact-count set | confirmed |
| Default range | `MainOCR` ctor `setDigitNumberRange(15,19)`; overridden by the caller's `[m..n]` | confirmed |

## 6. Consumer / action

```
decode thread: a.b.a.d.a(...) -> String ( "" or digits )
  RecognizeController$b.dispatchMessage (msg 1 done)
    -> on non-empty: f.this.h.a(str)        // f.h = c.b callback
        DigitOCRManager.b callback -> CaptureFragment$e.a(String)
           c0.f()                            // camera.stopPreview(), state PAUSED
           UI Handler (CaptureFragment$f0).sendMessage(msg 1, obj=str)
              CaptureFragment.h(String):
                 buttons_contain.setVisibility(0)
                 result(TextView, id 2131362326).setText(str)
                 c0.f()                      // pause preview again
    -> on empty: f.this.j.a()                // request next frame
```

| Consumer step | Code | Detail | Conf |
|---|---|---|---|
| Result display | `CaptureFragment.h` | raw digit string in `R.id.result` `TextView` (not editable) | confirmed |
| Confirm / retry | `CaptureFragment$b` / `$c` | confirm: `S.putString(Integer.toString(CameraIntentData.f3574a) /*"2131362538"*/, resultText)` then `c(-1, S)` (fragment result) → `CaptureActivity.setResult`; retry: `i(false)` → next `CameraIntentData` or rescan | confirmed |
| Multi-step scan | `CaptureFragment.i(boolean)` | a `LinkedList<CameraIntentData>` allows several prompts in one session; button label toggles `mibi_button_next` / `mibi_button_confirm` | confirmed |
| Result intake | `VoucherFragment.a(int requestCode, int resultCode, Intent)` | only `requestCode==31 && resultCode==-1`, reads `extras.getString("2131362538")`; non-empty → `W.setText(string)` into `FormattableEditText` (`TYPE_NORMAL`, `setMaxLength((int) e0)`) | confirmed |
| Client-side length validation | `VoucherFragment$c.onClick` | `s.a(text.trim(), TYPE_NORMAL)`; if empty or `len < d0` or `len > e0` → `Toast(mibi_voucher_error_password /*"Code invalid."*/)`; else `presenter.a(code)` (network submission) | confirmed |
| PAN interpretation | default 15..19 digits in native = ISO/IEC 7812 bank-card PAN lengths — the library was written for PAN capture, but this app feeds it the voucher-code length window `[m..n]` from `VoucherPayRechargeMethod` | confirmed (code) / inferred (PAN intent) |

## 7. Failure and fallback

| Condition | Behaviour | Ref / Conf |
|---|---|---|
| No camera feature or incognito mode | `DigitOcrEntry.a(Context)` false → `VoucherFragment.i()` hides `camera_btn` (visibility 4); manual entry only | confirmed |
| CAMERA permission missing | `BaseFragment` permission flow: `U()` rationale (`mibi_incognito_mode_dialog_summary_camera`), `W()` permanently-denied text → `i0()` → `H()` (close) | confirmed |
| `Camera.open()` throws | Toast `mibi_camera_open_failed` (`"相机打开失败"`), `h()` returns false → `CaptureFragment.E()` calls `H()` | confirmed |
| Model assets missing / copy fails | `a.b.a.d.a(Context)` throws `IllegalStateException("Digital_OCR_svm*.models are not provided rightly")` on the decode thread | confirmed |
| Model load fails natively | `beginProcess` logs `"model load fail"` but the JNI returns 1, so Java believes it succeeded | confirmed |
| No digits / low confidence / wrong count | frame rejected (`""`), UI keeps scanning; user can still type manually or press retry | confirmed |
| Empty result and no callback (`j == null`) | dropped silently in `RecognizeController$1` | confirmed |
| Mapped crop out of frame bounds | `Bitmap.createBitmap` throws on the decode thread → uncaught → process crash (no fallback) | inferred |
| Rotation change while previewing (tablet) | only stored; camera/mask not re-configured until next `h()` | confirmed |
| Native object leak | `nativeEndProcess` never called (and not exported for this class) | confirmed |

## 8. Confidence and open questions

| Claim | Confidence | Evidence | Would settle it |
|---|---|---|---|
| Full trigger chain Voucher → EntryManager → DigitOcrEntry → CaptureActivity/Fragment | confirmed | `SRC2/com.xiaomi.payment.channel.VoucherFragment.j0`, `entry.a`, `l.f.Y1="mibi.ocr"`; `assets/manifest/ocr/plugin.xml`; `EV/xref.txt` | — |
| Mask rect = 350×65 dp centered; mapped by the portrait/landscape formulas above | confirmed | `PreviewMaskView.a`, `DigitOCRManager.a(C0010c,C0010c,Rect)`, `EV/resources.txt` | on-device screenshot |
| Frame = NV21, only Y used, size = preview size from `a.b.a.b.a(C0010c)` | confirmed | `a.b.a.d` indexing, absence of `setPreviewFormat` | — |
| Rotation applied twice independently (display orientation for preview, bitmap rotation for OCR) | confirmed | `a.b.a.b.a(C0010c,int)` + `a.b.a.d.a(Bitmap,int)` | — |
| One frame in flight, 300 ms re-arm, 300 ms AF retry | confirmed | `a.b.a.b$c`, `$a`, `RecognizeController$b` | trace |
| JNI signatures/ownership as tabulated | confirmed | `EV/jni_disasm.txt`, `NAT` | — |
| `nativegetwordConfidences` returns zeros | confirmed | 0x14c10 disasm (`NewIntArray` only) | — |
| `nativeBeginProcess` always returns true | confirmed | 0x148d8 `mov w0,#1` | — |
| `+0xe20` is the mean confidence compared against 80 | confirmed (field/dataflow) / inferred (semantic name) | `getAllWordMinConf` (0x291c4), writers listed in `EV/result_field_writers.txt` (ctor + `estimateBestResult` 0x2ae08 only) | instrumented native run printing `MainOCR+0xe20` |
| Voucher code length window comes from `VoucherPayRechargeMethod.m/n` | confirmed | `SRC2/o/l.java`, `VoucherFragment.l(Bundle)` | server payload |
| No image/result persistence in the OCR path | confirmed | no file writes in `SRC`/`SRC2` path; native strings only `D:\123.txt` (model dump) + `ocrdebug len=%d` | runtime `strace` |
| PAN (15..19) default vs voucher lengths | confirmed (default) / inferred (intent) | `MainOCR::MainOCR` @0x29a9c | vendor docs |

Open: exact `adaptiveThreshold` window/offset, `rgbToGray` coefficients, segmentation
DP constants (unchanged unknowns from `report.md` §8) — none of them affect the
JNI/UI contract above.

## 9. Reimplementation mapping

| Pipeline stage | Repro in `reimpl/` | Contract | Status |
|---|---|---|---|
| NV21 Y → gray/RGB | `pipeline.py` accepts the 20×32 patch; the Java transform is 3 lines `(Y&0xff)`, replicate to RGB — reproducible with numpy in one line | `uint8[w,h]` → `int32 ARGB` | reproduced conceptually (Java side, no learned params) |
| Rotate + crop | `Matrix.setRotate(cameraMode)` + crop at mapped rect; formulas in §2.2 | `crop = rotate(frame, cameraMode)[y:y+rh, x:x+rw]` | documented only (no image IO in repo) |
| Native gray/segment | not reimplemented (constants unknown) | `RGBA crop → list of 20×32 uint8 patches` | ARCHITECTURE_ONLY — segmentation is where the unknowns live |
| HOG | `reimpl/hog.py`, `reimpl/jax_model.py::hog_features` | `uint8[20,32]` → `float32[144]` (sum 16.0) | verified bit-exact vs vendor |
| SVM 10-class | `reimpl/lsvm.py`, `jax_model.py::predict_proba/predict` | `float32[144]` → `float64[10]` probs, digit + conf 0..100 + top-3 | verified (6.7e-8 / 4.7e-11) |
| SVM 2-class gate | `jax_model.py` binary branch (`PredictNegPos`) | `float32[144]` → `prob[0] > 0.5` | verified numerically; label semantics unknown |
| Confidence gate 80 / count whitelist / `trimBadDigit(…,60,…)` | not in JAX; Java implements `>80 && count ∈ {m..n}`; native adds per-digit 60 threshold | `(digits, meanConf, count)` → accept/reject | documented; Java side reproducible |
| String assembly + UI | `sprintf("%d")` chain; `CaptureFragment.h` → confirm → `VoucherFragment` | `int[] → "1234"` | documented |

`python reimpl/pipeline.py` exits 0 and prints shapes (`reimpl/verify.txt`); it covers
the HOG+LIBSVM stage with the real shipped weights. Everything upstream of the 20×32
patch is ARCHITECTURE_ONLY (no extracted parameters exist to reproduce).
