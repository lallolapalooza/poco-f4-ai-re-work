package com.quicinc.voice.activation.receiver;

import G.q;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p003d.g;
import p010k.a;
import p010k.b;
import v.j;

/* JADX INFO: loaded from: classes.dex */
public final class PackageChangedReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Intent f598a = new Intent("com.quicinc.voice.activation.action.PACKAGE_CHANGED");

    private PackageChangedReceiver() {
    }

    public static String a(Intent intent) {
        if (intent.getAction() == null) {
            return null;
        }
        String action = intent.getAction();
        action.hashCode();
        if (action.equals("android.intent.action.PACKAGE_DATA_CLEARED")) {
            return "DATA_CLEARED";
        }
        if (action.equals("android.intent.action.PACKAGE_ADDED") && !intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
            return "INSTALLED";
        }
        return null;
    }

    public static List b() {
        PackageManager packageManagerP = g.p();
        if (packageManagerP != null) {
            return packageManagerP.queryBroadcastReceivers(f598a, 0);
        }
        return null;
    }

    public static void c(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(new PackageChangedReceiver(), intentFilter);
    }

    public static void d(Intent intent) {
        String schemeSpecificPart;
        b bVarG;
        Map mapD;
        Uri data = intent.getData();
        if (data == null || (bVarG = g.e().g((schemeSpecificPart = data.getSchemeSpecificPart()))) == null) {
            return;
        }
        a aVarL = bVarG.l();
        if (aVarL != null && aVarL.b().equals(schemeSpecificPart) && (mapD = g.e().d(bVarG.j())) != null && mapD.size() > 0) {
            Iterator it = mapD.values().iterator();
            while (it.hasNext()) {
                h(((BigSoundModel) it.next()).getSoundModelKey());
            }
        }
        if (!g.f().p(bVarG.j())) {
            g.u().c(bVarG.j(), schemeSpecificPart);
        } else {
            g.o().n(bVarG);
            g.k().d(bVarG);
        }
    }

    public static void e(Context context, int i2, String str) {
        List<ResolveInfo> listB;
        if (i2 == -1 || (listB = b()) == null) {
            return;
        }
        for (ResolveInfo resolveInfo : listB) {
            if (i2 == 0 || resolveInfo.activityInfo.applicationInfo.uid == i2) {
                Intent intent = new Intent(f598a);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                intent.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
                intent.putExtra("EXTRA_STATE", str);
                if (i2 > 0) {
                    intent.putExtra("EXTRA_UID", i2);
                }
                context.sendBroadcast(intent);
            }
        }
    }

    public static void f(Context context, Intent intent) {
        String strA;
        if (intent == null || (strA = a(intent)) == null) {
            return;
        }
        e(context, intent.getIntExtra("android.intent.extra.UID", -1), strA);
    }

    public static void g(Context context) {
        e(context, 0, "MICROPHONE_PERMISSION_NEEDED");
    }

    public static void h(j jVar) {
        if (g.v().n(jVar).d()) {
            g.u().d(jVar);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
        q.r("action=" + action + ", replacing=" + booleanExtra);
        if ((action.equals("android.intent.action.PACKAGE_DATA_CLEARED") || action.equals("android.intent.action.PACKAGE_REMOVED")) && !booleanExtra) {
            d(intent);
        }
        f(context, intent);
    }
}
