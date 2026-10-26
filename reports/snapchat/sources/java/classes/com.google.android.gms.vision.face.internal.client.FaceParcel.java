package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.apps.common.proguard.UsedByNative;
import defpackage.AbstractC18433e2;
import defpackage.AbstractC33817qSa;
import defpackage.HWi;

/* JADX INFO: loaded from: classes2.dex */
@UsedByNative("wrapper.cc")
public class FaceParcel extends AbstractC18433e2 {
    public static final Parcelable.Creator<FaceParcel> CREATOR = new HWi(24);
    public final float R;
    public final float S;
    public final float T;
    public final float U;
    public final float V;
    public final LandmarkParcel[] W;
    public final float X;
    public final float Y;
    public final float Z;
    public final int a;
    public final int b;
    public final float c;

    public FaceParcel(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, LandmarkParcel[] landmarkParcelArr, float f7, float f8, float f9) {
        this.a = i;
        this.b = i2;
        this.c = f;
        this.R = f2;
        this.S = f3;
        this.T = f4;
        this.U = f5;
        this.V = f6;
        this.W = landmarkParcelArr;
        this.X = f7;
        this.Y = f8;
        this.Z = f9;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iG0 = AbstractC33817qSa.g0(parcel, 20293);
        AbstractC33817qSa.V(parcel, 1, this.a);
        AbstractC33817qSa.V(parcel, 2, this.b);
        AbstractC33817qSa.T(parcel, 3, this.c);
        AbstractC33817qSa.T(parcel, 4, this.R);
        AbstractC33817qSa.T(parcel, 5, this.S);
        AbstractC33817qSa.T(parcel, 6, this.T);
        AbstractC33817qSa.T(parcel, 7, this.U);
        AbstractC33817qSa.T(parcel, 8, this.V);
        AbstractC33817qSa.c0(parcel, 9, this.W, i);
        AbstractC33817qSa.T(parcel, 10, this.X);
        AbstractC33817qSa.T(parcel, 11, this.Y);
        AbstractC33817qSa.T(parcel, 12, this.Z);
        AbstractC33817qSa.h0(parcel, iG0);
    }
}
