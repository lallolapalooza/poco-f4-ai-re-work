#!/usr/bin/env python3
"""Parse a TFLite flatbuffer into a deterministic, TensorFlow-free JSON IR.

Uses the flatc-generated Python bindings that live next to this file in
``tools/tflite``.  No TensorFlow import, ever.

Public API (used by tflite2jax.py / verify_tflite_jax.py):

    ir = load_model("model.tflite")          # ModelIR
    ir.to_json()                             # deterministic dict
    ir.dump_json(path)                       # write JSON
    ir.subgraph(0)                           # SubGraphIR
    ir.signatures                            # list[SignatureIR]
    ir.constant(0, tensor_index)             # np.ndarray | None (raw const bytes)
    ir.constants(0)                          # dict[tensor_index] -> np.ndarray
    supported_by_jax(op_name)                # bool
    ir.unsupported(0)                        # list[dict] with index/op/reason

The JSON is stable: keys are sorted, tensors are indexed by flatbuffer order,
lists mirror flatbuffer vector order.
"""

from __future__ import annotations

import argparse
import importlib
import json
import os
import sys
from dataclasses import dataclass, field
from typing import Any, Dict, List, Optional, Sequence, Tuple

import numpy as np

_HERE = os.path.dirname(os.path.abspath(__file__))
if _HERE not in sys.path:
    sys.path.insert(0, _HERE)

from tflite.Model import Model  # noqa: E402
from tflite.BuiltinOperator import BuiltinOperator  # noqa: E402
from tflite.BuiltinOptions import BuiltinOptions  # noqa: E402
from tflite.BuiltinOptions2 import BuiltinOptions2  # noqa: E402
from tflite.TensorType import TensorType  # noqa: E402


# ---------------------------------------------------------------------------
# Op support registry
# ---------------------------------------------------------------------------
# Single source of truth shared with tflite2jax.py.  Every name here has an
# implementation in tflite2jax.OPS.  Keeping one list avoids the IR and the
# converter disagreeing about what is convertible.
SUPPORTED_BUILTIN_OPS: Tuple[str, ...] = (
    "ABS",
    "ADD",
    "ARG_MAX",
    "ARG_MIN",
    "AVERAGE_POOL_2D",
    "BATCH_MATMUL",
    "CAST",
    "CONCATENATION",
    "CONV_2D",
    "COS",
    "DEPTHWISE_CONV_2D",
    "DEPTH_TO_SPACE",
    "DEQUANTIZE",
    "DIV",
    "ELU",
    "EQUAL",
    "EXP",
    "EXPAND_DIMS",
    "FILL",
    "FLOOR",
    "FLOOR_DIV",
    "FULLY_CONNECTED",
    "GATHER",
    "GATHER_ND",
    "GREATER",
    "GREATER_EQUAL",
    "HARD_SWISH",
    "LEAKY_RELU",
    "LESS",
    "LESS_EQUAL",
    "LOG",
    "LOGICAL_AND",
    "LOGICAL_NOT",
    "LOGICAL_OR",
    "LOGISTIC",
    "MAXIMUM",
    "MAX_POOL_2D",
    "MEAN",
    "MINIMUM",
    "MUL",
    "NEG",
    "NOT_EQUAL",
    "ONE_HOT",
    "PACK",
    "PAD",
    "PADV2",
    "POW",
    "QUANTIZE",
    "REDUCE_MAX",
    "REDUCE_MIN",
    "REDUCE_PROD",
    "RELU",
    "RELU6",
    "RELU_N1_TO_1",
    "RESHAPE",
    "RESIZE_BILINEAR",
    "RESIZE_NEAREST_NEIGHBOR",
    "RSQRT",
    "SELECT",
    "SHAPE",
    "SIN",
    "SLICE",
    "SOFTMAX",
    "SPACE_TO_DEPTH",
    "SQUARE",
    "SQUARED_DIFFERENCE",
    "SQRT",
    "SQUEEZE",
    "STRIDED_SLICE",
    "SUB",
    "SUM",
    "TANH",
    "TILE",
    "TOPK_V2",
    "TRANSPOSE",
    "UNPACK",
    "ZEROS_LIKE",
)
SUPPORTED_SET = frozenset(SUPPORTED_BUILTIN_OPS)

