# EAI2 gap closure — container decoded, DNN VAD executed with real weights

Target: `qcom_voiceactivation` (Qualcomm Voice Assist 6.0.10), the 16 `eai2`
blobs under `models/eai/`.  Previous state: front-end real, every DNN stage
random, "EAI2 layer table opaque".

Status: **the container is decoded and one network runs on its real weights,
bit-verified against Qualcomm's own interpreter.**  The 1st/1.5-stage keyword
graphs are fully parsed (shapes, ops, weights, I/O) but their `framer` / `gru`
op semantics are not yet numerically pinned down — the precise blocker is §5.

---

## 1. How the format was recovered

1. `native/libs/libeai_floating.so` (arm64, stripped) still carries a
   `.gnu_debugdata` mini-debug-info section (xz-compressed ELF with a full
   `.symtab`): **425 function names** with addresses.
   ```
   dd if=libeai_floating.so bs=1 skip=$((0x2d0ff)) count=4372 of=dbg.xz
   xz -dc dbg.xz > dbg.so && readelf -sW dbg.so
   aarch64-linux-gnu-objcopy --add-symbol <name>=0x<addr>,global,function ...
   ```
   The symbolized copy is what Ghidra was pointed at, so the decompilation has
   real names instead of `FUN_0010xxxx`.
2. Ghidra headless (`tools/ghidra_decompile.sh`, 3 GB heap) on the symbolized
   DSO → `evidence/ghidra/eai_parse.c`, `eai_tensor_ops.c`, `eai_rnn.c`
   (loader chain, tensor helpers, GEMM/LSTM/GRU/softmax/etc.).
3. `evidence/harness/eai_host.c` — a self-contained arm64 host that mmaps the
   Android DSO, applies its own RELR/RELA relocations and *runs* it under
   `qemu-aarch64`, driving the recovered API:
   `eai_init_ex` → `eai_preapply` → `eai_set_property(0)` (activation pool) →
   `eai_apply` → `eai_execute(handle, &request, 1)`, then dumps every tensor
   (`evidence/native_dumps/`).  This is the oracle for everything below.
   `dlopen` cannot be used: the DSO uses the pre-standard Android `DT_RELR`
   tags (`0x6fffe000/1/3`) and `LIBC` symbol versions, which glibc 2.44 either
   rejects (`needed != NULL`) or crashes on while relocating.
4. `reimpl/eai_loader.py` re-parses the blobs from bytes only;
   `reimpl/verify_eai.py` compares that parse **field by field** against what
   the library itself reported for the same file (5 065 checks, 0 failures).

## 2. Container layout (byte level)

All little endian.  The loader scans the caller buffer at 4-byte steps for the
`eai2` magic (`extract_eai_model_from_buffer`), so a blob may sit inside a
bigger buffer (that is how the `.uim` files embed them).

```
+0x00  char[4]  "eai2"
+0x04  u16 major, u16 minor       format 2.2 in every shipped blob
+0x08  u8 compiler_major, u8 ?, u8 compiler_minor, u8 ?
+0x0c  u32 flags                  bit2 = float model (else "fixed point model,
                                  float runtime" -> load refused)
+0x10  u32 model size            must be <= buffer size
+0x14  u32 scratch bytes         (activation pool; runtime adds alignment 8)
+0x18  u32 persistent bytes      (state pool)
+0x1c  u32 section count
+0x20  u8 target_num, u8 target, u8 ?, u8 parsed   target!=0 -> ENPU model,
                                  refused by the floating runtime
+0x24  u32 reserved (0)
+0x28  section[section_count] { u32 type, u32 size, u32 offset, u32 reserved }
```

Section types (`initialize_eai_model_info` switch, plus `load_network`):

