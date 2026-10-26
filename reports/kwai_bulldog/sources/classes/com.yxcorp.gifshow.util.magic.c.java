package com.yxcorp.gifshow.util.magic;

import com.yxcorp.bugly.CrashReporter;
import com.yxcorp.gifshow.util.k1;
import com.yxcorp.gifshow.util.resource.y;
import com.yxcorp.utility.i;
import com.yxcorp.utility.n1;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ModelResourceUtil.java */
/* JADX INFO: loaded from: classes5.dex */
public class c {
    public static String a() {
        return e(com.yxcorp.gifshow.util.resource.model.b.f57255c.mResource) + File.separator + "/deformParams.json";
    }

    public static String b() {
        return e(com.yxcorp.gifshow.util.resource.model.b.f57253a.mResource);
    }

    public static String c() {
        return e("magic_tips");
    }

    public static String d() {
        String strG = g();
        if (!n1.p(strG)) {
            return strG;
        }
        List<File> arrayList = new ArrayList<>();
        try {
            for (File file : yd.a.b().f(".probe", false)) {
                if (file != null) {
                    arrayList.add(file);
                }
            }
        } catch (Exception e12) {
            CrashReporter.logCheckedException(e12, "ModelResourceUtil.class", "getMagicEmojiPath", 58);
            CrashReporter.logException(e12);
        }
        if (i.d(arrayList)) {
            arrayList = yd.a.b().d(false);
        }
        Iterator<File> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            File file2 = new File(it2.next(), "/magic_effect_resource/");
            if (file2.exists() && file2.isDirectory()) {
                return k1.i(file2.getAbsolutePath());
            }
        }
        if (arrayList.size() >= 1) {
            return k1.i(new File(arrayList.get(0), "/magic_effect_resource/").getAbsolutePath());
        }
        CrashReporter.logException(new Throwable("MAGIC_EMOJI find no cache path!"));
        return k1.i(new File(yd.a.b().j(dp.a.c()), "/magic_effect_resource/").getAbsolutePath());
    }

    public static String e(String str) {
        return d() + str;
    }

    public static File f(String str) {
        return new File(c(), String.valueOf(str.hashCode()));
    }

    private static String g() {
        Iterator<File> it2 = yd.a.b().d(false).iterator();
        while (it2.hasNext()) {
            File file = new File(it2.next(), "/.magic_model/magic_model");
            if (file.exists() && file.isDirectory()) {
                return k1.i(file.getAbsolutePath());
            }
        }
        return null;
    }

    public static String h() {
        return e(com.yxcorp.gifshow.util.resource.model.b.f57255c.mResource);
    }

    public static boolean i(String str) {
        return y.s0(str);
    }

    public static boolean j(String str) {
        return i(e(str));
    }

    public static boolean k(com.yxcorp.gifshow.util.resource.model.c cVar) {
        if (cVar == null) {
            return true;
        }
        return y.n0(cVar);
    }
}
