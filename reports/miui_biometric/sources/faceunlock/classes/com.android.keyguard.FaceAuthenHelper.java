package com.android.keyguard;

import android.content.Context;
import android.util.Log;
import android.util.Slog;
import com.android.keyguard.faceunlock.FaceUnlockCallback;
import com.android.keyguard.faceunlock.FaceUnlockManager;
import com.miui.face.MiuiFaceService;
import com.miui.face.util.DeviceUtils;
import hidden.HiddenApiBridge;

/* JADX INFO: loaded from: classes.dex */
public class FaceAuthenHelper {
    Context mContext;
    FaceUnlockManager mFaceUnlockManager;
    MiuiFaceService mService;
    private int mTimeout = 3000;
    private final Runnable mReleaseIfAuthenticated = new Runnable() { // from class: com.android.keyguard.FaceAuthenHelper$$ExternalSyntheticLambda3
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m1lambda$new$3$comandroidkeyguardFaceAuthenHelper();
        }
    };
    private final Runnable mAuthen = new Runnable() { // from class: com.android.keyguard.FaceAuthenHelper.1
        @Override // java.lang.Runnable
        public void run() {
            FaceUnlockCallback faceUnlockCallback = new FaceUnlockCallback() { // from class: com.android.keyguard.FaceAuthenHelper.1.1
                @Override // com.android.keyguard.faceunlock.FaceUnlockCallback
                public void onFaceStart() {
                    Slog.d("miui.face.FaceService", "miui.face.timeline, onFaceStart, ServicePrepareTime:" + (System.currentTimeMillis() - FaceAuthenHelper.this.mService.mBeginAuth));
                }

                @Override // com.android.keyguard.faceunlock.FaceUnlockCallback
                public void onFaceHelp(int i) {
                    Log.d("miui.face.FaceService", "onFaceHelp help:" + i);
                    FaceAuthenHelper.this.mService.getWorkerHandler().obtainMessage(2, i, 0).sendToTarget();
                }

                @Override // com.android.keyguard.faceunlock.FaceUnlockCallback
                public void onFaceAuthFailed(boolean z) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - FaceAuthenHelper.this.mService.mBeginAuth;
                    Slog.d("miui.face.FaceService", "onFaceAuthFailed hasFace:" + z + " time: " + jCurrentTimeMillis);
                    if (jCurrentTimeMillis >= FaceAuthenHelper.this.mTimeout) {
                        FaceAuthenHelper.this.mService.getWorkerHandler().obtainMessage(MiuiFaceService.MSG_FACE_UNLOCK_WORK_TIMEOUT, 3, 0).sendToTarget();
                    } else {
                        FaceAuthenHelper.this.mService.getWorkerHandler().obtainMessage(3, 0, z ? 1 : 0).sendToTarget();
                    }
                }

                @Override // com.android.keyguard.faceunlock.FaceUnlockCallback
                public void onFaceAuthenticated() {
                    FaceAuthenHelper.this.mService.getWorkerHandler().obtainMessage(3, 123456, 0).sendToTarget();
                    FaceAuthenHelper.this.releaseOnWorkerThread();
                }
            };
            if (!FaceAuthenHelper.this.mFaceUnlockManager.hasInit()) {
                FaceAuthenHelper.this.mFaceUnlockManager.initAll();
            }
            FaceAuthenHelper.this.mFaceUnlockManager.setRunOutTime(FaceAuthenHelper.this.mTimeout);
            FaceAuthenHelper.this.mFaceUnlockManager.startFaceUnlock(faceUnlockCallback);
        }
    };

    public FaceAuthenHelper(MiuiFaceService miuiFaceService, Context context) {
        this.mService = miuiFaceService;
        this.mContext = context;
        this.mFaceUnlockManager = FaceUnlockManager.getInstance(context);
    }

    public void tryPreInitAuth() {
        this.mFaceUnlockManager.runOnFaceUnlockWorkerThread(new Runnable() { // from class: com.android.keyguard.FaceAuthenHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m4lambda$tryPreInitAuth$0$comandroidkeyguardFaceAuthenHelper();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$tryPreInitAuth$0$com-android-keyguard-FaceAuthenHelper, reason: not valid java name */
    /* synthetic */ void m4lambda$tryPreInitAuth$0$comandroidkeyguardFaceAuthenHelper() {
        this.mFaceUnlockManager.removeReleaseMessage();
        if (this.mFaceUnlockManager.hasInit()) {
            this.mService.getWorkerHandler().obtainMessage(8, true).sendToTarget();
            return;
        }
        this.mFaceUnlockManager.initAll();
        this.mService.getWorkerHandler().obtainMessage(8, Boolean.valueOf(this.mFaceUnlockManager.hasInit())).sendToTarget();
    }

    public void removeFace(final int i) {
        this.mFaceUnlockManager.runOnFaceUnlockWorkerThread(new Runnable() { // from class: com.android.keyguard.FaceAuthenHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m2lambda$removeFace$1$comandroidkeyguardFaceAuthenHelper(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$removeFace$1$com-android-keyguard-FaceAuthenHelper, reason: not valid java name */
    /* synthetic */ void m2lambda$removeFace$1$comandroidkeyguardFaceAuthenHelper(int i) {
        if (this.mFaceUnlockManager.deleteFeature(i)) {
            if (i == 0) {
                HiddenApiBridge.Settings_Secure_getIntForUser(this.mContext.getContentResolver(), "face_unlock_has_feature", 0, 0);
            }
            this.mService.getWorkerHandler().obtainMessage(5, i, this.mFaceUnlockManager.getFeatureCount()).sendToTarget();
        } else {
            this.mService.getWorkerHandler().obtainMessage(4, 6, this.mFaceUnlockManager.getFeatureCount()).sendToTarget();
        }
        if (this.mFaceUnlockManager.getFeatureCount() == 0) {
            this.mFaceUnlockManager.release(false);
        }
    }

    public void stopFromBp() {
        this.mService.getWorkerHandler().removeMessages(2);
        this.mService.getWorkerHandler().removeMessages(3);
        this.mService.getWorkerHandler().obtainMessage(4, 5, 0).sendToTarget();
        this.mFaceUnlockManager.getAuthenHandler().removeCallbacks(this.mAuthen);
        this.mFaceUnlockManager.getAuthenHandler().postAtFrontOfQueue(new Runnable() { // from class: com.android.keyguard.FaceAuthenHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m3lambda$stopFromBp$2$comandroidkeyguardFaceAuthenHelper();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$stopFromBp$2$com-android-keyguard-FaceAuthenHelper, reason: not valid java name */
    /* synthetic */ void m3lambda$stopFromBp$2$comandroidkeyguardFaceAuthenHelper() {
        Slog.d("miui.face.FaceService", "FaceAuthenHelper mStopFromBp Runnable run ... ...");
        this.mFaceUnlockManager.stopFaceUnlock();
        releaseMemory();
    }

    /* JADX INFO: renamed from: lambda$new$3$com-android-keyguard-FaceAuthenHelper, reason: not valid java name */
    /* synthetic */ void m1lambda$new$3$comandroidkeyguardFaceAuthenHelper() {
        Slog.d("miui.face.FaceService", "FaceAuthenHelper mReleaseIfAuthenticated Runnable run ... ...");
        releaseMemory();
    }

    private void releaseMemory() {
        Slog.d("miui.face.FaceService", "release memory... ...");
        this.mFaceUnlockManager.release(!DeviceUtils.isLiteMode());
    }

    public void authen(int i) {
        this.mTimeout = i;
        this.mFaceUnlockManager.runOnFaceUnlockWorkerThread(this.mAuthen);
    }

    public void releaseOnWorkerThread() {
        this.mFaceUnlockManager.runOnFaceUnlockWorkerThread(this.mReleaseIfAuthenticated);
    }
}
