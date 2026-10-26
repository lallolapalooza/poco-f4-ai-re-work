package com.yxcorp.gifshow.camerasdk.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import com.kwai.camerasdk.render.VideoSurfaceView;
import com.yxcorp.utility.c1;
import com.yxcorp.utility.y1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes16.dex */
public class CameraView extends FrameLayout implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final PointF f38613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PointF f38614b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f38615c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile VideoSurfaceView f38616d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile FocusView f38617e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile View f38618f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b f38619g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final CopyOnWriteArrayList<CameraClickListener> f38620h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final View.OnClickListener f38621i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float f38622j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private ScaleGestureDetector f38623k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private volatile GestureDetector f38624l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Map<Object, CameraViewListener> f38625m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private List<View.OnTouchListener> f38626n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private volatile int f38627o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float f38628p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f38629q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private long f38630r;

    public interface CameraClickListener {
        boolean onClick();
    }

    public interface CameraViewListener {
        void onDoubleTap();

        void onFlingLeft();

        void onFlingRight();

        void onFlingUp();

        void onScale(float f12);

        void onScaleBegin(float f12);

        void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f12, float f13);
    }

    public interface b {
        boolean o(Rect rect);
    }

    private class c extends GestureDetector.SimpleOnGestureListener {
        private c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (CameraView.this.f38625m.size() <= 0) {
                return true;
            }
            Iterator it2 = CameraView.this.f38625m.values().iterator();
            while (it2.hasNext()) {
                ((CameraViewListener) it2.next()).onDoubleTap();
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f12, float f13) {
            if (motionEvent == null || motionEvent2 == null) {
                return false;
            }
            if (System.currentTimeMillis() - CameraView.this.f38630r < 1000) {
                return true;
            }
            boolean z12 = f12 < 0.0f;
            float fAbs = Math.abs(motionEvent.getX() - motionEvent2.getX());
            boolean z13 = f13 < 0.0f;
            float fAbs2 = Math.abs(motionEvent.getY() - motionEvent2.getY());
            if (CameraView.this.f38625m.size() > 0) {
                if (fAbs > CameraView.this.f38627o) {
                    if (c1.d()) {
                        if (z12) {
                            Iterator it2 = CameraView.this.f38625m.values().iterator();
                            while (it2.hasNext()) {
                                ((CameraViewListener) it2.next()).onFlingRight();
                            }
                            return true;
                        }
                        Iterator it3 = CameraView.this.f38625m.values().iterator();
                        while (it3.hasNext()) {
                            ((CameraViewListener) it3.next()).onFlingLeft();
                        }
                        return true;
                    }
                    if (z12) {
                        Iterator it4 = CameraView.this.f38625m.values().iterator();
                        while (it4.hasNext()) {
                            ((CameraViewListener) it4.next()).onFlingLeft();
                        }
                        return true;
                    }
                    Iterator it5 = CameraView.this.f38625m.values().iterator();
                    while (it5.hasNext()) {
                        ((CameraViewListener) it5.next()).onFlingRight();
                    }
                    return true;
                }
                if (fAbs2 > CameraView.this.f38627o && z13) {
                    Iterator it6 = CameraView.this.f38625m.values().iterator();
                    while (it6.hasNext()) {
                        ((CameraViewListener) it6.next()).onFlingUp();
                    }
                    return true;
                }
            }
            return super.onFling(motionEvent, motionEvent2, f12, f13);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f12, float f13) {
            if (CameraView.this.f38625m.size() <= 0) {
                return false;
            }
            Iterator it2 = CameraView.this.f38625m.values().iterator();
            while (it2.hasNext()) {
                ((CameraViewListener) it2.next()).onScroll(motionEvent, motionEvent2, f12, f13);
            }
            return false;
        }
    }

    private class d extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private d() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            double d12;
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (scaleFactor <= 1.0f) {
                if (scaleFactor < 1.0f && scaleFactor > 0.1f) {
                    d12 = ((double) scaleFactor) - 0.1d;
                }
                CameraView.g(CameraView.this, scaleFactor);
                CameraView cameraView = CameraView.this;
                cameraView.f38628p = Math.max(1.0f, cameraView.f38628p);
                if (CameraView.this.f38625m.size() <= 0 && CameraView.this.f38629q != CameraView.this.f38628p) {
                    Iterator it2 = CameraView.this.f38625m.values().iterator();
                    while (it2.hasNext()) {
                        ((CameraViewListener) it2.next()).onScale(CameraView.this.f38628p);
                    }
                    CameraView cameraView2 = CameraView.this;
                    cameraView2.f38629q = cameraView2.f38628p;
                    return true;
                }
            }
            d12 = ((double) scaleFactor) + 0.1d;
            scaleFactor = (float) d12;
            CameraView.g(CameraView.this, scaleFactor);
            CameraView cameraView3 = CameraView.this;
            cameraView3.f38628p = Math.max(1.0f, cameraView3.f38628p);
            return CameraView.this.f38625m.size() <= 0 ? false : false;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            if (CameraView.this.f38625m.size() > 0) {
                Iterator it2 = CameraView.this.f38625m.values().iterator();
                while (it2.hasNext()) {
                    ((CameraViewListener) it2.next()).onScaleBegin(scaleGestureDetector.getScaleFactor());
                }
            }
            return super.onScaleBegin(scaleGestureDetector);
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            CameraView.this.f38630r = System.currentTimeMillis();
        }
    }

    public CameraView(Context context, AttributeSet attributeSet, int i12) {
        super(context, attributeSet, i12);
        this.f38613a = new PointF();
        this.f38614b = new PointF();
        this.f38615c = false;
        this.f38620h = new CopyOnWriteArrayList<>();
        this.f38621i = new View.OnClickListener() { // from class: o80.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f82981a.r(view);
            }
        };
        this.f38622j = 1.0f;
        this.f38625m = new HashMap();
        this.f38626n = new ArrayList();
        this.f38627o = 100;
        this.f38628p = 1.0f;
        this.f38629q = 1.0f;
        p(context);
    }

    static /* synthetic */ float g(CameraView cameraView, float f12) {
        float f13 = cameraView.f38628p * f12;
        cameraView.f38628p = f13;
        return f13;
    }

    private ScaleGestureDetector getScaleDetector() {
        if (this.f38623k == null) {
            this.f38623k = new ScaleGestureDetector(getContext(), new d());
        }
        return this.f38623k;
    }

    private void o(float f12, float f13) {
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0 || this.f38619g == null) {
            return;
        }
        if (this.f38619g.o(new Rect((int) (f12 - 100.0f), (int) (f13 - 100.0f), (int) (f12 + 100.0f), (int) (100.0f + f13)))) {
            float f14 = getResources().getDisplayMetrics().density * 40.0f;
            this.f38617e.setArea(new Rect((int) (f12 - f14), (int) (f13 - f14), (int) (f12 + f14), (int) (f13 + f14)));
        }
    }

    private void p(Context context) {
        q(context);
    }

    private void q(Context context) {
        this.f38616d = new VideoSurfaceView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.f38616d.setLayoutParams(layoutParams);
        addView(this.f38616d);
        this.f38617e = new FocusView(getContext());
        addView(this.f38617e, -1, -1);
        this.f38618f = new View(context);
        this.f38618f.setBackgroundColor(-16777216);
        addView(this.f38618f, -1, -1);
        super.setOnClickListener(this.f38621i);
        c cVar = new c();
        this.f38624l = new GestureDetector(getContext(), cVar, new Handler(Looper.getMainLooper()));
        this.f38624l.setOnDoubleTapListener(cVar);
        this.f38627o = y1.x(context) / 5;
        super.setOnTouchListener(new View.OnTouchListener() { // from class: o80.c
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f82984a.onTouch(view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(View view) {
        if (Math.abs(this.f38614b.x - this.f38613a.x) >= 20.0f || Math.abs(this.f38614b.y - this.f38613a.y) >= 20.0f) {
            return;
        }
        Iterator<CameraClickListener> it2 = this.f38620h.iterator();
        while (it2.hasNext()) {
            if (it2.next().onClick()) {
                return;
            }
        }
        PointF pointF = this.f38613a;
        o(pointF.x, pointF.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(View.OnClickListener onClickListener, View view) {
        this.f38621i.onClick(view);
        onClickListener.onClick(view);
    }

    public b getCameraFocusHandler() {
        return this.f38619g;
    }

    public FocusView getFocusView() {
        return this.f38617e;
    }

    public View getMaskView() {
        return this.f38618f;
    }

    public int getPreviewHeight() {
        return this.f38616d.getHeight();
    }

    public VideoSurfaceView getSurfaceView() {
        return this.f38616d;
    }

    public void m(CameraClickListener cameraClickListener) {
        this.f38620h.add(cameraClickListener);
    }

    public void n(View.OnTouchListener onTouchListener) {
        this.f38626n.add(onTouchListener);
    }

    /* JADX WARN: Code duplicated, block: B:6:0x002a A[PHI: r0 r2
      0x002a: PHI (r0v4 float) = (r0v1 float), (r0v7 float) binds: [B:16:0x0053, B:5:0x0028] A[DONT_GENERATE, DONT_INLINE]
      0x002a: PHI (r2v7 float) = (r2v3 float), (r2v8 float) binds: [B:16:0x0053, B:5:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x002e A[PHI: r1 r2
      0x002e: PHI (r1v2 float) = (r1v1 float), (r1v3 float) binds: [B:16:0x0053, B:5:0x0028] A[DONT_GENERATE, DONT_INLINE]
      0x002e: PHI (r2v4 float) = (r2v3 float), (r2v8 float) binds: [B:16:0x0053, B:5:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i12, int i13) {
        float f12;
        float f13;
        float f14;
        int mode = View.MeasureSpec.getMode(i13);
        int mode2 = View.MeasureSpec.getMode(i12);
        int defaultSize = FrameLayout.getDefaultSize(getSuggestedMinimumHeight(), i13);
        int defaultSize2 = FrameLayout.getDefaultSize(getSuggestedMinimumWidth(), i12);
        if (this.f38615c) {
            f12 = defaultSize2;
            f13 = defaultSize;
            f14 = this.f38622j;
            if (f12 > f13 * f14) {
                defaultSize = (int) ((f12 / f14) + 0.5f);
            } else {
                defaultSize2 = (int) ((f14 * f13) + 0.5f);
            }
        } else if (mode != 1073741824 || mode2 != 1073741824) {
            if (mode == 1073741824) {
                defaultSize2 = (int) ((this.f38622j * defaultSize) + 0.5f);
            } else if (mode2 == 1073741824) {
                defaultSize = (int) ((defaultSize2 / this.f38622j) + 0.5f);
            } else {
                f12 = defaultSize2;
                f13 = defaultSize;
                f14 = this.f38622j;
                if (f12 > f13 * f14) {
                    defaultSize2 = (int) ((f14 * f13) + 0.5f);
                } else {
                    defaultSize = (int) ((f12 / f14) + 0.5f);
                }
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(defaultSize2, 1073741824), View.MeasureSpec.makeMeasureSpec(defaultSize, 1073741824));
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        List<View.OnTouchListener> list = this.f38626n;
        if (list != null) {
            Iterator<View.OnTouchListener> it2 = list.iterator();
            while (it2.hasNext()) {
                if (it2.next().onTouch(this, motionEvent)) {
                    return super.onTouchEvent(motionEvent);
                }
            }
        }
        getScaleDetector().onTouchEvent(motionEvent);
        this.f38624l.onTouchEvent(motionEvent);
        if ((motionEvent.getAction() & 255) == 0) {
            this.f38613a.set(motionEvent.getX(), motionEvent.getY());
        }
        if ((motionEvent.getAction() & 255) == 1) {
            this.f38614b.set(motionEvent.getX(), motionEvent.getY());
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCameraFocusHandler(b bVar) {
        this.f38619g = bVar;
    }

    public void setIsFullScreen(boolean z12) {
        this.f38615c = z12;
    }

    @Override // android.view.View
    public void setOnClickListener(final View.OnClickListener onClickListener) {
        super.setOnClickListener(new View.OnClickListener() { // from class: o80.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f82982a.s(onClickListener, view);
            }
        });
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.f38626n.add(onTouchListener);
    }

    public void setRatio(float f12) {
        this.f38622j = f12;
        requestLayout();
    }

    public void setSurfaceViewVisibility(int i12) {
        this.f38616d.setVisibility(i12);
    }

    public void t(CameraClickListener cameraClickListener) {
        this.f38620h.remove(cameraClickListener);
    }

    public void u(View.OnTouchListener onTouchListener) {
        this.f38626n.remove(onTouchListener);
    }

    public void v(Object obj, CameraViewListener cameraViewListener) {
        if (cameraViewListener != null) {
            this.f38625m.put(obj, cameraViewListener);
        } else {
            this.f38625m.remove(obj);
        }
    }

    public static class FocusView extends View implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Rect f38631a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Rect f38632b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Drawable f38633c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Paint f38634d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Animator f38635e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f38636f;

        class a implements ValueAnimator.AnimatorUpdateListener {
            a() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (FocusView.this.f38632b == null || FocusView.this.f38631a == null) {
                    return;
                }
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue() - 1.0f;
                FocusView.this.f38631a.bottom = (int) (FocusView.this.f38632b.bottom + ((FocusView.this.f38632b.height() * fFloatValue) / 2.0f));
                FocusView.this.f38631a.top = (int) (FocusView.this.f38632b.top - ((FocusView.this.f38632b.height() * fFloatValue) / 2.0f));
                FocusView.this.f38631a.left = (int) (FocusView.this.f38632b.left - ((FocusView.this.f38632b.width() * fFloatValue) / 2.0f));
                FocusView.this.f38631a.right = (int) (FocusView.this.f38632b.right + ((fFloatValue * FocusView.this.f38632b.width()) / 2.0f));
                FocusView.this.invalidate();
            }
        }

        class b implements ValueAnimator.AnimatorUpdateListener {
            b() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                FocusView.this.f38636f = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            }
        }

        class c implements ValueAnimator.AnimatorUpdateListener {
            c() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (FocusView.this.f38632b == null || FocusView.this.f38631a == null) {
                    return;
                }
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue() - 1.0f;
                FocusView.this.f38631a.bottom = (int) (FocusView.this.f38632b.bottom + ((FocusView.this.f38632b.height() * fFloatValue) / 2.0f));
                FocusView.this.f38631a.top = (int) (FocusView.this.f38632b.top - ((FocusView.this.f38632b.height() * fFloatValue) / 2.0f));
                FocusView.this.f38631a.left = (int) (FocusView.this.f38632b.left - ((FocusView.this.f38632b.width() * fFloatValue) / 2.0f));
                FocusView.this.f38631a.right = (int) (FocusView.this.f38632b.right + ((fFloatValue * FocusView.this.f38632b.width()) / 2.0f));
                FocusView.this.invalidate();
            }
        }

        class d implements ValueAnimator.AnimatorUpdateListener {
            d() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                FocusView.this.f38636f = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                FocusView.this.invalidate();
            }
        }

        public FocusView(Context context, AttributeSet attributeSet, int i12) {
            super(context, attributeSet, i12);
            this.f38636f = 255;
            d();
        }

        private void d() {
        }

        private AnimatorSet getFocusAni() {
            AnimatorSet animatorSet = new AnimatorSet();
            AnimatorSet animatorSet2 = new AnimatorSet();
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.6f, 0.9f);
            valueAnimatorOfFloat.setDuration(250L);
            valueAnimatorOfFloat.setInterpolator(new LinearInterpolator());
            valueAnimatorOfFloat.addUpdateListener(new a());
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 255);
            valueAnimatorOfInt.setDuration(250L);
            valueAnimatorOfInt.setInterpolator(new LinearInterpolator());
            valueAnimatorOfInt.addUpdateListener(new b());
            animatorSet2.playTogether(valueAnimatorOfFloat, valueAnimatorOfInt);
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.9f, 1.0f);
            valueAnimatorOfFloat2.setDuration(250L);
            valueAnimatorOfFloat2.setInterpolator(new LinearInterpolator());
            valueAnimatorOfFloat2.addUpdateListener(new c());
            ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(255, 0);
            valueAnimatorOfInt2.setDuration(250L);
            valueAnimatorOfInt2.setInterpolator(new LinearInterpolator());
            valueAnimatorOfInt2.addUpdateListener(new d());
            valueAnimatorOfInt2.setStartDelay(250L);
            animatorSet.playSequentially(animatorSet2, valueAnimatorOfFloat2, valueAnimatorOfInt2);
            return animatorSet;
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (this.f38631a == null) {
                return;
            }
            if (this.f38634d == null) {
                Paint paint = new Paint(2);
                this.f38634d = paint;
                paint.setColor(-16711681);
                this.f38634d.setStrokeWidth(2.0f);
                this.f38634d.setStyle(Paint.Style.STROKE);
            }
            Drawable drawable = this.f38633c;
            if (drawable == null) {
                canvas.drawRect(this.f38631a, this.f38634d);
                return;
            }
            drawable.setAlpha(this.f38636f);
            this.f38633c.setBounds(this.f38631a);
            this.f38633c.draw(canvas);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f38631a != null) {
                this.f38631a = null;
                invalidate();
            }
        }

        public void setArea(Rect rect) {
            this.f38631a = rect;
            this.f38632b = new Rect(rect);
            invalidate();
            removeCallbacks(this);
            if (this.f38631a != null) {
                postDelayed(this, 1000L);
            }
            Animator animator = this.f38635e;
            if (animator == null) {
                this.f38635e = getFocusAni();
            } else {
                animator.cancel();
            }
            this.f38635e.start();
        }

        public void setDrawable(Drawable drawable) {
            this.f38633c = drawable;
        }

        public FocusView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f38636f = 255;
            d();
        }

        public FocusView(Context context) {
            super(context);
            this.f38636f = 255;
            d();
        }
    }

    public CameraView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f38613a = new PointF();
        this.f38614b = new PointF();
        this.f38615c = false;
        this.f38620h = new CopyOnWriteArrayList<>();
        this.f38621i = new View.OnClickListener() { // from class: o80.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f82981a.r(view);
            }
        };
        this.f38622j = 1.0f;
        this.f38625m = new HashMap();
        this.f38626n = new ArrayList();
        this.f38627o = 100;
        this.f38628p = 1.0f;
        this.f38629q = 1.0f;
        p(context);
    }

    public CameraView(Context context) {
        super(context);
        this.f38613a = new PointF();
        this.f38614b = new PointF();
        this.f38615c = false;
        this.f38620h = new CopyOnWriteArrayList<>();
        this.f38621i = new View.OnClickListener() { // from class: o80.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f82981a.r(view);
            }
        };
        this.f38622j = 1.0f;
        this.f38625m = new HashMap();
        this.f38626n = new ArrayList();
        this.f38627o = 100;
        this.f38628p = 1.0f;
        this.f38629q = 1.0f;
        p(context);
    }
}
