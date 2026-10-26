package com.faceunity.core.support;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.enumeration.ProcessMode;
import com.faceunity.core.enumeration.RenderTypeEnum;
import com.faceunity.core.utils.FULogger;
import com.faceunity.wrapper.Faceunity;
import h2.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.e;
import yq.d;

/* JADX INFO: loaded from: classes.dex */
public final class FURenderBridge {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a f1380o = new a();
    public static volatile FURenderBridge p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1381a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final yq.c f1382b = kotlin.a.a(new fr.a<FaceBeautyController>() { // from class: com.faceunity.core.support.FURenderBridge$mFaceBeautyController$2
        @Override // fr.a
        public final FaceBeautyController invoke() {
            return new FaceBeautyController();
        }
    });

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<fr.a<d>> f1383c;
    public final List<fr.a<d>> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f1384e;
    public int f;
    public int g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CameraFacingEnum f1385h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1386i;
    public int j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public FUExternalInputEnum f1387k;
    public FUTransformMatrixEnum l;
    public FUTransformMatrixEnum m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public FUTransformMatrixEnum f1388n;

    public static final class a {
        public final FURenderBridge a() {
            if (FURenderBridge.p == null) {
                synchronized (this) {
                    if (FURenderBridge.p == null) {
                        FURenderBridge.p = new FURenderBridge();
                    }
                    d dVar = d.f18754a;
                }
            }
            FURenderBridge fURenderBridge = FURenderBridge.p;
            e.c(fURenderBridge);
            return fURenderBridge;
        }
    }

