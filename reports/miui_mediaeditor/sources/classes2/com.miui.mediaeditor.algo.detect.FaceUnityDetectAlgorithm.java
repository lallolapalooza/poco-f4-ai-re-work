package com.miui.mediaeditor.algo.detect;

import com.faceunity.core.enumeration.FUAITypeEnum;
import com.miui.mediaeditor.algo.base.BaseAlgorithm;
import com.miui.mediaeditor.algo.base.BaseParam;
import com.miui.mediaeditor.algo.base.BaseResult;
import com.miui.mediaeditor.algo.base.State;
import com.miui.mediaeditor.algo.detect.processer.BodyNumDetectParam;
import com.miui.mediaeditor.algo.detect.processer.FaceNumDetectParam;
import com.miui.mediaeditor.algo.detect.processer.LandMarksDetectParam;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.e;
import rg.b;
import rg.c;
import sg.a;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/miui/mediaeditor/algo/detect/FaceUnityDetectAlgorithm;", "Lcom/miui/mediaeditor/algo/base/BaseAlgorithm;", "<init>", "()V", "detect_release"}, k = 1, mv = {1, 8, 0})
public final class FaceUnityDetectAlgorithm extends BaseAlgorithm {
    @Override // com.miui.mediaeditor.algo.base.a
    public final BaseResult f(BaseParam param) {
        boolean zA;
        e.f(param, "param");
        super.f(param);
        if (param instanceof FaceUnityDetectInitParam) {
            FaceUnityDetectInitParam faceUnityDetectInitParam = (FaceUnityDetectInitParam) param;
            zA = a.a(faceUnityDetectInitParam.f7457n);
            int iOrdinal = faceUnityDetectInitParam.m.ordinal();
            FUAITypeEnum fUAITypeEnum = FUAITypeEnum.FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS;
            k2.a.C0199a c0199a = k2.a.d;
            if (iOrdinal == 0) {
                c0199a.a().e("model/ai_face_processor_detect_landmark.bundle", fUAITypeEnum, null);
            } else if (iOrdinal == 1) {
                c0199a.a().e("model/ai_human_processor_beautybody_mb.bundle", FUAITypeEnum.FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY, null);
            } else {
                if (iOrdinal == 2) {
                    throw new NotImplementedError();
                }
                if (iOrdinal == 3) {
                    c0199a.a().e("model/ai_face_processor_detect_landmark.bundle", fUAITypeEnum, null);
                }
            }
        } else {
            zA = false;
        }
        return new BaseResult(zA ? State.SUCCESS : State.FAILED);
    }

    @Override // com.miui.mediaeditor.algo.base.a
    public final BaseResult g(BaseParam param) {
        e.f(param, "param");
        if (param instanceof FaceUnityDetectReleaseParam) {
            int iOrdinal = ((FaceUnityDetectReleaseParam) param).m.ordinal();
            FUAITypeEnum fUAITypeEnum = FUAITypeEnum.FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS;
            k2.a.C0199a c0199a = k2.a.d;
            if (iOrdinal == 0) {
                c0199a.a().f(fUAITypeEnum);
            } else if (iOrdinal == 1) {
                c0199a.a().f(FUAITypeEnum.FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY);
            } else {
                if (iOrdinal == 2) {
                    throw new NotImplementedError();
                }
                if (iOrdinal == 3) {
                    c0199a.a().f(fUAITypeEnum);
                }
            }
        }
        return super.g(param);
    }

    @Override // com.miui.mediaeditor.algo.base.BaseAlgorithm
    public final void h() {
        b bVar = new b();
        HashMap<Class<?>, com.miui.mediaeditor.algo.base.b<?, ?>> map = this.f7326a;
        map.put(FaceNumDetectParam.class, bVar);
        map.put(BodyNumDetectParam.class, new rg.a());
        map.put(LandMarksDetectParam.class, new c());
    }
}
