package com.kwai.video.westeros.v2.faceless;

import android.content.Context;
import com.kwai.video.westeros.WesterosPlugin;
import com.kwai.video.westeros.helpers.WesterosSoLoader;
import com.yxcorp.bugly.CrashReporter;
import org.wysaid.nativePort.CGENativeLibraryLoader;

/* JADX INFO: loaded from: classes13.dex */
public class FacelessPlugin extends WesterosPlugin {
    private static boolean sInited;
    private static boolean sStaticInited;
    private final FaceMagicController faceMagicController;

    static {
        initInternal();
        sInited = false;
        sStaticInited = false;
    }

    public FacelessPlugin(@e.a Context context) {
        init(context);
        this.faceMagicController = new FaceMagicController(this.nativePlugin, true);
    }

    public static void init(@e.a Context context) {
        if (!sInited) {
            try {
                CGENativeLibraryLoader.setClassLoader(context.getClassLoader());
                CGENativeLibraryLoader.setAppContext(context.getApplicationContext());
                sInited = true;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "FacelessPlugin.class", "init", 27);
                th2.printStackTrace();
                sInited = false;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("FacelessPlugin init failed! : ");
                sb2.append(th2.getMessage());
            }
        }
        initInternal();
    }

    private static void initInternal() {
        if (sStaticInited) {
            return;
        }
        try {
            CGENativeLibraryLoader.load();
            FacelessSoLoader.loadNative();
            WesterosSoLoader.loadLibrary("faceless-plugin");
            sStaticInited = true;
        } catch (Throwable th2) {
            CrashReporter.logCheckedException(th2, "FacelessPlugin.class", "initInternal", 43);
            th2.printStackTrace();
            sStaticInited = false;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("FacelessPlugin initInternal failed! : ");
            sb2.append(th2.getMessage());
        }
    }

    private native long nativeCreateFacelessPlugin();

    private native void nativeDestroyFacelessPlugin(long j12);

    @Override // com.kwai.video.westeros.WesterosPlugin
    protected long createNativePlugin() {
        return nativeCreateFacelessPlugin();
    }

    public FaceMagicController createNewFaceMagicController() {
        return new FaceMagicController(this.nativePlugin, false);
    }

    public FaceMagicController getFaceMagicController() {
        return this.faceMagicController;
    }

    @Override // com.kwai.video.westeros.WesterosPlugin
    public void release() {
        this.faceMagicController.release();
        super.release();
    }

    @Override // com.kwai.video.westeros.WesterosPlugin
    protected void releaseNativePlugin(long j12) {
        nativeDestroyFacelessPlugin(j12);
    }
}
