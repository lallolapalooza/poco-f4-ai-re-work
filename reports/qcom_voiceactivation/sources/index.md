# Decompiled sources (jadx single-class)

Everything here was produced with
`tools/decompile_classes.py ... --jadx <wrapper that adds -j 3>`, i.e. one
`jadx --single-class` invocation per class, output confined to
`sources/classes/`. Logs in `sources/logs/`, per-batch hit tables in
`sources/hits_sources_batch{1,2,3}.json`.

File names are the fully-qualified class name; jadx obfuscated some packages
(`G.q`, `p009j.*`, `O.e`, `v.*`, `m.a`), those are the original app's R8 names.

## Detection path

| class | why it matters |
|---|---|
| `com.quicinc.voice.activation.onevoice.algorithm.OneVoiceAlgorithmTask` | ForkJoin driver: init/start/process/deinit per `Algorithm`, frame loop, result Bundle |
| `com.quicinc.voice.activation.algorithm.Algorithm` | JNI surface: `init(ByteBuffer,int,int)`, `start(Bundle)`, `process(ShortBuffer,Bundle,Bundle,int)`, thresholds/tolerances per `ConfigurationId` |
| `com.quicinc.voice.activation.algorithm.AlgorithmTask` / `AlgorithmProcessResult$*` / `AlgorithmResponseStatus` | plumbing, status codes |
| `com.quicinc.voice.activation.soundmodel.AlgorithmType` | 24 algorithm ids (`PDK5_XS`, `PDK6_XS`, `MFCN`, `PDK5XS_1P5`, `VOP`, `UBM_DNN`, `DNN_UV`, `TIVOP`, `IDUV`, …) plus `(major,minor)` |
| `com.quicinc.voice.activation.audiorecord.b` (`OneVoiceHotwordCapturer`) | frame sources over the SoundTrigger buffers |
| `com.quicinc.voice.activation.audiorecord.NormalAudioRecordAction` | `AudioRecord(src, 16000, 16 /*mono*/, 2 /*PCM16*/, …)` |
| `com.quicinc.voice.activation.soundtrigger.RecognitionEvent` | event geometry handed to the algorithm |

## Models / configuration

| class | why it matters |
|---|---|
| `com.quicinc.voice.activation.soundmodel.BigSoundModel` | per-user big sound model (keyword, user, locale, version, magic, configuration) |
| `com.quicinc.voice.activation.soundmodel.SoundModelCodec` | native enrollment API (`createUserModel`, `extend`, `verifyUserRecording`, `mergeModel`, `createFirstStageSM`) |
| `com.quicinc.voice.activation.soundmodel.SoundModel` / `SVAVersion` / `BigSoundModelType` / `ConfidenceData` | model metadata |
| `com.quicinc.voice.activation.soundmodel.opaque.data.Header$Key` | opaque header keys (`CONFIDENCE_LEVELS`, `HISTORY_BUFFER_CONFIG`, `KEYWORD_INDICES`, `TIMESTAMP`, `PERFORMANCE_MODE`) |
| `com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey` | exact Bundle key strings + types (`FirstStageKW.threshold`, `MiddleStageKW.thresholds`, `SecondStageUV.threshold.KW.0..5`, `VAD.nnvad_*`, `UV.VopAlpha`, …) |
| `com.quicinc.voice.activation.configuration.v4.{EPD,Sml,Device}ConfigurationKey` | EPD/SML/device keys |
| `com.quicinc.voice.activation.configuration.onevoice.a` (`KwModelMapper`) | SOC → 1st/1.5 stage model list, sets `general_UV_024` as platform UV base |
| `com.quicinc.voice.activation.configuration.onevoice.c` (`ModelCollection`) | `{KwHash, FsModelList, MsModelList, SsUvBaseModel}` |
| `com.quicinc.voice.activation.configuration.onevoice.d` (`OneVoiceModelManager`) | qvaconfig container ingestion, keyword groups |
| `com.quicinc.voice.activation.configuration.onevoice.e` (`SocIdModelMapper`) | `/sys/devices/soc0/soc_id` → per-SOC model set |
| `com.quicinc.voice.activation.configuration.onevoice.KwGroupEnum` | keyword grouping / overlay |

