# `qcom_voiceactivation` — data-pipeline contract

App: `com.quicinc.voice.activation` ("Qualcomm Voice Assist" 6.0.10).
APK: `system_ext/priv-app/QualcommVoiceActivation/QualcommVoiceActivation.apk`,
sha256 `565f8818b189d4cc0c90ad383f068093292fe51ac89ca8c1cc1d9680a7a32863`.

Two pipelines are documented here:

| id | name | trigger | terminal effect |
|---|---|---|---|
| **P1** | Always-on keyword detection (OneVoice) | STHAL/DSP keyphrase event over `SoundTriggerModule` | client `Service` receives `onUtteranceReceived(ParcelFileDescriptor, Bundle)` |
| **P2** | Speaker enrollment (user sound model) | client binder call to `EnrollmentService` | new per-user model file under `getFilesDir()`, `UVREnrollment.quality_score` returned |

Every row is tagged **confirmed** (observed in DEX/native/resource), **inferred**
(consistent evidence, one link not directly observed) or **unknown** (attempted,
not recoverable statically). All classes below are in `sources/classes/`; the
`/* compiled from: */` banner in each file gives the original source name.

---

## 0. End-to-end traces

### P1 — mic/HAL → assistant event

```
[client app, e.g. com.qualcomm.qti.voice.assist.client]
   │  bindService(IWakewordToggleService)                      CONFIRMED  manifest: WakewordToggleService exported
   ▼
WakewordToggleService.l(enableRecognition)                     CONFIRMED  engineservice/WakewordToggleService.java
   │  i.d(ParcelFileDescriptor) -> BigSoundModel; k.c ClientGroup registered
   ▼
n.D.p(clientGroup, audiorecord.b)      [OneVoiceSoundTriggerHelper]   CONFIRMED
   │
   ▼
p.f.E(uuid, SoundModelInfo, SecondStageSoundModel, capturer)   CONFIRMED  p.f.java:229
   │  n.f().c()  ->  SoundTriggerModule.attachModule()          CONFIRMED  E.n.c
   │  new OneVoiceFSSession(p.g) with SoundTrigger.KeyphraseSoundModel
   ▼
E.o.z(module) = loadSoundModel() + startRecognition()          CONFIRMED  E.o.java:181/203
   │  SoundTrigger.RecognitionConfig(maxCapture=?, allowMultiple=false,…)
   ▼
───────────── DSP / audioserver sound-trigger HAL ─────────────   (outside the APK)
   │  KeyphraseRecognitionEvent{captureSession, data, keyphraseExtras}
   ▼
E.m.f  (SoundTriggerModule.StatusListener.onRecognition)        CONFIRMED  E.m.java:82
   │  p.c.e(N.c) (OneVoice listener)
   ▼
p.g / p003d.d.d(uuid, N.c)                                     CONFIRMED  d.d.java:34
   │  PARTIAL_WAKE_LOCK("Recognition", 500 ms)                  CONFIRMED  d.g.java:252
   ▼
n.z.i(uuid, new RecognitionEvent(N.c))                         CONFIRMED  n.z.java:151
   │  AudioRecord(HOTWORD) started, 16 kHz/mono/PCM16
   │  120 ms reads -> AudioStreamProvider + client PFD
   ▼
n.L (HandlerThread "algorithm", prio -16)                      CONFIRMED  n.L.java:72
   │  1.5 stage: OneVoiceAlgorithmTask.compute() -> Algorithm.process -> JNI
   │  2nd stage: OneVoiceSSManager (UV/TIUV)
   ▼
bindService(ClientInfo.packageName/:className, 0x100001)       CONFIRMED  n.z.java:103
   ▼
client calls IUtteranceProviderService -> n.z.k/q              CONFIRMED
   ▼
IUtteranceReceiverCallback.onUtteranceReceived(fd, bundle)     CONFIRMED  n.z.java:237 (b()), 228 (c())
```

### P2 — enrollment

