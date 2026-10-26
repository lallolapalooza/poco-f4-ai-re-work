# Native artifacts — `libdigit_ocr.so`

Library: `lib/arm64-v8a/libdigit_ocr.so`, 767,880 B, sha256
`f374c84e71a692822fc344caa13734d9aa26ad9d8729ff478aabe97661507f2a`
(also shipped: `armeabi-v7a` 501,112 B and `x86_64` 784,600 B — same feature set).

| File | What |
|---|---|
| `elf_info_arm64.json` | `tools/elf_info.py` output: ELF header, 21 sections, 434 exported dynamic symbols, 100 imports, 11 `Java_*` JNI entry points, categorised strings. |
| `ghidra_digitocr.c` | Ghidra 12.1.2 headless pseudocode, 64 functions (`Java_.*|CDigitalOCR|CFEATURE_HOG|MainOCR|DigitSegment|svm_load_model|svm_predict|OCR_STR`), imagebase 0x100000 → subtract 0x100000 for ELF vaddrs. |
| `hog_extract.s` | `capstone` disassembly of `CFEATURE_HOG::ExtractGradientOrientation` @0x14fd8. |
| `cdigitalocr.s` | Disassembly 0x15468–0x15d34: `CDigitalOCR::LoadModel`, `PredictNegPos`, `Predict`. |
| `harness/` | qemu-aarch64 harness used to verify the reimplementation against the shipped binary (source, shim, build script, binary). |

## ABI / call map (confirmed)

```
Java_com_xiaomi_payment_camera_XiaoMIDigitOCR_nativeBeginProcess(env,cls,long,jstr9,jstr2)
        -> MainOCR::beginProcess(const char* svm9, const char* svm2)
             -> CDigitalOCR::LoadModel(std::string,std::string) -> svm_load_cvt_model() x2
Java_..._nativeSetImage(env,cls,long,ByteBuffer,w,h,fmt)  -> MainOCR::setImage(ptr,w,h,fmt)
Java_..._nativeRecogDigit(env,cls,long,int[])             -> MainOCR::recogdigitOCR() / process_OneImg
Java_..._nativegetDigitResultString                       -> MainOCR::getRecognitionResultString()
```

`MainOCR` object layout (from the ctor at 0x29aa8): `DigitSegmentManagement` at +0x0,
`CDigitalOCR` (two `svm_model*`: 10-class at +0x110, binary at +0x118) at +0x110,
BBox list +0x768, result digits `int[N]` +0x7d8, image ptr +0x7a8, w +0x7b0,
h +0x7b4, format +0x7b8 (1..4), result count +0xaf8, default digit range 15..19
(`setDigitNumberRange(15,19)` in the ctor — bank-card PAN length).

Model loading: `svm_load_model` (0x23578) opens the path `"rb"` **and** appends the
loaded model text to a hard-coded debug file `D:\123.txt` (`"a+"`) — a leftover in
the vendor's libsvm build; `svm_load_cvt_model` (0x24b20) pre-converts the model.
Both produce numerically identical predictions (verified).

## Verification harness

`harness/harness.cpp` (aarch64, cross-gcc 16) links the shipped `.so` and calls its
exported C++ symbols directly (asm labels, e.g. `_ZN11CDigitalOCR13PredictNegPosEPhiiRi`).
It needs a 20-line bionic shim (`harness/shim.c`: `__android_log_print`, `__errno`,
`_ctype_`, `__sF` + stdio interposition for bionic's fake `FILE`s, which is what
`std::cout` logging inside `DigitSegment::FindDigitTargetRect` writes to).

Two benign patches are applied to the *copy* used by the harness (`build.sh`):
`DT_INIT_ARRAYSZ` 0x68→0x20 (bionic skips the 9 NULL slots that glibc's loader would
call) and `DT_FINI_ARRAYSZ`→0. The APK and the original `.so` are untouched.

Modes: `hog` (feature vector), `cls` (features + 10-class probs + binary probs),
`dec` (45 one-vs-one decision values), `pipeline` (full MainOCR: beginProcess →
setImage → process_OneImg → digit array → result string → dump of the normalised
20x32 patches via `DigitSegmentManagement::getDigitMatBuffer`).

All reference numbers are already captured in `../evidence/` and `../reimpl/verify.txt`;
re-running is optional (`harness/build.sh`).
