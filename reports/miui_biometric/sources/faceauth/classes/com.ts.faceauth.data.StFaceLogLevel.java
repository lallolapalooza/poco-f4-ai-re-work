package com.ts.faceauth.data;

/* JADX INFO: loaded from: classes.dex */
public enum StFaceLogLevel {
    Error(0),
    Warning(1),
    Info(2),
    Debug(3);

    final int nativeInt;

    public int getValue() {
        return this.nativeInt;
    }

    StFaceLogLevel(int i) {
        this.nativeInt = i;
    }
}
