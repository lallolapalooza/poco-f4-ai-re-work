package com.kwai.video.westeros.v2.ycnn;

import com.google.protobuf.InvalidProtocolBufferException;
import com.kwai.video.westeros.WesterosPlugin;
import com.kwai.video.westeros.models.SalientData;
import com.kwai.video.westeros.models.YcnnFaceAttributeResult;
import com.yxcorp.bugly.CrashReporter;

/* JADX INFO: loaded from: classes13.dex */
public class YcnnPlugin extends WesterosPlugin {
    private uploadStatsCallBack mUploadStatsCallBack;
    private OnSalientDataListender salientDataListender;
    private OnYcnnFaceAttributeDetectionListener ycnnFaceAttrDetectionListener;
    private OnYcnnGeneralRecogDetectionListener ycnnGeneralRecogDetectionListener;
    private OnYcnnModelLoadFinishListener ycnnModelLoadFinishListener;

    public interface OnSalientDataListender {
        void onSalientData(SalientData salientData);
    }

    public interface OnYcnnFaceAttributeDetectionListener {
        void OnYcnnFaceAttributeDetectionResult(YcnnFaceAttributeResult ycnnFaceAttributeResult);
    }

    public interface OnYcnnGeneralRecogDetectionListener {
        void OnYcnnGeneralRecogDetectionResult(String str);
    }

    public interface OnYcnnModelLoadFinishListener {
        void OnYcnnModelLoadFinish(String str);
    }

    public interface uploadStatsCallBack {
        void uploadStats(String str);
    }

    static {
        YcnnSoLoader.loadNative();
    }

    private native long nativeCreateYcnnPlugin();

    private native void nativeDestroyYcnnPlugin(long j12);

    private native void nativeSetOnYcnnModelLoadFinish(long j12);

    private native void nativeSetParamInfo(String str, long j12);

    private native void nativeSetUploadStatsCallBack(long j12, String str);

    private native void nativeStartProvideFaceAttributeData(long j12);

    private native void nativeStartProvideGeneralRecogData(long j12, String str);

    private native void nativeStartProvideSalientData(long j12);

    private native void nativeStopProvideFaceAttributeData(long j12);

    private native void nativeStopProvideGeneralRecogData(long j12);

    private native void nativeStopProvideSalientData(long j12);

    private void onModelLoadFinish(String str) {
        OnYcnnModelLoadFinishListener onYcnnModelLoadFinishListener = this.ycnnModelLoadFinishListener;
        if (onYcnnModelLoadFinishListener != null) {
            onYcnnModelLoadFinishListener.OnYcnnModelLoadFinish(str);
        }
    }

    private void onSalientData(byte[] bArr) {
        SalientData from;
        if (this.salientDataListender != null) {
            if (bArr != null) {
                try {
                    from = SalientData.parseFrom(bArr);
                } catch (Exception e12) {
                    CrashReporter.logCheckedException(e12, "YcnnPlugin.class", "onSalientData", 115);
                    e12.printStackTrace();
                    return;
                }
            } else {
                from = null;
            }
            this.salientDataListender.onSalientData(from);
        }
    }

    private void onYcnnFaceAttributeResult(byte[] bArr) {
        YcnnFaceAttributeResult from;
        if (this.ycnnFaceAttrDetectionListener != null) {
            if (bArr != null) {
                try {
                    from = YcnnFaceAttributeResult.parseFrom(bArr);
                } catch (InvalidProtocolBufferException e12) {
                    CrashReporter.logCheckedException(e12, "YcnnPlugin.class", "onYcnnFaceAttributeResult", -119);
                    e12.printStackTrace();
                    return;
                }
            } else {
                from = null;
            }
            this.ycnnFaceAttrDetectionListener.OnYcnnFaceAttributeDetectionResult(from);
        }
    }

    private void onYcnnGeneralRecogResult(String str) {
        OnYcnnGeneralRecogDetectionListener onYcnnGeneralRecogDetectionListener = this.ycnnGeneralRecogDetectionListener;
        if (onYcnnGeneralRecogDetectionListener != null) {
            try {
                onYcnnGeneralRecogDetectionListener.OnYcnnGeneralRecogDetectionResult(str);
            } catch (Exception e12) {
                CrashReporter.logCheckedException(e12, "YcnnPlugin.class", "onYcnnGeneralRecogResult", 125);
                e12.printStackTrace();
            }
        }
    }

    @Override // com.kwai.video.westeros.WesterosPlugin
    protected long createNativePlugin() {
        return nativeCreateYcnnPlugin();
    }

    @Override // com.kwai.video.westeros.WesterosPlugin
    public void release() {
        super.release();
    }

    @Override // com.kwai.video.westeros.WesterosPlugin
    protected void releaseNativePlugin(long j12) {
        nativeDestroyYcnnPlugin(j12);
    }

    public void setAttrLocalPath(String str) {
        nativeSetParamInfo("{\"attr_local_path\":\"" + str + "\"}", this.nativePlugin);
    }

    public void setDevicePortraitInfo(String str) {
        nativeSetParamInfo("{\"device_portrait_info\":" + str + "}", this.nativePlugin);
    }

    public void setOnSalientDataListender(OnSalientDataListender onSalientDataListender) {
        this.salientDataListender = onSalientDataListender;
    }

    public void setOnYcnnFaceAttributeDetectionListener(OnYcnnFaceAttributeDetectionListener onYcnnFaceAttributeDetectionListener) {
        this.ycnnFaceAttrDetectionListener = onYcnnFaceAttributeDetectionListener;
    }

    public void setOnYcnnGeneralRecogDetectionListener(OnYcnnGeneralRecogDetectionListener onYcnnGeneralRecogDetectionListener) {
        this.ycnnGeneralRecogDetectionListener = onYcnnGeneralRecogDetectionListener;
    }

    public void setOnYcnnModelLoadFinishListener(OnYcnnModelLoadFinishListener onYcnnModelLoadFinishListener) {
        this.ycnnModelLoadFinishListener = onYcnnModelLoadFinishListener;
        nativeSetOnYcnnModelLoadFinish(this.nativePlugin);
    }

    public void setSaveLogInfoPath(String str, int i12) {
        nativeSetParamInfo("{\"save_loginfo_path\":\"" + str + "\",\"save_loginfo_mode\":" + i12 + "}", this.nativePlugin);
    }

    public void setUploadStatsCallBack(uploadStatsCallBack uploadstatscallback, String str) {
        this.mUploadStatsCallBack = uploadstatscallback;
        nativeSetUploadStatsCallBack(this.nativePlugin, str);
    }

    public void setVisionPortraitInfo(String str, String str2) {
    }

    public void startProvideFaceAttribute() {
        nativeStartProvideFaceAttributeData(this.nativePlugin);
    }

    public void startProvideGeneralRecog(String str) {
        nativeStartProvideGeneralRecogData(this.nativePlugin, str);
    }

    public void startProvideSalientData() {
        nativeStartProvideSalientData(this.nativePlugin);
    }

    public void stopProvideFaceAttribute() {
        nativeStopProvideFaceAttributeData(this.nativePlugin);
    }

    public void stopProvideGeneralRecog() {
        nativeStopProvideGeneralRecogData(this.nativePlugin);
    }

    public void stopProvideSalientData() {
        nativeStopProvideSalientData(this.nativePlugin);
    }
}
