"""Execute one smash-face sub-model record with its real recovered weights.

    python run_record.py --model models/tt_face_v10.0.model --record detect
    python run_record.py --model models/tt_face_v10.0.model --record mean
    python run_record.py --model models/tt_face_v10.0.model --record detect --input zeros
    python run_record.py --model models/tt_face_v10.0.model --list

What is real
------------
* container, AES-256-ECB key/config decryption and the net description text
  (see ``descriptor.py`` and ``GAP_CLOSURE.md``);
* every layer, its geometry, its bottom/top blob names, the graph topology;
* every weight byte and every bias word carved out of the record's weight
  buffer (see ``carve_weights``).

What is *not* verified
----------------------
The integer weights are fixed-point (``ints[9]`` = 1 -> int8, 2 -> int16,
bias = int32, both verified by byte-exact buffer closure).  The per-tensor
dequantisation scale was not recovered from ``libeffect.so``; ``--wscale``
(default 1.0) applies ``w * 2**-wscale``.  Consequently the numeric output
is *not* claimed to be bit-exact with the effect SDK -- it is the correct
graph over the correct weights up to that one open constant.
"""

from __future__ import annotations

import argparse
import sys
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Tuple

import numpy as np

import descriptor as D

WEIGHTED = ("Convolution", "DepthwiseSeparableConvolution",
            "DepthwiseSeparableConvolutionV2", "InnerProduct")


@dataclass
class Tensor:
    w: np.ndarray              # dequantised weights
    b: np.ndarray              # dequantised bias
    raw_weight_bytes: int
    raw_bias_bytes: int


