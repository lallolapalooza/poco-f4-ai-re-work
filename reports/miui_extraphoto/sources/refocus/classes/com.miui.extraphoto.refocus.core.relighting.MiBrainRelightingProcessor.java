package com.miui.extraphoto.refocus.core.relighting;

import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.manager.MiDualPhotoJni;
import com.miui.extraphoto.refocus.model.NativeData;
import com.miui.extraphoto.refocus.model.NativeImage;

/* JADX INFO: loaded from: classes.dex */
class MiBrainRelightingProcessor implements RelightingProcessor {
    private NativeImage mBlurImage = null;
    private final int[] mFacePoint = new int[4];
    private final int[] mPointCache = new int[2];
    private long mRelightingHandle;

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getFaceX() {
        return 0;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getFaceY() {
        return 0;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getInitResult() {
        return 0;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getLightingSourceX() {
        return 0;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public int getLightingSourceY() {
        return 0;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void setLightSource(PhotoInfoProvider photoInfoProvider, int i, int i2) {
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public boolean supportAdjustLightSource() {
        return false;
    }

    MiBrainRelightingProcessor() {
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public boolean initProcessor(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        this.mRelightingHandle = MiDualPhotoJni.initBrainRelightingEffect(photoInfoProvider.getDepthDataDegree(), dualPhotoNativeContext.getOriginImage().pointer, getDepthData(dualPhotoNativeContext).pointer, getDepthData(dualPhotoNativeContext).length, this.mFacePoint);
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
        NativeImage originImage = dualPhotoNativeContext.getOriginImage();
        long j = this.mRelightingHandle;
        int i2 = originImage.width;
        int i3 = originImage.height;
        MiDualPhotoJni.setBrainRelightingLightSource(j, i2 / 2, i3 / 2, i2, i3);
        long j2 = this.mRelightingHandle;
        NativeImage nativeImage = this.mBlurImage;
        MiDualPhotoJni.enableBrainRelightingEffect(j2, i, nativeImage == null ? 0L : nativeImage.pointer, dualPhotoNativeContext.getEffectImage().pointer, originImage.pointer, getDepthData(dualPhotoNativeContext).pointer, getDepthData(dualPhotoNativeContext).length, this.mPointCache);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void doSave(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext, int i) {
        NativeImage originImage = dualPhotoNativeContext.getOriginImage();
        NativeImage blurImage = getBlurImage(false, photoInfoProvider, dualPhotoNativeContext);
        long j = this.mRelightingHandle;
        if (j != 0) {
            MiDualPhotoJni.releaseBrainRelightingHandle(j);
            this.mRelightingHandle = 0L;
        }
        long jInitBrainRelightingEffect = MiDualPhotoJni.initBrainRelightingEffect(photoInfoProvider.getDepthDataDegree(), originImage.pointer, getDepthData(dualPhotoNativeContext).pointer, getDepthData(dualPhotoNativeContext).length, this.mFacePoint);
        int i2 = originImage.width;
        int i3 = originImage.height;
        MiDualPhotoJni.setBrainRelightingLightSource(jInitBrainRelightingEffect, i2 / 2, i3 / 2, i2, i3);
        MiDualPhotoJni.enableBrainRelightingEffect(jInitBrainRelightingEffect, i, blurImage.pointer, dualPhotoNativeContext.getEffectImage().pointer, dualPhotoNativeContext.getOriginImage().pointer, getDepthData(dualPhotoNativeContext).pointer, getDepthData(dualPhotoNativeContext).length, this.mPointCache);
        MiDualPhotoJni.releaseBrainRelightingHandle(jInitBrainRelightingEffect);
        blurImage.release();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void release() {
        long j = this.mRelightingHandle;
        if (j != 0) {
            MiDualPhotoJni.releaseBrainRelightingHandle(j);
            this.mRelightingHandle = 0L;
        }
        NativeImage nativeImage = this.mBlurImage;
        if (nativeImage != null) {
            nativeImage.release();
            this.mBlurImage = null;
        }
    }

    private void initBlurImage(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        if (this.mBlurImage == null) {
            this.mBlurImage = getBlurImage(true, photoInfoProvider, dualPhotoNativeContext);
        }
    }

    protected NativeImage getBlurImage(boolean z, PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        NativeImage originImage = dualPhotoNativeContext.getOriginImage();
        NativeImage nativeImage = new NativeImage(originImage);
        MiDualPhotoJni.processMiCameraRefocus(photoInfoProvider.getProfile(), z, nativeImage.width, nativeImage.height, nativeImage.pointer, originImage.pointer, 0L, 0L, dualPhotoNativeContext.getCurrentFocusPointX(), dualPhotoNativeContext.getCurrentFocusPointY(), photoInfoProvider.getAfCode(), photoInfoProvider.getBlurLevel(), photoInfoProvider.getISO(), photoInfoProvider.getTOF(), dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, false);
        return nativeImage;
    }

    protected NativeData getDepthData(DualPhotoNativeContext dualPhotoNativeContext) {
        return dualPhotoNativeContext.getDepthData();
    }
}
