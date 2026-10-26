package com.miui.mediaeditor.photo.remover_pro;

import com.miui.mediaeditor.aigc.ManagerStatus;
import com.miui.mediaeditor.aigc.config.ManagerListConfig;
import com.miui.mediaeditor.aigc.manager.BaseAIEngine;
import com.miui.mediaeditor.algo.base.BaseAlgorithm;
import miuix.appcompat.app.floatingactivity.i;

/* JADX INFO: loaded from: classes2.dex */
public final class InpaintAIEngine extends BaseAIEngine {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final yq.c f9037b = kotlin.a.a(new fr.a<a>() { // from class: com.miui.mediaeditor.photo.remover_pro.InpaintAIEngine$edgeCloudAIEngine$2
        @Override // fr.a
        public final InpaintAIEngine.a invoke() {
            return new InpaintAIEngine.a();
        }
    });

    public static final class a extends BaseAIEngine {
        @Override // com.miui.mediaeditor.aigc.manager.BaseAIEngine
        public final com.miui.mediaeditor.algo.base.a a() {
            return new BaseAlgorithm() { // from class: com.miui.mediaeditor.photo.remover_pro.InpaintAIEngine$EdgeCloudAIEngine$algoCreate$1
                @Override // com.miui.mediaeditor.algo.base.BaseAlgorithm
                public final void h() {
                }
            };
        }

        @Override // com.miui.mediaeditor.aigc.manager.BaseAIEngine
        public final ManagerListConfig.FUNC g() {
            return ManagerListConfig.FUNC.f7216e;
        }
    }

    static {
        i.u("㇇㇠ㇾ㇯\u31e7㇠ㇺ㇏㇇㇋㇠\u31e9\u31e7㇠\u31eb");
    }

    @Override // com.miui.mediaeditor.aigc.manager.BaseAIEngine
    public final com.miui.mediaeditor.algo.base.a a() {
        return new BaseAlgorithm() { // from class: com.miui.mediaeditor.photo.remover_pro.InpaintAIEngine$algoCreate$1
            @Override // com.miui.mediaeditor.algo.base.BaseAlgorithm
            public final void h() {
            }
        };
    }

    @Override // com.miui.mediaeditor.aigc.manager.BaseAIEngine
    public final ManagerListConfig.FUNC g() {
        yq.c cVar = this.f9037b;
        ManagerStatus managerStatusD = ((a) cVar.getValue()).d();
        kotlinx.coroutines.internal.a.Q(i.u("㇇㇠ㇾ㇯\u31e7㇠ㇺ㇏㇇㇋㇠\u31e9\u31e7㇠\u31eb"), i.u("\u31edㇻㇼㇼ\u31eb㇠ㇺㆮㇽㇺ㇯ㇺㇻㇽㆮ\u31e7ㇽㆮ") + managerStatusD);
        int iOrdinal = ((a) cVar.getValue()).d().ordinal();
        return (iOrdinal == 0 || iOrdinal == 1) ? ManagerListConfig.FUNC.d : ManagerListConfig.FUNC.f7216e;
    }
}
