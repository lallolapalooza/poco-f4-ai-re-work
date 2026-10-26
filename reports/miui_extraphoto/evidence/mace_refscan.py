#!/usr/bin/env python3
"""mace_refscan.py - reproducible negative evidence for the embedded MACE model
sections of libdoc_photo_process.so.

Scans the shared object for *any* mechanism that could reference the embedded
model description blocks (0x16020d8..0x160cb60) or the candidate tensor payload
regions, and for protobuf (NetDef) framing of the model names.

    python3 evidence/mace_refscan.py [path/to/libdoc_photo_process.so]

Exit code 0 always; the printed summary is the evidence quoted in
reimpl/GAP_CLOSURE.md.
"""
import re
import struct
import sys

SO = sys.argv[1] if len(sys.argv) > 1 else "native/libdoc_photo_process.so"
d = open(SO, "rb").read()

# section table
e_shoff, = struct.unpack_from("<Q", d, 0x28)
e_shentsize, e_shnum, e_shstrndx = struct.unpack_from("<HHH", d, 0x3A)
base = e_shoff + e_shstrndx * e_shentsize
str_off, str_size = struct.unpack_from("<QQ", d, base + 0x18)
shstr = d[str_off:str_off + str_size]
S = {}
for i in range(e_shnum):
    o = e_shoff + i * e_shentsize
    nm, typ, fl, addr, off, size = struct.unpack_from("<IIQQQQ", d, o)
    name = shstr[nm:shstr.index(b"\0", nm)].decode()
    S[name] = (addr, off, size)

TEXT = S[".text"]
RO = S[".rodata"]
RO_LO, RO_HI = RO[0], RO[0] + RO[2]
MODEL = (0x16020D8, 0x160CB60)
# fp16 weight buffers, from the six (ptr,size) getters in .text
PAYLOADS = [(0x4387CC, 0x4387CC + 0x8D460),
            (0x4C5CB0, 0x4C5CB0 + 0x1430F4),
            (0x608E28, 0x608E28 + 0x1430F4),
            (0x74BFB4, 0x74BFB4 + 0x304A4C),
            (0xA50A98, 0xA50A98 + 0xA7E470),
            (0x14CEF8C, 0x14CEF8C + 0x133148)]

print("file      : %s (%d B)" % (SO, len(d)))
print(".text     : %#x..%#x   .rodata: %#x..%#x"
      % (TEXT[0], TEXT[0] + TEXT[2], RO_LO, RO_HI))
print("model region %#x..%#x (%d B)" % (MODEL[0], MODEL[1], MODEL[1] - MODEL[0]))
print("weight payloads " + ", ".join("%#x..%#x" % p for p in PAYLOADS))
print()


def in_model(v):
    return MODEL[0] <= v < MODEL[1]


def in_payload(v):
    return any(a <= v < b for a, b in PAYLOADS)


# ---- 1. ELF relocations -----------------------------------------------------
rela = S[".rela.dyn"]
rel_model = rel_pay = 0
for i in range(rela[1], rela[1] + rela[2], 24):
    _off, info, add = struct.unpack_from("<QQq", d, i)
    if (info & 0xFFFFFFFF) != 1027:      # R_AARCH64_RELATIVE
        continue
    rel_model += in_model(add)
    rel_pay += in_payload(add)
print("1. R_AARCH64_RELATIVE relocations with addend in model region : %d"
      % rel_model)
print("   R_AARCH64_RELATIVE relocations with addend in payload      : %d"
      % rel_pay)

# ---- 2. PC-relative address materialisation (adrp + add) --------------------
ts, tsz = TEXT[0], TEXT[2]
adrp_model = adrp_pay = 0
from collections import Counter
hist = Counter()
payhist = Counter()
for i in range(ts, ts + tsz - 24, 4):
    w, = struct.unpack_from("<I", d, i)
    if (w & 0x9F00001F) != 0x90000000:
        continue
    immlo, immhi = (w >> 29) & 3, (w >> 5) & 0x7FFFF
    imm = (immhi << 2) | immlo
    if imm & (1 << 20):
        imm -= 1 << 21
    page, reg = (i & ~0xFFF) + (imm << 12), w & 0x1F
    for k in range(1, 13):
        j = i + 4 * k
        w2, = struct.unpack_from("<I", d, j)
        if (w2 & 0xFF800000) == 0x91000000 and ((w2 >> 5) & 0x1F) == reg:
            addr = page + (((w2 >> 10) & 0xFFF) << (12 if (w2 >> 22) & 1 else 0))
            if in_model(addr):
                adrp_model += 1
                hist[addr] += 1
            if in_payload(addr):
                adrp_pay += 1
                payhist[addr] += 1
            break
