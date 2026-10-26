package v5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import dd.z;
import h7.e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: v5.a$a, reason: collision with other inner class name */
    class RunnableC0617a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f54837c;

        RunnableC0617a(Context context) {
            this.f54837c = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.d(this.f54837c, null);
            } catch (Exception e10) {
                Log.e("TopGameUtils", "run: " + e10.toString());
            }
        }
    }

    private static List<String> a(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(e.a(context, "top_200_games.json")).optJSONArray("packageNames");
            int length = jSONArrayOptJSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                String strOptString = jSONArrayOptJSONArray.optString(i10);
                if (strOptString != null) {
                    arrayList.add(strOptString);
                }
            }
        } catch (Exception unused) {
        }
        arrayList.add("com.tencent.af");
        arrayList.add("com.t2ksports.nba2k19and");
        arrayList.add("com.t2ksports.nba2k20and");
        arrayList.add("com.t2ksports.nba2k18and");
        arrayList.add("com.miHoYo.enterprise.NGHSoD");
        arrayList.add("com.miHoYo.bh3.uc");
        arrayList.add("com.netease.lx12.mi");
        arrayList.add("com.netease.lx12");
        arrayList.add("com.netease.mrzh");
        return arrayList;
    }

    private static String b(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            String str = list.get(i10);
            if (str != null) {
                sb2.append(str);
                sb2.append(",");
            }
        }
        return sb2.toString();
    }

    public static void c(Context context) {
        boolean z10 = context.getSharedPreferences("gb_gamead_data_config", 0).getBoolean("is_never_send_top_games", true);
        if (z10) {
            z.c().b(new RunnableC0617a(context));
            return;
        }
        Log.i("TopGameUtils", "saveTopGamesForFirst: neverSend=" + z10);
    }

    public static void d(Context context, List<String> list) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gb_gamead_data_config", 0);
            boolean z10 = sharedPreferences.getBoolean("is_never_send_top_games", true);
            if (!z10 && Settings.Secure.getInt(context.getContentResolver(), "support_highfps", 0) != 1) {
                Log.i("TopGameUtils", "joyose not support!!!");
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (list != null && !list.isEmpty()) {
                arrayList.addAll(list);
            }
            for (String str : a(context)) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
            int size = arrayList.size();
            int i10 = 0;
            boolean z11 = false;
            while (i10 < size) {
                int i11 = i10 + 50;
                String strB = b(arrayList.subList(i10, i11 > size ? size : i11));
                if (!TextUtils.isEmpty(strB)) {
                    f(context, strB, z11);
                    if (!z11) {
                        z11 = true;
                    }
                }
                i10 = i11;
            }
            if (z10) {
                sharedPreferences.edit().putBoolean("is_never_send_top_games", false).commit();
            }
            Log.i("TopGameUtils", "saveTopGamesToPk: " + size);
        } catch (Exception e10) {
            Log.e("TopGameUtils", "save data to power keeper failed" + e10);
        }
    }

    public static void e(Context context, String str, int i10) {
        try {
            Intent intent = new Intent("com.miui.powerkeeper.set_fps");
            intent.putExtra("pkg", str);
            intent.putExtra("model", i10);
            intent.setPackage("com.miui.powerkeeper");
            context.sendBroadcast(intent, "com.miui.securitycenter.permission.TOP_GAME_LIST");
        } catch (Exception unused) {
        }
    }

    private static void f(Context context, String str, boolean z10) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Intent intent = new Intent("com.miui.securitycenter.intent.action.TOP_GAME_LIST");
            intent.putExtra("gameList", str);
            intent.putExtra("isAppend", z10);
            intent.setPackage("com.miui.powerkeeper");
            context.sendBroadcast(intent, "com.miui.securitycenter.permission.TOP_GAME_LIST");
        } catch (Exception unused) {
        }
    }
}
