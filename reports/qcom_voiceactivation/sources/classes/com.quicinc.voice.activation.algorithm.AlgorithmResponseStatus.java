package com.quicinc.voice.activation.algorithm;

import com.qualcomm.qti.qesdk.QesdkUtils;

/* JADX INFO: loaded from: classes.dex */
public enum AlgorithmResponseStatus {
    ALGORITHM_SUCCESS(0, QesdkUtils.TAGS.TAG_SUCCESS),
    ALGORITHM_FAILURE(-1, "failure"),
    ALGORITHM_BAD_PARAM(-2, "bad param"),
    ALGORITHM_NO_LIB(-3, "no library"),
    ALGORITHM_NO_FUNCTION(-4, "no function"),
    ALGORITHM_INVAL_CALL(-5, "invalidate call"),
    ALGORITHM_NOT_INIT(-6, "not init"),
    ALGORITHM_NO_MEM(-7, "no memory"),
    ALGORITHM_WRONG_FRAME(-8, "wrong frame"),
    ALGORITHM_WRONG_STATUS(-9, "wrong status"),
    ALGORITHM_NO_MODEL(-10, "lack of model"),
    ALGORITHM_NO_SESSION(-11, "no 1st stage session"),
    ALGORITHM_LOW_LAYER_FAILURE(-12, "sound trigger error");

    private final String mReason;
    private final int mValue;

    AlgorithmResponseStatus(int i2, String str) {
        this.mValue = i2;
        this.mReason = str + "(" + i2 + ")";
    }

    public static AlgorithmResponseStatus b(int i2) {
        for (AlgorithmResponseStatus algorithmResponseStatus : values()) {
            if (algorithmResponseStatus.mValue == i2) {
                return algorithmResponseStatus;
            }
        }
        return ALGORITHM_FAILURE;
    }

    public boolean c() {
        return this == ALGORITHM_SUCCESS;
    }

    public String d() {
        return this.mReason;
    }
}
