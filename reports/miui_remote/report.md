# Mi Remote (`XMRemoteController.apk`) — on-device/cloud voice pipeline

Slug: `miui_remote`  ·  Package: `com.duokan.phone.remotecontroller`  ·  Version: 6.6.6R (6352)
APK: `product/data-app/XMRemoteController/XMRemoteController.apk`
SHA-256: `6a955dfc5ef67f1b7cd4094108ec019d419c9e9541546d3e249fdde9354eb9a6` (35,905,205 B)
ABIs: `arm64-v8a`, `armeabi-v7a`  ·  minSdk 21 / targetSdk 33  ·  Main: `…HoriWidgetMainActivityV2`

All paths below are relative to `reports/miui_remote/`; ELF addresses are file virtual
addresses of the arm64-v8a libraries extracted under `inventory/libs/lib/arm64-v8a/`.

---

## 1. Executive summary

Mi Remote ships **two independent AI paths**:

| Path | What runs where | Evidence |
|---|---|---|
| **MiBrain voice control** ("语音控制", `VoiceControlActivity`) | mic capture, **local VAD** and Opus encode on device; **ASR + NLU + TTS in Xiaomi's cloud** (`wss://speech.ai.xiaomi.com/speech/v1.0/access`); TTS MP3 decoded locally | `cg.l`, `com.xiaomi.ai.c`, `libmibrainsdk.so`, `libmibraindec.so` |
| **External XiaoAi / MVS** | app registers as an MVS client of `com.miui.voiceassist` (AIDL + broadcasts); the *system* assistant does ASR/NLU and calls back | `com.miui.voiceassist.mvs.*`, `MvsClientReceiver` in manifest |

There is **no on-device ASR / wake-word model** in this APK. The only neural network in
the package is the **VAD** (`vad_2.1.0_0`) embedded inside `libmibrainvad2.so`; it is
shipped but **not referenced by this APK's Java code** — the SDK's own classic VAD
(`vadInit`/`vadCheckBegin` inside `libmibrainsdk.so`) is the one actually used when
`AsrRequest.setUseVad(true)` + `VadMode.VAD_MODE_LOCAL` (both defaults, `AsrRequest.java:35-40`).

---

## 2. Feature map

| Feature | Entry points | Pipeline | Models | Conf |
|---|---|---|---|---|
| Voice control of IR/BLE devices (TV, STB, AC, fan, light, projector, DVD, amp, purifier, heater, camera) | `com.xiaomi.mitv.phone.remotecontroller.voice.VoiceControlReceiver` (manifest, action `…voice_control_action`), `cg.k` (`VoiceControlActivity` fragment), `com.xiaomi.mitv.phone.remotecontroller.common.ui.SpeechVolumeView` | `cg.l` → `SpeechEngine.startIntegrally` → `MibrainRequest` → `AudioRecord(16 kHz)` → native `miBrainSdk*` (local VAD + Opus) → WebSocket ASR/NLP → JSON intent (`domain`/`entity`/`action`) → `cg.e` device handler → IR/BLE command; TTS answer played back | VAD `vad_2.1.0_0` (shipped, unused); cloud ASR/NLU/TTS | confirmed |
| Voice search for EPG / TV content | UI strings `Voice search`, `Tap the microphone to use voice controls`, EPG activities | same MiBrain session; NLP context carries `all_devices`; answer text displayed by `cg.k` | cloud | inferred |
| Voiceprint (speaker ID) | `com.xiaomi.ai.VoiceprintRegister/Recognize/Query/DeleteRequest`, `MIBRAINSDK_CMD_VOR*` | cloud `/speech/v1.0/vor` | cloud | confirmed |
| TV voice remote support | `mitv.hardware.voicerc.compatible` / `mitv.hardware.voicerc.default` strings, `BluetoothVoiceManager`, `ExtraBGVoiceControl`, `VoiceControlPopup` | system feature flags; BLE HID remote path in `wf.*` | — | inferred |
| XiaoAi third-party integration (MVS) | `com.miui.voiceassist.mvs.client.MvsClientManager`, `MvsClientReceiver` (`ACTION_RESGISTER_MVS`, permission `com.miui.voiceassist.MVS_BROADCAST`) | AIDL `IMvsTransition`, cards (`MvsCard`), `sendRequest/sendResult` | cloud (XiaoAi) | confirmed |
| iFly voice engine prompt | resource strings `Tap to install iFly voice engine`, `Tap "OK" to download voice engine` | **no code reference** to any iFly package in any dex string table; prompt only | — | inferred |