# Ops that make a subgraph stateful (variables / checkpoint IO).  These are
# reported explicitly instead of being silently skipped.
STATEFUL_OPS = frozenset(
    {
        "ASSIGN_VARIABLE",
        "CALL_ONCE",
        "READ_VARIABLE",
        "VAR_HANDLE",
        "HASHTABLE",
        "HASHTABLE_FIND",
        "HASHTABLE_IMPORT",
        "HASHTABLE_SIZE",
    }
)

# Tensor dtypes we can materialise with numpy.
DTYPE_TO_NUMPY: Dict[str, Optional[str]] = {
    "FLOAT32": "<f4",
    "FLOAT16": "<f2",
    "FLOAT64": "<f8",
    "INT8": "i1",
    "INT16": "<i2",
    "INT32": "<i4",
    "INT64": "<i8",
    "UINT8": "u1",
    "UINT16": "<u2",
    "UINT32": "<u4",
    "UINT64": "<u8",
    "BOOL": "?",
    "COMPLEX64": "<c8",
    "COMPLEX128": "<c16",
    # STRING / RESOURCE / VARIANT / INT4 are not plain numpy arrays.
    "STRING": None,
    "RESOURCE": None,
    "VARIANT": None,
    "INT4": None,
}


def _enum_maps(cls) -> Dict[int, str]:
    return {v: k for k, v in vars(cls).items() if isinstance(v, int)}


_OP_NAMES = _enum_maps(BuiltinOperator)
_OPTS_NAMES = _enum_maps(BuiltinOptions)
_OPTS2_NAMES = _enum_maps(BuiltinOptions2)
_TYPE_NAMES = _enum_maps(TensorType)

# Option fields whose integer value is an enum we translate to a name.
_ENUM_FIELDS = {
    "Padding": "Padding",
    "FusedActivationFunction": "ActivationFunctionType",
    "Activation": "ActivationFunctionType",
    "DataFormat": "DataFormat",
    "WeightsFormat": "FullyConnectedOptionsWeightsFormat",
    "InDataType": "TensorType",
    "OutDataType": "TensorType",
    "OutputType": "TensorType",
    "OutType": "TensorType",
    "Type": "TensorType",
    "Format": "CustomOptionsFormat",
}
_ENUM_CLASS_CACHE: Dict[str, Dict[int, str]] = {}


def _enum_name(field_name: str, value: Any) -> Any:
    cls_name = _ENUM_FIELDS.get(field_name)
    if cls_name is None or not isinstance(value, int):
        return value
    names = _ENUM_CLASS_CACHE.get(cls_name)
    if names is None:
        try:
            cls = importlib.import_module(f"tflite.{cls_name}").__dict__[cls_name]
        except (ImportError, KeyError):
            cls = None
        names = _enum_maps(cls) if cls is not None else {}
        _ENUM_CLASS_CACHE[cls_name] = names
    # Only translate when the field's enum actually defines the value;
    # otherwise the field is an ordinary integer.
    return names.get(value, value) if names else value


# ---------------------------------------------------------------------------
# Data classes
# ---------------------------------------------------------------------------
@dataclass
class Quantization:
    scale: List[float] = field(default_factory=list)
    zero_point: List[int] = field(default_factory=list)
    quantized_dimension: int = 0
    min: List[float] = field(default_factory=list)
    max: List[float] = field(default_factory=list)

    @property
    def is_quantized(self) -> bool:
        return bool(self.scale)

    @property
    def scheme(self) -> str:
        if not self.scale:
            return "none"
        return "per_channel" if len(self.scale) > 1 else "per_tensor"

    def to_json(self) -> Dict[str, Any]:
        return {
            "scale": [float(v) for v in self.scale],
            "zero_point": [int(v) for v in self.zero_point],
            "quantized_dimension": int(self.quantized_dimension),
            "min": [float(v) for v in self.min],
            "max": [float(v) for v in self.max],
            "scheme": self.scheme,
        }


