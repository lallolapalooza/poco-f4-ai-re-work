package com.xiaomi.ai.mibrain;

/* JADX INFO: loaded from: classes2.dex */
public class MibrainMp3Decoder extends MibrainDecoder {
    private long mInstance;

    static {
        System.loadLibrary("mibraindec");
    }

    public MibrainMp3Decoder(MibrainDecoder.MibrainDecoderIO mibrainDecoderIO) {
        super(mibrainDecoderIO);
    }

    private static native int cancel(long j10);

    private static native int deleteInstance(long j10);

    private int nativeCallBackDataIn(byte[] bArr, int i10) {
        return this.mMibrainDecoderIO.read(bArr, i10);
    }

    private void nativeCallBackDataOut(byte[] bArr, int i10, int i11, int i12, int i13, int i14, int i15) {
        this.mMibrainDecoderIO.write(bArr, i10, new MibrainDecoder.AudioInfo(i11, i12, i13, i14, i15));
    }

    private static native long newInstance();

    private static native int start(Object obj, long j10);

    private static native int stop(long j10);

    @Override // com.xiaomi.ai.mibrain.MibrainDecoder
    public int cancel() {
        cancel(this.mInstance);
        return 0;
    }

    @Override // com.xiaomi.ai.mibrain.MibrainDecoder
    public int init() {
        long jNewInstance = newInstance();
        this.mInstance = jNewInstance;
        return jNewInstance != 0 ? 0 : -1;
    }

    @Override // com.xiaomi.ai.mibrain.MibrainDecoder
    public void release() {
        long j10 = this.mInstance;
        if (j10 != 0) {
            deleteInstance(j10);
        }
    }

    @Override // com.xiaomi.ai.mibrain.MibrainDecoder
    public int start() {
        start(this, this.mInstance);
        return 0;
    }

    @Override // com.xiaomi.ai.mibrain.MibrainDecoder
    public int stop() {
        stop(this.mInstance);
        return 0;
    }
}
