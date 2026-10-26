package com.kwai.video.westeros;

import android.content.Context;
import androidx.annotation.Keep;
import com.kwai.camerasdk.Daenerys;
import com.kwai.camerasdk.face.FaceDetectorContext;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.render.OpengGL.EglBase;
import com.kwai.video.westeros.helpers.ValidationChecker;
import com.kwai.video.westeros.helpers.WesterosSoLoader;
import com.kwai.video.westeros.models.FeatureParams;
import com.kwai.video.westeros.models.FeatureType;
import com.yxcorp.bugly.CrashReporter;
import e.a;
import java.util.HashSet;
import java.util.Set;
import p000do.g1;
import p000do.h;
import p000do.z;

/* JADX INFO: loaded from: classes13.dex */
@Keep
public class Westeros {
    private static final String TAG = "Westeros";
    public static final String WESTEROS_EVENT_MODEL_MISSING = "WESTEROS_EVENT_MODEL_MISSING";
    private Set<WesterosPlugin> allPlugins;
    private OnBacklightDetectListener backlightDetectListener;
    private final CacheManager cacheManager;
    private Daenerys daenerys;
    private volatile boolean disposed;
    private Object listenerLock;
    private OnLowLightDetectionListener lowLightDetectionListener;
    private long nativeWesteros;
    private final ResourceManager resourceManager;
    private final UIInteractionHandler uiInteractionHandler;
    private ValidationChecker validationChecker;
    private OnWesterosEventListener westerosEventListener;
    private long westerosEventToken;

    public interface OnBacklightDetectListener {
        void onBacklightDetectorResult(boolean z12);
    }

    public interface OnLowLightDetectionListener {
        void onLowLightDetectorResult(boolean z12);
    }

    public interface OnWesterosEventListener {
        void onEvent(String str, String str2);
    }

    static {
        WesterosSoLoader.loadNative();
    }

    public Westeros(@a Context context, @a Daenerys daenerys) {
        this.listenerLock = new Object();
        this.validationChecker = new ValidationChecker() { // from class: com.kwai.video.westeros.Westeros.1
            @Override // com.kwai.video.westeros.helpers.ValidationChecker
            public boolean isValid() {
                if (Westeros.this.disposed) {
                    Log.w(Westeros.TAG, "Westeros is not valid!!");
                }
                return !Westeros.this.disposed;
            }
        };
        long jNativeCreateWesteros = nativeCreateWesteros();
        this.nativeWesteros = jNativeCreateWesteros;
        this.daenerys = daenerys;
        nativeSetDaenerys(jNativeCreateWesteros, daenerys.G());
        nativeInit(this.nativeWesteros, context);
        this.uiInteractionHandler = new UIInteractionHandler(this.nativeWesteros, this.validationChecker);
        this.resourceManager = new ResourceManager(this.nativeWesteros, this.validationChecker);
        this.allPlugins = new HashSet();
        this.cacheManager = new CacheManager(this.nativeWesteros, this.validationChecker);
    }

    private native void nativeApplyPlugin(long j12, long j13);

    private native long nativeCreateWesteros();

    private native void nativeDestroyWesteros(long j12);

    private native void nativeIgnoreSensorUpdate(long j12, boolean z12);

    private native void nativeInit(long j12, Object obj);

    private native void nativeRemovePlugin(long j12, long j13);

    private native void nativeSetBusiness(long j12, long j13);

    private native void nativeSetDaenerys(long j12, long j13);

    private native void nativeSetFaceDetectorContext(long j12, long j13, long j14, int i12);

    private native void nativeSetFeatureEnabled(long j12, int i12, boolean z12, byte[] bArr);

    private native void nativeSetShouldApplyImageModeForImage(long j12, boolean z12);

    private native void nativeSetStatsListener(long j12, WesterosStatsListener westerosStatsListener);

    private native void nativeSetSubBusiness(long j12, long j13);

    private native void nativeUnsetDaenerys(long j12);

    private native void nativeUpdateABTestKeyValuesJson(long j12, String str);

    private native void nativeUpdateBacklightThreshold(long j12, int i12);

    private native void nativeUpdateLowLightThreshold(long j12, int i12);

