#!/usr/bin/env python3
"""Convert a TFLite inference subgraph into a standalone JAX module.

The generated ``model.py`` is self-contained: it imports only ``os``,
``numpy`` and ``jax``, loads its weights from ``params.npz`` sitting next to
it, and exposes::

    params = init_params()          # dict[str, jax.Array]
    y = apply(params, x)            # single input
    y0, y1 = apply(params, x0, x1)  # multiple inputs / outputs

No TensorFlow, no flatbuffer access at run time, no interpreter.

Weights are read from the flatbuffer and written to ``params.npz`` in their
stored dtype (float32, float16, int8, ...).  Quantized *activations* are
emulated in float32: raw integer values are dequantized when consumed and
re-quantized when they reach a graph output (see README limitations).

CLI::

    python tflite2jax.py model.tflite --out outdir [--subgraph N]
                                      [--signature KEY] [--no-verify]

Writes ``model.py``, ``params.npz``, ``ir.json`` and (on successful
verification) ``verify.txt``.  For an unconvertible model it writes
``unsupported.json`` with status/reason/op list and exits 2 instead of
raising a traceback.
"""

from __future__ import annotations

import argparse
import json
import os
import sys
from dataclasses import dataclass
from typing import Any, Dict, List, Optional, Sequence, Tuple

import numpy as np

import tflite_ir
from tflite_ir import (
    DTYPE_TO_NUMPY,
    STATEFUL_OPS,
    ModelIR,
    OperatorIR,
    SignatureIR,
    SubGraphIR,
    TensorIR,
)

# ---------------------------------------------------------------------------
# Every op implemented by _CodeGen.  Must stay in sync with
# tflite_ir.SUPPORTED_BUILTIN_OPS (checked by tests).
# ---------------------------------------------------------------------------
OPS = frozenset(tflite_ir.SUPPORTED_BUILTIN_OPS)

_FLOAT_TYPES = ("FLOAT16", "FLOAT32", "FLOAT64")
_INT_TYPES = ("INT8", "INT16", "INT32", "INT64", "UINT8", "UINT16", "UINT32", "UINT64")
_QUANTIZED_INTS = ("INT8", "UINT8", "INT16")

_TFLITE_TO_JNP = {
    "FLOAT16": "jnp.float16",
    "FLOAT32": "jnp.float32",
    "FLOAT64": "jnp.float64",
    "INT8": "jnp.int8",
    "INT16": "jnp.int16",
    "INT32": "jnp.int32",
    "INT64": "jnp.int64",
    "UINT8": "jnp.uint8",
    "UINT16": "jnp.uint16",
    "UINT32": "jnp.uint32",
    "UINT64": "jnp.uint64",
    "BOOL": "jnp.bool_",
}


class ConversionError(Exception):
    """Base class for conversion problems."""


class OpBuildError(ConversionError):
    """A single operator cannot be lowered to JAX."""

    def __init__(self, op: Optional[OperatorIR], message: str):
        self.op_index = None if op is None else op.index
        self.op_name = None if op is None else op.op
        super().__init__(message)
        self.message = message

    def to_json(self) -> Dict[str, Any]:
        return {"index": self.op_index, "op": self.op_name, "reason": self.message}


class UnsupportedModel(ConversionError):
    """The selected subgraph cannot be converted; carries a machine-readable reason."""

    def __init__(
        self,
        status: str,
        reason: str,
        ir: ModelIR,
        sg: SubGraphIR,
        signature: Optional[SignatureIR],
        unsupported_ops: Sequence[Dict[str, Any]],
        op_errors: Sequence[OpBuildError] = (),
    ):
        self.status = status
        self.reason = reason
        self.ir = ir
        self.sg = sg
        self.signature = signature
        self.unsupported_ops = list(unsupported_ops)
        self.op_errors = list(op_errors)
        super().__init__(f"{status}: {reason}")

    def to_json(self) -> Dict[str, Any]:
        return {
            "status": self.status,
            "model": self.ir.path,
            "reason": self.reason,
            "subgraph_index": self.sg.index,
            "subgraph_name": self.sg.name,
            "subgraph_role": self.sg.role,
            "signature_key": self.signature.key if self.signature else None,
            "stateful": self.sg.is_stateful,
            "unsupported_ops": self.unsupported_ops,
            "build_errors": [e.to_json() for e in self.op_errors],
            "subgraphs": [
                {
                    "index": s.index,
                    "name": s.name,
                    "role": s.role,
                    "stateful": s.is_stateful,
                    "inputs": [s.tensor(i).name for i in s.inputs],
                    "outputs": [s.tensor(i).name for i in s.outputs],
                    "unsupported_ops": s.unsupported(),
                }
                for s in self.ir.subgraphs
            ],
            "hint": _hint_for_status(self.status),
        }


def _hint_for_status(status: str) -> str:
    return {
        "stateful_unsupported": (
            "Subgraph contains variables/checkpoint ops (VAR_HANDLE, READ_VARIABLE, "
            "ASSIGN_VARIABLE, CALL_ONCE). Fold or export inference weights first; "
            "only stateless inference subgraphs can be lowered to a pure JAX function."
        ),
        "custom_op_unsupported": (
            "Subgraph contains TFLite CUSTOM ops, which have no builtin semantics. "
            "Re-export the model with a supported standard op set."
        ),
        "dtype_unsupported": (
            "Subgraph uses STRING/RESOURCE/VARIANT tensors, which do not map to JAX arrays."
        ),
        "unsupported_ops": "One or more builtin ops have no JAX lowering in this tool.",
        "dynamic_shape_unsupported": (
            "The graph has dynamic (-1) tensor dimensions; JAX requires static shapes. "
            "Run with a fixed input signature."
        ),
        "unsupported_params": (
            "An op requires a compile-time constant (shape/axes/slice bounds) that is "
            "computed dynamically in this graph."
        ),
    }.get(status, "")


# ---------------------------------------------------------------------------
# Small flatbuffer helpers
# ---------------------------------------------------------------------------
def _const_array(sg: SubGraphIR, index: int) -> Optional[np.ndarray]:
    if index is None or index < 0:
        return None
    t = sg.tensor(index)
    return t.data if t.is_constant else None


def _need_const_array(op: OperatorIR, sg: SubGraphIR, k: int, what: str) -> np.ndarray:
    if k >= len(op.inputs):
        raise OpBuildError(op, f"{op.op} is missing required input {k} ({what})")
    idx = op.inputs[k]
    arr = _const_array(sg, idx)
    if arr is None:
        raise OpBuildError(
            op,
            f"{op.op} requires a constant {what} (input {k}, tensor {idx}) but it is dynamic",
        )
    return arr


def _const_ints(op: OperatorIR, sg: SubGraphIR, k: int, what: str) -> List[int]:
    return [int(v) for v in np.asarray(_need_const_array(op, sg, k, what)).reshape(-1)]


def _const_scalar(op: OperatorIR, sg: SubGraphIR, k: int, what: str) -> Any:
    arr = np.asarray(_need_const_array(op, sg, k, what)).reshape(-1)
    if arr.size != 1:
        raise OpBuildError(op, f"{op.op} {what} must be a scalar, got shape {arr.shape}")
    return arr[0].item()


def _const_dims(op: OperatorIR, sg: SubGraphIR, k: int, what: str) -> List[int]:
    return [int(v) for v in np.asarray(_need_const_array(op, sg, k, what)).reshape(-1)]


def _is_quantized_int(t: TensorIR) -> bool:
    return t.dtype in _QUANTIZED_INTS and t.quantization.is_quantized


def asym_per_channel(t: TensorIR) -> bool:
    """TFLite hybrid CONV uses asymmetric input quant for per-channel weights."""
    return len(t.quantization.scale) > 1


# ---------------------------------------------------------------------------
# Static STRIDED_SLICE lowering (masks are static in real models)
# ---------------------------------------------------------------------------
def _normalize_index(v: int, dim: int) -> int:
    v = int(v)
    return v + dim if v < 0 else v


