package f3;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f3593g = SystemProperties.get("ro.product.mod_device", "") + "_individual";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f3594a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f3595b;
    public int c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3596d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y6.a f3597e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d.a f3598f;

    public final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            b bVar = b.this;
            y6.a aVar = bVar.f3597e;
            f3.a aVar2 = new f3.a(bVar);
            Objects.requireNonNull(aVar);
            z6.a.f8984k.d(aVar2);
            Objects.requireNonNull(bVar.f3597e);
            z6.a.f8984k.d(null);
            bVar.f3594a.removeMessages(0);
            bVar.f3594a.sendEmptyMessageDelayed(0, 7200000L);
        }
    }

    public b(Context context, d.a aVar) {
        this.f3595b = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("BrightnessABTest");
        handlerThread.start();
        this.f3594a = new a(handlerThread.getLooper());
        this.f3598f = aVar;
    }
}
