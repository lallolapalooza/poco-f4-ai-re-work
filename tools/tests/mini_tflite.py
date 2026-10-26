"""Tiny TFLite flatbuffer builder used by the test-suite.

Builds single-subgraph models directly with the flatc-generated bindings (the
same ones tflite_ir.py uses), so tests do not need TensorFlow.  This covers
operators that do not appear in the sample corpus.
"""

from __future__ import annotations

import importlib
import os
import sys
from typing import Any, Dict, List, Optional, Sequence, Tuple

import flatbuffers
import numpy as np

_TOOLS = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
if _TOOLS not in sys.path:
    sys.path.insert(0, _TOOLS)

from tflite.BuiltinOperator import BuiltinOperator  # noqa: E402
from tflite.BuiltinOptions import BuiltinOptions  # noqa: E402
from tflite.Buffer import Buffer, BufferStart, BufferAddData, BufferEnd  # noqa: E402
from tflite.Model import (  # noqa: E402
    Model,
    ModelStart,
    ModelAddVersion,
    ModelAddOperatorCodes,
    ModelAddSubgraphs,
    ModelAddBuffers,
    ModelAddDescription,
    ModelCreateOperatorCodesVector,
    ModelCreateSubgraphsVector,
    ModelCreateBuffersVector,
    ModelEnd,
)
from tflite.Operator import (  # noqa: E402
    Operator,
    OperatorStart,
    OperatorAddOpcodeIndex,
    OperatorAddInputs,
    OperatorAddOutputs,
    OperatorAddBuiltinOptionsType,
    OperatorAddBuiltinOptions,
    OperatorCreateInputsVector,
    OperatorCreateOutputsVector,
    OperatorEnd,
)
from tflite.OperatorCode import (  # noqa: E402
    OperatorCode,
    OperatorCodeStart,
    OperatorCodeAddBuiltinCode,
    OperatorCodeAddDeprecatedBuiltinCode,
    OperatorCodeAddVersion,
    OperatorCodeEnd,
)
from tflite.QuantizationParameters import (  # noqa: E402
    QuantizationParameters,
    QuantizationParametersStart,
    QuantizationParametersAddScale,
    QuantizationParametersAddZeroPoint,
    QuantizationParametersAddQuantizedDimension,
    QuantizationParametersEnd,
)
from tflite.SubGraph import (  # noqa: E402
    SubGraph,
    SubGraphStart,
    SubGraphAddTensors,
    SubGraphAddInputs,
    SubGraphAddOutputs,
    SubGraphAddOperators,
    SubGraphAddName,
    SubGraphCreateTensorsVector,
    SubGraphCreateInputsVector,
    SubGraphCreateOutputsVector,
    SubGraphCreateOperatorsVector,
    SubGraphEnd,
)
from tflite.Tensor import (  # noqa: E402
    Tensor,
    TensorStart,
    TensorAddShape,
    TensorAddType,
    TensorAddBuffer,
    TensorAddName,
    TensorAddQuantization,
    TensorCreateShapeVector,
    TensorEnd,
)
from tflite.TensorType import TensorType  # noqa: E402

NUMPY_DTYPE = {
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
}

# Field values that are plain ints in the schema; the rest of the fields in
# our test models are either int32 scalars or int32 vectors.
_FLOAT_VECTOR_FIELDS = {"Scale", "Min", "Max"}


def _enum(cls, name: str, value: Any) -> int:
    return int(value) if isinstance(value, int) else int(getattr(cls, value))


def _create_int32_vector(b: flatbuffers.Builder, values: Sequence[int]) -> int:
    b.StartVector(4, len(values), 4)
    for v in reversed(values):
        b.PrependInt32(int(v))
    return b.EndVector()


def _create_float32_vector(b: flatbuffers.Builder, values: Sequence[float]) -> int:
    b.StartVector(4, len(values), 4)
    for v in reversed(values):
        b.PrependFloat32(float(v))
    return b.EndVector()


