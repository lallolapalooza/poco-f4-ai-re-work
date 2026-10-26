package com.ts.faceauth.data;

/* JADX INFO: loaded from: classes.dex */
public enum StFaceGpuPriority {
    ST_FACE_GpuModePriHigh(0),
    ST_FACE_GpuModePriNormal(1),
    ST_FACE_GpuModePriLow(2);

    final int nativeInt;

    public int getValue() {
        return this.nativeInt;
    }

    StFaceGpuPriority(int i) {
        this.nativeInt = i;
    }
}
