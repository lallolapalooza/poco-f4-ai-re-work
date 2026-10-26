package dh;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class b extends f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f44566d = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f44568c = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Pattern f44567b = Pattern.compile(f44566d);

    @Override // dh.f
    public boolean b(ah.b bVar, int[] iArr, int i10) {
        if (this.f44568c.size() <= 0) {
            return false;
        }
        iArr[i10] = iArr[i10] + 1;
        return true;
    }

    @Override // dh.f
    protected List<String> d(String str) {
        Matcher matcher = this.f44567b.matcher(str);
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (iStart > i10) {
                arrayList.add(str.substring(i10, iStart));
            }
            this.f44568c.add(str.substring(iStart, iEnd));
            i10 = iEnd;
        }
        if (i10 < str.length() - 1) {
            arrayList.add(str.substring(i10));
        }
        return arrayList;
    }

    @Override // dh.f
    protected void f() {
        super.f();
        this.f44568c.clear();
    }
}
