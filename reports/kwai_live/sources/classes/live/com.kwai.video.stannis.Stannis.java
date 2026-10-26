package com.kwai.video.stannis;

import android.content.Context;
import android.media.projection.MediaProjection;
import android.os.Build;
import com.kuaishou.client.log.event.packages.nano.ClientEvent;
import com.kwai.video.stannis.annotations.CalledFromNative;
import com.kwai.video.stannis.audio.StannisAudioCommon;
import com.kwai.video.stannis.audio.StannisAudioManagerInterface;
import com.kwai.video.stannis.audio.impl.HisenseAudioManager;
import com.kwai.video.stannis.audio.impl.StannisAudioManager;
import com.kwai.video.stannis.audio.support.HuaweiAudioKitHelper;
import com.kwai.video.stannis.observers.AudioBufferPlayObserver;
import com.kwai.video.stannis.observers.AudioFrameObserver;
import com.kwai.video.stannis.observers.AudioFrameObserverWrapper;
import com.kwai.video.stannis.observers.AudioInfoObserver;
import com.kwai.video.stannis.observers.AudioInfoObserverWrapper;
import com.kwai.video.stannis.observers.AudioSegmentPlayerObserver;
import com.kwai.video.stannis.observers.BgmObserver;
import com.kwai.video.stannis.observers.DataProviderObserver;
import com.kwai.video.stannis.observers.DataReadyObserver;
import com.kwai.video.stannis.observers.FingerprintObserver;
import com.kwai.video.stannis.observers.KaraokeScoreObserver;
import com.kwai.video.stannis.observers.RecordFileObserver;
import com.kwai.video.stannis.observers.StannisAudioDeviceStatusObserver;
import com.kwai.video.stannis.observers.StannisAudioSceneObserver;
import com.kwai.video.stannis.observers.StannisLogObserver;
import com.kwai.video.stannis.observers.StannisNotifyObserver;
import com.kwai.video.stannis.observers.StannisQosObserver;
import com.kwai.video.stannis.utils.ContextUtils;
import com.kwai.video.stannis.utils.Log;
import com.kwai.video.stannis.utils.NativeLoader;
import com.yxcorp.bugly.CrashReporter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes8.dex */
public class Stannis {
    public static final Map<Integer, Integer> AUDIO_OUTPUT_ROUTER_MAP;
    public static final int AUDIO_PLUGIN_BLUETOOTH = 262144;
    public static final int AUDIO_PLUGIN_LINE = 131072;
    public static final int AUDIO_PLUGIN_NONE = 65536;
    public static final int AUDIO_PLUGIN_RECEIVER = 327680;
    public static final Map<Integer, Integer> AUDIO_PLUGIN_ROUTER_MAP;
    public static final int AUDIO_PLUGIN_USB = 196608;
    public static final int Bypass = 2;
    public static final int Chat = 1;
    public static final int ChatMixBgm = 3;
    public static final int KAudioLiveChatMixBgmOut = 1024;
    public static final int KAudioLiveChatOut = 768;
    public static final int KAudioLiveStreamOut = 512;
    public static final int KAudioMultipleStreamMixOut = 1536;
    public static final int KWStannisAgcDefault = 0;
    public static final int KWStannisAgcDisable = 2;
    public static final int KWStannisAgcEnable = 1;
    public static final int KWStannisAudioChannelMono = 1;
    public static final int KWStannisAudioChannelStereo = 2;
    public static final int KWStannisAudioRouterBuiltinMic = 20;
    public static final int KWStannisAudioRouterEarpiece = 1;
    public static final int KWStannisAudioRouterHdmi = 7;
    public static final int KWStannisAudioRouterHeadset = 0;
    public static final int KWStannisAudioRouterHeadsetBluetooth = 5;
    public static final int KWStannisAudioRouterHeadsetNoMic = 2;
    public static final int KWStannisAudioRouterInput = 1;
    public static final int KWStannisAudioRouterLoudspeaker = 4;
    public static final int KWStannisAudioRouterOutput = 0;
    public static final int KWStannisAudioRouterSpeakerphone = 3;
    public static final int KWStannisAudioRouterUnknown = -1;
    public static final int KWStannisAudioRouterUsb = 6;
    public static final int KWStannisAutoMixBgmFeatureIndex = 0;
    public static final int KWStannisAutoMixFeatureLength = 20;
    public static final int KWStannisAutoMixVocalFeatureIndex = 1;
    public static final int KWStannisBypassDataWithAEC = 1;
    public static final int KWStannisDeviceError = 1;
    public static final int KWStannisHowlingDetect = 1;
    public static final int KWStannisHowlingDisable = 0;
    public static final int KWStannisHowlingSuppression = 2;
    public static final int KWStannisInputTypeAuto = 0;
    public static final int KWStannisInputTypeBluetooth = 4;
    public static final int KWStannisInputTypeBuiltinMic = 1;
    public static final int KWStannisInputTypeHeadset = 2;
    public static final int KWStannisInputTypeUSB = 3;
    public static final int KWStannisKaraokeLastScoreLength = 2;
    public static final int KWStannisKaraokeLastScoreRowIndex = 0;
    public static final int KWStannisKaraokeLastScoreScoreIndex = 1;
    public static final int KWStannisMuteDisable = 0;
    public static final int KWStannisMuteEnable = 1;
    public static final int KWStannisMuteEnableAndFillComfortableNoise = 2;
    public static final int KWStannisMutePipeLine = 3;
    public static final int KWStannisOutputTypeAuto = 0;
    public static final int KWStannisOutputTypeBluetooth = 5;
    public static final int KWStannisOutputTypeHDMI = 6;
    public static final int KWStannisOutputTypeLine = 3;
    public static final int KWStannisOutputTypeReceiver = 2;
    public static final int KWStannisOutputTypeSpeaker = 1;
    public static final int KWStannisOutputTypeUSB = 4;
    public static final int KWStannisQosStreamChat = 1;
    public static final int KWStannisQosStreamLive = 0;
    public static final int KWStannisSoftAecDefault = 0;
    public static final int KWStannisSoftAecDisable = 2;
    public static final int KWStannisSoftAecEnable = 1;
    public static final int KWStannisSoftAecLight = 4;
    public static final int KWStannisSoftAecMovieRecord = 3;
    public static final int KWStannisSuccess = 0;
    public static final int QOS_DISABLE_ALL = 0;
    public static final int QOS_ENABLE_DEBUG_INFO = 4;
    public static final int QOS_ENABLE_REALTIME = 1;
    public static final int QOS_ENABLE_SUMMARY = 2;
    public static final int QOS_ENABLE_UPLOAD_LOG = 8;
    public static final int QOS_TYPE_DEBUGINFO = 3;
    public static final int QOS_TYPE_REALTIME = 1;
    public static final int QOS_TYPE_SUMMARY = 2;
    public static final int QOS_TYPE_UNKNOWN = 0;
    public static final int QOS_UPLOAD_INTERVAL_DEFAULT = 10000;
    private static final String StannisAppHisense = "com.kwai.hisense";
    private static final String StannisAppSedna = "com.xiaosenmusic.sedna";
    public static final int Stream = 0;
    private static final String TAG = "Stannis";
    private static Stannis instance = null;
    public static final int kBitmapARGB = 1;
    public static final int kBitmapRGBA = 0;
    public static final int kChatOnly = 1536;
    public static final int kDeviceAndroid = 2;
    public static final int kGameChat = 1024;
    public static final int kInnerHiFi = 3072;
    public static final int kInterphone = 2304;
    public static final int kLevelDebug = 0;
    public static final int kLevelError = 3;
    public static final int kLevelInfo = 1;
    public static final int kLevelNone = 4;
    public static final int kLevelWarn = 2;
    public static final int kLiveChat = 768;
    public static final int kLiveGroupChat = 2048;
    public static final int kLiveKtvChat = 2560;
    public static final int kLiveStream = 512;
    public static final int kLiveStreamWithChat = 1792;
    public static final int kMovieRecord = 256;
    public static final int kPlayAudio = 1280;
    public static final int kRecordOnly = 2816;
    public static final int kReverbAmazing = 14;
    public static final int kReverbAmazing2 = 15;
    public static final int kReverbBathRoom = 7;
    public static final int kReverbChorus = 1;
    public static final int kReverbClassic = 2;
    public static final int kReverbConcert = 11;
    public static final int kReverbHeavy = 4;
    public static final int kReverbKTV = 6;
    public static final int kReverbLight = 12;
    public static final int kReverbNone = 0;
    public static final int kReverbOldTimeRadio = 16;
    public static final int kReverbPop = 3;
    public static final int kReverbProfessional = 18;
    public static final int kReverbRecord = 8;
    public static final int kReverbReverb = 5;
    public static final int kReverbStage = 10;
    public static final int kReverbStudio = 9;
    public static final int kReverbSuperStar = 13;
    public static final int kReverbUserDefine = 17;
    public static final int kRoleFemale = 2;
    public static final int kRoleGeneral = 0;
    public static final int kRoleMale = 1;
    public static final float kStannisDefaultCompressGain = -15.0f;
    public static final float kStannisKtvModeCompressGain = -15.0f;
    public static final int kVeoBadBoy = 7;
    public static final int kVeoBoy = 17;
    public static final int kVeoCute = 13;
    public static final int kVeoDenon = 10;
    public static final int kVeoDieFat = 6;
    public static final int kVeoEcho = 1;
    public static final int kVeoHeavyMechinery = 11;
    public static final int kVeoHeavyMetal = 9;
    public static final int kVeoLorie = 4;
    public static final int kVeoNone = 0;
    public static final int kVeoPilot = 14;
    public static final int kVeoPowerCurrent = 12;
    public static final int kVeoRobot = 3;
    public static final int kVeoThriller = 2;
    public static final int kVeoUncle = 5;
    public static final int kVeoUserDefine = 15;
    public static final int kVeoVibrato = 16;
    public static final int kVeoWaWaYin = 18;
    public static final int kVeoXiaoHuangRen = 8;
    public static String testAppName;
    private int audioDeviceScene;
    private AudioFrameObserverWrapper audioFrameObserverWrapper;
    private AudioInfoObserverWrapper audioInfoObserverWrapper;
    private AudioRouteListener audioRouteListener;
    private Context context;
    private DataReadyObserver dataReadyObserver;
    private StannisNotifyObserver notifyObserver;
    private StannisQosObserver qosObserver;
    private long recordSketchIntervalBegin;
    private long recordSketchIntervalEnd;
    private StannisAudioManagerInterface stannisAudioManager;
    private boolean isLiveSteamStarted = false;
    private boolean isChatStreamStarted = false;
    private boolean isAudioDeviceStarted = false;
    private boolean isPipeLineStarted = false;
    private int muteType = 0;
    private boolean isChatBgmMuted = false;
    private boolean isLiveStreamBgmMuted = false;
    private ReentrantLock audioManagerLock = new ReentrantLock();
    private boolean audioRecordStartedByUser = false;
    private boolean audioRecordEnabled = true;
    private HashSet<Long> userIdSet = new HashSet<>();
    private long userId = 0;
    private ConcurrentLinkedQueue<StannisAudioSceneObserver> audioSceneObservers = new ConcurrentLinkedQueue<>();
    private boolean userSetSpeakerOn = true;
    private volatile int userSetOutputType = 0;
    private KWStannisConfig audio_confg = new KWStannisConfig();
    private KWStannisAudioLib audioProcessLib = null;
    public long nativeStannis = nativeCreateStannis(Build.VERSION.SDK_INT);

    @Retention(RetentionPolicy.SOURCE)
    public @interface AUDIO_INPUT_ROUTER {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AUDIO_OUTPUT_ROUTER {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AUDIO_PLUGIN {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AUDIO_ROUTER_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AUDIO_SCENE {
    }

    public class AudioChangeParam {
        public float semitone = 0.0f;
        public float timbre = 0.0f;
        public float param3 = 0.0f;
        public float param4 = 0.0f;
        public float param5 = 0.0f;
        public float param6 = 0.0f;
        public float param7 = 0.0f;
        public float param8 = 0.0f;
        public float param9 = 0.0f;
        public float param10 = 0.0f;

        public AudioChangeParam() {
        }
    }

    public interface AudioRouteListener {
        void onRouteChange(int i12, int i13);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BITMAP_COLOR_SPACE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface INPUT_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisAgcMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisAudioChannelType {
    }

    public class KWStannisAudioLib {
        private AudioChangeParam audio_change_param;
        private long nativeAgcPtr = 0;
        private long nativeDspPtr = 0;
        private long nativeTimbrePtr = 0;
        private int current_reverb_level = 0;
        private int current_voice_effect = 0;
        private int current_vocal = 0;
        private int sample_rate = 0;
        private int channel_num = 0;
        private int enable_ns = 1;
        private int ns_level = -8;
        private int agc_max_gain = 20;
        private int agc_increment = 12;
        private int agc_target = 32767;
        public int reverb_level = 0;
        public int voice_effect = 0;
        public int vocal = 0;
        public boolean nativeInited = false;

        public KWStannisAudioLib() {
        }

