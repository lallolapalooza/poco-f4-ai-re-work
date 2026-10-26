package I;

import android.os.Build;

/* JADX INFO: compiled from: QDeviceIdleController.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static void a(String str, long j2, String str2) {
        if (Build.VERSION.SDK_INT <= 30) {
            L.a.a(str, j2, str2);
        } else {
            M.a.a(str, j2, str2);
        }
    }
}
