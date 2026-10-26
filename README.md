# Android AI/DNN reverse-engineering workspace for the Xiaomi Poco F4

Single working directory for the APK AI survey and the per-APK reverse-engineering
reports. Generated 2026-10-24; consolidated 2026-10-25. All target APKs were analysed statically; no APK or
mounted image was modified.

## Scope

- Source image: `/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/`
- 515 APKs scanned for on-device model assets, NN runtimes, and AI API markers.
- 53 candidate targets recorded in `targets.json`.
- 15 final targets in `scope.json`: intersection of the Xiaomi/POCO/partner rows
  with "ships an on-device model/runtime".
- Google-made apps were excluded (GmsCore, Velvet, Dialer, Messages, Photos,
  Drive, LatinIME, Turbo, Phonesky, AndroidSystemIntelligence,
  PrivateComputeServices, AdServices, OnDevicePersonalization, FederatedCompute,
  Maps, Chrome, WebView, Gmail, Meet, Assistant, Speech, TalkBack, SwitchAccess).

## Quick start

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
cd "$WS"

# Start here: per-app data-pipeline index and corrections
cat PIPELINES.md

# One report
cd reports/miui_securitycenter
cat report.md
cat PIPELINE.md
$WS/tooling/venv312/bin/python reimpl/model.py models/assets_model
```

## Layout

| Path | Contents |
|---|---|
| `PIPELINES.md` | Index of all 15 apps, data-pipeline counts, reimplementation status, key corrections |
| `MODELS.md` | Model extraction status for all 15 apps, including absent/encrypted/downloaded models |
| `REIMPL_COVERAGE.md` | Which extracted models have runnable code vs only a data-pipeline contract |
| `pipelines_index.json` | Machine-readable form of the same index |
| `PIPELINE_TEMPLATE.md` | Canonical 9-section data-pipeline contract used by every report |
| `PLAYBOOK.md` | Per-target reverse-engineering workflow and output rules |
| `SCHEMA.json` | Findings schema (`features`, `models`, `reimplementation`, `unknowns`) |
| `scope.json` | The 15 selected APKs (path, size, SHA-256) |
| `targets.json` | All 53 scanned candidates |
| `reports/<slug>/` | Per-APK deliverables (see below) |
| `tools/` | Shared analysis toolchain (`jadx`, APK/DEX, ELF, Ghidra, TFLite→JAX) |
| `tools/README.md` | Exact commands and limits for every tool |
| `tooling/` | Self-contained Python 3.12 + venv, JADX, REA source, flatbuffers tools, legacy venv |
| `transcripts/` | Snapshot of this session’s harness transcript + index |
| `misc/` | Ad-hoc disassembly fragments and loose artifacts |
| `samples/` | Small TFLite/ELF/DLC samples used to test the toolchain |
| `session_tmp/` | Session scratch that was moved off `/tmp`; see `MANIFEST.tsv` |
| `logs/` | Ghidra and long-running command logs |
| `shared/` | Shared notes and cross-report material |
| `work/` | Temporary tool test output |

## Per-report contract

Every `reports/<slug>/` contains:

| Item | Description |
|---|---|
| `report.md` | Human analysis: identity, features, evidence, corrections, unknowns |
| `PIPELINE.md` | Data path: source/trigger → transport/format → preprocessing → model invocation → postprocessing → consumer → failure/fallback → confidence → reimplementation mapping |
| `findings.json` | Machine-readable features/models/unknowns plus `data_pipelines` |
| `sources/` | Targeted `jadx --single-class` output only (no full-APK decompilation) |
| `native/` | ELF facts, JNI signatures, disassembly, Ghidra pseudocode |
| `models/` | Extracted models/weights (size-capped) |
| `ir/` | Model IR: tensors, ops, shapes, weights tables |
| `reimpl/` | Runnable JAX reference implementation and `verify.txt` |
| `evidence/` | Command transcripts, hashes, xrefs, validators |

`PIPELINE.md` is the canonical data-flow contract. `findings.json.data_pipelines`
is the machine-readable companion; its key names vary slightly between reports
because each app exposes different data types.

## Targets and status

| slug | app | AI subsystem | reimplementation |
|---|---|---|---|
| `miui_securitycenter` | MIUI Security | SMS spam/phishing cascade; new TFLite engine; delegated AI subtitles/UltraClear | verified |
| `miui_securitycoreadd` | Security Core Add | AI auto-brightness inference + on-device training | verified |
| `miui_mediaeditor` | Mi Media Editor | Face/body beauty, scene recognition, makeup, inpaint | verified |
| `miui_extraphoto` | ExtraPhoto | Document scan, relighting, dual-camera bokeh | runs_unverified |
| `miui_biometric` | Biometric face unlock | Enroll/auth, liveness, embedding, template match | architecture_only |
| `miui_paymentservice` | Payment Service | Card digit OCR (HOG + LIBSVM) | verified |
| `miui_remote` | Mi Remote | Voice capture → VAD → cloud ASR/NLU → IR/TTS | runs_unverified |
| `qcom_voiceactivation` | Qualcomm Voice Activation | Always-on keyword detection and enrollment (OneVoice) | architecture_only |
| `mtb_modemtestbox` | Modem Test Box | Radio-metric weak-signal forecast (TFLite) | verified |
| `snapchat` | Snapchat | Haar/ERT/ORB lens CV; ML Kit; deferred `.dnn.enc` | verified |
| `tiktok_musically` | TikTok (musically) | Face detect/landmark/age → video effects | architecture_only |
| `tiktok_trill` | TikTok (trill) | Face detect/landmark/age → video effects | architecture_only |
| `kwai_video` | Kwai | Camera → YCNN face/beauty → encode | architecture_only |
| `kwai_bulldog` | Kwai Bulldog | Camera → YCNN face/beauty → encode | architecture_only |
| `kwai_live` | Kwai Live | Live camera+audio → effects → push | runs_unverified |

Status meanings:

- `verified` — numerical check against a vendor/TFLite reference or a bit-exact
  stage check.
- `runs_unverified` — runs on synthetic input, but no vendor runtime reference
  was available for numeric comparison.
- `architecture_only` — exact tensor/IO contract and architecture recovered,
  but weights are encrypted, downloaded, or otherwise unavailable, so the JAX
  code uses deterministic random initialisation.

## Running the reimplementations

Python 3.12 with JAX, LiteRT, flatbuffers, capstone, pyelftools, androguard and
LIEF lives in `/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312`.

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
cd reports/<slug>
$PY reimpl/verify.py        # most reports
$PY reimpl/pipeline.py      # some reports
$PY reimpl/model.py --demo  # container/architecture demos
```

