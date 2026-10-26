package com.yxcorp.gifshow.live.push;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.smile.gifmaker.mvps.presenter.PresenterV1;
import com.yxcorp.bugly.CrashReporter;
import com.yxcorp.gifshow.Gsons;
import com.yxcorp.gifshow.activity.GifshowActivity;
import com.yxcorp.gifshow.api.live.LiveAnchorPlugin;
import com.yxcorp.gifshow.api.live.LiveCheckListener;
import com.yxcorp.gifshow.api.live.LivePlugin;
import com.yxcorp.gifshow.api.live.OnCloudFaceVerifyResultListener;
import com.yxcorp.gifshow.api.webview.WebViewPlugin;
import com.yxcorp.gifshow.autolog.AutoLogHelper;
import com.yxcorp.gifshow.live.gift.event.GiftBoxEvent;
import com.yxcorp.gifshow.live.play.LivePlayActivity;
import com.yxcorp.gifshow.live.play.g0;
import com.yxcorp.gifshow.live.play.i0;
import com.yxcorp.gifshow.live.play.z;
import com.yxcorp.gifshow.live.plaza.LivePlazaActivity;
import com.yxcorp.gifshow.live.plaza.presenter.LiveAuthorPresenter;
import com.yxcorp.gifshow.live.plaza.presenter.LiveClickPresenter;
import com.yxcorp.gifshow.live.plaza.presenter.LiveCoverPresenter;
import com.yxcorp.gifshow.live.plaza.presenter.LiveCoverSizePresenter;
import com.yxcorp.gifshow.live.plaza.presenter.SquareEntranceCardPresenter;
import com.yxcorp.gifshow.live.presenter.slide.SlidePlayPhotoCheckLivingPresenter;
import com.yxcorp.gifshow.live.presenter.slide.SlidePlayPhotoCheckNetworkPresenter;
import com.yxcorp.gifshow.live.presenter.slide.e3;
import com.yxcorp.gifshow.live.profile.u;
import com.yxcorp.gifshow.model.QPhoto;
import com.yxcorp.gifshow.model.response.LiveRecommendResponse;
import com.yxcorp.gifshow.recycler.RecyclerPresenter;
import com.yxcorp.gifshow.slideplay.d0;
import com.yxcorp.gifshow.util.Box;
import com.yxcorp.gifshow.util.n6;
import com.yxcorp.gifshow.webview.yoda.fragment.dialog.events.CloseHalfWebEvent;
import com.yxcorp.livestream.longconnection.exception.ServerException;
import com.yxcorp.retrofit.model.KwaiException;
import com.yxcorp.tmp.LiveShareHelper;
import com.yxcorp.utility.l1;
import com.yxcorp.utility.plugin.PluginManager;
import com.yxcorp.utility.u1;
import com.yxcorp.utility.x;
import fk0.e0;
import hl0.r;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import ln0.a0;
import ln0.c0;
import okhttp3.MultipartBody;
import sa1.s0;

/* JADX INFO: loaded from: classes4.dex */
public class LivePluginImpl implements LivePlugin {
    public static final String GOOGLE_PLAY_PACKAGE_NAME = "com.android.vending";
    public static final String GOOGLE_PLAY_APP_URL = n90.b.P().googlePlayUrl + uo.a.c().getPackageName();
    static QPhoto tmpPreLivePhoto = null;

