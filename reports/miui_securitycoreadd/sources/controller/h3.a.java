package h3;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.activity.e;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0080a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4045b;
    public float c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4046d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4047e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4048f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4049g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4050h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f4051i;

    /* JADX INFO: renamed from: h3.a$a, reason: collision with other inner class name */
    public class C0080a implements Parcelable.Creator<a> {
        @Override // android.os.Parcelable.Creator
        public final a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final a[] newArray(int i10) {
            return new a[i10];
        }
    }

    public a() {
    }

    public a(Parcel parcel) {
        this.f4045b = parcel.readInt();
        this.c = parcel.readFloat();
        this.f4046d = parcel.readFloat();
        this.f4048f = parcel.readFloat();
        this.f4049g = parcel.readFloat();
        this.f4050h = parcel.readFloat();
        this.f4047e = parcel.readFloat();
        this.f4051i = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sbT = e.t("BrightnessModelEvent{id=");
        sbT.append(this.f4045b);
        sbT.append(", amb_lux=");
        sbT.append(this.c);
        sbT.append(", amb_lux_span=");
        sbT.append(this.f4046d);
        sbT.append(", app_code=");
        sbT.append(this.f4048f);
        sbT.append(", orientation_and_app_industry=");
        sbT.append(this.f4047e);
        sbT.append(", curr_brt=");
        sbT.append(this.f4049g);
        sbT.append(", curr_brt_span=");
        sbT.append(this.f4050h);
        sbT.append(", curve_brt=");
        sbT.append(this.f4051i);
        sbT.append('}');
        return sbT.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f4045b);
        parcel.writeFloat(this.c);
        parcel.writeFloat(this.f4046d);
        parcel.writeFloat(this.f4048f);
        parcel.writeFloat(this.f4049g);
        parcel.writeFloat(this.f4050h);
        parcel.writeFloat(this.f4047e);
        parcel.writeFloat(this.f4051i);
    }
}
