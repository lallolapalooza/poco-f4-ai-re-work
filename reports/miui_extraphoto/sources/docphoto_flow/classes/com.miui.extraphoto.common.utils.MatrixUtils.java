package com.miui.extraphoto.common.utils;

import android.graphics.Matrix;

/* JADX INFO: loaded from: classes.dex */
public class MatrixUtils {
    private static final float[] mMatrixValues = new float[9];

    public static Matrix inverseMatrix(Matrix matrix) {
        if (matrix == null) {
            return null;
        }
        Matrix matrix2 = new Matrix();
        matrix.invert(matrix2);
        return matrix2;
    }

    public static float[] mapPoints(float[] fArr, Matrix matrix) {
        if (fArr == null || fArr.length == 0) {
            return null;
        }
        if (matrix == null) {
            return fArr;
        }
        float[] fArr2 = new float[fArr.length];
        matrix.mapPoints(fArr2, fArr);
        return fArr2;
    }
}
