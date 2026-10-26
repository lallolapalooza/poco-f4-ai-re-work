package com.quicinc.voice.activation.audiorecord;

import G.i;
import G.q;
import android.os.Bundle;
import com.quicinc.voice.activation.audiorecord.c;
import com.quicinc.voice.activation.onlineepd.EPD;
import com.quicinc.voice.activation.vad.DNNVAD;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import p003d.g;
import p009j.u;
import p009j.v;

/* JADX INFO: compiled from: TrainingAudioRecorder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v f360b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f362d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public NormalAudioRecordAction f364f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public EPD f365g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DNNVAD f366h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f368j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f359a = new u(this);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f367i = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f361c = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TrainingAudioRecorder$State f363e = TrainingAudioRecorder$State.UNINITIALIZED;

    public c(v vVar) {
        this.f360b = vVar;
        v();
    }

    public static /* synthetic */ ShortBuffer t(ShortBuffer shortBuffer, ShortBuffer shortBuffer2) {
        return ShortBuffer.allocate(shortBuffer.array().length + shortBuffer2.array().length).put(shortBuffer.array()).put(shortBuffer2.array());
    }

    public static /* synthetic */ ShortBuffer[] u(int i2) {
        return new ShortBuffer[i2];
    }

    public final void A(H.a aVar) {
        v vVar = this.f360b;
        if (vVar != null) {
            vVar.a(aVar);
        }
    }

    public final void B() {
        EPD epd = this.f365g;
        if (epd == null || epd.reInitEPD().e()) {
            return;
        }
        q.v("EPD reinit error");
    }

    public final void C() {
        DNNVAD dnnvad = this.f366h;
        if (dnnvad == null || dnnvad.reInitVAD() == 0) {
            return;
        }
        q.v("VAD reinit error");
    }

    public final void D() {
        EPD epd = this.f365g;
        if (epd == null || epd.releaseEPD().e()) {
            return;
        }
        q.v("EPD release error");
    }

    public final void E() {
        DNNVAD dnnvad = this.f366h;
        if (dnnvad == null || dnnvad.deinitVAD() == 0) {
            return;
        }
        q.v("VAD release error");
    }

    public void F() {
        K();
        L();
        this.f361c.clear();
        D();
        E();
        v();
    }

    public void G(int i2) {
        this.f368j = i2;
    }

    public final void H(TrainingAudioRecorder$State trainingAudioRecorder$State) {
        this.f363e = trainingAudioRecorder$State;
        z();
    }

    public void I(int i2) {
        this.f367i = i2;
    }

    public void J() {
        if (this.f363e == TrainingAudioRecorder$State.INITIALIZED) {
            this.f364f.fork();
            H(TrainingAudioRecorder$State.START_RECORDING);
        }
        v();
    }

    public void K() {
        if (this.f363e == TrainingAudioRecorder$State.START_RECORDING) {
            this.f364f.k();
            if (this.f367i > 0) {
                this.f359a.d(n());
            }
            H(TrainingAudioRecorder$State.STOP_RECORDING);
            this.f364f = null;
        }
        v();
    }

    public void L() {
        NormalAudioRecordAction normalAudioRecordAction = this.f364f;
        if (normalAudioRecordAction != null) {
            normalAudioRecordAction.k();
        }
        if (this.f363e == TrainingAudioRecorder$State.STOP_RECORDING) {
            this.f362d = null;
            this.f363e = TrainingAudioRecorder$State.UNINITIALIZED;
        }
        v();
    }

    public final ShortBuffer k(ArrayList arrayList) {
        return (ShortBuffer) arrayList.stream().reduce(new BinaryOperator() { // from class: j.q
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return c.t((ShortBuffer) obj, (ShortBuffer) obj2);
            }
        }).orElse(ShortBuffer.allocate(0));
    }

    public ShortBuffer l() {
        synchronized (this.f361c) {
            ArrayList arrayList = (ArrayList) this.f361c.get(this.f362d);
            if (arrayList == null) {
                return ShortBuffer.allocate(0);
            }
            return k(arrayList);
        }
    }

    public String m() {
        return this.f362d;
    }

    public final short[] n() {
        short[] sArr = new short[this.f367i * 16];
        Arrays.fill(sArr, (short) 0);
        return sArr;
    }

    public Map o() {
        return Collections.unmodifiableMap(this.f361c);
    }

    public ShortBuffer[] p() {
        ShortBuffer[] shortBufferArr;
        synchronized (this.f361c) {
            shortBufferArr = (ShortBuffer[]) o().values().stream().map(new Function() { // from class: j.r
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f823a.k((ArrayList) obj);
                }
            }).toArray(new IntFunction() { // from class: j.s
                @Override // java.util.function.IntFunction
                public final Object apply(int i2) {
                    return c.u(i2);
                }
            });
        }
        return shortBufferArr;
    }

    public void q(ByteBuffer byteBuffer, Bundle bundle) {
        EPD epd = new EPD(byteBuffer, bundle);
        this.f365g = epd;
        if (epd.initEPD().e()) {
            return;
        }
        q.v("EPD init error");
    }

    public void r(String str, i iVar) {
        NormalAudioRecordAction normalAudioRecordAction = this.f364f;
        if (normalAudioRecordAction != null) {
            normalAudioRecordAction.cancel(true);
        }
        if (this.f363e == TrainingAudioRecorder$State.UNINITIALIZED) {
            this.f362d = str;
            if (this.f365g != null) {
                this.f364f = new NormalAudioRecordAction(this.f368j, this.f359a, iVar, this.f365g);
                B();
            } else if (this.f366h != null) {
                this.f364f = new NormalAudioRecordAction(this.f368j, this.f359a, iVar, this.f366h);
                C();
            }
            this.f361c.put(this.f362d, new ArrayList());
            H(TrainingAudioRecorder$State.INITIALIZED);
        }
        v();
    }

    public void s() {
        DNNVAD dnnvad = new DNNVAD(g.f());
        this.f366h = dnnvad;
        dnnvad.initVAD();
    }

    public final void v() {
        q.v("utteranceId=" + this.f362d + ", state=" + this.f363e.name());
    }

    public final void w(String str, short[] sArr) {
        v vVar = this.f360b;
        if (vVar != null) {
            vVar.e(str, sArr);
        }
    }

    public final void x(s.a aVar) {
        v vVar = this.f360b;
        if (vVar != null) {
            vVar.b(aVar);
        }
    }

    public final void y() {
        v vVar = this.f360b;
        if (vVar != null) {
            vVar.c();
        }
    }

    public final void z() {
        v vVar = this.f360b;
        if (vVar != null) {
            vVar.d(this.f363e);
        }
    }
}
