package com.xiaomi.mirilhook;

import android.content.Context;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.os.Registrant;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import com.qti.extphone.BearerAllocationStatus;
import com.qti.extphone.Client;
import com.qti.extphone.DcParam;
import com.qti.extphone.ExtPhoneCallbackBase;
import com.qti.extphone.ExtTelephonyManager;
import com.qti.extphone.IExtPhoneCallback;
import com.qti.extphone.NrConfigType;
import com.qti.extphone.NrIconType;
import com.qti.extphone.ServiceCallback;
import com.qti.extphone.SignalStrength;
import com.qti.extphone.Status;
import com.qti.extphone.Token;
import com.qti.extphone.UpperLayerIndInfo;
import com.qualcomm.qcrilhook.OemHookCallback;
import com.qualcomm.qcrilhook.QcRilHook;
import com.qualcomm.qcrilhook.QcRilHookCallback;
import com.xiaomi.modem.ModemUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.codeaurora.telephony.utils.CommandException;
import org.json.JSONObject;
import vendor.xiaomi.hardware.misys.V1_0.IResultValue;

/* JADX INFO: loaded from: classes.dex */
public class MiRilHook extends QcRilHook {
    private static final int DEFAULT_PHONE = 0;
    private static final int DIAG_CMD_RESPONSE_BUFFER_SIZE = 40960;
    private static final int DYNAMIC_SAR_REQ_NUM = 524489;
    public static final int ESIM_REQUEST_DAL_ERROR = -2;
    public static final int ESIM_REQUEST_EFS_ERROR = -4;
    public static final int ESIM_REQUEST_INVALID = -1;
    public static final int ESIM_REQUEST_MODEM_ERROR = -3;
    public static final int ESIM_REQUEST_SUCCESS = 0;
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
    public static final int EVENT_OEMHOOK_DISPLAY_MIPI_GET = 46;
    public static final int EVENT_OEMHOOK_DISPLAY_MIPI_SET = 45;
    public static final int EVENT_OEMHOOK_DSDA_CURRENT_STATUS = 89;
    public static final int EVENT_OEMHOOK_DSDA_DEFAULT_STATUS = 88;
    public static final int EVENT_OEMHOOK_DSDA_IS_SUPPORTED = 87;
    public static final int EVENT_OEMHOOK_DUMP_BY_ERROR_FATAL = 99;
    public static final int EVENT_OEMHOOK_EFS_COMPARE = 109;
    public static final int EVENT_OEMHOOK_EFS_COPY = 108;
    public static final int EVENT_OEMHOOK_EFS_EXIST = 110;
    public static final int EVENT_OEMHOOK_ERASE_MODEM_ST1_ST2 = 54;
    public static final int EVENT_OEMHOOK_ESIM_PMIC_GPIO_GET = 119;
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
    public static final int EVENT_OEMHOOK_MBN_FEATURE_CURRENT_CONFIG_GET = 112;
    public static final int EVENT_OEMHOOK_MBN_FEATURE_DEFAULT_CONFIG_GET = 111;
    public static final int EVENT_OEMHOOK_MBN_LIST_GET = 50;
    public static final int EVENT_OEMHOOK_MBN_SW_TXT_PATH_INFO_GET = 41;
    public static final int EVENT_OEMHOOK_MCFG_CONFIG_LIST_SIZE_MAX_GET = 52;
    public static final int EVENT_OEMHOOK_MCFG_CONFIG_LIST_SIZE_MAX_SET = 53;
    public static final int EVENT_OEMHOOK_MCFG_RFS_REMOTE_DO = 42;
    public static final int EVENT_OEMHOOK_MI_BROADCAST_STATE_GET = 30;
    public static final int EVENT_OEMHOOK_MI_BROADCAST_STATE_SET = 29;
    public static final int EVENT_OEMHOOK_MODEM_CHIP_NAME_ID_GET = 96;
    public static final int EVENT_OEMHOOK_MODEM_DUMP = 69;
    public static final int EVENT_OEMHOOK_MODEM_ENHANCE_COMMON = 100;
    public static final int EVENT_OEMHOOK_MODEM_FACTORY_BUILD_MACRO_STATE_GET = 15;
    public static final int EVENT_OEMHOOK_MODEM_FEATURE_STATE_GET = 102;
    public static final int EVENT_OEMHOOK_MODEM_GET_CURRENT_ACT_MBN_CONFIG = 13;
    public static final int EVENT_OEMHOOK_MODEM_GET_FIX_RAT_COUNT = 78;
    public static final int EVENT_OEMHOOK_MODEM_HYDRA_CHECK = 94;
    public static final int EVENT_OEMHOOK_MODEM_HYDRA_DEBUG_INFO_GET = 95;
    public static final int EVENT_OEMHOOK_MODEM_HYDRA_DEBUG_INFO_GET_FOR_USER = 97;
    public static final int EVENT_OEMHOOK_MODEM_HYDRA_DIAG_NOT_SUPPORT_INFO_GET = 101;
    public static final int EVENT_OEMHOOK_MODEM_MBN_AUTO_SELECT_OPEN_CHECK = 23;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CHECK = 18;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_ACTIVE = 20;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_DEACTIVE = 16;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_DELETE = 17;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_FILE_PATH_GET = 12;
    public static final int EVENT_OEMHOOK_MODEM_MBN_CONFIG_RECOVERY = 21;
    public static final int EVENT_OEMHOOK_MODEM_MBN_REACTIVATE = 77;
    public static final int EVENT_OEMHOOK_MODEM_MBN_REFRESH = 107;
    public static final int EVENT_OEMHOOK_MODEM_OOS_BASE_TYPE_SET = 75;
    public static final int EVENT_OEMHOOK_MODEM_POWER_AP_CMD = 90;
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
    public static final int EVENT_OEMHOOK_NV_CMD = 118;
    public static final int EVENT_OEMHOOK_NV_EFS_SYNC = 35;
    public static final int EVENT_OEMHOOK_PROP_GET = 24;
    public static final int EVENT_OEMHOOK_PROP_SET = 25;
    public static final int EVENT_OEMHOOK_RF_ANT_DEVICE_DATA_GET = 32;
    public static final int EVENT_OEMHOOK_RF_CABLE_STATUS_GET = 65;
    public static final int EVENT_OEMHOOK_RF_DEVICE_INFO_GET = 8;
    public static final int EVENT_OEMHOOK_RF_DEVICE_TEST_SET = 7;
    public static final int EVENT_OEMHOOK_RF_MIPI_INFO_GET = 9;
    public static final int EVENT_OEMHOOK_SCREEN_STATUS_NOTIFY = 27;
    public static final int EVENT_OEMHOOK_SET_SAR_STATE = 28;
    public static final int EVENT_OEMHOOK_SHELL_CMD_EXECUTE = 26;
    public static final int EVENT_OEMHOOK_SSR_BY_ERROR_FATAL = 31;
    public static final int EVENT_OEMHOOK_UIM_POWER_EX = 85;
    public static final int EVENT_OEMHOOK_UIM_PURGE_GUTI = 93;
    public static final int EVENT_OEMHOOK_XIAOMI_CLEAN_ALL_CD_LWOTA = 130;
    public static final int EVENT_OEMHOOK_XIAOMI_CLOULD_VR_LWOTA = 133;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_DELTE = 6;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_READ = 4;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_STAT = 51;
    public static final int EVENT_OEMHOOK_XIAOMI_EFS_WRITE = 5;
    public static final int EVENT_OEMHOOK_XIAOMI_ID_LWOTA = 131;
    public static final int EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_ED_LWOTA = 125;
    public static final int EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_EW_LWOTA = 124;
    public static final int EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_HEW_LWOTA = 126;
    public static final int EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_HNW_LWOTA = 122;
    public static final int EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_MNW_LWOTA = 123;
    public static final int EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_NW_LWOTA = 121;
    public static final int EVENT_OEMHOOK_XIAOMI_MDU_LWOTA = 129;
    public static final int EVENT_OEMHOOK_XIAOMI_MD_LWOTA = 127;
    public static final int EVENT_OEMHOOK_XIAOMI_MODEM_HAL_CHIP_ID_GET = 82;
    public static final int EVENT_OEMHOOK_XIAOMI_MR_LWOTA = 128;
    public static final int EVENT_OEMHOOK_XIAOMI_NV_DELTE = 3;
    public static final int EVENT_OEMHOOK_XIAOMI_NV_READ = 1;
    public static final int EVENT_OEMHOOK_XIAOMI_NV_WRITE = 2;
    public static final int EVENT_OEMHOOK_XIAOMI_QCN_BACKUP = 33;
    public static final int EVENT_OEMHOOK_XIAOMI_QCN_RECOVERY = 34;
    public static final int EVENT_OEMHOOK_XIAOMI_UR_LWOTA = 132;
    private static final int HOOK_TYPE_QCOM = 2;
    private static final int INT_SIZE = 4;
    private static final String LOG_TAG = "MI_RIL_OEM_HOOK";
    private static final int LONG_SIZE = 8;
    private static final String LWOTA_DATA_ID = "data_id";
    private static final String LWOTA_DESCRIPTION = "description";
    private static final String LWOTA_IMPACT_ID = "impact_id";
    private static final String LWOTA_PLMN = "plmn";
    private static final String LWOTA_SHA_CHECK = "sha_check";
    private static final int MAX_PHONE_COUNT_DUAL_SIM = 2;
    private static final int MAX_PHONE_COUNT_SINGLE_SIM = 1;
    private static final int MAX_PHONE_COUNT_TRI_SIM = 3;
    public static final int MIRIL_HOOK_WORK_MODE_MASK_HYDRA = 2;
    public static final int MIRIL_HOOK_WORK_MODE_MASK_IDL = 1;
    public static final int MODEM_CHIP_NAME_ID_INVALID = 0;
    private static final int MODEM_FIX_RAT_COUNT_INVALID = 255;
    private static final int MODEM_STATS_DATA_INVALID = 65535;
    private static final int MODEM_STATS_DATA_INVALID_1 = -1;
    private static final float MODEM_STATS_DATA_INVALID_2 = -1.0f;
    public static final int NV71527_ADD_NO_VOPS_RAT = 4;
    public static final int NV71527_SET_AUTH_SCHEME = 3;
    public static final int NV73833_GET_VOLTE_ROAMING = 1;
    public static final int NV73833_SET_VOLTE_ROAMING = 2;
    private static final String PROPERTY_MULTI_SIM_CONFIG = "persist.radio.multisim.config";
    private static final String PROP_PRINT_IDLE = "0";
    private static final String PROP_PRINT_OFF = "2";
    private static final String PROP_PRINT_ON = "1";
    private static final String PROP_PRINT_TEMP = "persist.vendor.radio.hook_tmplog";
    private static final int QCRIL_REQ_HOOK_OEM_COMMON = 524970;
    private static final String QUALCOMM_HYDRA_DATA_IND_BC_NAME = "qualcomm.intent.action.ACTION_HYDRA_DATA_IND";
    private static final int RESPONSE_BUFFER_SIZE = 2048;
    private static final int SUB_ALL = 2;
    private static final int SUB_MASK_0 = 1;
    private static final int SUB_MASK_1 = 2;
    private static final int SUB_MASK_ALL = 3;
    private static final int TYPE_5G_CONFIG_INFO = 6;
    private static final int TYPE_5G_STATUS = 1;
    private static final int TYPE_ENABLE_ENDC = 8;
    private static final int TYPE_ENDC_STATUS = 9;
    private static final int TYPE_NR_BEARER_ALLOCATION = 3;
    private static final int TYPE_NR_DCPARAM = 2;
    private static final int TYPE_NR_ICON_TYPE = 7;
    private static final int TYPE_SIGNAL_STRENGTH = 5;
    private static final int TYPE_UPPER_LAYER_INDINFO = 4;
    public static final int XIAOMI_SSR_FOR_HOOK_EVS_SWITCH = -2147483391;
    private final int SUB_0;
    private final int SUB_1;
    private String m5GPackageName;
    private Registrant m5GRegistrant;
    private Client mClient;
    protected IExtPhoneCallback mExtPhoneCallback;
    private ServiceCallback mExtTelManagerServiceCallback;
    private ExtTelephonyManager mExtTelephonyManager;
    private final int mHeaderSize;
    private HashMap<OemHookCallback, MessageRequestInfo> mMapCallbackInfo;
    private OemHookCallback mOemHookCallback;
    private final String mOemIdentifier;
    private HashSet<Registrant> mQcCbRegistrants;
    private byte[] mResponse;
    private ByteBuffer mResponseByteBuffer;
    private static boolean mTmpLogPrintFlag = false;
    private static final String HOOK_IDENTIFIER = "QOEMHOOK";
    private static final int HOOK_HEAD_SIZE = HOOK_IDENTIFIER.length() + 8;
    private static int mNumPhones = 0;
    private static Context mContext = null;
    private static HydraDataManager mHydraMgr = null;
    private static ArrayList<String> mHookUserWhiteList = null;

    public MiRilHook(Context context, QcRilHookCallback cb, String packageName) {
        this(context, cb);
        mContext = context;
        log("MiRilHook, packageName = " + packageName + ", mContext = " + mContext + ", mHydraMgr = " + mHydraMgr + ", cb = " + cb);
        if (mHydraMgr == null && packageName != null) {
            mHydraMgr = new HydraDataManager(this, packageName);
        }
    }

