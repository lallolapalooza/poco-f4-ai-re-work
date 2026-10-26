package com.miui.gamebooster.beauty;

import android.app.ActivityManager;
import android.app.Service;
import android.app.TaskStackListener;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.exoplayer2.ExoPlayer;
import com.miui.gamebooster.beauty.BeautyService;
import com.miui.gamebooster.service.DockWindowManagerService;
import com.miui.gamebooster.service.IGameBoosterWindow;
import com.miui.networkassistant.config.Constants;
import dd.z;
import f4.i1;
import f4.n1;
import f4.s1;
import h7.o1;
import h7.p1;
import h7.x;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.app.MiuiFreeFormManager;
import miui.process.ForegroundInfo;
import miui.process.IActivityChangeListener;
import miui.process.ProcessManager;

/* JADX INFO: loaded from: classes2.dex */
public class BeautyService extends Service {
    private static final ArrayList<String> G;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f10174e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f10175f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f10176g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Handler f10177h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f10178i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private HandlerThread f10179j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private CameraManager f10181l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f10182m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public IGameBoosterWindow f10184o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f10185p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private ActivityManager f10187r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private volatile String f10189t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private volatile String f10190u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private volatile boolean f10191v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private volatile boolean f10192w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f10193x;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f10172c = Boolean.FALSE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f10173d = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final Object f10180k = new Object();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final i f10183n = new i(this, null);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f10186q = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private volatile j f10188s = j.NON_CONVERSATION_MODE;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private BroadcastReceiver f10194y = new a();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private CameraManager.AvailabilityCallback f10195z = new b();
    private BroadcastReceiver A = new c();
    private com.miui.gamebooster.mutiwindow.b.InterfaceC0153b B = new d();
    private IActivityChangeListener.Stub C = new e();
    private final Runnable D = new f();
    private TaskStackListener E = new g();
    private final IBinder.DeathRecipient F = new h();

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction()) || !TextUtils.equals(intent.getAction(), "beauty_action_monitor_activity")) {
                return;
            }
            BeautyService.this.v0();
        }
    }

    class b extends CameraManager.AvailabilityCallback {
        b() {
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(String str) {
            super.onCameraAvailable(str);
            Log.i("BeautyService", "onCameraAvailable:" + str);
            if (TextUtils.equals("1", str)) {
                BeautyService.this.f10173d = false;
                BeautyService.this.I();
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(String str) {
            super.onCameraUnavailable(str);
            Log.i("BeautyService", "onCameraUnavailable:" + str);
            if (TextUtils.equals("1", str)) {
                BeautyService.this.f10173d = true;
                if (f5.f.o().c(true, BeautyService.this.f10174e, BeautyService.this.f10175f)) {
                    BeautyService.this.l0();
                }
            }
        }
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("BeautyService", "ac:" + action);
            if (Constants.System.ACTION_USER_PRESENT.equals(action) || Constants.System.ACTION_SCREEN_ON.equals(action)) {
                BeautyService.this.b0();
                BeautyService.this.J();
            } else if (Constants.System.ACTION_SCREEN_OFF.equals(action)) {
                if (BeautyService.this.f10185p) {
                    BeautyService.this.M();
                    BeautyService.this.j0();
                } else {
                    if (f5.f.Y() && !f5.f.o().c(BeautyService.this.f10173d, BeautyService.this.f10174e, BeautyService.this.f10175f)) {
                        f5.f.Q0(false);
                    }
                    BeautyService.this.I();
                }
            }
        }
    }

    class d implements com.miui.gamebooster.mutiwindow.b.InterfaceC0153b {
        d() {
        }

        @Override // com.miui.gamebooster.mutiwindow.b.InterfaceC0153b
        public n6.e getId() {
            return n6.e.CONVERSATION;
        }

        @Override // com.miui.gamebooster.mutiwindow.b.InterfaceC0153b
        public boolean onForegroundInfoChanged(ForegroundInfo foregroundInfo) {
            int iM = s1.m(foregroundInfo.mForegroundUid);
            int iX = s1.x();
            if ((iM == 999 && iX == 10) || (iM != 999 && iX != iM)) {
                return false;
            }
            Log.i("BeautyService", "onForegroundInfoChanged: Cur=" + foregroundInfo.mForegroundPackageName + "\t last=" + foregroundInfo.mLastForegroundPackageName);
            if (BeautyService.G.contains(foregroundInfo.mForegroundPackageName)) {
                return false;
            }
            synchronized (BeautyService.this.f10180k) {
                f5.f.y0(foregroundInfo.mForegroundPackageName);
            }
            return false;
        }
    }

    class e extends IActivityChangeListener.Stub {
        e() {
        }

        @Override // miui.process.IActivityChangeListener
        public void onActivityChanged(ComponentName componentName, ComponentName componentName2) {
            if (componentName == null || componentName2 == null) {
                return;
            }
            Log.i("BeautyService", "notifyChange! preName = " + componentName.toString() + "; curName = " + componentName2.toString());
            synchronized (BeautyService.this.f10180k) {
                BeautyService.this.f10174e = componentName2.getPackageName();
                BeautyService.this.f10175f = componentName2.getClassName();
                BeautyService.this.b0();
                BeautyService.this.a0();
                BeautyService.this.H(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
            }
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i("BeautyService", "task Running...");
            try {
                synchronized (BeautyService.this.f10180k) {
                    BeautyService.this.f10178i = false;
                    BeautyService.this.f10192w = o1.a();
                    if (BeautyService.this.f10192w) {
                        BeautyService.this.h0();
                    } else {
                        BeautyService.this.r0();
                    }
                    BeautyService.this.u0();
                    BeautyService.this.a0();
                }
            } catch (Exception e10) {
                Log.e("BeautyService", "onTaskChange fail " + e10);
            }
        }
    }

    class g extends TaskStackListener {
        g() {
        }

        @Override // android.app.TaskStackListener
        public void onTaskStackChanged() {
            Log.i("BeautyService", "onTaskStackChanged");
            BeautyService.this.H(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        }
    }

    class h implements IBinder.DeathRecipient {
        h() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.i("BeautyService", "linkToDeath: " + BeautyService.this.f10184o);
            BeautyService.this.i0();
            BeautyService beautyService = BeautyService.this;
            beautyService.f10184o = null;
            beautyService.f10182m = false;
            BeautyService.this.J();
        }
    }

    private class i implements ServiceConnection {
        private i() {
        }

        /* synthetic */ i(BeautyService beautyService, a aVar) {
            this();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BeautyService.this.f10184o = IGameBoosterWindow.Stub.A0(iBinder);
            try {
                Log.i("BeautyService", "onServiceConnected: mGameWindowBinder = " + BeautyService.this.f10184o);
                BeautyService.this.J();
                iBinder.linkToDeath(BeautyService.this.F, 0);
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            BeautyService beautyService = BeautyService.this;
            beautyService.f10184o = null;
            beautyService.f10182m = false;
        }
    }

    private enum j {
        CONVERSATION_MODE,
        NON_CONVERSATION_MODE
    }

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        G = arrayList;
        arrayList.add("com.miui.screenrecorder");
        arrayList.add("com.lbe.security.miui");
        arrayList.add("com.milink.service");
        arrayList.add("com.xiaomi.miplay_client");
        arrayList.add("com.xiaomi.misubscreenui");
        arrayList.add("com.xiaomi.aiasst.vision");
        arrayList.add("com.miui.vpnsdkmanager");
        arrayList.add("com.xiaomi.migameservice");
        arrayList.add("com.xiaomi.gamecenter.sdk.service");
        arrayList.add("com.google.android.permissioncontroller");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(long j10) {
        synchronized (this.f10180k) {
            if (this.f10178i) {
                this.f10177h.removeCallbacks(this.D);
            }
            this.f10177h.postDelayed(this.D, j10);
            this.f10178i = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        if (this.f10185p) {
            Log.i("BeautyService", "dismissBeautyView: need process by conversation");
        } else {
            this.f10176g.post(new Runnable() { // from class: f5.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f45868c.T();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        z.c().b(new Runnable() { // from class: f5.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f45867c.U();
            }
        });
    }

    private void K(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("BeautyService start");
        printWriter.println("isFrontCameraOpen: " + this.f10173d);
        printWriter.println("currentPkg: " + this.f10174e + " currentCls: " + this.f10175f);
        printWriter.println();
        printWriter.println("BeautyService service end");
    }

    private void L() {
        IGameBoosterWindow iGameBoosterWindow = this.f10184o;
        if (iGameBoosterWindow == null || !iGameBoosterWindow.asBinder().isBinderAlive()) {
            return;
        }
        if (p1.d(getApplicationContext())) {
            Log.i("BeautyService", "screen Locked! won't enter Conversation Mode! ");
            return;
        }
        this.f10184o.q2(5, false, this.f10174e, this.f10175f, 0);
        this.f10188s = j.CONVERSATION_MODE;
        this.f10190u = this.f10175f;
        this.f10189t = this.f10174e;
        Log.i("BeautyService", "enterDockMode pkgName = " + this.f10174e + ", clsName = " + this.f10175f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        try {
            IGameBoosterWindow iGameBoosterWindow = this.f10184o;
            if (iGameBoosterWindow == null || !iGameBoosterWindow.asBinder().isBinderAlive()) {
                return;
            }
            this.f10184o.s2(5);
            this.f10188s = j.NON_CONVERSATION_MODE;
            this.f10190u = null;
            this.f10189t = null;
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    private List<ActivityManager.RunningTaskInfo> N(int i10, boolean z10) {
        try {
            if (this.f10187r == null) {
                this.f10187r = (ActivityManager) getSystemService("activity");
            }
            if (!z10) {
                return this.f10187r.getRunningTasks(i10);
            }
            ArrayList arrayList = new ArrayList();
            List<MiuiFreeFormManager.MiuiFreeFormStackInfo> allFreeFormStackInfosOnDisplay = MiuiFreeFormManager.getAllFreeFormStackInfosOnDisplay(n1.a());
            if (allFreeFormStackInfosOnDisplay == null) {
                allFreeFormStackInfosOnDisplay = new ArrayList();
            }
            ve.a.a("BeautyService", "freeformWindow size = " + allFreeFormStackInfosOnDisplay.size());
            HashSet hashSet = new HashSet();
            for (MiuiFreeFormManager.MiuiFreeFormStackInfo miuiFreeFormStackInfo : allFreeFormStackInfosOnDisplay) {
                ve.a.a("BeautyService", "freeformWindow  pkgname = " + miuiFreeFormStackInfo.packageName + ", stackid = " + miuiFreeFormStackInfo.stackId);
                hashSet.add(Integer.valueOf(miuiFreeFormStackInfo.stackId));
            }
            for (ActivityManager.RunningTaskInfo runningTaskInfo : this.f10187r.getRunningTasks(Math.min(this.f10193x + i10, allFreeFormStackInfosOnDisplay.size() + i10))) {
                ve.a.a("BeautyService", "runningTask Id = " + runningTaskInfo.id + ", pkgName = " + runningTaskInfo.topActivity.getPackageName());
                if (!hashSet.contains(Integer.valueOf(runningTaskInfo.id)) && arrayList.size() < i10) {
                    arrayList.add(runningTaskInfo);
                }
            }
            return arrayList;
        } catch (Exception e10) {
            Log.e("BeautyService", "getRunningTasks fail " + e10);
            return null;
        }
    }

    private void O() {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ComponentName componentNameS;
        if (Build.VERSION.SDK_INT <= 31 ? !((runningTasks = ((ActivityManager) getSystemService("activity")).getRunningTasks(1)) == null || runningTasks.size() <= 0 || (componentNameS = runningTasks.get(0).topActivity) == null) : (componentNameS = x.s(x.M())) != null) {
            this.f10174e = componentNameS.getPackageName();
            this.f10175f = componentNameS.getClassName();
        }
        Log.i("BeautyService", "initCurrentPage: " + this.f10174e + " " + this.f10175f);
    }

    private boolean P(boolean z10, j jVar) {
        return (z10 && jVar == j.CONVERSATION_MODE && Q(this.f10174e, this.f10175f, this.f10189t, this.f10190u)) || (!z10 && this.f10188s == j.NON_CONVERSATION_MODE);
    }

    private boolean Q(String str, String str2, String str3, String str4) {
        return TextUtils.equals(str, str3) && TextUtils.equals(str2, str4);
    }

    private boolean R(boolean z10, boolean z11) {
        if (p1.d(getApplicationContext())) {
            return false;
        }
        boolean zA = o1.a();
        Log.i("BeautyService", "inSplitScreenMode = " + zA + ", isFrontCameraOpen = " + z10 + ", mCurPkgName = " + this.f10174e + ", mCurClsName = " + this.f10175f);
        return (this.f10186q && zA) ? S(z11) : f5.f.o().c(z10, this.f10174e, this.f10175f);
    }

    private boolean S(boolean z10) {
        try {
            List<ActivityManager.RunningTaskInfo> listN = N(2, true);
            if (k6.c.p(listN)) {
                return false;
            }
            ComponentName componentName = null;
            Iterator<ActivityManager.RunningTaskInfo> it = listN.iterator();
            boolean z11 = false;
            boolean z12 = false;
            while (it.hasNext()) {
                ComponentName componentName2 = it.next().topActivity;
                boolean zD = f5.f.o().d(this.f10173d, componentName2.getPackageName(), componentName2.getClassName(), true);
                Log.i("BeautyService", "topActivity = " + componentName2 + ", canUseBeauty = " + zD);
                if (zD) {
                    z11 = true;
                    componentName = componentName2;
                } else if (f5.f.o().I0(componentName2.getPackageName(), componentName2.getClassName())) {
                    z12 = true;
                }
            }
            Log.i("BeautyService", "sceneOneSupport = " + z11 + ", sceneSecondSupport = " + z12);
            if (z11 && z12) {
                ve.a.a("BeautyService", "clsNameAtConversationMode = " + this.f10190u + ", pkgNameAtConversationMode = " + this.f10189t + ", mCurPkgName = " + this.f10174e + ", mCurClsName = " + this.f10175f);
                if (z10) {
                    this.f10174e = componentName.getPackageName();
                    this.f10175f = componentName.getClassName();
                }
                return true;
            }
        } catch (Exception e10) {
            Log.i("BeautyService", "isSplitSceneSupport fail " + e10);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T() {
        com.miui.gamebooster.beauty.a.o().m(this.f10173d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void U() {
        synchronized (this.f10180k) {
            a0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void V() {
        CameraManager cameraManager = (CameraManager) getSystemService("camera");
        this.f10181l = cameraManager;
        cameraManager.registerAvailabilityCallback(this.f10195z, this.f10176g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void W() {
        boolean zF0 = f5.f.f0();
        Log.i("BeautyService", "setFrontLight: " + zF0);
        f5.f.o().G0(zF0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X() {
        com.miui.gamebooster.beauty.a.o().k(this.f10174e, this.f10175f, this.f10173d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y() {
        CameraManager cameraManager = this.f10181l;
        if (cameraManager == null) {
            return;
        }
        cameraManager.unregisterAvailabilityCallback(this.f10195z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z() {
        f5.f.y();
        o0();
        c0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public void a0() {
        String str;
        IGameBoosterWindow iGameBoosterWindow;
        if (this.f10185p && g5.i.P()) {
            boolean zR = R(this.f10173d, true);
            Log.i("BeautyService", "onDockSwitchChange : \tcurrentUser = " + s1.p() + "\t isBound = " + this.f10182m + ", curState = " + this.f10188s + ", isSceneSupport = " + zR + ", isAllowSplit = " + this.f10186q);
            if (!P(zR, this.f10188s)) {
                boolean z10 = false;
                if (!s1.p() || !zR) {
                    if (this.f10182m) {
                        Log.i("BeautyService", "onDockSwitchChange: unbind to ui");
                        M();
                        try {
                            unbindService(this.f10183n);
                        } catch (Exception unused) {
                        }
                        this.f10182m = false;
                        this.f10184o = null;
                        return;
                    }
                    return;
                }
                Log.i("BeautyService", "onDockSwitchChange: mGameWindowBinder = " + this.f10184o);
                if (this.f10182m && (iGameBoosterWindow = this.f10184o) != null && iGameBoosterWindow.asBinder().isBinderAlive()) {
                    try {
                        L();
                        z10 = true;
                    } catch (Exception e10) {
                        e10.printStackTrace();
                        this.f10182m = false;
                        this.f10184o = null;
                    }
                }
                if (z10) {
                    return;
                }
                Log.i("BeautyService", "onDockSwitchChange: bind to ui");
                Intent intent = new Intent(this, (Class<?>) DockWindowManagerService.class);
                intent.setAction("com.miui.gamebooster.service.GameBoxService");
                this.f10182m = bindService(intent, this.f10183n, 1);
                return;
            }
            str = "already at target mode!";
        } else {
            str = "onDockSwitchChange: need process by beauty";
        }
        Log.i("BeautyService", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b0() {
        if (this.f10185p) {
            return;
        }
        if (!f5.f.o().c(this.f10173d, this.f10174e, this.f10175f)) {
            I();
            Log.i("BeautyService", "beaty service exit");
        } else {
            Log.d("BeautyService", "beaty service start:");
            if (f5.f.Y()) {
                f5.f.Q0(true);
            }
            l0();
        }
    }

    private void c0() {
        try {
            List<String> listR = f5.f.r(true);
            List<String> listR2 = f5.f.r(false);
            Log.i("BeautyService", "registerActivityChangeListener: " + listR + " " + listR2);
            if (!k6.c.p(listR) && !k6.c.p(listR2)) {
                ve.e.f("BeautyService", ProcessManager.class, "registerActivityChangeListener", new Class[]{List.class, List.class, Class.forName(IActivityChangeListener.DESCRIPTOR)}, listR, listR2, this.C);
                this.f10172c = Boolean.TRUE;
                return;
            }
            Log.i("BeautyService", "registerActivityChangeListener: invalid list");
        } catch (Exception e10) {
            Log.e("BeautyService", "registerActivityChangeListener exception!", e10);
        }
    }

    private void d0() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.System.ACTION_USER_PRESENT);
        intentFilter.addAction(Constants.System.ACTION_SCREEN_ON);
        intentFilter.addAction(Constants.System.ACTION_SCREEN_OFF);
        registerReceiver(this.A, intentFilter);
    }

    private void e0() {
        com.miui.common.base.asyn.a.a(new Runnable() { // from class: f5.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f45866c.V();
            }
        });
    }

    private void f0() {
        try {
            com.miui.gamebooster.mutiwindow.b.c().b(this.B);
            Log.i("BeautyService", "registerForegroundInfoListener");
        } catch (Exception e10) {
            Log.e("BeautyService", e10.toString());
        }
    }

    private void g0() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("beauty_action_monitor_activity");
            l0.a.b(this).c(this.f10194y, intentFilter);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h0() {
        if (this.f10191v) {
            return;
        }
        try {
            ve.f.e(ve.f.h(Class.forName("android.app.ActivityTaskManager"), "getService", null, new Object[0]), null, "registerTaskStackListener", new Class[]{Class.forName("android.app.ITaskStackListener")}, this.E);
            this.f10191v = true;
            Log.i("BeautyService", "registerTaskChangeListener");
        } catch (Exception e10) {
            Log.e("BeautyService", "registerTaskListener fail " + e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i0() {
        IGameBoosterWindow iGameBoosterWindow = this.f10184o;
        if (iGameBoosterWindow == null) {
            return;
        }
        try {
            iGameBoosterWindow.asBinder().unlinkToDeath(this.F, 0);
            unbindService(this.f10183n);
        } catch (Exception unused) {
        }
    }

    private static void k0() {
        z.c().b(new Runnable() { // from class: f5.m
            @Override // java.lang.Runnable
            public final void run() {
                BeautyService.W();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l0() {
        if (this.f10185p) {
            Log.i("BeautyService", "showBeautyView: need process by conversation");
        } else {
            this.f10176g.post(new Runnable() { // from class: f5.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f45863c.X();
                }
            });
        }
    }

    public static void m0(Context context) {
        Log.i("BeautyService", "start beauty monitor:" + f5.f.H() + " " + f5.f.o().d0() + " conversation:" + g5.i.a0() + " " + g5.i.P());
        k0();
        if ((f5.f.o().d0() && f5.f.H()) || (g5.i.a0() && g5.i.P())) {
            try {
                context.startService(new Intent(context, (Class<?>) BeautyService.class));
            } catch (Exception e10) {
                Log.e("BeautyService", "startProcessMonitorService: " + e10.toString());
            }
        }
    }

    public static void n0(Context context) {
        try {
            Log.i("BeautyService", "stopPageMonitorService");
            context.stopService(new Intent(context, (Class<?>) BeautyService.class));
        } catch (Exception e10) {
            Log.e("BeautyService", "stopPageMonitorService: " + e10.toString());
        }
    }

    private void o0() {
        if (this.f10172c.booleanValue()) {
            try {
                ve.e.f("BeautyService", ProcessManager.class, "unregisterActivityChanageListener", new Class[]{Class.forName(IActivityChangeListener.DESCRIPTOR)}, this.C);
                this.f10172c = Boolean.FALSE;
            } catch (Exception e10) {
                Log.e("BeautyService", "unregisterActivityChanageListener exception!", e10);
            }
        }
    }

    private void p0() {
        com.miui.common.base.asyn.a.a(new Runnable() { // from class: f5.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f45864c.Y();
            }
        });
    }

    private void q0() {
        com.miui.gamebooster.mutiwindow.b.c().f(this.B);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r0() {
        if (this.f10191v) {
            try {
                ve.f.e(ve.f.h(Class.forName("android.app.ActivityTaskManager"), "getService", null, new Object[0]), null, "unregisterTaskStackListener", new Class[]{Class.forName("android.app.ITaskStackListener")}, this.E);
                this.f10191v = false;
                Log.i("BeautyService", "unRegisterTaskChangeList");
            } catch (Exception e10) {
                Log.e("BeautyService", "unregisterTaskChangeListener exception: " + e10);
            }
        }
    }

    private void s0() {
        try {
            l0.a.b(this).e(this.f10194y);
        } catch (Exception unused) {
        }
    }

    private void t0() {
        try {
            unregisterReceiver(this.A);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u0() {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        ComponentName componentName;
        List<ActivityManager.RunningTaskInfo> listN = N(1, true);
        if (listN == null || k6.c.p(listN) || (runningTaskInfo = listN.get(0)) == null || (componentName = runningTaskInfo.topActivity) == null) {
            return;
        }
        ve.a.a("BeautyService", "updateForegroundInfo new = " + componentName + ", old Pkg = " + this.f10174e + ", old ClsName = " + this.f10175f);
        this.f10174e = componentName.getPackageName();
        this.f10175f = componentName.getClassName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v0() {
        z.c().b(new Runnable() { // from class: f5.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f45865c.Z();
            }
        });
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("=== BeautyService info ===");
        K(fileDescriptor, printWriter, strArr);
        printWriter.println();
    }

    public void j0() {
        try {
            if (this.f10182m) {
                this.f10182m = false;
                this.f10184o = null;
                unbindService(this.f10183n);
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f10176g = new Handler(getMainLooper());
        HandlerThread handlerThread = new HandlerThread("beauty_bg_service");
        this.f10179j = handlerThread;
        handlerThread.start();
        this.f10177h = new Handler(this.f10179j.getLooper());
        this.f10185p = g5.i.a0();
        O();
        f5.f.o().e();
        f5.f.E0();
        f0();
        c0();
        g0();
        e0();
        d0();
        this.f10186q = g5.i.S();
        this.f10192w = o1.a();
        this.f10193x = i1.a(this) ? 4 : 2;
        Log.i("BeautyService", "onCreate: Service{ BeautyService , " + hashCode() + "}");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        j0();
        I();
        q0();
        o0();
        s0();
        p0();
        t0();
        f5.f.j0();
        r0();
    }
}
