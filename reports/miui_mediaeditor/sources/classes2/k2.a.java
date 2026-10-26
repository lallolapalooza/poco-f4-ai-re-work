package k2;

import android.graphics.Bitmap;
import androidx.view.m;
import ch.qos.logback.core.CoreConstants;
import com.faceunity.core.enumeration.FUAITypeEnum;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.e;
import kotlin.text.i;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final C0199a d = new C0199a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile a f11917e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<Integer, Integer> f11918a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f11919b = 5;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f11920c = 10;

    /* JADX INFO: renamed from: k2.a$a, reason: collision with other inner class name */
    public static final class C0199a {
        public final a a() {
            if (a.f11917e == null) {
                synchronized (this) {
                    if (a.f11917e == null) {
                        a.f11917e = new a();
                    }
                    d dVar = d.f18754a;
                }
            }
            a aVar = a.f11917e;
            e.c(aVar);
            return aVar;
        }
    }

    public static void a(int i10) {
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuSetMaxFaces(i10);
        FULogger.a("KIT_SDKController", "fuSetMaxFaces  maxFaces:" + i10);
    }

    public static float[] b(Bitmap bitmap, int i10, int i11) {
        e.f(bitmap, "bitmap");
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        float[] array = Faceunity.fuImageGetImageInfo(bitmap, i10, i11);
        FULogger.a("KIT_SDKController", "fuImageGetLandmarksRect bitmap:" + bitmap + "  maxNum:" + i11 + " array：" + array + CoreConstants.CURLY_RIGHT);
        e.e(array, "array");
        return array;
    }

    public static void c(int i10) {
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        Faceunity.fuHumanProcessorSetMaxHumans(i10);
        FULogger.a("KIT_SDKController", "fuHumanProcessorSetMaxHumans  maxHumans:" + i10);
    }

    public static int d() {
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        int iFuIsTracking = Faceunity.fuIsTracking();
        FULogger.a("KIT_SDKController", "fuImageBeautyGetFaceNum  res:" + iFuIsTracking);
        return iFuIsTracking;
    }

    public final void e(String path, FUAITypeEnum fUAITypeEnum, g2.a aVar) throws IOException {
        e.f(path, "path");
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        int type = fUAITypeEnum.getType();
        int iFuIsAIModelLoaded = Faceunity.fuIsAIModelLoaded(type);
        StringBuilder sbI = androidx.appcompat.widget.a.i("fuIsAIModelLoaded  type:", type, "   isLoaded:");
        sbI.append(iFuIsAIModelLoaded == 1);
        FULogger.a("KIT_SDKController", sbI.toString());
        boolean z = iFuIsAIModelLoaded == 1;
        int i10 = this.f11920c;
        int i11 = this.f11919b;
        if (z) {
            int iOrdinal = fUAITypeEnum.ordinal();
            if (iOrdinal == 1) {
                a(i11);
            } else if (iOrdinal == 2) {
                c(i10);
            } else if (iOrdinal == 15) {
                a(i11);
            } else if (iOrdinal == 16) {
                c(i10);
            }
            if (aVar != null) {
                aVar.a(201, "hasLoaded type=" + fUAITypeEnum.getType() + ' ');
                return;
            }
            return;
        }
        if (i.l0(path)) {
            FULogger.b("KIT_FUAIController", "loadAIProcessor failed   type=" + fUAITypeEnum.getType() + "  bundle path isBlank");
            if (aVar != null) {
                aVar.b(10001, "file not found: ".concat(path));
                return;
            }
            return;
        }
        byte[] bArrW = miuix.appcompat.app.floatingactivity.i.w(b.f11921a.a(), path);
        if (bArrW == null) {
            FULogger.b("KIT_FUAIController", "loadAIProcessor failed  file not found: ".concat(path));
            g2.a aVar2 = b.d;
            if (aVar2 != null) {
                aVar2.b(10001, "file not found: ".concat(path));
            }
            if (aVar != null) {
                aVar.b(10001, "file not found: ".concat(path));
                return;
            }
            return;
        }
        FUAITypeEnum fUAITypeEnum2 = FUAITypeEnum.FUAITYPE_TONGUETRACKING;
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = this.f11918a;
        if (fUAITypeEnum == fUAITypeEnum2) {
            int iFuLoadTongueModel = Faceunity.fuLoadTongueModel(bArrW);
            StringBuilder sb2 = new StringBuilder("fuLoadTongueModel isLoaded:");
            sb2.append(iFuLoadTongueModel == 1);
            sb2.append("  path:");
            sb2.append(path);
            FULogger.a("KIT_SDKController", sb2.toString());
            if (iFuLoadTongueModel == 1) {
                g2.a aVar3 = b.d;
                if (aVar3 != null) {
                    aVar3.a(201, "loadTongueModel  ");
                }
                if (aVar != null) {
                    aVar.a(201, "loadTongueModel  ");
                }
            } else {
                g2.a aVar4 = b.d;
                if (aVar4 != null) {
                    aVar4.b(10002, "LoadTongueModel failed isLoaded=" + iFuLoadTongueModel + "  ");
                }
                if (aVar != null) {
                    aVar.b(10002, "LoadTongueModel failed isLoaded=" + iFuLoadTongueModel + "  ");
                }
            }
            if (iFuLoadTongueModel == 1) {
                concurrentHashMap.put(Integer.valueOf(fUAITypeEnum.getType()), Integer.valueOf(fUAITypeEnum.getType()));
                return;
            }
            return;
        }
        int type2 = fUAITypeEnum.getType();
        int iFuLoadAIModelFromPackage = Faceunity.fuLoadAIModelFromPackage(bArrW, type2);
        StringBuilder sbI2 = androidx.appcompat.widget.a.i("fuLoadAIModelFromPackage  type:", type2, "  isLoaded:");
        sbI2.append(iFuLoadAIModelFromPackage == 1);
        sbI2.append("  path:");
        sbI2.append(path);
        FULogger.a("KIT_SDKController", sbI2.toString());
        if (iFuLoadAIModelFromPackage == 1) {
            g2.a aVar5 = b.d;
            if (aVar5 != null) {
                aVar5.a(201, "loadAIModelFromPackage type=" + type2 + ' ');
            }
            if (aVar != null) {
                aVar.a(201, "loadAIModelFromPackage type=" + type2 + ' ');
            }
        } else {
            g2.a aVar6 = b.d;
            if (aVar6 != null) {
                aVar6.b(10002, m.d("loadAIModelFromPackage failed  type=", type2, " isLoaded=", iFuLoadAIModelFromPackage));
            }
            if (aVar != null) {
                aVar.b(10002, m.d("loadAIModelFromPackage failed  type=", type2, " isLoaded=", iFuLoadAIModelFromPackage));
            }
        }
        if (iFuLoadAIModelFromPackage == 1) {
            int iOrdinal2 = fUAITypeEnum.ordinal();
            if (iOrdinal2 == 1) {
                a(i11);
            } else if (iOrdinal2 == 2) {
                c(i10);
            } else if (iOrdinal2 == 15) {
                a(i11);
            } else if (iOrdinal2 == 16) {
                c(i10);
            }
            concurrentHashMap.put(Integer.valueOf(fUAITypeEnum.getType()), Integer.valueOf(fUAITypeEnum.getType()));
        }
    }

    public final void f(FUAITypeEnum fUAITypeEnum) {
        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
        SDKController.i(fUAITypeEnum.getType());
        this.f11918a.remove(Integer.valueOf(fUAITypeEnum.getType()));
    }

    public final void g() {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = this.f11918a;
        for (Map.Entry<Integer, Integer> entry : concurrentHashMap.entrySet()) {
            e.e(entry, "entries.next()");
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            Integer key = entry.getKey();
            e.e(key, "entry.key");
            SDKController.i(key.intValue());
        }
        concurrentHashMap.clear();
    }
}
