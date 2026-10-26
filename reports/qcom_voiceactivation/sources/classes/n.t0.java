package p013n;

import G.i;
import G.q;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.quicinc.voice.activation.audiorecord.a;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import p007h.b;
import p007h.e;

/* JADX INFO: compiled from: ThreadLifeManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class t0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final TimeUnit f1087j = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f1088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1089b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f1090c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f1091d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f1092e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bundle f1093f = new Bundle();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ReentrantLock f1094g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Condition f1095h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicBoolean f1096i;

    public t0(e eVar, a aVar) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f1094g = reentrantLock;
        this.f1095h = reentrantLock.newCondition();
        this.f1096i = new AtomicBoolean(false);
        this.f1088a = eVar;
        this.f1089b = aVar;
        this.f1090c = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread("2Stage", -16);
        handlerThread.start();
        this.f1091d = new Handler(handlerThread.getLooper());
        HandlerThread handlerThread2 = new HandlerThread("ThreadManager", -16);
        handlerThread2.start();
        this.f1092e = new Handler(handlerThread2.getLooper());
    }

    public static void f(Bundle bundle, b bVar, BigSoundModel bigSoundModel, RecognitionEvent recognitionEvent) {
        int iD = recognitionEvent.d();
        int iB = recognitionEvent.b();
        boolean z2 = true;
        while (bVar != null) {
            if (!bVar.a()) {
                z2 = false;
            }
            if (!bVar.l()) {
                iD = bVar.d(iD);
                iB = bVar.c(recognitionEvent.d(), iB);
            }
            bVar = bVar.n();
        }
        bundle.putBoolean("algorithm.success", z2);
        bundle.putInt("AudioMetaData.keywordStartIndex", iD);
        bundle.putInt("AudioMetaData.keywordEndIndex", iB);
        bundle.putInt("AudioMetaData.audioSampleBits", 16);
        bundle.putInt("AudioMetaData.audioSampleRate", 16000);
        bundle.putInt("AudioMetaData.audioFormat", 2);
        bundle.putInt("AudioMetaData.audioChannels", 16);
        bundle.putString("AudioMetaData.audioWakeword", bigSoundModel.getKeyword());
        bundle.putString("SoundModel.fileName", bigSoundModel.getSoundModelFileName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(i iVar, BigSoundModel bigSoundModel, RecognitionEvent recognitionEvent) {
        this.f1094g.lock();
        try {
            iVar.f();
            this.f1093f.putLong("SecondStage.startTimestamp", System.currentTimeMillis());
            b bVarE = this.f1088a.e(bigSoundModel.getSoundModelKey(), recognitionEvent.e(), iVar);
            this.f1093f.putLong("SecondStage.finishTimestamp", System.currentTimeMillis());
            this.f1089b.o(false);
            f(this.f1093f, bVarE, bigSoundModel, recognitionEvent);
            if (this.f1096i.compareAndSet(false, true)) {
                this.f1095h.signalAll();
                q.h("mResponsesCondition.signalAll");
            }
            if (bVarE == null) {
                q.i("SS bypass= true");
            } else {
                q.i("SS bypass= false, success= " + this.f1093f.get("algorithm.success"));
            }
            iVar.h(this.f1093f, bVarE);
        } finally {
            this.f1094g.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(BigSoundModel bigSoundModel, RecognitionEvent recognitionEvent, i iVar) {
        this.f1089b.k(bigSoundModel, recognitionEvent, iVar);
        this.f1089b.p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m() {
        this.f1089b.q();
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        BigSoundModel bigSoundModelE = this.f1089b.e();
        if (bigSoundModelE != null) {
            this.f1088a.a(bigSoundModelE.getSoundModelKey());
        }
    }

    public final void g() {
        q.h(this.f1093f.toString());
        this.f1096i.compareAndSet(true, false);
        this.f1093f.clear();
    }

    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void l(final BigSoundModel bigSoundModel, final RecognitionEvent recognitionEvent, final i iVar) {
        this.f1091d.post(new Runnable() { // from class: n.s0
            @Override // java.lang.Runnable
            public final void run() {
                this.f1083a.j(iVar, bigSoundModel, recognitionEvent);
            }
        });
    }

    public Bundle i() {
        Bundle bundle;
        this.f1094g.lock();
        try {
            try {
                if (!this.f1096i.get()) {
                    q.h("Wait mResponsesCondition, start = " + System.currentTimeMillis());
                    this.f1095h.await(10L, f1087j);
                    q.h("Wait mResponsesCondition, stop = " + System.currentTimeMillis());
                }
                q.h("mResponsesReady=" + this.f1096i.get());
                bundle = this.f1093f;
            } catch (InterruptedException e2) {
                q.h(Arrays.toString(e2.getStackTrace()));
                bundle = this.f1093f;
            }
            return bundle;
        } finally {
            this.f1096i.compareAndSet(true, false);
            this.f1094g.unlock();
        }
    }

    public void o(final BigSoundModel bigSoundModel, final RecognitionEvent recognitionEvent, final i iVar) {
        this.f1092e.post(new Runnable() { // from class: n.p0
            @Override // java.lang.Runnable
            public final void run() {
                this.f1068a.k(bigSoundModel, recognitionEvent, iVar);
            }
        });
        this.f1093f.putLong("AudioMetaData.startRecordingTimestamp", System.currentTimeMillis());
    }

    public void p(final BigSoundModel bigSoundModel, final RecognitionEvent recognitionEvent, final i iVar) {
        this.f1092e.post(new Runnable() { // from class: n.r0
            @Override // java.lang.Runnable
            public final void run() {
                this.f1079a.l(bigSoundModel, recognitionEvent, iVar);
            }
        });
    }

    public void q() {
        this.f1092e.post(new Runnable() { // from class: n.q0
            @Override // java.lang.Runnable
            public final void run() {
                this.f1077a.m();
            }
        });
    }

    public void r() {
        this.f1092e.post(new Runnable() { // from class: n.o0
            @Override // java.lang.Runnable
            public final void run() {
                this.f1063a.n();
            }
        });
    }
}
