package com.ts.faceauth.data;

import com.ts.faceapi.model.StRect;

/* JADX INFO: loaded from: classes.dex */
public class FaceDetectedInfo {
    private StRect mFaceRect;
    private boolean mIsDetected;

    public boolean getFaceDetected() {
        return this.mIsDetected;
    }

    public StRect getFaceRect() {
        return this.mFaceRect;
    }

    public FaceDetectedInfo() {
        this.mIsDetected = false;
        this.mFaceRect = null;
    }

    public FaceDetectedInfo(boolean z) {
        this.mFaceRect = null;
        this.mIsDetected = z;
    }

    public FaceDetectedInfo(boolean z, StRect stRect) {
        this.mIsDetected = z;
        this.mFaceRect = stRect;
    }
}
