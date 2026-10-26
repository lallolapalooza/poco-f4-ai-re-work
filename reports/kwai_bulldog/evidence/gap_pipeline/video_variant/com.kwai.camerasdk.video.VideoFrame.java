package com.kwai.camerasdk.video;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import co.a;
import com.google.protobuf.InvalidProtocolBufferException;
import com.kwai.camerasdk.videoCapture.FrameBuffer;
import com.yxcorp.bugly.CrashReporter;
import java.nio.ByteBuffer;
import p000do.g0;
import p000do.o1;
import p000do.p1;

/* JADX INFO: loaded from: classes15.dex */
@Keep
public class VideoFrame extends a {
    public static final int BITMAP = 4;
    public static final int None = 0;
    public static final int RGBA = 3;
    public static final int TEXTURE = 2;
    public static final int YUV = 1;
    public Bitmap bitmap;
    public FrameBuffer data;
    public VideoFrame originalFrame;
    public Runnable textureNotUsedRunnable;
    public int type = 0;
    public long nativeContext = 0;
    public int yuv_format = 0;
    public int textureId = 0;
    public boolean isOesTexture = false;
    public int width = 0;
    public int height = 0;
    public long timestamp = 0;
    public p1.b attributes = p1.D();

    private VideoFrame() {
    }

    public static VideoFrame fromBitmap(Bitmap bitmap, int i12, int i13, long j12) {
        VideoFrame videoFrame = new VideoFrame();
        videoFrame.type = 4;
        videoFrame.bitmap = bitmap;
        videoFrame.width = i12;
        videoFrame.height = i13;
        videoFrame.timestamp = j12;
        return videoFrame;
    }

    public static VideoFrame fromCpuFrame(FrameBuffer frameBuffer, int i12, int i13, int i14, long j12) {
        return fromCpuFrame(frameBuffer, i12, i13, i14, j12, false);
    }

    public static VideoFrame fromTexture(int i12, boolean z12, int i13, int i14, long j12) {
        VideoFrame videoFrame = new VideoFrame();
        videoFrame.type = 2;
        videoFrame.textureId = i12;
        videoFrame.isOesTexture = z12;
        videoFrame.width = i13;
        videoFrame.height = i14;
        videoFrame.timestamp = j12;
        return videoFrame;
    }

    private byte[] getSerializedAttributes() {
        return this.attributes.build().toByteArray();
    }

    private boolean widthHeightReversed() {
        return this.attributes.f().m() % 180 == 90;
    }

    private VideoFrame withSerializedAttributes(byte[] bArr) {
        try {
            this.attributes = p1.E(bArr).toBuilder();
        } catch (InvalidProtocolBufferException e12) {
            CrashReporter.logCheckedException(e12, "VideoFrame.class", "withSerializedAttributes", -83);
            e12.printStackTrace();
        }
        return this;
    }

    public VideoFrame addFaces(Iterable<g0> iterable) {
        this.attributes.a(iterable);
        return this;
    }

    public boolean canDrop() {
        return !this.attributes.e();
    }

    public int croppedHeight() {
        int iN = this.attributes.f().n() != 0 ? this.attributes.f().n() : this.height;
        if (this.attributes.f().j() > 0.0f) {
            iN = (int) (this.attributes.f().j() * iN);
        }
        return (iN + 3) & (-4);
    }

    public int croppedWidth() {
        int iO = this.attributes.f().o() != 0 ? this.attributes.f().o() : this.width;
        if (this.attributes.f().k() > 0.0f) {
            iO = (int) (this.attributes.f().k() * iO);
        }
        return (iO + 3) & (-4);
    }

    public boolean isTexture() {
        return this.type == 2;
    }

    public boolean isYuv() {
        return this.type == 1;
    }

    @Override // co.a
    public int mediaType() {
        return 0;
    }

    public int transformedHeight() {
        return widthHeightReversed() ? croppedWidth() : croppedHeight();
    }

    public int transformedWidth() {
        return widthHeightReversed() ? croppedHeight() : croppedWidth();
    }

    public VideoFrame withAttributes(p1.b bVar) {
        this.attributes = bVar;
        return this;
    }

    public VideoFrame withTransform(o1 o1Var) {
        this.attributes.u(o1Var);
        return this;
    }

    private static VideoFrame fromCpuFrame(FrameBuffer frameBuffer, int i12, int i13, int i14, long j12, boolean z12) {
        VideoFrame videoFrame = new VideoFrame();
        if (i14 == 3) {
            videoFrame.type = 3;
        } else {
            videoFrame.type = 1;
        }
        if (z12) {
            frameBuffer.byteBuffer.position(0);
            ByteBuffer byteBufferPut = ByteBuffer.allocate(frameBuffer.byteBuffer.capacity()).put(frameBuffer.byteBuffer);
            byteBufferPut.position(0);
            videoFrame.data = new FrameBuffer(byteBufferPut);
            frameBuffer.release();
        } else {
            videoFrame.data = frameBuffer;
        }
        videoFrame.width = i12;
        videoFrame.height = i13;
        videoFrame.yuv_format = i14;
        videoFrame.timestamp = j12;
        return videoFrame;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public VideoFrame m10clone() {
        int i12 = this.type;
        if (i12 == 1) {
            return fromCpuFrame(this.data, this.width, this.height, this.yuv_format, this.timestamp, true).withAttributes(this.attributes);
        }
        if (i12 == 2) {
            return fromTexture(this.textureId, this.isOesTexture, this.width, this.height, this.timestamp).withAttributes(this.attributes);
        }
        if (i12 == 3) {
            return fromCpuFrame(this.data, this.width, this.height, i12, this.timestamp, true).withAttributes(this.attributes);
        }
        if (i12 == 4) {
            return fromBitmap(this.bitmap, this.width, this.height, this.timestamp).withAttributes(this.attributes);
        }
        return null;
    }
}
