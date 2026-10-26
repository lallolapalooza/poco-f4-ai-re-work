package com.xiaomi.ai.mibrain;

/* JADX INFO: loaded from: classes2.dex */
public class MibrainRequestParamsBuilder {
    public static int MI_CMD_ASR_NLP = MibrainNativeRequest.MIBRAINSDK_CMD_ASR_NLP;
    public static int MI_CMD_ASR = MibrainNativeRequest.MIRBAINSDK_CMD_ASR;
    public static int MI_CMD_ASR_NLP_TTS = MibrainNativeRequest.MIBRAINSDK_CMD_ASR_NLP_TTS;
    public static int MI_CMD_TTS = MibrainNativeRequest.MIBRAINSDK_CMD_TTS;
    public static int MI_CMD_NLP = MibrainNativeRequest.MIBRAINSDK_CMD_NLP;
    public static int MI_CMD_NLP_TTS = MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS;
    public static int MI_CMD_VOR_REG = MibrainNativeRequest.MIBRAINSDK_CMD_VOR_REG;
    public static int MI_CMD_VOR = MibrainNativeRequest.MIBRAINSDK_CMD_VOR;
    public static int MI_CMD_VOR_DELETE = MibrainNativeRequest.MIBRAINSDK_CMD_VOR_DEL;
    public static int MI_CMD_VOR_QUERY = MibrainNativeRequest.MIBRAINSDK_CMD_VOR_QUERY;
    public static int MI_CMD_EVENT = MibrainNativeRequest.MIBRAINSDK_CMD_EVENT;
    public static int MI_CMD_EVENT_TTS = MibrainNativeRequest.MIBRAINSDK_CMD_EVENT_TTS;
    public static int EVN_PRODUCTION = 0;
    public static int EVN_PREVIEW = 1;
    public static int EVN_STAGING = 2;
    public static int EVN_PRODUCTION_SGP = 3;
    public static int MIBRAIN_AUTH_APPIDTOKEN = 0;
    public static int MIBRAIN_AUTH_MIAITOKEN = 1;
    public static int MIBRAIN_AUTH_MIOTTOKEN = 2;
    public static int MIBRAIN_AUTH_TPTOKEN = 3;
    public static int MIBRAIN_AUTH_MIAOTOKEN = 4;
    public static int MIBRAIN_AUTH_ANONYMOUS = 5;
    public static int MIBRAIN_AUTH_ANONYMOUS_PROXY = 6;
    public static int MIBRAIN_AUTH_DS_SIGNATURE = 7;
    private static int MIBRAINSDK_INTERNAL_VAD_NONE = 0;
    private static int MIBRAINSDK_INTERNAL_VAD_LOCAL = 1;
    private static int MIBRAINSDK_INTERNAL_VAD_CLOUD = 2;
    private VadMode mLocalVad = VadMode.VAD_MODE_LOCAL;
    private boolean mVadOpen = false;
    private MibrainRequestParams mMibrainRequestParams = new MibrainRequestParams();

    public enum VadMode {
        VAD_MODE_LOCAL,
        VAD_MODE_CLOUD
    }

    public MibrainRequestParams buid() throws MibrainException {
        MibrainRequestParams mibrainRequestParams = this.mMibrainRequestParams;
        if (mibrainRequestParams.appId == null) {
            throw new MibrainException("appid can't be null!");
        }
        int i10 = mibrainRequestParams.sdkCmd;
        if (i10 != MI_CMD_ASR_NLP && i10 != MI_CMD_ASR_NLP_TTS && i10 != MI_CMD_ASR && i10 != MI_CMD_TTS && i10 != MI_CMD_NLP && i10 != MI_CMD_NLP_TTS && i10 != MI_CMD_VOR && i10 != MI_CMD_VOR_REG && i10 != MI_CMD_VOR_DELETE && i10 != MI_CMD_VOR_QUERY && i10 != MI_CMD_EVENT && i10 != MI_CMD_EVENT_TTS) {
            throw new MibrainException("RequestCmd error");
        }
        if (i10 == MI_CMD_TTS && mibrainRequestParams.ttsText == null) {
            throw new MibrainException("cmd = MI_CMD_TTS but text input = null");
        }
        if (mibrainRequestParams.scopeData == null && mibrainRequestParams.authMode == MIBRAIN_AUTH_MIAITOKEN) {
            throw new MibrainException("mMibrainRequestParams.scopeData = null but authMode = MIBRAIN_AUTH_MIAITOKEN ");
        }
        if (i10 == MI_CMD_NLP && mibrainRequestParams.nlpText == null && mibrainRequestParams.isPendingSend != 1) {
            throw new MibrainException("cmd = MI_CMD_NLP but nlpText input = null");
        }
        if (i10 == MI_CMD_NLP_TTS && mibrainRequestParams.nlpText == null) {
            throw new MibrainException("cmd = MI_CMD_NLP_TTS but nlpText input = null");
        }
        if (this.mVadOpen) {
            mibrainRequestParams.useInternalVad = this.mLocalVad == VadMode.VAD_MODE_LOCAL ? MIBRAINSDK_INTERNAL_VAD_LOCAL : MIBRAINSDK_INTERNAL_VAD_CLOUD;
        }
        return mibrainRequestParams;
    }

