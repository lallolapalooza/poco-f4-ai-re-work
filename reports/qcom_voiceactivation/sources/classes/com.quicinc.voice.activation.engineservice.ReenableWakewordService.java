package com.quicinc.voice.activation.engineservice;

import G.q;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import p003d.g;
import p013n.Y;

/* JADX INFO: loaded from: classes.dex */
public class ReenableWakewordService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f533b = {"android.permission.RECORD_AUDIO"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f534a;

    public static Intent c() {
        Intent intent = new Intent(g.d(), (Class<?>) ReenableWakewordService.class);
        intent.setAction("ACTION_REENABLE_WAKEWORD");
        return intent;
    }

    public static Intent d() {
        Intent intent = new Intent(g.d(), (Class<?>) ReenableWakewordService.class);
        intent.setAction("ACTION_START_SERVICE");
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        this.f534a.sendEmptyMessage(10001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        this.f534a.sendEmptyMessage(10003);
    }

    public final boolean e() {
        for (String str : f533b) {
            if (checkSelfPermission(str) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        q.r("intent=" + intent);
        String action = intent != null ? intent.getAction() : null;
        if (!e()) {
            stopSelf();
            Process.killProcess(Process.myPid());
            return 1;
        }
        if (action == null || "ACTION_REENABLE_WAKEWORD".equals(action)) {
            this.f534a = new Y(Looper.getMainLooper());
            g.u().a(new Runnable() { // from class: n.W
                @Override // java.lang.Runnable
                public final void run() {
                    this.f965a.f();
                }
            });
            g.k().a(new Runnable() { // from class: n.X
                @Override // java.lang.Runnable
                public final void run() {
                    this.f966a.g();
                }
            });
        }
        return 1;
    }
}
