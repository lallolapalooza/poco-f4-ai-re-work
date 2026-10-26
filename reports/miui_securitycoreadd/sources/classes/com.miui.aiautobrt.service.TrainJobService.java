package com.miui.aiautobrt.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import androidx.activity.e;
import f3.c;
import gb.b;
import gb.d;
import i3.a;

/* JADX INFO: loaded from: classes.dex */
public class TrainJobService extends JobService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f2733b;

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f2733b = c.c(getApplicationContext());
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        d.f3839a.d("IndividualBrt-TrainJobService", "The training environment has met expectations");
        new Thread(new a(this, jobParameters)).start();
        return true;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        if (Build.VERSION.SDK_INT < 31) {
            return false;
        }
        b bVar = d.f3839a;
        StringBuilder sbT = e.t("The training stopped due to ");
        sbT.append(jobParameters.getStopReason());
        bVar.b("IndividualBrt-TrainJobService", sbT.toString());
        return false;
    }
}
