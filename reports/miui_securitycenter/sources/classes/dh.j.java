package dh;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class j extends f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f44594e = "((https?|ftp|file)://)?(?<![@|[A-Za-z0-9_]])([[A-Za-z0-9_]-_]+[.])+([a-zA-Z]+)(:[1-9]\\d*)?([/][[A-Za-z0-9_]+&#%?=.~_|!]*)*";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f44595f = "(((http(s?)|ftp|file):)?//)?((25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})(:[1-9]\\d*)?([/][[A-Za-z0-9_]+&#%?=.~_|!]*)*";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<String> f44598d = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Pattern f44596b = Pattern.compile(f44594e, 2);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Pattern f44597c = Pattern.compile(f44595f, 2);

    @Override // dh.f
    public boolean b(ah.b bVar, int[] iArr, int i10) {
        if (this.f44598d.size() <= 0) {
            return false;
        }
        iArr[i10] = iArr[i10] + 1;
        ArrayList arrayList = new ArrayList();
        bVar.f504g = arrayList;
        arrayList.addAll(this.f44598d);
        return true;
    }

    @Override // dh.f
    protected List<String> d(String str) {
        Matcher matcher = this.f44596b.matcher(str);
        ArrayList<String> arrayList = new ArrayList();
        int i10 = 0;
        while (matcher.find()) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            if (iStart > i10) {
                arrayList.add(str.substring(i10, iStart));
            }
            this.f44598d.add(str.substring(iStart, iEnd));
            i10 = iEnd;
        }
        if (i10 < str.length() - 1) {
            arrayList.add(str.substring(i10));
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : arrayList) {
            Matcher matcher2 = this.f44597c.matcher(str2);
            int i11 = 0;
            while (matcher2.find()) {
                int iStart2 = matcher2.start();
                int iEnd2 = matcher2.end();
                if (iStart2 > i11) {
                    arrayList2.add(str2.substring(i11, iStart2));
                }
                this.f44598d.add(str2.substring(iStart2, iEnd2));
                i11 = iEnd2;
            }
            if (i11 < str2.length() - 1) {
                arrayList2.add(str2.substring(i11));
            }
        }
        return arrayList2;
    }

    @Override // dh.f
    protected void f() {
        super.f();
        this.f44598d.clear();
    }
}
