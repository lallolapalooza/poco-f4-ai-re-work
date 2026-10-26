# `musically` — DNN ↔ video interface (follow-up)

Scope: verify the video-frame interface facts that were established for the `trill`
variant (`reports/tiktok_trill/evidence/video_interface.md`) against **this** APK, and
resolve the parts trill left open. Everything below is tied to a file in this report
directory or to a reproducible command.

## 0. Variant check first

| artifact | musically sha256 | trill sha256 | verdict |
|---|---|---|---|
| `lib/arm64-v8a/libeffect.so` | `dad4778a98808a2614a4e70d1a7753d4b3dfca8e988a1c52b65a7d4241e76c35` | `dad4778a…e76c35` | **byte-identical** |
| `lib/arm64-v8a/libttvesdk.so` | `e38abe25719145bb5fdf11729003615bce09f374d04c4e3d0e9c400665a6ce74` | `e38abe25…a6ce74` | **byte-identical** |
| 3 face model bundles | `8efeb7f2…`, `d0f4e9f9…`, `175210db…` | same (`evidence/hashes.txt`) | **byte-identical** |

Consequences: every native address quoted below is valid for both variants, so the
frame path is identical. What differs is only the **Java obfuscation map** (§4).

Commands:

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
T=$WS/tools
APK="/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/cust/app/customized/partner-com.zhiliaoapp.musically_100/partner-com.zhiliaoapp.musically_100.apk"
unzip -p "$APK" lib/arm64-v8a/libeffect.so   > native/libeffect.so
unzip -p "$APK" lib/arm64-v8a/libttvesdk.so  > native/libttvesdk.so
sha256sum native/libeffect.so native/libttvesdk.so
$T/jadx/bin/jadx --single-class com.bef.effectsdk.BEFEffectNative \
    --single-class-output sources/video/com.bef.effectsdk.BEFEffectNative.java --show-bad-code --no-res "$APK"
$T/jadx/bin/jadx --single-class com.ss.android.vesdk.faceinfo.VEFaceDetectInfo \
    --single-class-output sources/video/com.ss.android.vesdk.faceinfo.VEFaceDetectInfo.java --show-bad-code --no-res "$APK"
$T/disasm_elf.py native/libeffect.so --addr 0x2eac68 --size 0x40 -o -
$PY evidence/xref_scan.py native/libttvesdk.so --sym bef_effect_process_texture
```

## 1. Confirmed: what enters the effect SDK each frame

* Java-visible binding (`classes2.dex`):
  `public static native int nativeProcessFrame(long, int, int, int, int, double)` and
  `public static int processFrame(long, int, int, int, int, double)`.
  Source: `sources/video/com.bef.effectsdk.BEFEffectNative.java:21,108`.
* The frame is a **GL texture**, not YUV and not a file:
  `native/libeffect_musically_nativeProcessFrame.s` (0x2eac68) sets `w1=w5`, `w2=w6`
  (= the two *last* ints = width/height) and calls
  `bef_effect_set_width_height`, then tail-calls `bef_effect_process_texture`.
* The DNN tasks themselves refuse CPU pixel formats:
  `"FaceDetectTaskTT: Not support yuv!"` @0xd02ab1, `"Not support gray!"` @0xd02adf,
  `"FaceAttributesTask: Image data is null!"` @0xd250ed,
  `"ExpressionDetectTask: Not support yuv!"` @0xd25120
  (`evidence/libeffect_face_video_strings.txt`).
* Texture upload format in the VESDK decode/playback branch is **GL_TEXTURE_2D,
  RGBA8, UNSIGNED_BYTE**: libttvesdk 0x4b7314–0x4b7330 sets
  `target=0xde1 (GL_TEXTURE_2D)`, `internalformat=0x1908 (GL_RGBA)`,
  `w,h` from the frame struct, `border=0`, `format=0x1908`, `type=0x1401`
  and calls `glTexImage2D` (thunk 0x55a760 → PLT stub 0x19fca0 → GOT slot
  `0x8c2d10` = `glTexImage2D`; `evidence/vesdk_drawframe_callsites.txt`).

## 2. Confirmed: the first two int arguments (resolved; trill had this as unknown)

JNI static-method register mapping on aarch64:
`x0=*env, x1=jclass, x2=jlong handle, x3=int#1, x4=int#2, x5=int#3, x6=int#4, d0=double`.

`nativeProcessFrame` does exactly two register-shuffle thunks:

```
0x2eac74  mov w1, w5                 ; set_width_height arg2 = int#3
0x2eac80  mov w2, w6                 ; set_width_height arg3 = int#4
0x2eac88  bl  0xaf58d4               ; thunk: mov w19,w4 ; mov w20,w3 ; ret
0x2eac8c  bl  bef_effect_set_width_height
0x2eac90  bl  0xa9f91c               ; thunk: mov x0,x21 ; mov w1,w20 ; mov w2,w19 ; ret
0x2eaca4  b   bef_effect_process_texture
```

* `0xaf58d4` = `mov w19,w4; mov w20,w3; ret` → saves **int#2 → w19**, **int#1 → w20**.
* `0xa9f91c` = `mov x0,x21(handle); mov w1,w20; mov w2,w19; ret`.

Therefore, in Java terms:

```
processFrame(handle, texId, arg2, width, height, timestamp)
                 │       │      │       │
                 │       │      │       └─ bef_effect_set_width_height(handle, width, height)
                 │       └──────┴───────── bef_effect_process_texture(handle, texId, arg2, timestamp)
```

So **int#1 and int#2 are not frame flags that are dropped — they are forwarded, in
order, as arguments 2 and 3 of `bef_effect_process_texture`**. `bef_effect_process_texture`
itself is a 4-byte thunk: `mov w3,wzr; b bef_effect_process_texture_with_loading_timeout`
(0x3097f0), i.e. it adds `loadingTimeout = 0` and passes the double through `d0`.

The same call shape is visible in the real VESDK render loop, which is where the app
actually feeds frames (see §3): `w1 = [frame_struct+0x00]` (texture id) and
`w2 = [other_struct+0x00]` (opaque int) at 0x1cf944/0x1cf948.

Residual unknown: the semantic of argument 3 (`arg2`). It is not read by the
`process_texture` wrapper body (`w2` never appears in 0x3097f8–0x3098bc); it is only
described in the VESDK caller as a field of a second structure. `unknown` — most likely
a texture-type/flag field, but there is no string or xref that names it.

## 3. Confirmed: the per-frame path the app really uses

No Java code in this APK calls `BEFEffectNative.processFrame`:
`$T/dex_find.py "$APK" --regex 'processFrame'` returns the declaration only
(`com.bef.effectsdk.BEFEffectNative.processFrame (JIIIID)I`), no method reference from
any other class. The same holds for `trill` (`classes2.dex` only). The Java class is the
public SDK surface; the camera/editor pipeline calls the same C entry points from
**`libttvesdk.so`**, which links `libeffect.so` directly
(`readelf -d native/libttvesdk.so` → `NEEDED libeffect.so`).

Inside `libttvesdk.so` one large render function tagged by its own log strings
(`DrawFrame:LoadResource` @0x5cb5e5, `DrawFrame:bef_effect_algorithm` @0x5cb62f,
`[%d] bef_effect_process_texture >>>` @0x5cb6b4) contains the frame loop; PLT
call-site scan (`evidence/vesdk_drawframe_callsites.txt`):

| call | address |
|---|---|
| `bef_effect_set_width_height` | 0x1cf548 (camera/preview), 0x411f74, 0x412024, 0x413f4c, 0x554420 |
| `bef_effect_algorithm_multi_texture_with_params` | 0x1cf740 (detect-only branch) |
| `bef_effect_process_textures_with_native_buffers` | 0x1cf81c |
| `bef_effect_process_texture` | 0x1cf950 (texture branch), 0x4b7350 (uploaded-buffer branch) |
| `bef_effect_process_texture_mimo` | 0x1cf9bc |
| `bef_effect_process_texture_with_loading_timeout` | 0x413228 |

The texture branch at 0x1cf944 reads its per-input frame descriptor with stride `0x48`:

```
0x1cf540  ldp w1, w2, [x23,#0x14]   ; width  = +0x14, height = +0x18  -> set_width_height
0x1cf944  ldr w1, [x23]             ; texture id = +0x00
0x1cf948  ldr w2, [x22]             ; opaque int (see §2)
0x1cf94c  bl  0x55ece8              ; resolve handle (returns x0, time in d0)
0x1cf950  bl  bef_effect_process_texture
```

and the frame struct also carries a **double timestamp at +0x20**
(`ldr d0,[x23,#0x20]` at 0x1cf554, feeding `scvtf`/`fdiv` for fps bookkeeping).

## 4. Confirmed: rotation and camera facing are separate setters

