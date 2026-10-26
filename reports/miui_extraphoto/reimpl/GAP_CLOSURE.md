# GAP_CLOSURE — embedded MACE document models (`miui_extraphoto`)

Status: **partially closed**. The five embedded document models are located,
separated and extracted; their constant-tensor payload is proven to the byte
and every one of the 466 constant tensors now has a byte range and an fp16
decoder. The **operator graph (MACE `NetDef`) is not present in this binary**
and cannot be reconstructed from it, so there is no inference path.

Date: 2026-10-26.
Library: `native/libdoc_photo_process.so` (Android arm64, soname
`libDocumentProcess.so`, 25,029,048 B, `.rodata` sha256
`cbfac2f0e32012f1dff584bdb9fdfa12249d17eea95483afd5a57bea17a8d57d`).

Everything below is tagged **proven** (observed bytes/disassembly, reproducible
with the commands in §9) or **[inference]**.

---

## 0. Result in one table

| block | description block (vaddr) | names | const tensors | payload bytes | fp16 weight buffer | payload law |
|---|---|---:|---:|---:|---|---|
| 0 | `0x16020e0..0x1605be0` | 283 | 105 | 0x08D460 | `0x4387CC` (getter `0xb1694`) | verified (slack 2 B) |
| 1 | `0x1605be8..0x16071e8` | 369 | 160 | 0x1430F4 | `0x4C5CB0` **and** `0x608E28` (getters `0xb1828`, `0xb19bc`) | verified (slack 0) |
| 2 | `0x16071f0..0x1609560` | 226 | 57 | 0x304A4C | `0x74BFB4` (getter `0xb1b50`) | verified (slack 0) |
| 3 | `0x1609568..0x160a030` | 119 | 56 | 0xA7E470 | `0xA50A98` (getter `0xb1ce4`) | **not** verified (slack 0x120000) |
| 4 | `0x160a038..0x160cb60` | 202 | 88 | 0x133148 | `0x14CEF8C` (getter `0xb1e78`) | verified (slack 0) |

* description region `0x16020d8..0x160cb60` = 43,656 B, five blocks, 466 const-tensor records.
* weight buffers: six arrays, `0x4387CC..0x16020D4`, 18,650,700 B of fp16 data
  plus 700 B of alignment gaps (`0x84,0x84,0x98,0x98,0x84`) — the five sizes and
  the five gaps tile the region **exactly** (`18,650,700 + 700 = 18,651,400`).
* extracted artifacts: `models/embedded/mace/` (21 files + `MANIFEST.json`),
  parser/extractor/verifier: `reimpl/mace_loader.py`.

Not closed: MACE `NetDef` (op types, wiring, attributes, per-tensor shapes),
model-name → model-id registry, and the last 0x120000 B of block 3's payload.

---

## 1. Where the models are (proven)

```
.rodata                     0x435700 .. 0x16d80ad  (19,540,397 B)
  fp16 weight buffer 0      0x4387cc .. 0x4c5c2c   (578,656 B)
  gap 0x84
  fp16 weight buffer 1      0x4c5cb0 .. 0x608da4   (1,323,252 B)
  gap 0x84
  fp16 weight buffer 2      0x608e28 .. 0x74bf1c   (1,323,252 B)
  gap 0x98
  fp16 weight buffer 3      0x74bfb4 .. 0xa50a00   (3,164,748 B)
  gap 0x98
  fp16 weight buffer 4      0xa50a98 .. 0x14cef08  (10,997,360 B)
  gap 0x84
  fp16 weight buffer 5      0x14cef8c .. 0x16020d4  (1,257,800 B)
  model description blocks  0x16020d8 .. 0x160cb60 (43,656 B)
  MACE runtime strings, OpenCV, protobuf, TBB, libc++
                            0x160cb60 .. 0x16d80ad
```

Correction to an earlier reading of this file: the region
`0x4d3700..0x78c700` was previously labelled "int8 with 0x80 zero point"
(`PIPELINE.md` A.3 / `REIMPL_COVERAGE.md` treat the payload as opaque). It is
**fp16**: `00 80` is `-0.0` in binary16 and is used as padding inside these
buffers. Decoded as fp16 the whole region is finite, `|x|<=1` for 48 % of the
values, and the tensor boundaries in §3 fall on the boundaries of those padding
runs. There is no int8 tensor in this library.

