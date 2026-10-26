package com.miui.extraphoto.refocus.core.relighting;

import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.model.NativeData;
import com.miui.extraphoto.refocus.model.NativeImage;

/* JADX INFO: loaded from: classes.dex */
class MiBrainRelightingProcessorSoftEncrypted extends MiBrainRelightingProcessor {
    private NativeData mDepthData;

    MiBrainRelightingProcessorSoftEncrypted() {
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor
    protected NativeImage getBlurImage(boolean z, PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        NativeImage originImage = dualPhotoNativeContext.getOriginImage();
        NativeImage nativeImage = new NativeImage(originImage);
        DualPhotoJni.processMiRefocus(nativeImage.pointer, originImage.pointer, dualPhotoNativeContext.getDepthData().pointer, photoInfoProvider.getBlurLevel(), photoInfoProvider.getFilterId());
        return nativeImage;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor
    protected NativeData getDepthData(DualPhotoNativeContext dualPhotoNativeContext) {
        if (this.mDepthData == null) {
            NativeData nativeData = new NativeData(dualPhotoNativeContext.getDepthData());
            this.mDepthData = nativeData;
            DualPhotoJni.processMiRawDepth(nativeData.pointer);
        }
        return this.mDepthData;
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void changeToDefault(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        DualPhotoJni.copyImage(dualPhotoNativeContext.getOriginImage().pointer, dualPhotoNativeContext.getEffectImage().pointer);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.MiBrainRelightingProcessor, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public void release() {
        super.release();
        NativeData nativeData = this.mDepthData;
        if (nativeData != null) {
            nativeData.release();
            this.mDepthData = null;
        }
    }
}
