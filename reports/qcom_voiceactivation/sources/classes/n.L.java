package p013n;

import G.i;
import G.q;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.quicinc.voice.activation.audiorecord.b;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import p015p.f;
import p015p.j;
import p016q.a;
import p017r.h;

/* JADX INFO: compiled from: OneVoiceThreadLifeManager.java */
/* JADX INFO: loaded from: classes.dex */
public class L {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final TimeUnit f929l = TimeUnit.SECONDS;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f930a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f931b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f932c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f933d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f934e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Handler f935f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bundle f936g = new Bundle();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ReentrantLock f937h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Condition f938i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AtomicBoolean f939j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AtomicBoolean f940k;

    public L(f fVar, j jVar, b bVar) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f937h = reentrantLock;
        this.f938i = reentrantLock.newCondition();
        this.f939j = new AtomicBoolean(false);
        this.f940k = new AtomicBoolean(false);
        this.f930a = fVar;
        this.f931b = jVar;
        this.f932c = bVar;
        this.f933d = new Handler(Looper.getMainLooper());
        HandlerThread handlerThread = new HandlerThread("algorithm", -16);
        handlerThread.start();
        this.f934e = new Handler(handlerThread.getLooper());
        HandlerThread handlerThread2 = new HandlerThread("ThreadManager", -16);
        handlerThread2.start();
        this.f935f = new Handler(handlerThread2.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(i iVar, a aVar, RecognitionEvent recognitionEvent) {
        this.f937h.lock();
        try {
            iVar.u(aVar);
            this.f936g.putLong("1.5Stage.startTimestamp", System.currentTimeMillis());
            p007h.b bVarV = this.f930a.v(aVar.l(), iVar);
            this.f936g.putLong("1.5Stage.finishTimestamp", System.currentTimeMillis());
            h(aVar, this.f936g, bVarV, recognitionEvent);
            if (this.f940k.compareAndSet(false, true)) {
                this.f938i.signalAll();
                q.h("1.5 stage mResponsesCondition.signalAll");
            }
            q.i("1.5 stage success= " + this.f936g.get("1.5Stage.success"));
            iVar.c(this.f936g, bVarV);
        } finally {
            this.f937h.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(i iVar, p010k.b bVar) {
        this.f937h.lock();
        try {
            iVar.k(bVar.j());
            this.f936g.putLong("SecondStage.startTimestamp", System.currentTimeMillis());
            p007h.b bVarI = this.f931b.i(bVar.o(), iVar, this.f936g.getInt("1.5Stage.keywordStartIndex", 0), this.f936g.getInt("1.5Stage.keywordEndIndex", 0));
            this.f936g.putLong("SecondStage.finishTimestamp", System.currentTimeMillis());
            this.f932c.p(false);
            i(this.f936g, bVarI);
            if (this.f939j.compareAndSet(false, true)) {
                this.f938i.signalAll();
                q.h("SS stage mResponsesCondition.signalAll");
            }
            if (bVarI == null) {
                q.i("SS bypass= true");
            } else {
                q.i("SS bypass= false, success= " + this.f936g.get("algorithm.success"));
            }
            iVar.a(bVar.j(), this.f936g, bVarI);
        } finally {
            this.f937h.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(a aVar, RecognitionEvent recognitionEvent, i iVar) {
        this.f932c.m(aVar, recognitionEvent, iVar);
        this.f932c.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(UUID uuid, UUID uuid2) {
        this.f930a.i(uuid);
        this.f931b.b(uuid2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u() {
        this.f932c.r();
        j();
    }

    public final void h(a aVar, Bundle bundle, p007h.b bVar, RecognitionEvent recognitionEvent) {
        boolean zA;
        int iC;
        p010k.b bVarF;
        h hVarN;
        int iD = recognitionEvent.d();
        int iB = recognitionEvent.b();
        if (bVar != null) {
            zA = bVar.a();
            iD = bVar.g(iD);
            iB = bVar.f(recognitionEvent.d(), iB);
            iC = bVar.b();
        } else {
            zA = true;
            iC = -1;
        }
        if (aVar.n() == null) {
            iC = recognitionEvent.h() ? recognitionEvent.c() : 0;
        }
        bundle.putBoolean("1.5Stage.success", zA);
        bundle.putInt("1.5Stage.keywordStartIndex", iD);
        bundle.putInt("1.5Stage.keywordEndIndex", iB);
        bundle.putInt("1.5Stage.detectedIndex", iC);
        bundle.putInt("AudioMetaData.audioSampleBits", 16);
        bundle.putInt("AudioMetaData.audioSampleRate", 16000);
        bundle.putInt("AudioMetaData.audioFormat", 2);
        bundle.putInt("AudioMetaData.audioChannels", 16);
        if (!zA || (bVarF = aVar.f(iC)) == null || (hVarN = bVarF.n()) == null) {
            return;
        }
        bundle.putString("SoundModel.fileName", hVarN.c());
    }

    public final void i(Bundle bundle, p007h.b bVar) {
        int iD = bundle.getInt("1.5Stage.keywordStartIndex", 0);
        int iC = bundle.getInt("1.5Stage.keywordEndIndex", 0);
        boolean z2 = true;
        while (bVar != null) {
            if (!bVar.a()) {
                z2 = false;
            }
            if (!bVar.l()) {
                iD = bVar.d(iD);
                iC = bVar.c(bundle.getInt("1.5Stage.keywordStartIndex", 0), iC);
            }
            bVar = bVar.n();
        }
        bundle.putBoolean("algorithm.success", z2);
        bundle.putInt("AudioMetaData.keywordStartIndex", iD);
        bundle.putInt("AudioMetaData.keywordEndIndex", iC);
    }

    public final void j() {
        q.h(this.f936g.toString());
        this.f939j.compareAndSet(true, false);
        this.f936g.clear();
    }

    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void q(final a aVar, final RecognitionEvent recognitionEvent, final i iVar) {
        this.f934e.post(new Runnable() { // from class: n.K
            @Override // java.lang.Runnable
            public final void run() {
                this.f925a.o(iVar, aVar, recognitionEvent);
            }
        });
    }

    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final void s(final p010k.b bVar, RecognitionEvent recognitionEvent, final i iVar) {
        this.f934e.post(new Runnable() { // from class: n.J
            @Override // java.lang.Runnable
            public final void run() {
                this.f919a.p(iVar, bVar);
            }
        });
    }

    public Bundle m() {
        Bundle bundle;
        this.f937h.lock();
        try {
            try {
                if (!this.f940k.get()) {
                    q.h("Wait mResponsesCondition(1.5 stage), start = " + System.currentTimeMillis());
                    this.f938i.await(10L, f929l);
                    q.h("Wait mResponsesCondition(1.5 stage), stop = " + System.currentTimeMillis());
                }
                q.h("mMsResponsesReady=" + this.f940k.get());
                bundle = this.f936g;
            } catch (InterruptedException e2) {
                q.h(Arrays.toString(e2.getStackTrace()));
                bundle = this.f936g;
            }
            return bundle;
        } finally {
            this.f940k.compareAndSet(true, false);
            this.f937h.unlock();
        }
    }

    public Bundle n() {
        Bundle bundle;
        this.f937h.lock();
        try {
            try {
                if (!this.f939j.get()) {
                    q.h("Wait mResponsesCondition, start = " + System.currentTimeMillis());
                    this.f938i.await(10L, f929l);
                    q.h("Wait mResponsesCondition, stop = " + System.currentTimeMillis());
                }
                q.h("mResponsesReady=" + this.f939j.get());
                bundle = this.f936g;
            } catch (InterruptedException e2) {
                q.h(Arrays.toString(e2.getStackTrace()));
                bundle = this.f936g;
            }
            return bundle;
        } finally {
            this.f939j.compareAndSet(true, false);
            this.f937h.unlock();
        }
    }

    public void v(final a aVar, final RecognitionEvent recognitionEvent, final i iVar) {
        this.f935f.post(new Runnable() { // from class: n.G
            @Override // java.lang.Runnable
            public final void run() {
                this.f896a.q(aVar, recognitionEvent, iVar);
            }
        });
    }

    public void w(final a aVar, final RecognitionEvent recognitionEvent, final i iVar) {
        this.f935f.post(new Runnable() { // from class: n.H
            @Override // java.lang.Runnable
            public final void run() {
                this.f905a.r(aVar, recognitionEvent, iVar);
            }
        });
        this.f936g.putLong("AudioMetaData.startRecordingTimestamp", System.currentTimeMillis());
    }

    public void x(final p010k.b bVar, final RecognitionEvent recognitionEvent, final i iVar) {
        this.f935f.post(new Runnable() { // from class: n.I
            @Override // java.lang.Runnable
            public final void run() {
                this.f912a.s(bVar, recognitionEvent, iVar);
            }
        });
    }

    public void y(final UUID uuid, final UUID uuid2) {
        this.f935f.post(new Runnable() { // from class: n.E
            @Override // java.lang.Runnable
            public final void run() {
                this.f892a.t(uuid, uuid2);
            }
        });
    }

    public void z() {
        this.f935f.post(new Runnable() { // from class: n.F
            @Override // java.lang.Runnable
            public final void run() {
                this.f895a.u();
            }
        });
    }
}
