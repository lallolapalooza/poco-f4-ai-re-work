# TikTok `trill` (com.ss.android.ugc.trill) — AI subsystem

* APK: `cust/app/customized/partner-com.ss.android.ugc.trill_101/partner-com.ss.android.ugc.trill_101.apk`
* size 118 846 600 B, sha256 `c6e9b326f424800373384076f2490553c25aa29a1b358081e8633b9e6d8b59cc`
* package `com.ss.android.ugc.trill`, version 24.2.0 (240200), label "TikTok",
  minSdk 19 / targetSdk 30, arm64-v8a only, 13 dex, 129 native libs.
* Sister variant analysed in parallel: `tiktok_musically`
  (`com.zhiliaoapp.musically`, 24.2.0). Comparison in §2.

The AI content of this APK is exactly three face models plus the ByteDance
ByteNN / effect-SDK runtime. There is no TFLite, NNAPI or Mediapipe model.

---

## 1. Inventory (confirmed)

| asset | size | sha256 |
|---|---:|---|
| `assets/model/ttfacemodel/tt_face_v10.0.model` | 905 491 | `175210db…8fb65261` |
| `assets/model/ttfacemodel/tt_face_extra_v13.0.model` | 573 858 | `d0f4e9f9…84828d92` |
| `assets/model/ttfaceattrmodel/tt_face_attribute_age_v1.1.model` | 203 028 | `8efeb7f2…485c731` |

Native AI libraries (`lib/arm64-v8a/`), all in `native/`:

| lib | size | role |
|---|---:|---|
| `libbytenn.so` | 1 959 936 | ByteNN/espresso inference engine (BM container, OpenCL/GLES backends) |
| `libbytennwrapper.so` | 43 112 | JNI bridge `com.ss.bytenn.API` → libbytenn |
| `libByteAINN.so` | 38 808 | pitaya engine plug-in ("ByteAI NN" stone) |
| `libByteAIDT.so` | 18 264 | pitaya engine plug-in for DecisionTree (`libbytedt.so`) |
| `libAndroidPitayaCore.so` | 1 034 968 | pitaya model dispatcher |
| `libeffect.so` | 19 280 928 | ByteDance EffectSDK: face detect (`FaceDetectTaskTT`), attributes (`FaceAttributesTask`), beauty/reshape |
| `libgaia_lib.so`, `libtailor.so` | 415 728 / 55 376 | effect SDK supporting libs |
| `libavmdl*.so` | 596 144 / 22 280 / 108 552 | AV model runtime (not used by these assets) |

`libbytennwrapper.so` exports the nine JNI functions used by
`com.ss.bytenn.API` (`nativeCreateEngineInstance`, `nativeInitEngine`,
`nativeSetEngineInputs`, `nativeInference`, `nativeGetEngineOutputs`, …,
`native/elf_info` + `sources/classes/API.java`). `libByteAINN.so` exports
nothing (0 exports) — it registers a `pitaya::ByteNNEngineStone` at load time.

---

## 2. Variant comparison: `trill` vs `musically`

Method: zip member names + CRCs of both APKs; sha256 of every extracted model
and AI library (`evidence/variant_diff.txt`, `evidence/hashes.txt`).

| item | trill | musically | result |
|---|---|---|---|
| package / version | `com.ss.android.ugc.trill` / 24.2.0 | `com.zhiliaoapp.musically` / 24.2.0 | differ (shell only) |
| APK size | 118 846 600 | 118 824 480 | +22 120 B in trill |
| zip entries | 16 170 | 16 171 | musically has extra `assets/jsb_whitelist.zip` |
| `tt_face_v10.0.model` | `175210db…` | `175210db…` | **identical** |
| `tt_face_extra_v13.0.model` | `d0f4e9f9…` | `d0f4e9f9…` | **identical** |
| `tt_face_attribute_age_v1.1.model` | `8efeb7f2…` | `8efeb7f2…` | **identical** |
| `libByteAINN.so` / `libByteAIDT.so` | `e39117cb…` / `e3b4331e…` | same | **identical** |
| `libbytenn.so` / `libbytennwrapper.so` | `f488e553…` / `d504155c…` | same | **identical** |
| `libgaia_lib.so` / `libtailor.so` | `913a0fba…` / `ed757767…` | same | **identical** |
| `libavmdl*.so` | `10bea17f…` / `c1c8aa80…` / `94e7d5a9…` | same | **identical** |
| dex/manifest/resources/signature | — | — | differ (26 members incl. `classes4–11,13.dex`) |

