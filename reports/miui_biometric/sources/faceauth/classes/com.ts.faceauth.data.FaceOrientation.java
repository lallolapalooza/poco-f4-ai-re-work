package com.ts.faceauth.data;

/* JADX INFO: loaded from: classes.dex */
public enum FaceOrientation {
    UP(1),
    LEFT(2),
    DOWN(4),
    RIGHT(8),
    UNKNOWN(15);

    final int nativeInt;

    public int getValue() {
        return this.nativeInt;
    }

    FaceOrientation(int i) {
        this.nativeInt = i;
    }
}
