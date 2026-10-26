package com.faceunity.core.renderer.gltexturerender;

import android.graphics.Bitmap;
import android.util.Log;
import com.faceunity.core.cache.CacheData;
import com.faceunity.core.entity.ImageBeautyData;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import com.miui.camerainfra.cloudconfig.data.DataManager;
import fr.l;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import kotlin.jvm.internal.e;
import kotlin.text.i;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f1371e;
    public final /* synthetic */ Object m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f1372n;

    public /* synthetic */ c(d dVar, boolean z, l lVar) {
        this.m = dVar;
        this.f1371e = z;
        this.f1372n = lVar;
    }

    public /* synthetic */ c(DataManager dataManager, String str, boolean z) {
        this.m = dataManager;
        this.f1372n = str;
        this.f1371e = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long jCurrentTimeMillis;
        ImageBeautyData imageBeautyDataH;
        String strC;
        switch (this.d) {
            case 0:
                final d this$0 = (d) this.m;
                boolean z = this.f1371e;
                l cacheDataCallback = (l) this.f1372n;
                e.f(this$0, "this$0");
                e.f(cacheDataCallback, "$cacheDataCallback");
                Log.d(this$0.j, "saveCacheData begin: ");
                synchronized (this$0.R) {
                    jCurrentTimeMillis = System.currentTimeMillis();
                    Log.d(this$0.j, "saveCacheData: start: " + jCurrentTimeMillis);
                    m2.a aVar = this$0.c().f1330c;
                    if (aVar != null && aVar.f14097h) {
                        aVar.e(0, "image_beauty_manual_type");
                    }
                    m2.a aVar2 = this$0.c().f1330c;
                    if (aVar2 != null) {
                        aVar2.m();
                    }
                    m2.a aVar3 = this$0.c().f1330c;
                    if (aVar3 != null) {
                        aVar3.f14097h = false;
                        aVar3.W = 1.0d;
                        String strC2 = aVar3.c("image_manual_acen_data");
                        if (strC2 != null && (!i.l0(strC2)) && !e.a(strC2, "no data")) {
                            aVar3.f14097h = false;
                            aVar3.U(strC2);
                            aVar3.f14097h = true;
                        }
                        String strC3 = aVar3.c("image_manual_blur_data");
                        if (strC3 != null && (!i.l0(strC3)) && !e.a(strC3, "no data")) {
                            aVar3.f14097h = false;
                            aVar3.V(strC3);
                            aVar3.f14097h = true;
                        }
                        String strC4 = aVar3.c("image_manual_wrikle_data");
                        if (strC4 != null && (!i.l0(strC4)) && !e.a(strC4, "no data")) {
                            aVar3.f14097h = false;
                            aVar3.X(strC4);
                            aVar3.f14097h = true;
                        }
                        aVar3.k0();
                        aVar3.f14097h = true;
                    }
                    m2.a aVar4 = this$0.c().f1330c;
                    if (aVar4 != null && (strC = aVar4.c("image_face_data")) != null) {
                        aVar4.f14097h = false;
                        aVar4.f14109o0 = strC;
                        aVar4.f14097h = true;
                    }
                    Log.d(this$0.j, "saveCacheData: buildImageBuildData");
                    m2.a aVar5 = this$0.c().f1330c;
                    imageBeautyDataH = aVar5 != null ? aVar5.h() : null;
                    yq.d dVar = yq.d.f18754a;
                }
                Log.d(this$0.j, "saveCacheData: imageBeautyGetResult start");
                if (z) {
                    synchronized (this$0) {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        this$0.c().a(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.GLTexturePhotoRendererNoCache$imageBeautyGetResult$1
                            {
                                super(0);
                            }

                            @Override // fr.a
                            public final yq.d invoke() {
                                Log.d(this$0.j, "imageBeautyGetResult: addBeforeRenderGLThreadAction");
                                FURenderKit fURenderKitC = this$0.c();
                                j2.c fuRenderInputData = this$0.v;
                                fURenderKitC.getClass();
                                e.f(fuRenderInputData, "fuRenderInputData");
                                fURenderKitC.g().getClass();
                                FURenderBridge.d(fuRenderInputData);
                                return yq.d.f18754a;
                            }
                        });
                        this$0.c().b(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.GLTexturePhotoRendererNoCache$imageBeautyGetResult$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // fr.a
                            public final yq.d invoke() {
                                Log.d(this$0.j, "imageBeautyGetResult: doAfterRenderGLThreadAction");
                                countDownLatch.countDown();
                                return yq.d.f18754a;
                            }
                        });
                        this$0.i(false);
                        Log.d(this$0.j, "requestRender");
                        countDownLatch.await();
                        Log.d(this$0.j, "imageBeautyGetResult");
                        j2.c.a aVar6 = this$0.v.f11717e;
                    }
                } else {
                    synchronized (this$0) {
                        final CountDownLatch countDownLatch2 = new CountDownLatch(1);
                        this$0.c().a(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.GLTexturePhotoRendererNoCache$imageBeautyGetCurrentResult$1
                            {
                                super(0);
                            }

                            @Override // fr.a
                            public final yq.d invoke() {
                                j2.c.a aVar7;
                                String string;
                                Log.d(this$0.j, "imageBeautyGetCurrentResult: addBeforeRenderGLThreadAction");
                                FURenderKit fURenderKitC = this$0.c();
                                j2.c fuRenderInputData = this$0.v;
                                fURenderKitC.getClass();
                                e.f(fuRenderInputData, "fuRenderInputData");
                                fURenderKitC.g().getClass();
                                j2.c.a aVar8 = fuRenderInputData.f11717e;
                                if (aVar8 != null && FURenderBridge.c.f1389a[aVar8.f11718a.ordinal()] == 2 && (aVar7 = fuRenderInputData.f11717e) != null) {
                                    SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                                    int i10 = fuRenderInputData.f11714a;
                                    int i11 = fuRenderInputData.f11715b;
                                    int[] iArrD = f2.b.g.a().d();
                                    Bitmap bitmap = Faceunity.fuImageBeautyGetCurrentResultBitmap(i10, i11, iArrD);
                                    StringBuilder sbF = a0.a.f("fuImageBeautyGetCurrentResultBitmap width:", i10, "   height:", i11, "   items:");
                                    if (iArrD != null) {
                                        string = Arrays.toString(iArrD);
                                        e.e(string, "toString(this)");
                                    } else {
                                        string = null;
                                    }
                                    sbF.append(string);
                                    FULogger.a("KIT_SDKController", sbF.toString());
                                    e.e(bitmap, "bitmap");
                                    aVar7.f11721e = bitmap;
                                }
                                return yq.d.f18754a;
                            }
                        });
                        this$0.c().b(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.GLTexturePhotoRendererNoCache$imageBeautyGetCurrentResult$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // fr.a
                            public final yq.d invoke() {
                                Log.d(this$0.j, "imageBeautyGetCurrentResult: doAfterRenderGLThreadAction");
                                countDownLatch2.countDown();
                                return yq.d.f18754a;
                            }
                        });
                        this$0.i(false);
                        countDownLatch2.await();
                        Log.d(this$0.j, "imageBeautyGetCurrentResult");
                        j2.c.a aVar7 = this$0.v.f11717e;
                    }
                }
                Log.d(this$0.j, "saveCacheData: imageBeautyGetResult end");
                j2.c.a aVar8 = this$0.v.f11717e;
                CacheData.a aVar9 = aVar8 != null ? new CacheData.a(aVar8.f11718a, aVar8.f11719b, aVar8.f11720c, aVar8.d, aVar8.f11721e) : null;
                Log.d(this$0.j, "cacheDataCallback");
                cacheDataCallback.invoke(new CacheData(CacheData.CacheType.IMAGE, imageBeautyDataH, aVar9, this$0.v.f11716c));
                Log.d(this$0.j, "saveCacheData: cost: " + (System.currentTimeMillis() - jCurrentTimeMillis));
                return;
            default:
                DataManager this$1 = (DataManager) this.m;
                String module = (String) this.f1372n;
                boolean z10 = this.f1371e;
                e.f(this$1, "this$0");
                e.f(module, "$module");
                try {
                    this$1.c(module, z10);
                    return;
                } catch (Throwable th2) {
                    ng.a.h(th2);
                    return;
                }
        }
    }
}
