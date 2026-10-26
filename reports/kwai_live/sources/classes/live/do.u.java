package p000do;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.kuaishou.client.log.event.packages.nano.ClientEvent;
import com.yxcorp.bugly.CrashReporter;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: compiled from: DaenerysCaptureConfig.java */
/* JADX INFO: loaded from: classes15.dex */
public final class u extends GeneratedMessageLite<u, b> implements MessageLiteOrBuilder {

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private static final u f64772n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private static volatile Parser<u> f64773o0;
    private float B;
    private boolean C;
    private boolean D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    private int f64774J;
    private int K;
    private int L;
    private w M;
    private boolean N;
    private boolean O;
    private boolean P;
    private int Q;
    private int R;
    private float S;
    private float T;
    private boolean U;
    private int V;
    private boolean W;
    private int X;
    private int Y;
    private boolean Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f64775a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private int f64776a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f64777b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private boolean f64778b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f64779c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private boolean f64780c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f64781d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private int f64782d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f64783e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private boolean f64784e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f64785f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private boolean f64786f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f64787g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private boolean f64788g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f64789h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private boolean f64790h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f64791i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private boolean f64792i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f64793j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private boolean f64794j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f64795k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private boolean f64796k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f64797l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private boolean f64798l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f64799m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private boolean f64800m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f64801n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f64802o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f64803p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f64804q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f64805r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f64806t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private boolean f64807u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f64808w;

    /* JADX INFO: compiled from: DaenerysCaptureConfig.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f64809a;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f64809a = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
                CrashReporter.logCheckedException(e12, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
            try {
                f64809a[GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
                CrashReporter.logCheckedException(e13, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
            try {
                f64809a[GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
                CrashReporter.logCheckedException(e14, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
            try {
                f64809a[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
                CrashReporter.logCheckedException(e15, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
            try {
                f64809a[GeneratedMessageLite.MethodToInvoke.VISIT.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
                CrashReporter.logCheckedException(e16, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
            try {
                f64809a[GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
                CrashReporter.logCheckedException(e17, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
            try {
                f64809a[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
                CrashReporter.logCheckedException(e18, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
            try {
                f64809a[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
                CrashReporter.logCheckedException(e19, "DaenerysCaptureConfig$1.class", "<clinit>", -1);
            }
        }
    }

    /* JADX INFO: compiled from: DaenerysCaptureConfig.java */
    public static final class b extends GeneratedMessageLite.Builder<u, b> implements MessageLiteOrBuilder {
        /* synthetic */ b(a aVar) {
            this();
        }

        public b A(int i12) {
            copyOnWrite();
            ((u) this.instance).i1(i12);
            return this;
        }

        public b C(boolean z12) {
            copyOnWrite();
            ((u) this.instance).j1(z12);
            return this;
        }

        public b D(boolean z12) {
            copyOnWrite();
            ((u) this.instance).k1(z12);
            return this;
        }

        public b E(int i12) {
            copyOnWrite();
            ((u) this.instance).l1(i12);
            return this;
        }

        public b F(int i12) {
            copyOnWrite();
            ((u) this.instance).m1(i12);
            return this;
        }

        public b G(int i12) {
            copyOnWrite();
            ((u) this.instance).n1(i12);
            return this;
        }

        public b H(int i12) {
            copyOnWrite();
            ((u) this.instance).o1(i12);
            return this;
        }

        public b I(int i12) {
            copyOnWrite();
            ((u) this.instance).p1(i12);
            return this;
        }

        public b J(int i12) {
            copyOnWrite();
            ((u) this.instance).q1(i12);
            return this;
        }

        public b K(int i12) {
            copyOnWrite();
            ((u) this.instance).r1(i12);
            return this;
        }

        public b L(int i12) {
            copyOnWrite();
            ((u) this.instance).s1(i12);
            return this;
        }

        public b M(int i12) {
            copyOnWrite();
            ((u) this.instance).t1(i12);
            return this;
        }

        public b N(int i12) {
            copyOnWrite();
            ((u) this.instance).u1(i12);
            return this;
        }

        public b O(boolean z12) {
            copyOnWrite();
            ((u) this.instance).v1(z12);
            return this;
        }

        public b P(boolean z12) {
            copyOnWrite();
            ((u) this.instance).w1(z12);
            return this;
        }

        public b Q(boolean z12) {
            copyOnWrite();
            ((u) this.instance).x1(z12);
            return this;
        }

        public b R(boolean z12) {
            copyOnWrite();
            ((u) this.instance).y1(z12);
            return this;
        }

        public int a() {
            return ((u) this.instance).v0();
        }

        public int b() {
            return ((u) this.instance).y0();
        }

        public b c(n nVar) {
            copyOnWrite();
            ((u) this.instance).K0(nVar);
            return this;
        }

        public b d(int i12) {
            copyOnWrite();
            ((u) this.instance).L0(i12);
            return this;
        }

        public b e(i iVar) {
            copyOnWrite();
            ((u) this.instance).M0(iVar);
            return this;
        }

        public b f(k kVar) {
            copyOnWrite();
            ((u) this.instance).N0(kVar);
            return this;
        }

        public b g(m mVar) {
            copyOnWrite();
            ((u) this.instance).O0(mVar);
            return this;
        }

        public b h(m mVar) {
            copyOnWrite();
            ((u) this.instance).P0(mVar);
            return this;
        }

        public b i(int i12) {
            copyOnWrite();
            ((u) this.instance).Q0(i12);
            return this;
        }

        public b j(int i12) {
            copyOnWrite();
            ((u) this.instance).R0(i12);
            return this;
        }

        public b k(x xVar) {
            copyOnWrite();
            ((u) this.instance).S0(xVar);
            return this;
        }

