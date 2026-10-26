package com.miui.extraphoto.refocus.core.relighting;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.extraphoto.refocus.DualPhotoGestureFragment;

/* JADX INFO: loaded from: classes.dex */
public class RelightingGestureFragment extends DualPhotoGestureFragment<RelightingOperatingFragment> {
    private RelightingGestureView mGestureView;
    private float[] mPositionCenter = new float[2];
    private float[] mPositionCurrent = new float[2];
    private float[] mPoint = new float[2];
    private RectF mRectF = new RectF();
    private RelightingGestureView.OnPositionChangeListener mPositionChangeListener = new RelightingGestureView.OnPositionChangeListener() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingGestureFragment.1
        @Override // com.miui.extraphoto.refocus.core.relighting.RelightingGestureView.OnPositionChangeListener
        public void onDown() {
            ((RelightingOperatingFragment) ((DualPhotoGestureFragment) RelightingGestureFragment.this).mOperatingFragment).onGestureDown();
        }

        @Override // com.miui.extraphoto.refocus.core.relighting.RelightingGestureView.OnPositionChangeListener
        public void onUp() {
            ((RelightingOperatingFragment) ((DualPhotoGestureFragment) RelightingGestureFragment.this).mOperatingFragment).onGestureUp();
        }

        @Override // com.miui.extraphoto.refocus.core.relighting.RelightingGestureView.OnPositionChangeListener
        public void onPositionChange(float f, float f2) {
            RelightingGestureFragment.this.mPoint[0] = f;
            RelightingGestureFragment.this.mPoint[1] = f2;
            ((DualPhotoGestureFragment) RelightingGestureFragment.this).mCoordinateProvider.convertPointFromViewToBitmap(RelightingGestureFragment.this.mPoint);
            ((RelightingOperatingFragment) ((DualPhotoGestureFragment) RelightingGestureFragment.this).mOperatingFragment).setLightSource(RelightingGestureFragment.this.mPoint[0], RelightingGestureFragment.this.mPoint[1]);
        }
    };

    @Override // miuix.appcompat.app.Fragment, miuix.appcompat.app.IFragment
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new RelightingGestureView(getAppCompatActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        RelightingGestureView relightingGestureView = (RelightingGestureView) view;
        this.mGestureView = relightingGestureView;
        relightingGestureView.setVisibility(8);
        this.mGestureView.setPositionChangeListener(this.mPositionChangeListener);
    }

    public void setPosition(float f, float f2, float f3, float f4) {
        float[] fArr = this.mPositionCenter;
        fArr[0] = f;
        fArr[1] = f2;
        float[] fArr2 = this.mPositionCurrent;
        fArr2[0] = f3;
        fArr2[1] = f4;
        this.mCoordinateProvider.convertPointFromBitmapToView(fArr);
        this.mCoordinateProvider.convertPointFromBitmapToView(this.mPositionCurrent);
        this.mCoordinateProvider.getImageDisplayRect(this.mRectF);
        this.mGestureView.setImageDisplayRect(this.mRectF);
        RelightingGestureView relightingGestureView = this.mGestureView;
        float[] fArr3 = this.mPositionCenter;
        float f5 = fArr3[0];
        float f6 = fArr3[1];
        float[] fArr4 = this.mPositionCurrent;
        relightingGestureView.setPosition(f5, f6, fArr4[0], fArr4[1]);
    }

    public void notifyGestureVisibility(boolean z) {
        this.mGestureView.setVisibility(z ? 0 : 8);
    }
}
