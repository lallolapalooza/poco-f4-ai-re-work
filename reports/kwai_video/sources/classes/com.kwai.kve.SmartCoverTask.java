package com.kwai.kve;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import com.yxcorp.bugly.CrashReporter;

/* JADX INFO: loaded from: classes16.dex */
public class SmartCoverTask {
    private static final String TAG = "kve::SmartCoverTaskJava";
    private long mAnalyzer;
    private BitmapProxy mBitmapProvider = new BitmapProxy();
    private FaceProxy mFaceProxy;

    SmartCoverTask() {
        FaceProxy faceProxy = new FaceProxy();
        this.mFaceProxy = faceProxy;
        this.mAnalyzer = createHighlightAnalyzer(this.mBitmapProvider, faceProxy);
    }

    private native long createHighlightAnalyzer(ThumbnailProvider thumbnailProvider, FaceProxy faceProxy);

    private native void releaseHighlightAnalyzer(long j12);

    private native MediaAnalyzeResult runHighlightAnalyzer(long j12, String str);

    public SmartCoverResult analyze(Bitmap bitmap, FaceData[] faceDataArr) {
        if (this.mAnalyzer == 0) {
            return new SmartCoverResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_HANDLE, "The analyzer is not created."));
        }
        if (bitmap == null) {
            return new SmartCoverResult(new ErrorInfo(ErrorInfo.ErrorCode.INVALID_MEDIAASSET, "The bitmap is null."));
        }
        if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            try {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                new Canvas(bitmapCreateBitmap).drawBitmap(bitmap, new Matrix(), null);
                bitmap = bitmapCreateBitmap;
            } catch (OutOfMemoryError e12) {
                CrashReporter.logCheckedException(e12, "SmartCoverTask.class", "analyze", 40);
                LogUtil.e(TAG, "Failed to create ARGB_8888 bitmap, OOM!");
                return new SmartCoverResult(new ErrorInfo(ErrorInfo.ErrorCode.DECODER_MEM_ERROR, "Failed to create ARGB_8888 bitmap, OOM!"));
            }
        }
        this.mBitmapProvider.setBitmap(bitmap);
        this.mFaceProxy.setFaces(faceDataArr);
        MediaAnalyzeResult mediaAnalyzeResultRunHighlightAnalyzer = runHighlightAnalyzer(this.mAnalyzer, "Empty");
        this.mBitmapProvider.setBitmap(null);
        this.mFaceProxy.setFaces(null);
        return mediaAnalyzeResultRunHighlightAnalyzer.getErrorInfo().isOK() ? new SmartCoverResult(new ErrorInfo(), mediaAnalyzeResultRunHighlightAnalyzer.getIntegratedScore()) : new SmartCoverResult(mediaAnalyzeResultRunHighlightAnalyzer.getErrorInfo());
    }

    protected void finalize() throws Throwable {
        super.finalize();
        LogUtil.i(TAG, "SmartCoverTask to be garbage collected.");
        release();
    }

    public void release() {
        this.mBitmapProvider = null;
        this.mFaceProxy = null;
        long j12 = this.mAnalyzer;
        if (j12 != 0) {
            releaseHighlightAnalyzer(j12);
            LogUtil.i(TAG, "SmartCoverTask is released.");
            this.mAnalyzer = 0L;
        }
    }
}
