# miui_paymentservice — MiPay digit OCR (classical HOG + SVM)

## 1. Identity

| Field | Value | Source |
|---|---|---|
| APK | `product/app/PaymentService_Global/PaymentService_Global.apk` (36,181,276 B) | `scope`/`inventory/inventory.md` |
| Package | `com.xiaomi.payment` | `inventory/inventory.md` |
| Version | 1.12.13-global (code 1048043) | idem |
| Label | Mi Coin | idem |
| APK SHA-256 | `1b8e2bb07232021a635e031371b3370607c6dfdc6f11e4f01df5959f9c7927fd` | idem |
| OCR assets | `assets/Digital_OCR_svm_2.model` (258,588 B), `assets/Digital_OCR_svm_9.model` (568,611 B) | `inventory/inventory.md`, `models/` |
| Native | `lib/{arm64-v8a,armeabi-v7a,x86_64}/libdigit_ocr.so` (767,880 / 501,112 / 784,600 B) | idem |
| Plugin bundle | `assets/manifest/ocr/MANIFEST.MF` → `Bundle-SymbolicName: com.mibi.ocr`; `plugin.xml` → provider `mibi.ocr` = `com.mibi.ocr.entry.DigitOcrEntry` | unzip -p |

No TFLite/ONNX/NNAPI runtime is present. This subsystem is a **classical computer-vision
pipeline** (adaptive binarisation → connected components → geometric segmentation →
HOG → LIBSVM RBF SVM), used to read digit strings off a camera image (bank-card PAN,
meter/serial numbers) while the user frames them in a mask.

## 2. Feature: camera digit OCR ("mibi.ocr" plugin)

### 2.1 Java side (confirmed)

Call chain (all classes decompiled in `sources/`):

```
com.mibi.ocr.entry.DigitOcrEntry            (plugin entry, OCR bundle)
  └─ com.mibi.ocr.ui.CaptureActivity / CaptureFragment   camera UI + mask
       └─ a.b.a.c  (DigitOCRManager)        camera lifecycle, mask→preview rect mapping,
            │                               rotation→cameraMode, per-frame dispatch
            ├─ a.b.a.b (Camera1 preview)     SurfaceView + NV21 frames (a.b.a.b.h.a(byte[]))
            └─ a.b.a.f (RecognizeController) HandlerThread "decode", one frame at a time
                 └─ a.b.a.d (DigitOCRRecognizer)
                      ├─ NV21 Y plane → ARGB int[]:  (Y & 0xff) * 0x10101 | 0xFF000000
                      │                             (= gray replicated to R,G,B)
                      ├─ Bitmap ARGB_8888 → rotate(cameraMode) → crop(mask rect)
                      ├─ copyPixelsToBuffer → RGBA_8888 direct ByteBuffer
                      └─ com.xiaomi.payment.camera.XiaoMIDigitOCR
                           a(ByteBuffer,w,h,4)  -> nativeSetImage
                           l()                  -> nativeRecogDigit  → getDigitResultString
```

`XiaoMIDigitOCR` (`sources/classes/com.xiaomi.payment.camera.XiaoMIDigitOCR.java`):
`System.loadLibrary("digit_ocr")`, copies both `assets/Digital_OCR_svm_*.model` files into
`filesDir`, then `nativeBeginProcess(j, svm_9_path, svm_2_path)`; results are accepted only
when the mean confidence exceeds a threshold (default 80, `b(int)`) and the digit count is
in the caller-supplied whitelist (`c(int)` / `nativesetDigitRange`).

### 2.2 Native side (confirmed)

`native/README.md` has the symbol/ABI map; `native/ghidra_digitocr.c` the pseudocode.

