# Frame → face-model pipeline inside `libeffect.so` (native evidence)

Follow-up for gaps 1–4 of the patch ticket. All addresses are `libeffect.so`
virtual addresses (== file offsets; single RX PT_LOAD at vaddr 0, see
`readelf -lW`). Additions here are new; the earlier evidence files
(`video_interface.md`, `libeffect_nativeProcessFrame_2eac68.s`, …) are untouched.

## Reproduce

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
T=$WS/tools
cd $WS/reports/tiktok_trill

# symbol/PLT/call-site maps
$PY evidence/scripts/xref_scan.py native/libeffect.so 0xd66180 0xd66161 0xd65e7b ...
$PY evidence/scripts/calls_to.py  native/libeffect.so SetInput Inference Extract CreateNet getOutput
# raw disassembly of the sites quoted below
$PY $T/disasm_elf.py native/libeffect.so --addr 0x2eac68 --size 0x40 -o -
$PY $T/disasm_elf.py native/libeffect.so --addr 0x458d5c --size 0x130 -o -
$PY $T/disasm_elf.py native/libeffect.so --addr 0x9a0100 --size 0xc0 -o -
$PY $T/disasm_elf.py native/libeffect.so --addr 0x9e4f80 --size 0x1a0 -o -
# strings (file offsets == vaddr in .rodata)
strings -a -t x native/libeffect.so | grep -iE 'smash|base120|espresso'
```

Helper scripts added with this patch (mine, under `evidence/scripts/`):
`xref_scan.py` (ADRP+ADD / ADRP+LDR code xrefs to a vaddr, linear sweep,
~1 s over the 17 MB .text) and `calls_to.py` (PLT stub → imported symbol
mapping from `.rela.plt` + stub decoding, then all `bl`/`b` sites).

## 1. The two unresolved ints of `nativeProcessFrame` (confirmed + inferred)

JNI register map of `Java_..._nativeProcessFrame` @ **0x2eac68** (size 0x40):

| Java arg | register | use |
|---|---|---|
| `long handle` | x2 | moved to x21; later x0 for both C calls |
| `int i1` | x3 | saved to w20 by the outlined block @0xaf58d4 |
| `int i2` | x4 | saved to w19 by the same block |
| `int i3` | x5 | `w1` of `bef_effect_set_width_height` ⇒ **width** |
| `int i4` | x6 | `w2` of `bef_effect_set_width_height` ⇒ **height** |
| `double d` | d0 | saved in v8, restored into v0 for the tail call |

Confirmed instruction trail:

```
0x2eac74 mov  w1, w5            ; width
0x2eac78 mov  x21, x2           ; handle
0x2eac7c mov  x0, x2
0x2eac80 mov  w2, w6            ; height
0x2eac88 bl   0xaf58d4          ; -> "mov w19,w4 ; mov w20,w3 ; ret" (x4=i2, x3=i1)
0x2eac8c bl   bef_effect_set_width_height@plt   ; (handle, width, height)
0x2eac90 bl   0xa9f91c          ; -> "mov x0,x21 ; mov w1,w20 ; mov w2,w19 ; ret"
0x2eac9c mov  v0.16b, v8.16b    ; timestamp
0x2eaca4 b    bef_effect_process_texture@plt    ; (handle, i1, i2, d0)
```

So `i1`/`i2` are **not** dimensions (those are x5/x6) — they are forwarded
verbatim as argument 2 and 3 of `bef_effect_process_texture(handle, i1, i2,
timestamp)`. `bef_effect_process_texture` @0x3097f0 is an 8-byte thunk:

```
0x3097f0 mov w3, wzr                     ; resourceLoadingTimeoutUs = 0
0x3097f4 b   bef_effect_process_texture_with_loading_timeout@plt
```

and that function's own log format string is
`"%s [%s %d] %s timeStamp=%f resourceLoadingTimeoutUs=%d"` (**confirmed**,
`.rodata` 0xc9ef2c, loaded at 0x309844/0x30984c), which fixes the tail of the
signature as `(… , int resourceLoadingTimeoutUs, double timeStamp)`. The
`w3`-th argument is therefore the loading timeout and `d0` the timestamp
(**confirmed**). `bef_effect_set_width_height` @0x30738c sets the render size
separately from the texture arguments (**confirmed** by the call above).

Interpretation — now **confirmed** from the Java caller (see §1b):
`i1` = input GL texture id (the camera frame rendered into an off-screen FBO),
`i2` = output GL texture id (the effect result), `i3`/`i4` = width/height,
`d` = `System.nanoTime()/1e9` (seconds).

### 1b. Java caller (confirmed): `NaviGLSurfaceView.onDrawFrame`

`evidence/beffectnative_callers.txt` (new `evidence/scripts/dex_callers.py`,
DEX invoke scan) lists every caller of `BEFEffectNative`; the only frame-path
caller is `com.ss.android.ugc.aweme.view.customView.NaviGLSurfaceView`
(classes6.dex), method `onDrawFrame(GL10)`, bytecode pc 452 (`processAlgorithm`),
514 and 578 (`processFrame`). Source: `sources/classes/NaviGLSurfaceView.java`
lines 219–266, render context `sources/classes/C36620EXe.java`.

```java
// vertical branch (C36620EXe.LJIILLIIL == true), NaviGLSurfaceView.java:245
BEFEffectNative.processFrame(handle,
        c36620EXe.LJJJJJL[0],   // i1 = scaled camera-frame texture (input)
        c36620EXe.LJJJIL[0],    // i2 = effect output texture
        c36620EXe.LJII,         // i3 = width  = 270 * h / 480
        c36620EXe.LJIIIIZZ,     // i4 = height
        d);                     // d  = System.nanoTime() / 1.0E9
