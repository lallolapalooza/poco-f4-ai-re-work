package com.miui.extraphoto.refocus.manager;

import com.miui.extraphoto.refocus.Util;

/* JADX INFO: loaded from: classes.dex */
public class MiDualPhotoJni {
    private static final Class<AlgoManager> sAlgoManagerClass = AlgoManager.class;
    private static String sJsonPath;

    public static native void enableBrainRelightingEffect(long j, int i, long j2, long j3, long j4, long j5, int i2, int[] iArr);

    public static native long generateNativeMaskImage(int i, int i2, int i3);

    public static native long initBrainRelightingEffect(int i, long j, long j2, int i2, int[] iArr);

    private static native long initDynamicSpot(int i, long j, long j2, long j3, long j4, long j5, int i2, int i3, int i4, int i5, String str);

    private static native long initMiCameraRefocus(int i, boolean z, int i2, int i3, String str);

    public static native void processFlickerSpot(long j, int i);

    public static native void processMiCameraRefocusWidthHandle(long j, long j2, long j3, long j4, long j5, int i, int i2, int i3, int i4, int i5, int i6, long j6, int i7, boolean z);

    public static native void processSpot(long j);

    public static native void releaseBrainRelightingHandle(long j);

    public static native void releaseDynamicSpot(long j);

    public static native void releaseMiCameraRefocus(long j);

    public static native void releaseNativeMaskImage(long j);

    public static native long setBrainRelightingLightSource(long j, int i, int i2, int i3, int i4);

    public static void processMiCameraRefocus(int i, boolean z, int i2, int i3, long j, long j2, long j3, long j4, int i4, int i5, int i6, int i7, int i8, int i9, long j5, int i10, boolean z2) {
        long jInitMiCameraRefocus = initMiCameraRefocus(i, z, i2, i3);
        processMiCameraRefocusWidthHandle(jInitMiCameraRefocus, j, j2, j3, j4, i4, i5, i6, i7, i8, i9, j5, i10, z2);
        releaseMiCameraRefocus(jInitMiCameraRefocus);
    }

    public static long initMiCameraRefocus(int i, boolean z, int i2, int i3) {
        enSureJsonPath();
        return initMiCameraRefocus(i, z, i2, i3, sJsonPath);
    }

    private static void enSureJsonPath() {
        if (sJsonPath == null) {
            sJsonPath = Util.getMiCameraDualBokehJsonPath();
        }
    }

    public static long initDynamicSpot(int i, long j, long j2, long j3, long j4, long j5, int i2, int i3, int i4, int i5) {
        enSureJsonPath();
        return initDynamicSpot(i, j, j2, j3, j4, j5, i2, i3, i4, i5, sJsonPath);
    }
}
