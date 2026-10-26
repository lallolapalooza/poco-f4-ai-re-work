package com.xiaomi.ai.mibrain;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MibrainVad2 {
    private static final String TAG = "MibrainVad2";
    private static boolean mLibLoaded = false;
    private long mTask;
    private long mVadInstance;
    private int[] mVadResult = new int[6];

    public class DecodeResult {
        public boolean hasVoice;
        public int minVoiceLength;
        public int packNumBeg;
        public int packNumEnd;
        public int packNumVoice;
        public int retVal;

        public DecodeResult() {
        }
    }

    private native long createTask(long j10);

    private native boolean hasVoice(long j10, long j11, byte[] bArr, int i10, int[] iArr);

    private native long newVad(int i10, int i11);

    private native boolean releaseVad(long j10);

    private native boolean stopTask(long j10, long j11, int[] iArr);

    public boolean init(int i10, int i11, int i12, int i13, int i14) {
        if (!mLibLoaded) {
            System.loadLibrary("mibrainvad2");
            mLibLoaded = true;
        }
        if (i10 != 16000 && i11 != 1 && i12 != 16) {
            return false;
        }
        long jNewVad = newVad(i13, i14);
        this.mVadInstance = jNewVad;
        this.mTask = createTask(jNewVad);
        return true;
    }

    public DecodeResult processTask(byte[] bArr, int i10) {
        if (bArr != null && i10 != 0) {
            DecodeResult decodeResult = new DecodeResult();
            Arrays.fill(this.mVadResult, 0);
            if (hasVoice(this.mVadInstance, this.mTask, bArr, i10, this.mVadResult)) {
                int[] iArr = this.mVadResult;
                decodeResult.retVal = iArr[0];
                decodeResult.hasVoice = iArr[1] == 1;
                decodeResult.packNumVoice = iArr[2];
                decodeResult.packNumBeg = iArr[3];
                decodeResult.packNumEnd = iArr[4];
                decodeResult.minVoiceLength = iArr[5];
                return decodeResult;
            }
        }
        return null;
    }

    public boolean release() {
        return releaseVad(this.mVadInstance);
    }

    public boolean reset() {
        Arrays.fill(this.mVadResult, 0);
        boolean zStopTask = stopTask(this.mVadInstance, this.mTask, this.mVadResult);
        if (zStopTask) {
            this.mTask = createTask(this.mVadInstance);
        }
        return zStopTask;
    }

    public DecodeResult stopTask() {
        DecodeResult decodeResult = new DecodeResult();
        if (!stopTask(this.mVadInstance, this.mTask, this.mVadResult)) {
            return null;
        }
        int[] iArr = this.mVadResult;
        decodeResult.retVal = iArr[0];
        decodeResult.hasVoice = iArr[1] == 1;
        decodeResult.packNumVoice = iArr[2];
        decodeResult.packNumBeg = iArr[3];
        decodeResult.packNumEnd = iArr[4];
        decodeResult.minVoiceLength = iArr[5];
        return decodeResult;
    }
}