def _create_int64_vector(b: flatbuffers.Builder, values: Sequence[int]) -> int:
    b.StartVector(8, len(values), 8)
    for v in reversed(values):
        b.PrependInt64(int(v))
    return b.EndVector()


def _create_offset_vector(b: flatbuffers.Builder, offsets: Sequence[int]) -> int:
    """Vector of table/struct offsets (flatbuffers' CreateVectorOfTables is gone)."""
    b.StartVector(4, len(offsets), 4)
    for off in reversed(offsets):
        b.PrependUOffsetTRelative(off)
    return b.EndVector()


class MiniModel:
    """Accumulate tensors/operators, then serialise a valid .tflite flatbuffer."""

    def __init__(self, name: str = "mini"):
        self.name = name
        self.tensors: List[Dict[str, Any]] = []
        self.ops: List[Dict[str, Any]] = []
        self.buffers: List[Optional[bytes]] = [None]  # buffer 0 is always empty
        self.inputs: List[int] = []
        self.outputs: List[int] = []
        self.opcodes: List[Tuple[int, str]] = []

    # -- construction -----------------------------------------------------
    def tensor(
        self,
        shape: Sequence[int],
        dtype: str,
        data: Any = None,
        name: Optional[str] = None,
        scale: Optional[Sequence[float]] = None,
        zero_point: Optional[Sequence[int]] = None,
        quantized_dimension: int = 0,
    ) -> int:
        if dtype not in NUMPY_DTYPE:
            raise ValueError(f"unsupported test dtype {dtype}")
        index = len(self.tensors)
        buffer_index = 0
        if data is not None:
            arr = np.asarray(data, dtype=np.dtype(NUMPY_DTYPE[dtype])).reshape(tuple(shape))
            self.buffers.append(arr.tobytes(order="C"))
            buffer_index = len(self.buffers) - 1
        self.tensors.append(
            {
                "shape": [int(s) for s in shape],
                "dtype": dtype,
                "buffer": buffer_index,
                "name": name or f"t{index}",
                "scale": list(scale or []),
                "zero_point": list(zero_point or []),
                "qdim": int(quantized_dimension),
            }
        )
        return index

    def add(self, op: str, inputs: Sequence[int], outputs: Sequence[int], options: Optional[Dict[str, Any]] = None) -> None:
        self.ops.append(
            {
                "op": op,
                "inputs": [int(i) for i in inputs],
                "outputs": [int(o) for o in outputs],
                "options": None if options is None else dict(options),
            }
        )

    def set_io(self, inputs: Sequence[int], outputs: Sequence[int]) -> None:
        self.inputs = [int(i) for i in inputs]
        self.outputs = [int(o) for o in outputs]

    # -- serialisation ----------------------------------------------------
    def _option_table(self, b: flatbuffers.Builder, op: Dict[str, Any]) -> Tuple[int, int]:
        opts = op["options"]
        if opts is None:
            return 0, 0
        # The option type is named <Op>Options for everything this builder uses.
        type_name = f"{op['op'].title().replace('_', '')}Options"
        # Title-casing mangles acronyms/names; use an explicit mapping first.
        type_name = _OPTION_TYPES.get(op["op"], type_name)
        try:
            mod = importlib.import_module(f"tflite.{type_name}")
            start = getattr(mod, f"{type_name}Start")
            end = getattr(mod, f"{type_name}End")
        except (ImportError, AttributeError):
            return 0, 0
        vectors: Dict[str, int] = {}
        for field, value in opts.items():
            if isinstance(value, (list, tuple)):
                if field in _FLOAT_VECTOR_FIELDS:
                    vectors[field] = _create_float32_vector(b, value)
                else:
                    vectors[field] = _create_int32_vector(b, value)
        start(b)
        for field, value in opts.items():
            add = getattr(mod, f"{type_name}Add{field}", None)
            if add is None:
                raise AttributeError(f"{type_name} has no field {field}")
            add(b, vectors[field] if isinstance(value, (list, tuple)) else _coerce(value))
        offset = end(b)
        union_type = getattr(BuiltinOptions, type_name)
        return offset, int(union_type)

    def build(self) -> bytes:
        b = flatbuffers.Builder(0)
        # Buffers ---------------------------------------------------------
        buffer_offsets: List[int] = []
        for data in self.buffers:
            if data is None:
                BufferStart(b)
                buffer_offsets.append(BufferEnd(b))
            else:
                vec = b.CreateByteVector(data)
                BufferStart(b)
                BufferAddData(b, vec)
                buffer_offsets.append(BufferEnd(b))

        # Tensors ---------------------------------------------------------
        tensor_offsets: List[int] = []
        for t in self.tensors:
            name_off = b.CreateString(t["name"])
            shape_off = TensorCreateShapeVector(b, t["shape"])
            quant_off = 0
            if t["scale"]:
                scale_off = _create_float32_vector(b, t["scale"])
                zp_off = _create_int64_vector(b, t["zero_point"] or [0])
                QuantizationParametersStart(b)
                QuantizationParametersAddScale(b, scale_off)
                QuantizationParametersAddZeroPoint(b, zp_off)
                QuantizationParametersAddQuantizedDimension(b, t["qdim"])
                quant_off = QuantizationParametersEnd(b)
            TensorStart(b)
            TensorAddShape(b, shape_off)
            TensorAddType(b, int(getattr(TensorType, t["dtype"])))
            TensorAddBuffer(b, t["buffer"])
            TensorAddName(b, name_off)
            if quant_off:
                TensorAddQuantization(b, quant_off)
            tensor_offsets.append(TensorEnd(b))

        # Operator codes --------------------------------------------------
        opcode_index: Dict[str, int] = {}
        opcode_offsets: List[int] = []
        for op in self.ops:
            name = op["op"]
            if name in opcode_index:
                continue
            opcode_index[name] = len(opcode_offsets)
            code = int(getattr(BuiltinOperator, name))
            OperatorCodeStart(b)
            OperatorCodeAddBuiltinCode(b, code)
            OperatorCodeAddDeprecatedBuiltinCode(b, code)
            OperatorCodeAddVersion(b, 1)
            opcode_offsets.append(OperatorCodeEnd(b))

        # Operators -------------------------------------------------------
        operator_offsets: List[int] = []
        for op in self.ops:
            in_vec = OperatorCreateInputsVector(b, op["inputs"])
            out_vec = OperatorCreateOutputsVector(b, op["outputs"])
            opt_off, opt_type = self._option_table(b, op)
            OperatorStart(b)
            OperatorAddOpcodeIndex(b, opcode_index[op["op"]])
            OperatorAddInputs(b, in_vec)
            OperatorAddOutputs(b, out_vec)
            if opt_type:
                OperatorAddBuiltinOptionsType(b, opt_type)
                OperatorAddBuiltinOptions(b, opt_off)
            operator_offsets.append(OperatorEnd(b))

        # Subgraph --------------------------------------------------------
        sg_name = b.CreateString(self.name)
        tensors_vec = _create_offset_vector(b, tensor_offsets)
        inputs_vec = SubGraphCreateInputsVector(b, self.inputs)
        outputs_vec = SubGraphCreateOutputsVector(b, self.outputs)
        ops_vec = _create_offset_vector(b, operator_offsets)
        SubGraphStart(b)
        SubGraphAddTensors(b, tensors_vec)
        SubGraphAddInputs(b, inputs_vec)
        SubGraphAddOutputs(b, outputs_vec)
        SubGraphAddOperators(b, ops_vec)
        SubGraphAddName(b, sg_name)
        subgraph = SubGraphEnd(b)

        # Model -----------------------------------------------------------
        description = b.CreateString("mini_tflite test model")
        codes_vec = _create_offset_vector(b, opcode_offsets)
        subgraphs_vec = _create_offset_vector(b, [subgraph])
        buffers_vec = _create_offset_vector(b, buffer_offsets)
        ModelStart(b)
        ModelAddVersion(b, 3)
        ModelAddOperatorCodes(b, codes_vec)
        ModelAddSubgraphs(b, subgraphs_vec)
        ModelAddBuffers(b, buffers_vec)
        ModelAddDescription(b, description)
        model = ModelEnd(b)
        b.Finish(model, file_identifier=b"TFL3")
        return bytes(b.Output())


