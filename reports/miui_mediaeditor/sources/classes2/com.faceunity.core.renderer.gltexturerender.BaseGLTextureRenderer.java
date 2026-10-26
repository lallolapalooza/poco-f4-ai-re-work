package com.faceunity.core.renderer.gltexturerender;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.Log;
import com.faceunity.core.enumeration.ProcessMode;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseGLTextureRenderer implements b2.a.k {
    public static final float[] T = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    public static final float[] U = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    public final float[] A;
    public float[] B;
    public float[] C;
    public float[] D;
    public float[] E;
    public final float[] F;
    public volatile boolean G;
    public int H;
    public volatile boolean I;
    public volatile boolean J;
    public int K;
    public int L;
    public int M;
    public boolean N;
    public int O;
    public int P;
    public int Q;
    public final Object R;
    public Runnable S;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b2.a f1355a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public l2.a f1356b;
    public final boolean d;
    public final String j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final yq.c f1361k;
    public n2.c l;
    public n2.b m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1362n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1363o;
    public int p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1364q;
    public int r;
    public int s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f1365t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f1366u;
    public volatile j2.c v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1367w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1368x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float[] f1369y;
    public float[] z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1357c = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1358e = 0;
    public int f = 0;
    public int g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1359h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f1360i = false;

    public BaseGLTextureRenderer(b2.a aVar, l2.a aVar2, boolean z) {
        b2.a aVar3;
        this.f1355a = aVar;
        this.f1356b = aVar2;
        this.d = z;
        a aVar4 = new a(this);
        if (aVar != null) {
            aVar.setEGLContextClientVersion(2);
        }
        if (z && (aVar3 = this.f1355a) != null) {
            aVar3.setEGLContextFactory(aVar4);
        }
        b2.a aVar5 = this.f1355a;
        if (aVar5 != null) {
            aVar5.setRenderer(this);
        }
        b2.a aVar6 = this.f1355a;
        if (aVar6 != null) {
            aVar6.setRenderMode(0);
        }
        this.j = "BaseGLTextureRenderer";
        this.f1361k = kotlin.a.a(new fr.a<FURenderKit>() { // from class: com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer$mFURenderKit$2
            @Override // fr.a
            public final FURenderKit invoke() {
                return FURenderKit.d.a();
            }
        });
        this.f1362n = 1;
        this.f1363o = 1;
        this.v = new j2.c(0, 0);
        float[] fArr = T;
        float[] fArrCopyOf = Arrays.copyOf(fArr, 16);
        e.e(fArrCopyOf, "copyOf(this, size)");
        this.f1369y = fArrCopyOf;
        e.e(Arrays.copyOf(fArr, 16), "copyOf(this, size)");
        float[] fArrCopyOf2 = Arrays.copyOf(fArr, 16);
        e.e(fArrCopyOf2, "copyOf(this, size)");
        this.z = fArrCopyOf2;
        float[] fArrCopyOf3 = Arrays.copyOf(fArr, 16);
        e.e(fArrCopyOf3, "copyOf(this, size)");
        this.A = fArrCopyOf3;
        float[] fArrCopyOf4 = Arrays.copyOf(fArr, 16);
        e.e(fArrCopyOf4, "copyOf(this, size)");
        this.B = fArrCopyOf4;
        float[] fArrCopyOf5 = Arrays.copyOf(fArr, 16);
        e.e(fArrCopyOf5, "copyOf(this, size)");
        this.C = fArrCopyOf5;
        float[] fArrCopyOf6 = Arrays.copyOf(U, 16);
        e.e(fArrCopyOf6, "copyOf(this, size)");
        this.D = fArrCopyOf6;
        float[] fArrCopyOf7 = Arrays.copyOf(fArr, 16);
        e.e(fArrCopyOf7, "copyOf(this, size)");
        this.E = fArrCopyOf7;
        float[] fArrCopyOf8 = Arrays.copyOf(fArr, 16);
        e.e(fArrCopyOf8, "copyOf(this, size)");
        this.F = fArrCopyOf8;
        this.G = true;
        this.J = true;
        k2.b bVar = k2.b.f11921a;
        float f = 90;
        this.O = (int) ((bVar.a().getResources().getDisplayMetrics().density * f) + 0.5f);
        this.P = (int) ((f * bVar.a().getResources().getDisplayMetrics().density) + 0.5f);
        this.Q = 1;
        this.R = new Object();
    }

    @Override // b2.a.k
    public final void a() {
        m2.a aVar;
        float[] fArr = p2.a.f16123a;
        Log.i("KIT_GlUtil", "vendor  : " + GLES20.glGetString(7936));
        Log.i("KIT_GlUtil", "com.faceunity.core.renderer: " + GLES20.glGetString(7937));
        Log.i("KIT_GlUtil", "version : " + GLES20.glGetString(7938));
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(33307, iArr, 0);
        int i10 = iArr[0];
        GLES20.glGetIntegerv(33308, iArr, 0);
        int i11 = iArr[0];
        if (GLES20.glGetError() == 0) {
            Log.i("KIT_GlUtil", "glVersion: " + i10 + "." + i11);
        }
        l2.a aVar2 = this.f1356b;
        if (aVar2 != null) {
            aVar2.a();
        }
        this.l = new n2.c();
        this.m = new n2.b();
        this.H = 0;
        d dVar = (d) this;
        Bitmap bitmap = dVar.X;
        if (bitmap != null) {
            int[] iArr2 = new int[1];
            GLES20.glGenTextures(1, iArr2, 0);
            int i12 = iArr2[0];
            p2.a.b("glGenTextures");
            GLES20.glBindTexture(3553, i12);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            p2.a.b("loadImageTexture");
            GLUtils.texImage2D(3553, 0, bitmap, 0);
            p2.a.b("loadImageTexture");
            dVar.K = i12;
        }
        if (dVar.f1375c0) {
            dVar.c().e(dVar.Y);
            dVar.k(dVar.v);
            j2.c cVar = dVar.v;
            cVar.d = true;
            dVar.c().g().getClass();
            FURenderBridge.h(cVar);
            if (dVar.f1374b0 && (aVar = dVar.c().f1330c) != null && aVar.f14097h) {
                aVar.e(Double.valueOf(1.0d), "image_do_perpare_wrinkle");
            }
            dVar.f1367w = dVar.c().d(cVar);
            l2.a aVar3 = dVar.f1356b;
            if (aVar3 != null) {
                aVar3.c(dVar.I);
            }
            dVar.f1375c0 = false;
            return;
        }
        dVar.J = true;
        dVar.c().g().getClass();
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        int[] items = f2.b.g.a().d();
        e.f(items, "items");
        Faceunity.fuImageBeautyLoadCache(items);
        String string = Arrays.toString(items);
        e.e(string, "toString(this)");
        FULogger.a("KIT_SDKController", "fuImageBeautyLoadCache items:".concat(string));
        j2.c cVar2 = new j2.c(0, 0);
        cVar2.f11717e = new j2.c.a(dVar.f1373a0, 30);
        if (!dVar.f1379g0.contains(dVar.Z)) {
            dVar.k(cVar2);
            dVar.f1367w = dVar.c().d(cVar2);
        }
        if (dVar.Z == ProcessMode.FU_IMAGE_BEAUTY_MODE_UNKNOWN) {
            j2.c cVar3 = dVar.v;
            cVar3.d = false;
            cVar3.f11716c = ProcessMode.FU_IMAGE_BEAUTY_MODE_UPLOAD;
            dVar.c().g().getClass();
            FURenderBridge.h(cVar3);
        }
    }

    public abstract void b();

    public final FURenderKit c() {
        return (FURenderKit) this.f1361k.getValue();
    }

    public final void d() throws InterruptedException {
        if (this.f1360i) {
            final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final j2.c cVar = new j2.c(this.v.f11714a, this.v.f11715b);
            j2.c.a aVar = this.v.f11717e;
            e.c(aVar);
            cVar.f11717e = new j2.c.a(aVar.f11718a, 16);
            final d dVar = (d) this;
            c().a(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer$onPauseCacheBitmap$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // fr.a
                public final yq.d invoke() {
                    FURenderKit fURenderKitC = dVar.c();
                    j2.c fuRenderInputData = cVar;
                    fURenderKitC.getClass();
                    e.f(fuRenderInputData, "fuRenderInputData");
                    fURenderKitC.g().getClass();
                    FURenderBridge.d(fuRenderInputData);
                    return yq.d.f18754a;
                }
            });
            c().b(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer$onPauseCacheBitmap$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // fr.a
                public final yq.d invoke() {
                    countDownLatch.countDown();
                    ref$BooleanRef.element = true;
                    return yq.d.f18754a;
                }
            });
            i(false);
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        }
        FURenderKit fURenderKitC = c();
        final d dVar2 = (d) this;
        fURenderKitC.c(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer$onPause$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // fr.a
            public final yq.d invoke() {
                b2.a aVar2;
                dVar2.I = true;
                BaseGLTextureRenderer baseGLTextureRenderer = dVar2;
                Runnable runnable = baseGLTextureRenderer.S;
                if (runnable != null && (aVar2 = baseGLTextureRenderer.f1355a) != null) {
                    b2.a.i iVar = aVar2.f359e;
                    iVar.getClass();
                    b2.a.j jVar = b2.a.A;
                    synchronized (jVar) {
                        iVar.G.remove(runnable);
                        jVar.notifyAll();
                    }
                }
                final BaseGLTextureRenderer baseGLTextureRenderer2 = dVar2;
                Runnable runnable2 = new Runnable() { // from class: com.faceunity.core.renderer.gltexturerender.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseGLTextureRenderer this$0 = baseGLTextureRenderer2;
                        e.f(this$0, "this$0");
                        Log.d(this$0.j, "onPause: flagSure: false");
                        FURenderBridge fURenderBridgeG = this$0.c().g();
                        synchronized (fURenderBridgeG.f1381a) {
                            fURenderBridgeG.f1383c.clear();
                            fURenderBridgeG.d.clear();
                            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                            int[] items = f2.b.g.a().d();
                            e.f(items, "items");
                            Faceunity.fuImageBeautySaveCache(items);
                            String string = Arrays.toString(items);
                            e.e(string, "toString(this)");
                            FULogger.a("KIT_SDKController", "fuImageBeautySaveCache items:".concat(string));
                            Faceunity.fuReleaseGLResources();
                            FULogger.a("KIT_SDKController", "releaseGLResources");
                            yq.d dVar3 = yq.d.f18754a;
                        }
                        int i10 = this$0.f1367w;
                        if (i10 != 0) {
                            int[] iArr = {i10};
                            float[] fArr = p2.a.f16123a;
                            GLES20.glDeleteTextures(1, iArr, 0);
                            this$0.f1367w = 0;
                        }
                        int i11 = this$0.f1368x;
                        if (i11 != 0) {
                            int[] iArr2 = {i11};
                            float[] fArr2 = p2.a.f16123a;
                            GLES20.glDeleteTextures(1, iArr2, 0);
                            this$0.f1368x = 0;
                        }
                        int i12 = this$0.K;
                        if (i12 != 0) {
                            int[] iArr3 = {i12};
                            float[] fArr3 = p2.a.f16123a;
                            GLES20.glDeleteTextures(1, iArr3, 0);
                            this$0.K = 0;
                        }
                        n2.c cVar2 = this$0.l;
                        if (cVar2 != null) {
                            GLES20.glDeleteProgram(cVar2.f15599a);
                            cVar2.f15599a = -1;
                            this$0.l = null;
                        }
                        n2.b bVar = this$0.m;
                        if (bVar != null) {
                            GLES20.glDeleteProgram(bVar.f15599a);
                            bVar.f15599a = -1;
                            this$0.m = null;
                        }
                        Log.d(this$0.j, "Runnable releaseTexture");
                        this$0.S = null;
                    }
                };
                baseGLTextureRenderer2.S = runnable2;
                b2.a aVar3 = baseGLTextureRenderer2.f1355a;
                if (aVar3 != null) {
                    aVar3.b(runnable2);
                }
                b2.a aVar4 = dVar2.f1355a;
                if (aVar4 != null) {
                    b2.a.i iVar2 = aVar4.f359e;
                    iVar2.getClass();
                    b2.a.j jVar2 = b2.a.A;
                    synchronized (jVar2) {
                        iVar2.m = true;
                        jVar2.notifyAll();
                        while (!iVar2.f375e && !iVar2.f376n) {
                            try {
                                b2.a.A.wait();
                            } catch (InterruptedException unused) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
                return yq.d.f18754a;
            }
        });
    }

    @Override // b2.a.k
    public final void e(int i10, int i11) {
        if (this.f1362n != i10 || this.f1363o != i11) {
            this.f1362n = i10;
            this.f1363o = i11;
            d dVar = (d) this;
            float[] fArrA = p2.a.a(dVar.O, dVar.P, dVar.r, dVar.s);
            e.e(fArrA, "changeMvpMatrixCrop(\n   …eight.toFloat()\n        )");
            dVar.E = fArrA;
            Matrix.invertM(dVar.F, 0, fArrA, 0);
            float f = ((i10 * dVar.s) / i11) / dVar.r;
            float[] fArrCopyOf = Arrays.copyOf(p2.a.f16123a, 16);
            float f10 = f > 1.0f ? 1.0f / f : 1.0f;
            if (f > 1.0f) {
                f = 1.0f;
            }
            Matrix.scaleM(fArrCopyOf, 0, f10, f, 1.0f);
            e.e(fArrCopyOf, "changeMvpMatrixInside(\n …eight.toFloat()\n        )");
            float[] fArrCopyOf2 = Arrays.copyOf(fArrCopyOf, fArrCopyOf.length);
            e.e(fArrCopyOf2, "copyOf(this, size)");
            dVar.B = fArrCopyOf2;
            Matrix.scaleM(fArrCopyOf2, 0, 1.0f, -1.0f, 1.0f);
            float[] fArr = dVar.B;
            float[] fArrCopyOf3 = Arrays.copyOf(fArr, fArr.length);
            e.e(fArrCopyOf3, "copyOf(this, size)");
            dVar.z = fArrCopyOf3;
            int i12 = this.f1358e;
            if (i12 > 0 || this.f > 0 || this.g > 0 || this.f1359h > 0) {
                j((this.f1362n - this.g) - this.f1359h, (this.f1363o - i12) - this.f);
            } else {
                j(this.f1362n, this.f1363o);
            }
            this.M = this.f1363o - this.P;
        }
        l2.a aVar = this.f1356b;
        if (aVar != null) {
            aVar.e(i10, i11);
        }
    }

    @Override // b2.a.k
    public final void f() {
        Log.d(this.j, "onDrawFrame");
        if (this.I) {
            return;
        }
        if (((d) this).l != null) {
            Log.d(this.j, "renderLock: ");
            synchronized (this.R) {
                Log.d(this.j, "onDraw: ");
                FURenderBridge fURenderBridgeG = c().g();
                while (true) {
                    List<fr.a<yq.d>> list = fURenderBridgeG.f1383c;
                    if (list == null || list.isEmpty()) {
                        break;
                    } else {
                        list.remove(0).invoke();
                    }
                }
                Log.d(this.j, "prepareDrawFrame: ");
                if (this.J) {
                    Log.d(this.j, "previewRender: Time2: 发起渲染请求");
                    if (h()) {
                        return;
                    }
                }
                FURenderBridge fURenderBridgeG2 = c().g();
                while (true) {
                    List<fr.a<yq.d>> list2 = fURenderBridgeG2.d;
                    if (list2 == null || list2.isEmpty()) {
                        break;
                    } else {
                        list2.remove(0).invoke();
                    }
                }
                Log.d(this.j, "prepareAfterDrawFrame: ");
                GLES20.glClear(16640);
                b();
                Log.d(this.j, "drawRenderFrame: ");
                l2.a aVar = this.f1356b;
                if (aVar != null) {
                    aVar.d();
                }
                Log.d(this.j, "onDrawFrameAfter: ");
            }
        }
    }

    public final void g() {
        FURenderKit fURenderKitC = c();
        final d dVar = (d) this;
        fURenderKitC.c(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer$onResume$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // fr.a
            public final yq.d invoke() {
                b2.a aVar;
                if (dVar.I && (aVar = dVar.f1355a) != null) {
                    b2.a.i iVar = aVar.f359e;
                    iVar.getClass();
                    b2.a.j jVar = b2.a.A;
                    synchronized (jVar) {
                        iVar.m = false;
                        iVar.E = true;
                        iVar.F = false;
                        jVar.notifyAll();
                        while (!iVar.f375e && iVar.f376n && !iVar.F) {
                            try {
                                b2.a.A.wait();
                            } catch (InterruptedException unused) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
                dVar.I = false;
                return yq.d.f18754a;
            }
        });
    }

    public final boolean h() {
        j2.c cVar = ((d) this).v;
        if (cVar.f11717e == null) {
            return true;
        }
        if (this.G) {
            int i10 = this.H;
            this.H = i10 + 1;
            if (i10 >= 0) {
                l2.a aVar = this.f1356b;
                if (aVar != null) {
                    aVar.g(cVar);
                }
                FURenderKit fURenderKitC = c();
                fURenderKitC.getClass();
                j2.d dVarD = fURenderKitC.f().a(0).d(cVar);
                l2.a aVar2 = this.f1356b;
                if (aVar2 != null) {
                    aVar2.f(dVarD);
                }
                j2.d.a aVar3 = dVarD.f11728a;
                this.f1368x = aVar3 != null ? aVar3.f11729a : 0;
                float[] fArrCopyOf = Arrays.copyOf(T, 16);
                e.e(fArrCopyOf, "copyOf(this, size)");
                this.f1369y = fArrCopyOf;
            }
        }
        return false;
    }

    public final synchronized void i(boolean z) {
        b2.a aVar;
        this.J = z;
        if (this.Q == 0 && (aVar = this.f1355a) != null) {
            b2.a.i iVar = aVar.f359e;
            iVar.getClass();
            b2.a.j jVar = b2.a.A;
            synchronized (jVar) {
                iVar.E = true;
                jVar.notifyAll();
            }
        }
    }

    public final void j(int i10, int i11) {
        float f;
        float f10;
        float f11;
        float f12 = i10;
        int i12 = this.s;
        float f13 = i11;
        int i13 = this.r;
        float f14 = ((i12 * f12) / f13) / i13;
        if (f14 > 1.0f) {
            f = f13 / i12;
        } else {
            int i14 = (f14 > 1.0f ? 1 : (f14 == 1.0f ? 0 : -1));
            f = f12 / i13;
        }
        this.p = (int) (i13 * f);
        this.f1364q = (int) (i12 * f);
        int i15 = this.f1362n;
        int i16 = this.f1363o;
        float f15 = ((i15 * i12) / i16) / i13;
        if (f15 > 1.0f) {
            f10 = i16;
            f11 = i12;
        } else {
            int i17 = (f15 > 1.0f ? 1 : (f15 == 1.0f ? 0 : -1));
            f10 = i15;
            f11 = i13;
        }
        float f16 = f10 / f11;
        float[] fArrCopyOf = Arrays.copyOf(T, 16);
        e.e(fArrCopyOf, "copyOf(this, size)");
        int i18 = this.f1362n;
        double d = (((double) ((this.g + i18) - this.f1359h)) / 2.0d) / ((double) i18);
        int i19 = this.f1363o;
        double d10 = 2;
        Matrix.translateM(fArrCopyOf, 0, (float) ((d - 0.5d) * d10), (float) ((((((double) ((this.f + i19) - this.f1358e)) / 2.0d) / ((double) i19)) - 0.5d) * d10), 0.0f);
        float f17 = f / f16;
        Matrix.scaleM(fArrCopyOf, 0, f17, f17, 1.0f);
        float[] fArr = this.B;
        Matrix.multiplyMM(fArr, 0, fArrCopyOf, 0, fArr, 0);
        float[] fArr2 = this.B;
        this.C = fArr2;
        this.z = fArr2;
        this.f1365t = (float) (((double) (((this.f1362n + this.g) - this.f1359h) - this.p)) / 2.0d);
        this.f1366u = (float) (((double) (((this.f1363o + this.f) - this.f1358e) - this.f1364q)) / 2.0d);
    }
}
