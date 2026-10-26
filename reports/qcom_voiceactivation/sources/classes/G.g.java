package G;

/* JADX INFO: compiled from: AudioStreamSaverFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static i a(String str) {
        return c() ? new f(p003d.g.d(), str) : new h();
    }

    public static i b(String str) {
        return c() ? new p(p003d.g.d(), str) : new h();
    }

    public static boolean c() {
        return "1".equals(I.c.a("persist.qva.dump"));
    }
}
