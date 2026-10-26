package com.xiaomi.mtb.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Registrant;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.xiaomi.modem.ModemUtils;
import com.xiaomi.modem.OemHookAgent;
import com.xiaomi.mtb.ModemTestBoxItem;
import com.xiaomi.mtb.MtbApp;
import com.xiaomi.mtb.MtbCallbackBase;
import com.xiaomi.mtb.MtbCallbackBundle;
import com.xiaomi.mtb.MtbFloatingDisplayView;
import com.xiaomi.mtb.MtbOpenFileDialog;
import com.xiaomi.mtb.MtbUtils;
import com.xiaomi.mtb.R;
import com.xiaomi.mtb.XiaoMiServerPermissionCheck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MtbBaseActivity extends Activity {
    public static final String BUNDLE_DIALOG_EDIT_STRING = "BUNDLE_DIALOG_EDIT_STRING";
    public static final String BUNDLE_UPDATE_OPT_STATUS_VIEW_DESC = "BUNDLE_UPDATE_OPT_STATUS_VIEW_DESC";
    public static final String BUNDLE_UPDATE_OPT_STATUS_VIEW_ERRO = "BUNDLE_UPDATE_OPT_STATUS_VIEW_ERRO";
    protected static final int CELL_INFO_LIST_RATE_DISABLED = Integer.MAX_VALUE;
    protected static final int CELL_INFO_LIST_RATE_ENABLED = 0;
    protected static final int CHECK_DIALOG_TYPE_EDIT_SEND_MSG = 5;
    protected static final int CHECK_DIALOG_TYPE_INIT = 0;
    protected static final int CHECK_DIALOG_TYPE_MODME_SSR = 4;
    protected static final int CHECK_DIALOG_TYPE_PHONE_RESET = 3;
    protected static final int CHECK_DIALOG_TYPE_QUIT = 1;
    protected static final int CHECK_DIALOG_TYPE_SEND_MSG = 2;
    private static final int EVENT_QCRIL_HOOK_READY = -1;
    private static final int EVENT_UPDATE_OPT_STATUS_VIEW = -2;
    private static final int EVENT_VIEW_FREEZE_FOR_RILD_DONE = -4;
    private static final int EVENT_VIEW_FREEZE_FOR_SSR_DONE = -5;
    private static final int EVENT_VIEW_INIT_DONE = -3;
    private static final String LOG_TAG = "MtbBaseActivity";
    private static final int POLL_UPDATE_COUNT_MAX = -1;
    private static final int POLL_UPDATE_EVENT_IDLE = -255;
    private static final int POLL_UPDATE_INTERVAL_MAX_MS = 60000;
    private static final int POLL_UPDATE_INTERVAL_MIN_MS = 1000;
    protected static final int SUB_0 = 0;
    protected static final int SUB_1 = 1;
    protected static final int SUB_ALL = 2;
    protected static final int SUB_MASK_0 = 1;
    protected static final int SUB_MASK_1 = 2;
    protected static final int SUB_MASK_ALL = 3;
    private static final int VIEW_FREEZE_DONE_FOR_RILD_DELAY_MS = 2000;
    private static final int VIEW_FREEZE_DONE_FOR_SSR_DELAY_MS = 6000;
    private static final int VIEW_INIT_DONE = 1;
    private static final int VIEW_INIT_DONE_DELAY_MS = 300;
    private static final int VIEW_INIT_IDLE = 0;
    protected static long mClass = -2;
    protected static Context mContext = null;
    private static boolean mItemFloatingViewFlag = false;
    protected static MtbUtils mMtbUtils = null;
    public static int mNumPhones = 0;
    protected static Phone[] mPhones = null;
    private static boolean mTmpLogPrintFlag = false;
    private static boolean mTmpLogPrintFlag1 = false;
    protected static List mObjMtbToolsList = new ArrayList();
    public static OemHookAgent mMtbHookAgent = null;
    private int mViewInitState = 0;
    protected TelephonyManager mTelephonyManager = null;
    protected PhoneStateListener[] mPhoneStateListener = null;
    protected SubscriptionManager mSubscriptionManager = null;
    protected MtbTelephonyCallback[] mTelephonyCallback = null;
    protected SubscriptionInfo[] mSubscriptionInfo = null;
    protected TelephonyManager[] mTelephonyManagerMult = null;
    private MtbCallbackBase mMtbCallback = null;
    private final String COLOR_BG_OPT = "#4EEE94";
    private final String COLOR_BG_CFG = "#7FFFAA";
    private final String COLOR_BG_DATA = "#A4D3EE";
    private final String COLOR_BG_DEFAULT_VALUE = "#0BA683";
    private final String COLOR_BG_TEST_VALUE = "#FF0000";
    private final String COLOR_TXT_DATA_VALUE = "#9932CC";
    private final String COLOR_TXT_INFO_NAME = "#ff0000";
    private final String COLOR_TXT_SUB_NAME = "#C71585";
    protected LinearLayout mLayoutMain = null;
    protected LinearLayout mLayoutOptStatus = null;
    protected TextView mTxtOptStatus = null;
    private List mListMultiCheckBoxNameAll = null;
    private List mListMultiCheckBoxNameSel = null;
    private CheckBox mMultiCheckBox = null;
    private LinearLayout mMultiCheckBoxLayoutParent = null;
    private LinearLayout mMultiCheckBoxLayoutSub = null;
    private List mListMultiCheckBox = new ArrayList();
    private List mListMultiCheckBoxText = new ArrayList();
    private List mListMultiCheckBoxLayout = new ArrayList();
    private List mListMultiCheckBoxCheckState = new ArrayList();
    private SubscriptionManager.OnSubscriptionsChangedListener mOnMtbSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.1
        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            for (int i2 = 0; i2 < MtbBaseActivity.mNumPhones; i2++) {
                MtbBaseActivity.this.mSubscriptionInfo[i2] = SubscriptionManager.from(MtbBaseActivity.mContext).getActiveSubscriptionInfoForSimSlotIndex(i2);
                MtbBaseActivity.this.log("mOnMtbSubscriptionsChangedListener, mSubscriptionInfo[" + i2 + "]: " + MtbBaseActivity.this.mSubscriptionInfo[i2]);
                MtbBaseActivity mtbBaseActivity = MtbBaseActivity.this;
                SubscriptionInfo subscriptionInfo = mtbBaseActivity.mSubscriptionInfo[i2];
                if (subscriptionInfo == null) {
                    mtbBaseActivity.log("mSubscriptionInfo[" + i2 + "] get failed");
                    MtbBaseActivity mtbBaseActivity2 = MtbBaseActivity.this;
                    mtbBaseActivity2.unregisterMtbTelephonyCallback(mtbBaseActivity2.mTelephonyManagerMult[i2], mtbBaseActivity2.mTelephonyCallback[i2]);
                    MtbBaseActivity.this.mTelephonyManagerMult[i2] = null;
                } else {
                    mtbBaseActivity.mTelephonyManagerMult[i2] = mtbBaseActivity.mTelephonyManager.createForSubscriptionId(subscriptionInfo.getSubscriptionId());
                    MtbBaseActivity mtbBaseActivity3 = MtbBaseActivity.this;
                    if (mtbBaseActivity3.mTelephonyManagerMult[i2] == null) {
                        mtbBaseActivity3.log("mTelephonyManagerMult[" + i2 + "] get failed");
                    }
                }
            }
            MtbBaseActivity.this.onMtbSubscriptionsChanged();
        }
    };
    protected Handler mHandler = new Handler() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MtbBaseActivity.this.tmpLog1("handleMessage msg id: " + message.what);
            new Bundle();
            Bundle data = message.getData();
            int i2 = message.what;
            if (i2 == MtbBaseActivity.EVENT_VIEW_FREEZE_FOR_SSR_DONE) {
                MtbBaseActivity.this.log("EVENT_VIEW_FREEZE_FOR_SSR_DONE, mMtbCallback = " + MtbBaseActivity.this.mMtbCallback);
                MtbBaseActivity.this.enableView();
                if (MtbBaseActivity.this.mMtbCallback != null) {
                    MtbBaseActivity.this.mMtbCallback.onModemSsrEvent();
                }
                MtbBaseActivity mtbBaseActivity = MtbBaseActivity.this;
                mtbBaseActivity.onUpdateOptStatusView(false, mtbBaseActivity.getString(R.string.mtb_tool_setting_done));
                return;
            }
            if (i2 == MtbBaseActivity.EVENT_VIEW_FREEZE_FOR_RILD_DONE) {
                MtbBaseActivity.this.log("EVENT_VIEW_FREEZE_FOR_RILD_DONE, mMtbCallback = " + MtbBaseActivity.this.mMtbCallback);
                MtbBaseActivity.this.enableView();
                if (MtbBaseActivity.this.mMtbCallback != null) {
                    MtbBaseActivity.this.mMtbCallback.onRildRestEvent();
                }
                MtbBaseActivity mtbBaseActivity2 = MtbBaseActivity.this;
                mtbBaseActivity2.onUpdateOptStatusView(false, mtbBaseActivity2.getString(R.string.mtb_tool_setting_done));
                return;
            }
            if (i2 == MtbBaseActivity.EVENT_VIEW_INIT_DONE) {
                MtbBaseActivity.this.log("EVENT_VIEW_INIT_DONE");
                MtbBaseActivity.this.setViewInitDone();
                MtbBaseActivity mtbBaseActivity3 = MtbBaseActivity.this;
                mtbBaseActivity3.onUpdateOptStatusView(false, mtbBaseActivity3.getString(R.string.mtb_tool_opt_standby));
                return;
            }
            if (i2 == -2) {
                MtbBaseActivity.this.log("EVENT_UPDATE_OPT_STATUS_VIEW");
                MtbBaseActivity.this.onUpdateOptStatusView(data);
            } else {
                if (i2 != -1) {
                    MtbBaseActivity.this.onHandleMessage(message);
                    return;
                }
                MtbBaseActivity.this.log("EVENT_QCRIL_HOOK_READY");
                MtbApp.unregisterQcRilHookReady(MtbBaseActivity.this.mHandler);
                MtbBaseActivity.this.onInitByHook();
                MtbBaseActivity.this.onHookReady();
            }
        }
    };
    private BroadcastReceiver mMtbBaseBroadcastReceiver = new BroadcastReceiver() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.12
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            MtbBaseActivity.logc("Received: " + action);
            if (action == null) {
                MtbBaseActivity.logc("action is null");
                return;
            }
            if (XiaoMiServerPermissionCheck.XIAOMI_ACTION_BC_MI_SERVER_URL_CLEAR.equals(action)) {
                MtbBaseActivity.logc("XIAOMI_ACTION_BC_MI_SERVER_URL_CLEAR");
            } else if (!MtbFloatingDisplayView.XIAOMI_ACTION_DATA_FLOATING_DISPLAY_UPDATE.equals(action)) {
                MtbBaseActivity.logc("do nothing");
            } else {
                MtbBaseActivity.logc("XIAOMI_ACTION_DATA_FLOATING_DISPLAY_UPDATE in DataDisplay");
                boolean unused = MtbBaseActivity.mItemFloatingViewFlag = MtbFloatingDisplayView.onUpdateFloatingViewFlag(intent, MtbBaseActivity.mItemFloatingViewFlag);
            }
        }
    };

    public static int getNumberFromEditText(EditText editText) {
        int i2 = -123456;
        if (editText == null) {
            logc("getNumberFromEditText, edit is null");
            return -123456;
        }
        String string = editText.getText().toString();
        logc("strNum = " + string);
        if (!ModemUtils.isNumber(string)) {
            logc("getNumberFromEditText, this is not a number");
            return -123456;
        }
        try {
            i2 = Integer.parseInt(string);
            logc("getNumberFromEditText, num = " + i2);
            return i2;
        } catch (Exception e2) {
            logc("getNumberFromEditText, Exception: " + e2);
            e2.printStackTrace();
            return i2;
        }
    }

    public static boolean isFloatingViewRunning() {
        logc("mItemFloatingViewFlag = " + mItemFloatingViewFlag);
        return mItemFloatingViewFlag;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d(getClassName(), "MTB_ " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logc(String str) {
        Log.d(LOG_TAG, "MTB_ " + str);
    }

    public static void onAddList(int i2, Intent intent) {
        mObjMtbToolsList.add(new ModemTestBoxItem(i2, intent));
    }

    private boolean onAddMultiCheckBoxView(LinearLayout linearLayout) {
        log("onAddMultiCheckBoxView");
        int size = this.mListMultiCheckBoxLayout.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (linearLayout == this.mListMultiCheckBoxLayout.get(i2)) {
                this.mMultiCheckBoxLayoutParent.addView(linearLayout);
                int iIntValue = ((Integer) this.mListMultiCheckBoxCheckState.get(i2)).intValue();
                boolean z = iIntValue != 0;
                ((CheckBox) this.mListMultiCheckBox.get(i2)).setChecked(z);
                log("onAddMultiCheckBoxView, state = " + z + ", stateVal = " + iIntValue + ", i = " + i2);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onCheckStateMultiCheckBox(CheckBox checkBox, boolean z) {
        log("onCheckStateMultiCheckBox, state = " + z);
        int size = this.mListMultiCheckBox.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (checkBox == this.mListMultiCheckBox.get(i2)) {
                log("onCheckStateMultiCheckBox, stateVal = " + (z ? 1 : 0) + ", i = " + i2 + ", mListMultiCheckBoxNameAll.get(i) = " + ((String) this.mListMultiCheckBoxNameAll.get(i2)));
                this.mListMultiCheckBoxCheckState.set(i2, Integer.valueOf(z ? 1 : 0));
                onUpdateMultiCheckBoxNameListSel(i2, z);
                return true;
            }
        }
        return false;
    }

    private boolean onClearMultiCheckBoxView() {
        log("onClearMultiCheckBoxView");
        if (this.mMultiCheckBoxLayoutParent == null) {
            log("mMultiCheckBoxLayoutParent is null");
            return false;
        }
        int size = this.mListMultiCheckBox.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mMultiCheckBoxLayoutParent.removeView((View) this.mListMultiCheckBoxLayout.get(i2));
        }
        this.mListMultiCheckBox.clear();
        this.mListMultiCheckBoxText.clear();
        this.mListMultiCheckBoxLayout.clear();
        this.mListMultiCheckBoxCheckState.clear();
        return true;
    }

    private boolean onDrawMultiCheckBoxView() {
        log("onDrawMultiCheckBoxView");
        onClearMultiCheckBoxView();
        for (int i2 = 0; i2 < this.mListMultiCheckBoxNameAll.size(); i2++) {
            String str = (String) this.mListMultiCheckBoxNameAll.get(i2);
            log("mListMultiCheckBoxNameAll[" + i2 + "] = " + str);
            String strTrim = str.trim();
            if (strTrim == null || (strTrim != null && strTrim.isEmpty())) {
                log("invalid mMbnConfigInfoList[" + i2 + "] = " + strTrim);
                return false;
            }
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setBackgroundColor(Color.parseColor("#4EEE94"));
            linearLayout.setOrientation(0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.height = OemHookAgent.EVENT_OEMHOOK_MODEM_NW_SEARCH_TYPE_SET;
            linearLayout.setLayoutParams(layoutParams);
            this.mMultiCheckBox = new CheckBox(mContext);
            this.mListMultiCheckBoxCheckState.add(0);
            this.mListMultiCheckBox.add(this.mMultiCheckBox);
            this.mMultiCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.9
                CheckBox checkView;

                {
                    this.checkView = MtbBaseActivity.this.mMultiCheckBox;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    MtbBaseActivity.this.log("isChecked, checkView = " + this.checkView);
                    MtbBaseActivity.this.onCheckStateMultiCheckBox(this.checkView, z);
                }
            });
            linearLayout.addView(this.mMultiCheckBox);
            TextView textView = new TextView(mContext);
            textView.setText((CharSequence) this.mListMultiCheckBoxNameAll.get(i2));
            this.mListMultiCheckBoxText.add(textView);
            linearLayout.addView(textView);
            this.mListMultiCheckBoxLayout.add(linearLayout);
            onAddMultiCheckBoxView(linearLayout);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInitByHook() {
        logc("onInitByHook");
        OemHookAgent hook = OemHookAgent.getHook();
        mMtbHookAgent = hook;
        if (hook == null) {
            onUpdateOptStatusView(true, getString(R.string.mtb_tool_hook_initing_fail_notify));
            return;
        }
        onUpdateOptStatusView(true, getString(R.string.mtb_tool_hook_initing_ok_notify));
        if (MtbApp.mHalChipId == 0) {
            MtbApp.mHalChipId = ModemUtils.getModemHalChipId(mMtbHookAgent);
        }
        if (MtbApp.mChipId == 0) {
            MtbApp.mChipId = ModemUtils.getModemChipId(mMtbHookAgent);
        }
        if ("NULL".equals(MtbApp.mBuildProfile)) {
            MtbApp.mBuildProfile = ModemUtils.getBuildProfile(mMtbHookAgent);
        }
        log("onInitByHook end, mHalChipId = " + MtbApp.mHalChipId + ", mChipId = " + MtbApp.mChipId + ", mBuildProfile = " + MtbApp.mBuildProfile);
    }

    private void onInitStateMultiCheckBox() {
        int size = this.mListMultiCheckBox.size();
        int size2 = this.mListMultiCheckBoxNameAll.size();
        int size3 = this.mListMultiCheckBoxNameSel.size();
        log("onInitStateMultiCheckBox, sizeChk = " + size + ", sizeAll = " + size2 + ", sizeSel = " + size3);
        if (size != size2) {
            log("error, sizeChk != sizeAll");
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            this.mListMultiCheckBoxCheckState.set(i2, 0);
            for (int i3 = 0; i3 < size3; i3++) {
                if (((String) this.mListMultiCheckBoxNameAll.get(i2)).equals(this.mListMultiCheckBoxNameSel.get(i3))) {
                    log("strMultiCheckBoxName = " + ((String) this.mListMultiCheckBoxNameAll.get(i2)) + ", i = " + i2 + ", j = " + i3);
                    this.mListMultiCheckBoxCheckState.set(i2, 1);
                    ((CheckBox) this.mListMultiCheckBox.get(i2)).setChecked(true);
                }
            }
        }
    }

    private boolean onRemoveAllMultiCheckBoxView() {
        log("onRemoveAllMultiCheckBoxView");
        int size = this.mListMultiCheckBoxLayout.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mMultiCheckBoxLayoutParent.removeView((View) this.mListMultiCheckBoxLayout.get(i2));
        }
        return true;
    }

    public static boolean onRunFloatingView() {
        logc("mContext = " + mContext);
        Context context = mContext;
        if (context == null) {
            return false;
        }
        mItemFloatingViewFlag = true;
        MtbFloatingDisplayView.onOpenFloatingDisplayView(context);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSearchUpdateMultiCheckBoxView(EditText editText) {
        log("onSearchUpdateMultiCheckBoxView");
        if (editText == null) {
            log("editValue is null");
            return;
        }
        if (this.mListMultiCheckBoxNameAll == null) {
            log("mListMultiCheckBoxNameAll is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        String string = editText.getText().toString();
        int size = this.mListMultiCheckBoxText.size();
        log("searchString = " + string + ", size = " + size);
        String lowerCase = string.trim().toLowerCase();
        for (int i2 = 0; i2 < size; i2++) {
            String lowerCase2 = ((String) this.mListMultiCheckBoxNameAll.get(i2)).toLowerCase();
            if ("".equals(lowerCase) || lowerCase2.contains(lowerCase)) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        onShowMultiCheckBoxView(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onSelectAllMultiCheckBox() {
        log("onSelectAllMultiCheckBox");
        int size = this.mListMultiCheckBox.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((CheckBox) this.mListMultiCheckBox.get(i2)).setChecked(true);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onSelectNoneMultiCheckBox() {
        log("onSelectNoneMultiCheckBox");
        int size = this.mListMultiCheckBox.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((CheckBox) this.mListMultiCheckBox.get(i2)).setChecked(false);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onSelectOppositeMultiCheckBox() {
        log("onSelectOppositeMultiCheckBox");
        int size = this.mListMultiCheckBox.size();
        for (int i2 = 0; i2 < size; i2++) {
            CheckBox checkBox = (CheckBox) this.mListMultiCheckBox.get(i2);
            if (checkBox.isChecked()) {
                checkBox.setChecked(false);
            } else {
                checkBox.setChecked(true);
            }
        }
        return true;
    }

    private boolean onShowMultiCheckBoxView(List list) {
        log("onShowMultiCheckBoxView");
        if (list == null) {
            log("listViewId is null");
            onUpdateOptStatusView(true, "Emtpy view");
            ModemUtils.showToast(mContext, "Emtpy view");
            return false;
        }
        int size = this.mListMultiCheckBoxLayout.size();
        int size2 = list.size();
        log("sizeAll: " + size + ", size: " + size2);
        if (size2 > size) {
            log("size can not > sizeAll");
            onUpdateOptStatusView(true, "Invalid view");
            ModemUtils.showToast(mContext, "Invalid view");
            return false;
        }
        onRemoveAllMultiCheckBoxView();
        for (int i2 = 0; i2 < size2; i2++) {
            int iIntValue = ((Integer) list.get(i2)).intValue();
            if (iIntValue >= size) {
                log("j can not > sizeAll");
                onUpdateOptStatusView(true, "Invalid view");
                ModemUtils.showToast(mContext, "Invalid view");
                return false;
            }
            onAddMultiCheckBoxView((LinearLayout) this.mListMultiCheckBoxLayout.get(iIntValue));
        }
        return true;
    }

    private void onStartTimer(int i2) {
        log("onStartTimer, timer = " + i2 + "ms");
    }

    public static boolean onStopFloatingView() {
        logc("mContext = " + mContext);
        Context context = mContext;
        if (context == null) {
            return false;
        }
        mItemFloatingViewFlag = false;
        MtbFloatingDisplayView.onCloseFloatingDisplayView(context);
        return true;
    }

    private void onStopTimer() {
        log("onStopTimer");
    }

    private void onUpdateMultiCheckBoxNameListSel(int i2, boolean z) {
        log("onUpdateMultiCheckBoxNameListSel, i = " + i2 + ", state = " + z);
        int size = this.mListMultiCheckBoxNameSel.size();
        boolean z2 = false;
        int i3 = 0;
        while (i3 < size) {
            if (((String) this.mListMultiCheckBoxNameAll.get(i2)).equals(this.mListMultiCheckBoxNameSel.get(i3))) {
                log("strMultiCheckBoxName = " + ((String) this.mListMultiCheckBoxNameAll.get(i2)) + ", i = " + i2 + ", j = " + i3);
                z2 = true;
                break;
            }
            i3++;
        }
        if (true == z && !z2) {
            this.mListMultiCheckBoxNameSel.add((String) this.mListMultiCheckBoxNameAll.get(i2));
            log("onUpdateMultiCheckBoxNameListSel, add this item: " + ((String) this.mListMultiCheckBoxNameAll.get(i2)));
            return;
        }
        if (z || true != z2) {
            return;
        }
        this.mListMultiCheckBoxNameSel.remove(i3);
        log("onUpdateMultiCheckBoxNameListSel, remove this item: " + ((String) this.mListMultiCheckBoxNameAll.get(i2)));
    }

    private boolean registerMtbBaseBroadcastReceiver() {
        log("registerMtbBaseBroadcastReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MtbFloatingDisplayView.XIAOMI_ACTION_DATA_FLOATING_DISPLAY_UPDATE);
        registerReceiver(this.mMtbBaseBroadcastReceiver, intentFilter);
        return true;
    }

    private void showDialog(String str, String str2, final String str3, final int i2, final Handler handler, final Message message, final Message message2, final int i3, final int i4, final Intent intent, boolean z, boolean z2) {
        log("showDialog: strTitle: " + str);
        log("showDialog: strMsg: " + str2);
        log("showDialog: msgTip: " + str3);
        log("showDialog: checkFlag: " + i2);
        log("showDialog: hdl: " + handler);
        log("showDialog: msgYes: " + message);
        log("showDialog: msgNo: " + message2);
        log("showDialog: intVal1: " + i3);
        log("showDialog: intVal2: " + i4);
        log("showDialog: intent: " + intent);
        log("showDialog: showPositiveButton: " + z);
        log("showDialog: showNegativeButton: " + z2);
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle(str);
        builder.setMessage(str2);
        if (5 == i2) {
            builder.setTitle(str).setView(editText);
            editText.setHint(getString(R.string.mtb_tool_modem_data_dispaly_new_hint));
        }
        if (z) {
            builder.setPositiveButton(getString(R.string.mtb_tool_check_ok), new DialogInterface.OnClickListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.10
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i5) {
                    MtbBaseActivity.this.log("showDialog: setPositiveButton, which: " + i5);
                    String str4 = str3;
                    if (str4 != null) {
                        MtbBaseActivity.this.onUpdateOptStatusView(false, str4);
                    }
                    MtbBaseActivity mtbBaseActivity = MtbBaseActivity.this;
                    Handler handler2 = mtbBaseActivity.mHandler;
                    Handler handler3 = handler;
                    if (handler3 != null) {
                        handler2 = handler3;
                    }
                    int i6 = i2;
                    if (i6 == 0) {
                        Intent intent2 = intent;
                        if (intent2 == null) {
                            mtbBaseActivity.onUpdateOptStatusView(true, "intent is null");
                            return;
                        } else {
                            mtbBaseActivity.startActivity(intent2);
                            return;
                        }
                    }
                    if (1 == i6) {
                        mtbBaseActivity.finish();
                        return;
                    }
                    if (2 == i6) {
                        Message message3 = message;
                        if (message3 != null) {
                            mtbBaseActivity.onSendMsg(handler2, message3);
                            return;
                        } else {
                            mtbBaseActivity.onSendMsg(handler2, i3);
                            return;
                        }
                    }
                    if (3 == i6) {
                        ModemUtils.rebootSystem(MtbBaseActivity.mContext);
                        return;
                    }
                    if (4 == i6) {
                        MtbUtils.modemSSR(MtbBaseActivity.mMtbHookAgent, i3);
                        return;
                    }
                    if (5 == i6) {
                        String string = editText.getText().toString();
                        MtbBaseActivity.this.log("strDialogEdit: " + string);
                        Message messageObtain = Message.obtain();
                        messageObtain.what = i3;
                        Bundle bundle = new Bundle();
                        bundle.putString(MtbBaseActivity.BUNDLE_DIALOG_EDIT_STRING, string);
                        messageObtain.setData(bundle);
                        MtbBaseActivity.this.onSendMsg(handler2, messageObtain);
                    }
                }
            });
        }
        if (z2) {
            builder.setNegativeButton(getString(R.string.mtb_tool_check_cal), new DialogInterface.OnClickListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.11
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i5) {
                    MtbBaseActivity.this.log("showDialog: setNegativeButton, which: " + i5);
                    MtbBaseActivity mtbBaseActivity = MtbBaseActivity.this;
                    Handler handler2 = mtbBaseActivity.mHandler;
                    Handler handler3 = handler;
                    if (handler3 != null) {
                        handler2 = handler3;
                    }
                    Message message3 = message2;
                    if (message3 != null) {
                        mtbBaseActivity.onSendMsg(handler2, message3);
                    } else {
                        mtbBaseActivity.onSendMsg(handler2, i4);
                    }
                }
            });
        }
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tmpLog(String str) {
        if (mTmpLogPrintFlag) {
            log(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tmpLog1(String str) {
        if (mTmpLogPrintFlag1) {
            log(str);
        }
    }

    private void tmpLogc(String str) {
        if (mTmpLogPrintFlag) {
            logc(str);
        }
    }

    private void tmpLogc1(String str) {
        if (mTmpLogPrintFlag1) {
            logc(str);
        }
    }

    public void deInitMultiCheckBoxView() {
        log("deInitMultiCheckBoxView, mMultiCheckBoxLayoutParent = " + this.mMultiCheckBoxLayoutParent + ", mMultiCheckBoxLayoutSub = " + this.mMultiCheckBoxLayoutSub);
        LinearLayout linearLayout = this.mMultiCheckBoxLayoutParent;
        boolean z = linearLayout != null;
        LinearLayout linearLayout2 = this.mMultiCheckBoxLayoutSub;
        if ((linearLayout2 != null) & z) {
            linearLayout.removeView(linearLayout2);
        }
        onClearMultiCheckBoxView();
        this.mListMultiCheckBoxNameAll = null;
        this.mListMultiCheckBoxNameSel = null;
        this.mMultiCheckBox = null;
        this.mMultiCheckBoxLayoutParent = null;
        this.mMultiCheckBoxLayoutSub = null;
    }

    public void disableView() {
        log("disableView, mLayoutMain = " + this.mLayoutMain);
        MtbUtils.enableDisableView(this.mLayoutMain, false);
    }

    public void enableView() {
        log("enableView, mLayoutMain = " + this.mLayoutMain);
        MtbUtils.enableDisableView(this.mLayoutMain, true);
    }

    public void freezeViewForModemSsr() {
        freezeViewForModemSsr(null, VIEW_FREEZE_DONE_FOR_SSR_DELAY_MS);
    }

    public void freezeViewForRildRest() {
        freezeViewForRildRest(null);
    }

    protected int getActiveDataPhoneId() {
        int activeDataSubscriptionId = SubscriptionManager.getActiveDataSubscriptionId();
        int phoneId = SubscriptionManager.getPhoneId(activeDataSubscriptionId);
        if (phoneId != 1) {
            phoneId = 0;
        }
        log("getActiveDataPhoneId, dataSub = " + activeDataSubscriptionId + ", ddsSlot = " + phoneId);
        return phoneId;
    }

    protected int getActiveDataSubscriptionId() {
        log("getActiveDataSubscriptionId, mSubscriptionManager = " + this.mSubscriptionManager);
        if (this.mSubscriptionManager != null) {
            return SubscriptionManager.getActiveDataSubscriptionId();
        }
        return -1;
    }

    public String getClassName() {
        return LOG_TAG;
    }

    public Context getContext() {
        return mContext;
    }

    protected int getDefaultDataPhoneId() {
        log("getDefaultDataPhoneId, mSubscriptionManager = " + this.mSubscriptionManager);
        if (this.mSubscriptionManager == null) {
            return -1;
        }
        log("Not support getDefaultDataPhoneId");
        return -1;
    }

    protected int getDefaultSmsPhoneId() {
        log("getDefaultSmsPhoneId, mSubscriptionManager = " + this.mSubscriptionManager);
        if (this.mSubscriptionManager == null) {
            return -1;
        }
        log("Not support getDefaultSmsPhoneId");
        return -1;
    }

    public String getDialogEditString(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString(BUNDLE_DIALOG_EDIT_STRING);
        }
        log("getDialogEditString: data is null");
        return null;
    }

    protected int getPhoneId(int i2) {
        log("getPhoneId, mSubscriptionManager = " + this.mSubscriptionManager + ", subId = " + i2);
        if (this.mSubscriptionManager != null) {
            return SubscriptionManager.getPhoneId(i2);
        }
        return -1;
    }

    protected void initHeadView() {
        log("initHeadView");
        this.mLayoutMain = (LinearLayout) findViewById(R.id.layout_main);
        this.mLayoutOptStatus = (LinearLayout) findViewById(R.id.layout_opt_status);
        this.mTxtOptStatus = (TextView) findViewById(R.id.text_opt_status);
    }

    public void initMultiCheckBoxView(LinearLayout linearLayout, List list, List list2) {
        log("initMultiCheckBoxView");
        if (linearLayout == null) {
            log("layoutMain is null");
            onUpdateOptStatusView(true, mContext.getResources().getString(R.string.mtb_string_multi_checkbox_view_draw_fail));
            return;
        }
        if (list == null) {
            log("listNameAll is null");
            onUpdateOptStatusView(true, mContext.getResources().getString(R.string.mtb_string_multi_checkbox_view_draw_fail));
            return;
        }
        if (list2 == null) {
            log("listNameSel is null");
            onUpdateOptStatusView(true, mContext.getResources().getString(R.string.mtb_string_multi_checkbox_view_draw_fail));
            return;
        }
        deInitMultiCheckBoxView();
        this.mMultiCheckBoxLayoutParent = linearLayout;
        this.mListMultiCheckBoxNameAll = list;
        this.mListMultiCheckBoxNameSel = list2;
        LinearLayout linearLayout2 = new LinearLayout(this);
        this.mMultiCheckBoxLayoutSub = linearLayout2;
        linearLayout2.setBackgroundColor(Color.parseColor("#4EEE94"));
        this.mMultiCheckBoxLayoutSub.setOrientation(0);
        Button button = new Button(this);
        button.setText(getString(R.string.mtb_tool_select_all));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MtbBaseActivity.this.log("btn_select_all");
                MtbBaseActivity.this.onSelectAllMultiCheckBox();
            }
        });
        this.mMultiCheckBoxLayoutSub.addView(button);
        Button button2 = new Button(this);
        button2.setText(getString(R.string.mtb_tool_select_none));
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MtbBaseActivity.this.log("btn_select_none");
                MtbBaseActivity.this.onSelectNoneMultiCheckBox();
            }
        });
        this.mMultiCheckBoxLayoutSub.addView(button2);
        Button button3 = new Button(this);
        button3.setText(getString(R.string.mtb_tool_select_opposite));
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MtbBaseActivity.this.log("btn_select_opposite");
                MtbBaseActivity.this.onSelectOppositeMultiCheckBox();
            }
        });
        this.mMultiCheckBoxLayoutSub.addView(button3);
        String str = "" + this.mListMultiCheckBoxCheckState.size() + "/" + this.mListMultiCheckBoxNameAll.size();
        TextView textView = new TextView(mContext);
        textView.setText(str);
        this.mMultiCheckBoxLayoutSub.addView(textView);
        log("strCount: " + str);
        TextView textView2 = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.weight = 1.0f;
        textView2.setLayoutParams(layoutParams);
        this.mMultiCheckBoxLayoutSub.addView(textView2);
        final EditText editText = new EditText(mContext);
        editText.setHint(getString(R.string.mtb_tool_search));
        editText.addTextChangedListener(new TextWatcher() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.7
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MtbBaseActivity.this.log("edt_list_search afterTextChanged");
                MtbBaseActivity.this.onSearchUpdateMultiCheckBoxView(editText);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
        this.mMultiCheckBoxLayoutSub.addView(editText);
        Button button4 = new Button(this);
        button4.setText(getString(R.string.mtb_tool_modem_clear_empty));
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MtbBaseActivity.this.log("btn_search_clear");
                editText.setText("");
            }
        });
        this.mMultiCheckBoxLayoutSub.addView(button4);
        this.mMultiCheckBoxLayoutParent.addView(this.mMultiCheckBoxLayoutSub);
        onDrawMultiCheckBoxView();
        onInitStateMultiCheckBox();
    }

    protected boolean initPhoneInfo() {
        log("initPhoneInfo");
        mNumPhones = TelephonyManager.getDefault().getPhoneCount();
        log("mNumPhones = " + mNumPhones);
        int i2 = mNumPhones;
        if (i2 <= 0 || i2 > 2) {
            onUpdateOptStatusView(true, "Phone count is invalid, count = " + mNumPhones);
            return false;
        }
        Phone[] phones = PhoneFactory.getPhones();
        mPhones = phones;
        if (phones == null) {
            onUpdateOptStatusView(true, "Phone get failed");
            return false;
        }
        int length = phones.length;
        log("numPhones = " + length);
        int i3 = mNumPhones;
        if (i3 != length || length <= 0) {
            onUpdateOptStatusView(true, "Phone count mismatch, cnt_tm = " + mNumPhones + ", cnt_pf = " + length);
            return false;
        }
        this.mPhoneStateListener = new PhoneStateListener[i3];
        this.mSubscriptionInfo = new SubscriptionInfo[i3];
        this.mTelephonyManagerMult = new TelephonyManager[i3];
        this.mTelephonyCallback = new MtbTelephonyCallback[i3];
        for (int i4 = 0; i4 < mNumPhones; i4++) {
            this.mTelephonyCallback[i4] = new MtbTelephonyCallback(i4);
            if (this.mTelephonyCallback[i4] == null) {
                onUpdateOptStatusView(true, "mTelephonyCallback[" + i4 + "] get failed");
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        this.mTelephonyManager = telephonyManager;
        if (telephonyManager == null) {
            onUpdateOptStatusView(true, "mTelephonyManager init failed");
            return false;
        }
        SubscriptionManager subscriptionManager = (SubscriptionManager) getApplication().getSystemService("telephony_subscription_service");
        this.mSubscriptionManager = subscriptionManager;
        if (subscriptionManager == null) {
            onUpdateOptStatusView(true, "mSubscriptionManager init failed");
            return false;
        }
        subscriptionManager.addOnSubscriptionsChangedListener(this.mOnMtbSubscriptionsChangedListener);
        return true;
    }

    protected boolean initPhoneStaticInfo() {
        log("initPhoneStaticInfo");
        mNumPhones = TelephonyManager.getDefault().getPhoneCount();
        log("mNumPhones = " + mNumPhones);
        int i2 = mNumPhones;
        if (i2 <= 0 || i2 > 2) {
            onUpdateOptStatusView(true, "Phone count is invalid, count = " + mNumPhones);
            return false;
        }
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        this.mTelephonyManager = telephonyManager;
        if (telephonyManager == null) {
            onUpdateOptStatusView(true, "TelephonyManager init failed");
            return false;
        }
        SubscriptionManager subscriptionManager = (SubscriptionManager) getApplication().getSystemService("telephony_subscription_service");
        this.mSubscriptionManager = subscriptionManager;
        if (subscriptionManager != null) {
            return true;
        }
        onUpdateOptStatusView(true, "mSubscriptionManager init failed");
        return false;
    }

    public void initSubFeatureStateView() {
        logc("initSubFeatureStateView");
    }

    public boolean isViewInitDone() {
        log("isViewInitDone, mViewInitState = " + this.mViewInitState);
        if (1 == this.mViewInitState) {
            return true;
        }
        log("view not finish");
        return false;
    }

    public PollUpdateThread newAndStartPollUpdate(Context context, Handler handler, int i2, int i3, int i4, int i5) {
        if (context == null) {
            onUpdateOptStatusView(true, "The poll updating thread can not be started, cnt is null");
            return null;
        }
        if (handler == null) {
            onUpdateOptStatusView(true, "The poll updating thread can not be started, hdl is null");
            return null;
        }
        if (i5 <= 0) {
            onUpdateOptStatusView(true, "The poll updating thread can not be started, interval <= 0");
            return null;
        }
        PollUpdateThread pollUpdateThread = new PollUpdateThread(context, handler, i2, i3, i4, i5);
        pollUpdateThread.start();
        log("newAndStartPollUpdate, intervalMs = " + i5 + ", cnt = " + context + ", hdl = " + handler + ", obj = " + pollUpdateThread);
        return pollUpdateThread;
    }

    protected void onClearStatusView() {
        log("onClearStatusView");
        this.mLayoutOptStatus = null;
        this.mTxtOptStatus = null;
    }

    protected void onCommonInit(int i2) {
        logc("onCommonInit, layoutId = " + i2);
        onCommonInit();
        setContentView(i2);
        onUpdateBaseView();
        onHookReg();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        logc("onCreate");
    }

    public Dialog onCreateFileOpenDialog(TextView textView) {
        log("onCreateFileOpenDialog, txt: " + textView);
        return onCreateFileOpenDialog(null, -1, MtbOpenFileDialog.PATH_SUFFIX_DEF, null, textView);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy");
        unregisterReceiver(this.mMtbBaseBroadcastReceiver);
        mItemFloatingViewFlag = false;
        MtbFloatingDisplayView.onCloseFloatingDisplayView(mContext);
        SubscriptionManager subscriptionManager = this.mSubscriptionManager;
        if (subscriptionManager != null) {
            subscriptionManager.removeOnSubscriptionsChangedListener(this.mOnMtbSubscriptionsChangedListener);
        }
    }

    protected void onHandleMessage(Message message) {
        tmpLogc1("onHandleMessage msg id: " + message.what);
    }

    public void onHookReady() {
        logc("onHookReady");
    }

    public void onHookReg() {
        log("onHookReg");
        onUpdateOptStatusView(false, getString(R.string.mtb_tool_hook_initing_notify));
        MtbApp.registerHookReadyEvent(this.mHandler, -1, null);
    }

    protected void onInitFloatingView(LinearLayout linearLayout) {
        log("onInitFloatingView, LayoutFloatingView = " + linearLayout);
        MtbFloatingDisplayView.onInitLayout(linearLayout, mContext);
    }

    public void onMtbDataConnectionStateChanged(int i2, int i3, int i4) {
        logc("mTelephonyCallback onMtbDataConnectionStateChanged, sub: " + i2 + ", state: " + i3 + ", networkType: " + i4);
    }

    public void onMtbDisplayInfoChanged(int i2, TelephonyDisplayInfo telephonyDisplayInfo) {
        logc("mTelephonyCallback onMtbDisplayInfoChanged, sub: " + i2 + ", displayInfo: " + telephonyDisplayInfo);
    }

    public void onMtbServiceStateChanged(int i2, ServiceState serviceState) {
        logc("mTelephonyCallback onMtbServiceStateChanged, sub: " + i2 + ", serviceState: " + serviceState);
    }

    public void onMtbSignalStrengthsChanged(int i2, SignalStrength signalStrength) {
        logc("mTelephonyCallback onMtbSignalStrengthsChanged, sub: " + i2 + ", signalStrength: " + signalStrength);
    }

    public void onMtbSubscriptionsChanged() {
        logc("mOnMtbSubscriptionsChangedListener onMtbSubscriptionsChanged");
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        log("onPause");
    }

    public void onPollUpdateDo(PollUpdateThread pollUpdateThread) {
        logc("onPollUpdateDo, obj = " + pollUpdateThread);
    }

    public void onPollUpdateStart(PollUpdateThread pollUpdateThread) {
        logc("onPollUpdateStart, obj = " + pollUpdateThread);
    }

    public void onPollUpdateStop(PollUpdateThread pollUpdateThread) {
        logc("onPollUpdateStop, obj = " + pollUpdateThread);
    }

    public void onRemoveMsg(int i2) {
        onRemoveMsg(this.mHandler, i2);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        log("onResume");
        onUpdateBaseView();
    }

    public void onSendMsg(int i2) {
        onSendMsg(this.mHandler, i2);
    }

    public void onSendMsgWaitForInitDone() {
        onSendMsg(EVENT_VIEW_INIT_DONE, VIEW_INIT_DONE_DELAY_MS);
    }

    protected void onUpdateBaseView() {
        logc("onUpdateBaseView");
        initHeadView();
        MtbUtils.onInitView(this);
        ModemUtils.onInitHandler(this.mHandler);
        log("onUpdateBaseView, mMtbName = " + MtbApp.mMtbName + ", mHalChipId = " + MtbApp.mHalChipId + ", mChipId = " + MtbApp.mChipId + ", mDeviceName = " + MtbApp.mDeviceName + ", mDeviceHwInfo = " + MtbApp.mDeviceHwInfo + ", mDeviceModName = " + MtbApp.mDeviceModName + ", mDeviceHwLevel = " + MtbApp.mDeviceHwLevel + ", mBuildRegion = " + MtbApp.mBuildRegion + ", mCertType = " + MtbApp.mCertType + ", mFactoryBuild = " + MtbApp.mFactoryBuild + ", mRootType = " + MtbApp.mRootType + ", mBuildVersion = " + MtbApp.mBuildVersion + ", mBuildTags = " + MtbApp.mBuildTags);
    }

    protected void onUpdateFloatingView(String str) {
        log("onUpdateFloatingView, showText = " + str);
        MtbFloatingDisplayView.onUpdateFloatingDisplayView(str, mContext);
    }

    public void onUpdateOptStatusView(boolean z, String str) {
        log("onUpdateOptStatusView, desc = " + str + ", erro = " + z);
        LinearLayout linearLayout = this.mLayoutOptStatus;
        if (linearLayout == null) {
            log("mLayoutOptStatus is null");
            return;
        }
        if (this.mTxtOptStatus == null) {
            log("mTxtOptStatus is null");
            return;
        }
        if (str == null) {
            log("desc is null");
            return;
        }
        if (z) {
            linearLayout.setBackgroundColor(Color.parseColor("#FF0000"));
        } else {
            linearLayout.setBackgroundColor(Color.parseColor("#0BA683"));
        }
        this.mTxtOptStatus.setText(str);
        log(str);
    }

    public void onUpdateOptStatusViewEx(boolean z, String str) {
        log("onUpdateOptStatusViewEx, desc = " + str + ", erro = " + z);
        if (str == null) {
            log("desc is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = -2;
        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_UPDATE_OPT_STATUS_VIEW_ERRO, z);
        bundle.putString(BUNDLE_UPDATE_OPT_STATUS_VIEW_DESC, str);
        messageObtain.setData(bundle);
        onSendMsg(messageObtain);
    }

    protected void registerMtbTelephonyCallback(TelephonyManager telephonyManager, MtbTelephonyCallback mtbTelephonyCallback) {
        Context context;
        if (telephonyManager == null || mtbTelephonyCallback == null || (context = mContext) == null) {
            return;
        }
        telephonyManager.registerTelephonyCallback(context.getMainExecutor(), mtbTelephonyCallback);
    }

    public void registerOemHookCallbackForMiBroadcastDataRegistrant(String str, Registrant registrant, String str2, byte[] bArr, int i2) {
        log("registerOemHookCallbackForMiBroadcastDataRegistrant");
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        if (str == null) {
            log("packageName is null");
            return;
        }
        if (registrant == null) {
            log("registrant is null");
        } else if (str2 == null) {
            log("bcName is null");
        } else {
            oemHookAgent.registerOemHookCallbackForMiBroadcastDataRegistrant(str, registrant, str2, bArr, i2);
        }
    }

    protected void setCellInfoListRateDisabled() {
        log("setCellInfoListRateDisabled, mTelephonyManager = " + this.mTelephonyManager);
        if (this.mTelephonyManager != null) {
            log("Not support setCellInfoListRateDisabled");
        }
    }

    protected void setCellInfoListRateEnabled() {
        log("setCellInfoListRateEnabled, mTelephonyManager = " + this.mTelephonyManager);
        if (this.mTelephonyManager != null) {
            log("Not support setCellInfoListRateEnabled");
        }
    }

    public void setViewInitDone() {
        log("setViewInitDone, old mViewInitState = " + this.mViewInitState);
        this.mViewInitState = 1;
    }

    public void setViewInitIdle() {
        log("setViewInitIdle, old mViewInitState = " + this.mViewInitState);
        this.mViewInitState = 0;
    }

    public void showCheckDialogForInit(String str, String str2, Intent intent) {
        showDialog(str, str2, null, 0, null, null, null, 0, 0, intent, true, true);
    }

    public void showCheckDialogForQuit(String str) {
        showDialog(str, getString(R.string.mtb_tool_check_opt_with_quit), null, 1, null, null, null, 0, 0, null, true, true);
    }

    public void showCheckDialogForReboot(String str) {
        showDialog(str, getString(R.string.mtb_tool_finish_with_reboot), null, 3, null, null, null, 0, 0, null, true, true);
    }

    public void showCheckDialogForSendMsg(String str, String str2, String str3, Message message) {
        showDialog(str, str2, str3, 2, null, message, null, 0, 0, null, true, true);
    }

    public void showCheckDialogForSsr(String str, int i2) {
        showDialog(str, getString(R.string.mtb_tool_modem_ssr_notify), null, 4, null, null, null, i2, 0, null, true, true);
    }

    public void showMsgDialog(String str, String str2) {
        showDialog(str, str2, null, 0, null, null, null, 0, 0, null, false, false);
    }

    public void stopPollUpdate(PollUpdateThread pollUpdateThread) {
        log("stopPollUpdate, obj = " + pollUpdateThread);
        if (pollUpdateThread == null) {
            onUpdateOptStatusView(true, "The poll updating thread is stopped");
            return;
        }
        try {
            pollUpdateThread.dExit = true;
            pollUpdateThread.join();
        } catch (Exception e2) {
            log("onStopHotSwap, Exception: " + e2);
            e2.printStackTrace();
            onUpdateOptStatusView(true, "Error Happen");
        }
    }

    public void unRegisterOemHookCallbackForMiBroadcastDataRegistrant(String str, Registrant registrant) {
        log("unRegisterOemHookCallbackForMiBroadcastDataRegistrant");
        OemHookAgent oemHookAgent = mMtbHookAgent;
        if (oemHookAgent == null) {
            log("mMtbHookAgent is null");
            return;
        }
        if (str == null) {
            log("packageName is null");
        } else if (registrant == null) {
            log("registrant is null");
        } else {
            oemHookAgent.unRegisterOemHookCallbackForMiBroadcastDataRegistrant(str, registrant);
        }
    }

    protected void unregisterMtbTelephonyCallback(TelephonyManager telephonyManager, MtbTelephonyCallback mtbTelephonyCallback) {
        if (telephonyManager == null || mtbTelephonyCallback == null) {
            return;
        }
        telephonyManager.unregisterTelephonyCallback(mtbTelephonyCallback);
    }

    public static void onAddList(int i2, Intent intent, String str) {
        mObjMtbToolsList.add(new ModemTestBoxItem(i2, intent, str));
    }

    public void freezeViewForModemSsr(MtbCallbackBase mtbCallbackBase) {
        freezeViewForModemSsr(mtbCallbackBase, VIEW_FREEZE_DONE_FOR_SSR_DELAY_MS);
    }

    public void freezeViewForRildRest(MtbCallbackBase mtbCallbackBase) {
        log("freezeViewForRildRest, mtbCallback = " + mtbCallbackBase);
        this.mMtbCallback = mtbCallbackBase;
        disableView();
        onUpdateOptStatusView(false, getString(R.string.mtb_tool_setting_wait) + ": 2s");
        onSendMsg(EVENT_VIEW_FREEZE_FOR_RILD_DONE, VIEW_FREEZE_DONE_FOR_RILD_DELAY_MS);
    }

    public void onRemoveMsg(Handler handler, int i2) {
        if (handler != null) {
            handler.removeMessages(i2);
            return;
        }
        log("onRemoveMsg, hdl is null, msg_id = " + i2);
    }

    public void onSendMsg(int i2, int i3) {
        onSendMsg(this.mHandler, i2, i3);
    }

    public void showCheckDialogForSendMsg(String str, String str2, Message message) {
        showDialog(str, str2, null, 2, null, message, null, 0, 0, null, true, true);
    }

    class MtbTelephonyCallback extends TelephonyCallback implements TelephonyCallback.DataConnectionStateListener, TelephonyCallback.SignalStrengthsListener, TelephonyCallback.ServiceStateListener, TelephonyCallback.DisplayInfoListener {
        private int mSub;

        public MtbTelephonyCallback() {
            this.mSub = 0;
            MtbBaseActivity.this.log("Constructor, mSub = " + this.mSub);
        }

        @Override // android.telephony.TelephonyCallback.ServiceStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            MtbBaseActivity.this.onMtbServiceStateChanged(this.mSub, serviceState);
        }

        public MtbTelephonyCallback(int i2) {
            this.mSub = 0;
            MtbBaseActivity.this.log("Constructor, sub = " + i2);
            this.mSub = i2;
        }

        @Override // android.telephony.TelephonyCallback.DisplayInfoListener
        public void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
        }

        @Override // android.telephony.TelephonyCallback.SignalStrengthsListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        }

        @Override // android.telephony.TelephonyCallback.DataConnectionStateListener
        public void onDataConnectionStateChanged(int i2, int i3) {
        }
    }

    public static void onAddList(int i2, String str, String str2, Intent intent) {
        mObjMtbToolsList.add(new ModemTestBoxItem(i2, str, str2, intent));
    }

    public void freezeViewForModemSsr(int i2) {
        freezeViewForModemSsr(null, i2);
    }

    public Dialog onCreateFileOpenDialog(int i2, String str) {
        log("onCreateFileOpenDialog, msgId: " + i2 + ", tip: " + str);
        return onCreateFileOpenDialog(null, i2, MtbOpenFileDialog.PATH_SUFFIX_DEF, str, null);
    }

    public void onSendMsg(Message message) {
        onSendMsg(this.mHandler, message);
    }

    public void showCheckDialogForSendMsg(String str, String str2, String str3, int i2) {
        showDialog(str, str2, str3, 2, null, null, null, i2, 0, null, true, true);
    }

    public static void onAddList(int i2, String str, String str2, Intent intent, String str3) {
        mObjMtbToolsList.add(new ModemTestBoxItem(i2, str, str2, intent, str3));
    }

    public void freezeViewForModemSsr(MtbCallbackBase mtbCallbackBase, int i2) {
        log("freezeViewForModemSsr, mtbCallback = " + mtbCallbackBase + ", waitMs = " + i2);
        this.mMtbCallback = mtbCallbackBase;
        disableView();
        onUpdateOptStatusView(false, getString(R.string.mtb_tool_setting_wait) + ": " + (i2 / POLL_UPDATE_INTERVAL_MIN_MS) + "s");
        onSendMsg(EVENT_VIEW_FREEZE_FOR_SSR_DONE, i2);
    }

    public void onSendMsg(Handler handler, Message message) {
        if (handler == null) {
            log("onSendMsg, hdl is null");
        } else {
            handler.sendMessage(message);
        }
    }

    public void showCheckDialogForSendMsg(String str, String str2, int i2) {
        showDialog(str, str2, null, 2, null, null, null, i2, 0, null, true, true);
    }

    public Dialog onCreateFileOpenDialog(Handler handler, int i2, String str) {
        log("onCreateFileOpenDialog, hdl: " + handler + ", msgId: " + i2 + ", tip: " + str);
        return onCreateFileOpenDialog(handler, i2, MtbOpenFileDialog.PATH_SUFFIX_DEF, str, null);
    }

    public void showCheckDialogForSendMsg(String str, String str2, String str3, int i2, int i3) {
        showDialog(str, str2, str3, 2, null, null, null, i2, i3, null, true, true);
    }

    protected void onCommonInit() {
        logc("onCommonInit");
        mContext = this;
        setViewInitIdle();
        registerMtbBaseBroadcastReceiver();
        mMtbUtils = new MtbUtils();
    }

    public void onSendMsg(Handler handler, int i2) {
        if (handler == null) {
            log("onSendMsg, hdl is null, msg_id = " + i2);
            return;
        }
        handler.sendEmptyMessage(i2);
    }

    public void showCheckDialogForSendMsg(String str, String str2, int i2, int i3) {
        showDialog(str, str2, null, 2, null, null, null, i2, i3, null, true, true);
    }

    public PollUpdateThread newAndStartPollUpdate(MtbBaseActivity mtbBaseActivity, int i2, int i3) {
        if (mtbBaseActivity == null) {
            onUpdateOptStatusView(true, "The poll updating thread can not be started, view is null");
            return null;
        }
        if (i2 <= 0) {
            onUpdateOptStatusView(true, "The poll updating thread can not be started, intervalMs <= 0");
            return null;
        }
        if (i3 <= 0) {
            onUpdateOptStatusView(true, "The poll updating thread can not be started, cycleCount <= 0");
            return null;
        }
        PollUpdateThread pollUpdateThread = new PollUpdateThread(mtbBaseActivity, i2, i3);
        pollUpdateThread.start();
        log("newAndStartPollUpdate, intervalMs = " + i2 + ", cycleCount = " + i3 + ", view = " + mtbBaseActivity + ", obj = " + pollUpdateThread);
        return pollUpdateThread;
    }

    public Dialog onCreateFileOpenDialog(int i2, String str, String str2) {
        log("onCreateFileOpenDialog, msgId: " + i2 + ", strSuffix: " + str + ", tip: " + str2);
        return onCreateFileOpenDialog(null, i2, str, str2, null);
    }

    public void showCheckDialogForSendMsg(String str, String str2, String str3, Handler handler, int i2) {
        showDialog(str, str2, str3, 2, handler, null, null, i2, 0, null, true, true);
    }

    public void onSendMsg(Handler handler, int i2, int i3) {
        if (handler == null) {
            log("onSendMsg, hdl is null, msg_id = " + i2 + ", delayMs = " + i3);
            return;
        }
        handler.sendEmptyMessageDelayed(i2, i3);
    }

    public Dialog onCreateFileOpenDialog(final Handler handler, final int i2, String str, final String str2, final TextView textView) {
        log("onCreateFileOpenDialog, hdl = " + handler);
        log("onCreateFileOpenDialog, msgId = " + i2);
        log("onCreateFileOpenDialog, strSuffix = " + str);
        log("onCreateFileOpenDialog, tip = " + str2);
        log("onCreateFileOpenDialog, txt = " + textView);
        if (mContext == null) {
            onUpdateOptStatusView(true, "mContext is null");
            return null;
        }
        HashMap map = new HashMap();
        map.put(MtbOpenFileDialog.PATH_ROOT, Integer.valueOf(R.drawable.filedialog_root));
        map.put(MtbOpenFileDialog.PATH_PARENT, Integer.valueOf(R.drawable.filedialog_folder_up));
        map.put(MtbOpenFileDialog.PATH_FOLDER, Integer.valueOf(R.drawable.filedialog_folder));
        map.put("wav", Integer.valueOf(R.drawable.filedialog_wavfile));
        map.put(MtbOpenFileDialog.IMG_FILE_DEFAULT_ICON, Integer.valueOf(R.drawable.filedialog_file));
        return MtbOpenFileDialog.createDialog(mContext, getString(R.string.mtb_tool_select_file), new MtbCallbackBundle() { // from class: com.xiaomi.mtb.activity.MtbBaseActivity.3
            @Override // com.xiaomi.mtb.MtbCallbackBundle
            public void callback(Bundle bundle) {
                String string = bundle.getString(MtbOpenFileDialog.BUNDLE_PATH);
                MtbBaseActivity.this.log("onCreateFileOpenDialog, path = " + string + ", msgId = " + i2);
                String str3 = str2;
                if (str3 != null) {
                    MtbBaseActivity.this.onUpdateOptStatusView(false, str3);
                }
                TextView textView2 = textView;
                if (textView2 != null) {
                    textView2.setText(string);
                }
                MtbBaseActivity mtbBaseActivity = MtbBaseActivity.this;
                Handler handler2 = mtbBaseActivity.mHandler;
                Handler handler3 = handler;
                if (handler3 != null) {
                    handler2 = handler3;
                }
                if (i2 >= 0) {
                    if (handler2 == null) {
                        mtbBaseActivity.onUpdateOptStatusView(true, "hdlDo is null");
                        return;
                    }
                    Message messageObtain = Message.obtain();
                    messageObtain.what = i2;
                    messageObtain.setData(bundle);
                    handler2.sendMessage(messageObtain);
                }
            }
        }, str, map);
    }

    public class PollUpdateThread extends Thread {
        private Context dContext;
        public volatile int dCycleCount;
        public volatile int dEvtPollStart;
        public volatile int dEvtPollStop;
        public volatile int dEvtPollUpdate;
        public volatile boolean dExit;
        private Handler dHandler;
        public volatile int dIntervalMs;
        private MtbBaseActivity dView;

        public PollUpdateThread() {
            this.dContext = null;
            this.dHandler = null;
            this.dView = null;
            this.dExit = false;
            this.dEvtPollStart = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dEvtPollUpdate = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dEvtPollStop = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dIntervalMs = MtbBaseActivity.POLL_UPDATE_INTERVAL_MIN_MS;
            this.dCycleCount = -1;
            MtbBaseActivity.this.log("PollUpdateThread Constructor");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            MtbBaseActivity.this.log("PollUpdateThread run, dContext = " + this.dContext + ", dHandler = " + this.dHandler + ", dView = " + this.dView + ", dEvtPollStart = " + this.dEvtPollStart + ", dEvtPollUpdate = " + this.dEvtPollUpdate + ", dEvtPollStop = " + this.dEvtPollStop + ", dIntervalMs = " + this.dIntervalMs);
            if (this.dHandler != null && MtbBaseActivity.POLL_UPDATE_EVENT_IDLE != this.dEvtPollStart) {
                this.dHandler.sendEmptyMessage(this.dEvtPollStart);
            }
            MtbBaseActivity mtbBaseActivity = this.dView;
            if (mtbBaseActivity != null) {
                mtbBaseActivity.onPollUpdateStart(this);
            }
            int i2 = this.dIntervalMs / 100;
            int i3 = this.dIntervalMs % 100;
            int i4 = 0;
            while (!this.dExit && (i4 < this.dCycleCount || -1 == this.dCycleCount)) {
                MtbBaseActivity.this.tmpLog("dIntervalMs: " + this.dIntervalMs + ", dExit: " + this.dExit + ", cycleCount: " + i4 + ", dCycleCount: " + this.dCycleCount);
                if (this.dHandler != null && MtbBaseActivity.POLL_UPDATE_EVENT_IDLE != this.dEvtPollUpdate) {
                    this.dHandler.sendEmptyMessage(this.dEvtPollUpdate);
                }
                MtbBaseActivity mtbBaseActivity2 = this.dView;
                if (mtbBaseActivity2 != null) {
                    mtbBaseActivity2.onPollUpdateDo(this);
                }
                if (true == this.dExit) {
                    MtbBaseActivity.this.log("data-updating is stoped");
                    break;
                }
                try {
                    MtbBaseActivity.this.tmpLog("sleep start, msS1 = " + i2 + ", msS2 = " + i3 + ", dExit = " + this.dExit);
                    for (int i5 = 0; i5 < i2 && !this.dExit; i5++) {
                        MtbBaseActivity.this.tmpLog("msS1[" + i5 + "] will wait 100 ms");
                        Thread.sleep(100L);
                    }
                    if (i3 != 0 && !this.dExit) {
                        MtbBaseActivity.this.tmpLog("msS2 will wait " + i3 + " ms");
                        Thread.sleep((long) i3);
                    }
                    MtbBaseActivity.this.tmpLog("sleep done");
                } catch (Exception e2) {
                    MtbBaseActivity.this.log("PollUpdateThread run, Exception: " + e2);
                    e2.printStackTrace();
                }
                i4++;
                MtbBaseActivity.this.tmpLog("dExit = " + this.dExit + ", cycleCount = " + i4);
            }
            MtbBaseActivity.this.log("PollUpdateThread quit, obj = " + this);
            if (this.dHandler != null && MtbBaseActivity.POLL_UPDATE_EVENT_IDLE != this.dEvtPollStop) {
                this.dHandler.sendEmptyMessage(this.dEvtPollStop);
            }
            MtbBaseActivity mtbBaseActivity3 = this.dView;
            if (mtbBaseActivity3 != null) {
                mtbBaseActivity3.onPollUpdateStop(this);
            }
        }

        public PollUpdateThread(Context context, Handler handler, int i2, int i3, int i4, int i5) {
            this.dContext = null;
            this.dHandler = null;
            this.dView = null;
            this.dExit = false;
            this.dEvtPollStart = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dEvtPollUpdate = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dEvtPollStop = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dIntervalMs = MtbBaseActivity.POLL_UPDATE_INTERVAL_MIN_MS;
            this.dCycleCount = -1;
            MtbBaseActivity.this.log("PollUpdateThread Constructor, dExit = " + this.dExit + ", evtPollStart = " + i2 + ", evtPollUpdate = " + i3 + ", evtPollStop = " + i4 + ", intervalMs = " + i5);
            this.dContext = context;
            this.dHandler = handler;
            this.dEvtPollStart = i2;
            this.dEvtPollUpdate = i3;
            this.dEvtPollStop = i4;
            this.dIntervalMs = i5;
        }

        public PollUpdateThread(MtbBaseActivity mtbBaseActivity, int i2, int i3) {
            this.dContext = null;
            this.dHandler = null;
            this.dView = null;
            this.dExit = false;
            this.dEvtPollStart = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dEvtPollUpdate = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dEvtPollStop = MtbBaseActivity.POLL_UPDATE_EVENT_IDLE;
            this.dIntervalMs = MtbBaseActivity.POLL_UPDATE_INTERVAL_MIN_MS;
            this.dCycleCount = -1;
            MtbBaseActivity.this.log("PollUpdateThread Constructor, dExit = " + this.dExit + ", view = " + mtbBaseActivity + ", intervalMs = " + i2 + ", cycleCount = " + i3);
            this.dView = mtbBaseActivity;
            this.dIntervalMs = i2;
            this.dCycleCount = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUpdateOptStatusView(Bundle bundle) {
        log("onUpdateOptStatusView, data = " + bundle);
        if (bundle == null) {
            log("data is null");
        } else {
            onUpdateOptStatusView(bundle.getBoolean(BUNDLE_UPDATE_OPT_STATUS_VIEW_ERRO, false), bundle.getString(BUNDLE_UPDATE_OPT_STATUS_VIEW_DESC, "null"));
        }
    }
}
