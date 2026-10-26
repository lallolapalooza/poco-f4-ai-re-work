package dh;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
public class e {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static f[] f44577h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g[] f44579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f44580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f44581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f44582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f44583f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ah.g f44578a = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f44584g = false;

    public e() {
        int i10 = 0;
        if (f44577h == null) {
            f44577h = new f[]{new j(), new b(), new c()};
        }
        this.f44579b = new g[]{new i(), new h()};
        this.f44580c = 0;
        this.f44581d = new int[f44577h.length];
        while (true) {
            f[] fVarArr = f44577h;
            if (i10 >= fVarArr.length) {
                return;
            }
            int iA = fVarArr[i10].a();
            int[] iArr = this.f44581d;
            int i11 = this.f44580c;
            iArr[i10] = i11;
            this.f44580c = i11 + iA;
            i10++;
        }
    }

    private void a(ah.b bVar) {
        List<String> listE = bVar.e();
        TreeSet treeSet = new TreeSet();
        Iterator<String> it = listE.iterator();
        while (it.hasNext()) {
            for (String str : this.f44578a.a(it.next())) {
                if (ah.i.a(str) || this.f44578a.c(str)) {
                    treeSet.add(str);
                }
            }
        }
        bVar.o(treeSet);
    }

    public int b() {
        if (this.f44584g) {
            return this.f44582e + this.f44580c;
        }
        Log.e("RuleManager", "getRuleCount failed for having not be initialized.");
        return 0;
    }

    public g[] c() {
        return this.f44579b;
    }

    public void d() {
        int i10 = 0;
        this.f44582e = 0;
        this.f44583f = new int[this.f44579b.length];
        while (true) {
            g[] gVarArr = this.f44579b;
            if (i10 >= gVarArr.length) {
                this.f44584g = true;
                return;
            }
            int iA = gVarArr[i10].a();
            int[] iArr = this.f44583f;
            int i11 = this.f44582e;
            iArr[i10] = i11;
            this.f44582e = i11 + iA;
            i10++;
        }
    }

    public void e(ah.b bVar) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add(bVar.d());
        int[] iArr = new int[this.f44580c];
        int i10 = 0;
        while (true) {
            f[] fVarArr = f44577h;
            if (i10 >= fVarArr.length) {
                bVar.l(arrayList);
                bVar.m(iArr);
                return;
            } else {
                fVarArr[i10].f();
                arrayList = f44577h[i10].e(arrayList);
                f44577h[i10].c(bVar, iArr, this.f44581d[i10]);
                i10++;
            }
        }
    }

    public void f(ah.b bVar) {
        if (bVar.h() == null) {
            a(bVar);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(bVar.h());
            Collections.sort(arrayList);
            bVar.p(arrayList);
        }
        int[] iArr = new int[b()];
        int[] iArrF = bVar.f();
        int[] iArr2 = new int[b()];
        int i10 = 0;
        for (int i11 = 0; i11 < this.f44580c; i11++) {
            iArr2[i11] = iArrF[i11];
        }
        while (true) {
            g[] gVarArr = this.f44579b;
            if (i10 >= gVarArr.length) {
                bVar.m(iArr2);
                return;
            } else {
                gVarArr[i10].c(bVar, iArr2, this.f44580c + this.f44583f[i10]);
                i10++;
            }
        }
    }

    public void g(ah.g gVar) {
        this.f44578a = gVar;
    }
}
