package com.miui.mediaeditor.photo.remover;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.p002lifecycle.ViewModelProvider;
import com.airbnb.lottie.LottieAnimationView;
import com.miui.gallery.editor.photo.app.remover.Inpaint;
import com.miui.gallery.editor.photo.app.s;
import com.miui.gallery.util.g;
import com.miui.mediaeditor.R;
import com.miui.mediaeditor.photo.core.render.RenderData;
import com.miui.mediaeditor.photo.mosaic.k;
import com.miui.mediaeditor.photo.remover.model.RemoverPaintData;
import com.miui.mediaeditor.photo.remover.model.menu.RemoverData;
import com.miui.mediaeditor.photo.remover.widgets.RemoverGestureView;
import com.miui.mediaeditor.photo.widgets.UndoRedoCompareView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import wj.h;
import wj.i;

/* JADX INFO: loaded from: classes2.dex */
public class RemoverRenderFragment extends AbstractRemoverFragment implements i {
    public static final /* synthetic */ int Z = 0;
    public RemoverGestureView K;
    public LottieAnimationView L;
    public final ArrayList M;
    public RemoverPaintData N;
    public final ListIterator<RemoverPaintData> O;
    public float P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public final Handler U;
    public final d V;
    public boolean W;
    public RemoverFunctionViewModel X;
    public final b Y;

    public class a implements wj.a {
        public a() {
        }

        @Override // wj.a
        public final void c() {
            RemoverRenderFragment.this.K.d(false);
        }

        @Override // wj.a
        public final void w() {
            RemoverRenderFragment.this.K.d(true);
            h.a("639.2.5.1.14645");
        }
    }

    public class b implements RemoverGestureView.c {
        public b() {
        }

        public final void a(RemoverPaintData removerPaintData) {
            RemoverRenderFragment removerRenderFragment = RemoverRenderFragment.this;
            removerRenderFragment.U.removeCallbacks(removerRenderFragment.V);
            removerRenderFragment.L.setVisibility(8);
            if (removerPaintData != null) {
                removerRenderFragment.N = removerPaintData;
                int i10 = (removerRenderFragment.S + 1) % 10;
                removerRenderFragment.S = i10;
                removerRenderFragment.R = i10;
                kotlinx.coroutines.internal.a.m(Integer.valueOf(i10), "RemoverRenderFragment", "recordCurrent  mRecordCurr : %d");
                int i11 = removerRenderFragment.S;
                if (i11 == removerRenderFragment.Q) {
                    removerRenderFragment.Q = (i11 + 1) % 10;
                }
                ListIterator<RemoverPaintData> listIterator = removerRenderFragment.O;
                if (listIterator.hasNext()) {
                    listIterator.next();
                    listIterator.set(removerRenderFragment.N);
                    removerRenderFragment.T = listIterator.previousIndex();
                } else {
                    listIterator.add(removerRenderFragment.N);
                    removerRenderFragment.T = listIterator.previousIndex();
                }
                removerRenderFragment.K.o();
                removerRenderFragment.V0(8);
                boolean z = removerRenderFragment.S != removerRenderFragment.Q;
                UndoRedoCompareView undoRedoCompareView = removerRenderFragment.v;
                if (undoRedoCompareView != null) {
                    undoRedoCompareView.a(z, false, z);
                }
            }
            removerRenderFragment.K.setIsProcessing(false);
            UndoRedoCompareView undoRedoCompareView2 = removerRenderFragment.v;
            if (undoRedoCompareView2 != null) {
                undoRedoCompareView2.d.setClickable(true);
                undoRedoCompareView2.f9658e.setClickable(true);
            }
        }
    }

