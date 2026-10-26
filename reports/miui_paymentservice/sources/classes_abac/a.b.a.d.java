package a.b.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import androidx.core.view.ViewCompat;
import c.b1;
import com.xiaomi.payment.camera.XiaoMIDigitOCR;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: compiled from: DigitOCRRecognizer.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private XiaoMIDigitOCR f411a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f412b = false;

    protected boolean a(Context context) {
        this.f411a = new XiaoMIDigitOCR();
        if (this.f411a.a(context)) {
            return true;
        }
        throw new IllegalStateException("Digital_OCR_svm*.models are not provided rightly");
    }

    public void a(ArrayList<Integer> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = arrayList.get(i).intValue();
        }
        this.f411a.a(iArr);
        if (iArr.length > 0) {
            this.f412b = true;
        }
    }

    public boolean a() {
        return this.f412b;
    }

    public String a(byte[] bArr, int i, int i2, Point point, int i3, int i4, int i5) {
        if (i <= 0 || i2 <= 0 || point == null || i3 <= 0 || i4 <= 0) {
            return null;
        }
        int[] iArr = new int[i * i2];
        a(iArr, bArr, i, i2);
        Bitmap bitmapA = a(a(iArr, i, i2, i5), point.x, point.y, i3, i4);
        String strA = a(bitmapA);
        if (bitmapA != null) {
            bitmapA.recycle();
        }
        return strA;
    }

    private String a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(width * height * 4);
        bitmap.copyPixelsToBuffer(byteBufferAllocateDirect);
        byteBufferAllocateDirect.flip();
        this.f411a.a(byteBufferAllocateDirect, width, height, 4);
        this.f411a.l();
        return this.f411a.h() ? this.f411a.g() : "";
    }

    private Bitmap a(Bitmap bitmap, int i, int i2, int i3, int i4) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i, i2, i3, i4);
        if (bitmapCreateBitmap != bitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    private void a(int[] iArr, byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 * i;
            for (int i6 = 0; i6 < i; i6++) {
                iArr[i5 + i6] = ((bArr[i3 + i6] & b1.h) * 65793) | ViewCompat.MEASURED_STATE_MASK;
            }
            i3 += i;
        }
    }

    private Bitmap a(int[] iArr, int i, int i2, int i3) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
        Bitmap bitmapA = a(bitmapCreateBitmap, i3);
        if (bitmapA != bitmapCreateBitmap) {
            bitmapCreateBitmap.recycle();
        }
        return bitmapA;
    }

    private Bitmap a(Bitmap bitmap, int i) {
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i, bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f);
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap == bitmapCreateBitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return bitmapCreateBitmap;
        } catch (OutOfMemoryError unused) {
            return bitmap;
        }
    }
}
