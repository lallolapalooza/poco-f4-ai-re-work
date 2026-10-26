package com.miui.antispam.policy;

import android.content.Context;
import j2.c;
import j2.e;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    protected Context mContext;
    protected j2.b mJudge;
    protected b mPc;
    protected e mPolicyDesc;

    /* JADX INFO: renamed from: com.miui.antispam.policy.a$a, reason: collision with other inner class name */
    public class C0115a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7160a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f7161b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f7162c;

        public C0115a(a aVar, boolean z10, int i10) {
            this(z10, i10, "");
        }

        public C0115a(boolean z10, int i10, String str) {
            this.f7160a = z10;
            this.f7161b = i10;
            this.f7162c = str;
        }
    }

    public interface b {
        a a(e eVar);
    }

    public a(Context context, b bVar, j2.b bVar2, e eVar) {
        this.mContext = context;
        this.mPc = bVar;
        this.mJudge = bVar2;
        this.mPolicyDesc = eVar;
    }

    public abstract C0115a dbQuery(c cVar);

    public abstract String getName();

    public abstract int getType();

    public abstract C0115a handleData(c cVar);
}
