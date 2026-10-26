"""TikTok smash-face model descriptor: container + crypto + net-text parser.

Everything here was recovered from the shipped APK (`libeffect.so`) and
verified against the three shipped `.model` files; see `GAP_CLOSURE.md`.

Chain
-----
1. ``libeffect.so`` builds a 48-char **auth_code** as inline immediates in the
   model loader (e.g. ELF 0x68a628 for ``tt_face_v10.0.model``) and calls
   ``ModelPackage::init(auth_code)`` (ELF 0x6b2864).  The auth code is
   validated to be exactly 48 bytes ("the auth_code your provide seems not be
   the correct size ( %d vs 48)", rodata 0xd66933); a 32-byte string is then
   taken from it (ELF 0x6b299c -> ``312cd0`` = ``std::string(ptr, 32)``) and
   that string is the **AES-256 key**.
2. ``ModelPackage::parse(buf,size)`` (ELF 0x6b2ad4, needs ``size > 0x13``)
   walks the container written below and calls the crypto primitive
   ``FUN_0x9ce7d0(in, in_len, key, key_len, &out, &out_len)`` (ELF 0x9ce7d0)
   twice per record:

   * ``key_field``  decrypted with ``auth_code[0:32]``  -> 32-byte net key
   * ``config``     decrypted with that net key        -> net text

   The primitive implements AES key expansion (``SubWord`` at ELF 0x70d298,
   Rcon xtime with 0x1b1b1b1b at 0x9ce918-0x9ce924) and decrypts 16-byte
   blocks with **no chaining** -> AES-256-ECB.  Its checks
   (0x9ce958-0x9ce96c) are exactly the layout of an encrypted field:

       if (in_len - 4) < 0x10        -> reject
       if ((in_len - 4) & 0xf) != 0  -> reject
       out_len = *(u32*)(in + in_len - 4)          # plaintext length

   i.e. ``field = [ciphertext (multiple of 16)][u32 plaintext_len]``.
3. The decrypted ``config`` is the **net description text** consumed by
   ``espresso::Thrustor::CreateNet`` -> ``IESNN::parseNet`` in ``libbytenn.so``
   (``StringParser::NextLine``/``NextString``, ELF 0x140788).  ``weight`` is a
   raw little-endian parameter buffer.

Container (verified: all three files parse exactly to their file size)
----------------------------------------------------------------------
    u32  version_len ; char version[version_len]
    u32  num_model
    per model:
        u32  name_len   ; char name[name_len]
        u32  key_len    ; u8   key[key_len]        # 36 = 32 ct + u32 plain_len
        u32  checksum_a
        u32  config_len ; u8   config[config_len]
        u32  checksum_b
        u32  weight_len ; u8   weight[weight_len]

The 16 bytes at file offset 0 are an opaque per-file id; ``u32@0x10`` repeats
the file size ("model file size in your provide model is %d, but what we
expection size is: %d", rodata 0xd66b62).
"""

from __future__ import annotations

import struct
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Tuple

U32 = struct.Struct("<I")

# ---------------------------------------------------------------------------
# auth codes.  Extracted from libeffect.so (inline 48-char immediates built
# right before each `bl 0xa97bdc`; see tools in GAP_CLOSURE.md).  The site is
# the ELF address of the builder call inside the model loader.
# ---------------------------------------------------------------------------
AUTH_CODES: Dict[str, str] = {
    # tt_face_v10.0.model  (smash face base model loader, ELF 0x68a628)
    "tt_face_v10.0": "vVwfzV4dKMAdxYEcLJ15Bb5tm0b4I7HhozHgk1z9vYW5GIov",
    # tt_face_extra_v13.0.model (smash face extra model loader, ELF 0x68b6f8)
    "v13.0": "WaPdHZfZiMCQM2Ds6xMz9Rpva068vCyFMHPryDt0dhfUONtS",
}

# Number of bytes of the auth code that become the AES key (ELF 0x6b2998:
# `mov w2, #0x20` right before `bl 312cd0`).
AUTH_KEY_BYTES = 32


# ---------------------------------------------------------------------------
# AES-256-ECB.  Pure-python fallback so this module has no hard dependency;
# `cryptography` is used when importable (same result, faster).
# ---------------------------------------------------------------------------
def _xtime(a: int) -> int:
    a <<= 1
    return (a ^ 0x11B) & 0xFF if a & 0x100 else a


