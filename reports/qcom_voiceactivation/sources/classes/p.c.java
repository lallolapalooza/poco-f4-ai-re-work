package p015p;

import E.k;
import G.q;
import com.quicinc.voice.activation.soundtrigger.SoundTriggerSession$State;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import com.quicinc.voice.activation.wrapper.soundtrigger.QSoundTrigger$QServiceState;

/* JADX INFO: compiled from: OneVoiceFSMSManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f1155a;

    public c(f fVar) {
        this.f1155a = fVar;
    }

    @Override // E.k
    public void a(QSoundTrigger$QServiceState qSoundTrigger$QServiceState) {
        boolean z2 = qSoundTrigger$QServiceState == QSoundTrigger$QServiceState.DISABLED;
        q.r("onServiceStateChange(oneVoice) serviceDisabled = " + z2);
        if (z2 != this.f1155a.f1163e) {
            this.f1155a.f1163e = z2;
            this.f1155a.G(true);
        }
    }

    @Override // E.k
    public void b(N.c cVar) {
        g gVarQ = this.f1155a.q(cVar.e());
        q.r("onRecognitionAbort(oneVoice) " + cVar.e());
        if (gVarQ == null || !gVarQ.j()) {
            return;
        }
        gVarQ.t(SoundTriggerSession$State.RECOGNITION_PAUSED);
        e eVarB = gVarQ.B();
        if (eVarB != null) {
            eVarB.a(gVarQ.g());
        }
    }

    @Override // E.k
    public void c(boolean z2) {
        this.f1155a.f1163e = true;
        this.f1155a.w();
        if (z2) {
            this.f1155a.C();
        } else {
            this.f1155a.j();
        }
    }

    @Override // E.k
    public void d() {
        this.f1155a.x(QResponseStatus.ERROR);
        this.f1155a.j();
    }

    @Override // E.k
    public void e(N.c cVar) {
        int iE = cVar.e();
        g gVarQ = this.f1155a.q(iE);
        if (gVarQ == null) {
            q.j("OneVoice Session does not exist for handle = " + iE);
            return;
        }
        if (gVarQ.B() == null) {
            q.j("Without OneVoice session listener");
            return;
        }
        gVarQ.t(SoundTriggerSession$State.RECOGNITION_PAUSED);
        e eVarB = gVarQ.B();
        if (eVarB != null) {
            eVarB.d(gVarQ.g(), cVar);
        }
    }

    @Override // E.k
    public void onModelUnloaded(int i2) {
        q.r("onModelUnloaded(oneVoice) " + i2);
    }

    @Override // E.k
    public void onResourcesAvailable() {
        q.r("onResourcesAvailable(oneVoice)");
        this.f1155a.G(true);
    }
}
