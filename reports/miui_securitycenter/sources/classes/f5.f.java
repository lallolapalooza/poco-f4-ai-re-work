package f5;

import android.content.Intent;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.miui.analytics.StatManager;
import com.miui.securitycenter.Application;
import dd.z;
import f4.p1;
import f4.t;
import h7.k0;
import h7.o1;
import h7.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miui.os.Build;
import miui.util.FeatureParser;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f45853g = q();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static List<String> f45854h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final Map<String, a> f45855i = new ArrayMap();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static f f45856j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f45857a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<String> f45858b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f45859c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f45860d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f45861e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f45862f;

    static {
        List<String> listA = p.a();
        if (!k6.c.p(listA)) {
            f45854h.addAll(listA);
        }
        z.c().b(new Runnable() { // from class: f5.d
            @Override // java.lang.Runnable
            public final void run() {
                f.i0();
            }
        });
    }

    private f() {
        x();
    }

    public static void A0(boolean z10) {
        k0.f("pref_dialog_privacy", z10);
    }

    public static void B0() {
        k0.f("pref_privacy_func", true);
    }

    public static void C0(String str) {
        z3.a.r("pref_privacy_single_list", str);
    }

    public static void D0(String str) {
        z3.a.r("pref_privacy_size", str);
    }

    public static boolean E() {
        return z3.a.e("sp_auto_change_brightness", false);
    }

    public static void E0() {
        z.c().b(new Runnable() { // from class: f5.e
            @Override // java.lang.Runnable
            public final void run() {
                f.h0();
            }
        });
    }

    public static boolean F() {
        return (TextUtils.equals("taoyao", Build.DEVICE) || TextUtils.equals(Build.getRegion(), "IN") || !p1.a("persist.vendor.vcb.ability", false)) ? false : true;
    }

    public static void F0(boolean z10, int i10) {
        z3.a.n("pref_screen_light", z10);
        z3.a.p("pref_screen_light_value", i10);
    }

    public static boolean G() {
        return p1.a("persist.vendor.vcb.enable", false);
    }

    public static boolean H() {
        return z3.a.e("sp_beauty_function", true);
    }

    public static void H0(String str) {
        z3.a.r("pref_ultraclear_app", str);
    }

    public static void J0(boolean z10) {
        p1.d("persist.vendor.vcb.enable", z10 ? "true" : "false");
    }

    public static boolean K() {
        return p1.b("persist.vendor.vcf.colorsupport", 0) == 1;
    }

    private boolean L() {
        if (this.f45858b.isEmpty()) {
            this.f45858b.addAll(t());
        }
        return (!this.f45858b.isEmpty() ? this.f45858b : this.f45857a).contains(Build.DEVICE);
    }

    public static boolean M() {
        boolean z10 = T() || (!Build.IS_INTERNATIONAL_BUILD && f45854h.contains(Build.DEVICE) && o().L());
        Q0(z10);
        return z10;
    }

    public static void M0(boolean z10, int i10, int i11, boolean z11) {
        p1.d("persist.vendor.vcf.enable", String.valueOf(((z11 ? 1 : 0) << 16) + (i11 << 12) + ((i10 + 1) << 1) + (z10 ? 1 : 0)));
    }

    public static boolean N() {
        return k0.a("pref_face_func", false);
    }

    public static void N0(boolean z10) {
        Log.i("BeautyUtils", "switchPortraitCenter: " + z10);
        p1.d("persist.vendor.camera.facetracker.enable", z10 ? "1" : StatManager.PARAMS_SWITCH_OFF);
    }

    private static boolean P() {
        int iB = p1.b("persist.vendor.vcf.enable", -1);
        return iB != -1 && (iB & 1) == 1;
    }

    public static void Q0(boolean z10) {
        p1.d("persist.sys.privacy_camera", z10 ? "true" : "false");
    }

    public static boolean R() {
        return k0.a("pref_light_func", false);
    }

    public static boolean S() {
        return z3.a.e("pref_light_pre_status", false);
    }

    private static boolean T() {
        return TextUtils.equals("mars", Build.DEVICE) && w9.c.i();
    }

    public static boolean U() {
        return z3.a.e("key_portrait_center_status", false);
    }

    public static boolean V() {
        return k0.a("pref_pc_func", false);
    }

    public static boolean W() {
        return p1.b("persist.vendor.camera.facetracker.support", 0) == 1;
    }

    public static boolean X(a aVar) {
        if (aVar == null || o().Q(aVar)) {
            return z3.a.e("pref_privacy_global_status", false);
        }
        ArrayList<String> arrayListM = z3.a.m("pref_privacy_single_status", new ArrayList());
        if (k6.c.p(arrayListM)) {
            return false;
        }
        try {
            for (String str : arrayListM) {
                if (str.contains(aVar.b())) {
                    return Integer.parseInt(str.split("#")[1]) == 1;
                }
            }
        } catch (Exception e10) {
            Log.e("BeautyUtils", "isPrivacyCameraOpen error", e10);
        }
        return false;
    }

    public static boolean Y() {
        return M();
    }

    public static boolean Z() {
        return k0.a("pref_dialog_privacy", false);
    }

    public static boolean a0() {
        return k0.a("pref_privacy_func", false);
    }

    private static boolean c0() {
        return z3.a.e("pref_screen_light", false);
    }

    private boolean e0() {
        return z3.a.e("pref_support_front_light", false);
    }

    private static String f(String str, String str2) {
        return str + "/" + str2;
    }

    public static boolean f0() {
        if (n() >= 0 && t.i() >= 10) {
            CameraManager cameraManager = (CameraManager) Application.y().getSystemService("camera");
            try {
                for (String str : cameraManager.getCameraIdList()) {
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                    Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    if (bool != null && bool.booleanValue() && num != null && num.intValue() == 0) {
                        return true;
                    }
                }
            } catch (Throwable th2) {
                Log.e("BeautyUtils", "isBeautyLightEnable error", th2);
            }
        }
        return false;
    }

    public static int h() {
        f fVarO = o();
        if ((F() && fVarO.A() && !N()) || ((fVarO.O() && fVarO.B() && !R()) || ((Y() && fVarO.D() && !a0()) || (W() && fVarO.C() && !V())))) {
            return 1;
        }
        return k0.b("gb_game_beauty_type", 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h0() {
        p1.d("persist.sys.privacy_camera_radius_ratio", u());
    }

    public static int i() {
        if (o().O()) {
            return f45853g;
        }
        return 255;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void i0() {
        y();
        l0.a.b(Application.y()).d(new Intent("beauty_action_monitor_activity"));
    }

    public static void j0() {
        f45856j = null;
    }

    public static void k0(String str) {
        z3.a.r("pref_aisubtitle_app", str);
    }

    public static synchronized a l(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return f45855i.get(f(str, str2));
        }
        return null;
    }

    public static void l0(boolean z10) {
        z3.a.n("sp_auto_change_brightness", z10);
    }

    public static void m0(int i10) {
        k0.g("gb_game_beauty_type", i10);
    }

    private static int n() {
        int iB = p1.b("persist.vendor.vcf.enable", -1);
        return iB > 0 ? ((iB & 4095) >> 1) - 1 : iB;
    }

    public static synchronized f o() {
        if (f45856j == null) {
            f45856j = new f();
        }
        return f45856j;
    }

    public static void o0(String str) {
        z3.a.r("pref_face_support_apps", str);
    }

    public static int p() {
        int iB = p1.b("persist.vendor.vcf.enable", -1);
        return iB > 0 ? (iB & 61440) >> 12 : iB;
    }

    public static void p0() {
        k0.f("pref_face_func", true);
    }

    public static int q() {
        int integer = FeatureParser.getInteger("softlight_current_max_smartmax", -1);
        if (integer == -1) {
            return h7.n.d() ? 299 : 100;
        }
        return integer >> 16;
    }

    public static void q0(String str) {
        z3.a.r("pref_privacy_global_list", str);
    }

    public static synchronized List<String> r(boolean z10) {
        Map<String, a> map = f45855i;
        if (k6.c.q(map)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            a aVar = f45855i.get(it.next());
            if (aVar != null) {
                arrayList.add(z10 ? aVar.f45822a : aVar.f45823b);
            }
        }
        return arrayList;
    }

    public static void r0(String str) {
        z3.a.r("pref_light_support_apps", str);
    }

    public static void s0() {
        k0.f("pref_light_func", true);
    }

    public static List<String> t() {
        String strL = z3.a.l("pref_privacy_support_devices", "");
        if (TextUtils.isEmpty(strL)) {
            return Collections.emptyList();
        }
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(strL);
            if (jSONArray.length() > 0) {
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    arrayList.add(jSONArray.optString(i10));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    public static void t0(boolean z10) {
        z3.a.n("pref_light_pre_status", z10);
    }

    private static String u() {
        return z3.a.l("pref_privacy_size", "1.7");
    }

    public static void u0() {
        k0.f("pref_pc_func", true);
    }

    private static int v() {
        return z3.a.h("pref_screen_light_value", 255);
    }

    public static void v0(boolean z10) {
        z3.a.n("key_portrait_center_status", z10);
    }

    public static int w() {
        int integer = FeatureParser.getInteger("softlight_current_max_smartmax", -1);
        if (integer == -1) {
            return 75;
        }
        return integer & MenuBuilder.USER_MASK;
    }

    public static void w0(String str) {
        z3.a.r("pref_pickup_app", str);
    }

    private void x() {
        this.f45862f = e0();
        this.f45857a.add("zeus");
        this.f45857a.add("cupid");
        this.f45857a.add("venus");
        this.f45857a.add("star");
        this.f45857a.add("mars");
        this.f45857a.add("haydn");
        this.f45857a.add("odin");
        this.f45857a.add("renoir");
        this.f45857a.add("cetus");
    }

    public static void x0(String str) {
        z3.a.r("pref_portrait_center_app", str);
    }

    public static synchronized void y() {
        f45855i.clear();
        z("pref_face_support_apps", "beauty_face_support_activity_list.json", a.EnumC0422a.FACE);
        if (!g5.i.a0()) {
            z("pref_light_support_apps", "beauty_light_default_support_list.json", a.EnumC0422a.LIGHT);
        }
        z("pref_privacy_single_list", "privacy_single_support_activity_list.json", a.EnumC0422a.PRIVACY_SINGLE);
        z("pref_privacy_global_list", "privacy_all_support_activity_list.json", a.EnumC0422a.PRIVACY_GLOBAL);
        z("pref_portrait_center_app", "protrait_support_activity_list.json", a.EnumC0422a.PORTRAIT_CENTER);
        z("pref_pickup_app", "pickup_support_activity_list.json", a.EnumC0422a.PICKUP);
        z("pref_ultraclear_app", "ultraclear_support_activity_list.json", a.EnumC0422a.ULTRACLEAR);
        z("pref_aisubtitle_app", "aisubtitle_support_activity_list.json", a.EnumC0422a.AISUBTITLE);
        z("pref_split_scene", "support_split_scene_activities.json", a.EnumC0422a.SPLIT_SCENE);
        o().n0(o().f45859c, o().f45860d);
    }

    public static void y0(String str) {
        if (W()) {
            a value = null;
            for (Map.Entry<String, a> entry : f45855i.entrySet()) {
                if (TextUtils.equals(str, entry.getValue().f45822a)) {
                    value = entry.getValue();
                    break;
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("setPortraitCenterEnable: ");
            sb2.append(value == null);
            Log.i("BeautyUtils", sb2.toString());
            String str2 = StatManager.PARAMS_SWITCH_OFF;
            if (value == null) {
                p1.d("persist.vendor.camera.facetracker.active", StatManager.PARAMS_SWITCH_OFF);
                p1.d("persist.vendor.camera.facetracker.rrzosize", StatManager.PARAMS_SWITCH_OFF);
            } else {
                if (value.k()) {
                    str2 = "1";
                }
                p1.d("persist.vendor.camera.facetracker.active", str2);
                p1.d("persist.vendor.camera.facetracker.rrzosize", value.a());
            }
        }
    }

    private static void z(String str, String str2, a.EnumC0422a enumC0422a) throws Throwable {
        String strL = z3.a.l(str, "");
        if (TextUtils.isEmpty(strL)) {
            strL = h7.e.a(Application.y().getApplicationContext(), str2);
        }
        try {
            JSONArray jSONArray = new JSONArray(strL);
            if (jSONArray.length() > 0) {
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i10);
                    String strOptString = jSONObjectOptJSONObject.optString("pkgName");
                    String strOptString2 = jSONObjectOptJSONObject.optString("activityName");
                    String strOptString3 = jSONObjectOptJSONObject.optString("size");
                    String strOptString4 = jSONObjectOptJSONObject.optString("size_small");
                    a aVar = new a(strOptString, strOptString2, strOptString3, strOptString4, enumC0422a);
                    String strF = f(strOptString, strOptString2);
                    Map<String, a> map = f45855i;
                    if (map.containsKey(strF)) {
                        a aVarL = l(aVar.f45822a, aVar.f45823b);
                        if (aVarL != null) {
                            aVarL.f45824c = strOptString3;
                            aVarL.f45825d = strOptString4;
                            aVarL.f45826e.add(enumC0422a);
                        }
                    } else {
                        map.put(strF, aVar);
                    }
                }
            }
        } catch (Exception e10) {
            Log.e("BeautyUtils", "initTargetApps fail " + e10);
        }
    }

    public static void z0(String str) {
        z3.a.r("pref_privacy_support_devices", str);
    }

    public boolean A() {
        a aVarK = k();
        return aVarK != null && aVarK.f();
    }

    public boolean B() {
        a aVarK = k();
        return aVarK != null && aVarK.h();
    }

    public boolean C() {
        a aVarK = k();
        return aVarK != null && aVarK.k();
    }

    public boolean D() {
        a aVarK = k();
        return aVarK != null && aVarK.l();
    }

    public void G0(boolean z10) {
        this.f45862f = z10;
        z3.a.n("pref_support_front_light", z10);
    }

    public boolean I() {
        return true;
    }

    public boolean I0(String str, String str2) {
        a aVarL = l(str, str2);
        return aVarL != null && aVarL.c();
    }

    public boolean J() {
        return this.f45862f ? P() : c0();
    }

    public void K0(boolean z10) {
        z3.a.n("sp_beauty_function", z10);
        if (z10) {
            return;
        }
        L0(false, g(), p());
        if (Y()) {
            P0(false, null);
            O0(false);
        }
        if (W()) {
            N0(false);
        }
    }

    public void L0(boolean z10, int i10, int i11) {
        if (this.f45862f) {
            M0(z10, i10, i11, B());
        } else {
            F0(z10, i10);
        }
    }

    public boolean O() {
        return this.f45862f;
    }

    public void O0(boolean z10) {
        p1.d("persist.sys.privacy_camera_switch", z10 ? "true" : "false");
    }

    public void P0(boolean z10, a aVar) {
        if (aVar == null || Q(aVar)) {
            z3.a.n("pref_privacy_global_status", z10);
            return;
        }
        ArrayList<String> arrayListM = z3.a.m("pref_privacy_single_status", new ArrayList());
        if (k6.c.p(arrayListM)) {
            arrayListM = new ArrayList<>();
        }
        for (int size = arrayListM.size() - 1; size >= 0; size--) {
            String str = arrayListM.get(size);
            if (!TextUtils.isEmpty(str) && str.contains(aVar.b())) {
                arrayListM.remove(size);
                break;
            }
        }
        arrayListM.add(aVar.b() + "#" + (z10 ? 1 : 0));
        z3.a.s("pref_privacy_single_status", arrayListM);
    }

    public boolean Q(a aVar) {
        return aVar != null && aVar.m();
    }

    public boolean b0(a aVar) {
        return aVar != null && aVar.n();
    }

    public boolean c(boolean z10, String str, String str2) {
        return d(z10, str, str2, false);
    }

    public boolean d(boolean z10, String str, String str2, boolean z11) {
        a aVarL = l(str, str2);
        if (!g5.i.a0()) {
            return z10 && aVarL != null && aVarL.g();
        }
        boolean zA = o1.a();
        Log.i("BeautyUtils", "splitMode: on" + zA);
        if (z11) {
            return (aVarL == null || !aVarL.e() || w.h(Application.y())) ? false : true;
        }
        return (aVarL == null || !aVarL.e() || zA || w.h(Application.y())) ? false : true;
    }

    public boolean d0() {
        return F() || e0() || Y() || W();
    }

    public void e() {
        L0(false, g(), p());
        a aVarK = k();
        if (Y()) {
            if (b0(aVarK)) {
                P0(false, aVarK);
            }
            O0(false);
        }
        if (W()) {
            N0(false);
        }
    }

    public int g() {
        return O() ? n() : v();
    }

    public boolean g0() {
        a aVarK = k();
        return aVarK != null && aVarK.i();
    }

    public String j() {
        return this.f45860d;
    }

    public synchronized a k() {
        return this.f45861e;
    }

    public String m() {
        return this.f45859c;
    }

    public void n0(String str, String str2) {
        this.f45859c = str;
        this.f45860d = str2;
        this.f45861e = l(str, str2);
        Log.i("BeautyUtils", "setCurrentInfo: pkg " + str + " cls " + str2 + " info " + this.f45861e);
    }

    public float s() {
        float fB;
        if (h7.n.c()) {
            float fA = q.a(Application.y());
            fB = fA + (0.1f * fA);
        } else {
            fB = (O() ? p1.b("ro.vendor.front_flash.value", 255) : v()) / 255.0f;
        }
        return Math.min(fB, 1.0f);
    }
}