```
[client app] bindService(IEnrollmentService)                    CONFIRMED  manifest + EnrollmentService.onBind
   ▼
EnrollmentService.z(startUserVoiceEnrollment)                  CONFIRMED  EnrollmentService.java:218
   │  p004e.a.a() permission check, EnrollmentType from bundle
   ▼
n.q / n.t0 / M  (OneVoicePDKEnrollmentSession, general-UV, legacy)   CONFIRMED
   ▼
AbstractEnrollmentSession.v(ByteBuffer baseModel)              CONFIRMED  n.g.java:365
   │  reads Enrollment.audiosource, Enrollment.trainingPostRollPadding
   ▼
startUtteranceTraining(id) -> TrainingAudioRecorder           CONFIRMED  n.g.java:381
   │  NormalAudioRecordAction(src, 16000, MONO, PCM16, 64000)
   │  1280 B (40 ms) reads, EPD- or DNNVAD-gated
   ▼
stopUtteranceTraining -> SoundModelCodec.verifyUserRecording  CONFIRMED  n.q.java:70
   ▼
commitUserVoiceEnrollment -> h() score, m() pipe             CONFIRMED  EnrollmentService.java:149-167
   ▼
SoundModelCodec.createUserModel(...) -> u.g write             CONFIRMED  SoundModelCodec.java
```

---

## 1. Data sources / triggers

### 1.1 Process start

| # | trigger | class.method | confidence | evidence |
|---|---|---|---|---|
| 1 | process create | `VoiceAssistApplication.onCreate` → `g.x(ctx)` (Facade), `System.loadLibrary("qvajni")` | confirmed | `sources/classes/com.quicinc.voice.activation.VoiceAssistApplication.java`; `d.g.java` static block |
| 2 | package integrity guard | `b()` → `getPackageInfo(…, 0x100000)`; failure ⇒ `Process.killProcess` | confirmed | same file |
| 3 | `BOOT_COMPLETED`, `MY_PACKAGE_REPLACED` | `VoiceAssistReceiver.onReceive` → `startService(ReenableWakewordService.c())` | confirmed | `receiver/VoiceAssistReceiver.java:38`; manifest `<receiver>` filter |
| 4 | reboot reload | `ReenableWakewordService.onStartCommand` → `g.u().a(cb)` + `g.k().a(cb)`; kills the process when no model is active | confirmed | `engineservice/ReenableWakewordService.java`, `n.Y.java` |
| 5 | package add/remove/clear | `PackageChangedReceiver` → drop client groups of that package | confirmed | `receiver/PackageChangedReceiver.java`; registered from `d.g` ctor |
| 6 | no UI | only `PermissionActivity` (`PERMISSION_LAUNCHER`, `REQUEST_MIC_PERMISSION`) | confirmed | `evidence/AndroidManifest.xml` |

### 1.2 Detection enable (P1 trigger)

| # | item | detail | confidence |
|---|---|---|---|
| 7 | binder entry | `WakewordToggleService` (exported, no permission attr) → `IWakewordToggleService`; `enableRecognition(clientName, Bundle, ParcelFileDescriptor)` | confirmed (`engineservice/WakewordToggleService.java:119`) |
| 8 | other binder entries | `disableRecognition` (`i`), `isWakewordRecognitionEnabled` (`o`); `WakewordSettingsService`, `SoundModelParserService`, `RegenerateSoundModelService`, `UtteranceProviderService`, `QDumpService`, `LibUpdateService`, `TTSService`, `InputProviderService`, `LLMRecognitionService` | confirmed (manifest) |
| 9 | actual trigger of a detection | **sound-trigger HAL**, not the app: `SoundTriggerModule.startRecognition(handle, RecognitionConfig)`; the DSP/audioserver runs the keyword model | confirmed for the call (`E.o.c`, `N.l.c`); DSP internals unknown |
| 10 | audio source of a detection | `AudioRecord` with `AudioAttributes.USAGE_MEDIA(1)` / `CONTENT_TYPE_SPEECH(1)` / `setInternalCapturePreset(1999)`, `ENCODING_PCM_16BIT`, 16 000 Hz, `CHANNEL_IN_MONO` | preset code confirmed (`I.a.java:11-15`); `1999 == AUDIO_SOURCE_HOTWORD` inferred (platform constant) |
| 11 | session handle | `captureSession` int from the STHAL event is passed as `AudioRecord` `audioSessionId` (`I.a.c(bufferSize, mSessionNum)`) | confirmed (`OneShotAudioRecordAction.java:26`) |

