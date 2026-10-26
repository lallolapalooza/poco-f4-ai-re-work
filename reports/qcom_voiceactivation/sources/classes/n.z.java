package p013n;

import G.i;
import G.q;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.quicinc.voice.activation.audiorecord.b;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import p003d.g;
import p010k.a;
import p015p.f;
import p015p.j;

/* JADX INFO: renamed from: n.z, reason: case insensitive filesystem */
/* JADX INFO: compiled from: OneVoiceRecognitionHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class C0052z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1107a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final L f1109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f1110d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RecognitionEvent f1113g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public i f1114h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public UUID f1115i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public UUID f1116j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public j f1117k;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ConcurrentHashMap f1111e = new ConcurrentHashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicBoolean f1112f = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HandlerC0051y f1108b = new HandlerC0051y(new Runnable() { // from class: n.r
        @Override // java.lang.Runnable
        public final void run() {
            this.f1078a.p();
        }
    });

    public C0052z(Context context, f fVar, j jVar, b bVar) {
        this.f1107a = context;
        this.f1110d = bVar;
        this.f1117k = jVar;
        this.f1109c = new L(fVar, jVar, bVar);
    }

    public static void j(Bundle bundle, Consumer consumer) {
        StringBuilder sb = new StringBuilder();
        boolean z2 = bundle.getBoolean("algorithm.success");
        sb.append("success=");
        sb.append(z2);
        consumer.accept(sb.toString());
    }

    public final boolean a(a aVar) {
        q.d("BindClientService");
        ComponentName componentName = new ComponentName(aVar.b(), aVar.c());
        boolean zB = b(componentName);
        q.m();
        this.f1114h.s(componentName, zB);
        return zB;
    }

    public final boolean b(ComponentName componentName) {
        if (this.f1111e.containsKey(componentName.getPackageName())) {
            return true;
        }
        Intent intent = new Intent();
        if (this.f1107a.getPackageName().equals(componentName.getPackageName())) {
            intent.putExtra("isOneVoice", true);
        }
        intent.setComponent(componentName);
        ServiceConnectionC0050x serviceConnectionC0050x = new ServiceConnectionC0050x(this);
        this.f1111e.put(componentName.getPackageName(), serviceConnectionC0050x);
        q.h("bindService[" + componentName + "]");
        q.i("bindService");
        return this.f1107a.bindService(intent, serviceConnectionC0050x, 1048577);
    }

    public final boolean c(a aVar, p010k.b bVar) {
        return (aVar != null && bVar.q(aVar.b()) && h(aVar.b())) ? false : true;
    }

    public void d() {
        ArrayList arrayListJ = this.f1117k.j();
        for (p010k.b bVar : new ArrayList(g.e().f().values())) {
            if (arrayListJ.contains(bVar.o())) {
                e(bVar);
            }
        }
    }

    public final void e(p010k.b bVar) {
        if (bVar != null && g.o().n(bVar).d()) {
            g.k().d(bVar);
        }
    }

    public final boolean f(p016q.a aVar, RecognitionEvent recognitionEvent) {
        if (!recognitionEvent.h() || aVar.f(recognitionEvent.c()) != null) {
            return true;
        }
        q.h("cannot find client group for multi kw model");
        return false;
    }

    public final boolean g(String str) {
        return g.p().checkPermission("android.permission.RECORD_AUDIO", str) == 0;
    }

    public final boolean h(String str) {
        if (str != null) {
            try {
                if (g.p().getPackageInfo(str, 0) != null) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public void i(UUID uuid, RecognitionEvent recognitionEvent) {
        q.h("onRecognition process uuid = " + uuid);
        p016q.a aVarH = g.j().h(uuid);
        if (aVarH == null) {
            q.j("no associated model group for this RecognitionEvent");
        }
        if (this.f1112f.compareAndSet(false, true)) {
            if (!f(aVarH, recognitionEvent)) {
                if (this.f1112f.compareAndSet(true, false)) {
                    q.h("try Restart Recognition");
                    g.l().s();
                    q.h("exception  - mRecognizing=" + this.f1112f.get());
                    return;
                }
                return;
            }
            this.f1113g = recognitionEvent;
            i iVarB = G.g.b(aVarH.m());
            this.f1114h = iVarB;
            iVarB.o();
            this.f1114h.w(this.f1113g);
            n(aVarH, recognitionEvent);
            Bundle bundleM = this.f1109c.m();
            q.h("msResponse = " + bundleM);
            if (!bundleM.getBoolean("1.5Stage.success", false)) {
                p();
                return;
            }
            p010k.b bVarF = aVarH.f(bundleM.getInt("1.5Stage.detectedIndex", -1));
            if (bVarF == null) {
                q.h("cannot find client group");
                p();
                return;
            }
            a aVarL = bVarF.l();
            if (aVarL == null && !bVarF.r()) {
                aVarL = (a) bVarF.m().get(0);
            }
            if (c(aVarL, bVarF)) {
                p();
                e(bVarF);
                return;
            }
            if (!g(aVarL.b())) {
                q.h("granted=false, client=" + aVarL.b());
                p();
                e(bVarF);
                return;
            }
            o(bVarF, recognitionEvent);
            if (!a(aVarL)) {
                q.j("bind client service failure");
                p();
            }
            q.h("mRecognizing=" + this.f1112f.get());
        }
    }

    public void k(String str, InterfaceC0047u interfaceC0047u, Bundle bundle) {
        q.h("registerAudioReceiverCallback - packageName=" + str);
        q.i("registerAudioReceiverCallback");
        i iVar = this.f1114h;
        if (iVar != null) {
            iVar.e(str);
        }
        q.d("NoticeClient");
        Bundle bundleN = this.f1109c.n();
        if (bundleN == null || !bundleN.getBoolean("algorithm.success")) {
            l(interfaceC0047u, bundleN);
        } else {
            m(interfaceC0047u, bundleN);
        }
        q.p("DeliverRecognitionEvent", new String[0]);
        q.m();
        q.l("Recognition", 1);
    }

    public final void l(InterfaceC0047u interfaceC0047u, Bundle bundle) {
        q.h("responseFailure");
        interfaceC0047u.c(this.f1110d.j(), bundle);
        this.f1114h.g("\n\nQVA->Client onUtteranceVerificationFailure: " + System.currentTimeMillis());
        if (bundle != null) {
            j(bundle, new C0046t());
        }
    }

    public final void m(InterfaceC0047u interfaceC0047u, Bundle bundle) {
        q.h("responseSuccess");
        interfaceC0047u.b(this.f1110d.j(), bundle);
        this.f1114h.g("\n\nQVA->Client onUtteranceReceived: " + System.currentTimeMillis());
        if (bundle != null) {
            j(bundle, new C0045s());
        }
    }

    public final void n(p016q.a aVar, RecognitionEvent recognitionEvent) {
        this.f1108b.a();
        this.f1109c.w(aVar, recognitionEvent, this.f1114h);
        this.f1109c.v(aVar, recognitionEvent, this.f1114h);
        this.f1115i = aVar.l();
    }

    public final void o(p010k.b bVar, RecognitionEvent recognitionEvent) {
        this.f1109c.x(bVar, recognitionEvent, this.f1114h);
        this.f1116j = bVar.o();
    }

    public void p() {
        if (this.f1112f.compareAndSet(true, false)) {
            q.h("exception stopRecording");
            q.i("exception stopRecording");
            r();
            Iterator it = this.f1111e.values().iterator();
            while (it.hasNext()) {
                this.f1107a.unbindService((ServiceConnectionC0050x) it.next());
            }
            this.f1111e.clear();
            q.h("exception stopRecording - mRecognizing=" + this.f1112f.get());
        }
    }

    public void q(String str, InterfaceC0048v interfaceC0048v, Bundle bundle) {
        if (this.f1112f.compareAndSet(true, false)) {
            q.h("stopRecording - packageName=" + str);
            q.i("stopRecording ");
            r();
            Bundle bundleG = C0023f0.g();
            bundleG.putLong("AudioMetaData.stopRecordingTimestamp", System.currentTimeMillis());
            interfaceC0048v.a(bundleG);
            s(str);
            q.h("mRecognizing=" + this.f1112f.get());
        }
    }

    public final void r() {
        this.f1109c.y(this.f1115i, this.f1116j);
        this.f1109c.z();
        this.f1108b.b();
    }

    public final void s(String str) {
        q.h("unbindService - packageName=" + str);
        ServiceConnectionC0050x serviceConnectionC0050x = (ServiceConnectionC0050x) this.f1111e.remove(str);
        if (serviceConnectionC0050x != null) {
            this.f1107a.unbindService(serviceConnectionC0050x);
        }
    }
}
