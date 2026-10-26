package com.faceunity.core.renderer.gltexturerender;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.opengl.GLES20;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.ProcessMode;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.support.SDKController$systemErrorMaps$1;
import com.faceunity.wrapper.Faceunity;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.internal.e;

/* JADX INFO: loaded from: classes.dex */
public final class d extends BaseGLTextureRenderer {
    public final String V;
    public final Bitmap W;
    public final Bitmap X;
    public final String Y;
    public final ProcessMode Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final FUInputBufferEnum f1373a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final boolean f1374b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f1375c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final yq.c f1376d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f1377e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f1378f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final ArrayList f1379g0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(b2.a aVar, Bitmap bitmap, Bitmap bitmap2, String str, ProcessMode mProcessMode, FUInputBufferEnum mUserImageType, boolean z, boolean z10, l2.a aVar2) {
        super(aVar, aVar2, z);
        e.f(mProcessMode, "mProcessMode");
        e.f(mUserImageType, "mUserImageType");
        this.V = null;
        this.W = bitmap;
        this.X = bitmap2;
        this.Y = str;
        this.Z = mProcessMode;
        this.f1373a0 = mUserImageType;
        this.f1374b0 = z10;
        this.f1375c0 = true;
        this.f1376d0 = kotlin.a.a(new fr.a<ExecutorService>() { // from class: com.faceunity.core.renderer.gltexturerender.GLTexturePhotoRendererNoCache$mCacheExecutor$2
            @Override // fr.a
            public final ExecutorService invoke() {
                return Executors.newSingleThreadExecutor();
            }
        });
        this.v.f11717e = new j2.c.a(mUserImageType, 30);
        this.f1377e0 = this.r;
        this.f1378f0 = this.s;
        this.f1379g0 = bp.b.V(ProcessMode.FU_IMAGE_EDITOR_MODE_GLOBAL_ENHANCE, ProcessMode.FU_IMAGE_EDITOR_MODE_LOCAL_ENHANCE, ProcessMode.FU_IMAGE_EDITOR_MODE_AUTOCROP, ProcessMode.FU_IMAGE_EDITOR_MODE_SCENE_BEAUTIFY, ProcessMode.FU_IMAGE_EDITOR_MODE_ID_PHOTO, ProcessMode.FU_IMAGE_EDITOR_MODE_ART_PHOTO, ProcessMode.FU_IMAGE_EDITOR_MODE_PORTRAIT_COLOR_FOCUS, ProcessMode.FU_IMAGE_EDITOR_MODE_HDR);
    }

    @Override // com.faceunity.core.renderer.gltexturerender.BaseGLTextureRenderer
    public final void b() {
        GLES20.glViewport(0, 0, this.f1362n, this.f1363o);
        if (!this.G || this.f1368x <= 0) {
            n2.c cVar = this.l;
            e.c(cVar);
            cVar.c(this.f1367w, this.A, this.C);
        } else {
            n2.c cVar2 = this.l;
            e.c(cVar2);
            cVar2.c(this.f1368x, this.f1369y, this.z);
        }
        if (this.N) {
            GLES20.glViewport(this.L, this.M, this.O, this.P);
            n2.b bVar = this.m;
            e.c(bVar);
            int i10 = this.f1368x;
            float[] fArr = this.D;
            float[] fArr2 = this.E;
            float[] fArr3 = this.F;
            int i11 = this.K;
            p2.a.b("draw start");
            GLES20.glUseProgram(bVar.f15599a);
            p2.a.b("glUseProgram");
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i10);
            GLES20.glActiveTexture(33985);
            GLES20.glBindTexture(3553, i11);
            GLES20.glUniform1i(GLES20.glGetUniformLocation(bVar.f15599a, "backTexture"), 1);
            GLES20.glUniformMatrix4fv(bVar.f15420c, 1, false, fArr2, 0);
            p2.a.b("glUniformMatrix4fv");
            GLES20.glUniformMatrix4fv(bVar.d, 1, false, fArr3, 0);
            p2.a.b("glUniformMatrix4fv");
            GLES20.glUniformMatrix4fv(bVar.f15421e, 1, false, fArr, 0);
            p2.a.b("glUniformMatrix4fv");
            GLES20.glEnableVertexAttribArray(bVar.f);
            p2.a.b("glEnableVertexAttribArray");
            int i12 = bVar.f;
            o2.a aVar = bVar.f15600b;
            GLES20.glVertexAttribPointer(i12, 2, 5126, false, 8, (Buffer) aVar.f15597b);
            p2.a.b("glVertexAttribPointer");
            GLES20.glEnableVertexAttribArray(bVar.g);
            p2.a.b("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(bVar.g, 2, 5126, false, 8, (Buffer) aVar.f15596a);
            p2.a.b("glVertexAttribPointer");
            GLES20.glDrawArrays(5, 0, aVar.f15598c);
            p2.a.b("glDrawArrays");
            GLES20.glDisableVertexAttribArray(bVar.f);
            GLES20.glDisableVertexAttribArray(bVar.g);
            GLES20.glBindTexture(3553, 0);
            GLES20.glUseProgram(0);
        }
    }

