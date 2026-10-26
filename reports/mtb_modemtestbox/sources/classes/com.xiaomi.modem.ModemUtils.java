package com.xiaomi.modem;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.os.AsyncResult;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.os.SystemProperties;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;
import com.android.internal.telephony.Phone;
import com.xiaomi.mtb.MtbOpenFileDialog;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ModemUtils {
    public static final String BC_ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    public static final String BUNDLE_RETURN_VAL = "RETURN_VAL";
    public static final int COMMON_SWITCH_TYPE_DEF = 2;
    public static final int COMMON_SWITCH_TYPE_OFF = 0;
    public static final int COMMON_SWITCH_TYPE_ON = 1;
    public static final int DATA_TYPE_DECIMALISM = 0;
    public static final int DATA_TYPE_HEX = 1;
    public static final String DEVICE_ESIM_SUPPORT_LIST = "diting,nuwa,fuxi,ziyi";
    public static final int DMS_OP_MODE_FACTORY_TEST_MODE_V01 = 2;
    public static final int DMS_OP_MODE_LOW_POWER_V01 = 1;
    public static final int DMS_OP_MODE_MODE_ONLY_LOW_POWER_V01 = 7;
    public static final int DMS_OP_MODE_NET_TEST_GW_V01 = 8;
    public static final int DMS_OP_MODE_NOT_INIT_V01 = -1;
    public static final int DMS_OP_MODE_OFFLINE_V01 = 3;
    public static final int DMS_OP_MODE_ONLINE_V01 = 0;
    public static final int DMS_OP_MODE_PERSISTENT_LOW_POWER_V01 = 6;
    public static final int DMS_OP_MODE_RESETTING_V01 = 4;
    public static final int DMS_OP_MODE_SHUTTING_DOWN_V01 = 5;
    public static final String EFS_PATH_ASDIV_73841 = "/nv/item_files/mcs/trm/trm_ant_port_override_mode";
    public static final String EFS_PATH_ASDIV_73971 = "/nv/item_files/mcs/trm/ant_div_switch_master";
    public static final byte EFS_VALUE_ASDIV_73841_CONFIG0 = 0;
    public static final byte EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH = -1;
    public static final int IMEI_PROTECT_CLOSE_VALUE = 305419896;
    private static final String LOG_TAG = "ModemUtils";
    private static final int MAX_PHONE_COUNT_DUAL_SIM = 2;
    private static final int MAX_PHONE_COUNT_SINGLE_SIM = 1;
    private static final int MAX_PHONE_COUNT_TRI_SIM = 3;
    public static final int MODEM_CHIP_NAME_ID_INIT = -1;
    public static final int MODEM_CHIP_NAME_ID_INVALID = 0;
    public static final int MODEM_CHIP_NAME_ID_MTK_START = 1001;
    public static final int MODEM_CHIP_NAME_ID_QC_ODM = 100;
    public static final int MODEM_CHIP_NAME_ID_QC_ODM_HQ_DE311 = 101;
    public static final int MODEM_CHIP_NAME_ID_QC_SM7435 = 6;
    public static final int MODEM_CHIP_NAME_ID_QC_SM8350 = 1;
    public static final int MODEM_CHIP_NAME_ID_QC_SM8450 = 2;
    public static final int MODEM_CHIP_NAME_ID_QC_SM8450_DE20 = 3;
    public static final int MODEM_CHIP_NAME_ID_QC_SM8550 = 4;
    public static final int MODEM_CHIP_NAME_ID_QC_SM8635 = 7;
    public static final int MODEM_CHIP_NAME_ID_QC_SM8650 = 5;
    public static final int MODEM_CHIP_NAME_ID_QC_START = 1;
    public static final int NV_ID_453 = 453;
    public static final int NV_ID_6858 = 6858;
    public static final int NV_ID_6859 = 6859;
    public static final int OEM_NV_BYTE_LEN = 124;
    public static final int OEM_NV_BYTE_NO_SMS_AUTO_REG = 20;
    public static final String PROPERTY_DEVICE_INFO_HW = "ro.boot.hwc";
    public static final String PROPERTY_DEVICE_INFO_HW_LEVEL = "ro.boot.hwlevel";
    public static final String PROPERTY_DEVICE_INFO_HW_LEVEL_MP = "MP";
    public static final String PROPERTY_DEVICE_INFO_HW_LEVEL_NULL = "NULL";
    public static final String PROPERTY_DEVICE_INFO_HW_NULL = "NULL";
    public static final String PROPERTY_DEVICE_INFO_SW = "ro.product.mod_device";
    public static final String PROPERTY_DEVICE_INFO_SW_NULL = "NULL";
    public static final String PROPERTY_DEVICE_NAME = "ro.product.device";
    public static final String PROPERTY_DEVICE_NAME_VENDOR = "ro.product.vendor.device";
    private static final String PROPERTY_MULTI_SIM_CONFIG = "persist.radio.multisim.config";
    public static final String PROP_BUILD_PROFILE_FACTORY = "factory";
    public static final String PROP_BUILD_PROFILE_MIUI = "miui";
    public static final String PROP_BUILD_PROFILE_NATIVE = "native";
    public static final String PROP_BUILD_PROFILE_NULL = "NULL";
    public static final String PROP_BUILD_PROFILE_VENDOR = "ro.vendor.radio.build_profile";
    public static final String PROP_BUILD_TAGS = "ro.build.tags";
    public static final String PROP_BUILD_TAGS_NULL = "NULL";
    public static final String PROP_BUILD_TAGS_RELE = "release-keys";
    public static final String PROP_BUILD_TAGS_TEST = "test-keys";
    public static final String PROP_BUILD_TYPE = "ro.build.type";
    public static final String PROP_BUILD_TYPE_USER = "user";
    public static final String PROP_BUILD_VERSION = "ro.build.version.incremental";
    public static final String PROP_BUILD_VERSION_NULL = "";
    public static final String PROP_CERT_TYPE = "ro.cust.test";
    public static final String PROP_CERT_TYPE_CM = "cm";
    public static final String PROP_CERT_TYPE_CT = "ct";
    public static final String PROP_CERT_TYPE_CU = "cu";
    public static final String PROP_CERT_TYPE_NONE = "none";
    public static final String PROP_DEVICE_MOD_NAME_SUFFIX_DEV = "_dev";
    public static final String PROP_DEVICE_MOD_NAME_SUFFIX_FACTORY = "_factory";
    public static final String PROP_DEVICE_MOD_NAME_SUFFIX_NATIVE = "_native";
    public static final String PROP_DEVICE_MOD_NAME_SUFFIX_NULL = "NULL";
    public static final String PROP_DEVICE_MOD_NAME_SUFFIX_PRE = "_pre";
    public static final String PROP_DEVICE_MOD_NAME_SUFFIX_STABLE = "_stable";
    public static final String PROP_DEVICE_NAME_K9 = "renoir";
    public static final String PROP_DEVICE_NAME_L10 = "ingres";
    public static final String PROP_DEVICE_NAME_L11R = "munch";
    public static final String PROP_DEVICE_NAME_L2 = "zeus";
    public static final String PROP_DEVICE_NAME_NULL = "NULL";
    public static final String PROP_DISPLAY_MIPI_CONTROL = "persist.vendor.radio.display_mipi";
    public static final String PROP_DISPLAY_MIPI_CONTROL_INVALID = "2";
    public static final String PROP_DISPLAY_MIPI_CONTROL_OFF = "0";
    public static final String PROP_DISPLAY_MIPI_CONTROL_ON = "1";
    public static final String PROP_HOOK_TEMP_PRINT_SWITCH = "persist.vendor.radio.hook_tmplog";
    public static final String PROP_HYDRA_AP_PRINT = "persist.vendor.radio.print_stats";
    public static final String PROP_IMS_DEF_STATE = "persist.vendor.radio.ims_state_def";
    public static final String PROP_IMS_DEF_VALUE_DEF = "";
    public static final String PROP_IMS_DEF_VALUE_OFF = "0";
    public static final String PROP_IMS_DEF_VALUE_ON = "1";
    public static final String PROP_MIRIL_PRINT = "persist.vendor.radio.adb_log_mi";
    public static final String PROP_MIRIL_PRINT_OFF = "0";
    public static final String PROP_MIRIL_PRINT_ON = "65535";
    public static final String PROP_MIUI_BUILD_REGION = "ro.miui.build.region";
    public static final String PROP_MIUI_BUILD_REGION_CN = "cn";
    public static final String PROP_MIUI_BUILD_REGION_DEF = "none";
    public static final String PROP_MIUI_BUILD_REGION_VENDOR = "ro.vendor.miui.build.region";
    public static final String PROP_MI_CHECK_URL_PRINT = "persist.radio.url_check_print";
    public static final String PROP_MI_CHECK_URL_PRINT_OFF = "0";
    public static final String PROP_MI_CHECK_URL_PRINT_ON = "1";
    public static final String PROP_MODEM_AP_PRINT_OFF = "2";
    public static final String PROP_MODEM_AP_PRINT_ON = "1";
    public static final String PROP_MODEM_MBN_FEATURE_FLAG = "persist.vendor.radio.mbn_feature";
    public static final int PROP_MODEM_MBN_FEATURE_FLAG_NULL = 0;
    public static final int PROP_MODEM_MBN_SELFREPARING_OFF = 65503;
    public static final int PROP_MODEM_MBN_SELFREPARING_ON = 32;
    public static final int PROP_MODEM_MBN_SIM_HW_AUTO_SELECT_DISABLE = 8;
    public static final int PROP_MODEM_MBN_UPDATE_FLAG_HW = 1;
    public static final int PROP_MODEM_MBN_UPDATE_FLAG_MBN_LIST_SIZE = 4;
    public static final int PROP_MODEM_MBN_UPDATE_FLAG_SW = 2;
    public static final int PROP_MODEM_MBN_UPDATE_RFS_PATH_DISCOVER = 16;
    public static final String PROP_MODEM_STATS_MASK_KEEP_DIAG = "2";
    public static final String PROP_MODEM_STATS_MASK_KEEP_EVENT = "1";
    public static final String PROP_MODEM_STATS_MASK_KEEP_OFF = "0";
    public static final String PROP_MODEM_STATS_MASK_KEEP_ON = "3";
    public static final String PROP_MODEM_STATS_MASK_KEEP_SWITCH = "persist.vendor.radio.stats_mask_keep";
    public static final String PROP_MODEM_STATS_SWITCH = "persist.vendor.radio.stats_switch";
    public static final String PROP_MODEM_STATS_SWITCH_INVALID = "2";
    public static final String PROP_MODEM_STATS_SWITCH_OFF = "0";
    public static final String PROP_MODEM_STATS_SWITCH_ON = "1";
    public static final String PROP_NAME_FACTORY_BUILD = "ro.boot.factorybuild";
    public static final String PROP_NAME_FACTORY_BUILD_NULL = "NULL";
    public static final String PROP_NR_ONLY_FLAG = "persist.vendor.radio.nr_only";
    public static final String PROP_NR_ONLY_FLAG_OFF = "off";
    public static final String PROP_NR_ONLY_FLAG_ON = "on";
    public static final String PROP_NTN_LOG_LEVEL = "persist.odm.radio.ntn.log.level";
    public static final String PROP_NTN_LOG_LEVEL_DEBUG = "2";
    public static final String PROP_NTN_LOG_LEVEL_DEFAULT = "default";
    public static final String PROP_NTN_LOG_LEVEL_ERROR = "4";
    public static final String PROP_NTN_LOG_LEVEL_INFO = "1";
    public static final String PROP_NTN_LOG_LEVEL_NULL = "";
    public static final String PROP_NTN_LOG_LEVEL_VERBOSE = "0";
    public static final String PROP_NTN_LOG_LEVEL_WARNING = "3";
    public static final String PROP_PRINT_IDLE = "0";
    public static final String PROP_PRINT_OFF = "2";
    public static final String PROP_PRINT_ON = "1";
    public static final String PROP_PRINT_STATS = "persist.vendor.radio.print_stats";
    public static final String PROP_QCRIL_PRINT = "persist.vendor.radio.adb_log_on";
    public static final String PROP_QCRIL_PRINT_OFF = "0";
    public static final String PROP_QCRIL_PRINT_ON = "1";
    public static final String PROP_RADIO_BUILD_REGION_VENDOR = "ro.vendor.radio.build_region";
    private static final String PROP_RADIO_DEVICE_TYPE = "ro.radio.device_type";
    private static final String PROP_RADIO_DEVICE_TYPE_FOLDABLE = "3";
    private static final String PROP_RADIO_DEVICE_TYPE_NONE = "0";
    private static final String PROP_RADIO_DEVICE_TYPE_PAD = "2";
    private static final String PROP_RADIO_DEVICE_TYPE_PHONE = "1";
    private static final String PROP_RADIO_MANUFACTURER_ID = "ro.vendor.radio.manufacturer_id";
    public static final String PROP_RADIO_OPERATE_MODE = "persist.radio.operating_mode";
    private static final String PROP_RADIO_XIAOMI_MANUFACTURER_ID_HUAQIN = "4";
    private static final String PROP_RADIO_XIAOMI_MANUFACTURER_ID_LONGQI = "3";
    private static final String PROP_RADIO_XIAOMI_MANUFACTURER_ID_NULL = "0";
    private static final String PROP_RADIO_XIAOMI_MANUFACTURER_ID_WENTAI = "2";
    private static final String PROP_RADIO_XIAOMI_MANUFACTURER_ID_XIAOMI = "1";
    private static final String PROP_RADIO_XIAOMI_MANUFACTURER_ID_ZHONGKE = "5";
    public static final String PROP_RILD_BC_PRINT_SWITCH = "persist.vendor.radio.print_bc";
    public static final String PROP_RILD_HOOK_PRINT_SWITCH = "persist.vendor.radio.print_hook";
    public static final String PROP_RILD_PROP_PRINT_SWITCH = "persist.vendor.radio.print_prop";
    public static final String PROP_ROOT_TYPE = "ro.debuggable";
    public static final String PROP_ROOT_TYPE_NO = "0";
    public static final String PROP_ROOT_TYPE_NULL = "NULL";
    public static final String PROP_ROOT_TYPE_YES = "1";
    public static final String PROP_SAR_LOG_SWITCH = "persist.vendor.radio.dynamic_sar_log";
    public static final String PROP_SAR_LOG_SWITCH_CLOSE = "false";
    public static final String PROP_SAR_LOG_SWITCH_OPEN = "true";
    public static final String PROP_SAR_SWITCH_MAIN = "persist.vendor.radio.dynamic_sar";
    public static final String PROP_SAR_SWITCH_MAIN_CLOSE = "0";
    public static final String PROP_SAR_SWITCH_MAIN_DEF = "1";
    public static final String PROP_SAR_SWITCH_MAIN_OPEN = "2";
    public static final String PROP_SIM_STATE = "gsm.sim.state";
    public static final String PROP_SMS_AUTO_REG = "persist.radio.autosms";
    public static final String PROP_SMS_AUTO_REG_OFF = "disabled";
    public static final String PROP_SMS_AUTO_REG_ON = "enabled";
    public static final String PROP_SSR_LEVEL = "persist.vendor.ssr.restart_level";
    public static final String PROP_SSR_LEVEL_ALL_DISABLE = "ALL_DISABLE";
    public static final String PROP_SSR_LEVEL_ALL_ENABLE = "ALL_ENABLE";
    public static final String PROP_SSR_LEVEL_BAK = "persist.vendor.ssr.restart_level_bak";
    public static final String PROP_SSR_LEVEL_NULL = "";
    public static final String PROP_USB_CONFIG_HIGH28 = "persist.vendor.usb.config";
    public static final String PROP_USB_CONFIG_LOW28 = "persist.sys.usb.config";
    public static final String PROP_USB_CONFIG_NONROOT = "sys.usb.config";
    public static final String PROP_USB_CONFIG_NULL = "null";
    public static final String PROP_WENKONG_FLAG = "persist.vendor.radio.wenkong";
    public static final String PROP_WENKONG_FLAG_OFF = "off";
    public static final String PROP_WENKONG_FLAG_ON = "on";
    public static final String PROP_XIAOMI_SSR_WAIT_S = "persist.radio.ssr_wait_s";
    public static final int RADIO_DEVICE_TYPE_FOLDABLE = 3;
    public static final int RADIO_DEVICE_TYPE_NONE = 0;
    public static final int RADIO_DEVICE_TYPE_PAD = 2;
    public static final int RADIO_DEVICE_TYPE_PHONE = 1;
    public static final int RET_DO = 4;
    public static final int RET_ERROR = 2;
    public static final int RET_FALSE = 0;
    public static final int RET_NOTHING = 3;
    public static final int RET_TRUE = 1;
    public static final int SIGNED_DATA = 0;
    public static final String SIM_UNKNOWN = "unknown";
    public static final String STR_COMMON_SWITCH_TYPE_DEF = "";
    public static final String STR_COMMON_SWITCH_TYPE_OFF = "OFF";
    public static final String STR_COMMON_SWITCH_TYPE_ON = "ON";
    public static final int SUB_0 = 0;
    public static final int SUB_1 = 1;
    public static final int SUB_ALL = 2;
    public static final int SUB_MASK_0 = 1;
    public static final int SUB_MASK_0_1 = 3;
    public static final int SUB_MASK_1 = 2;
    public static final int SUB_MASK_ALL = 3;
    public static final int UNSIGNED_DATA = 1;
    public static final int XIAOMI_MANUFACTURER_ID_HUAQIN = 4;
    public static final int XIAOMI_MANUFACTURER_ID_LONGQI = 3;
    public static final int XIAOMI_MANUFACTURER_ID_NULL = 0;
    public static final int XIAOMI_MANUFACTURER_ID_WENTAI = 2;
    public static final int XIAOMI_MANUFACTURER_ID_XIAOMI = 1;
    public static final int XIAOMI_MANUFACTURER_ID_ZHONGKE = 5;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_HQ_DE311 = 101;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_INIT = -1;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_NULL = 0;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_ODM = 100;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_SM8350 = 1;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_SM8450 = 2;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_SM8550 = 3;
    public static final int XIAOMI_MODEM_HAL_CHIP_ID_SM8650 = 4;
    protected static Context mContext = null;
    protected static Handler mHandler = null;
    protected static OemHookAgent mHookAgent = null;
    private static int mNvEfsDataPrintMaxNum = 20;
    private static boolean mTmpLogPrintFlag = false;
    public static final byte[] EFS_VALUE_ASDIV_73971_OPEN = {2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 0};
    public static final byte[] EFS_VALUE_ASDIV_73971_CLOSE = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0};
    public static final int NETWORK_TYPE_NR_CA = 30;
    protected static int mHalChipId = -1;
    protected static int mChipId = -1;
    private static String mProcessName = null;

    public static String addStringFrontBySplit(String str, String str2, String str3) {
        if (str == null || "".equals(str)) {
            return str2;
        }
        return str2 + ";" + str;
    }

    public static void addStringList(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList == null || arrayList2 == null) {
            return;
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            arrayList.add((String) arrayList2.get(i2));
        }
    }

    public static void addStringListWithNoRepeat(ArrayList arrayList, String str) {
        if (arrayList == null) {
            log("addStringListWithNoRepeat, list is null");
        } else if (str == null) {
            log("addStringListWithNoRepeat, str is null");
        } else {
            if (findStringList(arrayList, str)) {
                return;
            }
            arrayList.add(str);
        }
    }

    public static byte[] bufferToBytes(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            log("buffer is null, bufferToBytes do nothing");
            return null;
        }
        int iRemaining = byteBuffer.remaining();
        log("bufferToBytes, len = " + iRemaining);
        byte[] bArr = new byte[iRemaining];
        byteBuffer.get(bArr);
        return bArr;
    }

    public static String byteBufferToString(ByteBuffer byteBuffer) {
        try {
            CharBuffer charBufferDecode = Charset.forName("UTF-8").newDecoder().decode(byteBuffer);
            byteBuffer.flip();
            return charBufferDecode.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            log("byteBufferToString Exception: " + e2);
            return null;
        }
    }

    public static ByteBuffer bytesToBuffer(byte[] bArr) {
        if (bArr == null) {
            log("bytes is null, bytesToBuffer do nothing");
            return null;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length);
        byteBufferAllocate.clear();
        byteBufferAllocate.get(bArr, 0, bArr.length);
        return byteBufferAllocate;
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb.append("0123456789abcdef".charAt((bArr[i2] >> 4) & 15));
            sb.append("0123456789abcdef".charAt(bArr[i2] & 15));
        }
        return sb.toString();
    }

    public static String bytesToString(byte[] bArr) {
        if (bArr == null) {
            log("bytesToString, bytes is null");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        return byteBufferToString(byteBufferWrap);
    }

    public static boolean closeHydraData(OemHookAgent oemHookAgent) {
        log("closeHydraData, hook: " + oemHookAgent);
        if (oemHookAgent == null) {
            log("hook is null");
            return false;
        }
        ByteBuffer byteBufferOnHookHydraStatsSetSync = oemHookAgent.onHookHydraStatsSetSync(getCurrentClassName(), 0);
        if (byteBufferOnHookHydraStatsSetSync == null) {
            log("bufRet is null");
            return false;
        }
        int i2 = byteBufferOnHookHydraStatsSetSync.getInt();
        log("ret: " + i2);
        if (i2 == 0) {
            return true;
        }
        log("closeHydraData fail");
        return false;
    }

    public static boolean copyDirectory(String str, String str2) throws Throwable {
        try {
            log("copyDirectory, oldDir: " + str + ", newDir: " + str2);
            if (str == null) {
                log("copyDirectory: oldDir is null");
                return false;
            }
            if (str2 == null) {
                log("copyDirectory: newDir is null");
                return false;
            }
            String strTrim = str.trim();
            String strTrim2 = str2.trim();
            String str3 = File.separator;
            if (!strTrim.endsWith(str3)) {
                strTrim = strTrim + str3;
            }
            if (!strTrim2.endsWith(str3)) {
                strTrim2 = strTrim2 + str3;
            }
            File file = new File(strTrim);
            if (!file.exists()) {
                log("copyDirectory: oldDir is not exists");
                return false;
            }
            if (!file.isDirectory()) {
                log("copyDirectory: oldDir is not a directory");
                return false;
            }
            if (!createDir(strTrim2)) {
                log("createDir sub-root fail: " + strTrim2);
                return false;
            }
            log("createDir sub-root ok: " + strTrim2);
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                log("copyDirectory: files is null, oldDir: " + strTrim);
                return false;
            }
            boolean zCopyDirectory = true;
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                String absolutePath = fileArrListFiles[i2].getAbsolutePath();
                String strReplace = absolutePath.replace(strTrim, strTrim2);
                if (!fileArrListFiles[i2].isFile()) {
                    if (fileArrListFiles[i2].isDirectory() && !(zCopyDirectory = copyDirectory(absolutePath, strReplace))) {
                        log("copyDirectory fail, oldName: " + absolutePath + ", newName: " + strReplace);
                        break;
                    }
                } else {
                    zCopyDirectory = copyFile(absolutePath, strReplace);
                    if (!zCopyDirectory) {
                        log("copyFile fail, oldName: " + absolutePath + ", newName: " + strReplace);
                        break;
                    }
                }
            }
            if (zCopyDirectory) {
                log("copyDirectory sub-dir ok, oldDir: " + strTrim + ", newDir: " + strTrim2);
                return true;
            }
            log("copyDirectory sub-dir fail, oldDir: " + strTrim + ", newDir: " + strTrim2);
            return false;
        } catch (Exception e2) {
            log("copyDirectory, Exception, oldDir: " + str + ", newDir: " + str2);
            StringBuilder sb = new StringBuilder();
            sb.append("copyDirectory, Exception, e: ");
            sb.append(e2);
            log(sb.toString());
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x0133 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x014f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static boolean copyFile(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            log("copyFile, oldPathName: " + str + ", newPathName: " + str2);
            if (str == null) {
                log("copyFile: oldPathName is null");
                return false;
            }
            if (str2 == null) {
                log("copyFile: newPathName is null");
                return false;
            }
            String strTrim = str.trim();
            String strTrim2 = str2.trim();
            File file = new File(strTrim);
            String filePathFromFullName = getFilePathFromFullName(strTrim2);
            if (filePathFromFullName == null) {
                log("file path get failed, do nothing");
                return false;
            }
            createDir(filePathFromFullName);
            File file2 = new File(strTrim2);
            if (!file.exists()) {
                log("copyFile: oldFile not exist");
                return false;
            }
            if (!file.isFile()) {
                log("copyFile:  oldFile not file");
                return false;
            }
            if (!file.canRead()) {
                log("copyFile:  oldFile cannot read");
                return false;
            }
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i2 = fileInputStream2.read(bArr);
                        if (-1 == i2) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i2);
                    }
                    fileInputStream2.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    try {
                        fileInputStream2.close();
                    } catch (Exception e2) {
                        log("copyFile, fileInputStream close Exception: " + e2);
                        e2.printStackTrace();
                    }
                    try {
                        fileOutputStream.close();
                        return true;
                    } catch (Exception e3) {
                        log("copyFile, fileOutputStream close Exception: " + e3);
                        e3.printStackTrace();
                        return true;
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileInputStream = fileInputStream2;
                    try {
                        log("copyFile, Exception: " + e);
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e5) {
                                log("copyFile, fileInputStream close Exception: " + e5);
                                e5.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e6) {
                                log("copyFile, fileOutputStream close Exception: " + e6);
                                e6.printStackTrace();
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e7) {
                                log("copyFile, fileInputStream close Exception: " + e7);
                                e7.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            throw th;
                        }
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (Exception e8) {
                            log("copyFile, fileOutputStream close Exception: " + e8);
                            e8.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        throw th;
                    }
                    fileOutputStream.close();
                    throw th;
                }
            } catch (Exception e9) {
                e = e9;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (Exception e10) {
            e = e10;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00d2 A[Catch: IOException -> 0x00ce, TryCatch #6 {IOException -> 0x00ce, blocks: (B:42:0x00ca, B:46:0x00d2, B:48:0x00d7), top: B:69:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00d7 A[Catch: IOException -> 0x00ce, TRY_LEAVE, TryCatch #6 {IOException -> 0x00ce, blocks: (B:42:0x00ca, B:46:0x00d2, B:48:0x00d7), top: B:69:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:58:0x00fc A[Catch: IOException -> 0x00f8, TryCatch #2 {IOException -> 0x00f8, blocks: (B:54:0x00f4, B:58:0x00fc, B:60:0x0101), top: B:65:0x00f4 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0101 A[Catch: IOException -> 0x00f8, TRY_LEAVE, TryCatch #2 {IOException -> 0x00f8, blocks: (B:54:0x00f4, B:58:0x00fc, B:60:0x0101), top: B:65:0x00f4 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.io.FileOutputStream, java.io.OutputStream] */
    public static boolean copyFileFromAssets(String str, String str2) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        log("copyFileFromAssets, assetsFilePath: " + str + ", newPathName: " + ((String) str2));
        if (str == null) {
            log("assetsFilePath is null");
            return false;
        }
        if (str2 == 0) {
            log("newPathName is null");
            return false;
        }
        InputStream inputStreamOpen = null;
        try {
            try {
                if (new File((String) str2).exists()) {
                    log("file exists, do nothing");
                    return true;
                }
                String filePathFromFullName = getFilePathFromFullName(str2);
                if (filePathFromFullName == null) {
                    log("file path get failed, do nothing");
                    return false;
                }
                createDir(filePathFromFullName);
                str2 = new FileOutputStream(new File((String) str2));
                try {
                    bufferedOutputStream2 = new BufferedOutputStream(str2);
                    try {
                        inputStreamOpen = mContext.getAssets().open(str);
                        byte[] bArr = new byte[1024];
                        while (inputStreamOpen.read(bArr) > 0) {
                            bufferedOutputStream2.write(bArr, 0, 1024);
                        }
                        inputStreamOpen.close();
                        bufferedOutputStream2.close();
                        str2.close();
                        try {
                            inputStreamOpen.close();
                            bufferedOutputStream2.close();
                            str2.close();
                        } catch (IOException e2) {
                            log("finally IOException e: " + e2);
                            e2.printStackTrace();
                        }
                        return true;
                    } catch (IOException e3) {
                        e = e3;
                        log("catch IOException e: " + e);
                        e.printStackTrace();
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                                if (bufferedOutputStream2 != null) {
                                    bufferedOutputStream2.close();
                                }
                                if (str2 != 0) {
                                    str2.close();
                                }
                            } catch (IOException e4) {
                                log("finally IOException e: " + e4);
                                e4.printStackTrace();
                                return false;
                            }
                        } else {
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            if (str2 != 0) {
                                str2.close();
                            }
                        }
                        return false;
                    }
                } catch (IOException e5) {
                    e = e5;
                    bufferedOutputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = null;
                    if (0 != 0) {
                        try {
                            inputStreamOpen.close();
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            if (str2 != 0) {
                                str2.close();
                            }
                        } catch (IOException e6) {
                            log("finally IOException e: " + e6);
                            e6.printStackTrace();
                            throw th;
                        }
                    } else {
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        if (str2 != 0) {
                            str2.close();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e7) {
            e = e7;
            str2 = 0;
            bufferedOutputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            str2 = 0;
            bufferedOutputStream = null;
        }
    }

    public static void copyFileFromAssetsList(ArrayList arrayList, String str) throws Throwable {
        log("copyFileFromAssetsList, fileAssetsPathList = " + arrayList + ", newPath: " + str);
        if (arrayList == null) {
            log("fileAssetsPathList is null");
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            copyFileFromAssets(str2, str + getFileNameFromFullName(str2, 1));
        }
    }

    public static int countString(String str, String str2) {
        int i2 = 0;
        if (str2 == null || str == null) {
            return 0;
        }
        while (str.contains(str2)) {
            str = str.substring(str.indexOf(str2) + str2.length());
            i2++;
        }
        return i2;
    }

    public static boolean createDir(String str) {
        try {
            log("createDir, dirPath: " + str);
            File file = new File(str);
            if (file.getParentFile().exists()) {
                file.mkdir();
                return true;
            }
            createDir(file.getParentFile().getAbsolutePath());
            file.mkdir();
            return true;
        } catch (Exception e2) {
            log("createDir, Exception: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDirectory(String str) {
        try {
            log("deleteDirectory, dir: " + str);
            if (str == null) {
                log("deleteDirectory: dir is null");
                return true;
            }
            String strTrim = str.trim();
            String str2 = File.separator;
            if (!strTrim.endsWith(str2)) {
                strTrim = strTrim + str2;
            }
            File file = new File(strTrim);
            if (!file.exists()) {
                log("deleteDirectory: dir is not exists");
                return true;
            }
            if (!file.isDirectory()) {
                log("deleteDirectory: dir is not a directory");
                return false;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                log("deleteDirectory: files is null, dir: " + strTrim);
                return false;
            }
            boolean zDeleteDirectory = true;
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                String absolutePath = fileArrListFiles[i2].getAbsolutePath();
                if (!fileArrListFiles[i2].isFile()) {
                    if (fileArrListFiles[i2].isDirectory() && !(zDeleteDirectory = deleteDirectory(absolutePath))) {
                        log("deleteDirectory fail: " + absolutePath);
                        break;
                    }
                } else {
                    zDeleteDirectory = deleteFile(absolutePath);
                    if (!zDeleteDirectory) {
                        log("deleteFile fail: " + absolutePath);
                        break;
                    }
                }
            }
            if (!zDeleteDirectory) {
                log("deleteDirectory sub-dir fail: " + strTrim);
                return false;
            }
            log("deleteDirectory sub-dir ok: " + strTrim);
            if (file.delete()) {
                log("deleteDirectory sub-root ok: " + strTrim);
                return true;
            }
            log("deleteDirectory sub-root fail: " + strTrim);
            return false;
        } catch (Exception e2) {
            log("deleteDirectory, Exception, dir: " + str);
            log("deleteDirectory, Exception, e: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile(String str) {
        try {
            log("deleteFile, filePath: " + str);
            if (str == null) {
                log("deleteFile: filePath is null");
                return true;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception e2) {
            log("deleteFile, Exception: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    public static String exeAdbCmd(String str) {
        log("exeAdbCmd command: " + str);
        try {
            Process processExec = Runtime.getRuntime().exec(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            char[] cArr = new char[4096];
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                int i2 = bufferedReader.read(cArr);
                if (i2 <= 0) {
                    bufferedReader.close();
                    processExec.waitFor();
                    String string = stringBuffer.toString();
                    log("outputStr: " + string);
                    return string;
                }
                stringBuffer.append(cArr, 0, i2);
            }
        } catch (IOException e2) {
            log("RuntimeException: " + e2);
            e2.printStackTrace();
            return null;
        } catch (InterruptedException e3) {
            log("InterruptedException: " + e3);
            e3.printStackTrace();
            return null;
        }
    }

    public static boolean fileExist(String str) {
        try {
            log("fileExist, fileName: " + str);
            File file = new File(str);
            if (!file.exists()) {
                log("fileExist: file not exist");
                return false;
            }
            if (!file.isFile()) {
                log("fileExist:  file not file");
                return false;
            }
            if (file.canRead()) {
                return true;
            }
            log("fileExist:  file cannot read");
            return false;
        } catch (Exception e2) {
            log("fileExist, Exception: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean findStringList(ArrayList arrayList, String str) {
        if (arrayList == null) {
            log("findStringList, list is null");
            return false;
        }
        if (str == null) {
            log("findStringList, str is null");
            return false;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equals(arrayList.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public static ByteBuffer getBuffer(int i2) {
        if (i2 > 0) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i2);
            byteBufferAllocate.order(ByteOrder.nativeOrder());
            return byteBufferAllocate;
        }
        log("getBuffer, bufLen is invalid, len = " + i2);
        return null;
    }

    public static String getBuildProfile(OemHookAgent oemHookAgent) {
        if (oemHookAgent == null) {
            log("getBuildProfile, hook is null");
            return null;
        }
        String strOnHookPropGetSync = oemHookAgent.onHookPropGetSync(PROP_BUILD_PROFILE_VENDOR, "NULL");
        log("getBuildProfile, val = " + strOnHookPropGetSync);
        return strOnHookPropGetSync;
    }

    public static String getBuildRegion() {
        return SystemProperties.get(PROP_MIUI_BUILD_REGION);
    }

    public static String getBuildTags() {
        return SystemProperties.get(PROP_BUILD_TAGS);
    }

    public static String getBuildVersion() {
        return SystemProperties.get(PROP_BUILD_VERSION);
    }

    public static byte getByte(ByteBuffer byteBuffer, byte b2) {
        return byteBuffer != null ? byteBuffer.get() : b2;
    }

    public static byte getByteFromString(String str) {
        return getByteFromString(str, true);
    }

    public static byte[] getBytes(double d2) {
        byte[] bArr = new byte[8];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        byteBufferWrap.putDouble(d2);
        return bArr;
    }

    public static String getCardSlotStateString(OemHookAgent oemHookAgent) {
        if (oemHookAgent == null) {
            log("getCardSlotStateString, hookAgent is null");
            return "UNKnown";
        }
        ByteBuffer byteBufferOnHookCommonMsgSync = oemHookAgent.onHookCommonMsgSync(39, 0);
        if (byteBufferOnHookCommonMsgSync == null) {
            log("getCardSlotStateString, byteBuf is null");
            return "UNKnown";
        }
        int i2 = byteBufferOnHookCommonMsgSync.getInt();
        log("getCardSlotStateString, r = " + i2);
        if (i2 != 0) {
            return "UNKnown";
        }
        log("getCardSlotStateString, t = " + byteBufferOnHookCommonMsgSync.getInt() + ", l = " + byteBufferOnHookCommonMsgSync.getInt());
        int i3 = byteBufferOnHookCommonMsgSync.getInt();
        StringBuilder sb = new StringBuilder();
        sb.append("getCardSlotStateString, v1 = ");
        sb.append(i3);
        log(sb.toString());
        return i3 == 0 ? "ABSENT" : "PRESENT";
    }

    public static String getCertType() {
        return SystemProperties.get(PROP_CERT_TYPE);
    }

    public static Map getCommands(String str) {
        log("PullParseXMLFromPath, fileFullPath = " + str);
        HashMap map = new HashMap();
        if (str == null) {
            log("fileFullPath is null");
            return null;
        }
        Context context = mContext;
        if (context == null) {
            log("mContext is null");
            return null;
        }
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStreamOpen, "utf-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2 && xmlPullParserNewPullParser.getName().equals("ATItemData")) {
                    map.put(xmlPullParserNewPullParser.getAttributeValue(null, "command").toLowerCase(), Integer.valueOf(Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "time"))));
                }
            }
        } catch (Exception e2) {
            log("Get exception while reading command list" + e2);
            e2.printStackTrace();
        }
        return map;
    }

    public static String getCurProcessName(Context context) {
        return getCurProcessName(context, "Unknown");
    }

    public static String getCurrentClassName() {
        return new Throwable().getStackTrace()[1].getClassName();
    }

    public static String getCurrentMethodName() {
        return new Throwable().getStackTrace()[1].getMethodName();
    }

    public static String getCurrentTimeString() {
        Calendar calendar = Calendar.getInstance();
        return "" + calendar.get(1) + MtbOpenFileDialog.PATH_FOLDER + calendar.get(2) + MtbOpenFileDialog.PATH_FOLDER + calendar.get(5) + MtbOpenFileDialog.PATH_FOLDER + calendar.get(11) + MtbOpenFileDialog.PATH_FOLDER + calendar.get(12) + MtbOpenFileDialog.PATH_FOLDER + calendar.get(13);
    }

    public static Date getCurrentTimeWithData(String str) {
        new SimpleDateFormat(str);
        return new Date(System.currentTimeMillis());
    }

    public static int getDefaultPhoneId() {
        return SubscriptionManager.getPhoneId(SubscriptionManager.getDefaultDataSubscriptionId());
    }

    public static String getDeviceHwInfo() {
        return SystemProperties.get(PROPERTY_DEVICE_INFO_HW);
    }

    public static String getDeviceHwLevel() {
        return SystemProperties.get(PROPERTY_DEVICE_INFO_HW_LEVEL);
    }

    public static String getDeviceName() {
        return SystemProperties.get(PROPERTY_DEVICE_NAME);
    }

    public static String getDeviceSwInfo() {
        return SystemProperties.get(PROPERTY_DEVICE_INFO_SW);
    }

    public static int getDeviceType() {
        int i2;
        String str = SystemProperties.get(PROP_RADIO_DEVICE_TYPE, "0");
        if ("1".equals(str)) {
            i2 = 1;
        } else if ("2".equals(str)) {
            i2 = 2;
        } else {
            i2 = "3".equals(str) ? 3 : 0;
        }
        log("getDeviceType, deviceType = " + i2);
        return i2;
    }

    public static double getDouble(ByteBuffer byteBuffer, double d2) {
        return byteBuffer != null ? byteBuffer.getDouble() : d2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static double getDoubleFromString(String str, boolean z) {
        tmpLog("getDoubleFromString, str = " + str + ", hexNotDec = " + z);
        double d2 = 0.0d;
        d2 = 0.0d;
        d2 = 0.0d;
        if (str == 0) {
            tmpLog("str is null, return 0");
            return 0.0d;
        }
        str.trim();
        if ("".equals(str)) {
            tmpLog("str is empty, return 0");
            return 0.0d;
        }
        try {
            if (z) {
                ByteBuffer hexDataBufferFromString = getHexDataBufferFromString(str, true);
                str = str;
                if (hexDataBufferFromString != null) {
                    d2 = hexDataBufferFromString.getDouble();
                    str = str;
                }
            } else {
                double dDoubleValue = new BigDecimal(str).doubleValue();
                d2 = dDoubleValue;
                str = dDoubleValue;
            }
        } catch (ArithmeticException e2) {
            log("ArithmeticException, str = " + str);
            log("ArithmeticException: " + e2);
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            log("NumberFormatException, str = " + str);
            log("NumberFormatException: " + e3);
            e3.printStackTrace();
        }
        tmpLog("val = " + d2);
        return d2;
    }

    public static String getEnbCellString(int i2) {
        return ((i2 >> 8) & 1048575) + "/" + ((i2 >> 2) & 63);
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().toString() + "/";
    }

    public static String getFactoryBuild() {
        return SystemProperties.get(PROP_NAME_FACTORY_BUILD);
    }

    public static String getFileNameFromFullName(String str, int i2) {
        String strSubstring = null;
        if (str == null) {
            log("getFileNameFromFullName, fullName is null");
            return null;
        }
        String filePathFromFullName = str;
        for (int i3 = 0; i3 < i2; i3++) {
            filePathFromFullName = getFilePathFromFullName(filePathFromFullName);
            if (filePathFromFullName == null) {
                log("getFileNameFromFullName, dirDeep is invalid, dirDeep: " + i2 + ", fullName: " + str);
                return null;
            }
        }
        int iLastIndexOf = filePathFromFullName.lastIndexOf("/");
        if (iLastIndexOf > 0) {
            strSubstring = str.substring(iLastIndexOf);
        } else {
            log("getFileNameFromFullName, filePath is invalid, dirDeep: " + i2 + ", fullName: " + str);
        }
        log("getFileNameFromFullName, dirDeep: " + i2 + ", fullName: " + str + ", fileName: " + strSubstring);
        return strSubstring;
    }

    public static String getFilePathFromFullName(String str) {
        String strSubstring = null;
        if (str == null) {
            log("getFilePathFromFullName, fullName is null");
            return null;
        }
        int iLastIndexOf = str.lastIndexOf("/");
        if (iLastIndexOf > 0) {
            strSubstring = str.substring(0, iLastIndexOf);
        } else {
            log("getFilePathFromFullName, filePath is invalid");
        }
        log("getFilePathFromFullName, fullName: " + str + ", filePath: " + strSubstring);
        return strSubstring;
    }

    public static float getFloat(ByteBuffer byteBuffer, float f2) {
        return byteBuffer != null ? byteBuffer.getFloat() : f2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static float getFloatFromString(String str, boolean z) {
        tmpLog("getFloatFromString, str = " + str + ", hexNotDec = " + z);
        float f2 = 0.0f;
        f2 = 0.0f;
        f2 = 0.0f;
        if (str == 0) {
            tmpLog("str is null, return 0");
            return 0.0f;
        }
        str.trim();
        if ("".equals(str)) {
            tmpLog("str is empty, return 0");
            return 0.0f;
        }
        try {
            if (z) {
                ByteBuffer hexDataBufferFromString = getHexDataBufferFromString(str, true);
                str = str;
                if (hexDataBufferFromString != null) {
                    f2 = hexDataBufferFromString.getFloat();
                    str = str;
                }
            } else {
                float fFloatValue = new BigDecimal(str).floatValue();
                f2 = fFloatValue;
                str = fFloatValue;
            }
        } catch (ArithmeticException e2) {
            log("ArithmeticException, str = " + str);
            log("ArithmeticException: " + e2);
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            log("NumberFormatException, str = " + str);
            log("NumberFormatException: " + e3);
            e3.printStackTrace();
        }
        tmpLog("val = " + f2);
        return f2;
    }

    public static ByteBuffer getHexDataBufferFromString(String str, boolean z) {
        String strSubstring;
        if (str == null) {
            log("getHexDataBufferFromString, str is null");
            return null;
        }
        String strTrim = str.trim();
        byte[] bArr = new byte[20];
        int length = strTrim.length();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 2;
            if (i4 < length) {
                strSubstring = strTrim.substring((length - 2) - i2, length - i2);
                if (z) {
                    tmpLog("byteStr = " + strSubstring);
                }
            } else {
                strSubstring = strTrim.substring(0, length - i2);
                if (z) {
                    tmpLog("end, byteStr = " + strSubstring);
                }
            }
            try {
                int iIntValue = Integer.valueOf(strSubstring, 16).intValue();
                byte b2 = (byte) iIntValue;
                if (z) {
                    tmpLog("bVal[" + i3 + "] = " + ((int) b2) + ", iVal = " + iIntValue + ", byteStr = " + strSubstring);
                }
                bArr[i3] = b2;
                i3++;
                if (i4 >= length || i3 >= 20) {
                    break;
                }
                i2 = i4;
            } catch (ArithmeticException e2) {
                log("ArithmeticException, str = " + str);
                log("ArithmeticException: " + e2);
                e2.printStackTrace();
                return null;
            } catch (NumberFormatException e3) {
                log("NumberFormatException, str = " + str);
                log("NumberFormatException: " + e3);
                e3.printStackTrace();
                return null;
            }
        }
        while (i3 < 20) {
            bArr[i3] = 0;
            i3++;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        return byteBufferWrap;
    }

    public static ByteBuffer getHydraData(OemHookAgent oemHookAgent, int i2, int i3) {
        tmpLog("getHydraData, sub = " + i2 + ", id: " + i3 + ", hook: " + oemHookAgent);
        if (oemHookAgent == null) {
            log("getHydraData fail, sub = " + i2 + ", id: " + i3 + ", hook: " + oemHookAgent);
            return null;
        }
        ByteBuffer buffer = getBuffer(12);
        if (buffer == null) {
            log("getHydraData fail, sub = " + i2 + ", id: " + i3 + ", bufHook: " + buffer);
            return null;
        }
        buffer.putInt(i2);
        buffer.putInt(1);
        buffer.putInt(i3);
        ByteBuffer byteBufferOnHookCommonMsgSync = oemHookAgent.onHookCommonMsgSync(62, buffer.array());
        if (byteBufferOnHookCommonMsgSync == null) {
            log("getHydraData fail, sub = " + i2 + ", id: " + i3 + ", bufRet: " + byteBufferOnHookCommonMsgSync);
            return null;
        }
        int i4 = byteBufferOnHookCommonMsgSync.getInt();
        if (i4 != 0) {
            log("getHydraData fail, sub = " + i2 + ", id: " + i3 + ", ret: " + i4);
            return null;
        }
        int i5 = byteBufferOnHookCommonMsgSync.getInt();
        if (1 != i5) {
            log("getHydraData fail, sub = " + i2 + ", id: " + i3 + ", idNum: " + i5);
            return null;
        }
        int i6 = byteBufferOnHookCommonMsgSync.getInt();
        if (i3 != i6) {
            log("getHydraData fail, sub = " + i2 + ", id: " + i3 + ", idRet: " + i6);
            return null;
        }
        int i7 = byteBufferOnHookCommonMsgSync.getInt();
        if (i7 > 0 && i7 <= 700) {
            return byteBufferOnHookCommonMsgSync;
        }
        log("getHydraData fail, sub = " + i2 + ", id: " + i3 + ", valueLen: " + i7);
        return null;
    }

    public static int getInt(ByteBuffer byteBuffer, int i2) {
        return byteBuffer != null ? byteBuffer.getInt() : i2;
    }

    public static int getIntFromByteArray(byte[] bArr, int i2) {
        int i3;
        try {
            i3 = bArr[i2] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH;
            try {
                i3 = i3 | ((bArr[i2 + 1] << 8) & 65280) | ((bArr[i2 + 2] << 16) & 16711680);
                return ((bArr[i2 + 3] << 24) & (-16777216)) | i3;
            } catch (Exception e2) {
                e = e2;
                log("Get exception while getIntFromByteArray" + e);
                e.printStackTrace();
                return i3;
            }
        } catch (Exception e3) {
            e = e3;
            i3 = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int getIntFromString(String str, boolean z) {
        tmpLog("getIntFromString, str = " + str + ", hexNotDec = " + z);
        int i2 = 0;
        i2 = 0;
        i2 = 0;
        if (str == 0) {
            tmpLog("str is null, return 0");
            return 0;
        }
        str.trim();
        if ("".equals(str)) {
            tmpLog("str is empty, return 0");
            return 0;
        }
        try {
            if (z) {
                ByteBuffer hexDataBufferFromString = getHexDataBufferFromString(str, true);
                str = str;
                if (hexDataBufferFromString != null) {
                    i2 = hexDataBufferFromString.getInt();
                    str = str;
                }
            } else {
                int iIntValue = new BigDecimal(str).intValue();
                i2 = iIntValue;
                str = iIntValue;
            }
        } catch (ArithmeticException e2) {
            log("ArithmeticException, str = " + str);
            log("ArithmeticException: " + e2);
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            log("NumberFormatException, str = " + str);
            log("NumberFormatException: " + e3);
            e3.printStackTrace();
        }
        tmpLog("val = " + i2);
        return i2;
    }

    public static String getIpStringByApnType(LinkProperties linkProperties) {
        String str = null;
        if (linkProperties == null) {
            log("getIpStringByApnType, link is null");
            return null;
        }
        for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
            String str2 = str != null ? str + "/" : "";
            String strSubstring = "" + linkAddress.getAddress();
            if (strSubstring.length() > 0) {
                strSubstring = strSubstring.substring(1);
            }
            str = str2 + strSubstring;
        }
        return str;
    }

    public static long getLong(ByteBuffer byteBuffer, long j2) {
        return byteBuffer != null ? byteBuffer.getLong() : j2;
    }

    public static long getLongFromByteArray(byte[] bArr, int i2) {
        try {
            return (bArr[i2] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH) | ((bArr[i2 + 1] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH) << 8) | ((bArr[i2 + 2] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH) << 16) | ((bArr[i2 + 3] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH) << 24) | ((bArr[i2 + 4] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH) << 32) | ((bArr[i2 + 5] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH) << 40) | (((long) (bArr[i2 + 6] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH)) << 48) | (((long) (bArr[i2 + 7] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH)) << 56);
        } catch (Exception e2) {
            log("Get exception while getLongFromByteArray" + e2);
            e2.printStackTrace();
            return 0L;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static long getLongFromString(String str, boolean z) {
        tmpLog("getLongFromString, str = " + str + ", hexNotDec = " + z);
        long j2 = 0;
        j2 = 0;
        j2 = 0;
        if (str == 0) {
            tmpLog("str is null, return 0");
            return 0L;
        }
        str.trim();
        if ("".equals(str)) {
            tmpLog("str is empty, return 0");
            return 0L;
        }
        try {
            if (z) {
                ByteBuffer hexDataBufferFromString = getHexDataBufferFromString(str, true);
                str = str;
                if (hexDataBufferFromString != null) {
                    j2 = hexDataBufferFromString.getLong();
                    str = str;
                }
            } else {
                long jLongValue = new BigDecimal(str).longValue();
                j2 = jLongValue;
                str = jLongValue;
            }
        } catch (ArithmeticException e2) {
            log("ArithmeticException, str = " + str);
            log("ArithmeticException: " + e2);
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            log("NumberFormatException, str = " + str);
            log("NumberFormatException: " + e3);
            e3.printStackTrace();
        }
        tmpLog("val = " + j2);
        return j2;
    }

    public static int getManufacturerId() {
        int i2;
        String str = SystemProperties.get(PROP_RADIO_MANUFACTURER_ID, "0");
        if ("1".equals(str)) {
            i2 = 1;
        } else if ("2".equals(str)) {
            i2 = 2;
        } else if ("3".equals(str)) {
            i2 = 3;
        } else if ("4".equals(str)) {
            i2 = 4;
        } else {
            i2 = "5".equals(str) ? 5 : 0;
        }
        log("getManufacturerId, manufacturerId = " + i2);
        return i2;
    }

    public static void getMapAndListByString(String str, HashMap map, List list, String str2) {
        if (str2 == null) {
            log("getMapAndListByString, split is null");
            return;
        }
        if (str == null) {
            log("getMapAndListByString, strAll is null");
            return;
        }
        if (map == null) {
            log("getMapAndListByString, strMap is null");
            return;
        }
        if (list == null) {
            log("getMapAndListByString, strList is null");
            return;
        }
        String[] strArrayBySplitWithoutSpace = getStrArrayBySplitWithoutSpace(str, str2);
        if (strArrayBySplitWithoutSpace == null) {
            log("getMapAndListByString, strArryAll is null");
            return;
        }
        if (strArrayBySplitWithoutSpace.length % 2 != 0) {
            log("getMapAndListByString, strArryAll.length = " + strArrayBySplitWithoutSpace.length + ", should be even number");
            return;
        }
        for (int i2 = 0; i2 < strArrayBySplitWithoutSpace.length; i2 += 2) {
            list.add(strArrayBySplitWithoutSpace[i2]);
            map.put(strArrayBySplitWithoutSpace[i2], strArrayBySplitWithoutSpace[i2 + 1]);
        }
    }

    public static int getModemChipId(OemHookAgent oemHookAgent) {
        if (-1 != mChipId) {
            log("getModemChipId, mChipId = " + mChipId);
            return mChipId;
        }
        if (oemHookAgent == null) {
            log("getModemChipId, hook is null");
            return 0;
        }
        mChipId = oemHookAgent.onHookModemChipNameIdGetSync();
        log("getModemChipId, mChipId = " + mChipId);
        return mChipId;
    }

    public static int getModemHalChipId(OemHookAgent oemHookAgent) {
        if (-1 != mHalChipId) {
            log("getModemHalChipId, mHalChipId = " + mHalChipId);
            return mHalChipId;
        }
        if (oemHookAgent == null) {
            log("getModemHalChipId, hook is null");
            return 0;
        }
        ByteBuffer byteBufferOnHookCommonMsgSync = oemHookAgent.onHookCommonMsgSync(82);
        if (byteBufferOnHookCommonMsgSync == null) {
            log("getModemHalChipId, buf is null");
            return 0;
        }
        int i2 = byteBufferOnHookCommonMsgSync.getInt();
        if (i2 != 0) {
            log("getModemHalChipId, ret is invalid, ret = " + i2);
            return 0;
        }
        int i3 = byteBufferOnHookCommonMsgSync.getInt();
        mHalChipId = i3;
        if (i3 <= 0) {
            log("getModemHalChipId, mHalChipId is invalid, mHalChipId = " + mHalChipId);
            return 0;
        }
        log("getModemHalChipId, mHalChipId = " + mHalChipId);
        return mHalChipId;
    }

    public static String getNetworkErrorCodeString(int i2) {
        String str = "[" + i2 + "]";
        if (100 == i2) {
            return str + "Continue";
        }
        if (101 == i2) {
            return str + "Switching Protocols";
        }
        if (102 == i2) {
            return str + "Processing";
        }
        if (200 == i2) {
            return str + "OK";
        }
        if (201 == i2) {
            return str + "Created";
        }
        if (202 == i2) {
            return str + "Accepted";
        }
        if (203 == i2) {
            return str + "Non Authoritative Information";
        }
        if (204 == i2) {
            return str + "No Content";
        }
        if (205 == i2) {
            return str + "Reset Content";
        }
        if (206 == i2) {
            return str + "Partial Content";
        }
        if (207 == i2) {
            return str + "Multi-Status";
        }
        if (300 == i2) {
            return str + "Mutliple Choices";
        }
        if (301 == i2) {
            return str + "Moved Permanently";
        }
        if (302 == i2) {
            return str + "Moved Temporarily";
        }
        if (303 == i2) {
            return str + "See Other";
        }
        if (304 == i2) {
            return str + "Not Modified";
        }
        if (305 == i2) {
            return str + "Use Proxy";
        }
        if (307 == i2) {
            return str + "Temporary Redirect";
        }
        if (400 == i2) {
            return str + "Bad Request";
        }
        if (401 == i2) {
            return str + "Unauthorized";
        }
        if (402 == i2) {
            return str + "Payment Required";
        }
        if (403 == i2) {
            return str + "Forbidden";
        }
        if (404 == i2) {
            return str + "Not Found";
        }
        if (405 == i2) {
            return str + "Method Not Allowed";
        }
        if (406 == i2) {
            return str + "Not Acceptable";
        }
        if (407 == i2) {
            return str + "Proxy Authentication Required";
        }
        if (408 == i2) {
            return str + "Request Timeout";
        }
        if (409 == i2) {
            return str + "Conflict";
        }
        if (410 == i2) {
            return str + "Gone";
        }
        if (411 == i2) {
            return str + "Length Required";
        }
        if (412 == i2) {
            return str + "Precondition Failed";
        }
        if (413 == i2) {
            return str + "Request Entity Too Large";
        }
        if (414 == i2) {
            return str + "Request-URI Too Long";
        }
        if (415 == i2) {
            return str + "Unsupported Media Type";
        }
        if (416 == i2) {
            return str + "Requested Range Not Satisfiable";
        }
        if (417 == i2) {
            return str + "Expectation Failed";
        }
        if (419 == i2) {
            return str + "Insufficient Space on Resource";
        }
        if (420 == i2) {
            return str + "Method Failure";
        }
        if (422 == i2) {
            return str + "Unprocessable Entity";
        }
        if (423 == i2) {
            return str + "Locked";
        }
        if (424 == i2) {
            return str + "Failed Dependency";
        }
        if (500 == i2) {
            return str + "Server Error";
        }
        if (501 == i2) {
            return str + "Not Implemented";
        }
        if (502 == i2) {
            return str + "Bad Gateway";
        }
        if (503 == i2) {
            return str + "Service Unavailable";
        }
        if (504 == i2) {
            return str + "Gateway Timeout";
        }
        if (505 == i2) {
            return str + "HTTP Version Not Supported";
        }
        if (507 != i2) {
            return "UNKnown";
        }
        return str + "Insufficient Storage";
    }

    public static int getOperateMode(OemHookAgent oemHookAgent) {
        if (oemHookAgent == null) {
            log("getOperateMode, hookAgent is null");
            return -1;
        }
        String strOnHookPropGetSync = oemHookAgent.onHookPropGetSync(PROP_RADIO_OPERATE_MODE, "-1");
        if (isNumber(strOnHookPropGetSync)) {
            return Integer.parseInt(strOnHookPropGetSync);
        }
        return -1;
    }

    public static String getOperateModeString(OemHookAgent oemHookAgent) {
        String str;
        int operateMode = getOperateMode(oemHookAgent);
        if (operateMode == 0) {
            str = "Online";
        } else if (1 == operateMode) {
            str = "Lower Power Mode";
        } else if (2 == operateMode) {
            str = "Factory Test Mode";
        } else if (3 == operateMode) {
            str = "Offline";
        } else if (4 == operateMode) {
            str = "Resetting";
        } else if (5 == operateMode) {
            str = "ShuttingDown";
        } else if (6 == operateMode) {
            str = "Persistent Low Power Mode";
        } else if (7 == operateMode) {
            str = "Mode-only Low Power Mode";
        } else {
            str = 8 == operateMode ? "Conducting Network Test for GSM/WCDMA" : "UNKnown";
        }
        return "[" + operateMode + "]" + str;
    }

    public static int getPhoneCount() {
        String str = SystemProperties.get(PROPERTY_MULTI_SIM_CONFIG);
        if (str.equals("dsds") || str.equals("dsda")) {
            return 2;
        }
        return str.equals("tsts") ? 3 : 1;
    }

    public static String getRootType() {
        return SystemProperties.get(PROP_ROOT_TYPE);
    }

    public static short getShort(ByteBuffer byteBuffer, short s) {
        return byteBuffer != null ? byteBuffer.getShort() : s;
    }

    public static short getShortFromByteArray(byte[] bArr, int i2) {
        try {
            return (short) (((short) (((short) (bArr[i2 + 1] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH)) << 8)) | ((short) (bArr[i2] & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH)));
        } catch (Exception e2) {
            log("Get exception while getShortFromByteArray" + e2);
            e2.printStackTrace();
            return (short) 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static short getShortFromString(String str, boolean z) {
        tmpLog("getShortFromString, str = " + str + ", hexNotDec = " + z);
        short s = 0;
        s = 0;
        s = 0;
        if (str == 0) {
            tmpLog("str is null, return 0");
            return (short) 0;
        }
        str.trim();
        if ("".equals(str)) {
            tmpLog("str is empty, return 0");
            return (short) 0;
        }
        try {
            if (z) {
                ByteBuffer hexDataBufferFromString = getHexDataBufferFromString(str, true);
                str = str;
                if (hexDataBufferFromString != null) {
                    s = hexDataBufferFromString.getShort();
                    str = str;
                }
            } else {
                short sShortValue = new BigDecimal(str).shortValue();
                s = sShortValue;
                str = sShortValue;
            }
        } catch (ArithmeticException e2) {
            log("ArithmeticException, str = " + str);
            log("ArithmeticException: " + e2);
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            log("NumberFormatException, str = " + str);
            log("NumberFormatException: " + e3);
            e3.printStackTrace();
        }
        tmpLog("val = " + ((int) s));
        return s;
    }

    public static String getSimStateString() {
        return SystemProperties.get(PROP_SIM_STATE, SIM_UNKNOWN);
    }

    public static String getSpVal(Context context, String str, String str2) {
        if (context == null) {
            log("getSpVal, context is null");
            return null;
        }
        if (str == null) {
            log("getSpVal, spName is null");
            return null;
        }
        if (str2 == null) {
            log("getSpVal, paraName is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(str2, null);
        }
        log("getSpVal, sp is null");
        return null;
    }

    public static boolean getStatsGetPrintFlag() {
        String str = SystemProperties.get("persist.vendor.radio.print_stats");
        log("getStatsGetPrintFlag, str = " + str);
        return "1".equals(str);
    }

    public static String[] getStrArrayBySplitWithoutSpace(String str, String str2) {
        return stringListToStringArray(getStringListBySplitWithoutSpace(str, str2));
    }

    public static String getString(byte[] bArr, int i2, String str) {
        String str2 = "";
        if (bArr == null) {
            return "";
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (i3 != 0 && i2 != 0 && i3 % i2 == 0 && str != null) {
                str2 = str2 + str;
            }
            str2 = str2 + getString(bArr[i3], true, true, true, true);
        }
        return str2;
    }

    public static String getStringByMapAndList(HashMap map, List list, String str) {
        if (str == null) {
            log("getStringByMapAndList, split is null");
            return null;
        }
        if (map == null) {
            log("getStringByMapAndList, strMap is null");
            return null;
        }
        if (list == null) {
            log("getStringByMapAndList, strList is null");
            return null;
        }
        String str2 = "";
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (map.get(list.get(i2)) != null) {
                if (!"".equals(str2)) {
                    str2 = str2 + str;
                }
                str2 = (str2 + ((String) list.get(i2)) + str) + ((String) map.get(list.get(i2)));
            }
        }
        return str2;
    }

    public static String getStringBySplit(String[] strArr, String str) {
        if (strArr == null) {
            log("getStringBySplit, strSrc is null");
            return null;
        }
        if (str == null) {
            log("getStringBySplit, strSplit is null");
            return null;
        }
        int length = strArr.length;
        String str2 = strArr[0];
        for (int i2 = 1; i2 < length; i2++) {
            str2 = str2 + str + strArr[i2];
        }
        return str2;
    }

    public static String getStringForByte(ByteBuffer byteBuffer, boolean z, boolean z2) {
        return byteBuffer == null ? "-" : getString(byteBuffer.get(), z, z2);
    }

    public static String getStringForBytes(ByteBuffer byteBuffer, int i2) {
        if (byteBuffer == null || i2 <= 0) {
            return "-";
        }
        byte[] bArr = new byte[i2];
        byteBuffer.get(bArr);
        return bytesToString(bArr);
    }

    public static String getStringForDouble(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return "-";
        }
        return "" + byteBuffer.getDouble();
    }

    public static String getStringForFloat(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return "-";
        }
        return "" + String.format("%.2f", Float.valueOf(byteBuffer.getFloat()));
    }

    public static String getStringForInt(ByteBuffer byteBuffer, boolean z, boolean z2) {
        return byteBuffer == null ? "-" : getString(byteBuffer.getInt(), z, z2);
    }

    public static String getStringForLong(ByteBuffer byteBuffer, boolean z, boolean z2) {
        return byteBuffer == null ? "-" : getString(byteBuffer.getLong(), z, z2);
    }

    public static String getStringForShort(ByteBuffer byteBuffer, boolean z, boolean z2) {
        return byteBuffer == null ? "-" : getString(byteBuffer.getShort(), z, z2);
    }

    public static String getStringFromResources(Context context, int i2) {
        if (context == null) {
            log("context is null, fail to getString for resId: " + i2);
            return null;
        }
        String string = context.getResources().getString(i2);
        log("Success to getString: " + string);
        return string;
    }

    public static ArrayList getStringListBySplitWithoutSpace(String str, String str2) {
        if (str == null) {
            log("getStringListBySplitWithoutSpace, strSrc is null");
            return null;
        }
        if (str2 == null) {
            log("getStringListBySplitWithoutSpace, strSplit is null");
            return null;
        }
        String[] strArrSplit = str.split(str2);
        ArrayList arrayList = new ArrayList();
        for (String str3 : strArrSplit) {
            String strTrim = str3.trim();
            if (!"".equals(strTrim)) {
                arrayList.add(strTrim);
            }
        }
        return arrayList;
    }

    public static String getTeleNetworkTypeString(int i2) {
        if (i2 == 0) {
            return "UNKNOWN";
        }
        if (1 == i2) {
            return "GPRS";
        }
        if (2 == i2) {
            return "EDGE";
        }
        if (3 == i2) {
            return "UMTS";
        }
        if (4 == i2) {
            return "CDMA";
        }
        if (5 == i2) {
            return "EVDO_0";
        }
        if (6 == i2) {
            return "EVDO_A";
        }
        if (7 == i2) {
            return "1xRTT";
        }
        if (8 == i2) {
            return "HSDPA";
        }
        if (9 == i2) {
            return "HSUPA";
        }
        if (10 == i2) {
            return "HSPA";
        }
        if (11 == i2) {
            return "IDEN";
        }
        if (12 == i2) {
            return "EVDO_B";
        }
        if (13 == i2) {
            return "LTE";
        }
        if (14 == i2) {
            return "EHRPD";
        }
        if (15 == i2) {
            return "HSPAP";
        }
        if (16 == i2) {
            return "GSM";
        }
        if (17 == i2) {
            return "NETWORK_TYPE_TD_SCDMA";
        }
        if (18 == i2) {
            return "IWLAN";
        }
        if (19 == i2) {
            return "LTE_CA";
        }
        if (20 == i2) {
            return "NR";
        }
        if (NETWORK_TYPE_NR_CA == i2) {
            return "NR_CA";
        }
        return "[" + i2 + "]UNKnown";
    }

    public static boolean isChinaBuild() {
        String str = SystemProperties.get(PROP_MIUI_BUILD_REGION, "none");
        boolean zEquals = TextUtils.equals(str, PROP_MIUI_BUILD_REGION_CN);
        log("isChinaBuild, ret = " + zEquals + ", val = " + str);
        return zEquals;
    }

    public static boolean isChinaCertBuild() {
        String str = SystemProperties.get(PROP_CERT_TYPE, "none");
        boolean z = TextUtils.equals(str, PROP_CERT_TYPE_CT) || TextUtils.equals(str, PROP_CERT_TYPE_CM) || TextUtils.equals(str, PROP_CERT_TYPE_CU);
        log("isChinaCertBuild, ret = " + z + ", val = " + str);
        return z;
    }

    public static boolean isFactoryBuild() {
        String str = SystemProperties.get(PROP_NAME_FACTORY_BUILD, "0");
        boolean zEquals = TextUtils.equals(str, "1");
        log("isFactoryBuild, ret = " + zEquals + ", val = " + str);
        return zEquals;
    }

    public static boolean isInStringList(ArrayList arrayList, String str) {
        if (arrayList == null || str == null) {
            return false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (str.equals(arrayList.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMpHw() {
        boolean z;
        String str = SystemProperties.get(PROPERTY_DEVICE_INFO_HW_LEVEL, "NULL");
        if (PROPERTY_DEVICE_INFO_HW_LEVEL_MP.equals(str)) {
            log("MP version");
            z = true;
        } else {
            z = false;
        }
        log("isMpHw, ret = " + z + ", val = " + str);
        return z;
    }

    public static boolean isNumber(String str) {
        if (str == null) {
            log("isNumber, filed is null");
            return false;
        }
        if (true != TextUtils.equals(str.trim(), "")) {
            return Pattern.compile("[0-9]*").matcher(str).matches();
        }
        log("isNumber, filed is empty");
        return false;
    }

    public static boolean isRootBuild() {
        String str = SystemProperties.get(PROP_ROOT_TYPE, "0");
        boolean zEquals = TextUtils.equals(str, "1");
        log("isRootBuild, ret = " + zEquals + ", val = " + str);
        return zEquals;
    }

    public static boolean isStableBuild() {
        String str = SystemProperties.get(PROP_BUILD_VERSION, "");
        log("isStableBuild, version = " + str);
        boolean z = false;
        if (!isTestBuild() && !isChinaCertBuild() && str != null && str.length() > 1 && "V".equals(str.substring(0, 1))) {
            z = true;
        }
        log("isStableBuild, ret = " + z);
        return z;
    }

    public static boolean isSupporteSIM() {
        return DEVICE_ESIM_SUPPORT_LIST.contains(getDeviceName()) && TextUtils.equals(getDeviceHwInfo(), "GL");
    }

    public static boolean isTestBuild() {
        String str = SystemProperties.get(PROP_BUILD_TAGS, PROP_BUILD_TAGS_TEST);
        boolean z = !TextUtils.equals(str, PROP_BUILD_TAGS_RELE);
        log("isTestBuild, ret = " + z + ", val = " + str);
        return z;
    }

    public static boolean isUserBuild() {
        boolean zEquals = TextUtils.equals(SystemProperties.get(PROP_BUILD_TYPE, PROP_BUILD_TYPE_USER), PROP_BUILD_TYPE_USER);
        log("isUserBuild, ret = " + zEquals);
        return zEquals;
    }

    public static void log(String str) {
        Log.d("ModemUtils(" + getCurProcessName(mContext) + ")", str);
    }

    public static int max(int i2, int i3) {
        return i2 < i3 ? i3 : i2;
    }

    public static int mid(int i2, int i3, int i4) {
        if (i2 > i4) {
            i2 = i4;
        }
        return i2 < i3 ? i3 : i2;
    }

    public static int min(int i2, int i3) {
        return i2 > i3 ? i3 : i2;
    }

    public static byte onGetByteFromString(String str, int i2) {
        return onGetByteFromString(str, true, i2);
    }

    public static double onGetDoubleFromString(String str, int i2) {
        tmpLog("onGetDoubleFromString, strIn = " + str + ", hexOrDec = " + i2);
        if (str == null) {
            tmpLog("strIn is null, return 0.0");
            return 0.0d;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            tmpLog("str is empty, return 0.0");
            return 0.0d;
        }
        double dDoubleValue = new BigDecimal(strTrim).doubleValue();
        tmpLog("val = " + dDoubleValue);
        return dDoubleValue;
    }

    public static float onGetFloatFromString(String str, int i2) {
        tmpLog("onGetFloatFromString, strIn = " + str + ", hexOrDec = " + i2);
        if (str == null) {
            tmpLog("strIn is null, return 0.0");
            return 0.0f;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            tmpLog("str is empty, return 0.0");
            return 0.0f;
        }
        float fFloatValue = new BigDecimal(strTrim).floatValue();
        tmpLog("val = " + fFloatValue);
        return fFloatValue;
    }

    public static ByteBuffer onGetHexDataBufferFromString(String str, boolean z) {
        String strSubstring;
        if (str == null) {
            log("onGetHexDataBufferFromString, str is null");
            return null;
        }
        String strTrim = str.trim();
        byte[] bArr = new byte[20];
        int length = strTrim.length();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 2;
            if (i4 < length) {
                strSubstring = strTrim.substring((length - 2) - i2, length - i2);
                if (z) {
                    tmpLog("byteStr = " + strSubstring);
                }
            } else {
                strSubstring = strTrim.substring(0, length - i2);
                if (z) {
                    tmpLog("end, byteStr = " + strSubstring);
                }
            }
            try {
                int iIntValue = Integer.valueOf(strSubstring, 16).intValue();
                byte b2 = (byte) iIntValue;
                if (z) {
                    tmpLog("bVal[" + i3 + "] = " + ((int) b2) + ", iVal = " + iIntValue + ", byteStr = " + strSubstring);
                }
                bArr[i3] = b2;
                i3++;
                if (i4 >= length || i3 >= 20) {
                    break;
                }
                i2 = i4;
            } catch (ArithmeticException e2) {
                log("ArithmeticException, str = " + str);
                log("ArithmeticException: " + e2);
                e2.printStackTrace();
                return null;
            } catch (NumberFormatException e3) {
                log("NumberFormatException, str = " + str);
                log("NumberFormatException: " + e3);
                e3.printStackTrace();
                return null;
            }
        }
        while (i3 < 20) {
            bArr[i3] = 0;
            i3++;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        return byteBufferWrap;
    }

    public static int onGetIntFromString(String str, int i2) {
        tmpLog("onGetIntFromString, strIn = " + str + ", hexOrDec = " + i2);
        int iIntValue = 0;
        if (str == null) {
            tmpLog("strIn is null, return 0");
            return 0;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            tmpLog("str is empty, return 0");
            return 0;
        }
        if (1 == i2) {
            ByteBuffer byteBufferOnGetHexDataBufferFromString = onGetHexDataBufferFromString(strTrim, true);
            if (byteBufferOnGetHexDataBufferFromString != null) {
                iIntValue = byteBufferOnGetHexDataBufferFromString.getInt();
            }
        } else {
            iIntValue = new BigDecimal(strTrim).intValue();
        }
        tmpLog("val = " + iIntValue);
        return iIntValue;
    }

    public static long onGetLongFromString(String str, int i2) {
        tmpLog("onGetLongFromString, strIn = " + str + ", hexOrDec = " + i2);
        long jLongValue = 0;
        if (str == null) {
            tmpLog("strIn is null, return 0");
            return 0L;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            tmpLog("str is empty, return 0");
            return 0L;
        }
        if (1 == i2) {
            ByteBuffer byteBufferOnGetHexDataBufferFromString = onGetHexDataBufferFromString(strTrim, true);
            if (byteBufferOnGetHexDataBufferFromString != null) {
                jLongValue = byteBufferOnGetHexDataBufferFromString.getLong();
            }
        } else {
            jLongValue = new BigDecimal(strTrim).longValue();
        }
        tmpLog("val = " + jLongValue);
        return jLongValue;
    }

    public static String onGetModSwVer(OemHookAgent oemHookAgent) {
        log("onGetModSwVer, hook = " + oemHookAgent);
        if (oemHookAgent == null) {
            log("hook is null");
            return null;
        }
        ByteBuffer byteBufferOnHookModSwVerGetSync = oemHookAgent.onHookModSwVerGetSync();
        if (byteBufferOnHookModSwVerGetSync == null) {
            log("byteBuf is null");
            return null;
        }
        int i2 = byteBufferOnHookModSwVerGetSync.getInt();
        log("ret = " + i2);
        if (i2 != 0) {
            log("0 != ret");
            return null;
        }
        String strByteBufferToString = byteBufferToString(byteBufferOnHookModSwVerGetSync);
        log("ModSwVer: " + strByteBufferToString);
        return strByteBufferToString;
    }

    public static short onGetShortFromString(String str, int i2) {
        tmpLog("onGetShortFromString, strIn = " + str + ", hexOrDec = " + i2);
        short sShortValue = 0;
        if (str == null) {
            tmpLog("strIn is null, return 0");
            return (short) 0;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            tmpLog("str is empty, return 0");
            return (short) 0;
        }
        if (1 == i2) {
            ByteBuffer byteBufferOnGetHexDataBufferFromString = onGetHexDataBufferFromString(strTrim, true);
            if (byteBufferOnGetHexDataBufferFromString != null) {
                sShortValue = byteBufferOnGetHexDataBufferFromString.getShort();
            }
        } else {
            sShortValue = new BigDecimal(strTrim).shortValue();
        }
        tmpLog("val = " + ((int) sShortValue));
        return sShortValue;
    }

    public static String onGetStringByDateType(byte b2, int i2, boolean z, int i3, boolean z2) {
        String string;
        if (z) {
            tmpLog("onGetStringByDateType, byte, val = " + ((int) b2) + ", uFlag = " + i2 + ", bPrint = " + z + ", hexOrDec = " + i3 + ", fillHexZero = " + z2);
        }
        if (1 == i3) {
            string = Integer.toHexString(b2 & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH);
            if (1 == string.length() && z2) {
                string = "0" + string;
            }
            if (z) {
                tmpLog("HEX, strVal = " + string);
            }
        } else {
            String str = "" + ((int) b2);
            if (1 != i2 || b2 >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf((byte) (b2 & 127)).add(BigDecimal.valueOf(127L)).add(BigDecimal.valueOf(1L)).toString();
                if (z) {
                    tmpLog("val < 0, new strVal = " + string);
                }
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static void onInitContext(Context context) {
        mContext = context;
        log("onInitContext, mContext = " + mContext);
    }

    public static void onInitHandler(Handler handler) {
        log("onInitHandler, hdl = " + handler);
        mHandler = handler;
    }

    public static void onInitHook(OemHookAgent oemHookAgent) {
        log("onInitHook, hook = " + oemHookAgent);
        mHookAgent = oemHookAgent;
    }

    public static boolean onIsBoolNumber(String str) {
        if (str == null) {
            log("onIsBoolNumber, filed is null");
            return false;
        }
        if (Pattern.compile("[0-1]*").matcher(str).matches()) {
            tmpLog("onIsBoolNumber, true");
            return true;
        }
        tmpLog("onIsBoolNumber, false");
        return false;
    }

    public static boolean onIsFloatNumber(String str) {
        if (str == null) {
            log("onIsFloatNumber, filed is null");
            return false;
        }
        if (Pattern.compile("[0-9-.]*").matcher(str).matches()) {
            tmpLog("onIsFloatNumber, true");
            return true;
        }
        tmpLog("onIsFloatNumber, false");
        return false;
    }

    public static boolean onIsNumber(String str) {
        if (str == null) {
            log("onIsNumber, filed is null");
            return false;
        }
        if (Pattern.compile("[0-9]*").matcher(str).matches()) {
            tmpLog("onIsNumber, true");
            return true;
        }
        tmpLog("onIsNumber, false");
        return false;
    }

    public static boolean onIsSignedNumber(String str) {
        if (str == null) {
            log("onIsSignedNumber, filed is null");
            return false;
        }
        if (Pattern.compile("[0-9-]*").matcher(str).matches()) {
            tmpLog("onIsSignedNumber, true");
            return true;
        }
        tmpLog("onIsSignedNumber, false");
        return false;
    }

    private static boolean onModemNtnLogIsOpen() {
        log("onModemNtnLogIsOpen");
        OemHookAgent oemHookAgent = mHookAgent;
        if (oemHookAgent == null) {
            log("mHookAgent is null");
            return false;
        }
        String strOnHookPropGetSync = oemHookAgent.onHookPropGetSync(PROP_NTN_LOG_LEVEL, "default");
        log("str: " + strOnHookPropGetSync);
        if ("2".equals(strOnHookPropGetSync)) {
            log("ntn log has been opened");
            return true;
        }
        log("ntn log has been closed");
        return false;
    }

    public static void onModemNtnLogSet(Context context) {
        log("onModemNtnLogSet, cnt = " + context);
        if (context == null) {
            log("cnt is null");
            showToast(context, "NTN LOG setting failed");
            return;
        }
        OemHookAgent hook = OemHookAgent.getHook();
        mHookAgent = hook;
        if (hook == null) {
            log("mHookAgent is null");
            showToast(context, "NTN LOG setting failed");
            return;
        }
        if (onModemNtnLogIsOpen()) {
            log("buf: " + mHookAgent.onHookPropSetSync(PROP_NTN_LOG_LEVEL, ""));
            showToast(context, "NTN LOG disable");
            return;
        }
        log("buf: " + mHookAgent.onHookPropSetSync(PROP_NTN_LOG_LEVEL, "2"));
        showToast(context, "NTN LOG enable");
    }

    public static boolean putByteToBuffer(ByteBuffer byteBuffer, byte[] bArr, int i2) {
        log("putByteToBuffer, len: " + i2);
        if (bArr == null) {
            log("putByteToBuffer, value is null");
            return false;
        }
        if (byteBuffer == null) {
            log("putByteToBuffer, buf is null");
            return false;
        }
        if (i2 <= 0) {
            log("len can not <= 0");
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byteBuffer.put(bArr[i3]);
        }
        for (int i4 = 0; i4 < mNvEfsDataPrintMaxNum && i4 < i2; i4++) {
            log("value[" + i4 + "] = " + ((int) bArr[i4]));
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static byte[] readFileToBytes(String str) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            log("readFileToBytes, filePath: " + str);
            if (str == null) {
                log("filePath is null");
                return null;
            }
            fileInputStream = new FileInputStream(str);
            try {
                try {
                    byte[] bArr = new byte[1024];
                    int i2 = fileInputStream.read(bArr);
                    log("len: " + i2);
                    byte[] bArr2 = null;
                    byte[] bArr3 = null;
                    int i3 = 0;
                    while (i2 > 0) {
                        if (i3 > 0) {
                            bArr3 = new byte[i3];
                            for (int i4 = 0; i4 < i3; i4++) {
                                bArr3[i4] = bArr2[i4];
                            }
                        }
                        int i5 = i3 + i2;
                        byte[] bArr4 = new byte[i5];
                        for (int i6 = 0; i6 < i3; i6++) {
                            bArr4[i6] = bArr3[i6];
                        }
                        for (int i7 = 0; i7 < i2; i7++) {
                            bArr4[i3 + i7] = bArr[i7];
                        }
                        i2 = fileInputStream.read(bArr);
                        log("lenAll: " + i5 + ", len: " + i2);
                        i3 = i5;
                        bArr2 = bArr4;
                    }
                    fileInputStream.close();
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        log("readFileToBytes, close Exception: " + e2);
                        e2.printStackTrace();
                    }
                    return bArr2;
                } catch (Exception e3) {
                    e = e3;
                    log("readFileToBytes, Exception: " + e);
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            log("readFileToBytes, close Exception: " + e4);
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e5) {
                        log("readFileToBytes, close Exception: " + e5);
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public static void rebootSystem(Context context) {
        if (context == null) {
            log("context is null, fail to rebootSystem");
            return;
        }
        Intent intent = new Intent("android.intent.action.REBOOT");
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        context.sendBroadcast(intent);
    }

    public static String removeStringBySplit(String str, String str2, String str3) {
        String[] strArrayBySplitWithoutSpace = getStrArrayBySplitWithoutSpace(str, str3);
        if (strArrayBySplitWithoutSpace == null) {
            log("removeStringBySplit, getStrArrayBySplitWithoutSpace fail");
            return null;
        }
        int length = strArrayBySplitWithoutSpace.length;
        String str4 = "";
        for (int i2 = 0; i2 < length; i2++) {
            String str5 = strArrayBySplitWithoutSpace[i2];
            if (str5 != null && !str5.equals(str2)) {
                if (!"".equals(str4)) {
                    str4 = str4 + ";";
                }
                str4 = str4 + strArrayBySplitWithoutSpace[i2];
            }
        }
        return str4;
    }

    public static void removeStringListAt(List list, int i2) {
        if (list == null) {
            log("removeStringListAt, strList is null");
            return;
        }
        if (i2 < 0 || i2 >= list.size()) {
            log("removeStringListAt, idx is invalid, idx = " + i2 + ", size = " + list.size());
            return;
        }
        Iterator it = list.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            if (i3 == i2) {
                it.remove();
                return;
            }
            i3++;
        }
    }

    public static byte revertHigh4AndLow4(byte b2) {
        return (byte) (((b2 << 4) & 240) + ((b2 >> 4) & 15));
    }

    public static Process rootExecProgram(String str, boolean z) {
        try {
            Process processExec = Runtime.getRuntime().exec("/system/xbin/su");
            DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
            String str2 = "" + str;
            log("rootExecProgram command: " + str2);
            dataOutputStream.writeBytes(str2 + "\n");
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            if (z) {
                processExec.waitFor();
                return null;
            }
            log("process: " + processExec);
            return processExec;
        } catch (IOException e2) {
            log("RuntimeException: " + e2);
            e2.printStackTrace();
            return null;
        } catch (InterruptedException e3) {
            log("InterruptedException: " + e3);
            e3.printStackTrace();
            return null;
        }
    }

    public static boolean setSpVal(Context context, String str, String str2, String str3) {
        if (context == null) {
            log("getSpVal, context is null");
            return false;
        }
        if (str == null) {
            log("getSpVal, spName is null");
            return false;
        }
        if (str2 == null) {
            log("getSpVal, paraName is null");
            return false;
        }
        if (str3 == null) {
            log("getSpVal, paraVal is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        if (sharedPreferences == null) {
            log("getSpVal, sp is null");
            return false;
        }
        sharedPreferences.edit().putString(str2, str3).commit();
        return true;
    }

    public static void showToast(Context context, String str) {
        if (context == null || str == null) {
            log("context is null, fail to show toast: " + str);
            return;
        }
        Toast.makeText(context, str, 0).show();
        log("Success to show toast: " + str);
    }

    public static String sizeByteToString(long j2) {
        String str;
        double d2 = j2;
        if (d2 >= 1000.0d) {
            double d3 = d2 / 1024.0d;
            if (d3 >= 1000.0d) {
                d3 = d2 / 1048576.0d;
                if (d3 >= 1000.0d) {
                    d3 = d2 / 1.073741824E9d;
                    if (d3 >= 1000.0d) {
                        d2 /= 1.099511627776E12d;
                        str = " T";
                    } else {
                        str = " G";
                    }
                } else {
                    str = " M";
                }
            } else {
                str = " K";
            }
            d2 = d3;
        } else {
            str = " B";
        }
        return "" + new BigDecimal(d2).setScale(2, 4).doubleValue() + str;
    }

    public static void skipData(ByteBuffer byteBuffer, int i2) {
        if (byteBuffer == null) {
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byteBuffer.get();
        }
    }

    public static void sleep(int i2) {
        log("Sleep, ms = " + i2);
        try {
            Thread.sleep(i2);
        } catch (InterruptedException unused) {
            log("Sleep failed");
        }
    }

    public static boolean startHydraData(OemHookAgent oemHookAgent) {
        log("startHydraData, hook: " + oemHookAgent);
        if (oemHookAgent == null) {
            log("hook is null");
            return false;
        }
        ByteBuffer byteBufferOnHookHydraStatsSetSync = oemHookAgent.onHookHydraStatsSetSync(getCurrentClassName(), 1);
        if (byteBufferOnHookHydraStatsSetSync == null) {
            log("bufRet is null");
            return false;
        }
        int i2 = byteBufferOnHookHydraStatsSetSync.getInt();
        log("ret: " + i2);
        if (i2 == 0) {
            return true;
        }
        log("startHydraData fail");
        return false;
    }

    public static ArrayList stringArrayToStringList(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static boolean stringContainStringList(ArrayList arrayList, String str) {
        if (arrayList == null || str == null) {
            return false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (str.contains((CharSequence) arrayList.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public static boolean stringListContainsString(ArrayList arrayList, String str) {
        if (arrayList == null || str == null) {
            return false;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2) != null && ((String) arrayList.get(i2)).contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static String[] stringListToStringArray(ArrayList arrayList) {
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean subIsValid(int i2) {
        return i2 == 0 || 1 == i2;
    }

    public static boolean subMask0IsOn(int i2) {
        return 1 == (i2 & 1);
    }

    public static boolean subMask1IsOn(int i2) {
        return 2 == (i2 & 2);
    }

    public static boolean subMaskIsValid(int i2) {
        return 1 == i2 || 2 == i2 || 3 == i2;
    }

    public static boolean subMaskxIsOn(int i2, int i3) {
        if (i3 == 0) {
            return subMask0IsOn(i2);
        }
        if (1 == i3) {
            return subMask1IsOn(i2);
        }
        return false;
    }

    public static long timeDateToLong(Date date) {
        return date.getTime();
    }

    public static String timeDateToString(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static Date timeLongToDate(long j2) {
        return new Date(j2);
    }

    public static String timeLongToString(long j2, String str) {
        return timeDateToString(timeLongToDate(j2), str);
    }

    public static String timeLongToStringEx(long j2) {
        Date date = new Date(j2);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(gregorianCalendar.getTime());
    }

    public static void tmpLog(String str) {
        if (mTmpLogPrintFlag) {
            log(str);
        }
    }

    public static String toHtmlFormat(String str) {
        return str == null ? str : str.replace(" ", "&nbsp;").replace("\n", "<br />");
    }

    public static String toHtmlFormatBigAndColor(String str, String str2) {
        if (str == null || str2 == null) {
            return str;
        }
        return "<font color='" + str2 + "'><b><big>" + str + "</big></b></font>";
    }

    public static String toHtmlFormatColor(String str, String str2) {
        if (str == null || str2 == null) {
            return str;
        }
        return "<font color='" + str2 + "'><b>" + str + "</b></font>";
    }

    public static String trimZero(String str) {
        if (str == null) {
            log("trimZero, str is null, will set empty sting");
            return "";
        }
        byte[] bytes = str.getBytes();
        int length = str.length();
        if (length <= 0) {
            log("trimZero, len <= 0, will set empty sting");
            return "";
        }
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (bytes[i3] != 0) {
                i2++;
            }
        }
        if (i2 <= 0) {
            log("trimZero, all zero, will set empty sting");
            return "";
        }
        byte[] bArr = new byte[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            byte b2 = bytes[i5];
            if (b2 != 0) {
                bArr[i4] = b2;
                i4++;
            }
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        return byteBufferToString(byteBufferWrap);
    }

    public static boolean writeBytesToFile(String str, byte[] bArr) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                log("writeBytesToFile, filePath: " + str);
                if (bArr == null) {
                    log("bytes is null");
                    return false;
                }
                log("bytes size: " + bArr.length);
                if (str == null) {
                    log("filePath is null");
                    return false;
                }
                String filePathFromFullName = getFilePathFromFullName(str);
                if (filePathFromFullName == null) {
                    log("file path get failed, do nothing");
                    return false;
                }
                createDir(filePathFromFullName);
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream2.close();
                    try {
                        fileOutputStream2.close();
                        return true;
                    } catch (Exception e2) {
                        log("writeBytesToFile, close Exception: " + e2);
                        e2.printStackTrace();
                        return true;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    log("writeBytesToFile, Exception: " + e);
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            log("writeBytesToFile, close Exception: " + e4);
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e5) {
                            log("writeBytesToFile, close Exception: " + e5);
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeStrToFile(String str, String str2) throws Throwable {
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                log("writeStrToFile, filePath: " + str);
                if (str2 == null) {
                    log("str is null");
                    return false;
                }
                log("str size: " + str2.length());
                if (str == null) {
                    log("filePath is null");
                    return false;
                }
                String filePathFromFullName = getFilePathFromFullName(str);
                if (filePathFromFullName == null) {
                    log("file path get failed, do nothing");
                    return false;
                }
                createDir(filePathFromFullName);
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(str));
                try {
                    outputStreamWriter2.write(str2);
                    outputStreamWriter2.close();
                    try {
                        outputStreamWriter2.close();
                        return true;
                    } catch (Exception e2) {
                        log("writeStrToFile, close Exception: " + e2);
                        e2.printStackTrace();
                        return true;
                    }
                } catch (Exception e3) {
                    outputStreamWriter = outputStreamWriter2;
                    e = e3;
                    log("writeStrToFile, Exception: " + e);
                    e.printStackTrace();
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (Exception e4) {
                            log("writeStrToFile, close Exception: " + e4);
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    outputStreamWriter = outputStreamWriter2;
                    th = th;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (Exception e5) {
                            log("writeStrToFile, close Exception: " + e5);
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e6) {
            e = e6;
        }
    }

    public static boolean writeStrToFileAppend(String str, String str2) throws Throwable {
        PrintWriter printWriter = null;
        try {
            try {
                log("writeStrToFileAppend, filePath: " + str);
                if (str2 == null) {
                    log("str is null");
                    return false;
                }
                log("str size: " + str2.length());
                if (str == null) {
                    log("filePath is null");
                    return false;
                }
                String filePathFromFullName = getFilePathFromFullName(str);
                if (filePathFromFullName == null) {
                    log("file path get failed, do nothing");
                    return false;
                }
                createDir(filePathFromFullName);
                PrintWriter printWriter2 = new PrintWriter(str, StandardCharsets.UTF_8);
                try {
                    printWriter2.println(str2);
                    printWriter2.close();
                    try {
                        printWriter2.close();
                        return true;
                    } catch (Exception e2) {
                        log("writeStrToFileAppend, close Exception: " + e2);
                        e2.printStackTrace();
                        return true;
                    }
                } catch (Exception e3) {
                    e = e3;
                    printWriter = printWriter2;
                    log("writeStrToFileAppend, Exception: " + e);
                    e.printStackTrace();
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception e4) {
                            log("writeStrToFileAppend, close Exception: " + e4);
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    printWriter = printWriter2;
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception e5) {
                            log("writeStrToFileAppend, close Exception: " + e5);
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeStrToFileAppendEx(String str, String str2) throws Throwable {
        FileWriter fileWriter = null;
        try {
            try {
                log("writeStrToFileAppendEx, filePath: " + str);
                if (str2 == null) {
                    log("str is null");
                    return false;
                }
                log("str size: " + str2.length());
                if (str == null) {
                    log("filePath is null");
                    return false;
                }
                String filePathFromFullName = getFilePathFromFullName(str);
                if (filePathFromFullName == null) {
                    log("file path get failed, do nothing");
                    return false;
                }
                createDir(filePathFromFullName);
                FileWriter fileWriter2 = new FileWriter(str, true);
                try {
                    fileWriter2.write(str2);
                    fileWriter2.close();
                    try {
                        fileWriter2.close();
                    } catch (Exception e2) {
                        log("writeStrToFileAppendEx, close Exception: " + e2);
                        e2.printStackTrace();
                    }
                    return true;
                } catch (Exception e3) {
                    e = e3;
                    fileWriter = fileWriter2;
                    log("writeStrToFileAppendEx, Exception: " + e);
                    e.printStackTrace();
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (Exception e4) {
                            log("writeStrToFileAppendEx, close Exception: " + e4);
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (Exception e5) {
                            log("writeStrToFileAppendEx, close Exception: " + e5);
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeStrToFileEx(String str, String str2) throws Throwable {
        FileWriter fileWriter = null;
        try {
            try {
                log("writeStrToFileEx, filePath: " + str);
                if (str2 == null) {
                    log("str is null");
                    return false;
                }
                log("str size: " + str2.length());
                if (str == null) {
                    log("filePath is null");
                    return false;
                }
                String filePathFromFullName = getFilePathFromFullName(str);
                if (filePathFromFullName == null) {
                    log("file path get failed, do nothing");
                    return false;
                }
                createDir(filePathFromFullName);
                FileWriter fileWriter2 = new FileWriter(str, false);
                try {
                    fileWriter2.write(str2);
                    fileWriter2.close();
                    try {
                        fileWriter2.close();
                        return true;
                    } catch (Exception e2) {
                        log("writeStrToFileEx, close Exception: " + e2);
                        e2.printStackTrace();
                        return true;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileWriter = fileWriter2;
                    log("writeStrToFileEx, Exception: " + e);
                    e.printStackTrace();
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (Exception e4) {
                            log("writeStrToFileEx, close Exception: " + e4);
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (Exception e5) {
                            log("writeStrToFileEx, close Exception: " + e5);
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public ByteBuffer getByteBufferFromAR(AsyncResult asyncResult) {
        if (asyncResult == null) {
            log("getByteBufferFromAR, ar is null");
            return null;
        }
        if (asyncResult.exception == null) {
            log("getByteBufferFromAR, ar.exception" + asyncResult.exception);
            return null;
        }
        Object obj = asyncResult.result;
        if (obj == null) {
            log("getByteBufferFromAR, Null Response");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap((byte[]) obj);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        return byteBufferWrap;
    }

    public static byte getByteFromString(String str, boolean z) {
        return getByteFromString(str, true, z);
    }

    public static String getCurProcessName(Context context, String str) {
        String str2 = mProcessName;
        if (str2 != null) {
            return str2;
        }
        if (context == null) {
            Log.e(LOG_TAG, "getCurProcessName, context is null");
            return str;
        }
        int iMyPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == iMyPid) {
                String str3 = runningAppProcessInfo.processName;
                mProcessName = str3;
                return str3;
            }
        }
        return str;
    }

    public static byte onGetByteFromString(String str, boolean z, int i2) {
        if (z) {
            tmpLog("onGetByteFromString, byte, strIn = " + str + ", bPrint = " + z + ", hexOrDec = " + i2);
        }
        byte bByteValue = 0;
        if (str == null) {
            tmpLog("strIn is null, return 0");
            return (byte) 0;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            tmpLog("str is empty, return 0");
            return (byte) 0;
        }
        if (1 == i2) {
            ByteBuffer byteBufferOnGetHexDataBufferFromString = onGetHexDataBufferFromString(strTrim, z);
            if (byteBufferOnGetHexDataBufferFromString != null) {
                bByteValue = byteBufferOnGetHexDataBufferFromString.get();
            }
        } else {
            bByteValue = new BigDecimal(strTrim).byteValue();
        }
        if (z) {
            tmpLog("val = " + ((int) bByteValue));
        }
        return bByteValue;
    }

    public static void addStringList(ArrayList arrayList, String str) {
        if (arrayList == null || str == null) {
            return;
        }
        arrayList.add(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte getByteFromString(String str, boolean z, boolean z2) {
        if (z) {
            tmpLog("getByteFromString, byte, strIn = " + str + ", bPrint = " + z + ", hexNotDec = " + z2);
        }
        byte b2 = 0;
        b2 = 0;
        b2 = 0;
        if (str == null) {
            tmpLog("strIn is null, return 0");
            return (byte) 0;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            tmpLog("str is empty, return 0");
            return (byte) 0;
        }
        try {
            if (z2) {
                ByteBuffer hexDataBufferFromString = getHexDataBufferFromString(strTrim, z);
                if (hexDataBufferFromString != null) {
                    strTrim = strTrim;
                    b2 = hexDataBufferFromString.get();
                    strTrim = strTrim;
                }
            } else {
                byte bByteValue = new BigDecimal(strTrim).byteValue();
                b2 = bByteValue;
                strTrim = bByteValue;
            }
            strTrim = strTrim;
        } catch (ArithmeticException e2) {
            log("ArithmeticException, str = " + strTrim);
            log("ArithmeticException: " + e2);
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            log("NumberFormatException, str = " + strTrim);
            log("NumberFormatException: " + e3);
            e3.printStackTrace();
        }
        if (z) {
            tmpLog("val = " + ((int) b2));
        }
        return b2;
    }

    public static ByteBuffer getBuffer(byte[] bArr) {
        if (bArr == null) {
            tmpLog("bytes is null, getBuffer do nothing");
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        return byteBufferWrap;
    }

    public static byte[] getBytes(float f2) {
        byte[] bArr = new byte[4];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        byteBufferWrap.putFloat(f2);
        return bArr;
    }

    public static int getPhoneCount(Context context) {
        if (context == null) {
            log("cnt is null");
            return 0;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getActiveModemCount();
    }

    public static boolean isChinaBuild(OemHookAgent oemHookAgent) {
        if (oemHookAgent == null) {
            log("isChinaBuild, hook is null");
            return false;
        }
        String strOnHookPropGetSync = oemHookAgent.onHookPropGetSync(PROP_RADIO_BUILD_REGION_VENDOR, "none");
        boolean zEquals = TextUtils.equals(strOnHookPropGetSync, PROP_MIUI_BUILD_REGION_CN);
        log("isChinaBuild, ret = " + zEquals + ", val = " + strOnHookPropGetSync);
        return zEquals;
    }

    public static void addStringListWithNoRepeat(ArrayList arrayList, String[] strArr) {
        if (arrayList == null) {
            log("addStringListWithNoRepeat, list is null");
            return;
        }
        if (strArr == null) {
            log("addStringListWithNoRepeat, arrStr is null");
            return;
        }
        for (String str : strArr) {
            addStringListWithNoRepeat(arrayList, str);
        }
    }

    public static String getString(byte b2, boolean z, boolean z2) {
        return getString(b2, z, z2, true, true);
    }

    public static String getString(byte b2, boolean z, boolean z2, boolean z3, boolean z4) {
        String string;
        if (z3) {
            tmpLog("getString, byte, val = " + ((int) b2) + ", unSigned = " + z + ", bPrint = " + z3 + ", hexNotDec = " + z2 + ", fillHexZero = " + z4);
        }
        if (z2) {
            string = Integer.toHexString(b2 & EFS_VALUE_ASDIV_73841_DYNAMIC_SWITCH);
            if (1 == string.length() && z4) {
                string = "0" + string;
            }
            if (z3) {
                tmpLog("HEX, strVal = " + string);
            }
        } else {
            String str = "" + ((int) b2);
            if (!z || b2 >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf((byte) (b2 & 127)).add(BigDecimal.valueOf(127L)).add(BigDecimal.valueOf(1L)).toString();
                if (z3) {
                    tmpLog("val < 0, new strVal = " + string);
                }
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static byte[] getBytes(long j2) {
        byte[] bArr = new byte[8];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        byteBufferWrap.putLong(j2);
        return bArr;
    }

    public static String getIpStringByApnType(Phone phone, String str) {
        if (phone == null) {
            log("getIpStringByApnType, phone is null");
            return null;
        }
        if (str == null) {
            log("getIpStringByApnType, apnType is null");
        }
        return null;
    }

    public static byte[] getBytes(int i2) {
        byte[] bArr = new byte[4];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        byteBufferWrap.putInt(i2);
        return bArr;
    }

    public static String onGetStringByDateType(short s, int i2, int i3) {
        String string;
        tmpLog("onGetStringByDateType, short, val = " + ((int) s) + ", uFlag = " + i2 + ", hexOrDec = " + i3);
        if (1 == i3) {
            string = Integer.toHexString(s & 65535);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + ((int) s);
            if (1 != i2 || s >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf((short) (s & Short.MAX_VALUE)).add(BigDecimal.valueOf(32767L)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static byte[] getBytes(short s) {
        byte[] bArr = new byte[2];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        byteBufferWrap.putShort(s);
        return bArr;
    }

    public static byte[] getBytes(byte b2) {
        byte[] bArr = new byte[1];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        byteBufferWrap.put(b2);
        return bArr;
    }

    public static String getString(short s, boolean z, boolean z2) {
        String string;
        tmpLog("getString, short, val = " + ((int) s) + ", unSigned = " + z);
        if (z2) {
            string = Integer.toHexString(s & 65535);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + ((int) s);
            if (!z || s >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf((short) (s & Short.MAX_VALUE)).add(BigDecimal.valueOf(32767L)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static String onGetStringByDateType(int i2, int i3, int i4) {
        String string;
        tmpLog("onGetStringByDateType, int, val = " + i2 + ", uFlag = " + i3 + ", hexOrDec = " + i4);
        if (1 == i4) {
            string = Integer.toHexString(i2);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + i2;
            if (1 != i3 || i2 >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf(i2 & Integer.MAX_VALUE).add(BigDecimal.valueOf(2147483647L)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static String getString(int i2, boolean z, boolean z2) {
        String string;
        tmpLog("getString, int, val = " + i2 + ", unSigned = " + z);
        if (z2) {
            string = Integer.toHexString(i2);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + i2;
            if (!z || i2 >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf(i2 & Integer.MAX_VALUE).add(BigDecimal.valueOf(2147483647L)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static byte[] readFileToBytes(int i2) throws Throwable {
        Throwable th;
        InputStream inputStreamOpenRawResource;
        try {
            log("readFileToBytes, resId: " + i2);
            inputStreamOpenRawResource = mContext.getResources().openRawResource(i2);
            try {
                try {
                    byte[] bArr = new byte[1024];
                    int i3 = inputStreamOpenRawResource.read(bArr);
                    log("len: " + i3);
                    byte[] bArr2 = null;
                    byte[] bArr3 = null;
                    int i4 = 0;
                    while (i3 > 0) {
                        if (i4 > 0) {
                            bArr3 = new byte[i4];
                            for (int i5 = 0; i5 < i4; i5++) {
                                bArr3[i5] = bArr2[i5];
                            }
                        }
                        int i6 = i4 + i3;
                        byte[] bArr4 = new byte[i6];
                        for (int i7 = 0; i7 < i4; i7++) {
                            bArr4[i7] = bArr3[i7];
                        }
                        for (int i8 = 0; i8 < i3; i8++) {
                            bArr4[i4 + i8] = bArr[i8];
                        }
                        i3 = inputStreamOpenRawResource.read(bArr);
                        log("lenAll: " + i6 + ", len: " + i3);
                        i4 = i6;
                        bArr2 = bArr4;
                    }
                    inputStreamOpenRawResource.close();
                    try {
                        inputStreamOpenRawResource.close();
                    } catch (Exception e2) {
                        log("readFileToBytes, close Exception: " + e2);
                        e2.printStackTrace();
                    }
                    return bArr2;
                } catch (Exception e3) {
                    e = e3;
                    log("readFileToBytes, Exception: " + e);
                    e.printStackTrace();
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (Exception e4) {
                            log("readFileToBytes, close Exception: " + e4);
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                if (inputStreamOpenRawResource != null) {
                    try {
                        inputStreamOpenRawResource.close();
                    } catch (Exception e5) {
                        log("readFileToBytes, close Exception: " + e5);
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            inputStreamOpenRawResource = null;
        } catch (Throwable th3) {
            th = th3;
            inputStreamOpenRawResource = null;
            if (inputStreamOpenRawResource != null) {
                inputStreamOpenRawResource.close();
            }
            throw th;
        }
    }

    public static String onGetStringByDateType(long j2, int i2, int i3) {
        String string;
        tmpLog("onGetStringByDateType, long, val = " + j2 + ", uFlag = " + i2 + ", hexOrDec = " + i3);
        if (1 == i3) {
            string = Long.toHexString(j2);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + j2;
            if (1 != i2 || j2 >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf(j2 & Long.MAX_VALUE).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static String getString(long j2, boolean z, boolean z2) {
        String string;
        tmpLog("getString, long, val = " + j2 + ", unSigned = " + z + ", hexNotDec = " + z2);
        if (z2) {
            string = Long.toHexString(j2);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + j2;
            if (!z || j2 >= 0) {
                string = str;
            } else {
                string = BigDecimal.valueOf(j2 & Long.MAX_VALUE).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static String onGetStringByDateType(float f2) {
        tmpLog("onGetStringByDateType, float, val = " + f2);
        return ("" + f2).toUpperCase();
    }

    public static String onGetStringByDateType(double d2) {
        tmpLog("onGetStringByDateType, double, val = " + d2);
        return ("" + d2).toUpperCase();
    }

    public static String getString(float f2, boolean z, boolean z2) {
        String string;
        tmpLog("getString, float, val = " + f2 + ", unSigned = " + z + ", hexNotDec = " + z2);
        if (z2) {
            string = Float.toHexString(f2);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + f2;
            if (!z || f2 >= 0.0f) {
                string = str;
            } else {
                string = BigDecimal.valueOf(0.0f).add(BigDecimal.valueOf(3.4028234663852886E38d)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static String getString(float f2) {
        tmpLog("getString, float, val = " + f2);
        return "" + f2;
    }

    public static String getString(double d2, boolean z, boolean z2) {
        String string;
        tmpLog("getString, double, val = " + d2 + ", unSigned = " + z + ", hexNotDec = " + z2);
        if (z2) {
            string = Double.toHexString(d2);
            tmpLog("HEX, strVal = " + string);
        } else {
            String str = "" + d2;
            if (!z || d2 >= 0.0d) {
                string = str;
            } else {
                string = BigDecimal.valueOf(0.0d).add(BigDecimal.valueOf(Double.MAX_VALUE)).add(BigDecimal.valueOf(1L)).toString();
                tmpLog("val < 0, new strVal = " + string);
            }
        }
        return string != null ? string.toUpperCase() : string;
    }

    public static String getString(double d2) {
        tmpLog("getString, double, val = " + d2);
        return "" + d2;
    }
}
