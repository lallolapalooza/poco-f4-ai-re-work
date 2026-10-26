package E;

import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;

/* JADX INFO: compiled from: Session.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends o {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g f35g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final v.j f36h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f37i;

    public e(v.j jVar) {
        super(jVar.f());
        this.f36h = jVar;
        this.f37i = jVar.d();
    }

    public g B() {
        return this.f35g;
    }

    public v.j C() {
        return this.f36h;
    }

    public void D(g gVar) {
        this.f35g = gVar;
    }

    @Override // E.o
    public void m(QResponseStatus qResponseStatus) {
        g gVar = this.f35g;
        if (gVar != null) {
            gVar.b(this.f36h, qResponseStatus);
        }
    }

    @Override // E.o
    public void n() {
        g gVar = this.f35g;
        if (gVar != null) {
            gVar.a(this.f36h);
        }
    }

    @Override // E.o
    public void o() {
        g gVar = this.f35g;
        if (gVar != null) {
            gVar.c(this.f36h);
        }
    }
}
