# Data-pipeline integration contract — `kwai_live` (`com.kwai.kuaishou.video.live` 6.0.20.524207)

Three pipelines are traced here. A and B are the **live push** pipelines (this
patch); C is the **model/effect binding** path and how it differs from the
recorded-video path. The AI stack itself (libraries, YCNN catalogue,
FaceMagic effects) is shared with `kwai_video`; see
`reports/_shared_kwai_sdk.md` and `report.md` §2/§5/§7.

* **A — live video**: Camera2/Daenerys capture → westeros GL effect graph
  (face detect + FaceMagic) → YUV readback (`MediaCallback`) → Arya RTC/RTMP.
* **B — live audio**: mic (Oboe/AAudio inside `libkwaiaudio.so`) → Stannis
  3A/VAD/voice-FX → Arya TX stream → RTMP, with the video clock.
* **C — live effect/model binding**: CDN resource → FaceMagic/YCNN path
  binding → effect callbacks back into the GL graph.

Legend: **confirmed** = read from an artifact (file/offset/class/method/line),
**inferred** = derived from symbols/strings/structure, **unknown** = not
recoverable from the APK.

Canonical evidence files for this patch:

| file | content |
|---|---|
| `evidence/live_push_trace.md` | line-level call trace for A/B/C |
| `evidence/kwaiaudio_strings.txt` | `libkwaiaudio.so` `.rodata` audio/AEC/VAD strings with offsets |
| `evidence/live_cmd.txt` | every command used to produce this patch |
| `native/audio_voicedetect_interface.s`, `native/audio_voicedetect_process.s` | disassembly of the VoiceDetect JNI pair |
| `native/libdaenerys.so.{json,summary.txt}` | ELF facts for the capture engine (138 JNI) |
| `sources/classes/live/*.java` | all classes cited below (jadx `--single-class`) |

---

# Pipeline A — live video: camera → face/beauty → push

## A.1 Data sources / triggers

| item | value | confidence | evidence |
|---|---|---|---|
| UI entry | `LivePushActivity` (ShellActivity, fragment `m`) receives `livePushConfig`/`livePushBeautyConfig`/`livePushFILTERConfig`/`liveFrontCamera`/`streamType` | confirmed | `sources/classes/live/com.yxcorp.gifshow.live.push.LivePushActivity.java:32-47` (`K0`, `putParcelable("livePushConfig",…)`) |
| preview/camera presenter | `com.yxcorp.gifshow.live.push.prepush.presenter.g` holds the `CameraView` (`N0()`), sets beauty/filter listeners, `onDestroy` releases | confirmed | `sources/classes/live/com.yxcorp.gifshow.live.push.prepush.presenter.g.java:330-343,397-410` |
| preview widget | `com.yxcorp.gifshow.camerasdk.widget.CameraView` (FrameLayout + `VideoSurfaceView` + gestures) | confirmed | `sources/classes/live/com.yxcorp.gifshow.camerasdk.widget.CameraView.java:32-44,354` |
| camera engine | `com.kwai.camerasdk.Daenerys` → `libdaenerys.so` (2 580 368 B, 138 JNI) | confirmed | `native/libdaenerys.so.summary.txt`; `sources/classes/live/com.kwai.camerasdk.Daenerys.java:43,316` |
| capture controller | `com.kwai.camerasdk.videoCapture.CameraControllerImpl` (Camera1/Camera2 sessions) | confirmed | `…CameraControllerImpl.java:29,1292` |
| push start (app) | `LivePushClient.S0()` → `KSMediaLiveKit.startPush(daenerys, "PHONE_AUDIO".equals(type)?2:1)` (2 = VOICE, 1 = NORMAL) | confirmed | `sources/classes/live/com.yxcorp.gifshow.live.push.stream.LivePushClient.java:638-642`; enum `KSMediaLiveKit.LiveType` |
| push start (kit) | `com.kwai.video.ksmedialivekit.LivePushClient.startPush(Daenerys)` requires `status==READY(1)`, registers `MediaCallback`, then `PushDestinationStrategy.startPush(this)` | confirmed | `com.kwai.video.ksmedialivekit.LivePushClient.java:391-419` |
| RTC engine | `com.kwai.video.arya.Arya` → natives in `libkwaivoip.so` (107 `Java_com_kwai_video_arya_Arya_*` of 130 JNI) | confirmed | `evidence/live_cmd.txt` (`elf_info libkwaivoip.so`), `Arya` method list |
| teardown | `LivePushClient.r0()`: `stopPush` → `destroy()` → `daenerys.U(null)`, `daenerys.h0(true)`, dispose subscriptions | confirmed | `…stream.LivePushClient.java:1178-1206` |

