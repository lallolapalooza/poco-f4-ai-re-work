package com.kwai.video.westeros.v2.faceless;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import com.google.protobuf.InvalidProtocolBufferException;
import com.kwai.video.westeros.helpers.ValidationChecker;
import com.kwai.video.westeros.models.BatchEffectCommand;
import com.kwai.video.westeros.models.BodySlimmingAdjustType;
import com.kwai.video.westeros.models.EffectCommand;
import com.kwai.video.westeros.models.EffectCommandType;
import com.kwai.video.westeros.models.EffectControl;
import com.kwai.video.westeros.models.EffectDescription;
import com.kwai.video.westeros.models.EffectError;
import com.kwai.video.westeros.models.EffectHint;
import com.kwai.video.westeros.models.EffectResource;
import com.kwai.video.westeros.models.EffectSlot;
import com.kwai.video.westeros.models.EffectType;
import com.kwai.video.westeros.models.UserInfo;
import com.kwai.video.westeros.v2.WesterosGlProcessor;
import com.yxcorp.bugly.CrashReporter;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes13.dex */
@Keep
public class FaceMagicController extends WesterosGlProcessor {
    private static FaceMagicLoadEffectFailedListener sFaceMagicLoadEffectFailedListener;
    private FaceMagicBodySlimmingListener bodySlimmingListener;
    private FaceMagicBokehListener bokehListener;
    private FaceMagicBoomGameListener boomGameListener;
    private FaceMagicNeedCaptureListener captureListener;
    private final ValidationChecker checker;
    private FaceMagicDepthFinishListener depthFinishListener;
    private FaceMagicExpressionListener expressionListener;
    private FaceMagicEnvironmentUnsafeListener faceMagicEnvironmentUnsafeListener;
    private FaceMagicListener faceMagicListener;
    private FaceMagicInputTextRequestListener inputTextRequestListener;
    private FaceMagicLockExposureListener lockExposureListener;
    private final byte[] mListenerLock = new byte[0];
    private FaceMagicRemovelListener magicRemovelListener;
    private FaceMagicMemojiListener memojiListener;
    private long nativeController;
    private FaceMagicPickFaceImageListener pickFaceImageListener;
    private FaceMagicPortraitListener portraitListener;
    private FaceMagicRecordingControlListener recordingControlListener;
    private FaceMagicReportBeautifyLogListener reportBeautifyLogListener;
    private FaceMagicUserInfoListener userInfoListener;
    private static final Object LOCK = new Object();
    private static Set<FaceMagicLoadEffectFailedListener> sFaceMagicLoadEffectFailedListeners = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: com.kwai.video.westeros.v2.faceless.FaceMagicController$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$kwai$video$westeros$models$EffectCommandType;