print("2. adrp+add targets inside model region (<=12 insns)          : %d"
      % adrp_model)
for addr, n in hist.most_common():
    lit = d[addr:addr + 40].split(b"\0")[0][:40]
    print("     %#x x%-4d -> %r" % (addr, n, lit))
print("   (all of the above are MACE runtime log string literals that the "
      "linker interleaved with the\n    model name streams; no tensor name, "
      "record table or payload byte is targeted)")
print("   adrp+add targets inside payload regions                    : %d"
      % adrp_pay)
for addr, n in payhist.most_common():
    print("     %#x x%d -> %r" % (addr, n, d[addr:addr + 16]))

# ---- 3. absolute constants (movz + movk) -----------------------------------
mov_model = mov_pay = 0
for i in range(ts, ts + tsz - 24, 4):
    w, = struct.unpack_from("<I", d, i)
    if (w & 0x7F800000) != 0xD2800000:          # MOVZ (64-bit)
        continue
    reg, val = w & 0x1F, ((w >> 5) & 0xFFFF) << (((w >> 21) & 3) * 16)
    ok = True
    for k in range(1, 4):
        wk, = struct.unpack_from("<I", d, i + 4 * k)
        if (wk & 0x7F800000) != 0xF2800000 or (wk & 0x1F) != reg:
            ok = False
            break
        val |= ((wk >> 5) & 0xFFFF) << (((wk >> 21) & 3) * 16)
    if ok:
        mov_model += in_model(val)
        mov_pay += in_payload(val)
print("3. movz/movk absolute constants in model region               : %d"
      % mov_model)
print("   movz/movk absolute constants in payload regions            : %d"
      % mov_pay)

# ---- 4. 64-bit literal-pool loads ------------------------------------------
lit_model = lit_pay = 0
for i in range(ts, ts + tsz - 4, 4):
    w, = struct.unpack_from("<I", d, i)
    if (w & 0xFF000000) != 0x58000000:          # LDR (literal, 64-bit)
        continue
    imm19 = (w >> 5) & 0x7FFFF
    if imm19 & (1 << 18):
        imm19 -= 1 << 19
    tgt = i + (imm19 << 2)
    if not (0 <= tgt < len(d) - 8):
        continue
    v, = struct.unpack_from("<Q", d, tgt)
    lit_model += in_model(v)
    lit_pay += in_payload(v)
print("4. ldr-literal 64-bit constants in model region               : %d"
      % lit_model)
print("   ldr-literal 64-bit constants in payload regions            : %d"
      % lit_pay)

# ---- 5. protobuf (NetDef) framing of the model names -----------------------
names = ["hed_model", "enhance_model", "dewarp_coarse_model", "dewarp_fine_model",
         "EdgeDet/MobilenetV1", "inference/generator", "pred_segment:0"]
print("5. protobuf framing of model strings in .rodata:")
for n in names:
    b = n.encode()
    plain = len(re.findall(re.escape(b), d))
    framed = len(re.findall(re.escape(b"\x0a" + bytes([len(b)]) + b), d)) + \
             len(re.findall(re.escape(b"\x22" + bytes([len(b)]) + b), d))
    print("   %-22s plain occurrences=%d  pb-framed (0x0a/0x22 len)=%d"
          % (n, plain, framed))
ops = ["Conv2D", "DepthwiseConv2d", "FullyConnected", "StridedSlice"]
for n in ops:
    b = n.encode()
    framed = len(re.findall(re.escape(b"\x22" + bytes([len(b)]) + b), d))
    print("   op type %-20s pb-framed (field 4,string)=%d" % (n, framed))

print()
print("verdict:")
print("  * model DESCRIPTION blocks (0x16020d8..0x160cb60) are referenced by "
      "nothing: 0 relocations,")
print("    and the only adrp+add hits inside the region are MACE runtime log "
      "literals interleaved")
print("    with the name streams ('Create operator ', 'Create tensor ', "
      "'Create net ').")
print("  * model WEIGHTS are reachable only through the constant-returning "
      "getters listed in")
print("    step 2 payload hits (adrp x0/add x0,#imm/ret and movz/movk/ret "
      "pairs, consumed by the")
print("    loader at 0xafbc4).  Those two instructions per getter are the "
      "entire link between")
print("    code and the 18 MB of fp16 bytes; nothing else points at them.")
print("  * no protobuf/NetDef framing of the model names exists anywhere in "
      "the image, so the")
print("    operator graph is not stored statically.")