## A.2 Transport and format

| property | value | confidence | evidence |
|---|---|---|---|
| camera API | `kAndroidCameraAuto` (Camera2 preferred, Camera1 fallback) | confirmed | `DaenerysConfigBuilder.defaultCaptureConfigBuilder()` `.e(p000do.i.kAndroidCameraAuto)` |
| capture config defaults | target fps 30 (`M(30)`), min fps 0 (`N(0)`), sizes `y0()=720`, `v0()=1280`, `u0()=t0()=W()=V()=0`, audio 44100 Hz / 1 ch / 16 bit, output data type **YUV** (`p000do.k.kCameraOutputDataTypeYuv = 0`) | confirmed (values) / inferred (`f()` argument order = (width,height)) | `sources/classes/live/com.kwai.camerasdk.DaenerysConfigBuilder.java:23-27`; builder letter→field map in `sources/classes/live/do.u.java:230-270,618-680` and getters `:710-766,1561-1818`; enum `do.k` |
| slot mapping | `resolutionRequest.a=(u0,t0)=(0,0)` (auto), `.b=(y0,v0)=(720,1280)`, `.c=(W,V)=(0,0)` | confirmed (map) | `do.u.java` setters `q1→f64777b(y0)`, `n1→f64779c(v0)`, `m1→f64803p(u0)`, `l1→f64804q(t0)`; applied in `CameraControllerImpl.initWithDaenerysCaptureConfig` `:1626-1645` |
| frame object | `com.kwai.camerasdk.video.VideoFrame` — `data.byteBuffer`, `width`, `height`, `yuv_format`, `timestamp`, `attributes`, `isTexture()/isYuv()` | confirmed | `VideoFrame` method dump (`evidence/live_cmd.txt`); used at `ksmedialivekit.LivePushClient.java:87` |
| encoder feed | `Daenerys.setMediaCallback(fps, maxWidth, maxHeight, MediaCallback)` → `nativeSetMediaCallback(j, fps, maxW, maxH, downSamplerType, cb)` | confirmed | `Daenerys.java:503,747-757` |
| max feed size | `LivePushClient` passes `config.s()` (fps), `config.u()`, `config.v()` from `sx.d` (server push config) | confirmed (call) / unknown (values) | `ksmedialivekit.LivePushClient.java:408` |
| frame→Arya | `Arya.inputRawVideo(ByteBuffer, VideoFrameAttribute(format, width, height, timestamp, rotation, csp, sourceId, rois, ""))` | confirmed | `ksmedialivekit.LivePushClient.java:85-88`; `Arya` `nativeInputRawVideoByteBuffer(J,int,ByteBuffer,int,int,long,int,int,String,int,boolean)` (`sources/classes/live/com.kwai.video.arya.Arya$AryaConfig.java:2288`) |
| rotation / mirror | rotation comes from `VideoFrame.attributes.f().m()`, colour space from `attributes.b()`; front mirror handled by `CameraControllerImpl.mirrorFrontCamera()`/`setMirrorFrontCamera` | confirmed (fields) / unknown (rotation units: degrees vs index) | `ksmedialivekit.LivePushClient.java:87`; `CameraControllerImpl.java:1690,1891,2056` |
| preview output | separate EGL display surface: `NativeRenderThread.nativeBindDisplayContext`, `nativeDrawFrame(VideoFrame)`, `nativeResizeView(w,h)`, `nativeSetDisplayEnabled` → `VideoSurfaceView` | confirmed | `sources/classes/live/com.kwai.camerasdk.render.NativeRenderThread.java:20-52`; `CameraView.getSurfaceView` |
| encoder output | `Arya` live stream: `nativeSetLiveStreamRtmpUrl`, `nativeSetLiveStreamVideoEncodeParam(w,h,fps,maxBitrate,resumeOnRtcStop)`; encoding inside `libkwaivoip.so` | confirmed (API) / inferred (encoder internals) | `Arya$AryaConfig.java:1443`; `KSMediaLiveKit.updateLiveStreamRtmpUrl` |
| timestamp clock | `VideoFrame.timestamp` (ns-scale, forwarded unchanged to Arya) | confirmed (forwarding) / unknown (epoch) | `ksmedialivekit.LivePushClient.java:87` |
| thread/queue | capture + effect run on Daenerys' native render thread (`GlPreProcessor.onDraw(VideoFrame,int)` is a GL-thread callback; `post(Runnable)` → `nativePostRunnableToGlThread`); Java `MediaCallback` is invoked from that pipeline; Arya enqueues into its own native worker (`nativeInputRawVideoByteBuffer` returns an int status) | confirmed (callbacks) / inferred (Arya queue depth) | `sources/classes/live/com.kwai.camerasdk.preprocess.GlPreProcessor.java:16-62`; `Daenerys.nativeGetRenderThread` |

