# Live push call trace (video / audio / effects)

All line numbers refer to files under `reports/kwai_live/sources/classes/live/`
(jadx `--single-class`, `--show-bad-code --no-res`). Native offsets refer to
`reports/kwai_live/inventory/libs/lib/arm64-v8a/*.so` (imagebase 0).

---

## 1. Video: UI → camera engine

```
LivePushActivity.K0(FragmentActivity, jl0.a)                       LivePushActivity.java:33-46
  extras: livePushConfig / livePushBeautyConfig / livePushFILTERConfig /
          liveFrontCamera / liveTitle / liveNotifyFollower / streamType
  → SingleFragmentPageActivity (fragment m)

prepush presenter g.N0() -> CameraView                             ...prepush.presenter.g.java:330-343
  CameraView(Context, AttributeSet)  → VideoSurfaceView + gesture    CameraView.java:32-44,354
  g.onDestroy()  → releases camera view                              g.java:397-410
```

`CameraView` holds `VideoSurfaceView` only; the engine is
`com.kwai.camerasdk.Daenerys` (`libdaenerys.so`, 138 JNI, sha256
`3b0758bf…b146ee`):

```
Daenerys(ctx, z, EglBase.Context)                                  Daenerys.java:316
  nativeCreateWesteros-like ctor: nativeInit(j, byte[] config, Object)
  nativeSetCameraController(j, cameraControllerNative)
  nativeSetMediaCallback(j, fps, maxW, maxH, downSampleType, cb)   Daenerys.java:503
  nativeSetFaceDetectorContext(j, fdctx, type)                     Daenerys.java:495 (via X())
  nativeAddAudioProcessor(j, proc, boolean)                        Daenerys.java:442
  nativeAddGLPreProcessorAtGroup / nativeInsertGLPreProcessorGroupAtSlot
                                                                    Daenerys.java:446,469
  nativeCreateSubPipeline / nativeGetRenderThread / nativeGetMediaRecorder
```

## 2. Video: capture config and formats

`DaenerysConfigBuilder.defaultCaptureConfigBuilder()` (all values are
builder-method literals; letter→field mapping recovered from `do.u.java`):

```
.e(p000do.i.kAndroidCameraAuto)   camera API auto (Camera2 → Camera1 fallback)
.t(false) .M(30) .N(0)            target fps 30, min fps 0
.J(720) .G(1280) .H(1280) .I(360) size candidates
.K(44100) .n(1) .d(2)             audio 44100 Hz, 1 ch, 2 bytes/sample
.f(p000do.k.kCameraOutputDataTypeYuv)   output = YUV (enum 0)
.k(x.kStabilizationModeOff) .l(x.kStabilizationModeOff)
.g/h(m.kCameraPreviewStream) .q(v.kEdgeModeDefault) .L(2000)
```

Field resolution (from `do.u.java`, `b` builder methods at 212-311 and setters
`i1..u1` at 618-680):

| builder | setter | field | getter | value |
|---|---|---|---|---|
| `E(int)` | `l1` | `f64804q` | `t0()` | 0 |
| `F(int)` | `m1` | `f64803p` | `u0()` | 0 |
| `G(int)` | `n1` | `f64779c` | `v0()` | 1280 |
| `H(int)` | `o1` | `f64781d` | — | 1280 |
| `I(int)` | `p1` | `f64783e` | — | 360 |
| `J(int)` | `q1` | `f64777b` | `y0()` | 720 |
| `K(int)` | `r1` | `f64787g` | — | 44100 |
| `M(int)` | `t1` | `f64785f` | `C0()` | 30 |
| `N(int)` | `u1` | — | `D0()` | 0 |

`CameraControllerImpl.initWithDaenerysCaptureConfig(u)` (line 1626) then maps:

```
resolutionRequest.a = f(u0=0,  t0=0)      // preview size = auto
resolutionRequest.b = f(y0=720, v0=1280)  // capture size = 720x1280
resolutionRequest.c = f(W, V)  = (0,0)
nativeUpdateTargetFps(nativeCameraController, C0()=30)
mirrorFrontCamera   = config.q0()
```

`Daenerys.b0(fps, maxW, maxH, MediaCallback)` (line 747) → `c0(...)` (752) →
`nativeSetMediaCallback` with `e0.kDownSamplerTypeUnknow` when called via `b0`.
`LivePushClient` (ksmedialivekit) calls `daenerys.b0(config.s(), config.u(),
config.v(), mediaCallback)` at line 408.

## 3. Video: frame → encoder

