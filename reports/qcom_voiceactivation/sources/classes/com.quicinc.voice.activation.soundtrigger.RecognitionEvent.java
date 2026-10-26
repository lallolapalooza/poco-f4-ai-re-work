package com.quicinc.voice.activation.soundtrigger;

import A.b;
import A.f;
import A.h;
import A.j;
import E.d;
import G.q;
import N.c;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import w.a;

/* JADX INFO: loaded from: classes.dex */
public final class RecognitionEvent implements Parcelable {
    public static final Parcelable.Creator CREATOR = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f664a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f665b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f666c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f667d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f668e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f669f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f670g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f671h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f672i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f673j;

    public RecognitionEvent(c cVar) {
        this.f669f = cVar;
        this.f664a = cVar.b();
        x.d dVarA = a.a(cVar.c());
        q.h("opaqueData " + dVarA.toString());
        g((z.d) dVarA.c().orElse(null));
        f((f) dVarA.b().orElse(null));
        i();
    }

    public int a() {
        return this.f664a;
    }

    public int b() {
        return this.f666c;
    }

    public int c() {
        return this.f672i;
    }

    public int d() {
        return this.f665b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.f668e;
    }

    public final void f(f fVar) {
        A.d dVarC;
        j jVar;
        if (fVar == null || (dVarC = fVar.c()) == null) {
            return;
        }
        b bVar = (b) dVarC.e().get(0);
        if (bVar == null) {
            return;
        }
        boolean z2 = bVar.d() > 1;
        this.f673j = z2;
        if (z2) {
            List listE = bVar.e();
            for (int i2 = 0; i2 < bVar.d(); i2++) {
                if (((h) listE.get(i2)).d() > 0) {
                    this.f672i = i2;
                    this.f667d = ((h) listE.get(i2)).d();
                    return;
                }
            }
            return;
        }
        h hVar = (h) bVar.e().get(0);
        if (hVar == null) {
            return;
        }
        this.f667d = hVar.d();
        List listE2 = hVar.e();
        if (listE2.isEmpty() || (jVar = (j) listE2.get(0)) == null) {
            return;
        }
        this.f668e = jVar.c();
    }

    public final void g(z.d dVar) {
        Optional map = Optional.ofNullable(dVar).map(new Function() { // from class: E.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((z.d) obj).c();
            }
        });
        this.f665b = ((Integer) map.map(new Function() { // from class: E.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((z.b) obj).d());
            }
        }).orElse(0)).intValue();
        this.f666c = ((Integer) map.map(new Function() { // from class: E.c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((z.b) obj).c());
            }
        }).orElse(0)).intValue();
        q.h("mStartIndex=" + this.f665b + ", mEndIndex=" + this.f666c);
    }

    public boolean h() {
        return this.f673j;
    }

    public final void i() {
        N.d[] dVarArrD;
        c cVar = this.f669f;
        if (cVar == null || (dVarArrD = cVar.d()) == null || dVarArrD.length <= 0) {
            return;
        }
        this.f671h = dVarArrD[0].a();
        N.a[] aVarArrB = dVarArrD[0].b();
        if (aVarArrB == null || aVarArrB.length <= 0) {
            return;
        }
        this.f670g = dVarArrD[0].a();
    }

    public String toString() {
        return "RecognitionEvent{mStatus=" + this.f669f.f() + ", mSoundModelHandle=" + this.f669f.e() + ", mCaptureAvailable=" + this.f669f.a() + ", mCaptureSession=" + this.f669f.b() + ", mBeginIndex=" + this.f665b + ", mEndIndex=" + this.f666c + ", isMultiKW=" + this.f673j + ", mKeywordID=" + this.f672i + ", mConfidence=" + this.f670g + ", mCoarseConfidence=" + this.f671h + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f664a);
        parcel.writeInt(this.f665b);
        parcel.writeInt(this.f666c);
        parcel.writeInt(this.f667d);
        parcel.writeInt(this.f668e);
    }

    public RecognitionEvent(Parcel parcel) {
        this.f664a = parcel.readInt();
        this.f665b = parcel.readInt();
        this.f666c = parcel.readInt();
        this.f667d = parcel.readInt();
        this.f668e = parcel.readInt();
    }
}
