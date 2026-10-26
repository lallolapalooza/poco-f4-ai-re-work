package com.miui.extraphoto.refocus.manager;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public class DualPhotoJni {
    private static final Class<AlgoManager> sAlgoManagerClass = AlgoManager.class;

    public static native long allocNativeImage(long j);

    public static native void configBitmapByNativeImage(Bitmap bitmap, long j);

    public static native void configYUVData(long j, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native void copyImage(long j, long j2);

    public static native long copyNativeData(long j, int i);

    public static native void enableRelightingEffect(long j, int i, long j2, long j3, long j4, long j5, int i2, int[] iArr);

    public static native long generateNativeData(byte[] bArr, int i);

    public static native long generateNativeImage(Bitmap bitmap, int i, int i2);

    public static native long initDynamicMotion(int i, int i2);

    public static native long initDynamicSpin(float f, boolean z, int i);

    public static native long initMeituEffect(long j, int i, int i2);

    public static native long initRelightingEffect(int i, long j, long j2, long j3, int i2, int[] iArr);

    public static native void previewMeituEffect(long j, int i, int i2, byte[] bArr, byte[] bArr2);

    public static native void processDynamicMotion(long j, long j2, long j3, long j4);

    public static native void processDynamicSpin(long j, long j2, long j3, long j4);

    public static native void processMeituEffect(long j, Bitmap bitmap, long j2, int i, int i2, boolean z);

    public static native void processMiRawDepth(long j);

    public static native void processMiRefocus(long j, long j2, long j3, float f, int i);

    public static native void processRefocus(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, long j3, int i7, long j4, int i8);

    public static native void processRefocusWithHandle(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5, int i6, long j4, int i7);

    public static native void releaseMeituEffect(long j);

    public static native long releaseNativeData(long j);

    public static native void releaseNativeImage(long j);

    public static native void releaseRefocusHandle(long j);

    public static native void releaseRelightingHandle(long j);

    public static native long setRelightingLightSource(long j, int i, int i2, int i3, int i4);
}
