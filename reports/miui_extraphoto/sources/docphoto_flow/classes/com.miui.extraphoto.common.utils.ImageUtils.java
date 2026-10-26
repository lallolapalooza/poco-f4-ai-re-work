package com.miui.extraphoto.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.miui.extraphoto.common.gles.utils.TextureUtils;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ImageUtils {
    public static int getDegreeByOrientation(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public static int getPhotoOrientationByDegree(int i) {
        if (i == 90) {
            return 6;
        }
        if (i != 180) {
            return i != 270 ? 1 : 8;
        }
        return 3;
    }

    public static Bitmap generateMirrorBitmap(Bitmap bitmap) {
        return generateMirrorBitmap(bitmap, 0);
    }

    public static Bitmap generateMirrorBitmap(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        if (TextureUtils.isVerticalOrientation(i)) {
            matrix.postScale(1.0f, -1.0f);
        } else {
            matrix.postScale(-1.0f, 1.0f);
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap generateBitmapWithOrientation(Bitmap bitmap, int i) {
        float degreeByOrientation = getDegreeByOrientation(i);
        if (degreeByOrientation == 0.0f) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(degreeByOrientation);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap generateBitmapWithDegree(Bitmap bitmap, int i) {
        return generateBitmapWithOrientation(bitmap, getPhotoOrientationByDegree(i));
    }

    public static void cropYuvByRect(byte[] bArr, byte[] bArr2, int i, int i2, Rect rect) {
        int iWidth = rect.width();
        int iHeight = rect.height();
        int i3 = rect.left;
        int i4 = rect.top;
        doCropYuv(bArr, bArr2, (i * i4) + i3, i * (i4 + iHeight), i, iWidth, 0);
        int i5 = i2 * i;
        int i6 = rect.top;
        doCropYuv(bArr, bArr2, ((i * i6) / 2) + i5 + rect.left, i5 + (((i6 + iHeight) * i) / 2), i, iWidth, iWidth * iHeight);
    }

    private static void doCropYuv(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5) {
        while (i < i2) {
            System.arraycopy(bArr, i, bArr2, i5, i4);
            i5 += i4;
            i += i3;
        }
    }

    public static Bitmap getScaleBitmapByMaxSize(Bitmap bitmap, int i) {
        int i2;
        int i3;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int iFindMaximumCommonDivisor = MathUtils.findMaximumCommonDivisor(width, height);
        int i4 = width / iFindMaximumCommonDivisor;
        int i5 = height / iFindMaximumCommonDivisor;
        float fMax = i / Math.max(i4, i5);
        if (fMax < 4.0f) {
            int iRound = Math.round(i4 * fMax);
            int iRound2 = Math.round(i5 * fMax);
            i2 = iRound - (iRound % 4);
            i3 = iRound2 - (iRound2 % 4);
        } else {
            int i6 = (int) fMax;
            if (i6 < 8) {
                i6 = 8;
            }
            int i7 = i6 - (i6 % 8);
            i2 = i4 * i7;
            i3 = i7 * i5;
        }
        return Bitmap.createScaledBitmap(bitmap, i2, i3, true);
    }

    public static void recycleBitmaps(Bitmap... bitmapArr) {
        if (bitmapArr == null) {
            return;
        }
        for (Bitmap bitmap : bitmapArr) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        int width = bitmap.getWidth();
        int width2 = bitmap.getWidth();
        if (width / 2 > i && width2 / 2 > i2) {
            return Bitmap.createScaledBitmap(Bitmap.createScaledBitmap(bitmap, i * 2, i2 * 2, true), i, i2, true);
        }
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }

    public static InSampleInfo computeInSampleInfo(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        InSampleInfo inSampleInfo = new InSampleInfo();
        inSampleInfo.outWidth = options.outWidth;
        inSampleInfo.outHeight = options.outHeight;
        inSampleInfo.inSampleSize = Integer.highestOneBit(Math.max(Math.max(options.outWidth / ScreenUtils.getScreenWidth(StaticContext.sGetAndroidContext()), options.outHeight / ScreenUtils.getScreenHeight(StaticContext.sGetAndroidContext())), 1));
        return inSampleInfo;
    }
}
