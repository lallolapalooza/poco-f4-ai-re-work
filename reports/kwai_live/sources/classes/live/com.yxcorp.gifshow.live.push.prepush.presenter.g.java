package com.yxcorp.gifshow.live.push.prepush.presenter;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.b0;
import androidx.lifecycle.t;
import com.kwai.product.filter_gesture.view.FilterTextSwitcherView;
import com.kwai.video.westeros.models.EffectType;
import com.yxcorp.gifshow.camerasdk.widget.CameraView;
import com.yxcorp.gifshow.entity.s;
import com.yxcorp.gifshow.product.FilterDelegate;
import com.yxcorp.gifshow.product.FilterFragment;
import com.yxcorp.gifshow.product.downloader.FilterManager;
import com.yxcorp.gifshow.record.presenter.exp.k1;
import com.yxcorp.utility.t1;
import com.yxcorp.utility.w1;
import java.io.File;
import kotlin.jvm.internal.w;
import kuaishou.perf.util.reflect.ReflectCommon;
import m10.m;

/* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
/* JADX INFO: loaded from: classes8.dex */
public final class g extends com.yxcorp.gifshow.live.presenter.slide.a implements zf0.b {
    public pv.b B;
    private FilterDelegate C;
    private e D = new e();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private s f45750k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private View f45751l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private View f45752m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private View f45753n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private FilterTextSwitcherView f45754o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private FilterFragment.FilterPanelListener f45755p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private jx0.b f45756q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public com.yxcorp.gifshow.product.f f45757r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public com.yxcorp.gifshow.camerasdk.f f45758t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k1 f45759u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public FragmentActivity f45760w;

    /* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
    public static final class a implements pv.f {
        a() {
        }

        @Override // pv.f
        public void a(s nextFilter, float f12) {
            String absolutePath;
            w.f(nextFilter, "nextFilter");
            if (nextFilter.mId < 0) {
                absolutePath = "";
            } else {
                File fileF = FilterManager.j().f(nextFilter);
                w.e(fileF, "FilterManager.getInstanc….getCacheFile(nextFilter)");
                absolutePath = fileF.getAbsolutePath();
            }
            String lookupPath = absolutePath;
            k80.c cVarF = g.this.R0().f();
            int imageType = nextFilter.getImageType();
            int i12 = nextFilter.mSourceType;
            w.e(lookupPath, "lookupPath");
            cVarF.b(imageType, i12, lookupPath, nextFilter.mDimension, nextFilter.mIntensity, f12);
        }

        @Override // pv.f
        public void b(s filter, boolean z12) {
            w.f(filter, "filter");
            g.this.R0().f().e(z12);
            if (!z12 || g.this.f45756q == null) {
                return;
            }
            jx0.b bVar = g.this.f45756q;
            w.d(bVar);
            bVar.j().setValue(filter);
        }

        @Override // pv.f
        public void c(s nextFilterConfig, float f12) {
            w.f(nextFilterConfig, "nextFilterConfig");
            g.this.R0().f().F(f12);
        }

        @Override // pv.f
        public s d() {
            s value;
            if (g.this.f45756q == null) {
                value = null;
            } else {
                jx0.b bVar = g.this.f45756q;
                w.d(bVar);
                androidx.lifecycle.s<s> sVarJ = bVar.j();
                w.e(sVarJ, "mFilterViewModel!!.filterLiveData");
                value = sVarJ.getValue();
            }
            return value != null ? value : s.getEmpty();
        }

        @Override // pv.f
        public s e(boolean z12) {
            if (g.this.f45756q == null) {
                return null;
            }
            jx0.b bVar = g.this.f45756q;
            w.d(bVar);
            return bVar.n(z12);
        }
    }

    /* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
    static final class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ s f45763b;

        b(s sVar) {
            this.f45763b = sVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            FilterTextSwitcherView filterTextSwitcherView = g.this.f45754o;
            w.d(filterTextSwitcherView);
            s sVar = this.f45763b;
            w.d(sVar);
            filterTextSwitcherView.setCurText(sVar.getDisplayName());
            if (g.this.S0() != null) {
                g.this.S0().j();
                g.this.S0().d();
            }
        }
    }

    /* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
    static final class c<T> implements t<s> {
        c() {
        }

        @Override // androidx.lifecycle.t
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void j(s sVar) {
            boolean z12 = false;
            if (sVar == null || sVar.isEmptyFilter()) {
                g.this.R0().f().c();
                View view = g.this.f45752m;
                w.d(view);
                view.setSelected(false);
                return;
            }
            if (FilterManager.j().k(sVar)) {
                g.this.f1(sVar);
                View view2 = g.this.f45752m;
                w.d(view2);
                View view3 = g.this.f45752m;
                w.d(view3);
                if (view3.isEnabled() && !sVar.isEmptyFilter()) {
                    z12 = true;
                }
                view2.setSelected(z12);
            }
        }
    }

    /* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
    static final class d<T> implements t<Float> {
        d() {
        }

        @Override // androidx.lifecycle.t
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void j(Float f12) {
            if (f12 != null) {
                g.this.R0().f().A(f12.floatValue());
            }
        }
    }

    /* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
    public static final class e implements CameraView.CameraClickListener {
        e() {
        }

        @Override // com.yxcorp.gifshow.camerasdk.widget.CameraView.CameraClickListener
        public boolean onClick() {
            FilterDelegate filterDelegate = g.this.C;
            return filterDelegate != null && filterDelegate.c();
        }
    }

    /* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
    static final class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            g.this.d1();
        }
    }

    /* JADX INFO: renamed from: com.yxcorp.gifshow.live.push.prepush.presenter.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LivePrePushFilterPresenter.kt */
    static final class RunnableC0738g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final RunnableC0738g f45768a = new RunnableC0738g();

