"""Minimal validator for SCHEMA.json (no jsonschema available in the venv).

Implements exactly the constraints used by ../../SCHEMA.json: required keys,
object/array types, string items and enums.

    python validate_findings.py ../../SCHEMA.json ../findings.json
"""

import json
import sys

ENUMS = {
    "confidence": {"confirmed", "inferred", "unknown"},
    "weights": {"extracted", "partial", "encrypted", "unavailable", "none"},
    "status": {"verified", "runs_unverified", "architecture_only", "failed"},
}


def check(obj, schema, path="$", errors=None):
    errors = errors if errors is not None else []
    t = schema.get("type")
    if t == "object":
        if not isinstance(obj, dict):
            errors.append(f"{path}: expected object, got {type(obj).__name__}")
            return errors
        for req in schema.get("required", []):
            if req not in obj:
                errors.append(f"{path}: missing required key '{req}'")
        for key, sub in schema.get("properties", {}).items():
            if key in obj:
                check(obj[key], sub, f"{path}.{key}", errors)
    elif t == "array":
        if not isinstance(obj, list):
            errors.append(f"{path}: expected array, got {type(obj).__name__}")
            return errors
        for i, item in enumerate(obj):
            check(item, schema.get("items", {}), f"{path}[{i}]", errors)
    elif t == "string":
        if not isinstance(obj, str):
            errors.append(f"{path}: expected string, got {type(obj).__name__}")
    elif t == "integer":
        if not isinstance(obj, int) or isinstance(obj, bool):
            errors.append(f"{path}: expected integer, got {type(obj).__name__}")
    elif t == "object_io":
        if not isinstance(obj, dict):
            errors.append(f"{path}: expected object")
    if "enum" in schema and obj not in schema["enum"]:
        errors.append(f"{path}: '{obj}' not in {schema['enum']}")
    return errors


def main(schema_path, findings_path):
    schema = json.load(open(schema_path))
    data = json.load(open(findings_path))
    errors = check(data, schema)
    # the schema's `io` property has no "type"; only require it to be an object
    for i, m in enumerate(data.get("models", [])):
        if "io" in m and not isinstance(m["io"], dict):
            errors.append(f"$.models[{i}].io: expected object")
    if errors:
        print("INVALID")
        for e in errors:
            print("  -", e)
        return 1
    print("VALID: findings.json conforms to SCHEMA.json")
    print(f"  features={len(data['features'])} models={len(data['models'])} "
          f"unknowns={len(data['unknowns'])} status={data['reimplementation']['status']}")
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1], sys.argv[2]))
