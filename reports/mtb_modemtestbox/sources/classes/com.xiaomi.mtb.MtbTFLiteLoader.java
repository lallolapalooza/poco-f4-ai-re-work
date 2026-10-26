package com.xiaomi.mtb;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.util.Log;
import h.g;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;
import org.tensorflow.lite.e;

/* JADX INFO: loaded from: classes.dex */
public class MtbTFLiteLoader {
    private static final String TAG = "MtbTFLiteLoader";
    private final Context mContext;
    private e mTFLite;
    private final String modelName;

    public MtbTFLiteLoader(Context context, String str) {
        this.mContext = context;
        this.modelName = str;
        try {
            if (Objects.isNull(this.mTFLite)) {
                this.mTFLite = new e(loadModelFile(context.getAssets(), str));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:29:0x0058  */
    private MappedByteBuffer loadModelFile(AssetManager assetManager, String str) throws Throwable {
        FileChannel channel;
        FileInputStream fileInputStream;
        log("on loading Model File");
        AssetFileDescriptor assetFileDescriptorOpenFd = this.mContext.getAssets().openFd(this.modelName);
        FileInputStream fileInputStream2 = null;
        map = null;
        MappedByteBuffer map = null;
        try {
            fileInputStream = new FileInputStream(assetFileDescriptorOpenFd.getFileDescriptor());
            try {
                channel = fileInputStream.getChannel();
                try {
                    try {
                        map = channel.map(FileChannel.MapMode.READ_ONLY, assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getDeclaredLength());
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (channel != null) {
                        }
                        return map;
                    }
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                channel = null;
            } catch (Throwable th2) {
                th = th2;
                channel = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (channel != null) {
                    channel.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            fileInputStream = null;
            channel = null;
        } catch (Throwable th3) {
            th = th3;
            channel = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (channel != null) {
                channel.close();
            }
            throw th;
        }
        channel.close();
        return map;
    }

    private static void log(String str) {
        Log.d(TAG, "MTB_ " + str);
    }

    public g getInputTensor(int i2) {
        return this.mTFLite.c(i2);
    }

    public g getOutputTensor(int i2) {
        return this.mTFLite.d(i2);
    }

    public void run(Object obj, Object obj2) {
        log("Run Model");
        this.mTFLite.e(obj, obj2);
    }
}
