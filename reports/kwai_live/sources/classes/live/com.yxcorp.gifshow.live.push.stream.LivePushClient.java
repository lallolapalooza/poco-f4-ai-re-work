package com.yxcorp.gifshow.live.push.stream;

import com.kwai.camerasdk.BlackImageCheckerCallback;
import com.kwai.camerasdk.Daenerys;
import com.kwai.chat.components.mydao.db.DBConstants;
import com.kwai.imsdk.util.StatisticsConstants;
import com.kwai.video.arya.QosInfo;
import com.kwai.video.ksmedialivekit.KSMediaLiveKit;
import com.kwai.video.ksmedialivekit.KwaiMiddleMediaLiveBuilder;
import com.yxcorp.bugly.CrashReporter;
import com.yxcorp.gifshow.Gsons;
import com.yxcorp.gifshow.live.music.LiveKtvReverbEffectFragment;
import com.yxcorp.gifshow.live.push.event.AudioEffectPlayEvent;
import com.yxcorp.gifshow.live.push.event.PushStateChangeEvent;
import com.yxcorp.gifshow.live.push.ui.p1;
import com.yxcorp.gifshow.util.d1;
import com.yxcorp.retrofit.model.KwaiException;
import com.yxcorp.utility.n1;
import com.yxcorp.utility.o1;
import com.yxcorp.utility.t1;
import hl0.a0;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import ln0.v;

/* JADX INFO: loaded from: classes8.dex */
public class LivePushClient {
    private static final Object Q = new Object();
    private String C;
    private Disposable H;
    private Disposable I;
    private OnLiveChatVideoFrameUpdateListener N;
    private OnLiveChatListener O;
    private OnLivePKListener P;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private KSMediaLiveKit f45775a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a0 f45776b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f45777c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private OnBadNetworkListener f45778d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private PushClientListener f45779e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f45780f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private QosInfo f45781g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f45782h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f45783i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private com.yxcorp.gifshow.live.music.c.b f45784j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private IPushBgmControl$BgmPushStateListener f45785k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f45786l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f45787m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f45788n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    p1 f45790p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f45791q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private Daenerys f45792r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private Disposable f45794t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private Disposable f45795u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f45796v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f45797w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private long f45798x;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private com.yxcorp.gifshow.live.music.f f45789o = LiveKtvReverbEffectFragment.C;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f45793s = 5;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private List<String> f45799y = new ArrayList();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private com.yxcorp.gifshow.live.push.stream.g f45800z = new com.yxcorp.gifshow.live.push.stream.g();
    private com.yxcorp.gifshow.live.push.stream.j A = com.yxcorp.gifshow.live.push.stream.j.LIVE_PK;
    private volatile boolean B = false;
    private io.netty.util.internal.d<KSMediaLiveKit.MediaLiveActiveSpeakerChangedListener> D = new io.netty.util.internal.d<>();
    private com.yxcorp.gifshow.live.push.stream.h E = null;
    private int F = -1;
    private String G = "BlackCheckToken";

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    private Function f45774J = new a();
    private int K = -1;
    private int L = -1;
    private boolean M = false;

    public interface OnBadNetworkListener {
        void onBadNetwork();
    }

    public interface OnLiveChatListener {
        void onLiveChatStart();

        void onLiveChatStop();
    }

    public interface OnLiveChatVideoFrameUpdateListener {
        void onLiveChatVideoFrameUpdate(byte[] bArr, int i12, int i13, int i14);
    }

    public interface OnLivePKListener {
        void onLivePKStart();

        void onLivePKStop();
    }

    public interface PushClientListener {
        void onQoSEventUpdated(int i12, String str);

        void onRestartPush();
    }

    class a implements Function<Observable<Throwable>, ObservableSource<?>> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ ObservableSource c(Throwable th2) throws Exception {
            if (th2 instanceof KwaiException) {
                KwaiException kwaiException = (KwaiException) th2;
                if (kwaiException.getErrorCode() == 1016013014) {
                    LivePushClient livePushClient = LivePushClient.this;
                    livePushClient.f45796v = true;
                    p1 p1Var = livePushClient.f45790p;
                    if (p1Var != null) {
                        p1Var.f46168f.f45554t.setValue(Boolean.TRUE);
                    }
                    return Observable.empty();
                }
                if (kwaiException.getErrorCode() == 1016013015) {
                    return Observable.error(th2);
                }
            }
            if (LivePushClient.this.f45793s <= 0) {
                return Observable.error(th2);
            }
            LivePushClient.m(LivePushClient.this);
            return Observable.timer(2000L, TimeUnit.MILLISECONDS).take(1L);
        }

