"""EAI2 model container parser for Qualcomm's EAI (libeai_floating.so, v2.2).

Reconstructed from the arm64 loader in ``native/libs/libeai_floating.so``:
symbols recovered from the ``.gnu_debugdata`` mini-debug-info, then decompiled
with Ghidra.  Evidence files:

    evidence/ghidra/eai_parse.c        validate_eai_model, initialize_eai_model_info,
                                       initialize_tensor_meta, relocate_tensor,
                                       parse_eai_layers, load_eai_layers,
                                       initialize_io_tensor, calculate_total_layer_size,
                                       so_op_intf_load2 (op-id table)
    evidence/ghidra/eai_tensor_ops.c   tensor_size, network_forward_from, gemm_*
    evidence/ghidra/eai_ops.c          per-op load/forward functions
    evidence/harness/eai_host.c        runs the real library (see run_harness.sh);
                                       its dump is the ground truth this parser is
                                       checked against (``verify.txt``).

File layout (all little endian, model start = first u32 == 'eai2'; the loader
scans the caller buffer at 4-byte steps for that magic):

    +0x00  char[4]  "eai2"
    +0x04  u16      format major (=2)          } validate_eai_model: must be >= 2
    +0x06  u16      format minor (=2)          } (major==2 requires minor>=2)
    +0x08  u8       compiler major             } get_compiler_version(lib+8, lib+10)
    +0x09  u8       -                          }
    +0x0a  u8       compiler minor             }
    +0x0b  u8       -
    +0x0c  u32      unknown, 4 in every shipped blob
    +0x10  u32      model size in bytes (must be <= caller buffer size)
    +0x14  u32      activation/scratch bytes required (runtime adds alignment)
    +0x18  u32      persistent-state bytes required (ditto)
    +0x1c  u32      section count
    +0x20  u32      flags;  byte0x21 = enpu version (must be 0 in this build),
                    byte0x23 is set to 1 by validate_eai_model ("parsed")
    +0x24  u32      reserved (0)
    +0x28  section[n_sections]  { u32 type, u32 size, u32 offset, u32 reserved }

Section types (initialize_eai_model_info switch):

    0  misc table       u32 count, u32 0, u16 count-1 ids   (see `misc`)
    1  tensor metadata  header + 0x50-byte tensor descriptors
    2  constant data    payload for tensors with a constant offset
    3  (never handled by the loader -> logged "ignore the unrecognized section")
    4  layer table      u32 count, u32 0, then variable-size layer records
    5  layer I/O lists  offset table of u16 tensor-id lists per layer + global I/O
    6  (handled by the loader but no shipped blob has one)

Tensor descriptor (0x50 bytes, first descriptor at section1_offset + 0x20):

    +0x00 u64  dims pointer       (runtime field; file value is always 0)
    +0x08 u32  flags              bit0 = constant/weight, bit2 = activation,
                                  bit7/bit30 = persistent state, bit22 = ?
    +0x0c u32  ndim (low nibble)
    +0x10 u32  reserved
    +0x14 u32  dims-form magic = 0x00043210 (all shipped tensors)
    +0x18 u32  dims[0..4]         (up to 5 dimensions)
    +0x2c u32  data offset inside section 2 (constants) or inside the scratch /
                                  persistent pool; 0xffffffff = no data
    +0x30 u64  data pointer       (runtime field)
    +0x38 u32  byte size          (== prod(dims) * elem_size)
    +0x3c u32  dtype flags        low nibble = element size in bytes (4)
    +0x40 u32  layout/stride hint (derived, not needed to run)
    +0x44 u32  always 1
    +0x48 u64  reserved

Tensor metadata header (8 u32 at section1_offset):
    +0x00 n_tensors, +0x04 0x20 (descriptor area offset),
    +0x08 offset of group1, +0x0c offset of group2,
    +0x10 n_group0 (constant tensors), +0x14 n_group1 (scratch/activations),
    +0x18 n_group2 (persistent state), +0x1c 0

Layer record (variable length; section4_offset + 8, stride = record[1]):

    +0x00 u32  op id (<= 0x46; see OP_NAMES)
    +0x04 u32  record size in bytes (>= 16)
    +0x08 u32  flags; bit 8 = "has offline params", bit 19 = fast-math/inline
    +0x0c u16  tensor-group index used by the type-0 misc table
    +0x0e u8   sub-type / layout selector (op specific)
    +0x0f u8   op specific flag
    +0x10 ..   record_size-16 bytes of op parameters

Layer I/O lists (section5):
    u32 count (= n_layers + 1), u32 global_io_offset, u32 layer_offsets[count-1]
    each list: u16 n_in, u16 n_out, u16 in_ids[n_in], u16 out_ids[n_out]
    0xffff marks an unused slot (e.g. LSTM's optional weight tensors).
"""

