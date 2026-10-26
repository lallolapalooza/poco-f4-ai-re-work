# TikTok `musically` (com.zhiliaoapp.musically) — AI subsystem

* APK: `cust/app/customized/partner-com.zhiliaoapp.musically_100/partner-com.zhiliaoapp.musically_100.apk`
* sha256 `3a488bed7ae1b2da18a947161a94a312ae64ad01137339774d37d5847909f2f9`, 118 824 480 B
* package `com.zhiliaoapp.musically`, version 24.2.0 (2022402000), label "TikTok",
  minSdk 19 / targetSdk 30, arm64-v8a only, 13 dex.
* Sibling variant `com.ss.android.ugc.trill` (analysed by ResTIKTOK2) is
  byte-identical for all three models and all AI libraries
  (`evidence/hashes.txt`, cross-checked against `reports/tiktok_trill`).
* AI content: three ByteDance "smash face" model bundles + the ByteNN/espresso
  runtime (`libbytenn.so`, `libbytennwrapper.so`, `libByteAINN.so`,
  `libByteAIDT.so`) + the 19 MB effect SDK `libeffect.so` which is the only
  native consumer of the bundle format. No TFLite/NNAPI/Mediapipe model.

---

## 1. Inventory and role of each artifact

| asset | size | sha256 (full in evidence/hashes.txt) | role |
|---|---:|---|---|
| `assets/model/ttfacemodel/tt_face_v10.0.model` | 905 491 | `175210db…8fb65261` | base face detector bundle (9 sub-models) |
| `assets/model/ttfacemodel/tt_face_extra_v13.0.model` | 573 858 | `d0f4e9f9…84828d92` | extra/landmark-eye refinement bundle (2 sub-models) |
| `assets/model/ttfaceattrmodel/tt_face_attribute_age_v1.1.model` | 203 028 | `8efeb7f2…485c731` | age attribute bundle (1 sub-model) |

AI libraries (hashes + `native/elf_info_*.txt`):

| lib | size | role |
|---|---:|---|
| `libeffect.so` | 19 280 928 | ByteDance EffectSDK/Bach; **loads the three bundles by path/name**, runs FaceDetect/FaceAttr, imports `espresso::Thrustor` from libbytenn |
| `libbytenn.so` | 1 959 936 | ByteNN + espresso engine; BM model container parser, md5 checks, XOR+inv-Sbox net-def deobfuscation, OpenCL/GLES backends |
| `libbytennwrapper.so` | 43 112 | JNI bridge for `com.ss.bytenn.API` (9 functions) |
| `libByteAINN.so` | 38 808 | pitaya engine plug-in `ByteNNEngineStone` (registers with `pitaya::RegisterEngine`) |
| `libByteAIDT.so` | 18 264 | pitaya decision-tree plug-in |
| `libgaia_lib.so` / `libtailor.so` | 415 728 / 55 376 | network/perf support (no model loading) |

---

## 2. Java / app layer (confirmed)

`com.bef.effectsdk.ModelsList.<clinit>` (`classes2.dex`) is the **only Java
reference** to the three assets:

```java
list = Arrays.asList(
  "model/ttfacemodel/tt_face_extra_v13.0.model",
  "model/ttfacemodel/tt_face_v10.0.model",
  "model/ttfaceattrmodel/tt_face_attribute_age_v1.1.model");
```

`com.bef.effectsdk.EffectSDKUtils` takes `ModelsList.list` as `assetFiles`
(line 36), copies the assets to the app model dir with
`getAssetRelativePath()` (e.g. `ttfacemodel/tt_face_v10.0.model`) and checks
`needUpdate()` against `AssetManager.open().available()`
(`sources/classes/EffectSDKUtils.java`). `com.bef.effectsdk.ModelnamesAssigner`
exposes two JNI calls that bind model names to effect resources. The generic
ByteNN path is `com.ss.bytenn.API` (`classes6.dex`): 9 native methods,
`ByteNNConfig.init(7Xf forwardType, ByteBuffer model, String[] inputNames,
String oclKernelBinPath, String modelName)`, `Tensor` with NCHW/NHWC + Float/Fp16/Int8
(`sources/classes/{API,ByteNNConfig,Tensor}.java`); the JNI glue is
`libbytennwrapper.so` (`Java_com_ss_bytenn_API_nativeInitEngine` reads exactly
these fields, `native/wrapper_nativeInitEngine.s`).

---

## 3. Feature map

