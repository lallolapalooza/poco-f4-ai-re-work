package p017r;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundmodel.SoundModel;
import java.util.List;
import p007h.c;
import p011l.a;
import p011l.d;
import p013n.C0029i0;
import v.i;

/* JADX INFO: compiled from: SecondStageSoundModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1211a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1212b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f f1213c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f1214d;

    public static h a(ParcelFileDescriptor parcelFileDescriptor, C0029i0 c0029i0) {
        BigSoundModel bigSoundModelD = i.d(parcelFileDescriptor);
        if (bigSoundModelD == null) {
            return null;
        }
        h hVar = new h();
        List<SoundModel> soundModelList = bigSoundModelD.getSoundModelList();
        String strM = a.m(c0029i0.d());
        String strA = a.a(a.g(strM));
        String strSubstring = strA.substring(strA.length() - 8);
        String str = strSubstring + strM.substring(strM.indexOf("_"));
        hVar.f1212b = c0029i0.d();
        for (SoundModel soundModel : soundModelList) {
            if (c.c(soundModel)) {
                f fVar = new f(strSubstring, str, c0029i0.c());
                hVar.f1213c = fVar;
                fVar.f1215a = soundModel;
            } else if (c.d(soundModel)) {
                String strE = c0029i0.e();
                hVar.f1211a = strE;
                g gVar = new g(strE, strSubstring, false);
                hVar.f1214d = gVar;
                gVar.f1215a = soundModel;
            }
        }
        return hVar;
    }

    public static h b(BigSoundModel bigSoundModel, Bundle bundle) {
        if (bigSoundModel == null) {
            return null;
        }
        h hVar = new h();
        List<SoundModel> soundModelList = bigSoundModel.getSoundModelList();
        String string = bundle.getString("SoundModel.fileName");
        String string2 = bundle.getString("SoundModel.locale");
        String strM = a.m(string);
        String strA = a.a(a.g(strM));
        String strSubstring = strA.substring(strA.length() - 8);
        String str = strSubstring + strM.substring(strM.indexOf("_"));
        hVar.f1212b = string;
        for (SoundModel soundModel : soundModelList) {
            if (c.c(soundModel)) {
                f fVar = new f(strSubstring, str, string2);
                hVar.f1213c = fVar;
                fVar.f1215a = soundModel;
                break;
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt(ModelConfigurationKey.f445e.b(), bundle.getInt("SecondStageKW.threshold"));
        bundle2.putInt(ModelConfigurationKey.f448h.b(), bundle.getInt("SecondStageKW.preTolerance"));
        bundle2.putInt(ModelConfigurationKey.f449i.b(), bundle.getInt("SecondStageKW.postTolerance"));
        hVar.f1213c.d(bundle2);
        return hVar;
    }

    public String c() {
        return this.f1212b;
    }

    public f d() {
        return this.f1213c;
    }

    public g e() {
        return this.f1214d;
    }

    public String f() {
        return this.f1211a;
    }

    public boolean g() {
        return this.f1214d != null;
    }

    public void h() {
        f fVar = this.f1213c;
        if (fVar != null) {
            fVar.c();
        }
        g gVar = this.f1214d;
        if (gVar != null) {
            gVar.c();
        }
    }

    public void i(String str) {
        this.f1212b = str;
    }

    public void j(d dVar) {
        f fVar = this.f1213c;
        if (fVar != null) {
            fVar.i(dVar);
        }
        g gVar = this.f1214d;
        if (gVar != null) {
            gVar.k(dVar);
        }
    }

    public void k(f fVar) {
        this.f1213c = fVar;
    }

    public void l(g gVar) {
        this.f1214d = gVar;
        this.f1211a = gVar != null ? gVar.i() : null;
    }
}
