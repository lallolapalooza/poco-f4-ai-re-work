# Mi Remote (`XMRemoteController.apk`) — data-pipeline integration contract

Slug: `miui_remote` · package `com.duokan.phone.remotecontroller` · version 6.6.6R (6352)
APK sha256 `6a955dfc5ef67f1b7cd4094108ec019d419c9e9541546d3e249fdde9354eb9a6`.

Scopes covered here:

| Pipeline | One-line description |
|---|---|
| **P1 voice command** | mic → PCM chunks → local VAD → Opus → WSS → ASR/NLP JSON → intent → IR |
| **P2 TTS playback** | `speak(text)` → WSS → MP3 chunks → libmad → PCM → (callback / AudioTrack) |

Every row is tagged `confirmed` (read directly in dex/JNI/native code),
`inferred` (derived from code that could not be executed) or `unknown`.
Line numbers refer to the jadx single-class files in `sources/classes/`
(regenerated with `tools/jadx/bin/jadx --single-class`; commands in
`evidence/pipeline_cmds.txt`). Native addresses are file VAs of
`inventory/libs/lib/arm64-v8a/libmibrainsdk.so` unless stated otherwise.

---

## 1. Data sources / triggers

| Step | Class + method | Detail | Conf |
|---|---|---|---|
| UI trigger | `cg.k` (VoiceControlActivity fragment, `classes3.dex`) — mic button `onClick`, `cg.k.java:525+` | calls `a0()` then `cg.l.p(activity, view)` | confirmed |
| Turn start | `cg.l.p` `cg.l.java:583` → `cg.l.o` `cg.l.java:514` | builds NLP context, calls `speechEngine.startIntegrally(paramBuilder)` (`cg.l.java:580`) | confirmed |
| Broadcast trigger | `com.xiaomi.mitv.phone.remotecontroller.voice.VoiceControlReceiver` (manifest action `…voice_control_action`) | alternative entry into the same `cg.l` singleton | confirmed |
| Engine facade | `com.xiaomi.ai.SpeechEngine.startIntegrally` `com.xiaomi.ai.SpeechEngine.java:1106` | `forceStop(); doStartIntegrally(paramBuilder);` — one turn at a time | confirmed |
| Engine impl | `com.xiaomi.ai.c.a(SpeechEngine.ParamBuilder)` `com.xiaomi.ai.c.java:1444` | tears down the previous turn, allocates the audio queue object `c.e`, spawns `com.xiaomi.ai.i` | confirmed |
| Request creation | `com.xiaomi.ai.i.run` `com.xiaomi.ai.i.java:25-152` | `new MibrainRequest(c.b(paramBuilder))` → `startRequest()` → native `newContext`/`startRequest` → starts recorder thread `c.a` | confirmed |
| Audio source | `com.xiaomi.ai.c$a.b(c.e)` `com.xiaomi.ai.c.java:174-260` | `AudioRecord.getMinBufferSize(16000, 16, 2)` + `new AudioRecord(1, 16000, 16, 2, minBufferSize)`; `startRecording()` then blocking `read(short[], 0, minBufferSize)` loop | confirmed |
| Teardown per turn | `com.xiaomi.ai.c.forceStop` `com.xiaomi.ai.c.java:2057` | stops player, ends queue, interrupts recorder thread, `MibrainRequest.asyncStopRequest()` | confirmed |
| Release | `com.xiaomi.ai.c.release` `com.xiaomi.ai.c.java:2230` | oauth helper `m.c()`, queue `f()`, `setBluetoothOn(false)` | confirmed |

The capture loop condition is `while (this.f17115j && !isInterrupted() && e.c())` where
`c.f17115j` is set true only when `needAsr || needVpRec || needVpReg`
(`com.xiaomi.ai.c.java:1474`), i.e. the mic is opened only for audio-bearing commands.

---

## 2. Transport and format

### 2.1 Capture → PCM