from __future__ import annotations

import json
import os
import struct
from dataclasses import dataclass, field

import numpy as np

MAGIC = b"eai2"

SECTION_TYPES = {
    0: "misc",
    1: "tensors",
    2: "constants",
    3: "unrecognised",
    4: "layers",
    5: "layer_io",
    6: "extra",
}

# so_op_intf_load2 (libeai_floating.so @0xef98): op id -> loader/forward family
OP_NAMES = {
    0x00: "conv", 0x02: "relu", 0x03: "logsoftmax", 0x04: "maxout", 0x05: "maxpool",
    0x07: "reshape", 0x08: "lstm", 0x0A: "gru", 0x0C: "transpose", 0x0E: "batchnorm",
    0x0F: "quantize", 0x10: "add", 0x11: "matmul", 0x12: "div", 0x14: "no_op",
    0x15: "reducemean", 0x17: "framer", 0x18: "slice", 0x19: "shape", 0x1A: "squeeze",
    0x1B: "unsqueeze", 0x1C: "mul", 0x1D: "max", 0x1E: "concat", 0x20: "softmax",
    0x21: "tanh", 0x22: "reduce", 0x23: "conv1d", 0x24: "sub", 0x25: "pow",
    0x26: "clip", 0x27: "sigmoid", 0x29: "gather", 0x2A: "log", 0x2B: "sqrt",
    0x2F: "exp", 0x32: "gemm", 0x34: "globalavgpool", 0x35: "averagepool",
    0x36: "argmax", 0x37: "argmin", 0x39: "convtranspose", 0x3A: "pad",
    0x3B: "prelu", 0x3D: "superconv", 0x3E: "supergemm", 0x3F: "superadd",
    0x40: "supersub", 0x41: "supermul", 0x43: "superdeconv", 0x44: "flowcontrol",
    0x45: "superavgpool", 0x46: "supermaxpool",
}

# in-memory layer struct sizes from calculate_total_layer_size (@0xed9c); the
# per-op loaders also store (size - 8) at layer+4, e.g. gemm_load -> 0xa8.
LAYER_STRUCT_SIZE = {
    0x00: 0xD8, 0x02: 0x70, 0x03: 0x98, 0x04: 0x78, 0x05: 0xD0, 0x08: 0xD0,
    0x0A: 0xD0, 0x0C: 0xA0, 0x0E: 0xA0, 0x0F: 0x98, 0x10: 0x70, 0x11: 0xA0,
    0x12: 0xA0, 0x14: 0x70, 0x15: 0x78, 0x17: 0x98, 0x18: 0xC0, 0x19: 0x70,
    0x1A: 0x78, 0x1B: 0x78, 0x1C: 0xA0, 0x1D: 0xB8, 0x1E: 0x78, 0x20: 0x98,
    0x21: 0x70, 0x22: 0x80, 0x23: 0xB0, 0x24: 0xA0, 0x25: 0xA0, 0x26: 0x80,
    0x27: 0x70, 0x29: 0x78, 0x2A: 0x70, 0x2B: 0x70, 0x2F: 0x70, 0x32: 0xA8,
    0x34: 0x80, 0x35: 0xD0, 0x36: 0x80, 0x37: 0x80, 0x39: 0xF8, 0x3A: 0xA8,
    0x3B: 0x70, 0x3D: 0x108, 0x3E: 0xA8, 0x3F: 0x98, 0x40: 0x98, 0x41: 0x98,
    0x43: 0xF8, 0x44: 0x80, 0x45: 0xE8, 0x46: 0xE8,
}

