package com.xiaomi.payment.camera;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import com.mipay.common.data.v0;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class XiaoMIDigitOCR {
    private static final String k = "XiaoMIDigitOCR";
    private static final String l = "Digital_OCR_svm_9.model";
    private static final String m = "Digital_OCR_svm_2.model";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3538e;
    private int[] h;
    private long i;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int[] f3537d = new int[100];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<Rect> f3539f = new ArrayList<>();
    private boolean g = false;
    private long j = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3534a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3535b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3536c = 80;

    static {
        System.loadLibrary("digit_ocr");
    }

    private boolean c(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.h;
            if (i2 >= iArr.length) {
                return false;
            }
            if (i == iArr[i2]) {
                return true;
            }
            i2++;
        }
    }

    private static native boolean nativeBeginProcess(long j, String str, String str2);

    private static native long nativeCreateObject();

    private static native boolean nativeEndProcess(long j);

    private static native int nativeRecogDigit(long j, int[] iArr);

    private static native int nativeSetCameraMode(long j, int i);

    private static native boolean nativeSetImage(long j, ByteBuffer byteBuffer, int i, int i2, int i3);

    private static native void nativeclearResultQueue(long j);

    private static native Rect[] nativegetDigitBoundingBoxes(long j);

    private static native String nativegetDigitResultString(long j);

    private static native int nativegetmeanConfidence(long j);

    private static native int[] nativegetwordConfidences(long j);

    private static native void nativesetDigitRange(long j, int i, int i2);

    public void a(int[] iArr) {
        this.h = iArr;
        int i = 0;
        int i2 = 21;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] > i) {
                i = iArr[i3];
            }
            if (iArr[i3] < i2) {
                i2 = iArr[i3];
            }
        }
        if (i < i2) {
            Log.e("paramsError", "setDigitRange is Error!");
            return;
        }
        this.f3535b = i2;
        this.f3534a = i;
        nativesetDigitRange(this.j, i2, i);
    }

    public void b(int i) {
        this.f3536c = i;
    }

    public List<Rect> d() {
        Rect[] rectArrNativegetDigitBoundingBoxes = nativegetDigitBoundingBoxes(this.j);
        this.f3539f.clear();
        for (Rect rect : rectArrNativegetDigitBoundingBoxes) {
            this.f3539f.add(rect);
        }
        return this.f3539f;
    }

    public int[] e() {
        return this.f3537d;
    }

    public int f() {
        return this.f3538e;
    }

    public String g() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < this.f3538e; i++) {
            sb.append(this.f3537d[i]);
        }
        c();
        return i();
    }

    public boolean h() {
        return this.g;
    }

    public String i() {
        return nativegetDigitResultString(this.j);
    }

    public int j() {
        return nativegetmeanConfidence(this.j);
    }

    public int[] k() {
        return nativegetwordConfidences(this.j);
    }

    public void l() {
        if (this.g) {
            m();
            this.g = false;
        }
        this.f3538e = nativeRecogDigit(this.j, this.f3537d);
    }

    public void m() {
        this.i = System.currentTimeMillis();
    }

    public boolean b() {
        nativeEndProcess(this.j);
        return true;
    }

    public void c() {
        int iJ = j();
        int[] iArrK = k();
        int i = 0;
        for (int i2 : iArrK) {
            i += i2;
        }
        int i3 = this.f3538e;
        if (i3 > 0) {
            int length = i / iArrK.length;
            if (iJ > this.f3536c && c(i3)) {
                this.g = true;
                a();
            } else {
                this.g = false;
            }
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(int i) {
        nativeSetCameraMode(this.j, i);
    }

    public boolean a(ByteBuffer byteBuffer, int i, int i2, int i3) {
        return nativeSetImage(this.j, byteBuffer, i, i2, i3);
    }

    private boolean a(String str, String str2) {
        this.j = nativeCreateObject();
        b(80);
        return nativeBeginProcess(this.j, str, str2);
    }

    public boolean a(Context context) {
        File filesDir = context.getFilesDir();
        String str = filesDir + "/" + l;
        String str2 = filesDir + "/" + m;
        File file = new File(str);
        File file2 = new File(str2);
        if (file.exists() || a(context, filesDir, l)) {
            return (file2.exists() || a(context, filesDir, m)) && a(str, str2);
        }
        return false;
    }

    private boolean a(Context context, File file, String str) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (SecurityException unused) {
                return false;
            }
        }
        if (!file.exists()) {
            return false;
        }
        return v0.a(context, str, file.getAbsolutePath() + File.separator + str);
    }

    public void a() {
        nativeclearResultQueue(this.j);
    }
}
