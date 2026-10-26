package cg;

import com.xiaomi.mitv.phone.remotecontroller.ir.dk.model.ControlKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class g implements l.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f12175b = "TVNLPProcessor";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public af.j f12176a = new af.j();

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f12177a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f12178b;

        public b() {
        }
    }

    @Override // cg.l.e
    public List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(10001);
        return arrayList;
    }

    @Override // cg.l.e
    public l.d b(ee.j jVar, String str, JSONObject jSONObject) {
        Objects.toString(jSONObject);
        jVar.l();
        jVar.e();
        return (jVar.e() == 10001 || jVar.e() == 10000) ? h(jVar, str) : g(jVar, str);
    }

    @Override // cg.l.e
    public List<ee.j> c() {
        return yd.k.g.f72699a.p0();
    }

    @Override // cg.l.e
    public ee.j d() {
        return null;
    }

    @Override // cg.l.e
    public String e() {
        return ControlKey.KEY_TV;
    }

    public b f(JSONObject jSONObject) {
        b bVar = new b();
        try {
            if (jSONObject.has("action")) {
                bVar.f12177a = jSONObject.optString("action");
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        return bVar;
    }

    public l.d g(ee.j jVar, String str) {
        String string;
        StringBuilder sb2;
        String str2;
        l.d dVar = new l.d();
        str.getClass();
        switch (str) {
            case "volume_up":
                if (jVar.r("vol+")) {
                    jVar.E("vol+");
                    string = "音量增大";
                    dVar.f12231b = string;
                }
                return dVar;
            case "volume_down":
                if (jVar.r("vol-")) {
                    jVar.E("vol-");
                    string = "音量减小";
                    dVar.f12231b = string;
                }
                return dVar;
            case "navigate_back":
                if (jVar.r("back")) {
                    jVar.E("back");
                    string = "返回";
                    dVar.f12231b = string;
                }
                return dVar;
            case "navigate_home":
                if (jVar.r("back")) {
                    jVar.E("back");
                    string = "回到主页";
                    dVar.f12231b = string;
                }
                return dVar;
            case "navigate_menu":
                if (jVar.r("menu")) {
                    jVar.E("menu");
                    string = "菜单";
                    dVar.f12231b = string;
                }
                return dVar;
            case "channel_next":
                if (jVar.r("ch+")) {
                    jVar.E("ch+");
                    string = "下一频道";
                    dVar.f12231b = string;
                }
                return dVar;
            case "channel_prev":
                if (jVar.r("ch-")) {
                    jVar.E("ch-");
                    string = "上一频道";
                    dVar.f12231b = string;
                }
                return dVar;
            case "mute":
                if (jVar.r("back")) {
                    jVar.E("back");
                    string = "电视静音";
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
            case "navigate_ok":
                if (jVar.r("ok")) {
                    jVar.E("ok");
                    string = "确认";
                    dVar.f12231b = string;
                }
                return dVar;
            default:
                dVar.f12231b = l.f12206m;
                dVar.f12230a = false;
                return dVar;
        }
    }

    public l.d h(ee.j jVar, String str) {
        String string;
        StringBuilder sb2;
        String str2;
        l.d dVar = new l.d();
        str.getClass();
        switch (str) {
            case "volume_up":
                i(this.f12176a.o());
                string = "音量增大";
                dVar.f12231b = string;
                return dVar;
            case "volume_down":
                i(this.f12176a.p());
                string = "音量减小";
                dVar.f12231b = string;
                return dVar;
            case "navigate_back":
                i(this.f12176a.a());
                string = "返回";
                dVar.f12231b = string;
                return dVar;
            case "navigate_down":
                i(this.f12176a.b());
                string = "向下";
                dVar.f12231b = string;
                return dVar;
            case "navigate_home":
                i(this.f12176a.c());
                string = "回到桌面";
                dVar.f12231b = string;
                return dVar;
            case "navigate_left":
                i(this.f12176a.d());
                string = "向左";
                dVar.f12231b = string;
                return dVar;
            case "navigate_menu":
                i(this.f12176a.i());
                string = "菜单";
                dVar.f12231b = string;
                return dVar;
            case "open":
                i(this.f12176a.l());
                sb2 = new StringBuilder();
                str2 = "打开";
                sb2.append(str2);
                sb2.append(jVar.l());
                string = sb2.toString();
                dVar.f12231b = string;
                return dVar;
            case "close":
                i(this.f12176a.l());
                sb2 = new StringBuilder();
                str2 = "关闭";
                sb2.append(str2);
                sb2.append(jVar.l());
                string = sb2.toString();
                dVar.f12231b = string;
                return dVar;
            case "navigate_right":
                i(this.f12176a.m());
                string = "向右";
                dVar.f12231b = string;
                return dVar;
            case "navigate_ok":
                i(this.f12176a.j());
                string = "确认";
                dVar.f12231b = string;
                return dVar;
            case "navigate_up":
                i(this.f12176a.n());
                string = "向上";
                dVar.f12231b = string;
                return dVar;
            default:
                dVar.f12231b = l.f12206m;
                dVar.f12230a = false;
                return dVar;
        }
    }

    public void i(df.e eVar) {
        vd.d.g().l(eVar, true, true);
    }
}
