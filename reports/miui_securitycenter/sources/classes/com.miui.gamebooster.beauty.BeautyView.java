package com.miui.gamebooster.beauty;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.miui.maml.folme.AnimatedProperty;
import com.miui.securitycenter.R;
import f5.f;
import f5.n;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.property.ViewProperty;
import miuix.appcompat.internal.app.widget.ActionBarMovableLayout;
import w9.h;

/* JADX INFO: loaded from: classes2.dex */
public class BeautyView extends FrameLayout implements View.OnClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f10235c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ViewGroup f10236d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ViewGroup f10237e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ViewGroup f10238f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ViewGroup f10239g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Resources f10240h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ImageView f10241i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ImageView f10242j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private ImageView f10243k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ImageView f10244l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ImageView f10245m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private TextView f10246n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private View f10247o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private View f10248p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private View f10249q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private BeautySmallView f10250r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private AnimatorSet f10251s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private Handler f10252t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private c f10253u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private Runnable f10254v;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BeautyView beautyView = BeautyView.this;
            beautyView.o(beautyView.f10250r, BeautyView.this.f10247o, true);
        }
    }

    class b implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f10256c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ View f10257d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f10258e;

        b(View view, View view2, boolean z10) {
            this.f10256c = view;
            this.f10257d = view2;
            this.f10258e = z10;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            View view = this.f10256c;
            if (view != null) {
                view.setAlpha(1.0f);
            }
            View view2 = this.f10257d;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            BeautyView.this.f10235c = 2;
            f.m0(BeautyView.this.f10235c);
            if (this.f10258e) {
                return;
            }
            BeautyView.this.f10252t.postDelayed(BeautyView.this.f10254v, 5000L);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            View view = this.f10256c;
            if (view != null) {
                view.setAlpha(0.0f);
                this.f10256c.setVisibility(0);
            }
            View view2 = this.f10257d;
            if (view2 != null) {
                view2.setVisibility(0);
            }
        }
    }

    public interface c {
        void a();

        void b(f5.a aVar);
    }

    public BeautyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10252t = new Handler(Looper.getMainLooper());
        this.f10254v = new a();
        i(context);
    }

    private void h(View view, View view2, int i10, int i11, int i12, int i13) {
        Folme.useAt(view).visible().hide(new AnimConfig().setEase(-2, 0.9f, 0.3f));
        Folme.useAt(view2).visible().show(new AnimConfig().setEase(-2, 0.95f, 0.2f));
        IStateStyle iStateStyleState = Folme.useAt(view2).state();
        AnimState animState = new AnimState("start");
        ViewProperty viewProperty = ViewProperty.WIDTH;
        AnimState animStateAdd = animState.add(viewProperty, i10);
        ViewProperty viewProperty2 = ViewProperty.HEIGHT;
        AnimState animStateAdd2 = animStateAdd.add(viewProperty2, i11);
        ViewProperty viewProperty3 = ViewProperty.ALPHA;
        iStateStyleState.fromTo(animStateAdd2.add(viewProperty3, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), new AnimState("end").add(viewProperty, i12).add(viewProperty2, i13).add(viewProperty3, 1.0d), new AnimConfig().setEase(-2, 0.9f, 0.2f));
    }

    private void i(Context context) {
        this.f10240h = context.getResources();
        if (f.S()) {
            j(true);
        }
    }

    private void k() {
        View view = this.f10247o;
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = this.f10240h.getDimensionPixelOffset(R.dimen.beauty_pannel_width_normal);
        this.f10247o.setLayoutParams(layoutParams);
    }

    private void l() {
        k();
        int i10 = this.f10235c;
        if (i10 == 1) {
            this.f10247o.setVisibility(0);
            this.f10250r.setVisibility(8);
        } else if (i10 == 2) {
            this.f10247o.setVisibility(8);
            this.f10250r.setVisibility(0);
        }
        f fVarO = f.o();
        n.d(f.F() && fVarO.A(), fVarO.B(), f.Y() && fVarO.D(), f.W() && fVarO.C());
    }

    private void m() {
        this.f10252t.removeCallbacks(this.f10254v);
        this.f10252t.postDelayed(this.f10254v, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(View view, View view2, boolean z10) {
        View view3 = this.f10248p;
        if (view3 == null || view3.getVisibility() != 0) {
            AnimatorSet animatorSet = this.f10251s;
            if (animatorSet == null || !animatorSet.isRunning()) {
                l7.b bVar = new l7.b();
                bVar.a(0.8f).b(0.5f);
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.setDuration(1000);
                animatorSet2.setInterpolator(bVar);
                if (z10) {
                    animatorSet2.playTogether(ObjectAnimator.ofFloat(view, AnimatedProperty.PROPERTY_NAME_SCALE_X, 0.3f, 1.0f), ObjectAnimator.ofFloat(view, AnimatedProperty.PROPERTY_NAME_SCALE_Y, 0.3f, 1.0f), ObjectAnimator.ofPropertyValuesHolder(view2, PropertyValuesHolder.ofFloat(AnimatedProperty.PROPERTY_NAME_SCALE_X, 1.0f, 0.3f), PropertyValuesHolder.ofFloat(AnimatedProperty.PROPERTY_NAME_PIVOT_X, 0.0f), PropertyValuesHolder.ofFloat(AnimatedProperty.PROPERTY_NAME_SCALE_Y, 1.0f, 0.3f), PropertyValuesHolder.ofFloat(AnimatedProperty.PROPERTY_NAME_PIVOT_Y, 0.0f)));
                } else {
                    animatorSet2.playTogether(ObjectAnimator.ofFloat(view, AnimatedProperty.PROPERTY_NAME_SCALE_X, 0.3f, 1.0f), ObjectAnimator.ofFloat(view, AnimatedProperty.PROPERTY_NAME_SCALE_Y, 0.3f, 1.0f), ObjectAnimator.ofFloat(view2, AnimatedProperty.PROPERTY_NAME_SCALE_X, 1.0f, 0.3f), ObjectAnimator.ofFloat(view2, AnimatedProperty.PROPERTY_NAME_SCALE_Y, 1.0f, 0.3f));
                }
                AnimatorSet animatorSet3 = new AnimatorSet();
                new l7.b().a(0.9f).b(0.15f);
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, "alpha", 1.0f, 0.0f);
                objectAnimatorOfFloat.setStartDelay(100L);
                long j10 = ActionBarMovableLayout.DEFAULT_SPRING_BACK_DURATION;
                objectAnimatorOfFloat.setDuration(j10);
                objectAnimatorOfFloat2.setDuration(j10);
                animatorSet3.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2);
                animatorSet3.play(objectAnimatorOfFloat2);
                animatorSet3.addListener(new b(view, view2, z10));
                AnimatorSet animatorSet4 = new AnimatorSet();
                this.f10251s = animatorSet4;
                animatorSet4.play(animatorSet3).with(animatorSet2);
                this.f10251s.start();
            }
        }
    }

    private void p() {
        boolean z10 = f.F() && f.o().A();
        h.n(z10 ? 0 : 8, this.f10236d);
        if (z10) {
            f.p0();
        }
        ImageView imageView = this.f10241i;
        if (imageView != null) {
            imageView.setSelected(f.G());
        }
    }

    private void q() {
        boolean zB = f.o().B();
        if (zB) {
            f.s0();
        }
        h.n(zB ? 0 : 8, this.f10237e);
        ImageView imageView = this.f10242j;
        if (imageView != null) {
            imageView.setSelected(f.o().J());
        }
        boolean zK = f.K();
        TextView textView = this.f10246n;
        if (textView != null) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, zK ? R.drawable.gb_ic_beauty_light_triangle : 0, 0);
        }
    }

    private void r() {
        boolean z10 = f.W() && f.o().C();
        h.n(z10 ? 0 : 8, this.f10239g);
        if (z10) {
            f.u0();
        }
        ImageView imageView = this.f10244l;
        if (imageView != null) {
            imageView.setSelected(f.U());
        }
    }

    private void s() {
        boolean z10 = f.Y() && f.o().D();
        if (z10) {
            f.B0();
        } else {
            ViewGroup viewGroup = this.f10237e;
            if (viewGroup != null) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewGroup.getLayoutParams();
                layoutParams.setMargins(0, layoutParams.topMargin, 0, 0);
                this.f10237e.setLayoutParams(layoutParams);
            }
        }
        h.n(z10 ? 0 : 8, this.f10238f);
        ImageView imageView = this.f10243k;
        if (imageView != null) {
            imageView.setSelected(f.X(f.o().k()));
        }
    }

    private void t() {
        BeautySmallView beautySmallView = this.f10250r;
        if (beautySmallView != null) {
            beautySmallView.e();
        }
    }

    public void j(boolean z10) {
        f.o().L0(z10, f.o().g(), f.p());
    }

    public void n() {
        if (this.f10235c != 1) {
            return;
        }
        o(this.f10250r, this.f10247o, true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == R.id.iv_ligth_back) {
            h(this.f10248p, this.f10247o, getResources().getDimensionPixelOffset(R.dimen.dp_171), getResources().getDimensionPixelOffset(R.dimen.dp_235), this.f10247o.getWidth(), this.f10247o.getHeight());
            m();
        }
        if (id2 == R.id.layout_small) {
            o(this.f10247o, this.f10250r, false);
            return;
        }
        if (id2 == R.id.tv_beauty_light) {
            f.o();
            if (f.K()) {
                this.f10252t.removeCallbacks(null);
                AnimatorSet animatorSet = this.f10251s;
                if (animatorSet != null) {
                    animatorSet.cancel();
                }
                View view2 = this.f10247o;
                h(view2, this.f10248p, view2.getWidth(), this.f10247o.getHeight(), getResources().getDimensionPixelOffset(R.dimen.dp_171), getResources().getDimensionPixelOffset(R.dimen.dp_235));
                return;
            }
            return;
        }
        switch (id2) {
            case R.id.iv_beauty_face /* 2131428774 */:
                f.J0(!f.G());
                p();
                t();
                m();
                n.f();
                break;
            case R.id.iv_beauty_light /* 2131428775 */:
                f.o().L0(!f.o().J(), f.o().g(), f.p());
                q();
                t();
                m();
                c cVar = this.f10253u;
                if (cVar != null) {
                    cVar.a();
                }
                n.g();
                break;
            case R.id.iv_beauty_pc /* 2131428776 */:
                boolean zU = f.U();
                f.v0(!zU);
                f.N0(!zU);
                r();
                t();
                n.h();
                break;
            case R.id.iv_beauty_privacy /* 2131428777 */:
                f5.a aVarK = f.o().k();
                boolean zX = f.X(aVarK);
                f.o().P0(!zX, aVarK);
                f.o().O0(!zX);
                s();
                t();
                m();
                c cVar2 = this.f10253u;
                if (cVar2 != null) {
                    cVar2.b(aVarK);
                }
                n.i();
                break;
            case R.id.iv_beauty_settings /* 2131428778 */:
                n.c();
                Intent intent = new Intent(getContext(), (Class<?>) BeautySettingsActivity.class);
                intent.addFlags(335544320);
                getContext().startActivity(intent);
                break;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f.t0(f.o().J());
        if (f.o().J()) {
            j(false);
        }
        Handler handler = this.f10252t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f10235c = f.h();
        this.f10247o = findViewById(R.id.layout_normal);
        this.f10236d = (ViewGroup) findViewById(R.id.layout_face);
        this.f10237e = (ViewGroup) findViewById(R.id.layout_light);
        this.f10238f = (ViewGroup) findViewById(R.id.layout_privacy);
        this.f10239g = (ViewGroup) findViewById(R.id.layout_pc);
        this.f10241i = (ImageView) findViewById(R.id.iv_beauty_face);
        this.f10242j = (ImageView) findViewById(R.id.iv_beauty_light);
        this.f10246n = (TextView) findViewById(R.id.tv_beauty_light);
        this.f10243k = (ImageView) findViewById(R.id.iv_beauty_privacy);
        this.f10244l = (ImageView) findViewById(R.id.iv_beauty_pc);
        this.f10245m = (ImageView) findViewById(R.id.iv_beauty_settings);
        l7.a.a(this.f10241i);
        l7.a.a(this.f10242j);
        l7.a.a(this.f10243k);
        l7.a.a(this.f10244l);
        this.f10248p = findViewById(R.id.layout_light_content);
        View viewFindViewById = findViewById(R.id.iv_ligth_back);
        this.f10249q = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        BeautySmallView beautySmallView = (BeautySmallView) findViewById(R.id.layout_small);
        this.f10250r = beautySmallView;
        beautySmallView.setOnClickListener(this);
        this.f10243k.setOnClickListener(this);
        this.f10241i.setOnClickListener(this);
        this.f10246n.setOnClickListener(this);
        this.f10242j.setOnClickListener(this);
        this.f10244l.setOnClickListener(this);
        this.f10245m.setOnClickListener(this);
        l();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i10, int i11) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i10), Integer.MIN_VALUE), i11);
    }

    public void setOnStatusChangeListener(c cVar) {
        this.f10253u = cVar;
    }

    public void u() {
        p();
        q();
        s();
        r();
        BeautySmallView beautySmallView = this.f10250r;
        if (beautySmallView != null) {
            beautySmallView.e();
        }
    }
}
