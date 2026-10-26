package E;

import G.q;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: SessionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ConcurrentHashMap f39d = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f40a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f41b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public k f42c = new f(this);

    public h(g gVar) {
        this.f40a = gVar;
        n.f().i(this.f42c);
    }

    public static int o() {
        return f39d.size();
    }

    public final void A(boolean z2) {
        boolean zP = p();
        Iterator it = new ArrayList(f39d.values()).iterator();
        while (it.hasNext()) {
            B((e) it.next(), zP, z2);
        }
    }

    public final QResponseStatus B(e eVar, boolean z2, boolean z3) {
        if (!z2) {
            return QResponseStatus.OK;
        }
        if (eVar.k()) {
            return eVar.z(n.f().h(), z3);
        }
        QResponseStatus qResponseStatusY = eVar.y(n.f().h(), z3);
        if (!qResponseStatusY.b()) {
            return qResponseStatusY;
        }
        f39d.remove(eVar.g());
        return qResponseStatusY;
    }

    public final void i() {
        Iterator it = f39d.values().iterator();
        while (it.hasNext()) {
            ((e) it.next()).a();
        }
        f39d.clear();
    }

    public final e j(v.j jVar, g gVar) {
        e eVar = new e(jVar);
        eVar.D(gVar);
        eVar.u(jVar.f());
        return eVar;
    }

    public void k() {
        q.d("TurnOnDetection(All_1st)");
        A(true);
        q.m();
    }

    public N.f l() {
        return n.f().g();
    }

    public final e m(int i2) {
        for (e eVar : f39d.values()) {
            if (eVar.f() == i2) {
                return eVar;
            }
        }
        return null;
    }

    public final e n(v.j jVar) {
        for (e eVar : f39d.values()) {
            if (eVar.C().equals(jVar)) {
                return eVar;
            }
        }
        return null;
    }

    public final boolean p() {
        return !this.f41b;
    }

    public boolean q(v.j jVar) {
        q.r("isRecognitionActive " + jVar);
        e eVarN = n(jVar);
        return eVarN != null && eVarN.k();
    }

    public QResponseStatus r(v.j jVar, N.e eVar) {
        q.r("loadSoundModel " + jVar);
        ConcurrentHashMap concurrentHashMap = f39d;
        e eVarJ = (e) concurrentHashMap.get(jVar.f());
        if (eVarJ == null) {
            eVarJ = j(jVar, this.f40a);
            concurrentHashMap.put(eVarJ.g(), eVarJ);
        }
        QResponseStatus qResponseStatusC = n.f().c();
        if (!qResponseStatusC.b()) {
            return qResponseStatusC;
        }
        eVarJ.q(eVar);
        return eVarJ.l(n.f().h());
    }

    public final void s() {
        Iterator it = f39d.values().iterator();
        while (it.hasNext()) {
            ((e) it.next()).p();
        }
    }

    public final void t(QResponseStatus qResponseStatus) {
        for (e eVar : f39d.values()) {
            g gVarB = eVar.B();
            if (gVarB != null) {
                gVarB.b(eVar.C(), qResponseStatus);
            }
        }
    }

    public QResponseStatus u(v.j jVar, N.g gVar) {
        q.r("startRecognition " + jVar);
        e eVar = (e) f39d.get(jVar.f());
        if (eVar == null) {
            return QResponseStatus.BAD_VALUE;
        }
        QResponseStatus qResponseStatusC = n.f().c();
        if (!qResponseStatusC.b()) {
            return qResponseStatusC;
        }
        eVar.s(true);
        eVar.r(gVar);
        return eVar.v(n.f().h(), false);
    }

    public QResponseStatus v(v.j jVar) {
        q.r("stopRecognition " + jVar);
        e eVar = (e) f39d.get(jVar.f());
        if (eVar == null) {
            return QResponseStatus.BAD_VALUE;
        }
        QResponseStatus qResponseStatusC = n.f().c();
        if (!qResponseStatusC.b()) {
            return qResponseStatusC;
        }
        eVar.s(false);
        return eVar.w(n.f().h(), false);
    }

    public final void w() {
        if (this.f41b) {
            q.r("tryUpdateAllRecognition - mServiceDisabled = " + this.f41b);
            this.f41b = false;
            A(true);
        }
    }

    public QResponseStatus x(v.j jVar) {
        q.r("turnOffDetection " + jVar);
        q.d("TurnOffDetection(1st)");
        QResponseStatus qResponseStatusC = n.f().c();
        if (!qResponseStatusC.b()) {
            q.m();
            return qResponseStatusC;
        }
        e eVarN = n(jVar);
        if (eVarN == null) {
            q.m();
            return QResponseStatus.BAD_VALUE;
        }
        eVarN.s(false);
        QResponseStatus qResponseStatusB = B(eVarN, p(), false);
        if (f39d.size() == 0) {
            n.f().e();
        }
        q.m();
        return qResponseStatusB;
    }

    public QResponseStatus y(v.j jVar, N.e eVar, N.g gVar) {
        q.r("turnOnDetection " + jVar);
        q.d("TurnOnDetection(1st)");
        QResponseStatus qResponseStatusC = n.f().c();
        if (!qResponseStatusC.b()) {
            q.m();
            return qResponseStatusC;
        }
        e eVarN = n(jVar);
        if (eVarN == null) {
            eVarN = j(jVar, this.f40a);
            eVarN.q(eVar);
            eVarN.r(gVar);
            f39d.put(eVarN.g(), eVarN);
        }
        eVarN.s(true);
        QResponseStatus qResponseStatusB = B(eVarN, p(), false);
        q.m();
        return qResponseStatusB;
    }

    public QResponseStatus z(v.j jVar) {
        q.r("unloadSoundModel " + jVar);
        e eVar = (e) f39d.remove(jVar.f());
        if (eVar == null) {
            return QResponseStatus.BAD_VALUE;
        }
        QResponseStatus qResponseStatusC = n.f().c();
        return !qResponseStatusC.b() ? qResponseStatusC : eVar.A(n.f().h());
    }
}