So the *AI subsystem is byte-identical* between the two partner builds; only the
application shell (manifest, resources, obfuscated dex payloads, api keys,
signature) differs. All findings below therefore apply to both variants.

---

## 3. Java / app level (confirmed)

### 3.1 Model provisioning
`com.bef.effectsdk.ModelsList.<clinit>` (classes2.dex,
`sources/classes/ModelsList.java`) contains exactly:

```java
list = Arrays.asList(
  "model/ttfacemodel/tt_face_extra_v13.0.model",
  "model/ttfacemodel/tt_face_v10.0.model",
  "model/ttfaceattrmodel/tt_face_attribute_age_v1.1.model");
```

`com.bef.effectsdk.EffectSDKUtils` (classes2.dex) takes this list as its
`assetFiles` field and, on first run / version change, copies each asset to the
app model directory, keeping the path *after* `model/`
(`copyAssets()` → `getAssetRelativePath()`), i.e. the files end up as
`<dir>/ttfacemodel/tt_face_v10.0.model`, `<dir>/ttfaceattrmodel/…`. A size check
against `AssetManager.open().available()` drives `needUpdate()`.

### 3.2 ByteNN JNI path (app ML SDK)
`com.ss.android.ml` uses an evaluator factory implemented in
`com.ss.android.ugc.aweme.inferenceengine.InferenceEngineServiceImpl`
(classes5.dex). Its anonymous evaluator (`X.8Kf`,
`sources/classes/InferenceEngineServiceImpl.java` and
`X_8Kf.instructions.txt`) does:

1. `API.CreateEngine()` → handles a native engine instance.
2. `ByteNNConfig.init(forwardType, modelBuffer, null, "", "")`, forward type
   `Auto` when `MLConfigModel.bytenn_forward_type == "auto"` else `CPU`;
   `setThreadNum(2)` only if `num_threads == 2`, else 1.
3. `API.InitEngine(config)`; the model is a `MappedByteBuffer` (memory-mapped
   model file).
4. Inference: `GetEngineInputConfig()` → `Tensor.setData(ByteBuffer)` →
   `SetEngineInputs()` → `Inference()` → `GetEngineOutputs()` →
   `Tensor.getData()` read as **float32** into `float[][]`.

Error strings observed: `"byteNN create engine failed with code "`,
`"byteNN init engine failed with code "`, `"byteNN inference get output failed
with code "`, `"bytenn inference error, outBuffer.size != resultBuffer.size"`.

### 3.3 Face-detect / attribute switches
VESDK-facing names exist in the dex string pool of classes6.dex (settings bean
fields/methods, not constants): `isUseTTFaceDetect`, `setUseTTFaceDetect`,
`useTTFaceDetect`, `setFaceAttribute`, `nativeSetFaceAttribute`, plus model
types `com.ss.android.medialib.model.FaceAttribute*` and
`com.ss.android.vesdk.faceinfo.VEFaceAttributeInfo`. So the app can select the
ByteDance ("TT") face detector in the VESDK record/preview pipeline and read
per-face attributes (age/expression) from `VEFaceAttributeInfo`.

### 3.4 Effects that consume the models
The APK ships effects that rely on the effect-SDK face tracking:
`assets/ttlive_FaceReshape_V2.zip` (`FaceReshape_V2/config.json` type
`FaceReshape_V2`, `distortion.json` with landmark-index driven warps `pos: 23`,
`pos: 19`), `assets/ttlive_reshape_composer.zip` (type `FaceDistortion`),
`assets/ttlive_beauty_composer.zip`, `assets/ttlive_Live_Beauty_12.zip`
(`"requirement": {"faceDetect": false}`). The models themselves are effect-SDK
built-ins (see §5), not effect-package payloads.

---

## 4. The `.model` container (confirmed structure, undecoded payload)

Header, identical scheme in all three files:

```
0x00  16 B   opaque per-file id (not md5/sha of the body; differs per file)
0x10  u32    total file size (== stat size: 905491 / 573858 / 203028)
0x14  u32    model-name length  ->  name[ ]  ("tt_face_v10.0", "v13.0",
                                              "tt_face_attribute_age_v1.1")
       u32   record count N      (9 / 2 / 1)
       N x record:
         u32   record-name length -> name[ ]  (ASCII)
         u32   constant 36
         32 B  per-file constant key  (byte-identical in every record)
         u32   constant 32
         32 B  per-record blob
         ...   payload
```

