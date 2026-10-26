#!/usr/bin/env python3
"""Tests for the TFLite -> JAX toolchain.

Run with::

    /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python -m unittest discover -s tools/tests -v

Corpus tests use $WS/samples/tflite.  Synthetic tests build tiny TFLite
flatbuffers with the generated bindings (no TensorFlow) so operators that do
not occur in the sample set are still exercised against ai_edge_litert.
"""

from __future__ import annotations

import glob
import json
import os
import subprocess
import sys
import tempfile
import unittest

import numpy as np

TOOLS = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
if TOOLS not in sys.path:
    sys.path.insert(0, TOOLS)
TESTS = os.path.dirname(os.path.abspath(__file__))
if TESTS not in sys.path:
    sys.path.insert(0, TESTS)

import tflite_ir  # noqa: E402
import tflite2jax  # noqa: E402
import verify_tflite_jax as verify  # noqa: E402
from mini_tflite import MiniModel, write_model  # noqa: E402

WS = os.path.dirname(TOOLS)
SAMPLES = os.path.join(WS, "samples", "tflite")
CONVERTIBLE = [
    "asi__line_model.tflite",
    "gmscore__ranker.tflite",
    "gmscore__rc_ranker.tflite",
    "gmscore__rc_segmenter.tflite",
    "gmscore__segmenter.tflite",
    "modemtestbox__0.3_new_0410_less.tflite",
    "phonesky__notification_clickability.tflite",
    "photos__trigger_model.v0.2.3.tflite",
    "photos__trigger_model_me.v2.27.0.tflite",
]
SKIPPED = {
    "adservices__model.tflite": "custom_op_unsupported",
    "messages__a47.tflite": "custom_op_unsupported",
    "securitycoreadd__aiautobrt.tflite": "stateful_unsupported",
}


def sample(name: str) -> str:
    return os.path.join(SAMPLES, name)


def _convert_and_verify(test: unittest.TestCase, model: MiniModel, name: str,
                        inputs=None, seed: int = 0) -> "tflite2jax.Conversion":
    with tempfile.TemporaryDirectory() as tmp:
        path = write_model(model, os.path.join(tmp, name + ".tflite"))
        ir = tflite_ir.load_model(path, with_stats=False)
        conv = tflite2jax.convert_ir(ir)
        outdir = os.path.join(tmp, "out")
        tflite2jax.write_conversion(conv, outdir)
        report = verify.verify_conversion(conv, outdir, inputs=inputs, seed=seed)
        test.assertEqual(report["status"], "pass", verify.render_text(report))
        return conv


class TestIR(unittest.TestCase):
    def test_all_samples_parse_deterministically(self):
        for path in sorted(glob.glob(os.path.join(SAMPLES, "*.tflite"))):
            with self.subTest(model=os.path.basename(path)):
                ir = tflite_ir.load_model(path)
                payload = ir.to_json()
                first = json.dumps(payload, sort_keys=True)
                second = json.dumps(tflite_ir.load_model(path).to_json(), sort_keys=True)
                self.assertEqual(first, second)
                self.assertEqual(payload["format"], "tflite_ir/1")
                self.assertGreaterEqual(payload["model_version"], 3)
                self.assertTrue(payload["subgraphs"])
                sg = payload["subgraphs"][0]
                self.assertIn("operators", sg)
                self.assertIn("tensors", sg)
                self.assertIn("unsupported_ops", sg)
                if sg["operators"]:
                    op = sg["operators"][0]
                    for key in ("index", "op", "inputs", "outputs", "options"):
                        self.assertIn(key, op)
                for tensor in sg["tensors"][:20]:
                    self.assertIn("quantization", tensor)
                    self.assertIn("buffer_bytes", tensor)

    def test_constant_stats_and_quantization(self):
        ir = tflite_ir.load_model(sample("photos__trigger_model.v0.2.3.tflite"))
        sg = ir.subgraphs[0]
        consts = [t for t in sg.tensors if t.is_constant]
        self.assertTrue(consts)
        self.assertTrue(any(t.stats is not None for t in consts))
        quantized = [t for t in sg.tensors if t.quantization.is_quantized]
        self.assertTrue(quantized)
        self.assertEqual(quantized[0].quantization.scheme, "per_tensor")

    def test_subgraph_roles(self):
        ir = tflite_ir.load_model(sample("securitycoreadd__aiautobrt.tflite"))
        roles = {sg.name: sg.role for sg in ir.subgraphs}
        self.assertEqual(roles["new_model_infer"], "inference")
        self.assertEqual(roles["old_model_infer"], "inference")
        self.assertEqual(roles["restore"], "restore")
        self.assertEqual(roles["save"], "save")
        self.assertEqual(roles["save_for_replace"], "save")
        self.assertEqual(roles["train"], "train")
        self.assertEqual(roles["NoOp"], "init")
        self.assertTrue(all(sg.is_stateful for sg in ir.subgraphs))

    def test_signature_selection(self):
        ir = tflite_ir.load_model(sample("adservices__model.tflite"))
        sg, sig, reason = tflite_ir.choose_subgraph(ir)
        self.assertEqual(sig.key, "serving_default")
        self.assertEqual(sg.index, 0)
        self.assertIn("signature", reason)
        sg1, _, _ = tflite_ir.choose_subgraph(ir, subgraph=0)
        self.assertEqual(sg1.index, 0)