    public MiRilHook(Context context, QcRilHookCallback cb) {
        super(context, cb);
        this.mOemIdentifier = HOOK_IDENTIFIER;
        this.mHeaderSize = HOOK_IDENTIFIER.length() + 8;
        this.SUB_0 = 0;
        this.SUB_1 = 1;
        this.mExtTelephonyManager = null;
        this.mClient = null;
        this.m5GRegistrant = null;
        this.m5GPackageName = null;
        this.mResponse = null;
        this.mResponseByteBuffer = null;
        this.mQcCbRegistrants = new HashSet<>();
        this.mMapCallbackInfo = new HashMap<>();
        this.mOemHookCallback = new OemHookCallback() { // from class: com.xiaomi.mirilhook.MiRilHook.2
            public void onOemHookCallback(byte[] response, int phoneId) {
                if (MiRilHook.this.mQcCbRegistrants == null) {
                    MiRilHook.log("mQcCbRegistrants is null");
                    return;
                }
                if (MiRilHook.this.mQcCbRegistrants.size() == 0) {
                    MiRilHook.log("mQcCbRegistrants size is 0");
                    return;
                }
                for (Registrant registrant : MiRilHook.this.mQcCbRegistrants) {
                    registrant.notifyRegistrant(new AsyncResult((Object) null, response, (Throwable) null));
                }
            }
        };
        this.mExtTelManagerServiceCallback = null;
        this.mExtPhoneCallback = new ExtPhoneCallbackBase() { // from class: com.xiaomi.mirilhook.MiRilHook.5
            public void on5gStatus(int i, Token token, Status status, boolean z) throws RemoteException {
                MiRilHook.log("--on5gStatus, status: " + status + ", enabled: " + z);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[12];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(1);
                MiRilHook.this.mResponseByteBuffer.putInt(i);
                MiRilHook.this.mResponseByteBuffer.putInt(z ? 1 : 0);
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onNrDcParam(int slotId, Token token, Status status, DcParam dcParam) throws RemoteException {
                MiRilHook.log("--onNrDcParam: slotId = " + slotId + ", token = " + token + ", status" + status + ", dcParam = " + dcParam);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[20];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(2);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (dcParam == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(dcParam.getEndc());
                    MiRilHook.this.mResponseByteBuffer.putInt(dcParam.getDcnr());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onAnyNrBearerAllocation(int slotId, Token token, Status status, BearerAllocationStatus bearerStatus) throws RemoteException {
                MiRilHook.log("--onNrBearerAllocationChange: slotId = " + slotId + ", token = " + token + ", status" + status + ", bearerStatus = " + bearerStatus);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(3);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (bearerStatus == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(bearerStatus.getBearerAllocationStatus());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onUpperLayerIndInfo(int slotId, Token token, Status status, UpperLayerIndInfo uilInfo) throws RemoteException {
                MiRilHook.log("--onUpperLayerIndInfo: slotId = " + slotId + ", token = " + token + ", status" + status + ", UpperLayerIndInfo = " + uilInfo);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[20];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(4);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (uilInfo == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(uilInfo.getUpperLayerIndInfoAvailable());
                    MiRilHook.this.mResponseByteBuffer.putInt(uilInfo.getPlmnInfoListAvailable());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onSignalStrength(int slotId, Token token, Status status, SignalStrength signalStrength) throws RemoteException {
                MiRilHook.log("--onSignalStrength: slotId = " + slotId + ", token = " + token + ", status" + status + ", signalStrength = " + signalStrength);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[20];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(5);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (signalStrength == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(signalStrength.getRsrp());
                    MiRilHook.this.mResponseByteBuffer.putInt(signalStrength.getSnr());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void on5gConfigInfo(int slotId, Token token, Status status, NrConfigType nrConfigType) throws RemoteException {
                MiRilHook.log("--on5gConfigInfo: slotId = " + slotId + ", token = " + token + ",  status" + status + ", NrConfigType = " + nrConfigType);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(6);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (nrConfigType == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(nrConfigType.getNrConfigType());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onNrIconType(int slotId, Token token, Status status, NrIconType nrIconType) throws RemoteException {
                MiRilHook.log("--onNrIconType: slotId = " + slotId + ", token = " + token + ", status" + status + ", NrIconType = " + nrIconType);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(7);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (nrIconType == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(nrIconType.get());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onEnableEndc(int slotId, Token token, Status status) throws RemoteException {
                MiRilHook.log("--onEnableEndc: slotId = " + slotId + ", token = " + token + ", status" + status);
                if (slotId < 0 || slotId >= MiRilHook.mNumPhones) {
                    MiRilHook.log("slotId is invalid, mNumPhones = " + MiRilHook.mNumPhones);
                } else if (status.get() == 1 && MiRilHook.this.mClient != null && MiRilHook.this.mExtTelephonyManager != null) {
                    MiRilHook.this.mExtTelephonyManager.queryEndcStatus(slotId, MiRilHook.this.mClient);
                } else {
                    MiRilHook.log("status.get() = " + status.get());
                }
            }

            public void onEndcStatus(int i, Token token, Status status, boolean z) throws RemoteException {
                MiRilHook.log("--onEndcStatus: slotId = " + i + ", token = " + token + ", status" + status + ", endcStatus = " + z);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(9);
                MiRilHook.this.mResponseByteBuffer.putInt(i);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                MiRilHook.this.mResponseByteBuffer.putInt(z ? 1 : 0);
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }
        };
        mContext = context;
        log("MiRilHook, mContext = " + mContext + ", cb = " + cb);
        onInit();
    }

    public MiRilHook(Context context, QcRilHookCallback cb, int workMode) {
        super(context, cb);
        this.mOemIdentifier = HOOK_IDENTIFIER;
        this.mHeaderSize = HOOK_IDENTIFIER.length() + 8;
        this.SUB_0 = 0;
        this.SUB_1 = 1;
        this.mExtTelephonyManager = null;
        this.mClient = null;
        this.m5GRegistrant = null;
        this.m5GPackageName = null;
        this.mResponse = null;
        this.mResponseByteBuffer = null;
        this.mQcCbRegistrants = new HashSet<>();
        this.mMapCallbackInfo = new HashMap<>();
        this.mOemHookCallback = new OemHookCallback() { // from class: com.xiaomi.mirilhook.MiRilHook.2
            public void onOemHookCallback(byte[] response, int phoneId) {
                if (MiRilHook.this.mQcCbRegistrants == null) {
                    MiRilHook.log("mQcCbRegistrants is null");
                    return;
                }
                if (MiRilHook.this.mQcCbRegistrants.size() == 0) {
                    MiRilHook.log("mQcCbRegistrants size is 0");
                    return;
                }
                for (Registrant registrant : MiRilHook.this.mQcCbRegistrants) {
                    registrant.notifyRegistrant(new AsyncResult((Object) null, response, (Throwable) null));
                }
            }
        };
        this.mExtTelManagerServiceCallback = null;
        this.mExtPhoneCallback = new ExtPhoneCallbackBase() { // from class: com.xiaomi.mirilhook.MiRilHook.5
            public void on5gStatus(int i, Token token, Status status, boolean z) throws RemoteException {
                MiRilHook.log("--on5gStatus, status: " + status + ", enabled: " + z);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[12];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(1);
                MiRilHook.this.mResponseByteBuffer.putInt(i);
                MiRilHook.this.mResponseByteBuffer.putInt(z ? 1 : 0);
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onNrDcParam(int slotId, Token token, Status status, DcParam dcParam) throws RemoteException {
                MiRilHook.log("--onNrDcParam: slotId = " + slotId + ", token = " + token + ", status" + status + ", dcParam = " + dcParam);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[20];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(2);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (dcParam == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(dcParam.getEndc());
                    MiRilHook.this.mResponseByteBuffer.putInt(dcParam.getDcnr());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onAnyNrBearerAllocation(int slotId, Token token, Status status, BearerAllocationStatus bearerStatus) throws RemoteException {
                MiRilHook.log("--onNrBearerAllocationChange: slotId = " + slotId + ", token = " + token + ", status" + status + ", bearerStatus = " + bearerStatus);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(3);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (bearerStatus == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(bearerStatus.getBearerAllocationStatus());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onUpperLayerIndInfo(int slotId, Token token, Status status, UpperLayerIndInfo uilInfo) throws RemoteException {
                MiRilHook.log("--onUpperLayerIndInfo: slotId = " + slotId + ", token = " + token + ", status" + status + ", UpperLayerIndInfo = " + uilInfo);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[20];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(4);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (uilInfo == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(uilInfo.getUpperLayerIndInfoAvailable());
                    MiRilHook.this.mResponseByteBuffer.putInt(uilInfo.getPlmnInfoListAvailable());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onSignalStrength(int slotId, Token token, Status status, SignalStrength signalStrength) throws RemoteException {
                MiRilHook.log("--onSignalStrength: slotId = " + slotId + ", token = " + token + ", status" + status + ", signalStrength = " + signalStrength);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[20];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(5);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (signalStrength == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(signalStrength.getRsrp());
                    MiRilHook.this.mResponseByteBuffer.putInt(signalStrength.getSnr());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void on5gConfigInfo(int slotId, Token token, Status status, NrConfigType nrConfigType) throws RemoteException {
                MiRilHook.log("--on5gConfigInfo: slotId = " + slotId + ", token = " + token + ",  status" + status + ", NrConfigType = " + nrConfigType);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(6);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (nrConfigType == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(nrConfigType.getNrConfigType());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onNrIconType(int slotId, Token token, Status status, NrIconType nrIconType) throws RemoteException {
                MiRilHook.log("--onNrIconType: slotId = " + slotId + ", token = " + token + ", status" + status + ", NrIconType = " + nrIconType);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(7);
                MiRilHook.this.mResponseByteBuffer.putInt(slotId);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                if (nrIconType == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(nrIconType.get());
                }
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }

            public void onEnableEndc(int slotId, Token token, Status status) throws RemoteException {
                MiRilHook.log("--onEnableEndc: slotId = " + slotId + ", token = " + token + ", status" + status);
                if (slotId < 0 || slotId >= MiRilHook.mNumPhones) {
                    MiRilHook.log("slotId is invalid, mNumPhones = " + MiRilHook.mNumPhones);
                } else if (status.get() == 1 && MiRilHook.this.mClient != null && MiRilHook.this.mExtTelephonyManager != null) {
                    MiRilHook.this.mExtTelephonyManager.queryEndcStatus(slotId, MiRilHook.this.mClient);
                } else {
                    MiRilHook.log("status.get() = " + status.get());
                }
            }

            public void onEndcStatus(int i, Token token, Status status, boolean z) throws RemoteException {
                MiRilHook.log("--onEndcStatus: slotId = " + i + ", token = " + token + ", status" + status + ", endcStatus = " + z);
                if (MiRilHook.this.m5GRegistrant == null) {
                    MiRilHook.log("m5GRegistrant is null, not to update data.");
                }
                MiRilHook.this.mResponse = new byte[16];
                MiRilHook miRilHook = MiRilHook.this;
                miRilHook.mResponseByteBuffer = ByteBuffer.wrap(miRilHook.mResponse);
                MiRilHook.this.mResponseByteBuffer.putInt(9);
                MiRilHook.this.mResponseByteBuffer.putInt(i);
                if (status == null) {
                    MiRilHook.this.mResponseByteBuffer.putInt(MiRilHook.MODEM_STATS_DATA_INVALID);
                } else {
                    MiRilHook.this.mResponseByteBuffer.putInt(status.get());
                }
                MiRilHook.this.mResponseByteBuffer.putInt(z ? 1 : 0);
                MiRilHook.this.m5GRegistrant.notifyRegistrant(new AsyncResult((Object) null, MiRilHook.this.mResponse, (Throwable) null));
            }
        };
        mContext = context;
        log("MiRilHook, workMode = " + workMode + ", mContext = " + mContext + ", cb = " + cb);
        onInit();
        onInitExtTelManagerServiceCallback();
        log("MiRilHook, mHydraMgr = " + mHydraMgr);
        if (mHydraMgr == null && 2 == (workMode & 2)) {
            log("HYDRA_MODE is on, will create HydraDataManager");
            mHydraMgr = new HydraDataManager(this);
        }
        if (1 == (workMode & 1)) {
            log("IDL_MODE is on, will wait onQcRilHookReady");
        } else {
            log("IDL_MODE is off, will call onQcRilHookReady");
            cb.onQcRilHookReady();
        }
    }

    private void onInit() {
        mNumPhones = getPhoneCount();
        mTmpLogPrintFlag = getTmpLogFlag();
        onInitHookUserWhiteList();
        log("onInit, mNumPhones = " + mNumPhones + ", mTmpLogPrintFlag = " + mTmpLogPrintFlag);
    }

    public void dispose() {
        if (mContext != null) {
            super.dispose();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static int getHookType() {
        return 2;
    }

    private boolean getTmpLogFlag() {
        String str = SystemProperties.get("persist.vendor.radio.hook_tmplog");
        log("getTmpLogFlag, str = " + str);
        if ("1".equals(str)) {
            return true;
        }
        return false;
    }

    private static void onInitHookUserWhiteList() {
        if (mHookUserWhiteList != null) {
            tmpLog("onInitHookUserWhiteList already, mHookUserWhiteList: " + mHookUserWhiteList + ", mHookUserWhiteList.size: " + mHookUserWhiteList.size());
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        mHookUserWhiteList = arrayList;
        arrayList.add("com.xiaomi.mtb");
        mHookUserWhiteList.add("com.android.phone");
        tmpLog("onInitHookUserWhiteList done, mHookUserWhiteList: " + mHookUserWhiteList + ", mHookUserWhiteList.size: " + mHookUserWhiteList.size());
    }

    private boolean onCheckHookUserWhiteList() {
        String processName = ModemUtils.getCurProcessName(mContext);
        if (processName == null) {
            tmpLog("onCheckHookUserWhiteList, processName is null");
            return false;
        }
        ArrayList<String> arrayList = mHookUserWhiteList;
        if (arrayList == null) {
            tmpLog("onCheckHookUserWhiteList, mHookUserWhiteList is not init, processName: " + processName);
            return false;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (true == processName.equals(mHookUserWhiteList.get(i))) {
                tmpLog("onCheckHookUserWhiteList ok, processName: " + processName + ", mHookUserWhiteList: " + mHookUserWhiteList + ", mHookUserWhiteList.size: " + mHookUserWhiteList.size());
                return true;
            }
        }
        tmpLog("onCheckHookUserWhiteList failed, processName: " + processName + ", mHookUserWhiteList: " + mHookUserWhiteList + ", mHookUserWhiteList.size: " + mHookUserWhiteList.size());
        return false;
    }

    public boolean startModemStats(String packageName) {
        log(getCurrentMethodName() + ", packageName = " + packageName);
        return startModemStats();
    }

    public boolean closeModemStats(String packageName) {
        log(getCurrentMethodName() + ", packageName = " + packageName);
        return closeModemStats();
    }

    public boolean startModemStats() {
        String processName = ModemUtils.getCurProcessName(mContext);
        log(getCurrentMethodName() + ", mHydraMgr = " + mHydraMgr + ", mContext = " + mContext + ", processName = " + processName);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.startModemStats(processName);
    }

    public boolean closeModemStats() {
        String processName = ModemUtils.getCurProcessName(mContext);
        log(getCurrentMethodName() + ", mHydraMgr = " + mHydraMgr + ", mContext = " + mContext + ", processName = " + processName);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.closeModemStats(processName);
    }

    public boolean resetModemStats() {
        String processName = ModemUtils.getCurProcessName(mContext);
        log(getCurrentMethodName() + ", mHydraMgr = " + mHydraMgr + ", mContext = " + mContext + ", processName = " + processName);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.resetModemStats();
    }

    public boolean clearIndResource() {
        log(getCurrentMethodName() + ", mHydraMgr = " + mHydraMgr);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.clearIndResource();
    }

    public boolean regRadioTech(int sub, Handler hdl, int what, Object obj) {
        log(getCurrentMethodName() + ", sub = " + sub + ", hdl = " + hdl + ", what = " + what + ", obj = " + obj);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaRegRadioTech.addItem(sub, hdl, what, obj);
    }

    public boolean deregRadioTech() {
        log(getCurrentMethodName());
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaRegRadioTech.removeItem();
    }

    public int getAntNumber() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaRfDpdt.getAntNumber();
    }

    public int getAntPath() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaRfDpdt.getAntPath();
    }

    public int getAntOldConfig() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaRfDpdt.getAntOldConfig();
    }

    public int getAntNewConfig() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaRfDpdt.getAntNewConfig();
    }

    public int getSigPath() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaRfDpdt.getSigPath();
    }

    public int getLteMcc(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteMcc(sub);
    }

    public int getLteMnc(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteMnc(sub);
    }

    public int getLteCellId(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteCellId(sub);
    }

    public int getLteTac(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteTac(sub);
    }

    public int getLteEarfcn(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteEarfcn(sub);
    }

    public int getLtePci(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLtePci(sub);
    }

    public int getLteRsrp(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteRsrp(sub);
    }

    public int getLteRsrq(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteRsrq(sub);
    }

    public int getLteRssi(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteRssi(sub);
    }

    public int getLteSnr(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteSnr(sub);
    }

    public int getLteFreqBandInd(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteFreqBandInd(sub);
    }

    public int getLteDlBandwidth(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteDlBandwidth(sub);
    }

    public int getLteUlBandwidth(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMeasInfo.getLteUlBandwidth(sub);
    }

    public ByteBuffer getLteMeasInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return null;
        }
        return hydraDataManager.mStatsParaDiagLogLteSrvMeas.getLteMeasInfo(sub);
    }

    public int getLteRI(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLtePuschCsf.getLteRI(sub);
    }

    public int getLteCQI(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLtePuschCsf.getLteCQI(sub);
    }

    public int getLtePuschTx(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLteTxPowerB16E.getLtePuschTx(sub);
    }

    public int getLtePuschPathLoss(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLteTxPowerB16E.getLtePuschPathLoss(sub);
    }

    public int getLtePuschMod(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLteTxPower.getLtePuschMod(sub);
    }

    public int getLtePuschMcs(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLteTxPower.getLtePuschMcs(sub);
    }

    public int getLtePdschMcs(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLtePdschStatInd.getLtePdschMcs(sub);
    }

    public int getLtePdschRb(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLtePdschStatInd.getLtePdschRb(sub);
    }

    public int getLtePdschMod(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLtePdschStatInd.getLtePdschMod(sub);
    }

    public int getLteLayersNumForCrnti(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLtePdschStatInd.getLteLayersNumForCrnti(sub);
    }

    public int getLteTbBytes(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return -1;
        }
        return hydraDataManager.mStatsParaDiagLogLtePdschStatInd.getLteTbBytes(sub);
    }

    public float getLteBler(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID_2;
        }
        return hydraDataManager.mStatsParaDiagLogLtePdschStatInd.getLteBler(sub);
    }

    public int getLtePucchTx(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLteTxPowerB16F.getLtePucchTx(sub);
    }

    public int getLtePucchPathLoss(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogLteTxPowerB16F.getLtePucchPathLoss(sub);
    }

    public int getLtePhrInd(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMacUlTransportBlock.getLtePhrInd(sub);
    }

    public int getLteBsrLcg3(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsLteMacUlTransportBlock.getLteBsrLcg3(sub);
    }

    public float getLteFullSchSfnRatio(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID_2;
        }
        return hydraDataManager.mModemStatsLteMacUlTransportBlock.getLteFullSchSfnRatio(sub);
    }

    public boolean isLteRrcActive(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return false;
        }
        return hydraDataManager.mModemStatsLteRrcStateChange.isLteRrcActive(sub);
    }

    public int getLteRrcActiveTime(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return -1;
        }
        return hydraDataManager.mModemStatsLteRrcStateChange.getLteRrcActiveTime(sub);
    }

    public int getLteRrcSetupCount(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return -1;
        }
        return hydraDataManager.mModemStatsLteRrcStateChange.getLteRrcSetupCount(sub);
    }

    public boolean clearLteRrcStateData(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return false;
        }
        return hydraDataManager.mModemStatsLteRrcStateChange.clearLteRrcStateData(sub);
    }

    public ByteBuffer getLteRlfInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteRlfInfo.getLteRlfInfo();
    }

    public ByteBuffer getLteRachFailInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteRachFailInfo.getLteRachFailInfo();
    }

    public ByteBuffer getLteNasRejectInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteNasRejectInfo.getLteNasRejectInfo();
    }

    public ByteBuffer getLteNasRejectList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteNasRejectList.getLteNasRejectList();
    }

    public ByteBuffer getLteNasRejectForDfsList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteNasRejectForDfsList.getLteNasRejectForDfsList();
    }

    public ByteBuffer getLteT3410ExpiryList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteT3410ExpiryList.getLteT3410ExpiryList();
    }

    public ByteBuffer getLteT3411ExpiryList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteT3411ExpiryList.getLteT3411ExpiryList();
    }

    public ByteBuffer getLteT3430ExpiryList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteT3430ExpiryList.getLteT3430ExpiryList();
    }

    public ByteBuffer getLteRlfOfWeakSignalList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteRlfList.getLteRlfOfWeakSignalList(sub);
    }

    public boolean regNr5g22MimoError(int sub, Handler hdl, int what, Object obj) {
        log(getCurrentMethodName() + ", sub = " + sub + ", hdl = " + hdl + ", what = " + what + ", obj = " + obj);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaNr5g22MimoError.addItem(sub, hdl, what, obj);
    }

    public boolean deregNr5g22MimoError() {
        log(getCurrentMethodName());
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaNr5g22MimoError.removeItem();
    }

    public void regPoorNetworkFastRecovery(int type, int sub, Handler hdl, int what, Object obj) {
        if (type == 0) {
            regGsmBasementRecognition(sub, hdl, what, obj);
            return;
        }
        if (1 == type) {
            regWcdmaBasementRecognition(sub, hdl, what, obj);
            return;
        }
        if (2 == type) {
            regLteBasementRecognition(sub, hdl, what, obj);
        } else if (3 == type) {
            regLteEventRecognition(sub, hdl, what, obj);
        } else {
            log(getCurrentMethodName() + ", type is error");
        }
    }

    public void deregregPoorNetworkFastRecovery(int type) {
        if (type == 0) {
            deregGsmBasementRecognition();
            return;
        }
        if (1 == type) {
            deregWcdmaBasementRecognition();
            return;
        }
        if (2 == type) {
            deregLteBasementRecognition();
        } else if (3 == type) {
            deregLteEventRecognition();
        } else {
            log(getCurrentMethodName() + ", type is error");
        }
    }

    public boolean regGsmBasementRecognition(int sub, Handler hdl, int what, Object obj) {
        log(getCurrentMethodName() + ", sub = " + sub + ", hdl = " + hdl + ", what = " + what + ", obj = " + obj);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaGsmBasementRecognition.addItem(sub, hdl, what, obj);
    }

    public boolean deregGsmBasementRecognition() {
        log(getCurrentMethodName());
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaGsmBasementRecognition.removeItem();
    }

    public boolean regWcdmaBasementRecognition(int sub, Handler hdl, int what, Object obj) {
        log(getCurrentMethodName() + ", sub = " + sub + ", hdl = " + hdl + ", what = " + what + ", obj = " + obj);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaWcdmaBasementRecognition.addItem(sub, hdl, what, obj);
    }

    public boolean deregWcdmaBasementRecognition() {
        log(getCurrentMethodName());
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaWcdmaBasementRecognition.removeItem();
    }

    public boolean regLteBasementRecognition(int sub, Handler hdl, int what, Object obj) {
        log(getCurrentMethodName() + ", sub = " + sub + ", hdl = " + hdl + ", what = " + what + ", obj = " + obj);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaLteBasementRecognition.addItem(sub, hdl, what, obj);
    }

    public boolean deregLteBasementRecognition() {
        log(getCurrentMethodName());
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaLteBasementRecognition.removeItem();
    }

    public boolean regLteEventRecognition(int sub, Handler hdl, int what, Object obj) {
        log(getCurrentMethodName() + ", sub = " + sub + ", hdl = " + hdl + ", what = " + what + ", obj = " + obj);
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaLteEventRecognition.addItem(sub, hdl, what, obj);
    }

    public boolean deregLteEventRecognition() {
        log(getCurrentMethodName());
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return false;
        }
        return hydraDataManager.mStatsParaLteEventRecognition.removeItem();
    }

    public int getNr5gTxModeOfPusch(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaRr5gMacUlPhysicalChannelScheduleReport.getNr5gTxModeOfPusch(sub);
    }

    public ByteBuffer getNr5gHighTOosList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gHighTDropList.getNr5gHighTOosList();
    }

    public ByteBuffer getNr5gT3510ExpiryList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gT3510ExpiryList.getNr5gT3510ExpiryList();
    }

    public ByteBuffer getNr5gT3511ExpiryList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gT3511ExpiryList.getNr5gT3511ExpiryList();
    }

