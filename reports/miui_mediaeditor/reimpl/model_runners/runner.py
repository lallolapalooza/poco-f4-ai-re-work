#!/usr/bin/env python3
"""Execution engine for one miui_mediaeditor TFLite model.

Two execution paths, both on the real extracted weights:

``jax``
    the standalone module produced by ``tools/tflite2jax.py`` (``model.py`` +
    ``params.npz``), also cross-checked against the LiteRT reference kernels
    with the exact same input tensors;
``litert``
    the reference interpreter itself (``ai_edge_litert``), used when the
    converter cannot lower the graph (PRELU/SPLIT) or when the converted
    module fails numeric parity.  ``specs.py`` records the exact reason.

Nothing here reads TensorFlow, and no model bytes are modified.
"""

from __future__ import annotations

import importlib.util
import os
import sys
import time
from typing import Any

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
REPORT = os.path.dirname(os.path.dirname(HERE))
WORKSPACE = os.path.dirname(os.path.dirname(REPORT))
TOOLS = os.path.join(WORKSPACE, "tools")
if TOOLS not in sys.path:
    sys.path.insert(0, TOOLS)
if HERE not in sys.path:
    sys.path.insert(0, HERE)

import tflite_ir  # noqa: E402  (flatbuffer IR, no TF dependency)

import postprocess  # noqa: E402
import preprocess  # noqa: E402
from specs import Spec  # noqa: E402

FLOAT_TOL = 1e-4
FLOAT16_TOL = 1e-2
INT_TOL = 0.02

_MASK_FAMILIES = {"mask_multiclass", "mask_binary"}


class RunError(Exception):
    pass


def _import_module(outdir: str):
    path = os.path.join(outdir, "model.py")
    if not os.path.exists(path):
        raise RunError(f"no generated model.py in {outdir}")
    name = "_mr_" + os.path.basename(os.path.normpath(outdir)).replace(".", "_")
    spec = importlib.util.spec_from_file_location(name, path)
    if spec is None or spec.loader is None:
        raise RunError(f"cannot import {path}")
    module = importlib.util.module_from_spec(spec)
    sys.modules[name] = module
    spec.loader.exec_module(module)
    return module


def _interpreter(model_path: str):
    try:
        from ai_edge_litert.interpreter import Interpreter, OpResolverType
    except Exception as exc:  # pragma: no cover - environment dependent
        raise RunError(f"ai_edge_litert unavailable: {exc}") from exc
    resolver = getattr(OpResolverType, "BUILTIN_WITHOUT_DEFAULT_DELEGATES", None)
    if resolver is None:
        resolver = getattr(OpResolverType, "BUILTIN_REF", None)
    it = Interpreter(model_path=model_path, experimental_op_resolver_type=resolver)
    it.allocate_tensors()
    return it


def _quant(ts) -> tuple[list, list]:
    q = getattr(ts, "quantization", None)
    if q is None:
        return [], []
    return list(getattr(q, "scale", []) or []), list(getattr(q, "zero_point", []) or [])


def _dequantize(arr: np.ndarray, ts) -> np.ndarray:
    scale, zp = _quant(ts)
    a = np.asarray(arr, dtype=np.float64)
    if not scale:
        return a
    s = np.asarray(scale, dtype=np.float64)
    z = np.asarray(zp or [0], dtype=np.float64)
    if s.size > 1:
        axis = int(getattr(ts.quantization, "quantized_dimension", 0)) % a.ndim
        shp = [1] * a.ndim
        shp[axis] = s.size
        s = s.reshape(shp)
        z = z.reshape(shp) if z.size > 1 else z.reshape([1] * a.ndim)
    elif z.size > 1:
        z = z.reshape(-1)[0:1]
    return (a - z) * s


