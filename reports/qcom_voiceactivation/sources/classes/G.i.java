package G;

import android.content.ComponentName;
import android.os.Bundle;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundmodel.ConfidenceData;
import com.quicinc.voice.activation.soundtrigger.RecognitionEvent;

/* JADX INFO: compiled from: IAudioStreamSaver.java */
/* JADX INFO: loaded from: classes.dex */
public interface i {
    void a(String str, Bundle bundle, p007h.b bVar);

    void b(byte[] bArr, int i2, int i3);

    void c(Bundle bundle, p007h.b bVar);

    void d();

    void e(String str);

    void f();

    void g(String str);

    void h(Bundle bundle, p007h.b bVar);

    void i(RecognitionEvent recognitionEvent, BigSoundModel bigSoundModel);

    void j(int i2, String str);

    void k(String str);

    void l();

    void m(boolean z2, int i2, ConfidenceData confidenceData, int i3, int i4, int i5, int i6);

    void n(Bundle bundle);

    void o();

    void p(int i2, int i3);

    void q(String str, String str2, String str3, String str4, String str5);

    void r();

    void s(ComponentName componentName, boolean z2);

    void t();

    void u(p016q.a aVar);

    void v(byte[] bArr, int i2, int i3);

    void w(RecognitionEvent recognitionEvent);
}
