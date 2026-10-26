package com.kwai.video.ksmedialivekit;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.kwai.camerasdk.Daenerys;
import com.kwai.camerasdk.MediaCallback;
import com.kwai.camerasdk.video.VideoFrame;
import com.kwai.video.arya.Arya;
import com.kwai.video.arya.AryaInitConfig;
import com.kwai.video.arya.AryaManager;
import com.kwai.video.arya.QosInfo;
import com.kwai.video.arya.SignalMessageHandler;
import com.kwai.video.arya.observers.AryaCallObserver;
import com.kwai.video.arya.observers.AryaLogObserver;
import com.kwai.video.arya.observers.AryaQosObserver;
import com.kwai.video.ksmedialivekit.network.NetworkRequester;
import com.kwai.yoda.model.LifecycleEvent;
import com.yxcorp.bugly.CrashReporter;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes8.dex */
public class LivePushClient implements PushDestinationStrategy.PushDelegate {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Daenerys f31356c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private sx.d f31357d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Arya f31358e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Context f31359f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private NetworkRequester f31360g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private KSMediaLiveKit.MediaLiveStatusListener f31361h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private KSMediaLiveKit.MediaLiveErrorListener f31362i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private KSMediaLiveKit.MediaLiveDebugInfoListener f31363j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private KSMediaLiveKit.MediaLiveEventListener f31364k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private LongConnectionDelegate f31367n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private ux.c f31368o;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private Handler f31371r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f31372s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AtomicInteger f31354a = new AtomicInteger();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private AtomicInteger f31355b = new AtomicInteger();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private volatile boolean f31365l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private PushDestinationStrategy f31366m = new PushCdnStrategy();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private QosInfo f31369p = new QosInfo();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Object f31370q = new Object();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private MediaCallback f31373t = new MediaCallback() { // from class: com.kwai.video.ksmedialivekit.LivePushClient.1
        @Override // com.kwai.camerasdk.MediaCallback
        protected void onVideoFrame(VideoFrame videoFrame) {
            if (LivePushClient.this.f31358e != null) {
                LivePushClient.this.f31358e.inputRawVideo(videoFrame.data.byteBuffer, new Arya.VideoFrameAttribute(videoFrame.yuv_format, videoFrame.width, videoFrame.height, videoFrame.timestamp, videoFrame.attributes.f().m(), videoFrame.attributes.b(), ""));
            }
        }
    };

