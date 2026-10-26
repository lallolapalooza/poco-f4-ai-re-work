package ah;

import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Set<String> f498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<String> f499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f500c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f501d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f502e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f503f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<String> f504g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f505h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f506i;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List<a> f511n;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f507j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f508k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f509l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f510m = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f512o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f513p = 0;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f514a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f515b;

        public a(String str, int i10) {
            this.f515b = str;
            this.f514a = i10;
        }
    }

    public b(String str, String str2) {
        String strK = k(str);
        this.f505h = strK;
        this.f506i = dh.a.d(strK);
        this.f501d = h.c(str2);
    }

    private String k(String str) {
        return (str.length() <= 3 || str.indexOf("+86") != 0) ? str : str.substring(3);
    }

    public void a(int i10) {
        this.f512o = i10 | this.f512o;
    }

    public String b() {
        return this.f505h;
    }

    public int c() {
        return this.f506i;
    }

    public String d() {
        return this.f501d;
    }

    public List<String> e() {
        return this.f500c;
    }

    public int[] f() {
        return this.f503f;
    }

    public int g() {
        return this.f513p;
    }

    public Set<String> h() {
        return this.f498a;
    }

    public List<String> i() {
        return this.f499b;
    }

    public int j() {
        return this.f512o;
    }

    public void l(List<String> list) {
        this.f500c = list;
    }

    public void m(int[] iArr) {
        this.f503f = iArr;
    }

    public void n(int i10) {
        this.f513p = i10;
    }

    public void o(Set<String> set) {
        this.f498a = set;
    }

    public void p(List<String> list) {
        this.f499b = list;
    }

    public String toString() {
        return "[" + this.f502e + "][" + this.f505h + "]:" + this.f501d;
    }
}