```
com.kwai.camerasdk.MediaCallback.onVideoFrame(VideoFrame)          (abstract)
  ↳ com.kwai.video.ksmedialivekit.LivePushClient$1.onVideoFrame     ksmedialivekit/LivePushClient.java:84-89
      arya.inputRawVideo(videoFrame.data.byteBuffer,
          new Arya.VideoFrameAttribute(
              videoFrame.yuv_format, videoFrame.width, videoFrame.height,
              videoFrame.timestamp, videoFrame.attributes.f().m() /*rotation*/,
              videoFrame.attributes.b() /*csp*/, ""))
  ↳ Arya.inputRawVideo(ByteBuffer, VideoFrameAttribute)             Arya$AryaConfig.java:2288
      nativeInputRawVideoByteBuffer(handle, format, buf, w, h, ts,
                                    rotation, csp, rois, sourceId, mirror)
```

`Arya.VideoFrameAttribute` fields (same file, lines 2323-2350): `format`,
`width`, `height`, `timestamp`, `rotation`, `csp`, `rois`, `sourceId`.

Encoder parameters are native (`Arya$AryaConfig.java:1443`):

```
nativeSetLiveStreamVideoEncodeParam(handle, width, height, fps, maxBitrate,
                                    resumeOnRtcStop)
nativeSetLiveStreamRtmpUrl(handle, url)
```

`AryaConfig` defaults (`Arya$AryaConfig.java:351-419`): `videoTargetFps=15`,
`videoMinFpsForAdapt=10`, `videoTargetWidth=360`, `videoTargetHeight=640`,
`videoEnableCrop=false`, `videoKeyFrameInterval=4`, `videoMinBitrateKbps=200`,
`videoDropBefEncAvgFpsToNotify=5`, `enableAudioVad=true`,
`enableAudioPreProcess=true`, `videoEnableHwEnc/HwDec=false`.

Video clock: `VideoFrame.timestamp` forwarded verbatim. Frame-drop accounting
is native; the app exposes it via `LivePushClient.getDroppedVideoFrames()` →
`QosInfo.getDroppedVideoFrames()` (`stream/LivePushClient.java:922`).

Preview is a separate EGL display pass (`NativeRenderThread.nativeDrawFrame`,
`:36`) fed from the same effected texture; the encoder gets the YUV readback
path described above.

## 4. Where face detection / FaceMagic sit

```
Westeros.setFaceDetectorContext(FaceDetectorContext ctx, int type)  Westeros.java:341-350
    daenerys.X(ctx, type)                                          Daenerys.java:693-719
        → nativeSetFaceDetectorContext(daenerys, ctx, type)
    nativeSetFaceDetectorContext(westeros, ctx.getNativeContext(),
                                 ctx.getNativeSensorManager(), type)
```

`libfacedetectcontext.so` JNI (26): `nativeInit(1632 B @0x184b0)`,
`nativeSetData(304 @0x18e28)`, `nativeDetectFacesFromFrame(1312 @0x1a19c)`,
`nativePrepare(740 @0x1af34)`, `nativeResetVideoDetector(192 @0x1b218)`,
`nativeSetFaceDetectConfig(1012 @0x1976c)`.

The effect processor is a Daenerys GL pre-processor:

```
WesterosGlProcessor.toDaenerysProcessor()  → AbstractGlProcessor wrapper     WesterosGlProcessor.java:50-62
GlPreProcessor.onDraw(VideoFrame, int textureId)  // GL thread callback       GlPreProcessor.java:39
GlPreProcessor.post(Runnable) → nativePostRunnableToGlThread                 GlPreProcessor.java:43-50
FaceMagicController.nativeGetGlProcessor(handle)                             libfaceless-plugin.so @0x3d6f0
```

`FaceMagicController` (124 methods) native surface: `nativeInitController`,
`nativeSetEffect(byte[],int,int)`, `nativeSetEffectEnable`, `nativeUpdateEffectControl`,
`nativeSendEffectCommand(Sync|Batch)`, `nativeUpdateEffectUsingFramePts`,
`nativeSetInitialVideoFrameSize`, `nativeSetTimeControl`, `nativeSetRecordingSpeed`.
Callbacks into Java: `onReceivedBokehMask(byte[],int,int)`,
`onReceivedMemojiIcon(ByteBuffer,int,int)`, `onEffectDescriptionFromNative`,
`onEffectHintFromNative`, `onEffectPlayCompleted(int,int)`,
`onFaceMagicEnvironmentUnsafe(int)`, `onLoadFileError(String,int)`.

## 5. Audio: mic → Stannis → Arya

