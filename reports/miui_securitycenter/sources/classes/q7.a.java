package q7;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.miui.securitycenter.R;
import h7.c0;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Uri f53098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Uri f53099b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f53100c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f53101d;

    static {
        Uri uri = Uri.parse("content://com.xiaomi.aiasst.vision");
        f53098a = uri;
        f53099b = Uri.withAppendedPath(uri, "subtitleState");
        f53100c = false;
        f53101d = true;
    }

    public static void a(Context context, String str) {
        if (c0.m(context) && f53100c) {
            f53100c = false;
            b(context, str);
        }
    }

    public static void b(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.xiaomi.aiasst.vision", "com.xiaomi.aiasst.vision.control.translation.AiTranslateService"));
            Bundle bundle = new Bundle();
            bundle.putString("from", str);
            bundle.putString("extra", "close");
            bundle.putString("floatingWindowType", "startAiSubtitlesWindow");
            intent.putExtras(bundle);
            context.startService(intent);
            Log.i("AiSubTitleUtils", "close AiSubTitles. channel = " + str);
        } catch (Exception e10) {
            Log.i("AiSubTitleUtils", "closeAiAssistantFeature fail " + str + ", e = " + e10);
        }
    }

    public static boolean c(Context context, String str) {
        int columnIndex;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(e(str), null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("value")) >= 0) {
                    return cursorQuery.getInt(columnIndex) == 1;
                }
            } catch (Exception e10) {
                Log.e("AiSubTitleUtils", "getPPTTranslationStatus FAIL " + e10);
            }
            return false;
        } finally {
            ti.e.a(cursorQuery);
        }
    }

    public static int d() {
        return gj.a.f46691a ? R.string.vtb_ai_subtitle_intl : R.string.vtb_ai_subtitle;
    }

    private static Uri e(String str) {
        if (TextUtils.equals(str, "conference_toolbox_screen_translate")) {
            return Uri.parse("content://com.xiaomi.aiasst.vision/screenTranslateState");
        }
        if (TextUtils.equals(str, "video_toolbox") || TextUtils.equals(str, "conference_toolbox")) {
            return f53099b;
        }
        throw new IllegalArgumentException("error channel : " + str);
    }

    public static boolean f() {
        try {
            try {
                Cursor cursorQuery = com.miui.common.e.d().getContentResolver().query(f53099b, null, null, null, null);
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    f53101d = false;
                } else {
                    f53101d = true;
                    int columnIndex = cursorQuery.getColumnIndex("value");
                    if (columnIndex >= 0) {
                        boolean z10 = cursorQuery.getInt(columnIndex) != 0;
                        ti.e.a(cursorQuery);
                        return z10;
                    }
                }
                ti.e.a(cursorQuery);
                return false;
            } catch (Exception unused) {
                f53101d = false;
                ti.e.a(null);
                return false;
            }
        } catch (Throwable th2) {
            ti.e.a(null);
            throw th2;
        }
    }

    public static boolean g() {
        return f53101d;
    }

    public static boolean h(Context context) {
        int columnIndex;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(e("conference_toolbox_screen_translate"), null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("value")) >= 0) {
                    int i10 = cursorQuery.getInt(columnIndex);
                    return i10 == 0 || i10 == 1;
                }
            } catch (Exception e10) {
                Log.e("AiSubTitleUtils", "isSupportPPTTranslation fail " + e10);
            }
            return false;
        } finally {
            ti.e.a(cursorQuery);
        }
    }

    public static void i(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.xiaomi.aiasst.vision", "com.xiaomi.aiasst.vision.control.translation.AiTranslateService"));
            Bundle bundle = new Bundle();
            bundle.putString("from", str);
            bundle.putString("floatingWindowType", "startAiSubtitlesWindow");
            intent.putExtras(bundle);
            context.startService(intent);
            Log.i("AiSubTitleUtils", "start AiSubTitles. channel = " + str);
        } catch (Exception e10) {
            Log.i("AiSubTitleUtils", "startAIAssistantFeature fail " + str + ", e: " + e10);
        }
    }

    public static void j(Context context, String str) {
        if (c0.m(context)) {
            f53100c = true;
            i(context, str);
        }
    }
}
