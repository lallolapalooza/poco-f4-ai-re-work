package com.kwai.camerasdk.videoCapture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.view.WindowManager;
import androidx.annotation.Keep;
import com.kwai.camerasdk.DaenerysConfigBuilder;
import com.kwai.camerasdk.KSCameraSDKException;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.mediarecorder.RecordingStatesListener;
import com.kwai.camerasdk.monitor.FrameMonitor;
import com.kwai.camerasdk.render.OpengGL.EglBase;
import com.kwai.camerasdk.stats.StatsHolder;
import com.kwai.camerasdk.utils.RetryStartPreviewHelper;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.camerasdk.videoCapture.cameras.ZoomController;
import com.yxcorp.bugly.CrashReporter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import p000do.n1;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public class CameraControllerImpl extends CameraController implements RecordingStatesListener {
    private static final int FPS_MIN_THRESHOLD = 20;
    private static final boolean SYNC_OPEN_CLOSE_CAMERA = false;
    private static final String TAG = "CameraControllerImpl";
    private int adaptedFrameRate;
    private p000do.i cameraApiVersion;
    private com.kwai.camerasdk.utils.f cameraCaptureSize;
    private CameraSession.CameraDataListener cameraDataListener;
    private int cameraOrientation;
    private CameraController.b cameraPositionChangeCallback;
    private final Handler cameraThreadHandler;
    private p000do.u captureConfig;
    private p000do.u config;
    private final Context context;
    private Activity currentActivity;
    private CameraSession currentSession;
    private CameraController.DataListener dataListener;
    private float defaultAECompensation;
    private float defaultScreenBrightness;
    private boolean disableStabilization;
    private volatile boolean disposed;
    private p000do.v edgeMode;
    private EglBase.Context eglContext;
    private boolean enableDumpDepth;
    private boolean enableFaceDetectAutoExposure;
    private boolean enableHdr;
    private boolean enableLowlightBoost;
    private boolean enableSimulateMultiCamera;
    private float focalLength;
    private WeakReference<FrameMonitor> frameMonitorWeakReference;
    private float horizontalViewAngle;
    private boolean isPictureHdrSupported;
    private boolean isPreviewHdrSupported;
    private RetryStartPreviewHelper mRetryStartPreviewHelper;
    private int maxPreviewFps;
    private boolean mirrorFrontCamera;
    private long nativeCameraController;
    private CameraController.d onCameraInitTimeCallback;
    private volatile boolean openSubCamera;
    private com.kwai.camerasdk.utils.f pictureCropSize;
    private com.kwai.camerasdk.utils.f[] pictureSizes;
    private com.kwai.camerasdk.utils.f previewCropSize;
    private com.kwai.camerasdk.utils.f[] previewSizes;
    private boolean reOpenedCamera2v1;
    private boolean recordingHintEnabledForBackCamera;
    private boolean recordingHintEnabledForFrontCamera;
    private com.kwai.camerasdk.utils.f[] recordingSizes;
    private com.kwai.camerasdk.videoCapture.cameras.b resolutionRequest;
    private com.kwai.camerasdk.videoCapture.f stateHolder;
    private WeakReference<StatsHolder> stats;
    private int targetFps;
    private int targetMinFps;
    private boolean useFrontCamera;
    private volatile boolean waitForCloseSession;
    private boolean zeroShutterLagIfSupportEnabled;
    private static final String[] VIDEO_PERMISSIONS = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    private static int sCameraNumbers = 0;
    private static volatile boolean disableSetAdaptedCameraFps = false;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.getCameraSessionExtend() == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.getCameraSessionExtend().M();
        }
    }

    class a0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24075a;

        a0(boolean z12) {
            this.f24075a = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.j(!this.f24075a);
            if (CameraControllerImpl.this.currentSession == null) {
                Log.e(CameraControllerImpl.TAG, "enableVideoStabilizationIfSupport error, currentSession == null");
                return;
            }
            Log.i(CameraControllerImpl.TAG, "setCaptureStabilizationMode getVideoStabilizationType = " + CameraControllerImpl.this.currentSession.s());
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.setVideoStabilizationType(CameraControllerImpl.this.currentSession.s());
            }
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class b implements CameraSession.CameraDataListener {
        b() {
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraSession.CameraDataListener
        public void onReportCameraFunctionFailed(p000do.f0 f0Var, int i12) {
            StatsHolder statsHolder;
            if (CameraControllerImpl.this.stats == null || (statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get()) == null) {
                return;
            }
            statsHolder.reportCameraFunctionFailed(f0Var, i12);
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraSession.CameraDataListener
        public void onVideoFrameCaptured(@e.a CameraSession cameraSession, @e.a VideoFrame videoFrame) {
            Runnable runnable;
            if (CameraControllerImpl.this.disposed) {
                return;
            }
            if (cameraSession != CameraControllerImpl.this.currentSession) {
                Runnable runnable2 = videoFrame.textureNotUsedRunnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
                Log.d(CameraControllerImpl.TAG, "on byte buffer frame from another session.");
                return;
            }
            if (!CameraControllerImpl.this.hasSinks() && (runnable = videoFrame.textureNotUsedRunnable) != null) {
                runnable.run();
            }
            FrameMonitor frameMonitor = (FrameMonitor) CameraControllerImpl.this.frameMonitorWeakReference.get();
            if (frameMonitor != null) {
                frameMonitor.d(p000do.o0.kCameraFrameProcessThread, videoFrame.timestamp);
            }
            if (CameraControllerImpl.this.dataListener != null) {
                CameraControllerImpl.this.dataListener.onVideoFrameCaptured(CameraControllerImpl.this, videoFrame);
                return;
            }
            CameraControllerImpl.this.publishMediaFrame(videoFrame);
            if (!CameraControllerImpl.this.enableSimulateMultiCamera || CameraControllerImpl.this.cameraApiVersion == p000do.i.kAndroidCameraUnit) {
                return;
            }
            videoFrame.attributes.s(1);
            CameraControllerImpl.this.publishMediaFrame(videoFrame);
        }
    }

    class b0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p000do.m f24078a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ p000do.x f24079b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f24080c;

        b0(p000do.m mVar, p000do.x xVar, boolean z12) {
            this.f24078a = mVar;
            this.f24079b = xVar;
            this.f24080c = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession != null) {
                if (CameraControllerImpl.this.disableStabilization || CameraControllerImpl.this.stateHolder.d()) {
                    CameraControllerImpl.this.currentSession.c(this.f24078a, this.f24079b, this.f24080c);
                    if (CameraControllerImpl.this.currentSession == null) {
                        Log.e(CameraControllerImpl.TAG, "setVideoStabilizationMode error, currentSession == null");
                        return;
                    }
                    StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
                    if (statsHolder != null) {
                        statsHolder.setVideoStabilizationType(CameraControllerImpl.this.currentSession.s());
                    }
                    CameraControllerImpl.this.stateHolder.k();
                }
            }
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f24082a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f24083b;

        c(int i12, int i13) {
            this.f24082a = i12;
            this.f24083b = i13;
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraControllerImpl.this.targetMinFps = this.f24082a;
            CameraControllerImpl.this.targetFps = this.f24083b;
            CameraControllerImpl.this.adaptedFrameRate = this.f24083b;
            CameraControllerImpl cameraControllerImpl = CameraControllerImpl.this;
            cameraControllerImpl.nativeUpdateTargetFps(cameraControllerImpl.nativeCameraController, this.f24083b);
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d() || CameraControllerImpl.this.currentSession.y(this.f24082a, this.f24083b)) {
                return;
            }
            Log.e(CameraControllerImpl.TAG, "updateFps error");
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.reportCameraFunctionFailed(p000do.f0.CAMERA_SET_ADAPTED_CAMERA_FPS_FAILED, this.f24083b);
            }
        }
    }

    class c0 implements Runnable {
        c0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.H(CameraControllerImpl.this.zeroShutterLagIfSupportEnabled);
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f24086a;

        d(int i12) {
            this.f24086a = i12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f24086a < CameraControllerImpl.this.targetMinFps || this.f24086a > CameraControllerImpl.this.targetFps || 20 > CameraControllerImpl.this.targetFps || CameraControllerImpl.this.adaptedFrameRate == Math.max(this.f24086a, 20)) {
                return;
            }
            CameraControllerImpl.this.adaptedFrameRate = Math.max(this.f24086a, 20);
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d() || CameraControllerImpl.this.currentSession.l(CameraControllerImpl.this.config.D0(), CameraControllerImpl.this.adaptedFrameRate)) {
                return;
            }
            boolean unused = CameraControllerImpl.disableSetAdaptedCameraFps = true;
            Log.e(CameraControllerImpl.TAG, "setAdaptedCameraFps error");
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.reportCameraFunctionFailed(p000do.f0.CAMERA_SET_ADAPTED_CAMERA_FPS_FAILED, this.f24086a);
            }
        }
    }

    class d0 implements Runnable {
        d0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.m(CameraControllerImpl.this.mirrorFrontCamera());
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.disposed) {
                return;
            }
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                CameraControllerImpl.this.mRetryStartPreviewHelper.t();
            }
        }
    }

    class e0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.kwai.camerasdk.utils.f f24090a;

        e0(com.kwai.camerasdk.utils.f fVar) {
            this.f24090a = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!CameraControllerImpl.this.stateHolder.i()) {
                Log.i(CameraControllerImpl.TAG, "updatePreviewResolution states is " + CameraControllerImpl.this.stateHolder.b());
                return;
            }
            if (this.f24090a == null) {
                Log.i(CameraControllerImpl.TAG, "updatePreviewResolution size is null");
            } else {
                Log.i(CameraControllerImpl.TAG, "updatePreviewResolution size width = " + this.f24090a.d() + ", height = " + this.f24090a.c());
            }
            CameraControllerImpl.this.currentSession.E(this.f24090a);
            CameraControllerImpl.this.resetSize();
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i(CameraControllerImpl.TAG, "startPreviewImpl in cameraThreadHandler");
            if (CameraControllerImpl.this.stateHolder.f()) {
                CameraControllerImpl.this.updateStatsCameraStatus();
                StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
                if (statsHolder != null) {
                    statsHolder.startPreview();
                }
                CameraControllerImpl.this.openCameraSession();
                return;
            }
            if (!CameraControllerImpl.this.stateHolder.h()) {
                Log.w(CameraControllerImpl.TAG, "Invalid camera state change.");
            } else {
                CameraControllerImpl.this.waitForCloseSession = false;
                Log.d(CameraControllerImpl.TAG, "waitForCloseSession = false");
            }
        }
    }

    class f0 implements Runnable {
        f0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.stateHolder.i()) {
                CameraControllerImpl.this.currentSession.J(CameraControllerImpl.this.config.y0(), CameraControllerImpl.this.config.v0(), CameraControllerImpl.this.config.w0());
                if (CameraControllerImpl.this.currentSession != null) {
                    CameraControllerImpl.this.resetSize();
                }
            }
        }
    }

    class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24094a;

        g(boolean z12) {
            this.f24094a = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                CameraControllerImpl.this.useFrontCamera = this.f24094a;
                CameraControllerImpl.this.mRetryStartPreviewHelper.t();
            }
        }
    }

    class g0 implements CameraController.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CameraController.f f24096a;

        g0(CameraController.f fVar) {
            this.f24096a = fVar;
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraController.f
        public void n(p000do.f0 f0Var, Exception exc) {
            CameraControllerImpl.this.mRetryStartPreviewHelper.p(f0Var, exc);
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraController.f
        public void o(CameraController cameraController, CameraController.c cVar, CameraController.c cVar2) {
            CameraController.f fVar = this.f24096a;
            if (fVar != null) {
                fVar.o(CameraControllerImpl.this, cVar, cVar2);
            }
        }
    }

    class h implements Runnable {
        h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraControllerImpl.this.mRetryStartPreviewHelper.r();
            Log.i(CameraControllerImpl.TAG, "stop preview. in cameraThreadHandler");
            if (CameraControllerImpl.this.stateHolder.f()) {
                return;
            }
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.stopPreview();
            }
            CameraControllerImpl.this.closeCameraSession();
        }
    }

    class h0 implements Runnable {
        h0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession != null) {
                CameraControllerImpl.this.currentSession.t(CameraControllerImpl.this.config.W(), CameraControllerImpl.this.config.V(), CameraControllerImpl.this.config.H0());
            }
        }
    }

    class i implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CameraController.e f24100a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f24101b;

        i(CameraController.e eVar, boolean z12) {
            this.f24100a = eVar;
            this.f24101b = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.o(this.f24100a, this.f24101b);
            CameraControllerImpl.this.stateHolder.l();
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class i0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24103a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n1 f24104b;

        i0(boolean z12, n1 n1Var) {
            this.f24103a = z12;
            this.f24104b = n1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            n1 n1Var;
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (!this.f24103a || statsHolder == null || (n1Var = this.f24104b) == null) {
                return;
            }
            statsHolder.reportTakePictureStats(n1Var);
        }
    }

    class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f24106a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f24107b;

        j(long j12, int i12) {
            this.f24106a = j12;
            this.f24107b = i12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession != null) {
                CameraControllerImpl.this.currentSession.x(this.f24106a, this.f24107b);
            }
        }
    }

    class j0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24109a;

        j0(boolean z12) {
            this.f24109a = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.config.H0() != this.f24109a) {
                CameraControllerImpl cameraControllerImpl = CameraControllerImpl.this;
                cameraControllerImpl.config = cameraControllerImpl.config.toBuilder().R(this.f24109a).build();
                if (CameraControllerImpl.this.currentSession != null) {
                    CameraControllerImpl.this.currentSession.d(this.f24109a);
                }
            }
        }
    }

    class k implements Runnable {
        k(CameraControllerImpl cameraControllerImpl) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Process.setThreadPriority(1);
        }
    }

    static /* synthetic */ class k0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f24111a;

        static {
            int[] iArr = new int[p000do.y.values().length];
            f24111a = iArr;
            try {
                iArr[p000do.y.kStabilizationTypeSystemEIS.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "CameraControllerImpl$43.class", "<clinit>", -1);
            }
            try {
                f24111a[p000do.y.kStabilizationTypeVendorEIS.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "CameraControllerImpl$43.class", "<clinit>", -1);
            }
            try {
                f24111a[p000do.y.kStabilizationTypeSystemOIS.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "CameraControllerImpl$43.class", "<clinit>", -1);
            }
            try {
                f24111a[p000do.y.kStabilizationTypeVendorOIS.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "CameraControllerImpl$43.class", "<clinit>", -1);
            }
            try {
                f24111a[p000do.y.kStabilizationTypeVendorSuperEIS.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "CameraControllerImpl$43.class", "<clinit>", -1);
            }
        }
    }

    class l implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24112a;

        l(boolean z12) {
            this.f24112a = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraControllerImpl.this.enableHdr = this.f24112a;
            if (CameraControllerImpl.this.currentSession != null) {
                CameraControllerImpl.this.currentSession.v(CameraControllerImpl.this.getEnableHdr());
            }
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.setEnableHdr(CameraControllerImpl.this.getEnableHdr());
            }
        }
    }

    class l0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p000do.u f24114a;

        l0(p000do.u uVar) {
            this.f24114a = uVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.stateHolder.f()) {
                CameraControllerImpl.this.initWithDaenerysCaptureConfig(this.f24114a);
            } else {
                Log.e(CameraControllerImpl.TAG, "updateDaenerysCaptureConfig stateHolder is not Idle");
            }
        }
    }

    class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p000do.n f24116a;

        m(p000do.n nVar) {
            this.f24116a = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            if ((!CameraControllerImpl.this.useFrontCamera && CameraControllerImpl.this.config.k0()) || CameraController.supportCaptureDeviceType(this.f24116a, CameraControllerImpl.this.useFrontCamera, CameraControllerImpl.this.cameraApiVersion, CameraControllerImpl.this.context)) {
                CameraControllerImpl.this.currentSession.r(this.f24116a);
            }
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.setZoom(CameraControllerImpl.this.getZoom());
            }
        }
    }

    class m0 implements Runnable {
        m0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraControllerImpl.this.updateStatsCameraStatus();
        }
    }

    class n implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24119a;

        n(boolean z12) {
            this.f24119a = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.getNumberOfCameras(CameraControllerImpl.this.context, CameraControllerImpl.this.cameraApiVersion) <= 1) {
                Log.w(CameraControllerImpl.TAG, "This device only has one camera!");
                return;
            }
            if (CameraControllerImpl.this.stateHolder.f() || CameraControllerImpl.this.stateHolder.e() || CameraControllerImpl.this.stateHolder.h()) {
                Log.w(CameraControllerImpl.TAG, "Invalid state to call switch camera. state: " + CameraControllerImpl.this.stateHolder.b());
                return;
            }
            boolean z12 = CameraControllerImpl.this.useFrontCamera;
            boolean z13 = this.f24119a;
            if (z12 == z13) {
                return;
            }
            CameraControllerImpl.this.useFrontCamera = z13;
            CameraControllerImpl.this.updateStatsCameraStatus();
            if (!CameraControllerImpl.this.openSubCamera) {
                CameraControllerImpl.this.openCameraSession();
            } else if (CameraControllerImpl.this.getCameraSessionExtend() != null && CameraControllerImpl.this.getCameraSessionExtend().N()) {
                CameraControllerImpl.this.getCameraSessionExtend().switchCamera(this.f24119a);
                if (CameraControllerImpl.this.cameraPositionChangeCallback != null) {
                    CameraControllerImpl.this.cameraPositionChangeCallback.a(this.f24119a);
                }
            }
            Log.i(CameraControllerImpl.TAG, "switchCamera end");
        }
    }

    class n0 implements Runnable {
        n0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.getCameraSessionExtend() != null) {
                CameraControllerImpl.this.getCameraSessionExtend().A();
            } else if (CameraControllerImpl.this.stateHolder.f()) {
                CameraControllerImpl.this.mRetryStartPreviewHelper.t();
            }
        }
    }

    class o implements Runnable {
        o() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.O().setAFAEAutoMode(CameraControllerImpl.this.enableFaceDetectAutoExposure && CameraControllerImpl.this.isFrontCamera());
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class o0 implements Runnable {
        o0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.getCameraSessionExtend() != null) {
                CameraControllerImpl.this.getCameraSessionExtend().b();
            }
        }
    }

    class p implements Runnable {
        p() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.O().setAFAETapMode();
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class p0 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24125a;

        p0(boolean z12) {
            this.f24125a = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraControllerImpl.this.enableLowlightBoost = this.f24125a;
            if (CameraControllerImpl.this.getCameraSessionExtend() == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.getCameraSessionExtend().e(this.f24125a);
        }
    }

    class q implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Rect[] f24127a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int[] f24128b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f24129c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f24130d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ p000do.c0 f24131e;

        q(Rect[] rectArr, int[] iArr, int i12, int i13, p000do.c0 c0Var) {
            this.f24127a = rectArr;
            this.f24128b = iArr;
            this.f24129c = i12;
            this.f24130d = i13;
            this.f24131e = c0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.O().setAFAEMeteringRegions(this.f24127a, this.f24128b, this.f24129c, this.f24130d, this.f24131e);
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class q0 implements Runnable {
        q0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.getCameraSessionExtend() == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.getCameraSessionExtend().I();
        }
    }

    class r implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f24134a;

        r(float f12) {
            this.f24134a = f12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.O().setAECompensation(this.f24134a);
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    private class r0 implements CameraSession.a {

        class a implements CameraSession.b {
            a() {
            }

            @Override // com.kwai.camerasdk.videoCapture.CameraSession.b
            public void a(int i12, int i13) {
                StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
                if (statsHolder != null) {
                    statsHolder.SetDeviceOrientation(i12, i13);
                    Log.i(CameraControllerImpl.TAG, "[DeviceOrientationChanged] device = " + i12 + ", camera = " + i13);
                }
            }
        }

        class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ p000do.f0 f24138a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ CameraSession.c f24139b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ Exception f24140c;

            b(p000do.f0 f0Var, CameraSession.c cVar, Exception exc) {
                this.f24138a = f0Var;
                this.f24139b = cVar;
                this.f24140c = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                Log.e(CameraControllerImpl.TAG, "Create session failure.");
                synchronized (CameraControllerImpl.this) {
                    try {
                        CameraControllerImpl.this.currentSession = null;
                    } catch (Throwable th2) {
                        CrashReporter.logCheckedException(th2, "CameraControllerImpl$SessionCreateListener$2.class", "run", -1);
                        throw th2;
                    }
                }
                if (!CameraControllerImpl.this.reOpenedCamera2v1 && CameraControllerImpl.this.cameraApiVersion == p000do.i.kAndroidCamera2 && this.f24138a != p000do.f0.CAMERA_UNIT_AUTHENTICATION_FAILED) {
                    CameraControllerImpl.this.cameraApiVersion = p000do.i.kAndroidCamera1;
                    CameraControllerImpl.this.openCameraSession();
                    CameraControllerImpl.this.reOpenedCamera2v1 = true;
                    return;
                }
                if (this.f24138a == p000do.f0.CAMERA_UNIT_AUTHENTICATION_FAILED) {
                    CameraControllerImpl cameraControllerImpl = CameraControllerImpl.this;
                    cameraControllerImpl.cameraApiVersion = cameraControllerImpl.initCameraApiVersion(false);
                    com.kwai.camerasdk.videoCapture.cameras.cameraunit.g.y0();
                }
                CameraControllerImpl.this.stateHolder.o(this.f24139b, this.f24138a, this.f24140c);
                if (CameraControllerImpl.this.disposed) {
                    CameraControllerImpl.this.disposeInternal();
                }
            }
        }

        private r0() {
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraSession.a
        public void a(long j12, long j13) {
            Log.i(CameraControllerImpl.TAG, "onReceivedFirstFrame");
            if (CameraControllerImpl.this.onCameraInitTimeCallback != null) {
                CameraControllerImpl.this.onCameraInitTimeCallback.a(j12, j13);
            }
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraSession.a
        public void b(@e.a CameraSession.c cVar, p000do.f0 f0Var, Exception exc) {
            CameraControllerImpl.this.executeOnCameraThread(new b(f0Var, cVar, exc));
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraSession.a
        public void c() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.i()) {
                return;
            }
            CameraControllerImpl.this.initCameraParametersAfterCameraOpened();
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraSession.a
        public void d(@e.a CameraSession cameraSession) {
            StatsHolder statsHolder;
            Log.i(CameraControllerImpl.TAG, "create session successfully. ");
            synchronized (CameraControllerImpl.class) {
                try {
                    CameraControllerImpl.this.currentSession = cameraSession;
                    CameraControllerImpl.this.currentSession.P(new a());
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "CameraControllerImpl$SessionCreateListener.class", "onDone", -1);
                    throw th2;
                }
            }
            if (CameraControllerImpl.this.currentSession != null) {
                CameraControllerImpl.this.currentSession.g((FrameMonitor) CameraControllerImpl.this.frameMonitorWeakReference.get());
            }
            CameraControllerImpl.this.reset();
            CameraControllerImpl.this.initCameraParametersAfterCameraOpened();
            CameraControllerImpl.this.mRetryStartPreviewHelper.q();
            CameraControllerImpl.this.stateHolder.n();
            if (CameraControllerImpl.this.currentSession != null && (CameraControllerImpl.this.currentSession instanceof ho.e) && (statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get()) != null) {
                statsHolder.setCameraFocalLengths(((ho.e) CameraControllerImpl.this.currentSession).r0());
            }
            if (CameraControllerImpl.this.waitForCloseSession) {
                Log.i(CameraControllerImpl.TAG, "waitForCloseSession execute closeCameraSession");
                CameraControllerImpl.this.waitForCloseSession = false;
                CameraControllerImpl.this.closeCameraSession();
            }
            CameraControllerImpl.this.reportCameraCapability();
            if (!CameraControllerImpl.this.waitForCloseSession && !CameraControllerImpl.this.disposed) {
                if (CameraControllerImpl.this.getCameraSessionExtend() != null) {
                    if (CameraControllerImpl.this.getCameraSessionExtend().G() && !CameraControllerImpl.this.openSubCamera) {
                        CameraControllerImpl.this.closeSubCamera();
                    } else if (!CameraControllerImpl.this.getCameraSessionExtend().G() && CameraControllerImpl.this.openSubCamera) {
                        CameraControllerImpl.this.openSubCamera();
                    }
                    if (CameraControllerImpl.this.getCameraSessionExtend().N()) {
                        CameraControllerImpl.this.getCameraSessionExtend().switchCamera(CameraControllerImpl.this.useFrontCamera);
                    }
                } else {
                    Log.i(CameraControllerImpl.TAG, "getCameraSessionExtend() is null");
                }
            }
            if (CameraControllerImpl.this.disposed) {
                CameraControllerImpl.this.disposeInternal();
            }
        }

        @Override // com.kwai.camerasdk.videoCapture.CameraSession.a
        public void e(long j12) {
            Log.i(CameraControllerImpl.TAG, "onPrepareOpen");
            if (CameraControllerImpl.this.stateHolder != null) {
                CameraControllerImpl.this.stateHolder.q();
            }
            if (CameraControllerImpl.this.onCameraInitTimeCallback != null) {
                CameraControllerImpl.this.onCameraInitTimeCallback.b(j12);
            }
        }

        /* synthetic */ r0(CameraControllerImpl cameraControllerImpl, k kVar) {
            this();
        }
    }

    class s implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f24142a;

        s(boolean z12) {
            this.f24142a = z12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.O().setAutoExposureLock(this.f24142a);
        }
    }

    class t implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f24144a;

        t(float f12) {
            this.f24144a = f12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            if (Math.abs(CameraControllerImpl.this.getZoom() - this.f24144a) > Float.MIN_NORMAL) {
                CameraControllerImpl.this.currentSession.p().setZoom(this.f24144a);
            }
            CameraControllerImpl.this.stateHolder.k();
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.setZoom(CameraControllerImpl.this.getZoom());
            }
        }
    }

    class u implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f24146a;

        u(int i12) {
            this.f24146a = i12;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.p().setZoom(this.f24146a);
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class v implements RetryStartPreviewHelper.RetryStartPreviewHelperListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ CameraController.f f24148a;

        v(CameraController.f fVar) {
            this.f24148a = fVar;
        }

        @Override // com.kwai.camerasdk.utils.RetryStartPreviewHelper.RetryStartPreviewHelperListener
        public void execOpenCameraFailed(p000do.f0 f0Var, Exception exc) {
            StatsHolder statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get();
            if (statsHolder != null) {
                statsHolder.onOpenCameraFailed(f0Var.getNumber());
            }
            CameraController.f fVar = this.f24148a;
            if (fVar != null) {
                fVar.n(f0Var, exc);
            }
        }

        @Override // com.kwai.camerasdk.utils.RetryStartPreviewHelper.RetryStartPreviewHelperListener
        public void execStartPreview() {
            CameraControllerImpl.this.startPreviewImpl();
        }
    }

    class w implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.kwai.camerasdk.videoCapture.cameras.c.a f24150a;

        w(com.kwai.camerasdk.videoCapture.cameras.c.a aVar) {
            this.f24150a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraControllerImpl.this.currentSession == null || !CameraControllerImpl.this.stateHolder.d()) {
                return;
            }
            CameraControllerImpl.this.currentSession.L().setFlashMode(this.f24150a);
            CameraControllerImpl.this.stateHolder.k();
        }
    }

    class x implements Runnable {
        x() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraControllerImpl.this.disposed = true;
            if (!CameraControllerImpl.this.stateHolder.h()) {
                CameraControllerImpl.this.disposeInternal();
                return;
            }
            Log.i(CameraControllerImpl.TAG, "Current stateHolder state: " + CameraControllerImpl.this.stateHolder.b());
            CameraControllerImpl.this.closeCameraSession();
        }
    }

    class y implements Runnable {
        y() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StatsHolder statsHolder;
            if (!CameraControllerImpl.this.stateHolder.f()) {
                if (CameraControllerImpl.this.stateHolder.i() && (statsHolder = (StatsHolder) CameraControllerImpl.this.stats.get()) != null) {
                    statsHolder.stopPreview();
                }
                CameraControllerImpl.this.closeCameraSession();
            }
            CameraControllerImpl cameraControllerImpl = CameraControllerImpl.this;
            cameraControllerImpl.nativeDestroyCameraController(cameraControllerImpl.nativeCameraController);
            CameraControllerImpl.this.nativeCameraController = 0L;
        }
    }

    class z implements Runnable {
        z() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CameraControllerImpl.this.cameraThreadHandler.getLooper().quit();
        }
    }

    public CameraControllerImpl(Context context, p000do.u uVar, CameraController.f fVar) {
        this(context, uVar, fVar, null, null, false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeCameraSession() {
        Log.i(TAG, "closeCameraSession start");
        if (this.stateHolder.e()) {
            Log.i(TAG, "closeCameraSession end isClosing");
            return;
        }
        if (this.stateHolder.h()) {
            this.waitForCloseSession = true;
            Log.i(TAG, "waitForCloseSession = true");
        } else if (this.currentSession == null || !this.stateHolder.d()) {
            Log.i(TAG, "no session open.");
            this.stateHolder.m();
        } else {
            this.stateHolder.p();
            this.currentSession.stop();
            synchronized (this) {
                try {
                    this.currentSession = null;
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "CameraControllerImpl.class", "closeCameraSession", -1);
                    throw th2;
                }
            }
            this.mRetryStartPreviewHelper.o();
            this.stateHolder.m();
            Log.i(TAG, "stop preview done.");
        }
        Log.i(TAG, "closeCameraSession end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disposeInternal() {
        Log.i(TAG, "DisposeInternal");
        executeOnCameraThread(new y());
        executeOnCameraThread(new z());
        Log.i(TAG, "dispose end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeOnCameraThread(Runnable runnable) {
        if (Thread.currentThread() == this.cameraThreadHandler.getLooper().getThread()) {
            runnable.run();
        } else {
            this.cameraThreadHandler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.kwai.camerasdk.videoCapture.d getCameraSessionExtend() {
        com.kwai.camerasdk.videoCapture.d dVar;
        synchronized (this) {
            try {
                CameraSession cameraSession = this.currentSession;
                dVar = (cameraSession == null || !(cameraSession instanceof com.kwai.camerasdk.videoCapture.d)) ? null : (com.kwai.camerasdk.videoCapture.d) cameraSession;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "CameraControllerImpl.class", "getCameraSessionExtend", -110);
                throw th2;
            }
        }
        return dVar;
    }

    private float getDefaultScreenBrightness() {
        Activity activity = this.currentActivity;
        if (activity != null) {
            return activity.getWindow().getAttributes().screenBrightness;
        }
        return 1.0f;
    }

    @Deprecated
    public static int getNumberOfCameras(Context context) {
        return getNumberOfCameras(context, p000do.i.kAndroidCamera1);
    }

    private p000do.x getStabilizationMode() {
        return this.useFrontCamera ? this.config.Y() : this.config.X();
    }

    private boolean hasPermissionsGranted(String[] strArr) {
        for (String str : strArr) {
            if (androidx.core.content.a.a(this.context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public p000do.i initCameraApiVersion(boolean z12) {
        p000do.i iVarR = this.config.R();
        if (iVarR == p000do.i.kAndroidCameraKit && !com.kwai.camerasdk.videoCapture.cameras.camerakit.f.v0(this.context)) {
            iVarR = p000do.i.kAndroidCameraAuto;
        }
        if (iVarR == p000do.i.kAndroidCameraUnit && !com.kwai.camerasdk.videoCapture.cameras.cameraunit.g.C0(this.context, z12)) {
            iVarR = p000do.i.kAndroidCameraAuto;
        }
        if (iVarR == p000do.i.kAndroidCameraVivo && !com.kwai.camerasdk.videoCapture.cameras.cameravivo.e.n0(this.context)) {
            iVarR = p000do.i.kAndroidCameraAuto;
        }
        if (iVarR == p000do.i.kAndroidCameraAuto) {
            iVarR = fo.b.f(this.context);
        }
        Log.i(TAG, "initCameraApiVersion apiVersion = " + iVarR);
        return iVarR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCameraParametersAfterCameraOpened() {
        setAFAEAutoMode(this.enableFaceDetectAutoExposure);
        setMirrorFrontCamera(mirrorFrontCamera());
        Log.i(TAG, "initCameraParametersAfterCameraOpened getVideoStabilizationType = " + this.currentSession.s());
        StatsHolder statsHolder = this.stats.get();
        if (statsHolder != null) {
            statsHolder.setVideoStabilizationType(this.currentSession.s());
            statsHolder.setEnableLowlightBoost(getLowLightBoostEnabled());
            statsHolder.setZoom(1.0f);
        }
    }

    private boolean isAdaptedCameraFpsSupportCustomRange() {
        return (com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.b() || com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.g() || com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.f()) ? false : true;
    }

    private boolean isRecordingHintEnabled() {
        return isFrontCamera() ? this.recordingHintEnabledForFrontCamera : this.recordingHintEnabledForBackCamera;
    }

    private native long nativeCreateCameraController();

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDestroyCameraController(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeUpdateTargetFps(long j12, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public void openCameraSession() {
        Log.i(TAG, "openCameraSession start");
        this.useFrontCamera = getNumberOfCameras(this.context, this.cameraApiVersion) < 2 ? false : this.useFrontCamera;
        this.stateHolder.q();
        this.adaptedFrameRate = this.targetFps;
        Log.i(TAG, "createCameraSession");
        p000do.x stabilizationMode = getStabilizationMode();
        boolean z12 = !this.disableStabilization;
        Log.i(TAG, "videoStabilizationEnabledIfSupport: " + z12);
        com.kwai.camerasdk.videoCapture.e.b(this.cameraApiVersion, this.currentSession, this.context.getApplicationContext(), new r0(this, null), this.cameraDataListener, this.resolutionRequest, new com.kwai.camerasdk.videoCapture.c(this.useFrontCamera, this.mirrorFrontCamera, this.targetFps, this.targetMinFps, z12, isRecordingHintEnabled(), this.zeroShutterLagIfSupportEnabled, this.useFrontCamera ? this.config.U() : this.config.T(), stabilizationMode, isAdaptedCameraFpsSupportCustomRange(), this.enableHdr, this.config.m0(), this.config.H0(), this.config.B0(), this.edgeMode, this.config.Z(), this.enableDumpDepth, this.eglContext, this.config.l0(), this.config.S(), this.openSubCamera, this.config.k0(), this.enableLowlightBoost, this.config.g0(), this.config.n0()));
        Log.d(TAG, "openCameraSession end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportCameraCapability() {
        StatsHolder statsHolder;
        com.kwai.camerasdk.utils.f[] fVarArr = this.previewSizes;
        if (fVarArr == null || fVarArr.length == 0 || this.currentSession == null || (statsHolder = this.stats.get()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i12 = 0;
        while (true) {
            com.kwai.camerasdk.utils.f[] fVarArr2 = this.previewSizes;
            if (i12 >= fVarArr2.length) {
                statsHolder.reportCameraCapability(p000do.j.k().b(this.cameraApiVersion.getNumber()).c(this.currentSession.isFrontCamera()).a(arrayList).f(this.maxPreviewFps).d(this.isPictureHdrSupported).e(this.isPreviewHdrSupported).build());
                return;
            } else {
                arrayList.add(fVarArr2[i12].toString());
                i12++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetSize() {
        this.previewCropSize = this.currentSession.K();
        this.cameraCaptureSize = this.currentSession.u();
        this.pictureCropSize = this.currentSession.C();
        this.previewSizes = this.currentSession.n();
        this.recordingSizes = this.currentSession.q();
        this.pictureSizes = this.currentSession.h();
    }

    private boolean setScreenBrightness(float f12) {
        Activity activity = this.currentActivity;
        if (activity == null) {
            return false;
        }
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = f12;
        this.currentActivity.getWindow().setAttributes(attributes);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatsCameraStatus() {
        StatsHolder statsHolder = this.stats.get();
        if (statsHolder != null) {
            statsHolder.switchCamera(this.useFrontCamera);
            statsHolder.setCameraApiVersion(this.cameraApiVersion.getNumber());
            statsHolder.setRecordingHint(isRecordingHintEnabled());
            statsHolder.setEnableHdr(getEnableHdr());
            statsHolder.setZoom(1.0f);
            if (!this.stateHolder.f() && !this.stateHolder.e()) {
                statsHolder.startPreview();
            }
            CameraSession cameraSession = this.currentSession;
            if (cameraSession == null || !(cameraSession instanceof ho.e)) {
                return;
            }
            statsHolder.setCameraFocalLengths(((ho.e) cameraSession).r0());
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void beginConfiguration() {
        this.cameraThreadHandler.post(new q0());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void closeSubCamera() {
        Log.i(TAG, "Close sub camera");
        this.openSubCamera = false;
        this.cameraThreadHandler.post(new o0());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void commitConfiguration() {
        this.cameraThreadHandler.post(new a());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void dispose() {
        Log.i(TAG, "disposing");
        executeOnCameraThread(new x());
    }

    public void enableDumpDepth() {
        this.enableDumpDepth = true;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void fallbackPictureCaptureConfig(n1 n1Var) {
        boolean z12 = false;
        boolean z13 = true;
        if (this.config.o0()) {
            z13 = false;
        } else {
            if (this.cameraApiVersion == p000do.i.kAndroidCamera2 && this.config.H0()) {
                this.config = this.config.toBuilder().R(false).build();
            } else if (this.config.W() > 1080) {
                this.config = this.config.toBuilder().j(1080).i(1920).build();
            } else {
                this.config = this.config.toBuilder().y(true).build();
            }
            z12 = true;
        }
        if (z12) {
            executeOnCameraThread(new h0());
        }
        if (z13) {
            executeOnCameraThread(new i0(z13, n1Var));
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public float getAECompensation() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.O().getAECompensation();
        }
        return 0.0f;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public com.kwai.camerasdk.videoCapture.cameras.a.EnumC0350a getAFAEMode() {
        CameraSession cameraSession = this.currentSession;
        return cameraSession != null ? cameraSession.O().getAFAEMode() : com.kwai.camerasdk.videoCapture.cameras.a.EnumC0350a.Auto;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public Camera getCamera() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession == null || !(cameraSession instanceof go.c)) {
            return null;
        }
        return ((go.c) cameraSession).b0();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public p000do.i getCameraApiVersion() {
        return this.cameraApiVersion;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public com.kwai.camerasdk.utils.f getCameraCaptureSize() {
        return this.cameraCaptureSize;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public int getCameraOrientation() {
        return this.cameraOrientation;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public p000do.n getCaptureDeviceType() {
        CameraSession cameraSession = this.currentSession;
        return cameraSession != null ? cameraSession.w() : p000do.n.kCaptureDeviceTypeBuiltInWideAngleCamera;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public p000do.u getConfig() {
        return this.config.toBuilder().P(this.useFrontCamera).build();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public p000do.u getDaenerysCaptureConfig() {
        return this.captureConfig;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public boolean getEnableHdr() {
        return this.enableHdr && this.useFrontCamera && this.cameraApiVersion == p000do.i.kAndroidCameraKit;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public float getExposureValueStep() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.O().getExposureValueStep();
        }
        return 0.0f;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.c
    @e.a
    public com.kwai.camerasdk.videoCapture.cameras.c.a getFlashMode() {
        CameraSession cameraSession = this.currentSession;
        return cameraSession != null ? cameraSession.L().getFlashMode() : com.kwai.camerasdk.videoCapture.cameras.c.a.FLASH_MODE_OFF;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public float getFocalLength() {
        return this.focalLength;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public float getHorizontalViewAngle() {
        return this.horizontalViewAngle;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public boolean getLowLightBoostEnabled() {
        return getCameraSessionExtend() != null && getCameraSessionExtend().B();
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public int getMaxAECompensation() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.O().getMaxAECompensation();
        }
        return 0;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public float getMaxZoom() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.p().getMaxZoom();
        }
        return 1.0f;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public int getMaxZoomSteps() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.p().getMaxZoomSteps();
        }
        return 0;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public int getMinAECompensation() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.O().getMinAECompensation();
        }
        return 0;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public float getMinZoom() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.p().getMinZoom();
        }
        return 1.0f;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public long getNativeCameraController() {
        return this.nativeCameraController;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public com.kwai.camerasdk.utils.f getPictureSize() {
        return this.pictureCropSize;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public com.kwai.camerasdk.utils.f[] getPictureSizes() {
        return this.pictureSizes;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public com.kwai.camerasdk.utils.f getPreviewSize() {
        return this.previewCropSize;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public com.kwai.camerasdk.utils.f[] getPreviewSizes() {
        return this.previewSizes;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public com.kwai.camerasdk.utils.f[] getRecordingSizes() {
        return this.recordingSizes;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public CameraController.c getState() {
        return this.stateHolder.b();
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.c
    @e.a
    public com.kwai.camerasdk.videoCapture.cameras.c.a[] getSupportedFlashModes() {
        CameraSession cameraSession = this.currentSession;
        return cameraSession != null ? cameraSession.L().getSupportedFlashModes() : new com.kwai.camerasdk.videoCapture.cameras.c.a[0];
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public p000do.x getVideoStabilizationMode() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession == null || !this.stateHolder.d()) {
            return p000do.x.kStabilizationModeOff;
        }
        p000do.x xVar = p000do.x.kStabilizationModeOff;
        int i12 = k0.f24111a[cameraSession.s().ordinal()];
        if (i12 == 1 || i12 == 2) {
            return p000do.x.kStabilizationModeEIS;
        }
        if (i12 == 3 || i12 == 4) {
            return p000do.x.kStabilizationModeOIS;
        }
        if (i12 != 5) {
            return xVar;
        }
        return cameraSession.w() == p000do.n.kCaptureDeviceTypeBuiltInUltraWideCamera ? p000do.x.kStabilizationModeProSuperEIS : p000do.x.kStabilizationModeSuperEIS;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public float getZoom() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.p().getZoom();
        }
        return 1.0f;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.c
    @e.a
    public boolean hasFlash() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            return cameraSession.L().hasFlash();
        }
        return false;
    }

    public void initWithDaenerysCaptureConfig(p000do.u uVar) {
        p000do.u uVarBuild = p000do.u.J0(uVar).build();
        this.config = uVarBuild;
        if (uVarBuild.y0() == 0 || this.config.v0() == 0) {
            p000do.u uVarBuild2 = DaenerysConfigBuilder.defaultCaptureConfigBuilder().build();
            this.config = this.config.toBuilder().J(uVarBuild2.y0()).G(uVarBuild2.v0()).build();
        }
        boolean z12 = true;
        this.cameraApiVersion = initCameraApiVersion(true);
        this.useFrontCamera = sCameraNumbers == 1 ? false : this.config.F0();
        com.kwai.camerasdk.videoCapture.cameras.b bVar = new com.kwai.camerasdk.videoCapture.cameras.b();
        this.resolutionRequest = bVar;
        bVar.f24205a = new com.kwai.camerasdk.utils.f(this.config.u0(), this.config.t0());
        this.resolutionRequest.f24206b = new com.kwai.camerasdk.utils.f(this.config.y0(), this.config.v0());
        this.resolutionRequest.f24209e = this.config.w0();
        this.resolutionRequest.f24210f = this.config.x0();
        com.kwai.camerasdk.videoCapture.cameras.b bVar2 = this.resolutionRequest;
        bVar2.f24211g = true;
        bVar2.f24207c = new com.kwai.camerasdk.utils.f(this.config.W(), this.config.V());
        this.resolutionRequest.f24212h = this.config.Q();
        this.resolutionRequest.f24213i = this.config.E0();
        this.resolutionRequest.f24214j = this.config.G0();
        nativeUpdateTargetFps(this.nativeCameraController, this.config.C0());
        this.enableFaceDetectAutoExposure = this.config.f0();
        this.recordingHintEnabledForFrontCamera = this.config.j0() && this.cameraApiVersion == p000do.i.kAndroidCamera1;
        this.recordingHintEnabledForBackCamera = this.config.i0() && this.cameraApiVersion == p000do.i.kAndroidCamera1;
        this.targetMinFps = this.config.D0();
        this.targetFps = this.config.C0();
        this.adaptedFrameRate = this.config.C0();
        this.enableHdr = this.config.h0();
        this.edgeMode = this.config.d0();
        this.disableStabilization = this.config.c0();
        Log.i(TAG, "initWithDaenerysCaptureConfig disableStabilization: " + this.disableStabilization);
        this.zeroShutterLagIfSupportEnabled = this.config.e0();
        if (!disableSetAdaptedCameraFps) {
            if (!this.config.b0() && !jo.a.a(jo.c.f75245a)) {
                z12 = false;
            }
            disableSetAdaptedCameraFps = z12;
        }
        Log.i(TAG, "videoStabilizationMode = " + getStabilizationMode());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public boolean isFrontCamera() {
        return this.useFrontCamera;
    }

    public boolean isPreview(long j12) {
        return this.stateHolder.i() && System.currentTimeMillis() - this.stateHolder.c() > j12;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public boolean isZoomSupported() {
        CameraSession cameraSession = this.currentSession;
        return cameraSession != null && cameraSession.p().isZoomSupported();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void markNextFramesToCapture(long j12, int i12) {
        this.cameraThreadHandler.post(new j(j12, i12));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public boolean mirrorFrontCamera() {
        boolean z12;
        synchronized (this) {
            try {
                z12 = this.mirrorFrontCamera;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "CameraControllerImpl.class", "mirrorFrontCamera", 66);
                throw th2;
            }
        }
        return z12;
    }

    @Override // com.kwai.camerasdk.mediarecorder.RecordingStatesListener
    public boolean onStartCapturePreview() {
        return this.stateHolder.i() || this.stateHolder.j();
    }

    @Override // com.kwai.camerasdk.mediarecorder.RecordingStatesListener
    public boolean onStartRecordingVideo() {
        if (this.stateHolder.g()) {
            Log.w(TAG, "Invalid state change.");
            return false;
        }
        this.stateHolder.r();
        return true;
    }

    @Override // com.kwai.camerasdk.mediarecorder.RecordingStatesListener
    public void onStopCapturePreview() {
    }

    @Override // com.kwai.camerasdk.mediarecorder.RecordingStatesListener
    public void onStopRecordingVideo() {
        if (this.stateHolder.j()) {
            this.stateHolder.s();
        } else {
            Log.w(TAG, "Invalid state change.");
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void openSubCamera() {
        Log.i(TAG, "Open sub camera");
        this.openSubCamera = true;
        this.cameraThreadHandler.post(new n0());
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController, com.kwai.camerasdk.videoCapture.cameras.c, com.kwai.camerasdk.videoCapture.cameras.a
    public void reset() {
        this.currentSession.O().reset();
        this.currentSession.p().reset();
        this.currentSession.L().reset();
        this.horizontalViewAngle = this.currentSession.Q();
        this.focalLength = this.currentSession.D();
        this.cameraOrientation = this.currentSession.k();
        this.maxPreviewFps = this.currentSession.f();
        this.isPictureHdrSupported = this.currentSession.F();
        this.isPreviewHdrSupported = this.currentSession.a();
        resetSize();
    }

    public void resetAECompensationFromNative() {
        setAECompensation(this.defaultAECompensation);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void resumePreview() {
        Log.i(TAG, "resumePreview");
        this.cameraThreadHandler.post(new e());
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public void setAECompensation(float f12) {
        this.cameraThreadHandler.post(new r(f12));
    }

    public void setAECompensationFromNative(float f12) {
        setAECompensation(f12);
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public void setAFAEAutoMode(boolean z12) {
        this.enableFaceDetectAutoExposure = z12;
        this.cameraThreadHandler.post(new o());
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public void setAFAEMeteringRegions(Rect[] rectArr, int[] iArr, int i12, int i13, p000do.c0 c0Var) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("setAFAEMeteringRegions rect: ");
        sb2.append((rectArr == null || rectArr.length <= 0) ? new Rect(0, 0, 0, 0) : rectArr[0]);
        Log.d(TAG, sb2.toString());
        this.cameraThreadHandler.post(new q(rectArr, iArr, i12, i13, c0Var));
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public void setAFAETapMode() {
        this.cameraThreadHandler.post(new p());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setAWBMode(CameraController.g gVar) {
        Log.w(TAG, "setAWBMode API is not implemented yet");
    }

    public void setAdaptedCameraFps(int i12) {
        if (disableSetAdaptedCameraFps) {
            return;
        }
        this.cameraThreadHandler.post(new d(i12));
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.a
    public boolean setAutoExposureLock(boolean z12) {
        if (this.currentSession == null) {
            return false;
        }
        executeOnCameraThread(new s(z12));
        return true;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setCameraStreamTypeAndVideoStabilizationMode(p000do.m mVar, p000do.x xVar, boolean z12) {
        Log.i(TAG, "setCameraStreamTypeAndVideoStabilizationMode cameraStreamType: " + mVar + ", stabilizationMode: " + xVar + ", isFront: " + z12);
        if (z12) {
            if (this.config.U() == mVar && this.config.Y() == xVar) {
                return;
            } else {
                this.config = this.config.toBuilder().h(mVar).l(xVar).build();
            }
        } else if (this.config.T() == mVar && this.config.X() == xVar) {
            return;
        } else {
            this.config = this.config.toBuilder().g(mVar).k(xVar).build();
        }
        if (z12 != isFrontCamera()) {
            return;
        }
        this.cameraThreadHandler.post(new b0(mVar, xVar, z12));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setCaptureDeviceType(p000do.n nVar) {
        Log.i(TAG, "setCaptureDeviceType: " + nVar);
        this.cameraThreadHandler.post(new m(nVar));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setDisableStabilization(boolean z12) {
        Log.i(TAG, "setDisableStabilization: " + z12);
        if (this.disableStabilization == z12) {
            return;
        }
        this.disableStabilization = z12;
        this.cameraThreadHandler.post(new a0(z12));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setEnableHdr(boolean z12) {
        this.cameraThreadHandler.post(new l(z12));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setEnableLowLightBoost(boolean z12) {
        this.cameraThreadHandler.post(new p0(z12));
    }

    public void setEnableSimulateMultiCamera(boolean z12) {
        this.enableSimulateMultiCamera = z12;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.c
    public void setFlashMode(@e.a com.kwai.camerasdk.videoCapture.cameras.c.a aVar) {
        this.cameraThreadHandler.post(new w(aVar));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setFrameMonitor(FrameMonitor frameMonitor) {
        WeakReference<FrameMonitor> weakReference = new WeakReference<>(frameMonitor);
        this.frameMonitorWeakReference = weakReference;
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            cameraSession.g(weakReference.get());
        }
    }

    public void setIgnoreCameraUsageDetection(boolean z12) {
        this.mRetryStartPreviewHelper.v(z12);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public boolean setLowLightStrategyEnabled(Activity activity, boolean z12) {
        if (this.currentActivity == null) {
            this.currentActivity = activity;
            this.defaultScreenBrightness = getDefaultScreenBrightness();
        }
        return z12 ? setScreenBrightness(1.0f) : setScreenBrightness(this.defaultScreenBrightness);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setMirrorFrontCamera(boolean z12) {
        synchronized (this) {
            try {
                this.mirrorFrontCamera = z12;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "CameraControllerImpl.class", "setMirrorFrontCamera", -1);
                throw th2;
            }
        }
        executeOnCameraThread(new d0());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setOnCameraInitTimeCallback(CameraController.d dVar) {
        this.onCameraInitTimeCallback = dVar;
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public void setOnZoomListener(@e.a ZoomController.OnZoomListener onZoomListener) {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession != null) {
            cameraSession.p().setOnZoomListener(onZoomListener);
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setPreviewCallback() {
        CameraSession cameraSession = this.currentSession;
        if (cameraSession == null || !(cameraSession instanceof go.c)) {
            return;
        }
        ((go.c) cameraSession).A0();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setStats(StatsHolder statsHolder) {
        this.stats = new WeakReference<>(statsHolder);
        this.cameraThreadHandler.post(new m0());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setUseYuvOutputForCamera2TakePicture(boolean z12) {
        executeOnCameraThread(new j0(z12));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setVideoStabilizationMode(p000do.x xVar, boolean z12) {
        Log.e(TAG, "Set video stabilization mode: " + xVar + ", isFront: " + z12);
        p000do.u uVar = this.config;
        setCameraStreamTypeAndVideoStabilizationMode(z12 ? uVar.U() : uVar.T(), xVar, z12);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void setZeroShutterLagIfSupportEnabled(boolean z12) {
        this.zeroShutterLagIfSupportEnabled = z12;
        this.cameraThreadHandler.post(new c0());
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public void setZoom(float f12) {
        Log.d(TAG, "Set zoom: " + f12);
        this.cameraThreadHandler.post(new t(f12));
    }

    public void startPreviewImpl() {
        Log.i(TAG, "startPreviewImpl");
        this.cameraThreadHandler.post(new f());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void stopPreview() {
        Log.i(TAG, "stopPreview");
        this.cameraThreadHandler.post(new h());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public boolean supportLowLightBoost() {
        boolean z12;
        synchronized (this) {
            try {
                z12 = getCameraSessionExtend() != null && getCameraSessionExtend().i();
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "CameraControllerImpl.class", "supportLowLightBoost", 122);
                throw th2;
            }
        }
        return z12;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public boolean supportTakePicture() {
        CameraSession cameraSession = this.currentSession;
        return cameraSession != null && cameraSession.z();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void switchCamera(boolean z12) {
        Log.i(TAG, "switchCamera start");
        this.cameraThreadHandler.post(new n(z12));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void takePicture(CameraController.e eVar) {
        takePicture(eVar, false);
    }

    @Override // com.kwai.camerasdk.mediarecorder.RecordingStatesListener
    public void updateCaptureImageStats(p000do.q qVar) {
        StatsHolder statsHolder = this.stats.get();
        if (statsHolder != null) {
            statsHolder.captureImageFinish(qVar);
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void updateDaenerysCaptureConfig(p000do.u uVar) {
        Log.i(TAG, "updateDaenerysCaptureConfig");
        this.cameraThreadHandler.post(new l0(uVar));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void updateFps(int i12, int i13) {
        this.cameraThreadHandler.post(new c(i12, i13));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void updatePreviewResolution(com.kwai.camerasdk.utils.f fVar) {
        executeOnCameraThread(new e0(fVar));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void updatePreviewScale(com.kwai.camerasdk.utils.f fVar) {
        updatePreviewResolution(fo.f.e(this.resolutionRequest, fVar));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void updateResolutionCaptureConfig(int i12, int i13, int i14) {
        this.config = this.config.toBuilder().J(i12).G(i13).H(i14).build();
        executeOnCameraThread(new f0());
    }

    public CameraControllerImpl(Context context, p000do.u uVar, CameraController.f fVar, CameraController.DataListener dataListener, EglBase.Context context2, boolean z12, CameraController.b bVar) {
        this.adaptedFrameRate = 30;
        this.currentSession = null;
        this.enableHdr = false;
        this.horizontalViewAngle = 0.0f;
        this.focalLength = 0.0f;
        this.disableStabilization = false;
        this.cameraOrientation = 0;
        this.frameMonitorWeakReference = new WeakReference<>(null);
        this.enableFaceDetectAutoExposure = false;
        this.currentActivity = null;
        this.enableSimulateMultiCamera = false;
        this.enableDumpDepth = false;
        this.openSubCamera = false;
        this.enableLowlightBoost = false;
        this.cameraPositionChangeCallback = null;
        this.reOpenedCamera2v1 = false;
        this.cameraDataListener = new b();
        p000do.u uVarA = fo.b.a(uVar);
        this.captureConfig = uVarA;
        this.dataListener = dataListener;
        this.context = context;
        this.disableStabilization = uVarA.c0();
        this.eglContext = context2;
        this.mirrorFrontCamera = uVar.q0();
        this.cameraPositionChangeCallback = bVar;
        this.openSubCamera = uVarA.r0();
        Log.i(TAG, "CameraControllerImpl disableStabilization: " + this.disableStabilization);
        HandlerThread handlerThread = new HandlerThread("CameraThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.cameraThreadHandler = handler;
        handler.post(new k(this));
        this.mRetryStartPreviewHelper = new RetryStartPreviewHelper(new v(fVar), handler, z12);
        this.stats = new WeakReference<>(null);
        this.nativeCameraController = nativeCreateCameraController();
        this.stateHolder = new com.kwai.camerasdk.videoCapture.f(new g0(fVar));
        initWithDaenerysCaptureConfig(uVarA);
        this.defaultAECompensation = getAECompensation();
    }

    public static int getNumberOfCameras(Context context, p000do.i iVar) {
        int i12 = sCameraNumbers;
        if (i12 > 0) {
            return i12;
        }
        if (iVar == p000do.i.kAndroidCamera2) {
            try {
                sCameraNumbers = ho.e.t0(context);
            } catch (KSCameraSDKException.IllegalStateException e12) {
                CrashReporter.logCheckedException(e12, "CameraControllerImpl.class", "getNumberOfCameras", -22);
                e12.printStackTrace();
                sCameraNumbers = go.c.h0();
            }
        } else {
            sCameraNumbers = go.c.h0();
        }
        return sCameraNumbers;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void takePicture(CameraController.e eVar, boolean z12) {
        Log.i(TAG, "takePicture, mute : " + z12);
        this.cameraThreadHandler.post(new i(eVar, z12));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraController
    public void resumePreview(boolean z12) {
        Log.i(TAG, "resumePreview useFrontCamera = " + z12);
        this.cameraThreadHandler.post(new g(z12));
    }

    @Override // com.kwai.camerasdk.videoCapture.cameras.ZoomController
    public void setZoom(int i12) {
        this.cameraThreadHandler.post(new u(i12));
    }
}
