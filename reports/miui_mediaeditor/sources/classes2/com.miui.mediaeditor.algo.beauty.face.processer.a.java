package com.miui.mediaeditor.algo.beauty.face.processer;

import androidx.view.m;
import com.miui.mediaeditor.algo.base.BaseParam;
import com.miui.mediaeditor.algo.base.BaseResult;
import com.miui.mediaeditor.algo.base.State;
import com.miui.mediaeditor.algo.beauty.common.f;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.e;
import og.c;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements com.miui.mediaeditor.algo.base.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7444a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.miui.mediaeditor.algo.beauty.face.a f7445b;

    public a(com.miui.mediaeditor.algo.beauty.face.a beautySDKManager, int i10) {
        this.f7444a = i10;
        if (i10 != 1) {
            e.f(beautySDKManager, "beautySDKManager");
            this.f7445b = beautySDKManager;
        } else {
            e.f(beautySDKManager, "beautySDKManager");
            this.f7445b = beautySDKManager;
        }
    }

    /* JADX WARN: Code duplicated, block: B:121:0x0263  */
    /* JADX WARN: Code duplicated, block: B:16:0x0065  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.miui.mediaeditor.algo.base.b
    public final BaseResult a(BaseParam baseParam) {
        double d;
        double dL;
        State state = State.SUCCESS;
        int i10 = this.f7444a;
        com.miui.mediaeditor.algo.beauty.face.a aVar = this.f7445b;
        switch (i10) {
            case 0:
                FaceBeautyProcessPreviewParam faceBeautyProcessPreviewParam = (FaceBeautyProcessPreviewParam) baseParam;
                com.miui.mediaeditor.algo.beauty.common.a<?, ?> value = aVar.f7368b.getValue();
                if (value instanceof og.a) {
                    og.a aVar2 = (og.a) value;
                    aVar2.getClass();
                    og.b beautyData = faceBeautyProcessPreviewParam.m;
                    e.f(beautyData, "beautyData");
                    StringBuilder sb2 = new StringBuilder("setEffectIntensity() called with: k=");
                    c cVar = beautyData.f15682c;
                    sb2.append(cVar.f7422a);
                    sb2.append(", p=");
                    sb2.append(beautyData.f15683e);
                    sb2.append(", undo=");
                    boolean z = faceBeautyProcessPreviewParam.f7439n;
                    m.s(sb2, z, "FaceBeautyDS");
                    double d10 = z ? beautyData.f : beautyData.f15683e;
                    c.n nVar = c.n.f15698b;
                    if (e.a(nVar, cVar)) {
                        d10 += (double) 100;
                        d = 200;
                    } else {
                        if (e.a(beautyData.g, f.b.f7426c)) {
                            d10 += (double) 100;
                            d = 200;
                        } else {
                            d = 100;
                        }
                    }
                    double d11 = d10 / d;
                    LinkedHashMap linkedHashMap = aVar2.f15681c;
                    linkedHashMap.put(cVar, beautyData);
                    c.C0306c c0306c = c.C0306c.f15687b;
                    boolean zA = e.a(c0306c, cVar);
                    int i11 = 1;
                    m2.a aVar3 = aVar2.f7370b;
                    if (zA) {
                        dL = aVar3.l(1, aVar3.l);
                    } else if (e.a(c.b.f15686b, cVar)) {
                        dL = aVar3.l(1, aVar3.m);
                    } else if (e.a(nVar, cVar)) {
                        dL = aVar3.l(1, aVar3.f14106n);
                    } else if (e.a(c.d.f15688b, cVar)) {
                        dL = aVar3.l(1, aVar3.f14108o);
                    } else if (e.a(c.l.f15696b, cVar)) {
                        dL = aVar3.l(1, aVar3.p);
                    } else if (e.a(c.k.f15695b, cVar)) {
                        dL = aVar3.l(1, aVar3.s);
                    } else if (e.a(c.a.f15685b, cVar)) {
                        dL = aVar3.l(1, aVar3.f14111t);
                    } else if (e.a(c.g.f15691b, cVar)) {
                        dL = aVar3.l(1, aVar3.v);
                    } else if (e.a(c.i.f15693b, cVar)) {
                        dL = aVar3.l(1, aVar3.f14113w);
                    } else if (e.a(c.j.f15694b, cVar)) {
                        dL = aVar3.l(1, aVar3.f14114x);
                    } else if (e.a(c.h.f15692b, cVar)) {
                        dL = aVar3.l(1, aVar3.f14115y);
                    } else if (e.a(c.m.f15697b, cVar)) {
                        dL = aVar3.l(1, aVar3.z);
                    } else if (e.a(c.e.f15689b, cVar)) {
                        dL = aVar3.l(1, aVar3.A);
                    } else {
                        dL = e.a(c.f.f15690b, cVar) ? aVar3.l(1, aVar3.B) : 0.0d;
                    }
                    if ((d11 == dL) == false) {
                        if (e.a(c0306c, cVar)) {
                            aVar3.i0("image_blur", aVar3.l, d11);
                        } else if (e.a(c.b.f15686b, cVar)) {
                            aVar3.i0("image_aver", aVar3.m, d11);
                        } else if (e.a(nVar, cVar)) {
                            aVar3.i0("image_white", aVar3.f14106n, d11);
                        } else if (e.a(c.d.f15688b, cVar)) {
                            aVar3.i0("image_lighteye", aVar3.f14108o, d11);
                        } else if (e.a(c.l.f15696b, cVar)) {
                            aVar3.i0("image_threed", aVar3.p, d11);
                        } else if (e.a(c.k.f15695b, cVar)) {
                            aVar3.i0("image_darkcircle", aVar3.s, d11);
                        } else if (e.a(c.a.f15685b, cVar)) {
                            aVar3.i0("image_acencancel", aVar3.f14111t, d11);
                        } else if (e.a(c.g.f15691b, cVar)) {
                            aVar3.i0("image_wrinkle", aVar3.v, d11);
                        } else if (e.a(c.i.f15693b, cVar)) {
                            aVar3.i0("image_red", aVar3.f14113w, d11);
                        } else if (e.a(c.j.f15694b, cVar)) {
                            aVar3.i0("image_red_type", aVar3.f14114x, d11);
                        } else if (e.a(c.h.f15692b, cVar)) {
                            aVar3.i0("image_jili", aVar3.f14115y, d11);
                        } else if (e.a(c.m.f15697b, cVar)) {
                            aVar3.i0("image_warmcold", aVar3.z, d11);
                        } else if (e.a(c.e.f15689b, cVar)) {
                            aVar3.i0("image_sharpenbrown", aVar3.A, d11);
                        } else if (e.a(c.f.f15690b, cVar)) {
                            aVar3.i0("image_sharpenbrown_type", aVar3.B, d11);
                        }
                    }
                    og.b bVar = (og.b) linkedHashMap.get(cVar);
                    if (bVar != null) {
                        if (bVar.d) {
                            c.a aVar4 = c.a.f15685b;
                            c cVar2 = bVar.f15682c;
                            if (!e.a(cVar2, aVar4)) {
                                if (e.a(cVar2, c0306c)) {
                                    i11 = 2;
                                } else if (e.a(cVar2, c.g.f15691b)) {
                                    i11 = 3;
                                } else {
                                    i11 = 0;
                                }
                            }
                        } else {
                            i11 = 0;
                        }
                        if (aVar3.f14097h) {
                            aVar3.e(Integer.valueOf(i11), "image_beauty_type");
                        }
                    }
                    boolean z10 = beautyData.d;
                    if (aVar3.f14097h) {
                        aVar3.e(Integer.valueOf(z10 ? 1 : 0), "image_beauty_manual_type");
                    }
                }
                break;
            default:
                SetImagePointSizeParam setImagePointSizeParam = (SetImagePointSizeParam) baseParam;
                com.miui.mediaeditor.algo.beauty.common.a<?, ?> value2 = aVar.f7368b.getValue();
                if (value2 instanceof og.a) {
                    m2.a aVar5 = ((og.a) value2).f7370b;
                    if (aVar5.f14097h) {
                        aVar5.e(Double.valueOf(setImagePointSizeParam.m), "image_pointsize");
                    }
                }
                break;
        }
        return new BaseResult(state);
    }
}