## A.3 Preprocessing

Order inside the Daenerys GL pipeline (confirmed by the processor plug-in API,
exact slot indices unknown because `Daenerys` is obfuscated-free but native):

| # | operation | where | confidence |
|---|---|---|---|
| 1 | sensor/orientation + camera session geometry (preview crop, `previewCropSize`, `cameraCaptureSize`) | `CameraControllerImpl.resetSize()` / `CameraSession` | confirmed (fields) |
| 2 | front-camera mirror (if `config.q0()`), then GL pre-processor group: `nativeAddGLPreProcessorAtGroup` / `nativeInsertGLPreProcessorGroupAtSlot` | `Daenerys.java:446,469` | confirmed (API) |
| 3 | westeros effect graph = `Westeros` bound to the same `Daenerys` (`nativeSetDaenerys`) with `WesterosGlProcessor.toDaenerysProcessor()` returning a `GlPreProcessor` | `sources/classes/live/com.kwai.video.westeros.Westeros.java:56-72`; `…v2.WesterosGlProcessor.java:15-60` | confirmed |
| 4 | face detection: `FaceDetectorContext` registered on both sides — `Westeros.setFaceDetectorContext(ctx,int)` calls `daenerys.X(ctx,int)` **and** `nativeSetFaceDetectorContext(westeros, nativeCtx, nativeSensorMgr, type)` | `Westeros.java:238-240,341-350`; `Daenerys.java:693-719` | confirmed |
| 5 | FaceMagic effect: `FaceMagicController` GL processor (from `libfaceless-plugin.so`) consumes the frame + landmarks; callbacks (bokeh mask, memoji icon) come back on the same thread | `FaceMagicController` 124 methods; `nativeGetGlProcessor(J)J` | confirmed (API) / inferred (thread) |
| 6 | YUV readback for the encoder: `nativeSetMediaCallback(..., downSamplerType, …)` (`e0.kDownSamplerTypeUnknow` = no explicit downsampler in the default call) | `Daenerys.java:747-757` | confirmed (API) / unknown (exact readback shader) |
| 7 | display blit: `nativeDrawFrame(VideoFrame)` on the display EGL context | `NativeRenderThread.java:36` | confirmed |

Colour contract: `VideoFrameAttribute.csp` is passed as an int to Arya
(`nativeInputRawVideoByteBuffer(..., csp, rois, sourceId, mirror)`); the
enumeration is not in the APK, so the YUV matrix (BT.601 vs BT.709) is
**unknown**. The camera SDK's own recorder declares BT.601_PAL + full range
for H.264 (`MediaCodecEncoder` `KEY_COLOR_STANDARD=2`, `KEY_COLOR_RANGE=1`),
which suggests the same convention for live but is not proven for Arya.

## A.4 Model invocation

| stage | engine | binding | confidence |
|---|---|---|---|
| face detect / landmarks / seg / matting | YCNN (downloaded backends: `ycnn`, `ycnn2`, `hiai_*`, `tflite_mtk_static`, SNPE) | `FaceDetectorContext.nativeInit(ctx, type, id)`, `nativeSetData`, `nativeDetectFacesFromFrame(VideoFrame, type)`; backends loaded by `YcnnSoLoader` | confirmed (API) / models not in APK |
| beauty / makeup / AR / MMU | `libFaceMagic.so` (AE2) | `FaceMagicController.nativeInitController`, `nativeSetEffect(byte[],int,int)`, `nativeUpdateEffectControl`, `nativeSendEffectCommand(Sync)` | confirmed |
| model path plumbing | `westeros.ResourceManager.nativeSetYlabModelPathConfig`, `nativeSetDirtyLensDetectModelPath`, `nativeSetPath` (43 JNI) | Java `ResourceManager` | confirmed (`native/libwesteros.so.json`) |
| plain frame input to YCNN plugins (bitmap path, used by editor/other surfaces) | `YcnnPluginUtils.getFaceData(Bitmap, config, ctx)` | `sources/classes/YcnnPluginUtils.java` | confirmed |