---

## 3. Java state machine (recovered)

### 3.1 App wrapper — `cg.l` = `VoiceControlManager`
Source: `sources/classes/cg.l.java` (jadx single-class, `classes3.dex`).

* Singleton `l()` → `k()`:
  * `SpeechEngine.createEngine(ctx, ENGINE_MI_AI=1, "2882303761517406041", "5141740612041")`
    (app id / api key pair at `cg.l.java:52,55`; the unused production pair
    `2882303761517406027` / `5311740683027` is at `:46,49`).
  * `setEnv(0, …)` when `vd.d.v()` (CN production) else `setEnv(3, …)` (SGP) plus
    `setUserAgent("MiRemote; Sensy/1.56 Build/<versionCode> Channel/Release OS/Android")`.
  * registers `AsrListener`, `NlpListener`, `TtsListener`.
* `o()` — start of one turn:
  * builds `{"all_devices":[{id,name,brand,type,selectedSTB?}]}` from `yd.k.g` (bound devices),
  * `AsrRequest.setUseVad(true)`, `NlpRequest.setContext("mi_remote", devicesJson)`,
  * `SpeechEngine.startIntegrally(needAsr().setAsrRequest(…).needNlp().setNlpRequest(…))`.
* `p(activity, view)` — shows the popup (`ud.d`) and starts the turn; `q()` — `endSpeech()`.
* ASR callbacks: `onPartialResults` → UI, `onResults` → UI, `onError` → localized
  error (`voice_control_err_permission` / `voice_control_err_network` / `…_err_common`).
* **NLU → command** (`b.onResult`, `cg.l.java:190-300`):
  * parses `intention.domain`, `intention.entity`, `intention.action`;
  * `domain == "tvChannelControl"` → `cg.f` handler (requires a bound STB);
  * else `entity` → device-type handler in `f12217c` (`cg.a`, `cg.g`, `cg.f`, `cg.b`);
  * `device_list` (comma separated ids) or the handler's default device selects the
    `ee.j` device; missing device → Chinese error text spoken via TTS;
  * result `d{ok, speakText}` → `f.b()` (success) or `f.g()` (failure).
* Device-type → Chinese label map: `aircondition, camera, heater, projector, tv, amp,
  dvd, fan, stb, light, purifier` (`cg.l.java:452-490`).
* TTS completion (`c.onPlayFinish`) → `f.i()` (UI back to idle).

### 3.2 SDK engine — `com.xiaomi.ai.c`
Source: `sources/classes/com.xiaomi.ai.c.java`.

* Microphone: `AudioRecord.getMinBufferSize(16000, 16, 2)` and
  `new AudioRecord(1 /*MIC*/, 16000, 16, 2, minBufferSize)`; loop
  `read(short[])` → `byte[]` → `MibrainRequest.addData` (`c.java:179-215`).
* Request parameters (`c.java:1543-1590`): 1 channel, 16 bit, **16000 Hz**,
  `setMaxEmptyAudioTime(4|8)`, `setCodecMode(...)`, `setOpusBitRates/OpusFrameSize`,
  `setUseInternalVadFlag(AsrRequest.useVad)`,
  `setVadTypeLocal(VAD_MODE_LOCAL|VAD_MODE_CLOUD)`.
* TTS request: `setTTSCodec("mp3")` (`c.java:1567`).

### 3.3 JNI-facing request — `com.xiaomi.ai.mibrain.MibrainRequest`
`startRequest()` → `native newContext(params,0,0)` + `startRequest(ctx)`; `stopRequest()`
with `MI_STOP_FLAG_SYNC_STOP=1 / ASYNC_STOP=2`; audio via `addData(ctx, byte[], len, flag)`;
results come back through `MibrainRequestListener` (`onAsrResult`, `onNlpResult`,
`onTtsResult`, `onError`). Command ids (`MibrainNativeRequest.java:7-38`):
`ASR=1, ASR_NLP=0, ASR_NLP_TTS=2, TTS=3, NLP=4, NLP_TTS=5, VOR_REG=6, VOR=7, VOR_DEL=8,
VOR_QUERY=9, EVENT=10, EVENT_TTS=11`.