# fused "super" ops carry a layer struct of the base op plus an inline tail
SUPER_OF = {0x3D: 0x00, 0x3E: 0x11, 0x3F: 0x10, 0x40: 0x24, 0x41: 0x1C,
            0x43: 0x39, 0x45: 0x35, 0x46: 0x05}


class EaiError(RuntimeError):
    pass


@dataclass
class Tensor:
    id: int
    group: int          # 0 constant, 1 scratch, 2 persistent
    flags: int
    ndim: int
    dims: tuple
    data_off: int       # offset inside its pool, 0xFFFFFFFF = none
    nbytes: int
    dtype: int
    magic: int = 0
    padded: bool = False
    shape: tuple = ()
    name: str = ""

    @property
    def elem_size(self) -> int:
        return self.dtype & 0xF

    @property
    def is_constant(self) -> bool:
        return bool(self.flags & 1)

    def __post_init__(self):
        # tensor_size() multiplies dims[0..ndim-1]; ndim may be < len(dims)
        self.shape = tuple(int(d) for d in self.dims[: self.ndim])


@dataclass
class Layer:
    index: int
    op_id: int
    record_size: int
    flags: int
    group_index: int    # u16 at +0x0c -- index used by the misc section
    subtype: int        # u8 at +0x0e
    op_flag: int        # u8 at +0x0f
    params: bytes       # record_size - 16 raw bytes
    in_ids: tuple = ()
    out_ids: tuple = ()
    name: str = ""
    extra: dict = field(default_factory=dict)

    @property
    def op(self) -> str:
        return OP_NAMES.get(self.op_id, "op_0x%02x" % self.op_id)

    def u32(self, off: int) -> int:
        return struct.unpack_from("<I", self.params, off - 0x10)[0]

    def float(self, off: int) -> float:
        return struct.unpack_from("<f", self.params, off - 0x10)[0]

    def u16(self, off: int) -> int:
        return struct.unpack_from("<H", self.params, off - 0x10)[0]


