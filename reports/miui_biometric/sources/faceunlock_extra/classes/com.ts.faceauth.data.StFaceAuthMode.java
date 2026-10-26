package com.ts.faceauth.data;

/* JADX INFO: loaded from: classes.dex */
public enum StFaceAuthMode {
    ST_FACEAUTH_MODE_NORMAL(0),
    ST_FACEAUTH_MODE_FAST(1);

    final int mode;

    public int getValue() {
        return this.mode;
    }

    StFaceAuthMode(int i) {
        this.mode = i;
    }
}
