package com.kwai.camerasdk.face;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.protobuf.InvalidProtocolBufferException;
import com.kwai.camerasdk.KSCameraSDKException;
import com.kwai.camerasdk.utils.SensorUtils;
import com.kwai.camerasdk.utils.e;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.video.editorsdk2.EditorSdk2Utils;
import com.yxcorp.bugly.CrashReporter;
import java.util.ArrayList;
import java.util.List;
import mo.g0;
import mo.h;
import mo.h0;
import mo.i0;
import mo.j0;
import mo.k0;
import mo.l0;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public class FaceDetectorContext {
    private static final String TAG = "FaceDetectorContext";
    private Context context;
    private volatile boolean disposed;
    private boolean faceDetectInitialized;
    private boolean ignoreSensorUpdate;
    private final Object lock;
    private ModelMissingListener modelMissingListener;
    private long nativeContext;
    private long nativeSensorManager;
    private SensorUtils sensorUtils;
    private UploadStatsListener uploadStatsListener;

    public interface ModelMissingListener {
        void onModelMissing(int i12, String str);
    }

    public interface UploadStatsListener {
        void uploadStats(String str);
    }

    class a implements com.kwai.camerasdk.face.a {
        a() {
        }

        @Override // com.kwai.camerasdk.face.a
        public void a(boolean z12) {
            if (FaceDetectorContext.this.disposed) {
                return;
            }
            FaceDetectorContext faceDetectorContext = FaceDetectorContext.this;
            faceDetectorContext.nativeSetEnableAdaptiveMinFaceSize(faceDetectorContext.nativeContext, l0.kVideoDetector.getNumber(), z12);
        }

        @Override // com.kwai.camerasdk.face.a
        public void b(h0 h0Var) {
            if (FaceDetectorContext.this.disposed) {
                return;
            }
            FaceDetectorContext faceDetectorContext = FaceDetectorContext.this;
            faceDetectorContext.nativeSetFaceDetectConfig(faceDetectorContext.nativeContext, l0.kVideoDetector.getNumber(), h0Var.toByteArray());
        }
    }

    static {
        com.kwai.camerasdk.utils.a.b();
        com.kwai.camerasdk.utils.a.a("opencv_world");
        com.kwai.camerasdk.utils.a.a(EditorSdk2Utils.SO_LIBRARY_TYPE_YKIT);
        com.kwai.camerasdk.utils.a.a("facedetectcontext");
    }

    public FaceDetectorContext(Context context, k0 k0Var) {
        this(context, k0Var, i0.kFaceDetectInitAuto);
    }

    private native void nativeClearOverrideFaceDetectMode(long j12);

    private native void nativeDestroy(long j12, long j13);

    private native byte[][] nativeDetectFacesFromFrame(long j12, VideoFrame videoFrame, int i12);

    private native void nativeEnableLoadAssetFunc(Context context, long j12, boolean z12);

    /* JADX INFO: Access modifiers changed from: private */
    public native byte[] nativeGetFaceDetectConfig(long j12, int i12);

    private native int nativeGetType(long j12);

    private native void nativeIgnoreSensorUpdate(long j12, boolean z12);

    private native long nativeInit(long j12, int i12, int i13);

    private native long nativeInitSensorManager();

    private native void nativePrepare(long j12, int i12);

    private native void nativeResetVideoDetector(long j12);

    private native void nativeSetBusinessAndABTestParam(long j12, long j13, String str);

    private native void nativeSetData(long j12, int i12, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetEnableAdaptiveMinFaceSize(long j12, int i12, boolean z12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetFaceDetectConfig(long j12, int i12, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetFaceDetectEnabled(long j12, int i12, boolean z12);

    private native void nativeSetFirstFrameValid(long j12, boolean z12);

    private native void nativeSetOverrideFaceDetectMode(long j12, int i12);

    private native void nativeSetTestDetectMode(long j12, int i12);

    private native void nativeSetType(Context context, long j12, int i12);

    private void onModelMissingCallback(int i12, String str) {
        ModelMissingListener modelMissingListener;
        if (this.disposed || (modelMissingListener = this.modelMissingListener) == null) {
            return;
        }
        modelMissingListener.onModelMissing(i12, str);
    }

    private void onUploadStatsCallback(String str) {
        UploadStatsListener uploadStatsListener;
        if (this.disposed || (uploadStatsListener = this.uploadStatsListener) == null) {
            return;
        }
        uploadStatsListener.uploadStats(str);
    }

    public void clearOverrideFaceDetectMode() {
        if (this.disposed) {
            return;
        }
        nativeClearOverrideFaceDetectMode(this.nativeContext);
    }

    public List<g0> detectFacesFromFrame(VideoFrame videoFrame, l0 l0Var) {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return new ArrayList();
                }
                byte[][] bArrNativeDetectFacesFromFrame = nativeDetectFacesFromFrame(this.nativeContext, videoFrame, l0Var.getNumber());
                ArrayList arrayList = new ArrayList();
                if (bArrNativeDetectFacesFromFrame != null) {
                    try {
                        for (byte[] bArr : bArrNativeDetectFacesFromFrame) {
                            arrayList.add(g0.f(bArr));
                        }
                    } catch (InvalidProtocolBufferException e12) {
                        CrashReporter.logCheckedException(e12, "FaceDetectorContext.class", "detectFacesFromFrame", -99);
                        e12.printStackTrace();
                    }
                }
                return arrayList;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "detectFacesFromFrame", -94);
                throw th2;
            }
        }
    }

    public void dispose() {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return;
                }
                this.disposed = true;
                this.sensorUtils.release();
                nativeDestroy(this.nativeContext, this.nativeSensorManager);
                this.nativeContext = 0L;
                this.nativeSensorManager = 0L;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "dispose", -1);
                throw th2;
            }
        }
    }

    public void enableLoadAssetFunc(boolean z12) {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return;
                }
                if (this.faceDetectInitialized) {
                    return;
                }
                if (com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.f23915h) {
                    nativeEnableLoadAssetFunc(this.context, this.nativeContext, z12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "enableLoadAssetFunc", -1);
                throw th2;
            }
        }
    }

    public Object getLock() {
        return this.lock;
    }

    public long getNativeContext() {
        long j12;
        synchronized (this.lock) {
            try {
                j12 = this.nativeContext;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "getNativeContext", -73);
                throw th2;
            }
        }
        return j12;
    }

    public long getNativeSensorManager() {
        return this.nativeSensorManager;
    }

    public e getSensorController() {
        return this.sensorUtils;
    }

    @Deprecated
    public k0 getType() {
        return k0.kYcnnFaceDetect;
    }

    public com.kwai.camerasdk.face.a getVideoFaceDetector() {
        a aVar;
        synchronized (this.lock) {
            try {
                aVar = new a();
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "getVideoFaceDetector", -8);
                throw th2;
            }
        }
        return aVar;
    }

    public void ignoreSensorUpdate(boolean z12) {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return;
                }
                nativeIgnoreSensorUpdate(this.nativeContext, z12);
                this.ignoreSensorUpdate = z12;
                if (z12) {
                    this.sensorUtils.onPause();
                } else {
                    this.sensorUtils.onResume();
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "ignoreSensorUpdate", -1);
                throw th2;
            }
        }
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public void onPause() {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return;
                }
                this.sensorUtils.onPause();
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "onPause", -1);
                throw th2;
            }
        }
    }

    public void onResume() {
        synchronized (this.lock) {
            try {
                if (!this.disposed && !this.ignoreSensorUpdate) {
                    this.sensorUtils.onResume();
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "onResume", -1);
                throw th2;
            }
        }
    }

    public void prepareImageDetector() {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return;
                }
                nativePrepare(this.nativeContext, l0.kImageDetector.getNumber());
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "prepareImageDetector", -1);
                throw th2;
            }
        }
    }

    public void prepareVideoDetector() {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return;
                }
                nativePrepare(this.nativeContext, l0.kVideoDetector.getNumber());
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "prepareVideoDetector", -1);
                throw th2;
            }
        }
    }

    public void resetVideoDetector() {
        if (this.disposed) {
            return;
        }
        nativeResetVideoDetector(this.nativeContext);
    }

    public void setActivityRequestedOrientation(int i12) {
        if (this.disposed) {
            return;
        }
        this.sensorUtils.setActivityRequestedOrientation(i12);
    }

    public void setBusinessAndABTestParam(h hVar, String str) {
        if (this.disposed) {
            return;
        }
        nativeSetBusinessAndABTestParam(this.nativeContext, hVar.getNumber(), str);
    }

    public void setData(k0 k0Var, String str) throws KSCameraSDKException.InvalidDataException {
        synchronized (this.lock) {
            try {
                if (this.disposed) {
                    return;
                }
                if (this.faceDetectInitialized) {
                    return;
                }
                if (com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.f23915h) {
                    nativeSetData(this.nativeContext, k0Var.getNumber(), str);
                    this.faceDetectInitialized = true;
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceDetectorContext.class", "setData", -1);
                throw th2;
            }
        }
    }

    public void setFirstFrameValid(boolean z12) {
        if (this.disposed) {
            return;
        }
        nativeSetFirstFrameValid(this.nativeContext, z12);
    }

    public void setModelMissingListener(ModelMissingListener modelMissingListener) {
        this.modelMissingListener = modelMissingListener;
    }

    public void setOverrideFaceDetectMode(j0 j0Var) {
        if (this.disposed) {
            return;
        }
        nativeSetOverrideFaceDetectMode(this.nativeContext, j0Var.getNumber());
    }

    public void setTestDetectMode(j0 j0Var) {
        if (this.disposed) {
            return;
        }
        nativeSetTestDetectMode(this.nativeContext, j0Var.getNumber());
    }

    @Deprecated
    public void setType(k0 k0Var) {
    }

    public void setUploadStatsListener(UploadStatsListener uploadStatsListener) {
        this.uploadStatsListener = uploadStatsListener;
    }

    public FaceDetectorContext(Context context, k0 k0Var, i0 i0Var) {
        this(context, k0Var, i0Var, false);
    }

    public FaceDetectorContext(Context context, k0 k0Var, i0 i0Var, boolean z12) {
        this.nativeContext = 0L;
        this.disposed = false;
        this.faceDetectInitialized = false;
        this.ignoreSensorUpdate = false;
        this.lock = new Object();
        this.context = context;
        long jNativeInitSensorManager = nativeInitSensorManager();
        this.nativeSensorManager = jNativeInitSensorManager;
        this.nativeContext = nativeInit(jNativeInitSensorManager, k0Var.getNumber(), i0Var.getNumber());
        this.sensorUtils = new SensorUtils(context, this.nativeSensorManager, z12);
        this.ignoreSensorUpdate = z12;
    }
}
