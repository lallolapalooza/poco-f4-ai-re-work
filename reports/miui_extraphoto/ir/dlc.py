#!/usr/bin/env python3
"""Parser for the Snapdragon NPE (SNPE) ``.dlc`` container found in ExtraPhoto.

Container evidence
------------------
``assets/common/doc_photo/doc_model/dlc/document_model.dlc`` is a ZIP archive:

* ``dlc.metadata`` (227 B, plain text) --
  ``converter-command=snpe-tensorflow-to-dlc ... out_node=['img_output']
  ... input_dim=[['hed_input', '1,512,512,3']]``,
  ``converter-version=1.22.0.212``.
* ``model`` (357151 B) -- 8-byte header ``d5 0a 02 00 | 05 00 00 00``
  (u32 magic 0x00020AD5, u32 version 5) followed by a protobuf wire-format
  payload.  The payload walks cleanly with a generic protobuf reader.

Top level message (field numbers observed):

* ``1``  repeated TensorDescriptor  (100 entries: name + dims)
* ``5``  repeated Layer             (100 entries: the executable graph)
* ``13`` varint = 1                 (model format/version marker)

Layer message:

* ``1`` name, ``2`` layer type id, ``3`` output dims (repeated),
* ``6`` layer index, ``7`` input tensor names (repeated),
* ``8`` output tensor names (repeated), ``9``/``10`` tensor ids,
* ``11``/``12``/``13`` reserved byte blobs (empty here),
* op params live in a type-specific top-level field:
  ``30`` conv/deconv, ``90`` activation, ``60`` elementwise binary,
  ``25`` concat, ``161`` const.

Conv/deconv param message ``30``:
``1,2`` kernel h,w; ``3,4`` stride h,w; ``5,6`` pad h,w;
``7,8`` dilation h,w; ``9`` group; ``10`` pad type; ``11`` out channels;
``20`` repeated TensorParam {``1`` dims, ``3`` {``3`` raw bytes,
``4`` Quantizer}} -- first is the weight tensor, second the bias.

Quantizer: ``1`` bitwidth, ``2`` min, ``3`` max, ``4`` step, ``5`` offset.
Dequantisation used by SNPE for these uint8 buffers::

    x = step * (q + offset)          # q = unsigned byte, offset <= 0

(checked: step*(0+offset) == min and step*(255+offset) == max for every
quantizer in the file).
"""
import json
import struct
import numpy as np

HEADER_SIZE = 8
MAGIC = 0x00020AD5
VERSION = 5

# layer type ids observed in document_model.dlc
T_INPUT = 60
T_CONST = 211
T_CONV = 40
T_DECONV = 70
T_ACTIVATION = 150
T_ADD = 90
T_CONCAT = 30
T_ELEMENTWISE = 95

LAYER_TYPES = {
    T_INPUT: "input",
    T_CONST: "const",
    T_CONV: "conv2d",
    T_DECONV: "deconv2d",
    T_ACTIVATION: "activation",
    T_ADD: "add",
    T_CONCAT: "concat",
    T_ELEMENTWISE: "elementwise_binary",
}

ACTIVATIONS = {2: "sigmoid", 3: "relu", 5: "relu6"}
BINOPS = {4: "div"}


class Msg:
    """One protobuf message: ordered list of (field, wiretype, value)."""

    __slots__ = ("fields",)

    def __init__(self, fields):
        self.fields = fields

    def all(self, num):
        return [v for (n, wt, v, raw) in self.fields if n == num]

    def raw(self, num, default=None):
        """Raw wire payload bytes of the first occurrence of field `num`."""
        for (n, wt, v, raw) in self.fields:
            if n == num and raw is not None:
                return raw
        return default

    def one(self, num, default=None):
        vals = self.all(num)
        return vals[0] if vals else default

    def msg(self, num):
        v = self.one(num)
        return v if isinstance(v, Msg) else None

    def varint(self, num, default=None):
        v = self.one(num)
        return v if isinstance(v, int) else default


