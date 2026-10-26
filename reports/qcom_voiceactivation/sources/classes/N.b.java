package N;

import android.hardware.soundtrigger.SoundTrigger;
import android.os.Build;
import java.util.Locale;

/* JADX INFO: compiled from: QKeyphrase.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final J.a f183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SoundTrigger.Keyphrase f184b;

    public b(int i2, int i3, String str, String str2, int[] iArr) {
        if (Build.VERSION.SDK_INT == 29) {
            this.f183a = new K.a(i2, i3, str, str2, iArr);
        } else {
            this.f183a = new L.b(i2, i3, Locale.forLanguageTag(str), str2, iArr);
        }
        this.f184b = this.f183a.a();
    }

    public static SoundTrigger.Keyphrase[] a(b[] bVarArr) {
        int length = bVarArr.length;
        SoundTrigger.Keyphrase[] keyphraseArr = new SoundTrigger.Keyphrase[length];
        for (int i2 = 0; i2 < length; i2++) {
            keyphraseArr[i2] = bVarArr[i2].f184b;
        }
        return keyphraseArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && b.class == obj.getClass()) {
            return this.f184b.equals(((b) obj).f184b);
        }
        return false;
    }

    public int hashCode() {
        return this.f184b.hashCode();
    }

    public String toString() {
        return this.f183a.toString();
    }
}
