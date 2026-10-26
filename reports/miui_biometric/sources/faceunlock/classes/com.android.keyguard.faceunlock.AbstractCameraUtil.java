package com.android.keyguard.faceunlock;

import android.view.Surface;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractCameraUtil {
    public static final int CAMERA_AVAILABLE = 0;
    public static final int CAMERA_OPEN_INIT = -5;
    public static final int ERROR_CAMERA_DISCONNECTED = -3;
    public static final int ERROR_CAMERA_ID = -1;
    public static final int ERROR_CAMERA_IN_USE = 1;
    public static final int ERROR_EXCEPTION = -2;
    public static final int ERROR_MAX_CAMERAS_IN_USE = 2;
    public static final String TAG = "miui.face.transplant";
    public int width = 640;
    public int height = 480;
    public int angle = 90;

    public void closeCamera() {
    }

    public void closeCameraImmediately() {
    }

    public void closeCameraImmediatelyIfCameraThread() {
    }

    public String getCameraId() {
        return null;
    }

    public void openCamera(int i) {
    }

    public void setCameraCallback(IPreviewCallback iPreviewCallback) {
    }

    public void startPreview(Surface surface) {
    }
}
