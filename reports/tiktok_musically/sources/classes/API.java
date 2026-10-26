package com.ss.bytenn;

import X.0lJ;
import X.7Xe;
import android.os.SystemClock;
import com.bytedance.covode.number.Covode;
import com.bytedance.librarian.Librarian;
import java.util.ArrayList;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/session_tmp/ttdex/classes6.dex */
public class API {
    public static String TAG;
    public long engineHandle;

    public static native long nativeCreateEngineInstance();

    public static native int nativeDestroyEngine(long j);

    public static native Tensor[] nativeGetEngineOutputs(long j);

    public static native Tensor[] nativeGetInputConfig(long j);

    public static native int nativeInference(long j);

    public static native int nativeInitEngine(long j, ByteNNConfig byteNNConfig);

    public static native int nativeReInferShape(long j, int i, int i2);

    public static native int nativeReleaseEngine(long j);

    public static native int nativeSetEngineInputs(long j, Tensor[] tensorArr);

    public 7Xe Inference() {
        return 7Xe.values()[nativeInference(this.engineHandle)];
    }

    public 7Xe ReleaseEngine() {
        return 7Xe.values()[nativeReleaseEngine(this.engineHandle)];
    }

    static {
        Covode.recordClassIndex(116704);
        TAG = "BYTENN.API";
        com_ss_bytenn_API_com_ss_android_ugc_aweme_lancet_LoadSoLancet_loadLibrary("bytenn");
        com_ss_bytenn_API_com_ss_android_ugc_aweme_lancet_LoadSoLancet_loadLibrary("bytennwrapper");
    }

    public 7Xe CreateEngine() {
        long jNativeCreateEngineInstance = nativeCreateEngineInstance();
        this.engineHandle = jNativeCreateEngineInstance;
        if (jNativeCreateEngineInstance == 0) {
            return 7Xe.ERR_MEMORY_ALLOC;
        }
        return 7Xe.NO_ERROR;
    }

    public 7Xe DestroyEngine() {
        7Xe r2 = 7Xe.values()[nativeDestroyEngine(this.engineHandle)];
        this.engineHandle = 0L;
        return r2;
    }

    public static void com_ss_bytenn_API_com_ss_android_ugc_aweme_lancet_LoadSoLancet_loadLibrary(String str) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Librarian.LIZ(str);
        0lJ.LIZ(jUptimeMillis, str);
    }

    public 7Xe InitEngine(ByteNNConfig byteNNConfig) {
        int iNativeInitEngine = nativeInitEngine(this.engineHandle, byteNNConfig);
        7Xe.values();
        return 7Xe.values()[iNativeInitEngine];
    }

    public 7Xe GetEngineInputConfig(ArrayList<Tensor> arrayList) {
        Tensor[] tensorArrNativeGetInputConfig = nativeGetInputConfig(this.engineHandle);
        if (tensorArrNativeGetInputConfig.length <= 0) {
            return 7Xe.ERR_UNEXPECTED;
        }
        for (Tensor tensor : tensorArrNativeGetInputConfig) {
            arrayList.add(tensor);
        }
        return 7Xe.NO_ERROR;
    }

    public 7Xe GetEngineOutputs(ArrayList<Tensor> arrayList) {
        Tensor[] tensorArrNativeGetEngineOutputs = nativeGetEngineOutputs(this.engineHandle);
        if (tensorArrNativeGetEngineOutputs.length <= 0) {
            return 7Xe.ERR_UNEXPECTED;
        }
        for (Tensor tensor : tensorArrNativeGetEngineOutputs) {
            arrayList.add(tensor);
        }
        return 7Xe.NO_ERROR;
    }

    public 7Xe SetEngineInputs(ArrayList<Tensor> arrayList) {
        return 7Xe.values()[nativeSetEngineInputs(this.engineHandle, (Tensor[]) arrayList.toArray(new Tensor[arrayList.size()]))];
    }

    public 7Xe ReInferShape(int i, int i2) {
        return 7Xe.values()[nativeReInferShape(this.engineHandle, i2, i)];
    }
}
