# Command transcripts

Environment: `PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python`,
`APK_ROOT="/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system"`,
`APK="$APK_ROOT/system_ext/priv-app/QualcommVoiceActivation/QualcommVoiceActivation.apk"`.

## 0. Identity

```
sha256sum "$APK"
565f8818b189d4cc0c90ad383f068093292fe51ac89ca8c1cc1d9680a7a32863  QualcommVoiceActivation.apk
```

## 1. Inventory

```
$PY tools/apk_inventory.py "$APK" --out reports/qcom_voiceactivation/inventory \
    --extract-models --extract-libs --max-file-mb 60 --max-total-mb 120
inventory: com.quicinc.voice.activation 6.0.10 entries=37 ai_models=7 ai_libs=7 markers=0
```
Output: `inventory/inventory.md`, `inventory/inventory.json`.

## 2. DEX survey

```
$PY tools/dex_find.py "$APK" --regex '.+' --classes-only \
    --out reports/qcom_voiceactivation/evidence/all_classes.jsonl
dex_find: dex=1 classes_scanned=695 class_hits=695 class_refs=342 method_records=0 emitted=1037
$PY tools/strings_dex.py "$APK" --regex '' --min-len 4 --unique \
    --out reports/qcom_voiceactivation/evidence/dex_strings.txt      # 4921 strings
```

## 3. Java decompilation (jadx single-class, 3 threads)

`work/jadx2.sh` = `exec $TOOLS/jadx/bin/jadx -j 3 "$@"` (absolute path required:
`decompile_classes.py` chdirs into the output directory).

```
$PY tools/decompile_classes.py "$APK" \
  --regex 'com\.quicinc\.voice\.activation\.(algorithm|onevoice\.algorithm|soundmodel|vad)\.' \
  --out reports/qcom_voiceactivation/sources_batch1 --jadx $PWD/work/jadx2.sh --max 12
    12/12 ok
$PY tools/decompile_classes.py "$APK" \
  --regex 'com\.quicinc\.voice\.activation\.(configuration\.|engineservice\.OneVoiceModelFileManager|engineservice\.EnrollmentService|asr\.WhisperEngine|onlineepd|soundmodel\.opaque)' \
  --out reports/qcom_voiceactivation/sources_batch2 --jadx $PWD/work/jadx2.sh --max 12
    12/12 ok
$PY tools/decompile_classes.py "$APK" \
  --regex 'com\.quicinc\.voice\.activation\.(audiorecord|soundtrigger)\.' \
  --out reports/qcom_voiceactivation/sources_batch3 --jadx $PWD/work/jadx2.sh --max 8
    8/8 ok
```
Consolidated into `sources/classes/` (32 files, 412 KB), index in
`sources/index.md`, logs in `sources/logs/`.

## 4. Native library survey

```
for f in libqvajni libqvapdk libqvapdkxs libqvarnn libqvasml libqvauv libqvavad \
         libdnnvad_jni libeai_floating libwhisper_lib libVoiceSdk; do
  $PY tools/elf_info.py reports/qcom_voiceactivation/native/libs/$f.so \
      --json reports/qcom_voiceactivation/native/elfinfo/$f.so.json \
      > reports/qcom_voiceactivation/native/elfinfo/$f.so.txt
done
```
Key facts: `libqvajni.so` 36 JNI exports (91 total), needs only `libqvasml.so`;
`libqvasml.so` 577 exports, needs `libeai_floating`, `libVoiceSdk`,
`libtensorflowlite_c`; `libdnnvad_jni.so` 4 JNI exports and defines `Vad`
itself; `libwhisper_lib.so` 6 JNI exports (whisper.cpp/ggml); `libqvapdk*`,
`libqvarnn`, `libqvauv` export only `capi_v2_get_static_properties`/`capi_v2_init`.

## 5. Disassembly (capstone, addresses are ELF vaddrs)

```
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x901d4 --size 0xf4  --out native/disasm/decodePdkXSHeader.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x63ab0 --size 0x1ac --out native/disasm/ListenBigSoundModel_readHeaderFrom.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x63890 --size 0x220 --out native/disasm/ListenBigSoundModel_readFrom.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x5e32c --size 0x214 --out native/disasm/udk7_feature_process.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x5e540 --size 0x188 --out native/disasm/udk7_feature_spectrum.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x5e6c8 --size 0xc0  --out native/disasm/udk7_feature_mel.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x5e144 --size 0xb8  --out native/disasm/udk7_feature_init.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x5e788 --size 0x1a4 --out native/disasm/realfft.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x5f6c0 --size 0xbc  --out native/disasm/udk7_q_complex_vector_mag_f.s
$PY tools/disasm_elf.py native/libs/libqvasml.so --addr 0x5c044 --size 0x68c --out native/disasm/udk7_network_init.s
$PY tools/disasm_elf.py native/libs/libdnnvad_jni.so --addr 0x2fd0 --size 0x2bc --out native/disasm/Vad_init.s
$PY tools/disasm_elf.py native/libs/libdnnvad_jni.so --addr 0x328c --size 0x238 --out native/disasm/Vad_process.s
```