@dataclass
class TensorIR:
    index: int
    name: str
    shape: List[int]
    shape_signature: List[int]
    dtype: str
    buffer_index: int
    buffer_bytes: int
    is_variable: bool
    quantization: Quantization
    # Populated for constant tensors only.
    data: Optional[np.ndarray] = None
    # Per-channel dequantization helper, filled by tflite2jax.
    stats: Optional[Dict[str, float]] = None

    @property
    def is_constant(self) -> bool:
        return self.data is not None and self.data.size > 0

    @property
    def numpy_dtype(self) -> Optional[str]:
        return DTYPE_TO_NUMPY.get(self.dtype)

    def to_json(self) -> Dict[str, Any]:
        d: Dict[str, Any] = {
            "index": self.index,
            "name": self.name,
            "shape": list(self.shape),
            "shape_signature": list(self.shape_signature),
            "dtype": self.dtype,
            "buffer_index": self.buffer_index,
            "buffer_bytes": self.buffer_bytes,
            "is_constant": bool(self.is_constant),
            "is_variable": bool(self.is_variable),
            "quantization": self.quantization.to_json(),
        }
        if self.stats is not None:
            d["constant_stats"] = dict(self.stats)
        return d


@dataclass
class OperatorIR:
    index: int
    opcode_index: int
    op: str
    builtin_code: Optional[int]
    custom_code: Optional[str]
    opcode_version: int
    inputs: List[int]
    outputs: List[int]
    intermediates: List[int]
    options_type: Optional[str]
    options: Dict[str, Any]
    custom_options_hex: Optional[str]

    def to_json(self) -> Dict[str, Any]:
        return {
            "index": self.index,
            "opcode_index": self.opcode_index,
            "op": self.op,
            "builtin_code": self.builtin_code,
            "custom_code": self.custom_code,
            "opcode_version": self.opcode_version,
            "inputs": list(self.inputs),
            "outputs": list(self.outputs),
            "intermediates": list(self.intermediates),
            "options_type": self.options_type,
            "options": dict(self.options),
            "custom_options_hex": self.custom_options_hex,
        }


@dataclass
class SubGraphIR:
    index: int
    name: Optional[str]
    tensors: List[TensorIR]
    inputs: List[int]
    outputs: List[int]
    operators: List[OperatorIR]
    role: str = "inference"

    def tensor(self, index: int) -> TensorIR:
        return self.tensors[index]

    @property
    def const_map(self) -> Dict[int, np.ndarray]:
        return {t.index: t.data for t in self.tensors if t.is_constant}

    @property
    def is_stateful(self) -> bool:
        return any(op.op in STATEFUL_OPS for op in self.operators)

    def unsupported(self) -> List[Dict[str, Any]]:
        return unsupported_ops(self)

    def _io_json(self, index: int) -> Dict[str, Any]:
        t = self.tensor(index)
        return {
            "tensor_index": t.index,
            "name": t.name,
            "shape": list(t.shape),
            "dtype": t.dtype,
            "quantization": t.quantization.to_json(),
        }

    def to_json(self) -> Dict[str, Any]:
        return {
            "index": self.index,
            "name": self.name,
            "role": self.role,
            "stateful": self.is_stateful,
            "inputs": [self._io_json(i) for i in self.inputs],
            "outputs": [self._io_json(i) for i in self.outputs],
            "input_indices": list(self.inputs),
            "output_indices": list(self.outputs),
            "operators": [op.to_json() for op in self.operators],
            "tensors": [t.to_json() for t in self.tensors],
            "unsupported_ops": self.unsupported(),
        }


@dataclass
class SignatureIR:
    key: str
    subgraph_index: int
    inputs: List[Tuple[str, int]]  # (name, tensor_index)
    outputs: List[Tuple[str, int]]

    def to_json(self) -> Dict[str, Any]:
        return {
            "key": self.key,
            "subgraph_index": self.subgraph_index,
            "inputs": [{"name": n, "tensor_index": i} for n, i in self.inputs],
            "outputs": [{"name": n, "tensor_index": i} for n, i in self.outputs],
        }


