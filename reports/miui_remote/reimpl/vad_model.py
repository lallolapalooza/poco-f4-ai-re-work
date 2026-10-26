"""Runnable JAX reimplementation of MiBrain's on-device VAD ("CornVad", model vad_2.1.0_0).

Everything here is reconstructed statically from libmibrainvad2.so:
  * container: "soil-binn" blob embedded as `mdata_vad` (symbol, 1,132,916 bytes)
  * model: Soil component graph, parsed with the loader semantics recovered from
    ComponentLoad / Component*Load (see native/ghidra_vad2.c)
  * weights: the three affine layers are the *real* extracted floats.

Layer chain recovered from the binary stream (9 components, ids 0..8):
    0 input (840 = 40 cepstra x 21 frames)
    1 affine   840 -> 300        (W1 252000 floats + b1 300)   -> F1
    2 pnorm    out_size = 100    (300 -> 100, window 3)
    3 affine   100 -> 300        (W3 30000 + b3 300)           -> F2
    4 pnorm    out_size = 100    (300 -> 100)
    5 normalize(100, need_divide=1, target_rms=1.0, source=internal)
    6 normalize(100, same)
    7 affine   100 -> 2          (W7 200 + b7 2)               -> F3
    8 softmax  (num-pdf = 2)

`init_params()` returns the real affine weights; `apply()` is the JAX forward
pass.  pnorm / normalize follow the loader's field names (out_size, target_rms,
need_divide); their exact arithmetic is ARCHITECTURE_ONLY and marked as such.
"""
from __future__ import annotations

import json

import numpy as np
import jax
import jax.numpy as jnp

from soil_binn import SoilBinn

# ModelComponentType() -> int ids (from libmibrainvad2.so, 0x2ff70)
TYPE_NAMES = {
    0: "reserved",
    1: "input",
    2: "gru",
    3: "lstm",
    4: "convolution",
    5: "max_pooling",
    6: "splice",
    7: "padding",
    8: "affine",
    9: "relu",
    10: "tanh",
    11: "add",
    12: "reducesum",
    13: "unfixedscale",
    14: "sigmoid",
    15: "pnorm",
    16: "fixedscale",
    17: "normalize",
    18: "stats_pooling",
    19: "softmax",
    20: "embedding",
    21: "reshape",
}


class Reader:
    """Sequential reader over the Soil binary model stream (mode 7 = memory)."""

    def __init__(self, data: bytes, off: int):
        self.d = data
        self.p = off

    def i32(self):
        v = int.from_bytes(self.d[self.p:self.p + 4], "little", signed=True)
        self.p += 4
        return v

    def f32(self):
        v = float(np.frombuffer(self.d[self.p:self.p + 4], dtype="<f4")[0])
        self.p += 4
        return v

    def floats(self, n):
        v = np.frombuffer(self.d[self.p:self.p + 4 * n], dtype="<f4").copy()
        self.p += 4 * n
        return v


def load_container(path):
    """Parse the soil-binn container and return (root dict, raw bytes)."""
    data = open(path, "rb").read()
    root = SoilBinn(data).parse()
    items = root[1]["list"][1]
    am = items[0][1]["am"]
    conf = items[1][1]["conf"]
    cmvn = items[2][1]["cmvn"]

    p = SoilBinn(data)
    c = p.parse_at(conf[1])
    cfg = {k: dict(v[1]) for k, v in c[1].items() if v[0] == "obj"}

    p = SoilBinn(data)
    p.pos = cmvn[1]
    txt = p.take(cmvn[2]).decode("utf-8", "replace")
    vals = [float(x) for x in txt.replace("[", " ").replace("]", " ").split()]

    return {
        "am": {"offset": am[1], "length": am[2]},
        "conf": cfg,
        "cmvn": vals,          # 82 = 2 x 41
        "data": data,
    }


