#!/usr/bin/env python3
"""Extract the weights/constants of assets/tflite_langid.tflite.jpg into params.npz.

Tensor map (subgraph 0), from ir/tflite_langid.json:

  0  Placeholder                              STRING [1]        (model input)
  1  pod/IndexToClassID                      STRING [111]      (class -> language code)
  2  head_0_ngram_1/kmeans_compression/b_matrix FLOAT32 [256,4]
  3  head_0_ngram_1/kmeans_compression/c_matrix UINT8   [5501,6]
  4  head_0_ngram_2/... b_matrix FLOAT32 [256,4]
  5  head_0_ngram_2/... c_matrix UINT8   [5501,6]
  6  head_0_ngram_3/... b_matrix FLOAT32 [256,4]
  7  head_0_ngram_3/... c_matrix UINT8   [13001,6]
  8  head_0_ngram_4/... b_matrix FLOAT32 [256,4]
  9  head_0_ngram_4/... c_matrix UINT8   [13001,6]
 10  fc1 bias  FLOAT32 [160]
 11  fc2 bias  FLOAT32 [200]
 12  fc3 bias  FLOAT32 [111]
 13  fc1 W     INT8 [160,96]   scale 0.027866341173648834  zp 0
 14  fc2 W     INT8 [200,160]  scale 0.011496654711663723  zp 0
 15  fc3 W     INT8 [111,200]  scale 0.014155043289065361  zp 0

Run:  python extract_params.py [--model ../models/tflite_langid.tflite] [--out params.npz]
"""

from __future__ import annotations

import argparse
import json
import os
import struct
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
TOOLS = os.path.abspath(os.path.join(HERE, "..", "..", "..", "tools"))
if TOOLS not in sys.path:
    sys.path.insert(0, TOOLS)

import tflite_ir  # noqa: E402


def parse_string_tensor(raw: bytes) -> list[str]:
    """TFLite STRING tensor: int32 count, int32 offsets[count], then utf-8 blobs."""
    n = struct.unpack_from("<i", raw, 0)[0]
    offs = [struct.unpack_from("<i", raw, 4 + 4 * i)[0] for i in range(n)]
    out = []
    for i, o in enumerate(offs):
        end = offs[i + 1] if i + 1 < n else len(raw)
        out.append(raw[o:end].split(b"\x00")[0].decode("utf-8"))
    return out


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--model", default=os.path.join(HERE, "..", "models", "tflite_langid.tflite"))
    ap.add_argument("--out", default=os.path.join(HERE, "params.npz"))
    a = ap.parse_args()

    ir = tflite_ir.load_model(a.model)
    sg = ir.subgraph(0)
    arrays = {}
    for i in (2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15):
        arrays[f"t{i}"] = ir.constant(0, i)
        assert arrays[f"t{i}"] is not None, f"tensor {i} not constant"

    raw = open(a.model, "rb").read()
    from tflite.Model import Model  # bundled flatbuffer bindings

    m = Model.GetRootAsModel(raw, 0)
    sg0 = m.Subgraphs(0)
    strbuf = bytes(m.Buffers(sg0.Tensors(1).Buffer()).DataAsNumpy().tolist())
    class_ids = parse_string_tensor(strbuf)

    meta = bytes(m.Buffers(m.Metadata(1).Buffer()).DataAsNumpy().tolist())
    # metadata: flatbuffer TFLite metadata -> last 4 bytes are a float threshold
    threshold = struct.unpack("<f", meta[-4:])[0]

    np.savez(
        a.out,
        **arrays,
        class_ids=np.array(class_ids, dtype=object),
        fc_scales=np.array(
            [
                sg.tensors[13].quantization.scale[0],
                sg.tensors[14].quantization.scale[0],
                sg.tensors[15].quantization.scale[0],
            ],
            dtype=np.float64,
        ),
        threshold=np.float64(threshold),
        # custom-op options decoded from the NGramHash flexbuffer (see report.md)
        ngram_lengths=np.array([1, 2, 3, 4], dtype=np.int64),
        vocab_sizes=np.array([5500, 5500, 13000, 13000], dtype=np.int64),
        seed=np.int64(10911),
        max_splits=np.int64(128),
    )
    print(f"wrote {a.out}: {len(arrays)} tensors, {len(class_ids)} class ids, "
          f"threshold={threshold:g}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
