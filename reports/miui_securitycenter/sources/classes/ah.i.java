package ah;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class i {
    public static boolean a(String str) {
        for (int i10 = 0; i10 < str.length(); i10++) {
            if (!d(str.charAt(i10))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(List<String> list) {
        ArrayList<String> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = list.iterator();
        int length = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            length += next.length();
            boolean zE = e(next.charAt(0));
            int i10 = 0;
            for (int i11 = 1; i11 < next.length(); i11++) {
                if (zE != e(next.charAt(i11))) {
                    String strSubstring = next.substring(i10, i11);
                    if (zE) {
                        arrayList.add(strSubstring);
                    } else {
                        arrayList2.add(strSubstring);
                    }
                    zE = !zE;
                    i10 = i11;
                }
            }
            String strSubstring2 = next.substring(i10);
            if (zE) {
                arrayList.add(strSubstring2);
            } else {
                arrayList2.add(strSubstring2);
            }
        }
        int length2 = 0;
        int i12 = 0;
        int length3 = 0;
        for (String str : arrayList) {
            if (length2 < str.length()) {
                length2 = str.length();
            }
            if (str.length() > 3) {
                i12++;
            }
            length3 += str.length();
        }
        Iterator it2 = arrayList2.iterator();
        int i13 = 0;
        while (it2.hasNext()) {
            if (((String) it2.next()).length() > 1) {
                i13++;
            }
        }
        return (arrayList.size() > length / 4 || length3 * 3 < length) && i12 <= 1 && length2 < 8 && i13 * 2 > arrayList2.size();
    }

    public static String c(String str) {
        if (str.indexOf("//") != -1) {
            str = str.substring(str.indexOf("//") + 2);
        }
        return str.indexOf("/") != -1 ? str.substring(0, str.indexOf("/")) : str;
    }

    public static boolean d(char c10) {
        return 19968 <= c10 && c10 <= 40959;
    }

    public static boolean e(char c10) {
        return f(c10) || c10 == '.' || c10 == ' ' || c10 == ' ';
    }

    public static boolean f(char c10) {
        if (19968 <= c10 && c10 <= 40959) {
            return true;
        }
        if ('0' <= c10 && c10 <= '9') {
            return true;
        }
        if ('a' <= c10 && c10 <= 'z') {
            return true;
        }
        if ('A' > c10 || c10 > 'Z') {
            return 65296 <= c10 && c10 <= 65305;
        }
        return true;
    }

    public static double g(double d10) {
        return Math.log(1.0d - Math.exp(d10));
    }
}
