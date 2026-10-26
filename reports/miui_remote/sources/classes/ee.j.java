package ee;

import android.util.Log;
import com.hzy.tvmao.KKACManagerV2;
import com.hzy.tvmao.ir.ac.ACStateV2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class j {
    public static final int A = -1;
    public static final int B = -100;
    public static final int C = 1000000;
    public static final int D = -1;
    public static final int E = 0;
    public static final int F = 1;
    public static final int G = -1;
    public static final int H = 0;
    public static final int I = 1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int f23525p = 99;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int f23526q = 100;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int f23527r = 101;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int f23528s = 102;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int f23529t = 103;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final int f23530u = 104;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final int f23531v = 105;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final int f23532w = 106;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final int f23533x = 107;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int f23534y = 108;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final int f23535z = 109;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f23537b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f23538c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f23539d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f23541f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g f23544i;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public WeakReference<KKACManagerV2> f23549n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public WeakReference<re.k> f23550o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f23536a = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f23540e = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f23542g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f23543h = "";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public af.a f23545j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public af.h f23546k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public JSONObject f23547l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f23548m = false;

    public static class a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f23553c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f23554d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f23555e;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f23558h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public String f23559i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public String f23562l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public String f23563m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public String f23564n;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public String f23566p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public String f23567q;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public double f23551a = -10000.0d;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public double f23552b = -10000.0d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f23556f = -1;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f23557g = -1;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f23560j = -1;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f23561k = -1;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f23565o = -1;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f23568r = false;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f23569s = 0;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f23570t = -1;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public long f23571u = 0;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public int f23572v = 0;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f23573w = 0;

        public static a a(JSONObject jSONObject) {
            a aVar = new a();
            if (jSONObject.has("longitude")) {
                aVar.f23551a = jSONObject.optDouble("longitude");
            }
            if (jSONObject.has("latitude")) {
                aVar.f23552b = jSONObject.optDouble("latitude");
            }
            aVar.f23553c = jSONObject.optString("bssid");
            aVar.f23554d = jSONObject.optString("ssid");
            aVar.f23555e = jSONObject.optString("ircode_id");
            aVar.f23558h = jSONObject.optString("name");
            aVar.f23559i = jSONObject.optString("extra");
            aVar.f23562l = jSONObject.optString("brand_name");
            if (jSONObject.has("user_key")) {
                aVar.f23571u = jSONObject.optLong("user_key");
            }
            if (jSONObject.has(e.f23443l7)) {
                aVar.f23564n = jSONObject.optString(e.f23443l7);
            }
            if (jSONObject.has("level")) {
                aVar.f23570t = jSONObject.optInt("level");
            }
            if (jSONObject.has(e.f23439h7)) {
                aVar.f23565o = jSONObject.optInt(e.f23439h7);
            }
            if (jSONObject.has("lineup")) {
                aVar.f23566p = String.valueOf(jSONObject.optInt("lineup"));
            }
            if (jSONObject.has(e.f23454w7)) {
                aVar.f23566p = jSONObject.optString(e.f23454w7);
            }
            if (jSONObject.has(e.f23455x7)) {
                aVar.f23567q = jSONObject.optString(e.f23455x7);
            }
            if (jSONObject.has(af.b.f1584w)) {
                aVar.f23557g = jSONObject.optInt(af.b.f1584w);
            }
            if (jSONObject.has("device_type_id")) {
                aVar.f23556f = jSONObject.optInt("device_type_id");
            }
            if (jSONObject.has("source")) {
                aVar.f23563m = jSONObject.optString("source");
            }
            if (jSONObject.has("use")) {
                aVar.f23569s = jSONObject.optInt("use");
            }
            if (jSONObject.has("good")) {
                aVar.f23572v = jSONObject.optInt("good");
            }
            if (jSONObject.has("bad")) {
                aVar.f23573w = jSONObject.optInt("bad");
            }
            return aVar;
        }

        public JSONObject b() {
            JSONObject jSONObject = new JSONObject();
            try {
                double d10 = this.f23551a;
                if (d10 != -10000.0d && this.f23552b != -10000.0d) {
                    jSONObject.putOpt("longitude", Double.valueOf(d10));
                    jSONObject.putOpt("latitude", Double.valueOf(this.f23552b));
                }
                jSONObject.putOpt("bssid", this.f23553c);
                jSONObject.putOpt("ssid", this.f23554d);
                jSONObject.putOpt("ircode_id", this.f23555e);
                jSONObject.putOpt("user_key", Long.valueOf(this.f23571u));
                jSONObject.putOpt("level", Integer.valueOf(this.f23570t));
                jSONObject.putOpt(e.f23443l7, this.f23564n);
                jSONObject.putOpt("device_type_id", Integer.valueOf(this.f23556f));
                jSONObject.putOpt(af.b.f1584w, Integer.valueOf(this.f23557g));
                jSONObject.putOpt("name", this.f23558h);
                jSONObject.putOpt("extra", this.f23559i);
                jSONObject.putOpt("brand_id", Integer.valueOf(this.f23560j));
                jSONObject.putOpt("tp_brand_id", Integer.valueOf(this.f23561k));
                jSONObject.putOpt("brand_name", this.f23562l);
                jSONObject.putOpt(e.f23439h7, Integer.valueOf(this.f23565o));
                jSONObject.putOpt(e.f23454w7, this.f23566p);
                jSONObject.putOpt(e.f23455x7, this.f23567q);
                jSONObject.putOpt("source", vd.d.e());
                jSONObject.putOpt("use", Integer.valueOf(this.f23569s));
                jSONObject.putOpt("good", Integer.valueOf(this.f23572v));
                jSONObject.putOpt("bad", Integer.valueOf(this.f23573w));
            } catch (Exception e10) {
                e10.printStackTrace();
            }
            return jSONObject;
        }
    }

    public j(String str, int i10, c cVar) {
        this.f23537b = str;
        this.f23538c = i10;
        this.f23539d = cVar;
    }

    public static j a(a aVar) {
        if (aVar == null) {
            return null;
        }
        return new j(aVar.f23558h, 104, e.d(aVar));
    }

    public static j f(JSONObject jSONObject) {
        j jVar = null;
        if (jSONObject == null) {
            return null;
        }
        try {
            e eVarA = e.H7.a(jSONObject.getJSONObject("device_info"));
            if (eVarA == null) {
                return null;
            }
            j jVar2 = new j(jSONObject.getString("name"), jSONObject.getInt("type"), eVarA);
            try {
                if (jSONObject.has("id")) {
                    jVar2.f23536a = jSONObject.getInt("id");
                }
                return jVar2;
            } catch (Exception e10) {
                e = e10;
                jVar = jVar2;
            }
        } catch (Exception e11) {
            e = e11;
        }
        e.printStackTrace();
        return jVar;
    }

    public boolean A() {
        c cVar;
        return (x() || (cVar = this.f23539d) == null || !(cVar instanceof e) || ((e) cVar).w() == -1) ? false : true;
    }

    public void B() {
        WeakReference<KKACManagerV2> weakReference = this.f23549n;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.f23549n.get().onPause();
    }

    public void C() {
        c cVar = this.f23539d;
        if ((cVar instanceof e) && cVar.b() == 3) {
            int iD = ((e) this.f23539d).D();
            if ((iD == 0 || iD == 1004 || iD == 1005) && vd.d.K()) {
                h().onResume();
            }
        }
    }

    public void D() {
        this.f23549n = null;
        this.f23550o = null;
        this.f23546k = null;
        this.f23545j = null;
        c cVar = this.f23539d;
        if (cVar instanceof e) {
            ((e) cVar).Q();
        }
    }

    public void E(String str) {
        Log.e("miir", "send key: " + str);
        c cVar = this.f23539d;
        if (cVar instanceof e) {
            e eVar = (e) cVar;
            if (e() != 3) {
                vd.d.g().k(eVar.m(str));
                return;
            }
            int iD = eVar.D();
            if (iD != 0) {
                if (iD == 1001) {
                    u();
                    G(str);
                    return;
                } else if (iD != 1004 && iD != 1005) {
                    return;
                }
            }
            s();
            F(str);
        }
    }

    public final void F(String str) {
        KKACManagerV2 kKACManagerV2;
        int curTemp;
        String aCStateV2InString;
        if (this.f23549n.get() == null) {
            return;
        }
        if (str != "power") {
            if (this.f23549n.get().getPowerState() == 1) {
                this.f23549n.get().changePowerState();
            }
            str.getClass();
            switch (str) {
                case "MODE":
                    this.f23549n.get().changeACModel();
                    break;
                case "shake_wind":
                    this.f23549n.get().changeUDWindDirect(ACStateV2.UDWindDirectKey.UDDIRECT_KEY_SWING);
                    break;
                case "tempIncSpecial":
                    kKACManagerV2 = this.f23549n.get();
                    curTemp = this.f23549n.get().getCurTemp() + 1;
                    kKACManagerV2.setTargetTemp(curTemp);
                    break;
                case "wind_speed":
                    this.f23549n.get().changeWindSpeed();
                    break;
                case "tempDecSpecial":
                    kKACManagerV2 = this.f23549n.get();
                    curTemp = this.f23549n.get().getCurTemp() - 1;
                    kKACManagerV2.setTargetTemp(curTemp);
                    break;
            }
            return;
        }
        this.f23549n.get().changePowerState();
        vd.d.g().j(this.f23546k.e(), this.f23549n.get().getACIRPatternIntArray(), true, true);
        ee.a aVar = (ee.a) j();
        try {
            aCStateV2InString = this.f23549n.get().getACStateV2InString();
        } catch (Throwable th2) {
            th2.printStackTrace();
            aCStateV2InString = "";
        }
        aVar.p(aCStateV2InString);
        aVar.n(this.f23549n.get().getPowerState() == 1);
        aVar.q(this.f23549n.get().getCurModelType());
        aVar.r(this.f23549n.get().getCurTemp());
    }

    public final void G(String str) {
        ee.a aVar = (ee.a) j();
        str.getClass();
        switch (str) {
            case "MODE":
                aVar.q(aVar.k() == 0 ? 1 : 0);
                break;
            case "power":
                if (!aVar.m()) {
                    vd.d.g().k(this.f23545j.b().g());
                    aVar.n(true);
                    return;
                }
                break;
            case "tempIncSpecial":
                aVar.r(aVar.l() + 1 <= 30 ? aVar.l() + 1 : 30);
                break;
            case "tempDecSpecial":
                aVar.r(aVar.l() - 1 < 16 ? 16 : aVar.l() - 1);
                break;
            default:
                return;
        }
        aVar.n(false);
        bf.a aVarB = this.f23545j.b();
        df.e eVar = null;
        if (aVar.k() == 0) {
            eVar = aVarB.c()[0][aVar.l() - 16];
        } else if (aVar.k() == 1) {
            eVar = aVarB.d()[0][aVar.l() - 16];
        }
        vd.d.g().k(eVar);
    }

    public void H(boolean z10) {
        this.f23542g = z10;
    }

    public void I(boolean z10) {
        this.f23540e = z10;
    }

    public void J(c cVar) {
        this.f23539d = cVar;
    }

    public void K(boolean z10) {
        c cVar = this.f23539d;
        if (cVar == null || !(cVar instanceof e)) {
            return;
        }
        ((e) cVar).X(z10);
    }

    public void L(int i10) {
        this.f23536a = i10;
    }

    public void M(g gVar) {
        this.f23544i = gVar;
    }

    public void N(long j10) {
        j().g(j10);
    }

    public void O(double d10, double d11) {
        c cVar = this.f23539d;
        if (cVar == null || !(cVar instanceof e)) {
            return;
        }
        ((e) cVar).h0(d10, d11);
    }

    public void P(String str) {
        this.f23537b = str;
    }

    public void Q(String str) {
        this.f23543h = str;
    }

    public void R(long j10) {
        this.f23541f = j10;
    }

    public void S(int i10) {
        this.f23538c = i10;
    }

    public bf.b b() {
        c cVar = this.f23539d;
        if (cVar instanceof e) {
            return ((e) cVar).g();
        }
        return null;
    }

    public JSONObject c() {
        if (!(this.f23539d instanceof e)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.f23537b);
            jSONObject.put("type", this.f23538c);
            jSONObject.put("id", this.f23536a);
            jSONObject.put("device_info", ((e) this.f23539d).h());
            return jSONObject;
        } catch (JSONException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    public c d() {
        return this.f23539d;
    }

    public int e() {
        c cVar = this.f23539d;
        if (cVar == null) {
            return 0;
        }
        return cVar.b();
    }

    public boolean equals(Object obj) {
        if (obj instanceof j) {
            toString();
            obj.toString();
            c cVarD = ((j) obj).d();
            if ((cVarD instanceof h) && (d() instanceof h)) {
                h hVar = (h) cVarD;
                return ((h) d()).c().f17717i.getAddress().equalsIgnoreCase(hVar.c().f17717i.getAddress()) && ((h) d()).c().f17714f == hVar.c().f17714f;
            }
        }
        return super.equals(obj);
    }

    public int g() {
        return this.f23536a;
    }

    public KKACManagerV2 h() {
        s();
        return this.f23549n.get();
    }

    public af.h i() {
        return this.f23546k;
    }

    public g j() {
        if (this.f23544i == null) {
            c cVar = this.f23539d;
            this.f23544i = ((cVar instanceof e) && cVar.b() == 3) ? new ee.a() : new g();
            this.f23544i.f23491a = 3;
        }
        return this.f23544i;
    }

    public long k() {
        g gVar = this.f23544i;
        if (gVar != null) {
            return gVar.b();
        }
        return 0L;
    }

    public String l() {
        return this.f23537b;
    }

    public String m() {
        return this.f23543h;
    }

    public a n() {
        c cVarD = d();
        if (cVarD == null || !(cVarD instanceof e)) {
            return null;
        }
        a aVarV = ((e) cVarD).v();
        if (aVarV != null) {
            aVarV.f23558h = l();
            aVarV.f23569s = 0;
        }
        return aVarV;
    }

    public long o() {
        return this.f23541f;
    }

    public int p() {
        return this.f23538c;
    }

    public re.k q() {
        t();
        return this.f23550o.get();
    }

    public boolean r(String str) {
        c cVar = this.f23539d;
        if (cVar instanceof e) {
            return ((e) cVar).J(str);
        }
        return false;
    }

    public final void s() {
        if (this.f23546k == null) {
            e eVar = (e) this.f23539d;
            this.f23546k = af.h.f1624l.a(eVar.n());
            String[] strArrSplit = eVar.s().split("_");
            if (strArrSplit != null && strArrSplit.length > 0) {
                this.f23546k.j(Integer.valueOf(strArrSplit[strArrSplit.length - 1]).intValue());
            }
        }
        WeakReference<KKACManagerV2> weakReference = this.f23549n;
        if (weakReference == null || weakReference.get() == null) {
            KKACManagerV2 kKACManagerV2 = new KKACManagerV2();
            this.f23549n = new WeakReference<>(kKACManagerV2);
            kKACManagerV2.initIRData(this.f23546k.f(), this.f23546k.d(), (ArrayList) this.f23546k.c());
            kKACManagerV2.setACStateV2FromString(((ee.a) j()).j());
            kKACManagerV2.onResume();
        }
    }

    public final void t() {
        this.f23547l = ((e) this.f23539d).n();
        WeakReference<re.k> weakReference = this.f23550o;
        if (weakReference == null || weakReference.get() == null) {
            re.k kVar = new re.k(this.f23547l);
            this.f23550o = new WeakReference<>(kVar);
            try {
                kVar.D(((ee.a) j()).j());
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
    }

    public final void u() {
        if (this.f23545j == null) {
            this.f23545j = af.a.f1582b.a(((e) this.f23539d).n());
        }
    }

    public boolean v() {
        return this.f23542g;
    }

    public boolean w() {
        return this.f23540e;
    }

    public boolean x() {
        c cVar = this.f23539d;
        if (cVar == null || !(cVar instanceof e)) {
            return false;
        }
        return ((e) cVar).L();
    }

    public boolean y(j jVar) {
        return z() && jVar.z() && ((e) this.f23539d).N((e) jVar.d());
    }

    public boolean z() {
        c cVar;
        return this.f23538c == 101 && (cVar = this.f23539d) != null && (cVar instanceof e);
    }
}
