package com.kwai.video.arya;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.projection.MediaProjection;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Pair;
import com.kuaishou.client.log.event.packages.nano.ClientEvent;
import com.kuaishou.weapon.gp.f2;
import com.kuaishou.weapon.gp.k2;
import com.kwai.video.arya.GL.EglBase;
import com.kwai.video.arya.GL.TextureBuffer;
import com.kwai.video.arya.annotations.CalledFromNative;
import com.kwai.video.arya.observers.AryaCallObserver;
import com.kwai.video.arya.observers.AryaCallObserverInner;
import com.kwai.video.arya.observers.AryaQosObserver;
import com.kwai.video.arya.observers.AryaResultObserver;
import com.kwai.video.arya.observers.AudioBufferPlayObserver;
import com.kwai.video.arya.observers.AudioMixerObserver;
import com.kwai.video.arya.observers.AudioRecordingObserver;
import com.kwai.video.arya.observers.AudioSceneObserver;
import com.kwai.video.arya.observers.AudioSegmentPlayerObserver;
import com.kwai.video.arya.observers.BgmObserver;
import com.kwai.video.arya.observers.BroadcastObserver;
import com.kwai.video.arya.observers.ConnectivityObserver;
import com.kwai.video.arya.observers.FileStreamingObserver;
import com.kwai.video.arya.observers.KaraokeScoreObserver;
import com.kwai.video.arya.observers.LiveStreamBgmOffsetObserver;
import com.kwai.video.arya.observers.MediaFrameObserver;
import com.kwai.video.arya.observers.RawAudioObserver;
import com.kwai.video.arya.utils.Log;
import com.kwai.video.stannis.observers.DataReadyObserver;
import com.kwai.yoda.model.LaunchModelInternal;
import com.yxcorp.bugly.CrashReporter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes8.dex */
public class Arya {
    public static final int ARYA_QOS_CHAT_WAY = 1;
    public static final int ARYA_QOS_DISABLE_ALL = 0;
    public static final int ARYA_QOS_ENABLE_NETWORK_INFO = 4;
    public static final int ARYA_QOS_ENABLE_REALTIME = 1;
    public static final int ARYA_QOS_ENABLE_SUMMARY = 2;
    public static final int ARYA_QOS_LIVE_WAY = 0;
    public static final int ARYA_QOS_TYPE_NETWORK_INFO = 4;
    public static final int ARYA_QOS_TYPE_REALTIME = 1;
    public static final int ARYA_QOS_TYPE_SUMMARY = 2;
    public static final int ARYA_QOS_TYPE_UNKNOWN = 0;
    public static final int ARYA_QOS_UPLOAD_INTERVAL_DEFAULT = 10000;
    public static final int KWAryaAgcDefault = 0;
    public static final int KWAryaAgcDisable = 2;
    public static final int KWAryaAgcEnable = 1;
    public static final int KWAryaAudioBypassDataWithAEC = 1;
    public static final int KWAryaAudioLowDelayModeAccelarate = 1;
    public static final int KWAryaAudioLowDelayModeNormal = 0;
    public static final int KWAryaAudioOutputTypeAuto = 0;
    public static final int KWAryaAudioOutputTypeBluetooth = 5;
    public static final int KWAryaAudioOutputTypeLine = 3;
    public static final int KWAryaAudioOutputTypeReceiver = 2;
    public static final int KWAryaAudioOutputTypeSpeaker = 1;
    public static final int KWAryaAudioOutputTypeUSB = 4;
    public static final int KWAryaAudioPlayingStop = 2;
    public static final int KWAryaAudioRecordTypeAac = 0;
    public static final int KWAryaAudioRecordTypePcm = 1;
    public static final int KWAryaAudioRecordingStop = 1;
    public static final int KWAryaAudioRouteInput = 1;
    public static final int KWAryaAudioRouteOutput = 0;
    public static final int KWAryaAudioRouterBuiltinMic = 20;
    public static final int KWAryaAudioRoutingEarpiece = 1;
    public static final int KWAryaAudioRoutingHdmi = 7;
    public static final int KWAryaAudioRoutingHeadset = 0;
    public static final int KWAryaAudioRoutingHeadsetBluetooth = 5;
    public static final int KWAryaAudioRoutingHeadsetNoMic = 2;
    public static final int KWAryaAudioRoutingLoudspeaker = 4;
    public static final int KWAryaAudioRoutingSpeakerphone = 3;
    public static final int KWAryaAudioRoutingUnknown = -1;
    public static final int KWAryaAudioRoutingUsb = 6;
    public static final int KWAryaBatteryBatteryStateCharging = 2;
    public static final int KWAryaBatteryStateFull = 3;
    public static final int KWAryaBatteryStateUnknown = 0;
    public static final int KWAryaBatteryStateUnplugged = 1;
    public static final int KWAryaDefaultCameraSourceId = 1;
    public static final int KWAryaDefaultDirectorSourceId = 0;
    public static final int KWAryaDefaultSceneId = 100;
    public static final int KWAryaDirectorMixModeFill = 3;
    public static final int KWAryaDirectorMixModeFit = 2;
    public static final int KWAryaDirectorMixModeHidden = 1;
    public static final int KWAryaDirectorOutputModeAllowBlack = 1;
    public static final int KWAryaDirectorOutputModeAllowPartialBlackOnly = 2;
    public static final int KWAryaDirectorOutputModeNotAllowBlack = 0;
    public static final int KWAryaMediaModeAudioAndVideo = 2;
    public static final int KWAryaMediaModeAudioOnly = 0;
    public static final int KWAryaMediaModeVideoOnly = 1;
    public static final int KWAryaMetadataTypeAudio = 1;
    public static final int KWAryaMetadataTypeUnknown = -1;
    public static final int KWAryaMetadataTypeVideo = 0;
    public static final int KWAryaNetworkTypeTelephone = 1;
    public static final int KWAryaNetworkTypeUnkown = 0;
    public static final int KWAryaNetworkTypeWifi = 2;
    public static final int KWAryaPropertyFlagWriteToDebugInfo = 0;
    public static final int KWAryaPropertyFlagWriteToPrivKey = 2;
    public static final int KWAryaPropertyFlagWriteToQos = 1;
    public static final int KWAryaRequestAudioFocusFailed = 0;
    public static final int KWAryaSoftAecDefault = 0;
    public static final int KWAryaSoftAecDisable = 2;
    public static final int KWAryaSoftAecEnable = 1;
    public static final int KWAryaSoftAecLight = 4;
    public static final int KWAryaSoftAecMovieRecord = 3;
    public static final int KWAryaStreamCapabilityNone = 0;
    public static final int KWAryaStreamCapabilityRecvAudio = 4;
    public static final int KWAryaStreamCapabilityRecvVideo = 8;
    public static final int KWAryaStreamCapabilitySendAudio = 1;
    public static final int KWAryaStreamCapabilitySendRecvCtrl = 16;
    public static final int KWAryaStreamCapabilitySendVideo = 2;
    public static final int KWAryaStreamOperateTypeAdd = 1;
    public static final int KWAryaStreamOperateTypeRemove = 2;
    public static final int KWAryaStreamOperateTypeUnknown = 0;
    public static final int KWAryaStreamTypeAll = 3;
    public static final int KWAryaStreamTypeLiveChat = 2;
    public static final int KWAryaStreamTypeLiveStream = 1;
    public static final int KWAryaStreamTypeUnknown = 0;
    public static final int KWAryaVideoDroppedByFrameFilter = 1;
    public static final int KWAryaVideoDroppedByLowMemory = 2;
    public static final int KWAryaVideoDroppedByOtherReasons = 3;
    public static final int KWAryaVideoMixTypeDirector = 4;
    public static final int KWAryaVideoMixTypeExternalSource = 3;
    public static final int KWAryaVideoMixTypeOverlay = 1;
    public static final int KWAryaVideoMixTypeSideBySide = 2;
    public static final int KWAryaVideoMixTypeSingle = 0;
    public static final int KWAryaVideoProcessed = 0;
    public static final int KWAryaVideoReqMode1PlusN = 1;
    public static final int KWAryaVideoReqMode1PlusNForDynRes = 3;
    public static final int KWAryaVideoReqModeMxN = 2;
    public static final int KWAryaVoicePartyKtvRoleAudience = 1;
    public static final int KWAryaVoicePartyKtvRoleSinger = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static ConnectivityManager.NetworkCallback f28478f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static ConnectivityManager.NetworkCallback f28479g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static ConnectivityManager.NetworkCallback f28480h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static Network f28481i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static Network f28482j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static Network f28483k = null;
    public static final int kAudioSceneChatOnly = 1536;
    public static final int kAudioSceneGameChat = 1024;
    public static final int kAudioSceneLiveChat = 768;
    public static final int kAudioSceneLiveGroupChat = 2048;
    public static final int kAudioSceneLiveStream = 512;
    public static final int kAudioSceneLiveStreamWithChat = 1792;
    public static final int kAudioSceneMovieRecord = 256;
    public static final int kAudioScenePlayAudio = 1280;
    public static final int kAudioSceneRecordOnly = 2816;
    public static final int kBT601FullCsp = 1;
    public static final int kBT601LimitCsp = 0;
    public static final int kBT709FullCsp = 3;
    public static final int kBT709LimitCsp = 2;
    public static final int kBitmapABGR = 3;
    public static final int kBitmapARGB = 1;
    public static final int kBitmapBGRA = 2;
    public static final int kBitmapRGBA = 0;
    public static final int kHowlingDetect = 1;
    public static final int kHowlingMaxModes = 3;
    public static final int kHowlingNoProcess = 0;
    public static final int kHowlingSuprress = 2;
    public static final int kKtpFlowAVInterleave = 0;
    public static final int kKtpFlowAudioDropEvenly = 2;
    public static final int kKtpFlowAudioFInFOut = 1;
    public static final int kMicMute = 1;
    public static final int kMicMuteAndFillComfortableNoise = 2;
    public static final int kMicMutePipeline = 3;
    public static final int kMicUnMute = 0;
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
    public static final int kReverbRecord = 8;
    public static final int kReverbReverb = 5;
    public static final int kReverbStage = 10;
    public static final int kReverbStudio = 9;
    public static final int kReverbSuperStar = 13;
    public static final int kRoleFemale = 2;
    public static final int kRoleGeneral = 0;
    public static final int kRoleMale = 1;
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
    public static final int kVoicePartyModeKTV = 1;
    public static final int kVoicePartyModeNormal = 0;
    private AryaContext A;
    private c B;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AudioRecordingObserver f28487c;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Context f28494p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private SignalMessageHandler f28495q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private long f28496r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private AryaQosObserver f28497s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private a f28502x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private a.C0440a f28503y;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f28485a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f28486b = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f28488d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f28489e = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ConnectivityManager f28490l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f28491m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f28492n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f28493o = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f28498t = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f28499u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private String f28500v = "";

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private String f28501w = "";

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f28504z = false;
    private boolean C = false;
    private boolean D = true;
    private String E = "";
    private String F = "";
    private String G = "";
    private boolean H = false;
    private Timer I = null;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    private DataReadyObserver f28484J = null;
    private boolean K = false;
    private boolean L = true;
    private BroadcastReceiver M = null;
    private BroadcastReceiver N = null;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ARYA_KTP_FLOW_MODE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ARYA_MIC_MUTE_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ARYA_REVERB_LEVEL {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ARYA_VIDEO_COLOR_SPACE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ARYA_VOICE_EFFECT_OPTION {
    }

    public interface AryaAudioDeviceStatusListener {
        void onAudioDeviceStatusChange(int i12);
    }

    public interface AryaAudioRouteListener {
        void onRouteChange(int i12, int i13);
    }

    public static class AryaConfig {
        public String packageName = null;
        public String appName = null;
        public String appVersion = null;
        public String appUserId = null;
        public String deviceId = null;
        public boolean isAnchor = false;
        public int qosEnableFlag = 3;
        public int qosUploadInterval = 10000;
        public int makeCallTimeoutMs = k2.f22582a6;
        public int ktpFlowMode = 1;
        public boolean videoEnableHevc = false;
        public boolean videoEnableHwEnc = false;
        public boolean videoEnableHwDec = false;
        public String videoEncConfig = "";
        public String aryaConfig = "";
        public int videoTargetFps = 15;
        public int videoMinFpsForAdapt = 10;
        public int videoTargetWidth = 360;
        public int videoTargetHeight = 640;
        public boolean videoEnableCrop = false;
        public boolean videoEnableCutForVoiceParty = true;
        public boolean videoEnableInsertFrameForChat = true;
        public boolean videoEnableInnerMix = true;
        public int videoInitBitrateKbps = ClientEvent.TaskEvent.Action.CLOSE_OPERATION_ENTRANCE;
        public int videoMinBitrateKbps = 200;
        public int videoMaxBitrateKbps = ClientEvent.TaskEvent.Action.QUIZ_BALANCE_BIND_ACCOUNT;
        public int videoKeyFrameInterval = 4;
        public int videoDropBefEncStatPeriodMs = ClientEvent.TaskEvent.Action.SHOW_LIVE_PK_RULE_POPUP;
        public int videoDropBefEncAvgFpsToNotify = 5;
        public float videoGuestPositionLeft = 0.7f;
        public float videoGuestPositionTop = 0.7f;
        public float videoGuestPositionWidth = 0.25f;
        public float videoGuestPositionHeight = 0.25f;
        public int speakerInactiveMinIntervalMs = 500;
        public boolean localLoopback = false;
        public boolean enableFrameRateDynAdapt = false;
        public int dumpEnableFlag = 0;
        public String dumpPath = "";
        private String documentRootPath = "";
        private String debugFilesPath = "";
        public boolean enableAudioVad = true;
        public boolean enableNeedleLog = false;
        public boolean enableVEncTest = false;
        public boolean useOfflineRecord = false;
        public boolean offlineRecordNeedMux = true;
        public boolean offlineRecordDropFrame = false;
        public boolean enableLowMemory = false;
        public int offlineRecordMediaMode = 2;
        public int rtcMediaMode = 2;
        public int videoPoolCapacity = 2;
        public boolean enableNetState = false;
        public int previewWidth = 0;
        public int previewHeight = 0;
        public int captureWidth = 0;
        public int captureHeight = 0;
        public boolean useExternalAudioDevice = false;
        public boolean enableAudioPreProcess = true;
        public String debugInfoInMeta = "";
        public int momentsCapacityMs = 0;
        public boolean enableVideoRequest = false;
        public boolean requestAllVideos = true;
        public boolean liveStreamKeepAspectRatio = false;
        public float liveStreamEffectiveAreaX = 0.0f;
        public float liveStreamEffectiveAreaY = 0.0f;
        public int userNetworkType = 0;
        public String freeTrafficType = "";
        public boolean enableDebugInfo = false;
        public boolean enableDebugMode = false;
    }

    public static class AryaDeviceInfo {

        /* JADX INFO: renamed from: id, reason: collision with root package name */
        public int f28505id;
        public String name;
        public int routeType;

        public String toString() {
            return "AryaDeviceInfo{id=" + this.f28505id + ", name='" + this.name + "', routeType=" + this.routeType + '}';
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioDeviceStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BITMAP_COLOR_SPACE {
    }

    public static class DirectorConfig {
        public EglBase.Context eglContext = null;
        public int mixMode;
        public int outputHeight;
        public int outputWidth;
        public int sceneId;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HOWLING_MODE {
    }

    public static class InitParam {
        public boolean needCreateEglContext = true;
        public EglBase.Context eglContext = null;
        public boolean enableWebSocket = false;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaAgcMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaAudioBypassDataOptions {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaAudioLowDelayMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaAudioOutputType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaAudioRecordType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaAudioRouteType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaAudioRouting {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaBatteryState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaDirectorMixMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaDirectorOutputMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaInputRawVideoResult {
    }

    public static class KWAryaLayout {
        public int sourceId = 0;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f28508x = 0;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f28509y = 0;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f28507w = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f28506h = 0;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f28510z = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaMediaMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaMetadataType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaNetworkType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaPropertyFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaSoftAecMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaStreamCapability {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaStreamOperateType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaStreamType {
    }

    public static class KWAryaVideoEncodeParameter {
        public int width = 0;
        public int height = 0;
        public int fps = 0;
        public int maxBitrate = 0;
        public boolean resumeOnRtcStop = true;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaVideoMixType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaVideoReqMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KWAryaVoicePartyKtvRole {
    }

    public static class LayoutItem {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f28511h;
        public ParticipantMediaInfo mediaInfo;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public float f28512w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public float f28513x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public float f28514y;

        public LayoutItem(float f12, float f13, float f14, float f15, ParticipantMediaInfo participantMediaInfo) {
            this.f28513x = f12;
            this.f28514y = f13;
            this.f28512w = f14;
            this.f28511h = f15;
            this.mediaInfo = participantMediaInfo;
        }
    }

    public static class LiveStreamParam {
        public NicInfo[] localNics;
        public boolean pushOrigin = true;
        public String callId = "";
        public String idc = "";
        public String rtmpUrl = "";
        public boolean audioOnly = false;
    }

    public static class NicInfo {

        /* JADX INFO: renamed from: ip, reason: collision with root package name */
        public String f28515ip;
        public boolean isCellular;
        public int socketFd = -1;
    }

    public static class ParticipantMediaInfo {
        public int sessionId;
        public String userId;
        public int videoSourceType;

        public ParticipantMediaInfo(String str, int i12, int i13) {
            this.userId = str;
            this.videoSourceType = i12;
            this.sessionId = i13;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface QOS_TYPE {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VOICE_ROLE_OPTION {
    }

    static {
        com.kwai.video.arya.utils.b.a();
        f28478f = null;
        f28479g = null;
        f28480h = null;
        f28481i = null;
        f28482j = null;
        f28483k = null;
    }

    Arya(Context context, a aVar, AryaContext aryaContext) {
        this.f28502x = null;
        this.f28503y = null;
        this.A = null;
        this.B = null;
        this.A = aryaContext;
        this.f28496r = nativeCreateVoip(aryaContext.f28789z);
        this.f28494p = context;
        this.f28502x = aVar;
        a.C0440a c0440a = new a.C0440a();
        this.f28503y = c0440a;
        c0440a.f28836b = this.f28496r;
        this.B = new c();
    }

    private native int nativeBindSocket(long j12, long j13);

    private native void nativeBroadcastToOtherParticipants(long j12, byte[] bArr, int i12);

    private native boolean nativeCreateDirector(long j12, int i12, int i13);

    private native boolean nativeCreateScene(long j12, int i12, EglBase.Context context, int i13);

    private native long nativeCreateVoip(long j12);

    private native void nativeDestroyDirector(long j12);

    private native void nativeDestroySceneWithId(long j12, int i12);

    private native void nativeDestroyVoip(long j12);

    private native void nativeDisableVideoMix(long j12);

    private native void nativeEnableVideoMix(long j12);

    private native int nativeGetAudioFlowIdByUserId(long j12, String str);

    private native long nativeGetNetSpeedMeasureSessionId(long j12);

    private native int nativeGetNetState(long j12);

    private native ParticipantMediaInfo[] nativeGetParticipantMediaInfo(long j12, String str);

    private native String[] nativeGetParticipantsList(long j12);

    private native String nativeGetPeerUplinkReports(long j12, String str);

    private native QosInfo nativeGetQosInfo(long j12);

    private native String nativeGetSignatureForJoinChannel(long j12, String str, String str2, String str3, String str4, long j13);

    private native String nativeGetUserIdByAudioFlowId(long j12, int i12);

    private native LayoutItem[] nativeGetVideoLayout(long j12, ParticipantMediaInfo[] participantMediaInfoArr);

    private native boolean nativeHasPKSession(long j12);

    private native void nativeInitVoip(long j12, AryaCallObserverInner aryaCallObserverInner, boolean z12);

    private native void nativeInputImageToDirectorSource(long j12, ByteBuffer byteBuffer, int i12, int i13, int i14);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeInputRawAudioByteArray(long j12, byte[] bArr, int i12, int i13, int i14, long j13, boolean z12, int i15);

    private native int nativeInputRawVideoByteArray(long j12, int i12, byte[] bArr, int i13, int i14, int i15, long j13, int i16, int i17, String str, int i18);

    private native int nativeInputRawVideoByteBuffer(long j12, int i12, ByteBuffer byteBuffer, int i13, int i14, long j13, int i15, int i16, String str, int i17, boolean z12);

    private native int nativeInputRawVideoTextureBuffer(long j12, TextureBuffer textureBuffer, String str, int i12, boolean z12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeIsEnableDirectorScaleSize(long j12);

    private native boolean nativeIsLiveStreamSessionId(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeIsPKSessionId(long j12, int i12);

    private native boolean nativeIsStartingFileStreaming(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeNotifyNetworkChange(long j12, int i12);

    private native void nativeOnBackground();

    private native void nativeOnForeground();

    private native void nativePause(long j12);

    private native void nativePauseFileStreaming(long j12);

    private native void nativePauseLiveRecording(long j12);

    private static native void nativeProbeConnectivity(long j12, String str, int i12, int i13, int i14, ConnectivityObserver connectivityObserver);

    private native void nativeReportRoomConfig(long j12, String str, String str2);

    private native void nativeRequestVideoForParticipants(long j12, String str, ArrayList<ParticipantMediaInfo> arrayList, int i12);

    private native void nativeResume(long j12);

    private native void nativeResumeFileStreaming(long j12);

    private native void nativeResumeLiveRecording(long j12);

    private native void nativeSaveMoments(long j12, String str, AryaResultObserver aryaResultObserver);

    private native void nativeSeekFileStreamingToMs(long j12, long j13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSendForceTransfer(long j12, boolean z12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSendMetaData(long j12, String str, String str2, long j13);

    private native void nativeSendScreenCastInfo(long j12, byte[] bArr);

    private native boolean nativeSetAsMainSourceOfScene(long j12, int i12, int i13);

    private native void nativeSetAudioLowDelayMode(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetBatteryInfo(long j12, int i12, int i13);

    private native void nativeSetBroadcastObserver(long j12, BroadcastObserver broadcastObserver);

    private native void nativeSetCodecFactories(long j12, Context context, EglBase.Context context2);

    private native void nativeSetConfigs(long j12, AryaConfig aryaConfig);

    private native void nativeSetDataInLiveStream(long j12, byte[] bArr);

    private native void nativeSetIntProperty(long j12, String str, int i12, int i13);

    private native void nativeSetKtpModelPath(long j12, String str);

    private native void nativeSetLiveStreamBgmOffsetCb(long j12, int i12, LiveStreamBgmOffsetObserver liveStreamBgmOffsetObserver);

    private native void nativeSetLiveStreamRtmpUrl(long j12, String str);

    private native void nativeSetLiveStreamVideoEncodeParam(long j12, int i12, int i13, int i14, int i15, boolean z12);

    private native void nativeSetLocalNetworkInfo(long j12, int i12, String str);

    private native void nativeSetLogo(long j12, ByteBuffer byteBuffer, int i12, int i13, float f12, float f13, int i14, boolean z12);

    private native void nativeSetMediaCallback(long j12, MediaFrameObserver mediaFrameObserver, int i12);

    private native void nativeSetMixType(long j12, int i12);

    private native void nativeSetMuteAllRemoteAudioStreams(long j12, String str, boolean z12);

    private native void nativeSetMuteMicOfAllOthers(long j12, boolean z12);

    private native void nativeSetMuteMicOfOtherOne(long j12, String str, boolean z12);

    private native void nativeSetMuteMicrophone(long j12, boolean z12);

    private native void nativeSetMuteRemoteAudioStreams(long j12, String str, String str2, boolean z12);

    private native void nativeSetReplacedImage(long j12, ByteBuffer byteBuffer, int i12, int i13, int i14);

    private native void nativeSetRotation(long j12, int i12, int i13);

    private native void nativeSetRtcAudioDtxIgnored(long j12, boolean z12);

    private native void nativeSetSignalingMessage(long j12, byte[] bArr);

    private native void nativeSetSignalingMessageJson(long j12, String str);

    private native void nativeSetStringProperty(long j12, String str, String str2, int i12);

    private native void nativeSetTsptInfo(long j12, byte[] bArr);

    private native void nativeSetVideoBitrateTable(long j12, String str, String str2);

    private native void nativeSetVideoLayoutScheme(long j12, String str);

    private native void nativeSetVideoMaxBitrate(long j12, int i12, String str);

    private native void nativeSetVideoMirror(long j12, boolean z12);

    private native boolean nativeSetVideoMixOutputMode(long j12, int i12, int i13);

    private native void nativeSetVideoTxDisabled(long j12, int i12);

    private native void nativeSetVideoTxEnabled(long j12, int i12);

    private native void nativeSetVoicePartyKtvMode(long j12, int i12, int i13, int i14, int i15);

    private native void nativeSetWallClockTime(long j12, long j13);

    private native void nativeStartAudioRecording(long j12, AudioRecordingObserver audioRecordingObserver, int i12, int i13);

    private native void nativeStartCall(long j12, byte[] bArr, LiveStreamParam liveStreamParam, String str);

    private native void nativeStartLiveRecording(long j12, String str, AryaResultObserver aryaResultObserver);

    private native void nativeStartLiveRecordingForAudioMix(long j12, String str, AryaResultObserver aryaResultObserver, int i12);

    private native void nativeStartLiveStream(long j12, LiveStreamParam liveStreamParam, String str);

    private native void nativeStartMixRemoteAndLocalAudio(long j12, AudioMixerObserver audioMixerObserver);

    private native void nativeStartNetSpeedMeasure(long j12, String str, String str2, int i12, int i13);

    private native void nativeStartScreencast(long j12, String str, int i12, int i13, int i14, int i15, int i16);

    private native void nativeStartUrlFileStreaming(long j12, String[] strArr, String str, int i12, FileStreamingObserver fileStreamingObserver, AryaCallObserverInner aryaCallObserverInner);

    private native void nativeStopAudioRecording(long j12, AudioRecordingObserver audioRecordingObserver);

    private native void nativeStopCall(long j12, byte[] bArr);

    private native void nativeStopFileStreaming(long j12);

    private native void nativeStopLiveRecording(long j12, AryaResultObserver aryaResultObserver);

    private native void nativeStopLiveStream(long j12, String str);

    private native void nativeStopLowLatencyLiveByForce(long j12);

    private native void nativeStopMixRemoteAndLocalAudio(long j12);

    private native int nativeStopNetSpeedMeasure(long j12);

    private native void nativeStopRtcSessionByForce(long j12);

    private native void nativeStopScreencast(long j12);

    private native void nativeUninitVoip(long j12);

    private native boolean nativeUpdateLayoutForScene(long j12, int i12, KWAryaLayout[] kWAryaLayoutArr, Buffer buffer);

    private native void nativeUpdateMixOutputSize(long j12, int i12, int i13);

    @CalledFromNative
    private void onQosUpdated(int i12, int i13, String str, boolean z12) {
        if (i12 == 1 && z12) {
            String strC = this.f28502x.c(i13);
            if (!"{}".equals(strC)) {
                str = strC.substring(0, strC.length() - 1) + LaunchModelInternal.HYID_SEPARATOR + str.substring(1, str.length());
            }
        }
        try {
            if (this.f28497s == null || (this.f28498t & i12) == 0) {
                return;
            }
            Log.d("Arya", "QosUpload type: " + i12);
            this.f28497s.onQosEventUpdated(i12, str);
        } catch (NullPointerException e12) {
            CrashReporter.logCheckedException(e12, "Arya.class", "onQosUpdated", -64);
            e12.printStackTrace();
        }
    }

    @CalledFromNative
    private void sendSignalMessage(byte[] bArr) {
        SignalMessageHandler signalMessageHandler = this.f28495q;
        if (signalMessageHandler != null) {
            signalMessageHandler.sendSignalMessage(bArr);
        }
    }

    public void addAudioBypassDataObserver(RawAudioObserver rawAudioObserver, int i12) {
        this.f28502x.a(rawAudioObserver, i12);
    }

    public void addAudioSceneObserver(AudioSceneObserver audioSceneObserver) {
        this.f28502x.a(audioSceneObserver);
    }

    public void addMixTrack(int i12) {
        this.f28502x.p(i12);
    }

    public int bindSocket(long j12) {
        return nativeBindSocket(this.f28496r, j12);
    }

    public void broadcastToOtherParticipants(byte[] bArr) {
        nativeBroadcastToOtherParticipants(this.f28496r, bArr, 0);
    }

    public void cleanSoundEffectCache() {
        this.f28502x.m();
    }

    public void clearAllAudioBuffer() {
        this.f28502x.y();
    }

    public void clearAudioBuffer(String str) {
        this.f28502x.c(str);
    }

    public boolean createScene(DirectorConfig directorConfig) {
        if (directorConfig.eglContext == null) {
            directorConfig.eglContext = EglContextHolder.sharedContext();
        }
        nativeCreateDirector(this.f28496r, directorConfig.outputWidth, directorConfig.outputHeight);
        return nativeCreateScene(this.f28496r, directorConfig.sceneId, directorConfig.eglContext, directorConfig.mixMode);
    }

    public void destroyScene(int i12) {
        nativeDestroySceneWithId(this.f28496r, i12);
        nativeDestroyDirector(this.f28496r);
    }

    public void disableDenoise() {
        this.f28502x.b(false, "");
    }

    public void disableHeadphoneMonitor() {
        this.A.f28782s = false;
        this.f28502x.C();
    }

    public void disableVideoMix() {
        nativeDisableVideoMix(this.f28496r);
    }

    public void disableVideoTx(int i12) {
        nativeSetVideoTxDisabled(this.f28496r, i12);
    }

    public void enableDenoise(String str) {
        this.f28502x.b(true, str);
    }

    public boolean enableHeadphoneMonitor(boolean z12) {
        this.A.f28782s = true;
        return this.f28502x.o(z12);
    }

    public void enableMixingAudioSegment(boolean z12) {
        this.A.f28781r = z12;
        this.f28502x.l(z12);
    }

    public void enableVideoMix() {
        nativeEnableVideoMix(this.f28496r);
    }

    public void enableVideoTx(int i12) {
        nativeSetVideoTxEnabled(this.f28496r, i12);
    }

    protected void finalize() throws Throwable {
        a();
        super.finalize();
    }

    public String[] getActiveSpeakers() {
        ArrayList arrayList = new ArrayList();
        for (int i12 : this.f28502x.b()) {
            String strNativeGetUserIdByAudioFlowId = nativeGetUserIdByAudioFlowId(this.f28496r, i12);
            Log.d("Arya", "getActiveSpeakers transfer audio id: " + i12 + " to user id: " + strNativeGetUserIdByAudioFlowId);
            if (strNativeGetUserIdByAudioFlowId == null) {
                Log.w("Arya", "getActiveSpeakers get user id failed");
            } else if (this.H && strNativeGetUserIdByAudioFlowId.equals(this.G)) {
                Log.d("Arya", "getActiveSpeakers local user id: " + strNativeGetUserIdByAudioFlowId + " was muted, filter it");
            } else {
                Log.d("Arya", "getActiveSpeakers active speaker user id: " + strNativeGetUserIdByAudioFlowId);
                arrayList.add(strNativeGetUserIdByAudioFlowId);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public int[] getAllAudioOutputTypes() {
        return this.f28502x.g();
    }

    public int getAudioOutputType() {
        return this.f28502x.f();
    }

    public AryaDeviceInfo getCurrentDevice(int i12) {
        return this.f28502x.r(i12);
    }

    public AryaDeviceInfo[] getDevices(int i12) {
        return this.f28502x.s(i12);
    }

    public int getKaraokeAverageScore() {
        return this.f28502x.s();
    }

    public int getKaraokeScorePitch() {
        return this.f28502x.r();
    }

    public int getKaraokeTotalScore() {
        return this.f28502x.q();
    }

    public String getKtvQos() {
        return this.B.a(this.f28502x.o(1000));
    }

    public long getNetSpeedMeasureSessionId() {
        return nativeGetNetSpeedMeasureSessionId(this.f28496r);
    }

    public int getNetState() {
        return nativeGetNetState(this.f28496r);
    }

    public ParticipantMediaInfo[] getParticipantMediaInfo(String str) {
        return nativeGetParticipantMediaInfo(this.f28496r, str);
    }

    public String[] getParticipantsList() {
        return nativeGetParticipantsList(this.f28496r);
    }

    public String getPeerUplinkReports(String str) {
        return nativeGetPeerUplinkReports(this.f28496r, str);
    }

    public QosInfo getQosInfo() {
        QosInfo qosInfoNativeGetQosInfo = nativeGetQosInfo(this.f28496r);
        qosInfoNativeGetQosInfo.checkSelf(this.f28499u);
        return qosInfoNativeGetQosInfo;
    }

    public String getSignatureForJoinChannel(String str, String str2, String str3, String str4, long j12) {
        return nativeGetSignatureForJoinChannel(this.f28496r, str, str2, str3, str4, j12);
    }

    public int getSpeakerDeviceVolume() {
        return this.f28502x.c();
    }

    public LayoutItem[] getVideoLayout(ParticipantMediaInfo[] participantMediaInfoArr) {
        return nativeGetVideoLayout(this.f28496r, participantMediaInfoArr);
    }

    public int getVoiceEnergy(String str) {
        return this.f28502x.b(nativeGetAudioFlowIdByUserId(this.f28496r, str));
    }

    public synchronized boolean init(SignalMessageHandler signalMessageHandler, AryaCallObserver aryaCallObserver, AryaQosObserver aryaQosObserver, boolean z12) {
        return a(signalMessageHandler, aryaCallObserver, aryaQosObserver, (EglBase.Context) null, z12);
    }

    public void inputImageToDirectorSource(Bitmap bitmap, int i12) {
        if (bitmap != null) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bitmap.getByteCount());
            bitmap.copyPixelsToBuffer(byteBufferAllocateDirect);
            nativeInputImageToDirectorSource(this.f28496r, byteBufferAllocateDirect, bitmap.getWidth(), bitmap.getHeight(), i12);
        }
    }

    public void inputPcmPlay(byte[] bArr, int i12, int i13, int i14, long j12) {
        this.f28502x.c(bArr, i12, i13, i14, j12);
    }

    public void inputRawAudio(byte[] bArr, int i12, int i13, int i14, long j12) {
        if (this.C) {
            if (this.D) {
                this.f28502x.b(bArr, i12, i13, i14, j12);
            } else {
                nativeInputRawAudioByteArray(this.f28496r, bArr, i12, i13, i14, j12, false, 0);
            }
        }
    }

    public void inputRawAudioForChat(byte[] bArr, int i12, int i13, int i14, long j12) {
        nativeInputRawAudioByteArray(this.f28496r, bArr, i12, i13, i14, j12, true, 0);
    }

    public int inputRawVideo(int i12, byte[] bArr, int i13, int i14, long j12, int i15, int i16) {
        return nativeInputRawVideoByteArray(this.f28496r, i12, bArr, bArr.length, i13, i14, j12, i15, i16, "", 0);
    }

    public void inputSpeakerAudio(byte[] bArr, int i12, int i13, int i14, long j12) {
        this.f28502x.a(bArr, i12, i13, i14, j12);
    }

    public void inputTrackData(int i12, byte[] bArr, int i13, int i14, int i15, long j12) {
        this.f28502x.a(i12, bArr, i13, i14, i15, j12);
    }

    public void insertDataInLiveStream(byte[] bArr) {
        nativeSetDataInLiveStream(this.f28496r, bArr);
    }

    public void isStartingFileStreaming() {
        nativeIsStartingFileStreaming(this.f28496r);
    }

    public boolean isSupportHeadphoneMonitor(boolean z12) {
        return this.f28502x.n(z12);
    }

    public void loadSoundEffectCache(String str) {
        this.f28502x.a(str);
    }

    public void networkChanged(int i12, String str) {
        nativeSetLocalNetworkInfo(this.f28496r, i12, str);
    }

    public void onBackground() {
        nativeOnBackground();
    }

    public void onForeground() {
        nativeOnForeground();
    }

    public void pause() {
        nativePause(this.f28496r);
        this.f28502x.b(this.f28503y);
    }

    public void pauseAudioBuffer() {
        this.f28502x.x();
    }

    public void pauseBgm() {
        this.f28502x.i();
    }

    public void pauseFileStreaming() {
        nativePauseFileStreaming(this.f28496r);
    }

    public void pauseLiveRecording() {
        nativePauseLiveRecording(this.f28496r);
        this.f28502x.b(this.f28503y);
    }

    public void playAudioBuffer(String str, byte[] bArr, float f12, boolean z12, AudioBufferPlayObserver audioBufferPlayObserver) {
        this.f28502x.a(str, bArr, f12, z12, audioBufferPlayObserver);
    }

    public void playSoundEffect(String str, final BgmObserver bgmObserver) {
        this.f28502x.a(str, new a.c() { // from class: com.kwai.video.arya.Arya.8
            @Override // com.kwai.video.arya.a.c
            public void onCompleted(String str2) {
                bgmObserver.onCompleted(str2);
            }

            @Override // com.kwai.video.arya.a.c
            public void onError(String str2, BgmObserver.BgmErrorType bgmErrorType) {
                bgmObserver.onError(str2, bgmErrorType);
            }

            @Override // com.kwai.video.arya.a.c
            public void onProgressed(String str2, float f12, float f13) {
                bgmObserver.onProgressed(str2, f12, f13);
            }

            @Override // com.kwai.video.arya.a.c
            public void onStart(String str2) {
                bgmObserver.onStart(str2);
            }
        });
    }

    public void postReceivedSignalingMessage(byte[] bArr) {
        nativeSetSignalingMessage(this.f28496r, bArr);
    }

    public void postReceivedSignalingMessageJson(String str) {
        nativeSetSignalingMessageJson(this.f28496r, str);
    }

    public void probeConnectivity(String str, int i12, int i13, final ConnectivityObserver connectivityObserver) {
        if (str.isEmpty()) {
            Log.w("Arya", "probeConnection with empty address");
            if (connectivityObserver != null) {
                connectivityObserver.isConnectable(false);
                return;
            }
            return;
        }
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length > 2) {
            strArrSplit = str.split("]:");
        }
        if (strArrSplit.length != 2) {
            Log.e("Arya", "probeConnection invalid addr:" + str);
            if (connectivityObserver != null) {
                connectivityObserver.isConnectable(false);
                return;
            }
            return;
        }
        String strSubstring = strArrSplit[0];
        int i14 = Integer.parseInt(strArrSplit[1]);
        if (strSubstring.startsWith("[")) {
            strSubstring = strSubstring.substring(1, strSubstring.length());
        }
        String str2 = strSubstring;
        Log.i("Arya", "probeConnection addr:" + str + " ip:" + str2 + " port:" + i14);
        nativeProbeConnectivity(this.f28496r, str2, i14, i12, i13, new ConnectivityObserver() { // from class: com.kwai.video.arya.Arya.5
            @Override // com.kwai.video.arya.observers.ConnectivityObserver
            public void isConnectable(final boolean z12) {
                new Thread(new Runnable() { // from class: com.kwai.video.arya.Arya.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ConnectivityObserver connectivityObserver2 = connectivityObserver;
                        if (connectivityObserver2 != null) {
                            connectivityObserver2.isConnectable(z12);
                        }
                    }
                }).start();
            }
        });
    }

    public void removeAllAudioBypassDataObserver() {
        this.f28502x.w();
    }

    public void removeAudioSceneObserver(AudioSceneObserver audioSceneObserver) {
        this.f28502x.b(audioSceneObserver);
    }

    public void removeMixTrack(int i12) {
        this.f28502x.q(i12);
    }

    public void replaceVideoWithBitmap(Bitmap bitmap) {
        replaceVideoWithBitmap(bitmap, 3);
    }

    public void requestVideoForParticipants(String str, ArrayList<ParticipantMediaInfo> arrayList, int i12) {
        nativeRequestVideoForParticipants(this.f28496r, str, arrayList, i12);
    }

    public void resetPcmPlay() {
        this.f28502x.B();
    }

    public void resume() {
        this.f28502x.c(this.f28503y);
        nativeResume(this.f28496r);
    }

    public void resumeAudioBuffer(boolean z12) {
        this.f28502x.m(z12);
    }

    public void resumeBgm() {
        this.f28502x.j();
    }

    public void resumeFileStreaming() {
        nativeResumeFileStreaming(this.f28496r);
    }

    public void resumeLiveRecording() {
        this.f28502x.c(this.f28503y);
        nativeResumeLiveRecording(this.f28496r);
    }

    public void saveMoments(String str, AryaResultObserver aryaResultObserver) {
        nativeSaveMoments(this.f28496r, str, aryaResultObserver);
    }

    public void seekBgm(int i12) {
        this.f28502x.l(i12);
        this.B.a(i12);
    }

    public void seekFileStreamingToMs(long j12) {
        nativeSeekFileStreamingToMs(this.f28496r, j12);
    }

    public int sendMediaMetadataData(byte[] bArr, int i12) {
        if (bArr == null) {
            return -1;
        }
        if (i12 != 0) {
            return 0;
        }
        nativeSetTsptInfo(this.f28496r, bArr);
        return 0;
    }

    public void sendScreencastInfo(byte[] bArr) {
        nativeSendScreenCastInfo(this.f28496r, bArr);
    }

    public void setAgcMode(int i12) {
        this.A.f28788y = i12;
        this.f28502x.i(i12);
    }

    public void setAsMainSourceOfScene(int i12, int i13) {
        nativeSetAsMainSourceOfScene(this.f28496r, i12, i13);
    }

    public void setAudioDeviceStatusListener(AryaAudioDeviceStatusListener aryaAudioDeviceStatusListener) {
        this.f28502x.a(aryaAudioDeviceStatusListener);
    }

    public void setAudioInputVolume(float f12) {
        this.A.f28764a = f12;
        this.f28502x.c(f12);
    }

    public void setAudioLowDelayMode(int i12) {
        nativeSetAudioLowDelayMode(this.f28496r, i12);
    }

    public void setAudioOutputType(int i12) {
        this.f28502x.j(i12);
    }

    public void setAudioRouteListener(AryaAudioRouteListener aryaAudioRouteListener) {
        this.f28502x.a(aryaAudioRouteListener);
    }

    public void setAudioVoiceEffectOption(int i12) {
        this.A.f28766c = i12;
        this.f28502x.e(i12);
    }

    public void setAudioVoiceRoleOption(int i12) {
        this.A.f28767d = i12;
        this.f28502x.f(i12);
    }

    public void setBgmPitch(int i12) {
        AryaContext aryaContext = this.A;
        if (i12 != aryaContext.f28778o) {
            aryaContext.f28778o = i12;
            this.f28502x.m(i12);
            f();
        }
    }

    public void setBgmVolume(float f12) {
        AryaContext aryaContext = this.A;
        if (f12 != aryaContext.f28775l) {
            aryaContext.f28775l = f12;
            this.f28502x.e(f12);
            f();
        }
    }

    public void setBroadcastObserver(BroadcastObserver broadcastObserver) {
        nativeSetBroadcastObserver(this.f28496r, broadcastObserver);
    }

    public void setCaptureSize(int i12, int i13) {
        nativeSetIntProperty(this.f28496r, "cap_w", i12, 0);
        nativeSetIntProperty(this.f28496r, "cap_h", i13, 0);
    }

    public void setEnableDeepNs(boolean z12, String str) {
        this.f28502x.a(z12, str);
    }

    public void setEnableInnerMix(boolean z12) {
        this.L = z12;
    }

    public void setEnableNoiseSuppression(boolean z12) {
        this.A.f28771h = z12;
        this.f28502x.g(z12);
    }

    public void setEnableRecording(boolean z12) {
        Log.d("Arya", "setEnableRecording:" + z12);
        this.f28502x.d(z12);
    }

    public void setEnableSpeakerInputAEC(boolean z12) {
        this.f28502x.i(z12);
    }

    public void setEncodeDebugInfoKey(String str) {
        nativeSetStringProperty(this.f28496r, "priv_key", str, 2);
    }

    public void setHowlingSuppressionMode(int i12) {
        this.A.f28772i = i12;
        this.f28502x.h(i12);
    }

    public boolean setKaraokeScorePitch(int i12) {
        return this.f28502x.n(i12);
    }

    public void setKtpModelPath(String str) {
        nativeSetKtpModelPath(this.f28496r, str);
    }

    public void setLiveStreamVideoEncodeParam(KWAryaVideoEncodeParameter kWAryaVideoEncodeParameter) {
        nativeSetLiveStreamVideoEncodeParam(this.f28496r, kWAryaVideoEncodeParameter.width, kWAryaVideoEncodeParameter.height, kWAryaVideoEncodeParameter.fps, kWAryaVideoEncodeParameter.maxBitrate, kWAryaVideoEncodeParameter.resumeOnRtcStop);
    }

    public void setLogo(ByteBuffer byteBuffer, int i12, int i13, float f12, float f13, int i14) {
        if (i12 < 0 || i13 < 0) {
            return;
        }
        nativeSetLogo(this.f28496r, byteBuffer, i12, i13, f12, f13, i14, false);
    }

    public void setMediaFrameObserver(MediaFrameObserver mediaFrameObserver, int i12) {
        nativeSetMediaCallback(this.f28496r, mediaFrameObserver, i12);
    }

    public void setMixType(int i12) {
        nativeSetMixType(this.f28496r, i12);
    }

    public void setMuteAllRemoteAudioStreams(String str, boolean z12) {
        nativeSetMuteAllRemoteAudioStreams(this.f28496r, str, z12);
    }

    public void setMuteBgm(boolean z12) {
        this.A.f28777n = z12;
        this.f28502x.j(z12);
    }

    public void setMuteChatOutBgm(boolean z12) {
        this.f28502x.h(z12);
        this.A.f28783t = z12;
    }

    public void setMuteMicOfAllOthers(boolean z12) {
        nativeSetMuteMicOfAllOthers(this.f28496r, z12);
    }

    public void setMuteMicOfOtherOne(String str, boolean z12) {
        nativeSetMuteMicOfOtherOne(this.f28496r, str, z12);
    }

    public void setMuteMicrophone(int i12) {
        Log.d("Arya", "setMuteMicrophone type:" + i12);
        this.H = i12 == 1;
        this.A.f28768e = i12;
        this.f28502x.g(i12);
        nativeSetMuteMicrophone(this.f28496r, this.H);
    }

    public void setMuteRemote(boolean z12, boolean z13) {
        Log.d("Arya", "setMuteRemote: " + z12 + " improveAudioQuality: " + z13);
        AryaContext aryaContext = this.A;
        aryaContext.f28773j = z12;
        aryaContext.f28774k = z13;
        this.f28502x.a(z12, z13);
    }

    public void setMuteRemoteAudioStreams(String str, String str2, boolean z12) {
        nativeSetMuteRemoteAudioStreams(this.f28496r, str, str2, z12);
    }

    public void setMuteSoundEffect(boolean z12) {
        this.A.f28780q = z12;
        this.f28502x.k(z12);
    }

    public void setMuteSpeaker(boolean z12) {
        this.A.f28769f = z12;
        this.f28502x.b(z12);
    }

    public void setPcmPlayVolume(float f12) {
        this.f28502x.h(f12);
    }

    public void setPkGameId(String str) {
        nativeSetStringProperty(this.f28496r, "pk_id", str, 1);
    }

    public void setPreviewSize(int i12, int i13) {
        nativeSetIntProperty(this.f28496r, "pre_w", i12, 0);
        nativeSetIntProperty(this.f28496r, "pre_h", i13, 0);
    }

    public void setProperty(String str, String str2, int i12) {
        nativeSetStringProperty(this.f28496r, str, str2, i12);
    }

    public void setRemoteBgmVolume(float f12) {
        Log.d("Arya", "setRemoteBgmVolume: " + f12);
        this.A.f28776m = f12;
        this.f28502x.f(f12);
    }

    public void setRequestAudioFocus(boolean z12) {
        this.f28502x.p(z12);
    }

    public void setReverbLevel(int i12) {
        this.A.f28765b = i12;
        this.f28502x.d(i12);
    }

    public void setRotation(int i12, int i13) {
        nativeSetRotation(this.f28496r, i12, i13);
    }

    public void setSoftAecMode(int i12) {
        this.f28502x.k(i12);
    }

    public void setSoundEffectVolume(float f12) {
        this.A.f28779p = f12;
        this.f28502x.g(f12);
    }

    public void setSpeakerOn(boolean z12) {
        this.A.f28770g = z12;
        this.f28502x.c(z12);
    }

    public void setSpeakerVolume(float f12) {
        Log.d("Arya", "setSpeakerVolume: " + f12);
        this.f28502x.a(f12);
    }

    public void setUsingBuiltinMic(boolean z12) {
        this.f28502x.q(z12);
    }

    public void setVideoBitrateTable(String str, String str2) {
        nativeSetVideoBitrateTable(this.f28496r, str, str2);
    }

    public void setVideoLayoutScheme(String str) {
        nativeSetVideoLayoutScheme(this.f28496r, str);
    }

    public void setVideoMaxBitrate(int i12, String str) {
        nativeSetVideoMaxBitrate(this.f28496r, i12, str);
    }

    public void setVideoMirror(boolean z12) {
        nativeSetVideoMirror(this.f28496r, z12);
    }

    public void setVideoMixOutputMode(int i12, int i13) {
        nativeSetVideoMixOutputMode(this.f28496r, i12, i13);
    }

    public void startAudioEngine(int i12) {
        this.f28502x.a(i12, this.f28503y);
    }

    public boolean startAudioInnerCap(MediaProjection mediaProjection) {
        return this.f28502x.a(mediaProjection);
    }

    public void startAudioRecording(AudioRecordingObserver audioRecordingObserver) {
        a(audioRecordingObserver, 0, 2816);
    }

    public boolean startBgm(ArrayList<String> arrayList, boolean z12, int i12, final BgmObserver bgmObserver) {
        Log.i("Arya", "startBgm progressIntervalMs: " + i12);
        this.f28502x.a(arrayList, z12, i12, new a.c() { // from class: com.kwai.video.arya.Arya.6
            @Override // com.kwai.video.arya.a.c
            public void onCompleted(String str) {
                bgmObserver.onCompleted(str);
                Arya arya = Arya.this;
                arya.nativeSendForceTransfer(arya.f28496r, false);
                Arya.this.B.b();
                Arya.this.a(false);
            }

            @Override // com.kwai.video.arya.a.c
            public void onError(String str, BgmObserver.BgmErrorType bgmErrorType) {
                bgmObserver.onError(str, bgmErrorType);
                Arya arya = Arya.this;
                arya.nativeSendForceTransfer(arya.f28496r, false);
                Arya.this.B.b();
                Arya.this.a(false);
            }

            @Override // com.kwai.video.arya.a.c
            public void onProgressed(String str, float f12, float f13) {
                bgmObserver.onProgressed(str, f12, f13);
                Arya.this.B.f28837a = (int) f12;
                Arya.this.B.f28838b = (int) f13;
            }

            @Override // com.kwai.video.arya.a.c
            public void onStart(String str) {
                bgmObserver.onStart(str);
            }
        });
        nativeSendForceTransfer(this.f28496r, true);
        if (this.f28485a) {
            nativeSetLiveStreamBgmOffsetCb(this.f28496r, i12, new LiveStreamBgmOffsetObserver() { // from class: com.kwai.video.arya.Arya.7
                @Override // com.kwai.video.arya.observers.LiveStreamBgmOffsetObserver
                public void onBgmOffset(int i13) {
                    bgmObserver.offsetInLiveStream(i13);
                }
            });
        }
        this.B.a();
        a(true);
        return true;
    }

    public void startCall(@e.a byte[] bArr, LiveStreamParam liveStreamParam) {
        if (this.f28491m && Build.VERSION.SDK_INT >= 23) {
            a(liveStreamParam);
        }
        nativeStartCall(this.f28496r, bArr, liveStreamParam, d());
    }

    public boolean startFileStreaming(List<Map<String, String>> list, String str, int i12, FileStreamingObserver fileStreamingObserver) {
        String[] strArr = new String[list.size()];
        for (int i13 = 0; i13 < list.size(); i13++) {
            strArr[i13] = list.get(i13).get("url");
            if (strArr[i13] == null) {
                return false;
            }
        }
        nativeStartUrlFileStreaming(this.f28496r, strArr, str, i12, fileStreamingObserver, new AryaCallObserverInner() { // from class: com.kwai.video.arya.Arya.9
            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onMediaServerInfo(String str2, String str3, int i14, boolean z12) {
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotify(String str2, int i14, int i15, String str3) {
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerAddArx(String str2, int i14, int i15, int i16) {
                Arya.this.f28502x.a(i14, Arya.this.f28503y, i15, i16);
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerRemoveArx(String str2, int i14) {
                Arya.this.f28502x.a(i14);
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onVideoSendParamChanged(int i14, int i15, int i16, int i17) {
            }
        });
        return true;
    }

    public void startKaraokeScore(long j12, String str, final String str2, String str3, String str4, String str5, int i12, int i13, final KaraokeScoreObserver karaokeScoreObserver) {
        this.f28502x.a(j12, str, str2, str3, str4, str5, i12, i13, new a.d() { // from class: com.kwai.video.arya.Arya.11
            @Override // com.kwai.video.arya.a.d
            public void onScore(KaraokeScore karaokeScore) {
                karaokeScoreObserver.onScore(karaokeScore);
            }

            @Override // com.kwai.video.arya.a.d
            public void onScore(String str6, int i14, int i15, int i16) {
                karaokeScoreObserver.onScore(str2, i14, i15, i16);
            }
        });
    }

    public void startKaraokeVad(String str) {
        this.f28502x.b(str);
    }

    public void startLiveRecording(String str, AryaResultObserver aryaResultObserver) {
        nativeStartLiveRecording(this.f28496r, str, aryaResultObserver);
    }

    public void startLiveRecordingForAudioMix(String str, AryaResultObserver aryaResultObserver, int i12) {
        this.K = true;
        nativeStartLiveRecordingForAudioMix(this.f28496r, str, aryaResultObserver, i12);
    }

    public void startLiveStream(LiveStreamParam liveStreamParam) {
        if (this.f28491m && Build.VERSION.SDK_INT >= 23) {
            a(liveStreamParam);
        }
        nativeStartLiveStream(this.f28496r, liveStreamParam, d());
    }

    public void startMixRemoteAndLocalAudio(AudioMixerObserver audioMixerObserver) {
        Log.d("Arya", "startMixRemoteAndLocalAudio");
        nativeStartMixRemoteAndLocalAudio(this.f28496r, audioMixerObserver);
    }

    public void startMultipleStreamMix() {
        if (this.f28484J == null) {
            this.f28484J = new DataReadyObserver() { // from class: com.kwai.video.arya.Arya.16
                @Override // com.kwai.video.stannis.observers.DataReadyObserver
                public void onDataReady(int i12, ByteBuffer byteBuffer, int i13, int i14, int i15, long j12, short s12, int i16) {
                    boolean z12 = i12 != 512;
                    int iRemaining = byteBuffer.remaining();
                    byte[] bArr = new byte[iRemaining];
                    byteBuffer.get(bArr, 0, iRemaining);
                    int i17 = i13 * i15 * 2;
                    if (i12 != 1536) {
                        Arya arya = Arya.this;
                        arya.nativeInputRawAudioByteArray(arya.f28496r, bArr, i17, i14, i15, j12, z12, 0);
                    } else if (Arya.this.K) {
                        Arya arya2 = Arya.this;
                        arya2.nativeInputRawAudioByteArray(arya2.f28496r, bArr, i17, i14, i15, j12, false, i12);
                    }
                }
            };
        }
        this.f28502x.a(this.f28484J);
    }

    public void startNetSpeedMeasure(String str, String str2, int i12, int i13) {
        nativeStartNetSpeedMeasure(this.f28496r, str, str2, i12, i13);
    }

    public void startPcmPlay() {
        this.f28502x.z();
    }

    public void startPlayAudioSegment(String str, String str2, final boolean z12, final AudioSegmentPlayerObserver audioSegmentPlayerObserver) {
        this.f28502x.a(str, str2, new a.b() { // from class: com.kwai.video.arya.Arya.10
            @Override // com.kwai.video.arya.a.b
            public void onFinished(String str3, AudioSegmentPlayerObserver.ErrorType errorType) {
                audioSegmentPlayerObserver.onFinished(str3, errorType);
                Arya.this.a(false);
            }

            @Override // com.kwai.video.arya.a.b
            public void onProgressed(String str3, float f12, float f13) {
                audioSegmentPlayerObserver.onProgressed(str3, f12, f13);
            }

            @Override // com.kwai.video.arya.a.b
            public void onStartMixing(String str3, String str4, long j12) {
                if (z12) {
                    Arya arya = Arya.this;
                    arya.nativeSendMetaData(arya.f28496r, f2.f22320i, str4, j12);
                }
            }
        });
        a(true);
    }

    public void startScreencast(String str, int i12, int i13, int i14, int i15, int i16) {
        nativeStartScreencast(this.f28496r, str, i12, i13, i14, i15, i16);
    }

    public Boolean startVoicePartyKtvMode(int i12, int i13, int i14, ArrayList<String> arrayList, int i15, BgmObserver bgmObserver) {
        if (i13 <= 0) {
            Log.e("Arya", String.format("startVoicePartyKtvMode: bgmId(%d) must > 0", Integer.valueOf(i13)));
            return Boolean.FALSE;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("startVoicePartyKtvMode: bgmId: ");
        sb2.append(i13);
        sb2.append(", bgmStartPos: ");
        sb2.append(i14);
        sb2.append(", isAnchor: ");
        sb2.append(this.f28485a);
        sb2.append(", role: ");
        sb2.append(i12 == 0 ? "singer" : "audience");
        Log.d("Arya", sb2.toString());
        startBgm(arrayList, false, i15, bgmObserver);
        seekBgm(i14);
        this.f28488d = true;
        this.f28489e = i12;
        if (i12 == 1) {
            pauseBgm();
            setMuteChatOutBgm(true);
            this.f28486b = i14;
        } else {
            if (this.f28485a) {
                setMuteChatOutBgm(true);
            } else {
                this.f28502x.f(0.0f);
                this.A.f28776m = 0.0f;
                this.f28502x.d(0.0f);
                this.A.f28784u = 0.0f;
            }
            this.f28502x.e(true);
            this.f28502x.b(-15.0f);
            this.f28502x.f(true);
            AryaContext aryaContext = this.A;
            aryaContext.f28785v = true;
            aryaContext.f28786w = -15.0f;
            aryaContext.f28787x = true;
            f();
        }
        nativeSetVoicePartyKtvMode(this.f28496r, 1, i13, i14, i12);
        return Boolean.TRUE;
    }

    public void stopAllSoundEffects() {
        this.f28502x.n();
    }

    public void stopAudioEngine() {
        this.f28502x.a(this.f28503y);
    }

    public void stopAudioRecording() {
        Log.d("Arya", "stopAudioRecording");
        nativeStopAudioRecording(this.f28496r, this.f28487c);
        this.f28502x.a(this.f28503y);
    }

    public void stopBgm() {
        this.f28502x.h();
        nativeSendForceTransfer(this.f28496r, false);
        if (this.f28485a) {
            nativeSetLiveStreamBgmOffsetCb(this.f28496r, 0, null);
        }
        this.B.b();
        a(false);
    }

    public void stopCall(@e.a byte[] bArr) {
        this.f28502x.u();
        this.f28502x.l();
        this.f28502x.a(this.f28503y);
        this.f28500v = "";
        this.f28501w = "";
        nativeStopCall(this.f28496r, bArr);
    }

    public void stopFileStreaming() {
        nativeStopFileStreaming(this.f28496r);
    }

    public void stopInnerCap() {
        this.f28502x.e();
    }

    public void stopKaraokeScore() {
        this.f28502x.p();
    }

    public void stopKaraokeVad() {
        this.f28502x.v();
    }

    public void stopLiveChatByForce() {
        nativeStopRtcSessionByForce(this.f28496r);
    }

    public void stopLivePkByForce() {
        nativeStopRtcSessionByForce(this.f28496r);
    }

    public void stopLiveRecording(AryaResultObserver aryaResultObserver) {
        this.K = false;
        nativeStopLiveRecording(this.f28496r, aryaResultObserver);
    }

    public void stopLiveStream(String str) {
        this.f28502x.u();
        this.f28502x.l();
        this.f28502x.a(this.f28503y);
        this.f28500v = "";
        this.f28501w = "";
        nativeStopLiveStream(this.f28496r, str);
    }

    public void stopLowLatencyLiveByForce() {
        nativeStopLowLatencyLiveByForce(this.f28496r);
    }

    public void stopMixRemoteAndLocalAudio() {
        Log.d("Arya", "stopMixRemoteAndLocalAudio");
        nativeStopMixRemoteAndLocalAudio(this.f28496r);
    }

    public void stopMultipleStreamMix() {
        this.f28502x.D();
    }

    public int stopNetSpeedMeasure() {
        return nativeStopNetSpeedMeasure(this.f28496r);
    }

    public void stopPcmPlay() {
        this.f28502x.A();
    }

    public void stopPlayAudioSegment() {
        this.f28502x.o();
        a(false);
    }

    public void stopScreencast() {
        nativeStopScreencast(this.f28496r);
    }

    public void stopVoicePartyByForce() {
        nativeStopRtcSessionByForce(this.f28496r);
    }

    public void stopVoicePartyKtvMode() {
        Log.d("Arya", "stopVoicePartyKtvMode");
        g();
        this.f28488d = false;
        stopBgm();
        if (this.f28489e == 1) {
            setMuteChatOutBgm(false);
            this.f28502x.m(this.A.f28778o);
            this.f28502x.e(this.A.f28775l);
            this.f28502x.f(this.A.f28776m);
        } else {
            this.f28502x.d(1.0f);
            this.f28502x.e(false);
            this.f28502x.b(-15.0f);
            this.f28502x.f(false);
            AryaContext aryaContext = this.A;
            aryaContext.f28784u = 1.0f;
            aryaContext.f28785v = false;
            aryaContext.f28786w = -15.0f;
            aryaContext.f28787x = false;
        }
        nativeSetVoicePartyKtvMode(this.f28496r, 0, 0, 0, this.f28489e);
    }

    public synchronized void uninit() {
        Log.i("Arya", "uninit arya sdk.");
        if (!this.f28504z) {
            Log.w("Arya", "[Arya] already uninited");
            return;
        }
        nativeUninitVoip(this.f28496r);
        BroadcastReceiver broadcastReceiver = this.M;
        if (broadcastReceiver != null) {
            this.f28494p.unregisterReceiver(broadcastReceiver);
            this.M = null;
        }
        BroadcastReceiver broadcastReceiver2 = this.N;
        if (broadcastReceiver2 != null) {
            this.f28494p.unregisterReceiver(broadcastReceiver2);
            this.N = null;
        }
        this.f28494p = null;
        g();
        this.f28502x.a(this.f28503y);
        this.f28504z = false;
    }

    public boolean updateBgmIndex(int i12, int i13) {
        this.f28502x.a(i12, i13);
        return true;
    }

    public void updateConfig(AryaConfig aryaConfig) {
        String str;
        boolean z12;
        boolean zBooleanValue;
        this.f28498t = aryaConfig.qosEnableFlag;
        aryaConfig.packageName = this.f28494p.getPackageName();
        if (aryaConfig.appName == null || (str = aryaConfig.appVersion) == null) {
            throw new AssertionError("appName|appVersion cannot be null!");
        }
        if (aryaConfig.appUserId == null) {
            aryaConfig.appUserId = "anonymous";
        }
        if (str.endsWith("99999")) {
            aryaConfig.packageName += ".debug";
        }
        this.G = aryaConfig.appUserId;
        this.f28485a = aryaConfig.isAnchor;
        aryaConfig.documentRootPath = this.f28494p.getFilesDir().getAbsolutePath();
        aryaConfig.debugFilesPath = aryaConfig.documentRootPath + "/arya_debug_files";
        this.f28499u = aryaConfig.localLoopback;
        this.C = aryaConfig.useExternalAudioDevice;
        this.D = aryaConfig.enableAudioPreProcess;
        setCaptureSize(aryaConfig.captureWidth, aryaConfig.captureHeight);
        setPreviewSize(aryaConfig.previewWidth, aryaConfig.previewHeight);
        Matcher matcher = Pattern.compile("\"sessionNumPerNic\": *(\\d+),").matcher(aryaConfig.aryaConfig);
        int i12 = 0;
        while (matcher.find()) {
            Log.i("Arya", "multiNic: found sessionNumPerNic " + matcher.group(1));
            try {
                i12 = Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e12) {
                CrashReporter.logCheckedException(e12, "Arya.class", "updateConfig", -83);
                Log.w("Arya", "multiNic: parse sessionNumPerNic failed, " + e12);
            }
            boolean z13 = (i12 & 16) > 0;
            this.f28492n = (i12 & 64) > 0;
            int i13 = i12 & 15;
            Log.i("Arya", "multiNic: sessionNumPerNic: " + i12 + ", freeTrafficType: " + aryaConfig.freeTrafficType + ", enableNoneFreeTraffic: " + z13 + ", realSessionNumPerNic: " + i13);
            if ((i13 == 1 && (z13 || aryaConfig.freeTrafficType.equals("K-T-T"))) || this.f28492n) {
                try {
                    if (this.f28490l == null) {
                        this.f28490l = (ConnectivityManager) this.f28494p.getSystemService("connectivity");
                    }
                    Method declaredMethod = Class.forName(this.f28490l.getClass().getName()).getDeclaredMethod("getMobileDataEnabled", new Class[0]);
                    declaredMethod.setAccessible(true);
                    zBooleanValue = ((Boolean) declaredMethod.invoke(this.f28490l, new Object[0])).booleanValue();
                    try {
                        Log.i("Arya", "multiNic: mobile date enabled:" + zBooleanValue);
                    } catch (Exception e13) {
                        z12 = zBooleanValue;
                        e = e13;
                        CrashReporter.logCheckedException(e, "Arya.class", "updateConfig", -61);
                        Log.i("Arya", "multiNic: mobile date enabled failed, " + e);
                        zBooleanValue = z12;
                    }
                } catch (Exception e14) {
                    e = e14;
                    z12 = false;
                }
                if (zBooleanValue) {
                    this.f28491m = true;
                }
            }
        }
        if (this.f28491m) {
            h();
        }
        nativeSetConfigs(this.f28496r, aryaConfig);
        Log.d("Arya", "setConfig enableAudioPreProcess: " + this.D + ", useExternalAudioDevice: " + this.C);
        this.f28502x.a(aryaConfig);
    }

    public boolean updateLayoutForScene(int i12, KWAryaLayout[] kWAryaLayoutArr, ByteBuffer byteBuffer) {
        return nativeUpdateLayoutForScene(this.f28496r, i12, kWAryaLayoutArr, byteBuffer);
    }

    public void updateLiveStreamRtmpUrl(String str) {
        nativeSetLiveStreamRtmpUrl(this.f28496r, str);
    }

    public void updateWallClockTime(long j12) {
        nativeSetWallClockTime(this.f28496r, j12);
    }

    private String d() {
        return ((("[" + Build.MANUFACTURER + " | " + Build.MODEL + "]") + "[" + Build.VERSION.RELEASE + " | " + Build.VERSION.SDK_INT + "]") + "[" + Build.DEVICE + "]") + "[" + d.d(this.f28494p) + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f28489e == 0 && this.f28488d) {
            try {
                String str = String.format("%.2f", Float.valueOf(this.A.f28775l));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bgmPitch", this.A.f28778o);
                jSONObject.put("bgmVolume", Float.valueOf(str));
                nativeBroadcastToOtherParticipants(this.f28496r, jSONObject.toString().getBytes(), 1);
            } catch (Exception e12) {
                CrashReporter.logCheckedException(e12, "Arya.class", f2.f22325n, -33);
                e12.printStackTrace();
            }
        }
    }

    private void f() {
        if (this.f28489e == 0 && this.f28488d) {
            Timer timer = this.I;
            if (timer != null) {
                timer.cancel();
            }
            this.I = new Timer();
            this.I.schedule(new TimerTask() { // from class: com.kwai.video.arya.Arya.12
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Arya.this.e();
                }
            }, 0L, 5000L);
        }
    }

    private void g() {
        Timer timer = this.I;
        if (timer != null) {
            timer.cancel();
            this.I = null;
        }
    }

    @SuppressLint({"WrongConstant"})
    private void h() {
        if (f28478f != null) {
            Log.i("Arya", "prepareMultiNetwork callback already set, no need to request again");
            return;
        }
        if (androidx.core.content.a.a(this.f28494p, "android.permission.CHANGE_NETWORK_STATE") != 0) {
            Log.i("Arya", "prepareMultiNetwork CHANGE_NETWORK_STATE permission not granted");
            return;
        }
        if (Build.VERSION.SDK_INT < 23) {
            Log.i("Arya", "Will not call prepareMultiNetwork as model too old");
            return;
        }
        Log.i("Arya", "prepareMultiNetwork");
        if (this.f28490l == null) {
            this.f28490l = (ConnectivityManager) this.f28494p.getSystemService("connectivity");
        }
        if (f28478f == null) {
            f28478f = new ConnectivityManager.NetworkCallback() { // from class: com.kwai.video.arya.Arya.13
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    try {
                        Network unused = Arya.f28481i = network;
                        Log.i("Arya", "multiNic: wifi network onAvailable");
                    } catch (NullPointerException e12) {
                        CrashReporter.logCheckedException(e12, "Arya$13.class", "onAvailable", 88);
                        e12.printStackTrace();
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onUnavailable() {
                    Log.i("Arya", "multiNic: wifi network onUnavailable");
                    Network unused = Arya.f28481i = null;
                }
            };
        }
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        builder.addTransportType(1);
        this.f28490l.requestNetwork(builder.build(), f28478f);
        if (f28479g == null) {
            f28479g = new ConnectivityManager.NetworkCallback() { // from class: com.kwai.video.arya.Arya.14
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    try {
                        Network unused = Arya.f28482j = network;
                        Log.i("Arya", "multiNic: mobile network onAvailable");
                    } catch (NullPointerException e12) {
                        CrashReporter.logCheckedException(e12, "Arya$14.class", "onAvailable", 114);
                        e12.printStackTrace();
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onUnavailable() {
                    Log.i("Arya", "multiNic: mobile network onUnavailable");
                    Network unused = Arya.f28482j = null;
                }
            };
        }
        NetworkRequest.Builder builder2 = new NetworkRequest.Builder();
        builder2.addCapability(0);
        builder2.addTransportType(0);
        this.f28490l.requestNetwork(builder2.build(), f28479g);
        if (f28480h == null) {
            f28480h = new ConnectivityManager.NetworkCallback() { // from class: com.kwai.video.arya.Arya.15
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    try {
                        Network unused = Arya.f28483k = network;
                        Log.i("Arya", "multiNic: eth network onAvailable");
                    } catch (NullPointerException e12) {
                        CrashReporter.logCheckedException(e12, "Arya$15.class", "onAvailable", -115);
                        e12.printStackTrace();
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onUnavailable() {
                    Log.i("Arya", "multiNic: eth network onUnavailable");
                    Network unused = Arya.f28483k = null;
                }
            };
        }
        NetworkRequest.Builder builder3 = new NetworkRequest.Builder();
        builder3.addTransportType(3);
        this.f28490l.requestNetwork(builder3.build(), f28480h);
        Log.i("Arya", "multiNic: network service requested");
    }

    private Map<Integer, List<NicInfo>> i() {
        Network network;
        Network network2;
        Network network3;
        HashMap map = new HashMap();
        if (Build.VERSION.SDK_INT < 23) {
            Log.i("Arya", "Will not call getLocalNics as model too old");
            return map;
        }
        for (Pair<String, String> pair : d.a()) {
            NicInfo nicInfo = new NicInfo();
            nicInfo.f28515ip = (String) pair.second;
            String str = (String) pair.first;
            if (str != null && str.contains("wlan") && (network3 = f28481i) != null) {
                nicInfo.socketFd = bindSocket(network3.getNetworkHandle());
                Log.i("Arya", "multiNic: wifi bind interface: " + str + ", ip: " + ((String) pair.second) + ", netId: " + f28481i.getNetworkHandle() + ", fd: " + nicInfo.socketFd);
                if (!map.containsKey(1)) {
                    map.put(1, new ArrayList());
                }
                ((List) map.get(1)).add(nicInfo);
            } else if (str != null && ((str.contains("rmnet") || str.contains("ccmni")) && (network2 = f28482j) != null)) {
                nicInfo.isCellular = true;
                nicInfo.socketFd = bindSocket(network2.getNetworkHandle());
                Log.i("Arya", "multiNic: mobile bind interface: " + str + ", ip: " + ((String) pair.second) + ", netId: " + f28482j.getNetworkHandle() + ", fd: " + nicInfo.socketFd);
                if (!map.containsKey(0)) {
                    map.put(0, new ArrayList());
                }
                ((List) map.get(0)).add(nicInfo);
            } else if (str == null || !str.contains("eth") || (network = f28483k) == null) {
                Log.i("Arya", "multiNic: unknown interface or network not available: " + str + ", ip: " + ((String) pair.second));
            } else {
                nicInfo.socketFd = bindSocket(network.getNetworkHandle());
                Log.i("Arya", "multiNic: ethernet bind interface: " + str + ", ip: " + ((String) pair.second) + ", netId: " + f28483k.getNetworkHandle() + ", fd: " + nicInfo.socketFd);
                if (!map.containsKey(3)) {
                    map.put(3, new ArrayList());
                }
                ((List) map.get(3)).add(nicInfo);
            }
        }
        return map;
    }

    public synchronized boolean init(SignalMessageHandler signalMessageHandler, AryaCallObserver aryaCallObserver, AryaQosObserver aryaQosObserver) {
        return a(signalMessageHandler, aryaCallObserver, aryaQosObserver, (EglBase.Context) null, true);
    }

    public int inputRawVideo(byte[] bArr, VideoFrameAttribute videoFrameAttribute) {
        return nativeInputRawVideoByteArray(this.f28496r, videoFrameAttribute.format, bArr, bArr.length, videoFrameAttribute.width, videoFrameAttribute.height, videoFrameAttribute.timestamp, videoFrameAttribute.rotation, videoFrameAttribute.csp, videoFrameAttribute.rois, videoFrameAttribute.sourceId);
    }

    public void replaceVideoWithBitmap(Bitmap bitmap, int i12) {
        if (bitmap == null) {
            nativeSetReplacedImage(this.f28496r, null, 0, 0, i12);
            return;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBufferAllocateDirect);
        nativeSetReplacedImage(this.f28496r, byteBufferAllocateDirect, bitmap.getWidth(), bitmap.getHeight(), i12);
    }

    public void setLogo(ByteBuffer byteBuffer, int i12, int i13, float f12, float f13, int i14, boolean z12) {
        if (i12 < 0 || i13 < 0) {
            return;
        }
        nativeSetLogo(this.f28496r, byteBuffer, i12, i13, f12, f13, i14, z12);
    }

    public void startAudioRecording(AudioRecordingObserver audioRecordingObserver, int i12) {
        Log.d("Arya", "startAudioRecording type:" + i12);
        a(audioRecordingObserver, i12, 2816);
    }

    public synchronized boolean init(SignalMessageHandler signalMessageHandler, AryaCallObserver aryaCallObserver, AryaQosObserver aryaQosObserver, EglBase.Context context) {
        return a(signalMessageHandler, aryaCallObserver, aryaQosObserver, context, true);
    }

    public int inputRawVideo(ByteBuffer byteBuffer, VideoFrameAttribute videoFrameAttribute) {
        return nativeInputRawVideoByteBuffer(this.f28496r, videoFrameAttribute.format, byteBuffer, videoFrameAttribute.width, videoFrameAttribute.height, videoFrameAttribute.timestamp, videoFrameAttribute.rotation, videoFrameAttribute.csp, videoFrameAttribute.rois, videoFrameAttribute.sourceId, this.L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f28502x.t();
    }

    public synchronized boolean init(SignalMessageHandler signalMessageHandler, AryaCallObserver aryaCallObserver, AryaQosObserver aryaQosObserver, InitParam initParam) {
        this.f28493o = initParam.enableWebSocket;
        return a(signalMessageHandler, aryaCallObserver, aryaQosObserver, initParam.eglContext, initParam.needCreateEglContext);
    }

    public int inputRawVideo(TextureBuffer textureBuffer) {
        return inputRawVideo(textureBuffer, "", 0);
    }

    public void startAudioRecording(AudioRecordingObserver audioRecordingObserver, int i12, int i13) {
        Log.d("Arya", "startAudioRecording type:" + i12 + ", audioScene:" + i13);
        a(audioRecordingObserver, i12, i13);
    }

    boolean b() {
        return this.f28502x.d();
    }

    public int inputRawVideo(TextureBuffer textureBuffer, String str, int i12) {
        int iNativeInputRawVideoTextureBuffer = nativeInputRawVideoTextureBuffer(this.f28496r, textureBuffer, str, i12, this.L);
        textureBuffer.release();
        return iNativeInputRawVideoTextureBuffer;
    }

    public static class VideoFrameAttribute {
        public int csp;
        public int format;
        public int height;
        public String rois;
        public int rotation;
        public int sourceId;
        public long timestamp;
        public int width;

        public VideoFrameAttribute(int i12, int i13, int i14, long j12, int i15, int i16, String str) {
            this.csp = 1;
            this.rois = "";
            this.sourceId = 0;
            this.format = i12;
            this.width = i13;
            this.height = i14;
            this.timestamp = j12;
            this.rotation = i15;
            this.csp = i16;
            this.rois = str;
        }

        public VideoFrameAttribute(int i12, int i13, int i14, long j12, int i15, int i16, String str, int i17) {
            this.csp = 1;
            this.rois = "";
            this.sourceId = 0;
            this.format = i12;
            this.width = i13;
            this.height = i14;
            this.timestamp = j12;
            this.rotation = i15;
            this.csp = i16;
            this.rois = str;
            this.sourceId = i17;
        }
    }

    private synchronized boolean a(SignalMessageHandler signalMessageHandler, final AryaCallObserver aryaCallObserver, AryaQosObserver aryaQosObserver, EglBase.Context context, boolean z12) {
        Log.i("Arya", "[Arya] init arya sdk, needCreateEglContext: " + z12 + ", enableWebSocket: " + this.f28493o);
        if (this.f28504z) {
            Log.w("Arya", "[Arya] already inited");
            return false;
        }
        com.kwai.video.arya.utils.a.a(this.f28494p);
        this.f28495q = signalMessageHandler;
        this.f28497s = aryaQosObserver;
        this.f28503y.f28835a = new a.e() { // from class: com.kwai.video.arya.Arya.1
            @Override // com.kwai.video.arya.a.e
            public void onNotify(int i12) {
                String str = Arya.this.f28500v;
                if (str == null || str.isEmpty()) {
                    str = Arya.this.f28501w;
                }
                if (aryaCallObserver == null || str == null || str.isEmpty()) {
                    return;
                }
                if (i12 == 9 || i12 == 32 || i12 == 35 || i12 == 52) {
                    aryaCallObserver.onNotify(str, i12);
                }
            }
        };
        AryaCallObserverInner aryaCallObserverInner = new AryaCallObserverInner() { // from class: com.kwai.video.arya.Arya.2
            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onAudioPassThroughMsg(String str, ByteBuffer byteBuffer) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onAudioPassThroughMsg(str, byteBuffer);
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onLocalAudioStats(String str) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onLocalAudioStats(new KWAryaStats.KWAryaLocalAudioStats(str));
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onLocalVideoStats(String str) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onLocalVideoStats(new KWAryaStats.KWAryaLocalVideoStats(str));
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onMediaServerInfo(String str, String str2, int i12, boolean z13) {
                Arya.this.f28500v = str;
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onMediaServerInfo(str, str2, i12, z13);
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNetworkQuality(String str) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onNetworkQuality(new KWAryaStats.KWAryaNetworkStats(str));
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotify(String str, int i12, int i13, String str2) {
                if (i13 == 28) {
                    Arya.this.f28501w = str;
                    if (i12 == 3) {
                        i13 = 7;
                    } else if (i12 == 8) {
                        i13 = 25;
                    } else if (i12 == 5) {
                        i13 = 10;
                    } else if (i12 == 6) {
                        i13 = 13;
                    }
                } else if (i13 == 29) {
                    Arya.this.f28501w = "";
                    Arya.this.F = "";
                    if (i12 == 3) {
                        i13 = 8;
                    } else if (i12 == 8) {
                        i13 = 26;
                    } else if (i12 == 5) {
                        i13 = 12;
                    } else if (i12 == 6) {
                        i13 = 14;
                    }
                } else if (i13 == 33) {
                    Arya.this.setMuteMicrophone(1);
                } else if (i13 == 34) {
                    Arya.this.setMuteMicrophone(0);
                } else if (i13 == 37) {
                    Arya.this.a(str, str2);
                }
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onNotify(str, i13);
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerAddArx(String str, int i12, int i13, int i14) {
                Arya.this.f28502x.a(i12, Arya.this.f28503y, i13, i14);
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerChangeAudioScene(String str, int i12, boolean z13, Object obj) {
                if (!Arya.this.C || Arya.this.D) {
                    if (!Arya.this.f28504z) {
                        Log.i("Arya", "[Arya] onNotifyInnerChangeAudioScene, wrong status: arya was uninted");
                        return;
                    }
                    if (z13) {
                        Arya.this.f28502x.a((AudioServerConfig) obj);
                    }
                    Arya.this.f28502x.a(i12, Arya.this.f28503y);
                    Arya.this.f28502x.a(Arya.this.A);
                    Arya.this.c();
                    if (Arya.this.f28488d && Arya.this.f28485a) {
                        return;
                    }
                    Arya.this.f28502x.k();
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerGuestAudioInfoUpdated(String str, byte[] bArr) {
                Arya.this.a(bArr);
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerKtvBgmStart(String str, int i12) {
                Arya arya = Arya.this;
                int i13 = arya.f28486b;
                if (i12 == i13) {
                    arya.resumeBgm();
                } else if (i12 > i13) {
                    arya.resumeBgm();
                    Arya.this.seekBgm(i12);
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerRemoveArx(String str, int i12) {
                Arya.this.f28502x.a(i12);
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onNotifyInnerStopStannis(String str) {
                Arya.this.f28502x.a(Arya.this.f28503y);
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onRemoteAudioStats(String str) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onRemoteAudioStats(new KWAryaStats.KWAryaRemoteAudioStats(str));
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onRemoteVideoStats(String str) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onRemoteVideoStats(new KWAryaStats.KWAryaRemoteVideoStats(str));
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onRtcStats(String str) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onRtcStats(new KWAryaStats.KWAryaRtcStats(str));
                }
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onVideoSendParamChanged(int i12, int i13, int i14, int i15) {
                Log.i("Arya", "[Arya] onVideoSendParamChanged: width=" + i13 + ", height=" + i14 + ", fps=" + i15);
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    Arya arya = Arya.this;
                    aryaCallObserver2.onVideoSendParamChanged(i13, i14, i15, arya.nativeIsPKSessionId(arya.f28496r, i12));
                }
                Arya arya2 = Arya.this;
                if (!arya2.nativeIsEnableDirectorScaleSize(arya2.f28496r) || i13 * i14 == 0) {
                    return;
                }
                Arya.this.a(i12, i13, i14);
            }

            @Override // com.kwai.video.arya.observers.AryaCallObserverInner
            public void onVoiceComment(String str, ByteBuffer byteBuffer) {
                AryaCallObserver aryaCallObserver2 = aryaCallObserver;
                if (aryaCallObserver2 != null) {
                    aryaCallObserver2.onVoiceComment(str, byteBuffer);
                }
            }
        };
        if (context == null && z12) {
            Log.i("Arya", "[Arya] eglContext is null, get shared egl context");
            context = EglContextHolder.sharedContext();
        }
        nativeInitVoip(this.f28496r, aryaCallObserverInner, this.f28493o);
        nativeSetCodecFactories(this.f28496r, this.f28494p, context);
        if (this.f28493o) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.kwai.video.arya.Arya.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    NetworkInfo activeNetworkInfo;
                    String action = intent.getAction();
                    if (action == null || !action.equals("android.net.conn.CONNECTIVITY_CHANGE") || (activeNetworkInfo = ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
                        return;
                    }
                    String typeName = activeNetworkInfo.getTypeName();
                    int type = activeNetworkInfo.getType();
                    Log.d("Arya", String.format("[Arya] networkStateBroadcast name:%s, type:%d", typeName, Integer.valueOf(type)));
                    Arya arya = Arya.this;
                    arya.nativeNotifyNetworkChange(arya.f28496r, type);
                }
            };
            this.M = broadcastReceiver;
            this.f28494p.registerReceiver(broadcastReceiver, intentFilter);
        }
        BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() { // from class: com.kwai.video.arya.Arya.4
            /* JADX WARN: Code duplicated, block: B:4:0x001f  */
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                int intExtra = (int) ((intent.getIntExtra("level", -1) * 100) / intent.getIntExtra("scale", -1));
                int intExtra2 = intent.getIntExtra("status", -1);
                int i12 = 3;
                if (intExtra2 == 1) {
                    i12 = 0;
                } else if (intExtra2 == 2) {
                    i12 = 2;
                } else if (intExtra2 != 5) {
                    if (intExtra2 == 3) {
                        i12 = 1;
                    } else {
                        i12 = 0;
                    }
                }
                Log.d("Arya", "batteryInfoBroadcast level:" + intExtra + ", batteryState:" + i12);
                Arya arya = Arya.this;
                arya.nativeSetBatteryInfo(arya.f28496r, intExtra, i12);
            }
        };
        this.N = broadcastReceiver2;
        this.f28494p.registerReceiver(broadcastReceiver2, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.A.a();
        this.f28504z = true;
        return true;
    }

    private void a(AudioRecordingObserver audioRecordingObserver, int i12, int i13) {
        this.f28487c = audioRecordingObserver;
        this.f28502x.a(false);
        this.f28502x.a(i13, this.f28503y);
        nativeStartAudioRecording(this.f28496r, audioRecordingObserver, i12, i13);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z12) {
        nativeSetRtcAudioDtxIgnored(this.f28496r, z12);
    }

    void a() {
        Log.i("Arya", "destroy " + this);
        nativeDestroyVoip(this.f28496r);
        this.f28496r = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr) {
        if (this.f28489e == 1 && this.f28488d) {
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                if (jSONObject.has("bgmPitch")) {
                    int i12 = jSONObject.getInt("bgmPitch");
                    Log.i("Arya", "handleGuestAudioInfo: pitch: " + i12);
                    this.f28502x.m(i12);
                }
                if (jSONObject.has("bgmVolume")) {
                    float f12 = (float) jSONObject.getDouble("bgmVolume");
                    Log.i("Arya", "handleGuestAudioInfo: bgmVolume: " + f12);
                    this.f28502x.e(f12);
                    this.f28502x.f(f12);
                }
            } catch (Exception e12) {
                CrashReporter.logCheckedException(e12, "Arya.class", f2.f22315d, 18);
                e12.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        nativeReportRoomConfig(this.f28496r, str, str2);
    }

    private void a(LiveStreamParam liveStreamParam) {
        Map<Integer, List<NicInfo>> mapI = i();
        if (mapI.size() > 1 && !this.f28492n) {
            ArrayList arrayList = new ArrayList();
            Iterator it2 = Arrays.asList(3, 1, 0).iterator();
            while (it2.hasNext()) {
                int iIntValue = ((Integer) it2.next()).intValue();
                if (mapI.containsKey(Integer.valueOf(iIntValue))) {
                    arrayList.addAll(mapI.get(Integer.valueOf(iIntValue)));
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            NicInfo[] nicInfoArr = new NicInfo[arrayList.size()];
            liveStreamParam.localNics = nicInfoArr;
            liveStreamParam.localNics = (NicInfo[]) arrayList.toArray(nicInfoArr);
            return;
        }
        Log.i("Arya", "multiNic: interface not enough to open multiNic " + mapI.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i12, int i13, int i14) {
        if (nativeHasPKSession(this.f28496r) || !nativeIsLiveStreamSessionId(this.f28496r, i12)) {
            return;
        }
        nativeUpdateMixOutputSize(this.f28496r, i13, i14);
    }
}