def _coerce(value: Any) -> Any:
    if isinstance(value, np.generic):
        return value.item()
    return value


# Option union classes do not always follow the op name (e.g. pool -> Pool2D,
# mean -> Reducer); map explicitly.
_OPTION_TYPES = {
    "CONV_2D": "Conv2DOptions",
    "DEPTHWISE_CONV_2D": "DepthwiseConv2DOptions",
    "FULLY_CONNECTED": "FullyConnectedOptions",
    "SOFTMAX": "SoftmaxOptions",
    "CONCATENATION": "ConcatenationOptions",
    "ADD": "AddOptions",
    "SUB": "SubOptions",
    "MUL": "MulOptions",
    "DIV": "DivOptions",
    "MAX_POOL_2D": "Pool2DOptions",
    "AVERAGE_POOL_2D": "Pool2DOptions",
    "RESIZE_BILINEAR": "ResizeBilinearOptions",
    "RESIZE_NEAREST_NEIGHBOR": "ResizeNearestNeighborOptions",
    "SQUEEZE": "SqueezeOptions",
    "GATHER": "GatherOptions",
    "GATHER_ND": "GatherNdOptions",
    "ARG_MAX": "ArgMaxOptions",
    "ARG_MIN": "ArgMinOptions",
    "ONE_HOT": "OneHotOptions",
    "PACK": "PackOptions",
    "UNPACK": "UnpackOptions",
    "STRIDED_SLICE": "StridedSliceOptions",
    "BATCH_MATMUL": "BatchMatMulOptions",
    "SPACE_TO_DEPTH": "SpaceToDepthOptions",
    "DEPTH_TO_SPACE": "DepthToSpaceOptions",
    "TOPK_V2": "TopKV2Options",
    "CAST": "CastOptions",
    "SHAPE": "ShapeOptions",
    "LEAKY_RELU": "LeakyReluOptions",
    "MEAN": "ReducerOptions",
    "SUM": "ReducerOptions",
    "REDUCE_MAX": "ReducerOptions",
    "REDUCE_MIN": "ReducerOptions",
    "REDUCE_PROD": "ReducerOptions",
    "SELECT": "SelectOptions",
    "SELECT_V2": "SelectV2Options",
    "FLOOR_DIV": "FloorDivOptions",
    "SQUARE": "SquareOptions",
    "SQUARED_DIFFERENCE": "SquaredDifferenceOptions",
    "ABS": "AbsOptions",
    "COS": "CosOptions",
    "POW": "PowOptions",
    "ZEROS_LIKE": "ZerosLikeOptions",
    "FILL": "FillOptions",
    "TILE": "TileOptions",
    "EQUAL": "EqualOptions",
    "NOT_EQUAL": "NotEqualOptions",
    "GREATER": "GreaterOptions",
    "GREATER_EQUAL": "GreaterEqualOptions",
    "LESS": "LessOptions",
    "LESS_EQUAL": "LessEqualOptions",
    "LOGICAL_AND": "LogicalAndOptions",
    "LOGICAL_OR": "LogicalOrOptions",
    "LOGICAL_NOT": "LogicalNotOptions",
    "QUANTIZE": "QuantizeOptions",
    "DEQUANTIZE": "DequantizeOptions",
    "HARD_SWISH": "HardSwishOptions",
    "MAXIMUM": "MaximumMinimumOptions",
    "MINIMUM": "MaximumMinimumOptions",
}


def write_model(model: MiniModel, path: str) -> str:
    os.makedirs(os.path.dirname(os.path.abspath(path)), exist_ok=True)
    with open(path, "wb") as f:
        f.write(model.build())
    return path
