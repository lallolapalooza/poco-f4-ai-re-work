#!/usr/bin/env python3
"""JAX re-implementation of the Snapdragon NPE document model shipped in
ExtraPhotoGlobal.apk (assets/common/doc_photo/doc_model/dlc/document_model.dlc).

Everything comes from the parsed DLC protobuf (see ../ir/dlc.py):
 * quantised uint8 kernels/bias with per-tensor (min,max,step,offset);
   dequantised with x = step * (q + offset)   [verified for all 107 tensors]
 * graph: input 1x512x512x3 -> ... -> 1x512x512x1 sigmoid
 * layer ops: Conv2D / depthwise Conv2D / Conv2DTranspose / ReLU / ReLU6 /
   Sigmoid / add / concat(axis=3) / truediv by the constant 255.

The DLC is a weight-quantised, float-activation SNPE model: only the kernel
and bias buffers carry a Quantizer, none of the activation tensors do.
"""
import os
import sys
import numpy as np

import jax
import jax.numpy as jnp

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, os.path.join(HERE, "..", "ir"))
import dlc  # noqa: E402

DLC_MODEL = os.path.join(HERE, "..", "models", "dlc_extract", "model")
INPUT_NAME = "hed_input:0"
OUTPUT_NAME = "img_output:0"
INPUT_SHAPE = (1, 512, 512, 3)


def _pads(in_h, in_w, k_h, k_w, s_h, s_w, out_h, out_w, pad_before):
    """Reconstruct TF-SAME pads from the declared output size + stored pad."""
    total_h = max(0, (out_h - 1) * s_h + k_h - in_h)
    total_w = max(0, (out_w - 1) * s_w + k_w - in_w)
    pb_h, pb_w = pad_before
    pa_h = total_h - pb_h
    pa_w = total_w - pb_w
    if pa_h < 0 or pa_w < 0:
        raise ValueError(f"inconsistent pads total=({total_h},{total_w}) before=({pb_h},{pb_w})")
    return (pb_h, pa_h), (pb_w, pa_w)


def _conv(x, w, b, stride, pads):
    y = jax.lax.conv_general_dilated(
        x, jnp.asarray(w), window_strides=tuple(stride),
        padding=(pads[0], pads[1]),
        dimension_numbers=("NHWC", "HWIO", "NHWC"))
    return y + jnp.asarray(b).reshape(1, 1, 1, -1)


def _depthwise(x, w, b, stride, pads):
    # DLC stores TF depthwise filters as [kh, kw, in, multiplier]; JAX grouped
    # convolution wants [kh, kw, in/group, out] with group = in_channels.
    cin = x.shape[-1]
    # JAX grouped conv wants [kh, kw, cin/group, cout]; the DLC depthwise
    # filter [kh, kw, 1, cin] is already exactly that with group = cin.
    y = jax.lax.conv_general_dilated(
        x, jnp.asarray(w), window_strides=tuple(stride),
        padding=(pads[0], pads[1]), feature_group_count=cin,
        dimension_numbers=("NHWC", "HWIO", "NHWC"))
    return y + jnp.asarray(b).reshape(1, 1, 1, -1)


def _deconv(x, w, b, stride, out_hw):
    # JAX conv_transpose reuses the conv kernel spec [kh, kw, in, out], which is
    # exactly how the DLC stores deconv filters (verified: 64->24, 24->12, ...).
    # Its explicit `padding` is expressed in forward-convolution terms:
    #   out = (in-1)*s + k - 2*(k-1-p)  =>  p = k-1 - (raw_out-out)/2
    k_h, k_w = w.shape[0], w.shape[1]
    raw_h = (x.shape[1] - 1) * stride[0] + k_h
    raw_w = (x.shape[2] - 1) * stride[1] + k_w
    crop_h, crop_w = raw_h - out_hw[0], raw_w - out_hw[1]
    p_h, p_w = k_h - 1 - crop_h // 2, k_w - 1 - crop_w // 2
    y = jax.lax.conv_transpose(
        x, jnp.asarray(w), strides=tuple(stride), padding=((p_h, p_h), (p_w, p_w)),
        dimension_numbers=("NHWC", "HWIO", "NHWC"))
    return y + jnp.asarray(b).reshape(1, 1, 1, -1)


def _activate(x, kind, extra):
    if kind == "relu":
        return jax.nn.relu(x)
    if kind == "relu6":
        return jnp.clip(x, 0.0, 6.0)
    if kind == "sigmoid":
        return jax.nn.sigmoid(x)
    raise ValueError(f"unknown activation {kind}")


