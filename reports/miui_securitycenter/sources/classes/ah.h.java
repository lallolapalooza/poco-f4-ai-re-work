package ah;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class h {
    private static String a(String str) {
        StringBuilder sb2 = new StringBuilder();
        boolean z10 = false;
        int i10 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < str.length(); i12++) {
            if (i.f(str.charAt(i12))) {
                i11++;
                sb2.append(str.charAt(i12));
                if ((i12 == 0 || !i.f(str.charAt(i12 - 1))) && (i12 == str.length() - 1 || !i.f(str.charAt(i12 + 1)))) {
                    i10++;
                }
            }
        }
        if (i10 >= 3 && (((double) i10) * 1.0d) / ((double) i11) >= 0.5d) {
            z10 = true;
        }
        if (z10) {
            return sb2.toString();
        }
        return null;
    }

    private static boolean b(char c10) {
        return c10 == ',' || c10 == '.' || c10 == ';' || c10 == ':' || c10 == '!' || c10 == 65292 || c10 == 12290 || c10 == 65307 || c10 == 12289 || c10 == 65306 || c10 == 65281;
    }

    public static String c(String str) {
        int length = str.length() - 1;
        while (length >= 0) {
            char cCharAt = str.charAt(length);
            if (cCharAt != 0 && cCharAt != '\n' && cCharAt != ' ' && cCharAt != '\t') {
                break;
            }
            length--;
        }
        String strSubstring = str.substring(0, length + 1);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i10 = 0;
        int i11 = 0;
        while (i10 < strSubstring.length()) {
            if (b(strSubstring.charAt(i10))) {
                arrayList.add(strSubstring.substring(i11, i10));
                arrayList2.add(Character.valueOf(strSubstring.charAt(i10)));
                i11 = i10 + 1;
            }
            i10++;
        }
        if (i11 < i10) {
            arrayList.add(strSubstring.substring(i11, i10));
        }
        boolean z10 = false;
        for (int i12 = 0; i12 < arrayList.size(); i12++) {
            String strA = a((String) arrayList.get(i12));
            if (strA != null) {
                arrayList.set(i12, strA);
                z10 = true;
            }
        }
        if (!z10) {
            return strSubstring;
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i13 = 0; i13 < arrayList2.size(); i13++) {
            sb2.append((String) arrayList.get(i13));
            sb2.append(arrayList2.get(i13));
        }
        if (arrayList2.size() < arrayList.size()) {
            sb2.append((String) arrayList.get(arrayList.size() - 1));
        }
        return sb2.toString();
    }
}