| type | content | consumed by |
|---|---|---|
| 0 | `u32 count, u32 0, u16[count-1]` running index per layer | not read in the paths decompiled |
| 1 | tensor table: `u32 n_tensors, u32 0x20, u32 group1_off, u32 group2_off, u32 n0, u32 n1, u32 n2, u32 0` then `n_tensors` × 0x50-byte descriptors | `initialize_tensor_meta`, `relocate_tensor`, `tensor_size` |
| 2 | constant payload for the group-0 tensors (offsets are relative) | `relocate_tensor(kind=0)` |
| 3 | never handled ("ignore the unrecognized section") | — |
| 4 | `u32 n_layers, u32 0` then variable-size layer records | `calculate_total_layer_size`, `load_eai_layers` |
| 5 | `u32 count, u32 io_off, u32 layer_off[count-1]` — per-layer `u16 n_in, u16 n_out, u16 in[n_in], u16 out[n_out]` (0xffff = unused) | `get_layer_tensor_count`, `load_eai_layers`, `initialize_io_tensor` |
| 6 | supported by the loader, absent from every shipped blob | — |

Tensor descriptor (0x50 bytes; first at `section1 + 0x20`):

```
+0x00 u64 dims pointer        runtime field, 0 in the file
+0x08 u32 flags               bit0 constant/weight, bit2 activation,
                              bit7/bit30 persistent state, bit22 ?
+0x0c u32 ndim (low nibble)
+0x10 u32 reserved
+0x14 u32 dims-form tag       5 nibbles; shipped: 43210 34210 23410 21430
                              23140 823410 (gemm only special-cases 34210)
+0x18 u32 dims[0..4]
+0x2c u32 data offset         inside section 2 (group 0) or the scratch /
                              persistent pool (groups 1 / 2); 0xffffffff = none
+0x30 u64 data pointer        runtime field
+0x38 u32 byte size           == prod(dims) * elem_size for float models
+0x3c u32 dtype flags         low nibble = element size (4 = float32)
+0x40 u32 layout hint
+0x44 u32 1
+0x48 u64 reserved
```

Tensor groups: group 0 = constants (relocated into section 2), group 1 =
activations (relocated into the activation pool, the offsets form a liveness
plan with deliberate overlap), group 2 = persistent state (own pool, retained
across `eai_execute` calls).

Layer record (stride = its own `size` field, `section4 + 8` … end):

```
+0x00 u32 op id (<= 0x46)
+0x04 u32 record size (>= 16)
+0x08 u32 flags            bit8 "has offline params", bit9 = runtime skip bit,
                           bit19 fast-math
+0x0c u16 group index      (mirrored by section 0)
+0x0e u8  sub-type         op specific layout selector
+0x0f u8  op specific flag
+0x10 ..  op parameters (record_size - 16 bytes), read by <op>_load
```

`calculate_total_layer_size` gives the in-memory struct size per op id, which
is an independent cross-check of the op id (e.g. gemm 0xa8, lstm/gru 0xd0,
softmax 0x98, conv 0xd8).  Op id → name comes from `so_op_intf_load2`:

```
00 conv   02 relu   03 logsoftmax 04 maxout  05 maxpool  07 reshape 08 lstm
0a gru    0c transpose 0e batchnorm 0f quantize 10 add 11 matmul 12 div
14 no_op  15 reducemean 17 framer 18 slice 19 shape 1a squeeze 1b unsqueeze
1c mul    1d max    1e concat   20 softmax 21 tanh   22 reduce  23 conv1d
24 sub    25 pow    26 clip     27 sigmoid 29 gather 2a log     2b sqrt
2f exp    32 gemm   34 globalavgpool 35 averagepool 36 argmax 37 argmin
39 convtranspose 3a pad 3b prelu 3d-46 super* (fused conv/gemm/add/sub/mul/
deconv/avgpool/maxpool) 44 flowcontrol
```

## 3. Per-blob inventory (parsed from bytes, `ir/eai_parsed.json`)

