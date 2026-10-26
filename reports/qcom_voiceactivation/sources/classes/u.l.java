package u;

import G.q;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.soundmodel.BigSoundModel;
import com.quicinc.voice.activation.soundmodel.SVAVersion;
import com.quicinc.voice.activation.soundmodel.SoundModel;
import com.quicinc.voice.activation.xmlhelper.Type;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import p013n.C0027h0;

/* JADX INFO: compiled from: SoundModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class l implements b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f1242f = "l";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1243a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f1244b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f1245c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p010k.c f1246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f1247e;

    public l(String str, p010k.c cVar) {
        this.f1243a = str;
        this.f1246d = cVar;
        HandlerThread handlerThread = new HandlerThread(f1242f);
        handlerThread.start();
        this.f1247e = new Handler(handlerThread.getLooper());
    }

    public static /* synthetic */ boolean x(File file, String str) {
        return str.contains(".");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y() {
        Iterator it = this.f1246d.f().values().iterator();
        while (it.hasNext()) {
            D((p010k.b) it.next());
        }
    }

    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public final void w(Runnable runnable) {
        File[] fileArrListFiles = new File(this.f1243a).listFiles(new FilenameFilter() { // from class: u.i
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return l.x(file, str);
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(f1242f);
        sb.append(" load client file size ");
        sb.append(fileArrListFiles == null ? 0 : fileArrListFiles.length);
        q.t(sb.toString());
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            L(fileArrListFiles);
            q.r("reenableWakewordRecognitions");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: u.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1238a.y();
                }
            });
        }
        runnable.run();
        q.r("non-OneVoice models load finished");
    }

    public final synchronized void B(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f1244b.get(str);
        if (reentrantLock == null) {
            reentrantLock = new ReentrantLock();
            this.f1244b.put(str, reentrantLock);
        }
        reentrantLock.lock();
    }

    public final void C(v.j jVar) {
        boolean zL;
        String strB = jVar.b();
        String strE = jVar.e();
        String strD = jVar.d();
        File file = new File(this.f1243a, strB);
        if (!file.exists()) {
            boolean zMkdir = file.mkdir();
            File file2 = new File(file, strE);
            q.t(f1242f + " rebuildSoundModelDir groupDir and bigsoundmoelDir create : " + (zMkdir & file2.mkdir() & new File(file2, strD).mkdir()));
            return;
        }
        File file3 = new File(file, strE);
        if (file3.exists()) {
            zL = l(file3, false);
            q.t(f1242f + " rebuildSoundModelDir userIdDir delete:" + zL);
        } else {
            zL = file3.mkdir();
            q.t(f1242f + " rebuildSoundModelDir userIdDir create:" + zL);
        }
        File file4 = new File(file3, strD);
        if (file4.exists()) {
            q.t(f1242f + " rebuildSoundModelDir bigSoundModelDir delete:" + (l(file4, false) & zL));
            return;
        }
        q.t(f1242f + " rebuildSoundModelDir bigSoundModelDir create:" + (file4.mkdir() & zL));
    }

    public final void D(p010k.b bVar) {
        p010k.a aVarL = bVar.l();
        if (aVarL == null) {
            return;
        }
        String strB = aVarL.b();
        if (!v("android.permission.RECORD_AUDIO", strB)) {
            q.r("granted=false, group=" + bVar.j());
            p003d.g.u().c(bVar.j(), strB);
            return;
        }
        for (BigSoundModel bigSoundModel : bVar.i().values()) {
            q.r("group=" + bVar.j() + ", bigSoundModel=" + bigSoundModel.toString());
            C0027h0 c0027h0 = new C0027h0(bigSoundModel);
            c0027h0.e();
            bigSoundModel.setSoundModelConfiguration(c0027h0.d());
            p003d.g.v().o(bigSoundModel, p003d.g.g());
        }
    }

    public final boolean E(v.j jVar) {
        boolean zL = l(new File(new File(new File(this.f1243a, jVar.b()), jVar.e()), jVar.d()), true);
        q.t(f1242f + " remove client " + zL);
        return zL;
    }

    public final O.b F(BigSoundModel bigSoundModel) {
        O.b bVar = new O.b("bigsoundmodel");
        String soundModelFileName = bigSoundModel.getSoundModelFileName();
        Type type = Type.STRING;
        bVar.j(new O.g("SoundModelFileName", soundModelFileName, type));
        bVar.j(new O.g("Locale", bigSoundModel.getLocale(), type));
        String magicNumber = bigSoundModel.getMagicNumber();
        Type type2 = Type.INTEGER;
        bVar.j(new O.g("MagicNumber", magicNumber, type2));
        bVar.j(new O.g("SVAVersion", String.valueOf(bigSoundModel.getVersion().f()), type2));
        bVar.j(new O.g("Keyword", bigSoundModel.getKeyword(), type));
        bVar.j(new O.g("UserId", bigSoundModel.getUserId(), type));
        bVar.j(new O.g("BigSoundModelType", bigSoundModel.getType().c(), type));
        bVar.j(new O.g("SoundModelCount", String.valueOf(bigSoundModel.getSoundModelCount()), type2));
        return bVar;
    }

    public final void G(v.j jVar, BigSoundModel bigSoundModel) {
        List<SoundModel> soundModelList = bigSoundModel.getSoundModelList();
        if (soundModelList == null || soundModelList.size() <= 0) {
            return;
        }
        q.t(f1242f + " replaceBinary soundModel size " + soundModelList.size());
        File file = new File(new File(new File(this.f1243a, jVar.b()), jVar.e()), jVar.d());
        for (SoundModel soundModel : soundModelList) {
            String strE = soundModel.getAlgorithmType().e();
            final byte[] data = soundModel.getData();
            final File file2 = new File(file, strE + ".binary");
            this.f1247e.post(new Runnable() { // from class: u.k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1239a.z(file2, data);
                }
            });
        }
    }

    public final O.b H(BigSoundModel bigSoundModel) {
        p010k.a aVarH = this.f1246d.h(bigSoundModel.getSoundModelKey().b());
        if (aVarH == null) {
            return null;
        }
        O.b bVar = new O.b("client");
        String strB = aVarH.b();
        Type type = Type.STRING;
        bVar.j(new O.g("PackageName", strB, type));
        String strC = aVarH.c();
        bVar.j(new O.g("ClassName", strC, type));
        Type type2 = Type.BOOLEAN;
        bVar.j(new O.g("Preferred", "true", type2));
        bVar.j(new O.g("IsForLLMClient", String.valueOf(aVarH.d()), type2));
        bVar.j(new O.g("isUVDisabled", String.valueOf(aVarH.e()), type2));
        q.t(f1242f + " replaceClientFromBSM packagename = " + strB + " classname = " + strC + " isForLLMClient = " + aVarH.d() + " isUVDisabled = " + aVarH.e());
        return bVar;
    }

    public final void I(v.j jVar, BigSoundModel bigSoundModel) {
        StringBuilder sb = new StringBuilder();
        String str = f1242f;
        sb.append(str);
        sb.append(" targetBigSoundModel ");
        sb.append(bigSoundModel.toString());
        q.t(sb.toString());
        O.j jVarJ = (O.j) this.f1245c.get(jVar.f());
        if (jVarJ == null) {
            jVarJ = j(jVar.b(), jVar.e(), jVar.d());
            q.t(str + " create helper " + jVarJ);
            this.f1245c.put(jVar.f(), jVarJ);
        }
        J(jVarJ, bigSoundModel);
    }

    public final void J(O.j jVar, BigSoundModel bigSoundModel) {
        O.c cVar = new O.c("information");
        O.b bVarH = H(bigSoundModel);
        O.b bVarF = F(bigSoundModel);
        cVar.i(bVarH);
        cVar.i(bVarF);
        jVar.d(cVar);
    }

    public final void K(File[] fileArr, p010k.b bVar, String str) {
        for (File file : fileArr) {
            File[] fileArrListFiles = file.listFiles();
            StringBuilder sb = new StringBuilder();
            String str2 = f1242f;
            sb.append(str2);
            sb.append(" load sm+xml file size ");
            sb.append((fileArrListFiles == null || fileArrListFiles.length <= 0) ? 0 : fileArrListFiles.length);
            q.t(sb.toString());
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                String name = file.getName();
                BigSoundModel bigSoundModel = new BigSoundModel();
                String strJ = bVar.j();
                O.j jVarJ = j(strJ, str, name);
                O.e eVarI = jVarJ.i();
                if (eVarI == null) {
                    q.j("can not find sound model information " + strJ + "," + str + "," + name);
                    return;
                }
                m(eVarI, fileArrListFiles, bigSoundModel, bVar);
                v.j jVar = new v.j(strJ, name, str, bigSoundModel.getBigSoundModelLocale());
                bigSoundModel.setSoundModelKey(jVar);
                this.f1245c.put(jVar.f(), jVarJ);
                bVar.a(bigSoundModel);
                q.t(str2 + " group name = " + strJ + " class = " + bVar.l().c() + "\nload bigsoundmodel : \n" + bigSoundModel.toString());
            }
        }
    }

    public final void L(File[] fileArr) {
        boolean z2;
        for (File file : fileArr) {
            String name = file.getName();
            File[] fileArrListFiles = file.listFiles();
            q.t(file.getAbsolutePath());
            StringBuilder sb = new StringBuilder();
            sb.append(f1242f);
            sb.append(" load userId file size ");
            sb.append(fileArrListFiles == null ? 0 : fileArrListFiles.length);
            q.t(sb.toString());
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                if (p003d.g.f().j(name) == null) {
                    q.j("keyword is null, client " + name + " is invalid");
                } else {
                    int length = fileArrListFiles.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            z2 = false;
                            break;
                        }
                        File file2 = fileArrListFiles[i2];
                        String[] list = file2.list();
                        if (list != null && list.length > 0) {
                            q.t(file2.getAbsolutePath() + " is active");
                            z2 = true;
                            break;
                        }
                        i2++;
                    }
                    if (z2) {
                        p010k.b bVar = new p010k.b(name);
                        M(fileArrListFiles, bVar);
                        this.f1244b.put(name, new ReentrantLock());
                        this.f1246d.a(bVar);
                    }
                }
            }
        }
    }

    public final void M(File[] fileArr, p010k.b bVar) {
        for (File file : fileArr) {
            File[] fileArrListFiles = file.listFiles();
            q.t(file.getAbsolutePath());
            StringBuilder sb = new StringBuilder();
            sb.append(f1242f);
            sb.append(" load bigSM file size ");
            sb.append((fileArrListFiles == null || fileArrListFiles.length <= 0) ? 0 : fileArrListFiles.length);
            q.t(sb.toString());
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                K(fileArrListFiles, bVar, file.getName());
            }
        }
    }

    public final synchronized void N(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f1244b.get(str);
        if (reentrantLock != null && reentrantLock.isLocked()) {
            reentrantLock.unlock();
        }
    }

    public final void O(BigSoundModel bigSoundModel) {
        v.j soundModelKey = bigSoundModel.getSoundModelKey();
        C(soundModelKey);
        I(soundModelKey, bigSoundModel);
        G(soundModelKey, bigSoundModel);
    }

    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public final void z(File file, byte[] bArr) {
        try {
            Files.write(file.toPath(), bArr, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e2) {
            q.j(f1242f + ":" + e2.getMessage());
        }
    }

    @Override // u.b
    public void a(final Runnable runnable) {
        this.f1247e.post(new Runnable() { // from class: u.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f1236a.w(runnable);
            }
        });
    }

    @Override // u.b
    public void b(BigSoundModel bigSoundModel) {
        if (bigSoundModel == null || bigSoundModel.getSoundModelKey() == null) {
            return;
        }
        String strB = bigSoundModel.getSoundModelKey().b();
        B(strB);
        try {
            try {
                O(bigSoundModel);
            } catch (Exception e2) {
                q.j(e2.getMessage());
            }
        } finally {
            N(strB);
        }
    }

    @Override // u.b
    public boolean c(String str, String str2) {
        p010k.a aVar;
        boolean zK;
        if (str == null || str.trim().isEmpty() || str2 == null || str2.trim().isEmpty()) {
            return false;
        }
        B(str);
        boolean z2 = true;
        try {
            try {
                p010k.b bVarE = this.f1246d.e(str);
                if (bVarE != null) {
                    List listM = bVarE.m();
                    Iterator it = listM.iterator();
                    do {
                        if (!it.hasNext()) {
                            aVar = null;
                            break;
                        }
                        aVar = (p010k.a) it.next();
                    } while (!str2.equals(aVar.b()));
                    if (aVar != null) {
                        int size = listM.size();
                        if (size == 1) {
                            Map mapI = bVarE.i();
                            if (!mapI.isEmpty()) {
                                zK = true;
                                for (BigSoundModel bigSoundModel : mapI.values()) {
                                    try {
                                        bVarE.v(bigSoundModel);
                                        zK &= k(bigSoundModel);
                                    } catch (Exception e2) {
                                        e = e2;
                                        z2 = zK;
                                    }
                                }
                            }
                            q.j(e.getMessage());
                            return z2;
                        }
                        zK = true;
                        bVarE.c(str2);
                        if (size == 1) {
                            this.f1246d.j(str);
                        }
                        return zK;
                    }
                }
                return false;
            } catch (Exception e3) {
                e = e3;
            }
        } finally {
            N(str);
            this.f1244b.remove(str);
        }
    }

    @Override // u.b
    public boolean d(v.j jVar) {
        BigSoundModel bigSoundModelC;
        boolean zK;
        if (jVar == null || (bigSoundModelC = this.f1246d.c(jVar.b())) == null) {
            return false;
        }
        String strB = jVar.b();
        B(strB);
        try {
            try {
                zK = k(bigSoundModelC);
                N(strB);
            } catch (Exception e2) {
                q.j(e2.getMessage());
                N(strB);
                zK = true;
            }
            return zK;
        } catch (Throwable th) {
            N(strB);
            throw th;
        }
    }

    public final O.j j(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1243a);
        String str4 = File.separator;
        sb.append(str4);
        sb.append(str);
        sb.append(str4);
        sb.append(str2);
        sb.append(str4);
        sb.append(str3);
        sb.append(str4);
        sb.append("information.xml");
        return new O.j(new O.d(sb.toString(), true));
    }

    public final boolean k(BigSoundModel bigSoundModel) {
        v.j soundModelKey = bigSoundModel.getSoundModelKey();
        this.f1245c.remove(soundModelKey.f());
        return E(soundModelKey);
    }

    public final boolean l(File file, boolean z2) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return true;
        }
        boolean zL = true;
        for (File file2 : fileArrListFiles) {
            zL &= file2.isDirectory() ? l(file2, true) : !file2.getName().contains("qvaconfig_v4.xml") && file2.delete();
        }
        return (zL && z2) ? file.delete() : zL;
    }

    public final void m(O.e eVar, File[] fileArr, BigSoundModel bigSoundModel, p010k.b bVar) {
        for (File file : fileArr) {
            String strU = u(file.getName());
            if (".xml".equals(strU)) {
                r(eVar, bigSoundModel, bVar);
            } else if (".binary".equals(strU)) {
                o(file, bigSoundModel);
            }
        }
    }

    public final void n(O.b bVar, BigSoundModel bigSoundModel) {
        bigSoundModel.setMagicNumber("0x" + Integer.toHexString(bVar.b("MagicNumber")));
        bigSoundModel.setVersion(SVAVersion.b(bVar.b("SVAVersion")));
        bigSoundModel.setKeyword(bVar.d("Keyword"));
        bigSoundModel.setUserId(bVar.d("UserId"));
        bigSoundModel.setBigSoundModelLocale(bVar.d("Locale"));
    }

    public final void o(File file, BigSoundModel bigSoundModel) {
        SoundModel soundModel = new SoundModel();
        soundModel.setAlgorithmType(AlgorithmType.c(t(file.getName())));
        soundModel.setData(s(file));
        bigSoundModel.addSoundModel(soundModel);
    }

    public final void p(O.b bVar, p010k.b bVar2) {
        String strD = bVar.d("PackageName");
        String strD2 = bVar.d("ClassName");
        Boolean boolE = bVar.e("Preferred");
        Boolean boolE2 = bVar.e("IsForLLMClient");
        Boolean boolE3 = bVar.e("isUVDisabled");
        bVar2.t(strD, strD2, boolE2.booleanValue());
        if (boolE == null || boolE.booleanValue()) {
            bVar2.z(strD);
            bVar2.l().f(boolE3.booleanValue());
        }
    }

    public final void q(O.e eVar, BigSoundModel bigSoundModel, p010k.b bVar) {
        if (eVar == null) {
            return;
        }
        if (!eVar.m()) {
            Iterator it = ((O.c) eVar).h().iterator();
            while (it.hasNext()) {
                q((O.e) it.next(), bigSoundModel, bVar);
            }
            return;
        }
        O.b bVar2 = (O.b) eVar;
        String strK = eVar.k();
        strK.hashCode();
        if (strK.equals("client")) {
            p(bVar2, bVar);
        } else if (strK.equals("bigsoundmodel")) {
            n(bVar2, bigSoundModel);
        }
    }

    public final void r(O.e eVar, BigSoundModel bigSoundModel, p010k.b bVar) {
        q(eVar, bigSoundModel, bVar);
    }

    public final byte[] s(File file) {
        byte[] bArr = new byte[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                fileInputStream.close();
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e2) {
            q.j(f1242f + ":" + e2.getMessage());
        }
        return bArr;
    }

    public final String t(String str) {
        return str.substring(0, str.lastIndexOf(46));
    }

    public final String u(String str) {
        return str.substring(str.lastIndexOf("."));
    }

    public final boolean v(String str, String str2) {
        return p003d.g.p().checkPermission(str, str2) == 0;
    }
}
