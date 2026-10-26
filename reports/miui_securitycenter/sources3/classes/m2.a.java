package m2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import ce.j;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.miui.analytics.StatManager;
import com.miui.luckymoney.config.Constants;
import com.miui.securitycenter.Application;
import com.xiaomi.miglobaladsdk.bid.BidConstance;
import f4.f;
import g2.b;
import g2.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miui.os.Build;
import org.json.JSONArray;
import org.json.JSONObject;
import x3.i;

/* JADX INFO: loaded from: classes2.dex */
public class a extends com.miui.antispam.service.a {

    /* JADX INFO: renamed from: m2.a$a, reason: collision with other inner class name */
    class RunnableC0498a implements Runnable {
        RunnableC0498a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i("CloudPhoneListService", "start doUpdate ...");
            a.this.f();
            Log.i("CloudPhoneListService", "end doUpdate ...");
            a.this.d(false);
        }
    }

    public a(Context context, com.miui.antispam.service.a.InterfaceC0116a interfaceC0116a) {
        super(context, interfaceC0116a);
    }

    private void g() {
        SQLiteDatabase writableDatabase = c.a(Application.y()).getWritableDatabase();
        writableDatabase.execSQL("delete from phone_list where cloudUid not null");
        writableDatabase.execSQL("delete from keyword where cloudUid not null");
    }

    public boolean f() {
        String str;
        char c10;
        List list;
        Object aVar;
        List list2;
        Object aVar2;
        String str2 = "CloudPhoneListService";
        try {
            long jC = b.c();
            Log.e("CloudPhoneListService", "local cloudsDataVersion is = " + jC);
            if (jC == 0) {
                g();
            }
            HashMap map = new HashMap();
            map.put(Constants.JSON_KEY_DATA_VERSION, String.valueOf(jC));
            String strW = j.w(map, "https://api.sec.intl.miui.com/harassIntercept/intercepts", j.b.POST, "5cdd8678-cddf-4269-ab73-48387445bba5", new i("antispam_cloudphonelistservice"));
            if (TextUtils.isEmpty(strW)) {
                return false;
            }
            JSONObject jSONObject = new JSONObject(strW);
            long j10 = jSONObject.getInt("l");
            if (b.c() < j10) {
                SparseArray<List<i2.b>> sparseArray = new SparseArray<>();
                sparseArray.put(0, new ArrayList());
                sparseArray.put(1, new ArrayList());
                sparseArray.put(2, new ArrayList());
                SparseArray<List<i2.a>> sparseArray2 = new SparseArray<>();
                sparseArray2.put(0, new ArrayList());
                sparseArray2.put(1, new ArrayList());
                sparseArray2.put(2, new ArrayList());
                h2.c cVar = new h2.c();
                h2.b bVar = new h2.b();
                JSONArray jSONArray = jSONObject.getJSONArray(BidConstance.BID_V);
                int i10 = 0;
                while (i10 < jSONArray.length()) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
                    String strOptString = jSONObject2.optString("status");
                    int iOptInt = jSONObject2.optInt("type");
                    JSONArray jSONArray2 = jSONArray;
                    if (!AppSettingsData.STATUS_NEW.equals(strOptString)) {
                        str = str2;
                        if ("updated".equals(strOptString)) {
                            if (iOptInt != 1) {
                                c10 = 2;
                                if (iOptInt == 2) {
                                    if (bVar.e(jSONObject2.optString(StatManager.PARAMS_UUID))) {
                                        list2 = sparseArray2.get(1);
                                        aVar2 = new i2.a(jSONObject2.optString(StatManager.PARAMS_UUID), jSONObject2.optString("value"), Integer.valueOf(jSONObject2.optString("attributes")).intValue());
                                        list2.add(aVar2);
                                        c10 = 2;
                                    } else {
                                        list = sparseArray2.get(0);
                                        aVar = new i2.a(jSONObject2.optString(StatManager.PARAMS_UUID), jSONObject2.optString("value"), Integer.valueOf(jSONObject2.optString("attributes")).intValue());
                                        list.add(aVar);
                                        c10 = 2;
                                    }
                                }
                            } else if (cVar.e(jSONObject2.optString(StatManager.PARAMS_UUID))) {
                                list2 = sparseArray.get(1);
                                aVar2 = new i2.b(jSONObject2.optString(StatManager.PARAMS_UUID), jSONObject2.optString("value"), Integer.valueOf(jSONObject2.optString("attributes").substring(0, 1)).intValue(), Integer.valueOf(jSONObject2.optString("attributes").substring(1, 2)).intValue());
                                list2.add(aVar2);
                                c10 = 2;
                            } else {
                                list = sparseArray.get(0);
                                aVar = new i2.b(jSONObject2.optString(StatManager.PARAMS_UUID), jSONObject2.optString("value"), Integer.valueOf(jSONObject2.optString("attributes").substring(0, 1)).intValue(), Integer.valueOf(jSONObject2.optString("attributes").substring(1, 2)).intValue());
                                list.add(aVar);
                                c10 = 2;
                            }
                        } else if (!"deleted".equals(strOptString)) {
                            c10 = 2;
                        } else if (iOptInt == 1) {
                            sparseArray.get(2).add(new i2.b(jSONObject2.optString(StatManager.PARAMS_UUID)));
                            c10 = 2;
                        } else {
                            c10 = 2;
                            if (iOptInt == 2) {
                                sparseArray2.get(2).add(new i2.a(jSONObject2.optString(StatManager.PARAMS_UUID)));
                            }
                        }
                    } else if (iOptInt == 1) {
                        str = str2;
                        try {
                            sparseArray.get(0).add(new i2.b(jSONObject2.optString(StatManager.PARAMS_UUID), jSONObject2.optString("value"), Integer.valueOf(jSONObject2.optString("attributes").substring(0, 1)).intValue(), Integer.valueOf(jSONObject2.optString("attributes").substring(1, 2)).intValue()));
                            c10 = 2;
                        } catch (Exception e10) {
                            e = e10;
                        }
                    } else {
                        str = str2;
                        c10 = 2;
                        if (iOptInt == 2) {
                            list = sparseArray2.get(0);
                            aVar = new i2.a(jSONObject2.optString(StatManager.PARAMS_UUID), jSONObject2.optString("value"), Integer.valueOf(jSONObject2.optString("attributes")).intValue());
                            list.add(aVar);
                            c10 = 2;
                        }
                    }
                    i10++;
                    jSONArray = jSONArray2;
                    str2 = str;
                }
                str = str2;
                cVar.g(sparseArray);
                bVar.f(sparseArray2);
                b.u(j10);
            }
            return true;
        } catch (Exception e11) {
            e = e11;
            str = str2;
        }
        Log.e(str, "Exception in pull cloud phone list:", e);
        return false;
    }

    public void h() {
        if (Build.IS_INTERNATIONAL_BUILD || !b.h()) {
            return;
        }
        d(true);
        f.b(new RunnableC0498a());
    }
}