def _varint(b, i):
    v = 0
    shift = 0
    while True:
        c = b[i]
        i += 1
        v |= (c & 0x7F) << shift
        if not (c & 0x80):
            return v, i
        shift += 7


def parse_msg(b, start, end):
    """Strict recursive protobuf wire parser; returns Msg or None."""
    fields = []
    i = start
    while i < end:
        try:
            key, i = _varint(b, i)
        except IndexError:
            return None
        num, wt = key >> 3, key & 7
        if num == 0 or wt in (3, 4, 6, 7):
            return None
        if wt == 0:
            v, i = _varint(b, i)
            fields.append((num, "varint", v, None))
        elif wt == 1:
            if i + 8 > end:
                return None
            raw = bytes(b[i:i + 8])
            fields.append((num, "f64", raw, raw))
            i += 8
        elif wt == 2:
            ln, i = _varint(b, i)
            if i + ln > end:
                return None
            payload = bytes(b[i:i + ln])
            sub = parse_msg(b, i, i + ln) if ln >= 2 else None
            fields.append((num, "msg" if sub is not None else "bytes",
                           sub if sub is not None else payload, payload))
            i += ln
        elif wt == 5:
            if i + 4 > end:
                return None
            raw = bytes(b[i:i + 4])
            fields.append((num, "f32", raw, raw))
            i += 4
        else:
            return None
    return Msg(fields)


def f32(raw):
    return struct.unpack("<f", raw)[0]


def quantizer(msg):
    """Decode a Quantizer message -> dict."""
    if msg is None:
        return None
    return {
        "bitwidth": msg.varint(1),
        "min": f32(msg.one(2)),
        "max": f32(msg.one(3)),
        "step": f32(msg.one(4)),
        "offset": f32(msg.one(5)),
    }


def dequantize(blob, q):
    """SNPE uint8 storage -> float32:  x = step * (q + offset)."""
    if blob is None or q is None:
        return None
    a = np.frombuffer(blob, dtype=np.uint8).astype(np.float32)
    return q["step"] * (a + np.float32(q["offset"]))


def tensor_param(msg):
    """TensorParam {dims, data{blob, quantizer}} -> (dims, raw blob, quant)."""
    dims = [v for (n, wt, v, raw) in msg.fields if n == 1 and wt == "varint"]
    data = msg.msg(3)
    blob = None
    q = None
    if data is not None:
        blob = data.raw(3)          # raw wire bytes: never re-parsed as protobuf
        q = quantizer(data.msg(4))
    return dims, blob, q


