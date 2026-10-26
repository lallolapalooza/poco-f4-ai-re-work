package com.kwai.video.westeros.v2.ycnn;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.kwai.video.westeros.models.YCNNFaces;
import com.kwai.video.westeros.models.YCNNResourceConfig;
import com.yxcorp.bugly.CrashReporter;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes13.dex */
public class YcnnPluginUtils {
    public static YCNNFaces getFaceData(Bitmap bitmap, YCNNResourceConfig yCNNResourceConfig, Context context) {
        if (bitmap != null && yCNNResourceConfig.getYcnnModelPath() != null && yCNNResourceConfig.getYcnnModelPath().length() != 0) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(bitmap.getByteCount());
            bitmap.copyPixelsToBuffer(byteBufferAllocateDirect);
            try {
                return YCNNFaces.parseFrom(nativeGetFaceData(bitmap.getWidth(), bitmap.getHeight(), byteBufferAllocateDirect, yCNNResourceConfig.toByteArray(), context));
            } catch (InvalidProtocolBufferException e12) {
                CrashReporter.logCheckedException(e12, "YcnnPluginUtils.class", "getFaceData", 24);
                e12.printStackTrace();
            }
        }
        return null;
    }

    private static native byte[] nativeGetFaceData(int i12, int i13, ByteBuffer byteBuffer, byte[] bArr, Object obj);
}
