package com.faceunity.core.biz.impl;

import android.graphics.Bitmap;
import android.util.Log;
import com.faceunity.core.cache.CacheData;
import com.faceunity.core.entity.ImageBeautyData;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.ProcessMode;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.model.facebeauty.FaceBeautySmartStyleEnum;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import com.google.android.gms.internal.mlkit_language_id_common.n9;
import com.miui.gallery.util.k;
import d2.a;
import e2.b;
import j2.d;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.h;
import yq.c;

/* JADX INFO: loaded from: classes.dex */
public final class IFUCoreBeautyImpl implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1260a = h.a(IFUCoreBeautyImpl.class).d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1261b = kotlin.a.a(new fr.a<FURenderBridge>() { // from class: com.faceunity.core.biz.impl.IFUCoreBeautyImpl$mFURenderBridge$2
        @Override // fr.a
        public final FURenderBridge invoke() {
            return FURenderBridge.f1380o.a();
        }
    });

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f1262c = kotlin.a.a(new fr.a<b>() { // from class: com.faceunity.core.biz.impl.IFUCoreBeautyImpl$mImageBeautyDataFactory$2
        @Override // fr.a
        public final b invoke() {
            return new b();
        }
    });

    @Override // d2.a
    public final void a() {
        FURenderKit.a aVar = FURenderKit.d;
        if (aVar.a().f1330c != null) {
            aVar.a().h(null);
        }
        e().g();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // d2.a
    public final Bitmap b(Bitmap bitmap, List list) {
        FURenderKit.a aVar;
        String str;
        int i10;
        int i11 = 1;
        if (!(!list.isEmpty())) {
            return bitmap;
        }
        j2.c cVar = new j2.c(bitmap.getWidth(), bitmap.getHeight());
        j2.c.a aVar2 = new j2.c.a(FUInputBufferEnum.FU_FORMAT_BITMAP, 30);
        cVar.f11717e = aVar2;
        aVar2.f11721e = bitmap;
        cVar.f11714a = bitmap.getWidth();
        cVar.f11715b = bitmap.getHeight();
        int size = list.size();
        int i12 = 0;
        while (true) {
            aVar = FURenderKit.d;
            if (i12 >= size) {
                break;
            }
            cVar.d = i12 == 0 ? i11 : 0;
            ProcessMode processMode = ((CacheData) list.get(i12)).f1267c;
            e.f(processMode, "<set-?>");
            cVar.f11716c = processMode;
            m2.a aVar3 = aVar.a().f1330c;
            if (aVar3 != null) {
                ImageBeautyData imageBeautyData = ((CacheData) list.get(i12)).f1265a;
                if (imageBeautyData == null || (str = imageBeautyData.f1284e) == null) {
                    str = "";
                }
                aVar3.f14109o0 = str;
                if (aVar3.f14097h) {
                    aVar3.f("image_face_data", str);
                }
                e().getClass();
                FURenderBridge.h(cVar);
                if (aVar3.f14097h) {
                    aVar3.e(Double.valueOf(1.0d), "image_do_perpare_wrinkle");
                }
                k2.a.d.a();
                aVar3.w(k2.a.d());
                k kVar = k.A;
                synchronized (kVar) {
                    kVar.t(aVar3, false);
                    kVar.s(aVar3);
                }
                ImageBeautyData imageBeautyData2 = ((CacheData) list.get(i12)).f1265a;
                if (imageBeautyData2 != null) {
                    if ((n9.I == 0 ? i11 : 0) == 0 && (i10 = imageBeautyData2.f1307x0) > 0) {
                        int[] iArr = new int[i11];
                        iArr[0] = imageBeautyData2.f1309y0;
                        SDKController.b(i10, iArr);
                    }
                    if (!e.a(aVar3.j, imageBeautyData2.m)) {
                        aVar3.y(imageBeautyData2.m);
                    }
                    double d = aVar3.f14102k;
                    double d10 = imageBeautyData2.f1294n;
                    if ((d == d10 ? i11 : 0) == 0) {
                        aVar3.x(d10);
                    }
                    int i13 = aVar3.f14099i;
                    if (i11 <= i13) {
                        int i14 = i11;
                        while (true) {
                            if ((aVar3.l(i14, aVar3.l) == imageBeautyData2.a(i14, imageBeautyData2.r) ? i11 : 0) == 0) {
                                aVar3.B(imageBeautyData2.a(i14, imageBeautyData2.r), i14);
                            }
                            if (i14 == i13) {
                                break;
                            }
                            i14++;
                        }
                    }
                    int i15 = aVar3.f14099i;
                    if (i11 <= i15) {
                        int i16 = i11;
                        while (true) {
                            if ((aVar3.l(i16, aVar3.m) == imageBeautyData2.a(i16, imageBeautyData2.s) ? i11 : 0) == 0) {
                                aVar3.A(imageBeautyData2.a(i16, imageBeautyData2.s), i16);
                            }
                            if (i16 == i15) {
                                break;
                            }
                            i16++;
                        }
                    }
                    int i17 = aVar3.f14099i;
                    if (i11 <= i17) {
                        int i18 = i11;
                        while (true) {
                            if ((aVar3.l(i18, aVar3.f14106n) == imageBeautyData2.a(i18, imageBeautyData2.v) ? i11 : 0) == 0) {
                                aVar3.h0(i18, "image_white", aVar3.f14106n, imageBeautyData2.a(i18, imageBeautyData2.v));
                            }
                            if (i18 == i17) {
                                break;
                            }
                            i18++;
                        }
                    }
                    int i19 = aVar3.f14099i;
                    if (i11 <= i19) {
                        int i20 = i11;
                        while (true) {
                            if ((aVar3.l(i20, aVar3.f14108o) == imageBeautyData2.a(i20, imageBeautyData2.f1304w) ? i11 : 0) == 0) {
                                aVar3.K(imageBeautyData2.a(i20, imageBeautyData2.f1304w), i20);
                            }
                            if (i20 == i19) {
                                break;
                            }
                            i20++;
                        }
                    }
                    int i21 = aVar3.f14099i;
                    if (i11 <= i21) {
                        int i22 = i11;
                        while (true) {
                            if ((aVar3.l(i22, aVar3.p) == imageBeautyData2.a(i22, imageBeautyData2.f1306x) ? i11 : 0) == 0) {
                                aVar3.g0(imageBeautyData2.a(i22, imageBeautyData2.f1306x), i22);
                            }
                            if (i22 == i21) {
                                break;
                            }
                            i22++;
                        }
                    }
                    int i23 = aVar3.f14099i;
                    if (i11 <= i23) {
                        int i24 = i11;
                        while (true) {
                            if ((aVar3.l(i24, aVar3.s) == imageBeautyData2.a(i24, imageBeautyData2.f1308y) ? i11 : 0) == 0) {
                                aVar3.d0(imageBeautyData2.a(i24, imageBeautyData2.f1308y), i24);
                            }
                            if (i24 == i23) {
                                break;
                            }
                            i24++;
                        }
                    }
                    int i25 = aVar3.f14099i;
                    if (i11 <= i25) {
                        int i26 = i11;
                        while (true) {
                            if ((aVar3.l(i26, aVar3.f14111t) == imageBeautyData2.a(i26, imageBeautyData2.z) ? i11 : 0) == 0) {
                                aVar3.z(imageBeautyData2.a(i26, imageBeautyData2.z), i26);
                            }
                            if (i26 == i25) {
                                break;
                            }
                            i26++;
                        }
                    }
                    int i27 = aVar3.f14099i;
                    if (i11 <= i27) {
                        int i28 = i11;
                        while (true) {
                            if ((aVar3.l(i28, aVar3.f14112u) == imageBeautyData2.a(i28, imageBeautyData2.A) ? i11 : 0) == 0) {
                                aVar3.J(imageBeautyData2.a(i28, imageBeautyData2.A), i28);
                            }
                            if (i28 == i27) {
                                break;
                            }
                            i28++;
                        }
                    }
                    int i29 = aVar3.f14099i;
                    if (i11 <= i29) {
                        int i30 = i11;
                        while (true) {
                            if ((aVar3.l(i30, aVar3.v) == imageBeautyData2.a(i30, imageBeautyData2.D) ? i11 : 0) == 0) {
                                aVar3.h0(i30, "image_wrinkle", aVar3.v, imageBeautyData2.a(i30, imageBeautyData2.D));
                            }
                            if (i30 == i29) {
                                break;
                            }
                            i30++;
                        }
                    }
                    int i31 = aVar3.f14099i;
                    if (i11 <= i31) {
                        int i32 = i11;
                        while (true) {
                            if ((aVar3.l(i32, aVar3.f14113w) == imageBeautyData2.a(i32, imageBeautyData2.E) ? i11 : 0) == 0) {
                                aVar3.b0(imageBeautyData2.a(i32, imageBeautyData2.E), i32);
                            }
                            if (i32 == i31) {
                                break;
                            }
                            i32++;
                        }
                    }
                    int i33 = aVar3.f14099i;
                    if (i11 <= i33) {
                        int i34 = i11;
                        while (true) {
                            if ((aVar3.l(i34, aVar3.f14114x) == imageBeautyData2.a(i34, imageBeautyData2.F) ? i11 : 0) == 0) {
                                aVar3.c0(imageBeautyData2.a(i34, imageBeautyData2.F), i34);
                            }
                            if (i34 == i33) {
                                break;
                            }
                            i34++;
                        }
                    }
                    int i35 = aVar3.f14099i;
                    if (i11 <= i35) {
                        int i36 = i11;
                        while (true) {
                            if ((aVar3.l(i36, aVar3.f14115y) == imageBeautyData2.a(i36, imageBeautyData2.G) ? i11 : 0) == 0) {
                                aVar3.e0(imageBeautyData2.a(i36, imageBeautyData2.G), i36);
                            }
                            if (i36 == i35) {
                                break;
                            }
                            i36++;
                        }
                    }
                    int i37 = aVar3.f14099i;
                    if (i11 <= i37) {
                        int i38 = i11;
                        while (true) {
                            if ((aVar3.l(i38, aVar3.z) == imageBeautyData2.a(i38, imageBeautyData2.H) ? i11 : 0) == 0) {
                                aVar3.h0(i38, "image_warmcold", aVar3.z, imageBeautyData2.a(i38, imageBeautyData2.H));
                            }
                            if (i38 == i37) {
                                break;
                            }
                            i38++;
                        }
                    }
                    int i39 = aVar3.f14099i;
                    if (i11 <= i39) {
                        int i40 = i11;
                        while (true) {
                            if ((aVar3.l(i40, aVar3.A) == imageBeautyData2.a(i40, imageBeautyData2.I) ? i11 : 0) == 0) {
                                aVar3.L(imageBeautyData2.a(i40, imageBeautyData2.I), i40);
                            }
                            if (i40 == i39) {
                                break;
                            }
                            i40++;
                        }
                    }
                    int i41 = aVar3.f14099i;
                    if (i11 <= i41) {
                        int i42 = i11;
                        while (true) {
                            if ((aVar3.l(i42, aVar3.B) == imageBeautyData2.a(i42, imageBeautyData2.J) ? i11 : 0) == 0) {
                                aVar3.M(imageBeautyData2.a(i42, imageBeautyData2.J), i42);
                            }
                            if (i42 == i41) {
                                break;
                            }
                            i42++;
                        }
                    }
                    int i43 = aVar3.f14099i;
                    if (i11 <= i43) {
                        int i44 = i11;
                        while (true) {
                            if ((aVar3.l(i44, aVar3.r) == imageBeautyData2.a(i44, imageBeautyData2.K) ? i11 : 0) == 0) {
                                aVar3.h0(i44, "image_makeup", aVar3.r, imageBeautyData2.a(i44, imageBeautyData2.K));
                            }
                            if (i44 == i43) {
                                break;
                            }
                            i44++;
                        }
                    }
                    int i45 = aVar3.f14099i;
                    if (i11 <= i45) {
                        int i46 = i11;
                        while (true) {
                            if ((aVar3.l(i46, aVar3.f14110q) == imageBeautyData2.a(i46, imageBeautyData2.L) ? i11 : 0) == 0) {
                                aVar3.h0(i46, "image_threed_type", aVar3.f14110q, imageBeautyData2.a(i46, imageBeautyData2.L));
                            }
                            if (i46 == i45) {
                                break;
                            }
                            i46++;
                        }
                    }
                    int i47 = aVar3.f14099i;
                    if (i11 <= i47) {
                        int i48 = i11;
                        while (true) {
                            if ((aVar3.l(i48, aVar3.C) == imageBeautyData2.a(i48, imageBeautyData2.M) ? i11 : 0) == 0) {
                                aVar3.F(imageBeautyData2.a(i48, imageBeautyData2.M), i48);
                            }
                            if (i48 == i47) {
                                break;
                            }
                            i48++;
                        }
                    }
                    int i49 = aVar3.f14099i;
                    if (i11 <= i49) {
                        int i50 = i11;
                        while (true) {
                            if ((aVar3.l(i50, aVar3.F) == imageBeautyData2.a(i50, imageBeautyData2.P) ? i11 : 0) == 0) {
                                aVar3.E(imageBeautyData2.a(i50, imageBeautyData2.P), i50);
                            }
                            if (i50 == i49) {
                                break;
                            }
                            i50++;
                        }
                    }
                    int i51 = aVar3.f14099i;
                    if (i11 <= i51) {
                        int i52 = i11;
                        while (true) {
                            if ((aVar3.l(i52, aVar3.D) == imageBeautyData2.a(i52, imageBeautyData2.N) ? i11 : 0) == 0) {
                                aVar3.G(imageBeautyData2.a(i52, imageBeautyData2.N), i52);
                            }
                            if (i52 == i51) {
                                break;
                            }
                            i52++;
                        }
                    }
                    int i53 = aVar3.f14099i;
                    if (i11 <= i53) {
                        int i54 = i11;
                        while (true) {
                            if ((aVar3.l(i54, aVar3.E) == imageBeautyData2.a(i54, imageBeautyData2.O) ? i11 : 0) == 0) {
                                aVar3.D(imageBeautyData2.a(i54, imageBeautyData2.O), i54);
                            }
                            if (i54 == i53) {
                                break;
                            }
                            i54++;
                        }
                    }
                    int i55 = aVar3.f14099i;
                    if (i11 <= i55) {
                        int i56 = i11;
                        while (true) {
                            if ((aVar3.l(i56, aVar3.H) == imageBeautyData2.a(i56, imageBeautyData2.R) ? i11 : 0) == 0) {
                                aVar3.I(imageBeautyData2.a(i56, imageBeautyData2.R), i56);
                            }
                            if (i56 == i55) {
                                break;
                            }
                            i56++;
                        }
                    }
                    int i57 = aVar3.f14099i;
                    if (i11 <= i57) {
                        int i58 = i11;
                        while (true) {
                            if ((aVar3.l(i58, aVar3.I) == imageBeautyData2.a(i58, imageBeautyData2.S) ? i11 : 0) == 0) {
                                aVar3.Q(imageBeautyData2.a(i58, imageBeautyData2.S), i58);
                            }
                            if (i58 == i57) {
                                break;
                            }
                            i58++;
                        }
                    }
                    int i59 = aVar3.f14099i;
                    if (i11 <= i59) {
                        int i60 = i11;
                        while (true) {
                            if ((aVar3.l(i60, aVar3.Q) == imageBeautyData2.a(i60, imageBeautyData2.f1280a0) ? i11 : 0) == 0) {
                                aVar3.f0(imageBeautyData2.a(i60, imageBeautyData2.f1280a0), i60);
                            }
                            if (i60 == i59) {
                                break;
                            }
                            i60++;
                        }
                    }
                    int i61 = aVar3.f14099i;
                    if (i11 <= i61) {
                        int i62 = i11;
                        while (true) {
                            if ((aVar3.l(i62, aVar3.R) == imageBeautyData2.a(i62, imageBeautyData2.f1281b0) ? i11 : 0) == 0) {
                                aVar3.H(imageBeautyData2.a(i62, imageBeautyData2.f1281b0), i62);
                            }
                            if (i62 == i61) {
                                break;
                            }
                            i62++;
                        }
                    }
                    int i63 = aVar3.f14099i;
                    if (i11 <= i63) {
                        int i64 = i11;
                        while (true) {
                            if ((aVar3.l(i64, aVar3.S) == imageBeautyData2.a(i64, imageBeautyData2.f1282c0) ? i11 : 0) == 0) {
                                aVar3.T(imageBeautyData2.a(i64, imageBeautyData2.f1282c0), i64);
                            }
                            if (i64 == i63) {
                                break;
                            }
                            i64++;
                        }
                    }
                    int i65 = aVar3.f14099i;
                    if (i11 <= i65) {
                        int i66 = i11;
                        while (true) {
                            if ((aVar3.l(i66, aVar3.U) == imageBeautyData2.a(i66, imageBeautyData2.f1285e0) ? i11 : 0) == 0) {
                                aVar3.R(imageBeautyData2.a(i66, imageBeautyData2.f1285e0), i66);
                            }
                            if (i66 == i65) {
                                break;
                            }
                            i66++;
                        }
                    }
                    int i67 = aVar3.f14099i;
                    if (i11 <= i67) {
                        int i68 = i11;
                        while (true) {
                            if ((aVar3.l(i68, aVar3.G) == imageBeautyData2.a(i68, imageBeautyData2.Q) ? i11 : 0) == 0) {
                                aVar3.N(imageBeautyData2.a(i68, imageBeautyData2.Q), i68);
                            }
                            if (i68 == i67) {
                                break;
                            }
                            i68++;
                        }
                    }
                    int i69 = aVar3.f14099i;
                    if (i11 <= i69) {
                        int i70 = i11;
                        while (true) {
                            if ((aVar3.l(i70, aVar3.L) == imageBeautyData2.a(i70, imageBeautyData2.V) ? i11 : 0) == 0) {
                                aVar3.P(imageBeautyData2.a(i70, imageBeautyData2.V), i70);
                            }
                            if (i70 == i69) {
                                break;
                            }
                            i70++;
                        }
                    }
                    int i71 = aVar3.f14099i;
                    if (i11 <= i71) {
                        int i72 = i11;
                        while (true) {
                            if ((aVar3.l(i72, aVar3.M) == imageBeautyData2.a(i72, imageBeautyData2.W) ? i11 : 0) == 0) {
                                aVar3.O(imageBeautyData2.a(i72, imageBeautyData2.W), i72);
                            }
                            if (i72 == i71) {
                                break;
                            }
                            i72++;
                        }
                    }
                    int i73 = aVar3.f14099i;
                    if (i11 <= i73) {
                        int i74 = i11;
                        while (true) {
                            if ((aVar3.l(i74, aVar3.P) == imageBeautyData2.a(i74, imageBeautyData2.Z) ? i11 : 0) == 0) {
                                aVar3.C(imageBeautyData2.a(i74, imageBeautyData2.Z), i74);
                            }
                            if (i74 == i73) {
                                break;
                            }
                            i74++;
                        }
                    }
                    int i75 = aVar3.f14099i;
                    if (i11 <= i75) {
                        int i76 = i11;
                        while (true) {
                            if ((aVar3.l(i76, aVar3.J) == imageBeautyData2.a(i76, imageBeautyData2.T) ? i11 : 0) == 0) {
                                aVar3.Z(imageBeautyData2.a(i76, imageBeautyData2.T), i76);
                            }
                            if (i76 == i75) {
                                break;
                            }
                            i76++;
                        }
                    }
                    int i77 = aVar3.f14099i;
                    if (i11 <= i77) {
                        int i78 = i11;
                        while (true) {
                            if ((aVar3.l(i78, aVar3.N) == imageBeautyData2.a(i78, imageBeautyData2.X) ? i11 : 0) == 0) {
                                aVar3.S(imageBeautyData2.a(i78, imageBeautyData2.X), i78);
                            }
                            if (i78 == i77) {
                                break;
                            }
                            i78++;
                        }
                    }
                    int i79 = aVar3.f14099i;
                    if (i11 <= i79) {
                        int i80 = i11;
                        while (true) {
                            if ((aVar3.l(i80, aVar3.T) == imageBeautyData2.a(i80, imageBeautyData2.f1283d0) ? i11 : 0) == 0) {
                                aVar3.a0(imageBeautyData2.a(i80, imageBeautyData2.f1283d0), i80);
                            }
                            if (i80 == i79) {
                                break;
                            }
                            i80++;
                        }
                    }
                    int i81 = aVar3.f14099i;
                    if (i11 <= i81) {
                        int i82 = i11;
                        while (true) {
                            if ((aVar3.l(i82, aVar3.K) == imageBeautyData2.a(i82, imageBeautyData2.U) ? i11 : 0) == 0) {
                                aVar3.Y(imageBeautyData2.a(i82, imageBeautyData2.U), i82);
                            }
                            if (i82 == i81) {
                                break;
                            }
                            i82++;
                        }
                    }
                    int i83 = aVar3.f14099i;
                    if (i11 <= i83) {
                        int i84 = i11;
                        while (true) {
                            if ((aVar3.l(i84, aVar3.O) == imageBeautyData2.a(i84, imageBeautyData2.Y) ? i11 : 0) == 0) {
                                aVar3.h0(i84, "image_intensity_smile", aVar3.O, imageBeautyData2.a(i84, imageBeautyData2.Y));
                            }
                            if (i84 == i83) {
                                break;
                            }
                            i84++;
                        }
                    }
                    double d11 = aVar3.f14094e0;
                    double d12 = imageBeautyData2.f1290j0;
                    if ((d11 == d12 ? i11 : 0) == 0) {
                        aVar3.u(d12);
                    }
                    double d13 = aVar3.f14092c0;
                    double d14 = imageBeautyData2.f1288h0;
                    if ((d13 == d14 ? i11 : 0) == 0) {
                        aVar3.v(d14);
                    }
                    double d15 = aVar3.f14090a0;
                    double d16 = imageBeautyData2.f1286f0;
                    if ((d15 == d16 ? i11 : 0) == 0) {
                        aVar3.f14090a0 = d16;
                        if (aVar3.f14097h) {
                            aVar3.e(Double.valueOf(d16), "body_intensity_body_slim");
                        }
                    }
                    double d17 = aVar3.f14091b0;
                    double d18 = imageBeautyData2.f1287g0;
                    if ((d17 == d18 ? i11 : 0) == 0) {
                        aVar3.o(d18);
                    }
                    double d19 = aVar3.f14093d0;
                    double d20 = imageBeautyData2.f1289i0;
                    if ((d19 == d20 ? i11 : 0) == 0) {
                        aVar3.t(d20);
                    }
                    double d21 = aVar3.f14095f0;
                    double d22 = imageBeautyData2.f1291k0;
                    if ((d21 == d22 ? i11 : 0) == 0) {
                        aVar3.r(d22);
                    }
                    double d23 = aVar3.f14096g0;
                    double d24 = imageBeautyData2.f1292l0;
                    if ((d23 == d24 ? i11 : 0) == 0) {
                        aVar3.q(d24);
                    }
                    double d25 = aVar3.f14098h0;
                    double d26 = imageBeautyData2.f1293m0;
                    if ((d25 == d26 ? i11 : 0) == 0) {
                        aVar3.s(d26);
                    }
                    double d27 = aVar3.f14100i0;
                    double d28 = imageBeautyData2.f1295n0;
                    if ((d27 == d28 ? i11 : 0) == 0) {
                        aVar3.p(d28);
                    }
                    aVar3.f14107n0.clear();
                    LinkedList<m2.a.C0264a> linkedList = imageBeautyData2.f1296o0;
                    if (((linkedList == null || linkedList.isEmpty()) ? i11 : 0) != 0) {
                        aVar3.g(0.0d, 0.0d, 0.0d, 0.5d);
                        aVar3.m();
                    } else {
                        for (m2.a.C0264a c0264a : linkedList) {
                            aVar3.j0();
                            aVar3.g(c0264a.f14117b, c0264a.f14118c, c0264a.d, c0264a.f14116a);
                        }
                        aVar3.m();
                    }
                    double d29 = aVar3.W;
                    double d30 = imageBeautyData2.f1301t0;
                    if (!(d29 == d30)) {
                        aVar3.W = d30;
                        if (aVar3.f14097h) {
                            aVar3.e(Double.valueOf(d30), "image_manual_result_status");
                        }
                    }
                    if (!e.a(aVar3.X, imageBeautyData2.f1298q0)) {
                        aVar3.U(imageBeautyData2.f1298q0);
                    }
                    if (!e.a(aVar3.Y, imageBeautyData2.f1299r0)) {
                        aVar3.V(imageBeautyData2.f1299r0);
                    }
                    if (!e.a(aVar3.Z, imageBeautyData2.f1300s0)) {
                        aVar3.X(imageBeautyData2.f1300s0);
                    }
                    if (!e.a(aVar3.V, imageBeautyData2.f1297p0)) {
                        aVar3.W(imageBeautyData2.f1297p0);
                    }
                    double d31 = aVar3.f14103k0;
                    double d32 = imageBeautyData2.f1302u0;
                    if (!(d31 == d32)) {
                        aVar3.f14103k0 = d32;
                        if (aVar3.f14097h) {
                            aVar3.e(Double.valueOf(d32), "pet_auto_beauty");
                        }
                    }
                    double d33 = aVar3.f14104l0;
                    double d34 = imageBeautyData2.f1303v0;
                    if (!(d33 == d34)) {
                        aVar3.f14104l0 = d34;
                        if (aVar3.f14097h) {
                            aVar3.e(Double.valueOf(d34), "pet_hair_bright");
                        }
                    }
                    double d35 = aVar3.f14105m0;
                    double d36 = imageBeautyData2.f1305w0;
                    if (!(d35 == d36)) {
                        aVar3.f14105m0 = d36;
                        if (aVar3.f14097h) {
                            aVar3.e(Double.valueOf(d36), "pet_texture");
                        }
                    }
                }
            }
            e().getClass();
            FURenderBridge.e(cVar);
            i12++;
            i11 = 1;
        }
        m2.a aVar4 = aVar.a().f1330c;
        if (aVar4 != null) {
            k kVar2 = k.A;
            synchronized (kVar2) {
                kVar2.t(aVar4, false);
                kVar2.s(aVar4);
            }
        }
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuReleaseGLResources();
        FULogger.a("KIT_SDKController", "releaseGLResources");
        j2.c.a aVar5 = cVar.f11717e;
        if (aVar5 != null) {
            return aVar5.f11721e;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:97:0x01d7  */
    @Override // d2.a
    public final ImageBeautyData c(Bitmap bitmap) {
        int i10;
        boolean z;
        m2.a aVar;
        if (bitmap.isRecycled()) {
            return null;
        }
        j2.c cVar = new j2.c(bitmap.getWidth(), bitmap.getHeight());
        j2.c.a aVar2 = new j2.c.a(FUInputBufferEnum.FU_FORMAT_BITMAP, 30);
        cVar.f11717e = aVar2;
        aVar2.f11721e = bitmap;
        cVar.f11714a = bitmap.getWidth();
        cVar.f11715b = bitmap.getHeight();
        boolean z10 = true;
        cVar.d = true;
        cVar.f11716c = ProcessMode.FU_IMAGE_BEAUTY_MODE_AUTO;
        e().getClass();
        Log.d(this.f1260a, a6.b.f("buildImageBeautyDataForBeautyModeAuto: res:", FURenderBridge.h(cVar)));
        k2.a.d.a();
        int iD = k2.a.d();
        if (iD <= 0) {
            return null;
        }
        b bVar = (b) this.f1262c.getValue();
        bVar.getClass();
        m2.a aVar3 = FURenderKit.d.a().f1330c;
        String str = bVar.f10405a;
        Log.d(str, "setSmartBeautyParamIntensity: imageBeauty:" + aVar3 + "faceNum:" + iD);
        if (aVar3 == null) {
            return null;
        }
        aVar3.w(iD);
        int[] iArr = new int[iD];
        int i11 = 0;
        while (i11 < iD) {
            int i12 = i11 + 1;
            int iK = (int) aVar3.k(i12, "image_auto_style");
            if (iK < 0) {
                iK = 1;
            }
            iArr[i11] = iK;
            i11 = i12;
        }
        int i13 = iArr[0];
        if (i13 == FaceBeautySmartStyleEnum.CLASSIC_1_M.getType() || i13 == FaceBeautySmartStyleEnum.CLASSIC_2_M.getType() || i13 == FaceBeautySmartStyleEnum.CLASSIC_3_M.getType() || i13 == FaceBeautySmartStyleEnum.CLASSIC_1.getType() || i13 == FaceBeautySmartStyleEnum.CLASSIC_2.getType() || i13 == FaceBeautySmartStyleEnum.CLASSIC_3.getType()) {
            i10 = 0;
        } else if (i13 == FaceBeautySmartStyleEnum.NATURE_1_M.getType() || i13 == FaceBeautySmartStyleEnum.NATURE_2_M.getType() || i13 == FaceBeautySmartStyleEnum.NATURE_3_M.getType() || i13 == FaceBeautySmartStyleEnum.NATURE_1.getType() || i13 == FaceBeautySmartStyleEnum.NATURE_2.getType() || i13 == FaceBeautySmartStyleEnum.NATURE_3.getType()) {
            i10 = 1;
        } else if (i13 == FaceBeautySmartStyleEnum.TEXTURE_1_M.getType() || i13 == FaceBeautySmartStyleEnum.TEXTURE_2_M.getType() || i13 == FaceBeautySmartStyleEnum.TEXTURE_3_M.getType() || i13 == FaceBeautySmartStyleEnum.TEXTURE_1.getType() || i13 == FaceBeautySmartStyleEnum.TEXTURE_2.getType() || i13 == FaceBeautySmartStyleEnum.TEXTURE_3.getType()) {
            i10 = 2;
        } else if (i13 == FaceBeautySmartStyleEnum.MATURE_1_M.getType() || i13 == FaceBeautySmartStyleEnum.MATURE_2_M.getType() || i13 == FaceBeautySmartStyleEnum.MATURE_3_M.getType()) {
            i10 = 3;
        } else {
            FaceBeautySmartStyleEnum faceBeautySmartStyleEnum = FaceBeautySmartStyleEnum.MATURE_1;
            if (i13 == faceBeautySmartStyleEnum.getType() || i13 == faceBeautySmartStyleEnum.getType() || i13 == FaceBeautySmartStyleEnum.MATURE_3.getType()) {
                i10 = 3;
            } else if (i13 == FaceBeautySmartStyleEnum.JAPANESE_1_M.getType() || i13 == FaceBeautySmartStyleEnum.JAPANESE_2_M.getType() || i13 == FaceBeautySmartStyleEnum.JAPANESE_3_M.getType() || i13 == FaceBeautySmartStyleEnum.JAPANESE_1.getType() || i13 == FaceBeautySmartStyleEnum.JAPANESE_2.getType() || i13 == FaceBeautySmartStyleEnum.JAPANESE_3.getType()) {
                i10 = 4;
            } else {
                i10 = (i13 == FaceBeautySmartStyleEnum.LIGHT_COLOR_1_M.getType() || i13 == FaceBeautySmartStyleEnum.LIGHT_COLOR_2_M.getType() || i13 == FaceBeautySmartStyleEnum.LIGHT_COLOR_3_M.getType() || i13 == FaceBeautySmartStyleEnum.LIGHT_COLOR_1.getType() || i13 == FaceBeautySmartStyleEnum.LIGHT_COLOR_2.getType() || i13 == FaceBeautySmartStyleEnum.LIGHT_COLOR_3.getType()) ? 5 : 6;
            }
        }
        Log.d(str, "setSmartBeautyParamIntensity: recommendStyleArray:" + Arrays.toString(iArr) + "position:" + i10);
        int i14 = 0;
        while (true) {
            if (i14 < iD) {
                e2.a aVarH = b.h(iArr[i14]);
                if (aVarH != null && aVarH.f10388c != 3) {
                    z = false;
                    break;
                }
                i14++;
            } else {
                z = true;
                break;
            }
        }
        int i15 = 0;
        while (true) {
            if (i15 < iD) {
                e2.a aVarH2 = b.h(iArr[i15]);
                if (aVarH2 != null && aVarH2.f10388c == 2) {
                    break;
                }
                i15++;
            } else {
                z10 = false;
                break;
            }
        }
        double d = z ? 0.0d : b.f10400b[i10];
        double d10 = z ? 0.0d : b.f10401c[i10];
        double d11 = z10 ? 0.7d : 0.0d;
        double d12 = b.d[i10];
        m2.a aVar4 = aVar3;
        Log.d(str, "setSmartBeautyParamIntensity: isChild:" + z + "hasWoman:" + z10 + "faceBeautyDefault:" + d + "faceWrapDefault:" + d10 + "faceMakeUpDefault:" + d11 + "filterDefault:" + d12);
        int i16 = 0;
        while (i16 < iD) {
            e2.a aVarI = b.i(iArr[i16], i10);
            if (aVarI != null) {
                aVar = aVar4;
                aVar.f14097h = false;
                int i17 = i16 + 1;
                aVar.B(aVarI.d * d, i17);
                aVar.A(aVarI.f10389e * d, i17);
                aVar.z(aVarI.f * d, i17);
                aVar.J(aVarI.g * d, i17);
                aVar.d0(aVarI.f10390h * d, i17);
                aVar.K(aVarI.f10391i * d, i17);
                aVar.e0(aVarI.j * d, i17);
                aVar.F(aVarI.f10392k * d10, i17);
                aVar.N(b.g(aVarI.l, d10), i17);
                aVar.Z(b.g(aVarI.m, d10), i17);
                aVar.H(b.g(aVarI.f10393n, d10), i17);
                aVar.T(b.g(aVarI.f10394o, d10), i17);
                aVar.g0(aVarI.v * d11, i17);
                aVar.c0(d11 > 0.0d ? aVarI.r : 0.0d, i17);
                aVar.b0(aVarI.s * d11, i17);
                aVar.M(d11 <= 0.0d ? 0.0d : aVarI.f10396t, i17);
                aVar.L(aVarI.f10397u * d11, i17);
            } else {
                aVar = aVar4;
            }
            i16++;
            d12 = d12;
            aVar4 = aVar;
        }
        m2.a aVar5 = aVar4;
        double d13 = d12;
        e2.a aVarI2 = b.i(iArr[0], i10);
        if (aVarI2 != null) {
            aVar5.y(aVarI2.p);
            aVar5.x(aVarI2.f10395q * d13);
        }
        return aVar5.h();
    }

    @Override // d2.a
    public final d d(j2.c cVar) {
        d dVarC;
        synchronized (e().f1381a) {
            Thread.currentThread().getId();
            dVarC = FURenderBridge.c(cVar);
        }
        return dVarC;
    }

    public final FURenderBridge e() {
        return (FURenderBridge) this.f1261b.getValue();
    }
}
