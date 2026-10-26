# Decompiled sources (jadx `--single-class`)

APK: `cust/app/customized/partner-com.kwai.kuaishou.video.live_155/partner-com.kwai.kuaishou.video.live_155.apk`
(com.kwai.kuaishou.video.live 6.0.20.524207, sha256 `642f1cbf…3acbe9`)

| Class | dex | Why |
|---|---|---|
| `classes/com.yxcorp.gifshow.live.effect.LiveMagicEffectDownloader.java` (383 lines) | classes4 | Live magic-effect/effect-package acquisition: `MagicDownloadPlugin.downloadMagicFace(material)` (line 332, 367) |
| `classes/YcnnSoLoader.java` (69 lines) | classes13 | YCNN inference backend loading: `ykit_core_plugin`, HiAI (`hiai_ir`, `hcl`, …), `tflite_mtk_static`, SNPE (`ycnn_ext_snpe`) |
| `classes/YcnnPluginUtils.java` (28 lines) | classes13 | `getFaceData(Bitmap, YCNNResourceConfig{getYcnnModelPath()}, ctx)` → `nativeGetFaceData` → `YCNNFaces` protobuf |
| `classes/com.kwai.video.westeros.helpers.WesterosSoLoader.java` (34 lines) | classes13 | `System.loadLibrary` chain for `westeros`, `c++_shared`, `ykit_core_plugin` |
| `classes/com.kwai.video.westeros.models.VisionEngine.java` | classes13 | protobuf extension registry only (the KVE engine itself is `com.kwai.kve.VisionEngine`, classes16) |

Classes whose jadx run exceeded the wrapper timeout (recorded in `logs/`), analysed from
`evidence/class_surfaces.txt` (method/descriptor dumps from the DEX) instead:
`com.kwai.video.westeros.v2.faceless.FaceMagicController` (124 methods),
`com.yxcorp.gifshow.live.push.prepush.presenter.LivePrePushPrettifyPresenter` (13),
`com.kwai.video.westeros.mmuplugin.MmuPlugin` (21),
`com.kwai.video.westeros.models.YlabModelPathConfig` (protobuf, 32),
`com.kwai.FaceMagic.nativePort.FMEffectLivePet` (30).

## Live-push patch (`classes/live/`, added 2026-10-24)

All decompiled with `jadx --single-class --show-bad-code --no-res --threads-count 2`.
`do.u`/`do.k` are printed by jadx as `p000do.*` inside the Java source but the
files keep the DEX names.

