package com.milab.aieditsdk;

import java.io.File;
import kotlinx.coroutines.internal.a;

/* JADX INFO: loaded from: classes.dex */
public class AiEdit {
    static {
        String str = a.L.getDir("libs", 0).getAbsolutePath() + File.separator;
        System.load(str + "libMNN.so");
        System.load(str + "libaiedit.so");
    }

    public static native long init(String str);

    public static native float[] process(long j, int[] iArr, float[] fArr);

    public static native int release(long j);
}
