package j2;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.miui.antispam.policy.BlackAddressPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.cloud.common.XSimChangeNotification;
import miui.provider.ExtraTelephony;
import miui.telephony.PhoneNumberUtils;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SparseArray<SparseArray<Map<String, Integer>>> f47828a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SparseArray<SparseArray<List<String>>> f47829b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f47830c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private AtomicBoolean f47831d = new AtomicBoolean(false);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private AtomicBoolean f47832e = new AtomicBoolean(false);

    class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            b.this.n();
            b.this.m();
            b.this.f47831d.set(!b.this.f47832e.get());
        }
    }

    public b(Context context) {
        this.f47830c = context;
    }

    private String j(String str, int i10, int i11) {
        for (String str2 : this.f47829b.get(i10).get(i11)) {
            if (str.toLowerCase().contains(str2.toLowerCase())) {
                return str2;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        SparseArray<SparseArray<List<String>>> sparseArray = new SparseArray<>();
        this.f47829b = sparseArray;
        sparseArray.put(1, new SparseArray<>());
        this.f47829b.put(4, new SparseArray<>());
        this.f47829b.put(2, new SparseArray<>());
        this.f47829b.put(3, new SparseArray<>());
        this.f47829b.get(1).put(1, Collections.synchronizedList(new ArrayList()));
        this.f47829b.get(1).put(2, Collections.synchronizedList(new ArrayList()));
        this.f47829b.get(4).put(1, Collections.synchronizedList(new ArrayList()));
        this.f47829b.get(4).put(2, Collections.synchronizedList(new ArrayList()));
        this.f47829b.get(2).put(1, Collections.synchronizedList(new ArrayList()));
        this.f47829b.get(3).put(1, Collections.synchronizedList(new ArrayList()));
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.f47830c.getContentResolver().query(ExtraTelephony.Keyword.CONTENT_URI, new String[]{"type", "data", XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID}, null, null, null);
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("data"));
                        int i10 = cursorQuery.getInt(cursorQuery.getColumnIndex("type"));
                        this.f47829b.get(i10).get(cursorQuery.getInt(cursorQuery.getColumnIndex(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID))).add(string);
                    }
                }
            } catch (Exception e10) {
                this.f47832e.set(true);
                Log.e("Judge", "Exception when initKeywordList !", e10);
            }
            ti.e.a(cursorQuery);
            Log.i("Judge", "Loading AntiSpam KeywordList");
        } catch (Throwable th2) {
            ti.e.a(cursorQuery);
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        SparseArray<SparseArray<Map<String, Integer>>> sparseArray = new SparseArray<>();
        this.f47828a = sparseArray;
        int i10 = d2.a.c.f44063a;
        sparseArray.put(i10, new SparseArray<>());
        SparseArray<SparseArray<Map<String, Integer>>> sparseArray2 = this.f47828a;
        int i11 = d2.a.c.f44064b;
        sparseArray2.put(i11, new SparseArray<>());
        SparseArray<SparseArray<Map<String, Integer>>> sparseArray3 = this.f47828a;
        int i12 = d2.a.c.f44065c;
        sparseArray3.put(i12, new SparseArray<>());
        SparseArray<SparseArray<Map<String, Integer>>> sparseArray4 = this.f47828a;
        int i13 = d2.a.c.f44066d;
        sparseArray4.put(i13, new SparseArray<>());
        SparseArray<SparseArray<Map<String, Integer>>> sparseArray5 = this.f47828a;
        int i14 = d2.a.c.f44067e;
        sparseArray5.put(i14, new SparseArray<>());
        SparseArray<SparseArray<Map<String, Integer>>> sparseArray6 = this.f47828a;
        int i15 = d2.a.c.f44068f;
        sparseArray6.put(i15, new SparseArray<>());
        SparseArray<SparseArray<Map<String, Integer>>> sparseArray7 = this.f47828a;
        int i16 = d2.a.c.f44069g;
        sparseArray7.put(i16, new SparseArray<>());
        this.f47828a.get(i10).put(1, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i10).put(2, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i11).put(1, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i11).put(2, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i12).put(1, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i13).put(1, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i14).put(1, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i15).put(1, Collections.synchronizedMap(new ArrayMap()));
        this.f47828a.get(i16).put(1, Collections.synchronizedMap(new ArrayMap()));
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.f47830c.getContentResolver().query(ExtraTelephony.Phonelist.CONTENT_URI, new String[]{"type", "state", "number", XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID}, "sync_dirty<> ? ", new String[]{String.valueOf(1)}, null);
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        this.f47828a.get(cursorQuery.getInt(cursorQuery.getColumnIndex("type"))).get(cursorQuery.getInt(cursorQuery.getColumnIndex(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID))).put(cursorQuery.getString(cursorQuery.getColumnIndex("number")), Integer.valueOf(cursorQuery.getInt(cursorQuery.getColumnIndex("state"))));
                    }
                }
            } catch (Exception e10) {
                this.f47832e.set(true);
                Log.e("Judge", "Exception when initPhoneList !", e10);
            }
            ti.e.a(cursorQuery);
            Log.i("Judge", "Loading AntiSpam PhoneList");
        } catch (Throwable th2) {
            ti.e.a(cursorQuery);
            throw th2;
        }
    }

    private boolean p(c cVar, int i10) {
        int i11;
        int i12 = d2.a.c.f44063a;
        int i13 = (i10 == i12 || i10 == d2.a.c.f44064b) ? cVar.f47838e : 1;
        if (t(cVar.f47835b, cVar.f47839f, i10, i13) || o(cVar.f47834a, cVar.f47839f, i10, i13) || u(cVar.f47835b, cVar.f47839f, i10, i13) || v(cVar.f47835b, cVar.f47839f, i10, i13)) {
            return true;
        }
        if ((i10 == i12 || i10 == d2.a.c.f44064b) && (i11 = cVar.f47839f) == 1) {
            return u(cVar.f47834a, i11, i10, i13);
        }
        return false;
    }

    private boolean v(String str, int i10, int i11, int i12) {
        for (Map.Entry<String, Integer> entry : this.f47828a.get(i11).get(i12).entrySet()) {
            int iIntValue = entry.getValue().intValue();
            if (iIntValue == 0 || iIntValue == i10) {
                if (str.matches(y(entry.getKey()))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String y(String str) {
        return str.replace("*", "[\\s\\S]*").replace("#", "[\\s\\S]").replace("+", "\\+");
    }

    public void A(String str, int i10, int i11) {
        if (this.f47831d.get()) {
            List<String> list = this.f47829b.get(i10).get(i11);
            if (!list.contains(str)) {
                list.add(str);
            }
            if (d2.a.f44060a) {
                Log.i("Judge", "insert keyword : data = " + str);
            }
        }
    }

    public void B(String str, int i10, int i11) {
        if (this.f47831d.get()) {
            this.f47828a.get(i10).get(i11).remove(str);
            if (d2.a.f44060a) {
                Log.i("Judge", "delete phone list : number = " + str);
            }
        }
    }

    public void C(String str, int i10, int i11, int i12) {
        if (this.f47831d.get()) {
            this.f47828a.get(i11).get(i12).put(str, Integer.valueOf(i10));
            if (d2.a.f44060a) {
                Log.i("Judge", "insert phone list : number = " + str);
            }
        }
    }

    public boolean e(String str, int i10, int i11, int i12) {
        return t(str, i10, i11, i12) || u(str, i10, i11, i12) || v(str, i10, i11, i12);
    }

    public boolean f(c cVar, int i10) {
        return p(cVar, i10);
    }

    public String g(String str, int i10) {
        return j(str, 1, i10);
    }

    public String h(String str) {
        return j(str, 2, 1);
    }

    public String i(String str) {
        return j(str, 3, 1);
    }

    public String k(String str, int i10) {
        return j(str, 4, i10);
    }

    public void l() {
        new a().start();
    }

    public boolean o(String str, int i10, int i11, int i12) {
        String countryCode = PhoneNumberUtils.PhoneNumber.parse(str).getCountryCode();
        if (!TextUtils.isEmpty(countryCode)) {
            if (t("***" + countryCode, i10, i11, i12)) {
                return true;
            }
        }
        String locationAreaCode = PhoneNumberUtils.PhoneNumber.getLocationAreaCode(this.f47830c, str);
        if (BlackAddressPolicy.a.b(locationAreaCode)) {
            locationAreaCode = BlackAddressPolicy.a.a(this.f47830c, str, locationAreaCode);
        }
        return t("***" + locationAreaCode, i10, i11, i12);
    }

    public boolean q() {
        return this.f47831d.get();
    }

    public boolean r(c cVar) {
        return p(cVar, d2.a.c.f44066d);
    }

    public boolean s(c cVar) {
        return p(cVar, d2.a.c.f44067e);
    }

    public boolean t(String str, int i10, int i11, int i12) {
        Map<String, Integer> map = this.f47828a.get(i11).get(i12);
        if (!map.containsKey(str)) {
            return false;
        }
        int iIntValue = map.get(str).intValue();
        return iIntValue == 0 || iIntValue == i10;
    }

    public boolean u(String str, int i10, int i11, int i12) {
        String str2 = "";
        for (int i13 = 0; i13 < str.length(); i13++) {
            str2 = str2 + str.charAt(i13);
            if (t(str2 + "*", i10, i11, i12)) {
                return true;
            }
        }
        return false;
    }

    public boolean w(c cVar) {
        return p(cVar, d2.a.c.f44068f);
    }

    public boolean x(c cVar) {
        return p(cVar, d2.a.c.f44069g);
    }

    public void z(String str, int i10, int i11) {
        if (this.f47831d.get()) {
            this.f47829b.get(i10).get(i11).remove(str);
            if (d2.a.f44060a) {
                Log.i("Judge", "delete keyword : data = " + str);
            }
        }
    }
}
