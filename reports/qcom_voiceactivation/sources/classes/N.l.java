package N;

import android.hardware.soundtrigger.SoundTriggerModule;
import com.quicinc.voice.activation.wrapper.soundtrigger.QResponseStatus;

/* JADX INFO: compiled from: QSoundTriggerModule.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SoundTriggerModule f194a;

    public l(SoundTriggerModule soundTriggerModule) {
        this.f194a = soundTriggerModule;
    }

    public void a() {
        this.f194a.detach();
    }

    public QResponseStatus b(e eVar, int[] iArr) {
        return QResponseStatus.a(this.f194a.loadSoundModel(eVar.a(), iArr));
    }

    public QResponseStatus c(int i2, g gVar) {
        return QResponseStatus.a(this.f194a.startRecognition(i2, gVar.a()));
    }

    public QResponseStatus d(int i2) {
        return QResponseStatus.a(this.f194a.stopRecognition(i2));
    }

    public QResponseStatus e(int i2) {
        return QResponseStatus.a(this.f194a.unloadSoundModel(i2));
    }
}