        public b l(x xVar) {
            copyOnWrite();
            ((u) this.instance).T0(xVar);
            return this;
        }

        public b m(boolean z12) {
            copyOnWrite();
            ((u) this.instance).U0(z12);
            return this;
        }

        public b n(int i12) {
            copyOnWrite();
            ((u) this.instance).V0(i12);
            return this;
        }

        public b o(boolean z12) {
            copyOnWrite();
            ((u) this.instance).W0(z12);
            return this;
        }

        public b p(boolean z12) {
            copyOnWrite();
            ((u) this.instance).X0(z12);
            return this;
        }

        public b q(v vVar) {
            copyOnWrite();
            ((u) this.instance).Y0(vVar);
            return this;
        }

        public b r(boolean z12) {
            copyOnWrite();
            ((u) this.instance).Z0(z12);
            return this;
        }

        public b s(boolean z12) {
            copyOnWrite();
            ((u) this.instance).a1(z12);
            return this;
        }

        public b t(boolean z12) {
            copyOnWrite();
            ((u) this.instance).b1(z12);
            return this;
        }

        public b u(boolean z12) {
            copyOnWrite();
            ((u) this.instance).c1(z12);
            return this;
        }

        public b v(boolean z12) {
            copyOnWrite();
            ((u) this.instance).d1(z12);
            return this;
        }

        public b w(boolean z12) {
            copyOnWrite();
            ((u) this.instance).e1(z12);
            return this;
        }

        public b x(boolean z12) {
            copyOnWrite();
            ((u) this.instance).f1(z12);
            return this;
        }

        public b y(boolean z12) {
            copyOnWrite();
            ((u) this.instance).g1(z12);
            return this;
        }

        public b z(n nVar) {
            copyOnWrite();
            ((u) this.instance).h1(nVar);
            return this;
        }

        private b() {
            super(u.f64772n0);
        }
    }

    static {
        u uVar = new u();
        f64772n0 = uVar;
        uVar.makeImmutable();
    }

    private u() {
    }

    public static b I0() {
        return f64772n0.toBuilder();
    }

