# `tiktok_musically` — data-pipeline integration contract

Package `com.zhiliaoapp.musically`, version 24.2.0 (`2022402000`), APK sha256
`3a488bed7ae1b2da18a947161a94a312ae64ad01137339774d37d5847909f2f9`.
Native binaries `libeffect.so` (`dad4778a…e76c35`) and `libttvesdk.so`
(`e38abe25…a6ce74`) and all three model bundles are **byte-identical to the `trill`
variant**, so every address below applies to both.

Evidence files are relative to this directory. Frame interface details:
`evidence/video_interface.md`; per-frame call sites:
`evidence/vesdk_drawframe_callsites.txt`; strings:
`evidence/libeffect_face_video_strings.txt`.

## 1. Data sources / triggers

| # | trigger | exact location | confidence |
|---|---|---|---|
| 1 | Camera preview / recorded clip / imported media rendered by VESDK | `libttvesdk.so` render function tagged by its own logs `DrawFrame:LoadResource` @0x5cb5e5, `DrawFrame:bef_effect_algorithm` @0x5cb62f, `[%d] bef_effect_process_texture >>>` @0x5cb6b4; frame loop 0x1cf4f0–0x1cfa60 | confirmed |
| 2 | Effect handle / resource finder / sticker (effect) load | `Java_com_bef_effectsdk_BEFEffectNative_nativeCreateHandle` @0x2ea99c, `nativeInitResourceFinder` @0x2eaa10, `nativeSetStickerPath` @0x2eab8c, `bef_effect_create_handle` @0x304a44; `…ModelnamesAssigner_nativeSetAssignedModelNames` @0x2ed484 | confirmed |
| 3 | Face-detection task activation | string `FaceDetectTaskTT.cpp` @0xd02a5c, class symbol `N3BEF16FaceDetectTaskTTE` @0xdc6877; AB gate `enable_fastmode_faceDetect240` @0xd64cbf + `bef_effect_use_TT_facedetect` @0x100f7 | strings confirmed, AB semantics inferred |
| 4 | Face-attribute task activation | `N3BEF18FaceAttributesTaskE` @0xdde608, `N3BEF23FaceAttributesAlgorithmE` @0xdde524, `…registerAlgorithm…` @0xdde544 | confirmed (symbols) |
| 5 | Model assets made available to the SDK | `com.bef.effectsdk.ModelsList.<clinit>` (classes2) lists the three `assets/model/…` paths; `com.bef.effectsdk.EffectSDKUtils` copies them to the app model dir | confirmed (report §2) |

Lifecycle: handle created once per VESDK session; per frame the render loop calls
`bef_effect_set_width_height` + a texture entry point; teardown via
`nativeDestroyHandle` @0x2ea9f8.

Java entry `BEFEffectNative.processFrame(long,int,int,int,int,double)` is called by
`com.ss.android.ugc.aweme.view.customView.NaviGLSurfaceView.onDrawFrame`
(`sources/video/NaviGLSurfaceView.java:233,245,265`), on the GL render thread. The
earlier "no class in the APK calls it" was a `dex_find --regex processFrame` artifact:
the call site is inside `onDrawFrame`, not a method named `processFrame`. The recorder
path is native `libttvesdk.so` → `libeffect.so` (`NEEDED`); the Navi preview path is the
confirmed Java caller.

## 2. Transport and format

