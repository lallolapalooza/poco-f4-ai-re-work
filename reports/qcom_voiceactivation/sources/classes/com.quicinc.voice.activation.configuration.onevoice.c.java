package com.quicinc.voice.activation.configuration.onevoice;

import java.util.ArrayList;

/* JADX INFO: compiled from: ModelCollection.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f388a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f389b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f390c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f391d;

    public c(String str) {
        this.f388a = str;
    }

    public void a(String str, String str2) {
        this.f389b.add(new b(str, str2));
    }

    public void b(String str, String str2) {
        this.f390c.add(new b(str, str2));
    }

    public ArrayList c() {
        return this.f389b;
    }

    public String d() {
        return this.f388a;
    }

    public ArrayList e() {
        return this.f390c;
    }

    public b f() {
        return this.f391d;
    }

    public void g(String str) {
        this.f391d = new b(str, null);
    }

    public String toString() {
        return "ModelCollection{KwHash=" + this.f388a + ", FsModelList=" + this.f389b + ", MsModelList=" + this.f390c + ", SsUvBaseModel=" + this.f391d + '}';
    }
}
