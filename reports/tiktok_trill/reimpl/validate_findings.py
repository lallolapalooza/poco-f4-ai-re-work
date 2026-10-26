"""Minimal validator for findings.json against $WS/SCHEMA.json (jsonschema absent)."""
import json, sys

SCHEMA = "/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/SCHEMA.json"
ERR = []

def chk(cond, msg):
    if not cond:
        ERR.append(msg)

def typ(v, t):
    return {"string": str, "integer": int, "object": dict, "array": list}.get(t) and isinstance(v, {"string": str, "integer": int, "object": dict, "array": list}[t])

def main(path):
    schema = json.load(open(SCHEMA))
    doc = json.load(open(path))
    for k in schema["required"]:
        chk(k in doc, f"missing required top-level key {k!r}")
    for k, t in [("slug", "string"), ("package", "string"), ("app_name", "string"),
                 ("ai_summary", "string"), ("features", "array"), ("models", "array"),
                 ("reimplementation", "object"), ("unknowns", "array")]:
        chk(typ(doc.get(k), t), f"{k} must be {t}")
    chk(all(isinstance(u, str) for u in doc.get("unknowns", [])), "unknowns must be strings")
    for i, f in enumerate(doc.get("features", [])):
        for k in ("name", "entrypoints", "pipeline", "confidence"):
            chk(k in f, f"features[{i}] missing {k}")
        chk(isinstance(f.get("entrypoints"), list), f"features[{i}].entrypoints list")
        chk(isinstance(f.get("pipeline"), list), f"features[{i}].pipeline list")
        chk(f.get("confidence") in ("confirmed", "inferred", "unknown"), f"features[{i}].confidence enum")
        if "models" in f:
            chk(isinstance(f["models"], list), f"features[{i}].models list")
    for i, m in enumerate(doc.get("models", [])):
        for k in ("path", "format", "confidence"):
            chk(k in m, f"models[{i}] missing {k}")
        chk(m.get("confidence") in ("confirmed", "inferred", "unknown"), f"models[{i}].confidence enum")
        if "weights" in m:
            chk(m["weights"] in ("extracted", "partial", "encrypted", "unavailable", "none"),
                f"models[{i}].weights enum")
        if "size" in m:
            chk(isinstance(m["size"], int), f"models[{i}].size int")
        if "ops" in m:
            chk(isinstance(m["ops"], list), f"models[{i}].ops list")
        if "io" in m:
            chk(isinstance(m["io"], dict), f"models[{i}].io object")
    r = doc.get("reimplementation", {})
    for k in ("path", "run_command", "status"):
        chk(k in r, f"reimplementation missing {k}")
    chk(r.get("status") in ("verified", "runs_unverified", "architecture_only", "failed"),
        "reimplementation.status enum")
    if ERR:
        print("INVALID")
        for e in ERR:
            print(" -", e)
        return 1
    print(f"VALID: {path} conforms to {SCHEMA} (manual validation, jsonschema unavailable)")
    return 0

if __name__ == "__main__":
    sys.exit(main(sys.argv[1] if len(sys.argv) > 1 else "findings.json"))
