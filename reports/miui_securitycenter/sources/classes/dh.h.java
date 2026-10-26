package dh;

import java.io.DataInputStream;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class h extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<int[]> f44586a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f44587b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f44588c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f44589d = 0;

    private int e(ah.b bVar) {
        return bVar.d().length();
    }

    @Override // dh.g
    public boolean b(ah.b bVar, int[] iArr, int i10) {
        if (e(bVar) < this.f44589d) {
            return false;
        }
        iArr[i10] = iArr[i10] + 1;
        return true;
    }

    @Override // dh.g
    public void d(DataInputStream dataInputStream) {
        this.f44589d = dataInputStream.readInt();
    }
}