class TestRegistry(unittest.TestCase):
    def test_supported_ops_match_implementations(self):
        self.assertEqual(set(tflite_ir.SUPPORTED_SET), set(tflite2jax.OPS))
        for name in tflite2jax.OPS:
            self.assertTrue(
                hasattr(tflite2jax._CodeGen, f"_emit_{name}"),
                f"missing _CodeGen._emit_{name}",
            )

    def test_unsupported_reasons(self):
        for name, status in SKIPPED.items():
            with self.subTest(model=name):
                ir = tflite_ir.load_model(sample(name), with_stats=False)
                with self.assertRaises(tflite2jax.UnsupportedModel) as ctx:
                    tflite2jax.convert_ir(ir)
                self.assertEqual(ctx.exception.status, status)
                self.assertTrue(ctx.exception.unsupported_ops)
                payload = ctx.exception.to_json()
                self.assertEqual(payload["status"], status)
                self.assertTrue(payload["subgraphs"])


class TestStridedSlice(unittest.TestCase):
    def test_corpus_slice_shapes(self):
        checked = 0
        for path in sorted(glob.glob(os.path.join(SAMPLES, "*.tflite"))):
            ir = tflite_ir.load_model(path, with_stats=False)
            for sg in ir.subgraphs:
                for op in sg.operators:
                    if op.op != "STRIDED_SLICE":
                        continue
                    if any(len(op.inputs) <= k or not sg.tensor(op.inputs[k]).is_constant
                           for k in (1, 2, 3)):
                        continue
                    begin = [int(v) for v in sg.tensor(op.inputs[1]).data.reshape(-1)]
                    end = [int(v) for v in sg.tensor(op.inputs[2]).data.reshape(-1)]
                    strides = [int(v) for v in sg.tensor(op.inputs[3]).data.reshape(-1)]
                    in_shape = sg.tensor(op.inputs[0]).shape
                    if any(d < 0 for d in in_shape):
                        continue
                    spec = tflite2jax.build_static_slice(
                        in_shape,
                        begin,
                        end,
                        strides,
                        int(op.options.get("BeginMask", 0)),
                        int(op.options.get("EndMask", 0)),
                        int(op.options.get("EllipsisMask", 0)),
                        int(op.options.get("NewAxisMask", 0)),
                        int(op.options.get("ShrinkAxisMask", 0)),
                    )
                    got = np.zeros(in_shape, dtype=np.float32)[spec].shape
                    expected = tuple(sg.tensor(op.outputs[0]).shape)
                    self.assertEqual(tuple(got), expected, f"{path} op {op.index}")
                    checked += 1
        self.assertGreater(checked, 100)


