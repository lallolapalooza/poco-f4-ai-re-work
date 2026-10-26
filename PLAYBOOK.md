# Target reverse-engineering playbook

Scope: the AI/ML subsystem of ONE preinstalled Android APK. Goal: another agent with no context can (a) understand the pipeline and (b) run a JAX reimplementation.

## Inputs
- `$WS/reports/<slug>/brief.json` — pre-scanned model/lib/dex markers.
- `scope.json` — APK path, sha256, size.
- APKs are read-only at `$APK_ROOT/...`; never modify them.

## Required output (in `$WS/reports/<slug>/`)
- `report.md` — identity, feature map, evidence links, pipeline diagrams, unknowns.
- `findings.json` — machine-readable, schema in `SCHEMA.json`.
- `sources/` — only targeted decompiled Java/Kotlin (jadx `--single-class`), with `index.md`.
- `native/` — ELF info, disassembly, Ghidra pseudocode for AI-relevant functions.
- `models/` — extracted model files (<= 50 MB total unless justified).
- `ir/` — TFLite IR JSON, op lists, I/O tensors.
- `reimpl/` — runnable JAX code + `README.md` + `verify.txt`.
- `evidence/` — command transcripts (`cmd.txt` + stdout/stderr), hashes.

## Method (do not skip)
1. **Inventory**: `tools/apk_inventory.py "$APK" --out reports/<slug>/inventory --extract-models --extract-libs`.
2. **Feature discovery**: manifest components, dex strings/classes (`tools/dex_find.py`, `tools/strings_dex.py`), resource names, settings/UI strings. Identify every AI feature, not just the first.
3. **Java/Kotlin**: decompile the smallest set of classes that shows entrypoint -> preprocessing -> model invocation -> postprocessing. Record class/method names and line-level behavior in `report.md`.
4. **Native**: for every AI `.so`, run `tools/elf_info.py`; list `Java_*`/JNI exports; disassemble wrappers; use Ghidra for functions that load/decrypt/preprocess or implement the model. Note stripped/indirect limits.
5. **Models**: extract and identify format (`file`, magic). TFLite: `tools/tflite_ir.py`, then `tools/tflite2jax.py`. Non-TFLite (DLC/SNPE, .uim, .bundle, .model, SVM, encrypted): document header/container, attempt carve/decrypt from native code, and infer I/O from call sites. Never pretend an opaque blob was parsed.
6. **JAX reimplementation**: `reimpl/model.py` (or `pipeline.py`) with `init_params()`/`apply(params, x)`. Use real extracted weights where possible. If weights are unavailable/encrypted, implement the documented architecture with deterministic random init and mark it `ARCHITECTURE_ONLY`. Run it on synthetic input; save `verify.txt` with command, shapes, and exit status. If a TFLite reference runs, compare numerically.
7. **Handoff**: state exactly what is known, what is inferred, and what blocks a full reimplementation.

## Rules
- Write only under `$WS/reports/<slug>`; shared tools are read-only.
- Do not emit full-APK decompilation; keep `sources/` < 50 MB.
- Prefer evidence over narrative: exact path, offset, class, method, string.
- Confidence tags: `confirmed` (observed in artifact/code), `inferred`, `unknown`.
- No network fetches of the target's cloud models; static analysis only.
- Resource budget: at most one Ghidra import at a time; JVM heap <= 3 GB; clean temp files.

## Acceptance
- `python reimpl/*.py` exits 0 and prints input/output shapes.
- `findings.json` validates against `SCHEMA.json`.
- Every claim in `report.md` has a file/offset/class/method/command reference.
- At least one end-to-end AI feature pipeline is traced from UI/entrypoint to model output.
