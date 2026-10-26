#!/usr/bin/env python3
"""Inventory an APK: manifest facts, zip members, and parent-analysis AI flags.

Usage:
    apk_inventory.py <apk> --out DIR [--extract-models] [--extract-libs]
                     [--max-file-mb 50] [--max-total-mb 90]

Writes DIR/inventory.json and DIR/inventory.md.  Nothing is extracted unless
--extract-models / --extract-libs is given, and extraction is always size
capped.  Manifest parsing is cached in DIR/_cache/apk_meta.json.
"""

from __future__ import annotations

import argparse
import json
import os
import sys
import time
import zipfile

# Silence androguard's loguru firehose BEFORE importing it (10k+ DEBUG lines).
try:
    from loguru import logger

    logger.remove()
except Exception:  # pragma: no cover - loguru is a hard androguard dep
    pass

import apk_common as C


def parse_args(argv: list[str] | None = None) -> argparse.Namespace:
    p = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    p.add_argument("apk", help="APK file")
    p.add_argument("--out", required=True, help="output directory")
    p.add_argument("--extract-models", action="store_true", help="extract AI/model assets")
    p.add_argument("--extract-libs", action="store_true", help="extract AI/flagged native libs")
    p.add_argument("--max-file-mb", type=float, default=50.0, help="per-file extraction cap (MiB)")
    p.add_argument("--max-total-mb", type=float, default=90.0, help="total extraction cap (MiB)")
    p.add_argument("--no-cache", action="store_true", help="ignore cached manifest metadata")
    return p.parse_args(argv)


def load_manifest(apk: str) -> dict:
    """Parse manifest/package facts with androguard (loguru already silenced)."""
    from androguard.core.apk import APK

    a = APK(apk)
    out: dict = {}

    def safe(fn, default=None):
        try:
            v = fn()
            return default if v is None else v
        except Exception:
            return default

    out["package"] = safe(a.get_package)
    out["version_name"] = safe(a.get_androidversion_name)
    out["version_code"] = safe(a.get_androidversion_code)
    out["label"] = safe(a.get_app_name) or out["package"] or os.path.basename(apk)
    out["min_sdk"] = safe(a.get_min_sdk_version)
    out["target_sdk"] = safe(a.get_target_sdk_version)
    out["permissions"] = sorted(set(safe(a.get_permissions, []) or []))
    out["declared_permissions"] = len(safe(a.get_declared_permissions, []) or [])
    out["activities"] = len(safe(a.get_activities, []) or [])
    out["services"] = len(safe(a.get_services, []) or [])
    out["receivers"] = len(safe(a.get_receivers, []) or [])
    out["providers"] = len(safe(a.get_providers, []) or [])
    out["features"] = sorted(set(safe(a.get_features, []) or []))
    out["main_activity"] = safe(a.get_main_activity)
    return out


def manifest_cached(apk: str, out_dir: str, no_cache: bool) -> dict:
    cache_dir = os.path.join(out_dir, "_cache")
    cache_path = os.path.join(cache_dir, "apk_meta.json")
    st = os.stat(apk)
    key = f"{os.path.abspath(apk)}|{st.st_size}|{st.st_mtime_ns}"
    cache: dict = {}
    if not no_cache and os.path.exists(cache_path):
        try:
            with open(cache_path, "r", encoding="utf-8") as f:
                cache = json.load(f)
        except Exception:
            cache = {}
    if key in cache:
        return cache[key]
    meta = load_manifest(apk)
    cache = {key: meta}  # keep the cache small: only the last APK
    os.makedirs(cache_dir, exist_ok=True)
    with open(cache_path, "w", encoding="utf-8") as f:
        json.dump(cache, f)
    return meta


def extract_member(z: zipfile.ZipFile, info: zipfile.ZipInfo, dest_root: str) -> str:
    rel = C.safe_relpath(info.filename)
    if not rel:
        raise ValueError("unsafe path")
    dest = os.path.abspath(os.path.join(dest_root, rel))
    root_abs = os.path.abspath(dest_root)
    if not (dest == root_abs or dest.startswith(root_abs + os.sep)):
        raise ValueError("path escapes output dir")
    os.makedirs(os.path.dirname(dest), exist_ok=True)
    with z.open(info) as src, open(dest, "wb") as dst:
        while True:
            chunk = src.read(1 << 20)
            if not chunk:
                break
            dst.write(chunk)
    return os.path.relpath(dest, os.path.dirname(dest_root))


