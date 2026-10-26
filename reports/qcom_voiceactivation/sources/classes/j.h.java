package p009j;

import G.q;
import H.e;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: AudioStreamProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantLock f809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Condition f810b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicInteger f811c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public AtomicBoolean f812d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f813e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f814f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile boolean f815g;

    public h(int i2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f809a = reentrantLock;
        this.f810b = reentrantLock.newCondition();
        this.f811c = new AtomicInteger(0);
        this.f812d = new AtomicBoolean(false);
        this.f815g = true;
        this.f814f = 0;
        this.f813e = new byte[i.c(i2)];
    }

    public void a() {
        this.f809a.lock();
        try {
            q.h("cancel=" + this.f812d.get());
            this.f812d.compareAndSet(false, true);
            q.h("cancel=" + this.f812d.get());
            this.f810b.signalAll();
        } finally {
            this.f809a.unlock();
        }
    }

    public void b() {
        q.h("decrementReader = " + (this.f811c.compareAndSet(0, 0) ? 0 : this.f811c.decrementAndGet()));
    }

    public byte[] c(b bVar) {
        q.h("getAudioStream enter");
        this.f809a.lock();
        while (!this.f812d.get() && !bVar.b(this.f814f)) {
            try {
                try {
                    q.h("await enter");
                    this.f810b.await();
                    q.h("await exit");
                } catch (InterruptedException e2) {
                    q.h(Arrays.toString(e2.getStackTrace()));
                    Thread.currentThread().interrupt();
                    this.f809a.unlock();
                    q.h("getAudioStream exit");
                    return new byte[0];
                }
            } catch (Throwable th) {
                this.f809a.unlock();
                q.h("getAudioStream exit");
                throw th;
            }
        }
        byte[] bArr = this.f813e;
        this.f809a.unlock();
        q.h("getAudioStream exit");
        return bArr;
    }

    public int d() {
        return this.f814f;
    }

    public b e(int i2) {
        q.h("getDNNVADReader enter");
        this.f809a.lock();
        try {
            j();
            return new e(this, i2);
        } finally {
            this.f809a.unlock();
            q.h("getDNNVADReader exit");
        }
    }

    public b f(int i2, int i3, int i4, int i5) {
        q.h("getNormalReader enter");
        this.f809a.lock();
        try {
            j();
            return new c(this, i2, i3, i4, i5);
        } finally {
            this.f809a.unlock();
            q.h("getNormalReader exit");
        }
    }

    public b g(int i2, int i3, int i4, int i5) {
        q.h("getOneShotReader enter");
        this.f809a.lock();
        try {
            j();
            return new d(this, i2, i3, i4, i5);
        } finally {
            this.f809a.unlock();
            q.h("getOneShotReader exit");
        }
    }

    public b h(int i2, int i3, int i4, int i5) {
        q.h("getPDKReader enter");
        this.f809a.lock();
        try {
            j();
            return new e(this, i2, i3, i4, i5);
        } finally {
            this.f809a.unlock();
            q.h("getPDKReader exit");
        }
    }

    public b i(int i2, int i3, int i4, int i5) {
        q.h("getTIUVReader enter");
        this.f809a.lock();
        try {
            j();
            return new g(this, i2, i3, i4, i5);
        } finally {
            this.f809a.unlock();
            q.h("getTIUVReader exit");
        }
    }

    public final void j() {
        q.h("incrementReader = " + this.f811c.incrementAndGet());
    }

    public boolean k() {
        return this.f812d.get();
    }

    public void l(byte[] bArr, int i2) {
        q.h("mReaderCount.get()=" + this.f811c.get());
        if (this.f813e.length - this.f814f > i2) {
            if (this.f815g || this.f811c.get() != 0) {
                q.h("putAudioStream enter");
                this.f809a.lock();
                q.h("putAudioStream lock");
                try {
                    try {
                        q.h("mAudioBytes.size=" + this.f813e.length + ", mCurrentIndex=" + this.f814f + ", length=" + i2);
                        System.arraycopy(bArr, 0, this.f813e, this.f814f, i2);
                        this.f814f = this.f814f + i2;
                    } catch (Exception e2) {
                        q.h(Arrays.toString(e2.getStackTrace()));
                    }
                } finally {
                    q.h("signalAll enter");
                    this.f810b.signalAll();
                    q.h("signalAll exit");
                    this.f809a.unlock();
                    q.h("putAudioStream exit");
                }
            }
        }
    }

    public void m(boolean z2) {
        this.f815g = z2;
    }
}