        @Override // io.reactivex.functions.Function
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ObservableSource<?> apply(@e.a Observable<Throwable> observable) throws Exception {
            return observable.flatMap(new Function() { // from class: com.yxcorp.gifshow.live.push.stream.e
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return this.f45823a.c((Throwable) obj);
                }
            });
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.yxcorp.gifshow.live.push.stream.h f45802a;

        b(com.yxcorp.gifshow.live.push.stream.h hVar) {
            this.f45802a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            p1 p1Var = LivePushClient.this.f45790p;
            if (p1Var != null) {
                p1Var.f46168f.f45555u.setValue(this.f45802a);
                LivePushClient.this.f45790p.f46168f.f45554t.setValue(Boolean.FALSE);
            }
        }
    }

    class c implements Consumer<Throwable> {
        c() {
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th2) throws Exception {
            if (!(th2 instanceof KwaiException)) {
                LivePushClient.this.w0(new com.yxcorp.gifshow.live.push.stream.h(3, 0, th2.getMessage(), LivePushClient.this.f45791q ? com.yxcorp.gifshow.live.push.stream.h.a.BACKGROUND : com.yxcorp.gifshow.live.push.stream.h.a.FOREGROUND));
            } else {
                KwaiException kwaiException = (KwaiException) th2;
                LivePushClient.this.w0(new com.yxcorp.gifshow.live.push.stream.h(3, kwaiException.getErrorCode(), kwaiException.getMessage(), LivePushClient.this.f45791q ? com.yxcorp.gifshow.live.push.stream.h.a.BACKGROUND : com.yxcorp.gifshow.live.push.stream.h.a.FOREGROUND));
            }
        }
    }

    class d implements Consumer<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f45805a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f45806b;

        d(int i12, long j12) {
            this.f45805a = i12;
            this.f45806b = j12;
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th2) throws Exception {
            String str = "client_status: " + LivePushClient.this.L + " sync_status: " + this.f45805a;
            dv0.f.f65054f.h("LivePush").j("FAIL", str, new Object[0]);
            if (LivePushClient.this.f45799y.size() > 20) {
                LivePushClient.this.f45799y.remove(0);
            }
            LivePushClient.this.f45799y.add(0, d1.a(System.currentTimeMillis()) + DBConstants.FAIL + str);
            Disposable disposable = LivePushClient.this.f45795u;
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
            LivePushClient.this.f45795u = null;
            long j12 = this.f45806b;
            if (j12 >= 16) {
                LivePushClient.this.M = true;
            } else {
                LivePushClient livePushClient = LivePushClient.this;
                livePushClient.x0(j12 != 0 ? 2 * j12 : 2L, livePushClient.L);
            }
        }
    }

    class e implements BlackImageCheckerCallback {
        e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            LivePushClient.this.f45792r.h0(false);
        }

        @Override // com.kwai.camerasdk.BlackImageCheckerCallback
        public void onBlackImageDetected(int i12, String str) {
            LivePushClient.this.F = i12;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i12);
            sb2.append(" ");
            sb2.append(str);
            t1.m(new Runnable() { // from class: com.yxcorp.gifshow.live.push.stream.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f45824a.b();
                }
            }, LivePushClient.this.G);
        }
    }

    class f implements KSMediaLiveKit.MediaLiveBgmListener {
        f() {
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onCompleted(String str) {
            if (LivePushClient.this.f45785k != null) {
                LivePushClient.this.f45785k.onCompleted();
            }
            if (LivePushClient.this.f45786l) {
                LivePushClient.this.X0();
            }
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onError(String str, int i12) {
            if (LivePushClient.this.f45785k != null) {
                LivePushClient.this.f45785k.onError(i12);
            }
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onProgress(String str, float f12, float f13) {
            if (LivePushClient.this.f45785k != null) {
                LivePushClient.this.f45785k.onProgressed(f12, f13);
            }
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onStart(String str) {
        }
    }

    class g implements KSMediaLiveKit.MediaLiveBgmListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f45810a;

        g(LivePushClient livePushClient, String str) {
            this.f45810a = str;
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onCompleted(String str) {
            org.greenrobot.eventbus.c.e().o(new AudioEffectPlayEvent(2, this.f45810a));
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onError(String str, int i12) {
            org.greenrobot.eventbus.c.e().o(new AudioEffectPlayEvent(2, this.f45810a));
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onProgress(String str, float f12, float f13) {
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveBgmListener
        public void onStart(String str) {
        }
    }

    static /* synthetic */ class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f45811a;

        static {
            int[] iArr = new int[com.yxcorp.gifshow.live.music.c.b.values().length];
            f45811a = iArr;
            try {
                iArr[com.yxcorp.gifshow.live.music.c.b.ACCOMPANIMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "LivePushClient$17.class", "<clinit>", -1);
                CrashReporter.logCheckedException(e12, "LivePushClient$17.class", "<clinit>", -1);
            }
            try {
                f45811a[com.yxcorp.gifshow.live.music.c.b.GUIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "LivePushClient$17.class", "<clinit>", -1);
                CrashReporter.logCheckedException(e13, "LivePushClient$17.class", "<clinit>", -1);
            }
            try {
                f45811a[com.yxcorp.gifshow.live.music.c.b.ORIGIN.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "LivePushClient$17.class", "<clinit>", -1);
                CrashReporter.logCheckedException(e14, "LivePushClient$17.class", "<clinit>", -1);
            }
        }
    }

    class i implements ux.a.InterfaceC1771a {
        i(LivePushClient livePushClient) {
        }

        @Override // ux.a.InterfaceC1771a
        public void d(String str, String str2) {
            dv0.f.f65054f.h("arya").b(str, str2, new Object[0]);
        }

        @Override // ux.a.InterfaceC1771a
        public void e(String str, String str2) {
            dv0.f.f65054f.h("arya").c(str, str2, new Object[0]);
        }

        @Override // ux.a.InterfaceC1771a
        public void i(String str, String str2) {
            dv0.f.f65054f.h("arya").g(str, str2, new Object[0]);
        }

        @Override // ux.a.InterfaceC1771a
        public void w(String str, String str2) {
            dv0.f.f65054f.h("arya").k(str, str2, new Object[0]);
        }
    }

    class j implements sx.a {
        j(LivePushClient livePushClient) {
        }
    }

    class k implements KSMediaLiveKit.MediaLiveErrorListener {
        k() {
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveErrorListener
        public void onError(int i12, String str) {
            LivePushClient.this.E = new com.yxcorp.gifshow.live.push.stream.h(i12, str);
        }
    }

    class l implements KSMediaLiveKit.QosExtraInterface {
        l() {
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.QosExtraInterface
        public String getQosExtra() {
            com.google.gson.l lVar = new com.google.gson.l();
            p1 p1Var = LivePushClient.this.f45790p;
            if (p1Var != null) {
                lVar.L("daenerys", p1Var.f46165c);
            }
            lVar.I("blackImage", Integer.valueOf(LivePushClient.this.F));
            com.kscorp.oversea.platform.app.m mVar = com.kscorp.oversea.platform.app.m.f18287b;
            if (mVar.a().d() != null) {
                lVar.I("client_time", mVar.a().d());
            } else {
                mVar.a().b(null);
            }
            LivePushClient.this.F = -1;
            return lVar.toString();
        }
    }

    class m implements KSMediaLiveKit.MediaLiveStatusListener {
        m() {
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveStatusListener
        public void onStatus(int i12, int i13, String str) {
            if (i12 == 4) {
                LivePushClient livePushClient = LivePushClient.this;
                livePushClient.f45796v = false;
                if (livePushClient.E == null) {
                    LivePushClient.this.E = new com.yxcorp.gifshow.live.push.stream.h(0, "");
                }
                LivePushClient.this.E.f(1);
                LivePushClient.this.E.e(LivePushClient.this.f45791q ? com.yxcorp.gifshow.live.push.stream.h.a.BACKGROUND : com.yxcorp.gifshow.live.push.stream.h.a.FOREGROUND);
                LivePushClient livePushClient2 = LivePushClient.this;
                livePushClient2.w0(livePushClient2.E);
                if (!LivePushClient.this.f45791q) {
                    org.greenrobot.eventbus.c.e().o(new PushStateChangeEvent(jj0.l.f75036d1, false));
                }
                nm0.a.v(false);
                return;
            }
            if (i12 == 2) {
                LivePushClient.A(0);
                if (LivePushClient.this.f45776b.isPreLiving) {
                    LivePushClient.this.q0();
                }
                org.greenrobot.eventbus.c.e().o(new PushStateChangeEvent(jj0.l.f75090s, true));
                nm0.a.v(true);
                return;
            }
            if (i12 == 3) {
                if (LivePushClient.this.f45779e != null) {
                    LivePushClient.this.f45779e.onRestartPush();
                }
                LivePushClient.this.f45798x = System.currentTimeMillis();
                org.greenrobot.eventbus.c.e().o(new PushStateChangeEvent(jj0.l.f75056i1, false));
            }
        }
    }

    class n implements Consumer<Long> {
        n(LivePushClient livePushClient) {
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Long l12) throws Exception {
            org.greenrobot.eventbus.c.e().o(new PushStateChangeEvent(jj0.l.f75040e1, false));
        }
    }

    class o implements Consumer<Long> {
        o() {
        }

        @Override // io.reactivex.functions.Consumer
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Long l12) throws Exception {
            LivePushClient livePushClient = LivePushClient.this;
            livePushClient.w0(new com.yxcorp.gifshow.live.push.stream.h(4, 0, "", livePushClient.f45791q ? com.yxcorp.gifshow.live.push.stream.h.a.BACKGROUND : com.yxcorp.gifshow.live.push.stream.h.a.FOREGROUND));
        }
    }

    class p implements KSMediaLiveKit.MediaLiveEventListener {
        p() {
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveEventListener
        public void onEvent(int i12, String str) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("MediaLiveEventListener\ttype:\t");
            sb2.append(i12);
            if (i12 == 1) {
                if (LivePushClient.this.f45778d != null) {
                    LivePushClient.this.f45778d.onBadNetwork();
                }
                LivePushClient.F(LivePushClient.this);
                return;
            }
            if (i12 == 2) {
                if (LivePushClient.this.O != null) {
                    LivePushClient.this.O.onLiveChatStart();
                }
            } else if (i12 == 3) {
                if (LivePushClient.this.O != null) {
                    LivePushClient.this.O.onLiveChatStop();
                }
            } else if (i12 == 4) {
                if (LivePushClient.this.P != null) {
                    LivePushClient.this.P.onLivePKStart();
                }
            } else {
                if (i12 != 5 || LivePushClient.this.P == null) {
                    return;
                }
                LivePushClient.this.P.onLivePKStop();
            }
        }
    }

    class q implements KSMediaLiveKit.MediaLiveMediaDataListener {
        q() {
        }

        @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveMediaDataListener
        public void onVideoDecoded(String str, ByteBuffer byteBuffer, int i12, int i13, int i14, int i15) {
            if (LivePushClient.this.N != null) {
                byte[] bArr = new byte[byteBuffer.remaining()];
                byteBuffer.get(bArr);
                try {
                    LivePushClient.this.N.onLiveChatVideoFrameUpdate(bArr, i12, i13, i14);
                } catch (NullPointerException e12) {
                    CrashReporter.logCheckedException(e12, "LivePushClient$9.class", "onVideoDecoded", -82);
                    CrashReporter.logCheckedException(e12, "LivePushClient$9.class", "onVideoDecoded", -82);
                    e12.printStackTrace();
                }
                byteBuffer.position(0);
                byteBuffer.put(bArr);
            }
        }
    }

    public enum r {
        UNKNOWN,
        CDN,
        ORIGIN
    }

    public LivePushClient(a0 a0Var, p1 p1Var, boolean z12, String str) {
        this.f45797w = false;
        this.f45790p = p1Var;
        this.C = str;
        g0(a0Var);
        this.f45797w = z12;
    }

    static /* synthetic */ int A(int i12) {
        return i12;
    }

    static /* synthetic */ int F(LivePushClient livePushClient) {
        int i12 = livePushClient.f45780f;
        livePushClient.f45780f = i12 + 1;
        return i12;
    }

    private String H(a0 a0Var) {
        String str = a0Var.videoPushRes;
        if (!n1.p(str)) {
            return str;
        }
        com.yxcorp.gifshow.live.model.a aVar = new com.yxcorp.gifshow.live.model.a();
        aVar.liveStreamConfig = a0Var.aryaConfig;
        aVar.pushRtmpUrl = a0Var.a();
        aVar.mClientConfigStr = a0Var.videoConfig;
        return Gsons.f36335b.v(aVar);
    }

    private void I(com.yxcorp.gifshow.live.music.c.b bVar) {
        float f12 = this.f45787m;
        if (f12 != -1.0f) {
            this.f45788n += this.f45782h - f12;
        }
        if (bVar == com.yxcorp.gifshow.live.music.c.b.ORIGIN) {
            this.f45787m = this.f45782h;
        } else {
            this.f45787m = -1.0f;
        }
    }

    private void L() {
        Disposable disposable = this.H;
        if (disposable != null && !disposable.isDisposed()) {
            this.H.dispose();
        }
        Disposable disposable2 = this.I;
        if (disposable2 == null || disposable2.isDisposed()) {
            return;
        }
        this.I.dispose();
    }

    private void R0(String str, String str2, String str3, String str4) {
    }

    private void S0() {
        if (this.f45775a != null) {
            qn0.r.f86875a.logCustomEvent("LIVE_START_PUSH", this.f45776b.liveStreamId);
            this.f45775a.startPush(this.f45792r, "PHONE_AUDIO".equals(this.C) ? 2 : 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l0(Map map) {
        Iterator<KSMediaLiveKit.MediaLiveActiveSpeakerChangedListener> it2 = this.D.iterator();
        while (it2.hasNext()) {
            it2.next().onActiveSpeakerChanged(map);
        }
    }

    static /* synthetic */ int m(LivePushClient livePushClient) {
        int i12 = livePushClient.f45793s;
        livePushClient.f45793s = i12 - 1;
        return i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m0(Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            Disposable disposable = this.H;
            if (disposable != null && !disposable.isDisposed()) {
                this.H.dispose();
            }
            Disposable disposable2 = this.I;
            if (disposable2 != null && !disposable2.isDisposed()) {
                this.I.dispose();
                org.greenrobot.eventbus.c.e().o(new PushStateChangeEvent(jj0.l.f75090s, true));
            }
        } else {
            Disposable disposable3 = this.H;
            if (disposable3 != null && !disposable3.isDisposed()) {
                this.H.dispose();
            }
            TimeUnit timeUnit = TimeUnit.SECONDS;
            Observable<Long> observableTake = Observable.timer(5L, timeUnit).take(1L);
            Scheduler scheduler = sd1.g.f89569a;
            this.H = observableTake.observeOn(scheduler).subscribe(new n(this));
            Disposable disposable4 = this.I;
            if (disposable4 != null && !disposable4.isDisposed()) {
                this.I.dispose();
            }
            this.I = Observable.timer(60L, timeUnit).take(1L).observeOn(scheduler).subscribe(new o());
        }
        b1(bool.booleanValue() ? 1 : 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n0(Object obj) throws Exception {
        this.f45796v = true;
        p1 p1Var = this.f45790p;
        if (p1Var != null) {
            p1Var.f46168f.f45554t.setValue(Boolean.TRUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o0(int i12, com.yxcorp.retrofit.model.c cVar) throws Exception {
        String str = "client_status: " + this.L + " sync_status: " + i12;
        dv0.f.f65054f.h("LivePush").j("SUCCESS", str, new Object[0]);
        if (this.f45799y.size() > 20) {
            this.f45799y.remove(0);
        }
        this.f45799y.add(0, d1.a(System.currentTimeMillis()) + " SUCCESS " + str);
        Disposable disposable = this.f45795u;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        this.f45795u = null;
        this.K = i12;
        this.M = false;
        int i13 = this.L;
        if (i13 != i12) {
            x0(0L, i13);
        }
    }

    private void p0() {
        this.f45800z.d().subscribe(new Consumer() { // from class: com.yxcorp.gifshow.live.push.stream.b
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                this.f45819a.m0((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w0(com.yxcorp.gifshow.live.push.stream.h hVar) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setMediaLiveStatusListener(null);
            a1();
        }
        t1.m(new b(hVar), Q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void x0(long j12, final int i12) {
        Disposable disposable = this.f45795u;
        if (disposable == null || disposable.isDisposed()) {
            dv0.f.f65054f.h("LivePush").j("START", "client_status: " + this.L + " sync_status: " + i12, new Object[0]);
            this.f45795u = com.yxcorp.gifshow.live.a.a().syncPushStatus(this.f45776b.liveStreamId, i12).delay(j12, TimeUnit.SECONDS).observeOn(sd1.g.f89570b).subscribeOn(sd1.g.f89569a).subscribe(new Consumer() { // from class: com.yxcorp.gifshow.live.push.stream.d
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) throws Exception {
                    this.f45821a.o0(i12, (com.yxcorp.retrofit.model.c) obj);
                }
            }, new d(i12, j12));
        }
    }

    public void A0(float f12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setRemoteBgmVolume(f12);
        }
    }

    public void B0(int i12) {
        I(this.f45784j);
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.seekBgm(i12);
        }
        if (this.f45784j == com.yxcorp.gifshow.live.music.c.b.ORIGIN) {
            this.f45787m = i12;
        }
    }

    public void C0(int i12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setBgmPitch(i12);
        }
    }

    public void D0(IPushBgmControl$BgmPushStateListener iPushBgmControl$BgmPushStateListener) {
        this.f45785k = iPushBgmControl$BgmPushStateListener;
    }

    public void E0(Daenerys daenerys) {
        this.f45792r = daenerys;
        daenerys.U(new e());
    }

    public void F0(boolean z12) {
        this.B = z12;
    }

    public void G(KSMediaLiveKit.MediaLiveActiveSpeakerChangedListener mediaLiveActiveSpeakerChangedListener) {
        this.D.add(mediaLiveActiveSpeakerChangedListener);
    }

    public void G0(boolean z12) {
        this.f45775a.setMirror(!z12);
    }

    public void H0(com.yxcorp.gifshow.live.push.stream.j jVar) {
        this.A = jVar;
    }

    public void I0(OnBadNetworkListener onBadNetworkListener) {
        this.f45778d = onBadNetworkListener;
    }

    public boolean J(com.yxcorp.gifshow.live.music.c.b bVar) {
        this.f45784j = bVar;
        I(bVar);
        if (this.f45775a == null) {
            return false;
        }
        int i12 = h.f45811a[bVar.ordinal()];
        if (i12 != 1) {
            return i12 != 2 ? this.f45775a.updateBgmIndex(0, 0) : this.f45775a.updateBgmIndex(0, 1);
        }
        return this.f45775a.updateBgmIndex(1, 1);
    }

    public void J0(OnLiveChatVideoFrameUpdateListener onLiveChatVideoFrameUpdateListener) {
        this.N = onLiveChatVideoFrameUpdateListener;
    }

    public void K() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setRemoteBgmVolume(0.0f);
        }
    }

    public void K0(OnLiveChatListener onLiveChatListener) {
        this.O = onLiveChatListener;
    }

    public void L0(OnLivePKListener onLivePKListener) {
        this.P = onLivePKListener;
    }

    public void M(boolean z12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            if (z12) {
                kSMediaLiveKit.enableHeadphoneMonitor(d20.i.m3());
            } else {
                kSMediaLiveKit.disableHeadphoneMonitor();
            }
        }
    }

    public void M0(boolean z12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setMuteBgm(z12);
        }
    }

    public void N(boolean z12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setEnableNoiseSuppression(z12);
        }
    }

    public void N0(float f12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setBgmVolume(f12);
            this.f45775a.setRemoteBgmVolume(f12);
        }
    }

    public int O() {
        return this.f45780f;
    }

    public void O0(PushClientListener pushClientListener) {
        this.f45779e = pushClientListener;
    }

    public long P() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo == null) {
            return 0L;
        }
        return qosInfo.getCpuFreqDnCnt();
    }

    public void P0(com.yxcorp.gifshow.live.music.f fVar) {
        if (fVar == null) {
            fVar = LiveKtvReverbEffectFragment.C;
        }
        this.f45789o = fVar;
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setReverbLevel(fVar.mReverbLevel);
        }
    }

    public long Q() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo == null) {
            return 0L;
        }
        return qosInfo.getCpuFreqUpCnt();
    }

    public void Q0(float f12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.setAudioInputVolume(f12);
        }
    }

    public long R() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo == null) {
            return 0L;
        }
        return qosInfo.getDroppedVideoFrames();
    }

    public long S() {
        if (this.f45781g == null) {
            e1();
        }
        return this.f45781g.getEncodedFrames();
    }

    public String T() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo != null) {
            return qosInfo.getIdc();
        }
        return null;
    }

    public void T0(@e.a String str, String str2, String str3, String str4, String str5, String str6, boolean z12) {
        if (this.f45775a != null) {
            Y0();
            this.f45775a.startBgm(str, z12, new f());
        }
        if (n1.p(this.f45776b.liveStreamId) || n1.p(str6) || n1.p(str4)) {
            return;
        }
        this.f45786l = true;
        R0(this.f45776b.liveStreamId, str4, str5, str6);
    }

    public KSMediaLiveKit U() {
        return this.f45775a;
    }

    public void U0(@e.a String str, String str2, String str3, boolean z12) {
        T0(str, str2, str3, "", "", "", z12);
    }

    public long V() {
        return (long) this.f45783i;
    }

    public void V0(byte[] bArr) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.startRtc(bArr, this.A.getType());
        }
    }

    public String W() {
        String strSubstring = this.f45776b.a().substring(7);
        return strSubstring.substring(0, strSubstring.indexOf("/"));
    }

    public void W0() {
        if (ja0.a.b0() || this.f45776b == null) {
            return;
        }
        this.f45777c = false;
        S0();
    }

    public String X() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo == null) {
            return null;
        }
        return qosInfo.getRtmpHostIP();
    }

    public com.yxcorp.gifshow.live.push.stream.g Y() {
        return this.f45800z;
    }

    public void Y0() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.stopBgm();
        }
        if (this.f45786l) {
            X0();
        }
        this.f45782h = 0.0f;
        this.f45783i = 0.0f;
    }

    public String Z() {
        return this.f45799y.size() > 0 ? o1.m("\n ", this.f45799y) : "";
    }

    public void Z0() {
        if (this.f45775a != null) {
            this.B = true;
            this.f45775a.stopRtc();
        }
    }

    public String a0() {
        return this.K == 2 ? "FAIL" : "SUCCESS";
    }

    public void a1() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null && !this.f45777c) {
            kSMediaLiveKit.stopPush(null);
        }
        this.f45777c = true;
        Y0();
        L();
    }

    public String b0() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo != null) {
            return n1.p(qosInfo.getPushUrl()) ? this.f45781g.getLiveStreamId() : this.f45781g.getPushUrl();
        }
        return this.f45776b.a();
    }

    public void b1(int i12) {
        this.L = i12;
        if (!com.yxcorp.monitor.a.f61833c.e()) {
            this.M = true;
        } else if (this.L != this.K || this.M) {
            x0(0L, i12);
        }
    }

    public int c0() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo != null) {
            return qosInfo.getRtUploadNum();
        }
        return 0;
    }

    public void c1(String str) {
        v.b(this.f45775a, str);
    }

    public String d0() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo != null) {
            return qosInfo.getSdkVersion();
        }
        return null;
    }

    public void d1(long j12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.updateWallClockTime(j12);
        }
    }

    public long e0() {
        QosInfo qosInfo = this.f45781g;
        if (qosInfo == null) {
            return 0L;
        }
        return qosInfo.getUploadedKByte();
    }

    public void e1() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            this.f45781g = kSMediaLiveKit.getQosInfo();
        }
        if (this.f45781g == null) {
            this.f45781g = new QosInfo();
        }
    }

    public boolean f0() {
        return this.f45777c;
    }

    public void g0(a0 a0Var) {
        this.f45776b = a0Var;
        ux.a.g(new i(this));
        ux.a.d(true);
        new j(this);
        sx.c.a aVar = new sx.c.a();
        aVar.f89983c = 5;
        aVar.f89984d = StatisticsConstants.SEND_RESOURCE_MESSAGE_TIMEOUT_MS;
        sx.c cVar = new sx.c();
        cVar.f89980a = aVar;
        v.c(new sx.c.b(), v.a(a0Var.videoConfig));
        KwaiMiddleMediaLiveBuilder kwaiMiddleMediaLiveBuilderLivePkAspectRatio = new KwaiMiddleMediaLiveBuilder(uo.a.c()).appId("bdf6cbbde63333aa6bb9598bb90bc95283d19eb7").token("b053bfef99aac85f147e94fd5a13b335e04bff45").userId(com.yxcorp.gifshow.user.auth.a.f56866b.getId()).appVersion(uo.a.f93389f).kpn("KWAI.ME").livePushConfig(H(a0Var)).mediaLiveKitConfig(cVar).livePkAspectRatio(0.8888889f);
        if (ja0.a.a0()) {
            kwaiMiddleMediaLiveBuilderLivePkAspectRatio.enableMockFallbackTest(true);
        }
        KSMediaLiveKit kSMediaLiveKitBuild = kwaiMiddleMediaLiveBuilderLivePkAspectRatio.build();
        this.f45775a = kSMediaLiveKitBuild;
        kSMediaLiveKitBuild.setMediaLiveErrorListener(new k());
        this.f45775a.setQosExtraInterface(new l());
        this.f45775a.setMediaLiveStatusListener(new m());
        this.f45775a.prepareToPush();
        p0();
        if (!this.f45776b.isPreLiving) {
            this.f45796v = true;
            p1 p1Var = this.f45790p;
            if (p1Var != null) {
                p1Var.f46168f.f45554t.setValue(Boolean.TRUE);
            }
        }
        p1 p1Var2 = this.f45790p;
        if (p1Var2 == null || !"PHONE_AUDIO".equals(p1Var2.f46168f.I())) {
            return;
        }
        this.f45775a.setMediaLiveActiveSpeakerListener(new KSMediaLiveKit.MediaLiveActiveSpeakerChangedListener() { // from class: com.yxcorp.gifshow.live.push.stream.a
            @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit.MediaLiveActiveSpeakerChangedListener
            public final void onActiveSpeakerChanged(Map map) {
                this.f45818a.l0(map);
            }
        });
        this.f45775a.enableInsertActiveSpeakersInLiveStream(true);
    }

    public void h0() {
    }

    public void i0() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit == null) {
            return;
        }
        kSMediaLiveKit.setMediaLiveEventListener(new p());
        this.f45775a.setMediaLiveMediaDataListener(new q());
    }

    public boolean j0() {
        return this.B;
    }

    public boolean k0(boolean z12) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            return kSMediaLiveKit.isSupportHeadphoneMonitor(z12);
        }
        return false;
    }

    public void q0() {
        if (this.f45796v) {
            return;
        }
        this.f45793s = 0;
        this.f45794t = com.yxcorp.gifshow.live.a.a().startLive(this.f45776b.liveStreamId, this.f45797w).retryWhen(this.f45774J).subscribeOn(sd1.g.f89570b).observeOn(sd1.g.f89569a).subscribe(new Consumer() { // from class: com.yxcorp.gifshow.live.push.stream.c
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                this.f45820a.n0(obj);
            }
        }, new c());
    }

    public void r0() {
        t1.k(Q);
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            if (!this.f45777c) {
                kSMediaLiveKit.stopPush(null);
            }
            this.f45775a.destroy();
            this.f45775a = null;
        }
        t1.k(this.G);
        Daenerys daenerys = this.f45792r;
        if (daenerys != null) {
            daenerys.U(null);
            this.f45792r.h0(true);
            this.f45792r = null;
        }
        Disposable disposable = this.f45794t;
        if (disposable != null && !disposable.isDisposed()) {
            this.f45794t.dispose();
        }
        this.f45794t = null;
        Disposable disposable2 = this.f45795u;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.f45795u.dispose();
        }
        this.f45795u = null;
        ux.a.g(null);
    }

    public void s0() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.pause();
            this.f45791q = true;
            this.f45800z.c(true);
            L();
        }
    }

    public void t0() {
        KSMediaLiveKit kSMediaLiveKit;
        if (ja0.a.b0() || (kSMediaLiveKit = this.f45775a) == null) {
            return;
        }
        kSMediaLiveKit.resume();
        this.f45791q = false;
        this.f45800z.c(false);
    }

    public void u0() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.pauseBgm();
        }
    }

    public void v0(String str, String str2) {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.cleanSoundEffectCache();
            this.f45775a.playSoundEffect(str2, new g(this, str));
        }
    }

    public void y0(KSMediaLiveKit.MediaLiveActiveSpeakerChangedListener mediaLiveActiveSpeakerChangedListener) {
        this.D.remove(mediaLiveActiveSpeakerChangedListener);
    }

    public void z0() {
        KSMediaLiveKit kSMediaLiveKit = this.f45775a;
        if (kSMediaLiveKit != null) {
            kSMediaLiveKit.resumeBgm();
        }
    }
}
