package com.miui.mediaeditor.algo.beauty.faceshape;

import com.miui.mediaeditor.algo.base.BaseAlgorithm;
import com.miui.mediaeditor.algo.base.BaseParam;
import com.miui.mediaeditor.algo.base.BaseResult;
import com.miui.mediaeditor.algo.base.State;
import com.miui.mediaeditor.algo.base.b;
import com.miui.mediaeditor.algo.beauty.body.processer.c;
import com.miui.mediaeditor.algo.beauty.common.processer.BeautyExportDataParam;
import com.miui.mediaeditor.algo.beauty.common.processer.BeautyProcessOriginParam;
import com.miui.mediaeditor.algo.beauty.common.processer.DetectedInfoParam;
import com.miui.mediaeditor.algo.beauty.faceshape.processer.FaceRectListParam;
import com.miui.mediaeditor.algo.beauty.faceshape.processer.FaceShapeProcessPreviewParam;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import pg.a;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/miui/mediaeditor/algo/beauty/faceshape/FaceShapeAlgorithm;", "Lcom/miui/mediaeditor/algo/base/BaseAlgorithm;", "<init>", "()V", "face_shape_release"}, k = 1, mv = {1, 8, 0})
public final class FaceShapeAlgorithm extends BaseAlgorithm {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f7447c = new a();

    @Override // com.miui.mediaeditor.algo.base.a
    public final BaseResult f(BaseParam param) {
        e.f(param, "param");
        super.f(param);
        this.f7447c.b();
        return new BaseResult(State.SUCCESS);
    }

    @Override // com.miui.mediaeditor.algo.base.a
    public final BaseResult g(BaseParam param) {
        e.f(param, "param");
        this.f7447c.d();
        return super.g(param);
    }

    @Override // com.miui.mediaeditor.algo.base.BaseAlgorithm
    public final void h() {
        a aVar = this.f7447c;
        jg.a aVar2 = new jg.a(aVar);
        HashMap<Class<?>, b<?, ?>> map = this.f7326a;
        map.put(FaceShapeProcessPreviewParam.class, aVar2);
        map.put(DetectedInfoParam.class, new c(aVar));
        map.put(FaceRectListParam.class, new com.miui.mediaeditor.algo.animalbeauty.e(aVar));
        map.put(BeautyExportDataParam.class, new com.miui.mediaeditor.algo.beauty.common.processer.a());
        map.put(BeautyProcessOriginParam.class, new com.miui.mediaeditor.algo.beauty.common.processer.b(aVar));
    }
}
