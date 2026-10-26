package re;

import android.hardware.ConsumerIrManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.xiaomi.mitv.phone.remotecontroller.ir.Miir;
import vd.x0;
import wf.m0;
import wf.y;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f52558d = "IRManager";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f52559e = 19;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f52560f = "-----BEGIN CERTIFICATE-----\nMIICYzCCAcwCAQIwDQYJKoZIhvcNAQEEBQAwfTELMAkGA1UEBhMCQ04xDDAKBgNV\nBAgMA0JFSjEQMA4GA1UEBwwHQmVpSmluZzEPMA0GA1UECgwGRHVva2FuMQwwCgYD\nVQQLDANSJkQxDDAKBgNVBAMMA2JveDEhMB8GCSqGSIb3DQEJARYSc2VydmljZUBk\n";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static volatile g f52561g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f52563b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f52564c = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConsumerIrManager f52562a = (ConsumerIrManager) x0.a().getSystemService("consumer_ir");

    public g() {
        new Thread(new Runnable() { // from class: re.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f52553a.g();
            }
        }).start();
    }

    public static g d() {
        if (f52561g == null) {
            synchronized (g.class) {
                if (f52561g == null) {
                    f52561g = new g();
                }
            }
        }
        return f52561g;
    }

    public static String e() {
        return f52560f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        Looper.prepare();
        this.f52563b = new Handler();
        Looper.loop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(boolean z10, int i10, int[] iArr) {
        if (z10) {
            p(i10, iArr);
            return;
        }
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i11 = 0; i11 < length; i11++) {
            iArr2[i11] = (int) ((((long) iArr[i11]) * 1000000) / ((long) i10));
        }
        p(i10, iArr2);
    }

    public boolean f() {
        ConsumerIrManager consumerIrManager = this.f52562a;
        return consumerIrManager != null && consumerIrManager.hasIrEmitter();
    }

    public void j(final int i10, final int[] iArr, final boolean z10, boolean z11) {
        if (z11) {
            m0.b.f64481a.e(x0.a());
        }
        if (iArr == null || iArr.length == 0) {
            y.m(f52558d, "pattern null");
        } else {
            this.f52563b.post(new Runnable() { // from class: re.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f52554a.h(z10, i10, iArr);
                }
            });
        }
    }

    public void k(df.e eVar) {
        l(eVar, true, false);
    }

    public void l(df.e eVar, boolean z10, boolean z11) {
        df.d dVarF;
        String str;
        df.c cVar;
        if (eVar == null) {
            return;
        }
        StringBuilder sbA = android.support.v4.media.e.a("sendIR:");
        sbA.append(eVar.e());
        Log.e(x0.f61212a, sbA.toString());
        if (z10) {
            m0.b.f64481a.e(x0.a());
        }
        if (this.f52564c) {
            dVarF = eVar.c();
            str = "发正码";
        } else {
            dVarF = eVar.f();
            if (dVarF != null) {
                str = "发反码";
            } else {
                dVarF = eVar.c();
                str = "没有反码，发正码";
            }
        }
        n(str);
        this.f52564c = !this.f52564c;
        if (dVarF instanceof df.f) {
            cVar = (df.f) dVarF;
            if (eVar.b() <= 0) {
                return;
            }
        } else {
            if (!(dVarF instanceof df.a)) {
                if (dVarF instanceof df.b) {
                    j(eVar.b(), ((df.b) dVarF).b(), true, false);
                    return;
                }
                return;
            }
            cVar = (df.a) dVarF;
        }
        m(eVar.b(), cVar.c(), cVar.b());
    }

    public final void m(final int i10, final String str, final String str2) {
        this.f52563b.post(new Runnable() { // from class: re.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f52549a.i(i10, str, str2);
            }
        });
    }

    public final void n(String str) {
    }

    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public void i(int i10, String str, String str2) {
        Miir.d(x0.a(), i10, str, str2);
    }

    public void p(int i10, int[] iArr) {
        if (this.f52562a.hasIrEmitter()) {
            this.f52562a.transmit(i10, iArr);
        }
    }
}
