package com.android.keyguard.faceunlock;

/* JADX INFO: loaded from: classes.dex */
public interface FaceUnlockCallback {
    void onFaceAuthFailed(boolean z);

    void onFaceAuthenticated();

    void onFaceHelp(int i);

    void onFaceStart();
}
