package com.faceunity.core.faceunity;

import android.graphics.Bitmap;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.view.l;
import c2.a;
import com.faceunity.core.biz.FUCoreBizApiProxy;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import j2.b;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.e;
import yq.c;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public final class FURenderKit {
    public static final a d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile FURenderKit f1327e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1328a = kotlin.a.a(new fr.a<FURenderBridge>() { // from class: com.faceunity.core.faceunity.FURenderKit$mFURenderBridge$2
        @Override // fr.a
        public final FURenderBridge invoke() {
            return FURenderBridge.f1380o.a();
        }
    });

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1329b = kotlin.a.a(new fr.a<FUCoreBizApiProxy>() { // from class: com.faceunity.core.faceunity.FURenderKit$mFUCoreBiz$2
        @Override // fr.a
        public final FUCoreBizApiProxy invoke() {
            a.C0029a c0029a = a.f707a;
            FUCoreBizApiProxy fUCoreBizApiProxy = a.f708b;
            if (fUCoreBizApiProxy == null) {
                synchronized (c0029a) {
                    fUCoreBizApiProxy = a.f708b;
                    if (fUCoreBizApiProxy == null) {
                        fUCoreBizApiProxy = new FUCoreBizApiProxy();
                        a.f708b = fUCoreBizApiProxy;
                    }
                }
            }
            return fUCoreBizApiProxy;
        }
    });

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m2.a f1330c;

    public static final class a {
        public final FURenderKit a() {
            if (FURenderKit.f1327e == null) {
                synchronized (this) {
                    if (FURenderKit.f1327e == null) {
                        FURenderKit.f1327e = new FURenderKit();
                    }
                    d dVar = d.f18754a;
                }
            }
            FURenderKit fURenderKit = FURenderKit.f1327e;
            e.c(fURenderKit);
            return fURenderKit;
        }
    }

    public final void a(fr.a<d> aVar) {
        g().f1383c.add(aVar);
    }

    public final void b(fr.a<d> aVar) {
        g().d.add(aVar);
    }

    public final void c(fr.a<d> aVar) {
        FURenderBridge fURenderBridgeG = g();
        fURenderBridgeG.getClass();
        Message message = new Message();
        message.what = 998;
        message.obj = new l(aVar, 9);
        if (fURenderBridgeG.f1384e == null) {
            HandlerThread handlerThread = new HandlerThread("KIT_".concat(FURenderBridge.class.getSimpleName()));
            handlerThread.start();
            Looper looper = handlerThread.getLooper();
            e.e(looper, "backgroundThread.looper");
            fURenderBridgeG.f1384e = new FURenderBridge.b(looper);
        }
        FURenderBridge.b bVar = fURenderBridgeG.f1384e;
        if (bVar != null) {
            bVar.sendMessage(message);
        }
    }

    public final int d(j2.c cVar) {
        j2.c.a aVar = cVar.f11717e;
        if (aVar != null) {
            int iOrdinal = aVar.f11718a.ordinal();
            if (iOrdinal == 0) {
                j2.c.a aVar2 = cVar.f11717e;
                e.c(aVar2);
                int type = aVar2.f11718a.getType();
                int i10 = cVar.f11714a;
                int i11 = cVar.f11715b;
                j2.c.a aVar3 = cVar.f11717e;
                byte[] bArr = aVar3 != null ? aVar3.f11719b : null;
                e.c(bArr);
                int i12 = cVar.f11714a * 4;
                g().getClass();
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                int iFuImageBeautyCreateTexture = Faceunity.fuImageBeautyCreateTexture(type, i10, i11, bArr, i12, null, 0, null, 0, -1.0f);
                StringBuilder sbF = a0.a.f("fuImageBeautyCreateTexture format:", type, " width:", i10, "   height:");
                sbF.append(i11);
                sbF.append(" id ");
                sbF.append(iFuImageBeautyCreateTexture);
                FULogger.a("KIT_SDKController", sbF.toString());
                return iFuImageBeautyCreateTexture;
            }
            if (iOrdinal == 2) {
                j2.c.a aVar4 = cVar.f11717e;
                Bitmap bitmap = aVar4 != null ? aVar4.f11721e : null;
                e.c(bitmap);
                g().getClass();
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
                int iFuImageBeautyCreateTextureWithBitmap = Faceunity.fuImageBeautyCreateTextureWithBitmap(bitmap, -1.0f);
                FULogger.a("KIT_SDKController", "fuImageBeautyCreateTextureWithBitmap format:" + bitmap + " id " + iFuImageBeautyCreateTextureWithBitmap);
                return iFuImageBeautyCreateTextureWithBitmap;
            }
        }
        return 0;
    }

    public final void e(String path) {
        e.f(path, "path");
        g().getClass();
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuImageBeautySetCacheDir(path);
        FULogger.a("KIT_SDKController", "fuImageBeautySetCacheDir path: ".concat(path));
    }

    public final FUCoreBizApiProxy f() {
        return (FUCoreBizApiProxy) this.f1329b.getValue();
    }

    public final FURenderBridge g() {
        return (FURenderBridge) this.f1328a.getValue();
    }

    public final void h(m2.a aVar) throws InterruptedException {
        if (e.a(this.f1330c, aVar)) {
            return;
        }
        this.f1330c = aVar;
        if (aVar == null) {
            BaseSingleController.d((FaceBeautyController) g().f1382b.getValue());
            return;
        }
        aVar.f1333c = true;
        FaceBeautyController faceBeautyControllerB = aVar.b();
        aVar.f14097h = true;
        b bVar = new b(aVar.f1331a, new LinkedHashMap(), aVar.f);
        faceBeautyControllerB.getClass();
        BaseSingleController.a aVar2 = faceBeautyControllerB.f1278i;
        if (aVar2 != null) {
            aVar2.removeMessages(999);
        }
        long jNanoTime = System.nanoTime();
        faceBeautyControllerB.f1274b = jNanoTime;
        faceBeautyControllerB.g(bVar);
        BaseSingleModel.a aVar3 = aVar.f1334e;
        if (aVar3 != null) {
            BaseSingleModel baseSingleModel = BaseSingleModel.this;
            baseSingleModel.f1332b = jNanoTime;
            ConcurrentHashMap<String, fr.a<d>> concurrentHashMap = baseSingleModel.d;
            for (Map.Entry<String, fr.a<d>> entry : concurrentHashMap.entrySet()) {
                e.e(entry, "iterator.next()");
                Map.Entry<String, fr.a<d>> entry2 = entry;
                concurrentHashMap.remove(entry2.getKey());
                entry2.getValue().invoke();
            }
            baseSingleModel.f1333c = false;
        }
        g().getClass();
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuProcessorChangeModel(1);
        FULogger.a("KIT_SDKController", "fuProcessorChangeModel");
    }
}