    private void onBacklightData(boolean z12) {
        synchronized (this.listenerLock) {
            try {
                OnBacklightDetectListener onBacklightDetectListener = this.backlightDetectListener;
                if (onBacklightDetectListener != null) {
                    onBacklightDetectListener.onBacklightDetectorResult(z12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Westeros.class", "onBacklightData", -1);
                throw th2;
            }
        }
    }

    private void onLowLightData(boolean z12) {
        synchronized (this.listenerLock) {
            try {
                OnLowLightDetectionListener onLowLightDetectionListener = this.lowLightDetectionListener;
                if (onLowLightDetectionListener != null) {
                    onLowLightDetectionListener.onLowLightDetectorResult(z12);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Westeros.class", "onLowLightData", -1);
                throw th2;
            }
        }
    }

    private void onWesterosEvent(String str, String str2) {
        synchronized (this.listenerLock) {
            try {
                OnWesterosEventListener onWesterosEventListener = this.westerosEventListener;
                if (onWesterosEventListener != null) {
                    onWesterosEventListener.onEvent(str, str2);
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Westeros.class", "onWesterosEvent", -1);
                throw th2;
            }
        }
    }

    public static void preloadLibrarys(Context context) {
        WesterosSoLoader.loadNative();
        com.kwai.camerasdk.utils.a.b();
    }

    public static void setLogParam(Daenerys.LogParam logParam) {
        Daenerys.a0(logParam);
    }

    public synchronized void applyPlugin(@a WesterosPlugin westerosPlugin) {
        if (westerosPlugin != null) {
            if (!westerosPlugin.isReleased() && this.validationChecker.isValid()) {
                nativeApplyPlugin(this.nativeWesteros, westerosPlugin.getNativePlugin());
                this.allPlugins.add(westerosPlugin);
                return;
            }
        }
        Log.e(TAG, "applyPlugin error: plugin is invalid.");
    }

    public void dispose() {
        dispose(new Runnable() { // from class: com.kwai.video.westeros.Westeros.2
            @Override // java.lang.Runnable
            public void run() {
                Log.d(Westeros.TAG, "Disposed");
            }
        });
    }

    public Set<WesterosPlugin> getAllPlugins() {
        return this.allPlugins;
    }

    public CacheManager getCacheManager() {
        if (this.validationChecker.isValid()) {
            return this.cacheManager;
        }
        return null;
    }

    public Daenerys getDaenerys() {
        if (this.validationChecker.isValid()) {
            return this.daenerys;
        }
        return null;
    }

    public ResourceManager getResourceManager() {
        if (this.validationChecker.isValid()) {
            return this.resourceManager;
        }
        return null;
    }

    public UIInteractionHandler getUiInteractionHandler() {
        if (this.validationChecker.isValid()) {
            return this.uiInteractionHandler;
        }
        return null;
    }

    public void ignoreSensorUpdate(boolean z12) {
        if (this.validationChecker.isValid()) {
            nativeIgnoreSensorUpdate(this.nativeWesteros, z12);
        }
    }

    public synchronized void removePlugin(@a WesterosPlugin westerosPlugin) {
        if (westerosPlugin != null) {
            if (!westerosPlugin.isReleased() && this.validationChecker.isValid()) {
                nativeRemovePlugin(this.nativeWesteros, westerosPlugin.getNativePlugin());
                this.allPlugins.remove(westerosPlugin);
                return;
            }
        }
        Log.e(TAG, "removePlugin error: plugin is invalid.");
    }

    public void setBusiness(h hVar) {
        if (this.validationChecker.isValid()) {
            nativeSetBusiness(this.nativeWesteros, hVar.getNumber());
        }
    }

    public synchronized void setFaceDetectorContext(FaceDetectorContext faceDetectorContext) {
        setFaceDetectorContext(faceDetectorContext, 0);
    }

    public void setFeatureEnabled(FeatureType featureType, boolean z12) {
        if (this.validationChecker.isValid()) {
            setFeatureEnabledAndParams(featureType, z12, null);
        }
    }

    public void setFeatureEnabledAndParams(FeatureType featureType, boolean z12, FeatureParams featureParams) {
        if (this.validationChecker.isValid()) {
            if (featureParams != null) {
                nativeSetFeatureEnabled(this.nativeWesteros, featureType.getNumber(), z12, featureParams.toByteArray());
            } else {
                nativeSetFeatureEnabled(this.nativeWesteros, featureType.getNumber(), z12, null);
            }
        }
    }

    public void setOnBacklightDetectListener(OnBacklightDetectListener onBacklightDetectListener) {
        synchronized (this.listenerLock) {
            try {
                this.backlightDetectListener = onBacklightDetectListener;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Westeros.class", "setOnBacklightDetectListener", -1);
                throw th2;
            }
        }
    }

    public void setOnEventListener(OnWesterosEventListener onWesterosEventListener) {
        synchronized (this.listenerLock) {
            try {
                this.westerosEventListener = onWesterosEventListener;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Westeros.class", "setOnEventListener", -1);
                throw th2;
            }
        }
    }

    public void setOnLowLightDetectionListener(OnLowLightDetectionListener onLowLightDetectionListener) {
        synchronized (this.listenerLock) {
            try {
                this.lowLightDetectionListener = onLowLightDetectionListener;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "Westeros.class", "setOnLowLightDetectionListener", -1);
                throw th2;
            }
        }
    }

    public void setSensorRate(int i12) {
        SensorUtils.setRate(i12);
    }

    public void setShouldApplyImageModeForImage(boolean z12) {
        if (this.validationChecker.isValid()) {
            nativeSetShouldApplyImageModeForImage(this.nativeWesteros, z12);
        }
    }

    public void setStatsListener(WesterosStatsListener westerosStatsListener) {
        if (this.validationChecker.isValid()) {
            nativeSetStatsListener(this.nativeWesteros, westerosStatsListener);
        }
    }

    public void setSubBusiness(g1 g1Var) {
        if (this.validationChecker.isValid()) {
            nativeSetSubBusiness(this.nativeWesteros, g1Var.getNumber());
        }
    }

    public void updateABTestKeyValuesJson(String str) {
        if (this.validationChecker.isValid()) {
            nativeUpdateABTestKeyValuesJson(this.nativeWesteros, str);
        }
    }

    public void updateBacklightThreshold(int i12) {
        if (this.validationChecker.isValid()) {
            nativeUpdateBacklightThreshold(this.nativeWesteros, i12);
        }
    }

    public void updateLowlightThreshold(int i12) {
        if (this.validationChecker.isValid()) {
            nativeUpdateLowLightThreshold(this.nativeWesteros, i12);
        }
    }

    public synchronized void dispose(Runnable runnable) {
        Log.i(TAG, "Westeros dispose.");
        this.disposed = true;
        nativeUnsetDaenerys(this.nativeWesteros);
        this.daenerys.A(runnable);
        nativeDestroyWesteros(this.nativeWesteros);
        this.uiInteractionHandler.dispose();
        Log.i(TAG, "Destroy done.");
    }

    public synchronized void setFaceDetectorContext(FaceDetectorContext faceDetectorContext, int i12) {
        if (this.validationChecker.isValid()) {
            this.daenerys.X(faceDetectorContext, i12);
            if (faceDetectorContext != null) {
                nativeSetFaceDetectorContext(this.nativeWesteros, faceDetectorContext.getNativeContext(), faceDetectorContext.getNativeSensorManager(), i12);
            } else {
                nativeSetFaceDetectorContext(this.nativeWesteros, 0L, 0L, i12);
            }
        }
    }

    public Westeros(@a Context context, @a z zVar) {
        this(context, zVar, null);
    }

    public Westeros(@a Context context, @a z zVar, EglBase.Context context2) {
        this.listenerLock = new Object();
        this.validationChecker = new ValidationChecker() { // from class: com.kwai.video.westeros.Westeros.1
            @Override // com.kwai.video.westeros.helpers.ValidationChecker
            public boolean isValid() {
                if (Westeros.this.disposed) {
                    Log.w(Westeros.TAG, "Westeros is not valid!!");
                }
                return !Westeros.this.disposed;
            }
        };
        this.nativeWesteros = nativeCreateWesteros();
        Daenerys daenerys = new Daenerys(context, zVar, context2);
        this.daenerys = daenerys;
        nativeSetDaenerys(this.nativeWesteros, daenerys.G());
        setBusiness(zVar.W());
        setSensorRate(zVar.j0());
        nativeInit(this.nativeWesteros, context);
        this.uiInteractionHandler = new UIInteractionHandler(this.nativeWesteros, this.validationChecker);
        this.resourceManager = new ResourceManager(this.nativeWesteros, this.validationChecker);
        this.allPlugins = new HashSet();
        this.cacheManager = new CacheManager(this.nativeWesteros, this.validationChecker);
    }
}
