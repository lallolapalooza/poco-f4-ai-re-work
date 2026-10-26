package defpackage;

import android.opengl.EGL14;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: Hs8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes4.dex */
public final class C3988Hs8 implements InterfaceC6612Mu8, InterfaceC44953zSa {
    public final AbstractC13945aPa R;
    public final AbstractC13945aPa S;
    public final AbstractC13945aPa T;
    public final C44135ync U;
    public final boolean V;
    public final AbstractC13945aPa W;
    public final AbstractC33080pre X;
    public final InterfaceC19898fD6 Y;
    public final C22109h03 Z;
    public final InterfaceC18530e6g a;
    public final AtomicReference a0;
    public final AbstractC13945aPa b;
    public final AbstractC32045p1g b0;
    public final AbstractC32045p1g c;
    public volatile AbstractC14971bEi c0;
    public volatile String d0;
    public volatile String e0;
    public volatile boolean f0;
    public volatile boolean g0;
    public OP1 h0;
    public AbstractC45096zZf i0;
    public int j0;

    public C3988Hs8(InterfaceC18530e6g interfaceC18530e6g, AbstractC13945aPa abstractC13945aPa, AbstractC32045p1g abstractC32045p1g, AbstractC13945aPa abstractC13945aPa2, AbstractC13945aPa abstractC13945aPa3, AbstractC13945aPa abstractC13945aPa4, C44135ync c44135ync, boolean z, AbstractC13945aPa abstractC13945aPa5, AbstractC33080pre abstractC33080pre) {
        C3471Gs8 c3471Gs8 = C3471Gs8.b;
        this.a = interfaceC18530e6g;
        this.b = abstractC13945aPa;
        this.c = abstractC32045p1g;
        this.R = abstractC13945aPa2;
        this.S = abstractC13945aPa3;
        this.T = abstractC13945aPa4;
        this.U = c44135ync;
        this.V = z;
        this.W = abstractC13945aPa5;
        this.X = abstractC33080pre;
        this.Y = c3471Gs8;
        this.Z = new C22109h03();
        C44793zKa c44793zKa = C22910he8.d;
        C44793zKa c44793zKa2 = C22910he8.d;
        this.a0 = new AtomicReference(C22910he8.e);
        this.b0 = new C26039kB0().U2();
        this.c0 = new C45521zv1();
        this.h0 = OP1.READY;
        this.i0 = C42621xZf.b;
        this.j0 = 1;
    }

    @Override // defpackage.InterfaceC39171umg
    public final String a() {
        return this.e0;
    }

