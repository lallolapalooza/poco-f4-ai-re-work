package com.ts.faceauth.data;

/* JADX INFO: loaded from: classes.dex */
public class STImage {
    public int mFormat;
    public int mHeight;
    public byte[] mImageData;
    public int mStride;
    public int mWidth;

    public STImage() {
        this.mImageData = null;
    }

    public STImage(byte[] bArr, CvPixelFormat cvPixelFormat, int i, int i2, int i3) {
        this.mImageData = bArr;
        this.mFormat = cvPixelFormat.getValue();
        this.mWidth = i;
        this.mHeight = i2;
        this.mStride = i3;
    }

    public STImage(byte[] bArr, int i, int i2, int i3, int i4) {
        this.mImageData = bArr;
        this.mFormat = i;
        this.mWidth = i2;
        this.mHeight = i3;
        this.mStride = i4;
    }
}