## 2. Description-block grammar (proven)

Each block is `[u64 0x0000000000000000 | u32 0 | u32 payload_bytes]` followed
by a name stream and one const-tensor table:

```
trailer      u32 0, u32 payload_bytes      (8 B, 8-aligned)
names        NUL-terminated ASCII, tokens back to back, <=8 B of NUL padding
             between tokens; MACE op types ("Conv2D", "DepthwiseConv2d",
             "StridedSlice", "ScalarMath", "ResizeBilinear", ...), argument
             names ("padding", "strides", "activation", ...), tensor names
             ("EdgeDet/MobilenetV1/Conv2d_0/weights:0", "inference/generator/
             conv1_1/Relu", "dewarp_466", "model.res1.conv2.conv2d.weight")
table        n x { u64 byte_offset, u64 fp16_element_count }, 16 B aligned,
             monotone-ish layout order, up to one 16-byte invalid slot
             (block 0 has one: 0x400000000,0 at 0x1605570)
```

The five trailers are at `0x16020d8`, `0x1605be0`, `0x16071e8`, `0x1609560`,
`0x160a030`; each block runs to the next trailer, the last one to `0x160cb60`
(where the MACE runtime strings begin). Boundaries are self-delimiting: the
trailer value is exactly the block's payload size (§3), so a block cannot be
confused with the neighbouring rodata.

## 3. Constant-tensor payload law (proven)

`byte_offset` is a byte offset into the block's fp16 buffer and
`fp16_element_count` is an element count; a tensor body therefore occupies
`[off, off + 2*count)`. Evidence:

1. consecutive records in all five tables satisfy
   `off[i+1] = off[i] + 2*count[i]` (zero-gap packing) — e.g. block 4:
   `0x13c8+2*0x120=0x1608`, `0x1608+2*0x20=0x1648`, `0x1648+2*0x800=0x2648`, ...
2. the union of `[off, off+2*count)` reproduces the trailer value:
   blocks 0/1/2/4 have slack `2/0/0/0` B and coverage `1.0000/1.0000/1.0000/1.0000`.
3. sizes are meaningful element counts: block 4 contains
   `288 = 3*3*1*32` (depthwise), `2048 = 1*1*32*64`, `8192 = 1*1*64*128`,
   `16384 = 1*1*128*128`, `81920 = 1*1*256*320` — a MobileNet-V1 layout, matching
   the `EdgeDet/MobilenetV1/...` names.
4. decoded fp16 values are ordinary weights: block 4's first tensor is a
   1-element scalar `-4.16016`; the 288-element tensor has `std 0.289`,
   `absmax 1.72`; `finite = 1.0` for every tensor in blocks 0/1/2/4.

Block 3 is the exception: its 56 records end at `0x95E470` while its trailer
declares `0xA7E470`; the 1,179,648-byte difference is exactly **two more
`0x90000`-element tensors** (the two largest tensors in that table), i.e. the
table we can see does not describe the tail of that buffer. Marked not verified.

## 4. How the code reaches the weights (proven)

The library contains six tiny accessor functions, each 12 bytes from its size
counterpart (`evidence/mace_loader_notes.txt`):

```
0xb1694  adrp x0,0x438000 ; add x0,x0,#0x7cc ; ret      -> 0x4387cc
0xb16a0  mov  w0,#0xd460  ; movk w0,#8,lsl#16 ; ret     -> 0x8d460
... (six pairs: 0xb1828/0xb1834, 0xb19bc/0xb19c8, 0xb1b50/0xb1b5c,
     0xb1ce4/0xb1cf0, 0xb1e78/0xb1e84)
```

All twelve are called exactly once, from the loader at `0xafbc4`, which
dispatches on a model id (`cmp w8,#8` + jump table at `0x4385a4`, 9 cases),
fetches that model's `(payload, size)` and calls

```
bl 0x183674   ; (engine, net_def, input_nodes, output_nodes,
              ;  model_data=payload, model_data_size=size,
              ;  model_data_unused=0, 0)
```

i.e. the `MiAILab::MacePlatform` wrapper over
`engine_->Init(net_def, in, out, model_data, size, unused)` whose format strings
are at `0x160dc32`/`0x160dd76`. The `net_def` argument is read from a `.bss`
registry (`0x17ee418`) after a name lookup (`bl 0xb029c`), **not** from
`.rodata`.

