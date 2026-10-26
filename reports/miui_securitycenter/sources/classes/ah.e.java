package ah;

import android.content.Context;
import com.google.android.exoplayer2.C;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected ch.d f521a;

    protected static boolean b(b bVar) {
        return (bVar.g() & C.BUFFER_FLAG_FIRST_SAMPLE) != 0;
    }

    protected static boolean e(b bVar) {
        return (bVar.g() & 1) != 0;
    }

    protected void a(b bVar, Context context) {
        String strC;
        if (this.f521a == null && (strC = c(context)) != null) {
            ch.d dVar = new ch.d();
            this.f521a = dVar;
            dVar.d(strC);
        }
        ch.d dVar2 = this.f521a;
        if (dVar2 == null || !dVar2.c()) {
            return;
        }
        bVar.n(this.f521a.e(bVar));
    }

    protected String c(Context context) {
        return c.a(context);
    }

    protected abstract boolean d(b bVar, Context context);

    public void f(b bVar, Context context) {
        if (i.b(bVar.e())) {
            bVar.a(1);
        }
        if (d(bVar, context)) {
            bVar.a(2);
        }
    }

    protected double[] g(double[] dArr, b bVar) {
        int iG = bVar.g();
        int iB = ch.d.b(iG);
        int iA = ch.d.a(iG);
        if ((iG & 268435455) == 0 || iB == iA) {
            return dArr;
        }
        int length = dArr.length;
        double[] dArr2 = new double[length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        double d10 = (((double) (iA - iB)) * (dArr2[0] - dArr2[1])) / 4.0d;
        for (int i10 = 0; i10 < length; i10++) {
            dArr2[i10] = dArr2[i10] + d10;
        }
        return dArr2;
    }
}
