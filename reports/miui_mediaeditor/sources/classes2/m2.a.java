package m2;

import a6.b;
import android.util.Log;
import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.entity.ImageBeautyData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.google.android.gms.internal.mlkit_language_id_common.n9;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.text.e;
import kotlin.text.i;

/* JADX INFO: loaded from: classes.dex */
public final class a extends BaseSingleModel {
    public final double[] A;
    public final double[] B;
    public final double[] C;
    public final double[] D;
    public final double[] E;
    public final double[] F;
    public final double[] G;
    public final double[] H;
    public final double[] I;
    public final double[] J;
    public final double[] K;
    public final double[] L;
    public final double[] M;
    public final double[] N;
    public final double[] O;
    public final double[] P;
    public final double[] Q;
    public final double[] R;
    public final double[] S;
    public final double[] T;
    public final double[] U;
    public String V;
    public double W;
    public String X;
    public String Y;
    public String Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public double f14090a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public double f14091b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public double f14092c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public double f14093d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public double f14094e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public double f14095f0;
    public final String g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public double f14096g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f14097h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public double f14098h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14099i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public double f14100i0;
    public String j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public double f14101j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public double f14102k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public double f14103k0;
    public final double[] l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public double f14104l0;
    public final double[] m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public double f14105m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final double[] f14106n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final LinkedList<C0264a> f14107n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final double[] f14108o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public String f14109o0;
    public final double[] p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final double[] f14110q;
    public final double[] r;
    public final double[] s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final double[] f14111t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final double[] f14112u;
    public final double[] v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final double[] f14113w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final double[] f14114x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final double[] f14115y;
    public final double[] z;

    /* JADX INFO: renamed from: m2.a$a, reason: collision with other inner class name */
    public final class C0264a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f14116a = 0.5d;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double f14117b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public double f14118c;
        public double d;

        public C0264a() {
        }

        public final C0264a a() {
            a aVar = a.this;
            aVar.f14097h = false;
            C0264a c0264a = aVar.new C0264a();
            c0264a.d(this.f14116a);
            c0264a.c(this.f14117b);
            c0264a.e(this.f14118c);
            c0264a.b(this.d);
            aVar.f14097h = true;
            return c0264a;
        }

        public final void b(double d) {
            this.d = d;
            a aVar = a.this;
            if (aVar.f14097h) {
                aVar.e(Double.valueOf(d), "body_manual_leg_bottom_offset");
            }
        }

        public final void c(double d) {
            this.f14117b = d;
            a aVar = a.this;
            if (aVar.f14097h) {
                aVar.e(Double.valueOf(d), "body_manual_leg_center_offset");
            }
        }

        public final void d(double d) {
            this.f14116a = d;
            a aVar = a.this;
            if (aVar.f14097h) {
                aVar.e(Double.valueOf(d), "body_manual_leg_slim");
            }
        }

