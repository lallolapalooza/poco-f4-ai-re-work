package com.miui.mediaeditor.detection.scene;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes2.dex */
public class SceneDetector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f7808a = {"libyuv.so", "libcamera_scene.so", "libFaceDetLmd.so", "libscene_detection.so"};

    public static native int detectScene(Bitmap bitmap);

    public static native void init();

    public static native void release();
}
