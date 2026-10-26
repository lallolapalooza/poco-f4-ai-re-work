package org.tensorflow.lite;

import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b {
    public String[] c;

    public a(ByteBuffer byteBuffer) {
        super(new NativeInterpreterWrapperExperimental(byteBuffer));
        NativeInterpreterWrapper nativeInterpreterWrapper = this.f6655b;
        if (nativeInterpreterWrapper == null) {
            throw new IllegalStateException("Internal error: The Interpreter has already been closed.");
        }
        this.c = nativeInterpreterWrapper.e();
    }

    public final void a(Map<String, Object> map, Map<String, Object> map2, String str) {
        NativeInterpreterWrapper nativeInterpreterWrapper = this.f6655b;
        if (nativeInterpreterWrapper == null) {
            throw new IllegalStateException("Internal error: The Interpreter has already been closed.");
        }
        nativeInterpreterWrapper.g(map, map2, str);
    }
}
