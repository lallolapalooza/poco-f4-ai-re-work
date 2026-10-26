package com.miui.extraphoto.refocus.model;

import android.graphics.Bitmap;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class NativeImage {
    public final int height;
    public final long pointer;
    private AtomicBoolean released = new AtomicBoolean(false);
    public final int width;

    public NativeImage(Bitmap bitmap) {
        int width = bitmap.getWidth();
        this.width = width;
        int height = bitmap.getHeight();
        this.height = height;
        this.pointer = DualPhotoJni.generateNativeImage(bitmap, width, height);
    }

    public NativeImage(NativeImage nativeImage) {
        this.width = nativeImage.width;
        this.height = nativeImage.height;
        this.pointer = DualPhotoJni.allocNativeImage(nativeImage.pointer);
    }

    public void release() {
        if (this.released.getAndSet(true)) {
            return;
        }
        DualPhotoJni.releaseNativeImage(this.pointer);
    }
}
