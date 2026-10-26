package com.xiaomi.payment.entryData;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class CameraIntentData implements Parcelable {
    public static final Parcelable.Creator<CameraIntentData> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3574a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3575b;
    public ArrayList<Integer> h;

    class a implements Parcelable.Creator<CameraIntentData> {
        a() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraIntentData createFromParcel(Parcel parcel) {
            return new CameraIntentData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CameraIntentData[] newArray(int i) {
            return new CameraIntentData[i];
        }
    }

    public CameraIntentData(int i, String str, ArrayList<Integer> arrayList) {
        this.f3574a = i;
        this.f3575b = str;
        this.h = arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3574a);
        parcel.writeString(this.f3575b);
        parcel.writeList(this.h);
    }

    public CameraIntentData(Parcel parcel) {
        this.f3574a = parcel.readInt();
        this.f3575b = parcel.readString();
        this.h = parcel.readArrayList(Integer.class.getClassLoader());
    }
}
