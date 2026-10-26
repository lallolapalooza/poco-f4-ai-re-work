package ah;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f522b = "ah.g";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private bh.a f523a;

    public String[] a(String str) {
        return this.f523a.c(str);
    }

    public void b() {
        this.f523a = null;
    }

    public boolean c(String str) {
        return this.f523a.f(str);
    }

    public void d(Context context) {
        this.f523a = new bh.a();
        try {
            InputStream inputStreamB = c.b(context, "dict");
            this.f523a.h(inputStreamB);
            inputStreamB.close();
        } catch (IOException unused) {
            Log.d(f522b, "init failed");
        }
    }
}