    public final void k(j2.c cVar) {
        int attributeInt;
        Bitmap bitmapCreateBitmap = this.W;
        if (bitmapCreateBitmap == null) {
            String str = this.V;
            e.c(str);
            Bitmap bitmap = BitmapFactory.decodeFile(str, new BitmapFactory.Options());
            if (bitmap.getConfig() == Bitmap.Config.RGBA_F16) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int[] iArr = new int[height * width];
                bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
                bitmap = Bitmap.createBitmap(iArr, 0, width, width, height, Bitmap.Config.ARGB_8888);
            }
            int i10 = 0;
            try {
                attributeInt = new ExifInterface(str).getAttributeInt("Orientation", -1);
            } catch (IOException e10) {
                e10.printStackTrace();
                attributeInt = 0;
            }
            if (attributeInt == 3) {
                i10 = 180;
            } else if (attributeInt == 6) {
                i10 = 90;
            } else if (attributeInt == 8) {
                i10 = 270;
            }
            e.e(bitmap, "bitmap");
            if (i10 == 90 || i10 == 180 || i10 == 270) {
                Matrix matrix = new Matrix();
                matrix.postRotate(i10);
                bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                e.e(bitmapCreateBitmap, "createBitmap(bitmap, 0, …map.height, matrix, true)");
            } else {
                bitmapCreateBitmap = bitmap;
            }
        }
        this.r = bitmapCreateBitmap.getWidth();
        this.s = bitmapCreateBitmap.getHeight();
        cVar.getClass();
        ProcessMode processMode = this.Z;
        e.f(processMode, "<set-?>");
        cVar.f11716c = processMode;
        int i11 = this.r;
        cVar.f11714a = i11;
        int i12 = this.s;
        cVar.f11715b = i12;
        j2.c.a aVar = cVar.f11717e;
        if (aVar != null) {
            FUInputBufferEnum fUInputBufferEnum = FUInputBufferEnum.FU_FORMAT_NV21_BUFFER;
            FUInputBufferEnum fUInputBufferEnum2 = aVar.f11718a;
            if (fUInputBufferEnum2 != fUInputBufferEnum) {
                if (fUInputBufferEnum2 == FUInputBufferEnum.FU_FORMAT_BITMAP) {
                    aVar.f11721e = bitmapCreateBitmap;
                    return;
                }
                return;
            }
            int i13 = (i11 % 2) + i11;
            this.f1377e0 = i13;
            int i14 = (i12 % 2) + i12;
            this.f1378f0 = i14;
            cVar.f11714a = i13;
            cVar.f11715b = i14;
            byte[] bArr = new byte[((i14 * i13) * 3) / 2];
            FURenderKit fURenderKitC = c();
            int i15 = this.f1377e0;
            int i16 = this.f1378f0;
            fURenderKitC.getClass();
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            Faceunity.fuImageBeautyConvertRGBA2NV21WithBitmap(bitmapCreateBitmap, i15, i16, bArr);
            aVar.f11719b = bArr;
            bitmapCreateBitmap.recycle();
        }
    }

    public final void l(final com.miui.mediaeditor.algo.beauty.common.core.render.b bVar) {
        c().a(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.GLTexturePhotoRendererNoCache$prepareWrinkle$3
            {
                super(0);
            }

            @Override // fr.a
            public final yq.d invoke() {
                m2.a aVar = this.this$0.c().f1330c;
                if (aVar != null && aVar.f14097h) {
                    aVar.e(Double.valueOf(1.0d), "image_do_perpare_wrinkle");
                }
                return yq.d.f18754a;
            }
        });
        c().b(new fr.a<yq.d>() { // from class: com.faceunity.core.renderer.gltexturerender.GLTexturePhotoRendererNoCache$prepareWrinkle$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // fr.a
            public final yq.d invoke() {
                bVar.a(203, "prepareWrinkle success");
                return yq.d.f18754a;
            }
        });
        synchronized (this) {
            i(true);
        }
    }
}
