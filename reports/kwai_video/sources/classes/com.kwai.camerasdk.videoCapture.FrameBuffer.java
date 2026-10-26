package com.kwai.camerasdk.videoCapture;

import androidx.annotation.Keep;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public class FrameBuffer extends com.kwai.camerasdk.utils.d.a {
    public ByteBuffer byteBuffer;
    private boolean isNativeBuffer;

    public FrameBuffer(int i12) {
        this.isNativeBuffer = false;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i12);
        this.byteBuffer = byteBufferAllocateDirect;
        if (byteBufferAllocateDirect == null) {
            this.byteBuffer = ByteBuffer.allocate(i12);
        }
    }

    private native ByteBuffer nativeCreateByteBuffer(int i12);

    private native void nativeFreeByteBuffer(ByteBuffer byteBuffer);

    @Override // com.kwai.camerasdk.utils.d.a
    protected void free() {
        if (this.isNativeBuffer) {
            nativeFreeByteBuffer(this.byteBuffer);
        }
    }

    public byte[] getByteArray() {
        if (!this.byteBuffer.isDirect()) {
            return this.byteBuffer.array();
        }
        this.byteBuffer.position(0);
        byte[] bArr = new byte[this.byteBuffer.remaining()];
        this.byteBuffer.get(bArr);
        this.byteBuffer.position(0);
        return bArr;
    }

    public boolean isDirect() {
        return this.byteBuffer.isDirect();
    }

    public FrameBuffer put(byte[] bArr) {
        this.byteBuffer.put(bArr);
        return this;
    }

    @Override // com.kwai.camerasdk.utils.d.a
    protected void reset() {
        this.byteBuffer.position(0);
    }

    public FrameBuffer(int i12, boolean z12) {
        this.isNativeBuffer = false;
        this.isNativeBuffer = z12;
        if (z12) {
            this.byteBuffer = nativeCreateByteBuffer(i12);
            return;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i12);
        this.byteBuffer = byteBufferAllocateDirect;
        if (byteBufferAllocateDirect == null) {
            this.byteBuffer = ByteBuffer.allocate(i12);
        }
    }

    public FrameBuffer(ByteBuffer byteBuffer) {
        this.isNativeBuffer = false;
        this.byteBuffer = byteBuffer;
    }
}
