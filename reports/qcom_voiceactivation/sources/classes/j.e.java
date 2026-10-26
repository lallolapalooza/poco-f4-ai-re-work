package p009j;

import G.q;
import com.quicinc.voice.activation.algorithm.FrameFlag;

/* JADX INFO: compiled from: AudioStreamProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f786h = i.j() * 120;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f788b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f789c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f790d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f791e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f792f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f793g = true;

    public e(h hVar, int i2, int i3, int i4, int i5) {
        this.f787a = hVar;
        int iD = b.d(i2, i4);
        this.f790d = iD;
        this.f788b = iD;
        this.f791e = i3;
        this.f792f = i3 + i5;
        this.f789c = f786h;
        q.h(toString());
    }

    @Override // p009j.b
    public int a() {
        return this.f792f;
    }

    @Override // p009j.b
    public boolean b(int i2) {
        q.h(toString());
        if (this.f793g) {
            return i2 > this.f791e;
        }
        return this.f790d + this.f789c < i2;
    }

    @Override // p009j.b
    public int c() {
        return this.f788b;
    }

    @Override // p009j.b
    public void e() {
        this.f787a.b();
    }

    @Override // p009j.b
    public f f() {
        int i2;
        FrameFlag frameFlag;
        byte[] bArrC = this.f787a.c(this);
        if (this.f787a.k()) {
            return new f(true);
        }
        int i3 = this.f790d;
        if (this.f793g) {
            this.f793g = false;
            frameFlag = FrameFlag.FRAME_TYPE_HEAD;
            i2 = this.f791e - i3;
            this.f790d = i3 + i2;
        } else {
            i2 = this.f789c;
            int i4 = i3 + i2;
            this.f790d = i4;
            frameFlag = i4 >= this.f792f ? FrameFlag.FRAME_TYPE_TAIL : FrameFlag.FRAME_TYPE_MIDDLE;
        }
        q.h(toString());
        q.h("flag=" + frameFlag.name());
        return new f(bArrC, i3, i2, frameFlag);
    }

    public String toString() {
        return "PDKReader{mReadSize=" + this.f789c + ", mReadStart=" + this.f790d + ", mFirstReadEnd=" + this.f791e + ", mFinishEnd=" + this.f792f + ", mIsFirstRead=" + this.f793g + '}';
    }
}
