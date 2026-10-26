package com.yxcorp.gifshow.live.push.prepush.presenter;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.k;
import com.yxcorp.gifshow.api.magicemoji.MagicEmojiPlugin;
import com.yxcorp.gifshow.product.prettify.PrettifyFragment;
import com.yxcorp.gifshow.product.prettify.beauty.BeautyChangeEvent;
import com.yxcorp.gifshow.util.n6;
import com.yxcorp.utility.plugin.PluginManager;
import com.yxcorp.utility.w1;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
import m10.m;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes8.dex */
public class LivePrePushPrettifyPresenter extends com.smile.gifmaker.mvps.presenter.d implements k {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final boolean f45725j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public com.yxcorp.gifshow.camerasdk.f f45726k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private k80.c f45727l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private FragmentActivity f45728m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private PrettifyFragment f45729n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private View f45730o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private View f45731p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private View f45732q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private PrettifyFragment.OnPrettifyPanelListener f45733r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private com.yxcorp.gifshow.product.prettify.beauty.a f45734t;

    public LivePrePushPrettifyPresenter(Fragment fragment) {
        this.f45728m = fragment.getActivity();
        MagicEmojiPlugin magicEmojiPlugin = (MagicEmojiPlugin) PluginManager.get(MagicEmojiPlugin.class);
        boolean z12 = !magicEmojiPlugin.isBlock() && magicEmojiPlugin.isAvailable();
        this.f45725j = z12;
        if (!z12) {
            d20.i.K5(true);
            n6.l3(false);
        }
        this.f45734t = com.yxcorp.gifshow.product.prettify.beauty.c.q();
        fragment.getLifecycle().a(this);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(" BEAUTIF_AVAILABLE=");
        sb2.append(z12);
    }

    private void G0() {
        PrettifyFragment prettifyFragment = this.f45729n;
        if (prettifyFragment == null) {
            return;
        }
        prettifyFragment.T1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I0(Object obj) throws Exception {
        J0();
    }

    public boolean J0() {
        if (!this.f45731p.isEnabled()) {
            return false;
        }
        jv0.c.f("BEAUTIFY_PANEL_REAL_SHOW", "LIVE_PREVIEW");
        this.f45732q.setVisibility(0);
        if (this.f45729n == null) {
            this.f45729n = new PrettifyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("beauty_source", 1);
            this.f45729n.setArguments(bundle);
            this.f45728m.getSupportFragmentManager().a().u(m10.d.f79541k, m10.d.f79545o).t(m.f79747l, this.f45729n, "prettify").j();
        } else {
            this.f45728m.getSupportFragmentManager().a().u(m10.d.f79541k, m10.d.f79545o).z(this.f45729n).j();
        }
        this.f45729n.Q2(this.f45733r);
        this.f45729n.P2(this.f45727l);
        return true;
    }

    public void L0(PrettifyFragment.OnPrettifyPanelListener onPrettifyPanelListener) {
        this.f45733r = onPrettifyPanelListener;
    }

    void M0(boolean z12) {
        if (this.f45725j) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("updateBeautifyState: use advance beauty mBeautifyConfig=");
            sb2.append(this.f45734t);
            if (this.f45727l != null) {
                com.yxcorp.gifshow.product.prettify.beauty.c.w(this.f45727l, n6.l0(com.yxcorp.gifshow.product.prettify.a.b(1)) ? this.f45734t : null, z12);
            }
        }
    }

    @Override // com.smile.gifmaker.mvps.presenter.d
    public void a0(View view) {
        if (!sl0.b.a()) {
            super.a0(view);
        }
        this.f45730o = w1.e(view, mj0.b.S3);
        this.f45731p = w1.e(view, mj0.b.Z3);
        this.f45732q = w1.e(view, m.f79747l);
        com.jakewharton.rxbinding2.view.i.a(this.f45730o).throttleFirst(300L, TimeUnit.MILLISECONDS).observeOn(in.a.f72154a).doOnNext(new Consumer() { // from class: com.yxcorp.gifshow.live.push.prepush.presenter.i
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                this.f45771a.I0(obj);
            }
        }).subscribe();
    }

    public boolean b() {
        PrettifyFragment prettifyFragment = this.f45729n;
        if (prettifyFragment == null || !prettifyFragment.isVisible()) {
            return false;
        }
        G0();
        return true;
    }

    @Override // com.smile.gifmaker.mvps.presenter.d
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        super.onDestroy();
        org.greenrobot.eventbus.c.e().w(this);
        PrettifyFragment prettifyFragment = this.f45729n;
        if (prettifyFragment != null) {
            prettifyFragment.Q2(null);
        }
        this.f45733r = null;
    }

    @org.greenrobot.eventbus.k(threadMode = ThreadMode.MAIN)
    public void onEvent(BeautyChangeEvent beautyChangeEvent) {
        com.yxcorp.gifshow.product.prettify.beauty.a aVar;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("1 onEvent() called with: event = [");
        sb2.append(beautyChangeEvent);
        sb2.append("]");
        if (this.f45725j) {
            com.yxcorp.gifshow.product.prettify.beauty.a aVar2 = this.f45734t;
            boolean z12 = (aVar2 == null || (aVar = beautyChangeEvent.mConfig) == null || aVar2.mId != aVar.mId || aVar2.mSmoothSkinConfig.mBright == aVar.mSmoothSkinConfig.mBright) ? false : true;
            this.f45734t = beautyChangeEvent.mConfig;
            boolean zB = com.yxcorp.gifshow.product.prettify.a.b(1);
            com.yxcorp.gifshow.product.prettify.beauty.c.B(this.f45734t);
            d20.i.K5(!n6.l0(zB));
            M0(z12);
        }
    }

    @Override // com.smile.gifmaker.mvps.presenter.d
    protected void t0() {
        super.t0();
        com.yxcorp.gifshow.camerasdk.f fVar = this.f45726k;
        if (fVar != null) {
            this.f45727l = fVar.f();
        }
        M0(false);
        org.greenrobot.eventbus.c.e().t(this);
    }
}
