package com.quicinc.voice.activation.soundmodel.opaque.data;

/* JADX INFO: loaded from: classes.dex */
public enum Header$Key {
    CONFIDENCE_LEVELS,
    HISTORY_BUFFER_CONFIG,
    KEYWORD_INDICES,
    TIMESTAMP,
    PERFORMANCE_MODE,
    NONE;

    public static Header$Key b(int i2) {
        for (Header$Key header$Key : values()) {
            if (i2 == header$Key.ordinal()) {
                return header$Key;
            }
        }
        return NONE;
    }

    public static int c() {
        return 4;
    }
}
