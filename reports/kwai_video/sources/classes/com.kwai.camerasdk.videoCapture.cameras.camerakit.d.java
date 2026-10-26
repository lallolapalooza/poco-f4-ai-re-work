package com.kwai.camerasdk.videoCapture.cameras.camerakit;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Size;
import com.huawei.camera.camerakit.ActionDataCallback;
import com.huawei.camera.camerakit.ActionStateCallback;
import com.huawei.camera.camerakit.CameraDeviceCallback;
import com.huawei.camera.camerakit.CameraKit;
import com.huawei.camera.camerakit.Mode;
import com.huawei.camera.camerakit.ModeCharacteristics;
import com.huawei.camera.camerakit.ModeStateCallback;
import com.huawei.camera.camerakit.RequestKey;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.monitor.FrameMonitor;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.camerasdk.videoCapture.CameraController;
import com.kwai.camerasdk.videoCapture.CameraSession;
import com.kwai.camerasdk.videoCapture.FrameBuffer;
import com.kwai.camerasdk.videoCapture.cameras.ResolutionSelector;
import com.kwai.camerasdk.videoCapture.i;
import com.kwai.chat.kwailink.constants.KwaiLinkCode;
import com.yxcorp.bugly.CrashReporter;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import p000do.c0;
import p000do.f0;
import p000do.m;
import p000do.n;
import p000do.o0;
import p000do.o1;
import p000do.q1;
import p000do.s;
import p000do.v0;
import p000do.x;
import p000do.y;

/* JADX INFO: compiled from: CameraKitSession.java */
/* JADX INFO: loaded from: classes15.dex */
@TargetApi(28)
public abstract class d implements CameraSession {
    private int A;
    private int B;
    protected ModeCharacteristics E;
    private CameraKit F;
    private String G;
    protected Mode H;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    private final ModeStateCallback f23653J;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Context f23654a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final CameraSession.a f23655b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.kwai.camerasdk.videoCapture.cameras.camerakit.c f23656c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.kwai.camerasdk.videoCapture.cameras.camerakit.g f23657d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.kwai.camerasdk.videoCapture.cameras.camerakit.b f23658e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.kwai.camerasdk.videoCapture.cameras.camerakit.a f23659f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected final CameraSession.CameraDataListener f23660g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private com.kwai.camerasdk.videoCapture.cameras.b f23661h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final com.kwai.camerasdk.videoCapture.c f23662i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected final Handler f23663j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ImageReader f23665l;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    protected com.kwai.camerasdk.utils.f f23670q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private com.kwai.camerasdk.utils.f f23671r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    protected MediaRecorder f23674u;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    g f23677x;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private long f23664k = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f23666m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private long f23667n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private i f23668o = new i();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f23669p = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f23672s = 1.0f;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private v0.b f23673t = v0.i();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private y f23675v = y.kStabilizationTypeNone;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f23676w = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private WeakReference<FrameMonitor> f23678y = new WeakReference<>(null);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private int f23679z = fo.b.e();
    private h C = h.IDLE;
    private int D = 5;
    private final CameraDeviceCallback I = new a(this);

    /* JADX INFO: compiled from: CameraKitSession.java */
    class a extends CameraDeviceCallback {
        a(d dVar) {
        }
    }

    /* JADX INFO: compiled from: CameraKitSession.java */
    class b extends ActionStateCallback {
        b(d dVar) {
        }
    }

    /* JADX INFO: compiled from: CameraKitSession.java */
    class c extends ActionDataCallback {
        c(d dVar) {
        }
    }

    /* JADX INFO: renamed from: com.kwai.camerasdk.videoCapture.cameras.camerakit.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CameraKitSession.java */
    class C0346d extends ModeStateCallback {
        C0346d(d dVar) {
        }
    }

