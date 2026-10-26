# Qualcomm Voice Assist — `qcom_voiceactivation`

Always-on voice activation ("OneVoice", Snapdragon Voice Activation) plus user
enrollment, DNN voice-activity detection and on-device Whisper ASR.

| field | value |
|---|---|
| APK | `system_ext/priv-app/QualcommVoiceActivation/QualcommVoiceActivation.apk` |
| package | `com.quicinc.voice.activation` |
| label / version | Qualcomm Voice Assist / 6.0.10 (code 600010) |
| size / sha256 | 47 391 029 B / `565f8818b189d4cc0c90ad383f068093292fe51ac89ca8c1cc1d9680a7a32863` |
| sdk / ABI | minSdk 29, targetSdk 34 / `arm64-v8a` only |
| zip entries | 37 (1 dex 615 428 B, 15 `.so`, 7 `.uim`, 1 `.eai`, 1 `qvaconfig_v4.xml`) |
| declared features | `com.quicinc.voice.assist`, `.asr`, `.uvr` |
| key permissions | `CAPTURE_AUDIO_HOTWORD`, `RECORD_AUDIO`, `FOREGROUND_SERVICE_MICROPHONE`, `RECEIVE_BOOT_COMPLETED` |
| components | 1 activity (`PermissionActivity`), 16 services, 1 receiver |

Evidence: `inventory/inventory.md`, `inventory/inventory.json`,
`evidence/AndroidManifest.xml` (binary, strings extracted).

There is **no app UI** for detection: the app is a system service provider. The
single activity is a microphone-permission prompt; everything else is
sound-trigger plumbing driven by the framework's
`SoundTrigger`/`VoiceInteraction` path.

> **Pipeline contract:** [`PIPELINE.md`](PIPELINE.md) holds the per-stage
> data-pipeline tables (buffers, threads, thresholds, event structs, failure
> paths, reimplementation mapping) for the two pipelines in this app —
> always-on keyword detection and speaker enrollment. The Android
> entry/service/consumer wiring is documented in §10.

---

## 1. Feature map

| # | feature | entrypoint | pipeline | models |
|---|---|---|---|---|
| F1 | Always-on keyword ("OneVoice") detection | `OneVoiceAlgorithmTask.compute()` (`sources/classes/com.quicinc.voice.activation.onevoice.algorithm.OneVoiceAlgorithmTask.java`) ← `OneVoiceHotwordCapturer` ← `SoundTriggerSession` | AudioRecord/STHAL 16 kHz → DNN VAD gate → **1st stage** keyword net (per 10 ms frame) → **1.5 stage** keyword net → **2nd stage** user-verification (UV) → threshold + tolerance → `RecognitionEvent` | `1st_stage/*.uim`, `1.5_stage/*.uim`, `ubm/general_UV_0{15,24}.uim`, `speech_float.eai` |
| F2 | Speaker enrollment | `EnrollmentService` (`engineservice.EnrollmentService`), `AbstractEnrollmentSession`, `RegenerateSoundModelService` | record 5 utterances (+3 noisy, ≤11) → `SoundModelCodec.getSizeWhenCreateUserModel` → `createUserModel(...)` → `verifyUserRecording` → `BigSoundModel` persisted per user | UBMs above + user model written by SML |
| F3 | Speech recognition (ASR) | `asr.WhisperEngine`, `WhisperProcessAction`, `llm.LLMRecognitionService`, `InputProviderService`, `tts.TTSService` | 16 kHz PCM → `libwhisper_lib.so` (ggml/whisper.cpp) with **downloaded** `ASR_binary/Whisper/encoder_model_quantized_cached.dlc` (+`vocab.bin`) → transcript → TTS | `libSNPE.so` for the DLC encoder; whisper ggml weights are *not* in the APK |
| F4 | On-device end-point detection (EPD) | `onlineepd.EPD` (JNI) / `EndPointDetection` in `libqvasml.so` | energy/SNR state machine on 10 ms frames, used to bound utterances and to pad training data | none (algorithmic) |
| F5 | Fluence-classic VAD | `com.qualcomm.qti.vad.DNNVAD` → `libdnnvad_jni.so` → `libqvavad.so` (`capi_v2`) | FFT/dsplib primitives + `nnvad_wrapper_*` around the eAI model | `speech_float.eai` |
| F6 | DSP-side wake-word (CAPI_v2 / audio HAL) | `libqvapdk.so`, `libqvapdkxs.so`, `libqvarnn.so`, `libqvauv.so` (each exports only `capi_v2_get_static_properties`/`capi_v2_init`); their names appear in `libqvajni.so` `.rodata`, so the JNI wrapper can `dlopen` them | ADSP CAPI_v2 modules; `libqvapdkxs.so` exposes tunables `block_size`, `num_channels`, `eer_mode`, `n_min_req_frame_model_1..3`, `ignore_detect_model_1..3` (3 detect models) | same PDK/RNN/UV model families, also loaded by audioserver |

`libVoiceSdk.so` is ID R&D **VoiceSDK** (`net.idrnd.voicesdk.*` JNI, 93 entries:
`VoiceTemplate.saveToFile`, `VoiceTemplate.serialize`, …) and is a
`DT_NEEDED` of `libqvasml.so`; it backs the "IDUV" / anti-spoof style UV
algorithms (`AlgorithmType.IDUV`, `UV.antiSpoofEnable` in qvaconfig) rather than
the keyword path.

Confidence: F1/F2/F3 **confirmed** (code + assets), F4 **confirmed**,
F5 **confirmed** (loader chain), F6 **inferred** (module identity from exports,
tunables and the dlopen name table; the DSP configuration flow is not visible
statically from the APK).

---

## 2. End-to-end detection pipeline