Evidence: `evidence/model_structure.txt`, `ir/*.model.json`,
`reimpl/model.py`. Verified invariants:

* the 32-byte key occurs **exactly N times** in each file (9/9, 2/2, 1/1);
* `const36 == 36` and `const32 == 32` in every record of every file;
* `declared_size == file size` in all three.

Record tables (payload extents from `reimpl/model.py`):

`tt_face_v10.0.model` — tag `detect`; records in file order:

| record | payload | size | payload begins with |
|---|---|---:|---|
| `detect` | 0x07b–0x4b3f | 19 140 | `a3d4d575` + 32-B signature |
| `base120` | 0x4b92–0x5d866 | 363 732 | `2c95508a…` |
| `base120det` | 0x5d8bc–0x83b40 | 156 292 | `5c23c920…` |
| `pout` | 0x83b90–0x8577d | 7 149 | `9183084c…` |
| `eyecls` | 0x857cf–0x873bc | 7 149 | `1da9f6a2…` |
| `partface` | 0x87410–0xad615 | 156 165 | `c21ff35e…` |
| `image` | 0xad666–0xc3022 | 88 508 | `d31e7f52` then the shared 112-B prefix |
| `slowimage` | 0xc3077–0xdb2f3 | 98 940 | `fdfb3ad7` + same shared 112-B prefix |
| `mean` | 0xdb343–0xdd113 | 7 632 | `u32 7620` + 1905 float32 + 8 zero bytes (§4.3) |

`tt_face_extra_v13.0.model` — tag `iris`: records `iris` (25 864 B) and
`extra` (547 800 B). `tt_face_attribute_age_v1.1.model` — tag `agenet`:
one record `agenet` (202 892 B).

