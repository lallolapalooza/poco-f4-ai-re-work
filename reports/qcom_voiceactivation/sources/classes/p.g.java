package p015p;

import E.o;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import java.util.UUID;

/* JADX INFO: compiled from: OneVoiceFSSession.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends o {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e f1165g;

    public g(UUID uuid) {
        super(uuid);
    }

    public e B() {
        return this.f1165g;
    }

    public void C(e eVar) {
        this.f1165g = eVar;
    }

    @Override // E.o
    public void m(QResponseStatus qResponseStatus) {
        e eVar = this.f1165g;
        if (eVar != null) {
            eVar.b(this.f49a, qResponseStatus);
        }
    }

    @Override // E.o
    public void n() {
        e eVar = this.f1165g;
        if (eVar != null) {
            eVar.a(this.f49a);
        }
    }

    @Override // E.o
    public void o() {
        e eVar = this.f1165g;
        if (eVar != null) {
            eVar.c(this.f49a);
        }
    }
}