```
SoundTrigger / VoiceInteraction framework
   │  RecognitionEvent (pre-roll + history buffer partitions)
   ▼
OneVoiceHotwordCapturer (audiorecord.b)  ── AudioRecord(16000, CHANNEL_IN_MONO, PCM16)
   │                                          (NormalAudioRecordAction.java:37)
   ▼
OneVoiceAlgorithmTask.compute()  ── ForkJoin over one Algorithm per stage
   │   algorithm.initAlgorithm()  → JNI Algorithm.init(soundModel, size, typeOrdinal)
   │   algorithm.startAlgorithm() → JNI Algorithm.start(Bundle thresholds)
   │   loop: read frame → algorithm.processAlgorithm(short[] frame, …)
   ▼
libqvajni.so
   ├── Algorithm::keyword_detection_process(short*, nsamples, result*, flag)   @0x1293c
   └── Algorithm::user_verification_process(short*, …, params, result, flag)   @0x12c60
        │  dlsym() on libqvasml.so: smlInit / smlSet / smlGet / smlProcess,
        │  getSoundModelHeader, querySoundModel, createUserKeywordModel*
        ▼
libqvasml.so  (Qualcomm "Listen"/SML sound-model library, 577 exports)
   ├── PdkXSCore          – 1st stage + 1.5 stage (PDK5_XS / PDK6_XS.MFCN)
   ├── Stage2UVCore       – 2nd stage user verification (VOP/DNN_UV/TIVOP/IDUV)
   ├── Gmms/GmmAdapt/GaussAccu – GMM-UBM adaptation for classic UV
   ├── EndPointDetection  – online EPD (log10 energy/SNR)
   ├── VoiceWakeupFeatureExtraction / udk7_feature_* – 80-bin mel front-end
   └── eAI wrapper        – udk7_network_init/apply → libeai_floating.so
   ▼
libeai_floating.so (EAI2 interpreter)  ← eai2 blobs inside the .uim / .eai files
```

`Algorithm::init` (@`0x1302c`) performs a `dlopen` and resolves the SML entry
points through `dlsym`; that is why the JNI shim has no `sml*` imports while
`strings native/libs/libqvajni.so` lists `smlInit`, `smlSet`, `smlGet`,
`smlProcess`, `getSoundModelHeader`, `querySoundModel`,
`createUserKeywordModel*`, `releaseSoundModelHeader`. The same `.rodata` also
holds the names `libqvapdk.so`, `libqvapdkxs.so`, `libqvauv.so`,
`libqvarnn.so`, `libqvavad.so` next to `libqvasml.so` (which is also a
`DT_NEEDED`), i.e. the wrapper can bind either the CPU SML or the DSP CAPI_v2
back end at runtime (INFERRED: the selection logic is inside the obfuscated
helper that builds the `algorithm_parameters` bundle).

Result contract returned to Java (`OneVoiceAlgorithmTask.a()`): `success`,
`startIndex`/`endIndex`, `preTolerance`/`postTolerance`, `duration`,
`realLength`, plus per-stage extras `1.5Stage.detectedIndex`,
`1.5Stage.keywordStartIndex`, `1.5Stage.keywordEndIndex`,
`1.5Stage.startTimestamp`, `1.5Stage.finishTimestamp`, `firstStageUVScore`.

---

## 3. `.uim` container decoded

All 7 shipped `.uim` files parse with one layout (`reimpl/uim_container.py`,
transcript `evidence/uim_parse.txt`), and the layout is confirmed by the
decompiled loader (`native/ghidra_qvasml.c`,
`ListenBigSoundModel::readFrom`, and the body parser reached from
`sml_model_decoder` @`0xf7450` → `0x102fd0`):

| offset | size | field | verified by |
|---|---|---|---|
| 0x000 | 4 | magic `0x00180CC8` (bytes `c8 0c 18 00`) | `0xf7514`: `sub w8,w8,#0x180,lsl#12; cmp w8,#0xcc8` |
| 0x004 | 4 | payload size, must be ≤ `0x05F5E100` (100 MB) | same fn: `cmp w8,#0x5f5e100` |
| 0x008 | 4 | model version word: `0x300`, `0x4xx…0x7xx`, `0x800` | `ListenBigSoundModel::isInvalidBigSoundModelVersion` |
| 0x00C | 4 | number of individual models (2 for 1st-stage files, else 1) | `0x102fd0` loop count |
| 0x010 | 4 | name length / flag (nonzero ⇒ copy name1) | `0x102fd0` `cbz w9` gate |
| 0x014 | 4 | second-name flag (0 in every shipped file) | `0x102fd0` `cbz w8` gate |
| 0x018 | 200 | model name, NUL padded | `memcpy(out+0xc, buf+0xc, 0xc8)`, NUL at `out+0xd3` |
| 0x0E0 | 200 | optional second name (unused) | `memcpy(out+0xd4, buf+0xd4, 0xc8)` |
| 0x1A8 | count×16 | individual-model descriptors | `0x102fd0`: `x8 = buf+0x19c`, stride `0x10`, `+0x0c` word must be nonzero |
| 0x1A8+16·count | … | version-specific sub-model blobs (`PDK5`/`PDK6` header, `eai2` blobs) | `decodePdkXSHeader` @`0x901d4` |

`sml_model_decoder` @`0xf7450` validates the header with `0xf7514`, computes the
version family as `index = (version-0x300)>>8` (so 0x300→0 … 0x800→5, `cmp #5`
else error 1) and tail-calls `0x102fd0` with `(buf+0xc, model+0x10)`. The
sub-model content is then re-dispatched from `ListenBigSoundModel::readFrom`'s
20-entry switch on the decoder return code.