@dataclass
class ModelIR:
    path: str
    version: int
    description: Optional[str]
    operator_codes: List[Dict[str, Any]]
    subgraphs: List[SubGraphIR]
    signatures: List[SignatureIR]
    buffer_count: int
    file_size: int
    _buf: bytes = field(repr=False, default=b"")

    # -- helpers ----------------------------------------------------------
    def subgraph(self, index: int) -> SubGraphIR:
        if index < 0 or index >= len(self.subgraphs):
            raise IndexError(
                f"subgraph {index} out of range (model has {len(self.subgraphs)})"
            )
        return self.subgraphs[index]

    def constant(self, sg_index: int, tensor_index: int) -> Optional[np.ndarray]:
        if tensor_index < 0:
            return None
        t = self.subgraph(sg_index).tensor(tensor_index)
        return t.data

    def constants(self, sg_index: int) -> Dict[int, np.ndarray]:
        return self.subgraph(sg_index).const_map

    def signature(self, key: Optional[str] = None) -> Optional[SignatureIR]:
        if not self.signatures:
            return None
        if key is None:
            return self.signatures[0]
        for s in self.signatures:
            if s.key == key:
                return s
        raise KeyError(f"no signature {key!r}; available: {[s.key for s in self.signatures]}")

    def to_json(self) -> Dict[str, Any]:
        return {
            "format": "tflite_ir/1",
            "path": self.path,
            "file_size": self.file_size,
            "model_version": self.version,
            "description": self.description,
            "buffer_count": self.buffer_count,
            "operator_codes": [dict(c) for c in self.operator_codes],
            "signatures": [s.to_json() for s in self.signatures],
            "subgraphs": [s.to_json() for s in self.subgraphs],
            "supported_ops": sorted(SUPPORTED_SET),
        }

    def dump_json(self, out_path: str) -> None:
        payload = json.dumps(self.to_json(), indent=2, sort_keys=True)
        with open(out_path, "w", encoding="utf-8") as f:
            f.write(payload + "\n")


# ---------------------------------------------------------------------------
# Flatbuffer reading
# ---------------------------------------------------------------------------
def _str(b) -> Optional[str]:
    if b is None:
        return None
    if isinstance(b, bytes):
        return b.decode("utf-8", "replace")
    return str(b)


def _vec(getter, length_getter) -> List[Any]:
    n = length_getter()
    if not n:
        return []
    out = []
    for j in range(n):
        v = getter(j)
        if isinstance(v, np.generic):
            v = v.item()
        out.append(v)
    return out


def _decode_options(union_type: int, union_table, name_maps) -> Tuple[Optional[str], Dict[str, Any]]:
    if not union_type:
        return None, {}
    cls_name = name_maps.get(union_type)
    if cls_name is None:
        return f"Unknown({union_type})", {}
    try:
        cls = importlib.import_module(f"tflite.{cls_name}").__dict__[cls_name]
    except (ImportError, KeyError):
        cls = None
    if cls is None or union_table is None:
        # Options type known but object missing: report the type only.
        return cls_name, {}
    obj = cls()
    obj.Init(union_table.Bytes, union_table.Pos)
    out: Dict[str, Any] = {}
    for attr in dir(cls):
        if not attr or not attr[0].isupper():
            continue
        if attr in ("Init", "GetRootAs", f"GetRootAs{cls_name}", f"{cls_name}BufferHasIdentifier"):
            continue
        if attr.endswith("AsNumpy") or attr.endswith("IsNone"):
            continue
        if attr.endswith("Length") and not attr.endswith("VectorLength"):
            base = attr[: -len("Length")]
            try:
                n = getattr(obj, attr)()
            except Exception:
                continue
            if not n:
                continue
            vals = []
            for j in range(n):
                v = getattr(obj, base)(j)
                if isinstance(v, np.generic):
                    v = v.item()
                vals.append(_enum_name(base, v) if j == 0 else v)
            out[base] = vals
            continue
        if attr.startswith("GetRootAs"):
            continue
        try:
            v = getattr(obj, attr)()
        except Exception:
            continue
        if isinstance(v, np.generic):
            v = v.item()
        if isinstance(v, (bool, int, float)):
            out[attr] = _enum_name(attr, v)
        elif isinstance(v, bytes):
            out[attr] = v.hex()
        else:
            # Ignore unions / tables / strings we do not model here.
            continue
    return cls_name, out


def _decode_quantization(q) -> Quantization:
    if q is None:
        return Quantization()
    scale = [float(v) for v in _vec(q.Scale, q.ScaleLength)]
    zp: List[int] = []
    if q.ZeroPointLength():
        for j in range(q.ZeroPointLength()):
            v = q.ZeroPoint(j)
            zp.append(int(v.item() if isinstance(v, np.generic) else v))
    mn = [float(v) for v in _vec(q.Min, q.MinLength)]
    mx = [float(v) for v in _vec(q.Max, q.MaxLength)]
    return Quantization(
        scale=scale,
        zero_point=zp,
        quantized_dimension=int(q.QuantizedDimension()),
        min=mn,
        max=mx,
    )


