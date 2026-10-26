package com.looksery.sdk.domain;

import defpackage.AbstractC19915fE3;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class CameraConfiguration {
    public CameraIndex mCameraIndex;
    public CameraColorSpace mColorSpace;

    public enum CameraColorSpace {
        GRAYSCALE,
        RGBY
    }

    public enum CameraIndex {
        LEFT,
        RIGHT,
        STEREO
    }

    public CameraConfiguration(CameraColorSpace cameraColorSpace, CameraIndex cameraIndex) {
        this.mColorSpace = cameraColorSpace;
        this.mCameraIndex = cameraIndex;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CameraConfiguration.class != obj.getClass()) {
            return false;
        }
        CameraConfiguration cameraConfiguration = (CameraConfiguration) obj;
        return this.mColorSpace.equals(cameraConfiguration.mColorSpace) && this.mCameraIndex == cameraConfiguration.mCameraIndex;
    }

    public CameraIndex getCameraIndex() {
        return this.mCameraIndex;
    }

    public CameraColorSpace getColorSpace() {
        return this.mColorSpace;
    }

    public int hashCode() {
        return Objects.hash(this.mColorSpace, this.mCameraIndex);
    }

    public void setCameraIndex(CameraIndex cameraIndex) {
        this.mCameraIndex = cameraIndex;
    }

    public void setColorSpace(CameraColorSpace cameraColorSpace) {
        this.mColorSpace = cameraColorSpace;
    }

    public String toString() {
        StringBuilder sbD = AbstractC19915fE3.d("CameraConfiguration{, mColorSpace=");
        sbD.append(this.mColorSpace);
        sbD.append(", mCameraIndex=");
        sbD.append(this.mCameraIndex);
        sbD.append('}');
        return sbD.toString();
    }
}
