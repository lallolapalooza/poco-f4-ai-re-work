# sources index (jadx --single-class)
# APK: com.duokan.phone.remotecontroller 6.6.6R (sha256 6a955dfc5ef67f1b7cd4094108ec019d419c9e9541546d3e249fdde9354eb9a6)

| file | class | dex | why |
|---|---|---|---|
| `sources/classes/cg.k.java` | `cg.k` | classes3.dex | VoiceControlActivity fragment (voice UI), implements cg.l.f callbacks |
| `sources/classes/cg.l.java` | `cg.l` | classes3.dex | VoiceControlManager: SpeechEngine init (appId/apiKey), ASR/NLP/TTS callbacks, intent->device routing, TTS |
| `sources/classes/com.miui.voiceassist.mvs.client.MvsClientManager.java` | `com.miui.voiceassist.mvs.client.MvsClientManager` | classes2.dex | XiaoAi MVS client API |
| `sources/classes/com.xiaomi.ai.AsrRequest.java` | `com.xiaomi.ai.AsrRequest` | classes2.dex | ASR request defaults (VAD local, recorder input, opus codec modes) |
| `sources/classes/com.xiaomi.ai.SpeechEngine.java` | `com.xiaomi.ai.SpeechEngine` | classes2.dex | public SDK facade, ParamBuilder, ProcessStage, ResultParser |
| `sources/classes/com.xiaomi.ai.c.java` | `com.xiaomi.ai.c` | classes2.dex | engine implementation: AudioRecord capture loop, request params, VAD mode, TTS mp3 |
| `sources/classes/com.xiaomi.ai.mibrain.MibrainDecoder.java` | `com.xiaomi.ai.mibrain.MibrainDecoder` | classes2.dex | PCM/mp3 decoder plumbing |
| `sources/classes/com.xiaomi.ai.mibrain.MibrainNativeRequest.java` | `com.xiaomi.ai.mibrain.MibrainNativeRequest` | classes2.dex | JNI command ids and stop flags |
| `sources/classes/com.xiaomi.ai.mibrain.MibrainRequest.java` | `com.xiaomi.ai.mibrain.MibrainRequest` | classes2.dex | request context lifecycle, addData/stopRequest |
| `sources/classes/com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.java` | `com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder` | classes2.dex | VAD mode enum, auth modes, request fields |
| `sources/classes/com.xiaomi.ai.mibrain.MibrainVad.java` | `com.xiaomi.ai.mibrain.MibrainVad` | classes2.dex | JNI wrapper for the legacy SDK VAD |
| `sources/classes/com.xiaomi.ai.mibrain.MibrainVad2.java` | `com.xiaomi.ai.mibrain.MibrainVad2` | classes2.dex | JNI wrapper for CornVad NN VAD; int[6] result layout |
| `sources/classes/com.xiaomi.ai.mibrain.Mibrainsdk.java` | `com.xiaomi.ai.mibrain.Mibrainsdk` | classes2.dex | System.loadLibrary(mibrainsdk/mibrainjni) |
| `sources/classes/com.xiaomi.ai.utils.UploadHelper.java` | `com.xiaomi.ai.utils.UploadHelper` | classes2.dex | helper |
| `sources/classes/com.xiaomi.mitv.phone.remotecontroller.common.ui.SpeechVolumeView.java` | `com.xiaomi.mitv.phone.remotecontroller.common.ui.SpeechVolumeView` | classes2.dex | mic level UI |
| `sources/classes/com.xiaomi.mitv.phone.remotecontroller.voice.VoiceControlReceiver.java` | `com.xiaomi.mitv.phone.remotecontroller.voice.VoiceControlReceiver` | classes3.dex | manifest receiver for voice_control_action |
| `sources/classes/wf.e0$a.java` | `wf.e0$a` | classes3.dex | helper |
| `sources/classes/wf.e0.java` | `wf.e0` | classes3.dex | voice-control settings helper (VOICECONTROL flag) |

Note: jadx --single-class writes the *outer* class when an inner class name is requested;
inner classes (cg.l$a etc.) are contained in cg.l.java.

## Patch 2026-10-24 — audio/response-path classes (PIPELINE.md)

