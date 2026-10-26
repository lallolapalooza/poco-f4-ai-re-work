package com.quicinc.voice.activation.algorithm;

/* JADX INFO: loaded from: classes.dex */
public enum AlgorithmProcessResult$AlgorithmResultKey {
    UNKNOWN("UNKNOWN"),
    DETECTED_FLAG("detected"),
    USER_SCORE("uv_score"),
    CONFIDENCE_SCORE("confidence_score"),
    KEYWORD_START_INDEX("keyword_start_index"),
    KEYWORD_END_INDEX("keyword_end_index"),
    DETECTED_INDEX("detected_index");

    private final String mDescriptor;

    AlgorithmProcessResult$AlgorithmResultKey(String str) {
        this.mDescriptor = str;
    }

    public String b() {
        return this.mDescriptor;
    }
}
