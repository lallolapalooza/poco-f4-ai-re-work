package vd;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import com.xiaomi.mitv.epg.EpgManager;
import com.xiaomi.mitv.epg.model.Program;
import com.xiaomi.mitv.phone.remotecontroller.XMRCApplication;
import com.xiaomi.mitv.socialtv.common.utils.HanziToPinyin;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class d {
    public static String E = null;
    public static boolean F = false;
    public static boolean H = false;
    public static String J = null;
    public static EpgManager K = null;
    public static Program L = null;
    public static re.g M = null;
    public static String N = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f61117a = "GlobalData";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f61118b = "1008156";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f61119c = "F6E69BD9DB9B0FD7E3152F36B9A4B891";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f61120d = "controller_id";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f61121e = "controller_add";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f61122f = "room_id";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f61123g = "call_from";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f61124h = "channel_list";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f61125i = "device_type";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f61126j = "open_dev_id";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f61127k = "lock_screen";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f61128l = "src_xiaoai_assistant";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f61129m = "mihome";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f61130n = "mac";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final String f61131o = "shortcut";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f61132p = "miuitil";

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static String f61134r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static String f61135s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static String f61136t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static String f61137u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static String f61138v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static String f61140x;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Set<String> f61133q = new HashSet(Arrays.asList("AT", "BE", "BG", "CY", "CZ", "DE", "DK", "EE", "ES", "FI", "FR", "GB", "GR", "HR", "HU", "IE", "IT", "LT", "LU", "LV", "MT", "NL", "PL", "PT", "RO", "SE", "SI", "SK"));

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static int f61139w = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static boolean f61141y = false;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static boolean f61142z = false;
    public static boolean A = false;
    public static boolean B = false;
    public static boolean C = false;
    public static boolean D = false;
    public static boolean G = false;
    public static boolean I = false;

    public static boolean A(Activity activity) {
        return I || B(activity) || ej.a.v();
    }

    public static boolean B(Activity activity) {
        WindowInsets rootWindowInsets;
        if (activity == null) {
            return false;
        }
        try {
            View decorView = activity.getWindow().getDecorView();
            return (decorView == null || Build.VERSION.SDK_INT < 28 || (rootWindowInsets = decorView.getRootWindowInsets()) == null || rootWindowInsets.getDisplayCutout() == null) ? false : true;
        } catch (Exception unused) {
        }
    }

    public static boolean C() {
        return true;
    }

    public static boolean D() {
        return C;
    }

    public static boolean E() {
        return !B && wf.e0.u(XMRCApplication.d()) == 1;
    }

    public static boolean F() {
        return H;
    }

    public static boolean G() {
        return !TextUtils.isEmpty(J) && J.equalsIgnoreCase("mx_telcel");
    }

    public static void H() {
        f61134r = wf.c.b(XMRCApplication.d());
        N = wf.c.e(XMRCApplication.f17679d);
        f61139w = 6352;
        f61140x = y8.c.f67357f;
    }

    public static void I(Program program) {
        L = program;
    }

    public static void J(boolean z10) {
        H = z10;
        if (f61142z && z10) {
            G = true;
        }
    }

    public static boolean K() {
        return false;
    }

    public static void a() {
        M = t() ? new re.c() : new re.g();
        J(M.f());
        try {
            TelephonyManager telephonyManager = (TelephonyManager) XMRCApplication.d().getSystemService("phone");
            f61135s = telephonyManager.getNetworkCountryIso();
            f61136t = telephonyManager.getSimCountryIso();
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        H();
        String str = Build.DEVICE;
        String str2 = Build.PRODUCT;
        String str3 = Build.MODEL;
        x0.e();
    }

    public static int b() {
        return f61139w;
    }

    public static String c() {
        return f61140x;
    }

    public static Program d() {
        return L;
    }

    public static String e() {
        if (f61134r == null) {
            f61134r = "0";
        }
        return f61134r;
    }

    public static EpgManager f() {
        if (K == null) {
            synchronized (d.class) {
                if (K == null) {
                    K = new le.h(XMRCApplication.d());
                }
            }
        }
        return K;
    }

    public static re.g g() {
        return M;
    }

    public static final String h() {
        String strG = wf.d.g("ro.miui.product.home");
        return TextUtils.isEmpty(strG) ? "com.miui.home" : strG;
    }

    @TargetApi(21)
    public static String i() {
        String str;
        String str2 = f61138v;
        if (str2 != null) {
            return str2;
        }
        if (TextUtils.isEmpty(f61137u)) {
            String country = Locale.getDefault().getCountry();
            f61138v = country;
            if (!TextUtils.isEmpty(country)) {
                return f61138v;
            }
            String str3 = f61135s;
            if (str3 == null || str3.length() <= 0) {
                String str4 = f61136t;
                if (str4 == null || str4.length() <= 0) {
                    return f61138v;
                }
                str = f61136t;
            } else {
                str = f61135s;
            }
        } else {
            str = f61137u;
        }
        f61138v = str;
        return str;
    }

    public static String j() {
        if (TextUtils.isEmpty(N)) {
            N = wf.c.e(XMRCApplication.d());
        }
        return N;
    }

    public static boolean k() {
        return F;
    }

    public static boolean l() {
        return G;
    }

    public static boolean m() {
        return f61142z || A;
    }

    public static void n() {
        try {
            f61137u = wf.d.g("ro.miui.region");
            J = wf.d.g("ro.miui.customized.region");
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        try {
            String strG = wf.d.g("ro.miui.notch");
            if (strG != null && Integer.valueOf(strG).intValue() == 1) {
                I = true;
            }
        } catch (Exception unused) {
        }
        i();
        ij.c.o(f61138v);
        f61141y = f61138v.equalsIgnoreCase("zh") || f61138v.equalsIgnoreCase("hk") || f61138v.equalsIgnoreCase("tw") || f61138v.equalsIgnoreCase("mo") || f61138v.equalsIgnoreCase("cn");
        f61142z = f61138v.equalsIgnoreCase("zh") || f61138v.equalsIgnoreCase("cn");
        A = f61138v.equalsIgnoreCase("in");
        if (TextUtils.isEmpty(f61138v)) {
            B = true;
        } else {
            B = f61133q.contains(f61138v.toUpperCase());
            if ("RU".equals(f61138v.toUpperCase())) {
                C = true;
            } else if ("US".equals(f61138v.toUpperCase())) {
                D = true;
            }
        }
        E = Build.BRAND;
        if (f61142z) {
            F = true;
        }
        StringBuilder sbA = android.support.v4.media.e.a("GlobalData init :");
        sbA.append(f61138v);
        Log.e(f61117a, sbA.toString());
        HanziToPinyin.UNIHANS[0] = 38463;
    }

    public static void o() {
        E = Build.BRAND;
    }

    public static boolean p() {
        return "zh".equalsIgnoreCase(Locale.getDefault().getLanguage());
    }

    public static boolean q() {
        return "zh".equalsIgnoreCase(Locale.getDefault().getLanguage()) && "cn".equalsIgnoreCase(Locale.getDefault().getCountry());
    }

    public static boolean r() {
        return false;
    }

    public static boolean s() {
        return false;
    }

    public static boolean t() {
        String str = E;
        return str != null && str.toLowerCase().contains("huawei") && wf.d.i(XMRCApplication.d().getApplicationContext(), "com.uei.quicksetsdk.huawei");
    }

    public static boolean u() {
        return f61141y;
    }

    public static boolean v() {
        return f61142z;
    }

    public static boolean w() {
        return B;
    }

    public static boolean x() {
        return A;
    }

    public static boolean y() {
        return D;
    }

    public static boolean z() {
        return true;
    }
}
