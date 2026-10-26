package u;

import G.q;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.soundmodel.SoundModel;
import com.quicinc.voice.activation.xmlhelper.Type;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: OneVoiceSoundModelProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f1230f = "g";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f1232b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f1233c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p016q.c f1234d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f1235e;

    public g(String str, p016q.c cVar) {
        this.f1231a = str;
        this.f1234d = cVar;
        HandlerThread handlerThread = new HandlerThread(f1230f);
        handlerThread.start();
        this.f1235e = new Handler(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E() {
        Iterator it = p003d.g.j().e().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((p016q.a) it.next()).e().iterator();
            while (it2.hasNext()) {
                K((p010k.b) it2.next());
            }
        }
    }

    public static /* synthetic */ boolean G(File file, String str) {
        return str.contains(".");
    }

    public final O.b A(p017r.f fVar, Path path) {
        if (fVar == null) {
            return null;
        }
        O.b bVar = new O.b("SecondStagePDKModel");
        String strH = fVar.h();
        Type type = Type.STRING;
        bVar.j(new O.g("SoundModelFileName", strH, type));
        bVar.j(new O.g("Keyword", fVar.f(), type));
        bVar.j(new O.g("Locale", fVar.g(), type));
        if (fVar.b() != null && fVar.b().getAlgorithmType() != null) {
            bVar.j(new O.g("Path", path.toAbsolutePath().toString() + "/" + fVar.b().getAlgorithmType().e() + ".binary", type));
        }
        return bVar;
    }

    public final O.b B(p017r.g gVar, Path path) {
        String strF = null;
        if (gVar == null) {
            return null;
        }
        O.b bVar = new O.b("SecondStageUVModel");
        String strI = gVar.i();
        Type type = Type.STRING;
        bVar.j(new O.g("UserId", strI, type));
        bVar.j(new O.g("Keyword", gVar.h(), type));
        bVar.j(new O.g("IsGeneralUV", gVar.j() ? "true" : "false", Type.BOOLEAN));
        if (gVar.j()) {
            strF = p003d.g.m().f();
        } else if (gVar.b() != null && gVar.b().getAlgorithmType() != null) {
            strF = path.toAbsolutePath().toString() + "/" + gVar.b().getAlgorithmType().e() + ".binary";
        }
        bVar.j(new O.g("Path", strF, type));
        return bVar;
    }

    public final boolean C(String str, String str2) {
        return p003d.g.p().checkPermission(str, str2) == 0;
    }

    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public final void D(Runnable runnable) {
        File[] fileArrListFiles = new File(this.f1231a).listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            R(fileArrListFiles);
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: u.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f1226a.E();
            }
        });
        runnable.run();
        q.r("OneVoice models load finished");
    }

    public final synchronized void I(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f1232b.get(str);
        if (reentrantLock == null) {
            reentrantLock = new ReentrantLock();
            this.f1232b.put(str, reentrantLock);
        }
        reentrantLock.lock();
    }

    public final void J(p016q.a aVar, p010k.b bVar) {
        Path path;
        String strM = aVar.m();
        String strJ = bVar.j();
        p017r.h hVarN = bVar.n();
        if (hVarN == null) {
            path = Paths.get(this.f1231a, strM, strJ);
        } else {
            path = Paths.get(this.f1231a, strM, strJ, TextUtils.isEmpty(hVarN.f()) ? "null" : hVarN.f());
        }
        try {
            if (Files.exists(path, new LinkOption[0])) {
                q(path.toFile(), false);
            } else {
                Files.createDirectories(path, new FileAttribute[0]);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public final void K(p010k.b bVar) {
        p010k.a aVarL = bVar.l();
        if (aVarL == null) {
            return;
        }
        if (C("android.permission.RECORD_AUDIO", aVarL.b())) {
            p003d.g.o().p(bVar, p003d.g.l());
            p003d.g.o().m(bVar);
        } else {
            q.r("granted=false, group=" + bVar.j());
        }
    }

    public final void L(p016q.a aVar, p010k.b bVar) {
        p017r.h hVarN = bVar.n();
        if (hVarN == null) {
            return;
        }
        Path path = Paths.get(this.f1231a, aVar.m(), bVar.j(), TextUtils.isEmpty(hVarN.f()) ? "null" : hVarN.f());
        p017r.f fVarD = hVarN.d();
        p017r.g gVarE = hVarN.e();
        P(path, fVarD);
        if (gVarE != null && !gVarE.j()) {
            P(path, gVarE);
        }
        M(bVar, path);
    }

    public final void M(p010k.b bVar, Path path) {
        p017r.h hVarN = bVar.n();
        if (hVarN == null) {
            return;
        }
        Path path2 = Paths.get(path.toAbsolutePath().toString(), "information.xml");
        O.j jVarP = (O.j) this.f1233c.get(bVar.o());
        if (jVarP == null) {
            jVarP = p(path2.toAbsolutePath().toString());
            q.t(f1230f + " create helper " + jVarP);
            this.f1233c.put(bVar.o(), jVarP);
        }
        O.c cVar = new O.c("information");
        O.b bVarV = v(bVar);
        O.b bVarZ = z(hVarN);
        O.b bVarA = A(hVarN.d(), path);
        O.b bVarB = B(hVarN.e(), path);
        cVar.i(bVarV);
        cVar.i(bVarZ);
        cVar.i(bVarA);
        cVar.i(bVarB);
        jVarP.d(cVar);
    }

    public final void N(p016q.a aVar) {
        O(aVar, Paths.get(this.f1231a, aVar.m()));
    }

    public final void O(p016q.a aVar, Path path) {
        Path path2 = Paths.get(path.toAbsolutePath().toString(), "information.xml");
        if (Files.exists(path2, new LinkOption[0])) {
            return;
        }
        O.j jVarP = (O.j) this.f1233c.get(aVar.l());
        if (jVarP == null) {
            jVarP = p(path2.toAbsolutePath().toString());
            q.t(f1230f + " create helper " + jVarP);
            this.f1233c.put(aVar.l(), jVarP);
        }
        O.c cVar = new O.c("information");
        O.b bVarX = x(aVar);
        O.b bVarW = w(aVar.h());
        O.b bVarY = y(aVar.n());
        cVar.i(bVarX);
        cVar.i(bVarW);
        cVar.i(bVarY);
        jVarP.d(cVar);
    }

    public final void P(Path path, p017r.i iVar) {
        if (iVar == null || iVar.b() == null || iVar.b().getSize() == 0) {
            return;
        }
        final byte[] data = iVar.b().getData();
        String strE = iVar.b().getAlgorithmType().e();
        final Path path2 = Paths.get(path.toAbsolutePath().toString(), strE + ".binary");
        this.f1235e.post(new Runnable() { // from class: u.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f1227a.F(path2, data);
            }
        });
    }

    public final void Q(File[] fileArr, p016q.a aVar) {
        for (File file : fileArr) {
            p010k.b bVar = new p010k.b(file.getName());
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                S(fileArrListFiles, bVar, aVar);
            }
        }
    }

    public final void R(File[] fileArr) {
        for (File file : fileArr) {
            if (file.getName().contains("-")) {
                O.j jVarP = p(Paths.get(this.f1231a, file.getName(), "information.xml").toAbsolutePath().toString());
                O.e eVarI = jVarP.i();
                if (eVarI == null) {
                    q.j("can not find model group information " + file.getName());
                    return;
                }
                q.e("traverseModelGroupFiles " + eVarI.toString());
                p016q.a aVarJ = j(eVarI);
                aVarJ.a(i(eVarI), k(eVarI));
                this.f1233c.put(aVarJ.l(), jVarP);
                File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: u.f
                    @Override // java.io.FilenameFilter
                    public final boolean accept(File file2, String str) {
                        return g.G(file2, str);
                    }
                });
                if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                    Q(fileArrListFiles, aVarJ);
                }
            }
        }
    }

    public final void S(File[] fileArr, p010k.b bVar, p016q.a aVar) {
        for (File file : fileArr) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                O.j jVarP = p(Paths.get(file.getAbsolutePath(), "information.xml").toAbsolutePath().toString());
                O.e eVarI = jVarP.i();
                if (eVarI == null) {
                    q.j("can not find client group information " + file.getAbsolutePath());
                    return;
                }
                q.e("traverseUserIdFiles " + eVarI.toString());
                s(eVarI, bVar);
                p017r.f fVarL = l(eVarI);
                p017r.g gVarN = n(eVarI);
                p017r.h hVarM = m(eVarI);
                hVarM.k(fVarL);
                hVarM.l(gVarN);
                hVarM.j(p003d.g.f());
                bVar.b(hVarM);
                p003d.g.j().a(aVar, bVar);
                p003d.g.j().i(aVar);
                this.f1233c.put(bVar.o(), jVarP);
            }
        }
    }

    public final synchronized void T(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f1232b.get(str);
        if (reentrantLock != null && reentrantLock.isLocked()) {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public final void F(Path path, byte[] bArr) {
        try {
            Files.write(path, bArr, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e2) {
            q.j(f1230f + ":" + e2.getMessage());
        }
    }

    @Override // u.a
    public void a(final Runnable runnable) {
        this.f1235e.post(new Runnable() { // from class: u.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f1224a.D(runnable);
            }
        });
    }

    @Override // u.a
    public void b(p010k.b bVar, boolean z2) {
        p016q.a aVarG = this.f1234d.g(bVar);
        if (aVarG != null) {
            String strM = aVarG.m();
            I(strM);
            if (!z2) {
                try {
                    try {
                        J(aVarG, bVar);
                        N(aVarG);
                    } catch (Exception e2) {
                        q.j(e2.getMessage());
                    }
                } finally {
                    T(strM);
                }
            }
            L(aVarG, bVar);
        }
    }

    @Override // u.a
    public boolean d(p010k.b bVar) {
        p016q.a aVarG = this.f1234d.g(bVar);
        if (aVarG == null) {
            return false;
        }
        String strM = aVarG.m();
        I(strM);
        boolean zR = r(aVarG, bVar);
        if (zR) {
            p003d.g.e().j(bVar.j());
            this.f1234d.j(bVar);
        }
        T(strM);
        return zR;
    }

    public final p017r.a i(O.e eVar) {
        return p017r.a.a(eVar.o("FirstStageModel", null).d("Path"), p003d.g.f());
    }

    public final p016q.a j(O.e eVar) {
        O.e eVarO = eVar.o("ModelGroup", null);
        return new p016q.a(eVarO.d("ModelGroupKwHash"), eVarO.d("Locale"));
    }

    public final p017r.b k(O.e eVar) {
        O.e eVarO = eVar.o("MiddleStageModel", null);
        if (eVarO != null) {
            return p017r.c.c(eVarO.d("Path"), p003d.g.f());
        }
        return null;
    }

    public final p017r.f l(O.e eVar) {
        O.e eVarO = eVar.o("SecondStagePDKModel", null);
        if (eVarO == null) {
            return null;
        }
        String strD = eVarO.d("SoundModelFileName");
        String strD2 = eVarO.d("Locale");
        String strD3 = eVarO.d("Keyword");
        String strD4 = eVarO.d("Path");
        p017r.f fVar = new p017r.f(strD3, strD, strD2);
        fVar.j(o(strD4));
        return fVar;
    }

    public final p017r.h m(O.e eVar) {
        String strD = eVar.o("SecondStageModel", null).d("SoundModelFileName");
        p017r.h hVar = new p017r.h();
        hVar.i(strD);
        return hVar;
    }

    public final p017r.g n(O.e eVar) {
        O.e eVarO = eVar.o("SecondStageUVModel", null);
        if (eVarO == null) {
            return null;
        }
        String strD = eVarO.d("UserId");
        String strD2 = eVarO.d("Keyword");
        boolean zBooleanValue = eVarO.e("IsGeneralUV").booleanValue();
        String strD3 = eVarO.d("Path");
        if (zBooleanValue) {
            return null;
        }
        p017r.g gVar = new p017r.g(strD, strD2, false);
        gVar.l(o(strD3));
        return gVar;
    }

    public final SoundModel o(String str) {
        SoundModel soundModel = new SoundModel();
        soundModel.setAlgorithmType(AlgorithmType.c(t(str)));
        soundModel.setData(u(str));
        return soundModel;
    }

    public final O.j p(String str) {
        return new O.j(new O.d(str, true));
    }

    public final boolean q(File file, boolean z2) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return true;
        }
        boolean zQ = true;
        for (File file2 : fileArrListFiles) {
            zQ &= file2.isDirectory() ? q(file2, true) : file2.delete();
        }
        return (zQ && z2) ? file.delete() : zQ;
    }

    public final boolean r(p016q.a aVar, p010k.b bVar) {
        String strM = aVar.m();
        String strJ = bVar.j();
        p017r.h hVarN = bVar.n();
        if (hVarN == null) {
            return false;
        }
        Path path = Paths.get(this.f1231a, strM, strJ, TextUtils.isEmpty(hVarN.f()) ? "null" : hVarN.f());
        boolean zQ = q(path.toFile(), true);
        if (zQ) {
            File file = path.getParent().toFile();
            String[] list = file.list();
            if (list == null || list.length == 0) {
                file.delete();
            }
            File file2 = path.getParent().getParent().toFile();
            String[] list2 = file2.list();
            if (list2 == null || list2.length <= 1) {
                q(file2, true);
            }
        }
        return zQ;
    }

    public final void s(O.e eVar, p010k.b bVar) {
        O.e eVarO = eVar.o("ClientGroup", null);
        String strD = eVarO.d("PackageName");
        String strD2 = eVarO.d("ClassName");
        Boolean boolE = eVarO.e("Preferred");
        Boolean boolE2 = eVarO.e("IsForLLMClient");
        Boolean boolE3 = eVarO.e("isUVDisabled");
        bVar.y(eVarO.d("Locale"));
        bVar.t(strD, strD2, boolE2.booleanValue());
        p003d.g.e().a(bVar);
        if (boolE == null || boolE.booleanValue()) {
            bVar.z(strD);
            bVar.l().f(boolE3.booleanValue());
        }
    }

    public final String t(String str) {
        return str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(46));
    }

    public final byte[] u(String str) {
        byte[] bArr = new byte[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
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
            q.j(f1230f + ":" + e2.getMessage());
        }
        return bArr;
    }

    public final O.b v(p010k.b bVar) {
        O.b bVar2 = new O.b("ClientGroup");
        p010k.a aVarL = bVar.l();
        if (aVarL != null) {
            String strB = aVarL.b();
            String strC = aVarL.c();
            boolean zD = aVarL.d();
            boolean zE = aVarL.e();
            Type type = Type.STRING;
            bVar2.j(new O.g("PackageName", strB, type));
            bVar2.j(new O.g("ClassName", strC, type));
            Type type2 = Type.BOOLEAN;
            bVar2.j(new O.g("Preferred", "true", type2));
            bVar2.j(new O.g("IsForLLMClient", String.valueOf(zD), type2));
            bVar2.j(new O.g("isUVDisabled", String.valueOf(zE), type2));
        }
        bVar2.j(new O.g("Locale", bVar.k(), Type.STRING));
        return bVar2;
    }

    public final O.b w(p017r.a aVar) {
        if (aVar == null) {
            return null;
        }
        O.b bVar = new O.b("FirstStageModel");
        String strE = aVar.e();
        Type type = Type.STRING;
        bVar.j(new O.g("SoundModelFileName", strE, type));
        bVar.j(new O.g("Path", aVar.j(), type));
        bVar.j(new O.g("Locale", aVar.h(), type));
        return bVar;
    }

    public final O.b x(p016q.a aVar) {
        O.b bVar = new O.b("ModelGroup");
        String strJ = aVar.j();
        Type type = Type.STRING;
        bVar.j(new O.g("ModelGroupKwHash", strJ, type));
        bVar.j(new O.g("Locale", aVar.k(), type));
        return bVar;
    }

    public final O.b y(p017r.b bVar) {
        if (bVar == null) {
            return null;
        }
        O.b bVar2 = new O.b("MiddleStageModel");
        String strG = bVar.g();
        Type type = Type.STRING;
        bVar2.j(new O.g("SoundModelFileName", strG, type));
        bVar2.j(new O.g("Keyword", bVar.i(), type));
        bVar2.j(new O.g("Path", bVar.k(), type));
        bVar2.j(new O.g("Locale", bVar.j(), type));
        return bVar2;
    }

    public final O.b z(p017r.h hVar) {
        O.b bVar = new O.b("SecondStageModel");
        bVar.j(new O.g("SoundModelFileName", hVar.c(), Type.STRING));
        return bVar;
    }
}