def main(argv: list[str] | None = None) -> int:
    args = parse_args(argv)
    t0 = time.time()
    apk = os.path.abspath(args.apk)
    out_dir = os.path.abspath(args.out)
    os.makedirs(out_dir, exist_ok=True)

    z = C.open_zip(apk)
    size = os.path.getsize(apk)

    groups: dict[str, list[dict]] = {
        "dex": [],
        "native_libs": [],
        "model_assets": [],
        "configs": [],
        "other": [],
    }
    abis: set[str] = set()
    ai_assets: list[dict] = []

    for info in C.iter_zip_members(z):
        name = info.filename
        rec = {"name": name, "size": info.file_size, "compressed": info.compress_size}
        group = C.classify_member(name)
        groups[group].append(rec)
        if name.startswith("lib/") and "/" in name:
            abi = name.split("/", 2)[1]
            if abi:
                abis.add(abi)
        if group not in ("model_assets", "native_libs") and (
            name.startswith("assets/") or name.startswith("res/raw/") or name.startswith("lib/")
        ):
            if C.ASSET_KEYWORD_RE.search(name):
                ai_assets.append({"name": name, "size": info.file_size})

    # TFLite magic + AI keyword annotations for model candidates.
    ai_models: list[dict] = []
    for rec in groups["model_assets"]:
        name = rec["name"]
        try:
            with z.open(name) as f:
                head = f.read(16)
        except Exception:
            head = b""
        rec["magic"] = C.magic_label(head)
        rec["tflite"] = C.is_tflite_magic(head)
        kws = sorted({m.group(0).lower() for m in C.ASSET_KEYWORD_RE.finditer(name)})
        rec["keywords"] = kws
        ai_models.append(rec)

    ai_libs: list[dict] = []
    for rec in groups["native_libs"]:
        if C.AI_LIB_RE.search(rec["name"]):
            hit = C.AI_LIB_RE.search(rec["name"])
            rec["matched"] = hit.group(1).lower() if hit and hit.lastindex else None
            ai_libs.append(rec)

    # DEX markers: one streaming pass per dex, bounded memory.
    dex_markers: dict[str, int] = {}
    for rec in groups["dex"]:
        try:
            with z.open(rec["name"]) as f:
                counts = C.count_markers_stream(f, C.DEX_MARKERS)
            for k, v in counts.items():
                if v:
                    dex_markers[k] = dex_markers.get(k, 0) + v
        except Exception as exc:
            print(f"warning: marker scan failed for {rec['name']}: {exc}", file=sys.stderr)

    # Optional, capped extraction.
    extracted: dict[str, list[dict]] = {"models": [], "libs": []}
    skipped: list[dict] = []
    file_cap = int(args.max_file_mb * 1024 * 1024)
    total_cap = int(args.max_total_mb * 1024 * 1024)
    total_bytes = 0

    def do_extract(records: list[dict], subdir: str, key: str) -> None:
        nonlocal total_bytes
        root = os.path.join(out_dir, subdir)
        for rec in records:
            info = z.getinfo(rec["name"])
            if info.file_size > file_cap or total_bytes + info.file_size > total_cap:
                skipped.append(
                    {
                        "name": rec["name"],
                        "size": info.file_size,
                        "reason": "file cap" if info.file_size > file_cap else "total cap",
                    }
                )
                continue
            try:
                rel = extract_member(z, info, root)
            except Exception as exc:
                skipped.append({"name": rec["name"], "size": info.file_size, "reason": str(exc)})
                continue
            total_bytes += info.file_size
            extracted[key].append({"name": rec["name"], "size": info.file_size, "file": rel})

    if args.extract_models:
        do_extract(ai_models, "models", "models")
    if args.extract_libs:
        do_extract(ai_libs, "libs", "libs")

    meta = manifest_cached(apk, out_dir, args.no_cache)
    z.close()

    inventory = {
        "apk": apk,
        "size": size,
        "sha256": C.sha256_file(apk),
        "package": meta.get("package"),
        "version_name": meta.get("version_name"),
        "version_code": meta.get("version_code"),
        "label": meta.get("label"),
        "min_sdk": meta.get("min_sdk"),
        "target_sdk": meta.get("target_sdk"),
        "main_activity": meta.get("main_activity"),
        "abis": sorted(abis),
        "permissions": meta.get("permissions", []),
        "declared_permissions": meta.get("declared_permissions", 0),
        "components": {
            "activities": meta.get("activities", 0),
            "services": meta.get("services", 0),
            "receivers": meta.get("receivers", 0),
            "providers": meta.get("providers", 0),
        },
        "features": meta.get("features", []),
        "counts": {
            "entries": sum(len(v) for v in groups.values()),
            **{k: len(v) for k, v in groups.items()},
        },
        "groups": groups,
        "ai": {
            "model_assets": ai_models,
            "libs": ai_libs,
            "asset_hits": ai_assets,
            "dex_markers": dict(sorted(dex_markers.items(), key=lambda kv: -kv[1])),
            "keyword_set": {
                "model_name_re": C.MODEL_NAME_RE.pattern,
                "model_ext_re": C.MODEL_EXT_RE.pattern,
                "lib_re": C.AI_LIB_RE.pattern,
                "asset_re": C.ASSET_KEYWORD_RE.pattern,
                "dex_markers": list(C.DEX_MARKER_NAMES),
            },
        },
        "extraction": {
            "models": extracted["models"],
            "libs": extracted["libs"],
            "skipped": skipped,
            "bytes": total_bytes,
            "limits": {"max_file_mb": args.max_file_mb, "max_total_mb": args.max_total_mb},
        },
        "elapsed_sec": round(time.time() - t0, 3),
    }

    json_path = os.path.join(out_dir, "inventory.json")
    with open(json_path, "w", encoding="utf-8") as f:
        json.dump(inventory, f, indent=2)

    md_path = os.path.join(out_dir, "inventory.md")
    with open(md_path, "w", encoding="utf-8") as f:
        write_markdown(f, inventory)

    print(
        f"inventory: {inventory['package']} {inventory['version_name']} "
        f"entries={inventory['counts']['entries']} ai_models={len(ai_models)} "
        f"ai_libs={len(ai_libs)} markers={len(dex_markers)} -> {out_dir} "
        f"({inventory['elapsed_sec']}s)",
        file=sys.stderr,
    )
    return 0


