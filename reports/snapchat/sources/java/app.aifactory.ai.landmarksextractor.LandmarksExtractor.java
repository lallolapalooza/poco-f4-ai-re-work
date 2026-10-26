package app.aifactory.ai.landmarksextractor;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.RectF;
import com.snap.nloader.android.NLOader;
import defpackage.AbstractC19915fE3;
import defpackage.C23371i18;
import defpackage.C34822rGi;
import defpackage.EnumC25847k18;
import defpackage.InterfaceC24609j18;
import defpackage.TM5;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LandmarksExtractor implements AutoCloseable {
    private long interpreterAligner;
    private long interpreterRefiner;

    public LandmarksExtractor(AssetManager assetManager, Map<EnumC25847k18, String> map) throws IOException {
        initInterpreters(map, new C34822rGi(this, assetManager, 4));
    }

    public LandmarksExtractor(Map<EnumC25847k18, String> map) throws IOException {
        initInterpreters(map, new C23371i18(this, 0));
    }

    private native float[] convert98LandmarksTo78LandmarksInternal(float[] fArr);

    private native float[] getLandmarksFastInternal(Bitmap bitmap, RectF rectF, int i);

    private native float[] getLandmarksInternal(Bitmap bitmap, RectF rectF, int i);

    private native float[] getLandmarksRefinedInternal(Bitmap bitmap, float[] fArr);

    public static Map<EnumC25847k18, String> getModelPaths(Context context) {
        HashMap map = new HashMap();
        map.put(EnumC25847k18.ALIGNER, TM5.j(context, "landmarksextractor:model_path_aligner"));
        map.put(EnumC25847k18.REFINER, TM5.j(context, "landmarksextractor:model_path_refiner"));
        return map;
    }

    private void initInterpreters(Map<EnumC25847k18, String> map, InterfaceC24609j18 interfaceC24609j18) throws IOException {
        EnumC25847k18 enumC25847k18 = EnumC25847k18.ALIGNER;
        if (map.containsKey(enumC25847k18)) {
            try {
                this.interpreterAligner = interfaceC24609j18.g(map.get(enumC25847k18));
            } catch (Exception unused) {
                this.interpreterAligner = 0L;
            }
        }
        if (this.interpreterAligner == 0) {
            throw new IOException("aligner model not found");
        }
        EnumC25847k18 enumC25847k19 = EnumC25847k18.REFINER;
        if (map.containsKey(enumC25847k19)) {
            try {
                this.interpreterRefiner = interfaceC24609j18.g(map.get(enumC25847k19));
            } catch (Exception unused2) {
                this.interpreterRefiner = 0L;
            }
        }
        if (this.interpreterRefiner == 0) {
            throw new IOException("refiner model not found");
        }
    }

    private native void internalCloseInterpreter(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native long internalInitInterpreter(byte[] bArr);

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
        String strJ = TM5.j(context, "landmarksextractor:native_library");
        if (strJ == null) {
            try {
                NLOader.initializeNativeComponent("landmarks_extractor_android");
                return;
            } catch (UnsatisfiedLinkError unused) {
                strJ = "aifactory_native_sdk";
            }
        }
        NLOader.initializeNativeComponent(strJ);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        long j = this.interpreterAligner;
        if (j != 0) {
            internalCloseInterpreter(j);
        }
        long j2 = this.interpreterRefiner;
        if (j2 != 0) {
            internalCloseInterpreter(j2);
        }
        this.interpreterAligner = 0L;
        this.interpreterRefiner = 0L;
    }

    public float[] convert98LandmarksTo78Landmarks(float[] fArr) {
        return convert98LandmarksTo78LandmarksInternal(fArr);
    }

    public float[] getLandmarks(Bitmap bitmap, RectF rectF, int i) {
        if (this.interpreterAligner == 0 || this.interpreterRefiner == 0) {
            return null;
        }
        return getLandmarksInternal(bitmap, rectF, i);
    }

    public float[] getLandmarksFast(Bitmap bitmap, RectF rectF, int i) {
        if (this.interpreterAligner == 0) {
            return null;
        }
        return getLandmarksFastInternal(bitmap, rectF, i);
    }

    public float[] getLandmarksRefined(Bitmap bitmap, float[] fArr) {
        if (this.interpreterRefiner == 0) {
            return null;
        }
        return getLandmarksRefinedInternal(bitmap, fArr);
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
