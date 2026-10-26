package com.faceunity.core.controller;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.view.n;
import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import com.google.android.gms.internal.mlkit_language_id_common.n9;
import f2.b;
import fr.a;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import kotlin.jvm.internal.e;
import yq.c;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSingleController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1273a = "KIT_".concat(getClass().getSimpleName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1274b = -99;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1275c = -1;
    public final c d = kotlin.a.a(new fr.a<b>() { // from class: com.faceunity.core.controller.BaseSingleController$mBundleManager$2
        @Override // fr.a
        public final b invoke() {
            return b.g.a();
        }
    });

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f1276e = kotlin.a.a(new fr.a<FURenderBridge>() { // from class: com.faceunity.core.controller.BaseSingleController$mFURenderBridge$2
        @Override // fr.a
        public final FURenderBridge invoke() {
            return FURenderBridge.f1380o.a();
        }
    });
    public final ArrayList f = new ArrayList();
    public final ArrayList g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final LinkedHashMap f1277h = new LinkedHashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f1278i;

    public static final class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BaseSingleController f1279a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper, BaseSingleController singleController) {
            super(looper);
            e.f(singleController, "singleController");
            this.f1279a = singleController;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message msg) {
            e.f(msg, "msg");
            super.handleMessage(msg);
            BaseSingleController baseSingleController = this.f1279a;
            baseSingleController.getClass();
            Object obj = msg.obj;
            e.d(obj, "null cannot be cast to non-null type java.lang.Runnable");
            ((Runnable) obj).run();
            baseSingleController.getClass();
        }
    }

    public static void d(final BaseSingleController baseSingleController) throws InterruptedException {
        Looper looper;
        a aVar = baseSingleController.f1278i;
        final fr.a aVar2 = null;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            fr.a<d> aVar3 = new fr.a<d>() { // from class: com.faceunity.core.controller.BaseSingleController$release$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // fr.a
                public final d invoke() {
                    BaseSingleController baseSingleController2 = this.this$0;
                    baseSingleController2.f1274b = -99L;
                    if (baseSingleController2.f1275c > 0) {
                        a<d> aVar4 = aVar2;
                        if (aVar4 != null) {
                            aVar4.invoke();
                        }
                        this.this$0.b().c(this.this$0.f1275c);
                        this.this$0.f1275c = -1;
                    }
                    countDownLatch.countDown();
                    return d.f18754a;
                }
            };
            Message message = new Message();
            message.what = 1;
            message.obj = new n(aVar3, 9);
            if (baseSingleController.f1278i == null) {
                HandlerThread handlerThread = new HandlerThread("KIT_".concat(baseSingleController.getClass().getSimpleName()));
                handlerThread.start();
                Looper looper2 = handlerThread.getLooper();
                e.e(looper2, "backgroundThread.looper");
                baseSingleController.f1278i = new a(looper2, baseSingleController);
            }
            a aVar4 = baseSingleController.f1278i;
            if (aVar4 != null) {
                aVar4.sendMessage(message);
            }
            countDownLatch.await();
        }
        if (baseSingleController.f1278i == null) {
            baseSingleController.b().c(baseSingleController.f1275c);
            baseSingleController.f1275c = -1;
        }
        a aVar5 = baseSingleController.f1278i;
        if (aVar5 != null && (looper = aVar5.getLooper()) != null) {
            looper.quitSafely();
        }
        baseSingleController.f1278i = null;
    }

    public final void a(j2.a aVar, boolean z, fr.a<d> aVar2) {
        int iE;
        String str = this.f1273a;
        if (aVar != null) {
            StringBuilder sb2 = new StringBuilder("loadBundleFile path: ");
            String str2 = aVar.f11707a;
            sb2.append(str2);
            Log.e(str, sb2.toString());
            iE = b().e(aVar.f11709c, str2);
            n9.J = iE;
            Log.i(str, "mainBundleHandle: " + iE);
        } else {
            iE = 0;
        }
        if (iE <= 0) {
            b().c(this.f1275c);
            this.f1275c = -1;
            StringBuilder sbI = androidx.appcompat.widget.a.i("loadControllerBundle failed handle:", iE, "  path:");
            sbI.append(aVar != null ? aVar.f11707a : null);
            FULogger.b(str, sbI.toString());
            return;
        }
        if (z) {
            Log.e(str, "updateControllerBundle handle: " + iE);
            b bVarB = b();
            int i10 = this.f1275c;
            boolean z10 = this instanceof FaceBeautyController;
            bVarB.getClass();
            FULogger.a("KIT_BundleManager", "bindControllerBundle  oldHandle:" + i10 + "  newHandle:" + iE + " isFaceBeauty:" + z10);
            if (i10 != iE) {
                if (i10 > 0) {
                    bVarB.b(i10);
                    bVarB.h(i10);
                }
                if (iE > 0) {
                    if (z10) {
                        bVarB.g(iE);
                    } else {
                        synchronized (bVarB.d) {
                            if (!bVarB.f10637c.contains(Integer.valueOf(iE))) {
                                bVarB.f10637c.add(Integer.valueOf(iE));
                                bVarB.f10635a = true;
                            }
                            d dVar = d.f18754a;
                        }
                    }
                }
            }
        } else {
            Log.e(str, "destroyControllerBundle");
            b().c(this.f1275c);
        }
        this.f1275c = iE;
        aVar2.invoke();
    }

    public final b b() {
        return (b) this.d.getValue();
    }

    public final void c(LinkedHashMap params) {
        e.f(params, "params");
        if (this.f1275c <= 0) {
            FULogger.b(this.f1273a, androidx.view.a.j(new StringBuilder("setItemParam failed handle:"), this.f1275c, "  "));
            return;
        }
        for (Map.Entry entry : params.entrySet()) {
            final String str = (String) entry.getKey();
            final Object value = entry.getValue();
            ((FURenderBridge) this.f1276e.getValue()).f1383c.add(new fr.a<d>() { // from class: com.faceunity.core.controller.BaseSingleController$itemSetParamGL$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // fr.a
                public final d invoke() {
                    Object obj = value;
                    if (obj instanceof Double) {
                        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                        SDKController.f(this.f1275c, str, ((Number) obj).doubleValue());
                    } else if (obj instanceof String) {
                        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
                        SDKController.g(this.f1275c, str, (String) obj);
                    } else if (obj instanceof double[]) {
                        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$3 = SDKController.f1392a;
                        SDKController.h(this.f1275c, str, (double[]) obj);
                    } else if (obj instanceof Integer) {
                        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$4 = SDKController.f1392a;
                        SDKController.f(this.f1275c, str, ((Number) obj).intValue());
                    } else if (obj instanceof Float) {
                        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$5 = SDKController.f1392a;
                        SDKController.f(this.f1275c, str, ((Number) obj).floatValue());
                    }
                    return d.f18754a;
                }
            });
        }
    }

    public final void e(String key, Object value, long j) {
        e.f(key, "key");
        e.f(value, "value");
        if (j != this.f1274b) {
            return;
        }
        int i10 = this.f1275c;
        if (i10 <= 0) {
            FULogger.b(this.f1273a, androidx.view.a.j(new StringBuilder("setItemParam failed handle:"), this.f1275c, "  "));
            return;
        }
        if (value instanceof Double) {
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            SDKController.f(i10, key, ((Number) value).doubleValue());
            return;
        }
        if (value instanceof String) {
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
            SDKController.g(i10, key, (String) value);
            return;
        }
        if (value instanceof double[]) {
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$3 = SDKController.f1392a;
            SDKController.h(i10, key, (double[]) value);
            return;
        }
        if (!(value instanceof byte[])) {
            if (value instanceof Integer) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$4 = SDKController.f1392a;
                SDKController.f(i10, key, ((Number) value).intValue());
                return;
            } else {
                if (value instanceof Float) {
                    SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$5 = SDKController.f1392a;
                    SDKController.f(i10, key, ((Number) value).floatValue());
                    return;
                }
                return;
            }
        }
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$6 = SDKController.f1392a;
        byte[] bArr = (byte[]) value;
        int length = bArr.length;
        FULogger.a("KIT_SDKController", "fuItemSetParamu8v   item: " + i10 + "    name:" + key + "   value:" + bArr + ".contentToString() size:" + length);
        Faceunity.fuItemSetParamu8v(i10, key, bArr, length);
    }

    public final void f(long j, String key, String value) {
        e.f(key, "key");
        e.f(value, "value");
        if (j != this.f1274b) {
            return;
        }
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        int[] items = b().d();
        e.f(items, "items");
        FULogger.a("KIT_SDKController", "imageBeautySetParam   item: " + items + "    name:" + key + "   value:" + value);
        Faceunity.fuImageBeautySetParam(items, key, value);
    }
}
