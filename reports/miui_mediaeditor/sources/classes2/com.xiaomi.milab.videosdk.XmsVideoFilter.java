package com.xiaomi.milab.videosdk;

/* JADX INFO: loaded from: classes2.dex */
public class XmsVideoFilter extends XmsFilter {
    public XmsVideoFilter(String str) {
        super(str);
    }

    private native double nativeGetDoubleParam(long j, String str);

    private native int nativeGetIntParam(long j, String str);

    private native float[] nativeGetRect(long j, String str);

    private native void nativeSetColorParam(long j, String str, int i10, int i11, int i12, int i13);

    private native void nativeSetDoubleParam(long j, String str, double d);

    private native void nativeSetIntParam(long j, String str, int i10);

    private native void nativeSetMat4(long j, String str, float[] fArr);

    private native void nativeSetRect(long j, String str, float[] fArr);

    private native void nativeSetStringParam(long j, String str, String str2);

    public double getDoubleParam(String str) {
        logThisMethod("getDoubleParam");
        if (checkNull("getDoubleParam")) {
            return 0.0d;
        }
        return nativeGetDoubleParam(getNativeObject(), str);
    }

    public int getIntParam(String str) {
        logThisMethod("getIntParam");
        if (checkNull("getIntParam")) {
            return 0;
        }
        return nativeGetIntParam(getNativeObject(), str);
    }

    public float[] getRect(String str) {
        logThisMethod("getRect");
        if (checkNull("getRect")) {
            return null;
        }
        return nativeGetRect(getNativeObject(), str);
    }

    public void setColorParam(String str, int i10, int i11, int i12, int i13) {
        logThisMethod("setColorParam");
        if (checkNull("setColorParam")) {
            return;
        }
        nativeSetColorParam(getNativeObject(), str, i10, i11, i12, i13);
    }

    public void setDoubleParam(String str, double d) {
        logThisMethod("setDoubleParam");
        if (checkNull("setDoubleParam")) {
            return;
        }
        nativeSetDoubleParam(getNativeObject(), str, d);
    }

    public void setIntParam(String str, int i10) {
        logThisMethod("setIntParam");
        if (checkNull("setIntParam")) {
            return;
        }
        nativeSetIntParam(getNativeObject(), str, i10);
    }

    public void setMat4(String str, float[] fArr) {
        logThisMethod("setMat4");
        if (checkNull("setMat4")) {
            return;
        }
        nativeSetMat4(getNativeObject(), str, fArr);
    }

    public void setRect(String str, float[] fArr) {
        logThisMethod("setRect");
        if (checkNull("setRect")) {
            return;
        }
        nativeSetRect(getNativeObject(), str, fArr);
    }

    public void setStringParam(String str, String str2) {
        logThisMethod("setStringParam");
        if (checkNull("setStringParam")) {
            return;
        }
        nativeSetStringParam(getNativeObject(), str, str2);
    }
}
