package com.quicinc.voice.activation.onevoice.algorithm;

import G.i;
import G.q;
import android.os.Bundle;
import com.qualcomm.qti.qesdk.QesdkUtils;
import com.quicinc.voice.activation.algorithm.Algorithm;
import com.quicinc.voice.activation.algorithm.AlgorithmProcessResult$AlgorithmResultKey;
import com.quicinc.voice.activation.algorithm.AlgorithmResponseStatus;
import com.quicinc.voice.activation.audiorecord.b;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.soundmodel.ConfigurationId;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.RecursiveTask;
import p007h.a;
import p009j.f;

/* JADX INFO: loaded from: classes.dex */
public class OneVoiceAlgorithmTask extends RecursiveTask {
    private final List mAlgos;
    private i mAudioStreamSaver;
    private final int mEnd;
    private boolean mGeneralUV;
    private final b mHotwordCapturer;
    private OneVoiceAlgorithmTask mLeftChild;
    private int mMsEndIndex;
    private int mMsStartIndex;
    private boolean mRequested;
    private OneVoiceAlgorithmTask mRightChild;
    private final int mStart;
    private State mState;

    enum State {
        IDLE,
        RECOGNIZING,
        RECOGNITION_PAUSED
    }

    public OneVoiceAlgorithmTask(b bVar, List list, boolean z2, int i2, int i3) {
        this.mState = State.IDLE;
        this.mHotwordCapturer = bVar;
        this.mAlgos = list;
        this.mGeneralUV = z2;
        this.mStart = i2;
        this.mEnd = i3;
    }

    public static a c(AlgorithmType algorithmType, AlgorithmResponseStatus algorithmResponseStatus) {
        return new a(algorithmType, algorithmResponseStatus);
    }

    public static a i() {
        return c(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_FAILURE);
    }

    public static a v() {
        return c(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_SUCCESS);
    }

