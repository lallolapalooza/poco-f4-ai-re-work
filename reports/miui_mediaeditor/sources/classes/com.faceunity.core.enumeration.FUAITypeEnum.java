package com.faceunity.core.enumeration;

import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/faceunity/core/enumeration/FUAITypeEnum;", "", "", "type", "I", DateTokenConverter.CONVERTER_KEY, "()I", "fu_core_xiaomiRelease"}, k = 1, mv = {1, 7, 1})
public enum FUAITypeEnum {
    FUAITYPE_TONGUETRACKING(32),
    FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY(65536),
    FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY(131072),
    FUAITYPE_IMAGE_BEAUTY(2048),
    FUAITYPE_FACE_ATTRIBUTE_PROCESSOR(4096),
    FUAITYPE_PLUGINS_WRINKLE_PROCESSOR_IMAGE_BEAUTY(1048576),
    FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS(8388608),
    /* JADX INFO: Fake field, exist only in values array */
    FUAITYPE_FACEPROCESSOR_RESTORE(16777216),
    /* JADX INFO: Fake field, exist only in values array */
    FUAITYPE_PET_SEG_PROCESSOR(33554432),
    FUAITYPE_FACEPROCESSOR_BINDI_SEG_PROCESSOR(67108864),
    /* JADX INFO: Fake field, exist only in values array */
    PHOTOEDITOR_FUAITYPE_SCENE_SEMANTIC_SEG(0),
    /* JADX INFO: Fake field, exist only in values array */
    PHOTOEDITOR_FUAITYPE_SCENE_RECOG(1),
    /* JADX INFO: Fake field, exist only in values array */
    PHOTOEDITOR_FUAITYPE_ARTS_PHOTO(2),
    /* JADX INFO: Fake field, exist only in values array */
    PHOTOEDITOR_FUAITYPE_LIGHT_RECOG(3),
    /* JADX INFO: Fake field, exist only in values array */
    PHOTOEDITOR_FUAITYPE_ID_PHOTO(4),
    /* JADX INFO: Fake field, exist only in values array */
    FUAITYPE_FACEPROCESSOR(256),
    /* JADX INFO: Fake field, exist only in values array */
    FUAITYPE_HUMAN_PROCESSOR(512),
    /* JADX INFO: Fake field, exist only in values array */
    FUAITYPE_VIDEOPROCESSOR(2097152);

    private final int type;

    FUAITypeEnum(int i10) {
        this.type = i10;
    }

    /* JADX INFO: renamed from: d, reason: from getter */
    public final int getType() {
        return this.type;
    }
}
