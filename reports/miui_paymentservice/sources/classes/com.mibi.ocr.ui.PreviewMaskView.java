package com.mibi.ocr.ui;

import a.b.a.c;
import a.b.a.e;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class PreviewMaskView extends View {
    private static final double o = 0.95d;
    private static final long p = 100;
    private static final int[] q = {0, 32, 64, 96, 128, 160, 192, 224, 192, 160, 128, 96, 64, 32};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2738a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2739b;
    private int h;
    private Paint i;
    private RectF j;
    private Xfermode k;
    private Xfermode l;
    private Rect m;
    private c.C0010c n;

    public PreviewMaskView(Context context) {
        this(context, null);
    }

    private void a() {
        this.i = new Paint(1);
        this.k = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.l = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        this.f2739b = getResources().getColor(e.f.mibi_focus_view_mask);
        this.h = getResources().getColor(e.f.mibi_focus_view_middle_line);
        this.f2738a = 0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.m == null || this.j == null) {
            return;
        }
        canvas.drawColor(this.f2739b);
        this.i.setAlpha(255);
        this.i.setStyle(Paint.Style.FILL);
        this.i.setXfermode(this.k);
        canvas.drawRoundRect(this.j, 20.0f, 20.0f, this.i);
        this.i.setColor(this.h);
        this.i.setXfermode(this.l);
        Paint paint = this.i;
        int[] iArr = q;
        int i = this.f2738a;
        this.f2738a = i + 1;
        paint.setAlpha(iArr[i % iArr.length]);
        Rect rect = this.m;
        float fWidth = rect.left + (rect.width() / 15);
        Rect rect2 = this.m;
        float f2 = ((rect2.top + rect2.bottom) / 2) - 1;
        float fWidth2 = rect2.right - (rect2.width() / 15);
        Rect rect3 = this.m;
        canvas.drawRect(fWidth, f2, fWidth2, ((rect3.top + rect3.bottom) / 2) + 1, this.i);
        Rect rect4 = this.m;
        int iWidth = rect4.left + (rect4.width() / 15);
        Rect rect5 = this.m;
        int i2 = ((rect5.top + rect5.bottom) / 2) - 1;
        int iWidth2 = rect5.right - (rect5.width() / 15);
        Rect rect6 = this.m;
        postInvalidateDelayed(p, iWidth, i2, iWidth2, ((rect6.top + rect6.bottom) / 2) + 2);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iResolveSize = View.resolveSize(getSuggestedMinimumWidth(), i);
        int iResolveSize2 = View.resolveSize(getSuggestedMinimumHeight(), i2);
        setMeasuredDimension(iResolveSize, iResolveSize2);
        if (this.n == null) {
            this.n = new c.C0010c(iResolveSize, iResolveSize2);
        }
        if (this.m == null) {
            a(this.n);
        }
        if (this.j == null) {
            this.j = new RectF(this.m);
        }
    }

    public PreviewMaskView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreviewMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public Rect a(c.C0010c c0010c) {
        if (c0010c == null) {
            return null;
        }
        int i = c0010c.f407a;
        int i2 = c0010c.f408b;
        int dimensionPixelSize = getResources().getDimensionPixelSize(e.g.mibi_camera_frame_rect_width);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(e.g.mibi_camera_frame_rect_height);
        if (i < dimensionPixelSize) {
            double d2 = i;
            Double.isNaN(d2);
            dimensionPixelSize = (int) (d2 * o);
        }
        this.m = new Rect((i - dimensionPixelSize) / 2, (i2 - dimensionPixelSize2) / 2, (i + dimensionPixelSize) / 2, (i2 + dimensionPixelSize2) / 2);
        return this.m;
    }
}
