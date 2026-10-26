package com.kwai.kscnnrenderlib;

import com.yxcorp.bugly.CrashReporter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes14.dex */
public class ImgRecog {
    private long mKSJNIRenderObj = 0;
    private volatile long mModelParamIndex = 0;
    private ImgRecogConfig mImgRecogConfig = new ImgRecogConfig();
    private ImgRecogParam mImgRecogParam = new ImgRecogParam();
    private YCNNModelInfo.YCNNModelIn mYCNNModelIn = new YCNNModelInfo.YCNNModelIn();
    private YCNNModelInfo.KSGeneralRecogOut mImgRecogOut = new YCNNModelInfo.KSGeneralRecogOut();
    private KSJNILib mKSJNILib = new KSJNILib();

    public static class ImgRecogConfig {
        public String landmarkModelpath = "";
        public String faceattribModelpath = "";
        public String generalrecogModelpath = "";
        public int load_object_submodel = 1;
        public int load_scene_submodel = 1;
        public ArrayList<String> load_object_submodel_names = new ArrayList<>();
        public ArrayList<String> load_scene_submodel_names = new ArrayList<>();
        public int recog_mode = 1;
    }

    public static class ImgRecogParam {
        public int multiSinglePersonMode = 1;
        public int getPersonInfo = 1;
        public int useFaceID = 0;
        public int topN = -1;
        public int sceneOut = 1;
        public int objectOut = 1;
        public int subObjectOut = 1;
        public int subSceneOut = 1;
    }

    public static ImgRecog createImgRecog(ImgRecogConfig imgRecogConfig) {
        ImgRecog imgRecog = new ImgRecog();
        imgRecog.mImgRecogConfig = imgRecogConfig;
        if (imgRecog.mKSJNILib.createImgRecog(imgRecog) == 0) {
            return imgRecog;
        }
        return null;
    }

    public void release() {
        synchronized (this) {
            try {
                this.mKSJNILib.releaseImgRecog(this);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "ImgRecog.class", "release", -1);
                throw th2;
            }
        }
    }

    public void resetFaceID() {
        synchronized (this) {
            try {
                this.mKSJNILib.resetImgRecogFaceID(this);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "ImgRecog.class", "resetFaceID", -1);
                throw th2;
            }
        }
    }

    public void run(YCNNModelInfo.YCNNModelIn yCNNModelIn, YCNNModelInfo.KSGeneralRecogOut kSGeneralRecogOut) {
        this.mYCNNModelIn = yCNNModelIn;
        this.mImgRecogOut = kSGeneralRecogOut;
        synchronized (this) {
            try {
                this.mKSJNILib.runImgRecog(this, this.mYCNNModelIn.data_0);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "ImgRecog.class", "run", -1);
                throw th2;
            }
        }
    }

    public void setLogOn(boolean z12) {
        synchronized (this) {
            try {
                this.mKSJNILib.setLogOn(this, z12);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "ImgRecog.class", "setLogOn", -1);
                throw th2;
            }
        }
    }

    public void setParam(ImgRecogParam imgRecogParam) {
        this.mImgRecogParam = imgRecogParam;
        this.mModelParamIndex++;
    }
}
