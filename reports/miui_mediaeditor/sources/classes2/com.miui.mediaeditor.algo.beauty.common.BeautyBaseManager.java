package com.miui.mediaeditor.algo.beauty.common;

import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.enumeration.FUFaceProcessorDetectModeEnum;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import com.miui.gallery.util.k;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BeautyBaseManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f7367a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final yq.c<? extends a<?, ?>> f7368b = kotlin.a.a(new fr.a<a<?, ?>>() { // from class: com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager$dataSourceLazy$1
        {
            super(0);
        }

        @Override // fr.a
        public final a<?, ?> invoke() {
            return this.this$0.a();
        }
    });

    public abstract a<?, ?> a();

    public final void b() {
        AtomicBoolean atomicBoolean = this.f7367a;
        if (atomicBoolean.get()) {
            kotlinx.coroutines.internal.a.s0("BeautyBaseProcessor", "beauty has initialized");
            return;
        }
        k2.a.d.a();
        k2.a.a(5);
        k2.a.c(1);
        FUFaceProcessorDetectModeEnum fUFaceProcessorDetectModeEnum = FUFaceProcessorDetectModeEnum.IMAGE;
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        int type = fUFaceProcessorDetectModeEnum.getType();
        Faceunity.fuSetFaceProcessorDetectMode(type);
        FULogger.a("KIT_SDKController", "fuSetFaceProcessorDetectMode   mode:" + type);
        int type2 = fUFaceProcessorDetectModeEnum.getType();
        Faceunity.fuSetHumanProcessorDetectMode(type2);
        FULogger.a("KIT_SDKController", "setHumanProcessorDetectMode   mode:" + type2);
        FURenderKit fURenderKitA = FURenderKit.d.a();
        String absolutePath = kotlinx.coroutines.internal.a.D("attribute").getAbsolutePath();
        kotlin.jvm.internal.e.e(absolutePath, "getCachePath(BeautyConst…H_ATTRIBUTE).absolutePath");
        Faceunity.fuImageBeautySetAttributePath(absolutePath);
        FULogger.a("KIT_SDKController", "fuImageBeautySetAttributePath path ".concat(absolutePath));
        String absolutePath2 = kotlinx.coroutines.internal.a.D("faceParams").getAbsolutePath();
        kotlin.jvm.internal.e.e(absolutePath2, "getCachePath(BeautyConst…FACE_PARAMS).absolutePath");
        Faceunity.fuImageBeautySetFaceBeautyPath(absolutePath2);
        FULogger.a("KIT_SDKController", "fuImageBeautySetFaceBeautyPath path ".concat(absolutePath2));
        String absolutePath3 = kotlinx.coroutines.internal.a.D("sdkImageCache").getAbsolutePath();
        kotlin.jvm.internal.e.e(absolutePath3, "getCachePath(BeautyConst…IMAGE_CACHE).absolutePath");
        fURenderKitA.e(absolutePath3);
        c();
        atomicBoolean.set(true);
        kotlinx.coroutines.internal.a.o("BeautyBaseProcessor", "beauty init");
    }

    public abstract void c();

    public void d() {
        AtomicBoolean atomicBoolean = this.f7367a;
        if (atomicBoolean.get()) {
            atomicBoolean.set(false);
            k2.a.d.a().g();
            FURenderKit.a aVar = FURenderKit.d;
            m2.a aVar2 = aVar.a().f1330c;
            if (aVar2 != null) {
                synchronized (aVar2) {
                    k kVar = k.A;
                    synchronized (kVar) {
                        kVar.t(aVar2, true);
                        kVar.s(aVar2);
                    }
                }
            }
            FaceBeautyController faceBeautyController = (FaceBeautyController) aVar.a().g().f1382b.getValue();
            f2.b bVarB = faceBeautyController.b();
            ArrayList arrayList = faceBeautyController.f;
            int[] iArrW0 = r.W0(arrayList);
            bVarB.getClass();
            for (int i10 : iArrW0) {
                if (i10 > 0) {
                    bVarB.b(i10);
                }
            }
            arrayList.clear();
            faceBeautyController.g.clear();
            faceBeautyController.f1277h.clear();
            aVar.a().f().a(0).a();
            kotlinx.coroutines.internal.a.o("BeautyBaseProcessor", "beauty release");
        }
    }
}
