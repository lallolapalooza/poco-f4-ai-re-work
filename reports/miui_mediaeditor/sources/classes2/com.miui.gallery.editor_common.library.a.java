package com.miui.gallery.editor_common.library;

import android.os.Build;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Long[] f5063a = {1018L};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Long[] f5064b = {34568L};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Long[] f5065c;
    public static final Long[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Long[] f5066e;
    public static final Long[] f;
    public static final Long[] g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Long[] f5067h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Long[] f5068i;
    public static final Long[] j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Long[] f5069k;
    public static final Long[] l;
    public static final Long[] m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Long[] f5070n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final HashMap<Long, String> f5071o;
    public static final String p;

    static {
        long j10;
        Long[] lArr = new Long[2];
        ii.j.a aVar = ii.j.d;
        long j11 = Long.MIN_VALUE;
        if (((Integer) aVar.a()).intValue() == 1) {
            j10 = Long.MIN_VALUE;
        } else {
            j10 = ((Integer) aVar.a()).intValue() == 2 ? 9001034L : 9002034L;
        }
        kotlinx.coroutines.internal.a.m(Long.valueOf(j10), miuix.appcompat.app.floatingactivity.i.u("㇝㇥ㇷ㇍\u31e6\u31eb\u31ed㇥㇆\u31eb㇢ㇾ\u31ebㇼ"), miuix.appcompat.app.floatingactivity.i.u("ㇽ㇥ㇷㆮ㇯㇢\u31e9㇡ㇼ\u31e7ㇺ\u31e6㇣ㆮ\u31e7\u31eaㆮㆫ\u31ea"));
        lArr[0] = Long.valueOf(j10);
        lArr[1] = 30000004L;
        f5065c = lArr;
        d = new Long[]{20002001L};
        f5066e = new Long[]{Long.valueOf(com.miui.mediaeditor.config.remover2.a.a()), 40007008L};
        f = new Long[]{5001005L};
        g = new Long[]{5003005L};
        f5067h = new Long[]{5002005L};
        f5068i = new Long[]{10281L};
        j = new Long[]{Long.valueOf(com.miui.mediaeditor.config.remover2.a.a()), 40010009L};
        f5069k = new Long[]{20022001L};
        l = new Long[]{20002001L, 20003003L};
        m = new Long[]{40030004L};
        Long[] lArr2 = new Long[2];
        lArr2[0] = 3001005L;
        if (p4.p.o(p4.p.D)) {
            j11 = 3004003;
        } else if (p4.p.o(p4.p.E)) {
            j11 = 3007002;
        } else if (p4.p.o(p4.p.A)) {
            j11 = 3005003;
        } else if (p4.p.o(p4.p.z)) {
            j11 = 3006001;
        } else if (p4.p.o(p4.p.F)) {
            j11 = 3003004;
        }
        lArr2[1] = Long.valueOf(j11);
        f5070n = lArr2;
        f5071o = new HashMap<>();
        String[] strArr = Build.SUPPORTED_ABIS;
        String[] strArr2 = Build.SUPPORTED_64_BIT_ABIS;
        p = (strArr2 == null || strArr2.length <= 0) ? strArr[0] : strArr2[0];
    }

    public static File a(long j10) {
        String str = f5071o.get(Long.valueOf(j10));
        if (str == null) {
            str = "libs";
        }
        return kotlinx.coroutines.internal.a.L.getDir(str, 0);
    }
}
