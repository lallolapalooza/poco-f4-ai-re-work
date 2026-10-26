# `tiktok_trill` — data-pipeline integration contract

Target: `com.ss.android.ugc.trill` 24.2.0 (240200),
`cust/app/customized/partner-com.ss.android.ugc.trill_101/partner-com.ss.android.ugc.trill_101.apk`,
sha256 `c6e9b326f424800373384076f2490553c25aa29a1b358081e8633b9e6d8b59cc`.

This document covers the **face data path** (one frame, camera → models → render).
Container/weight facts stay in `report.md` §4; the frame-level native evidence is
in `evidence/frame_pipeline_native.md` (new with this patch), the earlier
interface note in `evidence/video_interface.md`.

Confidence tags: **confirmed** = read directly in the artifact (instruction,
string, field); **inferred** = follows from confirmed facts but not observed
end-to-end; **unknown** = searched, not recovered (what was tried is listed).

---

## 0. End-to-end frame trace (acceptance summary)

```
[1] camera SurfaceTexture.updateTexImage()           NaviGLSurfaceView.onDrawFrame, source line 163-169
      │                                              (GL render thread, once per display frame)
[2] render camera texture (this.LJIIIZ[0]) into FBO  C36620EXe.LJJJJJ (colour = tex LJJJJJL),
      │  sized 270*h/480 × h, GL_RGBA8              NaviGLSurfaceView.java:186-200
[3] BEFEffectNative.processFrame(handle, i1, i2,     i1 = LJJJJJL[0] (input texture)
        width, height, System.nanoTime()/1e9)        i2 = LJJJIL[0]  (output texture)
      │                                              NaviGLSurfaceView.java:245 / 265
[4] JNI 0x2eac68: bef_effect_set_width_height(handle, width, height)
      │              bef_effect_process_texture(handle, i1, i2, timestamp)   0x2eac68-0x2eaca4
[5] 0x3097f0 thunk → bef_effect_process_texture_with_loading_timeout(handle, i1, i2, 0, ts)
      │              log fmt "…timeStamp=%f resourceLoadingTimeoutUs=%d" (0xc9ef2c)
[6] effect graph: texture → CPU image buffer (bef_algorithm_image_st)    (per-task, see §2.4)
      │              RGB888 (3 ch) or RGBA8888 (4 ch); YUV / gray rejected
[7] FaceDetectTaskTT::process 0x458d08 → FS_DoPredict 0x68bc04
      │              (handle, data, channels, …, stride=3*w, flags, result*)
[8] smash loader: ModelPackage submodels by name search (base120 0x696268, base120det 0x696334,
      │              pout 0x6963d0, partface 0x696544, eyecls 0x696470, iris 0x69641c,
      │              extra 0x6964d4, slowimage 0x6965d8, mean 0x68a6a8)
[9] preprocessing: letterbox planner 0x9cf6d8 (portrait) / 0x9cf7ac (landscape):
      │              scale so the short side = net input size, aspect kept, other
      │              side aligned; scale cached at +0x34/+0x38 (§3). Numeric net
      │              extent unknown; detector u8→f32 normalisation unknown (§3)
[10] espresso::Thrustor per submodel: CreateNet 0x697ce8, SetInput 0x9a017c,
      │              Inference 0x697a90/0x9e50b8, Extract 0x697a60, getOutput 0x69e2d4
[11] postprocess: dequant scale + index permute (0x697ab4-0x697b88, table 0x12a2bf0),
      │              max-index/threshold 19/47 classes, 0.5/0.125 (0x69f740-0x69f854)
[12] algorithm_result_face_st / algorithm_result_face_attrs_st  (native)
      │              → FaceDetectInfo/FaceDetect/FaceExtInfo (Java model)
[13] VEFaceDetectInfo.covert → VEFaceDetectInfo{PVJ[faceCount]{rect, score, 106 pts,
      │              visibility, yaw/pitch/roll, eyeDistance, faceID, action, trackCount, PVI}}
[14] consumers: effect nodes (FaceReshape_V2/FaceDistortion landmark `pos`), GL overlay
      │              (X.EHf/X.EHg), rect extraction (La.LIZ), face-attribute callback (X.EHr)
[15] effect result texture LJJJIL (or 256×256 LJJJLIIL preview) → drawn to the surface;
                     encoder path only via VESDK record (not the same Java wrapper) — see §6
```

