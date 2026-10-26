package com.yxcorp.gifshow.util.magic;

import android.os.SystemClock;
import ao0.n0;
import ao0.r;
import com.google.gson.l;
import com.kuaishou.weapon.gp.f2;
import com.kwai.imsdk.internal.util.BitmapUtil;
import com.kwai.video.editorsdk2.EditorSdk2Utils;
import com.yxcorp.bugly.CrashReporter;
import com.yxcorp.gifshow.Gsons;
import com.yxcorp.gifshow.model.h;
import com.yxcorp.gifshow.util.resource.b0;
import com.yxcorp.utility.i;
import com.yxcorp.utility.m0;
import com.yxcorp.utility.n1;
import com.yxcorp.utility.t;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import yf1.d;
import yf1.g;

/* JADX INFO: compiled from: MagicEmojiResourceHelper.java */
/* JADX INFO: loaded from: classes5.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final long f57108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<String, com.yxcorp.gifshow.util.resource.model.c> f57109b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Map<String, com.yxcorp.gifshow.util.resource.model.c> f57110c;

    /* JADX INFO: compiled from: MagicEmojiResourceHelper.java */
    static class a implements Serializable {

        @lb.c(BitmapUtil.FILE_SCHEME)
        public String mFilePath;

        @lb.c("md5")
        public String mMd5Value = "";

        a() {
        }
    }

    /* JADX INFO: renamed from: com.yxcorp.gifshow.util.magic.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MagicEmojiResourceHelper.java */
    public static class C0898b implements Serializable {

        @lb.c("models")
        List<String> mModels;
    }

    /* JADX INFO: compiled from: MagicEmojiResourceHelper.java */
    public static class c implements Serializable {

        @lb.c("checkList")
        List<a> mCheckList;
    }

    static {
        List<String> list;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        f57108a = jElapsedRealtime;
        f57109b = new ConcurrentHashMap();
        f57110c = new ConcurrentHashMap();
        l lVar = new l();
        try {
            String strF = b0.f();
            lVar.I(f2.f21984d, Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
            com.yxcorp.apm.a.a("MERH_initV2", lVar.toString());
            if (!n1.p(strF)) {
                C0898b c0898b = (C0898b) Gsons.f36006b.l(strF, C0898b.class);
                lVar.I(f2.f21991k, Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
                com.yxcorp.apm.a.a("MERH_initV2", lVar.toString());
                if (c0898b != null && (list = c0898b.mModels) != null) {
                    for (String str : list) {
                        f57110c.put(str, new com.yxcorp.gifshow.util.resource.model.c(str));
                    }
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("setMagicModelList from preference ");
            sb2.append(f57110c.size());
        } catch (Exception e12) {
            CrashReporter.logCheckedException(e12, "MagicEmojiResourceHelper.class", "<clinit>", 73);
            e12.printStackTrace();
            b0.k();
            lVar.I(f2.f21992l, Long.valueOf(SystemClock.elapsedRealtime() - f57108a));
            com.yxcorp.apm.a.a("MERH_initV2", lVar.toString());
        }
        lVar.I(f2.f21993m, Long.valueOf(SystemClock.elapsedRealtime() - f57108a));
        StringBuilder sb3 = new StringBuilder();
        sb3.append("MERH_initV2 ");
        sb3.append(lVar.toString());
        com.yxcorp.apm.a.a("MERH_initV2", lVar.toString());
    }

    public static void b(com.yxcorp.gifshow.util.resource.model.c cVar) {
        String str;
        if (cVar == null || (str = cVar.mResource) == null) {
            return;
        }
        f57109b.put(str, cVar);
    }

    public static void c() {
        String[] strArr = {EditorSdk2Utils.SO_LIBRARY_TYPE_MMU, "ycnn_face_detect", "ylab", "face_3d_resource"};
        String strD = com.yxcorp.gifshow.util.magic.c.d();
        for (int i12 = 0; i12 < 4; i12++) {
            File file = new File(strD + strArr[i12]);
            if (file.exists()) {
                d.o(file);
            }
        }
    }

    public static com.yxcorp.gifshow.util.resource.model.c d(String str) {
        com.yxcorp.gifshow.util.resource.model.c cVar = f57109b.get(str);
        return cVar != null ? cVar : f57110c.get(str);
    }

    public static Map<String, String> e() {
        HashMap map = new HashMap();
        for (String str : f57109b.keySet()) {
            if (!map.containsKey(str)) {
                map.put(str, com.yxcorp.gifshow.util.magic.c.e(str));
            }
        }
        for (String str2 : f57110c.keySet()) {
            if (!map.containsKey(str2)) {
                map.put(str2, com.yxcorp.gifshow.util.magic.c.e(str2));
            }
        }
        return map;
    }

    public static List<com.yxcorp.gifshow.util.resource.model.c> f() {
        ArrayList arrayList = new ArrayList(f57109b.values());
        if (f57110c.size() > 0) {
            arrayList.addAll(f57110c.values());
        }
        return arrayList;
    }

    public static List<String> g(List<String> list) {
        if (i.d(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            com.yxcorp.gifshow.util.resource.model.c cVarD = d(str);
            if (cVarD == null) {
                arrayList.add(str);
            } else {
                if (com.yxcorp.gifshow.util.magic.c.k(cVarD)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("needDownloadYModels model -> ");
                    sb2.append(cVarD.getResourceName());
                    arrayList.add(str);
                }
                if (com.yxcorp.gifshow.util.resource.model.b.f57255c.mResource.equals(str)) {
                    com.yxcorp.gifshow.util.resource.model.c cVar = com.yxcorp.gifshow.util.resource.model.b.f57253a;
                    if (com.yxcorp.gifshow.util.magic.c.k(cVar)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("needDownloadYModels model -> ");
                        sb3.append(cVar.getResourceName());
                        arrayList.add(cVar.mResource);
                    }
                    com.yxcorp.gifshow.util.resource.model.c cVar2 = com.yxcorp.gifshow.util.resource.model.b.f57275w;
                    if (com.yxcorp.gifshow.util.magic.c.k(cVar2)) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("needDownloadYModels model -> ");
                        sb4.append(cVar2.getResourceName());
                        arrayList.add(cVar2.mResource);
                    }
                }
            }
        }
        return arrayList;
    }

    public static Map<String, String> h() {
        HashMap map = new HashMap();
        for (String str : f57109b.keySet()) {
            String strE = com.yxcorp.gifshow.util.magic.c.e(str);
            if (com.yxcorp.gifshow.util.magic.c.i(strE)) {
                map.put(str, strE);
            }
        }
        if (f57110c.size() > 0) {
            for (String str2 : f57110c.keySet()) {
                String strE2 = com.yxcorp.gifshow.util.magic.c.e(str2);
                if (com.yxcorp.gifshow.util.magic.c.i(strE2) && !map.containsKey(str2)) {
                    map.put(str2, strE2);
                }
            }
        }
        map.remove(com.yxcorp.gifshow.util.resource.model.b.f57253a.mResource);
        return map;
    }

    public static boolean i(h hVar) {
        if (hVar == null || n1.p(hVar.getResourceName())) {
            return false;
        }
        return f57109b.containsKey(hVar.getResourceName()) || f57110c.containsKey(hVar.getResourceName());
    }

    public static boolean j(List<String> list) {
        if (i.d(list)) {
            return false;
        }
        for (String str : list) {
            com.yxcorp.gifshow.util.resource.model.c cVarD = d(str);
            if (cVarD == null || com.yxcorp.gifshow.util.magic.c.k(cVarD)) {
                return true;
            }
            if (com.yxcorp.gifshow.util.resource.model.b.f57255c.mResource.equals(str) && (com.yxcorp.gifshow.util.magic.c.k(com.yxcorp.gifshow.util.resource.model.b.f57253a) || com.yxcorp.gifshow.util.magic.c.k(com.yxcorp.gifshow.util.resource.model.b.f57275w))) {
                return true;
            }
        }
        return false;
    }

    public static boolean k(String str) {
        return l(str, "check.json");
    }

    public static boolean l(String str, String str2) {
        List<a> list;
        File file = new File(str, str2);
        if (!file.exists()) {
            return true;
        }
        try {
            FileReader fileReader = new FileReader(file);
            c cVar = null;
            try {
                try {
                    cVar = (c) Gsons.f36006b.j(fileReader, c.class);
                } catch (Exception e12) {
                    CrashReporter.logCheckedException(e12, "MagicEmojiResourceHelper.class", "isResourceValid", 6);
                    try {
                        r.f5649a.logCustomEvent("ResourceValid", g.q(fileReader));
                    } catch (Exception e13) {
                        CrashReporter.logCheckedException(e13, "MagicEmojiResourceHelper.class", "isResourceValid", 10);
                        e13.printStackTrace();
                    }
                }
                if (cVar == null || (list = cVar.mCheckList) == null) {
                    fileReader.close();
                    return false;
                }
                for (a aVar : list) {
                    File file2 = new File(str, aVar.mFilePath);
                    com.yxcorp.apm.a.a("modelFileSize", "oom check, file = " + aVar.mFilePath + " size=" + file2.length());
                    String strC = t.c(d.W(file2));
                    String str3 = aVar.mMd5Value;
                    if (str3 != null && !str3.equalsIgnoreCase(strC)) {
                        n0.b("MagicEmojiResourceHelper", "md5 check failed. file: " + aVar.mFilePath + " , the md5 in checklist: " + aVar.mMd5Value + " , the md5 from file: " + strC);
                        fileReader.close();
                        return false;
                    }
                }
                fileReader.close();
                return true;
            } catch (Throwable th2) {
                CrashReporter.logCheckedException(th2, "MagicEmojiResourceHelper.class", "isResourceValid", 1);
                try {
                    fileReader.close();
                } catch (Throwable th3) {
                    CrashReporter.logCheckedException(th3, "MagicEmojiResourceHelper.class", "isResourceValid", -1);
                    th2.addSuppressed(th3);
                }
                throw th2;
            }
        } catch (Throwable th4) {
            CrashReporter.logCheckedException(th4, "MagicEmojiResourceHelper.class", "isResourceValid", 28);
            m0.g(th4);
        }
        CrashReporter.logCheckedException(th4, "MagicEmojiResourceHelper.class", "isResourceValid", 28);
        m0.g(th4);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void m(C0898b c0898b) {
        if (c0898b.mModels.size() < 1) {
            b0.p(null);
        } else {
            b0.p(Gsons.f36006b.v(c0898b));
        }
    }

    public static void n(String str, List<String> list) {
        if (i.d(list)) {
            return;
        }
        l lVar = new l();
        lVar.L("key", str);
        lVar.L("modelName", Gsons.f36006b.v(list));
        r.f5649a.logCustomEvent("model_missing_info", lVar.toString());
    }

    public static void o(List<String> list) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (String str : list) {
            if (!f57109b.containsKey(str)) {
                com.yxcorp.gifshow.util.resource.model.c cVar = f57110c.get(str);
                if (cVar == null) {
                    cVar = new com.yxcorp.gifshow.util.resource.model.c(str);
                }
                concurrentHashMap.put(str, cVar);
            }
        }
        f57110c = concurrentHashMap;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("setMagicModelList from net unDeclaredSize = ");
        sb2.append(f57110c.size());
        sb2.append(", allSize = ");
        sb2.append(list.size());
        final C0898b c0898b = new C0898b();
        c0898b.mModels = new ArrayList(f57110c.keySet());
        rn.d.c(new Runnable() { // from class: com.yxcorp.gifshow.util.magic.a
            @Override // java.lang.Runnable
            public final void run() {
                b.m(c0898b);
            }
        });
    }

    public static List<com.yxcorp.gifshow.util.resource.model.c> p(List<String> list) {
        if (i.d(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            Map<String, com.yxcorp.gifshow.util.resource.model.c> map = f57109b;
            if (map.containsKey(str)) {
                arrayList.add(map.get(str));
            } else if (f57110c.containsKey(str)) {
                arrayList.add(f57110c.get(str));
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" toCategory error!!!");
            }
        }
        return arrayList;
    }
}
