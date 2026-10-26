package com.xiaomi.mitv.phone.remotecontroller.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.duokan.phone.remotecontroller.R;
import l1.d;

/* JADX INFO: loaded from: classes2.dex */
public class SpeechVolumeView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f19042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f19043b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f19044c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f19045d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f19046e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f19047f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f19048g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Paint f19049h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Paint f19050i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Paint f19051j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public RectF f19052k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public RectF f19053l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public RectF f19054m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public RectF f19055n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public RectF f19056o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public RectF[] f19057p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f19058q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f19059r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f19060s;

    public SpeechVolumeView(Context context) {
        super(context);
        this.f19058q = 0;
        this.f19059r = 0.0f;
        this.f19060s = 1;
        a(context);
    }

    public SpeechVolumeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f19058q = 0;
        this.f19059r = 0.0f;
        this.f19060s = 1;
        a(context);
    }

    public SpeechVolumeView(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        this.f19058q = 0;
        this.f19059r = 0.0f;
        this.f19060s = 1;
        a(context);
    }

    public final void a(Context context) {
        this.f19042a = getResources().getDimension(R.dimen.speech_volum_1);
        this.f19043b = getResources().getDimension(R.dimen.speech_volum_2);
        this.f19044c = getResources().getDimension(R.dimen.speech_volum_3);
        this.f19045d = getResources().getDimension(R.dimen.speech_volum_4);
        this.f19046e = getResources().getDimension(R.dimen.speech_volum_height);
        this.f19047f = getResources().getDimension(R.dimen.speech_volum_border);
        this.f19048g = getResources().getDimension(R.dimen.speech_volum_radius);
        Paint paint = new Paint();
        this.f19049h = paint;
        paint.setColor(Color.parseColor("#000000"));
        this.f19049h.setAlpha(76);
        float f10 = this.f19047f;
        this.f19052k = new RectF(0.0f, 0.0f, f10, f10);
        float f11 = this.f19046e;
        float f12 = f11 / 3.0f;
        float fA = d.a(f11, 5.0f, this.f19047f, 2.0f);
        float f13 = fA + f11 + f12;
        float f14 = f13 + f11 + f12;
        float f15 = f11 + f14 + f12;
        float f16 = this.f19047f;
        float f17 = this.f19042a;
        this.f19053l = new RectF((f16 - f17) / 2.0f, f15, (f16 + f17) / 2.0f, this.f19046e + f15);
        float f18 = this.f19047f;
        float f19 = this.f19043b;
        this.f19054m = new RectF((f18 - f19) / 2.0f, f14, (f18 + f19) / 2.0f, this.f19046e + f14);
        float f20 = this.f19047f;
        float f21 = this.f19044c;
        this.f19055n = new RectF((f20 - f21) / 2.0f, f13, (f20 + f21) / 2.0f, this.f19046e + f13);
        float f22 = this.f19047f;
        float f23 = this.f19045d;
        RectF rectF = new RectF((f22 - f23) / 2.0f, fA, (f22 + f23) / 2.0f, this.f19046e + fA);
        this.f19056o = rectF;
        this.f19057p = new RectF[]{this.f19053l, this.f19054m, this.f19055n, rectF};
        for (int i10 = 0; i10 < 4; i10++) {
            RectF rectF2 = this.f19057p[i10];
            float f24 = rectF2.left;
            float f25 = rectF2.right;
            float f26 = rectF2.top;
            float f27 = rectF2.bottom;
        }
        Paint paint2 = new Paint();
        this.f19050i = paint2;
        paint2.setColor(Color.parseColor("#555555"));
        this.f19050i.setAlpha(255);
        Paint paint3 = new Paint();
        this.f19051j = paint3;
        paint3.setColor(Color.parseColor("#1155ee"));
        this.f19051j.setAlpha(255);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        RectF rectF = this.f19052k;
        float f10 = this.f19048g;
        canvas.drawRoundRect(rectF, f10, f10, this.f19049h);
        int i10 = 0;
        int i11 = 0;
        while (true) {
            RectF[] rectFArr = this.f19057p;
            if (i11 >= rectFArr.length) {
                break;
            }
            canvas.drawRect(rectFArr[i11], this.f19050i);
            i11++;
        }
        float f11 = this.f19059r;
        while (i10 < (this.f19058q * 4) / this.f19060s) {
            canvas.drawRect(this.f19057p[i10], this.f19051j);
            f11 -= this.f19046e;
            i10++;
        }
        if (f11 > 0.0f && i10 < 4) {
            RectF rectF2 = this.f19057p[i10];
            float f12 = rectF2.left;
            float f13 = rectF2.bottom;
            canvas.drawRect(f12, f13 - f11, rectF2.right, f13, this.f19051j);
        }
        super.onDraw(canvas);
    }

    public void setMaxVolume(int i10) {
        this.f19060s = i10;
    }

    public void setVolume(int i10) {
        this.f19058q = i10;
        this.f19059r = ((i10 * 4) * this.f19046e) / this.f19060s;
        invalidate();
    }
}
