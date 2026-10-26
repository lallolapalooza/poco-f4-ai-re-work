package com.kwai.camerasdk.preprocess;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public abstract class AbstractGlProcessor extends a {
    static {
        com.kwai.camerasdk.utils.a.b();
    }

    private native void nativeReleaseGlProcessor(long j12);

    @Override // com.kwai.camerasdk.preprocess.a
    protected void releaseNativeResource() {
        nativeReleaseGlProcessor(this.nativeProcessor);
    }
}
