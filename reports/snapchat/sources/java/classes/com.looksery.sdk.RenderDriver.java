package com.looksery.sdk;

/* JADX INFO: loaded from: classes2.dex */
public interface RenderDriver {
    double getCurrentFps();

    long getNativeRenderDriver();

    void release();

    void start();

    void stop();
}
