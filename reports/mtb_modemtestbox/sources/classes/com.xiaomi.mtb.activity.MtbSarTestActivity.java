package com.xiaomi.mtb.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Message;
import android.os.Process;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import com.xiaomi.modem.DeviceStateListenerBase;
import com.xiaomi.modem.OemHookAgent;
import com.xiaomi.mtb.MtbFloatingDisplayView;
import com.xiaomi.mtb.MtbUtils;
import com.xiaomi.mtb.R;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class MtbSarTestActivity extends MtbBaseActivity {
    private static final int AUDIO_SAMPLE_RATE = 8000;
    private static final String BC_ACTION_DEVICE_STATE = "xiaomi.intent.action.DEVICE_STATE";
    private static final String BC_PARAMS_DEVICE_STATE_TYPE = "BC_PARAMS_DEVICE_STATE_TYPE";
    private static final String BC_PARAMS_DEVICE_STATE_VAL = "BC_PARAMS_DEVICE_STATE_VAL";
    private static final int DEVICE_STATE_NUM_MAX = 100;
    private static final int EVENT_MODEM_SAR_SWITCH_SET = 8;
    private static final String LOG_TAG = "MtbSarTestActivity";
    private static final String PROPERTY_DEVICE_STATE_PREFIX = "debug.device.";
    private static final String PROPERTY_SAR_CURRENT_JUDGE_DSI = "debug.modem.sar.judge_dsi";
    private static int SAR_DSI_VALUE_MAX = 20;
    private static int SAR_DSI_VALUE_MIN = 0;
    private static AudioManager mAudioManager = null;
    private static int mAudioReceiverSwtich = -1;
    private static AudioTrack mAudioTrack = null;
    private static byte[] mAudioTrackBuffer = null;
    private static int mAudioTrackBufferSize = 0;
    private static AudioTrackRunnale mAudioTrackRunnable = null;
    private static Thread mAudioTrackThread = null;
    private static int mHotspotSwtich = -1;
    private static int mSarDsiToBeSet = -1;
    private static int mSarSwtich = -1;
    private static String[] mStrArrDsiSupport = {"0", "1", "2", "3", "4", MtbFloatingDisplayView.FLOAT_VIEW_TXT_COLOR_CHANGE, MtbFloatingDisplayView.FLOAT_VIEW_BG_COLOR_CHANGE, MtbFloatingDisplayView.FLOAT_VIEW_TRANS_BIG, MtbFloatingDisplayView.FLOAT_VIEW_TRANS_SMALL, MtbFloatingDisplayView.FLOAT_VIEW_MOVE_UP, MtbFloatingDisplayView.FLOAT_VIEW_MOVE_DOWN, MtbFloatingDisplayView.FLOAT_VIEW_MOVE_UPDATE, "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    private final int SUB_0 = 0;
    private final int SUB_1 = 1;
    private final String COLOR_BG_DEFAULT_VALUE = "#0BA683";
    private final String COLOR_BG_TEST_VALUE = "#FF0000";
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.xiaomi.mtb.activity.MtbSarTestActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                MtbSarTestActivity.this.log("mBroadcastReceiver, intent is null");
                return;
            }
            String action = intent.getAction();
            MtbSarTestActivity.this.log("========================= BroadcastReceiver action: " + action);
            if (MtbSarTestActivity.BC_ACTION_DEVICE_STATE.equals(action)) {
                MtbSarTestActivity.this.log("BC_ACTION_DEVICE_STATE");
                MtbSarTestActivity.this.saveDeviceStateChangeEventFromBroadcast(intent);
            }
        }
    };

    class AudioTrackRunnale implements Runnable {
        private AudioTrackRunnale() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MtbSarTestActivity.this.log("audio play start...." + Process.myTid());
            while (MtbSarTestActivity.mAudioTrackThread != null) {
                synchronized (this) {
                    if (MtbSarTestActivity.mAudioTrack != null) {
                        MtbSarTestActivity.this.log("AudioTrack.write()");
                        MtbSarTestActivity.mAudioTrack.write(MtbSarTestActivity.mAudioTrackBuffer, 0, MtbSarTestActivity.mAudioTrackBufferSize);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d(LOG_TAG, "MTB_ " + str);
    }

    private boolean onCheckDsiValue(boolean z, String str) {
        if (str == null) {
            onUpdateOptStatusView(true, "The value you input is null ptr");
            return false;
        }
        String strTrim = str.trim();
        if ("".equals(strTrim)) {
            if (z) {
                onUpdateOptStatusView(true, "The value you input is empty string");
            } else {
                log("The value you input is empty string");
            }
            return false;
        }
        if (!onIsNumber(strTrim)) {
            onUpdateOptStatusView(true, "The value you input is not a number");
            return false;
        }
        byte bByteValue = new BigDecimal(strTrim).byteValue();
        if (bByteValue <= SAR_DSI_VALUE_MAX && bByteValue >= SAR_DSI_VALUE_MIN) {
            onUpdateOptStatusView(false, getString(R.string.mtb_tool_opt_standby));
            return true;
        }
        onUpdateOptStatusView(true, "DSI value should be >= " + SAR_DSI_VALUE_MIN + ", and <= " + SAR_DSI_VALUE_MAX);
        return false;
    }

    private boolean onIsNumber(String str) {
        if (str != null) {
            return Pattern.compile("[0-9]*").matcher(str).matches();
        }
        log("filed is null");
        return false;
    }

    private void onSarDsiSet(String str) {
        if (!onCheckDsiValue(true, str)) {
            log("onCheckDsiValue failed");
            return;
        }
        byte bByteValue = new BigDecimal(str).byteValue();
        log("onSarDsiSet, filed dsi = " + ((int) bByteValue));
        onSarDsiSet(bByteValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDeviceStateChangeEventFromBroadcast(Intent intent) {
        int intExtra = intent.getIntExtra(BC_PARAMS_DEVICE_STATE_TYPE, -1);
        int intExtra2 = intent.getIntExtra(BC_PARAMS_DEVICE_STATE_VAL, -1);
        log("saveDeviceStateChangeEventFromBroadcast, eventType = " + intExtra + ", intValue = " + intExtra2);
        onSarInfoUpdateAndShow(intExtra, intExtra2);
    }

    @Override // com.xiaomi.mtb.activity.MtbBaseActivity
    public String getClassName() {
        return LOG_TAG;
    }

    public void onCommonTest() {
        log("onCommonTest");
        ByteBuffer byteBufferOnHookCommonMsgSync = MtbBaseActivity.mMtbHookAgent.onHookCommonMsgSync(39, 0);
        if (byteBufferOnHookCommonMsgSync == null) {
            log("byteBuf is null");
            onUpdateOptStatusView(true, "Fail to get card slot status, reboot and retry!");
            return;
        }
        int i2 = byteBufferOnHookCommonMsgSync.getInt();
        int i3 = byteBufferOnHookCommonMsgSync.getInt();
        int i4 = byteBufferOnHookCommonMsgSync.getInt();
        log("t = " + i2 + ", l = " + i3 + ", status = " + i4);
        StringBuilder sb = new StringBuilder();
        sb.append("Card slot status: ");
        sb.append(i4);
        onUpdateOptStatusView(false, sb.toString());
    }

    @Override // com.xiaomi.mtb.activity.MtbBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        log("onCreate");
        onCommonInit(R.layout.modem_sar_test);
    }

    @Override // com.xiaomi.mtb.activity.MtbBaseActivity, android.app.Activity
    public void onDestroy() {
        log("onDestroy");
        super.onDestroy();
        unregisterReceiver(this.mBroadcastReceiver);
        log("onDestroy, mAudioTrackThread = " + mAudioTrackThread + ", mAudioManager = " + mAudioManager);
        if (mAudioTrackThread != null && mAudioManager != null) {
            stopAudioTrack();
        }
        mSarSwtich = -1;
        mSarDsiToBeSet = -1;
        MtbBaseActivity.mContext = null;
    }

    @Override // com.xiaomi.mtb.activity.MtbBaseActivity
    public void onHandleMessage(Message message) {
        log("onHandleMessage msg id: " + message.what);
        new Bundle();
        message.getData();
        if (message.what != 8) {
            log("invalid msg id: " + message.what);
            return;
        }
        log("EVENT_MODEM_SAR_SWITCH_SET");
        if (MtbUtils.sarSwitchStateSet(mSarSwtich)) {
            onUpdateOptStatusView(false, getString(R.string.mtb_tool_finish_with_reboot_notify));
        } else {
            onUpdateOptStatusView(true, getString(R.string.mtb_tool_opt_fail));
        }
    }

    @Override // com.xiaomi.mtb.activity.MtbBaseActivity
    public void onHookReady() {
        log("onHookReady");
        OemHookAgent hook = OemHookAgent.getHook();
        MtbBaseActivity.mMtbHookAgent = hook;
        if (hook == null) {
            onUpdateOptStatusView(true, getString(R.string.mtb_tool_hook_initing_fail_notify));
        } else {
            onSetMainView();
        }
    }

    @Override // com.xiaomi.mtb.activity.MtbBaseActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    protected void onRegisterBroadcast() {
        log("onRegisterBroadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BC_ACTION_DEVICE_STATE);
        registerReceiver(this.mBroadcastReceiver, intentFilter);
    }

    @Override // com.xiaomi.mtb.activity.MtbBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        onSarInfoUpdateAndShow();
    }

    public void onSarInfoUpdateAndShow() {
        onSarInfoUpdateAndShow(-1, -1);
    }

    public void onSetMainView() {
        log("onSetMainView");
        Spinner spinner = (Spinner) findViewById(R.id.spn_sar_test);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.xiaomi.mtb.activity.MtbSarTestActivity.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView adapterView, View view, int i2, long j2) {
                MtbSarTestActivity.this.log("spn_sar_switch, position = " + i2);
                if (MtbSarTestActivity.mSarSwtich < 0) {
                    MtbSarTestActivity.this.log("spn_sar_switch is initing");
                    int unused = MtbSarTestActivity.mSarSwtich = i2;
                } else {
                    int unused2 = MtbSarTestActivity.mSarSwtich = i2;
                    MtbSarTestActivity.this.onSendMsg(8);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView adapterView) {
                MtbSarTestActivity.this.log("spn_sar_switch, onNothingSelected.");
            }
        });
        MtbUtils.sarSwitchStateInit(spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spn_sar_dsi_set);
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, mStrArrDsiSupport));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.xiaomi.mtb.activity.MtbSarTestActivity.3
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView adapterView, View view, int i2, long j2) {
                MtbSarTestActivity.this.log("spn_sar_dsi_set, position = " + i2);
                if (MtbSarTestActivity.mSarDsiToBeSet < 0) {
                    MtbSarTestActivity.this.log("spn_sar_dsi_set is initing");
                    int unused = MtbSarTestActivity.mSarDsiToBeSet = i2;
                } else {
                    int unused2 = MtbSarTestActivity.mSarDsiToBeSet = i2;
                    MtbSarTestActivity.this.onSarDsiSet(MtbSarTestActivity.mSarDsiToBeSet);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView adapterView) {
                MtbSarTestActivity.this.log("spn_sar_dsi_set, onNothingSelected.");
            }
        });
        ((Button) findViewById(R.id.sar_info_get)).setOnClickListener(new View.OnClickListener() { // from class: com.xiaomi.mtb.activity.MtbSarTestActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MtbSarTestActivity.this.log("sar_info_get click");
                MtbSarTestActivity.this.onSarInfoUpdateAndShow();
            }
        });
        MtbUtils.onInitSarLogSwitchView(false);
        mAudioManager = (AudioManager) getSystemService("audio");
        ((Switch) findViewById(R.id.sw_audio_receiver)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaomi.mtb.activity.MtbSarTestActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MtbSarTestActivity.this.log("sw_audio_receiver click, isChecked = " + z);
                if (z) {
                    MtbSarTestActivity.this.startAudioTrack();
                } else {
                    MtbSarTestActivity.this.stopAudioTrack();
                }
            }
        });
        onUpdateOptStatusView(false, getString(R.string.mtb_tool_opt_standby));
        onRegisterBroadcast();
    }

    public synchronized void startAudioTrack() {
        log("start(), mAudioManager = " + mAudioManager);
        AudioManager audioManager = mAudioManager;
        if (audioManager == null) {
            log("startAudioTrack do nothing, mAudioManager is null");
            return;
        }
        if (mAudioTrackThread != null) {
            log("startAudioTrack do nothing, mAudioTrackThread is not null");
            return;
        }
        audioManager.setMode(2);
        int minBufferSize = AudioTrack.getMinBufferSize(AUDIO_SAMPLE_RATE, 2, 2);
        mAudioTrackBufferSize = minBufferSize;
        mAudioTrackBuffer = new byte[minBufferSize];
        AudioTrack audioTrack = new AudioTrack(0, AUDIO_SAMPLE_RATE, 2, 2, mAudioTrackBufferSize, 1);
        mAudioTrack = audioTrack;
        if (audioTrack.getState() == 0) {
            mAudioTrack = new AudioTrack(3, AUDIO_SAMPLE_RATE, 2, 2, mAudioTrackBufferSize, 1);
        }
        log("AudioTrack.play()");
        mAudioTrack.play();
        mAudioTrackRunnable = new AudioTrackRunnale();
        Thread thread = new Thread(mAudioTrackRunnable);
        mAudioTrackThread = thread;
        thread.start();
        log("start track...");
    }

    public void stopAudioTrack() {
        log("stopAudioTrack()");
        AudioManager audioManager = mAudioManager;
        if (audioManager == null) {
            log("stopAudioTrack do nothing, mAudioManager is null");
            return;
        }
        if (mAudioTrack == null) {
            log("stopAudioTrack do nothing, mAudioTrack is null");
            return;
        }
        if (mAudioTrackRunnable == null) {
            log("stopAudioTrack do nothing, mAudioTrackRunnable is null");
            return;
        }
        audioManager.setMode(0);
        if (mAudioTrackRunnable != null) {
            mAudioTrackRunnable = null;
            mAudioTrackThread = null;
        }
        if (mAudioTrack.getState() == 1) {
            mAudioTrack.stop();
            mAudioTrack.release();
        }
        log("stop track...");
    }

    public void onSarInfoUpdateAndShow(int i2, int i3) {
        log("onSarInfoUpdateAndShow, eventType = " + i2 + ", intValue = " + i3);
        String str = ("The DSI of JUDGE: " + SystemProperties.get(PROPERTY_SAR_CURRENT_JUDGE_DSI) + "\n") + "The DSI of Modem: " + MtbBaseActivity.mMtbHookAgent.getSarDsi() + "\n";
        for (int i4 = 0; i4 < 100; i4++) {
            String deviceEventTypeString = DeviceStateListenerBase.getDeviceEventTypeString(i4);
            if (deviceEventTypeString != null) {
                String str2 = SystemProperties.get(PROPERTY_DEVICE_STATE_PREFIX + deviceEventTypeString.toLowerCase());
                if (str2 != null && !"".equals(str2)) {
                    str = str + DeviceStateListenerBase.getDeviceEventTypeStringEx(i4) + ": " + str2 + "\n";
                }
            }
        }
        log("onSarInfoUpdateAndShow, strDeviceStateShow = " + str);
        ((TextView) findViewById(R.id.text_modem_sar_info)).setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSarDsiSet(int i2) {
        log("onSarDsiSet, dsi = " + i2);
        if (!MtbBaseActivity.mMtbHookAgent.setSarDsi(i2)) {
            onUpdateOptStatusView(true, getString(R.string.mtb_tool_opt_fail));
        } else {
            onSarInfoUpdateAndShow();
            onUpdateOptStatusView(false, getString(R.string.mtb_tool_opt_success));
        }
    }
}
