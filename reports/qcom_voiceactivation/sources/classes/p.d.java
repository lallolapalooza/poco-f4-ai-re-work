package p015p;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: OneVoiceFSMSManager.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AtomicInteger f1156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f1157b;

    public d(f fVar, int i2) {
        this.f1157b = fVar;
        this.f1156a = new AtomicInteger(i2);
    }

    public void a() {
        if (this.f1156a.get() > 0) {
            this.f1156a.getAndDecrement();
        }
    }

    public int b() {
        return this.f1156a.get();
    }

    public void c() {
        this.f1156a.getAndIncrement();
    }

    public boolean d() {
        return this.f1156a.get() <= 1;
    }
}
