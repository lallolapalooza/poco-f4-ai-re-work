package com.quicinc.voice.activation.algorithm;

import G.i;
import G.q;
import android.os.Bundle;
import com.qualcomm.qti.qesdk.QesdkUtils;
import com.quicinc.voice.activation.audiorecord.a;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.soundmodel.ConfigurationId;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.RecursiveTask;
import p007h.d;
import p009j.b;
import p009j.f;

/* JADX INFO: loaded from: classes.dex */
public final class AlgorithmTask extends RecursiveTask {
    private final List mAlgorithmList;
    private i mAudioStreamSaver;
    private final int mEnd;
    private int mFirstStageUVScore;
    private final a mHotwordCapturer;
    private AlgorithmTask mLeftChild;
    private boolean mRequested;
    private AlgorithmTask mRightChild;
    private final int mStart;
    private State mState = State.IDLE;

    enum State {
        IDLE,
        RECOGNIZING,
        RECOGNITION_PAUSED
    }

    public AlgorithmTask(a aVar, List list, int i2, int i3) {
        this.mHotwordCapturer = aVar;
        this.mAlgorithmList = list;
        this.mStart = i2;
        this.mEnd = i3;
    }

    public static p007h.a c(AlgorithmType algorithmType, AlgorithmResponseStatus algorithmResponseStatus) {
        return new p007h.a(algorithmType, algorithmResponseStatus);
    }

    public static p007h.a i() {
        return c(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_FAILURE);
    }

    public static p007h.a u() {
        return c(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_SUCCESS);
    }

