package E;

import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import java.util.ArrayList;

/* JADX INFO: compiled from: SoundTriggerModuleProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static n f45d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public N.l f46a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public N.f f47b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList f48c = new ArrayList();

    public n() {
        c();
    }

    public static n f() {
        if (f45d == null) {
            synchronized (n.class) {
                if (f45d == null) {
                    f45d = new n();
                }
            }
        }
        return f45d;
    }

    public QResponseStatus c() {
        if (this.f46a != null && this.f47b != null) {
            return QResponseStatus.OK;
        }
        ArrayList arrayList = new ArrayList();
        QResponseStatus qResponseStatusB = N.k.b(arrayList);
        if (!qResponseStatusB.b()) {
            return qResponseStatusB;
        }
        if (arrayList.isEmpty()) {
            return QResponseStatus.ERROR;
        }
        N.f fVar = (N.f) arrayList.get(0);
        this.f47b = fVar;
        N.l lVarA = N.k.a(fVar.a(), new m(this), null);
        this.f46a = lVarA;
        return lVarA == null ? QResponseStatus.ERROR : QResponseStatus.OK;
    }

    public final void d() {
        N.l lVar = this.f46a;
        if (lVar != null) {
            lVar.a();
            this.f46a = null;
            this.f47b = null;
        }
    }

    public void e() {
        if (h.o() == 0 && p015p.f.r() == 0) {
            d();
        }
    }

    public N.f g() {
        if (c().b()) {
            return this.f47b;
        }
        return null;
    }

    public N.l h() {
        if (c().b()) {
            return this.f46a;
        }
        return null;
    }

    public void i(k kVar) {
        if (kVar != null) {
            this.f48c.add(kVar);
        }
    }
}
