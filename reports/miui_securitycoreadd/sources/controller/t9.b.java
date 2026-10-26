package t9;

import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f7814b = a.VERBOSE;
    public CopyOnWriteArrayList<u9.a> c = new CopyOnWriteArrayList<>();

    public b(String str) {
        this.f7813a = str;
    }

    public final void a(a aVar, String str, String str2, Throwable th, x9.b bVar) {
        if (aVar.compareTo(this.f7814b) >= 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (u9.a aVar2 : this.c) {
                String str3 = this.f7813a;
                if (str2 == null) {
                    aVar2.a(str3, str, jCurrentTimeMillis, aVar, bVar);
                } else {
                    aVar2.b(str3, str, jCurrentTimeMillis, aVar, str2, th);
                }
            }
        }
    }
}
