package com.yxcorp.gifshow.live.push;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.b0;
import com.yxcorp.gifshow.activity.SingleFragmentPageActivity;
import com.yxcorp.gifshow.api.live.LiveDebugPlugin;
import com.yxcorp.gifshow.fragment.BaseFragment;
import com.yxcorp.gifshow.live.gift.help.x;
import com.yxcorp.utility.g0;
import com.yxcorp.utility.plugin.PluginManager;
import hl0.a0;

/* JADX INFO: loaded from: classes8.dex */
public class LivePushActivity extends SingleFragmentPageActivity {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private m f45516o;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J0(int i12) {
        if (M()) {
            j0();
        }
    }

    public static void K0(@e.a FragmentActivity fragmentActivity, jl0.a aVar) {
        Intent intent = new Intent(fragmentActivity, (Class<?>) LivePushActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("livePushConfig", aVar.f75195a);
        bundle.putString("background_image", aVar.f75196b);
        bundle.putSerializable("livePushBeautyConfig", aVar.f75197c);
        bundle.putSerializable("livePushFILTERConfig", aVar.f75198d);
        bundle.putBoolean("liveFrontCamera", aVar.f75199e);
        bundle.putString("liveTitle", aVar.f75200f);
        bundle.putBoolean("liveNotifyFollower", aVar.f75201g);
        bundle.putString("streamType", aVar.f75202h);
        intent.putExtras(bundle);
        fragmentActivity.startActivity(intent);
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentActivity
    protected boolean A0() {
        return false;
    }

    public o40.i H0() {
        return this.f36686i;
    }

    public m I0() {
        return this.f45516o;
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentPageActivity, com.yxcorp.gifshow.activity.KwaiActivity
    public String J() {
        if (y0() == null || !(y0() instanceof BaseFragment)) {
            return null;
        }
        return ((BaseFragment) y0()).getUrl();
    }

    @Override // com.yxcorp.gifshow.activity.GifshowActivity, android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(jj0.e.f74511f, jj0.e.f74515j);
    }

    @Override // com.yxcorp.gifshow.activity.GifshowActivity
    protected void j0() {
        Window window = getWindow();
        View decorView = window.getDecorView();
        int i12 = Build.VERSION.SDK_INT;
        if (i12 >= 21) {
            window.addFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else if (i12 >= 19) {
            window.addFlags(67108864);
        }
        decorView.setSystemUiVisibility(g0.f62444d);
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentActivity, com.yxcorp.gifshow.activity.GifshowActivity, com.trello.rxlifecycle3.components.support.RxFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        com.yxcorp.gifshow.api.init.scheduler.h.PRODUCT.scheduleNow();
        nw.b.p().s();
        nm0.a.l();
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
        as0.e eVar = (as0.e) b0.c(this).a(as0.e.class);
        eVar.f5539b.setValue(Boolean.FALSE);
        eVar.f5541d.setValue(new as0.e.a(true));
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.yxcorp.gifshow.live.push.i
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i12) {
                this.f45528a.J0(i12);
            }
        });
        com.yxcorp.gifshow.apm.c.m();
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentActivity, com.yxcorp.gifshow.activity.KwaiActivity, com.trello.rxlifecycle3.components.support.RxFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        x.a();
        if (com.yxcorp.utility.b0.f62381a) {
            ((LiveDebugPlugin) PluginManager.get(LiveDebugPlugin.class)).clearLongConn();
        }
        com.yxcorp.gifshow.apm.c.o();
    }

    @Override // com.yxcorp.gifshow.activity.GifshowActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        a0 a0Var = (a0) getIntent().getParcelableExtra("livePushConfig");
        a0 a0Var2 = (a0) intent.getParcelableExtra("livePushConfig");
        super.onNewIntent(intent);
        if (a0Var == null || a0Var2 == null || TextUtils.equals(a0Var.liveStreamId, a0Var2.liveStreamId)) {
            return;
        }
        m mVar = this.f45516o;
        if (mVar != null) {
            S(mVar);
        }
        Fragment fragmentX0 = x0();
        getSupportFragmentManager().a().t(v(), fragmentX0, fragmentX0.getClass().getSimpleName()).j();
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentActivity, com.yxcorp.gifshow.activity.GifshowActivity, com.yxcorp.gifshow.activity.KwaiActivity, com.trello.rxlifecycle3.components.support.RxFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentActivity, com.yxcorp.gifshow.activity.GifshowActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z12) {
        if (z12) {
            j0();
        }
        super.onWindowFocusChanged(z12);
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentActivity
    @e.a
    public Fragment x0() {
        m mVar = new m();
        this.f45516o = mVar;
        mVar.setArguments(getIntent().getExtras());
        C(this.f45516o);
        return this.f45516o;
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentActivity
    public Fragment y0() {
        return this.f45516o;
    }

    @Override // com.yxcorp.gifshow.activity.SingleFragmentPageActivity, com.yxcorp.gifshow.activity.GifshowActivity, qn0.j0
    public int z() {
        m mVar = this.f45516o;
        if (mVar == null || mVar.isAdded()) {
            return super.z();
        }
        return 0;
    }
}
