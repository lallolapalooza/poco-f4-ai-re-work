package com.yxcorp.gifshow.live.music;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.yxcorp.gifshow.fragment.g;
import com.yxcorp.utility.g0;
import com.yxcorp.utility.w1;
import com.yxcorp.widget.LiveSeekBar;
import jj0.h;
import jj0.i;
import jj0.j;
import jj0.l;

/* JADX INFO: loaded from: classes8.dex */
public class LivePushSoundEffectFragment extends g {
    LiveSeekBar C;
    LiveSeekBar D;
    View E;
    private OnMixRateChangedListener F;
    private float G;
    private float H;

    public interface OnMixRateChangedListener {
        void onMixRateChanged(float f12, float f13);
    }

    class a implements SeekBar.OnSeekBarChangeListener {
        a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i12, boolean z12) {
            LivePushSoundEffectFragment.this.n2();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class b implements SeekBar.OnSeekBarChangeListener {
        b() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i12, boolean z12) {
            LivePushSoundEffectFragment.this.n2();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    static {
        new f(l.P1, h.f74624q0, 0, 1);
        new f(l.S1, h.f74630t0, 1, 2);
        new f(l.Q1, h.f74626r0, 2, 3);
        new f(l.R1, h.f74628s0, 3, 4);
        new f(l.O1, h.f74622p0, 4, 5);
    }

    public LivePushSoundEffectFragment() {
        super.setArguments(new Bundle());
    }

    public void m2(View view) {
        this.D = (LiveSeekBar) w1.e(view, i.Qa);
        this.C = (LiveSeekBar) w1.e(view, i.f74724g7);
    }

    void n2() {
        int progress = this.C.getProgress();
        int progress2 = this.D.getProgress();
        OnMixRateChangedListener onMixRateChangedListener = this.F;
        if (onMixRateChangedListener != null) {
            onMixRateChangedListener.onMixRateChanged((progress * 1.0f) / this.C.getMax(), (progress2 * 1.0f) / this.D.getMax());
        }
    }

    public LivePushSoundEffectFragment o2(OnMixRateChangedListener onMixRateChangedListener) {
        this.F = onMixRateChangedListener;
        return this;
    }

    @Override // com.yxcorp.gifshow.fragment.i, androidx.fragment.app.t, androidx.fragment.app.DialogFragment
    @e.a
    public Dialog onCreateDialog(Bundle bundle) {
        ag0.b bVar = new ag0.b(getActivity());
        bVar.k(g0.c(getActivity().getWindow()));
        return bVar;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getArguments() != null) {
        }
        this.G = d20.i.U1();
        this.H = d20.i.V1();
        View view = this.E;
        if (view == null) {
            View viewInflate = layoutInflater.inflate(j.O0, viewGroup, false);
            this.E = viewInflate;
            m2(viewInflate);
        } else if (view.getParent() != null) {
            ((ViewGroup) this.E.getParent()).removeView(this.E);
        }
        this.C.setOnSeekBarChangeListener(new a());
        LiveSeekBar liveSeekBar = this.C;
        liveSeekBar.setProgress((int) (this.G * liveSeekBar.getMax()));
        this.D.setOnSeekBarChangeListener(new b());
        LiveSeekBar liveSeekBar2 = this.D;
        liveSeekBar2.setProgress((int) (this.H * liveSeekBar2.getMax()));
        return this.E;
    }

    @Override // androidx.fragment.app.Fragment
    public final void setArguments(Bundle bundle) {
        if (getArguments() != null) {
            getArguments().putAll(bundle);
        }
    }
}
