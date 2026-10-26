package defpackage;

import com.snapchat.client.network_types.NnmInternalErrorCode;

/* JADX INFO: loaded from: classes4.dex */
public final class IJ4 extends AbstractC27469lKc implements InterfaceC44953zSa {
    public final InterfaceC18530e6g X;
    public final int Y;
    public final AbstractC32045p1g Z;
    public final C22109h03 a0;
    public final C2842Fmg b0;
    public C1429Cte c0;
    public volatile C24957jIg d0;
    public volatile C24957jIg e0;
    public final C24957jIg f0;
    public S55 g0;
    public final C24957jIg h0;
    public final C24957jIg i0;

    public IJ4(InterfaceC18530e6g interfaceC18530e6g) {
        C3471Gs8 c3471Gs8 = C3471Gs8.n0;
        this.X = interfaceC18530e6g;
        this.Y = 1280;
        this.Z = new C26039kB0().U2();
        this.a0 = new C22109h03();
        this.b0 = (C2842Fmg) c3471Gs8.invoke();
        C24957jIg c24957jIgClone = this.R.clone();
        c24957jIgClone.a(this.S.c);
        this.d0 = c24957jIgClone;
        C24957jIg c24957jIgClone2 = this.R.clone();
        c24957jIgClone2.f(false);
        this.e0 = c24957jIgClone2;
        this.f0 = new C24957jIg();
        this.g0 = R55.a;
        this.h0 = new C24957jIg();
        this.i0 = new C24957jIg();
    }

    public final void A(C55 c55) {
        this.a0.b(c55);
    }

    @Override // defpackage.AbstractC27469lKc
    public final void j(C29207mjf c29207mjf) {
        this.b0.r(c29207mjf);
    }

    @Override // defpackage.AbstractC27469lKc
    public final void k(C26231kKc c26231kKc) {
        this.b0.t(c26231kKc);
        C1429Cte c1429Cte = e() > b() ? new C1429Cte(b(), e()) : new C1429Cte(e(), b());
        this.c0 = c1429Cte;
        int i = c1429Cte.b;
        int i2 = this.Y;
        if (i > i2) {
            this.c0 = new C1429Cte((int) ((c1429Cte.a / i) * i2), i2);
        }
    }

    @Override // defpackage.AbstractC27469lKc
    public final void l(C24957jIg c24957jIg) {
        this.b0.u(c24957jIg);
        C24957jIg c24957jIgClone = this.R.clone();
        c24957jIgClone.a(this.S.c);
        this.d0 = c24957jIgClone;
        C24957jIg c24957jIgClone2 = this.R.clone();
        c24957jIgClone2.f(false);
        this.e0 = c24957jIgClone2;
    }

    @Override // defpackage.AbstractC27469lKc
    public final void m(C24957jIg c24957jIg) {
        this.b0.v(c24957jIg);
        C24957jIg c24957jIgClone = this.R.clone();
        c24957jIgClone.a(this.S.c);
        this.d0 = c24957jIgClone;
    }

    @Override // defpackage.AbstractC27469lKc
    public final void n(InterfaceC5205Kbe interfaceC5205Kbe) {
        this.b0.w(interfaceC5205Kbe);
    }

    @Override // defpackage.AbstractC27469lKc
    public final void o(InterfaceC6460Mmg interfaceC6460Mmg) {
        this.b0.x(interfaceC6460Mmg);
    }

