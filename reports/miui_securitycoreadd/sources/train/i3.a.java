package i3;

import android.app.job.JobParameters;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import com.miui.aiautobrt.database.BrightnessModelDatabase;
import com.miui.aiautobrt.service.TrainJobService;
import com.xiaomi.aiautobrt.IIndividualCallback;
import com.xiaomi.aiautobrt.IndividualTrainEvent;
import com.xiaomi.aiautobrt.InterfaceVersionHelper;
import f3.c;
import f3.d;
import j8.m;
import java.io.File;
import java.lang.reflect.Array;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ JobParameters f4270b;
    public final /* synthetic */ TrainJobService c;

    public a(TrainJobService trainJobService, JobParameters jobParameters) {
        this.c = trainJobService;
        this.f4270b = jobParameters;
    }

    /* JADX WARN: Code duplicated, block: B:70:0x0287 A[Catch: Exception -> 0x02a2, TRY_LEAVE, TryCatch #5 {Exception -> 0x02a2, blocks: (B:63:0x0260, B:65:0x0269, B:67:0x027e, B:70:0x0287), top: B:151:0x0260 }] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.util.ArrayList, java.util.List, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r0v49, types: [java.util.ArrayList, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r0v51, types: [java.util.ArrayList, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r0v71, types: [java.util.ArrayList, java.util.List, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.util.ArrayList, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r1v29, types: [java.util.ArrayList, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r5v16, types: [java.util.ArrayList, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r6v35, types: [java.util.ArrayList, java.util.List<h3.a>] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.util.ArrayList, java.util.List<h3.a>] */
    @Override // java.lang.Runnable
    public final void run() {
        List<h3.a> list;
        boolean z10;
        boolean z11;
        char c;
        List listSubList;
        float[][] fArr;
        float f10;
        a aVar = this;
        t9.a aVar2 = t9.a.INFO;
        t9.a aVar3 = t9.a.ERROR;
        c cVar = aVar.c.f2733b;
        int i10 = 0;
        if (cVar.f3605e >= 2) {
            List<h3.a> listD = cVar.d(cVar.f3604d);
            List<h3.a> listD2 = cVar.d(0);
            if ((!(Settings.Secure.getInt(cVar.f3602a.getContentResolver(), "force_train_enable", 0) == 1) || listD.size() < 1000) && listD.size() < 10000) {
                list = listD;
            } else {
                cVar.f3604d = listD.size() + cVar.f3604d;
                d dVar = cVar.f3603b;
                if (dVar.c.c) {
                    Collections.shuffle(listD2);
                    int size = (listD2.size() / 10) * 9;
                    dVar.f3610d = new ArrayList(listD2.subList(0, size));
                    dVar.f3611e = new ArrayList(listD2.subList(size, listD2.size()));
                    float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) float.class, 128, 3);
                    float[][] fArr3 = (float[][]) Array.newInstance((Class<?>) float.class, 128, 1);
                    float f11 = -1.0f;
                    int i11 = 0;
                    while (true) {
                        if (i11 >= 5) {
                            list = listD;
                            break;
                        }
                        Collections.shuffle(dVar.f3610d);
                        int i12 = i10;
                        int i13 = i12;
                        while (true) {
                            if (!(i12 < dVar.f3610d.size())) {
                                break;
                            }
                            int i14 = i12 + 128;
                            if (i14 >= dVar.f3610d.size()) {
                                ?? r0 = dVar.f3610d;
                                listSubList = r0.subList(r0.size() - 128, dVar.f3610d.size());
                            } else {
                                listSubList = dVar.f3610d.subList(i12, i14);
                            }
                            int i15 = 0;
                            while (i15 < listSubList.size()) {
                                h3.a aVar4 = (h3.a) listSubList.get(i15);
                                float[] fArr4 = fArr2[i15];
                                List list2 = listSubList;
                                fArr4[0] = aVar4.c;
                                fArr4[1] = aVar4.f4046d;
                                fArr4[2] = aVar4.f4048f;
                                fArr3[i15][0] = aVar4.f4049g;
                                gb.c.i(dVar.f3608a);
                                gb.c.c.d("IndividualBrt-ModelController", "Train data: " + aVar4);
                                i15++;
                                i14 = i14;
                                listSubList = list2;
                                listD = listD;
                            }
                            int i16 = i14;
                            List<h3.a> list3 = listD;
                            g3.a aVar5 = dVar.c;
                            Objects.requireNonNull(aVar5);
                            try {
                                HashMap map = new HashMap();
                                map.put("x", fArr2);
                                map.put("y", fArr3);
                                HashMap map2 = new HashMap();
                                FloatBuffer floatBufferAllocate = FloatBuffer.allocate(1);
                                map2.put("loss", floatBufferAllocate);
                                fArr = fArr2;
                                try {
                                    aVar5.f3667e.a(map, map2, "train");
                                    f10 = floatBufferAllocate.get(0);
                                    try {
                                        gb.c.i(aVar5.f3665b);
                                        gb.c.c.d("IndividualBrt-BrightnessLiteModel", "Train loss:" + floatBufferAllocate.get(0));
                                    } catch (IllegalArgumentException e10) {
                                        e = e10;
                                        gb.c.i(aVar5.f3665b);
                                        gb.d.f3839a.a(aVar3, "IndividualBrt-BrightnessLiteModel", "Model train error!", e);
                                        gb.c.c.a(aVar3, "IndividualBrt-BrightnessLiteModel", "Model train error!", e);
                                    }
                                } catch (IllegalArgumentException e11) {
                                    e = e11;
                                    f10 = 1.0f;
                                    gb.c.i(aVar5.f3665b);
                                    gb.d.f3839a.a(aVar3, "IndividualBrt-BrightnessLiteModel", "Model train error!", e);
                                    gb.c.c.a(aVar3, "IndividualBrt-BrightnessLiteModel", "Model train error!", e);
                                    f11 = f10;
                                    i13++;
                                    i12 = i16;
                                    fArr2 = fArr;
                                    listD = list3;
                                }
                            } catch (IllegalArgumentException e12) {
                                e = e12;
                                fArr = fArr2;
                            }
                            f11 = f10;
                            i13++;
                            i12 = i16;
                            fArr2 = fArr;
                            listD = list3;
                        }
                        list = listD;
                        float[][] fArr5 = fArr2;
                        if (i13 == 0) {
                            break;
                        }
                        i11++;
                        fArr2 = fArr5;
                        listD = list;
                        i10 = 0;
                    }
                    if (dVar.f3611e.size() > 0) {
                        IndividualTrainEvent individualTrainEvent = new IndividualTrainEvent();
                        individualTrainEvent.setTrainSampleNum(dVar.f3610d.size());
                        individualTrainEvent.setModelTrainLoss(f11);
                        g3.a aVar6 = dVar.c;
                        ?? r10 = dVar.f3611e;
                        Objects.requireNonNull(aVar6);
                        Iterator it = r10.iterator();
                        float fAbs = 0.0f;
                        float fAbs2 = 0.0f;
                        while (it.hasNext()) {
                            h3.a aVar7 = (h3.a) it.next();
                            Iterator it2 = it;
                            float fA = aVar6.a("new_model_infer", aVar7);
                            float fA2 = aVar6.a("old_model_infer", aVar7);
                            float f12 = aVar7.f4049g;
                            fAbs += Math.abs((f12 - fA) / f12);
                            fAbs2 += Math.abs((f12 - fA2) / f12);
                            it = it2;
                        }
                        float size2 = fAbs / r10.size();
                        float size3 = fAbs2 / r10.size();
                        gb.c.i(aVar6.f3665b);
                        String str = "Model train result newFinalMape = " + size2 + ", oldFinalMape =" + size3;
                        gb.d.f3839a.a(aVar2, "IndividualBrt-BrightnessLiteModel", str, null);
                        gb.c.c.a(aVar2, "IndividualBrt-BrightnessLiteModel", str, null);
                        individualTrainEvent.setCurModelMAPE(size2);
                        individualTrainEvent.setPreModelMAPE(size3);
                        if (size2 < size3) {
                            try {
                                HashMap map3 = new HashMap();
                                if (aVar6.f3666d == null) {
                                    File file = new File(aVar6.f3665b.getFilesDir(), "checkpoint");
                                    aVar6.f3666d = file;
                                    if (file.exists() || aVar6.f3666d.createNewFile()) {
                                        map3.put("checkpoint_path", aVar6.f3666d.getAbsolutePath());
                                        aVar6.f3667e.a(map3, new HashMap(), "save_for_replace");
                                        aVar6.e();
                                    }
                                } else {
                                    map3.put("checkpoint_path", aVar6.f3666d.getAbsolutePath());
                                    aVar6.f3667e.a(map3, new HashMap(), "save_for_replace");
                                    aVar6.e();
                                }
                            } catch (Exception e13) {
                                gb.c.i(aVar6.f3665b);
                                gb.d.f3839a.a(aVar3, "IndividualBrt-BrightnessLiteModel", "Model save error!", e13);
                                gb.c.c.a(aVar3, "IndividualBrt-BrightnessLiteModel", "Model save error!", e13);
                            }
                            z10 = true;
                        } else {
                            z10 = false;
                        }
                        if (z10) {
                            float[] fArr6 = new float[4];
                            float[] fArr7 = new float[4];
                            for (h3.a aVar8 : dVar.f3611e) {
                                float fA3 = dVar.c.a("old_model_infer", aVar8);
                                float f13 = aVar8.f4051i;
                                float f14 = aVar8.f4049g;
                                float[] fArr8 = m.f4504d;
                                if (f14 < fArr8[0]) {
                                    c = 0;
                                } else {
                                    if (f14 < fArr8[0] || f14 >= fArr8[1]) {
                                        c = (f14 < fArr8[1] || f14 >= fArr8[2]) ? (char) 3 : (char) 2;
                                    } else {
                                        c = 1;
                                    }
                                    fArr6[c] = Math.abs((f14 - f13) / f14) + fArr6[c];
                                    fArr7[c] = Math.abs((f14 - fA3) / f14) + fArr7[c];
                                }
                                fArr6[c] = Math.abs((f14 - f13) / f14) + fArr6[c];
                                fArr7[c] = Math.abs((f14 - fA3) / f14) + fArr7[c];
                            }
                            int i17 = 0;
                            while (true) {
                                if (i17 >= 4) {
                                    z11 = true;
                                    break;
                                } else {
                                    if (fArr7[i17] > fArr6[i17]) {
                                        z11 = false;
                                        break;
                                    }
                                    i17++;
                                }
                            }
                            int i18 = SystemProperties.getInt("debug.miui.display.dbg", 0);
                            if (z11 || (i18 & 128) != 0) {
                                c cVar2 = (c) dVar.f3609b;
                                Objects.requireNonNull(cVar2);
                                try {
                                    IIndividualCallback iIndividualCallback = cVar2.f3606f;
                                    if (iIndividualCallback != null) {
                                        iIndividualCallback.onTrainFinished();
                                        cVar2.b();
                                    } else {
                                        gb.c.i(cVar2.f3602a);
                                        gb.d.f3839a.a(aVar3, "IndividualBrt-MainController", "Can't get callback!", null);
                                        gb.c.c.a(aVar3, "IndividualBrt-MainController", "Can't get callback!", null);
                                    }
                                } catch (RemoteException e14) {
                                    gb.c.i(cVar2.f3602a);
                                    gb.d.f3839a.a(aVar3, "IndividualBrt-MainController", "Notify training result error!", e14);
                                    gb.c.c.a(aVar3, "IndividualBrt-MainController", "Notify training result error!", e14);
                                }
                            }
                            gb.c.i(dVar.f3608a);
                            String str2 = "Model train result defaultMape: " + Arrays.toString(fArr6) + ", modelMape: " + Arrays.toString(fArr7) + ", verify size = " + dVar.f3611e.size();
                            gb.d.f3839a.a(aVar2, "IndividualBrt-ModelController", str2, null);
                            gb.c.c.a(aVar2, "IndividualBrt-ModelController", str2, null);
                        }
                        c cVar3 = (c) dVar.f3609b;
                        Objects.requireNonNull(cVar3);
                        try {
                            int interfaceVersion = InterfaceVersionHelper.getInterfaceVersion("onTrainIndicatorsFinished");
                            IIndividualCallback iIndividualCallback2 = cVar3.f3606f;
                            if (iIndividualCallback2 == null || cVar3.f3605e < interfaceVersion) {
                                gb.c.i(cVar3.f3602a);
                                String str3 = "Can't get callback! Framework version=" + cVar3.f3605e + ", version=" + interfaceVersion;
                                gb.d.f3839a.a(aVar3, "IndividualBrt-MainController", str3, null);
                                gb.c.c.a(aVar3, "IndividualBrt-MainController", str3, null);
                            } else {
                                iIndividualCallback2.onTrainIndicatorsFinished(individualTrainEvent);
                            }
                        } catch (RemoteException e15) {
                            gb.c.i(cVar3.f3602a);
                            gb.d.f3839a.a(aVar3, "IndividualBrt-MainController", "Notify trainIndicators finished error!", e15);
                            gb.c.c.a(aVar3, "IndividualBrt-MainController", "Notify trainIndicators finished error!", e15);
                        }
                    }
                } else {
                    gb.c.i(dVar.f3608a);
                    gb.d.f3839a.a(aVar3, "IndividualBrt-ModelController", "Lite model is not available!", null);
                    gb.c.c.a(aVar3, "IndividualBrt-ModelController", "Lite model is not available!", null);
                    list = listD;
                }
                if (cVar.f3604d >= 50000) {
                    if (BrightnessModelDatabase.p(cVar.f3602a).q().f().f4045b == 2147473647) {
                        cVar.b();
                    } else {
                        cVar.f3604d = 0;
                        for (int i19 = 0; cVar.d(i19).size() >= 50000; i19 = 0) {
                            BrightnessModelDatabase.p(cVar.f3602a).q().d();
                        }
                    }
                }
            }
            gb.c.i(cVar.f3602a);
            String str4 = "Number of all data: " + listD2.size() + ", Number of new data: " + list.size() + ", Count = " + cVar.f3604d;
            gb.d.f3839a.a(aVar2, "IndividualBrt-MainController", str4, null);
            gb.c.c.a(aVar2, "IndividualBrt-MainController", str4, null);
            aVar = this;
        }
        aVar.c.jobFinished(aVar.f4270b, false);
    }
}
