package j2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.miui.guardprovider.aidl.IURLScanServer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f47874e = "URLFilterManager";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f47875a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IURLScanServer f47877c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ServiceConnection f47878d = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CountDownLatch f47876b = new CountDownLatch(1);

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(f.f47874e, "on GPService Connected");
            f.this.f47877c = IURLScanServer.Stub.A0(iBinder);
            f.this.f47876b.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(f.f47874e, "on GPService DisConnected");
            f.this.f47877c = null;
        }
    }

    public f(Context context) {
        this.f47875a = context.getApplicationContext();
        c();
        try {
            boolean zAwait = this.f47876b.await(8L, TimeUnit.SECONDS);
            Log.e(f47874e, "mCountDownLatch.await result:" + zAwait);
        } catch (InterruptedException e10) {
            Log.e(f47874e, "exception when create URLFilterManager: ", e10);
        }
    }

    private void c() {
        Intent intent = new Intent("com.miui.guardprovider.action.urlscan");
        intent.setPackage("com.miui.guardprovider");
        this.f47875a.bindService(intent, this.f47878d, 1);
    }

    private void e() {
        if (this.f47877c != null) {
            this.f47877c = null;
            this.f47875a.unbindService(this.f47878d);
            Log.i(f47874e, "unbindGuardProviderService");
        }
    }

    public int d(String str) {
        int iB3 = -1;
        try {
            try {
                IURLScanServer iURLScanServer = this.f47877c;
                if (iURLScanServer != null) {
                    iB3 = iURLScanServer.b3("AVL", str);
                }
            } catch (RemoteException e10) {
                Log.e(f47874e, "exception in scanURL : " + e10.toString());
            }
            e();
            Log.i(f47874e, "scanURL result = " + iB3);
            return iB3;
        } catch (Throwable th2) {
            e();
            throw th2;
        }
    }

    public void f() {
        int iF;
        String str;
        String str2;
        try {
            try {
                IURLScanServer iURLScanServer = this.f47877c;
                iF = iURLScanServer != null ? iURLScanServer.F("AVL") : -1;
                e();
            } catch (RemoteException e10) {
                Log.e(f47874e, "exception in updateURLRule : " + e10.toString());
                e();
                iF = -1;
            }
            if (iF == -1) {
                str = f47874e;
                str2 = "AVL URL rules update failed !";
            } else {
                if (iF != 0 && iF != 1) {
                    return;
                }
                str = f47874e;
                str2 = "AVL URL rules update success !";
            }
            Log.i(str, str2);
        } catch (Throwable th2) {
            e();
            throw th2;
        }
    }
}
