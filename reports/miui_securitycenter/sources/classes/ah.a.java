package ah;

import android.content.Context;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class a {
    public int a(Context context, String str, String str2) {
        boolean zH;
        System.currentTimeMillis();
        b bVar = new b(str, str2);
        new dh.e().e(bVar);
        int i10 = 0;
        try {
            zH = b().h(bVar, context);
            if (zH) {
                try {
                    if (dh.a.c(bVar.c()) && f.a(context, bVar.b())) {
                        zH = false;
                    }
                } catch (IOException e10) {
                    e = e10;
                    e.printStackTrace();
                }
            }
        } catch (IOException e11) {
            e = e11;
            zH = false;
        }
        if (zH) {
            i10 = 2;
            if ((bVar.j() & 2) != 0) {
                return 1;
            }
        }
        return i10;
    }

    protected d b() {
        return new d();
    }
}
