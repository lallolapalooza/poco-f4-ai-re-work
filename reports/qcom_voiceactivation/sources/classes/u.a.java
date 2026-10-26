package u;

import android.content.Context;

/* JADX INFO: compiled from: IOneVoiceSoundModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {
    static a c(Context context, p016q.c cVar) {
        return new g(context.getFilesDir().getAbsolutePath(), cVar);
    }

    void a(Runnable runnable);

    void b(p010k.b bVar, boolean z2);

    boolean d(p010k.b bVar);
}
