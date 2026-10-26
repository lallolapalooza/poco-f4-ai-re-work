package p007h;

import G.q;
import android.os.Bundle;
import com.quicinc.voice.activation.algorithm.AlgorithmProcessResult$AlgorithmResultKey;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.soundmodel.ConfigurationId;
import p009j.i;

/* JADX INFO: compiled from: AlgorithmProcessResult.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AlgorithmType f751a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f752b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bundle f753c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f754d;

    public b(AlgorithmType algorithmType, int i2, Bundle bundle) {
        this.f751a = algorithmType;
        this.f752b = i2;
        this.f753c = bundle;
    }

    public boolean a() {
        return this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.DETECTED_FLAG.b()) == 1;
    }

    public int b() {
        return this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.DETECTED_INDEX.b());
    }

    public int c(int i2, int i3) {
        int i4 = i();
        return i4 > 0 ? (i2 - this.f752b) + i4 : i3;
    }

    public int d(int i2) {
        return (i2 - this.f752b) + Math.max(j(), 0);
    }

    public final int e() {
        int i2 = this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.CONFIDENCE_SCORE.b());
        q.h("kwConfidenceLevel=" + i2);
        return i2;
    }

    public int f(int i2, int i3) {
        int i4 = this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.KEYWORD_END_INDEX.b(), 0) * 10 * i.j();
        return i4 > 0 ? (i2 - this.f752b) + i4 : i3;
    }

    public int g(int i2) {
        int i3 = this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.KEYWORD_START_INDEX.b(), 0) * 10 * i.j();
        return i3 >= 0 ? Integer.max((i2 - this.f752b) + i3, 0) : i2;
    }

    public int h() {
        return l() ? (int) k() : e();
    }

    public final int i() {
        return this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.KEYWORD_END_INDEX.b(), 0);
    }

    public final int j() {
        return this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.KEYWORD_START_INDEX.b(), 0);
    }

    public final float k() {
        int i2 = this.f753c.getInt(AlgorithmProcessResult$AlgorithmResultKey.USER_SCORE.b());
        q.h("uvScore=" + i2);
        return i2;
    }

    public boolean l() {
        return this.f751a.d() == ConfigurationId.SML_ID_SVA_S_STAGE_USER;
    }

    public b m(b bVar) {
        b bVar2 = this;
        while (true) {
            b bVar3 = bVar2.f754d;
            if (bVar3 == null) {
                bVar2.f754d = bVar;
                return this;
            }
            bVar2 = bVar3;
        }
    }

    public b n() {
        return this.f754d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Result {");
        while (this != null) {
            sb.append("[");
            sb.append("name=");
            sb.append(this.f751a.e());
            sb.append(", score=");
            sb.append(this.h());
            sb.append(", result=");
            sb.append(this.a());
            if (this.f751a.d() != ConfigurationId.SML_ID_SVA_S_STAGE_USER) {
                sb.append(", startIndex=");
                sb.append(this.j());
                sb.append(", endIndex=");
                sb.append(this.i());
            }
            sb.append("], ");
            this = this.f754d;
        }
        sb.append("}");
        return sb.toString();
    }
}
