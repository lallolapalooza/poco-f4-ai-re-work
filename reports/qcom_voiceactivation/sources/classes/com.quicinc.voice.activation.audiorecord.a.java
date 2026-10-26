package com.quicinc.voice.activation.audiorecord;

import G.i;
import G.q;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import com.quicinc.voice.activation.configuration.v4.DeviceConfigurationKey;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;
import p009j.h;
import p009j.n;
import p013n.C0037m0;

/* JADX INFO: compiled from: HotwordCapturer.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0037m0 f346a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public h f347b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecognitionEvent f348c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigSoundModel f349d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p009j.a f350e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public OneShotAudioRecordAction f351f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public HotwordCapturer$State f352g = HotwordCapturer$State.IDLE;

    public a(C0037m0 c0037m0) {
        this.f346a = c0037m0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: j.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f817a.r();
            }
        });
    }

    public final void b() {
        if (this.f351f != null) {
            q.h("closeAudioRecord");
            this.f351f.cancel(true);
            this.f351f = null;
        }
    }

    public final void c() {
        if (this.f350e != null) {
            q.h("closeAudioSink");
            this.f350e.a();
            this.f350e = null;
        }
    }

    public final void d() {
        h hVar = this.f347b;
        if (hVar != null) {
            hVar.a();
        }
    }

    public BigSoundModel e() {
        return this.f349d;
    }

    public p009j.b f(int i2, int i3) {
        return this.f347b.f(this.f348c.d(), this.f348c.b(), i2, i3);
    }

    public p009j.b g(int i2, int i3) {
        return this.f347b.g(this.f348c.d(), this.f348c.b(), i2, i3);
    }

    public p009j.b h(int i2, int i3) {
        int iD = this.f348c.d();
        int iB = this.f348c.b();
        q.h("startIndex=" + iD + ", endIndex=" + iB);
        return this.f347b.h(iD, iB, i2, i3);
    }

    public ParcelFileDescriptor i() {
        p009j.a aVar = this.f350e;
        if (aVar != null) {
            return aVar.b();
        }
        return null;
    }

    public final ParcelFileDescriptor j() {
        p009j.a aVar = this.f350e;
        if (aVar != null) {
            return aVar.c();
        }
        return null;
    }

    public void k(BigSoundModel bigSoundModel, RecognitionEvent recognitionEvent, i iVar) {
        if (this.f352g == HotwordCapturer$State.IDLE) {
            this.f348c = recognitionEvent;
            this.f349d = bigSoundModel;
            this.f347b = new h(bigSoundModel.getSoundModelConfiguration().getInt(DeviceConfigurationKey.f404g.b()));
            this.f350e = new p009j.a();
            OneShotAudioRecordAction oneShotAudioRecordAction = new OneShotAudioRecordAction(this.f348c.a(), this.f347b, j(), iVar);
            this.f351f = oneShotAudioRecordAction;
            oneShotAudioRecordAction.c(new n() { // from class: j.j
                @Override // p009j.n
                public final void a() {
                    this.f816a.l();
                }
            });
            HotwordCapturer$State hotwordCapturer$State = this.f352g;
            this.f352g = HotwordCapturer$State.INIT;
            q.h("mState from " + hotwordCapturer$State.name() + " -> " + this.f352g.name());
            q.i("mState from " + hotwordCapturer$State.name() + " -> " + this.f352g.name());
        }
        m("initRecording");
        q.i("initRecording");
    }

    public final void m(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("methodName=");
        sb.append(str);
        sb.append(", SoundModelKey=");
        BigSoundModel bigSoundModel = this.f349d;
        sb.append(bigSoundModel != null ? bigSoundModel.getSoundModelKey() : null);
        sb.append(", mRecognitionEvent=");
        sb.append(this.f348c);
        sb.append(", mState=");
        sb.append(this.f352g.name());
        q.h(sb.toString());
    }

    public final void n() {
        q.h("release");
        b();
        d();
        c();
    }

    public void o(boolean z2) {
        h hVar = this.f347b;
        if (hVar != null) {
            hVar.m(z2);
        }
    }

    public void p() {
        if (this.f352g == HotwordCapturer$State.INIT) {
            this.f351f.reinitialize();
            this.f351f.fork();
            HotwordCapturer$State hotwordCapturer$State = this.f352g;
            this.f352g = HotwordCapturer$State.RECORDING;
            q.h("mState from " + hotwordCapturer$State.name() + " -> " + this.f352g.name());
            q.i("mState from " + hotwordCapturer$State.name() + " -> " + this.f352g.name());
        }
        m("startRecording");
        q.i("startRecording");
    }

    public void q() {
        if (this.f352g == HotwordCapturer$State.RECORDING) {
            n();
            HotwordCapturer$State hotwordCapturer$State = this.f352g;
            this.f352g = HotwordCapturer$State.IDLE;
            q.h("mState from " + hotwordCapturer$State.name() + " -> " + this.f352g.name());
            q.i("mState from " + hotwordCapturer$State.name() + " -> " + this.f352g.name());
        }
        m("stopRecording");
    }

    public void r() {
        q.r("tryRestartRecognition");
        if (this.f346a.b() != null && this.f346a.b().c()) {
            q.r("tryRestartRecognition - all");
            this.f346a.a();
        }
        this.f349d = null;
    }
}