### 1.3 Enrollment trigger (P2)

| # | item | detail | confidence |
|---|---|---|---|
| 12 | binder entry | `EnrollmentService` (exported) → `IEnrollmentService`: `startUserVoiceEnrollment`, `startUtteranceTraining`, `stopUtteranceTraining`, `commitUserVoiceEnrollment`, `cancelUserVoiceEnrollment`, `cancelUtteranceTraining`, `finishUserVoiceEnrollment`, `transferRecording`, `getUtterancesInfo`, `isGeneralUVEnrolled`, `removeGeneralUV` | confirmed (`EnrollmentService.java`) |
| 13 | auth | `Binder.getCallingUid()` → `getNameForUid` is the session key; `RECORD_AUDIO` must be granted to the caller | confirmed (`EnrollmentService.s()`, `z()`) |

### 1.4 Lifecycle

| phase | P1 | P2 | confidence |
|---|---|---|---|
| init | per-model `SoundTriggerSession` created on first `turnOnDetection`, kept in a static `ConcurrentHashMap` | per-caller session object in `EnrollmentService.f524b` | confirmed |
| per-event / per-frame | one `AudioRecord` + one `AudioStreamProvider` per keyphrase event, torn down at `stopRecording` | one `NormalAudioRecordAction` per utterance | confirmed |
| teardown | `n.z.p()` / `n.z.q()`: cancel algorithms, release `AudioRecord`, close `AudioSink` sockets, unbind client, `stopForeground` | `AbstractEnrollmentSession.t()`: stop recorder, clear utterance map, close pipes | confirmed |

---

## 2. Transport and format

### 2.1 Audio contract (both pipelines)

| parameter | value | confidence | evidence |
|---|---|---|---|
| sample rate | 16 000 Hz | confirmed | `I.a.b()`, `NormalAudioRecordAction` ctor, `AudioMetaData.audioSampleRate=16000` in `n.L.h` |
| channels | 1 (`CHANNEL_IN_MONO` = 16) | confirmed | `I.a.b()` `setChannelMask(16)`; `AudioMetaData.audioChannels=16` |
| encoding | `ENCODING_PCM_16BIT` (2), little endian | confirmed | `I.a.b()`, `NormalAudioRecordAction.b()` `ByteOrder.LITTLE_ENDIAN` |
| byte rate | 32 000 B/s (`Constants.j() == 32` bytes/ms) | confirmed | `j.i.java` |
| `AudioRecord` buffer | 64 000 B = 2 s (`Constants.a() = (32000*16)/8`) | confirmed | `OneShotAudioRecordAction.mAudioRecordBufferSize` |
| capture read size (P1) | 3 840 B = 1 920 samples = **120 ms** (`Constants.d()`) | confirmed | `OneShotAudioRecordAction.mReadBufferSize`, `j.i.d()` |
| capture read size (P2) | 1 280 B = 640 samples = **40 ms** (`Constants.e()`, `Constants.h()`) | confirmed | `NormalAudioRecordAction.d()` |
| stream buffer | `(HistoryBufferDuration/1000 + 8) * 16000 * 2` = **320 000 B = 10 s** at the shipped `HistoryBufferDuration = 2000` | confirmed | `j.h` ctor → `i.c(ms)`, `j.i.c()`; `qvaconfig_v4.xml` `HistoryBufferDuration` |
| pre-roll | `PreRollDuration = 1000 ms` (config; the STHAL event already carries `beginIndex`) | confirmed (config) / inferred (use) | `qvaconfig_v4.xml`; `RecognitionEvent.d()` |
| client audio stream | `ParcelFileDescriptor.createReliableSocketPair()`, `[0]` = read side handed out, `[1]` = write side kept by the capture task | confirmed | `j.a.java` |
| index unit | STHAL/AI indices are **10 ms frames**, converted to bytes with `index * 10 * 32` | confirmed | `h.b.f()` / `h.b.g()` |