```
Java_..._nativeSetImage  -> MainOCR::setImage(ptr,w,h,fmt)   [fmt 1..4]
Java_..._nativeRecogDigit-> MainOCR::recogdigitOCR
                              -> MainOCR::process_OneImg(img,w,h,fmt)
                                  1. DigitSegmentManagement::segmentDigit
                                       DigitSegment::SegDigitInGrayImg(w,h,fmt)
                                         rgbToGray(img,gray,fmt,w,h)
                                         adaptiveThreshold(gray,bin,w,h)
                                         SegDigitInBinaryImg(bin)
                                           FindDigitConnectedReigon   (connected components)
                                           FindDigitTargetRect(...,1.0,3.5)  (aspect filter)
                                           RANSAC line -> CorrectBiasAngle if |atan|>eps
                                           SetDigitMatVector  -> 20x32 grayscale patches
                                  2. per patch: CDigitalOCR::Predict(patch,20,32,&OCR_STR vec)
                                       CFEATURE_HOG::ExtractGradientOrientation(patch,20,32,&feat)
                                       svm_predict_probability(model_9, feat) -> argmax
                                       OCR_STR{uint32 digit; uint32 conf=prob*100}
                                       + 2nd/3rd candidate if prob > 0.1
                                  3. sprintf digits, MainOCR::trimBadDigit(...,60,...)
                                     (uses CDigitalOCR::PredictNegPos = model_2 as a
                                      secondary keep/drop signal), saveCurrentFrameResult
                                  4. MainOCR::getRecognitionResultString -> "1234"
```

`MainOCR` ctor calls `setDigitNumberRange(15, 19)` → the default accepted length is a
15–19 digit bank-card PAN.

## 3. Model files — `sm_type` container = LIBSVM text (confirmed, fully parsed)

The 8-byte magic reported by the inventory is simply the ASCII header
`"svm_type"` (`73 76 6d 5f 74 79 70 65`). Both files are **standard LIBSVM
`svm_save_model` text dumps with CRLF line endings** — nothing is encrypted or packed.

```
svm_type c_svc
kernel_type rbf
gamma 2.000000
nr_class 10
total_sv 1076
rho   <45 values>
label 0 1 2 3 4 5 6 7 8 9
probA <45 values>
probB <45 values>
nr_sv 115 89 76 128 87 134 152 47 137 111
SV
<coef_0..coef_8> idx:val idx:val ...
... (1076 lines)
```

| | `Digital_OCR_svm_9.model` | `Digital_OCR_svm_2.model` |
|---|---|---|
| size / sha256 | 568,611 B / `e972116e…d238bb` | 258,588 B / `a88dd751…425a172` |
| svm_type / kernel | `c_svc` / `rbf` | `c_svc` / `rbf` |
| gamma | 2.0 | 0.5 |
| nr_class / labels | 10 / 0…9 | 2 / 0,1 |
| total_sv | 1076 | 545 |
| nSV per class | 115,89,76,128,87,134,152,47,137,111 | 295,250 |
| rho / probA / probB | 45 each | 1 each |
| feature indices used | 1…142 (144-dim vector, bins 143/144 never active) | 1…142 |
| role | digit 0–9 classifier (`CDigitalOCR::Predict`) | binary "neg/pos" gate (`PredictNegPos`, used by `MainOCR::trimBadDigit`) |

Parser: `reimpl/lsvm.py` (`SvmModel`), which reconstructs `sv`, `sv_coef[total_sv][nr_class-1]`,
`rho`, `probA/B`, `label`, `start[]`, `nSV[]` and implements libsvm's one-vs-one decision
values, voting, sigmoid and pairwise-coupling probability.

## 4. Feature contract — HOG (confirmed pixel-exactly)

`CFEATURE_HOG::ExtractGradientOrientation(patch, w=20, h=32, &vector<float>)` @0x14fd8
(rejects anything but 20×32 with return −1):

1. **binarise in place**: `p = 255 if p > 80 else 0` (threshold `0x50`).
2. **gx**: `gx[y][x] = p[y][x] − p[y][x−1]` for `x ≥ 1`; column 0 is left 0.
3. **gy**: `gy[y][x] = p[y][x] − p[y−1][x]` for `y ≥ 1`; row 0 is left 0.
4. **orientation**: `ang = atan2(gy, gx) · 180/π + 180`; if `ang ≥ 360` subtract 360
   (constants at 0x15450: 57.295780490442965, 180.0, 360.0f, 40.0f).
5. **binning**: 4×4 cells of 5×8 px, 9 orientation bins of 40°:
   `bin = (x/5 + (y/8)*4) * 9 + int(ang/40)`, histogram of the 640 pixels.
6. **normalise**: `feat[bin] = hist[bin] / 40.0` (cell area) → **144 float32**,
   sum of all features = 640/40 = 16.

Reference implementations: `reimpl/hog.py` (numpy) and `reimpl/jax_model.py`
(`hog_features`). Both reproduce the shipped function bit-exactly on random and
shaped test images (`evidence/hog/*.native.txt`, max|diff| = 0.0).