### 3.4 End-to-end turn (confirmed/observed pieces)

```
mic (AudioRecord 16k/16bit/mono)
  → com.xiaomi.ai.c loop → MibrainRequest.addData
  → libmibrainjni: Java_..._MibrainNativeRequest_addData → miBrainSdk* (libmibrainsdk)
       ├─ vadCheckBegin()/vadCheckEnd()  (LOCAL VAD, libmibrainsdk 0x16fdc/0x1711c)
       ├─ Opus encode (codecMode)         (libmibrainsdk, ":CodecMode:%d,isLast:%d inputsize:%d")
       └─ protobuf over wss TLS          (mbedTLS + cwebsocket, "message convert to protobuf failed")
  ← RESULT_ASR_PARTIAL / RESULT_ASR_FINAL / RESULT_NLP / RESULT_TTS_BEGIN|URL|END
  → SpeechEngine mResultParser (q) → SpeechResult{query, intention, content, toSpeak}
  → cg.l NLU routing (domain/entity/action) → cg.<device> handler → IR / BLE / MiLink
  → TTS mp3 → libmibraindec (libmad) → AudioTrack
```

---

## 4. Native stack

All libs are ELF64 AArch64, NDK r16b (Android SDK 21), stripped (`.dynsym` only).

| Library | Size | Role | Conf |
|---|---:|---|---|
| `libmibrainjni.so` | 46,792 | JNI glue, 36 exports: `MibrainOauthManager` (10), `MibrainNativeRequest` (19), `MibrainUtils` (2), `MibrainVad` (5), links `libmibrainsdk.so` | confirmed |
| `libmibrainsdk.so` | 1,022,800 | C SDK: WebSocket client + mbedTLS + protobuf + **libopus** + classic VAD (`vadInit/vadCheckBegin/vadCheckEnd/vadUninit`) + OAuth | confirmed |
| `libmibrainvad2.so` | 1,509,480 | **CornVad** engine + **Soil** NN runtime + embedded model `mdata_vad` (1,132,916 B) | confirmed |
| `libmibraindec.so` | 136,912 | MP3 decoder = **libmad 0.15.1b** (`"MPEG Audio Decoder 0.15.1 (beta)"`), JNI `MibrainMp3Decoder` | confirmed |
| `libairkanopus-jni.so` | 395,096 | Opus for AirKan screen-mirroring (`com.mitv.airkan.opus.Opus`), not voice | confirmed |

### 4.1 JNI API (exact symbols)

`libmibrainjni.so` (36):
```
MibrainOauthManager: newInstance init getAccessToken releaseInstance setAnonymousAuth
                     updateAnonymousArgs genAnonymousAuthorization unInit setTimeout setEnv setPt
MibrainNativeRequest: newContext deleteContext startRequest addData addNlpData addDataRaw
                      stopRequest setDebugLogLevel setLogHookEnable getResourceDetails
                      getAnonymousAuthorization newWakeUpRequestParams releaseWakeUpRequestParams
                      sendWakeUpRequest startUploadRequest addUploadData endUploadData
MibrainUtils: base64 sha1
MibrainVad: newVad releaseVad vadInit vadCheckBegin vadCheckEnd vadUnInit
```
`libmibrainvad2.so` (5): `MibrainVad2.newVad(int,int)`, `.createTask(long)`,
`.hasVoice(long,long,byte[],int,int[])`, `.stopTask(long,long,int[])`, `.releaseVad(long)`.

`newVad` (0xdd64) → `CornVad::CornEngine::Init("null",1)` (string at `.rodata` 0x49c30)
→ `CornEngineInit` → `CornLoadModelBin(NULL, engine)` which memcpy's `mdata_vad` and
`SoilInit(soil, blob, 7 /*memory*/, NULL)`; `SetVoiceSilLength(voice,sil)` sets
min-voice/min-sil frame counts.

`Java_..._hasVoice` (0xddec) reads the JAVA `int[6]` result array:
`[0]=retVal, [1]=hasVoice, [2]=packNumVoice, [3]=packNumBeg, [4]=packNumEnd,
[5]=minVoiceLength` (`MibrainVad2.java`, `DecodeResult`).

