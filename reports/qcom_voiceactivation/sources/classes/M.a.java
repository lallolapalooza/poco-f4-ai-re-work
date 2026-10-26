package M;

import android.os.IDeviceIdleController;
import android.os.ServiceManager;

/* JADX INFO: compiled from: DeviceIdleControllerS.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static void a(String str, long j2, String str2) {
        IDeviceIdleController iDeviceIdleControllerB = b();
        if (iDeviceIdleControllerB != null) {
            try {
                iDeviceIdleControllerB.addPowerSaveTempWhitelistApp(str, j2, 0, 14, str2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static IDeviceIdleController b() {
        return IDeviceIdleController.Stub.asInterface(ServiceManager.getService("deviceidle"));
    }
}
