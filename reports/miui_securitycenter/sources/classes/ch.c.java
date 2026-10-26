package ch;

import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f6226h = "ch.c";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f6227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f6228b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f6229c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f6230d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Map<String, c> f6231e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<String[]> f6232f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f6233g = false;

    public c(Map<String, c> map) {
        this.f6231e = map;
    }

    public String a() {
        return this.f6230d;
    }

    public int b() {
        return this.f6229c;
    }

    public boolean c(JSONObject jSONObject) {
        if (!jSONObject.has("sms_type") && !jSONObject.has("assist")) {
            return false;
        }
        try {
            if (jSONObject.has("sms_type")) {
                this.f6229c = jSONObject.getInt("sms_type");
            }
            if (jSONObject.has("assist") && jSONObject.getBoolean("assist")) {
                this.f6233g = true;
            }
            if (jSONObject.has("name")) {
                this.f6230d = jSONObject.getString("name");
            }
            if (jSONObject.has("address")) {
                this.f6227a = new a(jSONObject.getJSONObject("address"));
            }
            if (jSONObject.has(TtmlNode.TAG_BODY)) {
                this.f6228b = new b(jSONObject.getJSONObject(TtmlNode.TAG_BODY));
            }
            if (jSONObject.has("combi")) {
                JSONArray jSONArray = jSONObject.getJSONArray("combi");
                int length = jSONArray.length();
                this.f6232f = new ArrayList();
                for (int i10 = 0; i10 < length; i10++) {
                    this.f6232f.add(jSONArray.getString(i10).split("&"));
                }
            }
            return (this.f6227a == null && this.f6228b == null && this.f6232f == null) ? false : true;
        } catch (JSONException unused) {
            Log.e(f6226h, "JSONException when decode features.");
            return false;
        }
    }

    public boolean d() {
        return this.f6233g;
    }

    public boolean e(ah.b bVar) {
        boolean z10;
        a aVar = this.f6227a;
        if (aVar != null && !aVar.b(bVar)) {
            return false;
        }
        b bVar2 = this.f6228b;
        if (bVar2 != null && !bVar2.b(bVar)) {
            return false;
        }
        List<String[]> list = this.f6232f;
        if (list == null) {
            return true;
        }
        for (String[] strArr : list) {
            int length = strArr.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    z10 = true;
                    break;
                }
                if (!this.f6231e.get(strArr[i10]).e(bVar)) {
                    z10 = false;
                    break;
                }
                i10++;
            }
            if (z10) {
                return true;
            }
        }
        return false;
    }
}