def build_static_slice(
    shape: Sequence[int],
    begin: Sequence[int],
    end: Sequence[int],
    strides: Sequence[int],
    begin_mask: int = 0,
    end_mask: int = 0,
    ellipsis_mask: int = 0,
    new_axis_mask: int = 0,
    shrink_axis_mask: int = 0,
) -> Tuple[Any, ...]:
    """Translate TFLite STRIDED_SLICE semantics into a Python index tuple.

    Only statically known shapes/bounds are supported, which is what the
    generated module needs.  Raises ValueError on invalid specs (including
    negative strides, which JAX's indexing does not support).
    """
    rank = len(shape)
    spec_len = max(len(begin), len(end), len(strides))
    begin = list(begin) + [0] * (spec_len - len(begin))
    end = list(end) + [0] * (spec_len - len(end))
    strides = list(strides) + [1] * (spec_len - len(strides))
    for v in strides:
        if v == 0:
            raise ValueError("STRIDED_SLICE stride cannot be 0")
        if v < 0:
            raise ValueError("negative STRIDED_SLICE strides are not supported")

    ell_positions = [i for i in range(spec_len) if (ellipsis_mask >> i) & 1]
    if len(ell_positions) > 1:
        raise ValueError("STRIDED_SLICE has more than one ellipsis")
    ell_pos = ell_positions[0] if ell_positions else None

    def consumes(i: int) -> bool:
        return not ((new_axis_mask >> i) & 1) and i != ell_pos

    before = sum(1 for i in range(ell_pos) if consumes(i)) if ell_pos is not None else 0
    after = (
        sum(1 for i in range(ell_pos + 1, spec_len) if consumes(i))
        if ell_pos is not None
        else 0
    )
    if ell_pos is not None:
        ell_dims = rank - before - after
        if ell_dims < 0:
            raise ValueError("STRIDED_SLICE ellipsis consumes a negative number of dimensions")
    else:
        total = sum(1 for i in range(spec_len) if consumes(i))
        ell_dims = rank - total
        if ell_dims < 0:
            raise ValueError(
                f"STRIDED_SLICE spec has {total} dims but tensor rank is {rank}"
            )

    out: List[Any] = []
    dim = 0
    for i in range(spec_len):
        if i == ell_pos:
            out.extend([slice(None, None, None)] * ell_dims)
            dim += ell_dims
            continue
        if (new_axis_mask >> i) & 1:
            out.append(None)
            continue
        if dim >= rank:
            raise ValueError("STRIDED_SLICE touches more dims than the tensor has")
        d = int(shape[dim])
        dim += 1
        if (shrink_axis_mask >> i) & 1:
            idx = _normalize_index(begin[i], d)
            if idx < 0 or idx >= d:
                raise ValueError(f"STRIDED_SLICE shrink index {begin[i]} out of range for dim {d}")
            if strides[i] != 1:
                raise ValueError("STRIDED_SLICE shrink axis with stride != 1 is not supported")
            out.append(idx)
            continue
        b = None if (begin_mask >> i) & 1 else int(begin[i])
        e = None if (end_mask >> i) & 1 else int(end[i])
        st = int(strides[i])
        if b is None and e is None and st == 1:
            out.append(slice(None, None, None))
        else:
            out.append(slice(b, e, st))
    if dim < rank:
        out.extend([slice(None, None, None)] * (rank - dim))
    return tuple(out)


def _slice_repr(idx: Tuple[Any, ...]) -> str:
    return repr(idx)


