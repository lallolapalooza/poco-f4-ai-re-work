package b9;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Pattern f5567a;

    public a(Pattern pattern) {
        this.f5567a = pattern;
    }

    public boolean a(String str) {
        return this.f5567a.matcher(str).matches();
    }
}
