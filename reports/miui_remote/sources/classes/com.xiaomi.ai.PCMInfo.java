package com.xiaomi.ai;

/* JADX INFO: loaded from: classes2.dex */
public class PCMInfo {
    public int bit;
    public int channels;
    public boolean eof;
    public byte[] pcm;
    public int sample_rate;

    public PCMInfo(int i10, int i11, int i12, boolean z10, byte[] bArr) {
        this.sample_rate = i10;
        this.channels = i11;
        this.bit = i12;
        this.eof = z10;
        this.pcm = bArr;
    }

    public int getBit() {
        return this.bit;
    }

    public int getChannels() {
        return this.channels;
    }

    public boolean getEOF() {
        return this.eof;
    }

    public byte[] getPCM() {
        return this.pcm;
    }

    public int getSampleRate() {
        return this.sample_rate;
    }
}