### 2.2 Frame / hop at the native boundary

| item | value | confidence | evidence |
|---|---|---|---|
| Java → JNI frame | one `ShortBuffer` view over the reader's `byte[]` slice (`FrameFlag` HEAD/MIDDLE/TAIL/ONE_SHOT) | confirmed | `Algorithm.processAlgorithm`, `j.f` |
| analysis frame | 480 samples (30 ms), hop 160 (10 ms) | confirmed | `native/disasm/udk7_feature_process.s` (counter `0x1e0`, `memmove 0x280`, `sub #0xa0`) |
| FFT | 512-point real, 256 magnitudes | confirmed | `native/disasm/realfft.s`, `udk7_q_fft.s` |
| features | 80 sparse triangular mel filters, no log | confirmed | `models/frontend/mel_matrix_80x256.npy`, `udk7_feature_mel.s` |
| reader chunk (PDK/1.5 stage) | 3 840 B (120 ms) per `read()` (`PDKReader.mReadSize = 32*120`) | confirmed | `j.e.java:11` |
| VAD-gated 2nd stage | first read = pre-roll→trigger, then 120 ms chunks clipped to `DNNVADAction` command end | confirmed | `j.g.java` (`TIUVReader`) |

### 2.3 Threads / queues

| thread | priority | work | confidence |
|---|---|---|---|
| `HandlerThread("algorithm", -16)` | `THREAD_PRIORITY_URGENT_AUDIO` | 1.5-stage and 2nd-stage algorithm runs | confirmed (`n.L.java:72`) |
| `HandlerThread("ThreadManager", -16)` | urgent audio | start/stop capture, session teardown | confirmed (`n.L.java:75`) |
| `HandlerThread("FsStateCallback")` | default | 1st-stage callback fan-out when recognition is lost | confirmed (`n.D.java:36`) |
| `ForkJoinPool` common pool | default | `OneShotAudioRecordAction extends RecursiveAction` (capture), `OneVoiceAlgorithmTask extends RecursiveTask` (stage fan-out) | confirmed |
| main `Handler` | — | completion callbacks, notify/stop paths | confirmed |
| `HandlerThread("g")` (model store) | default | `u.g` persistence / reload | confirmed (`u.g.java`) |

Queue depth 1: `n.L` uses `AtomicBoolean` gates (`mResponsesReady`, `mMsResponsesReady`) plus `ReentrantLock` + `Condition.await(10, SECONDS)`; no unbounded queue. Frame drop policy: `AudioStreamProvider.l()` silently drops a chunk once `mAudioBytes.length - mCurrentIndex <= length` (buffer full) — confirmed in `j.h.l()`.

---

## 3. Preprocessing

Order for P1 (confirmed unless noted):

1. **STHAL event** carries the opaque keyword payload (`N.c.c() = KeyphraseRecognitionEvent.data`) which is parsed by `x.d` into start/end indices and confidence (`RecognitionEvent.g/f/i`).
2. **PCM acquisition** — `AudioRecord.read(byte[3840])`, no resampling, no gain.
3. **Stream assembly** — bytes appended to `AudioStreamProvider`; readers expose `[startIndex-preTolerance, endIndex+postTolerance]`.
4. **1.5-stage slice** — `OneShotReader`: `mReadStart = beginIndex - preTolerance`, `mFinishEnd = endIndex + postTolerance`; tolerances from `MiddleStageKW.preTolerance/postTolerance` (400/400 ms in `qvaconfig_v4.xml`, defaults 300/440).
5. **Native front-end** — ×1/32768 (`0x38000000`), Hann(400) in a 480 window, 512-pt FFT, magnitude, 80 mel dot products.
6. **Network normalisation** — log/CMVN applied *inside* the EAI graph or not at all: **unknown** (caller emits linear mel; the EAI2 parser was not decoded — see report §9.6).

