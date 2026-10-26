package com.xiaomi.payment.channel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mipay.common.component.FormattableEditText;
import com.mipay.common.data.d0;
import com.mipay.common.data.g0;
import com.mipay.common.data.s;
import com.mipay.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.payment.entryData.CameraIntentData;
import com.xiaomi.payment.l.f;
import com.xiaomi.payment.o.l;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class VoucherFragment extends BaseProcessFragment implements com.xiaomi.payment.ui.h.a.b {
    private static String l0 = "VoucherFragment";
    private static int m0 = 31;
    private FormattableEditText W;
    private ImageView X;
    private Button Y;
    private TextView Z;
    private String a0;
    private boolean b0;
    private l c0;
    private long d0;
    private long e0;
    private ArrayList<Integer> f0;
    private String g0;
    private com.xiaomi.payment.l.c h0;
    private final String[] i0 = {"android.permission.CAMERA"};
    private View.OnClickListener j0 = new b();
    private View.OnClickListener k0 = new c();

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (VoucherFragment.this.h0 != null) {
                Bundle bundle = new Bundle();
                com.xiaomi.payment.entry.a aVarA = com.xiaomi.payment.entry.a.a();
                VoucherFragment voucherFragment = VoucherFragment.this;
                aVarA.a(voucherFragment, voucherFragment.h0, bundle, -1);
            }
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            VoucherFragment.this.p0();
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String strA = s.a(VoucherFragment.this.W.getText().toString().trim(), s.a.TYPE_NORMAL);
            if (TextUtils.isEmpty(strA) || strA.length() < VoucherFragment.this.d0 || strA.length() > VoucherFragment.this.e0) {
                Toast.makeText(VoucherFragment.this.getActivity(), com.xiaomi.payment.n.b.q.mibi_voucher_error_password, 0).show();
            } else {
                VoucherFragment.this.Y.setClickable(false);
                ((com.xiaomi.payment.channel.d.b) VoucherFragment.this.getPresenter()).a(strA);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0() {
        a(this.i0);
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void D() {
        super.D();
        g0.a(this, this.b0 ? "Pay:" : "Recharge:");
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void E() {
        super.E();
        this.Y.setClickable(true);
        g0.b(this, this.b0 ? "Pay:" : "Recharge:");
    }

    @Override // com.mipay.common.base.BaseFragment
    protected int U() {
        return com.xiaomi.payment.n.b.q.mibi_incognito_mode_dialog_summary_camera;
    }

    @Override // com.mipay.common.base.BaseFragment
    protected String W() {
        return getString(com.xiaomi.payment.n.b.q.mibi_permanently_denied_perm_camera);
    }

    @Override // com.mipay.common.base.BaseFragment
    protected String[] X() {
        return this.i0;
    }

    @Override // com.mipay.common.base.BaseFragment
    protected boolean f0() {
        return true;
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void i(Bundle bundle) {
        super.i(bundle);
        a(this.a0);
        this.Y.setOnClickListener(this.k0);
        if (com.xiaomi.payment.entry.a.a().a(getActivity(), f.Y1)) {
            this.X.setVisibility(0);
            this.X.setOnClickListener(this.j0);
        } else {
            this.X.setVisibility(4);
        }
        if (TextUtils.isEmpty(this.g0)) {
            this.Z.setVisibility(8);
            return;
        }
        this.Z.setVisibility(0);
        this.Z.getPaint().setFlags(8);
        this.Z.setText(this.g0);
        this.Z.setOnClickListener(new a());
    }

    @Override // com.mipay.common.base.BaseFragment
    protected void j0() {
        super.j0();
        String string = getResources().getString(com.xiaomi.payment.n.b.q.mibi_scan_prompt_voucher);
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        arrayList.add(new CameraIntentData(com.xiaomi.payment.n.b.j.voucher_edit, string, this.f0));
        bundle.putParcelableArrayList(IEntry.f3559b, arrayList);
        com.xiaomi.payment.entry.a.a().a(f.Y1, this, bundle, m0);
    }

    @Override // com.mipay.common.ui.fragment.BaseProcessFragment, com.mipay.common.base.BaseFragment
    protected void l(Bundle bundle) {
        super.l(bundle);
        this.c0 = (l) bundle.getSerializable(f.Z1);
        l lVar = this.c0;
        this.a0 = lVar.h;
        this.d0 = lVar.m;
        this.e0 = lVar.n;
        this.f0 = new ArrayList<>();
        for (long j = this.d0; j <= this.e0; j++) {
            this.f0.add(new Integer((int) j));
        }
        l lVar2 = this.c0;
        this.g0 = lVar2.i;
        this.h0 = lVar2.l;
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.t
    public com.mipay.common.base.s z() {
        return new com.xiaomi.payment.channel.d.b();
    }

    @Override // com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.xiaomi.payment.n.b.m.mibi_voucher, (ViewGroup) null);
        this.W = (FormattableEditText) viewInflate.findViewById(com.xiaomi.payment.n.b.j.voucher_edit);
        this.W.setFormatType(s.a.TYPE_NORMAL);
        this.W.setMaxLength((int) this.e0);
        this.Y = (Button) viewInflate.findViewById(com.xiaomi.payment.n.b.j.button_recharge);
        this.X = (ImageView) viewInflate.findViewById(com.xiaomi.payment.n.b.j.camera_btn);
        this.Z = (TextView) viewInflate.findViewById(com.xiaomi.payment.n.b.j.contentHint);
        return viewInflate;
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.StepFragment
    public void b(int i, Bundle bundle) {
        super.b(i, bundle);
        if (i == 111111) {
            this.W.setText("");
        }
    }

    @Override // com.xiaomi.payment.ui.h.a.b
    public void d(long j) {
        a(0, false);
        Y().d().a(this.S, f.I5, Long.valueOf(j));
        Bundle bundle = new Bundle();
        bundle.putString("title", this.a0);
        bundle.putBoolean(f.K5, true);
        com.xiaomi.payment.channel.a.a(this, bundle, this.b0);
    }

    @Override // com.mipay.common.base.BaseFragment
    protected void a(d0 d0Var) {
        super.a(d0Var);
        if (TextUtils.isEmpty(this.S)) {
            return;
        }
        this.b0 = d0Var.b(this.S, f.r2);
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.DecoratableFragment, com.mipay.common.base.StepFragment
    public void a(int i, int i2, Intent intent) {
        Bundle extras;
        super.a(i, i2, intent);
        if (i != m0 || -1 != i2 || intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        String string = extras.getString(Integer.toString(com.xiaomi.payment.n.b.j.voucher_edit));
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.W.setText(string);
    }

    @Override // com.mipay.common.base.p
    public void a(int i, boolean z) {
        if (z) {
            b(getString(com.xiaomi.payment.n.b.q.mibi_progress_voucher_creating), false);
        } else {
            m0();
        }
    }

    @Override // com.mipay.common.base.o
    public void a(int i, String str, Throwable th) {
        a(0, false);
        this.Y.setClickable(true);
        com.xiaomi.payment.channel.a.a(this, i, str);
    }

    @Override // com.mipay.common.base.BaseFragment, com.mipay.common.base.StepFragment
    public void a(int i, int i2, Bundle bundle) {
        Log.v(l0, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            c(i2, bundle);
        }
    }
}