```
Arya.startLiveStream(LiveStreamParam, url)                Arya$AryaConfig.java (nativeStartLiveStream)
  ↳ AryaAudioEngineProxyImp (extends com.kwai.video.arya.a)     AryaAudioEngineProxyImp.java:11-19,220
      Stannis.getInstance() ; setStannisConfig(
          audioChannel=2, audioOutputChannel=2,
          enableAudioVad=AryaConfig.enableAudioVad,
          qosFlag/qosUploadInterval/dumpPath, …)               :1150-1162
      stannisConfig.audioChannel = z ? 2 : 1                  :1178-1182
      startKaraokeVad(lyrics)                                  :1140
      getKaraokeVadDurationResult(i)                           :856
      QosInfo JSON: atxRecSr/atxStrmSr/atxStrmBytes/…          :1321-1322
```

Stannis 10 ms contract (`Stannis.java`):

```
processData(byte[] b):
    int need = (sample_rate * channel_num * 2) / 100;   // :323
    if (b.length != need) { log "… is not 10ms …"; return; }   // :329
getSamplePerFrame() = sample_rate * channel_num / 100  // :314-316
```

Voice detection (`AudioProcess.AudioVoiceDetectInterface`, `AudioProcess.java:271-311`):

```
new AudioVoiceDetectInterface(sampleRate, channelNum, modelPath)
    nativeAudioVoiceDetectInterface(int,int,String)        libkwaiaudio.so @0x12a49c
process(byte[] pcm, int len) → AudioVoiceDetectResult
    nativeAudioVoiceDetectProcess(long, byte[], int, float[3])   @0x12a56c
    returns 1 when available; writes 3 floats at [x25],[x25+4],[x25+8]
    fields: result_available, backgroud_prob, speech_prob, music_prob
```

Camera-SDK audio (when the camera pipeline owns the mic):

```
AudioControllerImpl:  BUFFERS_PER_SECOND=100, CALLBACK_BUFFER_SIZE_MS=10,
                      BITS_PER_SAMPLE=16, BUFFER_SIZE_FACTOR=2      :24-27
  ctor: sampleRate=48000, channels=1                                :176-177
  initRecording(sr, ch): AudioRecord(0=MIC, sr, channelIn, 2=PCM16, minBuf*2)  :197-245
  read loop: publishMediaFrame(new AudioFrame(bytes, channels, sampleRate, ts)):144
             ts = t0 + frames*1000/sampleRate                        :137
AudioProcessor (nativeAddAudioProcessor): onAudioFrame(AudioFrame)   AudioProcessor.java:23
```

## 6. Lifecycle / reconnect / fallback

```
stream/LivePushClient.q0()  → startLive(liveStreamId).retryWhen(class a)  :1165-1176
class a:  err 1016013014 → stop (empty); 1016013015 → fatal;
          else retry timer 2000 ms while f45793s-- > 0                    :162-197
class m (MediaLiveStatusListener):
          4 ERROR   → PushStateChangeEvent(d1,false), stop bgm, restart event
          2 PUSH_SUCCESS → A(0), preLiving → q0(), event s,true
          3 RETRYING → onRestartPush(), record restart time           :452-488
S0():  kSMediaLiveKit.startPush(daenerys, PHONE_AUDIO ? 2 : 1)         :638-642
r0():  stopPush → destroy → daenerys.U(null) → daenerys.h0(true)       :1178-1206
s0():  kSMediaLiveKit.pause(); background flag                       :1208-1216
t0():  kSMediaLiveKit.resume()                                       :1218-1226
```

Camera fallback: `CameraControllerImpl` reopens with Camera1 once
(`cameraApiVersion = kAndroidCamera1; reOpenedCamera2v1 = true`, lines 882-886)
and uses `RetryStartPreviewHelper` (line 1050).

GPU/NPU fallback: `GPUPerformance.getDeviceGPUScore()` maps `GL_RENDERER` to
`Level{unknown,bad,low,medium,high,best}` with cut points 0/50/100/200/500
(lines 24-47); YCNN backends are optional libraries loaded by `YcnnSoLoader`.

## 7. Effect/model binding (live vs record)

```
LiveMagicEffectDownloader → MagicDownloadPlugin.downloadMagicFace(material)
    sources/classes/com.yxcorp.gifshow.live.effect.LiveMagicEffectDownloader.java:332,367
FaceMagicController.nativeSetEffect(byte[] pkg, int a, int b)
FaceMagicController.nativeSetExternalBuiltinPath(String)
ResourceManager.nativeSetYlabModelPathConfig(byte[])      libwesteros.so @0x3c700
ResourceManager.nativeSetDirtyLensDetectModelPath(String)  libwesteros.so @0x3d170
ResourceManager.nativeSetFace3DResourcesDir(String)        libwesteros.so @0x3cfd4
```

Recorded/editor path differs in the frame driver: `MediaRecorderImpl` +
`MediaCodecEncoder` (surface input, `createInputSurface()`), and bitmap-based
`YcnnPluginUtils.getFaceData(Bitmap, YCNNResourceConfig, Context)` instead of
the Daenerys video detector. Same model catalogue and same JNI resource setters.
