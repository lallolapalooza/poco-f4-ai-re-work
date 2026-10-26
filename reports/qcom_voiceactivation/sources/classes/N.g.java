package N;

import android.hardware.soundtrigger.SoundTrigger;

/* JADX INFO: compiled from: QRecognitionConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SoundTrigger.RecognitionConfig f191a;

    public g(boolean z2, boolean z3, d[] dVarArr, byte[] bArr) {
        this.f191a = new SoundTrigger.RecognitionConfig(z2, z3, d.d(dVarArr), bArr);
    }

    public SoundTrigger.RecognitionConfig a() {
        return this.f191a;
    }

    public String toString() {
        return this.f191a.toString();
    }
}
