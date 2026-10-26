package com.xiaomi.mtb;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Registrant;
import android.os.RegistrantList;
import android.os.SystemProperties;
import android.util.Log;
import com.xiaomi.modem.ModemUtils;
import com.xiaomi.modem.OemHookAgent;
import com.xiaomi.mtb.activity.MtbDataDisplayActivity;
import com.xiaomi.mtb.activity.MtbLogManagerActivity;
import com.xiaomi.mtb.activity.MtbSmartTestConfigActivity;
import com.xiaomi.mtb.cloud.ModemCloudController;
import com.xiaomi.mtb.cloud.ModemCloudUtils;
import com.xiaomi.mtb.modemdump.ModemDumpServiceConfig;

/* JADX INFO: loaded from: classes.dex */
public class MtbApp extends Application {
    private static final String BC_ACTION_MTB_IS_RUNNING = "xiaomi.intent.action.MTB_IS_RUNNING";
    private static final int EVENT_MI_SERVER_CHECK_FOR_NTN_LOG_SET = 6;
    private static final int EVENT_MI_SERVER_CHECK_FOR_ONLY_UPADTE_CLASS = 4;
    private static final int EVENT_MI_SERVER_CHECK_FOR_SMART_EXTEND = 5;
    private static final int EVENT_MI_SERVER_CHECK_FOR_USB_DIAG_SET = 3;
    private static final int EVENT_MI_SERVER_CHECK_FROM_BC = 2;
    private static final int EVENT_QCRIL_HOOK_READY = 0;
    private static final String MTB_NAME_DEF = "Rueban(MTB)";
    private static final int OEM_PROJECT_FLAG_INIT = 0;
    private static final int OEM_PROJECT_FLAG_NO = 2;
    private static final int OEM_PROJECT_FLAG_YES = 1;
    private static final String PROCESS_NAME_MTB = "com.xiaomi.mtb";
    private static final String PROP_MTB_IS_STARTED = "ro.vendor.radio.mtb_is_started";
    private static final String PROP_MTB_IS_STARTED_NO = "0";
    private static final String PROP_MTB_IS_STARTED_SYS = "debug.mtb.running";
    private static final String PROP_MTB_IS_STARTED_YES = "1";
    private static final String PROP_SWITCH_LPM_MONITOR = "persist.vendor.radio.lpm_monitor";
    private static final String PROP_SWITCH_LPM_MONITOR_OFF = "false";
    private static final String PROP_SWITCH_LPM_MONITOR_ON = "true";
    private static final String PROP_SWITCH_SS_MONITOR = "persist.vendor.radio.ss_monitor";
    private static final String PROP_SWITCH_SS_MONITOR_OFF = "false";
    private static final String PROP_SWITCH_SS_MONITOR_ON = "true";
    private static final String TAG = "MtbApp";
    public static String mBuildProfile = "NULL";
    public static String mBuildRegion = "none";
    public static String mBuildTags = "NULL";
    public static String mBuildVersion = "";
    public static String mCertType = "none";
    public static int mChipId = 0;
    public static Context mContext = null;
    public static String mDeviceHwInfo = "NULL";
    public static String mDeviceHwLevel = "NULL";
    public static String mDeviceModName = "NULL";
    public static String mDeviceName = "NULL";
    public static String mFactoryBuild = "NULL";
    public static int mHalChipId = 0;
    private static MtbFloatingDisplayView mItemFloatingView = null;
    private static MtbApp mMtbApp = null;
    private static OemHookAgent mMtbHookAgent = null;
    public static String mMtbName = "Rueban(MTB)";
    private static int mOemProject = 0;
    public static String mRootType = "NULL";
    private static RegistrantList mHookReadyRegistrants = new RegistrantList();
    private static long mClass = -1;
    protected Handler mHandler = new Handler() { // from class: com.xiaomi.mtb.MtbApp.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MtbApp.log("handleMessage msg id: " + message.what);
            new Bundle();
            Bundle data = message.getData();
            int i2 = message.what;
            if (i2 == 0) {
                MtbApp.log("EVENT_QCRIL_HOOK_READY");
                MtbApp.this.onHookDone();
                return;
            }
            if (i2 == 2) {
                MtbApp.log("EVENT_MI_SERVER_CHECK_FROM_BC");
                MtbApp.this.onMiServerCheckDoneForOnlyUpdateClass(data);
                return;
            }
            if (i2 == 3) {
                MtbApp.log("EVENT_MI_SERVER_CHECK_FOR_USB_DIAG_SET");
                MtbApp.this.onMiServerCheckDoneForUSBDiag(data);
                return;
            }
            if (i2 == 4) {
                MtbApp.log("EVENT_MI_SERVER_CHECK_FOR_ONLY_UPADTE_CLASS");
                MtbApp.this.onMiServerCheckDoneForOnlyUpdateClass(data);
                return;
            }
            if (i2 == 5) {
                MtbApp.log("EVENT_MI_SERVER_CHECK_FOR_SMART_EXTEND");
                MtbApp.this.onMiServerCheckDoneForSmartExtend(data);
            } else if (i2 == 6) {
                MtbApp.log("EVENT_MI_SERVER_CHECK_FOR_NTN_LOG_SET");
                MtbApp.this.onMiServerCheckDoneForNtnLog(data);
            } else {
                MtbApp.log("invalid msg id: " + message.what);
            }
        }
    };
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.xiaomi.mtb.MtbApp.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            MtbApp.log("Received: " + action);
            if (MtbLogManagerActivity.BC_CHECK_AND_CONTROL_MDLOG.equals(action)) {
                MtbApp.log("BC_CHECK_AND_CONTROL_MDLOG");
                MtbLogManagerActivity.modemOtaOfflineLog(intent);
                return;
            }
            if (MtbDataDisplayActivity.XIAOMI_ACTION_DATA_DISPLAY_PREF_UPDATE.equals(action)) {
                MtbApp.log("XIAOMI_ACTION_DATA_DISPLAY_PREF_UPDATE");
                MtbDataDisplayActivity.onUpdateDataDisplayPrefGroupDataBase(intent, MtbApp.mContext);
                return;
            }
            if (ModemUtils.BC_ACTION_SHUTDOWN.equals(action)) {
                MtbApp.log("BC_ACTION_SHUTDOWN");
                MtbUtils.onNotifyRildForShutdown(MtbApp.mMtbHookAgent);
                return;
            }
            if (MtbFloatingDisplayView.XIAOMI_ACTION_DATA_FLOATING_DISPLAY_UPDATE.equals(action)) {
                MtbApp.log("XIAOMI_ACTION_DATA_FLOATING_DISPLAY_UPDATE");
                MtbFloatingDisplayView unused = MtbApp.mItemFloatingView = MtbFloatingDisplayView.onUpdateFloatingViewOption(intent, MtbApp.mItemFloatingView, context);
                return;
            }
            if (XiaoMiServerPermissionCheck.XIAOMI_ACTION_BC_MI_SERVER_CHECK.equals(action)) {
                MtbApp.log("XIAOMI_ACTION_BC_MI_SERVER_CHECK");
                XiaoMiServerPermissionCheck.urlCheckFromBroadcast(context, intent);
                return;
            }
            if (XiaoMiServerPermissionCheck.XIAOMI_ACTION_BC_MI_SERVER_URL_CLEAR.equals(action)) {
                MtbApp.log("XIAOMI_ACTION_BC_MI_SERVER_URL_CLEAR");
                XiaoMiServerPermissionCheck.clearUrlFromBroadcast(context);
                return;
            }
            if (UrlPermissionCheck.XIAOMI_ACTION_BC_MI_URL_CONFIG.equals(action)) {
                MtbApp.log("XIAOMI_ACTION_BC_MI_URL_CONFIG");
                UrlPermissionCheck.updateUrlConfigFromBroadcast(context, intent);
                return;
            }
            if (UrlPermissionCheck.XIAOMI_ACTION_BC_MI_URL_CLEAR.equals(action)) {
                MtbApp.log("XIAOMI_ACTION_BC_MI_URL_CLEAR");
                UrlPermissionCheck.clearUrlConfigFromBroadcast(context);
                return;
            }
            if (MtbSmartTestConfigActivity.ACTION_AIRPLANE_MODE_CHANGED.equals(action)) {
                MtbApp.log("ACTION_AIRPLANE_MODE_CHANGED");
                MtbSmartTestConfigActivity.onActionAirplaneModeChangedHdl(context, intent);
                return;
            }
            if (MtbSmartTestConfigActivity.ACTION_SERVICE_STATE_CHANGED.equals(action)) {
                MtbApp.log("ACTION_SERVICE_STATE_CHANGED");
                MtbSmartTestConfigActivity.onActionServiceStateChangedHdl(context, intent);
            } else {
                if (!ModemCloudController.ACTION_CLOUD_TELE_FEATURE_INFO_CHANGED.equals(action)) {
                    MtbApp.log("Mtbapp mBroadcastReceiver do nothing");
                    return;
                }
                if (ModemCloudUtils.needInitModemCloudController() && ModemCloudController.getInstance() == null) {
                    ModemCloudController.init(MtbApp.mContext);
                }
                if (ModemCloudController.getInstance() != null) {
                    ModemCloudController.getInstance().startProcessCloudControlLogic();
                }
            }
        }
    };

    public MtbApp() {
        log("Constructor");
    }

    public static Context getContext() {
        log("getContext, mContext = " + mContext);
        return mContext;
    }

    public static OemHookAgent getHook() {
        log("getHook, mMtbHookAgent = " + mMtbHookAgent);
        return mMtbHookAgent;
    }

    public static MtbApp getInstance() {
        log("getInstance, mMtbApp = " + mMtbApp);
        return mMtbApp;
    }

    public static long getMiServerPermissionClass() {
        return mClass;
    }

    public static boolean isOemProduct() {
        if (mMtbHookAgent == null) {
            log("isOemProduct, mMtbHookAgent is null");
            return false;
        }
        if (true == MtbUtils.DEVICE_MTB_NON_OEM_LIST.contains(mDeviceName)) {
            log("isOemProduct, DEVICE_MTB_NON_OEM_LIST: " + mDeviceName);
            return false;
        }
        if (mOemProject == 0) {
            if (true == mMtbHookAgent.isOemProduct()) {
                mOemProject = 1;
            } else {
                mOemProject = 2;
            }
        }
        log("isOemProduct, mOemProject = " + mOemProject);
        return 1 == mOemProject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    private void onBroadcastForMtbIsRunning() {
        if (mContext == null) {
            log("onBroadcastForMtbIsRunning, mContext is null");
            return;
        }
        mContext.sendBroadcast(new Intent(BC_ACTION_MTB_IS_RUNNING));
        log("Sent a broadcast for mtb_is_running");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHookDone() {
        log("onHookDone");
        OemHookAgent.unregisterQcRilHookReady(this.mHandler);
        mMtbHookAgent = OemHookAgent.getHook();
        synchronized (mHookReadyRegistrants) {
            mHookReadyRegistrants.notifyRegistrants();
        }
        if (true == isOemProduct()) {
            log("OEM project will do nothing");
        } else {
            onInitByHook();
        }
    }

    private void onInitByHook() {
        String curProcessName = ModemUtils.getCurProcessName(this);
        log("onInitByHook, processName: " + curProcessName);
        if (!PROCESS_NAME_MTB.equals(curProcessName)) {
            log("Will only make init for blew in process: com.xiaomi.mtb");
            return;
        }
        try {
            try {
                startMtbService();
                ModemUtils.onInitHook(mMtbHookAgent);
                onRegisterReceiver();
                onUpdateMtbRunState(true);
            } catch (Exception e2) {
                log("Exception e: " + e2);
            }
        } finally {
            log("onInitByHook done");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMiServerCheckDoneForNtnLog(Bundle bundle) {
        log("onMiServerCheckDoneForNtnLog");
        if (bundle == null) {
            log("data is null");
            return;
        }
        long j2 = bundle.getLong(XiaoMiServerPermissionCheck.BUNDLE_CLASS);
        mClass = j2;
        MtbSmartTestConfigActivity.onModemNtnLogSettingDo(mContext, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMiServerCheckDoneForOnlyUpdateClass(Bundle bundle) {
        log("onMiServerCheckDoneForOnlyUpdateClass");
        if (bundle == null) {
            log("data is null");
        } else {
            mClass = bundle.getLong(XiaoMiServerPermissionCheck.BUNDLE_CLASS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMiServerCheckDoneForSmartExtend(Bundle bundle) {
        log("onMiServerCheckDoneForSmartExtend");
        if (bundle == null) {
            log("data is null");
            return;
        }
        long j2 = bundle.getLong(XiaoMiServerPermissionCheck.BUNDLE_CLASS);
        mClass = j2;
        MtbSmartTestConfigActivity.onStartSmartTestExtendDo(mContext, mMtbHookAgent, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMiServerCheckDoneForUSBDiag(Bundle bundle) {
        log("onMiServerCheckDoneForUSBDiag");
        if (bundle == null) {
            log("data is null");
            return;
        }
        long j2 = bundle.getLong(XiaoMiServerPermissionCheck.BUNDLE_CLASS);
        mClass = j2;
        MtbSmartTestConfigActivity.onModemDiagSettingDo(mContext, j2);
    }

    private void onRegisterReceiver() {
        log("onRegisterReceiver");
        if (mMtbHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MtbDataDisplayActivity.XIAOMI_ACTION_DATA_DISPLAY_PREF_UPDATE);
        intentFilter.addAction(MtbFloatingDisplayView.XIAOMI_ACTION_DATA_FLOATING_DISPLAY_UPDATE);
        intentFilter.addAction(XiaoMiServerPermissionCheck.XIAOMI_ACTION_BC_MI_SERVER_URL_CLEAR);
        intentFilter.addAction(UrlPermissionCheck.XIAOMI_ACTION_BC_MI_URL_CONFIG);
        intentFilter.addAction(UrlPermissionCheck.XIAOMI_ACTION_BC_MI_URL_CLEAR);
        intentFilter.addAction(ModemCloudController.ACTION_CLOUD_TELE_FEATURE_INFO_CHANGED);
        registerReceiver(this.mBroadcastReceiver, intentFilter);
    }

    private void onUpdateMtbRunState(boolean z) {
        log("onUpdateMtbRunState, start = " + z);
        if (mMtbHookAgent == null) {
            log("mMtbHookAgent is null");
        } else if (z) {
            SystemProperties.set(PROP_MTB_IS_STARTED_SYS, "1");
            mMtbHookAgent.onHookPropSetSync(PROP_MTB_IS_STARTED, "1");
        } else {
            SystemProperties.set(PROP_MTB_IS_STARTED_SYS, "0");
            mMtbHookAgent.onHookPropSetSync(PROP_MTB_IS_STARTED, "0");
        }
    }

    public static void registerHookReadyEvent(Handler handler, int i2, Object obj) {
        log("registerHookReadyEvent, handler = " + handler + ", what = " + i2 + ", obj = " + obj + ", mMtbHookAgent = " + mMtbHookAgent);
        Registrant registrant = new Registrant(handler, i2, obj);
        synchronized (mHookReadyRegistrants) {
            mHookReadyRegistrants.add(registrant);
            if (mMtbHookAgent != null) {
                registrant.notifyRegistrant();
            }
        }
    }

    public static void setMiServerPermissionClass(long j2) {
        log("setMiServerPermissionClass, mClass = " + mClass + ", cls = " + j2);
        mClass = j2;
    }

    private void startMiServerCheck(int i2) {
        log("startMiServerCheck, event = " + i2);
        String curProcessName = ModemUtils.getCurProcessName(this);
        log("processName: " + curProcessName);
        if (PROCESS_NAME_MTB.equals(curProcessName)) {
            XiaoMiServerPermissionCheck.onCheckAndInitPermission(mContext, this.mHandler, i2, mMtbHookAgent);
        } else {
            log("Will only start mtb service in process: com.xiaomi.mtb");
        }
    }

    private void startMtbService() {
        log("startMtbService, mHookType = " + OemHookAgent.mHookType);
        String curProcessName = ModemUtils.getCurProcessName(this);
        log("processName: " + curProcessName);
        if (!PROCESS_NAME_MTB.equals(curProcessName)) {
            log("Will only start mtb service in process: com.xiaomi.mtb");
            return;
        }
        startService(new Intent(this, (Class<?>) MtbService.class));
        if (OemHookAgent.mHookType == 2) {
            log("Qcom MTB will start ModemDump Service");
            ModemDumpServiceConfig.startService(this);
        }
    }

    public static void unregisterQcRilHookReady(Handler handler) {
        log("unregisterQcRilHookReady, handler = " + handler);
        synchronized (mHookReadyRegistrants) {
            mHookReadyRegistrants.remove(handler);
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        log("onCreate");
        mContext = this;
        mMtbApp = this;
        ModemUtils.onInitContext(this);
        MtbUtils.onInitApp(this);
        onInit();
        onBroadcastForMtbIsRunning();
        if (true != MtbUtils.DEVICE_MTB_NOT_SUPPORT_LIST.contains(mDeviceName)) {
            OemHookAgent.registerHookReadyEvent(this, this.mHandler, 0, null);
            return;
        }
        log("onCreate, mtb is not supported for " + mDeviceName);
    }

    public void onInit() {
        log("onInit");
        if ("NULL".equals(mDeviceName)) {
            mDeviceName = ModemUtils.getDeviceName();
        }
        if ("NULL".equals(mDeviceHwInfo)) {
            mDeviceHwInfo = ModemUtils.getDeviceHwInfo();
        }
        if ("NULL".equals(mDeviceModName)) {
            mDeviceModName = ModemUtils.getDeviceSwInfo();
        }
        if ("NULL".equals(mDeviceHwLevel)) {
            mDeviceHwLevel = ModemUtils.getDeviceHwLevel();
        }
        if ("none".equals(mBuildRegion)) {
            mBuildRegion = ModemUtils.getBuildRegion();
        }
        if ("none".equals(mCertType)) {
            mCertType = ModemUtils.getCertType();
        }
        if ("NULL".equals(mFactoryBuild)) {
            mFactoryBuild = ModemUtils.getFactoryBuild();
        }
        if ("NULL".equals(mRootType)) {
            mRootType = ModemUtils.getRootType();
        }
        if ("".equals(mBuildVersion)) {
            mBuildVersion = ModemUtils.getBuildVersion();
        }
        if ("NULL".equals(mBuildTags)) {
            mBuildTags = ModemUtils.getBuildTags();
        }
        if (MTB_NAME_DEF.equals(mMtbName)) {
            mMtbName = mContext.getResources().getString(R.string.mtb_tool_name);
        }
        log("onInit, mMtbName = " + mMtbName + ", mDeviceName = " + mDeviceName + ", mDeviceHwInfo = " + mDeviceHwInfo + ", mDeviceModName = " + mDeviceModName + ", mDeviceHwLevel = " + mDeviceHwLevel + ", mBuildRegion = " + mBuildRegion + ", mCertType = " + mCertType + ", mFactoryBuild = " + mFactoryBuild + ", mRootType = " + mRootType + ", mBuildVersion = " + mBuildVersion + ", mBuildTags = " + mBuildTags);
    }

    public void onRemoveMsg(int i2) {
        this.mHandler.removeMessages(i2);
    }

    public void onSendMsg(int i2) {
        this.mHandler.sendEmptyMessage(i2);
    }

    public void startMiServerCheckForNtnLogSet() {
        log("startMiServerCheckForNtnLogSet");
        startMiServerCheck(6);
    }

    public void startMiServerCheckForOnlyUpdateClass() {
        log("startMiServerCheckForOnlyUpdateClass");
        String curProcessName = ModemUtils.getCurProcessName(this);
        log("processName: " + curProcessName);
        if (PROCESS_NAME_MTB.equals(curProcessName)) {
            XiaoMiServerPermissionCheck.onCheckAndInitPermission(mContext, this.mHandler, 4, mMtbHookAgent);
        } else {
            log("Will only start mtb service in process: com.xiaomi.mtb");
        }
    }

    public void startMiServerCheckForSmartExtend() {
        log("startMiServerCheckForSmartExtend");
        startMiServerCheck(5);
    }

    public void startMiServerCheckForUsbDiagSet() {
        log("startMiServerCheckForUsbDiagSet");
        startMiServerCheck(3);
    }

    public void onSendMsg(int i2, int i3) {
        this.mHandler.sendEmptyMessageDelayed(i2, i3);
    }
}