| Property | Value | Conf |
|---|---|---|
| Audio source | `MediaRecorder.AudioSource.MIC` (constant `1`) | confirmed (`c.java:181`) |
| Sample rate | 16000 Hz | confirmed |
| Channels | 1 (CHANNEL_IN_MONO = 16) | confirmed |
| Encoding | ENCODING_PCM_16BIT = 2, native byte order little-endian | confirmed (`com.xiaomi.ai.utils.e.a(short[],int)` writes `low, high`) |
| Buffer size passed to `AudioRecord` | `AudioRecord.getMinBufferSize(16000,16,2)` — platform value, not a constant | confirmed |
| `short[]` length | `= minBufferSize` (a byte count used as a *sample* count) | confirmed (`c.java:201`) |
| Samples per `read()` | `minBufferSize` shorts (2×minBufferSize bytes) | confirmed |
| Resulting chunk | for a device returning 1280 B → **1280 samples = 2560 B = 80 ms** | inferred (getMinBufferSize is device-dependent; 1280 B is the AOSP value for this config) |
| Conversion | `com.xiaomi.ai.utils.e.a(short[], int)` → new `byte[2n]`, s16le | confirmed |
| Chunk object | `com.xiaomi.ai.c$b(byte[], off, len, lastFlag, abandonFlag)` — copies the payload into a fresh array | confirmed (`c.java:433-505`) |
| Per-chunk side channel | `notifyAsrBufferReceived(byte[])` + `notifyAsrRmsChanged(float)`; RMS = mean absolute amplitude of the s16 samples (`com.xiaomi.ai.utils.n.a(byte[],int)`) | confirmed |

### 2.2 PCM → SDK queue → native

| Property | Value | Conf |
|---|---|---|
| Java queue | `com.xiaomi.ai.c$e.f17151b = new LinkedBlockingDeque< c$b >()` — **unbounded** | confirmed (`c.java:1235`) |
| Producer | recorder thread `c$a` via `eVar.a(bVar)` (`c.java:216`) | confirmed |
| Consumer | thread running `com.xiaomi.ai.l` (`c$e.d()` `c.java:1372`): `while (e.c()) e.b();` then `queue.clear()` | confirmed |
| Consumer body | `c$e.b()` `c.java:1264`: `MibrainRequest.addAudioData(byte[] data, boolean abandon, boolean last)` (the 3-boolean overload at `MibrainRequest.java:232`) | confirmed |
| End-of-stream | zero-length chunk with `lastFlag=true` is enqueued by `e.e()`/`e.f()` (`c.java:1380`, `1385`) | confirmed |
| JNI | `MibrainNativeRequest.addData(long ctx, byte[] buf, int len, int flag)` (`MibrainNativeRequest.java:147`) → `mibrain_sdk_wrapper_addData` @0x7524 in `libmibrainjni.so`: `GetByteArrayElements` → `miBrainSdkAddDataInternal(ctx, ptr, len, flag)` → `ReleaseByteArrayElements` | confirmed |
| Flags | `flag = last ? 1 : (abandon ? 2 : 0)` — `BRAIN_ADD_DATA_FLAG_CONTINUE=0`, `TASK_END=1`, `VOR_REG_DIV=2`; `len` is the byte length (0 for a pure end marker) | confirmed |
| Return codes | `-5` ended, `-2` uninitialised, `-6` params (NLP/TTS cmd), `-7` buffer-queue full, `-1/-3/-4/-8…` init/mem/unknown | confirmed (constants `MibrainRequest.java:8-35`) |

### 2.3 Native buffer management (`libmibrainsdk.so`)

All of the following is `miBrainSdkAddDataInternal` @ **0x13f74**
(full decode: `native/asm/sdk_addDataInternal.s`, copy in `evidence/sdk_adddata_disasm.txt`).

