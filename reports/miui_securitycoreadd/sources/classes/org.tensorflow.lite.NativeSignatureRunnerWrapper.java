package org.tensorflow.lite;

import androidx.activity.e;

/* JADX INFO: loaded from: classes.dex */
final class NativeSignatureRunnerWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6650b;
    public boolean c = false;

    public NativeSignatureRunnerWrapper(long j3, long j10, String str) {
        this.f6650b = j10;
        long jNativeGetSignatureRunner = nativeGetSignatureRunner(j3, str);
        this.f6649a = jNativeGetSignatureRunner;
        if (jNativeGetSignatureRunner == -1) {
            throw new IllegalArgumentException(e.r("Input error: Signature ", str, " not found."));
        }
    }

    private static native void nativeAllocateTensors(long j3, long j10);

    private static native int nativeGetInputIndex(long j3, String str);

    private static native int nativeGetOutputIndex(long j3, String str);

    private static native long nativeGetSignatureRunner(long j3, String str);

    private static native int nativeGetSubgraphIndex(long j3);

    private static native void nativeInvoke(long j3, long j10);

    private static native boolean nativeResizeInput(long j3, long j10, String str, int[] iArr);

    public final void a() {
        if (this.c) {
            return;
        }
        nativeAllocateTensors(this.f6649a, this.f6650b);
        this.c = true;
    }

    public final int b(String str) {
        int iNativeGetInputIndex = nativeGetInputIndex(this.f6649a, str);
        if (iNativeGetInputIndex != -1) {
            return iNativeGetInputIndex;
        }
        throw new IllegalArgumentException(e.r("Input error: input ", str, " not found."));
    }

    public final int c(String str) {
        int iNativeGetOutputIndex = nativeGetOutputIndex(this.f6649a, str);
        if (iNativeGetOutputIndex != -1) {
            return iNativeGetOutputIndex;
        }
        throw new IllegalArgumentException(e.r("Input error: output ", str, " not found."));
    }

    public final int d() {
        return nativeGetSubgraphIndex(this.f6649a);
    }

    public final void e() {
        nativeInvoke(this.f6649a, this.f6650b);
    }

    public final boolean f(String str, int[] iArr) {
        this.c = false;
        return nativeResizeInput(this.f6649a, this.f6650b, str, iArr);
    }
}
