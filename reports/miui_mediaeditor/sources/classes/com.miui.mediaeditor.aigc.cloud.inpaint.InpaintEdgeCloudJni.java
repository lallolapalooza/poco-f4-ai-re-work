package com.miui.mediaeditor.aigc.cloud.inpaint;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import com.miui.mediaeditor.aigc.cloud.inpaint.entity.InpaintSdkData;

/* JADX INFO: loaded from: classes2.dex */
@Keep
class InpaintEdgeCloudJni {
    public static long init(String str, String str2, String str3, String str4, String str5) {
        return nativeInit(str, str2, str3, str4, str5);
    }

    public static int inpaint(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i10, int i11, InpaintSdkData inpaintSdkData, long j, int i12, int[] iArr) {
        return nativeInpaint(bitmap, bitmap2, bitmap3, i10, i11, inpaintSdkData, j, i12, iArr);
    }

    private static native long nativeInit(String str, String str2, String str3, String str4, String str5);

    private static native int nativeInpaint(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i10, int i11, InpaintSdkData inpaintSdkData, long j, int i12, int[] iArr);

    private static native void nativeRelease(long j);

    private static native int nativeUpsample(Bitmap bitmap, int i10, int i11, InpaintSdkData inpaintSdkData);

    public static void release(long j) {
        nativeRelease(j);
    }

    public static int upsample(Bitmap bitmap, int i10, int i11, InpaintSdkData inpaintSdkData) {
        return nativeUpsample(bitmap, i10, i11, inpaintSdkData);
    }
}