## 5. Verification against the shipped binary (confirmed)

`native/harness/` builds an aarch64 harness that `dlopen`s nothing — it links the shipped
`libdigit_ocr.so` and calls its exported C++ symbols (`svm_load_cvt_model`,
`svm_predict_probability`, `svm_predict_values`, `CFEATURE_HOG::ExtractGradientOrientation`,
`CDigitalOCR::Predict/PredictNegPos`, `MainOCR::*`) under qemu-aarch64 with a 20-line
bionic shim. All numbers are captured in `evidence/` and `reimpl/verify.txt`.

| Check | Result |
|---|---|
| HOG vs vendor on 6 images (random, shapes, blank, white) | **max abs diff 0.0** |
| 45 one-vs-one decision values vs `svm_predict_values` | max abs diff **6.7e-08** |
| class probabilities vs `svm_predict_probability` (numpy float64) | max abs diff **4.7e-11** |
| class probabilities, JAX float64 vs vendor | max abs diff **4.7e-11** |
| full native pipeline on a synthetic "1234" card image | `digits=4`, `digit_array: 1 2 3 4` |
| JAX classifier on the vendor's own 4 normalised 20×32 patches | **"1234"** |
| `python reimpl/pipeline.py` | exit 0 (see `reimpl/verify.txt`) |

## 6. JAX reimplementation

`reimpl/jax_model.py` + `reimpl/pipeline.py` (run: `python reimpl/pipeline.py`).

* Real weights, parsed from the shipped `.model` files — nothing is randomised.
* float64 arithmetic like libsvm (feature vector stays float32, as the vendor's
  `std::vector<float>`); the pairwise-coupling iteration is a `lax.while_loop` that
  replicates libsvm's `multiclass_probability` update exactly.
* Exposes `hog_features`, `decision_values`, `predict_proba`, `predict`,
  `classify_digit` (digit + confidence 0–100 + top-3, matching `CDigitalOCR::Predict`),
  `recognize`.
* Also accepts 28×28 / 32×32 inputs by nearest-neighbour resampling to 20×32
  (`resize_to_patch`) for convenience; the native path normalises via segmentation.

Verification output (`reimpl/verify.txt`): shapes `(1076,144)`, `(1076,9)`, `(45)`, HOG
`(1,144)` sum 16.000, decision `(1,45)`, probs `(1,10)` sum 1.000000, six vendor
cross-checks < 1e-10, recognised string `1234`, `EXIT=0`.

## 7. Confidence summary

| Claim | Confidence | Evidence |
|---|---|---|
| Models are LIBSVM text `c_svc`/RBF; parameters as tabulated | confirmed | hexdump, parser, `svm_get_nr_class` under qemu, `evidence/` |
| Feature = binarise(>80) + 20×32 HOG 4×4×9 / 40 | confirmed | `ghidra_digitocr.c:374-714`, exact numeric match |
| 10-class SVM decides the digit; top-3 with prob>0.1 exported as `OCR_STR` | confirmed | `CDigitalOCR::Predict` pseudocode + `OCR_STR{uint32,uint32*100}` |
| model_2 is a binary gate evaluated in `MainOCR::trimBadDigit` | confirmed (computation) / inferred (semantics of label 0 vs 1) | `PredictNegPos` callers at 0x2939c, 0x29444 |
| Segmentation (adaptiveThreshold, connected components, 1:3.5 aspect, angle correction, 20×32 resize) | confirmed (structure) / unknown (exact threshold kernel & constants) | `DigitSegment::*` pseudocode |
| Java frame path NV21→ARGB→rotate→crop→RGBA | confirmed | `a.b.a.d.java`, `XiaoMIDigitOCR.java` |

## 8. Unknowns / limits

1. `adaptiveThreshold` (local function, not exported) — window size, offset/`C`, and
   whether it is mean- or Gaussian-weighted are not recovered; only its call signature
   `(gray, &bin, w, h)` is known.
2. `rgbToGray` exact coefficients per `format` (1..4) are not recovered (it is called
   with 4 for RGBA_8888 from the Java path).
3. `FindDigitTargetRect`'s internal constants beyond the `(1.0, 3.5)` min/max aspect
   arguments, and the projection-histogram/DP split logic in
   `FindSegPointFromHistogram`/`FindSegPointDP`, are only structurally documented.
4. `svm_load_cvt_model`'s internal "cvt" pre-conversion is not decoded, but it is
   numerically identical to `svm_load_model` + standard prediction (verified).
