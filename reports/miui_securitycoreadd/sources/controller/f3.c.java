package f3;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import com.miui.aiautobrt.database.BrightnessModelDatabase;
import com.xiaomi.aiautobrt.IIndividualCallback;
import com.xiaomi.aiautobrt.IndividualModelEvent;
import com.xiaomi.aiautobrt.InterfaceVersionHelper;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c implements d.a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f3600h = SystemProperties.get("ro.build.type", "unknown");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile c f3601i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f3603b;
    public final b c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3604d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public IIndividualCallback f3606f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public f f3607g;

    public c(Context context) {
        this.f3602a = context;
        this.f3603b = new d(context, this);
        this.c = new b(context, this);
        if ("userdebug".equals(f3600h)) {
            f fVar = new f(new File(context.getFilesDir().getPath()), context, this);
            this.f3607g = fVar;
            fVar.startWatching();
        }
    }

    public static c c(Context context) {
        if (f3601i == null) {
            synchronized (c.class) {
                if (f3601i == null) {
                    f3601i = new c(context);
                }
            }
        }
        return f3601i;
    }

    public final h3.a a(IndividualModelEvent individualModelEvent) {
        h3.a aVar = new h3.a();
        aVar.c = (float) Math.log(individualModelEvent.getAmbientLux() + 1.0f);
        aVar.f4046d = (float) Math.log(individualModelEvent.getAmbientLuxSpan() + 1.0f);
        aVar.f4048f = (float) Math.log(individualModelEvent.getAppCategoryId() + 1.0f);
        aVar.f4047e = (float) Math.log(individualModelEvent.getMixedOrientationApp() + 1.0f);
        aVar.f4050h = individualModelEvent.getCurrentBrightnessSpan();
        aVar.f4049g = individualModelEvent.getCurrentBrightness();
        aVar.f4051i = individualModelEvent.getDefaultConfigBrightness();
        return aVar;
    }

    public final void b() {
        this.f3604d = 0;
        BrightnessModelDatabase.p(this.f3602a).q().c();
        BrightnessModelDatabase.p(this.f3602a).q().e();
    }

    public final List<h3.a> d(int i10) {
        return BrightnessModelDatabase.p(this.f3602a).q().a(i10);
    }

    public final void e(int i10, int i11) {
        t9.a aVar = t9.a.ERROR;
        try {
            int interfaceVersion = InterfaceVersionHelper.getInterfaceVersion("onAbTestExperimentUpdated");
            if (this.f3606f == null || this.f3605e < interfaceVersion) {
                gb.c.i(this.f3602a);
                gb.d.f3839a.a(aVar, "IndividualBrt-MainController", "Can't get callback!", null);
                gb.c.c.a(aVar, "IndividualBrt-MainController", "Can't get callback!", null);
            } else {
                int i12 = Settings.Secure.getInt(this.f3602a.getContentResolver(), "custom_brightness_mode", 0);
                if (i12 != 0) {
                    i11 = i12;
                }
                this.f3606f.onAbTestExperimentUpdated(i10, i11);
            }
        } catch (RemoteException e10) {
            gb.c.i(this.f3602a);
            gb.d.f3839a.a(aVar, "IndividualBrt-MainController", "Notify ABTest error!", e10);
            gb.c.c.a(aVar, "IndividualBrt-MainController", "Notify ABTest error!", e10);
        }
    }

    public final void f(IndividualModelEvent individualModelEvent) {
        if (this.f3605e >= 2) {
            h3.a aVarA = a(individualModelEvent);
            BrightnessModelDatabase.p(this.f3602a).q().b(aVarA);
            gb.c.i(this.f3602a);
            String str = "onEventChanged: " + aVarA;
            t9.a aVar = t9.a.INFO;
            gb.d.f3839a.a(aVar, "IndividualBrt-MainController", str, null);
            gb.c.c.a(aVar, "IndividualBrt-MainController", str, null);
        }
    }
}