Each `reimpl/README.md` and `reimpl/verify.txt` gives the exact command and the
observed result. The TFLite converter is `tools/tflite2jax.py`; it generated
standalone `model.py` + `params.npz` for the models that could be numerically
verified.

## Toolchain

| Tool | Purpose |
|---|---|
| `tools/apk_inventory.py` | Package/manifest and zip inventory; AI asset/lib flags |
| `tools/dex_find.py` | DEX class/method search without decompilation |
| `tools/decompile_classes.py` | Targeted `jadx --single-class` with timeouts |
| `tools/strings_dex.py` | DEX string-table search |
| `tools/elf_info.py` | ELF/JNI/strings facts for aarch64 `.so` |
| `tools/disasm_elf.py` | Capstone disassembly, PLT/GOT annotations |
| `tools/ghidra_decompile.sh` | Headless Ghidra 12.1.2 wrapper with timeout/heap cap |
| `tools/carve_models.py` | Carve TFLite/ZIP-DLC/Caffe/model blobs |
| `tools/tflite_ir.py` | TFLite flatbuffer → JSON IR |
| `tools/tflite2jax.py` | TFLite → standalone JAX model with real weights |
| `tools/verify_tflite_jax.py` | JAX vs LiteRT numerical verification |
| `tools/jadx/bin/jadx` | Local JADX 1.5.6 |
| `/opt/ghidra/support/analyzeHeadless` | System Ghidra |

See `tools/README.md` for commands, supported ops, and limits.

## Session data

This folder is self-contained. Session scratch that used to live in `/tmp` is
under `session_tmp/`; `session_tmp/MANIFEST.tsv` records original path, new
path, type, size and mtime. Absolute `/tmp/...` references in reports and
evidence were rewritten to the new paths.

The main harness transcript is copied to
`transcripts/main_apk_ai_scan.jsonl` (`transcripts/INDEX.md` has the session id,
size and SHA-256). The live copy remains under
`~/.omp/agent/sessions/-Downloads/` because the harness keeps writing to it.

## Session archive

`omp-archive-2026-10-26_apk-ai-scan.zip` (36 MB compressed, 147 MB raw) was
generated with `alosslessdev/omp-archive-main` via its `export_session_archive`
tool. It contains the main transcript (953 entries), 40 subagent sessions, 260
artifact/log files, 147 recovered background jobs, `agents.json`, `jobs.json`,
and a standalone `viewer.html`. The archive is not redacted; review it before
sharing.

Active harness state (`/tmp/omp-shell-snapshots-1000`) and unrelated `/tmp`
content (other projects, browser profiles, emulator logs, the `vmlinux-to-elf`
venv) were left in place.

Tooling is inside this folder: `tooling/python3.12` (bundled interpreter),
`tooling/venv312` (JAX/LiteRT), `tooling/jadx`, `tooling/rea-src`, and
`tooling/flatbuffers-tools`. Older commands that used `~/re_workspace` have
been rewritten to these paths. The venv has no `pip`; use
`tooling/venv312/bin/python -m ...` or the bundled `uv` workflow.

## Known limits

- Static analysis only. No target app was executed on a device; the only
  runtime checks are JAX/LiteRT comparisons on synthetic inputs.
- Encrypted or absent weights remain the main blocker: Biometric STEF/Caffe
  payloads, TikTok smash-face descriptor, Qualcomm `eai2` layer tables, Kwai
  downloaded YCNN models, MediaEditor's missing bundle key.
- Cloud-side models (Xiaomi speech/SMS resource packs, Google Play services,
  Snapchat AI Factory `.dnn.enc`) are documented as boundaries, not reversed.
- Some apps delegate inference outside the APK (MIUI Security AI subtitles,
  UltraClear, beauty/privacy; Snapchat ML Kit); the reports state the boundary
  rather than claiming local weights.
- `findings.json.data_pipelines` is heterogeneous by design; use `PIPELINE.md`
  for a uniform contract.

## Provenance

- Scanned 515 APKs; 214 had AI-ish filenames, 217 had DEX AI markers.
- All reports use the same evidence rules: `confirmed`, `inferred`, `unknown`.
- Target hashes and paths are in `scope.json`; tool and model hashes are in each
  report's `evidence/hashes*`.
