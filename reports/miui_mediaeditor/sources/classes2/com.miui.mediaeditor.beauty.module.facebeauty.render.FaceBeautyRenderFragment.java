package com.miui.mediaeditor.beauty.module.facebeauty.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.p002lifecycle.Observer;
import androidx.view.m;
import com.faceunity.core.enumeration.ProcessMode;
import com.miui.gallery.util.concurrent.ThreadManager;
import com.miui.mediaeditor.R;
import com.miui.mediaeditor.algo.base.DefaultBaseParam;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.MagnifierBeautyGlView;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.OperateType;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.h;
import com.miui.mediaeditor.algo.beauty.common.core.widgets.i;
import com.miui.mediaeditor.algo.beauty.common.processer.BeautyExportDataParam;
import com.miui.mediaeditor.algo.beauty.common.processer.DetectedInfoParam;
import com.miui.mediaeditor.algo.beauty.common.processer.DetectedInfoResult;
import com.miui.mediaeditor.algo.beauty.face.processer.HandBeautyCoordinateParam;
import com.miui.mediaeditor.algo.beauty.face.processer.ImageStrokeState;
import com.miui.mediaeditor.algo.beauty.face.processer.ImageStrokeStateParam;
import com.miui.mediaeditor.algo.beauty.face.processer.SetImagePointSizeParam;
import com.miui.mediaeditor.beauty.module.facebeauty.FaceBeautyViewModel;
import com.miui.mediaeditor.beauty.module.facebeauty.data.FaceBeautyMenuEntity;
import com.miui.mediaeditor.beauty.module.facebeauty.data.FaceBeautyRenderData;
import com.miui.mediaeditor.photo.base.AbstractTrackFragment;
import com.miui.mediaeditor.photo.base.FunctionBaseViewModel;
import com.miui.mediaeditor.photo.base.RenderFragment;
import com.miui.mediaeditor.photo.base.RenderVMFragment;
import com.miui.mediaeditor.photo.core.render.RenderData;
import fr.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.k;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/miui/mediaeditor/beauty/module/facebeauty/render/FaceBeautyRenderFragment;", "Lcom/miui/mediaeditor/photo/base/RenderVMFragment;", "Lcom/miui/mediaeditor/beauty/module/facebeauty/FaceBeautyViewModel;", "<init>", "()V", "dynamicfeature_beauty_release"}, k = 1, mv = {1, 8, 0})
public final class FaceBeautyRenderFragment extends RenderVMFragment<FaceBeautyViewModel> {

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public static final /* synthetic */ int f7745b0 = 0;
    public ImageView L;
    public ImageView M;
    public ImageView N;
    public ImageView O;
    public MagnifierBeautyGlView P;
    public ViewGroup Q;
    public boolean S;
    public int T;
    public int U;
    public int V;
    public RenderData W;
    public View Z;
    public boolean R = true;
    public final a X = new a();
    public final b Y = new b();

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final c f7746a0 = new c();

    public static final class a implements wj.a {
        public a() {
        }

        @Override // wj.a
        public final void c() {
            MagnifierBeautyGlView magnifierBeautyGlView = FaceBeautyRenderFragment.this.P;
            if (magnifierBeautyGlView != null) {
                magnifierBeautyGlView.l(false);
            }
        }

        @Override // wj.a
        public final void w() {
            MagnifierBeautyGlView magnifierBeautyGlView = FaceBeautyRenderFragment.this.P;
            if (magnifierBeautyGlView != null) {
                magnifierBeautyGlView.l(true);
            }
        }
    }