        private void UpdateConfig() {
            this.current_reverb_level = this.reverb_level;
            this.current_voice_effect = this.voice_effect;
            this.current_vocal = this.vocal;
            Stannis.this.nativeAudioProcessSetAgcParam(this.nativeAgcPtr, this.enable_ns, this.ns_level, this.agc_max_gain, this.agc_increment, this.agc_target);
            Stannis.this.nativeAudioProcessSetDspParam(this.nativeDspPtr, this.current_reverb_level, this.current_voice_effect);
            Stannis.this.nativeAudioProcessSetVocalParam(this.nativeTimbrePtr, this.current_vocal);
        }

        public int getSamplePerFrame() {
            return (this.sample_rate * this.channel_num) / 100;
        }

        public void initNative() {
            this.nativeAgcPtr = Stannis.this.nativeAudioProcessCreateAgcInterface(this.sample_rate, this.channel_num);
            this.nativeDspPtr = Stannis.this.nativeAudioProcessCreateDspInterface(this.sample_rate, this.channel_num);
            this.nativeTimbrePtr = Stannis.this.nativeAudioProcessCreateTimbreInterface(this.sample_rate, this.channel_num);
            this.nativeInited = true;
        }

        public boolean isNativeInited() {
            return this.nativeInited;
        }

        public void processData(byte[] bArr) {
            int i12 = ((this.sample_rate * this.channel_num) * 2) / 100;
            if (bArr.length != i12) {
                Log.i(Stannis.TAG, "Stannis AudioProcessLib ProcessData is not 10ms. length=" + bArr.length + ", sr=" + this.sample_rate + ", ch=" + this.channel_num);
                return;
            }
            int i13 = this.current_reverb_level;
            int i14 = this.reverb_level;
            if (i13 != i14 || this.current_voice_effect != this.voice_effect) {
                this.current_reverb_level = i14;
                int i15 = this.voice_effect;
                this.current_voice_effect = i15;
                Stannis.this.nativeAudioProcessSetDspParam(this.nativeDspPtr, i14, i15);
            }
            int i16 = this.current_vocal;
            int i17 = this.vocal;
            if (i16 != i17) {
                this.current_vocal = i17;
                Stannis.this.nativeAudioProcessSetVocalParam(this.nativeTimbrePtr, i17);
            }
            Stannis.this.nativeAudioProcessData(this.nativeAgcPtr, this.nativeDspPtr, this.nativeTimbrePtr, bArr, i12 / 2);
        }

        public void setAudioChangeParam(AudioChangeParam audioChangeParam) {
            long j12 = this.nativeDspPtr;
            if (j12 != 0) {
                Stannis.this.nativeAudioProcessSetVcoParameters(j12, audioChangeParam);
            }
        }

        public void setChannelNum(int i12) {
            this.channel_num = i12;
        }

        public void setEnableNs(boolean z12) {
            this.enable_ns = z12 ? 1 : 0;
            long j12 = this.nativeAgcPtr;
            if (j12 != 0) {
                Stannis.this.nativeAudioProcessSetAgcParam(j12, z12 ? 1 : 0, this.ns_level, this.agc_max_gain, this.agc_increment, this.agc_target);
            }
        }

        public void setSampleRate(int i12) {
            this.sample_rate = i12;
        }

        public void uninitNative() {
            long j12 = this.nativeAgcPtr;
            if (j12 != 0) {
                Stannis.this.nativeAudioProcessDestroyAgcInterface(j12);
                this.nativeAgcPtr = 0L;
            }
            long j13 = this.nativeDspPtr;
            if (j13 != 0) {
                Stannis.this.nativeAudioProcessDestroyDspInterface(j13);
                this.nativeDspPtr = 0L;
            }
            long j14 = this.nativeTimbrePtr;
            if (j14 != 0) {
                Stannis.this.nativeAudioProcessDestroyTimbreInterface(j14);
                this.nativeTimbrePtr = 0L;
            }
            this.nativeInited = false;
        }
    }

    public class KWStannisBgmGuidance {
        public int offset;
        public int type;
        public String url;

        public KWStannisBgmGuidance(int i12, String str, int i13) {
            this.type = 0;
            this.url = "";
            this.offset = 0;
            this.type = i12;
            this.url = str;
            this.offset = i13;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisBypassDataOptions {
    }

    public class KWStannisConfig {
        public String dumpPath = "";
        public int dumpFlag = 0;
        public int qosFlag = 3;
        public int qosUploadInterval = 10000;
        public boolean enableAudioVad = false;
        public long activeSpeakerHangOverInterval = 500;
        public boolean useExternalDevice = false;
        public int audioChannel = 1;
        public int audioOutputChannel = 1;

        public KWStannisConfig() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisHowlingSuppressionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisMuteType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisQosStreamType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisResult {
    }

    public class KWStannisServerConfig {

        @Deprecated
        public boolean enableSoftAec;
        public boolean enableDevAec = false;
        public int softAecNlp = 21;
        public boolean enableAecHighQuality = true;
        public boolean enableGroupDevAec = false;
        public int groupSoftAecNlp = 21;
        public boolean enableGroupAecHighQuality = true;
        public boolean forceAec = false;
        public int forceAecNlp = 18;
        public int roundTripLatency = ClientEvent.UrlPackage.Page.PC_LIVEMATE_CAMERA_SETTING_PAGE;
        public int chatRoundTripLatency = ClientEvent.UrlPackage.Page.PC_LIVEMATE_CAMERA_SETTING_PAGE;
        public boolean liveStreamMixBgm = false;
        public boolean ktvVendorSupport = true;
        public int deviceType = 0;
        public boolean useSoftHeadphoneMonitor = false;
        public boolean enableStereoInput = false;
        public boolean enableLiteMode = false;
        public boolean enableLineAgc = false;
        public int dereverb = 0;
        public boolean disableCommonNS = false;
        public boolean enableAudioRenderThread = false;
        public boolean disableNewAecDelayEst = false;
        public boolean enableAudioQualityEst = false;
        public boolean enableProfile = false;
        public int profileStatisticTimes = 2;
        public boolean enableLineNs = true;
        public int lineAecNsLevel = -20;
        public boolean stableSampleRate = false;
        public String audioJsonConfig = "";

        public KWStannisServerConfig() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWStannisSoftAecMode {
    }

    public class KWTempoInterface {
        private int channel_num;
        private long native_instance;
        private int sample10ms;
        private int sample_rate;

        public KWTempoInterface(int i12, int i13) {
            this.native_instance = 0L;
            this.native_instance = Stannis.this.nativeAudioProcessCreateTempoInterface(i12, i13);
            this.sample_rate = i12;
            this.channel_num = i13;
            this.sample10ms = i12 / 100;
        }

        public int DetectBpm(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return Stannis.this.nativeAudioProcessBpmDetect(j12, bArr, this.sample10ms);
            }
            return 0;
        }

        public int GetFrameSample() {
            return this.sample10ms * this.channel_num;
        }

        public int Process(byte[] bArr) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                return Stannis.this.nativeAudioProcessTempoProcess(j12, bArr, this.sample10ms);
            }
            return 0;
        }

        public void ReleaseNative() {
            long j12 = this.native_instance;
            if (j12 != 0) {
                Stannis.this.nativeAudioProcessDestroyTempoInterface(j12);
                this.native_instance = 0L;
            }
        }

        public void SetRate(float f12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                Stannis.this.nativeAudioProcessSetTempoRate(j12, f12);
            }
        }

