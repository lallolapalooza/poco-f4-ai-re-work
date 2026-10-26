package app.aifactory.ai.facesegmentation;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import app.aifactory.ai.modelcrypto.ModelCrypto;
import com.snap.nloader.android.NLOader;
import defpackage.AbstractC19915fE3;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FSFaceSegmentation implements AutoCloseable {
    private static final String TAG = "FACE_SEGMENTATION";
    private FSMetricsCallback callback;
    private long interpreterEyes;
    private long interpreterMain;

    public interface InterpreterCreator {
        long makeInterpreter(String str, FSSegmentationModelType fSSegmentationModelType);
    }

    public FSFaceSegmentation(AssetManager assetManager, Map<FSSegmentationModelType, String> map) {
        this(assetManager, map, "MACE");
    }

    public FSFaceSegmentation(final AssetManager assetManager, Map<FSSegmentationModelType, String> map, final String str) throws IOException {
        initInterpreters(map, new InterpreterCreator() { // from class: app.aifactory.ai.facesegmentation.FSFaceSegmentation.2
            @Override // app.aifactory.ai.facesegmentation.FSFaceSegmentation.InterpreterCreator
            public long makeInterpreter(String str2, FSSegmentationModelType fSSegmentationModelType) {
                if (!str2.endsWith(".enc")) {
                    throw new RuntimeException("Model is not encrypted");
                }
                try {
                    byte[] bArrDecrypt = new ModelCrypto().decrypt(FSFaceSegmentation.this.loadModelFile(assetManager, str2));
                    if (bArrDecrypt == null) {
                        return 0L;
                    }
                    return FSFaceSegmentation.this.internalInitInterpreter(bArrDecrypt, fSSegmentationModelType, str);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0L;
                }
            }
        });
    }

    public FSFaceSegmentation(Map<FSSegmentationModelType, String> map, File file) {
        this(map, file, "MACE");
    }

    public FSFaceSegmentation(Map<FSSegmentationModelType, String> map, File file, final String str) throws IOException {
        setCacheDir(file);
        initInterpreters(map, new InterpreterCreator() { // from class: app.aifactory.ai.facesegmentation.FSFaceSegmentation.1
            @Override // app.aifactory.ai.facesegmentation.FSFaceSegmentation.InterpreterCreator
            public long makeInterpreter(String str2, FSSegmentationModelType fSSegmentationModelType) {
                if (!str2.endsWith(".enc")) {
                    throw new RuntimeException("Model is not encrypted");
                }
                try {
                    byte[] bArrDecrypt = new ModelCrypto().decrypt(FSFaceSegmentation.this.loadModelFile(new File(str2)));
                    if (bArrDecrypt == null) {
                        return 0L;
                    }
                    return FSFaceSegmentation.this.internalInitInterpreter(bArrDecrypt, fSSegmentationModelType, str);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0L;
                }
            }
        });
    }

    public static Map<FSSegmentationModelType, String> getModelPaths(Context context) {
        HashMap map = new HashMap();
        map.put(FSSegmentationModelType.Main, FSContext.getMainModelPath(context));
        map.put(FSSegmentationModelType.Eyes, FSContext.getEyesModelPath(context));
        return map;
    }

    private void initInterpreters(Map<FSSegmentationModelType, String> map, InterpreterCreator interpreterCreator) throws IOException {
        FSSegmentationModelType fSSegmentationModelType = FSSegmentationModelType.Main;
        if (map.containsKey(fSSegmentationModelType)) {
            try {
                this.interpreterMain = interpreterCreator.makeInterpreter(map.get(fSSegmentationModelType), fSSegmentationModelType);
            } catch (Exception unused) {
                this.interpreterMain = 0L;
            }
        }
        if (this.interpreterMain == 0) {
            throw new IOException("main model not found");
        }
        FSSegmentationModelType fSSegmentationModelType2 = FSSegmentationModelType.Eyes;
        if (map.containsKey(fSSegmentationModelType2)) {
            try {
                this.interpreterEyes = interpreterCreator.makeInterpreter(map.get(fSSegmentationModelType2), fSSegmentationModelType2);
            } catch (Exception unused2) {
                this.interpreterEyes = 0L;
            }
        }
        if (this.interpreterEyes == 0) {
            throw new IOException("eyes model not found");
        }
    }

    private native void internalCloseInterpreter(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native long internalInitInterpreter(byte[] bArr, FSSegmentationModelType fSSegmentationModelType, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] loadModelFile(AssetManager assetManager, String str) throws IOException {
        AssetFileDescriptor assetFileDescriptorOpenFd = assetManager.openFd(str);
        FileInputStream fileInputStreamCreateInputStream = assetFileDescriptorOpenFd.createInputStream();
        int length = (int) assetFileDescriptorOpenFd.getLength();
        byte[] bArr = new byte[length];
        if (fileInputStreamCreateInputStream.read(bArr) != length) {
            throw new IOException(AbstractC19915fE3.c("Read failed, file ", str));
        }
        fileInputStreamCreateInputStream.close();
        assetFileDescriptorOpenFd.close();
        return bArr;
    }

    public static void loadNativeLibrary(Context context) {
        String nativeLibraryName = FSContext.getNativeLibraryName(context);
        if (nativeLibraryName == null) {
            try {
                NLOader.initializeNativeComponent("face_segmentation_android");
                return;
            } catch (UnsatisfiedLinkError unused) {
                nativeLibraryName = "aifactory_native_sdk";
            }
        }
        NLOader.initializeNativeComponent(nativeLibraryName);
    }

    private native long segmentImageInternal(Bitmap bitmap, float[] fArr, byte[] bArr, FSMetricsCallback fSMetricsCallback);

    private native void setCacheDirInternal(String str);

    private native void warmUpEyesNNInternal();

    private native void warmUpFaceNNInternal();

    @Override // java.lang.AutoCloseable
    public void close() {
        long j = this.interpreterMain;
        if (j != 0) {
            internalCloseInterpreter(j);
        }
        long j2 = this.interpreterEyes;
        if (j2 != 0) {
            internalCloseInterpreter(j2);
        }
        this.interpreterMain = 0L;
        this.interpreterEyes = 0L;
    }

    public FSTargetSegmentationResult segmentImage(Bitmap bitmap, float[] fArr) {
        return segmentImage(bitmap, fArr, null);
    }

    public FSTargetSegmentationResult segmentImage(Bitmap bitmap, float[] fArr, byte[] bArr) {
        return new FSTargetSegmentationResult(segmentImageInternal(bitmap, fArr, bArr, this.callback));
    }

    public void setCacheDir(File file) {
        file.mkdirs();
        if (!file.exists()) {
            throw new RuntimeException(file.getAbsolutePath() + " was not created");
        }
        if (!file.canRead()) {
            throw new RuntimeException(file.getAbsolutePath() + " is not readable");
        }
        if (file.canWrite()) {
            setCacheDirInternal(file.getAbsolutePath());
            return;
        }
        throw new RuntimeException(file.getAbsolutePath() + " is not writable");
    }

    public void setCallback(FSMetricsCallback fSMetricsCallback) {
        this.callback = fSMetricsCallback;
    }

    public native void setEnableNNApi(boolean z);

    public native void setPerformanceMode(FSPerformanceMode fSPerformanceMode);

    public native void setTfliteNumThreads(int i);

    public void warmUpEyesNN() {
        warmUpEyesNNInternal();
    }

    public void warmUpFaceNN() {
        warmUpFaceNNInternal();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] loadModelFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        if (fileInputStream.read(bArr) == length) {
            fileInputStream.close();
            return bArr;
        }
        StringBuilder sbD = AbstractC19915fE3.d("Read failed, file ");
        sbD.append(file.getAbsolutePath());
        throw new IOException(sbD.toString());
    }
}
