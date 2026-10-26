package com.ts.faceauth.util;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtil {
    private static final boolean DEBUG = true;
    public static final String TAG = "FaceAuthManager";

    public static void v(String str, String str2) {
        Log.v(str, str2);
    }

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }
}
