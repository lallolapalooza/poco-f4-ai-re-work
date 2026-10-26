package com.kwai.video.ksmedialivekit;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.kwai.camerasdk.Daenerys;
import com.kwai.middleware.azeroth.link.LinkStatusEvent;
import com.kwai.video.arya.QosInfo;
import com.kwai.video.ksmedialivekit.network.NetworkRequester;
import com.kwai.yoda.model.LifecycleEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class KwaiMediaLiveKit implements KSMediaLiveKit {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private sx.d f31324a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private sx.b f31325b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f31326c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private NetworkRequester f31327d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @e.a
    private LivePushClient f31328e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private LongConnectionDelegate f31329f;

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Daenerys f31330a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Context f31331b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private sx.b f31332c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private LongConnectionDelegate f31333d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private File f31334e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f31335f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f31336g = true;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f31337h;

        public Builder(Context context, sx.b bVar) {
            this.f31331b = context.getApplicationContext();
            this.f31332c = bVar;
        }

        private void b() {
            sx.b bVar;
            if (this.f31331b == null || (bVar = this.f31332c) == null || TextUtils.isEmpty(bVar.a()) || TextUtils.isEmpty(this.f31332c.c()) || TextUtils.isEmpty(this.f31332c.d()) || TextUtils.isEmpty(this.f31332c.f())) {
                throw new NullPointerException("context or account info is null");
            }
        }

        public KwaiMediaLiveKit build() {
            b();
            return new KwaiMediaLiveKit(this);
        }

        public Builder caption(String str) {
            this.f31335f = str;
            return this;
        }

        public Builder coverFile(File file) {
            this.f31334e = file;
            return this;
        }

        public Builder daenerys(Daenerys daenerys) {
            this.f31330a = daenerys;
            return this;
        }

        public Builder host(String str) {
            this.f31337h = str;
            return this;
        }

        public Builder isHd(boolean z12) {
            this.f31336g = z12;
            return this;
        }

        public Builder longConnection(LongConnectionDelegate longConnectionDelegate) {
            this.f31333d = longConnectionDelegate;
            return this;
        }
    }

    private class InterceptorChainImpl implements tx.a.InterfaceC1739a, tx.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @e.a
        private List<tx.a> f31338a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f31339b;

        private InterceptorChainImpl() {
            this.f31338a = new ArrayList();
        }

        public void add(tx.a aVar) {
            this.f31338a.add(aVar);
        }

        @Override // tx.a.b
        @e.a
        public Context appContext() {
            return KwaiMediaLiveKit.this.f31326c;
        }

        public void changeStatus(int i12) {
            KwaiMediaLiveKit.this.b(i12);
        }

        @Override // tx.a.b
        @e.a
        public sx.d config() {
            return KwaiMediaLiveKit.this.f31324a;
        }

        @Override // tx.a.InterfaceC1739a
        public void error(int i12, String str) {
            changeStatus(4);
            KwaiMediaLiveKit.this.c(1, str);
        }

        public void finish() {
            changeStatus(1);
            this.f31338a.clear();
        }

        @Override // tx.a.b
        @e.a
        public LongConnectionDelegate longConnection() {
            return KwaiMediaLiveKit.this.f31329f;
        }

        @Override // tx.a.b
        @e.a
        public NetworkRequester networkRequester() {
            return KwaiMediaLiveKit.this.f31327d;
        }

        @Override // tx.a.InterfaceC1739a
        public void next() {
            if (this.f31339b >= this.f31338a.size()) {
                finish();
            } else {
                this.f31338a.get(this.f31339b).a(this, this);
                this.f31339b++;
            }
        }

        @Override // tx.a.b
        @e.a
        public LivePushClient pushClient() {
            return KwaiMediaLiveKit.this.f31328e;
        }
    }

    public KwaiMediaLiveKit(Builder builder) {
        Daenerys unused = builder.f31330a;
        this.f31326c = builder.f31331b;
        this.f31325b = builder.f31332c;
        this.f31329f = builder.f31333d;
        sx.d dVar = new sx.d(this.f31325b);
        this.f31324a = dVar;
        dVar.h(builder.f31334e);
        this.f31324a.q(builder.f31335f);
        this.f31324a.o(builder.f31336g);
        this.f31324a.t(builder.f31337h);
        com.kwai.video.ksmedialivekit.network.impl.b bVar = new com.kwai.video.ksmedialivekit.network.impl.b(this.f31324a);
        this.f31327d = bVar;
        this.f31328e = new LivePushClient(this.f31326c, bVar, this.f31329f, this.f31324a);
        b(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i12) {
        this.f31328e.j(i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i12, String str) {
        this.f31328e.k(i12, str);
    }

    public static String stringOf(int i12) {
        if (i12 == 0) {
            return "INIT";
        }
        if (i12 == 1) {
            return "READY";
        }
        if (i12 == 2) {
            return "PUSH_SUCCESS";
        }
        if (i12 == 3) {
            return "RETRYING";
        }
        if (i12 != 4) {
            return i12 != 5 ? GrsBaseInfo.CountryCodeSource.UNKNOWN : "PUSH_STOPPED";
        }
        return LinkStatusEvent.LINK_STATUS_ERROR;
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void addQosInfoListener(KSMediaLiveKit.MediaLiveQosInfoListener mediaLiveQosInfoListener) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void addVideoMixSourceWithPosition(String str, sx.c.b bVar) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void cleanSoundEffectCache() {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void destroy() {
        ux.a.e("KwaiMediaLiveKit", "destroy");
        this.f31328e.destroy();
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void disableHeadphoneMonitor() {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void enableHeadphoneMonitor(boolean z12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void enableInsertActiveSpeakersInLiveStream(boolean z12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public int getPushType() {
        LivePushClient livePushClient = this.f31328e;
        if (livePushClient != null) {
            return livePushClient.getPushType();
        }
        return 0;
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public QosInfo getQosInfo() {
        return null;
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public int getRetryCount() {
        return 0;
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public boolean isSupportHeadphoneMonitor(boolean z12) {
        return false;
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void muteRemoteAudioStream(String str, boolean z12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void pause() {
        ux.a.e("KwaiMediaLiveKit", LifecycleEvent.PAUSE);
        this.f31328e.pause();
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void pauseBgm() {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void playSoundEffect(String str, KSMediaLiveKit.MediaLiveBgmListener mediaLiveBgmListener) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void prepareToPush() {
        ux.a.e("KwaiMediaLiveKit", "prepareToPush");
        InterceptorChainImpl interceptorChainImpl = new InterceptorChainImpl();
        interceptorChainImpl.add(new tx.c());
        interceptorChainImpl.add(new tx.b());
        interceptorChainImpl.add(new tx.d());
        interceptorChainImpl.next();
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void removeQosInfoListener(KSMediaLiveKit.MediaLiveQosInfoListener mediaLiveQosInfoListener) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void replaceVideoWithBitmap(Bitmap bitmap) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void resume() {
        ux.a.e("KwaiMediaLiveKit", LifecycleEvent.RESUME);
        this.f31328e.resume();
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void resumeBgm() {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void seekBgm(int i12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setAudioInputVolume(float f12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setBgmPitch(int i12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setBgmVolume(float f12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setEnableNoiseSuppression(boolean z12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMediaLiveActiveSpeakerListener(KSMediaLiveKit.MediaLiveActiveSpeakerChangedListener mediaLiveActiveSpeakerChangedListener) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMediaLiveDebugInfoListener(KSMediaLiveKit.MediaLiveDebugInfoListener mediaLiveDebugInfoListener) {
        this.f31328e.setMediaLiveDebugInfoListener(mediaLiveDebugInfoListener);
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMediaLiveErrorListener(KSMediaLiveKit.MediaLiveErrorListener mediaLiveErrorListener) {
        this.f31328e.setMediaLiveErrorListener(mediaLiveErrorListener);
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMediaLiveEventListener(KSMediaLiveKit.MediaLiveEventListener mediaLiveEventListener) {
        this.f31328e.setMediaLiveEventListener(mediaLiveEventListener);
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMediaLiveMediaDataListener(KSMediaLiveKit.MediaLiveMediaDataListener mediaLiveMediaDataListener) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMediaLiveStatusListener(KSMediaLiveKit.MediaLiveStatusListener mediaLiveStatusListener) {
        this.f31328e.setMediaLiveStatusListener(mediaLiveStatusListener);
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMirror(boolean z12) {
        this.f31328e.setMirror(z12);
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMuteBgm(boolean z12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMuteMicOfAllOthers(boolean z12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setMuteMicOfOtherOne(String str, boolean z12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setQosExtraInterface(KSMediaLiveKit.QosExtraInterface qosExtraInterface) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setRemoteBgmVolume(float f12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void setReverbLevel(int i12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void startBgm(String str, boolean z12, KSMediaLiveKit.MediaLiveBgmListener mediaLiveBgmListener) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void startPush(Daenerys daenerys) {
        ux.a.e("KwaiMediaLiveKit", "startPush");
        this.f31328e.startPush(daenerys);
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void startPush(Daenerys daenerys, int i12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void startRtc(byte[] bArr, int i12) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void stopBgm() {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void stopPush(String str) {
        ux.a.e("KwaiMediaLiveKit", "stopPush");
        this.f31328e.stopPush();
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void stopRtc() {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public boolean updateBgmIndex(int i12, int i13) {
        return false;
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void updateLiveStreamRtmpUrl(String str) {
    }

    @Override // com.kwai.video.ksmedialivekit.KSMediaLiveKit
    public void updateWallClockTime(long j12) {
    }
}