    public final void a(Algorithm algorithm, Bundle bundle, b bVar, long j2, long j3, int i2) {
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
        if (algorithm.a()) {
            bundle2.putInt("firstStageUVScore", j());
        }
        this.mAudioStreamSaver.n(bundle2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.RecursiveTask
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public p007h.b compute() {
        Bundle bundle;
        if (!m()) {
            int i2 = this.mStart;
            int i3 = (this.mEnd + i2) / 2;
            AlgorithmTask algorithmTask = new AlgorithmTask(this.mHotwordCapturer, this.mAlgorithmList, i2, i3);
            this.mLeftChild = algorithmTask;
            algorithmTask.q(this.mFirstStageUVScore);
            this.mLeftChild.p(this.mAudioStreamSaver);
            AlgorithmTask algorithmTask2 = new AlgorithmTask(this.mHotwordCapturer, this.mAlgorithmList, i3, this.mEnd);
            this.mRightChild = algorithmTask2;
            algorithmTask2.q(this.mFirstStageUVScore);
            this.mRightChild.p(this.mAudioStreamSaver);
            this.mLeftChild.fork();
            this.mRightChild.fork();
            return ((p007h.b) this.mLeftChild.join()).m((p007h.b) this.mRightChild.join());
        }
        Algorithm algorithm = (Algorithm) this.mAlgorithmList.get(this.mStart);
        AlgorithmType algorithmType = algorithm.getAlgorithmType();
        StringBuilder sb = new StringBuilder();
        sb.append("Process(");
        sb.append(algorithmType);
        String str = ")";
        sb.append(")");
        String string = sb.toString();
        q.d(string);
        b bVarK = k(algorithm);
        if (algorithm.a()) {
            bundle = new Bundle();
            bundle.putInt("1st_stage_uv_score", j());
        } else {
            bundle = null;
        }
        Bundle bundle2 = new Bundle();
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i4 = 0;
        long j2 = 0;
        int iC = 0;
        while (true) {
            q.d(string + "ReadFrame(" + i4 + str);
            f fVarF = bVarK.f();
            q.m();
            q.d(string + "Frame(" + i4 + str);
            q.h("algorithm=" + algorithmType + ", readResult=" + fVarF);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (fVarF.e() || isCancelled()) {
                break;
            }
            AlgorithmResponseStatus algorithmResponseStatusProcessAlgorithm = algorithm.processAlgorithm(fVarF, bundle, bundle2);
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            q.m();
            i4++;
            iC += fVarF.c();
            j2 += jCurrentTimeMillis3 - jCurrentTimeMillis2;
            StringBuilder sb2 = new StringBuilder();
            String str2 = str;
            sb2.append("cancel=");
            sb2.append(isCancelled());
            q.h(sb2.toString());
            if (isCancelled() || !algorithmResponseStatusProcessAlgorithm.c() || fVarF.f() || bundle2.getInt(AlgorithmProcessResult$AlgorithmResultKey.DETECTED_FLAG.b()) == 1) {
                q.h("finishRead");
                bVarK.e();
            } else {
                str = str2;
            }
            q.m();
            q.p(string, "total duration=" + j2, "total data length=" + iC + "(" + (iC / p009j.i.j()) + " ms)");
            a(algorithm, bundle2, bVarK, jCurrentTimeMillis, j2, iC);
            return new p007h.b(algorithmType, algorithm.getPreTolerance(), bundle2);
        }
        q.h("cancel - finishRead");
        bVarK.e();
        q.m();
        q.p(string, "total duration=" + j2, "total data length=" + iC + "(" + (iC / p009j.i.j()) + " ms)");
        a(algorithm, bundle2, bVarK, jCurrentTimeMillis, j2, iC);
        return new p007h.b(algorithmType, algorithm.getPreTolerance(), bundle2);
    }

    @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.Future
    public boolean cancel(boolean z2) {
        AlgorithmTask algorithmTask = this.mLeftChild;
        if (algorithmTask != null) {
            algorithmTask.cancel(z2);
        }
        AlgorithmTask algorithmTask2 = this.mRightChild;
        if (algorithmTask2 != null) {
            algorithmTask2.cancel(z2);
        }
        return super.cancel(z2);
    }

    public synchronized p007h.a d() {
        return u();
    }

    public final p007h.a e() {
        for (Algorithm algorithm : this.mAlgorithmList) {
            AlgorithmResponseStatus algorithmResponseStatusDeinitAlgorithm = algorithm.deinitAlgorithm();
            if (!algorithmResponseStatusDeinitAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusDeinitAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.IDLE;
        q.h("mState from " + state.name() + " -> " + this.mState.name());
        return u();
    }

    public final p007h.a f() {
        for (Algorithm algorithm : this.mAlgorithmList) {
            AlgorithmResponseStatus algorithmResponseStatusInitAlgorithm = algorithm.initAlgorithm();
            if (!algorithmResponseStatusInitAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusInitAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.RECOGNITION_PAUSED;
        q.h("mState from " + state.name() + " -> " + this.mState.name());
        return u();
    }

    public final p007h.a g() {
        for (Algorithm algorithm : this.mAlgorithmList) {
            AlgorithmResponseStatus algorithmResponseStatusStartAlgorithm = algorithm.startAlgorithm();
            if (!algorithmResponseStatusStartAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusStartAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.RECOGNIZING;
        q.h("mState from " + state.name() + " -> " + this.mState.name());
        return u();
    }

    public final p007h.a h() {
        for (Algorithm algorithm : this.mAlgorithmList) {
            AlgorithmResponseStatus algorithmResponseStatusStopAlgorithm = algorithm.stopAlgorithm();
            if (!algorithmResponseStatusStopAlgorithm.c()) {
                return c(algorithm.getAlgorithmType(), algorithmResponseStatusStopAlgorithm);
            }
        }
        State state = this.mState;
        this.mState = State.RECOGNITION_PAUSED;
        q.h("mState from " + state.name() + " -> " + this.mState.name());
        return u();
    }

    public final int j() {
        return this.mFirstStageUVScore;
    }

    public b k(Algorithm algorithm) {
        int preTolerance = algorithm.getPreTolerance();
        int postTolerance = algorithm.getPostTolerance();
        ConfigurationId configurationIdD = algorithm.getAlgorithmType().d();
        q.h("algorithm=" + algorithm.getAlgorithmType() + ", preTolerance=" + preTolerance + ", postTolerance=" + postTolerance);
        int i2 = d.f755a[configurationIdD.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return this.mHotwordCapturer.h(preTolerance, postTolerance);
        }
        return i2 != 3 ? this.mHotwordCapturer.f(preTolerance, postTolerance) : this.mHotwordCapturer.g(preTolerance, postTolerance);
    }

    public synchronized p007h.a l() {
        return u();
    }

    public final boolean m() {
        return this.mEnd - this.mStart == 1;
    }

    public boolean n() {
        return this.mRequested;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized p007h.b o(int i2) {
        p007h.b bVar;
        q.h("proccess-mState=" + this.mState.name());
        bVar = new p007h.b(AlgorithmType.f599a, 0, new Bundle());
        if (this.mState == State.RECOGNIZING) {
            q(i2);
            reinitialize();
            fork();
            try {
                bVar = (p007h.b) join();
            } catch (CancellationException unused) {
            }
        }
        return bVar;
    }

    public void p(i iVar) {
        this.mAudioStreamSaver = iVar;
    }

    public final void q(int i2) {
        this.mFirstStageUVScore = i2;
    }

    public void r(boolean z2) {
        this.mRequested = z2;
    }

    public synchronized p007h.a s() {
        p007h.a aVarU;
        aVarU = u();
        if (this.mState == State.IDLE) {
            aVarU = f();
        }
        if (this.mState == State.RECOGNITION_PAUSED) {
            aVarU = g();
        }
        return aVarU;
    }

    public synchronized p007h.a t() {
        p007h.a aVarU;
        aVarU = u();
        if (this.mState == State.RECOGNIZING) {
            aVarU = h();
        }
        if (this.mState == State.RECOGNITION_PAUSED) {
            aVarU = e();
        }
        return aVarU;
    }
}
