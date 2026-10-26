package com.faceunity.core.model;

import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import j2.b;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.e;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSingleModel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j2.a f1331a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1333c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1332b = -1;
    public final ConcurrentHashMap<String, fr.a<d>> d = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f1334e = new a();
    public final boolean f = true;

    public static final class a {
        public a() {
        }
    }

    public BaseSingleModel(j2.a aVar) {
        this.f1331a = aVar;
    }

    public final Serializable a(String str) {
        FaceBeautyController faceBeautyControllerB = b();
        long j = this.f1332b;
        faceBeautyControllerB.getClass();
        if (j != faceBeautyControllerB.f1274b) {
            return Double.valueOf(0.0d);
        }
        int i10 = faceBeautyControllerB.f1275c;
        if (i10 > 0) {
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            return SDKController.e(i10, Double.TYPE, str);
        }
        FULogger.b(faceBeautyControllerB.f1273a, androidx.view.a.j(new StringBuilder("itemGetParam failed handle:"), faceBeautyControllerB.f1275c, "  "));
        return Double.valueOf(-1.0d);
    }

    public abstract FaceBeautyController b();

    public final String c(String str) {
        FaceBeautyController faceBeautyControllerB = b();
        long j = this.f1332b;
        faceBeautyControllerB.getClass();
        if (j != faceBeautyControllerB.f1274b) {
            return "";
        }
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        int[] items = faceBeautyControllerB.b().d();
        e.f(items, "items");
        String strFuImageBeautyGetParam = Faceunity.fuImageBeautyGetParam(items, str);
        FULogger.a("KIT_SDKController", "imageBeautyGetParam   item: " + items + "    name:" + str + " result:" + strFuImageBeautyGetParam);
        return strFuImageBeautyGetParam;
    }

    public final void d(j2.a aVar) {
        FaceBeautyController faceBeautyControllerB = b();
        ((m2.a) this).f14097h = true;
        b bVar = new b(aVar, new LinkedHashMap(), this.f);
        faceBeautyControllerB.getClass();
        System.nanoTime();
        faceBeautyControllerB.h(bVar);
    }

    public final void e(final Object value, final String key) {
        e.f(key, "key");
        e.f(value, "value");
        if (!this.f1333c) {
            b().e(key, value, this.f1332b);
            return;
        }
        ConcurrentHashMap<String, fr.a<d>> concurrentHashMap = this.d;
        final m2.a aVar = (m2.a) this;
        concurrentHashMap.put(key, new fr.a<d>() { // from class: com.faceunity.core.model.BaseSingleModel$updateAttributes$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // fr.a
            public final d invoke() {
                aVar.b().e(key, value, aVar.f1332b);
                return d.f18754a;
            }
        });
    }

    public final void f(final String str, final String value) {
        e.f(value, "value");
        if (!this.f1333c) {
            b().f(this.f1332b, str, value);
            return;
        }
        ConcurrentHashMap<String, fr.a<d>> concurrentHashMap = this.d;
        final m2.a aVar = (m2.a) this;
        concurrentHashMap.put(str, new fr.a<d>() { // from class: com.faceunity.core.model.BaseSingleModel$updateSpeAttributes$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // fr.a
            public final d invoke() {
                aVar.b().f(aVar.f1332b, str, value);
                return d.f18754a;
            }
        });
    }
}
