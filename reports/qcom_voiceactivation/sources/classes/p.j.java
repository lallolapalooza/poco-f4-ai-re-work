package p015p;

import G.i;
import G.q;
import android.os.Handler;
import android.os.Looper;
import com.quicinc.voice.activation.algorithm.Algorithm;
import com.quicinc.voice.activation.algorithm.AlgorithmResponseStatus;
import com.quicinc.voice.activation.audiorecord.b;
import com.quicinc.voice.activation.onevoice.algorithm.OneVoiceAlgorithmTask;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import p007h.a;
import p017r.g;
import p017r.h;

/* JADX INFO: compiled from: OneVoiceSSManager.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Algorithm f1170b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Handler f1172d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap f1169a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i f1171c = new i(this, 0);

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        q.g("updatePlatformUV mOneVoiceUV = " + this.f1170b);
        if (this.f1170b == null) {
            return;
        }
        g gVarD = d();
        if (gVarD == null) {
            q.j("get extended platform uv model failed");
            return;
        }
        AlgorithmResponseStatus algorithmResponseStatusStopAlgorithm = this.f1170b.stopAlgorithm();
        if (algorithmResponseStatusStopAlgorithm.c()) {
            algorithmResponseStatusStopAlgorithm = this.f1170b.deinitAlgorithm();
        }
        if (!algorithmResponseStatusStopAlgorithm.c()) {
            q.j("stop/deinit platform uv failed");
            return;
        }
        this.f1170b.replaceSoundModel(gVarD.b());
        AlgorithmResponseStatus algorithmResponseStatusInitAlgorithm = this.f1170b.initAlgorithm();
        if (algorithmResponseStatusInitAlgorithm.c()) {
            algorithmResponseStatusInitAlgorithm = this.f1170b.startAlgorithm();
        }
        if (algorithmResponseStatusInitAlgorithm.c()) {
            return;
        }
        q.j("init/start platform uv failed");
    }

    public void b(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask;
        if (uuid == null || (oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1169a.get(uuid)) == null) {
            return;
        }
        oneVoiceAlgorithmTask.cancel(true);
        q.h("cancelProcess(OneVoice)");
    }

    public final String c(UUID uuid, boolean z2) {
        return "OneVoice SS UUID[" + uuid + "], result = " + z2;
    }

    public g d() {
        String strH = p003d.g.m().h();
        if (strH == null) {
            return null;
        }
        return g.e(strH, p003d.g.f(), true);
    }

    public final Algorithm e() {
        if (this.f1170b == null) {
            String strH = p003d.g.m().h();
            if (strH == null) {
                return null;
            }
            this.f1170b = a.a(g.e(strH, p003d.g.f(), true));
        }
        return this.f1170b;
    }

    public boolean f(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1169a.get(uuid);
        boolean z2 = oneVoiceAlgorithmTask != null && oneVoiceAlgorithmTask.n();
        q.r(c(uuid, z2));
        return z2;
    }

    public a h(UUID uuid, h hVar, b bVar) {
        p010k.b bVar2;
        boolean z2;
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1169a.get(uuid);
        if (oneVoiceAlgorithmTask == null) {
            Iterator it = p003d.g.e().f().values().iterator();
            do {
                if (!it.hasNext()) {
                    bVar2 = null;
                    break;
                }
                bVar2 = (p010k.b) it.next();
            } while (uuid != bVar2.o());
            if (bVar2 == null) {
                return new a(AlgorithmType.f599a, AlgorithmResponseStatus.ALGORITHM_BAD_PARAM);
            }
            boolean zE = bVar2.l().e();
            List listB = a.b(bVar2, hVar, zE);
            if (hVar.g() || zE || e() == null) {
                z2 = false;
            } else {
                listB.add(this.f1170b);
                this.f1171c.c();
                z2 = true;
            }
            oneVoiceAlgorithmTask = new OneVoiceAlgorithmTask(bVar, listB, z2, 0, listB.size());
            this.f1169a.put(uuid, oneVoiceAlgorithmTask);
        }
        a aVarK = oneVoiceAlgorithmTask.k();
        q.r(c(uuid, aVarK.c()));
        return aVarK;
    }

    public p007h.b i(UUID uuid, i iVar, int i2, int i3) {
        q.d("AlgorithmDetection(One Voice)");
        p007h.b bVar = null;
        if (uuid == null) {
            return null;
        }
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1169a.get(uuid);
        if (oneVoiceAlgorithmTask != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            oneVoiceAlgorithmTask.q(i2, i3);
            oneVoiceAlgorithmTask.r(iVar);
            p007h.b bVarO = oneVoiceAlgorithmTask.o();
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            q.o("OneVoiceAlgorithm Process:start=" + jCurrentTimeMillis + ", end=" + jCurrentTimeMillis2 + ",total duration=" + (jCurrentTimeMillis2 - jCurrentTimeMillis));
            bVar = bVarO;
        }
        q.h(bVar != null ? bVar.toString() : "null");
        q.m();
        return bVar;
    }

    public ArrayList j() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : this.f1169a.entrySet()) {
            OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) entry.getValue();
            if (oneVoiceAlgorithmTask != null && oneVoiceAlgorithmTask.l()) {
                arrayList.add((UUID) entry.getKey());
            }
        }
        return arrayList;
    }

    public final void k() {
        this.f1170b = null;
    }

    public a l(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1169a.get(uuid);
        a aVarI = OneVoiceAlgorithmTask.i();
        if (oneVoiceAlgorithmTask != null) {
            oneVoiceAlgorithmTask.s(true);
            aVarI = oneVoiceAlgorithmTask.t();
        }
        q.r(c(uuid, aVarI.c()));
        return aVarI;
    }

    public a m(UUID uuid) {
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1169a.get(uuid);
        a aVarV = OneVoiceAlgorithmTask.v();
        if (oneVoiceAlgorithmTask != null) {
            oneVoiceAlgorithmTask.s(false);
            if (!this.f1171c.d() && oneVoiceAlgorithmTask.l()) {
                oneVoiceAlgorithmTask.p(e());
                this.f1171c.a();
            }
            aVarV = oneVoiceAlgorithmTask.u();
            if (oneVoiceAlgorithmTask.l()) {
                this.f1171c.a();
                if (this.f1171c.b() == 0) {
                    k();
                }
            }
        }
        q.r(c(uuid, aVarV.c()));
        return aVarV;
    }

    public a n(UUID uuid) {
        q.d("TurnOffOneVoiceDetection(2nd)");
        q.r("OneVoice SS turnOffDetection uuid=" + uuid);
        a aVarM = m(uuid);
        if (aVarM.c()) {
            aVarM = p(uuid);
        }
        q.r(c(uuid, aVarM.c()));
        q.m();
        return aVarM;
    }

    public a o(UUID uuid, h hVar, b bVar) {
        q.d("TurnOnOneVoiceDetection(2nd)");
        q.r("OneVoice SS turnOnDetection uuid=" + uuid);
        a aVarH = h(uuid, hVar, bVar);
        if (aVarH.c()) {
            aVarH = l(uuid);
            if (!aVarH.c()) {
                p(uuid);
            }
        }
        q.r(c(uuid, aVarH.c()));
        q.m();
        return aVarH;
    }

    public a p(UUID uuid) {
        a aVarV = OneVoiceAlgorithmTask.v();
        OneVoiceAlgorithmTask oneVoiceAlgorithmTask = (OneVoiceAlgorithmTask) this.f1169a.get(uuid);
        if (oneVoiceAlgorithmTask != null) {
            aVarV = oneVoiceAlgorithmTask.d();
            if (aVarV.c()) {
                this.f1169a.remove(uuid);
            }
        }
        q.r(c(uuid, aVarV.c()));
        return aVarV;
    }

    public void q() {
        q.g("updatePlatformUV mOneVoiceUV = " + this.f1170b);
        if (this.f1170b == null) {
            return;
        }
        if (this.f1172d == null) {
            this.f1172d = new Handler(Looper.getMainLooper());
        }
        this.f1172d.post(new Runnable() { // from class: p.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f1166a.g();
            }
        });
    }
}