// horizontal branch, line 265: (handle, LJJJJI[0] or LJJJ[0], LJJJIL[0], LJFF, LJI, d)
```

Texture roles are fixed by `C36620EXe.LIZJ()` (lines 167–290): every texture is
`glGenTextures` + `glTexImage2D(GL_TEXTURE_2D=3553, 0, GL_RGBA=6408, w, h, 0,
GL_RGBA, GL_UNSIGNED_BYTE=5121, null)` with `GL_LINEAR` min/mag, and each is
attached to its own FBO (`glFramebufferTexture2D`). `LJJJJJ` is the FBO whose
colour attachment is texture `LJJJJJL` (input), `LJJJI` ↔ `LJJJIL` (output),
`LJJJJIZL` ↔ `LJJJLIIL` (256×256 preview). Just before the effect call the
GL thread renders the camera `SurfaceTexture` frame (`this.LJIIIZ[0]`) into
that input FBO (`NaviGLSurfaceView.java:186–200`), then passes it to
`processAlgorithm`/`processFrame`.

Lifecycle/setters, `onSurfaceCreated` (NaviGLSurfaceView.java:683–688):

```java
handle = BEFEffectNative.createHandle(this.LJIIIIZZ == 3);
BEFEffectNative.initResourceFinder(handle, finder, 270, 480, "android");
BEFEffectNative.setOrientation(handle, 0);
BEFEffectNative.setFrameOrientation(handle, 0);
BEFEffectNative.setCameraPosition(handle, true);
```

`nativeSetCameraPosition` @0x2ead0c inverts the flag before calling the C API:
`tst w19,#0xff ; cset w1,eq ; b bef_effect_set_camera_device_position`, i.e.
Java `z` → native `!z` (**confirmed**); in this path the value is hard-coded,
so camera facing never changes at runtime (**confirmed**).

Also confirmed: the sibling detection-only entry
`nativeProcessAlgorithm` @0x2eabd4 packs its three ints into a stack parameter
block (`stp w21, w20, [sp] ; str w19, [sp, #8]`, 0x2eac38–0x2eac3c) and calls
`bef_effect_algorithm_multi_texture_with_params` @0x2eac40, i.e. it too takes
GL textures, not YUV.

## 2. What reaches the face detector: a CPU RGB(A) buffer (confirmed)

