package com.google.mlkit.vision.barcode.internal;

import com.google.mlkit.vision.common.internal.MobileVisionBase;
import defpackage.AbstractC11388Wai;
import defpackage.C0898Bt0;
import defpackage.C12266Xt0;
import defpackage.C1241Ck6;
import defpackage.C13633a9i;
import defpackage.C42306xJi;
import defpackage.C44965zT2;
import defpackage.CAd;
import defpackage.CLi;
import defpackage.CNi;
import defpackage.EnumC15111bLi;
import defpackage.InterfaceC11750Wt0;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class BarcodeScannerImpl extends MobileVisionBase<List<C0898Bt0>> implements InterfaceC11750Wt0 {
    static {
        new C1241Ck6(11);
    }

    public BarcodeScannerImpl(C12266Xt0 c12266Xt0, C42306xJi c42306xJi, Executor executor, CNi cNi) {
        super(c42306xJi, executor);
        C13633a9i c13633a9i = new C13633a9i(2);
        c13633a9i.b = AbstractC11388Wai.a(c12266Xt0);
        CLi cLi = new CLi(c13633a9i);
        CAd cAd = new CAd(4);
        cAd.e = cLi;
        cNi.c(new C44965zT2(cAd, 1), EnumC15111bLi.ON_DEVICE_BARCODE_CREATE, cNi.d());
    }
}
