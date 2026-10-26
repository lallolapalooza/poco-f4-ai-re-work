package p7;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.GmsVersion;
import com.miui.bubbles.settings.BubblesSettings;
import com.miui.warningcenter.disasterwarning.DisasterConstants;
import dd.z;
import f4.o;
import f4.s1;
import f5.f;
import g5.i;
import h7.c0;
import h7.d2;
import h7.m0;
import h7.u;
import h7.v0;
import h7.w;
import java.util.ArrayList;
import miui.os.Build;
import org.json.JSONArray;
import q7.e;
import q7.m;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static void A(Context context) {
        Log.i("CloudControlHelper", "loadCloudUltraclearActivity");
        try {
            String strD = d(context, "GbVtbAppListSettings", "ultraclear_activities", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudUltraclearActivity: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.H0(strD);
            V(context, new Intent("beauty_action_monitor_activity"));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudUltraclearActivity failed : " + e10);
        }
    }

    public static void B(Context context) {
        Log.i("CloudControlHelper", "loadCloudUnSupportSpeakerDenoise");
        try {
            String strD = d(context, "GbVtbAppListSettings", "speaker_denoise", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudUnSupportSpeakerDenoise: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            i.x0(strD);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudUnSupportSpeakerDenoise failed : " + e10);
        }
    }

    public static void C(Context context) throws Throwable {
        Log.i("CloudControlHelper", "loadCloudVideoApps");
        try {
            ArrayList<String> arrayListU = U(d(context, "GbVtbAppListSettings", "VideoApps", ""));
            Log.i("CloudControlHelper", "loadCloudVideoApps: " + arrayListU);
            if (arrayListU.isEmpty()) {
                return;
            }
            u.i("gamebooster", "vtb_net_support_apps", arrayListU, context);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudVideoApps failed : " + e10.toString());
        }
    }

    public static void D(Context context) {
        try {
            z3.a.s("key_device_not_allow_ignore_click", U(d(context, "gamebooster", "key_device_not_allow_ignore_click", "")));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadDeviceNotSupportIgnoreClickList : " + e10);
        }
    }

    public static void E(Context context) {
        Log.i("CloudControlHelper", "loadCleanerSettings");
        try {
            long jC = c(context, "CleanerSettings", "fboAppMinSize", GmsVersion.VERSION_LONGHORN);
            m9.f.g(jC);
            int iC = c(context, "CleanerSettings", "fboStateOpen", 1);
            m9.f.j(iC);
            String strD = d(context, "CleanerSettings", "fboRules", "");
            m9.f.i(strD);
            Log.i("CloudControlHelper", "stateOpen : " + iC + "  ,appMinSize : " + jC + "  ,rules : " + strD);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCleanerSettings failed : " + e10.toString());
        }
    }

    public static void F(Context context) {
        try {
            v0.k(U(d(context, "gamebooster", "key_fix_cpu_setting_game_list", "")));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadGPUSettingProblemFixGameList fail : " + e10.getMessage());
        }
    }

    public static void G(Context context) {
        try {
            v0.l(U(d(context, "gamebooster", "key_game_booster_start_use_lottie", "")));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadGameBoosterStartUseLottieDevices fail : " + e10.getMessage());
        }
    }

    public static void H(Context context) {
        if (e6.c.f()) {
            try {
                String strD = d(context, "gamebooster", "GamesizeRatio", "");
                Log.i("CloudControlHelper", "loadGameSizeRecommend: " + strD);
                ArrayList<String> arrayListU = U(strD);
                if (k6.c.p(arrayListU)) {
                    return;
                }
                e6.c.r(arrayListU);
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
    }

    private static void I(final Context context) {
        try {
            Log.i("CloudControlHelper", "loadGtAutoAddGameConfiguration");
            if (!Build.IS_INTERNATIONAL_BUILD && r5.b.b(context) && t5.a.e(context).x() && s1.q()) {
                final String strD = d(context, "GtConfigration", "AutoAddGame", "");
                z.c().b(new Runnable() { // from class: p7.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        com.miui.gamebooster.utils.b.a(context, strD);
                    }
                });
                return;
            }
            Log.i("CloudControlHelper", "loadGtAutoAddGameConfiguration: gt close or not support");
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadGtAutoAddGameConfiguration failed : " + e10.toString());
        }
    }

    private static void J(Context context) {
        try {
            Log.i("CloudControlHelper", "loadGtPerformanceBubbleConfiguration");
            if (Build.IS_INTERNATIONAL_BUILD) {
                Log.i("CloudControlHelper", "loadGtPerformanceBubbleConfiguration: only support in cn");
                return;
            }
            String strD = d(context, "GtConfigration", "PerformanceBubble", "");
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            p6.f.g(strD);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadGtAutoAddGameConfiguration failed : " + e10.toString());
        }
    }

    public static void K(Context context) {
        try {
            if (c0.g()) {
                String strD = d(context, "gamebooster", "gunsight", "");
                ArrayList<String> arrayListU = U(strD);
                if (!arrayListU.isEmpty()) {
                    v0.m(arrayListU);
                }
                if (td.a.f54381a) {
                    Log.i("CloudControlHelper", "loadGunSight: " + strD);
                }
            }
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadGunSight failed : " + e10.toString());
        }
    }

    public static void L(Context context) {
        try {
            if (c0.z()) {
                String strD = d(context, "gamebooster", "UnSupportMacro", "");
                ArrayList<String> arrayListU = U(strD);
                if (!arrayListU.isEmpty()) {
                    v0.n(arrayListU);
                }
                if (td.a.f54381a) {
                    Log.i("CloudControlHelper", "loadMacroSettings: " + strD);
                }
            }
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadMacroSettings failed : " + e10.toString());
        }
    }

    private static void M(Context context) {
        if (gj.a.f46691a) {
            return;
        }
        try {
            if (BubblesSettings.isBubbleNotificationSupport(context)) {
                String strD = d(context, "MiuiBubblesSettings", "MiuiBubblesApps", "");
                if (TextUtils.isEmpty(strD)) {
                    return;
                }
                BubblesSettings.getInstance(context).saveCloudMiuiBubblesApps(strD);
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    public static void N(Context context) {
        try {
            if (m.d(context)) {
                boolean zB = b(context, "GbVtbAppListSettings", "music_bgm_status", true);
                Log.i("CloudControlHelper", "loadMusicBgmSettings: " + zB);
                m.e(zB);
            }
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadMusicBgmSettings failed : " + e10.toString());
        }
    }

    public static void O(Context context) {
        Log.i("CloudControlHelper", "loadSmartFiveGSettings");
        try {
            if (w5.b.d()) {
                boolean zB = b(context, "GtbSettings", "smartFiveGStatus", true);
                boolean zB2 = b(context, "GtbSettings", "smartFiveGDialogStatus", true);
                t5.a.y0(zB);
                t5.a.x0(zB2);
            }
        } catch (Exception unused) {
        }
    }

    public static void P(Context context) {
        if (c0.t()) {
            try {
                boolean zB = b(context, "gamebooster", "SupportDisableSecondarySim", true);
                Log.i("CloudControlHelper", "loadSupportDisableSecondarySim: " + zB);
                v0.i(zB);
                if (zB) {
                    return;
                }
                t5.a.X(context, false);
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
    }

    public static void Q(Context context) {
        Log.i("CloudControlHelper", "loadUnSupportBts");
        try {
            ArrayList<String> arrayListU = U(d(context, "GbVtbAppListSettings", "UnSupportBts", ""));
            Log.i("CloudControlHelper", "devices: " + arrayListU);
            if (arrayListU.isEmpty()) {
                return;
            }
            e.w(arrayListU);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadUnSupportBts failed : " + e10.toString());
        }
    }

    public static void R(Context context) {
        try {
            if (c0.V()) {
                boolean zB = b(context, "gamebooster", "NewVoiceSupport", true);
                Log.i("CloudControlHelper", "loadVoiceSettings: " + zB);
                d2.n(zB);
            }
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadMusicBgmSettings failed : " + e10.toString());
        }
    }

    public static void S(Context context) {
        try {
            boolean zB = b(context, "gamebooster", "XunyouExpiringSoonSwitch", true);
            Log.i("CloudControlHelper", "loadXunyouExpiringSoonPushSwitch: " + zB);
            v0.j(zB);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "get Xunyou Expiring soon Switch fail : " + e10.getMessage());
        }
    }

    public static void T(Context context) {
        if (c0.V()) {
            try {
                int iC = c(context, "gamebooster", "key_xunyou_voice_interval", 7);
                Log.i("CloudControlHelper", "loadXunyouVoiceInterval: " + iC);
                m0.E(iC);
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
    }

    private static ArrayList<String> U(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        JSONArray jSONArray = new JSONArray(str);
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            String strOptString = jSONArray.optString(i10);
            if (!TextUtils.isEmpty(strOptString)) {
                arrayList.add(strOptString);
            }
        }
        return arrayList;
    }

    private static void V(Context context, Intent intent) {
        try {
            l0.a.b(context).d(intent);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "send update monitor activity message failed : " + e10.toString());
        }
    }

    private static boolean b(Context context, String str, String str2, boolean z10) {
        return o.a(context, str, str2, z10);
    }

    private static int c(Context context, String str, String str2, int i10) {
        return o.b(context, str, str2, i10);
    }

    private static String d(Context context, String str, String str2, String str3) {
        return o.d(context, str, str2, str3);
    }

    public static void f(Context context) {
        try {
            if (q7.f.j()) {
                String strD = d(context, "GbVtbAppListSettings", "aiStronger", "");
                ArrayList<String> arrayListU = U(strD);
                if (!arrayListU.isEmpty()) {
                    c.U(arrayListU);
                }
                if (td.a.f54381a) {
                    Log.i("CloudControlHelper", "loadAiSettings: " + strD);
                }
            }
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadAiSettings failed : " + e10.toString());
        }
    }

    public static void g(Context context) {
        try {
            if ((w.d() || w.f()) && s4.a.G(context) && s4.a.n(context) && s4.a.o(context)) {
                s4.a.v(b(context, "gamebooster", "key_allow_auto_close_global_dock", s4.a.G(context)));
            }
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadAutoCloseGlobalDockStatus fail : " + e10.getMessage());
        }
    }

    public static void h(Context context) {
        try {
            if (s4.a.G(context) && c.O(context) && c.Q(context)) {
                c.V(b(context, "gamebooster", "key_allow_auto_close_video_box", s4.a.G(context)));
            }
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadAutoCloseGlobalDockStatus fail : " + e10.getMessage());
        }
    }

    public static void i(Context context) {
        Log.i("CloudControlHelper", "loadCleanerSettings");
        if (Build.IS_INTERNATIONAL_BUILD) {
            try {
                int iC = c(context, "CleanerSettings", "iconRecallInterval", 7);
                int iC2 = c(context, "CleanerSettings", "iconRecallMaxCount", 2);
                Log.i("CloudControlHelper", "loadCleanerSettings: interval=" + iC + "\tmaxCount=" + iC2);
                n3.i iVarA = n3.i.a(context);
                iVarA.k(iC2);
                iVarA.j((((long) iC) * DisasterConstants.SEVEN_DAY_TIME_MILLS) / 7);
            } catch (Exception e10) {
                Log.e("CloudControlHelper", "loadCleanerSettings failed : " + e10.toString());
            }
        }
    }

    public static void j(Context context) {
        Log.i("CloudControlHelper", "loadCloud3dVisionLutSupportApps");
        try {
            ArrayList<String> arrayListU = U(d(context, "cinema", "SupportVisionLutApps", ""));
            if (arrayListU.isEmpty()) {
                return;
            }
            c.E0(arrayListU);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloud3dVisionLutSupportApps failed : " + e10.toString());
        }
    }

    public static void k(Context context) {
        Log.i("CloudControlHelper", "loadCloudAisubtitleActivity");
        try {
            String strD = d(context, "GbVtbAppListSettings", "aisubtitle_activities", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudAisubtitleActivity: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.k0(strD);
            V(context, new Intent("beauty_action_monitor_activity"));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudAisubtitleActivity failed : " + e10);
        }
    }

    public static void l(Context context) {
        Log.i("CloudControlHelper", "loadCloudBeautyFaceAppsActivity");
        try {
            String strD = d(context, "GbVtbAppListSettings", "beauty_face", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudBeautyFaceAppsActivity: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.o0(strD);
            V(context, new Intent("beauty_action_monitor_activity"));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudBeautyFaceAppsActivity failed : " + e10);
        }
    }

    public static void m(Context context) {
        Log.i("CloudControlHelper", "loadCloudBeautyLightAppsActivity");
        try {
            String strD = d(context, "GbVtbAppListSettings", "beauty_light", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudBeautyLightAppsActivity: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.r0(strD);
            V(context, new Intent("beauty_action_monitor_activity"));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudBeautyLightAppsActivity failed : " + e10);
        }
    }

    public static void n(Context context) {
        Log.i("CloudControlHelper", "loadCloudBeautyPrivacyAllActivity");
        try {
            String strD = d(context, "GbVtbAppListSettings", "beauty_privacy_all", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudBeautyPrivacyAllActivity: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.q0(strD);
            V(context, new Intent("beauty_action_monitor_activity"));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudBeautyPrivacyAllActivity failed : " + e10);
        }
    }

    public static void o(Context context) {
        Log.i("CloudControlHelper", "loadCloudBeautyPrivacySingleActivity");
        try {
            String strD = d(context, "GbVtbAppListSettings", "beauty_privacy_single", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudBeautyPrivacySingleActivity: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.C0(strD);
            V(context, new Intent("beauty_action_monitor_activity"));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudBeautyPrivacySingleActivity failed : " + e10);
        }
    }

    public static void p(Context context) {
        Log.i("CloudControlHelper", "loadCloudBeautyPrivacySize");
        try {
            String strD = d(context, "GbVtbAppListSettings", "beauty_privacy_size", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudBeautyPrivacySize: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.D0(strD);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudBeautyPrivacySize failed : " + e10);
        }
    }

    public static void q(Context context) {
        try {
            String strD = d(context, "GbVtbAppListSettings", "supportCbDivisionApps", "");
            ArrayList<String> arrayListU = U(strD);
            if (strD.isEmpty()) {
                return;
            }
            c.W(arrayListU);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudCbDivisionApps fail : " + e10.getMessage());
        }
    }

    public static void r(Context context) {
        Log.i("CloudControlHelper", "loadCloudConversationDevices");
        try {
            boolean zB = b(context, "GbVtbAppListSettings", "conversation_devices", true);
            Log.i("CloudControlHelper", "loadCloudConversationDevices: " + zB);
            i.s0(zB);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudConversationDevices : " + e10.getMessage());
        }
    }

    public static void s(Context context) {
        try {
            boolean zB = b(context, "GbVtbAppListSettings", "is_conversation_split_mode_no_limit", false);
            String strD = d(context, "GbVtbAppListSettings", "conversation_box_support_split_mode_devices", "");
            String strD2 = d(context, "GbVtbAppListSettings", "pref_split_scene", "");
            i.q0(U(strD), zB);
            if (!TextUtils.isEmpty(strD2)) {
                z3.a.r("pref_split_scene", strD2);
                V(context, new Intent("beauty_action_monitor_activity"));
                Log.i("CloudControlHelper", "broadcast update split scene");
            }
            Log.i("CloudControlHelper", "loadCloudConversationSplitModeInfo, nolimit = " + zB + ", devicesStr = " + strD + ", activityList = " + strD2);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudConversationSplitModeInfo fail " + e10);
        }
    }

    public static void t(Context context) {
        try {
            I(context);
            J(context);
            M(context);
            E(context);
        } catch (Exception unused) {
        }
    }

    public static void u(Context context) {
        Log.i("CloudControlHelper", "loadDivisionOptApps");
        try {
            ArrayList<String> arrayListU = U(d(context, "GbVtbAppListSettings", "SupportDivisionApps", ""));
            if (arrayListU.isEmpty()) {
                return;
            }
            c.b0(arrayListU);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadDivisionOptApps failed : " + e10.toString());
        }
    }

    public static void v(Context context) {
        Log.i("CloudControlHelper", "loadCloudDolbySpecialDevice");
        try {
            ArrayList<String> arrayListU = U(d(context, "GbVtbAppListSettings", "dolby_special_device", ""));
            if (arrayListU.isEmpty()) {
                return;
            }
            c.f0(arrayListU);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadDivisionOptApps failed : " + e10.toString());
        }
    }

    public static void w(Context context) {
        Log.i("CloudControlHelper", "loadPicOptApps");
        try {
            ArrayList<String> arrayListU = U(d(context, "GbVtbAppListSettings", "SupportFrcApps", ""));
            if (!arrayListU.isEmpty()) {
                c.h0(arrayListU);
            }
            ArrayList<String> arrayListU2 = U(d(context, "GbVtbAppListSettings", "SupportVppApps", ""));
            Log.i("CloudControlHelper", "vppList: " + arrayListU2);
            if (arrayListU2.isEmpty()) {
                return;
            }
            c.F0(arrayListU2);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadPicOptApps failed : " + e10.toString());
        }
    }

    public static void x(Context context) {
        Log.i("CloudControlHelper", "loadCloudPickupActivity");
        try {
            String strD = d(context, "GbVtbAppListSettings", "pickup_activities", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudPickupActivity: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.w0(strD);
            V(context, new Intent("beauty_action_monitor_activity"));
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudPickupActivity failed : " + e10);
        }
    }

    public static void y(Context context) {
        Log.i("CloudControlHelper", "loadCloudPortraitCenterApps");
        try {
            String strD = d(context, "GbVtbAppListSettings", "portrait_center", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudPortraitCenterApps: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.x0(strD);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudPortraitCenterApps failed : " + e10);
        }
    }

    public static void z(Context context) {
        Log.i("CloudControlHelper", "loadCloudPrivacySupportDevices");
        try {
            String strD = d(context, "GbVtbAppListSettings", "beauty_privacy_support_device", "");
            if (td.a.f54381a) {
                Log.i("CloudControlHelper", "loadCloudPrivacySupportDevices: " + strD);
            }
            if (TextUtils.isEmpty(strD)) {
                return;
            }
            f.z0(strD);
        } catch (Exception e10) {
            Log.e("CloudControlHelper", "loadCloudPrivacySupportDevices failed : " + e10);
        }
    }
}