| Property | Value | Evidence | Conf |
|---|---|---|---|
| Context-state gate | `[ctx+0x5dc] == 1` else log `"mibrain request not initialized yet"` → `-1` | `0x13fa8-0x13fb0`, `0x14508` | confirmed |
| Per-call size limit | `len > 0xFFFF (65535)` → log `" buffer size is  more than 64k"` → **`-6`** | `0x1404c-0x14058`, `0x14624` | confirmed |
| Total-audio cap | `[ctx+0x60c] > 0x3A97FF` (i.e. `0x3A9800 = 3,836,928` bytes) → log `" total audio max is %d"` (prints 0x3A9800), sends end flag, → **`-7`** | `0x13ffc-0x1400c`, `0x145e0` | confirmed |
| Ring capacity | `[ctx+0x648] = 0xC8` = **200 slots**, set at context init (`0x10dcc`, log `"max buffer size =%d"` @0xb6808) | confirmed |
| Ring fill test | `([ctx+0x64c] - [ctx+0x650]) >= ([ctx+0x648] - 1)` → log `" queue is full max is %d"` → **`-7`** (`MIBRAINSDK_ERR_BUFFERQUEUE`) | `0x14010-0x14028`, `0x14618` | confirmed |
| Queue depth in time | 200 × 2560 B = 512,000 B ≈ **16 s of 16 kHz s16le** | inferred from the 80 ms chunk above | inferred |
| Enqueue | `malloc(len)` + `memcpy` then `miBrainSdkQueueAdd(ctx, 1, buf, len, last)` @0x135c4 | `0x140a4-0x140d4` | confirmed |
| Duration meter | float32 at `[ctx+0x628] += len / 32000.0f` (bytes → seconds); constant `32000.0f` @0x148a8 | `0x14710-0x14738` | confirmed |
| Drop policy | **none** — a full ring returns `-7`; Java then aborts the turn, it does not drop old chunks | confirmed |
| VAD hook | with `[ctx+0x670]` (use_vad) non-zero the call path runs `vadCheckBegin`/`vadCheckEnd` around the chunk (`0x14800`, `0x147b4`, logs `"vadcheckbegin %d  use_vad=%d"` / `"vadcheckend %d use_vad=%d"` / `"voice end!!!!"`) | confirmed |
| Timestamps | none per chunk; only the accumulated duration and the per-turn `request_id` | confirmed |

### 2.4 Codec / wire format

| Property | Value | Conf |
|---|---|---|
| Negotiated format | protobuf messages over TLS WebSocket `wss://speech.ai.xiaomi.com/speech/v1.0/access` (SGP/preview/staging variants), `Sec-WebSocket-Protocol` | confirmed (`libmibrainsdk` `.rodata`) |
| Audio codec | Opus (`libopus`, tags `Opus-Frame-Size`, `CodecMode:%d,isLast:%d inputsize:%d`); raw PCM allowed when `USE_ENCODER_PCM=1` | confirmed |
| App's codec parameters | `AsrRequest.f17078h` (codecMode), `f17079i` (opus frame size), `f17080j` (bitrate) are all **0** — the app never calls `setEncodeMode`, so the SDK defaults apply | confirmed (`AsrRequest.java:34-40`, `c.java:1552-1556`) |
| Exact default Opus frame size / bitrate | not resolvable from the app; init log prints `needSdkCodec:%d innerCodecMode:%d,opusFrameSize:%d,opusBitrates:%d` | unknown |
| Stream parameters actually sent | 1 channel, 16 bit, 16000 Hz, `maxEmptyAudioTime` 4 (8 when `isForSai`), internal VAD flag = `AsrRequest.setUseVad` (`true`), VAD type = `VAD_MODE_LOCAL` | confirmed (`c.java:1540-1585`) |
| VAD frame geometry | CornVad/classic VAD: 25 ms window, 10 ms hop (400/160 samples @16 kHz) | confirmed for the NN VAD (`ir/vad_config.json`); for the classic VAD in `libmibrainsdk` the 10 ms hop is `inferred` |

### 2.5 Threads and queues

| # | Thread | Created at | Work | Conf |
|---|---|---|---|---|
| T1 | Android main | UI | `cg.k` click → `cg.l.p/o`; receives results only through `Handler f12192d7` (`cg.k.java:80`) | confirmed |
| T2 | `new Thread(new i(...))` | `c.java:1478` (from T1) | build `MibrainRequest`, native `newContext`+`startRequest`, then `c$a.start()` | confirmed |
| T3 | `c$a extends Thread` | end of `i.run()` | AudioRecord loop, 80 ms chunks → Java queue | confirmed |
| T4 | `Thread(new l(c$e))` | `c$e.d()` `c.java:1372` | `BlockingQueue.take()` → `addAudioData` (blocking producer/consumer) | confirmed |
| T5 | native cwebsocket receiver | `pthread_create` inside `libmibrainsdk` | reads frames, converts protobuf→JSON, upcalls the 15 `MibrainRequestListener` methods (`"callback onmessage start len=%d,type=%d,eof=%d"`) | confirmed |
| T6 | native sender | `libmibrainsdk` | Opus encode + frame write (`"write error data state error…"`) | confirmed |
| T7 | `streamplayer.k$b extends Thread` | `k.a()` at `RESULT_TTS_BEGIN` | waits on decoded-PCM queue; 40 ms poll when idle; mode decides callback vs track write | confirmed |
| T8 | `Thread(new h(f))` | `streamplayer.f.a()` | `h.run()` → `f.f()` → blocking `MibrainMp3Decoder.start()` (native libmad loop, reads MP3 through `PipedInputStream`, pushes decoded frames into the queue), then `release()` and `f17245f=true` | confirmed |

