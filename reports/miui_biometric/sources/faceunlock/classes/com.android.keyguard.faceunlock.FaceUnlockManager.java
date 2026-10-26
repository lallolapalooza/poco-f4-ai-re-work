package com.android.keyguard.faceunlock;

import android.content.Context;
import android.util.Log;
import android.util.Slog;
import com.miui.face.util.MiSightCompat;
import com.ts.faceauth.FaceAuthManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FaceUnlockManager extends BaseFaceUnlockManager {
    public static final String MODEL_PATH = "/data/data/com.miui.face/files/template_file.data";
    private static FaceUnlockManager sFaceUnlockManager;
    private FaceAuthManager mFaceAuthManager;
    private int mTimeout = 5000;

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public int getRunOutTime() {
        return this.mTimeout;
    }

    public static FaceUnlockManager getInstance(Context context) {
        if (sFaceUnlockManager == null) {
            sFaceUnlockManager = new FaceUnlockManager(context.getApplicationContext());
        }
        return sFaceUnlockManager;
    }

    private FaceUnlockManager(Context context) {
        this.mContext = context;
        this.mFaceAuthManager = FaceAuthManager.getInstance();
        initFaceUnlockUtil();
    }

    public void initAll() {
        if (this.mFaceAuthManager == null) {
            Slog.d("miui.face.transplant", "initAll failed mFaceAuthManager null");
            return;
        }
        if (this.mHasInit) {
            return;
        }
        Slog.d("miui.face.transplant", "initAll");
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            boolean zInitHandler = this.mFaceAuthManager.initHandler(this.mContext.getFilesDir().getAbsolutePath());
            this.mFaceAuthManager.initAll("model1", "model2");
            if (DEBUG) {
                Slog.d("miui.face.transplant", "init all time=" + (System.currentTimeMillis() - jCurrentTimeMillis) + ", intHandlerResult=" + zInitHandler);
            }
            this.mHasInit = true;
        } catch (Exception e) {
            Slog.e("miui.face.transplant", "init algo fail", e);
            MiSightCompat.sendEvent(904004003, "result", "init algo fail");
        }
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public void releaseFaceUnlock() {
        if (this.mHasInit) {
            this.mHasInit = false;
            FaceAuthManager faceAuthManager = this.mFaceAuthManager;
            if (faceAuthManager != null) {
                faceAuthManager.setStandByMode();
                this.mFaceAuthManager.release();
            }
            new FaceUnlockJni().nativeMallopt();
            Slog.d("miui.face.transplant", "face release complete");
        }
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public void deleteFeature() {
        if (DEBUG) {
            Slog.d("miui.face.transplant", "deleteFeature");
        }
        FaceAuthManager faceAuthManager = this.mFaceAuthManager;
        if (faceAuthManager != null) {
            faceAuthManager.deleteFeature();
            File file = new File(MODEL_PATH);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public boolean deleteFeature(int i) {
        int iDeleteFeature;
        if (DEBUG) {
            Slog.d("miui.face.transplant", "deleteFeature faceId = " + i);
        }
        initAll();
        if (this.mFaceAuthManager != null && this.mHasInit) {
            if (i == 0) {
                iDeleteFeature = this.mFaceAuthManager.deleteFeature();
            } else {
                iDeleteFeature = this.mFaceAuthManager.deleteFeature(i - 1);
            }
            Slog.d("miui.face.transplant", "getFeatureCount = " + this.mFaceAuthManager.getFeatureCount());
            if (iDeleteFeature == 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public void startFaceUnlock(FaceUnlockCallback faceUnlockCallback) {
        if (this.mFaceAuthManager == null) {
            Slog.d("TAG", "startFaceUnlock failed mFaceAuthManager null");
        } else {
            super.startFaceUnlock(faceUnlockCallback);
        }
    }

    @Override // com.android.keyguard.faceunlock.IPreviewCallback
    public void onPreviewFrame(byte[] bArr, String str) {
        int i = this.mFrameCount;
        this.mFrameCount = i + 1;
        if (i == 0) {
            this.mStartAfterFirstFrame = System.currentTimeMillis();
        }
        if (!this.mIsTrigger) {
            stopDetect();
            return;
        }
        if (this.mFaceAuthManager == null) {
            Slog.d("miui.face.transplant", "onPreviewFrame failed mFaceAuthManager null");
            return;
        }
        if (this.mIsSuccess || this.mIsDetecting) {
            return;
        }
        if (!this.mHasFace && this.mSkipIfNotFound) {
            this.mSkipIfNotFound = false;
            return;
        }
        this.mSkipIfNotFound = true;
        this.mIsDetecting = true;
        long jCurrentTimeMillis = System.currentTimeMillis() - sStageInFaceUnlockTime;
        if (DEBUG) {
            Log.d("miui.face.transplant", "receive preview frame time=" + jCurrentTimeMillis);
            sStageInFaceUnlockTime = System.currentTimeMillis();
        }
        int iCompare = this.mFaceAuthManager.compare(bArr, this.mCameraUtil.width, this.mCameraUtil.height, this.mCameraUtil.angle, true);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - sStageInFaceUnlockTime;
        if (DEBUG) {
            Log.d("miui.face.transplant", "compare time =" + jCurrentTimeMillis2 + "; result：" + iCompare);
            sStageInFaceUnlockTime = System.currentTimeMillis();
        }
        if (iCompare != 5) {
            this.mHasFace = true;
        }
        if (iCompare == 0) {
            this.mCallback.onFaceHelp(iCompare);
            handlePassed();
        } else {
            this.mCallback.onFaceHelp(iCompare);
            this.mIsDetecting = false;
        }
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public int getFeatureCount() {
        initAll();
        return this.mFaceAuthManager.getFeatureCount();
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public String getVersion() {
        return this.mFaceAuthManager.getVersion();
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public void setRunOutTime(int i) {
        Slog.d("miui.face.transplant", "FaceUnlockManager setRunOutTime timeout:" + i);
        if (i <= 2000) {
            return;
        }
        this.mTimeout = i;
    }

    @Override // com.android.keyguard.faceunlock.BaseFaceUnlockManager
    public List<Integer> getEnrolledFaceIds() {
        if (!this.mHasInit) {
            initAll();
        }
        ArrayList arrayList = new ArrayList();
        int[] faceIDsInTemplate = this.mFaceAuthManager.getFaceIDsInTemplate();
        if (faceIDsInTemplate == null) {
            Slog.d("miui.face.transplant", "get face IDs data error");
            return arrayList;
        }
        for (int i : faceIDsInTemplate) {
            arrayList.add(Integer.valueOf(i + 1));
        }
        return arrayList;
    }
}