Nothing is bundled: 0 model assets, no carved flatbuffer, and the YCNN
backends themselves are downloaded (see `report.md` §5/§6 and
`_shared_kwai_sdk.md` §3-§5).

## A.5 Output postprocessing

* `libfacedetectcontext.so` returns `byte[][]` from
  `nativeDetectFacesFromFrame` — serialized `com.kwai.video.westeros.models.YCNNFaces`
  protobuf, later decoded by `WesterosFrameAttributesDumpProcessor.onReceiveVideoFrameAttributes([B)`
  and `FaceMagicController.onReceivedBokehMask([B,int,int)`.
* FaceMagic pushes results back to Java through:
  `onReceivedBokehMask(byte[],int,int)`, `onReceivedBokehStatus(int)`,
  `onReceivedMemojiIcon(ByteBuffer,int,int)`, `onEffectDescriptionFromNative`,
  `onEffectHintFromNative`, `onEffectPlayCompleted(int,int)`,
  `onDepthFinishCallback`, `onReceiveRecordingControl(int)`.
* Nothing from these structs is re-serialized for the network: they only drive
  rendering (masks/landmarks stay on the GPU).

## A.6 Consumer / action

`Arya.inputRawVideo` → `nativeInputRawVideoByteBuffer` (libkwaivoip.so) →
live-stream encoder → RTMP URL set by `nativeSetLiveStreamRtmpUrl` / RTC
channel (`startRtc`, `nativeStartCall`). User-visible output: the live stream
itself; no storage.

## A.7 Failure and fallback

| condition | behaviour | confidence | evidence |
|---|---|---|---|
| Camera2 open/auth fails | one-shot reopen with `kAndroidCamera1` (`reOpenedCamera2v1` latch); `CameraControllerImpl` also has `RetryStartPreviewHelper` | confirmed | `CameraControllerImpl.java:882-886,1050` |
| encoder/push failure | `handlePushFailed()` → status `RETRYING(3)` → `PushDestinationStrategy.handlePushFailed` with network requester; app retry loop `retryWhen` (2 s timer, bounded by `f45793s`), fatal codes `1016013014` (stop) / `1016013015` (fatal) | confirmed | `ksmedialivekit.LivePushClient.java:137-150`; `stream.LivePushClient.java:162-197` |
| black frames | `Daenerys` black-image checker (`nativeSetBlackImageCheckerCallback`, `BlackImageCheckerCallback.onBlackImageDetected`) | confirmed | `Daenerys.java:491`; `stream.LivePushClient.java:274-297` |
| background/foreground | `s0()` pauses push (and cancels timers), `t0()` resumes | confirmed | `stream.LivePushClient.java:1208-1227` |
| GPU capability | `GPUPerformance` buckets renderer strings → score → `Level{unknown,bad,low,medium,high,best}` with thresholds 0/50/100/200/500 | confirmed | `sources/classes/live/com.kwai.video.westeros.GPUPerformance.java:17-47` |
| CPU/NPU backends | `YcnnSoLoader` loads HiAI / MTK `tflite_mtk_static` / SNPE only when present; absent on a stock Poco F4 build (libraries not in APK) | confirmed (APK) / inferred (runtime choice) | `sources/classes/YcnnSoLoader.java`; `report.md` §5 |

## A.8 Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| live video reaches Arya as YUV buffers from Daenerys' MediaCallback | confirmed | `ksmedialivekit.LivePushClient.java:85-88,408` | — |
| capture defaults 720×1280 / 30 fps / YUV | confirmed (values) | `DaenerysConfigBuilder.java:23-27` + `do.u` field map | a device trace (`setMediaCallback` args) |
| which resolution slot is preview vs encoder | inferred | `resolutionRequest.a/b/c` mapping | `libdaenerys.so` pipeline dump |
| rotation units / colour-space enum | unknown | `VideoFrameAttribute` fields | `libkwaivoip.so` `nativeInputRawVideoByteBuffer` |
| FaceMagic runs inside the Daenerys GL thread | inferred | `GlPreProcessor.onDraw` contract + `nativeGetGlProcessor` | Ghidra on `libfaceless-plugin.so` |

## A.9 Reimplementation mapping

