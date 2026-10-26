package com.xiaomi.ai.streamplayer;

import android.media.AudioTrack;
import com.xiaomi.ai.PCMInfo;
import com.xiaomi.ai.utils.Log;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f17252a = "MiSpeechSDK:StramingPlayer";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f17253c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f17254d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f17255e = 3;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f17256f = 3;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a f17259h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private com.xiaomi.ai.streamplayer.a f17261j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f17262k;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f17257b = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private b f17260i = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f17258g = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f17263l = 1;

    public interface a {
        void a(k kVar);

        void a(k kVar, int i10, String str);

        void a(k kVar, AudioTrack audioTrack);

        void a(k kVar, PCMInfo pCMInfo);
    }

    public class b extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f17264a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f17265b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f17266c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private AudioTrack f17268e;

        private b() {
            this.f17264a = 5;
            this.f17266c = 0;
        }

        private void a() {
            if (this.f17268e != null) {
                try {
                    if (k.this.f17263l == 1) {
                        this.f17268e.stop();
                    }
                } catch (Exception unused) {
                }
                synchronized (this) {
                    this.f17268e.release();
                    this.f17268e = null;
                    Log.d(k.f17252a, "releaseAudioTrack");
                }
            }
        }

        private void a(byte[] bArr) {
            if (this.f17268e == null || bArr == null || k.this.f17257b) {
                return;
            }
            this.f17268e.write(bArr, 0, bArr.length);
        }

        private boolean a(int i10, int i11) {
            AudioTrack audioTrack;
            if (i11 == 1) {
                audioTrack = new AudioTrack(k.this.f17262k, i10, 4, 2, AudioTrack.getMinBufferSize(i10, 4, 2), 1);
            } else {
                if (i11 != 2) {
                    throw new IllegalArgumentException(android.support.v4.media.c.a("not supported chanel numbers", i11));
                }
                audioTrack = new AudioTrack(k.this.f17262k, i10, 12, 2, AudioTrack.getMinBufferSize(i10, 12, 2), 1);
            }
            this.f17268e = audioTrack;
            if (this.f17268e.getState() != 0) {
                if (k.this.f17263l == 1) {
                    this.f17268e.play();
                }
                return true;
            }
            if (k.this.f17259h == null) {
                return false;
            }
            k.this.f17259h.a(k.this, 15, "STATE_UNINITIALIZED");
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            AudioTrack audioTrack = this.f17268e;
            synchronized (this) {
                if (audioTrack != null) {
                    try {
                        if (k.this.f17263l == 1) {
                            audioTrack.pause();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }

        private void c() {
            if (this.f17268e == null || k.this.f17257b) {
                return;
            }
            this.f17268e.flush();
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0040  */
        /* JADX WARN: Code duplicated, block: B:19:0x004a  */
        /* JADX WARN: Code duplicated, block: B:20:0x004c  */
        /* JADX WARN: Code duplicated, block: B:22:0x0050  */
        /* JADX WARN: Code duplicated, block: B:27:0x0069  */
        /* JADX WARN: Code duplicated, block: B:29:0x0071  */
        /* JADX WARN: Code duplicated, block: B:30:0x0091  */
        /* JADX WARN: Code duplicated, block: B:34:0x009b A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:35:0x009d  */
        /* JADX WARN: Code duplicated, block: B:40:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:52:0x00d3 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:61:0x00ec A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:68:0x00fd  */
        /* JADX WARN: Code duplicated, block: B:72:0x0109  */
        /* JADX WARN: Code duplicated, block: B:90:0x0124 A[EDGE_INSN: B:90:0x0124->B:81:0x0124 BREAK  A[LOOP:0: B:3:0x000f->B:77:0x011e], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:91:0x00a1 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:92:0x0124 A[EDGE_INSN: B:92:0x0124->B:81:0x0124 BREAK  A[LOOP:0: B:3:0x000f->B:77:0x011e], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:96:0x011e A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AudioTrack audioTrack;
            AudioTrack audioTrack2;
            super.run();
            int i10 = k.this.f17263l;
            boolean z10 = false;
            com.xiaomi.ai.streamplayer.a.C0169a c0169a = null;
            boolean z11 = false;
            int length = 0;
            int i11 = 0;
            while (!k.this.f17257b) {
                k kVar = k.this;
                if (!z10) {
                    com.xiaomi.ai.streamplayer.a.C0169a c0169aE = kVar.f17261j.e();
                    if (c0169aE != null && c0169aE.f17200b > 0 && !c0169aE.a(c0169a)) {
                        a();
                        if (!a(c0169aE.f17199a, c0169aE.f17200b)) {
                            break;
                        }
                        if (!k.this.f17257b) {
                            break;
                            break;
                        }
                        if (c0169aE != null) {
                            if (c0169aE.f17203e != null) {
                                if (k.this.f17259h != null) {
                                    k.this.f17259h.a(k.this, this.f17268e);
                                }
                                if (i10 == 2) {
                                    a(c0169aE.f17203e);
                                } else if (k.this.f17259h != null) {
                                    length += c0169aE.f17203e.length;
                                    k.this.f17259h.a(k.this, new PCMInfo(c0169aE.f17199a, c0169aE.f17200b, c0169aE.f17201c, c0169aE.f17202d, c0169aE.f17203e));
                                }
                                z11 = true;
                            }
                            z10 = c0169aE.f17202d;
                            if (z10) {
                                if (i10 == 1) {
                                    c();
                                }
                                k.this.f17259h.a(k.this);
                                break;
                            }
                        }
                        if (!k.this.f17257b) {
                            break;
                            break;
                        }
                        c0169a = c0169aE;
                        if (z11) {
                            this.f17266c++;
                        }
                        if (i10 == 2) {
                            this.f17266c++;
                        }
                        if (z11) {
                            this.f17265b = audioTrack.getPlaybackHeadPosition();
                        }
                        if (!z10) {
                        }
                        i11 = length;
                    } else {
                        if (!k.this.f17257b) {
                            break;
                        }
                        if (c0169aE != null) {
                            if (c0169aE.f17203e != null) {
                                if (k.this.f17259h != null && !z11) {
                                    k.this.f17259h.a(k.this, this.f17268e);
                                }
                                if (i10 == 2) {
                                    a(c0169aE.f17203e);
                                } else if (k.this.f17259h != null) {
                                    length += c0169aE.f17203e.length;
                                    k.this.f17259h.a(k.this, new PCMInfo(c0169aE.f17199a, c0169aE.f17200b, c0169aE.f17201c, c0169aE.f17202d, c0169aE.f17203e));
                                }
                                z11 = true;
                            }
                            z10 = c0169aE.f17202d;
                            if (z10) {
                                if (i10 == 1) {
                                    c();
                                }
                                k.this.f17259h.a(k.this);
                                break;
                            }
                        }
                        if (!k.this.f17257b) {
                            c0169a = c0169aE;
                            if (z11 && z10 && (audioTrack2 = this.f17268e) != null && this.f17265b != 0 && audioTrack2.getPlaybackHeadPosition() == this.f17265b) {
                                this.f17266c++;
                            }
                            if (i10 == 2 && z11 && z10 && this.f17268e != null && length == i11) {
                                this.f17266c++;
                            }
                            if (z11 && (audioTrack = this.f17268e) != null) {
                                this.f17265b = audioTrack.getPlaybackHeadPosition();
                            }
                            if (!z10 && this.f17266c >= 5) {
                                Log.e(k.f17252a, "on end");
                                if (k.this.f17259h == null) {
                                    break;
                                }
                                k.this.f17259h.a(k.this);
                                break;
                            }
                            i11 = length;
                        } else {
                            break;
                        }
                    }
                } else {
                    if (kVar.f17257b) {
                        break;
                    }
                    try {
                        synchronized (this) {
                            wait(40L);
                        }
                        if (k.this.f17257b) {
                            break;
                        }
                        if (z11) {
                            this.f17266c++;
                        }
                        if (i10 == 2) {
                            this.f17266c++;
                        }
                        if (z11) {
                            this.f17265b = audioTrack.getPlaybackHeadPosition();
                        }
                        if (!z10) {
                        }
                        i11 = length;
                    } catch (InterruptedException unused) {
                    }
                }
            }
            a();
            k.this.f17261j.b();
            Log.e(k.f17252a, "release decoder and track");
        }

        @Override // java.lang.Thread
        public void start() {
            super.start();
        }
    }

    public k(int i10) {
        this.f17262k = i10;
    }

    public synchronized void a() {
        if (this.f17258g == 2 && !this.f17257b) {
            this.f17257b = false;
            this.f17258g = 3;
            this.f17261j.a();
            this.f17260i.start();
        }
    }

    public void a(int i10) {
        this.f17263l = i10;
    }

    public void a(a aVar) {
        this.f17259h = aVar;
    }

    public synchronized void a(String str) {
        if (!"mp3".equalsIgnoreCase(str)) {
            throw new RuntimeException("not supported  tts codec !!");
        }
        this.f17261j = TTSDecoderConfig.sUseHardDecoder ? new d() : new f();
        this.f17258g = 2;
    }

    public void a(byte[] bArr, int i10, int i11) {
        this.f17261j.a(bArr, i10, i11);
    }

    public synchronized void b() {
        if (this.f17258g != 3) {
            return;
        }
        this.f17261j.d();
        this.f17258g = 3;
    }

    public synchronized void c() {
        this.f17257b = true;
        this.f17260i.b();
        b();
        this.f17261j.c();
    }
}