| blob | bytes | tensors | layers | groups c/s/p | ops | native load |
|---|---:|---:|---:|---|---|---|
| `63ff0ae5_en-US_001_198088` | 8 492 | 17 | 7 | 7/7/3 | argmax, framer, gather, gru, matmul, no_op×2 | ok (builder 4.3) |
| `63ff0ae5_en-US_001_58312` | 6 076 | 19 | 8 | 7/8/4 | add, argmax, framer, gather, gru, matmul, no_op×2 | **REFUSED**: builder 12.2 |
| `63ff0ae5_en-US_001_64968` | 132 752 | 96 | 46 | 41/42/13 | add×3, conv, framer×3, gemm, gru×4, log×2, logsoftmax, matmul×8, mul×4, no_op×11, sigmoid, softmax, sub×2, tanh×3, transpose | ok |
| `63ff0ae5_en-US_001_968` | 56 928 | 118 | 64 | 41/61/16 | as above + quantize×9, squeeze, unsqueeze×2, transpose×3 | **REFUSED**: builder 12.2 |
| `63ff0ae5_en-US_002_1208` | 408 936 | 192 | 114 | 69/98/25 | add×3, concat×8, conv, framer×11, gemm×5, gru×4, log×2, logsoftmax×5, matmul×24, mul×4, no_op×31, sigmoid, softmax×5, sub×2, tanh×7, transpose | ok |
| `63ff0ae5_en-US_002_410552` | 41 772 | 85 | 39 | 35/35/15 | argmax×5, concat×4, framer×5, gather×5, gru×5, matmul×5, no_op×10 | ok |
| `63ff0ae5_en-US_036_200136` | 8 492 | 17 | 7 | 7/7/3 | argmax, framer, gather, gru, matmul, no_op×2 | ok |
| `63ff0ae5_en-US_036_67016` | 132 752 | 96 | 46 | 41/42/13 | = 001_64968 | ok |
| `63ff0ae5_en-US_038_1224` | 41 508 | 140 | 60 | 63/62/15 | framer×3, gather, gru×4, log×2, logsoftmax, matmul×7, mul×5, no_op×12, softmax, superadd×13, superconv×7, supergemm, supersub×2, transpose | **REFUSED**: enpu v4 |
| `63ff0ae5_en-US_038_161736` | 13 708 | 24 | 12 | 8/12/4 | argmax, clip, convtranspose, framer, gru, matmul, no_op×3, transpose×3 | ok |
| `63ff0ae5_en-US_038_43208` | 5 820 | 24 | 11 | 7/12/5 | argmax, framer, gru, matmul, no_op×3, superdeconv, transpose×3 | **REFUSED**: fixed point |
| `63ff0ae5_en-US_038_49864` | 111 372 | 121 | 59 | 53/57/11 | add×3, clip×6, conv×7, framer×3, gather, gemm, gru×4, log×2, logsoftmax, matmul×7, mul×5, no_op×12, sigmoid, softmax, sub×2, tanh×3 | ok |
| `63ff0ae5_en-US_040_189384` | 8 492 | 17 | 7 | 7/7/3 | argmax, framer, gather, gru, matmul, no_op×2 | ok |
| `63ff0ae5_en-US_040_56264` | 132 752 | 96 | 46 | 41/42/13 | = 001_64968 | ok |
| `general_UV_015_526296` | 10 572 796 | 449 | 252 | 160/289/0 | add×18, batchnorm×16, clip×2, concat, conv×36, div×2, gemm×33, globalavgpool×16, log, matmul×2, mul×23, no_op×36, reduce×3, reducemean×3, relu×32, sigmoid×16, softmax, sqrt×3, sub×4, tanh, transpose×3 | ok |
| `speech_float_speech_0` | 36 632 | 20 | 9 | 7/10/3 | gemm×2, lstm, no_op×3, relu×2, softmax | ok |

I/O shapes: keyword stages take `1×1×1×16` feature frames and emit one score
(`argmax` index or logits); the 1.5 stage takes `1×1×40`; the user-verification
net `general_UV_015` takes `1×64×201` and emits `1×128`; the VAD takes `1×160`
and emits `1×2` (softmax over silence/speech).

## 4. Executed network: DNN VAD `speech_float_speech_0.eai`

```
T07 (1,160)  ──gemm(W T00 24×160, b T01)──▶ T08 ─relu─▶ T09 ─no_op─▶ T10
   ──lstm(Wx T02, Wh T03, bx T04, bh T04)──▶ T17 (y), T18 (c)
   ──no_op──▶ T19 ─relu─▶ T13 ─no_op─▶ T14 ──gemm(W T05 2×24, b T06)──▶ T15
   ──softmax──▶ T16 (2)
```