### 4.1 Not TFLite, not the bytenn `BM` container
`find`: no `TFL3` magic. Ghidra decompilation of
`BYTENN::EspressoDisassembler::disassemble(ByteNNInternalConfigExt&)` in
libbytenn.so (`native/bytenn_loader_decompiled.c`) shows the loader requires
`model[0]=='B' (0x42)`, `model[1]=='M' (0x4d)`, `u32 @+4 == buffer size`,
`u8 @+3 == version`, `u32 @+8 == graph type (3/4/5)`, string/dir offsets at
+0xc/+0x10/+0x1c/+0x20/+0x24/+0x2c/+0x34. The shipped files have none of this
(first bytes `12 12 1d 17…`, size at +0x10), and the literal ASCII `BM` occurs
8×/1×/0× (v10/extra/age) without any becoming a valid BM header. So the
`.model` files are a *different, higher-level* ByteDance container ("smashed
face" bundle) that `libeffect.so` understands, not the espresso net format.

### 4.2 libeffect.so consumes them by name search (confirmed)
`libeffect.so` contains (offsets in `evidence/libeffect_face_strings.txt`):

```
0xd65d93  smash face base model version;: %s
0xd65db7  tt_face_v10.0.model
0xd65deb  smash face extra model version;: %s
0xd65e10  tt_face_extra_v13.0.model
0xd65e2a  Loading smash face fast extra model version;: %s
0xd65e5c  tt_face_extra_fast_v13.0.model
0xd65e80  Extract model name: slowimage fail
0xd65ea4  Extract model name: imagedetect fail.
0xd66161  base120 | base120det | iris | eyecls | slowimage | partface
0xd66e11  agenet
0xd85571  pout
0xd028e3  %s [%s %d] --faceDetectAlgorithm: fail %d
0xd0290d  createFinder init FS_AddExtraModelFromBuf Face fail %d %p
0xd02952  --faceDetectAlgorithm: use fast-mode faceDetect240
0xd28af3  faceDetect240 / faceDetect280 / faceDetectDu
0xcdb9a4  "enable_fastmode_faceDetect240"   (AB setting)
0xd25032  FaceAttributesAlgorithm / FaceAttributesTask / FaceAttributesTask.cpp
0xcd9f40  ttfaceattrmodel/tt_face_attribute_v7.2.model
0xd25007  ttfaceattrmodel/tt_beauty_attr6_v2.0.model
0xd9283f  ttfaceattrmodel/tt_face_attribute_age_v1.1.model
0xd9288f  ttfaceattrmodel/tt_face_attribute_exp_v1.0.model
```

The record names in the files are exactly these strings, and the error messages
("Extract model name: slowimage fail") show the native code **searches the model
buffer for record names** instead of using a length table — which matches the
container having no payload lengths (§4). Code xref of
"Extract model name: slowimage fail" is `libeffect.so` 0x68b384 (ADRP/ADD pair at
0x68b378/0x68b37c), inside the smash-face loader
(`native/libeffect_string_xref_68b360.s`).

### 4.3 Payload encoding — decoded parts, open parts

Every record header carries a second length: the `u32` at `blob2+4`. For all 12
records of the three files it satisfies `(len - 4) % 16 == 0`:

| record | blob2+4 | record | blob2+4 |
|---|---:|---|---:|
| detect | 4 708 | base120 | 6 932 |
| base120det | 5 140 | pout | 676 |
| eyecls | 676 | partface | 5 012 |
| image | 6 644 | slowimage | 6 660 |
| mean | 20 | iris / extra / agenet | 3 556 / 11 236 / 14 260 |

That length delimits a **descriptor block at the start of the payload**
(`evidence/payload_blocks.txt`): for every non-`mean` record the first
`blob2+4` bytes have entropy 7.71–7.97 bit/B, while the remainder (the weight
data) has 5.03–7.69. (`mean` is a pure data record: its `blob2+4` is 20 but its
payload is the self-describing `[len][floats]` blob below, so the split rule
does not apply to it.) The blocks are structured, not encrypted: their tails are
dense little-endian `int16` op/tensor arrays (e.g. `partface` ends with
`02 00 02 00 02 00 03 00 03 00 04 00 08 00 05 00`) interspersed with `u32`
values that are just below the block size (`0x138b`=5003 vs block 5012 for
`partface`; `0x19ef`=6639 vs 6644 for `image`; `0x0298`=664 vs 676 for `pout`),
i.e. offsets/lengths of the graph description. So the payload is
`[descriptor block][weight tensors]`; what is missing is only the descriptor
schema (field order/op enum), not a cipher key.

`mean` is fully decoded and is *not* per-channel means:
`[u32 7620][1905 × float32][8 × 00]` = 7 632 B exactly. The values form a
monotone pair table — 30.03/73.78, 30.07/86.69, 30.16/99.6, 30.82/112.6,
32.35/125.7, 34.86/138.6, …, range −2.77…255.2 — i.e. anchor/prior geometry
(anchor size / stride table), not image normalisation constants. Preprocessing
therefore still has to be taken from `libeffect.so`, not from this record.

Cipher negative result: `libbytenn.so` decrypts BM models with
`out[i] = inv_sbox[in[i] ^ key[i % 32]]` (table `.rodata` 0x184b32, loops
0x42818–0x428cc of `disassemble`; key pointer = `model + u32@+0x20`),
disassembly in `native/bytenn_cipher_42800.s`. Applying that transform to these
payloads with the per-file key, the per-record blob, the file id or the block
prefix **raises** entropy (6.8–7.7 → 7.9) and never yields `BM`, so the bytenn
model cipher does not protect these files.

Still unknown: descriptor field layout (op enum, tensor ids, quantisation
scale/zero-point), tensor shapes, and the exact weight encoding after the
descriptor block (float32-dominant, but mixed with integer tables).

---

## 5. Feature map and pipeline

```
camera / album frame (VESDK preview or editor)
   │  VESettings.isUseTTFaceDetect / setUseTTFaceDetect   (dex strings, inferred switch)
   ▼
libeffect.so  FaceDetectTaskTT            (req "faceDetect", AB enable_fastmode_faceDetect240)
   │  loads model/ttfacemodel/tt_face_v10.0.model  (smash-face base: detect, base120, base120det, image/slowimage, mean)
   │  optional model/ttfacemodel/tt_face_extra_v13.0.model  (iris/eyecls/partface landmarks, FS_AddExtraModelFromBuf)
   ▼
BEF_HUMAN_FACE_* results  →  VEFaceInfo / FaceAttribute models
   │
   ├─ effects: FaceReshape_V2 / FaceDistortion (uses landmark indices), beauty composer, live beauty 12
   └─ FaceAttributesTask + FaceAttributesAlgorithm
          loads model/ttfaceattrmodel/tt_face_attribute_age_v1.1.model  (agenet, age)
          result handled by handleFaceAttributeEvent
```

ByteNN JNI path (parallel, app ML SDK): `InferenceEngineServiceImpl` →
`X.8Kf` → `com.ss.bytenn.API` → `libbytennwrapper.so` → `libbytenn.so`
(espresso engine, `CreateNet`/`SetInput`/`Inference`/`Extract`; libeffect.so
imports `espresso::Thrustor::CreateNet(name, void* model, vector<string>&)`,
`SetInput`, `Inference`, `Extract`, `ThrustorDefaultInSize`).

Model → feature mapping:

| model | feature | I/O (confidence) |
|---|---|---|
| `tt_face_v10.0.model` | TT (smash-face) face detection, base net of `FaceDetectTaskTT` | in: image blob (`image`/`slowimage`), preprocess `mean`; out: `pout` (objectness), `base120det`/`partface` (regression / part scores) — *inferred from record names + strings* |
| `tt_face_extra_v13.0.model` | landmark/eye refinement added via `FS_AddExtraModelFromBuf` | in: face crop; out: `iris`, `eyecls`, `extra` — *inferred* |
| `tt_face_attribute_age_v1.1.model` | face age attribute (`FaceAttributesTask`) | in: aligned face crop; out: `agenet` age logits — *inferred* |

Exact tensor shapes/dtypes: **unknown** (payload undecoded). Java side shows a
raw `ByteBuffer` in and float32 out.

---

## 6. Native analysis

* `BYTENN::EspressoDisassembler::disassemble` / `disassembleModels` /
  `CheckMd5sum`: decompiled (`native/bytenn_loader_decompiled.c`). Confirms the
  `BM` requirement, per-model md5 verification
  (`CheckMd5sum(map<string,const u8*>)`), and the multi-model variant
  (`disassembleModels`, map of named sub-models) — the latter is *not* used by
  these files.
* `libbytennwrapper.so`: 9 JNI exports under `Java_com_ss_bytenn_API_*`;
  `libbytenn.so` and `libAndroidPitayaCore.so` provide the engine.
* `libByteAINN.so`: 0 exports, registers `pitaya::ByteNNEngineStone`
  (`pitaya::RegisterEngine`, `ModelMeta(string, ForwardType)`); imports
  `libbytenn.so`.
* `libByteAIDT.so`: pitaya decision-tree stone over `libbytedt.so`.
* `libeffect.so`: 612 exports / 71 JNI; strings and code xref above.
  Face tasks: `FaceDetectTaskTT.cpp`, `FaceAttributesTask.cpp`,
  `FaceAttributesAlgorithm`, `FaceAttributeInterface`,
  `getFaceAttributeInfo`, `handleFaceAttributeEvent`, iris metrics
  (`irisWidth`, `left_iris`, `right_iris`).

---

## 7. Reimplementation status — ARCHITECTURE_ONLY

`reimpl/model.py`:

* `ModelPackage.parse()` — **real** reader for the container above, exercised on
  all three shipped files (`reimpl/verify.txt`, exit 0).
* `init_params()` / `apply()` — JAX shape-compatible placeholder:
  input `(1,120,120,3)` float32, outputs `pout (1,30,30,1)` and
  `box (1,30,30,4)`; deterministic random init. **Not** the shipped topology.
* `preprocess_bgr()` — inferred `(x-127.5)/127.5`, NCHW.

Blocker: the per-record payload is a `[graph descriptor][weights]` encoding whose
descriptor schema exists only in the stripped `libeffect.so` (the weights are
plaintext; only the descriptor field layout is missing). Until that parser
(`FS_AddExtraModelFromBuf` / smash-face loader at `libeffect.so:0x68b3xx`) is
reversed, real weights and the true layer topology cannot be extracted, so no
numerical comparison against a reference runtime is possible. Nothing in the
APK contains a second, simpler copy of these weights.

---

## 8. Unknowns

1. Payload schema of the `smashed face` bundle (tensor descriptors, quantisation,
   op codes) — blocks weight extraction.
2. Exact input resolution/dtype of `image`/`slowimage` (120 from `base120`, but
   `faceDetect240`/`280` modes exist and use other, non-shipped models).
3. Exact age-model output layout (class count, logits vs probabilities).
4. Whether the extra model is always added or only for landmark/eye effects.
5. Which AB settings enable the TT detector by default in this build (AB values
   are server-side).

## 9. Evidence index

| path | content |
|---|---|
| `evidence/hashes.txt` | sha256 of models + AI libs, trill vs musically |
| `evidence/variant_diff.txt` | ZIP member/CRC diff of the two variants |
| `evidence/model_structure.txt` | record tables produced by `reimpl/model.py` |
| `evidence/payload_blocks.txt` | descriptor-block size vs payload, entropy split per record |
| `evidence/libeffect_face_strings.txt` | libeffect.so model/face strings with offsets |
| `evidence/parse_v10.err` | empty stderr of the parser run (kept for audit) |
| `native/bytenn_loader_decompiled.c` | Ghidra: BM loader, md5, disassembleModels |
| `native/libeffect_string_xref_68b360.s` | ADRP/ADD xref to "Extract model name: slowimage fail" |
| `native/bytenn_cipher_42800.s` | bytenn XOR+inv-sbox model cipher loop (tested negative) |
| `native/bytenn_subst_table.bin` | AES inverse S-box used by that cipher |
| `native/libeffect.so.range_68ab00_68b100.s` | surrounding smash-face loader code |
| `native/*.elf_info.txt` | ELF facts for the 7 AI libraries |
| `ir/*.model.json` | parsed container per model |
| `sources/` | decompiled classes + `index.md` |


---

## 10. Video-frame interface (follow-up, confirmed)

The DNN is fed a **GL texture**, one per processed frame, not a video file or a
YUV buffer.

```
VESDK VERecorder/VECameraCapture (libttvesdk.so)
  -> BEFEffectNative.nativeProcessFrame(handle, int, int, width, height, timestamp)
  -> libeffect.so: bef_effect_set_width_height(handle, width, height)
                    bef_effect_process_texture(handle, ..., timestamp)
  -> Effect/algorithm graph (FaceDetectTaskTT, FaceAttributesTask)
  -> VEFaceDetectInfo (106 landmarks, rect, score, pose, eye distance,
     action, trackCount, faceID, visibility, iris/eye/lip/eyebrow points)
  -> Java callbacks (onFaceDetectResultCallback) and GL effect nodes
     (FaceReshape_V2, beauty, makeup) -> preview/encoder
```

Evidence: `sources/classes/BEFEffectNative.java`,
`sources/classes/VEFaceDetectInfo.java`,
`native/libeffect_nativeProcessFrame_2eac68.s`,
`native/libeffect_nativeProcessAlgorithm_2eabd4.s`,
`native/libeffect_video_exports.txt`, `evidence/video_interface.md`.
The detection-only path (`nativeProcessAlgorithm`) calls
`bef_effect_algorithm_multi_texture_with_params`. Orientation and camera facing
are supplied separately by `nativeSetFrameOrientation` and
`nativeSetCameraPosition`. The first two int arguments of `nativeProcessFrame`
were unresolved at the time of that note; they are **resolved in §11** below
(input/output GL texture ids). Exact DNN input resize, frame cadence defaults,
and the classical tracker remain inferred/unknown.

---

## 11. Frame pipeline patch (data flow, confirmed)

Full contract in `PIPELINE.md` (9 sections); raw evidence in
`evidence/frame_pipeline_native.md`; machine-readable form in
`findings.json → data_pipelines`.

### 11.1 The two ints of `nativeProcessFrame` — resolved

Register analysis of `Java_..._nativeProcessFrame` @0x2eac68 (0x40 bytes):
`x5/x6` are width/height (moved into `w1/w2` of
`bef_effect_set_width_height`), while `x3/x4` are stashed by an outlined
register-shuttle at 0xaf58d4 (`mov w19,w4 ; mov w20,w3`) and restored at
0xa9f91c (`mov w1,w20 ; mov w2,w19`) immediately before the tail call
`bef_effect_process_texture(handle, i1, i2, timestamp)` (addendum §1).
`bef_effect_process_texture` @0x3097f0 only zeroes the loading-timeout argument
and branches to `bef_effect_process_texture_with_loading_timeout`, whose log
format `"%s timeStamp=%f resourceLoadingTimeoutUs=%d"` (0xc9ef2c) fixes the
remaining parameters.

The Java caller resolves the semantics (new `evidence/scripts/dex_callers.py`
DEX invoke scan → `evidence/beffectnative_callers.txt`):
`com.ss.android.ugc.aweme.view.customView.NaviGLSurfaceView.onDrawFrame`
(classes6.dex, pc 514/578) calls

```java
BEFEffectNative.processFrame(handle, inTextureId, outTextureId, width, height,
                             System.nanoTime() / 1.0E9);
```

with `inTextureId = C36620EXe.LJJJJJL[0]` (the camera frame rendered into FBO
`LJJJJJ`) and `outTextureId = C36620EXe.LJJJIL[0]` (effect result).
`C36620EXe.LIZJ()` creates every texture as `GL_TEXTURE_2D` (3553), internal
format `GL_RGBA` (6408), type `GL_UNSIGNED_BYTE` (5121), `GL_LINEAR`, each with
its own FBO — **texture format, width/height and timestamp clock are now
confirmed for this path** (`NaviGLSurfaceView.java:245/265`,
`C36620EXe.java:167-290`). Rotation and camera facing are one-shot setters
(`setOrientation 0`, `setFrameOrientation 0`, `setCameraPosition true`, where
native inverts the flag: `cset w1,eq` at 0x2ead34 before
`bef_effect_set_camera_device_position`). OES input remains unknown for the
VESDK recorder entry, which is native (`libttvesdk.so`).

### 11.2 What the models actually receive

`FaceDetectTaskTT::process` @0x458d08 does not touch the texture: it reads an
image descriptor from the task and calls `FS_DoPredict` @0x68bc04
(`bl` at 0x458e7c) with a **CPU buffer**, channels 3 (type 3, RGB888,
stride `3*w`) or 4 (types 0–2), rejecting type 4/5 with
`"Not support gray!"`/`"Not support yuv!"` (0x458e08/0x458e24).
`FS_DoPredict` enforces `channels <= 3` at 0x68bc90.

### 11.3 Preprocessing — negative result and remaining gap

The detector's own preprocessing was **not** recovered. What is now excluded:
the two uint8→float32 loops that sit next to `Thrustor::SetInput`/`Inference`
belong to **GAN modules**, not to `FS_DoPredict`:

* `f = u8·2⁻⁷ − 1` at 0x9a0100 is inside `FUN_0099f8b0` (0x99f8b0, 2764 B),
  whose body resolves the layer name `"up_0_bright_high"`, and is followed by
  `Thrustor::SetInput` at 0x9a017c;
* `f = (u8/255 − mean[c]) / scale[c]` at 0x9e4fb0 is inside `FUN_009e4a90`
  (0x9e4a90, 16096 B), whose body contains `"LaughGanRet does not init."`
  (0xd98ffb, `smash/modules/laugh_gan`), and is followed by
  `"thrustor_->Inference: Backbone Cost time %ld"` + `Inference`.

Both were obtained from the address-driven Ghidra run
(`native/libeffect_smash_decompiled.c`, script `native/ghidra_scripts/DecompileAt.java`).

What the face path does instead: `FS_DoPredict` validates the image format
through `0x68fc4c` (same 0–5 enum as the task) and calls the detector driver
`0x9cf2d4`, which picks a geometry planner (`0x9cf6d8` portrait / `0x9cf7ac`
landscape). The planner **is** the resize: it scales so the shorter side equals
the network input size, keeps the aspect ratio, rounds the other extent up to a
multiple of an alignment field, and stores the scale (`+0x34`/`+0x38`) for
back-mapping (`native/libeffect_detect_infer_decompiled.c`). The numeric net
extent and alignment come from the model/config structs at runtime
(`net[+8]`/`net[+0xc]`), so they stay **unknown**, as does the detector's
u8→f32 normalisation (no such loop in the recovered path). Face-box crop helpers
(`GetSquareBox`, `expand box`, 0x6a2bec/0x6a2aa8) are separate. `PIPELINE.md` §3/§8 tracks this as an open question with the exact
functions to decompile next.

### 11.4 Invocation, cadence, threads

One `espresso::Thrustor` per submodel (`_Znwm(0x10)` → `C1` → `CreateNet` at
0x697cc0–0x697ce8), submodels located by name search (`base120` 0x696268,
`base120det` 0x696334, `pout` 0x6963d0, `iris` 0x69641c, `eyecls` 0x696470,
`partface` 0x696544, `extra` 0x6964d4, `slowimage` 0x6965d8, `mean` 0x68a6a8),
per-frame `SetInput` → `Inference` → `Extract`/`getOutput`
(0x9a017c→0x9a0188→0x9a0198; 0x69e2c8→0x69e2d4). `setThreads(4)` is
hard-coded (0x9b2584). Cadence is per-task and config-driven:
`bef_face_detect_ext_param_st{dectectIntervalTime, useFastModel, imageMode,
maxFaceNum}` (0xcc7112–0xcc734e), JSON keys `skip_frame`/`base_skip_frames`/
`framerate`/`face_detect_interval`, `ShouldGenerateTask`, plus AB
`enable_fastmode_faceDetect240` (the fast model
`tt_face_extra_fast_v13.0.model` is **not shipped**).

### 11.5 Output mapping

Postprocess is a dequantise+permute loop (0x697ab4–0x697b88, index table at
`.rodata` 0x12a2bf0) and a 19/47-class threshold helper with `0.5`/`0.125`
thresholds (0x69f740–0x69f858). Results land in `algorithm_result_face_st` /
`algorithm_result_face_attrs_st`, are bridged to
`com.ss.android.medialib.model.FaceDetect{,Info,FaceExtInfo}` and then to
`VEFaceDetectInfo{PVJ[]}`: `rect, score, 106 points, 106 visibility, yaw,
pitch, roll, eyeDistance, faceID, action, trackCount, PVI` where `PVI` carries
eyes (22+22), eyebrows (13+13), lips (64) and iris (20+20) point sets
(`sources/classes/PVJ.java`, `X.PVI.java`, unchanged `VEFaceDetectInfo.java`).
Consumers: GL overlay `X.EHf`/`X.EHg`, rect extraction `La.LIZ`, attribute
callback `X.EHr.onResult`, and the shipped effects — `FaceReshape_V2`
landmark indices `pos 23/19/13/105` with `requirement.faceDetect=true`, while
the beauty effects set `faceDetect=false`.

Per-record tensor attribution (`pout`/`base120det`/`partface`/`eyecls`/`agenet`)
stays **inferred**; `PIPELINE.md` §5/§8 lists what would settle each item.

### 11.6 New artifacts (this patch)

| path | content |
|---|---|
| `PIPELINE.md` | 9-section data-pipeline contract |
| `evidence/frame_pipeline_native.md` | frame trace, normalisation sites, Thrustor call map |
| `evidence/beffectnative_callers.txt` | every DEX invoke of `BEFEffectNative` (caller class/method/pc) |
| `evidence/thrustor_calls.txt` | all 206 `espresso::Thrustor` `bl`/`b` sites + PLT map |
| `evidence/thrustor_got_refs.txt` | GOT/PLT sanity check for `SetInput`/`setThreads` |
| `evidence/algorithm_xrefs.txt` | xrefs for `faceDetect240/280/Du`, `FaceDetectMaxFaceNum`, crop helpers |
| `evidence/tensor_name_xrefs.txt` | xrefs for `pout`, `fc6_prob`, `fc_landmark_240`, `c1_det`, `detector` |
| `evidence/smash_thrustor_697980.s`, `evidence/setinput_9a0100.s`, `evidence/inference_9e4f80.s` | raw disassembly of the inference/normalisation sites |
| `evidence/scripts/xref_scan.py`, `calls_to.py`, `dex_callers.py`, `reach.py` | reusable scanners (ADRP xrefs, PLT call sites, DEX invoke callers, direct-BL call-graph reachability) |
| `evidence/callgraph_reach.txt` | direct-call reachability from `FS_DoPredict` to the backbone/normalisation region |
| `native/ghidra_scripts/DecompileAt.java` | address-driven Ghidra decompiler script used for `libeffect_smash_decompiled.c` |
| `native/libeffect_fs_decompiled.c` | Ghidra: `nativeProcessFrame`/`nativeProcessAlgorithm`/`process_texture_with_loading_timeout` + `FS_DoPredict` (0x68bc04) and its smash callees |
| `native/libeffect_preprocess_decompiled.c` | Ghidra: the format dispatchers `0x68fc4c`/`0x68fd30`/`0x68ead4`/`0x68fbe4` in front of the detector |
| `native/libeffect_detector_decompiled.c` | Ghidra: `0x9cf2d4`, the smash detector driver called right after those helpers |
| `native/libeffect_detect_infer_decompiled.c` | Ghidra: the two inference branches `0x9cf6d8` / `0x9cf7ac` |
| `native/ghidra_scripts/DecompileAt.java` | address-driven decompiler script (Ghidra imagebase is 0x100000, so Ghidra prints ELF vaddr + 0x100000) |
| `sources/classes/NaviGLSurfaceView.java`, `C36620EXe.java`, `PVJ.java`, `X.PVI.java`, `com.ss.android.medialib.model.FaceDetect{,Info}.java`, `com.bef.effectsdk.view.ViewControllerInterface.java`, `ModelnamesAssigner.java`, `RequirementResourceMapper.java`, `RequirementDefine.java`, `com.ss.android.ttve.nativePort.TEEffectUtils.java` | new decompiled classes |
