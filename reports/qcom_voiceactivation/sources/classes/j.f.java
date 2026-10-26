package p009j;

import com.quicinc.voice.activation.algorithm.FrameFlag;

/* JADX INFO: compiled from: AudioStreamProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f794a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f795b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f796c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final FrameFlag f797d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f798e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f799f;

    public f(boolean z2) {
        this(new byte[0], 0, 0, FrameFlag.FRAME_TYPE_NONE);
        this.f798e = z2;
    }

    public byte[] a() {
        return this.f794a;
    }

    public FrameFlag b() {
        return this.f797d;
    }

    public int c() {
        return Math.min(this.f794a.length - this.f795b, this.f796c);
    }

    public int d() {
        return this.f795b;
    }

    public boolean e() {
        return this.f798e;
    }

    public boolean f() {
        return this.f799f;
    }

    public String toString() {
        return "ReadResult{mBytes.length=" + this.f794a.length + ", mOffset=" + this.f795b + ", mLength=" + this.f796c + ", mFrameFlag=" + this.f797d + ", mCancelled=" + this.f798e + ", mFinished=" + this.f799f + '}';
    }

    public f(byte[] bArr, int i2, int i3, FrameFlag frameFlag) {
        this.f794a = bArr;
        this.f795b = i2;
        this.f796c = i3;
        this.f797d = frameFlag;
        this.f799f = frameFlag.b();
    }

    public f(byte[] bArr, int i2, int i3, FrameFlag frameFlag, boolean z2) {
        this.f794a = bArr;
        this.f795b = i2;
        this.f796c = i3;
        this.f797d = frameFlag;
        this.f799f = z2;
    }
}
