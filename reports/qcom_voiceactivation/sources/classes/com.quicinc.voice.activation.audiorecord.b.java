package com.quicinc.voice.activation.audiorecord;

import G.i;
import G.q;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import com.quicinc.voice.activation.configuration.v4.DeviceConfigurationKey;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;
import p009j.h;
import p009j.n;
import p013n.D;

/* JADX INFO: compiled from: OneVoiceHotwordCapturer.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f353a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public h f354b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecognitionEvent f355c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public p009j.a f356d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OneShotAudioRecordAction f357e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public OneVoiceHotwordCapturer$State f358f = OneVoiceHotwordCapturer$State.IDLE;

    public b(D d2) {
        this.f353a = d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: j.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f822a.s();
            }
        });
    }

    public final void b() {
        if (this.f357e != null) {
            q.h("OneVoice closeAudioRecord");
            this.f357e.cancel(true);
            this.f357e = null;
        }
    }

    public final void c() {
        if (this.f356d != null) {
            q.h("OneVoice closeAudioSink");
            this.f356d.a();
            this.f356d = null;
        }
    }

    public final void d() {
        h hVar = this.f354b;
        if (hVar != null) {
            hVar.a();
        }
    }

    public p009j.b e(int i2) {
        return this.f354b.e(i2);
    }

    public p009j.b f(int i2, int i3) {
        return this.f354b.f(this.f355c.d(), this.f355c.b(), i2, i3);
    }

    public p009j.b g(int i2, int i3) {
        return this.f354b.g(this.f355c.d(), this.f355c.b(), i2, i3);
    }

    public p009j.b h(int i2, int i3) {
        return this.f354b.h(this.f355c.d(), this.f355c.b(), i2, i3);
    }

    public p009j.b i(int i2, int i3, int i4, int i5) {
        return this.f354b.h(i2, i3, i4, i5);
    }

    public ParcelFileDescriptor j() {
        p009j.a aVar = this.f356d;
        if (aVar != null) {
            return aVar.b();
        }
        return null;
    }

    public p009j.b k(int i2, int i3, int i4, int i5) {
        return this.f354b.i(i2, i3, i4, i5);
    }

    public final ParcelFileDescriptor l() {
        p009j.a aVar = this.f356d;
        if (aVar != null) {
            return aVar.c();
        }
        return null;
    }

    public void m(p016q.a aVar, RecognitionEvent recognitionEvent, i iVar) {
        if (OneVoiceHotwordCapturer$State.IDLE == this.f358f) {
            int i2 = aVar.g().d().getInt(DeviceConfigurationKey.f404g.b());
            this.f355c = recognitionEvent;
            this.f354b = new h(i2);
            this.f356d = new p009j.a();
            OneShotAudioRecordAction oneShotAudioRecordAction = new OneShotAudioRecordAction(this.f355c.a(), this.f354b, l(), iVar);
            this.f357e = oneShotAudioRecordAction;
            oneShotAudioRecordAction.c(new n() { // from class: j.o
                @Override // p009j.n
                public final void a() {
                    this.f821a.n();
                }
            });
            OneVoiceHotwordCapturer$State oneVoiceHotwordCapturer$State = this.f358f;
            this.f358f = OneVoiceHotwordCapturer$State.INIT;
            q.h("OneVoice hotword capturer mState from " + oneVoiceHotwordCapturer$State.name() + " -> " + this.f358f.name());
            q.i("OneVoice hotword capturer mState from " + oneVoiceHotwordCapturer$State.name() + " -> " + this.f358f.name());
        }
        q.i("OneVoice initRecording");
    }

    public final void o() {
        q.h("OneVoice release");
        b();
        d();
        c();
    }

    public void p(boolean z2) {
        h hVar = this.f354b;
        if (hVar != null) {
            hVar.m(z2);
        }
    }

    public void q() {
        if (OneVoiceHotwordCapturer$State.INIT == this.f358f) {
            this.f357e.reinitialize();
            this.f357e.fork();
            OneVoiceHotwordCapturer$State oneVoiceHotwordCapturer$State = this.f358f;
            this.f358f = OneVoiceHotwordCapturer$State.RECORDING;
            q.i("OneVoice hotword mState from " + oneVoiceHotwordCapturer$State.name() + " -> " + this.f358f.name());
        }
        q.i("OneVoice startRecording");
    }

    public void r() {
        if (OneVoiceHotwordCapturer$State.RECORDING == this.f358f) {
            o();
            OneVoiceHotwordCapturer$State oneVoiceHotwordCapturer$State = this.f358f;
            this.f358f = OneVoiceHotwordCapturer$State.IDLE;
            q.i("OneVoice hotword mState from " + oneVoiceHotwordCapturer$State.name() + " -> " + this.f358f.name());
        }
        q.i("OneVoice stopRecording");
    }

    public void s() {
        q.r("tryRestartRecognition-ModelGroup(OneVoice) - all");
        this.f353a.c();
    }
}
