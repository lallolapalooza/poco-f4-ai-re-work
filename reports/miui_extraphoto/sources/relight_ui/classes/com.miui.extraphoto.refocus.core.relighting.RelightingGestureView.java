package com.miui.extraphoto.refocus.core.relighting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.miui.extraphoto.refocus.R$color;
import com.miui.extraphoto.refocus.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class RelightingGestureView extends View {
    private float mAngle;
    private RectF mAvailableRect;
    private boolean mCanDraw;
    private float mCenterX;
    private float mCenterY;
    private float mCurrentX;
    private float mCurrentY;
    private Runnable mDismissRunnable;
    private GestureDetector mGestureDetector;
    private GestureDetector.OnGestureListener mGestureListener;
    private boolean mInitShow;
    private Matrix mMatrix;
    private float mMaxDrawRadius;
    private RectF mOvalRectF;
    private Paint mPaint;
    private float[] mPoint;
    private OnPositionChangeListener mPositionChangeListener;
    private float mRadius;
    private float mSphereRadius;
    private Paint mStrokePaint;

    public interface OnPositionChangeListener {
        void onDown();

        void onPositionChange(float f, float f2);

        void onUp();
    }

    static /* synthetic */ float access$224(RelightingGestureView relightingGestureView, float f) {
        float f2 = relightingGestureView.mCurrentX - f;
        relightingGestureView.mCurrentX = f2;
        return f2;
    }

    static /* synthetic */ float access$324(RelightingGestureView relightingGestureView, float f) {
        float f2 = relightingGestureView.mCurrentY - f;
        relightingGestureView.mCurrentY = f2;
        return f2;
    }

    public RelightingGestureView(Context context) {
        super(context);
        this.mPoint = new float[2];
        this.mMatrix = new Matrix();
        this.mOvalRectF = new RectF();
        this.mStrokePaint = new Paint(1);
        this.mPaint = new Paint(1);
        this.mCanDraw = false;
        this.mInitShow = false;
        this.mGestureListener = new GestureDetector.OnGestureListener() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingGestureView.1
            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                RelightingGestureView relightingGestureView = RelightingGestureView.this;
                relightingGestureView.removeCallbacks(relightingGestureView.mDismissRunnable);
                RelightingGestureView.this.notifyDown();
                return true;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                RelightingGestureView.access$224(RelightingGestureView.this, f);
                RelightingGestureView.access$324(RelightingGestureView.this, f2);
                RelightingGestureView.this.refreshPosition();
                RelightingGestureView.this.mCanDraw = true;
                RelightingGestureView.this.invalidate();
                RelightingGestureView relightingGestureView = RelightingGestureView.this;
                relightingGestureView.notifyPositionChange(relightingGestureView.mCurrentX, RelightingGestureView.this.mCurrentY);
                return true;
            }
        };
        this.mDismissRunnable = new Runnable() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingGestureView.2
            @Override // java.lang.Runnable
            public void run() {
                RelightingGestureView.this.mCanDraw = false;
                RelightingGestureView.this.invalidate();
            }
        };
        init();
    }

    private void init() {
        this.mStrokePaint.setColor(getResources().getColor(R$color.relighting_adjust_stroke));
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setStrokeWidth(3.0f);
        this.mPaint.setColor(getResources().getColor(R$color.relighting_adjust_solid));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mRadius = getResources().getDimensionPixelSize(R$dimen.relighting_adjust_radius);
        this.mSphereRadius = getResources().getDimensionPixelSize(R$dimen.relighting_adjust_sphere_radius);
        this.mAvailableRect = new RectF();
        GestureDetector gestureDetector = new GestureDetector(getContext(), this.mGestureListener);
        this.mGestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mAvailableRect.isEmpty() || !this.mCanDraw) {
            return;
        }
        canvas.save();
        canvas.clipRect(this.mAvailableRect);
        canvas.rotate(this.mAngle, this.mOvalRectF.centerX(), this.mOvalRectF.centerY());
        canvas.drawOval(this.mOvalRectF, this.mPaint);
        canvas.drawOval(this.mOvalRectF, this.mStrokePaint);
        canvas.restore();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = this.mGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            this.mCanDraw = false;
            invalidate();
            notifyUp();
        }
        return zOnTouchEvent;
    }

    public void setPosition(float f, float f2, float f3, float f4) {
        this.mCenterX = f;
        this.mCenterY = f2;
        this.mCurrentX = f3;
        this.mCurrentY = f4;
        refreshPosition();
        if (this.mInitShow) {
            return;
        }
        this.mCanDraw = true;
        this.mInitShow = true;
        invalidate();
        postDelayed(this.mDismissRunnable, 1000L);
    }

    public void setImageDisplayRect(RectF rectF) {
        this.mAvailableRect.set(rectF);
        this.mMaxDrawRadius = (Math.min(this.mAvailableRect.width(), this.mAvailableRect.height()) * 0.7f) / 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshPosition() {
        if (getDistance(this.mCenterX, this.mCenterY, this.mCurrentX, this.mCurrentY) > this.mMaxDrawRadius) {
            double degrees = Math.toDegrees(Math.atan2(this.mCenterY - this.mCurrentY, this.mCenterX - this.mCurrentX));
            float[] fArr = this.mPoint;
            fArr[0] = this.mCenterX - this.mMaxDrawRadius;
            fArr[1] = this.mCenterY;
            this.mMatrix.reset();
            this.mMatrix.postRotate((float) degrees, this.mCenterX, this.mCenterY);
            this.mMatrix.mapPoints(this.mPoint);
            float[] fArr2 = this.mPoint;
            this.mCurrentX = fArr2[0];
            this.mCurrentY = fArr2[1];
        }
        float distance = getDistance(this.mCenterX, this.mCenterY, this.mCurrentX, this.mCurrentY);
        this.mAngle = getAngle(this.mCenterX, this.mCenterY, this.mCurrentX, this.mCurrentY);
        float f = this.mRadius;
        float f2 = f - ((distance / this.mSphereRadius) * f);
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        RectF rectF = this.mOvalRectF;
        float f3 = this.mCurrentX;
        float f4 = this.mCurrentY;
        rectF.set(f3 - f2, f4 - f, f3 + f2, f4 + f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPositionChange(float f, float f2) {
        OnPositionChangeListener onPositionChangeListener = this.mPositionChangeListener;
        if (onPositionChangeListener != null) {
            onPositionChangeListener.onPositionChange(f, f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDown() {
        OnPositionChangeListener onPositionChangeListener = this.mPositionChangeListener;
        if (onPositionChangeListener != null) {
            onPositionChangeListener.onDown();
        }
    }

    private void notifyUp() {
        OnPositionChangeListener onPositionChangeListener = this.mPositionChangeListener;
        if (onPositionChangeListener != null) {
            onPositionChangeListener.onUp();
        }
    }

    public void setPositionChangeListener(OnPositionChangeListener onPositionChangeListener) {
        this.mPositionChangeListener = onPositionChangeListener;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setAvailableArea(0.0f, 0.0f, i, i2);
    }

    public void setAvailableArea(float f, float f2, float f3, float f4) {
        this.mAvailableRect.set(f, f2, f3 + f, f4 + f2);
    }

    public static float getDistance(float f, float f2, float f3, float f4) {
        return (float) Math.hypot(f - f3, f2 - f4);
    }

    public static float getAngle(float f, float f2, float f3, float f4) {
        return (float) ((Math.toDegrees(Math.atan2(f2 - f4, f - f3)) + 360.0d) % 360.0d);
    }
}
