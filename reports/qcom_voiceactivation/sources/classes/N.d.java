package N;

import android.hardware.soundtrigger.SoundTrigger;

/* JADX INFO: compiled from: QKeyphraseRecognitionExtra.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SoundTrigger.KeyphraseRecognitionExtra f186a;

    public d(int i2, int i3, int i4, a[] aVarArr) {
        this.f186a = new SoundTrigger.KeyphraseRecognitionExtra(i2, i3, i4, a.b(aVarArr));
    }

    public static d[] c(SoundTrigger.KeyphraseRecognitionExtra[] keyphraseRecognitionExtraArr) {
        if (keyphraseRecognitionExtraArr == null) {
            return null;
        }
        d[] dVarArr = new d[keyphraseRecognitionExtraArr.length];
        for (int i2 = 0; i2 < keyphraseRecognitionExtraArr.length; i2++) {
            dVarArr[i2] = new d(keyphraseRecognitionExtraArr[i2]);
        }
        return dVarArr;
    }

    public static SoundTrigger.KeyphraseRecognitionExtra[] d(d[] dVarArr) {
        if (dVarArr == null) {
            return null;
        }
        int length = dVarArr.length;
        SoundTrigger.KeyphraseRecognitionExtra[] keyphraseRecognitionExtraArr = new SoundTrigger.KeyphraseRecognitionExtra[length];
        for (int i2 = 0; i2 < length; i2++) {
            keyphraseRecognitionExtraArr[i2] = dVarArr[i2].f186a;
        }
        return keyphraseRecognitionExtraArr;
    }

    public int a() {
        return this.f186a.coarseConfidenceLevel;
    }

    public a[] b() {
        return a.a(this.f186a.confidenceLevels);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && d.class == obj.getClass()) {
            return this.f186a.equals(((d) obj).f186a);
        }
        return false;
    }

    public int hashCode() {
        return this.f186a.hashCode();
    }

    public String toString() {
        return this.f186a.toString();
    }

    public d(SoundTrigger.KeyphraseRecognitionExtra keyphraseRecognitionExtra) {
        this.f186a = keyphraseRecognitionExtra;
    }
}