    @Override // defpackage.AbstractC27469lKc
    public final void p() {
        this.b0.p();
        if (this.g0.k()) {
            return;
        }
        AbstractC32045p1g abstractC32045p1g = this.Z;
        R55 r55 = R55.a;
        abstractC32045p1g.p(r55);
        this.g0.dispose();
        this.g0 = r55;
        this.a0.f();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x004e  */
    /* JADX WARN: Code duplicated, block: B:15:0x0065  */
    /* JADX WARN: Code duplicated, block: B:18:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:19:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:22:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:25:0x011e  */
    /* JADX WARN: Code duplicated, block: B:29:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.AbstractC27469lKc
    public final void q(int i, long j, C24957jIg c24957jIg, C14243aeb c14243aeb) {
        float f;
        float f2;
        S55 s55;
        C29207mjf c29207mjf;
        int i2;
        long j2;
        float[] fArr;
        float[] fArr2;
        C1429Cte c1429Cte;
        C29207mjf c29207mjf2;
        C1429Cte c1429Cte2 = this.c0;
        C38814uUg c38814uUg = null;
        if (c1429Cte2 == null) {
            AbstractC20686fqi.J("processingSize");
            throw null;
        }
        int i3 = c1429Cte2.a;
        int i4 = c1429Cte2.b;
        int i5 = c14243aeb.b;
        int i6 = i3 * c14243aeb.c;
        int i7 = i5 * i4;
        if (i6 >= i7) {
            if (i6 > i7) {
                f2 = i7 / i6;
                f = 1.0f;
            } else {
                f = 1.0f;
            }
            float[] fArr3 = this.f0.c;
            fArr3[0] = f2;
            fArr3[5] = f;
            fArr3[12] = (1.0f - f2) * 0.5f;
            fArr3[13] = (1.0f - f) * 0.5f;
            s55 = this.g0;
            if (!s55.k()) {
                i2 = d().b;
                j2 = j * ((long) NnmInternalErrorCode.ERROR_OPEN_FILE_FOR_DOWNLOAD);
                fArr = c24957jIg.c;
                fArr2 = this.e0.c;
                c1429Cte = this.c0;
                if (c1429Cte != null) {
                    AbstractC20686fqi.J("processingSize");
                    throw null;
                }
                C3393Goa c3393GoaG = JIi.g(i, i2, j2, fArr, fArr2, c1429Cte.a, c1429Cte.b, c14243aeb.b, c14243aeb.c, 41.0f, -1.0f, 0, true, 1, false);
                ((InterfaceC36695smg) s55.x().a().a(c3393GoaG)).b();
                c3393GoaG.a();
                InterfaceC36695smg interfaceC36695smg = (InterfaceC36695smg) s55.x().b().a(0);
                C5978Loa c5978LoaP = AbstractC21267gJi.p(0, this.i0.c, false);
                s55.x().c().a(c5978LoaP);
                C24957jIg c24957jIg2 = this.h0;
                c24957jIg2.l(c5978LoaP.b);
                c24957jIg2.i(this.S);
                c5978LoaP.a();
                this.b0.I(interfaceC36695smg.c(), j, this.f0, c14243aeb, this.h0, EnumC2325Emg.TEXTURE_2D);
                interfaceC36695smg.b();
                c29207mjf2 = this.U;
                if (c29207mjf2 != null) {
                    c29207mjf2.g();
                    c38814uUg = C38814uUg.a;
                }
            }
            if (c38814uUg == null) {
                c14243aeb.a();
                this.h0.l(this.d0.c);
                this.b0.I(i, j, c24957jIg, c14243aeb, this.d0, d());
                c29207mjf = this.U;
                if (c29207mjf == null) {
                    return;
                }
                c29207mjf.g();
            }
        }
        f = i6 / i7;
        f2 = 1.0f;
        float[] fArr4 = this.f0.c;
        fArr4[0] = f2;
        fArr4[5] = f;
        fArr4[12] = (1.0f - f2) * 0.5f;
        fArr4[13] = (1.0f - f) * 0.5f;
        s55 = this.g0;
        if (!s55.k()) {
            i2 = d().b;
            j2 = j * ((long) NnmInternalErrorCode.ERROR_OPEN_FILE_FOR_DOWNLOAD);
            fArr = c24957jIg.c;
            fArr2 = this.e0.c;
            c1429Cte = this.c0;
            if (c1429Cte != null) {
                AbstractC20686fqi.J("processingSize");
                throw null;
            }
            C3393Goa c3393GoaG2 = JIi.g(i, i2, j2, fArr, fArr2, c1429Cte.a, c1429Cte.b, c14243aeb.b, c14243aeb.c, 41.0f, -1.0f, 0, true, 1, false);
            ((InterfaceC36695smg) s55.x().a().a(c3393GoaG2)).b();
            c3393GoaG2.a();
            InterfaceC36695smg interfaceC36695smg2 = (InterfaceC36695smg) s55.x().b().a(0);
            C5978Loa c5978LoaP2 = AbstractC21267gJi.p(0, this.i0.c, false);
            s55.x().c().a(c5978LoaP2);
            C24957jIg c24957jIg3 = this.h0;
            c24957jIg3.l(c5978LoaP2.b);
            c24957jIg3.i(this.S);
            c5978LoaP2.a();
            this.b0.I(interfaceC36695smg2.c(), j, this.f0, c14243aeb, this.h0, EnumC2325Emg.TEXTURE_2D);
            interfaceC36695smg2.b();
            c29207mjf2 = this.U;
            if (c29207mjf2 != null) {
                c29207mjf2.g();
                c38814uUg = C38814uUg.a;
            }
        }
        if (c38814uUg == null) {
            c14243aeb.a();
            this.h0.l(this.d0.c);
            this.b0.I(i, j, c24957jIg, c14243aeb, this.d0, d());
            c29207mjf = this.U;
            if (c29207mjf == null) {
                return;
            }
            c29207mjf.g();
        }
    }

    @Override // defpackage.InterfaceC44953zSa
    public final void s(NTa nTa) {
        this.Z.s(nTa);
    }

    @Override // defpackage.AbstractC27469lKc
    public final void z() {
        this.b0.z();
        if (this.g0.k()) {
            S55 s55 = (S55) this.X.get();
            this.g0 = s55;
            this.Z.p(s55);
        }
    }
}
