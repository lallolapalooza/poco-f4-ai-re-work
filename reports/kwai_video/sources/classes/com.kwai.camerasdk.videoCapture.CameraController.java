package com.kwai.camerasdk.videoCapture;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.ExifInterface;
import com.kwai.camerasdk.monitor.FrameMonitor;
import com.kwai.camerasdk.stats.StatsHolder;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.camerasdk.videoCapture.cameras.ZoomController;
import com.yxcorp.bugly.CrashReporter;
import p000do.f0;
import p000do.m;
import p000do.n;
import p000do.n1;
import p000do.u;
import p000do.x;

/* JADX INFO: loaded from: classes15.dex */
public abstract class CameraController extends co.c implements ZoomController, com.kwai.camerasdk.videoCapture.cameras.c, com.kwai.camerasdk.videoCapture.cameras.a {

    public interface DataListener {
        void onVideoFrameCaptured(@e.a CameraController cameraController, @e.a VideoFrame videoFrame);
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f23482a;

        static {
            int[] iArr = new int[p000do.i.values().length];
            f23482a = iArr;
            try {
                iArr[p000do.i.kAndroidCameraKit.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "CameraController$1.class", "<clinit>", -1);
            }
            try {
                f23482a[p000do.i.kAndroidCameraUnit.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "CameraController$1.class", "<clinit>", -1);
            }
            try {
                f23482a[p000do.i.kAndroidCameraVivo.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "CameraController$1.class", "<clinit>", -1);
            }
            try {
                f23482a[p000do.i.kAndroidCamera1.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "CameraController$1.class", "<clinit>", -1);
            }
            try {
                f23482a[p000do.i.kAndroidCameraAuto.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "CameraController$1.class", "<clinit>", -1);
            }
            try {
                f23482a[p000do.i.kAndroidCamera2.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "CameraController$1.class", "<clinit>", -1);
            }
        }
    }

    public interface b {
        void a(boolean z12);
    }

    public enum c {
        IdleState,
        OpeningState,
        PreviewState,
        RecordingState,
        CapturingState,
        ClosingState
    }

    public interface d {
        void a(long j12, long j13);

        void b(long j12);
    }

    public interface e {
        void a(f0 f0Var);

        void b(ExifInterface exifInterface);

        void c(n1 n1Var);
    }

    public interface f {
        void n(f0 f0Var, Exception exc);

        void o(CameraController cameraController, c cVar, c cVar2);
    }

    public enum g {
        Off,
        Auto,
        CloudyDaylight,
        Daylight,
        Fluorescent,
        Incandescent,
        Shade,
        Twilight,
        WarmFluorescent
    }

    public static void startAuthenticationRequest(Context context) {
        if (com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.h() || com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.i()) {
            com.kwai.camerasdk.videoCapture.cameras.cameraunit.g.B0(context);
        }
    }

    public static boolean supportApi(p000do.i iVar, Context context) {
        int i12 = a.f23482a[iVar.ordinal()];
        if (i12 == 1) {
            return com.kwai.camerasdk.videoCapture.cameras.camerakit.f.v0(context);
        }
        if (i12 == 2) {
            return com.kwai.camerasdk.videoCapture.cameras.cameraunit.g.C0(context, true);
        }
        if (i12 != 3) {
            return true;
        }
        return com.kwai.camerasdk.videoCapture.cameras.cameravivo.e.n0(context);
    }

    public static boolean supportCaptureDeviceType(n nVar, boolean z12, p000do.i iVar, Context context) {
        boolean z13 = true;
        switch (a.f23482a[iVar.ordinal()]) {
            case 1:
            case 6:
                if (nVar != n.kCaptureDeviceTypeBuiltInWideAngleCamera && (z12 || nVar != n.kCaptureDeviceTypeBuiltInUltraWideCamera)) {
                }
                return z13;
            case 2:
                if (!com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.h() || !com.kwai.camerasdk.videoCapture.cameras.cameraunit.g.D0(nVar, z12, context)) {
                }
                return z13;
            case 3:
                return com.kwai.camerasdk.videoCapture.cameras.cameravivo.e.o0(nVar, z12, context);
            case 4:
            case 5:
                if (nVar != n.kCaptureDeviceTypeBuiltInWideAngleCamera) {
                }
                return z13;
            default:
                return false;
        }
        z13 = false;
        return z13;
    }

