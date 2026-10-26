package com.kwai.kve;

import java.util.List;

/* JADX INFO: loaded from: classes16.dex */
public class SmartEditTaskBuilder {
    private FlashImageCallback mFlashImageCallback;
    private ProgressCallback mTotalProgressCallback;
    private VoiceDetectorCallback mVoiceDetectorCallback;
    private Decoder mDecoder = null;
    private List<? extends MediaAsset> mFiles = null;
    private MediaAsset mCover = null;
    private long mMemoryConfig = getDefaultMemoryConfig();

    SmartEditTaskBuilder() {
    }

    private native long getDefaultMemoryConfig();

    private native void releaseConfig(long j12);

    private native void setAlbumType(long j12, AlbumType albumType);

    private native void setAnalysisDimLimitNative(long j12, float f12);

    private native void setAnalysisDurationLimitNative(long j12, float f12);

    private native void setCacheFolder(long j12, String str);

    private native void setDedupThresholdNative(long j12, float f12);

    private native void setDesignatedMusic(long j12, String str);

    private native void setDesignatedTheme(long j12, String str);

    private native void setImageClipDurationNative(long j12, float f12);

    private native void setMaxAnalysisImageNumberNative(long j12, int i12);

    private native void setMaxAnalysisVideoNumberNative(long j12, int i12);

    private native void setMaxPresentedVideoNumberNative(long j12, int i12);

    private native void setMaxTotalDurationNative(long j12, float f12);

    private native void setMinAssetNumberNative(long j12, int i12);

    private native void setMinFlashImageInterval(long j12, float f12);

    private native void setMinImageDimRequiredNative(long j12, float f12);

    private native void setMinVideoDimRequiredNative(long j12, float f12);

    private native void setPresentedHeightNative(long j12, int i12);

    private native void setPresentedWidthNative(long j12, int i12);

    private native void setThemes(long j12, List<String> list);

    private native void setVideoClipDurationNative(long j12, float f12);

    private native void setWorstAssetsExcludeRatioNative(long j12, float f12);

    private native void suppressRandomness(long j12, boolean z12);

    public SmartEditTask build() {
        return new SmartEditTask(this.mDecoder, this.mFiles, this.mCover, this.mMemoryConfig, this.mTotalProgressCallback, this.mVoiceDetectorCallback, this.mFlashImageCallback, this);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        long j12 = this.mMemoryConfig;
        if (j12 != 0) {
            releaseConfig(j12);
        }
    }

    public SmartEditTaskBuilder setAlbumType(AlbumType albumType) {
        setAlbumType(this.mMemoryConfig, albumType);
        return this;
    }

    public SmartEditTaskBuilder setAnalysisDimLimit(float f12) {
        setAnalysisDimLimitNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setAnalysisDurationLimit(float f12) {
        setAnalysisDurationLimitNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setCacheFolder(String str) {
        if (str != null) {
            setCacheFolder(this.mMemoryConfig, str);
        }
        return this;
    }

    public SmartEditTaskBuilder setCoverAsset(MediaAsset mediaAsset) {
        this.mCover = mediaAsset;
        return this;
    }

    public SmartEditTaskBuilder setDecoder(Decoder decoder) {
        this.mDecoder = decoder;
        return this;
    }

    public SmartEditTaskBuilder setDedupThreshold(float f12) {
        setDedupThresholdNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setFiles(List<? extends MediaAsset> list) {
        this.mFiles = list;
        return this;
    }

    public SmartEditTaskBuilder setFlashImageCallback(FlashImageCallback flashImageCallback) {
        this.mFlashImageCallback = flashImageCallback;
        return this;
    }

    public SmartEditTaskBuilder setImageClipDuration(float f12) {
        setImageClipDurationNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setMaxAnalysisImageNumber(int i12) {
        setMaxAnalysisImageNumberNative(this.mMemoryConfig, i12);
        return this;
    }

    public SmartEditTaskBuilder setMaxAnalysisVideoNumber(int i12) {
        setMaxAnalysisVideoNumberNative(this.mMemoryConfig, i12);
        return this;
    }

    public SmartEditTaskBuilder setMaxPresentedVideoNumber(int i12) {
        setMaxPresentedVideoNumberNative(this.mMemoryConfig, i12);
        return this;
    }

    public SmartEditTaskBuilder setMaxTotalDuration(float f12) {
        setMaxTotalDurationNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setMinAssetNumber(int i12) {
        setMinAssetNumberNative(this.mMemoryConfig, i12);
        return this;
    }

    public SmartEditTaskBuilder setMinFlashImageInterval(float f12) {
        setMinFlashImageInterval(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setMinImageDimRequired(float f12) {
        setMinImageDimRequiredNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setMinVideoDimRequired(float f12) {
        setMinVideoDimRequiredNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setPresentedHeight(int i12) {
        setPresentedHeightNative(this.mMemoryConfig, i12);
        return this;
    }

    public SmartEditTaskBuilder setPresentedWidth(int i12) {
        setPresentedWidthNative(this.mMemoryConfig, i12);
        return this;
    }

    public SmartEditTaskBuilder setThemes(List<String> list) {
        if (list != null) {
            setThemes(this.mMemoryConfig, list);
        }
        return this;
    }

    public SmartEditTaskBuilder setTotalProgressCallback(ProgressCallback progressCallback) {
        this.mTotalProgressCallback = progressCallback;
        return this;
    }

    public SmartEditTaskBuilder setVideoClipDuration(float f12) {
        setVideoClipDurationNative(this.mMemoryConfig, f12);
        return this;
    }

    public SmartEditTaskBuilder setVoiceDetectorCallback(VoiceDetectorCallback voiceDetectorCallback) {
        this.mVoiceDetectorCallback = voiceDetectorCallback;
        return this;
    }

    public SmartEditTaskBuilder setWorstAssetsExcludeRatio(float f12) {
        setWorstAssetsExcludeRatioNative(this.mMemoryConfig, f12);
        return this;
    }

    void signalConfigReleased() {
        this.mMemoryConfig = 0L;
    }

    public SmartEditTaskBuilder suppressRandomness(boolean z12) {
        suppressRandomness(this.mMemoryConfig, z12);
        return this;
    }

    public SmartEditTaskBuilder useMusic(String str) {
        if (str != null && !str.isEmpty()) {
            setDesignatedMusic(this.mMemoryConfig, str);
        }
        return this;
    }

    public SmartEditTaskBuilder useTheme(String str) {
        if (str != null && !str.isEmpty()) {
            setDesignatedTheme(this.mMemoryConfig, str);
        }
        return this;
    }
}