    public class c implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return true;
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            RemoverRenderFragment.this.L.setVisibility(0);
        }
    }

    public RemoverRenderFragment() {
        ArrayList arrayList = new ArrayList();
        this.M = arrayList;
        this.O = arrayList.listIterator();
        this.Q = 0;
        this.S = 0;
        this.U = new Handler(new c());
        this.V = new d();
        this.Y = new b();
    }

    @Override // com.miui.mediaeditor.photo.base.AbstractTrackFragment
    public final List<Map<String, Object>> F0() {
        return new ArrayList();
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final void G0() {
        this.K.getClass();
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final View K0() {
        return this.K;
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment
    public final RenderData P0() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.M;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            for (int i10 = 0; i10 <= this.T; i10++) {
                arrayList.add((RemoverPaintData) arrayList2.get(i10));
            }
        }
        return new RemoverRenderData(arrayList);
    }

    @Override // com.miui.mediaeditor.photo.remover.AbstractRemoverFragment
    public final void Y0(float f) {
        this.P = f;
        RemoverGestureView removerGestureView = this.K;
        if (removerGestureView != null) {
            int i10 = (int) f;
            if (removerGestureView.K.e()) {
                return;
            }
            removerGestureView.O.setStrokeWidth(i10);
        }
    }

    @Override // com.miui.mediaeditor.photo.remover.AbstractRemoverFragment
    public final void Z0(RemoverData removerData) {
        int i10 = removerData.f8920n;
        if (i10 == 0) {
            RemoverGestureView removerGestureView = this.K;
            RemoverGestureView.ElementType elementType = RemoverGestureView.ElementType.FREE;
            if (removerGestureView.K.e()) {
                return;
            }
            removerGestureView.K = elementType;
            return;
        }
        if (i10 == 1) {
            RemoverGestureView removerGestureView2 = this.K;
            RemoverGestureView.ElementType elementType2 = RemoverGestureView.ElementType.LINE;
            if (removerGestureView2.K.e()) {
                return;
            }
            removerGestureView2.K = elementType2;
        }
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment, dk.f
    public final boolean isEmpty() {
        ArrayList arrayList = this.M;
        return arrayList == null || arrayList.isEmpty();
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment, w9.b.a
    public final void j() {
        w9.a.a(new k(getContext(), 1), getView());
    }

    @Override // wj.i
    public final void k() throws Throwable {
        int i10 = (this.S - 1) % 10;
        this.S = i10;
        if (i10 < 0) {
            this.S = i10 + 10;
        }
        kotlinx.coroutines.internal.a.m(Integer.valueOf(this.S), "RemoverRenderFragment", "previousRecord  mRecordCurr : %d");
        ListIterator<RemoverPaintData> listIterator = this.O;
        if (listIterator.hasPrevious()) {
            listIterator.previous();
            this.T = listIterator.previousIndex();
        }
        RemoverGestureView removerGestureView = this.K;
        int i11 = (removerGestureView.L - 1) % 10;
        removerGestureView.L = i11;
        if (i11 < 0) {
            removerGestureView.L = i11 + 10;
        }
        removerGestureView.n();
        removerGestureView.invalidate();
        int i12 = this.S;
        boolean z = i12 != this.Q;
        boolean z10 = i12 != this.R;
        UndoRedoCompareView undoRedoCompareView = this.v;
        if (undoRedoCompareView != null) {
            undoRedoCompareView.a(z, z10, z);
        }
    }

    @Override // wj.i
    public final void l() throws Throwable {
        this.S = (this.S + 1) % 10;
        ListIterator<RemoverPaintData> listIterator = this.O;
        if (listIterator.hasNext()) {
            listIterator.next();
            this.T = listIterator.previousIndex();
        }
        RemoverGestureView removerGestureView = this.K;
        removerGestureView.L = (removerGestureView.L + 1) % 10;
        removerGestureView.n();
        removerGestureView.invalidate();
        int i10 = this.S;
        boolean z = i10 != this.Q;
        boolean z10 = i10 != this.R;
        UndoRedoCompareView undoRedoCompareView = this.v;
        if (undoRedoCompareView != null) {
            undoRedoCompareView.a(z, z10, z);
        }
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment, com.miui.gallery.app.fragment.AndroidFragment, com.miui.mediaeditor.app.fragment.CommonFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.X = (RemoverFunctionViewModel) new ViewModelProvider(requireParentFragment()).get(RemoverFunctionViewModel.class);
        Inpaint.initialize();
        this.W = g.c(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new k(layoutInflater.getContext(), 1);
    }

    @Override // com.miui.mediaeditor.photo.base.RenderFragment, com.miui.mediaeditor.photo.base.AbstractTrackFragment, androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) throws Throwable {
        super.onViewCreated(view, bundle);
        this.K = (RemoverGestureView) view.findViewById(R.id.remover_gesture_view);
        this.L = (LottieAnimationView) view.findViewById(R.id.progress);
        this.K.setBitmap(this.f8145e);
        this.K.setRemoverCallback(this.Y);
        RemoverGestureView removerGestureView = this.K;
        int i10 = (int) this.P;
        if (!removerGestureView.K.e()) {
            removerGestureView.O.setStrokeWidth(i10);
        }
        UndoRedoCompareView undoRedoCompareView = this.v;
        if (undoRedoCompareView != null) {
            undoRedoCompareView.setRenderRecordListener(this);
        }
        T0(new a());
        this.X.d.observe(getViewLifecycleOwner(), new s(this, 5));
    }

    @Override // wj.i
    public final void x() {
        RemoverGestureView removerGestureView = this.K;
        for (int i10 = 0; i10 < 10; i10++) {
            File fileL = removerGestureView.l(i10);
            if (fileL != null && fileL.exists() && !fileL.delete()) {
                kotlinx.coroutines.internal.a.y("RemoverGestureView", "Failed to delete beauty record file: " + fileL.getPath());
            }
        }
        removerGestureView.getClass();
    }
}
