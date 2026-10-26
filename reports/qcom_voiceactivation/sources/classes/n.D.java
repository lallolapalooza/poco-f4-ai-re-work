package p013n;

import G.q;
import android.os.Handler;
import android.os.HandlerThread;
import com.quicinc.voice.activation.algorithm.AlgorithmResponseStatus;
import com.quicinc.voice.activation.llm.InputProviderService;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import p003d.g;
import p007h.a;
import p010k.b;
import p015p.f;
import p015p.j;
import p017r.h;
import v.c;

/* JADX INFO: compiled from: OneVoiceSoundTriggerHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public f f886a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f887b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f888c;

    public D(f fVar, j jVar) {
        this.f886a = fVar;
        this.f887b = jVar;
        HandlerThread handlerThread = new HandlerThread("FsStateCallback");
        handlerThread.start();
        this.f888c = new Handler(handlerThread.getLooper());
        this.f886a.y(new C() { // from class: n.A
            @Override // p013n.C
            public final void a() {
                this.f880a.l();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        for (b bVar : g.e().f().values()) {
            if (bVar.o() != null) {
                this.f887b.n(bVar.o());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.f888c.post(new Runnable() { // from class: n.B
            @Override // java.lang.Runnable
            public final void run() {
                this.f885a.k();
            }
        });
    }

    public a c() {
        return this.f886a.p();
    }

    public final UUID d(String str, String str2, C0029i0 c0029i0) {
        return UUID.nameUUIDFromBytes((str + "-" + str2 + "-" + c0029i0.d() + "-" + c0029i0.e() + "-" + c0029i0.c()).getBytes());
    }

    public final int[] e(b bVar, boolean z2) {
        p016q.a aVarG = g.j().g(bVar);
        List<b> listE = aVarG.e();
        ArrayList arrayList = new ArrayList();
        for (b bVar2 : listE) {
            boolean zH = h(bVar2);
            if (z2) {
                if (zH || bVar.j().equals(bVar2.j())) {
                    arrayList.add(Integer.valueOf(aVarG.i(bVar2)));
                }
            } else if (zH && !bVar.j().equals(bVar2.j())) {
                arrayList.add(Integer.valueOf(aVarG.i(bVar2)));
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            iArr[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr;
    }

    public j f() {
        return this.f887b;
    }

    public final void g(b bVar) {
        if (bVar.l().d()) {
            g.c().l(null);
        }
    }

    public boolean h(b bVar) {
        p016q.a aVarG = g.j().g(bVar);
        if (aVarG == null) {
            return false;
        }
        UUID uuidL = aVarG.l();
        boolean zT = this.f886a.t(uuidL);
        q.r("FS uuid=" + uuidL.toString() + ", isRecognitionActive=" + zT);
        if (!zT || !bVar.s()) {
            return zT;
        }
        UUID uuidO = bVar.o();
        boolean zF = this.f887b.f(uuidO);
        q.r("FS uuid=" + uuidO.toString() + ", isRecognitionActive=" + zF);
        return zF;
    }

    public boolean i(b bVar, C0029i0 c0029i0) {
        p016q.a aVarG = g.j().g(bVar);
        if (aVarG == null) {
            return false;
        }
        UUID uuidL = aVarG.l();
        boolean zT = this.f886a.t(uuidL);
        q.r("FS uuid=" + uuidL.toString() + ", isRecognitionActive=" + zT);
        if (!zT || c0029i0 == null || !bVar.s()) {
            return zT;
        }
        UUID uuidD = d(g.f().g(bVar.j()), bVar.j(), c0029i0);
        boolean zF = this.f887b.f(uuidD);
        q.r("SS uuid=" + uuidD.toString() + ", isRecognitionActive=" + zF);
        return zF;
    }

    public boolean j(p016q.a aVar) {
        if (aVar == null) {
            return false;
        }
        return this.f886a.t(aVar.l());
    }

    public void m(b bVar) {
        if (bVar != null) {
            bVar.u();
        }
    }

    public C0021e0 n(b bVar) {
        a aVarO = o(bVar);
        if (aVarO.c() && bVar.s()) {
            aVarO = this.f887b.n(bVar.o());
        }
        C0021e0 c0021e0C = C0021e0.c(aVarO);
        q.s("turnOff locale=" + bVar.k() + ", responseResult=" + c0021e0C.e());
        if (c0021e0C.d()) {
            InputProviderService.m();
        }
        return c0021e0C;
    }

    public final a o(b bVar) {
        p016q.a aVarG = g.j().g(bVar);
        UUID uuidL = aVarG.l();
        p017r.a aVarH = aVarG.h();
        if (aVarH.l()) {
            aVarH.o(e(bVar, false));
        }
        return this.f886a.D(uuidL, aVarH.k());
    }

    public C0021e0 p(b bVar, com.quicinc.voice.activation.audiorecord.b bVar2) {
        if (h(bVar)) {
            q.s("turnOn client group already active, skip");
            return C0021e0.f983c;
        }
        a aVarQ = q(bVar, bVar2);
        if (aVarQ.c() && bVar.s()) {
            aVarQ = r(bVar, bVar2);
            if (!aVarQ.c()) {
                o(bVar);
            }
        }
        C0021e0 c0021e0C = C0021e0.c(aVarQ);
        q.s("turnOn locale=" + bVar.k() + ", responseResult=" + c0021e0C.e());
        if (c0021e0C.d()) {
            g(bVar);
        }
        return c0021e0C;
    }

    public final a q(b bVar, com.quicinc.voice.activation.audiorecord.b bVar2) {
        p016q.a aVarG = g.j().g(bVar);
        p017r.a aVarH = aVarG.h();
        p017r.b bVarN = aVarG.n();
        if (aVarH == null) {
            q.r("model unsufficient");
            return new a(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_NO_MODEL);
        }
        if (aVarH.l()) {
            aVarH.o(e(bVar, true));
        }
        c cVarK = aVarH.k();
        UUID uuidL = aVarG.l();
        a aVarE = this.f886a.E(uuidL, cVarK, bVarN, bVar2);
        if (!aVarE.c()) {
            if (aVarH.l()) {
                aVarH.o(e(bVar, false));
            }
            this.f886a.D(uuidL, aVarH.k());
        }
        return aVarE;
    }

    public final a r(b bVar, com.quicinc.voice.activation.audiorecord.b bVar2) {
        UUID uuidO = bVar.o();
        h hVarN = bVar.n();
        if (uuidO == null || hVarN == null) {
            return null;
        }
        a aVarO = this.f887b.o(uuidO, hVarN, bVar2);
        if (aVarO.c()) {
            return aVarO;
        }
        this.f887b.n(uuidO);
        return aVarO;
    }
}
