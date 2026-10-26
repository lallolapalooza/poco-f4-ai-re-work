# GAP_CLOSURE — TikTok smash-face descriptor (`tiktok_trill`, `tiktok_musically`)

Status: **decoded**. The `.model` payload is no longer opaque. The block that
`report.md` §4.3 could only describe as "structured, not encrypted" is an
**AES-256-ECB-encrypted net description**, and the plaintext is a line-oriented
text graph that `libbytenn.so`'s `IESNN::parseNet` parses. 11 of 12 records now
decrypt to pristine ASCII and their weight buffers are carved; 4 of the 10
shape-resolvable nets close byte-exactly over the weight buffer and execute.

Everything below is byte-level derived from `libeffect.so` / `libbytenn.so` and
verified in `reimpl/verify.py` (`reimpl/verify.txt`, exit 0).

---

## 1. What `report.md` got wrong, and why

The old analysis had the right container and the right `[descriptor][weights]`
split but the wrong offsets, so the encrypted block looked like the "descriptor"
and the weights looked like they started right after it. Two off-by-Ns:

* the record header is `[u32 A][u32 config_len]` **plus** a `u32 checksum_b`
  after the config, which shifted every boundary by 4..28 bytes;
* `config_len` counts the ciphertext **plus** a trailing `u32` plaintext
  length, so the high-entropy region is `config_len - 4` bytes, not
  `blob2+4 - 4`.

The "block tail" bytes quoted in §4.3 (`664`, `6489`, `4689`, `14448`, …) are
exactly the two fields this analysis identifies: `664`/`4689` etc. are the
`u32` **plaintext length** at the end of the config field, and `6489`/`14448`
etc. are the `weight_len` field. The observed `(len - 4) % 16 == 0` invariant
was the AES block-size invariant of the ciphertext.

## 2. Container (`ModelPackage`)

Recovered from `libeffect.so`'s own error strings (rodata 0xd66bd2-0xd66cc9:
`error version_len %d`, `error num_model %d`, `error name_len %d`,
`error key_len %d`, `decrypt key using the auth_code fails`,
`error config_len %d`, `decrypt config using the key fails`,
`error weight_len %d`) and confirmed by parsing all three shipped files to the
exact byte:

```
0x00  16 B   opaque per-file id
0x10  u32    total file size  (checked: equals os.stat size)
0x14  u32    version_len ; char version[version_len]
      u32    num_model
      per model:
        u32  name_len   ; char name[name_len]          # detect, base120, ...
        u32  key_len    ; u8   key[key_len]            # 36 = 32 ct + u32 32
        u32  checksum_a
        u32  config_len ; u8   config[config_len]      # 4 + 16k
        u32  checksum_b
        u32  weight_len ; u8   weight[weight_len]
```

`reimpl/descriptor.py:ModelPackage.parse` implements exactly this and asserts
`declared_size == len(file)`, `config_len % 16 == 4`, `key[32:36] == u32 32`.
Extra + age files end exactly at EOF; `tt_face_v10.0.model` has 8 trailing
bytes after the `mean` record (the parser reports them as `trailing`).

Version strings: `tt_face_v10.0`, `v13.0`, `tt_face_attribute_age_v1.1`
(the extra model deliberately stores only `v13.0`).

## 3. Crypto

`libeffect.so` loader → `ModelPackage::init(auth_code)` (ELF 0x6b2864) →
`ModelPackage::parse(buf,size)` (ELF 0x6b2ad4; rejects `size <= 0x13`) →
`FUN_0x9ce7d0(in, in_len, key, key_len, &out, &out_len)` (ELF 0x9ce7d0).

* **Auth code**: 48 printable characters built as inline immediates in each
  model loader and validated to be exactly 48 bytes
  (`the auth_code your provide seems not be the correct size ( %d vs 48)`,
  rodata 0xd66933).
* **Key derivation**: ELF 0x6b2984-0x6b299c checks `len == 0x30` then builds
  `std::string(auth_code, 32)` (`bl 312cd0`, `mov w2,#0x20`). So the AES key is
  literally `auth_code[0:32]`.
