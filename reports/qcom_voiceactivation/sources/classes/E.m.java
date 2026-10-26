package E;

import G.q;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import com.quicinc.voice.activation.wrapper.soundtrigger.QSoundTrigger$QServiceState;
import java.util.Iterator;

/* JADX INFO: compiled from: SoundTriggerModuleProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements N.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n f44a;

    @Override // N.i
    public void a(QSoundTrigger$QServiceState qSoundTrigger$QServiceState) {
        q.r("onServiceStateChange serviceDisabled = " + (qSoundTrigger$QServiceState == QSoundTrigger$QServiceState.DISABLED));
        if (this.f44a.f48c.isEmpty()) {
            return;
        }
        Iterator it = this.f44a.f48c.iterator();
        while (it.hasNext()) {
            ((k) it.next()).a(qSoundTrigger$QServiceState);
        }
    }

    @Override // N.i
    public void c(N.c cVar) {
        if (cVar == null) {
            q.j("qKeyphraseRecognitionEvent event is null");
            return;
        }
        int i2 = j.f43a[cVar.f().ordinal()];
        if (i2 == 1) {
            d(cVar);
        } else if (i2 == 2) {
            e();
        } else {
            if (i2 != 3) {
                return;
            }
            f(cVar);
        }
    }

    public final void d(N.c cVar) {
        q.r("onRecognitionAbort soundModelHandle = " + cVar.e());
        if (this.f44a.f48c.isEmpty()) {
            return;
        }
        Iterator it = this.f44a.f48c.iterator();
        while (it.hasNext()) {
            ((k) it.next()).b(cVar);
        }
    }

    public final void e() {
        q.j("onRecognitionFailure");
        if (this.f44a.f48c.isEmpty()) {
            return;
        }
        Iterator it = this.f44a.f48c.iterator();
        while (it.hasNext()) {
            ((k) it.next()).d();
        }
    }

    public final void f(N.c cVar) {
        int iE = cVar.e();
        q.c("Recognition", 1);
        q.p("ReceiveRecognitionEvent(1st)[" + iE + "]", new String[0]);
        StringBuilder sb = new StringBuilder();
        sb.append("onRecognitionSuccess soundModelHandle = ");
        sb.append(iE);
        q.r(sb.toString());
        if (this.f44a.f48c.isEmpty()) {
            return;
        }
        Iterator it = this.f44a.f48c.iterator();
        while (it.hasNext()) {
            ((k) it.next()).e(cVar);
        }
    }

    @Override // N.i
    public void onModelUnloaded(int i2) {
        q.r("onModelUnloaded soundModelHandle = " + i2);
        if (this.f44a.f48c.isEmpty()) {
            return;
        }
        Iterator it = this.f44a.f48c.iterator();
        while (it.hasNext()) {
            ((k) it.next()).onModelUnloaded(i2);
        }
    }

    @Override // N.i
    public void onResourcesAvailable() {
        q.r("onResourcesAvailable");
        if (this.f44a.f48c.isEmpty()) {
            return;
        }
        Iterator it = this.f44a.f48c.iterator();
        while (it.hasNext()) {
            ((k) it.next()).onResourcesAvailable();
        }
    }

    @Override // N.i
    public void onServiceDied() {
        q.r("onServiceDied");
        this.f44a.d();
        QResponseStatus qResponseStatusC = this.f44a.c();
        if (this.f44a.f48c.isEmpty()) {
            return;
        }
        Iterator it = this.f44a.f48c.iterator();
        while (it.hasNext()) {
            ((k) it.next()).c(qResponseStatusC.b());
        }
    }

    public m(n nVar) {
        this.f44a = nVar;
    }

    @Override // N.i
    public void b(N.h hVar) {
    }
}