P2 preprocessing:

1. `NormalAudioRecordAction` reads 1 280 B (40 ms) and converts to `short[]`.
2. Gating is either `EPD.processEPD(ShortBuffer)` (default, `DetectionType.EPD`) or `DNNVAD.processVAD(ShortBuffer)`; the loop breaks when the gate reports end-of-phrase.
3. Utterance audio is accumulated per utterance id (`TrainingAudioRecorder.f361c : HashMap<String, ArrayList<ShortBuffer>>`) and concatenated on commit (`k(ArrayList)` → `ShortBuffer` reduce).
4. `SoundModelCodec.verifyUserRecording(model, utteranceId, ShortBuffer, postRollPadding, ConfidenceData)` runs the SML quality check; `getSizeWhenCreateUserModel` sizes the output.

---

## 4. Model invocation

| item | detail | confidence |
|---|---|---|
| engine (keyword/UV) | Qualcomm SML via `libqvasml.so`, executed through `libeai_floating.so` (EAI2 interpreter) | confirmed |
| Java surface | `com.quicinc.voice.activation.algorithm.Algorithm`: `native int init(ByteBuffer soundModel, int size, int algorithmType)`; `native int start(Bundle thresholds)`; `native int process(ShortBuffer frame, Bundle in, Bundle out, int flag)`; `native int stop()/deinit()/cancel()` | confirmed (`Algorithm.java`) |
| JNI symbols | `Java_com_quicinc_voice_activation_algorithm_Algorithm_{init@0x1481c,start@0x14af4,process@0x15230,stop@0x15880,deinit@0x158e0,cancel@0x15954}` | confirmed (`evidence/jni_symbols_libqvajni.txt`) |
| internal native calls | `Algorithm::keyword_detection_process(short*, nsamples, result*, flag)` @`0x1293c`, `Algorithm::user_verification_process(short*, …, params, result, flag)` @`0x12c60` | confirmed (`native/disasm/`) |
| SML binding | `dlopen`+`dlsym` of `smlInit/smlSet/smlGet/smlProcess`, `getSoundModelHeader`, `createUserKeywordModel*` | confirmed (report §2) |
| model bytes handed to SML | the `.uim` file read wholesale into a direct `ByteBuffer`; the same buffer is the `SoundTrigger.KeyphraseSoundModel.data` for the 1st stage | confirmed (`WakewordToggleService.r`, `p.e`) |
| VAD engine | `libdnnvad_jni` → `libqvavad` (`capi_v2`), model `assets/speech_float.eai` | confirmed (report §5) |
| enrollment engine | same `libqvajni` `SoundModelCodec.*` natives (20 exported `Java_…SoundModelCodec_*` entries) | confirmed |
| batching | none; one frame per `process()` call, one model per session | confirmed |
| delegates / threads | CPU (arm64) for SML; DSP CAPI_v2 modules (`libqvapdk*.so`) when the HAL selects the DSP backend | CPU confirmed; DSP selection inferred |
| cadence | 1 frame / 10 ms of audio per algorithm, driven by the reader loop | confirmed |

---

## 5. Output postprocessing

### 5.1 Per-stage results

`Algorithm.process` writes a `Bundle` with keys from
`AlgorithmProcessResult$AlgorithmResultKey` (confirmed,
`sources/classes/com.quicinc.voice.activation.algorithm.AlgorithmProcessResult$AlgorithmResultKey.java`):