Steps 1–6, 8–13 are **confirmed** at instruction/field level. Step 7's argument
list is confirmed for channels/stride/width; the remaining scalars are read from
a helper block (see §2.4). Steps 14–15: the Java consumers are confirmed; the
record/encoder branch is **inferred** (it runs inside `libttvesdk.so`, which is
outside this APK's AI assets).

---

## 1. Data sources / triggers

| claim | conf | evidence |
|---|---|---|
| Camera frames enter through a `SurfaceTexture`; `updateTexImage()` runs on the GL thread inside `NaviGLSurfaceView.onDrawFrame(GL10)` | confirmed | `sources/classes/NaviGLSurfaceView.java:163-169`; class in classes6.dex |
| The same method calls `BEFEffectNative.processAlgorithm(handle, tex, w, h, ts)` (detection-only) and `processFrame(...)` twice (orientation branches) | confirmed | `NaviGLSurfaceView.java:233, 245, 265`; `evidence/beffectnative_callers.txt` (pc 452/514/578) |
| Effect-graph face detection is gated by the effect package's `requirement.faceDetect` | confirmed | `assets/ttlive_reshape_composer.zip` → `distortion_test/content.json` `"faceDetect": true`; `assets/ttlive_Live_Beauty_12.zip` → `beauty.json` `"faceDetect": false`; parser log string `Parser: requirement parse: needFaceDetect %d, …` at 0xd‑ca1013 |
| Algorithm registry names the detector `faceDetect240` / `faceDetect280` / `faceDetectDu` | confirmed | strings 0xd28af3/0xd28b01/0xd28b20, registered at 0x324df0/0x324e14/0x324ec0, looked up at 0x52ae64/0x52ae8c/0x52aef8 |
| Per-frame task execution is decided by `ShouldGenerateTask` + config keys (`skip_frame`, `base_skip_frames`, `framerate`, `face_detect_interval`) | confirmed (strings) / inferred (wiring) | 0xd02822, 0xd6886b, 0xd8e02c, 0xd8e243, 0xd91818; no shipped asset sets them (scan of `assets/**` for these keys: 0 hits) |
| Detector interval and fast-model switch are per-task parameters (`dectectIntervalTime`, `useFastModel`, `maxFaceNum`, `imageMode`) | confirmed | `bef_face_detect_ext_param_st` introspection strings 0xcc7112-0xcc734e |
| Lifecycle init: `onSurfaceCreated` → `createHandle(cameraType==3)`, `initResourceFinder(h, finder, 270, 480, "android")`, `setOrientation 0`, `setFrameOrientation 0`, `setCameraPosition true` | confirmed | `NaviGLSurfaceView.java:683-688` |
| Teardown: `LIZLLL()` deletes 5 FBOs + 5 textures + buffers; `nativeDestroyHandle` | confirmed | `C36620EXe.java:122-164`; `BEFEffectNative.nativeDestroyHandle` |
| Model provisioning before any of this: `ModelsList.<clinit>` → `EffectSDKUtils.copyAssets()` copies `assets/model/ttfacemodel/*` and `ttfaceattrmodel/*` into the app dir | confirmed | `sources/classes/ModelsList.java`, `EffectSDKUtils.java`; report.md §3.1 |

Trigger granularity: **per rendered frame**, on the GL render thread. There is
no queue between `onDrawFrame` and the effect call — one call per frame, and the
effect call is synchronous (its return value is ignored by this caller).

## 2. Transport and format

### 2.1 GL textures (confirmed)

| property | value | evidence |
|---|---|---|
| target | `GL_TEXTURE_2D` (3553) for the AR/Navi path | `C36620EXe.LIZJ()`: `glBindTexture(3553, …)` ×5 |
| internal format / type | `GL_RGBA` (6408) / `GL_UNSIGNED_BYTE` (5121) | same: `glTexImage2D(3553,0,6408,w,h,0,6408,5121,null)` |
| filtering | `GL_LINEAR` min/mag (9729) | same (`glTexParameteri 10241/10240`) |
| input texture | `LJJJJJL`, size `LJII = 270*h/480` × `LJIIIIZZ = h` | `C36620EXe.java:246-256` |
| output texture | `LJJJIL`, size `LJFF` × `LJI` (preview size set by `setSize`) | `C36620EXe.java:200-222`, `:740-741` |
| other textures | `LJJJ` (w×h), `LJJJJI` (w×h), `LJJJLIIL` (256×256 preview) | `C36620EXe.java:177-199, 223-243, 275-290` |
| per-frame binding | input texture is the colour attachment of FBO `LJJJJJ`; the camera frame is blitted into it each frame | `C36620EXe.java:239` + `NaviGLSurfaceView.java:186-200` |

OES vs 2D: the AR/Navi caller is **2D-only** (confirmed above). The VESDK
recorder/camera-capture path that also reaches `bef_effect_process_texture` is
in `libttvesdk.so`; whether it hands over `GL_TEXTURE_EXTERNAL_OES` (camera
stream) could not be tested here — **unknown**, see §8.

### 2.2 Frame parameters handed to native (confirmed)

| parameter | value at the Java call | native use |
|---|---|---|
| `i1` | `LJJJJJL[0]` (vertical) / `LJJJJI[0]` or `LJJJ[0]` (horizontal) | 2nd arg of `bef_effect_process_texture` = **input texture** |
| `i2` | `LJJJIL[0]` | 3rd arg = **output texture** (effect result) |
| `i3` | `LJII` (vertical: `270*h/480`) / `LJFF` | `bef_effect_set_width_height(handle, i3, i4)` |
| `i4` | `LJIIIIZZ` (vertical: `h`) / `LJI` | same |
| `d` | `System.nanoTime() / 1.0E9` | `timeStamp` (seconds, `CLOCK_MONOTONIC`; **not** the camera `SurfaceTexture` timestamp) |
| orientation | set once to 0 (`setOrientation`, `setFrameOrientation`) | separate C setters |
| camera facing | set once, Java `true` → native `!z` → `bef_effect_set_camera_device_position` | `libeffect_nativeSetCameraPosition_2ead0c.s` |

Rotation/mirror therefore come from those two one-shot setters plus the effect
config, not from per-frame metadata. Mirroring in the display shader is separate
(`C36620EXe.LIZ(...)` uses fixed vertex/UV arrays `LJJJJLI`/`LJJJJLL`).

### 2.3 CPU buffer inside the effect graph (confirmed)

`FaceDetectTaskTT::process` builds the image descriptor from the task's input
image struct and checks its type at `[task+0x44]` (0x458d4c):

| type | channels passed | stride passed | result |
|---|---:|---:|---|
| 3 | 3 | `3*width` | accepted |
| 0–2 (default) | 0 (engine default) | `4*width` | accepted |
| 4 | — | — | rejected: `"FaceDetectTaskTT: Not support gray!"` (0xd02adf) |
| 5 | — | — | rejected: `"FaceDetectTaskTT: Not support yuv!"` (0xd02ab1) |

So the detector consumes **packed RGB888 / RGBA8888** — no YUV, no gray, no
direct texture access. `FS_DoPredict` @0x68bc04 re-validates: channels ≤ 3
(0x68bc90), non-null image and handle (0x68bc2c-0x68bc44), flags word checked
against 0x666/0x888/0x777 (0x68bd2c-0x68bd4c).

### 2.4 Thread / queue

| claim | conf | evidence |
|---|---|---|
| Frame path runs on the GL render thread; no worker queue in the Java caller | confirmed | `NaviGLSurfaceView.onDrawFrame`; no `Handler`/`Executor` on the path |
| Inference threads: `espresso::Thrustor::setThreads(4)` — hard-coded 4 | confirmed | 0x9b2584 `mov w1,#4`; 0x9b2588 `bl setThreads@plt` |
| CPU runtime forced for at least one net (`ThrustorEnforceCPURuntime`) | confirmed | 0x99bc1c |
| Frame drop inside the face path is temporal (interval/fps), not a queue | inferred | `dectectIntervalTime`, `face_detect_interval`, `skip_frame` strings; no ring buffer in the path |

## 3. Preprocessing

Order (confirmed where cited; both the detector resize and the detector
normalisation are still open):

1. **Colour/layout**: packed HWC bytes, 1 byte/channel, no channel swap visible
   in the code that copies the image into a float blob (source pointer advances
   by 1 byte per channel, destination by 4).
2. **Resize to the net input** — algorithm **confirmed**, numbers **unknown**.
   `FS_DoPredict` validates `(imageVersion, imageFormat)` through `0x68fc4c`
   (accepts `(0,5) (2,3) (2,2) (3,0) (3,1)` of the same 0–5 format enum the task
   uses) and calls the detector driver `0x9cf2d4`, which picks one of two
   geometry planners:

   *portrait* (`0x9cf6d8`) `s = netShortSide / net_w; H = net_h * s;`
   *landscape* (`0x9cf7ac`) the same with the extents swapped; then the
   non-target extent is rounded to the nearest multiple of the alignment field
   (`q = H/align; r = H-q*align; H' = r <= align/2 ? q*align : (q+1)*align`), and
   the scale is stored (`+0x34`/`+0x38`) to map detections back to the source
   frame. Evidence: `native/libeffect_detect_infer_decompiled.c`.

   The network extents come from the model at runtime
   (`0x9cf2d4`: `net[+8]`, `net[+0xc]` → planner `info[0x1c]`, `info[0x20]`), so
   the concrete input size (120/240/…) and the alignment value are **unknown**.
   Face-*box* crop helpers are separate: `GetSquareBox` / `expand box`
   (0xd66631 / 0xd6661d, xrefs 0x6a2bec / 0x6a2aa8).
3. **Numeric normalisation of the detector input** — **unknown**. No u8→f32 loop
   appears in the functions recovered from the `FS_DoPredict` path (the detector
   body itself, `0x9cf2d4`, was not decompiled — see §8); the two such loops
   that *are* in `libeffect.so` belong to GAN modules, not to the face detector
   (negative result below).

   Negative result (worth recording): the two u8→f32 normalisation loops found
   next to `Thrustor::SetInput`/`Inference` are in **GAN** functions —

   (a) 0x9a0100 sits in `FUN_0099f8b0` (ELF 0x99f8b0, 2764 B; Ghidra
   `FUN_00a9f8b0`), whose body looks up the tensor/layer name
   `"up_0_bright_high"`; the loop computes `f = u8 * 2⁻⁷ − 1`
   (`mov w10,#0x3c000000`, `fmul`, `fadd −1.0`) and is followed by
   `Thrustor::SetInput` at 0x9a017c;

   (b) 0x9e4fb0 sits in `FUN_009e4a90` (ELF 0x9e4a90, 16096 B; Ghidra
   `FUN_00ae4a90`), whose body contains the literal
   `"LaughGanRet does not init."` (0xd98ffb) — i.e. `smash/modules/laugh_gan`;
   the loop computes `f = (u8/255 − mean[c]) / scale[c]` with mean/scale at
   `[obj+0x40]+0x134` and is followed by `Thrustor::Inference` and the log
   `thrustor_->Inference: Backbone Cost time %ld` (0xd99f6c).

   The container's `mean` record is **not** normalisation data either
   (report.md §4.3: a 1905-float prior/anchor table).
4. **Padding**: none observed.

Contract mismatch risk: the container carries no input extent and the detector's
own normalisation was not recovered, so a reimplementation has to treat both as
free parameters (listed in §8).

## 4. Model invocation

| claim | conf | evidence |
|---|---|---|
| Engine: `espresso` (ByteNN) from `libbytenn.so`, C++ API `espresso::Thrustor` | confirmed | `nm -D --undefined-only libeffect.so`; PLT 0x2c7050/0x2c80e0/0x2c80f0/0x2c8100/0x2c8110/0x2c8130/0x2c8f70/0x2c8fa0/0x2c8fc0/0x2c8fd0 |
| One `Thrustor` per submodel: `operator new(0x10)` → `C1` → `CreateNet(name, modelBuf, vector<string>&)` | confirmed | 0x697cc0-0x697ce8 (`_Znwm@plt`, `ThrustorC1@plt`, tail `CreateNet@plt`); vector built at 0x697ca8/0x697cb4 |
| Model files | `model/ttfacemodel/tt_face_v10.0.model`, `model/ttfacemodel/tt_face_extra_v13.0.model`, `model/ttfaceattrmodel/tt_face_attribute_age_v1.1.model` | confirmed | `ModelsList.java`; native strings 0xd02b0e, 0xd02b34 (fast variant, absent), 0xd9283f |
| Submodels are located by **name search inside the buffer**, then fed to `CreateNet` | confirmed | error strings 0xd65e80/0xd65ea4/0xd65ecb; name xrefs `base120` 0x696268, `base120det` 0x696334, `pout` 0x6963d0, `iris` 0x69641c, `eyecls` 0x696470, `partface` 0x696544, `extra` 0x6964d4, `slowimage` 0x6965d8, `mean` 0x68a6a8 |
| Input binding: `SetInput(name, ptr, int, int, int)`, pointer = engine-side input buffer filled by the normalisation loop | confirmed | 0x9a0160-0x9a017c |
| Per-frame order: SetInput → Inference → Extract(name) / getOutput | confirmed | 0x9a017c → 0x9a0188 → 0x9a0198; 0x69e2c8 → 0x69e2d4 |
| Threads: 4 | confirmed | 0x9b2584-0x9b2588 |
| Cadence: gated per task; `dectectIntervalTime` (ms), `useFastModel`, `maxFaceNum`, `imageMode`; AB key `enable_fastmode_faceDetect240` | confirmed (params) / unknown (defaults) | 0xcc7112-0xcc734e; 0xd60e89; 0xd64cbf |
| Skip/drop: config keys `skip_frame`, `base_skip_frames`, `framerate`, `face_detect_interval`; generic gate `ShouldGenerateTask`; counters logged `skipFrame/skipFrameAll` | confirmed (strings) / inferred (semantics) | 0xd6886b, 0xd8e02c, 0xd8e243, 0xd91818, 0xd02822, 0xd92cb7 |
| Age attribute: `FaceAttributesTask::process` (0x51a5xx) → `FaceAttributesAlgorithm` → `agenet` submodel, `FS_DoAttrPredict`/`FS_DoAttrPredictBatch` | confirmed (strings) / inferred (wiring) | 0xd2505a, 0xd250ed, 0x51a578/0x51a628; `agenet` xref 0x6b49e0 |
| Attribution of records → tensors (which record is the classifier vs the regressor) | inferred | see §5 |

Note: `pout` (7 149 B) and `eyecls` (7 149 B) are by far the smallest records —
consistent with quantised 30×30-ish classification heads; `base120det` and
`partface` (156 KB each) with regression heads; `base120` (363 KB) with the
backbone; `image`/`slowimage` (88 KB/98 KB) with the image-input sub-graphs.
All of this is **inferred** from record sizes + names.

## 5. Output postprocessing

| step | conf | evidence |
|---|---|---|
| Dequantise + layout permute (smash-region net output): each output element `s *= scale` and is stored through an `int32` index table at `.rodata` 0x12a2bf0 (`ldrsw x12,[x12,#0x6a0]`); loop count is `(d0*d1*d2*d3)/2`, i.e. 2-byte → 4-byte conversion (fp16 source) | confirmed (loop/permute) / inferred (fp16) | 0x697ab4-0x697b88 (`mul w10,w25,w23`, `mul …,w24`, `mul …,w27`, `sdiv w10,w10,#2`, `ldur s1,[x9,#-4]`, `fmul s1,s0`, `str s1,[x13,x12,lsl#2]`) |
| Class decision helper: max over ≤0x13 (19) or ≤0x2f (47) classes with thresholds `0.5` and `0.125`, result cached in `[obj+0x70]` | confirmed | 0x69f740-0x69f858 (`cmp w8,#0x13`, `#0x2f`, `fmov s1,#0.125`, `#0.5`, `fcsel`, `fcmp`, `str w8,[x9,#0x70]`) |
| Face result container: `algorithm_result_face_st`, attributes: `algorithm_result_face_attrs_st`, both with `algorithm_result_st{m_type, m_timeStamp}` | confirmed | strings 0xca414b, 0xca40e9, 0xca73bb-0xca7416 |
| Landmark arity is 106 (default), 180, 240 or 280 depending on model/algorithm | confirmed | `landmarks.size() == 106 && … \|\| == 240 \|\| == 280` 0xd44a69; `== 180` 0xd44b04 |
| Coordinate transform back to the source frame is done by the algorithm layer (boxes/points already in image space) | inferred | no scale-back in the task path; `view_width/view_height` fields exist on the mesh result struct (0xca83da/0xca8461) |

Java-side result struct (confirmed, `sources/classes/`):

| `PVJ` field | type | wire order | source getter |
|---|---|---|---|
| `LIZ` | `Rect` | 1 | `FaceDetect.getRect()` |
| `LIZIZ` | float | 2 | `getScore()` |
| `LIZJ` | `PointF[106]` | 3 | `getPoints()` |
| `LIZLLL` | `float[106]` | 4 | `getPointVisibility()` |
| `LJ` / `LJFF` / `LJI` | float | 5/6/7 | `getYaw()` / `getPitch()` / `getRoll()` |
| `LJII` | float | 8 | `getEyeDistance()` |
| `LJIIIIZZ` | int | 9 | `getFaceID()` |
| `LJIIIZ` | int | 10 | `getAction()` |
| `LJIIJ` | int | 11 | `getTrackCount()` |
| `LJIIJJI` | `PVI` | 12 (if `faceExtFlag != 0`) | `FaceExtInfo` |

`PVI` extension flag bits (`X.PVI.LIZ()`): bit0 eyes (count + 22 + 22 pts),
bit1 eyebrows (count + 13 + 13), bit2 lips (count + 64), bit3 iris (count + 20 + 20).

**Tensor → field mapping** (candidate sets, record semantics):
`pout`/`base120det` and `partface` → box + 106-point regression + score;
`eyecls` → `action` (eye-open state) via the 19/47-class helper;
`iris` (extra model) → `PVI` iris points (20+20);
`agenet` → age attribute (separate `VEFaceAttributeInfo`, not in `VEFaceDetectInfo`).
Only the *existence* of these submodels and the shape of the decode are
confirmed; the per-record field attribution is **inferred**.

## 6. Consumer / action

| consumer | what it does | conf | evidence |
|---|---|---|---|
| Effect nodes (`FaceReshape_V2`, `FaceDistortion`) | use landmark indices from `distortion.json` (`pos: 23/19/13/105`, i.e. 106-point space); ship only when `requirement.faceDetect == true` | confirmed | `assets/ttlive_FaceReshape_V2.zip` → `distortion.json`; `ttlive_reshape_composer.zip` → `content.json` |
| Beauty / live-beauty effects | explicitly **do not** request face detection (`"faceDetect": false`) — they use the skin shader chain instead | confirmed | `Live_Beauty_12/beauty.json`, `GESticker_beauty12/content.json` |
| GL overlay | `X.EHf.LIZ(VEFaceAttributeInfo, VEFaceDetectInfo)` / `X.EHg.LIZ(...)` read `getInfo()` per face (landmark drawing) | confirmed | `evidence/beffectnative_callers.txt` (classes10.dex) |
| Box extraction | `La.LIZ(VEFaceDetectInfo) → Rect[]` | confirmed | same file (classes8.dex) |
| Attribute callback | `X.EHr.onResult(FaceAttributeInfo, FaceDetectInfo)` → `VEFaceDetectInfo.covert(...)` — this is the `com.ss.android.medialib` face callback bridged into VESDK types | confirmed | `VEFaceDetectInfo.java:74-116` |
| Render output | effect writes into texture `LJJJIL` (or the 256×256 `LJJJLIIL` preview), then `C36620EXe.LIZ(src, dst, alpha)` draws it to the surface / FBO | confirmed | `NaviGLSurfaceView.java:296-315`; `C36620EXe.java:177-290` |
| Encoder | recording happens through VESDK's native recorder; the Java `BEFEffectNative` wrapper is used by the AR/Navi *preview* view, not by the recorder | inferred | only caller of `processFrame` in dex is `NaviGLSurfaceView`; VESDK recorder is native |

## 7. Failure and fallback

| condition | behaviour | conf | evidence |
|---|---|---|---|
| null handle / null effect in `processFrame` | returns −5 / −6 without touching the graph | confirmed | 0x309894 `mov w19,#-5`, 0x30989c `mov w19,#-6` |
| `FaceDetectTaskTT` without detector / result container / image | logs and returns | confirmed | 0xd02a24, 0xd02a71, 0xd23801 / 0x458d84-0x458dd0 |
| YUV or gray frame reaches the detector | logs "Not support yuv/gray" and skips the frame | confirmed | 0x458dfc/0x458e18 branches |
| missing submodel in the bundle | `"The Model your provide don't contain a submodel: %s"` (detect/avatarDrive), `"Model not loaded!"` | confirmed | 0xd990be, 0xd99103, 0xd666b9 |
| fast mode requested but fast model absent | the fast extra model `ttfacemodel/tt_face_extra_fast_v13.0.model` is **not shipped**; code logs the fast/slow decision from AB `enable_fastmode_faceDetect240` | confirmed (strings) / unknown (actual fallback) | 0xd02b34, 0xd02952, 0xd029d4, 0xd60e89 |
| `Thrustor` creation failure | `"Create Thrustor fails in LoadModelFromBuf"`, `"Thrustor CreateNet fails"`, `"Init model_packager fails"` | confirmed | 0xd66659, 0xd6669f, 0xd66684 |
| espresso output map missing | `"smash espresso prob is null "` → the postprocess returns without writing a face | confirmed | 0xd66525 / 0x69f7c8-0x69f7dc |
| attribute model missing | attribute callback simply not delivered (class still initialises) | inferred | `FaceAttributesTask` guards at 0x51a578/0x51a628 |

No network download path for these three models exists (they are APK assets,
copied by `EffectSDKUtils`); the only "download" surface is the server-side AB
value that selects fast vs normal mode.

## 8. Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| `processFrame(handle, inTex, outTex, w, h, tsSeconds)` | confirmed | 0x2eac68-0x2eaca4 + `NaviGLSurfaceView.java:245/265` + `C36620EXe.LIZJ()` | — |
| Frames reach the DNN as packed RGB888/RGBA8888 CPU buffers | confirmed | `FS_DoPredict` args at 0x458e70-0x458e7c; type switch 0x458d5c-0x458e34 | — |
| The two u8→f32 loops (`/128 − 1`, `(/255 − mean)/scale`) belong to GAN nets, not the face detector | confirmed | containing functions: `FUN_0099f8b0` ("up_0_bright_high"), `FUN_009e4a90` ("LaughGanRet does not init.", 0xd98ffb) | — |
| Detector input resize = aspect-preserving scale to the net input with aligned padding | confirmed (algorithm) | 0x9cf6d8 / 0x9cf7ac, `native/libeffect_detect_infer_decompiled.c` | — |
| Numeric net input extent and alignment value | unknown | read from the model at runtime (`net[+8]`, `net[+0xc]` → planner `info[0x1c]`, `info[0x20]`; alignment `info[8]`, short-side target `info[0x18]`) | breakpoint `Thrustor::SetInput` (dims are its 3 int args) or decode the model descriptor |
| Detector u8→f32 normalisation (mean/scale, if any) | unknown | no such loop in `0x68bc04`, `0x9cf2d4`, `0x9cf6d8`, `0x9cf7ac` | decompile the next level (the nets' input writers) or trace the input buffer |
| Numeric net extent / alignment for `base120`, `image`, `slowimage` (the letterbox algorithm is confirmed) | unknown | planned in 0x9cf6d8/0x9cf7ac from `info[0x18]` (target), `info[8]` (alignment) and `net[+8]`/`net[+0xc]`; nothing is a literal, and `Thrustor::SetInput` takes its dims from a wrapper struct | runtime breakpoint on `SetInput` (dims are its three int args) or decoding the model descriptor |
| Per-frame cadence = every frame vs interval | inferred | `dectectIntervalTime`, `face_detect_interval`, `ShouldGenerateTask`; defaults are server/effect config | runtime log `--face Detect initTimeVal %d` (0xd02879) and the skip counters |
| Threads = 4 for the smash nets | confirmed for the site at 0x9b2584 | 0x9b2584-0x9b2588 | — (unless a second site overrides) |
| Record → tensor/field attribution | inferred | record names + sizes + postprocess shapes | descriptor-block schema (report.md §4.3) |
| OES texture support at the VESDK recorder entry | unknown | AR/Navi caller is 2D-only; recorder is native | `libttvesdk.so` disassembly (out of scope here) |
| Age model output layout | unknown | `agenet` xref 0x6b49e0; no class count recovered | decompile of the `agenet` postprocess |

## 9. Reimplementation mapping

| stage | JAX module | input → output contract | status |
|---|---|---|---|
| container | `reimpl/model.py::ModelPackage.parse` | `.model` bytes → 9/2/1 named records | **real**, verified on all three shipped files (`reimpl/verify.txt`) |
| preprocessing | not implemented yet (`reimpl/pipeline.py::preprocess` would own it) | `u8 HWC [0,255]` → letterbox to the net extent (short side = net size, other side aligned, scale returned for back-mapping) → `f32` with the detector's normalisation (**unknown**; the `/128 − 1` and `(/255 − mean)/scale` forms in the APK belong to GAN nets) | resize algorithm confirmed, numbers unknown |
| detector backbone | `reimpl/model.py::apply` | `(1,120,120,3)` → `pout (1,30,30,1)`, `box (1,30,30,4)` | ARCHITECTURE_ONLY — placeholder topology, random init |
| postprocess | not implemented yet | dequantise ×scale, permute through the int32 table at 0x12a2bf0, argmax with 0.5/0.125 thresholds | ARCHITECTURE_ONLY |
| landmark/extra model | not implemented | 106-point regression + iris/eyecls | ARCHITECTURE_ONLY (no topology) |
| age attribute | not implemented | `agenet` logits → age bucket | ARCHITECTURE_ONLY (class count unknown) |

`ARCHITECTURE_ONLY` because the per-record `[descriptor][weights]` payload has no
public schema and only the stripped `libeffect.so` smash loader parses it; the
weights are plaintext but their tensor shapes/order are described by that
descriptor. The JAX code therefore reproduces the *documented* shapes and the
confirmed normalisation arithmetic, not the shipped network.