def _decode_tensor_data(raw: Optional[bytes], dtype: str, shape: Sequence[int]) -> Optional[np.ndarray]:
    np_dtype = DTYPE_TO_NUMPY.get(dtype)
    if raw is None or not raw or np_dtype is None:
        return None
    try:
        arr = np.frombuffer(raw, dtype=np.dtype(np_dtype))
    except ValueError:
        return None
    shape = [int(s) for s in shape]
    expected = int(np.prod(shape)) if shape else 1
    if expected == 0:
        return np.zeros(shape, dtype=np.dtype(np_dtype))
    if arr.size == expected:
        return arr.reshape(shape).copy()
    if arr.size > expected:
        # TFLite sometimes pads buffers; only the leading elements are data.
        return arr[:expected].reshape(shape).copy()
    return arr.copy()


def _stats(arr: np.ndarray) -> Optional[Dict[str, float]]:
    if arr.size == 0 or arr.dtype.kind not in "biufc":
        return None
    if arr.dtype.kind == "c":
        return None
    # float64 accumulation keeps the JSON stable and meaningful.
    a = arr.astype(np.float64, copy=False)
    return {
        "min": float(a.min()),
        "max": float(a.max()),
        "mean": float(a.mean()),
    }


def _classify_role(name: Optional[str], ops: Sequence[str]) -> str:
    """Best-effort role used for multi-subgraph / SavedModel conversions."""
    lname = (name or "").lower()
    oset = set(ops)
    if "train" in lname:
        return "train"
    if "restore" in lname:
        return "restore"
    if "save" in lname:
        return "save"
    if "init" in lname or "noop" in lname or "no_op" in lname:
        return "init"
    if "infer" in lname or "serving" in lname or "predict" in lname:
        return "inference"
    has_vars = bool(oset & {"VAR_HANDLE", "READ_VARIABLE", "ASSIGN_VARIABLE"})
    if "CALL_ONCE" in oset and not has_vars:
        return "init"
    if has_vars:
        # Training graphs contain gradient machinery, checkpoint graphs only
        # move variables around.
        grad_ops = {"NEG", "SQRT", "SQUARE", "REDUCE_PROD", "SUM", "MEAN"}
        if len(grad_ops & oset) >= 2:
            return "train"
        if "ASSIGN_VARIABLE" in oset:
            return "save" if "READ_VARIABLE" not in oset else "restore"
        return "inference"
    return "inference"


