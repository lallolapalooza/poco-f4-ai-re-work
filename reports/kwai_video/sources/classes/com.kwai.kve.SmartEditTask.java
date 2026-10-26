package com.kwai.kve;

import aegon.chrome.net.NetError;
import com.kwai.yoda.model.LifecycleEvent;
import com.yxcorp.bugly.CrashReporter;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes16.dex */
public class SmartEditTask {
    private static final String TAG = "kve::SmartEditTaskJava";
    private List<? extends MediaAsset> mFiles;
    private WeakReference<FlashImageCallback> mFlashImageCallbackRef;
    private SmartEditResult mResult;
    private WeakReference<ProgressCallback> mTotalProgressCallbackRef;
    private WeakReference<VoiceDetectorCallback> mVoiceDetectorCallbackRef;
    private long mWorker;
    private final Object mLock = new Object();
    private boolean mIsRunningTask = false;
    private boolean mIsStopSignalled = false;

    SmartEditTask(Decoder decoder, List<? extends MediaAsset> list, MediaAsset mediaAsset, long j12, ProgressCallback progressCallback, VoiceDetectorCallback voiceDetectorCallback, FlashImageCallback flashImageCallback, SmartEditTaskBuilder smartEditTaskBuilder) {
        if (progressCallback != null) {
            this.mTotalProgressCallbackRef = new WeakReference<>(progressCallback);
        }
        if (voiceDetectorCallback != null) {
            this.mVoiceDetectorCallbackRef = new WeakReference<>(voiceDetectorCallback);
        }
        if (flashImageCallback != null) {
            this.mFlashImageCallbackRef = new WeakReference<>(flashImageCallback);
        }
        this.mFiles = list;
        if (decoder == null) {
            LogUtil.e(TAG, "Error while constructing SmartEditTask, the decoder is null.");
            this.mResult = new SmartEditResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_DECODER, "The decoder is null."));
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.e(TAG, "Error while constructing SmartEditTask, invalid mediaAssets.");
            this.mResult = new SmartEditResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_MEDIAASSET, "Invalid mediaAssets."));
            return;
        }
        this.mWorker = createSmartEditTask(j12, decoder, list, mediaAsset, this.mTotalProgressCallbackRef, this.mVoiceDetectorCallbackRef, this.mFlashImageCallbackRef, new WeakReference<>(this));
        smartEditTaskBuilder.signalConfigReleased();
        if (this.mWorker == 0) {
            LogUtil.e(TAG, "Error while constructing SmartEditTask, the underlying cpp task class is not constructed normally.");
            this.mResult = new SmartEditResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_HANDLE, "The underlying cpp task class is not constructed normally."));
        }
    }

    private native long createSmartEditTask(long j12, Decoder decoder, List<? extends MediaAsset> list, MediaAsset mediaAsset, WeakReference<ProgressCallback> weakReference, WeakReference<VoiceDetectorCallback> weakReference2, WeakReference<FlashImageCallback> weakReference3, WeakReference<SmartEditTask> weakReference4);

    private MediaAsset getAsset(String str) {
        if (this.mFiles == null) {
            return null;
        }
        for (int i12 = 0; i12 < this.mFiles.size(); i12++) {
            if (this.mFiles.get(i12).getFileName().equals(str)) {
                return this.mFiles.get(i12);
            }
        }
        return null;
    }

    private boolean isStopSignalled() {
        boolean z12;
        synchronized (this.mLock) {
            try {
                z12 = this.mIsStopSignalled;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "SmartEditTask.class", "isStopSignalled", -120);
                throw th2;
            }
        }
        return z12;
    }

    private void notifyGeneralProgress(float f12, String str) {
        ProgressCallback progressCallback;
        WeakReference<ProgressCallback> weakReference = this.mTotalProgressCallbackRef;
        if (weakReference == null || (progressCallback = weakReference.get()) == null) {
            return;
        }
        progressCallback.update(f12, str);
    }

    private native void releaseWorker(long j12);

    private void resetStatus() {
        this.mIsRunningTask = false;
        this.mIsStopSignalled = false;
        resetWorkerStopFlag(this.mWorker);
    }

    private native void resetWorkerStopFlag(long j12);

    private native SmartEditResult runWorker(long j12, Map<String, MediaAsset> map);

    private native void stopWorker(long j12);

    protected void finalize() throws Throwable {
        super.finalize();
        LogUtil.i(TAG, "SmartEditTask to be garbage collected.");
        release();
    }

    public native float getAnalysisDimLimit();

    public native float getAnalysisDurationLimit();

    public native float getImageClipDuration();

    public native int getMaxAnalysisImageNumber();

    public native int getMaxAnalysisVideoNumber();

    public native int getMaxPresentedVideoNumber();

    public native float getMaxTotalDuration();

    public native int getMinAssetNumber();

    public native float getMinImageDimRequired();

    public native float getMinVideoDimRequired();

    public native int getPresentedHeight();

    public native int getPresentedWidth();

    public native float getVideoClipDuration();

    public boolean isRunning() {
        boolean z12;
        synchronized (this.mLock) {
            try {
                z12 = this.mIsRunningTask;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "SmartEditTask.class", "isRunning", NetError.ERR_SSL_BAD_RECORD_MAC_ALERT);
                throw th2;
            }
        }
        return z12;
    }

    public void release() {
        synchronized (this.mLock) {
            try {
                long j12 = this.mWorker;
                if (j12 != 0) {
                    releaseWorker(j12);
                    this.mWorker = 0L;
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "SmartEditTask.class", "release", -1);
                throw th2;
            }
        }
    }

    public SmartEditResult run() {
        SmartEditResult smartEditResult;
        synchronized (this.mLock) {
            try {
                if (this.mIsRunningTask) {
                    LogUtil.e(TAG, "Can't execute run(), please stop the existing task first.");
                    return null;
                }
                this.mIsRunningTask = true;
                this.mIsStopSignalled = false;
                if (this.mResult != null) {
                    resetStatus();
                    return this.mResult;
                }
                if (getMinAssetNumber() <= 0) {
                    resetStatus();
                    return new SmartEditResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_PARAMETER, "Task parameter is not valid, please check."));
                }
                LogUtil.i(TAG, "SmartEditTask.run()");
                HashMap map = new HashMap();
                int size = this.mFiles.size();
                String str = "";
                for (int i12 = 0; i12 < size; i12++) {
                    MediaAsset mediaAsset = this.mFiles.get(i12);
                    String fileName = mediaAsset.getFileName();
                    String[] strArrSplit = fileName.split("/");
                    if (strArrSplit.length > 0) {
                        str = str + strArrSplit[strArrSplit.length - 1] + "; ";
                    }
                    map.put(fileName, mediaAsset);
                }
                LogUtil.d(TAG, "SmartEditTask candidate assets: " + str);
                notifyGeneralProgress(0.0f, "Analysis begins.");
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.mResult = runWorker(this.mWorker, map);
                LogUtil.i(TAG, "Total task time: " + (System.currentTimeMillis() - jCurrentTimeMillis) + "(ms)");
                synchronized (this.mLock) {
                    try {
                        resetStatus();
                        smartEditResult = this.mResult;
                    } catch (Throwable th2) {
                        CrashReporter.logCheckedException(th2, "SmartEditTask.class", "run", 107);
                        throw th2;
                    }
                }
                return smartEditResult;
            } catch (Throwable th3) {
                CrashReporter.logCheckedException(th3, "SmartEditTask.class", "run", -1);
                throw th3;
            }
        }
    }

    public native void setCacheFolder(String str);

    public native void setThemes(List<String> list);

    /* JADX WARN: Code duplicated, block: B:9:0x0016 A[Catch: all -> 0x001f, TryCatch #0 {all -> 0x001f, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x000f, B:10:0x001d, B:9:0x0016), top: B:16:0x0003 }] */
    public void stop() {
        synchronized (this.mLock) {
            try {
                if (this.mIsRunningTask) {
                    long j12 = this.mWorker;
                    if (j12 != 0) {
                        stopWorker(j12);
                        this.mIsStopSignalled = true;
                    } else {
                        LogUtil.e(TAG, "The task is not running yet, ignore stop signal.");
                    }
                } else {
                    LogUtil.e(TAG, "The task is not running yet, ignore stop signal.");
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "SmartEditTask.class", LifecycleEvent.STOP, -1);
                throw th2;
            }
        }
    }
}
