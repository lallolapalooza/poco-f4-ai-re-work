package com.kwai.camerasdk.preprocess;

/* JADX INFO: compiled from: AbstractPreProcessor.java */
/* JADX INFO: loaded from: classes15.dex */
public abstract class a {
    private static final String TAG = "AbstractPreProcessor";
    private volatile boolean released = false;
    protected long nativeProcessor = createNativeResource();

    protected abstract long createNativeResource();

    public long getNativeProcessor() {
        return this.nativeProcessor;
    }

    public void release() {
        if (this.released) {
            return;
        }
        this.released = true;
        releaseNativeResource();
    }

    protected abstract void releaseNativeResource();

    protected boolean released() {
        return this.released;
    }
}
