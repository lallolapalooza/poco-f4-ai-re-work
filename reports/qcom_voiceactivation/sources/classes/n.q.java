package p013n;

import G.q;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.qualcomm.qti.qesdk.QesdkUtils;
import com.quicinc.voice.activation.configuration.v4.DeviceConfigurationKey;
import com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey;
import com.quicinc.voice.activation.engineservice.AbstractEnrollmentSession$EnrollmentKey;
import com.quicinc.voice.activation.engineservice.OneVoiceModelFileManager$ModelType;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundmodel.ConfidenceData;
import com.quicinc.voice.activation.soundmodel.SoundModelCodec;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import p003d.g;
import p011l.c;
import p011l.d;
import v.i;

/* JADX INFO: renamed from: n.q, reason: case insensitive filesystem */
/* JADX INFO: compiled from: OneVoicePDKEnrollmentSession.java */
/* JADX INFO: loaded from: classes.dex */
public class C0044q extends AbstractC0024g {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public ByteBuffer f1072A;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C0042p f1073w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f1074x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f1075y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ByteBuffer f1076z;

    public C0044q(String str, d dVar, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) {
        super(str, dVar);
        this.f1073w = g.m();
        H(parcelFileDescriptor);
        G(bundle);
        D();
        q(this.f1074x, this.f1075y);
        F();
    }

    public static int C(int i2) {
        if (i2 < 0 || i2 > 100) {
            return 0;
        }
        return i2;
    }

    public static void r(String str) {
        q.v(str);
    }

