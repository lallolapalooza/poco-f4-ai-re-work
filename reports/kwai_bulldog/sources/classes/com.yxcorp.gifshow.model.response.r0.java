package com.yxcorp.gifshow.model.response;

import com.yxcorp.bugly.CrashReporter;
import com.yxcorp.utility.n1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: YlabModelConfigResponse.java */
/* JADX INFO: loaded from: classes4.dex */
public class r0 implements c, Serializable, Cloneable {

    @lb.c("modelUrls")
    public List<a> mModelConfigs;

    /* JADX INFO: compiled from: YlabModelConfigResponse.java */
    public static class a implements Cloneable {

        @lb.c("urls")
        public List<C0763a> mModelUrls;

        @lb.c("resourceKey")
        public String mResourceKey;

        /* JADX INFO: renamed from: com.yxcorp.gifshow.model.response.r0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: YlabModelConfigResponse.java */
        public static class C0763a {

            @lb.c("cdn")
            public String mCdn;

            @lb.c("url")
            public String mUrl;
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a clone() {
            try {
                a aVar = (a) super.clone();
                if (this.mModelUrls != null) {
                    aVar.mModelUrls = new ArrayList(this.mModelUrls);
                } else {
                    aVar.mModelUrls = new ArrayList();
                }
                return aVar;
            } catch (CloneNotSupportedException e12) {
                CrashReporter.logCheckedException(e12, "YlabModelConfigResponse$ModelConfig.class", "clone", 55);
                return null;
            }
        }

        public String toString() {
            try {
                a aVar = (a) super.clone();
                if (this.mModelUrls != null) {
                    aVar.mModelUrls = new ArrayList(this.mModelUrls);
                } else {
                    aVar.mModelUrls = new ArrayList();
                }
                List<C0763a> list = this.mModelUrls;
                if (list == null || list.size() <= 0) {
                    return this.mResourceKey + " : null";
                }
                return this.mResourceKey + " : " + this.mModelUrls.get(0).mUrl;
            } catch (CloneNotSupportedException e12) {
                CrashReporter.logCheckedException(e12, "YlabModelConfigResponse$ModelConfig.class", "toString", 71);
                return null;
            }
        }
    }

    @Override // com.yxcorp.gifshow.model.response.c
    public int getCdnCount(String str) {
        List<a> list = this.mModelConfigs;
        if (list == null) {
            return 0;
        }
        for (a aVar : list) {
            if (n1.h(aVar.mResourceKey, str) && !com.yxcorp.utility.i.d(aVar.mModelUrls)) {
                return aVar.mModelUrls.size();
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("getCdnCount getModelConfig null");
        return 0;
    }

    @Override // com.yxcorp.gifshow.model.response.c
    public String getDownloadUrlSuffix(String str, int i12) {
        List<a> list = this.mModelConfigs;
        if (list == null) {
            return null;
        }
        for (a aVar : list) {
            if (n1.h(aVar.mResourceKey, str) && !com.yxcorp.utility.i.d(aVar.mModelUrls) && i12 < aVar.mModelUrls.size()) {
                return aVar.mModelUrls.get(i12).mUrl;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("getDownloadUrlSuffix null");
        return null;
    }

    public List<String> getModelList() {
        ArrayList arrayList = new ArrayList();
        List<a> list = this.mModelConfigs;
        if (list != null) {
            Iterator<a> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next().mResourceKey);
            }
        }
        return arrayList;
    }

    public String toString() {
        if (this.mModelConfigs == null) {
            return "mModelConfigs null";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("mModelConfigs size :  " + this.mModelConfigs.size() + " \n");
        Iterator<a> it2 = this.mModelConfigs.iterator();
        while (it2.hasNext()) {
            sb2.append(it2.next().toString() + "\n");
        }
        return sb2.toString();
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public r0 m184clone() {
        try {
            r0 r0Var = (r0) super.clone();
            if (this.mModelConfigs != null) {
                r0Var.mModelConfigs = new ArrayList(this.mModelConfigs);
            } else {
                r0Var.mModelConfigs = new ArrayList();
            }
            return r0Var;
        } catch (CloneNotSupportedException e12) {
            CrashReporter.logCheckedException(e12, "YlabModelConfigResponse.class", "clone", -118);
            return null;
        }
    }
}
