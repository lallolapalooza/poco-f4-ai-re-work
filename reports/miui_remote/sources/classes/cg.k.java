package cg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.f1;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.duokan.phone.remotecontroller.R;
import com.xiaomi.ai.SpeechResult;
import e.k0;
import java.util.ArrayList;
import java.util.List;
import wf.m0;
import wf.r;

/* JADX INFO: loaded from: classes3.dex */
public class k extends androidx.fragment.app.b implements View.OnClickListener, l.f {

    /* JADX INFO: renamed from: e7, reason: collision with root package name */
    public static final String f12183e7 = "VoiceControlActivity";

    /* JADX INFO: renamed from: f7, reason: collision with root package name */
    public static final int f12184f7 = 2001;

    /* JADX INFO: renamed from: g7, reason: collision with root package name */
    public static final int f12185g7 = 2002;

    /* JADX INFO: renamed from: h7, reason: collision with root package name */
    public static final int f12186h7 = 2003;

    /* JADX INFO: renamed from: i7, reason: collision with root package name */
    public static final int f12187i7 = 2004;

    /* JADX INFO: renamed from: j7, reason: collision with root package name */
    public static final int f12188j7 = 2005;
    public View P6;
    public View Q6;
    public View R6;
    public TextView S6;
    public TextView T6;
    public TextView U6;
    public View V6;
    public RecyclerView W6;
    public b[] X;
    public LayoutAnimationController X6;
    public FrameLayout Y;
    public boolean Y6;
    public View Z;
    public View Z6;

    /* JADX INFO: renamed from: a7, reason: collision with root package name */
    public View f12189a7;

    /* JADX INFO: renamed from: b7, reason: collision with root package name */
    public ViewGroup f12190b7;

    /* JADX INFO: renamed from: c7, reason: collision with root package name */
    public View f12191c7;

    /* JADX INFO: renamed from: d7, reason: collision with root package name */
    public Handler f12192d7 = new a();

