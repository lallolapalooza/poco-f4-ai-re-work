package kg;

import com.faceunity.core.enumeration.FUAITypeEnum;
import com.faceunity.core.faceunity.FURenderKit;
import com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends BeautyBaseManager {
    @Override // com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager
    public final com.miui.mediaeditor.algo.beauty.common.a a() {
        return new lg.b();
    }

    @Override // com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager
    public final void c() {
        FURenderKit fURenderKitA = FURenderKit.d.a();
        m2.a aVar = this.f7368b.getValue().f7370b;
        aVar.d(new j2.a("graphics/image_beauty_bodyslim.bundle"));
        fURenderKitA.h(aVar);
        k2.a.d.a().e("model/ai_human_processor_beautybody_mb.bundle", FUAITypeEnum.FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY, null);
    }
}
