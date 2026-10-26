package com.quicinc.voice.activation.vad;

import G.q;
import H.a;
import H.c;
import com.quicinc.voice.activation.audiorecord.b;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import p009j.f;
import p009j.i;
import p011l.d;

/* JADX INFO: loaded from: classes.dex */
public final class DNNVADAction extends RecursiveAction {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f682a = DNNVADAction.class.getSimpleName();
    private final d mConfigManager;
    private DNNVAD mDNNVAD;
    private int mEndDetectionIndex;
    private H.d mEndDetectionTimeoutHandler;
    private final b mOneVoiceHotwordCapturer;
    private int mStartDetectionIndex;
    private final int mStartIndex;
    private final AtomicInteger mCounter = new AtomicInteger(0);
    private final ReentrantLock mVadReentrantLock = new ReentrantLock();

    public enum ResultType {
        RESULT_START_DETECTION,
        RESULT_END_DETECTION
    }

    public DNNVADAction(b bVar, d dVar, int i2) {
        this.mOneVoiceHotwordCapturer = bVar;
        this.mConfigManager = dVar;
        this.mStartIndex = i2;
    }

    public static void h(String str) {
        q.h(f682a + " " + str);
    }

    public synchronized void c() {
        StringBuilder sb = new StringBuilder();
        String str = f682a;
        sb.append(str);
        sb.append(" cancel counter = ");
        sb.append(this.mCounter.get());
        q.h(sb.toString());
        if (!this.mCounter.compareAndSet(0, 0)) {
            this.mCounter.decrementAndGet();
        }
        if (this.mCounter.compareAndSet(0, 0) && !isDone()) {
            h("cancel");
            cancel(true);
            i(null, true);
            l();
        }
        q.h(str + " decrementCounter = " + this.mCounter.get());
    }

    @Override // java.util.concurrent.RecursiveAction
    public void compute() {
        p009j.b bVarE = this.mOneVoiceHotwordCapturer.e(this.mStartIndex);
        DNNVAD dnnvad = new DNNVAD(this.mConfigManager);
        this.mDNNVAD = dnnvad;
        dnnvad.initVAD();
        k(this.mStartIndex);
        q.d("Process(DNNVAD)");
        long j2 = 0;
        int i2 = 0;
        int iC = 0;
        while (true) {
            q.d("Process(DNNVAD)ReadFrame(" + i2 + ")");
            f fVarF = bVarE.f();
            q.m();
            q.d("Process(DNNVAD)Frame(" + i2 + ")");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (fVarF.e() || isCancelled()) {
                q.h(f682a + " cancel - finishRead");
                bVarE.e();
            } else {
                a aVarJ = j(fVarF);
                q.h("Process(DNNVAD):" + aVarJ);
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                q.m();
                i2++;
                iC += fVarF.c();
                j2 += jCurrentTimeMillis2 - jCurrentTimeMillis;
                if (aVarJ.e() && aVarJ.d() && aVarJ.c()) {
                    i(aVarJ, false);
                }
                StringBuilder sb = new StringBuilder();
                String str = f682a;
                sb.append(str);
                sb.append(" cancel=");
                sb.append(isCancelled());
                q.h(sb.toString());
                if (isCancelled() || aVarJ.c()) {
                    q.h(str + " finishRead");
                    bVarE.e();
                }
            }
            DNNVAD dnnvad2 = this.mDNNVAD;
            if (dnnvad2 != null) {
                dnnvad2.deinitVAD();
                this.mDNNVAD = null;
            }
            q.m();
            q.p("Process(DNNVAD)", "total duration=" + j2, "total data length=" + iC + "(" + (iC / i.j()) + " ms)");
            return;
        }
    }

    public final ByteBuffer d(f fVar) {
        return ByteBuffer.wrap(fVar.a(), fVar.d(), fVar.c());
    }

    public final synchronized void e() {
        h("endDetectionTimeout");
        q.h(f682a + " endDetectionTimeout counter = " + this.mCounter.get());
        this.mCounter.set(0);
        c();
    }