| stage | JAX module | contract |
|---|---|---|
| beautify blend (smooth/bright/clarity/sharpen/LUT) | `reimpl/beauty.py` | RGB float32 `[H,W,3]` + `BeautifyParams` → same shape; presets from `assets/beauty_list` |
| body-slim warp | `reimpl/liquify.py` | landmarks `[63,2]` in [0,1]² + `*.params` ops → dense displacement field |
| composition | `reimpl/pipeline.py` | `apply(params, frame, ctx)` |
| face detect / landmarks / seg / matting / makeup / AR | — | **ARCHITECTURE_ONLY**: YCNN networks are downloaded and absent from the APK |

---

# Pipeline B — live audio: mic → Stannis → push

## B.1 Data sources / triggers

| item | value | confidence | evidence |
|---|---|---|---|
| start | `Arya.startLiveStream(LiveStreamParam, rtmpUrl)` → `nativeStartLiveStream` (libkwaivoip.so) creates the audio engine | confirmed (API) | `Arya` method list; `KSMediaLiveKit.startPush` |
| audio device layer | `com.kwai.video.arya.AryaAudioEngineProxyImp` (extends `com.kwai.video.arya.a`) proxies every device/effect call to **Stannis** | confirmed | `sources/classes/live/com.kwai.video.arya.AryaAudioEngineProxyImp.java:11-19,220` |
| Stannis = `libkwaiaudio.so` | 295 JNI, `Java_com_kwai_video_stannis_Stannis_*` + `..._AudioProcess_*` | confirmed | `native/libkwaiaudio.so.json` |
| scene | `Stannis.kLiveStream = 512`, `kLiveStreamWithChat = 1792`, `KAudioLiveStreamOut = 512` | confirmed | `sources/classes/live/com.kwai.video.stannis.Stannis.java:60,144-145` |
| VAD / AEC defaults | `AryaConfig.enableAudioVad = true`, `enableAudioPreProcess = true`; proxy forces `audioChannel = audioOutputChannel = 2` for live | confirmed | `Arya$AryaConfig.java:392,409`; `AryaAudioEngineProxyImp.java:1157-1162` |
| teardown | `Arya.stopLiveStream("User Hangup")` via `KSMediaLiveKit.stopPush`; `Stannis` uninit through the proxy | confirmed | `ksmedialivekit.LivePushClient.java:420-437`; `AryaAudioEngineProxyImp` |

## B.2 Transport and format

| property | value | confidence | evidence |
|---|---|---|---|
| capture API | Oboe/AAudio with OpenSLES fallback inside `libkwaiaudio.so` (`[AndroidAudioEngineOboe] InitRecorder sample_rate = %d, channels = %d, buffer_size = %d, recording_preset = %d`; `AAudioStreamBuilder_setSampleRate/_setFramesPerDataCallback`) | confirmed (strings) / inferred (live uses Oboe path) | `evidence/kwaiaudio_strings.txt` `0x15db00`, `0x161744`, `0x1616cd` |
| sample rate | runtime value from `SetAudioConfig` (`capture_sample_rate[%d]`); **44100** in the camera capture config (`DaenerysConfigBuilder .K(44100)`), **48000** in `AudioControllerImpl` default and `Stannis.startAudioInnerCap(48000,2,…)`; the live TX rate is reported as `kAudioTxLiveStreamSampleRate = %d` and in QosInfo `atxStrmSr` | confirmed (44100/48000 constants) / unknown (which one the live TX stream runs at) | `DaenerysConfigBuilder.java:23-27`; `AudioControllerImpl.java:176-177`; `Stannis.java:2350-2360`; `evidence/kwaiaudio_strings.txt` `0x15a71d`, `0x15b88e` |
| channels | 2 for live (proxy), 1 in camera capture config | confirmed | `AryaAudioEngineProxyImp.java:1159-1160,1180-1181` |
| PCM format | 16-bit signed PCM (`byte[]` on the Java side, `short[]` for the post-effect/watermark APIs; `getSamplePerFrame() = sr*ch/100`) | confirmed | `AudioProcess.java` native signatures; `Stannis.java:314-316` |
| frame/hop | **10 ms**, exact: `byteLength = sr*ch*2/100`; a different length is dropped with `"Stannis AudioProcessLib ProcessData is not 10ms"` | confirmed | `Stannis.java:322-331` |
| 3A ring buffer | AEC runs on 10 ms packets: `ringbuf_10ms_pkgs`, `nearend_delay_10ms_pkgs`, `[AudioPreProcess] SetAecInternal … sample_rate[%d], channels[%d]` | confirmed (log strings) | `evidence/kwaiaudio_strings.txt` `0x14b61d`, `0x14b7af`, `0x14bd77` |
| timestamp / sync | audio and video meet in the Arya native session; audio TX carries no per-frame timestamp on the Java API (`inputRawAudio(byte[],int,int,int,long)` has one, the capture path does not use it) — sync is done natively by the audio engine clock | confirmed (API) / inferred (clock) | `Arya.inputRawAudio/inputPcmPlay` signatures |
| A/V sync for BGM/KTV | `SpkVocalBgmSyncWorkshop` + `VocalBgmSyncWorkshop: first frame offset:%d`; `LiveStreamBgmOffsetCb` (`nativeSetLiveStreamBgmOffsetCb`) reports the offset back to Java | confirmed (symbols/strings) | `evidence/kwaiaudio_strings.txt` `0x14f920`, `0x14fa36`; `Arya` method list |
| thread/queue | engine worker threads + `AudioFrameObserverManager.HandleAudioFrame`; Java side has `HandlerThread`/`Handler` for observer dispatch | confirmed (classes) / inferred (depth) | `evidence/kwaiaudio_strings.txt` `0x14dcd9`; `AryaAudioEngineProxyImp.java:33-40` |
| second (westeros) audio bridge | `AudioPlugin` (`WesterosPlugin`) → `libaudioplugin.so` (43 160 B, 2 JNI: `nativeCreateAudioPlugin` @0x4bb8, `nativeDestroyAudioPlugin` @0x4c34) which links `libksaudioprocesslib.so` + `libdaenerys.so`; separate from the Stannis engine, role in the live push unknown | confirmed (ELF) / unknown (use) | `native/libaudioplugin.so.summary.txt`; `sources/classes/live/com.kwai.video.westeros.audioplugin.AudioPlugin.java` |