# ---------------------------------------------------------------------------
# Runtime helpers embedded in every generated model.py
# ---------------------------------------------------------------------------
_RUNTIME_SOURCE = r'''
def _act(x, name):
    if name in (None, "NONE"):
        return x
    if name == "RELU":
        return jnp.maximum(x, jnp.zeros((), x.dtype))
    if name == "RELU6":
        return jnp.clip(x, jnp.zeros((), x.dtype), jnp.full((), 6, x.dtype))
    if name == "RELU_N1_TO_1":
        return jnp.clip(x, jnp.full((), -1, x.dtype), jnp.ones((), x.dtype))
    if name == "TANH":
        return jnp.tanh(x)
    raise ValueError("unsupported fused activation: " + repr(name))


def _round_away(x):
    # TfLiteRound/std::round: halves away from zero (jnp.round uses ties-to-even).
    return jnp.sign(x) * jnp.floor(jnp.abs(x) + 0.5)


def _hybrid_quant(x, asymmetric, per_row):
    """Reproduce TFLite's float->int8 activation quantization for hybrid kernels.

    Hybrid kernels (float activations, int8 weights) quantize the *activation*
    before the integer matmul/conv:
      * FULLY_CONNECTED: one vector per batch row, symmetric or asymmetric
      * CONV_2D: one tensor per batch, symmetric for per-tensor weights and
        asymmetric for per-channel weights
    The returned array is the dequantized approximation of that int8 input, so
    the rest of the graph can keep running in float.
    """
    x = jnp.asarray(x, dtype=jnp.float32)
    if per_row:
        axis = (-1,)
    else:
        axis = tuple(range(1, x.ndim))
    rmin = jnp.min(x, axis=axis, keepdims=True)
    rmax = jnp.max(x, axis=axis, keepdims=True)
    if asymmetric:
        lo = jnp.minimum(rmin, 0.0)
        hi = jnp.maximum(rmax, 0.0)
        safe = jnp.where(hi - lo == 0, 1.0, hi - lo)
        scale = safe / 255.0
        # TFLite's asymmetric quantizer uses qmin=-128, qmax=127
        # (PortableAsymmetricQuantizeFloats).  The two zero-point formulas
        # coincide mathematically; the error comparison is only used to pick
        # the numerically more stable one.
        zp_from_min = -128.0 - lo / scale
        zp_from_max = 127.0 - hi / scale
        err_min = 128.0 + jnp.abs(lo / scale)
        err_max = 127.0 + jnp.abs(hi / scale)
        zp = jnp.where(err_min < err_max, zp_from_min, zp_from_max)
        # TFLite computes the zero point in double precision; for an exactly
        # symmetric range it is exactly -0.5 and std::round takes it away from
        # zero.  Float32 arithmetic can put it on the wrong side of the tie.
        symmetric = (lo == -hi) & (hi > 0)
        zp = jnp.where(symmetric, -0.5, zp)
        # Asymmetric quantization uses the full int8 range [-128, 127]
        # (kMinScale=-128 in TFLite's portable tensor utils).
        zp = jnp.clip(_round_away(zp), -128.0, 127.0)
        degenerate = hi == lo
        scale = jnp.where(degenerate, 1.0, scale)
        zp = jnp.where(degenerate, 0.0, zp)
        inv = 1.0 / scale
        q = jnp.clip(_round_away(zp + x * inv), -128.0, 127.0)
        q = jnp.where(degenerate, 0.0, q)
        return (q - zp) * scale
    rng = jnp.maximum(jnp.abs(rmin), jnp.abs(rmax))
    degenerate = rng == 0
    inv = jnp.where(degenerate, 127.0, 127.0 / rng)
    q = jnp.clip(_round_away(x * inv), -127.0, 127.0)
    q = jnp.where(degenerate, 0.0, q)
    scale = jnp.where(degenerate, 1.0, rng / 127.0)
    return q * scale


def _dequant(x, scale, zero_point, axis=-1):
    x = jnp.asarray(x).astype(jnp.float32)
    s = jnp.asarray(scale, dtype=jnp.float32)
    z = jnp.asarray(zero_point, dtype=jnp.float32)
    if s.size > 1:
        shape = [1] * x.ndim
        shape[axis % x.ndim] = s.size
        s = s.reshape(shape)
        z = z.reshape(shape)
    return (x - z) * s


def _quantize(x, scale, zero_point, dtype, axis=-1):
    x = jnp.asarray(x).astype(jnp.float32)
    s = jnp.asarray(scale, dtype=jnp.float32)
    z = jnp.asarray(zero_point, dtype=jnp.float32)
    if s.size > 1:
        shape = [1] * x.ndim
        shape[axis % x.ndim] = s.size
        s = s.reshape(shape)
        z = z.reshape(shape)
    info = np.iinfo(np.dtype(dtype))
    q = _round_away(x / s) + z
    return jnp.clip(q, info.min, info.max).astype(dtype)


def _same_pad(x, kernel, stride, dilation):
    h, w = x.shape[1], x.shape[2]
    kh, kw = kernel
    sh, sw = stride
    dh, dw = dilation
    out_h = -(-h // sh)
    out_w = -(-w // sw)
    ph = max((out_h - 1) * sh + (kh - 1) * dh + 1 - h, 0)
    pw = max((out_w - 1) * sw + (kw - 1) * dw + 1 - w, 0)
    if ph or pw:
        x = jnp.pad(x, ((0, 0), (ph // 2, ph - ph // 2), (pw // 2, pw - pw // 2), (0, 0)))
    return x


def _conv2d(x, w, b, stride, dilation, padding, act):
    x = jnp.asarray(x)
    w = jnp.asarray(w)
    if padding == "SAME":
        x = _same_pad(x, (w.shape[1], w.shape[2]), stride, dilation)
    y = jax.lax.conv_general_dilated(
        x,
        jnp.transpose(w, (1, 2, 3, 0)),
        window_strides=tuple(stride),
        padding="VALID",
        rhs_dilation=tuple(dilation),
        dimension_numbers=("NHWC", "HWIO", "NHWC"),
    )
    if b is not None:
        y = y + jnp.reshape(b, (1, 1, 1, -1))
    return _act(y, act)


def _depthwise_conv2d(x, w, b, stride, dilation, padding, act):
    x = jnp.asarray(x)
    w = jnp.asarray(w)
    ih = x.shape[3]
    kh, kw = w.shape[1], w.shape[2]
    ow = w.shape[3]
    mult = ow // ih
    if ih * mult != ow:
        raise ValueError("depthwise weights are not divisible by input channels")
    k = jnp.reshape(jnp.transpose(w, (1, 2, 3, 0)), (kh, kw, 1, ih * mult))
    if padding == "SAME":
        x = _same_pad(x, (kh, kw), stride, dilation)
    y = jax.lax.conv_general_dilated(
        x,
        k,
        window_strides=tuple(stride),
        padding="VALID",
        rhs_dilation=tuple(dilation),
        dimension_numbers=("NHWC", "HWIO", "NHWC"),
        feature_group_count=ih,
    )
    if b is not None:
        y = y + jnp.reshape(b, (1, 1, 1, -1))
    return _act(y, act)


def _pool(x, kind, window, stride, padding, act):
    x = jnp.asarray(x)
    wh, ww = window
    sh, sw = stride
    if kind == "AVG":
        x = x.astype(jnp.float32)
    if padding == "SAME":
        out_h = -(-x.shape[1] // sh)
        out_w = -(-x.shape[2] // sw)
        ph = max((out_h - 1) * sh + wh - x.shape[1], 0)
        pw = max((out_w - 1) * sw + ww - x.shape[2], 0)
        pad = ((0, 0), (ph // 2, ph - ph // 2), (pw // 2, pw - pw // 2), (0, 0))
    else:
        pad = None
    if kind == "MAX":
        if np.issubdtype(np.dtype(x.dtype), np.floating):
            init = jnp.array(-jnp.inf, x.dtype)
        else:
            init = jnp.array(np.iinfo(np.dtype(x.dtype)).min, x.dtype)
        xp = x if pad is None else jnp.pad(x, pad, constant_values=init)
        y = jax.lax.reduce_window(
            xp, init, jax.lax.max, (1, wh, ww, 1), (1, sh, sw, 1), "VALID",
        )
    else:
        if pad is not None:
            xp = jnp.pad(x, pad, constant_values=0.0)
        else:
            xp = x
        total = jax.lax.reduce_window(
            xp, jnp.array(0.0, jnp.float32), jax.lax.add,
            (1, wh, ww, 1), (1, sh, sw, 1), "VALID",
        )
        ones = jnp.ones((1, x.shape[1], x.shape[2], 1), dtype=jnp.float32)
        if pad is not None:
            ones = jnp.pad(ones, pad, constant_values=0.0)
        count = jax.lax.reduce_window(
            ones, jnp.array(0.0, jnp.float32), jax.lax.add,
            (1, wh, ww, 1), (1, sh, sw, 1), "VALID",
        )
        y = total / count
    return _act(y, act)


def _fc(x, w, b, keep_num_dims, act):
    x = jnp.asarray(x)
    w = jnp.asarray(w)
    if x.ndim == 1:
        y = jnp.matmul(x, jnp.transpose(w))
        if b is not None:
            y = y + jnp.reshape(b, (-1,))
        return _act(y, act)
    # TFLite contracts the LAST dimension; all leading dims are batch.
    flat = jnp.reshape(x, (-1, x.shape[-1]))
    y = jnp.matmul(flat, jnp.transpose(w))
    if b is not None:
        y = y + jnp.reshape(b, (1, -1))
    if keep_num_dims:
        y = jnp.reshape(y, x.shape[:-1] + (w.shape[0],))
    return _act(y, act)


def _softmax(x, beta):
    return jax.nn.softmax(jnp.asarray(x) * beta, axis=-1)


def _batch_matmul(a, b, adj_x, adj_y):
    a = jnp.asarray(a)
    b = jnp.asarray(b)
    if adj_x:
        a = jnp.swapaxes(a, -1, -2)
    if adj_y:
        b = jnp.swapaxes(b, -1, -2)
    return jnp.matmul(a, b)


def _gather_nd(x, indices):
    x = jnp.asarray(x)
    idx = jnp.asarray(indices)
    k = idx.shape[-1]
    prefix = 1
    for d in range(k):
        prefix *= x.shape[d]
    flat = jnp.reshape(x, (prefix,) + tuple(x.shape[k:]))
    offs = jnp.zeros(idx.shape[:-1], dtype=jnp.int32)
    for d in range(k):
        offs = offs * x.shape[d] + idx[..., d].astype(jnp.int32)
    return jnp.take(flat, offs, axis=0)


def _resize_bilinear(x, size, align_corners=False, half_pixel_centers=False):
    x = jnp.asarray(x)
    oh, ow = int(size[0]), int(size[1])
    ih, iw = x.shape[1], x.shape[2]

    def coord(in_size, out_size):
        dst = jnp.arange(out_size, dtype=jnp.float32)
        if align_corners and out_size > 1:
            return dst * (in_size - 1) / (out_size - 1)
        scale = in_size / out_size
        if half_pixel_centers:
            return (dst + 0.5) * scale - 0.5
        return dst * scale

    def setup(in_size, out_size):
        c = jnp.clip(coord(in_size, out_size), 0, in_size - 1)
        lo = jnp.floor(c).astype(jnp.int32)
        hi = jnp.minimum(lo + 1, in_size - 1)
        frac = c - lo.astype(jnp.float32)
        return lo, hi, frac

    y0, y1, wy = setup(ih, oh)
    x0, x1, wx = setup(iw, ow)
    wy = jnp.reshape(wy, (1, oh, 1, 1))
    wx = jnp.reshape(wx, (1, 1, ow, 1))
    top = jnp.take(x, y0, axis=1) * (1 - wy) + jnp.take(x, y1, axis=1) * wy
    return jnp.take(top, x0, axis=2) * (1 - wx) + jnp.take(top, x1, axis=2) * wx


def _resize_nearest(x, size, align_corners, half_pixel_centers):
    x = jnp.asarray(x)
    oh, ow = int(size[0]), int(size[1])
    ih, iw = x.shape[1], x.shape[2]

    def idx(in_size, out_size):
        dst = jnp.arange(out_size, dtype=jnp.float32)
        if align_corners and out_size > 1:
            v = jnp.floor(dst * (in_size - 1) / (out_size - 1) + 0.5)
        elif half_pixel_centers:
            v = jnp.floor((dst + 0.5) * (in_size / out_size))
        else:
            v = jnp.floor(dst * (in_size / out_size))
        return jnp.clip(v, 0, in_size - 1).astype(jnp.int32)

    return jnp.take(jnp.take(x, idx(ih, oh), axis=1), idx(iw, ow), axis=2)


def _space_to_depth(x, block):
    # TFLite channel order: (block_h, block_w) outer, input channel inner.
    b, h, w, c = x.shape
    oh, ow = h // block, w // block
    y = jnp.reshape(x, (b, oh, block, ow, block, c))
    y = jnp.transpose(y, (0, 1, 3, 2, 4, 5))
    return jnp.reshape(y, (b, oh, ow, c * block * block))


def _depth_to_space(x, block):
    b, h, w, c = x.shape
    co = c // (block * block)
    y = jnp.reshape(x, (b, h, w, block, block, co))
    y = jnp.transpose(y, (0, 1, 3, 2, 4, 5))
    return jnp.reshape(y, (b, h * block, w * block, co))


def _unpack(x, num, axis):
    x = jnp.moveaxis(jnp.asarray(x), axis, 0)
    return tuple(x[i] for i in range(num))
'''


# ---------------------------------------------------------------------------
# Code generation
# ---------------------------------------------------------------------------
@dataclass
class InputSpec:
    name: str
    tensor_index: int
    shape: List[int]
    dtype: str
    quantization: Dict[str, Any]

    def to_json(self) -> Dict[str, Any]:
        return {
            "name": self.name,
            "tensor_index": self.tensor_index,
            "shape": list(self.shape),
            "dtype": self.dtype,
            "quantization": dict(self.quantization),
        }


@dataclass
class Conversion:
    ir: ModelIR
    subgraph: SubGraphIR
    signature: Optional[SignatureIR]
    selection_reason: str
    inputs: List[InputSpec]
    outputs: List[InputSpec]
    params: Dict[str, np.ndarray]
    model_py: str
    status: str = "ok"

    def metadata(self) -> Dict[str, Any]:
        return {
            "status": self.status,
            "model": self.ir.path,
            "subgraph_index": self.subgraph.index,
            "subgraph_name": self.subgraph.name,
            "subgraph_role": self.subgraph.role,
            "signature_key": self.signature.key if self.signature else None,
            "selection_reason": self.selection_reason,
            "inputs": [i.to_json() for i in self.inputs],
            "outputs": [o.to_json() for o in self.outputs],
            "params_file": "params.npz",
            "model_file": "model.py",
        }


