package com.faceunity.core.biz.impl;

import android.graphics.Bitmap;
import com.faceunity.core.entity.ImageBeautyData;
import com.faceunity.core.enumeration.RenderTypeEnum;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import d2.a;
import f2.b;
import java.util.List;
import yq.c;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public final class IFUCoreVideoImpl implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1264a = kotlin.a.a(new fr.a<FURenderBridge>() { // from class: com.faceunity.core.biz.impl.IFUCoreVideoImpl$mFURenderBridge$2
        @Override // fr.a
        public final FURenderBridge invoke() {
            return FURenderBridge.f1380o.a();
        }
    });

    @Override // d2.a
    public final void a() {
        FURenderKit.d.a();
        FURenderBridge fURenderBridge = (FURenderBridge) this.f1264a.getValue();
        synchronized (fURenderBridge.f1381a) {
            fURenderBridge.g = -1;
            fURenderBridge.f1387k = null;
            fURenderBridge.f1385h = null;
            fURenderBridge.f1386i = -1;
            fURenderBridge.j = -1;
            fURenderBridge.l = null;
            fURenderBridge.m = null;
            fURenderBridge.f1388n = null;
            fURenderBridge.f = 0;
            if (!fURenderBridge.f1383c.isEmpty()) {
                fURenderBridge.f1383c.clear();
            }
            if (!fURenderBridge.d.isEmpty()) {
                fURenderBridge.d.clear();
            }
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            Faceunity.fuReleaseGLResources();
            FULogger.a("KIT_SDKController", "releaseGLResources");
            b.g.a().f();
            FULogger.a("KIT_SDKController", "fuOnCameraChange");
            Faceunity.fuOnCameraChange();
            Faceunity.fuHumanProcessorReset();
            FULogger.a("KIT_SDKController", "fuHumanProcessorReset");
            Faceunity.fuDone();
            FULogger.a("KIT_SDKController", "fuDone");
            Faceunity.fuOnDeviceLost();
            FULogger.a("KIT_SDKController", "fuOnDeviceLost");
            d dVar = d.f18754a;
        }
    }

    @Override // d2.a
    public final Bitmap b(Bitmap bitmap, List list) {
        return null;
    }

    @Override // d2.a
    public final ImageBeautyData c(Bitmap bitmap) {
        return null;
    }

    @Override // d2.a
    public final j2.d d(j2.c cVar) {
        j2.d dVarB;
        FURenderBridge fURenderBridge = (FURenderBridge) this.f1264a.getValue();
        RenderTypeEnum renderTypeEnum = RenderTypeEnum.NORMAL;
        fURenderBridge.getClass();
        synchronized (fURenderBridge.f1381a) {
            FULogger.a("KIT_FURenderBridge", "fuRenderTexture renderVideoWithInput start");
            fURenderBridge.i(cVar);
            Thread.currentThread().getId();
            while (true) {
                List<fr.a<d>> list = fURenderBridge.f1383c;
                if (list == null || list.isEmpty()) {
                    break;
                }
                list.remove(0).invoke();
            }
            dVarB = fURenderBridge.b(cVar, renderTypeEnum);
            while (true) {
                List<fr.a<d>> list2 = fURenderBridge.d;
                if (list2 == null || list2.isEmpty()) {
                    FULogger.a("KIT_FURenderBridge", "fuRenderTexture renderVideoWithInput end");
                } else {
                    list2.remove(0).invoke();
                }
            }
        }
        return dVarB;
    }
}