| # | feature | entrypoints | models / sub-nets | confidence |
|---|---|---|---|---|
| 1 | TT face detection (smash-face base) | `ModelsList.<clinit>` → `EffectSDKUtils.copyAssets` → `libeffect` `FaceDetectTaskTT` / `bef_fs_createHandler` / `FS_CreateHandlerFromBuf` / `AlgorithmFace::doLoadModel` | `tt_face_v10.0.model`: `detect`, `base120`, `base120det`, `image`, `slowimage`, `mean` (+`pout` output) | loading confirmed; model→feature mapping inferred |
| 2 | extra face landmarks / eye refinement | `FS_AddExtraModelFromBuf`, `AlgorithmFace add extra model fail`, `FaceFitting_InitModelFromBuf2` | `tt_face_extra_v13.0.model`: `iris`, `eyecls`, `partface` | loading confirmed; semantics inferred |
| 3 | face age attribute | `FaceAttributesTask`, `FaceAttributesAlgorithm`, `AEAlgorithmFaceAttr FS_DoAttrPredict`, string `ttfaceattrmodel/tt_face_attribute_age_v1.1.model` | `tt_face_attribute_age_v1.1.model`: `agenet` | loading confirmed; semantics inferred |
| 4 | face reshape / beauty driven by landmarks | `BEF::FaceReshapeFeature`, `bef_effect_*reshape_face*`, shipped effect assets `assets/ttlive_FaceReshape_V2.zip`, `ttlive_reshape_composer.zip`, `ttlive_beauty_composer.zip` | consumes result of 1/2 | assets confirmed, model dependency inferred |
| 5 | generic ByteNN inference SDK | `com.ss.bytenn.API` ↔ `libbytennwrapper.so` ↔ `libbytenn.so`; app ML evaluator | arbitrary BM models (not the three bundles) | confirmed |

Record names and the surrounding SDK strings come from `libeffect.so`:
`evidence/libeffect_face_strings.txt` (e.g. `base120|base120det|iris|eyecls|
slowimage|partface` @0xd66161, `pout` @0xd85571, `agenet` @0xd66e11,
`Extract model name: slowimage fail` @0xd65e80, version logs @0xd65d93 /
@0xd65deb, `AlgorithmFace::doLoadModel` @0xd918dd, record layer names
`crop_1`, `topdown0_proj.0`, `up0`, `elt1`, `classifier_1`,
`top0_shuffle.conv1.0` @0xd65f09-0xd66154).

### Pipeline

```
camera/album frame (VESDK, BGR 8UC3)
  └─ libeffect.so  smash-face loader  (searches record names inside the bundle)
       ├─ tt_face_v10.0.model : detect+base120(+image/slowimage/mean) → pout/box
       └─ tt_face_extra_v13.0.model : iris/eyecls/partface refinement
  └─ FaceAttributesTask → tt_face_attribute_age_v1.1.model (agenet) → age
       ↓
BEF_HUMAN_FACE_* / VEFaceInfo / VEFaceAttributeInfo → FaceReshape_V2, beauty, stickers
```

---

## 4. `.model` container (decoded structure)

All three files share this layout (parser: `reimpl/model_container.py`;
per-file JSON: `ir/container_*.json`; carved bytes: `models/sections/**`).

```
0x00 16 B   opaque per-file id (not md5/sha of the body; differs per file)
0x10 u32    total file size  (== stat size in all 3 files)
0x14 u32    version-string length -> version string
      u32   record count N (9 / 2 / 1)
      N x record:
        u32    record name length -> ASCII name
        u32    constant 36
        32 B   per-file key A (byte-identical in every record; occurs exactly N times)
        u32    constant 32
        32 B   blob B; u32 at B+4 = size of the descriptor block at payload start
        payload = descriptor block (B[4] bytes) + weight data (rest of payload)
```

Verified invariants (all files, all 12 records; `ir/container_*.json`
`invariants`): declared size == file size; const36/const32 exact; A repeats
exactly N times; **`(B[4]-4) % 16 == 0` for all 12 records**; records are
contiguous with no padding outside the payload.

`tt_face_v10.0.model` (version string `tt_face_v10.0`), in file order:

| record | record off | payload off | payload len | B[4] descriptor | internal offset(s) |
|---|---:|---:|---:|---:|---|
| detect | 0x029 | 0x07b | 19 140 | 4 708 | 4689 |
| base120 | 0x4b3f | 0x4b92 | 363 732 | 6 932 | 6925 |
| base120det | 0x5d866 | 0x5d8bc | 156 292 | 5 140 | 5122 |
| pout | 0x83b40 | 0x83b90 | 7 149 | 676 | 664 |
| eyecls | 0x8577d | 0x857cf | 7 149 | 676 | 667 |
| partface | 0x873bc | 0x87410 | 156 165 | 5 012 | 5003 |
| image | 0xad615 | 0xad666 | 88 508 | 6 644 | 6639 |
| slowimage | 0xc3022 | 0xc3077 | 98 940 | 6 660 | 6652 |
| mean | 0xdb2f3 | 0xdb343 | 7 632 | 20 | – (data record) |

