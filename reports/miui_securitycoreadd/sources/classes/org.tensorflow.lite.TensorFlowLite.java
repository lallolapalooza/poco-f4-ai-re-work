package org.tensorflow.lite;

/* JADX INFO: loaded from: classes.dex */
public final class TensorFlowLite {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final UnsatisfiedLinkError f6651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f6652b = false;

    static {
        try {
            System.loadLibrary("tensorflowlite_jni");
        } catch (UnsatisfiedLinkError e10) {
            e = e10;
            try {
                System.loadLibrary("tensorflowlite_jni_stable");
            } catch (UnsatisfiedLinkError unused) {
            }
        }
        e = null;
        f6651a = e;
    }

    public static void a() {
        if (f6652b) {
            return;
        }
        try {
            nativeDoNothing();
            f6652b = true;
        } catch (UnsatisfiedLinkError e10) {
            e = e10;
            UnsatisfiedLinkError unsatisfiedLinkError = f6651a;
            if (unsatisfiedLinkError != null) {
                e = unsatisfiedLinkError;
            }
            throw new UnsatisfiedLinkError("Failed to load native TensorFlow Lite methods. Check that the correct native libraries are present, and, if using a custom native library, have been properly loaded via System.loadLibrary():\n  " + e);
        }
    }

    private static native void nativeDoNothing();
}
