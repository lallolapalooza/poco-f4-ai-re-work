package com.miui.image;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.media.Image;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class Yuv {
    public final Rect crop;
    public final int format;
    public final int height;
    public final PlaneWrapper[] planes;
    public final PlaneWrapper u;
    public final PlaneWrapper v;
    public final int width;
    public final PlaneWrapper y;

    static /* synthetic */ String lambda$new$2() {
        return "Supported pixel strides for U and V planes are 1 and 2";
    }

    public Yuv(Image image) {
        int width = image.getWidth();
        this.width = width;
        int height = image.getHeight();
        this.height = height;
        int format = image.getFormat();
        this.format = format;
        this.crop = image.getCropRect();
        Image.Plane[] planes = image.getPlanes();
        require(planes != null && planes.length > 0, "Fail to get image planes");
        PlaneWrapper planeWrapper = new PlaneWrapper(width, height, planes[0]);
        this.y = planeWrapper;
        PlaneWrapper planeWrapper2 = new PlaneWrapper(width / 2, height / 2, planes[1]);
        this.u = planeWrapper2;
        PlaneWrapper planeWrapper3 = new PlaneWrapper(width / 2, height / 2, planes[2]);
        this.v = planeWrapper3;
        this.planes = new PlaneWrapper[]{planeWrapper, planeWrapper2, planeWrapper3};
        require(format == 35, "only support ImageFormat.YUV_420_888 for now");
        require(planeWrapper.pixelStride == 1, (Supplier<String>) new Supplier() { // from class: com.miui.image.Yuv$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.m46lambda$new$0$commiuiimageYuv();
            }
        });
        require(planeWrapper2.pixelStride == planeWrapper3.pixelStride && planeWrapper2.rowStride == planeWrapper3.rowStride, (Supplier<String>) new Supplier() { // from class: com.miui.image.Yuv$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.m47lambda$new$1$commiuiimageYuv();
            }
        });
        require(planeWrapper2.pixelStride == 1 || planeWrapper2.pixelStride == 2, (Supplier<String>) new Supplier() { // from class: com.miui.image.Yuv$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Yuv.lambda$new$2();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-miui-image-Yuv, reason: not valid java name */
    /* synthetic */ String m46lambda$new$0$commiuiimageYuv() {
        return "Pixel stride for Y plane must be 1 but got " + this.y.pixelStride + " instead.";
    }

    /* JADX INFO: renamed from: lambda$new$1$com-miui-image-Yuv, reason: not valid java name */
    /* synthetic */ String m47lambda$new$1$commiuiimageYuv() {
        return "U and V planes must have the same pixel and row strides but got pixel=" + this.u.pixelStride + " row=" + this.u.rowStride + " for U and pixel=" + this.v.pixelStride + " row=" + this.v.rowStride + " for V";
    }

    public byte[] getNV21Bytes() {
        Log.d("", toString());
        byte[] bArr = new byte[size()];
        getNV21Bytes(bArr);
        return bArr;
    }

    public void getNV21Bytes(byte[] bArr) {
        byte[] bArr2 = new byte[this.u.rowStride];
        byte[] bArr3 = new byte[this.v.rowStride];
        int i = 0;
        for (int i2 = 0; i2 < this.y.height - 1; i2++) {
            int i3 = this.y.width * this.y.pixelStride;
            this.y.buffer.get(bArr, i, i3);
            this.y.buffer.position((this.y.buffer.position() + this.y.rowStride) - i3);
            i += i3;
        }
        this.y.buffer.get(bArr, i, Math.min(this.y.buffer.remaining(), this.y.width * this.y.pixelStride));
        int i4 = i + (this.y.width * this.y.pixelStride);
        for (int i5 = 0; i5 < this.v.height - 1; i5++) {
            this.u.buffer.get(bArr2);
            this.v.buffer.get(bArr3);
            for (int i6 = 0; i6 < this.v.width; i6++) {
                int i7 = i4 + 1;
                bArr[i4] = bArr3[this.v.pixelStride * i6];
                i4 += 2;
                bArr[i7] = bArr2[this.u.pixelStride * i6];
            }
        }
        for (int i8 = 0; i8 < this.v.width && this.v.pixelStride * i8 < this.v.buffer.remaining(); i8++) {
            int i9 = i4 + 1;
            bArr[i4] = bArr3[this.v.pixelStride * i8];
            i4 += 2;
            bArr[i9] = bArr2[this.u.pixelStride * i8];
        }
        for (PlaneWrapper planeWrapper : this.planes) {
            planeWrapper.buffer.rewind();
        }
    }

    public int size() {
        return ((this.width * this.height) * ImageFormat.getBitsPerPixel(this.format)) / 8;
    }

    public String toString() {
        return "Yuv{width=" + this.width + ", height=" + this.height + ", format=" + this.format + ", crop=" + this.crop + ", y=" + this.y + ", u=" + this.u + ", v=" + this.v + '}';
    }

    private static void require(boolean z, String str) {
        if (!z) {
            throw new RuntimeException(str);
        }
    }

    private static void require(boolean z, Supplier<String> supplier) {
        if (!z) {
            throw new RuntimeException(supplier.get());
        }
    }

    public class PlaneWrapper {
        public ByteBuffer buffer;
        public final int height;
        public final int pixelStride;
        public final int rowStride;
        public final int width;

        private PlaneWrapper(int i, int i2, Image.Plane plane) {
            this.width = i;
            this.height = i2;
            this.buffer = plane.getBuffer();
            this.rowStride = plane.getRowStride();
            this.pixelStride = plane.getPixelStride();
        }

        public int size() {
            return (this.rowStride * (this.height - 1)) + Yuv.this.width;
        }

        public String toString() {
            return "PlaneWrapper{width=" + this.width + ", height=" + this.height + ", buffer=" + this.buffer + ", rowStride=" + this.rowStride + ", pixelStride=" + this.pixelStride + '}';
        }
    }
}