`tt_face_extra_v13.0.model` (`v13.0`): `iris` @0x021 payload 0x071 / 25 864 B,
descriptor 3 556; `extra` @0x6579 payload 0x65ca / 547 800 B, descriptor 11 236.
`tt_face_attribute_age_v1.1.model` (`tt_face_attribute_age_v1.1`): `agenet`
@0x036 payload 0x088 / 202 892 B, descriptor 14 260.

### 4.1 Descriptor block vs weights

The first `B[4]` payload bytes are a **dense binary graph descriptor**, not
ciphertext and not compressed:

* entropy 7.67–7.98 (uniform) for the 11 graph records, 5.03–7.69 for the
  remaining weight bytes (`ir/container_*.json`, `evidence/model_structure.txt`);
* the descriptor tail contains dense little-endian u16 op/tensor tables and
  u32 offsets just below the block size (partface ends
  `02 00 02 00 02 00 03 00 03 00 04 00 08 00 05 00`; internal u32 values
  5003 < 5012, 6639 < 6644, 14253 < 14260, 4689 < 4708, …) — a struct-size
  artifact explaining the `4 mod 16` alignment, not a cipher block;
* zlib/lzma/bz2 fail at every reasonable offset; AES-128/192/256
  ECB/CBC/CTR and ChaCha20 with every plausible key/IV derived from the file
  (A, A halves, B halves, file-id, header, name/hash) produce no plaintext;
  the bytenn XOR+inv-AES-Sbox routine (libbytenn `disassemble` @0x42500,
  loops 0x42808–0x428cc, inv-Sbox @0x184b32) was also applied to full payloads
  and descriptor blocks with the same key set and embedded-key offsets 0–512 —
  entropy rises, no text (details: `evidence/crypto_tests.txt`; independently
  reproduced by ResTIKTOK2 on the identical trill files).

The `mean` record is a data record, not a graph: `[u32 7620][1905 × float32 LE]
[8 zero bytes]` = 7 632 B.  The values are monotone pairs
(30.03/73.78, 30.07/86.69, 30.16/99.6, …) — anchor/prior geometry
(size vs stride), **not** image means.

### 4.2 What is not the container

`libbytenn`'s `BYTENN::EspressoDisassembler::disassemble` /
`disassembleModelBuffer` require a `BM` header: `bytes[0:2]=="BM"`,
`u32@4 == buffer size`, `u8@3` version, graph type at +8, offsets at
+0xc/+0x10/+0x1c/+0x20/+0x24/+0x2c/+0x34, optional md5
(`native/libbytenn.so._ZN6BYTENN20EspressoDisassembler*.s`).  The shipped
files have none of that (first bytes `12 12 1d 17…`, size at +0x10; the
literal `BM` bytes occur only inside weight data and never form a valid
header).  The bundles are a higher-level "smash-face" package that
`libeffect.so` parses.

---

## 5. Native loader path (confirmed)

* `libbytennwrapper.so` JNI reads `modelName`, `model` (ByteBuffer),
  `inputNames`, `numThread`, `oclKernelBinPath` and builds `BYTENN::Config`
  (`native/wrapper_nativeInitEngine.s`).
* `libeffect.so` imports `espresso::Thrustor::{CreateNet, SetInput, Inference,
  Extract, SkipLayer, DefaultInSize}` from `libbytenn.so` (import table,
  `native/elf_info_libeffect.txt`), i.e. it unpacks each sub-model into an
  espresso net-def string + weights pointer and calls Thrustor per record.
* `libeffect.so` string xrefs: version log 0x68a3c0, `AlgorithmFace::doLoadModel`
  thunk 0x962dc0 (delegates to per-type function pointer with buffer + size),
  `Extract model name` logger 0x6969e0, model-type switch 0x696218, model
  construction helpers 0x696634/0x696664/0x696830, `FS_*` face API strings.
  Disassembly: `native/libeffect_faceinit.s`, `libeffect_extract{1,2,3}.s`,
  `libeffect_face_doload.s` (0x962c00).
* `BachPackedModelUtil::checkFileHeader` (`native/libeffect_check_header.s`,
  0x9515f8) requires `u32@0 == buffer size`; our bundles fail this check, so
  they are not the Bach "packed model" format either.

