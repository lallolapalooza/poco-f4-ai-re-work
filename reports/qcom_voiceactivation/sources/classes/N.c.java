package N;

import android.hardware.soundtrigger.SoundTrigger;
import com.quicinc.voice.activation.wrapper.soundtrigger.QKeyphraseRecognitionEvent$QRecognitionStatus;

/* JADX INFO: compiled from: QKeyphraseRecognitionEvent.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SoundTrigger.KeyphraseRecognitionEvent f185a;

    public c(SoundTrigger.KeyphraseRecognitionEvent keyphraseRecognitionEvent) {
        this.f185a = keyphraseRecognitionEvent;
    }

    public boolean a() {
        return this.f185a.captureAvailable;
    }

    public int b() {
        return this.f185a.captureSession;
    }

    public byte[] c() {
        return this.f185a.data;
    }

    public d[] d() {
        return d.c(this.f185a.keyphraseExtras);
    }

    public int e() {
        return this.f185a.soundModelHandle;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && c.class == obj.getClass()) {
            return this.f185a.equals(((c) obj).f185a);
        }
        return false;
    }

    public QKeyphraseRecognitionEvent$QRecognitionStatus f() {
        return QKeyphraseRecognitionEvent$QRecognitionStatus.a(this.f185a.status);
    }

    public int hashCode() {
        return this.f185a.hashCode();
    }

    public String toString() {
        return this.f185a.toString();
    }
}