| key | type | meaning |
|---|---|---|
| `detected` | int (0/1) | stage fired |
| `detected_index` | int | index of the firing keyword (multi-KW models) |
| `confidence_score` | int | keyword score, 0…100 scale (thresholds are 26…74) |
| `keyword_start_index` / `keyword_end_index` | int | 10 ms frame indices inside the slice |
| `uv_score` | int | user-verification score (`SML_ID_SVA_S_STAGE_USER` results only) |

`p007h.b` (`AlgorithmProcessResult`) wraps `(AlgorithmType, preToleranceBytes, Bundle)`
and chains stages via `m()`; `d(i)` / `c(i,i)` / `f(i,i)` / `g(i)` translate a
stage-local index back into the parent slice with `index*10*32` bytes.

### 5.2 Stage chaining and thresholds

| step | where | threshold key | shipped value | confidence |
|---|---|---|---|---|
| DNN VAD gate | `DNNVADAction` (`g.l()` reader) | `nnvad.threshold` (Q20) + `nnvad.len.hangover` | 891289 (=0.85×2²⁰), 70 frames | confirmed |
| 1st stage accept | inside SML (`smlProcess`) | `FirstStageKW.threshold` / `.thresholds` | 26 (per model), `55,61,36,35,33` (MFCN) | confirmed |
| 1.5 stage accept | inside SML | `MiddleStageKW.thresholds` | `50,52,49,37,35` | confirmed |
| keyword start/end refine | `n.L.h` + `h.b` | — | `bVar.g(iD)`/`bVar.f(...)` on the chained results | confirmed |
| 2nd stage UV accept | inside SML | `SecondStageUV.threshold` (+`.kwcmd`) | 55.0/45.0 (`general_UV_024`), 13.0/19.0 (`general_UV_015`) | confirmed |
| bypass | `BigSoundModel.getBypass()` | `SecondStage.bypass` | false | confirmed |

### 5.3 Result structs consumed by Java

`n.L.h()` builds the 1.5-stage part of the bundle (confirmed, `n.L.java:143`):

```
"1.5Stage.success"            boolean
"1.5Stage.keywordStartIndex"  int   (bytes in the stream)
"1.5Stage.keywordEndIndex"    int
"1.5Stage.detectedIndex"      int   (keyword id, or STHAL keywordID for multi-KW)
"1.5Stage.startTimestamp"     long  (System.currentTimeMillis before smlProcess)
"1.5Stage.finishTimestamp"    long
"AudioMetaData.audioSampleBits"=16, ".audioSampleRate"=16000, ".audioFormat"=2, ".audioChannels"=16
"SoundModel.fileName"         String (2nd-stage model file)
```

`n.L.i()` then rewrites `"algorithm.success"`, `"AudioMetaData.keywordStartIndex"`,
`"AudioMetaData.keywordEndIndex"`; the 2nd stage adds
`"SecondStage.startTimestamp"` / `"SecondStage.finishTimestamp"`.
`n.z.i` adds `"AudioMetaData.startRecordingTimestamp"` and, on stop,
`"AudioMetaData.stopRecordingTimestamp"` (all `System.currentTimeMillis()`, i.e.
wall-clock `CLOCK_REALTIME`, **not** an audio-clock timestamp — confirmed).

---

## 6. Consumer / action

