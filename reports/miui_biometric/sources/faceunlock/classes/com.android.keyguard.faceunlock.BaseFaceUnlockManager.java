package com.android.keyguard.faceunlock;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.util.Slog;
import com.miui.face.util.DeviceUtils;
import com.miui.face.util.SharedPrefUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseFaceUnlockManager implements IPreviewCallback {
    public static boolean DEBUG = true;
    protected static final int MSG_FACE_UNLOCK_OPEN_CAMERA_FAIL = 1009;
    protected static final int MSG_FACE_UNLOCK_RELEASE = 1007;
    protected static final int MSG_FACE_UNLOCK_WORK_TIMEOUT = 1008;
    public static final long POP_CAMERA_DELAY_CREATE_SESSION = 380;
    private static final long RETRY_SLEEP_TIME = 200;
    public static final String TAG = "miui.face.transplant";
    private static final int UNLOCK_RETRY_TIME = 5;
    public static long sStageInFaceUnlockTime;
    protected FaceUnlockCallback mCallback;
    protected AbstractCameraUtil mCameraUtil;
    protected Context mContext;
    protected int mFrameCount;
    protected boolean mIsDetecting;
    protected boolean mIsSuccess;
    protected boolean mIsTrigger;
    private int mRetryTime;
    protected long mStartAfterFirstFrame;
    private HandlerThread mAuthenThread = null;
    protected Handler mAuthenHandler = null;
    protected boolean mHasInit = false;
    protected boolean mHasFace = false;
    protected boolean mSkipIfNotFound = false;

    public abstract void deleteFeature();

    public Handler getAuthenHandler() {
        return this.mAuthenHandler;
    }

    public abstract int getFeatureCount();

    public abstract int getRunOutTime();

    public abstract String getVersion();

    public boolean hasInit() {
        return this.mHasInit;
    }

    public abstract void releaseFaceUnlock();

    public abstract void setRunOutTime(int i);

    private void initAuthenThread() {
        HandlerThread handlerThread = new HandlerThread("auth_worker");
        this.mAuthenThread = handlerThread;
        handlerThread.start();
        this.mAuthenHandler = new Handler(this.mAuthenThread.getLooper()) { // from class: com.android.keyguard.faceunlock.BaseFaceUnlockManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1007:
                        BaseFaceUnlockManager.this.releaseFaceUnlock();
                        break;
                    case 1008:
                    case BaseFaceUnlockManager.MSG_FACE_UNLOCK_OPEN_CAMERA_FAIL /* 1009 */:
                        if (!BaseFaceUnlockManager.this.mIsSuccess && BaseFaceUnlockManager.this.mIsTrigger) {
                            BaseFaceUnlockManager.this.stopDetect();
                            BaseFaceUnlockManager.this.mCallback.onFaceAuthFailed(BaseFaceUnlockManager.this.mHasFace);
                            break;
                        }
                        break;
                }
            }
        };
    }

    public void runOnFaceUnlockWorkerThread(Runnable runnable) {
        HandlerThread handlerThread = this.mAuthenThread;
        if (handlerThread == null || this.mAuthenHandler == null) {
            return;
        }
        if (handlerThread.getThreadId() == Process.myTid()) {
            runnable.run();
        } else {
            this.mAuthenHandler.post(runnable);
        }
    }

    public void initFaceUnlockUtil() {
        initAuthenThread();
        this.mCameraUtil = new Camera2Util(this.mContext, this.mAuthenHandler);
    }

    public void startFaceUnlock(FaceUnlockCallback faceUnlockCallback) {
        this.mCallback = faceUnlockCallback;
        this.mRetryTime = 5;
        if (this.mIsTrigger || getFeatureCount() <= 0 || !this.mHasInit) {
            return;
        }
        this.mIsTrigger = true;
        this.mIsSuccess = false;
        this.mIsDetecting = false;
        this.mHasFace = false;
        this.mSkipIfNotFound = false;
        this.mStartAfterFirstFrame = 0L;
        this.mFrameCount = 0;
        this.mAuthenHandler.removeMessages(1007);
        Slog.i("miui.face.transplant", "sdk version=" + getVersion());
        sStageInFaceUnlockTime = System.currentTimeMillis();
        this.mCallback.onFaceStart();
        this.mCameraUtil.setCameraCallback(this);
        this.mCameraUtil.openCamera(0);
    }

    public void stopFaceUnlock() {
        if (this.mIsTrigger) {
            this.mIsTrigger = false;
            stopDetect();
        }
    }

    public void release(boolean z) {
        if (this.mHasInit) {
            if (!z) {
                releaseFaceUnlock();
            } else {
                if (this.mAuthenHandler.hasMessages(1007)) {
                    return;
                }
                this.mAuthenHandler.sendEmptyMessageDelayed(1007, 60000L);
            }
        }
    }

    public void removeReleaseMessage() {
        this.mAuthenHandler.removeMessages(1007);
    }

    protected void stopDetect() {
        this.mAuthenHandler.removeMessages(1008);
        this.mIsTrigger = false;
        if (!DeviceUtils.isVirtualLightDevice()) {
            this.mCameraUtil.closeCameraImmediatelyIfCameraThread();
        } else if (this.mFrameCount >= 30 || System.currentTimeMillis() - this.mStartAfterFirstFrame >= 1000) {
            Log.v("miui.face.transplant", "frameCount = " + this.mFrameCount + ", afterFirstFrameTime = " + (System.currentTimeMillis() - this.mStartAfterFirstFrame));
            this.mCameraUtil.closeCameraImmediatelyIfCameraThread();
        } else {
            Log.v("miui.face.transplant", "delay close camera");
        }
    }

    @Override // com.android.keyguard.faceunlock.IPreviewCallback
    public void openCameraSucced(String str, int i) {
        if (!this.mIsTrigger) {
            stopDetect();
            return;
        }
        if (i == 0) {
            if (DEBUG) {
                Slog.i("miui.face.transplant", "miui.face.timeline, open camera succeed time=" + (System.currentTimeMillis() - sStageInFaceUnlockTime));
                sStageInFaceUnlockTime = System.currentTimeMillis();
            }
            this.mCameraUtil.startPreview(null);
            this.mAuthenHandler.sendEmptyMessageDelayed(1008, getRunOutTime());
            return;
        }
        int i2 = this.mRetryTime;
        this.mRetryTime = i2 - 1;
        if (i2 <= 0) {
            this.mAuthenHandler.sendEmptyMessage(MSG_FACE_UNLOCK_OPEN_CAMERA_FAIL);
            Slog.w("miui.face.transplant", "open Camera failed");
        } else if (i == 1 || i == 2) {
            Slog.i("miui.face.transplant", "Open camera failed, try again[" + this.mRetryTime + "]");
            this.mAuthenHandler.postDelayed(new Runnable() { // from class: com.android.keyguard.faceunlock.BaseFaceUnlockManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m13x5c16e221();
                }
            }, ((long) (5 - this.mRetryTime)) * RETRY_SLEEP_TIME);
        } else {
            this.mAuthenHandler.sendEmptyMessage(MSG_FACE_UNLOCK_OPEN_CAMERA_FAIL);
            Slog.w("miui.face.transplant", "open Camera failed");
        }
    }

    /* JADX INFO: renamed from: lambda$openCameraSucced$0$com-android-keyguard-faceunlock-BaseFaceUnlockManager, reason: not valid java name */
    /* synthetic */ void m13x5c16e221() {
        this.mCameraUtil.openCamera(0);
    }

    protected void handlePassed() {
        this.mIsSuccess = true;
        this.mCallback.onFaceAuthenticated();
        stopDetect();
    }

    public boolean deleteFeature(int i) {
        deleteFeature();
        return true;
    }

    public List<Integer> getEnrolledFaceIds() {
        return new SharedPrefUtil(this.mContext).getEnrolledFaceIds();
    }
}