    public class a extends Handler {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            if (k.this.Y6 || k.this.V6.getVisibility() == 0) {
                return;
            }
            k.this.f12191c7.setVisibility(0);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(800L);
            k.this.f12191c7.startAnimation(alphaAnimation);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i10;
            View view;
            super.handleMessage(message);
            String str = "";
            switch (message.what) {
                case k.f12184f7 /* 2001 */:
                    k.this.Y();
                    return;
                case k.f12185g7 /* 2002 */:
                    k.this.c0();
                    k kVar = k.this;
                    kVar.Y6 = false;
                    if (kVar.S6.getVisibility() != 0) {
                        k.this.Z.setSelected(false);
                    } else {
                        k.this.b0();
                        k.this.Z();
                    }
                    break;
                case k.f12186h7 /* 2003 */:
                    break;
                case k.f12187i7 /* 2004 */:
                    k.this.f0();
                    k.this.T();
                    k.this.f12192d7.postDelayed(new Runnable() { // from class: cg.j
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f12182a.b();
                        }
                    }, 2000L);
                    Object obj = message.obj;
                    if (obj == null || !(obj instanceof String)) {
                        k.this.T6.setText("");
                        return;
                    } else {
                        k.this.Z6.setVisibility(0);
                        k.this.T6.setText((String) message.obj);
                        return;
                    }
                case k.f12188j7 /* 2005 */:
                    k.this.Y6 = false;
                    k.this.Z.setSelected(false);
                    k.this.c0();
                    k.this.T();
                    k.this.d0();
                    Object obj2 = message.obj;
                    if (obj2 != null && (obj2 instanceof String)) {
                        str = (String) obj2;
                    }
                    if (k.this.S6.getVisibility() != 0 || k.this.S6.getText().length() <= 0) {
                        k.this.U6.setVisibility(0);
                        k.this.U6.setText(str);
                        i10 = 4;
                        k.this.S6.setVisibility(4);
                        view = k.this.Z6;
                    } else {
                        k.this.Z6.setVisibility(0);
                        k.this.T6.setText(str);
                        view = k.this.f12190b7;
                        i10 = 8;
                    }
                    view.setVisibility(i10);
                    return;
                default:
                    return;
            }
            Object obj3 = message.obj;
            if (obj3 == null || !(obj3 instanceof String) || TextUtils.isEmpty((String) obj3)) {
                return;
            }
            k.this.S6.setVisibility(0);
            k.this.S6.setText((String) message.obj);
            k.this.T();
        }
    }

    public class b extends View {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f12194a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f12195b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f12196c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f12197d;

        public b(Context context, int i10, int i11, boolean z10, int i12) {
            super(context);
            this.f12195b = i10;
            this.f12196c = i11;
            this.f12194a = z10;
            this.f12197d = i12;
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int measuredWidth = getMeasuredWidth() / 2;
            int measuredHeight = getMeasuredHeight() / 2;
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(this.f12195b);
            if (!this.f12194a) {
                paint.setStrokeWidth(this.f12197d);
                paint.setStyle(Paint.Style.STROKE);
            }
            canvas.drawCircle(measuredWidth, measuredHeight, this.f12196c, paint);
        }
    }

    public class c extends RecyclerView.g<a> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String[] f12199c;

        public class a extends RecyclerView.d0 {
            public TextView I;

            public a(View view) {
                super(view);
                this.I = (TextView) view.findViewById(R.id.text_view_tip);
            }
        }

        public c(String[] strArr) {
            this.f12199c = strArr;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public void N(a aVar, int i10) {
            aVar.I.setText(this.f12199c[i10]);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
        public a P(ViewGroup viewGroup, int i10) {
            return new a(View.inflate(viewGroup.getContext(), R.layout.voice_ctrl_tip_item_view, null));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int x() {
            return this.f12199c.length;
        }
    }

    public class d extends RecyclerView.g<a> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List<e> f12201c;

        public class a extends RecyclerView.d0 {
            public ImageView I;
            public TextView J;

            public a(View view) {
                super(view);
                this.I = (ImageView) view.findViewById(R.id.item_icon);
                this.J = (TextView) view.findViewById(R.id.item_text);
            }
        }

        public d() {
            ArrayList arrayList = new ArrayList();
            this.f12201c = arrayList;
            arrayList.add(k.this.new e(R.drawable.ic_voice_help_settopbox, R.array.voice_stb_help));
            this.f12201c.add(k.this.new e(R.drawable.ic_voice_help_aircondition, R.array.voice_ac_help));
            this.f12201c.add(k.this.new e(R.drawable.ic_voice_help_tv, R.array.voice_tv_help));
            this.f12201c.add(k.this.new e(R.drawable.ic_voice_help_fan, R.array.voice_fan_help));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public void N(a aVar, int i10) {
            e eVar = this.f12201c.get(i10);
            aVar.I.setImageResource(eVar.f12203a);
            String[] stringArray = k.this.getContext().getResources().getStringArray(eVar.f12204b);
            StringBuilder sb2 = new StringBuilder();
            for (String str : stringArray) {
                sb2.append(str);
                sb2.append("\n");
            }
            aVar.J.setText(sb2.toString().trim());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
        public a P(ViewGroup viewGroup, int i10) {
            return new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.voice_ctrl_tip_detail_item_view, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int x() {
            return this.f12201c.size();
        }
    }

    public class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f12203a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f12204b;

        public e(int i10, int i11) {
            this.f12203a = i10;
            this.f12204b = i11;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean U(View view) {
        if (this.Y6) {
            return true;
        }
        Toast toastMakeText = Toast.makeText(getContext().getApplicationContext(), R.string.voice_tips_long_pressed, 0);
        toastMakeText.setGravity(80, 0, 500);
        toastMakeText.show();
        m0.b.f64481a.f(getContext());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void V() {
        if (this.Y6) {
            return;
        }
        this.Z.setSelected(false);
        c0();
        d0();
    }

    public final void T() {
        if (this.V6.getVisibility() == 0) {
            this.V6.setVisibility(8);
        }
    }

    public final void W() {
        this.W6.setAdapter(new d());
        this.V6.setVisibility(0);
        this.R6.setVisibility(8);
        this.Z6.setVisibility(4);
        this.U6.setVisibility(4);
        this.S6.setVisibility(4);
        this.f12191c7.setVisibility(4);
    }

    public final void X() {
        if (this.X6 == null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setDuration(800L);
            LayoutAnimationController layoutAnimationController = new LayoutAnimationController(alphaAnimation);
            this.X6 = layoutAnimationController;
            this.W6.setLayoutAnimation(layoutAnimationController);
        }
        this.V6.setVisibility(0);
        this.W6.startLayoutAnimation();
        this.R6.setVisibility(0);
        this.Z6.setVisibility(4);
        this.U6.setVisibility(4);
        this.S6.setVisibility(4);
        this.f12191c7.setVisibility(4);
    }

    public final void Y() {
        try {
            if (this.X == null) {
                int[] iArr = new int[2];
                this.Z.getLocationOnScreen(iArr);
                int measuredWidth = this.Z.getMeasuredWidth();
                int i10 = measuredWidth / 2;
                int i11 = measuredWidth / 2;
                int i12 = measuredWidth / 2;
                this.X = new b[2];
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(measuredWidth, measuredWidth);
                layoutParams.topMargin = iArr[1];
                layoutParams.leftMargin = iArr[0];
                for (int i13 = 0; i13 < this.X.length; i13++) {
                    b bVar = new b(getContext(), -16749059, i12, true, 0);
                    bVar.setVisibility(4);
                    this.Y.addView(bVar, layoutParams);
                    this.X[i13] = bVar;
                }
            }
            int i14 = 0;
            while (true) {
                b[] bVarArr = this.X;
                if (i14 >= bVarArr.length) {
                    return;
                }
                bVarArr[i14].setVisibility(0);
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.voice_ctrl_input_anim);
                animationLoadAnimation.setStartOffset(i14 * 300);
                this.X[i14].startAnimation(animationLoadAnimation);
                i14++;
            }
        } catch (Exception unused) {
        }
    }

    public final void Z() {
        this.f12190b7.setVisibility(0);
        this.f12189a7.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.voice_ctrl_scale_fade_ir_anim));
        this.Z6.setVisibility(0);
        this.T6.setText("");
    }

    public final void a0() {
        l.l().o();
        this.Z.setSelected(true);
        this.S6.setVisibility(4);
        this.Z6.setVisibility(4);
        this.f12191c7.setVisibility(4);
        this.U6.setVisibility(4);
        this.f12192d7.sendEmptyMessageDelayed(f12184f7, 200L);
        this.Y6 = true;
    }

    @Override // cg.l.f
    public void b(String str) {
        l.l().n(str);
        this.f12192d7.sendMessage(this.f12192d7.obtainMessage(f12187i7, str));
    }

    public final void b0() {
        b[] bVarArr = this.X;
        if (bVarArr == null || bVarArr.length == 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            b[] bVarArr2 = this.X;
            if (i10 >= bVarArr2.length) {
                return;
            }
            if (i10 == 0) {
                bVarArr2[i10].setVisibility(0);
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.voice_ctrl_scale_fade_anim);
                animationLoadAnimation.setStartOffset(i10 * 300);
                this.X[i10].startAnimation(animationLoadAnimation);
            } else {
                bVarArr2[i10].setVisibility(8);
            }
            i10++;
        }
    }

    @Override // cg.l.f
    public void c(SpeechResult speechResult) {
        speechResult.getAction();
        speechResult.getQuery();
        speechResult.getAnswerText();
        speechResult.getAnswer();
        this.f12192d7.sendMessage(this.f12192d7.obtainMessage(f12185g7, speechResult.getQuery()));
    }

    public final void c0() {
        b[] bVarArr = this.X;
        if (bVarArr == null || bVarArr.length == 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            b[] bVarArr2 = this.X;
            if (i10 >= bVarArr2.length) {
                return;
            }
            bVarArr2[i10].clearAnimation();
            this.X[i10].setVisibility(4);
            i10++;
        }
    }

    public final void d0() {
        this.f12189a7.clearAnimation();
    }

    public final void e0() {
        l.l().q();
        c0();
        this.Y6 = false;
    }

    @Override // cg.l.f
    public void f(SpeechResult speechResult) {
        speechResult.getAction();
        speechResult.getQuery();
        speechResult.getAnswerText();
        speechResult.getAnswer();
        this.f12192d7.sendMessage(this.f12192d7.obtainMessage(f12186h7, speechResult.getQuery()));
    }

    public final void f0() {
        b[] bVarArr = this.X;
        if (bVarArr == null || bVarArr.length == 0) {
            return;
        }
        this.f12192d7.postDelayed(new Runnable() { // from class: cg.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f12180a.V();
            }
        }, f1.f3055k);
    }

    @Override // cg.l.f
    public void g(String str) {
        l.l().n(str);
        Message messageObtainMessage = this.f12192d7.obtainMessage(f12188j7, str);
        this.f12192d7.removeMessages(f12184f7);
        this.f12192d7.sendMessage(messageObtainMessage);
    }

    @Override // cg.l.f
    public void i() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View view2 = this.Z;
        if (view != view2) {
            if (view == this.P6) {
                j();
                return;
            } else {
                if (view == this.Q6) {
                    W();
                    return;
                }
                return;
            }
        }
        if (!view2.isSelected()) {
            a0();
            return;
        }
        e0();
        d0();
        f0();
        this.Z.setSelected(false);
        this.Z6.setVisibility(4);
        this.U6.setVisibility(4);
        this.f12191c7.setVisibility(4);
        this.S6.setVisibility(4);
    }

    @Override // androidx.fragment.app.b, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        u(0, R.style.RCAppThemeLight);
        this.Y6 = false;
    }

    @Override // androidx.fragment.app.Fragment
    @k0
    public View onCreateView(LayoutInflater layoutInflater, @k0 ViewGroup viewGroup, @k0 Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_voice_control, viewGroup);
        View viewFindViewById = viewInflate.findViewById(R.id.success_group);
        this.Z6 = viewFindViewById;
        viewFindViewById.setVisibility(4);
        this.T6 = (TextView) viewInflate.findViewById(R.id.success_text);
        this.U6 = (TextView) viewInflate.findViewById(R.id.fail_text);
        TextView textView = (TextView) viewInflate.findViewById(R.id.result_text);
        this.S6 = textView;
        textView.setVisibility(4);
        View viewFindViewById2 = viewInflate.findViewById(R.id.btn_voice);
        this.Z = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this);
        this.Z.setOnLongClickListener(new View.OnLongClickListener() { // from class: cg.i
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f12181a.U(view);
            }
        });
        View viewFindViewById3 = viewInflate.findViewById(R.id.btn_close);
        this.P6 = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this);
        View viewFindViewById4 = viewInflate.findViewById(R.id.btn_help);
        this.Q6 = viewFindViewById4;
        viewFindViewById4.setOnClickListener(this);
        this.Y = (FrameLayout) viewInflate.findViewById(R.id.anim_container);
        this.f12191c7 = viewInflate.findViewById(R.id.ir_control_tips);
        this.V6 = viewInflate.findViewById(R.id.voice_tips_view);
        this.R6 = viewInflate.findViewById(R.id.tips_title_textview);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.tips_list_view);
        this.W6 = recyclerView;
        recyclerView.setAdapter(new c(getResources().getStringArray(R.array.voice_tips)));
        this.W6.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f12190b7 = (ViewGroup) viewInflate.findViewById(R.id.ir_anim);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.f12190b7.addView(new b(getContext(), -115914, 18, true, 0), layoutParams);
        b bVar = new b(getContext(), -115914, 19, false, 2);
        this.f12189a7 = bVar;
        this.f12190b7.addView(bVar, layoutParams);
        View decorView = getActivity().getWindow().getDecorView();
        decorView.destroyDrawingCache();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        if (drawingCache != null) {
            try {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawingCache.getWidth(), drawingCache.getHeight(), Bitmap.Config.ARGB_8888);
                r.a(drawingCache, bitmapCreateBitmap, 50);
                this.Y.setBackground(new BitmapDrawable(getResources(), bitmapCreateBitmap));
            } catch (Exception unused) {
            }
        }
        decorView.destroyDrawingCache();
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        l.l().r(this);
        e0();
        this.Z.setSelected(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (vd.d.p()) {
            l.l().m(this);
        } else {
            j();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @k0 Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (vd.d.p()) {
            X();
            a0();
        }
    }

    @Override // androidx.fragment.app.b
    public void x(androidx.fragment.app.h hVar, String str) {
        try {
            super.x(hVar, str);
        } catch (IllegalStateException unused) {
        }
    }
}
