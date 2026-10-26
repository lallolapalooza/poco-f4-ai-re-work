package p009j;

import G.q;
import H.e;
import com.quicinc.voice.activation.algorithm.FrameFlag;
import com.quicinc.voice.activation.vad.DNNVADAction;

/* JADX INFO: compiled from: AudioStreamProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DNNVADAction f801b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f802c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f803d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f804e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f805f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f806g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f807h = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f808i = Integer.MIN_VALUE;

    public g(h hVar, int i2, int i3, int i4, int i5) {
        this.f800a = hVar;
        this.f802c = b.d(i2, i4);
        int i6 = i5 + i3;
        this.f803d = i6;
        this.f804e = i6;
        this.f805f = e.f169e + i3;
        q.h(toString());
        g(i3);
    }

    @Override // p009j.b
    public int a() {
        return this.f804e;
    }

    @Override // p009j.b
    public boolean b(int i2) {
        q.h(toString());
        if (this.f806g) {
            if (i2 < this.f803d) {
                return false;
            }
            h();
            return true;
        }
        int iG = this.f801b.g(DNNVADAction.ResultType.RESULT_START_DETECTION);
        this.f807h = iG;
        if (iG < 0) {
            return false;
        }
        int iG2 = this.f801b.g(DNNVADAction.ResultType.RESULT_END_DETECTION);
        this.f808i = iG2;
        return iG2 >= 0;
    }

    @Override // p009j.b
    public int c() {
        return this.f802c;
    }

    @Override // p009j.b
    public void e() {
        this.f800a.b();
        i();
    }

    @Override // p009j.b
    public f f() {
        if (this.f800a.k()) {
            return new f(true);
        }
        byte[] bArrC = this.f800a.c(this);
        if (this.f806g) {
            this.f806g = false;
            FrameFlag frameFlag = FrameFlag.FRAME_TYPE_ONE_SHOT;
            int i2 = this.f803d - this.f802c;
            q.h(toString());
            q.h("flag=" + frameFlag.name());
            return new f(bArrC, this.f802c, i2, frameFlag, false);
        }
        int iD = this.f800a.d();
        int i3 = this.f808i;
        if (i3 != 0) {
            iD = Math.min(iD, this.f805f + i3);
        }
        this.f804e = iD;
        int i4 = iD - this.f802c;
        FrameFlag frameFlag2 = FrameFlag.FRAME_TYPE_ONE_SHOT;
        q.h(toString());
        q.h("flag=" + frameFlag2.name());
        return new f(bArrC, this.f802c, i4, frameFlag2, true);
    }

    public final void g(int i2) {
        this.f801b = new DNNVADAction(p003d.g.l(), p003d.g.f(), i2);
    }

    public final void h() {
        q.h("processVad");
        this.f801b.f();
    }

    public final void i() {
        q.h("releaseVad");
        this.f801b.c();
    }

    public String toString() {
        return "TIUVReader{mIsFirstRead=" + this.f806g + ", mReadStart=" + this.f802c + ", mFirstReadEnd=" + this.f803d + ", mFinishEnd=" + this.f804e + ", mCMDStartIndex=" + this.f807h + ", mCMDEndIndex=" + this.f808i + '}';
    }
}
