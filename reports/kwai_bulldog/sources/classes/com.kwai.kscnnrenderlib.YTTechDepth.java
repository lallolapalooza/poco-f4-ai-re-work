package com.kwai.kscnnrenderlib;

/* JADX INFO: loaded from: classes14.dex */
public class YTTechDepth {
    private static YTTechDepth depthInstance;
    private int input_c;
    private int input_h;
    private int input_w;
    public YCNNComm.KSImage maskOut;
    private long mKSJNIRenderObj = 0;
    private float mFocal = 0.0f;
    private KSJNILib mKSJNILib = new KSJNILib();

    public static YTTechDepth createYTTechDepth(String str) {
        YTTechDepth yTTechDepth = depthInstance;
        if (yTTechDepth != null) {
            return yTTechDepth;
        }
        YTTechDepth yTTechDepth2 = new YTTechDepth();
        depthInstance = yTTechDepth2;
        yTTechDepth2.mKSJNILib.createTechDepth(yTTechDepth2, str);
        YTTechDepth yTTechDepth3 = depthInstance;
        if (yTTechDepth3.mKSJNIRenderObj != 0) {
            return yTTechDepth3;
        }
        depthInstance = null;
        return null;
    }

    public float getFocallen() {
        float techDepthFocal = this.mKSJNILib.getTechDepthFocal(this);
        this.mFocal = techDepthFocal;
        return techDepthFocal;
    }

    public void getMask(YCNNComm.KSImage kSImage) {
        this.maskOut = kSImage;
        this.mKSJNILib.getTechDepthMask(this);
    }

    public void release() {
    }

    public int run(byte[] bArr, int i12, int i13, int i14) {
        this.input_w = i12;
        this.input_h = i13;
        this.input_c = i14 / i12;
        return this.mKSJNILib.runTechDepth(this, bArr, i12, i13, i14);
    }
}