All result callbacks reach the app **on T5**, not on the UI thread; every UI touch in
`cg.k` is marshalled via `Handler` messages `f12185g7` / `f12186h7` / `f12187i7` /
`f12188j7` (`cg.k.java:425-520`).

---

## 3. Preprocessing

| Stage | Operation | Where | Conf |
|---|---|---|---|
| 1 | s16le PCM, no gain/AGC/filter in Java | `c$a.b(e)` | confirmed |
| 2 | RMS level for the `SpeechVolumeView` meter = mean(\|sample\|), 0…32767 | `com.xiaomi.ai.utils.n.a` | confirmed |
| 3 | VAD on the *raw PCM chunk* (not on features) — `vadCheckBegin(handle, pcm16, nsamples, use_vad)` then `vadCheckEnd`; classic statistical detector at 0x41de0 | `miBrainSdkAddDataInternal` 0x14800 / 0x147b4 | confirmed (call sites) / `unknown` (detector arithmetic) |
| 4 | Opus encode of the same PCM inside the SDK | native (`CodecMode:%d,isLast:%d inputsize:%d`) | confirmed |
| 5 | No resampling, no AEC, no noise suppression in the app | — | confirmed (no such API in `sources/`) |

The NN VAD shipped in `libmibrainvad2.so` (`vad_2.1.0_0`) is **not** on this path: the
app never references `MibrainVad2` and the SDK uses its own `vadInit/vadCheckBegin`
(see `report.md` §5).

---

## 4. Model invocation

| Item | Value | Conf |
|---|---|---|
| On-device model | VAD only (classic detector inside `libmibrainsdk.so`); the 9-component Soil NN `vad_2.1.0_0` is present but unused | confirmed |
| ASR / NLU / TTS | remote service, called per turn through `AI_CMD_*` messages | confirmed |
| Request command ids | `ASR=1, ASR_NLP=0, ASR_NLP_TTS=2, TTS=3, NLP=4, NLP_TTS=5, VOR_REG=6, VOR=7, VOR_DEL=8, VOR_QUERY=9, EVENT=10, EVENT_TTS=11` | confirmed (`MibrainNativeRequest.java:7-38`) |
| Command used by the voice turn | `MI_CMD_ASR_NLP` (`needAsr().setAsrRequest(...).needNlp().setNlpRequest(...)`, `cg.l.java:578-580`) | confirmed |
| Command used for the spoken answer | `MI_CMD_TTS` via `SpeechEngine.speak` (`cg.l.java:508-512`) — a **separate** request, not part of the ASR turn | confirmed |
| Session/context | `NlpRequest.setContext("mi_remote", {"all_devices":[…]})`, `nlpVersion` default `"2.2"`, `time_zone`, `network`, `model`, lat/long when enabled | confirmed (`c.java:1610-1700`) |
| Protocol version | `mProtocolVersion = "1.0.5"` | confirmed (`SpeechEngine.java:66`) |

---

## 5. Output postprocessing (cloud → intent)

Native converts each protobuf message to **JSON text** and upcalls
`MibrainRequestListener.onRequestASRResult / onRequestNLpResult / onInstruction /
onRequestTTSResult / onRequestError` (`com.xiaomi.ai.c$d`, `c.java:506-1210`).

Message envelope (constant `com.xiaomi.onetrack.api.b.I` = **`"response"`**,
`com.xiaomi.onetrack.api.b.java:13`):