## VAD, EPD, enrollment, ASR

| class | why it matters |
|---|---|
| `com.quicinc.voice.activation.vad.DNNVAD` | loads `assets/speech*.eai`, config from `Platform_UV/nn_vad`, native `init/process/deinit` |
| `com.quicinc.voice.activation.vad.DNNVADAction` | frame pump for the VAD |
| `com.quicinc.voice.activation.onlineepd.EPD` | online EPD JNI |
| `com.quicinc.voice.activation.engineservice.EnrollmentService` | enrollment session orchestration |

## Not decompiled (deliberately)

Obfuscated helpers (`G.q`, `p009j.*`, `O.*`, `v.*`) are logging/Bundle/sound
model-file plumbing; the AI-relevant behaviour is fully visible in the classes
above plus the native libraries.

## Patch pass: entry points, data path, consumer (class name key)

Second decompilation pass (`evidence/patch_batch_decompile.txt`) added the
Android plumbing that the first pass skipped. jadx prefixes single-letter dex
packages with an index (`d.g` → `p003d.g`, `n.z` → `p013n.z`, …); file names in
`sources/classes/` are the jadx names, the `/* compiled from: */` banner in each
file gives the original source file name.

### Process / service / boot

| file | original name | role |
|---|---|---|
| `com.quicinc.voice.activation.VoiceAssistApplication.java` | `VoiceAssistApplication` | `onCreate` → `g.x()` builds the Facade graph, `System.loadLibrary("qvajni")`, starts/stops `ForegroundService` |
| `com.quicinc.voice.activation.receiver.VoiceAssistReceiver.java` | `VoiceAssistReceiver` | `BOOT_COMPLETED` / `MY_PACKAGE_REPLACED` → `startService(ReenableWakewordService)` |
| `com.quicinc.voice.activation.receiver.PackageChangedReceiver.java` | `PackageChangedReceiver` | `PACKAGE_ADDED/REMOVED/DATA_CLEARED` → drops client groups of the changed package |
| `com.quicinc.voice.activation.engineservice.ForegroundService.java` | `ForegroundService` | `startForeground(1, …)`, type microphone; OEM/SDK gate |
| `com.quicinc.voice.activation.engineservice.ReenableWakewordService.java` | `ReenableWakewordService` | post-boot reload of persisted sound models |
| `com.quicinc.voice.activation.engineservice.WakewordToggleService.java` | `WakewordToggleService` | **exported binder entry**: `IWakewordToggleService` (enable/disable/isEnabled) |
| `com.quicinc.voice.activation.engineservice.UtteranceProviderService.java` | `UtteranceProviderService` | **exported binder sink**: `IUtteranceProviderService`, client pulls the utterance |
| `d.g.java` | `Facade` | singleton object graph + `loadLibrary("qvajni")`, wake lock |
| `d.d.java` / `d.b.java` | `Facade` inner | OneVoice session listener → `RecognitionEvent` → `n.z` handler |
| `E.n.java` | `SoundTriggerModuleProvider` | `SoundTriggerModule.attachModule` / `listModulesAsList` |
| `E.m.java` | — | `SoundTriggerModule.StatusListener`: `onRecognition*`, `onModelUnloaded`, `onResourcesAvailable`, `onServiceDied` |
| `E.o.java` / `E.e.java` / `E.g.java` / `E.h.java` / `E.k.java` | `SoundTriggerSession`, `Session`, `SessionManager`, listener | STHAL session state machine (load/start/stop/unload) |
| `N.l.java` … `N.h.java` | `QSoundTriggerModule`, `QKeyphraseRecognitionEvent`, `QKeyphraseSoundModel`, `QRecognitionConfig`, `QKeyphrase`, `QKeyphraseRecognitionExtra`, `QConfidenceLevel` | one wrapper class per `android.hardware.soundtrigger.SoundTrigger.*` type (SDK-29 / SDK-30+ variants) |

### Detection dispatch and stages