* **Primitive**: AES with the standard key schedule — `SubWord` at ELF 0x70d298,
  RotWord as a byte-store/LE-reload, Rcon xtime with `0x1b1b1b1b` at
  0x9ce908-0x9ce924 — decrypting **16-byte blocks independently** (no IV, no
  chaining): AES-256-ECB.
* **Field format** (checks at 0x9ce958-0x9ce96c):

  ```
  field = [ ciphertext : len-4 bytes, multiple of 16 ][ u32 plaintext_len ]
  out_len = *(u32*)(field + len - 4)
  ```

  This is why `(config_len - 4) % 16 == 0` holds for every record and why
  `key_len` is 36 (`32 + u32 32`).

Verified by: a FIPS-197 C.3 known-answer test in `reimpl/verify.py`, and by
11 of 12 record configs decrypting to 100 % printable ASCII.

### Auth codes recovered (from inline immediates)

| model | loader site (ELF) | auth code |
|---|---|---|
| `tt_face_v10.0.model` | `0x68a628` | `vVwfzV4dKMAdxYEcLJ15Bb5tm0b4I7HhozHgk1z9vYW5GIov` |
| `tt_face_extra_v13.0.model` | `0x68b6f8` | `WaPdHZfZiMCQM2Ds6xMz9Rpva068vCyFMHPryDt0dhfUONtS` |

Method: `libeffect.so` builds each code as 7 register immediates (`w1..w7`)
followed by 40 `str wN,[sp,#8k]` stores; a small forward data-flow scan
(`movz wN,#imm` / `str wN,[sp,#off]`) recovers the 48 bytes. Validated by
reproducing the base model's known code bit-exactly. 29 such sites exist in
`libeffect.so` (face, hand, head-seg, attribute loaders); 24 decode to clean
48-char codes.

## 4. Net description format

`espresso::Thrustor::CreateNet` → `IESNN::Net::CreateNet` →
`IESNN::parseNet(string const&, u8 const*, size_t, IESNet*)` (libbytenn ELF
0x140788) using `IESNN::StringParser::NextLine`/`NextString`. Line 1 may be a
one-token tag; `parseNet` only special-cases `"E"`. Line 2 is
`version declared_layers [magic]`. Then one line per layer.

```
1 53
data 1 128 128 3 1 6
Convolution conv1 8 3 3 2 2 1 1 1 1 1 8 4 14 1 5 data conv1
DepthwiseSeparableConvolution block1_conv_a 8 3 3 1 1 1 1 1 1 1 4 4 9 1 6 conv1 block1_conv_a
Eltwise block1_eltsum conv1 block1_conv_b block1_eltsum 1 5 1
Pooling pool4 7 7 7 7 0 0 4 0 AVE conv4_2 pool4
InnerProduct fc_cls 2 1 0 4 0 4 0 4 0 pool4 fc_cls
Softmax prob fc_cls prob
```

* Integer tokens and blob-name tokens are interleaved per layer type; the
  parser keeps each group's order (`Layer.ints`, `Layer.names`), derives
  `bottoms` (names minus the trailing top) and, for pooling, `mode`
  (`MAX`/`AVE`/…).
* Convolution / DepthwiseSeparableConvolution prefixes: `num_output, kh, kw,
  sh, sw, ph, pw, ?, ?, dtype_code, …`. `dtype_code` is `ints[9]`.
* **`ints[9]` is the weight element size**: 1 → int8 (verified on `detect`),
  2 → int16 (verified on `image`, `slowimage`).
* Per-record quirks found: `base120det`/`partface` store a literal two-char
  `\n` escape; `base120` carries a `B` tag plus a magic `1587694005`; the
  `data` input layer has no name while `DataV2` does.

## 5. Weight buffer

```
per weight-bearing layer, in net order:
    weights   n * dtype_bytes        (int8 or int16, dtype from ints[9])
    bias      num_out * 4            (int32 fixed point)
```

Verification is **byte-exact closure** of the whole buffer, and the first bias
block of `detect` reads `[1410, 2945, 2168, 2135, 2027, -396, 8749, 9263]` as
int32 — 8 sane fixed-point biases for an 8-channel convolution:

| record | consumed / buffer | |
|---|---|---|
| `detect` | 14448 / 14448 | **CLOSED** |
| `base120det` | 151168 / 151168 | **CLOSED** |
| `image` | 81880 / 81880 | **CLOSED** |
| `slowimage` | 92296 / 92296 | **CLOSED** |
| `pout`, `eyecls` | 6104 / 6489 | residue 385 = InnerProduct as float32 (+384) + 1 B |
| `base120`, `partface`, `iris`, `extra` | residue > 0 | graphs use `Slice`/`Split` with several tops on one line; the shape tracker used for counting does not split them yet |

`mean` is **not** a network: its config plaintext is the single byte `"1"` and
its 7620-byte weight region is 1905 little-endian float32 values
(range −2.769 … 255.241) — the anchor/prior table, fully decoded.

## 6. Runnable

`reimpl/run_record.py --model <file> --record <name>` decrypts, parses,
carves the weights and executes the graph in numpy
(Convolution, DepthwiseSeparableConvolution, Pooling MAX/AVE + global,
Eltwise, InnerProduct, Softmax, ReLU, Sigmoid, Concat).

Executed with real weights (`reimpl/verify.txt`):

| record | in → out | note |
|---|---|---|
| `detect` | 1×3×128×128 → `rpn_bbox_pred/32s` 1×12×4×4 | 54 layers, 39 weighted tensors, buffer closed |
| `image` | 1×3×160×160 → 1×12×5×5 | int16 weights, buffer closed |
| `slowimage` | 1×3×192×192 → 1×16×6×6 | int16 weights, buffer closed |
| `pout` | 1×3×56×56 → `prob` 1×2×1×1 (softmax sums to 1) | residual layout |
| `mean` | 1905 float32 | data record |

## 7. Remaining blockers (precise)

1. **`agenet` / `tt_face_attribute_age_v1.1.model` auth code — NOT recovered**
   (1 of 12 records). Its config is a normal 14260-byte AES field
   (plaintext 14253 B) but we do not have its 48-char auth code. It is not any
   of the 756 `[A-Za-z0-9]{48}` literals in `libeffect.so`, not any of the 24
   codes decoded from `bl 0xa97bdc` sites, and not one-character-away from the
   five sites whose immediate scan came out at 47/49 bytes (all tested). Next
   step: the age model is loaded from `FaceAttributesTask`
   (`ttfaceattrmodel/tt_face_attribute_age_v1.1.model`, string 0xd9283f,
   xref site `0xafdd54`); decompile that loader and read its immediate array
   the same way (`movz`+`str` data-flow scan). The `tt_face_attribute_v3.2`
   loader's rodata literal `IMvV6VBoTmPoPKoXAvjdvyBkMy10dQXsnhF9pgX49h6VPh00`
   (0xd66dc6) was tested and is **not** it.
2. **Weight element dequantisation scale — NOT recovered.** Weights are
   int8/int16 via `ints[9]` and biases int32, both verified by buffer closure,
   but the per-tensor fixed-point scale is not in the weight buffer (a scan for
   float32 runs at all four phases finds none). `run_record.py --wscale`
   applies `w * 2**-wscale`; the default is 0. Consequence: outputs are the
   correct graph over the correct weights but are **not claimed bit-exact**
   with the effect SDK. Next step: `IESNN::readEspressoweight`
   (libbytenn ELF 0x1520cc / 0x14bb68) and the dequant loop at libeffect
   0x697ab4-0x697b88 (report §11.5) take the scale from the layer struct
   fields filled by `parseConvLayerV1` (libbytenn ELF 0x156250) — the two
   trailing groups of `ints[]` on each layer line are the remaining fields
   (`num_kernel, kernel, stride, pad, bias, weight, top, bottom` per rodata
   0x1854af) and were not fully mapped.
3. `Slice` / `Split` / `Crop` layers (multi-top lines) are not modelled, so
   `base120`, `partface`, `iris`, `extra` parse but over-count/under-count
   their weight buffers and do not execute. The graphs are fully readable; the
   fix is to split multi-top lines and carve the slice parameters.

Nothing here is invented: every op, shape, byte offset and constant above is
either read out of a shipped binary or verified by an exact byte-count match.