| item | value | evidence | confidence |
|---|---|---|---|
| video payload | GL texture, **GL_TEXTURE_2D / RGBA8888 / UNSIGNED_BYTE** | `glTexImage2D(target=0xde1, internalformat=0x1908, format=0x1908, type=0x1401)` in `libttvesdk.so` 0x4b7314–0x4b7330 (thunk 0x55a760 → PLT 0x19fca0 → GOT `0x8c2d10`=`glTexImage2D`) | confirmed |
| texture id | int, first field of the per-input frame descriptor (`+0x00`, stride `0x48`) | `libttvesdk.so` 0x1cf944 `ldr w1,[x23]` | confirmed |
| width / height | ints at `+0x14` / `+0x18` of the same descriptor; on the JNI path they are Java args 4 and 5 | 0x1cf540 `ldp w1,w2,[x23,#0x14]`; `native/libeffect_musically_nativeProcessFrame.s` (0x2eac74/0x2eac80) | confirmed |
| timestamp | `double`, descriptor `+0x20`; JNI arg 6 | `ldr d0,[x23,#0x20]` @0x1cf554; JNI `d0` in 0x2eac84 | confirmed as double; **clock origin unknown** (frame PTS seconds assumed) |
| rotation | separate setter, degrees int | `nativeSetFrameOrientation` @0x2ead50 → `bef_effect_set_frame_device_orientation`; `nativeSetOrientation` @0x2ead44 → `bef_effect_set_orientation` | confirmed; value encoding (0/90/180/270) inferred |
| camera facing | separate setter, boolean, inverted on the way down | `nativeSetCameraPosition` @0x2ead0c → `bef_effect_set_camera_device_position(handle, !z)` (`tst w19,#0xff; cset w1,eq`) | confirmed |
| CPU fallback path | decoded frame uploaded to a `GL_TEXTURE_2D` then handed to `bef_effect_process_texture` | `libttvesdk.so` 0x4b72f0–0x4b7350 | confirmed |
| YUV / GRAY | rejected by every DNN task | `"FaceDetectTaskTT: Not support yuv/ gray!"` @0xd02ab1/0xd02adf, `"FaceAttributesTask: …"`, `"ExpressionDetectTask: …"` @0xd25120 | confirmed |
| thread | GL/render thread of the VESDK filter chain (the same thread that issues `glTexImage2D`) | call sites in one function; no worker thread hand-off observed | inferred |
| drop / skip policy | per-frame; SDK has `skipFrame`/`skipFrameAll`/fps counters | report §3 strings | inferred |

JNI argument layout (resolved, was `unknown` in the trill report):

```
processFrame(handle, texId, arg2, width, height, timestamp)
  -> bef_effect_set_width_height(handle, width, height)
  -> bef_effect_process_texture(handle, texId, arg2, timestamp)   # arg2 forwarded, unread by the wrapper
```

`bef_effect_process_texture` @0x3097f0 is a thunk adding `loadingTimeout = 0` and calling
`bef_effect_process_texture_with_loading_timeout`. `arg2` semantics remain **unknown**
(it is neither read in 0x3097f8–0x3098bc nor named by any string).

## 3. Preprocessing

| step | implementation | status |
|---|---|---|
| frame → detector input | texture sampled/cropped inside `BEF::FaceDetectTaskTT`; the task refuses YUV/GRAY, so the crop is taken from the GL texture | confirmed (task rejects non-texture; texture is the only input) |
| resize target | `base120` / `base120det` record names suggest 120×120; fast mode has `faceDetect240`/`280` variants (`enable_fastmode_faceDetect240` @0xd64cbf) | inferred |
| normalisation constants | the `mean` record (1905 float32 monotone pairs) is anchor/prior geometry, not image means (report §4.1) | confirmed negative; true scale/bias unknown |
| face-attribute crop | aligned face crop, size unknown. The `bgr448` assertion claimed in report §6 is **not present** in this `libeffect.so` (`grep 'bgr448|448'` → no hit); only `image format only support RGBA8888 or GRAY8` @0xca2276 exists | unknown |
| Java-side preprocessing | none — JNI passes handle + ints + double only | confirmed |
| whether the ops match the model contract | cannot be checked: the per-record payload descriptor schema is still unparsed (report §5) | unknown |

## 4. Model invocation

