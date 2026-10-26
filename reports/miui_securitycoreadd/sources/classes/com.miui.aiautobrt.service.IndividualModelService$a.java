package com.miui.aiautobrt.service;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.telephony.MiuiTelephonyManagerStub;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.aiautobrt.IIndividualBrightnessService;
import com.xiaomi.aiautobrt.IIndividualCallback;
import com.xiaomi.aiautobrt.IndividualModelEvent;
import f3.b;
import f3.c;
import f3.f;
import gb.d;
import j7.k;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import y2.e;

/* JADX INFO: loaded from: classes.dex */
public class IndividualModelService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f2731b;
    public IIndividualCallback c;

    public final class a extends IIndividualBrightnessService.Stub {
        public a() {
        }

        public final void attach(IIndividualCallback iIndividualCallback) {
            IndividualModelService individualModelService = IndividualModelService.this;
            individualModelService.c = iIndividualCallback;
            individualModelService.f2731b.f3606f = iIndividualCallback;
        }

        public final void detach() {
            IndividualModelService individualModelService = IndividualModelService.this;
            individualModelService.c = null;
            c cVar = individualModelService.f2731b;
            f fVar = cVar.f3607g;
            if (fVar != null) {
                fVar.stopWatching();
            }
            cVar.f3606f = null;
        }

        public final void onEventChanged(IndividualModelEvent individualModelEvent) {
            IndividualModelService.this.f2731b.f(individualModelEvent);
        }

        public final void predictBrightness(IndividualModelEvent individualModelEvent) {
            float currentBrightness;
            t9.a aVar = t9.a.INFO;
            c cVar = IndividualModelService.this.f2731b;
            if (cVar.f3605e >= 2) {
                d.f3839a.d("IndividualBrt-MainController", "handle event = " + individualModelEvent);
                f3.d dVar = cVar.f3603b;
                h3.a aVarA = cVar.a(individualModelEvent);
                Objects.requireNonNull(dVar);
                currentBrightness = aVarA.f4049g;
                g3.a aVar2 = dVar.c;
                if (aVar2.c) {
                    currentBrightness = aVar2.a("old_model_infer", aVarA);
                    if (currentBrightness == -1.0f) {
                        currentBrightness = aVarA.f4049g;
                    }
                }
                gb.c.i(dVar.f3608a);
                String str = "handleBrightnessModelEventChanged: input=" + aVarA + ", output=" + currentBrightness;
                d.f3839a.a(aVar, "IndividualBrt-ModelController", str, null);
                gb.c.c.a(aVar, "IndividualBrt-ModelController", str, null);
            } else {
                gb.c.i(cVar.f3602a);
                String str2 = "Current version = " + cVar.f3605e;
                d.f3839a.a(aVar, "IndividualBrt-MainController", str2, null);
                gb.c.c.a(aVar, "IndividualBrt-MainController", str2, null);
                currentBrightness = individualModelEvent.getCurrentBrightness();
            }
            IIndividualCallback iIndividualCallback = IndividualModelService.this.c;
            if (iIndividualCallback != null) {
                iIndividualCallback.onPredictFinished(individualModelEvent.getAmbientLux(), individualModelEvent.getAppCategoryId(), currentBrightness);
            }
        }

        public final void provideInterfaceVersion(int i10) {
            IndividualModelService.this.f2731b.f3605e = i10;
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return new a();
    }

    /* JADX WARN: Type inference failed for: r6v6, types: [java.util.Set<java.lang.String>, java.util.TreeSet] */
    @Override // android.app.Service
    public final void onCreate() {
        c cVarC = c.c(getApplicationContext());
        this.f2731b = cVarC;
        b bVar = cVarC.c;
        Objects.requireNonNull(bVar);
        String imei = MiuiTelephonyManagerStub.getImei();
        y6.b.a aVar = new y6.b.a();
        aVar.f8831a = "AutoBrightness";
        aVar.f8832b = b.f3593g;
        aVar.f8833d = imei;
        aVar.c = imei;
        y6.b bVar2 = new y6.b(aVar);
        Context context = bVar.f3595b;
        if (!b7.a.f2084d) {
            synchronized (b7.a.class) {
                if (!b7.a.f2084d) {
                    b7.a.f2082a = context;
                    try {
                        int i10 = context.getPackageManager().getPackageInfo(b7.a.f2082a.getPackageName(), 0).versionCode;
                        b7.a.c = b7.a.f2082a.getPackageName();
                    } catch (PackageManager.NameNotFoundException e10) {
                        e10.printStackTrace();
                    }
                    b7.a.f2084d = true;
                }
            }
        }
        try {
            String str = b7.a.c;
            String strF = k.f();
            Log.d("ABTestSdk", "logOn: " + strF + ",pkg:" + str);
            e.c = (TextUtils.isEmpty(strF) || TextUtils.isEmpty(str) || !TextUtils.equals(str, strF)) ? false : true;
            e.i();
        } catch (Exception e11) {
            androidx.activity.e.v(e11, androidx.activity.e.t("LogUtil static initializer: "), "ABTestSdk");
        }
        StringBuilder sbT = androidx.activity.e.t("log on: ");
        sbT.append(e.c);
        Log.d("ABTestSdk", sbT.toString());
        e.e("ABTest", "abTestWithConfig start,config: " + bVar2.toString());
        y6.a aVar2 = new y6.a();
        aVar2.f8827a = bVar2;
        if (z6.a.f8984k == null) {
            synchronized (z6.a.class) {
                if (z6.a.f8984k == null) {
                    z6.a.f8984k = new z6.a(bVar2);
                }
            }
        }
        z6.a aVar3 = z6.a.f8984k;
        String str2 = bVar2.f8828a;
        Objects.requireNonNull(aVar3);
        JobInfo jobInfo = null;
        if (TextUtils.isEmpty(str2)) {
            e.e("ExpPlatformManager", "appName is empty, skip it!");
        } else {
            aVar3.f8985a.add(str2);
            aVar3.d(null);
            Handler handler = new Handler(Looper.getMainLooper());
            aVar3.f8989f = handler;
            handler.postDelayed(new z6.c(aVar3), 7200000);
        }
        bVar.f3597e = aVar2;
        z6.a.f8984k.f8988e = true;
        bVar.f3594a.sendEmptyMessageDelayed(0, 30000L);
        Context context2 = cVarC.f3602a;
        JobScheduler jobScheduler = (JobScheduler) context2.getSystemService(JobScheduler.class);
        JobInfo pendingJob = jobScheduler.getPendingJob(93197);
        JobInfo jobInfoBuild = new JobInfo.Builder(93197, new ComponentName(context2, (Class<?>) TrainJobService.class)).setRequiresDeviceIdle(true).setPeriodic(TimeUnit.HOURS.toMillis(24L)).build();
        if (pendingJob == null || pendingJob.equals(jobInfoBuild)) {
            jobInfo = pendingJob;
        } else {
            jobScheduler.cancel(93197);
        }
        if (jobInfo == null) {
            jobScheduler.schedule(jobInfoBuild);
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
    }
}
