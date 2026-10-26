package dh;

import java.io.DataInputStream;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g extends d {
    protected abstract boolean b(ah.b bVar, int[] iArr, int i10);

    public boolean c(ah.b bVar, int[] iArr, int i10) {
        if (bVar == null || iArr == null || i10 >= iArr.length) {
            return false;
        }
        return b(bVar, iArr, i10);
    }

    public abstract void d(DataInputStream dataInputStream);
}
