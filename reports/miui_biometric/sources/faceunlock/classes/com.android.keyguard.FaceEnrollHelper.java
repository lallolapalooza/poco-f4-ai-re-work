package com.android.keyguard;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.util.Slog;
import android.view.Surface;
import com.android.keyguard.faceunlock.AbstractCameraUtil;
import com.android.keyguard.faceunlock.BaseFaceEnrollHelper;
import com.android.keyguard.faceunlock.Camera2Util;
import com.android.keyguard.faceunlock.FaceUnlockManager;
import com.android.keyguard.faceunlock.IPreviewCallback;
import com.miui.face.MiuiFaceService;
import com.miui.face.util.DeviceUtils;
import com.ts.faceauth.FaceAuthManager;
import com.ts.faceauth.bean.EnrollResult;

/* JADX INFO: loaded from: classes.dex */
public class FaceEnrollHelper extends BaseFaceEnrollHelper implements IPreviewCallback {
    public static long sStageInFaceUnlockTime;
    private final AbstractCameraUtil mCameraUtil;
    private final Context mContext;
    private Rect mDetectRect;
    private Rect mEnrollRect;
    private int mFaceInputStep;
    private final FaceUnlockManager mFaceUnlockManager;
    private boolean mIsStopEnroll;
    private int mPreviewCount;
    private final MiuiFaceService mService;
    private Surface mSurface;
    private int mTimeout = 30000;
    private int mEnrollStep = 1;
    private boolean mCanNextFaceInputStep = true;
    private final Runnable mDelayedHide = new Runnable() { // from class: com.android.keyguard.FaceEnrollHelper.1
        @Override // java.lang.Runnable
        public void run() {
            FaceEnrollHelper.this.mCameraUtil.closeCameraImmediately();
            FaceEnrollHelper.this.mFaceUnlockManager.release(false);
            FaceEnrollHelper.this.mService.getWorkerHandler().obtainMessage(MiuiFaceService.MSG_FACE_UNLOCK_WORK_TIMEOUT, 3, 0).sendToTarget();
        }
    };
    private final Runnable mStopFromBp = new Runnable() { // from class: com.android.keyguard.FaceEnrollHelper.2
        @Override // java.lang.Runnable
        public void run() {
            Slog.d("miui.face.FaceService", "FaceEnrollHelper mStopFromBp run ......");
            FaceEnrollHelper.this.mIsStopEnroll = true;
            FaceEnrollHelper.this.mPreviewCount = 0;
            FaceEnrollHelper.this.mFaceUnlockManager.getAuthenHandler().removeCallbacks(FaceEnrollHelper.this.mDelayedHide);
            FaceEnrollHelper.this.mCameraUtil.closeCameraImmediately();
            if (FaceEnrollHelper.this.mFaceUnlockManager.getFeatureCount() == 0) {
                FaceEnrollHelper.this.mFaceUnlockManager.release(false);
            } else {
                FaceEnrollHelper.this.mFaceAuthManager.reset();
            }
            FaceEnrollHelper.this.mService.getWorkerHandler().obtainMessage(4, 5, 0).sendToTarget();
        }
    };
    private final Runnable mStartFromBp = new Runnable() { // from class: com.android.keyguard.FaceEnrollHelper.3
        @Override // java.lang.Runnable
        public void run() {
            Slog.d("miui.face.FaceService", "FaceEnrollHelper mStartFromBp run ......");
            Slog.i("miui.face.FaceService", "sdk version=" + FaceEnrollHelper.this.mFaceUnlockManager.getVersion());
            FaceEnrollHelper.this.mIsStopEnroll = false;
            FaceEnrollHelper.this.mCameraUtil.openCamera(0);
        }
    };
    private final Runnable mReleaseAfterEnrolled = new Runnable() { // from class: com.android.keyguard.FaceEnrollHelper.4
        @Override // java.lang.Runnable
        public void run() {
            Slog.d("miui.face.FaceService", "release memory after enrolled... ...");
            FaceEnrollHelper.this.mFaceUnlockManager.release(!DeviceUtils.isLiteMode());
        }
    };
    private final FaceAuthManager mFaceAuthManager = FaceAuthManager.getInstance();

    public FaceEnrollHelper(MiuiFaceService miuiFaceService, Context context) {
        this.mService = miuiFaceService;
        this.mContext = context;
        FaceUnlockManager faceUnlockManager = FaceUnlockManager.getInstance(context);
        this.mFaceUnlockManager = faceUnlockManager;
        this.mPreviewCount = 0;
        this.mCameraUtil = new Camera2Util(context, faceUnlockManager.getAuthenHandler());
    }

    public void start(Rect rect) {
        setDetectArea(rect);
        this.mFaceAuthManager.setEnrollImageNumberForFace(this.mEnrollStep);
        this.mFaceUnlockManager.getAuthenHandler().postDelayed(this.mDelayedHide, this.mTimeout);
        this.mCanNextFaceInputStep = true;
        sStageInFaceUnlockTime = System.currentTimeMillis();
    }

