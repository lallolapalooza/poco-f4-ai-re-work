package com.ts.faceauth.data;

/* JADX INFO: loaded from: classes.dex */
public enum CvPixelFormat {
    GRAY8(0),
    YUV420P(1),
    NV12(2),
    NV21(3),
    BGRA8888(4),
    BGR888(5),
    RGBA8888(6),
    RGB888(7);

    final int nativeInt;

    public int getValue() {
        return this.nativeInt;
    }

    CvPixelFormat(int i) {
        this.nativeInt = i;
    }
}