    class a implements DialogInterface.OnClickListener {
        a(LivePluginImpl livePluginImpl) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i12) {
            AutoLogHelper.logDialog(dialogInterface, i12);
            dialogInterface.dismiss();
        }
    }

    class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ GifshowActivity f45515a;

        b(LivePluginImpl livePluginImpl, GifshowActivity gifshowActivity) {
            this.f45515a = gifshowActivity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i12) {
            AutoLogHelper.logDialog(dialogInterface, i12);
            ((LivePlayActivity) this.f45515a).Z0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ QPhoto lambda$getLiveInfo$1(r rVar) throws Exception {
        if (rVar != null) {
            return rVar.mLiveStream;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showLiveUpdateDialog$0(GifshowActivity gifshowActivity, DialogInterface dialogInterface, int i12) {
        try {
            if (l1.L(gifshowActivity, GOOGLE_PLAY_PACKAGE_NAME)) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + uo.a.c().getPackageName()));
                intent.setPackage(GOOGLE_PLAY_PACKAGE_NAME);
                gifshowActivity.startActivity(intent);
            } else {
                gifshowActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(GOOGLE_PLAY_APP_URL)));
            }
        } catch (ActivityNotFoundException e12) {
            CrashReporter.logCheckedException(e12, "LivePluginImpl.class", "lambda$showLiveUpdateDialog$0", -94);
            e12.printStackTrace();
        }
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Bitmap lambda$wonderFrameStickerUpload$2(Activity activity, String str, String str2, String str3, String str4) throws Exception {
        return c0.a(activity, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ObservableSource lambda$wonderFrameStickerUpload$3(String str, String str2, Bitmap bitmap) throws Exception {
        MultipartBody.Part partB = c0.b("segmentPic", UUID.randomUUID().toString() + ".png", bitmap);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return com.yxcorp.gifshow.live.a.a().uploadSticker(partB, str, str2);
    }

    public static void showLiveUpdateDialog(final GifshowActivity gifshowActivity) {
        if (gifshowActivity == null) {
            return;
        }
        com.yxcorp.gifshow.widget.dialog.a.C0966a c0966aN = Box.n(gifshowActivity, jj0.m.f75120g);
        c0966aN.e(jj0.l.f75068l1);
        c0966aN.b(false);
        c0966aN.o(jj0.l.f75110y1, new DialogInterface.OnClickListener() { // from class: com.yxcorp.gifshow.live.push.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i12) {
                LivePluginImpl.lambda$showLiveUpdateDialog$0(gifshowActivity, dialogInterface, i12);
            }
        });
        c0966aN.w();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void checkLiveFeeds(List<QPhoto> list, LiveCheckListener liveCheckListener, boolean z12) {
        com.yxcorp.gifshow.live.utils.c.h().f(list, liveCheckListener, z12);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Observable<QPhoto> checkLiving(QPhoto qPhoto) {
        return com.yxcorp.gifshow.live.utils.c.g(qPhoto);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void closeAllConnections() {
        com.yxcorp.livestream.longconnection.b.c();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void closeHalfWeb(Activity activity) {
        if (u1.c(activity)) {
            x.a().o(new CloseHalfWebEvent());
        }
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Observable<Map<String, String>> encrypt(String str) {
        return com.yxcorp.gifshow.live.gift.help.g.i().g(str);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public int geLiveCheckDuration() {
        return d20.p.m0() * 1000;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Intent generateLiveSingleWebViewIntent(GifshowActivity gifshowActivity) {
        return ((WebViewPlugin) PluginManager.get(WebViewPlugin.class)).createWebIntent(gifshowActivity, s0.b(a0.f78970b.d(), 1)).putExtra(((WebViewPlugin) PluginManager.get(WebViewPlugin.class)).urlKey(), "ks://live/preview");
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Observable<Object> getAuthStatusObservable() {
        return gl0.b.c().b();
    }

    public qk0.b getCheapGift() {
        return com.yxcorp.gifshow.live.push.b.f();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public com.smile.gifmaker.mvps.presenter.d getCheckLivingPresenter(Object obj) {
        return new SlidePlayPhotoCheckLivingPresenter((d0) obj);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public com.smile.gifmaker.mvps.presenter.d getCheckNetworkPresenter(Object obj) {
        return new SlidePlayPhotoCheckNetworkPresenter((d0) obj);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public int getFeedLiveCheckDuration() {
        return d20.p.q();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Observable<QPhoto> getLiveInfo(String str) {
        return com.yxcorp.gifshow.live.a.a().livePlayLaunch(str).map(new re1.e()).map(new Function() { // from class: com.yxcorp.gifshow.live.push.f
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return LivePluginImpl.lambda$getLiveInfo$1((r) obj);
            }
        }).subscribeOn(sd1.g.f89570b);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Class<? extends Activity> getLivePlayActivityClass() {
        return LivePlayActivity.class;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Intent getLivePlayActivityIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) LivePlayActivity.class);
        intent.putExtra("arg_from_activity_identity", context.hashCode());
        intent.addFlags(67108864);
        intent.addFlags(536870912);
        intent.putExtra("open_by_kwai_scheme", true);
        return intent;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Class<? extends Activity> getLivePlazaActivityClass() {
        return LivePlazaActivity.class;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public com.smile.gifmaker.mvps.presenter.d getLivePreloadPresenter(Object obj) {
        return new e3((d0) obj);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Observable<LiveRecommendResponse> getLiveRecommendRequest() {
        return com.yxcorp.gifshow.live.a.a().liveRecommend(null, 20, 1).map(new re1.e()).onErrorReturnItem(new LiveRecommendResponse());
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean getLiveShareSwitch() {
        return d20.p.X();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public int getLiveSquareEntranceCardId() {
        return jj0.j.P0;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public PresenterV1<QPhoto> getLiveSquareEntranceCardPresenter() {
        return new SquareEntranceCardPresenter();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public QPhoto getPreLivePhoto() {
        return tmpPreLivePhoto;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public int getSlideLiveCheckDuration() {
        return d20.p.C0();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public String getSourceWithAvatar(QPhoto qPhoto) {
        return ln0.x.e(qPhoto);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public String getSourceWithCard(QPhoto qPhoto) {
        return ln0.x.f(qPhoto);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public String getSourceWithDoubleFeedAvatar(QPhoto qPhoto) {
        return ln0.x.g(qPhoto);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public String getSourceWithFixed(String str) {
        return ln0.x.h(str);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public String getWebLiveShareUrl() {
        return a0.f78970b.g();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean hasLivePermission() {
        return d20.i.g() == 4;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean hasLivePermission(int i12) {
        return i12 == 4 || i12 == 5;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void hideDrawer(GifshowActivity gifshowActivity) {
        if (gifshowActivity instanceof LivePlayActivity) {
            long jM0 = n6.m0();
            if (!com.yxcorp.gifshow.user.auth.a.w() || !String.valueOf(jM0).equals(com.yxcorp.gifshow.user.auth.a.f56866b.getId())) {
                ((LivePlayActivity) gifshowActivity).Z0();
                return;
            }
            com.yxcorp.gifshow.widget.dialog.a.C0966a c0966aN = Box.n(gifshowActivity, jj0.m.f75120g);
            c0966aN.e(jj0.l.f75067l0).o(jj0.l.f75110y1, new b(this, gifshowActivity)).h(jj0.l.f75078o, new a(this));
            c0966aN.w();
        }
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void initModule() {
        com.yxcorp.gifshow.live.push.b.h();
    }

    @Override // com.yxcorp.utility.plugin.Plugin
    public boolean isAvailable() {
        return true;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean isHoldout() {
        return d20.p.M();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean isLiveActivity(Activity activity) {
        return u1.c(activity) && ((activity instanceof LivePlayActivity) || ((LiveAnchorPlugin) PluginManager.get(LiveAnchorPlugin.class)).isPushActivity(activity));
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean isLivePlayActivityRunning() {
        return LivePlayActivity.K > 0;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean isLivePlayFragment(Fragment fragment) {
        return fragment instanceof g0;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean isLivePreFragment(Fragment fragment) {
        return fragment instanceof i0;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean isServerException(Throwable th2) {
        return (th2 instanceof ServerException) || (th2 instanceof KwaiException);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void liveClearData() {
        List<String> listR1 = d20.i.R1(o81.b.f82986a);
        if (com.yxcorp.utility.i.d(listR1)) {
            return;
        }
        listR1.clear();
        d20.i.D6(listR1);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void liveRecoRealShow(QPhoto qPhoto, String str) {
        yk0.r.f(qPhoto, str);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void liveShare(GifshowActivity gifshowActivity, QPhoto qPhoto) {
        new LiveShareHelper(gifshowActivity, qPhoto).Z();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void logBannerClick(int i12) {
        pl0.a.a(i12);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void logBannerShow(int i12) {
        pl0.a.b(i12);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void logClickHostCenter() {
        yk0.n.a();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void logEnterProfileTime(QPhoto qPhoto, long j12) {
        qn0.r.f86875a.m0(lo0.b.f(qPhoto.getUserId(), qPhoto.getLiveStreamId(), j12), qPhoto.getListLoadSequenceID(), qPhoto.getLiveInfo().getLiveRequestType());
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void logEntranceClick(QPhoto qPhoto, String str, String str2) {
        yk0.f.a(qPhoto, str, str2);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void logEntranceShow(QPhoto qPhoto, String str, String str2) {
        yk0.f.b(qPhoto, str, str2);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public PresenterV1<QPhoto> newCoverSizePresenter() {
        return new LiveCoverSizePresenter();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Fragment newSlideLivePlayFragment() {
        return new g0();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Fragment newSlidePreLiveFragment() {
        return new i0();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void onLogout() {
        com.yxcorp.gifshow.live.push.b.j();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void openLiveByAnchorId(GifshowActivity gifshowActivity, String str, String str2) {
        LivePlayActivity.u1(gifshowActivity, str, str2);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void openLiveByPhoto(GifshowActivity gifshowActivity, QPhoto qPhoto, String str) {
        ml0.a.g();
        if (qPhoto == null || qPhoto.getLiveInfo() == null) {
            CrashReporter.throwException(new IllegalArgumentException(Gsons.f36335b.v(qPhoto)));
        } else {
            LivePlayActivity.v1(gifshowActivity, qPhoto, str);
        }
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void openLiveBySource(Activity activity, String str) {
        hl0.h hVar = new hl0.h();
        hVar.n(str);
        LivePlayActivity.w1(activity, hVar);
        sl0.a.c();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void preStartLive(QPhoto qPhoto, boolean z12) {
        z.a().o(qPhoto, z12);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void removeLiveListener(List<QPhoto> list, LiveCheckListener liveCheckListener) {
        com.yxcorp.gifshow.live.utils.c.h().p(list, liveCheckListener);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void setPreLivePhoto(QPhoto qPhoto) {
        tmpPreLivePhoto = qPhoto;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public boolean shouldShowLiveTab() {
        int iG = d20.i.g();
        return iG > 0 && iG != 1;
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void showGiftPanel(Activity activity) {
        if (u1.c(activity) && (activity instanceof LivePlayActivity)) {
            x.a().o(new GiftBoxEvent(true));
        }
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void showMiniProfile(Activity activity, String str) {
        if (!(activity instanceof LivePlayActivity)) {
            ((LiveAnchorPlugin) PluginManager.get(LiveAnchorPlugin.class)).showMiniProfile(activity, str);
        } else {
            LivePlayActivity livePlayActivity = (LivePlayActivity) activity;
            u.K3(livePlayActivity, livePlayActivity.W0(), str, "LIVE_WEB", true, true);
        }
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void showRechargePanel(Activity activity, String str, Boolean bool) {
        if (u1.c(activity) && (activity instanceof LivePlayActivity)) {
            LivePlayActivity livePlayActivity = (LivePlayActivity) activity;
            e0.V2(livePlayActivity, livePlayActivity.W0(), str, bool);
        }
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void startLiveAuthenticateCameraActivityForResult(GifshowActivity gifshowActivity, Serializable serializable, int i12, cg0.a aVar) {
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void startLivePlazaActivity(GifshowActivity gifshowActivity, String str) {
        LivePlazaActivity.x0(gifshowActivity, str);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void updateAuthStatus() {
        gl0.b.c().e();
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public void verifyRealNameInfo(GifshowActivity gifshowActivity, Serializable serializable, OnCloudFaceVerifyResultListener onCloudFaceVerifyResultListener) {
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public Observable<String> wonderFrameStickerUpload(final Activity activity, final String str, final String str2, final String str3, final String str4, final String str5) {
        return Observable.just(str).observeOn(in.e.f72185a).map(new Function() { // from class: com.yxcorp.gifshow.live.push.d
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return LivePluginImpl.lambda$wonderFrameStickerUpload$2(activity, str, str2, str3, (String) obj);
            }
        }).observeOn(in.e.f72187c).flatMap(new Function() { // from class: com.yxcorp.gifshow.live.push.e
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return LivePluginImpl.lambda$wonderFrameStickerUpload$3(str4, str5, (Bitmap) obj);
            }
        }).map(new re1.e()).map(new Function() { // from class: com.yxcorp.gifshow.live.push.g
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ((hl0.g0) obj).a();
            }
        });
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public RecyclerPresenter<QPhoto> newLiveAuthorPresenter(String str) {
        return new LiveAuthorPresenter(str);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public RecyclerPresenter<QPhoto> newLiveClickPresenter(String str) {
        return new LiveClickPresenter(str);
    }

    @Override // com.yxcorp.gifshow.api.live.LivePlugin
    public RecyclerPresenter<QPhoto> newLiveCoverPresenter(String str) {
        return new LiveCoverPresenter(str);
    }
}
