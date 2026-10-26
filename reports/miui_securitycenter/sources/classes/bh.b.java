package bh;

import com.google.common.primitives.UnsignedBytes;

/* JADX INFO: loaded from: classes3.dex */
public class b {
    public static float a(byte[] bArr, int i10) {
        return Float.intBitsToFloat(b(bArr, i10));
    }

    public static int b(byte[] bArr, int i10) {
        return ((bArr[i10 + 0] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i10 + 3] & UnsignedBytes.MAX_VALUE) | ((bArr[i10 + 2] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i10 + 1] & UnsignedBytes.MAX_VALUE) << 16);
    }
}