5. The polarity of the binary model (`PredictNegPos` → `prob[0] > 0.5`) is known, but
   whether label 0 means "digit" or "non-digit" cannot be proven from the binary alone;
   `trimBadDigit` uses it as a tie-breaker against a per-digit confidence threshold.
6. The segmentation stage is **not** reimplemented in JAX — only the classifier stage
   (which is where all AI parameters live). Its output contract (20×32 patch) is
   enforced and verified.

## 9. UI → frame → JNI → result contract (patch 2026-10-24)

Full per-stage contract, coordinate maths and buffer ownership: **`PIPELINE.md`**
(this section summarises it; the machine-readable copy is `findings.json` →
`data_pipelines[0]`).

### 9.1 Caller side (dex `classes2.dex`, `sources/consumer/`)

```
VoucherFragment.j0()                        voucher ("Mi Coin card password") screen
  prompt  = R.string.mibi_scan_prompt_voucher   ("Scan Mi Coin password" / "请扫描米币卡密码")
  lengths = {o.l.m .. o.l.n}                 server "VoucherPayRechargeMethod" (payment_recharge_method)
  bundle["arguments"] = [ CameraIntentData(R.id.voucher_edit=2131362538, prompt, lengths) ]
  EntryManager.a().a(f.Y1="mibi.ocr", this, bundle, 31)     requestCode m0 = 31
    -> com.xiaomi.payment.entry.a (provider lookup) -> DigitOcrEntry.a(IEntry.a,bundle,31)
       -> Intent(CaptureActivity) + extra f.o0 = CaptureFragment + extra payment_fragment_arguments
       -> fragment.startActivityForResult(intent, 31)
```

`f.Y1 = "mibi.ocr"` (`sources/consumer/com.xiaomi.payment.l.f.java:115`) is the provider id
declared in `assets/manifest/ocr/plugin.xml`. Result intake:
`VoucherFragment.a(31, -1, intent)` reads `extras.getString("2131362538")` →
`FormattableEditText.setText` (`setMaxLength(n)`); submit validates `m ≤ len ≤ n`
else `Toast(mibi_voucher_error_password)`.

### 9.2 Camera path (dex `classes.dex`, `sources/classes*`)

| Step | Code | Detail |
|---|---|---|
| Mask | `PreviewMaskView.a(c.C0010c)` | centred `350dp × 65dp` window (318/59dp @xxxhdpi; 95 % of width if narrower) |
| Mapping | `DigitOCRManager.a(preview, screen, rect)` | portrait: `x*=previewH/screenW`, `y*=previewW/screenH`; landscape: plain scale. No clamping. |
| Capture | `a.b.a.b` | Camera1, `setOneShotPreviewCallback`, NV21, size chosen as the supported size closest to the screen aspect, `setDisplayOrientation(cameraMode)` |
| cameraMode | `a.b.a.c.b(rotation)` | `(450 − rotationDeg) % 360` → portrait: 90, ROTATION_90: 0, 180: 270, 270: 180 |
| Cadence | `a.b.a.b$c` | one frame per request; 300 ms re-arm; 300 ms auto-focus retry; one-shot callback |
| Worker | `RecognizeController` | `HandlerThread("decode")`, single frame in flight, drop-and-request-next when busy |
| Preprocess | `a.b.a.d.a(byte[],w,h,Point,rw,rh,rot)` | `Y → (Y*0x10101)|0xFF000000` ARGB; rotate by cameraMode; crop at mapped point; `copyPixelsToBuffer` → direct RGBA `ByteBuffer` |
| JNI | `XiaoMIDigitOCR` | `nativeSetImage(j,bb,w,h,4)` → `nativeRecogDigit(j,digits)` → `nativegetDigitResultString(j)` |

### 9.3 JNI contract (from `evidence/ui_jni/jni_disasm.txt`)