`FaceDetectTaskTT::process` (the function that owns the log strings
"Not support yuv!" / "Not support gray!") at **0x458d08** switches on an image
type field at `[task+0x44]` and calls the smash FaceSDK:

```
0x458d4c ldr  w9, [x19, #0x44]      ; image type enum
0x458d50 ldr  w8, [x19, #0x30]      ; image width
0x458d5c cmp  w9, #5 ; b.eq 0x458dfc ; -> 0xd02ab1 "FaceDetectTaskTT: Not support yuv!"
0x458d68 cmp  w9, #4 ; b.eq 0x458e18 ; -> 0xd02adf "FaceDetectTaskTT: Not support gray!"
0x458d70 cmp  w9, #3 ; b.ne 0x458e34
0x458d78 add  w22, w8, w8, lsl #1    ; 3 * width  (RGB888 row bytes)
0x458d7c mov  w20, #3                ; channels = 3
0x458e34 mov  w20, wzr               ; other types -> channels = 0 (net default)
   (default path, 0x458d60) lsl w22, w8, #2  ; 4 * width -> RGBA8888
0x458e54 ldr  x8, [x19, #0x6b38]     ; option/flags word
0x458e64 orr  x10, x8, #0x300000
0x458e6c csel x7, x8, x10, eq        ; flags for the call
0x458e70 mov  w2, w20                ; channels
0x458e74 mov  w5, w22                ; row bytes (stride)
0x458e78 str  x21, [sp]              ; 0x6a94-byte result struct, memset earlier
0x458e7c bl   0x68bc04               ; FS_DoPredict (= exported bef_fs_doPredict tail)
```

`FS_DoPredict` @**0x68bc04** validates its arguments itself: `w2` (channels)
must be ≤ 3 (`cmp w2,#3 ; b.hi` → error −0x6e at 0x68bc90), the handle and the
image pointer must be non-null (−0x6c), and the flags word `x7` is checked
against magic values 0x666/0x888/0x777 stored in the handle
(0x68bd2c–0x68bd4c). It then walks `[handle+0x238]` entries clearing bits in
`[handle+0x248]`.

**Consequence (confirmed by the above):** the DNNs do not read the GL texture
directly. A CPU image buffer (`bef_algorithm_image_st`: data ptr, width, height,
type) is produced upstream in the effect graph and handed to the smash face
detector as RGB888 (3 ch) or RGBA8888 (4 ch). YUV and gray inputs are rejected
with explicit log lines.

## 3. Preprocessing — what was found, and why it is not the detector's

Two uint8→float32 loops exist next to engine calls. **Both are in GAN
functions**, which was established from the Ghidra decompilation of their
containing functions (`native/libeffect_smash_decompiled.c`, produced by
`native/ghidra_scripts/DecompileAt.java`, address-driven):

**(a) `f = u8/128 − 1`** — 0x9a010c–0x9a0140, inside `FUN_0099f8b0`
(ELF 0x99f8b0, 2764 B; Ghidra prints `FUN_00a9f8b0` because it uses imagebase
0x100000). The function resolves the layer/tensor name `"up_0_bright_high"`
(seen six times in its body) and calls `Thrustor::SetInput` at 0x9a017c.

```
0x9a0114 mov  w10, #0x3c000000      ; 2^-7 = 0.0078125
0x9a0118 fmov s0, #-1.0
0x9a0134 ucvtf s1, s1               ; one unsigned byte
0x9a0138 fmul s1, s1, s2            ; * 1/128
0x9a013c fadd s1, s1, s0            ; - 1.0
0x9a0140 str  s1, [x11, x8, lsl #2]
```

**(b) `f = (u8/255 − mean[c]) / scale[c]`** — 0x9e4fb0–0x9e5090, inside
`FUN_009e4a90` (ELF 0x9e4a90, 16096 B), whose body contains the literal
`"LaughGanRet does not init."` (0xd98ffb) — the `smash/modules/laugh_gan`
module. The same function logs
`"thrustor_->Inference: Backbone Cost time %ld"` (0xd99f6c, xref 0x9e50d0) and
calls `Thrustor::Inference` at 0x9e50b8.

