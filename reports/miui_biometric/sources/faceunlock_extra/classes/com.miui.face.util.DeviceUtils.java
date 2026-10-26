package com.miui.face.util;

import android.os.Build;
import com.miui.face.MiuiBioApp;
import com.miui.face.R;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DeviceUtils {
    public static boolean isLiteMode() {
        return miuix.device.DeviceUtils.isMiuiLiteRom() || miuix.device.DeviceUtils.isMiuiMiddle();
    }

    public static boolean isNeedGetFrameNonsequence() {
        return Arrays.asList(MiuiBioApp.getContext().getResources().getStringArray(R.array.get_frame_nonsequence)).contains(Build.DEVICE);
    }

    public static boolean isNeedOperatingMode() {
        return Arrays.asList(MiuiBioApp.getContext().getResources().getStringArray(R.array.device_need_operating_mode)).contains(miui.os.Build.DEVICE);
    }

    public static boolean isVirtualLightDevice() {
        return Arrays.asList(MiuiBioApp.getContext().getResources().getStringArray(R.array.virtual_light_device)).contains(Build.DEVICE);
    }

    public static boolean isNeedMoreStreamInAuth() {
        return Arrays.asList(MiuiBioApp.getContext().getResources().getStringArray(R.array.need_more_stream_in_auth)).contains(Build.DEVICE);
    }
}
