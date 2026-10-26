package com.xiaomi.ai;

/* JADX INFO: loaded from: classes2.dex */
class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c.e f17184a;

    public l(c.e eVar) {
        this.f17184a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.f17184a.c()) {
            this.f17184a.b();
        }
        this.f17184a.f17151b.clear();
    }
}