def run_graph(model, x, verbose=False):
    """Execute the DLC graph in declared layer order. Returns output + trace."""
    weights = model.weight_arrays()
    tensors = {INPUT_NAME: jnp.asarray(x, dtype=jnp.float32)}
    trace = []
    for L in model.layers:
        t = L["type"]
        name = L["name"]
        if t == "input":
            continue
        if t == "const":
            v = weights[name]["value"]
            tensors[L["outputs"][0]] = jnp.asarray(v, dtype=jnp.float32)
        elif t == "elementwise_binary":
            a = tensors[L["inputs"][0]]
            b = tensors[L["inputs"][1]]
            if L["params"]["binop"] != "div":
                raise ValueError(f"unsupported binary op {L['params']}")
            tensors[L["outputs"][0]] = a / b
        elif t in ("conv2d", "deconv2d"):
            p = L["params"]
            xin = tensors[L["inputs"][0]]
            w = weights[name]["kernel"]
            b = weights[name]["bias"]
            k_h, k_w = p["kernel"]
            out_h, out_w = L["output_dims"][1], L["output_dims"][2]
            pads = _pads(xin.shape[1], xin.shape[2], k_h, k_w,
                         p["stride"][0], p["stride"][1],
                         out_h, out_w, (p["pad"][0], p["pad"][1]))
            if t == "deconv2d":
                y = _deconv(xin, w, b, p["stride"], (out_h, out_w))
            elif w.shape[2] == 1 and w.shape[3] == xin.shape[-1]:
                y = _depthwise(xin, w, b, p["stride"], pads)
            else:
                y = _conv(xin, w, b, p["stride"], pads)
            tensors[L["outputs"][0]] = y
        elif t == "activation":
            xin = tensors[L["inputs"][0]]
            tensors[L["outputs"][0]] = _activate(
                xin, L["params"]["activation"], L["params"].get("extra"))
        elif t == "add":
            a = tensors[L["inputs"][0]]
            b = tensors[L["inputs"][1]]
            co = L["params"].get("coeffs") or [1.0, 1.0]
            tensors[L["outputs"][0]] = co[0] * a + co[1] * b
        elif t == "concat":
            xs = [tensors[n] for n in L["inputs"]]
            tensors[L["outputs"][0]] = jnp.concatenate(xs, axis=L["params"]["axis"])
        else:
            raise ValueError(f"unsupported layer {name} type {t}")

        out = tensors[L["outputs"][0]]
        declared = tuple(L["output_dims"])
        got = tuple(out.shape)
        ok = (got == declared)
        trace.append((L["index"], name, t, declared, got, ok))
        if not ok:
            raise RuntimeError(f"shape mismatch at layer {L['index']} {name}: "
                               f"declared {declared} got {got}")
        if verbose:
            print(f"  [{L['index']:3d}] {t:10s} {str(got):20s} {name[-52:]}")
    return tensors[OUTPUT_NAME], trace


def main():
    model = dlc.load(os.path.abspath(DLC_MODEL))
    print(f"DLC: {os.path.abspath(DLC_MODEL)}")
    print(f"magic={model.magic:#x} version={model.version} layers={len(model.layers)} "
          f"tensors={len(model.tensors)}")
    rng = np.random.default_rng(0)
    # synthetic document-like input: uint8 range noise + a bright rectangle
    x = rng.integers(0, 256, size=INPUT_SHAPE, dtype=np.uint8).astype(np.float32)
    x[:, 100:400, 120:390, :] = 235.0
    y, trace = run_graph(model, x, verbose=True)
    y = np.asarray(y)
    print(f"input  {x.shape} {x.dtype} range=[{x.min():.1f},{x.max():.1f}]")
    print(f"output {y.shape} {y.dtype} range=[{y.min():.4f},{y.max():.4f}] "
          f"mean={y.mean():.4f}")
    assert x.shape == INPUT_SHAPE
    assert y.shape == (1, 512, 512, 1)
    assert np.isfinite(y).all() and 0.0 <= y.min() and y.max() <= 1.0
    print(f"graph trace: {len(trace)} layers, all declared shapes matched")
    # save a PGM preview of the edge map as evidence
    preview = (y[0, :, :, 0] * 255.0).astype(np.uint8)
    with open(os.path.join(HERE, "doc_hed_output.pgm"), "wb") as f:
        f.write(b"P5\n512 512\n255\n" + preview.tobytes())
    print("wrote doc_hed_output.pgm")


if __name__ == "__main__":
    main()
