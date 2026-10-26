package com.xiaomi.ocr.sdk.imgprocess;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public enum DocumentProcess$EnhanceType {
    RAW,
    COLOR,
    BIN,
    GRAY,
    FILTER_ID_CARD,
    DEMOIRE;

    @Override // java.lang.Enum
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
