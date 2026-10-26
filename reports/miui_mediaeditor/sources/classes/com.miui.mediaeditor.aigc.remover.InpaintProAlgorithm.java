package com.miui.mediaeditor.aigc.remover;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.view.a;
import com.miui.mediaeditor.aigc.remover.param.NNFData;

/* JADX INFO: loaded from: classes2.dex */
public class InpaintProAlgorithm {
    public static final int ERROR_CODE_INITIALIZED_FAILED = -11;
    public static final int ERROR_CODE_LOAD_FAILED = -10;
    private static final String TAG = "InpaintProAlgorithm";
    public static final int TypeLine = 2;
    public static final int TypeManual = 0;
    public static final int TypeObjects = 3;
    public static final int TypePeople = 1;
    private static boolean isInitialized = false;
    private static long removerInstancePtr;
    private static volatile InpaintProAlgorithm sInstance;

    private InpaintProAlgorithm() {
    }

    public static InpaintProAlgorithm getInstance() {
        if (sInstance == null) {
            synchronized (InpaintProAlgorithm.class) {
                if (sInstance == null) {
                    sInstance = new InpaintProAlgorithm();
                }
            }
        }
        return sInstance;
    }

    public static boolean init(long j, long j10, String str, String str2) {
        if (!isInitialized) {
            removerInstancePtr = InpaintProJni.init(j, j10, str, str2);
        }
        boolean z = removerInstancePtr != 0;
        isInitialized = z;
        return z;
    }

    public static int inpaint(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int i10, int i11, NNFData nNFData, int i12, int[] iArr) {
        if (!isInitialized) {
            return -11;
        }
        a.o(new StringBuilder("start inpaint, type: "), nNFData.type, TAG);
        return InpaintProJni.inpaint(bitmap, bitmap2, bitmap3, i10, i11, nNFData, removerInstancePtr, i12, iArr);
    }

    public static void release() {
        if (isInitialized) {
            InpaintProJni.release(removerInstancePtr);
            isInitialized = false;
            removerInstancePtr = 0L;
        }
    }

    public static int upsample(long j, Bitmap bitmap, int i10, int i11, NNFData nNFData) {
        Log.d(TAG, "start upsample");
        return InpaintProJni.upsample(j, bitmap, i10, i11, nNFData);
    }
}
