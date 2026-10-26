package com.ts.faceauth;

import com.ts.faceapi.model.StRect;
import com.ts.faceauth.data.FaceDetectedInfo;
import com.ts.faceauth.data.FaceIDInfo;
import com.ts.faceauth.data.STImage;

/* JADX INFO: loaded from: classes.dex */
public class FaceAuthNative {
    private static FaceDetectedCallback mFaceDetectedCallback;

    public static native void auth(STImage sTImage, int i, int[] iArr, int[] iArr2);

    public static native void createFaceAuth(String str, int i, int[] iArr);

    public static native int deleteAllFace();

    public static native int deleteFace(int i);

    public static native void enableFaceDetectCallback(boolean z);

    public static native int enroll(STImage sTImage, int i, int[] iArr);

    public static native int evaluate(String str, String str2, int i, int i2, int i3, int i4, int i5);

    public static native String getAlgoInfo();

    public static native FaceIDInfo[] getFaceIDInfoInTemplate(int[] iArr);

    public static native int getLastUnlockID(int[] iArr);

    public static native String getSDKVersion();

    public static native int initLicense(String str);

    public static native void releaseFaceAuth();

    public static native int resetFaceAuth();

    public static native int setAreaCode(int i);

    public static native int setEnrollNumberForFace(int i);

    public static native int setFaceAuthMode(int i);

    public static native void setGPUPriority(int i);

    public static native void setIdentifier(String str, int[] iArr);

    public static native void setLogLevel(int i);

    public static native void setOrientation(int i, int[] iArr);

    public static native void setRoi(StRect stRect, int[] iArr);

    public static native void setStandByMode();

    public static native int setTeeCommunicationFunc();

    public static native int setTeeCommunicationFuncAddr(long j);

    static {
        System.loadLibrary("jni_stfaceunlock_api");
        onFaceDetected(new FaceDetectedInfo(false, null));
    }

    public static void onFaceDetected(FaceDetectedInfo faceDetectedInfo) {
        FaceDetectedCallback faceDetectedCallback = mFaceDetectedCallback;
        if (faceDetectedCallback != null) {
            faceDetectedCallback.onDetected(faceDetectedInfo);
        }
    }

    public static void setCallback(FaceDetectedCallback faceDetectedCallback) {
        if (faceDetectedCallback == null) {
            throw new NullPointerException("callback can not be null");
        }
        mFaceDetectedCallback = faceDetectedCallback;
    }
}