    public static final class b implements com.miui.mediaeditor.algo.beauty.common.core.widgets.e {
        public b() {
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void a() {
            FaceBeautyRenderFragment faceBeautyRenderFragment = FaceBeautyRenderFragment.this;
            FaceBeautyViewModel faceBeautyViewModelA1 = FaceBeautyRenderFragment.a1(faceBeautyRenderFragment);
            faceBeautyViewModelA1.G.f(new DefaultBaseParam(0));
            faceBeautyViewModelA1.w();
            yq.c<b9.g> cVar = ThreadManager.f5574a;
            ThreadManager.a.e(new com.miui.mediaeditor.beauty.module.facebeauty.render.d(faceBeautyRenderFragment, 1));
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.e
        public final void d() {
            FaceBeautyRenderFragment faceBeautyRenderFragment = FaceBeautyRenderFragment.this;
            MagnifierBeautyGlView magnifierBeautyGlView = faceBeautyRenderFragment.P;
            int i10 = 1;
            if ((magnifierBeautyGlView == null || magnifierBeautyGlView.isEnabled()) ? false : true) {
                FaceBeautyViewModel faceBeautyViewModelY0 = faceBeautyRenderFragment.Y0();
                DetectedInfoResult detectedInfoResult = (DetectedInfoResult) faceBeautyViewModelY0.G.a(new DetectedInfoParam());
                int i11 = detectedInfoResult.f7432e;
                com.miui.mediaeditor.beauty.module.facebeauty.b bVar = faceBeautyViewModelY0.f7699y;
                bVar.getClass();
                Integer[] ages = detectedInfoResult.f7433n;
                kotlin.jvm.internal.e.f(ages, "ages");
                Integer[] genders = detectedInfoResult.r;
                kotlin.jvm.internal.e.f(genders, "genders");
                Integer[] skins = detectedInfoResult.s;
                kotlin.jvm.internal.e.f(skins, "skins");
                bVar.J = Integer.valueOf(i11);
                bVar.K = ages;
                bVar.L = genders;
                bVar.M = skins;
                kotlinx.coroutines.internal.a.o("FaceBeautyVM", "ages: " + k.l1(ages) + " genders: " + k.l1(genders) + " skins: " + k.l1(skins) + " faceCount: " + i11 + " humanCount: " + detectedInfoResult.m);
                yq.c<b9.g> cVar = ThreadManager.f5574a;
                ThreadManager.a.f(100L, new f(faceBeautyRenderFragment, i10));
            }
            yq.c<b9.g> cVar2 = ThreadManager.f5574a;
            ThreadManager.a.e(new e(faceBeautyRenderFragment, 2));
        }
    }

    public static final class c implements i {
        public c() {
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.i
        public final void a(double d) {
            FaceBeautyViewModel faceBeautyViewModelA1 = FaceBeautyRenderFragment.a1(FaceBeautyRenderFragment.this);
            faceBeautyViewModelA1.G.a(new SetImagePointSizeParam(d));
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.i
        public final void b() {
            FaceBeautyViewModel faceBeautyViewModelA1 = FaceBeautyRenderFragment.a1(FaceBeautyRenderFragment.this);
            faceBeautyViewModelA1.G.a(new ImageStrokeStateParam(ImageStrokeState.BEGIN));
            faceBeautyViewModelA1.K.postValue(Boolean.TRUE);
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.i
        public final void c() {
            FaceBeautyViewModel faceBeautyViewModelA1 = FaceBeautyRenderFragment.a1(FaceBeautyRenderFragment.this);
            faceBeautyViewModelA1.G.a(new ImageStrokeStateParam(ImageStrokeState.END));
            faceBeautyViewModelA1.K.postValue(Boolean.FALSE);
            FaceBeautyMenuEntity faceBeautyMenuEntityX = faceBeautyViewModelA1.x(faceBeautyViewModelA1.F);
            if (faceBeautyMenuEntityX != null) {
                og.b bVarV = FaceBeautyViewModel.v(faceBeautyViewModelA1, faceBeautyMenuEntityX);
                int i10 = faceBeautyViewModelA1.f7697w + 1;
                faceBeautyViewModelA1.f7697w = i10;
                bVarV.f15684h = i10;
                faceBeautyViewModelA1.z().c(bVarV);
            }
        }

        @Override // com.miui.mediaeditor.algo.beauty.common.core.widgets.i
        public final void d(float f, float f10) {
            FaceBeautyViewModel faceBeautyViewModelA1 = FaceBeautyRenderFragment.a1(FaceBeautyRenderFragment.this);
            faceBeautyViewModelA1.G.a(new HandBeautyCoordinateParam(f, f10));
        }
    }

    public static final class d implements Observer, kotlin.jvm.internal.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ l f7749a;

        public d(l lVar) {
            this.f7749a = lVar;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Observer) || !(obj instanceof kotlin.jvm.internal.c)) {
                return false;
            }
            return kotlin.jvm.internal.e.a(this.f7749a, ((kotlin.jvm.internal.c) obj).getFunctionDelegate());
        }

        @Override // kotlin.jvm.internal.c
        public final yq.a<?> getFunctionDelegate() {
            return this.f7749a;
        }

        public final int hashCode() {
            return this.f7749a.hashCode();
        }

        @Override // androidx.p002lifecycle.Observer
        public final /* synthetic */ void onChanged(Object obj) {
            this.f7749a.invoke(obj);
        }
    }

    public static final /* synthetic */ FaceBeautyViewModel a1(FaceBeautyRenderFragment faceBeautyRenderFragment) {
        return faceBeautyRenderFragment.Y0();
    }

    @Override // com.miui.mediaeditor.photo.base.AbstractTrackFragment
    public final List<Map<String, Object>> F0() {
        com.miui.mediaeditor.beauty.module.facebeauty.b bVar = Y0().f7699y;
        bVar.getClass();
        ArrayList arrayList = new ArrayList();
        Integer num = bVar.f7702n;
        if (num == null || num.intValue() != 0 || kotlin.jvm.internal.e.a(bVar.G, Boolean.TRUE)) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16363", bVar.G, bVar.f7702n, null, null, 24));
        }
        Integer num2 = bVar.r;
        if (num2 == null || num2.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16362", null, bVar.r, null, null, 26));
        }
        Integer num3 = bVar.s;
        if (num3 == null || num3.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16364", null, bVar.s, null, null, 26));
        }
        Integer num4 = bVar.v;
        if (num4 == null || num4.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16367", null, bVar.v, null, null, 26));
        }
        Integer num5 = bVar.f7703w;
        if (num5 == null || num5.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16366", null, bVar.f7703w, null, null, 26));
        }
        Integer num6 = bVar.f7704x;
        if (num6 == null || num6.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16368", null, bVar.f7704x, null, null, 26));
        }
        Integer num7 = bVar.f7705y;
        if (num7 == null || num7.intValue() != 0 || kotlin.jvm.internal.e.a(bVar.H, Boolean.TRUE)) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16361", bVar.H, bVar.f7705y, null, null, 24));
        }
        Integer num8 = bVar.z;
        if (num8 == null || num8.intValue() != 0 || kotlin.jvm.internal.e.a(bVar.I, Boolean.TRUE)) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16369", bVar.I, bVar.z, null, null, 24));
        }
        Integer num9 = bVar.A;
        if (num9 == null || num9.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16370", null, bVar.A, null, null, 26));
        }
        Integer num10 = bVar.D;
        if (num10 == null || num10.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16371", null, bVar.D, null, null, 26));
        }
        Integer num11 = bVar.E;
        if (num11 == null || num11.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.16365", null, bVar.E, null, null, 26));
        }
        Integer num12 = bVar.F;
        if (num12 == null || num12.intValue() != 0) {
            arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.19039", null, bVar.F, null, null, 26));
        }
        Integer[] numArr = bVar.K;
        if (numArr != null) {
            for (Integer num13 : numArr) {
                arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.19041", "age", bVar.d.get(num13), bVar.J, null, 16));
            }
        }
        Integer[] numArr2 = bVar.L;
        if (numArr2 != null) {
            for (Integer num14 : numArr2) {
                arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.19041", "gender", bVar.f7701e.get(num14), bVar.J, null, 16));
            }
        }
        Integer[] numArr3 = bVar.M;
        if (numArr3 != null) {
            for (Integer num15 : numArr3) {
                arrayList.add(AbstractTrackFragment.a.Q(bVar, "639.2.12.1.19041", "skin", bVar.m.get(num15), bVar.J, null, 16));
            }
        }
        return arrayList;
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final void G0() {
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final View K0() {
        return this.O;
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final boolean M0() {
        return true;
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    /* JADX INFO: renamed from: P0, reason: from getter */
    public final RenderData getW() {
        return this.W;
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final void Q0() {
        MagnifierBeautyGlView magnifierBeautyGlView = this.P;
        if (magnifierBeautyGlView == null) {
            return;
        }
        magnifierBeautyGlView.setVisibility(0);
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final void R0(boolean z, final RenderFragment.c cVar) {
        if (!z) {
            b1();
            cVar.f();
            return;
        }
        FaceBeautyViewModel faceBeautyViewModelY0 = Y0();
        faceBeautyViewModelY0.z.removeObservers(getViewLifecycleOwner());
        FaceBeautyViewModel faceBeautyViewModelY1 = Y0();
        FaceBeautyMenuEntity faceBeautyMenuEntityX = faceBeautyViewModelY1.x(faceBeautyViewModelY1.F);
        if (faceBeautyMenuEntityX != null) {
            og.b bVarV = FaceBeautyViewModel.v(faceBeautyViewModelY1, faceBeautyMenuEntityX);
            bVarV.d = false;
            faceBeautyViewModelY1.C(bVarV, false);
        }
        final MagnifierBeautyGlView magnifierBeautyGlView = this.P;
        if (magnifierBeautyGlView != null) {
            FaceBeautyViewModel faceBeautyViewModelY2 = Y0();
            l<com.miui.mediaeditor.algo.beauty.common.d, yq.d> lVar = new l<com.miui.mediaeditor.algo.beauty.common.d, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$prepareImageBeforeExit$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // fr.l
                public final yq.d invoke(com.miui.mediaeditor.algo.beauty.common.d dVar) {
                    com.miui.mediaeditor.algo.beauty.common.d exportData = dVar;
                    kotlin.jvm.internal.e.f(exportData, "exportData");
                    FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                    FaceBeautyRenderData faceBeautyRenderData = new FaceBeautyRenderData(exportData);
                    faceBeautyRenderData.f8288e = exportData.f7421c;
                    faceBeautyRenderFragment.W = faceBeautyRenderData;
                    MagnifierBeautyGlView magnifierBeautyGlView2 = magnifierBeautyGlView;
                    final FaceBeautyRenderFragment faceBeautyRenderFragment2 = this.this$0;
                    final RenderFragment.c cVar2 = cVar;
                    magnifierBeautyGlView2.post(new Runnable() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.g
                        @Override // java.lang.Runnable
                        public final void run() {
                            FaceBeautyRenderFragment this$0 = faceBeautyRenderFragment2;
                            kotlin.jvm.internal.e.f(this$0, "this$0");
                            RenderFragment.c beforeExitTransitionStart = cVar2;
                            kotlin.jvm.internal.e.f(beforeExitTransitionStart, "$beforeExitTransitionStart");
                            int i10 = FaceBeautyRenderFragment.f7745b0;
                            this$0.b1();
                            beforeExitTransitionStart.f();
                        }
                    });
                    return yq.d.f18754a;
                }
            };
            faceBeautyViewModelY2.G.e(new BeautyExportDataParam(com.miui.mediaeditor.algo.beauty.common.b.c.f7374b, new WeakReference(magnifierBeautyGlView)), new com.miui.mediaeditor.aigc.download.b(2, lVar));
        }
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final void U0(Bitmap bitmap) {
        ImageView imageView = this.O;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        ImageView imageView2 = this.O;
        if (imageView2 != null && imageView2.getVisibility() != 0) {
            imageView2.setVisibility(0);
        }
        MagnifierBeautyGlView magnifierBeautyGlView = this.P;
        if (magnifierBeautyGlView == null || magnifierBeautyGlView.getVisibility() == 8) {
            return;
        }
        magnifierBeautyGlView.setVisibility(8);
    }

    @Override // com.miui.mediaeditor.photo.base.RenderVMFragment
    public final void Z0() {
        Y0().r.observe(getViewLifecycleOwner(), new d(new l<Bitmap, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$1
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Bitmap bitmap) {
                Bitmap bitmap2 = bitmap;
                ColorSpace colorSpace = of.d.f15680a;
                kotlin.jvm.internal.e.e(bitmap2, "bitmap");
                Bitmap bitmapA = of.d.a(bitmap2);
                FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                FaceBeautyRenderFragment.a1(faceBeautyRenderFragment);
                ImageView imageView = faceBeautyRenderFragment.O;
                if (imageView != null) {
                    imageView.setImageBitmap(bitmapA);
                }
                faceBeautyRenderFragment.X0();
                MagnifierBeautyGlView magnifierBeautyGlView = faceBeautyRenderFragment.P;
                if (magnifierBeautyGlView != null) {
                    Bitmap bmpBack = ga.b.d(ContextCompat.getDrawable(faceBeautyRenderFragment.requireContext(), R.drawable.beauty_face_magnifying_glass_frame), 1024, 1024);
                    faceBeautyRenderFragment.T = faceBeautyRenderFragment.getResources().getDimensionPixelSize(R.dimen.editor_menu_beauty_face_magnifier_size);
                    faceBeautyRenderFragment.U = faceBeautyRenderFragment.getResources().getDimensionPixelSize(R.dimen.editor_menu_beauty_face_magnifier_margin_left);
                    faceBeautyRenderFragment.V = faceBeautyRenderFragment.getResources().getDimensionPixelSize(R.dimen.editor_menu_beauty_face_magnifier_margin_top);
                    kotlin.jvm.internal.e.e(bmpBack, "bmpBack");
                    magnifierBeautyGlView.setMagnifierInfo(new h(bmpBack, faceBeautyRenderFragment.T, faceBeautyRenderFragment.U, faceBeautyRenderFragment.V));
                    com.miui.mediaeditor.algo.beauty.common.b.c cVar = com.miui.mediaeditor.algo.beauty.common.b.c.f7374b;
                    magnifierBeautyGlView.d(bitmap2, ProcessMode.f1321e);
                    magnifierBeautyGlView.setEnabled(false);
                    magnifierBeautyGlView.getRenderListeners().add(faceBeautyRenderFragment.Y);
                    magnifierBeautyGlView.setOnHandleTouchListener(faceBeautyRenderFragment.f7746a0);
                }
                return yq.d.f18754a;
            }
        }));
        Y0().E.observe(getViewLifecycleOwner(), new d(new l<List<? extends FaceBeautyMenuEntity>, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$2
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(List<? extends FaceBeautyMenuEntity> list) {
                MagnifierBeautyGlView magnifierBeautyGlView;
                List<? extends FaceBeautyMenuEntity> it = list;
                kotlin.jvm.internal.e.e(it, "it");
                FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                for (FaceBeautyMenuEntity faceBeautyMenuEntity : it) {
                    if (FaceBeautyRenderFragment.a1(faceBeautyRenderFragment).B(faceBeautyMenuEntity) && faceBeautyMenuEntity.f7713y == 0 && !faceBeautyRenderFragment.S && (magnifierBeautyGlView = faceBeautyRenderFragment.P) != null) {
                        magnifierBeautyGlView.b(new e(faceBeautyRenderFragment, 0));
                    }
                }
                return yq.d.f18754a;
            }
        }));
        FaceBeautyViewModel faceBeautyViewModelY0 = Y0();
        faceBeautyViewModelY0.A.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$3
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                if (!faceBeautyRenderFragment.S) {
                    FunctionBaseViewModel.r(faceBeautyRenderFragment.Y0());
                    ThreadManager.f5574a.getValue().f491c.execute(new f(this.this$0, 0));
                }
                return yq.d.f18754a;
            }
        }));
        FaceBeautyViewModel faceBeautyViewModelY1 = Y0();
        faceBeautyViewModelY1.z.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$4
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                MagnifierBeautyGlView magnifierBeautyGlView;
                Boolean it = bool;
                kotlin.jvm.internal.e.e(it, "it");
                if (it.booleanValue() && (magnifierBeautyGlView = this.this$0.P) != null) {
                    magnifierBeautyGlView.i();
                }
                return yq.d.f18754a;
            }
        }));
        this.s.r.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$5
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                boolean z = !bool.booleanValue();
                int i10 = FaceBeautyRenderFragment.f7745b0;
                faceBeautyRenderFragment.W0(z);
                return yq.d.f18754a;
            }
        }));
        Y0().f8130n.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$6
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                Boolean it = bool;
                ImageView imageView = this.this$0.N;
                if (imageView != null) {
                    kotlin.jvm.internal.e.e(it, "it");
                    imageView.setEnabled(it.booleanValue());
                }
                return yq.d.f18754a;
            }
        }));
        Y0().m.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$7
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                Boolean it = bool;
                ImageView imageView = this.this$0.M;
                if (imageView != null) {
                    kotlin.jvm.internal.e.e(it, "it");
                    imageView.setEnabled(it.booleanValue());
                }
                return yq.d.f18754a;
            }
        }));
        Y0().f8129e.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$8
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                Boolean it = bool;
                ImageView imageView = this.this$0.L;
                if (imageView != null) {
                    kotlin.jvm.internal.e.e(it, "it");
                    imageView.setEnabled(it.booleanValue());
                }
                return yq.d.f18754a;
            }
        }));
        Y0().L.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$9
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                Boolean it = bool;
                kotlin.jvm.internal.e.e(it, "it");
                boolean zBooleanValue = it.booleanValue();
                FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                if (zBooleanValue) {
                    ImageView imageView = faceBeautyRenderFragment.L;
                    if (imageView != null && imageView.getVisibility() != 0) {
                        imageView.setVisibility(0);
                    }
                } else {
                    ImageView imageView2 = faceBeautyRenderFragment.L;
                    if (imageView2 != null && imageView2.getVisibility() != 4) {
                        imageView2.setVisibility(4);
                    }
                }
                return yq.d.f18754a;
            }
        }));
        Y0().M.observe(getViewLifecycleOwner(), new d(new l<Boolean, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$10
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Boolean bool) {
                Boolean it = bool;
                kotlin.jvm.internal.e.e(it, "it");
                boolean zBooleanValue = it.booleanValue();
                FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                if (zBooleanValue) {
                    ImageView imageView = faceBeautyRenderFragment.M;
                    if (imageView != null && imageView.getVisibility() != 0) {
                        imageView.setVisibility(0);
                    }
                } else {
                    ImageView imageView2 = faceBeautyRenderFragment.M;
                    if (imageView2 != null && imageView2.getVisibility() != 4) {
                        imageView2.setVisibility(4);
                    }
                }
                return yq.d.f18754a;
            }
        }));
        Y0().N.observe(getViewLifecycleOwner(), new d(new l<Integer, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$11
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(Integer num) {
                Integer it = num;
                FaceBeautyViewModel faceBeautyViewModelA1 = FaceBeautyRenderFragment.a1(this.this$0);
                kotlin.jvm.internal.e.e(it, "it");
                int iIntValue = it.intValue();
                double d10 = ((double) iIntValue) * (kotlin.jvm.internal.e.a(faceBeautyViewModelA1.F, og.c.a.f15685b) ? 0.01d : 0.007d);
                faceBeautyViewModelA1.G.a(new SetImagePointSizeParam(d10));
                kotlinx.coroutines.internal.a.o("FaceBeautyVM", "setPaintSizeMultiple() called with: multiple = " + iIntValue + ", currentMenuType = " + faceBeautyViewModelA1.F + ", mPaintSize = " + d10);
                MagnifierBeautyGlView magnifierBeautyGlView = this.this$0.P;
                if (magnifierBeautyGlView != null) {
                    magnifierBeautyGlView.setMPaintSize(d10);
                }
                return yq.d.f18754a;
            }
        }));
        Y0().D.observe(getViewLifecycleOwner(), new d(new l<FaceBeautyMenuEntity, yq.d>() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.FaceBeautyRenderFragment$observeData$12
            {
                super(1);
            }

            @Override // fr.l
            public final yq.d invoke(FaceBeautyMenuEntity faceBeautyMenuEntity) {
                OperateType operateType;
                FaceBeautyMenuEntity it = faceBeautyMenuEntity;
                FaceBeautyViewModel faceBeautyViewModelA1 = FaceBeautyRenderFragment.a1(this.this$0);
                kotlin.jvm.internal.e.e(it, "it");
                if (faceBeautyViewModelA1.B(it) && it.f7713y == 0) {
                    FaceBeautyRenderFragment faceBeautyRenderFragment = this.this$0;
                    if (!faceBeautyRenderFragment.S) {
                        FunctionBaseViewModel.r(faceBeautyRenderFragment.Y0());
                        ThreadManager.f5574a.getValue().f491c.execute(new c(this.this$0, 0));
                    }
                }
                MagnifierBeautyGlView magnifierBeautyGlView = this.this$0.P;
                if (magnifierBeautyGlView != null) {
                    if (it.f7712x) {
                        vh.c cVar = it.f7711w;
                        operateType = cVar != null ? cVar.m : null;
                    } else {
                        operateType = (OperateType) it.v.d;
                    }
                    magnifierBeautyGlView.setOperateType(operateType);
                }
                return yq.d.f18754a;
            }
        }));
    }

    public final void b1() {
        m.s(new StringBuilder("releaseRender, needRelease="), this.R, "FaceBeautyRF");
        if (this.R) {
            MagnifierBeautyGlView magnifierBeautyGlView = this.P;
            if (magnifierBeautyGlView != null) {
                magnifierBeautyGlView.b(new e(this, 1));
            }
            this.R = false;
        }
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment, dk.f
    public final boolean isEmpty() {
        return !Y0().z().a();
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment, w9.b.a
    public final void j() {
        L0();
        Context contextRequireContext = requireContext();
        kotlin.jvm.internal.e.e(contextRequireContext, "requireContext()");
        w9.a.a(new wh.b(contextRequireContext), getView());
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        kotlin.jvm.internal.e.f(inflater, "inflater");
        Context context = inflater.getContext();
        kotlin.jvm.internal.e.e(context, "inflater.context");
        return new wh.b(context);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        CopyOnWriteArrayList<com.miui.mediaeditor.algo.beauty.common.core.widgets.e> renderListeners;
        kotlinx.coroutines.internal.a.o("FaceBeautyRF", "fragment onDestroy");
        MagnifierBeautyGlView magnifierBeautyGlView = this.P;
        if (magnifierBeautyGlView != null && (renderListeners = magnifierBeautyGlView.getRenderListeners()) != null) {
            renderListeners.remove(this.Y);
        }
        FaceBeautyViewModel faceBeautyViewModelY0 = Y0();
        FaceBeautyMenuEntity faceBeautyMenuEntityX = faceBeautyViewModelY0.x(faceBeautyViewModelY0.F);
        if (faceBeautyMenuEntityX != null) {
            og.b bVarV = FaceBeautyViewModel.v(faceBeautyViewModelY0, faceBeautyMenuEntityX);
            bVarV.d = false;
            faceBeautyViewModelY0.C(bVarV, false);
        }
        b1();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onPause() {
        MagnifierBeautyGlView magnifierBeautyGlView;
        super.onPause();
        if (!this.R || (magnifierBeautyGlView = this.P) == null) {
            return;
        }
        magnifierBeautyGlView.f();
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment, androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        MagnifierBeautyGlView magnifierBeautyGlView = this.P;
        if (magnifierBeautyGlView != null) {
            magnifierBeautyGlView.g();
        }
    }

    @Override // com.miui.mediaeditor.photo.base.RenderVMFragment, com.miui.mediaeditor.photo.base.RenderFragment, com.miui.mediaeditor.photo.base.AbstractTrackFragment, androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        kotlin.jvm.internal.e.f(view, "view");
        super.onViewCreated(view, bundle);
        this.Q = (ViewGroup) view.findViewById(R.id.render_layout);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_placeholder);
        this.O = imageView;
        a9.a.a(R.string.photo_editor_transition_between_fragments_image_view, imageView);
        MagnifierBeautyGlView magnifierBeautyGlView = (MagnifierBeautyGlView) view.findViewById(R.id.gl_view);
        this.P = magnifierBeautyGlView;
        if (magnifierBeautyGlView != null && magnifierBeautyGlView.getVisibility() != 4) {
            magnifierBeautyGlView.setVisibility(4);
        }
        ImageView imageView2 = (ImageView) view.findViewById(R.id.operation_compare);
        T0(this.X);
        this.N = imageView2;
        this.L = (ImageView) view.findViewById(R.id.operation_revert);
        this.M = (ImageView) view.findViewById(R.id.operation_revoke);
        ImageView imageView3 = this.L;
        if (imageView3 != null) {
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i10 = FaceBeautyRenderFragment.f7745b0;
                    FaceBeautyRenderFragment this$0 = this.d;
                    kotlin.jvm.internal.e.f(this$0, "this$0");
                    this$0.Y0().z().d();
                }
            });
        }
        ImageView imageView4 = this.M;
        if (imageView4 != null) {
            imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.miui.mediaeditor.beauty.module.facebeauty.render.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i10 = FaceBeautyRenderFragment.f7745b0;
                    FaceBeautyRenderFragment this$0 = this.d;
                    kotlin.jvm.internal.e.f(this$0, "this$0");
                    this$0.Y0().z().e();
                }
            });
        }
        FunctionBaseViewModel.r(Y0());
        Y0().r.setValue(this.f8145e);
    }
}
