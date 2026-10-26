package N;

import com.quicinc.voice.activation.wrapper.soundtrigger.QSoundTrigger$QServiceState;

/* JADX INFO: compiled from: QSoundTrigger.java */
/* JADX INFO: loaded from: classes.dex */
public interface i {
    void a(QSoundTrigger$QServiceState qSoundTrigger$QServiceState);

    void b(h hVar);

    void c(c cVar);

    void onModelUnloaded(int i2);

    void onResourcesAvailable();

    void onServiceDied();
}