        static {
            int[] iArr = new int[EffectCommandType.values().length];
            $SwitchMap$com$kwai$video$westeros$models$EffectCommandType = iArr;
            try {
                iArr[EffectCommandType.kSwitchLookupEffect.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "FaceMagicController$2.class", "<clinit>", -1);
            }
            try {
                $SwitchMap$com$kwai$video$westeros$models$EffectCommandType[EffectCommandType.kSetMakeupResource.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "FaceMagicController$2.class", "<clinit>", -1);
            }
            try {
                $SwitchMap$com$kwai$video$westeros$models$EffectCommandType[EffectCommandType.kSetGroupEffect.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "FaceMagicController$2.class", "<clinit>", -1);
            }
        }
    }

    public interface FaceMagicBodySlimmingListener {
        void OnBodySlimmingStatusChanged(boolean z12);

        void OnSingleBodySlimmingStatusChanged(BodySlimmingAdjustType bodySlimmingAdjustType, boolean z12);
    }

    public interface FaceMagicBokehListener {
        void onReceivedBokehMask(byte[] bArr, int i12, int i13);

        void onReceivedBokehStatus(int i12);
    }

    public interface FaceMagicBoomGameListener {
        void onReceivedBoomGameInfo(String str);
    }

    public interface FaceMagicDepthFinishListener {
        void faceMagicDepthFinish();
    }

    public interface FaceMagicEnvironmentUnsafeListener {
        void onFaceMagicEnvironmentUnsafe(int i12);
    }

    public interface FaceMagicExpressionListener {
        void onReceivedExpressionChanged(String str, boolean z12);
    }

    public interface FaceMagicInputTextRequestListener {
        void onInputTextRequest(int i12, String str);
    }

    public interface FaceMagicListener {
        void onEffectDescriptionUpdated(EffectDescription effectDescription, EffectSlot effectSlot);

        void onEffectDescriptionUpdated(EffectDescription effectDescription, EffectSlot effectSlot, EffectResource effectResource);

        void onEffectHintUpdated(EffectHint effectHint);

        void onEffectPlayCompleted(EffectSlot effectSlot, int i12);

        void onLoadGroupEffect(EffectDescription effectDescription, EffectSlot effectSlot, String str);

        void onSetEffectFailed(EffectResource effectResource, EffectSlot effectSlot, EffectError effectError);
    }

    public interface FaceMagicLoadEffectFailedListener {
        void onLoadFileError(String str, int i12);
    }

    public interface FaceMagicLockExposureListener {
        void onReceivedLockExposure();
    }

    public interface FaceMagicMemojiListener {
        void onReceivedMemojiIcon(Bitmap bitmap);

        void onReceivedMemojiUserConfigJson(String str);
    }

    public interface FaceMagicNeedCaptureListener {
        void faceMagicNeedCapture();
    }

    public interface FaceMagicPickFaceImageListener {
        void onRequestPickFaceImage();
    }

    public interface FaceMagicPortraitListener {
        void onReceiveValidPortrait(boolean z12);
    }

    public interface FaceMagicRecordingControlListener {
        void onReceiveRecordingControl(int i12);
    }

    public interface FaceMagicRemovelListener {
        void onReceivedMagicRemovelStatus(int i12);
    }

    public interface FaceMagicReportBeautifyLogListener {
        void reportBeautifyLog(String str, String str2);
    }

    public interface FaceMagicUserInfoListener {
        void onGetUserInfo();
    }

    FaceMagicController(long j12, boolean z12) {
        long jNativeInitController = nativeInitController(j12, z12);
        this.nativeController = jNativeInitController;
        if (!z12) {
            this.nativeProcessor = nativeGetGlProcessor(jNativeInitController);
        }
        this.checker = new ValidationChecker() { // from class: com.kwai.video.westeros.v2.faceless.FaceMagicController.1
            @Override // com.kwai.video.westeros.helpers.ValidationChecker
            public boolean isValid() {
                return FaceMagicController.this.nativeController != 0;
            }
        };
    }

    private void OnBodySlimmingStatusChanged(boolean z12) {
        FaceMagicBodySlimmingListener faceMagicBodySlimmingListener = this.bodySlimmingListener;
        if (faceMagicBodySlimmingListener != null) {
            faceMagicBodySlimmingListener.OnBodySlimmingStatusChanged(z12);
        }
    }

    private void OnSingleBodySlimmingStatusChanged(int i12, boolean z12) {
        FaceMagicBodySlimmingListener faceMagicBodySlimmingListener = this.bodySlimmingListener;
        if (faceMagicBodySlimmingListener != null) {
            faceMagicBodySlimmingListener.OnSingleBodySlimmingStatusChanged(BodySlimmingAdjustType.forNumber(i12), z12);
        }
    }

    public static void addFaceMagicLoadEffectFailedListener(FaceMagicLoadEffectFailedListener faceMagicLoadEffectFailedListener) {
        if (faceMagicLoadEffectFailedListener == null) {
            return;
        }
        synchronized (LOCK) {
            try {
                sFaceMagicLoadEffectFailedListeners.add(faceMagicLoadEffectFailedListener);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "addFaceMagicLoadEffectFailedListener", -1);
                throw th2;
            }
        }
    }

    public static void enableAlertBox(boolean z12) {
        nativeEnableAlertBox(z12);
    }

    public static void enableMagicFileCheckCallback() {
        nativeEnableMagicFileCheckCallback();
    }

    public static int getDeformLiquifyIDBase() {
        return 10000;
    }

    public static int getSupport3DVersion() {
        return FacelessResourceVersion.SUPPORT_3D_VERSION;
    }

    public static int getSupportMakeupVersion() {
        return FacelessResourceVersion.SUPPORT_MAKEUP_VERSION;
    }

    public static int getSupportMaxVersion() {
        return FacelessResourceVersion.SUPPORT_MAX_VERSION;
    }

    public static int[] getUnSupportMakeupVersion() {
        return FacelessResourceVersion.UNSUPPORT_MAKEUP_VERSION;
    }

    public static int[] getUnSupportVersion() {
        return FacelessResourceVersion.UNSUPPORT_VERSION;
    }

    public static boolean isResourceValid(String str) {
        return new File(str, "params.txt").exists();
    }

    private native void nativeDestroyController(long j12);

    private native void nativeDisableEffect(long j12, int i12);

    private native void nativeDisableEffect2(long j12, int i12, byte[] bArr);

    private static native void nativeEnableAlertBox(boolean z12);

    private static native void nativeEnableMagicFileCheckCallback();

    private native long nativeGetGlProcessor(long j12);

    private native double nativeGetRedPacketScore(long j12);

    private native long nativeInitController(long j12, boolean z12);

    private native void nativeSendBatchEffectCommand(long j12, byte[] bArr);

    private native void nativeSendEffectCommand(long j12, byte[] bArr);

    private native void nativeSendEffectCommandSync(long j12, byte[] bArr);

    private static native void nativeSetAioSafe(long j12, boolean z12);

    private native void nativeSetAppKey(long j12, String str);

    public static native void nativeSetBeautifyExternalBuiltinPath(String str);

    private native void nativeSetDynamicRunningKeyandValue(String str, String str2);

    private native void nativeSetEffect(long j12, byte[] bArr, int i12, int i13);

    private native void nativeSetEffectEnable(long j12, int i12, boolean z12);

    private native void nativeSetEnableDeformGradient(long j12, boolean z12);

    private native void nativeSetExternalBuiltinPath(long j12, String str);

    private native void nativeSetInitialVideoFrameSize(long j12, int i12, int i13);

    private native void nativeSetPickedFaceImage(long j12, String str);

    public static native void nativeSetRecordingSpeed(long j12, float f12);

    private static native void nativeSetRunningKeyandValue(String str, String str2);

    public static native void nativeSetTimeControl(long j12, boolean z12);

    private native void nativeSetUserInfo(long j12, byte[] bArr);

    private native void nativeUpdateEffectControl(long j12, byte[] bArr);

    private native void nativeUpdateEffectUsingFramePts(long j12, boolean z12);

    private void onDepthFinishCallback() {
        FaceMagicDepthFinishListener faceMagicDepthFinishListener = this.depthFinishListener;
        if (faceMagicDepthFinishListener != null) {
            faceMagicDepthFinishListener.faceMagicDepthFinish();
        }
    }

    private void onEffectDescriptionFromNative(byte[] bArr, int i12) {
        try {
            synchronized (this.mListenerLock) {
                try {
                    FaceMagicListener faceMagicListener = this.faceMagicListener;
                    if (faceMagicListener != null) {
                        if (bArr != null) {
                            this.faceMagicListener.onEffectDescriptionUpdated(EffectDescription.parseFrom(bArr), EffectSlot.forNumber(i12));
                        } else {
                            faceMagicListener.onEffectDescriptionUpdated(null, EffectSlot.forNumber(i12));
                        }
                    }
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onEffectDescriptionFromNative", -1);
                    throw th2;
                }
            }
        } catch (InvalidProtocolBufferException e12) {
            CrashReporter.logCheckedException(e12, "FaceMagicController.class", "onEffectDescriptionFromNative", -3);
            e12.printStackTrace();
        }
    }

    private void onEffectHintFromNative(byte[] bArr) {
        synchronized (this.mListenerLock) {
            try {
                if (this.faceMagicListener != null) {
                    try {
                        this.faceMagicListener.onEffectHintUpdated(EffectHint.parseFrom(bArr));
                    } catch (InvalidProtocolBufferException e12) {
                        CrashReporter.logCheckedException(e12, "FaceMagicController.class", "onEffectHintFromNative", 29);
                        e12.printStackTrace();
                    }
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onEffectHintFromNative", -1);
                throw th2;
            }
        }
    }

    private void onEffectPlayCompleted(int i12, int i13) {
        synchronized (this.mListenerLock) {
            try {
                FaceMagicListener faceMagicListener = this.faceMagicListener;
                if (faceMagicListener != null) {
                    faceMagicListener.onEffectPlayCompleted(EffectSlot.forNumber(i12), i13);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onEffectPlayCompleted", -1);
                throw th2;
            }
        }
    }

    private void onFaceMagicEnvironmentUnsafe(int i12) {
        synchronized (this.mListenerLock) {
            try {
                FaceMagicEnvironmentUnsafeListener faceMagicEnvironmentUnsafeListener = this.faceMagicEnvironmentUnsafeListener;
                if (faceMagicEnvironmentUnsafeListener != null) {
                    faceMagicEnvironmentUnsafeListener.onFaceMagicEnvironmentUnsafe(i12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onFaceMagicEnvironmentUnsafe", -1);
                throw th2;
            }
        }
    }

    private void onGetUserInfoFromNative() {
        synchronized (this.mListenerLock) {
            try {
                FaceMagicUserInfoListener faceMagicUserInfoListener = this.userInfoListener;
                if (faceMagicUserInfoListener != null) {
                    faceMagicUserInfoListener.onGetUserInfo();
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onGetUserInfoFromNative", -1);
                throw th2;
            }
        }
    }

    private void onInputTextRequest(int i12, String str) {
        FaceMagicInputTextRequestListener faceMagicInputTextRequestListener = this.inputTextRequestListener;
        if (faceMagicInputTextRequestListener != null) {
            faceMagicInputTextRequestListener.onInputTextRequest(i12, str);
        }
    }

    private static void onLoadFileError(String str, int i12) {
        FaceMagicLoadEffectFailedListener faceMagicLoadEffectFailedListener = sFaceMagicLoadEffectFailedListener;
        if (faceMagicLoadEffectFailedListener != null) {
            faceMagicLoadEffectFailedListener.onLoadFileError(str, i12);
        }
        for (FaceMagicLoadEffectFailedListener faceMagicLoadEffectFailedListener2 : sFaceMagicLoadEffectFailedListeners) {
            if (faceMagicLoadEffectFailedListener2 != null) {
                faceMagicLoadEffectFailedListener2.onLoadFileError(str, i12);
            }
        }
    }

    private void onLoadGroupEffect(byte[] bArr, int i12, String str) {
        synchronized (this.mListenerLock) {
            try {
                if (this.faceMagicListener != null) {
                    EffectDescription from = null;
                    if (bArr != null) {
                        try {
                            from = EffectDescription.parseFrom(bArr);
                        } catch (InvalidProtocolBufferException e12) {
                            CrashReporter.logCheckedException(e12, "FaceMagicController.class", "onLoadGroupEffect", 105);
                            e12.printStackTrace();
                        }
                    }
                    this.faceMagicListener.onLoadGroupEffect(from, EffectSlot.forNumber(i12), str);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onLoadGroupEffect", -1);
                throw th2;
            }
        }
    }

    private void onReceiveRecordingControl(int i12) {
        FaceMagicRecordingControlListener faceMagicRecordingControlListener = this.recordingControlListener;
        if (faceMagicRecordingControlListener != null) {
            faceMagicRecordingControlListener.onReceiveRecordingControl(i12);
        }
    }

    private void onReceivedBokehMask(byte[] bArr, int i12, int i13) {
        FaceMagicBokehListener faceMagicBokehListener = this.bokehListener;
        if (faceMagicBokehListener != null) {
            faceMagicBokehListener.onReceivedBokehMask(bArr, i12, i13);
        }
    }

    private void onReceivedBokehStatus(int i12) {
        FaceMagicBokehListener faceMagicBokehListener = this.bokehListener;
        if (faceMagicBokehListener != null) {
            faceMagicBokehListener.onReceivedBokehStatus(i12);
        }
    }

    private void onReceivedBoomGameInfo(String str) {
        FaceMagicBoomGameListener faceMagicBoomGameListener = this.boomGameListener;
        if (faceMagicBoomGameListener != null) {
            faceMagicBoomGameListener.onReceivedBoomGameInfo(str);
        }
    }

    private void onReceivedLockExposure() {
        FaceMagicLockExposureListener faceMagicLockExposureListener = this.lockExposureListener;
        if (faceMagicLockExposureListener != null) {
            faceMagicLockExposureListener.onReceivedLockExposure();
        }
    }

    private void onReceivedMagicRemovelStatus(int i12) {
        FaceMagicRemovelListener faceMagicRemovelListener = this.magicRemovelListener;
        if (faceMagicRemovelListener != null) {
            faceMagicRemovelListener.onReceivedMagicRemovelStatus(i12);
        }
    }

    private void onReceivedMemojiIcon(ByteBuffer byteBuffer, int i12, int i13) {
        synchronized (this.mListenerLock) {
            try {
                if (this.memojiListener != null) {
                    byteBuffer.position(0);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i12, i13, Bitmap.Config.ARGB_8888);
                    bitmapCreateBitmap.copyPixelsFromBuffer(byteBuffer);
                    this.memojiListener.onReceivedMemojiIcon(bitmapCreateBitmap);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onReceivedMemojiIcon", -1);
                throw th2;
            }
        }
    }

    private void onReceivedMemojiUserConfigJsonNative(String str) {
        synchronized (this.mListenerLock) {
            try {
                FaceMagicMemojiListener faceMagicMemojiListener = this.memojiListener;
                if (faceMagicMemojiListener != null) {
                    faceMagicMemojiListener.onReceivedMemojiUserConfigJson(str);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onReceivedMemojiUserConfigJsonNative", -1);
                throw th2;
            }
        }
    }

    private void onRequestPickImageFromNative() {
        synchronized (this.mListenerLock) {
            try {
                FaceMagicPickFaceImageListener faceMagicPickFaceImageListener = this.pickFaceImageListener;
                if (faceMagicPickFaceImageListener != null) {
                    faceMagicPickFaceImageListener.onRequestPickFaceImage();
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onRequestPickImageFromNative", -1);
                throw th2;
            }
        }
    }

    private void onSetEffectFailed(byte[] bArr, int i12, byte[] bArr2) {
        EffectResource from;
        if (this.faceMagicListener != null) {
            EffectError from2 = null;
            if (bArr != null) {
                try {
                    from = EffectResource.parseFrom(bArr);
                } catch (InvalidProtocolBufferException e12) {
                    CrashReporter.logCheckedException(e12, "FaceMagicController.class", "onSetEffectFailed", -105);
                    e12.printStackTrace();
                    from = null;
                }
            } else {
                from = null;
            }
            if (bArr2 != null) {
                try {
                    from2 = EffectError.parseFrom(bArr2);
                } catch (InvalidProtocolBufferException e13) {
                    CrashReporter.logCheckedException(e13, "FaceMagicController.class", "onSetEffectFailed", -97);
                    e13.printStackTrace();
                }
            }
            this.faceMagicListener.onSetEffectFailed(from, EffectSlot.forNumber(i12), from2);
        }
    }

    public static void removeFaceMagicLoadEffectFailedListener(FaceMagicLoadEffectFailedListener faceMagicLoadEffectFailedListener) {
        if (faceMagicLoadEffectFailedListener == null) {
            return;
        }
        synchronized (LOCK) {
            try {
                if (sFaceMagicLoadEffectFailedListeners.contains(faceMagicLoadEffectFailedListener)) {
                    sFaceMagicLoadEffectFailedListeners.remove(faceMagicLoadEffectFailedListener);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FaceMagicController.class", "removeFaceMagicLoadEffectFailedListener", -1);
                throw th2;
            }
        }
    }

    public static void setBeautifyExternalBuiltinPath(String str) {
        nativeEnableMagicFileCheckCallback();
        if (str == null) {
            str = "";
        }
        nativeSetBeautifyExternalBuiltinPath(str);
    }

    public static void setFaceMagicLoadEffectFailedListener(FaceMagicLoadEffectFailedListener faceMagicLoadEffectFailedListener) {
        sFaceMagicLoadEffectFailedListener = faceMagicLoadEffectFailedListener;
    }

    public static void setRunningKeyandValue(String str, String str2) {
        nativeEnableMagicFileCheckCallback();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        nativeSetRunningKeyandValue(str, str2);
    }

    public void closeMagicAudio() {
        sendEffectCommand(EffectCommand.newBuilder().setCommandType(EffectCommandType.kCloseMagicAudio).build());
    }

    public void closeMagicAudioSync() {
        sendEffectCommandSync(EffectCommand.newBuilder().setCommandType(EffectCommandType.kCloseMagicAudio).build());
    }

    @Override // com.kwai.video.westeros.v2.WesterosGlProcessor
    protected long createNativeProcessor() {
        return 0L;
    }

    public void disableEffectAtSlot(EffectSlot effectSlot) {
        if (this.checker.isValid()) {
            nativeDisableEffect(this.nativeController, effectSlot.getNumber());
        }
    }

    public double getRedPacketScore() {
        if (this.checker.isValid()) {
            return nativeGetRedPacketScore(this.nativeController);
        }
        return 50.0d;
    }

    public native void nativeSetABTestJson(long j12, String str);

    public native void nativeSetExtraCommand(long j12, String str);

    public native void nativeSetIsValidPortraitCallback(long j12, boolean z12);

    public native void nativeSetOnExpressionChangedCallback(long j12, String str, boolean z12);

    public native void nativeSetReportBeautifyLogCallback(long j12, boolean z12);

    public void onCallCaptureTexture() {
        FaceMagicNeedCaptureListener faceMagicNeedCaptureListener = this.captureListener;
        if (faceMagicNeedCaptureListener != null) {
            faceMagicNeedCaptureListener.faceMagicNeedCapture();
        }
    }

    public void onReceiveValidPortrait(boolean z12) {
        FaceMagicPortraitListener faceMagicPortraitListener = this.portraitListener;
        if (faceMagicPortraitListener != null) {
            faceMagicPortraitListener.onReceiveValidPortrait(z12);
        }
    }

    public void onReceivedExpressionChanged(String str, boolean z12) {
        FaceMagicExpressionListener faceMagicExpressionListener = this.expressionListener;
        if (faceMagicExpressionListener != null) {
            faceMagicExpressionListener.onReceivedExpressionChanged(str, z12);
        }
    }

    public void pause() {
        sendEffectCommand(EffectCommand.newBuilder().setCommandType(EffectCommandType.kPause).build());
    }

    public void pauseSync() {
        sendEffectCommandSync(EffectCommand.newBuilder().setCommandType(EffectCommandType.kPause).build());
    }

    @Override // com.kwai.video.westeros.v2.WesterosGlProcessor
    public void release() {
        super.release();
        nativeDestroyController(this.nativeController);
        this.nativeController = 0L;
    }

    public void reportBeautifyLog(String str, String str2) {
        FaceMagicReportBeautifyLogListener faceMagicReportBeautifyLogListener = this.reportBeautifyLogListener;
        if (faceMagicReportBeautifyLogListener != null) {
            faceMagicReportBeautifyLogListener.reportBeautifyLog(str, str2);
        }
    }

    public void reset() {
        sendEffectCommand(EffectCommand.newBuilder().setCommandType(EffectCommandType.kReset).build());
    }

    public void resume() {
        sendEffectCommand(EffectCommand.newBuilder().setCommandType(EffectCommandType.kResume).build());
    }

    public void resumeMagicAudio() {
        sendEffectCommand(EffectCommand.newBuilder().setCommandType(EffectCommandType.kResumeMagicAudio).build());
    }

    public void resumeMagicAudioSync() {
        sendEffectCommandSync(EffectCommand.newBuilder().setCommandType(EffectCommandType.kResumeMagicAudio).build());
    }

    public void sendBatchEffectCommand(BatchEffectCommand batchEffectCommand) {
        if (this.checker.isValid()) {
            Iterator<EffectCommand> it2 = batchEffectCommand.getCommandsList().iterator();
            while (it2.hasNext()) {
                int i12 = AnonymousClass2.$SwitchMap$com$kwai$video$westeros$models$EffectCommandType[it2.next().getCommandType().ordinal()];
                if (i12 == 1 || i12 == 2 || i12 == 3) {
                    nativeEnableMagicFileCheckCallback();
                }
            }
            nativeSendBatchEffectCommand(this.nativeController, batchEffectCommand.toByteArray());
        }
    }

    public void sendEffectCommand(EffectCommand effectCommand) {
        if (this.checker.isValid()) {
            int i12 = AnonymousClass2.$SwitchMap$com$kwai$video$westeros$models$EffectCommandType[effectCommand.getCommandType().ordinal()];
            if (i12 == 1 || i12 == 2 || i12 == 3) {
                nativeEnableMagicFileCheckCallback();
            }
            nativeSendEffectCommand(this.nativeController, effectCommand.toByteArray());
        }
    }

    public void sendEffectCommandSync(EffectCommand effectCommand) {
        if (this.checker.isValid()) {
            nativeSendEffectCommandSync(this.nativeController, effectCommand.toByteArray());
        }
    }

    public void setABTestJson(String str) {
        if (this.checker.isValid()) {
            nativeSetABTestJson(this.nativeController, str);
        }
    }

    public void setAioSafe(boolean z12) {
        if (this.checker.isValid()) {
            nativeSetAioSafe(this.nativeController, z12);
        }
    }

    public void setAppKey(String str) {
        if (this.checker.isValid()) {
            nativeSetAppKey(this.nativeController, str);
        }
    }

    public void setDynamicRunningKeyandValue(String str, String str2) {
        nativeEnableMagicFileCheckCallback();
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        nativeSetDynamicRunningKeyandValue(str, str2);
    }

    public void setEffectAtSlot(EffectResource effectResource, EffectSlot effectSlot) {
        if (this.checker.isValid()) {
            if (effectSlot == EffectSlot.kEffectSlotMain) {
                nativeEnableMagicFileCheckCallback();
            }
            setEffectAtSlot(effectResource, effectSlot, 0);
        }
    }

    public void setEffectEnable(EffectType effectType, boolean z12) {
        if (this.checker.isValid()) {
            nativeSetEffectEnable(this.nativeController, effectType.getNumber(), z12);
        }
    }

    public void setEnableDeformGradient(boolean z12) {
        if (this.checker.isValid()) {
            nativeSetEnableDeformGradient(this.nativeController, z12);
        }
    }

    public void setExternalBuiltinPath(String str) {
        long j12 = this.nativeController;
        if (str == null) {
            str = "";
        }
        nativeSetExternalBuiltinPath(j12, str);
    }

    public void setExtraCommand(String str) {
        nativeSetExtraCommand(this.nativeController, str);
    }

    public void setFaceMagicBodySlimmingListener(FaceMagicBodySlimmingListener faceMagicBodySlimmingListener) {
        if (this.checker.isValid()) {
            this.bodySlimmingListener = faceMagicBodySlimmingListener;
        }
    }

    public void setFaceMagicBokehListener(FaceMagicBokehListener faceMagicBokehListener) {
        if (this.checker.isValid()) {
            this.bokehListener = faceMagicBokehListener;
        }
    }

    public void setFaceMagicBoomGameListener(FaceMagicBoomGameListener faceMagicBoomGameListener) {
        if (this.checker.isValid()) {
            this.boomGameListener = faceMagicBoomGameListener;
        }
    }

    public void setFaceMagicDepthFinishListener(FaceMagicDepthFinishListener faceMagicDepthFinishListener) {
        if (this.checker.isValid()) {
            this.depthFinishListener = faceMagicDepthFinishListener;
        }
    }

    public void setFaceMagicEnvironmentUnsafeListener(FaceMagicEnvironmentUnsafeListener faceMagicEnvironmentUnsafeListener) {
        this.faceMagicEnvironmentUnsafeListener = faceMagicEnvironmentUnsafeListener;
    }

    public void setFaceMagicExpressionListener(String str, FaceMagicExpressionListener faceMagicExpressionListener) {
        if (this.checker.isValid()) {
            this.expressionListener = faceMagicExpressionListener;
            nativeSetOnExpressionChangedCallback(this.nativeController, str, faceMagicExpressionListener != null);
        }
    }

    public void setFaceMagicListener(FaceMagicListener faceMagicListener) {
        if (this.checker.isValid()) {
            synchronized (this.mListenerLock) {
                try {
                    this.faceMagicListener = faceMagicListener;
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "FaceMagicController.class", "setFaceMagicListener", -1);
                    throw th2;
                }
            }
        }
    }

    public void setFaceMagicLockExposureListener(FaceMagicLockExposureListener faceMagicLockExposureListener) {
        if (this.checker.isValid()) {
            this.lockExposureListener = faceMagicLockExposureListener;
        }
    }

    public void setFaceMagicNeedCaptureListener(FaceMagicNeedCaptureListener faceMagicNeedCaptureListener) {
        if (this.checker.isValid()) {
            this.captureListener = faceMagicNeedCaptureListener;
        }
    }

    public void setFaceMagicPickFaceImageListener(FaceMagicPickFaceImageListener faceMagicPickFaceImageListener) {
        if (this.checker.isValid()) {
            this.pickFaceImageListener = faceMagicPickFaceImageListener;
        }
    }

    public void setFaceMagicPortraitListener(FaceMagicPortraitListener faceMagicPortraitListener) {
        if (this.checker.isValid()) {
            this.portraitListener = faceMagicPortraitListener;
            nativeSetIsValidPortraitCallback(this.nativeController, faceMagicPortraitListener != null);
        }
    }

    public void setFaceMagicRecodingControlListener(FaceMagicRecordingControlListener faceMagicRecordingControlListener) {
        if (this.checker.isValid()) {
            this.recordingControlListener = faceMagicRecordingControlListener;
        }
    }

    public void setFaceMagicRemovelListener(FaceMagicRemovelListener faceMagicRemovelListener) {
        if (this.checker.isValid()) {
            this.magicRemovelListener = faceMagicRemovelListener;
        }
    }

    public void setFaceMagicUserInfoListener(FaceMagicUserInfoListener faceMagicUserInfoListener) {
        if (this.checker.isValid()) {
            this.userInfoListener = faceMagicUserInfoListener;
        }
    }

    public void setInitialVideoFrameSize(int i12, int i13) {
        if (this.checker.isValid()) {
            nativeSetInitialVideoFrameSize(this.nativeController, i12, i13);
        }
    }

    public void setInputTextRequestListener(FaceMagicInputTextRequestListener faceMagicInputTextRequestListener) {
        if (this.checker.isValid()) {
            this.inputTextRequestListener = faceMagicInputTextRequestListener;
        }
    }

    public void setMemojiListener(FaceMagicMemojiListener faceMagicMemojiListener) {
        if (this.checker.isValid()) {
            this.memojiListener = faceMagicMemojiListener;
        }
    }

    public void setPickedFaceImage(String str) {
        if (this.checker.isValid()) {
            nativeSetPickedFaceImage(this.nativeController, str);
        }
    }

    public void setReportBeautifyLogListener(FaceMagicReportBeautifyLogListener faceMagicReportBeautifyLogListener) {
        if (this.checker.isValid()) {
            this.reportBeautifyLogListener = faceMagicReportBeautifyLogListener;
            nativeSetReportBeautifyLogCallback(this.nativeController, faceMagicReportBeautifyLogListener != null);
        }
    }

    public void setTimeControl(boolean z12) {
        nativeSetTimeControl(this.nativeController, z12);
    }

    public void setUserInfo(UserInfo userInfo) {
        if (this.checker.isValid()) {
            nativeSetUserInfo(this.nativeController, userInfo.toByteArray());
        }
    }

    public void updateEffectControl(EffectControl effectControl) {
        if (this.checker.isValid()) {
            nativeUpdateEffectControl(this.nativeController, effectControl.toByteArray());
        }
    }

    public void updateEffectUsingFramePts(boolean z12) {
        if (this.checker.isValid()) {
            nativeUpdateEffectUsingFramePts(this.nativeController, z12);
        }
    }

    public void disableEffectAtSlot(EffectSlot effectSlot, EffectResource effectResource) {
        if (this.checker.isValid()) {
            if (effectResource == null) {
                nativeDisableEffect2(this.nativeController, effectSlot.getNumber(), null);
            } else {
                nativeDisableEffect2(this.nativeController, effectSlot.getNumber(), effectResource.toByteArray());
            }
        }
    }

    public void setEffectAtSlot(EffectResource effectResource, EffectSlot effectSlot, int i12) {
        if (this.checker.isValid()) {
            nativeSetEffect(this.nativeController, effectResource.toByteArray(), effectSlot.getNumber(), i12);
        }
    }

    private void onEffectDescriptionFromNative(byte[] bArr, int i12, byte[] bArr2) {
        try {
            synchronized (this.mListenerLock) {
                try {
                    if (this.faceMagicListener != null) {
                        this.faceMagicListener.onEffectDescriptionUpdated(bArr != null ? EffectDescription.parseFrom(bArr) : null, EffectSlot.forNumber(i12), bArr2 != null ? EffectResource.parseFrom(bArr2) : null);
                    }
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "FaceMagicController.class", "onEffectDescriptionFromNative", -1);
                    throw th2;
                }
            }
        } catch (InvalidProtocolBufferException e12) {
            CrashReporter.logCheckedException(e12, "FaceMagicController.class", "onEffectDescriptionFromNative", 17);
            e12.printStackTrace();
        }
    }
}
