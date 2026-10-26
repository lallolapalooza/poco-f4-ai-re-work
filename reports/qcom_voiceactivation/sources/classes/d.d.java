package p003d;

import G.q;
import N.c;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import java.util.UUID;
import p015p.e;

/* JADX INFO: compiled from: Facade.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f713a;

    @Override // p015p.e
    public void a(UUID uuid) {
        q.h("onRecognitionPaused model uuid =" + uuid);
    }

    @Override // p015p.e
    public void b(UUID uuid, QResponseStatus qResponseStatus) {
        q.h("onError model uuid =" + uuid);
    }

    @Override // p015p.e
    public void c(UUID uuid) {
        q.h("onRecognitionResumed model uuid =" + uuid);
    }

    @Override // p015p.e
    public void d(UUID uuid, c cVar) {
        q.h("onRecognition model uuid =" + uuid + ", time=" + System.currentTimeMillis());
        this.f713a.b();
        g.n().i(uuid, new RecognitionEvent(cVar));
    }

    public d(g gVar) {
        this.f713a = gVar;
    }
}
