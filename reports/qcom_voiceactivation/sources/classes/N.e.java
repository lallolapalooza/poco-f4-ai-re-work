package N;

import android.hardware.soundtrigger.SoundTrigger;
import android.os.Build;
import java.util.UUID;

/* JADX INFO: compiled from: QKeyphraseSoundModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final J.b f187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SoundTrigger.KeyphraseSoundModel f188b;

    public e(UUID uuid, UUID uuid2, byte[] bArr, b[] bVarArr) {
        if (Build.VERSION.SDK_INT == 29) {
            this.f187a = new K.b(uuid, uuid2, bArr, b.a(bVarArr));
        } else {
            this.f187a = new L.c(uuid, uuid2, bArr, b.a(bVarArr));
        }
        this.f188b = this.f187a.a();
    }

    public SoundTrigger.KeyphraseSoundModel a() {
        return this.f188b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && e.class == obj.getClass()) {
            return this.f188b.equals(((e) obj).f188b);
        }
        return false;
    }

    public int hashCode() {
        return this.f188b.hashCode();
    }

    public String toString() {
        return this.f188b.toString();
    }
}
