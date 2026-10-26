package com.miui.extraphoto.docphoto.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import com.miui.extraphoto.common.utils.AssetsUtils;
import com.miui.extraphoto.docphoto.PrivacyWatermarkUtils;
import com.miui.extraphoto.docphoto.document.DocPhotoAccessUtils;
import com.miui.extraphoto.docphoto.ui.HandNode;
import com.miui.extraphoto.docphoto.ui.HandRecoverNode;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$DocumentType;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcess$EnhanceType;
import com.xiaomi.ocr.sdk.imgprocess.DocumentProcessJNI;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DocProcessor {
    private SafeProcessor mInnerProcessor;

    public DocProcessor(Context context) {
        this.mInnerProcessor = new SafeProcessor(context);
    }

    public void release() {
        new Thread(new Runnable() { // from class: com.miui.extraphoto.docphoto.manager.DocProcessor.1
            @Override // java.lang.Runnable
            public void run() {
                DocProcessor.this.mInnerProcessor.release();
                DocProcessor.this.mInnerProcessor = null;
            }
        }).start();
    }

    public float[] doScan(Bitmap bitmap, DocumentProcess$DocumentType documentProcess$DocumentType) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        if (safeProcessor == null) {
            return null;
        }
        return safeProcessor.doScan(bitmap, documentProcess$DocumentType);
    }

    public Bitmap doEnhance(Bitmap bitmap, DocumentProcess$EnhanceType documentProcess$EnhanceType, boolean z) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        return safeProcessor == null ? bitmap : safeProcessor.doEnhance(bitmap, documentProcess$EnhanceType, z);
    }

    public Bitmap doCropAndEnhance(Bitmap bitmap, float[] fArr, DocumentProcess$EnhanceType documentProcess$EnhanceType, DocumentProcess$DocumentType documentProcess$DocumentType, boolean z) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        return safeProcessor == null ? bitmap : safeProcessor.doCropAndEnhance(bitmap, fArr, documentProcess$EnhanceType, documentProcess$DocumentType, z);
    }

    public Bitmap doCrop(Bitmap bitmap, float[] fArr, DocumentProcess$DocumentType documentProcess$DocumentType) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        return safeProcessor == null ? bitmap : safeProcessor.doCrop(bitmap, fArr, documentProcess$DocumentType);
    }

    public Bitmap doUndistortion(Bitmap bitmap) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        return safeProcessor == null ? bitmap : safeProcessor.doUndistortion(bitmap);
    }

    public Pair<Bitmap, Bitmap> doDeHandwrite(Bitmap bitmap, Bitmap bitmap2) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        if (safeProcessor == null) {
            return new Pair<>(bitmap, bitmap2);
        }
        return safeProcessor.doDeHandwrite(bitmap, bitmap2);
    }

    public Bitmap doHandMode(Bitmap bitmap, Bitmap bitmap2, List<HandNode> list, Matrix matrix) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        return safeProcessor == null ? bitmap2 : safeProcessor.doHandMode(bitmap, bitmap2, list, matrix);
    }

    public Bitmap doWatermark(Bitmap bitmap, String str) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        return safeProcessor == null ? bitmap : safeProcessor.doWatermark(bitmap, str);
    }

    public Bitmap doRotate(Bitmap bitmap, int i) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        return safeProcessor == null ? bitmap : safeProcessor.doRotate(bitmap, i);
    }

    public int getPointsModeColor(Bitmap bitmap) {
        SafeProcessor safeProcessor = this.mInnerProcessor;
        if (safeProcessor == null) {
            return 0;
        }
        return safeProcessor.getPointsModeColor(bitmap);
    }

    private static class SafeProcessor {
        private Context mContext;
        private long mNativeObj;
        private boolean mReleased;
        private final Object mLock = new Object();
        private boolean mInited = false;

        public SafeProcessor(Context context) {
            this.mContext = context;
        }

        private void init() throws Throwable {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mContext.getFilesDir());
            String str = File.separator;
            sb.append(str);
            sb.append("doc_model");
            File file = new File(sb.toString(), "dlc");
            if (!file.exists()) {
                file.mkdirs();
            }
            AssetsUtils.copyAlgorithmModelFile(this.mContext.getAssets(), "common" + str + "doc_photo" + str + "doc_model" + str + "dlc", file.getPath());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mContext.getFilesDir());
            sb2.append(str);
            sb2.append("doc_model");
            File file2 = new File(sb2.toString(), "cache");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2.getPath(), "mace_cl_compiled_program.bin");
            if (DocPhotoAccessUtils.isNeedUseCache()) {
                AssetsUtils.copyAlgorithmModelFile(this.mContext.getAssets(), Build.DEVICE + str + "doc_photo" + str + "doc_model" + str + "cache", file2.getPath());
            } else if (!file3.exists()) {
                DocumentProcessJNI.nativeInitAndRun(file2.getPath(), file.getPath());
            }
            this.mNativeObj = DocumentProcessJNI.nativeInit(file2.getPath(), file.getPath());
            Log.d("SafeProcessor", String.format("[Doc] cache dir: %s,model dir: %s", file2.getPath(), file.getPath()));
            this.mInited = true;
        }

        private void assureInit() throws Throwable {
            if (this.mInited) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            init();
            Log.d("SafeProcessor", "[Doc] init consume " + (System.currentTimeMillis() - jCurrentTimeMillis));
        }

        public float[] doScan(Bitmap bitmap, DocumentProcess$DocumentType documentProcess$DocumentType) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return new float[8];
                }
                assureInit();
                long jCurrentTimeMillis = System.currentTimeMillis();
                float[] fArr = new float[8];
                DocumentProcessJNI.nativeScanDocumentBitmap(this.mNativeObj, bitmap, documentProcess$DocumentType.ordinal(), fArr, 0);
                Log.d("SafeProcessor", String.format("[DocPhoto] scan consume %d result %s", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis), Arrays.toString(fArr)));
                return fArr;
            }
        }

        public Bitmap doEnhance(Bitmap bitmap, DocumentProcess$EnhanceType documentProcess$EnhanceType, boolean z) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return bitmap;
                }
                assureInit();
                long jCurrentTimeMillis = System.currentTimeMillis();
                int iNativeEnhanceBitmap = DocumentProcessJNI.nativeEnhanceBitmap(this.mNativeObj, bitmap, documentProcess$EnhanceType.ordinal(), z);
                Log.d("SafeProcessor", String.format("[DocPhoto] enhance type %s consume %d result %d", documentProcess$EnhanceType.toString(), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis), Integer.valueOf(iNativeEnhanceBitmap)));
                if (iNativeEnhanceBitmap != 0) {
                    bitmap = null;
                }
                return bitmap;
            }
        }

        public Bitmap doCropAndEnhance(Bitmap bitmap, float[] fArr, DocumentProcess$EnhanceType documentProcess$EnhanceType, DocumentProcess$DocumentType documentProcess$DocumentType, boolean z) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return bitmap;
                }
                assureInit();
                long jCurrentTimeMillis = System.currentTimeMillis();
                Bitmap bitmapNativeCropAndEnhanceBitmap = DocumentProcessJNI.nativeCropAndEnhanceBitmap(this.mNativeObj, bitmap, fArr, documentProcess$EnhanceType.ordinal(), documentProcess$DocumentType.ordinal(), z);
                Object[] objArr = new Object[2];
                objArr[0] = Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis);
                objArr[1] = Boolean.valueOf(bitmapNativeCropAndEnhanceBitmap != null);
                Log.d("SafeProcessor", String.format("[DocPhoto] crop and enhance consume %d result %b", objArr));
                return bitmapNativeCropAndEnhanceBitmap;
            }
        }

        public Bitmap doCrop(Bitmap bitmap, float[] fArr, DocumentProcess$DocumentType documentProcess$DocumentType) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return bitmap;
                }
                if (fArr == null) {
                    return bitmap;
                }
                assureInit();
                long jCurrentTimeMillis = System.currentTimeMillis();
                Bitmap bitmapNativeCropImageBitmap = DocumentProcessJNI.nativeCropImageBitmap(this.mNativeObj, bitmap, fArr, documentProcess$DocumentType.ordinal());
                Object[] objArr = new Object[2];
                objArr[0] = Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis);
                objArr[1] = Boolean.valueOf(bitmapNativeCropImageBitmap != null);
                Log.d("SafeProcessor", String.format("[DocPhoto] crop consume %d result %b", objArr));
                return bitmapNativeCropImageBitmap;
            }
        }

        public Bitmap doUndistortion(Bitmap bitmap) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return bitmap;
                }
                assureInit();
                long jCurrentTimeMillis = System.currentTimeMillis();
                DocumentProcessJNI.nativeDewarpBitmap(this.mNativeObj, bitmap);
                Log.d("SafeProcessor", String.format("[DocPhoto] dewarp consume %d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                return bitmap;
            }
        }

        public Pair<Bitmap, Bitmap> doDeHandwrite(Bitmap bitmap, Bitmap bitmap2) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return new Pair<>(bitmap, bitmap2);
                }
                assureInit();
                long jCurrentTimeMillis = System.currentTimeMillis();
                DocumentProcessJNI.nativeHandwriteRemoveBitmap(this.mNativeObj, bitmap, bitmap2);
                Log.d("SafeProcessor", String.format("[DocPhoto] dehandwrite consume %d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                return new Pair<>(bitmap, bitmap2);
            }
        }

        public Bitmap doHandMode(Bitmap bitmap, Bitmap bitmap2, List<HandNode> list, Matrix matrix) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return bitmap2;
                }
                Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                long jCurrentTimeMillis = System.currentTimeMillis();
                Canvas canvas = new Canvas(bitmap2);
                if (matrix != null) {
                    canvas.concat(matrix);
                }
                for (HandNode handNode : list) {
                    if (handNode instanceof HandRecoverNode) {
                        ((HandRecoverNode) handNode).setShaderBitmap(null);
                    }
                    handNode.draw(canvas);
                }
                new Canvas(bitmapCopy).drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
                Log.d("SafeProcessor", String.format("[DocPhoto] do hand remove and recover consume %d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                return bitmapCopy;
            }
        }

        public Bitmap doWatermark(Bitmap bitmap, String str) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return bitmap;
                }
                assureInit();
                long jCurrentTimeMillis = System.currentTimeMillis();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888, true, bitmap.getColorSpace());
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
                PrivacyWatermarkUtils.drawWatermark(canvas, str, width, height, 0);
                Log.d("SafeProcessor", String.format("[DocPhoto] watermark consume %d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                return bitmapCreateBitmap;
            }
        }

        public Bitmap doRotate(Bitmap bitmap, int i) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return bitmap;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                Matrix matrix = new Matrix();
                matrix.setRotate(i, bitmap.getWidth(), bitmap.getHeight());
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                Log.d("SafeProcessor", String.format("[DocPhoto] rotate consume %d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                return bitmapCreateBitmap;
            }
        }

        public int getPointsModeColor(Bitmap bitmap) {
            synchronized (this.mLock) {
                if (this.mReleased) {
                    return 0;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                int[] iArr = new int[3];
                DocumentProcessJNI.nativeGetDelegateColor(this.mNativeObj, bitmap, iArr);
                int iRgb = Color.rgb(iArr[0], iArr[1], iArr[2]);
                Log.d("SafeProcessor", String.format("[DocPhoto] calculate points color consume %d", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
                return iRgb;
            }
        }

        public void release() {
            synchronized (this.mLock) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                DocumentProcessJNI.nativeRelease(this.mNativeObj);
                this.mContext = null;
                this.mReleased = true;
                Log.d("SafeProcessor", "[DocPhoto] release consume " + (System.currentTimeMillis() - jCurrentTimeMillis));
            }
        }
    }
}