    public static boolean supportProSuperStabilization(Context context, p000do.i iVar) {
        if (iVar != p000do.i.kAndroidCameraUnit) {
            return false;
        }
        return com.kwai.camerasdk.videoCapture.cameras.cameraunit.g.E0(context);
    }

    public static boolean supportSuperStabilization(Context context, p000do.i iVar) {
        if (iVar != p000do.i.kAndroidCameraUnit) {
            return false;
        }
        return com.kwai.camerasdk.videoCapture.cameras.cameraunit.g.F0(context);
    }

    public abstract void beginConfiguration();

    public abstract void closeSubCamera();

    public abstract void commitConfiguration();

    public abstract void dispose();

    public abstract void fallbackPictureCaptureConfig(n1 n1Var);

    public abstract Camera getCamera();

    public abstract p000do.i getCameraApiVersion();

    public abstract com.kwai.camerasdk.utils.f getCameraCaptureSize();

    public abstract int getCameraOrientation();

    public abstract n getCaptureDeviceType();

    public abstract u getConfig();

    public abstract u getDaenerysCaptureConfig();

    public abstract boolean getEnableHdr();

    public abstract float getFocalLength();

    public abstract float getHorizontalViewAngle();

    public abstract boolean getLowLightBoostEnabled();

    public abstract long getNativeCameraController();

    public abstract com.kwai.camerasdk.utils.f getPictureSize();

    public abstract com.kwai.camerasdk.utils.f[] getPictureSizes();

    public abstract com.kwai.camerasdk.utils.f getPreviewSize();

    public abstract com.kwai.camerasdk.utils.f[] getPreviewSizes();

    public abstract com.kwai.camerasdk.utils.f[] getRecordingSizes();

    public abstract c getState();

    public abstract x getVideoStabilizationMode();

    public abstract boolean isFrontCamera();

    public abstract void markNextFramesToCapture(long j12, int i12);

    public abstract boolean mirrorFrontCamera();

    public abstract void openSubCamera();

    public abstract void resumePreview();

    public abstract void resumePreview(boolean z12);

    public abstract void setAWBMode(g gVar);

    public abstract void setCameraStreamTypeAndVideoStabilizationMode(m mVar, x xVar, boolean z12);

    public abstract void setCaptureDeviceType(n nVar);

    public abstract void setDisableStabilization(boolean z12);

    public abstract void setEnableHdr(boolean z12);

    public abstract void setEnableLowLightBoost(boolean z12);

    public abstract void setFrameMonitor(FrameMonitor frameMonitor);

    public abstract boolean setLowLightStrategyEnabled(Activity activity, boolean z12);

    public abstract void setMirrorFrontCamera(boolean z12);

    public abstract void setOnCameraInitTimeCallback(d dVar);

    public abstract void setPreviewCallback();

    public abstract void setStats(StatsHolder statsHolder);

    public abstract void setUseYuvOutputForCamera2TakePicture(boolean z12);

    public abstract void setVideoStabilizationMode(x xVar, boolean z12);

    public abstract void setZeroShutterLagIfSupportEnabled(boolean z12);

    public abstract void stopPreview();

    public abstract boolean supportLowLightBoost();

    public abstract boolean supportTakePicture();

    public abstract void switchCamera(boolean z12);

    public abstract void takePicture(e eVar);

    public abstract void takePicture(e eVar, boolean z12);

    public abstract void updateDaenerysCaptureConfig(u uVar);

    public abstract void updateFps(int i12, int i13);

    public abstract void updatePreviewResolution(com.kwai.camerasdk.utils.f fVar);

    public abstract void updatePreviewScale(com.kwai.camerasdk.utils.f fVar);

    public abstract void updateResolutionCaptureConfig(int i12, int i13, int i14);
}
