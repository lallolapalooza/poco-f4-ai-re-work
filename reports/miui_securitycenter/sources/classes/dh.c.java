package dh;

import com.google.common.net.HttpHeaders;
import com.miui.maml.elements.TimepanelScreenElement;
import com.miui.networkassistant.utils.TelephonyUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class c extends f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f44569d = {"0123456789", "０１２３４５６７８９", "零一二三四五六七八九", "零壹贰叁肆伍陆柒捌玖"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String[] f44570e = {"①②③④⑤⑥⑦⑧⑨", "⒈⒉⒊⒋⒌⒍⒎⒏⒐", "⓵⓶⓷⓸⓹⓺⓻⓼⓽"};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String[][] f44571f = {new String[]{"I|loOBbgqzZ", "11100869922"}, new String[]{"｜ｏｑｌｂＯＩＢＺｚ", "1091601822"}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected static final String[] f44572g = {"0123456789", "０１２３４５６７８９", "①②③④⑤⑥⑦⑧⑨", "⒈⒉⒊⒋⒌⒍⒎⒏⒐", "⓵⓶⓷⓸⓹⓺⓻⓼⓽", "零一二三四五六七八九", "零壹贰叁肆伍陆柒捌玖"};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected static final int[] f44573h = {15, 16, 18, 19};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final String[] f44574i = {"BankCard", "Phone", "PhoneMobile", "Phone400", HttpHeaders.RANGE, TimepanelScreenElement.TAG_NAME, "Confus"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected ArrayList<ah.b.a> f44575b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected int[] f44576c = new int[a()];

    protected static char g(char c10) {
        for (String str : f44569d) {
            int iIndexOf = str.indexOf(c10);
            if (iIndexOf != -1) {
                return "0123456789".charAt(iIndexOf);
            }
        }
        for (String str2 : f44570e) {
            int iIndexOf2 = str2.indexOf(c10);
            if (iIndexOf2 != -1) {
                return "0123456789".charAt(iIndexOf2 + 1);
            }
        }
        int i10 = 0;
        while (true) {
            String[][] strArr = f44571f;
            if (i10 >= strArr.length) {
                return c10;
            }
            int iIndexOf3 = strArr[i10][0].indexOf(c10);
            if (iIndexOf3 != -1) {
                return strArr[i10][1].charAt(iIndexOf3);
            }
            i10++;
        }
    }

    protected static String h(String str) {
        StringBuilder sb2 = new StringBuilder();
        for (int i10 = 0; i10 < str.length(); i10++) {
            char cCharAt = str.charAt(i10);
            if (!l(cCharAt)) {
                sb2.append(g(cCharAt));
            }
        }
        return sb2.toString();
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0196  */
    /* JADX WARN: Code duplicated, block: B:102:0x019c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:105:0x01a2 A[LOOP:2: B:58:0x0120->B:105:0x01a2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:106:0x01ba A[PHI: r10
      0x01ba: PHI (r10v3 int) = (r10v2 int), (r10v2 int), (r10v4 int) binds: [B:56:0x0115, B:83:0x016b, B:78:0x015c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:109:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:112:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:57:0x0117  */
    /* JADX WARN: Code duplicated, block: B:62:0x0132  */
    /* JADX WARN: Code duplicated, block: B:63:0x0135  */
    private int i(String str, int i10, boolean z10, char c10, boolean z11, char c11) {
        int[] iArr;
        StringBuilder sb2;
        Iterator it;
        String string;
        int length;
        int i11 = 2;
        int i12 = -1;
        if (str.length() <= 2) {
            return -1;
        }
        boolean z12 = !n(str);
        ArrayList arrayList = new ArrayList();
        int i13 = 4;
        if (z10) {
            int i14 = 0;
            boolean z13 = false;
            for (int i15 = 0; i15 < str.length(); i15++) {
                if (str.charAt(i15) == c10) {
                    if (!z13) {
                        arrayList.add(str.substring(i14, i15));
                        z13 = true;
                    }
                    i14 = i15 + 1;
                } else {
                    z13 = false;
                }
            }
            arrayList.add(str.substring(i14));
            for (int i16 = 0; i16 < arrayList.size(); i16++) {
                arrayList.set(i16, h((String) arrayList.get(i16)));
            }
            if ("-—一~".indexOf(c10) != -1 && arrayList.size() == 2) {
                if (((String) arrayList.get(0)).charAt(0) == "0123456789".charAt(0) && !z11 && ((((String) arrayList.get(0)).length() == 3 || ((String) arrayList.get(0)).length() == 4) && (((String) arrayList.get(1)).length() == 7 || ((String) arrayList.get(1)).length() == 8))) {
                    i13 = 1;
                } else {
                    String strReplace = (String) arrayList.get(0);
                    String strReplace2 = (String) arrayList.get(1);
                    if (z11 && c11 != '.') {
                        strReplace = strReplace.replace(c11, '.');
                        strReplace2 = strReplace2.replace(c11, '.');
                    }
                    if (s(strReplace) && s(strReplace2) && Double.valueOf(strReplace).doubleValue() < Double.valueOf(strReplace2).doubleValue()) {
                        if (((String) arrayList.get(0)).equals(TelephonyUtil.VIRTUALOPT) && strReplace2.length() == 7 && !z11) {
                            i13 = 3;
                        }
                    }
                }
            }
            if (i13 < 0) {
                sb2 = new StringBuilder();
                it = arrayList.iterator();
                while (it.hasNext()) {
                    sb2.append(((String) it.next()).replaceAll("\\.", ""));
                    i11 = 2;
                    i12 = -1;
                }
                string = sb2.toString();
                length = string.length();
                if (length <= 19) {
                    i11 = i12;
                } else if (length != 15 || length == 16 || length == 19) {
                    if (o(string) || (z11 && z10)) {
                        i11 = i12;
                    } else {
                        i11 = 0;
                    }
                } else if (length > 12) {
                    i11 = i12;
                } else if (length >= 11) {
                    if (string.charAt(0) == "0123456789".charAt(0)) {
                        i13 = 1;
                    }
                    if (!a.c(a.d(string))) {
                        i11 = i13;
                    }
                } else if (length == 10) {
                    if (TelephonyUtil.VIRTUALOPT.equals(string.substring(0, 3))) {
                        i11 = 3;
                    } else {
                        i11 = i13;
                    }
                } else if (arrayList.size() == 1 && z11 && ":：".indexOf(c11) != i12) {
                    i11 = 5;
                } else if (z11 || z10 || string.charAt(0) <= '1' || !(length == 7 || length == 8)) {
                    i11 = i12;
                } else {
                    i11 = 1;
                }
            } else {
                i11 = i13;
            }
            if (i11 >= 0) {
                iArr = this.f44576c;
                iArr[i11] = iArr[i11] + 1;
                if (z12 || i10 != 0) {
                    iArr[6] = iArr[6] + 1;
                }
            }
            return i11;
        }
        arrayList.add(h(str));
        i13 = -1;
        if (i13 < 0) {
            sb2 = new StringBuilder();
            it = arrayList.iterator();
            while (it.hasNext()) {
                sb2.append(((String) it.next()).replaceAll("\\.", ""));
                i11 = 2;
                i12 = -1;
            }
            string = sb2.toString();
            length = string.length();
            if (length <= 19) {
                i11 = i12;
            } else if (length != 15) {
                if (o(string)) {
                    i11 = i12;
                } else {
                    i11 = i12;
                }
            } else if (o(string)) {
                i11 = i12;
            } else {
                i11 = i12;
            }
        } else {
            i11 = i13;
        }
        if (i11 >= 0) {
            iArr = this.f44576c;
            iArr[i11] = iArr[i11] + 1;
            if (z12) {
                iArr[6] = iArr[6] + 1;
            } else {
                iArr[6] = iArr[6] + 1;
            }
        }
        return i11;
    }

    protected static int j(char c10) {
        int i10 = 0;
        while (true) {
            String[] strArr = f44572g;
            if (i10 >= strArr.length) {
                return -1;
            }
            if (strArr[i10].indexOf(c10) != -1) {
                return i10;
            }
            i10++;
        }
    }

    protected static boolean k(char c10) {
        return ("I|loOBbgqzZ".indexOf(c10) == -1 && "｜ｏｑｌｂＯＩＢＺｚ".indexOf(c10) == -1) ? false : true;
    }

    protected static boolean l(char c10) {
        return ",-—一~ \u3000".indexOf(c10) != -1 || c10 == '\n' || c10 == '\t' || c10 == '\r';
    }

    private static boolean m(char c10) {
        return ":：".indexOf(c10) != -1 || c10 == '.';
    }

    protected static boolean n(String str) {
        int iJ;
        if (str == null || str.length() <= 0 || (iJ = j(str.charAt(0))) < 0) {
            return false;
        }
        for (int i10 = 1; i10 < str.length(); i10++) {
            char cCharAt = str.charAt(i10);
            if (j(cCharAt) != iJ && !l(cCharAt) && !m(cCharAt)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001b  */
    private static boolean o(String str) {
        boolean z10;
        boolean z11 = false;
        if (str.charAt(0) == '4' || str.charAt(0) == '5' || str.charAt(0) == '6') {
            boolean z12 = false;
            int i10 = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                int iCharAt = str.charAt(length) - '0';
                if (z12 && (iCharAt = iCharAt * 2) > 9) {
                    iCharAt -= 9;
                }
                i10 += iCharAt;
                z12 = !z12;
            }
            if (i10 % 10 == 0) {
                z10 = true;
            } else {
                z10 = false;
            }
        } else {
            z10 = false;
        }
        if (z10) {
            return z10;
        }
        if (str.length() != 19 && str.length() != 16) {
            return z10;
        }
        if (str.charAt(0) == '6' && str.charAt(1) == '2') {
            z11 = true;
        }
        return z11;
    }

    protected static boolean p(char c10) {
        return q(c10) && "零一二三四五六七八九".indexOf(c10) == -1 && "零壹贰叁肆伍陆柒捌玖".indexOf(c10) == -1;
    }

    protected static boolean q(char c10) {
        return j(c10) >= 0 || k(c10);
    }

    protected static int r(char c10) {
        return "０１２３４５６７８９".indexOf(c10) != -1 ? 1 : 0;
    }

    private static boolean s(String str) {
        return str.indexOf(46) == str.lastIndexOf(46);
    }

    @Override // dh.d
    protected int a() {
        return f44574i.length;
    }

    @Override // dh.f
    public boolean b(ah.b bVar, int[] iArr, int i10) {
        int i11 = 0;
        boolean z10 = false;
        while (true) {
            int[] iArr2 = this.f44576c;
            if (i11 >= iArr2.length) {
                break;
            }
            int i12 = iArr2[i11];
            if (i12 > 0) {
                z10 = true;
            }
            iArr[i10 + i11] = i12;
            i11++;
        }
        if (this.f44575b.size() > 0) {
            ArrayList arrayList = new ArrayList();
            bVar.f511n = arrayList;
            arrayList.addAll(this.f44575b);
        }
        return z10;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0084  */
    /* JADX WARN: Code duplicated, block: B:58:0x00d9 A[SYNTHETIC] */
    @Override // dh.f
    protected List<String> d(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        char c10 = '0';
        char c11 = '.';
        int i10 = -1;
        int i11 = 0;
        int iR = 0;
        boolean z10 = false;
        int i12 = -1;
        boolean z11 = false;
        int i13 = 0;
        int i14 = -1;
        while (true) {
            if (i13 >= str.length()) {
                break;
            }
            char cCharAt = str.charAt(i13);
            if (!q(cCharAt) || cCharAt == 19968) {
                if (i10 >= 0) {
                    if (l(cCharAt) && !z10) {
                        c10 = cCharAt;
                        i12 = i13;
                        z10 = true;
                    } else if (m(cCharAt) && !z11) {
                        c11 = cCharAt;
                        z11 = true;
                    } else if (cCharAt != c10 && cCharAt != c11) {
                        if (i10 > i11) {
                            arrayList.add(str.substring(i11, i10));
                        }
                        int i15 = i14 + 1;
                        String strSubstring = str.substring(i10, i15);
                        if (z10) {
                            z10 = i12 < i14;
                        }
                        int i16 = i(strSubstring, iR, z10, c10, z11, c11);
                        if (i16 >= 0) {
                            this.f44575b.add(new ah.b.a(strSubstring, i16));
                        }
                        i11 = i15;
                        i10 = -1;
                        iR = 0;
                        z10 = false;
                        i12 = -1;
                        z11 = false;
                    }
                }
            } else if (i10 >= 0) {
                if (p(cCharAt)) {
                    i14 = i13;
                }
            } else if (p(cCharAt)) {
                iR = r(cCharAt);
                i10 = i13;
                if (p(cCharAt)) {
                    i14 = i13;
                }
            }
            i13++;
        }
        if (i10 >= 0) {
            if (i10 > i11) {
                arrayList.add(str.substring(i11, i10));
            }
            int i17 = i14 + 1;
            if (i17 < str.length()) {
                arrayList.add(str.substring(i17));
            }
            String strSubstring2 = str.substring(i10, i17);
            int i18 = i(strSubstring2, iR, z10, c10, z11, c11);
            if (i18 >= 0) {
                this.f44575b.add(new ah.b.a(strSubstring2, i18));
            }
        } else {
            arrayList.add(str.substring(i11, str.length()));
        }
        return arrayList;
    }

    @Override // dh.f
    protected void f() {
        super.f();
        this.f44575b.clear();
        int i10 = 0;
        while (true) {
            int[] iArr = this.f44576c;
            if (i10 >= iArr.length) {
                return;
            }
            iArr[i10] = 0;
            i10++;
        }
    }
}
