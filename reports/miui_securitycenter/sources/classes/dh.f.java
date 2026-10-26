package dh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected boolean f44585a = false;

    public abstract boolean b(ah.b bVar, int[] iArr, int i10);

    public boolean c(ah.b bVar, int[] iArr, int i10) {
        if (iArr == null || i10 >= iArr.length) {
            return false;
        }
        return b(bVar, iArr, i10);
    }

    protected abstract List<String> d(String str);

    public List<String> e(List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.addAll(d(it.next()));
        }
        return arrayList;
    }

    protected void f() {
        this.f44585a = false;
    }
}
