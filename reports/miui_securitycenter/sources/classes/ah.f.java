package ah;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;

/* JADX INFO: loaded from: classes3.dex */
public class f {
    public static boolean a(Context context, String str) {
        return b(context, str) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0080 A[PHI: r5
      0x0080: PHI (r5v3 android.database.Cursor) = (r5v2 android.database.Cursor), (r5v10 android.database.Cursor) binds: [B:4:0x0039, B:6:0x006a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:21:0x009d A[Catch: SQLiteException -> 0x00ea, TryCatch #0 {SQLiteException -> 0x00ea, blocks: (B:8:0x006d, B:10:0x0073, B:12:0x0077, B:13:0x0079, B:17:0x0081, B:18:0x0096, B:24:0x00aa, B:25:0x00c5, B:27:0x00cb, B:28:0x00cf, B:30:0x00d5, B:36:0x00e2, B:32:0x00da, B:35:0x00e0, B:21:0x009d, B:23:0x00a8, B:37:0x00e6), top: B:45:0x006d }] */
    /* JADX WARN: Code duplicated, block: B:28:0x00cf A[Catch: SQLiteException -> 0x00ea, TryCatch #0 {SQLiteException -> 0x00ea, blocks: (B:8:0x006d, B:10:0x0073, B:12:0x0077, B:13:0x0079, B:17:0x0081, B:18:0x0096, B:24:0x00aa, B:25:0x00c5, B:27:0x00cb, B:28:0x00cf, B:30:0x00d5, B:36:0x00e2, B:32:0x00da, B:35:0x00e0, B:21:0x009d, B:23:0x00a8, B:37:0x00e6), top: B:45:0x006d }] */
    /* JADX WARN: Code duplicated, block: B:30:0x00d5 A[Catch: SQLiteException -> 0x00ea, TryCatch #0 {SQLiteException -> 0x00ea, blocks: (B:8:0x006d, B:10:0x0073, B:12:0x0077, B:13:0x0079, B:17:0x0081, B:18:0x0096, B:24:0x00aa, B:25:0x00c5, B:27:0x00cb, B:28:0x00cf, B:30:0x00d5, B:36:0x00e2, B:32:0x00da, B:35:0x00e0, B:21:0x009d, B:23:0x00a8, B:37:0x00e6), top: B:45:0x006d }] */
    /* JADX WARN: Code duplicated, block: B:31:0x00d8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x00da A[Catch: SQLiteException -> 0x00ea, TryCatch #0 {SQLiteException -> 0x00ea, blocks: (B:8:0x006d, B:10:0x0073, B:12:0x0077, B:13:0x0079, B:17:0x0081, B:18:0x0096, B:24:0x00aa, B:25:0x00c5, B:27:0x00cb, B:28:0x00cf, B:30:0x00d5, B:36:0x00e2, B:32:0x00da, B:35:0x00e0, B:21:0x009d, B:23:0x00a8, B:37:0x00e6), top: B:45:0x006d }] */
    /* JADX WARN: Code duplicated, block: B:33:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:35:0x00e0 A[Catch: SQLiteException -> 0x00ea, TryCatch #0 {SQLiteException -> 0x00ea, blocks: (B:8:0x006d, B:10:0x0073, B:12:0x0077, B:13:0x0079, B:17:0x0081, B:18:0x0096, B:24:0x00aa, B:25:0x00c5, B:27:0x00cb, B:28:0x00cf, B:30:0x00d5, B:36:0x00e2, B:32:0x00da, B:35:0x00e0, B:21:0x009d, B:23:0x00a8, B:37:0x00e6), top: B:45:0x006d }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00e6 A[Catch: SQLiteException -> 0x00ea, LOOP:1: B:18:0x0096->B:37:0x00e6, LOOP_END, TRY_LEAVE, TryCatch #0 {SQLiteException -> 0x00ea, blocks: (B:8:0x006d, B:10:0x0073, B:12:0x0077, B:13:0x0079, B:17:0x0081, B:18:0x0096, B:24:0x00aa, B:25:0x00c5, B:27:0x00cb, B:28:0x00cf, B:30:0x00d5, B:36:0x00e2, B:32:0x00da, B:35:0x00e0, B:21:0x009d, B:23:0x00a8, B:37:0x00e6), top: B:45:0x006d }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x00e2 A[SYNTHETIC] */
    public static int b(Context context, String str) {
        int i10;
        Cursor cursorQuery;
        Cursor cursorQuery2;
        int i11;
        String str2 = "+86" + str;
        int i12 = 0;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Uri uri = Uri.parse("content://sms/");
            String[] strArr = {"type", "thread_id"};
            Cursor cursorQuery3 = contentResolver.query(uri, strArr, "address=? or address=?", new String[]{str, str2}, null);
            if (cursorQuery3.moveToFirst()) {
                int columnIndex = cursorQuery3.getColumnIndex("type");
                long j10 = cursorQuery3.getLong(cursorQuery3.getColumnIndex("thread_id"));
                cursorQuery3.close();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(j10);
                cursorQuery3 = contentResolver.query(uri, strArr, "thread_id=?", new String[]{sb2.toString()}, null);
                if (cursorQuery3.moveToFirst()) {
                    i10 = 0;
                    do {
                        try {
                            int i13 = cursorQuery3.getInt(columnIndex);
                            if (i13 == 1) {
                                i10 |= 1;
                            }
                            if (i13 == 2) {
                                i10 |= 2;
                            }
                        } catch (SQLiteException e10) {
                            e = e10;
                            i12 = i10;
                            Log.e("SQLiteException in queryAddress", e.getMessage());
                            i10 = i12;
                        }
                    } while (cursorQuery3.moveToNext());
                } else {
                    i10 = 0;
                }
                cursorQuery3.close();
                cursorQuery = contentResolver.query(uri, new String[]{TtmlNode.TAG_BODY}, null, null, null);
                cursorQuery.moveToFirst();
                while (!cursorQuery.isAfterLast()) {
                    if (cursorQuery.getString(0).indexOf(str) != -1) {
                        i10 |= 4;
                        break;
                    }
                    cursorQuery.moveToNext();
                }
                cursorQuery.close();
                cursorQuery2 = contentResolver.query(CallLog.Calls.CONTENT_URI, new String[]{"type"}, "number=? or number=?", new String[]{str, str2}, "date DESC");
                cursorQuery2.moveToFirst();
                while (!cursorQuery2.isAfterLast()) {
                    i11 = cursorQuery2.getInt(0);
                    if (i11 == 1) {
                        i10 |= 32;
                    } else if (i11 == 2) {
                        i10 |= 64;
                    } else if (i11 == 3) {
                        i10 |= 16;
                    }
                    cursorQuery2.moveToNext();
                }
                cursorQuery2.close();
            } else {
                i10 = 0;
                cursorQuery3.close();
                cursorQuery = contentResolver.query(uri, new String[]{TtmlNode.TAG_BODY}, null, null, null);
                cursorQuery.moveToFirst();
                while (!cursorQuery.isAfterLast()) {
                    if (cursorQuery.getString(0).indexOf(str) != -1) {
                        i10 |= 4;
                        break;
                    }
                    cursorQuery.moveToNext();
                }
                cursorQuery.close();
                cursorQuery2 = contentResolver.query(CallLog.Calls.CONTENT_URI, new String[]{"type"}, "number=? or number=?", new String[]{str, str2}, "date DESC");
                cursorQuery2.moveToFirst();
                while (!cursorQuery2.isAfterLast()) {
                    i11 = cursorQuery2.getInt(0);
                    if (i11 == 1) {
                        i10 |= 32;
                    } else if (i11 == 2) {
                        i10 |= 64;
                    } else if (i11 == 3) {
                        i10 |= 16;
                    }
                    cursorQuery2.moveToNext();
                }
                cursorQuery2.close();
            }
        } catch (SQLiteException e11) {
            e = e11;
        }
        Log.e("queryAddress result:", Long.toHexString(i10));
        return i10;
    }
}