```json
{"meta":{"type":"<TYPE>","request_id":"…","tts":{"codec":"mp3"}},
 "response":{ … }}
```

| `meta.type` | Parsed by | Fields consumed | Result |
|---|---|---|---|
| `TRANSACTION_BEGIN` | `c$d.a(MibrainRequest,String)` `c.java:641` | — | advances `ProcessStage` |
| `RESULT_ASR_PARTIAL` | `SpeechEngine.ResultParser.getAsrResult` `SpeechEngine.java:208` | `response.queries[0].query` (key constant `RemoteCallHandlerActivity.f20304f` = **`"query"`**), `meta.request_id` | `notifyAsrParticalResults` → `cg.l$a.onPartialResults` → `cg.k.f` |
| `RESULT_ASR_FINAL` | same | same; empty query ⇒ `SpeechError(12)` | `notifyAsrResults` → `cg.l$a.onResults` → `cg.k.c` |
| `RESULT_ASR_VAD` | `c$d.a(MibrainRequest,JSONObject)` `c.java:936` | `response.is_final` | drives end-of-speech bookkeeping |
| `RESULT_NLP` | `ResultParser.getNlpResult` `SpeechEngine.java:256` | `response.version`, `response.session_id` (`OneTrack.Param.SESSION_ID`), `response.answer[0].{domain,action,text,intention.query}`, `response.answer[0].content.{to_speak (version≥2.2) \| toSpeak, to_display.text, to_display.duration, unknown_domain_action, open_mic, directive, vad_idle}`; `response.answer` present ⇒ NLP, absent ⇒ error | `notifyNlpResults` (or `notifyInstruction` when `instructions` exist) |
| `INSTRUCTION` | `ResultParser.getInstruction` → `APIUtils.readInstruction` | `response.instructions[]` (protobuf-JSON instruction objects) | `notifyInstruction` — **unused** by the app (`com.xiaomi.ai.api.common.Instruction` is referenced but `defined:false`) |
| `RESULT_TTS_BEGIN` | `c$d` | `meta.tts.codec` | creates `streamplayer.k` |
| `RESULT_TTS_URL` | `c$d` | whole `response` JSON | `notifyTtsGotUrl` (app ignores) |
| binary frames | `onRequestTTSResult(req, null, byte[], last)` → `c.java:969` | MP3 bytes | `k.a(byte[],0,len)` |
| `RESULT_TTS_END` | `c$d` | — | `k.b()`, `notifyTtsTransEnd(true)` |
| `ERROR` | `c$d.a(JSONObject)` `c.java:530` | `response.error_code`, `response.error_msg` (`rg.j.d.f52974k` = `"error_msg"`) | `SpeechError{type=1, code, msg}` → `notifyError` |
| bad stage / `status.code != 200` | `c$d.a(JSONObject)` `c.java:560-640` | `response.status.code` (must be 200 in `STAGE_NLP`), `response.status.error_details` | `SpeechError{type=2, code=3, "Internal state error"}` or `{type=1, code=<code>}` |

Error/status keys confirmed by decompiling the constant holders:
`com.xiaomi.onetrack.api.b.I="response"`, `rg.j.d.f52974k="error_msg"`,
`RemoteCallHandlerActivity.f20304f="query"`, `OneTrack.Param.SESSION_ID="session_id"`.

`ProcessStage` legality is enforced per stage (`c.java:560-640`): a message type that
does not belong to the current stage produces `SpeechError(3, "Internal state error")`.

### 5.1 NLP JSON → `cg.*` handler (the actual dispatch)

`cg.l$b.onResult` `cg.l.java:156-330`:

1. `new JSONObject(speechResult.getAnswer())` → `intention` object.
2. Reads `domain`, `entity`, `action` (`cg.l.java:217-219`).
3. `domain == "tvChannelControl"` → handler `cg.f`, device = preferred STB
   (`yd.k.g.f72699a.G()`).
4. else `entity` → handler map `l.f12217c` (registered in `cg.l.j()` `cg.l.java:479`):

| entity key (`cg.l.e.e()`) | handler class | device category |
|---|---|---|
| `aircondition` | `cg.a` | AC (`ee.j.e()==3`, KKACManagerV2) |
| `fan` | `cg.b` | fan |
| `stb` | `cg.f` | set-top box |
| `tv` | `cg.g` (`ControlKey.KEY_TV`) | TV |

