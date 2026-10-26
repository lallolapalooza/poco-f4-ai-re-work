package com.kwai.kscnnrenderlib;

import com.kwai.video.editorsdk2.EditorSdk2Utils;
import com.yxcorp.bugly.CrashReporter;

/* JADX INFO: loaded from: classes14.dex */
public class KSJNILib {
    public static String TAG = "ycnn2";

    static {
        try {
            System.loadLibrary("c++_shared");
            System.loadLibrary(EditorSdk2Utils.SO_LIBRARY_TYPE_YKIT);
        } catch (Throwable th2) {
            CrashReporter.logCheckedException(th2, "KSJNILib.class", "<clinit>", 21);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("System.loadLibrary(ycnn2) error: ");
            sb2.append(th2.getMessage());
        }
    }

    public native int YCNNGetConfig2Model(String str, YCNNModelInfo.YCNNModelConfig yCNNModelConfig);

    public native int createImgRecog(Object obj);

    public native int createModel(Object obj);

    public native int createModelPost(Object obj);

    public native int createTechDepth(Object obj, String str);

    public native int createTechMatting(Object obj);

    public native int devFun(Object obj, int i12);

    public native int getContour(Object obj, Object obj2, Object obj3);

    public native int getContour(Object obj, Object obj2, Object obj3, float f12, float f13, float f14, float f15);

    public native int getImageColor(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6);

    public native int getImageConnectedRange(Object obj, Object obj2, Object obj3, int i12, int i13, int i14, int i15);

    public native int getImageValidRange(Object obj, Object obj2, int i12, int i13, int[] iArr);

    public native float getTechDepthFocal(Object obj);

    public native void getTechDepthMask(Object obj);

    public native int initDepth(Object obj, String str);

    public native int releaseImgRecog(Object obj);

    public native int releaseModel(Object obj);

    public native int releaseModelPost(Object obj);

    public native int releaseObj(Object obj);

    public native int releaseTechDepth(Object obj);

    public native int releaseTechMatting(Object obj);

    public native int renderModel(Object obj, byte[] bArr);

    public native int renderModelPost(Object obj);

    public native int resetImgRecogFaceID(Object obj);

    public native int runImgRecog(Object obj, byte[] bArr);

    public native int runTechDepth(Object obj, byte[] bArr, int i12, int i13, int i14);

    public native int runTechMatting(Object obj, byte[] bArr);

    public native void setLogOn(Object obj, boolean z12);

    public native int setUploadStatsCallBack(Object obj, String str);

    public native int updateModelOut(Object obj);
}
