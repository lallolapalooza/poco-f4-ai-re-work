package com.miui.extraphoto.refocus.manager;

/* JADX INFO: loaded from: classes.dex */
public class MegviiDualPhotoJni {
    private static final Class<AlgoManager> sAlgoManagerClass = AlgoManager.class;

    public static native long initRefocus();

    public static native void processRefocusWidthHandle(long j, long j2, long j3, long j4, int i, int i2, int i3, float f, int i4, int i5, int i6);

    public static native void releaseRefocus(long j);

    public static void processRefocus(long j, long j2, long j3, int i, int i2, int i3, float f, int i4, int i5, int i6) {
        long jInitRefocus = initRefocus();
        processRefocusWidthHandle(jInitRefocus, j, j2, j3, i, i2, i3, f, i4, i5, i6);
        releaseRefocus(jInitRefocus);
    }
}