def load_model(path: str, with_stats: bool = True) -> ModelIR:
    with open(path, "rb") as f:
        buf = f.read()
    model = Model.GetRootAsModel(buf, 0)
    if model.SubgraphsIsNone():
        raise ValueError(f"{path}: not a TFLite flatbuffer (no subgraphs)")

    opcodes: List[Dict[str, Any]] = []
    for i in range(model.OperatorCodesLength()):
        oc = model.OperatorCodes(i)
        builtin_code = int(oc.BuiltinCode())
        deprecated = int(oc.DeprecatedBuiltinCode())
        effective = builtin_code if builtin_code else deprecated
        name = _OP_NAMES.get(effective, f"CUSTOM_{effective}")
        custom = _str(oc.CustomCode())
        if custom:
            name = "CUSTOM"
        opcodes.append(
            {
                "index": i,
                "builtin_code": effective,
                "builtin_name": name,
                "custom_code": custom,
                "version": int(oc.Version()),
            }
        )

    subgraphs: List[SubGraphIR] = []
    for si in range(model.SubgraphsLength()):
        sg = model.Subgraphs(si)
        # Buffers are shared by all subgraphs.
        tensors: List[TensorIR] = []
        for ti in range(sg.TensorsLength()):
            t = sg.Tensors(ti)
            shape = [int(v) for v in _vec(t.Shape, t.ShapeLength)]
            sig = [int(v) for v in _vec(t.ShapeSignature, t.ShapeSignatureLength)]
            dtype = _TYPE_NAMES.get(int(t.Type()), f"UNKNOWN_{int(t.Type())}")
            buffer_index = int(t.Buffer())
            buffer_bytes = 0
            raw: Optional[bytes] = None
            if buffer_index < model.BuffersLength():
                b = model.Buffers(buffer_index)
                n = b.DataLength()
                if n:
                    arr = b.DataAsNumpy()
                    raw = bytes(arr.tobytes()) if hasattr(arr, "tobytes") else bytes(arr)
                    buffer_bytes = n
                else:
                    size = int(b.Size())
                    offset = int(b.Offset())
                    if size > 0:
                        buffer_bytes = size
                        if offset > 0 and offset + size <= len(buf):
                            raw = buf[offset : offset + size]
            data = _decode_tensor_data(raw, dtype, shape)
            tensors.append(
                TensorIR(
                    index=ti,
                    name=_str(t.Name()) or f"tensor_{ti}",
                    shape=shape,
                    shape_signature=sig if sig else list(shape),
                    dtype=dtype,
                    buffer_index=buffer_index,
                    buffer_bytes=buffer_bytes,
                    is_variable=bool(t.IsVariable()),
                    quantization=_decode_quantization(t.Quantization()),
                    data=data,
                    stats=_stats(data) if (with_stats and data is not None) else None,
                )
            )

        inputs = [int(v) for v in _vec(sg.Inputs, sg.InputsLength)]
        outputs = [int(v) for v in _vec(sg.Outputs, sg.OutputsLength)]
        operators: List[OperatorIR] = []
        for oi in range(sg.OperatorsLength()):
            op = sg.Operators(oi)
            opcode_index = int(op.OpcodeIndex())
            code = opcodes[opcode_index] if opcode_index < len(opcodes) else None
            if code is None:
                op_name = f"UNKNOWN_OPCODE_{opcode_index}"
                custom_code = None
            elif code["custom_code"]:
                op_name = "CUSTOM"
                custom_code = code["custom_code"]
            else:
                op_name = code["builtin_name"]
                custom_code = None
            opt_type, opt_dict = _decode_options(
                int(op.BuiltinOptionsType()), op.BuiltinOptions(), _OPTS_NAMES
            )
            opt2_type, opt2_dict = _decode_options(
                int(op.BuiltinOptions2Type()), op.BuiltinOptions2(), _OPTS2_NAMES
            )
            if opt2_type is not None:
                opt_dict = dict(opt_dict)
                opt_dict["__options2_type__"] = opt2_type
                opt_dict["__options2__"] = opt2_dict
            co = None
            if op.CustomOptionsLength():
                co = bytes(op.CustomOptionsAsNumpy()).hex()
            operators.append(
                OperatorIR(
                    index=oi,
                    opcode_index=opcode_index,
                    op=op_name,
                    builtin_code=(code["builtin_code"] if code else None),
                    custom_code=custom_code,
                    opcode_version=(code["version"] if code else 0),
                    inputs=[int(v) for v in _vec(op.Inputs, op.InputsLength)],
                    outputs=[int(v) for v in _vec(op.Outputs, op.OutputsLength)],
                    intermediates=[int(v) for v in _vec(op.Intermediates, op.IntermediatesLength)],
                    options_type=opt_type,
                    options=opt_dict,
                    custom_options_hex=co,
                )
            )
        subgraphs.append(
            SubGraphIR(
                index=si,
                name=_str(sg.Name()),
                tensors=tensors,
                inputs=inputs,
                outputs=outputs,
                operators=operators,
                role=_classify_role(_str(sg.Name()), [o.op for o in operators]),
            )
        )

    signatures: List[SignatureIR] = []
    for i in range(model.SignatureDefsLength()):
        sd = model.SignatureDefs(i)
        ins = []
        for j in range(sd.InputsLength()):
            tm = sd.Inputs(j)
            ins.append((_str(tm.Name()) or f"input_{j}", int(tm.TensorIndex())))
        outs = []
        for j in range(sd.OutputsLength()):
            tm = sd.Outputs(j)
            outs.append((_str(tm.Name()) or f"output_{j}", int(tm.TensorIndex())))
        signatures.append(
            SignatureIR(
                key=_str(sd.SignatureKey()) or "serving_default",
                subgraph_index=int(sd.SubgraphIndex()),
                inputs=ins,
                outputs=outs,
            )
        )

    return ModelIR(
        path=os.path.abspath(path),
        version=int(model.Version()),
        description=_str(model.Description()),
        operator_codes=opcodes,
        subgraphs=subgraphs,
        signatures=signatures,
        buffer_count=int(model.BuffersLength()),
        file_size=len(buf),
        _buf=buf,
    )


