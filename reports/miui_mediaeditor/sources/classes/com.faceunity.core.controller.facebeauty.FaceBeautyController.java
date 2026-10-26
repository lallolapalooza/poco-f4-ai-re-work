package com.faceunity.core.controller.facebeauty;

import android.util.Log;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import fr.a;
import j2.b;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.e;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public final class FaceBeautyController extends BaseSingleController {
    public final void g(final b bVar) {
        a(bVar.f11710a, bVar.f11712c, new a<d>() { // from class: com.faceunity.core.controller.facebeauty.FaceBeautyController$applyControllerBundle$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // fr.a
            public final d invoke() {
                this.this$0.c(bVar.f11711b);
                return d.f18754a;
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00d2  */
    public final void h(final b bVar) {
        int iA;
        a<d> aVar = new a<d>() { // from class: com.faceunity.core.controller.facebeauty.FaceBeautyController$applyControllerSubBundleAction$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // fr.a
            public final d invoke() {
                this.this$0.c(bVar.f11711b);
                return d.f18754a;
            }
        };
        j2.a aVar2 = bVar.f11710a;
        String str = this.f1273a;
        if (aVar2 != null) {
            StringBuilder sb2 = new StringBuilder("applyControllerSubBundleAction path: ");
            String path = aVar2.f11707a;
            sb2.append(path);
            Log.i(str, sb2.toString());
            ArrayList arrayList = this.g;
            if (arrayList.contains(path)) {
                iA = 0;
            } else {
                Log.i(str, "loadBundleFile path: " + path);
                b().getClass();
                e.f(path, "path");
                FULogger.a("KIT_BundleManager", "createItemFromPackage path:".concat(path));
                iA = f2.b.a(path);
                if (iA < 0) {
                    FULogger.b("KIT_BundleManager", "createItemFromPackage failed path:".concat(path));
                }
                arrayList.add(path);
                Log.i(str, "updateControllerSubBundle handle: " + iA);
                f2.b bVarB = b();
                LinkedHashMap linkedHashMap = this.f1277h;
                Integer num = (Integer) linkedHashMap.get(path);
                int iIntValue = num != null ? num.intValue() : -1;
                bVarB.getClass();
                FULogger.a("KIT_BundleManager", "bindControllerSubBundle  oldHandle:" + iIntValue + "  newHandle:" + iA + " isFaceBeauty:true");
                if (iIntValue != iA) {
                    if (iIntValue > 0) {
                        SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                        Faceunity.fuDestroyItem(iIntValue);
                        FULogger.a("KIT_SDKController", "fuDestroyItem   handle:" + iIntValue);
                        bVarB.h(iIntValue);
                    }
                    if (iA > 0) {
                        bVarB.g(iA);
                    }
                }
                linkedHashMap.put(path, Integer.valueOf(iA));
            }
        } else {
            iA = 0;
        }
        if (iA > 0) {
            this.f.add(Integer.valueOf(iA));
            aVar.invoke();
        } else {
            StringBuilder sbI = androidx.appcompat.widget.a.i("loadControllerBundle failed handle:", iA, "  path:");
            sbI.append(aVar2 != null ? aVar2.f11707a : null);
            FULogger.b(str, sbI.toString());
        }
    }
}
