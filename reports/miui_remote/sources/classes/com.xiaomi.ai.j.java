package com.xiaomi.ai;

import android.media.AudioTrack;

/* JADX INFO: loaded from: classes2.dex */
class j implements com.xiaomi.ai.streamplayer.k.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c f17182a;

    public j(c cVar) {
        this.f17182a = cVar;
    }

    @Override // com.xiaomi.ai.streamplayer.k.a
    public void a(com.xiaomi.ai.streamplayer.k kVar) {
        synchronized (this.f17182a.f17120o) {
            if (kVar == this.f17182a.f17114i && this.f17182a.f17114i != null) {
                this.f17182a.notifyTtsPlayFinish();
            }
        }
    }

    @Override // com.xiaomi.ai.streamplayer.k.a
    public void a(com.xiaomi.ai.streamplayer.k kVar, int i10, String str) {
        synchronized (this.f17182a.f17120o) {
            if (kVar == this.f17182a.f17114i && this.f17182a.f17114i != null) {
                SpeechError speechError = new SpeechError(SpeechEngine.ProcessStage.STAGE_TTS);
                speechError.errMsg = str;
                speechError.errCode = i10;
                speechError.errType = 2;
                this.f17182a.notifyTtsError(speechError);
            }
        }
    }

    @Override // com.xiaomi.ai.streamplayer.k.a
    public void a(com.xiaomi.ai.streamplayer.k kVar, AudioTrack audioTrack) {
        synchronized (this.f17182a.f17120o) {
            if (kVar == this.f17182a.f17114i && this.f17182a.f17114i != null) {
                this.f17182a.notifyTtsPlayStart(audioTrack);
            }
        }
    }

    @Override // com.xiaomi.ai.streamplayer.k.a
    public void a(com.xiaomi.ai.streamplayer.k kVar, PCMInfo pCMInfo) {
        synchronized (this.f17182a.f17120o) {
            if (kVar == this.f17182a.f17114i && this.f17182a.f17114i != null) {
                this.f17182a.notifyTtsPCMData(pCMInfo);
            }
        }
    }
}
