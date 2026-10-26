package p013n;

import G.i;
import G.q;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.qualcomm.qti.qesdk.QesdkUtils;
import com.quicinc.voice.activation.audiorecord.c;
import com.quicinc.voice.activation.configuration.v4.DeviceConfigurationKey;
import com.quicinc.voice.activation.configuration.v4.EPDConfigurationKey;
import com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey;
import com.quicinc.voice.activation.xmlhelper.Type;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p011l.d;
import p011l.l;
import v.g;
import v.h;

/* JADX INFO: renamed from: n.g, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AbstractEnrollmentSession.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0024g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f995e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f996f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f997g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public d f998h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ByteBuffer f999i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Bundle f1000j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ParcelFileDescriptor[] f1002l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public h f1003m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HandlerC0022f f1005o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC0020e f1006p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1007q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public i f1009s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1010t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1011u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f1012v;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f1001k = new ArrayList();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final HashMap f1008r = new HashMap();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f1004n = new c(new C0018d(this));

    public AbstractC0024g(String str, d dVar) {
        this.f991a = str;
        this.f998h = dVar;
        if (dVar.p(str)) {
            this.f1009s = G.g.b(str);
        } else {
            this.f1009s = G.g.a(str);
        }
        this.f1009s.r();
    }

    public static void r(String str) {
        q.v(str);
    }

    public void A() {
        ByteBuffer byteBufferA = this.f1003m.a();
        try {
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(this.f1002l[1]);
            try {
                autoCloseOutputStream.write(byteBufferA.array());
                autoCloseOutputStream.close();
            } catch (Throwable th) {
                try {
                    autoCloseOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e2) {
            q.j("getExtendedSoundModelFileDescriptor - IOException=" + e2.toString());
            e();
        }
    }

    public abstract void B();

    public void c(String str, short[] sArr) {
        this.f1008r.put(str, sArr);
    }

    public void d() {
        r("cancelUtteranceTraining");
        this.f1005o.b();
        this.f1004n.K();
        this.f1004n.L();
        this.f1009s.t();
    }

    public final void e() {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.f1002l;
        if (parcelFileDescriptorArr == null) {
            return;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorArr[0];
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
            ParcelFileDescriptor parcelFileDescriptor2 = this.f1002l[1];
            if (parcelFileDescriptor2 != null) {
                parcelFileDescriptor2.close();
            }
        } catch (IOException unused) {
        }
        this.f1002l = null;
    }

    public final ShortBuffer[] f() {
        ShortBuffer[] shortBufferArr = new ShortBuffer[this.f1008r.size()];
        int i2 = 0;
        for (short[] sArr : this.f1008r.values()) {
            ShortBuffer shortBufferAllocate = ShortBuffer.allocate(sArr.length);
            shortBufferAllocate.put(sArr);
            shortBufferArr[i2] = shortBufferAllocate;
            i2++;
        }
        return shortBufferArr;
    }

    public ShortBuffer[] g() {
        if (this.f1007q == 0) {
            return this.f1004n.p();
        }
        ShortBuffer[] shortBufferArrF = f();
        ShortBuffer[] shortBufferArrP = this.f1004n.p();
        ShortBuffer[] shortBufferArr = new ShortBuffer[shortBufferArrF.length + shortBufferArrP.length];
        System.arraycopy(shortBufferArrF, 0, shortBufferArr, 0, shortBufferArrF.length);
        System.arraycopy(shortBufferArrP, 0, shortBufferArr, shortBufferArrF.length, shortBufferArrP.length);
        return shortBufferArr;
    }

    public abstract int h();

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int i(String str) {
        q.v("getAudioSource() source=" + str);
        if (TextUtils.isEmpty(str)) {
            return 6;
        }
        String upperCase = str.toUpperCase();
        upperCase.hashCode();
        byte b2 = -1;
        switch (upperCase.hashCode()) {
            case -2032180703:
                if (upperCase.equals("DEFAULT")) {
                    b2 = 0;
                }
                break;
            case -1909881542:
                if (upperCase.equals("CAMCORDER")) {
                    b2 = 1;
                }
                break;
            case -1000727189:
                if (upperCase.equals("VOICE_CALL")) {
                    b2 = 2;
                }
                break;
            case 76327:
                if (upperCase.equals("MIC")) {
                    b2 = 3;
                }
                break;
            case 90505257:
                if (upperCase.equals("VOICE_DOWNLINK")) {
                    b2 = 4;
                }
                break;
            case 813780970:
                if (upperCase.equals("VOICE_RECOGNITION")) {
                    b2 = 5;
                }
                break;
            case 903022818:
                if (upperCase.equals("VOICE_UPLINK")) {
                    b2 = 6;
                }
                break;
            case 1126640821:
                if (upperCase.equals("UNPROCESSED")) {
                    b2 = 7;
                }
                break;
            case 1331256137:
                if (upperCase.equals("VOICE_COMMUNICATION")) {
                    b2 = 8;
                }
                break;
            case 2119305781:
                if (upperCase.equals("REMOTE_SUBMIX")) {
                    b2 = 9;
                }
                break;
        }
        switch (b2) {
            case 0:
                return 0;
            case 1:
                return 5;
            case 2:
                return 4;
            case 3:
                return 1;
            case QesdkUtils.MISC.LENGTH_OF_UINT /* 4 */:
                return 3;
            case QesdkUtils.MISC.MAX_CORE_POOL_SIZE /* 5 */:
            default:
                return 6;
            case 6:
                return 2;
            case 7:
                return 9;
            case 8:
                return 7;
            case 9:
                return 8;
        }
    }

    public String j() {
        return this.f991a;
    }

    public Bundle k() {
        return l(true);
    }

    public Bundle l(boolean z2) {
        Bundle bundle = new Bundle();
        p011l.c cVar = new p011l.c(this.f991a, z2 ? this.f998h.j(this.f991a) : this.f998h.g(this.f991a), null, "sml_onlineEPD");
        for (EPDConfigurationKey ePDConfigurationKey : EPDConfigurationKey.values()) {
            Type typeA = ePDConfigurationKey.a();
            if (typeA.e()) {
                bundle.putFloat(ePDConfigurationKey.b(), this.f998h.b(cVar, ePDConfigurationKey));
            } else if (typeA.f()) {
                bundle.putInt(ePDConfigurationKey.b(), this.f998h.i(cVar, ePDConfigurationKey));
            } else if (typeA.d()) {
                bundle.putBoolean(ePDConfigurationKey.b(), this.f998h.a(cVar, ePDConfigurationKey));
            } else if (typeA.g()) {
                bundle.putString(ePDConfigurationKey.b(), this.f998h.m(cVar, ePDConfigurationKey));
            }
        }
        bundle.putInt(ModelConfigurationKey.f447g.b(), this.f996f);
        q.v("EPD parseEPDConfiguration params = " + bundle);
        return bundle;
    }

    public ParcelFileDescriptor m() {
        h hVar = this.f1003m;
        if (hVar != null && hVar.b() >= 0) {
            try {
                ParcelFileDescriptor[] parcelFileDescriptorArrCreateReliablePipe = ParcelFileDescriptor.createReliablePipe();
                this.f1002l = parcelFileDescriptorArrCreateReliablePipe;
                return parcelFileDescriptorArrCreateReliablePipe[0];
            } catch (IOException e2) {
                q.j("getExtendedSoundModelFileDescriptor - IOException=" + e2.toString());
            }
        }
        return null;
    }

    public Bundle n() {
        return this.f1000j;
    }

    public List o() {
        r("getUtterancesInfo");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f997g; i2++) {
            String str = this.f993c + "_" + i2;
            Bundle bundle = new Bundle();
            bundle.putString("UtteranceInfo.id", str);
            bundle.putString("UtteranceInfo.text", this.f993c);
            arrayList.add(bundle);
            this.f1001k.add(str);
        }
        return arrayList;
    }

    public void p() {
        if (this.f992b != null) {
            l.a().b(this.f991a, this.f992b.b(), this.f995e);
        }
    }

    public void q(String str, String str2) {
        l.a().d(this.f991a, str, str2, this.f995e);
    }

    public abstract void s();

    public void t() {
        this.f1008r.clear();
        this.f999i = null;
        this.f1003m = null;
        this.f1005o.b();
        this.f1004n.F();
        e();
        this.f1009s.t();
    }

    public final void u() {
        String strM = p003d.g.f().m(new p011l.c(this.f991a, p003d.g.f().g(j()), null, "common"), DeviceConfigurationKey.f402e);
        this.f1012v = strM;
        this.f1004n.G(i(strM));
    }

    public void v(ByteBuffer byteBuffer) {
        this.f999i = byteBuffer;
        x();
        u();
    }

    public void w(int i2) {
        this.f1005o = new HandlerC0022f(i2, new RunnableC0012a(this));
    }

    public final void x() {
        int i2 = p003d.g.f().i(new p011l.c(this.f991a, p003d.g.f().g(j()), null, "common"), DeviceConfigurationKey.f408k);
        this.f1011u = i2;
        this.f1004n.I(i2);
    }

    public void y(String str, InterfaceC0020e interfaceC0020e) {
        r("startUtteranceTraining");
        this.f1006p = interfaceC0020e;
        this.f1005o.a();
        this.f1004n.r(str, this.f1009s);
        this.f1004n.J();
        this.f1009s.j(this.f1010t, i(this.f1012v) + "(" + this.f1012v + ")");
    }

    public void z() {
        r("stopUtteranceTraining");
        this.f1005o.b();
        this.f1004n.K();
        B();
        this.f1004n.L();
    }
}