def parse_components(container):
    """Walk the Soil binary stream exactly as ComponentLoad + LoadDependence do."""
    r = Reader(container["data"], container["am"]["offset"])
    end = container["am"]["offset"] + container["am"]["length"]
    n_comp = r.i32()
    comps = []
    for _ in range(n_comp):
        t = r.i32()
        name = TYPE_NAMES.get(t, "unknown")
        c = {"id": len(comps), "type": name}
        if t == 8:                                   # affine
            c["in"], c["out"] = r.i32(), r.i32()
            c["W"] = r.floats(c["in"] * c["out"])
            c["b"] = r.floats(c["out"])
        elif t == 15:                                # pnorm
            c["out_size"] = r.i32()
        elif t == 17:                                # normalize
            c["size"] = r.i32()
            c["need_divide"] = r.i32()
            c["need_minus"] = r.i32()
            c["need_reverse"] = r.i32()
            c["target_rms"] = r.f32()
            c["vector_source"] = r.i32()             # 0 = internal, 1 = external
            if c["vector_source"] == 1:
                c["vec"] = [r.floats(c["size"]) for _ in range(4)]
        elif t == 19:                                # softmax
            c["axis"] = r.i32()
            c["flag"] = r.i32()
        elif t in (9, 10, 11, 1, 0):                 # relu/tanh/add/input
            pass
        else:
            raise NotImplementedError("component type %d (%s) at %d" % (t, name, r.p))
        if t != 1:                                   # LoadDependence() skipped for input
            n_dep = r.i32()
            c["deps"] = [r.i32() for _ in range(n_dep)]
        else:
            c["deps"] = []
        comps.append(c)
    if r.p != end:
        raise ValueError("stream not fully consumed: %d != %d" % (r.p, end))
    return comps


def init_params(path):
    """Return {'W1','b1','W3','b3','W7','b7','meta'} with real extracted weights."""
    c = load_container(path)
    comps = parse_components(c)
    aff = [x for x in comps if x["type"] == "affine"]
    if len(aff) != 3:
        raise ValueError("expected 3 affine layers, got %d" % len(aff))
    a1, a3, a7 = aff
    params = {
        "W1": jnp.asarray(a1["W"].reshape(a1["out"], a1["in"])),   # [300, 840]
        "b1": jnp.asarray(a1["b"]),
        "W3": jnp.asarray(a3["W"].reshape(a3["out"], a3["in"])),   # [300, 100]
        "b3": jnp.asarray(a3["b"]),
        "W7": jnp.asarray(a7["W"].reshape(a7["out"], a7["in"])),   # [2, 100]
        "b7": jnp.asarray(a7["b"]),
        "meta": {
            "n_components": len(comps),
            "components": [
                {k: v for k, v in x.items() if k not in ("W", "b", "vec")}
                for x in comps
            ],
            "conf": c["conf"],
            "weights_source": "libmibrainvad2.so:mdata_vad (embedded, offset 0x4fd90)",
        },
    }
    return params


def pnorm(x, out_size, p=2.0):
    """Soil ComponentPnormPropgate (ARCHITECTURE_ONLY arithmetic).

    The loader stores only `out_size`; the window is inferred as in/out.
    y_j = (sum_{k in window_j} x_k^p)^(1/p)
    """
    t, d = x.shape
    win = d // out_size
    x = x[:, : win * out_size].reshape(t, out_size, win)
    return jnp.power(jnp.sum(jnp.power(jnp.abs(x), p), axis=-1), 1.0 / p)


def normalize(x, target_rms=1.0, need_divide=True, need_minus=False, eps=1e-6):
    """Soil ComponentNormalizePropgate with vector_source=internal."""
    if need_minus:
        x = x - jnp.mean(x, axis=-1, keepdims=True)
    if need_divide:
        rms = jnp.sqrt(jnp.mean(jnp.square(x), axis=-1, keepdims=True) + eps)
        x = x / rms * target_rms
    return x


def apply(params, x):
    """Forward pass over stacked features x: [T, 840] -> logits [T, 2]."""
    h = x @ params["W1"].T + params["b1"]          # 840 -> 300
    h = pnorm(h, 100)                              # 300 -> 100
    h = h @ params["W3"].T + params["b3"]          # 100 -> 300
    h = pnorm(h, 100)                              # 300 -> 100
    h = normalize(h, 1.0, True)                    # component 5
    h = normalize(h, 1.0, True)                    # component 6
    return h @ params["W7"].T + params["b7"]       # 100 -> 2


def posteriors(params, x):
    """Speech posterior P(class 1) for every frame."""
    return jax.nn.softmax(apply(params, x), axis=-1)[:, 1]


def model_summary(path):
    c = load_container(path)
    comps = parse_components(c)
    out = []
    for x in comps:
        y = {k: (v.tolist() if isinstance(v, np.ndarray) else v) for k, v in x.items()
             if k not in ("W", "b")}
        if "W" in x:
            y["W_shape"] = list(x["W"].shape)
            y["W_mean"] = float(x["W"].mean())
            y["W_std"] = float(x["W"].std())
            y["b_shape"] = list(x["b"].shape)
        out.append(y)
    return {
        "container": {"am_offset": c["am"]["offset"], "am_length": c["am"]["length"],
                      "cmvn_values": len(c["cmvn"])},
        "conf": c["conf"],
        "components": out,
    }


if __name__ == "__main__":
    import sys
    p = sys.argv[1] if len(sys.argv) > 1 else "../models/vad_2.1.0_0.binn"
    print(json.dumps(model_summary(p), indent=1))