def layer_shapes(net: D.Net) -> List[Tuple[D.Layer, Tuple[int, int, int, int], Tuple[int, int]]]:
    """Infer (layer, out_shape, (num_weight, num_bias)) for the whole graph.

    Shape convention inside the net text: ``(num, channel, height, width)``.
    """
    blobs: Dict[str, Tuple[int, int, int, int]] = {}
    out = []
    for i, lay in enumerate(net.layers):
        t = lay.type
        if i == 0:                                     # data / DataV2 input
            n, h, w, c = lay.ints[0], lay.ints[1], lay.ints[2], lay.ints[3]
            blobs["data"] = (n, c, h, w)
            out.append((lay, (n, c, h, w), (0, 0)))
            continue
        # `Slice` / `Split` name several tops on one line; the simple tracker
        # here keeps one shape per name, so an unknown bottom falls back to the
        # most recent shape and the record is reported with a non-zero
        # `weight_layout_residue` instead of being silently trusted.
        last = out[-1][1] if out else (1, 1, 1, 1)
        bots = [blobs.get(b, last) for b in lay.bottoms] or [last]
        ib = bots[0]
        if t == "Convolution":
            oc, kh, kw, sh, sw, ph, pw = lay.ints[0:7]
            oh = (ib[2] + 2 * ph - kh) // sh + 1
            ow = (ib[3] + 2 * pw - kw) // sw + 1
            oshape = (ib[0], oc, oh, ow)
            prm = (oc * ib[1] * kh * kw, oc)
        elif t.startswith("DepthwiseSeparable"):
            ch, kh, kw, sh, sw, ph, pw = lay.ints[0:7]
            oh = (ib[2] + 2 * ph - kh) // sh + 1
            ow = (ib[3] + 2 * pw - kw) // sw + 1
            oshape = (ib[0], ch, oh, ow)
            prm = (ch * kh * kw, ch)
        elif t == "InnerProduct":
            oc = lay.ints[0]
            oshape = (ib[0], oc, 1, 1)
            prm = (oc * ib[1] * ib[2] * ib[3], oc)
        elif t.startswith("Pooling"):
            kh = lay.ints[0]
            if kh >= ib[2]:
                oshape = (ib[0], ib[1], 1, 1)
            else:
                oshape = (ib[0], ib[1], max(1, ib[2] // max(1, lay.ints[2])),
                          max(1, ib[3] // max(1, lay.ints[3])))
            prm = (0, 0)
        else:                                          # Eltwise / Softmax / ...
            oshape = ib
            prm = (0, 0)
        if lay.top:
            blobs[lay.top] = oshape
        out.append((lay, oshape, prm))
    return out


def carve_weights(net: D.Net, buf: bytes,
                  wscale: float = 0.0) -> (Dict[str, Tensor], dict):
    """Carve the record weight buffer.

    Verified layout (byte-exact closure on ``detect`` 14448 B and ``image``
    81880 B; first ``detect`` bias block reads [1410,2945,2168,2135,2027,
    -396,8749,9263] as int32, i.e. 8 sane fixed-point biases):

        per weight-bearing layer, in net order:
            int8|int16 weights   n * dtype_bytes
            int32 bias           num_out * 4
    """
    info = layer_shapes(net)
    need = sum(nw * lay.dtype_bytes + nb * 4
               for lay, _s, (nw, nb) in info if lay.type in WEIGHTED and nw)
    # Records whose buffer is 4 B/param longer than the int8/int16 rule (e.g.
    # `pout`, residue 384 = the InnerProduct tensor promoted to float32) are
    # retried with InnerProduct weights as float32.
    ip_f32 = (need != len(buf) and
              need + sum(3 * nw for lay, _s, (nw, nb) in info
                         if lay.type == "InnerProduct" and nw) == len(buf))
    tensors: Dict[str, Tensor] = {}
    o = 0
    for lay, oshape, (nw, nb) in info:
        if lay.type not in WEIGHTED or nw == 0:
            continue
        dt = 4 if (ip_f32 and lay.type == "InnerProduct") else lay.dtype_bytes
        npdt = "<f4" if dt == 4 and ip_f32 else D.DTYPE_NP[lay.dtype_bytes]
        nb_bytes = nw * dt
        b_bytes = nb * 4
        wraw = np.frombuffer(buf, dtype=npdt,
                             count=nw, offset=o) if nb_bytes else np.zeros(0)
        o += nb_bytes
        braw = np.frombuffer(buf, dtype="<i4", count=nb, offset=o) if b_bytes else np.zeros(0)
        o += b_bytes
        w = wraw.astype(np.float64) * (2.0 ** -wscale)
        b = braw.astype(np.float64) * (2.0 ** -wscale)
        if lay.type == "InnerProduct":
            w = w.reshape(nb, -1)
        elif lay.type.startswith("DepthwiseSeparable"):
            ch, kh, kw = lay.ints[0], lay.ints[1], lay.ints[2]
            w = w.reshape(ch, 1, kh, kw)
        else:
            oc, kh, kw = lay.ints[0], lay.ints[1], lay.ints[2]
            w = w.reshape(oc, -1, kh, kw)
        tensors[lay.top] = Tensor(w, b, nb_bytes, b_bytes)
    stats = {"weight_bytes_consumed": o, "buffer_len": len(buf),
             "closed": o == len(buf), "residue": len(buf) - o,
             "inner_product_f32": bool(ip_f32)}
    return tensors, stats


def _conv(x: np.ndarray, w: np.ndarray, b: np.ndarray, stride, pad) -> np.ndarray:
    n, cin, h, wd = x.shape
    oc, cin_w, kh, kw = w.shape
    assert cin == cin_w, (cin, cin_w)
    sh, sw = stride
    ph, pw = pad
    xp = np.pad(x, ((0, 0), (0, 0), (ph, ph), (pw, pw)))
    oh = (h + 2 * ph - kh) // sh + 1
    ow = (wd + 2 * pw - kw) // sw + 1
    out = np.zeros((n, oc, oh, ow), dtype=np.float64)
    y = np.lib.stride_tricks.sliding_window_view(xp, (kh, kw), axis=(2, 3))
    y = y[:, :, ::sh, ::sw][:, :, :oh, :ow]                 # (n,cin,oh,ow,kh,kw)
    for i in range(oc):
        out[:, i] = np.einsum("nchwkl,ckl->nhw", y, w[i])
    return out + b.reshape(1, -1, 1, 1)


def _dwconv(x: np.ndarray, w: np.ndarray, b: np.ndarray, stride, pad) -> np.ndarray:
    n, c, h, wd = x.shape
    _, _, kh, kw = w.shape
    sh, sw = stride
    ph, pw = pad
    xp = np.pad(x, ((0, 0), (0, 0), (ph, ph), (pw, pw)))
    oh = (h + 2 * ph - kh) // sh + 1
    ow = (wd + 2 * pw - kw) // sw + 1
    y = np.lib.stride_tricks.sliding_window_view(xp, (kh, kw), axis=(2, 3))
    y = y[:, :, ::sh, ::sw][:, :, :oh, :ow]
    out = np.einsum("nchwkl,ckl->nchw", y, w[:, 0])
    return out + b.reshape(1, -1, 1, 1)


def _pool(x: np.ndarray, lay: D.Layer) -> np.ndarray:
    kh, kw = lay.ints[0], lay.ints[1]
    if lay.ints[2] > 0:
        sh, sw = lay.ints[2], lay.ints[3]
    else:
        sh = sw = max(1, kh)
    ph = pw = 0
    if len(lay.ints) >= 6:
        ph, pw = lay.ints[4], lay.ints[5]
    mode = lay.mode or "MAX"
    n, c, h, wd = x.shape
    if kh >= h and kw >= wd and ph == 0 and pw == 0:
        oh = ow = 1
        y = x.reshape(n, c, 1, 1, h, wd)
    else:
        xp = np.pad(x, ((0, 0), (0, 0), (ph, ph), (pw, pw)))
        oh = (h + 2 * ph - kh) // sh + 1
        ow = (wd + 2 * pw - kw) // sw + 1
        y = np.lib.stride_tricks.sliding_window_view(xp, (kh, kw), axis=(2, 3))
        y = y[:, :, ::sh, ::sw][:, :, :oh, :ow]
    y = y.reshape(n, c, oh, ow, -1)
    return y.mean(-1) if mode == "AVE" else y.max(-1)


def forward(net: D.Net, tensors: Dict[str, Tensor],
            x: np.ndarray) -> Dict[str, np.ndarray]:
    blobs: Dict[str, np.ndarray] = {"data": x}
    info = {lay.name: (lay, oshape, prm) for lay, oshape, prm in layer_shapes(net)}
    for i, lay in enumerate(net.layers):
        t = lay.type
        if i == 0:
            continue
        if t == "Convolution":
            oc, kh, kw, sh, sw, ph, pw = lay.ints[0:7]
            y = _conv(blobs[lay.bottoms[0]], tensors[lay.top].w,
                      tensors[lay.top].b, (sh, sw), (ph, pw))
        elif t.startswith("DepthwiseSeparable"):
            ch, kh, kw, sh, sw, ph, pw = lay.ints[0:7]
            y = _dwconv(blobs[lay.bottoms[0]], tensors[lay.top].w,
                        tensors[lay.top].b, (sh, sw), (ph, pw))
        elif t == "InnerProduct":
            src = blobs[lay.bottoms[0]]
            v = src.reshape(src.shape[0], -1)
            y = (v @ tensors[lay.top].w.T + tensors[lay.top].b)
            y = y.reshape(y.shape[0], -1, 1, 1)
        elif t.startswith("Pooling"):
            y = _pool(blobs[lay.bottoms[0]], lay)
        elif t.startswith("Eltwise"):
            y = sum(blobs[b] for b in lay.bottoms)
        elif t.startswith("Concat"):
            axis = lay.ints[0] if lay.ints else 1
            srcs = [blobs[b] for b in lay.bottoms]
            ax = {1: 1, 2: 2, 3: 3}.get(axis, 1)
            y = np.concatenate(srcs, axis=ax)
        elif t == "Softmax":
            src = blobs[lay.bottoms[0]].reshape(blobs[lay.bottoms[0]].shape[0], -1)
            e = np.exp(src - src.max(axis=1, keepdims=True))
            y = (e / e.sum(axis=1, keepdims=True)).reshape(blobs[lay.bottoms[0]].shape)
        elif t in ("ReLU", "Relu"):
            y = np.maximum(blobs[lay.bottoms[0]], 0)
        elif t in ("Sigmoid",):
            s = blobs[lay.bottoms[0]]
            y = 1.0 / (1.0 + np.exp(-s))
        else:
            raise NotImplementedError(f"layer type {t!r} ({lay.name})")
        blobs[lay.top] = y
    return blobs


def run(model: str, record: str, wscale: float = 0.0,
        seed: int = 0, input_mode: str = "ramp",
        auth: Optional[str] = None) -> dict:
    pkg = D.ModelPackage.parse(model)
    rec = next((r for r in pkg.records if r.name == record), None)
    if rec is None:
        raise SystemExit(f"no record {record!r} in {model}; "
                         f"have {[r.name for r in pkg.records]}")
    auth = auth or D.AUTH_CODES.get(pkg.version)
    if auth is None:
        raise SystemExit(f"no auth_code for model version {pkg.version!r}")

    plain = rec.config_text(auth)
    if record == "mean":
        # Not a network: a pure data record.  `detect`'s config plaintext is
        # the single byte "1"; the 1905 float32 prior table lives in `weight`.
        vals = np.frombuffer(rec.weight, dtype="<f4")
        return {"record": record, "kind": "data", "count": int(vals.size),
                "min": float(vals.min()), "max": float(vals.max()),
                "checksum_a": rec.checksum_a, "checksum_b": rec.checksum_b}

    net = D.parse_net(plain.decode("ascii"))
    tensors, wstats = carve_weights(net, rec.weight, wscale)
    in_shape = layer_shapes(net)[0][1]
    n, c, h, w = in_shape
    if input_mode == "zeros":
        x = np.zeros(in_shape, dtype=np.float64)
    elif input_mode == "random":
        x = np.random.default_rng(seed).standard_normal(in_shape)
    else:                                   # deterministic ramp, no RNG
        x = np.linspace(-1.0, 1.0, n * c * h * w, dtype=np.float64).reshape(in_shape)
    blobs = forward(net, tensors, x)
    last = net.layers[-1].top
    out = blobs[last]
    return {
        "record": record, "kind": "net", "tag": net.tag,
        "version": net.version, "declared_layers": net.declared_layers,
        "parsed_layers": len(net.layers), "input_shape": list(in_shape),
        "output_blob": last, "output_shape": list(out.shape),
        "output_min": float(out.min()), "output_max": float(out.max()),
        "output_mean": float(out.mean()),
        "output_head": [round(float(v), 6) for v in out.reshape(-1)[:8]],
        "weighted_tensors": len(tensors),
        "weight_bytes_consumed": wstats["weight_bytes_consumed"],
        "weight_buffer_len": wstats["buffer_len"],
        "weight_layout_closed": wstats["closed"],
        "weight_layout_residue": wstats["residue"],
        "inner_product_f32": wstats["inner_product_f32"],
        "wscale": wscale,
    }


def main(argv=None) -> int:
    ap = argparse.ArgumentParser(description=__doc__,
                                 formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--model", required=True)
    ap.add_argument("--record")
    ap.add_argument("--wscale", type=float, default=0.0,
                    help="dequantisation exponent: w * 2**-wscale (default 0)")
    ap.add_argument("--input", default="ramp", choices=("ramp", "zeros", "random"))
    ap.add_argument("--seed", type=int, default=0)
    ap.add_argument("--list", action="store_true")
    a = ap.parse_args(argv)

    pkg = D.ModelPackage.parse(a.model)
    if a.list or not a.record:
        print(f"{a.model}: version={pkg.version!r} records={len(pkg.records)} "
              f"auth_known={pkg.version in D.AUTH_CODES}")
        for r in pkg.records:
            state = "ok"
            if pkg.version in D.AUTH_CODES:
                try:
                    D.parse_net(r.config_text(D.AUTH_CODES[pkg.version]).decode("ascii"))
                except Exception as e:                 # noqa: BLE001
                    state = f"no-net ({e})"
            else:
                state = "no-auth"
            print(f"  {r.name:11s} cfg={len(r.config):6d} plain={r.config_plain_len:6d} "
                  f"weight={len(r.weight):7d} {state}")
        return 0

    import json
    print(json.dumps(run(a.model, a.record, a.wscale, a.seed, a.input), indent=2))
    return 0


if __name__ == "__main__":
    sys.exit(main())
