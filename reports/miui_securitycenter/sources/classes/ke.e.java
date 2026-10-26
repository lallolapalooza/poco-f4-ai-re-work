package ke;

import android.text.TextUtils;
import com.miui.analytics.AnalyticsUtil;
import com.miui.analytics.StatManager;
import com.xiaomi.miglobaladsdk.bid.BidConstance;
import f4.v0;
import miuix.core.util.SystemProperties;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f48621a = SystemProperties.get("ro.product.model", "");

    private static String a(int i10, double d10, boolean z10) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("state", i10);
            jSONObject.put("type", z10 ? 2 : 1);
            jSONObject.put(StatManager.PARAMS_DURATION, d10);
            jSONObject.put("model", f48621a);
            jSONObject.put(BidConstance.BID_EXT, "");
            return jSONObject.toString();
        } catch (JSONException e10) {
            e10.printStackTrace();
            return "";
        }
    }

    private static void b(String str, String str2) {
        AnalyticsUtil.recordStringPropertyEvent("superpower", str, str2);
    }

    public static void c(int i10) {
        b("key_battery_level_exit_normal", String.valueOf(((i10 - 1) / 10) + 1));
    }

    public static void d(int i10) {
        b("key_battery_level_open_normal", String.valueOf(((i10 - 1) / 10) + 1));
    }

    public static void e(double d10, boolean z10) {
        if (v0.a()) {
            String strA = a(1, d10, z10);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            v0.b("securitycenter_power", strA, "mqs_super_power_saving_43081000", true);
        }
    }

    public static void f(int i10) {
        b("key_battery_level_exit_super", String.valueOf(((i10 - 1) / 10) + 1));
    }

    public static void g(int i10) {
        b("key_battery_level_open_super", String.valueOf(((i10 - 1) / 10) + 1));
    }

    public static void h(String str) {
        b("open_way", str);
    }

    public static void i(double d10, boolean z10) {
        if (v0.a()) {
            String strA = a(2, d10, z10);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            v0.b("securitycenter_power", strA, "mqs_super_power_saving_43081000", true);
        }
    }
}
