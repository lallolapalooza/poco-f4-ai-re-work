package j8;

import android.R;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class m implements n6.a, x1.d0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static m f4503b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f4507g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static volatile w1.e f4508h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile w1.d f4509i;
    public static final int[] c = {1970500467, -1979711488, 184549375};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float[] f4504d = {8.0f, 50.0f, 200.0f};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static char[] f4505e = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static byte[] f4506f = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f4510j = {R.attr.color, R.attr.alpha, R.attr.lStar, miuix.animation.R.attr.alpha, miuix.animation.R.attr.lStar};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f4511k = {miuix.animation.R.attr.fontProviderAuthority, miuix.animation.R.attr.fontProviderCerts, miuix.animation.R.attr.fontProviderFetchStrategy, miuix.animation.R.attr.fontProviderFetchTimeout, miuix.animation.R.attr.fontProviderPackage, miuix.animation.R.attr.fontProviderQuery, miuix.animation.R.attr.fontProviderSystemFontFamily};
    public static final int[] l = {R.attr.font, R.attr.fontWeight, R.attr.fontStyle, R.attr.ttcIndex, R.attr.fontVariationSettings, miuix.animation.R.attr.font, miuix.animation.R.attr.fontStyle, miuix.animation.R.attr.fontVariationSettings, miuix.animation.R.attr.fontWeight, miuix.animation.R.attr.ttcIndex};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f4512m = {R.attr.startColor, R.attr.endColor, R.attr.type, R.attr.centerX, R.attr.centerY, R.attr.gradientRadius, R.attr.tileMode, R.attr.centerColor, R.attr.startX, R.attr.startY, R.attr.endX, R.attr.endY};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f4513n = {R.attr.color, R.attr.offset};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final m f4514o = new m();

    public /* synthetic */ m() {
    }

    public static String b(byte[] bArr) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i10 = 0;
        while (i10 < length) {
            int i11 = i10 + 1;
            int i12 = bArr[i10] & 255;
            if (i11 == length) {
                char[] cArr = f4505e;
                stringBuffer.append(cArr[i12 >>> 2]);
                stringBuffer.append(cArr[(i12 & 3) << 4]);
                str = "==";
            } else {
                int i13 = i11 + 1;
                int i14 = bArr[i11] & 255;
                if (i13 == length) {
                    char[] cArr2 = f4505e;
                    stringBuffer.append(cArr2[i12 >>> 2]);
                    stringBuffer.append(cArr2[((i12 & 3) << 4) | ((i14 & 240) >>> 4)]);
                    stringBuffer.append(cArr2[(i14 & 15) << 2]);
                    str = "=";
                } else {
                    int i15 = i13 + 1;
                    int i16 = bArr[i13] & 255;
                    char[] cArr3 = f4505e;
                    stringBuffer.append(cArr3[i12 >>> 2]);
                    stringBuffer.append(cArr3[((i12 & 3) << 4) | ((i14 & 240) >>> 4)]);
                    stringBuffer.append(cArr3[((i14 & 15) << 2) | ((i16 & 192) >>> 6)]);
                    stringBuffer.append(cArr3[i16 & 63]);
                    i10 = i15;
                }
            }
            stringBuffer.append(str);
            break;
        }
        return stringBuffer.toString();
    }

    public static byte[] c(String str) {
        try {
            return e(str);
        } catch (UnsupportedEncodingException e10) {
            f7.a.j(f7.a.a("Base64Util"), "decode e", e10);
            return new byte[0];
        }
    }

    public static byte[] e(String str) throws UnsupportedEncodingException {
        int i10;
        byte b2;
        int i11;
        byte b10;
        int i12;
        byte b11;
        int i13;
        byte b12;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = str.getBytes("US-ASCII");
        int length = bytes.length;
        int i14 = 0;
        loop0: while (i14 < length) {
            while (true) {
                i10 = i14 + 1;
                b2 = f4506f[bytes[i14]];
                if (i10 >= length || b2 != -1) {
                    break;
                }
                i14 = i10;
            }
            if (b2 == -1) {
                break;
            }
            while (true) {
                i11 = i10 + 1;
                b10 = f4506f[bytes[i10]];
                if (i11 >= length || b10 != -1) {
                    break;
                }
                i10 = i11;
            }
            if (b10 == -1) {
                break;
            }
            stringBuffer.append((char) ((b2 << 2) | ((b10 & 48) >>> 4)));
            while (true) {
                i12 = i11 + 1;
                byte b13 = bytes[i11];
                if (b13 == 61) {
                    break loop0;
                }
                b11 = f4506f[b13];
                if (i12 >= length || b11 != -1) {
                    break;
                }
                i11 = i12;
            }
            if (b11 == -1) {
                break;
            }
            stringBuffer.append((char) (((b10 & 15) << 4) | ((b11 & 60) >>> 2)));
            while (true) {
                i13 = i12 + 1;
                byte b14 = bytes[i12];
                if (b14 == 61) {
                    break loop0;
                }
                b12 = f4506f[b14];
                if (i13 >= length || b12 != -1) {
                    break;
                }
                i12 = i13;
            }
            if (b12 == -1) {
                break;
            }
            stringBuffer.append((char) (b12 | ((b11 & 3) << 6)));
            i14 = i13;
        }
        return stringBuffer.toString().getBytes("iso8859-1");
    }

    public static void f() {
        int i10 = f4507g;
        if (i10 > 0) {
            f4507g = i10 - 1;
        }
    }

    public static Object g(Object obj, Field field) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e10) {
            throw new RuntimeException(e10);
        }
    }

    public static final String h(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final String i(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static Method j(Class cls, String str, Class... clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e10) {
            throw new RuntimeException(e10);
        }
    }

    public static Object k(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e10) {
            throw new RuntimeException(e10);
        } catch (InvocationTargetException e11) {
            throw new RuntimeException(e11);
        }
    }

    public static final String l(w7.d dVar) {
        Object objK;
        if (dVar instanceof m8.b) {
            return dVar.toString();
        }
        try {
            objK = dVar + '@' + i(dVar);
        } catch (Throwable th) {
            objK = k7.b.k(th);
        }
        if (t7.b.a(objK) != null) {
            objK = ((Object) dVar.getClass().getName()) + '@' + i(dVar);
        }
        return (String) objK;
    }

    @Override // n6.a
    public String a(String str) {
        byte[] bArrDigest;
        byte[] bytes = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bArrDigest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e10) {
            y2.e.r(e10);
            bArrDigest = null;
        }
        return new BigInteger(bArrDigest).abs().toString(36);
    }

    @Override // x1.d0
    public Object d(y1.c cVar, float f10) {
        boolean z10 = cVar.F() == 1;
        if (z10) {
            cVar.a();
        }
        float fB = (float) cVar.B();
        float fB2 = (float) cVar.B();
        while (cVar.s()) {
            cVar.J();
        }
        if (z10) {
            cVar.f();
        }
        return new a2.c((fB / 100.0f) * f10, (fB2 / 100.0f) * f10);
    }
}
