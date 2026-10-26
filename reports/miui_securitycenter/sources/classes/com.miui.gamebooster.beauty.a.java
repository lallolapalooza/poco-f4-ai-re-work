package com.miui.gamebooster.beauty;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.miui.securitycenter.Application;
import com.miui.securitycenter.R;
import f5.f;
import g5.i;
import h7.e2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import z7.u;

/* JADX INFO: loaded from: classes2.dex */
public class a implements BeautyView.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f10260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f10261b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private WindowManager f10262c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WindowManager.LayoutParams f10263d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<View> f10264e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Context f10265f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private LayoutInflater f10266g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private BeautyView f10267h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private View f10268i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private View f10269j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Handler f10270k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Runnable f10271l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private SensorManager f10272m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Sensor f10273n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private d f10274o;

    /* JADX INFO: renamed from: com.miui.gamebooster.beauty.a$a, reason: collision with other inner class name */
    class RunnableC0147a implements Runnable {
        RunnableC0147a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            aVar.v(aVar.f10268i);
        }
    }

    class b implements View.OnClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ k5.c f10276c;

        b(k5.c cVar) {
            this.f10276c = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a aVar = a.this;
            aVar.v(aVar.f10269j);
            k5.c cVar = this.f10276c;
            if (cVar != null) {
                cVar.a(false);
            }
        }
    }

    class c implements View.OnClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ k5.c f10278c;

        c(k5.c cVar) {
            this.f10278c = cVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.o().P0(true, null);
            f.o().O0(true);
            if (a.this.f10267h != null) {
                a.this.f10267h.u();
            }
            k5.c cVar = this.f10278c;
            if (cVar != null) {
                cVar.a(true);
            }
            a aVar = a.this;
            aVar.v(aVar.f10269j);
            a aVar2 = a.this;
            aVar2.y(aVar2.f10265f.getString(R.string.beauty_fun_privacy_tips));
        }
    }

    final class d implements SensorEventListener {
        d() {
        }

        private int a(float f10) {
            return (int) ((((double) f10) * (-1.35d)) + ((double) f.w()));
        }

        private void b() {
            if (a.this.f10272m != null) {
                a.this.f10272m.unregisterListener(a.this.f10274o);
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i10) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            float f10 = sensorEvent.values[0];
            int iA = a(f10);
            if (iA < 1 || iA > f.q()) {
                Log.i("BeautyWindowManager", "light invalid: " + iA);
            } else {
                Log.i("BeautyWindowManager", "lux " + f10 + " light " + iA);
                f.o().L0(f.o().J(), iA, f.p());
            }
            b();
        }
    }

    private static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @SuppressLint({"StaticFieldLeak"})
        static final a f10281a = new a(null);
    }

    private a() {
        this.f10264e = new ArrayList();
        this.f10270k = new Handler(Looper.getMainLooper());
        this.f10271l = new RunnableC0147a();
        Application applicationY = Application.y();
        this.f10265f = applicationY;
        this.f10262c = (WindowManager) applicationY.getSystemService("window");
        this.f10260a = applicationY.getResources();
        p();
    }

    /* synthetic */ a(RunnableC0147a runnableC0147a) {
        this();
    }

    private void j(View view, WindowManager.LayoutParams layoutParams) {
        if (view == null || this.f10264e.contains(view)) {
            return;
        }
        e2.s(view);
        this.f10262c.addView(view, layoutParams);
        this.f10262c.updateViewLayout(view, layoutParams);
        this.f10264e.add(view);
    }

    private WindowManager.LayoutParams l() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.setTitle("BeautyAssistantView");
        u.a(layoutParams);
        int i10 = Build.VERSION.SDK_INT;
        layoutParams.type = i10 >= 26 ? 2038 : 2002;
        layoutParams.format = -3;
        layoutParams.gravity = 8388659;
        layoutParams.flags = 852776;
        if (i10 >= 28) {
            layoutParams.layoutInDisplayCutoutMode = 1;
        }
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.x = this.f10260a.getDimensionPixelOffset(R.dimen.view_dimen_50);
        layoutParams.y = this.f10260a.getDimensionPixelOffset(R.dimen.view_dimen_356);
        return layoutParams;
    }

    private int n() {
        return e2.t(this.f10265f) ? this.f10265f.getResources().getDimensionPixelOffset(R.dimen.view_dimen_80) : e2.g(this.f10265f) + this.f10265f.getResources().getDimensionPixelOffset(R.dimen.view_dimen_80);
    }

    public static a o() {
        return e.f10281a;
    }

    private void q() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f10265f);
        this.f10266g = layoutInflaterFrom;
        BeautyView beautyView = (BeautyView) layoutInflaterFrom.inflate(R.layout.layout_beauty_float, (ViewGroup) null);
        this.f10267h = beautyView;
        beautyView.u();
        this.f10267h.setOnStatusChangeListener(this);
        this.f10267h.n();
    }

    private boolean r(Context context) {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        return uiModeManager != null && uiModeManager.getNightMode() == 2;
    }

    private boolean s() {
        return ((f.F() && f.o().A()) || f.o().B() || (f.Y() && f.o().D()) || (f.W() && f.o().C())) ? false : true;
    }

    private void t() {
        if (f.E()) {
            if (this.f10272m == null) {
                this.f10272m = (SensorManager) this.f10265f.getSystemService("sensor");
            }
            if (this.f10273n == null) {
                this.f10273n = this.f10272m.getDefaultSensor(5);
            }
            if (this.f10274o == null) {
                this.f10274o = new d();
            }
            this.f10272m.registerListener(this.f10274o, this.f10273n, 3);
        }
    }

    private void u() {
        try {
            Iterator<View> it = this.f10264e.iterator();
            while (it.hasNext()) {
                this.f10262c.removeView(it.next());
            }
            this.f10264e.clear();
        } catch (Exception e10) {
            Log.e("BeautyWindowManager", "removeAllAddedView " + e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(View view) {
        if (view == null) {
            return;
        }
        try {
            this.f10262c.removeView(view);
            this.f10264e.remove(view);
        } catch (Exception e10) {
            Log.e("BeautyWindowManager", "removeAllAddedView " + e10);
        }
    }

    private void w(View view) {
        if (this.f10264e.contains(view)) {
            this.f10262c.updateViewLayout(view, this.f10263d);
        } else {
            j(view, this.f10263d);
        }
    }

    @Override // com.miui.gamebooster.beauty.BeautyView.c
    public void a() {
        if (f.o().J()) {
            f5.b.d().i();
            i.G().l0();
        } else {
            f5.b.d().c();
            i.G().B0();
        }
    }

    @Override // com.miui.gamebooster.beauty.BeautyView.c
    public void b(f5.a aVar) {
        Context context;
        int i10;
        if (f.X(aVar)) {
            if (f.o().Q(aVar)) {
                context = this.f10265f;
                i10 = R.string.beauty_fun_privacy_tips;
            } else {
                context = this.f10265f;
                i10 = R.string.beauty_fun_privacy_open_tips;
            }
            y(context.getString(i10));
        }
    }

    public void k(String str, String str2, boolean z10) {
        f.o().n0(str, str2);
        if (!f.H() || this.f10261b || s()) {
            return;
        }
        t();
        q();
        Log.i("BeautyWindowManager", "create: ");
        w(this.f10267h);
        this.f10261b = true;
        i.G().C(this.f10265f, z10);
    }

    public void m(boolean z10) {
        i.G().B(this.f10265f, z10);
        u();
        this.f10261b = false;
    }

    public void p() {
        this.f10263d = l();
    }

    public void x(k5.c cVar) {
        View viewInflate = View.inflate(this.f10265f, R.layout.gb_privacy_camera_dialog, null);
        this.f10269j = viewInflate;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) viewInflate.findViewById(R.id.lav_settings_privacy);
        if (f.Y()) {
            lottieAnimationView.setImageAssetsFolder("privacy_camera");
            lottieAnimationView.setAnimation(r(this.f10265f) ? "privacy_camera/privacycamera_dark.json" : "privacy_camera/privacycamera_light.json");
        }
        LinearLayout linearLayout = (LinearLayout) this.f10269j.findViewById(R.id.layout_button);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, n());
        linearLayout.setLayoutParams(layoutParams);
        Button button = (Button) this.f10269j.findViewById(R.id.btn_cancel);
        Button button2 = (Button) this.f10269j.findViewById(R.id.btn_confirm);
        button.setOnClickListener(new b(cVar));
        button2.setOnClickListener(new c(cVar));
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
        layoutParams2.copyFrom(this.f10263d);
        layoutParams2.width = -1;
        layoutParams2.height = -1;
        layoutParams2.x = 0;
        layoutParams2.y = 0;
        j(this.f10269j, layoutParams2);
    }

    public void y(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.f10268i == null) {
            this.f10268i = View.inflate(this.f10265f, R.layout.gb_privacy_tips, null);
        }
        ((TextView) this.f10268i.findViewById(R.id.tv_tips)).setText(str);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.f10263d);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 49;
        layoutParams.x = 0;
        layoutParams.y = this.f10265f.getResources().getDimensionPixelOffset(R.dimen.view_dimen_156);
        j(this.f10268i, layoutParams);
        this.f10270k.postDelayed(this.f10271l, 4000L);
    }
}
