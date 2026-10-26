# Model extraction status

Answer: **all model files physically present in the 15 APKs are now extracted**
into each app's `reports/<slug>/models/` folder. Models that are embedded,
encrypted, downloaded at runtime, or absent from the APK are listed below with
the exact reason.

Each app has `models/INVENTORY.md` and `models/INVENTORY.json` with its own
file list, sizes, and exceptions.

| App | APK model assets | Extracted | Usable for reimplementation | Missing / not applicable |
|---|---|---:|---|---|
| `miui_securitycenter` | 1 model + dict/pattern | 3 files | custom 680 KB log-linear cascade, dict, pattern | new-engine TFLite (`1.bin`, `2.bin`, `v.txt`) is downloaded, not in APK |
| `miui_securitycoreadd` | 1 TFLite | 1 | `aiautobrt.tflite` (7 subgraphs) | runtime checkpoint is device-generated |
| `miui_mediaeditor` | 22 bundles + 1 TFLite | 22 bundles, 678/726 entries, 33/38 TFLite | 33 TFLite (32 runnable; 1 stock-runtime skip) + configs/shaders | 5 TFLite + 3 configs are in encrypted bundle prefixes; 1024-byte key absent |
| `miui_extraphoto` | DLC + 4 MACE caches | DLC graph/weights, 4 caches, 5 embedded MACE blocks | SNPE DLC (99 layers), 4 MACE CL caches, 466 MACE tensors/18.65 MB weights | MACE operator graph absent; no inference from embedded weights |
| `miui_biometric` | 8 embedded blobs | 8 `.bin` blobs | model bytes extracted | payloads encrypted (STEF v3); JAX architecture-only |
| `miui_paymentservice` | 2 LIBSVM models | 2 | both models parsed; HOG+SVM JAX verified | none |
| `miui_remote` | 1 embedded VAD model | 1 | VAD weights extracted; JAX runs | cloud ASR/NLU/TTS models are remote |
| `qcom_voiceactivation` | 7 `.uim` | 7 `.uim` + 16 EAI2 blobs + front-end tables | containers/blobs/features extracted; VAD runs exactly | keyword GRU/framer partial; 4 blobs refused by runtime; ASR DLC downloaded |
| `mtb_modemtestbox` | 1 TFLite | 1 | TFLite parsed; JAX verified | none |
| `snapchat` | 13 CV/lens assets | 15 files (incl. existing trees) | Haar, ERT, ORB trees, lenses, triangulation | no DNN; AI Factory `.dnn.enc` downloaded; ML Kit via GmsCore |
| `tiktok_musically` | 3 `.model` bundles | 3 bundles + all sections | AES-256-ECB descriptor decoded; 6/12 records execute with real weights | agenet auth code, dequant scale, Slice/Split remain |
| `tiktok_trill` | 3 `.model` bundles | 3 bundles + all sections | byte-identical to musically; 6 records execute | same as musically |
| `kwai_video` | none | support assets | LUT/face mask/beauty config | YCNN/effect models downloaded at runtime |
| `kwai_bulldog` | none | support assets | LUT/face mask/beauty config | downloaded YCNN models |
| `kwai_live` | none | support assets | none shipped | downloaded YCNN/effect models |

## Evidence

- `reports/<slug>/models/INVENTORY.md` — per-app list and exceptions
- `reports/<slug>/models/INVENTORY.json` — machine-readable counts
- `reports/miui_mediaeditor/models/bundle_extraction.json` — 22 bundles / entries / TFLite counts
- `reports/miui_mediaeditor/models/tflite/` — all 33 decrypted TFLite models
- `reports/miui_mediaeditor/models/bundle_entries/` — decrypted bundle contents
- `reports/miui_extraphoto/models/apk_assets/` — DLC + 4 MACE caches
- `reports/miui_extraphoto/models/embedded/` — embedded MACE `.rodata`
- `reports/miui_biometric/models/manifest.json` — 8 encrypted blobs
- `reports/qcom_voiceactivation/models/{uim,eai,frontend}/`
- `reports/tiktok_{musically,trill}/models/sections/`
- `reports/snapchat/models/apk_assets/`

## Verification

Run:

```bash
WS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work
find "$WS/reports" -path '*/models/*' -type f | wc -l
cat "$WS"/reports/*/models/INVENTORY.json
```

The extraction was checked against every model-like member of each APK by
SHA-256: **0 missing APK model assets**. The only non-extracted model bytes are
the 5 encrypted MediaEditor TFLite payloads, the TikTok `agenet` key/Slice
records, and the ExtraPhoto MACE operator graph. ExtraPhoto's MACE weights are
now separated (5 blocks, 466 tensors, 18.65 MB) but cannot be executed without
the graph.
