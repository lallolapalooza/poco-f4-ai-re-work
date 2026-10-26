package com.quicinc.voice.activation.soundmodel;

/* JADX INFO: loaded from: classes.dex */
public enum BigSoundModelType {
    UNKNOWN("UNKNOWN"),
    UIM("UIM"),
    UDM("UDM");

    private final String mName;

    BigSoundModelType(String str) {
        this.mName = str;
    }

    public static BigSoundModelType b(String str) {
        for (BigSoundModelType bigSoundModelType : values()) {
            if (bigSoundModelType.c().equalsIgnoreCase(str)) {
                return bigSoundModelType;
            }
        }
        return UNKNOWN;
    }

    public String c() {
        return this.mName;
    }

    public boolean d() {
        return "UDM".equalsIgnoreCase(this.mName);
    }
}
