package dh;

/* JADX INFO: loaded from: classes3.dex */
public class a extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f44565a = {"Common", "100+5", "106Short", "106Common", "106Long", "9Pre5", "Other", "Null"};

    public static int b() {
        return f44565a.length;
    }

    public static boolean c(int i10) {
        return i10 == 0;
    }

    public static int d(String str) {
        if (str == null || str.length() == 0) {
            return 7;
        }
        if (str.charAt(0) != '1' || str.length() < 5) {
            return (str.charAt(0) == '9' && str.length() == 5) ? 5 : 6;
        }
        char cCharAt = str.charAt(1);
        if (str.length() == 11 && (cCharAt == '3' || cCharAt == '5' || cCharAt == '8' || (cCharAt == '4' && str.charAt(2) == '7'))) {
            return 0;
        }
        if (cCharAt != '0') {
            return 6;
        }
        if (str.length() == 5 && str.charAt(2) == '0') {
            return 1;
        }
        if (str.charAt(2) != '6') {
            return 6;
        }
        if (str.length() < 9) {
            return 2;
        }
        return str.length() < 12 ? 3 : 4;
    }
}