Secondary capture path (camera SDK, used when the camera pipeline owns audio —
e.g. recording and any `AudioProcessor` added to Daenerys): `AudioControllerImpl`
= `AudioRecord(0 /*MIC*/, sampleRate, channelIn, 2 /*PCM_16BIT*/, buffer)`,
defaults **48000 Hz / mono**, `BUFFERS_PER_SECOND = 100`, `CALLBACK_BUFFER_SIZE_MS = 10`
and timestamp `t0 + frames*1000/sampleRate`.

## B.3 Preprocessing

| # | stage | implementation | confidence |
|---|---|---|---|
| 1 | AEC / NS / AGC (device AEC preferred, soft AEC fallback; `aec_nlp`, `force_aec`, `aec_ns_level`) | `kuaishou::audioengine::AudioPreProcess` (`SetAecInternal`, `SetAudioConfig`) | confirmed (strings) |
| 2 | deep VAD (`SetEnableDeepVad:%d, model:%s`) / simple VAD (`nativeAgcGetVadStatus`) | `AudioProcess` + `AudioVadInfoCollector` (`hang_over_ms`, default 500) | confirmed |
| 3 | voice change (`kVeo*` 0-15), reverb (`kReverb*` 0-18), post effect (`kPostEffect*` 0-5), vocal/timbre, pitch, tempo | `AudioProcess` interfaces → `nativeDspSetParameters`, `nativeAudioPostEffectSetOption`, `nativeSetVoiceEffectOption` | confirmed |
| 4 | voice/background/music detection | `AudioVoiceDetectInterface(sr, ch, modelPath).process(byte[],len)` → 3 probabilities | confirmed (JNI + Java) |
| 5 | resample to the stream rate | `FFmpegSwrAudioResampler` / libsamplerate 0.1.9 | confirmed (strings) |

## B.4 Model invocation

* `libkwaiaudio.so` contains **no** bundled model (`carve_libkwaiaudio.so.json`
  in `native/` reports no TFLite/Caffe/DLC blob).
* The VAD/denoise models are named at runtime: `SetEnableDeepVad:%d, model:%s`,
  `KtpModelPath` (`Arya.nativeSetKtpModelPath`), `SetHrtfModePath`,
  `Voice3D Model Path: %s GET_MODEL_MD5: %s` — i.e. paths + MD5 supplied by the
  Java layer, models downloaded.
* VoiceDetect interface is constructed with a **model path string**
  (`nativeAudioVoiceDetectInterface(int sr, int ch, String model)`), confirming
  a per-instance model file rather than a built-in network.

## B.5 Output postprocessing

