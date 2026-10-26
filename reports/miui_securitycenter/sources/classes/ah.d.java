package ah;

import android.content.Context;
import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class d extends e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f519c = "ah.d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f520b = 0;

    private boolean i(String str, char[] cArr, int i10) {
        boolean z10;
        for (int i11 = 0; i11 <= str.length() - i10; i11++) {
            int i12 = 0;
            while (true) {
                if (i12 >= i10) {
                    z10 = true;
                    break;
                }
                if (str.charAt(i11 + i12) != cArr[i12]) {
                    z10 = false;
                    break;
                }
                i12++;
            }
            if (z10) {
                return true;
            }
        }
        return false;
    }

    private int j(List<String> list, char[] cArr, int i10, int i11) {
        while (i11 < list.size()) {
            String str = list.get(i11);
            if (str.length() == i10) {
                boolean z10 = false;
                int i12 = 0;
                while (true) {
                    if (i12 >= i10) {
                        z10 = true;
                        break;
                    }
                    if (str.charAt(i12) != cArr[i12]) {
                        break;
                    }
                    i12++;
                }
                if (z10) {
                    return i11;
                }
            }
            i11++;
        }
        return i11;
    }

    @Override // ah.e
    protected boolean d(b bVar, Context context) {
        List<String> list = bVar.f504g;
        boolean z10 = false;
        if (list == null || list.size() <= 0) {
            return false;
        }
        int size = bVar.f504g.size();
        String[] strArr = new String[size];
        for (int i10 = 0; i10 < bVar.f504g.size(); i10++) {
            strArr[i10] = i.c(bVar.f504g.get(i10));
        }
        try {
            InputStream inputStreamB = c.b(context, "phish");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamB));
            bufferedReader.readLine();
            boolean z11 = false;
            while (!z11) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    for (int i11 = 0; i11 < size; i11++) {
                        if (strArr[i11].equalsIgnoreCase(line)) {
                            z11 = true;
                            break;
                        }
                    }
                } catch (IOException unused) {
                    z10 = z11;
                    Log.e(f519c, "Exception on read phish.");
                    return z10;
                }
            }
            bufferedReader.close();
            inputStreamB.close();
            return z11;
        } catch (IOException unused2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:99:0x0262, code lost:
    
        if (r2 > com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) goto L92;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean h(b bVar, Context context) throws IOException {
        char c10;
        int i10;
        int i11;
        int i12;
        int i13;
        b bVar2 = bVar;
        f(bVar, context);
        a(bVar, context);
        int i14 = 0;
        if (e.e(bVar)) {
            return false;
        }
        char c11 = 1;
        if (e.b(bVar)) {
            return true;
        }
        g gVarK = k(context);
        dh.e eVar = new dh.e();
        eVar.g(gVarK);
        InputStream inputStreamB = c.b(context, "model");
        DataInputStream dataInputStream = new DataInputStream(inputStreamB);
        dataInputStream.readInt();
        dataInputStream.readInt();
        int i15 = dataInputStream.readInt();
        double[] dArr = new double[i15];
        int i16 = 2;
        double[] dArr2 = new double[2];
        int i17 = 0;
        int i18 = 0;
        while (true) {
            if (i17 >= i15) {
                c10 = c11;
                i10 = i15;
                i11 = i14;
                i12 = i11;
                i13 = i12;
                break;
            }
            int i19 = i14;
            while (i19 < i16) {
                dArr2[i19] = 0.0d;
                i19++;
                i15 = i15;
                bVar2 = bVar;
            }
            int i20 = dataInputStream.readInt();
            int[] iArr = new int[i16];
            iArr[c11] = i20;
            iArr[i14] = i16;
            double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr);
            dh.g[] gVarArrC = eVar.c();
            int i21 = 0;
            while (i21 < gVarArrC.length) {
                gVarArrC[i21].d(dataInputStream);
                i21++;
                bVar2 = bVar;
                eVar = eVar;
                i15 = i15;
            }
            eVar.d();
            int i22 = 0;
            while (i22 < i20) {
                dArr3[0][i22] = dataInputStream.readDouble();
                dArr3[1][i22] = dataInputStream.readDouble();
                i22++;
                bVar2 = bVar;
                eVar = eVar;
                i15 = i15;
            }
            eVar.f(bVar2);
            int[] iArrF = bVar.f();
            int i23 = 0;
            while (i23 < iArrF.length) {
                dh.e eVar2 = eVar;
                int i24 = i15;
                dArr2[0] = dArr2[0] + (iArrF[i23] > 0 ? dArr3[0][i23] : i.g(dArr3[0][i23]));
                dArr2[1] = dArr2[1] + (iArrF[i23] > 0 ? dArr3[1][i23] : i.g(dArr3[1][i23]));
                i23++;
                bVar2 = bVar;
                eVar = eVar2;
                i15 = i24;
            }
            List<String> listI = bVar.i();
            int size = listI.size();
            int i25 = dataInputStream.readInt();
            char[] cArr = new char[64];
            dh.e eVar3 = eVar;
            i10 = i15;
            int i26 = 0;
            int i27 = 0;
            while (i27 < i25) {
                int i28 = i25;
                int i29 = 0;
                while (true) {
                    char c12 = dataInputStream.readChar();
                    if (c12 == '\n') {
                        break;
                    }
                    cArr[i29] = c12;
                    i29++;
                }
                int iJ = j(listI, cArr, i29, i26);
                boolean z10 = iJ < size;
                int i30 = z10 ? iJ + 1 : i26;
                for (int i31 = 0; i31 < 2; i31++) {
                    double dG = dataInputStream.readDouble();
                    double d10 = dArr2[i31];
                    if (!z10) {
                        dG = i.g(dG);
                    }
                    dArr2[i31] = d10 + dG;
                }
                i27++;
                bVar2 = bVar;
                i25 = i28;
                i26 = i30;
            }
            int i32 = dataInputStream.readInt();
            String strD = bVar.d();
            for (int i33 = 0; i33 < i32; i33++) {
                int i34 = 0;
                while (true) {
                    char c13 = dataInputStream.readChar();
                    if (c13 == '\n') {
                        break;
                    }
                    cArr[i34] = c13;
                    i34++;
                }
                boolean zI = i(strD, cArr, i34);
                for (int i35 = 0; i35 < 2; i35++) {
                    double dG2 = dataInputStream.readDouble();
                    double d11 = dArr2[i35];
                    if (!zI) {
                        dG2 = i.g(dG2);
                    }
                    dArr2[i35] = d11 + dG2;
                }
            }
            int i36 = 2;
            double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 2, dh.a.b());
            int i37 = 0;
            while (i37 < dh.a.b()) {
                dArr4[0][i37] = dataInputStream.readDouble();
                dArr4[1][i37] = dataInputStream.readDouble();
                i37++;
                i36 = 2;
            }
            int iC = bVar.c();
            int i38 = 0;
            while (i38 < i36) {
                dArr2[i38] = dArr2[i38] + dArr4[i38][iC];
                i38++;
                i36 = 2;
            }
            double[] dArr5 = new double[3];
            i12 = 0;
            dArr5[0] = dataInputStream.readDouble();
            dArr5[1] = dataInputStream.readDouble();
            dArr5[i36] = dataInputStream.readDouble();
            double d12 = dArr2[0] - dArr2[1];
            double[] dArrG = g(dArr5, bVar2);
            if (d12 < dArrG[1]) {
                i11 = 0;
                c10 = 1;
            } else if (d12 > dArrG[0]) {
                c10 = 1;
                i11 = 0;
                i12 = 1;
            } else {
                double d13 = d12 - dArrG[2];
                dArr[i17] = d13;
                if (d13 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    i18++;
                }
                i17++;
                eVar = eVar3;
                i15 = i10;
                i14 = 0;
                c11 = 1;
                i16 = 2;
            }
            i13 = 1;
            break;
        }
        gVarK.b();
        dataInputStream.close();
        inputStreamB.close();
        if (i13 != 0) {
            return i12;
        }
        int i39 = i10 - i18;
        if (i18 <= i39) {
            if (i18 >= i39) {
                int i40 = i10;
                double d14 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                for (int i41 = i11; i41 < i40; i41++) {
                    d14 += dArr[i41];
                }
            }
            return i11;
        }
        return c10;
    }

    protected g k(Context context) {
        g gVar = new g();
        gVar.d(context);
        return gVar;
    }
}
