package com.miui.extraphoto.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BigBitmapLoadUtils {
    private static final String[] sSpecialWhiteList = {"davinci", "davinciin"};

    public static int getMaxMemory(Context context) {
        int largeMemoryClass = ((ActivityManager) context.getSystemService("activity")).getLargeMemoryClass();
        Log.d("BigBitmapLoadUtils", "max memory:%dM" + largeMemoryClass);
        return largeMemoryClass;
    }

    public static long getPhoneTotalMem(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        Log.d("BigBitmapLoadUtils", "phone total memory:%d" + memoryInfo.totalMem);
        return memoryInfo.totalMem;
    }

    private static boolean isMemoryBeyond3G(Context context) {
        return getPhoneTotalMem(context) > 3435973836L;
    }

    private static boolean isMemoryReach6G(Context context) {
        return getPhoneTotalMem(context) > 5690831667L;
    }

    public static boolean isSpecialBigMemoryDevices() {
        for (String str : sSpecialWhiteList) {
            if (str.equals(Build.DEVICE)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBigMemoryApp(Context context) {
        return isSpecialBigMemoryDevices() || getMaxMemory(context) >= 512;
    }

    public static int calculateInSampleSize(Context context, int i, int i2) {
        long j;
        long j2 = ((long) i) * ((long) i2) * 4;
        int i3 = 1;
        if (Build.VERSION.SDK_INT < 26) {
            while (true) {
                long j3 = i3;
                if (((((long) getMaxMemory(context)) * 1024) * 1024) / 3 >= (j2 / j3) / j3) {
                    break;
                }
                i3 *= 2;
            }
        } else {
            long j4 = 96000000;
            if (j2 > 96000000) {
                if (isMemoryReach6G(context)) {
                    j = 436000000;
                } else {
                    if (isMemoryBeyond3G(context) && isBigMemoryApp(context)) {
                        j4 = 260000000;
                    }
                    j = j4;
                }
                while (true) {
                    long j5 = i3;
                    if (j >= (j2 / j5) / j5) {
                        break;
                    }
                    i3 *= 2;
                }
            }
        }
        return i3;
    }
}