# ---------------------------------------------------------------------------
# Support analysis
# ---------------------------------------------------------------------------
def supported_by_jax(op_name: str) -> bool:
    return op_name in SUPPORTED_SET


def unsupported_ops(sg: SubGraphIR) -> List[Dict[str, Any]]:
    """Explain, per op, why this subgraph cannot be executed in JAX."""
    out: List[Dict[str, Any]] = []
    bad_tensors = set()
    for t in sg.tensors:
        if t.dtype in ("STRING", "RESOURCE", "VARIANT", "INT4"):
            bad_tensors.add(t.index)
    for op in sg.operators:
        reason = None
        if op.op == "CUSTOM":
            reason = f"custom op {op.custom_code!r} is not supported"
        elif op.op in STATEFUL_OPS:
            reason = f"stateful op {op.op} is not supported by the JAX reference"
        elif op.op not in SUPPORTED_SET:
            reason = f"op {op.op} is not implemented by tflite2jax"
        else:
            for ti in list(op.inputs) + list(op.outputs):
                if ti in bad_tensors:
                    t = sg.tensor(ti)
                    reason = f"op {op.op} touches tensor {ti} with unsupported dtype {t.dtype}"
                    break
        if reason:
            out.append(
                {
                    "index": op.index,
                    "op": op.op,
                    "custom_code": op.custom_code,
                    "reason": reason,
                }
            )
    return out


def choose_subgraph(
    ir: ModelIR, subgraph: Optional[int] = None, signature: Optional[str] = None
) -> Tuple[SubGraphIR, Optional[SignatureIR], str]:
    """Pick the subgraph to execute and explain the choice.

    Priority:
      1. explicit --subgraph N
      2. explicit --signature KEY
      3. first signature whose subgraph is stateless and free of custom ops
         (i.e. an inference entry point)
      4. subgraph 0
    """
    if subgraph is not None:
        sg = ir.subgraph(subgraph)
        sig = None
        for s in ir.signatures:
            if s.subgraph_index == subgraph:
                sig = s
                break
        return sg, sig, f"explicit --subgraph {subgraph}"
    if signature is not None:
        sig = ir.signature(signature)
        return ir.subgraph(sig.subgraph_index), sig, f"explicit --signature {signature!r}"
    for s in ir.signatures:
        cand = ir.subgraph(s.subgraph_index)
        if not cand.unsupported():
            return cand, s, f"primary signature {s.key!r} (subgraph {cand.index})"
    if ir.signatures:
        s = ir.signatures[0]
        sg = ir.subgraph(s.subgraph_index)
        return sg, s, f"first signature {s.key!r} (subgraph {sg.index}); no runnable signature"
    sg = ir.subgraph(0)
    return sg, None, "subgraph 0 (model has no signature defs)"


# ---------------------------------------------------------------------------
# CLI
# ---------------------------------------------------------------------------
def main(argv: Optional[Sequence[str]] = None) -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("model", help="path to .tflite")
    ap.add_argument("--out", default="-", help="output JSON path ('-' = stdout)")
    ap.add_argument("--no-stats", action="store_true", help="omit constant min/max/mean")
    ap.add_argument("--summary", action="store_true", help="human-readable summary only")
    args = ap.parse_args(argv)

    ir = load_model(args.model, with_stats=not args.no_stats)
    if args.summary:
        print(f"{ir.path}  version={ir.version}  subgraphs={len(ir.subgraphs)}  signatures={len(ir.signatures)}")
        for sg in ir.subgraphs:
            ops = sorted({o.op for o in sg.operators})
            print(f"  subgraph {sg.index} name={sg.name!r} role={sg.role} tensors={len(sg.tensors)} ops={len(sg.operators)}")
            print(f"    inputs={[sg.tensor(i).name for i in sg.inputs]}")
            print(f"    outputs={[sg.tensor(i).name for i in sg.outputs]}")
            print(f"    op_types={ops}")
            unsup = sg.unsupported()
            if unsup:
                print(f"    unsupported={[(u['index'], u['op'], u['reason']) for u in unsup][:10]}")
        return 0

    if args.out == "-":
        json.dump(ir.to_json(), sys.stdout, indent=2, sort_keys=True)
        sys.stdout.write("\n")
    else:
        ir.dump_json(args.out)
        print(f"wrote {args.out}", file=sys.stderr)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
