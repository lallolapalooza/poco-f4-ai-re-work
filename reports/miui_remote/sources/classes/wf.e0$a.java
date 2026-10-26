package wf;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;
import vd.x0;

/* JADX INFO: loaded from: classes3.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f64377a = "RCSettings";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f64378b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f64379c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f64380d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f64381e = "settings";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f64382f = "key_selected_stb_id";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f64383g = "stb_append_ok_key";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f64384h = "brandlist_cache_version";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f64385i = "show_panel_in_lockscreen";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f64386j = "key_last_use_dev_id";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f64387k = "key_supported_vendors_from_server";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f64388l = "key_supported_long_vibrate_devices";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f64389m = "key_peel_uninstalled_show_switch";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f64390n = "key_updated_new_version";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final String f64391o = "key_updated_item_version";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f64392p = "key_updated_item_version_time";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f64393q = "key_updated_item_user_center_version";

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f64394r = "tv_dpad_mode";

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String f64395s = "wifi_rc_dpad_mode";

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f64396t = "preference_key_privacy_new_1";

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final String f64397u = "share_device_count";

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final String f64398v = "permiss_show_time";

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final String f64399w = "KEY_PRIVACY_CURRENT_VERSION";

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final String f64400x = "key_local_privacy_record";

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final String f64401y = "show_request_permission";

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final String f64402z = "has_shown_new_ir_tip";

    public enum a {
        VIBRATOR(true, "preference_key_enable_vibrator"),
        VOLUME(true, "preference_key_enable_volume"),
        IME(true, "preference_key_enable_ime"),
        TOUCHPAD(true, "preference_key_enable_touchpad"),
        PROJECT(false, "preference_key_enable_project"),
        TITITLEBAR(false, "preference_key_enable_titlebar"),
        TVSCREENSHOT(false, "preference_key_enable_tvscreenshot"),
        KEYGUARDRC(true, "preference_key_enable_keyGuardRC"),
        GAMEPAD(false, "preference_key_enable_gamepad"),
        MOUSE(false, "preference_key_enable_mouse"),
        ACCEPT_SHARE_RC(true, "preference_key_accept_share_rc"),
        SHORTCUT(false, "preference_key_shortcut_on"),
        VOICECONTROL(false, "preference_key_enable_voice_control"),
        NONE(false, "none");


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f64418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f64419b;

        a(boolean z10, String str) {
            this.f64418a = z10;
            this.f64419b = str;
        }

        public String a() {
            return this.f64419b;
        }

        public boolean b() {
            return this.f64418a;
        }
    }

    public static boolean A(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f64381e, 0);
        a aVar = a.VIBRATOR;
        sharedPreferences.contains(aVar.f64419b);
        return sharedPreferences.getBoolean(aVar.f64419b, aVar.f64418a);
    }

    public static boolean B(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f64381e, 0);
        a aVar = a.VOICECONTROL;
        sharedPreferences.contains(aVar.f64419b);
        return sharedPreferences.getBoolean(aVar.f64419b, aVar.f64418a);
    }

    public static boolean C(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f64381e, 0);
        a aVar = a.VOLUME;
        sharedPreferences.contains(aVar.f64419b);
        return sharedPreferences.getBoolean(aVar.f64419b, aVar.f64418a);
    }

    public static void D(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putString(f64399w, str);
        editorEdit.commit();
    }

    public static void E(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt("auto_tune_interval", i10);
        editorEdit.apply();
    }

    public static void F(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean("auto_tune_start_from_first", z10);
        editorEdit.apply();
    }

    public static void G(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64384h, i10);
        editorEdit.apply();
    }

    public static void H(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean("use_server_ir_only", z10);
        editorEdit.apply();
    }

    public static void I(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64386j, i10);
        editorEdit.apply();
    }

    public static void J(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putString(f64400x, str);
        editorEdit.commit();
    }

    public static void K(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(a.PROJECT.f64419b, z10);
        editorEdit.apply();
    }

    public static void L(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(f64389m, z10);
        editorEdit.apply();
    }

    public static void M(Context context) {
        context.getSharedPreferences(f64381e, 0).edit().putLong(f64398v, System.currentTimeMillis()).apply();
    }

    public static void N(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64396t, i10);
        editorEdit.commit();
    }

    public static void O(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64382f, i10);
        editorEdit.apply();
    }

    public static void P(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putString(f64387k, str);
        editorEdit.apply();
    }

    public static void Q(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64397u, i10);
        editorEdit.apply();
    }

    public static void R(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(a.SHORTCUT.f64419b, z10);
        editorEdit.apply();
    }

    public static void S(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(f64401y, true);
        editorEdit.apply();
    }

    public static void T() {
        x0.a().getSharedPreferences(f64381e, 0).edit().putBoolean(f64402z, true).apply();
    }

    public static void U(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(f64385i, z10);
        editorEdit.apply();
    }

    public static void V(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(f64383g, z10);
        editorEdit.apply();
    }

    public static void W(Context context, Set<String> set) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putStringSet(f64388l, set);
        editorEdit.apply();
    }

    public static void X(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64394r, i10);
        editorEdit.apply();
    }

    public static void Y(Context context, long j10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putLong(f64392p, j10);
        editorEdit.apply();
    }

    public static void Z(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64391o, i10);
        editorEdit.apply();
    }

    public static void a(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.remove(f64400x);
        editorEdit.commit();
    }

    public static void a0(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64390n, i10);
        editorEdit.apply();
    }

    public static String b(Context context) {
        return context.getSharedPreferences(f64381e, 0).getString(f64399w, "");
    }

    public static void b0(Context context, int i10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putInt(f64393q, i10);
        editorEdit.apply();
    }

    public static int c(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt("auto_tune_interval", 4);
    }

    public static void c0(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(a.VIBRATOR.f64419b, z10);
        editorEdit.apply();
        m0.b.f64481a.d(z10);
    }

    public static boolean d(Context context) {
        return context.getSharedPreferences(f64381e, 0).getBoolean("auto_tune_start_from_first", true);
    }

    public static void d0(Context context, boolean z10) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f64381e, 0).edit();
        editorEdit.putBoolean(a.VOICECONTROL.f64419b, z10);
        editorEdit.apply();
    }

    public static int e(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt(f64384h, 0);
    }

    public static boolean f(Context context) {
        return context.getSharedPreferences(f64381e, 0).getBoolean("use_server_ir_only", false);
    }

    public static String g(Context context) {
        return context.getSharedPreferences(f64381e, 0).getString(f64400x, "");
    }

    public static long h(Context context) {
        return context.getSharedPreferences(f64381e, 0).getLong(f64398v, 0L);
    }

    public static int i(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt(f64382f, -1);
    }

    public static String j(Context context) {
        return context.getSharedPreferences(f64381e, 0).getString(f64387k, "&peel;");
    }

    public static int k(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt(f64397u, 0);
    }

    public static boolean l(Context context) {
        return context.getSharedPreferences(f64381e, 0).getBoolean(f64401y, false);
    }

    public static boolean m() {
        return x0.a().getSharedPreferences(f64381e, 0).getBoolean(f64402z, false);
    }

    public static Set<String> n(Context context) {
        return context.getSharedPreferences(f64381e, 0).getStringSet(f64388l, null);
    }

    public static int o(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt(f64394r, 0);
    }

    public static long p(Context context) {
        return context.getSharedPreferences(f64381e, 0).getLong(f64392p, -1L);
    }

    public static int q(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt(f64391o, -1);
    }

    public static int r(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt(f64393q, -1);
    }

    public static boolean s(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f64381e, 0);
        a aVar = a.ACCEPT_SHARE_RC;
        sharedPreferences.contains(aVar.f64419b);
        return sharedPreferences.getBoolean(aVar.f64419b, aVar.f64418a);
    }

    public static boolean t(Context context) {
        return context.getSharedPreferences(f64381e, 0).getBoolean(f64389m, true);
    }

    public static int u(Context context) {
        return context.getSharedPreferences(f64381e, 0).getInt(f64396t, 0);
    }

    public static boolean v(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f64381e, 0);
        a aVar = a.PROJECT;
        sharedPreferences.contains(aVar.f64419b);
        return sharedPreferences.getBoolean(aVar.f64419b, aVar.f64418a);
    }

    public static boolean w(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f64381e, 0);
        a aVar = a.SHORTCUT;
        sharedPreferences.contains(aVar.f64419b);
        return sharedPreferences.getBoolean(aVar.f64419b, false);
    }

    public static boolean x(Context context) {
        return context.getSharedPreferences(f64381e, 0).getBoolean(f64385i, false);
    }

    public static boolean y(Context context) {
        return context.getSharedPreferences(f64381e, 0).getBoolean(f64383g, true);
    }

    public static boolean z(Context context, int i10) {
        return i10 <= context.getSharedPreferences(f64381e, 0).getInt(f64390n, -1);
    }
}
