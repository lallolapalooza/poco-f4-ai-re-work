# Shared: TikTok `tt_face*.model` container — musically/trill (byte-identical files)

Hashes (both APKs): tt_face_v10.0 175210db…, tt_face_extra_v13.0 d0f4e9f9…, tt_face_attribute_age_v1.1 8efeb7f2…

## Record structure (confirmed by both agents)
```
0x00 16 B  opaque per-file id (not md5/sha of body, not constant across files)
0x10 u32   total file size (== stat size)
0x14 u32   version-string length -> string ("tt_face_v10.0", "v13.0", "tt_face_attribute_age_v1.1")
      u32  record count N (9 / 2 / 1)
      N x record (in file order)
        u32   name length -> name ("detect","base120","base120det","pout","eyecls",
                                   "partface","image","slowimage","mean" / "iris","extra" / "agenet")
        u32   constant 36
        32 B  per-file key A (byte-identical in every record of a file; 9/2/1 occurrences exactly)
        u32   constant 32
        32 B  per-record blob B
        payload (extends to the next record name / EOF)
```
Verified in all 3 files: size field == file size; A repeats exactly N times; const36==36 and
const32==32 for every record; records are contiguous with no padding outside payload.

## NEW (musically, not in the earlier trill notes)
1. `B[4:8]` (u32 at offset 4 of the 32-byte per-record blob) is a size field and is
   **≡ 4 (mod 16) for all 12 records**:
   v10: detect 4708, base120 6932, base120det 5140, pout 676, eyecls 676,
        partface 5012, image 6644, slowimage 6660, mean 20;
   extra: iris 20?? (see its B), extra 0x2be4=11236; age: agenet 0x37b4=14260.
   All (value-4) are multiples of 16. Probability from chance ~16^-12.
   => this smells like a **16-byte block-cipher section length** (`4 + ciphertext_len`), i.e.
   an encrypted net-def/graph section per record. The five other u32s of B are random-looking.
2. The 32-byte A is NOT the AES key: AES-128/192/256 ECB/CBC/CTR (IV = 0/A[0:16]/A[16:32]/B[0:16]/
   B[16:32]/header16), key = A, A halves, md5/sha of A/name/paths/header, and XOR+inv-Sbox
   compositions were all tested over payload[0..B[4]) at offsets 0..12; no plaintext/text hit.
3. `mean` record payload is fully decoded and is **not** a graph:
   `[u32 7620][1905 x float32 LE][8 zero bytes]` = 7632 B exactly. First floats ≈ 30.03, 73.78,
   30.07, 86.69. (So B[4]=20 for mean does NOT delimit this float array.)
4. libbytenn ships a net-def *deobfuscation* routine (not AES): in
   `BYTENN::EspressoDisassembler::disassemble(ByteNNInternalConfig&)` @libbytenn 0x42500
   (also inside disassembleModels @0x43418): copy net-def, then `buf[i] ^= key[i % 32]`,
   then `buf[i] = AES_inverse_sbox[buf[i]]` (inv S-box table at libbytenn .rodata 0x184b32;
   scan loop at 0x42808-0x428cc). Key pointer comes from the BM header field at +0x20.
   This is the only statically visible model-data cipher in the AI libs; worth trying on the
   smashed-face record payloads once the key location is found (libeffect has no AES tables).
5. libeffect.so imports espresso::Thrustor (CreateNet/SetInput/Inference/Extract/SkipLayer)
   and hard-codes the record names plus partface layer names (crop_1, topdown0_proj.0, up0,
   elt1, classifier_1, top0_shuffle.conv1.0, …), so the unpacked payload is a text/binary
   espresso net def whose schema lives in the stripped smash-face loader (xrefs: version log
   0x68a3c0, extractor 0x696900/0x6969e0, loader helpers 0x696218/0x696634/0x696664).

## Follow-up target
Find who fills the `map<string, model*>` consumed by `ExtractModel` (0x696900) — i.e. the
function that turns payload bytes into the named sub-model map. That is where B[4] and the
deobfuscation/key are used.

## ADDENDUM (cross-checked with ResTIKTOK2 / trill, identical files)
* The `4 mod 16` alignment is a **struct-size artifact**: `B[4]` delimits a
  serialized graph-descriptor block at the start of the payload.  Its tail is a
  dense u16 op/tensor table with u32 offsets just below the block size
  (partface 5003/5012, image 6639/6644, agenet 14253/14260, detect 4689/4708).
  It is NOT ciphertext and NOT compressed.
* `mean` record = `[u32 7620][1905 fp32][8 zero]`; the values are monotone
  anchor-geometry pairs (30.03/73.78, 30.07/86.69, …), not image means.
* Cipher tests (AES/ChaCha/zlib/lzma/bz2 and bytenn XOR+inv-Sbox) all negative
  on full payloads and descriptor blocks; entropy rises, no text/BM.
* Remaining blocker: descriptor schema only (stripped libeffect.so smash-face
  loader; map-filling function upstream of ExtractModel 0x696900).
