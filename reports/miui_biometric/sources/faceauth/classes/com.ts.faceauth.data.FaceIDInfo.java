package com.ts.faceauth.data;

/* JADX INFO: loaded from: classes.dex */
public class FaceIDInfo {
    public int mFaceID;
    public int mFeatureCountOfFace;

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public FaceIDInfo m48clone() {
        FaceIDInfo faceIDInfo = new FaceIDInfo();
        faceIDInfo.mFaceID = this.mFaceID;
        faceIDInfo.mFeatureCountOfFace = this.mFeatureCountOfFace;
        return faceIDInfo;
    }
}
