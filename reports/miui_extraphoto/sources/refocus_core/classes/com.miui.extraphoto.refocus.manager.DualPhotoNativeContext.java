package com.miui.extraphoto.refocus.manager;

import android.graphics.Bitmap;
import android.util.Log;
import com.miui.extraphoto.refocus.IDisplay;
import com.miui.extraphoto.refocus.PhotoInfoProvider;
import com.miui.extraphoto.refocus.model.NativeData;
import com.miui.extraphoto.refocus.model.NativeImage;

/* JADX INFO: loaded from: classes.dex */
public class DualPhotoNativeContext {
    private int mBlurLevel;
    private int mCurrentFocusPointX;
    private int mCurrentFocusPointY;
    private NativeData mDepthData;
    private NativeImage mEffectImage;
    private MeituDataManager mMeituDataManager;
    private NativeImage mOriginImage;
    private PhotoInfoProvider mPhotoInfoProvider;
    private float mScale = 1.0f;
    private boolean mCanRefreshDisplay = true;

    public void initNative(PhotoInfoProvider photoInfoProvider) {
        NativeData nativeData = new NativeData(photoInfoProvider.getDepthData());
        this.mDepthData = nativeData;
        Log.d("DualPhotoNativeContext", String.format("new depth data : %s", nativeData));
        this.mPhotoInfoProvider = photoInfoProvider;
        generateNativeImage(photoInfoProvider.getProcessOriginBitmap(), photoInfoProvider);
        MeituDataManager meituDataManager = photoInfoProvider.getMeituDataManager();
        this.mMeituDataManager = meituDataManager;
        if (meituDataManager != null) {
            meituDataManager.initMeituEffect(photoInfoProvider.getDepthDataOrientation(), photoInfoProvider.getOriginBitmapWidth(), photoInfoProvider.getOriginBitmapHeight(), photoInfoProvider.getProcessWidth(), photoInfoProvider.getProcessHeight());
        }
    }

    public void generateNativeImage(Bitmap bitmap, PhotoInfoProvider photoInfoProvider) {
        releaseImage();
        this.mScale = photoInfoProvider.getOriginBitmapWidth() / bitmap.getWidth();
        this.mCurrentFocusPointX = Math.round(photoInfoProvider.getFocusX() / this.mScale);
        this.mCurrentFocusPointY = Math.round(photoInfoProvider.getFocusY() / this.mScale);
        this.mBlurLevel = Math.round(photoInfoProvider.getBlurLevel() / this.mScale);
        NativeImage nativeImage = new NativeImage(bitmap);
        this.mOriginImage = nativeImage;
        this.mEffectImage = new NativeImage(nativeImage);
    }

    private void releaseImage() {
        if (this.mOriginImage != null) {
            Log.d("DualPhotoNativeContext", "release origin image");
            this.mOriginImage.release();
            this.mOriginImage = null;
        }
        if (this.mEffectImage != null) {
            Log.d("DualPhotoNativeContext", "release buffer image");
            this.mEffectImage.release();
            this.mEffectImage = null;
        }
    }

    public void release() {
        releaseImage();
        NativeData nativeData = this.mDepthData;
        if (nativeData != null) {
            Log.d("DualPhotoNativeContext", String.format("release depth data : %s", nativeData));
            this.mDepthData.release();
            this.mDepthData = null;
        }
        MeituDataManager meituDataManager = this.mMeituDataManager;
        if (meituDataManager != null) {
            meituDataManager.releaseMeituEffect();
            this.mMeituDataManager = null;
        }
    }

    public void configDisplayData(IDisplay iDisplay) {
        if (this.mCanRefreshDisplay) {
            configYuvData(iDisplay.getYBuffer(), iDisplay.getUBuffer(), iDisplay.getVBuffer());
        }
    }

    public void configYuvData(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        NativeImage nativeImage = this.mEffectImage;
        if (nativeImage == null) {
            return;
        }
        DualPhotoJni.configYUVData(nativeImage.pointer, bArr, bArr2, bArr3);
        MeituDataManager meituDataManager = this.mMeituDataManager;
        if (meituDataManager != null) {
            meituDataManager.processPreviewEffect(bArr, bArr2, bArr3);
        }
        if (this.mPhotoInfoProvider.isMiMovie()) {
            NativeImage nativeImage2 = this.mOriginImage;
            DualPhotoMiMovieUtils.fillBlackBorderForYuvData(bArr, bArr2, bArr3, nativeImage2.width, nativeImage2.height);
        }
    }

    public NativeImage getOriginImage() {
        return this.mOriginImage;
    }

    public NativeImage getEffectImage() {
        return this.mEffectImage;
    }

    public NativeData getDepthData() {
        return this.mDepthData;
    }

    public int getCurrentFocusPointX() {
        return this.mCurrentFocusPointX;
    }

    public int getCurrentFocusPointY() {
        return this.mCurrentFocusPointY;
    }

    public int getBlurLevel() {
        return this.mBlurLevel;
    }

    public float getScale() {
        return this.mScale;
    }

    public void setCanRefreshDisplay(boolean z) {
        this.mCanRefreshDisplay = z;
    }
}
