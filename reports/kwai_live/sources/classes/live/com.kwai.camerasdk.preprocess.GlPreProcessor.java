package com.kwai.camerasdk.preprocess;

import androidx.annotation.Keep;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.utils.f;
import com.kwai.camerasdk.video.VideoFrame;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public abstract class GlPreProcessor extends a {
    private static final String TAG = "GlPreProcessor";

    public GlPreProcessor() {
        this.nativeProcessor = nativeCreateGlPreProcessor(null);
    }

    private native long nativeCreateGlPreProcessor(String str);

    private native int nativeGetFrameHeight(long j12);

    private native int nativeGetFrameWidth(long j12);

    private native void nativePostRunnableToGlThread(long j12, Runnable runnable);

    private native void nativeReleaseGlPreProcessor(long j12);

    private native void nativeSetExternalFilterEnabled(long j12, boolean z12);

    @Override // com.kwai.camerasdk.preprocess.a
    protected long createNativeResource() {
        return 0L;
    }

    public f getLastFrameSize() {
        return new f(nativeGetFrameWidth(this.nativeProcessor), nativeGetFrameHeight(this.nativeProcessor));
    }

    public abstract void onDraw(VideoFrame videoFrame, int i12);

    public abstract void onRenderThreadDestroy();

    protected void post(Runnable runnable) {
        if (runnable == null) {
            Log.e(TAG, "post: wrong arg runnable is null");
        } else if (released()) {
            Log.e(TAG, "native processor already released");
        } else {
            nativePostRunnableToGlThread(this.nativeProcessor, runnable);
        }
    }

    @Override // com.kwai.camerasdk.preprocess.a
    protected void releaseNativeResource() {
        nativeReleaseGlPreProcessor(this.nativeProcessor);
    }

    public void setExternalFilterEnabled(boolean z12) {
        if (released()) {
            return;
        }
        nativeSetExternalFilterEnabled(this.nativeProcessor, z12);
    }

    public GlPreProcessor(String str) {
        this.nativeProcessor = nativeCreateGlPreProcessor(str);
    }
}
