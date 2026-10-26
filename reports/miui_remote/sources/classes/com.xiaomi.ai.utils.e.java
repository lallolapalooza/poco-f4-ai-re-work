package com.xiaomi.ai.utils;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f17270a = "MemoryUtils";

    public static byte[] a(short[] sArr, int i10) {
        byte[] bArr = new byte[i10 * 2];
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = i11 * 2;
            short s10 = sArr[i11];
            bArr[i12 + 1] = (byte) (s10 >> 8);
            bArr[i12] = (byte) (s10 >> 0);
        }
        return bArr;
    }

    public static short[] a(byte[] bArr, int i10) {
        if (i10 % 2 != 0) {
            Log.e(f17270a, "toShortArray error ,len % 2 != 0 !!!");
            return null;
        }
        int i11 = i10 / 2;
        short[] sArr = new short[i11];
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = i12 * 2;
            sArr[i12] = (short) (bArr[i13] + (bArr[i13 + 1] << 8));
        }
        return sArr;
    }
}
