package h7;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.milink.api.v1.MilinkClientManager;
import com.miui.appmanager.AppManageUtils;
import com.miui.earthquakewarning.Constants;
import com.miui.permcenter.compact.SystemPropertiesCompat;
import com.miui.securitycenter.Application;
import java.util.ArrayList;
import miui.security.SecurityManager;
import miui.telephony.SubscriptionInfo;
import miui.telephony.SubscriptionManager;
import miui.util.FeatureParser;

/* JADX INFO: loaded from: classes2.dex */
public class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ArrayList<String> f46851a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ArrayList<String> f46852b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Long f46853c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Long f46854d;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        f46851a = arrayList;
        ArrayList<String> arrayList2 = new ArrayList<>();
        f46852b = arrayList2;
        f46853c = 1559059200000L;
        f46854d = 1563764980000L;
        arrayList.add("cepheus");
        arrayList.add("grus");
        arrayList.add("pyxis");
        arrayList.add("raphael");
        arrayList.add("raphaelin");
        arrayList2.add("davinci");
        arrayList2.add("davinciin");
    }

    public static boolean A() {
        return f4.p1.a("debug.game.video.support", false);
    }

    public static boolean B(Context context, int i10) {
        return k(context) && C(context, i10);
    }

    private static boolean C(Context context, int i10) {
        try {
            ve.f.d((SecurityManager) context.getSystemService("security"), "getAllGameStorageApps", new Class[]{Integer.TYPE}, Integer.valueOf(i10));
            return true;
        } catch (Throwable unused) {
            Log.e("GameBoosterFeatureUtils", "reflect error when get app isPrivacy");
            return false;
        }
    }

    public static boolean D() {
        if (Y()) {
            return false;
        }
        return l() || (a0.b() != -1 && "qcom".equals(c("miui.util.FeatureParser", "vendor")) && dd.c0.a() >= 12);
    }

    public static boolean E() {
        return l() || b("miui.util.FeatureParser", "support_displayfeature_gamemode", false);
    }

    public static boolean F() {
        if (!"stable".equals(ce.c.d())) {
            return dd.c0.a() >= 12 && Build.VERSION.SDK_INT >= 28;
        }
        if (Z()) {
            return false;
        }
        return (dd.c0.a() >= 13 && Build.VERSION.SDK_INT >= 28) || l() || h0();
    }

    public static boolean G() {
        return (l() && Build.VERSION.SDK_INT <= 28) || b("miui.util.FeatureParser", "support_wifi_low_latency_mode", false);
    }

    public static boolean H() {
        if (!b("miui.util.FeatureParser", "support_network_rps_mode", false)) {
            if (Build.VERSION.SDK_INT < 28) {
                return false;
            }
            String str = miui.os.Build.DEVICE;
            if (!"cepheus".equals(str) && (!"grus".equals(str) || dd.c0.a() < 12)) {
                return false;
            }
        }
        return true;
    }

    public static boolean I() {
        return f4.p1.b("ro.vendor.audio.voice.change.version", 1) == 2;
    }

    public static boolean J() {
        return x(true);
    }

    public static boolean K() {
        return x(false);
    }

    public static boolean L() {
        return b("miui.util.FeatureParser", "support_paper_eyecare", false);
    }

    public static boolean M() {
        return (!miui.os.Build.IS_INTERNATIONAL_BUILD || K()) && Build.VERSION.SDK_INT > 23;
    }

    public static boolean N() {
        return f4.p1.a("ro.vendor.fps.switch.default", false);
    }

    public static boolean O() {
        return n() || R();
    }

    public static boolean P() {
        if (miui.os.Build.IS_INTERNATIONAL_BUILD) {
            return false;
        }
        try {
            return ((Boolean) ve.f.g(Class.forName("miui.os.DeviceFeature"), Boolean.TYPE, "hasMirihiSupport", null, new Object[0])).booleanValue();
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", "isSupportSlip!", e10);
            return false;
        }
    }

    public static boolean Q() {
        return l() || b("miui.util.FeatureParser", "support_touchfeature_gamemode", false);
    }

    public static boolean R() {
        return l() || b("miui.util.FeatureParser", "support_touchfeature_gamemode", false);
    }

    public static boolean S() {
        return (dd.c0.b(Constants.SECURITY_ADD_PACKAGE) >= 90321 && Build.VERSION.SDK_INT >= 26) || i0();
    }

    public static boolean T() {
        return b("miui.util.FeatureParser", "support_game_mi_time", false);
    }

    public static boolean U() {
        return w() || V();
    }

    public static boolean V() {
        return !miui.os.Build.IS_INTERNATIONAL_BUILD && v7.j.B() && h() && f4.p1.a("ro.vendor.audio.voice.change.youme.support", false);
    }

    public static boolean W() {
        return b("miui.util.FeatureParser", "remove_screen_off_hold_on", false);
    }

    public static boolean X() {
        try {
            return ((TelephonyManager) Application.y().getSystemService("phone")).isVoiceCapable();
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", "isVoiceCapable error", e10);
            return false;
        }
    }

    public static boolean Y() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("pine");
        arrayList.add("laurus");
        return arrayList.contains(miui.os.Build.DEVICE);
    }

    private static boolean Z() {
        String string = SystemPropertiesCompat.getString("ro.board.platform", "default");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("notSupportHangupQ8451: platform=");
        sb2.append(string);
        sb2.append("\tbuildTime=");
        long j10 = miui.os.Build.TIME;
        sb2.append(j10);
        sb2.append("\tApiLevel=");
        int i10 = Build.VERSION.SDK_INT;
        sb2.append(i10);
        Log.i("GameBoosterFeatureUtils", sb2.toString());
        return "sdm845".equals(string) && j10 < 1582711483000L && i10 == 29;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    private static Boolean a(Context context) throws Throwable {
        Cursor cursorQuery;
        ?? r10 = 0;
        try {
            if (Build.VERSION.SDK_INT < 26) {
                return null;
            }
            try {
                cursorQuery = context.getContentResolver().query(Uri.parse("content://com.xiaomi.aiasst.vision/supportSubtitleState"), new String[]{"value"}, null, null);
                if (cursorQuery != null) {
                    try {
                        boolean z10 = true;
                        if (cursorQuery.getColumnCount() >= 1) {
                            cursorQuery.moveToFirst();
                            if (1 != cursorQuery.getInt(0)) {
                                z10 = false;
                            }
                            Boolean boolValueOf = Boolean.valueOf(z10);
                            cursorQuery.close();
                            return boolValueOf;
                        }
                    } catch (Exception e10) {
                        e = e10;
                        Log.i("GameBoosterFeatureUtils", "error : " + e.getMessage());
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            } catch (Exception e11) {
                e = e11;
                cursorQuery = null;
            } catch (Throwable th2) {
                th = th2;
                if (r10 != 0) {
                    r10.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            r10 = context;
        }
    }

    public static void a0(boolean z10) {
        f4.p1.d("debug.game.video.speed", String.valueOf(z10));
    }

    public static boolean b(String str, String str2, boolean z10) {
        Class cls = Boolean.TYPE;
        try {
            return ((Boolean) ve.f.g(Class.forName(str), cls, "getBoolean", new Class[]{String.class, cls}, str2, Boolean.valueOf(z10))).booleanValue();
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", e10.toString());
            return false;
        }
    }

    public static void b0(boolean z10) {
        f4.p1.d("debug.game.video.boot", String.valueOf(z10));
    }

    public static String c(String str, String str2) {
        try {
            return (String) ve.f.g(Class.forName(str), String.class, "getString", new Class[]{String.class}, str2);
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", e10.toString());
            return null;
        }
    }

    public static boolean c0() {
        return ve.d.a.d("miui.telephony.TelephonyManager").c("isViceImsAutoRejectSupport", null, new Object[0]).a();
    }

    private static boolean d(Context context) {
        int i10 = -1;
        Cursor cursorQuery = null;
        try {
            try {
                try {
                    cursorQuery = context.getContentResolver().query(Uri.parse("content://com.miui.voiceassist.xiaoai.manager.provider/mode/voicetrigger"), null, null, null, null);
                    if (cursorQuery != null && cursorQuery.moveToFirst()) {
                        i10 = Integer.parseInt(cursorQuery.getString(cursorQuery.getColumnIndex("voicetrigger_mode")));
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Exception e10) {
                    Log.e("GameBoosterFeatureUtils", "getVoiceTriggerAvailable err: " + e10);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Throwable th2) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Exception e11) {
                        e11.printStackTrace();
                    }
                }
                throw th2;
            }
        } catch (Exception e12) {
            e12.printStackTrace();
        }
        Log.e("GameBoosterFeatureUtils", "voice trigger mode: " + i10);
        return i10 >= 0;
    }

    public static boolean d0() {
        return Build.VERSION.SDK_INT > 29;
    }

    private static boolean e() {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = Application.y().getPackageManager().getApplicationInfo(com.miui.common.c.f9495e, 128);
            return (applicationInfo == null || (bundle = applicationInfo.metaData) == null || bundle.getInt("miui_dsda") != 1) ? false : true;
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", "fail call isAppSupport : " + e10.getMessage());
        }
        return false;
    }

    public static boolean e0(Context context) {
        return dd.c0.b("com.miui.voiceassist") >= 304008000 && !miui.os.Build.IS_INTERNATIONAL_BUILD && d(context);
    }

    public static boolean f() {
        boolean zB = false;
        try {
            zB = b("miui.util.FeatureParser", "is_blackshark", false);
            Log.i("GameBoosterFeatureUtils", "isBS: " + zB);
            return zB;
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", "getBlackSharkFeature Failed", e10);
            return zB;
        }
    }

    public static boolean f0() {
        return i() && e();
    }

    public static boolean g() {
        return f4.s1.q() && b("miui.util.FeatureParser", "support_game_gunsight", false);
    }

    public static boolean g0() {
        return ve.d.a.d("miui.telephony.TelephonyManager").c("getDefault", null, new Object[0]).l().b("isGwsdSupport", null, new Object[0]).a();
    }

    public static boolean h() {
        String[] strArr = miui.os.Build.SUPPORTED_64_BIT_ABIS;
        if (strArr == null) {
            return false;
        }
        for (String str : strArr) {
            if (TextUtils.equals(str, "arm64-v8a")) {
                return true;
            }
        }
        return false;
    }

    public static boolean h0() {
        if (dd.c0.a() < 12 || Build.VERSION.SDK_INT < 28) {
            return false;
        }
        ArrayList<String> arrayList = f46851a;
        String str = miui.os.Build.DEVICE;
        if (arrayList.contains(str)) {
            return true;
        }
        boolean zContains = f46852b.contains(str);
        long j10 = miui.os.Build.TIME;
        if (zContains) {
            return j10 > f46853c.longValue();
        }
        return j10 > f46854d.longValue();
    }

    private static boolean i() {
        if (miui.os.Build.IS_INTERNATIONAL_BUILD) {
            return false;
        }
        try {
            return Resources.getSystem().getBoolean(((Integer) ve.f.m(Class.forName("android.miui.R$bool"), "config_is_dual_data_support")).intValue());
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean i0() {
        String str = miui.os.Build.DEVICE;
        return "cepheus".equals(str) || (dd.c0.a() >= 12 && Build.VERSION.SDK_INT >= 28 && "grus".equals(str));
    }

    public static boolean j() {
        try {
            return ((Boolean) ve.f.d(ve.f.h(Class.forName("miui.telephony.TelephonyManagerEx"), "getDefault", null, new Object[0]), "isEnhancedDsdaSupported", null, new Object[0])).booleanValue();
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", "fail to call isEnhancedDsdaSupport : " + e10.getMessage());
            return false;
        }
    }

    public static boolean j0() {
        SubscriptionInfo subscriptionInfoForSlot = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(0);
        SubscriptionInfo subscriptionInfoForSlot2 = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(1);
        return y.a() && subscriptionInfoForSlot != null && subscriptionInfoForSlot2 != null && subscriptionInfoForSlot.isActivated() && subscriptionInfoForSlot2.isActivated();
    }

    private static boolean k(Context context) {
        if (AppManageUtils.Z(context)) {
            try {
                Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication("com.miui.home");
                if (resourcesForApplication != null) {
                    return resourcesForApplication.getBoolean(resourcesForApplication.getIdentifier("support_game_app_arrange_function", "bool", "com.miui.home"));
                }
            } catch (Throwable th2) {
                Log.e("GameBoosterFeatureUtils", "home support gamestorage fail", th2);
            }
        }
        return false;
    }

    public static boolean l() {
        return Build.VERSION.SDK_INT >= 26 && "cepheus".equals(miui.os.Build.DEVICE);
    }

    public static boolean m(Context context) throws Throwable {
        Boolean boolA = a(context);
        Log.d("GameBoosterFeatureUtils", "checkAISubtitle Result = " + boolA);
        if (boolA != null) {
            return boolA.booleanValue();
        }
        return !miui.os.Build.IS_INTERNATIONAL_BUILD && f4.a1.r(context, "com.xiaomi.aiasst.vision") >= 413;
    }

    public static boolean n() {
        return l() || b("android.os.SystemProperties", "ro.vendor.audio.game.effect", false);
    }

    public static boolean o() {
        return b("android.os.SystemProperties", "ro.vendor.audio.game.mode", false);
    }

    public static boolean p() {
        try {
            return b("miui.util.FeatureParser", "support_game_dim", false);
        } catch (Exception e10) {
            Log.e("GameBoosterFeatureUtils", "isSupportAuthBrightnessChange Failed", e10);
            return false;
        }
    }

    public static boolean q() {
        return b("miui.util.FeatureParser", "support_autobrightness_optimize", false);
    }

    public static boolean r() {
        return E() || Q();
    }

    public static boolean s() {
        return l() || (Build.VERSION.SDK_INT > 23 && dd.c0.a() >= 12);
    }

    public static boolean t() {
        return !miui.os.Build.IS_INTERNATIONAL_BUILD && FeatureParser.getBoolean("support_disable_ndds_sim", false);
    }

    public static boolean u() {
        return b("miui.util.FeatureParser", "remove_vgpaper", false);
    }

    public static boolean v() {
        return b("miui.util.FeatureParser", "support_video_dfps", false);
    }

    public static boolean w() {
        return f4.p1.a("ro.vendor.audio.voice.change.support", false);
    }

    public static boolean x(boolean z10) {
        if (miui.os.Build.IS_INTERNATIONAL_BUILD && !l() && dd.c0.a() < 12) {
            return false;
        }
        if (z10 && e2.v()) {
            return false;
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 28) {
            return true;
        }
        return i10 >= 26 && dd.c0.a() >= 12;
    }

    public static boolean y() {
        try {
            MilinkClientManager.class.getDeclaredMethod("disconnectWifiDisplay", new Class[0]);
            return f4.s1.q();
        } catch (NoSuchMethodException unused) {
            return false;
        }
    }

    public static boolean z() {
        return b("miui.util.FeatureParser", "support_mi_game_macro", false);
    }
}
