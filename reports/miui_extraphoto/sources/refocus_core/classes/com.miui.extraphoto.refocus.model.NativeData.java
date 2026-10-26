package com.miui.extraphoto.refocus.model;

import com.miui.extraphoto.refocus.manager.DualPhotoJni;

/* JADX INFO: loaded from: classes.dex */
public class NativeData {
    public final int length;
    public final long pointer;

    public NativeData(byte[] bArr) {
        int length = bArr.length;
        this.length = length;
        this.pointer = DualPhotoJni.generateNativeData(bArr, length);
    }

    public NativeData(NativeData nativeData) {
        int i = nativeData.length;
        this.length = i;
        this.pointer = DualPhotoJni.copyNativeData(nativeData.pointer, i);
    }

    public void release() {
        DualPhotoJni.releaseNativeData(this.pointer);
    }
}
