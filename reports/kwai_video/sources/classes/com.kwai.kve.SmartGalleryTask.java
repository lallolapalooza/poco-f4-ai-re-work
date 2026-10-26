package com.kwai.kve;

import android.graphics.Rect;
import com.kwai.yoda.model.LifecycleEvent;
import com.yxcorp.bugly.CrashReporter;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes16.dex */
public class SmartGalleryTask {
    private static final String TAG = "kve::SmartGalleryTaskJava";
    private long mAnalyzer;
    private List<? extends MediaAsset> mFiles;
    private SmartGalleryResult mResult;
    private ProgressCallback mTotalProgressCallback;
    private final Object mLock = new Object();
    private boolean mIsRunningTask = false;
    private boolean mIsStopSignalled = false;

    SmartGalleryTask(Decoder decoder, ThumbnailProvider thumbnailProvider, List<? extends MediaAsset> list) {
        this.mFiles = list;
        if (decoder == null) {
            this.mResult = new SmartGalleryResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_DECODER, "The decoder is null"));
            return;
        }
        if (thumbnailProvider == null) {
            this.mResult = new SmartGalleryResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_THUMBNAIL_PROVIDER, "The thumbnailProvider is null"));
            return;
        }
        if (list == null || list.isEmpty()) {
            this.mResult = new SmartGalleryResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_MEDIAASSET, "Invalid mediaAssets"));
            return;
        }
        long jCreateHighlightAnalyzer = createHighlightAnalyzer(decoder, thumbnailProvider);
        this.mAnalyzer = jCreateHighlightAnalyzer;
        if (jCreateHighlightAnalyzer == 0) {
            LogUtil.e(TAG, "Error while constructing SmartGalleryTask, the underlying cpp task class is not constructed normally.");
            this.mResult = new SmartGalleryResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_HANDLE, "The underlying cpp task class is not constructed normally"));
        }
    }

    private native long createHighlightAnalyzer(Decoder decoder, ThumbnailProvider thumbnailProvider);

    private native void releaseHighlightAnalyzer(long j12);

    private native void resetHighlightAnalyzerStopFlag(long j12);

    private void resetStatus() {
        this.mIsRunningTask = false;
        this.mIsStopSignalled = false;
        resetHighlightAnalyzerStopFlag(this.mAnalyzer);
    }

    private native MediaAnalyzeResult runHighlightAnalyzer(long j12, String str);

    private void runTotalProgressCallback(int i12, int i13) {
        float f12 = i13 != 0 ? i12 / i13 : 1.0f;
        ProgressCallback progressCallback = this.mTotalProgressCallback;
        if (progressCallback != null) {
            progressCallback.update(f12, "Complete file (" + i12 + "/" + i13 + ").");
        }
    }

    private native void stopHighlightAnalyzer(long j12);

    protected void finalize() throws Throwable {
        super.finalize();
        LogUtil.i(TAG, "SmartGalleryTask to be garbage collected.");
        release();
    }

    public boolean isRunning() {
        boolean z12;
        synchronized (this.mLock) {
            try {
                z12 = this.mIsRunningTask;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "SmartGalleryTask.class", "isRunning", 125);
                throw th2;
            }
        }
        return z12;
    }

    public void release() {
        synchronized (this.mLock) {
            try {
                long j12 = this.mAnalyzer;
                if (j12 != 0) {
                    releaseHighlightAnalyzer(j12);
                    this.mAnalyzer = 0L;
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "SmartGalleryTask.class", "release", -1);
                throw th2;
            }
        }
    }

    public SmartGalleryResult run() {
        SmartGalleryResult smartGalleryResult;
        synchronized (this.mLock) {
            try {
                MediaAsset mediaAsset = null;
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
                LogUtil.i(TAG, "SmartGalleryTask.run()");
                ErrorInfo errorInfo = new ErrorInfo();
                HashMap map = new HashMap();
                int size = this.mFiles.size();
                runTotalProgressCallback(0, size);
                int i12 = 0;
                while (i12 < size) {
                    MediaAsset mediaAsset2 = this.mFiles.get(i12);
                    LogUtil.i(TAG, mediaAsset2.getFileName() + " submitted for scoring");
                    map.put(mediaAsset2, new ThumbnailAnalyzeResult(runHighlightAnalyzer(this.mAnalyzer, mediaAsset2.getFileName()).getIntegratedScore()));
                    synchronized (this.mLock) {
                        try {
                            if (this.mIsStopSignalled) {
                                resetStatus();
                                return new SmartGalleryResult(new ErrorInfo(ErrorInfo.ErrorCode.ANALYSIS_UNFINISHED, "Analysis is stopped by the user"));
                            }
                        } catch (Throwable th2) {
                            CrashReporter.logCheckedException(th2, "SmartGalleryTask.class", "run", -1);
                            throw th2;
                        }
                    }
                    i12++;
                    runTotalProgressCallback(i12, size);
                }
                float score = -1000.0f;
                for (int i13 = 0; i13 < map.size(); i13++) {
                    MediaAsset mediaAsset3 = this.mFiles.get(i13);
                    ThumbnailAnalyzeResult thumbnailAnalyzeResult = (ThumbnailAnalyzeResult) map.get(mediaAsset3);
                    LogUtil.i(TAG, mediaAsset3.getFileName() + " score ==> " + thumbnailAnalyzeResult.getScore());
                    if (thumbnailAnalyzeResult.getScore() > score) {
                        score = thumbnailAnalyzeResult.getScore();
                        mediaAsset = mediaAsset3;
                    }
                }
                synchronized (this.mLock) {
                    try {
                        resetStatus();
                        smartGalleryResult = new SmartGalleryResult(errorInfo, mediaAsset, new Rect(), map);
                    } catch (Throwable th3) {
                        CrashReporter.logCheckedException(th3, "SmartGalleryTask.class", "run", 102);
                        throw th3;
                    }
                }
                return smartGalleryResult;
            } catch (Throwable th4) {
                CrashReporter.logCheckedException(th4, "SmartGalleryTask.class", "run", -1);
                throw th4;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0016 A[Catch: all -> 0x001f, TryCatch #0 {all -> 0x001f, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x000f, B:10:0x001d, B:9:0x0016), top: B:16:0x0003 }] */
    public void stop() {
        synchronized (this.mLock) {
            try {
                if (this.mIsRunningTask) {
                    long j12 = this.mAnalyzer;
                    if (j12 != 0) {
                        stopHighlightAnalyzer(j12);
                        this.mIsStopSignalled = true;
                    } else {
                        LogUtil.e(TAG, "The task is not running yet, ignore stop signal.");
                    }
                } else {
                    LogUtil.e(TAG, "The task is not running yet, ignore stop signal.");
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "SmartGalleryTask.class", LifecycleEvent.STOP, -1);
                throw th2;
            }
        }
    }
}