    /* JADX INFO: compiled from: CameraKitSession.java */
    class e implements ImageReader.OnImageAvailableListener {
        e() {
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader imageReader) {
            Image imageAcquireNextImage;
            long jNanoTime = System.nanoTime();
            long jElapsedRealtimeNanos = d.this.f23666m ? SystemClock.elapsedRealtimeNanos() : 0L;
            if (d.this.f23660g == null || (imageAcquireNextImage = imageReader.acquireNextImage()) == null) {
                return;
            }
            if (d.this.f23664k != 0) {
                if (d.this.f23666m) {
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    if (Math.abs(timeUnit.toMillis(jElapsedRealtimeNanos - imageAcquireNextImage.getTimestamp())) > 1000) {
                        d.this.f23666m = false;
                        d.this.f23660g.onReportCameraFunctionFailed(f0.CAMERA_KIT_ERROR, (int) timeUnit.toMillis(jElapsedRealtimeNanos - imageAcquireNextImage.getTimestamp()));
                        Log.e("CameraKitSession", "CAMERA_KIT_ERROR : time stamp diff = " + timeUnit.toMillis(jElapsedRealtimeNanos - imageAcquireNextImage.getTimestamp()));
                    } else {
                        d.this.f23667n = System.nanoTime() - SystemClock.elapsedRealtimeNanos();
                    }
                }
                d.this.f23655b.a(d.this.f23664k, SystemClock.uptimeMillis());
                d.this.f23664k = 0L;
            }
            if (d.this.f23666m) {
                jNanoTime = imageAcquireNextImage.getTimestamp() + d.this.f23667n;
            }
            FrameMonitor frameMonitor = (FrameMonitor) d.this.f23678y.get();
            if (frameMonitor != null) {
                frameMonitor.e(o0.kCameraFrameProcessThread, TimeUnit.NANOSECONDS.toMillis(jNanoTime));
            }
            try {
                FrameBuffer frameBufferE = d.this.f23668o.e(imageAcquireNextImage, d.this.f23670q);
                int iC = d.this.f23668o.c();
                int iD = d.this.f23668o.d();
                VideoFrame videoFrameWithTransform = VideoFrame.fromCpuFrame(frameBufferE, iD, d.this.f23670q.c(), iC, TimeUnit.NANOSECONDS.toMillis(jNanoTime)).withTransform(o1.p().f(d.this.h0()).e(d.this.f23662i.f23578a && d.this.f23662i.f23579b).build());
                videoFrameWithTransform.attributes.r(d.this.f23673t.build());
                videoFrameWithTransform.attributes.k(d.this.Q());
                videoFrameWithTransform.attributes.p(d.this.f23669p);
                videoFrameWithTransform.attributes.m(q1.kFrameSourcePreview);
                videoFrameWithTransform.attributes.h(d.this.f23679z);
                d.this.f23669p = false;
                if (d.this.f23677x != null) {
                    long jA = com.kwai.camerasdk.utils.h.a();
                    d dVar = d.this;
                    if (jA - dVar.f23677x.f23682a >= 0) {
                        if (dVar.A == 0) {
                            videoFrameWithTransform.attributes.p(true);
                            d.this.f23677x = null;
                        } else if (d.this.B < d.this.A) {
                            do.g.b bVarE = p000do.g.e();
                            bVarE.b(d.Y(d.this));
                            bVarE.a(d.this.A);
                            videoFrameWithTransform.attributes.g(bVarE.build());
                            videoFrameWithTransform.attributes.p(true);
                        } else {
                            d.this.f23677x = null;
                        }
                    }
                }
                fo.f.g(videoFrameWithTransform, d.this.f23672s, d.this.f23671r, iD - d.this.f23670q.d());
                videoFrameWithTransform.attributes.i(s.kBt601FullRange);
                videoFrameWithTransform.attributes.n(d.this.f23662i.f23578a);
                videoFrameWithTransform.attributes.l(d.b0(d.this));
                d dVar2 = d.this;
                dVar2.f23660g.onVideoFrameCaptured(dVar2, videoFrameWithTransform);
            } catch (Exception e12) {
                CrashReporter.logCheckedException(e12, "CameraKitSession$5.class", "onImageAvailable", 92);
                d.this.d0();
                Log.e("CameraKitSession", "Camera read error = " + e12.getMessage());
                d.this.stop();
                CameraSession.a aVar = d.this.f23655b;
                CameraSession.c cVar = CameraSession.c.ERROR;
                f0 f0Var = f0.CAMERA_2_PREVIEW_EXCEPTION_FAILED;
                aVar.b(cVar, f0Var, new Exception("" + f0Var));
            }
        }
    }

