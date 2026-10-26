package com.miui.mediaeditor.photo.remover;

import android.graphics.Bitmap;
import com.miui.gallery.editor.photo.app.remover.Inpaint;
import com.miui.mediaeditor.photo.base.FunctionFragment;
import com.miui.mediaeditor.photo.core.render.RenderData;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends com.miui.mediaeditor.photo.base.service.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f8912b = new b();

    public final void a() {
        this.f8912b.getClass();
        Inpaint.release();
    }

    @Override // cm.c
    public final Bitmap g(Bitmap bitmap, RenderData renderData, boolean z) {
        e.f(bitmap, "bitmap");
        e.f(renderData, "renderData");
        Bitmap bitmapD = this.f8912b.d(bitmap, renderData, z);
        e.e(bitmapD, "removerEngine.render(bit…renderData, renderOrigin)");
        return bitmapD;
    }

    @Override // com.miui.mediaeditor.photo.base.service.b
    public final FunctionFragment n() {
        return new RemoverFunctionFragment();
    }
}