    @Override // defpackage.InterfaceC39171umg
    public final void b(int i, int i2, long j, float[] fArr, float[] fArr2, EnumC34220qmg enumC34220qmg) {
        if (!((C22910he8) this.a0.get()).b) {
            throw new IllegalStateException("LensesCameraFeatureProcessingCoreSub must be initialized first".toString());
        }
        i();
        int iOrdinal = enumC34220qmg.ordinal();
        int i3 = 2;
        if (iOrdinal == 0) {
            i3 = 3;
        } else if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                throw new C38548uHa();
            }
            i3 = 1;
        }
        l(i3);
        AbstractC14971bEi abstractC14971bEi = this.c0;
        if (!(abstractC14971bEi instanceof C44284yv1)) {
            boolean z = abstractC14971bEi instanceof C45521zv1;
            return;
        }
        C44284yv1 c44284yv1 = (C44284yv1) abstractC14971bEi;
        S55 s55 = ((C22910he8) this.a0.get()).a;
        if (s55.k()) {
            return;
        }
        C2980Fte c2980Fte = c44284yv1.d;
        int i4 = c2980Fte.a;
        int i5 = c2980Fte.b;
        C2980Fte c2980Fte2 = c44284yv1.e;
        C3393Goa c3393GoaG = JIi.g(i, i2, j, fArr, fArr2, i4, i5, c2980Fte2.a, c2980Fte2.b, c44284yv1.f, -1.0f, c44284yv1.b, c44284yv1.c, this.j0, this.g0);
        InterfaceC36695smg interfaceC36695smg = (InterfaceC36695smg) s55.x().a().a(c3393GoaG);
        c3393GoaG.a();
        interfaceC36695smg.b();
    }

    @Override // defpackage.InterfaceC39171umg
    public final void c(EnumC29268mmg enumC29268mmg, float[] fArr, boolean z) {
        AbstractC14971bEi abstractC14971bEi = this.c0;
        if (!(abstractC14971bEi instanceof C44284yv1)) {
            boolean z2 = abstractC14971bEi instanceof C45521zv1;
            return;
        }
        S55 s55 = ((C22910he8) this.a0.get()).a;
        if (s55.k()) {
            return;
        }
        C5978Loa c5978LoaP = AbstractC21267gJi.p(enumC29268mmg.ordinal(), fArr, z);
        s55.x().c().a(c5978LoaP);
        c5978LoaP.a();
    }

    @Override // defpackage.InterfaceC42881xmg
    public final void d() {
        C22910he8 c22910he8;
        if (!((C22910he8) this.a0.get()).b) {
            throw new IllegalStateException("LensesCameraFeatureProcessingCoreSub must be initialized first".toString());
        }
        do {
            c22910he8 = (C22910he8) this.a0.get();
            if (!c22910he8.b) {
                break;
            }
        } while (!this.a0.compareAndSet(c22910he8, C22910he8.a(c22910he8, null, false, false, 5)));
        this.c.p(C40592vw1.a);
        this.Z.f();
        j();
        i();
    }

    @Override // defpackage.InterfaceC39171umg
    public final InterfaceC32982pmg e(EnumC29268mmg enumC29268mmg) {
        InterfaceC32982pmg c31744omg;
        AbstractC14971bEi abstractC14971bEi = this.c0;
        if (abstractC14971bEi instanceof C44284yv1) {
            S55 s55 = ((C22910he8) this.a0.get()).a;
            if (!s55.k()) {
                InterfaceC36695smg interfaceC36695smg = (InterfaceC36695smg) s55.x().b().a(Integer.valueOf(enumC29268mmg.ordinal()));
                try {
                    if (interfaceC36695smg.c() == 0) {
                        c31744omg = C21644gd.Y;
                    } else {
                        C30506nmg c30506nmg = InterfaceC32982pmg.K;
                        int iC = interfaceC36695smg.c();
                        int iE = interfaceC36695smg.e();
                        int iD = interfaceC36695smg.d();
                        Objects.requireNonNull(c30506nmg);
                        C31744omg c31744omg2 = (C31744omg) C30506nmg.b.b();
                        if (c31744omg2 == null) {
                            c31744omg = null;
                        } else {
                            c31744omg2.a = iC;
                            c31744omg2.b = iE;
                            c31744omg2.c = iD;
                        }
                        if (c31744omg == null) {
                            c31744omg = c31744omg2;
                            c31744omg = new C31744omg(iC, iE, iD);
                        }
                    }
                    return c31744omg;
                } finally {
                    interfaceC36695smg.b();
                }
            }
        } else {
            boolean z = abstractC14971bEi instanceof C45521zv1;
        }
        return C21644gd.Y;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0054  */
    @Override // defpackage.InterfaceC6612Mu8
    public final void f(C10574Ulg c10574Ulg) {
        boolean z;
        if (!((C22910he8) this.a0.get()).b) {
            throw new IllegalStateException("LensesCameraFeatureProcessingCoreSub must be initialized first".toString());
        }
        C42460xRc c42460xRc = c10574Ulg.c;
        AbstractC14971bEi abstractC14971bEi = this.c0;
        if (abstractC14971bEi instanceof C44284yv1) {
            C44284yv1 c44284yv1 = (C44284yv1) abstractC14971bEi;
            if (c44284yv1.b == c10574Ulg.a && c44284yv1.c == c10574Ulg.b) {
                C2980Fte c2980Fte = c44284yv1.d;
                if ((c42460xRc != null && c2980Fte.a == c42460xRc.a) && c2980Fte.b == c42460xRc.b) {
                    if (c44284yv1.i == c10574Ulg.d) {
                        z = (c44284yv1.j > c10574Ulg.e ? 1 : (c44284yv1.j == c10574Ulg.e ? 0 : -1)) == 0;
                    }
                }
            }
        }
        if (z) {
            return;
        }
        C43046xv1 c43046xv1C = M17.c(this.c0);
        c43046xv1C.e = Float.valueOf(1.0f);
        c43046xv1C.a = Integer.valueOf(c10574Ulg.a);
        c43046xv1C.b = Boolean.valueOf(c10574Ulg.b);
        c43046xv1C.f = Float.valueOf(c10574Ulg.d);
        c43046xv1C.g = Float.valueOf(c10574Ulg.e);
        if (c42460xRc != null) {
            c43046xv1C.c = new C2980Fte(c42460xRc.a, c42460xRc.b);
        }
        this.c0 = c43046xv1C.a();
        k();
    }

    @Override // defpackage.InterfaceC42881xmg
    public final void g() {
        C22910he8 c22910he8;
        this.c0 = new C45521zv1();
        final int i = 3;
        this.Z.b(this.b.u0(new C12001Xff(this, 25)).W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i2 = 0;
                switch (i) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i2 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i2 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i2 != 0) {
                            c3988Hs9.l(i2);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }));
        final int i2 = 4;
        this.Z.b(this.b.U1(C13306Zt8.a).o0().R1(1L).W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i3 = 0;
                switch (i2) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i3 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i3 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i3 != 0) {
                            c3988Hs9.l(i3);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }));
        final int i3 = 0;
        final int i4 = 5;
        this.Z.b(this.b.d2(new InterfaceC34750rD6(this) { // from class: Es8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.InterfaceC34750rD6
            public final Object apply(Object obj) {
                switch (i3) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            return c3988Hs8.T;
                        }
                        if (abstractC14560au8 instanceof C13306Zt8) {
                            return AbstractC13945aPa.C0();
                        }
                        throw new C38548uHa();
                    case 1:
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        return ((abstractC45096zZf instanceof C40147vZf) && ((C40147vZf) abstractC45096zZf).b == EnumC24056iZf.b) ? AbstractC13945aPa.d1(abstractC45096zZf).j0(500L, TimeUnit.MILLISECONDS, this.b.U.d()) : AbstractC13945aPa.d1(abstractC45096zZf);
                    default:
                        return this.b.X;
                }
            }
        }).o0().W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i5 = 0;
                switch (i4) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i5 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i5 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i5 != 0) {
                            c3988Hs9.l(i5);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }));
        ACd aCd = (ACd) this.Y.invoke();
        final int i5 = 1;
        final int i6 = 6;
        this.Z.b(AbstractC26117kEi.c(this.R.d2(new InterfaceC34750rD6(this) { // from class: Es8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.InterfaceC34750rD6
            public final Object apply(Object obj) {
                switch (i5) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            return c3988Hs8.T;
                        }
                        if (abstractC14560au8 instanceof C13306Zt8) {
                            return AbstractC13945aPa.C0();
                        }
                        throw new C38548uHa();
                    case 1:
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        return ((abstractC45096zZf instanceof C40147vZf) && ((C40147vZf) abstractC45096zZf).b == EnumC24056iZf.b) ? AbstractC13945aPa.d1(abstractC45096zZf).j0(500L, TimeUnit.MILLISECONDS, this.b.U.d()) : AbstractC13945aPa.d1(abstractC45096zZf);
                    default:
                        return this.b.X;
                }
            }
        }).t1(aCd).W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i7 = 0;
                switch (i6) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i7 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i7 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i7 != 0) {
                            c3988Hs9.l(i7);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }), 500L, TimeUnit.MILLISECONDS, this.U.d()));
        final int i7 = 2;
        final int i8 = 7;
        this.Z.b(this.R.u1(C43859yZf.class).U1(C43859yZf.b).g2(new InterfaceC34750rD6(this) { // from class: Es8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.InterfaceC34750rD6
            public final Object apply(Object obj) {
                switch (i7) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            return c3988Hs8.T;
                        }
                        if (abstractC14560au8 instanceof C13306Zt8) {
                            return AbstractC13945aPa.C0();
                        }
                        throw new C38548uHa();
                    case 1:
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        return ((abstractC45096zZf instanceof C40147vZf) && ((C40147vZf) abstractC45096zZf).b == EnumC24056iZf.b) ? AbstractC13945aPa.d1(abstractC45096zZf).j0(500L, TimeUnit.MILLISECONDS, this.b.U.d()) : AbstractC13945aPa.d1(abstractC45096zZf);
                    default:
                        return this.b.X;
                }
            }
        }).t1(aCd).W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i9 = 0;
                switch (i8) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i9 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i9 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i9 != 0) {
                            c3988Hs9.l(i9);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }));
        this.Z.b(AbstractC40169vai.s(this.R, this.b).d2(new C2954Fs8(aCd, i3)).t1(aCd).W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i9 = 0;
                switch (i3) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i9 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i9 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i9 != 0) {
                            c3988Hs9.l(i9);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }));
        this.Z.b(this.S.t1(aCd).W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i9 = 0;
                switch (i5) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i9 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i9 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i9 != 0) {
                            c3988Hs9.l(i9);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }));
        this.Z.b(this.W.W1(new I93(this) { // from class: Ds8
            public final /* synthetic */ C3988Hs8 b;

            {
                this.b = this;
            }

            @Override // defpackage.I93
            public final void x(Object obj) {
                int i9 = 0;
                switch (i7) {
                    case 0:
                        C3988Hs8 c3988Hs8 = this.b;
                        S55 s55 = ((C22910he8) c3988Hs8.a0.get()).a;
                        if (s55.k()) {
                            return;
                        }
                        if (!AbstractC20686fqi.f(EGL14.EGL_NO_CONTEXT, EGL14.eglGetCurrentContext())) {
                            s55.b().g().a(C38814uUg.a);
                            return;
                        } else {
                            if (c3988Hs8.V) {
                                c3988Hs8.b0.e(new IllegalStateException("Core can not be created with no active EGL context."));
                                return;
                            }
                            return;
                        }
                    case 1:
                        C3988Hs8 c3988Hs9 = this.b;
                        OP1 op1 = (OP1) obj;
                        if (c3988Hs9.h0 == op1) {
                            return;
                        }
                        switch (op1) {
                            case READY:
                            case REQUESTED:
                            case PRESENTING_CAPTURE_RESULT:
                            case INTERCEPTED:
                            case CANCELLING_PICTURE:
                                break;
                            case PREPARING_CAPTURE_VIDEO:
                            case CAPTURING_VIDEO:
                            case CAPTURING_VIDEO_WITHOUT_RENDERING:
                            case VIDEO_COMPLETING:
                                i9 = 2;
                                break;
                            case CAPTURING_PICTURE:
                                i9 = 3;
                                break;
                            default:
                                throw new C38548uHa();
                        }
                        if (i9 != 0) {
                            c3988Hs9.l(i9);
                        }
                        c3988Hs9.h0 = op1;
                        c3988Hs9.k();
                        return;
                    case 2:
                        this.b.g0 = ((Boolean) obj).booleanValue();
                        return;
                    case 3:
                        AbstractC20686fqi.f((AbstractC14560au8) obj, C13306Zt8.a);
                        return;
                    case 4:
                        C3988Hs8 c3988Hs10 = this.b;
                        AbstractC14560au8 abstractC14560au8 = (AbstractC14560au8) obj;
                        if (abstractC14560au8 instanceof AbstractC12790Yt8) {
                            c3988Hs10.f0 = true;
                            c3988Hs10.d0 = abstractC14560au8 instanceof C12274Xt8 ? ((C12274Xt8) abstractC14560au8).a : null;
                            c3988Hs10.m(c3988Hs10.j0);
                            return;
                        } else {
                            if (abstractC14560au8 instanceof C13306Zt8) {
                                c3988Hs10.d0 = null;
                                c3988Hs10.e0 = null;
                                c3988Hs10.f0 = false;
                                c3988Hs10.j();
                                return;
                            }
                            return;
                        }
                    case 5:
                        C3988Hs8 c3988Hs11 = this.b;
                        float fFloatValue = ((Float) obj).floatValue();
                        C43046xv1 c43046xv1C = M17.c(c3988Hs11.c0);
                        c43046xv1C.e = Float.valueOf(fFloatValue);
                        c3988Hs11.c0 = c43046xv1C.a();
                        return;
                    case 6:
                        C3988Hs8 c3988Hs12 = this.b;
                        AbstractC45096zZf abstractC45096zZf = (AbstractC45096zZf) obj;
                        c3988Hs12.i0 = abstractC45096zZf;
                        if (!AbstractC20686fqi.f(abstractC45096zZf, C43859yZf.b)) {
                            c3988Hs12.l(1);
                        }
                        c3988Hs12.k();
                        return;
                    default:
                        C3988Hs8 c3988Hs13 = this.b;
                        C42460xRc c42460xRc = (C42460xRc) obj;
                        C43046xv1 c43046xv1C2 = M17.c(c3988Hs13.c0);
                        c43046xv1C2.d = new C2980Fte(c42460xRc.a, c42460xRc.b);
                        c3988Hs13.c0 = c43046xv1C2.a();
                        c3988Hs13.k();
                        return;
                }
            }
        }));
        do {
            c22910he8 = (C22910he8) this.a0.get();
            if (c22910he8.b) {
                return;
            }
        } while (!this.a0.compareAndSet(c22910he8, C22910he8.a(c22910he8, null, true, false, 5)));
    }

    @Override // defpackage.InterfaceC39171umg
    public final boolean h() {
        return this.f0 && !((C22910he8) this.a0.get()).a.k();
    }

    public final void i() {
        C22910he8 c22910he8;
        S55 s55;
        C22910he8 c22910he8A;
        do {
            c22910he8 = (C22910he8) this.a0.get();
            if (!c22910he8.c || c22910he8.a.k()) {
                return;
            }
            C44793zKa c44793zKa = C22910he8.d;
            C44793zKa c44793zKa2 = C22910he8.d;
            s55 = C22910he8.e.a;
            c22910he8A = C22910he8.a(c22910he8, s55, false, false, 2);
        } while (!this.a0.compareAndSet(c22910he8, c22910he8A));
        if (this.a0.get() == c22910he8A) {
            this.b0.p(s55);
        }
        c22910he8.a.dispose();
    }

    public final void j() {
        C22910he8 c22910he8;
        do {
            c22910he8 = (C22910he8) this.a0.get();
            if (c22910he8.c || c22910he8.a.k()) {
                return;
            }
        } while (!this.a0.compareAndSet(c22910he8, C22910he8.a(c22910he8, null, false, true, 3)));
    }

    public final void k() {
        Object c41828ww1;
        AbstractC14971bEi abstractC14971bEi = this.c0;
        if (!(abstractC14971bEi instanceof C44284yv1)) {
            boolean z = abstractC14971bEi instanceof C45521zv1;
            return;
        }
        C44284yv1 c44284yv1 = (C44284yv1) abstractC14971bEi;
        EnumC34345qt1 enumC34345qt1 = c44284yv1.c ? EnumC34345qt1.FRONT : EnumC34345qt1.BACK;
        if (AbstractC20686fqi.f(this.i0, C43859yZf.b)) {
            int iZ = CZe.z(this.j0);
            if (iZ == 0) {
                c41828ww1 = new C44304yw1(enumC34345qt1, c44284yv1.d, c44284yv1.e);
            } else if (iZ == 1) {
                c41828ww1 = new C45541zw1(enumC34345qt1, c44284yv1.d, c44284yv1.e);
            } else {
                if (iZ != 2) {
                    throw new C38548uHa();
                }
                c41828ww1 = new C43066xw1(enumC34345qt1, c44284yv1.d, c44284yv1.e);
            }
        } else {
            c41828ww1 = new C41828ww1(enumC34345qt1);
        }
        this.c.p(c41828ww1);
        i();
    }

    public final void l(int i) {
        OP1 op1 = OP1.PRESENTING_CAPTURE_RESULT;
        if (this.j0 == i) {
            return;
        }
        OP1 op2 = this.h0;
        if (op2 == OP1.READY || op2 == OP1.REQUESTED || op2 == op1) {
            this.j0 = i;
            m(i);
            if (this.h0 != op1) {
                k();
            }
        }
    }

    public final void m(int i) {
        String str = this.d0;
        this.e0 = str != null ? AbstractC6116Lv9.j(str, FWf.s(i)) : null;
    }

    @Override // defpackage.InterfaceC44953zSa
    public final void s(NTa nTa) {
        while (true) {
            C22910he8 c22910he8 = (C22910he8) this.a0.get();
            if (!c22910he8.b || !c22910he8.a.k()) {
                break;
            }
            S55 s55 = (S55) this.a.get();
            if (this.a0.compareAndSet(c22910he8, C22910he8.a(c22910he8, s55, false, false, 6))) {
                this.b0.p(s55);
                break;
            }
            s55.dispose();
        }
        this.b0.s(nTa);
    }
}