| Native | Address | Returns | Notes |
|---|---|---|---|
| `nativeCreateObject` | 0x14b98 | `long` MainOCR* | `operator_new(0xe48)`; ctor default range 15..19 |
| `nativeBeginProcess(long,String,String)` | 0x14840 | `true` always | `GetStringUTFChars` → `beginProcess` → `ReleaseStringUTFChars`; `mov w0,#1` |
| `nativeSetImage(long,ByteBuffer,w,h,fmt)` | 0x148f0 | bool | `GetDirectBufferAddress` → `setImage` stores **borrowed pointer** (no copy/global ref); false if `fmt∉1..4` or NULL |
| `nativeRecogDigit(long,int[])` | 0x1497c | `jint` count | `recogdigitOCR` → `process_OneImg`; `GetIntArrayElements` copy of `count` digits from `+0x7d8`; `Release…(0)` |
| `nativegetDigitResultString(long)` | 0x14ac4 | `jstring` | `getBestFrameResultStr()` (`+0xe30`) → `NewStringUTF` |
| `nativegetmeanConfidence(long)` | 0x14c08 | `jint` | `ldr w0,[x0,#0xe20]` |
| `nativegetwordConfidences(long)` | 0x14c10 | `int[]` | `NewIntArray(count)` only — **zeros, never filled** |
| `nativegetDigitBoundingBoxes(long)` | 0x14c44 | `Rect[]` | std::list `+0x768` → `Rect(IIII)` |
| `nativesetDigitRange(long,int,int)` | 0x14f80 | void | `+0xe38` min, `+0xe3c` max |
| `nativeSetCameraMode(long,int)` | 0x14f6c | void | `+0xe40`; unused by this UI (no bytecode xref), but `process_OneImg` reads it at 0x2c7a4 and calls `estimateBestResult()` when non-zero |
| `nativeclearResultQueue(long)` | 0x14f78 | void | |
| `nativeEndProcess(long)` | — | — | **not exported** for this class (only `Java_com_xiaomicode_digitocr_DigitOCR_nativeEndProcess` @0x14948); `XiaoMIDigitOCR.b()` is dead code → native object leaks |

### 9.4 Acceptance gate and output

`XiaoMIDigitOCR.c()` accepts a frame iff `nativegetmeanConfidence() > 80` **and** the
digit count is in the caller-supplied whitelist (`nativesetDigitRange` range +
Java-side `int[]`); `d.a(Bitmap)` then returns `nativegetDigitResultString()`.
Non-empty → callback → UI `Handler` → `CaptureFragment.h(str)` shows the digits in
`R.id.result`, reveals the buttons and pauses the preview; empty → next frame is
requested. Confirm puts the text into the fragment result bundle under the decimal
string of `R.id.voucher_edit`; `VoucherFragment` re-validates the length client-side.

Privacy: no frame or digit persistence in this path — the only file writes are the two
LIBSVM models copied to app-private `filesDir`, and the vendor lib's leftover debug dump
`D:\123.txt` (model text, impossible path on Android). Native logging is limited to
`"ocrdebug len=%d"`; no digits are logged. — confirmed (source + `strings` of the `.so`)

## 10. Deliverables map

| Path | Contents |
|---|---|
| `report.md` | this document |
| `PIPELINE.md` | per-stage data-pipeline contract (trigger → transport → preprocessing → JNI → postprocess → consumer → failure → reimplementation map) |
| `findings.json` | machine-readable summary (validated against `SCHEMA.json` by `evidence/validate_findings.py`); `data_pipelines[]` mirrors `PIPELINE.md` |
| `sources/` + `sources/index.md` | decompiled Java (`classes/` = `com.mibi.ocr` + JNI wrapper, `classes_abac/` = `a.b.a.*` camera pipeline, `consumer/` = voucher-screen caller + entry dispatcher + models) |
| `native/` | `elf_info_arm64.json`, Ghidra pseudocode (64 funcs), HOG/Predict disassembly, `README.md`, `harness/` (qemu verification harness + bionic shim + `build.sh`) |
| `models/` | the two shipped `.model` files, byte-identical to the APK entries |
| `ir/` | parsed model IR JSON (`svm_9_ir.json`, `svm_2_ir.json`) + the implied op sequence |
| `reimpl/` | `lsvm.py`, `hog.py`, `jax_model.py`, `pipeline.py`, `README.md`, `verify.txt` (exit 0) |
| `evidence/` | `cmds.txt`, `hashes.txt`, `hog/*.bin|*.native.txt|*.cls.txt|*.dec.txt`, `patches/digits1234.patches.bin` (vendor-normalised 20×32 glyphs), `validate_findings.py`, `ui_jni/` (JNI disassembly, resource/layout dump, bytecode xref scripts + output) |
| `inventory/` | `tools/apk_inventory.py` output (manifest facts + extracted libs/models) |
