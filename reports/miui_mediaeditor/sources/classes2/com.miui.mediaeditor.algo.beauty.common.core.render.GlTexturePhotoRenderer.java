package com.miui.mediaeditor.algo.beauty.common.core.render;

import android.graphics.Bitmap;
import android.opengl.Matrix;
import android.util.Log;
import com.faceunity.core.cache.CacheData;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.ProcessMode;
import com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer;
import com.faceunity.core.renderer.gltexturerender.d;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.c;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.e;
import fr.l;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public final class GlTexturePhotoRenderer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f7379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Bitmap f7380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f7381c;
    public final d d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7382e;

    public GlTexturePhotoRenderer(c gLSurfaceView, Bitmap bitmap, Bitmap bitmap2, ProcessMode processMode, e eVar) {
        this.f7379a = gLSurfaceView;
        this.f7380b = bitmap;
        this.f7381c = eVar;
        a aVar = new a(this);
        String absolutePath = kotlinx.coroutines.internal.a.D("sdkImageCache").getAbsolutePath();
        kotlin.jvm.internal.e.f(gLSurfaceView, "gLSurfaceView");
        FUInputBufferEnum fUInputBufferEnum = FUInputBufferEnum.FU_FORMAT_BITMAP;
        kotlin.jvm.internal.e.f(bitmap, "bitmap");
        kotlin.jvm.internal.e.c(absolutePath);
        kotlin.jvm.internal.e.c(processMode);
        Bitmap bitmap3 = bitmap2 == null ? null : bitmap2;
        if (absolutePath.length() == 0) {
            Log.e("KIT_BaseFURenderer", "请传入sdk缓存路径");
        }
        this.d = new d(gLSurfaceView, bitmap, bitmap3, absolutePath, processMode, fUInputBufferEnum, false, false, aVar);
    }

    public final int a() {
        d dVar = this.d;
        if (dVar != null) {
            return dVar.f1364q;
        }
        kotlin.jvm.internal.e.l("realRenderer");
        throw null;
    }

    public final int b() {
        d dVar = this.d;
        if (dVar != null) {
            return dVar.f1363o;
        }
        kotlin.jvm.internal.e.l("realRenderer");
        throw null;
    }

    public final void c() {
        d dVar = this.d;
        if (dVar == null) {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
        synchronized (dVar) {
            dVar.i(true);
        }
    }

    public final void d() {
        d dVar = this.d;
        if (dVar != null) {
            dVar.i(false);
        } else {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
    }

    public final void e(final l<? super CacheData, yq.d> lVar) {
        d dVar = this.d;
        if (dVar == null) {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
        ((ExecutorService) dVar.f1376d0.getValue()).execute(new com.faceunity.core.renderer.gltexturerender.c(dVar, true, (l) new l<CacheData, yq.d>() { // from class: com.miui.mediaeditor.algo.beauty.common.core.render.GlTexturePhotoRenderer$saveData$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(CacheData cacheData) {
                CacheData it = cacheData;
                kotlin.jvm.internal.e.f(it, "it");
                lVar.invoke(it);
                return yq.d.f18754a;
            }
        }));
    }

    public final void f(boolean z) {
        d dVar = this.d;
        if (dVar != null) {
            dVar.N = z;
        } else {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
    }

    public final void g(int i10, int i11, int i12, int i13) {
        d dVar = this.d;
        if (dVar == null) {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
        dVar.O = i10;
        dVar.P = i11;
        float[] fArrA = p2.a.a(i10, i11, dVar.r, dVar.s);
        kotlin.jvm.internal.e.e(fArrA, "changeMvpMatrixCrop(\n   …eight.toFloat()\n        )");
        dVar.E = fArrA;
        Matrix.invertM(dVar.F, 0, fArrA, 0);
        dVar.L = i12;
        dVar.M = i13;
    }

    public final void h(float f, float f10, float f11) {
        d dVar = this.d;
        if (dVar == null) {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
        float[] fArrCopyOf = Arrays.copyOf(BaseGLTextureRenderer.U, 16);
        kotlin.jvm.internal.e.e(fArrCopyOf, "copyOf(this, size)");
        float f12 = 1 / f;
        float[] fArr = p2.a.f16123a;
        Matrix.translateM(fArrCopyOf, 0, (f10 * 2.0f) - 1.0f, (f11 * 2.0f) - 1.0f, 0.0f);
        Matrix.scaleM(fArrCopyOf, 0, f12, f12, 1.0f);
        dVar.D = fArrCopyOf;
    }
}
