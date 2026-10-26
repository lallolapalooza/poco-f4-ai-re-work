package N;

/* JADX INFO: compiled from: QSoundModelEvent.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends J.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final J.d f192a;

    public h(J.d dVar) {
        this.f192a = dVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && h.class == obj.getClass()) {
            return this.f192a.equals(((h) obj).f192a);
        }
        return false;
    }

    public int hashCode() {
        return this.f192a.hashCode();
    }

    public String toString() {
        return this.f192a.toString();
    }
}
