package com.kwai.camerasdk;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import com.kwai.camerasdk.audio.AudioFrame;
import com.kwai.camerasdk.debugtools.DebugInfoView;
import com.kwai.camerasdk.encoder.MediaCodecEncoder;
import com.kwai.camerasdk.face.FaceDetectorContext;
import com.kwai.camerasdk.log.DaenerysLogObserver;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.mediarecorder.MediaRecorderImpl;
import com.kwai.camerasdk.mediarecorder.RecordingStatesListener;
import com.kwai.camerasdk.monitor.FrameMonitor;
import com.kwai.camerasdk.preprocess.GlPreProcessorGroup;
import com.kwai.camerasdk.render.NativeRenderThread;
import com.kwai.camerasdk.render.OpengGL.EglBase;
import com.kwai.camerasdk.render.VideoSurfaceView;
import com.kwai.camerasdk.render.view2.NativeRenderThread2;
import com.kwai.camerasdk.stats.StatsHolder;
import com.kwai.camerasdk.stats.StatsListener;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.camerasdk.videoCapture.CameraController;
import com.kwai.camerasdk.videoCapture.DaenerysFrameObserver;
import com.yxcorp.bugly.CrashReporter;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import p000do.e0;
import p000do.f1;
import p000do.h0;
import p000do.j0;
import p000do.r0;
import p000do.t0;
import p000do.u;
import p000do.z;
import p000do.z0;

/* JADX INFO: loaded from: classes15.dex */
public class Daenerys implements co.b {
    private static FrameMonitor D;
    yn.d B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WeakReference<Context> f23766a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f23767b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DaenerysFrameObserver f23768c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private z f23769d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private u f23770e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.kwai.camerasdk.mediarecorder.d f23771f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final com.kwai.camerasdk.mediarecorder.b f23772g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final StatsHolder f23773h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private EglBase f23774i;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private DaenerysLayoutManager f23779n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private com.kwai.camerasdk.c f23780o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final Handler f23781p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private FaceDetectorContext f23782q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f23783r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private StatsListener f23784t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private DebugInfoView f23785u;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f23775j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private HashMap<Integer, eo.e> f23776k = new HashMap<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final Object f23777l = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f23778m = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f23786w = false;
    StatsListener C = new b();

    @Keep
    public static class LogParam {
        public String filePath;
        public int logLevel = 0;
        public boolean isConsoleEnable = true;
        public boolean isFileEnable = false;
        public int maxFileSize = 5242880;
        public int maxFileNum = 3;
        public DaenerysLogObserver logCb = null;
        public long nativeLogFunctionPtr = 0;
    }

    class a implements com.kwai.camerasdk.mediarecorder.d.a {
        a() {
        }

        @Override // com.kwai.camerasdk.mediarecorder.d.a
        public void a(boolean z12) {
            yn.d dVar = Daenerys.this.B;
            if (dVar != null) {
                dVar.h(z12);
            }
        }

        @Override // com.kwai.camerasdk.mediarecorder.d.a
        public void b(String str) {
            yn.d dVar = Daenerys.this.B;
            if (dVar != null) {
                dVar.g(str);
            }
        }
    }

    class b implements StatsListener {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            Daenerys.this.f23785u.j(Daenerys.this.B);
        }

        @Override // com.kwai.camerasdk.stats.StatsListener
        public void OnPipelineInfo(String str) {
            Daenerys.this.B.d(str);
            if (Daenerys.this.f23784t != null) {
                Daenerys.this.f23784t.OnPipelineInfo(str);
            }
        }

        @Override // com.kwai.camerasdk.stats.StatsListener
        public void onDebugInfo(String str) {
            yn.d dVar = Daenerys.this.B;
            if (dVar != null) {
                dVar.c(str);
            }
            if (Daenerys.this.f23784t != null) {
                Daenerys.this.f23784t.onDebugInfo(str);
            }
        }

        @Override // com.kwai.camerasdk.stats.StatsListener
        public void onPreviewStats(z0 z0Var) {
            if (Daenerys.this.f23784t != null) {
                Daenerys.this.f23784t.onPreviewStats(z0Var);
            }
        }

