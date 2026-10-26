package p009j;

import G.q;
import com.quicinc.voice.activation.algorithm.FrameFlag;

/* JADX INFO: compiled from: AudioStreamProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f783b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f784c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f785d;

    public d(h hVar, int i2, int i3, int i4, int i5) {
        this.f782a = hVar;
        int iD = b.d(i2, i4);
        this.f784c = iD;
        this.f783b = iD;
        this.f785d = i3 + i5;
        q.h(toString());
    }

    @Override // p009j.b
    public int a() {
        return this.f785d;
    }

    @Override // p009j.b
    public boolean b(int i2) {
        q.h(toString());
        return i2 >= this.f785d;
    }

    @Override // p009j.b
    public int c() {
        return this.f783b;
    }

    @Override // p009j.b
    public void e() {
        this.f782a.b();
    }

    @Override // p009j.b
    public f f() {
        byte[] bArrC = this.f782a.c(this);
        q.h(toString());
        if (this.f782a.k()) {
            return new f(true);
        }
        int i2 = this.f784c;
        return new f(bArrC, i2, this.f785d - i2, FrameFlag.FRAME_TYPE_ONE_SHOT);
    }

    public String toString() {
        return "OneShotReader{mReadStart=" + this.f784c + ", mFinishEnd=" + this.f785d + '}';
    }
}
