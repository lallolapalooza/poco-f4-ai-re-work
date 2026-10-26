package com.xiaomi.mtb;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.xiaomi.modem.OemHookAgent;
import com.xiaomi.mtb.cloud.ModemCloudUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class MtbService extends Service {
    private static final int EVENT_HOOK_READY_DONE = 100;
    private static final String TAG = "MtbService";
    private static OemHookAgent mMtbHookAgent;
    private Object mLock = new Object();
    protected Handler mHandler = new Handler() { // from class: com.xiaomi.mtb.MtbService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MtbService.log("handleMessage msg id: " + message.what);
            if (message.what == 100) {
                MtbService.log("EVENT_HOOK_READY_DONE");
                MtbService.this.onHookDone();
            } else {
                MtbService.log("invalid msg id: " + message.what);
            }
        }
    };
    private ServiceConnection mMbnServiceConnection = new ServiceConnection() { // from class: com.xiaomi.mtb.MtbService.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (MtbService.this.mLock) {
                MtbService.log("onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MtbService.log("onServiceDisconnected");
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHookDone() {
        log("onHookDone");
        MtbApp.unregisterQcRilHookReady(this.mHandler);
        mMtbHookAgent = MtbApp.getHook();
        log("mMtbHookAgent: " + mMtbHookAgent);
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        log("dump");
        MtbUtils.dumpLteBandpref(mMtbHookAgent, printWriter);
        MtbUtils.dumpRffeScanResult(mMtbHookAgent, printWriter);
        MtbUtils.dumpRfdeviceReportResult(mMtbHookAgent, printWriter);
        ModemCloudUtils.modemCloudDump(fileDescriptor, printWriter, strArr);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        log("onBind, intent = " + intent);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        log("onCreate");
        MtbApp.registerHookReadyEvent(this.mHandler, 100, null);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        log("onStartCommand, flags = " + i2 + ", startId = " + i3 + ", intent = " + intent);
        return super.onStartCommand(intent, i2, i3);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        log("onUnbind, intent = " + intent);
        return super.onUnbind(intent);
    }
}
