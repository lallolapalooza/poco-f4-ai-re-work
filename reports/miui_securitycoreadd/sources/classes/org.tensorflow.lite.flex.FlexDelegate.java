package org.tensorflow.lite.flex;

import java.io.Closeable;
import lb.b;
import org.tensorflow.lite.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection
public class FlexDelegate implements b, Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f6656b = nativeCreateDelegate();

    static {
        System.loadLibrary("tensorflowlite_flex_jni");
    }

    @UsedByReflection
    public FlexDelegate() {
    }

    private static native long nativeCreateDelegate();

    private static native void nativeDeleteDelegate(long j3);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @UsedByReflection
    public void close() {
        long j3 = this.f6656b;
        if (j3 != 0) {
            nativeDeleteDelegate(j3);
            this.f6656b = 0L;
        }
    }

    @Override // lb.b
    @UsedByReflection
    public long getNativeHandle() {
        return this.f6656b;
    }
}
