package com.xiaomi.modem;

import android.app.ActivityThread;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Registrant;
import android.os.RegistrantList;
import android.util.Log;
import dalvik.system.DexClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class OemHookAgent {
    private static final int DIAG_CMD_RESPONSE_BUFFER_SIZE = 40960;
    public static final int DISABLE_HOTSWAP = 1;
    public static final int ENABLE_HOTSWAP = 0;
    private static final int EVENT_MTKRIL_HOOK_DISCONNECTED = -2;
    private static final int EVENT_MTKRIL_HOOK_READY = -1;
    public static final int EVENT_MTK_OEMHOOK_AT_CMD_SEND = 3;
    public static final int EVENT_MTK_OEMHOOK_PROP_GET = 1;
    public static final int EVENT_MTK_OEMHOOK_PROP_SET = 2;
    public static final int EVENT_OEMHOOK_ANT_MIPI_GPIO_VALUE_GET = 67;
    public static final int EVENT_OEMHOOK_AT_CMD_SEND = 64;
    public static final int EVENT_OEMHOOK_CALL_STATUS_NOTIFY = 47;
    public static final int EVENT_OEMHOOK_CARD_SLOT_HOTSWAP_STRESS_TEST = 80;
    public static final int EVENT_OEMHOOK_CARD_SLOT_STATUS_GET = 55;
    public static final int EVENT_OEMHOOK_CARD_SLOT_STATUS_QUERY = 39;
    public static final int EVENT_OEMHOOK_CARD_SLOT_STATUS_SET = 57;
    public static final int EVENT_OEMHOOK_CARD_SLOT_TYPE_QUERY = 56;
    public static final int EVENT_OEMHOOK_COPY_FILE = 49;
    public static final int EVENT_OEMHOOK_DDS_CHANGE_NOTIFY = 48;
    public static final int EVENT_OEMHOOK_DIAG_CMD_SEND = 38;
    public static final int EVENT_OEMHOOK_DIAG_CMD_SEND_SYNC_WITH_RESULT = 66;
    public static final int EVENT_OEMHOOK_DISPLAY_MIPI_FEATURE_GET = 115;
    public static final int EVENT_OEMHOOK_DISPLAY_MIPI_FEATURE_SET = 116;
    public static final int EVENT_OEMHOOK_DISPLAY_MIPI_GET = 46;
    public static final int EVENT_OEMHOOK_DISPLAY_MIPI_SET = 45;
    public static final int EVENT_OEMHOOK_DSDA_HW_MBN_CFG = 134;
    public static final int EVENT_OEMHOOK_DSDA_IS_SUPPORTED = 87;
    public static final int EVENT_OEMHOOK_DUMP_BY_ERROR_FATAL = 99;
    public static final int EVENT_OEMHOOK_ERASE_MODEM_ST1_ST2 = 54;
    public static final int EVENT_OEMHOOK_ESIM_POWER_EX = 86;
    public static final int EVENT_OEMHOOK_ESIM_STATUS_GET = 83;
    public static final int EVENT_OEMHOOK_ESIM_STATUS_SET = 84;
    public static final int EVENT_OEMHOOK_FSG_BACKUP = 36;
    public static final int EVENT_OEMHOOK_FSG_BACKUP_NAME_GET = 40;
    public static final int EVENT_OEMHOOK_FSG_RECOVERY = 37;
    public static final int EVENT_OEMHOOK_GET_SAR_STATE = 63;
    public static final int EVENT_OEMHOOK_GOLDEN_COPY = 43;
    public static final int EVENT_OEMHOOK_HVOLTE_MBN_CFG = 44;
    public static final int EVENT_OEMHOOK_IS_OEM_PRODUCT = 105;
    public static final int EVENT_OEMHOOK_MBN_HW_TXT_PATH_INFO_GET = 135;
    public static final int EVENT_OEMHOOK_MBN_LIST_GET = 50;
    public static final int EVENT_OEMHOOK_MBN_SW_TXT_PATH_INFO_GET = 41;
    public static final int EVENT_OEMHOOK_MCFG_CONFIG_LIST_SIZE_MAX_GET = 52;
    public static final int EVENT_OEMHOOK_MCFG_CONFIG_LIST_SIZE_MAX_SET = 53;
    public static final int EVENT_OEMHOOK_MCFG_RFS_REMOTE_DO = 42;
    public static final int EVENT_OEMHOOK_MI_BROADCAST_STATE_GET = 30;
    public static final int EVENT_OEMHOOK_MI_BROADCAST_STATE_SET = 29;
    public static final int EVENT_OEMHOOK_MODEM_CLEAR_HW_MBN_CACHE = 137;
    public static final int EVENT_OEMHOOK_MODEM_CLEAR_SW_MBN_CACHE = 136;
    public static final int EVENT_OEMHOOK_MODEM_DUMP = 69;
    public static final int EVENT_OEMHOOK_MODEM_FACTORY_BUILD_MACRO_STATE_GET = 15;
    public static final int EVENT_OEMHOOK_MODEM_FEATURE_STATE_GET = 102;
    public static final int EVENT_OEMHOOK_MODEM_GET_CURRENT_ACT_MBN_CONFIG = 13;
    public static final int EVENT_OEMHOOK_MODEM_GET_FIX_RAT_COUNT = 78;
    public static final int EVENT_OEMHOOK_MODEM_MBN_AUTO_SELECT_OPEN_CHECK = 23;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CHECK = 18;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_ACTIVE = 20;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_DEACTIVE = 16;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_DELETE = 17;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_FILE_PATH_GET = 12;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_RECOVERY = 21;
    public static final int EVENT_OEMHOOK_MODEM_MBN_REACTIVATE = 77;
    public static final int EVENT_OEMHOOK_MODEM_NW_SEARCH_TYPE_SET = 120;
    public static final int EVENT_OEMHOOK_MODEM_OOS_BASE_TYPE_SET = 75;
    public static final int EVENT_OEMHOOK_MODEM_SCENE_RECOGNITION_REQ = 74;
    public static final int EVENT_OEMHOOK_MODEM_SSR = 68;
    public static final int EVENT_OEMHOOK_MODEM_SSR_TYPE_GET = 70;
    public static final int EVENT_OEMHOOK_MODEM_SSR_TYPE_NAME_GET = 72;
    public static final int EVENT_OEMHOOK_MODEM_SSR_TYPE_SET = 71;
    public static final int EVENT_OEMHOOK_MODEM_STATS_ADD_ID = 60;
    public static final int EVENT_OEMHOOK_MODEM_STATS_DATA_CLEAR = 76;
    public static final int EVENT_OEMHOOK_MODEM_STATS_DATA_GET = 62;
    public static final int EVENT_OEMHOOK_MODEM_STATS_ID_ALLOC = 58;
    public static final int EVENT_OEMHOOK_MODEM_STATS_ID_FREE = 59;
    public static final int EVENT_OEMHOOK_MODEM_STATS_REMOVE_ID = 61;
    public static final int EVENT_OEMHOOK_MODEM_STATS_SWITCH_GET = 10;
    public static final int EVENT_OEMHOOK_MODEM_STATS_SWITCH_SET = 11;
    public static final int EVENT_OEMHOOK_MODEM_SW_VERSION_GET = 19;
    public static final int EVENT_OEMHOOK_MODEM_SYNC_GAME_MODE = 79;
    public static final int EVENT_OEMHOOK_MODEM_TEST = 73;
    public static final int EVENT_OEMHOOK_MTB_CLASS_MASK_GET = 81;
    public static final int EVENT_OEMHOOK_NOTIFY_SHUTDOWN = 14;
    public static final int EVENT_OEMHOOK_NOTIFY_UIM_STATE = 22;
    public static final int EVENT_OEMHOOK_NULL = 0;
    public static final int EVENT_OEMHOOK_NV_EFS_SYNC = 35;
    public static final int EVENT_OEMHOOK_PROP_GET = 24;
    public static final int EVENT_OEMHOOK_PROP_SET = 25;
    public static final int EVENT_OEMHOOK_RF_ANT_DEVICE_DATA_GET = 32;
    public static final int EVENT_OEMHOOK_RF_CABLE_STATUS_GET = 65;
    public static final int EVENT_OEMHOOK_RF_DEVICE_INFO_GET = 8;
    public static final int EVENT_OEMHOOK_RF_DEVICE_TEST_SET = 7;
    public static final int EVENT_OEMHOOK_RF_MIPI_INFO_GET = 9;
    public static final int EVENT_OEMHOOK_RILD_RESET = 103;
    public static final int EVENT_OEMHOOK_RILD_TEST = 104;
    public static final int EVENT_OEMHOOK_SCENES_MODE_SET = 117;
    public static final int EVENT_OEMHOOK_SCREEN_STATUS_NOTIFY = 27;
    public static final int EVENT_OEMHOOK_SET_SAR_STATE = 28;
    public static final int EVENT_OEMHOOK_SHELL_CMD_EXECUTE = 26;
    public static final int EVENT_OEMHOOK_SSR_BY_ERROR_FATAL = 31;
    public static final int EVENT_OEMHOOK_UIM_POWER_EX = 85;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_DELTE = 6;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_READ = 4;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_STAT = 51;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_WRITE = 5;
    public static final int EVENT_OEMHOOK_XIAOMI_MODEM_HAL_CHIP_ID_GET = 82;
    public static final int EVENT_OEMHOOK_XIAOMI_NV_DELTE = 3;
    public static final int EVENT_OEMHOOK_XIAOMI_NV_READ = 1;
    public static final int EVENT_OEMHOOK_XIAOMI_NV_WRITE = 2;
    public static final int EVENT_OEMHOOK_XIAOMI_QCN_BACKUP = 33;
    public static final int EVENT_OEMHOOK_XIAOMI_QCN_RECOVERY = 34;
    public static final int HOOK_TYPE_MTK = 1;
    public static final int HOOK_TYPE_QCOM = 2;
    public static final int HOOK_TYPE_UNKNOW = 0;
    private static final String LOG_TAG = "OemHookAgent";
    private static final int MAX_PHONE_COUNT_DUAL_SIM = 2;
    private static final int MAX_PHONE_COUNT_SINGLE_SIM = 1;
    private static final int MAX_PHONE_COUNT_TRI_SIM = 3;
    private static final String MIRILHOOK_CLASS_NAME = "com.xiaomi.mirilhook.MiRilHook";
    private static final String MIRILHOOK_JAR_PATH = "/system_ext/framework/mirilhook.jar";
    public static final int MODEM_CHIP_NAME_ID_INVALID = 0;
    public static final int MODEM_FEATURE_TYPE_BIG_DATA = 2;
    public static final int MODEM_FEATURE_TYPE_HYDRA = 1;
    public static final int MODEM_FEATURE_TYPE_INIT = 0;
    public static final int MODEM_FEATURE_TYPE_USER_FEEDBACK = 3;
    private static final int MODEM_STATS_DATA_INVALID = 65535;
    private static final int MODEM_STATS_DATA_INVALID_1 = -1;
    private static final float MODEM_STATS_DATA_INVALID_2 = -1.0f;
    private static final String PROPERTY_MULTI_SIM_CONFIG = "persist.radio.multisim.config";
    private static final String QCRILHOOKCALLBACK_CLASS_NAME = "com.qualcomm.qcrilhook.QcRilHookCallback";
    private static final String QCRILHOOK_JAR_PATH = "/system_ext/framework/qcrilhook.jar";
    public static final int SLOT_2 = 2;
    public static final int UIM_POWER_DOWN = 0;
    public static final int UIM_POWER_UP = 1;
    private static Context mContext = null;
    private static OemHookAgent mHookAgent = null;
    public static int mHookType = 0;
    private static boolean mMiRilHookReady = false;
    public Handler mMtkHandler = new Handler() { // from class: com.xiaomi.modem.OemHookAgent.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            OemHookAgent.log("handleMessage msg id: " + message.what);
            int i2 = message.what;
            if (i2 == -2) {
                OemHookAgent.log("EVENT_MTKRIL_HOOK_DISCONNECTED");
                synchronized (OemHookAgent.mHookDisconnectedRegistrants) {
                    OemHookAgent.mMiRilHookReady = false;
                    OemHookAgent.mHookDisconnectedRegistrants.notifyRegistrants();
                }
                return;
            }
            if (i2 != -1) {
                OemHookAgent.log("invalid msg id " + message.what);
                return;
            }
            OemHookAgent.log("EVENT_MTKRIL_HOOK_READY");
            synchronized (OemHookAgent.mHookReadyRegistrants) {
                OemHookAgent.mMiRilHookReady = true;
                OemHookAgent.mHookReadyRegistrants.notifyRegistrants();
            }
        }
    };
    private static RegistrantList mHookReadyRegistrants = new RegistrantList();
    private static RegistrantList mHookDisconnectedRegistrants = new RegistrantList();
    private static DexClassLoader mMiRilJarLoader = null;
    private static Class mMiRilHookClass = null;
    private static Object mMiRilHookObj = null;
    private static DexClassLoader mQcRilJarLoader = null;
    private static Class mQcRilHookCallbackClass = null;
    private static Object mQcRilHookCallbackObj = null;
    private static int mNumPhones = 0;

    class QcRilHookCbMethodProxy implements InvocationHandler {
        QcRilHookCbMethodProxy() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            OemHookAgent.log("QcRilHookCbMethodProxy callbackMethod name:" + method.getName());
            if (method.getName().equals("onQcRilHookReady")) {
                OemHookAgent.log("QcRilHookCbMethodProxy onQcRilHookReady");
                synchronized (OemHookAgent.mHookReadyRegistrants) {
                    OemHookAgent.mMiRilHookReady = true;
                    OemHookAgent.mHookReadyRegistrants.notifyRegistrants();
                }
            }
            if (!method.getName().equals("onQcRilHookDisconnected")) {
                return null;
            }
            OemHookAgent.log("QcRilHookCbMethodProxy onQcRilHookDisconnected");
            synchronized (OemHookAgent.mHookDisconnectedRegistrants) {
                OemHookAgent.mMiRilHookReady = false;
                OemHookAgent.mHookDisconnectedRegistrants.notifyRegistrants();
            }
            return null;
        }
    }

    private OemHookAgent(Context context) {
        if (2 == mHookType) {
            try {
                Constructor constructor = mMiRilHookClass.getConstructor(Context.class, mQcRilHookCallbackClass, String.class);
                log("HOOK_TYPE_QCOM Constructor, qcRilHookConstructor = " + constructor + ", mQcRilHookCallbackClass = " + mQcRilHookCallbackClass + ", mQcRilHookCallbackObj = " + mQcRilHookCallbackObj + ", mMiRilHookClass = " + mMiRilHookClass);
                mMiRilHookObj = constructor.newInstance(context, mQcRilHookCallbackObj, ActivityThread.currentPackageName());
            } catch (Exception e2) {
                log("load OemHookAgent failed, error:" + e2.toString());
                e2.printStackTrace();
            }
        }
        if (1 == mHookType) {
            try {
                Constructor constructor2 = mMiRilHookClass.getConstructor(Context.class, Handler.class);
                log("HOOK_TYPE_MTK Constructor, miRilHookConstructor = " + constructor2 + ", mMtkHandler = " + this.mMtkHandler + ", mMiRilHookClass = " + mMiRilHookClass);
                mMiRilHookObj = constructor2.newInstance(context, this.mMtkHandler);
            } catch (Exception e3) {
                log("load OemHookAgent failed, error:" + e3.toString());
                e3.printStackTrace();
            }
        }
        log("Constructor, context = " + context + ", mMiRilHookObj = " + mMiRilHookObj);
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, String str3, Handler handler) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal1: " + str2 + ", strVal2: " + str3 + ", hdlVal: " + handler);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, String.class, Handler.class).invoke(mMiRilHookObj, str2, str3, handler);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private boolean checkMiRilHookMethod(String str) {
        if (str == null) {
            log("hookMethodName is null");
            return false;
        }
        if (mMiRilHookClass == null) {
            log("mMiRilHookClass is null");
            return false;
        }
        if (mMiRilHookObj != null) {
            return true;
        }
        log("mMiRilHookObj is null;");
        return false;
    }

    public static String getCurrentClassName() {
        return new Throwable().getStackTrace()[1].getClassName();
    }

    public static String getCurrentMethodName() {
        return new Throwable().getStackTrace()[1].getMethodName();
    }

    public static OemHookAgent getHook() {
        log("getHook, mMiRilHookReady = " + mMiRilHookReady + ", mHookAgent = " + mHookAgent);
        if (true == mMiRilHookReady) {
            return mHookAgent;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d("OemHookAgent(" + ModemUtils.getCurProcessName(mContext) + ")", str);
    }

    private static void makeHook(Context context) {
        log("makeHook, context = " + context);
        if (context == null) {
            log("context is null");
            return;
        }
        if (mContext == null) {
            mContext = context;
            log("init mContext = " + mContext);
        }
        if (mMiRilJarLoader == null) {
            mMiRilJarLoader = new DexClassLoader(MIRILHOOK_JAR_PATH, context.getDir("Jar", 0).getAbsolutePath(), null, OemHookAgent.class.getClassLoader());
            log("init mMiRilJarLoader = " + mMiRilJarLoader);
        }
        if (mMiRilHookClass == null) {
            try {
                Class clsLoadClass = mMiRilJarLoader.loadClass(MIRILHOOK_CLASS_NAME);
                mMiRilHookClass = clsLoadClass;
                mHookType = ((Integer) clsLoadClass.getMethod("getHookType", new Class[0]).invoke(null, new Object[0])).intValue();
                log("mHookType = " + mHookType);
            } catch (Exception e2) {
                log("load mMiRilHookClass failed, error:" + e2.toString());
                e2.printStackTrace();
            }
            log("init mMiRilHookClass = " + mMiRilHookClass);
        }
        if (2 == mHookType) {
            if (mQcRilJarLoader == null) {
                mQcRilJarLoader = new DexClassLoader(QCRILHOOK_JAR_PATH, context.getDir("Jar", 0).getAbsolutePath(), null, OemHookAgent.class.getClassLoader());
                log("init mQcRilJarLoader = " + mQcRilJarLoader);
            }
            if (mQcRilHookCallbackClass == null) {
                try {
                    mQcRilHookCallbackClass = mQcRilJarLoader.loadClass(QCRILHOOKCALLBACK_CLASS_NAME);
                } catch (Exception e3) {
                    log("load mQcRilHookCallbackClass failed, error:" + e3.toString());
                    e3.printStackTrace();
                }
                log("init mQcRilHookCallbackClass = " + mQcRilHookCallbackClass);
            }
            if (mQcRilHookCallbackObj == null) {
                try {
                    mQcRilHookCallbackObj = Proxy.newProxyInstance(OemHookAgent.class.getClassLoader(), new Class[]{mQcRilHookCallbackClass}, new QcRilHookCbMethodProxy());
                } catch (Exception e4) {
                    log("load mQcRilHookCallbackObj failed, error:" + e4.toString());
                    e4.printStackTrace();
                }
            }
        }
        if (mHookAgent == null) {
            mHookAgent = new OemHookAgent(mContext);
            log("init mHookAgent = " + mHookAgent);
        }
    }

    private void printMiRilHookMethodException(String str, Exception exc) {
        log("load " + str + " failed, error:" + exc.toString());
        exc.printStackTrace();
    }

    private void printMiRilHookMethodInfo(String str, Object obj) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj);
    }

    public static void registerHookDisconnectedEvent(Context context, Handler handler, int i2, Object obj) {
        log("registerHookDisconnectedEvent, context = " + context + ", handler = " + handler + ", what = " + i2 + ", obj = " + obj);
        StringBuilder sb = new StringBuilder();
        sb.append("mMiRilHookReady = ");
        sb.append(mMiRilHookReady);
        log(sb.toString());
        Registrant registrant = new Registrant(handler, i2, obj);
        synchronized (mHookDisconnectedRegistrants) {
            mHookDisconnectedRegistrants.add(registrant);
            if (mMiRilHookReady) {
                registrant.notifyRegistrant();
            }
        }
    }

    public static void registerHookReadyEvent(Context context, Handler handler, int i2, Object obj) {
        log("registerHookReadyEvent, context = " + context + ", handler = " + handler + ", what = " + i2 + ", obj = " + obj + ", mMiRilHookReady = " + mMiRilHookReady);
        makeHook(context);
        Registrant registrant = new Registrant(handler, i2, obj);
        synchronized (mHookReadyRegistrants) {
            mHookReadyRegistrants.add(registrant);
            if (mMiRilHookReady) {
                registrant.notifyRegistrant();
            }
        }
    }

    public static void unregisterQcRilHookDisconnected(Handler handler) {
        log("unregisterQcRilHookDisconnected, handler = " + handler);
        synchronized (mHookDisconnectedRegistrants) {
            mHookDisconnectedRegistrants.remove(handler);
        }
    }

    public static void unregisterQcRilHookReady(Handler handler) {
        log("unregisterQcRilHookReady, handler = " + handler);
        synchronized (mHookReadyRegistrants) {
            mHookReadyRegistrants.remove(handler);
        }
    }

    public void bindFiveGServiceConnection(String str, Context context, Registrant registrant) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, context, registrant);
    }

    public boolean clearIndResource() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public boolean clearLteRrcStateData(int i2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2)).booleanValue();
    }

    public boolean closeEvs() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public boolean closeModemStats(String str) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, str)).booleanValue();
    }

    public boolean deregNr5g22MimoError() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public boolean deregRadioTech() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public void dispose() {
        log("dispose, mHookAgent = " + mHookAgent);
        if (mHookAgent != null) {
            log("dispose");
            Class cls = mMiRilHookClass;
            if (cls == null) {
                log("mMiRilHookClass is null, return");
                return;
            }
            if (mMiRilHookObj == null) {
                log("mMiRilHookObj is null, return");
                return;
            }
            try {
                cls.getMethod("dispose", new Class[0]).invoke(mMiRilHookObj, new Object[0]);
                mHookAgent = null;
            } catch (Exception e2) {
                log("load dispose failed, error:" + e2.toString());
                e2.printStackTrace();
                return;
            }
        }
        mContext = null;
        mMiRilHookReady = false;
    }

    public boolean evsIsOpen() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    protected void finalize() {
        log("finalize");
    }

    public int getAntNewConfig() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID))).intValue();
    }

    public int getAntNumber() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID))).intValue();
    }

    public int getAntOldConfig() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID))).intValue();
    }

    public int getAntPath() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID))).intValue();
    }

    public int getHOFreq(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getHOPci(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public long getHOTime(int i2) {
        return ((Long) callMiRilHookMethod(getCurrentMethodName(), (Object) 0, i2)).longValue();
    }

    public int getHOType(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getImsSipMessageInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteBlerAbnormalCList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLteBlerAbnormalInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteBlerAbnormalPList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getLteBsrLcg3(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteCQI(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteCellId(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteDataSceneInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getLteDlBandwidth(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteEarfcn(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteFreqBandInd(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteFullSchInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteFullSchList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public float getLteFullSchSfnRatio(int i2) {
        return ((Float) callMiRilHookMethod(getCurrentMethodName(), Float.valueOf(MODEM_STATS_DATA_INVALID_2), i2)).floatValue();
    }

    public ByteBuffer getLteImsRtpPacketLossList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteImsSipMessageList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getLteLayersNumForCrnti(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteMcc(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteMcsInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteMeasInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLteMl1IratMeasList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getLteMnc(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteNasEmmStateList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLteNasRejectInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteNasRejectList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getLtePci(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLtePdcpDlDataList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLtePdcpUlDataList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getLtePdschMcs(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePdschMod(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePdschRb(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePhrInd(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePucchPathLoss(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePucchTx(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePuschMcs(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePuschMod(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePuschPathLoss(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLtePuschTx(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteRI(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteRachFailInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteRfBandAndAntennaNumberInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLteRlfInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteRlfOfWeakSignalList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getLteRrcActiveTime(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), (Object) (-1), i2)).intValue();
    }

    public int getLteRrcSetupCount(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), (Object) (-1), i2)).intValue();
    }

    public int getLteRsrp(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteRsrq(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteRssi(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getLteSnr(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteT3410ExpiryList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteT3411ExpiryList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getLteT3430ExpiryList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getLteTac(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteTimerExpiryInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLteTimerExpiryList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLteTxPowerInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getLteTxPowerList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getLteUlBandwidth(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getLteUlBlerInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getMbnFeatureCurrentConfig() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), 0)).intValue();
    }

    public int getMbnFeatureDefaultConfig() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), 0)).intValue();
    }

    public ByteBuffer getMcsTrmAsdivInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getModemFixRatCount(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), (Object) 255, i2)).intValue();
    }

    public ByteBuffer getNasMm5gStateList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getNr5gBand(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gBandWidth(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public float getNr5gBler(int i2) {
        return ((Float) callMiRilHookMethod(getCurrentMethodName(), Float.valueOf(MODEM_STATS_DATA_INVALID_2), i2)).floatValue();
    }

    public ByteBuffer getNr5gBlerAbnormalInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getNr5gBlerAbnormalList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getNr5gCQI(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gDataSceneInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getNr5gDlArfcn(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gDlMcs(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gFreq(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gFullSchInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getNr5gFullSchList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public float getNr5gFullSchSfnRatio(int i2) {
        return ((Float) callMiRilHookMethod(getCurrentMethodName(), Float.valueOf(MODEM_STATS_DATA_INVALID_2), i2)).floatValue();
    }

    public ByteBuffer getNr5gHighTOosList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getNr5gLayersNumForCrnti(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gMcsInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getNr5gMeasInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gNasRejectInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getNr5gNasRejectList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getNr5gPci(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gPdcpDlDataList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gPdcpUlDataList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getNr5gPdschMcs(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gPdschMod(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gPucchPathLoss(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gPucchTx(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gPuschPathLoss(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gPuschTx(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public int getNr5gRI(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gRachFailInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getNr5gRbNum(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gRfBandAndAntennaNumberInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gRlfInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getNr5gRlfList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gRrcIratHoFromNrList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gRrcIratRedirFromNrList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gRrcIratReselFromNrList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public float getNr5gRsrp(int i2) {
        return ((Float) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).floatValue();
    }

    public float getNr5gRsrq(int i2) {
        return ((Float) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).floatValue();
    }

    public int getNr5gShortBsrIdx(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gT3510ExpiryList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getNr5gT3511ExpiryList() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getNr5gTA(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gTimerExpiryList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getNr5gTxModeOfPusch(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNr5gTxPowerInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gTxPowerList(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer getNr5gUlBlerInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int getNr5gUlMcs(int i2) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID), i2)).intValue();
    }

    public ByteBuffer getNsaBandCombInfo(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public int getSarDsi() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), -1)).intValue();
    }

    public int getSigPath() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), Integer.valueOf(MODEM_STATS_DATA_INVALID))).intValue();
    }

    public ByteBuffer getSwitchConfigAndAntennaInfo() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer getTestExpendRequestDataByTimestamp(int i2, long j2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, j2);
    }

    public boolean isLteRrcActive(int i2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2)).booleanValue();
    }

    public boolean isModemFeatureSupported(int i2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2)).booleanValue();
    }

    public boolean isOemProduct() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public int onGetEsimStatus() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), -1)).intValue();
    }

    public void onHookAgentResponse(int i2, byte[] bArr, int i3) {
        log("onHookAgentResponse, phoneId: " + i3 + ", msgWhat: " + i2);
    }

    public String onHookAtCmdSendSync(String str, int i2) {
        return (String) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, i2);
    }

    public ByteBuffer onHookBigEfsReadSync(int i2, String str) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, str);
    }

    public boolean onHookCommonMsg(int i2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2)).booleanValue();
    }

    public boolean onHookCommonMsgBySub(int i2, int i3) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), (Object) Boolean.FALSE, i2, i3)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onHookCommonMsgSyncBySub(int i2, int i3) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3);
    }

    public ByteBuffer onHookCommonMsgSyncForBigResponse(int i2, int i3) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3);
    }

    public ByteBuffer onHookCopyFileSync(String str, String str2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, str2);
    }

    public ByteBuffer onHookDiagSendSync(byte[] bArr) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, bArr);
    }

    public ByteBuffer onHookDiagSendWithResultSync(byte[] bArr, int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, bArr, i2);
    }

    public boolean onHookDsdaCurrentStatusGetSync() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public boolean onHookDsdaDefaultStatusGetSync() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public boolean onHookDsdaIsSupportedSync() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public ByteBuffer onHookEfsOptSync(int i2, String str, int i3) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, str, i3);
    }

    public ByteBuffer onHookEfsWriteSync(int i2, String str, byte[] bArr) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, str, bArr);
    }

    public boolean onHookEfsWriteSyncEx(int i2, String str, byte[] bArr) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, str, bArr)).booleanValue();
    }

    public ByteBuffer onHookGetDeviceDataSync(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onHookGetDeviceInfoSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer onHookGetMipiInfoSync(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onHookHydraDebugInfoForUserGetSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer onHookHydraDebugInfoGetSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer onHookHydraDiagNotSupportInfoGetSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer onHookHydraStatsSetSync(String str, int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, i2);
    }

    public ByteBuffer onHookMbnCheckSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer onHookMbnOptSync(int i2, int i3, String str, int i4) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, str, i4);
    }

    public ByteBuffer onHookMbnReactivateSync(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onHookMbnRefreshSync(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onHookMiBroadcastStateGetSync(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onHookMiBroadcastStateSetSync(int i2, int i3) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3);
    }

    public ByteBuffer onHookModSwVerGetSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public int onHookModemChipNameIdGetSync() {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), 0)).intValue();
    }

    public ByteBuffer onHookModemHydraCheckSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer onHookModemSceneRecognitionReqSync(byte[] bArr) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, bArr);
    }

    public ByteBuffer onHookNvOptSync(int i2, int i3, int i4) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4);
    }

    public ByteBuffer onHookNvWriteSync(int i2, int i3, byte[] bArr) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, bArr);
    }

    public boolean onHookNvWriteSyncEx(int i2, int i3, byte[] bArr) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, i3, bArr)).booleanValue();
    }

    public String onHookPropGetSync(String str, String str2) {
        return (String) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, str2);
    }

    public boolean onHookPropSet(String str, String str2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, str, str2)).booleanValue();
    }

    public ByteBuffer onHookPropSetSync(String str, String str2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, str2);
    }

    public ByteBuffer onHookSarDsiGetSync() {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public ByteBuffer onHookSarDsiSetSync(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onHookSetDeviceStateSync(int i2, byte[] bArr, int i3, byte[] bArr2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, bArr, i3, bArr2);
    }

    public ByteBuffer onHookShellCmdExeuteSync(String str) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str);
    }

    public boolean onHookUimPowerReqEx(int i2, int i3, int i4) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, i3, i4)).booleanValue();
    }

    public ByteBuffer onMtkGetCmSystemServiceInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public void onMtkGetDMFInfoByAT(int i2, int i3, Handler handler) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, handler);
    }

    public ByteBuffer onMtkGetEl1PucchCsfByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetEl1PuschPowerControlByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetEl1ServingCellMeasByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetEl1TasInformationByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetEl2IpThroughputDlInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetEmEl1StatusByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetErrcMeasResultInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetErrcNewCellInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetErrcRLFInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetErrcServingCellInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNeighborCellMeasurementByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl1CsiReportByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl1NeighborCellMeasurementByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl1PuschPowerControlByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl1ServingCellCsirsMeaByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl1ServingCellMeasByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl1SyncTrsMeasurementByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl1TasInformationByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNl2SdapThroughputDlInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNrrcMeasInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNrrcServingCellInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public ByteBuffer onMtkGetNrrcStateChangeInfoByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public void onMtkHookPropGetSync(String str, String str2, Handler handler) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, str2, handler);
    }

    public void onMtkHookPropSetSync(String str, String str2, Handler handler) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, str2, handler);
    }

    public void onMtkSendAtCommand(String str, Handler handler) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, handler);
    }

    public ByteBuffer onMtkSendDMFCmdByAT(int i2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public void onMtkStartDMFByAT() {
        callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public void onMtkStartURCByAT() {
        callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public void onMtkStopDMFByAT() {
        callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public void onMtkStopURCByAT() {
        callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public void onRegisterAtUrcInd(int i2, int i3, Handler handler) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, handler);
    }

    public String onSendAtCommandByMtkTelephonyManagerEx(int i2, int i3) {
        return (String) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3);
    }

    public int onSetEsimStatus(int i2, boolean z) {
        return ((Integer) callMiRilHookMethod(getCurrentMethodName(), (Object) (-1), i2, z)).intValue();
    }

    public boolean openEvs() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public String propGet(String str, String str2) {
        return (String) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, str2);
    }

    public boolean propSet(String str, String str2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, str, str2)).booleanValue();
    }

    public boolean qcRilFeatureCtlCmn(int i2, int i3, int i4) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, i3, i4)).booleanValue();
    }

    public byte[] qcRilFeatureQueryCmn(int i2, int i3, int i4) {
        return (byte[]) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4);
    }

    public byte[] qcRilReadOemNV(int i2) {
        return (byte[]) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2);
    }

    public boolean regNr5g22MimoError(int i2, Handler handler, int i3, Object obj) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, handler, i3, obj)).booleanValue();
    }

    public boolean regRadioTech(int i2, Handler handler, int i3, Object obj) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, handler, i3, obj)).booleanValue();
    }

    public boolean registerOemHookCallbackForMiBroadcastDataRegistrant(String str, Registrant registrant, String str2, byte[] bArr) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, str, registrant, str2, bArr)).booleanValue();
    }

    public boolean resetModemStats() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public ByteBuffer sendQcRilHookMsgSync(int i2, byte[] bArr, int i3, int i4) {
        String currentMethodName = getCurrentMethodName();
        Object objInvoke = null;
        printMiRilHookMethodInfo(currentMethodName, null);
        log("Paramters info: , requestId: " + i2 + ", byteArryVal: " + bArr + ", responseSize: " + i3 + ", phoneId: " + i4);
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            objInvoke = cls.getMethod(currentMethodName, cls2, byte[].class, cls2, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), bArr, Integer.valueOf(i3), Integer.valueOf(i4));
        } catch (Exception e2) {
            printMiRilHookMethodException(currentMethodName, e2);
        }
        return (ByteBuffer) objInvoke;
    }

    public boolean setSarDsi(int i2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2)).booleanValue();
    }

    public boolean startModemStats(String str) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, str)).booleanValue();
    }

    public void unBindFiveGServiceConnection() {
        callMiRilHookMethod(getCurrentMethodName(), null);
    }

    public void unRegisterOemHookCallbackForMiBroadcastData(String str) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, str);
    }

    public void unRegisterOemHookCallbackForMiBroadcastDataRegistrant(String str, Registrant registrant) {
        callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, registrant);
    }

    public boolean closeModemStats() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public String onHookAtCmdSendSync(String str, int i2, int i3) {
        return (String) callMiRilHookMethod(getCurrentMethodName(), (Object) null, str, i2, i3);
    }

    public boolean onHookCommonMsg(int i2, int i3) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), (Object) Boolean.FALSE, i2, i3)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2, int i3) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3);
    }

    public ByteBuffer onHookCommonMsgSyncForBigResponse(int i2, int i3, int i4) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4);
    }

    public ByteBuffer onHookEfsWriteSync(int i2, String str, byte[] bArr, int i3) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, str, bArr, i3);
    }

    public boolean onHookEfsWriteSyncEx(int i2, String str, byte[] bArr, int i3) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, str, bArr, i3)).booleanValue();
    }

    public ByteBuffer onHookMiBroadcastStateSetSync(int i2, int i3, int i4) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4);
    }

    public ByteBuffer onHookNvWriteSync(int i2, int i3, byte[] bArr, int i4) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, bArr, i4);
    }

    public boolean onHookNvWriteSyncEx(int i2, int i3, byte[] bArr, int i4) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, i3, bArr, i4)).booleanValue();
    }

    public ByteBuffer onMtkSendDMFCmdByAT(int i2, int i3) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3);
    }

    public boolean qcRilFeatureCtlCmn(int i2, int i3, int i4, byte[] bArr) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, i3, i4, bArr)).booleanValue();
    }

    public byte[] qcRilFeatureQueryCmn(int i2, int i3, int i4, byte[] bArr) {
        return (byte[]) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4, bArr);
    }

    public boolean registerOemHookCallbackForMiBroadcastDataRegistrant(String str, Registrant registrant, String str2, byte[] bArr, int i2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, str, registrant, str2, bArr, i2)).booleanValue();
    }

    public boolean startModemStats() {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE)).booleanValue();
    }

    public boolean onHookCommonMsg(int i2, long j2) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, j2)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2, long j2) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, j2);
    }

    public boolean onHookCommonMsg(int i2, int i3, int i4) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, i3, i4)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2, int i3, int i4) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4);
    }

    public boolean onHookCommonMsg(int i2, int i3, int i4, int i5) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, i3, i4, i5)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2, int i3, int i4, int i5) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4, i5);
    }

    public boolean onHookCommonMsg(int i2, int i3, int i4, int i5, int i6) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4, i5, i6)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2, int i3, int i4, int i5, int i6) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, i3, i4, i5, i6);
    }

    public boolean onHookCommonMsg(int i2, String str) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, str)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2, String str) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, str);
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, String str3) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal1: " + str2 + ", strVal2: " + str3);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, String.class).invoke(mMiRilHookObj, str2, str3);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    public boolean onHookCommonMsg(int i2, byte[] bArr) {
        return ((Boolean) callMiRilHookMethod(getCurrentMethodName(), Boolean.FALSE, i2, bArr)).booleanValue();
    }

    public ByteBuffer onHookCommonMsgSync(int i2, byte[] bArr) {
        return (ByteBuffer) callMiRilHookMethod(getCurrentMethodName(), (Object) null, i2, bArr);
    }

    public ByteBuffer sendQcRilHookMsgSync(int i2, byte[] bArr, int i3) {
        String currentMethodName = getCurrentMethodName();
        Object objInvoke = null;
        printMiRilHookMethodInfo(currentMethodName, null);
        log("Paramters info: , requestId = " + i2 + ", request = " + bArr + ", phoneId = " + i3);
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            objInvoke = cls.getMethod(currentMethodName, cls2, byte[].class, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), bArr, Integer.valueOf(i3));
        } catch (Exception e2) {
            printMiRilHookMethodException(currentMethodName, e2);
        }
        return (ByteBuffer) objInvoke;
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, Registrant registrant, String str3, byte[] bArr, int i2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal1: " + str2 + ", registVal: " + registrant + ", strVal2: " + str3 + ", byteArryVal: " + bArr + ", iVal: " + i2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, Registrant.class, String.class, byte[].class, Integer.TYPE).invoke(mMiRilHookObj, str2, registrant, str3, bArr, Integer.valueOf(i2));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    public ByteBuffer sendQcRilHookMsgSync(int i2, byte[] bArr) {
        return sendQcRilHookMsgSync(i2, bArr, 0);
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, Registrant registrant, String str3, byte[] bArr) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal1: " + str2 + ", registVal: " + registrant + ", strVal2: " + str3 + ", byteArryVal: " + bArr);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, Registrant.class, String.class, byte[].class).invoke(mMiRilHookObj, str2, registrant, str3, bArr);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, Registrant registrant) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal: " + str2 + ", registVal: " + registrant);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, Registrant.class).invoke(mMiRilHookObj, str2, registrant);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, int i2, int i3) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal: " + str2 + ", iVal1: " + i2 + ", iVal2: " + i3);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, String.class, cls2, cls2).invoke(mMiRilHookObj, str2, Integer.valueOf(i2), Integer.valueOf(i3));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, int i2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal: " + str2 + ", iVal: " + i2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, Integer.TYPE).invoke(mMiRilHookObj, str2, Integer.valueOf(i2));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, Handler handler) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal: " + str2 + ", hdlVal: " + handler);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, Handler.class).invoke(mMiRilHookObj, str2, handler);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, int i3, Handler handler) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", hdlVal: " + handler);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, Handler.class).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), handler);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2, Context context, Registrant registrant) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal: " + str2 + ", cntVal: " + context + ", registVal: " + registrant);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class, Context.class, Registrant.class).invoke(mMiRilHookObj, str2, context, registrant);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, String str2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", strVal: " + str2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, String.class).invoke(mMiRilHookObj, str2);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, String str2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal: " + i2 + ", strVal: " + str2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, Integer.TYPE, String.class).invoke(mMiRilHookObj, Integer.valueOf(i2), str2);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, String str2, byte[] bArr, int i3) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", strVal: " + str2 + ", byteArryVal: " + bArr + ", iVal2: " + i3);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, String.class, byte[].class, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), str2, bArr, Integer.valueOf(i3));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, String str2, byte[] bArr) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal: " + i2 + ", strVal: " + str2 + ", byteArryVal: " + bArr);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, Integer.TYPE, String.class, byte[].class).invoke(mMiRilHookObj, Integer.valueOf(i2), str2, bArr);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, String str2, int i3) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", strVal: " + str2 + ", iVal2: " + i3);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, String.class, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), str2, Integer.valueOf(i3));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, long j2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal: " + i2 + ", lVal: " + j2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, Integer.TYPE, Long.TYPE).invoke(mMiRilHookObj, Integer.valueOf(i2), Long.valueOf(j2));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, int i3, int i4, int i5, int i6) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", iVal3: " + i4 + ", iVal4: " + i5 + ", iVal5: " + i6);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, cls2, cls2, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, int i3, int i4, int i5) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", iVal3: " + i4 + ", iVal4: " + i5);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, cls2, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, int i3, int i4, byte[] bArr) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", iVal3: " + i4 + ", byteArryVal: " + bArr);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, cls2, byte[].class).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), bArr);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, int i3, int i4) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", iVal3: " + i4);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    public Object callMiRilHookMethod(String str, Object obj, int i2, int i3, String str2, int i4) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", strVal: " + str2 + ", iVal3: " + i4);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, String.class, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), str2, Integer.valueOf(i4));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    public Object callMiRilHookMethod(String str, Object obj, int i2, int i3, byte[] bArr, int i4) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", byteArryVal: " + bArr + ", iVal3: " + i4);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, byte[].class, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), bArr, Integer.valueOf(i4));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    public Object callMiRilHookMethod(String str, Object obj, int i2, int i3, byte[] bArr) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3 + ", byteArryVal: " + bArr);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2, byte[].class).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3), bArr);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, int i3) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + i3);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, cls2).invoke(mMiRilHookObj, Integer.valueOf(i2), Integer.valueOf(i3));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, boolean z) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", iVal2: " + z);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, Integer.TYPE, Boolean.TYPE).invoke(mMiRilHookObj, Integer.valueOf(i2), Boolean.valueOf(z));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    public Object callMiRilHookMethod(String str, Object obj, int i2, Handler handler, int i3, Object obj2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", hdl: " + handler + ", iVal2: " + i3 + ", obj: " + obj2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, Handler.class, cls2, Object.class).invoke(mMiRilHookObj, Integer.valueOf(i2), handler, Integer.valueOf(i3), obj2);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, byte[] bArr, int i3, byte[] bArr2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal1: " + i2 + ", byteArryVal: " + bArr + ", iVal2: " + i3 + ", byteArryVal2: " + bArr2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            Class cls = mMiRilHookClass;
            Class<?> cls2 = Integer.TYPE;
            return cls.getMethod(str, cls2, byte[].class, cls2, byte[].class).invoke(mMiRilHookObj, Integer.valueOf(i2), bArr, Integer.valueOf(i3), bArr2);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2, byte[] bArr) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal: " + i2 + ", byteArryVal: " + bArr);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, Integer.TYPE, byte[].class).invoke(mMiRilHookObj, Integer.valueOf(i2), bArr);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, int i2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", iVal: " + i2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, Integer.TYPE).invoke(mMiRilHookObj, Integer.valueOf(i2));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, byte[] bArr, int i2) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", byteArryVal: " + bArr + ", iVal: " + i2);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, byte[].class, Integer.TYPE).invoke(mMiRilHookObj, bArr, Integer.valueOf(i2));
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj, byte[] bArr) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj + ", byteArryVal: " + bArr);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, byte[].class).invoke(mMiRilHookObj, bArr);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }

    private Object callMiRilHookMethod(String str, Object obj) {
        log("Mtb is calling the mirilhook method: " + str + ", defObj: " + obj);
        if (!checkMiRilHookMethod(str)) {
            log("checkMiRilHookMethod failed");
            return obj;
        }
        try {
            return mMiRilHookClass.getMethod(str, new Class[0]).invoke(mMiRilHookObj, new Object[0]);
        } catch (Exception e2) {
            log("load " + str + " failed, error:" + e2.toString());
            e2.printStackTrace();
            return obj;
        }
    }
}
