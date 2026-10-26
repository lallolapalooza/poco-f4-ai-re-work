package com.looksery.sdk.media;

/* JADX INFO: loaded from: classes2.dex */
public interface ExternalTextureStream {
    int getHeight();

    int getWidth();

    int nextExternalTextureFrame(float[] fArr);

    void release();

    boolean usesOESExternalTexture();
}
