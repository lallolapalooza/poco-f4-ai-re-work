package com.kwai.video.arya;

import android.content.Context;
import android.media.projection.MediaProjection;
import android.os.Handler;
import android.os.HandlerThread;
import com.kwai.video.arya.observers.AudioBufferPlayObserver;
import com.kwai.video.arya.observers.AudioSceneObserver;
import com.kwai.video.arya.observers.RawAudioObserver;
import com.kwai.video.arya.utils.Log;
import com.kwai.video.stannis.Stannis;
import com.kwai.video.stannis.Stannis.KWStannisServerConfig;
import com.kwai.video.stannis.StannisDeviceInfo;
import com.kwai.video.stannis.observers.AudioSegmentPlayerObserver;
import com.kwai.video.stannis.observers.BgmObserver;
import com.kwai.video.stannis.observers.DataReadyObserver;
import com.kwai.video.stannis.observers.KaraokeScoreObserver;
import com.kwai.video.stannis.observers.StannisAudioDeviceStatusObserver;
import com.kwai.video.stannis.observers.StannisAudioSceneObserver;
import com.kwai.video.stannis.observers.StannisNotifyObserver;
import com.yxcorp.bugly.CrashReporter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes8.dex */
public class AryaAudioEngineProxyImp extends com.kwai.video.arya.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Stannis f28516a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private HandlerThread f28520e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Handler f28521f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private HashMap<com.kwai.video.arya.a.C0440a, b> f28526k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private ArrayList<Integer> f28529n;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private HashMap<AudioSceneObserver, StannisAudioSceneObserver> f28533r;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f28517b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f28518c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f28519d = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f28522g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f28523h = 512;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private long f28524i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private long f28525j = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private DataReadyObserver f28527l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f28528m = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private float f28530o = 1.0f;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private Context f28531p = null;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private StannisNotifyObserver f28532q = null;

    /* JADX INFO: renamed from: com.kwai.video.arya.AryaAudioEngineProxyImp$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f28534a;

        AnonymousClass1(Context context) {
            this.f28534a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            AryaAudioEngineProxyImp.this.f28531p = this.f28534a;
            AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
            aryaAudioEngineProxyImp.f28524i = aryaAudioEngineProxyImp.nativeCreateStannisDataReceiver();
            AryaAudioEngineProxyImp aryaAudioEngineProxyImp2 = AryaAudioEngineProxyImp.this;
            aryaAudioEngineProxyImp2.f28525j = aryaAudioEngineProxyImp2.nativeCreateStannisDataProvider();
            AryaAudioEngineProxyImp.this.f28532q = new StannisNotifyObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.1.1
                @Override // com.kwai.video.stannis.observers.StannisNotifyObserver
                public void onNotify(final int i12) {
                    AryaAudioEngineProxyImp.this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            com.kwai.video.arya.a.e eVar;
                            int i13 = i12;
                            int i14 = 52;
                            if (i13 == 9) {
                                i14 = 9;
                            } else if (i13 == 35) {
                                i14 = 35;
                            } else if (i13 != 52) {
                                i14 = -1;
                            }
                            if (i14 != -1) {
                                for (com.kwai.video.arya.a.C0440a c0440a : AryaAudioEngineProxyImp.this.f28526k.keySet()) {
                                    b bVar = (b) AryaAudioEngineProxyImp.this.f28526k.get(c0440a);
                                    if (c0440a != null && (eVar = c0440a.f28835a) != null && bVar != null && !bVar.f28750a) {
                                        eVar.onNotify(i14);
                                    }
                                }
                            }
                        }
                    });
                }
            };
            if (AryaAudioEngineProxyImp.this.f28517b != -1) {
                Log.w("AryaAudioEngineProxyImp", "initAudioEngine, but stannisUserId is:" + AryaAudioEngineProxyImp.this.f28517b);
                AryaAudioEngineProxyImp.this.f28516a.uninitStannisEngine(AryaAudioEngineProxyImp.this.f28517b);
            }
            AryaAudioEngineProxyImp aryaAudioEngineProxyImp3 = AryaAudioEngineProxyImp.this;
            aryaAudioEngineProxyImp3.f28517b = aryaAudioEngineProxyImp3.f28516a.initStannisEngineWithNotifyObserver(this.f28534a, null, AryaAudioEngineProxyImp.this.f28532q);
        }
    }

    /* JADX INFO: renamed from: com.kwai.video.arya.AryaAudioEngineProxyImp$89, reason: invalid class name */
    static /* synthetic */ class AnonymousClass89 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f28736a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f28737b;

        static {
            int[] iArr = new int[AudioSegmentPlayerObserver.ErrorType.values().length];
            f28737b = iArr;
            try {
                iArr[AudioSegmentPlayerObserver.ErrorType.None.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28737b[AudioSegmentPlayerObserver.ErrorType.ErrorIo.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28737b[AudioSegmentPlayerObserver.ErrorType.Unknown.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28737b[AudioSegmentPlayerObserver.ErrorType.Malformed.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28737b[AudioSegmentPlayerObserver.ErrorType.NotSupported.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            int[] iArr2 = new int[BgmObserver.BgmErrorType.values().length];
            f28736a = iArr2;
            try {
                iArr2[BgmObserver.BgmErrorType.None.ordinal()] = 1;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28736a[BgmObserver.BgmErrorType.ErrorIo.ordinal()] = 2;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28736a[BgmObserver.BgmErrorType.Unknown.ordinal()] = 3;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28736a[BgmObserver.BgmErrorType.Malformed.ordinal()] = 4;
            } catch (NoSuchFieldError e22) {
                CrashReporter.logCheckedException(e22, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
            try {
                f28736a[BgmObserver.BgmErrorType.NotSupported.ordinal()] = 5;
            } catch (NoSuchFieldError e23) {
                CrashReporter.logCheckedException(e23, "AryaAudioEngineProxyImp$89.class", "<clinit>", -1);
            }
        }
    }

    public AryaAudioEngineProxyImp() {
        this.f28516a = null;
        this.f28520e = null;
        this.f28521f = null;
        this.f28526k = null;
        this.f28533r = null;
        Log.i("AryaAudioEngineProxyImp", "init audio engine");
        HandlerThread handlerThread = new HandlerThread("AryaCallStannis");
        this.f28520e = handlerThread;
        handlerThread.start();
        this.f28521f = new Handler(this.f28520e.getLooper());
        this.f28516a = Stannis.getInstance();
        this.f28526k = new HashMap<>();
        this.f28529n = new ArrayList<>();
        this.f28533r = new HashMap<>();
        E();
    }

    private void E() {
        String stannisVersion = this.f28516a.getStannisVersion();
        Pattern patternCompile = Pattern.compile("[A-za-z]*[0-9]+\\.[0-9]+\\.");
        if (patternCompile != null) {
            Matcher matcher = patternCompile.matcher(stannisVersion);
            String strGroup = matcher.find() ? matcher.group() : stannisVersion;
            Log.i("AryaAudioEngineProxyImp", "stannis main version: " + strGroup);
            if (!"r4.8.0.9.0.4d6622197".startsWith(strGroup)) {
                throw new AssertionError("stannis version not match, expected ver: r4.8.0.9.0.4d6622197, runtime ver: " + stannisVersion);
            }
        } else {
            Log.w("AryaAudioEngineProxyImp", "stannis version checking, pattern compile failed");
        }
        Log.i("AryaAudioEngineProxyImp", "stannis version match, expected version: r4.8.0.9.0.4d6622197, runtime ver: " + stannisVersion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        boolean z12;
        Iterator<Map.Entry<com.kwai.video.arya.a.C0440a, b>> it2 = this.f28526k.entrySet().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z12 = true;
                break;
            } else if (!it2.next().getValue().f28750a) {
                z12 = false;
                break;
            }
        }
        if (z12) {
            this.f28516a.pause();
            this.f28522g = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeCreateStannisDataProvider();

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeCreateStannisDataReceiver();

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDestroyStannisDataProvider(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDestroyStannisDataReceiver(long j12);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeStannisDataProviderAddVoip(long j12, long j13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeStannisDataProviderRemoveVoip(long j12, long j13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeStannisDataReceiverAddVoip(long j12, long j13);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeStannisDataReceiverRemoveVoip(long j12, long j13);

    @Override // com.kwai.video.arya.a
    public void A() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.79
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.stopPcmPlay();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void B() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.80
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.resetPcmPlay();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void C() {
        this.f28516a.disableHeadphoneMonitor();
    }

    @Override // com.kwai.video.arya.a
    public void D() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.83
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.stopMultipleStreamMix();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public boolean d() {
        return true;
    }

    @Override // com.kwai.video.arya.a
    public void u() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.61
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28519d = null;
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void v() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.68
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.stopKaraokeVad();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void w() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.71
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.RemoveAllBypassDataReadyObserver();
                AryaAudioEngineProxyImp.this.f28527l = null;
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void x() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.74
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.pauseAudioBuffer();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void y() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.77
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.clearAllAudioBuffer();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void z() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.78
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.startPcmPlay();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void f(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.6
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setAudioVoiceRoleOption(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void g(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.7
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setMuteMicrophone(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void h() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.20
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28518c = null;
                AryaAudioEngineProxyImp.this.f28516a.stopBgm();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void i() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.21
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.pauseBgm();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void j() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.22
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.resumeBgm();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void k() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.24
            @Override // java.lang.Runnable
            public void run() {
                if (AryaAudioEngineProxyImp.this.f28518c == null || !AryaAudioEngineProxyImp.this.f28518c.f28740a) {
                    return;
                }
                Log.i("AryaAudioEngineProxyImp", "Restore BGM with position:" + AryaAudioEngineProxyImp.this.f28518c.f28745f);
                AryaAudioEngineProxyImp.this.f28518c.f28740a = false;
                AryaAudioEngineProxyImp.this.f28516a.startBgm(AryaAudioEngineProxyImp.this.f28518c.f28741b, AryaAudioEngineProxyImp.this.f28518c.f28742c, AryaAudioEngineProxyImp.this.f28518c.f28743d, AryaAudioEngineProxyImp.this.f28518c.f28744e);
                AryaAudioEngineProxyImp.this.f28516a.updateBgmIndex(AryaAudioEngineProxyImp.this.f28518c.f28746g, AryaAudioEngineProxyImp.this.f28518c.f28747h);
                AryaAudioEngineProxyImp.this.f28516a.seekBgm((int) AryaAudioEngineProxyImp.this.f28518c.f28745f);
                AryaAudioEngineProxyImp.this.f28516a.setBgmPitch(AryaAudioEngineProxyImp.this.f28518c.f28748i);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void l(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.41
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.seekBgm(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void m(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.42
            @Override // java.lang.Runnable
            public void run() {
                if (AryaAudioEngineProxyImp.this.f28518c != null) {
                    AryaAudioEngineProxyImp.this.f28518c.f28748i = i12;
                }
                AryaAudioEngineProxyImp.this.f28516a.setBgmPitch(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void n() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.49
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.stopAllSoundEffects();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public boolean o(boolean z12) {
        return this.f28516a.enableHeadphoneMonitor(z12);
    }

    @Override // com.kwai.video.arya.a
    public void p(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.52
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setRequestAudioFocus(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public int q() {
        return this.f28516a.getKaraokeTotalScore();
    }

    @Override // com.kwai.video.arya.a
    public int r() {
        return this.f28516a.getKaraokeScorePitch();
    }

    @Override // com.kwai.video.arya.a
    public int s() {
        return this.f28516a.getKaraokeAverageScore();
    }

    @Override // com.kwai.video.arya.a
    public void t() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.60
            @Override // java.lang.Runnable
            public void run() {
                if (AryaAudioEngineProxyImp.this.f28519d == null || !AryaAudioEngineProxyImp.this.f28519d.f28753a) {
                    return;
                }
                Log.i("AryaAudioEngineProxyImp", "Restore karaokeScore");
                AryaAudioEngineProxyImp.this.f28519d.f28753a = false;
                AryaAudioEngineProxyImp.this.f28516a.startKaraokeScore(AryaAudioEngineProxyImp.this.f28519d.f28754b, AryaAudioEngineProxyImp.this.f28519d.f28755c, AryaAudioEngineProxyImp.this.f28519d.f28756d, AryaAudioEngineProxyImp.this.f28519d.f28757e, AryaAudioEngineProxyImp.this.f28519d.f28758f, AryaAudioEngineProxyImp.this.f28519d.f28759g, AryaAudioEngineProxyImp.this.f28519d.f28760h, AryaAudioEngineProxyImp.this.f28519d.f28761i, AryaAudioEngineProxyImp.this.f28519d.f28762j);
            }
        });
    }

    private class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f28750a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f28751b;

        private b() {
            this.f28750a = false;
            this.f28751b = 512;
        }

        /* synthetic */ b(AryaAudioEngineProxyImp aryaAudioEngineProxyImp, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f28753a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f28754b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f28755c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f28756d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f28757e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f28758f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f28759g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f28760h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f28761i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public KaraokeScoreObserver f28762j;

        private c() {
            this.f28753a = false;
            this.f28762j = null;
        }

        /* synthetic */ c(AryaAudioEngineProxyImp aryaAudioEngineProxyImp, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    @Override // com.kwai.video.arya.a
    public void e(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.5
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setAudioVoiceEffectOption(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void f(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.26
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setRemoteBgmVolume(f12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void g(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.14
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setEnableNoiseSuppression(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void h(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.28
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setMuteChatOutBgm(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void i(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.30
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setAgcMode(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void j(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.27
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setMuteBgm(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void k(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.36
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setSoftAecMode(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void l() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.43
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28518c = null;
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void m() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.46
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.cleanSoundEffectCache();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public boolean n(boolean z12) {
        return this.f28516a.isSupportHeadphoneMonitor(z12);
    }

    @Override // com.kwai.video.arya.a
    public void o() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.54
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.stopPlayAudioSegment();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void p() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.59
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28519d = null;
                AryaAudioEngineProxyImp.this.f28516a.stopKaraokeScore();
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void q(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.86
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.removeMixTrack(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public Arya.AryaDeviceInfo r(int i12) {
        return a(this.f28516a.getCurrentDevice(i12));
    }

    @Override // com.kwai.video.arya.a
    public Arya.AryaDeviceInfo[] s(int i12) {
        return a(this.f28516a.getDevices(i12));
    }

    @Override // com.kwai.video.arya.a
    public void c(final com.kwai.video.arya.a.C0440a c0440a) {
        Log.i("AryaAudioEngineProxyImp", "resume " + c0440a);
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.3
            @Override // java.lang.Runnable
            public void run() {
                if (AryaAudioEngineProxyImp.this.f28526k.containsKey(c0440a)) {
                    AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
                    aryaAudioEngineProxyImp.nativeStannisDataReceiverAddVoip(aryaAudioEngineProxyImp.f28524i, c0440a.f28836b);
                    AryaAudioEngineProxyImp aryaAudioEngineProxyImp2 = AryaAudioEngineProxyImp.this;
                    aryaAudioEngineProxyImp2.nativeStannisDataProviderAddVoip(aryaAudioEngineProxyImp2.f28525j, c0440a.f28836b);
                    if (AryaAudioEngineProxyImp.this.f28522g) {
                        AryaAudioEngineProxyImp.this.f28516a.resume();
                        AryaAudioEngineProxyImp.this.f28522g = false;
                    }
                    b bVar = (b) AryaAudioEngineProxyImp.this.f28526k.get(c0440a);
                    bVar.f28750a = false;
                    if (bVar.f28751b != AryaAudioEngineProxyImp.this.f28523h) {
                        AryaAudioEngineProxyImp.this.f28516a.startPipelineWithNativePtr(bVar.f28751b, AryaAudioEngineProxyImp.this.f28524i);
                        AryaAudioEngineProxyImp.this.f28523h = bVar.f28751b;
                        if (AryaAudioEngineProxyImp.this.f28527l != null) {
                            AryaAudioEngineProxyImp.this.f28516a.AddBypassDataReadyObserver(AryaAudioEngineProxyImp.this.f28527l, AryaAudioEngineProxyImp.this.f28528m);
                        }
                    }
                }
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void d(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.4
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setReverbLevel(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void e(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.25
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setBgmVolume(f12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public int f() {
        return this.f28516a.getOutputType();
    }

    @Override // com.kwai.video.arya.a
    public int[] g() {
        return this.f28516a.getOutputTypes();
    }

    @Override // com.kwai.video.arya.a
    public void h(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.62
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setHowlingSuppressionMode(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void i(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.35
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setEnableSpeakerInputAEC(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void j(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.32
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setOutputType(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void k(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.51
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setMuteSoundEffect(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void l(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.56
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.enableMixingAudioSegment(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void m(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.75
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.resumeAudioBuffer(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public boolean n(int i12) {
        return this.f28516a.setKaraokeScorePitch(i12);
    }

    @Override // com.kwai.video.arya.a
    public List o(int i12) {
        return this.f28516a.getKaraokeVadDurationResult(i12);
    }

    @Override // com.kwai.video.arya.a
    public void p(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.85
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.addMixTrack(i12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void q(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.88
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setUsingBuiltinMic(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void b(final com.kwai.video.arya.a.C0440a c0440a) {
        Log.i("AryaAudioEngineProxyImp", "pause " + c0440a);
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.2
            @Override // java.lang.Runnable
            public void run() {
                if (AryaAudioEngineProxyImp.this.f28526k.containsKey(c0440a)) {
                    ((b) AryaAudioEngineProxyImp.this.f28526k.get(c0440a)).f28750a = true;
                    AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
                    aryaAudioEngineProxyImp.nativeStannisDataReceiverRemoveVoip(aryaAudioEngineProxyImp.f28524i, c0440a.f28836b);
                    AryaAudioEngineProxyImp aryaAudioEngineProxyImp2 = AryaAudioEngineProxyImp.this;
                    aryaAudioEngineProxyImp2.nativeStannisDataProviderRemoveVoip(aryaAudioEngineProxyImp2.f28525j, c0440a.f28836b);
                    AryaAudioEngineProxyImp.this.F();
                }
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void d(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.13
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.enableRecord(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void e(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.63
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setKtvMode(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void f(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.66
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setBgmAutoSeekEnable(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void g(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.50
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setSoundEffectVolume(f12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void h(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.81
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setPcmPlayVolume(f12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public int c() {
        return this.f28516a.getSpeakerDeviceVolume();
    }

    @Override // com.kwai.video.arya.a
    public void d(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.40
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setRemoteMixVolume(f12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void e() {
        this.f28516a.stopInnerCap();
    }

    @Override // com.kwai.video.arya.a
    public int[] b() {
        return this.f28516a.getActiveSpeakers();
    }

    @Override // com.kwai.video.arya.a
    public String c(int i12) {
        return a(this.f28516a.getQosInfo(i12));
    }

    @Override // com.kwai.video.arya.a
    public int b(int i12) {
        return this.f28516a.getVoiceEnergy(i12);
    }

    @Override // com.kwai.video.arya.a
    public void c(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.8
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setAudioInputVolume(f12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void b(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.9
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setMuteSpeaker(z12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void c(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.11
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setSpeakerOn(z12);
            }
        });
    }

    private class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f28740a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<String> f28741b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f28742c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f28743d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public BgmObserver f28744e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f28745f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f28746g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f28747h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f28748i;

        private a() {
            this.f28740a = false;
            this.f28741b = null;
            this.f28742c = false;
            this.f28743d = 0;
            this.f28744e = null;
            this.f28745f = 0.0f;
            this.f28746g = 0;
            this.f28747h = 0;
            this.f28748i = 0;
        }

        /* synthetic */ a(AryaAudioEngineProxyImp aryaAudioEngineProxyImp, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    @Override // com.kwai.video.arya.a
    public void b(final boolean z12, final String str) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.31
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setEnableCommonDenoise(z12, str);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void c(final String str) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.76
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.clearAudioBuffer(str);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void b(final AudioSceneObserver audioSceneObserver) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.39
            @Override // java.lang.Runnable
            public void run() {
                if (audioSceneObserver == null) {
                    return;
                }
                StannisAudioSceneObserver stannisAudioSceneObserver = (StannisAudioSceneObserver) AryaAudioEngineProxyImp.this.f28533r.get(audioSceneObserver);
                AryaAudioEngineProxyImp.this.f28533r.remove(audioSceneObserver);
                Log.i("AryaAudioEngineProxyImp", "removeAudioSceneObserver size:" + AryaAudioEngineProxyImp.this.f28533r.size());
                if (stannisAudioSceneObserver != null) {
                    AryaAudioEngineProxyImp.this.f28516a.removeAudioSceneObserver(stannisAudioSceneObserver);
                }
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void c(byte[] bArr, int i12, int i13, int i14, long j12) {
        this.f28516a.inputPcmPlay(bArr, i12, i13, i14, j12);
    }

    @Override // com.kwai.video.arya.a
    public void a(Context context) {
        this.f28521f.post(new AnonymousClass1(context));
    }

    @Override // com.kwai.video.arya.a
    public void b(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.64
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setCompressGain(f12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a() {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.12
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.uninitStannisEngine(AryaAudioEngineProxyImp.this.f28517b);
                AryaAudioEngineProxyImp.this.f28517b = -1L;
                AryaAudioEngineProxyImp.this.f28526k.clear();
                AryaAudioEngineProxyImp.this.f28533r.clear();
                AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
                aryaAudioEngineProxyImp.nativeDestroyStannisDataReceiver(aryaAudioEngineProxyImp.f28524i);
                AryaAudioEngineProxyImp.this.f28524i = 0L;
                AryaAudioEngineProxyImp aryaAudioEngineProxyImp2 = AryaAudioEngineProxyImp.this;
                aryaAudioEngineProxyImp2.nativeDestroyStannisDataProvider(aryaAudioEngineProxyImp2.f28525j);
                AryaAudioEngineProxyImp.this.f28525j = 0L;
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void b(final String str) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.67
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.startKaraokeVad(str);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final Arya.AryaConfig aryaConfig) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.23
            @Override // java.lang.Runnable
            public void run() {
                Stannis.KWStannisConfig stannisConfig = AryaAudioEngineProxyImp.this.f28516a.getStannisConfig();
                Arya.AryaConfig aryaConfig2 = aryaConfig;
                stannisConfig.qosFlag = aryaConfig2.qosEnableFlag;
                stannisConfig.qosUploadInterval = aryaConfig2.qosUploadInterval;
                stannisConfig.activeSpeakerHangOverInterval = aryaConfig2.speakerInactiveMinIntervalMs;
                stannisConfig.dumpPath = aryaConfig2.dumpPath;
                stannisConfig.dumpFlag = aryaConfig2.dumpEnableFlag & 65535;
                stannisConfig.enableAudioVad = aryaConfig2.enableAudioVad;
                stannisConfig.useExternalDevice = aryaConfig2.useExternalAudioDevice;
                stannisConfig.audioChannel = 2;
                stannisConfig.audioOutputChannel = 2;
                AryaAudioEngineProxyImp.this.f28516a.enableAecDump(stannisConfig.dumpFlag != 0);
                AryaAudioEngineProxyImp.this.f28516a.setStannisConfig(stannisConfig);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void b(byte[] bArr, int i12, int i13, int i14, long j12) {
        this.f28516a.inputRawAudio(bArr, i12, i13, i14, j12);
    }

    @Override // com.kwai.video.arya.a
    public void a(final boolean z12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.34
            @Override // java.lang.Runnable
            public void run() {
                Log.i("AryaAudioEngineProxyImp", "setAudioStereo stereo:" + z12);
                Stannis.KWStannisConfig stannisConfig = AryaAudioEngineProxyImp.this.f28516a.getStannisConfig();
                boolean z13 = z12;
                stannisConfig.audioChannel = z13 ? 2 : 1;
                stannisConfig.audioOutputChannel = z13 ? 2 : 1;
                AryaAudioEngineProxyImp.this.f28516a.setStannisConfig(stannisConfig);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final AudioServerConfig audioServerConfig) {
        a(audioServerConfig.liveStreamStereo);
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.44
            @Override // java.lang.Runnable
            public void run() {
                Log.i("AryaAudioEngineProxyImp", "updateServerConfig " + audioServerConfig);
                Stannis stannis = AryaAudioEngineProxyImp.this.f28516a;
                stannis.getClass();
                Stannis.KWStannisServerConfig kWStannisServerConfig = stannis.new KWStannisServerConfig();
                AudioServerConfig audioServerConfig2 = audioServerConfig;
                kWStannisServerConfig.enableDevAec = audioServerConfig2.enableDevAec;
                kWStannisServerConfig.softAecNlp = audioServerConfig2.softAecNlp;
                kWStannisServerConfig.enableAecHighQuality = audioServerConfig2.enableAecHighQuality;
                kWStannisServerConfig.enableGroupDevAec = audioServerConfig2.enableGroupDevAec;
                kWStannisServerConfig.groupSoftAecNlp = audioServerConfig2.groupSoftAecNlp;
                kWStannisServerConfig.enableGroupAecHighQuality = audioServerConfig2.enableGroupAecHighQuality;
                kWStannisServerConfig.forceAec = audioServerConfig2.forceAec;
                kWStannisServerConfig.forceAecNlp = audioServerConfig2.forceAecNlp;
                kWStannisServerConfig.roundTripLatency = audioServerConfig2.roundTripLatency;
                kWStannisServerConfig.chatRoundTripLatency = audioServerConfig2.chatRoundTripLatency;
                kWStannisServerConfig.ktvVendorSupport = audioServerConfig2.ktvVendorSupport;
                kWStannisServerConfig.liveStreamMixBgm = audioServerConfig2.liveStreamMixBgm;
                kWStannisServerConfig.deviceType = audioServerConfig2.deviceType;
                kWStannisServerConfig.useSoftHeadphoneMonitor = audioServerConfig2.useSoftHeadphoneMonitor;
                kWStannisServerConfig.enableStereoInput = audioServerConfig2.stereoInput;
                kWStannisServerConfig.enableLiteMode = audioServerConfig2.liteMode;
                kWStannisServerConfig.disableCommonNS = audioServerConfig2.disableCommonNS;
                kWStannisServerConfig.enableLineAgc = audioServerConfig2.enableLineAgc;
                kWStannisServerConfig.disableNewAecDelayEst = audioServerConfig2.disableNewAecDelayEst;
                kWStannisServerConfig.enableProfile = audioServerConfig2.enableProfile;
                kWStannisServerConfig.profileStatisticTimes = audioServerConfig2.profileStatisticTimes;
                kWStannisServerConfig.enableLineNs = audioServerConfig2.enableLineNs;
                kWStannisServerConfig.lineAecNsLevel = audioServerConfig2.lineAecNsLevel;
                kWStannisServerConfig.audioJsonConfig = audioServerConfig2.jsonConfig;
                AryaAudioEngineProxyImp.this.f28516a.updateServerConfig(kWStannisServerConfig);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final int i12, final com.kwai.video.arya.a.C0440a c0440a) {
        Log.i("AryaAudioEngineProxyImp", "start " + c0440a);
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.55
            @Override // java.lang.Runnable
            public void run() {
                AnonymousClass1 anonymousClass1 = null;
                if (AryaAudioEngineProxyImp.this.f28516a.isInited()) {
                    Log.i("AryaAudioEngineProxyImp", "start stannis has been inited already.");
                } else {
                    Log.i("AryaAudioEngineProxyImp", "start stannis was not inited, init it again.");
                    AryaAudioEngineProxyImp.this.f28516a.uninitStannisEngine(AryaAudioEngineProxyImp.this.f28517b);
                    AryaAudioEngineProxyImp.this.f28526k.clear();
                    AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
                    aryaAudioEngineProxyImp.f28517b = aryaAudioEngineProxyImp.f28516a.initStannisEngineWithNotifyObserver(AryaAudioEngineProxyImp.this.f28531p, null, AryaAudioEngineProxyImp.this.f28532q);
                }
                boolean z12 = true;
                if (AryaAudioEngineProxyImp.this.f28526k.containsKey(c0440a)) {
                    b bVar = (b) AryaAudioEngineProxyImp.this.f28526k.get(c0440a);
                    z12 = true ^ bVar.f28750a;
                    bVar.f28751b = i12;
                    Log.i("AryaAudioEngineProxyImp", "shoudStart=" + z12 + " with ctx.pause=" + bVar.f28750a);
                } else {
                    b bVar2 = new b(AryaAudioEngineProxyImp.this, anonymousClass1);
                    bVar2.f28750a = false;
                    bVar2.f28751b = i12;
                    AryaAudioEngineProxyImp.this.f28526k.put(c0440a, bVar2);
                    Log.i("AryaAudioEngineProxyImp", "start delegateMap size:" + AryaAudioEngineProxyImp.this.f28526k.size());
                    AryaAudioEngineProxyImp aryaAudioEngineProxyImp2 = AryaAudioEngineProxyImp.this;
                    aryaAudioEngineProxyImp2.nativeStannisDataReceiverAddVoip(aryaAudioEngineProxyImp2.f28524i, c0440a.f28836b);
                    AryaAudioEngineProxyImp aryaAudioEngineProxyImp3 = AryaAudioEngineProxyImp.this;
                    aryaAudioEngineProxyImp3.nativeStannisDataProviderAddVoip(aryaAudioEngineProxyImp3.f28525j, c0440a.f28836b);
                }
                if (z12) {
                    if (AryaAudioEngineProxyImp.this.f28522g) {
                        AryaAudioEngineProxyImp.this.f28516a.resume();
                        AryaAudioEngineProxyImp.this.f28522g = false;
                    }
                    AryaAudioEngineProxyImp.this.f28516a.startPipelineWithNativePtr(i12, AryaAudioEngineProxyImp.this.f28524i);
                    AryaAudioEngineProxyImp.this.f28523h = i12;
                    if (AryaAudioEngineProxyImp.this.f28527l != null) {
                        AryaAudioEngineProxyImp.this.f28516a.AddBypassDataReadyObserver(AryaAudioEngineProxyImp.this.f28527l, AryaAudioEngineProxyImp.this.f28528m);
                    }
                }
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final com.kwai.video.arya.a.C0440a c0440a) {
        Log.i("AryaAudioEngineProxyImp", "stop " + c0440a);
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.65
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28530o = 1.0f;
                AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
                aryaAudioEngineProxyImp.nativeStannisDataReceiverRemoveVoip(aryaAudioEngineProxyImp.f28524i, c0440a.f28836b);
                AryaAudioEngineProxyImp aryaAudioEngineProxyImp2 = AryaAudioEngineProxyImp.this;
                aryaAudioEngineProxyImp2.nativeStannisDataProviderRemoveVoip(aryaAudioEngineProxyImp2.f28525j, c0440a.f28836b);
                AryaAudioEngineProxyImp.this.f28526k.remove(c0440a);
                Log.i("AryaAudioEngineProxyImp", "stop delegateMap size:" + AryaAudioEngineProxyImp.this.f28526k.size());
                if (AryaAudioEngineProxyImp.this.f28526k.isEmpty()) {
                    AryaAudioEngineProxyImp.this.f28516a.stopPipeline();
                } else {
                    AryaAudioEngineProxyImp.this.F();
                }
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final int i12, com.kwai.video.arya.a.C0440a c0440a, final int i13, final int i14) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.73
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.AddRxStreamWithNativeDataProvider(i12, AryaAudioEngineProxyImp.this.f28525j, i13, i14);
                AryaAudioEngineProxyImp.this.f28529n.add(Integer.valueOf(i12));
                Log.i("AryaAudioEngineProxyImp", "addRxStream with volume:" + AryaAudioEngineProxyImp.this.f28530o);
                AryaAudioEngineProxyImp.this.f28516a.setAudioRxVolume(i12, AryaAudioEngineProxyImp.this.f28530o);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.84
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28529n.remove(Integer.valueOf(i12));
                AryaAudioEngineProxyImp.this.f28516a.RemoveRxStream(i12);
            }
        });
    }

    private String a(com.kwai.video.stannis.QosInfo qosInfo) {
        return "{\"stnsVer\":\"" + qosInfo.sdkVersion + "\",\"aScene\":\"" + qosInfo.audioScene + "\",\"atxRecSr\":" + qosInfo.audioTxRecordedSampleRate + ",\"atxRecCh\":" + qosInfo.audioTxRecordedChannelNum + ",\"atxRecItr\":" + qosInfo.audioTxRecordedInterval + ",\"atxRecItrMx\":" + qosInfo.audioTxRecordedIntervalMax + ",\"atxRecGain\":" + qosInfo.audioTxRecordedGain + ",\"atxRecGainMx\":" + qosInfo.audioTxRecordedGainMax + ",\"atxRecBytes\":" + qosInfo.audioTxRecordBytes + ",\"atx3aGainMx\":" + qosInfo.audioTxAft3aGainMax + ",\"atxNearDly\":" + qosInfo.audioTxNearEndDelay + ",\"atxNearDlyMx\":" + qosInfo.audioTxNearEndDelayMax + ",\"atxStrmBytes\":" + qosInfo.audioTxLiveStreamOutputBytes + ",\"atxStrmSr\":" + qosInfo.audioTxLiveStreamSampleRate + ",\"atxStrmCh\":" + qosInfo.audioTxLiveStreamChannelNum + ",\"atxChatBytes\":" + qosInfo.audioTxLiveChatOutputBytes + ",\"atxChatSr\":" + qosInfo.audioTxLiveChatSampleRate + ",\"atxChatCh\":" + qosInfo.audioTxLiveChatChannelNum + ",\"atxLiveMixBgmInside\":" + qosInfo.audioTxLiveStreamMixBgmInside + ",\"atxInputState\":\"" + qosInfo.audioTxInputSourceState + "\",\"lMicMute\":" + qosInfo.audioIsLocalMicMuted + ",\"aDevHead\":" + qosInfo.audioDeviceConnectHeadphone + ",\"aDevBlue\":" + qosInfo.audioDeviceConnectBluetooth + ",\"aDevUsb\":" + qosInfo.audioDeviceConnectUSB + ",\"aDevIn\":\"" + qosInfo.audioDeviceInputPort + "\",\"aDevOut\":\"" + qosInfo.audioDeviceOutputPort + "\",\"aDevMod\":\"" + qosInfo.audioDeviceMode + "\",\"aDevEffect\":\"" + qosInfo.audioEffect + "\",\"aDevVAD\":" + qosInfo.audioVAD + ",\"arxInBytes\":" + qosInfo.audioRxInputBytes + ",\"arxInSr\":" + qosInfo.audioRxStreamInSampleRate + ",\"arxInCh\":" + qosInfo.audioRxStreamInChannelNum + ",\"arxPlyGain\":" + qosInfo.audioRxPlaybackGain + ",\"arxPlyGainMax\":" + qosInfo.audioRxPlaybackGainMax + ",\"arxPlyItr\":" + qosInfo.audioRxPlaybackInterval + ",\"arxPlyItrMx\":" + qosInfo.audioRxPlaybackIntervalMax + ",\"arxNumber\":" + qosInfo.audioRxNumber + ",\"arxSpkDevVol\":\"" + qosInfo.audioRxSpkDevVol + "\",\"aHowling\":" + qosInfo.howlingDetected + ",\"aAecSoft\":" + qosInfo.audioAecSoft + ",\"aAecNlp\":" + qosInfo.audioAecNlp + ",\"aAecErle\":\"" + qosInfo.audioAecErle + "\",\"aAecHQ\":" + qosInfo.audioAecHQ + ",\"aForceAec\":" + qosInfo.audioForceAec + ",\"aLtcSet\":" + qosInfo.audioLatencySetting + ",\"aLtcBuf\":\"" + qosInfo.audioLatencyBuffer + "\",\"aLiteMode\":" + qosInfo.audioLiteMode + ",\"aAgcOn\":" + qosInfo.audioAgcOn + "}";
    }

    @Override // com.kwai.video.arya.a
    public void a(final float f12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.10
            @Override // java.lang.Runnable
            public void run() {
                Log.i("AryaAudioEngineProxyImp", "setSpeakerVolume:" + f12);
                AryaAudioEngineProxyImp.this.f28530o = f12;
                Iterator it2 = AryaAudioEngineProxyImp.this.f28529n.iterator();
                while (it2.hasNext()) {
                    AryaAudioEngineProxyImp.this.f28516a.setAudioRxVolume(((Integer) it2.next()).intValue(), f12);
                }
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final boolean z12, final boolean z13) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.15
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setMuteRemote(z12, z13);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final Arya.AryaAudioDeviceStatusListener aryaAudioDeviceStatusListener) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.16
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setAudioDeviceStatusListener(new StannisAudioDeviceStatusObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.16.1
                    @Override // com.kwai.video.stannis.observers.StannisAudioDeviceStatusObserver
                    public void onAudioDeviceStatusChange(int i12) {
                        aryaAudioDeviceStatusListener.onAudioDeviceStatusChange(i12);
                    }
                });
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final ArrayList<String> arrayList, final boolean z12, final int i12, final com.kwai.video.arya.a.c cVar) {
        final BgmObserver bgmObserver = new BgmObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.17
            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onCompleted(String str) {
                AryaAudioEngineProxyImp.this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.17.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AryaAudioEngineProxyImp.this.f28518c = null;
                    }
                });
                cVar.onCompleted(str);
            }

            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onError(String str, BgmObserver.BgmErrorType bgmErrorType) {
                AryaAudioEngineProxyImp.this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.17.3
                    @Override // java.lang.Runnable
                    public void run() {
                        AryaAudioEngineProxyImp.this.f28518c = null;
                    }
                });
                com.kwai.video.arya.observers.BgmObserver.BgmErrorType bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.None;
                int i13 = AnonymousClass89.f28736a[bgmErrorType.ordinal()];
                if (i13 != 1) {
                    if (i13 == 2) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.ErrorIo;
                    } else if (i13 == 3) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.Unknown;
                    } else if (i13 == 4) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.Malformed;
                    } else if (i13 == 5) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.NotSupported;
                    }
                }
                cVar.onError(str, bgmErrorType2);
            }

            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onProgressed(String str, final float f12, float f13) {
                AryaAudioEngineProxyImp.this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.17.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (AryaAudioEngineProxyImp.this.f28518c != null) {
                            AryaAudioEngineProxyImp.this.f28518c.f28745f = f12;
                        }
                    }
                });
                cVar.onProgressed(str, f12, f13);
            }

            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onStart(String str) {
                cVar.onStart(str);
            }
        };
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.18
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
                aryaAudioEngineProxyImp.f28518c = new a(aryaAudioEngineProxyImp, null);
                AryaAudioEngineProxyImp.this.f28518c.f28740a = false;
                AryaAudioEngineProxyImp.this.f28518c.f28741b = arrayList;
                AryaAudioEngineProxyImp.this.f28518c.f28742c = z12;
                AryaAudioEngineProxyImp.this.f28518c.f28743d = i12;
                AryaAudioEngineProxyImp.this.f28518c.f28744e = bgmObserver;
                AryaAudioEngineProxyImp.this.f28518c.f28745f = 0.0f;
                if (arrayList.size() >= 2) {
                    AryaAudioEngineProxyImp.this.f28518c.f28746g = 0;
                    AryaAudioEngineProxyImp.this.f28518c.f28747h = 1;
                } else {
                    a aVar = AryaAudioEngineProxyImp.this.f28518c;
                    AryaAudioEngineProxyImp.this.f28518c.f28747h = 0;
                    aVar.f28746g = 0;
                }
                AryaAudioEngineProxyImp.this.f28516a.startBgm(arrayList, z12, i12, bgmObserver);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final int i12, final int i13) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.19
            @Override // java.lang.Runnable
            public void run() {
                if (AryaAudioEngineProxyImp.this.f28518c != null) {
                    AryaAudioEngineProxyImp.this.f28518c.f28746g = i12;
                    AryaAudioEngineProxyImp.this.f28518c.f28747h = i13;
                }
                AryaAudioEngineProxyImp.this.f28516a.updateBgmIndex(i12, i13);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final boolean z12, final String str) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.29
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setEnableDeepNs(z12, str);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final Arya.AryaAudioRouteListener aryaAudioRouteListener) {
        this.f28516a.setAudioRouteListener(new Stannis.AudioRouteListener() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.33
            @Override // com.kwai.video.stannis.Stannis.AudioRouteListener
            public void onRouteChange(int i12, int i13) {
                Arya.AryaAudioRouteListener aryaAudioRouteListener2 = aryaAudioRouteListener;
                if (aryaAudioRouteListener2 != null) {
                    aryaAudioRouteListener2.onRouteChange(i12, i13);
                }
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final byte[] bArr, final int i12, final int i13, final int i14, final long j12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.37
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.inputSpeakerAudio(bArr, i12, i13, i14, j12);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final AudioSceneObserver audioSceneObserver) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.38
            @Override // java.lang.Runnable
            public void run() {
                if (audioSceneObserver == null) {
                    return;
                }
                Log.i("AryaAudioEngineProxyImp", "[addAudioSceneObserver] audioSceneObserver:" + audioSceneObserver);
                if (AryaAudioEngineProxyImp.this.f28533r.containsKey(audioSceneObserver)) {
                    Log.i("AryaAudioEngineProxyImp", "[addAudioSceneObserver] audioSceneDelegateMap contains:" + audioSceneObserver);
                    return;
                }
                StannisAudioSceneObserver stannisAudioSceneObserver = new StannisAudioSceneObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.38.1
                    @Override // com.kwai.video.stannis.observers.StannisAudioSceneObserver
                    public void onAudioSceneStart(int i12) {
                        AudioSceneObserver audioSceneObserver2 = audioSceneObserver;
                        if (audioSceneObserver2 != null) {
                            audioSceneObserver2.onAudioSceneStart(i12);
                        }
                    }

                    @Override // com.kwai.video.stannis.observers.StannisAudioSceneObserver
                    public void onAudioSceneStop(int i12) {
                        AudioSceneObserver audioSceneObserver2 = audioSceneObserver;
                        if (audioSceneObserver2 != null) {
                            audioSceneObserver2.onAudioSceneStop(i12);
                        }
                    }
                };
                AryaAudioEngineProxyImp.this.f28533r.put(audioSceneObserver, stannisAudioSceneObserver);
                Log.i("AryaAudioEngineProxyImp", "[addAudioSceneObserver] stannisAudioSceneObserver:" + stannisAudioSceneObserver);
                AryaAudioEngineProxyImp.this.f28516a.addAudioSceneObserver(stannisAudioSceneObserver);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final String str) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.45
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.loadSoundEffectCache(str);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final String str, final com.kwai.video.arya.a.c cVar) {
        final BgmObserver bgmObserver = new BgmObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.47
            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onCompleted(String str2) {
                cVar.onCompleted(str2);
            }

            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onError(String str2, BgmObserver.BgmErrorType bgmErrorType) {
                com.kwai.video.arya.observers.BgmObserver.BgmErrorType bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.None;
                int i12 = AnonymousClass89.f28736a[bgmErrorType.ordinal()];
                if (i12 != 1) {
                    if (i12 == 2) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.ErrorIo;
                    } else if (i12 == 3) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.Unknown;
                    } else if (i12 == 4) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.Malformed;
                    } else if (i12 == 5) {
                        bgmErrorType2 = com.kwai.video.arya.observers.BgmObserver.BgmErrorType.NotSupported;
                    }
                }
                cVar.onError(str2, bgmErrorType2);
            }

            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onProgressed(String str2, float f12, float f13) {
                cVar.onProgressed(str2, f12, f13);
            }

            @Override // com.kwai.video.stannis.observers.BgmObserver
            public void onStart(String str2) {
                cVar.onStart(str2);
            }
        };
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.48
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.playSoundEffect(str, bgmObserver);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final String str, final String str2, final com.kwai.video.arya.a.b bVar) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.53
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.startPlayAudioSegment(str, str2, new AudioSegmentPlayerObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.53.1
                    @Override // com.kwai.video.stannis.observers.AudioSegmentPlayerObserver
                    public void onError(String str3, String str4, AudioSegmentPlayerObserver.ErrorType errorType) {
                        com.kwai.video.arya.observers.AudioSegmentPlayerObserver.ErrorType errorType2 = com.kwai.video.arya.observers.AudioSegmentPlayerObserver.ErrorType.None;
                        int i12 = AnonymousClass89.f28737b[errorType.ordinal()];
                        if (i12 != 1) {
                            if (i12 == 2) {
                                errorType2 = com.kwai.video.arya.observers.AudioSegmentPlayerObserver.ErrorType.ErrorIo;
                            } else if (i12 == 3) {
                                errorType2 = com.kwai.video.arya.observers.AudioSegmentPlayerObserver.ErrorType.Unknown;
                            } else if (i12 == 4) {
                                errorType2 = com.kwai.video.arya.observers.AudioSegmentPlayerObserver.ErrorType.Malformed;
                            } else if (i12 == 5) {
                                errorType2 = com.kwai.video.arya.observers.AudioSegmentPlayerObserver.ErrorType.NotSupported;
                            }
                        }
                        bVar.onFinished(str3, errorType2);
                    }

                    @Override // com.kwai.video.stannis.observers.AudioSegmentPlayerObserver
                    public void onFinished(String str3, String str4) {
                        bVar.onFinished(str3, com.kwai.video.arya.observers.AudioSegmentPlayerObserver.ErrorType.None);
                    }

                    @Override // com.kwai.video.stannis.observers.AudioSegmentPlayerObserver
                    public void onProgressed(String str3, String str4, float f12, float f13) {
                        bVar.onProgressed(str3, f12, f13);
                    }

                    @Override // com.kwai.video.stannis.observers.AudioSegmentPlayerObserver
                    public void onStartMixing(String str3, String str4, long j12) {
                        bVar.onStartMixing(str3, str4, j12);
                    }
                });
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final long j12, final String str, final String str2, final String str3, final String str4, final String str5, final int i12, final int i13, final com.kwai.video.arya.a.d dVar) {
        final KaraokeScoreObserver karaokeScoreObserver = new KaraokeScoreObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.57
            @Override // com.kwai.video.stannis.observers.KaraokeScoreObserver
            public void onScore(com.kwai.video.stannis.KaraokeScore karaokeScore) {
                dVar.onScore(new KaraokeScore(karaokeScore.liveID, karaokeScore.songID, karaokeScore.songType, karaokeScore.startTs, karaokeScore.stopTs, karaokeScore.scores));
            }

            @Override // com.kwai.video.stannis.observers.KaraokeScoreObserver
            public void onScore(String str6, int i14, int i15, int i16) {
                dVar.onScore(str2, i14, i15, i16);
            }
        };
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.58
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp aryaAudioEngineProxyImp = AryaAudioEngineProxyImp.this;
                aryaAudioEngineProxyImp.f28519d = new c(aryaAudioEngineProxyImp, null);
                AryaAudioEngineProxyImp.this.f28519d.f28754b = j12;
                AryaAudioEngineProxyImp.this.f28519d.f28755c = str;
                AryaAudioEngineProxyImp.this.f28519d.f28756d = str2;
                AryaAudioEngineProxyImp.this.f28519d.f28757e = str3;
                AryaAudioEngineProxyImp.this.f28519d.f28758f = str4;
                AryaAudioEngineProxyImp.this.f28519d.f28759g = str5;
                AryaAudioEngineProxyImp.this.f28519d.f28760h = i12;
                AryaAudioEngineProxyImp.this.f28519d.f28761i = i13;
                AryaAudioEngineProxyImp.this.f28519d.f28762j = karaokeScoreObserver;
                AryaAudioEngineProxyImp.this.f28516a.startKaraokeScore(j12, str, str2, str3, str4, str5, i12, i13, karaokeScoreObserver);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final AryaContext aryaContext) {
        Log.d("AryaAudioEngineProxyImp", "reconfig");
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.69
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.setAudioInputVolume(aryaContext.f28764a);
                AryaAudioEngineProxyImp.this.f28516a.setReverbLevel(aryaContext.f28765b);
                AryaAudioEngineProxyImp.this.f28516a.setAudioVoiceEffectOption(aryaContext.f28766c);
                AryaAudioEngineProxyImp.this.f28516a.setAudioVoiceRoleOption(aryaContext.f28767d);
                AryaAudioEngineProxyImp.this.f28516a.setMuteMicrophone(aryaContext.f28768e);
                AryaAudioEngineProxyImp.this.f28516a.setMuteSpeaker(aryaContext.f28769f);
                AryaAudioEngineProxyImp.this.f28516a.setSpeakerOn(aryaContext.f28770g);
                AryaAudioEngineProxyImp.this.f28516a.setEnableNoiseSuppression(aryaContext.f28771h);
                AryaAudioEngineProxyImp.this.f28516a.setHowlingSuppressionMode(aryaContext.f28772i);
                Stannis stannis = AryaAudioEngineProxyImp.this.f28516a;
                AryaContext aryaContext2 = aryaContext;
                stannis.setMuteRemote(aryaContext2.f28773j, aryaContext2.f28774k);
                AryaAudioEngineProxyImp.this.f28516a.setBgmVolume(aryaContext.f28775l);
                AryaAudioEngineProxyImp.this.f28516a.setRemoteBgmVolume(aryaContext.f28776m);
                AryaAudioEngineProxyImp.this.f28516a.setMuteBgm(aryaContext.f28777n);
                AryaAudioEngineProxyImp.this.f28516a.setBgmPitch(aryaContext.f28778o);
                AryaAudioEngineProxyImp.this.f28516a.setSoundEffectVolume(aryaContext.f28779p);
                AryaAudioEngineProxyImp.this.f28516a.setMuteSoundEffect(aryaContext.f28780q);
                AryaAudioEngineProxyImp.this.f28516a.enableMixingAudioSegment(aryaContext.f28781r);
                if (aryaContext.f28782s) {
                    AryaAudioEngineProxyImp.this.f28516a.enableHeadphoneMonitor(false);
                } else {
                    AryaAudioEngineProxyImp.this.f28516a.disableHeadphoneMonitor();
                }
                AryaAudioEngineProxyImp.this.f28516a.setMuteChatOutBgm(aryaContext.f28783t);
                AryaAudioEngineProxyImp.this.f28516a.setRemoteMixVolume(aryaContext.f28784u);
                AryaAudioEngineProxyImp.this.f28516a.setKtvMode(aryaContext.f28785v);
                AryaAudioEngineProxyImp.this.f28516a.setCompressGain(aryaContext.f28786w);
                AryaAudioEngineProxyImp.this.f28516a.setBgmAutoSeekEnable(aryaContext.f28787x);
                AryaAudioEngineProxyImp.this.f28516a.setAgcMode(aryaContext.f28788y);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final RawAudioObserver rawAudioObserver, final int i12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.70
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28527l = new DataReadyObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.70.1
                    @Override // com.kwai.video.stannis.observers.DataReadyObserver
                    public void onDataReady(int i13, ByteBuffer byteBuffer, int i14, int i15, int i16, long j12, short s12, int i17) {
                        RawAudioObserver rawAudioObserver2 = rawAudioObserver;
                        if (rawAudioObserver2 != null) {
                            rawAudioObserver2.onRawAudio(i13, byteBuffer, i14, i15, i16, j12, s12, i17);
                        }
                    }
                };
                AryaAudioEngineProxyImp.this.f28528m = i12;
                AryaAudioEngineProxyImp.this.f28516a.AddBypassDataReadyObserver(AryaAudioEngineProxyImp.this.f28527l, AryaAudioEngineProxyImp.this.f28528m);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final String str, final byte[] bArr, final float f12, final boolean z12, final AudioBufferPlayObserver audioBufferPlayObserver) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.72
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.playAudioBuffer(str, bArr, f12, z12, new com.kwai.video.stannis.observers.AudioBufferPlayObserver() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.72.1
                    @Override // com.kwai.video.stannis.observers.AudioBufferPlayObserver
                    public void onCompleted(String str2) {
                        AnonymousClass72 anonymousClass72 = AnonymousClass72.this;
                        AudioBufferPlayObserver audioBufferPlayObserver2 = audioBufferPlayObserver;
                        if (audioBufferPlayObserver2 != null) {
                            audioBufferPlayObserver2.onCompleted(str);
                        }
                    }
                });
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final DataReadyObserver dataReadyObserver) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.82
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.startMultipleStreamMix(dataReadyObserver);
            }
        });
    }

    @Override // com.kwai.video.arya.a
    public void a(final int i12, final byte[] bArr, final int i13, final int i14, final int i15, final long j12) {
        this.f28521f.post(new Runnable() { // from class: com.kwai.video.arya.AryaAudioEngineProxyImp.87
            @Override // java.lang.Runnable
            public void run() {
                AryaAudioEngineProxyImp.this.f28516a.inputTrackData(i12, bArr, i13, i14, i15, j12);
            }
        });
    }

    Arya.AryaDeviceInfo a(StannisDeviceInfo stannisDeviceInfo) {
        if (stannisDeviceInfo == null) {
            return null;
        }
        Arya.AryaDeviceInfo aryaDeviceInfo = new Arya.AryaDeviceInfo();
        aryaDeviceInfo.f28505id = stannisDeviceInfo.getId();
        aryaDeviceInfo.name = stannisDeviceInfo.getName();
        aryaDeviceInfo.routeType = stannisDeviceInfo.getRouteType();
        return aryaDeviceInfo;
    }

    Arya.AryaDeviceInfo[] a(StannisDeviceInfo[] stannisDeviceInfoArr) {
        if (stannisDeviceInfoArr == null) {
            return null;
        }
        Arya.AryaDeviceInfo[] aryaDeviceInfoArr = new Arya.AryaDeviceInfo[stannisDeviceInfoArr.length];
        for (int i12 = 0; i12 != stannisDeviceInfoArr.length; i12++) {
            aryaDeviceInfoArr[i12] = a(stannisDeviceInfoArr[i12]);
        }
        return aryaDeviceInfoArr;
    }

    @Override // com.kwai.video.arya.a
    public boolean a(MediaProjection mediaProjection) {
        return this.f28516a.startAudioInnerCap(mediaProjection);
    }
}