    private void setDetectArea(Rect rect) {
        this.mFaceAuthManager.setDetectArea(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void stopFromBp() {
        this.mFaceUnlockManager.getAuthenHandler().post(this.mStopFromBp);
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceEnrollHelper
    public void resetFaceEnroll() {
        this.mCanNextFaceInputStep = true;
        this.mFaceInputStep = 0;
        this.mFaceUnlockManager.runOnFaceUnlockWorkerThread(new Runnable() { // from class: com.android.keyguard.FaceEnrollHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m12lambda$resetFaceEnroll$0$comandroidkeyguardFaceEnrollHelper();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$resetFaceEnroll$0$com-android-keyguard-FaceEnrollHelper, reason: not valid java name */
    /* synthetic */ void m12lambda$resetFaceEnroll$0$comandroidkeyguardFaceEnrollHelper() {
        this.mFaceAuthManager.reset();
        setDetectArea(this.mDetectRect);
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceEnrollHelper
    public void startNextFaceEnrollStep() {
        this.mCanNextFaceInputStep = true;
        int i = this.mFaceInputStep + 1;
        this.mFaceInputStep = i;
        if (i == 1) {
            setDetectArea(this.mEnrollRect);
        }
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceEnrollHelper
    public void enroll(Surface surface, Rect rect, int i) {
        enroll(surface, null, rect, 1, i);
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceEnrollHelper
    public void enroll(Surface surface, Rect rect, Rect rect2, int i, int i2) {
        Slog.d("miui.face.FaceService", "enroll");
        if (i > 0 && i < 6) {
            this.mEnrollStep = i;
        }
        if (i2 > 2000 && i2 < 300000) {
            this.mTimeout = i2;
        }
        this.mSurface = surface;
        if (rect == null) {
            this.mDetectRect = rect2;
        } else {
            this.mDetectRect = rect;
        }
        this.mEnrollRect = rect2;
        this.mCameraUtil.setCameraCallback(this);
        this.mFaceUnlockManager.getAuthenHandler().post(this.mStartFromBp);
    }

    @Override // com.android.keyguard.faceunlock.IPreviewCallback
    public void onPreviewFrame(byte[] bArr, String str) {
        if (this.mIsStopEnroll) {
            Slog.d("miui.face.FaceService", "mIsStopEnroll == true");
            this.mCameraUtil.closeCameraImmediately();
            return;
        }
        Log.d("miui.face.transplant", "receive preview frame time=" + (System.currentTimeMillis() - sStageInFaceUnlockTime));
        sStageInFaceUnlockTime = System.currentTimeMillis();
        if (!this.mCanNextFaceInputStep) {
            Log.d("miui.face.FaceService", "wait and skip this frame……");
            return;
        }
        if (DeviceUtils.isNeedGetFrameNonsequence()) {
            int i = this.mPreviewCount;
            this.mPreviewCount = i + 1;
            if (i % 5 != 0) {
                Log.d("miui.face.FaceService", "it is sunny, skip this frame……");
                return;
            }
        }
        EnrollResult enrollResultSaveFeature = this.mFaceAuthManager.saveFeature(bArr, this.mCameraUtil.width, this.mCameraUtil.height, this.mCameraUtil.angle);
        Log.d("miui.face.transplant", "compare time =" + (System.currentTimeMillis() - sStageInFaceUnlockTime) + "; result：" + enrollResultSaveFeature.getEnrollStatus());
        sStageInFaceUnlockTime = System.currentTimeMillis();
        Log.d("miui.face.FaceService", "enroll, onPreviewFrame  ret:" + enrollResultSaveFeature);
        if (enrollResultSaveFeature.getEnrollStatus() == 19) {
            this.mCanNextFaceInputStep = false;
            this.mService.getWorkerHandler().obtainMessage(2, enrollResultSaveFeature.getEnrollStatus(), 0).sendToTarget();
            return;
        }
        if (enrollResultSaveFeature.getEnrollStatus() == 0) {
            this.mFaceUnlockManager.getAuthenHandler().removeCallbacks(this.mDelayedHide);
            this.mService.getWorkerHandler().obtainMessage(1, enrollResultSaveFeature.getFaceId() + 1, 0).sendToTarget();
            this.mCameraUtil.closeCameraImmediately();
            this.mFaceUnlockManager.runOnFaceUnlockWorkerThread(this.mReleaseAfterEnrolled);
            return;
        }
        if (enrollResultSaveFeature.getEnrollStatus() == 36) {
            this.mCanNextFaceInputStep = false;
            this.mFaceAuthManager.reset();
            this.mService.getWorkerHandler().obtainMessage(2, enrollResultSaveFeature.getEnrollStatus(), 0).sendToTarget();
            return;
        }
        this.mService.getWorkerHandler().obtainMessage(2, enrollResultSaveFeature.getEnrollStatus(), 0).sendToTarget();
    }

    @Override // com.android.keyguard.faceunlock.IPreviewCallback
    public void openCameraSucced(String str, int i) {
        Slog.d("miui.face.FaceService", "enroll, openCameraSucced  ,result: " + i + " cameraId:" + str);
        if (i == 0) {
            this.mFaceUnlockManager.removeReleaseMessage();
            this.mFaceUnlockManager.initAll();
            start(this.mDetectRect);
            try {
                this.mCameraUtil.startPreview(this.mSurface);
                this.mService.getWorkerHandler().obtainMessage(2, KeyguardUtils.MG_OPEN_CAMERA_SUCCESS, 0).sendToTarget();
                return;
            } catch (RuntimeException e) {
                e.printStackTrace();
                this.mCameraUtil.closeCameraImmediately();
                this.mService.getWorkerHandler().obtainMessage(2, KeyguardUtils.MG_OPEN_CAMERA_FAIL, 0).sendToTarget();
                return;
            }
        }
        this.mService.getWorkerHandler().obtainMessage(2, KeyguardUtils.MG_OPEN_CAMERA_FAIL, 0).sendToTarget();
    }
}