`MibrainVad` (v1) thunks into `libmibrainsdk.so`'s exported `vadInit` (0x16e20),
`vadCheckBegin` (0x16fdc), `vadCheckEnd` (0x1711c), `vadUninit` (0x1722c).
`vadInit` allocates 0x1060 bytes and initialises with `{16000,16000,1}`;
`vadCheckBegin(handle, pcm16, nsamples/2, use_vad)` calls the internal detector at
0x41de0 (log/exp arithmetic → adaptive statistical VAD, exact rule not fully reversed —
`unknown`).

### 4.2 `libmibrainsdk.so` — network + protocol (cloud contract)

| Kind | String (`.rodata`) |
|---|---|
| ASR/NLP/TTS endpoint | `wss://speech.ai.xiaomi.com/speech/v1.0/access` (0xb88c8), `wss://speech-sgp.ai.xiaomi.com/…/access` (0xb88f8), `wss://speech-preview.ai.xiaomi.com/…/access` (0xb87c0), `ws://speech-staging.ai.xiaomi.com/…/access` (0xb8930) |
| Voiceprint | `wss://speech.ai.xiaomi.com/speech/v1.0/vor` (0xb8860) + sgp/preview/staging |
| Debug upload | `…/speech/v1.0/dump` (0xb5af0/b5b28/b5b60) |
| Resource locator | `https://api.ai.xiaomi.com/cp_resource_locator/c/v1.0/provider/%s/ids/%s` (0xb6030) |
| Track | `https://api.ai.xiaomi.com/track/v1`, `/track/authmiot`, `/track/perf` |
| OAuth | `https://account.ai.xiaomi.com` (0xbfd38) + `/oauth2/auth/token?…grant_type=…` (0xc0200, 0xc0318), anonymous `%s/anonymous/app/auth/token` (0xbfde0) |
| Auth headers | `DO-TOKEN-V1 app_id:%s,scope_data:%s,access_token:%s`, `MIOT-TOKEN-V1`, `TP-TOKEN-V1`, `AO-TOKEN-V1`, `AA-TOKEN-V1 client_id:%s,api_key:%s,…` |
| Message types | `AI_CMD_ASR` (0xb8220), `AI_CMD_PRE_ASR` (0xb8230), `AI_CMD_NLP` (0xb82d0), `AI_CMD_TTS` (0xb8330), plus `RESULT_ASR_PARTIAL|FINAL`, `RESULT_NLP`, `RESULT_TTS_BEGIN|URL|END`, `WAKEUP`/`WAKEUP_AUDIO` |
| Codec | internal **Opus** (`Opus-Frame-Size`, `libopus unknown-fixed`, 640/1280-sample frames, 32/64 kbps) or raw PCM (`USE_ENCODER_PCM=1`) |
| Framing | protobuf (`message convert to protobuf failed`), WebSocket `HTTP/1.1 101` upgrade, `Sec-WebSocket-Protocol` |
| Wakeup-data upload | `WAKEUP_AUDIO`, `wakeup_word`, `wakeup_vendor`, `wakeup_type`, max 60 s (`miBrainDebugAddWakeupData MAX size is 60*1000`) |

**Stub contract** (for a cloud replacement): accept a WebSocket at `/speech/v1.0/access`,
authenticate from the `*-TOKEN-V1` header, then exchange protobuf messages tagged with
the `AI_CMD_*` ids, sending back `RESULT_*` messages; audio payload is Opus 16 kHz mono
(16-bit PCM accepted in PCM mode); reply TTS as MP3 binary or a URL.

---

## 5. The on-device model: `vad_2.1.0_0`

### 5.1 Container
`libmibrainvad2.so` embeds one blob through the exported object `mdata_vad`
(symbol value 0x4fd90, size 1,132,916 = `mdata_vad_length` at 0x164704), extracted to
`models/vad_2.1.0_0.binn` (sha256 `5e4d9ff470f763883bb6a2c6043e009059b855f97da9aa73fe3c4bda55816c66`).

The blob is **soil-binn** (parser: `reimpl/soil_binn.py`; layout recovered from the
`binn_*` accessors used by `CornLoadModelBin` at 0x31a4c):

