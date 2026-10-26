package com.quicinc.voice.activation.engineservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import p003d.g;
import p014o.k;

/* JADX INFO: loaded from: classes.dex */
public class ForegroundService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f526a;

    public static boolean b() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 == 31 || i2 == 32;
    }

    public static boolean c() {
        return b() && d();
    }

    public static boolean d() {
        String str = Build.MANUFACTURER;
        return "OnePlus".equalsIgnoreCase(str) || "OPPO".equalsIgnoreCase(str) || "realme".equalsIgnoreCase(str) || "Xiaomi".equalsIgnoreCase(str);
    }

    public static void e() {
        f("ACTION_ALWAYS_ON");
    }

    public static void f(String str) {
        Context contextD = g.d();
        Intent intent = new Intent(contextD, (Class<?>) ForegroundService.class);
        intent.setAction(str);
        contextD.startService(intent);
    }

    public static void g() {
        f("ACTION_START_NORMAL_RECORDING");
    }

    public static void h() {
        if (c()) {
            g.r().l();
        } else {
            f("ACTION_START_RECORDING_ALGORITHM");
        }
    }

    public static void i() {
        f("ACTION_STOP_FOREGROUND");
    }

    public static void j() {
        f("ACTION_STOP_NORMAL_RECORDING");
    }

    public static void k() {
        if (c()) {
            g.r().o();
        } else {
            f("ACTION_STOP_RECORDING_ALGORITHM");
        }
    }

    public final Notification a() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(new NotificationChannel("QVANotification", "Foreground Service Channel", 2));
        }
        return new Notification.Builder(getApplicationContext(), "QVANotification").build();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        String action = intent != null ? intent.getAction() : null;
        if ("ACTION_ALWAYS_ON".equals(action)) {
            startForeground(1, a());
            return 2;
        }
        if ("ACTION_START_RECORDING_ALGORITHM".equals(action)) {
            startForeground(1, a());
            g.r().l();
            return 2;
        }
        if ("ACTION_STOP_RECORDING_ALGORITHM".equals(action)) {
            g.r().o();
            stopForeground(true);
            return 2;
        }
        if ("ACTION_STOP_FOREGROUND".equals(action)) {
            stopForeground(true);
            return 2;
        }
        if ("ACTION_START_NORMAL_RECORDING".equals(action)) {
            if (!c()) {
                startForeground(1, a());
            }
            if (this.f526a == null) {
                this.f526a = new k(this);
            }
            this.f526a.d();
            return 2;
        }
        if (!"ACTION_STOP_NORMAL_RECORDING".equals(action)) {
            return 2;
        }
        k kVar = this.f526a;
        if (kVar != null) {
            kVar.f();
        }
        if (c()) {
            return 2;
        }
        stopForeground(true);
        return 2;
    }
}
