package z8;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Pattern f57388a = Pattern.compile("[\\u4e00-\\u9fa5]");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Pattern f57389b = Pattern.compile("^-?[0-9]+(\\.[0-9]*)?");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Pattern f57390c = Pattern.compile("^(?=.*[a-zA-Z])(?!.*\\s)(?!.*\\.)([a-zA-Z0-9!@#$%^&*+-~]){1,500}$");
}