class DlcModel:
    def __init__(self, raw):
        if len(raw) < HEADER_SIZE:
            raise ValueError("truncated DLC")
        self.magic, self.version = struct.unpack("<II", raw[:HEADER_SIZE])
        if self.magic != MAGIC or self.version != VERSION:
            raise ValueError(f"unexpected DLC header {self.magic:#x}/{self.version}")
        top = parse_msg(raw, HEADER_SIZE, len(raw))
        if top is None:
            raise ValueError("DLC protobuf payload did not parse")
        self.top = top
        self.tensors = {}
        for m in top.all(1):
            name = m.one(1).decode("utf-8")
            dims = [v for (n, wt, v, raw) in m.fields if n == 3 and wt == "varint"]
            self.tensors[name] = {"dims": dims, "type_id": m.varint(2)}
        self.layers = [self._layer(m, i) for i, m in enumerate(top.all(5))]
        self.model_version = top.varint(13)

    @staticmethod
    def _layer(m, idx):
        name = m.one(1).decode("utf-8")
        t = m.varint(2)
        out_dims = [v for (n, wt, v, raw) in m.fields if n == 3 and wt == "varint"]
        inputs = [x.decode("utf-8") for x in m.all(7)]
        outputs = [x.decode("utf-8") for x in m.all(8)]
        L = {
            "index": m.varint(6, idx),
            "name": name,
            "type_id": t,
            "type": LAYER_TYPES.get(t, f"unknown_{t}"),
            "output_dims": out_dims,
            "inputs": inputs,
            "outputs": outputs,
            "params": {},
            "weights": {},
        }
        if t in (T_CONV, T_DECONV):
            p = m.msg(30)
            g = lambda n: p.varint(n)
            L["params"] = {
                "kernel": [g(1), g(2)],
                "stride": [g(3), g(4)],
                "pad": [g(5), g(6)],
                "dilation": [g(7), g(8)],
                "group": g(9),
                "pad_type": g(10),
                "out_channels": g(11),
            }
            # f20 holds one weights TensorParam (f1) and one bias TensorParam (f2)
            wcontainer = p.msg(20)
            if wcontainer is not None:
                wparams = wcontainer.all(1)
                bparams = wcontainer.all(2)
                if wparams:
                    dims, blob, q = tensor_param(wparams[0])
                    L["weights"]["kernel"] = {"dims": dims, "blob": blob, "quant": q}
                if bparams:
                    dims, blob, q = tensor_param(bparams[0])
                    L["weights"]["bias"] = {"dims": dims, "blob": blob, "quant": q}
        elif t == T_ACTIVATION:
            p = m.msg(90)
            aid = p.varint(1)
            extra = [f32(p.one(i)) for i in (2, 3, 4, 5) if p.one(i) is not None]
            L["params"] = {"activation": ACTIVATIONS.get(aid, aid), "extra": extra}
        elif t == T_ADD:
            p = m.msg(60)
            L["params"] = {
                "coeffs": [f32(x) for (n, wt, x, raw) in p.fields if n == 2 and wt == "f32"]
                if p else [],
                "op": p.varint(1) if p else None,
            }
        elif t == T_CONCAT:
            p = m.msg(25)
            L["params"] = {"axis": p.varint(1) if p else None}
        elif t == T_ELEMENTWISE:
            p = m.msg(60)
            L["params"] = {"binop": BINOPS.get(p.varint(1), p.varint(1)) if p else None}
        elif t == T_CONST:
            tp = m.msg(161).msg(1)   # f161 { f1: TensorParam }
            dims, blob, q = tensor_param(tp)
            L["weights"]["value"] = {"dims": dims, "blob": blob, "quant": q}
        return L

    # ---- helpers -------------------------------------------------------
    def weight_arrays(self):
        """Dequantised float32 weights keyed by layer name."""
        out = {}
        for L in self.layers:
            if not L["weights"]:
                continue
            entry = {}
            for k, v in L["weights"].items():
                arr = dequantize(v["blob"], v["quant"])
                if arr is not None:
                    try:
                        entry[k] = arr.reshape(v["dims"])
                    except ValueError:
                        entry[k] = arr
                entry[k + "_quant"] = v["quant"]
                entry[k + "_dims"] = v["dims"]
            out[L["name"]] = entry
        return out

    def graph_json(self):
        return {
            "container": "SNPE DLC (protobuf payload)",
            "magic": hex(self.magic),
            "version": self.version,
            "model_version": self.model_version,
            "input_tensors": {
                n: t["dims"] for n, t in self.tensors.items()
                if n == "hed_input:0"
            },
            "output_tensors": {
                n: t["dims"] for n, t in self.tensors.items()
                if n == "img_output:0"
            },
            "tensors": self.tensors,
            "layers": [
                {k: v for k, v in L.items() if k != "weights"} for L in self.layers
            ],
            "layer_types": LAYER_TYPES,
        }


def load(path):
    with open(path, "rb") as f:
        return DlcModel(f.read())


if __name__ == "__main__":
    import sys
    m = load(sys.argv[1] if len(sys.argv) > 1 else "models/dlc_extract/model")
    print(json.dumps(m.graph_json()["layers"][:2], indent=1)[:2000])
    print("layers", len(m.layers), "tensors", len(m.tensors))
