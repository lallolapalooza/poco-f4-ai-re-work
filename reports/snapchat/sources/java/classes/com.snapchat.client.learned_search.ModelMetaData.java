package com.snapchat.client.learned_search;

import defpackage.AbstractC19915fE3;

/* JADX INFO: loaded from: classes6.dex */
public final class ModelMetaData {
    public final String mModelUrl;
    public final Integer mModelVersion;
    public final StatusEnums mStatusCode;

    public ModelMetaData(String str, Integer num, StatusEnums statusEnums) {
        this.mModelUrl = str;
        this.mModelVersion = num;
        this.mStatusCode = statusEnums;
    }

    public String getModelUrl() {
        return this.mModelUrl;
    }

    public Integer getModelVersion() {
        return this.mModelVersion;
    }

    public StatusEnums getStatusCode() {
        return this.mStatusCode;
    }

    public String toString() {
        StringBuilder sbD = AbstractC19915fE3.d("ModelMetaData{mModelUrl=");
        sbD.append(this.mModelUrl);
        sbD.append(",mModelVersion=");
        sbD.append(this.mModelVersion);
        sbD.append(",mStatusCode=");
        sbD.append(this.mStatusCode);
        sbD.append("}");
        return sbD.toString();
    }
}
