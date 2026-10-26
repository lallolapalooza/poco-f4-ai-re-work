package com.miui.mediaeditor.aigc.remover;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import com.miui.mediaeditor.aigc.remover.param.NNFData;

/* JADX INFO: loaded from: classes2.dex */
@Keep
class InpaintProJni {
    public static long init(long j, long j10, String str, String str2) {
        return nativeInit(j, j10, str, str2);
    }

    public static int inpaint(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i10, int i11, NNFData nNFData, long j, int i12, int[] iArr) {
        return nativeInpaint(bitmap, bitmap2, bitmap3, i10, i11, nNFData, j, i12, iArr);
    }

    private static native long nativeInit(long j, long j10, String str, String str2);

    private static native int nativeInpaint(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i10, int i11, NNFData nNFData, long j, int i12, int[] iArr);

    private static native void nativeRelease(long j);

    private static native int nativeUpsample(Bitmap bitmap, int i10, int i11, NNFData nNFData, long j);

    public static void release(long j) {
        nativeRelease(j);
    }

    public static int upsample(long j, Bitmap bitmap, int i10, int i11, NNFData nNFData) {
        return nativeUpsample(bitmap, i10, i11, nNFData, j);
    }
}
