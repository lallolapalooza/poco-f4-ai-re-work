package com.miui.gallery.editor.photo.app.remover;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import com.miui.mediaeditor.photo.remover.model.RemoverNNFData;
import kotlinx.coroutines.internal.a;
import q2.b;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class Inpaint {
    private static final String TAG = "Inpaint";
    private static boolean sLoaded = false;

    static {
        try {
            b.a().b(a.L, "vis");
            b.a().b(a.L, "inpaint");
            b.a().b(a.L, "inpaint_jni");
            sLoaded = true;
        } catch (Error e10) {
            a.r0(e10, TAG, "library load failed.\n");
        }
    }

    public static native boolean initialize();

    public static native long inpaintBmp(Bitmap bitmap, Bitmap bitmap2, int i10, int i11, int i12, int i13, int i14, int i15);

    public static native int inpaintBmpData(Bitmap bitmap, Bitmap bitmap2, int i10, int i11, int i12, int i13, int i14, int i15, RemoverNNFData removerNNFData);

    public static boolean isAvailable() {
        return sLoaded;
    }

    public static native void release();

    public static native void releaseNNF(long j);

    public static native void upsampleBmp(Bitmap bitmap, Bitmap bitmap2, int i10, int i11, int i12, int i13, int i14, int i15, long j);

    public static native void upsampleBmpData(Bitmap bitmap, Bitmap bitmap2, int i10, int i11, int i12, int i13, int i14, int i15, RemoverNNFData removerNNFData);
}
