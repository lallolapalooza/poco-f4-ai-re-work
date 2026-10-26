package com.xiaomi.milab.videosdk;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import com.xiaomi.milab.videosdk.interfaces.AudioExtractCallback;
import com.xiaomi.milab.videosdk.interfaces.ExportCallback;
import com.xiaomi.milab.videosdk.interfaces.PlayCallback;
import com.xiaomi.milab.videosdk.interfaces.TimelineCallback;
import com.xiaomi.milab.videosdk.message.EventHandler;
import com.xiaomi.milab.videosdk.message.MsgProxy;
import com.xiaomi.milab.videosdk.message.MsgType;

/* JADX INFO: loaded from: classes2.dex */
public class XmsContext extends XmsNativeObject {
    public static final String TAG;
    private static XmsContext mInstance;
    private static Object mObj;
    private boolean bHasInit = false;
    private Context mContext;
    private Surface mCurrentSurface;
    private EventHandler mEventHandler;
    private int mHeight;
    private int mWidth;
    private XmsTimeline xmsTimeline;

    public class a implements SurfaceHolder.Callback {
        public final /* synthetic */ XmsTimeline d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ XmsSurface f9959e;
        public final /* synthetic */ int m;

        public a(XmsTimeline xmsTimeline, XmsSurface xmsSurface, int i10) {
            this.d = xmsTimeline;
            this.f9959e = xmsSurface;
            this.m = i10;
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceChanged(SurfaceHolder surfaceHolder, int i10, int i11, int i12) {
            Surface surface = surfaceHolder.getSurface();
            XmsContext xmsContext = XmsContext.this;
            xmsContext.mCurrentSurface = surface;
            Surface surface2 = surfaceHolder.getSurface();
            XmsTimeline xmsTimeline = this.d;
            xmsTimeline.attachSurface(surface2);
            XmsSurface xmsSurface = this.f9959e;
            xmsTimeline.setProfile(xmsSurface.getWidth(), xmsSurface.getHeight(), this.m);
            xmsContext.initContext();
            xmsTimeline.surfaceChanged(xmsSurface.getSurface(), i11, i12);
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }
    }

    public class b implements TextureView.SurfaceTextureListener {
        public final /* synthetic */ XmsTextureView d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ XmsTimeline f9961e;
        public final /* synthetic */ int m;

