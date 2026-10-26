package com.faceunity.core.biz.impl;

import android.graphics.Bitmap;
import com.faceunity.core.cache.CacheData;
import com.faceunity.core.entity.ImageBeautyData;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.ProcessMode;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import d2.a;
import f2.b;
import j2.d;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.e;
import yq.c;

/* JADX INFO: loaded from: classes.dex */
public final class IFUCoreEditorImpl implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1263a = kotlin.a.a(new fr.a<FURenderBridge>() { // from class: com.faceunity.core.biz.impl.IFUCoreEditorImpl$mFURenderBridge$2
        @Override // fr.a
        public final FURenderBridge invoke() {
            return FURenderBridge.f1380o.a();
        }
    });

    @Override // d2.a
    public final void a() {
        FURenderKit.d.a();
        ((FURenderBridge) this.f1263a.getValue()).g();
    }

    @Override // d2.a
    public final Bitmap b(Bitmap bitmap, List list) {
        String string;
        String string2;
        Bitmap bitmap2;
        Bitmap bitmap3;
        if (list.isEmpty()) {
            return bitmap;
        }
        j2.c cVar = new j2.c(bitmap.getWidth(), bitmap.getHeight());
        j2.c.a aVar = new j2.c.a(FUInputBufferEnum.FU_FORMAT_BITMAP, 30);
        cVar.f11717e = aVar;
        aVar.f11721e = bitmap;
        cVar.f11714a = bitmap.getWidth();
        cVar.f11715b = bitmap.getHeight();
        int size = list.size();
        int i10 = 0;
        while (true) {
            Bitmap bitmapCreateScaledBitmap = null;
            if (i10 >= size) {
                break;
            }
            cVar.d = i10 == 0;
            ProcessMode processMode = ((CacheData) list.get(i10)).f1267c;
            e.f(processMode, "<set-?>");
            cVar.f11716c = processMode;
            ((CacheData) list.get(i10)).getClass();
            c cVar2 = this.f1263a;
            ((FURenderBridge) cVar2.getValue()).getClass();
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            int[] iArrD = b.g.a().d();
            int[] result = Faceunity.fuImageBeautyGetResultSize(iArrD);
            StringBuilder sb2 = new StringBuilder("fuImageBeautyGetResultBitmap result:");
            if (result != null) {
                string = Arrays.toString(result);
                e.e(string, "toString(this)");
            } else {
                string = null;
            }
            sb2.append(string);
            sb2.append(" items:");
            if (iArrD != null) {
                string2 = Arrays.toString(iArrD);
                e.e(string2, "toString(this)");
            } else {
                string2 = null;
            }
            sb2.append(string2);
            FULogger.a("KIT_SDKController", sb2.toString());
            e.e(result, "result");
            cVar.f11714a = result[0];
            cVar.f11715b = result[1];
            j2.c.a aVar2 = cVar.f11717e;
            if (aVar2 != null && (bitmap2 = aVar2.f11721e) != null) {
                if (bitmap2.getHeight() * bitmap2.getWidth() < cVar.f11714a * cVar.f11715b || !bitmap2.isMutable()) {
                    j2.c.a aVar3 = cVar.f11717e;
                    if (aVar3 != null) {
                        Bitmap bitmap4 = aVar3.f11721e;
                        if (bitmap4 != null) {
                            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap4, cVar.f11714a, cVar.f11715b, true);
                            e.b(bitmapCreateScaledBitmap, "Bitmap.createScaledBitma…s, width, height, filter)");
                        }
                        aVar3.f11721e = bitmapCreateScaledBitmap;
                    }
                } else {
                    j2.c.a aVar4 = cVar.f11717e;
                    if (aVar4 != null && (bitmap3 = aVar4.f11721e) != null) {
                        bitmap3.reconfigure(cVar.f11714a, cVar.f11715b, bitmap3.getConfig());
                    }
                }
            }
            ((FURenderBridge) cVar2.getValue()).getClass();
            FURenderBridge.e(cVar);
            i10++;
        }
        j2.c.a aVar5 = cVar.f11717e;
        if (aVar5 != null) {
            return aVar5.f11721e;
        }
        return null;
    }

    @Override // d2.a
    public final ImageBeautyData c(Bitmap bitmap) {
        return null;
    }

    @Override // d2.a
    public final d d(j2.c cVar) {
        d dVarC;
        synchronized (((FURenderBridge) this.f1263a.getValue()).f1381a) {
            Thread.currentThread().getId();
            dVarC = FURenderBridge.c(cVar);
        }
        return dVarC;
    }
}