5. Device selection: `cg.l.h(intention, entity)` `cg.l.java:427` — `device_list`
   comma-separated ids → `yd.k.g.f72699a.J(id)`; else handler `d()` / `c()[0]`.
6. `handler.b(device, action, intention)` → `cg.l$d{ok, speakText, tts}`.
7. No handler / no device → Chinese error text (`"对不起，暂不支持对…的语音控制"`,
   `"对不起，您还没有…，请先添加一个…"`) with `ok=false`.

### 5.2 Action → IR / BLE

| Handler | Actions handled | Emission | Conf |
|---|---|---|---|
| `cg.g` (TV) | `volume_up`, `volume_down`, `navigate_back/home/menu/up/down/left/right/ok`, `channel_next/prev`, `mute`, `open`, `close` | `device.r(key)` (key exists?) then `device.E(key)` (`cg.g.java:39-259`) | confirmed |
| `cg.f` (STB) | `volume_*`, `channel_*`, `open`, `close`, `play` (+ numeric channel digits) | `device.E(key)` | confirmed |
| `cg.b` (fan) | `speed_up/down`, `switch_speed`, `switch_wind_type`, `swing`, `mute`, `open`, `close`, `set_timer` | `device.E(key)` | confirmed |
| `cg.a` (AC) | `turn_up`, `turn_down`, `set` (`mode`,`fan_level`,`fan_direction`,`temperature`), `open`, `close` | `KKACManagerV2` state → `vd.d.g().j(irId, getACIRPatternIntArray(), true, true)` (`cg.a.java:163`) | confirmed |

`ee.j.E(String key)` `ee.j.java:337` is the single output funnel:

* non-AC device → `re.g.k(device.m(key))` → `re.g.l(df.e,…)` resolves the IR code object
  (positive/negative code, `df.a`/`df.b`/`df.f`) → `re.g.p(freq, int[] pattern)` →
  **`ConsumerIrManager.transmit(freq, pattern)`** (`re.g.java:168-172`), or
  `Miir.d(ctx, freq, code1, code2)` (`re.g.java:167`) for DB-defined codes.
  Transmission is posted to the dedicated `Handler` thread created in `re.g.<init>`
  (`re.g.java:36-66`).
* AC device → `KKACManagerV2` state machine → same `re.g.j(...)` → `ConsumerIrManager.transmit`.

**BLE**: none of the four handlers reaches a GATT write; the only calls on the device
object are `r/E/l` (`grep -o 'jVar\.[A-Za-z0-9_]*(' cg.{a,b,f,g}.java`). `BluetoothGatt`
+ `writeCharacteristic` *are* referenced in `classes3.dex`, but not from the voice
handler path — BLE is used for device discovery/state, not for voice-command output
(`inferred`; would be settled by tracing `ee.e`/`ee.j` transport for `p()==101/102`).

---

## 6. Consumer / action

| Consumer | Path | User-visible effect | Conf |
|---|---|---|---|
| Device IR | `cg.<handler>.b` → `ee.j.E` → `re.g` → `ConsumerIrManager.transmit` | appliance reacts (TV volume/channel, AC state) | confirmed (code) |
| Voice UI text | `cg.l.f` callbacks → `cg.k.b/g` → `Handler` messages → answer TextView | recognized query / confirmation | confirmed |
| Spoken answer | `cg.k.b/g` → `cg.l.n(text)` → `SpeechEngine.speak` → P2 | see P2 caveat | confirmed (call) |
| EPG search | same ASR/NLP turn; `cg.k.c(SpeechResult)` displays query only | search box text | inferred |

---

## 7. Failure and fallback

