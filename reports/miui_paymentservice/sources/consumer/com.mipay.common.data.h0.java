package com.mipay.common.data;

import miuipub.os.Build;

/* JADX INFO: compiled from: MiuiBuild.java */
/* JADX INFO: loaded from: classes.dex */
public class h0 {
    private h0() {
    }

    public static boolean a() {
        return Build.IS_ALPHA_BUILD;
    }

    public static boolean b() {
        return Build.IS_DEVELOPMENT_VERSION;
    }

    public static boolean c() {
        return Build.IS_INTERNATIONAL_BUILD;
    }

    public static boolean d() {
        return Build.getRegion().equals("KR");
    }

    public static boolean e() {
        return Build.IS_MIFIVE;
    }

    public static boolean f() {
        return Build.IS_MIFOUR;
    }

    public static boolean g() {
        return Build.IS_STABLE_VERSION;
    }

    public static boolean h() {
        return Build.IS_TABLET;
    }
}