    @Override // p013n.AbstractC0024g
    public void B() {
        Bundle bundle = new Bundle();
        bundle.putString("UtteranceInfo.id", this.f1004n.m());
        ShortBuffer shortBufferL = this.f1004n.l();
        q.d("VerifyRecording");
        int i2 = this.f1007q == 0 ? 0 : 1;
        ConfidenceData confidenceData = new ConfidenceData();
        int iVerifyUserRecording = SoundModelCodec.verifyUserRecording(this.f1076z, this.f993c, shortBufferL, i2, confidenceData);
        q.m();
        int iC = C((int) confidenceData.snr);
        int iC2 = C(confidenceData.userMatch);
        bundle.putInt("UtteranceTrainingData.SNR", iC);
        bundle.putInt("UtteranceTrainingData.confidence", iC2);
        boolean z2 = iVerifyUserRecording >= 0 && iC2 >= this.f996f;
        int i3 = this.f1010t + 1;
        this.f1010t = i3;
        if (z2) {
            this.f1006p.a(bundle);
        } else {
            this.f1010t = Math.max(i3 - 1, 0);
            if (this.f1007q == 0 && iVerifyUserRecording == -26) {
                bundle.putString("error.message", "low snr");
            }
            this.f1006p.e(bundle);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[verifyRecording, ");
        sb.append(this.f1004n.m());
        sb.append(", recordType=");
        sb.append(this.f1007q);
        sb.append(", threshold=");
        sb.append(this.f996f);
        sb.append(", score=");
        sb.append(iC2);
        sb.append(", snr=");
        sb.append(iC);
        sb.append(", result=");
        String str = QesdkUtils.TAGS.TAG_SUCCESS;
        sb.append(z2 ? QesdkUtils.TAGS.TAG_SUCCESS : "failure");
        sb.append("]");
        r(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("verifyRecording result=");
        if (!z2) {
            str = "failure";
        }
        sb2.append(str);
        q.w(sb2.toString());
        this.f1009s.m(z2, iVerifyUserRecording, confidenceData, this.f996f, 0, this.f1011u, this.f1010t);
    }

    public final void D() {
        String strG = this.f998h.g(this.f991a);
        String strC = this.f998h.c(strG, this.f995e);
        this.f1074x = strC;
        if (strC == null || strC.isEmpty()) {
            this.f1000j = C0023f0.d("no onevoice 1st stage model is found");
            q.j("no onevoice 1st stage model is found");
            return;
        }
        ByteBuffer byteBufferH = i.h(this.f1073w.g(this.f1074x, OneVoiceModelFileManager$ModelType.FStage));
        this.f1076z = byteBufferH;
        BigSoundModel bigSoundModelF = i.f(byteBufferH);
        this.f993c = bigSoundModelF == null ? null : bigSoundModelF.getKeyword();
        q.v("getOneVoiceModel, keyword = " + this.f993c);
        String strL = this.f998h.l(strG);
        this.f1075y = strL;
        if (strL == null || strL.isEmpty()) {
            this.f1000j = C0023f0.d("no one voice user base model is found");
            q.j("no one voice user base model is found");
            return;
        }
        this.f1072A = i.h(this.f1073w.g(this.f1075y, OneVoiceModelFileManager$ModelType.SStageUV));
        this.f996f = this.f998h.i(new c(this.f991a, strG, null, this.f1075y), ModelConfigurationKey.f447g);
        q.v("training threshold = " + this.f996f);
    }

    public final int E(c cVar) {
        return this.f1007q == 0 ? this.f998h.i(cVar, DeviceConfigurationKey.f399b) : this.f998h.i(cVar, DeviceConfigurationKey.f400c);
    }

    public final void F() {
        this.f1004n.q(this.f1076z, l(false));
    }

    public final void G(Bundle bundle) {
        AbstractEnrollmentSession$EnrollmentKey abstractEnrollmentSession$EnrollmentKey = AbstractEnrollmentSession$EnrollmentKey.PDK_SOUND_MODEL_FILE_NAME;
        String string = bundle.getString(abstractEnrollmentSession$EnrollmentKey.b());
        r("file full Name : " + string);
        if (string == null || string.isEmpty()) {
            String str = bundle + "[" + abstractEnrollmentSession$EnrollmentKey.b() + "] is null or empty:";
            this.f1000j = C0023f0.d(str);
            q.j(str);
            return;
        }
        if (!C0029i0.g(string)) {
            String str2 = "[" + string + "] format invalid:";
            this.f1000j = C0023f0.d(str2);
            q.j(str2);
            return;
        }
        this.f992b = new v.g(string, this.f991a);
        q.v("parseBundle keyword = " + this.f993c);
        AbstractEnrollmentSession$EnrollmentKey abstractEnrollmentSession$EnrollmentKey2 = AbstractEnrollmentSession$EnrollmentKey.USER_ID;
        String string2 = bundle.getString(abstractEnrollmentSession$EnrollmentKey2.b());
        if (string2 == null || string2.isEmpty()) {
            String str3 = bundle + "[" + abstractEnrollmentSession$EnrollmentKey2.b() + "] is null or empty:";
            this.f1000j = C0023f0.d(str3);
            q.j(str3);
            return;
        }
        this.f994d = string2;
        AbstractEnrollmentSession$EnrollmentKey abstractEnrollmentSession$EnrollmentKey3 = AbstractEnrollmentSession$EnrollmentKey.LOCALE;
        String string3 = bundle.getString(abstractEnrollmentSession$EnrollmentKey3.b());
        if (string3 == null || string3.isEmpty()) {
            String str4 = bundle + "[" + abstractEnrollmentSession$EnrollmentKey3.b() + "] is null or empty:";
            this.f1000j = C0023f0.d(str4);
            q.j(str4);
            return;
        }
        this.f995e = string3;
        this.f1007q = bundle.getInt(AbstractEnrollmentSession$EnrollmentKey.RECORDING_TYPE.b(), 0);
        c cVar = new c(this.f991a, this.f998h.g(this.f991a), null, "common");
        this.f997g = E(cVar);
        int i2 = this.f998h.i(cVar, DeviceConfigurationKey.f403f);
        w(i2);
        q.v("configurationInfo = " + cVar.toString());
        q.v("train number = " + this.f997g + " timeout = " + i2);
        this.f1009s.q(this.f994d, string, j(), this.f995e, null);
    }

    public final void H(ParcelFileDescriptor parcelFileDescriptor) {
        ByteBuffer byteBufferG = i.g(parcelFileDescriptor);
        if (byteBufferG != null) {
            v(byteBufferG);
        } else {
            this.f1000j = C0023f0.d("Read from ParcelFileDescriptor error");
            q.j("Read from ParcelFileDescriptor error");
        }
    }

    @Override // p013n.AbstractC0024g
    public int h() {
        this.f1003m = i.a(this.f999i, this.f1076z, this.f1072A, this.f993c, this.f994d, this.f1007q == 0 ? this.f1004n.o().size() : this.f1004n.o().size() + this.f1008r.size(), g());
        StringBuilder sb = new StringBuilder();
        sb.append("[generateSoundModel, keyword=");
        sb.append(this.f993c);
        sb.append(", userId=");
        sb.append(this.f994d);
        sb.append(", match=");
        sb.append(this.f1003m.c());
        sb.append(", result=");
        int iB = this.f1003m.b();
        String str = QesdkUtils.TAGS.TAG_SUCCESS;
        sb.append(iB >= 0 ? QesdkUtils.TAGS.TAG_SUCCESS : "failure");
        sb.append("]");
        r(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("generateSoundModel result=");
        if (this.f1003m.b() < 0) {
            str = "failure";
        }
        sb2.append(str);
        q.w(sb2.toString());
        this.f1009s.p(this.f1003m.b(), this.f1003m.c());
        return this.f1003m.c();
    }

    @Override // p013n.AbstractC0024g
    public void s() {
        q.j("onRecordingError");
    }
}
