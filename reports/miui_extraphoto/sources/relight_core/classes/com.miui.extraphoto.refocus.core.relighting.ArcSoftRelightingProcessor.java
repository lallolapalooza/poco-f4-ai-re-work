package com.miui.extraphoto.refocus.core.relighting;

import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;

/* JADX INFO: loaded from: classes.dex */
abstract class ArcSoftRelightingProcessor implements RelightingProcessor {
    protected int[] mFacePoint = new int[4];
    protected int[] mLightingSource = new int[2];
    protected long mRelightingHandle = 0;

    protected abstract void onRelease();

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public boolean supportAdjustLightSource() {
        return true;
    }

    ArcSoftRelightingProcessor() {
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void setLightSource(PhotoInfoProvider photoInfoProvider, int i, int i2) {
        DualPhotoJni.setRelightingLightSource(this.mRelightingHandle, i, i2, photoInfoProvider.getOriginBitmapWidth(), photoInfoProvider.getOriginBitmapHeight());
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getFaceX() {
        return this.mFacePoint[1];
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getFaceY() {
        return this.mFacePoint[2];
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getLightingSourceX() {
        return this.mLightingSource[0];
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getLightingSourceY() {
        return this.mLightingSource[1];
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getInitResult() {
        return this.mFacePoint[0];
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public final void release() {
        long j = this.mRelightingHandle;
        if (j != 0) {
            DualPhotoJni.releaseRelightingHandle(j);
            this.mRelightingHandle = 0L;
        }
        onRelease();
    }
}
