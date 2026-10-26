package com.miui.antispam.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class AntiSpamService extends Service {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f7170c = AntiSpamService.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f7171d = "timingUpdate";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(f7170c, "service create");
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(f7170c, "service onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i10, int i11) {
        if (intent == null) {
            return super.onStartCommand(null, i10, i11);
        }
        if (f7171d.equals(intent.getAction())) {
            new m2.a(this, null).h();
        }
        return super.onStartCommand(intent, i10, i11);
    }
}
