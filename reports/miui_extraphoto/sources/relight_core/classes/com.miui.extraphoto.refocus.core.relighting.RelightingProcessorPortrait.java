package com.miui.extraphoto.refocus.core.relighting;

import android.graphics.Bitmap;
import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.model.NativeImage;

/* JADX INFO: loaded from: classes.dex */
class RelightingProcessorPortrait extends ArcSoftRelightingProcessor {
    private NativeImage mBlurImage = null;

    RelightingProcessorPortrait() {
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public boolean initProcessor(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        Bitmap bitmapDecodeOriginBitmap = photoInfoProvider.decodeOriginBitmap(null);
        NativeImage nativeImage = new NativeImage(bitmapDecodeOriginBitmap);
        bitmapDecodeOriginBitmap.recycle();
        NativeImage nativeImage2 = new NativeImage(nativeImage);
        DualPhotoJni.processRefocus(nativeImage2.pointer, nativeImage.pointer, photoInfoProvider.getFocusX(), photoInfoProvider.getFocusY(), photoInfoProvider.getBlurLevel(), photoInfoProvider.getShineLevel(), photoInfoProvider.getShineThreshold(), photoInfoProvider.getFilterId(), dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, 0L, 0);
        this.mRelightingHandle = DualPhotoJni.initRelightingEffect(photoInfoProvider.getDepthDataDegree(), nativeImage2.pointer, nativeImage.pointer, dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, this.mFacePoint);
        nativeImage2.release();
        nativeImage.release();
        if (this.mFacePoint[0] != 0) {
            return false;
        }
        initBlurImage(photoInfoProvider, dualPhotoNativeContext);
        return true;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void changeToDefault(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        initBlurImage(photoInfoProvider, dualPhotoNativeContext);
        DualPhotoJni.copyImage(this.mBlurImage.pointer, dualPhotoNativeContext.getEffectImage().pointer);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void enableRelightingEffect(int i, DualPhotoNativeContext dualPhotoNativeContext) {
        long j = this.mRelightingHandle;
        NativeImage nativeImage = this.mBlurImage;
        DualPhotoJni.enableRelightingEffect(j, i, nativeImage == null ? 0L : nativeImage.pointer, dualPhotoNativeContext.getEffectImage().pointer, dualPhotoNativeContext.getOriginImage().pointer, dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, this.mLightingSource);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void doSave(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext, int i) {
        NativeImage nativeImage = new NativeImage(dualPhotoNativeContext.getOriginImage());
        DualPhotoJni.processRefocus(nativeImage.pointer, dualPhotoNativeContext.getOriginImage().pointer, photoInfoProvider.getFocusX(), photoInfoProvider.getFocusY(), photoInfoProvider.getBlurLevel(), photoInfoProvider.getShineLevel(), photoInfoProvider.getShineThreshold(), photoInfoProvider.getFilterId(), dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, 0L, 0);
        long j = this.mRelightingHandle;
        int[] iArr = this.mLightingSource;
        DualPhotoJni.setRelightingLightSource(j, iArr[0], iArr[1], photoInfoProvider.getOriginBitmapWidth(), photoInfoProvider.getOriginBitmapHeight());
        DualPhotoJni.enableRelightingEffect(this.mRelightingHandle, i, nativeImage.pointer, dualPhotoNativeContext.getEffectImage().pointer, dualPhotoNativeContext.getOriginImage().pointer, dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, this.mLightingSource);
        nativeImage.release();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor
    public void onRelease() {
        NativeImage nativeImage = this.mBlurImage;
        if (nativeImage != null) {
            nativeImage.release();
            this.mBlurImage = null;
        }
    }

    private void initBlurImage(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        if (this.mBlurImage == null) {
            NativeImage nativeImage = new NativeImage(dualPhotoNativeContext.getEffectImage());
            this.mBlurImage = nativeImage;
            DualPhotoJni.processRefocus(nativeImage.pointer, dualPhotoNativeContext.getOriginImage().pointer, dualPhotoNativeContext.getCurrentFocusPointX(), dualPhotoNativeContext.getCurrentFocusPointY(), dualPhotoNativeContext.getBlurLevel(), photoInfoProvider.getShineLevel(), photoInfoProvider.getShineThreshold(), photoInfoProvider.getFilterId(), dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, 0L, 0);
        }
    }
}
