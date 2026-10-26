package com.xiaomi.payment.entry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.mipay.common.data.b0;
import com.mipay.common.data.g0;
import com.mipay.common.data.v0;
import com.mipay.core.runtime.f;
import com.xiaomi.payment.hybrid.MibiHybridActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: EntryManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3561b = "EntryManager";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static a f3562c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3563d = "com.xiaomi.payment.entry_provider";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3564e = "provider";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3565f = "id";
    private static final String g = "class";
    private static final String h = "return_result";
    private static final String i = "exported";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, d> f3566a = new HashMap();

    /* JADX INFO: renamed from: com.xiaomi.payment.entry.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EntryManager.java */
    static /* synthetic */ class C0106a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3567a = new int[com.xiaomi.payment.l.c.b.values().length];

        static {
            try {
                f3567a[com.xiaomi.payment.l.c.b.LOCAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3567a[com.xiaomi.payment.l.c.b.WEB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3567a[com.xiaomi.payment.l.c.b.APP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: EntryManager.java */
    private static class b implements IEntry.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Activity f3568a;

        public b(Activity activity) {
            this.f3568a = activity;
        }

        @Override // com.xiaomi.payment.entry.IEntry.a
        public void a(Intent intent, int i) {
            this.f3568a.startActivityForResult(intent, i);
        }

        @Override // com.xiaomi.payment.entry.IEntry.a
        public Context getContext() {
            return this.f3568a;
        }
    }

    /* JADX INFO: compiled from: EntryManager.java */
    private static class c implements IEntry.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Context f3569a;

        public c(Context context) {
            this.f3569a = context;
        }

        @Override // com.xiaomi.payment.entry.IEntry.a
        public void a(Intent intent, int i) {
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            this.f3569a.startActivity(intent);
        }

        @Override // com.xiaomi.payment.entry.IEntry.a
        public Context getContext() {
            return this.f3569a;
        }
    }

    /* JADX INFO: compiled from: EntryManager.java */
    private class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        IEntry f3570a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Map<String, Object> f3571b;

        private d() {
        }

        /* synthetic */ d(a aVar, C0106a c0106a) {
            this();
        }
    }

    /* JADX INFO: compiled from: EntryManager.java */
    private static class e implements IEntry.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Fragment f3573a;

        public e(Fragment fragment) {
            this.f3573a = fragment;
        }

        @Override // com.xiaomi.payment.entry.IEntry.a
        public void a(Intent intent, int i) {
            this.f3573a.startActivityForResult(intent, i);
        }

        @Override // com.xiaomi.payment.entry.IEntry.a
        public Context getContext() {
            return this.f3573a.getActivity();
        }
    }

    private a() {
        b();
    }

    public static a a() {
        return f3562c;
    }

    private void b() {
        List<com.mipay.core.runtime.c> listF;
        f fVarB = com.mipay.core.runtime.a.b().b(f3563d);
        if (fVarB == null || (listF = fVarB.f()) == null) {
            return;
        }
        for (com.mipay.core.runtime.c cVar : listF) {
            try {
                for (com.mipay.core.runtime.d dVar : cVar.h()) {
                    try {
                        if (TextUtils.equals(dVar.getName(), f3564e)) {
                            String strB = dVar.b("id");
                            IEntry iEntry = (IEntry) dVar.d(g);
                            d dVar2 = new d(this, null);
                            dVar2.f3570a = iEntry;
                            dVar2.f3571b = a(dVar);
                            if (!TextUtils.isEmpty(strB) && iEntry != null) {
                                this.f3566a.put(strB, dVar2);
                            }
                        }
                    } catch (Exception unused) {
                        Log.d(f3561b, "parse entry extension fails at " + cVar.c());
                    }
                }
            } catch (Exception unused2) {
                Log.d(f3561b, "parse entry extension fails at " + cVar.c());
            }
        }
    }

    private void c(String str) {
        HashMap map = new HashMap();
        map.put(com.xiaomi.payment.l.a.w1, str);
        g0.a("entry", com.xiaomi.payment.l.a.v1, map);
    }

    private Map<String, Object> a(com.mipay.core.runtime.d dVar) {
        HashMap map = new HashMap();
        for (String str : dVar.j()) {
            if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, "id") && !TextUtils.equals(str, g)) {
                String strB = dVar.b(str);
                if (!TextUtils.isEmpty(strB)) {
                    map.put(str, strB);
                }
            }
        }
        return map;
    }

    public ArrayList<String> a(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<Map.Entry<String, d>> it = this.f3566a.entrySet().iterator();
        while (it.hasNext()) {
            IEntry iEntry = it.next().getValue().f3570a;
            if (iEntry.a(context)) {
                arrayList.add(iEntry.getId());
            }
        }
        return arrayList;
    }

    public boolean a(String str) {
        String strA = a(str, i);
        return strA == null || !strA.equalsIgnoreCase("false");
    }

    public boolean b(String str) {
        return Boolean.parseBoolean(a(str, h));
    }

    private String a(String str, String str2) {
        d dVar;
        Map<String, Object> map;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (dVar = this.f3566a.get(str)) == null || (map = dVar.f3571b) == null) {
            return null;
        }
        return (String) map.get(str2);
    }

    public boolean a(Activity activity, com.xiaomi.payment.l.c cVar, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a(new b(activity), cVar, bundle, i2);
    }

    public boolean a(Fragment fragment, com.xiaomi.payment.l.c cVar, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a(new e(fragment), cVar, bundle, i2);
    }

    public boolean a(Context context, com.xiaomi.payment.l.c cVar, Bundle bundle, int i2) {
        if (context == null) {
            return false;
        }
        return a(new c(context), cVar, bundle, i2);
    }

    private boolean a(IEntry.a aVar, com.xiaomi.payment.l.c cVar, Bundle bundle, int i2) {
        boolean zA = false;
        if (cVar == null) {
            return false;
        }
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        Bundle bundleA = com.xiaomi.payment.l.b.a(cVar.h);
        if (bundleA != null) {
            bundle2.putAll(bundleA);
        }
        int i3 = C0106a.f3567a[cVar.f3647b.ordinal()];
        if (i3 == 1) {
            zA = a(cVar.f3646a, aVar, bundle2, i2);
        } else if (i3 == 2) {
            zA = a(cVar.f3646a, aVar, cVar.k, bundle2, i2);
        } else if (i3 == 3) {
            zA = a(cVar.f3646a, aVar, cVar.i, cVar.j, bundle2, i2);
        }
        if (zA || TextUtils.isEmpty(cVar.k)) {
            return zA;
        }
        com.xiaomi.payment.l.c.b bVar = cVar.f3647b;
        return (bVar == com.xiaomi.payment.l.c.b.LOCAL || bVar == com.xiaomi.payment.l.c.b.APP) ? a(cVar.f3646a, aVar, cVar.k, bundle2, i2) : zA;
    }

    private boolean a(String str, IEntry.a aVar, String str2, Bundle bundle, int i2) {
        if (aVar == null) {
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            Log.e(f3561b, "webApp failed, url is null");
            return false;
        }
        if (bundle != null) {
            str2 = com.xiaomi.payment.l.b.a(str2, bundle);
        }
        Intent intent = new Intent(aVar.getContext(), (Class<?>) MibiHybridActivity.class);
        intent.putExtra("com.miui.sdk.hybrid.extra.URL", str2);
        aVar.a(intent, i2);
        c(str);
        return true;
    }

    public boolean a(String str, Context context, String str2, Bundle bundle) {
        if (context == null) {
            return false;
        }
        return a(str, new c(context), str2, bundle, -1);
    }

    public boolean a(String str, Activity activity, String str2, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a(str, new b(activity), str2, bundle, i2);
    }

    public boolean a(String str, Fragment fragment, String str2, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a(str, new e(fragment), str2, bundle, i2);
    }

    private boolean a(String str, IEntry.a aVar, String str2, String str3, Bundle bundle, int i2) {
        if (aVar == null) {
            return false;
        }
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            Log.e(f3561b, "enterApp failed intentUri and packageName is null");
            return false;
        }
        boolean zA = a(aVar, str2, bundle, i2);
        boolean z = true;
        if (!zA && !v0.c(aVar.getContext(), str3) && !b0.b(aVar.getContext(), str3)) {
            Log.e(f3561b, "enterApp failed intentUri:" + str2 + " and PackageName = " + str3);
            z = false;
        }
        if (z) {
            c(str);
        }
        return z;
    }

    public boolean a(String str, Context context, String str2, String str3, Bundle bundle) {
        if (context == null) {
            return false;
        }
        return a(str, new c(context), str2, str3, bundle, -1);
    }

    public boolean a(String str, Fragment fragment, String str2, String str3, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a(str, new e(fragment), str2, str3, bundle, i2);
    }

    public boolean a(String str, Activity activity, String str2, String str3, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a(str, new b(activity), str2, str3, bundle, i2);
    }

    private boolean a(IEntry.a aVar, String str, Bundle bundle, int i2) {
        Intent intentB;
        if (aVar == null || TextUtils.isEmpty(str) || (intentB = v0.b(str)) == null) {
            return false;
        }
        if (bundle != null) {
            intentB.putExtras(bundle);
        }
        if (v0.b(aVar.getContext(), intentB)) {
            aVar.a(intentB, i2);
            return true;
        }
        return false;
    }

    public boolean a(String str, Activity activity, Bundle bundle, int i2) {
        if (activity == null) {
            return false;
        }
        return a(str, new b(activity), bundle, i2);
    }

    public boolean a(String str, Fragment fragment, Bundle bundle, int i2) {
        if (fragment == null) {
            return false;
        }
        return a(str, new e(fragment), bundle, i2);
    }

    public boolean a(String str, Context context, Bundle bundle) {
        if (context == null) {
            return false;
        }
        return a(str, new c(context), bundle, -1);
    }

    private boolean a(String str, IEntry.a aVar, Bundle bundle, int i2) {
        if (aVar == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            this.f3566a.get(str).f3570a.a(aVar, bundle, i2);
            c(str);
            return true;
        } catch (Exception e2) {
            Log.e(f3561b, "enter failed for id: " + str, e2);
            return false;
        }
    }

    public boolean a(Context context, String str) {
        if (this.f3566a.containsKey(str)) {
            return this.f3566a.get(str).f3570a.a(context);
        }
        return false;
    }
}
