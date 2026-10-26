package u;

import android.content.Context;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;

/* JADX INFO: compiled from: ISoundModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public interface b {
    static b e(Context context, p010k.c cVar) {
        return new l(context.getFilesDir().getAbsolutePath(), cVar);
    }

    void a(Runnable runnable);

    void b(BigSoundModel bigSoundModel);

    boolean c(String str, String str2);

    boolean d(v.j jVar);
}