        public final void e(double d) {
            this.f14118c = d;
            a aVar = a.this;
            if (aVar.f14097h) {
                aVar.e(Double.valueOf(d), "body_manual_leg_top_offset");
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof C0264a)) {
                return false;
            }
            C0264a c0264a = (C0264a) obj;
            if (c0264a.f14116a == this.f14116a) {
                if (c0264a.f14117b == this.f14117b) {
                    if (c0264a.f14118c == this.f14118c) {
                        if (c0264a.d == this.d) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public a(j2.a aVar) {
        super(aVar);
        this.g = "ImageBeauty";
        this.f14099i = 1;
        this.j = "origin";
        this.l = new double[5];
        this.m = new double[5];
        this.f14106n = new double[5];
        this.f14108o = new double[5];
        this.p = new double[5];
        this.f14110q = new double[5];
        this.r = new double[5];
        this.s = new double[5];
        this.f14111t = new double[5];
        this.f14112u = new double[5];
        this.v = new double[5];
        this.f14113w = new double[5];
        this.f14114x = new double[5];
        this.f14115y = new double[5];
        this.z = new double[5];
        this.A = new double[5];
        this.B = new double[5];
        this.C = new double[5];
        this.D = new double[5];
        this.E = new double[5];
        this.F = new double[5];
        this.G = new double[5];
        this.H = new double[5];
        this.I = new double[5];
        this.J = new double[5];
        this.K = new double[5];
        this.L = new double[5];
        this.M = new double[5];
        this.N = new double[5];
        this.O = new double[5];
        this.P = new double[5];
        this.Q = new double[5];
        this.R = new double[5];
        this.S = new double[5];
        this.T = new double[5];
        this.U = new double[5];
        this.V = "{\"p_order\":[],\"cur_order\":0,\"last_order_num\":0}";
        this.W = 1.0d;
        this.X = "{\"p_r\":[],\"p_vec\":[],\"cur_order\":0}";
        this.Y = "{\"p_r\":[],\"p_vec\":[],\"cur_order\":0}";
        this.Z = "{\"p_r\":[],\"p_vec\":[],\"cur_order\":0}";
        this.f14093d0 = 0.5d;
        this.f14107n0 = new LinkedList<>();
        this.f14109o0 = "{\"p_rect\":[],\"p_do_face_beauty\":0,\"p_bdyscores\":[],\"p_jointscores\":[],\"p_pofdirpoints\":[],\"p_pofdirscores\":[]}";
    }

    public static String i(int i10, String str) {
        return e.g0("{\"faceid\":" + i10 + ",\"param\":\"" + str + "\"}");
    }

    public final void A(double d, int i10) {
        h0(i10, "image_aver", this.m, d);
    }

    public final void B(double d, int i10) {
        h0(i10, "image_blur", this.l, d);
    }

    public final void C(double d, int i10) {
        h0(i10, "image_intensity_brow", this.P, d);
    }

    public final void D(double d, int i10) {
        h0(i10, "image_cheek_narrow", this.E, d);
    }

    public final void E(double d, int i10) {
        h0(i10, "image_cheek_small", this.F, d);
    }

    public final void F(double d, int i10) {
        h0(i10, "image_cheek_thinning", this.C, d);
    }

    public final void G(double d, int i10) {
        h0(i10, "image_cheek_v", this.D, d);
    }

    public final void H(double d, int i10) {
        h0(i10, "image_intensity_cheekbone", this.R, d);
    }

    public final void I(double d, int i10) {
        h0(i10, "image_intensity_chin", this.H, d);
    }

    public final void J(double d, int i10) {
        h0(i10, "image_decreepattern", this.f14112u, d);
    }

    public final void K(double d, int i10) {
        h0(i10, "image_lighteye", this.f14108o, d);
    }

    public final void L(double d, int i10) {
        h0(i10, "image_sharpenbrown", this.A, d);
    }

    public final void M(double d, int i10) {
        h0(i10, "image_sharpenbrown_type", this.B, d);
    }

    public final void N(double d, int i10) {
        h0(i10, "image_eye_enlarging", this.G, d);
    }

    public final void O(double d, int i10) {
        h0(i10, "image_intensity_eye_rotate", this.M, d);
    }

    public final void P(double d, int i10) {
        h0(i10, "image_intensity_eye_space", this.L, d);
    }

    public final void Q(double d, int i10) {
        h0(i10, "image_intensity_forehead", this.I, d);
    }

    public final void R(double d, int i10) {
        h0(i10, "image_intensity_hairtop", this.U, d);
    }

    public final void S(double d, int i10) {
        h0(i10, "image_intensity_long_nose", this.N, d);
    }

    public final void T(double d, int i10) {
        h0(i10, "image_intensity_lowerjaw", this.S, d);
    }

    public final void U(String value) {
        kotlin.jvm.internal.e.f(value, "value");
        this.X = value;
        if (this.f14097h) {
            f("image_manual_acen_data", value);
        }
    }

    public final void V(String value) {
        kotlin.jvm.internal.e.f(value, "value");
        this.Y = value;
        if (this.f14097h) {
            f("image_manual_blur_data", value);
        }
    }

    public final void W(String value) {
        kotlin.jvm.internal.e.f(value, "value");
        this.V = value;
        if (this.f14097h) {
            e(value, "image_manual_order");
        }
    }

    public final void X(String value) {
        kotlin.jvm.internal.e.f(value, "value");
        this.Z = value;
        if (this.f14097h) {
            f("image_manual_wrikle_data", value);
        }
    }

    public final void Y(double d, int i10) {
        h0(i10, "image_intensity_mouth", this.K, d);
    }

    public final void Z(double d, int i10) {
        h0(i10, "image_intensity_nose", this.J, d);
    }

    public final void a0(double d, int i10) {
        h0(i10, "image_intensity_nosetip", this.T, d);
    }

    @Override // com.faceunity.core.model.BaseSingleModel
    public final FaceBeautyController b() {
        return (FaceBeautyController) FURenderBridge.f1380o.a().f1382b.getValue();
    }

    public final void b0(double d, int i10) {
        h0(i10, "image_red", this.f14113w, d);
    }

    public final void c0(double d, int i10) {
        h0(i10, "image_red_type", this.f14114x, d);
    }

    public final void d0(double d, int i10) {
        h0(i10, "image_darkcircle", this.s, d);
    }

    public final void e0(double d, int i10) {
        h0(i10, "image_jili", this.f14115y, d);
    }

    public final void f0(double d, int i10) {
        h0(i10, "image_intensity_temple", this.Q, d);
    }

    public final void g(double d, double d10, double d11, double d12) {
        C0264a c0264a = new C0264a();
        c0264a.c(d);
        c0264a.e(d10);
        c0264a.b(d11);
        c0264a.d(d12);
        this.f14107n0.add(c0264a);
    }

    public final void g0(double d, int i10) {
        h0(i10, "image_threed", this.p, d);
    }

    public final ImageBeautyData h() {
        ImageBeautyData imageBeautyData = new ImageBeautyData();
        if (!kotlin.jvm.internal.e.a(imageBeautyData.m, this.j)) {
            String str = this.j;
            kotlin.jvm.internal.e.f(str, "<set-?>");
            imageBeautyData.m = str;
        }
        double d = imageBeautyData.f1294n;
        double d10 = this.f14102k;
        if (!(d == d10)) {
            imageBeautyData.f1294n = d10;
        }
        double[] dArr = this.l;
        double[] dArrCopyOf = Arrays.copyOf(dArr, dArr.length);
        kotlin.jvm.internal.e.e(dArrCopyOf, "copyOf(this, size)");
        imageBeautyData.r = dArrCopyOf;
        double[] dArr2 = this.m;
        double[] dArrCopyOf2 = Arrays.copyOf(dArr2, dArr2.length);
        kotlin.jvm.internal.e.e(dArrCopyOf2, "copyOf(this, size)");
        imageBeautyData.s = dArrCopyOf2;
        double[] dArr3 = this.f14106n;
        double[] dArrCopyOf3 = Arrays.copyOf(dArr3, dArr3.length);
        kotlin.jvm.internal.e.e(dArrCopyOf3, "copyOf(this, size)");
        imageBeautyData.v = dArrCopyOf3;
        double[] dArr4 = this.f14108o;
        double[] dArrCopyOf4 = Arrays.copyOf(dArr4, dArr4.length);
        kotlin.jvm.internal.e.e(dArrCopyOf4, "copyOf(this, size)");
        imageBeautyData.f1304w = dArrCopyOf4;
        double[] dArr5 = this.p;
        double[] dArrCopyOf5 = Arrays.copyOf(dArr5, dArr5.length);
        kotlin.jvm.internal.e.e(dArrCopyOf5, "copyOf(this, size)");
        imageBeautyData.f1306x = dArrCopyOf5;
        double[] dArr6 = this.s;
        double[] dArrCopyOf6 = Arrays.copyOf(dArr6, dArr6.length);
        kotlin.jvm.internal.e.e(dArrCopyOf6, "copyOf(this, size)");
        imageBeautyData.f1308y = dArrCopyOf6;
        double[] dArr7 = this.f14111t;
        double[] dArrCopyOf7 = Arrays.copyOf(dArr7, dArr7.length);
        kotlin.jvm.internal.e.e(dArrCopyOf7, "copyOf(this, size)");
        imageBeautyData.z = dArrCopyOf7;
        double[] dArr8 = this.f14112u;
        double[] dArrCopyOf8 = Arrays.copyOf(dArr8, dArr8.length);
        kotlin.jvm.internal.e.e(dArrCopyOf8, "copyOf(this, size)");
        imageBeautyData.A = dArrCopyOf8;
        double[] dArr9 = this.v;
        double[] dArrCopyOf9 = Arrays.copyOf(dArr9, dArr9.length);
        kotlin.jvm.internal.e.e(dArrCopyOf9, "copyOf(this, size)");
        imageBeautyData.D = dArrCopyOf9;
        double[] dArr10 = this.f14113w;
        double[] dArrCopyOf10 = Arrays.copyOf(dArr10, dArr10.length);
        kotlin.jvm.internal.e.e(dArrCopyOf10, "copyOf(this, size)");
        imageBeautyData.E = dArrCopyOf10;
        double[] dArr11 = this.f14114x;
        double[] dArrCopyOf11 = Arrays.copyOf(dArr11, dArr11.length);
        kotlin.jvm.internal.e.e(dArrCopyOf11, "copyOf(this, size)");
        imageBeautyData.F = dArrCopyOf11;
        double[] dArr12 = this.f14115y;
        double[] dArrCopyOf12 = Arrays.copyOf(dArr12, dArr12.length);
        kotlin.jvm.internal.e.e(dArrCopyOf12, "copyOf(this, size)");
        imageBeautyData.G = dArrCopyOf12;
        double[] dArr13 = this.z;
        double[] dArrCopyOf13 = Arrays.copyOf(dArr13, dArr13.length);
        kotlin.jvm.internal.e.e(dArrCopyOf13, "copyOf(this, size)");
        imageBeautyData.H = dArrCopyOf13;
        double[] dArr14 = this.A;
        double[] dArrCopyOf14 = Arrays.copyOf(dArr14, dArr14.length);
        kotlin.jvm.internal.e.e(dArrCopyOf14, "copyOf(this, size)");
        imageBeautyData.I = dArrCopyOf14;
        double[] dArr15 = this.B;
        double[] dArrCopyOf15 = Arrays.copyOf(dArr15, dArr15.length);
        kotlin.jvm.internal.e.e(dArrCopyOf15, "copyOf(this, size)");
        imageBeautyData.J = dArrCopyOf15;
        if (n9.I == 1) {
            double[] dArr16 = this.r;
            double[] dArrCopyOf16 = Arrays.copyOf(dArr16, dArr16.length);
            kotlin.jvm.internal.e.e(dArrCopyOf16, "copyOf(this, size)");
            imageBeautyData.K = dArrCopyOf16;
            double[] dArr17 = this.f14110q;
            double[] dArrCopyOf17 = Arrays.copyOf(dArr17, dArr17.length);
            kotlin.jvm.internal.e.e(dArrCopyOf17, "copyOf(this, size)");
            imageBeautyData.L = dArrCopyOf17;
        }
        double[] dArr18 = this.C;
        double[] dArrCopyOf18 = Arrays.copyOf(dArr18, dArr18.length);
        kotlin.jvm.internal.e.e(dArrCopyOf18, "copyOf(this, size)");
        imageBeautyData.M = dArrCopyOf18;
        double[] dArr19 = this.F;
        double[] dArrCopyOf19 = Arrays.copyOf(dArr19, dArr19.length);
        kotlin.jvm.internal.e.e(dArrCopyOf19, "copyOf(this, size)");
        imageBeautyData.P = dArrCopyOf19;
        double[] dArr20 = this.D;
        double[] dArrCopyOf20 = Arrays.copyOf(dArr20, dArr20.length);
        kotlin.jvm.internal.e.e(dArrCopyOf20, "copyOf(this, size)");
        imageBeautyData.N = dArrCopyOf20;
        double[] dArr21 = this.E;
        double[] dArrCopyOf21 = Arrays.copyOf(dArr21, dArr21.length);
        kotlin.jvm.internal.e.e(dArrCopyOf21, "copyOf(this, size)");
        imageBeautyData.O = dArrCopyOf21;
        double[] dArr22 = this.H;
        double[] dArrCopyOf22 = Arrays.copyOf(dArr22, dArr22.length);
        kotlin.jvm.internal.e.e(dArrCopyOf22, "copyOf(this, size)");
        imageBeautyData.R = dArrCopyOf22;
        double[] dArr23 = this.I;
        double[] dArrCopyOf23 = Arrays.copyOf(dArr23, dArr23.length);
        kotlin.jvm.internal.e.e(dArrCopyOf23, "copyOf(this, size)");
        imageBeautyData.S = dArrCopyOf23;
        double[] dArr24 = this.Q;
        double[] dArrCopyOf24 = Arrays.copyOf(dArr24, dArr24.length);
        kotlin.jvm.internal.e.e(dArrCopyOf24, "copyOf(this, size)");
        imageBeautyData.f1280a0 = dArrCopyOf24;
        double[] dArr25 = this.R;
        double[] dArrCopyOf25 = Arrays.copyOf(dArr25, dArr25.length);
        kotlin.jvm.internal.e.e(dArrCopyOf25, "copyOf(this, size)");
        imageBeautyData.f1281b0 = dArrCopyOf25;
        double[] dArr26 = this.S;
        double[] dArrCopyOf26 = Arrays.copyOf(dArr26, dArr26.length);
        kotlin.jvm.internal.e.e(dArrCopyOf26, "copyOf(this, size)");
        imageBeautyData.f1282c0 = dArrCopyOf26;
        double[] dArr27 = this.U;
        double[] dArrCopyOf27 = Arrays.copyOf(dArr27, dArr27.length);
        kotlin.jvm.internal.e.e(dArrCopyOf27, "copyOf(this, size)");
        imageBeautyData.f1285e0 = dArrCopyOf27;
        double[] dArr28 = this.G;
        double[] dArrCopyOf28 = Arrays.copyOf(dArr28, dArr28.length);
        kotlin.jvm.internal.e.e(dArrCopyOf28, "copyOf(this, size)");
        imageBeautyData.Q = dArrCopyOf28;
        double[] dArr29 = this.L;
        double[] dArrCopyOf29 = Arrays.copyOf(dArr29, dArr29.length);
        kotlin.jvm.internal.e.e(dArrCopyOf29, "copyOf(this, size)");
        imageBeautyData.V = dArrCopyOf29;
        double[] dArr30 = this.M;
        double[] dArrCopyOf30 = Arrays.copyOf(dArr30, dArr30.length);
        kotlin.jvm.internal.e.e(dArrCopyOf30, "copyOf(this, size)");
        imageBeautyData.W = dArrCopyOf30;
        double[] dArr31 = this.P;
        double[] dArrCopyOf31 = Arrays.copyOf(dArr31, dArr31.length);
        kotlin.jvm.internal.e.e(dArrCopyOf31, "copyOf(this, size)");
        imageBeautyData.Z = dArrCopyOf31;
        double[] dArr32 = this.J;
        double[] dArrCopyOf32 = Arrays.copyOf(dArr32, dArr32.length);
        kotlin.jvm.internal.e.e(dArrCopyOf32, "copyOf(this, size)");
        imageBeautyData.T = dArrCopyOf32;
        double[] dArr33 = this.N;
        double[] dArrCopyOf33 = Arrays.copyOf(dArr33, dArr33.length);
        kotlin.jvm.internal.e.e(dArrCopyOf33, "copyOf(this, size)");
        imageBeautyData.X = dArrCopyOf33;
        double[] dArr34 = this.T;
        double[] dArrCopyOf34 = Arrays.copyOf(dArr34, dArr34.length);
        kotlin.jvm.internal.e.e(dArrCopyOf34, "copyOf(this, size)");
        imageBeautyData.f1283d0 = dArrCopyOf34;
        double[] dArr35 = this.K;
        double[] dArrCopyOf35 = Arrays.copyOf(dArr35, dArr35.length);
        kotlin.jvm.internal.e.e(dArrCopyOf35, "copyOf(this, size)");
        imageBeautyData.U = dArrCopyOf35;
        double[] dArr36 = this.O;
        double[] dArrCopyOf36 = Arrays.copyOf(dArr36, dArr36.length);
        kotlin.jvm.internal.e.e(dArrCopyOf36, "copyOf(this, size)");
        imageBeautyData.Y = dArrCopyOf36;
        imageBeautyData.f1290j0 = this.f14094e0;
        imageBeautyData.f1288h0 = this.f14092c0;
        imageBeautyData.f1286f0 = this.f14090a0;
        imageBeautyData.f1287g0 = this.f14091b0;
        imageBeautyData.f1289i0 = this.f14093d0;
        imageBeautyData.f1291k0 = this.f14095f0;
        imageBeautyData.f1292l0 = this.f14096g0;
        imageBeautyData.f1293m0 = this.f14098h0;
        imageBeautyData.f1295n0 = this.f14100i0;
        LinkedList<C0264a> linkedList = imageBeautyData.f1296o0;
        linkedList.clear();
        Iterator<T> it = this.f14107n0.iterator();
        while (it.hasNext()) {
            linkedList.add(((C0264a) it.next()).a());
        }
        imageBeautyData.f1301t0 = this.W;
        String str2 = this.X;
        kotlin.jvm.internal.e.f(str2, "<set-?>");
        imageBeautyData.f1298q0 = str2;
        String str3 = this.Y;
        kotlin.jvm.internal.e.f(str3, "<set-?>");
        imageBeautyData.f1299r0 = str3;
        String str4 = this.Z;
        kotlin.jvm.internal.e.f(str4, "<set-?>");
        imageBeautyData.f1300s0 = str4;
        String str5 = this.V;
        kotlin.jvm.internal.e.f(str5, "<set-?>");
        imageBeautyData.f1297p0 = str5;
        String str6 = this.f14109o0;
        kotlin.jvm.internal.e.f(str6, "<set-?>");
        imageBeautyData.f1284e = str6;
        imageBeautyData.f1302u0 = this.f14103k0;
        imageBeautyData.f1303v0 = this.f14104l0;
        imageBeautyData.f1305w0 = this.f14105m0;
        imageBeautyData.f1307x0 = n9.J;
        imageBeautyData.f1309y0 = n9.K;
        return imageBeautyData;
    }

    public final void h0(int i10, String str, double[] doubleArray, double d) {
        kotlin.jvm.internal.e.f(doubleArray, "doubleArray");
        if (i10 >= 1 && i10 <= 5) {
            doubleArray[i10 - 1] = d;
            if (this.f14097h) {
                e(Double.valueOf(d), i(i10, str));
                return;
            }
            return;
        }
        Log.e(this.g, "输入的人脸参数不合法: " + i10 + " name" + str);
    }

    public final void i0(String str, double[] doubleArray, double d) {
        kotlin.jvm.internal.e.f(doubleArray, "doubleArray");
        int i10 = this.f14099i;
        for (int i11 = 0; i11 < i10; i11++) {
            doubleArray[i11] = d;
            if (this.f14097h) {
                e(Double.valueOf(d), i(i11 + 1, str));
            }
        }
    }

    public final double j() {
        LinkedList<C0264a> linkedList = this.f14107n0;
        if (linkedList.size() < 1) {
            return 0.5d;
        }
        return linkedList.getLast().f14116a;
    }

    public final void j0() {
        if (this.f14097h) {
            e(Double.valueOf(1.0d), "redo_manual_grid");
        }
    }

    public final double k(int i10, String str) {
        if (i10 <= 0 || i10 > 5) {
            Log.e(this.g, b.f("输入的人脸参数不合法: ", i10));
            return 0.0d;
        }
        Serializable serializableA = a(i(i10, str));
        kotlin.jvm.internal.e.d(serializableA, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) serializableA).doubleValue();
    }

    public final void k0() {
        Object objE;
        FaceBeautyController faceBeautyControllerB = b();
        long j = this.f1332b;
        faceBeautyControllerB.getClass();
        if (j == faceBeautyControllerB.f1274b) {
            int i10 = faceBeautyControllerB.f1275c;
            if (i10 <= 0) {
                FULogger.b(faceBeautyControllerB.f1273a, androidx.view.a.j(new StringBuilder("setItemParam failed handle:"), faceBeautyControllerB.f1275c, "  "));
                objE = "";
            } else {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                objE = SDKController.e(i10, "image_manual_order".getClass(), "image_manual_order");
            }
        } else {
            objE = "";
        }
        if (objE == null || !(objE instanceof String) || !(!i.l0((CharSequence) objE)) || kotlin.jvm.internal.e.a(objE, "no data")) {
            return;
        }
        this.f14097h = false;
        W((String) objE);
        this.f14097h = true;
    }

    public final double l(int i10, double[] doubleArray) {
        kotlin.jvm.internal.e.f(doubleArray, "doubleArray");
        if (i10 >= 0 && i10 <= 5) {
            return doubleArray[i10 - 1];
        }
        Log.e(this.g, "输入的人脸参数不合法: " + i10);
        return 0.0d;
    }

    public final void m() {
        LinkedList<C0264a> linkedList = this.f14107n0;
        C0264a last = linkedList.size() < 1 ? null : linkedList.getLast();
        if (last != null) {
            if (!(last.f14116a == 0.5d) || linkedList.size() <= 0) {
                return;
            }
            linkedList.removeLast();
        }
    }

    public final void n() {
        if (this.f14097h) {
            e(Double.valueOf(1.0d), "clear_grid");
        }
        this.f14107n0.clear();
        g(0.0d, 0.0d, 0.0d, 0.5d);
        m();
        j0();
    }

    public final void o(double d) {
        this.f14091b0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_leg_stretch");
        }
    }

    public final void p(double d) {
        this.f14100i0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_arm_thin");
        }
    }

    public final void q(double d) {
        this.f14096g0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_head_slim");
        }
    }

    public final void r(double d) {
        this.f14095f0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_hip_stretch");
        }
    }

    public final void s(double d) {
        this.f14098h0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_leg_thin");
        }
    }

    public final void t(double d) {
        this.f14093d0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_shoulder_stretch");
        }
    }

    public final void u(double d) {
        this.f14094e0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_swan_neck");
        }
    }

    public final void v(double d) {
        this.f14092c0 = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "body_intensity_waist_slim");
        }
    }

    public final void w(int i10) {
        if (i10 >= 5) {
            i10 = 5;
        }
        this.f14099i = i10;
    }

    public final void x(double d) {
        this.f14102k = d;
        if (this.f14097h) {
            e(Double.valueOf(d), "filter_level");
        }
    }

    public final void y(String value) {
        kotlin.jvm.internal.e.f(value, "value");
        this.j = value;
        if (this.f14097h) {
            e(value, "filter_name");
        }
    }

    public final void z(double d, int i10) {
        h0(i10, "image_acencancel", this.f14111t, d);
    }
}