```
0x9e4fb0 mov  w4, #0x437f0000       ; 255.0
0x9e5020 ldr  x15, [x27, #0x40] ; add x15, x15, #0x134   ; mean[3]/scale[3]
0x9e5068 ldur s2, [x2, #-0xc]       ; mean[c]
0x9e506c ldr  s3, [x2], #4          ; scale[c]
0x9e5074 ucvtf s0, s0
0x9e5078 fdiv s0, s0, s1            ; /255
0x9e507c fsub s0, s0, s2            ; - mean[c]
0x9e5080 fdiv s0, s0, s3            ; / scale[c]
0x9e5088 str  s0, [x14, w1, sxtw #2]
```

**The face detector's preprocessing is elsewhere and remains unknown.**
`FS_DoPredict` (FUN_0078bc04 in the same decompilation) does:

```
iVar9 = FUN_0078fc4c(&local_398 /*plan*/, channels, this + 0x170);
if (iVar9 == 0 && [this+0x17c] == w && [this+0x178] == h) {   /* cached extent */
    FUN_0078fd30(this + 0x268, plan, param_7, plan);
    FUN_00acf2d4(...)          /* = ELF 0x9cf2d4, the detector proper */
}
```

`0x68fc4c` / `0x68fd30` / `0x68ead4` are unnamed and statically linked with
mobilecv2 (`cv::resize` stripped), so the resize geometry and any normalisation
inside the detector path could not be read. Strings that do exist belong to the
*face-box* crop stage: `"invalid expand box"` (0xd6661d, xref 0x6a2aa8) and
`"invalid init_box_local in GetSquareBox"` (0xd66631, xref 0x6a2bec).

Also confirmed in `FS_DoPredict` (this is the per-frame cadence logic, §5):

```
iVar21 = ++[this+0x1f0];                    /* attempted frames without result */
frameStep = (iVar21 < 10) ? 2 : (iVar21 < 0x1e ? 4 : interval/3);
if (frameCount % frameStep == 0) run_detector();
```

with the configured interval at `[this+0x1e8]` (`plVar13[0x3d]`).

### 3a. The resize IS recovered: aspect-preserving letterbox with stride alignment

Ghidra pass #3 (`native/libeffect_detect_infer_decompiled.c`) decompiled the two
branches the detector driver takes (`[info+0x10] == 1` → `0x9cf6d8`, else
`0x9cf7ac`), and both are the **input-geometry planner**:

```
/* 0x9cf6d8 (portrait: net_w < net_h) */
nw = info[0x1c]; nh = info[0x20]; target = info[0x18]; align = info[8];
s  = (float)target / (float)nw;
H  = (int)((float)nh * s);
info[0x34] = s;                       /* scale back to source coords        */
info[0x2c] = target; info[0x30] = H;  /* unaligned destination extent       */
info[0x24] = target;
info[0x28] = H rounded to a multiple of `align` (nearest, half up);
   /* q = H / align; r = H - q*align; H' = (r <= align/2) ? q*align : (q+1)*align */
/* 0x9cf7ac is the same with the roles of the two extents swapped
   (landscape: scale = target / nh, W = nw * s, info[0x38] = s)              */
```

So: the frame is scaled so that the **shorter** side equals the network input
size (`info[0x18]`), the other side keeps the aspect ratio and is then padded /
aligned up to a multiple of `info[8]`, and the scale factor is stored
(`+0x34` / `+0x38`) for mapping detections back. No numeric value is hard-coded:
`info[0x1c]`/`info[0x20]` come from the net via `0xbecec4(*(void**)obj)`
(`*(info+0x20) = *(net+8)`, `*(info+0x1c) = *(net+0xc)` in `0x9cf2d4`), i.e. the
espresso input tensor dims, and `info[0x18]`/`info[8]` are configuration fields
of the same struct. **The concrete numbers therefore still have to come from the
model descriptor** (unknown), but the *algorithm* is confirmed.

No u8→f32 conversion appears in these two planners, so the detector's
normalisation remains **unknown** as well.

