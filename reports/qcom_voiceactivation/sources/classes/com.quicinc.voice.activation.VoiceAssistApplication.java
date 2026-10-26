package com.quicinc.voice.activation;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import com.quicinc.voice.activation.engineservice.ForegroundService;
import com.quicinc.voice.activation.receiver.PackageChangedReceiver;
import p003d.g;
import p004e.a;
import p013n.C0025g0;

/* JADX INFO: loaded from: classes.dex */
public final class VoiceAssistApplication extends Application {
    public final void a(Context context) {
        if (Build.VERSION.SDK_INT >= 30) {
            C0025g0 c0025g0Q = g.q();
            if (a.a(context)) {
                c0025g0Q.c(true);
            } else if (c0025g0Q.a()) {
                c0025g0Q.c(false);
                PackageChangedReceiver.g(context);
            }
        }
    }

    public final boolean b() {
        try {
            getPackageManager().getPackageInfo(getPackageName(), 1048576);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Context applicationContext = getApplicationContext();
        if (!b()) {
            Process.killProcess(Process.myPid());
            return;
        }
        g.x(applicationContext);
        if (ForegroundService.c()) {
            ForegroundService.e();
        } else {
            ForegroundService.i();
        }
        a(applicationContext);
    }
}
