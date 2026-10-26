package com.miui.aiautobrt.database;

import android.content.Context;
import gb.c;
import h3.b;
import y0.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class BrightnessModelDatabase extends n {
    public static volatile BrightnessModelDatabase l;

    public static BrightnessModelDatabase p(Context context) {
        if (l == null) {
            synchronized (BrightnessModelDatabase.class) {
                if (l == null) {
                    n.a aVarG = c.g(context);
                    aVarG.f8562j = false;
                    aVarG.f8563k = true;
                    l = (BrightnessModelDatabase) aVarG.a();
                }
            }
        }
        return l;
    }

    public abstract b q();
}
