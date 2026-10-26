package com.yxcorp.gifshow.live.push.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kwai.imsdk.internal.util.BitmapUtil;
import com.yxcorp.download.DownloadManager;
import com.yxcorp.download.DownloadTask;
import com.yxcorp.gifshow.download.KwaiDownloadListener;
import com.yxcorp.gifshow.live.push.event.AudioEffectPlayEvent;
import com.yxcorp.gifshow.music.utils.MusicUtils;
import com.yxcorp.gifshow.recycler.widget.CustomRecyclerView;
import com.yxcorp.gifshow.recycler.widget.KwaiStaggeredGridLayoutManager;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes8.dex */
public class LiveAudioEffectFragment extends com.yxcorp.gifshow.fragment.g {
    private CustomRecyclerView C;
    private OnAudioEffectListener D;
    private List<com.yxcorp.gifshow.model.c> E;
    private b F;
    private OnDismissListener G;
    private View H;
    private View I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    private boolean f45845J;
    private boolean K;

    public interface OnAudioEffectListener {
        void onMusicEffectSelected(com.yxcorp.gifshow.model.c cVar, int i12);
    }

    public interface OnDismissListener extends DialogInterface.OnDismissListener {
        void onDismiss(DialogInterface dialogInterface, boolean z12);
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private OnAudioEffectListener f45846a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<com.yxcorp.gifshow.model.c> f45847b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private OnDismissListener f45848c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f45849d;

        public LiveAudioEffectFragment a() {
            LiveAudioEffectFragment liveAudioEffectFragment = new LiveAudioEffectFragment();
            liveAudioEffectFragment.D = this.f45846a;
            liveAudioEffectFragment.E = this.f45847b;
            liveAudioEffectFragment.G = this.f45848c;
            liveAudioEffectFragment.f45845J = this.f45849d;
            return liveAudioEffectFragment;
        }

        public a b(@e.a List<com.yxcorp.gifshow.model.c> list) {
            this.f45847b = list;
            return this;
        }

        public a c(boolean z12) {
            this.f45849d = z12;
            return this;
        }

        public a d(OnAudioEffectListener onAudioEffectListener) {
            this.f45846a = onAudioEffectListener;
            return this;
        }

        public a e(OnDismissListener onDismissListener) {
            this.f45848c = onDismissListener;
            return this;
        }
    }

    public class b extends RecyclerView.g<C0739b> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private List<com.yxcorp.gifshow.model.c> f45850c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f45851d = -1;

        class a extends KwaiDownloadListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ com.yxcorp.gifshow.model.c f45853a;

            a(com.yxcorp.gifshow.model.c cVar) {
                this.f45853a = cVar;
            }

