package p013n;

import G.q;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.quicinc.voice.activation.engineservice.ReenableWakewordService;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import p003d.g;
import p010k.b;

/* JADX INFO: compiled from: ReenableWakewordService.java */
/* JADX INFO: loaded from: classes.dex */
public class Y extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f967a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f968b;

    public Y(Looper looper) {
        super(looper);
        this.f967a = false;
        this.f968b = false;
    }

    public final void a() {
        if (this.f967a && this.f968b) {
            boolean z2 = false;
            for (b bVar : g.e().f().values()) {
                for (BigSoundModel bigSoundModel : g.e().d(bVar.j()).values()) {
                    if (g.v().d(bigSoundModel.getSoundModelKey())) {
                        q.r("has active sound model: " + bigSoundModel);
                        z2 = true;
                        break;
                    }
                }
                if (z2) {
                    break;
                }
                if (g.o().h(bVar)) {
                    q.r("has active OneVoice model for  " + bVar.j());
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                return;
            }
            q.r("stop self since no active model");
            g.d().stopService(ReenableWakewordService.c());
            Process.killProcess(Process.myPid());
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 10001) {
            q.r("non OneVoice model reenable completed");
            this.f968b = true;
            a();
        } else if (i2 == 10003) {
            q.r("OneVoice model reenable completed");
            this.f967a = true;
            a();
        }
    }
}
