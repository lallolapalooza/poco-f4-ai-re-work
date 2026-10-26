package com.xiaomi.ai.streamplayer;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.xiaomi.ai.utils.Log;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class d extends com.xiaomi.ai.streamplayer.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f17231e = "Mp3HardDecoder";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f17232g = "OMX.google.mp3.decoder";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PipedOutputStream f17233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PipedInputStream f17234b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i f17235c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f17236d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private MediaCodec f17237f;

    public class a implements j {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f17239b;

        private a() {
        }

        @Override // com.xiaomi.ai.streamplayer.j
        public void a(i iVar) {
        }

        @Override // com.xiaomi.ai.streamplayer.j
        public void a(i iVar, Object obj, byte[] bArr) {
            if (d.this.f17236d) {
                d.this.c();
            } else {
                d.this.a(bArr);
                this.f17239b = true;
            }
        }

        @Override // com.xiaomi.ai.streamplayer.j
        public void a(i iVar, String str) {
            d.this.c();
        }

        @Override // com.xiaomi.ai.streamplayer.j
        public void b(i iVar) {
            if (d.this.f17236d || !this.f17239b) {
                d.this.c();
            } else {
                d.this.f();
            }
        }
    }

    public d() {
        b bVar = new b();
        this.f17235c = bVar;
        bVar.a(new a());
        this.f17234b = new PipedInputStream(51200);
        try {
            this.f17233a = new PipedOutputStream(this.f17234b);
        } catch (IOException e10) {
            Log.e(f17231e, "Mp3HardDecoder ", e10);
        }
        this.f17235c.a(this.f17234b);
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void a() {
        this.f17235c.a();
        try {
            try {
                this.f17237f = MediaCodec.createByCodecName(f17232g);
            } catch (IllegalArgumentException e10) {
                Log.e(f17231e, " start createByCodecName ", e10);
            }
            if (this.f17237f == null) {
                Log.e(f17231e, "OMX.google.mp3.decoder not get");
                this.f17237f = MediaCodec.createDecoderByType("audio/mpeg");
            }
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setString("mime", "audio/mpeg");
            this.f17237f.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
            this.f17237f.start();
        } catch (IOException e11) {
            throw new RuntimeException(e11);
        }
    }

    public void a(byte[] bArr) {
        try {
            int iDequeueInputBuffer = this.f17237f.dequeueInputBuffer(-1L);
            if (iDequeueInputBuffer < 0) {
                throw new RuntimeException("dequeueInputBuffer-1");
            }
            ByteBuffer[] inputBuffers = this.f17237f.getInputBuffers();
            if (inputBuffers.length <= iDequeueInputBuffer) {
                Log.e(f17231e, " putBuffer byteBuffers.length > inputBufIndex false");
                return;
            }
            ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(bArr);
            this.f17237f.queueInputBuffer(iDequeueInputBuffer, 0, bArr.length, 0L, 0);
        } catch (IllegalStateException e10) {
            Log.e(f17231e, "release ", e10);
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void a(byte[] bArr, int i10, int i11) {
        if (bArr == null || i10 + i11 > bArr.length || i11 <= 0) {
            return;
        }
        try {
            this.f17233a.write(bArr, i10, i11);
        } catch (IOException e10) {
            Log.e(f17231e, "putEncodedBuffer ", e10);
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void b() {
        try {
            this.f17233a.close();
        } catch (IOException e10) {
            Log.e(f17231e, "release ", e10);
        }
        try {
            this.f17234b.close();
        } catch (IOException e11) {
            Log.e(f17231e, "release ", e11);
        }
        try {
            this.f17237f.stop();
        } catch (IllegalStateException e12) {
            Log.e(f17231e, "stop ", e12);
        }
        this.f17237f.release();
        Log.e(f17231e, "Mp3HardDecoder release");
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void c() {
        d();
        this.f17235c.b();
        this.f17236d = true;
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void d() {
        try {
            this.f17233a.close();
        } catch (IOException e10) {
            Log.e(f17231e, "release " + e10);
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public com.xiaomi.ai.streamplayer.a.C0169a e() {
        if (this.f17236d) {
            return null;
        }
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int iDequeueOutputBuffer = this.f17237f.dequeueOutputBuffer(bufferInfo, 0L);
            com.xiaomi.ai.streamplayer.a.C0169a c0169a = new com.xiaomi.ai.streamplayer.a.C0169a();
            if (iDequeueOutputBuffer == -2) {
                MediaFormat outputFormat = this.f17237f.getOutputFormat();
                c0169a.f17201c = 16;
                c0169a.f17200b = outputFormat.getInteger("channel-count");
                c0169a.f17199a = outputFormat.getInteger("sample-rate");
            }
            if (iDequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer = this.f17237f.getOutputBuffers()[iDequeueOutputBuffer];
                byte[] bArr = new byte[bufferInfo.size];
                c0169a.f17202d = bufferInfo.flags == 4;
                byteBuffer.get(bArr);
                c0169a.f17203e = bArr;
                byteBuffer.clear();
                this.f17237f.releaseOutputBuffer(iDequeueOutputBuffer, false);
            }
            return c0169a;
        } catch (IllegalStateException e10) {
            Log.e(f17231e, "getDecodedBuffer ", e10);
            return null;
        }
    }

    public void f() {
        try {
            int iDequeueInputBuffer = this.f17237f.dequeueInputBuffer(-1L);
            if (iDequeueInputBuffer < 0) {
                throw new RuntimeException("dequeueInputBuffer-1");
            }
            ByteBuffer[] inputBuffers = this.f17237f.getInputBuffers();
            if (inputBuffers.length <= iDequeueInputBuffer) {
                Log.e(f17231e, " putEofMsg byteBuffers.length > inputBufIndex false");
            } else {
                inputBuffers[iDequeueInputBuffer].clear();
                this.f17237f.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
            }
        } catch (IllegalStateException e10) {
            Log.e(f17231e, "release ", e10);
        }
    }
}