**Blocker**: the descriptor schema (tensor descriptors, op codes, quantisation
tables, offsets) exists only inside the stripped `libeffect.so` smash-face
loader; the function that turns payload bytes into the `map<string, model*>`
consumed by `ExtractModel` (0x696900) is not yet isolated.  Without it,
per-tensor weight slicing and exact layer topology stay unknown.

---

## 6. I/O contracts, preprocessing (what is known)

Confirmed only: bytes-in/bytes-out via espresso (`SetInput(name, ptr, w, h, c)`,
`Inference`, `Extract(name)`), effect SDK frames are OpenCV BGR `CV_8UC3`
(assert strings) and ByteNN tensors are float32/int8/fp16
(`Tensor`/`ByteNNConfig`), model names and sub-model names as above.

Inferred (documented in `reimpl/pipeline.py` docstrings):

| model | input (inferred) | outputs (inferred) |
|---|---|---|
| tt_face_v10.0 | BGR face/image crop, `base120` suggests 120×120; per-record `image`/`slowimage` + `mean` data; scale `(x-127.5)/128` | `pout` objectness map + `base120det` box regression (30×30) |
| tt_face_extra_v13.0 | aligned face crop ~120×120 | `iris` offsets, `eyecls` 2-class logits, `partface` 5-class part segmentation (FPN+shuffle decoder per libeffect layer names) |
| tt_face_attribute_age_v1.1 | aligned face crop; libeffect asserts a `bgr448` CV_8UC3 crop | `agenet` age (single value or age bins) |

Unknown: exact H/W/C, NCHW vs NHWC per tensor, quantisation (int8 scales/
zero-points), normalization constants (the `mean` record is anchor geometry,
not image means), age output layout, whether the extra bundle is always loaded.

---

## 7. Reimplementation

```
reimpl/model_container.py   real container parser/verifier/carver (no deps)
reimpl/pipeline.py          JAX pipeline, synthetic-face self-check
reimpl/README.md
reimpl/verify.txt           captured output (exit 0)
```

Run: `$PY reimpl/model_container.py --carve` and `$PY reimpl/pipeline.py
--verify` (JAX 0.12.0.1 CPU).  `pipeline.py` prints:

```
face_detector  (1,120,120,3) -> pout (1,30,30,1), box (1,30,30,4)
extra_model    (1,120,120,3) -> iris (1,30,30,2), eyecls (1,2), partface (1,30,30,5)
age_model      (1,448,448,3) -> (1,1)
decoded mean   (1905,) float32, first4 [30.032, 73.782, 30.069, 86.690]
```

**Status: ARCHITECTURE_ONLY** for the four networks (topologies are
placeholders); the container reader and the 1905 decoded float32 values are
real.  For a *verified* numerical reimplementation the descriptor schema must
be reversed from `libeffect.so`.

---

## 8. Unknowns / blockers

1. Smash-face payload descriptor schema (op/tensor tables, offsets) — blocks
   weight slicing, true topology and I/O shapes.
2. Per-record dtype/quantisation (int8 vs fp16 vs fp32; the weight regions show
   both long fp32 runs and dense int8/int16-looking bytes).
3. Exact input resolution and color order per sub-model.
4. Age head output layout (regression vs class logits, class boundaries).
5. Whether `tt_face_extra_v13.0.model` is always loaded or only for
   landmark/eye effects, and which AB flag enables the TT detector
   (`enable_fastmode_faceDetect240`, `bef_effect_use_TT_facedetect`).
6. Semantics of the `mean` record (anchor/prior geometry confirmed; its
   consumer in the detector is not).

## 9. Evidence index

* `evidence/hashes.txt` — APK/models/libs sha256
* `evidence/cmd.txt` — commands used
* `evidence/model_structure.txt` — parser run (records, entropy, invariants)
* `evidence/libeffect_face_strings.txt` — model/SDK strings + offsets
* `evidence/crypto_tests.txt` — negative cipher/compression tests
* `ir/container_*.json`, `ir/mean_weights.json` — machine-readable layout
* `models/sections/**` — carved descriptor blocks / weight regions / mean fp32
* `native/elf_info_*.txt`, `native/*.s` — ELF facts and targeted disassembly
* `sources/classes/*.java`, `sources/index.md` — targeted Java
* `reimpl/*` — runnable code + verify.txt

---

## 11. Video-frame interface (follow-up, confirmed)

Full evidence: `PIPELINE.md`, `evidence/video_interface.md`,
`evidence/vesdk_drawframe_callsites.txt`, `evidence/libeffect_face_video_strings.txt`,
`native/libeffect_musically_*.s`, `sources/video/`.