| item | value | confidence |
|---|---|---|
| runtime | ByteNN "espresso" (`espresso::Thrustor::CreateNet/SetInput/Inference/Extract/SkipLayer/DefaultInSize`) imported from `libbytenn.so`; `libeffect.so` never parses BM containers itself | confirmed (import table, report §5) |
| model files | `assets/model/ttfacemodel/tt_face_v10.0.model` (905 491 B, `175210db…`), `…/tt_face_extra_v13.0.model` (573 858 B, `d0f4e9f9…`), `assets/model/ttfaceattrmodel/tt_face_attribute_age_v1.1.model` (203 028 B, `8efeb7f2…`) | confirmed |
| sub-model records | `tt_face_v10.0`: detect, base120, base120det, pout, eyecls, partface, image, slowimage, mean; `tt_face_extra_v13.0`: iris, extra; `tt_face_attribute_age_v1.1`: agenet | confirmed (report §4) |
| model path source | asset paths from `ModelsList` → `EffectSDKUtils` copy → SDK resource finder | confirmed |
| tensor shapes / dtypes | **unknown** — descriptor block unparsed; `Tensor` supports NCHW/NHWC × Float/Fp16/Int8 (generic ByteNN API, not necessarily these bundles) | unknown |
| binding of the preprocessed buffer | espresso `SetInput(name, ptr, w, h, c)` with a raw buffer; textures are sampled inside the effect SDK before that call | inferred |
| batching / threads | one frame at a time; no batching observed | inferred |
| cadence | once per rendered frame, subject to SDK skip/drop paths | inferred |
| generic ByteNN path (app ML SDK, not these models) | `com.ss.bytenn.API` → `libbytennwrapper.so` → `libbytenn.so` | confirmed (report §2) |

## 5. Output postprocessing

Face result structs are published through the SDK event system:
`BES::NtfEvent<algorithm_result_face_st>` @0xdbe58d and
`BES::NtfEvent<algorithm_result_face_attrs_st>` @0xdbe232; string keys
`algorithm_result_face_st` @0xca414b and `algorithm_result_face_attrs_st` @0xca40e9.
Delivery to Java is a parcel read by `VEFaceDetectInfo.readFromParcel()`
(`X.C34719DjF` wrapper) or a direct conversion from `com.ss.android.medialib.model.FaceDetectInfo`.

`VEFaceDetectInfo` / `X.C64698PZc` fields (all confirmed from `covert()`):
`Rect rect`, `float score`, `PointF[106] points`, `float[106] pointVisibility`,
`float yaw`, `float pitch`, `float roll`, `float eyeDistance`,
`int faceID`, `int action`, `int trackCount`, and ext info `X.C64697PZb`
(mask bits: 1 = eyes 22 pts each, 2 = eyebrows 13, 4 = lips 64, 8 = iris 20 each —
read in that order in `C64697PZb.LIZ()`).

`VEFaceAttributeInfo` / `VEFaceAttribute`: `age` first, then `boyProb, attractive,
happyScore, expType(int), expProbs(float[7]), realFaceProb, quality, arousal, valence,
sadScore, angryScore, surpriseScore, maskProb, wearHatProb, mustacheProb, lipstickProb,
wearGlassProb, wearSunglassProb, blurScore, illumination`. Only `age` (`agenet` record)
has a shipped model; the rest stay at 0.

Public getters: `bef_effect_get_face_detect_result` @0x30b794,
`bef_effect_get_face_attrs_detect_result` @0x2f2248,
`bef_effect_get_expression_detect_result` @0x30b974.

Decode details that remain **unknown**: NMS/thresholds (not visible in Java, not
recovered from native), coordinate transform back to source space (the mesh struct
carries `view_width`/`view_height`, strings @0xca8405/0xca8461, implying a view-space
transform exists), temporal smoothing (classical tracker is in the effect SDK and was
not decompiled).

## 6. Consumer / action

| consumer | evidence | confidence |
|---|---|---|
| effect graph nodes (face reshape / beauty / makeup / stickers) | `BEF::FaceReshapeFeature`, `assets/ttlive_FaceReshape_V2.zip`, `ttlive_reshape_composer.zip`, `ttlive_beauty_composer.zip` (report §3) | assets + symbols confirmed, dependency on the DNN inferred |
| Java face-info API | `VEFaceDetectInfo.covert(FaceDetectInfo)`; referenced from classes8.dex / classes10.dex (recompiled classes not identified — obfuscated) | confirmed that these dexes reference it; consumer class unknown |
| face-attribute events | `handleFaceAttributeEvent` @0xca4550, `handleFaceAgeEvent` @0xca453d, `handleFaceInfoEvent` @0xca4465, `handleFaceFivePartEvent` @0xca4439, `handleFaceBeautyScoreEvent` @0xca4522 | confirmed (strings) |
| preview surface / encoder | VESDK filter chain: `DrawFrame` → effect → `Frames`/`Filters` (`vesdk::pub::Filters::set_bef_effect` @0x2eb830) → output surface | inferred (the frame path terminates in the VESDK output stage; exact encoder node not traced) |

