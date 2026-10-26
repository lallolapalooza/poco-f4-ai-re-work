package com.kwai.kscnnrenderlib;

import java.util.LinkedList;

/* JADX INFO: loaded from: classes14.dex */
public class YTTechMatting {
    private long mKSJNIRenderObj = 0;
    private volatile long mModelParamIndex = 0;
    private YTTechMattingConfig mYTTechMattingConfig = new YTTechMattingConfig();
    private YTTechMattingParam mYTTechMattingParam = new YTTechMattingParam();
    private YCNNModelInfo.YCNNModelIn mYCNNModelIn = new YCNNModelInfo.YCNNModelIn();
    private YTTechMattingOut mYTTechMattingOut = new YTTechMattingOut();
    private KSJNILib mKSJNILib = new KSJNILib();

    public static class YTMattingIndex {
        public int index = -1;
        public YCNNComm.KSImage img = new YCNNComm.KSImage();
    }

    public static class YTTechMattingConfig {
        public String config_file = "";
        public int matting_type = 0;
    }

    public static class YTTechMattingOut {
        public LinkedList<YTMattingIndex> imgs = new LinkedList<>();
    }

    public static class YTTechMattingParam {
        public int render_level = 0;
        public int out_mul_alpha = 0;
        public int out_cut_valid = 0;
    }

    public static YTTechMatting createYTTechMatting(YTTechMattingConfig yTTechMattingConfig) {
        YTTechMatting yTTechMatting = new YTTechMatting();
        yTTechMatting.mYTTechMattingConfig = yTTechMattingConfig;
        yTTechMatting.mKSJNILib.createTechMatting(yTTechMatting);
        return yTTechMatting;
    }

    public void release() {
        this.mKSJNILib.releaseTechMatting(this);
    }

    public void run(YCNNModelInfo.YCNNModelIn yCNNModelIn, YTTechMattingOut yTTechMattingOut) {
        this.mYCNNModelIn = yCNNModelIn;
        this.mYTTechMattingOut = yTTechMattingOut;
        this.mKSJNILib.runTechMatting(this, yCNNModelIn.data_0);
    }

    public void setParam(YTTechMattingParam yTTechMattingParam) {
        this.mYTTechMattingParam = yTTechMattingParam;
        this.mModelParamIndex++;
    }
}
