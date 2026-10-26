package p015p;

import E.k;
import E.n;
import G.i;
import G.q;
import com.quicinc.voice.activation.algorithm.AlgorithmResponseStatus;
import com.quicinc.voice.activation.onevoice.algorithm.OneVoiceAlgorithmTask;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import p003d.g;
import p007h.a;
import p013n.C;
import p017r.b;
import v.c;

/* JADX INFO: compiled from: OneVoiceFSMSManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ConcurrentHashMap f1158g = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f1161c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C f1162d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap f1159a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f1160b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1163e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public k f1164f = new c(this);

    public f(e eVar) {
        this.f1161c = eVar;
        n.f().i(this.f1164f);
    }

    public static a B() {
        return new a(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_SUCCESS);
    }

    public static a k() {
        return new a(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_BAD_PARAM);
    }

    public static a l() {
        return new a(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_SUCCESS);
    }

    public static a o() {
        return new a(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_LOW_LAYER_FAILURE);
    }

    public static int r() {
        return f1158g.size();
    }

    public final a A(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1159a.get(uuid);
        a aVarV = OneVoiceAlgorithmTask.v();
        if (oneVoiceAlgorithmTask == null) {
            return aVarV;
        }
        oneVoiceAlgorithmTask.s(false);
        return oneVoiceAlgorithmTask.u();
    }

    public final void C() {
        if (this.f1163e) {
            q.r("tryUpdateAllRecognition(oneVoice) - mServiceDisabled = " + this.f1163e);
            this.f1163e = false;
            G(true);
        }
    }

    public a D(UUID uuid, c cVar) {
        q.r("turnOffDetection(OneVoice) " + uuid);
        boolean zL = g.j().h(uuid).h().l();
        q.d("TurnOffOneVoiceDetection(1st)");
        if (!n.f().c().b()) {
            q.j("attach module failed");
            q.m();
            return o();
        }
        d dVar = (d) this.f1160b.get(uuid);
        if (dVar == null) {
            q.j("counter null");
            return k();
        }
        q.h("turnOffDetection counter=" + dVar.b());
        if (dVar.d()) {
            ConcurrentHashMap concurrentHashMap = f1158g;
            g gVar = (g) concurrentHashMap.get(uuid);
            if (gVar == null) {
                q.j("session null");
                q.m();
                return o();
            }
            gVar.s(false);
            if (!H(gVar, s(), false).b()) {
                q.m();
                return o();
            }
            if (concurrentHashMap.size() == 0) {
                n.f().e();
            }
            q.m();
            q.d("TurnOffOneVoiceDetection(1.5)");
            a aVarA = A(uuid);
            if (aVarA.c()) {
                aVarA = F(uuid);
            }
            q.r(m(uuid, aVarA.c()));
            q.m();
            if (aVarA.c()) {
                this.f1160b.remove(uuid);
            }
            return aVarA;
        }
        if (!zL) {
            dVar.a();
            return l();
        }
        ConcurrentHashMap concurrentHashMap2 = f1158g;
        g gVar2 = (g) concurrentHashMap2.get(uuid);
        if (gVar2 == null) {
            q.j("session null");
            q.m();
            return o();
        }
        gVar2.s(false);
        if (!H(gVar2, s(), false).b()) {
            q.m();
            return o();
        }
        concurrentHashMap2.put(uuid, gVar2);
        if (cVar != null) {
            gVar2.q(cVar.a());
            gVar2.r(cVar.b());
        }
        gVar2.s(true);
        if (H(gVar2, s(), false).b()) {
            dVar.a();
            return l();
        }
        q.m();
        return o();
    }

    public a E(UUID uuid, c cVar, b bVar, com.quicinc.voice.activation.audiorecord.b bVar2) {
        q.r("turnOnDetection(OneVoice) " + uuid);
        boolean zL = g.j().h(uuid).h().l();
        q.d("TurnOnOneVoiceDetection(1st)");
        if (!n.f().c().b()) {
            q.j("attach module failed");
            q.m();
            return o();
        }
        d dVar = (d) this.f1160b.get(uuid);
        if (dVar == null) {
            dVar = new d(this, 0);
            this.f1160b.put(uuid, dVar);
        }
        if (dVar.b() > 0) {
            q.h("turnOnDetection counter=" + dVar.b());
            if (!zL) {
                dVar.c();
                return l();
            }
        }
        ConcurrentHashMap concurrentHashMap = f1158g;
        g gVarN = (g) concurrentHashMap.get(uuid);
        if (gVarN == null) {
            gVarN = n(uuid, this.f1161c);
            gVarN.q(cVar.a());
            gVarN.r(cVar.b());
            concurrentHashMap.put(uuid, gVarN);
        }
        if (gVarN.k()) {
            if (cVar != null) {
                gVarN.q(cVar.a());
                gVarN.r(cVar.b());
            }
            a aVarB = B();
            gVarN.s(false);
            QResponseStatus qResponseStatusH = H(gVarN, s(), false);
            concurrentHashMap.put(uuid, gVarN);
            if (!qResponseStatusH.b()) {
                q.m();
                return o();
            }
            gVarN.s(true);
            if (!H(gVarN, s(), false).b()) {
                q.m();
                return o();
            }
            q.m();
            if (aVarB.c()) {
                dVar.c();
            }
            return aVarB;
        }
        gVarN.s(true);
        if (!H(gVarN, s(), false).b()) {
            q.m();
            return o();
        }
        q.m();
        a aVarB2 = B();
        if (bVar != null) {
            q.d("TurnOnOneVoiceDetection(1.5)");
            aVarB2 = u(uuid, bVar, bVar2);
            if (aVarB2.c()) {
                aVarB2 = z(uuid);
                if (!aVarB2.c()) {
                    F(uuid);
                }
            }
            q.r(m(uuid, aVarB2.c()));
            q.m();
        }
        if (aVarB2.c()) {
            dVar.c();
        }
        return aVarB2;
    }

    public final a F(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1159a.get(uuid);
        a aVarV = OneVoiceAlgorithmTask.v();
        if (oneVoiceAlgorithmTask != null) {
            aVarV = oneVoiceAlgorithmTask.d();
            if (aVarV.c()) {
                this.f1159a.remove(uuid);
            }
        }
        return aVarV;
    }

    public final void G(boolean z2) {
        boolean zS = s();
        Iterator it = new ArrayList(f1158g.values()).iterator();
        while (it.hasNext()) {
            H((g) it.next(), zS, z2);
        }
    }

    public final QResponseStatus H(g gVar, boolean z2, boolean z3) {
        if (!z2) {
            return QResponseStatus.OK;
        }
        if (gVar.k()) {
            return gVar.z(n.f().h(), z3);
        }
        QResponseStatus qResponseStatusY = gVar.y(n.f().h(), z3);
        if (!qResponseStatusY.b()) {
            return qResponseStatusY;
        }
        f1158g.remove(gVar.g());
        return qResponseStatusY;
    }

    public void i(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask;
        if (uuid == null || (oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1159a.get(uuid)) == null) {
            return;
        }
        oneVoiceAlgorithmTask.cancel(true);
        q.h("cancelProcess(oneVoice)");
    }

    public final void j() {
        for (g gVar : f1158g.values()) {
            gVar.a();
            a aVarA = A(gVar.g());
            if (aVarA.c()) {
                aVarA = F(gVar.g());
            }
            q.r("clearAllSessionState result=" + aVarA.a());
        }
        f1158g.clear();
        this.f1160b.clear();
        this.f1162d.a();
    }

    public final String m(UUID uuid, boolean z2) {
        return "(OneVoice) fs uuid [" + uuid + "], result = " + z2;
    }

    public final g n(UUID uuid, e eVar) {
        g gVar = new g(uuid);
        gVar.C(eVar);
        return gVar;
    }

    public a p() {
        q.r("forceTurnOnAllFS(OneVoice)");
        q.d("TurnOnOneVoiceDetection(All_1st)");
        if (n.f().c().b()) {
            G(true);
            q.m();
            return B();
        }
        q.j("attach module failed");
        q.m();
        return o();
    }

    public final g q(int i2) {
        for (g gVar : f1158g.values()) {
            if (gVar.f() == i2) {
                return gVar;
            }
        }
        return null;
    }

    public final boolean s() {
        return !this.f1163e;
    }

    public boolean t(UUID uuid) {
        q.r("isRecognitionActive(OneVoice) " + uuid);
        g gVar = (g) f1158g.get(uuid);
        boolean z2 = gVar != null && gVar.k();
        if (!z2) {
            return z2;
        }
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1159a.get(uuid);
        return oneVoiceAlgorithmTask == null || oneVoiceAlgorithmTask.n();
    }

    public final a u(UUID uuid, b bVar, com.quicinc.voice.activation.audiorecord.b bVar2) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1159a.get(uuid);
        if (oneVoiceAlgorithmTask == null) {
            List listC = a.c(bVar);
            oneVoiceAlgorithmTask = new OneVoiceAlgorithmTask(bVar2, listC, false, 0, listC.size());
            this.f1159a.put(uuid, oneVoiceAlgorithmTask);
        }
        return oneVoiceAlgorithmTask.k();
    }

    public p007h.b v(UUID uuid, i iVar) {
        p007h.b bVarO;
        q.d("AlgorithmDetection(One Voice)");
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1159a.get(uuid);
        if (oneVoiceAlgorithmTask != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            oneVoiceAlgorithmTask.r(iVar);
            bVarO = oneVoiceAlgorithmTask.o();
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            q.o("OneVoiceAlgorithm Process:start=" + jCurrentTimeMillis + ", end=" + jCurrentTimeMillis2 + ",total duration=" + (jCurrentTimeMillis2 - jCurrentTimeMillis));
        } else {
            bVarO = null;
        }
        q.h(bVarO != null ? bVarO.toString() : "null");
        q.m();
        return bVarO;
    }

    public final void w() {
        Iterator it = f1158g.values().iterator();
        while (it.hasNext()) {
            ((g) it.next()).p();
        }
    }

    public final void x(QResponseStatus qResponseStatus) {
        for (g gVar : f1158g.values()) {
            e eVarB = gVar.B();
            if (eVarB != null) {
                eVarB.b(gVar.g(), qResponseStatus);
            }
        }
    }

    public void y(C c2) {
        this.f1162d = c2;
    }

    public final a z(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1159a.get(uuid);
        a aVarI = OneVoiceAlgorithmTask.i();
        if (oneVoiceAlgorithmTask == null) {
            return aVarI;
        }
        oneVoiceAlgorithmTask.s(true);
        return oneVoiceAlgorithmTask.t();
    }
}