            @Override // com.yxcorp.gifshow.download.KwaiDownloadListener, com.yxcorp.download.DownloadListener
            public void completed(DownloadTask downloadTask) {
                if (com.yxcorp.utility.n1.h(this.f45853a.mUrl, downloadTask.getUrl())) {
                    b.this.l();
                }
            }
        }

        /* JADX INFO: renamed from: com.yxcorp.gifshow.live.push.ui.LiveAudioEffectFragment$b$b, reason: collision with other inner class name */
        public class C0739b extends RecyclerView.u {
            private ImageView B;
            private ProgressBar C;

            /* JADX INFO: renamed from: u, reason: collision with root package name */
            private ViewGroup f45855u;

            /* JADX INFO: renamed from: w, reason: collision with root package name */
            private AppCompatTextView f45856w;

            public C0739b(b bVar, View view) {
                super(view);
                this.f45855u = (ViewGroup) view.findViewById(m10.m.K0);
                this.f45856w = (AppCompatTextView) view.findViewById(m10.m.I1);
                this.B = (ImageView) view.findViewById(mj0.b.T6);
                this.C = (ProgressBar) view.findViewById(m10.m.f79759n1);
            }
        }

        public b(List<com.yxcorp.gifshow.model.c> list) {
            this.f45850c = list;
            if (list == null) {
                this.f45850c = new ArrayList();
            }
        }

        private void I(com.yxcorp.gifshow.model.c cVar, KwaiDownloadListener kwaiDownloadListener) {
            MusicUtils.p(cVar, J(cVar), kwaiDownloadListener);
        }

        private String J(com.yxcorp.gifshow.model.c cVar) {
            if (com.yxcorp.utility.n1.p(cVar.mPath)) {
                cVar.mPath = pd.a.b().a(".audio_effect").getPath() + File.separator + com.yxcorp.gifshow.util.j1.j(cVar.mUrl, BitmapUtil.MP3_SUFFIX);
            }
            return cVar.mPath;
        }

        @e.a
        private com.yxcorp.gifshow.model.c K(int i12) {
            return this.f45850c.get(i12);
        }

        private boolean M(com.yxcorp.gifshow.model.c cVar) {
            File file = new File(J(cVar));
            Integer taskId = DownloadManager.getInstance().getTaskId(cVar.mUrl);
            return file.exists() && (taskId == null || !DownloadManager.getInstance().isRunning(taskId.intValue()));
        }

        private boolean N(com.yxcorp.gifshow.model.c cVar) {
            Integer taskId = DownloadManager.getInstance().getTaskId(cVar.mUrl);
            return taskId != null && DownloadManager.getInstance().isRunning(taskId.intValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void O(com.yxcorp.gifshow.model.c cVar, C0739b c0739b, int i12, View view) {
            if (!M(cVar)) {
                com.yxcorp.utility.y1.N(c0739b.B, 8, false);
                com.yxcorp.utility.y1.N(c0739b.C, 0, false);
                I(cVar, new a(cVar));
            } else {
                this.f45851d = i12;
                l();
                if (LiveAudioEffectFragment.this.D != null) {
                    LiveAudioEffectFragment.this.D.onMusicEffectSelected(cVar, i12);
                }
            }
        }

        public com.yxcorp.gifshow.model.c L() {
            return K(this.f45851d);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
        public void w(final C0739b c0739b, final int i12) {
            final com.yxcorp.gifshow.model.c cVarK = K(i12);
            c0739b.f45856w.setText(cVarK.mName);
            if (M(cVarK)) {
                com.yxcorp.utility.y1.N(c0739b.B, 8, false);
                com.yxcorp.utility.y1.N(c0739b.C, 8, false);
                if (this.f45851d == i12) {
                    c0739b.f4319a.setSelected(true);
                } else {
                    c0739b.f4319a.setSelected(false);
                }
            } else {
                c0739b.f4319a.setSelected(false);
                if (N(cVarK)) {
                    com.yxcorp.utility.y1.N(c0739b.B, 8, false);
                    com.yxcorp.utility.y1.N(c0739b.C, 0, false);
                } else {
                    com.yxcorp.utility.y1.N(c0739b.B, 0, false);
                    com.yxcorp.utility.y1.N(c0739b.C, 8, false);
                }
            }
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.yxcorp.gifshow.live.push.ui.e0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f46045a.O(cVarK, c0739b, i12, view);
                }
            };
            c0739b.f45856w.setOnClickListener(onClickListener);
            c0739b.B.setOnClickListener(onClickListener);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public C0739b y(ViewGroup viewGroup, int i12) {
            return new C0739b(this, LayoutInflater.from(viewGroup.getContext()).inflate(mj0.c.f80735s, viewGroup, false));
        }

        public void R() {
            int i12 = this.f45851d;
            this.f45851d = -1;
            m(i12);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int e() {
            if (com.yxcorp.utility.i.d(this.f45850c)) {
                return 0;
            }
            return this.f45850c.size();
        }
    }

    private void s2(View view) {
        View viewFindViewById = view.findViewById(m10.m.f79719f1);
        this.H = viewFindViewById;
        com.jakewharton.rxbinding2.view.i.a(viewFindViewById).throttleFirst(300L, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.yxcorp.gifshow.live.push.ui.d0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                this.f46029a.t2(obj);
            }
        });
        this.I = view.findViewById(m10.m.f79698b0);
        this.C = (CustomRecyclerView) view.findViewById(m10.m.f79775q2);
        this.C.setLayoutManager(new KwaiStaggeredGridLayoutManager(2, 0));
        this.C.addItemDecoration(new bn0.a(getContext(), 2, com.yxcorp.utility.y1.b(uo.a.c(), 12.0f), 0, false));
        b bVar = new b(this.E);
        this.F = bVar;
        this.C.setAdapter(bVar);
        if (!this.f45845J) {
            com.yxcorp.utility.y1.N(this.H, 8, false);
            com.yxcorp.utility.y1.N(this.I, 8, false);
        }
        this.K = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t2(Object obj) throws Exception {
        u2();
    }

    private void u2() {
        this.K = true;
        dismissAllowingStateLoss();
    }

    @Override // com.yxcorp.gifshow.fragment.i, androidx.fragment.app.t, androidx.fragment.app.DialogFragment
    @e.a
    public Dialog onCreateDialog(Bundle bundle) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return super.onCreateDialog(bundle);
        }
        ag0.b bVar = new ag0.b(getActivity());
        bVar.k(com.yxcorp.utility.g0.c(getActivity().getWindow()));
        return bVar;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@e.a LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(m10.p.f79829h, viewGroup, false);
    }

    @Override // o20.a, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (org.greenrobot.eventbus.c.e().m(this)) {
            org.greenrobot.eventbus.c.e().w(this);
        }
    }

    @Override // com.yxcorp.gifshow.fragment.i, androidx.fragment.app.t, androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        OnDismissListener onDismissListener = this.G;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface, this.K);
        }
    }

    @org.greenrobot.eventbus.k(threadMode = ThreadMode.MAIN)
    public void onEvent(AudioEffectPlayEvent audioEffectPlayEvent) {
        com.yxcorp.gifshow.model.c cVarL;
        if (audioEffectPlayEvent == null || this.F == null || getActivity() == null || audioEffectPlayEvent.status != 2 || (cVarL = this.F.L()) == null || !com.yxcorp.utility.n1.h(cVarL.mId, audioEffectPlayEvent.musicId)) {
            return;
        }
        this.F.R();
    }

    @Override // o20.a, androidx.fragment.app.Fragment
    public void onViewCreated(@e.a View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        s2(view);
        h2(false);
        org.greenrobot.eventbus.c.e().t(this);
    }
}
