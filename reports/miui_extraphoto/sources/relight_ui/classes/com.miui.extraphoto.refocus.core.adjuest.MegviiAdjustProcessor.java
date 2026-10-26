package com.miui.extraphoto.refocus.core.adjuest;

import android.util.Log;
import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.manager.DualPhotoNativeContext;
import com.miui.extraphoto.refocus.manager.MegviiDualPhotoJni;
import com.miui.extraphoto.refocus.model.NativeImage;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MegviiAdjustProcessor extends AdjustProcessor {
    public static int ALGORITHM_NAME_DEFAULT = 0;
    public static int ALGORITHM_NAME_MEGVII_PORTRAIT_GOOGLE = 1;
    private long mHandler;
    private static final float[] F_VALUE = {1.0f, 1.1f, 1.2f, 1.4f, 1.6f, 1.8f, 2.0f, 2.2f, 2.5f, 2.8f, 3.2f, 3.5f, 4.0f, 4.5f, 5.0f, 5.6f, 6.3f, 7.1f, 8.0f, 9.0f, 10.0f, 11.0f, 13.0f, 14.0f, 16.0f};
    private static final float[] ALG_F_VALUE = {1.0f, 1.0f, 1.4f, 1.4f, 1.4f, 2.0f, 2.0f, 2.0f, 2.8f, 2.8f, 2.8f, 4.0f, 4.0f, 4.0f, 5.6f, 5.6f, 5.6f, 8.0f, 8.0f, 8.0f, 11.0f, 11.0f, 11.0f, 16.0f, 16.0f};

    MegviiAdjustProcessor(DualPhotoNativeContext dualPhotoNativeContext, PhotoInfoProvider photoInfoProvider) {
        super(dualPhotoNativeContext, photoInfoProvider);
    }

    @Override // com.miui.extraphoto.refocus.core.adjuest.AdjustProcessor
    public String getDisplayText(int i) {
        return String.valueOf(F_VALUE[i]);
    }

    @Override // com.miui.extraphoto.refocus.core.adjuest.AdjustProcessor
    public String generateStaticString(AdjustOperatingFragment.RefocusData refocusData) {
        return String.format(Locale.US, "%1.2f", Float.valueOf(F_VALUE[refocusData.currentIndex]));
    }

    @Override // com.miui.extraphoto.refocus.core.adjuest.AdjustProcessor
    public void onExtractInitData(AdjustOperatingFragment.RefocusData refocusData) {
        float originBitmapWidth = this.mPhotoInfoProvider.getOriginBitmapWidth() / this.mPhotoInfoProvider.getProcessWidth();
        refocusData.focusX = (int) (this.mPhotoInfoProvider.getFocusX() / originBitmapWidth);
        refocusData.focusY = (int) (this.mPhotoInfoProvider.getFocusY() / originBitmapWidth);
        int blurLevel = this.mPhotoInfoProvider.getBlurLevel();
        int iFindFValueIndex = findFValueIndex(blurLevel);
        float[] fArr = F_VALUE;
        Log.d("MegviiAdjustProcessor", String.format("blur level %d, fvalue %f,alg fvalue %f", Integer.valueOf(blurLevel), Float.valueOf(fArr[iFindFValueIndex]), Float.valueOf(ALG_F_VALUE[iFindFValueIndex])));
        if (iFindFValueIndex < 0) {
            iFindFValueIndex = 0;
        }
        refocusData.currentIndex = iFindFValueIndex;
        refocusData.count = fArr.length;
    }

    public static int findFValueIndex(int i) {
        float f = i / 10.0f;
        int i2 = 0;
        while (true) {
            float[] fArr = F_VALUE;
            if (i2 >= fArr.length) {
                return -1;
            }
            if (Float.compare(fArr[i2], f) == 0) {
                return i2;
            }
            i2++;
        }
    }

    @Override // com.miui.extraphoto.refocus.core.adjuest.AdjustProcessor
    protected void prepareForExport(AdjustOperatingFragment.RefocusData refocusData) {
        float originBitmapWidth = this.mPhotoInfoProvider.getOriginBitmapWidth() / this.mPhotoInfoProvider.getProcessWidth();
        refocusData.focusX = Math.round(refocusData.focusX * originBitmapWidth);
        refocusData.focusY = Math.round(refocusData.focusY * originBitmapWidth);
    }

    @Override // com.miui.extraphoto.refocus.core.adjuest.AdjustProcessor
    public void processRefocus(NativeImage nativeImage, NativeImage nativeImage2, AdjustOperatingFragment.RefocusData refocusData) {
        if (nativeImage == null || nativeImage2 == null || this.mDualPhotoNativeContext.getDepthData() == null) {
            return;
        }
        float fAlgFValue = algFValue(refocusData.currentIndex);
        if (this.mHandler == 0) {
            this.mHandler = MegviiDualPhotoJni.initRefocus();
        }
        MegviiDualPhotoJni.processRefocusWidthHandle(this.mHandler, nativeImage.pointer, nativeImage2.pointer, this.mDualPhotoNativeContext.getDepthData().pointer, this.mDualPhotoNativeContext.getDepthData().length, refocusData.focusX, refocusData.focusY, fAlgFValue, this.mPhotoInfoProvider.getDepthWidth(), this.mPhotoInfoProvider.getDepthHeight(), getPortraitType(this.mPhotoInfoProvider.getPortraitType()));
    }

    @Override // com.miui.extraphoto.refocus.core.adjuest.AdjustProcessor
    public void processRefocusOnSave(NativeImage nativeImage, NativeImage nativeImage2, AdjustOperatingFragment.RefocusData refocusData) {
        long j = this.mHandler;
        if (j != 0) {
            MegviiDualPhotoJni.releaseRefocus(j);
            this.mHandler = 0L;
        }
        MegviiDualPhotoJni.processRefocus(nativeImage.pointer, nativeImage2.pointer, this.mDualPhotoNativeContext.getDepthData().pointer, this.mDualPhotoNativeContext.getDepthData().length, refocusData.focusX, refocusData.focusY, algFValue(refocusData.currentIndex), this.mPhotoInfoProvider.getDepthWidth(), this.mPhotoInfoProvider.getDepthHeight(), getPortraitType(this.mPhotoInfoProvider.getPortraitType()));
    }

    @Override // com.miui.extraphoto.refocus.core.adjuest.AdjustProcessor
    public void release() {
        super.release();
        long j = this.mHandler;
        if (j != 0) {
            MegviiDualPhotoJni.releaseRefocus(j);
            this.mHandler = 0L;
        }
    }

    public static float algFValue(int i) {
        return ALG_F_VALUE[i];
    }

    public static int getPortraitType(String str) {
        str.hashCode();
        if (str.equals("megvii_portrait_google")) {
            return ALGORITHM_NAME_MEGVII_PORTRAIT_GOOGLE;
        }
        return ALGORITHM_NAME_DEFAULT;
    }
}