| file | class | dex | why |
|---|---|---|---|
| `sources/classes/com.xiaomi.ai.i.java` | `com.xiaomi.ai.i` | classes2.dex | turn setup thread: MibrainRequest + native context + recorder start |
| `sources/classes/com.xiaomi.ai.l.java` | `com.xiaomi.ai.l` | classes2.dex | audio-queue drain runnable (`take()` -> `addAudioData`) |
| `sources/classes/com.xiaomi.ai.j.java` | `com.xiaomi.ai.j` | classes2.dex | stream-player -> SpeechEngine TTS callback bridge |
| `sources/classes/com.xiaomi.ai.m.java` | `com.xiaomi.ai.m` | classes2.dex | OAuth/token helper (unused: app uses APPIDTOKEN auth) |
| `sources/classes/com.xiaomi.ai.PCMInfo.java` | `com.xiaomi.ai.PCMInfo` | classes2.dex | TTS PCM frame handed to `TtsListener.onPCMData` |
| `sources/classes/com.xiaomi.ai.streamplayer.k.java` | `...streamplayer.k` | classes2.dex | TTS player: AudioTrack / PCM-callback modes, 40 ms poll loop |
| `sources/classes/com.xiaomi.ai.streamplayer.a.java` | `...streamplayer.a` | classes2.dex | decoder base + decoded-frame struct |
| `sources/classes/com.xiaomi.ai.streamplayer.f.java` | `...streamplayer.f` | classes2.dex | soft MP3 decoder (PipedStream -> libmad) |
| `sources/classes/com.xiaomi.ai.streamplayer.g.java` | `...streamplayer.g` | classes2.dex | decoder I/O bridge (decoded PCM -> queue) |
| `sources/classes/com.xiaomi.ai.streamplayer.h.java` | `...streamplayer.h` | classes2.dex | decoder thread body |
| `sources/classes/com.xiaomi.ai.streamplayer.d.java` | `...streamplayer.d` | classes2.dex | MediaCodec hard decoder (not selected: `sUseHardDecoder=false`) |
| `sources/classes/com.xiaomi.ai.streamplayer.TTSDecoderConfig.java` | `...TTSDecoderConfig` | classes2.dex | `sUseHardDecoder` switch |
| `sources/classes/com.xiaomi.ai.mibrain.MibrainMp3Decoder.java` | `...MibrainMp3Decoder` | classes2.dex | JNI wrapper for libmibraindec (libmad) |
| `sources/classes/com.xiaomi.ai.utils.e.java` | `com.xiaomi.ai.utils.e` | classes2.dex | short[]<->byte[] s16le conversion |
| `sources/classes/com.xiaomi.ai.utils.n.java` | `com.xiaomi.ai.utils.n` | classes2.dex | RMS level for the mic meter |
| `sources/classes/cg.a.java` | `cg.a` | classes3.dex | AC intent handler -> KKACManagerV2 -> IR |
| `sources/classes/cg.b.java` | `cg.b` | classes3.dex | fan intent handler |
| `sources/classes/cg.f.java` | `cg.f` | classes3.dex | STB / tvChannelControl handler |
| `sources/classes/cg.g.java` | `cg.g` | classes3.dex | TV intent handler (volume/navigation/channel) |
| `sources/classes/ee.j.java` | `ee.j` | classes3.dex | device wrapper; `E(String)` is the IR output funnel |
| `sources/classes/re.g.java` | `re.g` | classes3.dex | IR blaster facade -> ConsumerIrManager.transmit / Miir.d |
| `sources/classes/vd.d.java` | `vd.d` | classes3.dex | env/version switch + `g()` accessor for `re.g` |
| `sources/classes/com.xiaomi.onetrack.api.b.java` | `...onetrack.api.b` | classes2.dex | constant `I = "response"` (JSON envelope key) |
| `sources/classes/rg.j.d.java` | `rg.j.d` | classes2.dex | constant `f52974k = "error_msg"` |
| `sources/classes/com.xiaomi.mitv.phone.remotecontroller.remotecall.RemoteCallHandlerActivity.java` | `...RemoteCallHandlerActivity` | classes2.dex | constant `f20304f = "query"` |
| `sources/classes/com.xiaomi.onetrack.OneTrack$Param.java` | `...OneTrack$Param` | classes2.dex | constant `SESSION_ID = "session_id"` (NLP session key) |
