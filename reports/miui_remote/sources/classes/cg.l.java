package cg;

import android.app.Activity;
import android.content.Context;
import android.media.AudioTrack;
import android.view.View;
import com.duokan.phone.remotecontroller.R;
import com.xiaomi.ai.AsrListener;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.ai.NlpListener;
import com.xiaomi.ai.NlpRequest;
import com.xiaomi.ai.PCMInfo;
import com.xiaomi.ai.SpeechEngine;
import com.xiaomi.ai.SpeechError;
import com.xiaomi.ai.SpeechResult;
import com.xiaomi.ai.TtsListener;
import com.xiaomi.ai.TtsRequest;
import com.xiaomi.mitv.phone.remotecontroller.XMRCApplication;
import com.xiaomi.mitv.phone.remotecontroller.remotecall.RemoteCallHandlerActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class l {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f12206m = "对不起，我没有听懂";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f12207n = "VoiceControlManager";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int f12208o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int f12209p = 3;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f12210q = "2882303761517406027";

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f12211r = "5311740683027";

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String f12212s = "2882303761517406041";

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f12213t = "5141740612041";

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static l f12214u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SpeechEngine f12215a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f f12216b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HashMap<String, e> f12217c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f12218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashSet<Integer> f12219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ud.d f12220f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public AsrRequest f12221g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public NlpRequest f12222h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TtsRequest f12223i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public AsrListener f12224j = new a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public NlpListener f12225k = new b();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public TtsListener f12226l = new c();

    public class a extends AsrListener.SimpleAsrListener {
        public a() {
        }

        /* JADX WARN: Code duplicated, block: B:12:0x0077  */
        /* JADX WARN: Code duplicated, block: B:14:? A[RETURN, SYNTHETIC] */
        @Override // com.xiaomi.ai.AsrListener.SimpleAsrListener, com.xiaomi.ai.AsrListener
        public void onError(SpeechError speechError) {
            String string;
            Context contextG;
            int i10;
            f fVar;
            speechError.toString();
            if (speechError.getErrCode() != 5) {
                if (speechError.getErrCode() == 13) {
                    contextG = l.this.g();
                    i10 = R.string.voice_control_err_network;
                } else {
                    string = String.format(l.this.g().getString(R.string.voice_control_err_common), Integer.valueOf(speechError.getErrCode()));
                }
                HashMap map = new HashMap();
                map.put("err_type", String.valueOf(speechError.getErrType()));
                map.put("err_code", String.valueOf(speechError.getErrCode()));
                map.put("err_msg", speechError.getErrMsg());
                fVar = l.this.f12216b;
                if (fVar != null) {
                    fVar.g(string);
                }
            }
            contextG = l.this.g();
            i10 = R.string.voice_control_err_permission;
            string = contextG.getString(i10);
            HashMap map2 = new HashMap();
            map2.put("err_type", String.valueOf(speechError.getErrType()));
            map2.put("err_code", String.valueOf(speechError.getErrCode()));
            map2.put("err_msg", speechError.getErrMsg());
            fVar = l.this.f12216b;
            if (fVar != null) {
                fVar.g(string);
            }
        }

        @Override // com.xiaomi.ai.AsrListener.SimpleAsrListener, com.xiaomi.ai.AsrListener
        public void onPartialResults(SpeechResult speechResult) {
            f fVar = l.this.f12216b;
            if (fVar != null) {
                fVar.f(speechResult);
            }
        }

        @Override // com.xiaomi.ai.AsrListener.SimpleAsrListener, com.xiaomi.ai.AsrListener
        public void onResults(SpeechResult speechResult) {
            speechResult.getResponse();
            f fVar = l.this.f12216b;
            if (fVar != null) {
                fVar.c(speechResult);
            }
        }
    }

    public class b implements NlpListener {
        public b() {
        }

        @Override // com.xiaomi.ai.NlpListener
        public void onError(SpeechError speechError) {
        }

        @Override // com.xiaomi.ai.NlpListener
        public void onResult(SpeechResult speechResult) {
            l lVar;
            l lVar2;
            e eVar;
            ee.j jVarH;
            String str;
            String str2;
            speechResult.getResponse();
            speechResult.getAnswer();
            if (speechResult.getAnswer() == null) {
                l.this.f12216b.g(l.f12206m);
                return;
            }
            if (vd.d.x()) {
                if (speechResult.getAnswer() == null || l.this.f12216b == null) {
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(speechResult.getAnswer());
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("content");
                    jSONObjectOptJSONObject.toString();
                    String strOptString = jSONObject.optString("text");
                    if (!wf.d.j(strOptString)) {
                        l.this.n(strOptString);
                    }
                    l.this.f12216b.b(jSONObjectOptJSONObject.toString());
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            d dVar = new d();
            try {
                try {
                    JSONObject jSONObjectOptJSONObject2 = new JSONObject(speechResult.getAnswer()).optJSONObject("intention");
                    if (jSONObjectOptJSONObject2 == null) {
                        dVar.f12230a = false;
                        if (l.this.f12216b != null) {
                            HashMap map = new HashMap();
                            map.put(RemoteCallHandlerActivity.f20304f, speechResult.getQuery());
                            map.put("speak_text", dVar.f12231b);
                            if (dVar.f12230a) {
                                l.this.f12216b.b(dVar.f12231b);
                                return;
                            } else {
                                l.this.f12216b.g(dVar.f12231b);
                                return;
                            }
                        }
                        return;
                    }
                    jSONObjectOptJSONObject2.toString();
                    String strOptString2 = jSONObjectOptJSONObject2.optString("domain");
                    String strOptString3 = jSONObjectOptJSONObject2.optString("entity");
                    String strOptString4 = jSONObjectOptJSONObject2.optString("action");
                    HashMap map2 = new HashMap();
                    map2.put("domain", strOptString2);
                    map2.put("entity", strOptString3);
                    map2.put("action", strOptString4);
                    if (!strOptString2.isEmpty() && !strOptString4.isEmpty()) {
                        if (strOptString2.equalsIgnoreCase("tvChannelControl")) {
                            eVar = l.this.f12218d;
                            jVarH = yd.k.g.f72699a.G();
                        } else {
                            if (strOptString3.isEmpty()) {
                                dVar.f12230a = false;
                                if (l.this.f12216b != null) {
                                    HashMap map3 = new HashMap();
                                    map3.put(RemoteCallHandlerActivity.f20304f, speechResult.getQuery());
                                    map3.put("speak_text", dVar.f12231b);
                                    if (dVar.f12230a) {
                                        l.this.f12216b.b(dVar.f12231b);
                                        return;
                                    } else {
                                        l.this.f12216b.g(dVar.f12231b);
                                        return;
                                    }
                                }
                                return;
                            }
                            eVar = l.this.f12217c.get(strOptString3);
                            jVarH = l.this.h(jSONObjectOptJSONObject2, strOptString3);
                        }
                        if (eVar == null) {
                            String strI = l.this.i(strOptString3);
                            if (strI != null) {
                                str = "对不起，暂不支持对" + strI + "的语音控制";
                            } else {
                                str = "对不起，暂不支持对该设备的语音控制";
                            }
                            dVar.f12231b = str;
                            dVar.f12230a = false;
                        } else if (jVarH != null) {
                            dVar = eVar.b(jVarH, strOptString4, jSONObjectOptJSONObject2);
                            String str3 = dVar.f12231b;
                        } else {
                            dVar.f12230a = false;
                            if (strOptString2.equalsIgnoreCase("tvChannelControl")) {
                                str2 = "对不起，您还没有绑定机顶盒，请在电视节目页面绑定机顶盒";
                            } else {
                                String strI2 = l.this.i(strOptString3);
                                if (strI2 != null) {
                                    str2 = "对不起，您还没有" + strI2 + "，请先添加一个" + strI2;
                                } else {
                                    str2 = "对不起，没有找到支持该操作的设备， 请先添加设备";
                                }
                            }
                            dVar.f12231b = str2;
                        }
                        if (l.this.f12216b != null) {
                            HashMap map4 = new HashMap();
                            map4.put(RemoteCallHandlerActivity.f20304f, speechResult.getQuery());
                            map4.put("speak_text", dVar.f12231b);
                            if (dVar.f12230a) {
                                lVar2 = l.this;
                                lVar2.f12216b.b(dVar.f12231b);
                                return;
                            } else {
                                lVar = l.this;
                                lVar.f12216b.g(dVar.f12231b);
                                return;
                            }
                        }
                        return;
                    }
                    dVar.f12230a = false;
                    if (l.this.f12216b != null) {
                        HashMap map5 = new HashMap();
                        map5.put(RemoteCallHandlerActivity.f20304f, speechResult.getQuery());
                        map5.put("speak_text", dVar.f12231b);
                        if (dVar.f12230a) {
                            l.this.f12216b.b(dVar.f12231b);
                        } else {
                            l.this.f12216b.g(dVar.f12231b);
                        }
                    }
                } catch (Exception e10) {
                    e10.printStackTrace();
                    if (l.this.f12216b == null) {
                        return;
                    }
                    HashMap map6 = new HashMap();
                    map6.put(RemoteCallHandlerActivity.f20304f, speechResult.getQuery());
                    map6.put("speak_text", dVar.f12231b);
                    if (!dVar.f12230a) {
                        lVar = l.this;
                        lVar.f12216b.g(dVar.f12231b);
                        return;
                    }
                    lVar2 = l.this;
                }
            } catch (Throwable th2) {
                if (l.this.f12216b != null) {
                    HashMap map7 = new HashMap();
                    map7.put(RemoteCallHandlerActivity.f20304f, speechResult.getQuery());
                    map7.put("speak_text", dVar.f12231b);
                    if (dVar.f12230a) {
                        l.this.f12216b.b(dVar.f12231b);
                    } else {
                        l.this.f12216b.g(dVar.f12231b);
                    }
                }
                throw th2;
            }
        }
    }

    public class c implements TtsListener {
        public c() {
        }

        @Override // com.xiaomi.ai.TtsListener
        public void onError(SpeechError speechError) {
        }

        @Override // com.xiaomi.ai.TtsListener
        public void onPCMData(PCMInfo pCMInfo) {
        }

        @Override // com.xiaomi.ai.TtsListener
        public void onPlayFinish() {
            f fVar = l.this.f12216b;
            if (fVar != null) {
                fVar.i();
            }
        }

        @Override // com.xiaomi.ai.TtsListener
        public void onPlayStart(AudioTrack audioTrack) {
        }

        @Override // com.xiaomi.ai.TtsListener
        public void onTtsGotURL(String str) {
        }

        @Override // com.xiaomi.ai.TtsListener
        public void onTtsTransEnd(boolean z10) {
        }

        @Override // com.xiaomi.ai.TtsListener
        public void onTtsTransStart() {
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f12230a = true;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f12231b = l.f12206m;
    }

    public interface e {
        List<Integer> a();

        d b(ee.j jVar, String str, JSONObject jSONObject);

        List<ee.j> c();

        ee.j d();

        String e();
    }

    public interface f {
        void b(String str);

        void c(SpeechResult speechResult);

        void f(SpeechResult speechResult);

        void g(String str);

        void i();
    }

    public l() {
        j();
    }

    public static synchronized l l() {
        if (f12214u == null) {
            l lVar = new l();
            f12214u = lVar;
            lVar.k();
        }
        return f12214u;
    }

    public void f(e eVar) {
        this.f12217c.put(eVar.e(), eVar);
        Iterator<Integer> it = eVar.a().iterator();
        while (it.hasNext()) {
            this.f12219e.add(it.next());
        }
    }

    public final Context g() {
        return XMRCApplication.d().getApplicationContext();
    }

    public ee.j h(JSONObject jSONObject, String str) {
        List<ee.j> listC;
        Objects.toString(jSONObject);
        String strOptString = jSONObject.optString("device_list");
        ee.j jVarD = null;
        if (strOptString == null || strOptString.length() <= 0) {
            e eVar = this.f12217c.get(str);
            if (eVar != null && (jVarD = eVar.d()) == null && (listC = eVar.c()) != null && listC.size() > 0) {
                jVarD = listC.get(0);
            }
        } else {
            String[] strArrSplit = strOptString.split(",");
            if (strArrSplit != null && strArrSplit.length > 0) {
                jVarD = yd.k.g.f72699a.J(Integer.parseInt(strArrSplit[0]));
            }
        }
        if (jVarD != null) {
            jVarD.l();
        }
        return jVarD;
    }

    public final String i(String str) {
        str.getClass();
        switch (str) {
            case "aircondition":
                return "空调";
            case "camera":
                return "相机";
            case "heater":
                return "热水器";
            case "projector":
                return "投影仪";
            case "tv":
                return "电视";
            case "amp":
                return "功放";
            case "dvd":
                return "DVD播放机";
            case "fan":
                return "风扇";
            case "stb":
                return "机顶盒";
            case "light":
                return "灯泡";
            case "purifier":
                return "空气净化器";
            default:
                return null;
        }
    }

    public void j() {
        this.f12217c = new HashMap<>();
        this.f12219e = new HashSet<>();
        f(new cg.a());
        f(new g());
        cg.f fVar = new cg.f();
        this.f12218d = fVar;
        f(fVar);
        f(new cg.b());
    }

    public void k() {
        SpeechEngine speechEngineCreateEngine = SpeechEngine.createEngine(g(), 1, f12212s, f12213t);
        this.f12215a = speechEngineCreateEngine;
        if (vd.d.v()) {
            speechEngineCreateEngine.setEnv(0, f12212s, f12213t);
        } else {
            speechEngineCreateEngine.setEnv(3, f12212s, f12213t);
            this.f12215a.setUserAgent(String.format("MiRemote; Sensy/1.56 Build/%d Channel/Release OS/Android", Integer.valueOf(vd.d.f61139w)));
        }
        this.f12215a.setAsrLisnter(this.f12224j);
        this.f12215a.setNlpListener(this.f12225k);
        this.f12215a.setTtsListener(this.f12226l);
    }

    public void m(f fVar) {
        this.f12216b = fVar;
    }

    public void n(String str) {
        TtsRequest ttsRequest = new TtsRequest();
        ttsRequest.setTextToSpeak(str);
        this.f12215a.speak(ttsRequest);
    }

    public void o() {
        JSONArray jSONArray = new JSONArray();
        ArrayList arrayList = new ArrayList();
        List<ee.j> listR = yd.k.g.f72699a.R();
        listR.size();
        int iH = yd.k.g.f72699a.H();
        for (ee.j jVar : listR) {
            ee.e eVar = (ee.e) jVar.d();
            int iB = eVar.b();
            int iG = jVar.g();
            if (jVar.p() != 101 && jVar.p() != 102) {
                jVar.p();
            } else if (this.f12219e.contains(Integer.valueOf(iB))) {
                String strValueOf = String.valueOf(iG);
                String strL = jVar.l();
                String strD = ye.a.d(g(), eVar.b());
                String strK = eVar.k();
                if (iB == 10001) {
                    strD = ye.a.d(g(), 1);
                    strK = "小米";
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", strValueOf);
                    jSONObject.put("name", strL);
                    jSONObject.put("brand", strK);
                    jSONObject.put("type", strD);
                    if (iH <= 0 || iG != iH) {
                        arrayList.add(jSONObject);
                    } else {
                        jSONObject.put("selectedSTB", true);
                        arrayList.add(0, jSONObject);
                    }
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put((JSONObject) it.next());
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("all_devices", jSONArray);
        } catch (Exception e11) {
            e11.printStackTrace();
        }
        jSONObject2.toString();
        if (this.f12221g == null) {
            AsrRequest asrRequest = new AsrRequest();
            this.f12221g = asrRequest;
            asrRequest.setUseVad(true);
        }
        if (this.f12222h == null) {
            this.f12222h = new NlpRequest();
        }
        if (this.f12223i == null) {
            this.f12223i = new TtsRequest();
        }
        if (!vd.d.v()) {
            this.f12221g.setLang(Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry());
        }
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        this.f12222h.setContext("mi_remote", jSONObject2.toString());
        paramBuilder.needAsr().setAsrRequest(this.f12221g).needNlp().setNlpRequest(this.f12222h);
        this.f12215a.startIntegrally(paramBuilder);
    }

    public void p(Activity activity, View view) {
        this.f12220f = new ud.d(activity, view);
        View decorView = activity.getWindow().getDecorView();
        o();
        this.f12220f.c(decorView, view);
    }

    public void q() {
        this.f12215a.endSpeech();
        ud.d dVar = this.f12220f;
        if (dVar == null || !dVar.isShowing()) {
            return;
        }
        this.f12220f.dismiss();
    }

    public void r(f fVar) {
        this.f12216b = null;
    }
}
