package com.ss.android.ugc.aweme.inferenceengine;

import X.0cy;
import X.0d0;
import X.0kl;
import X.0lD;
import X.C01638Jl;
import X.C01648Jm;
import X.C01668Jo;
import X.C01678Jp;
import X.C01688Jq;
import X.C01718Jt;
import X.C01868Ki;
import X.C8JY;
import X.C8JZ;
import X.C8K0;
import X.C8K1;
import X.C8K2;
import X.C8K5;
import X.C8K6;
import X.C8K7;
import X.InterfaceC01538Jb;
import X.InterfaceC01698Jr;
import X.InterfaceC01758Jx;
import X.InterfaceC01778Jz;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.covode.number.Covode;
import com.google.gson.Gson;
import com.google.gson.c.a;
import com.ss.android.common.util.NetworkUtils;
import com.ss.android.ml.process.bl.MLConfigModel;
import com.ss.bytenn.API;
import com.ss.bytenn.ByteNNConfig;
import com.ss.bytenn.Tensor;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/work/tmp_trill/classes5.dex */
public class InferenceEngineServiceImpl implements IInferenceEngineService {
    public static InterfaceC01778Jz LIZ;

    static {
        Covode.recordClassIndex(77407);
        LIZ = new InterfaceC01778Jz() { // from class: com.ss.android.ugc.aweme.inferenceengine.InferenceEngineServiceImpl.1
            static {
                Covode.recordClassIndex(77408);
            }

            @Override // X.InterfaceC01778Jz
            public final InterfaceC01758Jx LIZ() {
                return new InterfaceC01758Jx() { // from class: X.8Kf
                    public API LIZ;

                    static {
                        Covode.recordClassIndex(77412);
                    }

                    @Override // X.InterfaceC01758Jx
                    public final boolean LIZ(MappedByteBuffer mappedByteBuffer, MLConfigModel mLConfigModel) {
                        API api = new API();
                        this.LIZ = api;
                        EnumC01848Kg enumC01848KgCreateEngine = api.CreateEngine();
                        if (enumC01848KgCreateEngine != EnumC01848Kg.NO_ERROR) {
                            C8JY.LIZ(new RuntimeException("byteNN create engine failed with code ".concat(String.valueOf(enumC01848KgCreateEngine))));
                            return false;
                        }
                        ByteNNConfig byteNNConfig = new ByteNNConfig();
                        try {
                            EnumC01858Kh enumC01858Kh = EnumC01858Kh.CPU;
                            if (TextUtils.equals(mLConfigModel.bytenn_forward_type, "auto")) {
                                enumC01858Kh = EnumC01858Kh.Auto;
                            }
                            byteNNConfig.init(enumC01858Kh, mappedByteBuffer, (String[]) null, "", "");
                            if (mLConfigModel.num_threads == 2) {
                                byteNNConfig.setThreadNum(mLConfigModel.num_threads);
                            } else {
                                byteNNConfig.setThreadNum(1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        EnumC01848Kg enumC01848KgInitEngine = this.LIZ.InitEngine(byteNNConfig);
                        if (enumC01848KgInitEngine != EnumC01848Kg.NO_ERROR) {
                            C8JY.LIZ(new RuntimeException("byteNN init engine failed with code ".concat(String.valueOf(enumC01848KgInitEngine))));
                        }
                        if (enumC01848KgInitEngine != EnumC01848Kg.NO_ERROR) {
                            return false;
                        }
                        return true;
                    }

                    @Override // X.InterfaceC01758Jx
                    public final void LIZ(ByteBuffer byteBuffer, float[][] fArr) {
                        if (this.LIZ == null) {
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        EnumC01848Kg enumC01848KgGetEngineInputConfig = this.LIZ.GetEngineInputConfig(arrayList);
                        if (enumC01848KgGetEngineInputConfig != EnumC01848Kg.NO_ERROR) {
                            C8JY.LIZ(new RuntimeException("byteNN get engine input failed with code ".concat(String.valueOf(enumC01848KgGetEngineInputConfig))));
                            return;
                        }
                        try {
                            ((Tensor) arrayList.get(0)).setData(byteBuffer);
                            this.LIZ.SetEngineInputs(arrayList);
                            this.LIZ.Inference();
                            ArrayList arrayList2 = new ArrayList();
                            EnumC01848Kg enumC01848KgGetEngineOutputs = this.LIZ.GetEngineOutputs(arrayList2);
                            if (enumC01848KgGetEngineOutputs != EnumC01848Kg.NO_ERROR) {
                                C8JY.LIZ(new RuntimeException("byteNN inference get output failed with code ".concat(String.valueOf(enumC01848KgGetEngineOutputs))));
                                return;
                            }
                            ByteBuffer data = ((Tensor) arrayList2.get(0)).getData();
                            data.order(ByteOrder.nativeOrder());
                            int i = 0;
                            while (data.hasRemaining()) {
                                float f = data.getFloat();
                                if (i < fArr[0].length) {
                                    fArr[0][i] = f;
                                }
                                i++;
                            }
                            if (C8JY.LIZ && i != fArr[0].length) {
                                C01688Jq.LIZ("ml#evaluator", "bytenn inference error, outBuffer.size != resultBuffer.size, result.size:" + i + " out.size:" + fArr[0].length, null);
                            }
                        } catch (Exception e) {
                            C01688Jq.LIZ("ml#evaluator", "inference error: ", e);
                            C8JY.LIZ(e);
                        }
                    }
                };
            }
        };
    }

    @Override // com.ss.android.ugc.aweme.inferenceengine.IInferenceEngineService
    public final void LIZ(Context context) {
        if (C01668Jo.LIZ) {
            System.currentTimeMillis();
        }
        C8JY.LIZ = C01668Jo.LIZ;
        InterfaceC01778Jz interfaceC01778Jz = LIZ;
        C01718Jt.LIZ = true;
        C01718Jt.LIZIZ = interfaceC01778Jz;
        if (0lD.LIZJ == null || !0lD.LJ) {
            0lD.LIZJ = context.getFilesDir();
        }
        String absolutePath = 0lD.LIZJ.getAbsolutePath();
        C01648Jm c01648Jm = new C01648Jm();
        c01648Jm.LIZ = absolutePath;
        c01648Jm.LIZIZ = C8K2.LIZ;
        c01648Jm.LIZJ = new InterfaceC01538Jb() { // from class: com.ss.android.ugc.aweme.inferenceengine.InferenceEngineServiceImpl.3
            static {
                Covode.recordClassIndex(77410);
            }

            @Override // X.InterfaceC01538Jb
            public final <T> T LIZ(String str, Type type) {
                return (T) new Gson().fromJson(new a(new StringReader(str)), type);
            }
        };
        c01648Jm.LIZLLL = C8K5.LIZ;
        c01648Jm.LJ = new C8K0() { // from class: com.ss.android.ugc.aweme.inferenceengine.InferenceEngineServiceImpl.2
            static {
                Covode.recordClassIndex(77409);
            }

            @Override // X.C8K0
            public final ExecutorService LIZ() {
                return C01868Ki.LIZ;
            }
        };
        c01648Jm.LJFF = new C01668Jo();
        C01638Jl c01638Jl = new C01638Jl(c01648Jm);
        if (C8JY.LIZ) {
            new Throwable("not crash, only for debug!!");
        }
        C8JZ c8jzLIZ = C8JZ.LIZ();
        if (!C8JZ.LIZ) {
            C8JZ.LIZ = true;
            c8jzLIZ.LIZIZ = c01638Jl;
        }
        C01688Jq.LIZ = new C8K1() { // from class: com.ss.android.ugc.aweme.inferenceengine.InferenceEngineServiceImpl.4
            static {
                Covode.recordClassIndex(77411);
            }

            @Override // X.C8K1
            public final void LIZ(String str, String str2, Throwable th) {
                if (th == null) {
                    0kl.LIZIZ(str, str2);
                } else {
                    0kl.LIZIZ(str, str2 + " erMsg:" + th.getMessage());
                }
            }
        };
        C8K7.LIZ = true;
        C01678Jp c01678Jp = InterfaceC01698Jr.LIZ;
        C01678Jp.LIZ = C8K6.LIZ;
        if (C01678Jp.LIZJ && !C01678Jp.LIZIZ) {
            synchronized (Boolean.valueOf(C01678Jp.LIZIZ)) {
                c01678Jp.LIZ();
            }
        }
        if (C01668Jo.LIZ) {
            System.currentTimeMillis();
        }
    }

    public static final /* synthetic */ boolean LIZ(String str, String str2, String str3) {
        try {
            return NetworkUtils.downloadFile(-1, str, str2, (String) null, str3, (0cy) null, (String) null, (0d0) null, (List) null, (String[]) null, (int[]) null);
        } catch (Exception unused) {
            return false;
        }
    }
}
