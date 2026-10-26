package ch;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes3.dex */
public class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f6234d = "ch.d";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, c> f6235a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<c> f6236b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f6237c = false;

    public static int a(int i10) {
        int i11 = (i10 & MenuBuilder.USER_MASK) >> 4;
        int i12 = 0;
        int i13 = 12;
        while (i11 != 0) {
            if ((i11 & 1) != 0) {
                i12 += i13;
            }
            i11 >>= 1;
            i13--;
        }
        return i12;
    }

    public static int b(int i10) {
        int i11 = (i10 & 268369920) >> 16;
        int i12 = 1;
        int i13 = 0;
        while (i11 != 0) {
            if ((i11 & 1) != 0) {
                i13 += i12;
            }
            i11 >>= 1;
            i12++;
        }
        return i13;
    }

    public boolean c() {
        return this.f6237c;
    }

    public void d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(new JSONTokener(str));
            String string = jSONObject.getString("version");
            String str2 = f6234d;
            Log.d(str2, "Version:" + string);
            JSONArray jSONArray = jSONObject.getJSONArray("features");
            int length = jSONArray.length();
            Log.d(str2, "feature count:" + length);
            this.f6235a = new HashMap();
            this.f6236b = new ArrayList();
            for (int i10 = 0; i10 < length; i10++) {
                c cVar = new c(this.f6235a);
                if (cVar.c(jSONArray.getJSONObject(i10))) {
                    this.f6235a.put(cVar.a(), cVar);
                    this.f6236b.add(cVar);
                }
            }
            this.f6237c = true;
        } catch (JSONException e10) {
            this.f6237c = false;
            Log.e(f6234d, "JSONException when load features from pattern files");
            e10.printStackTrace();
        }
    }

    public int e(ah.b bVar) {
        int iB = 0;
        for (c cVar : this.f6236b) {
            if (!cVar.d() && cVar.e(bVar)) {
                iB |= cVar.b();
            }
        }
        return iB;
    }
}
