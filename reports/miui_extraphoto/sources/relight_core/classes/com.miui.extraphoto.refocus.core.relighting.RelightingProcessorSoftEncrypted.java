package com.miui.extraphoto.refocus.core.relighting;

import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.model.NativeData;

/* JADX INFO: loaded from: classes.dex */
public class RelightingProcessorSoftEncrypted extends RelightingProcessorSoftPortrait {
    private NativeData mDepthData;

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessorSoftPortrait, com.miui.extraphoto.refocus.core.relighting.RelightingProcessor
    public boolean initProcessor(PhotoInfoProvider photoInfoProvider, DualPhotoNativeContext dualPhotoNativeContext) {
        NativeData nativeData = new NativeData(dualPhotoNativeContext.getDepthData());
        this.mDepthData = nativeData;
        DualPhotoJni.processMiRawDepth(nativeData.pointer);
        return super.initProcessor(photoInfoProvider, dualPhotoNativeContext);
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessorSoftPortrait, com.miui.extraphoto.refocus.core.relighting.ArcSoftRelightingProcessor
    public void onRelease() {
        super.onRelease();
        NativeData nativeData = this.mDepthData;
        if (nativeData != null) {
            nativeData.release();
            this.mDepthData = null;
        }
    }

    @Override // com.miui.extraphoto.refocus.core.relighting.RelightingProcessorSoftPortrait
    protected NativeData getDepthData(DualPhotoNativeContext dualPhotoNativeContext) {
        return this.mDepthData;
    }
}
