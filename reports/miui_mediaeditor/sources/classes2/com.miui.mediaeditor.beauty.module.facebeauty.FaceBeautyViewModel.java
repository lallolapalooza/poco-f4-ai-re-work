package com.miui.mediaeditor.beauty.module.facebeauty;

import androidx.fragment.app.Fragment;
import androidx.p002lifecycle.Lifecycle;
import androidx.p002lifecycle.MutableLiveData;
import com.google.android.gms.internal.mlkit_language_id_common.n9;
import com.miui.gallery.arch.livedata.UnStickLiveData;
import com.miui.gallery.editor.photo.app.navigator.q;
import com.miui.gallery.editor.photo.app.navigator.r;
import com.miui.gallery.editor.photo.app.navigator.t;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.miui.gallery.util.concurrent.ThreadManager;
import com.miui.gallery.util.d0;
import com.miui.mediaeditor.R;
import com.miui.mediaeditor.algo.base.AlgorithmBuilder;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.OperateType;
import com.miui.mediaeditor.algo.beauty.face.FaceBeautyAlgorithm;
import com.miui.mediaeditor.algo.beauty.face.processer.FaceBeautyProcessPreviewParam;
import com.miui.mediaeditor.algo.beauty.face.processer.InitAiWrinkleRemoverParam;
import com.miui.mediaeditor.beauty.BitmapHolderViewModel;
import com.miui.mediaeditor.beauty.module.BeautyLibraryStateCheckHelper;
import com.miui.mediaeditor.beauty.module.facebeauty.data.FaceBeautyCategory;
import com.miui.mediaeditor.beauty.module.facebeauty.data.FaceBeautyMenuEntity;
import com.miui.mediaeditor.beauty.module.facebeauty.menu.FaceBeautyMenuFragment;
import fr.l;
import g8.k;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import kotlin.Metadata;
import mq.n;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/miui/mediaeditor/beauty/module/facebeauty/FaceBeautyViewModel;", "Lcom/miui/mediaeditor/beauty/BitmapHolderViewModel;", "<init>", "()V", "dynamicfeature_beauty_release"}, k = 1, mv = {1, 8, 0})
public final class FaceBeautyViewModel extends BitmapHolderViewModel {
    public final UnStickLiveData<Boolean> A;
    public final MutableLiveData<FaceBeautyMenuEntity> D;
    public final MutableLiveData<List<FaceBeautyMenuEntity>> E;
    public og.c F;
    public final com.miui.mediaeditor.algo.base.a G;
    public final yq.c H;
    public final yq.c I;
    public final MutableLiveData<Boolean> J;
    public final MutableLiveData<Boolean> K;
    public final MutableLiveData<Boolean> L;
    public final MutableLiveData<Boolean> M;
    public final MutableLiveData<Integer> N;
    public int O;
    public BeautyLibraryStateCheckHelper P;
    public final List<FaceBeautyCategory> s;
    public final yq.c v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f7697w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public FaceBeautyMenuEntity f7698x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final b f7699y;
    public final UnStickLiveData<Boolean> z;

