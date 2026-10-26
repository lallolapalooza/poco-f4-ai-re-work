package com.xiaomi.ai.mibrain;

import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
class MibrainNativeRequest {
    public static int MIBRAINSDK_CMD_ASR_NLP = 0;
    public static int MIBRAINSDK_CMD_ASR_NLP_TTS = 2;
    public static int MIBRAINSDK_CMD_EVENT = 10;
    public static int MIBRAINSDK_CMD_EVENT_TTS = 11;
    public static int MIBRAINSDK_CMD_NLP = 4;
    public static int MIBRAINSDK_CMD_NLP_TTS = 5;
    public static int MIBRAINSDK_CMD_TTS = 3;
    public static int MIBRAINSDK_CMD_VOR = 7;
    public static int MIBRAINSDK_CMD_VOR_DEL = 8;
    public static int MIBRAINSDK_CMD_VOR_QUERY = 9;
    public static int MIBRAINSDK_CMD_VOR_REG = 6;
    public static int MIBRAINSDK_RESULT_TYPE_BINARY_TTS = 121;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_ASR = 11;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_ASR_FINAL = 12;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_ERROR = 72;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_OTHER = 62;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_BEGIN = 41;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_END = 42;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_BEGIN = 51;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_END = 52;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_INSTRUCTION = 86;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_NLP = 31;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_DEL = 84;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_QUERY = 85;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REC = 92;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_FINAL = 82;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_PARTIAL = 83;
    public static int MIBRAINSDK_RESULT_TYPE_TXT_TTS = 122;
    public static int MIRBAINSDK_CMD_ASR = 1;
    public static int MI_STOP_FLAG_ASYNC_STOP = 2;
    public static int MI_STOP_FLAG_SYNC_STOP = 1;
    public static int MI_STOP_FLAG_WAIT_RESULT;
    public static Mibrainsdk.MibrainsdkLogHook slogHook;
    private MibrainRequestListener mMibrainRequestListener;
    private MibrainRequest mibrainRequest;

    static {
        Mibrainsdk.loadMibrainLibs();
    }

    public MibrainNativeRequest(MibrainRequestListener mibrainRequestListener, MibrainRequest mibrainRequest) {
        this.mMibrainRequestListener = mibrainRequestListener;
        this.mibrainRequest = mibrainRequest;
    }

    public static native String getResourceDetails(String str, String str2, String str3, int i10);

    private String needUpdateToken() {
        return this.mMibrainRequestListener.onNeedUpdateToken();
    }

    private void onEnd() {
        this.mMibrainRequestListener.onRequestSessionEnd(this.mibrainRequest);
    }

    private void onError(int i10, byte[] bArr) {
        MibrainRequestListener mibrainRequestListener = this.mMibrainRequestListener;
        try {
            mibrainRequestListener.onRequestError(this.mibrainRequest, new String(bArr, "UTF-8"), 2, i10);
        } catch (UnsupportedEncodingException e10) {
            e10.printStackTrace();
            mibrainRequestListener.onRequestError(this.mibrainRequest, "onError UnsupportedEncodingException", 2, -11);
        }
    }

    private void onEventTrack(String str) {
        this.mMibrainRequestListener.onEventTrack(str);
    }

    private String onGetToken(int i10) {
        return this.mMibrainRequestListener.onRequestGetToken(this.mibrainRequest, i10 != 0);
    }

