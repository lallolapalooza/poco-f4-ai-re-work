package com.ts.faceauth.bean;

/* JADX INFO: loaded from: classes.dex */
public class EnrollResult {
    private int mEnrollStatus;
    private int mFaceId;
    private int mNativeStatus;

    public int getEnrollStatus() {
        return this.mEnrollStatus;
    }

    public int getFaceId() {
        return this.mFaceId;
    }

    public int getNativeStatue() {
        return this.mNativeStatus;
    }

    public void setNativeStatus(int i) {
        this.mNativeStatus = i;
    }

    public EnrollResult() {
        this.mFaceId = -1;
    }

    public EnrollResult(int i, int i2) {
        this.mFaceId = i;
        this.mEnrollStatus = i2;
    }
}
