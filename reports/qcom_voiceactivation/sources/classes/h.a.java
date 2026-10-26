package p007h;

import com.quicinc.voice.activation.algorithm.AlgorithmResponseStatus;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;

/* JADX INFO: compiled from: AlgorithmExecuteResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AlgorithmType f749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AlgorithmResponseStatus f750b;

    public a(AlgorithmType algorithmType, AlgorithmResponseStatus algorithmResponseStatus) {
        this.f749a = algorithmType;
        this.f750b = algorithmResponseStatus;
    }

    public String a() {
        return this.f749a.e() + "(" + this.f749a.ordinal() + "): " + this.f750b.d();
    }

    public AlgorithmResponseStatus b() {
        return this.f750b;
    }

    public boolean c() {
        return this.f750b.c();
    }
}