### 11.1 Same binaries as `trill`

`libeffect.so` `dad4778a…e76c35`, `libttvesdk.so` `e38abe25…a6ce74` and the three
model bundles are byte-identical in both variants, so the frame path is the same and
all native addresses apply to both. Only the Java obfuscation map differs (see 11.4).

### 11.2 Frame path

```
VESDK render loop (libttvesdk.so, logs "DrawFrame:LoadResource" @0x5cb5e5,
                   "DrawFrame:bef_effect_algorithm" @0x5cb62f)
   frame descriptor, stride 0x48: [+0x00]=texture id, [+0x14]=w, [+0x18]=h, [+0x20]=double t
   │  bef_effect_set_width_height(handle, w, h)          0x1cf548
   ▼  bef_effect_process_texture(handle, texId, arg2, t) 0x1cf950  (or _mimo 0x1cf9bc,
   │                                                      native_buffers 0x1cf81c,
   │                                                      algorithm_multi_texture 0x1cf740)
libeffect.so 0x3097f0 → bef_effect_process_texture_with_loading_timeout 0x3097f8
   │  effect/algorithm graph: FaceDetectTaskTT (0xd02a5c), FaceAttributesTask (0xd25094)
   ▼
BES::NtfEvent<algorithm_result_face_st>      (0xdbe58d / 0xca414b)
BES::NtfEvent<algorithm_result_face_attrs_st>(0xdbe232 / 0xca40e9)
   ▼
VEFaceDetectInfo (106 pts, rect, score, pose, eye distance, action, faceID,
                  trackCount, visibility, iris/eye/lip/eyebrow)   → effect nodes,
VEFaceAttributeInfo (age, gender, expression, …)                  preview/encoder
```

Java-visible binding: `BEFEffectNative.nativeProcessFrame(long,int,int,int,int,double)`
(`sources/video/com.bef.effectsdk.BEFEffectNative.java:21`). **The confirmed caller is
`com.ss.android.ugc.aweme.view.customView.NaviGLSurfaceView.onDrawFrame`**
(`sources/video/NaviGLSurfaceView.java:233,245,265`): it calls `processAlgorithm` for
detection-only and `processFrame` on the two orientation branches. The earlier
"no Java class calls it" result was a `dex_find --regex processFrame` artifact — the
call site is in a method with a different name, so a method-name scan could not see it.
The VESDK recorder path is native and links `libeffect.so` (`NEEDED`); the Navi preview
path is the confirmed Java call site.

### 11.3 The two leading ints — resolved (trill left this unknown)

`nativeProcessFrame` forwards Java arg 2 as `process_texture` arg 2 and Java arg 3 as
`process_texture` arg 3, in order; args 4/5 are width/height and arg 6 is the double
timestamp. Proof: thunk `0xaf58d4` = `mov w19,w4; mov w20,w3; ret`, thunk `0xa9f91c` =
`mov x0,x21; mov w1,w20; mov w2,w19; ret`, then tail `b bef_effect_process_texture`
(`native/libeffect_musically_nativeProcessFrame.s`). `arg2` is not read by the
`process_texture` wrapper body — its meaning is still `unknown`.

Texture format is confirmed RGBA8 `GL_TEXTURE_2D`
(`glTexImage2D` with `0xde1/0x1908/0x1401`, libttvesdk 0x4b7314–0x4b7330); every DNN
task rejects YUV/GRAY (`Not support yuv/gray` @0xd02ab1/0xd02adf). Rotation and camera
facing are separate setters (`nativeSetFrameOrientation` → `bef_effect_set_frame_device_orientation`,
`nativeSetCameraPosition` → `bef_effect_set_camera_device_position` with the boolean
inverted).

### 11.4 Variant differences vs `trill`

| item | trill | musically |
|---|---|---|
| native libs/models | — | byte-identical |
| `VEFaceDetectInfo` field class | `X.PVJ` | `X.C64698PZc` |
| ext info class | `X.PVI` | `X.C64697PZb` |
| parcel wrapper / logger | `X.C36340EMk` / `X.C36453EQt` | `X.C34719DjF` / `X.C7Q2` |
| `Covode.recordClassIndex` (VEFaceDetectInfo) | 115413 | 115417 |
| field order / `covert()` / 106 points | identical | identical |

### 11.5 Correction

Report §6 stated that `libeffect.so` asserts a `bgr448` CV_8UC3 crop for the age model.
That string does not exist in this library (`grep -E 'bgr448|448'` → no hit). The age
model input size is therefore **unknown**, and the input contract of the attribute model
is limited to "aligned face crop of unknown size".