    public LivePushClient(Context context, NetworkRequester networkRequester, LongConnectionDelegate longConnectionDelegate, sx.d dVar) {
        AryaInitConfig.setSoLoader(new AryaInitConfig.AryaSoLoader() { // from class: com.kwai.video.ksmedialivekit.c
            @Override // com.kwai.video.arya.AryaInitConfig.AryaSoLoader
            public final void loadLibrary(String str) {
                LiveKitSoLoader.loadLibrary(str);
            }
        });
        AryaManager.LogParam logParam = new AryaManager.LogParam();
        logParam.logLevel = ux.a.f();
        logParam.logCb = new AryaLogObserver() { // from class: com.kwai.video.ksmedialivekit.e
            @Override // com.kwai.video.arya.observers.AryaLogObserver
            public final void onLog(String str) {
                ux.a.e("LivePushClient", str);
            }
        };
        AryaManager.setLogParam(logParam);
        this.f31371r = new Handler(Looper.getMainLooper());
        this.f31368o = new ux.c(this);
        this.f31357d = dVar;
        this.f31359f = context;
        this.f31367n = longConnectionDelegate;
        this.f31360g = networkRequester;
        this.f31358e = AryaManager.getInstance().createArya(this.f31359f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(String str) {
        this.f31363j.onMessage(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(byte[] bArr) {
        ux.a.b("LivePushClient", "sendSignalMessage");
        LongConnectionDelegate longConnectionDelegate = this.f31367n;
        if (longConnectionDelegate != null) {
            longConnectionDelegate.sendVoipSignal(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.f31365l) {
            ux.a.b("LivePushClient", "Manual Stopped, Do not Retry");
            return;
        }
        ux.a.e("LivePushClient", "handlePushFailed");
        j(3);
        int i12 = this.f31372s;
        if (i12 >= 1) {
            j(4);
        } else {
            this.f31372s = i12 + 1;
            this.f31366m.handlePushFailed(this, new NetworkRequester.Listener() { // from class: com.kwai.video.ksmedialivekit.LivePushClient.3
                @Override // com.kwai.video.ksmedialivekit.network.NetworkRequester.Listener
                public void onError(int i13, String str) {
                    LivePushClient.this.j(4);
                }

                @Override // com.kwai.video.ksmedialivekit.network.NetworkRequester.Listener
                public void onSuccess(xx.a aVar) {
                    synchronized (LivePushClient.this.f31370q) {
                        try {
                            LivePushClient.this.f31358e.updateLiveStreamRtmpUrl(LivePushClient.this.f31357d.B());
                            LivePushClient.this.j(2);
                        } catch (Throwable th2) {
                            CrashReporter.logCheckedException(th2, "LivePushClient$3.class", "onSuccess", -1);
                            throw th2;
                        }
                    }
                }
            });
        }
    }

    private void s() {
        this.f31368o.d(this.f31357d.y()).g(getPushType()).h(this.f31369p.getDroppedVideoFrames()).i(this.f31357d.B()).c(this.f31369p.getUploadedKByte()).b(this.f31372s).m(this.f31369p.getSdkVersion()).o(System.currentTimeMillis()).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(int i12, String str) {
        this.f31362i.onError(i12, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w() {
        this.f31361h.onStatus(this.f31354a.get(), 0, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(int i12, final String str) {
        ux.a.b("LivePushClient", "qosType: " + i12 + " message: " + str);
        if (this.f31363j != null) {
            zx.c.c(this.f31371r, new Runnable() { // from class: com.kwai.video.ksmedialivekit.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f31439a.l(str);
                }
            });
        }
    }

    @Override // com.kwai.video.ksmedialivekit.PushDestinationStrategy.PushDelegate
    public Arya arya() {
        return this.f31358e;
    }

    @Override // com.kwai.video.ksmedialivekit.PushDestinationStrategy.PushDelegate
    public sx.d config() {
        return this.f31357d;
    }

    public void destroy() {
        synchronized (this.f31370q) {
            try {
                this.f31366m.destroy();
                AryaManager.getInstance().destroyArya(this.f31358e);
                AryaManager.setLogParam(null);
                AryaInitConfig.setSoLoader(null);
                this.f31367n.disconnect();
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "LivePushClient.class", "destroy", -1);
                throw th2;
            }
        }
    }

    @Override // com.kwai.video.ksmedialivekit.PushDestinationStrategy.PushDelegate
    public void fallbackToCdn() {
        this.f31366m = new PushCdnStrategy();
    }

    public long getEncodedFrameCount() {
        return this.f31369p.getEncodedFrames();
    }

    public PushDestinationStrategy getPushDestinationStrategy() {
        return this.f31366m;
    }

    public int getPushType() {
        return this.f31366m instanceof PushOriginStrategy ? 2 : 1;
    }

    public long getUploadedKBytes() {
        return this.f31369p.getUploadedKByte();
    }

    public boolean hasManualStopped() {
        return this.f31365l;
    }

    void i() {
        if (this.f31361h != null) {
            zx.c.c(this.f31371r, new Runnable() { // from class: com.kwai.video.ksmedialivekit.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f31435a.w();
                }
            });
        }
    }

    public void initArya() {
        synchronized (this.f31370q) {
            try {
                this.f31358e.init(new SignalMessageHandler() { // from class: com.kwai.video.ksmedialivekit.d
                    @Override // com.kwai.video.arya.SignalMessageHandler
                    public final void sendSignalMessage(byte[] bArr) {
                        this.f31432a.m(bArr);
                    }
                }, new AryaCallObserver() { // from class: com.kwai.video.ksmedialivekit.LivePushClient.2
                    @Override // com.kwai.video.arya.observers.AryaCallObserver
                    public void onMediaServerInfo(String str, String str2, int i12, boolean z12) {
                        ux.a.b("LivePushClient", "callId: " + str + " ip: " + str2 + " port: " + i12 + " isLiveStream: " + z12);
                    }

                    @Override // com.kwai.video.arya.observers.AryaCallObserver
                    public void onNotify(String str, int i12) {
                        ux.a.b("LivePushClient", "callId: " + str + " type: " + i12);
                        if (i12 == 5) {
                            LivePushClient.this.o(1, "");
                        } else if (i12 == 22) {
                            LivePushClient.this.n();
                        } else {
                            if (i12 != 23) {
                                return;
                            }
                            LivePushClient.this.j(4);
                        }
                    }
                }, new AryaQosObserver() { // from class: com.kwai.video.ksmedialivekit.f
                    @Override // com.kwai.video.arya.observers.AryaQosObserver
                    public final void onQosEventUpdated(int i12, String str) {
                        this.f31434a.x(i12, str);
                    }
                });
                this.f31358e.updateConfig(this.f31357d.I());
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "LivePushClient.class", "initArya", -1);
                throw th2;
            }
        }
    }

    void j(int i12) {
        this.f31354a.set(i12);
        i();
    }

    void k(final int i12, final String str) {
        if (this.f31362i != null) {
            zx.c.c(this.f31371r, new Runnable() { // from class: com.kwai.video.ksmedialivekit.h
                @Override // java.lang.Runnable
                public final void run() {
                    this.f31436a.t(i12, str);
                }
            });
        }
    }

    @Override // com.kwai.video.ksmedialivekit.PushDestinationStrategy.PushDelegate
    public NetworkRequester networkRequester() {
        return this.f31360g;
    }

    void o(int i12, String str) {
        KSMediaLiveKit.MediaLiveEventListener mediaLiveEventListener = this.f31364k;
        if (mediaLiveEventListener != null) {
            mediaLiveEventListener.onEvent(i12, str);
        }
    }

    public void pause() {
        synchronized (this.f31370q) {
            try {
                if (this.f31354a.get() == 0) {
                    return;
                }
                this.f31358e.pause();
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "LivePushClient.class", LifecycleEvent.PAUSE, -1);
                throw th2;
            }
        }
    }

    public void postReceivedSignalingMessage(byte[] bArr) {
        this.f31358e.postReceivedSignalingMessage(bArr);
    }

    public void resume() {
        synchronized (this.f31370q) {
            try {
                if (this.f31354a.get() == 0) {
                    return;
                }
                this.f31358e.resume();
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "LivePushClient.class", LifecycleEvent.RESUME, -1);
                throw th2;
            }
        }
    }

    public void setMediaLiveDebugInfoListener(KSMediaLiveKit.MediaLiveDebugInfoListener mediaLiveDebugInfoListener) {
        this.f31363j = mediaLiveDebugInfoListener;
    }

    public void setMediaLiveErrorListener(KSMediaLiveKit.MediaLiveErrorListener mediaLiveErrorListener) {
        this.f31362i = mediaLiveErrorListener;
    }

    public void setMediaLiveEventListener(KSMediaLiveKit.MediaLiveEventListener mediaLiveEventListener) {
        this.f31364k = mediaLiveEventListener;
    }

    public void setMediaLiveStatusListener(KSMediaLiveKit.MediaLiveStatusListener mediaLiveStatusListener) {
        this.f31361h = mediaLiveStatusListener;
    }

    public void setMirror(boolean z12) {
        synchronized (this.f31370q) {
            try {
                Arya arya = this.f31358e;
                if (arya == null) {
                    return;
                }
                arya.setVideoMirror(z12);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "LivePushClient.class", "setMirror", -1);
                throw th2;
            }
        }
    }

    public void setPushDestinationStrategy(PushDestinationStrategy pushDestinationStrategy) {
        this.f31366m = pushDestinationStrategy;
    }

    public void startPush(@e.a Daenerys daenerys) {
        if (this.f31354a.get() != 1) {
            ux.a.b("LivePushClient", "MediaLiveKit Status Not Ready");
            k(2, "MediaLiveKit Status Not Ready");
            return;
        }
        if (!this.f31367n.isConnected() && this.f31355b.get() == 2) {
            ux.a.b("LivePushClient", "Long Connection is not connected");
            k(2, "MediaLiveKit Status Not Ready");
            return;
        }
        if (this.f31354a.get() == 2) {
            return;
        }
        this.f31368o.j();
        this.f31368o.l(System.currentTimeMillis());
        this.f31356c = daenerys;
        daenerys.b0(this.f31357d.s(), this.f31357d.u(), this.f31357d.v(), this.f31373t);
        synchronized (this.f31370q) {
            try {
                this.f31366m.startPush(this);
                j(2);
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "LivePushClient.class", "startPush", -1);
                throw th2;
            }
        }
    }

    public void stopPush() {
        synchronized (this.f31370q) {
            try {
                if (this.f31354a.get() != 2) {
                    return;
                }
                this.f31365l = true;
                this.f31358e.stopLiveStream("User Hangup");
                j(5);
                this.f31360g.c(this.f31357d.y(), null);
                this.f31368o.n();
                s();
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "LivePushClient.class", "stopPush", -1);
                throw th2;
            }
        }
    }

    public void updateQosInfo() {
        QosInfo qosInfo = this.f31358e.getQosInfo();
        this.f31369p = qosInfo;
        if (qosInfo == null) {
            this.f31369p = new QosInfo();
        }
    }
}
