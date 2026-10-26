package com.kwai.kve;

import com.yxcorp.bugly.CrashReporter;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes16.dex */
public class SmartUploadAnalyzer {
    private long mNativeAddress = createNative();

    private native void analyseNative(long j12, ByteBuffer[] byteBufferArr, int[] iArr);

    private native void closeNative(long j12);

    private native long createNative();

    private native void deleteNative(long j12);

    private native int getHardEncBitrateNative(long j12);

    private native int getSoftEncEstBitrateNative(long j12);

    private native int getSoftEncMaxBitrateNative(long j12);

    private native boolean openNative(long j12, int i12, int i13, int i14, int i15, float f12);

    private native boolean openWithModelTypeNative(long j12, int i12, int i13, int i14, int i15, float f12, int i16);

    public void analyse(ByteBuffer[] byteBufferArr, int[] iArr) {
        analyseNative(this.mNativeAddress, byteBufferArr, iArr);
    }

    public void close() {
        closeNative(this.mNativeAddress);
    }

    protected void finalize() throws Throwable {
        try {
            long j12 = this.mNativeAddress;
            if (j12 != 0) {
                deleteNative(j12);
            }
        } finally {
            CrashReporter.logCheckedException(th, "SmartUploadAnalyzer.class", "finalize", 19);
            super.finalize();
        }
    }

    public int getHardEncBitrate() {
        return getHardEncBitrateNative(this.mNativeAddress);
    }

    public int getSoftEncEstBitrate() {
        return getSoftEncEstBitrateNative(this.mNativeAddress);
    }

    public int getSoftEncMaxBitrate() {
        return getSoftEncMaxBitrateNative(this.mNativeAddress);
    }

    public boolean open(int i12, int i13, int i14, int i15, float f12) {
        return openNative(this.mNativeAddress, i12, i13, i14, i15, f12);
    }

    public boolean open(int i12, int i13, int i14, int i15, float f12, int i16) {
        return openWithModelTypeNative(this.mNativeAddress, i12, i13, i14, i15, f12, i16);
    }
}
