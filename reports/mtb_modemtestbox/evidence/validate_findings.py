#!/usr/bin/env python3
"""Minimal structural validator for findings.json against SCHEMA.json.

`jsonschema` is not installed in the workspace venv and there is no network,
so this checks the parts of the schema that matter: required keys, types,
enums and the per-feature/per-model required fields.
"""
import json
import sys

root = sys.argv[1]
findings = json.load(open(f"{root}/findings.json"))
schema = json.load(open(sys.argv[2]))

errors = []
CONF = {"confirmed", "inferred", "unknown"}
WEIGHTS = {"extracted", "partial", "encrypted", "unavailable", "none"}
STATUS = {"verified", "runs_unverified", "architecture_only", "failed"}

for k in schema["required"]:
    if k not in findings:
        errors.append(f"missing required top-level key: {k}")

for key, typ in [("slug", str), ("package", str), ("app_name", str),
                 ("ai_summary", str), ("features", list), ("models", list),
                 ("reimplementation", dict), ("unknowns", list)]:
    if key in findings and not isinstance(findings[key], typ):
        errors.append(f"{key}: expected {typ.__name__}")

for i, f in enumerate(findings.get("features", [])):
    for k in ("name", "entrypoints", "pipeline", "confidence"):
        if k not in f:
            errors.append(f"features[{i}]: missing {k}")
    if f.get("confidence") not in CONF:
        errors.append(f"features[{i}]: bad confidence {f.get('confidence')!r}")
    for k in ("entrypoints", "pipeline", "models"):
        if k in f and not all(isinstance(x, str) for x in f[k]):
            errors.append(f"features[{i}].{k}: non-string item")

for i, m in enumerate(findings.get("models", [])):
    for k in ("path", "format", "confidence"):
        if k not in m:
            errors.append(f"models[{i}]: missing {k}")
    if m.get("confidence") not in CONF:
        errors.append(f"models[{i}]: bad confidence")
    if "weights" in m and m["weights"] not in WEIGHTS:
        errors.append(f"models[{i}]: bad weights enum")
    if "size" in m and not isinstance(m["size"], int):
        errors.append(f"models[{i}]: size not int")
    if "ops" in m and not all(isinstance(x, str) for x in m["ops"]):
        errors.append(f"models[{i}].ops: non-string item")

r = findings.get("reimplementation", {})
for k in ("path", "run_command", "status"):
    if k not in r:
        errors.append(f"reimplementation: missing {k}")
if r.get("status") not in STATUS:
    errors.append(f"reimplementation: bad status {r.get('status')!r}")

if not all(isinstance(x, str) for x in findings.get("unknowns", [])):
    errors.append("unknowns: non-string item")

# data_pipelines: project extension, keep the same discipline
pipes = findings.get("data_pipelines", [])
if not isinstance(pipes, list):
    errors.append("data_pipelines: not a list")
for i, p in enumerate(pipes):
    for k in ("id", "name", "contract_file", "confidence", "triggers",
              "measurement_source", "transport", "preprocessing", "model",
              "postprocessing", "consumers", "lifecycle", "failure_fallback"):
        if k not in p:
            errors.append(f"data_pipelines[{i}]: missing {k}")
    if p.get("confidence") not in CONF:
        errors.append(f"data_pipelines[{i}]: bad confidence")
    for j, t in enumerate(p.get("triggers", [])):
        for k in ("surface", "component", "exported", "permission", "ref", "confidence"):
            if k not in t:
                errors.append(f"data_pipelines[{i}].triggers[{j}]: missing {k}")
        if t.get("confidence") not in CONF:
            errors.append(f"data_pipelines[{i}].triggers[{j}]: bad confidence")

print(f"findings.json: {len(findings.get('features', []))} feature(s), "
      f"{len(findings.get('models', []))} model(s), "
      f"{len(pipes)} data pipeline(s)")
if errors:
    print("INVALID:")
    for e in errors:
        print("  -", e)
    sys.exit(1)
print("VALID (required keys, types, enums, data_pipelines structure)")
