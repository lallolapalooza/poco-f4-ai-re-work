package com.kwai.video.westeros;

/* JADX INFO: loaded from: classes13.dex */
public abstract class WesterosPlugin {
    protected long nativePlugin;
    private volatile boolean released = false;

    public WesterosPlugin() {
        this.nativePlugin = 0L;
        this.nativePlugin = createNativePlugin();
    }

    protected abstract long createNativePlugin();

    long getNativePlugin() {
        return this.nativePlugin;
    }

    protected synchronized boolean isReleased() {
        return this.released;
    }

    public synchronized void release() {
        if (!this.released) {
            releaseNativePlugin(this.nativePlugin);
            this.nativePlugin = 0L;
            this.released = true;
        }
    }

    protected abstract void releaseNativePlugin(long j12);
}
