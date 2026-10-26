package com.miui.extraphoto.refocus.core.relighting;

import android.graphics.Bitmap;
import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.model.NativeData;
import com.miui.extraphoto.refocus.model.NativeImage;

/* JADX INFO: loaded from: classes.dex */
public class RelightingProcessorSoftPortrait extends ArcSoftRelightingProcessor {
    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor
    public void onRelease() {
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getFaceX() {
        return super.getFaceX();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getFaceY() {
        return super.getFaceY();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getInitResult() {
        return super.getInitResult();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getLightingSourceX() {
        return super.getLightingSourceX();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getLightingSourceY() {
        return super.getLightingSourceY();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ void setLightSource(PhotoInfoProvider photoInfoProvider, int i, int i2) {
        super.setLightSource(photoInfoProvider, i, i2);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ boolean supportAdjustLightSource() {
        return super.supportAdjustLightSource();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public boolean initProcessor(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        NativeData depthData = getDepthData(dualPhotoNativeContext);
        Bitmap bitmapDecodeOriginBitmap = photoInfoProvider.decodeOriginBitmap(null);
        NativeImage nativeImage = new NativeImage(bitmapDecodeOriginBitmap);
        bitmapDecodeOriginBitmap.recycle();
        this.mRelightingHandle = DualPhotoJni.initRelightingEffect(photoInfoProvider.getDepthDataDegree(), 0L, nativeImage.pointer, depthData.pointer, depthData.length, this.mFacePoint);
        nativeImage.release();
        return this.mFacePoint[0] == 0;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void changeToDefault(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        DualPhotoJni.copyImage(dualPhotoNativeContext.getOriginImage().pointer, dualPhotoNativeContext.getEffectImage().pointer);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void enableRelightingEffect(int i, DualPhotoNativeContext dualPhotoNativeContext) {
        NativeData depthData = getDepthData(dualPhotoNativeContext);
        if (dualPhotoNativeContext.getEffectImage() == null || dualPhotoNativeContext.getOriginImage() == null) {
            return;
        }
        DualPhotoJni.enableRelightingEffect(this.mRelightingHandle, i, 0L, dualPhotoNativeContext.getEffectImage().pointer, dualPhotoNativeContext.getOriginImage().pointer, depthData.pointer, depthData.length, this.mLightingSource);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void doSave(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext, int i) {
        long j = this.mRelightingHandle;
        int[] iArr = this.mLightingSource;
        DualPhotoJni.setRelightingLightSource(j, iArr[0], iArr[1], photoInfoProvider.getOriginBitmapWidth(), photoInfoProvider.getOriginBitmapHeight());
        NativeData depthData = getDepthData(dualPhotoNativeContext);
        if (dualPhotoNativeContext.getEffectImage() == null || dualPhotoNativeContext.getOriginImage() == null) {
            return;
        }
        DualPhotoJni.enableRelightingEffect(this.mRelightingHandle, i, 0L, dualPhotoNativeContext.getEffectImage().pointer, dualPhotoNativeContext.getOriginImage().pointer, depthData.pointer, depthData.length, this.mLightingSource);
    }

    protected NativeData getDepthData(DualPhotoNativeContext dualPhotoNativeContext) {
        return dualPhotoNativeContext.getDepthData();
    }
}
