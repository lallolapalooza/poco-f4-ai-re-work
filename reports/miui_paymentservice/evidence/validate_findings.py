#!/usr/bin/env python3
"""Minimal JSON-Schema validator (the subset used by $WS/SCHEMA.json).

    python validate_findings.py [findings.json] [SCHEMA.json]

Supports: type (object/array/string/integer/number/boolean/null), required,
properties, items, enum.  Exits 0 and prints VALID, or 1 and lists errors.
"""
import json
import sys

TYPES = {
    "object": dict,
    "array": list,
    "string": str,
    "integer": int,
    "number": (int, float),
    "boolean": bool,
    "null": type(None),
}


def check(inst, schema, path, errs):
    t = schema.get("type")
    if t:
        py = TYPES[t]
        if t == "integer" and isinstance(inst, bool):
            errs.append(f"{path}: expected integer, got boolean")
            return
        if not isinstance(inst, py):
            errs.append(f"{path}: expected {t}, got {type(inst).__name__}")
            return
    if "enum" in schema and inst not in schema["enum"]:
        errs.append(f"{path}: {inst!r} not in {schema['enum']}")
    if t == "object":
        for key in schema.get("required", []):
            if key not in inst:
                errs.append(f"{path}: missing required '{key}'")
        for key, sub in schema.get("properties", {}).items():
            if key in inst:
                check(inst[key], sub, f"{path}.{key}", errs)
    if t == "array" and "items" in schema:
        for i, item in enumerate(inst):
            check(item, schema["items"], f"{path}[{i}]", errs)


def main():
    findings = sys.argv[1] if len(sys.argv) > 1 else "findings.json"
    schema = sys.argv[2] if len(sys.argv) > 2 else "SCHEMA.json"
    inst = json.load(open(findings))
    sch = json.load(open(schema))
    errs = []
    check(inst, sch, "$", errs)
    if errs:
        print(f"INVALID ({len(errs)} error(s))")
        for e in errs:
            print(" -", e)
        return 1
    print(f"VALID: {findings} conforms to {schema}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
