package p003d;

import G.q;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.quicinc.voice.activation.asr.IResultListener;
import com.quicinc.voice.activation.engineservice.ForegroundService;

/* JADX INFO: compiled from: Facade.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements IResultListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public StringBuilder f712b;

    public final void a() {
        q.a("release");
        this.f711a = "";
        this.f712b = null;
        g.c().y();
        c();
    }

    public final void b(String str, boolean z2) {
        q.a("sendTranscription inputReceiverCallback = " + g.h());
        if (g.h() != null) {
            Bundle bundle = new Bundle();
            bundle.putString("LLMClient.textInput", str);
            bundle.putBoolean("LLMClient.finish", z2);
            q.a("sendTranscription params = " + bundle);
            try {
                g.h().O(bundle);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void c() {
        q.a("stopRecording");
        g.n().p();
        g.r().m();
        ForegroundService.j();
    }

    @Override // com.quicinc.voice.activation.asr.IResultListener
    public void onError(int i2, Exception exc) {
        q.b("onError errorCode=" + i2 + "  " + exc.getLocalizedMessage());
        if (i2 == -3 || i2 == -2) {
            b("", true);
            a();
        }
    }

    @Override // com.quicinc.voice.activation.asr.IResultListener
    public void onRecordingStopped() {
        q.a("onRecordingStopped");
        StringBuilder sb = this.f712b;
        b(sb != null ? sb.toString() : "", true);
        a();
    }

    @Override // com.quicinc.voice.activation.asr.IResultListener
    public void onSpeechEnd() {
        q.a("onSpeechEnd");
    }

    @Override // com.quicinc.voice.activation.asr.IResultListener
    public void onSpeechStart() {
        q.a("onSpeechStart");
    }

    @Override // com.quicinc.voice.activation.asr.IResultListener
    public void onTranscription(String str, String str2, boolean z2, int i2) {
        q.a("onTranscription transcription= " + str + ",language= " + str2 + ", last= " + z2 + ", code= " + i2);
        if (z2) {
            if (this.f712b == null) {
                this.f712b = new StringBuilder();
            }
            this.f711a = "";
            this.f712b.append(str);
            q.a("onTranscription lastTranscription= " + this.f712b.toString());
            b(this.f712b.toString(), false);
            return;
        }
        if (TextUtils.equals(this.f711a, str)) {
            return;
        }
        this.f711a = str;
        StringBuilder sb = this.f712b;
        b((sb != null ? sb.toString() : "") + str, false);
    }

    public b() {
        this.f711a = "";
        this.f712b = null;
    }
}
