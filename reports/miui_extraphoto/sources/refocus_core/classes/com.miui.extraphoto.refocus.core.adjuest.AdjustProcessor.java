package com.miui.extraphoto.refocus.core.adjuest;

import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.model.NativeImage;

/* JADX INFO: loaded from: classes.dex */
public abstract class AdjustProcessor {
    protected DualPhotoNativeContext mDualPhotoNativeContext;
    private boolean mHasData = false;
    protected PhotoInfoProvider mPhotoInfoProvider;

    public abstract String generateStaticString(AdjustOperatingFragment.RefocusData refocusData);

    public abstract String getDisplayText(int i);

    protected abstract void onExtractInitData(AdjustOperatingFragment.RefocusData refocusData);

    protected abstract void prepareForExport(AdjustOperatingFragment.RefocusData refocusData);

    public abstract void processRefocus(NativeImage nativeImage, NativeImage nativeImage2, AdjustOperatingFragment.RefocusData refocusData);

    public abstract void processRefocusOnSave(NativeImage nativeImage, NativeImage nativeImage2, AdjustOperatingFragment.RefocusData refocusData);

    public void release() {
    }

    public AdjustProcessor(DualPhotoNativeContext dualPhotoNativeContext, PhotoInfoProvider photoInfoProvider) {
        this.mDualPhotoNativeContext = dualPhotoNativeContext;
        this.mPhotoInfoProvider = photoInfoProvider;
    }

    public final void extractInitData(AdjustOperatingFragment.RefocusData refocusData) {
        onExtractInitData(refocusData);
        this.mHasData = true;
    }

    public boolean isHasData() {
        return this.mHasData;
    }

    static int findIndex(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }
}
