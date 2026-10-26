package E;

import G.q;
import com.quicinc.voice.activation.soundtrigger.SoundTriggerSession$State;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;
import java.util.UUID;

/* JADX INFO: compiled from: SoundTriggerSession.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public UUID f49a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public N.g f53e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public N.e f54f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SoundTriggerSession$State f51c = SoundTriggerSession$State.IDLE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f52d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f50b = false;

    public o(UUID uuid) {
        this.f49a = uuid;
    }

    public QResponseStatus A(N.l lVar) {
        return i() ? e(lVar) : QResponseStatus.OK;
    }

    public void a() {
        this.f51c = SoundTriggerSession$State.IDLE;
        this.f52d = Integer.MIN_VALUE;
        this.f50b = false;
    }

    public final QResponseStatus b(N.l lVar) {
        QResponseStatus qResponseStatus = QResponseStatus.OK;
        if (this.f52d != Integer.MIN_VALUE) {
            return qResponseStatus;
        }
        int[] iArr = {Integer.MIN_VALUE};
        q.r(x() + "mQKeyphraseSoundModel=" + this.f54f);
        QResponseStatus qResponseStatusB = lVar.b(this.f54f, iArr);
        if (qResponseStatusB.b()) {
            this.f52d = iArr[0];
            this.f51c = SoundTriggerSession$State.RECOGNITION_PAUSED;
            q.r(x() + "loadSoundModel success;  soundModelHandle = " + this.f52d);
        } else {
            q.j(x() + "loadSoundModel failed cause of " + qResponseStatusB.c());
        }
        q.r(x() + "loadSoundModel status = " + qResponseStatusB.c());
        return qResponseStatusB;
    }

    public final QResponseStatus c(N.l lVar, boolean z2) {
        q.r(x() + "mQRecognitionConfig=" + this.f53e);
        QResponseStatus qResponseStatusC = lVar.c(this.f52d, this.f53e);
        if (qResponseStatusC.b()) {
            this.f51c = SoundTriggerSession$State.RECOGNIZING;
            if (z2) {
                o();
            }
        } else {
            q.j(x() + "startRecognition failed with " + qResponseStatusC.c());
            if (z2) {
                m(qResponseStatusC);
            }
        }
        q.r(x() + "startRecognition status = " + qResponseStatusC.c());
        return qResponseStatusC;
    }

    public final QResponseStatus d(N.l lVar, boolean z2) {
        QResponseStatus qResponseStatusD = QResponseStatus.OK;
        int i2 = this.f52d;
        if (i2 != Integer.MIN_VALUE) {
            qResponseStatusD = lVar.d(i2);
            if (qResponseStatusD.b()) {
                this.f51c = SoundTriggerSession$State.RECOGNITION_PAUSED;
                if (z2) {
                    n();
                }
            } else {
                q.j(x() + "stopRecognition failed with " + qResponseStatusD.c());
                if (z2) {
                    m(qResponseStatusD);
                }
            }
        }
        q.r(x() + "stopRecognition status = " + qResponseStatusD.c());
        return qResponseStatusD;
    }

    public final QResponseStatus e(N.l lVar) {
        QResponseStatus qResponseStatusE = QResponseStatus.OK;
        int i2 = this.f52d;
        if (i2 != Integer.MIN_VALUE) {
            qResponseStatusE = lVar.e(i2);
            if (qResponseStatusE.b()) {
                this.f51c = SoundTriggerSession$State.IDLE;
                this.f52d = Integer.MIN_VALUE;
            } else {
                q.j(x() + "unloadSoundModel failed with " + qResponseStatusE.c());
            }
        }
        q.r(x() + "unloadSoundModel status = " + qResponseStatusE.c());
        return qResponseStatusE;
    }

    public int f() {
        return this.f52d;
    }

    public UUID g() {
        return this.f49a;
    }

    public boolean h() {
        return this.f51c == SoundTriggerSession$State.IDLE;
    }

    public boolean i() {
        return this.f51c == SoundTriggerSession$State.RECOGNITION_PAUSED;
    }

    public boolean j() {
        return this.f51c == SoundTriggerSession$State.RECOGNIZING;
    }

    public boolean k() {
        q.r(x() + "isRequested = " + this.f50b);
        return this.f50b;
    }

    public QResponseStatus l(N.l lVar) {
        return h() ? b(lVar) : QResponseStatus.OK;
    }

    public abstract void m(QResponseStatus qResponseStatus);

    public abstract void n();

    public abstract void o();

    public void p() {
        this.f51c = SoundTriggerSession$State.IDLE;
        this.f52d = Integer.MIN_VALUE;
    }

    public void q(N.e eVar) {
        this.f54f = eVar;
    }

    public void r(N.g gVar) {
        this.f53e = gVar;
    }

    public void s(boolean z2) {
        this.f50b = z2;
    }

    public void t(SoundTriggerSession$State soundTriggerSession$State) {
        this.f51c = soundTriggerSession$State;
    }

    public void u(UUID uuid) {
        this.f49a = uuid;
    }

    public QResponseStatus v(N.l lVar, boolean z2) {
        return i() ? c(lVar, z2) : QResponseStatus.OK;
    }

    public QResponseStatus w(N.l lVar, boolean z2) {
        return j() ? d(lVar, z2) : QResponseStatus.OK;
    }

    public final String x() {
        if (this instanceof e) {
            return " legacy ";
        }
        if (this instanceof p015p.g) {
            return " oneVoice ";
        }
        return null;
    }

    public QResponseStatus y(N.l lVar, boolean z2) {
        QResponseStatus qResponseStatusW = w(lVar, z2);
        if (qResponseStatusW.b()) {
            qResponseStatusW = A(lVar);
            if (qResponseStatusW.b()) {
                a();
            }
        }
        q.r(x() + "turnOffDetection status = " + qResponseStatusW.c());
        return qResponseStatusW;
    }

    public QResponseStatus z(N.l lVar, boolean z2) {
        QResponseStatus qResponseStatusL = l(lVar);
        if (qResponseStatusL.b()) {
            qResponseStatusL = v(lVar, z2);
            if (!qResponseStatusL.b()) {
                A(lVar);
            }
        }
        q.r(x() + "turnOnDetection status = " + qResponseStatusL.c());
        return qResponseStatusL;
    }
}