    public void f() {
        StringBuilder sb = new StringBuilder();
        String str = f682a;
        sb.append(str);
        sb.append(" forkProcess counter = ");
        sb.append(this.mCounter.get());
        q.h(sb.toString());
        if (this.mCounter.getAndIncrement() == 0) {
            this.mStartDetectionIndex = Integer.MIN_VALUE;
            this.mEndDetectionIndex = Integer.MIN_VALUE;
            if (isDone()) {
                reinitialize();
            }
            fork();
        }
        q.h(str + " incrementCounter = " + this.mCounter.get());
    }

    public int g(ResultType resultType) {
        int i2 = c.f166a[resultType.ordinal()];
        if (i2 == 1) {
            h("getResult RESULT_START_DETECTION");
            this.mVadReentrantLock.lock();
            try {
                return this.mStartDetectionIndex;
            } finally {
                this.mVadReentrantLock.unlock();
            }
        }
        if (i2 != 2) {
            return -1;
        }
        h("getResult RESULT_END_DETECTION");
        this.mVadReentrantLock.lock();
        try {
            return this.mEndDetectionIndex;
        } finally {
            this.mVadReentrantLock.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.util.concurrent.locks.ReentrantLock] */
    public final void i(a aVar, boolean z2) {
        h("notifyResultIfNeed mEndDetectionReentrantLock.lock() enter");
        this.mVadReentrantLock.lock();
        h("notifyResultIfNeed mEndDetectionReentrantLock.lock() exit");
        try {
            if (z2) {
                h("notifyResultIfNeed forceStop");
                this.mStartDetectionIndex = 0;
                this.mEndDetectionIndex = 0;
            } else if (aVar.d() && aVar.c()) {
                this.mStartDetectionIndex = aVar.b();
                this.mEndDetectionIndex = aVar.a();
                h("notifyResultIfNeed cmdStartIndex " + this.mStartDetectionIndex + ",cmdEndIndex " + this.mEndDetectionIndex);
                l();
            } else {
                this.mStartDetectionIndex = Integer.MIN_VALUE;
                this.mEndDetectionIndex = Integer.MIN_VALUE;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            h("notifyResultIfNeed mEndDetectionReentrantLock.unlock() enter");
            this.mVadReentrantLock.unlock();
            h("notifyResultIfNeed mEndDetectionReentrantLock.unlock() exit");
        }
    }

    public final a j(f fVar) {
        ShortBuffer shortBufferAsShortBuffer = d(fVar).order(ByteOrder.nativeOrder()).asShortBuffer();
        short[] sArr = new short[shortBufferAsShortBuffer.capacity()];
        shortBufferAsShortBuffer.get(sArr);
        ShortBuffer shortBufferWrap = ShortBuffer.wrap(sArr);
        q.h(f682a + " shortBuffer=" + shortBufferWrap.toString() + ", hasArrays=" + shortBufferWrap.hasArray());
        q.d("ProcessJNI(DNNVAD)");
        a aVarProcessVAD = this.mDNNVAD.processVAD(shortBufferWrap);
        q.m();
        return aVarProcessVAD;
    }

    public final void k(int i2) {
        DNNVAD dnnvad;
        if (this.mEndDetectionTimeoutHandler != null || (dnnvad = this.mDNNVAD) == null) {
            return;
        }
        int timeoutSize = (dnnvad.getTimeoutSize() - i2) / 32;
        this.mEndDetectionTimeoutHandler = new H.d(timeoutSize, new Runnable() { // from class: H.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f165a.e();
            }
        });
        h("setEndDetectionTimeout " + timeoutSize);
        this.mEndDetectionTimeoutHandler.a();
    }

    public final void l() {
        if (this.mEndDetectionTimeoutHandler != null) {
            h("stopEndDetectionTimeout");
            this.mEndDetectionTimeoutHandler.b();
            this.mEndDetectionTimeoutHandler = null;
        }
    }
}
