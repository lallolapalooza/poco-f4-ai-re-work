package com.xiaomi.mtb;

import android.util.Log;
import com.xiaomi.modem.ModemUtils;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class MtbModemSceneRecognitionInfo {
    public static final int MODEM_STATS_DATA_TYPE_ANT_INFO = 0;
    public static final int MODEM_STATS_DATA_TYPE_CM_CALL_EVENT_END = 6;
    public static final int MODEM_STATS_DATA_TYPE_CM_CALL_EVENT_ORIG = 5;
    public static final int MODEM_STATS_DATA_TYPE_LTE_RX_PCC = 2;
    public static final int MODEM_STATS_DATA_TYPE_LTE_TX = 1;
    public static final int MODEM_STATS_DATA_TYPE_MIN = 0;
    public static final int MODEM_STATS_DATA_TYPE_NIL = -1;
    public static final int MODEM_STATS_DATA_TYPE_NR5G_TX = 3;
    public static final int MODEM_STATS_DATA_TYPE_SIP_MSG = 4;
    public static final int MODEM_STATS_DATA_TYPE_SIP_SESSION_CANCEL = 11;
    public static final int MODEM_STATS_DATA_TYPE_SIP_SESSION_ESTABLISHED = 9;
    public static final int MODEM_STATS_DATA_TYPE_SIP_SESSION_FAILURE = 12;
    public static final int MODEM_STATS_DATA_TYPE_SIP_SESSION_RINGING = 8;
    public static final int MODEM_STATS_DATA_TYPE_SIP_SESSION_START = 7;
    public static final int MODEM_STATS_DATA_TYPE_SIP_SESSION_TERMINATED = 10;
    public static final int MODEM_STATS_DATA_TYPE_SIP_SESSION_TIMEOUT = 13;
    public static final int MODEM_STATS_DATA_TYPE_VOLTE_SESSION_END = 15;
    public static final int MODEM_STATS_DATA_TYPE_VOLTE_SESSION_SETUP = 14;
    public static final byte MO_CALL_DOING = 1;
    public static final byte MO_CALL_IDLE = 0;
    public static final int SUB_0 = 0;
    public static final int SUB_1 = 1;
    private static final String TAG = "MtbModemSceneRecognitionInfo";
    private MoCallInfo mMoCallInfo;
    private SipInfo mSipInfo;
    private VolteSessionEnd mVolteSessionEnd;
    private VolteSessionSetup mVolteSessionSetup;
    private VolteSipSession mVolteSipSession;
    private VolteSipSessionTimeout mVolteSipSessionTimeout;
    public int mDataType = -1;
    private String mStrSceneRecognitionInfo = "";

    class MoCallInfo {
        public int mMoCallFlag;

        private MoCallInfo() {
            this.mMoCallFlag = 0;
        }

        public String getString(ByteBuffer byteBuffer) {
            if (byteBuffer == null) {
                MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", data is null");
                return "";
            }
            this.mMoCallFlag = byteBuffer.getInt();
            MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", str: Mo Call Start");
            return "Mo Call Start";
        }
    }

    class SipInfo {
        public int mCmCallId;
        public byte mDirection;
        public short mMsgId;
        public short mResponseCode;
        public short mSub;

        private SipInfo() {
            this.mSub = (short) 0;
            this.mDirection = (byte) 0;
            this.mMsgId = (short) 0;
            this.mResponseCode = (short) 0;
            this.mCmCallId = 0;
        }

        public String getString(ByteBuffer byteBuffer) {
            if (byteBuffer == null) {
                MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", data is null");
                return "";
            }
            this.mSub = byteBuffer.getShort();
            this.mDirection = byteBuffer.get();
            this.mMsgId = byteBuffer.getShort();
            this.mResponseCode = byteBuffer.getShort();
            this.mCmCallId = byteBuffer.getInt();
            String str = (((("IMS SIP Message:\nsub: " + ((int) this.mSub) + "\n") + "direction: " + ((int) this.mDirection) + "\n") + "message id: " + ((int) this.mMsgId) + "\n") + "response code: " + ((int) this.mResponseCode) + "\n") + "call id: " + this.mCmCallId + "\n";
            MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", str: " + str);
            return str;
        }
    }

    class VolteSessionEnd {
        public int mCallSetupDelay;
        public byte mDirection;
        public short mEndCause;
        public byte mRat;

        private VolteSessionEnd() {
            this.mDirection = (byte) 0;
            this.mEndCause = (short) 0;
            this.mCallSetupDelay = 0;
            this.mRat = (byte) 0;
        }

        public String getString(ByteBuffer byteBuffer) {
            if (byteBuffer == null) {
                MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", data is null");
                return "";
            }
            this.mDirection = byteBuffer.get();
            this.mEndCause = byteBuffer.getShort();
            this.mCallSetupDelay = byteBuffer.getInt();
            this.mRat = byteBuffer.get();
            String str = ((("Volte End:\ndirection: " + ((int) this.mDirection) + "\n") + "end cause: " + ((int) this.mEndCause) + "\n") + "call delay: " + this.mCallSetupDelay + "\n") + "rat: " + ((int) this.mRat) + "\n";
            MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", str: " + str);
            return str;
        }
    }

    class VolteSessionSetup {
        public int mCallSetupDelay;
        public byte mDirection;
        public byte mRat;
        public short mResult;

        private VolteSessionSetup() {
            this.mDirection = (byte) 0;
            this.mResult = (short) 0;
            this.mCallSetupDelay = 0;
            this.mRat = (byte) 0;
        }

        public String getString(ByteBuffer byteBuffer) {
            if (byteBuffer == null) {
                MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", data is null");
                return "";
            }
            this.mDirection = byteBuffer.get();
            this.mResult = byteBuffer.getShort();
            this.mCallSetupDelay = byteBuffer.getInt();
            this.mRat = byteBuffer.get();
            String str = ((("Volte Setup:\ndirection: " + ((int) this.mDirection) + "\n") + "result: " + ((int) this.mResult) + "\n") + "call delay(ms): " + this.mCallSetupDelay + "\n") + "rat: " + ((int) this.mRat) + "\n";
            MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", str: " + str);
            return str;
        }
    }

    class VolteSipSession {
        public int mSipCallId;

        private VolteSipSession() {
            this.mSipCallId = 0;
        }

        public String getString(ByteBuffer byteBuffer, int i2) {
            if (byteBuffer == null) {
                MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", data is null");
                return "";
            }
            this.mSipCallId = byteBuffer.getInt();
            String str = ("Volte State:\nstate: " + i2 + "\n") + "call id: " + this.mSipCallId + "\n";
            MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", str: " + str);
            return str;
        }
    }

    class VolteSipSessionTimeout {
        public int mStateCurrent;
        public int mStateLast;

        private VolteSipSessionTimeout() {
            this.mStateCurrent = 0;
            this.mStateLast = 0;
        }

        public String getString(ByteBuffer byteBuffer) {
            if (byteBuffer == null) {
                MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", data is null");
                return "";
            }
            this.mStateCurrent = byteBuffer.getInt();
            this.mStateLast = byteBuffer.getInt();
            String str = ("Volte timeout:\ncurrent state: " + this.mStateCurrent + "\n") + "last state: " + this.mStateLast + "\n";
            MtbModemSceneRecognitionInfo.log(ModemUtils.getCurrentMethodName() + ", " + ModemUtils.getCurrentClassName() + ", str: " + str);
            return str;
        }
    }

    public MtbModemSceneRecognitionInfo() {
        this.mSipInfo = new SipInfo();
        this.mMoCallInfo = new MoCallInfo();
        this.mVolteSipSession = new VolteSipSession();
        this.mVolteSipSessionTimeout = new VolteSipSessionTimeout();
        this.mVolteSessionSetup = new VolteSessionSetup();
        this.mVolteSessionEnd = new VolteSessionEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    public String getStringFromSceneRecognitionIndData(ByteBuffer byteBuffer) {
        String string;
        log("getStringFromSceneRecognitionIndData");
        String str = "";
        if (byteBuffer == null) {
            log("getStringFromSceneRecognitionIndData, data is null");
            return "";
        }
        try {
            int i2 = byteBuffer.getInt();
            if (4 == i2) {
                string = this.mSipInfo.getString(byteBuffer);
            } else if (5 == i2) {
                string = this.mMoCallInfo.getString(byteBuffer);
            } else if (7 == i2 || 8 == i2 || 9 == i2 || 10 == i2 || 11 == i2 || 12 == i2) {
                string = this.mVolteSipSession.getString(byteBuffer, i2);
            } else if (13 == i2) {
                string = this.mVolteSipSessionTimeout.getString(byteBuffer);
            } else {
                if (14 != i2) {
                    if (15 == i2) {
                        string = this.mVolteSessionEnd.getString(byteBuffer);
                    } else {
                        log("dataType is invalid, dataType = " + i2);
                    }
                    String str2 = MtbUtils.timeDateToString(MtbUtils.getCurrentTimeWithData()) + " - " + str + "\n" + this.mStrSceneRecognitionInfo;
                    this.mStrSceneRecognitionInfo = str2;
                    return ModemUtils.toHtmlFormat(str2);
                }
                string = this.mVolteSessionSetup.getString(byteBuffer);
            }
            str = string;
        } catch (Exception e2) {
            log("getStringFromSceneRecognitionIndData, Exception info: " + e2);
            e2.printStackTrace();
        }
        String str3 = MtbUtils.timeDateToString(MtbUtils.getCurrentTimeWithData()) + " - " + str + "\n" + this.mStrSceneRecognitionInfo;
        this.mStrSceneRecognitionInfo = str3;
        return ModemUtils.toHtmlFormat(str3);
    }
}