PLT slot -> symbol resolution (needed to identify the helper called at
`0x111c78` in `udk7_feature_{mel,process}` as
`udk7_q_complex_vector_mag_f`): parse `.rela.plt` (offset 0xd650, 381 entries),
decode each `.plt` stub's `adrp`/`ldr` to get its GOT slot, map slot -> dynsym
name. Transcript:
`0x111ab0 -> calloc`, `0x111c70 -> udk7_q_complex_vector_mag_f`,
`0x111c90 -> udk7_q_vector_dot_f`, `0x111ca0 -> udk7_q_vector_mul_vector_inplace_f`.

Math-call-site scan over `.text` (bl to PLT of logf/log10f/expf/…):
`EndPointDetection::UpdateFrame`, `NoiseMixer::*`, `getSegmentStats`,
`sml::runAGAOnUserRecordings` use `log10`; `GmmAdapt::calcScoreAll`,
`Gmms::computeGaussianPosteriors` use `exp`. **No** log function on the
`udk7_feature_*` path.

## 6. Ghidra (one import at a time, 3 GB heap)

```
flock logs/ghidra.lock -c "tools/ghidra_decompile.sh native/libs/libqvasml.so \
   native/ghidra_qvasml.c 'ListenBigSoundModel::|ListenSoundModel::readFrom|ListenSoundModel::isStripped|getIndividualModelInfo|ModelVersion' \
   --timeout 900 --max-functions 30"
  SUMMARY matched=54 attempted=30 decompiled=30 failed=0   output 51 720 B

flock logs/ghidra.lock -c "tools/ghidra_decompile.sh native/libs/libeai_floating.so \
   native/ghidra_eai.c 'eai_|model|layer|tensor|tensor_info|parse' --timeout 900 --max-functions 30"
  functions in output: 30   output 38 713 B

flock logs/ghidra.lock -c "tools/ghidra_decompile.sh native/libs/libeai_floating.so \
   native/ghidra_eai_net.c 'load_network|op_layer|layer_load|load_layer|parse_' --timeout 900 --max-functions 25"
  functions in output: 13   output 11 885 B
```

## 7. Model carving / container parsing

```
$PY tools/carve_models.py models/uim/1st_stage/63ff0ae5_en-US_001.uim   # no candidates
$PY tools/carve_models.py evidence/speech_float.eai                    # no candidates
/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/uim_container.py models/uim/*/*.uim \
   | tee evidence/uim_parse.txt
```
`carve_models.py` finds nothing (no TFL3/DLC/caffe in these files); the
embedded networks are `eai2` blobs and were carved by their own magic + the
declared size at `+0x10` into `models/eai/` (16 blobs, hashes in
`ir/eai_blobs.json`).

## 8. Reimplementation run

```
$ /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python reimpl/pipeline.py
front-end: (198, 80) mel features, numpy-vs-jax max abs diff 7.629e-06
stages: vad_prob (198,) kw_logits (198,5) mid_logits (198,5)
exit 0
```
Full output: `reimpl/verify.txt`.

## Patch pass (Android entry / data path / consumer)

```bash
PY=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tooling/venv312/bin/python
TOOLS=/run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/tools
APK="$APK_ROOT/system_ext/priv-app/QualcommVoiceActivation/QualcommVoiceActivation.apk"

# binary AXML -> text (androguard)
$PY -c 'from androguard.core.axml import AXMLPrinter; ...'
#   -> /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/qva_manifest.xml ; 16 services / 1 activity / 1 receiver,
#      ForegroundService foregroundServiceType=0x80

# 23 single-class jadx batches, all outputs under /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/qva_b4 .. /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/qva_b28,
# then copied into reports/qcom_voiceactivation/sources/classes/
# full transcript: evidence/patch_batch_decompile.txt

# native JNI table for the Java entry points
$PY $TOOLS/disasm_elf.py native/libs/libqvajni.so --list-jni
#   -> evidence/jni_symbols_libqvajni.txt (36 Java_* entries)

# findings.json extension + schema check
/usr/bin/python3 -c "import json,jsonschema; jsonschema.validate(json.load(open('findings.json')), json.load(open('$WS/SCHEMA.json'))); print('VALID')"
#   VALID ; data_pipelines = [always_on_keyword_detection (11 steps), speaker_enrollment (7 steps)]
```
