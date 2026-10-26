package com.xiaomi.mtb;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.xiaomi.modem.OemHookAgent;
import com.xiaomi.mtb.activity.ModemTestBoxMainActivity;
import com.xiaomi.mtb.activity.MtbCarrierDemandActivity;
import com.xiaomi.mtb.activity.MtbDataDisplayActivity;
import com.xiaomi.mtb.activity.MtbModemAutoCheckToolActivity;
import com.xiaomi.mtb.activity.MtbMtkModemAutoCheckToolActivity;
import com.xiaomi.mtb.activity.MtbOneKeyAuthenticationActivity;
import com.xiaomi.mtb.activity.MtbSmartTestConfigActivity;
import com.xiaomi.mtb.aiservices.AiWeakSignalForecastService;

/* JADX INFO: loaded from: classes.dex */
public class MtbBroadcastReceiver extends BroadcastReceiver {
    private static final String APP_INSTALLED = "installed";
    private static final String HOST_CODE_MTB_ADB_LOG = "284";
    private static final String HOST_CODE_MTB_AI_SIGNAL = "24744625";
    private static final String HOST_CODE_MTB_DIAG_MGR = "717";
    private static final String HOST_CODE_MTB_INSTALL = "663368378";
    private static final String HOST_CODE_MTB_INSTALL_CU_CUST = "123456";
    private static final String HOST_CODE_MTB_INSTALL_KR_DEBUG_SCREEN = "663364636";
    private static final String HOST_CODE_MTB_INSTALL_MTB_SMART = "68276278";
    private static final String HOST_CODE_MTB_INSTALL_RENZHENG = "73694364";
    private static final String HOST_CODE_MTB_NTN_LOG_MGR = "686564";
    private static final String HOST_CODE_MTB_UNINSTALL = "6828";
    private static final String TAG = "MtbBroadcastReceiver";
    public static final String TEST_MODEL_MAGIC_NUMBER = "com.xiaomi.mtb.MAGIC_NUMBER";
    public static final String XIAOMI_INTENT_ACTION_AI_SIGNAL_GAME_STATUS = "com.xiaomi.mtb.aisignal.GAME_STATUS";
    public static final String XIAOMI_INTENT_ACTION_ONLY_FOR_WAKEUP_MTB = "xiaomi.intent.action.ONLY_FOR_WAKEUP_MTB";
    public static final String XIAOMI_INTENT_ACTION_TEST_MODEMHARDWARE = "com.xiaomi.mtb.RFFE_TEST";

    private boolean getMtbAppStateFromSP(Context context) {
        if (context != null) {
            return context.getSharedPreferences(context.getPackageName(), 0).getBoolean(APP_INSTALLED, false);
        }
        log("getMtbAppStateFromSP, context null");
        return false;
    }

