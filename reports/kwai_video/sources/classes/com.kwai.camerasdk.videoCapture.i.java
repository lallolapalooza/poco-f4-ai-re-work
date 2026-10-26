package com.kwai.camerasdk.videoCapture;

import android.annotation.TargetApi;
import android.graphics.ImageFormat;
import android.media.Image;
import com.kwai.camerasdk.log.Log;
import com.kwai.camerasdk.utils.ByteBufferUtils;

/* JADX INFO: compiled from: YUVFrameBufferReader.java */
/* JADX INFO: loaded from: classes15.dex */
@TargetApi(21)
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.kwai.camerasdk.utils.d<FrameBuffer> f23831a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f23832b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f23833c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f23834d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f23835e = false;

    private void a(int i12, int i13) {
        this.f23831a = new com.kwai.camerasdk.utils.d<>(new h(((ImageFormat.getBitsPerPixel(35) * i12) * i13) / 8), this.f23834d, this.f23835e);
    }

    public void b() {
        com.kwai.camerasdk.utils.d<FrameBuffer> dVar = this.f23831a;
        if (dVar != null) {
            dVar.a();
        }
    }

    public int c() {
        return this.f23833c;
    }

    public int d() {
        return this.f23832b;
    }

    public FrameBuffer e(Image image, com.kwai.camerasdk.utils.f fVar) {
        if (this.f23831a == null) {
            a(fVar.d(), fVar.c());
        }
        FrameBuffer frameBuffer = (FrameBuffer) this.f23831a.c();
        if (frameBuffer.byteBuffer.remaining() != ((fVar.d() * fVar.c()) * ImageFormat.getBitsPerPixel(35)) / 8) {
            Log.w("YUVFrameBufferReader", "frame buffer size is not equal to rowStride * height!!! Try to recreate frameBufferPool");
            a(fVar.d(), fVar.c());
            frameBuffer = (FrameBuffer) this.f23831a.c();
        }
        f(image, fVar, frameBuffer);
        return frameBuffer;
    }

    public void f(Image image, com.kwai.camerasdk.utils.f fVar, FrameBuffer frameBuffer) {
        Image.Plane plane = image.getPlanes()[0];
        Image.Plane plane2 = image.getPlanes()[1];
        Image.Plane plane3 = image.getPlanes()[2];
        int rowStride = plane.getRowStride();
        if (plane3.getPixelStride() == 2) {
            this.f23833c = 2;
            if (fVar.d() == rowStride) {
                ByteBufferUtils.b(frameBuffer.byteBuffer, plane.getBuffer());
                ByteBufferUtils.b(frameBuffer.byteBuffer, plane3.getBuffer());
            } else {
                ByteBufferUtils.a(frameBuffer.byteBuffer, fVar.d(), plane.getBuffer(), rowStride, fVar.c());
                ByteBufferUtils.a(frameBuffer.byteBuffer, fVar.d(), plane3.getBuffer(), rowStride, fVar.c() / 2);
            }
        } else if (plane2.getPixelStride() == 2) {
            this.f23833c = 1;
            if (fVar.d() == rowStride) {
                ByteBufferUtils.b(frameBuffer.byteBuffer, plane.getBuffer());
                ByteBufferUtils.b(frameBuffer.byteBuffer, plane2.getBuffer());
            } else {
                ByteBufferUtils.a(frameBuffer.byteBuffer, fVar.d(), plane.getBuffer(), rowStride, fVar.c());
                ByteBufferUtils.a(frameBuffer.byteBuffer, fVar.d(), plane2.getBuffer(), rowStride, fVar.c() / 2);
            }
        } else {
            this.f23833c = 0;
            if (fVar.d() == rowStride) {
                ByteBufferUtils.b(frameBuffer.byteBuffer, plane.getBuffer());
                ByteBufferUtils.b(frameBuffer.byteBuffer, plane2.getBuffer());
                ByteBufferUtils.b(frameBuffer.byteBuffer, plane3.getBuffer());
            } else {
                ByteBufferUtils.a(frameBuffer.byteBuffer, fVar.d(), plane.getBuffer(), rowStride, fVar.c());
                ByteBufferUtils.a(frameBuffer.byteBuffer, fVar.d() / 2, plane2.getBuffer(), plane2.getRowStride(), fVar.c() / 2);
                ByteBufferUtils.a(frameBuffer.byteBuffer, fVar.d() / 2, plane3.getBuffer(), plane3.getRowStride(), fVar.c() / 2);
            }
        }
        this.f23832b = fVar.d();
        image.close();
    }

    public void g(boolean z12) {
        this.f23834d = z12;
    }

    public void h(boolean z12) {
        this.f23835e = z12;
    }
}
