package p010k;

import java.io.PrintWriter;
import java.util.Objects;

/* JADX INFO: compiled from: Client.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f825a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f826b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f828d;

    public a(String str, String str2, boolean z2) {
        Objects.requireNonNull(str, "client's packageName should not be null");
        Objects.requireNonNull(str2, "client's serviceClass should not be null");
        this.f825a = str;
        this.f826b = str2;
        this.f827c = z2;
    }

    public void a(PrintWriter printWriter) {
        printWriter.println(this.f825a + "/" + this.f826b + "/forLLMClient:" + this.f827c);
    }

    public String b() {
        return this.f825a;
    }

    public String c() {
        return this.f826b;
    }

    public boolean d() {
        return this.f827c;
    }

    public boolean e() {
        return this.f828d;
    }

    public void f(boolean z2) {
        this.f828d = z2;
    }
}