    private static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    private void setMtbAppStater(Context context, boolean z) {
        if (context == null) {
            log("setMtbAppStater, context null");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            log("setMtbAppStater, PackageManager null");
            return;
        }
        packageManager.setComponentEnabledSetting(new ComponentName(context.getPackageName(), context.getPackageName() + ".activity.ModemTestBoxMainActivity"), z ? 1 : 2, 1);
        context.getSharedPreferences(context.getPackageName(), 0).edit().putBoolean(APP_INSTALLED, z).apply();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        log("Received: " + intent);
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.LOCKED_BOOT_COMPLETED".equals(action)) {
            log("Intent.ACTION_BOOT_COMPLETED or Intent.ACTION_LOCKED_BOOT_COMPLETED");
            if (context == null) {
                log("context is null");
                return;
            }
            log("App Name: " + context.getResources().getString(R.string.mtb_tool_name));
            return;
        }
        if (!"android.provider.Telephony.SECRET_CODE".equals(action)) {
            if (XiaoMiServerPermissionCheck.XIAOMI_ACTION_BC_MI_SERVER_CHECK.equals(action)) {
                log("MTB will config URL info for MI_SERVER authentication check");
                XiaoMiServerPermissionCheck.urlCheckFromBroadcast(context, intent);
                return;
            }
            if (XIAOMI_INTENT_ACTION_ONLY_FOR_WAKEUP_MTB.equals(action)) {
                log("This action is only used to wake up MTB");
                return;
            }
            if (XIAOMI_INTENT_ACTION_AI_SIGNAL_GAME_STATUS.equals(action)) {
                int i2 = OemHookAgent.mHookType;
                if (i2 == 2) {
                    log("Qcom MTB start BC-handle for AI_SIGNAL_GAME_STATUS");
                    AiWeakSignalForecastService.onAiSignalGameStatusBcHandle(context, intent);
                    return;
                } else if (i2 == 1) {
                    log("MTK MTB No BC-handle for AI_SIGNAL_GAME_STATUS");
                    return;
                } else {
                    log("MTB do nothing for AI_SIGNAL_GAME_STATUS");
                    return;
                }
            }
            if (!"com.xiaomi.mtb.RFFE_TEST".equals(action)) {
                log("MTB MtbBroadcastReceiver do nothing");
                return;
            }
            log("Receive RFFE_TEST");
            String str = Build.HARDWARE;
            if (str.matches("qcom")) {
                log("is QCOM platform!!");
                MtbModemAutoCheckToolActivity.startTestModemHardware(context);
                return;
            } else if (!str.matches("mt[0-9]*")) {
                log("get platform error");
                return;
            } else {
                log("is MTK platform");
                MtbMtkModemAutoCheckToolActivity.startMtbMtkModemAutoCheckToolTest(context);
                return;
            }
        }
        String host = intent.getData() != null ? intent.getData().getHost() : null;
        Intent intent2 = new Intent("android.intent.action.MAIN");
        log("TelephonyIntents.SECRET_CODE_ACTION, host: " + host);
        if (HOST_CODE_MTB_INSTALL.equals(host)) {
            log("MTB main install");
            intent2.setClass(context, ModemTestBoxMainActivity.class);
            intent2.setFlags(268435456);
            intent2.setAction(TEST_MODEL_MAGIC_NUMBER);
            context.startActivity(intent2);
            return;
        }
        if (HOST_CODE_MTB_UNINSTALL.equals(host)) {
            log("MTB main uninstall");
            return;
        }
        if (HOST_CODE_MTB_INSTALL_CU_CUST.equals(host)) {
            int i3 = OemHookAgent.mHookType;
            if (i3 == 2) {
                log("Qcom MTB for cu cust main install");
                MtbCarrierDemandActivity.startCuDemandActivity(context);
                return;
            } else if (i3 == 1) {
                log("MTK MTB No cu cust main install");
                return;
            } else {
                log("MTB do nothing for cu cust main install");
                return;
            }
        }
        if (HOST_CODE_MTB_INSTALL_KR_DEBUG_SCREEN.equals(host)) {
            int i4 = OemHookAgent.mHookType;
            if (i4 == 2) {
                log("Qcom MTB for Debug Screen main install");
                MtbDataDisplayActivity.startDebugSCreenActivity(context);
                return;
            } else if (i4 == 1) {
                log("MTK MTB No Debug Screen main install");
                return;
            } else {
                log("MTB do nothing for Debug Screen main install");
                return;
            }
        }
        if (HOST_CODE_MTB_INSTALL_RENZHENG.equals(host)) {
            int i5 = OemHookAgent.mHookType;
            if (i5 == 2) {
                log("Qcom MTB for Renzheng main install");
                MtbOneKeyAuthenticationActivity.startRenzhengActivity(context);
                return;
            } else {
                if (i5 == 1) {
                    return;
                }
                log("MTB do nothing for Renzheng main install");
                return;
            }
        }
        if (HOST_CODE_MTB_DIAG_MGR.equals(host)) {
            int i6 = OemHookAgent.mHookType;
            if (i6 == 2) {
                log("Qcom MTB for Diag mgr main install");
                MtbSmartTestConfigActivity.onModemDiagSetting(context);
                return;
            } else if (i6 == 1) {
                log("MTK MTB No Diag mgr main install");
                return;
            } else {
                log("MTB do nothing for Diag mgr main install");
                return;
            }
        }
        if (HOST_CODE_MTB_NTN_LOG_MGR.equals(host)) {
            int i7 = OemHookAgent.mHookType;
            if (i7 == 2) {
                log("Qcom MTB for NTN_LOG mgr main install");
                MtbSmartTestConfigActivity.onModemNtnLogSetting(context);
                return;
            } else if (i7 == 1) {
                log("MTK MTB No NTN_LOG mgr main install");
                return;
            } else {
                log("MTB do nothing for NTN_LOG mgr main install");
                return;
            }
        }
        if (HOST_CODE_MTB_INSTALL_MTB_SMART.equals(host)) {
            int i8 = OemHookAgent.mHookType;
            if (i8 == 2) {
                log("Qcom MTB for Smart test main install");
                MtbSmartTestConfigActivity.onStartSmartTestExtend(context);
                return;
            } else if (i8 == 1) {
                log("MTK MTB No Smart test main install");
                return;
            } else {
                log("MTB do nothing for Smart test main install");
                return;
            }
        }
        if (HOST_CODE_MTB_ADB_LOG.equals(host)) {
            log("HOST_CODE(284) is only used to wake up MTB");
            return;
        }
        if (!HOST_CODE_MTB_AI_SIGNAL.equals(host)) {
            log("MTB MtbBroadcastReceiver(SECRET_CODE_ACTION) do nothing");
            return;
        }
        int i9 = OemHookAgent.mHookType;
        if (i9 == 2) {
            log("Qcom MTB start BC-handle for AI_SIGNAL");
            AiWeakSignalForecastService.onAiSignalBcHandle(context);
        } else if (i9 == 1) {
            log("MTK MTB No BC-handle for AI_SIGNAL");
        } else {
            log("MTB do nothing for AI_SIGNAL");
        }
    }
}
