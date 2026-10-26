package com.miui.extraphoto.docphoto.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import com.miui.camerainfra.exif.ExifInterface;
import com.miui.extraphoto.common.utils.ExifUtil;
import com.miui.extraphoto.common.utils.IOUtils;
import com.miui.extraphoto.common.utils.ImageUtils;
import com.miui.extraphoto.common.utils.InSampleInfo;
import com.miui.extraphoto.common.utils.StaticContext;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class PhotoMetaData {
    Bitmap mBitmapDelegate;
    int mDegree;
    int mDelegateInSampleSize;
    ExifInterface mExif;
    int mHeight;
    int mOrientation = 1;
    int mWidth;

    boolean extract(Uri uri) throws Throwable {
        InputStream inputStream = null;
        try {
            try {
                InputStream inputStreamOpenInputStream = openInputStream(uri);
                try {
                    ExifInterface exifInterface = ExifUtil.getExifInterface(inputStreamOpenInputStream);
                    if (exifInterface == null) {
                        Log.d("PhotoMetaData", "exif info is null");
                        IOUtils.close("PhotoMetaData", inputStreamOpenInputStream);
                        return false;
                    }
                    this.mExif = exifInterface;
                    int orientation = ExifUtil.getOrientation(exifInterface);
                    this.mOrientation = orientation;
                    this.mDegree = ImageUtils.getDegreeByOrientation(orientation);
                    IOUtils.close("PhotoMetaData", inputStreamOpenInputStream);
                    InputStream inputStreamOpenInputStream2 = openInputStream(uri);
                    InSampleInfo inSampleInfoComputeInSampleInfo = ImageUtils.computeInSampleInfo(inputStreamOpenInputStream2);
                    this.mDelegateInSampleSize = inSampleInfoComputeInSampleInfo.inSampleSize;
                    this.mWidth = inSampleInfoComputeInSampleInfo.outWidth;
                    this.mHeight = inSampleInfoComputeInSampleInfo.outHeight;
                    IOUtils.close("PhotoMetaData", inputStreamOpenInputStream2);
                    InputStream inputStreamOpenInputStream3 = openInputStream(uri);
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = this.mDelegateInSampleSize;
                        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream3, null, options);
                        this.mBitmapDelegate = bitmapDecodeStream;
                        this.mBitmapDelegate = ImageUtils.generateBitmapWithOrientation(bitmapDecodeStream, this.mOrientation);
                        IOUtils.close("PhotoMetaData", inputStreamOpenInputStream3);
                        IOUtils.close("PhotoMetaData", inputStreamOpenInputStream3);
                        return true;
                    } catch (FileNotFoundException e) {
                        e = e;
                        inputStream = inputStreamOpenInputStream3;
                        e.printStackTrace();
                        IOUtils.close("PhotoMetaData", inputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStreamOpenInputStream3;
                        IOUtils.close("PhotoMetaData", inputStream);
                        throw th;
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    inputStream = inputStreamOpenInputStream;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = inputStreamOpenInputStream;
                }
            } catch (FileNotFoundException e3) {
                e = e3;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private InputStream openInputStream(Uri uri) throws FileNotFoundException {
        return StaticContext.sGetAndroidContext().getContentResolver().openInputStream(uri);
    }
}