```
1st_stage/63ff0ae5_en-US_001.uim  v5.0  name 'Group1p1.US.Stage1.PDK5XS.220619'
    eai2 @0x0003c8  56 928   fmt 2.2 kind 12.2
    eai2 @0x00e3c8   6 076   fmt 2.2 kind 12.2
    eai2 @0x00fdc8 132 752   fmt 2.2 kind 4.3
    eai2 @0x0305c8   8 492   fmt 2.2 kind 4.3
    PDK marker 'PDK5' @0x1c8 and @0xfbc8
1.5_stage/63ff0ae5_en-US_002.uim  v5.0  'Group1p1.US.Stage1p5.PDK5XS.220617'
    eai2 @0x0004b8 408 936 / @0x0643b8 41 772
1st_stage/63ff0ae5_en-US_038.uim  v8.0  'Group1p1.US.Stage1.PDK6XS.MFCN.220819'
    eai2 @0x4c8 41 508 / @0xa8c8 5 820 / @0xc2c8 111 372 / @0x277c8 13 708   (PDK6)
ubm/general_UV_015.uim            v8.0  'qc_ti_uv'
    eai2 @0x807d8 10 572 796
ubm/general_UV_024.uim            v7.0  'qc_ti_uv'
    (no eai2 – pure float UBM payload)
```

Loader evidence for those fields (disassembly under `native/disasm/`):

* `sml::decodePdkXSHeader` @`0x901d4` – rejects anything whose first u32 is not
  `"PDK4"`/`"PDK5"`/`"PDK6"` (`0x50444b34/35/36`) and returns generation 5 or 6.
  The `PDK5_1st_stage` / `PDK6_1st_stage` strings sit at body+0x20.
* `ListenBigSoundModel::readFrom` @`0x63890` – accepts version words
  `0x300`, `0x4xx…0x7xx` (`and w9,w8,#0xfffffcff; cmp w9,#0x400`) and `0x800`,
  rejects sizes ≥ `0x5F5E101` (100 MB), copies the blob and re-dispatches on the
  sub-model type through a 20-entry jump table.
* `ListenBigSoundModel::readHeaderFrom` @`0x63ab0` – same version gate.
* `sml::parseBigSoundModelwithMemoryAllocation` @`0x88020`,
  `sml::parseIndividualModelWithType` @`0x9455c`, `getSoundModelHeader` @`0x8116c`
  produce the per-keyword / per-user tables.

The `count` 16-byte descriptors at 0x1A8 are validated but not interpreted
field-by-field (e.g. `_001`: `{05 00 00 00, 00 00 00 00, 00 fa 00 00,
01 00 00 00}` then `{05 00 00 00, 00 fa 00 00, 00 2c 02 00, 20 00 00 00}`
followed by the `PDK5_1st_stage` name at 0x1C8). The per-keyword and per-user
tables behind them are produced by `sml::parseBigSoundModelwithMemoryAllocation`
@`0x88020` / `sml::parseIndividualModelWithType` @`0x9455c`, which were not
decompiled.

---

## 4. `eai2` neural-network container (embedded + `speech_float.eai`)

Magic `"eai2"`, 16-byte header, then a descriptor area and a parameter area:

| offset | field | `speech_float.eai` | `…_001_64968.eai` | `…_002_1208.eai` |
|---|---|---|---|---|
| 0x00 | magic `eai2` | | | |
| 0x04 | u16 fmt major/minor | 2.2 | 2.2 | 2.2 |
| 0x08 | u16 kind | 4.3 | 4.3 | 4.3 |
| 0x0C | u32 (blob class) | 4 | 4 | 4 |
| 0x10 | u32 total size | 36 632 | 132 752 | 408 936 |
| 0x14 | u32 | 864 | 1 680 | 15 720 |
| 0x18 | u32 | 192 | 2 | 6 |

Every carved blob's `0x10` size lands exactly on the next `"eai2"` magic (or on
EOF), so carving is unambiguous: 16 blobs, `models/eai/` (see
`evidence/eai_carve.txt`). Identical hashes show the PDK5 1st-stage network
(132 752 B) is shared by `_001`, `_036` and `_040`, while `_038` (PDK6/MFCN)
uses a different pair.

The descriptor area is *not* decoded. The interpreter is
`libeai_floating.so`, whose only exports are
`eai_init_ex/eai_apply/eai_preapply/eai_execute/eai_deinit/eai_get_property/
eai_set_property/eai_get_interface`; the parser is internal. Ghidra
(`native/ghidra_eai.c`, `native/ghidra_eai_net.c`) resolves the load chain:

```
eai_init_ex -> eai_init_internal @0x10c654
   -> load_network @0x10d754
        extract_eai_model_from_buffer(buffer, &size)
        validate_eai_model(ctx, model, size)
        copy_eai_meta_tensor(ctx)               # meta tensor = ctx[0]
        initialize_eai_model_info(ctx, model, size)
            rejects fixed-point models in the float runtime:
            "Error: fixed point model, float runtime!"
        initialize_tensor_meta(ctx)             # "fail to parse the eai tensors"
        relocate_tensor(ctx, 0, ctx[0x1c], ctx[0x1e])
        parse_eai_layers(ctx)   @0x10dcd0      # layer count = meta[0],
                                                # table at meta+8,
                                                # size = calculate_total_layer_size(...)
        load_eai_layers(ctx, loaders, n)
        initialize_io_tensor(ctx)
   -> load_layer_call_table(ctx)
```

The individual loaders (`extract_eai_model_from_buffer`,
`initialize_tensor_meta`, `calculate_total_layer_size` and the per-op
`eai_op_layer_*` modules) were not decompiled, so the layer list, tensor shapes
and weight offsets inside `eai2` remain **unknown**.

What *is* known about the networks comes from the caller side:

* `udk7_network_init` @`0x5c044` validates a different magic, `"UDK7"`
  (`0x55444b37`), plus `[+8]==size`, `[+0x10]==0x188` (header size), and then
  initialises **three** sub-graphs from offsets `+0x198`, `+size1`, `+size2`
  through the EAI interface (`[+0x160]` function pointer).
* `PdkXSCore::initialize` @`0x6abe0` issues the model setup through internal
  helpers at `0xb7d58`/`0xb7d60` (parameter ids `0x10..0x14`, values
  `0x10`/`0x20`/`0x28`) and through a property call at `0xb7bf0`
  (`w0 = 0xf`, 12-byte payload, i.e. an EAI-style set/get property); the
  wrappers were read as assembly only, so their exact EAI property enum is
  inferred.
* `Stage2UVCore::initialize` @`0x6eb58` does the same for the 2nd stage.

---

## 5. Stage → model → runtime map

| stage | model file (asset) | container | bytes | runtime | loader (libqvasml unless noted) | in/out dims |
|---|---|---|---|---|---|---|
| DNN VAD | `speech_float.eai` | `eai2` float | 36 632 | `libeai_floating` via `libdnnvad_jni`→`libqvavad` (`capi_v2`) | `Vad::init` @`0x2fd0` (libdnnvad_jni), `nnvad_wrapper_*` (str) | 1 speech prob/frame (threshold 0.85) |
| 1st stage KW (PDK5_XS) | `1st_stage/…_001/_036/_040.uim` | `.uim` v5 + `PDK5` + 4×`eai2` | 198–209 K | `libqvasml` `PdkXSCore` + `libeai_floating` | `decodePdkXSHeader` @`0x901d4`, `PdkXSCore::initialize` @`0x6abe0` | 80-mel → 5 keyword scores (`FirstStageKW.thresholds` = 5 values) |
| 1st stage KW (PDK6_XS MFCN) | `1st_stage/…_038.uim` | `.uim` v8 + `PDK6` + 4×`eai2` | 175 560 | same | same (`Generation 6`) | 80-mel → 5 keyword scores |
| 1.5 stage KW | `1.5_stage/…_002.uim` | `.uim` v5 + 2×`eai2` | 452 536 | `libqvasml` + EAI | same | 80-mel → 5 keyword scores (`MiddleStageKW.thresholds`) |
| 2nd stage UV (platform / TIUV) | `ubm/general_UV_024.uim` | `.uim` v7, **no eai2** | 7 304 344 | `libqvasml` `Stage2UVCore` / `GmmAdapt` | `Stage2UVCore::initialize` @`0x6eb58`, `verifyUserRecording` @`0x77570` | mel/feature vector → 1 UV score/kw (`SecondStageUV.threshold` 55.0, kwcmd 45.0) |
| 2nd stage UV (keyword-dependent) | `ubm/general_UV_015.uim` | `.uim` v8 + one 10.5 MB `eai2` | 11 099 104 | `libqvasml` + EAI (DNN UV) | same | 1 UV score/kw (13.0, kwcmd 19.0) |
| EPD | – | – | – | `EndPointDetection` (log10 energy) | `EndPointDetection::UpdateFrame` | frame → speech/non-speech |
| ASR | `ASR_binary/Whisper/encoder_model_quantized_cached.dlc` + `vocab.bin` (**downloaded**, absent) | SNPE DLC | – | `libSNPE.so` + `libwhisper_lib.so` | `Java_com_qualcomm_whisper_WhisperNative__1init` @`0x3e730` | PCM → token ids |

`AlgorithmType` (24 values, `sources/classes/…soundmodel.AlgorithmType.java`)
maps each of these to the integer passed to `Algorithm.init(..., typeOrdinal)`:
`PDK5_XS=2, PDK6_XS=3, MFCN=4, PDK5XS_1P5=5, PDK6XS_1P5=6, VOP=7,
UBM_DNN=9, UBM_TIVOP=10, PDK=14, RNN=15, DNN_UV=19, TIVOP=20, IDUV=23`, each
with a `(major,minor)` pair (e.g. `PDK5_XS` = 5.0, `PDK6_XS` = 6.0,
`UBM_DNN_V2P6` = 8.0).

Model **selection** is SOC-driven
(`sources/classes/…configuration.onevoice.{a,e,c}.java`):

| soc_id | 1st stage | 1.5 stage | 2nd stage UV |
|---|---|---|---|
| 454, 472 | `…_040` | `…_002` | `general_UV_015` |
| 506, 547, 564 | `…_036` | `…_002` | `general_UV_015` |
| 507, 565 | `…_040` | `…_002` | `general_UV_015` |
| 519, 536 | `…_001` | `…_002` | `general_UV_015` |
| 530, 531, 540, 591 | `…_036` | `…_002` | `general_UV_015` |
| 537, 583 | `…_036` | `…_002` | `general_UV_015` |
| 557, 577 | `…_038` (MFCN) | **none** | `general_UV_015` |
| 568, 602 | `…_036` | `…_002` | `general_UV_015` |

