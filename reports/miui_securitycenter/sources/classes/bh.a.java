package bh;

import android.util.Log;
import com.google.common.primitives.UnsignedBytes;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f5603k = "bh.a";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final int[][] f5604l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double[] f5605a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double[][] f5606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int[] f5607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f5608d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f5609e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f5610f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int[] f5611g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f5612h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f5613i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private byte[] f5614j;

    static {
        int[] iArr = new int[4];
        iArr[0] = 1;
        f5604l = new int[][]{iArr, new int[]{2, 0, 1, 2}};
    }

    private int a(byte[] bArr, int i10, char[] cArr, int i11, int i12) {
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = i13 * 2;
            char c10 = (char) ((bArr[i14 + 1 + i10] & UnsignedBytes.MAX_VALUE) | ((bArr[i14 + i10] & UnsignedBytes.MAX_VALUE) << 8));
            char c11 = cArr[i11 + i13];
            if (c11 != c10) {
                return c11 - c10;
            }
        }
        return 0;
    }

    private List<String> b(char[] cArr, int i10, int i11) {
        int i12;
        ArrayList arrayList = new ArrayList();
        int i13 = i11 - i10;
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i13, 4);
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i13, 4);
        for (int i14 = 0; i14 < i13; i14++) {
            int i15 = 0;
            if (i14 == 0) {
                while (i15 < 4) {
                    dArr[i14][i15] = this.f5605a[i15] + e(i15, cArr[i14 + i10]);
                    i15++;
                }
            } else {
                while (i15 < 4) {
                    Double dValueOf = Double.valueOf(-3.14E100d);
                    for (int i16 = 0; i16 < 4; i16++) {
                        Double dValueOf2 = Double.valueOf(dArr[i14 - 1][i16] + this.f5606b[i16][i15]);
                        if (dValueOf2.doubleValue() > dValueOf.doubleValue()) {
                            iArr[i14][i15] = i16;
                            dValueOf = dValueOf2;
                        }
                    }
                    if (dValueOf.doubleValue() == -3.14E100d || e(i15, cArr[i14 + i10]) == -3.14E100d) {
                        iArr[i14][i15] = 1;
                    }
                    dArr[i14][i15] = dValueOf.doubleValue() + e(i15, cArr[i14 + i10]);
                    i15++;
                }
            }
        }
        int i17 = i13 - 1;
        double[] dArr2 = dArr[i17];
        int[] iArr2 = new int[i13];
        iArr2[i17] = dArr2[1] > dArr2[3] ? 1 : 3;
        for (int i18 = i13 - 2; i18 >= 0; i18--) {
            int i19 = i18 + 1;
            iArr2[i18] = iArr[i19][iArr2[i19]];
        }
        int i20 = 0;
        while (i20 < i13) {
            int i21 = f5604l[0][iArr2[i20]];
            int i22 = i20 + 1;
            while (i22 < i13 && i21 == 1) {
                i21 = f5604l[i21][iArr2[i22]];
                i22++;
            }
            if (i21 == 0) {
                arrayList.add(new String(cArr, i10 + i20, i22 - i20));
                i20 = i22;
            } else {
                if (i22 >= i13) {
                    while (i20 < i13) {
                        arrayList.add(new String(cArr, i10 + i20, 1));
                        i20++;
                    }
                    break;
                }
                while (true) {
                    i12 = i22 - 1;
                    if (i20 >= i12) {
                        break;
                    }
                    arrayList.add(new String(cArr, i10 + i20, 1));
                    i20++;
                }
                i20 = i12;
            }
        }
        return arrayList;
    }

    private int d(char[] cArr, int i10, int i11) {
        if (i11 > this.f5609e) {
            return 0;
        }
        int i12 = i11 - 1;
        int i13 = this.f5611g[i12];
        int i14 = i11 * 2;
        int i15 = i14 + 4;
        int i16 = this.f5610f[i12] - 1;
        int i17 = 0;
        while (i17 <= i16) {
            int i18 = (i17 + i16) / 2;
            int i19 = i13 + (i15 * i18);
            int iA = a(this.f5614j, i19, cArr, i10, i11);
            if (iA == 0) {
                return b.b(this.f5614j, i19 + i14);
            }
            if (iA > 0) {
                i17 = i18 + 1;
            } else {
                i16 = i18 - 1;
            }
        }
        return 0;
    }

    private double e(int i10, char c10) {
        int i11 = this.f5608d[i10];
        int i12 = this.f5607c[i10] - 1;
        int i13 = 0;
        while (i13 <= i12) {
            int i14 = (i13 + i12) / 2;
            int i15 = (i14 * 6) + i11;
            byte[] bArr = this.f5614j;
            char c11 = (char) (((bArr[i15] & UnsignedBytes.MAX_VALUE) << 8) | (bArr[i15 + 1] & UnsignedBytes.MAX_VALUE));
            if (c11 == c10) {
                return b.a(bArr, i15 + 2);
            }
            if (c11 < c10) {
                i13 = i14 + 1;
            } else {
                i12 = i14 - 1;
            }
        }
        return -3.14E100d;
    }

    private boolean g(char c10) {
        return c10 >= 0 && c10 < 128;
    }

    public String[] c(String str) {
        ArrayList arrayList = new ArrayList();
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i10 = length + 1;
        double[] dArr = new double[i10];
        dArr[length] = 0.0d;
        int[] iArr = new int[i10];
        int i11 = length - 1;
        for (int i12 = i11; i12 >= 0; i12--) {
            iArr[i12] = i12 + 1;
        }
        double dLog = Math.log((this.f5613i * 1.0f) / this.f5612h);
        for (int i13 = i11; i13 >= 0; i13--) {
            int i14 = i13 + 1;
            dArr[i13] = (2.0d * dLog) + dArr[i14];
            while (i14 <= length) {
                int iD = d(charArray, i13, i14 - i13);
                if (iD != 0) {
                    double dLog2 = Math.log((iD * 1.0f) / this.f5612h) + dArr[i14];
                    if (dLog2 > dArr[i13]) {
                        iArr[i13] = i14;
                        dArr[i13] = dLog2;
                    }
                }
                i14++;
            }
        }
        int i15 = 0;
        while (i15 < length) {
            int i16 = iArr[i15];
            if (i16 == i15 + 1) {
                int i17 = i15;
                while (i17 < length) {
                    int i18 = i17 + 1;
                    if (iArr[i17] != i18) {
                        break;
                    }
                    i17 = i18;
                }
                while (i15 < i17) {
                    int i19 = i15;
                    while (i19 < i17 && !g(charArray[i19])) {
                        i19++;
                    }
                    if (i15 < i19) {
                        arrayList.addAll(b(charArray, i15, i19));
                    }
                    i15 = i19;
                    while (i15 < i17 && g(charArray[i15])) {
                        i15++;
                    }
                    if (i19 < i15 && i19 < i17) {
                        arrayList.add(new String(charArray, i19, i15 - i19));
                    }
                }
                i15 = i17;
            } else {
                arrayList.add(new String(charArray, i15, i16 - i15));
                i15 = iArr[i15];
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public boolean f(String str) {
        return d(str.toCharArray(), 0, str.length()) > 0;
    }

    public boolean h(InputStream inputStream) {
        int i10;
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readInt();
            dataInputStream.readInt();
            this.f5605a = new double[4];
            for (int i11 = 0; i11 < 4; i11++) {
                this.f5605a[i11] = dataInputStream.readDouble();
            }
            this.f5606b = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
            for (int i12 = 0; i12 < 4; i12++) {
                for (int i13 = 0; i13 < 4; i13++) {
                    this.f5606b[i12][i13] = dataInputStream.readDouble();
                }
            }
            this.f5607c = new int[4];
            for (int i14 = 0; i14 < 4; i14++) {
                this.f5607c[i14] = dataInputStream.readInt();
            }
            int i15 = dataInputStream.readInt();
            this.f5609e = i15;
            this.f5610f = new int[i15];
            for (int i16 = 0; i16 < this.f5609e; i16++) {
                this.f5610f[i16] = dataInputStream.readInt();
            }
            this.f5612h = dataInputStream.readInt();
            this.f5613i = dataInputStream.readInt();
            Log.d("Dict", "mLengthCount:" + this.f5609e + "\tmTotalFreq:" + this.f5612h + "\tmMinFreq:" + this.f5613i);
            int[] iArr = new int[4];
            this.f5608d = iArr;
            iArr[0] = 0;
            for (int i17 = 1; i17 < 4; i17++) {
                int[] iArr2 = this.f5608d;
                int i18 = i17 - 1;
                iArr2[i17] = iArr2[i18] + (this.f5607c[i18] * 6);
            }
            int i19 = this.f5608d[3] + (this.f5607c[3] * 6) + 0;
            int[] iArr3 = new int[this.f5609e];
            this.f5611g = iArr3;
            iArr3[0] = i19;
            int i20 = 1;
            while (true) {
                i10 = this.f5609e;
                if (i20 >= i10) {
                    break;
                }
                int[] iArr4 = this.f5611g;
                int i21 = i20 - 1;
                iArr4[i20] = iArr4[i21] + (this.f5610f[i21] * ((i20 * 2) + 4));
                i20++;
            }
            int i22 = this.f5611g[i10 - 1] + (this.f5610f[i10 - 1] * ((i10 * 2) + 4));
            int iAvailable = inputStream.available();
            if (i22 != iAvailable) {
                Log.e("Dict", "The dict file is wrong, please make sure it's not be modified.");
                return false;
            }
            byte[] bArr = new byte[iAvailable];
            this.f5614j = bArr;
            if (inputStream.read(bArr) != iAvailable) {
                Log.e("Dict", "Reading dict file failed, availabe size is not equal to realy read size.");
                return false;
            }
            dataInputStream.close();
            return true;
        } catch (IOException unused) {
            Log.e(f5603k, "IOException on load dict.");
            return false;
        }
    }
}
