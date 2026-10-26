package com.xiaomi.mtb;

import android.content.Intent;
import android.util.Log;
import com.xiaomi.mtb.activity.MtbAntTxRxModeActivity;
import com.xiaomi.mtb.activity.MtbAtCommandConfigActivity;
import com.xiaomi.mtb.activity.MtbBaseActivity;
import com.xiaomi.mtb.activity.MtbCarrierDemandActivity;
import com.xiaomi.mtb.activity.MtbDataDisplayActivity;
import com.xiaomi.mtb.activity.MtbDiagCommandConfigActivity;
import com.xiaomi.mtb.activity.MtbDisplayMipiTestActivity;
import com.xiaomi.mtb.activity.MtbHydraConfigActivity;
import com.xiaomi.mtb.activity.MtbLogManagerActivity;
import com.xiaomi.mtb.activity.MtbMbnConfigActivity;
import com.xiaomi.mtb.activity.MtbModemAutoCheckToolActivity;
import com.xiaomi.mtb.activity.MtbNvEfsConfigActivity;
import com.xiaomi.mtb.activity.MtbRfAntDeviceConfigActivity;
import com.xiaomi.mtb.activity.MtbRfAntPortConfigActivity;
import com.xiaomi.mtb.activity.MtbRfDebugToolActivity;
import com.xiaomi.mtb.activity.MtbSarTestActivity;
import com.xiaomi.mtb.activity.MtbSimTestActivity;
import com.xiaomi.mtb.activity.MtbSmartTestConfigActivity;
import com.xiaomi.mtb.activity.MtbUserPanelActivity;

/* JADX INFO: loaded from: classes.dex */
public class ModemTestBoxMainInit {
    private static final String TAG = "ModemTestBoxMainInit";

    private static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    public static void onInitMainToolBarList(MtbBaseActivity mtbBaseActivity) {
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 1L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_smart_test_setting, new Intent(mtbBaseActivity, (Class<?>) MtbSmartTestConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 2L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_data_display, new Intent(mtbBaseActivity, (Class<?>) MtbDataDisplayActivity.class), MtbDataDisplayActivity.onGetDataDisplayPrefGroupDataBase(mtbBaseActivity));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 4L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_nvefs_config_manager, new Intent(mtbBaseActivity, (Class<?>) MtbNvEfsConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 8L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_mbn_config_load_and_active, new Intent(mtbBaseActivity, (Class<?>) MtbMbnConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_SIM_TEST)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_sim_test, new Intent(mtbBaseActivity, (Class<?>) MtbSimTestActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 16L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_rf_ant_test, new Intent(mtbBaseActivity, (Class<?>) MtbRfAntPortConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 32L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_rf_ant_device_info_config, new Intent(mtbBaseActivity, (Class<?>) MtbRfAntDeviceConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 64L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_ant_tx_rx_mode, new Intent(mtbBaseActivity, (Class<?>) MtbAntTxRxModeActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_MODEM_AUTO_CHECK)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_rfc_debug, new Intent(mtbBaseActivity, (Class<?>) MtbRfDebugToolActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_MD_AUTO_CHECK)) {
            MtbBaseActivity.onAddList(R.string.mtb_modem_auto_check_tool, new Intent(mtbBaseActivity, (Class<?>) MtbModemAutoCheckToolActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_HYDRA_CONFIG)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_hydra_config, new Intent(mtbBaseActivity, (Class<?>) MtbHydraConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_ATCOMMAND)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_atcommand_config_manager, new Intent(mtbBaseActivity, (Class<?>) MtbAtCommandConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_DIAG_COMMAND)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_diag_command_config_manager, new Intent(mtbBaseActivity, (Class<?>) MtbDiagCommandConfigActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 128L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_sar_test, "com.miui.cit.auxiliary", "CitSarAuthenticaTestActivity", new Intent(mtbBaseActivity, (Class<?>) MtbSarTestActivity.class));
        }
        XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 512L);
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_CARRIER_OPT)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_carrier_demand, new Intent(mtbBaseActivity, (Class<?>) MtbCarrierDemandActivity.class), MtbCarrierDemandActivity.startCarrierDemandActivityFromMtb(mtbBaseActivity));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), 256L)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_display_mipi_test, new Intent(mtbBaseActivity, (Class<?>) MtbDisplayMipiTestActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_USER_PANEL)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_modem_user_panel, new Intent(mtbBaseActivity, (Class<?>) MtbUserPanelActivity.class));
        }
        if (XiaoMiServerPermissionCheck.checkClass(MtbApp.getMiServerPermissionClass(), XiaoMiServerPermissionCheck.MTB_CLASS_MASK_LOG_MANAGER)) {
            MtbBaseActivity.onAddList(R.string.mtb_tool_log_manager_tool, new Intent(mtbBaseActivity, (Class<?>) MtbLogManagerActivity.class));
        }
    }
}
