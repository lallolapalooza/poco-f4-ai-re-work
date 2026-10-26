package com.quicinc.voice.activation.vad;

import G.q;
import H.a;
import android.os.Bundle;
import android.text.TextUtils;
import com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import p003d.g;
import p009j.i;
import p011l.c;
import p011l.d;

/* JADX INFO: loaded from: classes.dex */
public class DNNVAD {
    public static final a EMPTY = createProcessResult(Integer.MIN_VALUE, null);
    private static final String NNVAD_CMD_END_DETECTION_THRESHOLD = "VAD.cmd_end_detection_threshold";
    private static final String NNVAD_LEN_FUTURE = "VAD.nnvad_len_future";
    private static final String NNVAD_LEN_HANGOVER = "VAD.nnvad_len_hangover";
    private static final String NNVAD_THRESHOLD = "VAD.nnvad_threshold";
    private static final String TAG = "DNNVAD";
    private static String mVadModelFileName;
    private long mNativeContext;
    private final Bundle mParams;
    private final Bundle mResultBundle = new Bundle();
    private final ByteBuffer mVadModelBuffer = getDNNVADModel();

    public DNNVAD(d dVar) {
        this.mParams = getDNNVADConfiguration(dVar);
    }

    private static a createProcessResult(int i2, Bundle bundle) {
        return new a(i2, bundle);
    }

    private Bundle getDNNVADConfiguration(d dVar) {
        Bundle bundle = new Bundle();
        c cVar = new c(null, "Platform_UV", null, "nn_vad");
        ModelConfigurationKey modelConfigurationKey = ModelConfigurationKey.f435I;
        bundle.putInt(modelConfigurationKey.b(), getInt(dVar, cVar, modelConfigurationKey));
        bundle.putInt(NNVAD_LEN_FUTURE, getInt(dVar, cVar, ModelConfigurationKey.f436J));
        bundle.putInt(NNVAD_LEN_HANGOVER, getInt(dVar, cVar, ModelConfigurationKey.f437K));
        bundle.putInt(NNVAD_THRESHOLD, getInt(dVar, cVar, ModelConfigurationKey.f438L));
        bundle.putInt(NNVAD_CMD_END_DETECTION_THRESHOLD, getInt(dVar, cVar, ModelConfigurationKey.f439M));
        q.e(TAG + " dnnvad config " + bundle);
        return bundle;
    }

    private ByteBuffer getDNNVADModel() {
        String dNNVADModelFile = getDNNVADModelFile();
        ByteBuffer byteBufferAllocateDirect = null;
        if (dNNVADModelFile == null) {
            return null;
        }
        try {
            InputStream inputStreamOpen = g.d().getAssets().open(dNNVADModelFile);
            try {
                int iAvailable = inputStreamOpen.available();
                byte[] bArr = new byte[iAvailable];
                byteBufferAllocateDirect = ByteBuffer.allocateDirect(inputStreamOpen.read(bArr, 0, iAvailable));
                byteBufferAllocateDirect.put(bArr);
                inputStreamOpen.close();
            } catch (Throwable th) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return byteBufferAllocateDirect;
    }

    private String getDNNVADModelFile() {
        String[] list;
        if (!TextUtils.isEmpty(mVadModelFileName)) {
            return mVadModelFileName;
        }
        try {
            list = g.d().getAssets().list("");
            try {
                q.h(TAG + " find dnnvad model files " + Arrays.toString(list));
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (IOException e3) {
            e = e3;
            list = null;
        }
        if (list == null) {
            return null;
        }
        for (String str : list) {
            if (str.startsWith("speech") && str.endsWith(".eai")) {
                mVadModelFileName = str;
                return str;
            }
        }
        q.j(TAG + " can not find avaiable vad model");
        return null;
    }

    private int getInt(d dVar, c cVar, p011l.g gVar) {
        int i2 = dVar.i(cVar, gVar);
        if (i2 == Integer.MIN_VALUE) {
            return 0;
        }
        return i2;
    }

    public native int deinit();

    public int deinitVAD() {
        int iDeinit;
        synchronized (this) {
            iDeinit = deinit();
        }
        q.h(TAG + " VAD deinit result = " + iDeinit);
        return iDeinit;
    }

    public int getTimeoutSize() {
        return this.mParams.getInt(ModelConfigurationKey.f435I.b(), 4000) * i.j();
    }

    public native int init(ByteBuffer byteBuffer, int i2, Bundle bundle);

    public int initVAD() {
        int iInit;
        if (this.mVadModelBuffer == null) {
            return -1;
        }
        synchronized (this) {
            ByteBuffer byteBuffer = this.mVadModelBuffer;
            iInit = init(byteBuffer, byteBuffer.capacity(), this.mParams);
        }
        return iInit;
    }

    public native int process(ShortBuffer shortBuffer, Bundle bundle);

    public a processVAD(ShortBuffer shortBuffer) {
        int iProcess;
        synchronized (this) {
            iProcess = process(shortBuffer, this.mResultBundle);
        }
        q.h(TAG + " VAD process result = " + iProcess + " bundle = " + this.mResultBundle);
        return createProcessResult(iProcess, this.mResultBundle);
    }

    public int reInitVAD() {
        return initVAD() & deinitVAD();
    }
}
