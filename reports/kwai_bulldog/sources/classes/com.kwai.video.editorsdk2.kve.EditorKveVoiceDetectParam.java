package com.kwai.video.editorsdk2.kve;

/* JADX INFO: loaded from: classes16.dex */
public class EditorKveVoiceDetectParam {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f30372a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double f30373b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f30374c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f30375d;

    public EditorKveVoiceDetectParam() {
        this.f30372a = 0.0d;
        this.f30373b = 0.0d;
    }

    public double getEndTime() {
        return this.f30373b;
    }

    public String getFileName() {
        return this.f30374c;
    }

    public String getModelPath() {
        return this.f30375d;
    }

    public double getStartTime() {
        return this.f30372a;
    }

    public void setEndTime(double d12) {
        this.f30373b = d12;
    }

    public void setFileName(String str) {
        this.f30374c = str;
    }

    public void setModelPath(String str) {
        this.f30375d = str;
    }

    public void setStartTime(double d12) {
        this.f30372a = d12;
    }

    public EditorKveVoiceDetectParam(double d12, double d13, String str, String str2) {
        this.f30372a = d12;
        this.f30373b = d13;
        this.f30374c = str;
        this.f30375d = str2;
    }
}
