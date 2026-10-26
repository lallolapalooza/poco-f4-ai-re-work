package com.looksery.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.looksery.sdk.facedetector.ExternalFaceDetectorFactory;
import com.looksery.sdk.io.LensCoreResources;
import defpackage.AbstractC19915fE3;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class FaceTrackerWrapper {
    private static final String TAG = "com.looksery.sdk.FaceTrackerWrapper";
    private final Runnable mReleaser;
    private final SafeNativeBridge mSafeNativeBridge;

    public static class Configuration {
        private static final String CORE_ASSETS_PATH = "asset:looksery/core";
        private final String cachePath;
        private final String coreResourcesPath;
        private final ExternalFaceDetectorFactory externalFaceDetectorFactory;
        private final LandmarksType landmarksType;
        private final Mode mode;

        public static class Builder {
            private String cachePath;
            private String coreResourcesPath;
            private ExternalFaceDetectorFactory externalFaceDetectorFactory;
            private LandmarksType landmarksType;
            private Mode mode;

            public Builder(Context context) {
                setCoreResourcesPath("asset:looksery/core");
                setCachePath(new File(context.getCacheDir(), "looksery_cache"));
            }

            public Configuration build() {
                return new Configuration(this);
            }

            public String getCachePath() {
                return this.cachePath;
            }

            public String getCoreResourcesPath() {
                return this.coreResourcesPath;
            }

            public ExternalFaceDetectorFactory getExternalFaceDetectorFactory() {
                return this.externalFaceDetectorFactory;
            }

            public LandmarksType getLandmarksType() {
                return this.landmarksType;
            }

            public Mode getMode() {
                return this.mode;
            }

            public Builder setCachePath(File file) {
                StringBuilder sbD = AbstractC19915fE3.d("file:");
                sbD.append(file.getAbsolutePath());
                this.cachePath = sbD.toString();
                return this;
            }

            public Builder setCoreResourcesPath(String str) {
                this.coreResourcesPath = str;
                return this;
            }

            public Builder setExternalFaceDetectorFactory(ExternalFaceDetectorFactory externalFaceDetectorFactory) {
                this.externalFaceDetectorFactory = externalFaceDetectorFactory;
                return this;
            }

            public Builder setLandmarksType(LandmarksType landmarksType) {
                this.landmarksType = landmarksType;
                return this;
            }

            public Builder setMode(Mode mode) {
                this.mode = mode;
                return this;
            }
        }

        public Configuration(Builder builder) {
            this.coreResourcesPath = builder.getCoreResourcesPath();
            this.cachePath = builder.getCachePath();
            this.landmarksType = builder.getLandmarksType();
            this.mode = builder.getMode();
            this.externalFaceDetectorFactory = builder.getExternalFaceDetectorFactory();
        }

        public static Builder newBuilder(Context context) {
            return new Builder(context);
        }

        public String getCachePath() {
            return this.cachePath;
        }

        public String getCoreResourcesPath() {
            return this.coreResourcesPath;
        }

        public ExternalFaceDetectorFactory getExternalFaceDetectorFactory() {
            return this.externalFaceDetectorFactory;
        }

        public LandmarksType getLandmarksType() {
            return this.landmarksType;
        }

        public Mode getMode() {
            return this.mode;
        }
    }

    public static class FaceInfo {
        private final float[] mFlatLandmarks;

        private FaceInfo(float[] fArr) {
            if (fArr.length % 2 != 0) {
                throw new IllegalArgumentException("Backing array must be of even item count");
            }
            this.mFlatLandmarks = fArr;
        }

        public float getLandmarkX(int i) {
            return this.mFlatLandmarks[i * 2];
        }

        public float getLandmarkY(int i) {
            return this.mFlatLandmarks[(i * 2) + 1];
        }

        public int landmarksCount() {
            return this.mFlatLandmarks.length / 2;
        }
    }

    public enum LandmarksType {
        Landmarks68,
        LandmarksWithEyes
    }

    public enum Mode {
        FAST,
        PRECISE
    }

    public FaceTrackerWrapper(Context context, Configuration configuration) {
        Runnable runnable = new Runnable() { // from class: com.looksery.sdk.FaceTrackerWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                FaceTrackerWrapper faceTrackerWrapper = FaceTrackerWrapper.this;
                faceTrackerWrapper.nativeRelease(faceTrackerWrapper.getNativeHandle());
            }
        };
        this.mReleaser = runnable;
        LSNativeLibraryLoader.ensureAllAreLoaded();
        LensCoreResources.initializeOnce(context);
        setResourcePath(configuration);
        this.mSafeNativeBridge = new SafeNativeBridge(nativeInit(configuration.getMode(), configuration.getLandmarksType(), configuration.getExternalFaceDetectorFactory()), runnable);
    }

    private void createDirectories(String str) {
        Uri uri = Uri.parse(str);
        if ("file".equals(uri.getScheme())) {
            File file = new File(uri.getPath());
            if (file.exists()) {
                if (file.isDirectory()) {
                    return;
                }
            } else if (file.mkdirs()) {
                return;
            }
            file.getAbsolutePath();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getNativeHandle() {
        return this.mSafeNativeBridge.getNativeHandle();
    }

    private native long nativeInit(Mode mode, LandmarksType landmarksType, ExternalFaceDetectorFactory externalFaceDetectorFactory);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeRelease(long j);

    private native void nativeRestartTracking(long j);

    private native void nativeSetMinFacePixelHeight(long j, int i);

    private native void nativeSetNumberOfFacesToTrack(long j, int i);

    private native void nativeSetResourcePath(String str, String str2);

    private native boolean nativeSetTrackingResourcesPath(long j, String str);

    private native float[][] nativeTrackImage(long j, Bitmap bitmap);

    private void setResourcePath(Configuration configuration) {
        createDirectories(configuration.getCachePath());
        nativeSetResourcePath(configuration.getCoreResourcesPath(), configuration.getCachePath());
    }

    public void release() {
        this.mSafeNativeBridge.release();
    }

    public void restartTracking() {
        nativeRestartTracking(getNativeHandle());
    }

    public void setMinFacePixelHeight(int i) {
        nativeSetMinFacePixelHeight(getNativeHandle(), i);
    }

    public void setNumberOfFacesToTrack(int i) {
        nativeSetNumberOfFacesToTrack(getNativeHandle(), i);
    }

    public boolean setTrackingResourcesPath(String str) {
        return nativeSetTrackingResourcesPath(getNativeHandle(), str);
    }

    public FaceInfo[] trackImage(Bitmap bitmap) {
        float[][] fArrNativeTrackImage = nativeTrackImage(getNativeHandle(), bitmap);
        int length = fArrNativeTrackImage.length;
        FaceInfo[] faceInfoArr = new FaceInfo[length];
        for (int i = 0; i < length; i++) {
            faceInfoArr[i] = new FaceInfo(fArrNativeTrackImage[i]);
        }
        return faceInfoArr;
    }
}
