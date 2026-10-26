package com.android.keyguard.faceunlock;

/* JADX INFO: loaded from: classes.dex */
public class FaceUnlockJni {
    public native void nativeMallopt();

    static {
        System.loadLibrary("jni_faceunlock");
    }
}
