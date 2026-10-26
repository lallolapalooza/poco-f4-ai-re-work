package ch;

import android.util.Log;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f6238g = "ch.e";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected String[] f6239a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Pattern[] f6240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected String[] f6241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Pattern[] f6242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected String[] f6243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Pattern[] f6244f;

    e(JSONObject jSONObject) {
        try {
            if (jSONObject.has("pattern")) {
                JSONArray jSONArray = jSONObject.getJSONArray("pattern");
                int length = jSONArray.length();
                this.f6239a = new String[length];
                this.f6240b = new Pattern[length];
                for (int i10 = 0; i10 < length; i10++) {
                    this.f6239a[i10] = jSONArray.getString(i10);
                    this.f6240b[i10] = Pattern.compile(this.f6239a[i10], 2);
                }
            }
            if (jSONObject.has("neg_pattern")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("neg_pattern");
                int length2 = jSONArray2.length();
                this.f6241c = new String[length2];
                this.f6242d = new Pattern[length2];
                for (int i11 = 0; i11 < length2; i11++) {
                    this.f6241c[i11] = jSONArray2.getString(i11);
                    this.f6242d[i11] = Pattern.compile(this.f6241c[i11], 2);
                }
            }
            if (jSONObject.has("or_pattern")) {
                JSONArray jSONArray3 = jSONObject.getJSONArray("or_pattern");
                int length3 = jSONArray3.length();
                this.f6243e = new String[length3];
                this.f6244f = new Pattern[length3];
                for (int i12 = 0; i12 < length3; i12++) {
                    this.f6243e[i12] = jSONArray3.getString(i12);
                    this.f6244f[i12] = Pattern.compile(this.f6243e[i12], 2);
                }
            }
        } catch (JSONException unused) {
            Log.e(f6238g, "JSONException when decode KEY_PATTERN features.");
        }
    }

    protected boolean a(String str) {
        Pattern[] patternArr = this.f6240b;
        if (patternArr != null && patternArr.length > 0) {
            for (Pattern pattern : patternArr) {
                if (!pattern.matcher(str).find()) {
                    return false;
                }
            }
        }
        Pattern[] patternArr2 = this.f6242d;
        if (patternArr2 != null && patternArr2.length > 0) {
            for (Pattern pattern2 : patternArr2) {
                if (pattern2.matcher(str).find()) {
                    return false;
                }
            }
        }
        Pattern[] patternArr3 = this.f6244f;
        if (patternArr3 == null || patternArr3.length <= 0) {
            return true;
        }
        for (Pattern pattern3 : patternArr3) {
            if (pattern3.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }
}
