package com.quicinc.voice.activation.audiorecord;

import G.i;
import G.q;
import android.media.AudioRecord;
import android.os.ParcelFileDescriptor;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import p003d.g;
import p009j.h;
import p009j.n;

/* JADX INFO: loaded from: classes.dex */
final class OneShotAudioRecordAction extends RecursiveAction {
    private final h mAudioStreamProvider;
    private final i mAudioStreamSaver;
    private n mOnCompleteListener;
    private final int mSessionNum;
    private final ParcelFileDescriptor mWritableParcelFileDescriptor;
    private final int mAudioRecordBufferSize = p009j.i.a();
    private final int mReadBufferSize = p009j.i.d();

    public OneShotAudioRecordAction(int i2, h hVar, ParcelFileDescriptor parcelFileDescriptor, i iVar) {
        this.mSessionNum = i2;
        this.mAudioStreamProvider = hVar;
        this.mWritableParcelFileDescriptor = parcelFileDescriptor;
        this.mAudioStreamSaver = iVar;
        iVar.l();
    }

    public final void a() {
        I.b.a(g.d().getPackageName(), 300000L, "QVA-HOTWORD");
    }

    public final AudioRecord b() {
        return I.a.c(this.mAudioRecordBufferSize, this.mSessionNum);
    }

    public void c(n nVar) {
        this.mOnCompleteListener = nVar;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0109 A[PHI: r0
      0x0109: PHI (r0v15 j.n) = (r0v14 j.n), (r0v20 j.n) binds: [B:43:0x0107, B:28:0x00b8] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // java.util.concurrent.RecursiveAction
    public void compute() {
        AudioRecord audioRecordB;
        n nVar;
        q.h("OneShotAudioRecordAction - compute enter");
        q.d("OneShotRecording");
        try {
            a();
            q.d("CallInitAudioRecord");
            audioRecordB = b();
            try {
                q.m();
                q.d("CallStartRecording");
                audioRecordB.startRecording();
                q.m();
            } catch (Exception e2) {
                e = e2;
                q.h(Arrays.toString(e.getStackTrace()));
            }
        } catch (Exception e3) {
            e = e3;
            audioRecordB = null;
        }
        if (audioRecordB != null) {
            try {
                if (audioRecordB.getRecordingState() == 3) {
                    try {
                        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(this.mWritableParcelFileDescriptor);
                        try {
                            byte[] bArr = new byte[this.mReadBufferSize];
                            while (true) {
                                int i2 = audioRecordB.read(bArr, 0, this.mReadBufferSize);
                                if (i2 <= 0) {
                                    break;
                                }
                                autoCloseOutputStream.write(bArr, 0, i2);
                                this.mAudioStreamProvider.l(bArr, i2);
                                this.mAudioStreamSaver.v(bArr, 0, i2);
                                if (isCancelled()) {
                                    break;
                                }
                                q.h("readSize=" + i2);
                            }
                            autoCloseOutputStream.close();
                            q.h("finally - stop");
                            try {
                                this.mWritableParcelFileDescriptor.close();
                            } catch (Exception unused) {
                            }
                            audioRecordB.stop();
                            audioRecordB.release();
                            this.mAudioStreamProvider.a();
                            this.mAudioStreamSaver.g("\nwriting last keyword end index " + this.mAudioStreamProvider.d());
                            this.mAudioStreamSaver.d();
                            nVar = this.mOnCompleteListener;
                            if (nVar != null) {
                                nVar.a();
                                this.mOnCompleteListener = null;
                            }
                            q.m();
                            q.h("OneShotAudioRecordAction - compute exit");
                            return;
                        } catch (Throwable th) {
                            try {
                                autoCloseOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (Exception e4) {
                        q.h(Arrays.toString(e4.getStackTrace()));
                        q.h("finally - stop");
                        try {
                            this.mWritableParcelFileDescriptor.close();
                        } catch (Exception unused2) {
                        }
                        audioRecordB.stop();
                        audioRecordB.release();
                        this.mAudioStreamProvider.a();
                        this.mAudioStreamSaver.g("\nwriting last keyword end index " + this.mAudioStreamProvider.d());
                        this.mAudioStreamSaver.d();
                        nVar = this.mOnCompleteListener;
                        if (nVar != null) {
                            nVar.a();
                            this.mOnCompleteListener = null;
                        }
                    }
                }
            } catch (Throwable th3) {
                q.h("finally - stop");
                try {
                    this.mWritableParcelFileDescriptor.close();
                } catch (Exception unused3) {
                }
                audioRecordB.stop();
                audioRecordB.release();
                this.mAudioStreamProvider.a();
                this.mAudioStreamSaver.g("\nwriting last keyword end index " + this.mAudioStreamProvider.d());
                this.mAudioStreamSaver.d();
                n nVar2 = this.mOnCompleteListener;
                if (nVar2 != null) {
                    nVar2.a();
                    this.mOnCompleteListener = null;
                }
                q.m();
                throw th3;
            }
        }
        q.h("AudioRecord state error, cancelled");
        this.mAudioStreamSaver.g("AudioRecord initialization error!");
        if (audioRecordB != null) {
            audioRecordB.release();
        }
        this.mAudioStreamProvider.a();
        n nVar3 = this.mOnCompleteListener;
        if (nVar3 != null) {
            nVar3.a();
            this.mOnCompleteListener = null;
        }
        q.m();
    }
}