        public void SetTempo(float f12) {
            long j12 = this.native_instance;
            if (j12 != 0) {
                Stannis.this.nativeAudioProcessSetTempo(j12, f12);
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LOG_LEVEL {
    }

    public static class LogParam {
        public String filePath;
        public int logLevel = 0;
        public boolean isConsoleEnable = true;
        public boolean isFileEnable = false;
        public int maxFileSize = 5242880;
        public int maxFileNum = 3;
        public StannisLogObserver logCb = null;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OUTPUT_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OutPutStreamType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface QOS_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface REVERB_LEVEL {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VOICE_EFFECT_OPTION {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VOICE_ROLE_OPTION {
    }

    static {
        NativeLoader.loadNative();
        AUDIO_PLUGIN_ROUTER_MAP = Collections.unmodifiableMap(new HashMap<Integer, Integer>() { // from class: com.kwai.video.stannis.Stannis.2
            {
                put(131072, 0);
                put(Integer.valueOf(Stannis.AUDIO_PLUGIN_RECEIVER), 1);
                put(65536, 3);
                put(262144, 5);
                put(Integer.valueOf(Stannis.AUDIO_PLUGIN_USB), 6);
            }
        });
        AUDIO_OUTPUT_ROUTER_MAP = Collections.unmodifiableMap(new HashMap<Integer, Integer>() { // from class: com.kwai.video.stannis.Stannis.3
            {
                put(3, 0);
                put(2, 1);
                put(1, 3);
                put(5, 5);
                put(4, 6);
                put(6, 7);
            }
        });
    }

    private Stannis() {
    }

    private void StartLiveChat(int i12, int i13, DataReadyObserver dataReadyObserver) {
        nativeStartChat(this.nativeStannis, i12, i13, dataReadyObserver);
        nativeSetMuteChatOutBgm(this.nativeStannis, this.isChatBgmMuted);
        this.isChatStreamStarted = true;
    }

    private void StartLiveChatWithNativePtr(int i12, int i13, long j12) {
        nativeStartChatWithNativeReceiver(this.nativeStannis, i12, i13, j12);
        nativeSetMuteChatOutBgm(this.nativeStannis, this.isChatBgmMuted);
        this.isChatStreamStarted = true;
    }

    private void StartLiveStream(int i12, int i13, DataReadyObserver dataReadyObserver) {
        nativeStartLiveStream(this.nativeStannis, i12, i13, dataReadyObserver);
        nativeSetMuteLiveStreamOutBgm(this.nativeStannis, this.isLiveStreamBgmMuted);
        this.isLiveSteamStarted = true;
    }

    private void StartLiveStreamWithNativePtr(int i12, int i13, long j12) {
        nativeStartLiveStreamWithNativeReceiver(this.nativeStannis, i12, i13, j12);
        nativeSetMuteLiveStreamOutBgm(this.nativeStannis, this.isLiveStreamBgmMuted);
        this.isLiveSteamStarted = true;
    }

    private void StopAllLiveChat() {
        nativeStopAllLiveChat(this.nativeStannis);
        this.isChatStreamStarted = false;
    }

    private void StopAllStream() {
        nativeStopAllStream(this.nativeStannis);
        this.isLiveSteamStarted = false;
        this.isChatStreamStarted = false;
    }

    @CalledFromNative
    private int getAudioPlugin(int i12) {
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            return stannisAudioManagerInterface.getPlugin(i12);
        }
        return 65536;
    }

    @CalledFromNative
    private String getAudioRecrodingDeviceName() {
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        return stannisAudioManagerInterface != null ? stannisAudioManagerInterface.getCurRecordingDeviceName() : "unkown";
    }

    public static Stannis getInstance() {
        if (instance == null) {
            synchronized (Stannis.class) {
                try {
                    if (instance == null) {
                        instance = new Stannis();
                    }
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "Stannis.class", "getInstance", -1);
                    throw th2;
                }
            }
        }
        return instance;
    }

    private void initAudioManager() {
        this.audioManagerLock.lock();
        if (this.stannisAudioManager == null) {
            String str = this.context.getApplicationInfo().packageName;
            if (StannisAppHisense.equalsIgnoreCase(str) || StannisAppSedna.equalsIgnoreCase(str) || StannisAppHisense.equalsIgnoreCase(testAppName)) {
                this.stannisAudioManager = new HisenseAudioManager(this.context, this.nativeStannis, null);
                Log.i(TAG, "[KWStannis] AudioManager use " + str + " implementation");
            } else {
                this.stannisAudioManager = new StannisAudioManager(this.context, this.nativeStannis, null);
                Log.i(TAG, "[KWStannis] AudioManager use default implementation");
            }
            this.stannisAudioManager.setSpeakerOn(this.userSetSpeakerOn);
        }
        this.audioManagerLock.unlock();
    }

    private void initInternal() {
        this.isLiveSteamStarted = false;
        this.isChatStreamStarted = false;
        this.isAudioDeviceStarted = false;
        this.isPipeLineStarted = false;
        this.muteType = 0;
    }

    private native void nativeAddBypassDataReadyObserver(long j12, DataReadyObserver dataReadyObserver);

    private native void nativeAddLiveChatMixBgmStream(long j12, int i12, int i13, DataReadyObserver dataReadyObserver);

    private native void nativeAddLiveChatMixBgmStreamWithNativeReceiver(long j12, int i12, int i13, long j13);

    private native void nativeAddMixTrack(long j12, int i12);

    private native void nativeAddRxStream(long j12, int i12, DataProviderObserver dataProviderObserver, int i13, int i14, boolean z12);

    private native void nativeAddRxStreamWithNativeDataProvider(long j12, int i12, long j13, int i13, int i14, boolean z12);

    private native void nativeAddRxStreamWithNativeDataProviderWithType(long j12, int i12, long j13, int i13, int i14, boolean z12, int i15);

    private native void nativeAddTxVoiceEnergy(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioProcessBpmDetect(long j12, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioProcessCreateAgcInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioProcessCreateDspInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioProcessCreateTempoInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeAudioProcessCreateTimbreInterface(int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessData(long j12, long j13, long j14, byte[] bArr, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessDestroyAgcInterface(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessDestroyDspInterface(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessDestroyTempoInterface(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessDestroyTimbreInterface(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessSetAgcParam(long j12, int i12, int i13, int i14, int i15, int i16);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessSetDspParam(long j12, int i12, int i13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessSetTempo(long j12, float f12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessSetTempoRate(long j12, float f12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessSetVcoParameters(long j12, AudioChangeParam audioChangeParam);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAudioProcessSetVocalParam(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAudioProcessTempoProcess(long j12, byte[] bArr, int i12);

    private native void nativeCleanSoundEffectCache(long j12);

    private native void nativeClearAllAudioBuffer(long j12);

    private native void nativeClearAudioBuffer(long j12, String str);

    private native long nativeCreateStannis(int i12);

    private native void nativeEnableAecDump(long j12, boolean z12);

    private native int nativeEnableAttenuation(long j12, int i12, boolean z12);

    private native void nativeEnableMixingAudioSegment(long j12, boolean z12);

    private native int nativeEnableSpatializer(long j12, int i12, boolean z12);

    private native void nativeFetchRawAudio(long j12, byte[] bArr, int i12, int i13, int i14);

    private native int[] nativeGetActiveSpeakers(long j12);

    private native float[] nativeGetAutoMixBgmFeature(long j12);

    private native float[] nativeGetAutoMixVocalFeature(long j12);

    private native float[] nativeGetAutoTuneFeatures(long j12);

    private native int nativeGetBgmOffsetByRecordPts(long j12, long j13);

    private native boolean nativeGetConfig(long j12, int i12, boolean z12, int i13, KWStannisConfig kWStannisConfig);

    private native String nativeGetCpuInfo();

    private native int nativeGetCpuPercent(long j12);

    private native String nativeGetDeviceModel(long j12);

    private native boolean nativeGetIsAudioChatEnable(long j12);

    private native boolean nativeGetIsLiveStreamEnable(long j12);

    private native int nativeGetKaraokeAverageScore(long j12);

    private native int[] nativeGetKaraokeLastScore(long j12);

    private native int nativeGetKaraokeScoreMidi(long j12);

    private native int nativeGetKaraokeScorePitch(long j12);

    private native int nativeGetKaraokeTotalScore(long j12);

    private native int nativeGetKaraokeVadDurationResult(long j12, int[] iArr, int i12);

    private native int nativeGetMemoryKBytes(long j12);

    private native MicrophoneInfo nativeGetMicrophoneInfo(long j12);

    private native float[] nativeGetPitchSequence(long j12);

    private native QosInfo nativeGetQosInfo(long j12, int i12);

    private native int nativeGetRecordDelay(long j12);

    private native int nativeGetRoundTripLatency(long j12);

    private native String nativeGetStannisVersion();

    private native String nativeGetTips(long j12);

    private native int nativeGetVocalBgmShiftMs(long j12);

    private native int nativeGetVoiceEnergy(long j12, int i12);

    private native void nativeInitStannis(long j12, StannisNotifyObserver stannisNotifyObserver);

    private native void nativeInputPcmPlay(long j12, byte[] bArr, int i12, int i13, int i14, long j13);

    private native void nativeInputRawAudio(long j12, byte[] bArr, int i12, int i13, int i14, long j13);

    private native void nativeInputSpeakerData(long j12, byte[] bArr, int i12, int i13, int i14, long j13);

    private native void nativeInputTrackData(long j12, int i12, byte[] bArr, int i13, int i14, int i15, long j13);

    private native boolean nativeIsEnableAudioQualityEstimation(long j12);

    private native boolean nativeIsEnableCommonDenoise(long j12);

    private native boolean nativeIsRenderMute(long j12);

    private native boolean nativeIsStannisInited(long j12);

    private native int nativeKaraokeVadGetResult(long j12, int[] iArr, int i12);

    private native boolean nativeKaraokeVadRowJump(long j12, int i12, long j13);

    private native void nativeLoadSoundEffectCache(long j12, String str);

    private native void nativeOnBackground();

    private native void nativeOnForeground();

    private native void nativePause(long j12);

    private native void nativePauseAudioBuffer(long j12);

    private native void nativePauseBgm(long j12);

    private native void nativePlayAudioBuffer(long j12, String str, byte[] bArr, int i12, float f12, boolean z12, AudioBufferPlayObserver audioBufferPlayObserver);

    private native void nativePlaySoundEffect(long j12, String str, BgmObserver bgmObserver);

    private native void nativeRemoveBypassDataReadyObserver(long j12);

    private native void nativeRemoveLiveChatMixBgmStream(long j12, int i12);

    private native void nativeRemoveMixTrack(long j12, int i12);

    private native void nativeRemoveRxStream(long j12, int i12);

    private native void nativeRemoveTxVoiceEnergy(long j12);

    private native void nativeResetPcmPlay(long j12);

    private native void nativeResume(long j12);

    private native void nativeResumeAudioBuffer(long j12, boolean z12);

    private native void nativeResumeBgm(long j12);

    private native void nativeSeekBgm(long j12, int i12);

    private native void nativeSetAecFarendCompressorGain(long j12, float f12);

    private native void nativeSetAgcMode(long j12, int i12);

    private native void nativeSetAudioInputVolume(long j12, float f12);

    private native int nativeSetAudioRecvRange(long j12, int i12);

    private native void nativeSetAudioRxVolume(long j12, int i12, float f12);

    private native void nativeSetAudioTeamConference(long j12, int i12);

    private native int nativeSetAudioTunnel(long j12, int i12);

    private native void nativeSetBgmAutoSeekEnable(long j12, boolean z12);

    private native void nativeSetBgmGuidanceType(long j12, int i12);

    private native void nativeSetBgmLocalFadeGain(long j12, float f12);

    private native void nativeSetBgmPitch(long j12, int i12);

    private native void nativeSetBgmRemoteFadeGain(long j12, float f12);

    private native void nativeSetBgmVolume(long j12, float f12);

    private native void nativeSetEnableAudioQualityEstimation(long j12, boolean z12, String str, String str2);

    private native void nativeSetEnableAutoMixProcess(long j12, boolean z12);

    private native void nativeSetEnableCommonDenoise(long j12, boolean z12, String str);

    private native void nativeSetEnableDeepDereverb(long j12, boolean z12, String str);

    private native void nativeSetEnableDeepNs(long j12, boolean z12, String str);

    private native void nativeSetEnableNoiseSuppression(long j12, boolean z12);

    private native void nativeSetEnableSpeakerInput(long j12, boolean z12, int i12);

    private native void nativeSetGuidanceVolume(long j12, float f12);

    private native void nativeSetHowlingSuppressionMode(long j12, int i12);

    private native void nativeSetHowlingSuppressionMultipleDevice(long j12, boolean z12);

    private native int nativeSetHrtfMode(long j12, int i12, int i13);

    private native int nativeSetHrtfModelPath(long j12, String str);

    private native boolean nativeSetKaraokeScorePitch(long j12, int i12);

    private native void nativeSetMuteBgm(long j12, boolean z12);

    private native void nativeSetMuteChatOutBgm(long j12, boolean z12);

    private native void nativeSetMuteLiveStreamOutBgm(long j12, boolean z12);

    private native void nativeSetMuteMicrophone(long j12, int i12);

    private native void nativeSetMuteRemote(long j12, boolean z12);

    private native void nativeSetMuteSoundEffect(long j12, boolean z12);

    private native void nativeSetMuteSpeaker(long j12, boolean z12);

    private native void nativeSetPcmPlayVolume(long j12, float f12);

    private native void nativeSetRemoteBgmVolume(long j12, float f12);

    private native void nativeSetRemoteMixVolume(long j12, float f12);

    private native void nativeSetReverbLevel(long j12, int i12);

    private native void nativeSetRxStreamMixerGain(long j12, int i12, boolean z12, float f12);

    private native void nativeSetSelfAudioTeamId(long j12, int i12);

    private native void nativeSetSoftAecMode(long j12, int i12);

    private native void nativeSetSoundEffectVolume(long j12, float f12);

    private native void nativeSetSpeakerOutputVolume(long j12, float f12);

    private native void nativeSetVoiceEffectOption(long j12, int i12);

    private native void nativeSetVoiceRoleOption(long j12, int i12);

    private native void nativeSetVoiceVocal(long j12, int i12);

    private native void nativeStartAutoTune(long j12);

    private native boolean nativeStartBgm(long j12, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i12, int i13, ArrayList<KWStannisBgmGuidance> arrayList3, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver);

    private native void nativeStartChat(long j12, int i12, int i13, DataReadyObserver dataReadyObserver);

    private native void nativeStartChatWithNativeReceiver(long j12, int i12, int i13, long j13);

    private native void nativeStartFingerprint(long j12, String str, FingerprintObserver fingerprintObserver);

    private native void nativeStartKaraokeScore(long j12, long j13, String str, String str2, String str3, String str4, String str5, int i12, int i13, KaraokeScoreObserver karaokeScoreObserver);

    private native void nativeStartKaraokeVad(long j12, String str);

    private native void nativeStartLiveStream(long j12, int i12, int i13, DataReadyObserver dataReadyObserver);

    private native void nativeStartLiveStreamWithNativeReceiver(long j12, int i12, int i13, long j13);

    private native void nativeStartMultipleStreamMix(long j12, DataReadyObserver dataReadyObserver);

    private native void nativeStartPcmPlay(long j12);

    private native void nativeStartPlayAudioSegment(long j12, String str, String str2, AudioSegmentPlayerObserver audioSegmentPlayerObserver);

    private native void nativeStartRecordFile(long j12, String str, RecordFileObserver recordFileObserver, String str2);

    private native void nativeStartSilenceGenerator(long j12);

    private native void nativeStartVocalBgmSync(long j12, String str);

    private native void nativeStartVocalBgmSyncByData(long j12, byte[] bArr, int i12, int i13);

    private native void nativeStopAllLiveChat(long j12);

    private native void nativeStopAllSoundEffects(long j12);

    private native void nativeStopAllStream(long j12);

    private native void nativeStopAudioRenderThread(long j12);

    private native void nativeStopAutoTune(long j12);

    private native void nativeStopBgm(long j12);

    private native void nativeStopFingerprint(long j12);

    private native void nativeStopKaraokeScore(long j12);

    private native void nativeStopKaraokeVad(long j12);

    private native void nativeStopMultipleStreamMix(long j12);

    private native void nativeStopPcmPlay(long j12);

    private native void nativeStopPlayAudioSegment(long j12);

    private native void nativeStopRecordFile(long j12, String str, String str2);

    private native void nativeStopSilenceGenerator(long j12);

    private native void nativeStopVocalBgmSync(long j12);

    private native void nativeUninitStannis(long j12);

    private native boolean nativeUpdateBgmIndex(long j12, int i12, int i13);

    private native int nativeUpdateSelfPosition(long j12, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3);

    private native void nativeUpdateServerConfig(long j12, KWStannisServerConfig kWStannisServerConfig);

    private native void nativeUploadSpeakerDeviceVolume(long j12, int i12);

    private boolean needStartPipeline(int i12) {
        return (this.isPipeLineStarted && this.audioDeviceScene == i12) ? false : true;
    }

    private void notifyAudioSceneChange(final boolean z12, final int i12) {
        new Thread() { // from class: com.kwai.video.stannis.Stannis.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                for (StannisAudioSceneObserver stannisAudioSceneObserver : Stannis.this.audioSceneObservers) {
                    if (z12) {
                        stannisAudioSceneObserver.onAudioSceneStart(i12);
                    } else {
                        stannisAudioSceneObserver.onAudioSceneStop(i12);
                    }
                }
            }
        }.start();
    }

    @CalledFromNative
    private void onQosUpdated(int i12, String str) {
        StannisQosObserver stannisQosObserver = this.qosObserver;
        if (stannisQosObserver != null) {
            stannisQosObserver.onQosEventUpdated(i12, str);
        }
    }

    private int resetAudioDevice() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartRecord() {
        StannisAudioManagerInterface stannisAudioManagerInterface;
        Log.i(TAG, "[KWStannis] restartRecord start");
        this.audioManagerLock.lock();
        if (this.audioRecordStartedByUser && (stannisAudioManagerInterface = this.stannisAudioManager) != null) {
            stannisAudioManagerInterface.stopRecording();
            this.stannisAudioManager.startRecording(this.audioDeviceScene);
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] restartRecord end");
    }

    private void setBgmLocalFadeGain(float f12) {
        nativeSetBgmLocalFadeGain(this.nativeStannis, f12);
    }

    private void setBgmRemoteFadeGain(float f12) {
        nativeSetBgmRemoteFadeGain(this.nativeStannis, f12);
    }

    public static void setLogParam(LogParam logParam) {
        Log.setLogParam(logParam);
    }

    private int startAudioDevice(int i12) {
        int i13;
        Log.i(TAG, "[KWStannis] startAudioDevice start");
        if (this.audio_confg.useExternalDevice) {
            Log.i(TAG, "[KWStannis] startAudioDevice end, useExternalDevice");
            return 0;
        }
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.initOutputType(this.userSetOutputType);
            this.stannisAudioManager.setAudioRouteListener(this.audioRouteListener);
            this.stannisAudioManager.startAudioDeviceCheckThread();
            this.stannisAudioManager.updateAudioDeviceConfig(i12);
            this.stannisAudioManager.setAudioSession();
            i13 = !this.stannisAudioManager.startPlayout(i12) ? 1 : 0;
            if (!startRecordingByUser(i12)) {
                i13 = 1;
            }
            this.stannisAudioManager.resetRoundTripLatencyWithDelay(0);
        } else {
            i13 = 1;
        }
        if (i13 == 0) {
            this.isAudioDeviceStarted = true;
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] startAudioDevice end");
        return i13;
    }

    private void startPipelineCheckAndReset(int i12) {
        if (StannisAudioCommon.isChatScene(i12)) {
            return;
        }
        Log.w(TAG, "[KWStannis] startPipeline reset userSetOutputType by scene = " + i12);
        this.userSetOutputType = 0;
    }

    private boolean startPlayInternal(ArrayList<String> arrayList, ArrayList<String> arrayList2, int i12, int i13, ArrayList<KWStannisBgmGuidance> arrayList3, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver) {
        if (arrayList != null && arrayList.size() != 0) {
            return nativeStartBgm(this.nativeStannis, arrayList, arrayList2, i12, i13, arrayList3, bgmObserver, dataReadyObserver);
        }
        Log.e(TAG, "startPlayBgm error: urlList is empty");
        return false;
    }

    private boolean startRecordingByUser(int i12) {
        boolean zStartRecording;
        Log.i(TAG, "[KWStannis] startRecordingByUser start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            zStartRecording = this.audioRecordEnabled ? stannisAudioManagerInterface.startRecording(i12) : true;
        } else {
            zStartRecording = false;
        }
        this.audioRecordStartedByUser = true;
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] startRecordingByUser end");
        return zStartRecording;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3 */
    private int stopAudioDevice() {
        Log.i(TAG, "[KWStannis] stopAudioDevice start");
        if (this.audio_confg.useExternalDevice) {
            Log.i(TAG, "[KWStannis] stopAudioDevice end, useExternalDevice");
            return 0;
        }
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        ?? r12 = 1;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.stopAudioDeviceCheckThread();
            boolean z12 = !stopRecordingByUser();
            if (this.stannisAudioManager.stopPlayout()) {
                r12 = z12;
            }
        } else {
            r12 = 0;
        }
        this.isAudioDeviceStarted = false;
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] stopAudioDevice end");
        return r12;
    }

    private void stopAudioRenderThread() {
        nativeStopAudioRenderThread(this.nativeStannis);
    }

    private boolean stopRecordingByUser() {
        Log.i(TAG, "[KWStannis] stopRecordingByUser start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        boolean zStopRecording = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.stopRecording() : false;
        this.audioRecordStartedByUser = false;
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] stopRecordingByUser end");
        return zStopRecording;
    }

    public void AddBypassDataReadyObserver(DataReadyObserver dataReadyObserver, int i12) {
        nativeAddBypassDataReadyObserver(this.nativeStannis, dataReadyObserver);
    }

    public void AddLiveChatMixBgmStream(DataReadyObserver dataReadyObserver) {
        if (this.isChatStreamStarted) {
            nativeAddLiveChatMixBgmStream(this.nativeStannis, 3, 3, dataReadyObserver);
        }
    }

    public void AddLiveChatMixBgmStreamWithNativePtr(long j12) {
        if (this.isChatStreamStarted) {
            nativeAddLiveChatMixBgmStreamWithNativeReceiver(this.nativeStannis, 3, 3, j12);
        }
    }

    public void AddRxStream(int i12, DataProviderObserver dataProviderObserver, int i13, int i14) {
        AddRxStream(i12, dataProviderObserver, i13, i14, false);
    }

    public void AddRxStreamWithNativeDataProvider(int i12, long j12, int i13, int i14) {
        AddRxStreamWithNativeDataProvider(i12, j12, i13, i14, false);
    }

    public void AddRxStreamWithNativeDataProviderWithType(int i12, long j12, int i13, int i14, boolean z12, int i15) {
        nativeAddRxStreamWithNativeDataProviderWithType(this.nativeStannis, i12, j12, i13, i14, z12, i15);
    }

    public KWTempoInterface CreateTempoInterface(int i12, int i13) {
        return new KWTempoInterface(i12, i13);
    }

    public void DestroyTempoInterface(KWTempoInterface kWTempoInterface) {
        kWTempoInterface.ReleaseNative();
    }

    public String KWStanisGetCpuInfo() {
        return nativeGetCpuInfo();
    }

    public void KWStannisAudioProcessAudioData(byte[] bArr) {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib != null) {
            kWStannisAudioLib.processData(bArr);
        }
    }

    public int KWStannisAudioProcessGetFrameSample() {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib == null) {
            return 0;
        }
        return kWStannisAudioLib.getSamplePerFrame();
    }

    public void KWStannisAudioProcessSetAudioChangeParam(AudioChangeParam audioChangeParam) {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib != null) {
            kWStannisAudioLib.setAudioChangeParam(audioChangeParam);
        }
    }

    public void KWStannisAudioProcessSetNs(boolean z12) {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib != null) {
            kWStannisAudioLib.setEnableNs(z12);
        }
    }

    public void KWStannisAudioProcessSetReverb(int i12) {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib != null) {
            kWStannisAudioLib.reverb_level = i12;
        }
    }

    public void KWStannisAudioProcessSetSampleRate(int i12, int i13) {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib == null) {
            return;
        }
        if (kWStannisAudioLib.isNativeInited()) {
            this.audioProcessLib.uninitNative();
        }
        this.audioProcessLib.setSampleRate(i12);
        this.audioProcessLib.setChannelNum(i13);
        this.audioProcessLib.initNative();
    }

    public void KWStannisAudioProcessSetVocal(int i12) {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib != null) {
            kWStannisAudioLib.vocal = i12;
        }
    }

    public void KWStannisAudioProcessSetVoiceEffect(int i12) {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib != null) {
            kWStannisAudioLib.voice_effect = i12;
        }
    }

    public void KWStannisCreateAudioProcessLib() {
        this.audioProcessLib = new KWStannisAudioLib();
    }

    public void KWStannisDestroyAudioProcessLib() {
        KWStannisAudioLib kWStannisAudioLib = this.audioProcessLib;
        if (kWStannisAudioLib != null) {
            kWStannisAudioLib.uninitNative();
            this.audioProcessLib = null;
        }
    }

    public void RemoveAllBypassDataReadyObserver() {
        nativeRemoveBypassDataReadyObserver(this.nativeStannis);
    }

    public void RemoveLiveChatMixBgmStream() {
        nativeRemoveLiveChatMixBgmStream(this.nativeStannis, 3);
    }

    public void RemoveRxStream(int i12) {
        nativeRemoveRxStream(this.nativeStannis, i12);
    }

    public void SetRxStreamMixerGain(int i12, boolean z12, float f12) {
        nativeSetRxStreamMixerGain(this.nativeStannis, i12, z12, f12);
    }

    public void addAudioSceneObserver(StannisAudioSceneObserver stannisAudioSceneObserver) {
        this.audioSceneObservers.add(stannisAudioSceneObserver);
    }

    public void addMixTrack(int i12) {
        nativeAddMixTrack(this.nativeStannis, i12);
    }

    public void cleanSoundEffectCache() {
        nativeCleanSoundEffectCache(this.nativeStannis);
    }

    public void clearAllAudioBuffer() {
        nativeClearAllAudioBuffer(this.nativeStannis);
    }

    public void clearAudioBuffer(String str) {
        long j12 = this.nativeStannis;
        if (str == null) {
            str = "";
        }
        nativeClearAudioBuffer(j12, str);
    }

    public void disableHeadphoneMonitor() {
        Log.i(TAG, "[KWStannis] disableHeadphoneMonitor start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.closeDeviceHeaphoneMonitor();
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] disableHeadphoneMonitor end");
    }

    public void enableAecDump(boolean z12) {
        nativeEnableAecDump(this.nativeStannis, z12);
    }

    public int enableAttenuation(int i12, boolean z12) {
        return nativeEnableAttenuation(this.nativeStannis, i12, z12);
    }

    @Deprecated
    public boolean enableHeadphoneMonitor(boolean z12) {
        return enableHeadphoneMonitor();
    }

    public void enableLocalRecord(boolean z12) {
        enableRecord(z12, false);
    }

    public void enableMixingAudioSegment(boolean z12) {
        nativeEnableMixingAudioSegment(this.nativeStannis, z12);
    }

    public void enableRecord(boolean z12) {
        enableRecord(z12, true);
    }

    public void enableRecordWithAEC(boolean z12, int i12) {
        Log.i(TAG, "[KWStannis] enableRecordWithAEC start: " + z12 + ", aec: " + i12);
        enableRecord(z12, false);
        nativeSetSoftAecMode(this.nativeStannis, i12);
    }

    public int enableSpatializer(int i12, boolean z12) {
        return nativeEnableSpatializer(this.nativeStannis, i12, z12);
    }

    public void fetchRawAudio(byte[] bArr, int i12, int i13, int i14) {
        if (this.isPipeLineStarted) {
            nativeFetchRawAudio(this.nativeStannis, bArr, i12, i13, i14);
        } else {
            Log.w(TAG, "[KWStannis] fetchRawAudio !isPipeLineStarted");
        }
    }

    public int[] getActiveSpeakers() {
        return nativeGetActiveSpeakers(this.nativeStannis);
    }

    public int getAudioOutputRouting() {
        int i12;
        boolean z12;
        StannisAudioManagerInterface stannisAudioManagerInterface;
        StannisAudioManagerInterface stannisAudioManagerInterface2;
        int outputType = getOutputType();
        this.audioManagerLock.lock();
        boolean z13 = true;
        if (outputType != 3 || (stannisAudioManagerInterface2 = this.stannisAudioManager) == null) {
            i12 = -1;
            z12 = false;
        } else {
            i12 = stannisAudioManagerInterface2.isHeadphoneWithMic() ? 0 : 2;
            z12 = true;
        }
        this.audioManagerLock.unlock();
        if (z12) {
            return i12;
        }
        if (outputType != 0) {
            return AUDIO_OUTPUT_ROUTER_MAP.get(Integer.valueOf(outputType)).intValue();
        }
        Log.d(TAG, "[KWStannis] ouputType: KWStannisOutputTypeAuto");
        int audioPlugin = getAudioPlugin(this.audioDeviceScene);
        this.audioManagerLock.lock();
        if (audioPlugin != 131072 || (stannisAudioManagerInterface = this.stannisAudioManager) == null) {
            z13 = z12;
        } else {
            i12 = stannisAudioManagerInterface.isHeadphoneWithMic() ? 0 : 2;
        }
        this.audioManagerLock.unlock();
        return z13 ? i12 : AUDIO_PLUGIN_ROUTER_MAP.get(Integer.valueOf(audioPlugin)).intValue();
    }

    public float[][] getAutoMixFeature() {
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, 2, 20);
        float[] fArrNativeGetAutoMixBgmFeature = nativeGetAutoMixBgmFeature(this.nativeStannis);
        float[] fArrNativeGetAutoMixVocalFeature = nativeGetAutoMixVocalFeature(this.nativeStannis);
        int i12 = 0;
        if (fArrNativeGetAutoMixBgmFeature.length == 20) {
            for (int i13 = 0; i13 < 20; i13++) {
                fArr[0][i13] = fArrNativeGetAutoMixBgmFeature[i13];
            }
        } else {
            for (int i14 = 0; i14 < 20; i14++) {
                fArr[0][i14] = 0.0f;
            }
        }
        if (fArrNativeGetAutoMixVocalFeature.length == 20) {
            while (i12 < 20) {
                fArr[1][i12] = fArrNativeGetAutoMixVocalFeature[i12];
                i12++;
            }
        } else {
            while (i12 < 20) {
                fArr[1][i12] = 0.0f;
                i12++;
            }
        }
        return fArr;
    }

    public float[] getAutoTuneFeatures() {
        return nativeGetAutoTuneFeatures(this.nativeStannis);
    }

    public int getBgmOffsetByRecordPts(long j12) {
        return nativeGetBgmOffsetByRecordPts(this.nativeStannis, j12);
    }

    public int getCpuPercent() {
        return nativeGetCpuPercent(this.nativeStannis);
    }

    public StannisDeviceInfo getCurrentDevice(int i12) {
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        StannisDeviceInfo currentDevice = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.getCurrentDevice(i12) : null;
        this.audioManagerLock.unlock();
        return currentDevice;
    }

    public String getDeviceModel() {
        return nativeGetDeviceModel(this.nativeStannis);
    }

    public StannisDeviceInfo[] getDevices(int i12) {
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        StannisDeviceInfo[] devices = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.getDevices(i12) : null;
        this.audioManagerLock.unlock();
        return devices;
    }

    public int getKaraokeAverageScore() {
        return nativeGetKaraokeAverageScore(this.nativeStannis);
    }

    public int[] getKaraokeLastScore() {
        return nativeGetKaraokeLastScore(this.nativeStannis);
    }

    public int getKaraokeScoreMidi() {
        return nativeGetKaraokeScoreMidi(this.nativeStannis);
    }

    public int getKaraokeScorePitch() {
        return nativeGetKaraokeScorePitch(this.nativeStannis);
    }

    public int getKaraokeTotalScore() {
        return nativeGetKaraokeTotalScore(this.nativeStannis);
    }

    public List getKaraokeVadDurationResult(int i12) {
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[i12];
        int iNativeGetKaraokeVadDurationResult = nativeGetKaraokeVadDurationResult(this.nativeStannis, iArr, i12);
        for (int i13 = 0; i13 < iNativeGetKaraokeVadDurationResult; i13++) {
            arrayList.add(Integer.valueOf(iArr[i13]));
        }
        return arrayList;
    }

    public int getMemoryKBytes() {
        return nativeGetMemoryKBytes(this.nativeStannis);
    }

    public int getOutputType() {
        Log.d(TAG, "[KWStannis] getOutputType start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        int outputType = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.getOutputType() : 0;
        this.audioManagerLock.unlock();
        Log.d(TAG, "[KWStannis] getOutputType end");
        return outputType;
    }

    public int[] getOutputTypes() {
        Log.d(TAG, "[KWStannis] getOutputTypes start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        int[] outputTypes = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.getOutputTypes() : null;
        this.audioManagerLock.unlock();
        Log.d(TAG, "[KWStannis] getOutputTypes end");
        return outputTypes;
    }

    public QosInfo getQosInfo() {
        return getQosInfo(0);
    }

    public int getRecordDelay() {
        return nativeGetRecordDelay(this.nativeStannis);
    }

    public long getRecordSketchLatency() {
        long jNativeGetRoundTripLatency = (this.recordSketchIntervalEnd - this.recordSketchIntervalBegin) + ((long) nativeGetRoundTripLatency(this.nativeStannis));
        Log.i(TAG, "recordSketchLatency: " + jNativeGetRoundTripLatency);
        return jNativeGetRoundTripLatency;
    }

    public int getSpeakerDeviceVolume() {
        Log.i(TAG, "[KWStannis] getSpeakerDeviceVolume start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        int speakerDeviceVolume = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.getSpeakerDeviceVolume(this.audioDeviceScene) : -1;
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] getSpeakerDeviceVolume end");
        return speakerDeviceVolume;
    }

    public StannisAudioManagerInterface getStannisAudioManager() {
        return this.stannisAudioManager;
    }

    public KWStannisConfig getStannisConfig() {
        return this.audio_confg;
    }

    public MicrophoneInfo getStannisMicrophoneInfo() {
        return nativeGetMicrophoneInfo(this.nativeStannis);
    }

    public String getStannisVersion() {
        return nativeGetStannisVersion();
    }

    public String getStannisVersionExt() {
        return "";
    }

    public String getTips() {
        return nativeGetTips(this.nativeStannis);
    }

    public float[] getVocalBgmPithSequence() {
        return nativeGetPitchSequence(this.nativeStannis);
    }

    public int getVocalBgmShiftMs() {
        return nativeGetVocalBgmShiftMs(this.nativeStannis);
    }

    public int getVoiceEnergy(int i12) {
        return nativeGetVoiceEnergy(this.nativeStannis, i12);
    }

    public void init(Context context, StannisQosObserver stannisQosObserver) {
        Log.i(TAG, "[KWStannis] init start");
        this.context = context;
        ContextUtils.initialize(context);
        this.qosObserver = stannisQosObserver;
        initInternal();
        nativeInitStannis(this.nativeStannis, null);
        initAudioManager();
        Log.i(TAG, "[KWStannis] init end");
    }

    public long initStannisEngine(Context context, StannisQosObserver stannisQosObserver) {
        long j12;
        synchronized (this) {
            try {
                j12 = this.userId + 1;
                this.userId = j12;
                Log.i(TAG, "[KWStannis] initStannisEngine with userId" + j12);
                if (this.userIdSet.isEmpty()) {
                    init(context, stannisQosObserver);
                }
                this.userIdSet.add(new Long(j12));
                Log.i(TAG, "[KWStannis] initStannisEngine : userId count:" + this.userIdSet.size());
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Stannis.class", "initStannisEngine", -1);
                throw th2;
            }
        }
        return j12;
    }

    public long initStannisEngineWithNotifyObserver(Context context, StannisQosObserver stannisQosObserver, StannisNotifyObserver stannisNotifyObserver) {
        long j12;
        synchronized (this) {
            try {
                j12 = this.userId + 1;
                this.userId = j12;
                Log.i(TAG, "[KWStannis] initStannisEngineWithNotifyObserver with notifyObserver:" + stannisNotifyObserver + ", and userId:" + j12);
                if (this.userIdSet.isEmpty()) {
                    initWithNotifyObserver(context, stannisQosObserver, stannisNotifyObserver);
                }
                this.userIdSet.add(new Long(j12));
                Log.i(TAG, "[KWStannis] initStannisEngineWithNotifyObserver : userId count:" + this.userIdSet.size());
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Stannis.class", "initStannisEngineWithNotifyObserver", -1);
                throw th2;
            }
        }
        return j12;
    }

    public void initWithNotifyObserver(Context context, StannisQosObserver stannisQosObserver, final StannisNotifyObserver stannisNotifyObserver) {
        Log.i(TAG, "[KWStannis] initWithNotifyObserver start");
        this.context = context;
        ContextUtils.initialize(context);
        this.qosObserver = stannisQosObserver;
        this.notifyObserver = new StannisNotifyObserver() { // from class: com.kwai.video.stannis.Stannis.1
            @Override // com.kwai.video.stannis.observers.StannisNotifyObserver
            public void onNotify(int i12) {
                if (i12 != 10) {
                    if (i12 == 11) {
                        return;
                    }
                    stannisNotifyObserver.onNotify(i12);
                } else {
                    Log.i(Stannis.TAG, "[KWStannis] Receive kNoAudioSource notification");
                    if (!Stannis.this.audioRecordEnabled || Stannis.this.audioDeviceScene == 1280) {
                        return;
                    }
                    Stannis.this.restartRecord();
                }
            }
        };
        initInternal();
        nativeInitStannis(this.nativeStannis, this.notifyObserver);
        initAudioManager();
        Log.i(TAG, "[KWStannis] initWithNotifyObserver end");
    }

    public void inputPcmPlay(byte[] bArr, int i12, int i13, int i14, long j12) {
        nativeInputPcmPlay(this.nativeStannis, bArr, i12, i13, i14, j12);
    }

    public void inputRawAudio(byte[] bArr, int i12, int i13, int i14, long j12) {
        nativeInputRawAudio(this.nativeStannis, bArr, i12, i13, i14, j12);
    }

    public void inputSpeakerAudio(byte[] bArr, int i12, int i13, int i14, long j12) {
        nativeInputSpeakerData(this.nativeStannis, bArr, i12, i13, i14, j12);
    }

    public void inputTrackData(int i12, byte[] bArr, int i13, int i14, int i15, long j12) {
        nativeInputTrackData(this.nativeStannis, i12, bArr, i13, i14, i15, j12);
    }

    public boolean isEnableAudioQualityEstimation() {
        return nativeIsEnableAudioQualityEstimation(this.nativeStannis);
    }

    public boolean isEnableCommonDenoise() {
        return nativeIsEnableCommonDenoise(this.nativeStannis);
    }

    public boolean isInited() {
        return nativeIsStannisInited(this.nativeStannis);
    }

    public boolean isRenderMute() {
        return nativeIsRenderMute(this.nativeStannis);
    }

    public boolean isSpeakerOn() {
        boolean speakerOn = this.userSetSpeakerOn;
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            speakerOn = stannisAudioManagerInterface.getSpeakerOn();
        }
        this.audioManagerLock.unlock();
        return speakerOn && 3 == getAudioOutputRouting();
    }

    @Deprecated
    public boolean isSupportHeadphoneMonitor(boolean z12) {
        return isSupportHeadphoneMonitor();
    }

    public boolean isSupportSoftHeadphoneMonitor() {
        Log.i(TAG, "[KWStannis] isSupportSoftHeadphoneMonitor start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        boolean zIsSupportHeaphoneMonitor = false;
        if (stannisAudioManagerInterface != null && !stannisAudioManagerInterface.isSupportVendorHeadphoneMonitor()) {
            zIsSupportHeaphoneMonitor = this.stannisAudioManager.isSupportHeaphoneMonitor();
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] isSupportSoftHeadphoneMonitor end");
        return zIsSupportHeaphoneMonitor;
    }

    public boolean isSupportVendorHeadphoneMonitor() {
        Log.i(TAG, "[KWStannis] isSupportVendorHeadphoneMonitor start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        boolean zIsSupportVendorHeadphoneMonitor = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.isSupportVendorHeadphoneMonitor() : false;
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] isSupportVendorHeadphoneMonitor end");
        return zIsSupportVendorHeadphoneMonitor;
    }

    public List karaokeVadGetResult(int i12) {
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[i12];
        int iNativeKaraokeVadGetResult = nativeKaraokeVadGetResult(this.nativeStannis, iArr, i12);
        for (int i13 = 0; i13 < iNativeKaraokeVadGetResult; i13++) {
            arrayList.add(Integer.valueOf(iArr[i13]));
        }
        return arrayList;
    }

    public boolean karaokeVadRowJump(int i12, long j12) {
        return nativeKaraokeVadRowJump(this.nativeStannis, i12, j12);
    }

    public void loadSoundEffectCache(String str) {
        nativeLoadSoundEffectCache(this.nativeStannis, str);
    }

    public native int nativeRegisterAudioFrameObserver(long j12, AudioFrameObserverWrapper audioFrameObserverWrapper);

    public native int nativeRegisterAudioInfoObserver(long j12, AudioInfoObserverWrapper audioInfoObserverWrapper);

    public native int nativeSetMixedAudioFrameParameters(long j12, int i12, int i13, int i14, int i15);

    public native int nativeSetPlaybackAudioFrameParameters(long j12, int i12, int i13, int i14, int i15);

    public native int nativeSetRecordingAudioFrameParameters(long j12, int i12, int i13, int i14, int i15);

    public native void nativeSetRegisterAudioInfoObserver(long j12, boolean z12);

    @Deprecated
    public void notifyQAVSdkStarted(long j12) {
    }

    @Deprecated
    public void notifyQAVSdkStopped(long j12) {
    }

    public void onBackground() {
        nativeOnBackground();
    }

    public void onForeground() {
        nativeOnForeground();
    }

    public void pause() {
        if (this.isPipeLineStarted) {
            stopAudioDevice();
            nativePause(this.nativeStannis);
        }
    }

    public void pauseAudioBuffer() {
        nativePauseAudioBuffer(this.nativeStannis);
    }

    public void pauseBgm() {
        nativePauseBgm(this.nativeStannis);
    }

    @Deprecated
    public void pauseRecord() {
        if (this.isPipeLineStarted) {
            stopRecordingByUser();
            nativeRemoveTxVoiceEnergy(this.nativeStannis);
        }
    }

    public void playAudioBuffer(String str, byte[] bArr, float f12, boolean z12, AudioBufferPlayObserver audioBufferPlayObserver) {
        long j12 = this.nativeStannis;
        if (str == null) {
            str = "";
        }
        nativePlayAudioBuffer(j12, str, bArr, bArr.length, f12, z12, audioBufferPlayObserver);
    }

    public void playSoundEffect(String str, BgmObserver bgmObserver) {
        if (this.isPipeLineStarted) {
            nativePlaySoundEffect(this.nativeStannis, str, bgmObserver);
        }
    }

    public int registerAudioFrameObserver(AudioFrameObserver audioFrameObserver) {
        if (audioFrameObserver == null) {
            this.audioFrameObserverWrapper = null;
        } else {
            this.audioFrameObserverWrapper = new AudioFrameObserverWrapper(audioFrameObserver);
        }
        return nativeRegisterAudioFrameObserver(this.nativeStannis, this.audioFrameObserverWrapper);
    }

    public int registerAudioInfoObserver(AudioInfoObserver audioInfoObserver) {
        if (audioInfoObserver == null) {
            this.audioInfoObserverWrapper = null;
        } else {
            this.audioInfoObserverWrapper = new AudioInfoObserverWrapper(audioInfoObserver);
        }
        return nativeRegisterAudioInfoObserver(this.nativeStannis, this.audioInfoObserverWrapper);
    }

    public void removeAudioSceneObserver(StannisAudioSceneObserver stannisAudioSceneObserver) {
        this.audioSceneObservers.remove(stannisAudioSceneObserver);
    }

    public void removeMixTrack(int i12) {
        nativeRemoveMixTrack(this.nativeStannis, i12);
    }

    public void resetPcmPlay() {
        nativeResetPcmPlay(this.nativeStannis);
    }

    public void resume() {
        if (this.isPipeLineStarted) {
            if (this.stannisAudioManager.isUserEnableHeadphoneMonitor() && HuaweiAudioKitHelper.isUseAudioKit()) {
                this.stannisAudioManager.openDeviceHeaphoneMonitor();
            }
            startAudioDevice(this.audioDeviceScene);
            nativeResume(this.nativeStannis);
        }
    }

    public void resumeAudioBuffer(boolean z12) {
        nativeResumeAudioBuffer(this.nativeStannis, z12);
    }

    public void resumeBgm() {
        nativeResumeBgm(this.nativeStannis);
    }

    @Deprecated
    public void resumeRecord() {
        Log.i(TAG, "[KWStannis] resumeRecord start");
        if (this.isPipeLineStarted) {
            this.audioManagerLock.lock();
            if (this.stannisAudioManager != null) {
                startRecordingByUser(this.audioDeviceScene);
                this.stannisAudioManager.resetAudioProcess();
                nativeAddTxVoiceEnergy(this.nativeStannis);
            }
            this.audioManagerLock.unlock();
        }
        Log.i(TAG, "[KWStannis] resumeRecord end");
    }

    public void seekBgm(int i12) {
        nativeSeekBgm(this.nativeStannis, i12);
    }

    public void setAgcMode(int i12) {
        nativeSetAgcMode(this.nativeStannis, i12);
    }

    public void setAudioDeviceStatusListener(StannisAudioDeviceStatusObserver stannisAudioDeviceStatusObserver) {
        Log.i(TAG, "[KWStannis] setAudioDeviceStatusListener start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.setAudioDeviceStatusListener(stannisAudioDeviceStatusObserver);
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setAudioDeviceStatusListener end");
    }

    public void setAudioInputVolume(float f12) {
        setHeadphoneMonitorVolume(f12);
        nativeSetAudioInputVolume(this.nativeStannis, f12);
    }

    public int setAudioRecvRange(int i12) {
        return nativeSetAudioRecvRange(this.nativeStannis, i12);
    }

    public void setAudioRouteListener(AudioRouteListener audioRouteListener) {
        Log.d(TAG, "[KWStannis] setAudioRouteListener = " + audioRouteListener);
        this.audioRouteListener = audioRouteListener;
    }

    public void setAudioRxVolume(int i12, float f12) {
        nativeSetAudioRxVolume(this.nativeStannis, i12, f12);
    }

    public void setAudioTeamModeConference(int i12) {
        Log.i(TAG, "[KWStannis] setAudioTeamModeConference start");
        nativeSetAudioTeamConference(this.nativeStannis, i12);
    }

    public int setAudioTunnel(int i12) {
        return nativeSetAudioTunnel(this.nativeStannis, i12);
    }

    public void setAudioVoiceEffectOption(int i12) {
        nativeSetVoiceEffectOption(this.nativeStannis, i12);
    }

    public void setAudioVoiceRoleOption(int i12) {
        nativeSetVoiceRoleOption(this.nativeStannis, i12);
    }

    public void setBgmAutoSeekEnable(boolean z12) {
        nativeSetBgmAutoSeekEnable(this.nativeStannis, z12);
    }

    public void setBgmGuidanceType(int i12) {
        nativeSetBgmGuidanceType(this.nativeStannis, i12);
    }

    public void setBgmPitch(int i12) {
        nativeSetBgmPitch(this.nativeStannis, i12);
    }

    public void setBgmVolume(float f12) {
        nativeSetBgmVolume(this.nativeStannis, f12);
    }

    public void setCompressGain(float f12) {
        nativeSetAecFarendCompressorGain(this.nativeStannis, f12);
    }

    public void setEnableAudioQualityEstimation(boolean z12, String str, String str2) {
        nativeSetEnableAudioQualityEstimation(this.nativeStannis, z12, str, str2);
    }

    public void setEnableAutoMixProcess(boolean z12) {
        nativeSetEnableAutoMixProcess(this.nativeStannis, z12);
    }

    public void setEnableCommonDenoise(boolean z12, String str) {
        if (z12 && str == null) {
            Log.w(TAG, "[KWStannis] setEnableCommonDenoise enable, but model path is null.");
            return;
        }
        Log.i(TAG, "[KWStannis] setEnableCommonDenoise: " + z12 + ", model = " + str);
        nativeSetEnableCommonDenoise(this.nativeStannis, z12, str);
    }

    public void setEnableDeepDereverb(boolean z12, String str) {
        if (z12 && str == null) {
            Log.w(TAG, "[KWStannis] setEnableDeepDereverb enable, but model path is null.");
            return;
        }
        Log.i(TAG, "[KWStannis] setEnableDeepDereverb: " + z12 + ", model = " + str);
        nativeSetEnableDeepDereverb(this.nativeStannis, z12, str);
    }

    public void setEnableDeepNs(boolean z12, String str) {
        if (z12 && str == null) {
            Log.w(TAG, "[KWStannis] setEnableDeepNs enable, but model path is null.");
            return;
        }
        Log.i(TAG, "[KWStannis] setEnableDeepNs: " + z12 + ", model = " + str);
        nativeSetEnableDeepNs(this.nativeStannis, z12, str);
    }

    public void setEnableNoiseSuppression(boolean z12) {
        nativeSetEnableNoiseSuppression(this.nativeStannis, z12);
    }

    public void setEnableSpeakerInputAEC(boolean z12) {
        nativeSetEnableSpeakerInput(this.nativeStannis, z12, ClientEvent.UrlPackage.Page.PC_LIVEMATE_CAMERA_SETTING_PAGE);
    }

    public void setFirstAudioFrameRenderedAfterSeek() {
        this.recordSketchIntervalEnd = System.currentTimeMillis();
        Log.i(TAG, "recordSketchIntervalEnd: " + this.recordSketchIntervalEnd);
    }

    public void setGuidanceVolume(float f12) {
        nativeSetGuidanceVolume(this.nativeStannis, f12);
    }

    public void setHeadphoneMonitorVolume(float f12) {
        Log.i(TAG, "[KWStannis] setHeadphoneMonitorVolume:" + f12 + " start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.setHeadphoneMonitorVolume(f12);
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setHeadphoneMonitorVolume end");
    }

    public void setHiFiMusicMode(boolean z12) {
        Log.i(TAG, "[KWStannis] setHiFiMusicMode start");
        this.audioManagerLock.lock();
        if (this.stannisAudioManager == null || !this.isAudioDeviceStarted) {
            this.audioManagerLock.unlock();
            Log.w(TAG, "[KWStannis] setHiFiMusicMode end:, device not started");
            return;
        }
        if (getStannisAudioManager().getDeviceConfig() != null) {
            int scene = getStannisAudioManager().getDeviceConfig().getScene();
            if (scene != 2048 && scene != 3072) {
                this.audioManagerLock.unlock();
                Log.d(TAG, "[KWStannis] setHiFiMusicMode end, do not need setHiFiMusicMode: audio_scene = " + scene + "， enable = " + z12);
                return;
            }
            StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
            if (stannisAudioManagerInterface != null) {
                if (z12) {
                    stannisAudioManagerInterface.resetDevice(kInnerHiFi, "scene = InnerHiFi", false);
                    this.audioDeviceScene = kInnerHiFi;
                } else {
                    stannisAudioManagerInterface.resetDevice(2048, "scene = kLiveGroupChat", false);
                    this.audioDeviceScene = 2048;
                }
                nativeSetMuteRemote(this.nativeStannis, z12);
            }
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setHiFiMusicMode end");
    }

    public void setHowlingSuppressionMode(int i12) {
        nativeSetHowlingSuppressionMode(this.nativeStannis, i12);
    }

    public void setHowlingSuppressionMultipleDevice(boolean z12) {
        nativeSetHowlingSuppressionMultipleDevice(this.nativeStannis, z12);
    }

    public int setHrtfMode(int i12, int i13) {
        return nativeSetHrtfMode(this.nativeStannis, i12, i13);
    }

    public int setHrtfModelPath(String str) {
        return nativeSetHrtfModelPath(this.nativeStannis, str);
    }

    public boolean setKaraokeScorePitch(int i12) {
        return nativeSetKaraokeScorePitch(this.nativeStannis, i12);
    }

    public void setKtvMode(boolean z12) {
        Log.i(TAG, "[KWStannis] setKtvMode start");
        this.audioManagerLock.lock();
        if (this.stannisAudioManager == null || !this.isAudioDeviceStarted) {
            this.audioManagerLock.unlock();
            Log.w(TAG, "[KWStannis] setKtvMode end:, device not started");
            return;
        }
        if (getStannisAudioManager().getDeviceConfig() != null) {
            int scene = getStannisAudioManager().getDeviceConfig().getScene();
            if ((scene != 2048 && scene != 2560) || ((!z12 && scene == 2048) || (z12 && scene == 2560))) {
                this.audioManagerLock.unlock();
                Log.d(TAG, "[KWStannis] setKtvMode end, do not need setKtvMode: audio_scene = " + scene + "， enable = " + z12);
                return;
            }
            StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
            if (stannisAudioManagerInterface != null) {
                if (z12) {
                    stannisAudioManagerInterface.resetDevice(2560, "setKtvMode = kLiveKtvChat", false);
                    this.audioDeviceScene = 2560;
                } else {
                    stannisAudioManagerInterface.resetDevice(2048, "setKtvMode = kLiveGroupChat", false);
                    this.audioDeviceScene = 2048;
                }
            }
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setKtvMode end");
    }

    public void setMicphoneVolume(float f12) {
        nativeSetAudioInputVolume(this.nativeStannis, f12);
    }

    public int setMixedAudioFrameParameters(int i12, int i13, int i14, int i15) {
        return nativeSetMixedAudioFrameParameters(this.nativeStannis, i12, i13, i14, i15);
    }

    public void setMuteBgm(boolean z12) {
        nativeSetMuteBgm(this.nativeStannis, z12);
    }

    public void setMuteChatOutBgm(boolean z12) {
        this.isChatBgmMuted = z12;
        nativeSetMuteChatOutBgm(this.nativeStannis, z12);
    }

    public void setMuteLiveStreamOutBgm(boolean z12) {
        this.isLiveStreamBgmMuted = z12;
        nativeSetMuteLiveStreamOutBgm(this.nativeStannis, z12);
    }

    public void setMuteMicrophone(int i12) {
        this.muteType = i12;
        nativeSetMuteMicrophone(this.nativeStannis, i12);
    }

    public void setMuteRemote(boolean z12, boolean z13) {
        Log.i(TAG, "[KWStannis] setMuteRemote start");
        this.audioManagerLock.lock();
        if (this.stannisAudioManager == null || !this.isAudioDeviceStarted) {
            this.audioManagerLock.unlock();
            Log.w(TAG, "[KWStannis] setMuteRemote end: device not started");
            return;
        }
        nativeSetMuteRemote(this.nativeStannis, z12);
        if ((z13 || !z12) && getStannisAudioManager().getDeviceConfig() != null) {
            int scene = getStannisAudioManager().getDeviceConfig().getScene();
            int i12 = 2048;
            if ((scene != 768 && scene != 1792 && scene != 2048 && scene != 2560) || (!z12 && scene != 1792 && scene != 2560)) {
                this.audioManagerLock.unlock();
                Log.w(TAG, "[KWStannis] setMuteRemote end, not need setMuteRemote: audio_scene = " + scene + " mute = " + z12);
                return;
            }
            if (z12) {
                i12 = (scene == 768 || scene == 1792) ? 1792 : 2560;
            } else if (scene == 768 || scene == 1792) {
                i12 = 768;
            }
            if (i12 == 2560) {
                setSoftAecMode(2);
            } else {
                setSoftAecMode(0);
            }
            StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
            if (stannisAudioManagerInterface != null) {
                stannisAudioManagerInterface.resetDevice(i12, "setMuteRemote", false);
            }
            this.audioDeviceScene = i12;
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setMuteRemote end");
    }

    public void setMuteSoundEffect(boolean z12) {
        nativeSetMuteSoundEffect(this.nativeStannis, z12);
    }

    public void setMuteSpeaker(boolean z12) {
        nativeSetMuteSpeaker(this.nativeStannis, z12);
    }

    public boolean setOutputType(int i12) {
        Log.d(TAG, "[KWStannis] setOutputType start, type = " + i12);
        this.userSetOutputType = i12;
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        boolean outputType = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.setOutputType(i12, this.audioDeviceScene) : false;
        this.audioManagerLock.unlock();
        Log.d(TAG, "[KWStannis] setOutputType end");
        return outputType;
    }

    public void setPcmPlayVolume(float f12) {
        nativeSetPcmPlayVolume(this.nativeStannis, f12);
    }

    public int setPlaybackAudioFrameParameters(int i12, int i13, int i14, int i15) {
        return nativeSetPlaybackAudioFrameParameters(this.nativeStannis, i12, i13, i14, i15);
    }

    public int setRecordingAudioFrameParameters(int i12, int i13, int i14, int i15) {
        return nativeSetRecordingAudioFrameParameters(this.nativeStannis, i12, i13, i14, i15);
    }

    public void setRegisterAudioInfoObserver(boolean z12) {
        nativeSetRegisterAudioInfoObserver(this.nativeStannis, z12);
    }

    public void setRemoteBgmVolume(float f12) {
        nativeSetRemoteBgmVolume(this.nativeStannis, f12);
    }

    public void setRemoteMixVolume(float f12) {
        nativeSetRemoteMixVolume(this.nativeStannis, f12);
    }

    public void setRequestAudioFocus(boolean z12) {
        Log.i(TAG, "[KWStannis] setRequestAudioFocus start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.setRequestAudioFocus(z12);
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setRequestAudioFocus end");
    }

    public void setReverbLevel(int i12) {
        Log.i(TAG, "[KWStannis] setReverbLevel start");
        nativeSetReverbLevel(this.nativeStannis, i12);
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.setHeadphoneMonitorReverbLevel(i12);
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setReverbLevel end");
    }

    public void setSelfAudioTeamId(int i12) {
        nativeSetSelfAudioTeamId(this.nativeStannis, i12);
    }

    public void setSoftAecMode(int i12) {
        nativeSetSoftAecMode(this.nativeStannis, i12);
    }

    public void setSoundEffectVolume(float f12) {
        nativeSetSoundEffectVolume(this.nativeStannis, f12);
    }

    public void setSpeakerOn(boolean z12) {
        Log.i(TAG, "[KWStannis] setSpeakerOn start, isOn = " + z12);
        this.userSetSpeakerOn = z12;
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface == null) {
            Log.w(TAG, "[KWStannis] setSpeakerOn invalid, audioManager is null");
        } else if (stannisAudioManagerInterface.getUserSetOutputType() == 0) {
            this.stannisAudioManager.setSpeakerOn(z12);
        } else {
            Log.w(TAG, "[KWStannis] setSpeakerOn invalid: UserSetOutputType = " + this.stannisAudioManager.getUserSetOutputType());
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setSpeakerOn end");
    }

    public void setSpeakerOutputVolume(float f12) {
        nativeSetSpeakerOutputVolume(this.nativeStannis, f12);
    }

    public void setStannisConfig(KWStannisConfig kWStannisConfig) {
        this.audio_confg = kWStannisConfig;
    }

    public void setStartRecordSketch() {
        this.recordSketchIntervalBegin = System.currentTimeMillis();
        Log.i(TAG, "recordSketchIntervalBegin: " + this.recordSketchIntervalBegin);
    }

    @Deprecated
    public void setUseQAVSdk(boolean z12) {
    }

    public void setUsingBuiltinMic(boolean z12) {
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.setUsingBuiltinMic(z12);
        }
        this.audioManagerLock.unlock();
    }

    public void setUsingLocalHeadphoneMonitorSetting(boolean z12) {
        Log.i(TAG, "[KWStannis] setUsingLocalHeadphoneMonitorSetting = " + z12);
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.setUsingLocalHeadphoneMonitorSetting(z12);
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] setUsingLocalHeadphoneMonitorSetting end ");
    }

    public void setVoiceVocal(int i12) {
        nativeSetVoiceVocal(this.nativeStannis, i12);
    }

    public boolean startAudioInnerCap(MediaProjection mediaProjection) {
        boolean zStartAudioInnerCap;
        Log.i(TAG, "[KWStannis] startAudioInnerCap start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            zStartAudioInnerCap = stannisAudioManagerInterface.startAudioInnerCap(48000, 2, mediaProjection);
            nativeSetEnableSpeakerInput(this.nativeStannis, true, ClientEvent.UrlPackage.Page.PC_LIVEMATE_CAMERA_SETTING_PAGE);
        } else {
            zStartAudioInnerCap = false;
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] startAudioInnerCap end");
        return zStartAudioInnerCap;
    }

    public void startAutoTuneProcess() {
        nativeStartAutoTune(this.nativeStannis);
    }

    public boolean startBgm(ArrayList<String> arrayList, boolean z12, int i12, BgmObserver bgmObserver) {
        return startBgm(arrayList, (ArrayList<String>) null, z12, i12, bgmObserver);
    }

    public void startFingerprint(String str, FingerprintObserver fingerprintObserver) {
        if (this.isPipeLineStarted) {
            nativeStartFingerprint(this.nativeStannis, str, fingerprintObserver);
            return;
        }
        Log.w(TAG, "[KWStannis] startFingerprint with (" + str + ") failed: You need startPipeLine before startFingerprint!");
    }

    public void startKaraokeScore(long j12, String str, String str2, String str3, String str4, String str5, int i12, int i13, KaraokeScoreObserver karaokeScoreObserver) {
        nativeStartKaraokeScore(this.nativeStannis, j12, str, str2, str3, str4, str5, i12, i13, karaokeScoreObserver);
    }

    public void startKaraokeVad(String str) {
        nativeStartKaraokeVad(this.nativeStannis, str);
    }

    public void startMultipleStreamMix(DataReadyObserver dataReadyObserver) {
        if (this.isPipeLineStarted) {
            nativeStartMultipleStreamMix(this.nativeStannis, dataReadyObserver);
        } else {
            Log.w(TAG, "[KWStannis] startMultipleStreamMix failed: You need startPipeLine before startMultipleStreamMix!");
        }
    }

    public void startPcmPlay() {
        nativeStartPcmPlay(this.nativeStannis);
    }

    public int startPipeline(int i12, DataReadyObserver dataReadyObserver) {
        Log.i(TAG, "[KWStannis] startPipeline start");
        startPipelineCheckAndReset(i12);
        int iStartAudioDevice = 0;
        if (!needStartPipeline(i12)) {
            Log.i(TAG, "[KWStannis] startPipeline end, not need to restart pipeline: scene=" + i12 + ", current_scene=" + this.audioDeviceScene);
            return 0;
        }
        this.dataReadyObserver = dataReadyObserver;
        boolean zNativeGetConfig = nativeGetConfig(this.nativeStannis, 2, this.userSetOutputType == 0, i12, this.audio_confg);
        boolean zNativeGetIsLiveStreamEnable = nativeGetIsLiveStreamEnable(this.nativeStannis);
        boolean zNativeGetIsAudioChatEnable = nativeGetIsAudioChatEnable(this.nativeStannis);
        if (zNativeGetIsLiveStreamEnable) {
            if (!this.isLiveSteamStarted) {
                StartLiveStream(0, 0, dataReadyObserver);
            }
        } else if (this.isLiveSteamStarted) {
            StopAllStream();
        }
        if (this.isChatStreamStarted) {
            StopAllLiveChat();
        }
        if (zNativeGetIsAudioChatEnable) {
            StartLiveChat(1, 1, dataReadyObserver);
        }
        nativeSetMuteMicrophone(this.nativeStannis, this.muteType);
        if (i12 != 1280) {
            nativeStopSilenceGenerator(this.nativeStannis);
        }
        boolean z12 = this.isAudioDeviceStarted;
        if (!z12) {
            iStartAudioDevice = startAudioDevice(i12);
        } else if (zNativeGetConfig) {
            if (z12) {
                stopAudioDevice();
            }
            iStartAudioDevice = startAudioDevice(i12);
        } else {
            this.audioManagerLock.lock();
            StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
            if (stannisAudioManagerInterface != null) {
                stannisAudioManagerInterface.updateAudioDeviceConfig(i12);
                this.stannisAudioManager.setAudioRouteListener(this.audioRouteListener);
            }
            this.audioManagerLock.unlock();
        }
        if (i12 == 1280) {
            nativeStartSilenceGenerator(this.nativeStannis);
        }
        this.isPipeLineStarted = true;
        this.audioDeviceScene = i12;
        notifyAudioSceneChange(true, i12);
        Log.i(TAG, "[KWStannis] startPipeline end");
        return iStartAudioDevice;
    }

    public int startPipelineWithNativePtr(int i12, long j12) {
        Log.i(TAG, "[KWStannis] startPipelineWithNativePtr start");
        startPipelineCheckAndReset(i12);
        int iStartAudioDevice = 0;
        if (!needStartPipeline(i12)) {
            Log.i(TAG, "[KWStannis] startPipelineWithNativePtr end, not need to restart pipeline: scene=" + i12 + ", current_scene=" + this.audioDeviceScene);
            return 0;
        }
        boolean zNativeGetConfig = nativeGetConfig(this.nativeStannis, 2, this.userSetOutputType == 0, i12, this.audio_confg);
        boolean zNativeGetIsLiveStreamEnable = nativeGetIsLiveStreamEnable(this.nativeStannis);
        boolean zNativeGetIsAudioChatEnable = nativeGetIsAudioChatEnable(this.nativeStannis);
        if (zNativeGetIsLiveStreamEnable) {
            if (!this.isLiveSteamStarted) {
                StartLiveStreamWithNativePtr(0, 0, j12);
            }
        } else if (this.isLiveSteamStarted) {
            StopAllStream();
        }
        if (this.isChatStreamStarted) {
            StopAllLiveChat();
        }
        if (zNativeGetIsAudioChatEnable) {
            StartLiveChatWithNativePtr(1, 1, j12);
        }
        nativeSetMuteMicrophone(this.nativeStannis, this.muteType);
        if (i12 != 1280) {
            nativeStopSilenceGenerator(this.nativeStannis);
        }
        boolean z12 = this.isAudioDeviceStarted;
        if (!z12) {
            iStartAudioDevice = startAudioDevice(i12);
        } else if (zNativeGetConfig) {
            if (z12) {
                stopAudioDevice();
            }
            iStartAudioDevice = startAudioDevice(i12);
        } else {
            this.audioManagerLock.lock();
            StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
            if (stannisAudioManagerInterface != null) {
                stannisAudioManagerInterface.updateAudioDeviceConfig(i12);
                this.stannisAudioManager.setAudioRouteListener(this.audioRouteListener);
            }
            this.audioManagerLock.unlock();
        }
        if (i12 == 1280) {
            nativeStartSilenceGenerator(this.nativeStannis);
        }
        this.isPipeLineStarted = true;
        this.audioDeviceScene = i12;
        notifyAudioSceneChange(true, i12);
        Log.i(TAG, "[KWStannis] startPipelineWithNativePtr end");
        return iStartAudioDevice;
    }

    public void startPlayAudioSegment(String str, String str2, AudioSegmentPlayerObserver audioSegmentPlayerObserver) {
        if (this.isPipeLineStarted) {
            nativeStartPlayAudioSegment(this.nativeStannis, str, str2, audioSegmentPlayerObserver);
        } else if (audioSegmentPlayerObserver != null) {
            audioSegmentPlayerObserver.onError(str2, str, AudioSegmentPlayerObserver.ErrorType.StartFailed);
        }
    }

    public boolean startPlayBgm(ArrayList<String> arrayList, int i12, int i13, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver) {
        return startPlayBgm(arrayList, null, i12, i13, bgmObserver, dataReadyObserver);
    }

    public int startRecordDevice() {
        Log.i(TAG, "[KWStannis] startRecordDevice start");
        boolean zNativeGetConfig = nativeGetConfig(this.nativeStannis, 2, this.userSetOutputType == 0, 256, this.audio_confg);
        boolean z12 = this.isAudioDeviceStarted;
        if (!z12) {
            return startAudioDevice(256);
        }
        if (zNativeGetConfig) {
            if (z12) {
                stopAudioDevice();
            }
            return startAudioDevice(256);
        }
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.updateAudioDeviceConfig(256);
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] startRecordDevice end");
        return 0;
    }

    public void startRecordFile(String str, RecordFileObserver recordFileObserver) {
        startRecordFile(str, recordFileObserver, "");
    }

    public void startVocalBgmSync(String str) {
        nativeStartVocalBgmSync(this.nativeStannis, str);
    }

    public void stopAllSoundEffects() {
        nativeStopAllSoundEffects(this.nativeStannis);
    }

    public void stopAutoTuneProcess() {
        nativeStopAutoTune(this.nativeStannis);
    }

    public void stopBgm() {
        nativeStopBgm(this.nativeStannis);
    }

    public void stopFingerprint() {
        nativeStopFingerprint(this.nativeStannis);
    }

    public void stopInnerCap() {
        Log.i(TAG, "[KWStannis] stopAudioInnerCap start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            if (stannisAudioManagerInterface.hasStartedAudioInnerCap()) {
                nativeSetEnableSpeakerInput(this.nativeStannis, false, ClientEvent.UrlPackage.Page.PC_LIVEMATE_CAMERA_SETTING_PAGE);
            }
            this.stannisAudioManager.stopInnerCap();
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] stopAudioInnerCap end");
    }

    public void stopKaraokeScore() {
        nativeStopKaraokeScore(this.nativeStannis);
    }

    public void stopKaraokeVad() {
        nativeStopKaraokeVad(this.nativeStannis);
    }

    public void stopMultipleStreamMix() {
        nativeStopMultipleStreamMix(this.nativeStannis);
    }

    public void stopPcmPlay() {
        nativeStopPcmPlay(this.nativeStannis);
    }

    public int stopPipeline() {
        int i12 = this.audioDeviceScene;
        this.muteType = 0;
        if (!this.isPipeLineStarted) {
            return 0;
        }
        stopBgm();
        stopAllSoundEffects();
        stopPlayAudioSegment();
        stopMultipleStreamMix();
        StopAllStream();
        disableHeadphoneMonitor();
        stopInnerCap();
        int iStopAudioDevice = stopAudioDevice();
        stopAudioRenderThread();
        this.isChatBgmMuted = false;
        this.isLiveStreamBgmMuted = false;
        this.isPipeLineStarted = false;
        this.dataReadyObserver = null;
        notifyAudioSceneChange(false, i12);
        return iStopAudioDevice;
    }

    public void stopPlayAudioSegment() {
        nativeStopPlayAudioSegment(this.nativeStannis);
    }

    public void stopRecordDevice() {
        if (this.isAudioDeviceStarted) {
            stopAudioDevice();
        }
    }

    public void stopRecordFile(String str) {
        stopRecordFile(str, "");
    }

    public void stopVocalBgmSync() {
        nativeStopVocalBgmSync(this.nativeStannis);
    }

    public void uninit() {
        Log.i(TAG, "[KWStannis] uninit start");
        if (this.isPipeLineStarted) {
            stopPipeline();
            this.isPipeLineStarted = false;
        }
        this.muteType = 0;
        nativeUninitStannis(this.nativeStannis);
        this.context = null;
        this.qosObserver = null;
        this.audioManagerLock.lock();
        this.stannisAudioManager = null;
        this.audioManagerLock.unlock();
        this.audioRouteListener = null;
        this.notifyObserver = null;
        this.audioRecordEnabled = true;
        Log.i(TAG, "[KWStannis] uninit end");
    }

    public void uninitStannisEngine(long j12) {
        Log.i(TAG, "[KWStannis] uninitStannisEngine: userId=" + j12);
        synchronized (this) {
            try {
                Long l12 = new Long(j12);
                if (this.userIdSet.contains(l12)) {
                    this.userIdSet.remove(l12);
                } else {
                    Log.w(TAG, "[KWStannis] uninitStannisEngine: not contains userId" + j12);
                }
                if (this.userIdSet.isEmpty()) {
                    uninit();
                }
                Log.i(TAG, "[KWStannis] uninitStannisEngine : userId count:" + this.userIdSet.size());
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Stannis.class", "uninitStannisEngine", -1);
                throw th2;
            }
        }
    }

    public boolean updateBgmIndex(int i12, int i13) {
        return nativeUpdateBgmIndex(this.nativeStannis, i12, i13);
    }

    public int updateSelfPosition(int[] iArr, float[] fArr, float[] fArr2, float[] fArr3) {
        return nativeUpdateSelfPosition(this.nativeStannis, iArr, fArr, fArr2, fArr3);
    }

    public void updateServerConfig(KWStannisServerConfig kWStannisServerConfig) {
        Log.i(TAG, "[KWStannis] updateServerConfig start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            stannisAudioManagerInterface.setServerConfigStr(kWStannisServerConfig.audioJsonConfig);
            this.stannisAudioManager.setKtvVendorSupport(kWStannisServerConfig.ktvVendorSupport);
            if (kWStannisServerConfig.ktvVendorSupport && this.stannisAudioManager.isSupportVendorHeadphoneMonitor()) {
                Log.i(TAG, "[KWStannis] updateServerConfig setDeviceType, use ktv vendor, do not set device type, use Java API");
            } else {
                this.stannisAudioManager.setDeviceType(kWStannisServerConfig.deviceType);
            }
            this.stannisAudioManager.setUseSoftHeadphoneMonitor(kWStannisServerConfig.useSoftHeadphoneMonitor);
        } else {
            Log.w(TAG, "updateServerConfig audioManager not initialized");
        }
        nativeUpdateServerConfig(this.nativeStannis, kWStannisServerConfig);
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] updateServerConfig end");
    }

    public void AddRxStream(int i12, DataProviderObserver dataProviderObserver, int i13, int i14, boolean z12) {
        nativeAddRxStream(this.nativeStannis, i12, dataProviderObserver, i13, i14, z12);
    }

    public void AddRxStreamWithNativeDataProvider(int i12, long j12, int i13, int i14, boolean z12) {
        nativeAddRxStreamWithNativeDataProvider(this.nativeStannis, i12, j12, i13, i14, z12);
    }

    public boolean enableHeadphoneMonitor() {
        boolean zOpenDeviceHeaphoneMonitor;
        Log.i(TAG, "[KWStannis] enableHeadphoneMonitor start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        if (stannisAudioManagerInterface != null) {
            boolean zIsHeadsetConnected = stannisAudioManagerInterface.isHeadsetConnected();
            boolean zIsUsbConnected = this.stannisAudioManager.isUsbConnected();
            if (zIsHeadsetConnected || zIsUsbConnected) {
                zOpenDeviceHeaphoneMonitor = this.stannisAudioManager.openDeviceHeaphoneMonitor();
            } else {
                Log.i(TAG, "[KWStannis] enableHeadphoneMonitor failed, isConnectHeadset = " + zIsHeadsetConnected + ", isConnectUsb = " + zIsUsbConnected);
                zOpenDeviceHeaphoneMonitor = false;
            }
        } else {
            zOpenDeviceHeaphoneMonitor = false;
        }
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] enableHeadphoneMonitor end");
        return zOpenDeviceHeaphoneMonitor;
    }

    public void enableRecord(boolean z12, boolean z13) {
        Log.i(TAG, "[KWStannis] enableRecord start: " + z12);
        if (this.audioRecordEnabled == z12) {
            Log.i(TAG, "[KWStannis] record already enabled!");
            return;
        }
        this.audioRecordEnabled = z12;
        if (this.audioRecordStartedByUser) {
            this.audioManagerLock.lock();
            if (this.audioRecordEnabled) {
                StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
                if (stannisAudioManagerInterface != null && !stannisAudioManagerInterface.startRecording(this.audioDeviceScene)) {
                    Log.e(TAG, "[KWStannis] enableRecord startRecording failed");
                }
            } else {
                StannisAudioManagerInterface stannisAudioManagerInterface2 = this.stannisAudioManager;
                if (stannisAudioManagerInterface2 != null && !stannisAudioManagerInterface2.stopRecording(z13)) {
                    Log.e(TAG, "[KWStannis] enableRecord stopRecording failed");
                }
            }
            this.audioManagerLock.unlock();
        }
        Log.i(TAG, "[KWStannis] enableRecord end");
    }

    public QosInfo getQosInfo(int i12) {
        nativeUploadSpeakerDeviceVolume(this.nativeStannis, getSpeakerDeviceVolume());
        return nativeGetQosInfo(this.nativeStannis, i12);
    }

    public boolean isSupportHeadphoneMonitor() {
        Log.i(TAG, "[KWStannis] isSupportHeadphoneMonitor start");
        this.audioManagerLock.lock();
        StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
        boolean zIsSupportHeaphoneMonitor = stannisAudioManagerInterface != null ? stannisAudioManagerInterface.isSupportHeaphoneMonitor() : false;
        this.audioManagerLock.unlock();
        Log.i(TAG, "[KWStannis] isSupportHeadphoneMonitor end");
        return zIsSupportHeaphoneMonitor;
    }

    public boolean startBgm(ArrayList<String> arrayList, ArrayList<String> arrayList2, boolean z12, int i12, BgmObserver bgmObserver) {
        return startBgm(arrayList, arrayList2, z12, i12, bgmObserver, (DataReadyObserver) null);
    }

    public boolean startPlayBgm(ArrayList<String> arrayList, ArrayList<String> arrayList2, int i12, int i13, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver) {
        return startPlayInternal(arrayList, arrayList2, i12, i13, null, bgmObserver, dataReadyObserver);
    }

    public void startRecordFile(String str, RecordFileObserver recordFileObserver, String str2) {
        if (this.isAudioDeviceStarted) {
            nativeStartRecordFile(this.nativeStannis, str, recordFileObserver, str2);
        } else {
            Log.e(TAG, "[KWStannis] startRecordFile record device not started.");
        }
    }

    public void startVocalBgmSync(byte[] bArr, int i12, int i13) {
        nativeStartVocalBgmSyncByData(this.nativeStannis, bArr, i12, i13);
    }

    public void stopRecordFile(String str, String str2) {
        nativeStopRecordFile(this.nativeStannis, str, str2);
    }

    public boolean startBgm(ArrayList<String> arrayList, boolean z12, int i12, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver) {
        return startBgm(arrayList, (ArrayList<String>) null, z12, i12, bgmObserver, dataReadyObserver);
    }

    public boolean startBgm(ArrayList<String> arrayList, ArrayList<String> arrayList2, boolean z12, int i12, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver) {
        if (arrayList != null && arrayList.size() != 0) {
            return startBgm(arrayList, arrayList2, z12, i12, null, bgmObserver, dataReadyObserver);
        }
        Log.e(TAG, "startBgm error: urlList is empty");
        return false;
    }

    public boolean startBgm(ArrayList<String> arrayList, boolean z12, int i12, ArrayList<KWStannisBgmGuidance> arrayList2, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver) {
        return startBgm(arrayList, null, z12, i12, arrayList2, bgmObserver, dataReadyObserver);
    }

    public boolean startBgm(ArrayList<String> arrayList, ArrayList<String> arrayList2, boolean z12, int i12, ArrayList<KWStannisBgmGuidance> arrayList3, BgmObserver bgmObserver, DataReadyObserver dataReadyObserver) {
        return startPlayInternal(arrayList, arrayList2, z12 ? -1 : 1, i12, arrayList3, bgmObserver, dataReadyObserver);
    }

    public int startPipelineWithNativePtr(int i12, int i13, long j12) {
        Log.i(TAG, "[KWStannis] startPipelineWithNativePtr start, vad_flow_id = " + i13);
        startPipelineCheckAndReset(i12);
        int iStartAudioDevice = 0;
        if (!needStartPipeline(i12)) {
            Log.i(TAG, "[KWStannis] startPipelineWithNativePtr start, vad_flow_id = " + i13 + ", not need to restart pipeline: scene=" + i12 + ", current_scene=" + this.audioDeviceScene);
            return 0;
        }
        boolean zNativeGetConfig = nativeGetConfig(this.nativeStannis, 2, this.userSetOutputType == 0, i12, this.audio_confg);
        boolean zNativeGetIsLiveStreamEnable = nativeGetIsLiveStreamEnable(this.nativeStannis);
        boolean zNativeGetIsAudioChatEnable = nativeGetIsAudioChatEnable(this.nativeStannis);
        if (zNativeGetIsLiveStreamEnable) {
            if (!this.isLiveSteamStarted) {
                StartLiveStreamWithNativePtr(0, i13, j12);
            }
        } else if (this.isLiveSteamStarted) {
            StopAllStream();
        }
        if (this.isChatStreamStarted) {
            StopAllLiveChat();
        }
        if (zNativeGetIsAudioChatEnable) {
            StartLiveChatWithNativePtr(1, i13, j12);
        }
        nativeSetMuteMicrophone(this.nativeStannis, this.muteType);
        if (i12 != 1280) {
            nativeStopSilenceGenerator(this.nativeStannis);
        }
        boolean z12 = this.isAudioDeviceStarted;
        if (!z12) {
            iStartAudioDevice = startAudioDevice(i12);
        } else if (zNativeGetConfig) {
            if (z12) {
                stopAudioDevice();
            }
            iStartAudioDevice = startAudioDevice(i12);
        } else {
            this.audioManagerLock.lock();
            StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
            if (stannisAudioManagerInterface != null) {
                stannisAudioManagerInterface.updateAudioDeviceConfig(i12);
                this.stannisAudioManager.setAudioRouteListener(this.audioRouteListener);
            }
            this.audioManagerLock.unlock();
        }
        if (i12 == 1280) {
            nativeStartSilenceGenerator(this.nativeStannis);
        }
        this.isPipeLineStarted = true;
        this.audioDeviceScene = i12;
        notifyAudioSceneChange(true, i12);
        Log.i(TAG, "[KWStannis] startPipelineWithNativePtr end, vad_flow_id = " + i13);
        return iStartAudioDevice;
    }

    public int startPipeline(int i12, int i13, DataReadyObserver dataReadyObserver) {
        Log.i(TAG, "[KWStannis] startPipeline start vad_flow_id" + i13);
        startPipelineCheckAndReset(i12);
        int iStartAudioDevice = 0;
        if (!needStartPipeline(i12)) {
            Log.i(TAG, "[KWStannis] startPipeline end, vad_flow_id" + i13 + ", not need to restart pipeline: scene=" + i12 + ", current_scene=" + this.audioDeviceScene);
            return 0;
        }
        this.dataReadyObserver = dataReadyObserver;
        boolean zNativeGetConfig = nativeGetConfig(this.nativeStannis, 2, this.userSetOutputType == 0, i12, this.audio_confg);
        boolean zNativeGetIsLiveStreamEnable = nativeGetIsLiveStreamEnable(this.nativeStannis);
        boolean zNativeGetIsAudioChatEnable = nativeGetIsAudioChatEnable(this.nativeStannis);
        if (zNativeGetIsLiveStreamEnable) {
            if (!this.isLiveSteamStarted) {
                StartLiveStream(0, i13, dataReadyObserver);
            }
        } else if (this.isLiveSteamStarted) {
            StopAllStream();
        }
        if (this.isChatStreamStarted) {
            StopAllLiveChat();
        }
        if (zNativeGetIsAudioChatEnable) {
            StartLiveChat(1, i13, dataReadyObserver);
        }
        nativeSetMuteMicrophone(this.nativeStannis, this.muteType);
        if (i12 != 1280) {
            nativeStopSilenceGenerator(this.nativeStannis);
        }
        boolean z12 = this.isAudioDeviceStarted;
        if (!z12) {
            iStartAudioDevice = startAudioDevice(i12);
        } else if (zNativeGetConfig) {
            if (z12) {
                stopAudioDevice();
            }
            iStartAudioDevice = startAudioDevice(i12);
        } else {
            this.audioManagerLock.lock();
            StannisAudioManagerInterface stannisAudioManagerInterface = this.stannisAudioManager;
            if (stannisAudioManagerInterface != null) {
                stannisAudioManagerInterface.updateAudioDeviceConfig(i12);
                this.stannisAudioManager.setAudioRouteListener(this.audioRouteListener);
            }
            this.audioManagerLock.unlock();
        }
        if (i12 == 1280) {
            nativeStartSilenceGenerator(this.nativeStannis);
        }
        this.isPipeLineStarted = true;
        this.audioDeviceScene = i12;
        notifyAudioSceneChange(true, i12);
        Log.i(TAG, "[KWStannis] startPipeline end, vad_flow_id" + i13);
        return iStartAudioDevice;
    }
}
