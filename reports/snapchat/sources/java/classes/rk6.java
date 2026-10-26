package defpackage;

import android.opengl.GLES20;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: rk6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C35407rk6 implements InterfaceC36645sk6, InterfaceC33258q0b {
    public final InterfaceC34329qs5 c;
    public final C34945rN1 d;
    public C34495r0b e;
    public final InterfaceC6336Mgc g;
    public final InterfaceC6336Mgc h;
    public final SS5 i;
    public final AtomicReference l;
    public final InterfaceC29621n48 m;
    public final InterfaceC26742kk6 n;
    public final C27980lk6 o;
    public C36823st4 p;
    public C36823st4 q;
    public final C24957jIg f = new C24957jIg();
    public final ArrayList j = new ArrayList();
    public final ConcurrentLinkedQueue k = new ConcurrentLinkedQueue();

    public C35407rk6(InterfaceC29621n48 interfaceC29621n48, InterfaceC26742kk6 interfaceC26742kk6, InterfaceC34329qs5 interfaceC34329qs5, C34945rN1 c34945rN1, AtomicReference atomicReference, InterfaceC6336Mgc interfaceC6336Mgc, InterfaceC6336Mgc interfaceC6336Mgc2, SS5 ss5, C27980lk6 c27980lk6) {
        this.c = interfaceC34329qs5;
        this.d = c34945rN1;
        this.m = interfaceC29621n48;
        this.n = interfaceC26742kk6;
        this.l = atomicReference;
        this.g = interfaceC6336Mgc;
        this.h = interfaceC6336Mgc2;
        this.i = ss5;
        this.o = c27980lk6;
    }

    public static void k(S45 s45, int i, boolean z, boolean z2, C14533at1 c14533at1, long j, C24957jIg c24957jIg, C9471Si6 c9471Si6, boolean z3, boolean z4, C27980lk6 c27980lk6) throws C6329Mg5 {
        C24957jIg c24957jIg2;
        boolean z5;
        C32932pk6 c32932pk6;
        int i2;
        EnumC2325Emg enumC2325Emg;
        C24957jIg c24957jIg3;
        C24957jIg c24957jIg4;
        S45 s46 = S45.FREEZE;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        c9471Si6.l(c14533at1.a.a, -1L);
        c14533at1.b.b();
        if (s45 != S45.CLEAR) {
            C32932pk6 c32932pk7 = c14533at1.c;
            EnumC2325Emg enumC2325Emg2 = EnumC2325Emg.TEXTURE_2D;
            if (z) {
                c24957jIg3 = InterfaceC36645sk6.b;
                c24957jIg2 = InterfaceC36645sk6.a;
                z5 = (s45 == s46 || z4) ? false : true;
                c32932pk6 = c32932pk7;
                i2 = i;
                enumC2325Emg = enumC2325Emg2;
            } else {
                c24957jIg2 = InterfaceC36645sk6.a;
                z5 = !z2 ? s45 == s46 || z4 : s45 == s46 || z4;
                c32932pk6 = c32932pk7;
                i2 = i;
                enumC2325Emg = enumC2325Emg2;
                j = j;
                c24957jIg3 = c24957jIg2;
                c24957jIg4 = c24957jIg;
            }
            c32932pk6.c(i2, enumC2325Emg, j, c24957jIg3, c24957jIg4, c24957jIg2, z5, c9471Si6, z3, c27980lk6);
            c9471Si6.l(c14533at1.a.a, SystemClock.elapsedRealtime() - jElapsedRealtime);
        }
        c32932pk6 = c14533at1.c;
        i2 = -1;
        EnumC2325Emg enumC2325Emg3 = EnumC2325Emg.TEXTURE_2D;
        c24957jIg2 = InterfaceC36645sk6.a;
        z5 = !z4;
        enumC2325Emg = enumC2325Emg3;
        c24957jIg3 = c24957jIg2;
        c24957jIg4 = c24957jIg2;
        c32932pk6.c(i2, enumC2325Emg, j, c24957jIg3, c24957jIg4, c24957jIg2, z5, c9471Si6, z3, c27980lk6);
        c9471Si6.l(c14533at1.a.a, SystemClock.elapsedRealtime() - jElapsedRealtime);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0103  */
    @Override // defpackage.InterfaceC36645sk6
    public final void a(C7363Og5 c7363Og5, S45 s45, float[] fArr, int i, EnumC2325Emg enumC2325Emg, long j, long j2, C24957jIg c24957jIg, C24957jIg c24957jIg2, Collection collection, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i2) throws C6329Mg5 {
        C9471Si6 c9471Si6;
        C24957jIg c24957jIg3;
        float[] fArr2;
        int i3;
        EnumC2325Emg enumC2325Emg2;
        boolean zC;
        C24957jIg c24957jIg4;
        float[] fArr3;
        int i4;
        EnumC2325Emg enumC2325EmgE;
        int i5;
        C32020p0b c32020p0b;
        char c;
        int i6;
        float[] fArr4;
        boolean z6;
        C9471Si6 c9471Si7;
        C24957jIg c24957jIg5;
        boolean z7;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        d(collection);
        if (this.j.isEmpty()) {
            c9471Si6 = new C9471Si6();
        } else {
            ArrayList arrayList = this.j;
            c9471Si6 = (C9471Si6) arrayList.remove(arrayList.size() - 1);
        }
        C9471Si6 c9471Si8 = c9471Si6;
        c9471Si8.j();
        c7363Og5.g();
        if (this.n.isEnabled()) {
            c24957jIg3 = c24957jIg;
            fArr2 = fArr;
            i3 = i;
            enumC2325Emg2 = enumC2325Emg;
            zC = this.n.c(i3, enumC2325Emg2, fArr2, c24957jIg3.c);
        } else {
            fArr2 = fArr;
            i3 = i;
            enumC2325Emg2 = enumC2325Emg;
            c24957jIg3 = c24957jIg;
            zC = false;
        }
        if (zC) {
            int iB = this.n.b();
            enumC2325EmgE = this.n.e();
            float[] fArr5 = this.n.a().c;
            C24957jIg c24957jIgD = this.n.d();
            if (!z4) {
                this.o.c();
            }
            i4 = iB;
            fArr3 = fArr5;
            c24957jIg4 = c24957jIgD;
        } else {
            c24957jIg4 = c24957jIg3;
            fArr3 = fArr2;
            i4 = i3;
            enumC2325EmgE = enumC2325Emg2;
        }
        boolean zH = ((InterfaceC39171umg) this.m.get()).h();
        long jElapsedRealtime2 = SystemClock.elapsedRealtime();
        ((InterfaceC39171umg) this.m.get()).b(i4, enumC2325EmgE == EnumC2325Emg.EXTERNAL_OES ? 36197 : 3553, j, fArr3, c24957jIg4.c, NY7.O0(z4, z3, z2));
        if (zH && !z4) {
            this.o.b();
        }
        c9471Si8.k(SystemClock.elapsedRealtime() - jElapsedRealtime2);
        long jElapsedRealtime3 = SystemClock.elapsedRealtime();
        try {
            try {
                C32020p0b c32020p0bB = this.e.b();
                if (!z4) {
                    C27980lk6 c27980lk6 = this.o;
                    if (c27980lk6.b && !c27980lk6.i && c27980lk6.c != 0) {
                        long jA = c27980lk6.a();
                        c27980lk6.e.R = jA;
                        c27980lk6.g.R += jA;
                    }
                }
                long jElapsedRealtime4 = SystemClock.elapsedRealtime() - jElapsedRealtime3;
                synchronized (c9471Si8) {
                    c9471Si8.a.f = jElapsedRealtime4;
                }
                synchronized (c32020p0bB) {
                    i5 = 1;
                    c32020p0bB.c++;
                }
                c32020p0bB.e = c9471Si8;
                try {
                    long jElapsedRealtime5 = SystemClock.elapsedRealtime();
                    c32020p0bB.a.a();
                    boolean z8 = zH;
                    int iH = h(fArr3, i4, zC, z8);
                    c32020p0bB.a.b(true);
                    c32020p0bB.f.a();
                    long jElapsedRealtime6 = SystemClock.elapsedRealtime() - jElapsedRealtime5;
                    synchronized (c9471Si8) {
                        c9471Si8.a.g = jElapsedRealtime6;
                    }
                    if (!z4) {
                        this.o.d();
                    }
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        C14533at1 c14533at1 = (C14533at1) it.next();
                        try {
                            N6g n6g = c14533at1.a;
                            if (n6g.m) {
                                n6g.o += i5;
                            }
                            if (n6g.n) {
                                if (n6g.d) {
                                    boolean z9 = n6g.f || z4;
                                    boolean z10 = iH != 0;
                                    if (z9 || !z10) {
                                        c32020p0b = c32020p0bB;
                                        i6 = i4;
                                        fArr4 = fArr3;
                                        z6 = zC;
                                        c9471Si7 = c9471Si8;
                                        c24957jIg5 = c24957jIg4;
                                        z7 = z8;
                                        j(z6, s45, c32020p0b, z7, c14533at1, j2, c24957jIg5, z, z4);
                                    } else {
                                        c32020p0b = c32020p0bB;
                                        c24957jIg5 = c24957jIg4;
                                        c = 1;
                                        z7 = z8;
                                        i6 = i4;
                                        fArr4 = fArr3;
                                        z6 = zC;
                                        c9471Si7 = c9471Si8;
                                        try {
                                            i(false, true, s45, iH, c14533at1, fArr3, j, c24957jIg5, c9471Si8, z, z4);
                                        } catch (C6329Mg5 e) {
                                            e = e;
                                            Object[] objArr = new Object[2];
                                            objArr[0] = Boolean.valueOf(GLES20.glIsTexture(c32020p0b.a.b));
                                            objArr[c] = c14533at1.a.a;
                                            throw new C6329Mg5(String.format("error in rendering, glIsTexture: %b, surfaceType %s", objArr), e);
                                        }
                                    }
                                } else {
                                    c32020p0b = c32020p0bB;
                                    i6 = i4;
                                    fArr4 = fArr3;
                                    z6 = zC;
                                    c9471Si7 = c9471Si8;
                                    c24957jIg5 = c24957jIg4;
                                    z7 = z8;
                                    i(z6, false, s45, i6, c14533at1, fArr4, j, c24957jIg5, c9471Si7, z, z4);
                                }
                                c32020p0bB = c32020p0b;
                                c9471Si8 = c9471Si7;
                                z8 = z7;
                                c24957jIg4 = c24957jIg5;
                                i4 = i6;
                                fArr3 = fArr4;
                                zC = z6;
                                i5 = 1;
                            }
                        } catch (C6329Mg5 e2) {
                            e = e2;
                            c32020p0b = c32020p0bB;
                            c = 1;
                        }
                    }
                    C32020p0b c32020p0b2 = c32020p0bB;
                    C9471Si6 c9471Si9 = c9471Si8;
                    long jElapsedRealtime7 = SystemClock.elapsedRealtime() - jElapsedRealtime;
                    synchronized (c9471Si9) {
                        c9471Si9.b = jElapsedRealtime7;
                    }
                    c32020p0b2.a();
                } catch (IE6 e3) {
                    throw new C6329Mg5("Render to frame buffer failed, errorMessage: ", e3);
                }
            } catch (InterruptedException e4) {
                Thread.currentThread().interrupt();
                throw new C6329Mg5(e4);
            }
        } catch (IE6 e5) {
            throw new C6329Mg5(e5);
        }
    }

    @Override // defpackage.InterfaceC36645sk6
    public final void b(int i, float[] fArr, EnumC2325Emg enumC2325Emg) {
        g(enumC2325Emg).q(i, 0L, new C24957jIg(fArr), null);
    }

    @Override // defpackage.InterfaceC36645sk6
    public final void c() {
        d(new ArrayList());
    }

    public final void d(Collection collection) {
        long j;
        while (true) {
            C9471Si6 c9471Si6 = (C9471Si6) this.k.poll();
            if (c9471Si6 == null) {
                return;
            }
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                C14533at1 c14533at1 = (C14533at1) it.next();
                synchronized (c9471Si6) {
                    j = c9471Si6.b;
                }
                c14533at1.a(c9471Si6, j);
            }
            this.j.add(c9471Si6);
        }
    }

    public final C36823st4 e(EnumC2325Emg enumC2325Emg) throws C6329Mg5 {
        C36823st4 c36823st4 = new C36823st4();
        try {
            C24957jIg c24957jIg = InterfaceC36645sk6.a;
            C42460xRc c42460xRc = this.e.e;
            c36823st4.y(c24957jIg, c24957jIg, c42460xRc.a, c42460xRc.b, enumC2325Emg, null, (InterfaceC5205Kbe) this.g.get(), (InterfaceC6460Mmg) this.h.get());
            c36823st4.a = false;
            c36823st4.b = false;
            return c36823st4;
        } catch (AbstractC0987Bx9 e) {
            try {
                c36823st4.p();
            } catch (IE6 unused) {
            }
            throw new C6329Mg5(e);
        }
    }

    public final void f() {
        try {
            try {
                C34495r0b c34495r0b = this.e;
                if (c34495r0b != null) {
                    synchronized (c34495r0b.a) {
                        c34495r0b.c();
                        c34495r0b.b = true;
                    }
                }
                this.e = null;
            } catch (IE6 e) {
                throw new C6329Mg5(e);
            }
        } catch (Throwable th) {
            this.e = null;
            throw th;
        }
    }

    public final C36823st4 g(EnumC2325Emg enumC2325Emg) {
        if (enumC2325Emg == EnumC2325Emg.TEXTURE_2D) {
            if (this.p == null) {
                this.p = e(enumC2325Emg);
            }
            return this.p;
        }
        if (this.q == null) {
            this.q = e(enumC2325Emg);
        }
        return this.q;
    }

    public final int h(float[] fArr, int i, boolean z, boolean z2) throws C6329Mg5 {
        if (!z2) {
            if (!z) {
                try {
                    g(EnumC2325Emg.EXTERNAL_OES).q(i, 0L, new C24957jIg((float[]) fArr.clone()), null);
                    return 0;
                } catch (AbstractC0987Bx9 e) {
                    throw new C6329Mg5(e);
                }
            }
            try {
                C36823st4 c36823st4G = g(EnumC2325Emg.TEXTURE_2D);
                int iB = this.n.b();
                c36823st4G.v(InterfaceC36645sk6.a);
                c36823st4G.q(iB, 0L, new C24957jIg((float[]) fArr.clone()), null);
                return 0;
            } catch (AbstractC0987Bx9 e2) {
                throw new C6329Mg5(e2);
            }
        }
        InterfaceC39171umg interfaceC39171umg = (InterfaceC39171umg) this.m.get();
        EnumC29268mmg enumC29268mmg = EnumC29268mmg.DEFAULT;
        interfaceC39171umg.c(enumC29268mmg, this.f.c, false);
        try {
            C36823st4 c36823st4G2 = g(EnumC2325Emg.TEXTURE_2D);
            c36823st4G2.v(this.f);
            InterfaceC32982pmg interfaceC32982pmgE = ((InterfaceC39171umg) this.m.get()).e(enumC29268mmg);
            int iC = interfaceC32982pmgE.c();
            C1429Cte c1429Cte = (C1429Cte) this.l.get();
            if (c1429Cte == null || c1429Cte.a != interfaceC32982pmgE.e() || c1429Cte.b != interfaceC32982pmgE.d()) {
                this.l.set(new C1429Cte(interfaceC32982pmgE.e(), interfaceC32982pmgE.d()));
            }
            interfaceC32982pmgE.b();
            c36823st4G2.q(iC, 0L, InterfaceC36645sk6.a, null);
            InterfaceC32982pmg interfaceC32982pmgE2 = ((InterfaceC39171umg) this.m.get()).e(EnumC29268mmg.PREVIEW);
            try {
                return interfaceC32982pmgE2.c();
            } finally {
                interfaceC32982pmgE2.b();
            }
        } catch (AbstractC0987Bx9 e3) {
            throw new C6329Mg5(e3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00a3 A[Catch: all -> 0x00e4, TryCatch #0 {, blocks: (B:3:0x000a, B:5:0x003a, B:9:0x0048, B:20:0x00a3, B:25:0x00b0, B:31:0x00c5, B:26:0x00b7, B:12:0x0068, B:17:0x0089), top: B:37:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:24:0x00af  */
    /* JADX WARN: Code duplicated, block: B:26:0x00b7 A[Catch: all -> 0x00e4, TryCatch #0 {, blocks: (B:3:0x000a, B:5:0x003a, B:9:0x0048, B:20:0x00a3, B:25:0x00b0, B:31:0x00c5, B:26:0x00b7, B:12:0x0068, B:17:0x0089), top: B:37:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:30:0x00c3  */
    public final void i(boolean z, boolean z2, S45 s45, int i, C14533at1 c14533at1, float[] fArr, long j, C24957jIg c24957jIg, C9471Si6 c9471Si6, boolean z3, boolean z4) {
        C24957jIg c24957jIg2;
        S45 s46;
        C32932pk6 c32932pk6;
        EnumC2325Emg enumC2325Emg;
        C24957jIg c24957jIg3;
        boolean z5;
        S45 s47 = S45.FREEZE;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        c9471Si6.l(c14533at1.a.a, -1L);
        c14533at1.b.b();
        C24957jIg c24957jIg4 = new C24957jIg((float[]) fArr.clone());
        c24957jIg4.n(-0.5f, -0.5f);
        c24957jIg4.e(false);
        c24957jIg4.n(0.5f, 0.5f);
        if (s45 != S45.CLEAR) {
            c24957jIg2 = c24957jIg4;
            s46 = s47;
            if (z2) {
                ((InterfaceC39171umg) this.m.get()).c(EnumC29268mmg.PREVIEW, this.f.c, true);
                c14533at1.c.c(i, EnumC2325Emg.TEXTURE_2D, j, this.f, c24957jIg, InterfaceC36645sk6.a, (s45 == s46 || z4) ? false : true, c9471Si6, z3, this.o);
            }
            if (z) {
                c32932pk6 = c14533at1.c;
                enumC2325Emg = EnumC2325Emg.TEXTURE_2D;
                c24957jIg3 = InterfaceC36645sk6.a;
                if (s45 != s46 || z4) {
                    z5 = false;
                } else {
                    z5 = true;
                }
            } else {
                c32932pk6 = c14533at1.c;
                enumC2325Emg = EnumC2325Emg.EXTERNAL_OES;
                c24957jIg3 = InterfaceC36645sk6.a;
                if (s45 != s46 || z4) {
                    z5 = false;
                } else {
                    z5 = true;
                }
            }
            c32932pk6.c(i, enumC2325Emg, j, c24957jIg2, c24957jIg, c24957jIg3, z5, c9471Si6, z3, this.o);
            c9471Si6.l(c14533at1.a.a, SystemClock.elapsedRealtime() - jElapsedRealtime);
        }
        C32932pk6 c32932pk7 = c14533at1.c;
        EnumC2325Emg enumC2325Emg2 = EnumC2325Emg.TEXTURE_2D;
        C24957jIg c24957jIg5 = InterfaceC36645sk6.a;
        c24957jIg2 = c24957jIg4;
        s46 = s47;
        c32932pk7.c(-1, enumC2325Emg2, j, c24957jIg5, c24957jIg5, c24957jIg5, !z4, c9471Si6, z3, this.o);
        if (z) {
            c32932pk6 = c14533at1.c;
            enumC2325Emg = EnumC2325Emg.TEXTURE_2D;
            c24957jIg3 = InterfaceC36645sk6.a;
            if (s45 != s46) {
                z5 = false;
            } else {
                z5 = false;
            }
        } else {
            c32932pk6 = c14533at1.c;
            enumC2325Emg = EnumC2325Emg.EXTERNAL_OES;
            c24957jIg3 = InterfaceC36645sk6.a;
            if (s45 != s46) {
                z5 = false;
            } else {
                z5 = false;
            }
        }
        c32932pk6.c(i, enumC2325Emg, j, c24957jIg2, c24957jIg, c24957jIg3, z5, c9471Si6, z3, this.o);
        c9471Si6.l(c14533at1.a.a, SystemClock.elapsedRealtime() - jElapsedRealtime);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0073, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void j(boolean z, S45 s45, C32020p0b c32020p0b, boolean z2, C14533at1 c14533at1, long j, C24957jIg c24957jIg, boolean z3, boolean z4) {
        HandlerC20109fNh handlerC20109fNh = c14533at1.e;
        if (handlerC20109fNh != null) {
            synchronized (c32020p0b) {
                c32020p0b.c++;
            }
            if (!handlerC20109fNh.post(new RunnableC34170qk6(this, c14533at1, c32020p0b, s45, z2, z, j, c24957jIg, z3, z4))) {
                c32020p0b.a();
            }
            return;
        }
        if (s45 == S45.CLEAR) {
            c14533at1.b.b();
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            GLES20.glClear(16384);
        }
        k(s45, c32020p0b.a.b, z2, z, c14533at1, j, c24957jIg, c32020p0b.e, z3, z4, this.o);
    }
}
