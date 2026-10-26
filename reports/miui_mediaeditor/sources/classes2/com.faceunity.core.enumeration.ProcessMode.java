package com.faceunity.core.enumeration;

import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/faceunity/core/enumeration/ProcessMode;", "", "", "mode", "I", DateTokenConverter.CONVERTER_KEY, "()I", "fu_core_xiaomiRelease"}, k = 1, mv = {1, 7, 1})
public enum ProcessMode {
    FU_IMAGE_BEAUTY_MODE_UNKNOWN(0),
    FU_IMAGE_BEAUTY_MODE_FACE_BEAUTY(1),
    FU_IMAGE_BEAUTY_MODE_FACE_WARP(2),
    FU_IMAGE_BEAUTY_MODE_BODY_SLIM(4),
    FU_IMAGE_BEAUTY_MODE_AUTO(8),
    FU_IMAGE_BEAUTY_MODE_UPLOAD(16),
    /* JADX INFO: Fake field, exist only in values array */
    FU_IMAGE_BEAUTY_MODE_RESTORE(Integer.MIN_VALUE),
    /* JADX INFO: Fake field, exist only in values array */
    FU_IMAGE_BEAUTY_MODE_PET_BEAUTY(Integer.MIN_VALUE),
    FU_IMAGE_EDITOR_MODE_GLOBAL_ENHANCE(1048576),
    FU_IMAGE_EDITOR_MODE_LOCAL_ENHANCE(2097152),
    FU_IMAGE_EDITOR_MODE_AUTOCROP(8388608),
    FU_IMAGE_EDITOR_MODE_SCENE_BEAUTIFY(33554432),
    FU_IMAGE_EDITOR_MODE_ID_PHOTO(16777216),
    FU_IMAGE_EDITOR_MODE_ART_PHOTO(67108864),
    FU_IMAGE_EDITOR_MODE_PORTRAIT_COLOR_FOCUS(134217728),
    FU_IMAGE_EDITOR_MODE_HDR(4194304);

    private final int mode;

    ProcessMode(int i10) {
        this.mode = i10;
    }

    /* JADX INFO: renamed from: d, reason: from getter */
    public final int getMode() {
        return this.mode;
    }
}