class ModelRunner:
    def __init__(self, spec: Spec):
        self.spec = spec
        self.ir = tflite_ir.load_model(spec.tflite_path, with_stats=False)
        self.sg = self.ir.subgraph(0)
        self.input_specs = [self.sg.tensor(i) for i in self.sg.inputs]
        self.output_specs = [self.sg.tensor(i) for i in self.sg.outputs]
        self.ops = [op.op for op in self.sg.operators]
        self._module = None
        self._params = None
        self._apply = None

    # -- metadata ------------------------------------------------------
    def metadata(self) -> dict:
        unsupported = self.sg.unsupported()
        return {
            "name": self.spec.name,
            "short": self.spec.short,
            "bundle": self.spec.bundle,
            "family": self.spec.family,
            "tflite": os.path.relpath(self.spec.tflite_path, REPORT),
            "subgraphs": len(self.ir.subgraphs),
            "ops": sorted(set(self.ops)),
            "op_count": len(self.ops),
            "unsupported_ops": sorted({u["op"] for u in unsupported}),
            "inputs": [
                {"name": t.name, "shape": list(t.shape), "dtype": t.dtype}
                for t in self.input_specs
            ],
            "outputs": [
                {"name": t.name, "shape": list(t.shape), "dtype": t.dtype,
                 "scale": _quant(t)[0][:1], "zero_point": _quant(t)[1][:1]}
                for t in self.output_specs
            ],
            "note": self.spec.note,
            "conversion": {
                "status": "converted" if self.spec.jax else "skipped",
                "runner": self.spec.jax or "litert",
                "reason": self.spec.no_jax_reason,
            },
        }

    # -- execution -----------------------------------------------------
    def _load_jax(self):
        if self._apply is not None:
            return
        if not self.spec.jax:
            raise RunError("no JAX module for this model")
        module = _import_module(self.spec.jax_dir)
        self._module = module
        self._params = module.init_params()
        try:
            import jax

            self._apply = jax.jit(module.apply)
        except Exception:
            self._apply = module.apply

    def run_jax(self, inputs: list[np.ndarray]) -> list[np.ndarray]:
        self._load_jax()
        out = self._apply(self._params, *inputs)
        outs = list(out) if isinstance(out, (tuple, list)) else [out]
        return [np.asarray(v) for v in outs]

    def run_litert(self, inputs: list[np.ndarray]) -> list[np.ndarray]:
        it = _interpreter(self.spec.tflite_path)
        for ts, arr in zip(self.input_specs, inputs):
            it.set_tensor(ts.index, np.asarray(arr))
        it.invoke()
        return [np.asarray(it.get_tensor(ts.index)) for ts in self.output_specs]

    # -- verification --------------------------------------------------
    def compare(self, jax_outs: list[np.ndarray], ref_outs: list[np.ndarray]) -> dict:
        rows = []
        for ts, got, want in zip(self.output_specs, jax_outs, ref_outs):
            got = np.asarray(got)
            want = np.asarray(want)
            row: dict[str, Any] = {
                "name": ts.name,
                "shape": list(want.shape),
                "dtype": ts.dtype,
                "jax_dtype": str(got.dtype),
                "shape_ok": list(got.shape) == list(want.shape),
            }
            if not row["shape_ok"]:
                row.update({"ok": False, "error": "shape mismatch"})
                rows.append(row)
                continue
            if ts.dtype in ("UINT8", "INT8", "INT16", "INT32", "INT64", "BOOL"):
                scale, _ = _quant(ts)
                row["exact_fraction"] = float((got == want).mean())
                if scale:
                    dq = np.abs(_dequantize(got, ts) - _dequantize(want, ts))
                    row["max_abs"] = float(dq.max())
                    row["tol"] = INT_TOL
                    row["ok"] = bool(row["max_abs"] <= INT_TOL)
                else:
                    row["max_abs"] = float(np.abs(got.astype(np.float64) - want.astype(np.float64)).max())
                    row["tol"] = 0.0
                    row["ok"] = bool(row["exact_fraction"] == 1.0)
            else:
                row["max_abs"] = float(np.abs(got.astype(np.float64) - want.astype(np.float64)).max())
                row["tol"] = FLOAT16_TOL if ts.dtype == "FLOAT16" else FLOAT_TOL
                row["ok"] = bool(row["max_abs"] <= row["tol"])
            rows.append(row)

        report: dict[str, Any] = {
            "outputs": rows,
            "max_abs": max((r.get("max_abs", 0.0) or 0.0) for r in rows) if rows else 0.0,
            "all_within_tolerance": all(r.get("ok") for r in rows),
        }
        semantic = self._semantic(jax_outs, ref_outs)
        if semantic is not None:
            report["semantic"] = semantic
        if report["all_within_tolerance"]:
            report["status"] = "pass"
        elif semantic and semantic.get("agreement", 0.0) >= 0.95:
            report["status"] = "pass_semantic"
        else:
            report["status"] = "fail"
        return report

    def _semantic(self, jax_outs: list[np.ndarray], ref_outs: list[np.ndarray]) -> dict | None:
        """Task-level agreement for mask families (argmax / thresholded IoU)."""
        if self.spec.family == "mask_multiclass":
            a = np.asarray(jax_outs[0])
            b = np.asarray(ref_outs[0])
            if a.ndim == 4:
                a, b = a[0], b[0]
            agree = float((a.argmax(-1) == b.argmax(-1)).mean())
            return {"metric": "argmax_class_agreement", "agreement": agree}
        if self.spec.family == "mask_binary":
            pa = np.asarray(jax_outs[0], dtype=np.float64).reshape(-1)
            pb = np.asarray(ref_outs[0], dtype=np.float64).reshape(-1)
            thr = postprocess.mask_threshold(self.spec)[0]
            ma, mb = pa >= thr, pb >= thr
            iou = float((ma & mb).sum() / max((ma | mb).sum(), 1))
            return {"metric": f"mask_iou_at_{thr}", "agreement": iou,
                    "coverage_jax": float(ma.mean()), "coverage_ref": float(mb.mean())}
        return None

    # -- one full pass -------------------------------------------------
    def execute(self, image: np.ndarray, force_litert: bool = False,
                verify: bool = True) -> dict:
        t0 = time.time()
        meta = self.metadata()
        if self.spec.hard_skip:
            return {
                "meta": meta,
                "status": "skip",
                "path": "none",
                "skip_reason": self.spec.no_jax_reason,
                "seconds": round(time.time() - t0, 2),
            }
        inputs, input_meta = preprocess.build_inputs(self.spec, self.input_specs, image)
        meta["input_provenance"] = input_meta

        entry: dict[str, Any] = {"meta": meta}
        try:
            if self.spec.jax and not force_litert:
                path = "jax"
                outputs = self.run_jax(inputs)
                entry["verification"] = self.compare(outputs, self.run_litert(inputs)) if verify else None
            else:
                path = "litert"
                outputs = self.run_litert(inputs)
                entry["verification"] = None
        except Exception as exc:  # keep demo_all alive; record precisely
            entry.update({
                "status": "error",
                "error": f"{type(exc).__name__}: {exc}",
                "path": "litert" if (force_litert or not self.spec.jax) else "jax",
                "seconds": round(time.time() - t0, 2),
            })
            return entry

        if path == "jax":
            vstatus = (entry["verification"] or {}).get("status", "unverified")
            status = vstatus if vstatus != "unverified" else "pass"
        elif force_litert:
            status = "reference_only"
        else:
            status = "litert_fallback"
            if self.spec.no_jax_reason:
                entry["fallback_reason"] = self.spec.no_jax_reason

        entry.update({
            "status": status,
            "path": path,
            "outputs": [
                {"name": ts.name, "shape": list(np.asarray(a).shape),
                 "dtype": str(np.asarray(a).dtype)}
                for ts, a in zip(self.output_specs, outputs)
            ],
            "postprocess": postprocess.postprocess(
                self.spec, self.input_specs, self.output_specs, outputs
            ),
            "seconds": round(time.time() - t0, 2),
        })
        return entry


def run(name: str, image: np.ndarray | None = None, force_litert: bool = False,
        verify: bool = True) -> dict:
    from specs import resolve
    from preprocess import synthetic_frame

    spec = resolve(name)
    if image is None:
        image = synthetic_frame(256, 256)
    return ModelRunner(spec).execute(image, force_litert=force_litert, verify=verify)