`reimpl/eai_run.py` implements the four ops the model needs and reproduces the
native interpreter **exactly**: every constant tensor and every graph value
agrees to ≤ 6e-08 (float32 noise), and the softmax output is bit-identical:

```
oracle-input  -> p(speech)=0.015254 p(non-speech)=0.984746   (native: same, diff 0.0)
```

Run it:

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
$WS/tooling/venv312/bin/python $WS/reports/qcom_voiceactivation/reimpl/run_vad.py   # exit 0
$WS/tooling/venv312/bin/python $WS/reports/qcom_voiceactivation/reimpl/verify_eai.py # writes verify.txt
```

Op semantics that had to be recovered (all from `evidence/ghidra/`):

* `gemm` (`gemm_forward`, flat path, sub-type 1): shapes come from the tensor
  descriptors, not the layer record.  The constant tensor (flag bit 0) is `W`,
  the other matrix input is `X`: `out[N,M] = X[N,K] @ W[M,K]ᵀ + bias[M]` with
  `M = W.dims[-2]`, `K = W.dims[-1]`, `N = X.dims[-2]`.
* `relu`, `no_op`: `max(x,0)`, identity copy.
* `lstm` (`lstm_forward_vanilla{,_step}`, 13 input slots): the 4H projection is
  `Wx@x + Wh@h_prev + b_in3 + b_in4` (the reference adds *both* bias inputs),
  and the gate blocks are **i = σ(pre[0:H]), o = σ(pre[H:2H]),
  f = σ(pre[2H:3H]), g = tanh(pre[3H:4H])** with `c = f·c_prev + i·g`,
  `h = o·tanh(c)`, output 0 = `h`, slot 8 = `c`.  Verified independently in
  `verify.txt` §D from the native dump alone (reconstruction of `c` and `y`).
  In this model `h0`/`c0` (inputs 5/6) are unused and the reference zeroes the
  cell tensor before each call, so the layer is stateless per frame.
* `softmax` (`softmax_forward_base`): softmax over the trailing axis.

Input contract caveat: the VAD consumes a **160-float frame produced by the
`libqvavad` CAPI_v2 front-end**, not the 80-bin mel of `logmel()` in
`pipeline.py` (that one belongs to `libqvasml`/udk7 in `libqvajni.so`).  The
160 numbers' definition was not decoded, so `run_vad.py` feeds either the
oracle frame or a documented synthetic one; the model itself is exact.

## 5. Remaining blockers (with evidence)

1. **Keyword stage (1st/1.5 stage) op semantics — `framer`, `gru`, `gather`,
   `matmul`.**  Parsing is complete and correct (verify.txt §A passes for all 12
   loadable keyword/UV blobs), and the layer graph plus every weight is
   recovered, but the pure-numpy run does not yet reproduce the native GRU
   state.  Evidence of the shape of the problem:
   * `framer_forward` @0x1136e0 / `framer_incremental_forward` @0x113444 are a
     *ring buffer*: a `memsmove` of `stride` rows plus a per-frame write, driven
     by `layer+0x74` (history length) and `layer+0x7c` (incremental flag).  The
     160-column framer of the 1st stage (`params = a0 00 00 00 01 00 00 00`)
     stacks 10 frames × 16 features.
   * `gru_forward_vanilla_step` @0x11447c is a fused variant: one gemm produces
     `3H`, `σ` is applied to the first `2H` (z, r) and the reset gate scales a
     projection before the `tanh`.  A search over the standard GRU families
     (block permutations, single/double bias, `(1-z)hh` vs `z·hh`, reset-scaled
     `tanh`) against the native `T14` state of
     `63ff0ae5_en-US_001_198088` leaves a residual of 6.7e-02, i.e. the wiring
     of the fused variant (which tensor feeds which gemm operand, and the
     `layer+0x78` mode switch) is still missing.  Nothing was guessed in its
     place: `eai_run.py` marks `gru`/`framer`/`gather`/`matmul` as
     "transcribed, unverified" and refuses to run a graph that contains them
     unless `allow_unverified=True`.
   * What would close it: `gru_load` (@0x27a60) + `gru_init` (@0x27748) define
     the in-memory layer struct; `gru_forward_vanilla_core` (@0x14b3c) is the
     dispatcher and calls `gru_forward_vanilla_step` (@0x1447c) twice from
     `0x14ca0` and `0x14e6c`, passing 14 arguments (x0..x7 + 6 stack words).
     All symbol addresses must be read as *vaddr + 0x100000* in the Ghidra
     listing (`lstm_forward_vanilla` = Ghidra 0x115c20 = vaddr 0x15c20), and the
     decompiled call shows only 10 of the 14 arguments - so the operand wiring
     has to come from that assembly, not from the pseudocode.  Inside the step
     the first gemm writes a `3H` projection into `param_14` (bias `param_12`),
     the second into `param_13` (bias `param_9`), `sigma` is applied to the first
     `2H` of the sum, the reset block is multiplied into a projection before
     `tanh`, and the final combine is `h = A·z + B − B·z` (z from the first
     block).  Which of the two weight tensors is input-side vs recurrent-side is
     the open question: with `h_prev = 0` the recurrent projection contributes
     nothing, so the native `T14` state of `63ff0ae5_en-US_001_198088` only
     pins the x side (residual 6.7e-02 for every standard family tried).
2. **Four blobs are refused by the shipped floating runtime** — this is a
   property of the files, not of the parser; each refusal is predicted from the
   header and matches the library's own log line:
   * `001_58312`, `001_968`: builder 12.2 → "Model with eai_builder version
     2.12 is not supported on runtime 3.4" (the header's compiler bytes are 12/2
     at +0x08/+0x0a).
   * `038_1224`: target byte 0x21 = 4 → "Unsupported model enpu version 4,
     runtime enpu version 0".  Its tensor payloads are quantised, so
     `nbytes != prod(dims)*elem_size` (e.g. T7 `1×48×16` int8 with
     `nbytes = 731`); the loader parses it leniently and marks the tensors
     `padded`.
   * `038_43208`: header word +0x0c = 0 (bit 2 clear) → "Error: fixed point
     model, float runtime!".
3. **`general_UV_015` (user verification)** is parsed (449 tensors, 252 layers,
   10.5 MB of constants) and loadable, but running it needs
   `conv`/`batchnorm`/`globalavgpool`/`div`/`reduce`, which are only
   transcribed — the same verification gap as (1).
4. **qvavad feature front-end** (the 160 numbers) is not decoded; the model
   contract (160 float32 per call, 2-class softmax) is.
5. Not attempted: `super*` fused ops (only in `038_1224`, which does not load),
   `quantize`/`squeeze`/`unsqueeze` numerics (only in the two 12.2 blobs).

## 6. Files

```
reimpl/eai_loader.py     container parser (header, sections, tensors, layers,
                         layer I/O, misc, refusal classification)
