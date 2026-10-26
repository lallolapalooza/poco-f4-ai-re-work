package com.miui.extraphoto.refocus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import com.miui.camerainfra.exif.ExifInterface;
import com.miui.extraphoto.common.feature.watermark.WaterMarkManager;
import com.miui.extraphoto.refocus.manager.MeituDataManager;

/* JADX INFO: loaded from: classes.dex */
public interface PhotoInfoProvider {
    Bitmap decodeOriginBitmap(BitmapFactory.Options options);

    int getAfCode();

    int getBlurLevel();

    int getBokehMappingVersion();

    ColorSpace getColorSpace();

    int getDegree();

    byte[] getDepthData();

    int getDepthDataDegree();

    int getDepthDataOrientation();

    int getDepthHeight();

    int getDepthWidth();

    int getExifHeight();

    int getExifWidth();

    int getFilterId();

    int getFocusX();

    int getFocusY();

    int getISO();

    MeituDataManager getMeituDataManager();

    int getOrientation();

    int getOriginBitmapHeight();

    int getOriginBitmapWidth();

    ExifInterface getOriginExif();

    String getPortraitType();

    int getProcessHeight();

    Bitmap getProcessOriginBitmap();

    int getProcessWidth();

    int getProfile();

    int getRelightType();

    int getRelightingIndex();

    int getShineLevel();

    int getShineThreshold();

    String getSourcePath();

    int getTOF();

    WaterMarkManager getWaterMarkManager();

    boolean isMiMovie();

    boolean isMirror();
}
