package com.ts.faceauth.bean;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticationResult {
    private int mAuthStatus;
    private int mNativeStatus;
    private int mUnlockUserID;

    public int getAuthStatus() {
        return this.mAuthStatus;
    }

    public int getNativeStatue() {
        return this.mNativeStatus;
    }

    public int getUnlockID() {
        return this.mUnlockUserID;
    }

    public void setNativeStatus(int i) {
        this.mNativeStatus = i;
    }

    public AuthenticationResult() {
    }

    public AuthenticationResult(int i, int i2) {
        this.mAuthStatus = i;
        this.mUnlockUserID = i2;
    }
}
