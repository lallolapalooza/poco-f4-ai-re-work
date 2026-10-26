"""TikTok/trill (com.ss.android.ugc.trill) face-model package reader + JAX pipeline.

Two independent parts:

1. `ModelPackage` -- a *verified* reader for the `.model` container used by
   `assets/model/ttfacemodel/*.model` and `assets/model/ttfaceattrmodel/*.model`.
   The layout below was recovered empirically from the three shipped files and
   cross-checked against every record in all three (see report.md, section 4).

       offset 0x00  16 bytes   opaque per-file id (not a hash of the body)
       offset 0x10  u32        total file size (== os.stat size)
       offset 0x14  u32        model name length, then the name (ASCII)
       offset 0x..  u32        record count N
       N x record:
           u32   name length, then name (ASCII)
           u32   constant 36
           32 B  per-file constant  (repeated identically in every record)
           u32   constant 32
           32 B  per-record blob
           ...   payload (opaque to us; plaintext, no container framing)

2. `pipeline` -- JAX code for the *documented* I/O contract of the two face
   models.  The tensor topology of the payload was NOT recovered (blocker in
   report.md), so `apply` is an explicit ARCHITECTURE_ONLY placeholder: it has
   the right input/output shapes and deterministic parameter init, and is not
   claimed to reproduce the shipped network.

Run:
    python model.py <model-file> [<model-file> ...]   # dump container records
    python model.py --demo                            # JAX shape demo
"""

from __future__ import annotations

import json
import os
import struct
import sys
from dataclasses import dataclass, field, asdict
from typing import List

U32 = struct.Struct("<I")


@dataclass
class Record:
    name: str
    name_offset: int
    const36: int
    key1: bytes          # 32-byte per-file constant
    const32: int
    blob2: bytes         # 32-byte per-record blob
    payload_offset: int
    payload_end: int

    @property
    def payload_size(self) -> int:
        return self.payload_end - self.payload_offset


@dataclass
class ModelPackage:
    path: str
    size: int
    file_id: bytes       # 16 opaque bytes
    declared_size: int
    model_name: str
    declared_records: int
    records: List[Record] = field(default_factory=list)

    @classmethod
    def parse(cls, path: str) -> "ModelPackage":
        with open(path, "rb") as fh:
            data = fh.read()

        file_id = data[0:16]
        (declared_size,) = U32.unpack_from(data, 0x10)
        (name_len,) = U32.unpack_from(data, 0x14)
        if not 1 <= name_len <= 127:
            raise ValueError(f"{path}: implausible model-name length {name_len}")
        name = data[0x18:0x18 + name_len].decode("ascii")
        off = 0x18 + name_len
        (n_records,) = U32.unpack_from(data, off)
        off += 4

        pkg = cls(path=path, size=len(data), file_id=file_id,
                  declared_size=declared_size, model_name=name,
                  declared_records=n_records)

        # The container does not store payload lengths: records are located by
        # their per-file 32-byte key, exactly as libeffect.so does when it
        # "Extract model name" (see report.md).  The key occurs exactly
        # `n_records` times in each shipped file.
        if n_records < 1:
            raise ValueError(f"{path}: record count {n_records}")
        (rlen,) = U32.unpack_from(data, off)
        key_pos = off + 4 + rlen + 4
        key1 = data[key_pos:key_pos + 32]
        if len(key1) != 32:
            raise ValueError(f"{path}: truncated first record")

        positions, i = [], 0
        while True:
            i = data.find(key1, i)
            if i < 0:
                break
            positions.append(i)
            i += 1
        if len(positions) != n_records:
            raise ValueError(f"{path}: key occurs {len(positions)} times, "
                             f"header declares {n_records}")

        for key_pos in positions:
            name_end = key_pos - 4          # 4-byte const-36 field follows the name
            name_off = None
            for rlen in range(1, 65):
                ln_off = name_end - rlen - 4
                if ln_off < 0:
                    break
                (cand,) = U32.unpack_from(data, ln_off)
                name = data[ln_off + 4:name_end]
                if cand == rlen and len(name) == rlen and all(32 <= c < 127 for c in name):
                    name_off = ln_off
                    break
            if name_off is None:
                raise ValueError(f"{path}: bad record name at {key_pos:#x}")
            (c36,) = U32.unpack_from(data, key_pos - 4)
            (c32,) = U32.unpack_from(data, key_pos + 32)
            blob2 = data[key_pos + 36:key_pos + 68]
            rname = data[name_off + 4:name_end].decode("ascii")
            pkg.records.append(Record(rname, name_off, c36, key1, c32, blob2,
                                      key_pos + 36 + 32, 0))

        for i, rec in enumerate(pkg.records):
            rec.payload_end = (pkg.records[i + 1].name_offset
                               if i + 1 < len(pkg.records) else len(data))
        return pkg

    def to_json(self) -> dict:
        d = asdict(self)
        for rec in d["records"]:
            rec["payload_size"] = rec["payload_end"] - rec["payload_offset"]
            rec["key1"] = rec["key1"].hex()
            rec["blob2"] = rec["blob2"].hex()
        d["file_id"] = self.file_id.hex()
        return d


