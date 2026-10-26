package com.ss.android.ttve.nativePort;

import X.C12750e5;
import android.graphics.Bitmap;
import com.bytedance.covode.number.Covode;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class TEEffectUtils {

    public interface ImageListener {
        static {
            Covode.recordClassIndex(42405);
        }

        void onData(int[] iArr, int i, int i2, int i3);
    }

    public interface QrImageListener {
        static {
            Covode.recordClassIndex(42406);
        }

        void onData(int[] iArr, int i, int i2, int i3);
    }

    public static native String getAudioAECModelName();

    public static native String getEffectVersion();

    public static String getTaintSceneDetectModelName() {
        return "lens_taint_scene_detect";
    }

    public static native int nativeGetQREncodedData(String str, int[] iArr, ImageListener imageListener);

    public static native int nativeGetQREncodedDataWithWH(String str, int i, int i2, int[] iArr, QrImageListener qrImageListener);

    public static native boolean nativeResizeImageWithEffect2(Bitmap bitmap, Bitmap bitmap2);

    static {
        Covode.recordClassIndex(42404);
        C12750e5.LIZ();
    }

    public static boolean resizeImageWithEffect(Bitmap bitmap, Bitmap bitmap2) {
        return nativeResizeImageWithEffect2(bitmap, bitmap2);
    }

    public static int getQREncodedData(String str, Map<Integer, Integer> map, ImageListener imageListener) {
        int[] iArr;
        if (map != null) {
            iArr = new int[map.size() * 2];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                iArr[i] = entry.getKey().intValue();
                iArr[i + 1] = entry.getValue().intValue();
                i += 2;
            }
        } else {
            iArr = null;
        }
        return nativeGetQREncodedData(str, iArr, imageListener);
    }

    public static int getQREncodedData(String str, int i, int i2, Map<Integer, Integer> map, QrImageListener qrImageListener) {
        int[] iArr;
        if (map != null) {
            iArr = new int[map.size() * 2];
            int i3 = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                iArr[i3] = entry.getKey().intValue();
                iArr[i3 + 1] = entry.getValue().intValue();
                i3 += 2;
            }
        } else {
            iArr = null;
        }
        return nativeGetQREncodedDataWithWH(str, i, i2, iArr, qrImageListener);
    }
}
