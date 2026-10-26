package com.miui.mediaeditor.algo.beauty.common.core.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.faceunity.core.cache.CacheData;
import com.faceunity.core.enumeration.ProcessMode;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import com.miui.mediaeditor.algo.beauty.common.core.render.GlTexturePhotoRenderer;
import fr.l;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001:\u0001<J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016J\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bR$\u0010\u0012\u001a\u0004\u0018\u00010\u000b8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u001a\u001a\u00020\u00138\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010!\u001a\u00020\b8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010%\u001a\u00020\b8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R$\u0010-\u001a\u0004\u0018\u00010&8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u00104\u001a\u00020.8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001d\u0010;\u001a\b\u0012\u0004\u0012\u000206058\u0006¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:¨\u0006="}, d2 = {"Lcom/miui/mediaeditor/algo/beauty/common/core/widgets/BeautyGlView;", "Lcom/miui/mediaeditor/algo/beauty/common/core/widgets/c;", "Landroid/view/ViewGroup$LayoutParams;", "params", "Lyq/d;", "setLayoutParams", "Landroid/graphics/Bitmap;", "getMagnifierBitmap", "", "getContentTop", "getContentBottom", "Lcom/miui/mediaeditor/algo/beauty/common/core/render/GlTexturePhotoRenderer;", "D", "Lcom/miui/mediaeditor/algo/beauty/common/core/render/GlTexturePhotoRenderer;", "getRenderer", "()Lcom/miui/mediaeditor/algo/beauty/common/core/render/GlTexturePhotoRenderer;", "setRenderer", "(Lcom/miui/mediaeditor/algo/beauty/common/core/render/GlTexturePhotoRenderer;)V", "renderer", "Lcom/miui/mediaeditor/algo/beauty/common/core/widgets/d;", "E", "Lcom/miui/mediaeditor/algo/beauty/common/core/widgets/d;", "getGestureTouchHandler", "()Lcom/miui/mediaeditor/algo/beauty/common/core/widgets/d;", "setGestureTouchHandler", "(Lcom/miui/mediaeditor/algo/beauty/common/core/widgets/d;)V", "gestureTouchHandler", "F", "I", "getSurfaceWidth", "()I", "setSurfaceWidth", "(I)V", "surfaceWidth", "G", "getSurfaceHeight", "setSurfaceHeight", "surfaceHeight", "", "H", "[F", "getContentLeftCorner", "()[F", "setContentLeftCorner", "([F)V", "contentLeftCorner", "Landroid/graphics/Matrix;", "Landroid/graphics/Matrix;", "getResultMatrix", "()Landroid/graphics/Matrix;", "setResultMatrix", "(Landroid/graphics/Matrix;)V", "resultMatrix", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/miui/mediaeditor/algo/beauty/common/core/widgets/e;", "J", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getRenderListeners", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "renderListeners", "a", "common_release"}, k = 1, mv = {1, 8, 0})
public class BeautyGlView extends c {
    public static final /* synthetic */ int M = 0;

    /* JADX INFO: renamed from: D, reason: from kotlin metadata */
    public GlTexturePhotoRenderer renderer;

    /* JADX INFO: renamed from: E, reason: from kotlin metadata */
    public d gestureTouchHandler;

    /* JADX INFO: renamed from: F, reason: from kotlin metadata */
    public int surfaceWidth;

    /* JADX INFO: renamed from: G, reason: from kotlin metadata */
    public int surfaceHeight;

    /* JADX INFO: renamed from: H, reason: from kotlin metadata */
    public float[] contentLeftCorner;

    /* JADX INFO: renamed from: I, reason: from kotlin metadata */
    public Matrix resultMatrix;

    /* JADX INFO: renamed from: J, reason: from kotlin metadata */
    public final CopyOnWriteArrayList<e> renderListeners;
    public final com.miui.gallery.editor.photo.app.navigator.h K;
    public final b L;

    public static final class a implements b2.a.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f7387a;

        public a(boolean z) {
            this.f7387a = z;
        }

        @Override // b2.a.g
        public final EGLSurface a(EGLDisplay eglDisplay, EGLConfig eglConfig, SurfaceTexture o10) {
            EGLSurface eGLSurfaceEglCreateWindowSurface;
            kotlin.jvm.internal.e.f(eglDisplay, "eglDisplay");
            kotlin.jvm.internal.e.f(eglConfig, "eglConfig");
            kotlin.jvm.internal.e.f(o10, "o");
            try {
                eGLSurfaceEglCreateWindowSurface = EGL14.eglCreateWindowSurface(eglDisplay, eglConfig, o10, wo.b.c(EGL14.eglQueryString(eglDisplay, 12373), this.f7387a), 0);
            } catch (IllegalArgumentException e10) {
                kotlinx.coroutines.internal.a.x(e10, "MediaEditorWindowSurfac", "eglCreateWindowSurface");
                eGLSurfaceEglCreateWindowSurface = null;
            }
            kotlin.jvm.internal.e.c(eGLSurfaceEglCreateWindowSurface);
            return eGLSurfaceEglCreateWindowSurface;
        }

        @Override // b2.a.g
        public final void b(EGLDisplay eglDisplay, EGLSurface eglSurface) {
            kotlin.jvm.internal.e.f(eglDisplay, "eglDisplay");
            kotlin.jvm.internal.e.f(eglSurface, "eglSurface");
            EGL14.eglDestroySurface(eglDisplay, eglSurface);
        }
    }

    public static final class b implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7388a = true;

        public b() {
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void a() {
            Iterator<T> it = BeautyGlView.this.getRenderListeners().iterator();
            while (it.hasNext()) {
                ((e) it.next()).a();
            }
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void b() {
            Iterator<T> it = BeautyGlView.this.getRenderListeners().iterator();
            while (it.hasNext()) {
                ((e) it.next()).b();
            }
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void c(boolean z) {
            Iterator<T> it = BeautyGlView.this.getRenderListeners().iterator();
            while (it.hasNext()) {
                ((e) it.next()).c(z);
            }
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void d() {
            Iterator<T> it = BeautyGlView.this.getRenderListeners().iterator();
            while (it.hasNext()) {
                ((e) it.next()).d();
            }
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void e() {
            Iterator<T> it = BeautyGlView.this.getRenderListeners().iterator();
            while (it.hasNext()) {
                ((e) it.next()).e();
            }
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void f() {
            Iterator<T> it = BeautyGlView.this.getRenderListeners().iterator();
            while (it.hasNext()) {
                ((e) it.next()).f();
            }
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void g() {
            d gestureTouchHandler;
            d.c cVar;
            BeautyGlView beautyGlView = BeautyGlView.this;
            GlTexturePhotoRenderer renderer = beautyGlView.getRenderer();
            kotlin.jvm.internal.e.c(renderer);
            com.faceunity.core.renderer.gltexturerender.d dVar = renderer.d;
            if (dVar == null) {
                kotlin.jvm.internal.e.l("realRenderer");
                throw null;
            }
            beautyGlView.setSurfaceWidth(dVar.f1362n);
            GlTexturePhotoRenderer renderer2 = beautyGlView.getRenderer();
            kotlin.jvm.internal.e.c(renderer2);
            beautyGlView.setSurfaceHeight(renderer2.b());
            beautyGlView.setContentLeftCorner(new float[2]);
            float[] contentLeftCorner = beautyGlView.getContentLeftCorner();
            kotlin.jvm.internal.e.c(contentLeftCorner);
            GlTexturePhotoRenderer renderer3 = beautyGlView.getRenderer();
            kotlin.jvm.internal.e.c(renderer3);
            com.faceunity.core.renderer.gltexturerender.d dVar2 = renderer3.d;
            if (dVar2 == null) {
                kotlin.jvm.internal.e.l("realRenderer");
                throw null;
            }
            contentLeftCorner[0] = -dVar2.f1365t;
            float[] contentLeftCorner2 = beautyGlView.getContentLeftCorner();
            kotlin.jvm.internal.e.c(contentLeftCorner2);
            GlTexturePhotoRenderer renderer4 = beautyGlView.getRenderer();
            kotlin.jvm.internal.e.c(renderer4);
            com.faceunity.core.renderer.gltexturerender.d dVar3 = renderer4.d;
            if (dVar3 == null) {
                kotlin.jvm.internal.e.l("realRenderer");
                throw null;
            }
            float f = dVar3.f1366u;
            GlTexturePhotoRenderer renderer5 = beautyGlView.getRenderer();
            kotlin.jvm.internal.e.c(renderer5);
            contentLeftCorner2[1] = (f + renderer5.a()) - beautyGlView.getSurfaceHeight();
            d gestureTouchHandler2 = beautyGlView.getGestureTouchHandler();
            int surfaceWidth = beautyGlView.getSurfaceWidth();
            int surfaceHeight = beautyGlView.getSurfaceHeight();
            gestureTouchHandler2.l = surfaceWidth;
            gestureTouchHandler2.m = surfaceHeight;
            float f10 = surfaceWidth;
            float f11 = surfaceHeight;
            gestureTouchHandler2.f7402o = new float[]{0.0f, 0.0f, f10, 0.0f, 0.0f, f11, f10, f11};
            gestureTouchHandler2.f7403q = gestureTouchHandler2.d();
            gestureTouchHandler2.r = gestureTouchHandler2.l;
            gestureTouchHandler2.s = gestureTouchHandler2.m;
            if (!this.f7388a && (cVar = (gestureTouchHandler = beautyGlView.getGestureTouchHandler()).f7401n) != null) {
                float[] fArr = gestureTouchHandler.g;
                ((com.miui.gallery.editor.photo.app.navigator.h) cVar).j(gestureTouchHandler.f7399i, Arrays.copyOf(fArr, fArr.length));
            }
            this.f7388a = false;
            for (e eVar : beautyGlView.getRenderListeners()) {
                beautyGlView.getSurfaceWidth();
                beautyGlView.getSurfaceHeight();
                eVar.g();
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BeautyGlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        kotlin.jvm.internal.e.f(context, "context");
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void d(Bitmap bitmap, ProcessMode processMode) {
        kotlin.jvm.internal.e.f(bitmap, "bitmap");
        kotlinx.coroutines.internal.a.o("BeautyGlView", "init() called with: bitmap.width = " + bitmap.getWidth() + ", bitmap.height = " + bitmap.getHeight() + ", processMode = " + processMode);
        ColorSpace colorSpace = bitmap.getColorSpace();
        int i10 = 0;
        setEGLWindowSurfaceFactory(new a(colorSpace != null ? colorSpace.isWideGamut() : false));
        Bitmap magnifierBitmap = getMagnifierBitmap();
        b bVar = this.L;
        kotlin.jvm.internal.e.c(bVar);
        GlTexturePhotoRenderer glTexturePhotoRenderer = new GlTexturePhotoRenderer(this, bitmap, magnifierBitmap, processMode, bVar);
        com.faceunity.core.renderer.gltexturerender.d dVar = glTexturePhotoRenderer.d;
        if (dVar == null) {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
        dVar.Q = 0;
        Context context = getContext();
        kotlin.jvm.internal.e.e(context, "this.context");
        com.miui.mediaeditor.algo.beauty.common.core.widgets.a.C0099a.a(context, glTexturePhotoRenderer);
        this.renderer = glTexturePhotoRenderer;
        d dVar2 = new d(getContext());
        dVar2.f7401n = this.K;
        setGestureTouchHandler(dVar2);
        setOnTouchListener(new com.miui.mediaeditor.algo.beauty.common.core.widgets.b(this, i10));
        e();
    }

    public void e() {
    }

    public final void f() throws InterruptedException {
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer != null) {
            com.faceunity.core.renderer.gltexturerender.d dVar = glTexturePhotoRenderer.d;
            if (dVar != null) {
                dVar.d();
            } else {
                kotlin.jvm.internal.e.l("realRenderer");
                throw null;
            }
        }
    }

    public final void g() {
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer != null) {
            com.faceunity.core.renderer.gltexturerender.d dVar = glTexturePhotoRenderer.d;
            if (dVar != null) {
                dVar.g();
            } else {
                kotlin.jvm.internal.e.l("realRenderer");
                throw null;
            }
        }
    }

    public final int getContentBottom() {
        int contentTop = getContentTop();
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        kotlin.jvm.internal.e.c(glTexturePhotoRenderer);
        return glTexturePhotoRenderer.a() + contentTop;
    }

    public final float[] getContentLeftCorner() {
        return this.contentLeftCorner;
    }

    public final int getContentTop() {
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        kotlin.jvm.internal.e.c(glTexturePhotoRenderer);
        int iB = glTexturePhotoRenderer.b();
        GlTexturePhotoRenderer glTexturePhotoRenderer2 = this.renderer;
        kotlin.jvm.internal.e.c(glTexturePhotoRenderer2);
        com.faceunity.core.renderer.gltexturerender.d dVar = glTexturePhotoRenderer2.d;
        if (dVar == null) {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
        int i10 = dVar.f1358e;
        GlTexturePhotoRenderer glTexturePhotoRenderer3 = this.renderer;
        kotlin.jvm.internal.e.c(glTexturePhotoRenderer3);
        com.faceunity.core.renderer.gltexturerender.d dVar2 = glTexturePhotoRenderer3.d;
        if (dVar2 == null) {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
        int i11 = dVar2.f;
        GlTexturePhotoRenderer glTexturePhotoRenderer4 = this.renderer;
        kotlin.jvm.internal.e.c(glTexturePhotoRenderer4);
        int iA = (((iB - i10) - i11) - glTexturePhotoRenderer4.a()) / 2;
        GlTexturePhotoRenderer glTexturePhotoRenderer5 = this.renderer;
        kotlin.jvm.internal.e.c(glTexturePhotoRenderer5);
        com.faceunity.core.renderer.gltexturerender.d dVar3 = glTexturePhotoRenderer5.d;
        if (dVar3 != null) {
            return dVar3.f1358e + iA;
        }
        kotlin.jvm.internal.e.l("realRenderer");
        throw null;
    }

    public final d getGestureTouchHandler() {
        d dVar = this.gestureTouchHandler;
        if (dVar != null) {
            return dVar;
        }
        kotlin.jvm.internal.e.l("gestureTouchHandler");
        throw null;
    }

    public Bitmap getMagnifierBitmap() {
        return null;
    }

    public final CopyOnWriteArrayList<e> getRenderListeners() {
        return this.renderListeners;
    }

    public final GlTexturePhotoRenderer getRenderer() {
        return this.renderer;
    }

    public final Matrix getResultMatrix() {
        return this.resultMatrix;
    }

    public final int getSurfaceHeight() {
        return this.surfaceHeight;
    }

    public final int getSurfaceWidth() {
        return this.surfaceWidth;
    }

    public final void h(l<? super Boolean, yq.d> lVar) {
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer == null || glTexturePhotoRenderer.f7382e) {
            return;
        }
        com.faceunity.core.renderer.gltexturerender.d dVar = glTexturePhotoRenderer.d;
        if (dVar != null) {
            dVar.l(new com.miui.mediaeditor.algo.beauty.common.core.render.b(lVar, glTexturePhotoRenderer));
        } else {
            kotlin.jvm.internal.e.l("realRenderer");
            throw null;
        }
    }

    public final void i() {
        kotlinx.coroutines.internal.a.o("BeautyGlView", "refresh() called");
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer != null) {
            glTexturePhotoRenderer.c();
        }
    }

    public final void j() {
        this.renderListeners.clear();
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer != null) {
            kotlinx.coroutines.internal.a.o("GlTexturePhotoRenderer", "releaseGLSurfaceView");
            com.faceunity.core.renderer.gltexturerender.d dVar = glTexturePhotoRenderer.d;
            if (dVar == null) {
                kotlin.jvm.internal.e.l("realRenderer");
                throw null;
            }
            ((ExecutorService) dVar.f1376d0.getValue()).shutdown();
            m2.a aVar = dVar.c().f1330c;
            if (aVar != null) {
                aVar.w(1);
            }
            if (dVar.d) {
                if (b2.b.g == null) {
                    synchronized (b2.b.class) {
                        if (b2.b.g == null) {
                            b2.b.g = new b2.b();
                        }
                        yq.d dVar2 = yq.d.f18754a;
                    }
                }
                b2.b bVar = b2.b.g;
                kotlin.jvm.internal.e.c(bVar);
                FULogger.a(bVar.f385a, "canPostEvent true");
                bVar.f388e = true;
                Runnable runnable = dVar.S;
                if (runnable != null) {
                    if (b2.b.g == null) {
                        synchronized (b2.b.class) {
                            if (b2.b.g == null) {
                                b2.b.g = new b2.b();
                            }
                            yq.d dVar3 = yq.d.f18754a;
                        }
                    }
                    b2.b bVar2 = b2.b.g;
                    kotlin.jvm.internal.e.c(bVar2);
                    if (bVar2.f388e) {
                        FULogger.a(bVar2.f385a, "queueEvent");
                        ExecutorService executorService = bVar2.f386b;
                        if (executorService != null) {
                            executorService.execute(new androidx.view.l(runnable, 8));
                        }
                    } else {
                        FULogger.a(bVar2.f385a, "queueEvent mCanPostEvent = false");
                    }
                }
            } else {
                FURenderBridge fURenderBridgeG = dVar.c().g();
                synchronized (fURenderBridgeG.f1381a) {
                    fURenderBridgeG.f1383c.clear();
                    fURenderBridgeG.d.clear();
                    SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                    Faceunity.fuReleaseGLResources();
                    FULogger.a("KIT_SDKController", "releaseGLResources");
                    yq.d dVar4 = yq.d.f18754a;
                }
            }
            String folderPath = dVar.Y;
            kotlin.jvm.internal.e.f(folderPath, "folderPath");
            miuix.appcompat.app.floatingactivity.i.m(folderPath);
            l2.a aVar2 = dVar.f1356b;
            if (aVar2 != null) {
                aVar2.b();
            }
            dVar.f1356b = null;
            dVar.f1355a = null;
        }
    }

    public final void k(final l<? super CacheData, yq.d> lVar) {
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer != null) {
            glTexturePhotoRenderer.e(new l<CacheData, yq.d>() { // from class: com.miui.mediaeditor.algo.beauty.common.core.widgets.BeautyGlView$saveData$1
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
            });
        }
    }

    public final void l(boolean z) {
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer != null) {
            com.faceunity.core.renderer.gltexturerender.d dVar = glTexturePhotoRenderer.d;
            if (dVar == null) {
                kotlin.jvm.internal.e.l("realRenderer");
                throw null;
            }
            dVar.G = !z;
            dVar.i(false);
        }
    }

    public final void setContentLeftCorner(float[] fArr) {
        this.contentLeftCorner = fArr;
    }

    public final void setGestureTouchHandler(d dVar) {
        kotlin.jvm.internal.e.f(dVar, "<set-?>");
        this.gestureTouchHandler = dVar;
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        GlTexturePhotoRenderer glTexturePhotoRenderer = this.renderer;
        if (glTexturePhotoRenderer != null) {
            Context context = getContext();
            kotlin.jvm.internal.e.e(context, "this.context");
            com.miui.mediaeditor.algo.beauty.common.core.widgets.a.C0099a.a(context, glTexturePhotoRenderer);
        }
    }

    public final void setRenderer(GlTexturePhotoRenderer glTexturePhotoRenderer) {
        this.renderer = glTexturePhotoRenderer;
    }

    public final void setResultMatrix(Matrix matrix) {
        kotlin.jvm.internal.e.f(matrix, "<set-?>");
        this.resultMatrix = matrix;
    }

    public final void setSurfaceHeight(int i10) {
        this.surfaceHeight = i10;
    }

    public final void setSurfaceWidth(int i10) {
        this.surfaceWidth = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BeautyGlView(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        kotlin.jvm.internal.e.f(context, "context");
        this.resultMatrix = new Matrix();
        this.renderListeners = new CopyOnWriteArrayList<>();
        this.K = new com.miui.gallery.editor.photo.app.navigator.h(this, 14);
        this.L = new b();
    }
}
