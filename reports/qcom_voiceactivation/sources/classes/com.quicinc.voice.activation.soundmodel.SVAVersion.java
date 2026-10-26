package com.quicinc.voice.activation.soundmodel;

/* JADX INFO: loaded from: classes.dex */
public enum SVAVersion {
    VERSION_UNKNOWN(0, 0, "VERSION_UNKNOWN"),
    VERSION_1_0(256, 0, "SVA1.0"),
    VERSION_2_0(512, 0, "SVA2.0"),
    VERSION_3_0(768, 256, "SVA3.0"),
    VERSION_4_0(1024, 257, "SVA4.0"),
    VERSION_5_0(1280, 258, "SVA5.0"),
    VERSION_6_0(1536, 259, "SVA6.0"),
    VERSION_7_0(1792, 260, "SVA7.0"),
    VERSION_8_0(2048, 261, "SVA8.0");

    private final String mDescription;
    private final int mImplementerVersion;
    private final int mValue;

    SVAVersion(int i2, int i3, String str) {
        this.mValue = i2;
        this.mImplementerVersion = i3;
        this.mDescription = str;
    }

    public static SVAVersion b(int i2) {
        for (SVAVersion sVAVersion : values()) {
            if (i2 == sVAVersion.f()) {
                return sVAVersion;
            }
        }
        return VERSION_UNKNOWN;
    }

    public static SVAVersion c(int i2) {
        for (SVAVersion sVAVersion : values()) {
            if (sVAVersion.e() == i2) {
                return sVAVersion;
            }
        }
        return VERSION_UNKNOWN;
    }

    public String d() {
        return this.mDescription;
    }

    public int e() {
        return this.mImplementerVersion;
    }

    public int f() {
        return this.mValue;
    }
}
