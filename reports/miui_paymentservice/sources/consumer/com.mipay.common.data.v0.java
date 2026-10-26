package com.mipay.common.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes.dex */
public class v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3141a = "Utils";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3142b = "mibi_channel_id";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f3143c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f3144d = 300000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f3145e = 1000;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f3146f = "400-100-3399";

    /* JADX INFO: compiled from: Utils.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3147a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3148b;
    }

    private v0() {
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (str.charAt(str.length() - 1) == '/') {
            str = str.substring(0, str.length() - 1);
        }
        if (str2.charAt(0) == '/') {
            str2 = str2.substring(1);
        }
        return str + "/" + str2;
    }

    public static synchronized int b() {
        int i;
        i = f3143c;
        f3143c = i + 1;
        return i;
    }

    public static String c(long j) {
        if (j % 100 == 0) {
            return String.format("%d", Long.valueOf(j / 100));
        }
        if (j % 10 == 0) {
            double d2 = j;
            Double.isNaN(d2);
            return String.format("%.1f", Double.valueOf(d2 / 100.0d));
        }
        double d3 = j;
        Double.isNaN(d3);
        return String.format("%.2f", Double.valueOf(d3 / 100.0d));
    }

    public static boolean d(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String e(Context context, String str) throws Throwable {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = context.getAssets().open(str);
            try {
                StringBuilder sb = new StringBuilder();
                Scanner scanner = new Scanner(inputStreamOpen);
                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine());
                }
                scanner.close();
                String string = sb.toString();
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException unused) {
                    }
                }
                return string;
            } catch (IOException unused2) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (IOException unused5) {
            inputStreamOpen = null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
        }
    }

    public static boolean f(Context context) {
        try {
            return ((Boolean) Class.forName("android.app.Activity").getMethod("isInMultiWindowMode", new Class[0]).invoke(context, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean g(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) == 2;
    }

    public static boolean h(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean i(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.isActiveNetworkMetered();
    }

    public static boolean j(Context context) {
        return c() ? !g(context) : h0.h();
    }

    public static boolean k(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static void l(Context context) {
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    public static a b(long j) {
        a aVar = new a();
        String strC = c(j);
        int iIndexOf = strC.indexOf(".");
        if (iIndexOf == -1) {
            aVar.f3147a = strC;
            aVar.f3148b = "";
        } else {
            aVar.f3147a = strC.substring(0, iIndexOf);
            aVar.f3148b = strC.substring(iIndexOf + 1);
        }
        return aVar;
    }

    public static boolean d() {
        return h0.h();
    }

    public static boolean c(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public static boolean d(Context context, String str) {
        if (context == null) {
            return false;
        }
        Intent intentB = b(str);
        if (!b(context, intentB)) {
            return false;
        }
        context.startActivity(intentB);
        return true;
    }

    public static boolean c(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                try {
                    if (!(context instanceof Activity)) {
                        launchIntentForPackage.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                    }
                    context.startActivity(launchIntentForPackage);
                    return true;
                } catch (Exception e2) {
                    Log.e(f3141a, "Utils launchApp Exception ", e2);
                }
            }
            return false;
        } catch (Exception e3) {
            Log.e(f3141a, "Utils launchApp getLaunchIntentForPackage exception ", e3);
            return false;
        }
    }

    public static int a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    public static void b(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    public static boolean e(Context context) {
        boolean z = false;
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                z = true;
            }
        } catch (Exception unused) {
        }
        Log.d(f3141a, "isDebugBuild:" + z);
        return z;
    }

    public static String a(long j) {
        double d2 = j;
        Double.isNaN(d2);
        return String.format("%.2f", Double.valueOf(d2 / 100.0d));
    }

    public static void a(Context context, View view, boolean z) {
        if (view == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getApplicationContext().getSystemService("input_method");
        if (z) {
            inputMethodManager.showSoftInput(view, 1);
        } else {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void b(Context context, String str, long j) {
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    public static boolean c() {
        return "cetus".equals(Build.DEVICE);
    }

    public static void a(Context context, View view) {
        if (view == null || context == null) {
            return;
        }
        ((InputMethodManager) context.getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean b(Context context, String str) {
        try {
            return context.getPackageManager().getLaunchIntentForPackage(str) != null;
        } catch (Exception e2) {
            Log.e(f3141a, "Utils isAppExists Exception ", e2);
            return false;
        }
    }

    public static boolean b(Activity activity) {
        return new Intent("android.intent.action.DIAL", Uri.parse("tel:400-100-3399")).resolveActivity(activity.getPackageManager()) != null;
    }

    public static boolean a(Context context, String str, boolean z) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }

    public static Intent b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Intent.parseUri(str, 1);
            } catch (URISyntaxException e2) {
                Log.e(f3141a, "Utils parseIntentUri URISyntaxException ", e2);
            }
        }
        return null;
    }

    public static long a(Context context, String str, long j) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(str, j);
    }

    public static long[] a(List<Long> list) {
        long[] jArr = new long[list.size()];
        Iterator<Long> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        return jArr;
    }

    @SuppressLint({"MissingPermission"})
    public static String b(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            if (telephonyManager.getSimState() == 5) {
                return telephonyManager.getLine1Number();
            }
            return null;
        } catch (Exception e2) {
            e0.a(f3141a, "get sim failed : " + e2.getMessage());
            return null;
        }
    }

    public static long[] a(Set<Long> set) {
        long[] jArr = new long[set.size()];
        Iterator<Long> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        return jArr;
    }

    public static void b(Session session) {
        SharedPreferences.Editor editorEdit = session.i().edit();
        editorEdit.remove(f.O0);
        editorEdit.apply();
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0056 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x0058 A[Catch: IOException -> 0x005b, TRY_LEAVE, TryCatch #6 {IOException -> 0x005b, blocks: (B:39:0x0053, B:41:0x0058), top: B:51:0x0053 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static boolean a(Context context, String str, String str2) throws Throwable {
        InputStream inputStreamOpen;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        fileOutputStream2 = null;
        InputStream inputStream = null;
        boolean z = false;
        try {
            try {
                inputStreamOpen = context.getAssets().open(str);
                try {
                    File file = new File(str2);
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStreamOpen.read(bArr);
                            if (i <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i);
                        }
                        z = true;
                        if (inputStreamOpen != null) {
                            inputStreamOpen.close();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        inputStream = inputStreamOpen;
                        try {
                            Log.e(f3141a, "Utils retrieveFileFromAssets IOException ", e);
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream != null) {
                            }
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            inputStreamOpen = inputStream;
                            fileOutputStream2 = fileOutputStream;
                            if (inputStreamOpen != null) {
                                try {
                                    inputStreamOpen.close();
                                    if (fileOutputStream2 != null) {
                                        fileOutputStream2.close();
                                    }
                                } catch (IOException unused) {
                                    throw th;
                                }
                            } else if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream;
                        if (inputStreamOpen != null) {
                            inputStreamOpen.close();
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                        } else if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                    } else if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } catch (IOException e4) {
            e = e4;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamOpen = null;
        }
        fileOutputStream.close();
        return z;
    }

    public static boolean b(Context context, Intent intent) {
        return (context == null || intent == null || context.getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    public static boolean a(Object... objArr) {
        for (Object obj : objArr) {
            b.b.a.a(obj);
        }
        return true;
    }

    public static boolean a(String... strArr) {
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static String a(CharSequence charSequence, Object... objArr) {
        return TextUtils.join(charSequence, objArr);
    }

    public static void a(Context context, String str, String str2, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str3) {
        a(context, str, str2, pendingIntent, pendingIntent2, null, b(), 0, str3);
    }

    public static void a(Context context, String str, String str2, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str3, int i, int i2, String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Notification.Builder autoCancel = new Notification.Builder(context).setSmallIcon(com.mipay.common.b.h.mibi_ic_milicenter_status_bar).setLargeIcon(BitmapFactory.decodeResource(context.getResources(), com.mipay.common.b.h.mibi_ic_milicenter)).setTicker(str).setContentTitle(str).setContentText(str2).setContentIntent(pendingIntent).setDeleteIntent(pendingIntent2).setAutoCancel(true);
        if (i2 == 2) {
            autoCancel.setOngoing(true);
        } else if (i2 == 8) {
            autoCancel.setOnlyAlertOnce(true);
        } else if (i2 == 16) {
            autoCancel.setAutoCancel(true);
        }
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            autoCancel.setChannelId(f3142b);
            if (notificationManager.getNotificationChannel(f3142b) == null) {
                NotificationChannel notificationChannel = new NotificationChannel(f3142b, str4, 3);
                notificationChannel.setDescription(str4);
                notificationChannel.enableVibration(true);
                notificationChannel.setLockscreenVisibility(1);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(str3, i, autoCancel.build());
    }

    public static void a(Context context, String str) {
        ((NotificationManager) context.getApplicationContext().getSystemService("notification")).cancel(str, 0);
    }

    public static boolean a(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!PhoneNumberUtils.isDialable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void a(Activity activity, String str) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }

    public static void a(Activity activity) {
        a(activity, f3146f);
    }

    public static boolean a(Context context, Intent intent) {
        ActivityInfo activityInfoResolveActivityInfo = intent.resolveActivityInfo(context.getPackageManager(), 0);
        if (activityInfoResolveActivityInfo == null) {
            return false;
        }
        return TextUtils.equals(context.getPackageName(), activityInfoResolveActivityInfo.packageName);
    }

    public static boolean a(Session session) {
        return session.i().getBoolean(f.O0, false);
    }

    public static void a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("current thread is not main");
        }
    }
}
