package com.kwai.camerasdk.preprocess;

import androidx.annotation.Keep;
import com.kwai.camerasdk.audio.AudioFrame;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public abstract class AudioProcessor extends a {
    private static final String TAG = "AudioProcessor";

    static {
        com.kwai.camerasdk.utils.a.b();
    }

    private native long nativeCreateAudioProcessor();

    private native void nativeReleaseAudioProcessor(long j12);

    @Override // com.kwai.camerasdk.preprocess.a
    protected long createNativeResource() {
        return nativeCreateAudioProcessor();
    }

    public abstract AudioFrame onAudioFrame(AudioFrame audioFrame);

    @Override // com.kwai.camerasdk.preprocess.a
    protected void releaseNativeResource() {
        nativeReleaseAudioProcessor(this.nativeProcessor);
    }
}
