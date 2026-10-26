package p009j;

import G.q;
import com.quicinc.voice.activation.algorithm.FrameFlag;

/* JADX INFO: compiled from: AudioStreamProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f777a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f778b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f779c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f780d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f781e = true;

    public c(h hVar, int i2, int i3, int i4, int i5) {
        this.f777a = hVar;
        int iD = b.d(i2, i4);
        this.f779c = iD;
        this.f778b = iD;
        this.f780d = i3 + i5;
        q.h(toString());
    }

    @Override // p009j.b
    public int a() {
        return this.f780d;
    }

    @Override // p009j.b
    public boolean b(int i2) {
        q.h(toString());
        return true;
    }

    @Override // p009j.b
    public int c() {
        return this.f778b;
    }

    @Override // p009j.b
    public void e() {
        this.f777a.b();
    }

    @Override // p009j.b
    public f f() {
        FrameFlag frameFlag = FrameFlag.FRAME_TYPE_MIDDLE;
        byte[] bArrC = this.f777a.c(this);
        if (this.f781e) {
            this.f781e = false;
            frameFlag = FrameFlag.FRAME_TYPE_HEAD;
        }
        int iD = this.f777a.d();
        int i2 = this.f779c;
        int i3 = iD - i2;
        int i4 = i2 + i3;
        this.f779c = i4;
        if (i4 >= this.f780d) {
            frameFlag = FrameFlag.FRAME_TYPE_TAIL;
        }
        q.h(toString());
        q.h("flag=" + frameFlag.name());
        return new f(bArrC, this.f779c, i3, frameFlag);
    }

    public String toString() {
        return "NormalReader{mReadStart=" + this.f779c + ", mFinishEnd=" + this.f780d + ", mIsFirstRead=" + this.f781e + '}';
    }
}