| Class | dex | Why |
|---|---|---|
| `live/com.kwai.camerasdk.Daenerys.java` (30 KB) | 15 | capture engine: native pipeline, GL pre-processors, audio processors, `setMediaCallback(fps,maxW,maxH,cb)` |
| `live/com.kwai.camerasdk.DaenerysConfigBuilder.java` | 15 | capture defaults (30 fps, 720/1280, YUV, 44100/1ch) |
| `live/do.u.java` (64 KB) | 15 | capture config protobuf: builder-letter → field → getter map used to resolve the defaults |
| `live/do.k.java`, `live/do.i.java` | 15 | `CameraOutputDataType` (Yuv=0/Texture=1/Both=2), camera-API enum |
| `live/com.kwai.camerasdk.videoCapture.CameraControllerImpl.java` (84 KB) | 15 | Camera1/Camera2 sessions, resolution slots, Camera2→Camera1 fallback, fps |
| `live/com.kwai.camerasdk.face.FaceDetectorContext.java` | 15 | YCNN detector context JNI surface (26 methods) |
| `live/com.kwai.camerasdk.render.NativeRenderThread.java` | 15 | preview EGL display thread (`nativeDrawFrame`, `nativeResizeView`) |
| `live/com.kwai.camerasdk.preprocess.GlPreProcessor.java` | 15 | GL-thread effect callback contract (`onDraw(VideoFrame,int)`) |
| `live/com.kwai.camerasdk.preprocess.AudioProcessor.java` | 15 | audio processor attached to Daenerys (`nativeAddAudioProcessor`) |
| `live/com.kwai.camerasdk.audioCapture.AudioControllerImpl.java` | 15 | AudioRecord path: 48 kHz mono PCM16, 10 ms buffers, MIC source |
| `live/com.kwai.camerasdk.encoder.MediaCodecEncoder.java` | 15 | H.264/HEVC encoder config (surface vs NV12 input, BT.601, VBR) |
| `live/com.kwai.video.stannis.AudioProcess.java` (41 KB) | 8 | Stannis audio FX/VAD Java API + every effect enum |
| `live/com.kwai.video.stannis.Stannis.java` (294 KB) | 8 | Stannis engine wrapper: 10 ms frame contract, live-stream scenes, AEC/VAD config |
| `live/com.kwai.video.arya.AryaAudioEngineProxyImp.java` (85 KB) | 8 | Arya → Stannis device proxy; live channel config |
| `live/com.kwai.video.arya.Arya$AryaConfig.java` (104 KB, jadx emitted the outer `Arya` class with its nested types) | 8 | `VideoFrameAttribute`, `nativeInputRawVideoByteBuffer`, live encode-param call, all live defaults |
| `live/com.kwai.video.ksmedialivekit.LivePushClient.java` | 8 | MediaCallback→Arya bridge, push start/stop, retry |
| `live/com.kwai.video.ksmedialivekit.KSMediaLiveKit.java` | 8 | push API contract (status codes, live types, QoS) |
| `live/com.kwai.video.ksmedialivekit.KwaiMediaLiveKit.java` | 8 | implementation delegating to the push client |
| `live/com.kwai.video.ksmedialivekit.LiveKitSoLoader.java` | 8 | native loader for the Arya/kwaivoip stack |
| `live/com.yxcorp.gifshow.live.push.stream.LivePushClient.java` (75 KB) | 8 | app push state machine: retry/backoff, pause/resume, QoS, BGM |
| `live/com.yxcorp.gifshow.live.push.LivePushActivity.java` | 8 | live entry activity / extras |
| `live/com.yxcorp.gifshow.live.push.prepush.presenter.g.java` | 8 | live preview presenter (CameraView, beauty/filter panel) |
| `live/com.yxcorp.gifshow.live.push.prepush.presenter.LivePrePushPrettifyPresenter.java` | 8 | `BeautyChangeEvent` → live beauty config |
| `live/com.yxcorp.gifshow.live.push.ui.LiveAudioEffectFragment.java` | 8 | live audio-effect panel |
| `live/com.yxcorp.gifshow.live.music.LivePushSoundEffectFragment.java` | 8 | BGM/voice mix panel |
| `live/com.yxcorp.gifshow.camerasdk.widget.CameraView.java` | 16 | preview widget (`VideoSurfaceView`, gestures) |
| `live/com.yxcorp.gifshow.camerasdk.CameraRecordPluginImpl.java` | 16 | plugin shim only (2 methods) |
| `live/com.kwai.video.westeros.Westeros.java` | 13 | westeros↔Daenerys binding, face-detector registration, model resource setters |
| `live/com.kwai.video.westeros.v2.WesterosGlProcessor.java` | 13 | westeros processor as a Daenerys `GlPreProcessor` |
| `live/com.kwai.video.westeros.GPUPerformance.java` | 13 | GPU score → Level buckets (fallback input) |
| `live/com.kwai.video.westeros.audioplugin.AudioPlugin.java` | 13 | westeros audio plugin → `libaudioplugin.so` (43 160 B, 2 JNI, needs `libksaudioprocesslib.so` + `libdaenerys.so`) |

Evidence: `evidence/live_push_trace.md`, `evidence/live_cmd.txt`,
`evidence/kwaiaudio_strings.txt`, `native/libdaenerys.so.*`,
`native/audio_voicedetect_*.s`.
