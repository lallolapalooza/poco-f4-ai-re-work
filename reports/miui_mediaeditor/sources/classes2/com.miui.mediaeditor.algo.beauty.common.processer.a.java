package com.miui.mediaeditor.algo.beauty.common.processer;

import android.graphics.Bitmap;
import com.faceunity.core.cache.CacheData;
import com.miui.mediaeditor.algo.base.BaseParam;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.BeautyGlView;
import fr.l;
import kotlin.jvm.internal.e;
import yq.d;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements com.miui.mediaeditor.algo.base.b<BeautyExportDataParam, BeautyExportDataResult> {
    @Override // com.miui.mediaeditor.algo.base.b
    public final void b(BaseParam baseParam, final l lVar) {
        final BeautyExportDataParam beautyExportDataParam = (BeautyExportDataParam) baseParam;
        BeautyGlView beautyGlView = beautyExportDataParam.f7428n.get();
        if (beautyGlView != null) {
            beautyGlView.k(new l<CacheData, d>() { // from class: com.miui.mediaeditor.algo.beauty.common.processer.BeautyExportDataProcessor$processAsync$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // fr.l
                public final d invoke(CacheData cacheData) {
                    Bitmap bitmap;
                    CacheData it = cacheData;
                    e.f(it, "it");
                    com.miui.mediaeditor.algo.beauty.common.b bVar = beautyExportDataParam.m;
                    e.c(bVar);
                    com.miui.mediaeditor.algo.beauty.common.d dVar = new com.miui.mediaeditor.algo.beauty.common.d(bVar);
                    l<BeautyExportDataResult, d> lVar2 = lVar;
                    dVar.f7420b = it.f1265a;
                    CacheData.a aVar = it.f1266b;
                    dVar.f7421c = (aVar == null || (bitmap = aVar.f1272e) == null) ? null : bitmap.copy(Bitmap.Config.ARGB_8888, true);
                    lVar2.invoke(new BeautyExportDataResult(dVar));
                    return d.f18754a;
                }
            });
        }
    }
}
