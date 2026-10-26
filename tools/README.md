# APK / DEX analysis tooling

Shared tools for inspecting the APKs in
`/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system`
without extracting or fully decompiling them.

All tools are single files next to this README and use the workspace Python:

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
TOOLS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tools
APK_ROOT="/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system"
```

Requirements: Python 3.12 with `androguard` (inventory only), plus the bundled
jadx at `tools/jadx/bin/jadx`.  `androguard`'s loguru logging is silenced
before import; do the same in any new script that imports it.

## Keyword set

`apk_common.py` contains the exact model/lib/asset/DEX-marker keyword sets from
the parent APK analysis (the scan that produced `reports/*/brief.json`):

- `MODEL_NAME_RE` – model file names
- `AI_LIB_RE` – native libraries
- `ASSET_KEYWORD_RE` – asset/config names
- `DEX_MARKERS` – byte strings counted in dex files
- `STRING_KEYWORD_RE` / `AI_CODE_RE` – dex string heuristics
- `DEFAULT_CLASS_REGEX` – class/method search default

Keep these in sync with the parent report if the analysis definition changes.

## 1. `apk_inventory.py`

```bash
$PY $TOOLS/apk_inventory.py "$APK_ROOT/product/app/SecurityCoreAdd/SecurityCoreAdd.apk" \
    --out /tmp/inv_securitycoreadd
```

Writes `inventory.json` and `inventory.md`:

- package, version name/code, label, min/target SDK, main activity
- permissions, component counts, declared features
- ABIs derived from `lib/<abi>/`
- every zip member grouped as `dex`, `native_libs`, `model_assets`, `configs`,
  `other`
- AI flags: model assets with 8-byte magic and `TFL3` boolean, native libs
  matching `AI_LIB_RE`, asset keyword hits, DEX marker counts

Nothing is extracted by default.  Optional extraction is capped:

```bash
$PY $TOOLS/apk_inventory.py "$APK" --out /tmp/inv --extract-models --extract-libs \
    --max-file-mb 50 --max-total-mb 90
```

Files land in `DIR/models/` and `DIR/libs/`; skipped files are listed in
`inventory.json` (`extraction.skipped`).  Defaults: 50 MiB per file, 90 MiB
total, so a default test output stays far below 100 MB.  Manifest facts are
cached in `DIR/_cache/apk_meta.json` (`--no-cache` to bypass).

Tested: 94 MB MIUISecurityCenterGlobal in ~6 s, 80 MB Kwai in ~2 s,
32 MB SecurityCoreAdd in ~1 s.

## 2. `dex_find.py`

```bash
# default AI keyword regex, classes only
$PY $TOOLS/dex_find.py "$APK_ROOT/product/app/SecurityCoreAdd/SecurityCoreAdd.apk" \
    --classes-only

# exact feature/class patterns
$PY $TOOLS/dex_find.py "$APK_ROOT/product/priv-app/MIUISecurityCenterGlobal/MIUISecurityCenterGlobal.apk" \
    --regex 'com\.miui\.gamebooster\.beauty' --classes-only
$PY $TOOLS/dex_find.py "$APK_ROOT/cust/app/customized/partner-com.kwai.video_33/partner-com.kwai.video_33.apk" \
    --regex 'org\.tensorflow\.lite' --classes-only
```

JSON Lines, one record per line:

```json
{"kind":"class","class":"com.miui.gamebooster.beauty.BeautyService","dex":"classes2.dex","defined":true,"methods":80,"access":1,"matched":["com.miui.gamebooster.beauty"]}
{"kind":"method","class":"...","method":"...","descriptor":"(...)V","access":0,"dex":"classes16.dex","defined":false,"via":"class"}
```

- Parses the DEX header, string/type/method tables and `class_data_item`
  directly.  No decompilation, no androguard.
- One dex member is read at a time; memory is bounded by the largest dex plus
  its string table, so 250 MB multi-dex APKs are fine.
- `defined:true` classes come from `class_defs`.  `defined:false` records are
  types/methods *referenced* by the APK but defined elsewhere (this is how
  `com.xiaomi.aiautobrt.*` appears in SecurityCoreAdd; `--no-refs` disables).
- Flags: `--regex`, `--limit N` (0 = unlimited, default), `--classes-only`,
  `--max-methods-per-class 50`, `--case-sensitive`, `--out FILE`.
- Methods are emitted for a matched class (up to the per-class cap) and for
  individually matching method names.  Classes with zero matching methods are
  still listed.

## 3. `decompile_classes.py`

```bash
$PY $TOOLS/decompile_classes.py \
    "$APK_ROOT/product/app/SecurityCoreAdd/SecurityCoreAdd.apk" \
    --regex 'aiautobrt|org\.tensorflow\.lite' \
    --out /tmp/decompiled_sca --max 5 --timeout 180
```

- Scans defined classes, filters by `--packages com.foo,com.bar`, ranks by
  (distinct keyword hits, method count), then runs jadx **once per class**:

  ```bash
  jadx --single-class <class> --single-class-output <DIR/classes/<class>.java> \
       --show-bad-code --no-res <apk>
  ```

- Never uses full-project mode or `-d`; decompiled output is only written
  under the requested `--out`.
- Writes `DIR/classes/<class>.java`, `DIR/logs/<class>.log`,
  `DIR/index.md` (class -> file links) and `DIR/hits.json` (all candidates,
  ranks, selected/status/file/seconds).
- `--max` default 5, `--timeout` default 180 s/class (jadx is killed on
  timeout), `--scan-limit` default 5000 matching classes.
- Tested: 5 SecurityCoreAdd classes in ~17 s.

## 4. `strings_dex.py`

```bash
$PY $TOOLS/strings_dex.py "$APK" --regex 'aiautobrt|model|tflite'
$PY $TOOLS/strings_dex.py "$APK" --regex '' --format plain --min-len 4 --limit 100
```

- Reads the DEX string table from every dex member (falls back to printable
  runs if the dex cannot be parsed), so embedded asset/feature names are
  included.
- Default filter is the parent `STRING_KEYWORD_RE`; `--regex ''` emits all.
- JSONL by default (`{"dex":"classes.dex","s":"..."}`), `--format plain` for
  one string per line.
- Flags: `--min-len`, `--limit` (0 = unlimited), `--unique`, `--out`.

## Limits and safety

- Inventory: no extraction by default; extraction caps as above; test runs
  produce < 2 MB of output.
- `dex_find` / `strings_dex`: stream one dex at a time; no full-APK model.
- `decompile_classes`: single-class jadx only; per-class timeout; output
  confined to `--out`; default 5 classes.
- jadx receives `TMPDIR=<out>/.tmp`, so its scratch files stay under the
  output directory.
- APKs and the mounted image are never modified.

## Tests

```bash
cd $TOOLS
$PY -m unittest tests.test_smoke -v
```

The smoke test runs inventory, dex search, strings and a 2-class decompile on
SecurityCoreAdd, and optionally checks MIUISecurityCenterGlobal/Kwai when
those APKs exist.  It uses a temporary directory under `work/ApkTooling/tests`
and does not extract by default.

---

# Native ELF tooling (aarch64 Android `.so`)

Static analysis for stripped/unstripped aarch64 shared objects.  These tools
are independent of the APK/DEX tools above (only `elf_info.parse_jni_name` is
imported by `disasm_elf.py`).

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
TOOLS=$WS/tools
APK_ROOT="/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system"

# Test corpus (copies already live in $WS/samples/android; md5 matches the APK)
unzip -p "$APK_ROOT/product/app/Biometric/Biometric.apk" \
    lib/arm64-v8a/libjni_stfaceunlock_api.so > /tmp/libjni_stfaceunlock_api.so
# md5 6f109313601cdab7cf81c8dc7dd87d29
```

Requirements: Python 3.12 with `lief` (1.0.0 dev API) and `capstone` 5;
Ghidra 12.1.2 at `/opt/ghidra` for `ghidra_decompile.sh`.

## 1. `elf_info.py`

```bash
$PY $TOOLS/elf_info.py $WS/samples/android/libaivs_jni.so
$PY $TOOLS/elf_info.py $WS/samples/android/libstfaceunlockocl.so \
    --json /tmp/big.json --strings-limit 100
$PY $TOOLS/elf_info.py libfoo.so --json -      # JSON only, no summary
```

Human summary: ELF header, build-id, Android SDK/NDK note, DT_NEEDED,
SONAME/RPATH, sections with flags, exported/imported symbols, JNI entry
points (decoded Java class/method), and AI-relevant strings grouped as
`tflite`, `model`, `nn_runtime`, `face`, `ocr`, `asr`, `segment`,
`model_name`.

JSON keys: `class`, `type`, `machine`, `entrypoint`, `imagebase`, `build_id`,
`android{sdk,ndk,ndk_build}`, `needed`, `soname`, `sections[]`,
`segments[]`, `symbols{exported,imported,symtab_count,dynsym_count}`,
`jni_entrypoints[]`, `strings{categories,counts,scanned_bytes}`,
`statistics`, `sha256`.

Only `SHF_ALLOC` non-executable sections are scanned for strings (`.rodata`,
`.data*`, `.dynstr`), so DWARF does not flood the report.  Speed: ~0.4 s on
the 727 KB lib, ~8 s on the 106 MB lib.

## 2. `disasm_elf.py`

```bash
$PY $TOOLS/disasm_elf.py libfoo.so --list-jni
$PY $TOOLS/disasm_elf.py $WS/samples/android/libaivs_jni.so \
    --symbol Java_com_xiaomi_ai_android_vad_Vad_native_1vadInit --out vad.s
$PY $TOOLS/disasm_elf.py $WS/samples/android/libstfaceunlockocl.so \
    --symbol st_auth_reset --out st.s
$PY $TOOLS/disasm_elf.py libfoo.so --addr 0x3530 --size 0x19c
$PY $TOOLS/disasm_elf.py libfoo.so --range 0x886c0:0x88700 -o -
```

- Function size comes from the symbol; if it is 0, the range is bounded by the
  next symbol/`ret`/`--max-bytes` (default 4096) and stops at the first
  `ret`/unconditional `b`.
- Direct `bl`/`b` targets are resolved to `name@plt` or an internal symbol
  (`name+0xN` for interior addresses).  `adrp`+`ldr` pairs into the GOT are
  annotated `name@got`, covering imported data as well as functions.
- PLT mapping is derived by decoding each `.plt` stub and matching its GOT
  slot against `.rela.plt` (not by assuming relocation order).
- Output defaults to `<lib>.<symbol|addr>.s` in the current directory.
- `--list-jni` prints address, size and decoded `class.method` for every
  `Java_*` symbol (18 in `libaivs_jni.so`, 25 in the Biometric API lib).

## 3. `ghidra_decompile.sh`

```bash
$TOOLS/ghidra_decompile.sh <so> <out.c> [regex] [options]

$TOOLS/ghidra_decompile.sh $WS/samples/android/libjni_stfaceunlock_api.so \
    /tmp/jni.c 'Java_.*' --timeout 600 --max-functions 64
```

Default regex: `Java_.*|JNI_OnLoad|.*[Mm]odel.*|.*[Ii]nfer.*`.  The wrapper
copies nothing, imports the `.so` into a temporary Ghidra project, runs full
auto-analysis, then `ghidra_scripts/DecompileSelected.java` decompiles
matching functions (ascending address) and appends banners with function
name, address, and size plus the C pseudocode.  A `SUMMARY matched=...
decompiled=... timedout=...` line is printed; the wrapper also reports the
number of functions and elapsed time.

Options: `--timeout SEC` (wall clock, default 600; on expiry the whole
Ghidra process group is terminated with a 10 s SIGKILL grace and the wrapper
exits 124),
`--max-functions N` (default 64; 0 = unlimited), `--func-timeout SEC`
(default 60/function), `--heap SIZE` (default `3G`; passes
`GHIDRA_HEADLESS_MAXMEM`/`GHIDRA_MAXMEM`), `--noanalysis` (only when
decompilation without auto-analysis is genuinely sufficient), and
`--keep-project`.

JDK: Ghidra 12.1.2 requires JDK >= 21.  The installed JDK 27 was verified to
start headless Ghidra and import an ELF (the decompile runs above used
JDK 21).  `support/launch.sh` tries `PATH` before `JAVA_HOME`, so the wrapper
prefers the local JDK 21 at
`/usr/lib/jvm/java-21-openjdk` (then `~/.jdks/jbr-21.0.11`, then `JAVA_HOME`,
then `PATH`) and prepends it to `PATH`.  Override with
`GHIDRA_JAVA_HOME=/path/to/jdk21`.  No system packages were modified.

Measured: Biometric `libjni_stfaceunlock_api.so` (131 KB, DWARF) -> 25/25
`Java_*` functions decompiled in 13 s idle, 60-90 s while another Ghidra
import shared the host, both with the 3 GB cap.

## 4. `carve_models.py`

```bash
$PY $TOOLS/carve_models.py libfoo.so
$PY $TOOLS/carve_models.py $WS/samples/android/libstfaceunlockocl.so \
    --json /tmp/carve.json
$PY $TOOLS/carve_models.py model.tflite --extract /tmp/carved
$PY $TOOLS/carve_models.py blob.bin --types tflite,zip,caffe,strings
$PY $TOOLS/carve_models.py small.bin --types flatbuffer --scan-flatbuffers
```

Detectors and output:

- `tflite`: `TFL3` identifier, validates root uoffset/vtable; reports the
  flatbuffer start (magic-4), `root_offset`/`vtable_offset`, and the whole
  file as size when the model starts at offset 0.
- `zip_dlc`: local ZIP header + matching EOCD (and ZIP64 EOCD/locator), so
  Snapdragon `.dlc` files and embedded archives get exact sizes.
- `caffe`: real NetParameter protobuf heuristic (printable net name, >= 3
  recognised layer messages) plus deduplicated Caffe-related strings
  (`caffe_model`, `.prototxt`, `Caffe`, ...).
- `strings`: model-name strings such as `M_Verify_..._model`, `*.tflite`,
  `*.dlc`, `*.caffemodel`.
- `flatbuffer` (opt-in): identifier-less root/vtable heuristic; skipped for
  files > 32 MiB to stay bounded.

Each finding has `kind`, `offset`, `size` (may be `null`), `confidence`,
`note`, `occurrences` (strings), and a ready-to-run GNU `dd` command.
`--extract DIR` writes only extractable blobs; nothing is written otherwise.
On the 106 MB lib this finds `caffe_model` at `0x75bd50` plus 19 model names
in ~5 s.  A TFLite sample yields one `tflite` finding at offset 0 with the
exact file size.

## Tests

```bash
$PY $TOOLS/tests/test_tools.py            # elf_info, disasm, carve (15-90 s)
$PY $TOOLS/tests/test_tools.py --ghidra   # adds the Ghidra run (15-90 s)
```

The suite uses `$WS/samples/android`, `$WS/samples/tflite` and
`$WS/samples/models/document_model.dlc` (override with `ELF_CORPUS`,
`TFLITE_CORPUS`, `DLC_SAMPLE`, `WORKSPACE`).  It also builds a synthetic blob
with an embedded Caffe NetParameter, TFLite flatbuffer and DLC zip to verify
offsets, ZIP64 handling and `--extract` byte equality.

## Native-analysis limits

- Stripped binaries expose only dynamic symbols.  Local function names are
  lost; Ghidra/`disasm_elf.py` operate on exported entry points and
  addresses.  `--list-jni` finds exported `Java_*` symbols only.
- `JNI_OnLoad` + `RegisterNatives` tables are not walked: native methods
  registered at runtime have no `Java_*` symbol.
- Indirect calls (`blr`, function-pointer/vtable dispatch) are not resolved;
  only direct `bl`/`b` and the PLT/GOT stubs are annotated.  C++ virtual
  dispatch and JNI `env->functions` calls remain unnamed.
- Symbol size 0 (common for assembly/hand-written kernels) makes the extent a
  heuristic; use `--size`/`--range` for exact coverage.  A tail-call `b` can
  end an auto-sized range early; pass `--no-stop-at-ret` or an explicit size.
- Addresses are ELF virtual addresses (shared objects normally load at a
  nonzero Android base; add the runtime base when matching logs).
- `carve_models.py` cannot know a TFLite flatbuffer's length (the format has
  no size prefix), so embedded TFLite findings may have `size: null`.  ZIP
  detection needs an intact EOCD; the Caffe detector is a heuristic, and
  Caffe/model strings are reported as strings, not verified models.
- `elf_info.py` string categories are keyword heuristics and capped per
  category (`--strings-limit`); `.data` may contain weights, so strings there
  can be incidental.
- Ghidra: one import at a time.  The 106 MB DWARF-bearing lib is much heavier
  than the small lib; bound it with a narrower regex, `--max-functions`, a
  longer `--timeout`, and keep the default 3 GB heap.  `--noanalysis` is only
  appropriate when raw decompilation without auto-analysis is enough (fewer
  functions/types are recovered).
- Resource guidance: `elf_info` ~8 s / few hundred MB on 106 MB; `disasm` <2 s
  and bounded by the requested range; `carve_models` ~5 s / mmap (no full
  copy); Ghidra 13 s idle (60-90 s under contention) for the 131 KB lib at
  3 GB max heap, expect minutes and ~2-3 GB for the 106 MB lib.  Avoid running two Ghidra imports concurrently
  on this 22 GB host.

---

# TFLite -> JAX reference execution (no TensorFlow)

Pure-Python tooling that parses a `.tflite` flatbuffer with the flatc bindings
in `tools/tflite/`, converts an inference subgraph into standalone JAX code
with the **real weights from the flatbuffer**, and verifies that code against
`ai_edge_litert` (`ai_edge_litert.interpreter.Interpreter`, reference kernels,
no XNNPACK delegate).

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
TOOLS=$WS/tools
SAMPLES=$WS/samples/tflite
```

Requirements: Python 3.12 with `jax`, `numpy`, `flatbuffers`, `ai_edge_litert`
(all present in the workspace venv).  **No TensorFlow is imported anywhere**
(including the generated model).

Files:

| file | purpose |
|---|---|
| `tflite_ir.py` | flatbuffer -> deterministic JSON IR (`load_model`, `to_json`, `dump_json`) |
| `tflite2jax.py` | subgraph -> `model.py` + `params.npz` + `ir.json` |
| `verify_tflite_jax.py` | reference-vs-JAX execution + tolerance table |
| `tflite_to_jax_example.py` | end-to-end convert + verify + demo CLI |
| `tests/test_tflite_jax.py` | corpus + synthetic-op tests |
| `tests/mini_tflite.py` | tiny flatbuffer builder used by the tests |

## 1. Inspect a model (`tflite_ir.py`)

```bash
$PY $TOOLS/tflite_ir.py $SAMPLES/photos__trigger_model.v0.2.3.tflite --summary
$PY $TOOLS/tflite_ir.py $SAMPLES/adservices__model.tflite --out /tmp/ir.json
$PY $TOOLS/tflite_ir.py model.tflite --no-stats        # skip constant min/max/mean
```

JSON layout (keys sorted, tensor/operator order mirrors the flatbuffer):

```
format: "tflite_ir/1"
model_version, description, file_size, buffer_count
operator_codes[]: {index, builtin_code, builtin_name, custom_code, version}
signatures[]:     {key, subgraph_index, inputs[{name,tensor_index}], outputs[...]}
subgraphs[]: {
  index, name, role, stateful,
  inputs[]/outputs[]: {tensor_index, name, shape, dtype, quantization},
  input_indices[]/output_indices[]: [tensor_index],
  operators[]: {index, opcode_index, op, builtin_code, custom_code,
                opcode_version, inputs[], outputs[], intermediates[],
                options_type, options, custom_options_hex},
  tensors[]:   {index, name, shape, shape_signature, dtype, buffer_index,
                buffer_bytes, is_constant, is_variable, quantization,
                constant_stats{min,max,mean}},
  unsupported_ops[]: {index, op, custom_code, reason}
}
supported_ops[]
```

`role` is `inference` / `train` / `restore` / `save` / `init`, derived from the
subgraph name first and then from the ops (used for the 7-subgraph training
model).  `unsupported_ops` explains every op the JAX converter cannot lower,
with op index and name; nothing is silently dropped.

Python API:

```python
import tflite_ir
ir = tflite_ir.load_model("model.tflite", with_stats=True)
sg = ir.subgraph(0)
sg.tensor(3).shape, sg.tensor(3).dtype, sg.tensor(3).quantization.scheme
ir.constant(0, 42)              # np.ndarray | None
ir.dump_json("ir.json")
```

## 2. Convert (`tflite2jax.py`)

```bash
$PY $TOOLS/tflite2jax.py $SAMPLES/asi__line_model.tflite --out /tmp/asi
$PY $TOOLS/tflite2jax.py model.tflite --out out --subgraph 1
$PY $TOOLS/tflite2jax.py model.tflite --out out --signature serving_default
$PY $TOOLS/tflite2jax.py model.tflite --out out --no-verify   # conversion only
```

Writes into `--out` (default `converted/<model-stem>/`):

- `model.py` — self-contained module, imports only `os`, `numpy`, `jax`,
  `jax.numpy`; no flatbuffer access.  Exposes `init_params()` and
  `apply(params, *inputs)` plus `INPUTS`, `OUTPUTS`, `SUBGRAPH_INDEX`,
  `SIGNATURE_KEY` metadata.
- `params.npz` — every constant tensor actually referenced, in its stored dtype
  (`float32`/`float16`/`int8`/`int32`/...), keyed `c<tensor_index>`.
- `ir.json` — full IR plus a `conversion` section (selection reason, IO specs).
- `verify.txt` / `verify.json` — comparison table (unless `--no-verify` or
  verification failed to run).
- `unsupported.json` (instead of `model.py`) when the subgraph cannot run.

Exit codes: `0` converted/verified, `1` verification mismatch, `2` model is
unsupported (a machine-readable JSON reason is printed and written).

Generated API:

```python
import sys; sys.path.insert(0, "out")
import model

params = model.init_params()          # dict[str, jax.Array]
y = model.apply(params, x)            # single input -> single output array
ys = model.apply(params, x0, x1)      # multiple inputs; multiple outputs -> tuple
# apply also accepts a single list/tuple of inputs: model.apply(params, [x0, x1])
```

Inputs are NHWC for 4-D tensors and are cast to the declared dtype.  The batch
dimension is part of the declared static shape (see limitations).  `apply`
raises `ValueError` on a wrong input count or shape.

**Subgraph selection**: `--subgraph N` wins; otherwise `--signature KEY`;
otherwise the first signature whose subgraph is stateless and free of custom
ops; otherwise subgraph 0.  The choice and reason are recorded in `ir.json`
(`conversion.selection_reason`) and in the `model.py` docstring.  For a
signature, the signature's input/output tensor lists (and order) are used; for
models without signatures, the subgraph's `inputs`/`outputs` are used.

**Quantization**: weights are stored raw; integer quantized tensors are
dequantized with `(q - zero_point) * scale`, including per-channel scales
(`quantized_dimension`).  `DEQUANTIZE`/`QUANTIZE` are explicit ops.  Hybrid
kernels (float activations, int8 weights) reproduce TFLite's input
quantization exactly: symmetric/asymmetric int8 quantization per FULLY_CONNECTED
batch row, per CONV_2D batch (symmetric for per-tensor weights, asymmetric for
per-channel weights), with TFLite's `[-128,127]` asymmetric clamp and
`std::round` (halves away from zero).  Fully-int8 *activation* graphs are
emulated in float32; int8 graph outputs are re-quantized at the boundary.

## 3. Verify (`verify_tflite_jax.py`)

```bash
$PY $TOOLS/verify_tflite_jax.py $SAMPLES/asi__line_model.tflite
$PY $TOOLS/verify_tflite_jax.py $SAMPLES --out /tmp/verified --json /tmp/verified/report.json
$PY $TOOLS/verify_tflite_jax.py model.tflite --inputs inputs.npz --seed 7
```

- Reference: `ai_edge_litert.interpreter.Interpreter` with
  `OpResolverType.BUILTIN_WITHOUT_DEFAULT_DELEGATES` (falls back to
  `BUILTIN_REF`).  XNNPACK is deliberately disabled: its hybrid float/int8
  kernels use different approximate numerics, so the canonical TFLite
  reference kernels are the meaningful target.
- Candidate: the generated `model.py` + `params.npz` imported from `--out`.
- Inputs: random by name/shape/dtype (`--inputs file.npz` overrides; keys may
  be the input name, `x0`, `input_0`, `0`, or the tensor index).
- Tolerances: float32 `1e-4` absolute, float16 `1e-2`, quantized outputs
  compared after dequantization with `0.02`, plain int/bool exact (0).
- Output: compact table (name, shape, dtype, max abs error, tolerance,
  PASS/FAIL), `verify.txt`, `verify.json`.
- Exit codes: `0` all comparable models pass (unsupported models are printed
  as `SKIP`), `1` mismatch, `2` execution error or `--require-all` with skips.

## 4. End-to-end example (`tflite_to_jax_example.py`)

```bash
$PY $TOOLS/tflite_to_jax_example.py $SAMPLES/photos__trigger_model.v0.2.3.tflite --out /tmp/out
$PY $TOOLS/tflite_to_jax_example.py $SAMPLES --out /tmp/out_corpus
```

Converts, verifies, prints the verification table, imports the generated
module, runs `apply()` once on random inputs and prints output shapes/dtypes
plus a one-line API reminder.  Handles directories; skipped/unsupported models
are reported without crashing.  Exit codes mirror `tflite2jax.py`.

## Supported operators

`tflite_ir.SUPPORTED_SET` is the single source of truth; the tests assert it
equals the converter's `OPS` registry and that every entry has an `_emit_*`
method.

```
ABS ADD ARG_MAX ARG_MIN AVERAGE_POOL_2D BATCH_MATMUL CAST CONCATENATION
CONV_2D COS DEPTHWISE_CONV_2D DEPTH_TO_SPACE DEQUANTIZE DIV ELU EQUAL EXP
EXPAND_DIMS FILL FLOOR FLOOR_DIV FULLY_CONNECTED GATHER GATHER_ND GREATER
GREATER_EQUAL HARD_SWISH LEAKY_RELU LESS LESS_EQUAL LOG LOGICAL_AND
LOGICAL_NOT LOGICAL_OR LOGISTIC MAXIMUM MAX_POOL_2D MEAN MINIMUM MUL NEG
NOT_EQUAL ONE_HOT PACK PAD PADV2 POW QUANTIZE REDUCE_MAX REDUCE_MIN
REDUCE_PROD RELU RELU6 RELU_N1_TO_1 RESHAPE RESIZE_BILINEAR
RESIZE_NEAREST_NEIGHBOR RSQRT SELECT SHAPE SIN SLICE SOFTMAX SPACE_TO_DEPTH
SQUARE SQUARED_DIFFERENCE SQRT SQUEEZE STRIDED_SLICE SUB SUM TANH TILE
TOPK_V2 TRANSPOSE UNPACK ZEROS_LIKE
```

Details: SAME/VALID padding (with strides and dilation), depth_multiplier,
fused activations, keep_num_dims for FC, per-tensor/per-channel quantization,
negative axes, static STRIDED_SLICE masks (begin/end/ellipsis/new-axis/shrink),
static RESHAPE/SQUEEZE/EXPAND_DIMS/SLICE/PAD/TILE/RESIZE/ONE_HOT parameters.
Non-constant shape/axis/slice parameters are reported as
`unsupported_params` with the op index, never silently skipped.

## Sample corpus results

Twelve models in `$WS/samples/tflite`; `verify_tflite_jax.py $SAMPLES` gives
9 PASS, 3 SKIP, 0 FAIL (seed 0, reference kernels).  "max_abs" is the largest
absolute output error over all outputs.

| model | result | max_abs | notes |
|---|---|---|---|
| `asi__line_model.tflite` | PASS | 2.3e-13 | 4 ops, float32 |
| `gmscore__ranker.tflite` | PASS | 5.7e-06 | 56 inputs, hybrid int8 FC weights |
| `gmscore__rc_ranker.tflite` | PASS | 0 | 11 inputs, 16 outputs, hybrid int8 FC |
| `gmscore__rc_segmenter.tflite` | PASS | 8.9e-07 | 9 inputs, 25 outputs, per-channel int8 CONV |
| `gmscore__segmenter.tflite` | PASS | 7.2e-07 | per-channel int8 CONV, ELU, LOGISTIC |
| `modemtestbox__0.3_new_0410_less.tflite` | PASS | 0 | fp16 weights + explicit DEQUANTIZE |
| `phonesky__notification_clickability.tflite` | PASS | 6.4e-12 | ZEROS_LIKE + CONCAT |
| `photos__trigger_model.v0.2.3.tflite` | PASS | 2.0e-06 | uint8 input, 6 outputs, MobilenetV1 |
| `photos__trigger_model_me.v2.27.0.tflite` | PASS | 3.6e-07 | uint8 input, 9 outputs |
| `adservices__model.tflite` | SKIP | - | `custom_op_unsupported`: 6 `UnsortedSegmentProd` CUSTOM ops (+6 GELU not implemented) |
| `messages__a47.tflite` | SKIP | - | `custom_op_unsupported`: 7 CUSTOM ops; also STRING tensors and ops touching them |
| `securitycoreadd__aiautobrt.tflite` | SKIP | - | `stateful_unsupported`: subgraph 0 has `CALL_ONCE`, 8x `READ_VARIABLE`, 8x `VAR_HANDLE` |

For `securitycoreadd__aiautobrt.tflite` the seven subgraphs are reported with
roles: 0 `new_model_infer` (inference), 1 `old_model_infer` (inference),
2 `restore`, 3 `save`, 4 `save_for_replace` (save), 5 `train`, 6 `NoOp`
(init).  The default selection is signature `new_model_infer` (subgraph 0);
`--subgraph 1` selects the old model.  Every subgraph is stateful, so all
choices emit `status=stateful_unsupported` with the exact op list; use
`unsupported.json` (`subgraphs[]` has roles/op lists for every subgraph) for
downstream tooling.

## Tests

```bash
cd $TOOLS
$PY -m unittest discover -s tests -v
```

- IR determinism + schema checks on all 12 samples, signature/subgraph
  selection, role classification.
- `STRIDED_SLICE` lowering validated against the declared output shape for
  every slice op in the corpus (>100).
- All 9 convertible samples converted and verified (reference kernels).
- Unsupported samples checked for `status`, reason, op lists, `unsupported.json`
  and exit code 2.
- The generated `model.py` is imported in a fresh subprocess with no
  `PYTHONPATH` and no flatbuffer access.
- Synthetic TFLite models (built with `tests/mini_tflite.py`) cover ops absent
  from the corpus: depthwise conv, dilation, FC keep_num_dims, all activations,
  pooling/resize, reshape/squeeze/expand/transpose/mean/concat/pad/slice,
  strided slice, pack/unpack/tile/reduce, gather/gather_nd/argmax/argmin/shape,
  one_hot/fill/zeros_like, batch_matmul/cast, space_to_depth/depth_to_space,
  topk, floor_div, quantize/dequantize, per-channel dequantization and a
  hybrid int8 depthwise convolution.

## Limitations

- Static shapes only: JAX needs concrete dimensions.  Graph inputs with `-1`
  dimensions are reported as `dynamic_shape_unsupported`; reshape/dilation
  parameters that are computed at runtime are rejected with the op index.
- Batch dimension is whatever the flatbuffer declares (usually 1).  Reshape
  constants embed it, so passing a different batch size is not supported.
- Fully-int8 activation graphs are emulated in float32.  int8 graph inputs are
  consumed as int8 and int8 outputs are re-quantized; intermediate int8
  rounding is not reproduced.  The hybrid (float activation + int8 weights)
  paths do match TFLite's reference kernels.
- Verification requires the selected subgraph to be subgraph 0 (the LiteRT
  `set_tensor`/`get_tensor` API); non-zero subgraphs can still be converted
  but are not run by the verifier.
- `CUSTOM` ops, `STRING`/`RESOURCE`/`VARIANT` tensors and stateful
  variable/checkpoint ops are not supported and are reported explicitly.
- `RESIZE_NEAREST_NEIGHBOR` rounding is `floor((dst+0.5)*scale)` for
  half_pixel_centers, `round(dst*(in-1)/(out-1))` for align_corners, and
  `floor(dst*scale)` otherwise (TFLite reference semantics; exact tie handling
  follows those rules).
- `AVERAGE_POOL_2D` uses TensorFlow semantics (padding excluded from the
  divisor); integer average pooling is computed in float32.

