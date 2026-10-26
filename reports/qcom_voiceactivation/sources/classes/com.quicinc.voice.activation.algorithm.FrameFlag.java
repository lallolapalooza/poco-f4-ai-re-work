package com.quicinc.voice.activation.algorithm;

/* JADX INFO: loaded from: classes.dex */
public enum FrameFlag {
    FRAME_TYPE_NONE,
    FRAME_TYPE_HEAD,
    FRAME_TYPE_MIDDLE,
    FRAME_TYPE_TAIL,
    FRAME_TYPE_ONE_SHOT;

    public boolean b() {
        return this == FRAME_TYPE_TAIL || this == FRAME_TYPE_ONE_SHOT;
    }
}
