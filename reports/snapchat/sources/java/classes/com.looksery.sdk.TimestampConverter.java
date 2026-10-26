package com.looksery.sdk;

import android.os.SystemClock;
import defpackage.AbstractC19915fE3;

/* JADX INFO: loaded from: classes2.dex */
public final class TimestampConverter {
    private static final boolean DEBUG = false;
    private static final String TAG = "com.looksery.sdk.TimestampConverter";
    private String mTag;
    private boolean mHasComputedOffset = false;
    private boolean mOffsetCorrectionEnabled = true;
    private double mTimestampOffsetSeconds = 0.0d;

    public TimestampConverter(String str) {
        this.mTag = AbstractC19915fE3.c(str, " timestamps");
    }

    public double convert(long j) {
        if (!this.mHasComputedOffset && this.mOffsetCorrectionEnabled && j > 0) {
            long j2 = j / 1000000;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long jUptimeMillis = SystemClock.uptimeMillis();
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jAbs = Math.abs(j2 - jElapsedRealtime);
            long j3 = j2 - jUptimeMillis;
            if (Math.abs(j3) < jAbs) {
                jAbs = Math.abs(j3);
            } else {
                jUptimeMillis = jElapsedRealtime;
            }
            if (Math.abs(j2 - jCurrentTimeMillis) >= jAbs) {
                jCurrentTimeMillis = jUptimeMillis;
            }
            double d = jElapsedRealtime - jCurrentTimeMillis;
            Double.isNaN(d);
            this.mTimestampOffsetSeconds = d / 1000.0d;
            this.mHasComputedOffset = true;
        }
        double d2 = j;
        Double.isNaN(d2);
        return (d2 / 1.0E9d) + this.mTimestampOffsetSeconds;
    }

    public void reset() {
        this.mHasComputedOffset = false;
    }

    public void setOffsetCorrectionEnabled(boolean z) {
        this.mOffsetCorrectionEnabled = z;
        if (!z) {
            this.mTimestampOffsetSeconds = 0.0d;
        }
        reset();
    }
}
