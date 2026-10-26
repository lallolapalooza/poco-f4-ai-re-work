package com.miui.face;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.util.Slog;
import java.util.Arrays;
import miui.os.Build;
import miui.util.FeatureParser;

/* JADX INFO: loaded from: classes.dex */
public class MiuiBioApp extends Application {
    public static final String ACTION_MIUI_REGION_CHANGED = "miui.intent.action.MIUI_REGION_CHANGED";
    private static Context sInstance;
    private RegionChangedReceiver mReceiver;

    public static Context getContext() {
        return sInstance;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
        sInstance = getApplicationContext();
        this.mReceiver = new RegionChangedReceiver();
        registerReceiver(getApplicationContext());
        initFaceService();
    }

    private boolean isFaceFeatureSupport() {
        String[] stringArray;
        if (Build.IS_INTERNATIONAL_BUILD) {
            stringArray = FeatureParser.getStringArray("support_face_unlock_region_global");
        } else {
            stringArray = FeatureParser.getStringArray("support_face_unlock_region_dom");
        }
        boolean z = stringArray != null && (Arrays.asList(stringArray).contains(Build.getRegion()) || Arrays.asList(stringArray).contains("ALL"));
        Log.d("miui.face.FaceService", "isInernational:" + Build.IS_INTERNATIONAL_BUILD + " supportR:" + Arrays.toString(stringArray) + " nowR:" + Build.getRegion());
        return z;
    }

    private void registerReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_MIUI_REGION_CHANGED);
        context.registerReceiver(this.mReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFaceService() {
        boolean zIsFaceFeatureSupport = isFaceFeatureSupport();
        Slog.d("miui.face.FaceService", "MiuiBioApp onCreate, isSupport:" + zIsFaceFeatureSupport);
        if (zIsFaceFeatureSupport) {
            MiuiFaceService.init(getApplicationContext());
        }
    }

    public class RegionChangedReceiver extends BroadcastReceiver {
        public RegionChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Slog.d("miui.face.FaceService", "region changed");
            if (intent != null && MiuiBioApp.ACTION_MIUI_REGION_CHANGED.equals(intent.getAction())) {
                MiuiBioApp.this.initFaceService();
            }
        }
    }
}