    public ByteBuffer getNr5gNasRejectList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gNasRejectList.getNr5gNasRejectList();
    }

    public ByteBuffer getNr5gNasRejectForDfsList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gNasRejectForDfsList.getNr5gNasRejectForDfsList();
    }

    public ByteBuffer getNr5gNasRejectInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gNasRejectInfo.getNr5gNasRejectInfo();
    }

    public ByteBuffer getNr5gRlfInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gRlfInfo.getNr5gRlfInfo();
    }

    public ByteBuffer getNr5gRachFailInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gRachFailInfo.getNr5gRachFailInfo();
    }

    public ByteBuffer getImsSipMessageInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsImsSipMessageInfo.getImsSipMessageInfo();
    }

    public ByteBuffer getNr5gMeasInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gMl1MeasDatabaseUpdateExt.getNr5gMeasInfo(sub);
    }

    public ByteBuffer getLteEventThresholdValueInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteEventThresholdValueInfo.getLteEventThresholdValueInfo();
    }

    public ByteBuffer getLteFullSchInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteFullSchInfo.getLteFullSchInfo();
    }

    public ByteBuffer getLteFullSchList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteFullSchList.getLteFullSchList(sub);
    }

    public ByteBuffer getNr5gFullSchInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gFullSchInfo.getNr5gFullSchInfo();
    }

    public ByteBuffer getNr5gFullSchList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gFullSchList.getNr5gFullSchList(sub);
    }

    public ByteBuffer getLteBlerAbnormalInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteBlerAbnormalInfo.getLteBlerAbnormalInfo();
    }

    public ByteBuffer getLteBlerAbnormalCList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteBlerAbnormalCList.getLteBlerAbnormalCList(sub);
    }

    public ByteBuffer getLteBlerAbnormalPList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteBlerAbnormalPList.getLteBlerAbnormalPList(sub);
    }

    public ByteBuffer getLteTxPowerList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteTxPowerList.getLteTxPowerList(sub);
    }

    public ByteBuffer getNr5gTxPowerList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gTxPowerList.getNr5gTxPowerList(sub);
    }

    public ByteBuffer getLteImsSipMessageList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteImsSipMessageList.getLteImsSipMessageList();
    }

    public ByteBuffer getLteImsSipMessageForDfsList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteImsSipMessageForDfsList.getLteImsSipMessageForDfsList();
    }

    public ByteBuffer getLteImsRtpPacketLossList() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteImsRtpPacketLossList.getLteImsRtpPacketLossList();
    }

    public ByteBuffer getNr5gBlerAbnormalInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gBlerAbnormalInfo.getNr5gBlerAbnormalInfo();
    }

    public ByteBuffer getNr5gBlerAbnormalList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gBlerAbnormalList.getNr5gBlerAbnormalList(sub);
    }

    public ByteBuffer getNr5gPdcpUlDataList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gPdcpUlDataList.getNr5gPdcpUlDataList(sub);
    }

    public ByteBuffer getNr5gPdcpDlDataList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gPdcpDlDataList.getNr5gPdcpDlDataList(sub);
    }

    public ByteBuffer getLtePdcpUlDataList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLtePdcpUlDataList.getLtePdcpUlDataList(sub);
    }

    public ByteBuffer getLtePdcpDlDataList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLtePdcpDlDataList.getLtePdcpDlDataList(sub);
    }

    public ByteBuffer getLteTxPowerInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteTxPowerInfo.getLteTxPowerInfo(sub);
    }

    public ByteBuffer getNr5gTxPowerInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gTxPowerInfo.getNr5gTxPowerInfo(sub);
    }

    public ByteBuffer getLteNlicEngineActiveInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteNlicEngineActiveInfo.getLteNlicEngineActiveInfo(sub);
    }

    public ByteBuffer getMcsTrmAsdivInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsMcsTrmAsdivInfo.getMcsTrmAsdivInfo();
    }

    public ByteBuffer getSwitchConfigAndAntennaInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsSwitchConfigAndAntennaInfo.getSwitchConfigAndAntennaInfo();
    }

    public ByteBuffer getLteUlBlerInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteUlBlerInfo.getLteUlBlerInfo();
    }

    public ByteBuffer getLteRssiAndSigPathInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteRssiSigPathInfo.getLteRssiAndSigPathInfo();
    }

    public ByteBuffer getNrRssiAndSigPathInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNrRssiSigPathInfo.getNrRssiAndSigPathInfo();
    }

    public ByteBuffer getNrChannelInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNrChannelInfo.getNrChannelInfo();
    }

    public ByteBuffer getNr5gUlBlerInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gUlBlerInfo.getNr5gUlBlerInfo();
    }

    public ByteBuffer getLteMcsInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteMcsInfo.getLteMcsInfo();
    }

    public ByteBuffer getNr5gMcsInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gMcsInfo.getNr5gMcsInfo();
    }

    public ByteBuffer getNsaBandCombInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNsaBandCombInfo.getNsaBandCombInfo();
    }

    public ByteBuffer getLteDataSceneInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteDataSceneInfo.getLteDataSceneInfo(sub);
    }

    public ByteBuffer getNr5gDataSceneInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gDataSceneInfo.getNr5gDataSceneInfo(sub);
    }

    public ByteBuffer getLteTimerExpiryList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteTimerExpiryList.getLteTimerExpiryList(sub);
    }

    public ByteBuffer getLteTimerExpiryInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteTimerExpiryInfo.getLteTimerExpiryInfo(sub);
    }

    public ByteBuffer getNr5gTimerExpiryList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gTimerExpiryList.getNr5gTimerExpiryList(sub);
    }

    public ByteBuffer getNasMm5gStateList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNasMm5gStateList.getNasMm5gStateList(sub);
    }

    public ByteBuffer getLteNasEmmStateList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteNasEmmStateList.getLteNasEmmStateList(sub);
    }

    public ByteBuffer getNr5gRrcIratRedirFromNrList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gRrcIratRedirFromNrList.getNr5gRrcIratRedirFromNrList(sub);
    }

    public ByteBuffer getNr5gRrcIratReselFromNrList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gRrcIratReselFromNrList.getNr5gRrcIratReselFromNrList(sub);
    }

    public ByteBuffer getNr5gRrcIratHoFromNrList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gRrcIratHoFromNrList.getNr5gRrcIratHoFromNrList(sub);
    }

    public ByteBuffer getLteMl1IratMeasList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteMl1IratMeasList.getLteMl1IratMeasList(sub);
    }

    public ByteBuffer getNr5gRlfList(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gRlfList.getNr5gRlfList(sub);
    }

    public ByteBuffer getLteRfBandAndAntennaNumberInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteRfBandAndAntennaNumberInfo.getLteRfBandAndAntennaNumberInfo(sub);
    }

    public ByteBuffer getNr5gRfBandAndAntennaNumberInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gRfBandAndAntennaNumberInfo.getNr5gRfBandAndAntennaNumberInfo(sub);
    }

    public ByteBuffer getNr5gSawAndSawlessTimeStatisticsInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gSawAndSawlessTimeStatisticsInfo.getNr5gSawAndSawlessTimeStatisticsInfo(sub);
    }

    public ByteBuffer getLteAiDataInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteAiDataInfo.getLteAiDataInfo(sub);
    }

    public ByteBuffer getNr5gAiDataInfo(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsNr5gAiDataInfo.getNr5gAiDataInfo(sub);
    }

    public ByteBuffer getLteAndNr5gBandwidthInfo() {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null");
            return null;
        }
        return hydraDataManager.mModemStatsLteAndNr5gBandwidthInfo.getLteAndNr5gBandwidthInfo();
    }

    public int getNr5gShortBsrIdx(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gL2UlTb.getNr5gShortBsrIdx(sub);
    }

    public float getNr5gFullSchSfnRatio(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID_2;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gL2UlTb.getNr5gFullSchSfnRatio(sub);
    }

    public int getNr5gFreq(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagEventNr5gRrcCellInfo.getNr5gFreq(sub);
    }

    public int getNr5gPci(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagEventNr5gRrcCellInfo.getNr5gPci(sub);
    }

    public int getNr5gBandWidth(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagEventNr5gRrcCellInfo.getNr5gBandWidth(sub);
    }

    public float getNr5gRsrp(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return 65535.0f;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacSrvBeam.getNr5gRsrp(sub);
    }

    public float getNr5gRsrq(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return 65535.0f;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacSrvBeam.getNr5gRsrq(sub);
    }

    public int getNr5gPuschTx(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacUlPhyPower.getNr5gPuschTx(sub);
    }

    public int getNr5gPuschPathLoss(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacUlPhyPowerB8D2.getNr5gPuschPathLoss(sub);
    }

    public int getNr5gPucchTx(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacUlPhyPowerB8D2.getNr5gPucchTx(sub);
    }

    public int getNr5gPucchPathLoss(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacUlPhyPowerB8D2.getNr5gPucchPathLoss(sub);
    }

    public int getNr5gRI(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacCsfReport.getNr5gRI(sub);
    }

    public int getNr5gCQI(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacCsfReport.getNr5gCQI(sub);
    }

    public float getNr5gBler(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID_2;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacPdschStats.getNr5gBler(sub);
    }

    public long getNr5gTbBytes(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return -1L;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacPdschStats.getNr5gTbBytes(sub);
    }

    public int getNr5gPdschMod(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacPdschStatus.getNr5gPdschMod(sub);
    }

    public int getNr5gPdschMcs(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacPdschStatus.getNr5gPdschMcs(sub);
    }

    public int getNr5gLayersNumForCrnti(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacPdschStatus.getNr5gLayersNumForCrnti(sub);
    }

    public int getNr5gTA(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacRachAttempt.getNr5gTA(sub);
    }

    public int getNr5gDlMcs(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacDciInfo.getNr5gDlMcs(sub);
    }

    public int getNr5gUlMcs(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacDciInfo.getNr5gUlMcs(sub);
    }

    public int getNr5gRbNum(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mStatsParaDiagLogNr5gMacDciInfo.getNr5gRbNum(sub);
    }

    public int getNr5gBand(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mNr5gRrcConfigurationInfo.getNr5gBand(sub);
    }

    public int getNr5gDlArfcn(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mNr5gRrcConfigurationInfo.getNr5gDlArfcn(sub);
    }

    public long getHOTime(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return 0L;
        }
        return hydraDataManager.mStatsParaDiagEventHandOver.getHOTime(sub);
    }

    public int getHOType(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return 0;
        }
        return hydraDataManager.mStatsParaDiagEventHandOver.getHOType(sub);
    }

    public int getHOFreq(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return 0;
        }
        return hydraDataManager.mStatsParaDiagEventHandOver.getHOFreq(sub);
    }

    public int getHOPci(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return 0;
        }
        return hydraDataManager.mStatsParaDiagEventHandOver.getHOPci(sub);
    }

    public int getImsCodecType(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsImsRtpSnAndPayload.getImsCodecType(sub);
    }

    public int getImsFrameType(int sub) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return MODEM_STATS_DATA_INVALID;
        }
        return hydraDataManager.mModemStatsImsRtpSnAndPayload.getImsFrameType(sub);
    }

    public ByteBuffer getTestExpendRequestDataByTimestamp(int sub, long timestamp) {
        HydraDataManager hydraDataManager = mHydraMgr;
        if (hydraDataManager == null) {
            log(getCurrentMethodName() + ", mHydraMgr is null, sub = " + sub);
            return null;
        }
        return hydraDataManager.mModemStatsTestExpendRequest.getTestExpendRequestDataByTimestamp(sub, timestamp);
    }

    private org.codeaurora.telephony.utils.AsyncResult sendRilOemHookMsg(String packageName, int requestId, byte[] request, int phoneId) {
        byte[] response = new byte[RESPONSE_BUFFER_SIZE];
        log("sendRilOemHookMsg: Outgoing Data is " + ModemUtils.bytesToHexString(request) + ", requestId = " + requestId + ", packageName = " + packageName + ", phoneId = " + phoneId);
        try {
            int retVal = this.mService.sendOemRilRequestRawEx(packageName, request, response, phoneId);
            log("sendOemRilRequestRawEx returns value = " + retVal);
            if (retVal >= 0) {
                byte[] validResponseBytes = null;
                if (retVal > 0) {
                    validResponseBytes = Arrays.copyOf(response, retVal);
                }
                org.codeaurora.telephony.utils.AsyncResult ar = new org.codeaurora.telephony.utils.AsyncResult(Integer.valueOf(retVal), validResponseBytes, (Throwable) null);
                return ar;
            }
            byte[] validResponseBytes2 = Arrays.copyOf(response, response.length);
            CommandException ex = CommandException.fromRilErrno(retVal * (-1));
            org.codeaurora.telephony.utils.AsyncResult ar2 = new org.codeaurora.telephony.utils.AsyncResult(request, validResponseBytes2, ex);
            return ar2;
        } catch (RemoteException e) {
            log("sendOemRilRequestRawEx RequestID = " + requestId + " exception, unable to send RIL request from this application: " + e);
            org.codeaurora.telephony.utils.AsyncResult ar3 = new org.codeaurora.telephony.utils.AsyncResult(Integer.valueOf(requestId), (Object) null, e);
            return ar3;
        } catch (NullPointerException e2) {
            log("NullPointerException caught at sendOemRilRequestRawEx.RequestID = " + requestId + ". Return Error");
            org.codeaurora.telephony.utils.AsyncResult ar4 = new org.codeaurora.telephony.utils.AsyncResult(Integer.valueOf(requestId), (Object) null, e2);
            return ar4;
        }
    }

    public org.codeaurora.telephony.utils.AsyncResult sendQcRilHookMsg(String packageName, int requestId, byte[] request, int phoneId) {
        validateInternalState();
        byte[] request_new = new byte[this.mHeaderSize + request.length];
        ByteBuffer reqBuffer = createBufferWithNativeByteOrder(request_new);
        addQcRilHookHeader(reqBuffer, requestId, request.length);
        reqBuffer.put(request);
        return sendRilOemHookMsg(packageName, requestId, request_new, phoneId);
    }

    private org.codeaurora.telephony.utils.AsyncResult sendRilOemHookMsg(int requestId, byte[] request, int responseSize, int phoneId) {
        log("sendRilOemHookMsg: Outgoing Data is " + ModemUtils.bytesToHexString(request) + ", requestId = " + requestId + ", responseSize = " + responseSize + ", phoneId = " + phoneId);
        if (responseSize <= 0) {
            log("sendRilOemHookMsg: responseSize is invalid");
            return null;
        }
        byte[] response = new byte[responseSize];
        try {
            int retVal = this.mService.sendOemRilRequestRaw(request, response, phoneId);
            log("sendOemRilRequestRaw returns value = " + retVal);
            if (retVal >= 0) {
                byte[] validResponseBytes = null;
                if (retVal > 0) {
                    validResponseBytes = Arrays.copyOf(response, retVal);
                }
                org.codeaurora.telephony.utils.AsyncResult ar = new org.codeaurora.telephony.utils.AsyncResult(Integer.valueOf(retVal), validResponseBytes, (Throwable) null);
                return ar;
            }
            byte[] validResponseBytes2 = Arrays.copyOf(response, response.length);
            CommandException ex = CommandException.fromRilErrno(retVal * (-1));
            org.codeaurora.telephony.utils.AsyncResult ar2 = new org.codeaurora.telephony.utils.AsyncResult(request, validResponseBytes2, ex);
            return ar2;
        } catch (RemoteException e) {
            log("sendOemRilRequestRaw RequestID = " + requestId + " exception, unable to send RIL request from this application: " + e);
            org.codeaurora.telephony.utils.AsyncResult ar3 = new org.codeaurora.telephony.utils.AsyncResult(Integer.valueOf(requestId), (Object) null, e);
            return ar3;
        } catch (NullPointerException e2) {
            log("NullPointerException caught at sendOemRilRequestRaw.RequestID = " + requestId + ". Return Error");
            org.codeaurora.telephony.utils.AsyncResult ar4 = new org.codeaurora.telephony.utils.AsyncResult(Integer.valueOf(requestId), (Object) null, e2);
            return ar4;
        }
    }

    public org.codeaurora.telephony.utils.AsyncResult sendQcRilHookMsg(int requestId, byte[] request, int responseSize, int phoneId) {
        validateInternalState();
        byte[] request_new = new byte[this.mHeaderSize + request.length];
        ByteBuffer reqBuffer = createBufferWithNativeByteOrder(request_new);
        addQcRilHookHeader(reqBuffer, requestId, request.length);
        reqBuffer.put(request);
        return sendRilOemHookMsg(requestId, request_new, responseSize, phoneId);
    }

    public ByteBuffer sendQcRilHookMsgSync(int requestId, byte[] request, int responseSize, int phoneId) {
        org.codeaurora.telephony.utils.AsyncResult ar = sendQcRilHookMsg(requestId, request, responseSize, phoneId);
        log("sendQcRilHookMsgSync, requestId = " + requestId + ", responseSize = " + responseSize + ", phoneId = " + phoneId + ", ar = " + ar);
        if (ar == null) {
            log("ar is null");
            return null;
        }
        if (ar.exception != null) {
            log("exception, ar.exception = " + ar.exception);
            return null;
        }
        if (ar.result == null) {
            log("Null Response");
            return null;
        }
        byte[] response = (byte[]) ar.result;
        ByteBuffer byteBuf = ByteBuffer.wrap(response);
        byteBuf.order(ByteOrder.nativeOrder());
        return byteBuf;
    }

    public ByteBuffer sendQcRilHookMsgSync(int requestId, byte[] request) {
        return sendQcRilHookMsgSync(requestId, request, 0);
    }

    public ByteBuffer sendQcRilHookMsgSync(int requestId, byte[] request, int phoneId) {
        org.codeaurora.telephony.utils.AsyncResult ar = sendQcRilHookMsg(requestId, request, phoneId);
        log("sendQcRilHookMsgSync, requestId = " + requestId + ", phoneId = " + phoneId + ", ar = " + ar);
        if (ar == null) {
            log("ar is null");
            return null;
        }
        if (ar.exception != null) {
            log("exception, ar.exception = " + ar.exception);
            return null;
        }
        if (ar.result == null) {
            log("Null Response");
            return null;
        }
        byte[] response = (byte[]) ar.result;
        ByteBuffer byteBuf = ByteBuffer.wrap(response);
        byteBuf.order(ByteOrder.nativeOrder());
        return byteBuf;
    }

    public ByteBuffer sendQcRilHookMsgSync(String packageName, int requestId, byte[] request) {
        return sendQcRilHookMsgSync(packageName, requestId, request, 0);
    }

    public ByteBuffer sendQcRilHookMsgSync(String packageName, int requestId, byte[] request, int phoneId) {
        org.codeaurora.telephony.utils.AsyncResult ar = sendQcRilHookMsg(packageName, requestId, request, phoneId);
        log("sendQcRilHookMsgSync, packageName = " + packageName + ", requestId = " + requestId + ", phoneId = " + phoneId + ", ar = " + ar);
        if (ar == null) {
            log("ar is null");
            return null;
        }
        if (ar.exception != null) {
            log("exception, ar.exception = " + ar.exception);
            return null;
        }
        if (ar.result == null) {
            log("Null Response");
            return null;
        }
        byte[] response = (byte[]) ar.result;
        ByteBuffer byteBuf = ByteBuffer.wrap(response);
        byteBuf.order(ByteOrder.nativeOrder());
        return byteBuf;
    }

    public static String byteBufferToString(ByteBuffer buffer) {
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            buffer.flip();
            return charBuffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            log("byteBufferToString Exception: " + ex);
            return null;
        }
    }

    private int getPhoneCount() {
        String mSimConfig = SystemProperties.get(PROPERTY_MULTI_SIM_CONFIG);
        if (mSimConfig.equals("dsds") || mSimConfig.equals("dsda")) {
            return 2;
        }
        if (mSimConfig.equals("tsts")) {
            return 3;
        }
        return 1;
    }

    public static boolean putByteToBuffer(ByteBuffer buf, byte[] value, int len) {
        log("putByteToBuffer, len: " + len);
        if (value == null) {
            log("putByteToBuffer, value is null");
            return false;
        }
        if (buf == null) {
            log("putByteToBuffer, buf is null");
            return false;
        }
        if (len <= 0) {
            log("len can not <= 0");
            return false;
        }
        for (int i = 0; i < len; i++) {
            buf.put(value[i]);
        }
        return true;
    }

    private OemHookCallback onGetHookCallBackCb(int msg_id) {
        log("onGetHookCallBackCb, msg_id = " + msg_id);
        Message msg = Message.obtain(null, msg_id, 0, 0);
        OemHookCallback oemHookCb = new OemHookCallback(msg);
        return oemHookCb;
    }

    private byte[] onGetHookMsgBytes(int msg_len) {
        StringBuilder sbAppend = new StringBuilder().append("onGetHookMsgBytes, HOOK_HEAD_SIZE = ");
        int i = HOOK_HEAD_SIZE;
        log(sbAppend.append(i).append(", msg_len = ").append(msg_len).toString());
        byte[] hookBytes = new byte[i + msg_len];
        return hookBytes;
    }

    private ByteBuffer onGetHookMsgBuffer(byte[] hookBytes) {
        if (hookBytes == null) {
            log("hookBytes is null");
            return null;
        }
        ByteBuffer reqBuffer = createBufferWithNativeByteOrder(hookBytes);
        return reqBuffer;
    }

    private boolean onHookSend(int msg_id, byte[] requestBytes) {
        return onHookSend(msg_id, requestBytes, 0);
    }

    public class MiHookCallback extends OemHookCallback {
        public Message mMsg;

        public MiHookCallback(Message msg) {
            super(msg);
            this.mMsg = msg;
        }

        public void onOemHookResponse(byte[] response, int phoneId) {
            MiRilHook.log("onOemHookResponse, mMsg.what: " + this.mMsg.what + ", phoneId: " + phoneId);
            MiRilHook.this.onHookAgentResponse(this.mMsg.what, response, phoneId);
        }
    }

    public void onHookAgentResponse(int msgWhat, byte[] response, int phoneId) {
        log("onHookAgentResponse, phoneId: " + phoneId + ", msgWhat: " + msgWhat);
    }

    private boolean onHookSend(int msg_id, byte[] requestBytes, int phoneId) {
        log("onHookSend, msg_id = " + msg_id + ", phoneId = " + phoneId);
        if (!onCheckHookUserWhiteList()) {
            log("Hook call_permission audit failed");
            return false;
        }
        if (phoneId < 0 || phoneId >= mNumPhones) {
            log("phoneId is invalid");
            return false;
        }
        if (requestBytes == null) {
            log("requestBytes is null");
            return false;
        }
        Message msg = Message.obtain(null, msg_id, 0, 0);
        OemHookCallback oemHookCb = new MiHookCallback(msg);
        sendQcRilHookMsgAsync(QCRIL_REQ_HOOK_OEM_COMMON, requestBytes, oemHookCb, phoneId);
        log("onHookSend end, msg_id = " + msg_id + ", phoneId = " + phoneId);
        return true;
    }

    private ByteBuffer onHookSendSync(String packageName, byte[] requestBytes) {
        return onHookSendSync(packageName, requestBytes, 0);
    }

    private ByteBuffer onHookSendSync(String packageName, byte[] requestBytes, int phoneId) {
        log("onHookSendSync, phoneId = " + phoneId + ", packageName = " + packageName);
        if (!onCheckHookUserWhiteList()) {
            log("Hook call_permission audit failed");
            return null;
        }
        if (phoneId < 0 || phoneId >= mNumPhones) {
            log("phoneId is invalid");
            return null;
        }
        if (packageName == null) {
            log("packageName is null");
            return null;
        }
        if (requestBytes == null) {
            log("requestBytes is null");
            return null;
        }
        return sendQcRilHookMsgSync(packageName, QCRIL_REQ_HOOK_OEM_COMMON, requestBytes, phoneId);
    }

    private ByteBuffer onHookSendSync(byte[] requestBytes) {
        return onHookSendSync(requestBytes, 0);
    }

    private ByteBuffer onHookSendSync(byte[] requestBytes, int phoneId) {
        log("onHookSendSync, phoneId = " + phoneId);
        if (!onCheckHookUserWhiteList()) {
            log("Hook call_permission audit failed");
            return null;
        }
        if (phoneId < 0 || phoneId >= mNumPhones) {
            log("phoneId is invalid");
            return null;
        }
        if (requestBytes == null) {
            log("requestBytes is null");
            return null;
        }
        return sendQcRilHookMsgSync(QCRIL_REQ_HOOK_OEM_COMMON, requestBytes, phoneId);
    }

    private ByteBuffer onHookSendSync(byte[] requestBytes, int responseSize, int phoneId) {
        log("onHookSendSync, phoneId = " + phoneId + ", responseSize = " + responseSize);
        if (!onCheckHookUserWhiteList()) {
            log("Hook call_permission audit failed");
            return null;
        }
        if (phoneId < 0 || phoneId >= mNumPhones) {
            log("phoneId is invalid");
            return null;
        }
        if (requestBytes == null) {
            log("requestBytes is null");
            return null;
        }
        if (responseSize <= 0) {
            log("responseSize <= 0");
            return null;
        }
        return sendQcRilHookMsgSync(QCRIL_REQ_HOOK_OEM_COMMON, requestBytes, responseSize, phoneId);
    }

    private ByteBuffer onHookSarSendSync(byte[] hookBytes) {
        log("onHookSarSendSync");
        if (!onCheckHookUserWhiteList()) {
            log("Hook call_permission audit failed");
            return null;
        }
        if (hookBytes == null) {
            log("hookBytes is null");
            return null;
        }
        return sendQcRilHookMsgSync(DYNAMIC_SAR_REQ_NUM, hookBytes);
    }

    public static String getCurrentClassName() {
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        String methodName = stacks[1].getClassName();
        return methodName;
    }

    public static String getCurrentMethodName() {
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        String methodName = stacks[1].getMethodName();
        return methodName;
    }

    public ByteBuffer onHookSarDsiSetSyncEx(int value) {
        log("onHookSarDsiSetSyncEx, value = " + value);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(value);
        reqBuffer.putInt(0);
        return onHookSarSendSync(hookBytes);
    }

    public int onSetEsimStatus(int i, boolean z) {
        log("eSIM__ onSetEsimStatus set level: " + i + "update_efs:" + z);
        ByteBuffer byteBufferOnHookCommonMsgSync = onHookCommonMsgSync(84, 8, i, z ? 1 : 0);
        if (byteBufferOnHookCommonMsgSync == null) {
            log("eSIM__ buf is null");
            return -1;
        }
        int i2 = byteBufferOnHookCommonMsgSync.getInt();
        log("eSIM__ onSetEsimStatus r = " + i2);
        if (i2 == 0) {
            return 0;
        }
        int i3 = byteBufferOnHookCommonMsgSync.getInt();
        int i4 = byteBufferOnHookCommonMsgSync.getInt();
        int i5 = byteBufferOnHookCommonMsgSync.getInt();
        log("eSIM__ t = " + i3 + ", l = " + i4 + ", error code = " + i5);
        if (i5 == -4) {
            return -4;
        }
        if (i5 < 0) {
            return -2;
        }
        return -3;
    }

    public int onGetEsimStatus() {
        log("eSIM__ onGetEsimStatus");
        ByteBuffer buf = onHookCommonMsgSync(83, 0);
        if (buf == null) {
            log("eSIM__ buf is null");
            return -1;
        }
        int r = buf.getInt();
        log("eSIM__ onGetEsimStatus r = " + r);
        if (r != 0) {
            return -2;
        }
        int t = buf.getInt();
        int l = buf.getInt();
        int result = buf.getInt();
        log("eSIM__ t = " + t + ", l = " + l + ",gpio status = " + result);
        return result;
    }

    public int onGetEsimGpioStatus(int slot) {
        log("get esim pmic gpio status");
        ByteBuffer buf = onHookCommonMsgSync(EVENT_OEMHOOK_ESIM_PMIC_GPIO_GET, 8, slot);
        if (buf == null) {
            log("eSIM__ buf is null");
            return -1;
        }
        int r = buf.getInt();
        log("eSIM__ onGetEsimStatus r = " + r);
        if (r != 0) {
            return -2;
        }
        int t = buf.getInt();
        int l = buf.getInt();
        int result = buf.getInt();
        log("eSIM__ t = " + t + ", l = " + l + ",gpio status = " + result);
        return result;
    }

    public boolean onHookUimPowerReqEx(int power_state, int slot, int ignore_hotswap_switch) {
        log("eSIM__ onHookUimPowerReqEx, power_state = " + power_state + ", slot = " + slot + ", ignore_hotswap_switch = " + ignore_hotswap_switch);
        ByteBuffer buf = onHookCommonMsgSync(85, power_state, slot, ignore_hotswap_switch);
        if (buf == null) {
            log("eSIM__ buf is null");
            return false;
        }
        int res = buf.getInt();
        log("eSIM__ onHookUimPowerReqEx hook response res = " + res);
        if (res != 0) {
            return false;
        }
        return true;
    }

    public boolean onHookEsimPowerReqEx(int power_state, int slot, int ignore_hotswap_switch, int sim_state) {
        log("eSIM__ onHookUimPowerReqEx, power_state = " + power_state + ", slot = " + slot + ", ignore_hotswap_switch = " + ignore_hotswap_switch + ", sim_state = " + sim_state);
        ByteBuffer buf = onHookCommonMsgSync(86, power_state, slot, ignore_hotswap_switch, sim_state);
        if (buf == null) {
            log("eSIM__ buf is null");
            return false;
        }
        int res = buf.getInt();
        log("eSIM__ onHookUimPowerReqEx hook response res = " + res);
        if (res != 0) {
            return false;
        }
        return true;
    }

    public boolean onHookUimPurgeGutiReq(int slot) {
        log("onHookUimPurgeGutiReq slot = " + slot);
        ByteBuffer buf = onHookCommonMsgSync(93, slot);
        if (buf == null) {
            log("buf is null");
            return false;
        }
        int res = buf.getInt();
        log("onHookUimPurgeGutiReq hook response res = " + res);
        if (res != 0) {
            return false;
        }
        return true;
    }

    public ByteBuffer onHookNVCMD(int nv_cmd_sub_id, int slot, byte[] bytes) {
        log("onHookNVCMD, nv_cmd_sub_id = " + nv_cmd_sub_id + ", slot = " + slot);
        log("bytes.length = " + bytes.length);
        int msg_len = bytes.length + 8;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(nv_cmd_sub_id);
        reqBuffer.putInt(slot);
        reqBuffer.put(bytes);
        ByteBuffer buf = onHookCommonMsgSync(EVENT_OEMHOOK_NV_CMD, hookBytes);
        if (buf == null) {
            log("buf is null");
            return null;
        }
        return buf;
    }

    public boolean onHookCommonMsg(int msg_id) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id));
    }

    public boolean onHookCommonMsgBySub(int msg_id, int phoneId) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id), phoneId);
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id) {
        return onHookSendSync(onPkHookCommonMsg(msg_id));
    }

    public ByteBuffer onHookCommonMsgSyncBySub(int msg_id, int phoneId) {
        return onHookSendSync(onPkHookCommonMsg(msg_id), phoneId);
    }

    public ByteBuffer onHookCommonMsgSyncForBigResponse(int msg_id, int responseSize) {
        return onHookSendSync(onPkHookCommonMsg(msg_id), responseSize, 0);
    }

    private byte[] onPkHookCommonMsg(int msg_id) {
        log("onPkHookCommonMsg, msg_id = " + msg_id);
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        return hookBytes;
    }

    public boolean onHookCommonMsg(int msg_id, int iArg1) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id, iArg1));
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id, int iArg1) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, iArg1));
    }

    public ByteBuffer onHookCommonMsgSyncForBigResponse(int msg_id, int iArg1, int responseSize) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, iArg1), responseSize, 0);
    }

    private byte[] onPkHookCommonMsg(int msg_id, int iArg1) {
        log("onPkHookCommonMsg, msg_id = " + msg_id + ", iArg1 = " + iArg1);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.putInt(iArg1);
        return hookBytes;
    }

    public boolean onHookCommonMsg(int msg_id, long lArg1) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id, lArg1));
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id, long lArg1) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, lArg1));
    }

    public ByteBuffer onHookCommonMsgSyncBySub(int msg_id, long lArg1, int phoneId) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, lArg1), phoneId);
    }

    private byte[] onPkHookCommonMsg(int msg_id, long lArg1) {
        log("onPkHookCommonMsg, msg_id = " + msg_id + ", lArg1 = " + lArg1);
        byte[] hookBytes = onGetHookMsgBytes(12);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.putLong(lArg1);
        return hookBytes;
    }

    public boolean onHookCommonMsg(int msg_id, int iArg1, int iArg2) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id, iArg1, iArg2));
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id, int iArg1, int iArg2) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, iArg1, iArg2));
    }

    private byte[] onPkHookCommonMsg(int msg_id, int iArg1, int iArg2) {
        log("onPkHookCommonMsg, msg_id = " + msg_id + ", iArg1 = " + iArg1 + ", iArg2 = " + iArg2);
        byte[] hookBytes = onGetHookMsgBytes(12);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.putInt(iArg1);
        reqBuffer.putInt(iArg2);
        return hookBytes;
    }

    public boolean onHookCommonMsg(int msg_id, int iArg1, int iArg2, int iArg3) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id, iArg1, iArg2, iArg3));
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id, int iArg1, int iArg2, int iArg3) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, iArg1, iArg2, iArg3));
    }

    private byte[] onPkHookCommonMsg(int msg_id, int iArg1, int iArg2, int iArg3) {
        log("onPkHookCommonMsg, msg_id = " + msg_id + ", iArg1 = " + iArg1 + ", iArg2 = " + iArg2 + ", iArg3 = " + iArg3);
        byte[] hookBytes = onGetHookMsgBytes(16);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.putInt(iArg1);
        reqBuffer.putInt(iArg2);
        reqBuffer.putInt(iArg3);
        return hookBytes;
    }

    public boolean onHookCommonMsg(int msg_id, int iArg1, int iArg2, int iArg3, int iArg4) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id, iArg1, iArg2, iArg3, iArg4));
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id, int iArg1, int iArg2, int iArg3, int iArg4) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, iArg1, iArg2, iArg3, iArg4));
    }

    private byte[] onPkHookCommonMsg(int msg_id, int iArg1, int iArg2, int iArg3, int iArg4) {
        log("onPkHookCommonMsg, msg_id = " + msg_id + ", iArg1 = " + iArg1 + ", iArg2 = " + iArg2 + ", iArg3 = " + iArg3 + ", iArg4 = " + iArg4);
        byte[] hookBytes = onGetHookMsgBytes(20);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.putInt(iArg1);
        reqBuffer.putInt(iArg2);
        reqBuffer.putInt(iArg3);
        reqBuffer.putInt(iArg4);
        return hookBytes;
    }

    public boolean onHookCommonMsg(int msg_id, String strArg1) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id, strArg1));
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id, String strArg1) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, strArg1));
    }

    public ByteBuffer onHookCommonMsgSyncForBigResponse(int msg_id, String strArg1, int responseSize) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, strArg1), responseSize, 0);
    }

    private byte[] onPkHookCommonMsg(int msg_id, String strArg1) {
        log("onPkHookCommonMsg, msg_id = " + msg_id + ", strArg1 = " + strArg1);
        if (strArg1 == null) {
            log("strArg1 is null");
            return null;
        }
        int msg_len = strArg1.length() + 8;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.putInt(strArg1.length());
        reqBuffer.put(strArg1.getBytes());
        return hookBytes;
    }

    public boolean onHookCommonMsg(int msg_id, byte[] bytes) {
        return onHookSend(msg_id, onPkHookCommonMsg(msg_id, bytes));
    }

    public ByteBuffer onHookCommonMsgSync(int msg_id, byte[] bytes) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, bytes));
    }

    public ByteBuffer onHookCommonMsgSyncBySub(int msg_id, byte[] bytes, int phoneId) {
        return onHookSendSync(onPkHookCommonMsg(msg_id, bytes), phoneId);
    }

    private byte[] onPkHookCommonMsg(int msg_id, byte[] bytes) {
        log("onPkHookCommonMsg, msg_id = " + msg_id + ", bytes = " + bytes);
        if (bytes == null) {
            log("bytes is null, will not send this hook msg");
            return null;
        }
        log("bytes.length = " + bytes.length);
        int msg_len = bytes.length + 4;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.put(bytes);
        return hookBytes;
    }

    public boolean onHookShellCmdExeute(String cmd) {
        return onHookSend(26, onPkHookShellCmdExeute(cmd));
    }

    public ByteBuffer onHookShellCmdExeuteSync(String cmd) {
        return onHookSendSync(onPkHookShellCmdExeute(cmd));
    }

    private byte[] onPkHookShellCmdExeute(String cmd) {
        log("onPkHookShellCmdExeute, cmd = " + cmd);
        if (cmd == null) {
            log("cmd is null");
            return null;
        }
        int msg_len = cmd.length() + 8;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(26);
        reqBuffer.putInt(cmd.length());
        reqBuffer.put(cmd.getBytes());
        return hookBytes;
    }

    public boolean onHookAtCmdSend(String at_cmd, int wait_ms) {
        return onHookSend(64, onPkHookAtCmdSend(at_cmd, wait_ms, 0));
    }

    public String onHookAtCmdSendSync(String at_cmd, int wait_ms) {
        return byteBufferToString(onHookSendSync(onPkHookAtCmdSend(at_cmd, wait_ms, 0)));
    }

    public String onHookAtCmdSendSync(String at_cmd, int wait_ms, int onceRead) {
        return byteBufferToString(onHookSendSync(onPkHookAtCmdSend(at_cmd, wait_ms, onceRead)));
    }

    private byte[] onPkHookAtCmdSend(String at_cmd, int wait_ms, int onceRead) {
        log("onPkHookAtCmdSend, wait_ms = " + wait_ms + ", onceRead = " + onceRead + ", at_cmd: " + at_cmd);
        if (at_cmd == null) {
            log("at_cmd is null");
            return null;
        }
        if (at_cmd.length() != at_cmd.getBytes().length) {
            log("at_cmd cannot have chinese");
            return null;
        }
        if (wait_ms < 0) {
            log("wait_ms is not valid");
            return null;
        }
        int msg_len = at_cmd.length() + 12;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(64);
        reqBuffer.putInt(at_cmd.length());
        reqBuffer.put(at_cmd.getBytes());
        reqBuffer.putInt(wait_ms);
        reqBuffer.putInt(onceRead);
        return hookBytes;
    }

    public boolean onHookModemPowerAPCommand(int featureOptType, byte[] bytes) {
        return onHookSend(90, onPkHookModemPowerAPCommand(featureOptType, bytes));
    }

    public ByteBuffer onHookModemPowerAPCommandSync(int featureOptType, byte[] bytes) {
        return onHookSendSync(onPkHookModemPowerAPCommand(featureOptType, bytes));
    }

    private byte[] onPkHookModemPowerAPCommand(int featureOptType, byte[] bytes) {
        log("onPkHookModemPowerAPCommand, featureOptType = " + featureOptType + ", bytes = " + bytes);
        if (bytes == null) {
            log("bytes is null, will not send EVENT_OEMHOOK_MODEM_POWER_AP_CMD");
            return null;
        }
        log("bytes.length = " + bytes.length);
        int msg_len = bytes.length + 12;
        int msg_sub_len = bytes.length + 4;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send EVENT_OEMHOOK_MODEM_POWER_AP_CMD");
            return null;
        }
        reqBuffer.putInt(90);
        reqBuffer.putInt(msg_sub_len);
        reqBuffer.putInt(featureOptType);
        reqBuffer.put(bytes);
        return hookBytes;
    }

    public boolean onHookModemOosBaseTypeSet(int oosType, int subMask) {
        onHookSend(75, onPkHookModemOosBaseTypeSet(oosType, subMask), 1);
        return onHookSend(75, onPkHookModemOosBaseTypeSet(oosType, subMask));
    }

    public ByteBuffer onHookModemOosBaseTypeSetSync(int oosType, int subMask) {
        onHookSendSync(onPkHookModemOosBaseTypeSet(oosType, subMask), 1);
        return onHookSendSync(onPkHookModemOosBaseTypeSet(oosType, subMask));
    }

    private byte[] onPkHookModemOosBaseTypeSet(int oosType, int subMask) {
        log("onPkHookModemOosBaseTypeSet, oosType = " + oosType + ", subMask = " + subMask);
        byte[] hookBytes = onGetHookMsgBytes(16);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(75);
        reqBuffer.putInt(8);
        reqBuffer.putInt(oosType);
        reqBuffer.putInt(subMask);
        return hookBytes;
    }

    public boolean onHookModemUpdateGameMode(int gameMode) {
        return onHookSend(79, onPkHookModemUpdateGameMode(gameMode));
    }

    public ByteBuffer onHookModemUpdateGameModeSync(int gameMode) {
        return onHookSendSync(onPkHookModemUpdateGameMode(gameMode));
    }

    private byte[] onPkHookModemUpdateGameMode(int gameMode) {
        log("onPkHookModemUpdateGameMode, gameMode = " + gameMode);
        byte[] hookBytes = onGetHookMsgBytes(12);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(79);
        reqBuffer.putInt(4);
        reqBuffer.putInt(gameMode);
        return hookBytes;
    }

    public int getModemFixRatCount(int sub) {
        log("getModemFixRatCount, sub = " + sub);
        ByteBuffer buf = onHookSendSync(onPkHookGetModemFixRatCount(sub));
        if (buf == null) {
            log("buf is null");
            return MODEM_FIX_RAT_COUNT_INVALID;
        }
        int r = buf.getInt();
        log("r = " + r);
        if (r != 0) {
            log("Error happen!");
            return MODEM_FIX_RAT_COUNT_INVALID;
        }
        int t = buf.getInt();
        int l = buf.getInt();
        log("t = " + t + ", l = " + l);
        int fixRatCout = buf.getInt();
        log("fixRatCout = " + fixRatCout);
        return fixRatCout;
    }

    private byte[] onPkHookGetModemFixRatCount(int sub) {
        log("onPkHookGetModemFixRatCount, sub = " + sub);
        byte[] hookBytes = onGetHookMsgBytes(12);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(78);
        reqBuffer.putInt(4);
        reqBuffer.putInt(sub);
        return hookBytes;
    }

    public boolean onHookPropGet(String propName, String defaultValue) {
        return onHookSend(24, onPkHookPropGet(propName, defaultValue));
    }

    public String propGet(String propName, String defaultValue) {
        String propValue = onHookPropGetSync(propName, defaultValue);
        log("propGet, propName = " + propName + ", defaultValue = " + defaultValue + ", propValue = " + propValue);
        return propValue;
    }

    public String onHookPropGetSync(String propName, String defaultValue) {
        ByteBuffer buf = onHookSendSync(onPkHookPropGet(propName, defaultValue));
        if (buf == null) {
            log("Maybe not support this hook interface, or no permit for read this prop");
            return "";
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("onHookPropGetSync, Maybe your input defaultValue is empty");
            return "";
        }
        buf.getInt();
        return byteBufferToString(buf);
    }

    private byte[] onPkHookPropGet(String propName, String defaultValue) {
        log("onPkHookPropGet, propName = " + propName + ", defaultValue = " + defaultValue);
        if (propName == null) {
            log("propName is null");
            return null;
        }
        if (defaultValue == null) {
            log("defaultValue is null");
            return null;
        }
        int msg_len = propName.length() + 12 + defaultValue.length();
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(24);
        reqBuffer.putInt(propName.length());
        reqBuffer.put(propName.getBytes());
        reqBuffer.putInt(defaultValue.length());
        reqBuffer.put(defaultValue.getBytes());
        return hookBytes;
    }

    public boolean onHookPropSet(String propName, String value) {
        return onHookSend(25, onPkHookPropSet(propName, value));
    }

    public boolean propSet(String propName, String value) {
        ByteBuffer byteBuf = onHookPropSetSync(propName, value);
        if (byteBuf == null) {
            log("propSet, byteBuf is null");
            return false;
        }
        int ret = byteBuf.getInt();
        log("propSet, propName = " + propName + ", ret = " + ret);
        if (ret != 0) {
            log("propSet failed");
            return false;
        }
        return true;
    }

    public ByteBuffer onHookPropSetSync(String propName, String value) {
        return onHookSendSync(onPkHookPropSet(propName, value));
    }

    private byte[] onPkHookPropSet(String propName, String value) {
        log("onPkHookPropSet, propName = " + propName + ", value = " + value);
        if (propName == null) {
            log("propName is null");
            return null;
        }
        if (value == null) {
            log("value is null");
            return null;
        }
        int msg_len = propName.length() + 12 + value.length();
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(25);
        reqBuffer.putInt(propName.length());
        reqBuffer.put(propName.getBytes());
        reqBuffer.putInt(value.length());
        reqBuffer.put(value.getBytes());
        return hookBytes;
    }

    public boolean onHookDiagSend(byte[] cmdBytes) {
        return onHookSend(38, onPkHookDiagSend(cmdBytes));
    }

    public ByteBuffer onHookDiagSendSync(byte[] cmdBytes) {
        return onHookSendSync(onPkHookDiagSend(cmdBytes));
    }

    private byte[] onPkHookDiagSend(byte[] cmdBytes) {
        log("onPkHookDiagSend");
        if (cmdBytes == null) {
            log("cmdBytes is null");
            return null;
        }
        int msg_len = cmdBytes.length + 8;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(38);
        reqBuffer.putInt(cmdBytes.length);
        reqBuffer.put(cmdBytes);
        return hookBytes;
    }

    public boolean onHookDiagSendWithResult(byte[] cmdBytes, int waitTime) {
        return onHookSend(66, onPkHookDiagSendWithResult(cmdBytes, waitTime));
    }

    public ByteBuffer onHookDiagSendWithResultSync(byte[] cmdBytes, int waitTime) {
        return onHookSendSync(onPkHookDiagSendWithResult(cmdBytes, waitTime), DIAG_CMD_RESPONSE_BUFFER_SIZE, 0);
    }

    private byte[] onPkHookDiagSendWithResult(byte[] cmdBytes, int waitTime) {
        log("onPkHookDiagSendWithResult");
        if (cmdBytes == null) {
            log("cmdBytes is null");
            return null;
        }
        int msg_len = cmdBytes.length + 12;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(66);
        reqBuffer.putInt(waitTime);
        reqBuffer.putInt(cmdBytes.length);
        reqBuffer.put(cmdBytes);
        return hookBytes;
    }

    public boolean onHookMbnReactivate(int subMask) {
        return onHookSend(77, onPkHookMbnReactivate(subMask));
    }

    public ByteBuffer onHookMbnReactivateSync(int subMask) {
        return onHookSendSync(onPkHookMbnReactivate(subMask));
    }

    private byte[] onPkHookMbnReactivate(int subMask) {
        log("onPkHookMbnReactivate, subMask = " + subMask);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(77);
        reqBuffer.putInt(subMask);
        return hookBytes;
    }

    public boolean onHookMbnRefresh(int subMask) {
        return onHookSend(EVENT_OEMHOOK_MODEM_MBN_REFRESH, onPkHookMbnRefresh(subMask));
    }

    public ByteBuffer onHookMbnRefreshSync(int subMask) {
        return onHookSendSync(onPkHookMbnRefresh(subMask));
    }

    private byte[] onPkHookMbnRefresh(int subMask) {
        log("onPkHookMbnRefresh, subMask = " + subMask);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(EVENT_OEMHOOK_MODEM_MBN_REFRESH);
        reqBuffer.putInt(subMask);
        return hookBytes;
    }

    public boolean onHookMbnOpt(int msg_id, int mbn_type, String mbnFile, int subIndexMask) {
        return onHookSend(msg_id, onPkHookMbnOpt(msg_id, mbn_type, mbnFile, subIndexMask));
    }

    public ByteBuffer onHookMbnOptSync(int msg_id, int mbn_type, String mbnFile, int subIndexMask) {
        return onHookSendSync(onPkHookMbnOpt(msg_id, mbn_type, mbnFile, subIndexMask));
    }

    private byte[] onPkHookMbnOpt(int msg_id, int mbn_type, String mbnFile, int subIndexMask) {
        log("onPkHookMbnOpt, msg_id = " + msg_id + ", mbn_type = " + mbn_type + ", subIndexMask = " + subIndexMask + ", mbnFile = " + mbnFile);
        if (mbnFile == null) {
            log("mbnFile is null");
            return null;
        }
        if (mbn_type != 0 && 1 != mbn_type) {
            log("mbn_type is invalid");
            return null;
        }
        int msg_len = mbnFile.length() + 12;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msg_id);
        reqBuffer.putInt(mbn_type);
        reqBuffer.putInt(subIndexMask);
        reqBuffer.putInt(mbnFile.length());
        reqBuffer.put(mbnFile.getBytes());
        return hookBytes;
    }

    public boolean onHookMbnCheck() {
        return onHookSend(18, onPkHookMbnCheck());
    }

    public ByteBuffer onHookMbnCheckSync() {
        return onHookSendSync(onPkHookMbnCheck());
    }

    private byte[] onPkHookMbnCheck() {
        log("onPkHookMbnCheck");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(18);
        return hookBytes;
    }

    public boolean onHookModSwVerGet() {
        return onHookSend(19, onPkHookModSwVerGet());
    }

    public ByteBuffer onHookModSwVerGetSync() {
        return onHookSendSync(onPkHookModSwVerGet());
    }

    private byte[] onPkHookModSwVerGet() {
        log("onPkHookModSwVerGet");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(19);
        return hookBytes;
    }

    public boolean onHookNvOpt(int sub, int nvId, int hook_id) {
        return onHookSend(hook_id, onPkHookNvOpt(sub, nvId, hook_id));
    }

    public ByteBuffer onHookNvOptSync(int sub, int nvId, int hook_id) {
        return onHookSendSync(onPkHookNvOpt(sub, nvId, hook_id));
    }

    private byte[] onPkHookNvOpt(int sub, int nvId, int hook_id) {
        log("onPkHookNvOpt, sub = " + sub + ", nvId = " + nvId + ", hook_id = " + hook_id);
        if (nvId < 0) {
            log("nvId < 0");
            return null;
        }
        if (sub != 0 && 1 != sub) {
            log("sub is invalid");
            return null;
        }
        if (1 != hook_id && 3 != hook_id) {
            log("hook_id is invalid");
            return null;
        }
        byte[] hookBytes = onGetHookMsgBytes(12);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(hook_id);
        reqBuffer.putInt(sub);
        reqBuffer.putInt(nvId);
        return hookBytes;
    }

    public boolean onHookNvRead(int sub, int nvId) {
        return onHookSend(1, onPkHookNvOpt(sub, nvId, 1));
    }

    public ByteBuffer nvReadByteBuffer(int sub, int nvId) {
        try {
            return onHookNvReadSync(sub, nvId);
        } catch (Exception e) {
            log("nvReadByteBuffer failed, Exception:" + e.toString());
            return null;
        }
    }

    public byte[] nvReadBytes(int sub, int nvId) {
        try {
            return onHookNvReadSyncEx(sub, nvId);
        } catch (Exception e) {
            log("nvReadBytes failed, Exception:" + e.toString());
            return null;
        }
    }

    public ByteBuffer onHookNvReadSync(int sub, int nvId) {
        return onHookSendSync(onPkHookNvOpt(sub, nvId, 1));
    }

    public byte[] onHookNvReadSyncEx(int sub, int nvId) {
        ByteBuffer buf = onHookNvReadSync(sub, nvId);
        if (buf == null) {
            log("onHookNvReadSyncEx fail, buf is null");
            return null;
        }
        int ret = buf.getInt();
        int len = buf.getInt();
        if (ret != 0) {
            log("onHookNvReadSyncEx fail, ret = " + ret);
            return null;
        }
        if (len <= 0) {
            log("onHookNvReadSyncEx fail, len = " + len);
            return null;
        }
        log("onHookNvReadSyncEx, ret = " + ret + ", len = " + len);
        byte[] bytes = new byte[len];
        buf.get(bytes);
        return bytes;
    }

    public boolean onHookNvDelete(int sub, int nvId) {
        return onHookSend(3, onPkHookNvOpt(sub, nvId, 3));
    }

    public ByteBuffer onHookNvDeleteSync(int sub, int nvId) {
        return onHookSendSync(onPkHookNvOpt(sub, nvId, 3));
    }

    public boolean nvDelete(int sub, int nvId) {
        try {
            return onHookNvDeleteSyncEx(sub, nvId);
        } catch (Exception e) {
            log("nvDelete failed, Exception:" + e.toString());
            return false;
        }
    }

    public boolean onHookNvDeleteSyncEx(int sub, int nvId) {
        ByteBuffer buf = onHookNvDeleteSync(sub, nvId);
        if (buf == null) {
            log("onHookNvDeleteSyncEx fail, buf is null");
            return false;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("onHookNvDeleteSyncEx fail, ret = " + ret);
            return false;
        }
        return true;
    }

    public boolean onHookNvWrite(int sub, int nvId, byte[] data) {
        if (data == null) {
            log("onPkHookNvWrite, sub = " + sub + ", nvId = " + nvId + ", data is null");
            return false;
        }
        return onHookNvWrite(sub, nvId, data, data.length);
    }

    public ByteBuffer onHookNvWriteSync(int sub, int nvId, byte[] data) {
        if (data == null) {
            log("onHookNvWriteSync, sub = " + sub + ", nvId = " + nvId + ", data is null");
            return null;
        }
        return onHookNvWriteSync(sub, nvId, data, data.length);
    }

    public boolean onHookNvWrite(int sub, int nvId, byte[] data, int dataLen) {
        return onHookSend(2, onPkHookNvWrite(sub, nvId, data, dataLen));
    }

    public ByteBuffer onHookNvWriteSync(int sub, int nvId, byte[] data, int dataLen) {
        return onHookSendSync(onPkHookNvWrite(sub, nvId, data, dataLen));
    }

    public boolean nvWrite(int sub, int nvId, byte[] data, int dataLen) {
        try {
            return onHookNvWriteSyncEx(sub, nvId, data, dataLen);
        } catch (Exception e) {
            log("nvWrite failed, Exception:" + e.toString());
            return false;
        }
    }

    public boolean onHookNvWriteSyncEx(int sub, int nvId, byte[] data, int dataLen) {
        ByteBuffer buf = onHookNvWriteSync(sub, nvId, data, dataLen);
        if (buf == null) {
            log("onHookNvWriteSyncEx fail, buf is null");
            return false;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("onHookNvWriteSyncEx fail, ret = " + ret);
            return false;
        }
        return true;
    }

    public boolean onHookNvWriteSyncEx(int sub, int nvId, byte[] data) {
        if (data == null) {
            log("onHookNvWriteSync, sub = " + sub + ", nvId = " + nvId + ", data is null");
            return false;
        }
        return onHookNvWriteSyncEx(sub, nvId, data, data.length);
    }

    private byte[] onPkHookNvWrite(int sub, int nvId, byte[] data, int dataLen) {
        log("onPkHookNvWrite, sub = " + sub + ", nvId = " + nvId + ", dataLen = " + dataLen);
        if (nvId < 0) {
            log("nvId < 0");
            return null;
        }
        if (sub != 0 && 1 != sub) {
            log("sub is invalid");
            return null;
        }
        if (data == null) {
            log("data is null");
            return null;
        }
        if (dataLen <= 0) {
            log("dataLen <= 0");
            return null;
        }
        int msg_len = dataLen + 16;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(2);
        reqBuffer.putInt(sub);
        reqBuffer.putInt(nvId);
        reqBuffer.putInt(dataLen);
        putByteToBuffer(reqBuffer, data, dataLen);
        return hookBytes;
    }

    public boolean onHookEfsOpt(int sub, String path, int hook_id) {
        return onHookSend(hook_id, onPkHookEfsOpt(sub, path, hook_id));
    }

    public ByteBuffer onHookEfsOptSync(int sub, String path, int hook_id) {
        return onHookSendSync(onPkHookEfsOpt(sub, path, hook_id));
    }

    private byte[] onPkHookEfsOpt(int sub, String path, int hook_id) {
        log("onPkHookEfsOpt, hook_id = " + hook_id + ", sub = " + sub + ", path = " + path);
        if (path == null) {
            log("path is null");
            return null;
        }
        if (4 != hook_id && 6 != hook_id && 110 != hook_id && 51 != hook_id) {
            log("hook_id is invalid");
            return null;
        }
        int msg_len = path.length() + 12;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(hook_id);
        reqBuffer.putInt(sub);
        reqBuffer.putInt(path.length());
        reqBuffer.put(path.getBytes());
        return hookBytes;
    }

    public boolean onHookEfsRead(int sub, String path) {
        return onHookSend(4, onPkHookEfsOpt(sub, path, 4));
    }

    public ByteBuffer onHookEfsReadSync(int sub, String path) {
        return onHookSendSync(onPkHookEfsOpt(sub, path, 4));
    }

    public ByteBuffer onHookBigEfsReadSync(int sub, String path, int responseSize) {
        return onHookSendSync(onPkHookEfsOpt(sub, path, 4), responseSize, 0);
    }

    public ByteBuffer onHookBigEfsReadSync(int sub, String path) {
        return onHookSendSync(onPkHookEfsOpt(sub, path, 4), 102400, 0);
    }

    public ByteBuffer efsReadByteBuffer(int sub, String path) {
        try {
            return onHookEfsReadSync(sub, path);
        } catch (Exception e) {
            log("efsReadByteBuffer failed, Exception:" + e.toString());
            return null;
        }
    }

    public ByteBuffer bigEfsReadByteBuffer(int sub, String path, int responseSize) {
        try {
            return onHookBigEfsReadSync(sub, path, responseSize);
        } catch (Exception e) {
            log("bigEfsReadByteBuffer failed, Exception:" + e.toString());
            return null;
        }
    }

    public ByteBuffer bigEfsReadByteBuffer1024(int sub, String path) {
        return bigEfsReadByteBuffer(sub, path, 102400);
    }

    public byte[] onHookEfsReadSyncEx(int sub, String path) {
        ByteBuffer buf = onHookEfsReadSync(sub, path);
        if (buf == null) {
            log("onHookEfsReadSyncEx fail, buf is null");
            return null;
        }
        int ret = buf.getInt();
        int len = buf.getInt();
        if (ret != 0) {
            log("onHookEfsReadSyncEx fail, ret = " + ret);
            return null;
        }
        if (len <= 0) {
            log("onHookEfsReadSyncEx fail, len = " + len);
            return null;
        }
        log("onHookEfsReadSyncEx, ret = " + ret + ", len = " + len);
        byte[] bytes = new byte[len];
        buf.get(bytes);
        return bytes;
    }

    public byte[] efsReadBytes(int sub, String path) {
        try {
            return onHookEfsReadSyncEx(sub, path);
        } catch (Exception e) {
            log("efsReadBytes failed, Exception:" + e.toString());
            return null;
        }
    }

    public byte[] onHookBigEfsReadSyncEx(int sub, String path, int responseSize) {
        ByteBuffer buf = onHookBigEfsReadSync(sub, path, responseSize);
        if (buf == null) {
            log("onHookBigEfsReadSyncEx fail, buf is null");
            return null;
        }
        int ret = buf.getInt();
        int len = buf.getInt();
        if (ret != 0) {
            log("onHookBigEfsReadSyncEx fail, ret = " + ret);
            return null;
        }
        if (len <= 0) {
            log("onHookBigEfsReadSyncEx fail, len = " + len);
            return null;
        }
        log("onHookBigEfsReadSyncEx, ret = " + ret + ", len = " + len + ", responseSize = " + responseSize);
        byte[] bytes = new byte[len];
        buf.get(bytes);
        return bytes;
    }

    public byte[] bigEfsReadBytes(int sub, String path, int responseSize) {
        try {
            return onHookBigEfsReadSyncEx(sub, path, responseSize);
        } catch (Exception e) {
            log("bigEfsReadBytes failed, Exception:" + e.toString());
            return null;
        }
    }

    public byte[] bigEfsReadBytes1024(int sub, String path) {
        return bigEfsReadBytes(sub, path, 102400);
    }

    public boolean onHookEfsDelete(int sub, String path) {
        return onHookSend(6, onPkHookEfsOpt(sub, path, 6));
    }

    public ByteBuffer onHookEfsDeleteSync(int sub, String path) {
        return onHookSendSync(onPkHookEfsOpt(sub, path, 6));
    }

    public boolean efsDelete(int sub, String path) {
        ByteBuffer buf = onHookEfsDeleteSync(sub, path);
        if (buf == null) {
            log("onHookEfsDeleteSyncEx fail, buf is null");
            return false;
        }
        try {
            int ret = buf.getInt();
            if (ret != 0) {
                log("onHookEfsDeleteSyncEx fail, ret = " + ret);
                return false;
            }
            return true;
        } catch (Exception e) {
            log("efsDelete failed, Exception:" + e.toString());
            return false;
        }
    }

    public boolean onHookEfsExist(int sub, String path) {
        return onHookSend(EVENT_OEMHOOK_EFS_EXIST, onPkHookEfsOpt(sub, path, 6));
    }

    public ByteBuffer onHookEfsExistSync(int sub, String path) {
        return onHookSendSync(onPkHookEfsOpt(sub, path, EVENT_OEMHOOK_EFS_EXIST));
    }

    public boolean efsExist(int sub, String path) {
        ByteBuffer buf = onHookEfsExistSync(sub, path);
        if (buf == null) {
            log("efsExist fail, buf is null");
            return false;
        }
        try {
            int ret = buf.getInt();
            if (ret != 0) {
                log("efsExist fail, ret = " + ret);
                return false;
            }
            return true;
        } catch (Exception e) {
            log("efsExist failed, Exception:" + e.toString());
            return false;
        }
    }

    public boolean efsSync() {
        ByteBuffer buf = onHookCommonMsgSync(35, 0);
        if (buf == null) {
            log("efsSync fail, buf is null");
            return false;
        }
        try {
            int ret = buf.getInt();
            if (ret != 0) {
                log("efsSync fail, ret = " + ret);
                return false;
            }
            return true;
        } catch (Exception e) {
            log("efsSync failed, Exception:" + e.toString());
            return false;
        }
    }

    public boolean onHookEfsCopy(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        return onHookSend(EVENT_OEMHOOK_EFS_COPY, onPkHookEfsCopy(subSrc, pathSrc, subTgt, pathTgt));
    }

    public ByteBuffer onHookEfsCopySync(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        return onHookSendSync(onPkHookEfsCopy(subSrc, pathSrc, subTgt, pathTgt));
    }

    public boolean efsCopy(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        ByteBuffer buf = onHookEfsCopySync(subSrc, pathSrc, subTgt, pathTgt);
        if (buf == null) {
            log("efsCopy fail, buf is null");
            return false;
        }
        try {
            int ret = buf.getInt();
            if (ret != 0) {
                log("efsCopy fail, ret = " + ret);
                return false;
            }
            return true;
        } catch (Exception e) {
            log("efsCopy failed, Exception:" + e.toString());
            return false;
        }
    }

    private byte[] onPkHookEfsCopy(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        log("onPkHookEfsCopy, subSrc = " + subSrc + ", pathSrc = " + pathSrc + ", subTgt = " + subTgt + ", pathTgt = " + pathTgt);
        if (pathSrc == null) {
            log("pathSrc is null");
            return null;
        }
        if (pathTgt == null) {
            log("pathTgt is null");
            return null;
        }
        int msg_len = pathSrc.length() + 12 + 4 + 4 + pathTgt.length();
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(EVENT_OEMHOOK_EFS_COPY);
        reqBuffer.putInt(subSrc);
        reqBuffer.putInt(pathSrc.length());
        reqBuffer.put(pathSrc.getBytes());
        reqBuffer.putInt(subTgt);
        reqBuffer.putInt(pathTgt.length());
        reqBuffer.put(pathTgt.getBytes());
        return hookBytes;
    }

    public boolean onHookEfsCompare(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        return onHookSend(EVENT_OEMHOOK_EFS_COPY, onPkHookEfsCompare(subSrc, pathSrc, subTgt, pathTgt));
    }

    public ByteBuffer onHookEfsCompareSync(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        return onHookSendSync(onPkHookEfsCompare(subSrc, pathSrc, subTgt, pathTgt));
    }

    public boolean efsCompare(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        ByteBuffer buf = onHookEfsCompareSync(subSrc, pathSrc, subTgt, pathTgt);
        if (buf == null) {
            log("efsCompare fail, buf is null");
            return false;
        }
        try {
            int ret = buf.getInt();
            if (ret != 0) {
                log("efsCompare fail, ret = " + ret);
                return false;
            }
            return true;
        } catch (Exception e) {
            log("efsCompare failed, Exception:" + e.toString());
            return false;
        }
    }

    private byte[] onPkHookEfsCompare(int subSrc, String pathSrc, int subTgt, String pathTgt) {
        log("onPkHookEfsCompare, subSrc = " + subSrc + ", pathSrc = " + pathSrc + ", subTgt = " + subTgt + ", pathTgt = " + pathTgt);
        if (pathSrc == null) {
            log("pathSrc is null");
            return null;
        }
        if (pathTgt == null) {
            log("pathTgt is null");
            return null;
        }
        int msg_len = pathSrc.length() + 12 + 4 + 4 + pathTgt.length();
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(EVENT_OEMHOOK_EFS_COPY);
        reqBuffer.putInt(subSrc);
        reqBuffer.putInt(pathSrc.length());
        reqBuffer.put(pathSrc.getBytes());
        reqBuffer.putInt(subTgt);
        reqBuffer.putInt(pathTgt.length());
        reqBuffer.put(pathTgt.getBytes());
        return hookBytes;
    }

    public boolean onHookEfsStats(int sub, String path) {
        return onHookSend(51, onPkHookEfsOpt(sub, path, 51));
    }

    public ByteBuffer onHookEfsStatsSync(int sub, String path) {
        return onHookSendSync(onPkHookEfsOpt(sub, path, 51));
    }

    public boolean onHookEfsWriteSyncEx(int sub, String path, byte[] data) {
        if (data == null) {
            log("onHookEfsWriteSyncEx, sub = " + sub + ", path = " + path + ", data is null");
            return false;
        }
        ByteBuffer buf = onHookEfsWriteSync(sub, path, data, data.length);
        if (buf == null) {
            log("onHookEfsWriteSyncEx fail, buf is null");
            return false;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("onHookEfsWriteSyncEx fail, ret = " + ret);
            return false;
        }
        return true;
    }

    public boolean onHookEfsWriteSyncEx(int sub, String path, byte[] data, int rildId) {
        if (data == null) {
            log("onHookEfsWriteSyncEx fail, data is null, sub = " + sub + ", path = " + path + ", rildId = " + rildId);
            return false;
        }
        if (rildId == 0 || 1 == rildId) {
            log("onHookEfsWriteSyncEx, sub = " + sub + ", path = " + path + ", rildId = " + rildId);
            ByteBuffer buf = onHookSendSync(onPkHookEfsWrite(sub, path, data, data.length), rildId);
            if (buf == null) {
                log("onHookEfsWriteSyncEx fail, buf is null");
                return false;
            }
            int ret = buf.getInt();
            if (ret == 0) {
                return true;
            }
            log("onHookEfsWriteSyncEx fail, ret is invalid, ret = " + ret);
            return false;
        }
        log("onHookEfsWriteSyncEx fail, rildId is invalid, sub = " + sub + ", path = " + path + ", rildId = " + rildId);
        return false;
    }

    public boolean onHookEfsWrite(int sub, String path, byte[] data) {
        if (data == null) {
            log("onHookEfsWrite, sub = " + sub + ", path = " + path + ", data is null");
            return false;
        }
        return onHookEfsWrite(sub, path, data, data.length);
    }

    public ByteBuffer onHookEfsWriteSync(int sub, String path, byte[] data) {
        if (data == null) {
            log("onHookEfsWriteSync, sub = " + sub + ", path = " + path + ", data is null");
            return null;
        }
        return onHookEfsWriteSync(sub, path, data, data.length);
    }

    public boolean onHookEfsWrite(int sub, String path, byte[] data, int dataLen) {
        return onHookSend(5, onPkHookEfsWrite(sub, path, data, dataLen));
    }

    public ByteBuffer onHookEfsWriteSync(int sub, String path, byte[] data, int dataLen) {
        return onHookSendSync(onPkHookEfsWrite(sub, path, data, dataLen));
    }

    public boolean efsWrite(int sub, String path, byte[] data) {
        ByteBuffer buf = onHookEfsWriteSync(sub, path, data);
        if (buf == null) {
            log("efsWrite, sub = " + sub + ", path = " + path + ", buf is null");
            return false;
        }
        try {
            int ret = buf.getInt();
            if (ret != 0) {
                log("efsWrite, sub = " + sub + ", path = " + path + ", ret = " + ret + ", ret is invalid");
                return false;
            }
            return true;
        } catch (Exception e) {
            log("efsWrite failed, Exception:" + e.toString());
            return false;
        }
    }

    private byte[] onPkHookEfsWrite(int sub, String path, byte[] data, int dataLen) {
        log("onPkHookEfsWrite, sub = " + sub + ", path = " + path + ", dataLen = " + dataLen);
        if (sub != 0 && 1 != sub) {
            log("mSubId is invalid");
            return null;
        }
        if (path == null) {
            log("path is null");
            return null;
        }
        if (data == null) {
            log("data is null");
            return null;
        }
        if (dataLen <= 0) {
            log("dataLen <= 0");
            return null;
        }
        int msg_len = path.length() + 12 + 4 + dataLen;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(5);
        reqBuffer.putInt(sub);
        reqBuffer.putInt(path.length());
        reqBuffer.put(path.getBytes());
        reqBuffer.putInt(dataLen);
        putByteToBuffer(reqBuffer, data, dataLen);
        return hookBytes;
    }

    private List<String> getLwotaPlmnList(String plmns) {
        if (plmns == null) {
            log("getLwotaPlmnList - nullptr for plmns");
            return null;
        }
        List<String> mccmncList = new ArrayList<>();
        String[] plmnArray = plmns.replace(" ", "").split(",");
        for (String plmn : plmnArray) {
            String[] mccmnc = plmn.split("-");
            if (mccmnc.length == 2) {
                for (String s : mccmnc) {
                    mccmncList.add(s);
                }
            }
        }
        return mccmncList;
    }

    private ByteBuffer onHookNWLwota(long dataId, int msgID, String plmns, int nvID, String data, String otaSeqSum, String otaSeqIdx, String shaCheck, String data_sep) {
        log("onHookNWLwota, dataId = " + dataId + ", msgID = " + msgID + ", plmns = " + plmns + ", nvID = " + nvID + ", data = " + data + ", shaCheck = " + shaCheck);
        int msg_len = 4 + 4;
        int msg_len2 = msg_len + 4;
        List<String> mccmncList = getLwotaPlmnList(plmns);
        Iterator<String> it = mccmncList.iterator();
        while (it.hasNext()) {
            msg_len2 = msg_len2 + 1 + it.next().getBytes().length;
        }
        int msg_len3 = msg_len2 + 4 + 4 + 4 + 4 + shaCheck.getBytes().length + 4 + data.getBytes().length;
        if (123 == msgID) {
            msg_len3 = msg_len3 + 4 + data_sep.getBytes().length;
        }
        byte[] hookBytes = onGetHookMsgBytes(msg_len3);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msgID);
        reqBuffer.putInt((int) dataId);
        reqBuffer.putInt(mccmncList.size() / 2);
        for (String s : mccmncList) {
            reqBuffer.put((byte) s.getBytes().length);
            reqBuffer.put(s.getBytes());
        }
        reqBuffer.putInt(Integer.parseInt(otaSeqSum));
        reqBuffer.putInt(Integer.parseInt(otaSeqIdx));
        reqBuffer.putInt(nvID);
        reqBuffer.putInt(shaCheck.getBytes().length);
        reqBuffer.put(shaCheck.getBytes());
        reqBuffer.putInt(data.getBytes().length);
        reqBuffer.put(data.getBytes());
        if (123 == msgID) {
            reqBuffer.putInt(data_sep.getBytes().length);
            reqBuffer.put(data_sep.getBytes());
        }
        ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
        if (retSub0 == null) {
            log("onHookNWLwota - Sub0 failed to send hook message");
            return null;
        }
        return retSub0;
    }

    private ByteBuffer onHookEWLwota(long dataId, int msgID, String plmns, String target, String data, String otaSeqSum, String otaSeqIdx, String efsSeqSum, String efsSeqIdx, String shaCheck) {
        log("onHookEWLwota, dataId = " + dataId + ", msgID = " + msgID + ", plmns = " + plmns + ", target = " + target + ", data = " + data + ", efsSeqSum = " + efsSeqSum + ", efsSeqIdx = " + efsSeqIdx + ", shaCheck = " + shaCheck);
        int msg_len = 4 + 4;
        int msg_len2 = msg_len + 4;
        List<String> mccmncList = getLwotaPlmnList(plmns);
        Iterator<String> it = mccmncList.iterator();
        while (it.hasNext()) {
            msg_len2 = msg_len2 + 1 + it.next().getBytes().length;
        }
        byte[] hookBytes = onGetHookMsgBytes(msg_len2 + 4 + 4 + 4 + 4 + 4 + shaCheck.getBytes().length + 4 + target.getBytes().length + 4 + data.getBytes().length);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msgID);
        int msg_id = (int) dataId;
        reqBuffer.putInt(msg_id);
        reqBuffer.putInt(mccmncList.size() / 2);
        for (Iterator<String> it2 = mccmncList.iterator(); it2.hasNext(); it2 = it2) {
            String s = it2.next();
            reqBuffer.put((byte) s.getBytes().length);
            reqBuffer.put(s.getBytes());
        }
        reqBuffer.putInt(Integer.parseInt(otaSeqSum));
        reqBuffer.putInt(Integer.parseInt(otaSeqIdx));
        reqBuffer.putInt(Integer.parseInt(efsSeqSum));
        reqBuffer.putInt(Integer.parseInt(efsSeqIdx));
        reqBuffer.putInt(shaCheck.getBytes().length);
        reqBuffer.put(shaCheck.getBytes());
        reqBuffer.putInt(target.getBytes().length);
        reqBuffer.put(target.getBytes());
        reqBuffer.putInt(data.getBytes().length);
        reqBuffer.put(data.getBytes());
        ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
        if (retSub0 == null) {
            log("onHookEWLwota - Sub0 failed to send hook message");
            return null;
        }
        return retSub0;
    }

    private ByteBuffer onHookEDLwota(long dataId, int msgID, String plmns, String target, String otaSeqSum, String otaSeqIdx) {
        log("onHookEDLwota, dataId = " + dataId + ", msgID = " + msgID + ", plmns = " + plmns + ", target = " + target);
        int msg_len = 4 + 4;
        int msg_len2 = msg_len + 4;
        List<String> mccmncList = getLwotaPlmnList(plmns);
        Iterator<String> it = mccmncList.iterator();
        while (it.hasNext()) {
            msg_len2 = msg_len2 + 1 + it.next().getBytes().length;
        }
        byte[] hookBytes = onGetHookMsgBytes(msg_len2 + 4 + 4 + 4 + target.getBytes().length);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msgID);
        reqBuffer.putInt((int) dataId);
        reqBuffer.putInt(mccmncList.size() / 2);
        for (String s : mccmncList) {
            reqBuffer.put((byte) s.getBytes().length);
            reqBuffer.put(s.getBytes());
        }
        reqBuffer.putInt(Integer.parseInt(otaSeqSum));
        reqBuffer.putInt(Integer.parseInt(otaSeqIdx));
        reqBuffer.putInt(target.getBytes().length);
        reqBuffer.put(target.getBytes());
        ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
        if (retSub0 == null) {
            log("onHookEDLwota - Sub0 failed to send hook message");
            return null;
        }
        return retSub0;
    }

    private ByteBuffer onHookMDULwota(long dataId, int msgID, String target, String data, String otaSeqSum, String otaSeqIdx, String shaCheck) {
        log("onHookMDULwota, msg id = " + msgID + ", target = " + target + ", data = " + data + ", otaSeqSum = " + otaSeqSum + ", otaSeqIdx = " + otaSeqIdx + ", shaCheck = " + shaCheck);
        int msg_len = 8 + 4;
        byte[] hookBytes = onGetHookMsgBytes(msg_len + 4 + target.getBytes().length + 4 + shaCheck.getBytes().length + 4 + data.getBytes().length);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(msgID);
        reqBuffer.putInt(Integer.parseInt(otaSeqSum));
        reqBuffer.putInt(Integer.parseInt(otaSeqIdx));
        reqBuffer.putInt(target.getBytes().length);
        reqBuffer.put(target.getBytes());
        reqBuffer.putInt(shaCheck.getBytes().length);
        reqBuffer.put(shaCheck.getBytes());
        reqBuffer.putInt(data.getBytes().length);
        reqBuffer.put(data.getBytes());
        ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
        if (retSub0 == null) {
            log("onHookMDULwota - Sub0 failed to send hook message");
            return null;
        }
        return retSub0;
    }

    private ByteBuffer onHookResetLwota() {
        log("onHookResetLwota: reset all config for LWOTA");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(EVENT_OEMHOOK_XIAOMI_CLEAN_ALL_CD_LWOTA);
        ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
        if (retSub0 == null) {
            log("onHookResetLwota - Sub0 or Sub1 failed to send hook message");
            return null;
        }
        return retSub0;
    }

    public ByteBuffer onHookLWOTAUpdateSync(long dataId, int mode, String plmns, String target, String data, String otaSeqSum, String otaSeqIdx, String efsSeqSum, String efsSeqIdx, String shaCheck, String data_sep) {
        if (plmns == null || target == null || otaSeqSum == null || otaSeqIdx == null) {
            log("onHookLWOTAUpdateSync - nullptr for plmns/target/otaSeqSum/otaSeqIdx");
            return null;
        }
        if ((plmns.length() != 0 && target.length() != 0 && otaSeqSum.length() != 0 && otaSeqIdx.length() != 0) || mode == 7 || mode == 8) {
            if (mode != 0 && 1 != mode && 2 != mode && 3 != mode && 4 != mode && 5 != mode && 6 != mode && 7 != mode && 8 != mode && 9 != mode) {
                log("onHookLWOTAUpdateSync - invalid oprt mode:" + mode);
                return null;
            }
            if (mode != 4 && mode != 6 && mode != 8 && (data == null || data.length() == 0 || shaCheck == null || shaCheck.length() == 0)) {
                log("onHookLWOTAUpdateSync - nullptr for data/sha when writing");
                return null;
            }
            if ((2 == mode || 3 == mode || 5 == mode) && (efsSeqSum == null || efsSeqIdx == null || efsSeqSum.length() == 0 || efsSeqIdx.length() == 0)) {
                log("onHookLWOTAUpdateSync - nullptr or empty str for efsSeqSum/efsSeqIdx when process efs");
                return null;
            }
            switch (mode) {
                case 0:
                    return onHookNWLwota(dataId, EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_NW_LWOTA, plmns, Integer.parseInt(target), data, otaSeqSum, otaSeqIdx, shaCheck, data_sep);
                case 1:
                    return onHookNWLwota(dataId, EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_HNW_LWOTA, plmns, Integer.parseInt(target), data, otaSeqSum, otaSeqIdx, shaCheck, data_sep);
                case 2:
                    return onHookEWLwota(dataId, 124, plmns, target, data, otaSeqSum, otaSeqIdx, efsSeqSum, efsSeqIdx, shaCheck);
                case 3:
                    return onHookEWLwota(dataId, EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_HEW_LWOTA, plmns, target, data, otaSeqSum, otaSeqIdx, efsSeqSum, efsSeqIdx, shaCheck);
                case 4:
                    return onHookEDLwota(dataId, EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_ED_LWOTA, plmns, target, otaSeqSum, otaSeqIdx);
                case 5:
                    return onHookEWLwota(dataId, EVENT_OEMHOOK_XIAOMI_MD_LWOTA, plmns, target, data, otaSeqSum, otaSeqIdx, efsSeqSum, efsSeqIdx, shaCheck);
                case 6:
                    return onHookEDLwota(dataId, EVENT_OEMHOOK_XIAOMI_MR_LWOTA, plmns, target, otaSeqSum, otaSeqIdx);
                case 7:
                    return onHookMDULwota(dataId, EVENT_OEMHOOK_XIAOMI_MDU_LWOTA, target, data, otaSeqSum, otaSeqIdx, shaCheck);
                case 8:
                    return onHookResetLwota();
                case 9:
                    return onHookNWLwota(dataId, EVENT_OEMHOOK_XIAOMI_INTERNATIONAL_MNW_LWOTA, plmns, Integer.parseInt(target), data, otaSeqSum, otaSeqIdx, shaCheck, data_sep);
                default:
                    return null;
            }
        }
        log("onHookLWOTAUpdateSync - empty str for plmns/target/otaSeqSum/otaSeqIdx");
        return null;
    }

    public ByteBuffer onHookLWOTAShaSync(List<JSONObject> lwotaShaList) {
        String str;
        String impact_id = "onHookLWOTAShaSync - Sub0 failed to send hook message";
        if (lwotaShaList == null || lwotaShaList.size() == 0) {
            return null;
        }
        log("onHookLWOTAShaSync - start sha sync, shaList = " + lwotaShaList);
        try {
            int count = lwotaShaList.size();
            int msg_len = 0 + 4;
            int msg_len2 = msg_len + 4;
            for (JSONObject shaObject : lwotaShaList) {
                int msg_len3 = msg_len2 + 4 + 4;
                String plmns = shaObject.optString(LWOTA_PLMN);
                Iterator<String> it = getLwotaPlmnList(plmns).iterator();
                while (it.hasNext()) {
                    msg_len3 = msg_len3 + 1 + it.next().getBytes().length;
                }
                int msg_len4 = msg_len3 + 4 + shaObject.optString(LWOTA_SHA_CHECK).getBytes().length + 4 + shaObject.optString(LWOTA_DESCRIPTION).getBytes().length + 4;
                String impact_id2 = shaObject.optString(LWOTA_IMPACT_ID).replace(" ", "");
                msg_len2 = msg_len4 + impact_id2.getBytes().length;
            }
            byte[] hookBytes = onGetHookMsgBytes(msg_len2);
            ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
            if (reqBuffer == null) {
                log("reqBuffer is null, will not send this hook msg");
                return null;
            }
            reqBuffer.putInt(EVENT_OEMHOOK_XIAOMI_ID_LWOTA);
            reqBuffer.putInt(count);
            for (JSONObject shaObject2 : lwotaShaList) {
                long data_id = Long.parseLong(shaObject2.optString(LWOTA_DATA_ID));
                int msg_len5 = msg_len2;
                str = impact_id;
                int msg_len6 = (int) data_id;
                try {
                    reqBuffer.putInt(msg_len6);
                    String plmns2 = shaObject2.optString(LWOTA_PLMN);
                    List<String> mccmncList = getLwotaPlmnList(plmns2);
                    reqBuffer.putInt(mccmncList.size() / 2);
                    for (Iterator<String> it2 = mccmncList.iterator(); it2.hasNext(); it2 = it2) {
                        String s = it2.next();
                        reqBuffer.put((byte) s.getBytes().length);
                        reqBuffer.put(s.getBytes());
                    }
                    String shaCheck = shaObject2.optString(LWOTA_SHA_CHECK);
                    reqBuffer.putInt(shaCheck.getBytes().length);
                    reqBuffer.put(shaCheck.getBytes());
                    String description = shaObject2.optString(LWOTA_DESCRIPTION);
                    reqBuffer.putInt(description.getBytes().length);
                    reqBuffer.put(description.getBytes());
                    String impact_id3 = shaObject2.optString(LWOTA_IMPACT_ID).replace(" ", "");
                    reqBuffer.putInt(impact_id3.getBytes().length);
                    reqBuffer.put(impact_id3.getBytes());
                    impact_id = str;
                    msg_len2 = msg_len5;
                } catch (Exception e) {
                    log(str);
                    return null;
                }
            }
            str = impact_id;
            ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
            if (retSub0 == null) {
                log(str);
                return null;
            }
            return retSub0;
        } catch (Exception e2) {
            str = "onHookLWOTAShaSync - Sub0 failed to send hook message";
        }
    }

    public ByteBuffer onHookLWOTAUpgradeSync(int sub_id) {
        log("onHookLWOTAUpgradeSync, msg id = " + EVENT_OEMHOOK_XIAOMI_UR_LWOTA + ", sub_id = " + sub_id);
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("onHookLWOTAUpgradeSync - reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(EVENT_OEMHOOK_XIAOMI_UR_LWOTA);
        if (sub_id == 0) {
            ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
            if (retSub0 == null) {
                log("onHookLWOTAUpgradeSync - Sub0 failed to send hook message");
                return null;
            }
            return retSub0;
        }
        if (1 == sub_id) {
            ByteBuffer retSub1 = onHookSendSync(hookBytes, 1);
            if (retSub1 == null) {
                log("onHookLWOTAUpgradeSync - Sub1 failed to send hook message");
                return null;
            }
            return retSub1;
        }
        if (2 == sub_id) {
            ByteBuffer retSub2 = onHookSendSync(hookBytes, 0);
            ByteBuffer retSub3 = onHookSendSync(hookBytes, 1);
            if (retSub2 == null || retSub3 == null) {
                log("onHookLWOTAUpgradeSync - Sub0 or Sub1 failed to send hook message");
                return null;
            }
            return retSub2;
        }
        log("onHookLWOTAUpgradeSync - Sub Id is error");
        return null;
    }

    public ByteBuffer onHookLWOTAGetVersionInfo() {
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("onHookLWOTAGetVersionInfo - reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(EVENT_OEMHOOK_XIAOMI_CLOULD_VR_LWOTA);
        ByteBuffer retSub0 = onHookSendSync(hookBytes, 0);
        if (retSub0 == null) {
            log("onHookLWOTAGetVersionInfo - Sub0 failed to send hook message");
            return null;
        }
        return retSub0;
    }

    public boolean openEvs() {
        return oHookEvsOpt((byte) 1);
    }

    public boolean closeEvs() {
        return oHookEvsOpt((byte) -1);
    }

    public boolean evsIsOpen() {
        return oHookEvsIsOpen();
    }

    private boolean oHookEvsOpt(byte flag) {
        try {
            ByteBuffer nv73846 = onHookEfsOptSync(0, "/nv/item_files/ims/IMSCodecDynamicConfig", 4);
            if (nv73846 == null) {
                log("oHookEvsOpt(" + ((int) flag) + "), can not read efs for sub0");
                return false;
            }
            int ret = nv73846.getInt();
            int len = nv73846.getInt();
            log("oHookEvsOpt(" + ((int) flag) + "), success to read efs for sub0, len = " + len + ", ret = " + ret);
            if (1024 != len) {
                log("oHookEvsOpt(" + ((int) flag) + "), len is invalid, can not read efs for sub0");
                return false;
            }
            byte[] bytes = new byte[IResultValue.MISYS_UNKNOWN];
            nv73846.get(bytes);
            log("oHookEvsOpt sub0, old evs_prim_mode = " + ((int) bytes[26]));
            bytes[26] = flag;
            if (onHookEfsWriteSync(0, "/nv/item_files/ims/IMSCodecDynamicConfig", bytes) != null) {
                ByteBuffer nv73847 = onHookEfsOptSync(1, "/nv/item_files/ims/IMSCodecDynamicConfig", 4);
                if (nv73847 == null) {
                    log("oHookEvsOpt(" + ((int) flag) + "), can not read efs for sub1");
                    return false;
                }
                int ret2 = nv73847.getInt();
                int len2 = nv73847.getInt();
                log("oHookEvsOpt(" + ((int) flag) + "), success to read efs for sub1, len = " + len2 + ", ret = " + ret2);
                if (ret2 != 0) {
                    log("oHookEvsOpt(" + ((int) flag) + "), ret is invalid, can not read efs for sub1");
                    return false;
                }
                if (1024 != len2) {
                    log("oHookEvsOpt(" + ((int) flag) + "), len is invalid, can not read efs for sub1");
                    return false;
                }
                nv73847.get(bytes);
                log("oHookEvsOpt sub1, old evs_prim_mode = " + ((int) bytes[26]));
                bytes[26] = flag;
                if (onHookEfsWriteSync(1, "/nv/item_files/ims/IMSCodecDynamicConfig", bytes) == null) {
                    log("oHookEvsOpt(" + ((int) flag) + "), can not write efs for sub1");
                    return false;
                }
                oHookEvsIsOpen();
                onHookCommonMsgSync(68, XIAOMI_SSR_FOR_HOOK_EVS_SWITCH);
                return true;
            }
            log("oHookEvsOpt(" + ((int) flag) + "), can not write efs for sub0");
            return false;
        } catch (Exception e) {
            log("oHookEvsOpt, Happen Exception, Exception info: " + e);
            return false;
        }
    }

    private boolean oHookEvsIsOpen() {
        try {
            ByteBuffer nv73846 = onHookEfsOptSync(0, "/nv/item_files/ims/IMSCodecDynamicConfig", 4);
            if (nv73846 == null) {
                log("oHookEvsIsOpen, can not read efs for sub0");
                return false;
            }
            int ret = nv73846.getInt();
            int len = nv73846.getInt();
            log("oHookEvsIsOpen, success to read efs for sub0, len = " + len + ", ret = " + ret);
            if (1024 != len) {
                log("oHookEvsIsOpen, len is invalid, can not read efs for sub0");
                return false;
            }
            byte[] bytes = new byte[IResultValue.MISYS_UNKNOWN];
            nv73846.get(bytes);
            int evs_prim_mode_sub0 = bytes[26];
            log("oHookEvsIsOpen sub0, evs_prim_mode_sub0 = " + evs_prim_mode_sub0);
            ByteBuffer nv73847 = onHookEfsOptSync(1, "/nv/item_files/ims/IMSCodecDynamicConfig", 4);
            if (nv73847 == null) {
                log("oHookEvsIsOpen, can not read efs for sub1");
                return false;
            }
            int ret2 = nv73847.getInt();
            int len2 = nv73847.getInt();
            log("oHookEvsIsOpen, success to read efs for sub1, len = " + len2 + ", ret = " + ret2);
            if (ret2 != 0) {
                log("oHookEvsIsOpen, ret is invalid, can not read efs for sub1");
                return false;
            }
            if (1024 != len2) {
                log("oHookEvsIsOpen, len is invalid, can not read efs for sub1");
                return false;
            }
            nv73847.get(bytes);
            int evs_prim_mode_sub1 = bytes[26];
            log("oHookEvsIsOpen sub1, evs_prim_mode_sub1 = " + evs_prim_mode_sub1);
            if (1 != evs_prim_mode_sub0 || 1 != evs_prim_mode_sub1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log("oHookEvsIsOpen, Happen Exception, Exception info: " + e);
            return false;
        }
    }

    public boolean onHookGetDeviceInfo() {
        return onHookSend(8, onPkHookGetDeviceInfo());
    }

    public ByteBuffer onHookGetDeviceInfoSync() {
        return onHookSendSync(onPkHookGetDeviceInfo());
    }

    private byte[] onPkHookGetDeviceInfo() {
        log("onPkHookGetDeviceInfo");
        byte[] hookBytes = onGetHookMsgBytes(0);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(8);
        reqBuffer.putInt(0);
        return hookBytes;
    }

    public boolean onHookGetDeviceData(int deviceDataBytesSize) {
        return onHookSend(32, onPkHookGetDeviceData(deviceDataBytesSize));
    }

    public ByteBuffer onHookGetDeviceDataSync(int deviceDataBytesSize) {
        return onHookSendSync(onPkHookGetDeviceData(deviceDataBytesSize));
    }

    private byte[] onPkHookGetDeviceData(int deviceDataBytesSize) {
        log("onPkHookGetDeviceData, deviceDataBytesSize = " + deviceDataBytesSize);
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(32);
        reqBuffer.putInt(4);
        reqBuffer.putInt(deviceDataBytesSize);
        return hookBytes;
    }

    public boolean onHookGetMipiInfo(int mipiValueEnumStrSize) {
        return onHookSend(9, onPkHookGetMipiInfo(mipiValueEnumStrSize));
    }

    public ByteBuffer onHookGetMipiInfoSync(int mipiValueEnumStrSize) {
        return onHookSendSync(onPkHookGetMipiInfo(mipiValueEnumStrSize));
    }

    private byte[] onPkHookGetMipiInfo(int mipiValueEnumStrSize) {
        log("onPkHookGetMipiInfo, mipiValueEnumStrSize = " + mipiValueEnumStrSize);
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(9);
        reqBuffer.putInt(4);
        reqBuffer.putInt(mipiValueEnumStrSize);
        return hookBytes;
    }

    public boolean qcRilSetDsiAsync(int value, int phoneId) {
        validateInternalState();
        byte[] payload = new byte[this.mHeaderSize + 8];
        ByteBuffer reqBuffer = createBufferWithNativeByteOrder(payload);
        OemHookCallback oemHookCb = new OemHookCallback(null) { // from class: com.xiaomi.mirilhook.MiRilHook.1
            public void onOemHookResponse(byte[] response, int phoneId2) throws RemoteException {
                MiRilHook.log("qcRilSetDsiAsync set DONE! ");
            }
        };
        log("sar DSI Set status req, DSI is " + value);
        addQcRilHookHeader(reqBuffer, DYNAMIC_SAR_REQ_NUM, 4);
        reqBuffer.putInt(value);
        reqBuffer.putInt(0);
        try {
            sendRilOemHookMsgAsync(DYNAMIC_SAR_REQ_NUM, payload, oemHookCb, phoneId);
            return true;
        } catch (Exception e) {
            log("qcRilSetDsiAsync DYNAMIC_SAR_REQ_NUM failed " + e);
            return false;
        }
    }

    public boolean onHookSetDeviceState(int gpioNum, byte[] gpioValue, int mipiNum, byte[] mipiValue) {
        return onHookSend(7, onPkHookSetDeviceState(gpioNum, gpioValue, mipiNum, mipiValue));
    }

    public ByteBuffer onHookSetDeviceStateSync(int gpioNum, byte[] gpioValue, int mipiNum, byte[] mipiValue) {
        return onHookSendSync(onPkHookSetDeviceState(gpioNum, gpioValue, mipiNum, mipiValue));
    }

    private byte[] onPkHookSetDeviceState(int gpioNum, byte[] gpioValue, int mipiNum, byte[] mipiValue) {
        log("onPkHookSetDeviceState, gpioNum = " + gpioNum + ", mipiNum = " + mipiNum);
        int msg_len = gpioNum + 8 + mipiNum;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(7);
        reqBuffer.putInt(gpioNum + mipiNum);
        putByteToBuffer(reqBuffer, gpioValue, gpioNum);
        putByteToBuffer(reqBuffer, mipiValue, mipiNum);
        return hookBytes;
    }

    public boolean onHookMiBroadcastStateSet(int bc_type, int bc_state) {
        return onHookMiBroadcastStateSet(bc_type, bc_state, 0);
    }

    public ByteBuffer onHookMiBroadcastStateSetSync(int bc_type, int bc_state) {
        return onHookMiBroadcastStateSetSync(bc_type, bc_state, 0);
    }

    public boolean onHookMiBroadcastStateSet(int bc_type, int bc_state, int phoneId) {
        return onHookSend(29, onHookPkMiBroadcastStateSet(bc_type, bc_state), phoneId);
    }

    public ByteBuffer onHookMiBroadcastStateSetSync(int bc_type, int bc_state, int phoneId) {
        return onHookSendSync(onHookPkMiBroadcastStateSet(bc_type, bc_state), phoneId);
    }

    private byte[] onHookPkMiBroadcastStateSet(int bc_type, int bc_state) {
        log("onHookPkMiBroadcastStateSet, bc_type = " + bc_type + ", bc_state = " + bc_state);
        byte[] hookBytes = onGetHookMsgBytes(12);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(29);
        reqBuffer.putInt(bc_type);
        reqBuffer.putInt(bc_state);
        return hookBytes;
    }

    public boolean onHookMiBroadcastStateGet(int bc_type) {
        return onHookSend(30, onPkHookMiBroadcastStateGet(bc_type));
    }

    public ByteBuffer onHookMiBroadcastStateGetSync(int bc_type) {
        return onHookSendSync(onPkHookMiBroadcastStateGet(bc_type));
    }

    private byte[] onPkHookMiBroadcastStateGet(int bc_type) {
        log("onPkHookMiBroadcastStateGet, bc_type = " + bc_type);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(30);
        reqBuffer.putInt(bc_type);
        return hookBytes;
    }

    public boolean onHookDsdaIsSupported() {
        return onHookSend(87, onPkHookDsdaIsSupported());
    }

    public boolean onHookDsdaIsSupportedSync() {
        ByteBuffer buf = onHookSendSync(onPkHookDsdaIsSupported());
        if (buf == null) {
            log("Maybe not support this hook interface");
            return false;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("hook return fail, ret = " + ret);
            return false;
        }
        return true;
    }

    private byte[] onPkHookDsdaIsSupported() {
        log("onPkHookDsdaIsSupported");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(87);
        return hookBytes;
    }

    public boolean onHookDsdaDefaultStatusGet() {
        return onHookSend(88, onPkHookDsdaDefaultStatusGet());
    }

    public boolean onHookDsdaDefaultStatusGetSync() {
        ByteBuffer buf = onHookSendSync(onPkHookDsdaDefaultStatusGet());
        if (buf == null) {
            log("Maybe not support this hook interface");
            return false;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("hook return fail, ret = " + ret);
            return false;
        }
        return true;
    }

    private byte[] onPkHookDsdaDefaultStatusGet() {
        log("onPkHookDsdaDefaultStatusGet");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(88);
        return hookBytes;
    }

    public boolean onHookDsdaCurrentStatusGet() {
        return onHookSend(89, onPkHookDsdaCurrentStatusGet());
    }

    public boolean onHookDsdaCurrentStatusGetSync() {
        ByteBuffer buf = onHookSendSync(onPkHookDsdaCurrentStatusGet());
        if (buf == null) {
            log("Maybe not support this hook interface");
            return false;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("hook return fail, ret = " + ret);
            return false;
        }
        return true;
    }

    private byte[] onPkHookDsdaCurrentStatusGet() {
        log("onPkHookDsdaCurrentStatusGet");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(89);
        return hookBytes;
    }

    public boolean isModemFeatureSupported(int featureType) {
        ByteBuffer buf = onHookSendSync(onPkHookIsModemFeatureSupported(featureType));
        if (buf == null) {
            log("isModemFeatureSupported, Maybe not support this hook interface");
            return false;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("isModemFeatureSupported, hook return fail, ret = " + ret);
            return false;
        }
        int t = buf.getInt();
        int l = buf.getInt();
        log("t = " + t + ", l = " + l);
        int featureState = buf.getInt();
        log("isModemFeatureSupported, featureType = " + featureType + ", featureState = " + featureState);
        if (featureState == 0) {
            return false;
        }
        return true;
    }

    private byte[] onPkHookIsModemFeatureSupported(int featureType) {
        log("onPkHookIsModemFeatureSupported, featureType = " + featureType);
        byte[] hookBytes = onGetHookMsgBytes(12);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log(getCurrentMethodName() + ", reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(102);
        reqBuffer.putInt(4);
        reqBuffer.putInt(featureType);
        return hookBytes;
    }

    public int getMbnFeatureDefaultConfig() {
        ByteBuffer buf = onHookSendSync(onPkHookCommonMsg(EVENT_OEMHOOK_MBN_FEATURE_DEFAULT_CONFIG_GET));
        if (buf == null) {
            log("getMbnFeatureDefaultConfig, Maybe not support this hook interface");
            return 0;
        }
        int ret = buf.getInt();
        log("getMbnFeatureDefaultConfig, ret = " + ret);
        return ret;
    }

    public int getMbnFeatureCurrentConfig() {
        ByteBuffer buf = onHookSendSync(onPkHookCommonMsg(EVENT_OEMHOOK_MBN_FEATURE_CURRENT_CONFIG_GET));
        if (buf == null) {
            log("getMbnFeatureCurrentConfig, Maybe not support this hook interface");
            return 0;
        }
        int ret = buf.getInt();
        log("getMbnFeatureCurrentConfig, ret = " + ret);
        return ret;
    }

    public boolean isOemProduct() {
        ByteBuffer buf = onHookCommonMsgSync(105);
        if (buf == null) {
            log("isOemProduct, Maybe not support this hook interface");
            return false;
        }
        int ret = buf.getInt();
        log("isOemProduct, ret = " + ret);
        if (ret != 0) {
            return false;
        }
        return true;
    }

    public boolean onHookModemChipNameIdGet() {
        return onHookSend(96, onPkHookModemChipNameIdGet());
    }

    public int onHookModemChipNameIdGetSync() {
        ByteBuffer buf = onHookSendSync(onPkHookModemChipNameIdGet());
        if (buf == null) {
            log("Maybe not support this hook interface");
            return 0;
        }
        int ret = buf.getInt();
        if (ret != 0) {
            log("hook return fail, ret = " + ret);
            return 0;
        }
        int chipNameId = buf.getInt();
        log("chipNameId = " + chipNameId);
        return chipNameId;
    }

    private byte[] onPkHookModemChipNameIdGet() {
        log("onPkHookModemChipNameIdGet");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(96);
        return hookBytes;
    }

    public boolean onHookNotifyScreenStatus(int status) {
        return onHookSend(27, onHookPkNotifyScreenStatus(status), 0);
    }

    public ByteBuffer onHookNotifyScreenStatusSync(int status) {
        return onHookSendSync(onHookPkNotifyScreenStatus(status), 0);
    }

    private byte[] onHookPkNotifyScreenStatus(int status) {
        log("onHookPkNotifyScreenStatus, status = " + status);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(27);
        reqBuffer.putInt(status);
        return hookBytes;
    }

    public ByteBuffer onHookHydraStatsGetSync(String packageName) {
        return onHookSendSync(packageName, onPkHookHydraStatsGet());
    }

    private byte[] onPkHookHydraStatsGet() {
        log("onPkHookHydraStatsGet");
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(10);
        return hookBytes;
    }

    public ByteBuffer onHookHydraStatsSetSync(String packageName, int swState) {
        return onHookSendSync(packageName, onPkHookHydraStatsSet(swState));
    }

    public ByteBuffer onHookHydraStatsSetSync(int swState) {
        return onHookSendSync(onPkHookHydraStatsSet(swState));
    }

    private byte[] onPkHookHydraStatsSet(int swState) {
        log("onPkHookHydraStatsSet, swState = " + swState);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(11);
        reqBuffer.putInt(swState);
        return hookBytes;
    }

    public ByteBuffer onHookModemHydraCheckSync() {
        return onHookSendSync(onPkHookModemHydraCheck());
    }

    private byte[] onPkHookModemHydraCheck() {
        log("onPkHookModemHydraCheck");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(94);
        return hookBytes;
    }

    public ByteBuffer onHookHydraDiagNotSupportInfoGetSync() {
        return onHookSendSync(onPkHookHydraDiagNotSupportInfoGet());
    }

    private byte[] onPkHookHydraDiagNotSupportInfoGet() {
        log("onPkHookHydraDiagNotSupportInfoGet");
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(101);
        reqBuffer.putInt(0);
        return hookBytes;
    }

    public ByteBuffer onHookHydraDebugInfoGetSync() {
        return onHookSendSync(onPkHookHydraDebugInfoGet());
    }

    private byte[] onPkHookHydraDebugInfoGet() {
        log("onPkHookHydraDebugInfoGet");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(95);
        return hookBytes;
    }

    public ByteBuffer onHookHydraDebugInfoForUserGetSync() {
        return onHookSendSync(onPkHookHydraDebugInfoForUserGet());
    }

    private byte[] onPkHookHydraDebugInfoForUserGet() {
        log("onPkHookHydraDebugInfoForUserGet");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(97);
        return hookBytes;
    }

    public boolean onHookModemSceneRecognitionReq(byte[] bytes) {
        return onHookSend(38, onPkModemSceneRecognitionReq(bytes));
    }

    public ByteBuffer onHookModemSceneRecognitionReqSync(byte[] bytes) {
        return onHookSendSync(onPkModemSceneRecognitionReq(bytes));
    }

    private byte[] onPkModemSceneRecognitionReq(byte[] bytes) {
        if (bytes == null) {
            log("onPkModemSceneRecognitionReq, bytes is null");
            return null;
        }
        log("onPkModemSceneRecognitionReq, bytes.length = " + bytes.length);
        int msg_len = bytes.length + 8;
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(74);
        reqBuffer.putInt(bytes.length);
        reqBuffer.put(bytes);
        return hookBytes;
    }

    public boolean onHookSarDsiGet() {
        return onHookSend(63, onPkHookSarDsiGet());
    }

    public ByteBuffer onHookSarDsiGetSync() {
        return onHookSendSync(onPkHookSarDsiGet());
    }

    public int getSarDsi() {
        ByteBuffer byteBuf = onHookSarDsiGetSync();
        if (byteBuf == null) {
            log("getSarDsi, byteBuf is null");
            return -1;
        }
        int dsi = byteBuf.getInt();
        log("getSarDsi, dsi = " + dsi);
        return dsi;
    }

    private byte[] onPkHookSarDsiGet() {
        log("onPkHookSarDsiGet");
        byte[] hookBytes = onGetHookMsgBytes(4);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(63);
        return hookBytes;
    }

    public boolean onHookSarDsiSet(int value) {
        return onHookSend(28, onPkHookSarDsiSet(value));
    }

    public ByteBuffer onHookSarDsiSetSync(int value) {
        return onHookSendSync(onPkHookSarDsiSet(value));
    }

    public boolean setSarDsi(int dsi) {
        ByteBuffer byteBuf = onHookSarDsiSetSync(dsi);
        if (byteBuf == null) {
            log("setSarDsi, byteBuf is null");
            return false;
        }
        int ret = byteBuf.getInt();
        log("getSarDsi, dsi = " + dsi + ", ret = " + ret);
        if (ret != 0) {
            log("getSarDsi failed");
            return false;
        }
        return true;
    }

    private byte[] onPkHookSarDsiSet(int value) {
        log("onPkHookSarDsiSet, value = " + value);
        byte[] hookBytes = onGetHookMsgBytes(8);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(28);
        reqBuffer.putInt(value);
        return hookBytes;
    }

    public boolean onHookCopyFile(String src, String tgt) {
        return onHookSend(49, onPkHookCopyFile(src, tgt));
    }

    public ByteBuffer onHookCopyFileSync(String src, String tgt) {
        return onHookSendSync(onPkHookCopyFile(src, tgt));
    }

    private byte[] onPkHookCopyFile(String src, String tgt) {
        log("onPkHookCopyFile, src = " + src + ", tgt = " + tgt);
        if (src == null) {
            log("src is null");
            return null;
        }
        if (tgt == null) {
            log("tgt is null");
            return null;
        }
        int msg_len = src.length() + 12 + tgt.length();
        byte[] hookBytes = onGetHookMsgBytes(msg_len);
        ByteBuffer reqBuffer = onGetHookMsgBuffer(hookBytes);
        if (reqBuffer == null) {
            log("reqBuffer is null, will not send this hook msg");
            return null;
        }
        reqBuffer.putInt(49);
        reqBuffer.putInt(src.length());
        reqBuffer.put(src.getBytes());
        reqBuffer.putInt(tgt.length());
        reqBuffer.put(tgt.getBytes());
        return hookBytes;
    }

    public boolean registerOemHookCallbackForMiBroadcastData(String packageName, OemHookCallback oemHookCb, String bcName, byte[] payload) {
        return registerOemHookCallbackForMiBroadcastData(packageName, oemHookCb, bcName, payload, 0);
    }

    public boolean registerOemHookCallbackForMiBroadcastData(String packageName, OemHookCallback oemHookCb, String bcName, byte[] payload, int phoneId) {
        log("registerOemHookCallbackForMiBroadcastData: bcName " + bcName + ", phoneId = " + phoneId + ", payload = " + payload + ", oemHookCb = " + oemHookCb + ", packageName = " + packageName);
        try {
            return this.mService.registerOemHookCallbackForMiBroadcastData(packageName, oemHookCb, bcName, payload, phoneId);
        } catch (RemoteException e) {
            log("registerOemHookCallbackForMiBroadcastData, RuntimeException: " + e);
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            log("registerOemHookCallbackForMiBroadcastData, NullPointerException: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    public boolean registerOemHookCallbackForMiBroadcastDataRegistrant(String packageName, Registrant registrant, String bcName, byte[] payload) {
        return registerOemHookCallbackForMiBroadcastDataRegistrant(packageName, registrant, bcName, payload, 0);
    }

    public boolean registerOemHookCallbackForMiBroadcastDataRegistrant(String packageName, Registrant registrant, String bcName, byte[] payload, int phoneId) {
        log("registerOemHookCallbackForMiBroadcastDataRegistrant: bcName " + bcName + ", phoneId = " + phoneId + ", payload = " + payload + ", registrant = " + registrant + ", packageName = " + packageName);
        if (registrant == null) {
            log("registrant is null");
            return false;
        }
        try {
            this.mQcCbRegistrants.add(registrant);
            return this.mService.registerOemHookCallbackForMiBroadcastData(packageName, this.mOemHookCallback, bcName, payload, phoneId);
        } catch (RemoteException e) {
            log("registerOemHookCallbackForMiBroadcastDataRegistrant, RuntimeException: " + e);
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            log("registerOemHookCallbackForMiBroadcastDataRegistrant, NullPointerException: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    public boolean unRegisterOemHookCallbackForMiBroadcastData(String packageName) {
        log("unRegisterOemHookCallbackForMiBroadcastData: packageName " + packageName);
        try {
            return this.mService.unRegisterOemHookCallbackForMiBroadcastData(packageName);
        } catch (RemoteException e) {
            log("unRegisterOemHookCallbackForMiBroadcastData, RuntimeException: " + e);
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            log("unRegisterOemHookCallbackForMiBroadcastData, NullPointerException: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    public boolean unRegisterOemHookCallbackForMiBroadcastDataRegistrant(String packageName, Registrant registrant) {
        log("unRegisterOemHookCallbackForMiBroadcastDataRegistrant: packageName " + packageName + "registrant" + registrant);
        HashSet<Registrant> hashSet = this.mQcCbRegistrants;
        if (hashSet == null) {
            log("mQcCbRegistrants is null");
            return false;
        }
        try {
            hashSet.remove(registrant);
            if (this.mQcCbRegistrants.size() != 0) {
                log("mQcCbRegistrants size is 0");
                return false;
            }
            return this.mService.unRegisterOemHookCallbackForMiBroadcastData(packageName);
        } catch (RemoteException e) {
            log("unRegisterOemHookCallbackForMiBroadcastDataRegistrant, RuntimeException: " + e);
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            log("unRegisterOemHookCallbackForMiBroadcastDataRegistrant, NullPointerException: " + e2);
            e2.printStackTrace();
            return false;
        }
    }

    private static final class MessageRequestInfo {
        public Object arg1;
        public Object arg2;
        public Object arg3;
        public Object arg4;

        public MessageRequestInfo(Object arg1, Object arg2, Object arg3, Object arg4) {
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.arg3 = arg3;
            this.arg4 = arg4;
        }
    }

    public boolean registerOemHookRegistrant(Handler hdl, int what, Object obj, String bcName) {
        return registerOemHookRegistrant(hdl, what, obj, bcName, null, 0);
    }

    public boolean registerOemHookRegistrant(Handler hdl, int what, Object obj, String bcName, byte[] payload) {
        return registerOemHookRegistrant(hdl, what, obj, bcName, payload, 0);
    }

    public boolean registerOemHookRegistrant(Handler hdl, int what, Object obj, String bcName, byte[] payload, int phoneId) {
        if (hdl == null) {
            log("registerOemHookRegistrant, hdl is null");
            return false;
        }
        Registrant registrant = new Registrant(hdl, what, obj);
        return registerOemHookRegistrant(registrant, bcName, payload, phoneId);
    }

    public boolean registerOemHookRegistrant(Registrant registrant, String bcName) {
        return registerOemHookRegistrant(registrant, bcName, (byte[]) null, 0);
    }

    public boolean registerOemHookRegistrant(Registrant registrant, String bcName, byte[] payload) {
        return registerOemHookRegistrant(registrant, bcName, payload, 0);
    }

    public boolean registerOemHookRegistrant(Registrant registrant, String bcName, byte[] payload, int phoneId) {
        log("registerOemHookRegistrant, bcName " + bcName + ", phoneId = " + phoneId + ", payload = " + payload + ", registrant = " + registrant);
        if (registrant == null) {
            log("registerOemHookRegistrant, registrant is null");
            return false;
        }
        HashMap<OemHookCallback, MessageRequestInfo> map = this.mMapCallbackInfo;
        if (map == null) {
            log("registerOemHookRegistrant, mMapCallbackInfo is null");
            return false;
        }
        synchronized (map) {
            Iterator<OemHookCallback> it = this.mMapCallbackInfo.keySet().iterator();
            while (it.hasNext()) {
                MessageRequestInfo msgInfo = this.mMapCallbackInfo.get(it.next());
                if (registrant == ((Registrant) msgInfo.arg1)) {
                    log("registerOemHookRegistrant, this registrant has been registered");
                    return false;
                }
            }
            try {
                OemHookCallback callback = new OemHookCallback() { // from class: com.xiaomi.mirilhook.MiRilHook.3
                    public void onOemHookCallback(byte[] response, int phoneId2) {
                        synchronized (MiRilHook.this.mMapCallbackInfo) {
                            MessageRequestInfo msgInfo2 = (MessageRequestInfo) MiRilHook.this.mMapCallbackInfo.get(this);
                            if (msgInfo2 == null) {
                                MiRilHook.log("onOemHookCallback, not find msgInfo, callback = " + this + ", phoneId = " + phoneId2);
                                return;
                            }
                            Registrant r = (Registrant) msgInfo2.arg1;
                            String bcNameNeed = (String) msgInfo2.arg2;
                            byte[] payload2 = (byte[]) msgInfo2.arg3;
                            int phoneIdNeed = ((Integer) msgInfo2.arg4).intValue();
                            if (MiRilHook.QUALCOMM_HYDRA_DATA_IND_BC_NAME.equals(bcNameNeed) && payload2 != null && 8 == payload2.length) {
                                ByteBuffer reqBuf = ByteBuffer.wrap(payload2);
                                reqBuf.order(ByteOrder.nativeOrder());
                                reqBuf.getInt();
                                int hydraFlag = reqBuf.getInt();
                                if (1 == hydraFlag) {
                                    ByteBuffer rspBuf = ByteBuffer.wrap(response);
                                    rspBuf.order(ByteOrder.nativeOrder());
                                    MiRilHook.mHydraMgr.saveIndData(rspBuf, r);
                                    MiRilHook.log("onOemHookCallback, QUALCOMM_HYDRA_DATA_IND_BC_NAME for HydraDataManager, r = " + r + ", callback = " + this + ", phoneId = " + phoneId2 + ", bcNameNeed = " + bcNameNeed + ", payload = " + payload2 + ", phoneIdNeed = " + phoneIdNeed);
                                    return;
                                }
                                MiRilHook.log("onOemHookCallback, QUALCOMM_HYDRA_DATA_IND_BC_NAME, do nothing, r = " + r + ", callback = " + this + ", phoneId = " + phoneId2 + ", bcNameNeed = " + bcNameNeed + ", payload = " + payload2 + ", phoneIdNeed = " + phoneIdNeed);
                                return;
                            }
                            r.notifyRegistrant(new AsyncResult(Integer.valueOf(phoneId2), response, (Throwable) null));
                            MiRilHook.log("onOemHookCallback, r = " + r + ", callback = " + this + ", phoneId = " + phoneId2 + ", bcNameNeed = " + bcNameNeed + ", payload = " + payload2 + ", phoneIdNeed = " + phoneIdNeed);
                        }
                    }
                };
                synchronized (this.mMapCallbackInfo) {
                    MessageRequestInfo msgInfo2 = new MessageRequestInfo(registrant, bcName, payload, Integer.valueOf(phoneId));
                    this.mMapCallbackInfo.put(callback, msgInfo2);
                }
                return this.mService.registerOemHookCallback(callback, bcName, payload, phoneId);
            } catch (RemoteException e) {
                log("registerOemHookRegistrant, RuntimeException: " + e);
                e.printStackTrace();
                return false;
            } catch (NullPointerException e2) {
                log("registerOemHookRegistrant, NullPointerException: " + e2);
                e2.printStackTrace();
                return false;
            }
        }
    }

    public boolean unRegisterOemHookRegistrant(Registrant registrant) {
        log("unRegisterOemHookRegistrant, registrant = " + registrant);
        if (registrant == null) {
            log("unRegisterOemHookRegistrant, registrant is null");
            return false;
        }
        HashMap<OemHookCallback, MessageRequestInfo> map = this.mMapCallbackInfo;
        if (map == null) {
            log("unRegisterOemHookRegistrant, mMapCallbackInfo is null");
            return false;
        }
        try {
            synchronized (map) {
                for (OemHookCallback callback : this.mMapCallbackInfo.keySet()) {
                    MessageRequestInfo msgInfo = this.mMapCallbackInfo.get(callback);
                    if (registrant == ((Registrant) msgInfo.arg1)) {
                        this.mMapCallbackInfo.remove(callback);
                        return this.mService.unRegisterOemHookCallback(callback);
                    }
                }
                return false;
            }
        } catch (RemoteException e) {
            log("unRegisterOemHookRegistrant, RuntimeException: " + e);
            e.printStackTrace();
        } catch (NullPointerException e2) {
            log("unRegisterOemHookRegistrant, NullPointerException: " + e2);
            e2.printStackTrace();
        }
    }

    public void bindFiveGServiceConnection(String packageName, Context context, Registrant registrant) {
        log("bindFiveGServiceConnection: context=" + context);
        if (this.mExtTelManagerServiceCallback == null) {
            log("onDeregIndicateFromRild, mExtTelManagerServiceCallback is null");
            return;
        }
        this.m5GRegistrant = registrant;
        this.m5GPackageName = packageName;
        ExtTelephonyManager extTelephonyManager = ExtTelephonyManager.getInstance(context);
        this.mExtTelephonyManager = extTelephonyManager;
        if (true == extTelephonyManager.connectService(this.mExtTelManagerServiceCallback)) {
            log("bindFiveGServiceConnection success");
        }
    }

    public void unBindFiveGServiceConnection() {
        log("unBindFiveGServiceConnection");
        ExtTelephonyManager extTelephonyManager = this.mExtTelephonyManager;
        if (extTelephonyManager == null) {
            log("mExtTelephonyManager is null");
            return;
        }
        try {
            extTelephonyManager.unRegisterCallback(this.mExtPhoneCallback);
        } catch (Exception e) {
            log("deregisterServiceConnection: Exception = " + e);
        }
        this.mExtTelephonyManager.disconnectService();
        this.m5GRegistrant = null;
        this.mExtTelephonyManager = null;
        this.mClient = null;
    }

    private void onInitExtTelManagerServiceCallback() {
        this.mExtTelManagerServiceCallback = new ServiceCallback() { // from class: com.xiaomi.mirilhook.MiRilHook.4
            public void onConnected() {
                if (MiRilHook.this.m5GPackageName == null) {
                    MiRilHook.log("onServiceConnected failed, m5GPackageName is null.");
                }
                MiRilHook.log("onServiceConnected. mNumPhones = " + MiRilHook.mNumPhones + ", m5GPackageName = " + MiRilHook.this.m5GPackageName);
                try {
                    MiRilHook miRilHook = MiRilHook.this;
                    miRilHook.mClient = miRilHook.mExtTelephonyManager.registerCallback(MiRilHook.this.m5GPackageName, MiRilHook.this.mExtPhoneCallback);
                    MiRilHook.log("mExtTelManagerServiceCallback: service connected " + MiRilHook.this.mClient);
                    for (int i = 0; i < MiRilHook.mNumPhones; i++) {
                        MiRilHook.this.mExtTelephonyManager.query5gStatus(i, MiRilHook.this.mClient);
                        MiRilHook.this.mExtTelephonyManager.queryNrIconType(i, MiRilHook.this.mClient);
                        MiRilHook.this.mExtTelephonyManager.queryEndcStatus(i, MiRilHook.this.mClient);
                        MiRilHook.this.mExtTelephonyManager.queryNrDcParam(i, MiRilHook.this.mClient);
                        MiRilHook.this.mExtTelephonyManager.queryNrBearerAllocation(i, MiRilHook.this.mClient);
                        MiRilHook.this.mExtTelephonyManager.queryUpperLayerIndInfo(i, MiRilHook.this.mClient);
                        MiRilHook.this.mExtTelephonyManager.query5gConfigInfo(i, MiRilHook.this.mClient);
                        MiRilHook.log("onServiceConnected. success ");
                    }
                } catch (Exception e) {
                    MiRilHook.log("onServiceConnected: Exception = " + e);
                }
            }

            public void onDisconnected() {
                MiRilHook.log("mExtTelManagerServiceCallback: service disconnected");
                cleanup();
            }

            private void cleanup() {
                MiRilHook.log("cleanup");
                MiRilHook.this.mExtTelephonyManager = null;
                MiRilHook.this.mClient = null;
            }
        };
    }

    private void validateInternalState() {
        if (isDisposed()) {
            throw new IllegalStateException("QcRilHook is in disposed state");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String msg) {
        Log.d("MI_RIL_OEM_HOOK(" + ModemUtils.getCurProcessName(mContext) + ")", msg);
    }

    private static void tmpLog(String msg) {
        if (mTmpLogPrintFlag) {
            log(msg);
        }
    }
}
