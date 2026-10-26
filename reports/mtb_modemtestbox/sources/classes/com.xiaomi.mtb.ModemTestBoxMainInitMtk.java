package com.xiaomi.mtb;

import android.content.Intent;
import android.util.Log;
import com.xiaomi.mtb.activity.MtbBaseActivity;
import com.xiaomi.mtb.activity.MtbMtkModemAutoCheckToolActivity;

/* JADX INFO: loaded from: classes.dex */
public class ModemTestBoxMainInitMtk {
    private static final String TAG = "ModemTestBoxMainInitMtk";

    private static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    public static void onInitMainToolBarList(MtbBaseActivity mtbBaseActivity) {
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_MODEM_AUTO_CHECK)) {
            MtbBaseActivity.onAddList(R.string.mtb_modem_auto_check_tool, new Intent(mtbBaseActivity, (Class<?>) MtbMtkModemAutoCheckToolActivity.class));
        }
    }
}