| step | mechanism | confidence |
|---|---|---|
| client registration | `WakewordToggleService.enableRecognition(clientName, bundle, fd)`; `ClientInfo.packageName` + `ClientInfo.className` (or `ClientInfo.forLLMClient`) | confirmed (`n.i.java`, `k.a.java`) |
| keyphrase → client mapping | `ClientGroupManager.e(clientName)` → `ClientGroup`; for multi-KW `a.f(detectedIndex)`; a `SoundModelKey(UUID)` = `nameUUIDFromBytes(group-fileName-userId-locale)` | confirmed (`k.b.h`, `v.j.a`) |
| bind | `bindService(new Intent().setComponent(new ComponentName(pkg, serviceClass)), conn, 0x100001)` = `BIND_AUTO_CREATE|BIND_IMPORTANT`; extra `"isOneVoice"=true` when the client is this package (LLM client) | confirmed (`n.z.b`) |
| audio delivery | client binds `IUtteranceProviderService` and calls `startUtterance(…)`; QVA answers `IUtteranceReceiverCallback.onUtteranceReceived(ParcelFileDescriptor, Bundle)`; failure ⇒ `onUtteranceVerificationFailure(fd, Bundle)` | confirmed (`n.z.k/m/l`, `UtteranceProviderService.i/j`) |
| audio payload | the `ParcelFileDescriptor` is the `AudioSink` socket (`j.a`), i.e. raw 16 kHz/mono/PCM16, plus the same `Bundle` metadata | confirmed |
| no broadcast / no intent | detection is delivered only over binder; there is no detection broadcast in the APK | confirmed (no such `sendBroadcast` on the path; the only receiver is boot/package) |
| LLM/ASR client | `ClientInfo.forLLMClient=true` ⇒ `className = LLMRecognitionService`, package = own package | confirmed (`n.i.java:47`) |

---

## 7. Failure and fallback

| condition | behaviour | confidence |
|---|---|---|
| sound model missing for the event's client group | `n.z`: `"cannot find client group"` → `p()` (release, no delivery); `n.V`: `"sound model is not found"` → return | confirmed |
| 2nd-stage sound model absent | `n.D.q`: `"model unsufficient"` → `AlgorithmResponseStatus.ALGORITHM_NO_MODEL` | confirmed |
| 1st stage turns on, 2nd stage fails | `n.D.q/r` rolls the 1st-stage session back (`D(uuid, config)`) so detection is not left half-enabled | confirmed |
| client package uninstalled | `f(pkg)` false ⇒ treated as "client invalid", `d(group)` turns the model off | confirmed |
| `RECORD_AUDIO` not granted to client | `"granted=false, client=…"` ⇒ abort + turn off | confirmed |
| 1.5 stage does not answer | `Condition.await(10, SECONDS)` then proceed with whatever is in the bundle; `1.5Stage.success` false ⇒ `p()` | confirmed (`n.L.m`) |
| recognition abort from HAL | `p.c.b`/`E.f.b`: session → `RECOGNITION_PAUSED`, listener `a(uuid)` (log only) | confirmed |
| recognition failure from HAL | `p.c.d`/`E.f.d`: `x(QResponseStatus.ERROR)` on all sessions + `j()` (clear state) | confirmed |
| STHAL service died | `E.m.onServiceDied` → `E.n.d()` (detach) → `c()` re-attach, `onResourcesAvailable` re-arms | confirmed |
| another app holds the mic | `NormalAudioRecordAction.c()`: voice-call mode (2/3) or a foreign `AudioRecordingConfiguration` ⇒ abort (`"Another audio record session found. Aborting."`) | confirmed |
| dump not requested | `G.g.a()/b()` return `G.h` `DummyAudioStreamSaver` unless `persist.qva.dump == "1"` | confirmed |
| AudioRecord init failure | `"AudioRecord state error, cancelled"`, stream cancelled, listener notified | confirmed |
| general UV not enrolled | `EnrollmentService.v()/x()` → `isGeneralUVEnrolled` / `removeGeneralUV`; the platform UV base model is used instead | confirmed |
| model file missing on disk | `OneVoiceModelFileManager.g()` copies it from `assets/` on demand (`1st_stage/`, `1.5_stage/`, `ubm/`) | confirmed |
| device not in the OEM list | `ForegroundService.c()` false ⇒ `ACTION_STOP_FOREGROUND` (no foreground service is kept) | confirmed |
| no AI model after reboot | `n.Y.a()` stops the service and kills the process | confirmed |

---

