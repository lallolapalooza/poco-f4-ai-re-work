package com.xiaomi.ai.streamplayer;

/* JADX INFO: loaded from: classes2.dex */
class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f17248a;

    public h(f fVar) {
        this.f17248a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f17248a.f();
        synchronized (this) {
            this.f17248a.f17241b.release();
            this.f17248a.f17245f = true;
        }
    }
}