        RunnableC0738g() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            sw0.a.a();
        }
    }

    private final void U0() {
        jx0.b bVar;
        pv.b bVar2 = this.B;
        if (bVar2 == null) {
            w.u("mFilterController");
        }
        bVar2.h(new a());
        FragmentActivity fragmentActivity = this.f45760w;
        if (fragmentActivity == null) {
            w.u("mActivity");
        }
        if (!fragmentActivity.getIntent().getBooleanExtra("show_filter_name", false) || (bVar = this.f45756q) == null) {
            return;
        }
        w.d(bVar);
        androidx.lifecycle.s<s> sVarJ = bVar.j();
        w.e(sVarJ, "mFilterViewModel!!.filterLiveData");
        s value = sVarJ.getValue();
        if (value == null) {
            value = s.getEmpty();
        }
        t1.o(new b(value), 1000L);
    }

    private final void V0() {
        com.yxcorp.gifshow.product.f fVar = this.f45757r;
        if (fVar == null) {
            w.u("mFilterHost");
        }
        Fragment fragmentF0 = fVar.f0();
        w.e(fragmentF0, "mFilterHost.hostFragment");
        jx0.b bVar = (jx0.b) b0.a(fragmentF0).a(jx0.b.class);
        this.f45756q = bVar;
        w.d(bVar);
        bVar.s(1);
        jx0.b bVar2 = this.f45756q;
        w.d(bVar2);
        bVar2.j().observe(fragmentF0.getViewLifecycleOwner(), new c());
        jx0.b bVar3 = this.f45756q;
        w.d(bVar3);
        bVar3.l().observe(fragmentF0.getViewLifecycleOwner(), new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d1() {
        FilterDelegate filterDelegate = this.C;
        if (filterDelegate != null) {
            com.yxcorp.gifshow.product.f fVar = this.f45757r;
            if (fVar == null) {
                w.u("mFilterHost");
            }
            filterDelegate.h(fVar, this.f45752m, this.f45753n, 3, 1, this.f45755p);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void f1(s sVar) {
        if (sVar != null) {
            com.yxcorp.gifshow.camerasdk.f fVar = this.f45758t;
            if (fVar == null) {
                w.u("mCameraSdk");
            }
            k80.c cVarF = fVar.f();
            File fileF = FilterManager.j().f(sVar);
            w.e(fileF, "FilterManager.getInstance().getCacheFile(filter)");
            String absolutePath = fileF.getAbsolutePath();
            w.e(absolutePath, "FilterManager.getInstanc…File(filter).absolutePath");
            cVarF.E(absolutePath, sVar.mSourceType, sVar.getImageType(), sVar.mDimension, sVar.mIntensity);
            if (!sVar.equalsIncludeEmpty(this.f45750k)) {
                FilterTextSwitcherView filterTextSwitcherView = this.f45754o;
                w.d(filterTextSwitcherView);
                filterTextSwitcherView.setCurText(sVar.getDisplayName());
                pv.b bVar = this.B;
                if (bVar == null) {
                    w.u("mFilterController");
                }
                bVar.j();
                pv.b bVar2 = this.B;
                if (bVar2 == null) {
                    w.u("mFilterController");
                }
                bVar2.d();
            }
            this.f45750k = sVar;
        }
    }

    @Override // zf0.b
    public /* synthetic */ boolean B(boolean z12) {
        return zf0.a.b(this, z12);
    }

    public final CameraView N0() {
        k1 k1Var = this.f45759u;
        if (k1Var == null) {
            w.u(ReflectCommon.M_CALLBACK);
        }
        CameraView cameraViewG = k1Var.g();
        if (cameraViewG != null) {
            return cameraViewG;
        }
        FragmentActivity fragmentActivity = this.f45760w;
        if (fragmentActivity == null) {
            w.u("mActivity");
        }
        return (CameraView) fragmentActivity.findViewById(m.f79725g2);
    }

    public final s O0() {
        androidx.lifecycle.s<s> sVarJ;
        jx0.b bVar = this.f45756q;
        if (bVar == null || (sVarJ = bVar.j()) == null) {
            return null;
        }
        return sVarJ.getValue();
    }

    public final com.yxcorp.gifshow.camerasdk.f R0() {
        com.yxcorp.gifshow.camerasdk.f fVar = this.f45758t;
        if (fVar == null) {
            w.u("mCameraSdk");
        }
        return fVar;
    }

    public final pv.b S0() {
        pv.b bVar = this.B;
        if (bVar == null) {
            w.u("mFilterController");
        }
        return bVar;
    }

    public final void Z0(FilterFragment.FilterPanelListener listener) {
        w.f(listener, "listener");
        this.f45755p = listener;
    }

    @Override // com.yxcorp.gifshow.live.presenter.slide.a, com.smile.gifmaker.mvps.presenter.d
    public void a0(View view) {
        super.a0(view);
        this.f45751l = w1.e(view, mj0.b.f80657b5);
        this.f45752m = w1.e(view, mj0.b.f80653a4);
        this.f45753n = w1.e(view, mj0.b.f80660c5);
        this.f45754o = (FilterTextSwitcherView) w1.e(view, m.f79803w0);
    }

    @Override // zf0.b
    public boolean b() {
        FilterDelegate filterDelegate = this.C;
        Boolean boolValueOf = filterDelegate != null ? Boolean.valueOf(filterDelegate.c()) : null;
        w.d(boolValueOf);
        if (boolValueOf.booleanValue()) {
            return true;
        }
        return zf0.a.a(this);
    }

    @Override // com.smile.gifmaker.mvps.presenter.d
    protected void onDestroy() {
        super.onDestroy();
        FilterDelegate filterDelegate = this.C;
        if (filterDelegate != null) {
            filterDelegate.g();
        }
        CameraView cameraViewN0 = N0();
        if (cameraViewN0 != null) {
            cameraViewN0.t(this.D);
        }
        this.f45755p = (FilterFragment.FilterPanelListener) null;
    }

    @Override // com.smile.gifmaker.mvps.presenter.d
    protected void t0() {
        super.t0();
        com.yxcorp.gifshow.product.f fVar = this.f45757r;
        if (fVar == null) {
            w.u("mFilterHost");
        }
        this.C = new FilterDelegate(fVar.f0());
        com.yxcorp.gifshow.camerasdk.f fVar2 = this.f45758t;
        if (fVar2 == null) {
            w.u("mCameraSdk");
        }
        fVar2.f().y(EffectType.kEffectTypeLookup, false);
        View view = this.f45752m;
        w.d(view);
        com.yxcorp.gifshow.product.prettify.beauty.g.c(view, true);
        V0();
        U0();
        View view2 = this.f45751l;
        if (view2 != null) {
            view2.setOnClickListener(new f());
        }
        CameraView cameraViewN0 = N0();
        if (cameraViewN0 != null) {
            cameraViewN0.m(this.D);
        }
        View view3 = this.f45752m;
        if (view3 != null) {
            view3.postDelayed(RunnableC0738g.f45768a, 100L);
        }
    }
}