### 3b. The helpers `FS_DoPredict` uses in front of the detector

Address-driven Ghidra pass #2 (`native/libeffect_preprocess_decompiled.c`) shows
these are **format dispatchers**, not resizers:

| helper (ELF vaddr) | size | what it does |
|---|---:|---|
| `0x68fc4c` | 228 B | validates an `(imageVersion, imageFormat)` pair: `(0,5)`, `(2,3)`, `(2,2)`, `(3,0)`, `(3,1)` are accepted, anything else returns −1 — the same 0–5 format enum that `FaceDetectTaskTT` switches on |
| `0x68fd30` | 104 B | jump table on the format (`param_3 < 4` → 4 cases), i.e. per-format image construction |
| `0x68ead4` | 124 B | geometry setup; special-cases `(format|1) == 3` and `format == 5` |
| `0x68fbe4` | 104 B | clears the result vectors (pairs at +0x330…+0x480) before a predict |
| `0x6a2aa8/0x6a2bec` | — | face-box crop (`GetSquareBox`, `expand box`) |

So the resize/normalisation is inside the detector itself (`0x9cf2d4`, called
right after these helpers), which is where the trace stops statically.

## 4. Model invocation (confirmed)

`libeffect.so` imports `espresso::Thrustor` from `libbytenn.so`
(`nm -D --undefined-only`): `C1/D1`, `CreateNet`, `SkipLayer`, `SetInput`,
`Inference`, `Extract`, `getOutput`, `ReInferShape`, `setThreads`,
`ThrustorDefaultInSize`, `ThrustorEnforceCPURuntime`, `VerifyNetParamters`.
PLT stub addresses and every `bl`/`b` site are in `evidence/thrustor_calls.txt`
(206 sites). Smash-region sites:

| symbol | PLT | call sites |
|---|---|---|
| `CreateNet(name, void* model, vector<string>&)` | 0x2c7050 | smash 0x697ce8 (`b` tail); builder 0x697c7c |
| `Extract(name)` | 0x2c80f0 | smash 0x697a60, 0x6a0e28 |
| `Inference()` | 0x2c8100 | smash 0x697a90, 0x69e2c8, 0x69eb8c, 0x6a48ac, 0x6a4b70, 0x6a4bf0, 0x6a5270 |
| `getOutput()` | 0x2c8110 | smash 0x69e2d4 |
| `ReInferShape(w,h)` | 0x2c8130 | smash 0x6a5238 |
| `SkipLayer(name)` | 0x2c80e0 | smash 0x696bb0 |
| `setThreads(int)` | 0x2c8fd0 | 0x9b2588 (`mov w1,#4` ⇒ 4 threads), tail 0xac1894 |
| `ThrustorDefaultInSize` | 0x2c8f70 | 0x973f6c, 0x974884, 0x982464, 0x9a0998 |
| `ThrustorEnforceCPURuntime` | 0x2c8fa0 | 0x99bc1c |
| `VerifyNetParamters()` | 0x2c8150 | no call site in this build |
| `SetInput(name, ptr, i, i, i)` | 0x2c8fc0 | 0x9a017c, 0xa66378, 0xa668a4, 0xa68aa0, 0xa73ec0 |

(The first `calls_to.py` run used `| tee … | head -60`; SIGPIPE truncated that
file at 199 lines and hid the `SetInput` sites. `evidence/thrustor_calls.txt`
now holds the complete 206-line scan.)

Net construction (0x697c7c): `operator new(0x10)` → `Thrustor::C1` → store into
the wrapper object → tail-call `CreateNet(name /*x1*/, model_ptr /*x2*/,
vector<string>& /*x3 = this+0x20*/)`. Each submodel of the smash bundle gets its
own `Thrustor` instance; the `vector<string>` (built by two `push_back`s at
0x697ca8/0x697cb4) is the espresso I/O-name list.