    public final void a(Algorithm algorithm, Bundle bundle, p009j.b bVar, long j2, long j3, int i2) {
        Bundle bundle2 = new Bundle();
        String strE = algorithm.getAlgorithmType().e();
        boolean z2 = bundle.getInt(AlgorithmProcessResult$AlgorithmResultKey.DETECTED_FLAG.b()) == 1;
        int preTolerance = algorithm.getPreTolerance();
        int postTolerance = algorithm.getPostTolerance();
        int iC = bVar.c();
        int iA = bVar.a();
        bundle2.putString(QesdkUtils.TAGS.JSON_TAG_NAME, strE);
        bundle2.putBoolean(QesdkUtils.TAGS.TAG_SUCCESS, z2);
        bundle2.putBundle("result", bundle);
        bundle2.putLong("startTime", j2);
        bundle2.putLong("endTime", j2 + j3);
        bundle2.putLong("duration", j3);
        bundle2.putInt("realLength", i2);
        bundle2.putInt("preTolerance", preTolerance);
        bundle2.putInt("postTolerance", postTolerance);
        bundle2.putInt("bytes", iA - iC);
        bundle2.putInt("startIndex", iC);
        bundle2.putInt("endIndex", iA);
        this.mAudioStreamSaver.n(bundle2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.RecursiveTask
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public p007h.b compute() {
        long j2;
        int i2;
        if (!m()) {
            int i3 = this.mStart;
            int i4 = (this.mEnd + i3) / 2;
            OneVoiceAlgorithmTask oneVoiceAlgorithmTask = new OneVoiceAlgorithmTask(this.mHotwordCapturer, this.mAlgos, i3, i4);
            this.mLeftChild = oneVoiceAlgorithmTask;
            oneVoiceAlgorithmTask.r(this.mAudioStreamSaver);
            this.mLeftChild.q(this.mMsStartIndex, this.mMsEndIndex);
            OneVoiceAlgorithmTask oneVoiceAlgorithmTask2 = new OneVoiceAlgorithmTask(this.mHotwordCapturer, this.mAlgos, i4, this.mEnd);
            this.mRightChild = oneVoiceAlgorithmTask2;
            oneVoiceAlgorithmTask2.r(this.mAudioStreamSaver);
            this.mRightChild.q(this.mMsStartIndex, this.mMsEndIndex);
            this.mLeftChild.fork();
            this.mRightChild.fork();
            return ((p007h.b) this.mLeftChild.join()).m((p007h.b) this.mRightChild.join());
        }
        Algorithm algorithm = (Algorithm) this.mAlgos.get(this.mStart);
        AlgorithmType algorithmType = algorithm.getAlgorithmType();
        StringBuilder sb = new StringBuilder();
        sb.append("Process(");
        sb.append(algorithmType);
        String str = ")";
        sb.append(")");
        String string = sb.toString();
        q.d(string);
        p009j.b bVarJ = j(algorithm);
        Bundle bundle = new Bundle();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j3 = 0;
        int i5 = 0;
        int iC = 0;
        while (true) {
            q.d(string + "ReadFrame(" + i5 + str);
            f fVarF = bVarJ.f();
            q.m();
            q.d(string + "Frame(" + i5 + str);
            q.h("algorithm=" + algorithmType + ", readResult=" + fVarF);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (fVarF.e() || isCancelled()) {
                break;
            }
            if (algorithm.isGeneralUV() && algorithm.getAlgorithmType().f()) {
                q.h("start TIUV " + algorithm.updateTIUVThreshold(fVarF.f()).d());
                iC = 0;
            }
            AlgorithmResponseStatus algorithmResponseStatusProcessAlgorithm = algorithm.processAlgorithm(fVarF, null, bundle);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            String str2 = str;
            sb2.append(":");
            sb2.append(bundle);
            q.h(sb2.toString());
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            q.m();
            i5++;
            iC += fVarF.c();
            long j4 = j3 + (jCurrentTimeMillis3 - jCurrentTimeMillis2);
            q.h("cancel=" + isCancelled());
            if (isCancelled()) {
                AlgorithmResponseStatus algorithmResponseStatusCancelAlgorithm = algorithm.cancelAlgorithm();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("try cancel algorithm process ");
                sb3.append(algorithmResponseStatusCancelAlgorithm.c() ? QesdkUtils.TAGS.TAG_SUCCESS : "failed");
                q.h(sb3.toString());
            }
            if (isCancelled() || !algorithmResponseStatusProcessAlgorithm.c() || fVarF.f() || bundle.getInt(AlgorithmProcessResult$AlgorithmResultKey.DETECTED_FLAG.b()) == 1) {
                q.h("finishRead");
                bVarJ.e();
                i2 = iC;
                j2 = j4;
            } else {
                j3 = j4;
                str = str2;
            }
            q.m();
            q.p(string, "total duration=" + j2, "total data length=" + i2 + "(" + (i2 / p009j.i.j()) + " ms)");
            a(algorithm, bundle, bVarJ, jCurrentTimeMillis, j2, i2);
            return new p007h.b(algorithmType, algorithm.getPreTolerance(), bundle);
        }
        q.h("cancel - finishRead");
        bVarJ.e();
        j2 = j3;
        i2 = iC;
        q.m();
        q.p(string, "total duration=" + j2, "total data length=" + i2 + "(" + (i2 / p009j.i.j()) + " ms)");
        a(algorithm, bundle, bVarJ, jCurrentTimeMillis, j2, i2);
        return new p007h.b(algorithmType, algorithm.getPreTolerance(), bundle);
    }

    @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.Future
    public boolean cancel(boolean z2) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = this.mLeftChild;
        if (oneVoiceAlgorithmTask != null) {
            oneVoiceAlgorithmTask.cancel(z2);
        }
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask2 = this.mRightChild;
        if (oneVoiceAlgorithmTask2 != null) {
            oneVoiceAlgorithmTask2.cancel(z2);
        }
        return super.cancel(z2);
    }

    public synchronized a d() {
        return v();
    }

    public final a e() {
        for (Algorithm algorithm : this.mAlgos) {
            AlgorithmResponseStatus algorithmResponseStatusDeinitAlgorithm = algorithm.deinitAlgorithm();
            if (!algorithmResponseStatusDeinitAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusDeinitAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.IDLE;
        q.h("OneVoice task mState from " + state.name() + " -> " + this.mState.name());
        return v();
    }

    public final a f() {
        for (Algorithm algorithm : this.mAlgos) {
            AlgorithmResponseStatus algorithmResponseStatusInitAlgorithm = algorithm.initAlgorithm();
            if (!algorithmResponseStatusInitAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusInitAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.RECOGNITION_PAUSED;
        q.h("OneVoice task mState from " + state.name() + " -> " + this.mState.name());
        return v();
    }

    public final a g() {
        for (Algorithm algorithm : this.mAlgos) {
            AlgorithmResponseStatus algorithmResponseStatusStartAlgorithm = algorithm.startAlgorithm();
            if (!algorithmResponseStatusStartAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusStartAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.RECOGNIZING;
        q.h("OneVoice task mState from " + state.name() + " -> " + this.mState.name());
        return v();
    }

    public final a h() {
        for (Algorithm algorithm : this.mAlgos) {
            AlgorithmResponseStatus algorithmResponseStatusStopAlgorithm = algorithm.stopAlgorithm();
            if (!algorithmResponseStatusStopAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusStopAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.RECOGNITION_PAUSED;
        q.h("OneVoice task mState from " + state.name() + " -> " + this.mState.name());
        return v();
    }

    public p009j.b j(Algorithm algorithm) {
        int preTolerance = algorithm.getPreTolerance();
        int postTolerance = algorithm.getPostTolerance();
        ConfigurationId configurationIdD = algorithm.getAlgorithmType().d();
        q.h("one voice algorithm=" + algorithm.getAlgorithmType() + ", preTolerance=" + preTolerance + ", postTolerance=" + postTolerance);
        int i2 = p015p.b.f1154a[configurationIdD.ordinal()];
        if (i2 == 1) {
            return this.mHotwordCapturer.i(this.mMsStartIndex, this.mMsEndIndex, preTolerance, postTolerance);
        }
        if (i2 == 2 || i2 == 3) {
            return this.mHotwordCapturer.h(preTolerance, postTolerance);
        }
        if (i2 != 4) {
            return this.mHotwordCapturer.f(preTolerance, postTolerance);
        }
        return algorithm.isGeneralUV() ? this.mHotwordCapturer.k(this.mMsStartIndex, this.mMsEndIndex, preTolerance, postTolerance) : this.mHotwordCapturer.g(preTolerance, postTolerance);
    }

    public synchronized a k() {
        return v();
    }

    public boolean l() {
        return this.mGeneralUV;
    }

    public final boolean m() {
        return this.mEnd - this.mStart == 1;
    }

    public boolean n() {
        return this.mRequested;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized p007h.b o() {
        p007h.b bVar;
        q.h("OneVoice task proccess-mState=" + this.mState.name());
        bVar = new p007h.b(AlgorithmType.f599a, 0, new Bundle());
        if (this.mState == State.RECOGNIZING) {
            reinitialize();
            fork();
            try {
                bVar = (p007h.b) join();
            } catch (CancellationException unused) {
            }
        }
        return bVar;
    }

    public synchronized void p(Algorithm algorithm) {
        if (this.mGeneralUV) {
            this.mAlgos.remove(algorithm);
            this.mGeneralUV = false;
        }
    }

    public void q(int i2, int i3) {
        this.mMsStartIndex = i2;
        this.mMsEndIndex = i3;
    }

    public void r(i iVar) {
        this.mAudioStreamSaver = iVar;
    }

    public void s(boolean z2) {
        this.mRequested = z2;
    }

    public synchronized a t() {
        a aVarV;
        aVarV = v();
        if (this.mState == State.IDLE) {
            aVarV = f();
        }
        if (this.mState == State.RECOGNITION_PAUSED) {
            aVarV = g();
        }
        return aVarV;
    }

    public synchronized a u() {
        a aVarV;
        aVarV = v();
        if (this.mState == State.RECOGNIZING) {
            aVarV = h();
        }
        if (this.mState == State.RECOGNITION_PAUSED) {
            aVarV = e();
        }
        return aVarV;
    }

    public OneVoiceAlgorithmTask(b bVar, List list, int i2, int i3) {
        this(bVar, list, false, i2, i3);
    }
}
