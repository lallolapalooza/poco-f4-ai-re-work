package org.tensorflow.lite;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: InterpreterImpl.java */
/* JADX INFO: loaded from: classes.dex */
class g implements h.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    NativeInterpreterWrapper f478a;

    g(NativeInterpreterWrapper nativeInterpreterWrapper) {
        this.f478a = nativeInterpreterWrapper;
    }

    void b() {
        if (this.f478a == null) {
            throw new IllegalStateException("Internal error: The Interpreter has already been closed.");
        }
    }

    public h.g c(int i2) {
        b();
        return this.f478a.e(i2);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        NativeInterpreterWrapper nativeInterpreterWrapper = this.f478a;
        if (nativeInterpreterWrapper != null) {
            nativeInterpreterWrapper.close();
            this.f478a = null;
        }
    }

    public h.g d(int i2) {
        b();
        return this.f478a.f(i2);
    }

    public void e(Object obj, Object obj2) {
        HashMap map = new HashMap();
        map.put(0, obj2);
        f(new Object[]{obj}, map);
    }

    public void f(Object[] objArr, Map map) {
        b();
        this.f478a.m(objArr, map);
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
