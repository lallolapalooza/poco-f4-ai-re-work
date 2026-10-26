package p003d;

import E.h;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import com.quicinc.voice.activation.asr.IResultListener;
import com.quicinc.voice.activation.libupdate.k;
import com.quicinc.voice.activation.receiver.PackageChangedReceiver;
import p005f.InterfaceC0010k;
import p007h.e;
import p010k.c;
import p011l.d;
import p013n.C0025g0;
import p013n.C0037m0;
import p013n.C0042p;
import p013n.C0052z;
import p013n.D;
import p013n.V;
import p015p.f;
import p015p.j;
import t.a;
import u.b;

/* JADX INFO: compiled from: Facade.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static g f715z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p016q.c f718c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f719d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f720e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u.a f721f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f722g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h f723h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final e f724i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0037m0 f725j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final com.quicinc.voice.activation.audiorecord.a f726k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final V f727l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PowerManager.WakeLock f728m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0025g0 f729n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final k f730o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final D f731p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final f f732q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final j f733r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final com.quicinc.voice.activation.audiorecord.b f734s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0052z f735t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0042p f736u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final F.e f737v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public p008i.g f738w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public IResultListener f739x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC0010k f740y;

    static {
        System.loadLibrary("qvajni");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g(Context context) {
        this.f716a = context;
        PackageChangedReceiver.c(context);
        c cVar = new c();
        this.f717b = cVar;
        this.f719d = a.a(context);
        this.f720e = b.e(context, cVar);
        d dVar = new d(context);
        this.f722g = dVar;
        p016q.c cVar2 = new p016q.c(dVar);
        this.f718c = cVar2;
        h hVar = new h(new f(this));
        this.f723h = hVar;
        e eVar = new e();
        this.f724i = eVar;
        C0037m0 c0037m0 = new C0037m0(hVar, eVar);
        this.f725j = c0037m0;
        com.quicinc.voice.activation.audiorecord.a aVar = new com.quicinc.voice.activation.audiorecord.a(c0037m0);
        this.f726k = aVar;
        this.f727l = new V(context, eVar, aVar);
        f fVar = new f(new d(this));
        this.f732q = fVar;
        j jVar = new j();
        this.f733r = jVar;
        D d2 = new D(fVar, jVar);
        this.f731p = d2;
        com.quicinc.voice.activation.audiorecord.b bVar = new com.quicinc.voice.activation.audiorecord.b(d2);
        this.f734s = bVar;
        this.f735t = new C0052z(context, fVar, jVar, bVar);
        this.f736u = new C0042p(context);
        this.f729n = new C0025g0(context);
        this.f730o = new k(context);
        this.f721f = u.a.c(context, cVar2);
        this.f737v = new F.e(context);
    }

    public static p008i.g c() {
        g gVar = f715z;
        if (gVar.f738w == null) {
            gVar.f738w = new p008i.g(d());
        }
        g gVar2 = f715z;
        if (gVar2.f739x == null) {
            gVar2.f739x = new b();
            g gVar3 = f715z;
            gVar3.f738w.r(gVar3.f739x);
        }
        return f715z.f738w;
    }

    public static Context d() {
        return f715z.f716a;
    }

    public static c e() {
        return f715z.f717b;
    }

    public static d f() {
        return f715z.f722g;
    }

    public static com.quicinc.voice.activation.audiorecord.a g() {
        return f715z.f726k;
    }

    public static InterfaceC0010k h() {
        return f715z.f740y;
    }

    public static k i() {
        return f715z.f730o;
    }

    public static p016q.c j() {
        return f715z.f718c;
    }

    public static u.a k() {
        return f715z.f721f;
    }

    public static com.quicinc.voice.activation.audiorecord.b l() {
        return f715z.f734s;
    }

    public static C0042p m() {
        return f715z.f736u;
    }

    public static C0052z n() {
        return f715z.f735t;
    }

    public static D o() {
        return f715z.f731p;
    }

    public static PackageManager p() {
        return f715z.f716a.getPackageManager();
    }

    public static C0025g0 q() {
        return f715z.f729n;
    }

    public static V r() {
        return f715z.f727l;
    }

    public static h s() {
        return f715z.f723h;
    }

    public static a t() {
        return f715z.f719d;
    }

    public static b u() {
        return f715z.f720e;
    }

    public static C0037m0 v() {
        return f715z.f725j;
    }

    public static F.e w() {
        return f715z.f737v;
    }

    public static void x(Context context) {
        f715z = new g(context);
    }

    public static void y(InterfaceC0010k interfaceC0010k) {
        f715z.f740y = interfaceC0010k;
    }

    public final synchronized void b() {
        if (this.f728m == null) {
            this.f728m = ((PowerManager) d().getSystemService("power")).newWakeLock(1, "Recognition");
        }
        if (this.f728m.isHeld()) {
            this.f728m.release();
        }
        this.f728m.acquire(500L);
    }
}