User-visible effect: landmarks/pose drive face reshaping and beauty; `age`/other
attribute scores drive age/beauty effects; results are not uploaded to the network from
the AI path (no network calls observed in this path).

## 7. Failure and fallback

| condition | behaviour | evidence | confidence |
|---|---|---|---|
| model missing / not copied | task logs `FaceDetectTaskTT: Error!!! No detector !!!!!` @0xd02a24 and returns no result; no fallback detector ships in the APK | strings | confirmed |
| result container missing | `Error!!! No result container !!!!!` @0xd02a71 | string | confirmed |
| empty frame | `Image data is null!` @0xd23801 | string | confirmed |
| CPU pixel format | `Not support yuv!` / `Not support gray!` @0xd02ab1/@0xd02adf — silently no result | strings | confirmed |
| null handle | `bef_effect_process_texture_with_loading_timeout` returns `-5`; missing config → `-6` (0x309894/0x30989c) | disasm | confirmed |
| AB flag off | `enable_fastmode_faceDetect240` not set (`AB value of {…} not exist` @0xd60eef) → non-fast path / TT detector disabled | strings | inferred |
| asset update | `EffectSDKUtils.needUpdate()` compares `AssetManager.open().available()` and re-copies | report §2 | confirmed |

## 8. Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| frame is a GL_TEXTURE_2D RGBA8888 texture | confirmed | libttvesdk 0x4b7314–0x4b7330 | done |
| width/height are Java args 4/5, timestamp is the double | confirmed | `native/libeffect_musically_nativeProcessFrame.s` | done |
| args 2/3 are forwarded to `bef_effect_process_texture` in order | confirmed | thunks 0xaf58d4 / 0xa9f91c | done |
| meaning of forwarded `arg2`/`arg3` | input/output GL texture IDs (trill Ghidra trace; this APK's libeffect is byte-identical) | trill `report.md` §11.1/11.3, trill `PIPELINE.md`; sha256 equality | done |
| preview path is `NaviGLSurfaceView.onDrawFrame` → `BEFEffectNative.processFrame`; recorder path is native `libttvesdk` → `libeffect` | confirmed / inferred | `sources/video/NaviGLSurfaceView.java:233,245,265`; `NEEDED libeffect.so` | done |
| detector input is 120×120 | inferred | `base120` record name | payload descriptor parse |
| attribute crop size | unknown | no `448`/`bgr448` string in libeffect | descriptor parse or a runtime trace |
| 106-point layout / field order | confirmed | `X.C64698PZc`, `VEFaceDetectInfo.covert` | done |
| NMS / thresholds / smoothing | unknown | not present in Java; native tracker not decompiled | Ghidra on `BEF::FaceDetectTaskTT` methods |

## 9. Reimplementation mapping

| stage | JAX module | contract | status |
|---|---|---|---|
| container parse | `reimpl/model_container.py` (`ModelPackage.parse`) | real: returns per-record name / descriptor size / weight region for all 12 records | real code |
| detector | `reimpl/pipeline.py` `face_detector` | in `(1,120,120,3)` float32 (BGR, `(x-127.5)/128`), out `pout (1,30,30,1)`, `box (1,30,30,4)` | ARCHITECTURE_ONLY |
| landmark / eye refinement | `reimpl/pipeline.py` `extra_model` | in `(1,120,120,3)`, out `iris (1,30,30,2)`, `eyecls (1,2)`, `partface (1,30,30,5)` | ARCHITECTURE_ONLY |
| age attribute | `reimpl/pipeline.py` `age_model` | in `(1,448,448,3)`, out `(1,1)` | ARCHITECTURE_ONLY (448 is a guess; not corroborated) |
| video front-end | **not reimplemented** | would need: RGBA8 texture → RGB, crop by previous frame / tile, resize to the model input, NCHW float | out of scope of the current reimpl |

All four networks are placeholders; only the container reader and the decoded 1905
float32 `mean` values are real. A numerical reimplementation is blocked on the
per-record payload descriptor schema (report §5), which lives only in the stripped
`libeffect.so` smash-face loader. The video glue (texture handling, w/h, orientation,
camera facing, timestamp) is now fully documented and is not a blocker.