def _gmul(a: int, b: int) -> int:
    r = 0
    for _ in range(8):
        if b & 1:
            r ^= a
        b >>= 1
        a = _xtime(a)
    return r


def _build_sbox() -> Tuple[List[int], List[int]]:
    sbox = [0] * 256
    p = q = 1
    while True:
        # multiply p by 3
        p = p ^ ((p << 1) & 0xFF) ^ (0x1B if p & 0x80 else 0)
        # divide q by 3
        q ^= q << 1
        q ^= q << 2
        q ^= q << 4
        q &= 0xFF
        if q & 0x80:
            q ^= 0x09
        x = q ^ ((q << 1) | (q >> 7)) ^ ((q << 2) | (q >> 6)) \
            ^ ((q << 3) | (q >> 5)) ^ ((q << 4) | (q >> 4))
        sbox[p] = (x ^ 0x63) & 0xFF
        if p == 1:
            break
    sbox[0] = 0x63
    inv = [0] * 256
    for i, v in enumerate(sbox):
        inv[v] = i
    return sbox, inv


SBOX, INV_SBOX = _build_sbox()
RCON = [0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36, 0x6C, 0xD8]


def _expand_key(key: bytes) -> List[List[int]]:
    nk = len(key) // 4
    assert nk in (4, 6, 8), "AES key must be 16/24/32 bytes"
    nr = nk + 6
    w = [list(key[4 * i:4 * i + 4]) for i in range(nk)]
    for i in range(nk, 4 * (nr + 1)):
        t = list(w[i - 1])
        if i % nk == 0:
            t = t[1:] + t[:1]                      # RotWord
            t = [SBOX[b] for b in t]               # SubWord
            t[0] ^= RCON[i // nk - 1]              # Rcon
        elif nk > 6 and i % nk == 4:
            t = [SBOX[b] for b in t]
        w.append([a ^ b for a, b in zip(w[i - nk], t)])
    return [sum(w[4 * r:4 * r + 4], []) for r in range(nr + 1)]


def _decrypt_block(rk: List[List[int]], block: bytes) -> bytes:
    nr = len(rk) - 1
    s = [b ^ k for b, k in zip(block, rk[nr])]
    for rnd in range(nr - 1, -1, -1):
        # InvShiftRows
        s = [s[(i + 4 * (i % 4)) % 16] for i in range(16)]
        # InvSubBytes
        s = [INV_SBOX[b] for b in s]
        # AddRoundKey
        s = [a ^ b for a, b in zip(s, rk[rnd])]
        if rnd:
            # InvMixColumns
            out = [0] * 16
            for c in range(4):
                a = s[4 * c:4 * c + 4]
                out[4 * c + 0] = _gmul(a[0], 14) ^ _gmul(a[1], 11) ^ _gmul(a[2], 13) ^ _gmul(a[3], 9)
                out[4 * c + 1] = _gmul(a[0], 9) ^ _gmul(a[1], 14) ^ _gmul(a[2], 11) ^ _gmul(a[3], 13)
                out[4 * c + 2] = _gmul(a[0], 13) ^ _gmul(a[1], 9) ^ _gmul(a[2], 14) ^ _gmul(a[3], 11)
                out[4 * c + 3] = _gmul(a[0], 11) ^ _gmul(a[1], 13) ^ _gmul(a[2], 9) ^ _gmul(a[3], 14)
            s = out
    return bytes(s)


def aes256_ecb_decrypt(key: bytes, data: bytes) -> bytes:
    """AES-256-ECB decrypt; `data` length must be a multiple of 16."""
    if len(data) % 16:
        raise ValueError("ciphertext length not a multiple of 16")
    try:
        from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
        c = Cipher(algorithms.AES(key), modes.ECB()).decryptor()
        return c.update(data) + c.finalize()
    except Exception:
        rk = _expand_key(key)
        return b"".join(_decrypt_block(rk, data[i:i + 16]) for i in range(0, len(data), 16))


# ---------------------------------------------------------------------------
class ContainerError(ValueError):
    pass


@dataclass
class Record:
    name: str
    key_field: bytes          # 36 B: [32 B ciphertext][u32 plaintext_len]
    checksum_a: int
    config: bytes             # [ciphertext][u32 plaintext_len]
    checksum_b: int
    weight: bytes
    offset: int

    @property
    def config_plain_len(self) -> int:
        return U32.unpack_from(self.config, len(self.config) - 4)[0]

    def net_key(self, auth_code: str) -> bytes:
        return aes256_ecb_decrypt(auth_code[:AUTH_KEY_BYTES].encode(),
                                  self.key_field[:len(self.key_field) - 4])

    def config_text(self, auth_code: str) -> bytes:
        key = self.net_key(auth_code)
        ct = self.config[:len(self.config) - 4]
        return aes256_ecb_decrypt(key, ct)[:self.config_plain_len]


@dataclass
class ModelPackage:
    path: str
    size: int
    file_id: bytes
    declared_size: int
    version: str
    declared_records: int
    records: List[Record] = field(default_factory=list)

    @classmethod
    def parse(cls, path: str) -> "ModelPackage":
        with open(path, "rb") as fh:
            data = fh.read()
        if len(data) < 0x18:
            raise ContainerError(f"{path}: too short")
        file_id = data[0:16]
        (declared_size,) = U32.unpack_from(data, 0x10)
        if declared_size != len(data):
            raise ContainerError(
                f"{path}: declared size {declared_size} != actual {len(data)}")
        o = 0x14
        (vlen,) = U32.unpack_from(data, o); o += 4
        if not 1 <= vlen <= 127:
            raise ContainerError(f"{path}: bad version_len {vlen}")
        version = data[o:o + vlen].decode("ascii"); o += vlen
        (nrec,) = U32.unpack_from(data, o); o += 4
        if not 1 <= nrec <= 64:
            raise ContainerError(f"{path}: bad num_model {nrec}")

        pkg = cls(path=path, size=len(data), file_id=file_id,
                  declared_size=declared_size, version=version,
                  declared_records=nrec)
        for _ in range(nrec):
            start = o
            (nlen,) = U32.unpack_from(data, o); o += 4
            if not 1 <= nlen <= 64:
                raise ContainerError(f"{path}@{o - 4:#x}: bad name_len {nlen}")
            name = data[o:o + nlen].decode("ascii"); o += nlen
            (klen,) = U32.unpack_from(data, o); o += 4
            key = data[o:o + klen]; o += klen
            (csa,) = U32.unpack_from(data, o); o += 4
            (clen,) = U32.unpack_from(data, o); o += 4
            if clen < 4 or (clen - 4) % 16:
                raise ContainerError(
                    f"{path}@{o - 4:#x}: config_len {clen} not 4 mod 16")
            cfg = data[o:o + clen]; o += clen
            (csb,) = U32.unpack_from(data, o); o += 4
            (wlen,) = U32.unpack_from(data, o); o += 4
            w = data[o:o + wlen]; o += wlen
            pkg.records.append(Record(name=name, key_field=key, checksum_a=csa,
                                      config=cfg, checksum_b=csb, weight=w,
                                      offset=start))
        pkg_bytes_consumed = o
        if pkg_bytes_consumed > len(data):
            raise ContainerError(f"{path}: records overrun the file")
        pkg.trailing = len(data) - pkg_bytes_consumed
        return pkg


# ---------------------------------------------------------------------------
# net description text
# ---------------------------------------------------------------------------
POOL_MODES = {"MAX", "AVE", "STOCHASTIC", "MAX_AVE"}
# Weight element size per `ints[9]` of a weight-bearing layer line.
# 1 -> int8, 2 -> int16 (verified byte-exact on `detect` and `image`).
DTYPE_BYTES = {1: 1, 2: 2, 4: 4}
DTYPE_NP = {1: "i1", 2: "<i2", 4: "<f4"}


@dataclass
class Layer:
    type: str
    name: str
    ints: List[int]
    names: List[str]       # non-integer tokens after the layer type, in order
    line: str

    @property
    def mode(self) -> Optional[str]:
        """Pooling mode string ("MAX"/"AVE"/...), if present."""
        for n in self.names:
            if n in POOL_MODES:
                return n
        return None

    @property
    def bottoms(self) -> List[str]:
        ns = [n for n in self.names if n not in POOL_MODES]
        return ns[:-1] if ns else []

    @property
    def top(self) -> Optional[str]:
        ns = [n for n in self.names if n not in POOL_MODES]
        return ns[-1] if ns else None

    @property
    def dtype_bytes(self) -> int:
        """Weight element size from the layer line (defaults to int8)."""
        if len(self.ints) > 9 and self.ints[9] in DTYPE_BYTES:
            return DTYPE_BYTES[self.ints[9]]
        return 1

    def __repr__(self) -> str:
        return f"<{self.type} {self.name} ints={self.ints} names={self.names}>"


@dataclass
class Net:
    tag: str                  # "" | "B" | "E"  (parseNet only special-cases "E")
    version: int
    declared_layers: int
    magic: Optional[int]
    layers: List[Layer]
    raw: str

    @property
    def input_layer(self) -> Layer:
        return self.layers[0]


_LAYER_TYPES = {
    "data", "DataV2", "Convolution", "DepthwiseSeparableConvolution",
    "DepthwiseSeparableConvolutionV2", "Concat", "Concat2", "Eltwise",
    "Pooling", "PoolingV2", "InnerProduct", "Softmax", "Reshape", "Flatten",
    "ReLU", "Sigmoid", "Tanh", "BatchNorm", "Scale", "Dropout",
    "Deconvolution", "ConvTranspose2d", "Depth2Space", "Space2Depth", "Pad2d",
    "OnnxOp1", "OnnxOp2", "Conv2D", "Conv2DStreaming", "ShuffleNet",
    "Shuffle", "SEScale", "Crop", "Slice", "Split", "Constant", "Clip",
    "HardSwish", "HardSigmoid", "ReluHardSwish", "LeakyRelu", "InstanceNorm",
    "ResizeBilinear", "GridSample", "Transpose", "SparseConv", "MemoryData",
    "EltwiseV2", "PoolingV2", "Data",
}


def parse_net(text: str) -> Net:
    """Parse the decrypted net description text.

    Line 1 may be a one-token tag ("B"/"E"); `IESNN::parseNet` only checks for
    "E" (strcmp at 0x1407xx region, libbytenn).  The next line is
    ``version declared_layers [magic]``.  Every following line is
    ``<LayerType> <name> <ints...> <names...>``; the split between ints and
    names is the first token that is not a decimal integer.
    """
    text = text.replace("\\n", "\n")
    lines = [ln for ln in text.splitlines() if ln.strip()]
    if not lines:
        raise ValueError("empty net text")
    tag = ""
    head = lines[0].split()
    if len(head) == 1 and head[0] in ("B", "E"):
        tag = head[0]
        lines = lines[1:]
    hdr = lines[0].split()
    if len(hdr) < 2:
        raise ValueError(f"bad header line {lines[0]!r}")
    version = int(hdr[0])
    declared = int(hdr[1])
    magic = int(hdr[2]) if len(hdr) > 2 else None

    layers: List[Layer] = []
    for ln in lines[1:]:
        tok = ln.split()
        if len(tok) < 2:
            raise ValueError(f"bad layer line {ln!r}")
        ltype = tok[0]
        if ltype == "data":                 # nameless input layer
            name, rest = "data", tok[1:]
        else:
            name, rest = tok[1], tok[2:]
        ints: List[int] = []
        names: List[str] = []
        for t in rest:
            try:
                ints.append(int(t, 10))
            except ValueError:
                names.append(t)
        layers.append(Layer(ltype, name, ints, names, ln))
    return Net(tag=tag, version=version, declared_layers=declared,
               magic=magic, layers=layers, raw=text)


def load_net(path: str, record: str,
             auth_code: Optional[str] = None) -> Tuple[Net, Record, "ModelPackage"]:
    pkg = ModelPackage.parse(path)
    rec = next((r for r in pkg.records if r.name == record), None)
    if rec is None:
        raise KeyError(f"{path}: no record named {record!r} "
                       f"(have {[r.name for r in pkg.records]})")
    auth = auth_code or AUTH_CODES.get(pkg.version)
    if auth is None:
        raise KeyError(
            f"no auth_code known for model version {pkg.version!r}; "
            f"known: {sorted(AUTH_CODES)}")
    return parse_net(rec.config_text(auth).decode("ascii")), rec, pkg


if __name__ == "__main__":
    import sys
    for p in sys.argv[1:]:
        pkg = ModelPackage.parse(p)
        print(f"{p}: version={pkg.version!r} records={len(pkg.records)} "
              f"trailing={getattr(pkg, 'trailing', 0)}")
        for r in pkg.records:
            print(f"  {r.name:11s} cfg={len(r.config):6d} "
                  f"plain={r.config_plain_len:6d} weight={len(r.weight):7d}")
