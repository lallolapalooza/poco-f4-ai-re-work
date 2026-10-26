package com.miui.mediaeditor.algo.beauty.body;

import com.miui.mediaeditor.algo.animalbeauty.d;
import com.miui.mediaeditor.algo.base.BaseAlgorithm;
import com.miui.mediaeditor.algo.base.BaseParam;
import com.miui.mediaeditor.algo.base.BaseResult;
import com.miui.mediaeditor.algo.base.State;
import com.miui.mediaeditor.algo.base.b;
import com.miui.mediaeditor.algo.beauty.body.processer.BodyBeautyProcessPreviewParam;
import com.miui.mediaeditor.algo.beauty.body.processer.ChangeBodyRadioParam;
import com.miui.mediaeditor.algo.beauty.body.processer.CheckBodyBeautyEnableParam;
import com.miui.mediaeditor.algo.beauty.body.processer.CurrentManualLegSlimListParam;
import com.miui.mediaeditor.algo.beauty.common.processer.BeautyExportDataParam;
import com.miui.mediaeditor.algo.beauty.common.processer.BeautyProcessOriginParam;
import java.util.HashMap;
import jg.a;
import kg.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/miui/mediaeditor/algo/beauty/body/BodyBeautyAlgorithm;", "Lcom/miui/mediaeditor/algo/base/BaseAlgorithm;", "<init>", "()V", "body_beauty_release"}, k = 1, mv = {1, 8, 0})
public final class BodyBeautyAlgorithm extends BaseAlgorithm {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f7334c = new c();

    @Override // com.miui.mediaeditor.algo.base.a
    public final BaseResult f(BaseParam param) {
        e.f(param, "param");
        super.f(param);
        this.f7334c.b();
        return new BaseResult(State.SUCCESS);
    }

    @Override // com.miui.mediaeditor.algo.base.a
    public final BaseResult g(BaseParam param) {
        e.f(param, "param");
        this.f7334c.d();
        return super.g(param);
    }

    @Override // com.miui.mediaeditor.algo.base.BaseAlgorithm
    public final void h() {
        c cVar = this.f7334c;
        com.miui.mediaeditor.algo.beauty.body.processer.c cVar2 = new com.miui.mediaeditor.algo.beauty.body.processer.c(cVar);
        HashMap<Class<?>, b<?, ?>> map = this.f7326a;
        map.put(BodyBeautyProcessPreviewParam.class, cVar2);
        map.put(CheckBodyBeautyEnableParam.class, new com.miui.mediaeditor.algo.animalbeauty.e(cVar));
        map.put(ChangeBodyRadioParam.class, new d(cVar));
        map.put(CurrentManualLegSlimListParam.class, new a(cVar));
        map.put(BeautyExportDataParam.class, new com.miui.mediaeditor.algo.beauty.common.processer.a());
        map.put(BeautyProcessOriginParam.class, new com.miui.mediaeditor.algo.beauty.common.processer.b(cVar));
    }
}
