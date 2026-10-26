# Decompiled sources — miui_paymentservice (com.xiaomi.payment 1.12.13-global)

APK: `/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/app/PaymentService_Global/PaymentService_Global.apk`
Tool: `tools/decompile_classes.py` (jadx `--single-class`), one JVM at a time.

## `classes/` — OCR plugin bundle (`com.mibi.ocr`, dex `classes.dex`) and the JNI wrapper (dex `classes2.dex`)

| File | Role |
|---|---|
| `com.mibi.ocr.entry.DigitOcrEntry.java` | OSGi entry point declared in `assets/manifest/ocr/plugin.xml` (`provider id="mibi.ocr"`); opens `CaptureActivity` for the digit scan. |
| `com.mibi.ocr.ui.CaptureActivity.java` | Activity host. |
| `com.mibi.ocr.ui.CaptureFragment.java` | Camera UI; owns `a.b.a.c` (`DigitOCRManager`), feeds recognised text into the card-number field, permission handling, orientation listener. |
| `com.mibi.ocr.ui.CameraPreview.java`, `PreviewMaskView.java` | `SurfaceView` preview + the scan-window mask (the crop rectangle shown to the user). |
| `com.xiaomi.payment.camera.XiaoMIDigitOCR.java` | **JNI wrapper**: `System.loadLibrary("digit_ocr")`, copies `assets/Digital_OCR_svm_9.model` and `_2.model` into `filesDir`, `nativeBeginProcess`, `nativeSetImage(ByteBuffer,w,h,format)`, `nativeRecogDigit`, `nativegetDigitResultString`, `nativegetDigitBoundingBoxes`, `nativesetDigitRange`. |

## `classes_abac/` — camera → OCR pipeline (obfuscated package `a.b.a`, dex `classes.dex`)

| File | Role |
|---|---|
| `a.b.a.c.java` | `DigitOCRManager`: camera lifecycle, preview size, maps the on-screen mask rect into preview coordinates (`a(C0010c, C0010c, Rect)`), rotation → `cameraMode`. |
| `a.b.a.d.java` | `DigitOCRRecognizer`: NV21 `byte[]` → ARGB `Bitmap` (`(Y & 0xff) * 0x10101 \| 0xFF000000`), rotate by cameraMode, crop to the mask rect, `copyPixelsToBuffer` (RGBA_8888) → `XiaoMIDigitOCR.a(ByteBuffer,w,h,4)` → `l()` (recog) → `g()` (result string). |
| `a.b.a.f.java` | `RecognizeController`: HandlerThread that runs `a.b.a.d` off the UI thread, per-frame callback plumbing. |
| `a.b.a.b*.java` | Camera1 preview wrapper; NV21 frame callbacks (`a.b.a.b.h.a(byte[])`). |
| `a.b.a.e*.java` | Small listeners/utilities used by the above. |

Full call chain: `DigitOcrEntry` → `CaptureActivity`/`CaptureFragment` → `a.b.a.c.h()` → `a.b.a.f.c()` →
camera frame → `a.b.a.d.a(byte[], w, h, Point crop, cw, ch, rotation)` → `XiaoMIDigitOCR.a(ByteBuffer,w,h,4)` →
`nativeSetImage` → `nativeRecogDigit` → `nativegetDigitResultString` → text shown in `CaptureFragment`.

## Not decompiled

`com.xiaomi.payment` core (entry/account/plugin framework) is out of scope; only the OCR call chain was decompiled.

## `consumer/` — host-app side (dex `classes2.dex`), added by the UI/JNI patch

Targeted `jadx --single-class` on the *caller* of the OCR entry and its data model.

| File | Role |
|---|---|
| `com.xiaomi.payment.channel.VoucherFragment.java` | Voucher / "Mi Coin card password" screen: builds `CameraIntentData` (prompt + allowed lengths), launches the OCR entry `f.Y1` with requestCode 31, receives the digit string into a `FormattableEditText`, validates the length before submitting. |
| `com.xiaomi.payment.o.l.java`, `com.xiaomi.payment.o.h.java` | `VoucherPayRechargeMethod` (server-provided): `long m,n` = allowed code-length window, `h` = prompt, `i` = hint, `l` = `EntryData` for the "how to get a code" link. |
| `com.xiaomi.payment.entry.a.java`, `com.xiaomi.payment.entry.IEntry.java` | `EntryManager`: id → `IEntry` provider lookup, fragment/activity adapters (`startActivityForResult`), `IEntry.f3559b = "arguments"`. |
| `com.xiaomi.payment.l.f.java` | constants used by the flow (`Y1 = "mibi.ocr"`, `Z1 = "payment_recharge_method"`, `I5`, `K5`, `r2`). |
| `com.xiaomi.payment.l.c.java` | `EntryData` (link target model). |
| `com.mipay.common.data.v0.java`, `com.mipay.common.data.h0.java` | `v0.d()` → `Build.IS_TABLET` (gates the orientation listener); `v0.a()` asset-copy helper used by `XiaoMIDigitOCR.a(Context)`. |

Bytecode cross-reference used to find the caller:
`evidence/ui_jni/probe_xref.py` (androguard dex scan; `xref.txt`).
Note that `"mibi.ocr"` is stored as a `static final` encoded value in `l.f`, not as a
`const-string`, so it does not appear in the xref — the link is established by
`VoucherFragment.j0()` → `f.Y1` → `assets/manifest/ocr/plugin.xml`.
