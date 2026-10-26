package com.xiaomi.ai.streamplayer;

import com.xiaomi.ai.mibrain.MibrainDecoder;
import com.xiaomi.ai.utils.Log;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
class g implements MibrainDecoder.MibrainDecoderIO {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f17247a;

    public g(f fVar) {
        this.f17247a = fVar;
    }

    @Override // com.xiaomi.ai.mibrain.MibrainDecoder.MibrainDecoderIO
    public int read(byte[] bArr, int i10) {
        try {
            return this.f17247a.f17242c.read(bArr, 0, i10);
        } catch (IOException e10) {
            Log.e("Mp3SoftDecoder", "MibrainMp3Decoder read ", e10);
            return -1;
        }
    }

    @Override // com.xiaomi.ai.mibrain.MibrainDecoder.MibrainDecoderIO
    public int write(byte[] bArr, int i10, MibrainDecoder.AudioInfo audioInfo) {
        a.C0169a c0169a = new a.C0169a();
        c0169a.f17200b = audioInfo.chinels;
        c0169a.f17201c = audioInfo.bit;
        c0169a.f17199a = audioInfo.sample_rate;
        byte[] bArr2 = new byte[i10];
        System.arraycopy(bArr, 0, bArr2, 0, i10);
        c0169a.f17203e = bArr2;
        try {
            this.f17247a.f17244e.put(c0169a);
        } catch (InterruptedException e10) {
            Log.e("Mp3SoftDecoder", af.e.f1588h, e10);
        }
        return i10;
    }
}
