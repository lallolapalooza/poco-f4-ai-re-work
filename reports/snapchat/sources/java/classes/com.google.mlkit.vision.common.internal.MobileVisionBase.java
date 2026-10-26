package com.google.mlkit.vision.common.internal;

import com.google.mlkit.vision.common.internal.MobileVisionBase;
import defpackage.AF8;
import defpackage.AbstractC40800w69;
import defpackage.C14851b8i;
import defpackage.GUi;
import defpackage.InterfaceC41320wWa;
import defpackage.LK0;
import defpackage.OF8;
import defpackage.P0i;
import defpackage.RunnableC25796jyi;
import java.io.Closeable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class MobileVisionBase<DetectionResultT> implements Closeable, OF8 {
    public static final LK0 S = new LK0("MobileVisionBase", "", 1);
    public final Executor R;
    public final AtomicBoolean a = new AtomicBoolean(false);
    public final AbstractC40800w69 b;
    public final P0i c;

    public MobileVisionBase(AbstractC40800w69 abstractC40800w69, Executor executor) {
        this.b = abstractC40800w69;
        P0i p0i = new P0i(2);
        this.c = p0i;
        this.R = executor;
        abstractC40800w69.b.incrementAndGet();
        abstractC40800w69.a(executor, new Callable() { // from class: Hzi
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LK0 lk0 = MobileVisionBase.S;
                return null;
            }
        }, (P0i) p0i.b).b(C14851b8i.b);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @InterfaceC41320wWa(AF8.ON_DESTROY)
    public synchronized void close() {
        boolean z = true;
        if (this.a.getAndSet(true)) {
            return;
        }
        this.c.a();
        AbstractC40800w69 abstractC40800w69 = this.b;
        Executor executor = this.R;
        if (abstractC40800w69.b.get() <= 0) {
            z = false;
        }
        GUi.P(z);
        abstractC40800w69.a.v(executor, new RunnableC25796jyi(abstractC40800w69, 3));
    }
}
