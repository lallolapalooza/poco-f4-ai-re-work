package pg;

import com.faceunity.core.enumeration.FUAITypeEnum;
import com.faceunity.core.faceunity.FURenderKit;
import com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends BeautyBaseManager {
    @Override // com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager
    public final com.miui.mediaeditor.algo.beauty.common.a a() {
        return new qg.a();
    }

    @Override // com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager
    public final void c() {
        FURenderKit fURenderKitA = FURenderKit.d.a();
        m2.a aVar = this.f7368b.getValue().f7370b;
        aVar.d(new j2.a("graphics/image_beauty_facewarp.bundle"));
        fURenderKitA.h(aVar);
        k2.a aVarA = k2.a.d.a();
        aVarA.e("model/ai_beauty_type_processor.bundle", FUAITypeEnum.FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY, null);
        aVarA.e("model/ai_face_beauty_processor.bundle", FUAITypeEnum.FUAITYPE_IMAGE_BEAUTY, null);
    }
}