    public static b J0(u uVar) {
        return f64772n0.toBuilder().mergeFrom(uVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K0(n nVar) {
        Objects.requireNonNull(nVar);
        this.R = nVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L0(int i12) {
        this.f64791i = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M0(i iVar) {
        Objects.requireNonNull(iVar);
        this.f64793j = iVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N0(k kVar) {
        Objects.requireNonNull(kVar);
        this.f64782d0 = kVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O0(m mVar) {
        Objects.requireNonNull(mVar);
        this.I = mVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P0(m mVar) {
        Objects.requireNonNull(mVar);
        this.f64774J = mVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q0(int i12) {
        this.f64799m = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0(int i12) {
        this.f64797l = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0(x xVar) {
        Objects.requireNonNull(xVar);
        this.G = xVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T0(x xVar) {
        Objects.requireNonNull(xVar);
        this.H = xVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U0(boolean z12) {
        this.W = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V0(int i12) {
        this.f64789h = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W0(boolean z12) {
        this.D = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0(boolean z12) {
        this.f64778b0 = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y0(v vVar) {
        Objects.requireNonNull(vVar);
        this.V = vVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z0(boolean z12) {
        this.f64806t = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a1(boolean z12) {
        this.f64795k = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b1(boolean z12) {
        this.N = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c1(boolean z12) {
        this.f64808w = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d1(boolean z12) {
        this.f64807u = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e1(boolean z12) {
        this.f64784e0 = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f1(boolean z12) {
        this.O = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g1(boolean z12) {
        this.Z = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h1(n nVar) {
        Objects.requireNonNull(nVar);
        this.Q = nVar.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i1(int i12) {
        this.X = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j1(boolean z12) {
        this.f64780c0 = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k1(boolean z12) {
        this.f64788g0 = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l1(int i12) {
        this.f64804q = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m1(int i12) {
        this.f64803p = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n1(int i12) {
        this.f64779c = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o1(int i12) {
        this.f64781d = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p1(int i12) {
        this.f64783e = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q1(int i12) {
        this.f64777b = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r1(int i12) {
        this.f64787g = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s1(int i12) {
        this.Y = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t1(int i12) {
        this.f64785f = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u1(int i12) {
        this.E = i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v1(boolean z12) {
        this.f64792i0 = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w1(boolean z12) {
        this.f64775a = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x1(boolean z12) {
        this.f64796k0 = z12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y1(boolean z12) {
        this.P = z12;
    }

    public int A0() {
        return this.Y;
    }

    public boolean B0() {
        return this.U;
    }

    public int C0() {
        return this.f64785f;
    }

    public int D0() {
        return this.E;
    }

    public boolean E0() {
        return this.f64792i0;
    }

    public boolean F0() {
        return this.f64775a;
    }

    public boolean G0() {
        return this.f64796k0;
    }

    public boolean H0() {
        return this.P;
    }

    public d Q() {
        d dVarForNumber = d.forNumber(this.F);
        return dVarForNumber == null ? d.UNRECOGNIZED : dVarForNumber;
    }

    public i R() {
        i iVarForNumber = i.forNumber(this.f64793j);
        return iVarForNumber == null ? i.UNRECOGNIZED : iVarForNumber;
    }

    public k S() {
        k kVarForNumber = k.forNumber(this.f64782d0);
        return kVarForNumber == null ? k.UNRECOGNIZED : kVarForNumber;
    }

    public m T() {
        m mVarForNumber = m.forNumber(this.I);
        return mVarForNumber == null ? m.UNRECOGNIZED : mVarForNumber;
    }

    public m U() {
        m mVarForNumber = m.forNumber(this.f64774J);
        return mVarForNumber == null ? m.UNRECOGNIZED : mVarForNumber;
    }

    public int V() {
        return this.f64799m;
    }

    public int W() {
        return this.f64797l;
    }

    public x X() {
        x xVarForNumber = x.forNumber(this.G);
        return xVarForNumber == null ? x.UNRECOGNIZED : xVarForNumber;
    }

    public x Y() {
        x xVarForNumber = x.forNumber(this.H);
        return xVarForNumber == null ? x.UNRECOGNIZED : xVarForNumber;
    }

    public boolean Z() {
        return this.W;
    }

    public int a0() {
        return this.f64789h;
    }

    public boolean b0() {
        return this.D;
    }

    public boolean c0() {
        return this.f64778b0;
    }

    public v d0() {
        v vVarForNumber = v.forNumber(this.V);
        return vVarForNumber == null ? v.UNRECOGNIZED : vVarForNumber;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        a aVar = null;
        boolean z12 = false;
        switch (a.f64809a[methodToInvoke.ordinal()]) {
            case 1:
                return new u();
            case 2:
                return f64772n0;
            case 3:
                return null;
            case 4:
                return new b(aVar);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                u uVar = (u) obj2;
                boolean z13 = this.f64775a;
                boolean z14 = uVar.f64775a;
                this.f64775a = visitor.visitBoolean(z13, z13, z14, z14);
                int i12 = this.f64777b;
                boolean z15 = i12 != 0;
                int i13 = uVar.f64777b;
                this.f64777b = visitor.visitInt(z15, i12, i13 != 0, i13);
                int i14 = this.f64779c;
                boolean z16 = i14 != 0;
                int i15 = uVar.f64779c;
                this.f64779c = visitor.visitInt(z16, i14, i15 != 0, i15);
                int i16 = this.f64781d;
                boolean z17 = i16 != 0;
                int i17 = uVar.f64781d;
                this.f64781d = visitor.visitInt(z17, i16, i17 != 0, i17);
                int i18 = this.f64783e;
                boolean z18 = i18 != 0;
                int i19 = uVar.f64783e;
                this.f64783e = visitor.visitInt(z18, i18, i19 != 0, i19);
                int i22 = this.f64785f;
                boolean z19 = i22 != 0;
                int i23 = uVar.f64785f;
                this.f64785f = visitor.visitInt(z19, i22, i23 != 0, i23);
                int i24 = this.f64787g;
                boolean z22 = i24 != 0;
                int i25 = uVar.f64787g;
                this.f64787g = visitor.visitInt(z22, i24, i25 != 0, i25);
                int i26 = this.f64789h;
                boolean z23 = i26 != 0;
                int i27 = uVar.f64789h;
                this.f64789h = visitor.visitInt(z23, i26, i27 != 0, i27);
                int i28 = this.f64791i;
                boolean z24 = i28 != 0;
                int i29 = uVar.f64791i;
                this.f64791i = visitor.visitInt(z24, i28, i29 != 0, i29);
                int i32 = this.f64793j;
                boolean z25 = i32 != 0;
                int i33 = uVar.f64793j;
                this.f64793j = visitor.visitInt(z25, i32, i33 != 0, i33);
                boolean z26 = this.f64795k;
                boolean z27 = uVar.f64795k;
                this.f64795k = visitor.visitBoolean(z26, z26, z27, z27);
                int i34 = this.f64797l;
                boolean z28 = i34 != 0;
                int i35 = uVar.f64797l;
                this.f64797l = visitor.visitInt(z28, i34, i35 != 0, i35);
                int i36 = this.f64799m;
                boolean z29 = i36 != 0;
                int i37 = uVar.f64799m;
                this.f64799m = visitor.visitInt(z29, i36, i37 != 0, i37);
                boolean z32 = this.f64801n;
                boolean z33 = uVar.f64801n;
                this.f64801n = visitor.visitBoolean(z32, z32, z33, z33);
                boolean z34 = this.f64802o;
                boolean z35 = uVar.f64802o;
                this.f64802o = visitor.visitBoolean(z34, z34, z35, z35);
                int i38 = this.f64803p;
                boolean z36 = i38 != 0;
                int i39 = uVar.f64803p;
                this.f64803p = visitor.visitInt(z36, i38, i39 != 0, i39);
                int i42 = this.f64804q;
                boolean z37 = i42 != 0;
                int i43 = uVar.f64804q;
                this.f64804q = visitor.visitInt(z37, i42, i43 != 0, i43);
                boolean z38 = this.f64805r;
                boolean z39 = uVar.f64805r;
                this.f64805r = visitor.visitBoolean(z38, z38, z39, z39);
                boolean z42 = this.f64806t;
                boolean z43 = uVar.f64806t;
                this.f64806t = visitor.visitBoolean(z42, z42, z43, z43);
                boolean z44 = this.f64807u;
                boolean z45 = uVar.f64807u;
                this.f64807u = visitor.visitBoolean(z44, z44, z45, z45);
                boolean z46 = this.f64808w;
                boolean z47 = uVar.f64808w;
                this.f64808w = visitor.visitBoolean(z46, z46, z47, z47);
                float f12 = this.B;
                boolean z48 = f12 != 0.0f;
                float f13 = uVar.B;
                this.B = visitor.visitFloat(z48, f12, f13 != 0.0f, f13);
                boolean z49 = this.C;
                boolean z52 = uVar.C;
                this.C = visitor.visitBoolean(z49, z49, z52, z52);
                boolean z53 = this.D;
                boolean z54 = uVar.D;
                this.D = visitor.visitBoolean(z53, z53, z54, z54);
                int i44 = this.E;
                boolean z55 = i44 != 0;
                int i45 = uVar.E;
                this.E = visitor.visitInt(z55, i44, i45 != 0, i45);
                int i46 = this.F;
                boolean z56 = i46 != 0;
                int i47 = uVar.F;
                this.F = visitor.visitInt(z56, i46, i47 != 0, i47);
                int i48 = this.G;
                boolean z57 = i48 != 0;
                int i49 = uVar.G;
                this.G = visitor.visitInt(z57, i48, i49 != 0, i49);
                int i52 = this.H;
                boolean z58 = i52 != 0;
                int i53 = uVar.H;
                this.H = visitor.visitInt(z58, i52, i53 != 0, i53);
                int i54 = this.I;
                boolean z59 = i54 != 0;
                int i55 = uVar.I;
                this.I = visitor.visitInt(z59, i54, i55 != 0, i55);
                int i56 = this.f64774J;
                boolean z62 = i56 != 0;
                int i57 = uVar.f64774J;
                this.f64774J = visitor.visitInt(z62, i56, i57 != 0, i57);
                int i58 = this.K;
                boolean z63 = i58 != 0;
                int i59 = uVar.K;
                this.K = visitor.visitInt(z63, i58, i59 != 0, i59);
                int i62 = this.L;
                boolean z64 = i62 != 0;
                int i63 = uVar.L;
                this.L = visitor.visitInt(z64, i62, i63 != 0, i63);
                this.M = (w) visitor.visitMessage(this.M, uVar.M);
                boolean z65 = this.N;
                boolean z66 = uVar.N;
                this.N = visitor.visitBoolean(z65, z65, z66, z66);
                boolean z67 = this.O;
                boolean z68 = uVar.O;
                this.O = visitor.visitBoolean(z67, z67, z68, z68);
                boolean z69 = this.P;
                boolean z72 = uVar.P;
                this.P = visitor.visitBoolean(z69, z69, z72, z72);
                int i64 = this.Q;
                boolean z73 = i64 != 0;
                int i65 = uVar.Q;
                this.Q = visitor.visitInt(z73, i64, i65 != 0, i65);
                int i66 = this.R;
                boolean z74 = i66 != 0;
                int i67 = uVar.R;
                this.R = visitor.visitInt(z74, i66, i67 != 0, i67);
                float f14 = this.S;
                boolean z75 = f14 != 0.0f;
                float f15 = uVar.S;
                this.S = visitor.visitFloat(z75, f14, f15 != 0.0f, f15);
                float f16 = this.T;
                boolean z76 = f16 != 0.0f;
                float f17 = uVar.T;
                this.T = visitor.visitFloat(z76, f16, f17 != 0.0f, f17);
                boolean z77 = this.U;
                boolean z78 = uVar.U;
                this.U = visitor.visitBoolean(z77, z77, z78, z78);
                int i68 = this.V;
                boolean z79 = i68 != 0;
                int i69 = uVar.V;
                this.V = visitor.visitInt(z79, i68, i69 != 0, i69);
                boolean z82 = this.W;
                boolean z83 = uVar.W;
                this.W = visitor.visitBoolean(z82, z82, z83, z83);
                int i72 = this.X;
                boolean z84 = i72 != 0;
                int i73 = uVar.X;
                this.X = visitor.visitInt(z84, i72, i73 != 0, i73);
                int i74 = this.Y;
                boolean z85 = i74 != 0;
                int i75 = uVar.Y;
                this.Y = visitor.visitInt(z85, i74, i75 != 0, i75);
                boolean z86 = this.Z;
                boolean z87 = uVar.Z;
                this.Z = visitor.visitBoolean(z86, z86, z87, z87);
                int i76 = this.f64776a0;
                boolean z88 = i76 != 0;
                int i77 = uVar.f64776a0;
                this.f64776a0 = visitor.visitInt(z88, i76, i77 != 0, i77);
                boolean z89 = this.f64778b0;
                boolean z92 = uVar.f64778b0;
                this.f64778b0 = visitor.visitBoolean(z89, z89, z92, z92);
                boolean z93 = this.f64780c0;
                boolean z94 = uVar.f64780c0;
                this.f64780c0 = visitor.visitBoolean(z93, z93, z94, z94);
                int i78 = this.f64782d0;
                boolean z95 = i78 != 0;
                int i79 = uVar.f64782d0;
                this.f64782d0 = visitor.visitInt(z95, i78, i79 != 0, i79);
                boolean z96 = this.f64784e0;
                boolean z97 = uVar.f64784e0;
                this.f64784e0 = visitor.visitBoolean(z96, z96, z97, z97);
                boolean z98 = this.f64786f0;
                boolean z99 = uVar.f64786f0;
                this.f64786f0 = visitor.visitBoolean(z98, z98, z99, z99);
                boolean z100 = this.f64788g0;
                boolean z101 = uVar.f64788g0;
                this.f64788g0 = visitor.visitBoolean(z100, z100, z101, z101);
                boolean z102 = this.f64790h0;
                boolean z103 = uVar.f64790h0;
                this.f64790h0 = visitor.visitBoolean(z102, z102, z103, z103);
                boolean z104 = this.f64792i0;
                boolean z105 = uVar.f64792i0;
                this.f64792i0 = visitor.visitBoolean(z104, z104, z105, z105);
                boolean z106 = this.f64794j0;
                boolean z107 = uVar.f64794j0;
                this.f64794j0 = visitor.visitBoolean(z106, z106, z107, z107);
                boolean z108 = this.f64796k0;
                boolean z109 = uVar.f64796k0;
                this.f64796k0 = visitor.visitBoolean(z108, z108, z109, z109);
                boolean z110 = this.f64798l0;
                boolean z111 = uVar.f64798l0;
                this.f64798l0 = visitor.visitBoolean(z110, z110, z111, z111);
                boolean z112 = this.f64800m0;
                boolean z113 = uVar.f64800m0;
                this.f64800m0 = visitor.visitBoolean(z112, z112, z113, z113);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z12) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            switch (tag) {
                                case 0:
                                    break;
                                case 8:
                                    this.f64775a = codedInputStream.readBool();
                                    continue;
                                case 16:
                                    this.f64777b = codedInputStream.readInt32();
                                    continue;
                                case 24:
                                    this.f64779c = codedInputStream.readInt32();
                                    continue;
                                case 32:
                                    this.f64781d = codedInputStream.readInt32();
                                    continue;
                                case 40:
                                    this.f64783e = codedInputStream.readInt32();
                                    continue;
                                case 48:
                                    this.f64785f = codedInputStream.readInt32();
                                    continue;
                                case 56:
                                    this.f64787g = codedInputStream.readInt32();
                                    continue;
                                case 64:
                                    this.f64789h = codedInputStream.readInt32();
                                    continue;
                                case 72:
                                    this.f64791i = codedInputStream.readInt32();
                                    continue;
                                case 88:
                                    this.f64793j = codedInputStream.readEnum();
                                    continue;
                                case 96:
                                    this.f64795k = codedInputStream.readBool();
                                    continue;
                                case 104:
                                    this.f64797l = codedInputStream.readInt32();
                                    continue;
                                case 112:
                                    this.f64799m = codedInputStream.readInt32();
                                    continue;
                                case 120:
                                    this.f64801n = codedInputStream.readBool();
                                    continue;
                                case 128:
                                    this.f64802o = codedInputStream.readBool();
                                    continue;
                                case 144:
                                    this.f64803p = codedInputStream.readInt32();
                                    continue;
                                case 152:
                                    this.f64804q = codedInputStream.readInt32();
                                    continue;
                                case 168:
                                    this.f64805r = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.UrlPackage.Page.FEEDBACK_AND_HELP /* 176 */:
                                    this.f64806t = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.UrlPackage.Page.DUET_INVITE_FRIEND /* 184 */:
                                    this.f64807u = codedInputStream.readBool();
                                    continue;
                                case 192:
                                    this.f64808w = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.UrlPackage.Page.ACCOUNT_APPEAL_REGISTER_INFO_PAGE /* 205 */:
                                    this.B = codedInputStream.readFloat();
                                    continue;
                                case ClientEvent.UrlPackage.Page.GLASS_GUIDE_PAGE /* 208 */:
                                    this.C = codedInputStream.readBool();
                                    continue;
                                case 216:
                                    this.D = codedInputStream.readBool();
                                    continue;
                                case 224:
                                    this.E = codedInputStream.readInt32();
                                    continue;
                                case 232:
                                    this.F = codedInputStream.readEnum();
                                    continue;
                                case 240:
                                    this.G = codedInputStream.readEnum();
                                    continue;
                                case 248:
                                    this.H = codedInputStream.readEnum();
                                    continue;
                                case 256:
                                    this.I = codedInputStream.readEnum();
                                    continue;
                                case ClientEvent.UrlPackage.Page.LAB_ITEM_DETAIL_PAGE /* 264 */:
                                    this.f64774J = codedInputStream.readEnum();
                                    continue;
                                case ClientEvent.UrlPackage.Page.H5_GAME_HOMEPAGE /* 272 */:
                                    this.K = codedInputStream.readInt32();
                                    continue;
                                case ClientEvent.UrlPackage.Page.SEARCH_CONTACTS_PAGE /* 280 */:
                                    this.L = codedInputStream.readInt32();
                                    continue;
                                case ClientEvent.UrlPackage.Page.FIND_STRANGER_PAGE /* 290 */:
                                    w wVar = this.M;
                                    w.b builder = wVar != null ? wVar.toBuilder() : null;
                                    w wVar2 = (w) codedInputStream.readMessage(w.parser(), extensionRegistryLite);
                                    this.M = wVar2;
                                    if (builder != null) {
                                        builder.mergeFrom(wVar2);
                                        this.M = builder.buildPartial();
                                    } else {
                                        continue;
                                    }
                                    break;
                                case ClientEvent.UrlPackage.Page.STATUS_LIST /* 296 */:
                                    this.N = codedInputStream.readBool();
                                    continue;
                                case 304:
                                    this.O = codedInputStream.readBool();
                                    continue;
                                case 312:
                                    this.P = codedInputStream.readBool();
                                    continue;
                                case 320:
                                    this.Q = codedInputStream.readEnum();
                                    continue;
                                case ClientEvent.UrlPackage.Page.H5_UNKNOW /* 328 */:
                                    this.R = codedInputStream.readEnum();
                                    continue;
                                case 341:
                                    this.S = codedInputStream.readFloat();
                                    continue;
                                case 349:
                                    this.T = codedInputStream.readFloat();
                                    continue;
                                case 352:
                                    this.U = codedInputStream.readBool();
                                    continue;
                                case 360:
                                    this.V = codedInputStream.readEnum();
                                    continue;
                                case 368:
                                    this.W = codedInputStream.readBool();
                                    continue;
                                case 376:
                                    this.X = codedInputStream.readInt32();
                                    continue;
                                case 384:
                                    this.Y = codedInputStream.readInt32();
                                    continue;
                                case 392:
                                    this.Z = codedInputStream.readBool();
                                    continue;
                                case 400:
                                    this.f64776a0 = codedInputStream.readInt32();
                                    continue;
                                case ClientEvent.TaskEvent.Action.SHOW_PHOTO_EFFECT /* 408 */:
                                    this.f64778b0 = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.TaskEvent.Action.ADD_LOCATION /* 416 */:
                                    this.f64780c0 = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.TaskEvent.Action.SHOW_MAGIC_FACE_ENTRANCE /* 424 */:
                                    this.f64782d0 = codedInputStream.readEnum();
                                    continue;
                                case ClientEvent.TaskEvent.Action.SWITCH_CAMERA /* 432 */:
                                    this.f64784e0 = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.TaskEvent.Action.FINISH_RECORDING /* 440 */:
                                    this.f64786f0 = codedInputStream.readBool();
                                    continue;
                                case 448:
                                    this.f64788g0 = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.TaskEvent.Action.CLICK_GROUP_ONLY /* 456 */:
                                    this.f64790h0 = codedInputStream.readBool();
                                    continue;
                                case 464:
                                    this.f64792i0 = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.TaskEvent.Action.MV_COMPOSITE /* 472 */:
                                    this.f64794j0 = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.TaskEvent.Action.VIDEO_COVER_SELECTION /* 480 */:
                                    this.f64796k0 = codedInputStream.readBool();
                                    continue;
                                case ClientEvent.TaskEvent.Action.SHOW_KUAISHAN_ENTRANCE /* 488 */:
                                    this.f64798l0 = codedInputStream.readBool();
                                    continue;
                                case 496:
                                    this.f64800m0 = codedInputStream.readBool();
                                    continue;
                                default:
                                    if (!codedInputStream.skipField(tag)) {
                                        break;
                                    }
                                    break;
                            }
                            z12 = true;
                        } catch (Throwable th2) {
                            CrashReporter.logCheckedException(th2, "DaenerysCaptureConfig.class", "dynamicMethod", 58);
                            throw th2;
                        }
                    } catch (InvalidProtocolBufferException e12) {
                        CrashReporter.logCheckedException(e12, "DaenerysCaptureConfig.class", "dynamicMethod", 52);
                        throw new RuntimeException(e12.setUnfinishedMessage(this));
                    } catch (IOException e13) {
                        CrashReporter.logCheckedException(e13, "DaenerysCaptureConfig.class", "dynamicMethod", 54);
                        throw new RuntimeException(new InvalidProtocolBufferException(e13.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 7:
                break;
            case 8:
                if (f64773o0 == null) {
                    synchronized (u.class) {
                        try {
                            if (f64773o0 == null) {
                                f64773o0 = new GeneratedMessageLite.DefaultInstanceBasedParser(f64772n0);
                            }
                        } catch (Throwable th3) {
                            CrashReporter.logCheckedException(th3, "DaenerysCaptureConfig.class", "dynamicMethod", -1);
                            throw th3;
                        }
                        break;
                    }
                }
                return f64773o0;
            default:
                throw new UnsupportedOperationException();
        }
        return f64772n0;
    }

    public boolean e0() {
        return this.f64806t;
    }

    public boolean f0() {
        return this.f64795k;
    }

    public boolean g0() {
        return this.f64798l0;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i12 = this.memoizedSerializedSize;
        if (i12 != -1) {
            return i12;
        }
        boolean z12 = this.f64775a;
        int iComputeBoolSize = z12 ? 0 + CodedOutputStream.computeBoolSize(1, z12) : 0;
        int i13 = this.f64777b;
        if (i13 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(2, i13);
        }
        int i14 = this.f64779c;
        if (i14 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(3, i14);
        }
        int i15 = this.f64781d;
        if (i15 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(4, i15);
        }
        int i16 = this.f64783e;
        if (i16 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(5, i16);
        }
        int i17 = this.f64785f;
        if (i17 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(6, i17);
        }
        int i18 = this.f64787g;
        if (i18 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(7, i18);
        }
        int i19 = this.f64789h;
        if (i19 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(8, i19);
        }
        int i22 = this.f64791i;
        if (i22 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(9, i22);
        }
        if (this.f64793j != i.kAndroidCameraAuto.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(11, this.f64793j);
        }
        boolean z13 = this.f64795k;
        if (z13) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(12, z13);
        }
        int i23 = this.f64797l;
        if (i23 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(13, i23);
        }
        int i24 = this.f64799m;
        if (i24 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(14, i24);
        }
        boolean z14 = this.f64801n;
        if (z14) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(15, z14);
        }
        boolean z15 = this.f64802o;
        if (z15) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(16, z15);
        }
        int i25 = this.f64803p;
        if (i25 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(18, i25);
        }
        int i26 = this.f64804q;
        if (i26 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(19, i26);
        }
        boolean z16 = this.f64805r;
        if (z16) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(21, z16);
        }
        boolean z17 = this.f64806t;
        if (z17) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(22, z17);
        }
        boolean z18 = this.f64807u;
        if (z18) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(23, z18);
        }
        boolean z19 = this.f64808w;
        if (z19) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(24, z19);
        }
        float f12 = this.B;
        if (f12 != 0.0f) {
            iComputeBoolSize += CodedOutputStream.computeFloatSize(25, f12);
        }
        boolean z22 = this.C;
        if (z22) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(26, z22);
        }
        boolean z23 = this.D;
        if (z23) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(27, z23);
        }
        int i27 = this.E;
        if (i27 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(28, i27);
        }
        if (this.F != d.kAspectRatioNone.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(29, this.F);
        }
        int i28 = this.G;
        x xVar = x.kStabilizationModeOff;
        if (i28 != xVar.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(30, this.G);
        }
        if (this.H != xVar.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(31, this.H);
        }
        int i29 = this.I;
        m mVar = m.kCameraPreviewStream;
        if (i29 != mVar.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(32, this.I);
        }
        if (this.f64774J != mVar.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(33, this.f64774J);
        }
        int i32 = this.K;
        if (i32 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(34, i32);
        }
        int i33 = this.L;
        if (i33 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(35, i33);
        }
        if (this.M != null) {
            iComputeBoolSize += CodedOutputStream.computeMessageSize(36, s0());
        }
        boolean z24 = this.N;
        if (z24) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(37, z24);
        }
        boolean z25 = this.O;
        if (z25) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(38, z25);
        }
        boolean z26 = this.P;
        if (z26) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(39, z26);
        }
        int i34 = this.Q;
        n nVar = n.kCaptureDeviceTypeBuiltInWideAngleCamera;
        if (i34 != nVar.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(40, this.Q);
        }
        if (this.R != nVar.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(41, this.R);
        }
        float f13 = this.S;
        if (f13 != 0.0f) {
            iComputeBoolSize += CodedOutputStream.computeFloatSize(42, f13);
        }
        float f14 = this.T;
        if (f14 != 0.0f) {
            iComputeBoolSize += CodedOutputStream.computeFloatSize(43, f14);
        }
        boolean z27 = this.U;
        if (z27) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(44, z27);
        }
        if (this.V != v.kEdgeModeDefault.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(45, this.V);
        }
        boolean z28 = this.W;
        if (z28) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(46, z28);
        }
        int i35 = this.X;
        if (i35 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(47, i35);
        }
        int i36 = this.Y;
        if (i36 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(48, i36);
        }
        boolean z29 = this.Z;
        if (z29) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(49, z29);
        }
        int i37 = this.f64776a0;
        if (i37 != 0) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(50, i37);
        }
        boolean z32 = this.f64778b0;
        if (z32) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(51, z32);
        }
        boolean z33 = this.f64780c0;
        if (z33) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(52, z33);
        }
        if (this.f64782d0 != k.kCameraOutputDataTypeYuv.getNumber()) {
            iComputeBoolSize += CodedOutputStream.computeEnumSize(53, this.f64782d0);
        }
        boolean z34 = this.f64784e0;
        if (z34) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(54, z34);
        }
        boolean z35 = this.f64786f0;
        if (z35) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(55, z35);
        }
        boolean z36 = this.f64788g0;
        if (z36) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(56, z36);
        }
        boolean z37 = this.f64790h0;
        if (z37) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(57, z37);
        }
        boolean z38 = this.f64792i0;
        if (z38) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(58, z38);
        }
        boolean z39 = this.f64794j0;
        if (z39) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(59, z39);
        }
        boolean z42 = this.f64796k0;
        if (z42) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(60, z42);
        }
        boolean z43 = this.f64798l0;
        if (z43) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(61, z43);
        }
        boolean z44 = this.f64800m0;
        if (z44) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(62, z44);
        }
        this.memoizedSerializedSize = iComputeBoolSize;
        return iComputeBoolSize;
    }

    public boolean h0() {
        return this.N;
    }

    public boolean i0() {
        return this.f64808w;
    }

    public boolean j0() {
        return this.f64807u;
    }

    public boolean k0() {
        return this.f64786f0;
    }

    public boolean l0() {
        return this.f64784e0;
    }

    public boolean m0() {
        return this.O;
    }

    public boolean n0() {
        return this.f64800m0;
    }

    public boolean o0() {
        return this.Z;
    }

    public int p0() {
        return this.X;
    }

    public boolean q0() {
        return this.f64780c0;
    }

    public boolean r0() {
        return this.f64788g0;
    }

    public w s0() {
        w wVar = this.M;
        return wVar == null ? w.b() : wVar;
    }

    public int t0() {
        return this.f64804q;
    }

    public int u0() {
        return this.f64803p;
    }

    public int v0() {
        return this.f64779c;
    }

    public int w0() {
        return this.f64781d;
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        boolean z12 = this.f64775a;
        if (z12) {
            codedOutputStream.writeBool(1, z12);
        }
        int i12 = this.f64777b;
        if (i12 != 0) {
            codedOutputStream.writeInt32(2, i12);
        }
        int i13 = this.f64779c;
        if (i13 != 0) {
            codedOutputStream.writeInt32(3, i13);
        }
        int i14 = this.f64781d;
        if (i14 != 0) {
            codedOutputStream.writeInt32(4, i14);
        }
        int i15 = this.f64783e;
        if (i15 != 0) {
            codedOutputStream.writeInt32(5, i15);
        }
        int i16 = this.f64785f;
        if (i16 != 0) {
            codedOutputStream.writeInt32(6, i16);
        }
        int i17 = this.f64787g;
        if (i17 != 0) {
            codedOutputStream.writeInt32(7, i17);
        }
        int i18 = this.f64789h;
        if (i18 != 0) {
            codedOutputStream.writeInt32(8, i18);
        }
        int i19 = this.f64791i;
        if (i19 != 0) {
            codedOutputStream.writeInt32(9, i19);
        }
        if (this.f64793j != i.kAndroidCameraAuto.getNumber()) {
            codedOutputStream.writeEnum(11, this.f64793j);
        }
        boolean z13 = this.f64795k;
        if (z13) {
            codedOutputStream.writeBool(12, z13);
        }
        int i22 = this.f64797l;
        if (i22 != 0) {
            codedOutputStream.writeInt32(13, i22);
        }
        int i23 = this.f64799m;
        if (i23 != 0) {
            codedOutputStream.writeInt32(14, i23);
        }
        boolean z14 = this.f64801n;
        if (z14) {
            codedOutputStream.writeBool(15, z14);
        }
        boolean z15 = this.f64802o;
        if (z15) {
            codedOutputStream.writeBool(16, z15);
        }
        int i24 = this.f64803p;
        if (i24 != 0) {
            codedOutputStream.writeInt32(18, i24);
        }
        int i25 = this.f64804q;
        if (i25 != 0) {
            codedOutputStream.writeInt32(19, i25);
        }
        boolean z16 = this.f64805r;
        if (z16) {
            codedOutputStream.writeBool(21, z16);
        }
        boolean z17 = this.f64806t;
        if (z17) {
            codedOutputStream.writeBool(22, z17);
        }
        boolean z18 = this.f64807u;
        if (z18) {
            codedOutputStream.writeBool(23, z18);
        }
        boolean z19 = this.f64808w;
        if (z19) {
            codedOutputStream.writeBool(24, z19);
        }
        float f12 = this.B;
        if (f12 != 0.0f) {
            codedOutputStream.writeFloat(25, f12);
        }
        boolean z22 = this.C;
        if (z22) {
            codedOutputStream.writeBool(26, z22);
        }
        boolean z23 = this.D;
        if (z23) {
            codedOutputStream.writeBool(27, z23);
        }
        int i26 = this.E;
        if (i26 != 0) {
            codedOutputStream.writeInt32(28, i26);
        }
        if (this.F != d.kAspectRatioNone.getNumber()) {
            codedOutputStream.writeEnum(29, this.F);
        }
        int i27 = this.G;
        x xVar = x.kStabilizationModeOff;
        if (i27 != xVar.getNumber()) {
            codedOutputStream.writeEnum(30, this.G);
        }
        if (this.H != xVar.getNumber()) {
            codedOutputStream.writeEnum(31, this.H);
        }
        int i28 = this.I;
        m mVar = m.kCameraPreviewStream;
        if (i28 != mVar.getNumber()) {
            codedOutputStream.writeEnum(32, this.I);
        }
        if (this.f64774J != mVar.getNumber()) {
            codedOutputStream.writeEnum(33, this.f64774J);
        }
        int i29 = this.K;
        if (i29 != 0) {
            codedOutputStream.writeInt32(34, i29);
        }
        int i32 = this.L;
        if (i32 != 0) {
            codedOutputStream.writeInt32(35, i32);
        }
        if (this.M != null) {
            codedOutputStream.writeMessage(36, s0());
        }
        boolean z24 = this.N;
        if (z24) {
            codedOutputStream.writeBool(37, z24);
        }
        boolean z25 = this.O;
        if (z25) {
            codedOutputStream.writeBool(38, z25);
        }
        boolean z26 = this.P;
        if (z26) {
            codedOutputStream.writeBool(39, z26);
        }
        int i33 = this.Q;
        n nVar = n.kCaptureDeviceTypeBuiltInWideAngleCamera;
        if (i33 != nVar.getNumber()) {
            codedOutputStream.writeEnum(40, this.Q);
        }
        if (this.R != nVar.getNumber()) {
            codedOutputStream.writeEnum(41, this.R);
        }
        float f13 = this.S;
        if (f13 != 0.0f) {
            codedOutputStream.writeFloat(42, f13);
        }
        float f14 = this.T;
        if (f14 != 0.0f) {
            codedOutputStream.writeFloat(43, f14);
        }
        boolean z27 = this.U;
        if (z27) {
            codedOutputStream.writeBool(44, z27);
        }
        if (this.V != v.kEdgeModeDefault.getNumber()) {
            codedOutputStream.writeEnum(45, this.V);
        }
        boolean z28 = this.W;
        if (z28) {
            codedOutputStream.writeBool(46, z28);
        }
        int i34 = this.X;
        if (i34 != 0) {
            codedOutputStream.writeInt32(47, i34);
        }
        int i35 = this.Y;
        if (i35 != 0) {
            codedOutputStream.writeInt32(48, i35);
        }
        boolean z29 = this.Z;
        if (z29) {
            codedOutputStream.writeBool(49, z29);
        }
        int i36 = this.f64776a0;
        if (i36 != 0) {
            codedOutputStream.writeInt32(50, i36);
        }
        boolean z32 = this.f64778b0;
        if (z32) {
            codedOutputStream.writeBool(51, z32);
        }
        boolean z33 = this.f64780c0;
        if (z33) {
            codedOutputStream.writeBool(52, z33);
        }
        if (this.f64782d0 != k.kCameraOutputDataTypeYuv.getNumber()) {
            codedOutputStream.writeEnum(53, this.f64782d0);
        }
        boolean z34 = this.f64784e0;
        if (z34) {
            codedOutputStream.writeBool(54, z34);
        }
        boolean z35 = this.f64786f0;
        if (z35) {
            codedOutputStream.writeBool(55, z35);
        }
        boolean z36 = this.f64788g0;
        if (z36) {
            codedOutputStream.writeBool(56, z36);
        }
        boolean z37 = this.f64790h0;
        if (z37) {
            codedOutputStream.writeBool(57, z37);
        }
        boolean z38 = this.f64792i0;
        if (z38) {
            codedOutputStream.writeBool(58, z38);
        }
        boolean z39 = this.f64794j0;
        if (z39) {
            codedOutputStream.writeBool(59, z39);
        }
        boolean z42 = this.f64796k0;
        if (z42) {
            codedOutputStream.writeBool(60, z42);
        }
        boolean z43 = this.f64798l0;
        if (z43) {
            codedOutputStream.writeBool(61, z43);
        }
        boolean z44 = this.f64800m0;
        if (z44) {
            codedOutputStream.writeBool(62, z44);
        }
    }

    public int x0() {
        return this.f64783e;
    }

    public int y0() {
        return this.f64777b;
    }

    public int z0() {
        return this.f64787g;
    }
}
