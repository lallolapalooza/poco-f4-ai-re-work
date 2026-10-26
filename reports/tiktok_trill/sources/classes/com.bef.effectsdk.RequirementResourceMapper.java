package com.bef.effectsdk;

import com.bytedance.covode.number.Covode;

/* JADX INFO: loaded from: classes2.dex */
public final class RequirementResourceMapper {
    static {
        Covode.recordClassIndex(2848);
    }

    public static native String[] nativePeekResourcesNeededByRequirements(String[] strArr);

    public static String[] peekResourcesNeededByRequirements(String[] strArr) {
        return nativePeekResourcesNeededByRequirements(strArr);
    }
}