```
object { list: [ {am: blob[1131400]}, {conf: blob[373]}, {cmvn: blob[1080]} ] }
```
* `conf` → `pre_conf{"--decoder-type":"nnet2"}`, `online_conf{"--feature-type":"mfcc",
  --num-pdf 2, --smooth-win-width 5, --sep-id 0, --min-voice-length 50, --min-sil-length 60,
  --left-context 10, --right-context 10}`, `mfcc_conf{"--use-energy":"false",
  --sample-frequency 16000, --num-mel-bins 40, --num-ceps 40, --low-freq 40,
  --high-freq -200}`, `version_conf{"--model-version":"vad_2.1.0_0"}`.
* `cmvn` → 82 floats parsed by `strtod` in `CornLoadModelBin` (0x31dd0-0x31ecc).

### 5.2 Weight stream (Soil binary, mode 7)
`CornLoadModelBin` iterates the binn object and calls `SoilInit(soil, am, 7, NULL)`.
Mode 7 = memory; `SoilMode()` maps it to `1`, so every loader reads 4-byte words
sequentially from `am` (`SoilLoadBinInt/Float/ArrayFloat`, `SoilLoadBinArrayFloat` has no
length prefix — the length comes from the component's dimensions).
`LoadDependence` (0x30960) then reads `[n_dep][dep ids…]` (skipped for type `input`).

Parsed graph (`reimpl/vad_model.py`, offsets are inside `vad_2.1.0_0.binn`):

| id | type | params | weights (offset) | deps |
|---:|---|---|---|---:|
| 0 | input | — | — | — |
| 1 | affine | 840 → 300 | W 1008000 B @51, b 300 @1008051 | 0 |
| 2 | pnorm | out_size 100 | — | 1 |
| 3 | affine | 100 → 300 | W 120000 B @1009287, b 300 @1129287 | 2 |
| 4 | pnorm | out_size 100 | — | 3 |
| 5 | normalize | size 100, divide=1, minus=0, rev=0, rms=1.0, src=internal | — | 4 |
| 6 | normalize | size 100, divide=1, minus=0, rev=0, rms=1.0, src=internal | — | 5 |
| 7 | affine | 100 → 2 | W 800 B @1130595, b 2 @1131395 | 6 |
| 8 | softmax | axis 0, flag 0 | — | 7 |

The parser consumes the stream exactly (`end 1131431 == am_offset+am_length`), which
validates the layout. Weight checksums are in `ir/vad_model_ir.json`
(W1 sha256 `c71e3281c8bd020aaf1969de6e0f97f1…`, W3 `7172850a46e08509b08ba8f89da1c820…`,
W7 `16ac2a433a52977e6b31f67114f37417…`).

**Feature front-end** (`CornFeatureInitialize` 0x32b50, `CornSpectProcessPack` 0x33040,
`CornMfccCompute` 0x33884, `CornMelBankInitial` 0x328e0, `CornCmvnCompute` 0x33b1c):

* 16 kHz, 25 ms frames (400 samples), 10 ms shift (160), 512-point FFT
  (constants `0x467a0000`=16000.0, `0x41c80000`=25.0, `0x41200000`=10.0, `0x3f7851ec`=0.97,
  `0x200`=512, `0x190`=400; Povey window, case 3 with exponent 0.85).
* per-frame mean removal (DC) then pre-emphasis 0.97, Povey window, power spectrum,
  40 triangular mel filters 40 Hz…7800 Hz, DCT → 40 cepstra (no energy, no deltas).
* CMVN with the embedded statistics, then ±10-frame context stacking → **840**-dim input.
* posterior smoothing (`--smooth-win-width 5`) and decoder
  (`CornSmoothPosterior` 0x35eec, `CornDetectVoice` 0x35fec, `CornDecode` 0x360d0).

### 5.3 Network shape

```
840 → affine(300) → pnorm(100) → affine(300) → pnorm(100) → normalize(100) ×2 → affine(2) → softmax
```
(`pnorm` reduces the dimension by the inferred window in/out = 3 and is therefore the
only dimension-reducing element; it is the reason the two `pnorm` layers turn 300 into 100.)

---

## 6. Reimplementation (`reimpl/`)

```
reimpl/
  soil_binn.py    # soil-binn container parser (validated: consumes the blob exactly)
  vad_model.py    # container+graph+weights extraction; JAX forward pass
  frontend.py     # Kaldi-style MFCC (16k/25ms/10ms/preemph .97/Povey/512/40 mel/40 ceps),
                  # CMVN, ±10-frame stacking
  pipeline.py     # end-to-end demo on synthetic audio + decoder state machine
  verify.txt      # run transcript
  README.md
```
Run: `cd reimpl && python pipeline.py` → exit 0 (see `verify.txt`).

Status: **runs_unverified** — real weights for all three affine layers, real model graph,
real front-end configuration; `pnorm`/`normalize` arithmetic and the CMVN scaling are
reconstructed from field names/sizes only (ARCHITECTURE_ONLY). No aarch64 execution
environment was available, so no numeric comparison against the original `.so` was made.

---

## 7. Confidence and unknowns

**Confirmed**: JNI surface of all four libs; app→SDK call path; mic capture parameters;
cloud endpoints and auth headers; VAD model container, graph, dimensions and weights;
VAD configuration (16 kHz, 40 mel/40 ceps, ±10 context, num-pdf 2, min-voice 50,
min-sil 60, smoothing 5); TTS = MP3 decoded by libmad.

**Inferred**: `pnorm` window/`normalize` arithmetic; the exact semantic of the embedded
CMVN array scale; iFly prompt being a UI-only leftover; MVS/Local-voice interplay.

**Unknown / blockers**: the classic VAD inside `libmibrainsdk.so` (0x41de0) was not
fully reversed; no access to cloud ASR/NLU models; `vad_2.1.0_0` is not exercised by this
APK's Java code, so its runtime behaviour on real audio could not be compared.

---

## 8. Evidence index

| Item | Location |
|---|---|
| Inventory (manifest, permissions, libs) | `inventory/inventory.md`, `inventory/inventory.json` |
| Decompiled Java (single-class jadx) | `sources/classes/`, index in `sources/index.md` |
| ELF facts / JNI lists | `native/elf/*.txt\|json` |
| Disassembly | `native/asm/*.s` |
| Ghidra pseudocode (48 functions) | `native/ghidra_vad2.c` |
| Extracted model | `models/vad_2.1.0_0.binn` |
| Model IR / config / CMVN | `ir/vad_model_ir.json`, `ir/vad_config.json`, `ir/vad_cmvn.json` |
| Dex string evidence | `evidence/dex_strings_voice.jsonl`, `evidence/dex_strings_endpoints.txt` |
| Manifest | `evidence/AndroidManifest.txt` |
| Commands + hashes | `evidence/cmd.txt`, `evidence/hashes.txt` |

---

## 9. Audio data path, cloud response path, lifecycle (patch 2026-10-24)

Full table-by-table contract: **`PIPELINE.md`**. This section is the narrative trace
plus the corrections it forced on §1–§4.

### 9.1 Corrections to the earlier revision

| Earlier statement | Corrected fact | Evidence |
|---|---|---|
| "TTS answer played back" | The TTS stream is decoded but the app has **no PCM sink**: `cg.l$c.onPCMData` and `onPlayStart` are empty, and `setTTSMode` is never called, so the player runs in mode 1 (callback) and drops the audio. `onPlayFinish` only resets the UI. | `cg.l.java:332-370`, `com.xiaomi.ai.c.java:1407`, `com.xiaomi.ai.streamplayer.k.java:56-311` |
| engine auth = MiAI OAuth (`*_TOKEN-V1` headers) | `cg.l.k()` uses the **4-argument** `createEngine(ctx, 1, appId, apiKey)` overload → `ENGINE_AUTH_APPIDTOKEN` (=0): the API key travels as the token, and the MiAI OAuth helper `m` is never created (authMode 0 skips `init`). | `cg.l.java:490-495`, `SpeechEngine.java:397-403`, `com.xiaomi.ai.c.java:2211-2230` |
| NLP reply fields `domain/entity/action` at top level | They live in `response.answer[0].{domain,entity,action}` and are re-parseable from `SpeechResult.getAnswer()`; the query string is `intention.query`. | `SpeechEngine.java:256-310`, `cg.l.java:200-230` |
| `RESULT_TTS_*` ids | Those exist, but the app's answer speech is a **second** `speak()` request (`MI_CMD_TTS=3`), not part of the ASR+NLP turn (`MI_CMD_ASR_NLP=0`). | `cg.l.java:508-512`, `cg.l.java:578-580` |

### 9.2 PCM → VAD → Opus → cloud → intent → IR (confirmed stage by stage)

```
cg.k.onClick ────────────────────────────────► cg.l.p / cg.l.o              [UI thread]
  startIntegrally(paramBuilder)                cg.l.java:583 / 514
    forceStop() → doStartIntegrally            SpeechEngine.java:1106
      c.a(ParamBuilder)                        com.xiaomi.ai.c.java:1444
        new c$e  (LinkedBlockingDeque, unbounded)          c.java:1235
        new Thread(new i(...))                 c.java:1478        → T2
T2 i.run: MibrainRequest(c.b(paramBuilder))    i.java:25
          native newContext + startRequest; c$a.start()        → T3
T3 c$a.b(e): AudioRecord(1, 16000, 16, 2, getMinBufferSize)
          loop: read(short[minBufSize], 0, minBufSize)
                utils.e.a(short[],n) → byte[2n] s16le        c.java:174-260
                queue.add(c$b) + notifyAsrBufferReceived/Rms  c.java:216
T4 com.xiaomi.ai.l: while(e.c()) e.b()          l.java:12
      MibrainRequest.addAudioData(bytes, abandon, last)         c.java:1264
        MibrainNativeRequest.addData(ctx, buf, len, flag)
T5/T6 libmibrainsdk: miBrainSdkAddDataInternal @0x13f74
      state gate → size ≤64 KiB → total ≤3,836,928 B → ring 200 slots
      vadCheckBegin/vadCheckEnd (LOCAL VAD) → Opus encode
      protobuf → WSS wss://speech.ai.xiaomi.com/speech/v1.0/access
T5 native receiver: protobuf → JSON text → c$d callbacks        c.java:506-1210
      meta.type RESULT_ASR_PARTIAL|FINAL → notifyAsrResults     c.java:1046
      meta.type RESULT_NLP → ResultParser.getNlpResult          SpeechEngine.java:256
        → cg.l$b.onResult → intention{domain,entity,action}     cg.l.java:156-330
        → cg.a|b|f|g .b(device, action, intention)              cg.a.java:56 …
        → ee.j.E(key) → re.g → ConsumerIrManager.transmit       ee.j.java:337, re.g.java:168
```

Per-chunk budget (confirmed): **80 ms of 16 kHz s16le = 2560 B = 1280 samples** on a
device whose `getMinBufferSize` returns 1280 B; native ring 200 chunks ≈ 16 s; hard
per-call limit 64 KiB; per-turn cap 3,836,928 B (≈120 s); no drop policy — a full ring
returns `-7` and aborts the turn (`c$e.b()` reports `"sdk addAudioData failed"`).
No per-chunk timestamps exist; only `request_id`/`session_id` and a native
`bytes/32000.0` duration accumulator at `ctx+0x628`.

### 9.3 Response path details

| Stage | Wire/JSON | Java object | App consumer |
|---|---|---|---|
| ASR partial/final | `meta.type=RESULT_ASR_PARTIAL/FINAL`, `response.queries[0].query`, `meta.request_id` | `SpeechResult.query/requestId` | `cg.l$a.onPartialResults/onResults` → `cg.k.f/.c` → Handler message `f12186h7/f12185g7` |
| NLP | `response.{version,session_id,answer[0].{domain,action,text,intention,content}}` | `SpeechResult.{answer,intention,domain,action,query,sessionId,toSpeak,toDisplay,directive,openMic,unknownDomainAction,vadIdle}` | `cg.l$b.onResult` |
| Error | `meta.type=ERROR`, `response.error_code/error_msg` | `SpeechError{errType=1}` | `voice_control_err_*` strings |
| TTS begin/chunks/end | `meta.tts.codec="mp3"`, binary MP3 frames | `streamplayer.k` → `f` → libmad → `PCMInfo` | (no-op in this app, see §9.1) |

Intent dispatch keys (confirmed): handler map `cg.l.f12217c` keyed by
`cg.l.e.e()` = `aircondition` (`cg.a`), `fan` (`cg.b`), `stb` (`cg.f`),
`tv` (`cg.g`, `ControlKey.KEY_TV`); `domain=="tvChannelControl"` forces `cg.f`
with the preferred STB `yd.k.g.f72699a.G()`. Device choice: `device_list`
(comma-separated ids → `yd.k.g.f72699a.J(id)`) else handler `d()`/`c()[0]`
(`cg.l.java:427-448`). Actions per handler are the strings in
`cg.a/b/f/g.b(...)` (`turn_up`, `set`, `volume_up`, `channel_next`, `navigate_ok`, …).

Output: every handler funnels through `ee.j.E(String)`; non-AC devices resolve an IR
code object (`re.g.k(df.e)` → `df.a/df.b/df.f`) and transmit with
`ConsumerIrManager.transmit(freq, int[])` (or `Miir.d(...)` for DB remotes) on `re.g`'s
own `Handler` thread; AC devices go through `KKACManagerV2` and the same
`re.g.j(freq, pattern, …)`. **No BLE/GATT write is reachable from the four voice
handlers** (`grep -o 'jVar\.[A-Za-z0-9_]*(' cg.{a,b,f,g}.java` → only `r/E/l`).

### 9.4 Lifecycle / fallback

| Phase | Behaviour | Evidence |
|---|---|---|
| init | `cg.l.l()` singleton → `k()`: `createEngine(ctx,1,"2882303761517406041","5141740612041")`, `setEnv(0=CN, 3=SGP)`, `setUserAgent("MiRemote; Sensy/1.56 …")`, registers ASR/NLP/TTS listeners | `cg.l.java:490-506` |
| auth | authMode 0 (`ENGINE_AUTH_APPIDTOKEN`): `mAppId` = appId, `mToken` = apiKey, both sent as request params; the OAuth helper `m` is never created for mode 0, so `c$d.onRequestGetToken` logs `"onRequestGetToken aiOauthHelper = null"` and returns null; the native SDK builds the header from appId/token (template `AA-TOKEN-V1 client_id:%s,api_key:%s`, mapping inferred) | `com.xiaomi.ai.c.java:2211-2230`, `SpeechEngine.java:397-403`, `c.java:1089-1120` |
| per-turn reconnect | a fresh `MibrainRequest` + native context per turn; Java has **no** retry/reconnect loop (native has `"goto retry"` markers for connect/token) | `i.java:25-152`, `libmibrainsdk` strings |
| mic permission | only an indirect check: `AudioRecord.getState()!=1` ⇒ `SpeechError{stage=REQUESTING,type=2,code=5}` ⇒ `voice_control_err_permission` | `c.java:182-195`, `cg.l.java:99-127` |
| timeouts | connect 5 s, receive 10 s, upload 4 s, active 10 s (settable, never changed by the app) | `SpeechEngine.java:68-71` |
| cloud failure | `onRequestError` → `SpeechError` (type 1 service / 2 local) → UI string only; **no local/offline fallback** (no on-device ASR) | `c.java:1057-1085`, `cg.l.java:99-127` |
| buffer overflow | native `-7` → queue drain stops (`f17154e=true`) → turn aborted | `c.java:1264-1310` |
| teardown | `endSpeech()` enqueues an end chunk; `forceStop()` stops player/queue/recorder + `asyncStopRequest()`; each new turn calls `forceStop()` first | `c.java:2030`, `2057`, `SpeechEngine.java:1101/1106` |
| TTS playback | mode 1 (PCM callback) → `cg.l$c.onPCMData` empty ⇒ decoded MP3 is not rendered; `onPlayFinish` → empty `cg.k.i()` | see §9.1 |

### 9.5 Evidence added by this patch

| Item | Location |
|---|---|
| Full pipeline contract | `PIPELINE.md` |
| New decompiled classes (jadx single-class) | `sources/classes/`: `com.xiaomi.ai.{i,l,j,m,PCMInfo}.java`, `com.xiaomi.ai.streamplayer.{k,a,d,f,g,h,TTSDecoderConfig}.java`, `com.xiaomi.ai.mibrain.MibrainMp3Decoder.java`, `com.xiaomi.ai.utils.{e,n}.java`, `cg.{a,b,f,g}.java`, `re.g.java`, `ee.j.java`, `vd.d.java`, `com.xiaomi.onetrack.api.b.java`, `rg.j.d.java`, `com.xiaomi.mitv.phone.remotecontroller.remotecall.RemoteCallHandlerActivity.java` |
| Native decode of `miBrainSdkAddDataInternal` | `evidence/sdk_adddata_disasm.txt` = `native/asm/sdk_addDataInternal.s` (0x13ec0-0x14860, 615 lines) |
| Commands used | `evidence/pipeline_cmds.txt` |
