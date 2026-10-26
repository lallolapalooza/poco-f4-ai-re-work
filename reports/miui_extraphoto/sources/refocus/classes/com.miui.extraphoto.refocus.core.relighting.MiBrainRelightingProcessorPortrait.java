package com.miui.extraphoto.refocus.core.relighting;

import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.model.NativeImage;

/* JADX INFO: loaded from: classes.dex */
public class MiBrainRelightingProcessorPortrait extends MiBrainRelightingProcessor {
    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ void changeToDefault(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        super.changeToDefault(photoInfoProvider, dualPhotoNativeContext);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ void doSave(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext, int i) {
        super.doSave(photoInfoProvider, dualPhotoNativeContext, i);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ void enableRelightingEffect(int i, DualPhotoNativeContext dualPhotoNativeContext) {
        super.enableRelightingEffect(i, dualPhotoNativeContext);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getFaceX() {
        return super.getFaceX();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getFaceY() {
        return super.getFaceY();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getInitResult() {
        return super.getInitResult();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getLightingSourceX() {
        return super.getLightingSourceX();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ int getLightingSourceY() {
        return super.getLightingSourceY();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ boolean initProcessor(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        return super.initProcessor(photoInfoProvider, dualPhotoNativeContext);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ void setLightSource(PhotoInfoProvider photoInfoProvider, int i, int i2) {
        super.setLightSource(photoInfoProvider, i, i2);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public /* bridge */ /* synthetic */ boolean supportAdjustLightSource() {
        return super.supportAdjustLightSource();
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor
    protected NativeImage getBlurImage(boolean z, PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        NativeImage nativeImage = new NativeImage(dualPhotoNativeContext.getEffectImage());
        DualPhotoJni.processRefocus(nativeImage.pointer, dualPhotoNativeContext.getOriginImage().pointer, dualPhotoNativeContext.getCurrentFocusPointX(), dualPhotoNativeContext.getCurrentFocusPointY(), dualPhotoNativeContext.getBlurLevel(), photoInfoProvider.getShineLevel(), photoInfoProvider.getShineThreshold(), photoInfoProvider.getFilterId(), dualPhotoNativeContext.getDepthData().pointer, dualPhotoNativeContext.getDepthData().length, 0L, 0);
        return nativeImage;
    }
}
