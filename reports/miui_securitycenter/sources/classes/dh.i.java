package dh;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class i extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int[][] f44590a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<Character> f44591b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f44592c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f44593d = 0;

    @Override // dh.d
    protected int a() {
        return this.f44591b.size();
    }

    @Override // dh.g
    public boolean b(ah.b bVar, int[] iArr, int i10) {
        String strD = bVar.d();
        boolean z10 = false;
        for (int i11 = 0; i11 < this.f44591b.size(); i11++) {
            if (strD.indexOf(this.f44591b.get(i11).charValue()) != -1) {
                int i12 = i10 + i11;
                iArr[i12] = iArr[i12] + 1;
                z10 = true;
            }
        }
        return z10;
    }

    @Override // dh.g
    public void d(DataInputStream dataInputStream) throws IOException {
        this.f44591b = new ArrayList();
        int i10 = dataInputStream.readInt();
        for (int i11 = 0; i11 < i10; i11++) {
            this.f44591b.add(Character.valueOf(dataInputStream.readChar()));
        }
    }
}
