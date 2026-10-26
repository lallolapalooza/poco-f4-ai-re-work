package v;

import java.util.UUID;

/* JADX INFO: compiled from: SoundModelKey.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1270b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1271c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f1272d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final UUID f1273e = a();

    public j(String str, String str2, String str3, String str4) {
        this.f1269a = str;
        this.f1270b = str2;
        this.f1271c = str3;
        this.f1272d = str4;
    }

    public final UUID a() {
        return UUID.nameUUIDFromBytes((this.f1269a + "-" + this.f1270b + "-" + this.f1271c + "-" + this.f1272d).getBytes());
    }

    public String b() {
        return this.f1269a;
    }

    public String c() {
        return this.f1272d;
    }

    public String d() {
        return this.f1270b;
    }

    public String e() {
        return this.f1271c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return f().equals(((j) obj).f());
        }
        return false;
    }

    public UUID f() {
        return this.f1273e;
    }

    public int hashCode() {
        return f().hashCode();
    }

    public String toString() {
        return "SoundModelKey{mGroupName='" + this.f1269a + "', mSoundModelFileName='" + this.f1270b + "', mUserId='" + this.f1271c + "', mLocale='" + this.f1272d + "', mUuid=" + this.f1273e + '}';
    }
}
