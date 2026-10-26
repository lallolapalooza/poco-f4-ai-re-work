package com.mibi.ocr.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.mipay.common.base.BaseFragment;
import com.mipay.common.data.e0;
import com.mipay.common.data.i0;
import com.mipay.common.data.k0;
import com.mipay.common.data.v0;
import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.payment.entryData.CameraIntentData;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CaptureFragment extends BaseFragment {
    private static final String k0 = "CaptureFragment";
    public static final int l0 = 1;
    private Bundle S;
    private LinkedList<CameraIntentData> T;
    private CameraIntentData U;
    private CameraPreview V;
    private PreviewMaskView W;
    private TextView X;
    private Button Y;
    private Button Z;
    private View a0;
    private f b0;
    private a.b.a.c c0;
    private int e0;
    private final String[] d0 = {"android.permission.CAMERA"};
    private Handler f0 = new a();
    private View.OnClickListener g0 = new b();
    private View.OnClickListener h0 = new c();
    private View.OnClickListener i0 = new d();
    private final a.b.a.c.b j0 = new e();

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            String str = (String) message.obj;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            CaptureFragment.this.h(str);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaptureFragment.this.S.putString(Integer.toString(CaptureFragment.this.U.f3574a), CaptureFragment.this.X.getText().toString());
            CaptureFragment captureFragment = CaptureFragment.this;
            captureFragment.c(-1, captureFragment.S);
            CaptureFragment.this.H();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaptureFragment.this.i(false);
        }
    }

    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CaptureFragment.this.S.putString(Integer.toString(CaptureFragment.this.U.f3574a), CaptureFragment.this.X.getText().toString());
            CaptureFragment.this.i(true);
        }
    }

    class e implements a.b.a.c.b {
        e() {
        }

        @Override // a.b.a.c.b
        public void a(String str) {
            CaptureFragment.this.c0.f();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Message messageObtainMessage = CaptureFragment.this.f0.obtainMessage();
            messageObtainMessage.obj = str;
            messageObtainMessage.what = 1;
            CaptureFragment.this.f0.sendMessage(messageObtainMessage);
        }
    }

    class f extends OrientationEventListener {
        public f(Context context) {
            super(context);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i) {
            int i2;
            if (v0.d()) {
                if ((i == 90 || i == 180 || i == 270 || i == 0) && (i2 = (i + 90) % 360) != CaptureFragment.this.c0.c()) {
                    CaptureFragment.this.c0.a(i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(String str) {
        this.a0.setVisibility(0);
        this.X.setText(str);
        this.c0.f();
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void A() {
        super.A();
        this.c0.a();
    }

    @Override // com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void C() {
        super.C();
        if (com.mipay.common.data.d.B() >= 10) {
            e0.a(k0, "rollback navigation bar setting");
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (attributes.flags == this.e0) {
                return;
            }
            window.clearFlags(134217728);
            if (attributes.flags == this.e0) {
                return;
            }
            window.clearFlags(Integer.MIN_VALUE);
        }
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void D() {
        super.D();
        this.c0.i();
        this.b0.disable();
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void E() {
        super.E();
        if (!k0.b(getActivity(), this.d0) || i0.a((Context) getActivity())) {
            return;
        }
        if (!this.c0.h()) {
            H();
        } else {
            i(this.U == null);
            this.b0.enable();
        }
    }

    @Override // com.mipay.common.base.BaseFragment
    protected int U() {
        return a.b.a.e.q.mibi_incognito_mode_dialog_summary_camera;
    }

    @Override // com.mipay.common.base.BaseFragment
    protected String W() {
        return getString(a.b.a.e.q.mibi_permanently_denied_perm_camera);
    }

    @Override // com.mipay.common.base.BaseFragment
    protected String[] X() {
        return this.d0;
    }

    @Override // com.mipay.common.base.BaseFragment
    public boolean f0() {
        return true;
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void i(Bundle bundle) {
        super.i(bundle);
        this.S = new Bundle();
        this.Z.setOnClickListener(this.h0);
        this.b0 = new f(getActivity());
        this.c0.a(this.W.a(this.c0.d()));
        this.V.setPreviewCallback(this.c0.b());
        this.c0.a(this.j0);
    }

    @Override // com.mipay.common.base.BaseFragment
    protected void i0() {
        super.i0();
        e0.a(k0, "permission denied");
        H();
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void j(Bundle bundle) {
        super.j(bundle);
        f(false);
        a(this.d0);
        this.c0 = new a.b.a.c(getActivity());
    }

    @Override // com.mipay.common.base.BaseFragment
    protected void l(Bundle bundle) {
        super.l(bundle);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(IEntry.f3559b);
        if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
            throw new IllegalArgumentException("camera data is empty");
        }
        this.T = new LinkedList<>();
        this.T.addAll(parcelableArrayList);
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.StepFragment
    public void n() {
        if (!this.S.isEmpty()) {
            c(-1, this.S);
        }
        super.n();
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void a(Activity activity) {
        super.a(activity);
        if (com.mipay.common.data.d.B() >= 10) {
            e0.a(k0, "opt miui 12 navigation bar");
            Window window = getActivity().getWindow();
            this.e0 = window.getAttributes().flags;
            window.addFlags(Integer.MIN_VALUE);
            window.addFlags(134217728);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(boolean z) {
        if (z && !this.T.isEmpty()) {
            this.U = this.T.pop();
        }
        if (this.U == null) {
            return;
        }
        this.X.setTextSize(0, getResources().getDimension(a.b.a.e.g.mibi_text_size_camera_prompt));
        this.X.setText(this.U.f3575b);
        this.c0.a(this.U.h);
        if (!this.T.isEmpty()) {
            this.Y.setText(a.b.a.e.q.mibi_button_next);
            this.Y.setOnClickListener(this.i0);
        } else {
            this.Y.setText(a.b.a.e.q.mibi_button_confirm);
            this.Y.setOnClickListener(this.g0);
        }
        this.a0.setVisibility(8);
        this.c0.g();
    }

    @Override // com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(a.b.a.e.m.mibi_capture, (ViewGroup) null);
        this.a0 = viewInflate.findViewById(a.b.a.e.j.buttons_contain);
        this.X = (TextView) viewInflate.findViewById(a.b.a.e.j.result);
        this.W = (PreviewMaskView) viewInflate.findViewById(a.b.a.e.j.mask_view);
        this.V = (CameraPreview) viewInflate.findViewById(a.b.a.e.j.preview);
        this.Y = (Button) viewInflate.findViewById(a.b.a.e.j.confirm);
        this.Z = (Button) viewInflate.findViewById(a.b.a.e.j.retry);
        return viewInflate;
    }
}