class TestCorpusRoundTrip(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.tmp = tempfile.TemporaryDirectory()

    @classmethod
    def tearDownClass(cls):
        cls.tmp.cleanup()

    def test_convertible_models(self):
        for name in CONVERTIBLE:
            with self.subTest(model=name):
                outdir = os.path.join(self.tmp.name, os.path.splitext(name)[0])
                ir = tflite_ir.load_model(sample(name), with_stats=False)
                conv = tflite2jax.convert_ir(ir)
                tflite2jax.write_conversion(conv, outdir)
                report = verify.verify_conversion(conv, outdir, seed=0)
                self.assertEqual(report["status"], "pass", verify.render_text(report))
                self.assertTrue(os.path.exists(os.path.join(outdir, "model.py")))
                self.assertTrue(os.path.exists(os.path.join(outdir, "params.npz")))
                self.assertTrue(os.path.exists(os.path.join(outdir, "ir.json")))
                self.assertTrue(os.path.exists(os.path.join(outdir, "verify.txt")))
                with open(os.path.join(outdir, "verify.txt"), encoding="utf-8") as f:
                    self.assertIn("PASS", f.read())

    def test_unsupported_models_emit_json(self):
        tmp = tempfile.mkdtemp(dir=self.tmp.name)
        for name, status in SKIPPED.items():
            with self.subTest(model=name):
                outdir = os.path.join(tmp, os.path.splitext(name)[0])
                code = tflite2jax.main([sample(name), "--out", outdir, "--no-verify", "--quiet"])
                self.assertEqual(code, 2)
                with open(os.path.join(outdir, "unsupported.json"), encoding="utf-8") as f:
                    payload = json.load(f)
                self.assertEqual(payload["status"], status)
                self.assertTrue(payload["reason"])
                self.assertTrue(payload["unsupported_ops"])
                self.assertTrue(os.path.exists(os.path.join(outdir, "ir.json")))
                self.assertFalse(os.path.exists(os.path.join(outdir, "model.py")))

    def test_explicit_subgraph_selection(self):
        ir = tflite_ir.load_model(sample("securitycoreadd__aiautobrt.tflite"), with_stats=False)
        for index in (0, 1, 2, 5, 6):
            with self.subTest(subgraph=index):
                with self.assertRaises(tflite2jax.UnsupportedModel) as ctx:
                    tflite2jax.convert_ir(ir, subgraph=index)
                self.assertEqual(ctx.exception.status, "stateful_unsupported")
                self.assertEqual(ctx.exception.sg.index, index)

    def test_standalone_generated_module(self):
        name = "asi__line_model.tflite"
        outdir = os.path.join(self.tmp.name, "standalone")
        ir = tflite_ir.load_model(sample(name), with_stats=False)
        conv = tflite2jax.convert_ir(ir)
        tflite2jax.write_conversion(conv, outdir)
        script = r"""
import importlib.util, sys
import numpy as np
path = sys.argv[1]
spec = importlib.util.spec_from_file_location("generated", path)
m = importlib.util.module_from_spec(spec)
spec.loader.exec_module(m)
assert "tflite" not in sys.modules and "tensorflow" not in sys.modules
p = m.init_params()
x = np.linspace(-1, 1, 14, dtype=np.float32).reshape(1, 14)
y = m.apply(p, x)
print(np.asarray(y).shape, float(np.asarray(y).sum()))
"""
        env = dict(os.environ)
        env.pop("PYTHONPATH", None)
        proc = subprocess.run(
            [sys.executable, "-c", script, os.path.join(outdir, "model.py")],
            cwd=tempfile.gettempdir(),
            env=env,
            capture_output=True,
            text=True,
        )
        self.assertEqual(proc.returncode, 0, proc.stderr)
        self.assertIn("(1, 4)", proc.stdout)
        with open(os.path.join(outdir, "model.py"), encoding="utf-8") as f:
            source = f.read()
        self.assertNotIn("import tflite", source)
        self.assertNotIn("import tensorflow", source)
        self.assertNotIn(SAMPLES, source)


class TestSyntheticOps(unittest.TestCase):
    """Operators not (or barely) covered by the sample corpus."""

    # -- convolutions / FC -------------------------------------------------
    def test_conv2d_same_stride_activation(self):
        m = MiniModel("conv_same")
        x = m.tensor([1, 7, 7, 3], "FLOAT32", name="x")
        w = m.tensor([4, 3, 3, 3], "FLOAT32", data=np.arange(108).reshape(4, 3, 3, 3) / 50.0 - 1.0)
        b = m.tensor([4], "FLOAT32", data=[0.1, -0.2, 0.3, 0.0])
        y = m.tensor([1, 4, 4, 4], "FLOAT32")
        m.add("CONV_2D", [x, w, b], [y], {
            "Padding": 0, "StrideH": 2, "StrideW": 2,
            "FusedActivationFunction": 3, "DilationHFactor": 1, "DilationWFactor": 1,
        })
        m.set_io([x], [y])
        _convert_and_verify(self, m, "conv_same")

    def test_conv2d_valid_dilation(self):
        m = MiniModel("conv_dil")
        x = m.tensor([1, 9, 9, 2], "FLOAT32", name="x")
        w = m.tensor([3, 3, 3, 2], "FLOAT32", data=np.linspace(-1, 1, 54))
        b = m.tensor([3], "FLOAT32", data=[0.0, 0.5, -0.5])
        y = m.tensor([1, 5, 5, 3], "FLOAT32")
        m.add("CONV_2D", [x, w, b], [y], {
            "Padding": 1, "StrideH": 1, "StrideW": 1,
            "FusedActivationFunction": 0, "DilationHFactor": 2, "DilationWFactor": 2,
        })
        m.set_io([x], [y])
        _convert_and_verify(self, m, "conv_dil")

    def test_depthwise_conv(self):
        m = MiniModel("dw")
        x = m.tensor([1, 6, 6, 4], "FLOAT32", name="x")
        w = m.tensor([1, 3, 3, 4], "FLOAT32", data=np.linspace(-1, 1, 36))
        b = m.tensor([4], "FLOAT32", data=[0.0, 0.1, -0.1, 0.2])
        y = m.tensor([1, 3, 3, 4], "FLOAT32")
        m.add("DEPTHWISE_CONV_2D", [x, w, b], [y], {
            "Padding": 0, "StrideH": 2, "StrideW": 2, "DepthMultiplier": 1,
            "FusedActivationFunction": 3, "DilationHFactor": 1, "DilationWFactor": 1,
        })
        m.set_io([x], [y])
        _convert_and_verify(self, m, "depthwise")

    def test_depthwise_conv_hybrid_int8(self):
        m = MiniModel("dw_hybrid")
        x = m.tensor([1, 5, 5, 4], "FLOAT32", name="x")
        w = m.tensor([1, 3, 3, 4], "INT8", data=np.linspace(-100, 100, 36).astype(np.int8),
                     scale=[0.01, 0.02, 0.03, 0.04], zero_point=[0, 0, 0, 0],
                     quantized_dimension=3)
        b = m.tensor([4], "FLOAT32", data=[0.0, 0.1, -0.1, 0.2])
        y = m.tensor([1, 5, 5, 4], "FLOAT32")
        m.add("DEPTHWISE_CONV_2D", [x, w, b], [y], {
            "Padding": 0, "StrideH": 1, "StrideW": 1, "DepthMultiplier": 1,
            "FusedActivationFunction": 3, "DilationHFactor": 1, "DilationWFactor": 1,
        })
        m.set_io([x], [y])
        _convert_and_verify(self, m, "depthwise_hybrid")

    def test_fully_connected_keep_num_dims(self):
        m = MiniModel("fc")
        x = m.tensor([1, 3, 5], "FLOAT32", name="x")
        w = m.tensor([4, 5], "FLOAT32", data=np.linspace(-1, 1, 20))
        b = m.tensor([4], "FLOAT32", data=[0.0, 0.1, -0.1, 0.2])
        y = m.tensor([1, 3, 4], "FLOAT32")
        m.add("FULLY_CONNECTED", [x, w, b], [y], {
            "FusedActivationFunction": 0, "KeepNumDims": True,
            "AsymmetricQuantizeInputs": False, "WeightsFormat": 0,
        })
        w2 = m.tensor([4, 5], "FLOAT32", data=np.linspace(1, -1, 20))
        y2 = m.tensor([3, 4], "FLOAT32")
        m.add("FULLY_CONNECTED", [x, w2, b], [y2], {
            "FusedActivationFunction": 1, "KeepNumDims": False,
            "AsymmetricQuantizeInputs": False, "WeightsFormat": 0,
        })
        m.set_io([x], [y, y2])
        _convert_and_verify(self, m, "fc")

    # -- elementwise / activations ----------------------------------------
    def test_activation_chain(self):
        m = MiniModel("acts")
        x = m.tensor([1, 3, 4], "FLOAT32", name="x")
        prev = x
        chain = [
            ("RELU6", None),
            ("LOGISTIC", None),
            ("TANH", None),
            ("HARD_SWISH", None),
            ("RELU", None),
            ("RELU_N1_TO_1", None),
            ("ELU", None),
        ]
        for i, (op, opts) in enumerate(chain):
            y = m.tensor([1, 3, 4], "FLOAT32")
            m.add(op, [prev], [y], opts)
            prev = y
        m.set_io([x], [prev])
        _convert_and_verify(self, m, "acts")

    def test_binary_and_unary_math(self):
        m = MiniModel("math")
        x = m.tensor([2, 4], "FLOAT32", name="x")
        c = m.tensor([2, 4], "FLOAT32", data=np.linspace(0.1, 0.8, 8))
        outputs = []

        def unary(op, src, opts=None):
            y = m.tensor([2, 4], "FLOAT32")
            m.add(op, [src], [y], opts)
            outputs.append(y)
            return y

        def binary(op, a, b, opts=None):
            y = m.tensor([2, 4], "FLOAT32")
            m.add(op, [a, b], [y], opts)
            outputs.append(y)
            return y

        a = binary("ADD", x, c, {"FusedActivationFunction": 0})
        s = binary("SUB", a, c)
        mu = binary("MUL", s, c)
        d = binary("DIV", mu, c)
        mx = binary("MAXIMUM", d, c, {})
        mn = binary("MINIMUM", mx, c, {})
        sq = binary("SQUARED_DIFFERENCE", mn, c, {})
        p = binary("POW", c, c, {})
        absx = unary("ABS", sq, {})
        positive = binary("ADD", absx, c, {"FusedActivationFunction": 0})
        rooted = unary("SQRT", positive)
        unary("RSQRT", positive)
        logged = unary("LOG", rooted)
        unary("SQUARE", logged, {})
        unary("NEG", logged)
        unary("EXP", c)
        unary("SIN", c, {})
        unary("COS", c, {})
        unary("FLOOR", c)
        m.set_io([x], outputs)
        _convert_and_verify(self, m, "math")

    def test_comparisons_logical_select(self):
        m = MiniModel("logic")
        a = m.tensor([2, 3], "FLOAT32", name="a")
        b = m.tensor([2, 3], "FLOAT32", name="b")
        outs = []
        for op, opts in [
            ("LESS", None), ("LESS_EQUAL", None), ("GREATER", None),
            ("GREATER_EQUAL", None), ("EQUAL", {}), ("NOT_EQUAL", {}),
        ]:
            y = m.tensor([2, 3], "BOOL")
            m.add(op, [a, b], [y], opts)
            outs.append(y)
        cond = outs[0]
        y = m.tensor([2, 3], "BOOL")
        m.add("LOGICAL_NOT", [cond], [y], {})
        outs.append(y)
        y = m.tensor([2, 3], "BOOL")
        m.add("LOGICAL_AND", [cond, outs[6]], [y], {})
        outs.append(y)
        y = m.tensor([2, 3], "BOOL")
        m.add("LOGICAL_OR", [cond, outs[6]], [y], {})
        outs.append(y)
        sel = m.tensor([2, 3], "FLOAT32")
        m.add("SELECT", [cond, a, b], [sel], {})
        outs.append(sel)
        m.set_io([a, b], outs)
        _convert_and_verify(self, m, "logic")

    # -- pooling / resize --------------------------------------------------
    def test_pools(self):
        m = MiniModel("pool")
        x = m.tensor([1, 7, 7, 3], "FLOAT32", name="x")
        y1 = m.tensor([1, 4, 4, 3], "FLOAT32")
        m.add("MAX_POOL_2D", [x], [y1], {
            "Padding": 0, "StrideH": 2, "StrideW": 2,
            "FilterHeight": 3, "FilterWidth": 3, "FusedActivationFunction": 3,
        })
        y2 = m.tensor([1, 2, 2, 3], "FLOAT32")
        m.add("AVERAGE_POOL_2D", [y1], [y2], {
            "Padding": 1, "StrideH": 2, "StrideW": 2,
            "FilterHeight": 2, "FilterWidth": 2, "FusedActivationFunction": 0,
        })
        m.set_io([x], [y2])
        _convert_and_verify(self, m, "pools")

    def test_resize(self):
        m = MiniModel("resize")
        x = m.tensor([1, 4, 4, 2], "FLOAT32", name="x")
        size = m.tensor([2], "INT32", data=[8, 8])
        y1 = m.tensor([1, 8, 8, 2], "FLOAT32")
        m.add("RESIZE_BILINEAR", [x, size], [y1], {
            "AlignCorners": False, "HalfPixelCenters": True,
        })
        size2 = m.tensor([2], "INT32", data=[6, 6])
        y2 = m.tensor([1, 6, 6, 2], "FLOAT32")
        m.add("RESIZE_NEAREST_NEIGHBOR", [x, size2], [y2], {
            "AlignCorners": False, "HalfPixelCenters": False,
        })
        m.set_io([x], [y1, y2])
        _convert_and_verify(self, m, "resize")

    # -- shape ops ---------------------------------------------------------
    def test_shape_ops(self):
        m = MiniModel("shape")
        x = m.tensor([1, 2, 3, 4], "FLOAT32", name="x")
        perm = m.tensor([4], "INT32", data=[0, 3, 1, 2])
        t1 = m.tensor([1, 4, 2, 3], "FLOAT32")
        m.add("TRANSPOSE", [x, perm], [t1])
        shp = m.tensor([2], "INT32", data=[1, 24])
        t2 = m.tensor([1, 24], "FLOAT32")
        m.add("RESHAPE", [t1, shp], [t2])
        axis = m.tensor([], "INT32", data=0)
        t3 = m.tensor([1, 1, 24], "FLOAT32")
        m.add("EXPAND_DIMS", [t2, axis], [t3], {})
        t4 = m.tensor([1, 24], "FLOAT32")
        m.add("SQUEEZE", [t3], [t4], {"SqueezeDims": [0]})
        axes = m.tensor([1], "INT32", data=[1])
        t5 = m.tensor([1, 1], "FLOAT32")
        m.add("MEAN", [t4, axes], [t5], {"KeepDims": True})
        m.set_io([x], [t5])
        _convert_and_verify(self, m, "shape")

    def test_concat_pad_slice(self):
        m = MiniModel("pad")
        x = m.tensor([2, 3], "FLOAT32", name="x")
        c = m.tensor([2, 2], "FLOAT32", data=[1.0, 2.0, 3.0, 4.0])
        cat = m.tensor([2, 5], "FLOAT32")
        m.add("CONCATENATION", [x, c], [cat], {"Axis": 1, "FusedActivationFunction": 0})
        pads = m.tensor([2, 2], "INT32", data=[[0, 0], [0, 1]])
        padded = m.tensor([2, 6], "FLOAT32")
        m.add("PAD", [cat, pads], [padded])
        begin = m.tensor([2], "INT32", data=[0, 0])
        size = m.tensor([2], "INT32", data=[1, 4])
        sliced = m.tensor([1, 4], "FLOAT32")
        m.add("SLICE", [padded, begin, size], [sliced])
        pads2 = m.tensor([2, 2], "INT32", data=[[0, 0], [0, 2]])
        val = m.tensor([], "FLOAT32", data=1.5)
        padded2 = m.tensor([1, 6], "FLOAT32")
        m.add("PADV2", [sliced, pads2, val], [padded2])
        m.set_io([x], [padded2])
        _convert_and_verify(self, m, "pad")

    def test_strided_slice(self):
        m = MiniModel("strided")
        x = m.tensor([3, 4, 5], "FLOAT32", name="x")
        begin = m.tensor([3], "INT32", data=[1, 0, 0])
        end = m.tensor([3], "INT32", data=[3, 4, 5])
        strides = m.tensor([3], "INT32", data=[1, 1, 1])
        y = m.tensor([2, 4, 5], "FLOAT32")
        m.add("STRIDED_SLICE", [x, begin, end, strides], [y], {
            "BeginMask": 0, "EndMask": 0, "EllipsisMask": 0,
            "NewAxisMask": 0, "ShrinkAxisMask": 0,
        })
        begin2 = m.tensor([3], "INT32", data=[2, 0, 0])
        end2 = m.tensor([3], "INT32", data=[0, 0, 0])
        strides2 = m.tensor([3], "INT32", data=[1, 1, 1])
        y2 = m.tensor([4, 5], "FLOAT32")
        m.add("STRIDED_SLICE", [x, begin2, end2, strides2], [y2], {
            "BeginMask": 6, "EndMask": 6, "EllipsisMask": 0,
            "NewAxisMask": 0, "ShrinkAxisMask": 1,
        })
        m.set_io([x], [y, y2])
        _convert_and_verify(self, m, "strided")

    def test_pack_tile_reduce(self):
        m = MiniModel("pack")
        a = m.tensor([2, 3], "FLOAT32", name="a")
        b = m.tensor([2, 3], "FLOAT32", name="b")
        packed = m.tensor([2, 2, 3], "FLOAT32")
        m.add("PACK", [a, b], [packed], {"ValuesCount": 2, "Axis": 0})
        mult = m.tensor([3], "INT32", data=[2, 1, 1])
        tiled = m.tensor([4, 2, 3], "FLOAT32")
        m.add("TILE", [packed, mult], [tiled], {})
        axes = m.tensor([1], "INT32", data=[0])
        reduced = m.tensor([2, 3], "FLOAT32")
        m.add("REDUCE_MAX", [tiled, axes], [reduced], {"KeepDims": False})
        summed = m.tensor([2, 3], "FLOAT32")
        m.add("SUM", [tiled, axes], [summed], {"KeepDims": False})
        prod = m.tensor([2, 3], "FLOAT32")
        m.add("REDUCE_PROD", [tiled, axes], [prod], {"KeepDims": False})
        reduced_min = m.tensor([2, 3], "FLOAT32")
        m.add("REDUCE_MIN", [tiled, axes], [reduced_min], {"KeepDims": False})
        m.set_io([a, b], [reduced, summed, prod, reduced_min])
        _convert_and_verify(self, m, "pack_tile")

    def test_unpack(self):
        m = MiniModel("unpack")
        x = m.tensor([3, 2, 4], "FLOAT32", name="x")
        outs = [m.tensor([2, 4], "FLOAT32") for _ in range(3)]
        m.add("UNPACK", [x], outs, {"Num": 3, "Axis": 0})
        m.set_io([x], outs)
        _convert_and_verify(self, m, "unpack")

    # -- indexing ----------------------------------------------------------
    def test_gather_argmax_shape(self):
        m = MiniModel("gather")
        params = m.tensor([5, 3], "FLOAT32", data=np.linspace(-1, 1, 15))
        idx = m.tensor([3], "INT32", data=[4, 0, 2])
        g = m.tensor([3, 3], "FLOAT32")
        m.add("GATHER", [params, idx], [g], {"Axis": 0, "BatchDims": 0})
        params_nd = m.tensor([4, 3], "FLOAT32", data=np.linspace(0, 1, 12))
        idx_nd = m.tensor([2, 2], "INT32", data=[0, 1, 3, 2])
        gnd = m.tensor([2, 3], "FLOAT32")
        m.add("GATHER_ND", [params_nd, idx_nd], [gnd], {})
        x = m.tensor([2, 5], "FLOAT32", name="x")
        axis = m.tensor([], "INT32", data=1)
        am = m.tensor([2], "INT32")
        m.add("ARG_MAX", [x, axis], [am], {"OutputType": 2})
        amin = m.tensor([2], "INT32")
        m.add("ARG_MIN", [x, axis], [amin], {"OutputType": 2})
        shp = m.tensor([2], "INT32")
        m.add("SHAPE", [x], [shp], {"OutType": 2})
        m.set_io([x], [g, gnd, am, amin, shp])
        _convert_and_verify(self, m, "gather")

    def test_one_hot_and_fill(self):
        m = MiniModel("fill")
        idx = m.tensor([3], "INT32", data=[0, 2, 1])
        depth = m.tensor([], "INT32", data=3)
        on = m.tensor([], "FLOAT32", data=1.0)
        off = m.tensor([], "FLOAT32", data=0.0)
        oh = m.tensor([3, 3], "FLOAT32")
        m.add("ONE_HOT", [idx, depth, on, off], [oh], {"Axis": -1})
        x = m.tensor([2, 2], "FLOAT32", name="x")
        zeros = m.tensor([2, 2], "FLOAT32")
        m.add("ZEROS_LIKE", [x], [zeros], {})
        dims = m.tensor([2], "INT32", data=[2, 3])
        value = m.tensor([], "FLOAT32", data=3.5)
        filled = m.tensor([2, 3], "FLOAT32")
        m.add("FILL", [dims, value], [filled], {})
        m.set_io([x], [oh, zeros, filled])
        _convert_and_verify(self, m, "one_hot_fill")

    # -- matmul / layout / topk -------------------------------------------
    def test_batch_matmul_and_cast(self):
        m = MiniModel("matmul")
        a = m.tensor([1, 3], "FLOAT32", name="a")
        b = m.tensor([3, 2], "FLOAT32", data=np.linspace(-1, 1, 6))
        mm = m.tensor([1, 2], "FLOAT32")
        m.add("BATCH_MATMUL", [a, b], [mm], {"AdjX": False, "AdjY": False})
        cast = m.tensor([1, 3], "INT32")
        m.add("CAST", [a], [cast], {"InDataType": 0, "OutDataType": 2})
        cast_back = m.tensor([1, 3], "FLOAT32")
        m.add("CAST", [cast], [cast_back], {"InDataType": 2, "OutDataType": 0})
        m.set_io([a], [mm, cast, cast_back])
        _convert_and_verify(self, m, "matmul")

    def test_space_depth_roundtrip(self):
        m = MiniModel("space")
        x = m.tensor([1, 4, 4, 2], "FLOAT32", name="x")
        s2d = m.tensor([1, 2, 2, 8], "FLOAT32")
        m.add("SPACE_TO_DEPTH", [x], [s2d], {"BlockSize": 2})
        d2s = m.tensor([1, 4, 4, 2], "FLOAT32")
        m.add("DEPTH_TO_SPACE", [s2d], [d2s], {"BlockSize": 2})
        m.set_io([x], [d2s])
        _convert_and_verify(self, m, "space_depth")

    def test_floor_div(self):
        m = MiniModel("floordiv")
        a = m.tensor([2, 3], "INT32", name="a")
        b = m.tensor([2, 3], "INT32", name="b")
        y = m.tensor([2, 3], "INT32")
        m.add("FLOOR_DIV", [a, b], [y], {})
        m.set_io([a, b], [y])
        inputs = [
            np.array([[-7, -6, 5], [9, 8, -4]], dtype=np.int32),
            np.array([[2, 3, -2], [4, 3, 3]], dtype=np.int32),
        ]
        _convert_and_verify(self, m, "floordiv", inputs=inputs)

    def test_topk(self):
        m = MiniModel("topk")
        x = m.tensor([2, 5], "FLOAT32", name="x")
        k = m.tensor([], "INT32", data=3)
        values = m.tensor([2, 3], "FLOAT32")
        indices = m.tensor([2, 3], "INT32")
        m.add("TOPK_V2", [x, k], [values, indices], {})
        m.set_io([x], [values, indices])
        _convert_and_verify(self, m, "topk")

    def test_quantize_dequantize(self):
        m = MiniModel("quant")
        x = m.tensor([1, 4], "FLOAT32", name="x")
        q = m.tensor([1, 4], "INT8", scale=[0.1], zero_point=[0])
        m.add("QUANTIZE", [x], [q], {})
        dq = m.tensor([1, 4], "FLOAT32")
        m.add("DEQUANTIZE", [q], [dq], {})
        # Constant int8 weights with per-channel quantization dequantized.
        c = m.tensor([2, 3], "INT8", data=[[10, -20, 30], [-40, 50, -60]],
                     scale=[0.5, 0.25], zero_point=[0, 0], quantized_dimension=0)
        cdq = m.tensor([2, 3], "FLOAT32")
        m.add("DEQUANTIZE", [c], [cdq], {})
        m.set_io([x], [dq, cdq])
        _convert_and_verify(self, m, "quant", seed=3)


if __name__ == "__main__":
    unittest.main(verbosity=2)