        @Override // com.kwai.camerasdk.stats.StatsListener
        public void onReportJsonStats(String str) {
            yn.d dVar = Daenerys.this.B;
            if (dVar != null) {
                dVar.e(str);
            }
            if (!Daenerys.this.f23786w && Daenerys.this.f23785u != null) {
                Daenerys.this.f23786w = true;
                Daenerys.this.f23785u.post(new Runnable() { // from class: com.kwai.camerasdk.l
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f23899a.b();
                    }
                });
            }
            if (Daenerys.this.f23784t != null) {
                Daenerys.this.f23784t.onReportJsonStats(str);
            }
        }

        @Override // com.kwai.camerasdk.stats.StatsListener
        public void onSessionSegmentStats(f1 f1Var) {
            yn.d dVar = Daenerys.this.B;
            if (dVar != null) {
                dVar.f(f1Var);
            }
            if (Daenerys.this.f23784t != null) {
                Daenerys.this.f23784t.onSessionSegmentStats(f1Var);
            }
        }
    }

    class c implements Runnable {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            Daenerys.D.c();
            Daenerys.this.f23773h.dispose();
            Daenerys.this.f23768c.d();
            Daenerys daenerys = Daenerys.this;
            daenerys.nativeDestroy(daenerys.f23767b);
            Daenerys.this.f23767b = 0L;
            Daenerys.this.f23768c.a();
            Log.i("Daenerys", "Daenerys successfully disposed.");
        }

        @Override // java.lang.Runnable
        public void run() {
            Daenerys.this.T(new Runnable() { // from class: com.kwai.camerasdk.m
                @Override // java.lang.Runnable
                public final void run() {
                    this.f23924a.b();
                }
            });
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (Daenerys.this.f23774i != null) {
                    Daenerys.this.f23774i.l();
                }
                if (Daenerys.this.f23785u != null && Daenerys.this.f23786w) {
                    Daenerys.this.f23785u.l();
                    Daenerys.this.f23786w = false;
                }
            } catch (RuntimeException e12) {
                CrashReporter.logCheckedException(e12, "Daenerys$5.class", "run", -61);
                Log.e("Daenerys", "release exception " + e12.toString());
            }
            Daenerys.this.f23781p.getLooper().quit();
        }
    }

    class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Runnable f23791a;

        e(Runnable runnable) {
            this.f23791a = runnable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            Daenerys.this.f23773h.dispose();
            Daenerys daenerys = Daenerys.this;
            daenerys.nativeDestroy(daenerys.f23767b);
            Daenerys.this.f23767b = 0L;
            Daenerys.this.f23768c.a();
            Log.i("Daenerys", "Daenerys successfully disposed.");
        }

        @Override // java.lang.Runnable
        public void run() {
            Daenerys.this.T(new Runnable() { // from class: com.kwai.camerasdk.n
                @Override // java.lang.Runnable
                public final void run() {
                    this.f23956a.b();
                }
            });
            Runnable runnable = this.f23791a;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (Daenerys.this.f23774i != null) {
                    Daenerys.this.f23774i.l();
                }
            } catch (RuntimeException e12) {
                CrashReporter.logCheckedException(e12, "Daenerys$7.class", "run", -11);
                Log.e("Daenerys", "release exception " + e12.toString());
            }
            Daenerys.this.f23781p.getLooper().quit();
        }
    }

    static {
        com.kwai.camerasdk.utils.a.b();
    }

    public Daenerys(@e.a Context context, @e.a z zVar, EglBase.Context context2) {
        EglBase.Context contextI;
        this.f23767b = 0L;
        z zVarY = y(zVar);
        this.f23766a = new WeakReference<>(context);
        boolean zD0 = zVarY.d0();
        if (zD0) {
            contextI = null;
        } else {
            try {
                EglBase eglBaseB = EglBase.b(context2);
                this.f23774i = eglBaseB;
                contextI = eglBaseB.i();
            } catch (RuntimeException e12) {
                CrashReporter.logCheckedException(e12, "Daenerys.class", "<init>", -97);
                Log.e("Daenerys", "create EGL context error:" + e12.getMessage());
                contextI = null;
            }
        }
        if (context2 != null && contextI != null) {
            contextI.setCreateEGLFailListener(context2.getCreateEGLFailListener());
        }
        this.f23769d = zVarY;
        this.f23783r = zVarY.f0();
        MediaCodecEncoder.enableColorSpaceSetting = zVarY.c0();
        HandlerThread handlerThread = new HandlerThread("DaenerysMainThread");
        handlerThread.start();
        this.f23781p = new Handler(handlerThread.getLooper());
        synchronized (this) {
            try {
                long jNativeStormBorn = nativeStormBorn(context, contextI, zD0);
                this.f23767b = jNativeStormBorn;
                nativeInit(jNativeStormBorn, zVarY.toByteArray(), context);
                nativeSetAssetManager(this.f23767b, context.getAssets());
                StatsHolder statsHolder = new StatsHolder(this.f23767b, context.getApplicationContext());
                this.f23773h = statsHolder;
                statsHolder.setListener(this.C);
                this.f23768c = new DaenerysFrameObserver(this.f23767b);
                this.f23771f = new com.kwai.camerasdk.mediarecorder.d(nativeGetMediaRecorder(this.f23767b, t0.kMain.getNumber()), zVarY);
                this.f23772g = new MediaRecorderImpl(nativeGetMediaRecorder(this.f23767b, t0.kRaw.getNumber()));
                this.f23780o = new com.kwai.camerasdk.c(this.f23771f);
                this.f23779n = new DaenerysLayoutManager(this.f23767b);
                D = new FrameMonitor(this.f23767b, zVarY.b0());
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Daenerys.class", "<init>", -1);
                throw th2;
            }
        }
    }

    public static FrameMonitor D() {
        return D;
    }

    private void I() {
        if (com.kwai.camerasdk.videoCapture.cameras.camera2.vendor.a.f24222h) {
            h0 h0VarBuild = h0.h().e(false).d(j0.kTrackingFast).b(60.0f).a(0.0f).c(this.f23769d.g0()).build();
            FaceDetectorContext faceDetectorContext = this.f23782q;
            if (faceDetectorContext != null) {
                faceDetectorContext.getVideoFaceDetector().b(h0VarBuild);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(com.kwai.camerasdk.preprocess.a aVar, r0 r0Var) {
        x(aVar, r0Var, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(com.kwai.camerasdk.preprocess.a aVar, r0 r0Var, boolean z12) {
        if (aVar instanceof GlPreProcessorGroup) {
            nativeAddGLPreProcessorGroupAtGroup(this.f23767b, ((GlPreProcessorGroup) aVar).getNativeGroup(), r0Var.getNumber(), z12, false);
        } else {
            nativeAddGLPreProcessorAtGroup(this.f23767b, aVar.getNativeProcessor(), r0Var.getNumber(), z12, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L() {
        nativeExecuteRenderThreadRunnable(this.f23767b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M() {
        nativeResume(this.f23767b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N() {
        nativeResume(this.f23767b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(CameraController cameraController) {
        nativeSetCameraController(this.f23767b, cameraController.getNativeCameraController());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(FaceDetectorContext faceDetectorContext, int i12) {
        nativeSetFaceDetectorContext(this.f23767b, faceDetectorContext.getNativeContext(), i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Q(int i12) {
        nativeSetFaceDetectorContext(this.f23767b, 0L, i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(Runnable runnable) {
        synchronized (this) {
            try {
                if (this.f23767b != 0) {
                    runnable.run();
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Daenerys.class", "safeCallNativeMethod", -1);
                throw th2;
            }
        }
    }

    public static void a0(LogParam logParam) {
        Log.setLogParam(logParam);
    }

    private native void nativeAddAudioProcessor(long j12, long j13, boolean z12);

    private native void nativeAddExternalProcessorAtGroup(long j12, long j13, int i12, boolean z12, boolean z13);

    private native void nativeAddGLPreProcessorAtGroup(long j12, long j13, int i12, boolean z12, boolean z13);

    private native void nativeAddGLPreProcessorGroupAtGroup(long j12, long j13, int i12, boolean z12, boolean z13);

    private native long nativeCreateGlProcessorGroup(long j12);

    private native void nativeCreateSubPipeline(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDestroy(long j12);

    private native void nativeExecuteRenderThreadRunnable(long j12);

    private native long nativeGetMediaRecorder(long j12, int i12);

    private native long nativeGetRenderThread(long j12, int i12);

    private native long nativeGetSubPipelineCount(long j12);

    private static native String nativeGetVersion();

    private native void nativeInit(long j12, byte[] bArr, Object obj);

    private native void nativeInsertGLPreProcessorGroupAtSlot(long j12, long j13, int i12);

    private native void nativePause(long j12);

    private native String nativeProbeRuntimeConnection(long j12);

    private native void nativeReclaimMemory(long j12);

    private native void nativeRemoveAudioProcessor(long j12, long j13);

    private native void nativeRemoveExternalProcessorFromGroup(long j12, long j13, int i12);

    private native void nativeRemoveGLPreProcessorFromGroup(long j12, long j13, int i12);

    private native void nativeRemoveGLPreProcessorGroupAtSlot(long j12, long j13, int i12);

    private native void nativeRemoveGLPreProcessorGroupFromGroup(long j12, long j13, int i12);

    private native void nativeResume(long j12);

    private native void nativeSetAssetManager(long j12, AssetManager assetManager);

    private native void nativeSetBlackImageCheckerCallback(long j12, BlackImageCheckerCallback blackImageCheckerCallback);

    private native void nativeSetCameraController(long j12, long j13);

    private native void nativeSetFaceDetectorContext(long j12, long j13, int i12);

    private native void nativeSetFrameRateAdapterCallback(long j12, FrameRateAdapterCallback frameRateAdapterCallback);

    private native void nativeSetFrameResolutionLocked(long j12, boolean z12);

    private native void nativeSetLimitedResolutionEnabled(long j12, boolean z12);

    private native void nativeSetMediaCallback(long j12, int i12, int i13, int i14, int i15, MediaCallback mediaCallback);

    private native void nativeSetStopBlackImageCheck(long j12, boolean z12);

    private native void nativeSetSyncRenderThread(long j12, boolean z12);

    private native void nativeSetTargetFps(long j12, int i12);

    private native void nativeSetVideoSourceConverter(long j12, VideoSourceConverter videoSourceConverter);

    private native long nativeStormBorn(Object obj, Object obj2);

    private native long nativeStormBorn(Object obj, Object obj2, boolean z12);

    private native void nativeWaitForSyncRenderThread(long j12, int i12);

    private z y(z zVar) {
        boolean z12;
        try {
            z.b bVarS0 = z.s0(zVar);
            boolean z13 = false;
            if (zVar.n0() <= 0) {
                bVarS0.Q(30);
                Log.e("Daenerys", "config error: config.targetFps <= 0");
                z12 = false;
            } else {
                z12 = true;
            }
            if (zVar.h0() <= 0) {
                bVarS0.F(30);
                Log.e("Daenerys", "config error: HardwareRecordingTargetFps invalid");
                z12 = false;
            }
            if (zVar.k0() <= 0) {
                bVarS0.M(20);
                Log.e("Daenerys", "config error: SoftwareRecordingTargetFps invalid");
                z12 = false;
            }
            if (zVar.m0() < 0) {
                bVarS0.O(0);
                Log.e("Daenerys", "config error: onfig.subPipelineCount < 0");
                z12 = false;
            }
            if (zVar.V() <= 0) {
                bVarS0.g(44100);
                Log.e("Daenerys", "config error: config.audioSampleRate <= 0");
            } else {
                z13 = z12;
            }
            if (!z13) {
                return bVarS0.build();
            }
            Log.i("Daenerys", "config valid!");
            return zVar;
        } catch (Exception e12) {
            CrashReporter.logCheckedException(e12, "Daenerys.class", "checkDaenerysConfigValid", -118);
            Log.e("Daenerys", "checkDaenerysConfigValid failed! return original config");
            return zVar;
        }
    }

    public void A(Runnable runnable) {
        if (this.f23775j) {
            return;
        }
        Log.i("Daenerys", "dispose: ");
        synchronized (this.f23777l) {
            try {
                for (eo.e eVar : this.f23776k.values()) {
                    if (eVar != null) {
                        eVar.release();
                    }
                }
                this.f23776k.clear();
                this.f23778m = true;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Daenerys.class", "dispose", -1);
                throw th2;
            }
        }
        e eVar2 = new e(runnable);
        if (this.f23783r) {
            eVar2.run();
        } else {
            this.f23781p.post(eVar2);
        }
        this.f23781p.post(new f());
        this.f23775j = true;
    }

    public void B() {
        if (this.f23775j) {
            return;
        }
        T(new Runnable() { // from class: com.kwai.camerasdk.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f23821a.L();
            }
        });
    }

    public com.kwai.camerasdk.c C() {
        return this.f23780o;
    }

    public DaenerysLayoutManager E() {
        return this.f23779n;
    }

    public com.kwai.camerasdk.mediarecorder.b F() {
        return this.f23771f;
    }

    public long G() {
        return this.f23767b;
    }

    public StatsHolder H() {
        return this.f23773h;
    }

    public void R() {
        Log.i("Daenerys", "onPause");
        FaceDetectorContext faceDetectorContext = this.f23782q;
        if (faceDetectorContext != null) {
            faceDetectorContext.onPause();
        }
        T(new Runnable() { // from class: com.kwai.camerasdk.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f23880a.M();
            }
        });
    }

    public void S() {
        Log.i("Daenerys", "onResume");
        FaceDetectorContext faceDetectorContext = this.f23782q;
        if (faceDetectorContext != null) {
            faceDetectorContext.onResume();
        }
        T(new Runnable() { // from class: com.kwai.camerasdk.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f23883a.N();
            }
        });
    }

    public void U(@e.a BlackImageCheckerCallback blackImageCheckerCallback) {
        if (this.f23775j) {
            return;
        }
        nativeSetBlackImageCheckerCallback(this.f23767b, blackImageCheckerCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void V(@e.a final CameraController cameraController) {
        Log.i("Daenerys", "setCameraMediaSource");
        cameraController.addSink(this);
        this.f23770e = cameraController.getDaenerysCaptureConfig();
        new WeakReference(cameraController);
        this.f23771f.setStatesListener((RecordingStatesListener) cameraController);
        this.f23771f.b(new a());
        this.f23772g.setStatesListener(null);
        cameraController.setStats(this.f23773h);
        cameraController.setFrameMonitor(D);
        this.f23780o.l(cameraController);
        T(new Runnable() { // from class: com.kwai.camerasdk.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f23897a.O(cameraController);
            }
        });
    }

    public void W(eo.d dVar) {
        DebugInfoView debugInfoView = new DebugInfoView(this.f23766a.get());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        debugInfoView.setLayoutParams(layoutParams);
        ((ViewGroup) dVar.getView().getParent()).addView(debugInfoView);
        this.f23785u = debugInfoView;
        yn.d dVar2 = new yn.d();
        this.B = dVar2;
        dVar2.a(this.f23769d);
        this.B.b(this.f23770e);
    }

    public synchronized void X(final FaceDetectorContext faceDetectorContext, final int i12) {
        Log.i("Daenerys", "setFaceDetectorContext");
        this.f23782q = faceDetectorContext;
        if (faceDetectorContext == null || faceDetectorContext.isDisposed()) {
            T(new Runnable() { // from class: com.kwai.camerasdk.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f23885a.Q(i12);
                }
            });
        } else {
            synchronized (this.f23782q.getLock()) {
                try {
                    T(new Runnable() { // from class: com.kwai.camerasdk.h
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f23887a.P(faceDetectorContext, i12);
                        }
                    });
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "Daenerys.class", "setFaceDetectorContext", -1);
                    throw th2;
                }
            }
            I();
        }
    }

    public void Y(@e.a FrameRateAdapterCallback frameRateAdapterCallback) {
        if (this.f23775j) {
            return;
        }
        nativeSetFrameRateAdapterCallback(this.f23767b, frameRateAdapterCallback);
    }

    public void Z(boolean z12) {
        if (this.f23775j) {
            return;
        }
        nativeSetFrameResolutionLocked(this.f23767b, z12);
    }

    @Override // co.b
    public void a(co.a aVar) {
        if (this.f23775j || aVar == null) {
            return;
        }
        if (aVar.mediaType() == 0) {
            this.f23768c.c((VideoFrame) aVar);
        } else if (aVar.mediaType() == 1) {
            this.f23768c.b((AudioFrame) aVar);
        }
    }

    public void b0(int i12, int i13, int i14, @e.a MediaCallback mediaCallback) {
        c0(i12, i13, i14, e0.kDownSamplerTypeUnknow, mediaCallback);
    }

    public void c0(int i12, int i13, int i14, e0 e0Var, @e.a MediaCallback mediaCallback) {
        Log.i("Daenerys", "setMediaCallback fps = " + i12 + " maxWidth = " + i13 + " maxHeight = " + i14);
        if (this.f23775j) {
            return;
        }
        nativeSetMediaCallback(this.f23767b, i12, i13, i14, e0Var.getNumber(), mediaCallback);
    }

    public void d0(VideoSurfaceView videoSurfaceView) {
        Log.i("Daenerys", "setPreviewVideoSurfaceView");
        e0(videoSurfaceView);
    }

    public void e0(eo.d dVar) {
        f0(dVar, 0);
    }

    public void f0(eo.d dVar, int i12) {
        if (this.f23775j) {
            return;
        }
        synchronized (this.f23777l) {
            try {
                if (this.f23778m) {
                    Log.i("Daenerys", "setPreviewVideoView renderThreadDisposed");
                    return;
                }
                if (this.f23776k.get(Integer.valueOf(i12)) != null) {
                    this.f23776k.get(Integer.valueOf(i12)).release();
                    this.f23776k.remove(Integer.valueOf(i12));
                }
                if (dVar == null) {
                    Log.i("Daenerys", "setPreviewVideoView videoView == null");
                } else if (this.f23769d.e0()) {
                    Log.i("Daenerys", "setPreviewVideoView using RenderThread2");
                    NativeRenderThread2 nativeRenderThread2 = new NativeRenderThread2(nativeGetRenderThread(this.f23767b, i12), this.f23774i.i());
                    dVar.setRenderThread(nativeRenderThread2);
                    this.f23776k.put(Integer.valueOf(i12), nativeRenderThread2);
                } else {
                    Log.i("Daenerys", "setPreviewVideoView using RenderThread");
                    NativeRenderThread nativeRenderThread = new NativeRenderThread(nativeGetRenderThread(this.f23767b, i12));
                    dVar.setRenderThread(nativeRenderThread);
                    this.f23776k.put(Integer.valueOf(i12), nativeRenderThread);
                }
                this.f23780o.m(dVar);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Daenerys.class", "setPreviewVideoView", -1);
                throw th2;
            }
        }
    }

    public void g0(StatsListener statsListener) {
        this.f23784t = statsListener;
    }

    public void h0(boolean z12) {
        if (this.f23775j) {
            return;
        }
        nativeSetStopBlackImageCheck(this.f23767b, z12);
    }

    public void i0() {
        j0(0);
    }

    public void j0(int i12) {
        if (this.f23775j) {
            return;
        }
        nativeWaitForSyncRenderThread(this.f23767b, i12);
    }

    public void w(final com.kwai.camerasdk.preprocess.a aVar, final r0 r0Var) {
        T(new Runnable() { // from class: com.kwai.camerasdk.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f23890a.J(aVar, r0Var);
            }
        });
    }

    public void x(final com.kwai.camerasdk.preprocess.a aVar, final r0 r0Var, final boolean z12) {
        if (this.f23775j) {
            return;
        }
        T(new Runnable() { // from class: com.kwai.camerasdk.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f23893a.K(aVar, r0Var, z12);
            }
        });
    }

    public void z() {
        if (this.f23775j) {
            return;
        }
        Log.i("Daenerys", "dispose: ");
        synchronized (this.f23777l) {
            try {
                for (eo.e eVar : this.f23776k.values()) {
                    if (eVar != null) {
                        eVar.release();
                    }
                }
                this.f23776k.clear();
                this.f23778m = true;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Daenerys.class", "dispose", -1);
                throw th2;
            }
        }
        c cVar = new c();
        if (this.f23783r) {
            cVar.run();
        } else {
            this.f23781p.post(cVar);
        }
        this.f23781p.post(new d());
        this.f23775j = true;
    }
}
