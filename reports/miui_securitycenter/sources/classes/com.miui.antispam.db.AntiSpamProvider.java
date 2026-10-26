package com.miui.antispam.db;

import android.accounts.Account;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.SystemSettings;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.miui.antispam.policy.SmartSmsFilterPolicy;
import com.miui.networkassistant.dual.Sim;
import com.miui.permission.PermissionContract;
import d2.b;
import dd.w;
import f4.t;
import f4.z;
import j2.c;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import miui.accounts.ExtraAccountManager;
import miui.cloud.CloudPushConstants;
import miui.cloud.common.XSimChangeNotification;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.telephony.phonenumber.CountryCode;
import o2.a;
import o2.f;
import o2.i;
import ti.e;

/* JADX INFO: loaded from: classes2.dex */
public class AntiSpamProvider extends ContentProvider {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final UriMatcher f7155e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f7156c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f7157d;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f7155e = uriMatcher;
        uriMatcher.addURI("antispam", "sms_judge", 9);
        uriMatcher.addURI("antispam", "call_judge", 10);
        uriMatcher.addURI("antispam", "url_judge", 13);
        uriMatcher.addURI("antispam", "service_num_judge", 14);
        uriMatcher.addURI("antispam", "call_transfer_intercept_judge", 21);
        uriMatcher.addURI("antispam", "phone_list", 1);
        uriMatcher.addURI("antispam", "phone_list/*", 2);
        uriMatcher.addURI("antispam", "log", 3);
        uriMatcher.addURI("antispam", "log/*", 4);
        uriMatcher.addURI("antispam", "log_sms", 5);
        uriMatcher.addURI("antispam", "logconversation", 6);
        uriMatcher.addURI("antispam", "keyword", 7);
        uriMatcher.addURI("antispam", "keyword/*", 8);
        uriMatcher.addURI("antispam", "mode", 15);
        uriMatcher.addURI("antispam", "mode/*", 16);
        uriMatcher.addURI("antispam", "sim", 17);
        uriMatcher.addURI("antispam", "sim/*", 18);
        uriMatcher.addURI("antispam", "unsynced_count", 11);
        uriMatcher.addURI("antispam", "synced_count", 12);
        uriMatcher.addURI("antispam", "allow_repeat", 22);
        uriMatcher.addURI("antispam", "account", 36);
        uriMatcher.addURI("antispam", "account/*", 37);
        uriMatcher.addURI("antispam", "markednumber", 30);
        uriMatcher.addURI("antispam", "markednumber/*", 31);
        uriMatcher.addURI("antispam", "cloudantispam", 32);
        uriMatcher.addURI("antispam", "cloudantispam/*", 33);
        uriMatcher.addURI("antispam", "category", 34);
        uriMatcher.addURI("antispam", "category/*", 35);
        uriMatcher.addURI("antispam", "report_sms", 19);
        uriMatcher.addURI("antispam", "report_sms_pending", 20);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strNormalizeNumber = ExtraTelephony.normalizeNumber(str);
        int length = strNormalizeNumber.length();
        if (length < 7) {
            return "substr(number, -7, 7)='" + strNormalizeNumber + "'";
        }
        return "substr(number, -7, 7)='" + strNormalizeNumber.substring(length - 7) + "' AND PHONE_NUMBERS_EQUAL(number, '" + strNormalizeNumber + "', 0)";
    }

    private int b(String[] strArr) {
        c cVar = new c(strArr[0], f.b(Integer.parseInt(strArr[1]) + 1), 2, null, "is_forward_call".equals(strArr[2]), "is_repeated_normal_call".equals(strArr[3]), "is_repeated_blocked_call".equals(strArr[4]));
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Log.e("AntiSpamProvider", "getCallBlockType callingIdentity = " + jClearCallingIdentity);
        Log.e("AntiSpamProvider", "networkCoutrycode=" + CountryCode.getNetworkCountryCode());
        Log.e("AntiSpamProvider", "areaNum=" + cVar.f47845l);
        int iB = this.f7157d.b(cVar);
        Binder.restoreCallingIdentity(jClearCallingIdentity);
        return iB;
    }

    private int c(String[] strArr) {
        c cVar = new c(strArr[0], f.s(getContext(), Integer.parseInt(strArr[2])), 1, strArr[1], false, false, false);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Log.e("AntiSpamProvider", "getSMSBlockType callingIdentity = " + jClearCallingIdentity);
        int iE = this.f7157d.e(cVar);
        Binder.restoreCallingIdentity(jClearCallingIdentity);
        return iE;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0060 A[PHI: r3
      0x0060: PHI (r3v2 java.lang.String) = (r3v1 java.lang.String), (r3v15 java.lang.String), (r3v15 java.lang.String) binds: [B:3:0x0042, B:5:0x0058, B:7:0x005b] A[DONT_GENERATE, DONT_INLINE]] */
    private Cursor d(SQLiteDatabase sQLiteDatabase, SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        Integer num;
        String[] strArr3 = {"number", "reason", "data1"};
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        Log.e("AntiSpamProvider", "getSmsLogCursor callingIdentity = " + jClearCallingIdentity);
        Cursor cursorQuery = getContext().getContentResolver().query(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI, strArr, null, null, str2);
        String str4 = "type = 2";
        if (str != null) {
            str4 = "type = 2 AND " + str;
            if (strArr2 == null || strArr2.length <= 0) {
                str3 = null;
            } else {
                str3 = strArr2[0];
            }
        } else {
            str3 = null;
        }
        String str5 = str4;
        int i10 = 1;
        Cursor cursorQuery2 = sQLiteQueryBuilder.query(sQLiteDatabase, strArr3, str5, str != null ? new String[]{str3} : null, null, null, null);
        if (cursorQuery == null || cursorQuery2 == null) {
            e.a(cursorQuery);
            e.a(cursorQuery2);
            return null;
        }
        HashMap map = new HashMap();
        while (cursorQuery2.moveToNext()) {
            try {
                try {
                    map.put(cursorQuery2.getString(0), new AbstractMap.SimpleEntry(Integer.valueOf(cursorQuery2.getInt(i10)), cursorQuery2.getString(2)));
                    i10 = 1;
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
            } catch (Throwable th2) {
                cursorQuery2.close();
                throw th2;
            }
        }
        cursorQuery2.close();
        ArrayList arrayList = new ArrayList();
        arrayList.add("_id");
        for (int i11 = 1; i11 < strArr.length; i11++) {
            arrayList.add(strArr[i11]);
        }
        arrayList.add("data1");
        arrayList.add("reason");
        MatrixCursor matrixCursor = new MatrixCursor((String[]) arrayList.toArray(new String[0]));
        while (cursorQuery.moveToNext() && (strArr2 == null || strArr2.length <= 1 || cursorQuery.getPosition() <= Integer.parseInt(strArr2[1]) - 1)) {
            try {
                try {
                    Map.Entry entry = (Map.Entry) map.remove(f.I(cursorQuery.getString(cursorQuery.getColumnIndex("address"))));
                    int iIntValue = 4;
                    if (entry != null || str == null || (str3 != null && Integer.parseInt(str3) == 4)) {
                        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
                        for (int i12 = 0; i12 < cursorQuery.getColumnCount(); i12++) {
                            rowBuilderNewRow.add(cursorQuery.getString(i12));
                        }
                        String str6 = "";
                        if (entry != null) {
                            num = (Integer) entry.getKey();
                            String str7 = (String) entry.getValue();
                            if (str7 != null) {
                                str6 = str7;
                            }
                            rowBuilderNewRow.add(str6);
                        } else {
                            rowBuilderNewRow.add("");
                            num = 4;
                        }
                        if (num != null) {
                            iIntValue = num.intValue();
                        }
                        rowBuilderNewRow.add(Integer.valueOf(iIntValue));
                    }
                } catch (NumberFormatException e11) {
                    e11.printStackTrace();
                }
            } catch (Throwable th3) {
                cursorQuery.close();
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw th3;
            }
        }
        cursorQuery.close();
        Binder.restoreCallingIdentity(jClearCallingIdentity);
        return matrixCursor;
    }

    private Uri e(SQLiteDatabase sQLiteDatabase, ContentValues contentValues, String str, Integer num, Integer num2) {
        if (num2 != null && !a.j(getContext(), num2.intValue())) {
            a.t(getContext(), num2.intValue(), true);
        }
        contentValues.put(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID, Integer.valueOf(num2 == null ? 1 : num2.intValue()));
        String[] strArr = new String[3];
        strArr[0] = str;
        strArr[1] = String.valueOf(num.intValue() == 1 ? 4 : 1);
        strArr[2] = String.valueOf(num2 == null ? 1 : num2.intValue());
        this.f7157d.g(sQLiteDatabase, "keyword", "data = ? AND type = ? AND sim_id = ?", strArr);
        int iDelete = sQLiteDatabase.delete("keyword", "data = ? AND type = ? AND sim_id = ?", strArr);
        Uri uri = null;
        if (iDelete > 0) {
            getContext().getContentResolver().notifyChange(ExtraTelephony.Keyword.CONTENT_URI, (ContentObserver) null, false);
        }
        long jInsert = sQLiteDatabase.insert("keyword", null, contentValues);
        if (jInsert > 0) {
            this.f7157d.h(str, num.intValue(), num2 != null ? num2.intValue() : 1);
            Uri uriWithAppendedId = ContentUris.withAppendedId(ExtraTelephony.Keyword.CONTENT_URI, jInsert);
            getContext().getContentResolver().notifyChange(ExtraTelephony.Keyword.CONTENT_URI, (ContentObserver) null, false);
            uri = uriWithAppendedId;
        }
        if (d2.a.f44060a) {
            Log.i("AntiSpamProvider", "insert URI_KEYWORD : data = " + str);
        }
        return uri;
    }

    private Uri f(SQLiteDatabase sQLiteDatabase, ContentValues contentValues, String str, int i10, int i11, Integer num) {
        if (num != null && !a.j(getContext(), num.intValue())) {
            a.t(getContext(), num.intValue(), true);
        }
        Uri uri = null;
        if (!f.z(getContext(), str, i10, i11, num == null ? 1 : num.intValue())) {
            contentValues.put(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID, Integer.valueOf(num == null ? 1 : num.intValue()));
            long jInsert = sQLiteDatabase.insert("phone_list", null, contentValues);
            if (jInsert > 0) {
                this.f7157d.k(str, i10, i11, num != null ? num.intValue() : 1);
                Account xiaomiAccount = ExtraAccountManager.getXiaomiAccount(getContext());
                if (xiaomiAccount != null && !contentValues.containsKey("record_id")) {
                    ContentResolver.requestSync(xiaomiAccount, "antispam", new Bundle());
                }
                Uri uri2 = a.d.f52094a;
                Uri uriWithAppendedId = ContentUris.withAppendedId(uri2, jInsert);
                getContext().getContentResolver().notifyChange(uri2, (ContentObserver) null, false);
                uri = uriWithAppendedId;
            }
            if (d2.a.f44060a) {
                Log.i("AntiSpamProvider", "insert URI_PHONELIST : number = " + str);
            }
        }
        return uri;
    }

    private boolean g() {
        String callingPackage;
        try {
            callingPackage = getCallingPackage();
        } catch (Exception e10) {
            Log.d("AntiSpamProvider", e10.toString());
            callingPackage = null;
        }
        return "com.android.mms".equals(callingPackage);
    }

    private boolean h() {
        if (this.f7156c) {
            return true;
        }
        boolean zE = z.E();
        this.f7156c = zE;
        return zE;
    }

    private boolean i(Uri uri) {
        String queryParameter = uri.getQueryParameter(PermissionContract.Method.GetPermissionFlags.EXTRA_PERMISSION_FLAG);
        return (queryParameter == null || !TextUtils.equals(queryParameter, "1") || Build.IS_INTERNATIONAL_BUILD || w.u()) ? false : true;
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> arrayList) {
        SQLiteDatabase writableDatabase = g2.c.a(getContext()).getWritableDatabase();
        if (writableDatabase.enableWriteAheadLogging()) {
            writableDatabase.beginTransactionNonExclusive();
        } else {
            writableDatabase.beginTransaction();
        }
        try {
            ContentProviderResult[] contentProviderResultArrApplyBatch = super.applyBatch(arrayList);
            writableDatabase.setTransactionSuccessful();
            return contentProviderResultArrApplyBatch;
        } catch (OperationApplicationException e10) {
            e10.printStackTrace();
            return null;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Bundle call(@NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        if ("getBlockKeyword".equals(str)) {
            SQLiteDatabase readableDatabase = g2.c.a(getContext()).getReadableDatabase();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables("fwlog");
            Cursor cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"data1"}, "number = ? AND type = 2", new String[]{str2}, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                String string = cursorQuery.getString(0);
                cursorQuery.close();
                Bundle bundle2 = new Bundle();
                bundle2.putString("blockKeyword", string);
                return bundle2;
            }
        } else if ("setMmsBlockType".equals(str) || "getMmsBlockType".equals(str)) {
            if (bundle == null) {
                return null;
            }
            int i10 = bundle.getInt(Sim.SIM_ID, 1);
            if (!"setMmsBlockType".equals(str)) {
                Bundle bundle3 = new Bundle();
                bundle3.putInt("blockType", g2.b.a(getContext(), "mms_mode", i10, 2));
                return bundle3;
            }
            g2.b.r(getContext(), "mms_mode", i10, bundle.getInt("blockType", 1));
        } else if ("initSmsEngine".equals(str)) {
            ((SmartSmsFilterPolicy) this.f7157d.a(j2.e.f47867w)).initSmsEngine(true);
        }
        return super.call(str, str2, bundle);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0098, code lost:
    
        if (r12 > 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009a, code lost:
    
        r13 = getContext().getContentResolver();
        r14 = miui.provider.ExtraTelephony.AntiSpamMode.CONTENT_URI;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a9, code lost:
    
        if (r12 > 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ce, code lost:
    
        if (r12 > 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d0, code lost:
    
        r13 = getContext().getContentResolver();
        r14 = miui.provider.ExtraTelephony.Keyword.CONTENT_URI;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0104, code lost:
    
        if (r12 > 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01ac, code lost:
    
        if (r12 > 0) goto L68;
     */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int delete(Uri uri, String str, String[] strArr) {
        int iDelete;
        ContentResolver contentResolver;
        Uri uri2;
        SQLiteDatabase writableDatabase = g2.c.a(getContext()).getWritableDatabase();
        int iMatch = f7155e.match(uri);
        if (iMatch == 1) {
            if (d2.a.f44060a) {
                Log.i("AntiSpamProvider", "delete URI_PHONELIST ");
            }
            if (g() && strArr.length == 3) {
                str = "number = ? AND type = ?";
                strArr = new String[]{strArr[0], strArr[2]};
            }
            this.f7157d.j(writableDatabase, "phone_list", str, strArr);
            iDelete = writableDatabase.delete("phone_list", str, strArr);
        } else if (iMatch == 2) {
            if (d2.a.f44060a) {
                Log.i("AntiSpamProvider", "delete URI_PHONELIST_ID ");
            }
            this.f7157d.j(writableDatabase, "phone_list", "_id = ? ", new String[]{uri.getLastPathSegment()});
            iDelete = writableDatabase.delete("phone_list", "_id = ? ", new String[]{uri.getLastPathSegment()});
            if (iDelete > 0) {
                Account xiaomiAccount = ExtraAccountManager.getXiaomiAccount(getContext());
                if (xiaomiAccount != null) {
                    ContentResolver.requestSync(xiaomiAccount, "antispam", new Bundle());
                }
                contentResolver = getContext().getContentResolver();
                uri2 = a.d.f52094a;
                contentResolver.notifyChange(uri2, (ContentObserver) null, false);
            }
        } else if (iMatch == 3) {
            iDelete = writableDatabase.delete("fwlog", str, strArr);
            if (iDelete > 0) {
                getContext().getContentResolver().notifyChange(a.c.f52091a, (ContentObserver) null, false);
                contentResolver = getContext().getContentResolver();
                uri2 = a.c.f52092b;
                contentResolver.notifyChange(uri2, (ContentObserver) null, false);
            }
        } else if (iMatch == 4) {
            iDelete = writableDatabase.delete("fwlog", "_id = ? ", new String[]{uri.getLastPathSegment()});
            if (iDelete > 0) {
                contentResolver = getContext().getContentResolver();
                uri2 = a.c.f52091a;
                contentResolver.notifyChange(uri2, (ContentObserver) null, false);
            }
        } else if (iMatch == 7) {
            if (d2.a.f44060a) {
                Log.i("AntiSpamProvider", "delete URI_KEYWORD ");
            }
            if (g() && strArr.length == 3) {
                str = "data = ? AND type = ?";
                strArr = new String[]{strArr[0], strArr[2]};
            }
            this.f7157d.g(writableDatabase, "keyword", str, strArr);
            iDelete = writableDatabase.delete("keyword", str, strArr);
        } else if (iMatch == 8) {
            if (d2.a.f44060a) {
                Log.i("AntiSpamProvider", "delete URI_KEYWORD_ID ");
            }
            this.f7157d.g(writableDatabase, "keyword", "_id = ? ", new String[]{uri.getLastPathSegment()});
            iDelete = writableDatabase.delete("keyword", "_id = ? ", new String[]{uri.getLastPathSegment()});
        } else if (iMatch == 15) {
            iDelete = writableDatabase.delete("mode", str, strArr);
        } else if (iMatch == 16) {
            iDelete = writableDatabase.delete("mode", "_id = ? ", new String[]{uri.getLastPathSegment()});
        } else if (iMatch == 18) {
            iDelete = writableDatabase.delete("sim", "_id = ? ", new String[]{uri.getLastPathSegment()});
            if (iDelete > 0) {
                contentResolver = getContext().getContentResolver();
                uri2 = ExtraTelephony.AntiSpamSim.CONTENT_URI;
                contentResolver.notifyChange(uri2, (ContentObserver) null, false);
            }
        } else {
            if (iMatch != 20) {
                throw new UnsupportedOperationException("Cannot delete that URL: " + uri);
            }
            iDelete = writableDatabase.delete("reportSmsPending", str, strArr);
            if (iDelete > 0) {
                contentResolver = getContext().getContentResolver();
                uri2 = d2.a.b.f44062b;
                contentResolver.notifyChange(uri2, (ContentObserver) null, false);
            }
        }
        return iDelete;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = f7155e.match(uri);
        if (iMatch == 1) {
            return "vnd.android.cursor.dir/antispam-phone_list";
        }
        if (iMatch == 2) {
            return "vnd.android.cursor.item/antispam-phone_list";
        }
        if (iMatch == 3) {
            return "vnd.android.cursor.dir/antispam-log";
        }
        if (iMatch == 4) {
            return "vnd.android.cursor.item/antispam-log";
        }
        if (iMatch == 7) {
            return "vnd.android.cursor.dir/antispam-keyword";
        }
        if (iMatch == 8) {
            return "vnd.android.cursor.item/antispam-keyword";
        }
        if (iMatch == 15) {
            return "vnd.android.cursor.dir/antispam-mode";
        }
        if (iMatch == 16) {
            return "vnd.android.cursor.item/antispam-mode";
        }
        switch (iMatch) {
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
                return "*/*";
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        String str;
        Uri uriWithAppendedId;
        ContentResolver contentResolver;
        Uri uri2;
        long jInsert;
        SQLiteDatabase writableDatabase = g2.c.a(getContext()).getWritableDatabase();
        int iMatch = f7155e.match(uri);
        boolean zG = g();
        Log.e("AntiSpamProvider", "insert callingIdentity = " + Binder.clearCallingIdentity());
        if (iMatch == 1) {
            if (i(uri)) {
                return Uri.parse("content://antispam/phone_list?cta=false");
            }
            String asString = contentValues.getAsString("number");
            String strI = f.I(asString);
            if (TextUtils.isEmpty(strI)) {
                return null;
            }
            if (TextUtils.equals(t.g(), "lm_cr")) {
                str = f.g(asString) + strI;
            } else {
                str = strI;
            }
            contentValues.put("number", str);
            contentValues.put("display_number", str);
            Integer asInteger = contentValues.getAsInteger("state");
            Integer asInteger2 = contentValues.getAsInteger("type");
            Integer asInteger3 = contentValues.getAsInteger(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID);
            int iIntValue = asInteger.intValue();
            int iIntValue2 = asInteger2.intValue();
            if (!zG) {
                return f(writableDatabase, contentValues, str, iIntValue, iIntValue2, asInteger3);
            }
            String str2 = str;
            f(writableDatabase, contentValues, str2, iIntValue, iIntValue2, 1);
            f(writableDatabase, contentValues, str2, asInteger.intValue(), asInteger2.intValue(), 2);
            return null;
        }
        if (iMatch == 3) {
            contentValues.put("number", ExtraTelephony.normalizeNumber(contentValues.getAsString("number")));
            long jInsert2 = writableDatabase.insert("fwlog", null, contentValues);
            if (jInsert2 <= 0) {
                return null;
            }
            Uri uri3 = a.c.f52091a;
            uriWithAppendedId = ContentUris.withAppendedId(uri3, jInsert2);
            getContext().getContentResolver().notifyChange(uri3, (ContentObserver) null, false);
            contentResolver = getContext().getContentResolver();
            uri2 = a.c.f52092b;
        } else {
            if (iMatch == 7) {
                if (i(uri)) {
                    return Uri.parse("content://antispam/phone_list?cta=false");
                }
                String asString2 = contentValues.getAsString("data");
                Integer asInteger4 = contentValues.getAsInteger("type");
                Integer asInteger5 = contentValues.getAsInteger(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID);
                if (!zG) {
                    return e(writableDatabase, contentValues, asString2, asInteger4, asInteger5);
                }
                e(writableDatabase, contentValues, asString2, asInteger4, 1);
                e(writableDatabase, contentValues, asString2, asInteger4, 2);
                return null;
            }
            if (iMatch == 15) {
                long jInsert3 = writableDatabase.insert("mode", null, contentValues);
                if (jInsert3 <= 0) {
                    return null;
                }
                uriWithAppendedId = ContentUris.withAppendedId(ExtraTelephony.AntiSpamMode.CONTENT_URI, jInsert3);
                contentResolver = getContext().getContentResolver();
                uri2 = ExtraTelephony.AntiSpamMode.CONTENT_URI;
            } else if (iMatch != 17) {
                if (iMatch == 19) {
                    jInsert = writableDatabase.insert("reportSms", null, contentValues);
                    if (jInsert <= 0) {
                        return null;
                    }
                    uri2 = d2.a.C0395a.f44061b;
                } else {
                    if (iMatch != 20) {
                        throw new UnsupportedOperationException("Cannot insert that URL: " + uri);
                    }
                    jInsert = writableDatabase.insert("reportSmsPending", null, contentValues);
                    if (jInsert <= 0) {
                        return null;
                    }
                    uri2 = d2.a.b.f44062b;
                }
                uriWithAppendedId = ContentUris.withAppendedId(uri2, jInsert);
                contentResolver = getContext().getContentResolver();
            } else {
                long jInsert4 = writableDatabase.insert("sim", null, contentValues);
                if (jInsert4 <= 0) {
                    return null;
                }
                uriWithAppendedId = ContentUris.withAppendedId(ExtraTelephony.AntiSpamSim.CONTENT_URI, jInsert4);
                contentResolver = getContext().getContentResolver();
                uri2 = ExtraTelephony.AntiSpamSim.CONTENT_URI;
            }
        }
        contentResolver.notifyChange(uri2, (ContentObserver) null, false);
        return uriWithAppendedId;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f7157d = b.c(getContext());
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0171  */
    /* JADX WARN: Code duplicated, block: B:55:0x0177 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:57:0x0179  */
    /* JADX WARN: Code duplicated, block: B:58:0x0194  */
    /* JADX WARN: Code duplicated, block: B:60:0x019a  */
    /* JADX WARN: Code duplicated, block: B:63:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:65:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:66:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:68:0x01ed A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:72:0x0203  */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursorCheckPrivacyAndReturnCursor;
        String strA;
        String str3;
        int i10;
        StringBuilder sb2;
        String str4;
        String str5;
        String[] strArr3;
        Cursor cursorQuery;
        ContentResolver contentResolver;
        Uri uri2;
        SQLiteDatabase readableDatabase = g2.c.a(getContext()).getReadableDatabase();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int iMatch = f7155e.match(uri);
        if ((iMatch == 3 || iMatch == 4 || iMatch == 6 || iMatch == 5) && (cursorCheckPrivacyAndReturnCursor = SystemSettings.Secure.checkPrivacyAndReturnCursor(getContext())) != null) {
            return cursorCheckPrivacyAndReturnCursor;
        }
        if (!h()) {
            Log.e("AntiSpamProvider", "update fail, uri = " + uri + ", turn on privacy no agree");
            return null;
        }
        switch (iMatch) {
            case 1:
            case 11:
            case 12:
                sQLiteQueryBuilder.setTables("phone_list");
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g() && iMatch == 1 && strArr2.length == 3) {
                                str5 = "number = ? AND type = ?";
                                strArr3 = new String[]{strArr2[0], strArr2[2]};
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery2 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery2.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery2;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 2:
                sQLiteQueryBuilder.setTables("phone_list");
                strA = a(uri.getLastPathSegment());
                sQLiteQueryBuilder.appendWhere(strA);
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery3 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery3.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery3;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 3:
            case 5:
                sQLiteQueryBuilder.setTables("fwlog");
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery4 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery4.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery4;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 4:
                sQLiteQueryBuilder.setTables("fwlog");
                strA = a(uri.getLastPathSegment());
                sQLiteQueryBuilder.appendWhere(strA);
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery5 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery5.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery5;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 6:
                sQLiteQueryBuilder.setTables("(select * from fwlog order by date ASC)");
                str3 = "number";
                i10 = 5;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery6 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery6.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery6;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 7:
                sQLiteQueryBuilder.setTables("keyword");
                strA = String.format("_id IN (SELECT _id FROM %s GROUP BY `%s`, %s)", "keyword", "data", XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID);
                sQLiteQueryBuilder.appendWhere(strA);
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery7 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery7.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery7;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 8:
                sQLiteQueryBuilder.setTables("keyword");
                sb2 = new StringBuilder();
                sb2.append("_id='");
                sb2.append(uri.getLastPathSegment());
                sb2.append("'");
                strA = sb2.toString();
                sQLiteQueryBuilder.appendWhere(strA);
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery8 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery8.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery8;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 9:
                if (!z.A() && f.E(getContext()) && strArr != null && strArr.length == 3) {
                    MatrixCursor matrixCursor = new MatrixCursor(new String[]{"smsBlockType"});
                    matrixCursor.addRow(new Integer[]{Integer.valueOf(c(strArr))});
                    return matrixCursor;
                }
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery9 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery9.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery9;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 10:
                if (strArr != null && strArr.length == 5) {
                    MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{"callBlockType"});
                    matrixCursor2.addRow(new Integer[]{Integer.valueOf(b(strArr))});
                    return matrixCursor2;
                }
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery10 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery10.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery10;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 13:
            case 14:
            case 21:
            default:
                Log.e("AntiSpamProvider", "Unknown query URI: " + uri);
                return null;
            case 15:
                sQLiteQueryBuilder.setTables("mode");
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery11 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery11.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery11;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 16:
                sQLiteQueryBuilder.setTables("mode");
                sb2 = new StringBuilder();
                sb2.append("_id='");
                sb2.append(uri.getLastPathSegment());
                sb2.append("'");
                strA = sb2.toString();
                sQLiteQueryBuilder.appendWhere(strA);
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery12 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery12.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery12;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 17:
            case 18:
                sQLiteQueryBuilder.setTables("sim");
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery13 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery13.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery13;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 19:
                str4 = "reportSms";
                sQLiteQueryBuilder.setTables(str4);
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery14 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery14.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery14;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 20:
                str4 = "reportSmsPending";
                sQLiteQueryBuilder.setTables(str4);
                i10 = 5;
                str3 = null;
                if (iMatch == i10) {
                    if (iMatch == 11) {
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty <> ? AND type IN (?,?,?)", new String[]{String.valueOf(3), "1", CloudPushConstants.CHANNEL_ID, "3"}, null, null, null);
                    } else {
                        if (iMatch == 12) {
                            if (!g()) {
                                str5 = str;
                                strArr3 = strArr2;
                            } else {
                                str5 = str;
                                strArr3 = strArr2;
                            }
                            Cursor cursorQuery15 = sQLiteQueryBuilder.query(readableDatabase, strArr, str5, strArr3, str3, null, str2);
                            cursorQuery15.setNotificationUri(getContext().getContentResolver(), uri);
                            return cursorQuery15;
                        }
                        cursorQuery = sQLiteQueryBuilder.query(readableDatabase, new String[]{"count(*)"}, "sync_dirty = ?", new String[]{String.valueOf(3)}, null, null, null);
                    }
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52097a;
                } else {
                    if (z.A()) {
                        return null;
                    }
                    cursorQuery = d(readableDatabase, sQLiteQueryBuilder, strArr, str, strArr2, str2);
                    contentResolver = getContext().getContentResolver();
                    uri2 = a.e.f52098b;
                }
                cursorQuery.setNotificationUri(contentResolver, uri2);
                return cursorQuery;
            case 22:
                int iIntValue = Integer.valueOf(strArr2[0]).intValue();
                MatrixCursor matrixCursor3 = new MatrixCursor(new String[]{"allow_repeat"});
                matrixCursor3.addRow(new Integer[]{Integer.valueOf(g2.b.k(iIntValue) ? 1 : 0)});
                return matrixCursor3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x01e5, code lost:
    
        if (r1 > 0) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0204, code lost:
    
        if (r1 > 0) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0206, code lost:
    
        r4 = null;
        getContext().getContentResolver().notifyChange(o2.a.c.f52091a, (android.database.ContentObserver) null, false);
        r2 = getContext().getContentResolver();
        r3 = o2.a.c.f52092b;
     */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int iUpdate;
        ContentResolver contentResolver;
        Uri uri2;
        ContentObserver contentObserver;
        int iUpdate2;
        ContentResolver contentResolver2;
        Uri uri3;
        if (!h()) {
            Log.e("AntiSpamProvider", "update fail, uri = " + uri + ", turn on privacy no agree");
            return 0;
        }
        SQLiteDatabase writableDatabase = g2.c.a(getContext()).getWritableDatabase();
        int iMatch = f7155e.match(uri);
        if (iMatch != 1) {
            if (iMatch == 2) {
                if (d2.a.f44060a) {
                    Log.i("AntiSpamProvider", "update URI_PHONELIST_ID ");
                }
                if (str != null || strArr != null) {
                    throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
                }
                this.f7157d.j(writableDatabase, "phone_list", "_id = ? ", new String[]{uri.getLastPathSegment()});
                int iUpdate3 = writableDatabase.update("phone_list", contentValues, "_id = ? ", new String[]{uri.getLastPathSegment()});
                if (iUpdate3 > 0) {
                    this.f7157d.l(writableDatabase, "phone_list", "_id = ? AND sync_dirty <> ? ", new String[]{uri.getLastPathSegment(), String.valueOf(1)});
                    Account xiaomiAccount = ExtraAccountManager.getXiaomiAccount(getContext());
                    if (xiaomiAccount != null && !contentValues.containsKey("e_tag")) {
                        ContentResolver.requestSync(xiaomiAccount, "antispam", new Bundle());
                    }
                    getContext().getContentResolver().notifyChange(a.d.f52094a, (ContentObserver) null, false);
                }
                return iUpdate3;
            }
            if (iMatch == 3) {
                iUpdate2 = writableDatabase.update("fwlog", contentValues, str, strArr);
            } else if (iMatch != 4) {
                if (iMatch == 13) {
                    if (strArr == null || strArr.length != 2) {
                        return 0;
                    }
                    return this.f7157d.f(strArr[0], strArr[1]);
                }
                if (iMatch == 14) {
                    if (strArr == null || strArr.length != 1) {
                        return 0;
                    }
                    return i.f(getContext(), strArr[0]) ? 1 : 0;
                }
                if (iMatch == 16) {
                    if (str != null || strArr != null) {
                        throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
                    }
                    iUpdate2 = writableDatabase.update("mode", contentValues, "_id = ? ", new String[]{uri.getLastPathSegment()});
                    if (iUpdate2 > 0) {
                        contentResolver2 = getContext().getContentResolver();
                        uri3 = ExtraTelephony.AntiSpamMode.CONTENT_URI;
                        ContentObserver contentObserver2 = null;
                        contentResolver2.notifyChange(uri3, contentObserver2, false);
                    }
                } else if (iMatch == 17) {
                    iUpdate2 = writableDatabase.update("sim", contentValues, str, strArr);
                    if (iUpdate2 > 0) {
                        contentResolver2 = getContext().getContentResolver();
                        uri3 = ExtraTelephony.AntiSpamSim.CONTENT_URI;
                        ContentObserver contentObserver3 = null;
                        contentResolver2.notifyChange(uri3, contentObserver3, false);
                    }
                } else {
                    if (iMatch == 21) {
                        if (strArr == null || strArr.length != 1) {
                            return 0;
                        }
                        return g2.b.g(f.b(Integer.valueOf(strArr[0]).intValue() + 1)) ? 1 : 0;
                    }
                    switch (iMatch) {
                        case 7:
                            if (d2.a.f44060a) {
                                Log.i("AntiSpamProvider", "update URI_KEYWORD ");
                            }
                            int iG = this.f7157d.g(writableDatabase, "keyword", str, strArr);
                            iUpdate = writableDatabase.update("keyword", contentValues, str, strArr);
                            if (iUpdate > 0) {
                                contentObserver = null;
                                this.f7157d.i(writableDatabase, "keyword", "_id = " + iG, null);
                                contentResolver = getContext().getContentResolver();
                                uri2 = ExtraTelephony.Keyword.CONTENT_URI;
                                contentResolver.notifyChange(uri2, contentObserver, false);
                            }
                            break;
                        case 8:
                            String lastPathSegment = uri.getLastPathSegment();
                            if (d2.a.f44060a) {
                                Log.i("AntiSpamProvider", "update URI_KEYWORD_ID ");
                            }
                            if (str != null || strArr != null) {
                                throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
                            }
                            this.f7157d.g(writableDatabase, "keyword", "_id = ? ", new String[]{lastPathSegment});
                            writableDatabase.execSQL("DELETE FROM keyword WHERE _id != ? AND data = ? AND sim_id = ?", new String[]{lastPathSegment, contentValues.getAsString("data"), contentValues.getAsString(XSimChangeNotification.BROADCAST_EXTRA_KEY_SIM_ID)});
                            iUpdate2 = writableDatabase.update("keyword", contentValues, "_id = ? ", new String[]{lastPathSegment});
                            if (iUpdate2 > 0) {
                                this.f7157d.i(writableDatabase, "keyword", "_id = ? ", new String[]{lastPathSegment});
                                contentResolver2 = getContext().getContentResolver();
                                uri3 = ExtraTelephony.Keyword.CONTENT_URI;
                                ContentObserver contentObserver4 = null;
                                contentResolver2.notifyChange(uri3, contentObserver4, false);
                            }
                            break;
                            break;
                        case 9:
                            if (z.A() || !f.E(getContext()) || strArr == null || strArr.length != 3) {
                                return 0;
                            }
                            return c(strArr);
                        case 10:
                            if (strArr == null || strArr.length != 5) {
                                return 0;
                            }
                            return b(strArr);
                        default:
                            throw new UnsupportedOperationException("Cannot update that URL: " + uri);
                    }
                }
            } else {
                if (str != null || strArr != null) {
                    throw new UnsupportedOperationException("Cannot update URL " + uri + " with a where clause");
                }
                iUpdate2 = writableDatabase.update("fwlog", contentValues, "_id = ? ", new String[]{uri.getLastPathSegment()});
            }
            return iUpdate2;
        }
        if (d2.a.f44060a) {
            Log.i("AntiSpamProvider", "update URI_PHONELIST ");
        }
        int iJ = this.f7157d.j(writableDatabase, "phone_list", str, strArr);
        iUpdate = writableDatabase.update("phone_list", contentValues, str, strArr);
        if (iUpdate > 0) {
            this.f7157d.l(writableDatabase, "phone_list", "_id = ? AND sync_dirty <> ? ", new String[]{String.valueOf(iJ), String.valueOf(1)});
            contentResolver = getContext().getContentResolver();
            uri2 = a.d.f52094a;
            contentObserver = null;
            contentResolver.notifyChange(uri2, contentObserver, false);
        }
        return iUpdate;
    }
}
