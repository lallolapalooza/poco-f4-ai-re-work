package com.kwai.camerasdk;

import androidx.annotation.Keep;
import com.kuaishou.weapon.gp.k2;
import p000do.d0;
import p000do.e0;
import p000do.p0;
import p000do.u;
import p000do.v;
import p000do.x;
import p000do.z;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public class DaenerysConfigBuilder {
    private static final float MIN_FACE_SIZE_RATIO = 0.277f;

    private DaenerysConfigBuilder() {
    }

    public static z.b defaultBuilder() {
        return z.r0().S(true).o(true).n(0.5f).I(p000do.c.k360P).Q(30).F(30).M(20).T(10000).g(44100).c(1).b(2).a(192).f(p000do.f.kAacLow).d(p000do.e.kFdkAac).e(20000).H(true).C(199).D(p0.kGLSyncNotTested).p(false).v(true).J(false).A(false).E(16).P(false).z(false).k(false).q(true).s(false).r(false).w(false).O(0).K(false).L(false).y(false).u(false).x(false).t(false).m(e0.kDownSamplerTypeUnknow).l(d0.kApplyStageNone);
    }

    public static u.b defaultCaptureConfigBuilder() {
        u.b bVarO = u.I0().e(p000do.i.kAndroidCameraAuto).t(false).M(30).N(0).P(true).F(0).E(0).J(720).G(1280).H(1280).I(360).K(44100).n(1).d(2).s(true).v(false).u(false).r(false).o(false);
        x xVar = x.kStabilizationModeOff;
        u.b bVarL = bVarO.k(xVar).l(xVar);
        p000do.m mVar = p000do.m.kCameraPreviewStream;
        u.b bVarY = bVarL.g(mVar).h(mVar).x(true).q(v.kEdgeModeDefault).m(false).A(k2.f22582a6).L(2000).y(false);
        p000do.n nVar = p000do.n.kCaptureDeviceTypeBuiltInWideAngleCamera;
        return bVarY.z(nVar).c(nVar).p(false).C(true).f(p000do.k.kCameraOutputDataTypeYuv).w(true).O(false).Q(false);
    }
}
