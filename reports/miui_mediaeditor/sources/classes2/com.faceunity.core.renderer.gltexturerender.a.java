package com.faceunity.core.renderer.gltexturerender;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.util.Log;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b2.a.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BaseGLTextureRenderer f1370a;

    public a(BaseGLTextureRenderer baseGLTextureRenderer) {
        this.f1370a = baseGLTextureRenderer;
    }

    @Override // b2.a.f
    public final EGLContext a(EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
        int[] iArr = {12440, this.f1370a.f1357c, 12344};
        if (b2.b.g == null) {
            synchronized (b2.b.class) {
                if (b2.b.g == null) {
                    b2.b.g = new b2.b();
                }
                yq.d dVar = yq.d.f18754a;
            }
        }
        b2.b bVar = b2.b.g;
        e.c(bVar);
        EGLContext eGLContext = bVar.f;
        if (this.f1370a.f1357c == 0) {
            iArr = null;
        }
        EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr, 0);
        e.e(eGLContextEglCreateContext, "eglCreateContext(\n      …lse null, 0\n            )");
        return eGLContextEglCreateContext;
    }

    @Override // b2.a.f
    public final void b(EGLDisplay eGLDisplay, EGLContext eGLContext) {
        if (EGL14.eglDestroyContext(eGLDisplay, eGLContext)) {
            return;
        }
        Log.e(this.f1370a.j, "display:" + eGLDisplay + " context: " + eGLContext);
    }
}