| Condition | Detection | Handling | Conf |
|---|---|---|---|
| No network before start | `com.xiaomi.ai.utils.f.a(context)` in `i.run` | `SpeechError(13)` "Network error" → `voice_control_err_network` | confirmed |
| Mic permission denied / recorder init fail | `AudioRecord.getState() != 1` (`c.java:182`) | `SpeechError{stage=REQUESTING, type=2, code=5, "Fail to init recorder"}` → `AsrListener.onError` maps **code 5** to `R.string.voice_control_err_permission` (`cg.l.java:99-127`) | confirmed |
| silence / no speech | native VAD with `maxEmptyAudioTime` = 4 (8 when `isForSai`), `c.java:1544` | service ends the turn; an empty final query becomes `SpeechError(12)` (`c.java:704`) | inferred |
| Connect timeout | `mConnectTimeout = 5 s` (`SpeechEngine.java:68`) → `setConnectTimeOut` | native `-10`, `onRequestError` → `SpeechError` | confirmed (values) / inferred (mapping) |
| Read/write/active timeout | `mReceiveTimeout 10 s`, `mUploadTimeout 4 s`, `mActiveTimeout 10 s` | native `-18/-19/-20` | confirmed (values) |
| Cloud returns `meta.type=ERROR` | `c$d.a(JSONObject)` | `SpeechError{type=1, code=response.error_code}` | confirmed |
| Cloud returns bad stage | `c.java:560-640` | `SpeechError{type=2, code=3, "Internal state error"}` | confirmed |
| Buffer queue full | native `-7` | `c$e.b()` sets `f17154e` and reports `"sdk addAudioData failed -7"` → turn aborted, **no retry, no local command** | confirmed |
| Any of the above in the app | `cg.l$a.onError` / `cg.l$b.onError` | UI text only (`voice_control_err_*`), TTS of the message only for the NLU-missing-device case | confirmed |
| Reconnect | — | no Java retry; every turn builds a **new** native context (`MibrainRequest` per turn). Native has retry markers `"goto retry"`, `"MIOT or TP goto retry"` for the token/connect step | confirmed (Java) / inferred (native retry scope) |
| Local fallback ASR | — | **none**: there is no on-device ASR; a cloud failure aborts the command | confirmed |

### 7.1 TTS decode / playback (P2)

| Step | Class + method | Detail | Conf |
|---|---|---|---|
| Trigger | `cg.l.n(String)` `cg.l.java:508` | `TtsRequest.setTextToSpeak(str)`; `SpeechEngine.speak` (`SpeechEngine.java:1101`) = `forceStop()` + `doSpeak()` | confirmed |
| Request params | `c.b(ParamBuilder)` `c.java:1506` | `setTTSCodec("mp3")`, `setTTSText`, tone/volume/speed, `sdkCmd = MI_CMD_TTS` | confirmed |
| Stream start | `c$d` on `RESULT_TTS_BEGIN` `c.java:746-770` | waits up to 10×100 ms while `AudioManager.isBluetoothScoOn()`; `new streamplayer.k(mStreamType=STREAM_MUSIC=3)`; `a(codec)`; `a(listener)`; `a(TTSMode)`; `a()` | confirmed |
| Codec selection | `k.a(String)` `streamplayer.k.java:334` | only `"mp3"` accepted; `TTSDecoderConfig.sUseHardDecoder == false` → **soft decoder `streamplayer.f`** (MediaCodec path `streamplayer.d` unused) | confirmed |
| Decode | `f.a(byte[],int,int)` → `PipedOutputStream` → `MibrainMp3Decoder` (JNI `libmibraindec` = libmad) → `g.write()` → `LinkedBlockingDeque<C0169a>` | `streamplayer.f.java:44-121`, `streamplayer.g.java` | confirmed |
| Playback | `k$b.run` `streamplayer.k.java:56-311` | `take()` decoded frame; creates `AudioTrack(streamType, rate, chCfg, PCM16, getMinBufferSize, MODE_STREAM)`; **mode 2** writes bytes, **mode 1** calls `TtsListener.onPCMData(PCMInfo{sample_rate,channels,bit,eof,pcm})` | confirmed |
| Play mode in this app | `c` constructor sets `f17129x = 1` (`c.java:1407`); `cg.l` never calls `setTTSMode` | **mode 1 = PCM callback** | confirmed |
| PCM sink in this app | `cg.l$c.onPCMData` is empty **and** `onPlayStart(AudioTrack)` is empty (`cg.l.java:332-370`) | decoded audio is dropped; the `AudioTrack` created in mode 1 is `play()`ed but never written | confirmed (code) |
| Audible TTS | consequence of the row above | **no audible TTS** from this SDK path in this build | inferred |
| End | `RESULT_TTS_END` → `k.b()`; player thread sees eof → `listener.a(k)` → `notifyTtsPlayFinish` → `cg.l$c.onPlayFinish` → `cg.l.f.i()` (implemented as an empty method in `cg.k.i()` `cg.k.java:520`) | confirmed |
| URL variant | `RESULT_TTS_URL` → `notifyTtsGotUrl` (empty handler) | confirmed |
| Threads | T7 player (40 ms poll), T8 libmad decode thread, `PipedInputStream/PipedOutputStream` (default 1024-byte pipe) as backpressure between MP3 feed and decoder | confirmed |

