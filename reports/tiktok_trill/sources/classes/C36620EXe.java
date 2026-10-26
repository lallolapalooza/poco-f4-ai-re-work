package X;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.bef.effectsdk.BEFEffectNative;
import com.bef.effectsdk.ResourceFinder;
import com.bytedance.covode.number.Covode;
import java.io.ByteArrayOutputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.g.b.m;

/* JADX INFO: renamed from: X.EXe, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes10.dex */
public final class C36620EXe {
    public static final float[] LJJJJLI;
    public static final float[] LJJJJLL;
    public static final short[] LJJJJZ;
    public static final C36622EXg LJJJJZI;
    public long LIZIZ;
    public ResourceFinder LIZJ;
    public String LIZLLL;
    public Long LJ;
    public int LJFF;
    public int LJI;
    public int LJII;
    public int LJIIIIZZ;
    public float LJIIJJI;
    public float LJIIL;
    public float LJIILIIL;
    public float LJIILJJIL;
    public boolean LJIILLIIL;
    public boolean LJIIZILJ;
    public boolean LJIJJ;
    public List<ByteArrayOutputStream> LJJ;
    public int LJJI;
    public int LJJIFFI;
    public int LJJII;
    public int LJJIII;
    public int LJJIIJ;
    public int LJJIIJZLJL;
    public int LJJIIZ;
    public int LJJIIZI;
    public int LJJIJ;
    public int[] LJJIJIIJI;
    public int[] LJJIJIIJIL;
    public int[] LJJIJIL;
    public FloatBuffer LJJIJL;
    public FloatBuffer LJJIJLIJ;
    public ShortBuffer LJJIL;
    public int[] LJJIZ;
    public int[] LJJJ;
    public int[] LJJJI;
    public int[] LJJJIL;
    public int[] LJJJJ;
    public int[] LJJJJI;
    public int[] LJJJJIZL;
    public int[] LJJJJJ;
    public int[] LJJJJJL;
    public C1IX<C24590xB> LJJJJL;
    public int[] LJJJLIIL;
    public final ReentrantLock LIZ = new ReentrantLock();
    public final int LJIIIZ = 480;
    public final int LJIIJ = 270;
    public boolean LJIILL = true;
    public int LJIJ = 30;
    public int LJIJI = 30;
    public final int LJIJJLI = 256;
    public final int LJIL = 256;

    private void LJ() {
        ResourceFinder resourceFinder = this.LIZJ;
        if (resourceFinder != null) {
            BEFEffectNative.releaseResourceFinder(this.LIZIZ, resourceFinder);
            this.LIZJ = null;
        }
    }

    public final void LIZ() {
        long j = this.LIZIZ;
        if (j != 0) {
            BEFEffectNative.destroyHandle(j);
            this.LIZIZ = 0L;
        }
    }

    public final void finalize() {
        LJ();
        LIZ();
        LIZLLL();
        LIZIZ();
    }

    public final void LIZIZ() {
        int i = this.LJJIIJZLJL;
        if (i != 0) {
            GLES20.glDeleteProgram(i);
        }
        int i2 = this.LJJI;
        if (i2 != 0) {
            GLES20.glDeleteProgram(i2);
        }
        int[] iArr = this.LJJIJIIJI;
        if (iArr != null) {
            GLES20.glDeleteBuffers(1, iArr, 0);
        }
        int[] iArr2 = this.LJJIJIIJIL;
        if (iArr2 != null) {
            GLES20.glDeleteBuffers(1, iArr2, 0);
        }
        int[] iArr3 = this.LJJIJIL;
        if (iArr3 != null) {
            GLES20.glDeleteBuffers(1, iArr3, 0);
        }
    }

    public final void LIZLLL() {
        int[] iArr = this.LJJIZ;
        if (iArr != null) {
            GLES20.glDeleteFramebuffers(1, iArr, 0);
        }
        int[] iArr2 = this.LJJJ;
        if (iArr2 != null) {
            GLES20.glDeleteTextures(1, iArr2, 0);
        }
        int[] iArr3 = this.LJJJI;
        if (iArr3 != null) {
            GLES20.glDeleteFramebuffers(1, iArr3, 0);
        }
        int[] iArr4 = this.LJJJIL;
        if (iArr4 != null) {
            GLES20.glDeleteTextures(1, iArr4, 0);
        }
        int[] iArr5 = this.LJJJJ;
        if (iArr5 != null) {
            GLES20.glDeleteFramebuffers(1, iArr5, 0);
        }
        int[] iArr6 = this.LJJJJI;
        if (iArr6 != null) {
            GLES20.glDeleteTextures(1, iArr6, 0);
        }
        int[] iArr7 = this.LJJJJJ;
        if (iArr7 != null) {
            GLES20.glDeleteFramebuffers(1, iArr7, 0);
        }
        int[] iArr8 = this.LJJJJJL;
        if (iArr8 != null) {
            GLES20.glDeleteTextures(1, iArr8, 0);
        }
        int[] iArr9 = this.LJJJJIZL;
        if (iArr9 != null) {
            GLES20.glDeleteFramebuffers(1, iArr9, 0);
        }
        int[] iArr10 = this.LJJJLIIL;
        if (iArr10 != null) {
            GLES20.glDeleteTextures(1, iArr10, 0);
        }
    }

