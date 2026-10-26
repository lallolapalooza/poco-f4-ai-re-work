package p013n;

import G.i;
import G.q;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.quicinc.voice.activation.audiorecord.a;
import com.quicinc.voice.activation.engineservice.ForegroundService;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import p003d.g;
import p007h.e;
import p010k.b;
import v.j;

/* JADX INFO: compiled from: RecognitionHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap f958c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicBoolean f959d = new AtomicBoolean(false);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final U f960e = new U(new Runnable() { // from class: n.O
        @Override // java.lang.Runnable
        public final void run() {
            this.f952a.m();
        }
    });

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t0 f961f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public i f962g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public BigSoundModel f963h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public RecognitionEvent f964i;

    public V(Context context, e eVar, a aVar) {
        this.f956a = context;
        this.f957b = aVar;
        this.f961f = new t0(eVar, aVar);
    }

    public static void h(Bundle bundle, Consumer consumer) {
        StringBuilder sb = new StringBuilder();
        boolean z2 = bundle.getBoolean("algorithm.success");
        sb.append("success=");
        sb.append(z2);
        consumer.accept(sb.toString());
    }

    public final boolean a(p010k.a aVar) {
        q.d("BindClientService");
        ComponentName componentName = new ComponentName(aVar.b(), aVar.c());
        boolean zB = b(componentName);
        q.m();
        this.f962g.s(componentName, zB);
        return zB;
    }

    public final boolean b(ComponentName componentName) {
        if (this.f958c.containsKey(componentName.getPackageName())) {
            return true;
        }
        Intent intent = new Intent();
        if (this.f956a.getPackageName().equals(componentName.getPackageName())) {
            intent.putExtra("isOneVoice", false);
        }
        intent.setComponent(componentName);
        T t2 = new T(this);
        this.f958c.put(componentName.getPackageName(), t2);
        q.h("bindService[" + componentName + "]");
        q.i("bindService");
        return this.f956a.bindService(intent, t2, 1048577);
    }

    public final boolean c(p010k.a aVar, b bVar) {
        return (aVar != null && bVar.q(aVar.b()) && f(aVar.b())) ? false : true;
    }

    public final void d(String str) {
        b bVarE = g.e().e(str);
        if (bVarE == null) {
            return;
        }
        Iterator it = bVarE.i().values().iterator();
        while (it.hasNext()) {
            p(((BigSoundModel) it.next()).getSoundModelKey());
        }
        p010k.a aVarL = bVarE.l();
        if (aVarL != null) {
            g.u().c(str, aVarL.b());
        }
    }

    public final boolean e(String str) {
        return g.p().checkPermission("android.permission.RECORD_AUDIO", str) == 0;
    }

    public final boolean f(String str) {
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

    public void g(j jVar, RecognitionEvent recognitionEvent) {
        if (jVar == null || recognitionEvent == null) {
            return;
        }
        String strB = jVar.b();
        b bVarE = g.e().e(strB);
        p010k.a aVarL = bVarE.l();
        if (aVarL == null && !bVarE.r()) {
            aVarL = (p010k.a) bVarE.m().get(0);
        }
        if (c(aVarL, bVarE)) {
            d(strB);
            return;
        }
        if (!e(aVarL.b())) {
            q.h("granted=false, client=" + aVarL.b());
            d(strB);
            return;
        }
        BigSoundModel bigSoundModelH = bVarE.h(jVar);
        if (bigSoundModelH == null) {
            q.j("sound model is not found");
            return;
        }
        if (this.f959d.compareAndSet(false, true)) {
            this.f963h = bigSoundModelH;
            this.f964i = recognitionEvent;
            i iVarA = G.g.a(strB);
            this.f962g = iVarA;
            iVarA.o();
            this.f962g.i(this.f964i, this.f963h);
            ForegroundService.h();
            if (!a(aVarL)) {
                q.j("bind client service failure");
                m();
            }
            q.h("mRecognizing=" + this.f959d.get());
        }
    }

    public void i(String str, P p2, Bundle bundle) {
        q.h("registerAudioReceiverCallback - packageName=" + str);
        q.i("registerAudioReceiverCallback");
        i iVar = this.f962g;
        if (iVar != null) {
            iVar.e(str);
        }
        q.d("NoticeClient");
        Bundle bundleI = this.f961f.i();
        if (bundleI == null || !bundleI.getBoolean("algorithm.success")) {
            j(p2, bundleI);
        } else {
            k(p2, bundleI);
        }
        q.p("DeliverRecognitionEvent", new String[0]);
        q.m();
        q.l("Recognition", 1);
    }

    public final void j(P p2, Bundle bundle) {
        q.h("responseFailure");
        p2.c(this.f957b.i(), bundle);
        this.f962g.g("\n\nQVA->Client onUtteranceVerificationFailure: " + System.currentTimeMillis());
        if (bundle != null) {
            h(bundle, new C0046t());
        }
    }

    public final void k(P p2, Bundle bundle) {
        q.h("responseSuccess");
        p2.b(this.f957b.i(), bundle);
        this.f962g.g("\n\nQVA->Client onUtteranceReceived: " + System.currentTimeMillis());
        if (bundle != null) {
            h(bundle, new C0045s());
        }
    }

    public void l() {
        this.f960e.a();
        this.f961f.o(this.f963h, this.f964i, this.f962g);
        this.f961f.p(this.f963h, this.f964i, this.f962g);
        this.f963h = null;
    }

    public void m() {
        if (this.f959d.compareAndSet(true, false)) {
            q.h("exception stopRecording");
            q.i("exception stopRecording");
            ForegroundService.k();
            Iterator it = this.f958c.values().iterator();
            while (it.hasNext()) {
                this.f956a.unbindService((T) it.next());
            }
            this.f958c.clear();
            q.h("exception stopRecording - mRecognizing=" + this.f959d.get());
        }
    }

    public void n(String str, Q q2, Bundle bundle) {
        this.f962g.g("\n\nClinet->QVA stopRecording:" + System.currentTimeMillis() + "\n");
        if (this.f959d.compareAndSet(true, false)) {
            q.h("stopRecording - packageName=" + str);
            q.i("stopRecording ");
            ForegroundService.k();
            Bundle bundleG = C0023f0.g();
            bundleG.putLong("AudioMetaData.stopRecordingTimestamp", System.currentTimeMillis());
            q2.a(bundleG);
            q(str);
            q.h("mRecognizing=" + this.f959d.get());
        }
    }

    public void o() {
        this.f961f.r();
        this.f961f.q();
        this.f960e.b();
    }

    public final void p(j jVar) {
        if (g.v().n(jVar).d()) {
            g.u().d(jVar);
            g.e().i(jVar.b(), jVar);
        }
    }

    public final void q(String str) {
        q.h("unbindService - packageName=" + str);
        T t2 = (T) this.f958c.remove(str);
        if (t2 != null) {
            this.f956a.unbindService(t2);
        }
    }
}
