package com.miui.mediaeditor.algo.beauty.face;

import a6.b;
import com.faceunity.core.enumeration.FUAITypeEnum;
import com.faceunity.core.faceunity.FURenderKit;
import com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager;
import fr.l;
import kotlin.jvm.internal.e;
import yq.d;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends BeautyBaseManager {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f7436c;

    /* JADX INFO: renamed from: com.miui.mediaeditor.algo.beauty.face.a$a, reason: collision with other inner class name */
    public static final class C0100a implements g2.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ l<Boolean, d> f7437a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ a f7438b;

        /* JADX WARN: Multi-variable type inference failed */
        public C0100a(l<? super Boolean, d> lVar, a aVar) {
            this.f7437a = lVar;
            this.f7438b = aVar;
        }

        @Override // g2.a
        public final void a(int i10, String msg) {
            e.f(msg, "msg");
            this.f7438b.f7436c = true;
            this.f7437a.invoke(Boolean.TRUE);
        }

        @Override // g2.a
        public final void b(int i10, String errMsg) {
            e.f(errMsg, "errMsg");
            this.f7437a.invoke(Boolean.FALSE);
        }
    }

    @Override // com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager
    public final com.miui.mediaeditor.algo.beauty.common.a a() {
        return new og.a();
    }

    @Override // com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager
    public final void c() {
        FURenderKit fURenderKitA = FURenderKit.d.a();
        m2.a aVar = this.f7368b.getValue().f7370b;
        aVar.d(new j2.a("graphics/image_beauty_facebeauty.bundle"));
        fURenderKitA.h(aVar);
        k2.a aVarA = k2.a.d.a();
        aVarA.e("model/ai_beauty_type_processor.bundle", FUAITypeEnum.FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY, null);
        aVarA.e("model/ai_face_beauty_processor.bundle", FUAITypeEnum.FUAITYPE_IMAGE_BEAUTY, null);
        aVarA.e("model/ai_face_bindi_seg_processor.bundle", FUAITypeEnum.FUAITYPE_FACEPROCESSOR_BINDI_SEG_PROCESSOR, null);
        e(new l<Boolean, d>() { // from class: com.miui.mediaeditor.algo.beauty.face.FaceBeautySDKManager$initAiWrinkleRemover$1
            @Override // fr.l
            public final /* bridge */ /* synthetic */ d invoke(Boolean bool) {
                bool.booleanValue();
                return d.f18754a;
            }
        });
    }

    @Override // com.miui.mediaeditor.algo.beauty.common.BeautyBaseManager
    public final void d() {
        super.d();
        this.f7436c = false;
    }

    public final void e(l<? super Boolean, d> block) {
        e.f(block, "block");
        if (this.f7436c) {
            block.invoke(Boolean.TRUE);
            return;
        }
        String str = ng.a.d;
        String strJ = str == null || str.length() == 0 ? null : b.j(new StringBuilder(), ng.a.d, "/ai_wrinkle_remover_processor.bundle");
        if (strJ == null || strJ.length() == 0) {
            block.invoke(Boolean.FALSE);
        } else {
            k2.a.d.a().e(strJ, FUAITypeEnum.FUAITYPE_PLUGINS_WRINKLE_PROCESSOR_IMAGE_BEAUTY, new C0100a(block, this));
        }
    }
}
