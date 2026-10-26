package com.faceunity.core.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/faceunity/core/entity/ImageBeautyData;", "Landroid/os/Parcelable;", "<init>", "()V", "fu_core_xiaomiRelease"}, k = 1, mv = {1, 7, 1})
public final class ImageBeautyData implements Parcelable {
    public static final Parcelable.Creator<ImageBeautyData> CREATOR = new a();

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public double f1286f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public double f1287g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public double f1288h0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public double f1290j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public double f1291k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public double f1292l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public double f1293m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f1294n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public double f1295n0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public double f1302u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public double f1303v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public double f1305w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f1307x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f1309y0;
    public final String d = "ImageBeautyData";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1284e = "{\"p_rect\":[],\"p_do_face_beauty\":0,\"p_bdyscores\":[],\"p_jointscores\":[],\"p_pofdirpoints\":[],\"p_pofdirscores\":[]}";
    public String m = "origin";
    public double[] r = new double[5];
    public double[] s = new double[5];
    public double[] v = new double[5];

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public double[] f1304w = new double[5];

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public double[] f1306x = new double[5];

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public double[] f1308y = new double[5];
    public double[] z = new double[5];
    public double[] A = new double[5];
    public double[] D = new double[5];
    public double[] E = new double[5];
    public double[] F = new double[5];
    public double[] G = new double[5];
    public double[] H = new double[5];
    public double[] I = new double[5];
    public double[] J = new double[5];
    public double[] K = new double[5];
    public double[] L = new double[5];
    public double[] M = new double[5];
    public double[] N = new double[5];
    public double[] O = new double[5];
    public double[] P = new double[5];
    public double[] Q = new double[5];
    public double[] R = new double[5];
    public double[] S = new double[5];
    public double[] T = new double[5];
    public double[] U = new double[5];
    public double[] V = new double[5];
    public double[] W = new double[5];
    public double[] X = new double[5];
    public double[] Y = new double[5];
    public double[] Z = new double[5];

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public double[] f1280a0 = new double[5];

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public double[] f1281b0 = new double[5];

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public double[] f1282c0 = new double[5];

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public double[] f1283d0 = new double[5];

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public double[] f1285e0 = new double[5];

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public double f1289i0 = 0.5d;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final LinkedList<m2.a.C0264a> f1296o0 = new LinkedList<>();

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public String f1297p0 = "{\"p_order\":[],\"cur_order\":0,\"last_order_num\":0}";

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public String f1298q0 = "{\"p_r\":[],\"p_vec\":[],\"cur_order\":0}";

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public String f1299r0 = "{\"p_r\":[],\"p_vec\":[],\"cur_order\":0}";

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public String f1300s0 = "{\"p_r\":[],\"p_vec\":[],\"cur_order\":0}";

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public double f1301t0 = 1.0d;

    public static final class a implements Parcelable.Creator<ImageBeautyData> {
        @Override // android.os.Parcelable.Creator
        public final ImageBeautyData createFromParcel(Parcel parcel) {
            e.f(parcel, "parcel");
            parcel.readInt();
            return new ImageBeautyData();
        }

        @Override // android.os.Parcelable.Creator
        public final ImageBeautyData[] newArray(int i10) {
            return new ImageBeautyData[i10];
        }
    }

    public final double a(int i10, double[] doubleArray) {
        e.f(doubleArray, "doubleArray");
        if (i10 >= 0 && i10 <= 5) {
            return doubleArray[i10 - 1];
        }
        Log.e(this.d, "输入的人脸参数不合法: " + i10);
        return 0.0d;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel out, int i10) {
        e.f(out, "out");
        out.writeInt(1);
    }
}