| JNI (libeffect, identical in both variants) | forwards to |
|---|---|
| `nativeSetFrameOrientation(long,int)` 0x2ead50 | `bef_effect_set_frame_device_orientation(handle, degrees)` |
| `nativeSetOrientation(long,int)` 0x2ead44 | `bef_effect_set_orientation(handle, degrees)` |
| `nativeSetCameraPosition(long,boolean)` 0x2ead0c | `bef_effect_set_orientation(handle,0)` → `bef_effect_set_frame_device_orientation(handle,…)` → `bef_effect_set_camera_device_position(handle, !z)` (`tst w19,#0xff; cset w1,eq` → the Java boolean is inverted on the way down) |

Evidence: `native/libeffect_musically_nativeSetFrameOrientation.s`,
`native/libeffect_musically_nativeSetCameraPosition.s`,
`native/libeffect_musically_nativeSetOrientation.s`, `evidence/libeffect_video_exports.txt`.

Detection-only path `nativeProcessAlgorithm(long,int,int,int,double)` 0x2eabd4:
`int#1` unused, `int#2/#3` → `bef_effect_set_width_height`, then
`bef_effect_algorithm_multi_texture_with_params` with a stack `params` struct
(0x2ebac10–0x2eac40). This matches `bef_effect_algorithm_multi_texture_with_params` at
0x1cf740 in VESDK. Evidence: `native/libeffect_musically_nativeProcessAlgorithm.s`.

## 5. Confirmed: face result struct → Java fields (variant-specific names)

`VEFaceDetectInfo.covert(FaceDetectInfo)` fills `X.C64698PZc`
(trill: `X.PVJ`) in this exact order:

| `X.C64698PZc` field | type | source getter |
|---|---|---|
| `LIZ` | `Rect` | `getRect()` |
| `LIZIZ` | `float` | `getScore()` |
| `LIZJ` | `PointF[106]` | `getPoints()` |
| `LIZLLL` | `float[106]` | `getPointVisibility()` |
| `LJ` | `float` | `getYaw()` |
| `LJFF` | `float` | `getPitch()` |
| `LJI` | `float` | `getRoll()` |
| `LJII` | `float` | `getEyeDistance()` |
| `LJIIIIZZ` | `int` | `getFaceID()` |
| `LJIIIZ` | `int` | `getAction()` |
| `LJIIJ` | `int` | `getTrackCount()` |
| `LJIIJJI` | `X.C64697PZb` | `getFaceExtInfo()` (iris/eye/lip/eyebrow counts + point arrays) |

`readFromParcel()` reads the same 106+106 arrays plus a `faceExtFlag` and then per-face
ext info — i.e. the native side serializes into a `X.C34719DjF` parcel wrapper, and the
two arrays are always 106 entries in both variants
(`sources/video/X.C64698PZc.java`, `…VEFaceDetectInfo.java:28-60`).

Variant difference: only the obfuscation map and the `Covode.recordClassIndex` values.
trill: `PVJ`/`PVI`/`C36340EMk`/`C36453EQt`, index 115413; musically:
`C64698PZc`/`C64697PZb`/`C34719DjF`/`C7Q2`, index 115417. Field order, types and the
`covert()` bodies are identical.

## 6. Attribute side

`VEFaceAttribute` (classes6, `Covode` 115415) exposes 22 floats/ints; `readFromParcel()`
order: `age, boyProb, attractive, happyScore, expType(int), expProbs(float[7]),
realFaceProb, quality, arousal, valence, sadScore, angryScore, surpriseScore, maskProb,
wearHatProb, mustacheProb, lipstickProb, wearGlassProb, wearSunglassProb, blurScore,
illumination`. `age` is field 0 — consistent with the only shipped attribute model
`tt_face_attribute_age_v1.1.model` (`agenet`). The other fields belong to models that
are **named in the SDK but not shipped** (`tt_beauty_attr6_v2.0.model` @0xd25007,
`tt_face_attribute_v7.2.model` @0xcd9f40, `tt_face_attribute_extra_v3.0.model`
@0xd914a5, `tt_face_attribute_exp_v1.0.model` @0xd9288f) — for those the values stay 0.

## 7. Not corroborated in this APK

The main report §6 says libeffect asserts a `bgr448` CV_8UC3 crop for the age model.
`strings -a native/libeffect.so | grep -E 'bgr448|448'` finds no such string; the only
colour-format assertions in the library are
`"image format only support RGBA8888 or GRAY8"` @0xca2276 and the task-level
`Not support yuv/gray` lines. The 448 crop therefore remains **unknown** here (it is
not evidence-backed), and the inputs of the age model cannot be stated more precisely
than "aligned face crop, size unknown".