class _CodeGen:
    def __init__(self, ir: ModelIR, sg: SubGraphIR, signature: Optional[SignatureIR]):
        self.ir = ir
        self.sg = sg
        self.signature = signature
        self.lines: List[str] = []
        self.runtime_dtype: Dict[int, str] = {}
        self.var_names: Dict[int, str] = {}
        self.input_var: Dict[int, str] = {}
        self.params_used: set = set()
        self.errors: List[OpBuildError] = []
        self.inputs: List[InputSpec] = []
        self.outputs: List[InputSpec] = []
        self._select_io()

    # -- IO selection -----------------------------------------------------
    def _select_io(self) -> None:
        sg = self.sg
        if self.signature is not None:
            in_pairs = self.signature.inputs
            out_pairs = self.signature.outputs
        else:
            in_pairs = [(sg.tensor(i).name, i) for i in sg.inputs]
            out_pairs = [(sg.tensor(i).name, i) for i in sg.outputs]
        for k, (name, ti) in enumerate(in_pairs):
            t = sg.tensor(ti)
            self.inputs.append(
                InputSpec(
                    name=name or t.name,
                    tensor_index=ti,
                    shape=list(t.shape),
                    dtype=t.dtype,
                    quantization=t.quantization.to_json(),
                )
            )
            self.input_var[ti] = f"x{k}"
            self.runtime_dtype[ti] = t.dtype
        for k, (name, ti) in enumerate(out_pairs):
            t = sg.tensor(ti)
            self.outputs.append(
                InputSpec(
                    name=name or t.name,
                    tensor_index=ti,
                    shape=list(t.shape),
                    dtype=t.dtype,
                    quantization=t.quantization.to_json(),
                )
            )

    # -- expression helpers ----------------------------------------------
    def tensor(self, index: int) -> TensorIR:
        return self.sg.tensor(index)

    def raw(self, index: int) -> str:
        if index is None or index < 0:
            raise OpBuildError(None, f"optional tensor input {index} is not present")
        if index in self.input_var:
            return self.input_var[index]
        if index in self.var_names:
            return self.var_names[index]
        t = self.tensor(index)
        if t.is_constant:
            self.params_used.add(index)
            return f"c{index}"
        raise OpBuildError(None, f"tensor {index} ({t.name}) is read before it is produced")

    def effective_dtype(self, index: int, want_float: bool = True) -> str:
        """Dtype of the expression returned by ``expr(index, want_float)``."""
        t = self.tensor(index)
        rt = self.runtime_dtype.get(index, t.dtype)
        if want_float and _is_quantized_int(t) and rt in _QUANTIZED_INTS:
            return "FLOAT32"
        return rt

    def expr(self, index: int, want_float: bool = True) -> str:
        e = self.raw(index)
        t = self.tensor(index)
        rt = self.runtime_dtype.get(index, t.dtype)
        if want_float and _is_quantized_int(t) and rt in _QUANTIZED_INTS:
            q = t.quantization
            zps = q.zero_point if q.zero_point else [0]
            return (
                f"_dequant({e}, {[float(v) for v in q.scale]!r}, "
                f"{[int(v) for v in zps]!r}, axis={int(q.quantized_dimension)})"
            )
        return e

    def assign(self, index: int, expr: str, dtype: str, comment: str = "") -> None:
        name = f"t{index}"
        self.var_names[index] = name
        self.runtime_dtype[index] = dtype
        line = f"{name} = {expr}"
        if comment:
            line += f"  # {comment}"
        self.lines.append(line)

    def assign_multi(self, indices: Sequence[int], expr: str, dtypes: Sequence[str], comment: str = "") -> None:
        names = [f"t{i}" for i in indices]
        for i, d in zip(indices, dtypes):
            self.var_names[i] = f"t{i}"
            self.runtime_dtype[i] = d
        line = f"{', '.join(names)} = {expr}"
        if comment:
            line += f"  # {comment}"
        self.lines.append(line)

    def opts(self, op: OperatorIR) -> Dict[str, Any]:
        return op.options or {}

    def compute_dtype(self, index: int) -> str:
        t = self.tensor(index)
        if _is_quantized_int(t):
            # quantized math is emulated in float32
            return "FLOAT32"
        if t.dtype in ("INT4",):
            raise OpBuildError(None, f"tensor {index} has unsupported dtype INT4")
        return t.dtype

    def act_expr(self, expr: str, op: OperatorIR) -> str:
        name = self.opts(op).get("FusedActivationFunction", "NONE")
        if name in (None, "NONE", 0):
            return expr
        return f"_act({expr}, {name!r})"

    def const_ints(self, op: OperatorIR, k: int, what: str) -> List[int]:
        return _const_ints(op, self.sg, k, what)

    # -- per-op lowering --------------------------------------------------
    def emit(self, op: OperatorIR) -> None:
        method = getattr(self, f"_emit_{op.op}", None)
        if method is None:
            raise OpBuildError(op, f"op {op.op} is not implemented by tflite2jax")
        method(op)

    def _hybrid_activation(self, op: OperatorIR, x: str, asymmetric: bool, per_row: bool) -> str:
        """Wrap x with TFLite's hybrid int8 activation quantization when needed."""
        in_t = self.tensor(op.inputs[0])
        w_t = self.tensor(op.inputs[1])
        if in_t.dtype in ("FLOAT32", "FLOAT16") and _is_quantized_int(w_t):
            return f"_hybrid_quant({x}, asymmetric={asymmetric!r}, per_row={per_row!r})"
        return x

    def _emit_CONV_2D(self, op: OperatorIR) -> None:
        o = self.opts(op)
        w_t = self.tensor(op.inputs[1])
        x = self._hybrid_activation(
            op, self.expr(op.inputs[0]), asym_per_channel(w_t), per_row=False
        )
        w = self.expr(op.inputs[1])
        b = self.expr(op.inputs[2]) if len(op.inputs) > 2 and op.inputs[2] >= 0 else "None"
        stride = (int(o.get("StrideH", 1)), int(o.get("StrideW", 1)))
        dilation = (int(o.get("DilationHFactor", 1)), int(o.get("DilationWFactor", 1)))
        padding = str(o.get("Padding", "SAME"))
        act = o.get("FusedActivationFunction", "NONE")
        self.assign(
            op.outputs[0],
            f"_conv2d({x}, {w}, {b}, stride={stride!r}, dilation={dilation!r}, "
            f"padding={padding!r}, act={act!r})",
            self.compute_dtype(op.outputs[0]),
        )

    def _emit_DEPTHWISE_CONV_2D(self, op: OperatorIR) -> None:
        o = self.opts(op)
        # TFLite depthwise hybrid kernels always use per-channel weights and
        # asymmetric per-batch activation quantization.
        x = self._hybrid_activation(op, self.expr(op.inputs[0]), True, per_row=False)
        w = self.expr(op.inputs[1])
        b = self.expr(op.inputs[2]) if len(op.inputs) > 2 and op.inputs[2] >= 0 else "None"
        stride = (int(o.get("StrideH", 1)), int(o.get("StrideW", 1)))
        dilation = (int(o.get("DilationHFactor", 1)), int(o.get("DilationWFactor", 1)))
        padding = str(o.get("Padding", "SAME"))
        act = o.get("FusedActivationFunction", "NONE")
        self.assign(
            op.outputs[0],
            f"_depthwise_conv2d({x}, {w}, {b}, stride={stride!r}, dilation={dilation!r}, "
            f"padding={padding!r}, act={act!r})",
            self.compute_dtype(op.outputs[0]),
        )

    def _emit_FULLY_CONNECTED(self, op: OperatorIR) -> None:
        o = self.opts(op)
        x = self._hybrid_activation(
            op,
            self.expr(op.inputs[0]),
            bool(o.get("AsymmetricQuantizeInputs", False)),
            per_row=True,
        )
        w = self.expr(op.inputs[1])
        b = self.expr(op.inputs[2]) if len(op.inputs) > 2 and op.inputs[2] >= 0 else "None"
        keep = bool(o.get("KeepNumDims", False))
        act = o.get("FusedActivationFunction", "NONE")
        self.assign(
            op.outputs[0],
            f"_fc({x}, {w}, {b}, keep_num_dims={keep!r}, act={act!r})",
            self.compute_dtype(op.outputs[0]),
        )

    def _binary(self, op: OperatorIR, fn: str, activation: bool = False) -> None:
        a = self.expr(op.inputs[0])
        b = self.expr(op.inputs[1])
        out = op.outputs[0]
        t = self.tensor(out)
        if fn == "jnp.divide" and t.dtype not in _FLOAT_TYPES:
            fn = "jnp.floor_divide"
        e = f"{fn}({a}, {b})"
        if activation:
            e = self.act_expr(e, op)
        self.assign(out, e, self.compute_dtype(out))

    def _emit_ADD(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.add", activation=True)

    def _emit_SUB(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.subtract", activation=True)

    def _emit_MUL(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.multiply", activation=True)

    def _emit_DIV(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.divide", activation=True)

    def _emit_FLOOR_DIV(self, op: OperatorIR) -> None:
        a = self.expr(op.inputs[0])
        b = self.expr(op.inputs[1])
        out = op.outputs[0]
        t = self.tensor(out)
        if t.dtype in _FLOAT_TYPES:
            e = f"jnp.floor(jnp.divide({a}, {b}))"
        else:
            e = f"jnp.floor_divide({a}, {b})"
        self.assign(out, e, self.compute_dtype(out))

    def _emit_MAXIMUM(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.maximum")

    def _emit_MINIMUM(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.minimum")

    def _emit_POW(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.power")

    def _emit_SQUARED_DIFFERENCE(self, op: OperatorIR) -> None:
        a = self.expr(op.inputs[0])
        b = self.expr(op.inputs[1])
        self.assign(op.outputs[0], f"jnp.square({a} - {b})", self.compute_dtype(op.outputs[0]))

    def _emit_EQUAL(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.equal")

    def _emit_NOT_EQUAL(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.not_equal")

    def _emit_LESS(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.less")

    def _emit_LESS_EQUAL(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.less_equal")

    def _emit_GREATER(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.greater")

    def _emit_GREATER_EQUAL(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.greater_equal")

    def _emit_LOGICAL_AND(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.logical_and")

    def _emit_LOGICAL_OR(self, op: OperatorIR) -> None:
        self._binary(op, "jnp.logical_or")

    def _emit_SELECT(self, op: OperatorIR) -> None:
        c = self.expr(op.inputs[0], want_float=False)
        a = self.expr(op.inputs[1])
        b = self.expr(op.inputs[2])
        self.assign(op.outputs[0], f"jnp.where({c}, {a}, {b})", self.compute_dtype(op.outputs[0]))

    def _unary(self, op: OperatorIR, fn: str) -> None:
        x = self.expr(op.inputs[0])
        self.assign(op.outputs[0], f"{fn}({x})", self.compute_dtype(op.outputs[0]))

    def _emit_LOGICAL_NOT(self, op: OperatorIR) -> None:
        x = self.expr(op.inputs[0], want_float=False)
        self.assign(op.outputs[0], f"jnp.logical_not({x})", self.tensor(op.outputs[0]).dtype)

    def _emit_ABS(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.abs")

    def _emit_NEG(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.negative")

    def _emit_FLOOR(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.floor")

    def _emit_SQRT(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.sqrt")

    def _emit_RSQRT(self, op: OperatorIR) -> None:
        x = self.expr(op.inputs[0])
        self.assign(op.outputs[0], f"jnp.reciprocal(jnp.sqrt({x}))", self.compute_dtype(op.outputs[0]))

    def _emit_EXP(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.exp")

    def _emit_LOG(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.log")

    def _emit_SIN(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.sin")

    def _emit_COS(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.cos")

    def _emit_SQUARE(self, op: OperatorIR) -> None:
        self._unary(op, "jnp.square")

    def _emit_RELU(self, op: OperatorIR) -> None:
        self._unary_act(op, "RELU")

    def _emit_RELU6(self, op: OperatorIR) -> None:
        self._unary_act(op, "RELU6")

    def _emit_RELU_N1_TO_1(self, op: OperatorIR) -> None:
        self._unary_act(op, "RELU_N1_TO_1")

    def _emit_TANH(self, op: OperatorIR) -> None:
        self._unary_act(op, "TANH")

    def _unary_act(self, op: OperatorIR, name: str) -> None:
        x = self.expr(op.inputs[0])
        self.assign(op.outputs[0], f"_act({x}, {name!r})", self.compute_dtype(op.outputs[0]))

    def _emit_LOGISTIC(self, op: OperatorIR) -> None:
        self._unary(op, "jax.nn.sigmoid")

    def _emit_ELU(self, op: OperatorIR) -> None:
        x = self.expr(op.inputs[0])
        e = f"jnp.where({x} > 0, {x}, jnp.expm1({x}))"
        self.assign(op.outputs[0], e, self.compute_dtype(op.outputs[0]))

    def _emit_LEAKY_RELU(self, op: OperatorIR) -> None:
        alpha = float(self.opts(op).get("Alpha", 0.0))
        x = self.expr(op.inputs[0])
        e = f"jnp.where({x} >= 0, {x}, {alpha!r} * {x})"
        self.assign(op.outputs[0], e, self.compute_dtype(op.outputs[0]))

    def _emit_HARD_SWISH(self, op: OperatorIR) -> None:
        x = self.expr(op.inputs[0])
        e = f"({x} * jnp.clip({x} + 3, 0, 6) / 6)"
        self.assign(op.outputs[0], e, self.compute_dtype(op.outputs[0]))

    def _emit_SOFTMAX(self, op: OperatorIR) -> None:
        beta = float(self.opts(op).get("Beta", 1.0))
        x = self.expr(op.inputs[0])
        self.assign(op.outputs[0], f"_softmax({x}, {beta!r})", self.compute_dtype(op.outputs[0]))

    def _emit_MAX_POOL_2D(self, op: OperatorIR) -> None:
        self._pool(op, "MAX")

    def _emit_AVERAGE_POOL_2D(self, op: OperatorIR) -> None:
        self._pool(op, "AVG")

    def _pool(self, op: OperatorIR, kind: str) -> None:
        o = self.opts(op)
        x = self.expr(op.inputs[0])
        window = (int(o.get("FilterHeight", 1)), int(o.get("FilterWidth", 1)))
        stride = (int(o.get("StrideH", 1)), int(o.get("StrideW", 1)))
        padding = str(o.get("Padding", "SAME"))
        act = o.get("FusedActivationFunction", "NONE")
        self.assign(
            op.outputs[0],
            f"_pool({x}, {kind!r}, window={window!r}, stride={stride!r}, "
            f"padding={padding!r}, act={act!r})",
            self.compute_dtype(op.outputs[0]),
        )

    def _emit_RESHAPE(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        shape = _const_ints(op, self.sg, 1, "new shape")
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"jnp.reshape({x}, {tuple(shape)!r})", rt)

    def _emit_SQUEEZE(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        dims = self.opts(op).get("SqueezeDims", [])
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        if dims:
            e = f"jnp.squeeze({x}, axis={tuple(int(d) for d in dims)!r})"
        else:
            e = f"jnp.squeeze({x})"
        self.assign(op.outputs[0], e, rt)

    def _emit_EXPAND_DIMS(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        axis = int(_const_scalar(op, self.sg, 1, "axis"))
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"jnp.expand_dims({x}, {axis})", rt)

    def _emit_CONCATENATION(self, op: OperatorIR) -> None:
        parts = [self.expr(i) for i in op.inputs if i >= 0]
        axis = int(self.opts(op).get("Axis", 0))
        e = f"jnp.concatenate([{', '.join(parts)}], axis={axis})"
        e = self.act_expr(e, op)
        rt = self.effective_dtype(op.inputs[0], True)
        self.assign(op.outputs[0], e, rt)

    def _emit_PACK(self, op: OperatorIR) -> None:
        parts = [self.expr(i) for i in op.inputs if i >= 0]
        axis = int(self.opts(op).get("Axis", 0))
        rt = self.effective_dtype(op.inputs[0], True)
        self.assign(op.outputs[0], f"jnp.stack([{', '.join(parts)}], axis={axis})", rt)

    def _emit_UNPACK(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        num = int(self.opts(op).get("Num", self.tensor(op.outputs[0]).shape[0] if self.tensor(op.outputs[0]).shape else 1))
        axis = int(self.opts(op).get("Axis", 0))
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign_multi(op.outputs, f"_unpack({x}, {num}, {axis})", [rt] * len(op.outputs))

    def _emit_TILE(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        mult = _const_ints(op, self.sg, 1, "multiples")
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"jnp.tile({x}, {tuple(mult)!r})", rt)

    def _emit_PAD(self, op: OperatorIR) -> None:
        self._pad(op)

    def _emit_PADV2(self, op: OperatorIR) -> None:
        self._pad(op, with_value=True)

    def _pad(self, op: OperatorIR, with_value: bool = False) -> None:
        x = self.raw(op.inputs[0])
        pads = _const_ints(op, self.sg, 1, "paddings")
        if len(pads) % 2:
            raise OpBuildError(op, "PAD paddings length must be even")
        pairs = [(pads[i], pads[i + 1]) for i in range(0, len(pads), 2)]
        if with_value:
            val = _const_scalar(op, self.sg, 2, "constant value")
            e = f"jnp.pad({x}, {pairs!r}, constant_values={val!r})"
        else:
            e = f"jnp.pad({x}, {pairs!r})"
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], e, rt)

    def _emit_SLICE(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        begin = _const_ints(op, self.sg, 1, "begin")
        size = _const_ints(op, self.sg, 2, "size")
        in_shape = self.tensor(op.inputs[0]).shape
        spec: List[Any] = []
        for d, (b, sz) in enumerate(zip(begin, size)):
            dim = in_shape[d] if d < len(in_shape) else None
            if sz == -1:
                if b == 0:
                    spec.append(slice(None, None, None))
                else:
                    spec.append(slice(b, None, None))
            elif dim is not None and b == 0 and sz == dim:
                spec.append(slice(None, None, None))
            else:
                spec.append(slice(b, b + sz if sz >= 0 else None, None))
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"{x}[{tuple(spec)!r}]", rt)

    def _emit_TRANSPOSE(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        perm = _const_ints(op, self.sg, 1, "perm")
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"jnp.transpose({x}, {tuple(perm)!r})", rt)

    def _emit_STRIDED_SLICE(self, op: OperatorIR) -> None:
        o = self.opts(op)
        x = self.raw(op.inputs[0])
        begin = _const_ints(op, self.sg, 1, "begin")
        end = _const_ints(op, self.sg, 2, "end")
        strides = _const_ints(op, self.sg, 3, "strides")
        shape = self.tensor(op.inputs[0]).shape
        if any(d < 0 for d in shape):
            raise OpBuildError(op, "STRIDED_SLICE on a dynamic-shape tensor is not supported")
        try:
            spec = build_static_slice(
                shape,
                begin,
                end,
                strides,
                int(o.get("BeginMask", 0)),
                int(o.get("EndMask", 0)),
                int(o.get("EllipsisMask", 0)),
                int(o.get("NewAxisMask", 0)),
                int(o.get("ShrinkAxisMask", 0)),
            )
        except ValueError as exc:
            raise OpBuildError(op, f"STRIDED_SLICE lowering failed: {exc}") from exc
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"{x}[{spec!r}]", rt)

    def _reduce(self, op: OperatorIR, fn: str) -> None:
        x = self.expr(op.inputs[0])
        axes = _const_ints(op, self.sg, 1, "axes")
        keep = bool(self.opts(op).get("KeepDims", False))
        axis = tuple(axes)
        self.assign(
            op.outputs[0],
            f"{fn}({x}, axis={axis!r}, keepdims={keep!r})",
            self.compute_dtype(op.outputs[0]),
        )

    def _emit_MEAN(self, op: OperatorIR) -> None:
        self._reduce(op, "jnp.mean")

    def _emit_SUM(self, op: OperatorIR) -> None:
        self._reduce(op, "jnp.sum")

    def _emit_REDUCE_PROD(self, op: OperatorIR) -> None:
        self._reduce(op, "jnp.prod")

    def _emit_REDUCE_MAX(self, op: OperatorIR) -> None:
        self._reduce(op, "jnp.max")

    def _emit_REDUCE_MIN(self, op: OperatorIR) -> None:
        self._reduce(op, "jnp.min")

    def _emit_GATHER(self, op: OperatorIR) -> None:
        o = self.opts(op)
        if int(o.get("BatchDims", 0)) != 0:
            raise OpBuildError(op, "GATHER with batch_dims != 0 is not supported")
        data = self.expr(op.inputs[0])
        idx = self.expr(op.inputs[1], want_float=False)
        axis = int(o.get("Axis", 0))
        rt = self.effective_dtype(op.inputs[0], True)
        self.assign(op.outputs[0], f"jnp.take({data}, {idx}, axis={axis})", rt)

    def _emit_GATHER_ND(self, op: OperatorIR) -> None:
        data = self.expr(op.inputs[0])
        idx = self.expr(op.inputs[1], want_float=False)
        rt = self.effective_dtype(op.inputs[0], True)
        self.assign(op.outputs[0], f"_gather_nd({data}, {idx})", rt)

    def _emit_ONE_HOT(self, op: OperatorIR) -> None:
        idx = self.expr(op.inputs[0], want_float=False)
        depth = int(_const_scalar(op, self.sg, 1, "depth"))
        on = _const_scalar(op, self.sg, 2, "on value")
        off = _const_scalar(op, self.sg, 3, "off value")
        axis = int(self.opts(op).get("Axis", -1))
        out_t = self.tensor(op.outputs[0])
        dtype = out_t.dtype if out_t.dtype in _FLOAT_TYPES else "FLOAT32"
        e = f"jax.nn.one_hot({idx}, {depth}, axis={axis}, dtype={_TFLITE_TO_JNP[dtype]})"
        if float(on) != 1.0 or float(off) != 0.0:
            e = f"({float(on)!r} * {e} + {float(off)!r} * (1 - {e}))"
        self.assign(op.outputs[0], e, dtype)

    def _emit_SHAPE(self, op: OperatorIR) -> None:
        x = self.expr(op.inputs[0], want_float=False)
        out_t = self.tensor(op.outputs[0])
        dtype = out_t.dtype if out_t.dtype in ("INT32", "INT64") else "INT32"
        self.assign(
            op.outputs[0],
            f"jnp.asarray({x}.shape, dtype={_TFLITE_TO_JNP[dtype]})",
            dtype,
        )

    def _emit_FILL(self, op: OperatorIR) -> None:
        dims = _const_ints(op, self.sg, 0, "dims")
        value = _const_scalar(op, self.sg, 1, "value")
        out_t = self.tensor(op.outputs[0])
        dtype = out_t.dtype
        if dtype not in _TFLITE_TO_JNP:
            raise OpBuildError(op, f"FILL output dtype {dtype} is unsupported")
        self.assign(
            op.outputs[0],
            f"jnp.full({tuple(dims)!r}, {value!r}, dtype={_TFLITE_TO_JNP[dtype]})",
            dtype,
        )

    def _emit_ZEROS_LIKE(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"jnp.zeros_like({x})", rt)

    def _emit_CAST(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        out_t = self.tensor(op.outputs[0])
        if out_t.dtype not in _TFLITE_TO_JNP:
            raise OpBuildError(op, f"CAST to {out_t.dtype} is unsupported")
        self.assign(op.outputs[0], f"{x}.astype({_TFLITE_TO_JNP[out_t.dtype]})", out_t.dtype)

    def _emit_DEQUANTIZE(self, op: OperatorIR) -> None:
        in_t = self.tensor(op.inputs[0])
        x = self.raw(op.inputs[0])
        rt = self.runtime_dtype.get(op.inputs[0], in_t.dtype)
        if _is_quantized_int(in_t) and rt in _QUANTIZED_INTS:
            q = in_t.quantization
            zps = q.zero_point if q.zero_point else [0]
            e = (
                f"_dequant({x}, {[float(v) for v in q.scale]!r}, "
                f"{[int(v) for v in zps]!r}, axis={int(q.quantized_dimension)})"
            )
        else:
            e = f"jnp.asarray({x}).astype(jnp.float32)"
        self.assign(op.outputs[0], e, "FLOAT32")

    def _emit_QUANTIZE(self, op: OperatorIR) -> None:
        x = self.expr(op.inputs[0])
        out_t = self.tensor(op.outputs[0])
        if not _is_quantized_int(out_t):
            if out_t.dtype not in _TFLITE_TO_JNP:
                raise OpBuildError(op, f"QUANTIZE output dtype {out_t.dtype} is unsupported")
            self.assign(op.outputs[0], f"{x}.astype({_TFLITE_TO_JNP[out_t.dtype]})", out_t.dtype)
            return
        q = out_t.quantization
        zps = q.zero_point if q.zero_point else [0]
        e = (
            f"_quantize({x}, {[float(v) for v in q.scale]!r}, "
            f"{[int(v) for v in zps]!r}, {DTYPE_TO_NUMPY[out_t.dtype]!r}, "
            f"axis={int(q.quantized_dimension)})"
        )
        self.assign(op.outputs[0], e, out_t.dtype)

    def _emit_ARG_MAX(self, op: OperatorIR) -> None:
        self._arg(op, "jnp.argmax")

    def _emit_ARG_MIN(self, op: OperatorIR) -> None:
        self._arg(op, "jnp.argmin")

    def _arg(self, op: OperatorIR, fn: str) -> None:
        x = self.expr(op.inputs[0])
        axis = int(_const_scalar(op, self.sg, 1, "axis")) if len(op.inputs) > 1 and op.inputs[1] >= 0 else 0
        out_t = self.tensor(op.outputs[0])
        dtype = out_t.dtype if out_t.dtype in ("INT32", "INT64") else "INT32"
        self.assign(op.outputs[0], f"{fn}({x}, axis={axis}).astype({_TFLITE_TO_JNP[dtype]})", dtype)

    def _emit_BATCH_MATMUL(self, op: OperatorIR) -> None:
        o = self.opts(op)
        a = self.expr(op.inputs[0])
        b = self.expr(op.inputs[1])
        e = (
            f"_batch_matmul({a}, {b}, adj_x={bool(o.get('AdjX', False))!r}, "
            f"adj_y={bool(o.get('AdjY', False))!r})"
        )
        self.assign(op.outputs[0], e, self.compute_dtype(op.outputs[0]))

    def _emit_RESIZE_BILINEAR(self, op: OperatorIR) -> None:
        o = self.opts(op)
        x = self.expr(op.inputs[0])
        size = _const_ints(op, self.sg, 1, "size")
        self.assign(
            op.outputs[0],
            f"_resize_bilinear({x}, {tuple(size)!r}, align_corners={bool(o.get('AlignCorners', False))!r}, "
            f"half_pixel_centers={bool(o.get('HalfPixelCenters', False))!r})",
            self.compute_dtype(op.outputs[0]),
        )

    def _emit_RESIZE_NEAREST_NEIGHBOR(self, op: OperatorIR) -> None:
        o = self.opts(op)
        x = self.expr(op.inputs[0])
        size = _const_ints(op, self.sg, 1, "size")
        self.assign(
            op.outputs[0],
            f"_resize_nearest({x}, {tuple(size)!r}, align_corners={bool(o.get('AlignCorners', False))!r}, "
            f"half_pixel_centers={bool(o.get('HalfPixelCenters', False))!r})",
            self.compute_dtype(op.outputs[0]),
        )

    def _emit_SPACE_TO_DEPTH(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        block = int(self.opts(op).get("BlockSize", 1))
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"_space_to_depth({x}, {block})", rt)

    def _emit_DEPTH_TO_SPACE(self, op: OperatorIR) -> None:
        x = self.raw(op.inputs[0])
        block = int(self.opts(op).get("BlockSize", 1))
        rt = self.runtime_dtype.get(op.inputs[0], self.tensor(op.inputs[0]).dtype)
        self.assign(op.outputs[0], f"_depth_to_space({x}, {block})", rt)

    def _emit_TOPK_V2(self, op: OperatorIR) -> None:
        x = self.expr(op.inputs[0])
        k = int(_const_scalar(op, self.sg, 1, "k"))
        vals_t = self.tensor(op.outputs[0])
        idx_t = self.tensor(op.outputs[1])
        self.assign_multi(
            op.outputs,
            f"jax.lax.top_k({x}, {k})",
            [self.compute_dtype(op.outputs[0]), idx_t.dtype if idx_t.dtype in ("INT32", "INT64") else "INT32"],
        )

    # -- driver -----------------------------------------------------------
    def run(self) -> None:
        for op in self.sg.operators:
            try:
                self.emit(op)
            except OpBuildError as exc:
                if exc.op_index is None:
                    exc.op_index = op.index
                    exc.op_name = op.op
                self.errors.append(exc)
            except Exception as exc:  # keep scanning so all problems get reported
                self.errors.append(OpBuildError(op, f"{type(exc).__name__}: {exc}"))

    def apply_source(self) -> str:
        lines: List[str] = []
        for k, spec in enumerate(self.inputs):
            ti = spec.tensor_index
            var = self.input_var[ti]
            jnp_dtype = _TFLITE_TO_JNP.get(spec.dtype)
            if jnp_dtype is None:
                raise UnsupportedModel(
                    "dtype_unsupported",
                    f"input tensor {ti} has unsupported dtype {spec.dtype}",
                    self.ir,
                    self.sg,
                    self.signature,
                    self.sg.unsupported(),
                )
            lines.append(f"    {var} = jnp.asarray(xs[{k}], dtype={jnp_dtype})")
            shape = tuple(int(d) for d in spec.shape)
            lines.append(
                f"    if {var}.shape != {shape!r}: raise ValueError("
                f"\"input {k} {spec.name!r}: expected shape {shape!r}, got %s\" % ({var}.shape,))"
            )
        if self.params_used:
            lines.append("")
            for ti in sorted(self.params_used):
                t = self.tensor(ti)
                lines.append(f"    c{ti} = params['c{ti}']  # {_clean(t.name)}")
        if self.lines:
            lines.append("")
            lines.extend("    " + ln for ln in self.lines)
        lines.append("")
        out_vars = []
        for k, spec in enumerate(self.outputs):
            ti = spec.tensor_index
            e = self.raw(ti)
            rt = self.runtime_dtype.get(ti, spec.dtype)
            final = self._finalize(ti, e, rt)
            if final == e:
                out_vars.append(e)
            else:
                lines.append(f"    y{k} = {final}")
                out_vars.append(f"y{k}")
        if len(out_vars) == 1:
            lines.append(f"    return {out_vars[0]}")
        else:
            lines.append(f"    return ({', '.join(out_vars)},)")
        return "\n".join(lines)

    def _finalize(self, ti: int, expr: str, runtime: str) -> str:
        t = self.tensor(ti)
        if _is_quantized_int(t) and runtime not in _QUANTIZED_INTS:
            q = t.quantization
            zps = q.zero_point if q.zero_point else [0]
            return (
                f"_quantize({expr}, {[float(v) for v in q.scale]!r}, "
                f"{[int(v) for v in zps]!r}, {DTYPE_TO_NUMPY[t.dtype]!r}, "
                f"axis={int(q.quantized_dimension)})"
            )
        if t.dtype in _TFLITE_TO_JNP and runtime != t.dtype and (
            t.dtype in _FLOAT_TYPES or t.dtype in _INT_TYPES or t.dtype == "BOOL"
        ):
            # Preserve declared output dtype (e.g. float16/int32), never leave
            # float64 leakage from numpy.
            return f"{expr}.astype({_TFLITE_TO_JNP[t.dtype]})"
        return expr


def _clean(name: str) -> str:
    return " ".join(str(name).replace("\n", " ").split())[:70]


def _select(ir: ModelIR, subgraph: Optional[int], signature: Optional[str]):
    return tflite_ir.choose_subgraph(ir, subgraph=subgraph, signature=signature)


def _validate_static(ir: ModelIR, sg: SubGraphIR, sig: Optional[SignatureIR]) -> None:
    if sig is not None:
        ins = [ti for _, ti in sig.inputs]
        outs = [ti for _, ti in sig.outputs]
    else:
        ins = list(sg.inputs)
        outs = list(sg.outputs)
    for ti in ins + outs:
        t = sg.tensor(ti)
        if t.dtype in ("STRING", "RESOURCE", "VARIANT", "INT4"):
            raise UnsupportedModel(
                "dtype_unsupported",
                f"tensor {ti} ({t.name}) has unsupported dtype {t.dtype}",
                ir,
                sg,
                sig,
                sg.unsupported(),
            )
        if any(d < 0 for d in t.shape):
            raise UnsupportedModel(
                "dynamic_shape_unsupported",
                f"tensor {ti} ({t.name}) has dynamic shape {t.shape}",
                ir,
                sg,
                sig,
                sg.unsupported(),
            )


def _failure_status(sg: SubGraphIR, unsupported: Sequence[Dict[str, Any]]) -> str:
    ops = {u["op"] for u in unsupported}
    if ops & STATEFUL_OPS:
        return "stateful_unsupported"
    if "CUSTOM" in ops:
        return "custom_op_unsupported"
    if any("dtype" in u["reason"] for u in unsupported):
        return "dtype_unsupported"
    return "unsupported_ops"


def _format_reason(sg: SubGraphIR, unsupported: Sequence[Dict[str, Any]]) -> str:
    counts: Dict[str, List[int]] = {}
    for u in unsupported:
        counts.setdefault(u["op"], []).append(u["index"])
    parts = [
        f"{op}({len(idxs)} op{'s' if len(idxs) != 1 else ''}, first index {idxs[0]})"
        for op, idxs in sorted(counts.items())
    ]
    return (
        f"subgraph {sg.index} {sg.name!r} (role={sg.role}) cannot be lowered: "
        + ", ".join(parts)
    )


def convert_ir(
    ir: ModelIR,
    subgraph: Optional[int] = None,
    signature: Optional[str] = None,
) -> Conversion:
    """Lower one inference subgraph to JAX source + weights."""
    sg, sig, reason = _select(ir, subgraph, signature)
    unsupported = sg.unsupported()
    if unsupported:
        raise UnsupportedModel(
            _failure_status(sg, unsupported),
            _format_reason(sg, unsupported),
            ir,
            sg,
            sig,
            unsupported,
        )
    _validate_static(ir, sg, sig)

    cg = _CodeGen(ir, sg, sig)
    cg.run()
    if cg.errors:
        status = "unsupported_params"
        details = "; ".join(
            f"op {e.op_index} ({e.op_name}): {e.message}" for e in cg.errors[:8]
        )
        if len(cg.errors) > 8:
            details += f"; ... {len(cg.errors) - 8} more"
        raise UnsupportedModel(
            status,
            f"subgraph {sg.index} {sg.name!r} needs dynamic parameters: {details}",
            ir,
            sg,
            sig,
            unsupported,
            cg.errors,
        )

    apply_body = cg.apply_source()
    params: Dict[str, np.ndarray] = {}
    for ti in sorted(cg.params_used):
        t = sg.tensor(ti)
        if t.data is None:
            raise UnsupportedModel(
                "unsupported_params",
                f"constant tensor {ti} has no decodable buffer",
                ir,
                sg,
                sig,
                unsupported,
            )
        params[f"c{ti}"] = np.asarray(t.data)

    model_py = _render_module(ir, sg, sig, reason, cg, params, apply_body)
    return Conversion(
        ir=ir,
        subgraph=sg,
        signature=sig,
        selection_reason=reason,
        inputs=cg.inputs,
        outputs=cg.outputs,
        params=params,
        model_py=model_py,
    )


def convert(
    model: Any,
    subgraph: Optional[int] = None,
    signature: Optional[str] = None,
) -> Conversion:
    ir = model if isinstance(model, ModelIR) else tflite_ir.load_model(str(model))
    return convert_ir(ir, subgraph=subgraph, signature=signature)


def _render_module(
    ir: ModelIR,
    sg: SubGraphIR,
    sig: Optional[SignatureIR],
    reason: str,
    cg: _CodeGen,
    params: Dict[str, np.ndarray],
    apply_body: str,
) -> str:
    in_lines = "\n".join(
        f"    {i!r}," for i in [s.to_json() for s in cg.inputs]
    )
    out_lines = "\n".join(
        f"    {o!r}," for o in [s.to_json() for s in cg.outputs]
    )
    header = (
        f'"""Generated by tflite2jax.py -- do not edit.\n\n'
        f"Source model : {os.path.basename(ir.path)}\n"
        f"Subgraph     : {sg.index} ({sg.name!r}, role={sg.role})\n"
        f"Signature    : {sig.key if sig else None}\n"
        f"Selection    : {reason}\n"
        f"Operators    : {len(sg.operators)}\n"
        f"Inputs       : "
        + ", ".join(f"{s.name} {s.dtype}{tuple(s.shape)}" for s in cg.inputs)
        + "\nOutputs      : "
        + ", ".join(f"{s.name} {s.dtype}{tuple(s.shape)}" for s in cg.outputs)
        + "\n\n"
        "API: params = init_params(); apply(params, *inputs).  A single output is\n"
        "returned directly, multiple outputs are returned as a tuple.  The batch\n"
        "dimension is part of each declared shape (static, NHWC for 4-D tensors).\n"
        '"""\n'
        "import os\n\n"
        "import numpy as np\n"
        "import jax\n"
        "import jax.numpy as jnp\n\n"
        f"_PARAMS_FILE = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'params.npz')\n"
        f"SUBGRAPH_INDEX = {sg.index}\n"
        f"SIGNATURE_KEY = {sig.key if sig else None!r}\n"
        f"INPUTS = [\n{in_lines}\n]\n"
        f"OUTPUTS = [\n{out_lines}\n]\n"
    )
    body = (
        "\n"
        + _RUNTIME_SOURCE.strip()
        + "\n\n\n"
        "def init_params():\n"
        '    """Load the weights written next to this file."""\n'
        "    with np.load(_PARAMS_FILE) as z:\n"
        "        return {k: jnp.asarray(z[k]) for k in z.files}\n"
        "\n\n"
        "def apply(params, *inputs):\n"
        '    """Run the graph.  Returns one array or a tuple for multiple outputs."""\n'
        "    if len(inputs) == 1 and isinstance(inputs[0], (list, tuple)):\n"
        "        inputs = tuple(inputs[0])\n"
        f"    if len(inputs) != {len(cg.inputs)}:\n"
        f"        raise ValueError('expected {len(cg.inputs)} input arrays, got %d' % len(inputs))\n"
        "    xs = inputs\n"
        + apply_body
        + "\n"
    )
    return header + body


# ---------------------------------------------------------------------------
# Output writing
# ---------------------------------------------------------------------------
def write_conversion(conv: Conversion, outdir: str) -> None:
    os.makedirs(outdir, exist_ok=True)
    with open(os.path.join(outdir, "model.py"), "w", encoding="utf-8") as f:
        f.write(conv.model_py)
    np.savez_compressed(os.path.join(outdir, "params.npz"), **conv.params)
    payload = conv.ir.to_json()
    payload["conversion"] = conv.metadata()
    with open(os.path.join(outdir, "ir.json"), "w", encoding="utf-8") as f:
        json.dump(payload, f, indent=2, sort_keys=True)
        f.write("\n")


def write_unsupported(exc: UnsupportedModel, outdir: str) -> Dict[str, Any]:
    os.makedirs(outdir, exist_ok=True)
    payload = exc.to_json()
    payload["model_conversion"] = {
        "status": "unsupported",
        "reason": exc.reason,
        "subgraph_index": exc.sg.index,
        "signature_key": exc.signature.key if exc.signature else None,
    }
    with open(os.path.join(outdir, "unsupported.json"), "w", encoding="utf-8") as f:
        json.dump(payload, f, indent=2, sort_keys=True)
        f.write("\n")
    ir_payload = exc.ir.to_json()
    ir_payload["conversion"] = {
        "status": exc.status,
        "reason": exc.reason,
        "subgraph_index": exc.sg.index,
        "subgraph_name": exc.sg.name,
        "subgraph_role": exc.sg.role,
        "signature_key": exc.signature.key if exc.signature else None,
        "unsupported_ops": exc.unsupported_ops,
    }
    with open(os.path.join(outdir, "ir.json"), "w", encoding="utf-8") as f:
        json.dump(ir_payload, f, indent=2, sort_keys=True)
        f.write("\n")
    return payload


# ---------------------------------------------------------------------------
# CLI
# ---------------------------------------------------------------------------
def _default_outdir(model_path: str) -> str:
    stem = os.path.splitext(os.path.basename(model_path))[0]
    return os.path.join("converted", stem)


def main(argv: Optional[Sequence[str]] = None) -> int:
    ap = argparse.ArgumentParser(description="Convert a TFLite subgraph to standalone JAX code")
    ap.add_argument("model", help="path to .tflite")
    ap.add_argument("--out", default=None, help="output directory (default: converted/<model>)")
    ap.add_argument("--subgraph", type=int, default=None, help="subgraph index to convert")
    ap.add_argument("--signature", default=None, help="signature key to convert")
    ap.add_argument("--no-verify", action="store_true", help="skip reference verification")
    ap.add_argument("--quiet", action="store_true")
    args = ap.parse_args(argv)

    outdir = args.out or _default_outdir(args.model)
    ir = tflite_ir.load_model(args.model, with_stats=False)
    try:
        conv = convert_ir(ir, subgraph=args.subgraph, signature=args.signature)
    except UnsupportedModel as exc:
        payload = write_unsupported(exc, outdir)
        print(json.dumps({
            "status": payload["status"],
            "model": payload["model"],
            "subgraph_index": payload["subgraph_index"],
            "signature_key": payload["signature_key"],
            "reason": payload["reason"],
            "unsupported_ops": payload["unsupported_ops"],
            "output": outdir,
        }, indent=2, sort_keys=True))
        return 2

    write_conversion(conv, outdir)
    if not args.quiet:
        print(
            f"wrote {os.path.join(outdir, 'model.py')} "
            f"({len(conv.inputs)} input(s), {len(conv.outputs)} output(s), "
            f"{len(conv.params)} weight tensors)",
            file=sys.stderr,
        )

    if args.no_verify:
        return 0

    try:
        import verify_tflite_jax as verify
    except Exception as exc:  # litert missing is not a conversion failure
        if not args.quiet:
            print(f"verification skipped: {exc}", file=sys.stderr)
        return 0

    try:
        report = verify.verify_conversion(conv, outdir)
    except verify.VerifyError as exc:
        print(f"verification error: {exc}", file=sys.stderr)
        return 1
    if not args.quiet:
        print(verify.render_text(report), file=sys.stderr)
    return 0 if report["ok"] else 1


if __name__ == "__main__":
    raise SystemExit(main())
