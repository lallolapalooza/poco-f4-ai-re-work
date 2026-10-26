package com.xiaomi.ai.mibrain;

/* JADX INFO: loaded from: classes2.dex */
public class MibrainVad {
    private static final int FIRSTPACK = 0;
    private static final int INTERMEIDATE_PACK = 1;
    private boolean mIsEnd;
    private boolean mIsSpeak;
    private int mPackCount;
    private long mVadInstance;

    static {
        Mibrainsdk.loadMibrainLibs();
    }

    private native long newVad();

    private native void releaseVad(long j10);

    private native int vadCheckBegin(long j10, byte[] bArr, int i10, int i11);

    private native int vadCheckEnd(long j10, byte[] bArr, int i10, int i11);

    private native int vadInit(long j10, float f10, float f11, float f12);

    private native int vadUnInit(long j10);

    public int init(int i10, int i11, int i12, float f10, float f11, float f12) {
        if (i10 != 16000 && i11 != 1 && i12 != 16) {
            return -1;
        }
        long jNewVad = newVad();
        this.mVadInstance = jNewVad;
        if (jNewVad == 0) {
            return -1;
        }
        if (vadInit(jNewVad, f10, f11, f12) == 0) {
            return 0;
        }
        releaseVad(this.mVadInstance);
        this.mVadInstance = 0L;
        return -1;
    }

    public boolean isSpeak(byte[] bArr, int i10) throws MibrainException {
        if (i10 < 8000 || i10 >= 16000) {
            throw new MibrainException("buffer must more than 8000bytes and less than 16000bytes");
        }
        if (bArr == null) {
            throw new MibrainException("buffer is null ");
        }
        if (!this.mIsEnd) {
            int i11 = this.mPackCount + 1;
            this.mPackCount = i11;
            boolean z10 = this.mIsSpeak;
            long j10 = this.mVadInstance;
            if (!z10) {
                if (vadCheckBegin(j10, bArr, i10, i11 == 1 ? 0 : 1) >= 0) {
                    this.mIsSpeak = true;
                }
            } else if (vadCheckEnd(j10, bArr, i10, 1) >= 0) {
                this.mIsSpeak = false;
                this.mIsEnd = true;
            }
        }
        return this.mIsSpeak;
    }

    public void release() {
        this.mIsEnd = true;
        long j10 = this.mVadInstance;
        if (j10 != 0) {
            vadUnInit(j10);
        }
        long j11 = this.mVadInstance;
        if (j11 != 0) {
            releaseVad(j11);
            this.mVadInstance = 0L;
        }
    }
}
