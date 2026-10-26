package com.mibi.ocr.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* JADX INFO: loaded from: classes.dex */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f2731a;

    public interface a {
        void a(SurfaceHolder surfaceHolder);

        void a(SurfaceHolder surfaceHolder, int i, int i2, int i3);

        void b(SurfaceHolder surfaceHolder);
    }

    public CameraPreview(Context context) {
        this(context, null);
    }

    public void setPreviewCallback(a aVar) {
        this.f2731a = aVar;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        a aVar = this.f2731a;
        if (aVar != null) {
            aVar.a(surfaceHolder, i, i2, i3);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        a aVar = this.f2731a;
        if (aVar != null) {
            aVar.b(surfaceHolder);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        a aVar = this.f2731a;
        if (aVar != null) {
            aVar.a(surfaceHolder);
        }
    }

    public CameraPreview(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CameraPreview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        getHolder().addCallback(this);
    }
}
