package com.quicinc.voice.activation.engineservice;

import G.q;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import p003d.g;
import p004e.a;
import p005f.InterfaceC0005f;
import p005f.p;
import p005f.z;
import p013n.AbstractC0024g;
import p013n.BinderC0036m;
import p013n.C0023f0;
import p013n.C0030j;
import p013n.C0032k;
import p013n.C0039n0;
import p013n.C0042p;
import p013n.C0044q;
import p013n.InterfaceC0020e;
import p013n.M;

/* JADX INFO: loaded from: classes.dex */
public final class EnrollmentService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BinderC0036m f523a = new BinderC0036m(this);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f524b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public C0042p f525c = g.m();

    public static String s() {
        return g.p().getNameForUid(Binder.getCallingUid());
    }

    public static void w(String str) {
        q.v(str);
    }

    public final void A(AbstractC0024g abstractC0024g, p pVar) {
        Bundle bundleN = abstractC0024g.n();
        if (bundleN == null) {
            C0023f0.e(pVar);
            return;
        }
        this.f524b.remove(abstractC0024g.j());
        abstractC0024g.t();
        C0023f0.a(pVar, bundleN);
        q.j("startUserVoiceEnrollmentInner - failureResponse=" + bundleN.toString());
    }

    public final void B(z zVar, Bundle bundle) {
        w("startUtteranceTraining");
        q.w("startUtteranceTraining");
        String strS = s();
        AbstractC0024g abstractC0024gT = t(strS);
        if (abstractC0024gT != null) {
            abstractC0024gT.y(bundle.getString("UtteranceInfo.id"), q(zVar));
            return;
        }
        zVar.e(C0023f0.d("Session[" + strS + "] is no found"));
    }

    public final void C(Bundle bundle) {
        w("transferRecording");
        q.w("transferRecording");
        AbstractC0024g abstractC0024gT = t(s());
        if (abstractC0024gT != null) {
            for (String str : bundle.keySet()) {
                abstractC0024gT.c(str, bundle.getShortArray(str));
            }
        }
    }

    public final AbstractC0024g k(String str, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) {
        AbstractC0024g abstractC0024g = (AbstractC0024g) this.f524b.get(str);
        if (abstractC0024g != null) {
            this.f524b.remove(str);
            abstractC0024g.t();
        }
        C0044q c0044q = new C0044q(str, g.f(), bundle, parcelFileDescriptor);
        this.f524b.put(str, c0044q);
        return c0044q;
    }

    public final AbstractC0024g l(String str, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) {
        AbstractC0024g abstractC0024g = (AbstractC0024g) this.f524b.get(str);
        if (abstractC0024g != null) {
            this.f524b.remove(str);
            abstractC0024g.t();
        }
        M m2 = new M(str, g.f(), bundle, parcelFileDescriptor);
        this.f524b.put(str, m2);
        return m2;
    }

    public final AbstractC0024g m(String str, Bundle bundle) {
        AbstractC0024g abstractC0024g = (AbstractC0024g) this.f524b.get(str);
        if (abstractC0024g != null) {
            this.f524b.remove(str);
            abstractC0024g.t();
        }
        C0039n0 c0039n0 = new C0039n0(str, g.f(), bundle);
        this.f524b.put(str, c0039n0);
        return c0039n0;
    }

    public final void n(p pVar, Bundle bundle) {
        w("cancelUserVoiceEnrollment");
        q.w("cancelUserVoiceEnrollment");
        String strS = s();
        AbstractC0024g abstractC0024gY = y(strS);
        if (abstractC0024gY != null) {
            abstractC0024gY.t();
            C0023f0.e(pVar);
            return;
        }
        C0023f0.b(pVar, "Session[" + strS + "] is no found");
    }

    public final void o(p pVar, Bundle bundle) {
        w("cancelUtteranceTraining");
        q.w("cancelUtteranceTraining");
        String strS = s();
        AbstractC0024g abstractC0024gT = t(strS);
        if (abstractC0024gT != null) {
            abstractC0024gT.d();
            C0023f0.e(pVar);
            return;
        }
        C0023f0.b(pVar, "Session[" + strS + "] is no found");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f523a;
    }

    public final void p(InterfaceC0005f interfaceC0005f, Bundle bundle) {
        w("commitUserVoiceEnrollment");
        q.w("commitUserVoiceEnrollment");
        String strS = s();
        AbstractC0024g abstractC0024gT = t(strS);
        if (abstractC0024gT == null) {
            interfaceC0005f.d0(C0023f0.d("Session[" + strS + "] is no found"));
            return;
        }
        int iH = abstractC0024gT.h();
        ParcelFileDescriptor parcelFileDescriptorM = abstractC0024gT.m();
        if (parcelFileDescriptorM == null) {
            interfaceC0005f.d0(C0023f0.d("Generate error"));
            q.j("commitUserVoiceEnrollment - Generate error");
            return;
        }
        Bundle bundleG = C0023f0.g();
        bundleG.putInt("UVREnrollment.quality_score", iH);
        interfaceC0005f.k(parcelFileDescriptorM, bundleG);
        q.v("commitUserVoiceEnrollment - Generate success");
        if (abstractC0024gT instanceof C0039n0) {
            return;
        }
        abstractC0024gT.A();
    }

    public final InterfaceC0020e q(z zVar) {
        return new C0030j(this, zVar);
    }

    public final void r(Bundle bundle) {
        w("finishUserVoiceEnrollment");
        q.w("finishUserVoiceEnrollment");
        AbstractC0024g abstractC0024gY = y(s());
        if (abstractC0024gY != null) {
            abstractC0024gY.t();
        }
    }

    public final AbstractC0024g t(String str) {
        return (AbstractC0024g) this.f524b.get(str);
    }

    public final List u(Bundle bundle) {
        w("getUtterancesInfo");
        AbstractC0024g abstractC0024gT = t(s());
        return abstractC0024gT != null ? abstractC0024gT.o() : new ArrayList(0);
    }

    public final boolean v() {
        w("isGeneralUVEnrolled");
        return g.m().i();
    }

    public final void x(p pVar) {
        w("removeGeneralUV");
        g.n().d();
        if (g.m().j()) {
            C0023f0.e(pVar);
        } else {
            C0023f0.b(pVar, "remove general UV failure");
        }
    }

    public final AbstractC0024g y(String str) {
        return (AbstractC0024g) this.f524b.remove(str);
    }

    public final void z(Bundle bundle, ParcelFileDescriptor parcelFileDescriptor, p pVar) {
        AbstractC0024g abstractC0024gK;
        w("startUserVoiceEnrollment");
        q.w("startUserVoiceEnrollment");
        if (!a.a(g.d())) {
            C0023f0.b(pVar, "permissions no granted");
            q.j("startUserVoiceEnrollment - permissions no granted");
            return;
        }
        if (bundle == null) {
            C0023f0.b(pVar, "params is null");
            q.j("startUserVoiceEnrollment - params is null");
            return;
        }
        String strS = s();
        AbstractEnrollmentSession$EnrollmentType abstractEnrollmentSession$EnrollmentTypeB = AbstractEnrollmentSession$EnrollmentType.b(bundle.getInt(AbstractEnrollmentSession$EnrollmentKey.ENROLLMENT_TYPE.b()));
        int i2 = C0032k.f1052a[abstractEnrollmentSession$EnrollmentTypeB.ordinal()];
        if (i2 == 1) {
            abstractC0024gK = g.f().p(strS) ? k(strS, bundle, parcelFileDescriptor) : l(strS, bundle, parcelFileDescriptor);
        } else {
            if (i2 != 2) {
                String str = "Can't support " + abstractEnrollmentSession$EnrollmentTypeB.name() + ", only support PDK";
                C0023f0.b(pVar, str);
                q.j("startUserVoiceEnrollment - " + str);
                return;
            }
            if (this.f525c.i()) {
                q.g("startUserVoiceEnrollment - general model already exists");
            }
            abstractC0024gK = m(strS, bundle);
        }
        A(abstractC0024gK, pVar);
    }
}
