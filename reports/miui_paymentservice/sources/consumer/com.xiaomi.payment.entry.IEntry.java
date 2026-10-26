package com.xiaomi.payment.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mipay.core.runtime.e;

/* JADX INFO: loaded from: classes2.dex */
public interface IEntry extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3558a = "result";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3559b = "arguments";

    public interface a {
        void a(Intent intent, int i);

        Context getContext();
    }

    void a(a aVar, Bundle bundle, int i);

    boolean a(Context context);

    String getId();
}
