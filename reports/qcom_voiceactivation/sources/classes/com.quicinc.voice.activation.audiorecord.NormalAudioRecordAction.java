package com.quicinc.voice.activation.audiorecord;

import G.i;
import G.q;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import com.quicinc.voice.activation.onlineepd.EPD;
import com.quicinc.voice.activation.vad.DNNVAD;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.concurrent.RecursiveAction;
import p003d.g;
import p009j.l;

/* JADX INFO: loaded from: classes.dex */
final class NormalAudioRecordAction extends RecursiveAction {
    private final AudioRecord mAudioRecord;
    private final i mAudioStreamSaver;
    private DetectionType mDetectionType;
    private final EPD mEPD;
    private final l mListener;
    private final DNNVAD mVAD;

    enum DetectionType {
        UNKNOWN,
        EPD,
        VAD
    }

    public NormalAudioRecordAction(int i2, l lVar, i iVar, EPD epd) {
        DetectionType detectionType = DetectionType.EPD;
        this.mDetectionType = detectionType;
        this.mListener = lVar;
        this.mAudioRecord = new AudioRecord(i2, 16000, 16, 2, p009j.i.a());
        this.mAudioStreamSaver = iVar;
        this.mEPD = epd;
        this.mVAD = null;
        this.mDetectionType = detectionType;
    }

    public final void a() {
        I.b.a(g.d().getPackageName(), 300000L, "QVA-TRAINING");
    }

    public final short[] b(byte[] bArr, int i2) {
        short[] sArr = new short[i2 / 2];
        ByteBuffer.wrap(bArr, 0, i2).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sArr);
        return sArr;
    }

    public final boolean c() {
        AudioManager audioManager = (AudioManager) g.d().getSystemService("audio");
        if (audioManager == null) {
            q.j("AudioManager null. Aborting.");
            return false;
        }
        int mode = audioManager.getMode();
        if (mode == 2 || mode == 3) {
            q.j("Voice call in progress. Aborting.");
            return false;
        }
        for (AudioRecordingConfiguration audioRecordingConfiguration : audioManager.getActiveRecordingConfigurations()) {
            AudioDeviceInfo audioDevice = audioRecordingConfiguration.getAudioDevice();
            if (audioDevice != null) {
                q.v("Audio Config: id=" + audioDevice.getId() + ", clientSessionId=" + audioRecordingConfiguration.getClientAudioSessionId() + ", source=" + audioRecordingConfiguration.getAudioSource() + ", type=" + audioDevice.getType() + ", isSilenced=" + audioRecordingConfiguration.isClientSilenced());
                if (audioRecordingConfiguration.getClientAudioSessionId() != this.mAudioRecord.getAudioSessionId()) {
                    q.j("Another audio record session found. Aborting.");
                    return false;
                }
            }
        }
        return true;
    }

    @Override // java.util.concurrent.RecursiveAction
    public void compute() {
        a();
        if (!c()) {
            g();
            this.mAudioRecord.release();
            return;
        }
        this.mAudioRecord.startRecording();
        q.w("startRecording");
        int iD = d();
        byte[] bArr = new byte[iD];
        while (true) {
            try {
                int i2 = this.mAudioRecord.read(bArr, 0, iD);
                if (i2 < 0) {
                    break;
                }
                short[] sArrB = b(bArr, i2);
                e(sArrB);
                if (!isCancelled()) {
                    this.mAudioStreamSaver.b(bArr, 0, i2);
                    ShortBuffer shortBufferWrap = ShortBuffer.wrap(sArrB);
                    if (this.mDetectionType != DetectionType.EPD) {
                        H.a aVarJ = j(shortBufferWrap);
                        q.v("VAD processVad result = " + aVarJ);
                        if (aVarJ.e() && aVarJ.c()) {
                            h(aVarJ);
                            break;
                        }
                    } else {
                        s.a aVarI = i(shortBufferWrap);
                        q.v("EPD processEPD result = " + aVarI);
                        if (aVarI.e() && aVarI.d()) {
                            f(aVarI);
                            break;
                        }
                    }
                } else {
                    break;
                }
            } catch (Throwable th) {
                k();
                throw th;
            }
        }
        k();
    }

    public final int d() {
        return this.mDetectionType == DetectionType.EPD ? p009j.i.e() : p009j.i.h();
    }

    public final void e(short[] sArr) {
        l lVar = this.mListener;
        if (lVar != null) {
            lVar.d(sArr);
        }
    }

    public final void f(s.a aVar) {
        l lVar = this.mListener;
        if (lVar != null) {
            lVar.b(aVar);
        }
    }

    public final void g() {
        l lVar = this.mListener;
        if (lVar != null) {
            lVar.c();
        }
    }

    public final void h(H.a aVar) {
        l lVar = this.mListener;
        if (lVar != null) {
            lVar.a(aVar);
        }
    }

    public final s.a i(ShortBuffer shortBuffer) {
        EPD epd = this.mEPD;
        return epd == null ? EPD.EMPTY : epd.processEPD(shortBuffer);
    }

    public final H.a j(ShortBuffer shortBuffer) {
        DNNVAD dnnvad = this.mVAD;
        return dnnvad == null ? DNNVAD.EMPTY : dnnvad.processVAD(shortBuffer);
    }

    public synchronized void k() {
        if (!isCancelled()) {
            cancel(true);
        }
        AudioRecord audioRecord = this.mAudioRecord;
        if (audioRecord != null && audioRecord.getRecordingState() == 3) {
            this.mAudioRecord.stop();
            this.mAudioRecord.release();
        }
    }

    public NormalAudioRecordAction(int i2, l lVar, i iVar, DNNVAD dnnvad) {
        this.mDetectionType = DetectionType.EPD;
        this.mListener = lVar;
        this.mAudioRecord = new AudioRecord(i2, 16000, 16, 2, p009j.i.a());
        this.mAudioStreamSaver = iVar;
        this.mVAD = dnnvad;
        this.mEPD = null;
        this.mDetectionType = DetectionType.VAD;
    }
}
