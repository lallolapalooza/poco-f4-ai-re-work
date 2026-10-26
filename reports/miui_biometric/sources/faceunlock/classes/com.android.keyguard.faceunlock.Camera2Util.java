package com.android.keyguard.faceunlock;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Slog;
import android.view.Surface;
import android.view.TextureView;
import com.miui.face.util.DeviceUtils;
import com.miui.face.util.MiSightCompat;
import com.miui.face.util.QseeVendorTag;
import com.miui.face.util.VendorTag;
import com.miui.face.util.VendorTagHelper;
import com.miui.image.Yuv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class Camera2Util extends AbstractCameraUtil {
    public static final int OPERATING_MODE = 32774;
    private CameraCharacteristics cameraCharacteristics;
    private boolean isCameraInUse;
    private CameraDevice mCameraDevice;
    private final Handler mCameraHandler;
    private CameraCaptureSession mCaptureSession;
    private final Context mContext;
    private IPreviewCallback mPreviewCallback;
    private ImageReader mPreviewImageReader;
    private CaptureRequest.Builder requestBuilder;
    private final TextureView tempView;
    public String mCameraId = "-1";
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() { // from class: com.android.keyguard.faceunlock.Camera2Util.1
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            Camera2Util.this.mCameraDevice = cameraDevice;
            try {
                Camera2Util camera2Util = Camera2Util.this;
                camera2Util.requestBuilder = camera2Util.mCameraDevice.createCaptureRequest(1);
                Camera2Util.this.configRequestBuilder(new VendorTagHelper(Camera2Util.this.cameraCharacteristics, Camera2Util.this.requestBuilder));
                Camera2Util.this.mPreviewCallback.openCameraSucced(Camera2Util.this.mCameraId, 0);
            } catch (CameraAccessException e) {
                Slog.w("miui.face.transplant", "setRepeatingRequest fail, perhaps as expected");
                if (e.getMessage().contains("CAMERA_DISCONNECTED")) {
                    return;
                }
                Slog.e("miui.face.transplant", "setRepeatingRequest fail", e);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            Slog.i("miui.face.transplant", "CameraDevice onDisconnected");
            Camera2Util.this.closeCameraImmediately();
            Camera2Util.this.mPreviewCallback.openCameraSucced(Camera2Util.this.mCameraId, Camera2Util.this.isCameraInUse ? 1 : -3);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            Slog.i("miui.face.transplant", "An error was received while the camera was on. errorCode: " + i);
            Camera2Util.this.closeCameraImmediately();
            if (i == 1) {
                Camera2Util.this.mPreviewCallback.openCameraSucced(Camera2Util.this.mCameraId, 1);
            } else if (i == 2) {
                Camera2Util.this.mPreviewCallback.openCameraSucced(Camera2Util.this.mCameraId, 2);
            } else {
                Slog.e("miui.face.transplant", "unexpected error was received while the camera was on. errorCode: " + i);
                Camera2Util.this.mPreviewCallback.openCameraSucced(Camera2Util.this.mCameraId, -2);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            super.onClosed(cameraDevice);
            Slog.i("miui.face.transplant", "CameraDevice onClosed");
        }
    };
    CameraCaptureSession.StateCallback mCameraCaptureSessionCallback = new CameraCaptureSession.StateCallback() { // from class: com.android.keyguard.faceunlock.Camera2Util.2
        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            if (Camera2Util.this.mCameraDevice == null) {
                return;
            }
            Camera2Util.this.mCaptureSession = cameraCaptureSession;
            try {
                Camera2Util.this.mCaptureSession.setRepeatingRequest(Camera2Util.this.requestBuilder.build(), null, Camera2Util.this.mCameraHandler);
            } catch (CameraAccessException e) {
                Slog.e("miui.face.transplant", "setRepeatingRequest fail", e);
                Camera2Util.this.mPreviewCallback.openCameraSucced(Camera2Util.this.mCameraId, -2);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Slog.e("miui.face.transplant", "configure CameraCaptureSession fail");
        }
    };
    private CameraManager mCameraManager = null;

    @Override // com.android.keyguard.faceunlock.AbstractCameraUtil
    public void setCameraCallback(IPreviewCallback iPreviewCallback) {
        this.mPreviewCallback = iPreviewCallback;
    }

    public Camera2Util(Context context, Handler handler) {
        this.mContext = context;
        this.mCameraHandler = handler;
        TextureView textureView = new TextureView(context);
        this.tempView = textureView;
        textureView.setSurfaceTexture(new SurfaceTexture(0));
    }

    public void runOnCameraWorkerThread(Runnable runnable) {
        Handler handler = this.mCameraHandler;
        if (handler == null || runnable == null) {
            return;
        }
        handler.post(runnable);
    }

    @Override // com.android.keyguard.faceunlock.AbstractCameraUtil
    public void openCamera(int i) {
        runOnCameraWorkerThread(new Runnable() { // from class: com.android.keyguard.faceunlock.Camera2Util$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m25lambda$openCamera$0$comandroidkeyguardfaceunlockCamera2Util();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$openCamera$0$com-android-keyguard-faceunlock-Camera2Util, reason: not valid java name */
    /* synthetic */ void m25lambda$openCamera$0$comandroidkeyguardfaceunlockCamera2Util() {
        try {
            this.isCameraInUse = false;
            String cameraId = getCameraId();
            this.mCameraId = cameraId;
            if (!"-1".equals(cameraId)) {
                getCameraManager().openCamera(this.mCameraId, this.stateCallback, this.mCameraHandler);
                this.cameraCharacteristics = getCameraManager().getCameraCharacteristics(this.mCameraId);
            } else {
                Slog.w("miui.face.transplant", "open camera2 faild");
                MiSightCompat.sendEvent(904004001, "result", "can not get cameraId");
                this.mPreviewCallback.openCameraSucced(this.mCameraId, -1);
            }
        } catch (Exception e) {
            Slog.i("miui.face.transplant", "open camera fail, because an exception occurred");
            if (!e.getMessage().contains("CAMERA_IN_USE") && !e.getMessage().contains("MAX_CAMERAS_IN_USE")) {
                Slog.e("miui.face.transplant", "open camera fail, because an exception occurred", e);
            }
            MiSightCompat.sendEvent(904004001, "result", "an exception occurred");
            this.isCameraInUse = true;
        }
    }

    @Override // com.android.keyguard.faceunlock.AbstractCameraUtil
    public String getCameraId() {
        try {
            this.mCameraId = getFrontFacingCameraId();
        } catch (CameraAccessException e) {
            Slog.e("miui.face.transplant", "can not get cameraId", e);
        }
        return this.mCameraId;
    }

    @Override // com.android.keyguard.faceunlock.AbstractCameraUtil
    public void startPreview(Surface surface) {
        boolean z;
        if (this.mCameraDevice == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (surface != null) {
            this.requestBuilder.addTarget(surface);
            arrayList.add(surface);
            z = false;
        } else {
            if (DeviceUtils.isNeedMoreStreamInAuth()) {
                Surface surface2 = new Surface(this.tempView.getSurfaceTexture());
                this.requestBuilder.addTarget(surface2);
                arrayList.add(surface2);
            }
            z = true;
        }
        try {
            ImageReader imageReaderNewInstance = ImageReader.newInstance(this.width, this.height, 35, 1);
            this.mPreviewImageReader = imageReaderNewInstance;
            imageReaderNewInstance.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: com.android.keyguard.faceunlock.Camera2Util$$ExternalSyntheticLambda2
                @Override // android.media.ImageReader.OnImageAvailableListener
                public final void onImageAvailable(ImageReader imageReader) {
                    this.f$0.m26x64ce4d8c(imageReader);
                }
            }, this.mCameraHandler);
            this.requestBuilder.addTarget(this.mPreviewImageReader.getSurface());
            arrayList.add(this.mPreviewImageReader.getSurface());
            createCaptureSession(arrayList, z);
        } catch (Exception e) {
            Log.w("miui.face.transplant", "createCaptureSession fail", e);
            closeCamera();
        }
    }

    /* JADX INFO: renamed from: lambda$startPreview$1$com-android-keyguard-faceunlock-Camera2Util, reason: not valid java name */
    /* synthetic */ void m26x64ce4d8c(ImageReader imageReader) {
        Image imageAcquireNextImage = imageReader.acquireNextImage();
        if (imageAcquireNextImage == null) {
            return;
        }
        byte[] nV21Bytes = new Yuv(imageAcquireNextImage).getNV21Bytes();
        imageAcquireNextImage.close();
        this.mPreviewCallback.onPreviewFrame(nV21Bytes, this.mCameraId);
    }

    @Override // com.android.keyguard.faceunlock.AbstractCameraUtil
    public void closeCamera() {
        runOnCameraWorkerThread(new Camera2Util$$ExternalSyntheticLambda0(this));
    }

    @Override // com.android.keyguard.faceunlock.AbstractCameraUtil
    public void closeCameraImmediately() {
        ImageReader imageReader = this.mPreviewImageReader;
        if (imageReader != null) {
            try {
                imageReader.close();
            } catch (Exception e) {
                Log.d("miui.face.transplant", "Ignore exception", e);
            }
            this.mPreviewImageReader = null;
        }
        CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
        if (cameraCaptureSession != null) {
            try {
                cameraCaptureSession.close();
            } catch (Exception e2) {
                Log.d("miui.face.transplant", "Ignore exception", e2);
            }
            this.mCaptureSession = null;
        }
        CameraDevice cameraDevice = this.mCameraDevice;
        if (cameraDevice != null) {
            try {
                cameraDevice.close();
            } catch (Exception e3) {
                Log.d("miui.face.transplant", "Ignore exception", e3);
            }
            this.mCameraDevice = null;
        }
        Slog.i("miui.face.transplant", "Camera2Util, close camera");
    }

    @Override // com.android.keyguard.faceunlock.AbstractCameraUtil
    public void closeCameraImmediatelyIfCameraThread() {
        if (this.mCameraHandler.getLooper().isCurrentThread()) {
            closeCameraImmediately();
        } else {
            runOnCameraWorkerThread(new Camera2Util$$ExternalSyntheticLambda0(this));
        }
    }

    private String getFrontFacingCameraId() throws CameraAccessException {
        for (String str : getCameraManager().getCameraIdList()) {
            int iIntValue = ((Integer) Objects.requireNonNull((Integer) getCameraManager().getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING))).intValue();
            Log.i("miui.face.transplant", "camera id: " + str + ", LENS_FACING: " + iIntValue);
            if (iIntValue == 0) {
                return str;
            }
        }
        return "-1";
    }

    private CameraManager getCameraManager() {
        if (this.mCameraManager == null) {
            this.mCameraManager = (CameraManager) this.mContext.getSystemService(CameraManager.class);
        }
        return this.mCameraManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configRequestBuilder(VendorTagHelper vendorTagHelper) {
        vendorTagHelper.setRequestKey((CaptureRequest.Key<int>) CaptureRequest.CONTROL_AF_MODE, 4);
        if (DeviceUtils.isVirtualLightDevice()) {
            vendorTagHelper.trySetRequestKey((VendorTag<int>) QseeVendorTag.EXPOSURE_METERING_MODE, 0);
        }
    }

    private void createCaptureSession(List<Surface> list, boolean z) throws CameraAccessException {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Surface> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new OutputConfiguration(it.next()));
        }
        int i = (z && DeviceUtils.isNeedOperatingMode()) ? OPERATING_MODE : 0;
        final Handler handler = new Handler(Looper.myLooper());
        SessionConfiguration sessionConfiguration = new SessionConfiguration(i, arrayList, new Executor() { // from class: com.android.keyguard.faceunlock.Camera2Util$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, this.mCameraCaptureSessionCallback);
        sessionConfiguration.setSessionParameters(this.requestBuilder.build());
        this.mCameraDevice.createCaptureSession(sessionConfiguration);
    }
}
