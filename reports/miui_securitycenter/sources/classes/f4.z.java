package f4;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.miui.networkassistant.config.Constants;
import com.miui.networkassistant.utils.TelephonyUtil;
import com.miui.permcenter.compact.MiuiSettingsCompat;
import com.miui.permcenter.compact.SystemPropertiesCompat;
import com.miui.securitycenter.Application;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import miuix.core.util.SystemProperties;

/* JADX INFO: loaded from: classes2.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f45821a = ce.j.m();

    public static boolean A() {
        return p1.a("ro.miui.google.csp", false);
    }

    public static boolean B(Configuration configuration) {
        return h.b(configuration);
    }

    public static boolean C(Context context) {
        return ((PowerManager) context.getSystemService("power")).isScreenOn();
    }

    public static boolean D(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "miui_new_version");
        Log.d("SecurityCenterDebug", "curVersion : " + Build.VERSION.INCREMENTAL + ", newVersion : " + string);
        if (TextUtils.isEmpty(string) || Build.VERSION.INCREMENTAL.equals(string)) {
            return false;
        }
        String strM = od.g.m();
        od.g.J(string);
        if (TextUtils.isEmpty(strM) || strM.equals(string)) {
            return true;
        }
        ce.n.c("MIUI_UPDATE");
        return true;
    }

    public static boolean E() {
        return Build.VERSION.SDK_INT < 28 || Settings.Global.getInt(Application.y().getContentResolver(), "device_provisioning_mobile_data", 0) == 1 || Settings.Global.getInt(Application.y().getContentResolver(), Constants.System.DEVICE_PROVISIONED, 0) == 1;
    }

    public static boolean F() {
        return yi.a.D() || v() || w();
    }

    public static boolean G(Context context) {
        return ve.d.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("isVoiceCapable", null, new Object[0]).a();
    }

    public static boolean H() {
        return SystemProperties.getInt("persist.sys.muiltdisplay_type", 0) == 2;
    }

    public static boolean I() {
        return SystemPropertiesCompat.getBoolean("ro.radio.noril", false);
    }

    public static Address a(Context context, Location location) {
        try {
            List<Address> fromLocation = new Geocoder(context, Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (fromLocation.size() > 0) {
                return fromLocation.get(0);
            }
            return null;
        } catch (Exception e10) {
            e10.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static int b(Context context, float f10) {
        return v3.a.a(context, f10);
    }

    public static String c(Context context) {
        return ve.d.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("getSubscriberIdForSlot", new Class[]{Integer.TYPE}, Integer.valueOf(m())).m();
    }

    public static String d(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        return string == null ? "" : string;
    }

    public static int e() {
        int iN = (int) (((t.n() / 1024) / 1024) / 1024);
        if (iN <= 4) {
            return 1;
        }
        return iN <= 6 ? 2 : 4;
    }

    public static String f(Context context) {
        if (f45821a) {
            return "";
        }
        String strM = null;
        try {
            strM = ve.d.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("getDeviceId", null, new Object[0]).m();
        } catch (Exception unused) {
        }
        return strM == null ? "" : strM;
    }

    public static String g(Context context) {
        try {
            Class<?> cls = Class.forName("android.provider.MiuiSettings$System");
            return (String) cls.getMethod("getDeviceName", Context.class).invoke(cls, context);
        } catch (Exception e10) {
            Log.e("DeviceUtils", "Get Phone name error:", e10);
            return null;
        }
    }

    @Deprecated
    public static String h() {
        return x.c();
    }

    @Deprecated
    public static final String i() {
        return com.miui.common.a.b();
    }

    public static String j(Context context) {
        String strC = c(context);
        return TextUtils.isEmpty(strC) ? "OTHER" : k(context, strC);
    }

    private static String k(Context context, String str) {
        if (str.startsWith("46001")) {
            return TelephonyUtil.UNICOM;
        }
        if (str.startsWith("46003") || str.startsWith("460003")) {
            return TelephonyUtil.TELECOM;
        }
        return (str.startsWith("46000") || str.startsWith("46002") || str.startsWith("46007")) ? TelephonyUtil.CMCC : "OTHER";
    }

    public static String l(Context context) {
        String strC = c(context);
        if (TextUtils.isEmpty(strC)) {
            return "OTHER";
        }
        String strK = k(context, strC);
        return "OTHER".equals(strK) ? strC.substring(0, 8) : strK;
    }

    public static int m() {
        int i10 = ve.d.a.d("miui.telephony.SubscriptionManager").c("getDefault", null, new Object[0]).l().b("getDefaultDataSlotId", null, new Object[0]).i();
        if (i10 < 0 || i10 > 1) {
            return 0;
        }
        return i10;
    }

    public static int n() {
        return y.a();
    }

    @Deprecated
    public static int o(Context context) {
        return x.g(context);
    }

    public static String p() {
        String property = System.getProperty("http.agent");
        return TextUtils.isEmpty(property) ? "" : property;
    }

    private static boolean q(Context context, int i10) {
        String strM = ve.d.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("getSimOperatorForSlot", new Class[]{Integer.TYPE}, Integer.valueOf(i10)).m();
        return TextUtils.isEmpty(strM) || strM.startsWith("460");
    }

    public static boolean r(Context context) {
        return q(context, m());
    }

    public static boolean s(List<String> list) {
        String str = Build.DEVICE;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(str, it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean t(String... strArr) {
        String str = Build.DEVICE;
        for (String str2 : strArr) {
            if (TextUtils.equals(str, str2)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean u() {
        return x.h();
    }

    public static boolean v() {
        return y.c();
    }

    public static boolean w() {
        return yi.a.F();
    }

    public static boolean x(Context context) {
        return MiuiSettingsCompat.isNavigationBarFullScreen(context, "force_fsg_nav_bar");
    }

    public static boolean y() {
        return yi.a.E() || yi.a.C() || w();
    }

    @Deprecated
    public static boolean z() {
        return x.o();
    }
}
