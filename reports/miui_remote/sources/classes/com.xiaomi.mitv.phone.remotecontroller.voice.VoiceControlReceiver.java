package com.xiaomi.mitv.phone.remotecontroller.voice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes3.dex */
public class VoiceControlReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f20352b = "com.xiaomi.mitv.phone.remotecontroller.voice_control_action";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20353a = "VoiceControlReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        intent.getAction().equalsIgnoreCase(f20352b);
    }
}
