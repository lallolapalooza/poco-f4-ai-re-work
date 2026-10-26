package com.miui.face.util;

import android.util.Slog;
import com.miui.misight.mievent.MiSight;

/* JADX INFO: loaded from: classes.dex */
public class MiSightCompat {
    private static final int STATUS_SUPPORT = 2;
    private static final int STATUS_UNSUPPORTED = 1;
    private static int status;

    static {
        try {
            Class.forName("com.miui.misight.mievent.MiSight");
            status = 2;
        } catch (Exception unused) {
            Slog.w("miui.face.FaceService", "can not load MiSight");
            status = 1;
        }
    }

    public static void sendEvent(int i, Object... objArr) {
        if (status == 1) {
            return;
        }
        try {
            MiSight.sendEvent(MiSight.constructEvent(i, objArr));
        } catch (Exception unused) {
            Slog.w("miui.face.FaceService", "send MiEvent fail");
            status = 1;
        }
    }
}
