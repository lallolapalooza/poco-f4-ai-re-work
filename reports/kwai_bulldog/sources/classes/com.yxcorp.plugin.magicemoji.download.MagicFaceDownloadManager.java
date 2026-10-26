package com.yxcorp.plugin.magicemoji.download;

import android.net.Uri;
import com.kwai.imsdk.internal.constants.KwaiConstants;
import com.yxcorp.bugly.CrashReporter;
import com.yxcorp.gifshow.api.magicemoji.MagicEmojiPlugin;
import com.yxcorp.gifshow.model.CDNUrl;
import com.yxcorp.gifshow.model.MagicEmoji;
import com.yxcorp.gifshow.model.k0;
import com.yxcorp.gifshow.util.resource.b0;
import com.yxcorp.utility.n1;
import com.yxcorp.utility.plugin.PluginManager;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import m20.w;
import qe1.o;
import zg1.m;

/* JADX INFO: loaded from: classes5.dex */
public class MagicFaceDownloadManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final MagicFaceDownloadHelper f61723a = new MagicFaceDownloadHelper();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<String, CommonMultiListener> f61724b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Map<String, List<MagicFaceDownloadHelper.Listener>> f61725c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f61726d = {5};

    public static class CommonMultiListener extends BaseMultiListener {
        public boolean mHotUpdate;
        private final long mStartTime;

        public CommonMultiListener(MagicEmoji.MagicFace magicFace) {
            super(magicFace);
            this.mStartTime = System.currentTimeMillis();
        }

        @Override // com.yxcorp.plugin.magicemoji.download.BaseMultiListener
        public void onFinalCompleted(MagicEmoji.MagicFace magicFace) {
            List list = (List) MagicFaceDownloadManager.f61725c.get(magicFace.getUniqueIdentifier());
            if (list != null) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    ((MagicFaceDownloadHelper.Listener) it2.next()).onCompleted(magicFace);
                }
            }
            MagicFaceDownloadManager.f61724b.remove(magicFace.getUniqueIdentifier());
            d.c(magicFace, this.mStartTime, this.mHotUpdate);
            ((MagicEmojiPlugin) PluginManager.get(MagicEmojiPlugin.class)).reportDownloadTime(magicFace, this.mStartTime, System.currentTimeMillis());
        }

        @Override // com.yxcorp.plugin.magicemoji.download.BaseMultiListener
        public void onFinalFailed(MagicEmoji.MagicFace magicFace, Throwable th2) {
            List list = (List) MagicFaceDownloadManager.f61725c.get(magicFace.getUniqueIdentifier());
            if (list != null) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    ((MagicFaceDownloadHelper.Listener) it2.next()).onFailed(magicFace, th2);
                }
            }
            MagicFaceDownloadManager.f61724b.remove(magicFace.getUniqueIdentifier());
            d.b(magicFace, th2);
            com.kuaishou.android.toast.c.c(zv.a.f100298a);
        }

        @Override // com.yxcorp.plugin.magicemoji.download.BaseMultiListener
        public void onFinalProgress(MagicEmoji.MagicFace magicFace, int i12, int i13) {
            List list = (List) MagicFaceDownloadManager.f61725c.get(magicFace.getUniqueIdentifier());
            if (list == null) {
                return;
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                ((MagicFaceDownloadHelper.Listener) it2.next()).onProgress(magicFace, i12, i13);
            }
        }
    }

    class a implements u81.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final File f61727a = yd.a.b().a(".magic_emoji");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final File f61728b = zd.i.f99661e;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final File f61729c = new File(com.yxcorp.gifshow.util.magic.c.d());

        a() {
        }

        private long b(long j12, List<String> list) {
            File[] fileArrListFiles;
            if (this.f61727a.exists() && this.f61727a.isDirectory() && (fileArrListFiles = this.f61727a.listFiles()) != null) {
                for (File file : fileArrListFiles) {
                    if (list.contains(file.getName())) {
                        j12 -= file.length();
                    } else {
                        yf1.d.o(file);
                    }
                }
            }
            return j12;
        }

        @Override // u81.d
        public long a(u81.e eVar) {
            long jI0 = this.f61727a.exists() ? 0 + yf1.d.i0(this.f61727a) : 0L;
            if (this.f61728b.exists()) {
                jI0 += yf1.d.i0(this.f61728b);
            }
            if (this.f61729c.exists()) {
                jI0 += yf1.d.i0(this.f61729c);
            }
            yf1.d.l(this.f61728b);
            yf1.d.l(this.f61729c);
            b0.a();
            if (!c(eVar)) {
                yf1.d.l(this.f61727a);
                return jI0;
            }
            List<MagicEmoji.MagicFace> listB = eVar.b();
            ArrayList arrayList = new ArrayList();
            Iterator<MagicEmoji.MagicFace> it2 = listB.iterator();
            while (it2.hasNext()) {
                arrayList.add(MagicFaceDownloadManager.h(it2.next()));
            }
            if (!com.yxcorp.utility.i.d(arrayList)) {
                return b(jI0, arrayList);
            }
            yf1.d.l(this.f61727a);
            return jI0;
        }

        public boolean c(u81.e eVar) {
            return (eVar == null || com.yxcorp.utility.i.d(eVar.b())) ? false : true;
        }
    }

    public static void c(@NonNull MagicEmoji.MagicFace magicFace, MagicFaceDownloadHelper.Listener listener) {
        z(listener);
        Map<String, List<MagicFaceDownloadHelper.Listener>> map = f61725c;
        List<MagicFaceDownloadHelper.Listener> copyOnWriteArrayList = map.get(magicFace.getUniqueIdentifier());
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        }
        copyOnWriteArrayList.add(listener);
        map.put(magicFace.getUniqueIdentifier(), copyOnWriteArrayList);
    }

    public static CommonMultiListener d(@NonNull MagicEmoji.MagicFace magicFace, @Nullable MagicFaceDownloadHelper.Listener listener) {
        Map<String, CommonMultiListener> map = f61724b;
        CommonMultiListener commonMultiListener = map.get(magicFace.getUniqueIdentifier());
        if (commonMultiListener != null) {
            c(magicFace, listener);
            return commonMultiListener;
        }
        CommonMultiListener commonMultiListener2 = new CommonMultiListener(magicFace);
        map.put(magicFace.getUniqueIdentifier(), commonMultiListener2);
        c(magicFace, listener);
        commonMultiListener2.createDownloadTask();
        return commonMultiListener2;
    }

    public static int e(@NonNull MagicEmoji.MagicFace magicFace) {
        CommonMultiListener commonMultiListener = f61724b.get(magicFace.getUniqueIdentifier());
        if (commonMultiListener == null) {
            return 0;
        }
        return commonMultiListener.calculatProgress();
    }

    public static MagicFaceDownloadHelper f() {
        return f61723a;
    }

    @NonNull
    public static File g(MagicEmoji.MagicFace magicFace) {
        if (magicFace != null && magicFace.isLocalTest()) {
            return new File(magicFace.localTestPath);
        }
        return new File(yd.a.b().a(".magic_emoji"), h(magicFace));
    }

    @NonNull
    public static String h(@Nullable MagicEmoji.MagicFace magicFace) {
        if (magicFace == null || n1.p(magicFace.getResource())) {
            if (com.yxcorp.utility.b0.f62055a) {
                throw new NullPointerException("magic face is null");
            }
            return "";
        }
        return magicFace.mId + KwaiConstants.KEY_SEPARATOR + i(magicFace);
    }

    @NonNull
    private static String i(@NonNull MagicEmoji.MagicFace magicFace) {
        if (n1.p(magicFace.getResource())) {
            return "";
        }
        try {
            return Uri.parse(magicFace.getResource()).getLastPathSegment();
        } catch (Throwable th2) {
            CrashReporter.logCheckedException(th2, "MagicFaceDownloadManager.class", "getMagicFaceUrlFlag", -75);
            th2.printStackTrace();
            return "";
        }
    }

    public static void j(com.yxcorp.plugin.magicemoji.api.entity.c cVar) {
        ArrayList arrayList = new ArrayList();
        Iterator<MagicEmoji> it2 = cVar.mMagicEmojis.iterator();
        while (it2.hasNext()) {
            for (MagicEmoji.MagicFace magicFace : it2.next().mMagicFaces) {
                if (com.yxcorp.utility.f.f(f61726d, magicFace.mVersion) >= 0) {
                    arrayList.add(magicFace);
                }
            }
        }
        x(arrayList);
    }

    public static boolean k(@Nullable MagicEmoji.MagicFace magicFace) {
        if (magicFace == null || !magicFace.isLocalTest()) {
            return (t(magicFace) || u(magicFace) || v(magicFace)) ? false : true;
        }
        return true;
    }

    public static boolean l(@Nullable MagicEmoji.MagicFace magicFace) {
        String[] list;
        CDNUrl[] cDNUrlArr;
        if (magicFace == null) {
            return false;
        }
        if (n1.p(magicFace.getResource()) && ((cDNUrlArr = magicFace.mResources) == null || cDNUrlArr.length == 0)) {
            return false;
        }
        File fileG = g(magicFace);
        if (!fileG.exists() || (list = fileG.list()) == null || list.length <= 0) {
            return false;
        }
        if (new File(fileG, "params.txt").exists()) {
            return true;
        }
        return new File(fileG, "params_720.txt").exists();
    }

    public static boolean m(@Nullable MagicEmoji.MagicFace magicFace) {
        return (!s(magicFace) || u(magicFace) || v(magicFace)) ? false : true;
    }

    public static boolean n(@Nullable MagicEmoji.MagicFace magicFace) {
        File fileG;
        CDNUrl[] cDNUrlArr;
        if (magicFace == null) {
            return false;
        }
        if ((n1.p(magicFace.getResource()) && ((cDNUrlArr = magicFace.mResources) == null || cDNUrlArr.length == 0)) || (fileG = g(magicFace)) == null) {
            return false;
        }
        return fileG.exists();
    }

    public static boolean o(@NonNull MagicEmoji.MagicFace magicFace) {
        return f61724b.containsKey(magicFace.getUniqueIdentifier());
    }

    public static boolean p(MagicEmoji.MagicFace magicFace) {
        k0 k0Var = magicFace.mPassThroughParams;
        return (k0Var == null || k0Var.mYModels == null) ? false : true;
    }

    private static boolean q(MagicEmoji.MagicFace magicFace) {
        if (magicFace == null) {
            return false;
        }
        return l(magicFace);
    }

    public static boolean r(MagicEmoji.MagicFace magicFace) {
        int i12 = h.f61745a;
        int i13 = magicFace.mVersion;
        if (i12 < i13 || com.yxcorp.utility.f.f(h.f61746b, i13) >= 0) {
            return false;
        }
        return magicFace.mVersion != h.f61747c || (w.m() && zd.i.f99661e.exists());
    }

    private static boolean s(MagicEmoji.MagicFace magicFace) {
        if (magicFace == null) {
            return false;
        }
        return n(magicFace);
    }

    public static boolean t(MagicEmoji.MagicFace magicFace) {
        return !q(magicFace);
    }

    public static boolean u(MagicEmoji.MagicFace magicFace) {
        return p(magicFace) && com.yxcorp.gifshow.util.magic.b.j(magicFace.mPassThroughParams.mYModels);
    }

    public static boolean v(MagicEmoji.MagicFace magicFace) {
        m<Integer, String> mVarB = o.b(magicFace);
        if (mVarB == null || mVarB.getFirst().intValue() == 1) {
            return false;
        }
        return !com.yxcorp.gifshow.util.magic.c.f(mVarB.getSecond()).exists();
    }

    public static void w(MagicEmoji.MagicFace magicFace) {
    }

    private static void x(List<MagicEmoji.MagicFace> list) {
        for (MagicEmoji.MagicFace magicFace : list) {
            if (!k(magicFace)) {
                d(magicFace, null);
            }
        }
    }

    public static void y() {
        u81.c.k(new a());
    }

    public static void z(MagicFaceDownloadHelper.Listener listener) {
        for (List<MagicFaceDownloadHelper.Listener> list : f61725c.values()) {
            if (list != null) {
                list.remove(listener);
            }
        }
    }
}