    public FaceBeautyViewModel() {
        String strT = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_tab_skin);
        kotlin.jvm.internal.e.e(strT, "getString(R.string.beauty_skin_beauty_tab_skin)");
        og.c.C0306c c0306c = og.c.C0306c.f15687b;
        String strT2 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_microdermabrasion);
        kotlin.jvm.internal.e.e(strT2, "getString(R.string.beaut…tegory_microdermabrasion)");
        int i10 = R.drawable.ic_skin_grinding;
        com.miui.mediaeditor.algo.beauty.common.f.a aVar = com.miui.mediaeditor.algo.beauty.common.f.a.f7425c;
        vh.a aVar2 = new vh.a(aVar, 1);
        OperateType operateType = OperateType.HAND_TOUCH_APPLY;
        og.c.a aVar3 = og.c.a.f15685b;
        String strT3 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_freckle_and_acne);
        kotlin.jvm.internal.e.e(strT3, "getString(R.string.beaut…ategory_freckle_and_acne)");
        int i11 = 0;
        og.c.g gVar = og.c.g.f15691b;
        String strT4 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_wrinkle_removal);
        kotlin.jvm.internal.e.e(strT4, "getString(R.string.beaut…category_wrinkle_removal)");
        og.c.h hVar = og.c.h.f15692b;
        String strT5 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_skin_texture);
        kotlin.jvm.internal.e.e(strT5, "getString(R.string.beaut…ty_category_skin_texture)");
        FaceBeautyMenuEntity[] faceBeautyMenuEntityArr = {new FaceBeautyMenuEntity(c0306c, 0, strT2, i10, aVar2, new vh.c(operateType), 0, 192), new FaceBeautyMenuEntity(aVar3, 0, strT3, R.drawable.ic_freckle_acne_removal, new vh.a(com.miui.mediaeditor.algo.beauty.common.f.c.f7427c, 1), new vh.c(OperateType.HAND_TOUCH_CLICK), i11, 192), new FaceBeautyMenuEntity(gVar, 0, strT4, R.drawable.ic_anti_wrinkle, new vh.a(aVar, 1), new vh.c(operateType), 20, 64), new FaceBeautyMenuEntity(hVar, 0, strT5, R.drawable.ic_skin_texture, new vh.a(aVar, 1), (vh.c) null, 20, 96)};
        String strT6 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_tab_skin_color);
        kotlin.jvm.internal.e.e(strT6, "getString(R.string.beaut…in_beauty_tab_skin_color)");
        og.c.n nVar = og.c.n.f15698b;
        String strT7 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_whitening);
        kotlin.jvm.internal.e.e(strT7, "getString(R.string.beaut…eauty_category_whitening)");
        int i12 = R.drawable.ic_skin_whitening;
        com.miui.mediaeditor.algo.beauty.common.f.b bVar = com.miui.mediaeditor.algo.beauty.common.f.b.f7426c;
        String strT8 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_even_skin);
        kotlin.jvm.internal.e.e(strT8, "getString(R.string.beaut…eauty_category_even_skin)");
        og.c.k kVar = og.c.k.f15695b;
        String strT9 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_remove_dark_circles);
        kotlin.jvm.internal.e.e(strT9, "getString(R.string.beaut…gory_remove_dark_circles)");
        og.c.m mVar = og.c.m.f15697b;
        String strT10 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_color);
        kotlin.jvm.internal.e.e(strT10, "getString(R.string.beaut…in_beauty_category_color)");
        FaceBeautyMenuEntity[] faceBeautyMenuEntityArr2 = {new FaceBeautyMenuEntity(nVar, 1, strT7, i12, new vh.a(bVar, 1), (vh.c) null, 0, 224), new FaceBeautyMenuEntity(og.c.b.f15686b, 1, strT8, R.drawable.ic_even_skin, new vh.a(aVar, 1), (vh.c) null, 0, 224), new FaceBeautyMenuEntity(kVar, 1, strT9, R.drawable.ic_dispel_dark_circles, new vh.a(aVar, 1), (vh.c) null, 0, 224), new FaceBeautyMenuEntity(mVar, 1, strT10, R.drawable.ic_skin_colour, new vh.a(bVar, 1), (vh.c) null, 0, 224)};
        String strT11 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_tab_adjust);
        kotlin.jvm.internal.e.e(strT11, "getString(R.string.beauty_skin_beauty_tab_adjust)");
        String strT12 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_dazzling);
        kotlin.jvm.internal.e.e(strT12, "getString(R.string.beaut…beauty_category_dazzling)");
        String strT13 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_color_boost);
        kotlin.jvm.internal.e.e(strT13, "getString(R.string.beaut…uty_category_color_boost)");
        int i13 = R.drawable.ic_appearance_improvement;
        vh.a aVar4 = new vh.a(aVar, 1);
        og.c.l lVar = og.c.l.f15696b;
        String strT14 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_three_dimensional_facial_features);
        kotlin.jvm.internal.e.e(strT14, "getString(R.string.beaut…ensional_facial_features)");
        int i14 = R.drawable.ic_dimensional_facial_features;
        vh.a aVar5 = new vh.a(aVar, 1);
        og.c.e eVar = og.c.e.f15689b;
        String strT15 = miuix.appcompat.app.floatingactivity.i.t(R.string.beauty_skin_beauty_category_eyebrow_enhancement);
        kotlin.jvm.internal.e.e(strT15, "getString(R.string.beaut…gory_eyebrow_enhancement)");
        List<FaceBeautyCategory> listS = bp.b.S(new FaceBeautyCategory(0, strT, bp.b.S(faceBeautyMenuEntityArr)), new FaceBeautyCategory(1, strT6, bp.b.S(faceBeautyMenuEntityArr2)), new FaceBeautyCategory(2, strT11, bp.b.S(new FaceBeautyMenuEntity(og.c.d.f15688b, 2, strT12, R.drawable.ic_bright, new vh.a(aVar, 1), (vh.c) null, 0, 224), new FaceBeautyMenuEntity(og.c.i.f15693b, 2, strT13, i13, aVar4, (vh.c) null, 0, 224), new FaceBeautyMenuEntity(lVar, 2, strT14, i14, aVar5, (vh.c) null, 0, 224), new FaceBeautyMenuEntity(eVar, 2, strT15, R.drawable.ic_eyebrow, new vh.a(aVar, 1), (vh.c) null, i11, 224))));
        this.s = listS;
        this.v = kotlin.a.a(new fr.a<ArrayList<FaceBeautyMenuEntity>>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel$menuEntities$2
            {
                super(0);
            }

            @Override // fr.a
            public final ArrayList<FaceBeautyMenuEntity> invoke() {
                ArrayList<FaceBeautyMenuEntity> arrayList = new ArrayList<>();
                Iterator<T> it = this.this$0.s.iterator();
                while (it.hasNext()) {
                    List<FaceBeautyMenuEntity> list = ((FaceBeautyCategory) it.next()).r;
                    if (list != null) {
                        arrayList.addAll(list);
                    }
                }
                return arrayList;
            }
        });
        this.f7699y = new b();
        this.z = new UnStickLiveData<>();
        this.A = new UnStickLiveData<>();
        MutableLiveData<FaceBeautyMenuEntity> mutableLiveData = new MutableLiveData<>();
        this.D = mutableLiveData;
        this.E = new MutableLiveData<>();
        this.F = c0306c;
        this.G = new AlgorithmBuilder().setAlgorithm(new FaceBeautyAlgorithm()).addDefaultMonitor().build();
        this.H = kotlin.a.a(new fr.a<f>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel$repository$2
            @Override // fr.a
            public final f invoke() {
                return new f();
            }
        });
        this.I = kotlin.a.a(new fr.a<ci.a<og.c, og.b>>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel$operationManager$2
            {
                super(0);
            }

            @Override // fr.a
            public final ci.a<og.c, og.b> invoke() {
                ci.a<og.c, og.b> aVar6 = new ci.a<>();
                aVar6.f825a = new i(this.this$0);
                return aVar6;
            }
        });
        this.J = new MutableLiveData<>();
        this.K = new MutableLiveData<>();
        this.L = new MutableLiveData<>();
        this.M = new MutableLiveData<>();
        this.N = new MutableLiveData<>(2);
        List<FaceBeautyMenuEntity> list = listS.get(0).r;
        mutableLiveData.setValue(list != null ? list.get(0) : null);
    }

    public static final void t(FaceBeautyViewModel faceBeautyViewModel, int i10) {
        faceBeautyViewModel.getClass();
        kotlinx.coroutines.internal.a.m(Integer.valueOf(i10), "FaceBeautyVM", "onLibraryStateChanged, state: %d");
        MutableLiveData<List<FaceBeautyMenuEntity>> mutableLiveData = faceBeautyViewModel.E;
        ArrayList arrayList = (ArrayList) faceBeautyViewModel.v.getValue();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (faceBeautyViewModel.B((FaceBeautyMenuEntity) obj)) {
                arrayList2.add(obj);
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            ((FaceBeautyMenuEntity) it.next()).f7713y = i10;
        }
        mutableLiveData.postValue(arrayList2);
    }

    public static og.b v(FaceBeautyViewModel faceBeautyViewModel, FaceBeautyMenuEntity faceBeautyMenuEntity) {
        faceBeautyViewModel.getClass();
        vh.a aVar = faceBeautyMenuEntity.v;
        return new og.b(faceBeautyMenuEntity.m, faceBeautyMenuEntity.f7712x, aVar.f17949e, 0.0d, aVar.m);
    }

    public final void A(Lifecycle lifecycle) {
        BeautyLibraryStateCheckHelper beautyLibraryStateCheckHelper = new BeautyLibraryStateCheckHelper(new c());
        this.P = beautyLibraryStateCheckHelper;
        mq.h hVarCreate = mq.h.create(new com.miui.mediaeditor.aigc.download.d(beautyLibraryStateCheckHelper, 3));
        kotlin.jvm.internal.e.e(hVarCreate, "create { emitter: Observ…}\n            )\n        }");
        ThreadPoolExecutor threadPoolExecutor = ThreadManager.f5574a.getValue().f491c;
        n nVar = wq.a.f18185a;
        ((com.uber.autodispose.h) hVarCreate.subscribeOn(new ExecutorScheduler(threadPoolExecutor)).as(n9.f(lp.a.a(lifecycle)))).subscribe(new t(9, new l<Integer, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel$initLibraryCheckHelper$1$1
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Integer num) {
                Integer state = num;
                FaceBeautyViewModel faceBeautyViewModel = this.this$0;
                FaceBeautyMenuEntity faceBeautyMenuEntityX = faceBeautyViewModel.x(((f) faceBeautyViewModel.H.getValue()).f7714a.get(0));
                if (faceBeautyMenuEntityX != null) {
                    FaceBeautyViewModel faceBeautyViewModel2 = this.this$0;
                    int i10 = faceBeautyMenuEntityX.f7713y;
                    if (state == null || i10 != state.intValue()) {
                        kotlin.jvm.internal.e.e(state, "state");
                        FaceBeautyViewModel.t(faceBeautyViewModel2, state.intValue());
                    }
                }
                return yq.d.f18754a;
            }
        }), new q(11, new l<Throwable, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel$initLibraryCheckHelper$1$2
            @Override // fr.l
            public final yq.d invoke(Throwable th2) {
                kotlinx.coroutines.internal.a.x(th2, "FaceBeautyVM", "checkLibraryState error");
                return yq.d.f18754a;
            }
        }));
    }

    public final boolean B(FaceBeautyMenuEntity it) {
        kotlin.jvm.internal.e.f(it, "it");
        f fVar = (f) this.H.getValue();
        fVar.getClass();
        return fVar.f7714a.contains(it.m);
    }

    public final void C(og.b bVar, boolean z) {
        this.F = bVar.f15682c;
        this.G.a(new FaceBeautyProcessPreviewParam(bVar, z));
        this.z.setValue(Boolean.TRUE);
    }

    public final void D(FaceBeautyMenuEntity entity) {
        kotlin.jvm.internal.e.f(entity, "entity");
        this.f7697w = 0;
        this.D.setValue(entity);
        this.J.setValue(Boolean.valueOf(entity.f7712x));
        boolean z = entity.f7712x;
        b bVar = this.f7699y;
        if (z) {
            og.c cVar = this.F;
            bVar.getClass();
            if (kotlin.jvm.internal.e.a(cVar, og.c.C0306c.f15687b)) {
                bVar.G = Boolean.TRUE;
            } else if (kotlin.jvm.internal.e.a(cVar, og.c.a.f15685b)) {
                bVar.H = Boolean.TRUE;
            } else if (kotlin.jvm.internal.e.a(cVar, og.c.g.f15691b)) {
                bVar.I = Boolean.TRUE;
            }
        }
        bVar.R(entity);
        z().b();
        C(v(this, entity), false);
        vh.c cVar2 = entity.f7711w;
        if (cVar2 != null) {
            this.N.setValue(Integer.valueOf(cVar2.f17953e));
        }
        this.f7698x = FaceBeautyMenuEntity.d(entity);
    }

    public final void E(Fragment fragment, final og.c cVar) {
        BeautyLibraryStateCheckHelper beautyLibraryStateCheckHelper = this.P;
        if (beautyLibraryStateCheckHelper != null) {
            mq.h hVarCreate = mq.h.create(new com.airbnb.lottie.a(beautyLibraryStateCheckHelper, 1));
            kotlin.jvm.internal.e.e(hVarCreate, "create { emitter: Observ…rDownload(true)\n        }");
            ThreadPoolExecutor threadPoolExecutor = ThreadManager.f5574a.getValue().f491c;
            n nVar = wq.a.f18185a;
            ((com.uber.autodispose.h) hVarCreate.subscribeOn(new ExecutorScheduler(threadPoolExecutor)).as(n9.f(lp.a.a(fragment.getLifecycle())))).subscribe(new q(12, new l<Integer, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel$startDownloadLibrary$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // fr.l
                public final yq.d invoke(Integer num) {
                    Integer state = num;
                    FaceBeautyMenuEntity faceBeautyMenuEntityX = this.this$0.x(cVar);
                    if (faceBeautyMenuEntityX != null) {
                        FaceBeautyViewModel faceBeautyViewModel = this.this$0;
                        int i10 = faceBeautyMenuEntityX.f7713y;
                        if (state == null || i10 != state.intValue()) {
                            kotlin.jvm.internal.e.e(state, "state");
                            FaceBeautyViewModel.t(faceBeautyViewModel, state.intValue());
                        }
                    }
                    return yq.d.f18754a;
                }
            }), new r(7, new l<Throwable, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel$startDownloadLibrary$1$2
                @Override // fr.l
                public final yq.d invoke(Throwable th2) {
                    kotlinx.coroutines.internal.a.x(th2, "FaceBeautyVM", "startDownloadLibrary error");
                    return yq.d.f18754a;
                }
            }));
        }
    }

    public final void F(FaceBeautyMenuEntity itemEntity) {
        kotlin.jvm.internal.e.f(itemEntity, "itemEntity");
        FaceBeautyMenuEntity faceBeautyMenuEntity = this.f7698x;
        if (!kotlin.jvm.internal.e.a(faceBeautyMenuEntity != null ? faceBeautyMenuEntity.m : null, itemEntity.m)) {
            D(itemEntity);
        }
        this.O = itemEntity.v.f17949e;
    }

    public final void G(FaceBeautyMenuEntity entity) {
        kotlin.jvm.internal.e.f(entity, "entity");
        C(v(this, entity), false);
    }

    public final boolean u(final FaceBeautyMenuFragment fragment, final FaceBeautyMenuEntity entity) {
        int i10;
        kotlin.jvm.internal.e.f(fragment, "fragment");
        kotlin.jvm.internal.e.f(entity, "entity");
        int i11 = 0;
        if (!B(entity) || (i10 = entity.f7713y) == 0 || i10 == 17) {
            return false;
        }
        if (!BaseGalleryPreferences.a.a()) {
            kotlinx.coroutines.internal.a.y("FaceBeautyVM", "download sdk cta not allowed");
            k.c(fragment.getActivity(), new i8.a() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.g
                @Override // i8.a
                public final void k0(boolean z) {
                    FaceBeautyViewModel this$0 = this.d;
                    kotlin.jvm.internal.e.f(this$0, "this$0");
                    Fragment fragment2 = fragment;
                    kotlin.jvm.internal.e.f(fragment2, "$fragment");
                    FaceBeautyMenuEntity entity2 = entity;
                    kotlin.jvm.internal.e.f(entity2, "$entity");
                    if (z) {
                        this$0.E(fragment2, entity2.m);
                    }
                }
            });
        } else if (!kotlinx.coroutines.internal.a.Y()) {
            d0.a(fragment.getContext(), R.string.photo_editor_common_download_failed_msg);
        } else if (kotlinx.coroutines.internal.a.T()) {
            pc.b.a(fragment.getContext(), new h(i11, this, fragment, entity));
        } else {
            E(fragment, entity.m);
        }
        kotlinx.coroutines.internal.a.s0("FaceBeautyVM", "no ai_wrinkle_remover_processor.bundle found!");
        return true;
    }

    public final void w() {
        Long l = com.miui.gallery.editor_common.library.a.f5067h[0];
        kotlin.jvm.internal.e.e(l, "LibraryConstantsHelper.sFaceBeautyBundles[0]");
        String path = com.miui.gallery.editor_common.library.a.a(l.longValue()).getPath();
        kotlin.jvm.internal.e.e(path, "getSpecificDirForLibrary…0]\n                ).path");
        if (this.G.a(new InitAiWrinkleRemoverParam(path)).b()) {
            this.A.postValue(Boolean.TRUE);
        }
    }

    public final FaceBeautyMenuEntity x(og.c menuType) {
        kotlin.jvm.internal.e.f(menuType, "menuType");
        Iterator<T> it = this.s.iterator();
        while (it.hasNext()) {
            List<FaceBeautyMenuEntity> list = ((FaceBeautyCategory) it.next()).r;
            if (list != null) {
                for (FaceBeautyMenuEntity faceBeautyMenuEntity : list) {
                    if (kotlin.jvm.internal.e.a(faceBeautyMenuEntity.m, menuType)) {
                        return faceBeautyMenuEntity;
                    }
                }
            }
        }
        return null;
    }

    public final void y(FaceBeautyMenuEntity entity) {
        kotlin.jvm.internal.e.f(entity, "entity");
        if (entity.v.f17949e != this.O) {
            og.b bVarV = v(this, entity);
            bVarV.f = this.O;
            z().c(bVarV);
            this.f7699y.R(entity);
        }
    }

    public final ci.a<og.c, og.b> z() {
        return (ci.a) this.I.getValue();
    }
}
