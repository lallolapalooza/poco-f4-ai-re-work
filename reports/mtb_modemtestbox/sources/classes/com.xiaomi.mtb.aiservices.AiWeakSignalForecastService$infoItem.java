package com.xiaomi.mtb.aiservices;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemProperties;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.xiaomi.infra.galaxy.fds.Common;
import com.xiaomi.modem.ModemUtils;
import com.xiaomi.modem.OemHookAgent;
import com.xiaomi.mtb.MtbTFLiteLoader;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AiWeakSignalForecastService extends Service {
    private static String AI_SIGNAL_CLOUD_CONTROL_PROPERTY = "persist.vendor.ai.signal.cloud.switch";
    private static String AI_SIGNAL_INTENT_ACTION_FORECAST_RES = "com.xiaomi.mtb.aisignal.FORECAST_RES";
    private static final int CMD_GET_SIGNAL_INFO = 200;
    private static final int FORECAST_DELAY_TIME_MILLIS = 1000;
    private static final int GET_INFO_DELAY_TIME_MILLIS = 100;
    private static final String TAG = "Ai_Weak_Signal_Forecast_Service";
    private static Intent aiSignalIntent;
    private float[][][] input;
    private long lastTime;
    private ScheduledExecutorService mExecutor;
    private MtbTFLiteLoader mTFLiteInterpreter;
    private float[][] output;
    private TelephonyManager tm;
    private OemHookAgent mMtbHookAgent = null;
    private final ArrayDeque inputDeque = new ArrayDeque();
    private String mForecastRes = "NOT Ready";
    private String current_network_type = "N/A";
    private float current_RSRP = 0.0f;
    private float current_RSRQ = 0.0f;
    private float current_SINR = 0.0f;
    private float current_PCI = 0.0f;
    private float lastPCI = 0.0f;
    private boolean isfrombroadcast = false;
    private boolean isServiceRunning = false;
    private final Runnable getSignalInfo = new Runnable() { // from class: com.xiaomi.mtb.aiservices.AiWeakSignalForecastService.1
        @Override // java.lang.Runnable
        @SuppressLint({"DefaultLocale"})
        public void run() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j2 = jCurrentTimeMillis - AiWeakSignalForecastService.this.lastTime;
            AiWeakSignalForecastService.this.lastTime = jCurrentTimeMillis;
            Log.d("getSignalInfo", String.format("采集使用时间 %d ms", Long.valueOf(j2)));
            String strSubstring = ("" + new Timestamp(jCurrentTimeMillis)).substring(11);
            AiWeakSignalForecastService.this.getCurrentInfo();
            AiWeakSignalForecastService aiWeakSignalForecastService = AiWeakSignalForecastService.this;
            aiWeakSignalForecastService.AddInfoDeque(new infoItem(strSubstring, aiWeakSignalForecastService.current_RSRP, AiWeakSignalForecastService.this.current_RSRQ, AiWeakSignalForecastService.this.current_SINR, AiWeakSignalForecastService.this.current_PCI != AiWeakSignalForecastService.this.lastPCI ? 1.0f : 0.0f));
            Object[] objArr = new Object[4];
            objArr[0] = Float.valueOf(AiWeakSignalForecastService.this.current_RSRP);
            objArr[1] = Float.valueOf(AiWeakSignalForecastService.this.current_RSRQ);
            objArr[2] = Float.valueOf(AiWeakSignalForecastService.this.current_SINR);
            objArr[3] = Float.valueOf(AiWeakSignalForecastService.this.current_PCI == AiWeakSignalForecastService.this.lastPCI ? 0.0f : 1.0f);
            Log.d("getSignalInfo", String.format("采集到的数据为：RSRP:%.1f   RSRQ:%.1f   SINR:%.1f   PCI:%.1f", objArr));
            AiWeakSignalForecastService aiWeakSignalForecastService2 = AiWeakSignalForecastService.this;
            aiWeakSignalForecastService2.lastPCI = aiWeakSignalForecastService2.current_PCI;
        }
    };
    private final Runnable forecastRes = new Runnable() { // from class: com.xiaomi.mtb.aiservices.AiWeakSignalForecastService.2
        @Override // java.lang.Runnable
        @SuppressLint({"DefaultLocale"})
        public void run() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            ("" + new Timestamp(jCurrentTimeMillis)).substring(11, 22);
            Log.d("forecastRes", String.format("inputDeque.size()=%d", Integer.valueOf(AiWeakSignalForecastService.this.inputDeque.size())));
            if (AiWeakSignalForecastService.this.inputDeque.size() == 100) {
                Log.d("forecastRes", "开始推演");
                AiWeakSignalForecastService.this.makeInputFromInfoItem();
                AiWeakSignalForecastService.this.mTFLiteInterpreter.run(AiWeakSignalForecastService.this.input, AiWeakSignalForecastService.this.output);
                AiWeakSignalForecastService aiWeakSignalForecastService = AiWeakSignalForecastService.this;
                aiWeakSignalForecastService.mForecastRes = aiWeakSignalForecastService.output[0][1] < 0.5f ? "WEAK" : "STRONG";
            }
            Log.d("forecastRes", String.format("推演任务使用时间 %d ms---当前预测结果为:" + AiWeakSignalForecastService.this.mForecastRes, Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
            if (AiWeakSignalForecastService.this.isfrombroadcast) {
                Intent intent = new Intent();
                intent.setAction("com.xiaomi.mtb.aisignal.FORECAST_RES");
                intent.putExtra("FORECAST_RES", AiWeakSignalForecastService.this.mForecastRes);
                AiWeakSignalForecastService.this.sendBroadcast(intent);
            }
        }
    };
    private final IBinder mBinder = new IAiWeakSignal.Stub() { // from class: com.xiaomi.mtb.aiservices.AiWeakSignalForecastService.3
        @Override // com.xiaomi.mtb.aiservices.IAiWeakSignal
        public String getForecastRes(String str) {
            return AiWeakSignalForecastService.this.mForecastRes;
        }
    };

    class infoItem {
        private float PCI;
        private float RSRP;
        private float RSRQ;
        private float SINR;
        private String time;

        public infoItem(String str, float f2, float f3, float f4, float f5) {
            this.time = str;
            this.RSRP = f2;
            this.RSRQ = f3;
            this.SINR = f4;
            this.PCI = f5;
        }

        public float getPCI() {
            return this.PCI;
        }

        public float getRSRP() {
            return this.RSRP;
        }

        public float getRSRQ() {
            return this.RSRQ;
        }

        public float getSINR() {
            return this.SINR;
        }

        public String getTime() {
            return this.time;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void AddInfoDeque(infoItem infoitem) {
        if (this.inputDeque.size() == 100) {
            this.inputDeque.removeFirst();
        }
        this.inputDeque.add(infoitem);
    }

    private void SignalInfoHandle(String str, ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            Log.d(TAG, "byteBuf is null");
            return;
        }
        str.hashCode();
        if (str.equals("getLteMeasInfo")) {
            updateLteMeasInfo(byteBuffer);
        } else if (str.equals("getNr5gMeasInfo")) {
            update5gMeasInfo(byteBuffer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCurrentInfo() {
        Log.d(TAG, "on getCurrentInfo");
        int defaultDataSlotId = getDefaultDataSlotId();
        Log.d(TAG, "active data Sub:" + defaultDataSlotId);
        int networkType = this.tm.getNetworkType();
        if (networkType == 13) {
            this.current_network_type = "LTE";
            SignalInfoHandle("getLteMeasInfo", this.mMtbHookAgent.getLteMeasInfo(defaultDataSlotId));
        } else {
            if (networkType != 20) {
                return;
            }
            this.current_network_type = "NR";
            SignalInfoHandle("getNr5gMeasInfo", this.mMtbHookAgent.getNr5gMeasInfo(defaultDataSlotId));
        }
    }

    private int getDefaultDataSlotId() {
        int phoneId = SubscriptionManager.getPhoneId(SubscriptionManager.getActiveDataSubscriptionId());
        if (phoneId != 1) {
            return 0;
        }
        return phoneId;
    }

    private void initService(Intent intent) {
        if (SystemProperties.get(AI_SIGNAL_CLOUD_CONTROL_PROPERTY).equals(ModemUtils.PROP_SAR_LOG_SWITCH_OPEN)) {
            stopSelf();
            Log.d(TAG, "cloud control switch is closed! service will stop!");
        }
        OemHookAgent hook = OemHookAgent.getHook();
        this.mMtbHookAgent = hook;
        try {
            hook.getLteMeasInfo(0);
        } catch (NullPointerException unused) {
            Log.d(TAG, "捕获到了NullPointerException异常,mMtbHookAgent为空!");
        }
        int[] iArr = {1, Common.HTTP_STATUS_BAD_REQUEST, 1};
        Class cls = Float.TYPE;
        this.input = (float[][][]) Array.newInstance((Class<?>) cls, iArr);
        this.output = (float[][]) Array.newInstance((Class<?>) cls, 1, 2);
        this.tm = (TelephonyManager) getSystemService("phone");
        this.mTFLiteInterpreter = new MtbTFLiteLoader(getApplicationContext(), "0.3_new_0410_less.tflite");
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(10);
        this.mExecutor = scheduledExecutorServiceNewScheduledThreadPool;
        Runnable runnable = this.getSignalInfo;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(runnable, 0L, 100L, timeUnit);
        this.mExecutor.scheduleAtFixedRate(this.forecastRes, 0L, 1000L, timeUnit);
    }

    private static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeInputFromInfoItem() {
        Log.d(TAG, "on makeInputFromInfoItem");
        float[] fArr = new float[100];
        float[] fArr2 = new float[100];
        float[] fArr3 = new float[100];
        int i2 = 0;
        for (infoItem infoitem : this.inputDeque) {
            fArr[i2] = infoitem.getRSRP();
            fArr2[i2] = infoitem.getRSRQ();
            fArr3[i2] = infoitem.getSINR();
            this.input[0][i2 + 300][0] = infoitem.getPCI();
            i2++;
        }
        float[] fArrMedianFilter = medianFilter(fArr, 5);
        float[] fArrMedianFilter2 = medianFilter(fArr2, 5);
        float[] fArrMedianFilter3 = medianFilter(fArr3, 5);
        for (int i3 = 0; i3 < 100; i3++) {
            float[][] fArr4 = this.input[0];
            fArr4[i3][0] = fArrMedianFilter[i3];
            fArr4[i3 + 100][0] = fArrMedianFilter2[i3];
            fArr4[i3 + 200][0] = fArrMedianFilter3[i3];
        }
    }

    public static float median(float[] fArr) {
        int length = fArr.length;
        int i2 = length / 2;
        quickSort(fArr, 0, length - 1);
        return length % 2 == 0 ? (fArr[i2 - 1] + fArr[i2]) * 0.5f : fArr[i2];
    }

    public static float[] medianFilter(float[] fArr, int i2) {
        float[] fArrCopyOfRange;
        Log.d(TAG, "In medianFilter");
        int length = fArr.length;
        if (i2 > length) {
            return null;
        }
        int i3 = i2 / 2;
        float[] fArr2 = new float[length];
        for (int i4 = 0; i4 < length; i4++) {
            if (i4 <= i3) {
                fArrCopyOfRange = Arrays.copyOfRange(fArr, 0, i4 + i3 + 1);
            } else {
                int i5 = i4 + i3;
                fArrCopyOfRange = i5 >= length ? Arrays.copyOfRange(fArr, i4 - i3, length) : Arrays.copyOfRange(fArr, i4 - i3, i5 + 1);
            }
            fArr2[i4] = median(fArrCopyOfRange);
        }
        return fArr2;
    }

    public static void onAiSignalBcHandle(Context context) {
        if (aiSignalIntent != null) {
            log("Ai_Signal service stop!");
            Toast.makeText(context, "Ai_Signal service stop!", 1).show();
            context.stopService(aiSignalIntent);
            aiSignalIntent = null;
            return;
        }
        log("Ai_Signal service start!");
        Toast.makeText(context, "Ai_Signal service start!", 1).show();
        Intent intent = new Intent(context, (Class<?>) AiWeakSignalForecastService.class);
        aiSignalIntent = intent;
        context.startService(intent);
    }

    public static void onAiSignalGameStatusBcHandle(Context context, Intent intent) {
        if (intent.getIntExtra("gameStatus", 0) == 0) {
            log("Receive AI_SIGNAL_GAME_EXIT");
            if (aiSignalIntent == null) {
                log("game exit, Ai_Signal service not run!");
                Toast.makeText(context, "Ai_Signal service not run!", 1).show();
                return;
            } else {
                log("Ai_Signal service will stop from broadcast!");
                Toast.makeText(context, "Ai_Signal service stop!", 1).show();
                context.stopService(aiSignalIntent);
                aiSignalIntent = null;
                return;
            }
        }
        log("Receive AI_SIGNAL_GAME_START");
        if (aiSignalIntent == null) {
            log("Ai_Signal service start from broadcast!");
            Toast.makeText(context, "Ai_Signal service start!", 1).show();
        } else {
            log("Ai_Signal service running , will restart it!");
            Toast.makeText(context, "Ai_Signal service restart!", 1).show();
            context.stopService(aiSignalIntent);
            aiSignalIntent = null;
        }
        Intent intent2 = new Intent(context, (Class<?>) AiWeakSignalForecastService.class);
        aiSignalIntent = intent2;
        intent2.putExtra("GAME_BroadCast", "True");
        context.startService(aiSignalIntent);
    }

    public static void quickSort(float[] fArr, int i2, int i3) {
        float f2;
        float f3 = fArr[((i3 - i2) / 2) + i2];
        int i4 = i2;
        int i5 = i3;
        while (i4 <= i5) {
            while (fArr[i4] < f3) {
                i4++;
            }
            while (true) {
                f2 = fArr[i5];
                if (f2 <= f3) {
                    break;
                } else {
                    i5--;
                }
            }
            if (i4 <= i5) {
                float f4 = fArr[i4];
                fArr[i4] = f2;
                fArr[i5] = f4;
                i4++;
                i5--;
            }
        }
        if (i2 < i5) {
            quickSort(fArr, i2, i5);
        }
        if (i4 < i3) {
            quickSort(fArr, i4, i3);
        }
    }

    private void update5gMeasInfo(ByteBuffer byteBuffer) {
        Log.d(TAG, "on update NR Meas Info:");
        byteBuffer.get();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        float f2 = byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        this.current_PCI = byteBuffer.getInt();
        float f3 = byteBuffer.getFloat();
        float f4 = byteBuffer.getFloat();
        if (f3 <= -30.0f && f3 >= -150.0f) {
            this.current_RSRP = f3;
        }
        if (f2 >= -40.0f && f2 <= 10.0f) {
            this.current_RSRQ = f2;
        }
        if (f4 < -20.0f || f4 > 40.0f) {
            return;
        }
        this.current_SINR = f4;
    }

    private void updateLteMeasInfo(ByteBuffer byteBuffer) {
        Log.d(TAG, "on update LTE Meas Info:");
        byteBuffer.get();
        byteBuffer.getInt();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        byteBuffer.getFloat();
        float f2 = byteBuffer.getFloat();
        float f3 = byteBuffer.getFloat();
        float f4 = byteBuffer.getFloat();
        this.current_PCI = byteBuffer.getInt();
        if (f2 <= -30.0f && f2 >= -150.0f) {
            this.current_RSRP = f2;
        }
        if (f3 >= -40.0f && f3 <= 10.0f) {
            this.current_RSRQ = f3;
        }
        if (f4 < -20.0f || f4 > 40.0f) {
            return;
        }
        this.current_SINR = f4;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind AiWeakSignalForecastService");
        if (!this.isServiceRunning) {
            initService(intent);
            this.isServiceRunning = true;
        }
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mExecutor.shutdown();
        this.isServiceRunning = false;
        Log.d(TAG, "onDestroy: Ai_Weak_Signal_Forecast_Service Destroyed");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Log.d(TAG, "onStartCommand , " + i3);
        if (!this.isServiceRunning) {
            initService(intent);
            this.isfrombroadcast = intent.getExtra("GAME_BroadCast", ModemUtils.PROP_SAR_LOG_SWITCH_CLOSE).equals("True");
            Log.d(TAG, "isfrombroadcast=" + Boolean.toString(this.isfrombroadcast));
            this.isServiceRunning = true;
        }
        return super.onStartCommand(intent, i2, i3);
    }
}
