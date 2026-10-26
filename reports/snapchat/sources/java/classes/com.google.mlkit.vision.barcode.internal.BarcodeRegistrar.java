package com.google.mlkit.vision.barcode.internal;

import defpackage.AT2;
import defpackage.AbstractC10153Tqc;
import defpackage.AbstractC5369Kji;
import defpackage.BI4;
import defpackage.C0776Bmi;
import defpackage.C14330aii;
import defpackage.C22289h93;
import defpackage.C24445it5;
import defpackage.C34782rEi;
import defpackage.C5349Kii;
import defpackage.G6h;
import defpackage.Gzi;
import defpackage.KT2;
import defpackage.U8a;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BarcodeRegistrar implements KT2 {
    @Override // defpackage.KT2
    public final List getComponents() {
        C22289h93 c22289h93A = AT2.a(C34782rEi.class);
        c22289h93A.a(new BI4(U8a.class, 1, 0));
        c22289h93A.e = G6h.T;
        AT2 at2B = c22289h93A.b();
        C22289h93 c22289h93A2 = AT2.a(Gzi.class);
        c22289h93A2.a(new BI4(C34782rEi.class, 1, 0));
        c22289h93A2.a(new BI4(C24445it5.class, 1, 0));
        c22289h93A2.e = C14330aii.c;
        AT2 at2B2 = c22289h93A2.b();
        C5349Kii c5349Kii = AbstractC5369Kji.b;
        Object[] objArr = {at2B, at2B2};
        for (int i = 0; i < 2; i++) {
            if (objArr[i] == null) {
                throw new NullPointerException(AbstractC10153Tqc.k(20, "at index ", i));
            }
        }
        return new C0776Bmi(objArr, 2);
    }
}
