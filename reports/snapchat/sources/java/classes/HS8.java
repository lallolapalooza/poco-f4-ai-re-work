package defpackage;

import android.location.LocationManager;

/* JADX INFO: loaded from: classes4.dex */
public final class HS8 implements FS8 {
    public final Z9g a = new Z9g(C36876svh.c);

    public final boolean a() {
        if (d()) {
            return b() || c();
        }
        return false;
    }

    public final boolean b() {
        try {
            return ((LocationManager) this.a.getValue()).isProviderEnabled("gps");
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public final boolean c() {
        try {
            return ((LocationManager) this.a.getValue()).isProviderEnabled("network");
        } catch (RuntimeException unused) {
            return false;
        }
    }

    public final boolean d() {
        return b() || c();
    }
}
