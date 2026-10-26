package d2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.miui.antispam.policy.KeywordsBlackListPolicy;
import com.miui.antispam.policy.MmsPolicy;
import dd.w;
import j2.c;
import j2.d;
import j2.e;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import miui.cloud.common.XSimChangeNotification;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import o2.f;

/* JADX INFO: loaded from: classes2.dex */
public class b implements com.miui.antispam.policy.a.b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static volatile b f44070f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f44071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private j2.b f44072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.miui.antispam.policy.b f44073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TreeMap<e, com.miui.antispam.policy.a> f44074d = new TreeMap<>(new a());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.miui.antispam.policy.a f44075e;

    class a implements Comparator<e> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e eVar, e eVar2) {
            return Integer.valueOf(eVar.f47871c).compareTo(Integer.valueOf(eVar2.f47871c));
        }
    }

    private b(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f44071a = applicationContext;
        this.f44072b = new j2.b(applicationContext);
        for (e eVar : e.values()) {
            try {
                this.f44074d.put(eVar, (com.miui.antispam.policy.a) eVar.f47872d.getConstructor(Context.class, com.miui.antispam.policy.a.b.class, j2.b.class, e.class).newInstance(this.f44071a, this, this.f44072b, eVar));
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e10) {
                e10.printStackTrace();
            }
        }
        this.f44073c = new com.miui.antispam.policy.b(this.f44071a);
        this.f44072b.l();
    }

    public static synchronized b c(Context context) {
        if (f44070f == null) {
            f44070f = new b(context);
        }
        return f44070f;
    }

    @Override // com.miui.antispam.policy.a.b
    public com.miui.antispam.policy.a a(e eVar) {
        return this.f44074d.get(eVar);
    }

    public int b(c cVar) {
        int i10 = 0;
        if (!Build.IS_INTERNATIONAL_BUILD && !w.u()) {
            return 0;
        }
        Log.d("PolicyManager", "start call policy judge, antispam enabled = " + o2.a.j(this.f44071a, cVar.f47838e));
        if (o2.a.j(this.f44071a, cVar.f47838e)) {
            for (Map.Entry<e, com.miui.antispam.policy.a> entry : this.f44074d.entrySet()) {
                com.miui.antispam.policy.a value = entry.getValue();
                if (entry.getKey().f47873e == d.CALL || entry.getKey().f47873e == d.BOTH) {
                    Log.w("PolicyManager", "start call policy " + value.getName());
                    com.miui.antispam.policy.a.C0115a c0115aHandleData = value.handleData(cVar);
                    if (c0115aHandleData != null) {
                        i10 = c0115aHandleData.f7161b;
                        if (c0115aHandleData.f7160a) {
                            Log.w("PolicyManager", "call is handled by " + value.getName());
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        Log.d("PolicyManager", "conventional call policy judge finish" + i10);
        return i10;
    }

    public j2.b d() {
        return this.f44072b;
    }

    public int e(c cVar) {
        int i10 = 0;
        if (!Build.IS_INTERNATIONAL_BUILD && !w.u()) {
            return 0;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        String str = "";
        if (!cVar.f47844k || Build.IS_INTERNATIONAL_BUILD) {
            Log.d("PolicyManager", "start sms policy judge, antispam enabled = " + o2.a.j(this.f44071a, cVar.f47838e));
            if (o2.a.j(this.f44071a, cVar.f47838e)) {
                for (Map.Entry<e, com.miui.antispam.policy.a> entry : this.f44074d.entrySet()) {
                    com.miui.antispam.policy.a value = entry.getValue();
                    if (entry.getKey().f47873e == d.SMS || entry.getKey().f47873e == d.BOTH) {
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        Log.w("PolicyManager", "start sms policy " + value.getName());
                        com.miui.antispam.policy.a.C0115a c0115aHandleData = value.handleData(cVar);
                        if (c0115aHandleData != null) {
                            if (value instanceof KeywordsBlackListPolicy) {
                                str = c0115aHandleData.f7162c;
                            }
                            i10 = c0115aHandleData.f7161b;
                            if (c0115aHandleData.f7160a) {
                                Log.w("PolicyManager", "sms is handled by " + value.getName());
                                break;
                            }
                        }
                        e2.a.k(entry.getKey().f47871c, System.currentTimeMillis() - jCurrentTimeMillis2);
                    }
                }
            }
            Log.d("PolicyManager", "conventional sms policy judge finish");
            com.miui.antispam.policy.b.a aVarD = this.f44073c.d(i10, cVar);
            if (aVarD != null && aVarD.f7167a) {
                Log.w("PolicyManager", "sms is handled by URLFilterPolicy");
                i10 = aVarD.f7168b;
            }
        } else {
            Log.d("PolicyManager", "start mms policy judge, antispam enabled = " + o2.a.j(this.f44071a, cVar.f47838e));
            if (o2.a.j(this.f44071a, cVar.f47838e)) {
                if (this.f44075e == null) {
                    this.f44075e = new MmsPolicy(this.f44071a, null, null, null);
                }
                com.miui.antispam.policy.a.C0115a c0115aHandleData2 = this.f44075e.handleData(cVar);
                if (c0115aHandleData2 != null && (i10 = c0115aHandleData2.f7161b) > 0) {
                    Log.w("PolicyManager", "mms is handled by " + this.f44075e.getName());
                }
            }
        }
        int realBlockType = ExtraTelephony.getRealBlockType(i10);
        if (realBlockType >= 3) {
            f.a(this.f44071a, cVar.f47835b, realBlockType, str);
        }
        Log.d("PolicyManager", "all sms policy judge finished : " + i10);
        e2.a.h(System.currentTimeMillis() - jCurrentTimeMillis);
        return i10;
    }

    public int f(String str, String str2) throws Throwable {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iC = this.f44073c.c(str, str2);
        Log.e("PolicyManager", "getURLScanResult execute time =" + (System.currentTimeMillis() - jCurrentTimeMillis));
        return iC;
    }

    public int g(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from " + str + " where " + str2, strArr);
        int i10 = -1;
        if (cursorRawQuery != null) {
            while (cursorRawQuery.moveToNext()) {
                try {
                    try {
                        i10 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("_id"));
                        this.f44072b.z(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("data")), cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("type")), cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID)));
                    } catch (Exception e10) {
                        e10.printStackTrace();
                    }
                } catch (Throwable th2) {
                    cursorRawQuery.close();
                    throw th2;
                }
            }
            cursorRawQuery.close();
        }
        return i10;
    }

    public void h(String str, int i10, int i11) {
        this.f44072b.A(str, i10, i11);
    }

    public void i(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from " + str + " where " + str2, strArr);
        if (cursorRawQuery != null) {
            try {
                try {
                    if (cursorRawQuery.moveToNext()) {
                        this.f44072b.A(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("data")), cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("type")), cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID)));
                    }
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
            } finally {
                cursorRawQuery.close();
            }
        }
    }

    public int j(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from " + str + " where " + str2, strArr);
        int i10 = -1;
        if (cursorRawQuery != null) {
            while (cursorRawQuery.moveToNext()) {
                try {
                    try {
                        i10 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("_id"));
                        this.f44072b.B(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")), cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("type")), cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID)));
                    } catch (Exception e10) {
                        e10.printStackTrace();
                    }
                } catch (Throwable th2) {
                    cursorRawQuery.close();
                    throw th2;
                }
            }
            cursorRawQuery.close();
        }
        return i10;
    }

    public void k(String str, int i10, int i11, int i12) {
        this.f44072b.C(str, i10, i11, i12);
    }

    public void l(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from " + str + " where " + str2, strArr);
        try {
            if (cursorRawQuery != null) {
                try {
                    if (cursorRawQuery.moveToNext()) {
                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number"));
                        int i10 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("type"));
                        int i11 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex("state"));
                        int i12 = cursorRawQuery.getInt(cursorRawQuery.getColumnIndex(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID));
                        this.f44072b.C(string, i11, i10, i12);
                        if (!o2.a.j(this.f44071a, i12)) {
                            o2.a.t(this.f44071a, i12, true);
                        }
                    }
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
            }
        } finally {
            cursorRawQuery.close();
        }
    }
}