Ghidra confirms the shape of this function (`FUN_001afbb0`, 0x630 B): it looks
up `DAT_018ee418` (vaddr `0x17ee418`, the `.bss` model registry) with the model
name, `switch`es on the resulting id (9 cases), and in every case
(a) builds a *net object* by calling one of six identical 0x100-byte builders
(`0xb16ac`, `0xb1840`, `0xb19d4`, `0xb1b68`, `0xb1cfc`, `0xb1e90`; they log
`"Create multi net "` from `0x438671` and allocate a 0x70-byte `MultiNetDef`),
(b) takes the payload pointer/size from the getter pair, and
(c) calls `0x183674`.

Switch table → payload (proven mechanically, `evidence/mace_loader_notes.txt`):

| case | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|---|---|---|---|---|---|---|---|---|---|
| payload | P3 `0x74bfb4` | P5 `0x14cef8c` | P1 `0x4c5cb0` | P2 `0x608e28` | P0 `0x4387cc` | — | — | — | P4 `0xa50a98` |

Cases 5–7 fall through to the default branch: three of the nine model names
have **no data in this build**.

## 5. Which block is which model

| block | tensor-name family | identification | confidence |
|---|---|---|---|
| 4 | `EdgeDet/MobilenetV1/...`, last names `pred_segment`, `pred_segment:0` | `hed_model` / `ScanModel`: descriptor `0x436fe2` gives `hed_input` 1x384x384x3 → `img_output` 1x384x384x2 and the mask has exactly 2 output channels | **confirmed** (names + shape + descriptor) |
| 1 | `dewarp_*`, `net.latlayer*`, `Div_1`, `Clip_*` | `dewarp_coarse_model` and `dewarp_fine_model`: one table, **two** payloads of identical size, reached by switch cases 2 and 3; `dewarp_fine_model` descriptor `0x437ea0` = 1x193x193x3 + 1x385x385x3 → 1x385x385x2 | **confirmed** that the block is the dewarp pair; which of P1/P2 is coarse vs fine is **unknown** (identical layout, no name link) |
| 2 | `inference/generator/input_conv/...`, `Deconv2D`, `Activation` | one of {`enhance_model`, `demoiring_model`}; switch case 0 | [inference] |
| 0 | `inference/generator/conv1_1...conv5_2`, `ConvBlock5`, `deconv5_1`, `concat`, `Tanh` | the other of {`enhance_model`, `demoiring_model`}; switch case 4; HED-style 1-channel generator | [inference] |
| 3 | `model.conv1...`, `res1..res3`, `deres2..4`, `deconv4`, `ResizeNearestNeighbor`, `DepthToSpace` | switch case 8 = last enum member; 119 names, 11 MB payload = the largest network | [inference] = `handwrite_model` |

Reason the name→id map is not resolved: the enum lives in `.bss` and no
`{char*, int}` table for it exists in `.rodata` (0 relocations target the model
name strings `hed_model`/`enhance_model`/...; they are referenced only by the
per-model init functions that copy them into the `ScanModel`/`EnhanceModel`
descriptors).

## 6. Blockers (precise)

**B1 — no `NetDef` in the image.** The model names occur only as C strings
(`hed_model` ×1, `enhance_model` ×3, `EdgeDet/MobilenetV1` ×92,
`inference/generator` ×469). No protobuf framing of any of them exists
(`0x0a`/`0x22` + length prefixes: 0 hits), and no `mace.NetDef` payload is
present. `mace.pb.cc` is linked, so the runtime *can* parse a `NetDef`; it never
gets one from this file.

**B2 — the description blocks are referenced by nothing, and the graph object
is built at run time.** 0
`R_AARCH64_RELATIVE` relocations, 0 `movz/movk` absolute constants, 0 64-bit
literal-pool constants and 0 `adrp+add`/`adrp+ldr` targets inside
`0x16020d8..0x160cb60` other than the five MACE log literals that the linker
interleaved with the name streams (`Create net `, `Create operators`,
`Create operator `, `Create tensor `, and the accidental `"20"`).
`evidence/mace_refscan.txt`. Consequence: the name streams + tables cannot be
turned into a graph by us *and* are not turned into one by this library; the
`net_def` comes from the `.bss` registry (`0x17ee418`), which is filled at run
time by a component that is not statically resolvable here.