reimpl/eai_run.py        numpy interpreter; VERIFIED_OPS = no_op, relu, gemm,
                         softmax, lstm
reimpl/run_vad.py        runnable example: speech_float.eai end to end
reimpl/verify_eai.py     writes reimpl/verify.txt (5 065 checks)
ir/eai_parsed.json       full machine-readable parse of all 16 blobs
evidence/harness/        eai_host.c (self-relocating arm64 host + oracle),
                         run_harness.sh
evidence/native_dumps/   oracle output: <blob>.txt (sections, tensors, layers,
                         I/O, tensor heads) + speech_float tensor dumps
evidence/ghidra/         eai_parse.c, eai_tensor_ops.c, eai_rnn.c,
                         libeai_floating_symtab.txt (recovered symbol table)
```

## 7. Reproduce

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
R=$WS/reports/qcom_voiceactivation
PY=$WS/tooling/venv312/bin/python

$R/evidence/harness/run_harness.sh          # native oracle (qemu-aarch64, ~1 min)
$PY $R/reimpl/eai_loader.py                 # parse all blobs -> ir/eai_parsed.json
$PY $R/reimpl/verify_eai.py                 # -> reimpl/verify.txt  (exit 0)
$PY $R/reimpl/run_vad.py                    # VAD example        (exit 0)
```
