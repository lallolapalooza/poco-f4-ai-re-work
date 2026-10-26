package com.xiaomi.ai;

/* JADX INFO: loaded from: classes2.dex */
public class AsrRequest {
    public static final int OPUS_BITRATES_32K = 32000;
    public static final int OPUS_BITRATES_64K = 64000;
    public static final int OPUS_FRAMESIZE_1280 = 1280;
    public static final int OPUS_FRAMESIZE_640 = 640;
    public static final short USE_ENCODER_BV32_FLOAT = 2;
    public static final short USE_ENCODER_OPUS = 4;
    public static final short USE_ENCODER_PCM = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f17071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f17072b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f17076f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f17081k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public NlpRequest f17084n;
    public String vendorStr;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f17073c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public DataInputMode f17074d = DataInputMode.DATA_INPUT_MODE_RECORDER;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public VadMode f17075e = VadMode.VAD_MODE_LOCAL;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f17077g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public short f17078h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f17079i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f17080j = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f17082l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17083m = 0;

    public enum DataInputMode {
        DATA_INPUT_MODE_RECORDER,
        DATA_INPUT_MODE_BUFFER
    }

    public enum VadMode {
        VAD_MODE_LOCAL,
        VAD_MODE_CLOUD
    }

    public String a() {
        return this.vendorStr;
    }

    public void enableEncoder(boolean z10) {
        this.f17078h = (short) (z10 ? this.f17078h | 256 : this.f17078h & 65279);
    }

    public void setDataInputMode(DataInputMode dataInputMode) {
        this.f17074d = dataInputMode;
    }

    public void setDialect(String str) {
        this.f17081k = str;
    }

    public void setDisableAsrTimeout(boolean z10) {
        this.f17082l = z10;
    }

    public void setEncodeMode(short s10, int i10, int i11) {
        this.f17078h = (short) (this.f17078h | s10);
        if (s10 == 4) {
            this.f17079i = i10;
            this.f17080j = i11;
        }
    }

    public void setExtraNlp(NlpRequest nlpRequest) {
        this.f17084n = nlpRequest;
    }

    public void setForSai(boolean z10) {
        this.f17072b = z10;
    }

    public void setLang(String str) {
        this.f17076f = str;
    }

    public void setMaxAudioTime(int i10) {
        this.f17083m = i10;
    }

    public void setPreAsrTrack(int i10) {
        this.f17077g = i10;
    }

    public void setRemoveEndPunctuation(boolean z10) {
        this.f17073c = z10;
    }

    public void setUseVad(boolean z10) {
        this.f17071a = z10;
    }

    public void setVadMode(VadMode vadMode) {
        this.f17075e = vadMode;
    }
}