        public b(XmsTextureView xmsTextureView, XmsTimeline xmsTimeline, int i10) {
            this.d = xmsTextureView;
            this.f9961e = xmsTimeline;
            this.m = i10;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i10, int i11) {
            Log.i(XmsContext.TAG, "onSurfaceTextureAvailable width " + i10 + "height " + i11);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i10, int i11) {
            Log.i(XmsContext.TAG, "onSurfaceTextureSizeChanged width " + i10 + "height " + i11);
            XmsContext xmsContext = XmsContext.this;
            boolean z = xmsContext.bHasInit;
            XmsTextureView xmsTextureView = this.d;
            XmsTimeline xmsTimeline = this.f9961e;
            if (z) {
                Surface surface = new Surface(surfaceTexture);
                xmsContext.mCurrentSurface = surface;
                xmsTextureView.setSurface(surface);
                xmsTimeline.surfaceChanged(surface, i10, i11);
                xmsContext.mWidth = i10;
                xmsContext.mHeight = i11;
                return;
            }
            Surface surface2 = new Surface(surfaceTexture);
            xmsContext.mCurrentSurface = surface2;
            xmsTextureView.setSurface(surface2);
            xmsTimeline.attachSurface(surface2);
            xmsContext.mWidth = i10;
            xmsContext.mHeight = i11;
            xmsTimeline.setProfile(i10, i11, this.m);
            xmsContext.initContext();
            xmsContext.bHasInit = true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    static {
        System.loadLibrary("MiVideoSDK");
        TAG = "XmsContext";
        mObj = new Object();
    }

    private XmsContext() {
        createContext();
    }

    private void createContext() {
        this.xmsTimeline = null;
        setNativeObject(nativeCreateContext());
    }

    public static XmsContext getInstance() {
        XmsContext xmsContext;
        synchronized (mObj) {
            if (mInstance == null) {
                mInstance = new XmsContext();
            }
            xmsContext = mInstance;
        }
        return xmsContext;
    }

    private void initEventHandler() {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mEventHandler = new EventHandler(looperMyLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(mainLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        MsgProxy.registerMessageHandler(10001, this.mEventHandler);
        Log.i(TAG, "initEventHandler registerMessageHandler");
    }

    private static native void nativeAttachPreviewGLThread(long j);

    private static native void nativeCancelExport(long j, long j10);

    private static native long nativeCreateContext();

    private static native long nativeCreateTimeline(long j);

    private static native void nativeExportTimeline(long j, long j10, String str, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18);

    private static native void nativeExportTimelineCbr(long j, long j10, String str, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18);

    private static native void nativeExportTimelineFd(long j, long j10, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20);

    private static native String nativeGetSdkVersion();

    private static native long nativeGetTimelineCurrentPosition(long j, long j10);

    private static native void nativeInitContext(long j);

    private static native void nativePauseTimeline(long j, long j10);

    private static native void nativePauseToBackground(long j, long j10);

    private static native void nativePlayTimeline(long j, long j10);

    private static native void nativePlayTimelineByStart(long j, long j10, int i10);

    private static native void nativePlayTimelinePrepare(long j, long j10, int i10);

    private static native void nativeRelease(long j);

    private static native void nativeRemoveTimeline(long j, long j10);

    private static native void nativeResumeTimeline(long j, long j10);

    private static native void nativeResumeToForeground(long j, long j10);

    private static native void nativeSeekTimeline(long j, long j10, long j11, int i10);

    private static native int nativeSetupBeauty(long j, String str, String str2, String str3, int i10);

    private static native int nativeSetupDolbyVision(long j, boolean z, String str);

    private static native int nativeShutDownBeauty(long j);

    private static native void nativeSkipPauseTimeline(long j, long j10);

    public void attachPreviewGLThread() {
        if (checkNull("attachPreviewGLThread")) {
            return;
        }
        nativeAttachPreviewGLThread(getNativeObject());
    }

    public void attachSurface(XmsTimeline xmsTimeline, XmsSurface xmsSurface, int i10) {
        if (checkNull("attachSurface") || xmsTimeline == null || xmsTimeline.checkNull("attachSurface")) {
            return;
        }
        if (xmsSurface != null && xmsSurface.isCreated()) {
            xmsTimeline.attachSurface(xmsSurface.getSurface());
            xmsTimeline.setProfile(xmsSurface.getWidth(), xmsSurface.getHeight(), i10);
            initContext();
        }
        xmsSurface.getHolder().addCallback(new a(xmsTimeline, xmsSurface, i10));
    }

    public void attachTexture(XmsTimeline xmsTimeline, XmsTextureView xmsTextureView, int i10, int i11, int i12) {
        Log.i(TAG, "attachTexture width " + this.mWidth + " height " + this.mHeight);
        if (checkNull("attachTexture") || xmsTimeline == null || xmsTimeline.checkNull("attachTexture")) {
            return;
        }
        if (xmsTextureView != null && xmsTextureView.isCreated()) {
            xmsTimeline.attachSurface(xmsTextureView.getSurface());
            this.mCurrentSurface = xmsTextureView.getSurface();
            xmsTimeline.setProfile(i10, i11, i12);
            this.mWidth = i10;
            this.mHeight = i11;
            initContext();
            this.bHasInit = true;
        }
        xmsTextureView.setSurfaceTextureListener(new b(xmsTextureView, xmsTimeline, i12));
    }

    public void cancelExport(XmsTimeline xmsTimeline) {
        if (checkNull("cancelExport") || xmsTimeline == null || xmsTimeline.checkNull("cancelExport")) {
            return;
        }
        nativeCancelExport(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public XmsTimeline createTimeline() {
        if (checkNull("createTimeline")) {
            return null;
        }
        XmsTimeline xmsTimelineCreateTimeline = XmsTimeline.createTimeline();
        this.xmsTimeline = xmsTimelineCreateTimeline;
        return xmsTimelineCreateTimeline;
    }

    public long createTimelineReal() {
        return nativeCreateTimeline(getNativeObject());
    }

    public void exportTimeline(XmsTimeline xmsTimeline, String str, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
        if (checkNull("exportTimeline") || xmsTimeline == null || xmsTimeline.checkNull("exportTimeline")) {
            return;
        }
        nativeExportTimeline(getNativeObject(), xmsTimeline.getNativeObject(), str, i10, i11, i12, i13, i14, i15, i16, i17, i18);
    }

    public void exportTimelineCbr(XmsTimeline xmsTimeline, String str, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18) {
        if (checkNull("exportTimelineCbr") || xmsTimeline == null || xmsTimeline.checkNull("exportTimelineCbr")) {
            return;
        }
        nativeExportTimelineCbr(getNativeObject(), xmsTimeline.getNativeObject(), str, i10, i11, i12, i13, i14, i15, i16, i17, i18);
    }

    public void flushTimeline(XmsTimeline xmsTimeline) {
        if (checkNull("flushTimeline") || xmsTimeline == null || xmsTimeline.checkNull("flushTimeline") || this.mCurrentSurface == null) {
            return;
        }
        xmsTimeline.updateRender();
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getSdkVersion() {
        return nativeGetSdkVersion();
    }

    public long getTimelineCurrentPosition(XmsTimeline xmsTimeline) {
        if (checkNull("getTimelineCurrentPosition") || xmsTimeline == null || xmsTimeline.checkNull("getTimelineCurrentPosition")) {
            return -1L;
        }
        return nativeGetTimelineCurrentPosition(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public XmsTimeline getXmsTimeline() {
        if (checkNull("getXmsTimeline")) {
            return null;
        }
        return this.xmsTimeline;
    }

    public void initContext() {
        nativeInitContext(getNativeObject());
    }

    public void initLister() {
        initEventHandler();
    }

    public void pause(XmsTimeline xmsTimeline) {
        if (checkNull("pause") || xmsTimeline == null || xmsTimeline.checkNull("pause")) {
            return;
        }
        nativePauseTimeline(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public void pauseToBackground(XmsTimeline xmsTimeline) {
        if (checkNull("pauseToBackground") || xmsTimeline == null || xmsTimeline.checkNull("pauseToBackground")) {
            return;
        }
        nativePauseToBackground(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public void playTimeline(XmsTimeline xmsTimeline) {
        if (checkNull("playTimeline") || xmsTimeline == null || xmsTimeline.checkNull("playTimeline")) {
            return;
        }
        nativePlayTimeline(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public void prepareTimeline(XmsTimeline xmsTimeline, int i10) {
        if (checkNull("prepareTimeline") || xmsTimeline == null || xmsTimeline.checkNull("prepareTimeline")) {
            return;
        }
        nativePlayTimelinePrepare(getNativeObject(), xmsTimeline.getNativeObject(), i10);
    }

    public void release() {
        synchronized (mObj) {
            nativeRelease(getNativeObject());
            mInstance = null;
        }
        MsgProxy.destory();
        releaseInner();
    }

    @Override // com.xiaomi.milab.videosdk.XmsNativeObject
    public void releaseInner() {
        super.releaseInner();
        XmsTimeline xmsTimeline = this.xmsTimeline;
        if (xmsTimeline != null) {
            xmsTimeline.releaseInner();
            this.xmsTimeline = null;
        }
    }

    public void removeTimeline(XmsTimeline xmsTimeline) {
        if (checkNull("removeTimeline") || xmsTimeline == null || xmsTimeline.checkNull("removeTimeline")) {
            return;
        }
        nativeRemoveTimeline(getNativeObject(), xmsTimeline.getNativeObject());
        xmsTimeline.releaseInner();
    }

    public void resume(XmsTimeline xmsTimeline) {
        if (checkNull("resume") || xmsTimeline == null || xmsTimeline.checkNull("resume")) {
            return;
        }
        nativeResumeTimeline(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public void resumeToForeground(XmsTimeline xmsTimeline) {
        if (checkNull("resumeToForeground") || xmsTimeline == null || xmsTimeline.checkNull("resumeToForeground")) {
            return;
        }
        nativeResumeToForeground(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public void seekTimeline(XmsTimeline xmsTimeline, long j, int i10) {
        if (checkNull("seekTimeline") || xmsTimeline == null || xmsTimeline.checkNull("seekTimeline")) {
            return;
        }
        nativeSeekTimeline(getNativeObject(), xmsTimeline.getNativeObject(), j, i10);
    }

    public void setAudioExtractCallback(AudioExtractCallback audioExtractCallback) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler == null) {
            Log.i(TAG, "setAudioExtractCallback mEventHandler null");
            return;
        }
        MsgProxy.registerMessageHandler(MsgType.XMSAUDIOEXTRACT, eventHandler);
        Log.i(TAG, "setAudioExtractCallback ");
        this.mEventHandler.setAudioExtractCallback(audioExtractCallback);
    }

    public void setContext(Context context) {
        Log.i(TAG, "setContext" + context.toString());
        this.mContext = context;
    }

    public void setExportCallback(ExportCallback exportCallback) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler == null) {
            Log.i(TAG, "setExportCallback mEventHandler null");
            return;
        }
        MsgProxy.registerMessageHandler(10002, eventHandler);
        Log.i(TAG, "setExportCallback ");
        this.mEventHandler.setExportCallback(exportCallback);
    }

    public void setPlayCallback(PlayCallback playCallback) {
        if (this.mEventHandler == null) {
            Log.i(TAG, "setPlayCallback mEventHandler null");
        } else {
            Log.i(TAG, "setPlayCallback ");
            this.mEventHandler.setPlayCallback(playCallback);
        }
    }

    public void setPreviewRecordCallback(ExportCallback exportCallback) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler == null) {
            Log.i(TAG, "setExportCallback mEventHandler null");
            return;
        }
        MsgProxy.registerMessageHandler(MsgType.XMSPREVIEWEXPORT, eventHandler);
        Log.i(TAG, "setPreviewExportCallback ");
        this.mEventHandler.setPreviewExportCallback(exportCallback);
    }

    public void setTimelineCallback(TimelineCallback timelineCallback) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler == null) {
            Log.i(TAG, "setTimelineCallback mEventHandler null");
            return;
        }
        MsgProxy.registerMessageHandler(MsgType.XMSTIMELINE, eventHandler);
        Log.i(TAG, "setTimelineCallback ");
        this.mEventHandler.setTimelineCallback(timelineCallback);
    }

    public int setupBeauty(String str, String str2, String str3, int i10) {
        if (checkNull("setupBeauty")) {
            return -1;
        }
        return nativeSetupBeauty(getNativeObject(), str, str2, str3, i10);
    }

    public int setupDolbyVision(boolean z, String str) {
        if (checkNull("setupDolbyVision")) {
            return -1;
        }
        return nativeSetupDolbyVision(getNativeObject(), z, str);
    }

    public int shutDownBeauty() {
        if (checkNull("shutDown")) {
            return -1;
        }
        return nativeShutDownBeauty(getNativeObject());
    }

    public void skipPause(XmsTimeline xmsTimeline) {
        if (checkNull("skipPause") || xmsTimeline == null || xmsTimeline.checkNull("skipPause")) {
            return;
        }
        nativeSkipPauseTimeline(getNativeObject(), xmsTimeline.getNativeObject());
    }

    public void stop(XmsTimeline xmsTimeline) {
        if (checkNull("stop") || xmsTimeline == null || xmsTimeline.checkNull("stop")) {
            return;
        }
        xmsTimeline.stop();
    }

    public void unRegisterMessageHandler() {
        Log.i(TAG, "unRegisterMessageHandler");
        MsgProxy.unRegisterMessageHandler(10001, this.mEventHandler);
        MsgProxy.unRegisterMessageHandler(MsgType.XMSPREVIEWEXPORT, this.mEventHandler);
        MsgProxy.unRegisterMessageHandler(10002, this.mEventHandler);
        MsgProxy.unRegisterMessageHandler(MsgType.XMSTRANSCODE, this.mEventHandler);
        MsgProxy.unRegisterMessageHandler(MsgType.XMSPREVIEWEXPORT, this.mEventHandler);
    }

    public void exportTimeline(XmsTimeline xmsTimeline, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20) {
        if (checkNull("exportTimeline_fd") || xmsTimeline == null || xmsTimeline.checkNull("exportTimeline_fd")) {
            return;
        }
        nativeExportTimelineFd(getNativeObject(), xmsTimeline.getNativeObject(), i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20);
    }

    public void playTimeline(XmsTimeline xmsTimeline, int i10) {
        if (checkNull("playTimeline_pos") || xmsTimeline == null || xmsTimeline.checkNull("playTimeline_pos")) {
            return;
        }
        nativePlayTimelineByStart(getNativeObject(), xmsTimeline.getNativeObject(), i10);
    }

    public void attachTexture(XmsTimeline xmsTimeline, XmsTextureView xmsTextureView, int i10) {
        Log.i(TAG, "attachTexture width " + this.mWidth + " height " + this.mHeight);
        attachTexture(xmsTimeline, xmsTextureView, xmsTextureView.getWidth(), xmsTextureView.getHeight(), i10);
    }
}