`AudioVoiceDetectResult{result_available:int, backgroud_prob:float,
speech_prob:float, music_prob:float}` — the native wrapper writes exactly three
floats (`native/audio_voicedetect_process.s:0x12a60c` `stp w27,w24,[x25]` /
`str w26,[x25,#8]`) and returns 1 when a result is available. Active-speaker
lists come from `AudioVadInfoCollector` (`return active speakers: {%s}`) and
are forwarded to Java through `MediaLiveActiveSpeakerListener.onActiveSpeakerChanged(Map)`.

## B.6 Consumer / action

Arya TX path (`LiveStreamOut` inside `libkwaiaudio.so`) → RTC/RTMP muxer in
`libkwaivoip.so`. User-visible: live audio (with BGM mixing, KTV reverb,
voice changer, headphone monitor, mute controls exposed by `KSMediaLiveKit`).

## B.7 Failure and fallback

| condition | behaviour | confidence | evidence |
|---|---|---|---|
| CPU pressure | `[AudioEngineImpl] NotifyObserver OnPerformanceInsufficient: %d will close commmon ns and audio quality estimation` | confirmed | `evidence/kwaiaudio_strings.txt` `0x1403b0` |
| device/sample mismatch | resampler inserted (`audio input config changed: sample_rate: %d->%d, channel_num: %d->%d`) | confirmed | `evidence/kwaiaudio_strings.txt` `0x15313b` |
| AAudio unavailable | OpenSLES implementation inside the same lib (`AudioStreamOpenSLES::open()`) | confirmed (symbols) | `evidence/kwaiaudio_strings.txt` `0x16325b` |
| mic denied / device error | `Stannis.restartRecord()` / `startAudioDevice` retry through `AudioDeviceStatusObserver` | inferred | `Stannis.java:1085-1168,1905` |

## B.8 Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| live audio is Stannis via Arya's proxy | confirmed | `AryaAudioEngineProxyImp.java:11-19,220` | — |
| frame size is 10 ms PCM16 | confirmed | `Stannis.java:314-331` | — |
| live TX sample rate | unknown (44100 or 48000 both appear) | `DaenerysConfigBuilder`, `AudioControllerImpl`, `startAudioInnerCap(48000)` | QosInfo `atxStrmSr` from a device session |
| VAD model file name & format | unknown | `SetEnableDeepVad:%d, model:%s` only | a device dump of the model dir |

## B.9 Reimplementation mapping

| stage | JAX | status |
|---|---|---|
| VAD (3-class) | not implemented | ARCHITECTURE_ONLY — model is downloaded, no input contract beyond 10 ms PCM16 |
| AEC/NS/AGC, reverb, voice change | not implemented | ARCHITECTURE_ONLY (DSP, not NN) |
| beauty/body-slim (video) | `reimpl/*` | see Pipeline A.9 |

---

# Pipeline C — live effect / model binding (vs recorded path)

## C.1 Data sources / triggers

| item | value | confidence | evidence |
|---|---|---|---|
| live effect selection | `LiveMagicEmojiPresenter`, `LiveMagicEffectDownloader`, `LiveAudioEffectFragment` (audio FX), `LivePrePushPrettifyPresenter` (beauty) | confirmed (classes) | `report.md` §3/§4; `sources/classes/live/…LiveAudioEffectFragment.java` |
| downloader | `MagicDownloadPlugin.downloadMagicFace(material)` (`LiveMagicEffectDownloader.java:332,367`) | confirmed | `sources/classes/com.yxcorp.gifshow.live.effect.LiveMagicEffectDownloader.java` |
| same resource system as record | `com.yxcorp.gifshow.util.resource.model.a` (`STICKER`, `THEME`, `EMOJI`, `EDITOR`, `VOICE_DETECT`, `CUT_MATTING`), `c` (MagicModel), MD5 check, multi-CDN | confirmed (shared doc) | `_shared_kwai_sdk.md` §5 |
| YCNN model config | `GET /rest/o/production/ycnn/models` → `YlabModelPathConfig` → `nativeSetYlabModelPathConfig` | confirmed | `report.md` §5 |

## C.2 Transport and format

Effect package = zipped AE2 bundle (zip; index + assets). The Java side passes
it to native as `byte[]` (`FaceMagicController.nativeSetEffect(byte[], int, int)`,
`nativeSendEffectCommand(byte[])`) or as a directory
(`nativeSetExternalBuiltinPath`, `nativeSetBeautifyExternalBuiltinPath`,
`ResourceManager.nativeSetPath`). Model files are passed as **paths only** —
native never receives model bytes over JNI (confirmed by the JNI signature set
of `libwesteros.so`/`libfaceless-plugin.so`).

