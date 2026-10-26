package com.xiaomi.ai.streamplayer;

import com.xiaomi.ai.mibrain.MibrainMp3Decoder;
import com.xiaomi.ai.utils.Log;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes2.dex */
public class f extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f17240a = "Mp3SoftDecoder";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private PipedOutputStream f17243d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f17245f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MibrainMp3Decoder f17241b = new MibrainMp3Decoder(new g(this));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BlockingQueue<a.C0169a> f17244e = new LinkedBlockingDeque();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Thread f17246g = new Thread(new h(this));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PipedInputStream f17242c = new PipedInputStream();

    public f() {
        try {
            this.f17243d = new PipedOutputStream(this.f17242c);
        } catch (IOException e10) {
            Log.e(f17240a, "PIPE IO ", e10);
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void a() {
        this.f17241b.init();
        this.f17246g.start();
    }

    public void a(byte[] bArr, int i10) {
        if (bArr != null) {
            try {
                this.f17243d.write(bArr, 0, i10);
            } catch (IOException e10) {
                Log.e(f17240a, "write ", e10);
            }
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void a(byte[] bArr, int i10, int i11) {
        try {
            this.f17243d.write(bArr, 0, i11);
        } catch (IOException e10) {
            Log.e(f17240a, "putEncodedBuffe ", e10);
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void b() {
        synchronized (this) {
            if (!this.f17245f) {
                this.f17241b.cancel();
                this.f17246g.interrupt();
            }
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void c() {
        synchronized (this) {
            if (!this.f17245f) {
                this.f17241b.cancel();
                this.f17246g.interrupt();
            }
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public void d() {
        try {
            this.f17243d.close();
        } catch (IOException e10) {
            Log.e(f17240a, "end ", e10);
        }
    }

    @Override // com.xiaomi.ai.streamplayer.a
    public a.C0169a e() {
        try {
            return this.f17244e.take();
        } catch (InterruptedException e10) {
            Log.e(f17240a, "getDecodedBuffer ", e10);
            return null;
        }
    }

    public void f() {
        this.f17241b.start();
        try {
            this.f17242c.close();
        } catch (IOException unused) {
        }
        a.C0169a c0169a = new a.C0169a();
        c0169a.f17202d = true;
        try {
            this.f17244e.put(c0169a);
        } catch (InterruptedException e10) {
            Log.e(f17240a, af.e.f1588h, e10);
        }
    }
}