class EaiModel:
    """Parsed EAI2 blob."""

    def __init__(self, data: bytes, name: str = ""):
        self.name = name
        self.raw = bytes(data)
        self.start = self._find_magic()
        self._parse_header()
        self._parse_sections()
        self._parse_tensors()
        self._parse_layers()
        self._parse_io()

    # -- header ---------------------------------------------------------
    def _find_magic(self) -> int:
        buf = self.raw
        for off in range(0, max(0, len(buf) - 4) + 1, 4):
            if buf[off:off + 4] == MAGIC:
                return off
        raise EaiError("no eai2 magic")

    def _u32(self, off: int) -> int:
        return struct.unpack_from("<I", self.raw, self.start + off)[0]

    def _parse_header(self):
        b = self.raw
        s = self.start
        if len(b) - s < 0x2C:
            raise EaiError("truncated header")
        self.version = (struct.unpack_from("<H", b, s + 4)[0],
                        struct.unpack_from("<H", b, s + 6)[0])
        self.compiler = (b[s + 8], b[s + 10])
        self.unknown_0c = self._u32(0x0C)
        self.size = self._u32(0x10)
        self.scratch_bytes = self._u32(0x14)
        self.persistent_bytes = self._u32(0x18)
        self.n_sections = self._u32(0x1C)
        self.header_flags = self._u32(0x20)
        # byte 0x20/0x21: model target.  The shipped *floating* runtime
        # (libeai_floating.so) only accepts 0 ("Unsupported model enpu
        # version %d, runtime enpu version %d" otherwise); 3 and 4 mark
        # builds compiled for the ENPU/NPU back-end.
        self.targets = (b[s + 0x20], b[s + 0x21])
        self.enpu_version = b[s + 0x21]
        if self.version[0] < 2 or (self.version[0] == 2 and self.version[1] < 2):
            raise EaiError("unsupported eai format %d.%d" % self.version)
        self.runnable_by_floating = self.refusal_reason() is None
        if self.size > len(b) - s:
            raise EaiError("model size %d > buffer %d" % (self.size, len(b) - s))
        if (s & 7) != 0:
            raise EaiError("model start %d is not 8-byte aligned" % s)

    def _parse_sections(self):
        self.sections = {}
        self.section_list = []
        base = self.start + 0x28
        total = self.n_sections * 16 + 0x28
        for i in range(self.n_sections):
            stype, size, off, rsvd = struct.unpack_from("<4I", self.raw, base + 16 * i)
            if self.start + off + size > len(self.raw):
                raise EaiError("section %d out of file" % i)
            self.sections.setdefault(stype, []).append((off, size))
            self.section_list.append({"index": i, "type": stype,
                                      "name": SECTION_TYPES.get(stype, "?"),
                                      "offset": off, "size": size})
            total += size
        # validate_eai_model's cheap bound check
        self.size_check = (total <= len(self.raw) - self.start)
        unknown = [st for st in self.sections if st not in (0, 1, 2, 4, 5, 6)]
        if unknown:
            raise EaiError("unrecognised section types %s" % unknown)

    def refusal_reason(self):
        """Why libeai_floating.so refuses this blob (None = it loads).

        Mirrors validate_eai_model() + load_network()'s float check in the order
        the library applies them:
          1. get_compiler_version(model) vs the runtime's own 4.3 build
          2. byte 0x21 ("enpu"/target version) must be 0 for the floating runtime
          3. header word 0x0c bit 2 must be set (float model)
        """
        if self.compiler[0] > 4:
            return "builder"          # "Model with eai_builder version %d.%d ..."
        if self.targets[1] != 0:
            return "enpu"             # "Unsupported model enpu version %d, runtime ..."
        if not (self._u32(0x0C) >> 2) & 1:
            return "fixed-point"      # "Error: fixed point model, float runtime!"
        return None

    def section(self, stype: int):
        lst = self.sections.get(stype)
        return lst[0] if lst else None

    # -- tensors --------------------------------------------------------
    def _parse_tensors(self):
        sec = self.section(1)
        if sec is None:
            raise EaiError("no tensor section")
        off, size = sec
        base = self.start + off
        (self.n_tensors, self.tensor_hdr_off, self.group1_off, self.group2_off,
         self.n_group0, self.n_group1, self.n_group2, _r) = struct.unpack_from(
            "<8I", self.raw, base)
        if self.n_group0 + self.n_group1 + self.n_group2 != self.n_tensors:
            raise EaiError("tensor group counts disagree")
        if self.n_tensors * 0x50 + 0x20 != size:
            raise EaiError("tensor section size mismatch: %d vs %d"
                           % (self.n_tensors * 0x50 + 0x20, size))
        self.tensors = []
        dbase = base + 0x20
        for i in range(self.n_tensors):
            d = dbase + i * 0x50
            (_dp, flags, ndim, _r0, magic, d0, d1, d2, d3, d4,
             data_off, _ptr, nbytes, dtype, _extra, _one, _t0,
             _t1) = struct.unpack_from("<Q10I Q 6I", self.raw, d)
            # +0x14: packed "dims form" tag: five nibbles, one code per
            # dimension, occasionally with bit 23 set.  Shipped values are
            # 0x43210, 0x34210, 0x23410, 0x21430, 0x23140, 0x823410; the
            # per-nibble meaning is not needed to run a graph (gemm_forward is
            # the only reader, and only tests for 0x34210 explicitly).
            if magic & 0xFF000000 not in (0, 0x80000000):
                raise EaiError("tensor %d: unexpected dims tag 0x%x" % (i, magic))
            t = Tensor(id=i,
                       group=0 if i < self.n_group0 else
                             (1 if i < self.n_group0 + self.n_group1 else 2),
                       flags=flags, ndim=ndim & 0xF,
                       dims=(d0, d1, d2, d3, d4),
                       data_off=data_off, nbytes=nbytes, dtype=dtype)
            t.magic = magic
            # tensor_size(): product of dims[0..ndim-1] times element size
            calc = t.elem_size
            for k in range(t.ndim):
                calc *= t.shape[k]
            if calc != nbytes:
                # Quantised (ENPU-target) builds pad tensor payloads to an
                # alignment and encode scale/zero-point after the data, so the
                # plain product only holds for float models.
                t.padded = True
                if self.runnable_by_floating:
                    raise EaiError("tensor %d: size %d != prod(dims)*%d = %d"
                                   % (i, nbytes, t.elem_size, calc))
            self.tensors.append(t)

    def constant_bytes(self, t: Tensor) -> np.ndarray:
        if t.data_off == 0xFFFFFFFF:
            return np.zeros(t.shape, np.float32)
        sec = self.section(2)
        if sec is None:
            raise EaiError("no constant section for tensor %d" % t.id)
        off, size = sec
        if t.data_off + t.nbytes > size:
            raise EaiError("tensor %d constant data out of section" % t.id)
        start = self.start + off + t.data_off
        dt = {4: "<f4", 2: "<f2", 1: "<i1", 8: "<f8"} .get(t.elem_size)
        if dt is None:
            raise EaiError("tensor %d: unsupported element size %d" % (t.id, t.elem_size))
        arr = np.frombuffer(self.raw, dtype=dt, count=int(np.prod(t.shape))
                            if t.shape else 1, offset=start)
        return np.array(arr, dtype=np.float32).reshape(t.shape)

    # -- layers ---------------------------------------------------------
    def _parse_layers(self):
        sec = self.section(4)
        if sec is None:
            raise EaiError("no layer section")
        off, size = sec
        base = self.start + off
        self.n_layers, self.layer_hdr_pad = struct.unpack_from("<2I", self.raw, base)
        p = base + 8
        self.layers = []
        for i in range(self.n_layers):
            op_id, rsz, flags, f3 = struct.unpack_from("<4I", self.raw, p)
            if op_id not in OP_NAMES:
                raise EaiError("layer %d: unknown op id 0x%02x" % (i, op_id))
            if rsz < 16 or p + rsz > base + size:
                raise EaiError("layer %d: bad record size %d" % (i, rsz))
            layer = Layer(index=i, op_id=op_id, record_size=rsz, flags=flags,
                          group_index=f3 & 0xFFFF, subtype=(f3 >> 16) & 0xFF,
                          op_flag=(f3 >> 24) & 0xFF,
                          params=self.raw[p + 16:p + rsz])
            # sanity: the loader's own struct sizes are >= record size
            if op_id in LAYER_STRUCT_SIZE and LAYER_STRUCT_SIZE[op_id] < rsz:
                raise EaiError("layer %d: record %d exceeds known struct %d"
                               % (i, rsz, LAYER_STRUCT_SIZE[op_id]))
            self.layers.append(layer)
            p += rsz
        self.layer_data_end = p - base

    # -- layer i/o lists ------------------------------------------------
    def _parse_io(self):
        sec = self.section(5)
        if sec is None:
            raise EaiError("no layer I/O section")
        off, size = sec
        base = self.start + off
        count, io_off = struct.unpack_from("<2I", self.raw, base)
        offsets = list(struct.unpack_from("<%dI" % (count - 1), self.raw, base + 8))

        def read_list(rel):
            p = base + rel
            n_in, n_out = struct.unpack_from("<2H", self.raw, p)
            ids = struct.unpack_from("<%dH" % (n_in + n_out), self.raw, p + 4)
            return n_in, n_out, ids[:n_in], ids[n_in:]

        self.n_inputs, self.n_outputs, self.input_ids, self.output_ids = read_list(io_off)
        self.misc_io = (self.n_inputs, self.n_outputs)
        if len(offsets) < self.n_layers:
            raise EaiError("I/O offset table shorter than layer count")
        for i, layer in enumerate(self.layers):
            n_in, n_out, ins, outs = read_list(offsets[i])
            layer.in_ids = tuple(-1 if x == 0xFFFF else x for x in ins)
            layer.out_ids = tuple(-1 if x == 0xFFFF else x for x in outs)
            for tid in list(layer.in_ids) + list(layer.out_ids):
                if tid >= self.n_tensors:
                    raise EaiError("layer %d references tensor %d" % (i, tid))
        # misc section (type 0): u32 count, u32 0, u16[count-1] group indexes
        sec0 = self.section(0)
        self.misc = None
        if sec0 is not None:
            off, size = sec0
            p = self.start + off
            cnt, zero = struct.unpack_from("<2I", self.raw, p)
            n_avail = max(0, (size - 8) // 2)
            n = min(cnt - 1, n_avail) if cnt else 0
            ids = struct.unpack_from("<%dH" % n, self.raw, p + 8) if n else ()
            # observed content: 2,3,4,... i.e. a running index per layer; the
            # runtime never reads this table in the code paths we decompiled.
            self.misc = {"count": cnt, "zero": zero, "ids": list(ids),
                         "ids_fit_section": cnt - 1 <= n_avail}

    # -- helpers --------------------------------------------------------
    @property
    def inputs(self):
        return [self.tensors[i] for i in self.input_ids]

    @property
    def outputs(self):
        return [self.tensors[i] for i in self.output_ids]

    def op_histogram(self):
        h = {}
        for l in self.layers:
            h[l.op] = h.get(l.op, 0) + 1
        return h

    def summary(self) -> dict:
        return {
            "name": self.name,
            "bytes": len(self.raw),
            "magic_offset": self.start,
            "version": "%d.%d" % self.version,
            "compiler": "%d.%d" % self.compiler,
            "targets": list(self.targets),
            "runnable_by_libeai_floating": self.runnable_by_floating,
            "refusal_reason": self.refusal_reason(),
            "model_size": self.size,
            "scratch_bytes": self.scratch_bytes,
            "persistent_bytes": self.persistent_bytes,
            "sections": self.section_list,
            "n_tensors": self.n_tensors,
            "tensor_groups": [self.n_group0, self.n_group1, self.n_group2],
            "n_layers": self.n_layers,
            "ops": self.op_histogram(),
            "inputs": [{"id": t.id, "shape": list(t.shape)} for t in self.inputs],
            "outputs": [{"id": t.id, "shape": list(t.shape)} for t in self.outputs],
            "misc": self.misc,
            "layers": [
                {"index": l.index, "op": l.op, "op_id": l.op_id,
                 "record_size": l.record_size, "flags": l.flags,
                 "subtype": l.subtype, "op_flag": l.op_flag,
                 "params_hex": l.params.hex(),
                 "in_ids": list(l.in_ids), "out_ids": list(l.out_ids)}
                for l in self.layers
            ],
            "tensors": [
                {"id": t.id, "group": t.group, "flags": t.flags,
                 "shape": list(t.shape), "dims_raw": list(t.dims),
                 "ndim": t.ndim, "data_off": t.data_off,
                 "nbytes": t.nbytes, "dtype": t.dtype, "magic": t.magic,
                 "padded": t.padded}
                for t in self.tensors
            ],
        }


def load(path: str) -> EaiModel:
    with open(path, "rb") as f:
        name = os.path.basename(path)
        return EaiModel(f.read(), name=name[:-4] if name.endswith(".eai") else name)


def main(argv):
    import sys
    here = os.path.dirname(os.path.abspath(__file__))
    root = os.path.dirname(here)
    eai_dir = os.path.join(root, "models", "eai")
    paths = argv[1:] or sorted(os.path.join(eai_dir, f)
                               for f in os.listdir(eai_dir) if f.endswith(".eai"))
    out = {}
    for p in paths:
        m = load(p)
        s = m.summary()
        out[m.name] = s
        print("%-32s %8d B  v%s  tensors=%-4d groups=%s layers=%-4d"
              % (m.name, len(m.raw), s["version"], s["n_tensors"],
                 s["tensor_groups"], s["n_layers"]))
        print("    sections:", ", ".join("%s(%d B)" % (x["name"], x["size"])
                                         for x in s["sections"]))
        print("    ops:", ", ".join("%s x%d" % kv for kv in sorted(s["ops"].items())))
        print("    in :", [(t.id, t.shape) for t in m.inputs],
              " out:", [(t.id, t.shape) for t in m.outputs])
        print("    misc ids:", s["misc"]["ids"] if s["misc"] else None)
    dest = os.path.join(root, "ir", "eai_parsed.json")
    os.makedirs(os.path.dirname(dest), exist_ok=True)
    with open(dest, "w") as f:
        json.dump(out, f, indent=1)
    print("\nwrote", dest)
    return 0


if __name__ == "__main__":
    import sys
    raise SystemExit(main(sys.argv))
