package cg;

import com.xiaomi.mitv.phone.remotecontroller.ir.dk.model.ControlKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b implements l.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f12154a = "FanNLPProcessor";

    /* JADX INFO: renamed from: cg.b$b, reason: collision with other inner class name */
    public class C0143b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f12155a;

        public C0143b() {
        }
    }

    @Override // cg.l.e
    public List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(6);
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:67:0x00df A[PHI: r9
      0x00df: PHI (r9v13 java.lang.String) = (r9v12 java.lang.String), (r9v14 java.lang.String) binds: [B:63:0x00d4, B:66:0x00dd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:92:0x014f  */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ca, code lost:
    
        if (r8.r("fanspeed") != false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0157, code lost:
    
        if (r8.r("fanspeed") != false) goto L95;
     */
    @Override // cg.l.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public l.d b(ee.j jVar, String str, JSONObject jSONObject) {
        String string;
        StringBuilder sb2;
        String str2;
        Objects.toString(jSONObject);
        jVar.l();
        l.d dVar = new l.d();
        str.getClass();
        switch (str) {
            case "speed_up":
                if (!jVar.r(ControlKey.KEY_WIND_SPEED_INC)) {
                    if (jVar.r(ControlKey.KEY_WIND_SPEED)) {
                        jVar.E(ControlKey.KEY_WIND_SPEED);
                        dVar.f12231b = "改变风速";
                    }
                    break;
                } else {
                    jVar.E(ControlKey.KEY_WIND_SPEED_INC);
                    string = "增大风速";
                    dVar.f12231b = string;
                }
                return dVar;
            case "switch_wind_type":
                if (jVar.r("wind_type")) {
                    jVar.E("wind_type");
                    string = "改变风类";
                    dVar.f12231b = string;
                }
                return dVar;
            case "mute":
                if (jVar.r("mute")) {
                    jVar.E("mute");
                    string = "静音模式";
                    dVar.f12231b = string;
                }
                return dVar;
            case "open":
                if (jVar.r("power")) {
                    jVar.E("power");
                    sb2 = new StringBuilder();
                    str2 = "打开";
                    sb2.append(str2);
                    sb2.append(jVar.l());
                    string = sb2.toString();
                    dVar.f12231b = string;
                }
                return dVar;
            case "close":
                if (jVar.r("poweroff")) {
                    jVar.E("poweroff");
                } else if (jVar.r("power")) {
                    jVar.E("power");
                }
                sb2 = new StringBuilder();
                str2 = "关闭";
                sb2.append(str2);
                sb2.append(jVar.l());
                string = sb2.toString();
                dVar.f12231b = string;
                return dVar;
            case "swing":
                String str3 = "oscillation";
                if (jVar.r("oscillation")) {
                    jVar.E(str3);
                } else {
                    str3 = ControlKey.KEY_SHAKE_WIND;
                    if (jVar.r(ControlKey.KEY_SHAKE_WIND)) {
                        jVar.E(str3);
                    }
                }
                string = "风扇摇头";
                dVar.f12231b = string;
                return dVar;
            case "speed_down":
                if (!jVar.r(ControlKey.KEY_WIND_SPEED_INC)) {
                    if (jVar.r(ControlKey.KEY_WIND_SPEED)) {
                        jVar.E(ControlKey.KEY_WIND_SPEED);
                        dVar.f12231b = "改变风速";
                    }
                    break;
                } else {
                    jVar.E(ControlKey.KEY_WIND_SPEED_INC);
                    string = "减小风速";
                    dVar.f12231b = string;
                }
                return dVar;
            case "set_timer":
                if (jVar.r("timer")) {
                    jVar.E("timer");
                    string = "设置定时";
                    dVar.f12231b = string;
                }
                return dVar;
            case "switch_speed":
                if (jVar.r(ControlKey.KEY_WIND_SPEED)) {
                    jVar.E(ControlKey.KEY_WIND_SPEED);
                } else if (jVar.r("fanspeed")) {
                    jVar.E("fanspeed");
                }
                dVar.f12231b = "改变风速";
                return dVar;
            default:
                dVar.f12231b = l.f12206m;
                dVar.f12230a = false;
                return dVar;
        }
    }

    @Override // cg.l.e
    public List<ee.j> c() {
        return yd.k.g.f72699a.L();
    }

    @Override // cg.l.e
    public ee.j d() {
        return null;
    }

    @Override // cg.l.e
    public String e() {
        return "fan";
    }

    public C0143b f(JSONObject jSONObject) {
        C0143b c0143b = new C0143b();
        try {
            if (jSONObject.has("action")) {
                c0143b.f12155a = jSONObject.optString("action");
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        return c0143b;
    }
}
