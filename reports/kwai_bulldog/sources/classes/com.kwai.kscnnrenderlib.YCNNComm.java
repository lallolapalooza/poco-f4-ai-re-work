package com.kwai.kscnnrenderlib;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.yxcorp.bugly.CrashReporter;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes14.dex */
public class YCNNComm {
    public static final int KSY_COLOR_BGRA = 0;
    public static final int KSY_COLOR_NV12 = 2;
    public static final int KSY_COLOR_NV21 = 3;
    public static final int KSY_COLOR_RGBA = 1;
    public static final int KSY_COLOR_Y = 5;
    public static final int KSY_COLOR_YUV420P = 4;
    public static final int KSY_COLOR_YUVA444 = 6;

    public static class KSImage {
        public int width = 0;
        public int height = 0;
        public int channel = 0;
        public ByteBuffer buffer = null;
        public KSRect range = new KSRect();

        public void setData(ByteBuffer byteBuffer) {
            if (byteBuffer == null || byteBuffer.capacity() < 1) {
                return;
            }
            this.buffer = ByteBuffer.allocateDirect(byteBuffer.capacity());
            byteBuffer.rewind();
            this.buffer.put(byteBuffer);
            byteBuffer.rewind();
            this.buffer.flip();
        }

        public void setSize(int i12, int i13, int i14) {
            this.width = i12;
            this.height = i13;
            this.channel = i14;
            this.buffer = ByteBuffer.allocateDirect(i12 * i13 * i14);
        }
    }

    public static class KSPtInfo implements Serializable {
        public float xPos = 0.0f;
        public float yPos = 0.0f;
        public float zPos = 0.0f;
        public int valid = 0;
    }

    public static class KSRect implements Serializable {
        public int left = 0;
        public int top = 0;
        public int width = 0;
        public int height = 0;
    }

    public static class KSRectf implements Serializable {
        public float left = 0.0f;
        public float top = 0.0f;
        public float width = 0.0f;
        public float height = 0.0f;
    }

    public static class KSVec2f implements Serializable {

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public float f26298x = 0.0f;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public float f26299y = 0.0f;
    }

    public static class KSVec3i implements Serializable {

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f26300x = 0;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f26301y = 0;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f26302z = 0;
    }

    public static void FuncCopyKSImage(KSImage kSImage, KSImage kSImage2) {
        kSImage2.width = kSImage.width;
        kSImage2.height = kSImage.height;
        kSImage2.channel = kSImage.channel;
        KSRect kSRect = kSImage2.range;
        KSRect kSRect2 = kSImage.range;
        kSRect.left = kSRect2.left;
        kSRect.top = kSRect2.top;
        kSRect.width = kSRect2.width;
        kSRect.height = kSRect2.height;
        kSImage2.buffer = cloneByteBuffer(kSImage.buffer);
    }

    public static String YCNNGetMainVersion() {
        return "3.0.96";
    }

    public static ByteBuffer cloneByteBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null || byteBuffer.capacity() < 1) {
            return null;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(byteBuffer.capacity());
        byteBuffer.rewind();
        byteBufferAllocateDirect.put(byteBuffer);
        byteBuffer.rewind();
        byteBufferAllocateDirect.flip();
        byteBufferAllocateDirect.array();
        return byteBufferAllocateDirect;
    }

    public static Bitmap loadImageToBitmap(AssetManager assetManager, String str, int i12, boolean z12) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i13 = 1;
        if (i12 > 0) {
            options.inJustDecodeBounds = true;
            if (z12) {
                try {
                    BitmapFactory.decodeStream(assetManager.open(str), null, options);
                } catch (IOException e12) {
                    CrashReporter.logCheckedException(e12, "YCNNComm.class", "loadImageToBitmap", -101);
                    e12.printStackTrace();
                    return null;
                }
            } else if (new File(str).exists()) {
                BitmapFactory.decodeFile(str, options);
            }
            int i14 = options.outWidth;
            int i15 = options.outHeight;
            while (true) {
                if (i14 / i13 <= i12 && i15 / i13 <= i12) {
                    break;
                }
                i13 *= 2;
            }
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = false;
        options2.inSampleSize = i13;
        if (!z12) {
            if (new File(str).exists()) {
                return BitmapFactory.decodeFile(str, options2);
            }
            return null;
        }
        try {
            return BitmapFactory.decodeStream(assetManager.open(str), null, options2);
        } catch (IOException e13) {
            CrashReporter.logCheckedException(e13, "YCNNComm.class", "loadImageToBitmap", -75);
            e13.printStackTrace();
            return null;
        }
    }

    public static int loadImageToRGBA(AssetManager assetManager, String str, int i12, boolean z12, KSImage kSImage) {
        Bitmap bitmapLoadImageToBitmap = loadImageToBitmap(assetManager, str, i12, z12);
        if (bitmapLoadImageToBitmap == null) {
            return 0;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bitmapLoadImageToBitmap.getByteCount());
        bitmapLoadImageToBitmap.copyPixelsToBuffer(byteBufferAllocateDirect);
        kSImage.buffer = byteBufferAllocateDirect;
        kSImage.width = bitmapLoadImageToBitmap.getWidth();
        kSImage.height = bitmapLoadImageToBitmap.getHeight();
        kSImage.channel = 4;
        return 1;
    }
}
