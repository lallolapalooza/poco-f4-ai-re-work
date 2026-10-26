package com.miui.extraphoto.docphoto;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.text.TextUtils;
import com.miui.extraphoto.common.utils.ImageUtils;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$EnhanceType;

/* JADX INFO: loaded from: classes.dex */
public class Util {
    public static Matrix getDelegateBitmapMatrix(int i, int i2, float f, int i3) {
        int degreeByOrientation = ImageUtils.getDegreeByOrientation(i3);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        matrix.postRotate(degreeByOrientation);
        RectF rectF = new RectF(0.0f, 0.0f, i, i2);
        RectF rectF2 = new RectF();
        matrix.mapRect(rectF2, rectF);
        matrix.postTranslate(-rectF2.left, -rectF2.top);
        return matrix;
    }

    public static DocumentProcess$EnhanceType convertToEnhanceType(String str) {
        if (str == null) {
            return DocumentProcess$EnhanceType.RAW;
        }
        DocumentProcess$EnhanceType documentProcess$EnhanceType = DocumentProcess$EnhanceType.RAW;
        if (TextUtils.equals(str, documentProcess$EnhanceType.toString())) {
            return documentProcess$EnhanceType;
        }
        DocumentProcess$EnhanceType documentProcess$EnhanceType2 = DocumentProcess$EnhanceType.BIN;
        if (TextUtils.equals(str, documentProcess$EnhanceType2.toString())) {
            return documentProcess$EnhanceType2;
        }
        DocumentProcess$EnhanceType documentProcess$EnhanceType3 = DocumentProcess$EnhanceType.COLOR;
        if (TextUtils.equals(str, documentProcess$EnhanceType3.toString())) {
            return documentProcess$EnhanceType3;
        }
        DocumentProcess$EnhanceType documentProcess$EnhanceType4 = DocumentProcess$EnhanceType.GRAY;
        return TextUtils.equals(str, documentProcess$EnhanceType4.toString()) ? documentProcess$EnhanceType4 : documentProcess$EnhanceType;
    }

    public static String convertToString(float[] fArr) {
        if (fArr == null || fArr.length != 8) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (float f : fArr) {
            sb.append(f);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static float[] convertToPoints(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(",");
        if (strArrSplit.length != 8) {
            return null;
        }
        float[] fArr = new float[8];
        for (int i = 0; i < strArrSplit.length; i++) {
            try {
                fArr[i] = Float.parseFloat(strArrSplit[i]);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return fArr;
    }
}
