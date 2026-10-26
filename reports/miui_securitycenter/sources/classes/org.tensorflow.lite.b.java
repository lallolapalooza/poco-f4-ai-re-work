package org.tensorflow.lite;

import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    NativeInterpreterWrapperExperimental f52415d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String[] f52416e;

    public static class a extends c.a {
    }

    public b(File file) {
        this(file, null);
    }

    public b(File file, a aVar) {
        this(new NativeInterpreterWrapperExperimental(file.getAbsolutePath(), aVar));
    }

    private b(NativeInterpreterWrapperExperimental nativeInterpreterWrapperExperimental) {
        super(nativeInterpreterWrapperExperimental);
        this.f52415d = nativeInterpreterWrapperExperimental;
        this.f52416e = k();
    }

    @Override // org.tensorflow.lite.c
    public /* bridge */ /* synthetic */ void b(Object obj, Object obj2) {
        super.b(obj, obj2);
    }

    @Override // org.tensorflow.lite.c, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // org.tensorflow.lite.c
    public /* bridge */ /* synthetic */ void d(Object[] objArr, Map map) {
        super.d(objArr, map);
    }

    public String[] k() {
        a();
        return this.f52417c.n();
    }
}