    private static void onLogHook(int i10, byte[] bArr) {
        Mibrainsdk.MibrainsdkLogHook mibrainsdkLogHook = slogHook;
        if (mibrainsdkLogHook != null) {
            try {
                mibrainsdkLogHook.onLog(i10, new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e10) {
                e10.printStackTrace();
                slogHook.onLog(i10, "log UnsupportedEncodingException ");
            }
        }
    }

    private void onResults(byte[] bArr, boolean z10, int i10) {
        MibrainRequestListener mibrainRequestListener = this.mMibrainRequestListener;
        try {
            if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_ASR) {
                mibrainRequestListener.onRequestASRResult(this.mibrainRequest, new String(bArr, "UTF-8"), false);
            } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_ASR_FINAL) {
                mibrainRequestListener.onRequestASRResult(this.mibrainRequest, new String(bArr, "UTF-8"), true);
            } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_NLP) {
                mibrainRequestListener.onRequestNLpResult(this.mibrainRequest, new String(bArr, "UTF-8"));
            } else {
                if (i10 == MIBRAINSDK_RESULT_TYPE_BINARY_TTS) {
                    mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, null, bArr, z10);
                    return;
                }
                if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_TTS) {
                    mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, new String(bArr, "UTF-8"), null, z10);
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_BEGIN) {
                    mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, new String(bArr, "UTF-8"), null, false);
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_FLAG_SESSION_TTS_END) {
                    mibrainRequestListener.onRequestTTSResult(this.mibrainRequest, new String(bArr, "UTF-8"), null, true);
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_FLAG_ERROR) {
                    mibrainRequestListener.onRequestError(this.mibrainRequest, new String(bArr, "UTF-8"), 1, -11);
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REC) {
                    mibrainRequestListener.onRequestVoiceprintRecognizedResult(this.mibrainRequest, new String(bArr, "UTF-8"));
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_FINAL || i10 == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_PARTIAL) {
                    mibrainRequestListener.onRequestVoiceprintRegisteredResult(this.mibrainRequest, new String(bArr, "UTF-8"), i10 == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_REG_FINAL);
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_DEL) {
                    mibrainRequestListener.onRequestVoiceprintDeleteResult(this.mibrainRequest, new String(bArr, "UTF-8"));
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_RESULT_VOR_QUERY) {
                    mibrainRequestListener.onRequestVoiceprintQueryResult(this.mibrainRequest, new String(bArr, "UTF-8"));
                } else if (i10 == MIBRAINSDK_RESULT_TYPE_TXT_INSTRUCTION) {
                    mibrainRequestListener.onInstruction(this.mibrainRequest, new String(bArr, "UTF-8"));
                } else {
                    mibrainRequestListener.onRequestOtherResult(this.mibrainRequest, new String(bArr, "UTF-8"));
                }
            }
        } catch (UnsupportedEncodingException e10) {
            e10.printStackTrace();
            mibrainRequestListener.onRequestError(this.mibrainRequest, "data UnsupportedEncodingException", 2, -11);
        }
    }

    private void onStart() {
        this.mMibrainRequestListener.onRequestSessionStart(this.mibrainRequest);
    }

    public static native int setDebugLogLevel(int i10);

    public static void setLogHook(Mibrainsdk.MibrainsdkLogHook mibrainsdkLogHook) {
        slogHook = mibrainsdkLogHook;
        setLogHookEnable(mibrainsdkLogHook == null ? 0 : 1);
    }

    public static native int setLogHookEnable(int i10);

    public native int addData(long j10, byte[] bArr, int i10, int i11);

    public native int addDataRaw(long j10, int i10, byte[] bArr, int i11, int i12, int i13);

    public native int addNlpData(long j10, String str);

    public native int addUploadData(long j10, byte[] bArr, int i10);

    public native int deleteContext(long j10);

    public native int endUploadData(long j10);

    public native String getAnonymousAuthorization(long j10);

    public native long newContext(MibrainRequestParams mibrainRequestParams, int i10, long j10);

    public native long newWakeUpRequestParams(int i10, int i11);

    public native void releaseWakeUpRequestParams(long j10);

    public native int sendWakeUpRequest(long j10, MibrainWakeupRequestParams mibrainWakeupRequestParams);

    public native int startRequest(long j10);

    public native long startUploadRequest(long j10, MibrainWakeupRequestParams mibrainWakeupRequestParams);

    public native int stopRequest(long j10, int i10);
}