## 8. Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| detection is triggered by the STHAL DSP, not by continuous `AudioRecord` | confirmed | `E.o.c`, `N.l.c`, no always-on `AudioRecord` start anywhere | — |
| the app starts `AudioRecord` only *after* the keyphrase event (to fetch the utterance) | confirmed | `n.z.i` → `audiorecord.b.w()` → `OneShotAudioRecordAction` | — |
| capture preset 1999 = `AUDIO_SOURCE_HOTWORD` | inferred | `I.a.a()` `setInternalCapturePreset(1999)`; 1999 is the platform `AUDIO_SOURCE_HOTWORD` value | vendor `audio.h` / `AudioAttributes` docs |
| 1st-stage and 1.5-stage thresholds are applied inside SML | inferred | thresholds arrive in the `start(Bundle)` opaque parameter bundle; no Java comparison exists | SML pseudocode for `smlSet`/`smlProcess` |
| screen-off survival is the DSP + wake lock + doze whitelist | confirmed for the three mechanisms | `I.b.a(..., 300000, "QVA-HOTWORD")`, `d.g.b()` (500 ms `PARTIAL_WAKE_LOCK`), `ForegroundService` (SDK 31/32 + OEM list only) | — |
| the `.uim` buffers are passed to `loadSoundModel` unchanged | inferred | `WakewordToggleService.r()` passes model-file paths into `p.e`, which reads whole files into `ByteBuffer` | `p.e`/`u.g` full read |
| EAI2 layer table / tensor shapes | unknown | report §9.1 | decompile `parse_eai_layers` sub-parsers |
| which CAPI_v2 DSP module runs a given SOC | unknown | only the `dlopen` name table is visible | audio HAL configuration on device |

---

## 9. Reimplementation mapping

| pipeline stage | JAX/numpy module in `reimpl/pipeline.py` | input → output | status |
|---|---|---|---|
| PCM decode + framing (16 kHz, 480/160) | `pc16_to_float`, `frames_of` | `int16[N]` → `f32[F, 480]` | **REAL** |
| window + FFT + magnitude + mel | `logmel` / `logmel_jax` (`hann_480.npy`, sparse `mel_matrix_80x256.npy`) | `int16[N]` → `f32[F, 80]` | **REAL** coefficients; the `log()` is **INFERRED** (native path emits linear mel) |
| feature normalisation | inline in `detect` (per-utterance mean/var) | `[F,80]` → `[F,80]` | INFERRED (not recoverable from the caller) |
| DNN VAD net | `init_vad_params` / `vad_apply` | `[F,80]` → `[F]` logits | ARCHITECTURE_ONLY |
| hangover / Q20 threshold / lookahead | `vad_gate(prob, 891289, 70, 0)` | `[F]` → `bool[F]` | **REAL** (qvaconfig values) |
| 1st stage keyword net | `init_keyword_params` / `keyword_apply` (5 outputs) | `[F,80]` → `[F,5]` | ARCHITECTURE_ONLY |
| 1.5 stage keyword net | same `keyword_apply` with the 1.5-stage params | `[F,80]` → `[F,5]` | ARCHITECTURE_ONLY |
| 2nd stage UV scorer | **not implemented** — `detect` stops at the 1.5-stage decision | — | ARCHITECTURE_ONLY / absent |
| threshold/tolerance decision layer | `detect` (`FirstStageKW.threshold`, `MiddleStageKW.thresholds`, `DEFAULTS`) | scores → accept + start/end | **REAL** (qvaconfig values) |
| enrollment codec (`verifyUserRecording`, `createUserModel`) | — | not reimplemented | ARCHITECTURE_ONLY: native SML, no weights in the APK |
| client delivery (PFD + Bundle) | — | Android binder, out of scope | n/a |

ARCHITECTURE_ONLY reasons: the EAI2 container's layer table, tensor shapes and
weight offsets are not decoded (report §9.1), and the SML enrollment/UV covectors
live inside `libqvasml.so` + `.uim` bodies whose per-model tables were not
decompiled (report §9.2). No DSP internals are claimed anywhere in this file.