# --------------------------------------------------------------------------
# JAX side.  ARCHITECTURE_ONLY (see module docstring).
# --------------------------------------------------------------------------
def _jax():
    import jax
    import jax.numpy as jnp
    return jax, jnp


def init_params(key, in_h: int = 120, in_w: int = 120, width: int = 16):
    """Deterministic parameter init for the placeholder detector/regressor."""
    jax, jnp = _jax()

    def conv(k, cin, cout, ks):
        k1, k2 = jax.random.split(k)
        w = jax.random.normal(k1, (ks, ks, cin, cout)) * jnp.sqrt(2.0 / (ks * ks * cin))
        b = jnp.zeros((cout,))
        return {"w": w, "b": b}

    k0, k1, k2, k3 = jax.random.split(key, 4)
    return {
        "c1": conv(k0, 3, width, 3),
        "c2": conv(k1, width, width * 2, 3),
        "cls": conv(k2, width * 2, 1, 1),
        "box": conv(k3, width * 2, 4, 1),
    }


def _conv(x, p, stride: int = 1):
    """NHWC conv, 'SAME' padding, stride `stride`."""
    from jax import lax
    _, jnp = _jax()
    y = lax.conv_general_dilated(
        x, p["w"], window_strides=(stride, stride), padding="SAME",
        dimension_numbers=("NHWC", "HWIO", "NHWC"))
    return jnp.maximum(y + p["b"], 0.0)


def _pool2(x):
    import jax.numpy as jnp
    n, h, w, c = x.shape
    return jnp.max(x.reshape(n, h // 2, 2, w // 2, 2, c), axis=(2, 4))


def apply(params, x):
    """x: (N, H, W, 3) float32 -> {'pout': (N,h,w,1), 'box': (N,h,w,4)}.

    Head names follow the records found in the shipped packages ('pout' is the
    objectness head, 'box' the 4-value regression head).  The topology is a
    placeholder: the real layer structure lives in the undecoded payload.
    """
    _, jnp = _jax()
    y = _conv(x, params["c1"])            # (N, H, W, 16)
    y = _pool2(y)                          # (N, H/2, W/2, 16)
    y = _conv(y, params["c2"])            # (N, H/2, W/2, 32)
    y = _pool2(y)                          # (N, H/4, W/4, 32)
    cls = _conv(y, params["cls"])         # 1x1 conv -> (N, H/4, W/4, 1)
    box = _conv(y, params["box"])         # 1x1 conv -> (N, H/4, W/4, 4)
    return {"pout": cls, "box": box}


def preprocess_bgr(img_uint8):
    """Documented preprocessing stand-in: HWC uint8 RGB -> NCHW float32 [-1,1].

    The container carries a 'mean' record whose payload begins with float32
    values around 30/74/86 (per-channel means in 0..255 units, inferred); the
    exact scale/offset used by libeffect.so was not recovered.
    """
    _, jnp = _jax()
    x = jnp.asarray(img_uint8, dtype=jnp.float32)
    x = (x - 127.5) / 127.5
    return jnp.transpose(x, (2, 0, 1))[None]


def demo():
    import numpy as np
    jax, jnp = _jax()
    key = jax.random.PRNGKey(0)
    params = init_params(key)
    x = jnp.asarray(np.random.rand(1, 120, 120, 3).astype("float32"))
    out = apply(params, x)
    print("input :", x.shape, x.dtype)
    for k, v in out.items():
        print(f"output {k}: {v.shape} {v.dtype}")
    assert out["pout"].shape == (1, 30, 30, 1), out["pout"].shape
    assert out["box"].shape == (1, 30, 30, 4), out["box"].shape
    print("ARCHITECTURE_ONLY placeholder ran OK")


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print(__doc__)
        sys.exit(2)
    if sys.argv[1] == "--demo":
        demo()
        sys.exit(0)
    for path in sys.argv[1:]:
        pkg = ModelPackage.parse(path)
        print(json.dumps(pkg.to_json(), indent=1))
