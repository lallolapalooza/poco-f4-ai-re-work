package com.kwai.video.westeros.v2;

import com.kwai.camerasdk.preprocess.AbstractGlProcessor;
import com.kwai.camerasdk.preprocess.a;

/* JADX INFO: loaded from: classes13.dex */
public abstract class WesterosGlProcessor {
    private static final String TAG = "WesterosGlProcessor";
    protected long nativeProcessor;
    private volatile boolean released = false;
    private a daenerysProcessor = null;

    private static class WesterosDaenerysGlProcessor extends AbstractGlProcessor {
        public WesterosDaenerysGlProcessor(long j12) {
            this.nativeProcessor = j12;
        }

        @Override // com.kwai.camerasdk.preprocess.a
        protected long createNativeResource() {
            return 0L;
        }
    }

    public WesterosGlProcessor() {
        this.nativeProcessor = 0L;
        this.nativeProcessor = createNativeProcessor();
    }

    private native long nativeGetDaenerysGlProcessor(long j12);

    private native void nativeReleaseWesterosGlProcessor(long j12);

    private void releaseNativeProcessor(long j12) {
        long j13 = this.nativeProcessor;
        if (j13 != 0) {
            nativeReleaseWesterosGlProcessor(j13);
        }
    }

    protected abstract long createNativeProcessor();

    public void release() {
        if (this.released) {
            return;
        }
        this.released = true;
        releaseNativeProcessor(this.nativeProcessor);
        a aVar = this.daenerysProcessor;
        if (aVar != null) {
            aVar.release();
        }
    }

    protected boolean released() {
        return this.released;
    }

    public a toDaenerysProcessor() {
        if (this.daenerysProcessor == null) {
            this.daenerysProcessor = new WesterosDaenerysGlProcessor(nativeGetDaenerysGlProcessor(this.nativeProcessor));
        }
        return this.daenerysProcessor;
    }
}
