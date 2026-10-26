package f2;

import android.util.Log;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import com.xiaomi.milab.videosdk.message.MsgType;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import kotlin.collections.r;
import kotlin.jvm.internal.e;
import miuix.appcompat.app.floatingactivity.i;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final a g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static volatile b f10634h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f10635a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f10636b = new int[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedList<Integer> f10637c = new LinkedList<>();
    public final Object d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, f2.a> f10638e = new HashMap<>();
    public final HashMap<Integer, String> f = new HashMap<>();

    public static final class a {
        public final b a() {
            if (b.f10634h == null) {
                synchronized (this) {
                    if (b.f10634h == null) {
                        b.f10634h = new b();
                    }
                    d dVar = d.f18754a;
                }
            }
            b bVar = b.f10634h;
            e.c(bVar);
            return bVar;
        }
    }

    public static int a(String str) throws IOException {
        String msg = "createItemFromPackage   path=" + str;
        e.f(msg, "msg");
        if (FULogger.f1393a.ordinal() <= 2) {
            Log.i("KIT_BundleManager", msg);
        }
        if (str.length() == 0) {
            return 0;
        }
        byte[] bArrW = i.w(k2.b.f11921a.a(), str);
        if (bArrW == null) {
            g2.a aVar = k2.b.d;
            if (aVar != null) {
                aVar.b(10001, "file not found: ".concat(str));
            }
            FULogger.a("KIT_BundleManager", "createItemFromPackage failed   file not found: ".concat(str));
            return 0;
        }
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuSetInputCameraBufferMatrixState(1);
        FULogger.a("KIT_SDKController", "setInputCameraBufferMatrixState    enable:true");
        int iFuCreateItemFromPackage = Faceunity.fuCreateItemFromPackage(bArrW);
        FULogger.a("KIT_SDKController", "fuCreateItemFromPackage   handle:" + iFuCreateItemFromPackage + "   path:" + str);
        if (iFuCreateItemFromPackage > 0) {
            g2.a aVar2 = k2.b.d;
            if (aVar2 != null) {
                aVar2.a(202, "createItemFromPackage");
            }
        } else {
            g2.a aVar3 = k2.b.d;
            if (aVar3 != null) {
                aVar3.b(MsgType.XMSTRANSCODE, "createItemFromPackage  failed");
            }
        }
        return iFuCreateItemFromPackage;
    }

    public final void b(int i10) {
        HashMap<Integer, String> map = this.f;
        String str = map.get(Integer.valueOf(i10));
        FULogger.a("KIT_BundleManager", "destroyBundle  path:" + str + "    handle:" + i10);
        if (str != null) {
            this.f10638e.remove(str);
            map.remove(Integer.valueOf(i10));
        }
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuDestroyItem(i10);
        FULogger.a("KIT_SDKController", "fuDestroyItem   handle:" + i10);
    }

    public final void c(int i10) {
        FULogger.a("KIT_BundleManager", "destroyControllerBundle  handle:" + i10 + "  ");
        if (i10 > 0) {
            b(i10);
            h(i10);
        }
    }

    public final int[] d() {
        int[] iArrW0;
        if (!this.f10635a) {
            return this.f10636b;
        }
        synchronized (this.d) {
            this.f10635a = false;
            iArrW0 = r.W0(this.f10637c);
            this.f10636b = iArrW0;
            d dVar = d.f18754a;
        }
        return iArrW0;
    }

    public final int e(String name, String path) throws IOException {
        e.f(name, "name");
        e.f(path, "path");
        FULogger.a("KIT_BundleManager", "createItemFromPackage  name:" + name + "  path:" + path);
        HashMap<String, f2.a> map = this.f10638e;
        f2.a aVar = map.get(path);
        int iA = aVar != null ? aVar.f10632c : 0;
        if (iA <= 0) {
            iA = a(path);
            if (iA > 0) {
                map.put(path, new f2.a(name, path, iA));
                this.f.put(Integer.valueOf(iA), path);
            } else {
                FULogger.b("KIT_BundleManager", "createItemFromPackage failed  name:" + name + "  path:" + path);
            }
        }
        return iA;
    }

    public final void f() {
        FULogger.a("KIT_BundleManager", "release");
        synchronized (this.d) {
            this.f10637c.clear();
            this.f10635a = true;
            d dVar = d.f18754a;
        }
        this.f10638e.clear();
        this.f.clear();
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuDestroyAllItems();
        FULogger.a("KIT_SDKController", "fuDestroyAllItems");
    }

    public final void g(int i10) {
        synchronized (this.d) {
            Log.d("KIT_BundleManager", "renderBundlesAdd: index: 0 handle: " + i10 + ' ' + r.G0(this.f10637c, null, null, null, null, 63));
            if (!this.f10637c.contains(Integer.valueOf(i10))) {
                this.f10637c.add(0, Integer.valueOf(i10));
                this.f10635a = true;
            }
            Log.d("KIT_BundleManager", "renderBundlesAdd: ".concat(r.G0(this.f10637c, null, null, null, null, 63)));
        }
    }

    public final void h(int i10) {
        synchronized (this.d) {
            Log.d("KIT_BundleManager", "renderBundlesRemove: handle: " + i10 + ' ' + r.G0(this.f10637c, null, null, null, null, 63));
            if (this.f10637c.contains(Integer.valueOf(i10))) {
                this.f10637c.remove(Integer.valueOf(i10));
                this.f10635a = true;
            }
            Log.d("KIT_BundleManager", "renderBundlesRemove: ".concat(r.G0(this.f10637c, null, null, null, null, 63)));
        }
    }
}
