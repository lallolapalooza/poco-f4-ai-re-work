package com.xiaomi.payment.l;

import android.text.TextUtils;
import android.util.Log;
import com.mipay.common.d.l;
import java.io.Serializable;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: EntryData.java */
/* JADX INFO: loaded from: classes2.dex */
public class c implements Serializable {
    private static final String m = "EntryData";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3646a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f3647b;
    public Map<String, Object> h;
    public String i;
    public String j;
    public String k;
    private transient Map<String, Object> l;

    /* JADX INFO: compiled from: EntryData.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3648a = new int[b.values().length];

        static {
            try {
                f3648a[b.LOCAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3648a[b.WEB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3648a[b.APP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public <T> T a(String str, T t) {
        Map<String, Object> map = this.l;
        if (map != null && map.containsKey(str)) {
            try {
                return (T) t.getClass().cast(this.l.get(str));
            } catch (Exception unused) {
            }
        }
        return t;
    }

    /* JADX INFO: compiled from: EntryData.java */
    public enum b {
        LOCAL,
        WEB,
        APP;

        public static b a(String str) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException e2) {
                Log.e(c.m, "EntryData Type IllegalArgumentException ", e2);
                return null;
            }
        }

        public static String a() {
            b[] bVarArrValues = values();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < bVarArrValues.length; i2++) {
                if (i2 > 0) {
                    sb.append(',');
                }
                sb.append(bVarArrValues[i2].name());
            }
            return sb.toString();
        }
    }

    public void a(JSONObject jSONObject) throws l {
        if (jSONObject != null) {
            try {
                this.f3646a = jSONObject.getString("id");
                this.h = com.xiaomi.payment.l.b.b(jSONObject.optJSONObject(com.mipay.common.data.f.X0));
                this.l = com.xiaomi.payment.l.b.b(jSONObject.optJSONObject(com.mipay.common.data.f.Y0));
                String string = jSONObject.getString("type");
                this.f3647b = b.a(string);
                if (this.f3647b != null) {
                    int i = a.f3648a[this.f3647b.ordinal()];
                    if (i == 1) {
                        this.k = jSONObject.optString("url");
                        return;
                    }
                    if (i == 2) {
                        this.k = jSONObject.getString("url");
                        if (TextUtils.isEmpty(this.k)) {
                            throw new l("mUrl should not all be null in WEB, type is " + string);
                        }
                        return;
                    }
                    if (i != 3) {
                        return;
                    }
                    this.k = jSONObject.optString("url");
                    this.i = jSONObject.getString(com.mipay.common.data.f.U0);
                    this.j = jSONObject.getString(com.mipay.common.data.f.V0);
                    if (TextUtils.isEmpty(this.i) && TextUtils.isEmpty(this.j)) {
                        throw new l("mIntentUri and mPackageName should not all be null in APP, type is " + string);
                    }
                    return;
                }
                throw new l("entryData type is null, type is " + string);
            } catch (JSONException e2) {
                throw new l(e2);
            }
        }
    }
}
