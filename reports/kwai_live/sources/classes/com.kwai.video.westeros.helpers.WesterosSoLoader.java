package com.kwai.video.westeros.helpers;

import com.kwai.camerasdk.utils.a;

/* JADX INFO: loaded from: classes13.dex */
public class WesterosSoLoader {
    private static volatile boolean sloadNativeSuccessed = false;

    public static void loadLibrary(String str) {
        a.a(str);
    }

    public static void loadNative() {
        if (sloadNativeSuccessed) {
            return;
        }
        a.b();
        a.a("c++_shared");
        jx.a.a("6214227cd0a1f50c2d7cde0837359bf496afaf3a", new jx.a.b() { // from class: com.kwai.video.westeros.helpers.WesterosSoLoader.1
            @Override // jx.a.b
            public void loadLibrary(String str) {
                a.a(str);
            }
        });
        oq.a.a("v4.8.0.6", new oq.a.b() { // from class: com.kwai.video.westeros.helpers.WesterosSoLoader.2
            @Override // oq.a.b
            public void loadLibrary(String str) {
                a.a(str);
            }
        });
        a.a("westeros");
        sloadNativeSuccessed = true;
    }
}
