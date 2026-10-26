package com.quicinc.voice.activation.engineservice;

import G.q;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import java.util.HashMap;
import java.util.Iterator;
import p003d.g;
import p005f.p;
import p010k.c;
import p011l.d;
import p013n.C0021e0;
import p013n.C0023f0;
import p013n.C0027h0;
import p013n.C0028i;
import p013n.C0029i0;
import p013n.C0037m0;
import p013n.C0042p;
import p013n.D;
import p013n.P0;
import p017r.h;
import u.a;
import u.b;
import v.i;
import v.j;

/* JADX INFO: loaded from: classes.dex */
public final class WakewordToggleService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final P0 f548a = new P0(this);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f549b = new Handler();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f550c = g.u();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f551d = g.k();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f552e = g.e();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0037m0 f553f = g.v();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final D f554g = g.o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f555h = g.f();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p016q.c f556i = g.j();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0042p f557j = g.m();

    public static j g(String str, C0029i0 c0029i0) {
        return new j(str, c0029i0.d(), c0029i0.e(), c0029i0.c());
    }

    public static String n() {
        return g.p().getNameForUid(Binder.getCallingUid());
    }

    public final void h(p010k.b bVar, p pVar) {
        C0021e0 c0021e0N = this.f554g.n(bVar);
        if (c0021e0N.d()) {
            C0023f0.f(pVar, C0023f0.g());
            this.f551d.d(bVar);
            bVar.x();
        } else {
            C0023f0.a(pVar, C0023f0.c(c0021e0N.a(), c0021e0N.e()));
        }
        q.m();
    }

    public final void i(String str, Bundle bundle, p pVar) {
        p010k.b bVarE = this.f552e.e(str);
        if (bVarE == null) {
            C0023f0.b(pVar, "Client [" + str + "] is not found");
            return;
        }
        q.d("DisableRecognition");
        if (this.f555h.p(bVarE.j())) {
            g.n().p();
            q.r("disable onevoice recognition");
            h(bVarE, pVar);
            return;
        }
        g.r().m();
        if (bundle == null) {
            q.r("disable all recognition by client");
            j(bVarE, pVar);
        } else {
            q.r("disable an recognition by sound model");
            k(bVarE, bundle, pVar);
        }
    }

    public final void j(p010k.b bVar, p pVar) {
        Iterator it = new HashMap(bVar.i()).values().iterator();
        while (it.hasNext()) {
            q(((BigSoundModel) it.next()).getSoundModelKey(), pVar);
        }
    }

    public final void k(p010k.b bVar, Bundle bundle, p pVar) {
        C0029i0 c0029i0 = new C0029i0(bundle);
        if (c0029i0.f()) {
            q(g(bVar.j(), c0029i0), pVar);
        } else {
            C0023f0.a(pVar, c0029i0.b());
            q.m();
        }
    }

    public final void l(String str, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor, p pVar) {
        q.d("EnableRecognition");
        if (!p004e.a.a(g.d())) {
            C0023f0.b(pVar, "permissions no granted");
            q.m();
            return;
        }
        C0028i c0028i = new C0028i(bundle);
        if (!c0028i.f()) {
            C0023f0.a(pVar, c0028i.c());
            q.m();
            return;
        }
        C0029i0 c0029i0 = new C0029i0(bundle);
        if (!c0029i0.f()) {
            C0023f0.a(pVar, c0029i0.b());
            q.m();
            return;
        }
        p010k.b bVarE = this.f552e.e(str);
        if (bVarE == null) {
            bVarE = new p010k.b(str);
            this.f552e.a(bVarE);
        }
        p010k.b bVar = bVarE;
        if (!this.f555h.p(bVar.j())) {
            BigSoundModel bigSoundModelD = i.d(parcelFileDescriptor);
            if (bigSoundModelD == null) {
                C0023f0.b(pVar, "Parse sound model error");
                q.m();
                return;
            }
            bigSoundModelD.setSoundModelKey(g(bVar.j(), c0029i0));
            C0027h0 c0027h0 = new C0027h0(bigSoundModelD);
            c0027h0.e();
            bigSoundModelD.setSoundModelConfiguration(c0027h0.d());
            bVar.t(c0028i.d(), c0028i.b(), c0028i.e());
            bVar.z(c0028i.d());
            bVar.a(bigSoundModelD);
            i.k(bigSoundModelD);
            q.t("enableWakewordRecognition " + bigSoundModelD.toString());
            r(bigSoundModelD, pVar);
            q.m();
            startService(ReenableWakewordService.d());
            return;
        }
        if (bVar.j().equals("com.qualcomm.qti.voice.assist.qvs")) {
            m(bVar, bundle, parcelFileDescriptor, pVar, c0028i, c0029i0);
            return;
        }
        h hVarE = p017r.c.e(parcelFileDescriptor, c0029i0);
        hVarE.j(this.f555h);
        bVar.t(c0028i.d(), c0028i.b(), c0028i.e());
        bVar.z(c0028i.d());
        bVar.y(c0029i0.c());
        bVar.b(hVarE);
        p016q.a aVarG = this.f556i.g(bVar);
        if (aVarG == null) {
            p016q.a aVarF = this.f556i.f(bVar);
            if (aVarF == null) {
                this.f556i.b(bVar);
            } else {
                this.f556i.a(aVarF, bVar);
            }
            aVarG = this.f556i.g(bVar);
        }
        String strG = this.f555h.g(bVar.j());
        String strC = this.f555h.c(strG, c0029i0.c());
        String strK = this.f555h.k(strG, c0029i0.c());
        aVarG.a(p017r.c.a(this.f557j.g(strC, OneVoiceModelFileManager$ModelType.FStage).getAbsolutePath(), this.f555h), !TextUtils.isEmpty(strK) ? p017r.c.c(this.f557j.g(strK, OneVoiceModelFileManager$ModelType.MStage).getAbsolutePath(), this.f555h) : null);
        p(bVar, pVar);
        this.f554g.m(bVar);
        q.m();
        startService(ReenableWakewordService.d());
    }

    public final void m(p010k.b bVar, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor, p pVar, C0028i c0028i, C0029i0 c0029i0) {
        q.r("enableWakewordRecognitionForQVS");
        bVar.t(c0028i.d(), c0028i.b(), c0028i.e());
        bVar.z(c0028i.d());
        bVar.y(c0029i0.c());
        BigSoundModel bigSoundModelD = i.d(parcelFileDescriptor);
        p016q.a aVarG = this.f556i.g(bVar);
        if (aVarG == null) {
            p016q.a aVarF = this.f556i.f(bVar);
            int i2 = bundle.getInt("1p5StageKW.index");
            if (aVarF == null) {
                this.f556i.c(bVar, "cc5c9716", i2);
            } else {
                aVarF.p(i2, bVar);
            }
            aVarG = this.f556i.g(bVar);
        }
        aVarG.a(p017r.c.b(bigSoundModelD, bundle), p017r.c.d(bigSoundModelD, bundle));
        bVar.b(p017r.c.f(bigSoundModelD, bundle));
        C0021e0 c0021e0P = this.f554g.p(bVar, g.l());
        if (c0021e0P.d()) {
            C0023f0.f(pVar, C0023f0.g());
        } else {
            C0023f0.a(pVar, C0023f0.c(c0021e0P.a(), c0021e0P.e()));
        }
        this.f554g.m(bVar);
        q.m();
        startService(ReenableWakewordService.d());
    }

    public final boolean o(p010k.b bVar, Bundle bundle) {
        boolean zD;
        boolean zP = this.f555h.p(bVar.j());
        C0029i0 c0029i0 = new C0029i0(bundle);
        if (!c0029i0.f()) {
            zD = false;
        } else if (zP) {
            zD = this.f554g.i(bVar, c0029i0);
        } else {
            zD = this.f553f.d(g(bVar.j(), c0029i0));
        }
        q.s("isWakewordRecognitionEnabled =" + zD);
        return zD;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f548a;
    }

    public final void p(p010k.b bVar, p pVar) {
        C0021e0 c0021e0P = this.f554g.p(bVar, g.l());
        if (!c0021e0P.d()) {
            C0023f0.a(pVar, C0023f0.c(c0021e0P.a(), c0021e0P.e()));
        } else {
            C0023f0.f(pVar, C0023f0.g());
            this.f551d.b(bVar, false);
        }
    }

    public final void q(j jVar, p pVar) {
        C0021e0 c0021e0N = this.f553f.n(jVar);
        if (c0021e0N.d()) {
            C0023f0.f(pVar, C0023f0.g());
            this.f550c.d(jVar);
            p010k.b bVarE = this.f552e.e(jVar.b());
            if (bVarE != null) {
                bVarE.w(jVar);
            }
        } else {
            C0023f0.a(pVar, C0023f0.c(c0021e0N.a(), c0021e0N.e()));
        }
        q.m();
    }

    public final void r(BigSoundModel bigSoundModel, p pVar) {
        C0021e0 c0021e0O = this.f553f.o(bigSoundModel, g.g());
        if (!c0021e0O.d()) {
            C0023f0.a(pVar, C0023f0.c(c0021e0O.a(), c0021e0O.e()));
        } else {
            C0023f0.f(pVar, C0023f0.g());
            this.f550c.b(bigSoundModel);
        }
    }
}