    /* JADX INFO: compiled from: CameraKitSession.java */
    static /* synthetic */ class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f23681a;

        static {
            int[] iArr = new int[x.values().length];
            f23681a = iArr;
            try {
                iArr[x.kStabilizationModeAuto.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "CameraKitSession$6.class", "<clinit>", -1);
            }
            try {
                f23681a[x.kStabilizationModeEIS.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "CameraKitSession$6.class", "<clinit>", -1);
            }
            try {
                f23681a[x.kStabilizationModeOIS.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "CameraKitSession$6.class", "<clinit>", -1);
            }
            try {
                f23681a[x.kStabilizationModeOff.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "CameraKitSession$6.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: CameraKitSession.java */
    private enum h {
        IDLE,
        INITIALIZING,
        INITIALIZED,
        RELEASING,
        RELEASED,
        ERROROCCURED
    }

    protected d(d dVar, Context context, CameraSession.a aVar, CameraSession.CameraDataListener cameraDataListener, com.kwai.camerasdk.videoCapture.cameras.b bVar, com.kwai.camerasdk.videoCapture.c cVar) {
        new b(this);
        new c(this);
        this.f23653J = new C0346d(this);
        new e();
        this.f23654a = context;
        this.f23655b = aVar;
        this.f23660g = cameraDataListener;
        this.f23661h = bVar;
        this.f23662i = cVar;
        this.f23663j = new Handler();
        this.f23659f = new com.kwai.camerasdk.videoCapture.cameras.camerakit.a(this);
        this.f23658e = new com.kwai.camerasdk.videoCapture.cameras.camerakit.b(this);
        this.f23656c = new com.kwai.camerasdk.videoCapture.cameras.camerakit.c(this, cVar.f23591n);
        this.f23657d = new com.kwai.camerasdk.videoCapture.cameras.camerakit.g(this);
        this.f23668o.g(cVar.f23601x);
        this.f23668o.h(cVar.f23602y);
        Log.i("CameraKitSession", "Create CameraKitSession");
        if (dVar != null) {
            dVar.stop();
        }
        try {
            Log.i("CameraKitSession", "start to open camera");
            e0(cVar.f23578a);
            this.E = this.F.getModeCharacteristics(this.G, this.D);
            k0();
            Log.i("CameraKitSession", "Create sessionging....");
            q0();
        } catch (Exception e12) {
            CrashReporter.logCheckedException(e12, "CameraKitSession.class", "<init>", -81);
            Log.e("CameraKitSession", "Create camera failed: " + e12);
            this.f23655b.b(CameraSession.c.ERROR, f0.CAMERA_KIT_ERROR, e12);
        }
    }

    static /* synthetic */ int Y(d dVar) {
        int i12 = dVar.B;
        dVar.B = i12 + 1;
        return i12;
    }

    static /* synthetic */ int b0(d dVar) {
        int i12 = dVar.f23676w;
        dVar.f23676w = i12 + 1;
        return i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0() {
        if (Thread.currentThread() != this.f23663j.getLooper().getThread()) {
            throw new IllegalStateException("Wrong Thread");
        }
    }

    private void e0(boolean z12) throws IllegalArgumentException {
        Log.i("CameraKitSession", "choose camera");
        CameraKit cameraKit = CameraKit.getInstance(this.f23654a);
        this.F = cameraKit;
        if (cameraKit == null) {
            throw new IllegalArgumentException("This device does't not support camerakit");
        }
        cameraKit.registerCameraDeviceCallback(this.I, this.f23663j);
        String[] cameraIdList = this.F.getCameraIdList();
        if (cameraIdList == null || cameraIdList.length == 0) {
            throw new IllegalArgumentException("This device does't have avaiable camera!");
        }
        Log.i("CameraKitSession", "Current mode: " + this.D);
        for (String str : cameraIdList) {
            int[] supportedModes = this.F.getSupportedModes(str);
            Log.i("CameraKitSession", "cameraId: " + this.F.getCameraInfo(str).getFacingType() + ", supportedModes: ");
            if (com.samsung.android.sdk.camera.impl.internal.a.a(supportedModes, this.D) && ((this.F.getCameraInfo(str).getFacingType() == 0 && z12) || (this.F.getCameraInfo(str).getFacingType() == 1 && !z12))) {
                this.G = str;
                break;
            }
        }
        if (this.G == null) {
            this.G = cameraIdList[0];
        }
    }

    private void k0() {
        Log.i("CameraKitSession", "initResolution");
        l0(new ResolutionSelector(this.f23661h, fo.f.f(k()), n(), h()));
    }

    private void l0(ResolutionSelector resolutionSelector) {
        Log.i("CameraKitSession", "initResolution ResolutionSelector");
        this.f23670q = resolutionSelector.l();
        this.f23671r = resolutionSelector.h();
        this.f23672s = resolutionSelector.k();
        this.f23656c.d(resolutionSelector.j(), resolutionSelector.g(), resolutionSelector.i());
        Log.i("CameraKitSession", "initResolution resolutionRequest previewSize = " + this.f23661h.f23615b.d() + "x" + this.f23661h.f23615b.c() + " MaxPreviewSize = " + this.f23661h.f23618e + " CanCrop = " + this.f23661h.f23620g);
        if (this.f23661h.f23617d != null) {
            Log.i("CameraKitSession", "initResolution requestChangePreviewSize = " + this.f23661h.f23617d.d() + "x" + this.f23661h.f23617d.c());
        }
        Log.i("CameraKitSession", "initResolution previewSize = " + this.f23670q.d() + "x" + this.f23670q.c());
        Log.i("CameraKitSession", "initResolution previewCropSize = " + this.f23671r.d() + "x" + this.f23671r.c());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("initResolution previewScaleRatio = ");
        sb2.append(this.f23672s);
        Log.i("CameraKitSession", sb2.toString());
        Log.i("CameraKitSession", "initResolution pictureSize = " + this.f23656c.c().d() + "x" + this.f23656c.c().c());
        Log.i("CameraKitSession", "initResolution pictureCropSize = " + this.f23656c.a().d() + "x" + this.f23656c.a().c());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("initResolution pictureScaleRatio = ");
        sb3.append(this.f23656c.b());
        Log.i("CameraKitSession", sb3.toString());
    }

    private boolean m0() {
        return false;
    }

    private boolean n0() {
        return false;
    }

    private boolean o0() {
        return true;
    }

    private boolean p0() {
        return false;
    }

    private void q0() {
        d0();
        Log.i("CameraKitSession", "Opening camera");
        s0(h.INITIALIZING);
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.f23664k = jUptimeMillis;
        this.f23655b.e(jUptimeMillis);
        this.F.createMode(this.G, this.D, this.f23653J, this.f23663j);
        Log.i("CameraKitSession", "Create mode cameraid: " + this.G + " modetype:" + this.D + " statecallback: " + this.f23653J + " threadhandler: " + this.f23663j);
    }

    private void r0() {
        Log.i("CameraKitSession", "ReopenCamera");
        if (this.C != h.INITIALIZING) {
            Log.i("CameraKitSession", "StopInternal");
            u0();
            Log.i("CameraKitSession", "OpenCamra");
            q0();
        }
    }

    private void s0(h hVar) {
        Log.d("CameraKitSession", "set mode status: " + hVar);
        this.C = hVar;
    }

    private void t0() {
        Log.i("CameraKitSession", "stopCaptureSession");
        Mode mode = this.H;
        if (mode != null) {
            mode.stopPreview();
        }
    }

    private void u0() {
        d0();
        Log.i("CameraKitSession", "CameraKitSession stopping...");
        t0();
        this.F.unregisterCameraDeviceCallback(this.I);
        ImageReader imageReader = this.f23665l;
        if (imageReader != null) {
            imageReader.close();
            this.f23665l = null;
        }
        if (this.H != null) {
            Log.i("CameraKitSession", "mode.release(): " + this.H);
            this.H.release();
            this.H = null;
        }
        MediaRecorder mediaRecorder = this.f23674u;
        if (mediaRecorder != null) {
            mediaRecorder.release();
            this.f23674u = null;
        }
        this.f23656c.f();
        i iVar = this.f23668o;
        if (iVar != null) {
            iVar.b();
        }
        Log.i("CameraKitSession", "CameraKitSession stop done");
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public com.kwai.camerasdk.utils.f C() {
        return this.f23656c.a();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public float D() {
        return 4.6f;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void E(com.kwai.camerasdk.utils.f fVar) {
        Log.i("CameraKitSession", "update preview resolution: " + fVar);
        this.f23661h.f23617d = fVar;
        k0();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public boolean F() {
        ModeCharacteristics modeCharacteristics = this.E;
        if (modeCharacteristics == null || modeCharacteristics.getSupportedParameters() == null) {
            return false;
        }
        return this.E.getSupportedParameters().contains(RequestKey.HW_SENSOR_HDR);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void H(boolean z12) {
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void J(int i12, int i13, int i14) {
        this.f23661h.f23615b = new com.kwai.camerasdk.utils.f(i12, i13);
        this.f23661h.f23618e = i14;
        ResolutionSelector resolutionSelector = new ResolutionSelector(this.f23661h, fo.f.f(k()), n(), h());
        boolean z12 = (this.f23670q == null || resolutionSelector.l() == null || this.f23670q.equals(resolutionSelector.l())) ? false : true;
        l0(resolutionSelector);
        if (z12) {
            Log.i("CameraKitSession", "Restart capture sessoion due to resetRequestPreviewSize width: " + i12 + ", height: " + i13 + ", maxSize: " + i14);
            r0();
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public com.kwai.camerasdk.utils.f K() {
        return this.f23671r;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void P(CameraSession.b bVar) {
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public float Q() {
        if (com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.c()) {
            return this.f23662i.f23578a ? 79.93243f : 66.37915f;
        }
        if (com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.d()) {
            return this.f23662i.f23578a ? 75.29524f : 66.56778f;
        }
        return 65.0f;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public boolean a() {
        return F();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void c(m mVar, x xVar, boolean z12) {
        if (z12 == isFrontCamera() && xVar != this.f23662i.f23586i) {
            int i12 = f.f23681a[xVar.ordinal()];
            if (i12 != 1) {
                if (i12 != 2) {
                    if (i12 != 3) {
                        if (i12 != 4) {
                            return;
                        }
                    } else if (!m0() && !p0()) {
                        return;
                    }
                } else if (!n0() && !o0()) {
                    return;
                }
            } else if (!n0() && !o0() && !p0()) {
                return;
            }
            com.kwai.camerasdk.videoCapture.c cVar = this.f23662i;
            cVar.f23586i = xVar;
            if (cVar.f23582e) {
                r0();
            }
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void d(boolean z12) {
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public int f() {
        return 30;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    @e.a
    /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
    public com.kwai.camerasdk.videoCapture.cameras.camerakit.a O() {
        return this.f23659f;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void g(FrameMonitor frameMonitor) {
        this.f23678y = new WeakReference<>(frameMonitor);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    @e.a
    /* JADX INFO: renamed from: g0, reason: merged with bridge method [inline-methods] */
    public com.kwai.camerasdk.videoCapture.cameras.camerakit.b L() {
        return this.f23658e;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public com.kwai.camerasdk.utils.f[] h() {
        List supportedCaptureSizes = this.E.getSupportedCaptureSizes(256);
        com.kwai.camerasdk.utils.f[] fVarArr = new com.kwai.camerasdk.utils.f[supportedCaptureSizes.size()];
        for (int i12 = 0; i12 < supportedCaptureSizes.size(); i12++) {
            Size size = (Size) supportedCaptureSizes.get(i12);
            fVarArr[i12] = new com.kwai.camerasdk.utils.f(size.getWidth(), size.getHeight());
        }
        return fVarArr;
    }

    protected int h0() {
        int iIntValue;
        int iD = fo.f.d(this.f23654a);
        boolean z12 = this.f23662i.f23578a;
        if (!z12) {
            iD = 360 - iD;
        }
        ModeCharacteristics modeCharacteristics = this.E;
        if (modeCharacteristics == null) {
            iIntValue = z12 ? 270 : 90;
        } else {
            iIntValue = ((Integer) modeCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        }
        return (iIntValue + iD) % 360;
    }

    public Matrix i0(com.kwai.camerasdk.utils.f fVar, c0 c0Var) {
        return com.kwai.camerasdk.videoCapture.cameras.camerakit.e.a(this.E, this.f23662i.f23578a, fo.f.d(this.f23654a), k(), fVar, this.f23670q, this.f23671r, c0Var, new Rect(KwaiLinkCode.CODE_TIME_OUT, KwaiLinkCode.CODE_TIME_OUT, 1000, 1000));
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public boolean isFrontCamera() {
        return this.f23662i.f23578a;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void j(boolean z12) {
        com.kwai.camerasdk.videoCapture.c cVar = this.f23662i;
        if (z12 == cVar.f23582e) {
            return;
        }
        cVar.f23582e = z12;
        int i12 = f.f23681a[cVar.f23586i.ordinal()];
        if (i12 != 1) {
            if (i12 != 2) {
                if (i12 != 3) {
                    return;
                }
                if (!m0() && !p0()) {
                    return;
                }
            } else if (!n0() && !o0()) {
                return;
            }
        } else if (!n0() && !o0() && !p0()) {
            return;
        }
        Log.i("CameraKitSession", "Restart capture session due to stabilization changed: " + z12);
        r0();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    @e.a
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public com.kwai.camerasdk.videoCapture.cameras.camerakit.g p() {
        return this.f23657d;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public int k() {
        return ((Integer) this.E.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public boolean l(int i12, int i13) {
        return true;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void m(boolean z12) {
        this.f23662i.f23579b = z12;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public com.kwai.camerasdk.utils.f[] n() {
        ModeCharacteristics modeCharacteristics = this.E;
        if (modeCharacteristics == null) {
            Log.e("CameraKitSession", "getPreviewSizes in wrong state");
            return new com.kwai.camerasdk.utils.f[0];
        }
        List supportedPreviewSizes = modeCharacteristics.getSupportedPreviewSizes(SurfaceTexture.class);
        com.kwai.camerasdk.utils.f[] fVarArr = new com.kwai.camerasdk.utils.f[supportedPreviewSizes.size()];
        for (int i12 = 0; i12 < supportedPreviewSizes.size(); i12++) {
            fVarArr[i12] = new com.kwai.camerasdk.utils.f(((Size) supportedPreviewSizes.get(i12)).getWidth(), ((Size) supportedPreviewSizes.get(i12)).getHeight());
        }
        return fVarArr;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void o(CameraController.e eVar, boolean z12) {
        if (this.f23662i.f23596s || !this.f23656c.e() || this.f23656c.g(eVar)) {
            return;
        }
        x(0L, 0);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public com.kwai.camerasdk.utils.f[] q() {
        Map supportedVideoSizes;
        List list;
        ModeCharacteristics modeCharacteristics = this.E;
        if (modeCharacteristics == null || (supportedVideoSizes = modeCharacteristics.getSupportedVideoSizes(MediaRecorder.class)) == null || !supportedVideoSizes.containsKey(30) || (list = (List) supportedVideoSizes.get(30)) == null || list.size() <= 0) {
            return new com.kwai.camerasdk.utils.f[0];
        }
        com.kwai.camerasdk.utils.f[] fVarArr = new com.kwai.camerasdk.utils.f[list.size()];
        for (int i12 = 0; i12 < list.size(); i12++) {
            fVarArr[i12] = new com.kwai.camerasdk.utils.f(((Size) list.get(i12)).getWidth(), ((Size) list.get(i12)).getHeight());
        }
        return fVarArr;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void r(n nVar) {
        com.kwai.camerasdk.videoCapture.cameras.camerakit.g gVar;
        if (nVar == n.kCaptureDeviceTypeBuiltInWideAngleCamera) {
            com.kwai.camerasdk.videoCapture.cameras.camerakit.g gVar2 = this.f23657d;
            if (gVar2 != null) {
                gVar2.setZoom(1.0f);
                return;
            }
            return;
        }
        if (nVar != n.kCaptureDeviceTypeBuiltInUltraWideCamera || (gVar = this.f23657d) == null) {
            return;
        }
        gVar.setZoom(gVar.getMinZoom());
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public y s() {
        return this.f23675v;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void stop() {
        u0();
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void t(int i12, int i13, boolean z12) {
        com.kwai.camerasdk.utils.f fVar = new com.kwai.camerasdk.utils.f(i12, i13);
        if (fVar.equals(this.f23661h.f23616c)) {
            Log.e("CameraKitSession", "the same picture config");
            return;
        }
        this.f23661h.f23616c = fVar;
        ResolutionSelector resolutionSelector = new ResolutionSelector(this.f23661h, fo.f.f(k()), n(), h());
        boolean z13 = false;
        if (this.f23656c.c() != null && resolutionSelector.j() != null && !this.f23656c.c().equals(resolutionSelector.j())) {
            z13 = true;
        }
        l0(resolutionSelector);
        if (z13) {
            Log.i("CameraKitSession", "Restart capture sessoion due to updateRequestPictureConfig width: " + i12 + ", height: " + i13);
            r0();
        }
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public com.kwai.camerasdk.utils.f u() {
        return this.f23670q;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void v(boolean z12) {
        List supportedParameters = this.E.getSupportedParameters();
        if (supportedParameters == null || !supportedParameters.contains(RequestKey.HW_SENSOR_HDR)) {
            Log.e("CameraKitSession", "Do not support hdr.");
            return;
        }
        this.H.setParameter(RequestKey.HW_SENSOR_HDR, Boolean.valueOf(z12));
        Log.e("CameraKitSession", "Set enable hdr: " + z12);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public n w() {
        com.kwai.camerasdk.videoCapture.cameras.camerakit.g gVar = this.f23657d;
        if (gVar == null) {
            return n.kCaptureDeviceTypeBuiltInWideAngleCamera;
        }
        return ((double) gVar.getZoom()) >= 1.0d ? n.kCaptureDeviceTypeBuiltInWideAngleCamera : n.kCaptureDeviceTypeBuiltInUltraWideCamera;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public void x(long j12, int i12) {
        g gVar = new g(this, null);
        this.f23677x = gVar;
        gVar.f23682a = com.kwai.camerasdk.utils.h.a() + j12;
        this.A = i12;
        this.B = 0;
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public boolean y(int i12, int i13) {
        com.kwai.camerasdk.videoCapture.c cVar = this.f23662i;
        cVar.f23581d = i12;
        cVar.f23580c = i13;
        return l(i12, i13);
    }

    @Override // com.kwai.camerasdk.videoCapture.CameraSession
    public boolean z() {
        return false;
    }

    /* JADX INFO: compiled from: CameraKitSession.java */
    private class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f23682a;

        private g(d dVar) {
            this.f23682a = 0L;
        }

        /* synthetic */ g(d dVar, a aVar) {
            this(dVar);
        }
    }
}
