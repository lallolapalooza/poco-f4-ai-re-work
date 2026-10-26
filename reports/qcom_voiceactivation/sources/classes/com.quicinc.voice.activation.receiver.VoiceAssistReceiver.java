package com.quicinc.voice.activation.receiver;

import G.q;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import com.quicinc.voice.activation.engineservice.ReenableWakewordService;
import p003d.g;

/* JADX INFO: loaded from: classes.dex */
public final class VoiceAssistReceiver extends BroadcastReceiver {
    public final void a() {
        PackageInfo packageInfo;
        Context contextD = g.d();
        try {
            packageInfo = contextD.getPackageManager().getPackageInfo(contextD.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            q.k("PackageManager Exception for QVA", e2);
            packageInfo = null;
        }
        String strB = g.q().b();
        if (packageInfo == null || packageInfo.versionName.equals(strB)) {
            return;
        }
        ((AudioManager) contextD.getSystemService(AudioManager.class)).setParameters("qva.version=" + packageInfo.versionName);
        g.q().d(packageInfo.versionName);
        q.g("update QDATA qva.version= " + packageInfo.versionName);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        q.h("VoiceAssistReceiver intent = " + intent);
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.MY_PACKAGE_REPLACED".equals(action)) {
            a();
            context.startService(ReenableWakewordService.c());
        }
    }
}
