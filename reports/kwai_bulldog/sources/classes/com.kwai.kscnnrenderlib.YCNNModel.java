package com.kwai.kscnnrenderlib;

/* JADX INFO: loaded from: classes14.dex */
public class YCNNModel {
    private KSRenderObj mKSRenderObj = new KSRenderObj();

    public void devFun(int i12) {
        this.mKSRenderObj.devFun(i12);
    }

    public String getInfo() {
        return this.mKSRenderObj.getInfo();
    }

    public int getLandmarks(YCNNModelInfo.KSFaceDetectOut kSFaceDetectOut) {
        return this.mKSRenderObj.getLandmarks(kSFaceDetectOut);
    }

    public YCNNModelInfo.KSLandmarksParam getLandmarksParam() {
        return this.mKSRenderObj.getLandmarksParam();
    }

    public int runModelBuffer(YCNNModelInfo.YCNNModelIn yCNNModelIn) {
        return this.mKSRenderObj.runModelBuffer(yCNNModelIn);
    }

    public void setLandmarksParam(YCNNModelInfo.KSLandmarksParam kSLandmarksParam) {
        this.mKSRenderObj.setLandmarksParam(kSLandmarksParam);
    }
}
