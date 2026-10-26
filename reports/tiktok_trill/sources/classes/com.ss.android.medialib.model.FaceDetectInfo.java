package com.ss.android.medialib.model;

import com.bytedance.covode.number.Covode;

/* JADX INFO: loaded from: classes4.dex */
public class FaceDetectInfo {
    public FaceDetect[] info;

    static {
        Covode.recordClassIndex(41572);
    }

    public FaceDetect[] getInfo() {
        return this.info;
    }

    public void setInfo(FaceDetect[] faceDetectArr) {
        this.info = faceDetectArr;
    }
}
