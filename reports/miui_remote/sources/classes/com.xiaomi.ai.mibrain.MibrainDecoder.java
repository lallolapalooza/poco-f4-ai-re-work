package com.xiaomi.ai.mibrain;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MibrainDecoder {
    public MibrainDecoderIO mMibrainDecoderIO;

    public static class AudioInfo {
        public int bit;
        public int bitrate;
        public int chinels;
        public int mpeg_layer;
        public int sample_rate;

        public AudioInfo(int i10, int i11, int i12, int i13, int i14) {
            this.sample_rate = i10;
            this.bitrate = i11;
            this.chinels = i12;
            this.mpeg_layer = i13;
            this.bit = i14;
        }
    }

    public interface MibrainDecoderIO {
        int read(byte[] bArr, int i10);

        int write(byte[] bArr, int i10, AudioInfo audioInfo);
    }

    public MibrainDecoder(MibrainDecoderIO mibrainDecoderIO) {
        this.mMibrainDecoderIO = mibrainDecoderIO;
    }

    public abstract int cancel();

    public abstract int init();

    public abstract void release();

    public abstract int start();

    public abstract int stop();
}
