package o2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.material.timepicker.TimeModel;
import com.miui.antispam.ui.activity.AddAntiSpamActivity;
import com.miui.antispam.ui.activity.CallLogListActivity;
import com.miui.permission.StoragePolicyContract;
import com.miui.securitycenter.Application;
import com.miui.securitycenter.R;
import f4.t;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import miui.cloud.CloudPushConstants;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.SubscriptionInfo;
import miui.telephony.SubscriptionManager;
import miui.telephony.phonenumber.CountryCode;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f52128a = Application.x().getString(R.string.activity_title_antispam);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f52129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static HashMap<String, String> f52130c;

    class a extends HashMap<String, String> {
        a() {
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f52131c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f52132d;

        b(Context context, String str) {
            this.f52131c = context;
            this.f52132d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.H(this.f52131c, this.f52132d);
        }
    }

    class c extends AsyncTask<Void, Void, Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f52133a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f52134b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f52135c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f52136d;

        c(String str, Context context, int i10, String str2) {
            this.f52133a = str;
            this.f52134b = context;
            this.f52135c = i10;
            this.f52136d = str2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:7:0x0050 A[Catch: all -> 0x0083, Exception -> 0x0085, TRY_LEAVE, TryCatch #1 {Exception -> 0x0085, blocks: (B:4:0x0027, B:6:0x002d, B:7:0x0050), top: B:20:0x0027, outer: #0 }] */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            ContentValues contentValues = new ContentValues();
            String strI = f.I(this.f52133a);
            Cursor cursorQuery = this.f52134b.getContentResolver().query(Uri.withAppendedPath(ExtraTelephony.FirewallLog.CONTENT_URI, strI), null, "type = 2", null, null);
            try {
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            long j10 = cursorQuery.getLong(0);
                            contentValues.put("reason", Integer.valueOf(this.f52135c));
                            contentValues.put("data1", this.f52136d);
                            this.f52134b.getContentResolver().update(ContentUris.withAppendedId(ExtraTelephony.FirewallLog.CONTENT_URI, j10), contentValues, null, null);
                        } else {
                            contentValues.put("number", strI);
                            contentValues.put(StoragePolicyContract.MOUNT_MODE_READ, (Integer) 1);
                            contentValues.put("type", (Integer) 2);
                            contentValues.put("reason", Integer.valueOf(this.f52135c));
                            contentValues.put("data1", this.f52136d);
                            this.f52134b.getContentResolver().insert(ExtraTelephony.FirewallLog.CONTENT_URI, contentValues);
                        }
                    } catch (Exception e10) {
                        Log.e("AntiSpamUtils", "Cursor exception when add message intercept log! ", e10);
                    }
                } else {
                    contentValues.put("number", strI);
                    contentValues.put(StoragePolicyContract.MOUNT_MODE_READ, (Integer) 1);
                    contentValues.put("type", (Integer) 2);
                    contentValues.put("reason", Integer.valueOf(this.f52135c));
                    contentValues.put("data1", this.f52136d);
                    this.f52134b.getContentResolver().insert(ExtraTelephony.FirewallLog.CONTENT_URI, contentValues);
                }
                return null;
            } finally {
                ti.e.a(cursorQuery);
            }
        }
    }

    class d implements Comparator<SubscriptionInfo> {
        d() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SubscriptionInfo subscriptionInfo, SubscriptionInfo subscriptionInfo2) {
            return subscriptionInfo.getSlotId() - subscriptionInfo2.getSlotId();
        }
    }

    static {
        f52129b = t.i() == 7;
        f52130c = new a();
    }

    public static boolean A() {
        int scaleMode = MiuiConfiguration.getScaleMode();
        return scaleMode == 14 || scaleMode == 15 || scaleMode == 11;
    }

    public static boolean B(String str) {
        return str.contains("86") || str.contains("852") || str.contains("853") || str.contains("886");
    }

    public static boolean C(String str) {
        return str.equals("-3") || str.equals("-2") || str.equals("-1");
    }

    public static boolean D(Context context, int i10) {
        return o2.a.q(context) && i10 == o2.a.g(context);
    }

    public static boolean E(Context context) {
        if (!Build.IS_INTERNATIONAL_BUILD) {
            return true;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SENDTO");
        intent.setData(Uri.parse("smsto:"));
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return false;
        }
        return "com.android.mms".equals(resolveInfoResolveActivity.activityInfo.packageName);
    }

    public static void F(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_read", (Integer) 1);
        context.getContentResolver().update(CallLog.Calls.CONTENT_URI, contentValues, "firewalltype <> 0 ", null);
    }

    public static void G(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StoragePolicyContract.MOUNT_MODE_READ, (Integer) 1);
        contentValues.put("seen", (Integer) 1);
        context.getContentResolver().update(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI, contentValues, null, null);
    }

    public static void H(Context context, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(StoragePolicyContract.MOUNT_MODE_READ, (Integer) 1);
        contentValues.put("seen", (Integer) 1);
        context.getContentResolver().update(j(str), contentValues, null, null);
    }

    public static String I(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.matches("[a-zA-Z]*-[a-zA-Z]*")) {
            return str.substring(str.indexOf("-"));
        }
        String strNormalizeNumber = str.contains("*") ? ExtraTelephony.normalizeNumber(str) : PhoneNumberUtils.PhoneNumber.parse(str).getNormalizedNumber(false, true);
        return TextUtils.isEmpty(strNormalizeNumber) ? str : strNormalizeNumber;
    }

    public static void J(Context context, String str, int i10, int i11, int i12) {
        ContentValues contentValues;
        ContentResolver contentResolver;
        Uri uriWithAppendedPath;
        String strI = I(str);
        if (TextUtils.isEmpty(strI)) {
            return;
        }
        if (!o2.a.j(context, i12)) {
            o2.a.t(context, i12, true);
        }
        Cursor cursorQuery = context.getContentResolver().query(ExtraTelephony.Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ?", new String[]{strI, String.valueOf(i11), String.valueOf(i12), String.valueOf(1)}, null);
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                try {
                    try {
                        long j10 = cursorQuery.getLong(0);
                        int i13 = cursorQuery.getInt(cursorQuery.getColumnIndex("state"));
                        int i14 = cursorQuery.getInt(cursorQuery.getColumnIndex("sync_dirty"));
                        if (i13 == 0 && i10 != 0) {
                            contentValues = new ContentValues();
                            contentValues.put("state", Integer.valueOf(i10 == 1 ? 2 : 1));
                            if (i14 == 3) {
                                contentValues.put("sync_dirty", (Integer) 2);
                            }
                            contentResolver = context.getContentResolver();
                            uriWithAppendedPath = Uri.withAppendedPath(ExtraTelephony.Phonelist.CONTENT_URI, String.valueOf(j10));
                        } else if (i10 == 0 || i13 == i10) {
                            if (i14 == 3 || i14 == 2) {
                                contentValues = new ContentValues();
                                contentValues.put("sync_dirty", (Integer) 1);
                                contentResolver = context.getContentResolver();
                                uriWithAppendedPath = Uri.withAppendedPath(ExtraTelephony.Phonelist.CONTENT_URI, String.valueOf(j10));
                            } else {
                                context.getContentResolver().delete(Uri.withAppendedPath(ExtraTelephony.Phonelist.CONTENT_URI, String.valueOf(j10)), null, null);
                            }
                        }
                        contentResolver.update(uriWithAppendedPath, contentValues, null, null);
                    } catch (Exception e10) {
                        Log.e("AntiSpamUtils", "Cursor exception in removeBlacklist(): ", e10);
                    }
                } catch (Throwable th2) {
                    cursorQuery.close();
                    throw th2;
                }
            }
            cursorQuery.close();
        }
    }

    public static void K(Context context, String str, String str2, Intent intent, boolean z10, int i10, String str3) {
        e4.b.C0409b c0409b = new e4.b.C0409b(context);
        c0409b.h(str);
        c0409b.g(str2);
        c0409b.v(Build.IS_INTERNATIONAL_BUILD ? R.drawable.security_small_icon : R.drawable.icon_antispam);
        c0409b.q(R.drawable.icon_antispam);
        c0409b.i(z10);
        c0409b.j(true);
        c0409b.l(2);
        c0409b.f(PendingIntent.getActivity(context, 0, intent, 201326592));
        c0409b.e("com.miui.antispam_new", f52128a);
        c0409b.r(i10);
        c0409b.c(str3);
        c0409b.p(true);
        c0409b.a().I();
    }

    public static void L(Context context, String str, int i10) {
        Intent intentA = f2.b.a(context, new Intent((String) ve.e.h("AntiSpamUtils", Intent.class, "ACTION_CALL_PRIVILEGED"), Uri.fromParts("tel", str, null)), f2.b.f45712a);
        if (i10 != -1) {
            SubscriptionManager.putSlotIdExtra(intentA, i10);
            intentA.putExtra("com.android.phone.extra.slot", i10);
        }
        intentA.setFlags(335544320);
        context.startActivity(intentA);
    }

    public static void M(Context context, String str, int i10) {
        Intent intent = new Intent(context, (Class<?>) CallLogListActivity.class);
        intent.putExtra("number", str);
        intent.putExtra("number_presentation", i10);
        context.startActivity(intent);
    }

    public static void N(Context context, String[] strArr, int i10, Integer[] numArr, int i11, int i12) {
        Intent intent = new Intent("miui.intent.action.ADD_FIREWALL");
        intent.setType("vnd.android.cursor.item/firewall-blacklist");
        intent.putExtra("numbers", strArr);
        intent.putExtra(AddAntiSpamActivity.f7357k, i10);
        intent.putExtra(AddAntiSpamActivity.f7359m, i11);
        intent.putExtra(AddAntiSpamActivity.f7356j, i12);
        intent.putExtra(AddAntiSpamActivity.f7358l, o2.a.D(numArr));
        context.startActivity(intent);
    }

    public static void O(Context context, String str) {
        Intent intent = new Intent("miui.intent.action.MARK_ANTISPAM");
        intent.putExtra("com.miui.yellowpage.extra.number", str);
        context.startActivity(intent);
    }

    public static void P(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", str, null));
        intent.setFlags(335544320);
        context.startActivity(intent);
    }

    public static void Q(Context context, String str, String str2) {
        f4.f.b(new b(context.getApplicationContext(), str));
        Intent intent = new Intent("android.intent.action.VIEW");
        if (t.F()) {
            t.J(intent);
        }
        intent.setClassName("com.android.mms", "com.android.mms.ui.BlockedConversationActivity");
        intent.putExtra("number", str);
        intent.putExtra("extraData", str2);
        context.startActivity(intent);
    }

    public static void R(Context context, String str, int i10, int i11) throws Throwable {
        String string;
        String string2;
        boolean z10;
        int i12;
        Resources resources = context.getResources();
        Intent intent = new Intent("miui.intent.action.SET_FIREWALL");
        intent.addFlags(67108864);
        intent.putExtra("notification_intercept_content", i10);
        intent.putExtra(":miui:starting_window_label", "");
        intent.putExtra("is_from_intercept_notification", true);
        intent.putExtra("notification_block_type", i11);
        if (i10 == 2) {
            int iD = d();
            string = context.getString(R.string.fw_blocked);
            string2 = context.getString(R.string.fw_blocked_count) + resources.getQuantityString(R.plurals.fw_blocked_count_sms, iD, Integer.valueOf(iD));
            z10 = true;
            i12 = 798;
        } else {
            int iR = r();
            String strM = m(context, str);
            string = context.getString(R.string.fw_blocked);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(context.getString(R.string.fw_blocked_count));
            int i13 = iR == 1 ? R.plurals.fw_blocked_count_call_one : R.plurals.fw_blocked_count_call;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(iR);
            if (!TextUtils.isEmpty(strM)) {
                str = strM;
            }
            objArr[1] = str;
            sb2.append(resources.getQuantityString(i13, iR, objArr));
            string2 = sb2.toString();
            z10 = true;
            i12 = 797;
        }
        K(context, string, string2, intent, z10, i12, null);
        e2.a.j("antispam_noti_action", i11 == 17 ? "overseas" : "mainland", "show");
        e2.a.j("antispam_notification", i10 == 2 ? "sms" : "call", "show");
    }

    public static void a(Context context, String str, int i10, String str2) {
        new c(str, context, i10, str2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static int b(int i10) {
        if (i10 == 1 || i10 == 2) {
            return i10;
        }
        return 1;
    }

    public static String c(String str) {
        try {
            return str.startsWith("+") ? String.format(Locale.getDefault(), "+%d", Long.valueOf(str.substring(1))) : String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Long.valueOf(str));
        } catch (Exception unused) {
            return str;
        }
    }

    private static int d() {
        g2.b.A(g2.b.d() + 1);
        return g2.b.d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.InputStream] */
    public static JSONArray e(Context context) throws Throwable {
        Throwable th2;
        InputStream inputStreamOpen;
        try {
            try {
                inputStreamOpen = context.getAssets().open("address.json");
                try {
                    JSONArray jSONArray = new JSONArray(ti.e.i(inputStreamOpen));
                    ti.e.b(inputStreamOpen);
                    return jSONArray;
                } catch (IOException e10) {
                    e = e10;
                    e.printStackTrace();
                    ti.e.b(inputStreamOpen);
                    return null;
                } catch (JSONException e11) {
                    e = e11;
                    e.printStackTrace();
                    ti.e.b(inputStreamOpen);
                    return null;
                }
            } catch (Throwable th3) {
                th2 = th3;
                ti.e.b(context);
                throw th2;
            }
        } catch (IOException e12) {
            e = e12;
            inputStreamOpen = null;
        } catch (JSONException e13) {
            e = e13;
            inputStreamOpen = null;
        } catch (Throwable th4) {
            th2 = th4;
            context = 0;
            ti.e.b(context);
            throw th2;
        }
    }

    public static String f(Context context) throws Throwable {
        Throwable th2;
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = context.getAssets().open("address.json");
            try {
                try {
                    String strI = ti.e.i(inputStreamOpen);
                    ti.e.b(inputStreamOpen);
                    return strI;
                } catch (IOException e10) {
                    e = e10;
                    Log.e("AntiSpamUtils", "IOException in getAddressesJsonString(): ", e);
                    ti.e.b(inputStreamOpen);
                    return null;
                }
            } catch (Throwable th3) {
                th2 = th3;
                ti.e.b(inputStreamOpen);
                throw th2;
            }
        } catch (IOException e11) {
            e = e11;
            inputStreamOpen = null;
        } catch (Throwable th4) {
            th2 = th4;
            inputStreamOpen = null;
            ti.e.b(inputStreamOpen);
            throw th2;
        }
    }

    public static String g(String str) {
        if (!TextUtils.isEmpty(str)) {
            String networkCountryCode = CountryCode.getNetworkCountryCode();
            String countryCode = PhoneNumberUtils.PhoneNumber.parse(str).getCountryCode();
            if (!TextUtils.isEmpty(countryCode) && countryCode.startsWith(networkCountryCode)) {
                return countryCode.substring(networkCountryCode.length());
            }
        }
        return "";
    }

    public static int h(Context context, int i10) {
        String str;
        String[] strArr;
        int i11 = 0;
        Cursor cursorQuery = null;
        try {
            try {
                if (!o2.a.p() || o2.a.o()) {
                    str = "type = ? AND sync_dirty <> ? AND sim_id = ?";
                    strArr = new String[]{"1", String.valueOf(1), String.valueOf(i10)};
                } else {
                    str = "type = ? AND sync_dirty <> ? AND sim_id = ? AND state != ?";
                    strArr = new String[]{"1", String.valueOf(1), String.valueOf(i10), String.valueOf(2)};
                }
                cursorQuery = context.getContentResolver().query(ExtraTelephony.Phonelist.CONTENT_URI, new String[]{"count(*)"}, str, strArr, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    i11 = cursorQuery.getInt(0);
                }
            } catch (Exception e10) {
                Log.e("AntiSpamUtils", "cursor error when get blacklist count. ", e10);
            }
            return i11;
        } finally {
            ti.e.a(cursorQuery);
        }
    }

    public static int i(Context context, String str) {
        Cursor cursorQuery = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, "PHONE_NUMBERS_EQUAL(number, '" + str + "', 0) AND firewalltype <> 0 ", null, null);
        if (cursorQuery == null) {
            return 0;
        }
        try {
            return cursorQuery.getCount();
        } finally {
            cursorQuery.close();
        }
    }

    private static Uri j(String str) {
        return ContentUris.withAppendedId(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI.buildUpon().appendQueryParameter("blocked_conv_addr", str).build(), 0L);
    }

    public static int k(Context context, String str) {
        Cursor cursorQuery = context.getContentResolver().query(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI, new String[]{"message_count"}, "PHONE_NUMBERS_EQUAL(address, '" + str + "', 0)", null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    return cursorQuery.getInt(0);
                }
            } finally {
                cursorQuery.close();
            }
        }
        return 0;
    }

    public static String l(Activity activity) {
        try {
            return (String) ve.f.i(activity, Activity.class, "mReferrer");
        } catch (Exception e10) {
            e10.printStackTrace();
            return null;
        }
    }

    public static String m(Context context, String str) throws Throwable {
        Cursor cursorQuery;
        if (C(str)) {
            return PhoneNumberUtils.getPresentationString(PhoneNumberUtils.getPresentation(str));
        }
        Cursor cursor = null;
        string = null;
        string = null;
        String string = null;
        try {
            cursorQuery = context.getContentResolver().query(Uri.withAppendedPath(j.a(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, j.b()), str), new String[]{"display_name"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            string = cursorQuery.getString(0);
                        }
                    } catch (Exception e10) {
                        e = e10;
                        Log.e("AntiSpamUtils", "Cursor err in queryContactName(): ", e);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorQuery;
                    ti.e.a(cursor);
                    throw th;
                }
            }
        } catch (Exception e11) {
            e = e11;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
            ti.e.a(cursor);
            throw th;
        }
        ti.e.a(cursorQuery);
        return string;
    }

    public static String n(Context context, long j10, boolean z10) {
        return !z10 ? DateUtils.formatDateTime(context, j10, 524305) : DateUtils.getRelativeTimeSpanString(context, j10, false).toString();
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002e A[PHI: r1
      0x002e: PHI (r1v2 java.lang.String) = (r1v0 java.lang.String), (r1v3 java.lang.String) binds: [B:11:0x001e, B:13:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    public static Pair<String, String> o(Context context, String str) throws Throwable {
        YellowPagePhone phoneInfo;
        String tag = null;
        if (str == null) {
            return new Pair<>(null, null);
        }
        String strM = m(context, str);
        if (TextUtils.isEmpty(strM) && (phoneInfo = YellowPageUtils.getPhoneInfo(context, str, false)) != null) {
            if (phoneInfo.isYellowPage()) {
                strM = phoneInfo.getYellowPageName();
                if (!TextUtils.equals(strM, phoneInfo.getTag())) {
                    tag = phoneInfo.getTag();
                }
            } else {
                tag = phoneInfo.getTag();
            }
        }
        return new Pair<>(strM, tag);
    }

    public static String p(String str) {
        return (Build.IS_INTERNATIONAL_BUILD || !y(str)) ? str : str.substring(6);
    }

    public static int q(Context context, int i10, int i11) {
        int i12 = 0;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(ExtraTelephony.Keyword.CONTENT_URI, new String[]{"count(*)"}, "type = ? AND sim_id = ? ", new String[]{String.valueOf(i10), String.valueOf(i11)}, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    i12 = cursorQuery.getInt(0);
                }
            } catch (Exception e10) {
                Log.e("AntiSpamUtils", "cursor error when get keyword count. ", e10);
            }
            return i12;
        } finally {
            ti.e.a(cursorQuery);
        }
    }

    private static int r() {
        g2.b.B(g2.b.e() + 1);
        return g2.b.e();
    }

    public static int s(Context context, int i10) {
        if (o2.a.k(context)) {
            return 1;
        }
        return b(i10 + 1);
    }

    public static List<SubscriptionInfo> t() {
        List<SubscriptionInfo> subscriptionInfoList = SubscriptionManager.getDefault().getSubscriptionInfoList();
        if (subscriptionInfoList == null || subscriptionInfoList.size() == 0) {
            return null;
        }
        if (subscriptionInfoList.size() > 1) {
            Collections.sort(subscriptionInfoList, new d());
        }
        return subscriptionInfoList;
    }

    public static String u(Context context) {
        Bundle bundleCall;
        try {
            bundleCall = context.getContentResolver().call(Uri.parse("content://com.miui.virtualsim.provider.virtualsimInfo"), "getCarrierName", (String) null, (Bundle) null);
        } catch (Exception e10) {
            Log.e("AntiSpamUtils", "getVirtualSimCarrierName e" + e10);
            bundleCall = null;
        }
        if (bundleCall == null) {
            return null;
        }
        return bundleCall.getString("carrierName");
    }

    public static int v(Context context, int i10) {
        String str;
        String[] strArr;
        int i11 = 0;
        Cursor cursorQuery = null;
        try {
            try {
                if (!o2.a.p() || o2.a.o()) {
                    str = "type = ? AND sync_dirty <> ? AND sim_id = ?";
                    strArr = new String[]{CloudPushConstants.CHANNEL_ID, String.valueOf(1), String.valueOf(i10)};
                } else {
                    str = "type = ? AND sync_dirty <> ? AND sim_id = ? AND state != ?";
                    strArr = new String[]{CloudPushConstants.CHANNEL_ID, String.valueOf(1), String.valueOf(i10), String.valueOf(2)};
                }
                cursorQuery = context.getContentResolver().query(ExtraTelephony.Phonelist.CONTENT_URI, new String[]{"count(*)"}, str, strArr, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    i11 = cursorQuery.getInt(0);
                }
            } catch (Exception e10) {
                Log.e("AntiSpamUtils", "cursor error when get whitelist count. ", e10);
            }
            return i11;
        } finally {
            ti.e.a(cursorQuery);
        }
    }

    public static boolean w(Activity activity) {
        String strL = l(activity);
        if (TextUtils.isEmpty(strL)) {
            return true;
        }
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(strL, 0);
            return packageInfo == null || (packageInfo.applicationInfo.flags & 1) == 0;
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    public static boolean x(Context context, String str) {
        Cursor cursorQuery = context.getContentResolver().query(Uri.withAppendedPath(j.a(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, j.b()), str), new String[]{"display_name"}, null, null, null);
        if (cursorQuery != null) {
            try {
                return cursorQuery.getCount() > 0;
            } catch (Exception e10) {
                Log.e("AntiSpamUtils", "Cursor err in queryContactName(): ", e10);
                return false;
            } finally {
                cursorQuery.close();
            }
        }
        return false;
    }

    public static boolean y(String str) {
        return !TextUtils.isEmpty(str) && str.length() > 6 && (str.startsWith("125831") || str.startsWith("125832") || str.startsWith("125833"));
    }

    public static boolean z(Context context, String str, int i10, int i11, int i12) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strI = I(str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Cursor cursorQuery = context.getContentResolver().query(ExtraTelephony.Phonelist.CONTENT_URI, null, "number = ? AND type = ? AND sim_id = ? AND sync_dirty <> ? ", new String[]{strI, String.valueOf(i11), String.valueOf(i12), String.valueOf(1)}, null);
        try {
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToNext()) {
                        int i13 = cursorQuery.getInt(cursorQuery.getColumnIndex("state"));
                        int i14 = cursorQuery.getInt(cursorQuery.getColumnIndex("sync_dirty"));
                        if (i13 != 0 && i13 != i10) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("state", (Integer) 0);
                            if (i14 == 3) {
                                contentValues.put("sync_dirty", (Integer) 2);
                            }
                            context.getContentResolver().update(Uri.withAppendedPath(ExtraTelephony.Phonelist.CONTENT_URI, String.valueOf(cursorQuery.getLong(0))), contentValues, null, null);
                        }
                        cursorQuery.close();
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        return true;
                    }
                } catch (Exception e10) {
                    Log.e("AntiSpamUtils", "Exception when check number in phoneList. ", e10);
                }
                cursorQuery.close();
            }
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return false;
        } catch (Throwable th2) {
            cursorQuery.close();
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th2;
        }
    }
}