---

## 8. Confidence and open questions

| Claim | Conf | Evidence | What would settle it |
|---|---|---|---|
| Chunk = `getMinBufferSize` shorts, i.e. 80 ms on a 1280-byte device | inferred | `c.java:180-202` | run `AudioRecord.getMinBufferSize` on the target phone or log `audioRecord.read` returns |
| Native ring = 200 chunks, 64 KiB/call, 3,836,928 B/turn | confirmed | `0x10dcc`, `0x14010-0x14028`, `0x1404c-0x14058`, `0x13ffc` | — |
| No drop, hard `-7` on full queue | confirmed | `c$e.b()` + native returns | — |
| Opus frame size / bitrate actually negotiated | unknown | app passes 0/0/0 (`c.java:1552-1556`) | capture the init call arguments in a native debugger or read the `miBrainSdkInitContext` defaults |
| ASR-final JSON path is `response.queries[0].query` | confirmed | `ResultParser.getAsrResult` + `com.xiaomi.onetrack.api.b.java:13` (`I="response"`) + `RemoteCallHandlerActivity.java:42` (`f20304f="query"`) | — |
| Cloud NLP `content.to_speak` vs `toSpeak` depends on `response.version >= 2.2` | confirmed | `SpeechEngine.java:283-296` | — |
| TTS is silent in this build (mode 1 + empty PCM sink) | inferred | `c.java:1407`, `streamplayer.k.java` run loop, `cg.l.java:341` | device test with audio capture, or a build where `setTTSMode(2)` is called |
| Instruction API (`com.xiaomi.ai.api.*`) is unusable | confirmed | `dex_find`: `Instruction`/`APIUtils` `defined:false`; `INSTRUCTION` messages still parsed | — |
| BLE is not a voice-command sink | inferred | `grep -o 'jVar\.[A-Za-z0-9_]*(' cg.{a,b,f,g}.java` → only `r/E/l` | trace `ee.e` transport for `p()==101/102` |

Open questions carried from the base report (unchanged): classic VAD arithmetic at
`0x41de0`; cloud model internals; whether `vad_2.1.0_0` is ever activated.

---

## 9. Reimplementation mapping

| Stage | Reproduce with | Input → output contract | Status |
|---|---|---|---|
| Capture framing | (not modelled) | 16 kHz s16le mono, 80 ms chunks | n/a — host code |
| Local VAD (classic, used) | `reimpl/pipeline.py` VAD state machine + `reimpl/frontend.py` MFCC + `reimpl/vad_model.py` | 25 ms/10 ms frames → per-frame posterior → voice/sil decision | ARCHITECTURE_ONLY for the classic detector; the NN path is `runs_unverified` with real weights |
| Opus encode | **not reimplemented** | PCM chunk → Opus frame | not applicable (library codec) |
| Cloud ASR/NLP | documentation only (`report.md` §4.2 endpoints; JSON envelope in §5 above) | Opus/PCM ⇄ `{"meta":…,"response":…}` | only documented |
| Intent → IR | `cg.a/b/f/g` + `ee.j.E` + `re.g` | `{domain,entity,action,content}` + device → `ConsumerIrManager.transmit(freq, int[])` | can be re-implemented exactly: the pattern arrays come from the TVMao/KKAC database (`KKACManagerV2.getACIRPatternIntArray`) |
| TTS | `streamplayer.k/f` + libmad | MP3 byte stream → PCM frames (rate/channels/bit come from the decoder's `AudioInfo` per frame) → AudioTrack or PCM callback | documented, no code needed |
