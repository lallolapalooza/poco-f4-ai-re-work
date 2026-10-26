#!/usr/bin/env python3
"""Minimal JSON-Schema subset validator (required/properties/type/items/enum)."""
import json, sys

TYPES = {"object": dict, "array": list, "string": str, "integer": int, "number": (int, float)}

def check(node, schema, path="$"):
    errs = []
    t = schema.get("type")
    if t and not isinstance(node, TYPES[t]) or (t == "integer" and isinstance(node, bool)):
        return ["%s: expected %s got %s" % (path, t, type(node).__name__)]
    if "enum" in schema and node not in schema["enum"]:
        errs.append("%s: %r not in %r" % (path, node, schema["enum"]))
    if isinstance(node, dict):
        for k in schema.get("required", []):
            if k not in node:
                errs.append("%s: missing required %r" % (path, k))
        for k, sub in schema.get("properties", {}).items():
            if k in node:
                errs += check(node[k], sub, path + "." + k)
    if isinstance(node, list) and "items" in schema:
        for i, v in enumerate(node):
            errs += check(v, schema["items"], "%s[%d]" % (path, i))
    return errs

schema = json.load(open(sys.argv[1]))
doc = json.load(open(sys.argv[2]))
errs = check(doc, schema)
if errs:
    print("INVALID")
    print("\n".join(errs))
    sys.exit(1)
print("VALID: %s conforms to %s" % (sys.argv[2], sys.argv[1]))