def _md(s) -> str:
    return str(s).replace("|", "\\|")


def write_markdown(f, inv: dict) -> None:
    ai = inv["ai"]
    f.write(f"# APK inventory: {_md(inv['label'])}\n\n")
    f.write(f"`{_md(inv['apk'])}`\n\n")
    f.write("| Field | Value |\n|---|---|\n")
    for label, key in [
        ("Package", "package"),
        ("Version", "version_name"),
        ("Version code", "version_code"),
        ("Label", "label"),
        ("Min SDK", "min_sdk"),
        ("Target SDK", "target_sdk"),
        ("Main activity", "main_activity"),
        ("Size", "size"),
        ("SHA-256", "sha256"),
        ("ABIs", "abis"),
    ]:
        f.write(f"| {label} | {_md(inv.get(key))} |\n")
    comp = inv["components"]
    f.write(
        f"| Components | activities={comp['activities']}, services={comp['services']}, "
        f"receivers={comp['receivers']}, providers={comp['providers']} |\n"
    )
    f.write(f"| Permissions | {len(inv['permissions'])} |\n")
    f.write(f"| Zip entries | {inv['counts']['entries']} |\n\n")

    f.write("## Groups\n\n| Group | Files | Uncompressed bytes |\n|---|---:|---:|\n")
    for k in ("dex", "native_libs", "model_assets", "configs", "other"):
        recs = inv["groups"][k]
        f.write(f"| {k} | {len(recs)} | {sum(r['size'] for r in recs)} |\n")
    f.write("\n")

    f.write("## AI artifacts\n\n")
    f.write(f"### Model assets ({len(ai['model_assets'])})\n\n")
    if ai["model_assets"]:
        f.write("| Member | Size | Magic | TFL3 | Keywords |\n|---|---:|---|---|---|\n")
        for r in ai["model_assets"]:
            f.write(
                f"| `{_md(r['name'])}` | {r['size']} | {_md(r.get('magic'))} | "
                f"{'yes' if r.get('tflite') else 'no'} | {_md(', '.join(r.get('keywords', [])))} |\n"
            )
    else:
        f.write("_none_\n")

    f.write(f"\n### Native libs matching the parent keyword set ({len(ai['libs'])})\n\n")
    if ai["libs"]:
        f.write("| Library | Size | Match |\n|---|---:|---|\n")
        for r in ai["libs"]:
            f.write(f"| `{_md(r['name'])}` | {r['size']} | {_md(r.get('matched'))} |\n")
    else:
        f.write("_none_\n")

    f.write(f"\n### DEX markers ({len(ai['dex_markers'])})\n\n")
    if ai["dex_markers"]:
        f.write("| Marker | Occurrences |\n|---|---:|\n")
        for k, v in ai["dex_markers"].items():
            f.write(f"| `{_md(k)}` | {v} |\n")
    else:
        f.write("_none_\n")

    hits = ai["asset_hits"]
    f.write(f"\n### Asset keyword hits ({len(hits)})\n\n")
    if hits:
        f.write("| Asset | Size |\n|---|---:|\n")
        for r in hits[:100]:
            f.write(f"| `{_md(r['name'])}` | {r['size']} |\n")
        if len(hits) > 100:
            f.write(f"\n_{len(hits) - 100} more in inventory.json_\n")
    else:
        f.write("_none_\n")

    ex = inv["extraction"]
    f.write("\n## Extraction\n\n")
    f.write(
        f"Extracted {len(ex['models'])} model(s), {len(ex['libs'])} lib(s), "
        f"{ex['bytes']} bytes; limits {ex['limits']}.\n"
    )
    if ex["skipped"]:
        f.write("\n| Skipped | Size | Reason |\n|---|---:|---|\n")
        for r in ex["skipped"]:
            f.write(f"| `{_md(r['name'])}` | {r['size']} | {_md(r['reason'])} |\n")

    f.write("\n## Permissions\n\n")
    for p in inv["permissions"]:
        f.write(f"- `{_md(p)}`\n")


if __name__ == "__main__":
    raise SystemExit(main())