**B3 — block 3 tail.** 0x120000 B (two 0x90000-element tensors) of block 3's
payload have no record.

**B4 — no shapes.** A record stores only (offset, element count). Per-tensor
shapes/axes/op assignment exist only in the missing `NetDef`. Weight *shapes*
can be *guessed* from name + MACE op semantics (§3.3 does it for MobileNet), but
that is reconstruction, not recovery.

**B5 — dtype is derived, not declared.** fp16 is inferred from the exact
2-bytes-per-element packing law plus value statistics; there is no dtype tag in
the description block.

## 7. What the artifacts can and cannot do

Can: load any constant tensor of any of the five models as fp16
(`offset`, `elements`), verify its statistics, and lay out an input tensor for a
model whose descriptor and names are known (e.g. `hed_model`: RGBA→RGB
384x384x3 staging is already documented by `PIPELINE.md` A.3).

Cannot: run inference. Without the `NetDef` there is no op order, no
activation/stride/padding attributes, no graph wiring; fabricating one would be
inventing the model.

## 8. Not separable / dead data?

Two readings are consistent with B1+B2:

1. the description blocks are **debug leftovers** of a build that dumped the
   optimised graph, while the real graph is compiled into a different artefact;
2. the graph is **built programmatically** at run time from a table that this
   binary does not contain.

Either way, for *this* APK the recovered artefacts are: names, tensor sizes, and
the complete fp16 weight set — not an executable model.

## 9. Reproduce

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
PY=$WS/tooling/venv312/bin/python
cd "$WS/reports/miui_extraphoto"

$PY reimpl/mace_loader.py --self-test                     # synthetic-image test
$PY reimpl/mace_loader.py --report                        # block/payload report
$PY reimpl/mace_loader.py --extract models/embedded/mace   # carve artifacts
$PY reimpl/mace_loader.py --check  models/embedded/mace    # hash/range check
$PY evidence/mace_refscan.py                               # negative evidence
```

Recorded output: `reimpl/verify.txt`, `evidence/mace_refscan.txt`,
`evidence/mace_loader_notes.txt`, machine-readable report
`reimpl/mace_report.json`.

## 10. Corrections this work makes to the report set

* `PIPELINE.md` A.8 / `REIMPL_COVERAGE.md`: "ExtraPhoto embedded MACE/vendor
  models (not separable)" — they **are** separable; 6 buffers, 466 tensors,
  exact bases/sizes (§0, §4).
* `PIPELINE.md` A.2: `dewarp_fine_model` descriptor `0x437ea0` actually holds
  three 4-D arrays — `[1,193,193,3]`, `[1,385,385,3]`, `[1,385,385,2]`; the
  "193x193x3 coarse" block mentioned in the table is the *first input* of the
  fine model. `enhance_model` (`0x437b61`) has exactly one array,
  `[1,512,512,3]`.
* `REIMPL_COVERAGE.md` / report: the payload is fp16, not int8; there is no
  `0x80` zero point (§1).
* `PIPELINE.md` E.1 ("does the shipping ScanModel include `/255`?"): the
  normalisation is not a weight, it is an `Eltwise/truediv` op with a
  `scalar_input` argument — the argument *string* names (`truediv`,
  `scalar_input`, `has_data_format`) are in block 0's name stream, and its
  `scalar_input` constant is the 4-element tensor at offset 0 of block 0's
  payload (`0x4387cc`: `0.0978, 0.0668, 0.0784, 0.0` [inference]). The exact
  runtime division still needs the op attributes from the missing `NetDef`.

## 11. Next steps that would actually close the graph

1. Resolve the `.bss` registry writer for `0x17ee418` (follow the stores into
   that region from the model init functions) — the `NetDef` may be assembled by
   code that uses *no* model strings, in which case the tables in §2 are the
   only graph witness and a format reverse-engineering of the *table order*
   (op order is partially recoverable from the name stream order) is the only
   route left.
2. Check a device-side run of `nativeInit` with `frida` on
   `MiAILab::MacePlatform::Init` to dump the `NetDef` bytes at
   `engine_->Init` entry — the only remaining source of the graph.
3. Ask whether `assets/common/doc_photo/doc_model/dlc/document_model.dlc`
   (SNPE, `1x512x512x3 -> 1x512x512x1`) is the same network as block 0 or 2; the
   DLC is a complete graph and would give the missing shapes for that one model.