plus a platform-wide `GeneralUvBaseModel = general_UV_024`
(`SocIdModelMapper.j("general_UV_024")`), matching the qvaconfig comments
("2nd stage UV UBM for keyword dependent UV" vs "platform 2nd stage UV UBM for
TIUV").

---

## 6. Audio front-end (recovered)

Confirmed from `native/disasm/udk7_feature_{init,process,spectrum,mel}.s`,
`realfft.s`, `udk7_q_complex_vector_mag_f.s`, `udk7_q_fft.s`:

| parameter | value | evidence |
|---|---|---|
| sample rate / format | 16 000 Hz, mono, PCM16 | `NormalAudioRecordAction.java:37` `new AudioRecord(src, 16000, 16, 2, …)` |
| analysis frame | 480 samples (30 ms), hop 160 (10 ms) | `udk7_feature_process`: counter `#0x1e0`, `memmove(0x280)` from `+0x140`, `sub #0xa0` |
| window | Hann, N = 400, symmetric, zero padded into 480 | `udk_hann_window` (.data 0x1162a0, 1920 B = 480 f32): nonzero 1…399, peak 1.0 at 199/200, 0 at 400…479 |
| input scaling | × 1/32768 (`0x38000000`) | `udk7_feature_spectrum` `movi v0.2s,#0x38,lsl#24` |
| FFT | 512-point real (built as complex, `udk7_q_fft` with `sincos` twiddles) | `realfft` copies 512 floats then `bl udk7_q_fft` |
| magnitude | `sqrt(re²+im²)` for 256 bins | `udk7_q_complex_vector_mag_f` @`0x5f6c0` (`fmul`,`faddp`,`fsqrt`) |
| mel | 80 triangular filters, sparse table: `start[80]`, `len[80]`, `offset[80]`, 470 taps | `udk_mel_filter_{start,len,offset,80}` (.data 0x116a20/0x116b60/0x116ca0/0x116de0) |
| mel bin range | bins 5 … 243 of 256 (≈156 Hz … 7.6 kHz) | reconstructed matrix, `models/frontend/mel_matrix_80x256.npy` |
| mel op | dot product of |X| with the triangular taps (`udk7_q_vector_dot_f`) | `udk7_feature_mel` @`0x5e6c8` |
| log | **none** in this path | only `log10` call sites in `EndPointDetection`, `NoiseMixer`, `getSegmentStats`, `runAGAOnUserRecordings` |

The exported coefficients are stored verbatim in `models/frontend/`
(`hann_480.npy`, `mel_taps.npy`, `mel_matrix_80x256.npy`, `frontend.json`).

Also embedded in `libqvasml.so` (extracted, not yet wired to a decoder):
`ubm_means` (0x11b4b8, 4992 B = 2496 × int16) and `ubm_inverse_variances`
(0x11c838, 2496 × int16) with `ubm_weight_gconst` (0x11b3b8, 64 × int32);
`Gmms::init` @`0x71a8c` shows the runtime takes a 12-byte header
`{dim, num_mix, ?}` of float32 arrays, so the on-disk int16 blobs are the
"UDK creator default" fixed-point copies. 2496 = 64 × 39, i.e. 39-dim features
× 64 mixtures **if** that split is right — unverified (see §8).

---

## 7. Thresholds and timings (`assets/qvaconfig_v4.xml`)

232 `<Item>` values, 2 container groups (`Default`, `63ff0ae5`, `Platform_UV`,
`a977b1c7`, `db4000a1`). The ones that matter for detection:

| key | value | meaning |
|---|---|---|
| `V4/V5.FirstStageKW.threshold` | 40 / 30 | 1st-stage acceptance (score 0…100) |
| `63ff0ae5_en-US_00{1,036,040}.FirstStageKW.threshold` | 26 | per-model 1st-stage threshold |
| `63ff0ae5_en-US_038.FirstStageKW.thresholds` | `55,61,36,35,33` | 5 per-keyword thresholds (MFCN model) |
| `MiddleStageKW.thresholds` (1.5 stage) | `50,52,49,37,35` | 5 per-keyword thresholds |
| `SecondStageKW.threshold` | 40 / 74 / 56 / 87 / … | 2nd-stage keyword confidence |
| `SecondStageUV.threshold` | 55.0 (`general_UV_024`), 13.0 (`general_UV_015`) | UV (speaker) score |
| `SecondStageUV.threshold.kwcmd` | 45.0 / 19.0 | UV score when the utterance is a command |
| `Enrollment.threshold` | 30 (V4 50, V5 25) | enrollment acceptance |
| `Enrollment.utterances` / `.noisy` / `.max` | 5 / 3 / 11 | enrollment budget |
| `KW.preTolerance` / `postTolerance` | 400 / 400 ms (default 300/440) | audio slice for 2nd stage |
| `UV.preTolerance` / `postTolerance` | 200 / 200 ms | audio slice for UV |
| `nnvad.threshold` | **891289** = 0.85 × 2^20 (Q20) | DNN VAD speech threshold |
| `nnvad.len.hangover` / `len.future` | 70 / 0 frames | VAD hangover (700 ms) |
| `cmd.end.detection.threshold` / `timeout` | 30 frames / 4000 ms | end-of-command detection |
| `HistoryBufferDuration` / `PreRollDuration` | 2000 / 1000 ms | STHAL buffers |
| `SecondStage.bypass` | false | UV can be skipped |
| `UV.antiSpoofEnable` / `antiSpoofThreshold` | false / 0.3 | ID R&D anti-spoof |
| `EPD.*` | `minSnrOnset 3.0`, `forgettingFactorNoise 0.03`, `numMinFramesInPhrase 15`, `preEmphasize 1`, … | online EPD |
| `V4.UV.DnnUvAlpha/Beta` | 100.0 / −21.0 | DNN-UV score calibration |
| `V4.UV.VopAlpha/Beta/Gamma/Delta` | 1.0 / 0.1 / −2.5 / 20.0 | VOP score calibration |
| `V4.UV72Alpha/Beta` | 100.0 / 0.0 | UV7.2 calibration |

`ModelConfigurationKey` (237-line enum, `sources/classes/…v4.ModelConfigurationKey.java`)
gives the exact `Bundle` key strings passed to
`Algorithm.start()/updateTIUVThreshold()`, e.g. `kwThreshold`,
`1P5.thresholds`, `uvThreshold`, `vop.alpha`, `dnnuv.beta`, `uv72.alpha`,
`VAD.nnvad_threshold`.

---

## 8. JAX reimplementation

`reimpl/pipeline.py` (runs, `verify.txt`):

* **REAL**: front-end entirely — Hann(400-in-480), 512-pt FFT, 256 magnitudes,
  80×256 sparse mel matrix with the extracted coefficients, 16 kHz framing;
  and the decision layer — Q20 VAD threshold, hangover/lookahead, 5-keyword
  thresholds, tolerances.
* **ARCHITECTURE_ONLY**: VAD net (80-mel × 5-frame context → 1 logit), 1st
  stage net (80-mel × 40-frame context → 5 logits), 1.5 stage net (same), 2nd
  stage UV scorer. Weights are deterministic pseudo-random
  (`jax.random`), not trained, because the `eai2` layer table is undecoded.

Verification on 2 s of synthetic audio (int16): `(198, 80)` mel features,
numpy↔JAX front-end max abs diff `7.6e-06`, all four stages produce the
documented shapes, exit 0.

`reimpl/uim_container.py` decodes the `.uim` header and enumerates the embedded
`eai2` blobs for all 7 files (`evidence/uim_parse.txt`).

---

## 9. Unknowns / blockers

1. **`eai2` layer table** — the descriptor region (0…~2152 B for
   `speech_float.eai`) has no strings and no self-describing tags. Ghidra
   decompilation of `libeai_floating.so` recovered the load chain
   (`eai_init_internal` @0x10c654 → `load_network` @0x10d754 →
   `validate_eai_model`/`initialize_tensor_meta`/`parse_eai_layers` @0x10dcd0
   → `load_eai_layers` → `initialize_io_tensor`), but the sub-parsers
   (`extract_eai_model_from_buffer`, `initialize_tensor_meta`,
   `calculate_total_layer_size`, the `eai_op_layer_*` loaders) were not
   decompiled. Tensor shapes, op types, weight offsets and quantisation stay
   unknown, so the DNN stages remain ARCHITECTURE_ONLY. Nothing was inferred in
   place of the missing output.
2. **`.uim` body tables** — keyword ids/threshold seeds/user tables behind the
   16-byte individual-model descriptors at 0x1A8 are produced by
   `sml::parseBigSoundModelwithMemoryAllocation` (@0x88020) and
   `sml::parseIndividualModelWithType` (@0x9455c), which were not decompiled;
   only the descriptor table itself (count, 16-byte stride, nonzero words) is
   verified.
3. **UBM blob encoding** — 2496 int16 means / inverse variances + 64 int32
   weights; dim/mix split (64×39 vs 32×78) and the fixed-point scale are not
   proven, so the GMM path is extracted but not wired.
4. **ASR weights** — `encoder_model_quantized_cached.dlc` and `vocab.bin` are
   downloaded at runtime via QESDK (`VOICEUI_API_START_DOWNLOAD`,
   `ASR_binary/Whisper`), not shipped in the APK; `libwhisper_lib.so` is a
   whisper.cpp/ggml runtime only (weights absent).
5. **DSP path (`libqvapdk*`, `libqvarnn`, `libqvauv`)** — CAPI_v2 modules that
   run inside audioserver; nothing in the APK shows how the DSP is configured
   (model blobs are handed to it by the HAL).
6. **Mel normalisation before the networks** — the native front-end emits
   linear mel magnitudes; whether the EAI model applies log/CMVN internally is
   not visible from the caller. The reimplementation uses log + per-utterance
   mean/variance (marked INFERRED).

---

## 10. Android entry, data path and consumer (patch pass)

Second analysis pass over the DEX plumbing that §2 summarised only at the
native boundary. New decompiled classes are listed in
`sources/index.md` ("Patch pass" section); the batch transcript is
`evidence/patch_batch_decompile.txt`; JNI entries are
`evidence/jni_symbols_libqvajni.txt`. Detailed per-stage tables live in
`PIPELINE.md`.

### 10.1 Component map (confirmed, `evidence/AndroidManifest.xml`)

| component | exported | role |
|---|---|---|
| `VoiceAssistApplication` | – | builds the `Facade` (`d.g`), `System.loadLibrary("qvajni")`, starts/stops `ForegroundService`, kills itself if its own package record is hidden |
| `receiver.VoiceAssistReceiver` | yes | `BOOT_COMPLETED`, `MY_PACKAGE_REPLACED` → `a()` (writes `qva.version=<ver>` via `AudioManager.setParameters`) then `startService(ReenableWakewordService)` |
| `receiver.PackageChangedReceiver` | no (registered in code) | `PACKAGE_{ADDED,REMOVED,DATA_CLEARED}` → `com.quicinc.voice.activation.action.PACKAGE_CHANGED` handling, drops stale client groups |
| `engineservice.ForegroundService` | no | `startForeground(1, NotificationChannel "QVANotification")`; manifest `foregroundServiceType=0x80` = **microphone**. Started only when `SDK==31||32` **and** `MANUFACTURER ∈ {OnePlus, OPPO, realme, Xiaomi}` (`ForegroundService.b/c/d`); otherwise `ACTION_STOP_FOREGROUND` |
| `engineservice.ReenableWakewordService` | no | post-boot reload: waits for the legacy and OneVoice model stores (`g.u()`, `g.k()`), kills the process when no model is active (`n.Y.a`) |
| `engineservice.WakewordToggleService` | **yes** | `IWakewordToggleService`: `enableRecognition(client, bundle, soundModelPfd)`, `disableRecognition`, `isWakewordRecognitionEnabled`; also `WakewordToggleDebugService`, `WakewordSettingsService` |
| `engineservice.UtteranceProviderService` | **yes** | `IUtteranceProviderService`: the client pulls the captured utterance / stops the recording |
| `engineservice.EnrollmentService` | **yes** | `IEnrollmentService` (P2, §10.5) |
| `activity.PermissionActivity` | yes | only UI: `RECORD_AUDIO` prompt (`p004e.a`) |
| `llm.InputProviderService`, `llm.LLMRecognitionService` | yes/no | in-app LLM client path (`ClientInfo.forLLMClient`) |
| `tts.TTSService`, `libupdate.*` | yes | unrelated to detection |

There is **no** `VoiceInteractionService` and no always-on `AudioRecord`: the
keyword itself is detected by the sound-trigger HAL/DSP, and the app only
*registers* the model and then *captures the utterance* after the event.

### 10.2 Detection enable → STHAL session

```
WakewordToggleService.l()                       (engineservice/WakewordToggleService.java:119)
  p004e.a.a(ctx)                                RECORD_AUDIO for this package
  new C0028i(bundle)                            ClientInfo{packageName, className, forLLMClient}
  new C0029i0(bundle)                           SoundModelKey parts {group, locale, userId, keyword}
  k.c.e(name) / new k.b(name)                   ClientGroup registry
  i.d(pfd) -> BigSoundModel                     model file parsed (report §3 container)
  bVar.a(model); i.k(model)                     registered + persisted
  p(bVar, pVar) -> n.D.p(bVar, g.l())           OneVoiceSoundTriggerHelper turn-on
     p015p.f.E(uuid, info, ss, capturer)        (p.f.java:229)
        E.n.c()   = SoundTriggerModule.attachModule(listModulesAsList()[0])
        new p015p.g(uuid) + SoundTrigger.KeyphraseSoundModel(keyword, vendor, .uim bytes, Keyphrase[])
        E.o.z(module) = loadSoundModel() -> startRecognition(handle, RecognitionConfig)
     p015p.j.o(uuid, ss, capturer)              second-stage UV registration
  startService(ReenableWakewordService)         so a later boot can restore it
```

`SoundTrigger.RecognitionConfig` is built by `N.g(boolean, boolean, KeyphraseRecognitionExtra[], byte[])`
(`N.g.java`); the per-keyword extras carry `coarseConfidenceLevel` and
`ConfidenceLevel[]`, which is what `RecognitionEvent` later reads back.

### 10.3 Event path: STHAL → Java → 1.5 stage → 2nd stage

1. `E.m.f(N.c)` — `SoundTriggerModule.StatusListener.onRecognition(KeyphraseRecognitionEvent)`;
   status is mapped by `QKeyphraseRecognitionEvent$QRecognitionStatus`
   (`SUCCESS` → `d`, `ABORT` → `e`, `FAILURE` → `f`).
2. `p015p.c.e(N.c)` → `p015p.g.B().d(uuid, N.c)`; the session listener is
   `p003d.d` (`Facade`), which acquires a 500 ms `PARTIAL_WAKE_LOCK("Recognition")`
   and calls `g.n().i(uuid, new RecognitionEvent(c))` (`d.g.java:252`, `d.d.java:34`).
3. `RecognitionEvent(N.c)` (`soundtrigger/RecognitionEvent.java`) decodes the
   opaque payload (`x.d`) into:
   `captureSession`, `beginIndex`, `endIndex`, `keywordId/detectedIndex`,
   `confidenceLevel`, `isMultiKW`; `toString()` prints every field.
4. `p013n.z.i(uuid, event)` (`OneVoiceRecognitionHandler`):
   * `g.j().h(uuid)` → sound-model group for this session UUID;
   * `AtomicBoolean.compareAndSet(false,true)` = single recognition at a time;
   * `G.g.b(keyword)` → audio saver (`DummyAudioStreamSaver` unless `persist.qva.dump=1`);
   * `saver.o()` creates the `ParcelFileDescriptor` socket pair;
   * `saver.w(event)` starts capture (`audiorecord.b.m(...)`);
   * `n(group,event)` → `n.L` start-recording + 1.5-stage job;
   * `n.L.m()` waits (realtime `Condition`, 10 s cap) for the 1.5-stage bundle.
5. `audiorecord.b.m()` creates `AudioStreamProvider` (10 s) and a
   `OneShotAudioRecordAction` (`RecursiveAction`): `AudioRecord` with
   `USAGE_MEDIA`/`CONTENT_TYPE_SPEECH`/`setInternalCapturePreset(1999)`,
   16 kHz/mono/PCM16, 64 000 B buffer; read loop of 3 840 B (120 ms) →
   client PFD + stream provider + `IAudioStreamSaver` (`QVA-HOTWORD` doze
   whitelist for 300 s per capture, `I.b.a`).
6. 1.5 stage: `n.L.q()` posts to `HandlerThread("algorithm", -16)` →
   `p015p.f.v(uuid, saver)` → `OneVoiceAlgorithmTask.compute()` (fork/join over
   the algorithm list) → reader `.f()` → `Algorithm.processAlgorithm` →
   JNI `Algorithm_process` → `smlProcess`. Result bundle keys are listed in
   `PIPELINE.md` §5.3.
7. 2nd stage: `n.L.s()` → `p015p.j.i(uuid, saver, kwStart, kwEnd)`; when the
   model needs it, the reader is `TIUVReader` (`j.g`), which itself runs
   `DNNVADAction` to bound the command; otherwise `OneShotReader`.
8. Timestamps: `"1.5Stage.startTimestamp"`, `"1.5Stage.finishTimestamp"`,
   `"SecondStage.start/finishTimestamp"`, `"AudioMetaData.startRecordingTimestamp"`,
   `"AudioMetaData.stopRecordingTimestamp"` — all `System.currentTimeMillis()`.

### 10.4 Consumer: keyphrase → client service

* `n.z.i()` picks the `ClientGroup` from `"1.5Stage.detectedIndex"`
  (`a.f(int)` resolves multi-keyword models); no group ⇒ `"cannot find client
  group"` + `p()` (abort).
* Validity: preferred client (`ClientGroup.l()`), package installed, and
  `RECORD_AUDIO` granted to that package (`g()`); any failure ⇒ abort + turn off.
* `a(client)` → `bindService(Intent().setComponent(ComponentName(pkg, serviceClass)),
  conn, 0x100001)`; extra `"isOneVoice"=true` only for the in-package LLM client.
* Delivery: the bound client calls back into `UtteranceProviderService`
  (`i()` start / `j()` stop) which dispatches to `n.z.k/q`; the reply is
  `IUtteranceReceiverCallback.b(ParcelFileDescriptor, Bundle)` =
  `onUtteranceReceived`, or `.c(...)` = `onUtteranceVerificationFailure`.
  The `ParcelFileDescriptor` is the raw 16 kHz/mono/PCM16 stream; the `Bundle`
  carries the `AudioMetaData.*` / `*Stage.*` keys above.
* No broadcast, no intent, no `MediaSession`: detection is binder-only.
* Abort on the client side: `"Clinet->QVA stopRecording"` (`n.z.q`) closes the
  recording, stamps `"AudioMetaData.stopRecordingTimestamp"` and unbinds.

### 10.5 Enrollment (P2)

```
EnrollmentService.z()  startUserVoiceEnrollment(bundle, pfd)     EnrollmentService.java:218
  session key = getNameForUid(callingUid)
  EnrollmentType  -> n.q (OneVoice PDK) | n.t0 (general UV -> general_UV/uv.udm) | M (legacy PDK)
n.g.v(ByteBuffer)      base model + Enrollment.audiosource + Enrollment.trainingPostRollPadding
n.g.y(id, cb)          startUtteranceTraining: timer (n.f Handler, Enrollment.timeout) +
                       TrainingAudioRecorder.r(id) + J() -> NormalAudioRecordAction
                       (source from Enrollment.audiosource, default VOICE_RECOGNITION=6;
                        16000/16/2, 64000 B buffer, 1280 B reads, EPD or DNNVAD gated)
n.g.z()                stopUtteranceTraining: n.q.B() = SoundModelCodec.verifyUserRecording(
                       model, utteranceId, ShortBuffer, postRoll, ConfidenceData)
EnrollmentService.p()  commitUserVoiceEnrollment: score = h(),
                       ParcelFileDescriptor p = m() (createReliablePipe),
                       callback.k(fd, {"UVREnrollment.quality_score": score}), then
                       n.g.A() writes the generated model into the pipe
```

* Utterance budget comes from `qvaconfig_v4.xml` (`Enrollment.utterances` 5,
  `.noisy` 3, `.max` 11, `Enrollment.threshold` 30).
* Native calls used: `SoundModelCodec.getSizeWhenCreateUserModel`,
  `createUserModel`, `extend`, `verifyUserRecording`, `mergeModel`,
  `getByteBufferSize`, `createFirstStageSM` (all `Java_…SoundModelCodec_*`,
  addresses in `evidence/jni_symbols_libqvajni.txt`).

**Storage (per user).** `u.g` (`OneVoiceSoundModelProvider`) writes
`getFilesDir()/<container>/<clientGroup>/<userId|null>/{information.xml,
<AlgorithmType>.binary}` and reads it back on boot
(`u.g.java:143/176/191`). The general UV lives at
`getFilesDir()/general_UV/uv.udm` (`n.p.f()`), the shipped models at
`getFilesDir()/onevoice_models/{1st_stage,1.5_stage,ubm}/<name>.uim`
(`n.p.d/g`). The in-APK model selection is SOC-driven (report §5).

**Fallback if the model is missing.** `n.D.q()` → `"model unsufficient"` /
`ALGORITHM_NO_MODEL`; `n.V.g()` → `"sound model is not found"`; a failed
second stage rolls the first stage back (`n.D.q`); `OneVoiceModelFileManager.g()`
re-copies a missing `.uim` from `assets/` on first use.

### 10.6 Screen-off survival (mechanisms, all confirmed)

| mechanism | code |
|---|---|
| keyword detection runs in STHAL/DSP, not in the app process | `E.o.z` → `SoundTriggerModule.startRecognition` |
| `AudioRecord` uses the HOTWORD capture preset (1999), which the audio policy allows while the screen is off for `CAPTURE_AUDIO_HOTWORD` holders | `I.a.a()` |
| Doze exemption for 300 s per capture, tag `QVA-HOTWORD` | `I.b.a(pkg, 300000, "QVA-HOTWORD")` → `IDeviceIdleController.addPowerSaveTempWhitelistApp` (`L.a`/`M.a`) |
| 500 ms `PARTIAL_WAKE_LOCK("Recognition")` on each keyphrase event | `d.g.b()` |
| foreground service of type microphone — only on SDK 31/32 with Xiaomi/OPPO/realme/OnePlus | `ForegroundService` |
| reboot restore | `VoiceAssistReceiver` → `ReenableWakewordService` |

### 10.7 Permissions actually required

`RECORD_AUDIO` (self + each client), `CAPTURE_AUDIO_HOTWORD` (HOTWORD capture
preset), `FOREGROUND_SERVICE` + `FOREGROUND_SERVICE_MICROPHONE`,
`CHANGE_DEVICE_IDLE_TEMP_WHITELIST` (hidden `deviceidle` binder),
`WAKE_LOCK`, `RECEIVE_BOOT_COMPLETED`, `MODIFY_AUDIO_SETTINGS` (`qva.version`
parameter), `START_ACTIVITIES_FROM_BACKGROUND`, `FORCE_STOP_PACKAGES`
(manifest only, no call site found).
