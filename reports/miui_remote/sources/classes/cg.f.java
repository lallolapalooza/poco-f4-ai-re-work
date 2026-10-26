package cg;

import com.xiaomi.mitv.epg.model.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;
import wf.u;

/* JADX INFO: loaded from: classes3.dex */
public class f implements l.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f12171a = "VoiceControlSTB";

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f12172a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f12173b;

        public b() {
        }
    }

    public static /* synthetic */ void h(le.h hVar, int i10) {
        le.h.P(String.valueOf(i10));
    }

    @Override // cg.l.e
    public List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(2);
        return arrayList;
    }

    @Override // cg.l.e
    public l.d b(ee.j jVar, String str, JSONObject jSONObject) {
        String string;
        StringBuilder sb2;
        Objects.toString(jSONObject);
        jVar.l();
        jVar.r("on");
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
            case "open":
                if (jVar.r("power")) {
                    jVar.E("power");
                    sb2 = new StringBuilder();
                    sb2.append("打开");
                    sb2.append(jVar.l());
                    string = sb2.toString();
                    dVar.f12231b = string;
                }
                return dVar;
            case "play":
                jVar.l();
                k(jVar, jSONObject, dVar);
                return dVar;
            case "close":
                if (!jVar.r("poweroff")) {
                    if (jVar.r("power")) {
                        jVar.E("power");
                        sb2 = new StringBuilder();
                    }
                    return dVar;
                }
                jVar.E("poweroff");
                sb2 = new StringBuilder();
                sb2.append("关闭");
                sb2.append(jVar.l());
                string = sb2.toString();
                dVar.f12231b = string;
                return dVar;
            default:
                dVar.f12231b = l.f12206m;
                dVar.f12230a = false;
                return dVar;
        }
    }

    @Override // cg.l.e
    public List<ee.j> c() {
        return yd.k.g.f72699a.n0();
    }

    @Override // cg.l.e
    public ee.j d() {
        List<ee.j> listC;
        ee.j jVarG = yd.k.g.f72699a.G();
        return (jVarG != null || (listC = c()) == null || listC.size() <= 0) ? jVarG : listC.get(0);
    }

    @Override // cg.l.e
    public String e() {
        return "stb";
    }

    public b j(JSONObject jSONObject) {
        b bVar = new b();
        try {
            if (jSONObject.has("action")) {
                bVar.f12172a = jSONObject.optString("action");
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        return bVar;
    }

    public void k(ee.j jVar, JSONObject jSONObject, l.d dVar) {
        JSONObject jSONObject2;
        String strA;
        StringBuilder sb2;
        jVar.l();
        Objects.toString(jSONObject);
        final int iOptInt = jSONObject.optInt("channel_num");
        String strOptString = jSONObject.optString("standard_channel");
        final le.h hVar = (le.h) vd.d.f();
        if (hVar == null) {
            return;
        }
        if (iOptInt <= 0) {
            final Channel channelR = hVar.r(strOptString);
            if (channelR == null) {
                try {
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("channels");
                    for (int i10 = 0; i10 < jSONArrayOptJSONArray.length() && ((jSONObject2 = (JSONObject) jSONArrayOptJSONArray.get(i10)) == null || (channelR = hVar.r(jSONObject2.optString("channel"))) == null); i10++) {
                    }
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
            }
            if (channelR != null) {
                u.e(new Runnable() { // from class: cg.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        le.h hVar2 = hVar;
                        le.h.O(channelR, null);
                    }
                });
                sb2 = new StringBuilder();
                sb2.append("换到");
                sb2.append(channelR.number);
                sb2.append("频道");
                sb2.append(channelR.name);
            } else {
                dVar.f12230a = false;
                strA = android.support.v4.media.k.a("对不起，我不知道", strOptString, "是哪个频道");
            }
            dVar.f12231b = strA;
        }
        u.e(new Runnable() { // from class: cg.d
            @Override // java.lang.Runnable
            public final void run() {
                f.h(hVar, iOptInt);
            }
        });
        sb2 = new StringBuilder();
        sb2.append("换到");
        sb2.append(iOptInt);
        sb2.append("频道");
        strA = sb2.toString();
        dVar.f12231b = strA;
    }
}