    static {
        Covode.recordClassIndex(109416);
        LJJJJZI = new C36622EXg((byte) 0);
        LJJJJLI = new float[]{-1.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f};
        LJJJJLL = new float[]{-1.0f, 1.0f, 0.0f, 0.0f, 1.0f, -1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f};
        LJJJJZ = new short[]{0, 1, 2, 0, 2, 3};
    }

    public final void LIZJ() throws Throwable {
        LIZLLL();
        int i = this.LJFF;
        int i2 = this.LJI;
        int[] iArr = new int[1];
        this.LJJIZ = iArr;
        this.LJJJ = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        int[] iArr2 = this.LJJIZ;
        if (iArr2 == null) {
            m.LIZIZ();
        }
        GLES20.glBindFramebuffer(36160, iArr2[0]);
        GLES20.glGenTextures(1, this.LJJJ, 0);
        int[] iArr3 = this.LJJJ;
        if (iArr3 == null) {
            m.LIZIZ();
        }
        GLES20.glBindTexture(3553, iArr3[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
        int[] iArr4 = this.LJJJ;
        if (iArr4 == null) {
            m.LIZIZ();
        }
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr4[0], 0);
        int[] iArr5 = new int[1];
        this.LJJJI = iArr5;
        this.LJJJIL = new int[1];
        GLES20.glGenFramebuffers(1, iArr5, 0);
        int[] iArr6 = this.LJJJI;
        if (iArr6 == null) {
            m.LIZIZ();
        }
        GLES20.glBindFramebuffer(36160, iArr6[0]);
        GLES20.glGenTextures(1, this.LJJJIL, 0);
        int[] iArr7 = this.LJJJIL;
        if (iArr7 == null) {
            m.LIZIZ();
        }
        GLES20.glBindTexture(3553, iArr7[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
        int[] iArr8 = this.LJJJIL;
        if (iArr8 == null) {
            m.LIZIZ();
        }
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr8[0], 0);
        int[] iArr9 = new int[1];
        this.LJJJJ = iArr9;
        this.LJJJJI = new int[1];
        GLES20.glGenFramebuffers(1, iArr9, 0);
        int[] iArr10 = this.LJJJJ;
        if (iArr10 == null) {
            m.LIZIZ();
        }
        GLES20.glBindFramebuffer(36160, iArr10[0]);
        GLES20.glGenTextures(1, this.LJJJJI, 0);
        int[] iArr11 = this.LJJJJI;
        if (iArr11 == null) {
            m.LIZIZ();
        }
        GLES20.glBindTexture(3553, iArr11[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
        int[] iArr12 = this.LJJJJI;
        if (iArr12 == null) {
            m.LIZIZ();
        }
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr12[0], 0);
        int[] iArr13 = new int[1];
        this.LJJJJJ = iArr13;
        this.LJJJJJL = new int[1];
        this.LJII = (this.LJIIJ * i2) / this.LJIIIZ;
        this.LJIIIIZZ = i2;
        GLES20.glGenFramebuffers(1, iArr13, 0);
        int[] iArr14 = this.LJJJJJ;
        if (iArr14 == null) {
            m.LIZIZ();
        }
        GLES20.glBindFramebuffer(36160, iArr14[0]);
        GLES20.glGenTextures(1, this.LJJJJJL, 0);
        int[] iArr15 = this.LJJJJJL;
        if (iArr15 == null) {
            m.LIZIZ();
        }
        GLES20.glBindTexture(3553, iArr15[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexImage2D(3553, 0, 6408, this.LJII, this.LJIIIIZZ, 0, 6408, 5121, null);
        int[] iArr16 = this.LJJJJJL;
        if (iArr16 == null) {
            m.LIZIZ();
        }
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr16[0], 0);
        int[] iArr17 = new int[1];
        this.LJJJJIZL = iArr17;
        this.LJJJLIIL = new int[1];
        GLES20.glGenFramebuffers(1, iArr17, 0);
        int[] iArr18 = this.LJJJJIZL;
        if (iArr18 == null) {
            m.LIZIZ();
        }
        GLES20.glBindFramebuffer(36160, iArr18[0]);
        GLES20.glGenTextures(1, this.LJJJLIIL, 0);
        int[] iArr19 = this.LJJJLIIL;
        if (iArr19 == null) {
            m.LIZIZ();
        }
        GLES20.glBindTexture(3553, iArr19[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexImage2D(3553, 0, 6408, this.LJIJJLI, this.LJIL, 0, 6408, 5121, null);
        int[] iArr20 = this.LJJJLIIL;
        if (iArr20 == null) {
            m.LIZIZ();
        }
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr20[0], 0);
    }

    public final void LIZ(ResourceFinder resourceFinder) {
        LJ();
        this.LIZJ = resourceFinder;
    }

    public final void LIZ(String str) {
        this.LIZLLL = str;
        this.LJ = Long.valueOf(this.LIZIZ);
        BEFEffectNative.setStickerPath(this.LIZIZ, this.LIZLLL);
    }

    private Bitmap LIZ(Bitmap bitmap) throws Throwable {
        m.LIZLLL(bitmap, "");
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - iMin) / 2, (bitmap.getHeight() - iMin) / 2, iMin, iMin);
        m.LIZIZ(bitmapCreateBitmap, "");
        return bitmapCreateBitmap;
    }

    public final Bitmap LIZ(boolean z) throws Throwable {
        int[] iArr = this.LJJJI;
        if (iArr == null) {
            m.LIZIZ();
        }
        GLES20.glBindFramebuffer(36160, iArr[0]);
        if (z) {
            return LIZ(LIZ(this.LJFF, this.LJI, false));
        }
        return LIZ(this.LJFF, this.LJI, false);
    }

    public final void LIZIZ(int[] iArr, float[] fArr, float[] fArr2) {
        BEFEffectNative.touchMoveEvent(this.LIZIZ, iArr, fArr, fArr2);
    }

    public final void LIZJ(int[] iArr, float[] fArr, float[] fArr2) {
        BEFEffectNative.touchEndEvent(this.LIZIZ, iArr, fArr, fArr2);
    }

    public final Bitmap LIZ(int i, int i2, boolean z) throws Throwable {
        int i3;
        int i4 = i * i2;
        int[] iArr = new int[i4];
        int[] iArr2 = new int[i4];
        IntBuffer intBufferWrap = IntBuffer.wrap(iArr);
        intBufferWrap.position(0);
        GLES20.glReadPixels(0, 0, i, i2, 6408, 5121, intBufferWrap);
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i5 * i;
            if (z) {
                i3 = ((i2 - i5) - 1) * i;
            } else {
                i3 = i6;
            }
            for (int i7 = 0; i7 < i; i7++) {
                int i8 = iArr[i6 + i7];
                iArr2[i3 + i7] = (i8 & (-16711936)) | ((i8 << 16) & 16711680) | ((i8 >> 16) & 255);
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr2, i, i2, Bitmap.Config.ARGB_8888);
        m.LIZIZ(bitmapCreateBitmap, "");
        return bitmapCreateBitmap;
    }

    public final void LIZ(int i, int i2, float f) throws Throwable {
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        GLES20.glBindFramebuffer(36160, i2);
        if (f > 0.0f) {
            z = true;
            i3 = this.LJJI;
        } else {
            z = false;
            i3 = this.LJJIIJZLJL;
        }
        if (z) {
            i4 = 36197;
        } else {
            i4 = 3553;
        }
        if (z) {
            i5 = this.LJJIFFI;
        } else {
            i5 = this.LJJIJ;
        }
        if (z) {
            i6 = this.LJJIII;
        } else {
            i6 = this.LJJIIZ;
        }
        if (z) {
            i7 = this.LJJII;
        } else {
            i7 = this.LJJIIZI;
        }
        if (z) {
            int[] iArr = this.LJJIJIIJIL;
            if (iArr == null) {
                m.LIZIZ();
            }
            i8 = iArr[0];
        } else {
            int[] iArr2 = this.LJJIJIIJI;
            if (iArr2 == null) {
                m.LIZIZ();
            }
            i8 = iArr2[0];
        }
        GLES20.glUseProgram(i3);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(i4, i);
        GLES20.glUniform1i(i5, 0);
        if (z) {
            GLES20.glUniform1f(this.LJJIIJ, f);
        }
        GLES20.glBindBuffer(34962, i8);
        GLES20.glVertexAttribPointer(i7, 2, 5126, false, 20, 0);
        GLES20.glEnableVertexAttribArray(i7);
        GLES20.glVertexAttribPointer(i6, 2, 5126, false, 20, 12);
        GLES20.glEnableVertexAttribArray(i6);
        int[] iArr3 = this.LJJIJIL;
        if (iArr3 == null) {
            m.LIZIZ();
        }
        GLES20.glBindBuffer(34963, iArr3[0]);
        GLES20.glDrawElements(4, 6, 5123, 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    public final void LIZ(int[] iArr, float[] fArr, float[] fArr2) {
        BEFEffectNative.touchBeginEvent(this.LIZIZ, iArr, fArr, fArr2);
    }

    public final void LIZ(int i, int i2, int i3, String str) {
        C22090t9.LIZ("JEFF", "sendMessage, type=" + i + ", arg1=" + i2 + ", arg2=" + i3 + ", arg3=" + str);
        BEFEffectNative.sendMessage(this.LIZIZ, i, i2, i3, str);
    }
}
