package com.xiaomi.mtb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Registrant;
import android.util.Log;
import com.xiaomi.modem.ModemUtils;
import com.xiaomi.modem.OemHookAgent;
import com.xiaomi.mtb.activity.MtbBaseActivity;
import com.xiaomi.mtb.activity.MtbDataDisplayActivity;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MtbHydraDataManager {
    public static final String BASIC_CMN_STATUS = "BASIC_CMN_STATUS";
    public static final String BASIC_SUB_STATUS = "BASIC_SUB_STATUS";
    private static final String BROADCAST_CONTEXT = "BROADCAST_CONTEXT";
    private static final String BUNDLE_OEMHOOKCB_PHONEID = "BUNDLE_OEMHOOKCB_PHONEID";
    private static final String BUNDLE_OEMHOOKCB_RESULT = "BUNDLE_OEMHOOKCB_RESULT";
    public static final String CALL_INFO = "CALL_INFO";
    private static final String COLOR_TXT_SUB_NAME = "#C71585";
    public static final String DATA_GROUP_NO_SHOW_FLAG = "There is no data to show for this SUB.";
    public static final String DATA_INFO = "DATA_INFO";
    private static final int EVENT_DATA_UPDATE_HYDRA = 100;
    public static final String GPS_INFO = "GPS_INFO";
    private static final int HYDRA_STATS_CLOSE = 0;
    private static final int HYDRA_STATS_OPEN = 1;
    private static final int LTE_BW_IDX_NRB_100 = 5;
    private static final int LTE_BW_IDX_NRB_15 = 1;
    private static final int LTE_BW_IDX_NRB_25 = 2;
    private static final int LTE_BW_IDX_NRB_50 = 3;
    private static final int LTE_BW_IDX_NRB_6 = 0;
    private static final int LTE_BW_IDX_NRB_75 = 4;
    private static final int LTE_BW_NRB_100 = 100;
    private static final int LTE_BW_NRB_15 = 15;
    private static final int LTE_BW_NRB_25 = 25;
    private static final int LTE_BW_NRB_50 = 50;
    private static final int LTE_BW_NRB_6 = 6;
    private static final int LTE_BW_NRB_75 = 75;
    public static final String LTE_CA_INFO = "LTE_CA_INFO";
    private static final int LTE_CONNECTED = 0;
    private static final int LTE_IDLE = 1;
    private static final int LTE_MODULATION_1024QAM = 5;
    private static final int LTE_MODULATION_16QAM = 2;
    private static final int LTE_MODULATION_256QAM = 4;
    private static final int LTE_MODULATION_64QAM = 3;
    private static final int LTE_MODULATION_BPSK = 0;
    private static final int LTE_MODULATION_INVALID = 255;
    private static final int LTE_MODULATION_QPSK = 1;
    public static final String LTE_NBR_INFO = "LTE_NBR_INFO";
    private static final int LTE_PDSCH_MODULATION_1024QAM = 10;
    private static final int LTE_PDSCH_MODULATION_16QAM = 4;
    private static final int LTE_PDSCH_MODULATION_256QAM = 8;
    private static final int LTE_PDSCH_MODULATION_64QAM = 6;
    private static final int LTE_PDSCH_MODULATION_QPSK = 2;
    private static final float LTE_RSRP_INI = 255.0f;
    private static final float LTE_RSRP_MAX = -44.0f;
    private static final float LTE_RSRP_MIN = -140.0f;
    private static final float LTE_RSRQ_MAX = -3.0f;
    private static final float LTE_RSRQ_MIN = -19.5f;
    private static final float LTE_SINR_MAX = -10.0f;
    private static final float LTE_SINR_MIN = 40.0f;
    public static final String LTE_SRV_BASIC_INFO = "LTE_SRV_BASIC_INFO";
    public static final String LTE_SRV_DETAIL_INFO = "LTE_SRV_DETAIL_INFO";
    private static final int MI_BROADCAST_HYDRA_DATA_IND = 3;
    private static final int MODEM_STATS_CALL_ADDRESS_MAX = 48;
    private static final int MODEM_STATS_DATA_CALL_DIRECTION_MO = 1;
    private static final int MODEM_STATS_DATA_CALL_DIRECTION_MT = 2;
    private static final int MODEM_STATS_DATA_CALL_DIRECTION_NONE = 0;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_AMR_NB = 6;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_AMR_WB = 7;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVRC = 2;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVRC_B = 3;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVRC_NW = 5;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVRC_WB = 4;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVS_FB = 20;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVS_NB = 17;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVS_SWB = 19;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_EVS_WB = 18;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_G711A = 13;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_G711AB = 15;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_G711U = 11;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_G722 = 14;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_G723 = 12;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_G729 = 16;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_GSM_EFR = 8;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_GSM_FR = 9;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_GSM_HR = 10;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_MAX = 32767;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_NONE = 0;
    private static final int MODEM_STATS_DATA_CALL_SPEECH_CODEC_QCELP13K = 1;
    private static final int MODEM_STATS_DATA_CALL_STATE_CONV = 3;
    private static final int MODEM_STATS_DATA_CALL_STATE_IDLE = 0;
    private static final int MODEM_STATS_DATA_CALL_STATE_INCOM = 2;
    private static final int MODEM_STATS_DATA_CALL_STATE_IN_PROGRESS = 4;
    private static final int MODEM_STATS_DATA_CALL_STATE_MAX = 6;
    private static final int MODEM_STATS_DATA_CALL_STATE_ORIG = 1;
    private static final int MODEM_STATS_DATA_CALL_STATE_RECALL_RSP_PEND = 5;
    private static final int MODEM_STATS_DATA_CALL_STATE_UNKNOWN = -1;
    private static final int MODEM_STATS_DATA_CALL_TYPE_CS_DATA = 1;
    private static final int MODEM_STATS_DATA_CALL_TYPE_EMERGENCY = 9;
    private static final int MODEM_STATS_DATA_CALL_TYPE_EXT = 16;
    private static final int MODEM_STATS_DATA_CALL_TYPE_MAX = 17;
    private static final int MODEM_STATS_DATA_CALL_TYPE_NONE = -1;
    private static final int MODEM_STATS_DATA_CALL_TYPE_NON_STD_OTASP = 8;
    private static final int MODEM_STATS_DATA_CALL_TYPE_OTAPA = 6;
    private static final int MODEM_STATS_DATA_CALL_TYPE_PD = 4;
    private static final int MODEM_STATS_DATA_CALL_TYPE_PS_DATA = 2;
    private static final int MODEM_STATS_DATA_CALL_TYPE_PS_DATA_IS707B = 14;
    private static final int MODEM_STATS_DATA_CALL_TYPE_SMS = 3;
    private static final int MODEM_STATS_DATA_CALL_TYPE_STD_OTASP = 7;
    private static final int MODEM_STATS_DATA_CALL_TYPE_SUPS = 10;
    private static final int MODEM_STATS_DATA_CALL_TYPE_TEST = 5;
    private static final int MODEM_STATS_DATA_CALL_TYPE_UNKNOWN = 15;
    private static final int MODEM_STATS_DATA_CALL_TYPE_VOICE = 0;
    private static final int MODEM_STATS_DATA_CALL_TYPE_VS = 13;
    private static final int MODEM_STATS_DATA_CALL_TYPE_VT = 11;
    private static final int MODEM_STATS_DATA_CALL_TYPE_VT_LOOPBACK = 12;
    private static final int MODEM_STATS_DATA_GET_NUM_BYTE_NO = 4;
    private static final int MODEM_STATS_DATA_ID_1544_DIAG_LOG_QMI_MCS_QCSI_PKT_FOR_VOICE = 19;
    private static final int MODEM_STATS_DATA_ID_1606_DIAG_EVENT_LTE_RRC_STATE_CHANGE = 5;
    private static final int MODEM_STATS_DATA_ID_1632_DIAG_EVENT_LTE_T3410_EXPIRY_LIST = 0;
    private static final int MODEM_STATS_DATA_ID_1632_DIAG_EVENT_LTE_T3430_EXPIRY_LIST = 1;
    private static final int MODEM_STATS_DATA_ID_1995_DIAG_EVENT_LTE_RLF_OF_WEAK_SIGNAL_LIST = 4;
    private static final int MODEM_STATS_DATA_ID_2622_DIAG_EVENT_MCS_TRM_ASDIV = 100;
    private static final int MODEM_STATS_DATA_ID_3184_DIAG_EVENT_NR5G_RRC_NEW_CELL_IND_V2SSB = 92;
    private static final int MODEM_STATS_DATA_ID_3215_DIAG_EVENT_NR5G_T3510_EXPIRY_LIST = 2;
    private static final int MODEM_STATS_DATA_ID_B0C2_DIAG_LOG_LTE_RRC_SERVING_CELL_INFO = 129;
    private static final int MODEM_STATS_DATA_ID_B111_DIAG_LOG_LTE_LL1_RX_AGC = 96;
    private static final int MODEM_STATS_DATA_ID_B14E_DIAG_LOG_LTE_PUSCH_CSF = 84;
    private static final int MODEM_STATS_DATA_ID_B16D_DIAG_LOG_LTE_TX_POWER = 83;
    private static final int MODEM_STATS_DATA_ID_B16E_DIAG_LOG_LTE_PUSCH_POWER_CONTROL = 98;
    private static final int MODEM_STATS_DATA_ID_B173_DIAG_LOG_LTE_PDSCH_STAT_INDICATION = 93;
    private static final int MODEM_STATS_DATA_ID_B193_DIAG_LOG_LTE_SRV_MEAS = 82;
    private static final int MODEM_STATS_DATA_ID_B823_DIAG_LOG_NR5G_RRC_SERVING_CELL_INFO = 130;
    private static final int MODEM_STATS_DATA_ID_B825_DIAG_LOG_NR5G_RRC_CONFIGURATION_INFO = 97;
    private static final int MODEM_STATS_DATA_ID_B884_DIAG_LOG_NR5G_MAC_UL_PHY_CH_POWER_CTRL = 90;
    private static final int MODEM_STATS_DATA_ID_B885_DIAG_LOG_NR5G_MAC_DCI_INFO = 85;
    private static final int MODEM_STATS_DATA_ID_B887_DIAG_LOG_NR5G_22MIMO_IND = 23;
    private static final int MODEM_STATS_DATA_ID_B887_DIAG_LOG_NR5G_MAC_PDSCH_STATUS = 87;
    private static final int MODEM_STATS_DATA_ID_B888_DIAG_LOG_NR5G_MAC_PDSCH_STATS = 88;
    private static final int MODEM_STATS_DATA_ID_B88A_DIAG_LOG_NR5G_MAC_RACH_ATTEMPT = 86;
    private static final int MODEM_STATS_DATA_ID_B8A7_DIAG_LOG_NR5G_MAC_CSF_REPORT = 89;
    private static final int MODEM_STATS_DATA_ID_B8C9_DIAG_LOG_NR5G_LL1_FW_RX_CONTROL_AGC = 94;
    private static final int MODEM_STATS_DATA_ID_B8D2_DIAG_LOG_NR5G_LL1_FW_MAC_TX_IU_POWER = 99;
    private static final int MODEM_STATS_DATA_ID_B8D8_DIAG_LOG_NR5G_LL1_LOG_SERVING_SNR = 137;
    private static final int MODEM_STATS_DATA_ID_B975_DIAG_LOG_NR5G_ML1_SRV_BEAM = 91;
    private static final int MODEM_STATS_DATA_ID_B97F_DIAG_LOG_NR5G_ML1_MEAS_DATABASE_UPDATE_EXT = 135;
    private static final int MODEM_STATS_DATA_ID_B97F_DIAG_LOG_NR5G_ML1_MEAS_SCC1 = 7;
    private static final int MODEM_STATS_DATA_ID_B97F_DIAG_LOG_NR5G_ML1_MEAS_SCC2 = 8;
    private static final int MODEM_STATS_DATA_ID_B981_DIAG_LOG_NR5G_HIGH_T_DROP_LIST = 3;
    private static final int MODEM_STATS_DATA_ID_CALL_CM_REDIAL = 62;
    private static final int MODEM_STATS_DATA_ID_CALL_DETAIL = 58;
    private static final int MODEM_STATS_DATA_ID_CALL_GSM_DROP = 63;
    private static final int MODEM_STATS_DATA_ID_CALL_IMS_SIP_SESSION_FAILURE = 59;
    private static final int MODEM_STATS_DATA_ID_CALL_IMS_SIP_SESSION_RINGING = 61;
    private static final int MODEM_STATS_DATA_ID_CALL_LTE_RRC_IRAT_HO_FROM_EUTRAN_FAILUR = 60;
    private static final int MODEM_STATS_DATA_ID_CALL_WCDMA_DROP = 64;
    private static final int MODEM_STATS_DATA_ID_CDMA_REG_STAT = 6;
    private static final int MODEM_STATS_DATA_ID_DATA_DETACH = 69;
    private static final int MODEM_STATS_DATA_ID_GPS = 73;
    private static final int MODEM_STATS_DATA_ID_GW_MESSAGE_SENT = 79;
    private static final int MODEM_STATS_DATA_ID_IMS_INFO = 37;
    private static final int MODEM_STATS_DATA_ID_LTE_EMM_OTA_OUTGOING_MSG = 78;
    private static final int MODEM_STATS_DATA_ID_LTE_MEAS_INFO = 30;
    private static final int MODEM_STATS_DATA_ID_LTE_NGBR_IND_CA_INFO = 35;
    private static final int MODEM_STATS_DATA_ID_LTE_NGBR_IND_GSM_INFO = 33;
    private static final int MODEM_STATS_DATA_ID_LTE_NGBR_IND_INTER_FREQ = 32;
    private static final int MODEM_STATS_DATA_ID_LTE_NGBR_IND_INTRA_FREQ = 31;
    private static final int MODEM_STATS_DATA_ID_LTE_NGBR_IND_WCDMA_INFO = 34;
    private static final int MODEM_STATS_DATA_ID_LTE_RLF = 81;
    private static final int MODEM_STATS_DATA_ID_LTE_RRC_STATE = 36;
    private static final int MODEM_STATS_DATA_ID_MAX = 101;
    private static final int MODEM_STATS_DATA_ID_MIN = 0;
    private static final int MODEM_STATS_DATA_ID_NAS_ATTACH_TYPE = 39;
    private static final int MODEM_STATS_DATA_ID_NAS_CS_REJECT_CAUSE = 41;
    private static final int MODEM_STATS_DATA_ID_NAS_PERIODIC_TIMER = 40;
    private static final int MODEM_STATS_DATA_ID_NAS_PS_CALL_INFO = 43;
    private static final int MODEM_STATS_DATA_ID_NAS_PS_REJECT_CAUSE = 42;
    private static final int MODEM_STATS_DATA_ID_NAS_REG_NW_INFO = 38;
    private static final int MODEM_STATS_DATA_ID_NR5G_MEAS_INFO = 75;
    private static final int MODEM_STATS_DATA_ID_NR5G_NGBR_IND_INTER_FREQ = 77;
    private static final int MODEM_STATS_DATA_ID_NR5G_NGBR_IND_INTRA_FREQ = 76;
    private static final int MODEM_STATS_DATA_ID_NR5G_RLF = 80;
    private static final int MODEM_STATS_DATA_ID_REG_ACTIVE_BAND = 48;
    private static final int MODEM_STATS_DATA_ID_REG_ARFCN = 49;
    private static final int MODEM_STATS_DATA_ID_REG_BID = 54;
    private static final int MODEM_STATS_DATA_ID_REG_CELL_ID = 50;
    private static final int MODEM_STATS_DATA_ID_REG_LAC_ID = 51;
    private static final int MODEM_STATS_DATA_ID_REG_LTE_IMS_VOICE_AVAIL = 56;
    private static final int MODEM_STATS_DATA_ID_REG_MCC_MNC = 55;
    private static final int MODEM_STATS_DATA_ID_REG_NID = 53;
    private static final int MODEM_STATS_DATA_ID_REG_RADIO_TECH = 44;
    private static final int MODEM_STATS_DATA_ID_REG_REJECT_CAUSE = 46;
    private static final int MODEM_STATS_DATA_ID_REG_RLF = 57;
    private static final int MODEM_STATS_DATA_ID_REG_SID = 52;
    private static final int MODEM_STATS_DATA_ID_REG_SRV_DOMAIN = 47;
    private static final int MODEM_STATS_DATA_ID_REG_SRV_STATUS = 45;
    private static final int MODEM_STATS_DATA_ID_RF_DPDT = 70;
    private static final int MODEM_STATS_DATA_ID_RF_RX_PWR = 71;
    private static final int MODEM_STATS_DATA_ID_RF_TX_PWR = 72;
    private static final int MODEM_STATS_DATA_ID_UIM_CARD_ERROR = 67;
    private static final int MODEM_STATS_DATA_ID_UIM_INSERT = 65;
    private static final int MODEM_STATS_DATA_ID_UIM_PIN_STATUS = 68;
    private static final int MODEM_STATS_DATA_ID_UIM_SUB_READY = 66;
    private static final int MODEM_STATS_DATA_ID_WCDMA_ACTIVE_SET_INFO = 27;
    private static final int MODEM_STATS_DATA_ID_WCDMA_ACTIVE_SET_REFERENCE_Rl_INFO = 26;
    private static final int MODEM_STATS_DATA_ID_WCDMA_MONITORED_SET = 28;
    private static final int MODEM_STATS_DATA_ID_WCDMA_RRC_STATE = 29;
    private static final int MODEM_STATS_DATA_ID_WCDMA_SERVING_CELL_INFO = 24;
    private static final int MODEM_STATS_DATA_ID_WCDMA_SERVING_CELL_POWER_INFO = 25;
    private static final int MODEM_STATS_DATA_ID_XXXX_DIAG_EVENT_LTE_EMM_TIMER = 95;
    private static final String MODEM_STATS_DATA_INVALID = "MODEM_STATS_DATA_INVALID";
    private static final int MODEM_STATS_DATA_LTE_NBR_CELL_FREQ_NUM_MAX = 2;
    private static final int MODEM_STATS_DATA_LTE_NBR_CELL_NUM_MAX = 8;
    private static final int MODEM_STATS_DATA_LTE_SCELL_SIZE = 3;
    private static final int MODEM_STATS_DATA_MAX_APN_ADR_LEN = 102;
    private static final int MODEM_STATS_DATA_MAX_PDP_ADR_LEN = 20;
    private static final int MODEM_STATS_DATA_NOT_GET = -1;
    private static final byte MODEM_STATS_DATA_NOT_GET_BYTE = -1;
    private static final int MODEM_STATS_DATA_PLMN_NUM_MAX = 3;
    private static final int MODEM_STATS_DATA_REG_RLF_CDMA = 4;
    private static final int MODEM_STATS_DATA_REG_RLF_GSM = 3;
    private static final int MODEM_STATS_DATA_REG_RLF_LTE = 1;
    private static final int MODEM_STATS_DATA_REG_RLF_NONE = 0;
    private static final int MODEM_STATS_DATA_REG_RLF_NR5G = 5;
    private static final int MODEM_STATS_DATA_REG_RLF_WCDMA = 2;
    public static final int MODEM_STATS_DATA_SRV_STATUS_LIMITED = 1;
    public static final int MODEM_STATS_DATA_SRV_STATUS_LIMITED_INTERNAL = 6;
    public static final int MODEM_STATS_DATA_SRV_STATUS_LIMITED_REGIONAL = 3;
    public static final int MODEM_STATS_DATA_SRV_STATUS_LIMITED_REGIONAL_INTERNAL = 7;
    public static final int MODEM_STATS_DATA_SRV_STATUS_MAX = 9;
    public static final int MODEM_STATS_DATA_SRV_STATUS_NONE = -1;
    public static final int MODEM_STATS_DATA_SRV_STATUS_NO_SRV = 0;
    public static final int MODEM_STATS_DATA_SRV_STATUS_NO_SRV_INTERNAL = 5;
    public static final int MODEM_STATS_DATA_SRV_STATUS_PWR_SAVE = 4;
    public static final int MODEM_STATS_DATA_SRV_STATUS_PWR_SAVE_INTERNAL = 8;
    public static final int MODEM_STATS_DATA_SRV_STATUS_SRV = 2;
    public static final int MODEM_STATS_DATA_SYS_MODE_AMPS = 1;
    public static final int MODEM_STATS_DATA_SYS_MODE_CDMA = 2;
    public static final int MODEM_STATS_DATA_SYS_MODE_GPS = 6;
    public static final int MODEM_STATS_DATA_SYS_MODE_GSM = 3;
    public static final int MODEM_STATS_DATA_SYS_MODE_GW = 7;
    public static final int MODEM_STATS_DATA_SYS_MODE_GWL = 10;
    public static final int MODEM_STATS_DATA_SYS_MODE_HDR = 4;
    public static final int MODEM_STATS_DATA_SYS_MODE_LTE = 9;
    public static final int MODEM_STATS_DATA_SYS_MODE_MAX = 13;
    public static final int MODEM_STATS_DATA_SYS_MODE_NONE = -1;
    public static final int MODEM_STATS_DATA_SYS_MODE_NO_SRV = 0;
    public static final int MODEM_STATS_DATA_SYS_MODE_NR5G = 12;
    public static final int MODEM_STATS_DATA_SYS_MODE_TDS = 11;
    public static final int MODEM_STATS_DATA_SYS_MODE_WCDMA = 5;
    public static final int MODEM_STATS_DATA_SYS_MODE_WLAN = 8;
    private static final int MODEM_STATS_DATA_SYS_TMSI_SIZE = 4;
    private static final int MODEM_STATS_DATA_UIM_APP_CSIM = 4;
    private static final int MODEM_STATS_DATA_UIM_APP_NONE = 0;
    private static final int MODEM_STATS_DATA_UIM_APP_RUIM = 2;
    private static final int MODEM_STATS_DATA_UIM_APP_SIM = 1;
    private static final int MODEM_STATS_DATA_UIM_APP_UNKNOWN = 5;
    private static final int MODEM_STATS_DATA_UIM_APP_USIM = 3;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_CARD_REMOVED = 20;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_CORRUPT_ATR_RCVD_MAX_TIMES = 4;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_INT_CMD_ERR_EXCEED_MAX_NUMS = 8;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_INT_CMD_TIMED_OUT_AFTER_PPS = 7;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_INT_ERR_IN_PASSIVE_MODE = 14;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_MAXED_OVERRUN_ERR = 11;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_MAXED_PARITY_ERROR = 9;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_MAXED_RX_BREAK_ERR = 10;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_MAX_OVERRUN_IN_PASSIVE = 18;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_MAX_PARITY_IN_PASSIVE = 16;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_MAX_RXBRK_IN_PASSIVE = 17;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_NO_ATR_RCVD_AFTER_RESET = 3;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_NO_ATR_RCVD_AT_MAX_VOLT = 2;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_NO_PROTOCOL_AT_CARD_PUP = 19;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_NULL_BYTES = 22;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_POLL_ERROR = 1;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_PPS_TIMED_OUT_MAX_TIMES = 5;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_PWR_DN_CMD_NOTIFY = 13;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_SAP_CONNECTED = 23;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_TECHNICAL_PROBLEM = 21;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_TIMED_OUT_IN_PASSIVE_MODE = 15;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_TRANS_TIMER_EXP = 12;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_UNKNOWN_ERROR = 0;
    private static final int MODEM_STATS_DATA_UIM_CARD_ERR_VOLT_MISMATCH = 6;
    private static final int MODEM_STATS_DATA_UIM_ICCID_LEN = 10;
    private static final int MODEM_STATS_DATA_UIM_PIN_BLOCKED = 4;
    private static final int MODEM_STATS_DATA_UIM_PIN_CHANGED = 7;
    private static final int MODEM_STATS_DATA_UIM_PIN_DISABLED = 3;
    private static final int MODEM_STATS_DATA_UIM_PIN_ENABLED_NOT_VERIFIED = 1;
    private static final int MODEM_STATS_DATA_UIM_PIN_ENABLED_VERIFIED = 2;
    private static final int MODEM_STATS_DATA_UIM_PIN_NOT_FOUND = 8;
    private static final int MODEM_STATS_DATA_UIM_PIN_PERM_BLOCKED = 5;
    private static final int MODEM_STATS_DATA_UIM_PIN_RETRY_COUNT_DECREASED = 9;
    private static final int MODEM_STATS_DATA_UIM_PIN_STATUS_NOT_INITIALIZED = 0;
    private static final int MODEM_STATS_DATA_UIM_PIN_UNBLOCKED = 6;
    private static final int MODEM_STATS_DATA_VOICE_HANDOVER_1XCSFB = 7;
    private static final int MODEM_STATS_DATA_VOICE_HANDOVER_GWCSFB = 8;
    private static final int MODEM_STATS_DATA_VOICE_HO_G_2_G = 1;
    private static final int MODEM_STATS_DATA_VOICE_HO_G_2_W = 2;
    private static final int MODEM_STATS_DATA_VOICE_HO_NONE = 0;
    private static final int MODEM_STATS_DATA_VOICE_HO_SRVCC_L_2_C = 9;
    private static final int MODEM_STATS_DATA_VOICE_HO_SRVCC_L_2_G = 5;
    private static final int MODEM_STATS_DATA_VOICE_HO_SRVCC_L_2_W = 6;
    private static final int MODEM_STATS_DATA_VOICE_HO_W_2_G = 4;
    private static final int MODEM_STATS_DATA_VOICE_HO_W_2_W = 3;
    private static final int MODEM_STATS_DEREG_BYTE_SIZE_MAX = 412;
    private static final int MODEM_STATS_GET_ID_NUM_MAX = 10;
    private static final int MODEM_STATS_ID_MAX = 10;
    private static final int MODEM_STATS_IND_DATA_START = 1;
    private static final int MODEM_STATS_IND_DATA_STOP = 0;
    private static final int MODEM_STATS_REG_BYTE_SIZE_MAX = 812;
    private static final int MODEM_STATS_REG_ID_NUM_BYTE_NO = 8;
    private static final int MODEM_STATS_REG_ID_NUM_MAX = 100;
    private static final int MODEM_STATS_STACK_ID_MAX = 2;
    public static final String NAS_INFO = "NAS_INFO";
    public static final String NON_STOP_GROUP_CMN = "NON_STOP_GROUP_CMN";
    public static final String NON_STOP_GROUP_SUB = "NON_STOP_GROUP_SUB";
    private static final int NR5G_BAND_TYPE_MMW = 2;
    private static final int NR5G_BAND_TYPE_SUB6 = 1;
    private static final int NR5G_BAND_TYPE_UNKNOWN = 0;
    private static final int NR5G_BW_100MHz = 12;
    private static final int NR5G_BW_10MHz = 1;
    private static final int NR5G_BW_15MHz = 2;
    private static final int NR5G_BW_200MHz = 13;
    private static final int NR5G_BW_20MHz = 3;
    private static final int NR5G_BW_25MHz = 4;
    private static final int NR5G_BW_30MHz = 5;
    private static final int NR5G_BW_400MHz = 14;
    private static final int NR5G_BW_40MHz = 6;
    private static final int NR5G_BW_50MHz = 7;
    private static final int NR5G_BW_5MHz = 0;
    private static final int NR5G_BW_60MHz = 8;
    private static final int NR5G_BW_70MHz = 9;
    private static final int NR5G_BW_80MHz = 10;
    private static final int NR5G_BW_90MHz = 11;
    private static final int NR5G_BW_V3_100MHz = 11;
    private static final int NR5G_BW_V3_10MHz = 1;
    private static final int NR5G_BW_V3_15MHz = 2;
    private static final int NR5G_BW_V3_200MHz = 12;
    private static final int NR5G_BW_V3_20MHz = 3;
    private static final int NR5G_BW_V3_25MHz = 4;
    private static final int NR5G_BW_V3_30MHz = 5;
    private static final int NR5G_BW_V3_400MHz = 13;
    private static final int NR5G_BW_V3_40MHz = 6;
    private static final int NR5G_BW_V3_50MHz = 7;
    private static final int NR5G_BW_V3_5MHz = 0;
    private static final int NR5G_BW_V3_60MHz = 8;
    private static final int NR5G_BW_V3_80MHz = 9;
    private static final int NR5G_BW_V3_90MHz = 10;
    public static final String NR5G_NBR_INFO = "NR5G_NBR_INFO";
    private static final int NR5G_PDSCH_MODULATION_1024QAM = 5;
    private static final int NR5G_PDSCH_MODULATION_16QAM = 2;
    private static final int NR5G_PDSCH_MODULATION_256QAM = 4;
    private static final int NR5G_PDSCH_MODULATION_64QAM = 3;
    private static final int NR5G_PDSCH_MODULATION_QPSK = 0;
    private static final int NR5G_RB_PATH_LTE = 1;
    private static final int NR5G_RB_PATH_LTE_AND_NR = 3;
    private static final int NR5G_RB_PATH_MAX = 4;
    private static final int NR5G_RB_PATH_NONE = 0;
    private static final int NR5G_RB_PATH_NR = 2;
    private static final int NR5G_RB_TYPE_DRB = 2;
    private static final int NR5G_RB_TYPE_INVALID = 0;
    private static final int NR5G_RB_TYPE_MTCH = 3;
    private static final int NR5G_RB_TYPE_SRB = 1;
    public static final String NR5G_SRV_INFO = "NR5G_SRV_INFO";
    public static final String PHONE_SUB_INFO_MWC = "PHONE_SUB_INFO";
    public static final String PHONE_SUB_SIGNAL_INFO = "PHONE_RX_INFO";
    public static final String PHONE_SUB_TX_INFO = "PHONE_TX_INFO";
    public static final String PHONE_SUB_VOICE_INFO = "PHONE_VOICE_INFO";
    private static final String PROP_PRINT_IDLE = "0";
    private static final String PROP_PRINT_OFF = "2";
    private static final String PROP_PRINT_ON = "1";
    private static final String PROP_PRINT_STATS = "persist.vendor.radio.print_stats";
    private static final String QUALCOMM_HYDRA_DATA_IND_BC_NAME = "qualcomm.intent.action.ACTION_HYDRA_DATA_IND";
    public static final String RF_INFO = "RF_INFO";
    private static final int SRV_DOMAIN_CAMPED = 4;
    private static final int SRV_DOMAIN_CS_ONLY = 1;
    private static final int SRV_DOMAIN_CS_PS = 3;
    private static final int SRV_DOMAIN_CS_VOLTE = 5;
    private static final int SRV_DOMAIN_NONE = -1;
    private static final int SRV_DOMAIN_NO_SRV = 0;
    private static final int SRV_DOMAIN_PS_ONLY = 2;
    private static final int SRV_DOMAIN_VOLTE = 6;
    private static final int SUB_0 = 0;
    private static final int SUB_1 = 1;
    private static final int SUB_ALL = 2;
    private static final int SUB_MASK_0 = 1;
    private static final int SUB_MASK_1 = 2;
    private static final int SUB_MASK_ALL = 3;
    private static final int SYS_NAS_PROCEDURE_CM = 2;
    private static final int SYS_NAS_PROCEDURE_EMM_COMBINED = 9;
    private static final int SYS_NAS_PROCEDURE_EMM_COMBINED_NON_EPS = 8;
    private static final int SYS_NAS_PROCEDURE_EMM_NON_COMBINED_EPS = 7;
    private static final int SYS_NAS_PROCEDURE_ESM = 10;
    private static final int SYS_NAS_PROCEDURE_GMM_COMBINED = 5;
    private static final int SYS_NAS_PROCEDURE_GMM_NON_COMBINED = 3;
    private static final int SYS_NAS_PROCEDURE_MM = 1;
    private static final int SYS_NAS_PROCEDURE_MM_GMM_COMBINED = 6;
    private static final int SYS_NAS_PROCEDURE_NONE = -1;
    private static final int SYS_NAS_PROCEDURE_SM = 4;
    private static final int SYS_RAT_GSM_RADIO_ACCESS = 0;
    private static final int SYS_RAT_LTE_RADIO_ACCESS = 2;
    private static final int SYS_RAT_NONE = -1;
    private static final int SYS_RAT_NR5G_RADIO_ACCESS = 4;
    private static final int SYS_RAT_TDS_RADIO_ACCESS = 3;
    private static final int SYS_RAT_UMTS_RADIO_ACCESS = 1;
    private static final String TAG = "MtbHydraDataManager";
    public static final String UIM_INFO = "UIM_INFO";
    private static final byte ZERO_BYTE = 0;
    private static Context mContext = null;
    private static boolean mGetDataLogPrintFlag = false;
    private static boolean mIndDataLogPrintFlag = true;
    private static MtbHydraDataManager mInstance = null;
    private static int mModemStatsIntervalSec = 1;
    private static OemHookAgent mMtbHookAgent = null;
    private static boolean mParseDataLogPrintFlag = false;
    private static boolean mTmpLogPrintFlag = false;
    private static boolean mVersionShow = false;
    private String mCardSlotStatus;
    private String mCardStatus;
    private Registrant mHydraDataIndRegistrant;
    private static Map mMapDataDisplayAllValue = new HashMap();
    private static int mNumPhones = 0;
    private static ArrayList mArrCurGroupName = null;
    private static int mSub = 2;
    private static int mStatsClientId = 10;
    private static Handler mHandler = null;
    private static MtbDataDisplayBuffer[] mDataBuffer = null;
    private static MtbDataDisplayActivity mView = null;
    private static HashMap mMapDataGroupPref = null;
    private static ArrayList mArrayNonStopGroupName = null;
    private static HashMap mMapForId2Group = new HashMap();
    private static ArrayList mListForGroup = null;
    private boolean mIsBcRegistered = false;
    private final int EVENT_OEMHOOKCB_RESPONSE = 0;
    private byte[] oemCbResponse = null;
    private int oemCbPhoneId = -1;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.xiaomi.mtb.MtbHydraDataManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            MtbHydraDataManager.indDataLog("Received: " + action);
            if (action == null) {
                MtbHydraDataManager.log("action is null");
                return;
            }
            if (MtbHydraDataManager.QUALCOMM_HYDRA_DATA_IND_BC_NAME.equals(action)) {
                MtbHydraDataManager.log("QUALCOMM_HYDRA_DATA_IND_BC_NAME");
                if (MtbHydraDataManager.this.mHydraDataIndRegistrant == null) {
                    MtbHydraDataManager.log("mHydraDataIndRegistrant is null");
                    return;
                } else {
                    MtbHydraDataManager.this.mHydraDataIndRegistrant.notifyRegistrant(new AsyncResult((Object) null, intent.getByteArrayExtra(MtbHydraDataManager.BROADCAST_CONTEXT), (Throwable) null));
                    return;
                }
            }
            if ("android.intent.action.SIM_STATE_CHANGED".equals(action)) {
                MtbHydraDataManager.log("ACTION_SIM_STATE_CHANGED");
                MtbHydraDataManager.this.mCardStatus = ModemUtils.getSimStateString();
                MtbHydraDataManager.this.mCardSlotStatus = ModemUtils.getCardSlotStateString(MtbHydraDataManager.mMtbHookAgent);
                MtbHydraDataManager.this.parseAndUpdateData(MtbHydraDataManager.mArrCurGroupName, MtbHydraDataManager.mSub, false);
            }
        }
    };

    public class ModemStatsGroup {
        protected static final int MASK_0 = 0;
        protected static final int MASK_1 = 1;
        protected static final int MASK_2 = 2;
        protected static final int MASK_3 = 4;
        protected static final int MASK_4 = 8;
        protected static final int MASK_5 = 16;
        protected static final int MASK_6 = 32;
        protected static final int MASK_7 = 64;
        protected static final int MASK_8 = 128;
        public boolean mBShow;
        BigDecimal mBigDecimal;
        public boolean mBySub;
        ByteBuffer mData;
        Map mDataBackup;
        double mDouble;
        double mDouble1;
        float mFloat;
        float mFloat1;
        public String mGroupName;
        int mIVal;
        public boolean mInitStart;
        int mJVal;
        short mShort;
        short mShort1;
        String mStrFormat;
        String mStrFormat1;
        String mStrFormat2;
        String mStrFormat3;
        String mStrFormat4;
        String mStrFormat5;
        String mStrFormat6;
        String mStrFormat7;
        String mStrFormat8;
        String mStrVal;
        String mStrVal1;
        String mVersion;

        public ModemStatsGroup(String str, boolean z, boolean z2) {
            this.mBShow = true;
            this.mDataBackup = null;
            this.mData = null;
            this.mVersion = "";
            this.mStrFormat = "";
            this.mStrFormat1 = "";
            this.mStrFormat2 = "";
            this.mStrFormat3 = "";
            this.mStrFormat4 = "";
            this.mStrFormat5 = "";
            this.mStrFormat6 = "";
            this.mStrFormat7 = "";
            this.mStrFormat8 = "";
            this.mStrVal = "";
            this.mStrVal1 = "";
            this.mIVal = 0;
            this.mJVal = 0;
            this.mDouble = 0.0d;
            this.mDouble1 = 0.0d;
            this.mFloat = 0.0f;
            this.mFloat1 = 0.0f;
            this.mShort = (short) 0;
            this.mShort1 = (short) 0;
            this.mBigDecimal = null;
            this.mGroupName = str;
            this.mBySub = z;
            this.mInitStart = z2;
            MtbHydraDataManager.log("ModemStatsGroup Constructor, mGroupName = " + this.mGroupName + ", mBySub = " + this.mBySub + ", mInitStart = " + this.mInitStart);
        }

        protected void addGroupItem(int i2) {
            MtbHydraDataManager.this.addItem(i2, this.mGroupName);
        }

        protected void callMainUpdateProc(int i2) {
            MtbHydraDataManager.log("This group is not defined callMainUpdateProc function, mGroupName = " + this.mGroupName + ", sub = " + i2 + ", mBySub = " + this.mBySub + ", mInitStart = " + this.mInitStart + ", mBShow = " + this.mBShow);
        }

        protected String clearCache() {
            this.mDataBackup = null;
            this.mData = null;
            this.mVersion = "";
            this.mStrFormat1 = "";
            this.mStrFormat2 = "";
            this.mStrFormat3 = "";
            this.mStrFormat4 = "";
            this.mStrFormat5 = "";
            this.mStrFormat6 = "";
            this.mStrFormat7 = "";
            this.mStrFormat8 = "";
            this.mStrVal = "";
            this.mIVal = 0;
            this.mJVal = 0;
            this.mDouble = 0.0d;
            this.mDouble1 = 0.0d;
            this.mFloat = 0.0f;
            this.mFloat1 = 0.0f;
            this.mShort = (short) 0;
            this.mShort1 = (short) 0;
            return null;
        }

        protected String formatString(int i2) {
            MtbHydraDataManager.log("This group is not defined formatString function, mGroupName = " + this.mGroupName + ", sub = " + i2 + ", mBySub = " + this.mBySub + ", mInitStart = " + this.mInitStart);
            return null;
        }

        protected byte getByte(byte b2) {
            return ModemUtils.getByte(this.mData, b2);
        }

        protected double getDouble(double d2) {
            return ModemUtils.getDouble(this.mData, d2);
        }

        protected float getFloat(float f2) {
            return ModemUtils.getFloat(this.mData, f2);
        }

        public String getFormatString(int i2) {
            clearCache();
            return formatString(i2);
        }

        protected String getHexStringForUint8() {
            return ModemUtils.getStringForByte(this.mData, true, true);
        }

        protected int getInt(int i2) {
            return ModemUtils.getInt(this.mData, i2);
        }

        protected long getLong(long j2) {
            return ModemUtils.getLong(this.mData, j2);
        }

        protected short getShort(short s) {
            return ModemUtils.getShort(this.mData, s);
        }

        protected String getStringForBytes(int i2) {
            return ModemUtils.getStringForBytes(this.mData, i2);
        }

        protected String getStringForDouble() {
            return ModemUtils.getStringForDouble(this.mData);
        }

        protected String getStringForFloat() {
            return ModemUtils.getStringForFloat(this.mData);
        }

        protected String getStringForInt16() {
            return ModemUtils.getStringForShort(this.mData, false, false);
        }

        protected String getStringForInt32() {
            return ModemUtils.getStringForInt(this.mData, false, false);
        }

        protected String getStringForInt64() {
            return ModemUtils.getStringForLong(this.mData, false, false);
        }

        protected String getStringForInt8() {
            return ModemUtils.getStringForByte(this.mData, false, false);
        }

        protected String getStringForUint16() {
            return ModemUtils.getStringForShort(this.mData, true, false);
        }

        protected String getStringForUint32() {
            return ModemUtils.getStringForInt(this.mData, true, false);
        }

        protected String getStringForUint64() {
            return ModemUtils.getStringForLong(this.mData, true, false);
        }

        protected String getStringForUint8() {
            return ModemUtils.getStringForByte(this.mData, true, false);
        }

        protected String getStringForUint8Hex() {
            return ModemUtils.getStringForByte(this.mData, true, true);
        }

        protected String returnString(int i2) {
            String str = "";
            if ((i2 & 1) != 0) {
                str = "" + this.mStrFormat1;
            }
            if ((i2 & 2) != 0) {
                str = str + this.mStrFormat2;
            }
            if ((i2 & 4) != 0) {
                str = str + this.mStrFormat3;
            }
            if ((i2 & 8) != 0) {
                str = str + this.mStrFormat4;
            }
            if ((i2 & 16) != 0) {
                str = str + this.mStrFormat5;
            }
            if ((i2 & 32) != 0) {
                str = str + this.mStrFormat6;
            }
            if ((i2 & 64) != 0) {
                str = str + this.mStrFormat7;
            }
            if ((i2 & MASK_8) == 0) {
                return str;
            }
            return str + this.mStrFormat8;
        }

        protected void skipData(int i2) {
            ModemUtils.skipData(this.mData, i2);
        }

        protected void addGroupItem(int i2, boolean z) {
            MtbHydraDataManager.this.addItem(i2, this.mGroupName, z);
        }

        public ModemStatsGroup(String str, boolean z, boolean z2, boolean z3) {
            this.mDataBackup = null;
            this.mData = null;
            this.mVersion = "";
            this.mStrFormat = "";
            this.mStrFormat1 = "";
            this.mStrFormat2 = "";
            this.mStrFormat3 = "";
            this.mStrFormat4 = "";
            this.mStrFormat5 = "";
            this.mStrFormat6 = "";
            this.mStrFormat7 = "";
            this.mStrFormat8 = "";
            this.mStrVal = "";
            this.mStrVal1 = "";
            this.mIVal = 0;
            this.mJVal = 0;
            this.mDouble = 0.0d;
            this.mDouble1 = 0.0d;
            this.mFloat = 0.0f;
            this.mFloat1 = 0.0f;
            this.mShort = (short) 0;
            this.mShort1 = (short) 0;
            this.mBigDecimal = null;
            this.mGroupName = str;
            this.mBySub = z;
            this.mInitStart = z2;
            this.mBShow = z3;
            MtbHydraDataManager.log("ModemStatsGroup Constructor, mGroupName = " + this.mGroupName + ", mBySub = " + this.mBySub + ", mInitStart = " + this.mInitStart + ", mBShow = " + this.mBShow);
        }
    }

    public class ModemStatsGroupBasicCmnStatus extends ModemStatsGroup {
        public ModemStatsGroupBasicCmnStatus() {
            super(MtbHydraDataManager.BASIC_CMN_STATUS, false, false);
            MtbHydraDataManager.log("ModemStatsGroupBasicCmnStatus Constructor");
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("  %-17.17s %-17.17s %-17.17s", "OPERATION_MODE", "CARD_STATUS", "CARD_SLOT_STATUS"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            String str = this.mStrFormat1 + String.format("\n   %-17.17s %-17.17s %-17.17s", ModemUtils.getOperateModeString(MtbHydraDataManager.mMtbHookAgent), MtbHydraDataManager.this.mCardStatus, MtbHydraDataManager.this.mCardSlotStatus);
            this.mStrFormat1 = str;
            return str;
        }
    }

    public class ModemStatsGroupBasicSubStatus extends ModemStatsGroup {
        public ModemStatsGroupBasicSubStatus() {
            super(MtbHydraDataManager.BASIC_SUB_STATUS, true, false);
            MtbHydraDataManager.log("ModemStatsGroupBasicSubStatus Constructor");
            addGroupItem(45, true);
            addGroupItem(47, true);
            addGroupItem(46);
            addGroupItem(57);
            addGroupItem(81);
            addGroupItem(80);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor("  Network Type   : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.getNetworkTypeString(MtbHydraDataManager.getNetworkType(i2));
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n  Servcie Status : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.this.getNetworkStatusString(MtbHydraDataManager.getNetworkStatus(i2));
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n  Server  Domain : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.this.getSrvDomainString(MtbHydraDataManager.getSrvDomain(i2));
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n  IMS Status     : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.mDataBuffer[i2].mImsState;
            if (!"".equals(MtbHydraDataManager.mDataBuffer[i2].mDefIpAddr)) {
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n  INTERNET IP    : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.mDataBuffer[i2].mDefIpAddr;
            }
            if (!"-".equals(MtbHydraDataManager.this.getRlfTypeString(MtbHydraDataManager.getRlfType(i2)))) {
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n  RLF Type       : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.this.getRlfTypeString(MtbHydraDataManager.getRlfType(i2));
            }
            this.mData = MtbHydraDataManager.this.getData(46, i2);
            int i3 = 0;
            byte b2 = getByte((byte) 0);
            this.mIVal = b2;
            int iMin = ModemUtils.min(b2, 2);
            this.mIVal = iMin;
            if (iMin <= 0) {
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n  Reject INFO    : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + "No reject info";
            } else {
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n  Reject INFO    : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + "Reject Happpened";
            }
            int i4 = this.mIVal;
            if (1 != i4) {
                if (2 == i4) {
                    while (true) {
                        this.mJVal = i3;
                        if (this.mJVal >= 2) {
                            break;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.mStrFormat1);
                        sb.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].reject_rat   : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb.append(MtbHydraDataManager.this.getAccessRatString(getByte((byte) -1)));
                        this.mStrFormat1 = sb.toString();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(this.mStrFormat1);
                        sb2.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].reject_domain: ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb2.append(MtbHydraDataManager.this.getSrvDomainString(getByte((byte) -1)));
                        this.mStrFormat1 = sb2.toString();
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(this.mStrFormat1);
                        sb3.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].reject_cause : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb3.append(MtbHydraDataManager.this.getEmmCauseString(getByte((byte) -1)));
                        this.mStrFormat1 = sb3.toString();
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(this.mStrFormat2);
                        sb4.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].nas_procedure: ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb4.append(MtbHydraDataManager.this.getNasProcedureString(getByte((byte) -1)));
                        this.mStrFormat2 = sb4.toString();
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append(this.mStrFormat1);
                        sb5.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].plmn         : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb5.append(getStringForUint8());
                        sb5.append(getStringForUint8());
                        sb5.append(getStringForUint8());
                        this.mStrFormat1 = sb5.toString();
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append(this.mStrFormat2);
                        sb6.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].primary_plmn : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb6.append(getStringForUint8());
                        sb6.append(getStringForUint8());
                        sb6.append(getStringForUint8());
                        this.mStrFormat2 = sb6.toString();
                        StringBuilder sb7 = new StringBuilder();
                        sb7.append(this.mStrFormat2);
                        sb7.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].lac_or_tac   : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb7.append(getStringForUint16());
                        this.mStrFormat2 = sb7.toString();
                        StringBuilder sb8 = new StringBuilder();
                        sb8.append(this.mStrFormat2);
                        sb8.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].tac_5g       : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb8.append(getStringForUint32());
                        this.mStrFormat2 = sb8.toString();
                        StringBuilder sb9 = new StringBuilder();
                        sb9.append(this.mStrFormat2);
                        sb9.append(ModemUtils.toHtmlFormatColor("\n   stacks[" + this.mJVal + "].csg_id       : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME));
                        sb9.append(getStringForUint32());
                        this.mStrFormat2 = sb9.toString();
                        i3 = this.mJVal + 1;
                    }
                }
            } else {
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n   reject_rat    : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.this.getAccessRatString(getByte((byte) -1));
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n   reject_domain : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.this.getSrvDomainString(getByte((byte) -1));
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n   reject_cause  : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.this.getEmmCauseString(getByte((byte) -1));
                this.mStrFormat2 += ModemUtils.toHtmlFormatColor("\n   nas_procedure : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + MtbHydraDataManager.this.getNasProcedureString(getByte((byte) -1));
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor("\n   plmn          : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + getHexStringForUint8() + getHexStringForUint8() + getHexStringForUint8();
                this.mStrFormat2 += ModemUtils.toHtmlFormatColor("\n   primary_plmn  : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + getHexStringForUint8() + getHexStringForUint8() + getHexStringForUint8();
                this.mStrFormat2 += ModemUtils.toHtmlFormatColor("\n   lac_or_tac    : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + getStringForUint16();
                this.mStrFormat2 += ModemUtils.toHtmlFormatColor("\n   tac_5g        : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + getStringForUint32();
                this.mStrFormat2 += ModemUtils.toHtmlFormatColor("\n   csg_id        : ", MtbHydraDataManager.COLOR_TXT_SUB_NAME) + getStringForUint32();
            }
            return this.mStrFormat1;
        }
    }

    public class ModemStatsGroupCall extends ModemStatsGroup {
        public ModemStatsGroupCall() {
            super(MtbHydraDataManager.CALL_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupCall Constructor");
            addGroupItem(58);
            addGroupItem(59, true);
            addGroupItem(60, true);
            addGroupItem(61, true);
            addGroupItem(62, true);
            addGroupItem(63, true);
            addGroupItem(64, true);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            this.mData = MtbHydraDataManager.this.getData(58, i2);
            this.mStrFormat1 += "callid: " + getStringForUint16();
            this.mIVal = ModemUtils.mid(getShort((short) 0), 0, 48);
            this.mStrFormat1 += "\nnumber: " + getStringForBytes(this.mIVal);
            skipData(48 - this.mIVal);
            this.mStrFormat1 += "\ncall_state: " + MtbHydraDataManager.this.getCallStateString(getInt(-1));
            this.mStrFormat1 += "\ncall_type: " + MtbHydraDataManager.this.getCallTypeString(getInt(-1));
            this.mStrFormat1 += "\nsys_mode: " + MtbHydraDataManager.getNetworkTypeString(getInt(-1));
            this.mStrFormat1 += "\nspeech_codec_type: " + MtbHydraDataManager.this.getCallSpeechCodecString(getInt(-1));
            this.mStrFormat1 += "\ndirection: " + MtbHydraDataManager.this.getCallDirectionString(getInt(-1));
            this.mStrFormat1 += "\ncall_connect_time: " + getStringForUint64();
            this.mStrFormat1 += "\ncall_end_time: " + getStringForUint64();
            this.mStrFormat1 += "\nvoice_ho_type: " + MtbHydraDataManager.this.getCallHoTypeString(getInt(-1));
            this.mStrFormat1 += "\ncall_end_callid: " + getStringForUint16();
            this.mStrFormat1 += "\ncall_end_error_code: " + getStringForUint32();
            this.mStrFormat1 += "\ncall_end_term_code: " + getStringForUint16();
            this.mData = MtbHydraDataManager.this.getData(59, i2);
            this.mStrFormat1 += "\nims_sip_sesstion_failure: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(60, i2);
            this.mStrFormat1 += "\nlte_rrc_irat_ho_from_eutran_failure: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(61, i2);
            this.mStrFormat1 += "\nims_sip_session_ringing: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(62, i2);
            this.mStrFormat1 += "\ncm_call_redial: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(63, i2);
            this.mStrFormat1 += "\ngsm_call_drop: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(64, i2);
            String str = this.mStrFormat1 + "\nwcdma_call_drop: " + getStringForUint8();
            this.mStrFormat1 = str;
            return str;
        }
    }

    public class ModemStatsGroupData extends ModemStatsGroup {
        public ModemStatsGroupData() {
            super(MtbHydraDataManager.DATA_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupData Constructor");
            addGroupItem(69);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            this.mData = MtbHydraDataManager.this.getData(69, i2);
            this.mStrFormat1 += "active_rat: " + getStringForUint8();
            this.mStrFormat1 += "\ndetach_type: " + getStringForUint8();
            this.mStrFormat1 += "\nstatus: " + getStringForUint8();
            this.mStrFormat1 += "\nend_reason: " + getStringForUint8();
            String str = this.mStrFormat1 + "\ndetach_cause: " + getStringForUint8();
            this.mStrFormat1 = str;
            return str;
        }
    }

    public class ModemStatsGroupGps extends ModemStatsGroup {
        public ModemStatsGroupGps() {
            super(MtbHydraDataManager.GPS_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupGps Constructor");
            addGroupItem(73);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            this.mData = MtbHydraDataManager.this.getData(73, i2);
            this.mStrFormat1 += "latitude_valid: " + getStringForUint8();
            this.mStrFormat1 += "\nlatitude: " + getStringForDouble();
            this.mStrFormat1 += "\nlongitude_valid: " + getStringForUint8();
            this.mStrFormat1 += "\nlongitude: " + getStringForDouble();
            this.mStrFormat1 += "\nspeed_horizontal_valid: " + getStringForUint8();
            this.mStrFormat1 += "\nspeed_horizontal: " + getStringForFloat();
            this.mStrFormat1 += "\ntimestamp_valid: " + getStringForUint8();
            String str = this.mStrFormat1 + "\ntimestamp: " + getStringForUint64();
            this.mStrFormat1 = str;
            return str;
        }
    }

    public class ModemStatsGroupLteCa extends ModemStatsGroup {
        public ModemStatsGroupLteCa() {
            super(MtbHydraDataManager.LTE_CA_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupLteCa Constructor");
            addGroupItem(35);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            if (9 != MtbHydraDataManager.getNetworkType(i2)) {
                return MtbHydraDataManager.DATA_GROUP_NO_SHOW_FLAG;
            }
            this.mData = MtbHydraDataManager.this.getData(35, i2);
            this.mStrFormat1 += "srv_earfcn: " + getStringForUint32();
            this.mStrFormat1 += "\nsrv_cell_id: " + getStringForUint32();
            this.mStrFormat1 += "\nsrv_freq_band_ind: " + getStringForUint8();
            this.mStrFormat1 += "\nsrv_dl_bandwidth: " + MtbHydraDataManager.this.getLteBandWidthIdxString(getByte((byte) -1));
            this.mStrFormat1 += "\nsrv_rssnr: " + getStringForInt8();
            this.mStrFormat1 += "\nscell_pci: " + getStringForUint16();
            this.mStrFormat1 += "\nscell_rsrp: " + getStringForInt16();
            this.mStrFormat1 += "\nscell_rsrq: " + getStringForInt16();
            this.mStrFormat1 += "\nscell_rssi: " + getStringForInt16();
            this.mStrFormat1 += "\nscell_sinr: " + getStringForInt16();
            this.mStrFormat2 += "\nscell_srxlev: " + getStringForInt16();
            this.mStrFormat2 += "\nscell_squal: " + getStringForInt16();
            this.mStrFormat2 += "\nscell_rank: " + getStringForInt16();
            this.mStrFormat2 += "\nscell_deact_timer: " + getStringForUint32();
            this.mStrFormat2 += "\nca_activated: " + getStringForUint8();
            return this.mStrFormat1;
        }
    }

    public class ModemStatsGroupLteNbr extends ModemStatsGroup {
        public ModemStatsGroupLteNbr() {
            super(MtbHydraDataManager.LTE_NBR_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupLteNbr Constructor");
            addGroupItem(31);
            addGroupItem(32);
            addGroupItem(34);
            addGroupItem(33);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            if (9 != MtbHydraDataManager.getNetworkType(i2)) {
                return MtbHydraDataManager.DATA_GROUP_NO_SHOW_FLAG;
            }
            this.mData = MtbHydraDataManager.this.getData(31, i2);
            this.mStrFormat1 += "srv_earfcn: " + getStringForUint32();
            this.mStrFormat1 += "\nsrv_cell_id: " + getStringForUint16();
            byte b2 = 0;
            this.mIVal = getByte((byte) 0);
            this.mStrFormat1 += "\nnum_nbr_lte_intra_cells: " + this.mIVal;
            this.mIVal = ModemUtils.min(this.mIVal, 8);
            int i9 = 0;
            while (true) {
                i3 = this.mIVal;
                if (i9 >= i3) {
                    break;
                }
                this.mStrFormat1 += "\nnbr_lte_intra_cells[" + i9 + "].pci: " + getStringForUint16();
                String stringForInt16 = getStringForInt16();
                this.mStrFormat1 += "\nnbr_lte_intra_cells[" + i9 + "].rsrp: " + getStringForInt16();
                this.mStrFormat1 += "\nnbr_lte_intra_cells[" + i9 + "].rsrq: " + stringForInt16;
                this.mStrFormat1 += "\nnbr_lte_intra_cells[" + i9 + "].rssi: " + getStringForInt16();
                this.mStrFormat1 += "\nnbr_lte_intra_cells[" + i9 + "].sinr: " + getStringForInt16();
                this.mStrFormat1 += "\nnbr_lte_intra_cells[" + i9 + "].srxlev: " + getStringForInt16();
                i9++;
            }
            skipData((8 - i3) * 12);
            this.mStrFormat1 += "\ncell_resel_priority: " + getStringForUint8();
            this.mStrFormat1 += "\ns_non_intra_search: " + getStringForUint8();
            this.mStrFormat1 += "\nthresh_serving_low: " + getStringForUint8();
            this.mStrFormat1 += "\ns_intra_search: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(32, i2);
            this.mIVal = getByte((byte) 0);
            this.mStrFormat1 += "\nnum_nbr_lte_inter_freqs: " + this.mIVal;
            char c2 = 2;
            this.mIVal = ModemUtils.min(this.mIVal, 2);
            int i10 = 0;
            while (true) {
                i4 = this.mIVal;
                if (i10 >= i4) {
                    break;
                }
                this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].earfcn: " + getStringForUint32();
                this.mJVal = getByte(b2);
                this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].num_cells: " + this.mJVal;
                this.mJVal = ModemUtils.min(this.mJVal, 8);
                int i11 = b2;
                while (true) {
                    i8 = this.mJVal;
                    if (i11 < i8) {
                        this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].cell[" + i11 + "].pci: " + getStringForUint16();
                        String stringForInt17 = getStringForInt16();
                        this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].cell[" + i11 + "].rsrp: " + getStringForInt16();
                        this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].cell[" + i11 + "].rsrq: " + stringForInt17;
                        this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].cell[" + i11 + "].rssi: " + getStringForInt16();
                        this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].cell[" + i11 + "].sinr: " + getStringForInt16();
                        this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].cell[" + i11 + "].srxlev: " + getStringForInt16();
                        i11++;
                    }
                }
                skipData((8 - i8) * 12);
                this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].threshX_low: " + getStringForUint8();
                this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].threshX_high: " + getStringForUint8();
                this.mStrFormat1 += "\nnbr_lte_inter_freqs[" + i10 + "].cell_resel_priority: " + getStringForUint8();
                i10++;
                b2 = 0;
                c2 = 2;
            }
            skipData((2 - i4) * OemHookAgent.EVENT_OEMHOOK_RILD_TEST);
            this.mData = MtbHydraDataManager.this.getData(34, i2);
            this.mIVal = getByte((byte) 0);
            this.mStrFormat1 += "\nnum_nbr_wcdma_freqs: " + this.mIVal;
            this.mIVal = ModemUtils.min(this.mIVal, 2);
            int i12 = 0;
            while (true) {
                i5 = this.mIVal;
                if (i12 >= i5) {
                    break;
                }
                this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].uarfcn: " + getStringForUint16();
                this.mJVal = getByte((byte) 0);
                this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].num_cells: " + this.mJVal;
                this.mJVal = ModemUtils.min(this.mJVal, 8);
                int i13 = 0;
                while (true) {
                    i7 = this.mJVal;
                    if (i13 < i7) {
                        this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].cell[" + i13 + "].psc: " + getStringForUint16();
                        this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].cell[" + i13 + "].cpich_rscp: " + getStringForInt16();
                        this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].cell[" + i13 + "].cpich_ecno: " + getStringForInt16();
                        this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].cell[" + i13 + "].srxlev: " + getStringForInt16();
                        i13++;
                    }
                }
                skipData((8 - i7) * 8);
                this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].cell_resel_priority: " + getStringForUint8();
                this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].thresh_Xhigh: " + getStringForUint16();
                this.mStrFormat1 += "\nnbr_wcdma_freqs[" + i12 + "].thresh_Xlow: " + getStringForUint16();
                i12++;
            }
            skipData((2 - i5) * 72);
            this.mData = MtbHydraDataManager.this.getData(33, i2);
            this.mIVal = getByte((byte) 0);
            this.mStrFormat1 += "\nnum_nbr_gsm_freq_groups: " + this.mIVal;
            this.mIVal = ModemUtils.min(this.mIVal, 2);
            int i14 = 0;
            while (true) {
                int i15 = this.mIVal;
                if (i14 >= i15) {
                    skipData((2 - i15) * 69);
                    return this.mStrFormat1;
                }
                this.mJVal = getByte((byte) 0);
                this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].num_arfcns: " + this.mJVal;
                this.mJVal = ModemUtils.min(this.mJVal, 8);
                int i16 = 0;
                while (true) {
                    i6 = this.mJVal;
                    if (i16 < i6) {
                        this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].arfcn[" + i16 + "].arfcn: " + getStringForUint16();
                        this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].arfcn[" + i16 + "].band_1900: " + getStringForUint8();
                        this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].arfcn[" + i16 + "].bsic_id: " + getStringForUint8();
                        this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].arfcn[" + i16 + "].rssi: " + getStringForInt16();
                        this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].arfcn[" + i16 + "].srxlev: " + getStringForInt16();
                        i16++;
                    }
                }
                skipData((8 - i6) * 8);
                this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].cell_resel_priority: " + getStringForUint8();
                this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].thresh_gsm_high: " + getStringForUint8();
                this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].thresh_gsm_low: " + getStringForUint8();
                this.mStrFormat1 += "\nnbr_gsm_freq_groups[" + i14 + "].ncc_permitted: " + getStringForUint8();
                i14++;
            }
        }
    }

    public class ModemStatsGroupLteSrvBasic extends ModemStatsGroup {
        public ModemStatsGroupLteSrvBasic(String str) {
            super(str, true, false);
            MtbHydraDataManager.log("ModemStatsGroupLteSrvBasic Constructor, groupName = " + str);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            return formatString(i2, 1);
        }

        /* JADX WARN: Code duplicated, block: B:70:0x0a52  */
        /* JADX WARN: Code duplicated, block: B:73:0x0b32  */
        /* JADX WARN: Code duplicated, block: B:74:0x0b36  */
        /* JADX WARN: Code duplicated, block: B:77:0x0b4e  */
        /* JADX WARN: Code duplicated, block: B:80:0x0ba7  */
        /* JADX WARN: Code duplicated, block: B:83:0x0bd1  */
        /* JADX WARN: Code duplicated, block: B:84:0x0be5  */
        /* JADX WARN: Instruction removed from duplicated block: B:70:0x0a52, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:77:0x0b4e, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:80:0x0ba7, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:83:0x0bd1, please report this as an issue */
        protected String formatString(int i2, int i3) {
            short s;
            String stringForUint16;
            String str;
            String str2;
            if (9 != MtbHydraDataManager.getNetworkType(i2)) {
                return MtbHydraDataManager.DATA_GROUP_NO_SHOW_FLAG;
            }
            this.mData = MtbHydraDataManager.this.getData(30, i2);
            this.mDataBackup = MtbHydraDataManager.this.getDataBackup(30, i2);
            String stringForUint17 = getStringForUint16();
            String stringForUint18 = getStringForUint16();
            String enbCellString = ModemUtils.getEnbCellString(getInt(0));
            String stringForUint19 = getStringForUint16();
            String stringForUint32 = getStringForUint32();
            String stringForUint110 = getStringForUint16();
            String stringForInt16 = getStringForInt16();
            String stringForInt17 = getStringForInt16();
            String stringForInt18 = getStringForInt16();
            String stringForUint8 = getStringForUint8();
            String str3 = "B" + getStringForUint8();
            String str4 = MtbHydraDataManager.this.getLteBandWidthIdxString(getByte((byte) -1)) + "/" + MtbHydraDataManager.this.getLteBandWidthIdxString(getByte((byte) -1));
            this.mStrFormat2 += "\nnum_meas: " + getStringForUint8();
            this.mStrFormat2 += "\npad: " + getStringForUint8();
            this.mStrFormat2 += "\nstale_measurements: " + getStringForUint8();
            this.mStrFormat2 += "\nidle_drx_cycle_len: " + getStringForUint16();
            this.mStrFormat2 += "\ncdrx_short_cycle_len: " + getStringForUint16();
            this.mStrFormat2 += "\ncdrx_long_cycle_len: " + getStringForUint16();
            this.mStrFormat2 += "\npci: " + getStringForUint16();
            this.mStrFormat2 += "\nrsrp_10xdb: " + getStringForInt16();
            this.mStrFormat2 += "\nrsrq_10xdb: " + getStringForInt16();
            this.mStrFormat2 += "\nrssi_10xdb: " + getStringForInt16();
            this.mStrFormat2 += "\nsinr_10xdb: " + getStringForInt16();
            this.mStrFormat2 += "\nsrxlev: " + getStringForInt16();
            this.mStrFormat2 += "\nsqual: " + getStringForInt16();
            this.mStrFormat2 += "\nrank: " + getStringForInt16();
            String lteIdleOrConnectString = MtbHydraDataManager.this.getLteIdleOrConnectString(getByte((byte) 0));
            this.mStrFormat2 += "\nlte_ca_scell_present: " + getStringForUint8();
            this.mStrFormat2 += "\nra_rnti: " + getStringForUint16();
            this.mStrFormat2 += "\nc_rnti: " + getStringForUint16();
            this.mStrFormat2 += "\ncqi_wb: " + getStringForUint16();
            this.mStrFormat2 += "\nenb_num_tx_antenna: " + getStringForUint8();
            this.mStrFormat2 += "\ndrx_enable: " + getStringForUint8();
            this.mStrFormat2 += "\non_duration_timer: " + getStringForUint16();
            this.mStrFormat2 += "\ninactivity_timer: " + getStringForUint16();
            this.mStrFormat2 += "\nretx_timer: " + getStringForUint16();
            this.mStrFormat2 += "\nlong_drx_cycle: " + getStringForUint16();
            this.mStrFormat2 += "\noffset: " + getStringForUint32();
            this.mStrFormat2 += "\nshort_drx_cycle_enable: " + getStringForUint8();
            this.mStrFormat2 += "\nshort_drx_cycle: " + getStringForUint16();
            this.mStrFormat2 += "\nshort_drx_cycle_timer: " + getStringForUint32();
            this.mStrFormat2 += "\ncqi_cfg_enable: " + getStringForUint8();
            this.mStrFormat2 += "\ncqi_reporting_mode_aperiodic_enable: " + getStringForUint8();
            this.mStrFormat2 += "\ncqi_reporting_mode_aperiodic: " + getStringForUint8();
            this.mStrFormat2 += "\nnom_pdsch_rs_epre_offset: " + getStringForInt8();
            this.mStrFormat2 += "\ncqi_rel9_param_present: " + getStringForUint8();
            this.mStrFormat2 += "\ncqi_mask_enable: " + getStringForUint8();
            this.mStrFormat2 += "\ntransmission_mode: " + getStringForUint8();
            this.mStrFormat2 += "\ncodebook_subset_restriction: " + getStringForUint64();
            this.mStrFormat2 += "\ntx_antenna_selection_enabled: " + getStringForUint8();
            this.mStrFormat2 += "\ntx_antenna_selection_ctrl: " + getStringForUint8();
            String stringForUint111 = getStringForUint16();
            this.mStrFormat2 += "\nnb: " + getStringForUint8();
            this.mStrFormat2 += "\nue_id: " + getStringForUint16();
            this.mStrFormat2 += "\ntiming_advance: " + getStringForUint32();
            this.mStrFormat2 += "\ndoppler_measurement: " + getStringForUint16();
            this.mStrFormat2 += "\ntrm_available: " + getStringForUint8();
            String str5 = getStringForInt16() + "/" + getStringForInt16();
            String str6 = getStringForInt16() + "/" + getStringForInt8();
            this.mData = MtbHydraDataManager.this.getData(72, i2);
            this.mDataBackup = MtbHydraDataManager.this.getDataBackup(72, i2);
            this.mStrFormat2 += "\ntx_power_is_in_traffic: " + getStringForUint8();
            this.mStrVal = getStringForInt32();
            StringBuilder sb = new StringBuilder();
            sb.append(this.mStrFormat2);
            sb.append("\n");
            MtbHydraDataManager mtbHydraDataManager = MtbHydraDataManager.this;
            Map map = this.mDataBackup;
            String str7 = this.mStrVal;
            sb.append(mtbHydraDataManager.getString(map, "tx_pwr       ", str7, !"-3276".equals(str7)));
            this.mStrFormat2 = sb.toString();
            this.mStrVal = getStringForUint8();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mStrFormat2);
            sb2.append("\n");
            MtbHydraDataManager mtbHydraDataManager2 = MtbHydraDataManager.this;
            Map map2 = this.mDataBackup;
            String str8 = this.mStrVal;
            sb2.append(mtbHydraDataManager2.getString(map2, "tx_power_pa_gain_state", str8, !"0".equals(str8)));
            this.mStrFormat2 = sb2.toString();
            ByteBuffer byteBuffer = this.mData;
            if (byteBuffer != null) {
                int i4 = byteBuffer.getInt() / 10;
                this.mIVal = i4;
                this.mStrVal = ModemUtils.getString(i4, false, false);
            } else {
                this.mStrVal = "-";
            }
            MtbHydraDataManager mtbHydraDataManager3 = MtbHydraDataManager.this;
            Map map3 = this.mDataBackup;
            String str9 = this.mStrVal;
            mtbHydraDataManager3.getString(map3, "TX_PRACH", str9, !"-3276".equals(str9));
            String str10 = this.mStrVal;
            ByteBuffer byteBuffer2 = this.mData;
            if (byteBuffer2 != null) {
                int i5 = byteBuffer2.getInt() / 10;
                this.mIVal = i5;
                this.mStrVal = ModemUtils.getString(i5, false, false);
            } else {
                this.mStrVal = "-";
            }
            MtbHydraDataManager mtbHydraDataManager4 = MtbHydraDataManager.this;
            Map map4 = this.mDataBackup;
            String str11 = this.mStrVal;
            mtbHydraDataManager4.getString(map4, "TX_SRS", str11, !"-3276".equals(str11));
            String str12 = this.mStrVal;
            ByteBuffer byteBuffer3 = this.mData;
            if (byteBuffer3 != null) {
                int i6 = byteBuffer3.getInt() / 10;
                this.mIVal = i6;
                this.mStrVal = ModemUtils.getString(i6, false, false);
            } else {
                this.mStrVal = "-";
            }
            MtbHydraDataManager mtbHydraDataManager5 = MtbHydraDataManager.this;
            Map map5 = this.mDataBackup;
            String str13 = this.mStrVal;
            mtbHydraDataManager5.getString(map5, "TX_PUCCH", str13, !"-3276".equals(str13));
            String str14 = this.mStrVal;
            ByteBuffer byteBuffer4 = this.mData;
            if (byteBuffer4 != null) {
                int i7 = byteBuffer4.getInt() / 10;
                this.mIVal = i7;
                this.mStrVal = ModemUtils.getString(i7, false, false);
            } else {
                this.mStrVal = "-";
            }
            MtbHydraDataManager mtbHydraDataManager6 = MtbHydraDataManager.this;
            Map map6 = this.mDataBackup;
            String str15 = this.mStrVal;
            mtbHydraDataManager6.getString(map6, "TX_PUSCH", str15, !"-3276".equals(str15));
            String str16 = this.mStrVal;
            this.mData = MtbHydraDataManager.this.getData(36, i2);
            this.mStrFormat2 += "\nrrc_cause: " + getStringForUint8();
            this.mStrFormat2 += "\nrrc_state: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(82, i2);
            this.mDataBackup = MtbHydraDataManager.this.getDataBackup(82, i2);
            this.mVersion = getStringForUint8();
            String str17 = "";
            if (MtbHydraDataManager.mVersionShow) {
                str17 = "\n**version: " + this.mVersion;
            }
            this.mVersion = getStringForUint8();
            if (MtbHydraDataManager.mVersionShow) {
                str17 = str17 + "\n**version_ssmr: " + this.mVersion;
            }
            this.mStrFormat2 += "\nearfcn: " + getStringForUint32();
            this.mStrFormat2 += "\npci: " + getStringForUint16();
            this.mStrFormat2 += "\nis_srv_cell: " + getStringForUint8();
            this.mShort = getShort((short) 0);
            this.mFloat = getFloat(MtbHydraDataManager.LTE_RSRP_INI);
            this.mStrFormat2 += "\nrsrp_rx0: " + this.mFloat;
            this.mStrFormat2 += "\nrsrq_rx0: " + getStringForFloat();
            this.mStrFormat2 += "\nrssi_rx0: " + getStringForFloat();
            this.mStrFormat2 += "\nsnr_rx0: " + getStringForFloat();
            this.mFloat1 = getFloat(MtbHydraDataManager.LTE_RSRP_INI);
            this.mStrFormat2 += "\nrsrp_rx1: " + this.mFloat1;
            this.mStrFormat2 += "\nrsrq_rx1: " + getStringForFloat();
            this.mStrFormat2 += "\nrssi_rx1: " + getStringForFloat();
            this.mStrFormat2 += "\nsnr_rx1: " + getStringForFloat();
            this.mStrFormat2 += "\nrsrp_rx2: " + getStringForFloat();
            this.mStrFormat2 += "\nrsrq_rx2: " + getStringForFloat();
            this.mStrFormat2 += "\nrssi_rx2: " + getStringForFloat();
            this.mStrFormat2 += "\nsnr_rx2: " + getStringForFloat();
            this.mStrFormat2 += "\nrsrp_rx3: " + getStringForFloat();
            this.mStrFormat2 += "\nrsrq_rx3: " + getStringForFloat();
            this.mStrFormat2 += "\nrssi_rx3: " + getStringForFloat();
            this.mStrFormat2 += "\nsnr_rx3: " + getStringForFloat();
            short s2 = this.mShort;
            if (1 != s2) {
                if (2 == s2 || 4 == s2 || 8 == s2) {
                    s = 1;
                } else if (3 == s2 || 5 == s2 || 6 == s2 || 9 == s2 || 10 == s2 || 12 == s2) {
                    this.mShort = (short) 2;
                } else if (7 == s2 || 11 == s2 || 13 == s2 || 14 == s2) {
                    this.mShort = (short) 3;
                } else if (15 == s2) {
                    this.mShort = (short) 4;
                } else {
                    this.mShort = (short) 0;
                }
                this.mFloat -= this.mFloat1;
                BigDecimal bigDecimal = new BigDecimal(this.mFloat);
                this.mBigDecimal = bigDecimal;
                this.mDouble = bigDecimal.setScale(1, 4).doubleValue();
                String str18 = ((int) this.mShort) + "/" + this.mDouble;
                this.mStrFormat1 += str17;
                this.mData = MtbHydraDataManager.this.getData(83, i2);
                this.mDataBackup = MtbHydraDataManager.this.getDataBackup(83, i2);
                this.mVersion = getStringForUint8();
                if (MtbHydraDataManager.mVersionShow) {
                    this.mStrFormat1 += "\n**version: " + this.mVersion;
                }
                this.mStrVal = getStringForInt8();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(this.mStrFormat2);
                sb3.append("\n");
                MtbHydraDataManager mtbHydraDataManager7 = MtbHydraDataManager.this;
                Map map7 = this.mDataBackup;
                String str19 = this.mStrVal;
                sb3.append(mtbHydraDataManager7.getString(map7, "pucch_tx_power", str19, !"255".equals(str19)));
                this.mStrFormat2 = sb3.toString();
                this.mStrVal = getStringForInt8();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(this.mStrFormat2);
                sb4.append("\n");
                MtbHydraDataManager mtbHydraDataManager8 = MtbHydraDataManager.this;
                Map map8 = this.mDataBackup;
                String str20 = this.mStrVal;
                sb4.append(mtbHydraDataManager8.getString(map8, "pusch_tx_power", str20, !"255".equals(str20)));
                this.mStrFormat2 = sb4.toString();
                String lteModulationString = MtbHydraDataManager.this.getLteModulationString(getByte((byte) -1));
                this.mStrVal = lteModulationString;
                MtbHydraDataManager.this.getString(this.mDataBackup, "PUSCH_MOD", lteModulationString, !MtbHydraDataManager.MODEM_STATS_DATA_INVALID.equals(lteModulationString));
                String str21 = this.mStrVal;
                this.mStrVal = getStringForUint16();
                StringBuilder sb5 = new StringBuilder();
                sb5.append(this.mStrFormat2);
                sb5.append("\n");
                MtbHydraDataManager mtbHydraDataManager9 = MtbHydraDataManager.this;
                Map map9 = this.mDataBackup;
                String str22 = this.mStrVal;
                sb5.append(mtbHydraDataManager9.getString(map9, "pusch_num_res_blocks", str22, !ModemUtils.PROP_MIRIL_PRINT_ON.equals(str22)));
                this.mStrFormat2 = sb5.toString();
                stringForUint16 = getStringForUint16();
                this.mStrVal = stringForUint16;
                if (ModemUtils.PROP_MIRIL_PRINT_ON.equals(stringForUint16)) {
                    str = "-";
                } else {
                    str = this.mStrVal;
                }
                this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B173_DIAG_LOG_LTE_PDSCH_STAT_INDICATION, i2);
                this.mVersion = getStringForUint8();
                if (MtbHydraDataManager.mVersionShow) {
                    this.mStrFormat1 += "\n**version: " + this.mVersion;
                }
                String str23 = getStringForUint8() + "/" + getStringForUint8() + "/" + MtbHydraDataManager.this.getLtePdschModulationString(getByte((byte) -1));
                this.mData = MtbHydraDataManager.this.getData(84, i2);
                this.mVersion = getStringForUint8();
                if (MtbHydraDataManager.mVersionShow) {
                    this.mStrFormat1 += "\n**version: " + this.mVersion;
                }
                this.mIVal = getByte((byte) -1) + 1;
                StringBuilder sb6 = new StringBuilder();
                if (this.mIVal > 0) {
                    str2 = "Rank" + this.mIVal;
                } else {
                    str2 = "-";
                }
                sb6.append(str2);
                sb6.append("/");
                sb6.append(getStringForUint8());
                String string = sb6.toString();
                this.mStrFormat2 += "\ncqi_cw1      : " + getStringForUint8();
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("  %-3.3s %-3.3s %-10.10s %-5.5s %-6.6s %-4.4s %-4.4s %-4.4s %-4.4s %-3.3s", "MCC", "MNC", "eNB/Cell", "TAC", "EARFCN", "PCI", "RSRP", "RSRQ", "RSSI", "SNR"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
                this.mStrFormat1 += String.format("\n  %-3.3s %-3.3s %-10.10s %-5.5s %-6.6s %-4.4s %-4.4s %-4.4s %-4.4s %-3.3s", stringForUint17, stringForUint18, enbCellString, stringForUint19, stringForUint32, stringForUint110, stringForInt16, stringForInt17, stringForInt18, stringForUint8);
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-17.17s %-17.17s", "AVG_RSRP/RSRQ", "AVG_RSSI/SNR", "ANT/Diff"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
                this.mStrFormat1 += String.format("\n   %-17.17s %-17.17s %-17.17s", str5, str6, str18);
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-17.17s %-17.17s", "BAND/PAGE_CYC", "RI/CQI", "RRC_STATE"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
                this.mStrFormat1 += String.format("\n   %-17.17s %-17.17s %-17.17s", str3 + "/" + stringForUint111, string, lteIdleOrConnectString);
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-17.17s %-17.17s", "TX_PRACH/SRS", "TX_PUCCH/PUSCH", "PUSCH_MOD/MCS"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
                StringBuilder sb7 = new StringBuilder();
                sb7.append(this.mStrFormat1);
                sb7.append(String.format("\n   %-17.17s %-17.17s %-17.17s", str10 + "/" + str12, str14 + "/" + str16, str21 + "/" + str));
                this.mStrFormat1 = sb7.toString();
                this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-22.22s", "DL_MCS/RB/MOD", "BW-DL/UL"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
                this.mStrFormat1 += String.format("\n   %-17.17s %-22.22s", str23, str4);
                return returnString(i3);
            }
            s = 1;
            this.mShort = s;
            this.mFloat -= this.mFloat1;
            BigDecimal bigDecimal2 = new BigDecimal(this.mFloat);
            this.mBigDecimal = bigDecimal2;
            this.mDouble = bigDecimal2.setScale(1, 4).doubleValue();
            String str110 = ((int) this.mShort) + "/" + this.mDouble;
            this.mStrFormat1 += str17;
            this.mData = MtbHydraDataManager.this.getData(83, i2);
            this.mDataBackup = MtbHydraDataManager.this.getDataBackup(83, i2);
            this.mVersion = getStringForUint8();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            this.mStrVal = getStringForInt8();
            StringBuilder sb8 = new StringBuilder();
            sb8.append(this.mStrFormat2);
            sb8.append("\n");
            MtbHydraDataManager mtbHydraDataManager10 = MtbHydraDataManager.this;
            Map map10 = this.mDataBackup;
            String str111 = this.mStrVal;
            sb8.append(mtbHydraDataManager10.getString(map10, "pucch_tx_power", str111, !"255".equals(str111)));
            this.mStrFormat2 = sb8.toString();
            this.mStrVal = getStringForInt8();
            StringBuilder sb9 = new StringBuilder();
            sb9.append(this.mStrFormat2);
            sb9.append("\n");
            MtbHydraDataManager mtbHydraDataManager11 = MtbHydraDataManager.this;
            Map map11 = this.mDataBackup;
            String str24 = this.mStrVal;
            sb9.append(mtbHydraDataManager11.getString(map11, "pusch_tx_power", str24, !"255".equals(str24)));
            this.mStrFormat2 = sb9.toString();
            String lteModulationString2 = MtbHydraDataManager.this.getLteModulationString(getByte((byte) -1));
            this.mStrVal = lteModulationString2;
            MtbHydraDataManager.this.getString(this.mDataBackup, "PUSCH_MOD", lteModulationString2, !MtbHydraDataManager.MODEM_STATS_DATA_INVALID.equals(lteModulationString2));
            String str25 = this.mStrVal;
            this.mStrVal = getStringForUint16();
            StringBuilder sb10 = new StringBuilder();
            sb10.append(this.mStrFormat2);
            sb10.append("\n");
            MtbHydraDataManager mtbHydraDataManager12 = MtbHydraDataManager.this;
            Map map12 = this.mDataBackup;
            String str26 = this.mStrVal;
            sb10.append(mtbHydraDataManager12.getString(map12, "pusch_num_res_blocks", str26, !ModemUtils.PROP_MIRIL_PRINT_ON.equals(str26)));
            this.mStrFormat2 = sb10.toString();
            stringForUint16 = getStringForUint16();
            this.mStrVal = stringForUint16;
            if (ModemUtils.PROP_MIRIL_PRINT_ON.equals(stringForUint16)) {
                str = this.mStrVal;
            } else {
                str = "-";
            }
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B173_DIAG_LOG_LTE_PDSCH_STAT_INDICATION, i2);
            this.mVersion = getStringForUint8();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            String str27 = getStringForUint8() + "/" + getStringForUint8() + "/" + MtbHydraDataManager.this.getLtePdschModulationString(getByte((byte) -1));
            this.mData = MtbHydraDataManager.this.getData(84, i2);
            this.mVersion = getStringForUint8();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            this.mIVal = getByte((byte) -1) + 1;
            StringBuilder sb11 = new StringBuilder();
            if (this.mIVal > 0) {
                str2 = "Rank" + this.mIVal;
            } else {
                str2 = "-";
            }
            sb11.append(str2);
            sb11.append("/");
            sb11.append(getStringForUint8());
            String string2 = sb11.toString();
            this.mStrFormat2 += "\ncqi_cw1      : " + getStringForUint8();
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("  %-3.3s %-3.3s %-10.10s %-5.5s %-6.6s %-4.4s %-4.4s %-4.4s %-4.4s %-3.3s", "MCC", "MNC", "eNB/Cell", "TAC", "EARFCN", "PCI", "RSRP", "RSRQ", "RSSI", "SNR"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            this.mStrFormat1 += String.format("\n  %-3.3s %-3.3s %-10.10s %-5.5s %-6.6s %-4.4s %-4.4s %-4.4s %-4.4s %-3.3s", stringForUint17, stringForUint18, enbCellString, stringForUint19, stringForUint32, stringForUint110, stringForInt16, stringForInt17, stringForInt18, stringForUint8);
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-17.17s %-17.17s", "AVG_RSRP/RSRQ", "AVG_RSSI/SNR", "ANT/Diff"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            this.mStrFormat1 += String.format("\n   %-17.17s %-17.17s %-17.17s", str5, str6, str110);
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-17.17s %-17.17s", "BAND/PAGE_CYC", "RI/CQI", "RRC_STATE"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            this.mStrFormat1 += String.format("\n   %-17.17s %-17.17s %-17.17s", str3 + "/" + stringForUint111, string2, lteIdleOrConnectString);
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-17.17s %-17.17s", "TX_PRACH/SRS", "TX_PUCCH/PUSCH", "PUSCH_MOD/MCS"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            StringBuilder sb12 = new StringBuilder();
            sb12.append(this.mStrFormat1);
            sb12.append(String.format("\n   %-17.17s %-17.17s %-17.17s", str10 + "/" + str12, str14 + "/" + str16, str25 + "/" + str));
            this.mStrFormat1 = sb12.toString();
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-22.22s", "DL_MCS/RB/MOD", "BW-DL/UL"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            this.mStrFormat1 += String.format("\n   %-17.17s %-22.22s", str27, str4);
            return returnString(i3);
        }

        public ModemStatsGroupLteSrvBasic() {
            super(MtbHydraDataManager.LTE_SRV_BASIC_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupLteSrvBasic Constructor");
            addGroupItem(36);
            addGroupItem(30);
            addGroupItem(72);
            addGroupItem(82);
            addGroupItem(83);
            addGroupItem(84);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B173_DIAG_LOG_LTE_PDSCH_STAT_INDICATION);
        }
    }

    public class ModemStatsGroupLteSrvDetail extends ModemStatsGroupLteSrvBasic {
        public ModemStatsGroupLteSrvDetail() {
            super(MtbHydraDataManager.LTE_SRV_DETAIL_INFO);
            MtbHydraDataManager.log("ModemStatsGroupLteSrvDetail Constructor");
            addGroupItem(36);
            addGroupItem(30);
            addGroupItem(72);
            addGroupItem(82);
            addGroupItem(83);
            addGroupItem(84);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B173_DIAG_LOG_LTE_PDSCH_STAT_INDICATION);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroupLteSrvBasic, com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            return formatString(i2, 3);
        }
    }

    public class ModemStatsGroupNas extends ModemStatsGroup {
        public ModemStatsGroupNas() {
            super(MtbHydraDataManager.NAS_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupNas Constructor");
            addGroupItem(38);
            addGroupItem(39);
            addGroupItem(40);
            addGroupItem(41);
            addGroupItem(42);
            addGroupItem(43);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            int i3;
            this.mData = MtbHydraDataManager.this.getData(38, i2);
            this.mStrFormat1 += "gprs_attach_status: " + getStringForUint8();
            int i4 = 0;
            for (int i5 = 0; i5 < 4; i5++) {
                this.mStrFormat1 += "\ntmsi[" + i5 + "]: " + getStringForUint8();
            }
            for (int i6 = 0; i6 < 4; i6++) {
                this.mStrFormat1 += "\nptmsi[" + i6 + "]: " + getStringForUint8();
            }
            for (int i7 = 0; i7 < 3; i7++) {
                this.mStrFormat1 += "\nrplmn.identity[" + i7 + "]: " + getStringForUint8Hex();
            }
            this.mData = MtbHydraDataManager.this.getData(39, i2);
            this.mStrFormat1 += "\nnas_attach_type: " + getStringForInt8();
            this.mData = MtbHydraDataManager.this.getData(40, i2);
            this.mStrFormat1 += "\nT3212_value: " + getStringForUint64();
            this.mStrFormat1 += "\nT3312_value: " + getStringForUint64();
            this.mData = MtbHydraDataManager.this.getData(41, i2);
            this.mStrFormat1 += "\nnas_cs_reject_cause: " + getStringForInt8();
            this.mData = MtbHydraDataManager.this.getData(42, i2);
            this.mStrFormat1 += "\nnas_ps_reject_cause: " + getStringForInt8();
            this.mData = MtbHydraDataManager.this.getData(43, i2);
            this.mStrFormat1 += "\nnas_pdp_address_valid: " + getStringForUint8();
            this.mStrFormat1 += "\npdp_type_org: " + getStringForInt8();
            this.mStrFormat1 += "\npdp_type_num: " + getStringForInt8();
            this.mIVal = getByte((byte) 0);
            this.mStrFormat1 += "\npdp_addr_len: " + this.mIVal;
            this.mIVal = ModemUtils.min(this.mIVal, 20);
            int i8 = 0;
            while (true) {
                i3 = this.mIVal;
                if (i8 >= i3) {
                    break;
                }
                this.mStrFormat1 += "\npdp_addr[" + i8 + "]: " + getStringForInt8();
                i8++;
            }
            skipData(20 - i3);
            this.mStrFormat1 += "\nnas_pdp_qos_valid: " + getStringForUint8();
            this.mStrFormat1 += "\ndelay_class: " + getStringForInt8();
            this.mStrFormat1 += "\nreliability_class: " + getStringForInt8();
            this.mStrFormat1 += "\npeak_throughput: " + getStringForInt8();
            this.mStrFormat1 += "\nprecedence_class: " + getStringForInt8();
            this.mStrFormat1 += "\nmean_throughput: " + getStringForInt8();
            this.mStrFormat1 += "\ntraffic_class: " + getStringForInt8();
            this.mStrFormat1 += "\ndeliver_order: " + getStringForInt8();
            this.mStrFormat1 += "\ndeliver_err_sdu: " + getStringForInt8();
            this.mStrFormat1 += "\nmax_sdu_size: " + getStringForInt8();
            this.mStrFormat1 += "\nmax_bit_rare_uplink: " + getStringForInt8();
            this.mStrFormat1 += "\nmax_bit_rare_downlink: " + getStringForInt8();
            this.mStrFormat1 += "\nresidual_ber: " + getStringForInt8();
            this.mStrFormat1 += "\nsdu_err_ratio: " + getStringForInt8();
            this.mStrFormat1 += "\ntranfer_delay: " + getStringForInt8();
            this.mStrFormat1 += "\ntraffic_priority: " + getStringForInt8();
            this.mStrFormat1 += "\nguarantee_ber_uplink: " + getStringForInt8();
            this.mStrFormat1 += "\nguarantee_ber_dwnlink: " + getStringForInt8();
            this.mStrFormat1 += "\nsig_ind: " + getStringForUint8();
            this.mStrFormat1 += "\nsrc_stat_desc: " + getStringForInt8();
            this.mStrFormat1 += "\next_max_bit_rate_downlink: " + getStringForInt8();
            this.mStrFormat1 += "\next_gtd_bit_rate_downlink: " + getStringForInt8();
            this.mStrFormat1 += "\next_max_bit_rate_uplink: " + getStringForInt8();
            this.mStrFormat1 += "\next_gtd_bit_rate_uplink: " + getStringForInt8();
            this.mStrFormat1 += "\nnas_apn_addr_valid: " + getStringForUint8();
            this.mIVal = getShort((short) 0);
            this.mStrFormat1 += "\napn_addr_len: " + this.mIVal;
            this.mIVal = ModemUtils.min(this.mIVal, 102);
            while (true) {
                int i9 = this.mIVal;
                if (i4 >= i9) {
                    skipData(102 - i9);
                    return this.mStrFormat1;
                }
                this.mStrFormat1 += "\napn_addr[" + i4 + "]: " + getStringForInt8();
                i4++;
            }
        }
    }

    public class ModemStatsGroupNonStopCmn extends ModemStatsGroup {
        public ModemStatsGroupNonStopCmn() {
            super(MtbHydraDataManager.NON_STOP_GROUP_CMN, false, true);
            MtbHydraDataManager.log("ModemStatsGroupNonStopCmn Constructor");
        }
    }

    public class ModemStatsGroupNonStopSub extends ModemStatsGroup {
        public ModemStatsGroupNonStopSub() {
            super(MtbHydraDataManager.NON_STOP_GROUP_SUB, true, true);
            MtbHydraDataManager.log("ModemStatsGroupNonStopSub Constructor");
            addGroupItem(44, true);
        }
    }

    public class ModemStatsGroupNr5GSrv extends ModemStatsGroup {
        public ModemStatsGroupNr5GSrv() {
            super(MtbHydraDataManager.NR5G_SRV_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupNr5GSrv Constructor");
            addGroupItem(75);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B825_DIAG_LOG_NR5G_RRC_CONFIGURATION_INFO);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B975_DIAG_LOG_NR5G_ML1_SRV_BEAM);
            addGroupItem(85);
            addGroupItem(86);
            addGroupItem(87);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B888_DIAG_LOG_NR5G_MAC_PDSCH_STATS);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B8A7_DIAG_LOG_NR5G_MAC_CSF_REPORT);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B884_DIAG_LOG_NR5G_MAC_UL_PHY_CH_POWER_CTRL);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_3184_DIAG_EVENT_NR5G_RRC_NEW_CELL_IND_V2SSB);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B8C9_DIAG_LOG_NR5G_LL1_FW_RX_CONTROL_AGC);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            String stringForUint16;
            String str;
            int networkType = MtbHydraDataManager.getNetworkType(i2);
            if (9 != networkType && 12 != networkType) {
                return MtbHydraDataManager.DATA_GROUP_NO_SHOW_FLAG;
            }
            this.mData = MtbHydraDataManager.this.getData(75, i2);
            this.mStrFormat2 += "mcc: " + getStringForUint16();
            this.mStrFormat2 += "\nmnc: " + getStringForUint16();
            this.mStrFormat2 += "\ncellId: " + getStringForUint32();
            this.mStrFormat2 += "\ntac: " + getStringForUint16();
            this.mStrFormat2 += "\nearfcn: " + getStringForUint32();
            this.mStrFormat2 += "\npci: " + getStringForUint16();
            this.mStrFormat2 += "\nrsrp: " + getStringForInt16();
            this.mStrFormat2 += "\nrsrq: " + getStringForInt16();
            this.mStrFormat2 += "\nrssi: " + getStringForInt16();
            this.mStrFormat2 += "\nsinr: " + getStringForInt8();
            this.mStrFormat2 += "\nband: B" + getStringForUint8();
            this.mStrFormat2 += "\ndl_bw: " + MtbHydraDataManager.this.getNr5gBandWidthIdxString(getByte((byte) -1));
            this.mStrFormat2 += "\nul_bw: " + MtbHydraDataManager.this.getNr5gBandWidthIdxString(getByte((byte) -1));
            this.mStrFormat2 += "\nnum_meas: " + getStringForUint8();
            this.mStrFormat2 += "\npad: " + getStringForUint8();
            this.mStrFormat2 += "\nsrxlev: " + getStringForUint16();
            this.mStrFormat2 += "\nthresh_serving_low: " + getStringForUint8();
            this.mStrFormat2 += "\nstale_measurements: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B825_DIAG_LOG_NR5G_RRC_CONFIGURATION_INFO, i2);
            if (Integer.MAX_VALUE == MtbHydraDataManager.mDataBuffer[i2].m5GRsrp) {
                this.mData = null;
            }
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "**version: " + this.mVersion;
                stringForUint16 = getStringForUint16();
            } else {
                stringForUint16 = getStringForUint16();
            }
            String str2 = getStringForUint32() + "/" + getStringForUint32();
            String str3 = getStringForUint16() + "/" + MtbHydraDataManager.this.getNr5gBandTypeString(getByte((byte) -1));
            if ("3".equals(this.mVersion)) {
                str = MtbHydraDataManager.this.getNr5gBandWidthIdxV3String(getByte((byte) -1)) + "/" + MtbHydraDataManager.this.getNr5gBandWidthIdxV3String(getByte((byte) -1));
            } else {
                str = MtbHydraDataManager.this.getNr5gBandWidthIdxString(getByte((byte) -1)) + "/" + MtbHydraDataManager.this.getNr5gBandWidthIdxString(getByte((byte) -1));
            }
            this.mStrFormat2 += "\ndl_rb_info   : " + MtbHydraDataManager.this.getNr5gRbTypeString(getByte((byte) -1)) + "/" + MtbHydraDataManager.this.getNr5gRbPathString(getByte((byte) -1));
            this.mStrFormat2 += "\ndl_rohc      : " + getStringForUint8();
            this.mStrFormat2 += "\nul_rb_info   : " + MtbHydraDataManager.this.getNr5gRbTypeString(getByte((byte) -1)) + "/" + MtbHydraDataManager.this.getNr5gRbPathString(getByte((byte) -1));
            this.mStrFormat2 += "\nul_rohc      : " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B975_DIAG_LOG_NR5G_ML1_SRV_BEAM, i2);
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            this.mStrFormat2 += "\nrsrp: " + getStringForFloat();
            this.mStrFormat2 += "\nrsrq: " + getStringForFloat();
            this.mData = MtbHydraDataManager.this.getData(85, i2);
            if (Integer.MAX_VALUE == MtbHydraDataManager.mDataBuffer[i2].m5GRsrp) {
                this.mData = null;
            }
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            this.mStrVal = getStringForUint16();
            this.mStrVal1 = getStringForUint16();
            StringBuilder sb = new StringBuilder();
            sb.append(ModemUtils.PROP_MIRIL_PRINT_ON.equals(this.mStrVal) ? "-" : this.mStrVal);
            sb.append("/");
            sb.append(ModemUtils.PROP_MIRIL_PRINT_ON.equals(this.mStrVal1) ? "-" : this.mStrVal1);
            String string = sb.toString();
            String stringForUint32 = getStringForUint32();
            this.mData = MtbHydraDataManager.this.getData(86, i2);
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            this.mStrFormat2 += "\nta: " + getStringForUint16();
            this.mData = MtbHydraDataManager.this.getData(87, i2);
            if (Integer.MAX_VALUE == MtbHydraDataManager.mDataBuffer[i2].m5GRsrp) {
                this.mData = null;
            }
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            String nr5gPdschModulationString = MtbHydraDataManager.this.getNr5gPdschModulationString(getByte((byte) -1));
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B888_DIAG_LOG_NR5G_MAC_PDSCH_STATS, i2);
            if (Integer.MAX_VALUE == MtbHydraDataManager.mDataBuffer[i2].m5GRsrp) {
                this.mData = null;
            }
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            String stringForFloat = getStringForFloat();
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B8A7_DIAG_LOG_NR5G_MAC_CSF_REPORT, i2);
            if (Integer.MAX_VALUE == MtbHydraDataManager.mDataBuffer[i2].m5GRsrp) {
                this.mData = null;
            }
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            String str4 = getStringForUint8() + "/" + getStringForUint8();
            this.mStrFormat2 += "\nbitwidth_ri  : " + getStringForUint8();
            this.mStrFormat2 += "\nbitwidth_cqi : " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B884_DIAG_LOG_NR5G_MAC_UL_PHY_CH_POWER_CTRL, i2);
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            this.mStrFormat2 += "\ntx_power_pusch: " + getStringForInt16();
            this.mStrFormat2 += "\ntx_power_pucch: " + getStringForInt16();
            this.mStrFormat2 += "\ntx_power_srs: " + getStringForInt16();
            this.mStrFormat2 += "\ntx_power_prach: " + getStringForInt16();
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_3184_DIAG_EVENT_NR5G_RRC_NEW_CELL_IND_V2SSB, i2);
            this.mStrFormat2 += "\nssb_freq: " + getStringForUint32();
            this.mStrFormat2 += "\npci: " + getStringForUint16();
            this.mStrFormat2 += "\nbw: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B8C9_DIAG_LOG_NR5G_LL1_FW_RX_CONTROL_AGC, i2);
            this.mVersion = getStringForUint32();
            if (MtbHydraDataManager.mVersionShow) {
                this.mStrFormat1 += "\n**version: " + this.mVersion;
            }
            this.mStrFormat2 += "\nrssi0: " + getStringForFloat();
            this.mStrFormat2 += "\nrssi1: " + getStringForFloat();
            this.mStrFormat2 += "\nrssi2: " + getStringForFloat();
            this.mStrFormat2 += "\nrssi3: " + getStringForFloat();
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("  %-17.17s %-17.17s %-17.17s", "PCI/BAND/TYPE", "ARFCN_DL/UL", "RI/CQI"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            this.mStrFormat1 += String.format("\n   %-17.17s %-17.17s %-17.17s", stringForUint16 + "/" + str3, str2, str4);
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-17.17s %-17.17s %-17.17s", "MCS_DL/UL", "RB_ASSI_NUM", "MOD_TYPE/BLER"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            this.mStrFormat1 += String.format("\n   %-17.17s %-17.17s %-17.17s", string, stringForUint32, nr5gPdschModulationString + "/" + stringForFloat);
            this.mStrFormat1 += ModemUtils.toHtmlFormatColor(String.format("\n  %-22.22s", "BW_DL/UL"), MtbHydraDataManager.COLOR_TXT_SUB_NAME);
            String str5 = this.mStrFormat1 + String.format("\n   %-22.22s", str);
            this.mStrFormat1 = str5;
            return str5;
        }
    }

    public class ModemStatsGroupPhoneSubInfoMwc extends ModemStatsGroup {
        public ModemStatsGroupPhoneSubInfoMwc() {
            super(MtbHydraDataManager.PHONE_SUB_INFO_MWC, true, false, false);
            MtbHydraDataManager.log("ModemStatsGroupPhoneSubInfoMwc Constructor");
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B825_DIAG_LOG_NR5G_RRC_CONFIGURATION_INFO);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected void callMainUpdateProc(int i2) {
            MtbHydraDataManager.tmpLog("callMainUpdateProc, mGroupName = " + this.mGroupName + ", sub = " + i2);
            this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B825_DIAG_LOG_NR5G_RRC_CONFIGURATION_INFO, i2);
            this.mVersion = getStringForUint32();
            this.mStrFormat2 += "\npci          : " + getStringForUint16();
            getStringForUint32();
            getStringForUint32();
            getStringForUint16();
            MtbHydraDataManager.this.getNr5gBandTypeString(getByte((byte) -1));
            if ("3".equals(this.mVersion)) {
                MtbHydraDataManager.this.getNr5gBandWidthIdxV3String(getByte((byte) -1));
                MtbHydraDataManager.this.getNr5gBandWidthIdxV3String(getByte((byte) -1));
            } else {
                MtbHydraDataManager.this.getNr5gBandWidthIdxString(getByte((byte) -1));
                MtbHydraDataManager.this.getNr5gBandWidthIdxString(getByte((byte) -1));
            }
            this.mStrFormat2 += "\ndl_rb_info   : " + MtbHydraDataManager.this.getNr5gRbTypeString(getByte((byte) -1)) + "/" + MtbHydraDataManager.this.getNr5gRbPathString(getByte((byte) -1));
            this.mStrFormat2 += "\ndl_rohc      : " + getStringForUint8();
            this.mStrFormat2 += "\nul_rb_info   : " + MtbHydraDataManager.this.getNr5gRbTypeString(getByte((byte) -1)) + "/" + MtbHydraDataManager.this.getNr5gRbPathString(getByte((byte) -1));
            this.mStrFormat2 += "\nul_rohc      : " + getStringForUint8();
            MtbHydraDataManager.mDataBuffer[i2].mNr5gRrcState = getByte((byte) -1);
            MtbHydraDataManager.mDataBuffer[i2].mNr5gConnMode = getByte((byte) -1);
            MtbHydraDataManager.mView.onUpdatePhoneSubInfoMWC(i2);
        }
    }

    public class ModemStatsGroupRf extends ModemStatsGroup {
        public ModemStatsGroupRf() {
            super(MtbHydraDataManager.RF_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupRf Constructor");
            addGroupItem(70);
            addGroupItem(71);
            addGroupItem(72);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            this.mData = MtbHydraDataManager.this.getData(70, i2);
            this.mStrFormat1 += "dpdt: " + getStringForUint8();
            this.mData = MtbHydraDataManager.this.getData(71, i2);
            this.mStrFormat1 += "\nrx_diversity_indicator: " + getStringForUint32();
            this.mStrFormat1 += "\nrx_pwr_chain0_is_radio_tuned: " + getStringForUint8();
            this.mStrFormat1 += "\nrx_pwr_chain0_rx_pwr: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain0_ecio: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain0_rscp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain0_rsrp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain0_phase: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain1_is_radio_tuned: " + getStringForUint8();
            this.mStrFormat1 += "\nrx_pwr_chain1_rx_pwr: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain1_ecio: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain1_rscp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain1_rsrp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain1_phase: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain2_is_radio_tuned: " + getStringForUint8();
            this.mStrFormat1 += "\nrx_pwr_chain2_rx_pwr: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain2_ecio: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain2_rscp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain2_rsrp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain2_phase: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain3_is_radio_tuned: " + getStringForUint8();
            this.mStrFormat1 += "\nrx_pwr_chain3_rx_pwr: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain3_ecio: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain3_rscp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain3_rsrp: " + getStringForInt32();
            this.mStrFormat1 += "\nrx_pwr_chain3_phase: " + getStringForInt32();
            this.mData = MtbHydraDataManager.this.getData(72, i2);
            this.mDataBackup = MtbHydraDataManager.this.getDataBackup(72, i2);
            this.mStrFormat1 += "\ntx_power_is_in_traffic: " + getStringForUint8();
            this.mStrVal = getStringForInt32();
            StringBuilder sb = new StringBuilder();
            sb.append(this.mStrFormat1);
            sb.append("\n");
            MtbHydraDataManager mtbHydraDataManager = MtbHydraDataManager.this;
            Map map = this.mDataBackup;
            String str = this.mStrVal;
            sb.append(mtbHydraDataManager.getString(map, "tx_power_tx_pwr", str, !"-3276".equals(str)));
            this.mStrFormat1 = sb.toString();
            this.mStrVal = getStringForUint8();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mStrFormat1);
            sb2.append("\n");
            MtbHydraDataManager mtbHydraDataManager2 = MtbHydraDataManager.this;
            Map map2 = this.mDataBackup;
            String str2 = this.mStrVal;
            sb2.append(mtbHydraDataManager2.getString(map2, "tx_power_pa_gain_state", str2, !"0".equals(str2)));
            this.mStrFormat1 = sb2.toString();
            ByteBuffer byteBuffer = this.mData;
            if (byteBuffer != null) {
                int i3 = byteBuffer.getInt() / 10;
                this.mIVal = i3;
                this.mStrVal = ModemUtils.getString(i3, false, false);
            } else {
                this.mStrVal = "-";
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.mStrFormat1);
            sb3.append("\n");
            MtbHydraDataManager mtbHydraDataManager3 = MtbHydraDataManager.this;
            Map map3 = this.mDataBackup;
            String str3 = this.mStrVal;
            sb3.append(mtbHydraDataManager3.getString(map3, "tx_power_prach", str3, !"-3276".equals(str3)));
            this.mStrFormat1 = sb3.toString();
            ByteBuffer byteBuffer2 = this.mData;
            if (byteBuffer2 != null) {
                int i4 = byteBuffer2.getInt() / 10;
                this.mIVal = i4;
                this.mStrVal = ModemUtils.getString(i4, false, false);
            } else {
                this.mStrVal = "-";
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.mStrFormat1);
            sb4.append("\n");
            MtbHydraDataManager mtbHydraDataManager4 = MtbHydraDataManager.this;
            Map map4 = this.mDataBackup;
            String str4 = this.mStrVal;
            sb4.append(mtbHydraDataManager4.getString(map4, "tx_power_srs", str4, !"-3276".equals(str4)));
            this.mStrFormat1 = sb4.toString();
            ByteBuffer byteBuffer3 = this.mData;
            if (byteBuffer3 != null) {
                int i5 = byteBuffer3.getInt() / 10;
                this.mIVal = i5;
                this.mStrVal = ModemUtils.getString(i5, false, false);
            } else {
                this.mStrVal = "-";
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.mStrFormat1);
            sb5.append("\n");
            MtbHydraDataManager mtbHydraDataManager5 = MtbHydraDataManager.this;
            Map map5 = this.mDataBackup;
            String str5 = this.mStrVal;
            sb5.append(mtbHydraDataManager5.getString(map5, "tx_power_pucch", str5, !"-3276".equals(str5)));
            this.mStrFormat1 = sb5.toString();
            ByteBuffer byteBuffer4 = this.mData;
            if (byteBuffer4 != null) {
                int i6 = byteBuffer4.getInt() / 10;
                this.mIVal = i6;
                this.mStrVal = ModemUtils.getString(i6, false, false);
            } else {
                this.mStrVal = "-";
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append(this.mStrFormat1);
            sb6.append("\n");
            MtbHydraDataManager mtbHydraDataManager6 = MtbHydraDataManager.this;
            Map map6 = this.mDataBackup;
            String str6 = this.mStrVal;
            sb6.append(mtbHydraDataManager6.getString(map6, "tx_power_pusch", str6, !"-3276".equals(str6)));
            this.mStrFormat1 = sb6.toString();
            this.mData = MtbHydraDataManager.this.getData(100, i2);
            this.mStrFormat1 += "\nversion: " + getStringForUint8();
            this.mStrFormat1 += "\nant_number: " + getStringForUint8();
            this.mStrFormat1 += "\nant_switch_path: " + getStringForUint16();
            String str7 = this.mStrFormat1 + "\nsig_path: " + getStringForUint16();
            this.mStrFormat1 = str7;
            return str7;
        }
    }

    public class ModemStatsGroupSubSignalInfo extends ModemStatsGroup {
        public ModemStatsGroupSubSignalInfo() {
            super(MtbHydraDataManager.PHONE_SUB_SIGNAL_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupSubSignalInfo Constructor");
            addGroupItem(82);
            addGroupItem(135);
            addGroupItem(137);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            MtbHydraDataManager.tmpLog("formatString, mGroupName = " + this.mGroupName + ", sub = " + i2);
            int networkType = MtbHydraDataManager.getNetworkType(i2);
            MtbHydraDataManager.log("formatString, mGroupName = " + this.mGroupName + ", sub = " + i2 + ", rat = " + networkType);
            if (9 == networkType) {
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType, false);
                this.mStrFormat1 = "";
                this.mStrFormat2 = "";
                this.mStrFormat3 = "";
                this.mData = MtbHydraDataManager.this.getData(82, i2);
                skipData(1);
                skipData(1);
                this.mStrFormat1 += "\nearfcn  : " + getStringForUint32();
                this.mStrFormat1 += "\npci     : " + getStringForUint16();
                this.mStrFormat2 += "\nis_srv_cell: " + getStringForUint8();
                this.mShort = getShort((short) 0);
                this.mFloat = getFloat(MtbHydraDataManager.LTE_RSRP_INI);
                this.mStrFormat1 += "\nrsrp_rx0: " + this.mFloat;
                this.mStrFormat1 += "\nrsrq_rx0: " + getStringForFloat();
                this.mStrFormat2 += "\nrssi_rx0: " + getStringForFloat();
                this.mStrFormat2 += "\nsnr_rx0: " + getStringForFloat();
                this.mFloat1 = getFloat(MtbHydraDataManager.LTE_RSRP_INI);
                this.mStrFormat1 += "\nrsrp_rx1: " + this.mFloat1;
                this.mStrFormat1 += "\nrsrq_rx1: " + getStringForFloat();
                this.mStrFormat2 += "\nrssi_rx1: " + getStringForFloat();
                this.mStrFormat2 += "\nsnr_rx1: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrp_rx2: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrq_rx2: " + getStringForFloat();
                this.mStrFormat2 += "\nrssi_rx2: " + getStringForFloat();
                this.mStrFormat2 += "\nsnr_rx2: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrp_rx3: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrq_rx3: " + getStringForFloat();
                this.mStrFormat2 += "\nrssi_rx3: " + getStringForFloat();
                this.mStrFormat2 += "\nsnr_rx3: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrp    : " + getStringForFloat();
                this.mStrFormat1 += "\nrsrq    : " + getStringForFloat();
                this.mStrFormat2 += "\nf_max_rssi: " + getStringForFloat();
                this.mStrFormat += this.mStrFormat1;
            } else if (12 == networkType) {
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType, false);
                this.mStrFormat1 = "";
                this.mStrFormat2 = "";
                this.mStrFormat3 = "";
                this.mStrFormat4 = "";
                this.mData = MtbHydraDataManager.this.getData(135, i2);
                skipData(4);
                this.mStrFormat1 += "\nrsrp_rx0: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrp_rx1: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrp_rx2: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrp_rx3: " + getStringForFloat();
                this.mStrFormat1 += "\nrsrq    : " + getStringForFloat();
                this.mStrFormat2 += "\npci     : " + getStringForUint16();
                this.mStrFormat3 += "\ncell_cnt: " + getStringForUint8();
                this.mStrFormat4 += "\nearfcn  : " + getStringForUint32();
                this.mData = MtbHydraDataManager.this.getData(137, i2);
                skipData(4);
                this.mStrFormat1 += "\nsnr_rx0 : " + getStringForFloat();
                this.mStrFormat1 += "\nsnr_rx1 : " + getStringForFloat();
                this.mStrFormat1 += "\nsnr_rx2 : " + getStringForFloat();
                this.mStrFormat1 += "\nsnr_rx3 : " + getStringForFloat();
                this.mStrFormat += this.mStrFormat4 + this.mStrFormat2 + this.mStrFormat1;
            } else {
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType);
                this.mStrFormat += "\nno info show";
            }
            String str = this.mStrFormat + "\n";
            this.mStrFormat = str;
            return str;
        }
    }

    public class ModemStatsGroupSubTxInfo extends ModemStatsGroup {
        public ModemStatsGroupSubTxInfo() {
            super(MtbHydraDataManager.PHONE_SUB_TX_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupSubTxInfo Constructor");
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B16E_DIAG_LOG_LTE_PUSCH_POWER_CONTROL);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B884_DIAG_LOG_NR5G_MAC_UL_PHY_CH_POWER_CTRL);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B0C2_DIAG_LOG_LTE_RRC_SERVING_CELL_INFO);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B823_DIAG_LOG_NR5G_RRC_SERVING_CELL_INFO);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            MtbHydraDataManager.tmpLog("formatString, mGroupName = " + this.mGroupName + ", sub = " + i2);
            int networkType = MtbHydraDataManager.getNetworkType(i2);
            MtbHydraDataManager.log("formatString, mGroupName = " + this.mGroupName + ", sub = " + i2 + ", rat = " + networkType);
            if (9 == networkType) {
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType, false);
                this.mStrFormat1 = "";
                this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B0C2_DIAG_LOG_LTE_RRC_SERVING_CELL_INFO, i2);
                skipData(1);
                this.mStrFormat1 += "\nmcc     : " + getStringForUint16();
                this.mStrFormat1 += "\nmnc     : " + getStringForUint16();
                this.mStrFormat1 += "\ntac     : " + getStringForUint16();
                this.mStrFormat1 += "\ncgi     : " + getStringForUint32();
                this.mStrFormat1 += "\npci     : " + getStringForUint16();
                this.mStrFormat1 += "\nband    : " + getStringForUint32();
                this.mStrFormat1 += "\nearfn   : " + getStringForUint32();
                this.mStrFormat1 += "\nbw      : " + getStringForUint8();
                this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B16E_DIAG_LOG_LTE_PUSCH_POWER_CONTROL, i2);
                skipData(1);
                this.mStrFormat1 += "\ntx_pusch: " + getStringForUint16();
                this.mStrFormat += this.mStrFormat1;
            } else if (12 == networkType) {
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType, false);
                this.mStrFormat1 = "";
                this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B823_DIAG_LOG_NR5G_RRC_SERVING_CELL_INFO, i2);
                skipData(4);
                this.mStrFormat1 += "\nmcc     : " + getStringForUint16();
                this.mStrFormat1 += "\nmnc     : " + getStringForUint16();
                this.mStrFormat1 += "\ntac     : " + getStringForUint32();
                this.mStrFormat1 += "\ncgi     : " + getStringForUint64();
                this.mStrFormat1 += "\npci     : " + getStringForUint16();
                this.mStrFormat1 += "\nband    : " + getStringForUint16();
                this.mStrFormat1 += "\nearfn   : " + getStringForUint32();
                this.mStrFormat1 += "\nbw      : " + getStringForUint16();
                skipData(7);
                this.mStrFormat1 += "\nscs     : " + getStringForUint8();
                this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B884_DIAG_LOG_NR5G_MAC_UL_PHY_CH_POWER_CTRL, i2);
                skipData(4);
                this.mStrFormat1 += "\ntx_pusch: " + getStringForFloat();
                this.mStrFormat += this.mStrFormat1;
            } else {
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType);
                this.mStrFormat += "\nno info show";
            }
            String str = this.mStrFormat + "\n";
            this.mStrFormat = str;
            return str;
        }
    }

    public class ModemStatsGroupSubVoiceInfo extends ModemStatsGroup {
        public ModemStatsGroupSubVoiceInfo() {
            super(MtbHydraDataManager.PHONE_SUB_VOICE_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupSubVoiceInfo Constructor");
            addGroupItem(19);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B0C2_DIAG_LOG_LTE_RRC_SERVING_CELL_INFO);
            addGroupItem(MtbHydraDataManager.MODEM_STATS_DATA_ID_B823_DIAG_LOG_NR5G_RRC_SERVING_CELL_INFO);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            MtbHydraDataManager.tmpLog("formatString, mGroupName = " + this.mGroupName + ", sub = " + i2);
            int networkType = MtbHydraDataManager.getNetworkType(i2);
            if (9 == networkType) {
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType, false);
                this.mStrFormat1 = "";
                this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B0C2_DIAG_LOG_LTE_RRC_SERVING_CELL_INFO, i2);
                skipData(1);
                this.mStrFormat1 += "\nmcc     : " + getStringForUint16();
                this.mStrFormat1 += "\nmnc     : " + getStringForUint16();
                this.mStrFormat1 += "\ntac     : " + getStringForUint16();
                this.mStrFormat1 += "\ncgi     : " + getStringForUint32();
                this.mStrFormat1 += "\npci     : " + getStringForUint16();
                this.mStrFormat1 += "\nband    : " + getStringForUint32();
                this.mStrFormat1 += "\nearfn   : " + getStringForUint32();
                this.mStrFormat1 += "\nbw      : " + getStringForUint8();
                this.mStrFormat += this.mStrFormat1;
            } else {
                if (12 != networkType) {
                    this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType);
                    String str = this.mStrFormat + "\nno info show";
                    this.mStrFormat = str;
                    return str;
                }
                this.mStrFormat = "rat     : " + MtbHydraDataManager.getNetworkTypeString(networkType, false);
                this.mStrFormat1 = "";
                this.mData = MtbHydraDataManager.this.getData(MtbHydraDataManager.MODEM_STATS_DATA_ID_B823_DIAG_LOG_NR5G_RRC_SERVING_CELL_INFO, i2);
                skipData(4);
                this.mStrFormat1 += "\nmcc     : " + getStringForUint16();
                this.mStrFormat1 += "\nmnc     : " + getStringForUint16();
                this.mStrFormat1 += "\ntac     : " + getStringForUint32();
                this.mStrFormat1 += "\ncgi     : " + getStringForUint64();
                this.mStrFormat1 += "\npci     : " + getStringForUint16();
                this.mStrFormat1 += "\nband    : " + getStringForUint16();
                this.mStrFormat1 += "\nearfn   : " + getStringForUint32();
                this.mStrFormat1 += "\nbw      : " + getStringForUint16();
                skipData(7);
                this.mStrFormat1 += "\nscs     : " + getStringForUint8();
                this.mStrFormat += this.mStrFormat1;
            }
            this.mData = MtbHydraDataManager.this.getData(19, i2);
            skipData(1);
            this.mStrFormat += "\nnetwork_mode : " + MtbHydraDataManager.this.getVoiceNetworkModeString(getInt(-1));
            this.mStrFormat += "\nspeech_codec : " + MtbHydraDataManager.this.getVoiceSpeechCodecString(getInt(-1));
            this.mStrFormat += "\nspeech_freq  : " + getStringForUint32() + "(Hz)";
            this.mStrFormat += "\ncall_id      : " + getStringForUint8();
            this.mStrFormat1 += "\naudio_quality: " + MtbHydraDataManager.this.getVoiceAudioQualityString(getInt(-1));
            String str2 = this.mStrFormat + "\n";
            this.mStrFormat = str2;
            return str2;
        }
    }

    public class ModemStatsGroupUim extends ModemStatsGroup {
        public ModemStatsGroupUim() {
            super(MtbHydraDataManager.UIM_INFO, true, false);
            MtbHydraDataManager.log("ModemStatsGroupUim Constructor");
            addGroupItem(65);
            addGroupItem(66);
            addGroupItem(67);
            addGroupItem(68);
        }

        @Override // com.xiaomi.mtb.MtbHydraDataManager.ModemStatsGroup
        protected String formatString(int i2) {
            this.mData = MtbHydraDataManager.this.getData(65, i2);
            this.mStrFormat1 += "slot_id: " + getStringForUint16();
            this.mStrFormat1 += "\ncard_inserted: " + getStringForUint8();
            skipData(1);
            this.mStrFormat1 += "\niccid: ";
            for (int i3 = 0; i3 < 10 && this.mData != null; i3++) {
                this.mStrFormat1 += ModemUtils.getString(ModemUtils.revertHigh4AndLow4(getByte((byte) 0)), true, true);
            }
            this.mData = MtbHydraDataManager.this.getData(66, i2);
            skipData(2);
            this.mStrFormat1 += "\nis_sub_ready: " + getStringForUint8();
            this.mStrFormat1 += "\napp_type: " + MtbHydraDataManager.this.getUimAppTypeString(getInt(-1));
            this.mData = MtbHydraDataManager.this.getData(67, i2);
            skipData(2);
            this.mStrFormat1 += "\ncard_error: " + MtbHydraDataManager.this.getCardErrorString(getInt(-1));
            this.mData = MtbHydraDataManager.this.getData(68, i2);
            skipData(2);
            String str = this.mStrFormat1 + "\npin_status: " + MtbHydraDataManager.this.getUimPinStatusString(getInt(-1));
            this.mStrFormat1 = str;
            return str;
        }
    }

    private MtbHydraDataManager(MtbDataDisplayActivity mtbDataDisplayActivity) {
        this.mHydraDataIndRegistrant = null;
        this.mCardStatus = "Not Get";
        this.mCardSlotStatus = "Not Get";
        mView = mtbDataDisplayActivity;
        mContext = mtbDataDisplayActivity.getContext();
        mMtbHookAgent = MtbBaseActivity.mMtbHookAgent;
        mMapDataDisplayAllValue = mtbDataDisplayActivity.mMapDataDisplayAllValue;
        mNumPhones = MtbBaseActivity.mNumPhones;
        mModemStatsIntervalSec = mtbDataDisplayActivity.mTimeInterval;
        mHandler = mtbDataDisplayActivity.mHandlerData;
        mDataBuffer = mtbDataDisplayActivity.mDataBuffer;
        mGetDataLogPrintFlag = getStatsGetPrintFlag();
        log("Constructor, mContext = " + mContext + ", mMtbHookAgent = " + mMtbHookAgent + ", mMapDataDisplayAllValue = " + mMapDataDisplayAllValue + ", mNumPhones = " + mNumPhones + ", mView = " + mView + ", mModemStatsIntervalSec = " + mModemStatsIntervalSec + ", mHandler = " + mHandler + ", mDataBuffer = " + mDataBuffer + ", mGetDataLogPrintFlag = " + mGetDataLogPrintFlag + ", mIndDataLogPrintFlag = " + mIndDataLogPrintFlag + ", mTmpLogPrintFlag = " + mTmpLogPrintFlag + ", mParseDataLogPrintFlag = " + mParseDataLogPrintFlag);
        this.mHydraDataIndRegistrant = new Registrant(mHandler, 100, (Object) null);
        this.mCardStatus = ModemUtils.getSimStateString();
        this.mCardSlotStatus = ModemUtils.getCardSlotStateString(mMtbHookAgent);
        onInitListForGroup();
        startModemStats();
        allocModemStatsId();
        registerOemHookCallbackForMiBroadcastData();
        registerBcReceiver();
        registerGroupForInit();
    }

    public static void allocModemStatsId() {
        log("allocModemStatsId, old mStatsClientId: " + mStatsClientId);
        if (mStatsClientId < 10) {
            log("allocModemStatsId already done");
            return;
        }
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        ByteBuffer byteBufferOnHookCommonMsgSync = oemHookAgent.onHookCommonMsgSync(58);
        if (byteBufferOnHookCommonMsgSync == null) {
            log("bufRet is null");
            return;
        }
        int i2 = byteBufferOnHookCommonMsgSync.getInt();
        log("ret: " + i2);
        if (i2 != 0) {
            log("EVENT_OEMHOOK_MODEM_STATS_ID_ALLOC fail");
            return;
        }
        mStatsClientId = byteBufferOnHookCommonMsgSync.getInt();
        log("new mStatsClientId: " + mStatsClientId);
    }

    private void callMainUpdateProc(String str, int i2) {
        ArrayList arrayList = mListForGroup;
        if (arrayList == null) {
            log("callMainUpdateProc, mListForGroup is null");
            return;
        }
        if (str == null) {
            log("callMainUpdateProc, groupName is null");
            return;
        }
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            ModemStatsGroup modemStatsGroup = (ModemStatsGroup) mListForGroup.get(i3);
            if (true == str.equals(modemStatsGroup.mGroupName)) {
                tmpLog("callMainUpdateProc, size = " + size + ", groupName = " + str + ", sub = " + i2);
                modemStatsGroup.callMainUpdateProc(i2);
                return;
            }
        }
        tmpLog("This group is not defined, size = " + size + ", groupName = " + str + ", sub = " + i2);
    }

    public static void changeGroup(ArrayList arrayList, int i2) {
        log("changeGroup, mArrCurGroupName = " + mArrCurGroupName + ", arrGroupName = " + arrayList + ", sub = " + i2 + ", mSub = " + mSub);
        if (mInstance == null) {
            log("not init, do nothing");
            return;
        }
        if (!judgeGroupSame(arrayList, i2)) {
            mArrCurGroupName = arrayList;
            deregisterGroup();
            registerGroup(mArrCurGroupName, i2);
            mInstance.parseAndUpdateData(mArrCurGroupName, i2, false);
            return;
        }
        log("------ Group info for reg is no chnage, will not change group name, sub: " + i2);
        registerGroup(mArrCurGroupName, i2);
        mInstance.parseAndUpdateData(mArrCurGroupName, i2, false);
    }

    public static void closeModemStats() {
        log("closeModemStats, mStatsClientId: " + mStatsClientId);
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        ByteBuffer byteBufferOnHookHydraStatsSetSync = oemHookAgent.onHookHydraStatsSetSync(ModemUtils.getCurrentClassName(), 0);
        if (byteBufferOnHookHydraStatsSetSync == null) {
            log("bufRet is null");
            return;
        }
        int i2 = byteBufferOnHookHydraStatsSetSync.getInt();
        log("ret: " + i2);
        if (i2 != 0) {
            log("closeModemStats fail");
        }
    }

    private void deregisterBcReceiver() {
        log("deregisterBcReceiver");
        Context context = mContext;
        if (context == null) {
            log("mContext is null");
        } else if (true == this.mIsBcRegistered) {
            context.unregisterReceiver(this.mBroadcastReceiver);
            this.mIsBcRegistered = false;
        }
    }

    public static void deregisterGroup() {
        log("deregisterGroup, mSub = " + mSub + ", mNumPhones = " + mNumPhones + ", mArrCurGroupName: " + mArrCurGroupName);
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager == null) {
            log("not init, do nothing");
            return;
        }
        ArrayList arrayList = mArrCurGroupName;
        if (arrayList != null) {
            mtbHydraDataManager.groupMgr(arrayList, mSub, false, false);
        } else {
            log("------ Not find active group to deregister");
        }
    }

    private static void deregisterGroupForDisplose() {
        log("deregisterGroupForDisplose");
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager == null) {
            log("mInstance is null");
        } else {
            mtbHydraDataManager.groupMgr(getGroupNameListForNonStop(), 2, false, true);
        }
    }

    public static void dispose() {
        log("dispose, hydraMgr mInstance = " + mInstance);
        deregisterGroupForDisplose();
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager != null) {
            mtbHydraDataManager.freeResource();
        }
        deregisterGroup();
        freeModemStatsId();
        mInstance = null;
        mContext = null;
        mMtbHookAgent = null;
        mListForGroup = null;
        mArrayNonStopGroupName = null;
        mNumPhones = 0;
        mMapForId2Group.clear();
        mArrCurGroupName = null;
        mSub = 2;
        mStatsClientId = 10;
    }

    private String formatString(String str, int i2) {
        ArrayList arrayList = mListForGroup;
        if (arrayList == null) {
            log("formatString, mListForGroup is null");
            return null;
        }
        if (str == null) {
            log("formatString, groupName is null");
            return null;
        }
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            ModemStatsGroup modemStatsGroup = (ModemStatsGroup) mListForGroup.get(i3);
            if (true == str.equals(modemStatsGroup.mGroupName)) {
                String formatString = modemStatsGroup.getFormatString(i2);
                parseDataLog("formatString, size = " + size + ", groupName = " + str + ", sub = " + i2 + ", strFormat = " + formatString);
                return formatString;
            }
        }
        parseDataLog("This group is not defined, size = " + size + ", groupName = " + str + ", sub = " + i2);
        return null;
    }

    public static void freeModemStatsId() {
        log("freeModemStatsId, mStatsClientId: " + mStatsClientId);
        int i2 = mStatsClientId;
        if (i2 >= 10) {
            log("mStatsClientId is not yet inited");
            return;
        }
        if (mInstance == null) {
            log("not init, do nothing");
            return;
        }
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        ByteBuffer byteBufferOnHookCommonMsgSync = oemHookAgent.onHookCommonMsgSync(59, i2);
        if (byteBufferOnHookCommonMsgSync == null) {
            log("bufRet is null");
            return;
        }
        int i3 = byteBufferOnHookCommonMsgSync.getInt();
        log("ret: " + i3);
        if (i3 != 0) {
            log("EVENT_OEMHOOK_MODEM_STATS_ID_FREE fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAccessRatString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (-1 == b2) {
            str = "NONE";
        } else if (b2 == 0) {
            str = "GSM";
        } else if (1 == b2) {
            str = "UMTS";
        } else if (2 == b2) {
            str = "LTE";
        } else if (3 == b2) {
            str = "TDS";
        } else if (4 == b2) {
            str = "NR5G";
        } else {
            log("getAccessRatString, val: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCallDirectionString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "NONE";
        } else if (1 == i2) {
            str = "MO";
        } else if (2 == i2) {
            str = "MT";
        } else {
            log("getCallDirectionString, direction: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCallHoTypeString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "NONE";
        } else if (1 == i2) {
            str = "G_2_G";
        } else if (3 == i2) {
            str = "W_2_W";
        } else if (4 == i2) {
            str = "W_2_G";
        } else if (5 == i2) {
            str = "L_2_G";
        } else if (6 == i2) {
            str = "L_2_W";
        } else if (7 == i2) {
            str = "1XCSFB";
        } else if (8 == i2) {
            str = "GWCSFB";
        } else if (9 == i2) {
            str = "L_2_C";
        } else {
            log("getCallHoTypeString, direction: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCallSpeechCodecString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "NONE";
        } else if (1 == i2) {
            str = "QCELP13K";
        } else if (2 == i2) {
            str = "EVRC";
        } else if (3 == i2) {
            str = "EVRC_B";
        } else if (4 == i2) {
            str = "EVRC_WB";
        } else if (5 == i2) {
            str = "EVRC_NW";
        } else if (6 == i2) {
            str = "AMR_NB";
        } else if (7 == i2) {
            str = "AMR_WB";
        } else if (8 == i2) {
            str = "GSM_EFR";
        } else if (9 == i2) {
            str = "GSM_FR";
        } else if (10 == i2) {
            str = "GSM_HR";
        } else if (11 == i2) {
            str = "G711U";
        } else if (12 == i2) {
            str = "G723";
        } else if (13 == i2) {
            str = "G711A";
        } else if (14 == i2) {
            str = "G722";
        } else if (15 == i2) {
            str = "G711AB";
        } else if (16 == i2) {
            str = "G729";
        } else {
            str = "EVS_NB";
            if (17 != i2 && 17 != i2) {
                if (19 == i2) {
                    str = "EVS_SWB";
                } else if (20 == i2) {
                    str = "EVS_FB";
                } else {
                    log("getCallSpeechCodecString, codecType: " + i2);
                    str = "UNKnown";
                }
            }
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCallStateString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (-1 == i2) {
            str = "UNKNOWN";
        } else if (i2 == 0) {
            str = "Idle";
        } else if (1 == i2) {
            str = "Origination";
        } else if (2 == i2) {
            str = "Incoming";
        } else if (3 == i2) {
            str = "Conversation";
        } else if (4 == i2) {
            str = "IN_PROGRESS";
        } else if (5 == i2) {
            str = "RECALL_RSP_PEND";
        } else {
            log("getCallStateString, callState: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCallTypeString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (-1 == i2) {
            str = "None";
        } else if (i2 == 0) {
            str = "VOICE";
        } else if (1 == i2) {
            str = "CS_DATA";
        } else if (2 == i2) {
            str = "PS_DATA";
        } else if (3 == i2) {
            str = "SMS";
        } else if (4 == i2) {
            str = "Position Determination for CDMA";
        } else if (5 == i2) {
            str = "TEST";
        } else if (6 == i2) {
            str = "OTAPA for CDMA";
        } else if (7 == i2) {
            str = "OTASP for CDMA";
        } else if (8 == i2) {
            str = "NON_STD_OTASP for CDMA";
        } else if (9 == i2) {
            str = "EMERGENCY";
        } else if (10 == i2) {
            str = "SUPS";
        } else if (11 == i2) {
            str = "VT";
        } else if (12 == i2) {
            str = "VT_LOOPBACK";
        } else if (13 == i2) {
            str = "Video Share";
        } else if (14 == i2) {
            str = "PS_DATA_IS707B for CDMA";
        } else if (15 == i2) {
            str = "UNKNOWN";
        } else {
            log("getCallTypeString, callType: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCardErrorString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "UNKNOWN";
        } else if (1 == i2) {
            str = "POLL_ERROR";
        } else if (2 == i2) {
            str = "ATR was not received at the maximum voltage";
        } else if (3 == i2) {
            str = "ATR was not received after a reset";
        } else if (4 == i2) {
            str = "Corrupted ATR was received after the maximum times";
        } else if (5 == i2) {
            str = "Protocol and Parameter Select timed out after the maximum times";
        } else if (6 == i2) {
            str = "Voltage mismatch";
        } else if (7 == i2) {
            str = "Internal command timed out after a Protocol and Parameter Select";
        } else if (8 == i2) {
            str = "Maximum number of internal command errors was reached";
        } else if (9 == i2) {
            str = "Maximized due to a parity error";
        } else if (10 == i2) {
            str = "Maximized due to a break in a reception error";
        } else if (11 == i2) {
            str = "Maximized due to an overrun error";
        } else if (12 == i2) {
            str = "Transmission timer expired";
        } else if (13 == i2) {
            str = "Power-down command notification error";
        } else if (14 == i2) {
            str = "Internal error in Passive mode";
        } else if (15 == i2) {
            str = "Timed out in Passive mode";
        } else if (16 == i2) {
            str = "Maximum parity error in Passive mode";
        } else if (17 == i2) {
            str = "Maximum reception break in Passive mode";
        } else if (18 == i2) {
            str = "Maximum overrun errors in Passive mode";
        } else if (19 == i2) {
            str = "No protocol at card power up";
        } else if (20 == i2) {
            str = "Card Removed";
        } else if (21 == i2) {
            str = "Technical problem";
        } else if (22 == i2) {
            str = "Card responded with NULL bytes";
        } else if (23 == i2) {
            str = "Card unavailable as SAP is connected";
        } else {
            log("getCardErrorString, err: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    private static void getDataLog(String str) {
        if (mGetDataLogPrintFlag) {
            log(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:103:0x010d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0118  */
    public String getEmmCauseString(byte b2) {
        String str;
        byte b3 = -1;
        if (-1 == b2) {
            str = "-";
        } else if (2 == b2) {
            str = "IMSI unknown in HSS";
        } else if (3 == b2) {
            str = "Illegal UE";
        } else if (6 == b2) {
            str = "Illegal ME";
        } else if (9 == b2) {
            str = "UE identity cannot be derived by the network";
        } else if (10 == b2) {
            str = "Implicitly detached";
        } else if (5 == b2) {
            str = "IMEI not accepted";
        } else if (7 == b2) {
            str = "EPS services not allowed";
        } else if (8 == b2) {
            str = "EPS services and non-EPS services not allowed";
        } else if (11 == b2) {
            str = "PLMN not allowed";
        } else if (12 == b2) {
            str = "Tracking area not allowed";
        } else if (13 == b2) {
            str = "Roaming not allowed in this tracking area";
        } else if (14 == b2) {
            str = "EPS services not allowed in this PLMN";
        } else if (15 == b2) {
            str = "No suitable cells in tracking area";
        } else if (25 == b2) {
            str = "Not authorized for this CSG";
        } else if (35 == b2) {
            str = "Requested service option not authorized in this PLMN";
        } else if (40 == b2) {
            str = "No EPS bearer context activated";
        } else if (16 == b2) {
            str = "MSC temporarily not reachable";
        } else if (17 == b2) {
            str = "Network failure";
        } else if (18 == b2) {
            str = "CS domain not available";
        } else if (19 == b2) {
            str = "ESM failure";
        } else if (20 == b2) {
            str = "MAC failure";
        } else if (21 == b2) {
            str = "Synch failure";
        } else if (22 == b2) {
            str = "Congestion";
        } else if (23 == b2) {
            str = "UE security capabilities mismatch";
        } else if (24 == b2 || 26 == b2) {
            str = "Security mode rejected, unspecified";
        } else if (39 == b2) {
            str = "CS service temporarily not available";
        } else if (42 == b2) {
            str = "Severe network failure";
        } else if (MODEM_STATS_DATA_ID_XXXX_DIAG_EVENT_LTE_EMM_TIMER == b2) {
            str = "Semantically incorrect message";
        } else if (MODEM_STATS_DATA_ID_B111_DIAG_LOG_LTE_LL1_RX_AGC == b2) {
            str = "Invalid mandatory information";
        } else if (MODEM_STATS_DATA_ID_B825_DIAG_LOG_NR5G_RRC_CONFIGURATION_INFO == b2) {
            str = "Message type non-existent or not implemented";
        } else if (MODEM_STATS_DATA_ID_B16E_DIAG_LOG_LTE_PUSCH_POWER_CONTROL == b2) {
            str = "Message type not compatible with protocol state";
        } else {
            if (99 == b2) {
                str = "Information element non-existent or not implemented";
            } else if (100 == b2) {
                str = "Conditional IE error";
            } else if (101 == b2) {
                str = "Message not compatible with protocol state";
            } else if (111 == b2) {
                str = "Protocol error, unspecified";
            } else if (8 == b2) {
                str = "Operator Determined Barring";
            } else if (26 == b2) {
                str = "Insufficient resources";
            } else if (27 == b2) {
                str = "Missing or unknown APN";
            } else if (28 == b2) {
                str = "Unknown PDN type";
            } else if (29 == b2) {
                str = "User authentication failed";
            } else if (30 == b2) {
                str = "Request rejected by Serving GW or PDN GW";
            } else if (31 == b2) {
                str = "Request rejected, unspecified";
            } else if (32 == b2) {
                str = "Service option not supported";
            } else if (33 == b2) {
                str = "Requested service option not subscribed";
            } else if (34 == b2) {
                str = "Service option temporarily out of order";
            } else if (35 == b2) {
                str = "PTI already in use";
            } else if (36 == b2) {
                str = "Regular deactivation";
            } else if (37 == b2) {
                str = "EPS QoS not accepted";
            } else if (38 == b2) {
                str = "Network failure";
            } else if (39 == b2) {
                str = "Reactivation requested";
            } else if (41 == b2) {
                str = "Semantic error in the TFT operation";
            } else if (42 == b2) {
                str = "Syntactical error in the TFT operation";
            } else if (43 == b2) {
                str = "Invalid EPS bearer identity";
            } else if (44 == b2) {
                str = "Semantic errors in packet filter(s)";
            } else if (45 == b2) {
                str = "Syntactical error in packet filter(s)";
            } else if (47 == b2) {
                str = "PTI mismatch";
            } else if (49 == b2) {
                str = "Last PDN disconnection not allowed";
            } else if (50 == b2) {
                str = "PDN type IPv4 only allowed";
            } else if (51 == b2) {
                str = "PDN type IPv6 only allowed";
            } else if (52 == b2) {
                str = "single address bearers only allowed";
            } else if (53 == b2) {
                str = "ESM information not received";
            } else if (54 == b2) {
                str = "PDN connection does not exist";
            } else if (55 == b2) {
                str = "Multiple PDN connections for a given APN not allowed";
            } else if (56 == b2) {
                str = "Collision with network initiated request";
            } else if (57 == b2) {
                str = "PDN type IPv4v6 only allowed";
            } else if (58 == b2) {
                str = "PDN type non IP only allowed";
            } else if (59 == b2) {
                str = "Unsupported QCI value";
            } else if (60 == b2) {
                str = "Bearer handling not supported";
            } else if (65 == b2) {
                str = "Maximum number of EPS bearers reached";
            } else if (66 == b2) {
                str = "Requested APN not supported in current RAT and PLMN combination";
            } else if (81 == b2) {
                str = "Invalid PTI value";
            } else if (112 == b2) {
                str = "APN restriction value incompatible with active EPS bearer context";
            } else if (MODEM_STATS_DATA_ID_XXXX_DIAG_EVENT_LTE_EMM_TIMER == b2) {
                str = "Semantically incorrect message";
            } else if (MODEM_STATS_DATA_ID_B111_DIAG_LOG_LTE_LL1_RX_AGC == b2) {
                str = "Invalid mandatory information";
            } else if (MODEM_STATS_DATA_ID_B825_DIAG_LOG_NR5G_RRC_CONFIGURATION_INFO == b2) {
                str = "Message type non-existent or not implemented";
            } else if (MODEM_STATS_DATA_ID_B16E_DIAG_LOG_LTE_PUSCH_POWER_CONTROL == b2) {
                str = "Message type not compatible with protocol state";
            } else if (99 == b2) {
                str = "Information element non-existent or not implemented";
            } else if (100 == b2) {
                str = "Conditional IE error";
            } else if (101 == b2) {
                str = "Message not compatible with protocol state";
            } else if (111 == b2) {
                str = "Protocol error, unspecified";
            } else {
                log("getEmmCauseString, val: " + ((int) b2));
                str = "UNKnown";
            }
            b3 = -1;
        }
        if (b3 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    private static ArrayList getGroupNameListForNonStop() {
        ArrayList arrayList = mArrayNonStopGroupName;
        if (arrayList != null) {
            return arrayList;
        }
        mArrayNonStopGroupName = new ArrayList();
        ArrayList arrayList2 = mListForGroup;
        if (arrayList2 == null) {
            log("mListForGroup is null");
            return mArrayNonStopGroupName;
        }
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((ModemStatsGroup) mListForGroup.get(i2)).mInitStart) {
                mArrayNonStopGroupName.add(((ModemStatsGroup) mListForGroup.get(i2)).mGroupName);
            }
        }
        log("getGroupNameListForNonStop, mArrayNonStopGroupName = " + mArrayNonStopGroupName);
        return mArrayNonStopGroupName;
    }

    private ModemStatsGroup getGroupPtrByName(String str) {
        ArrayList arrayList = mListForGroup;
        if (arrayList == null) {
            log("getGroupPtrByName, mListForGroup is null");
            return null;
        }
        if (str == null) {
            log("getGroupPtrByName, groupName is null");
            return null;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ModemStatsGroup modemStatsGroup = (ModemStatsGroup) mListForGroup.get(i2);
            if (true == str.equals(modemStatsGroup.mGroupName)) {
                tmpLog("getGroupPtrByName, size = " + size + ", groupName = " + str);
                return modemStatsGroup;
            }
        }
        tmpLog("This group is not defined, size = " + size + ", groupName = " + str);
        return null;
    }

    private int getHydraData(ArrayList arrayList, int i2) {
        String str;
        String str2;
        getDataLog("getHydraData, sub = " + i2 + ", arrGroupName: " + arrayList);
        if (arrayList == null) {
            log("arrGroupName is null");
            return 0;
        }
        if (mMtbHookAgent == null) {
            log("mMtbHookAgent is null");
            return 0;
        }
        int size = arrayList.size();
        getDataLog("sizeGroup = " + size);
        getDataLog("hook size: " + MODEM_STATS_DEREG_BYTE_SIZE_MAX + ", msgType: 62");
        ByteBuffer buffer = ModemUtils.getBuffer(MODEM_STATS_DEREG_BYTE_SIZE_MAX);
        String str3 = "bufHook is null";
        if (buffer == null) {
            log("bufHook is null");
            return 0;
        }
        buffer.putInt(i2);
        buffer.putInt(10);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int iSaveGetData = 0;
        while (true) {
            String str4 = "------ Fail to get hydra data, ret: ";
            if (i3 >= size) {
                int i6 = iSaveGetData;
                if (i4 > 0) {
                    getDataLog("------ End hook-sending for getting data, sub: " + i2);
                    getDataLog("idSum: " + i5 + ", sub: " + i2 + ", idNum: " + i4);
                    buffer.putInt(4, i4);
                    ByteBuffer byteBufferOnHookCommonMsgSync = mMtbHookAgent.onHookCommonMsgSync(62, buffer.array());
                    if (byteBufferOnHookCommonMsgSync == null) {
                        log("bufRet is null");
                        log("------ Not Support to get hydra data, sub: " + i2);
                        return 0;
                    }
                    int i7 = byteBufferOnHookCommonMsgSync.getInt();
                    if (i7 == 0) {
                        return i6 + saveGetData(i2, byteBufferOnHookCommonMsgSync);
                    }
                    getDataLog("------ Fail to get hydra data, ret: " + i7 + ", sub: " + i2);
                } else {
                    getDataLog("------ No hook-sending for getting data, sub: " + i2);
                }
                return i6;
            }
            boolean zIsDataBySub = isDataBySub((String) arrayList.get(i3));
            int i8 = size;
            StringBuilder sb = new StringBuilder();
            int i9 = iSaveGetData;
            sb.append("arrGroupName[");
            sb.append(i3);
            sb.append("]: ");
            sb.append((String) arrayList.get(i3));
            sb.append(", bySub: ");
            sb.append(zIsDataBySub);
            getDataLog(sb.toString());
            if (zIsDataBySub || i2 <= 0) {
                Iterator it = mMapForId2Group.keySet().iterator();
                iSaveGetData = i9;
                while (it.hasNext()) {
                    int iIntValue = ((Integer) it.next()).intValue();
                    it = it;
                    str3 = str3;
                    ModemStatsItem modemStatsItem = (ModemStatsItem) mMapForId2Group.get(Integer.valueOf(iIntValue));
                    if (modemStatsItem == null) {
                        log("item is null, continue, id: " + iIntValue);
                    } else {
                        if (!isInGroup(iIntValue, (String) arrayList.get(i3))) {
                            str2 = str4;
                        } else if (modemStatsItem.mIndType) {
                            getDataLog("item is ind type data, will not get it, mGroupName: " + ((String) arrayList.get(i3)) + ", sub: " + i2 + ", id: " + iIntValue);
                        } else {
                            String str5 = str4;
                            getDataLog("get[" + i4 + "], mGroupName: " + ((String) arrayList.get(i3)) + ", sub: " + i2 + ", id: " + iIntValue);
                            buffer.putInt(iIntValue);
                            i4++;
                            i5++;
                            if (i4 >= 10) {
                                getDataLog("------ ID count reach the max vlau: 10, will send hook msg for getting data, sub: " + i2);
                                getDataLog("idSum: " + i5 + ", idNum: " + i4);
                                ByteBuffer byteBufferOnHookCommonMsgSync2 = mMtbHookAgent.onHookCommonMsgSync(62, buffer.array());
                                if (byteBufferOnHookCommonMsgSync2 == null) {
                                    log("bufRet is null");
                                    log("------ Not Support to get hydra data, sub: " + i2);
                                    return 0;
                                }
                                int i10 = byteBufferOnHookCommonMsgSync2.getInt();
                                if (i10 == 0) {
                                    iSaveGetData += saveGetData(i2, byteBufferOnHookCommonMsgSync2);
                                    str2 = str5;
                                } else {
                                    StringBuilder sb2 = new StringBuilder();
                                    str2 = str5;
                                    sb2.append(str2);
                                    sb2.append(i10);
                                    sb2.append(", sub: ");
                                    sb2.append(i2);
                                    getDataLog(sb2.toString());
                                }
                                buffer = ModemUtils.getBuffer(MODEM_STATS_DEREG_BYTE_SIZE_MAX);
                                if (buffer == null) {
                                    log(str3);
                                    return 0;
                                }
                                buffer.putInt(i2);
                                buffer.putInt(10);
                                i4 = 0;
                            } else {
                                str2 = str5;
                            }
                        }
                        str4 = str2;
                    }
                    str4 = str4;
                }
                str = str3;
            } else {
                getDataLog("not group by sub, only get sub0");
                str = str3;
                iSaveGetData = i9;
            }
            i3++;
            size = i8;
            str3 = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLteBandWidthIdxString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (b2 == 0) {
            str = "1.4MHz";
        } else if (1 == b2) {
            str = "3MHz";
        } else if (2 == b2) {
            str = "5MHz";
        } else if (3 == b2) {
            str = "10MHz";
        } else if (4 == b2) {
            str = "15MHz";
        } else if (5 == b2) {
            str = "20MHz";
        } else {
            log("getLteBandWidthIdxString, bw: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    private String getLteBandWidthString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (6 == i2) {
            str = "1.4MHz_6RB";
        } else if (15 == i2) {
            str = "3MHz_15RB";
        } else if (25 == i2) {
            str = "5MHz_25RB";
        } else if (50 == i2) {
            str = "10MHz_50RB";
        } else if (75 == i2) {
            str = "15MHz_75RB";
        } else if (100 == i2) {
            str = "20MHz_100RB";
        } else {
            log("getLteBandWidthString, bw: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLteIdleOrConnectString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (1 == i2) {
            str = "RRC-IDLE";
        } else if (i2 == 0) {
            str = "RRC-CONNECTED";
        } else {
            log("getLteIdleOrConnectString, idle: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLteModulationString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (b2 == 0) {
            str = "BPSK";
        } else if (1 == b2) {
            str = "QPSK";
        } else if (2 == b2) {
            str = "16QAM";
        } else if (3 == b2) {
            str = "64QAM";
        } else if (4 == b2) {
            str = "256QAM";
        } else if (5 == b2) {
            str = "1024QAM";
        } else if (LTE_MODULATION_INVALID == b2) {
            str = MODEM_STATS_DATA_INVALID;
        } else {
            log("getLteModulationString, mod: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getLtePdschModulationString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (2 == b2) {
            str = "QPSK";
        } else if (4 == b2) {
            str = "16QAM";
        } else if (6 == b2) {
            str = "64QAM";
        } else if (8 == b2) {
            str = "256QAM";
        } else if (10 == b2) {
            str = "1024QAM";
        } else {
            log("getLteModulationString, mod: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNasProcedureString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (-1 == b2) {
            str = "FOR INTERNAL USE OF CM ONLY";
        } else if (1 == b2) {
            str = "MM";
        } else if (2 == b2) {
            str = "CM";
        } else if (3 == b2) {
            str = "GMM_NON_COMBINED";
        } else if (4 == b2) {
            str = "SM";
        } else if (5 == b2) {
            str = "GMM_COMBINED";
        } else if (6 == b2) {
            str = "MM_GMM_COMBINED";
        } else if (7 == b2) {
            str = "EMM_NON_COMBINED_EPS";
        } else if (8 == b2) {
            str = "EMM_COMBINED_NON_EPS";
        } else if (9 == b2) {
            str = "EMM_COMBINED";
        } else if (10 == b2) {
            str = "ESM";
        } else {
            log("getNasProcedureString, val: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getNetworkStatus(int i2) {
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager != null) {
            ByteBuffer data = mtbHydraDataManager.getData(45, i2);
            if (ModemUtils.min(data != null ? data.get() : (byte) 0, 2) > 0) {
                return data.getInt();
            }
            return -1;
        }
        log("getNetworkStatus, not init, do nothing, sub = " + i2);
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNetworkStatusString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (-1 == i2) {
            str = "None";
        } else if (i2 == 0) {
            str = "No service";
        } else if (1 == i2) {
            str = "Limited service";
        } else if (2 == i2) {
            str = "Service available";
        } else if (3 == i2) {
            str = "Limited regional service";
        } else if (4 == i2) {
            str = "Power save or Deep sleep";
        } else if (5 == i2) {
            str = "No service FOR INTERNAL";
        } else if (6 == i2) {
            str = "Limited service FOR INTERNAL";
        } else if (7 == i2) {
            str = "Limited regional service FOR INTERNAL";
        } else if (8 == i2) {
            str = "Power save FOR INTERNAL";
        } else {
            log("getNetworkStatusString, status: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    public static int getNetworkType(int i2) {
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager != null) {
            ByteBuffer data = mtbHydraDataManager.getData(44, i2);
            if (ModemUtils.min(data != null ? data.get() : (byte) 0, 2) > 0) {
                return data.getInt();
            }
            return -1;
        }
        log("getNetworkType, not init, do nothing, sub = " + i2);
        return -1;
    }

    public static String getNetworkTypeString(byte b2) {
        return getNetworkTypeString(getNetworkType(b2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNr5gBandTypeString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (b2 == 0) {
            str = "UNKNOWN";
        } else if (1 == b2) {
            str = "SUB6";
        } else if (2 == b2) {
            str = "MMW";
        } else {
            log("getNr5gBandTypeString, bandType: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNr5gBandWidthIdxString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (b2 == 0) {
            str = "5MHz";
        } else if (1 == b2) {
            str = "10MHz";
        } else if (2 == b2) {
            str = "15MHz";
        } else if (3 == b2) {
            str = "20MHz";
        } else if (4 == b2) {
            str = "25MHz";
        } else if (5 == b2) {
            str = "30MHz";
        } else if (6 == b2) {
            str = "40MHz";
        } else if (7 == b2) {
            str = "50MHz";
        } else if (8 == b2) {
            str = "60MHz";
        } else if (9 == b2) {
            str = "70MHz";
        } else if (10 == b2) {
            str = "80MHz";
        } else if (11 == b2) {
            str = "90MHz";
        } else if (12 == b2) {
            str = "100MHz";
        } else if (13 == b2) {
            str = "200MHz";
        } else if (14 == b2) {
            str = "400MHz";
        } else {
            log("getNr5gBandWidthIdxString, bw: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNr5gBandWidthIdxV3String(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (b2 == 0) {
            str = "5MHz";
        } else if (1 == b2) {
            str = "10MHz";
        } else if (2 == b2) {
            str = "15MHz";
        } else if (3 == b2) {
            str = "20MHz";
        } else if (4 == b2) {
            str = "25MHz";
        } else if (5 == b2) {
            str = "30MHz";
        } else if (6 == b2) {
            str = "40MHz";
        } else if (7 == b2) {
            str = "50MHz";
        } else if (8 == b2) {
            str = "60MHz";
        } else if (9 == b2) {
            str = "80MHz";
        } else if (10 == b2) {
            str = "90MHz";
        } else if (11 == b2) {
            str = "100MHz";
        } else if (12 == b2) {
            str = "200MHz";
        } else if (13 == b2) {
            str = "400MHz";
        } else {
            log("getNr5gBandWidthIdxV3String, bw: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNr5gPdschModulationString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (b2 == 0) {
            str = "QPSK";
        } else if (2 == b2) {
            str = "16QAM";
        } else if (3 == b2) {
            str = "64QAM";
        } else if (4 == b2) {
            str = "256QAM";
        } else if (5 == b2) {
            str = "1024QAM";
        } else {
            log("getNr5gPdschModulationString, mod: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNr5gRbPathString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (b2 == 0) {
            str = "NONE";
        } else if (1 == b2) {
            str = "LTE";
        } else if (2 == b2) {
            str = "NR";
        } else if (3 == b2) {
            str = "LTE_AND_NR";
        } else if (4 == b2) {
            str = "MAX";
        } else if (b2 == 0) {
            str = "INVALID";
        } else {
            log("getNr5gRbPathString, rbPath: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNr5gRbTypeString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else {
            str = "INVALID";
            if (b2 != 0) {
                if (1 == b2) {
                    str = "SRB";
                } else if (2 == b2) {
                    str = "DRB";
                } else if (3 == b2) {
                    str = "MTCH";
                } else if (b2 != 0) {
                    log("getNr5gRbTypeString, rbType: " + ((int) b2));
                    str = "UNKnown";
                }
            }
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    public static HashMap getPrefGroupMap() {
        log("getPrefGroupMap, mMapDataGroupPref = " + mMapDataGroupPref);
        onInitMapDataGroupPref();
        return mMapDataGroupPref;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getRlfType(int i2) {
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager == null) {
            log("getRlfType, not init, do nothing, sub = " + i2);
            return -1;
        }
        ByteBuffer data = mtbHydraDataManager.getData(57, i2);
        int i3 = data != null ? data.getInt() : -1;
        if (mInstance.getData(81, i2) != null) {
            i3 = 1;
        }
        if (mInstance.getData(80, i2) != null) {
            return 5;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getRlfTypeString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "None";
        } else if (1 == i2) {
            str = "LTE RLF happened";
        } else if (2 == i2) {
            str = "WCDMA RLF happened";
        } else if (3 == i2) {
            str = "GSM RLF happened";
        } else if (4 == i2) {
            str = "CDMA RLF happened";
        } else if (5 == i2) {
            str = "NR5G RLF happened";
        } else {
            log("getRlfTypeString, type: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte getSrvDomain(int i2) {
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager != null) {
            ByteBuffer data = mtbHydraDataManager.getData(47, i2);
            if (ModemUtils.min(data != null ? data.get() : (byte) 0, 2) >= 1) {
                return data.get();
            }
            return (byte) -1;
        }
        log("getSrvDomain, not init, do nothing, sub = " + i2);
        return (byte) -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSrvDomainString(byte b2) {
        String str;
        if (-1 == b2) {
            str = "-";
        } else if (-1 == b2) {
            str = "NONE";
        } else if (b2 == 0) {
            str = "NO_SRV";
        } else if (1 == b2) {
            str = "CS_ONLY";
        } else if (2 == b2) {
            str = "PS_ONLY";
        } else if (3 == b2) {
            str = "CS_PS";
        } else if (4 == b2) {
            str = "CAMPED";
        } else if (5 == b2) {
            str = "CS_VOLTE";
        } else if (6 == b2) {
            str = "VOLTE";
        } else {
            log("getSrvDomainString, val: " + ((int) b2));
            str = "UNKnown";
        }
        if (-1 == b2) {
            return str;
        }
        return "[" + ((int) b2) + "]" + str;
    }

    private boolean getStatsGetPrintFlag() {
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("getStatsGetPrintFlag, mMtbHookAgent is null");
            return false;
        }
        String strOnHookPropGetSync = oemHookAgent.onHookPropGetSync("persist.vendor.radio.print_stats", "0");
        log("getStatsGetPrintFlag, str = " + strOnHookPropGetSync);
        return "1".equals(strOnHookPropGetSync);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getString(Map map, String str, String str2, boolean z) {
        if (!z) {
            String str3 = "-";
            if (map != null && map.get(str) != null) {
                str3 = (String) map.get(str);
            }
            str2 = str3;
        } else if (map != null) {
            map.put(str, str2);
        }
        return str + ": " + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getUimAppTypeString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "NONE";
        } else if (1 == i2) {
            str = "SIM";
        } else if (2 == i2) {
            str = "RUIM";
        } else if (3 == i2) {
            str = "USIM";
        } else if (4 == i2) {
            str = "CSIM";
        } else if (5 == i2) {
            str = "UNKNOWN";
        } else {
            log("getUimAppTypeString, appType: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getUimPinStatusString(int i2) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "Status of PIN checking is not initialized";
        } else if (1 == i2) {
            str = "PIN checking is enabled but the PIN has not been verified";
        } else if (2 == i2) {
            str = "PIN checking is enabled and the PIN has been verified";
        } else if (3 == i2) {
            str = "PIN checking has been disabled";
        } else if (4 == i2) {
            str = "After three consecutive false PIN presentations = 0; the PIN has been blocked";
        } else if (5 == i2) {
            str = "PIN has been permanently blocked";
        } else if (6 == i2) {
            str = "PIN is enabled again after having been blocked";
        } else if (7 == i2) {
            str = "PIN has been changed";
        } else if (8 == i2) {
            str = "PIN is not found";
        } else if (9 == i2) {
            str = "PIN retry count decreased";
        } else {
            log("getUimPinStatusString, pinStatus: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getVoiceAudioQualityString(int i2) {
        String str;
        if (-1 == i2 || 65535 == i2) {
            str = "-";
        } else if (1 == i2) {
            str = "No HD";
        } else if (2 == i2) {
            str = "HD";
        } else if (3 == i2) {
            str = "HD+";
        } else {
            log("getVoiceAudioQualityString, audioQuality: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getVoiceNetworkModeString(int i2) {
        String str;
        if (-1 == i2 || 65535 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "NONE";
        } else if (1 == i2) {
            str = "GSM";
        } else if (2 == i2) {
            str = "WCDMA";
        } else if (3 == i2) {
            str = "CDMA";
        } else if (4 == i2) {
            str = "LTE";
        } else if (5 == i2) {
            str = "TD-SCDMA";
        } else if (6 == i2) {
            str = "WLAN";
        } else if (7 == i2) {
            str = "NR5G";
        } else if (8 == i2) {
            str = "C_IWLAN";
        } else {
            log("getVoiceNetworkModeString, networkMode: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getVoiceSpeechCodecString(int i2) {
        String str;
        if (-1 == i2 || 65535 == i2) {
            str = "-";
        } else if (i2 == 0) {
            str = "NONE";
        } else if (1 == i2) {
            str = "QCELP-13K";
        } else if (2 == i2) {
            str = "EVRC";
        } else if (3 == i2) {
            str = "EVRC-B";
        } else if (4 == i2) {
            str = "EVRC wideband";
        } else if (5 == i2) {
            str = "EVRC narrowband-wideband";
        } else if (6 == i2) {
            str = "AMR narrowband";
        } else if (7 == i2) {
            str = "AMR wideband";
        } else if (8 == i2) {
            str = "GSM enhanced full rate";
        } else if (9 == i2) {
            str = "GSM full rate";
        } else if (10 == i2) {
            str = "GSM half rate";
        } else if (11 == i2) {
            str = "G711U";
        } else if (12 == i2) {
            str = "G723";
        } else if (13 == i2) {
            str = "G711A";
        } else if (14 == i2) {
            str = "G722";
        } else if (15 == i2) {
            str = "G711AB";
        } else if (16 == i2) {
            str = "G729";
        } else if (17 == i2) {
            str = "EVS narrowband";
        } else if (18 == i2) {
            str = "EVS wideband";
        } else if (19 == i2) {
            str = "EVS super-wideband";
        } else if (20 == i2) {
            str = "EVS fullband";
        } else {
            log("getVoiceSpeechCodecString, speechCodec: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    private boolean groupIsForInit(String str) {
        if (str == null) {
            log("groupIsForInit, groupName is null");
            return false;
        }
        ArrayList arrayList = mListForGroup;
        if (arrayList == null) {
            log("groupIsForInit, mListForGroup is null, groupName = " + str);
            return false;
        }
        int size = arrayList.size();
        new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            if (((ModemStatsGroup) mListForGroup.get(i2)).mInitStart && str.equals(((ModemStatsGroup) mListForGroup.get(i2)).mGroupName)) {
                log("This group is reg for init, groupName = " + str);
                return true;
            }
        }
        return false;
    }

    private void groupMgr(ArrayList arrayList, int i2, boolean z, boolean z2) {
        int i3;
        int i4;
        String str;
        int i5;
        String str2;
        int i6;
        int i7;
        String str3;
        String str4;
        boolean z3 = z2;
        log("groupMgr, mStatsClientId = " + mStatsClientId + ", sub = " + i2 + ", mNumPhones = " + mNumPhones + ", reg = " + z + ", forceDereg = " + z3 + ", mModemStatsIntervalSec = " + mModemStatsIntervalSec + ", arrGroupName: " + arrayList);
        if (arrayList == null) {
            log("arrGroupName is null");
            return;
        }
        if (mMtbHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        if (mStatsClientId >= 10) {
            log("mStatsClientId is not yet inited");
            return;
        }
        int size = arrayList.size();
        log("sizeGroup = " + size);
        if (size <= 0) {
            log("sizeGroup <= 0, do nothing");
            return;
        }
        if (z) {
            i3 = MODEM_STATS_REG_BYTE_SIZE_MAX;
            i4 = 60;
            str = "register";
        } else {
            i3 = MODEM_STATS_DEREG_BYTE_SIZE_MAX;
            i4 = 61;
            str = "deregister";
        }
        log("hook size: " + i3 + ", msgType: " + i4);
        ByteBuffer buffer = ModemUtils.getBuffer(i3);
        String str5 = "bufHook is null";
        if (buffer == null) {
            log("bufHook is null");
            return;
        }
        buffer.putInt(mStatsClientId);
        buffer.putInt(i2);
        buffer.putInt(100);
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            String str6 = "------ Not Support to reg/dereg hydra data, sub: ";
            String str7 = ", idNum: ";
            int i11 = i10;
            ByteBuffer byteBuffer = buffer;
            if (i8 >= size) {
                int i12 = i4;
                int i13 = i9;
                if (i13 > 0) {
                    log("------ End hook-sending for " + str + ", sub: " + i2);
                    log("mStatsClientId: " + mStatsClientId + ", idNum: " + i13);
                    byteBuffer.putInt(8, i13);
                    if (mMtbHookAgent.onHookCommonMsgSync(i12, byteBuffer.array()) == null) {
                        log("------ Not Support to reg/dereg hydra data, sub: " + i2);
                        return;
                    }
                } else {
                    log("------ No hook-sending for " + str + ", sub: " + i2);
                }
                if (z) {
                    mSub = i2;
                    log("------ REG, mSub = " + mSub + ", idSum: " + i11 + ", mArrCurGroupName: " + mArrCurGroupName);
                    return;
                }
                mSub = 2;
                log("------ DEREG, mSub = " + mSub + ", idSum: " + i11 + ", mArrCurGroupName: " + mArrCurGroupName);
                return;
            }
            int i14 = size;
            StringBuilder sb = new StringBuilder();
            int i15 = i9;
            sb.append("arrGroupName[");
            sb.append(i8);
            sb.append("]: ");
            sb.append((String) arrayList.get(i8));
            log(sb.toString());
            if (groupIsForInit((String) arrayList.get(i8))) {
                if (!z && !z3) {
                    log("This group is needed all the time from init, so can not dereg");
                    i5 = i3;
                    str2 = str5;
                    i10 = i11;
                    buffer = byteBuffer;
                    i9 = i15;
                    i6 = i4;
                }
                i8++;
                z3 = z2;
                i4 = i6;
                size = i14;
                str5 = str2;
                i3 = i5;
            } else {
                clearGroupData((String) arrayList.get(i8), i2);
            }
            Iterator it = mMapForId2Group.keySet().iterator();
            ByteBuffer byteBuffer2 = byteBuffer;
            int i16 = i15;
            while (it.hasNext()) {
                it = it;
                int iIntValue = ((Integer) it.next()).intValue();
                str5 = str5;
                i3 = i3;
                if (isInGroup(iIntValue, (String) arrayList.get(i8))) {
                    String str8 = str6;
                    ModemStatsItem modemStatsItem = (ModemStatsItem) mMapForId2Group.get(Integer.valueOf(iIntValue));
                    if (modemStatsItem == null) {
                        log("item is null, continue, id: " + iIntValue);
                        i4 = i4;
                        str7 = str7;
                    } else {
                        if (z) {
                            log("add[" + i16 + "], mGroupName: " + ((String) arrayList.get(i8)) + ", id: " + iIntValue + ", mIndType: " + modemStatsItem.mIndType);
                        } else {
                            log("remove[" + i16 + "], mGroupName: " + ((String) arrayList.get(i8)) + ", id: " + iIntValue + ", mIndType: " + modemStatsItem.mIndType);
                        }
                        if (modemStatsItem.mIndType) {
                            byteBuffer2.putInt(iIntValue);
                            if (z) {
                                byteBuffer2.putInt(mModemStatsIntervalSec);
                            }
                            i16++;
                            i11++;
                            if (i16 >= 100) {
                                log("------ ID count reach the max vlau: 100, will send hook msg for " + str + ", sub: " + i2);
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("mStatsClientId: ");
                                sb2.append(mStatsClientId);
                                str3 = str7;
                                sb2.append(str3);
                                sb2.append(i16);
                                log(sb2.toString());
                                i7 = i4;
                                if (mMtbHookAgent.onHookCommonMsgSync(i7, byteBuffer2.array()) == null) {
                                    log(str8 + i2);
                                    return;
                                }
                                str4 = str8;
                                ByteBuffer buffer2 = ModemUtils.getBuffer(i3);
                                if (buffer2 == null) {
                                    log(str5);
                                    return;
                                }
                                buffer2.putInt(mStatsClientId);
                                buffer2.putInt(i2);
                                buffer2.putInt(100);
                                byteBuffer2 = buffer2;
                                i16 = 0;
                            } else {
                                str4 = str8;
                                i7 = i4;
                                str3 = str7;
                            }
                        } else {
                            log("item is not ind type data, will not reg or dereg it, mGroupName: " + ((String) arrayList.get(i8)) + ", sub: " + i2 + ", id: " + iIntValue);
                        }
                    }
                    str6 = str8;
                    i4 = i4;
                    str7 = str7;
                } else {
                    i7 = i4;
                    str3 = str7;
                    str4 = str6;
                    log("id not In Group, [" + i16 + "], mGroupName: " + ((String) arrayList.get(i8)) + ", id: " + iIntValue);
                    byteBuffer2 = byteBuffer2;
                }
                str6 = str4;
                str7 = str3;
                i4 = i7;
            }
            i5 = i3;
            str2 = str5;
            ByteBuffer byteBuffer3 = byteBuffer2;
            i6 = i4;
            i9 = i16;
            i10 = i11;
            buffer = byteBuffer3;
            i8++;
            z3 = z2;
            i4 = i6;
            size = i14;
            str5 = str2;
            i3 = i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void indDataLog(String str) {
        if (mIndDataLogPrintFlag) {
            log(str);
        }
    }

    private boolean isDataBySub(String str) {
        ArrayList arrayList = mListForGroup;
        if (arrayList == null) {
            log("isDataBySub, mListForGroup is null");
            return true;
        }
        if (str == null) {
            log("isDataBySub, groupName is null");
            return true;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ModemStatsGroup modemStatsGroup = (ModemStatsGroup) mListForGroup.get(i2);
            if (true == str.equals(modemStatsGroup.mGroupName)) {
                tmpLog("isDataBySub, size = " + size + ", groupName = " + str + ", group.mBySub = " + modemStatsGroup.mBySub);
                return modemStatsGroup.mBySub;
            }
        }
        tmpLog("isInitGroup, this group is not defined, groupName = " + str);
        return true;
    }

    private boolean isHydraGroup(String str) {
        if (getGroupPtrByName(str) == null) {
            tmpLog("isHydraGroup, not find this hydra group, groupName = " + str);
            return false;
        }
        tmpLog("it is a hydra grop, groupName = " + str);
        return true;
    }

    private boolean isInitGroup(String str) {
        ModemStatsGroup groupPtrByName = getGroupPtrByName(str);
        if (groupPtrByName == null) {
            tmpLog("isInitGroup, this group is not defined, groupName = " + str);
            return true;
        }
        tmpLog("isInitGroup, groupName = " + str + ", group.mInitStart = " + groupPtrByName.mInitStart);
        return groupPtrByName.mInitStart;
    }

    private boolean isShow(String str) {
        ModemStatsGroup groupPtrByName = getGroupPtrByName(str);
        if (groupPtrByName == null) {
            log("isShow, group is null");
            return false;
        }
        tmpLog("will not show, groupName = " + str);
        return groupPtrByName.mBShow;
    }

    private static boolean judgeGroupSame(ArrayList arrayList, int i2) {
        boolean z;
        boolean z2;
        boolean z3;
        log("judgeGroupSame, sub = " + i2 + ", mSub = " + mSub + ", mNumPhones = " + mNumPhones + ", arrGroupName: " + arrayList + ", mArrCurGroupName: " + mArrCurGroupName);
        if (i2 != mSub) {
            log("sub changed");
            return false;
        }
        ArrayList arrayList2 = mArrCurGroupName;
        if (arrayList2 == null || arrayList == null) {
            log("group is null");
            return false;
        }
        if (mInstance == null) {
            log("mInstance is null");
            return false;
        }
        int size = arrayList2.size();
        int size2 = arrayList.size();
        int i3 = 0;
        while (true) {
            boolean z4 = true;
            if (i3 >= size2) {
                for (int i4 = 0; i4 < size; i4++) {
                    Iterator it = mMapForId2Group.keySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        if (mInstance.isInGroup(((Integer) it.next()).intValue(), (String) mArrCurGroupName.get(i4))) {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        int i5 = 0;
                        while (true) {
                            if (i5 >= size2) {
                                z2 = false;
                                break;
                            }
                            if (((String) mArrCurGroupName.get(i4)).equals(arrayList.get(i5))) {
                                z2 = true;
                                break;
                            }
                            i5++;
                        }
                        if (!z2) {
                            log("mArrCurGroupName is more");
                            return false;
                        }
                    }
                }
                return true;
            }
            Iterator it2 = mMapForId2Group.keySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z3 = false;
                    break;
                }
                if (mInstance.isInGroup(((Integer) it2.next()).intValue(), (String) arrayList.get(i3))) {
                    z3 = true;
                    break;
                }
            }
            if (z3) {
                int i6 = 0;
                while (true) {
                    if (i6 >= size) {
                        z4 = false;
                        break;
                    }
                    if (((String) arrayList.get(i3)).equals(mArrCurGroupName.get(i6))) {
                        break;
                    }
                    i6++;
                }
                if (!z4) {
                    log("arrGroupName is more");
                    return false;
                }
            }
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    public static void make(MtbDataDisplayActivity mtbDataDisplayActivity) {
        if (mInstance != null) {
            log("make mInstance none, hydraMgr mInstance = " + mInstance);
            return;
        }
        mInstance = new MtbHydraDataManager(mtbDataDisplayActivity);
        log("make mInstance done, hydraMgr mInstance = " + mInstance);
    }

    public static void onClearHydraDataInfo() {
        log("onClearHydraDataInfo, mSub = " + mSub + ", mArrCurGroupName: " + mArrCurGroupName);
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager == null) {
            log("not init, do nothing");
        } else {
            mtbHydraDataManager.parseAndUpdateData(mArrCurGroupName, mSub, true);
        }
    }

    public static void onHandleMessage(Message message) {
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager == null) {
            log("not init, do nothing");
        } else {
            mtbHydraDataManager.onHandleMessageDo(message);
        }
    }

    private void onInitListForGroup() {
        if (mListForGroup != null) {
            log("onInitListForGroup already, mListForGroup: " + mListForGroup + ", mListForGroup.size: " + mListForGroup.size());
            return;
        }
        ArrayList arrayList = new ArrayList();
        mListForGroup = arrayList;
        arrayList.add(new ModemStatsGroupNonStopCmn());
        mListForGroup.add(new ModemStatsGroupNonStopSub());
        mListForGroup.add(new ModemStatsGroupBasicCmnStatus());
        mListForGroup.add(new ModemStatsGroupBasicSubStatus());
        mListForGroup.add(new ModemStatsGroupNas());
        mListForGroup.add(new ModemStatsGroupCall());
        mListForGroup.add(new ModemStatsGroupUim());
        mListForGroup.add(new ModemStatsGroupData());
        mListForGroup.add(new ModemStatsGroupRf());
        mListForGroup.add(new ModemStatsGroupGps());
        mListForGroup.add(new ModemStatsGroupLteSrvBasic());
        mListForGroup.add(new ModemStatsGroupLteSrvDetail());
        mListForGroup.add(new ModemStatsGroupLteCa());
        mListForGroup.add(new ModemStatsGroupLteNbr());
        mListForGroup.add(new ModemStatsGroupNr5GSrv());
        mListForGroup.add(new ModemStatsGroupPhoneSubInfoMwc());
        mListForGroup.add(new ModemStatsGroupSubSignalInfo());
        mListForGroup.add(new ModemStatsGroupSubTxInfo());
        mListForGroup.add(new ModemStatsGroupSubVoiceInfo());
        log("onInitListForGroup done, mListForGroup: " + mListForGroup + ", mListForGroup.size: " + mListForGroup.size());
    }

    private static void onInitMapDataGroupPref() {
        if (mMapDataGroupPref != null) {
            return;
        }
        mMapDataGroupPref = new HashMap();
    }

    public static int onUpdateHydraDataInfo() {
        int hydraData;
        getDataLog("onUpdateHydraDataInfo, mSub = " + mSub + ", mArrCurGroupName: " + mArrCurGroupName);
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager == null) {
            log("not init, do nothing");
            return 0;
        }
        ArrayList arrayList = mArrCurGroupName;
        if (arrayList == null) {
            log("mArrCurGroupName is null, do nothing");
            return 0;
        }
        int i2 = mSub;
        if (2 == i2) {
            hydraData = 0;
            for (int i3 = 0; i3 < mNumPhones; i3++) {
                hydraData = hydraData + mInstance.getHydraData(mArrCurGroupName, i3) + mInstance.getHydraData(getGroupNameListForNonStop(), i3);
            }
        } else {
            hydraData = mInstance.getHydraData(getGroupNameListForNonStop(), mSub) + mtbHydraDataManager.getHydraData(arrayList, i2) + 0;
        }
        mInstance.parseAndUpdateData(mArrCurGroupName, mSub, false);
        return hydraData;
    }

    private void onUpdateOptStatusView(boolean z, String str) {
        MtbDataDisplayActivity mtbDataDisplayActivity = mView;
        if (mtbDataDisplayActivity == null) {
            log("onUpdateOptStatusView, mView is null");
        } else {
            mtbDataDisplayActivity.onUpdateOptStatusView(z, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseAndUpdateData(ArrayList arrayList, int i2, boolean z) {
        String string;
        parseDataLog("parseAndUpdateData, sub = " + i2 + ", mNumPhones = " + mNumPhones + ", clear = " + z + ", arrGroupName: " + arrayList);
        if (mMapDataDisplayAllValue == null) {
            log("mMapDataDisplayAllValue is null");
            return;
        }
        if (arrayList == null) {
            log("arrGroupName is null");
            return;
        }
        if (mNumPhones <= 0) {
            log("mNumPhones is invalid");
            return;
        }
        int size = arrayList.size();
        parseDataLog("sizeGroup = " + size);
        String[] strArr = new String[mNumPhones];
        for (int i3 = 0; i3 < size; i3++) {
            parseDataLog("arrGroupName[" + i3 + "]: " + ((String) arrayList.get(i3)));
            if (isHydraGroup((String) arrayList.get(i3))) {
                if (isShow((String) arrayList.get(i3))) {
                    boolean zIsDataBySub = isDataBySub((String) arrayList.get(i3));
                    for (int i4 = 0; i4 < mNumPhones; i4++) {
                        try {
                            strArr[i4] = (true != z || isInitGroup((String) arrayList.get(i3))) ? formatString((String) arrayList.get(i3), i4) : null;
                        } catch (Exception e2) {
                            onUpdateOptStatusView(true, "Exception happen during parsing modem data");
                            log("parseAndUpdateData happen Exception: " + e2);
                            e2.printStackTrace();
                        }
                    }
                    if (!zIsDataBySub || 1 == mNumPhones) {
                        String str = strArr[0];
                        string = str != null ? str : "null";
                    } else if (2 == i2) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(ModemUtils.toHtmlFormatColor("--->SUB0:\n", COLOR_TXT_SUB_NAME));
                        String str2 = strArr[0];
                        if (str2 == null) {
                            str2 = "null";
                        }
                        sb.append(str2);
                        string = sb.toString();
                        for (int i5 = 1; i5 < mNumPhones; i5++) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(string);
                            sb2.append(ModemUtils.toHtmlFormatColor("\n--->SUB" + i5 + ":\n", COLOR_TXT_SUB_NAME));
                            String str3 = strArr[i5];
                            if (str3 == null) {
                                str3 = "null";
                            }
                            sb2.append(str3);
                            string = sb2.toString();
                        }
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(ModemUtils.toHtmlFormatColor("--->SUB" + i2 + ":\n", COLOR_TXT_SUB_NAME));
                        String str4 = strArr[i2];
                        sb3.append(str4 != null ? str4 : "null");
                        string = sb3.toString();
                    }
                    mMapDataDisplayAllValue.put((String) arrayList.get(i3), string);
                    parseDataLog("mMapDataDisplayAllValue, sub = " + i2 + ", mNumPhones = " + mNumPhones + ", arrGroupName = " + ((String) arrayList.get(i3)) + ", ShowText:\n" + string);
                } else if (2 == i2) {
                    try {
                        callMainUpdateProc((String) arrayList.get(i3), 0);
                        for (int i6 = 1; i6 < mNumPhones; i6++) {
                            callMainUpdateProc((String) arrayList.get(i3), i6);
                        }
                    } catch (Exception e3) {
                        onUpdateOptStatusView(true, "Exception happen during callMainUpdateProc");
                        log("parseAndUpdateData happen Exception: " + e3);
                        e3.printStackTrace();
                    }
                } else {
                    callMainUpdateProc((String) arrayList.get(i3), i2);
                }
            }
        }
    }

    private static void parseDataLog(String str) {
        if (mGetDataLogPrintFlag) {
            log(str);
        }
    }

    private void registerBcReceiver() {
        log("registerBcReceiver");
        if (mContext == null) {
            log("mContext is null");
            return;
        }
        deregisterBcReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        mContext.registerReceiver(this.mBroadcastReceiver, intentFilter);
        this.mIsBcRegistered = true;
    }

    private static void registerGroup(ArrayList arrayList, int i2) {
        log("registerGroup");
        MtbHydraDataManager mtbHydraDataManager = mInstance;
        if (mtbHydraDataManager == null) {
            log("not init, do nothing");
            return;
        }
        if (arrayList != null) {
            mtbHydraDataManager.groupMgr(arrayList, i2, true, false);
            return;
        }
        log("------ Invalid given group to register, sub: " + i2);
    }

    private void registerGroupForInit() {
        log("registerGroupForInit");
        groupMgr(getGroupNameListForNonStop(), 2, true, false);
    }

    private int saveGetData(int i2, ByteBuffer byteBuffer) {
        getDataLog("saveGetData, sub = " + i2);
        if (byteBuffer == null) {
            log("data is null");
            return 0;
        }
        int i3 = byteBuffer.getInt();
        getDataLog("idNum = " + i3);
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = byteBuffer.getInt();
            getDataLog("id[" + i5 + "]: " + i6);
            int i7 = byteBuffer.getInt();
            getDataLog("value_len[" + i5 + "]: " + i7);
            if (i7 <= 0 || i7 > 700) {
                log("Data is invalid, abort to parse");
                return 0;
            }
            byte[] bArr = new byte[i7];
            byteBuffer.get(bArr);
            ModemStatsItem modemStatsItem = (ModemStatsItem) mMapForId2Group.get(Integer.valueOf(i6));
            if (modemStatsItem == null) {
                log("item is null, continue, id: " + i6);
            } else {
                if (i2 == 0) {
                    modemStatsItem.mDataSub0 = bArr;
                } else {
                    modemStatsItem.mDataSub1 = bArr;
                }
                i4++;
            }
        }
        getDataLog("saveGetData, flag = " + i4);
        return i4;
    }

    public static int saveIndData(ByteBuffer byteBuffer) {
        indDataLog("saveIndData, mSub = " + mSub + ", mArrCurGroupName: " + mArrCurGroupName);
        if (mInstance == null) {
            log("not init, do nothing");
            return 0;
        }
        if (byteBuffer == null) {
            log("data is null");
            return 0;
        }
        int i2 = byteBuffer.getInt();
        int i3 = byteBuffer.getInt();
        indDataLog("stats_client_id = " + i2 + ", mStatsClientId = " + mStatsClientId + ", idNum = " + i3);
        if (mStatsClientId != i2) {
            log("stats_client_id not belong to mtb");
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = byteBuffer.getInt();
            indDataLog("sub[" + i5 + "]: " + i6);
            int i7 = byteBuffer.getInt();
            indDataLog("id[" + i5 + "]: " + i7);
            int i8 = byteBuffer.getInt();
            indDataLog("value_len[" + i5 + "]: " + i8);
            if (i8 <= 0 || i8 > 700) {
                log("Data is invalid, abort to parse");
                return 0;
            }
            byte[] bArr = new byte[i8];
            byteBuffer.get(bArr);
            ModemStatsItem modemStatsItem = (ModemStatsItem) mMapForId2Group.get(Integer.valueOf(i7));
            if (modemStatsItem == null) {
                log("item is null, continue, id: " + i7);
            } else {
                if (i6 == 0) {
                    modemStatsItem.mDataSub0 = bArr;
                } else if (1 == i6) {
                    modemStatsItem.mDataSub1 = bArr;
                } else {
                    log("sub is invalid, continue");
                }
                i4++;
            }
        }
        indDataLog("saveIndData, flag = " + i4);
        mInstance.parseAndUpdateData(mArrCurGroupName, mSub, false);
        return i4;
    }

    public static void startModemStats() {
        log("startModemStats, mStatsClientId: " + mStatsClientId);
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        ByteBuffer byteBufferOnHookHydraStatsSetSync = oemHookAgent.onHookHydraStatsSetSync(ModemUtils.getCurrentClassName(), 1);
        if (byteBufferOnHookHydraStatsSetSync == null) {
            log("bufRet is null");
            return;
        }
        int i2 = byteBufferOnHookHydraStatsSetSync.getInt();
        log("ret: " + i2);
        if (i2 != 0) {
            log("startModemStats fail");
        }
    }

    public static void startModemStatsBC() {
        log("startModemStatsBC");
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        ByteBuffer byteBufferOnHookMiBroadcastStateSetSync = oemHookAgent.onHookMiBroadcastStateSetSync(3, 1);
        if (byteBufferOnHookMiBroadcastStateSetSync == null) {
            log("bufRet is null");
            return;
        }
        int i2 = byteBufferOnHookMiBroadcastStateSetSync.getInt();
        log("ret: " + i2);
        if (i2 != 0) {
            log("startModemStatsBC fail");
        }
    }

    public static void stopModemStatsBC() {
        log("stopModemStatsBC");
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        ByteBuffer byteBufferOnHookMiBroadcastStateSetSync = oemHookAgent.onHookMiBroadcastStateSetSync(3, 0);
        if (byteBufferOnHookMiBroadcastStateSetSync == null) {
            log("bufRet is null");
            return;
        }
        int i2 = byteBufferOnHookMiBroadcastStateSetSync.getInt();
        log("ret: " + i2);
        if (i2 != 0) {
            log("stopModemStatsBC fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void tmpLog(String str) {
        if (mGetDataLogPrintFlag) {
            log(str);
        }
    }

    public static void updateTimeInterval(int i2) {
        log("updateTimeInterval, modemStatsIntervalSec = " + i2 + ", mModemStatsIntervalSec = " + mModemStatsIntervalSec);
        if (mInstance == null) {
            log("not init, do nothing");
        } else {
            if (i2 == mModemStatsIntervalSec) {
                log("mModemStatsIntervalSec is no change, do nothing");
                return;
            }
            mModemStatsIntervalSec = i2;
            deregisterGroup();
            registerGroup(mArrCurGroupName, mSub);
        }
    }

    protected void addItem(int i2, String str) {
        addItem(i2, str, false);
    }

    public void clearGroupData(String str, int i2) {
        if (str == null) {
            log("clearGroupData, groupName is null, sub = " + i2);
            return;
        }
        tmpLog("clearGroupData, groupName = " + str + ", sub = " + i2);
        Iterator it = mMapForId2Group.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            if (isInGroup(iIntValue, str)) {
                clearItemData(iIntValue, i2);
            }
        }
    }

    public void clearItemData(int i2, int i3) {
        ModemStatsItem item = getItem(i2);
        if (item == null) {
            log("clearItemData, item is null, sub = " + i3);
            return;
        }
        tmpLog("clearItemData, mId = " + item.mId + ", sub = " + i3);
        if (i3 == 0) {
            item.mDataSub0 = null;
        } else if (1 == i3) {
            item.mDataSub1 = null;
        } else {
            item.mDataSub0 = null;
            item.mDataSub1 = null;
        }
    }

    public void freeResource() {
        log("freeResource");
        unRegisterOemHookCallbackForMiBroadcastData();
        deregisterBcReceiver();
        Registrant registrant = this.mHydraDataIndRegistrant;
        if (registrant != null) {
            registrant.clear();
            this.mHydraDataIndRegistrant = null;
        }
    }

    public ByteBuffer getData(int i2, int i3) {
        ModemStatsItem item = getItem(i2);
        if (item == null) {
            log("getData, item is null, id = " + i2 + ", sub = " + i3);
            return null;
        }
        if (i3 == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("getData, id = ");
            sb.append(i2);
            sb.append(", item.mDataSub0.length = ");
            byte[] bArr = item.mDataSub0;
            sb.append(bArr != null ? bArr.length : 0);
            parseDataLog(sb.toString());
            return ModemUtils.getBuffer(item.mDataSub0);
        }
        if (1 != i3) {
            log("getData, sub is invalid, id = " + i2 + ", sub = " + i3);
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("getData, id = ");
        sb2.append(i2);
        sb2.append(", item.mDataSub1.length = ");
        byte[] bArr2 = item.mDataSub1;
        sb2.append(bArr2 != null ? bArr2.length : 0);
        parseDataLog(sb2.toString());
        return ModemUtils.getBuffer(item.mDataSub1);
    }

    public Map getDataBackup(int i2, int i3) {
        ModemStatsItem item = getItem(i2);
        if (item != null) {
            return i3 == 0 ? item.mMapParamValSub0 : item.mMapParamValSub1;
        }
        log("getDataBackup, item is null, id = " + i2 + ", sub = " + i3);
        return null;
    }

    public ModemStatsItem getItem(int i2) {
        HashMap map = mMapForId2Group;
        if (map != null) {
            return (ModemStatsItem) map.get(Integer.valueOf(i2));
        }
        log("getItem, mMapForId2Group is null, id = " + i2);
        return null;
    }

    public boolean isInGroup(int i2, String str) {
        if (str == null) {
            log("isInGroup, groupName is null, id = " + i2);
            return false;
        }
        ModemStatsItem modemStatsItem = (ModemStatsItem) mMapForId2Group.get(Integer.valueOf(i2));
        if (modemStatsItem == null) {
            log("item is null, continue, id: " + i2);
            return false;
        }
        if (!modemStatsItem.isInGroup(str)) {
            return false;
        }
        tmpLog("isInGroup, groupName = " + str + ", id = " + i2);
        return true;
    }

    public void onHandleMessageDo(Message message) {
        indDataLog("onHandleMessageDo msg id: " + message.what);
        new Bundle();
        message.getData();
        AsyncResult asyncResult = (AsyncResult) message.obj;
        if (message.what != 100) {
            log("invalid msg id: " + message.what);
            return;
        }
        indDataLog("EVENT_DATA_UPDATE_HYDRA");
        if (saveIndData(ModemUtils.getBuffer((byte[]) asyncResult.result)) > 0) {
            mView.onSendMsgForUpdataDataView();
        }
    }

    public void registerOemHookCallbackForMiBroadcastData() {
        log("registerOemHookCallbackForMiBroadcastData");
        if (mMtbHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        byte[] bArr = new byte[4];
        ModemUtils.getBuffer(bArr).putInt(mStatsClientId);
        mMtbHookAgent.registerOemHookCallbackForMiBroadcastDataRegistrant(ModemUtils.getCurrentClassName(), this.mHydraDataIndRegistrant, QUALCOMM_HYDRA_DATA_IND_BC_NAME, bArr, mNumPhones);
    }

    public void unRegisterOemHookCallbackForMiBroadcastData() {
        log("unRegisterOemHookCallbackForMiBroadcastData");
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
        } else {
            oemHookAgent.unRegisterOemHookCallbackForMiBroadcastDataRegistrant(ModemUtils.getCurrentClassName(), this.mHydraDataIndRegistrant);
        }
    }

    public static String getNetworkTypeString(int i2) {
        return getNetworkTypeString(i2, true);
    }

    protected void addItem(int i2, String str, boolean z) {
        HashMap map = mMapForId2Group;
        if (map == null) {
            log("addItem, mMapForId2Group is null");
            return;
        }
        if (str == null) {
            log("addItem, groupName is null");
            return;
        }
        ModemStatsItem modemStatsItem = (ModemStatsItem) map.get(Integer.valueOf(i2));
        if (modemStatsItem == null) {
        } else {
            modemStatsItem.addGroup(str);
        }
    }

    public static String getNetworkTypeString(int i2, boolean z) {
        String str;
        if (-1 == i2) {
            str = "-";
        } else if (-1 == i2) {
            str = "NONE";
        } else if (i2 == 0) {
            str = "NO_SRV";
        } else if (1 == i2) {
            str = "AMPS";
        } else if (2 == i2) {
            str = "CDMA";
        } else if (3 == i2) {
            str = "GSM";
        } else if (4 == i2) {
            str = "HDR";
        } else if (5 == i2) {
            str = "WCDMA";
        } else if (6 == i2) {
            str = "GPS";
        } else if (7 == i2) {
            str = "GW";
        } else if (8 == i2) {
            str = "WLAN";
        } else if (9 == i2) {
            str = "LTE";
        } else if (10 == i2) {
            str = "GWL";
        } else if (11 == i2) {
            str = "TDS";
        } else if (12 == i2) {
            str = "NR5G";
        } else {
            log("getNetworkTypeString, rat: " + i2);
            str = "UNKnown";
        }
        if (-1 == i2 || true != z) {
            return str;
        }
        return "[" + i2 + "]" + str;
    }

    private String getString(Map map, String str, double d2, boolean z) {
        return getString(map, str, ModemUtils.getString(d2), z);
    }

    private String getString(Map map, String str, float f2, boolean z) {
        return getString(map, str, ModemUtils.getString(f2), z);
    }

    private String getString(Map map, String str, int i2, boolean z) {
        return getString(map, str, ModemUtils.getString(i2, true, false), z);
    }

    private String getString(Map map, String str, short s, boolean z) {
        return getString(map, str, ModemUtils.getString(s, true, false), z);
    }

    private String getString(Map map, String str, byte b2, boolean z) {
        return getString(map, str, ModemUtils.getString(b2, true, false), z);
    }

    public class ModemStatsItem {
        public byte[] mDataSub0;
        public byte[] mDataSub1;
        public int mId;
        public boolean mIndType;
        public ArrayList mListGroupName;
        public Map mMapParamValSub0;
        public Map mMapParamValSub1;

        public ModemStatsItem(int i2, ArrayList arrayList, boolean z) {
            this.mId = 101;
            this.mIndType = false;
            this.mDataSub0 = null;
            this.mDataSub1 = null;
            this.mListGroupName = new ArrayList();
            this.mMapParamValSub0 = new HashMap();
            this.mMapParamValSub1 = new HashMap();
            this.mId = i2;
            this.mIndType = z;
            ModemUtils.addStringList(this.mListGroupName, arrayList);
            MtbHydraDataManager.log("ModemStatsItem, id = " + i2 + ", indType = " + z + ", listGroupName = " + arrayList);
        }

        public void addGroup(String str) {
            MtbHydraDataManager.log("ModemStatsItem, addGroup, mId = " + this.mId + ", mIndType = " + this.mIndType + ", groupName = " + str + ", mListGroupName = " + this.mListGroupName);
            ModemUtils.addStringList(this.mListGroupName, str);
        }

        public boolean isInGroup(String str) {
            return ModemUtils.isInStringList(this.mListGroupName, str);
        }

        public ModemStatsItem(int i2, ArrayList arrayList) {
            this.mId = 101;
            this.mIndType = false;
            this.mDataSub0 = null;
            this.mDataSub1 = null;
            this.mListGroupName = new ArrayList();
            this.mMapParamValSub0 = new HashMap();
            this.mMapParamValSub1 = new HashMap();
            this.mId = i2;
            ModemUtils.addStringList(this.mListGroupName, arrayList);
            MtbHydraDataManager.log("ModemStatsItem, id = " + i2 + ", mIndType = " + this.mIndType + ", listGroupName = " + arrayList);
        }

        public ModemStatsItem(int i2, String str, boolean z) {
            this.mId = 101;
            this.mIndType = false;
            this.mDataSub0 = null;
            this.mDataSub1 = null;
            this.mListGroupName = new ArrayList();
            this.mMapParamValSub0 = new HashMap();
            this.mMapParamValSub1 = new HashMap();
            this.mId = i2;
            this.mIndType = z;
            ModemUtils.addStringList(this.mListGroupName, str);
            MtbHydraDataManager.log("ModemStatsItem, id = " + i2 + ", indType = " + z + ", groupName = " + str);
        }

        public ModemStatsItem(int i2, String str) {
            this.mId = 101;
            this.mIndType = false;
            this.mDataSub0 = null;
            this.mDataSub1 = null;
            this.mListGroupName = new ArrayList();
            this.mMapParamValSub0 = new HashMap();
            this.mMapParamValSub1 = new HashMap();
            this.mId = i2;
            ModemUtils.addStringList(this.mListGroupName, str);
            MtbHydraDataManager.log("ModemStatsItem, id = " + i2 + ", mIndType = " + this.mIndType + ", groupName = " + str);
        }
    }
}
