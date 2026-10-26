package com.quicinc.voice.activation.engineservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import p003d.g;
import p005f.F;
import p005f.p;
import p013n.InterfaceC0047u;
import p013n.InterfaceC0048v;
import p013n.P;
import p013n.Q;
import p013n.u0;
import p013n.v0;
import p013n.w0;
import p013n.x0;
import p013n.z0;

/* JADX INFO: loaded from: classes.dex */
public final class UtteranceProviderService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f538a = new z0(this);

    public static String h() {
        return g.p().getNameForUid(Binder.getCallingUid());
    }

    public final Q d(p pVar) {
        return new w0(this, pVar);
    }

    public final InterfaceC0048v e(p pVar) {
        return new x0(this, pVar);
    }

    public final InterfaceC0047u f(F f2) {
        return new v0(this, f2);
    }

    public final P g(F f2) {
        return new u0(this, f2);
    }

    public final void i(F f2, Bundle bundle) {
        boolean z2 = bundle != null && bundle.getBoolean("isOneVoice");
        String strH = h();
        if (g.f().p(strH) || (strH.equals(getPackageName()) && z2)) {
            g.n().k(strH, f(f2), bundle);
        } else {
            g.r().i(strH, g(f2), bundle);
        }
    }

    public final void j(p pVar, Bundle bundle) {
        String strH = h();
        boolean z2 = bundle != null && bundle.getBoolean("isOneVoice");
        if (g.f().p(strH) || (strH.equals(getPackageName()) && z2)) {
            g.n().q(strH, e(pVar), bundle);
        } else {
            g.r().n(strH, d(pVar), bundle);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f538a;
    }
}
