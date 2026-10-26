package com.kwai.video.westeros.audioplugin;

import com.kwai.video.westeros.WesterosPlugin;
import com.kwai.video.westeros.helpers.WesterosSoLoader;

/* JADX INFO: loaded from: classes13.dex */
public class AudioPlugin extends WesterosPlugin {
    static {
        WesterosSoLoader.loadNative();
        WesterosSoLoader.loadLibrary("audioplugin");
    }

    private native long nativeCreateAudioPlugin();

    private native void nativeDestroyAudioPlugin(long j12);

    @Override // com.kwai.video.westeros.WesterosPlugin
    protected long createNativePlugin() {
        return nativeCreateAudioPlugin();
    }

    @Override // com.kwai.video.westeros.WesterosPlugin
    protected void releaseNativePlugin(long j12) {
        nativeDestroyAudioPlugin(j12);
    }
}
