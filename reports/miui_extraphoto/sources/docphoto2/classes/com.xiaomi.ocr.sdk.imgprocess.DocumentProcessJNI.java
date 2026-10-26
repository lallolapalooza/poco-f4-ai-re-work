package com.xiaomi.ocr.sdk.imgprocess;

import android.graphics.Bitmap;
import android.util.Log;
import com.miui.extraphoto.docphoto.document.DocPhotoAccessUtils;

/* JADX INFO: loaded from: classes.dex */
public class DocumentProcessJNI {
    public static native Bitmap nativeCropAndEnhanceBitmap(long j, Bitmap bitmap, float[] fArr, int i, int i2, boolean z);

    public static native Bitmap nativeCropImageBitmap(long j, Bitmap bitmap, float[] fArr, int i);

    public static native int nativeDewarpBitmap(long j, Bitmap bitmap);

    public static native int nativeEnhanceBitmap(long j, Bitmap bitmap, int i, boolean z);

    public static native int nativeGetDelegateColor(long j, Bitmap bitmap, int[] iArr);

    public static native String nativeGetVersion();

    public static native void nativeGlobalInit();

    public static native int nativeHandwriteRemoveBitmap(long j, Bitmap bitmap, Bitmap bitmap2);

    public static native long nativeInit(String str, String str2);

    public static native void nativeInitAndRun(String str, String str2);

    public static native void nativeRelease(long j);

    public static native int nativeScanDocumentBitmap(long j, Bitmap bitmap, int i, float[] fArr, int i2);

    static {
        System.loadLibrary("doc_photo_c++_shared");
        System.loadLibrary("doc_photo_process");
        nativeGlobalInit();
    }

    public static boolean isSupportMoer() {
        String strNativeGetVersion = nativeGetVersion();
        Log.d("DocumentProcessJNI", "isSupportMoer: version=" + strNativeGetVersion);
        if (strNativeGetVersion != null && strNativeGetVersion.length() >= 2) {
            try {
                if (Integer.parseInt(strNativeGetVersion.substring(1, 2)) < 2) {
                    Log.d("DocumentProcessJNI", "wrong version, version = " + strNativeGetVersion);
                    return false;
                }
                return DocPhotoAccessUtils.isSupportMoer();
            } catch (NumberFormatException unused) {
                Log.d("DocumentProcessJNI", "parse version error");
            }
        }
        return false;
    }

    public static boolean isSupportHandwrite() {
        String strNativeGetVersion = nativeGetVersion();
        Log.d("DocumentProcessJNI", "isSupportHandwrite: version=" + strNativeGetVersion);
        if (strNativeGetVersion != null && strNativeGetVersion.length() >= 2) {
            try {
                if (Integer.parseInt(strNativeGetVersion.substring(1, 2)) < 3) {
                    Log.d("DocumentProcessJNI", "wrong version, version = " + strNativeGetVersion);
                    return false;
                }
                return DocPhotoAccessUtils.isSupportHandwrite();
            } catch (NumberFormatException unused) {
                Log.d("DocumentProcessJNI", "parse version error");
            }
        }
        return false;
    }
}
