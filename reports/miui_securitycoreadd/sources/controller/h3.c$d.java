package h3;

import android.database.Cursor;
import c1.f;
import j7.k;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import y0.n;
import y0.p;
import y0.r;

/* JADX INFO: loaded from: classes.dex */
public final class c implements h3.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f4052a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y0.d<h3.a> f4053b;
    public final b c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0081c f4054d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f4055e;

    public class a extends y0.d<h3.a> {
        public a(n nVar) {
            super(nVar);
        }

        @Override // y0.r
        public final String c() {
            return "INSERT OR ABORT INTO `brightness_model_event` (`mId`,`ambient_lux`,`ambient_lux_span`,`orientation_and_app_industry`,`app_code`,`cur_brt`,`cur_brt_span`,`curve_brt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
        }

        public final void e(f fVar, Object obj) {
            h3.a aVar = (h3.a) obj;
            fVar.k(1, aVar.f4045b);
            fVar.t(2, aVar.c);
            fVar.t(3, aVar.f4046d);
            fVar.t(4, aVar.f4047e);
            fVar.t(5, aVar.f4048f);
            fVar.t(6, aVar.f4049g);
            fVar.t(7, aVar.f4050h);
            fVar.t(8, aVar.f4051i);
        }
    }

    public class b extends r {
        public b(n nVar) {
            super(nVar);
        }

        @Override // y0.r
        public final String c() {
            return "DELETE FROM brightness_model_event";
        }
    }

    /* JADX INFO: renamed from: h3.c$c, reason: collision with other inner class name */
    public class C0081c extends r {
        public C0081c(n nVar) {
            super(nVar);
        }

        @Override // y0.r
        public final String c() {
            return "DELETE FROM brightness_model_event WHERE mId IN (SELECT mId FROM brightness_model_event ORDER BY mId LIMIT ?)";
        }
    }

    public class d extends r {
        public d(n nVar) {
            super(nVar);
        }

        @Override // y0.r
        public final String c() {
            return "UPDATE sqlite_sequence SET seq = 0 WHERE name = 'brightness_model_event'";
        }
    }

    public c(n nVar) {
        this.f4052a = nVar;
        this.f4053b = new a(nVar);
        this.c = new b(nVar);
        this.f4054d = new C0081c(nVar);
        this.f4055e = new d(nVar);
    }

    @Override // h3.b
    public final List<h3.a> a(int i10) {
        p pVarF = p.f("SELECT * FROM brightness_model_event LIMIT -1 OFFSET ?", 1);
        pVarF.k(1, i10);
        this.f4052a.b();
        Cursor cursorX = m3.f.x(this.f4052a, pVarF);
        try {
            int iZ = k.z(cursorX, "mId");
            int iZ2 = k.z(cursorX, "ambient_lux");
            int iZ3 = k.z(cursorX, "ambient_lux_span");
            int iZ4 = k.z(cursorX, "orientation_and_app_industry");
            int iZ5 = k.z(cursorX, "app_code");
            int iZ6 = k.z(cursorX, "cur_brt");
            int iZ7 = k.z(cursorX, "cur_brt_span");
            int iZ8 = k.z(cursorX, "curve_brt");
            ArrayList arrayList = new ArrayList(cursorX.getCount());
            while (cursorX.moveToNext()) {
                h3.a aVar = new h3.a();
                aVar.f4045b = cursorX.getInt(iZ);
                aVar.c = cursorX.getFloat(iZ2);
                aVar.f4046d = cursorX.getFloat(iZ3);
                aVar.f4047e = cursorX.getFloat(iZ4);
                aVar.f4048f = cursorX.getFloat(iZ5);
                aVar.f4049g = cursorX.getFloat(iZ6);
                aVar.f4050h = cursorX.getFloat(iZ7);
                aVar.f4051i = cursorX.getFloat(iZ8);
                arrayList.add(aVar);
            }
            cursorX.close();
            pVarF.g();
            return arrayList;
        } catch (Throwable th) {
            cursorX.close();
            pVarF.g();
            throw th;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [h3.c$a, java.lang.Object, y0.d<h3.a>, y0.r] */
    @Override // h3.b
    public final void b(h3.a... aVarArr) {
        this.f4052a.b();
        this.f4052a.c();
        try {
            ?? r0 = this.f4053b;
            Objects.requireNonNull(r0);
            f fVarA = r0.a();
            try {
                for (h3.a aVar : aVarArr) {
                    r0.e(fVarA, aVar);
                    fVarA.v();
                }
                r0.d(fVarA);
                this.f4052a.n();
                this.f4052a.l();
            } catch (Throwable th) {
                r0.d(fVarA);
                throw th;
            }
        } catch (Throwable th2) {
            this.f4052a.l();
            throw th2;
        }
    }

    @Override // h3.b
    public final void c() {
        this.f4052a.b();
        f fVarA = this.c.a();
        this.f4052a.c();
        try {
            fVarA.o();
            this.f4052a.n();
        } finally {
            this.f4052a.l();
            this.c.d(fVarA);
        }
    }

    @Override // h3.b
    public final void d() {
        this.f4052a.b();
        f fVarA = this.f4054d.a();
        fVarA.k(1, 10000);
        this.f4052a.c();
        try {
            fVarA.o();
            this.f4052a.n();
        } finally {
            this.f4052a.l();
            this.f4054d.d(fVarA);
        }
    }

    @Override // h3.b
    public final void e() {
        this.f4052a.b();
        f fVarA = this.f4055e.a();
        this.f4052a.c();
        try {
            fVarA.o();
            this.f4052a.n();
        } finally {
            this.f4052a.l();
            this.f4055e.d(fVarA);
        }
    }

    @Override // h3.b
    public final h3.a f() {
        h3.a aVar;
        p pVarF = p.f("SELECT * FROM brightness_model_event ORDER BY mId DESC LIMIT 1", 0);
        this.f4052a.b();
        Cursor cursorX = m3.f.x(this.f4052a, pVarF);
        try {
            int iZ = k.z(cursorX, "mId");
            int iZ2 = k.z(cursorX, "ambient_lux");
            int iZ3 = k.z(cursorX, "ambient_lux_span");
            int iZ4 = k.z(cursorX, "orientation_and_app_industry");
            int iZ5 = k.z(cursorX, "app_code");
            int iZ6 = k.z(cursorX, "cur_brt");
            int iZ7 = k.z(cursorX, "cur_brt_span");
            int iZ8 = k.z(cursorX, "curve_brt");
            if (cursorX.moveToFirst()) {
                aVar = new h3.a();
                aVar.f4045b = cursorX.getInt(iZ);
                aVar.c = cursorX.getFloat(iZ2);
                aVar.f4046d = cursorX.getFloat(iZ3);
                aVar.f4047e = cursorX.getFloat(iZ4);
                aVar.f4048f = cursorX.getFloat(iZ5);
                aVar.f4049g = cursorX.getFloat(iZ6);
                aVar.f4050h = cursorX.getFloat(iZ7);
                aVar.f4051i = cursorX.getFloat(iZ8);
            } else {
                aVar = null;
            }
            return aVar;
        } finally {
            cursorX.close();
            pVarF.g();
        }
    }
}