## C.3 Preprocessing (binding)

1. Download → verify MD5 → unzip → register in `addToMagicModelsResourceList()`.
2. `ResourceManager.nativeSetYlabModelPathConfig(byte[] protobuf)` /
   `nativeSetDirtyLensDetectModelPath` / `nativeSetFace3DResourcesDir` before
   the first frame.
3. `FaceMagicController.nativeInitController`, then `nativeSetEffect` per
   selected sticker/makeup; beauty parameters go through protobuf commands
   (`EffectControl`, `EffectCommand`, `BeautifyVersion`, `BodySlimmingAdjustType`).
4. Model backends (`ycnn`, `hiai_*`, `tflite_mtk_static`, SNPE) are loaded by
   `YcnnSoLoader`/`WesterosSoLoader` on first use; `CpuInfoChecker.supportSNPE()`
   gates the Qualcomm path.

## C.4 Model invocation

Same YCNN/FaceMagic runtime as the record path (`_shared_kwai_sdk.md` §3-§5).
The difference is **who drives it per frame**:

| aspect | live push | recorded video |
|---|---|---|
| frame driver | Daenerys native pipeline → `GlPreProcessor.onDraw(VideoFrame, int)` (texture id) | `CameraControllerImpl` + `MediaRecorderImpl`/`MediaCodecEncoder` (GL→encoder surface) and, in the editor, `com.kwai.video.editorsdk2.WesterosWrapper` |
| effect controller | `FaceMagicController` created by the faceless plugin and attached to the westeros graph bound to `Daenerys` | for image/bitmap paths `YcnnPluginUtils.getFaceData(Bitmap,…)` is used instead of the live detector |
| output sink | `MediaCallback` → `Arya.inputRawVideo` | `MediaCodec` input surface / `AICut` analysis |
| timing controls | `nativeSetTimeControl`, `nativeUpdateEffectUsingFramePts`, `nativeSetRecordingSpeed` per frame | same API, driven by the recorder clock |
| model set | live needs only the faces/effects currently selected; identical download catalogue | editor additionally loads `AIEdit_*_seg.fbm` and KVE models |

**Inferred** differences (not proven by decompilation): the editor path may use
CPU/bitmap frames, while the live path always binds GL textures; and the live
path cannot afford per-frame model reloads, so `nativeSetEffect` is issued only
on selection change.

## C.5 Output postprocessing

Same callbacks as Pipeline A.5. In live, callback results are consumed only by
the renderer; the record/editor path additionally serializes them
(`getSerializedAttributes`, `WesterosFrameAttributesDumpProcessor`).

## C.6 Consumer / action

Live: effect visible in the preview and encoded stream. Record: effect baked
into the recorded file / edited output. No network upload of model outputs in
either path (only `uploadStats` telemetry strings).

## C.7 Failure and fallback

| condition | behaviour | confidence | evidence |
|---|---|---|---|
| model dir missing | `"Ycnn model directory is empty / not a directory / not exists"`, `ycnn_download_failed`, `{"missing_models":[…]}` status JSON | confirmed (strings) | `report.md` §5; `_shared_kwai_sdk.md` §4 |
| effect load failure | `FaceMagicController.FaceMagicLoadEffectFailedListener`, `onLoadFileError(String,int)` | confirmed | `FaceMagicController` method list |
| version mismatch | `getSupportMakeupVersion`, `getUnSupportVersion()[I`, `BeautifyVersion` negotiation | confirmed | `FaceMagicController` method list |
| unsafe environment | `onFaceMagicEnvironmentUnsafe(int)` / `nativeSetAioSafe` | confirmed | `FaceMagicController` method list |

## C.8 Confidence and open questions

| claim | confidence | evidence | what would settle it |
|---|---|---|---|
| native receives paths/packages, never model bytes | confirmed | JNI tables of `libwesteros.so`, `libfaceless-plugin.so` | — |
| live and record share the same effect catalogue | confirmed | class list + `assets/beauty_list` | — |
| recorded path uses bitmap/YCNN plugin variants | inferred | `YcnnPluginUtils.getFaceData(Bitmap,…)` exists; no live call site | `editorsdk2` decompilation |

## C.9 Reimplementation mapping

Identical to A.9; the download/binding stages are not reproducible offline
(no models in the APK), so they are **ARCHITECTURE_ONLY**.