| file | original name | role |
|---|---|---|
| `n.z.java` | `OneVoiceRecognitionHandler` | 1st-stage event → 1.5 stage → 2nd stage → client bind + deliver |
| `n.V.java` | `RecognitionHandler` | legacy (non-OneVoice) equivalent |
| `n.L.java` | `OneVoiceThreadLifeManager` | `HandlerThread("algorithm", -16)`, `HandlerThread("ThreadManager", -16)`, condition wait (10 s) |
| `n.D.java` | `OneVoiceSoundTriggerHelper` | 1st-stage session (de)activation + UV counter |
| `n.t0.java` / `n.m0.java` | legacy helpers | SoundTriggerHelper (legacy path) |
| `n.g.java` | `AbstractEnrollmentSession` | enrollment base: utterance collection, EPD params, commit |
| `n.q.java` | `OneVoicePDKEnrollmentSession` | `SoundModelCodec.verifyUserRecording` per utterance |
| `n.p.java` | `OneVoiceModelFileManager` | model file layout (`onevoice_models/…`, `general_UV/uv.udm`) |
| `n.i.java` | `ClientInfo` | `ClientInfo.packageName` / `.className` / `.forLLMClient` keys |
| `n.Y.java` | `ReenableWakewordService$Handler` | boot reload completion |
| `p.f.java` / `p.j.java` | `OneVoiceFSMSManager`, `OneVoiceSSManager` | 1st/1.5-stage and 2nd-stage algorithm managers |
| `p.c.java` / `p.g.java` / `p.d.java` / `p.e.java` | — | STHAL listener, `OneVoiceFSSession`, UV reference counter, session-listener interface |

### Audio transport

| file | original name | role |
|---|---|---|
| `j.i.java` | `Constants` | 32000 B/s, buffer-size helpers (`a()`, `c(ms)`, `d()`, `e()`, `h()`, `j()`=32 B/ms) |
| `j.h.java` | `AudioStreamProvider` | single-writer ring (byte[]) + per-consumer readers, `ReentrantLock`/`Condition` |
| `j.c.java` / `j.d.java` / `j.e.java` / `j.g.java` | `NormalReader`, `OneShotReader`, `PDKReader`, `TIUVReader` | reader policies incl. `FrameFlag` head/middle/tail and DNN-VAD command bounds |
| `j.a.java` | `AudioSink` | `ParcelFileDescriptor.createReliableSocketPair()` for the client audio stream |
| `I.a.java` | `QAudioRecord` | `AudioAttributes` + `setInternalCapturePreset(1999 /*HOTWORD*/)`, 16 kHz / mono / PCM16 |
| `I.b.java`, `L.a.java`, `M.a.java` | `QDeviceIdleController`, `DeviceIdleControllerR/S` | `IDeviceIdleController.addPowerSaveTempWhitelistApp(…, 300_000, …)` |
| `com.quicinc.voice.activation.audiorecord.c.java` | `TrainingAudioRecorder` | enrollment recorder (EPD/VAD-gated), per-utterance `ShortBuffer` lists |

### Result / client structs

| file | original name | role |
|---|---|---|
| `h.b.java` | `AlgorithmProcessResult` | `detected`, `detected_index`, `confidence_score`, `keyword_start_index`, `keyword_end_index`, `uv_score` + index→byte conversion (`index*10*32`) |
| `h.a.java` / `h.c.java` | `AlgorithmExecuteResult`, `AlgorithmSpliter` | stage result + 1st/2nd-stage split test |
| `k.a.java` / `k.b.java` / `k.c.java` | `Client`, `ClientGroup`, `ClientGroupManager` | per-keyword client registry, preferred client, per-user sound models |
| `u.a.java` / `u.b.java` / `u.g.java` / `u.l.java` | `IOneVoiceSoundModelProvider`, `ISoundModelProvider` + impls | persistence of user models under `getFilesDir()` |
| `v.j.java` / `v.c.java` | `SoundModelKey`, `SoundModelInfo` | `UUID.nameUUIDFromBytes(groupName-fileName-userId-locale)` |
| `r.c.java` / `r.h.java` | `OneVoiceModelCreator`, `SecondStageSoundModel` | model-file → SML binary conversion |