Input binding (0x9a0158–0x9a017c): `SetInput(name /*stack string*/,
data = [obj+0x58], int = 3*[obj+0x10] << 2, int = [obj+8], int = [obj+0xc])`.
The first int is `12 × [obj+0x10]`, i.e. a 3-channel float row/plane byte count;
the other two are the image extents held in the wrapper object. Input dims for
`base120` therefore come from the wrapper object, **not** from a literal 120 in
code — the `120` exists only as the submodel name.

Submodel names are resolved by *name search inside the model buffer*, not by a
length table (confirmed by the error strings "Extract model name: slowimage
fail" 0xd65e80, "…imagedetect fail." 0xd65ea4, "…detect fail" 0xd65ecb). Xrefs
of each name string to the resolver code:

| submodel string | vaddr | xref |
|---|---|---|
| `base120` | 0xd66161 | 0x696268 |
| `base120det` | 0xd66169 | 0x696334 |
| `iris` | 0xd66174 | 0x69641c |
| `eyecls` | 0xd66179 | 0x696470 |
| `partface` | 0xd6619a | 0x696544 |
| `extra` | 0xd6618a | 0x6964d4 |
| `slowimage` | 0xd66180 | 0x6965d8, 0x68b354 |
| `pout` | 0xd85571 | 0x6963d0 |
| `agenet` | 0xd66e11 | 0x6b49e0, 0x2fbd40, 0x2fbd98 |
| `mean` | 0xd65e7b | 0x520d18, 0x68a6a8, 0xb79ab0 |

Postprocessing seen in the smash region: a **max-index/threshold** routine at
0x69f740 (`cmp w8,#0x13`=19, `cmp w8,#0x2f`=47, `fmov s1,#0.125`,
`fmov s1,#0.5`, then `fcsel`/`fcmp` and a state write to `[obj+0x70]`) and a
**float scale + index-permute** loop at 0x697ab4–0x697b88: it computes
`(len/2)` from the four tensor dims at `[sp+8..0x1c]`, multiplies each element
by a scale in `s0` and stores it through an `int32` index table based at
`.rodata` 0x12a2bf0 (`add x11,#0x12a2000 ; add x11,x11,#0x550` + `ldrsw
[x12,#0x6a0]`). This is the dequant/NHWC↔NCHW step that turns an espresso output
blob into the app-side face layout.

## 5. Cadence, skip/drop, threads (confirmed strings, inferred wiring)

* `bef_face_detect_ext_param_st` fields (introspection strings at 0xcc7112):
  `maxFaceNum`, `useFastModel`, `imageMode`, `dectectIntervalTime` (sic) —
  i.e. the detector is asked to run at an interval and can switch to the fast
  model. Getter/setter symbols listed at 0xcc719f–0xcc734e.
* `useFastModel` selects the fast path logged as
  "--faceDetectAlgorithm: use fast-mode faceDetect240" (0xd02952) vs
  "do not use fast-mode faceDetect240" (0xd029d4), driven by AB key
  `enable_fastmode_faceDetect240` (0xd64cbf; AB log 0xd60e89/0xd60eef).
  The fast extra model file name is `ttfacemodel/tt_face_extra_fast_v13.0.model`
  (0xd02b34) — **not shipped** in this APK.
* Algorithm registry names `faceDetect240` (0xd28af3), `faceDetect280`
  (0xd28b01), `faceDetectDu` (0xd28b20) are referenced from a registration site
  (0x324df0/0x324e14/0x324ec0) and a lookup site (0x52ae64/0x52ae8c/0x52aef8);
  `FaceDetectMaxFaceNum` (0xd61710) is parsed in `FaceDetectAlgorithmTT`
  (0x457ca8/0x457ce8) and again at 0x635d20.
* Effect-config JSON keys understood by the effect graph: `skip_frame`
  (0xd6886b), `base_skip_frames` (0xd8e02c), `framerate` (0xd8e243),
  `face_detect_interval` (0xd91818), `face_mask_framerate` (0xd9431e),
  `hand_refresh_frame_interval` (0xd9195c), `foot_detection_interval`
  (0xd91e26). Generic gate `ShouldGenerateTask` (0xd02822, next to the
  FaceReshape/FaceDetect parser strings). Skip counters are logged as
  "AEAlgorithm…: skipFrame %d / skipFrameAll %d" (0xd92cb7, 0xd92cd9,
  0xd949d8).
* Threads: `espresso::Thrustor::setThreads(int)` is imported (PLT 0x2c8230)
  and called from 0x9b2588 (and tail 0xac1894); `ThrustorEnforceCPURuntime`
  from 0x99bc1c. No per-frame thread creation in the face path; libeffect
  itself uses pthread pools (`pthread_create`, `std::thread` imported).

## 6. Output structs

* `algorithm_result_face_st` (0xca414b), `algorithm_result_face_attrs_st`
  (0xca40e9), `algorithm_result_face_mesh_st` (0xca8360) and their
  introspection getters are present; `algorithm_result_st` carries `m_type`
  (0xca73cc) and `m_timeStamp` (0xca73d3).
* Landmark arity check in the face code: `landmarks.size() == 106 && !eye_lv2 …
  || landmarks.size() == 240 || landmarks.size() == 280` (0xd44a69), plus
  `landmarks.size() == 180` / `>= 180` (0xd44b04/0xd44b2d) — same geometry
  variants as the `faceDetect240/280` algorithm names.
* Java side (unchanged, see `sources/classes/VEFaceDetectInfo.java`): per-face
  `PVJ` = rect, score, 106 points + 106 visibility, yaw/pitch/roll, eye
  distance, action, faceID, trackCount, optional `PVI` extension
  (eye/brow/lip/iris point sets).

## Boundary

Not recovered: the exact `mean[]`/`scale[]` values, which submodel consumes
which normalisation, the resize geometry (letterbox vs square crop) of the
detector input, whether the camera texture reaching `processFrame` is OES or
2D at the *VESDK recorder* entry (the AR/Navi caller uses 2D; see `PIPELINE.md`
§2), and the `VEFaceDetectInfo` → tensor mapping (record names give the
candidate sets and the postprocessing code gives the decode shape, not the
per-field provenance).

## 7. Output-side Java classes (confirmed)

`sources/classes/PVJ.java`, `X.PVI.java`, `com.ss.android.medialib.model.FaceDetect.java`,
`VEFaceDetectInfo.java` (`readFromParcel` order = wire order):

| # | `PVJ` field | type | meaning (`FaceDetect` getter) |
|---|---|---|---|
| 1 | `LIZ` | `Rect` | `getRect()` — face box, 4 ints |
| 2 | `LIZIZ` | float | `getScore()` |
| 3 | `LIZJ` | `PointF[]` (`LIZ(106)`) | `getPoints()` — 106 landmarks |
| 4 | `LIZLLL` | `float[]` (`LIZIZ(106)`) | `getPointVisibility()` — per-point visibility |
| 5 | `LJ` | float | `getYaw()` |
| 6 | `LJFF` | float | `getPitch()` |
| 7 | `LJI` | float | `getRoll()` |
| 8 | `LJII` | float | `getEyeDistance()` |
| 9 | `LJIIIIZZ` | int | `getFaceID()` |
| 10 | `LJIIIZ` | int | `getAction()` |
| 11 | `LJIIJ` | int | `getTrackCount()` |
| 12 | `LJIIJJI` | `PVI` | extension set, present iff `faceExtFlag != 0` |

`PVI.LIZ()` decodes the extension by flag bits: bit0 eyes (`count`, 22 + 22
points), bit1 eyebrows (`count`, 13 + 13), bit2 lips (`count`, 64 points),
bit3 iris (`count`, 20 + 20) — `X.PVI.java` lines 24–45.

Java consumers of `VEFaceDetectInfo` (`evidence/beffectnative_callers.txt`):
`X.EHf.LIZ(VEFaceAttributeInfo, VEFaceDetectInfo)` and `X.EHg.LIZ(...)`
(per-face overlay/landmark drawing), `X.EHr.onResult(FaceAttributeInfo,
FaceDetectInfo)` → `VEFaceDetectInfo.covert(...)`, and `La.LIZ(VEFaceDetectInfo)
→ Rect[]` (box extraction).
