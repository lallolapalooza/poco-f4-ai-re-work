package org.tensorflow.lite;

import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: compiled from: Interpreter.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final NativeInterpreterWrapperExperimental f473b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String[] f474c;

    public e(ByteBuffer byteBuffer) {
        this(byteBuffer, null);
    }

    @Override // org.tensorflow.lite.g
    public /* bridge */ /* synthetic */ h.g c(int i2) {
        return super.c(i2);
    }

    @Override // org.tensorflow.lite.g, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // org.tensorflow.lite.g
    public /* bridge */ /* synthetic */ h.g d(int i2) {
        return super.d(i2);
    }

    @Override // org.tensorflow.lite.g
    public /* bridge */ /* synthetic */ void e(Object obj, Object obj2) {
        super.e(obj, obj2);
    }

    @Override // org.tensorflow.lite.g
    public /* bridge */ /* synthetic */ void f(Object[] objArr, Map map) {
        super.f(objArr, map);
    }

    public String[] g() {
        b();
        return this.f478a.g();
    }

    public e(ByteBuffer byteBuffer, d dVar) {
        this(new NativeInterpreterWrapperExperimental(byteBuffer, dVar));
    }

    private e(NativeInterpreterWrapperExperimental nativeInterpreterWrapperExperimental) {
        super(nativeInterpreterWrapperExperimental);
        this.f473b = nativeInterpreterWrapperExperimental;
        this.f474c = g();
    }
}
