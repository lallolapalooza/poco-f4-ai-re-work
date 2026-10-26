package ch;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a extends e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final String f6220i = "ch.a";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int[] f6221h;

    a(JSONObject jSONObject) {
        super(jSONObject);
        try {
            if (jSONObject.has("type")) {
                JSONArray jSONArray = jSONObject.getJSONArray("type");
                this.f6221h = new int[jSONArray.length()];
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    this.f6221h[i10] = jSONArray.getInt(i10);
                }
            }
        } catch (JSONException unused) {
            Log.e(f6220i, "JSONException when decode KEY_TYPE features.");
        }
    }

    public boolean b(ah.b bVar) {
        boolean z10;
        int iC = bVar.c();
        int[] iArr = this.f6221h;
        if (iArr != null && iArr.length > 0) {
            int length = iArr.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    z10 = false;
                    break;
                }
                if (iArr[i10] == iC) {
                    z10 = true;
                    break;
                }
                i10++;
            }
            if (!z10) {
                return false;
            }
        }
        return super.a(bVar.b());
    }
}
