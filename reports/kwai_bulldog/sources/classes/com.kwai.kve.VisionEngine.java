package com.kwai.kve;

import android.content.Context;
import com.getkeepsafe.relinker.ReLinker;
import com.yxcorp.bugly.CrashReporter;

/* JADX INFO: loaded from: classes16.dex */
public class VisionEngine {
    private static boolean sIsJniLoaded = false;
    static final Object sLock = new Object();
    private static volatile Handler sHandler = new DefaultHandler();

    public static class DefaultHandler implements Handler {
        private Context mContext;

        @Override // com.kwai.kve.VisionEngine.Handler
        public void loadLibrary(String str) {
            Context context = this.mContext;
            if (context != null) {
                ReLinker.loadLibrary(context, str);
            } else {
                System.loadLibrary(str);
            }
        }

        @Override // com.kwai.kve.VisionEngine.Handler
        public void setContext(Context context) {
            this.mContext = context;
        }
    }

    public interface Handler {
        void loadLibrary(String str);

        void setContext(Context context);
    }

    private VisionEngine() {
    }

    @Deprecated
    public static LutEnhancerBuilder createEnhanceBuilder() {
        loadJni();
        initLogUtil();
        return new LutEnhancerBuilder();
    }

    public static SmartCoverTaskBuilder createSmartCoverTaskBuilder() {
        loadJni();
        initLogUtil();
        return new SmartCoverTaskBuilder();
    }

    public static SmartEditTaskBuilder createSmartEditTaskBuilder() {
        loadJni();
        initLogUtil();
        return new SmartEditTaskBuilder();
    }

    public static SmartGalleryTaskBuilder createSmartGalleryTaskBuilder() {
        loadJni();
        initLogUtil();
        return new SmartGalleryTaskBuilder();
    }

    public static void init() {
        loadJni();
        initLogUtil();
    }

    protected static void initLogUtil() {
        synchronized (sLock) {
            try {
                if (!LogUtil.initialized()) {
                    LogUtil.setLogger(new GifLogger());
                    LogUtil.enableLogcat(false);
                    LogUtil.enableLogger(true);
                    LogUtil.logVersion();
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "VisionEngine.class", "initLogUtil", -1);
                throw th2;
            }
        }
    }

    public static void loadJni() {
        synchronized (sLock) {
            try {
                if (!sIsJniLoaded) {
                    sHandler.loadLibrary("visionengine");
                    sIsJniLoaded = true;
                }
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "VisionEngine.class", "loadJni", -1);
                throw th2;
            }
        }
    }

    public static void setContext(Context context) {
        synchronized (sLock) {
            try {
                sHandler.setContext(context);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "VisionEngine.class", "setContext", -1);
                throw th2;
            }
        }
    }

    public static void setHandler(Handler handler) {
        synchronized (sLock) {
            if (handler != null) {
                try {
                    sHandler = handler;
                } catch (Throwable th2) {
                    CrashReporter.logCheckedException(th2, "VisionEngine.class", "setHandler", -1);
                    throw th2;
                }
            }
        }
    }
}
