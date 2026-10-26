package com.xiaomi.ai.mibrain;

/* JADX INFO: loaded from: classes2.dex */
public class MibrainRequest {
    private static final int BRAIN_ADD_DATA_FLAG_CONTINUE = 0;
    private static final int BRAIN_ADD_DATA_FLAG_TASK_END = 1;
    private static final int BRAIN_ADD_DATA_FLAG_VOR_REG_DIV = 2;
    public static final int MIBRAINSDK_ERR_BUFFERQUEUE = -7;
    public static final int MIBRAINSDK_ERR_ENDED = -5;
    public static final int MIBRAINSDK_ERR_HAS_INITIALIZED = -3;
    public static final int MIBRAINSDK_ERR_INIT_FAILED = -1;
    public static final int MIBRAINSDK_ERR_MEM_FAILED = -4;
    public static final int MIBRAINSDK_ERR_PARAMS = -6;
    public static final int MIBRAINSDK_ERR_RECVDATA = -9;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_ACTIVE_TIMEOUT = -20;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_AUTH_FAILED = -22;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_CONNECT_TIMEOUT = -10;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_CRT_ERROR = -14;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_GET_TOKEN_FAILED = -23;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_HANDSHAKE_ERROR = -13;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_INIT_FAILED_ERROR = -16;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_INIT_STATE_ERROR = -17;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_NETWORK_ERROR = -15;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_READ_DATA_ERROR = -11;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_READ_DATA_TIMEOUT = -18;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_SYSTEM_TIME_ERROR = -21;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_WRITE_DATA_ERROR = -12;
    public static final int MIBRAINSDK_ERR_TRANSMISSION_WRITE_DATA_TIMEOUT = -19;
    public static final int MIBRAINSDK_ERR_UNINITIALED = -2;
    public static final int MIBRAINSDK_ERR_UNKNOWN = -8;
    public static final int MIBRAINSDK_NEW_MODE_NORMAL = 0;
    public static final int MIBRAINSDK_NEW_MODE_PENDING_DATA = 1;
    public static final int MIBRAINSDK_OK = 0;
    public static final int MIBRAIN_IPC_BINARY_MSG = 1;
    public static final int MIBRAIN_IPC_TEXT_MSG = 4;
    public static final int MI_ERR_ERRORTYPE_LOCAL = 2;
    public static final int MI_ERR_ERRORTYPE_SERVICE = 1;
    public int mErrorCode;
    public GetAnonymousAuthorizationInterface mInter;
    private long mNativeContext;
    private MibrainRequestListener mRequestListener;
    private MibrainRequestParams mRequestParams;
    private MibrainWakeupRequestParams mWakeupRequestParams;
    private int mode;
    private boolean mEnd = false;
    private MibrainRequestListener internalRequestListener = new MibrainRequestListener() { // from class: com.xiaomi.ai.mibrain.MibrainRequest.1
        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onEventTrack(String str) {
            MibrainRequest.this.mRequestListener.onEventTrack(str);
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onInstruction(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onInstruction(mibrainRequest, str);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public String onNeedUpdateToken() {
            return MibrainRequest.this.mRequestListener.onNeedUpdateToken();
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestASRResult(MibrainRequest mibrainRequest, String str, boolean z10) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestASRResult(mibrainRequest, str, z10);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestError(MibrainRequest mibrainRequest, String str, int i10, int i11) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestError(mibrainRequest, str, i10, i11);
            }
            MibrainRequest.this.mErrorCode = i11;
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public String onRequestGetToken(MibrainRequest mibrainRequest, boolean z10) {
            if (MibrainRequest.this.mRequestListener != null) {
                return MibrainRequest.this.mRequestListener.onRequestGetToken(mibrainRequest, z10);
            }
            return null;
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestNLpResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestNLpResult(mibrainRequest, str);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestOtherResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestOtherResult(mibrainRequest, str);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestSessionEnd(MibrainRequest mibrainRequest) {
            synchronized (MibrainRequest.this) {
                MibrainRequest mibrainRequest2 = MibrainRequest.this;
                if (mibrainRequest2.mInter != null) {
                    MibrainRequest.this.mInter.onGetAnonymousAuthorization(mibrainRequest2.mMibrainNativeRequest.getAnonymousAuthorization(MibrainRequest.this.mNativeContext));
                }
                MibrainRequest.this.mMibrainNativeRequest.deleteContext(MibrainRequest.this.mNativeContext);
                MibrainRequest.this.mEnd = true;
            }
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestSessionEnd(mibrainRequest);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestSessionStart(MibrainRequest mibrainRequest) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestSessionStart(mibrainRequest);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestTTSResult(MibrainRequest mibrainRequest, String str, byte[] bArr, boolean z10) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestTTSResult(mibrainRequest, str, bArr, z10);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintDeleteResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintDeleteResult(mibrainRequest, str);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintQueryResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintQueryResult(mibrainRequest, str);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintRecognizedResult(MibrainRequest mibrainRequest, String str) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintRecognizedResult(mibrainRequest, str);
            }
        }

        @Override // com.xiaomi.ai.mibrain.MibrainRequestListener
        public void onRequestVoiceprintRegisteredResult(MibrainRequest mibrainRequest, String str, boolean z10) {
            if (MibrainRequest.this.mRequestListener != null) {
                MibrainRequest.this.mRequestListener.onRequestVoiceprintRegisteredResult(mibrainRequest, str, z10);
            }
        }
    };
    private MibrainNativeRequest mMibrainNativeRequest = new MibrainNativeRequest(this.internalRequestListener, this);

    public MibrainRequest(MibrainRequestParams mibrainRequestParams) {
        this.mRequestParams = mibrainRequestParams;
    }

    public MibrainRequest(MibrainWakeupRequestParams mibrainWakeupRequestParams) {
        this.mWakeupRequestParams = mibrainWakeupRequestParams;
    }

    public static String getResourceDetails(String str, String str2, String str3, int i10) {
        return MibrainNativeRequest.getResourceDetails(str, str2, str3, i10);
    }

    public synchronized int addAudioData(byte[] bArr, int i10, boolean z10) {
        int i11;
        if (this.mEnd) {
            i11 = -5;
        } else {
            long j10 = this.mNativeContext;
            if (j10 == 0) {
                i11 = -2;
            } else {
                MibrainRequestParams mibrainRequestParams = this.mRequestParams;
                int i12 = mibrainRequestParams.sdkCmd;
                if ((i12 != MibrainNativeRequest.MIBRAINSDK_CMD_TTS || mibrainRequestParams.ttsSuperaddition != 0) && i12 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP && i12 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                    return this.mMibrainNativeRequest.addData(j10, bArr, i10, z10 ? 1 : 0);
                }
                i11 = -6;
            }
        }
        return i11;
    }

    public synchronized int addAudioData(byte[] bArr, int i10, boolean z10, boolean z11) {
        int i11;
        if (this.mEnd) {
            i11 = -5;
        } else {
            long j10 = this.mNativeContext;
            if (j10 == 0) {
                i11 = -2;
            } else {
                MibrainRequestParams mibrainRequestParams = this.mRequestParams;
                int i12 = mibrainRequestParams.sdkCmd;
                if ((i12 != MibrainNativeRequest.MIBRAINSDK_CMD_TTS || mibrainRequestParams.ttsSuperaddition != 0) && i12 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP && i12 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                    return this.mMibrainNativeRequest.addData(j10, bArr, i10, z11 ? 1 : z10 ? 2 : 0);
                }
                i11 = -6;
            }
        }
        return i11;
    }

    public synchronized int addAudioData(byte[] bArr, boolean z10) {
        int i10;
        if (this.mEnd) {
            i10 = -5;
        } else {
            long j10 = this.mNativeContext;
            if (j10 == 0) {
                i10 = -2;
            } else {
                MibrainRequestParams mibrainRequestParams = this.mRequestParams;
                int i11 = mibrainRequestParams.sdkCmd;
                if ((i11 != MibrainNativeRequest.MIBRAINSDK_CMD_TTS || mibrainRequestParams.ttsSuperaddition != 0) && i11 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP && i11 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                    return this.mMibrainNativeRequest.addData(j10, bArr, bArr == null ? 0 : bArr.length, z10 ? 1 : 0);
                }
                i10 = -6;
            }
        }
        return i10;
    }

    public synchronized int addAudioData(byte[] bArr, boolean z10, boolean z11) {
        int i10;
        if (this.mEnd) {
            i10 = -5;
        } else {
            long j10 = this.mNativeContext;
            if (j10 == 0) {
                i10 = -2;
            } else {
                MibrainRequestParams mibrainRequestParams = this.mRequestParams;
                int i11 = mibrainRequestParams.sdkCmd;
                if ((i11 != MibrainNativeRequest.MIBRAINSDK_CMD_TTS || mibrainRequestParams.ttsSuperaddition != 0) && i11 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP && i11 != MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS) {
                    return this.mMibrainNativeRequest.addData(j10, bArr, bArr == null ? 0 : bArr.length, z11 ? 1 : z10 ? 2 : 0);
                }
                i10 = -6;
            }
        }
        return i10;
    }

    public synchronized int addNlpData(String str) {
        int i10;
        if (this.mEnd) {
            i10 = -5;
        } else {
            long j10 = this.mNativeContext;
            if (j10 == 0) {
                i10 = -2;
            } else {
                MibrainRequestParams mibrainRequestParams = this.mRequestParams;
                int i11 = mibrainRequestParams.sdkCmd;
                if ((i11 == MibrainNativeRequest.MIBRAINSDK_CMD_TTS && mibrainRequestParams.ttsSuperaddition == 0) || i11 == MibrainNativeRequest.MIBRAINSDK_CMD_ASR_NLP_TTS || i11 == MibrainNativeRequest.MIBRAINSDK_CMD_ASR_NLP || i11 == MibrainNativeRequest.MIBRAINSDK_CMD_NLP || i11 == MibrainNativeRequest.MIBRAINSDK_CMD_NLP_TTS || i11 == MibrainNativeRequest.MIBRAINSDK_CMD_EVENT || i11 == MibrainNativeRequest.MIBRAINSDK_CMD_EVENT_TTS) {
                    return this.mMibrainNativeRequest.addNlpData(j10, str);
                }
                i10 = -6;
            }
        }
        return i10;
    }

    public synchronized int addRawData(byte[] bArr, int i10, boolean z10, boolean z11) {
        int i11;
        if (this.mEnd) {
            i11 = -5;
        } else {
            long j10 = this.mNativeContext;
            if (j10 != 0) {
                return this.mMibrainNativeRequest.addDataRaw(j10, i10, bArr, bArr == null ? 0 : bArr.length, z10 ? 1 : 0, z11 ? 1 : 0);
            }
            i11 = -2;
        }
        return i11;
    }

    public int addUploadData(long j10, byte[] bArr, int i10) {
        return this.mMibrainNativeRequest.addUploadData(j10, bArr, i10);
    }

    public synchronized void asyncStopRequest() {
        if (this.mEnd) {
            return;
        }
        long j10 = this.mNativeContext;
        if (j10 == 0) {
            return;
        }
        this.mMibrainNativeRequest.stopRequest(j10, MibrainNativeRequest.MI_STOP_FLAG_ASYNC_STOP);
    }

    public int endUploadData(long j10) {
        return this.mMibrainNativeRequest.endUploadData(j10);
    }

    public synchronized String getAnonymousAuthorization() {
        return this.mMibrainNativeRequest.getAnonymousAuthorization(this.mNativeContext);
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void releaseUploadRequest(long j10) {
        this.mMibrainNativeRequest.releaseWakeUpRequestParams(j10);
    }

    public synchronized int sendPendingData() {
        if (this.mEnd) {
            return -5;
        }
        long j10 = this.mNativeContext;
        if (j10 == 0) {
            return -5;
        }
        return (int) this.mMibrainNativeRequest.newContext(this.mRequestParams, 1, j10);
    }

    public void setGetAnonymousAuthorizationCallback(GetAnonymousAuthorizationInterface getAnonymousAuthorizationInterface) {
        this.mInter = getAnonymousAuthorizationInterface;
    }

    public void setRequestListener(MibrainRequestListener mibrainRequestListener) {
        this.mRequestListener = mibrainRequestListener;
    }

    public synchronized int startRequest() {
        int i10;
        if (this.mEnd) {
            i10 = -5;
        } else if (this.mNativeContext != 0) {
            i10 = -3;
        } else {
            long jNewContext = this.mMibrainNativeRequest.newContext(this.mRequestParams, 0, 0L);
            this.mNativeContext = jNewContext;
            if (jNewContext != 0) {
                int iStartRequest = this.mMibrainNativeRequest.startRequest(jNewContext);
                if (iStartRequest != 0) {
                    this.mEnd = true;
                    this.mMibrainNativeRequest.deleteContext(this.mNativeContext);
                }
                return iStartRequest;
            }
            this.mEnd = true;
            i10 = -4;
        }
        return i10;
    }

    public long startUploadRequest(int i10, int i11) {
        long jNewWakeUpRequestParams = this.mMibrainNativeRequest.newWakeUpRequestParams(i10, i11);
        if (jNewWakeUpRequestParams == 0) {
            return -1L;
        }
        return this.mMibrainNativeRequest.startUploadRequest(jNewWakeUpRequestParams, this.mWakeupRequestParams);
    }

    public int startWakeupRequest(int i10, int i11) {
        long jNewWakeUpRequestParams = this.mMibrainNativeRequest.newWakeUpRequestParams(i10, i11);
        if (jNewWakeUpRequestParams == 0) {
            return -1;
        }
        int iSendWakeUpRequest = this.mMibrainNativeRequest.sendWakeUpRequest(jNewWakeUpRequestParams, this.mWakeupRequestParams);
        this.mMibrainNativeRequest.releaseWakeUpRequestParams(jNewWakeUpRequestParams);
        return iSendWakeUpRequest;
    }

    public void updateRequestParams(MibrainRequestParams mibrainRequestParams) {
        this.mRequestParams = mibrainRequestParams;
    }

    public synchronized void waitForResult() {
        if (this.mEnd) {
            return;
        }
        long j10 = this.mNativeContext;
        if (j10 == 0) {
            return;
        }
        this.mMibrainNativeRequest.stopRequest(j10, MibrainNativeRequest.MI_STOP_FLAG_WAIT_RESULT);
    }
}
