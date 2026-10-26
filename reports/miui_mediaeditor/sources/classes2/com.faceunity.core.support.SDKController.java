package com.faceunity.core.support;

import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import g2.a;
import java.io.Serializable;
import java.util.Arrays;
import k2.b;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
public final class SDKController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SDKController$systemErrorMaps$1 f1392a = new SDKController$systemErrorMaps$1();

    public static void a() {
        int iFuGetSystemError = Faceunity.fuGetSystemError();
        if (iFuGetSystemError != 0) {
            String strFuGetSystemErrorString = Faceunity.fuGetSystemErrorString(iFuGetSystemError);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(strFuGetSystemErrorString);
            sb2.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
            Integer numValueOf = Integer.valueOf(iFuGetSystemError);
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = f1392a;
            sb2.append((String) sDKController$systemErrorMaps$1.get(numValueOf));
            sb2.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
            FULogger.b("KIT_SDKController", sb2.toString());
            b.f11921a.getClass();
            a aVar = b.d;
            if (aVar != null) {
                aVar.b(iFuGetSystemError, strFuGetSystemErrorString + CoreConstants.LEFT_PARENTHESIS_CHAR + ((String) sDKController$systemErrorMaps$1.get(Integer.valueOf(iFuGetSystemError))) + CoreConstants.RIGHT_PARENTHESIS_CHAR);
            }
        }
    }

    public static void b(int i10, int[] iArr) {
        FULogger.a("KIT_SDKController", "fuBindItems   item: " + i10 + "    items:" + iArr);
        Faceunity.fuBindItems(i10, iArr);
    }

    public static void c(int i10, int i11, int i12, byte[] bArr, int i13, int[] iArr) {
        String string;
        long jNanoTime = System.nanoTime();
        Faceunity.fuImageBeautyGetResult(i10, 0, i11, i12, bArr, i13, null, 0, null, 0, iArr);
        StringBuilder sb2 = new StringBuilder("fuImageBeautyGetResult saveTime: ");
        sb2.append(System.nanoTime() - jNanoTime);
        sb2.append("format:");
        sb2.append(i10);
        sb2.append("   tex_id:0   width:");
        sb2.append(i11);
        sb2.append("   height:");
        sb2.append(i12);
        sb2.append("   items:");
        if (iArr != null) {
            string = Arrays.toString(iArr);
            e.e(string, "toString(this)");
        } else {
            string = null;
        }
        sb2.append(string);
        FULogger.a("KIT_SDKController", sb2.toString());
    }

    public static int d(int i10, int i11, int i12, int[] iArr, byte[] bArr, int i13, int i14, int i15, byte[] bArr2) {
        StringBuilder sb2 = new StringBuilder("fuRenderImg   img:");
        a0.a.k(sb2, bArr.length, "   w:", i10, "  h:");
        sb2.append(i11);
        sb2.append("  flags:0  items:");
        sb2.append(Arrays.toString(iArr));
        sb2.append("  imgType:");
        sb2.append(i13);
        sb2.append("frame_id:");
        sb2.append(i12);
        sb2.append("    readback_w:");
        sb2.append(i14);
        sb2.append("      readback_h:");
        sb2.append(i15);
        sb2.append("  readback_img:");
        sb2.append(bArr2 != null ? bArr2.length : 0);
        String msg = sb2.toString();
        e.f(msg, "msg");
        if (FULogger.f1393a.ordinal() <= 0) {
            Log.v("KIT_SDKController", msg);
        }
        int iFuRenderImg = Faceunity.fuRenderImg(i10, i11, i12, iArr, 0, bArr, i13, i14, i15, bArr2);
        String msg2 = "fuRenderImg  res:" + iFuRenderImg;
        e.f(msg2, "msg");
        if (FULogger.f1393a.ordinal() <= 0) {
            Log.v("KIT_SDKController", msg2);
        }
        return iFuRenderImg;
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [float[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r8v4, types: [double[], java.io.Serializable] */
    public static Serializable e(int i10, Class clazz, String str) {
        e.f(clazz, "clazz");
        if (e.a(clazz, Double.TYPE)) {
            double dFuItemGetParam = Faceunity.fuItemGetParam(i10, str);
            FULogger.a("KIT_SDKController", "fuItemGetParam   item: " + i10 + "    name:" + str + "   res:" + dFuItemGetParam);
            return Double.valueOf(dFuItemGetParam);
        }
        String string = null;
        if (e.a(clazz, double[].class)) {
            ?? FuItemGetParamdv = Faceunity.fuItemGetParamdv(i10, str);
            StringBuilder sb2 = new StringBuilder("fuItemGetParamdv   item: ");
            sb2.append(i10);
            sb2.append("    name:");
            sb2.append(str);
            sb2.append("   res:");
            if (FuItemGetParamdv != 0) {
                string = Arrays.toString((double[]) FuItemGetParamdv);
                e.e(string, "toString(this)");
            }
            sb2.append(string);
            FULogger.a("KIT_SDKController", sb2.toString());
            return FuItemGetParamdv;
        }
        if (e.a(clazz, String.class)) {
            String strFuItemGetParamString = Faceunity.fuItemGetParamString(i10, str);
            FULogger.a("KIT_SDKController", "fuItemGetParamString   item: " + i10 + "    name:" + str + "   res:" + strFuItemGetParamString);
            return strFuItemGetParamString;
        }
        if (!e.a(clazz, float[].class)) {
            return null;
        }
        ?? FuItemGetParamfv = Faceunity.fuItemGetParamfv(i10, str);
        StringBuilder sb3 = new StringBuilder("fuItemGetParamfv   item: ");
        sb3.append(i10);
        sb3.append("    name:");
        sb3.append(str);
        sb3.append("   res:");
        if (FuItemGetParamfv != 0) {
            string = Arrays.toString((float[]) FuItemGetParamfv);
            e.e(string, "toString(this)");
        }
        sb3.append(string);
        FULogger.a("KIT_SDKController", sb3.toString());
        return FuItemGetParamfv;
    }

    public static void f(int i10, String name, double d) {
        e.f(name, "name");
        FULogger.a("KIT_SDKController", "fuItemSetParam   item: " + i10 + "    name:" + name + "   value:" + d);
        Faceunity.fuItemSetParam(i10, name, d);
    }

    public static void g(int i10, String name, String value) {
        e.f(name, "name");
        e.f(value, "value");
        FULogger.a("KIT_SDKController", "fuItemSetParam   item: " + i10 + "    name:" + name + "   value:" + value);
        Faceunity.fuItemSetParam(i10, name, value);
    }

    public static void h(int i10, String name, double[] value) {
        e.f(name, "name");
        e.f(value, "value");
        StringBuilder sb2 = new StringBuilder("fuItemSetParam   item: ");
        sb2.append(i10);
        sb2.append("    name:");
        sb2.append(name);
        sb2.append("   value:");
        String string = Arrays.toString(value);
        e.e(string, "toString(this)");
        sb2.append(string);
        FULogger.a("KIT_SDKController", sb2.toString());
        Faceunity.fuItemSetParam(i10, name, value);
    }

    public static void i(int i10) {
        int iFuReleaseAIModel = Faceunity.fuReleaseAIModel(i10);
        StringBuilder sbI = androidx.appcompat.widget.a.i("fuReleaseAIModel  type:", i10, "   res:");
        sbI.append(iFuReleaseAIModel == 1);
        FULogger.a("KIT_SDKController", sbI.toString());
    }
}