    public MibrainRequestParamsBuilder setASRAudioMaxTime(int i10) {
        this.mMibrainRequestParams.asrAudioMaxTime = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setASRDialect(String str) {
        this.mMibrainRequestParams.asrDialect = str;
        return this;
    }

    public MibrainRequestParamsBuilder setASRDisableTimeout(int i10) {
        this.mMibrainRequestParams.asrDisableTimeout = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setASRVendor(String str) {
        this.mMibrainRequestParams.asrVendor = str;
        return this;
    }

    public MibrainRequestParamsBuilder setActiveTimeout(int i10) {
        this.mMibrainRequestParams.activeTimeout = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setApiKey(String str) {
        this.mMibrainRequestParams.apiKey = str;
        return this;
    }

    public MibrainRequestParamsBuilder setAppId(String str) {
        this.mMibrainRequestParams.appId = str;
        return this;
    }

    public MibrainRequestParamsBuilder setAsrLang(String str) {
        this.mMibrainRequestParams.asrLang = str;
        return this;
    }

    public MibrainRequestParamsBuilder setAsrRemoveEndPunc(boolean z10) {
        this.mMibrainRequestParams.asrRemoveEndPunc = z10 ? 1 : 0;
        return this;
    }

    public MibrainRequestParamsBuilder setAudioBitNum(int i10) {
        this.mMibrainRequestParams.audioBitNum = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setAuthMode(int i10) {
        this.mMibrainRequestParams.authMode = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setChannels(int i10) {
        this.mMibrainRequestParams.channels = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setCodecMode(short s10) {
        this.mMibrainRequestParams.codecMode = s10;
        return this;
    }

    public MibrainRequestParamsBuilder setConnectTimeOut(int i10) {
        this.mMibrainRequestParams.connectTimeOut = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setCustomNLPApi(String str) {
        this.mMibrainRequestParams.customNlPApi = str;
        return this;
    }

    public MibrainRequestParamsBuilder setCustomNLPAppId(String str) {
        this.mMibrainRequestParams.customNlPAppID = str;
        return this;
    }

    public MibrainRequestParamsBuilder setCustomNLPAppToken(String str) {
        this.mMibrainRequestParams.customNlPAppToKen = str;
        return this;
    }

    public MibrainRequestParamsBuilder setCustomNLPVersion(String str) {
        this.mMibrainRequestParams.nlpVersion = str;
        return this;
    }

    public MibrainRequestParamsBuilder setDevice(String str) {
        this.mMibrainRequestParams.device = str;
        return this;
    }

    public MibrainRequestParamsBuilder setDeviceId(String str) {
        this.mMibrainRequestParams.deviceId = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEnv(int i10) {
        this.mMibrainRequestParams.env = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setEventContextName(String str) {
        this.mMibrainRequestParams.eventContextName = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEventContextNameSpace(String str) {
        this.mMibrainRequestParams.eventContextNameSpace = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEventContextPayload(String str) {
        this.mMibrainRequestParams.eventContextPayload = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEventLevel(int i10) {
        this.mMibrainRequestParams.eventLevel = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setEventName(String str) {
        this.mMibrainRequestParams.eventName = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEventNameSpace(String str) {
        this.mMibrainRequestParams.eventNameSpace = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEventPayload(String str) {
        this.mMibrainRequestParams.eventPayload = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEventSessionId(String str) {
        this.mMibrainRequestParams.eventSessionId = str;
        return this;
    }

    public MibrainRequestParamsBuilder setEventTrackReceiver(int i10) {
        this.mMibrainRequestParams.eventTrackReceiver = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setIsForSai(boolean z10) {
        this.mMibrainRequestParams.isForSai = z10 ? 1 : 0;
        return this;
    }

    public MibrainRequestParamsBuilder setIsPendingSend(int i10) {
        this.mMibrainRequestParams.isPendingSend = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setMaxEmptyAudioTime(int i10) {
        this.mMibrainRequestParams.maxEmptyAudioTime = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPContext(String str) {
        this.mMibrainRequestParams.nlpContext = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPSession(String str) {
        this.mMibrainRequestParams.nlpSession = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPSpeechData(String str) {
        this.mMibrainRequestParams.speechData = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPText(String str) {
        this.mMibrainRequestParams.nlpText = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPUserInfoAge(int i10) {
        this.mMibrainRequestParams.userInfoAge = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPUserInfoExtend(String str) {
        this.mMibrainRequestParams.userInfoExtend = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPUserInfoGender(String str) {
        this.mMibrainRequestParams.userInfoGender = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPUserInfoId(String str) {
        this.mMibrainRequestParams.userInfoId = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPUserInfoIdType(String str) {
        this.mMibrainRequestParams.userInfoIdType = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNLPUserInfoIp(String str) {
        this.mMibrainRequestParams.userInfoIp = str;
        return this;
    }

    public MibrainRequestParamsBuilder setNeedExtraNLP(int i10) {
        this.mMibrainRequestParams.needExtraNlp = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setNeedWakeupData(boolean z10) {
        this.mMibrainRequestParams.needWakeupData = z10 ? 1 : 0;
        return this;
    }

    public MibrainRequestParamsBuilder setOpusBitRates(int i10) {
        this.mMibrainRequestParams.opusBitRates = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setOpusFrameSize(int i10) {
        this.mMibrainRequestParams.opusFrameSize = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setPreAsrTrack(int i10) {
        this.mMibrainRequestParams.preAsrTrack = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setProtocolVersion(String str) {
        this.mMibrainRequestParams.protocolVersion = str;
        return this;
    }

    public MibrainRequestParamsBuilder setReceiverTimeOut(int i10) {
        this.mMibrainRequestParams.receiveTimeout = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setRequestCmd(int i10) {
        this.mMibrainRequestParams.sdkCmd = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setRequestId(String str) {
        this.mMibrainRequestParams.requestId = str;
        return this;
    }

    public MibrainRequestParamsBuilder setSampleRate(int i10) {
        this.mMibrainRequestParams.sampleRate = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setScopeData(String str) {
        this.mMibrainRequestParams.scopeData = str;
        return this;
    }

    public MibrainRequestParamsBuilder setStartEventTrack(int i10) {
        this.mMibrainRequestParams.startEventTrack = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSAdditional(boolean z10) {
        this.mMibrainRequestParams.ttsSuperaddition = z10 ? 1 : 0;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSAsFileUrl(int i10) {
        this.mMibrainRequestParams.ttsAsFileUrl = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSCodec(String str) {
        this.mMibrainRequestParams.ttsCodec = str;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSExtendVendor(String str) {
        this.mMibrainRequestParams.ttsExtendVendor = str;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSLang(String str) {
        this.mMibrainRequestParams.ttsLang = str;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSSpeaker(int i10) {
        this.mMibrainRequestParams.ttsSpeaker = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSSpeed(int i10) {
        this.mMibrainRequestParams.ttsSpeed = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSText(String str) {
        this.mMibrainRequestParams.ttsText = str;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSTimeout(int i10) {
        this.mMibrainRequestParams.ttsTimeout = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSTone(int i10) {
        this.mMibrainRequestParams.ttsTone = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSVendor(String str) {
        this.mMibrainRequestParams.ttsVendor = str;
        return this;
    }

    public MibrainRequestParamsBuilder setTTSVolume(int i10) {
        this.mMibrainRequestParams.ttsVolume = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setToken(String str) {
        this.mMibrainRequestParams.appToken = str;
        return this;
    }

    public MibrainRequestParamsBuilder setUploadTimeout(int i10) {
        this.mMibrainRequestParams.uploadTimeout = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setUseExternalOAuthToken(int i10) {
        this.mMibrainRequestParams.useExternalOAuthToken = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setUseInternalVadFlag(boolean z10) {
        this.mVadOpen = z10;
        return this;
    }

    public MibrainRequestParamsBuilder setUserAgent(String str) {
        this.mMibrainRequestParams.userAgent = str;
        return this;
    }

    public MibrainRequestParamsBuilder setVadTypeLocal(VadMode vadMode) {
        this.mLocalVad = vadMode;
        return this;
    }

    public MibrainRequestParamsBuilder setVoiceId(int i10) {
        this.mMibrainRequestParams.voiceId = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setVoiceprintDeleteIds(String str) {
        this.mMibrainRequestParams.voiceprintDeleteIds = str;
        return this;
    }

    public MibrainRequestParamsBuilder setVoiceprintDisableTextDependent(int i10) {
        this.mMibrainRequestParams.vorDisableTextDependent = i10;
        return this;
    }

    public MibrainRequestParamsBuilder setVoiceprintFamilyId(String str) {
        this.mMibrainRequestParams.voiceprintFamilyId = str;
        return this;
    }

    public MibrainRequestParamsBuilder setVoiceprintQueries(String str) {
        this.mMibrainRequestParams.voiceprintQueries = str;
        return this;
    }

    public MibrainRequestParamsBuilder setVoiceprintUserId(String str) {
        this.mMibrainRequestParams.voiceprintUserId = str;
        return this;
    }

    public MibrainRequestParamsBuilder setVoiceprintVendor(String str) {
        this.mMibrainRequestParams.voiceprintVendor = str;
        return this;
    }

    public MibrainRequestParamsBuilder setWakeupInfo(String str) {
        this.mMibrainRequestParams.wakeupInfo = str;
        return this;
    }
}