    public static final class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message msg) {
            e.f(msg, "msg");
            super.handleMessage(msg);
            Object obj = msg.obj;
            e.d(obj, "null cannot be cast to non-null type java.lang.Runnable");
            ((Runnable) obj).run();
        }
    }

    public /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1389a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1390b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final /* synthetic */ int[] f1391c;

        static {
            int[] iArr = new int[FUInputBufferEnum.values().length];
            iArr[0] = 1;
            iArr[2] = 2;
            f1389a = iArr;
            int[] iArr2 = new int[FUExternalInputEnum.values().length];
            iArr2[1] = 1;
            iArr2[2] = 2;
            f1390b = iArr2;
            int[] iArr3 = new int[RenderTypeEnum.values().length];
            iArr3[0] = 1;
            f1391c = iArr3;
        }
    }

    public FURenderBridge() {
        kotlin.a.a(new fr.a<h2.a>() { // from class: com.faceunity.core.support.FURenderBridge$mImageEditorController$2
            @Override // fr.a
            public final a invoke() {
                return new a();
            }
        });
        kotlin.a.a(new fr.a<i2.a>() { // from class: com.faceunity.core.support.FURenderBridge$mVideoFaceBeautyController$2
            @Override // fr.a
            public final i2.a invoke() {
                return new i2.a();
            }
        });
        List<fr.a<d>> listSynchronizedList = Collections.synchronizedList(new ArrayList(16));
        e.e(listSynchronizedList, "synchronizedList(ArrayList<() -> Unit>(16))");
        this.f1383c = listSynchronizedList;
        List<fr.a<d>> listSynchronizedList2 = Collections.synchronizedList(new ArrayList(16));
        e.e(listSynchronizedList2, "synchronizedList(ArrayList<() -> Unit>(16))");
        this.d = listSynchronizedList2;
        this.g = -1;
        this.f1386i = -1;
        this.j = -1;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x007f  */
    public static j2.d c(j2.c cVar) {
        int iFuImageBeautyCreateTextureCoverPreview;
        if (cVar.g == 1) {
            j2.c.a aVar = cVar.f11717e;
            if (aVar != null) {
                FUInputBufferEnum fUInputBufferEnum = FUInputBufferEnum.FU_FORMAT_NV21_BUFFER;
                FUInputBufferEnum fUInputBufferEnum2 = aVar.f11718a;
                if (fUInputBufferEnum2 == fUInputBufferEnum) {
                    SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                    int type = fUInputBufferEnum2.getType();
                    int i10 = cVar.f11714a;
                    int i11 = cVar.f11715b;
                    byte[] bArr = aVar.f11719b;
                    e.c(bArr);
                    iFuImageBeautyCreateTextureCoverPreview = Faceunity.fuImageBeautyCreateTextureCoverPreview(type, i10, i11, bArr, cVar.f11714a * 4, null, 0, null, 0, -1.0f);
                    StringBuilder sbF = a0.a.f("fuImageBeautyCreateTexture format:", type, " width:", i10, "   height:");
                    sbF.append(i11);
                    sbF.append(" id ");
                    sbF.append(iFuImageBeautyCreateTextureCoverPreview);
                    FULogger.a("KIT_SDKController", sbF.toString());
                } else if (fUInputBufferEnum2 == FUInputBufferEnum.FU_FORMAT_BITMAP) {
                    SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
                    Bitmap bitmap = aVar.f11721e;
                    e.c(bitmap);
                    int iFuImageBeautyCreateTextureWithBitmapCoverPreview = Faceunity.fuImageBeautyCreateTextureWithBitmapCoverPreview(bitmap, -1.0f);
                    FULogger.a("KIT_SDKController", "fuImageBeautyCreateTextureWithBitmapCoverPreview  id:" + iFuImageBeautyCreateTextureWithBitmapCoverPreview + "  bitmap " + bitmap + " scaleRatio:-1.0");
                    iFuImageBeautyCreateTextureCoverPreview = iFuImageBeautyCreateTextureWithBitmapCoverPreview;
                } else {
                    iFuImageBeautyCreateTextureCoverPreview = 0;
                }
            } else {
                iFuImageBeautyCreateTextureCoverPreview = 0;
            }
            cVar.g = 0;
        } else {
            iFuImageBeautyCreateTextureCoverPreview = 0;
            f2.b.a aVar2 = f2.b.g;
            if (!(aVar2.a().d().length == 0)) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$3 = SDKController.f1392a;
                int[] items = aVar2.a().d();
                e.f(items, "items");
                int iFuImageBeautyPreview = Faceunity.fuImageBeautyPreview(items);
                StringBuilder sbI = androidx.appcompat.widget.a.i("fuImageBeautyPreview  id:", iFuImageBeautyPreview, "  items:");
                String string = Arrays.toString(items);
                e.e(string, "toString(this)");
                sbI.append(string);
                FULogger.a("KIT_SDKController", sbI.toString());
                iFuImageBeautyCreateTextureCoverPreview = iFuImageBeautyPreview;
            }
            if (iFuImageBeautyCreateTextureCoverPreview <= 0) {
                SDKController.a();
            }
        }
        return new j2.d(new j2.d.a(iFuImageBeautyCreateTextureCoverPreview, cVar.f11714a, cVar.f11715b), 2);
    }

    public static void d(j2.c fuRenderInputData) {
        j2.c.a aVar;
        String string;
        e.f(fuRenderInputData, "fuRenderInputData");
        j2.c.a aVar2 = fuRenderInputData.f11717e;
        if (aVar2 != null) {
            FUInputBufferEnum fUInputBufferEnum = aVar2.f11718a;
            int iOrdinal = fUInputBufferEnum.ordinal();
            f2.b.a aVar3 = f2.b.g;
            if (iOrdinal == 0) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                int type = fUInputBufferEnum.getType();
                int i10 = fuRenderInputData.f11714a;
                int i11 = fuRenderInputData.f11715b;
                byte[] bArr = aVar2.f11719b;
                e.c(bArr);
                SDKController.c(type, i10, i11, bArr, fuRenderInputData.f11714a, aVar3.a().d());
                return;
            }
            if (iOrdinal == 2 && (aVar = fuRenderInputData.f11717e) != null) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
                int i12 = fuRenderInputData.f11714a;
                int i13 = fuRenderInputData.f11715b;
                int[] iArrD = aVar3.a().d();
                Bitmap bitmap = Faceunity.fuImageBeautyGetResultBitmap(i12, i13, iArrD);
                StringBuilder sbF = a0.a.f("fuImageBeautyGetResultBitmap width:", i12, "   height:", i13, "   items:");
                if (iArrD != null) {
                    string = Arrays.toString(iArrD);
                    e.e(string, "toString(this)");
                } else {
                    string = null;
                }
                sbF.append(string);
                FULogger.a("KIT_SDKController", sbF.toString());
                e.e(bitmap, "bitmap");
                aVar.f11721e = bitmap;
            }
        }
    }

    public static void e(j2.c cVar) {
        j2.c.a aVar = cVar.f11717e;
        if (aVar != null) {
            FUInputBufferEnum fUInputBufferEnum = aVar.f11718a;
            int iOrdinal = fUInputBufferEnum.ordinal();
            f2.b.a aVar2 = f2.b.g;
            if (iOrdinal == 0) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                int type = fUInputBufferEnum.getType();
                int i10 = cVar.f11714a;
                int i11 = cVar.f11715b;
                byte[] bArr = aVar.f11719b;
                e.c(bArr);
                SDKController.c(type, i10, i11, bArr, cVar.f11714a, aVar2.a().d());
                return;
            }
            if (iOrdinal != 2) {
                return;
            }
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
            j2.c.a aVar3 = cVar.f11717e;
            String string = null;
            Bitmap bitmap = aVar3 != null ? aVar3.f11721e : null;
            e.c(bitmap);
            int[] iArrD = aVar2.a().d();
            StringBuilder sbI = androidx.appcompat.widget.a.i("fuImageBeautyGetResultBitmap res: ", Faceunity.fuImageBeautyGetResultWithBitmap(bitmap, iArrD), " items:");
            if (iArrD != null) {
                string = Arrays.toString(iArrD);
                e.e(string, "toString(this)");
            }
            sbI.append(string);
            FULogger.a("KIT_SDKController", sbI.toString());
        }
    }

    public static boolean f(FUTransformMatrixEnum fUTransformMatrixEnum) {
        return fUTransformMatrixEnum == FUTransformMatrixEnum.CCROT90 || fUTransformMatrixEnum == FUTransformMatrixEnum.CCROT270 || fUTransformMatrixEnum == FUTransformMatrixEnum.CCROT90_FLIPVERTICAL || fUTransformMatrixEnum == FUTransformMatrixEnum.CCROT90_FLIPHORIZONTAL;
    }

    public static int h(j2.c cVar) {
        String string;
        String string2;
        j2.c.a aVar = cVar.f11717e;
        if (aVar != null) {
            int iOrdinal = aVar.f11718a.ordinal();
            f2.b.a aVar2 = f2.b.g;
            if (iOrdinal == 0) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                j2.c.a aVar3 = cVar.f11717e;
                e.c(aVar3);
                int type = aVar3.f11718a.getType();
                int i10 = cVar.f11714a;
                int i11 = cVar.f11715b;
                j2.c.a aVar4 = cVar.f11717e;
                byte[] bArr = aVar4 != null ? aVar4.f11719b : null;
                e.c(bArr);
                int i12 = cVar.f11714a;
                int[] iArrD = aVar2.a().d();
                boolean z = cVar.d;
                ProcessMode processMode = cVar.f11716c;
                e.f(processMode, "processMode");
                int iFuImageBeautyPreProcess = Faceunity.fuImageBeautyPreProcess(type, 0, i10, i11, bArr, i12, null, 0, null, 0, iArrD, 1, z ? 1 : 0, processMode.getMode(), -1.0f);
                StringBuilder sbF = a0.a.f("setImageBeautyPreProcess format:", type, "   tex_id:0   width:", i10, "   height:");
                sbF.append(i11);
                sbF.append("   items:");
                if (iArrD != null) {
                    string = Arrays.toString(iArrD);
                    e.e(string, "toString(this)");
                } else {
                    string = null;
                }
                sbF.append(string);
                sbF.append(" isGpuMode:1   isNewImage:");
                sbF.append(z ? 1 : 0);
                sbF.append("   process_mode:");
                sbF.append(processMode.getMode());
                sbF.append("  scaleRatio:-1.0 res ");
                sbF.append(iFuImageBeautyPreProcess);
                FULogger.a("KIT_SDKController", sbF.toString());
                return iFuImageBeautyPreProcess;
            }
            if (iOrdinal == 2) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
                j2.c.a aVar5 = cVar.f11717e;
                e.c(aVar5);
                Bitmap bitmap = aVar5.f11721e;
                e.c(bitmap);
                int[] iArrD2 = aVar2.a().d();
                boolean z10 = cVar.d;
                ProcessMode processMode2 = cVar.f11716c;
                e.f(processMode2, "processMode");
                int iFuImageBeautyPreProcessWithBitmap = Faceunity.fuImageBeautyPreProcessWithBitmap(bitmap, iArrD2, 1, z10 ? 1 : 0, processMode2.getMode(), -1.0f);
                StringBuilder sb2 = new StringBuilder("fuImageBeautyPreProcessWithBitmap bitmap:");
                sb2.append(bitmap);
                sb2.append("  items:");
                if (iArrD2 != null) {
                    string2 = Arrays.toString(iArrD2);
                    e.e(string2, "toString(this)");
                } else {
                    string2 = null;
                }
                sb2.append(string2);
                sb2.append(" isGpuMode:1   isNewImage:");
                sb2.append(z10 ? 1 : 0);
                sb2.append("   process_mode:");
                sb2.append(processMode2.getMode());
                sb2.append("  scaleRatio:-1.0 res ");
                sb2.append(iFuImageBeautyPreProcessWithBitmap);
                FULogger.a("KIT_SDKController", sb2.toString());
                return iFuImageBeautyPreProcessWithBitmap;
            }
        }
        return 0;
    }

    public final int a() {
        FUExternalInputEnum fUExternalInputEnum = this.f1387k;
        int i10 = fUExternalInputEnum == null ? -1 : c.f1390b[fUExternalInputEnum.ordinal()];
        if (i10 == 1) {
            return 0;
        }
        if (i10 != 2) {
            return this.f1385h == CameraFacingEnum.CAMERA_FRONT ? (((this.f1386i + this.j) + 90) % 360) / 90 : (((this.f1386i - this.j) + 270) % 360) / 90;
        }
        int i11 = this.f1386i;
        if (i11 == 90) {
            return 3;
        }
        if (i11 != 180) {
            return i11 != 270 ? 0 : 1;
        }
        return 2;
    }

    public final j2.d b(j2.c cVar, RenderTypeEnum renderTypeEnum) {
        FULogger.a("KIT_FURenderBridge", "fuRenderTexture drawFrame start");
        j2.c.a aVar = cVar.f11717e;
        byte[] bArr = aVar != null ? aVar.f11719b : null;
        FUInputBufferEnum fUInputBufferEnum = aVar != null ? aVar.f11718a : null;
        boolean z = cVar.f.f11726h;
        if (cVar.f11714a <= 0 || cVar.f11715b <= 0) {
            FULogger.b("KIT_FURenderBridge", "renderInput data is illegal   width:" + cVar.f11714a + "  height:" + cVar.f11715b + "  ");
            return new j2.d(null, 3);
        }
        FUTransformMatrixEnum fUTransformMatrixEnum = this.m;
        FUTransformMatrixEnum fUTransformMatrixEnum2 = FUTransformMatrixEnum.CCROT0;
        if (fUTransformMatrixEnum == null) {
            fUTransformMatrixEnum = fUTransformMatrixEnum2;
        }
        boolean zF = f(fUTransformMatrixEnum);
        FUTransformMatrixEnum fUTransformMatrixEnum3 = this.l;
        if (fUTransformMatrixEnum3 == null) {
            fUTransformMatrixEnum3 = fUTransformMatrixEnum2;
        }
        boolean zF2 = f(fUTransformMatrixEnum3);
        FUTransformMatrixEnum fUTransformMatrixEnum4 = this.f1388n;
        if (fUTransformMatrixEnum4 != null) {
            fUTransformMatrixEnum2 = fUTransformMatrixEnum4;
        }
        boolean zF3 = f(fUTransformMatrixEnum2);
        boolean z10 = (zF && !zF3) || (!zF && zF3);
        boolean z11 = (zF2 && !zF3) || (!zF2 && zF3);
        if (c.f1391c[renderTypeEnum.ordinal()] == 1) {
            FUInputBufferEnum fUInputBufferEnum2 = FUInputBufferEnum.FU_FORMAT_YUV_BUFFER;
            f2.b.a aVar2 = f2.b.g;
            if (fUInputBufferEnum == fUInputBufferEnum2) {
                int i10 = cVar.f11714a;
                int i11 = cVar.f11715b;
                j2.c.a aVar3 = cVar.f11717e;
                byte[] bArr2 = aVar3 != null ? aVar3.f11719b : null;
                byte[] bArr3 = aVar3 != null ? aVar3.f11720c : null;
                byte[] bArr4 = aVar3 != null ? aVar3.d : null;
                if (bArr2 == null || bArr3 == null || bArr4 == null) {
                    StringBuilder sb2 = new StringBuilder("drawFrameYUV data is illegal  y_buffer:");
                    sb2.append(bArr2 == null);
                    sb2.append("  u_buffer:");
                    sb2.append(bArr3 == null);
                    sb2.append(" v_buffer:");
                    sb2.append(bArr4 == null);
                    sb2.append(" width:");
                    sb2.append(i10);
                    sb2.append("  height:");
                    sb2.append(i11);
                    sb2.append("  ");
                    FULogger.b("KIT_FURenderBridge", sb2.toString());
                    return new j2.d(null, 3);
                }
                int i12 = z11 ? i10 : i11;
                int i13 = z11 ? i11 : i10;
                int i14 = z10 ? i10 : i11;
                int i15 = z10 ? i11 : i10;
                int length = bArr2.length;
                int length2 = bArr3.length;
                int length3 = bArr4.length;
                int i16 = length + length2 + length3;
                byte[] bArr5 = new byte[i16];
                System.arraycopy(bArr2, 0, bArr5, 0, length);
                for (int i17 = 0; i17 < length3; i17++) {
                    bArr5[(i17 * 2) + length] = bArr4[i17];
                }
                for (int i18 = 0; i18 < length2; i18++) {
                    bArr5[(i18 * 2) + length + 1] = bArr3[i18];
                }
                byte[] bArr6 = z ? new byte[i16] : null;
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
                int i19 = this.f;
                this.f = i19 + 1;
                int iD = SDKController.d(i10, i11, i19, aVar2.a().d(), bArr5, FUInputBufferEnum.FU_FORMAT_NV21_BUFFER.getType(), i15, i14, bArr6);
                if (iD <= 0) {
                    SDKController.a();
                }
                if (!z) {
                    return new j2.d(new j2.d.a(iD, i13, i12), 2);
                }
                int length4 = bArr2.length;
                byte[] bArr7 = new byte[length4];
                int length5 = bArr3.length;
                byte[] bArr8 = new byte[length5];
                int length6 = bArr4.length;
                byte[] bArr9 = new byte[length6];
                e.c(bArr6);
                System.arraycopy(bArr6, 0, bArr7, 0, length4);
                for (int i20 = 0; i20 < length6; i20++) {
                    bArr9[i20] = bArr6[(i20 * 2) + length4];
                }
                for (int i21 = 0; i21 < length5; i21++) {
                    bArr8[i21] = bArr6[(i21 * 2) + length4 + 1];
                }
                j2.d.a aVar4 = new j2.d.a(iD, i13, i12);
                System.arraycopy(bArr7, 0, new byte[length4], 0, length4);
                System.arraycopy(bArr8, 0, new byte[length5], 0, length5);
                System.arraycopy(bArr9, 0, new byte[length6], 0, length6);
                return new j2.d(aVar4);
            }
            if (bArr != null && fUInputBufferEnum != null) {
                int i22 = cVar.f11714a;
                int i23 = cVar.f11715b;
                int type = fUInputBufferEnum.getType();
                int i24 = z11 ? i22 : i23;
                int i25 = z11 ? i23 : i22;
                int i26 = z10 ? i22 : i23;
                int i27 = z10 ? i23 : i22;
                byte[] bArr10 = z ? new byte[bArr.length] : null;
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
                int i28 = this.f;
                this.f = i28 + 1;
                int iD2 = SDKController.d(i22, i23, i28, aVar2.a().d(), bArr, type, i27, i26, bArr10);
                if (iD2 <= 0) {
                    SDKController.a();
                }
                return z ? new j2.d(new j2.d.a(iD2, i25, i24)) : new j2.d(new j2.d.a(iD2, i25, i24), 2);
            }
        }
        return new j2.d(null, 3);
    }

    public final void g() {
        Looper looper;
        synchronized (this.f1381a) {
            f2.b.g.a().f();
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            Faceunity.fuHumanProcessorReset();
            FULogger.a("KIT_SDKController", "fuHumanProcessorReset");
            Faceunity.fuDone();
            FULogger.a("KIT_SDKController", "fuDone");
            Faceunity.fuOnDeviceLost();
            FULogger.a("KIT_SDKController", "fuOnDeviceLost");
            b bVar = this.f1384e;
            if (bVar != null) {
                bVar.removeCallbacksAndMessages(null);
            }
            b bVar2 = this.f1384e;
            if (bVar2 != null && (looper = bVar2.getLooper()) != null) {
                looper.quitSafely();
            }
            this.f1384e = null;
            d dVar = d.f18754a;
        }
    }

    public final void i(j2.c cVar) {
        boolean z;
        int iA;
        FUExternalInputEnum fUExternalInputEnum = this.f1387k;
        j2.c.b bVar = cVar.f;
        FUExternalInputEnum fUExternalInputEnum2 = bVar.f11722a;
        boolean z10 = true;
        int i10 = bVar.f11724c;
        int i11 = bVar.f11723b;
        if (fUExternalInputEnum == fUExternalInputEnum2 && this.f1386i == i11 && this.j == i10) {
            z = false;
        } else {
            this.f1387k = fUExternalInputEnum2;
            this.f1386i = i11;
            this.j = i10;
            z = true;
        }
        CameraFacingEnum cameraFacingEnum = this.f1385h;
        CameraFacingEnum cameraFacingEnum2 = bVar.d;
        if (cameraFacingEnum != cameraFacingEnum2) {
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$1 = SDKController.f1392a;
            FULogger.a("KIT_SDKController", "fuClearCacheResource ");
            Faceunity.fuClearCacheResource();
            this.f1385h = cameraFacingEnum2;
        } else {
            z10 = false;
        }
        if (z10) {
            int iA2 = a();
            if (this.g != iA2) {
                this.g = iA2;
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$2 = SDKController.f1392a;
                FULogger.a("KIT_SDKController", "fuOnCameraChange");
                Faceunity.fuOnCameraChange();
                Faceunity.fuHumanProcessorReset();
                FULogger.a("KIT_SDKController", "fuHumanProcessorReset");
            }
        } else if (z && this.g != (iA = a())) {
            this.g = iA;
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$3 = SDKController.f1392a;
            FULogger.a("KIT_SDKController", "fuOnCameraChange");
            Faceunity.fuOnCameraChange();
            Faceunity.fuHumanProcessorReset();
            FULogger.a("KIT_SDKController", "fuHumanProcessorReset");
        }
        FUTransformMatrixEnum fUTransformMatrixEnum = this.l;
        FUTransformMatrixEnum fUTransformMatrixEnum2 = bVar.f11725e;
        if (fUTransformMatrixEnum2 != fUTransformMatrixEnum) {
            this.l = fUTransformMatrixEnum2;
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$4 = SDKController.f1392a;
            int index = fUTransformMatrixEnum2.getIndex();
            FULogger.a("KIT_SDKController", "setInputCameraTextureMatrix    matrix:" + index);
            Faceunity.fuSetInputTextureMatrix(index);
        }
        FUTransformMatrixEnum fUTransformMatrixEnum3 = this.m;
        FUTransformMatrixEnum fUTransformMatrixEnum4 = bVar.f;
        if (fUTransformMatrixEnum4 != fUTransformMatrixEnum3) {
            this.m = fUTransformMatrixEnum4;
            SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$5 = SDKController.f1392a;
            int index2 = fUTransformMatrixEnum4.getIndex();
            FULogger.a("KIT_SDKController", "setInputCameraBufferMatrix    matrix:" + index2);
            Faceunity.fuSetInputBufferMatrix(index2);
        }
        FUTransformMatrixEnum fUTransformMatrixEnum5 = this.f1388n;
        FUTransformMatrixEnum fUTransformMatrixEnum6 = bVar.f11727i;
        if (fUTransformMatrixEnum6 != fUTransformMatrixEnum5) {
            this.f1388n = fUTransformMatrixEnum6;
            if (bVar.g) {
                SDKController$systemErrorMaps$1 sDKController$systemErrorMaps$6 = SDKController.f1392a;
                int index3 = fUTransformMatrixEnum6.getIndex();
                FULogger.a("KIT_SDKController", "fuSetOutputMatrix    matrix:" + index3);
                Faceunity.fuSetOutputMatrix(index3);
            }
        }
    }
}
