package org.tensorflow.lite;

/* JADX INFO: loaded from: classes.dex */
public class b implements AutoCloseable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public NativeInterpreterWrapper f6655b;

    public b(NativeInterpreterWrapper nativeInterpreterWrapper) {
        this.f6655b = nativeInterpreterWrapper;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        NativeInterpreterWrapper nativeInterpreterWrapper = this.f6655b;
        if (nativeInterpreterWrapper != null) {
            nativeInterpreterWrapper.close();
            this.f6655b = null;
        }
    }

    public final void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
